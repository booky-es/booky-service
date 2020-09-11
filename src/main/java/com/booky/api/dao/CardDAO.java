package com.booky.api.dao;

import com.booky.api.model.Card;
import com.booky.api.model.Group;


public interface CardDAO {

	Group findGroupOfCard(long cardId);

	Card createCard(Card card);

	Card findCardById(long id);

	Card updateCard(Card card);

	void deleteCard(long id);
}
