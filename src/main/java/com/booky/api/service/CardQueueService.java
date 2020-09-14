package com.booky.api.service;


import com.booky.api.exception.CardQueueServiceException;
import com.booky.api.model.CardQueue;
import com.booky.api.model.CreateCard;

import java.util.List;

public interface CardQueueService {


	List<CardQueue> getAllCardQueueOfGroup(long groupId) throws CardQueueServiceException;

	void rejectCardFromQueue(long id) throws CardQueueServiceException;

	CreateCard approveCardFromQueue(long id) throws CardQueueServiceException;
}
