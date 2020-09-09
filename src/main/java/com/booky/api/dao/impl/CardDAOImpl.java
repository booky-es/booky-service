package com.booky.api.dao.impl;

import com.booky.api.constants.Messages;
import com.booky.api.context.UserContext;
import com.booky.api.dao.CardDAO;
import com.booky.api.exception.CardDAOException;
import com.booky.api.model.Card;
import com.booky.api.model.Group;
import com.booky.api.repository.CardRepository;
import com.booky.api.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
public class CardDAOImpl implements CardDAO {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Override
	public List<Card> findAllCardsInGroup(long groupId) throws CardDAOException {
		List<Card> cards;
		try {
			cards = cardRepository.findAll();
		} catch (Exception exception) {
			throw new CardDAOException(exception);
		}
		return cards;
	}

	@Override
	public Card createCard(Card card) throws CardDAOException {
		Group group = groupRepository.findById(card.getGroupId()).orElse(null);
		if(group == null) throw new CardDAOException(Messages.CARD_CREATION_EXCEPTION_NO_SUCH_GROUP);

		BigInteger userId = UserContext.getUserFromContext().getUserId();
		if(!group.getAdminIds().contains(userId))  throw new CardDAOException(Messages.CARD_CREATION_EXCEPTION_INVALID_ADMIN);

		card = cardRepository.save(card);
		group.getCardIds().add(card.getId());
		groupRepository.save(group);
		return card;
	}

	@Override
	public Card findCardById(long id) throws CardDAOException {
		Card card;
		try {
			card = cardRepository.findById(id).orElse(null);
		} catch (Exception exception) {
			throw new CardDAOException(exception);
		}
		return card;
	}

}
