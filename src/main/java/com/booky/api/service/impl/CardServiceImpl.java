package com.booky.api.service.impl;

import com.booky.api.constants.CardStatus;
import com.booky.api.constants.Messages;
import com.booky.api.context.UserContext;
import com.booky.api.dao.CardDAO;
import com.booky.api.dao.CardQueueDAO;
import com.booky.api.dao.GroupDAO;
import com.booky.api.exception.CardServiceException;
import com.booky.api.model.Card;
import com.booky.api.model.CardQueue;
import com.booky.api.model.CreateCard;
import com.booky.api.model.Group;
import com.booky.api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;


@Service
public class CardServiceImpl implements CardService {

	@Autowired
	CardDAO cardDAO;

	@Autowired
	GroupDAO groupDAO;

	@Autowired
	CardQueueDAO cardQueueDAO;

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

	@Override
	public CreateCard updateCard(CreateCard card) throws CardServiceException {
		Card newCard = new Card();
		newCard.setId(card.getId());
		newCard.setGroupId(card.getGroupId());
		newCard.setTitle(card.getTitle());
		newCard.setUrl(card.getUrl());
		newCard.setDescription(card.getDescription());
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
			return CardStatus.DELETED;
		} else {
			throw new CardServiceException(Messages.CARD_DELETE_EXCEPTION_NOT_ADMIN);
		}
	}
}
