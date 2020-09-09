package com.booky.api.service.impl;

import com.booky.api.dao.CardDAO;
import com.booky.api.exception.CardDAOException;
import com.booky.api.exception.CardServiceException;
import com.booky.api.model.Card;
import com.booky.api.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CardServiceImpl implements CardService {

	@Autowired
	CardDAO cardDAO;

	@Override
	public Card createCard(Card card) throws CardServiceException {
		try {
			card = cardDAO.createCard(card);
		} catch (CardDAOException exception) {
			throw new CardServiceException(exception);
		}
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

}
