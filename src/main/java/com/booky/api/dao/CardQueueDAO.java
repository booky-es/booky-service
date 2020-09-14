package com.booky.api.dao;

import com.booky.api.model.CardQueue;

import java.util.List;

public interface CardQueueDAO {

	List<CardQueue> findAllCardQueuesInGroup(long groupId);

	CardQueue createCardQueue(CardQueue cardQueue);

	CardQueue findCardQueueById(long id);

	void deleteAllCardQueuesForCard(long cardId);

	void deleteCardQueueFromQueueById(long id);
}
