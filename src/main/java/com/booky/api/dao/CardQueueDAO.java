package com.booky.api.dao;

import com.booky.api.exception.CardQueueDAOException;
import com.booky.api.model.CardQueue;

import java.util.List;

public interface CardQueueDAO {

	List<CardQueue> findAllCardQueuesInGroup(long groupId) throws CardQueueDAOException;

	CardQueue createCardQueue(CardQueue cardQueue);

	CardQueue findCardQueueById(long id) throws CardQueueDAOException;

}
