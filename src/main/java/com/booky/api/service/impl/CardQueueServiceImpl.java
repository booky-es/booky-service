package com.booky.api.service.impl;

import com.booky.api.constants.CardStatus;
import com.booky.api.constants.Messages;
import com.booky.api.context.UserContext;
import com.booky.api.dao.CardQueueDAO;
import com.booky.api.dao.GroupDAO;
import com.booky.api.exception.CardQueueServiceException;
import com.booky.api.exception.CardServiceException;
import com.booky.api.model.CardQueue;
import com.booky.api.model.CreateCard;
import com.booky.api.model.Group;
import com.booky.api.service.CardQueueService;
import com.booky.api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;


@Service
public class CardQueueServiceImpl implements CardQueueService {

	@Autowired
	private CardService cardService;

	@Autowired
	private CardQueueDAO cardQueueDAO;

	@Autowired
	private GroupDAO groupDAO;

	@Override
	public List<CardQueue> getAllCardQueueOfGroup(long groupId) throws CardQueueServiceException {
		Group group = groupDAO.findOneGroup(groupId);
		if(group == null) throw new CardQueueServiceException(Messages.GET_ALL_QUEUE_CARDS_EXCEPTION_NO_SUCH_GROUP);

		BigInteger userId = UserContext.getUserFromContext().getUserId();
		if(group.getAdminIds().contains(userId)) {
			return cardQueueDAO.findAllCardQueuesInGroup(groupId);
		} else {
			throw new CardQueueServiceException(Messages.GET_ALL_QUEUE_CARDS_EXCEPTION_NOT_ADMIN);
		}
	}

	@Override
	public void rejectCardFromQueue(long id) throws CardQueueServiceException {
		CardQueue cardQueue = cardQueueDAO.findCardQueueById(id);
		Group group = groupDAO.findOneGroup(cardQueue.getGroupId());
		if(group == null) return;

		BigInteger userId = UserContext.getUserFromContext().getUserId();
		if(group.getAdminIds().contains(userId)) {
			cardQueueDAO.deleteCardQueueFromQueueById(id);
		} else {
			throw new CardQueueServiceException(Messages.DELETE_QUEUE_CARD_EXCEPTION_NOT_ADMIN);
		}
	}

	@Override
	@Transactional
	public CreateCard approveCardFromQueue(long id) throws CardQueueServiceException {
		CardQueue cardQueue = cardQueueDAO.findCardQueueById(id);
		CreateCard card = new CreateCard(cardQueue);
		try {
			if(card.getStatus() == CardStatus.PENDING_FOR_CREATION)
				cardService.createCard(card);
			else if(card.getStatus() == CardStatus.PENDING_FOR_UPDATE)
				cardService.updateCard(card);
			cardQueueDAO.deleteCardQueueFromQueueById(id);
		} catch (CardServiceException exception) {
			throw new CardQueueServiceException(exception);
		}
		return card;
	}

}
