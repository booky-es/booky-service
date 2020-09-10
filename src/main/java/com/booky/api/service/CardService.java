package com.booky.api.service;


import com.booky.api.exception.CardServiceException;
import com.booky.api.model.Card;
import com.booky.api.model.CreateCard;

public interface CardService {

	CreateCard createCard(CreateCard card) throws CardServiceException;

	Card findOneCard(long id) throws CardServiceException;

	CreateCard updateCard(CreateCard card) throws CardServiceException;
}
