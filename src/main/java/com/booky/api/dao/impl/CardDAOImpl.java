package com.booky.api.dao.impl;

import com.booky.api.dao.CardDAO;
import com.booky.api.model.Card;
import com.booky.api.model.Group;
import com.booky.api.repository.CardRepository;
import com.booky.api.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CardDAOImpl implements CardDAO {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Override
	public Group findGroupOfCard(long id) {
		return groupRepository.findByCardIds(id);
	}

	@Override
	public Card createCard(Card card) {
		Group group = groupRepository.findById(card.getGroupId()).orElse(null);
		card = cardRepository.save(card);
		group.getCardIds().add(card.getId());
		groupRepository.save(group);
		return card;
	}

	@Override
	public Card findCardById(long id) {
		return cardRepository.findById(id).orElse(null);
	}

	@Override
	public Card updateCard(Card card) {
		cardRepository.save(card);
		return card;
	}

	@Override
	public void deleteCard(long id) {
		cardRepository.deleteById(id);
		Group group = groupRepository.findByCardIds(id);
		group.getCardIds().remove(id);
		groupRepository.save(group);
	}

}
