package com.booky.api.service;


import com.booky.api.exception.CardServiceException;
import com.booky.api.model.Card;

public interface CardService {

	Card createCard(Card card) throws CardServiceException;

	Card findOneCard(long id) throws CardServiceException;
}
