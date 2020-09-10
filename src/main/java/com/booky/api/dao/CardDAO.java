package com.booky.api.dao;

import com.booky.api.exception.CardDAOException;
import com.booky.api.model.Card;

import java.util.List;

public interface CardDAO {

	List<Card> findAllCardsInGroup(long id) throws CardDAOException;

	Card createCard(Card card);

	Card findCardById(long id) throws CardDAOException;

	Card updateCard(Card card);
}
