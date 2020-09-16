package com.booky.api.service.impl;

import com.booky.api.constants.ApiConstants;
import com.booky.api.constants.CardStatus;
import com.booky.api.constants.Messages;
import com.booky.api.context.UserContext;
import com.booky.api.dao.CardDAO;
import com.booky.api.dao.CardQueueDAO;
import com.booky.api.dao.GroupDAO;
import com.booky.api.exception.CardServiceException;
import com.booky.api.model.*;
import com.booky.api.service.CardService;
import com.booky.api.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;


@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDAO cardDAO;

	@Autowired
	private GroupDAO groupDAO;

	@Autowired
	private CardQueueDAO cardQueueDAO;

	@Autowired
	private UrlService urlService;

	/**
	 * Convert request object to Card object. Validate afterwards.
	 * If admin, generate the short url and create the card.
	 * If not admin, just create a card in the queue.
	 * Set id and status for response card.
	 * Set short url for the response card (this is required
	 * considering the case where card is created by admin)
	 *
	 * @param card
	 * @return
	 * @throws CardServiceException
	 */
	@Override
	public CreateCard createCard(CreateCard card) throws CardServiceException {
		Card newCard = new Card();
		newCard.setGroupId(card.getGroupId());
		newCard.setTitle(card.getTitle());
		newCard.setUrl(card.getUrl());
		newCard.setDescription(card.getDescription());
		try {
			Group group = groupDAO.findOneGroup(card.getGroupId());
			if(group == null) throw new CardServiceException(Messages.CARD_CREATION_EXCEPTION_NO_SUCH_GROUP);

			BigInteger userId = UserContext.getUserFromContext().getUserId();
			if(group.getAdminIds().contains(userId)) {
				URL url = new URL(newCard.getUrl(), LocalDateTime.now().minusDays(ApiConstants.DAYS_TO_MINUS_FOR_PERMANENT_URLS));
				url = urlService.createUrl(url);
				newCard.setShortUrl(url.getShortUrl());
				cardDAO.createCard(newCard);
				card.setStatus(CardStatus.CREATED);

			} else {
				CardQueue cardQueue = new CardQueue(newCard, CardStatus.PENDING_FOR_CREATION);
				cardQueueDAO.createCardQueue(cardQueue);
				card.setStatus(CardStatus.PENDING_FOR_CREATION);
			}
		} catch (CardServiceException exception) {
			throw new CardServiceException(exception);
		}
		card.setId(newCard.getId());
		card.setShortUrl(newCard.getShortUrl());
		return card;
	}

	@Override
	public Card findOneCard(long id) throws CardServiceException {
		Card card;
		try {
			card = cardDAO.findCardById(id);
		} catch (Exception exception) {
			throw new CardServiceException(exception);
		}
		return card;
	}


	/**
	 *
	 * Convert request object to Card object. Validate afterwards
	 * If admin, update the card.
	 * If not admin, create an updated card in the queue.
	 * Set id and status for the response card.
	 *
	 * @param card
	 * @return
	 * @throws CardServiceException
	 */
	@Override
	public CreateCard updateCard(CreateCard card) throws CardServiceException {
		Card newCard = new Card();
		newCard.setId(card.getId());
		newCard.setGroupId(card.getGroupId());
		newCard.setTitle(card.getTitle());
		newCard.setUrl(card.getUrl());
		newCard.setDescription(card.getDescription());
		newCard.setShortUrl(card.getShortUrl());

		try {
			Card existingCard = cardDAO.findCardById(card.getId());
			if(existingCard == null) throw new CardServiceException(Messages.CARD_UPDATE_EXCEPTION_NOT_SUCH_CARD);

			Group group = cardDAO.findGroupOfCard(card.getId());
			if(group == null || group.getId() != card.getGroupId()) throw new CardServiceException(Messages.CARD_UPDATE_EXCEPTION_NO_SUCH_GROUP);

			BigInteger userId = UserContext.getUserFromContext().getUserId();
			if(group.getAdminIds().contains(userId)) {
				cardDAO.updateCard(newCard);
				card.setStatus(CardStatus.UPDATED);
			} else {
				CardQueue cardQueue = new CardQueue(newCard, CardStatus.PENDING_FOR_UPDATE);
				cardQueueDAO.createCardQueue(cardQueue);
				card.setStatus(CardStatus.PENDING_FOR_UPDATE);
			}
		} catch (CardServiceException exception) {
			throw new CardServiceException(exception);
		}
		card.setId(newCard.getId());
		return card;
	}

	/**
	 *
	 * Validate the request.
	 * If admin, delete the card
	 * If not admin raise exception.
	 *
	 * @param id
	 * @return
	 * @throws CardServiceException
	 */
	@Override
	public CardStatus deleteCard(long id) throws CardServiceException {
		Card card = cardDAO.findCardById(id);
		if(card == null) throw new CardServiceException(Messages.CARD_DELETE_EXCEPTION_NO_SUCH_CARD);

		Group group = cardDAO.findGroupOfCard(card.getId());
		if(group == null) throw new CardServiceException(Messages.CARD_DELETE_EXCEPTION_NO_GROUP_FOUND_FOR_CARD);

		BigInteger userId = UserContext.getUserFromContext().getUserId();
		if(group.getAdminIds().contains(userId)) {
			cardDAO.deleteCard(id);
			cardQueueDAO.deleteAllCardQueuesForCard(id);
			urlService.deleteUrl(card.getShortUrl());
			return CardStatus.DELETED;
		} else {
			throw new CardServiceException(Messages.CARD_DELETE_EXCEPTION_NOT_ADMIN);
		}
	}
}
