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
				CardQueue cardQueue = new CardQueue(newCard);
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
			Group group = groupDAO.findOneGroup(card.getGroupId());
			if(group == null) throw new CardServiceException(Messages.CARD_CREATION_EXCEPTION_NO_SUCH_GROUP);

			BigInteger userId = UserContext.getUserFromContext().getUserId();
			if(group.getAdminIds().contains(userId)) {
				cardDAO.createCard(newCard);
				card.setStatus(CardStatus.CREATED);
			} else {
				CardQueue cardQueue = new CardQueue(newCard);
				cardQueueDAO.createCardQueue(cardQueue);
				card.setStatus(CardStatus.PENDING_FOR_UPDATE);
			}
		} catch (CardServiceException exception) {
			throw new CardServiceException(exception);
		}
		card.setId(newCard.getId());
		return card;
	}
}
