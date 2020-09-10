package com.booky.api.dao.impl;


import com.booky.api.dao.CardQueueDAO;
import com.booky.api.exception.CardQueueDAOException;
import com.booky.api.model.CardQueue;
import com.booky.api.model.Group;
import com.booky.api.repository.CardQueueRepository;
import com.booky.api.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardQueueDAOImpl implements CardQueueDAO {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private CardQueueRepository cardQueueRepository;


	@Override
	public List<CardQueue> findAllCardQueuesInGroup(long groupId) throws CardQueueDAOException {
		return null;
	}

	@Override
	public CardQueue createCardQueue(CardQueue cardQueue) {
		Group group = groupRepository.findById(cardQueue.getGroupId()).orElse(null);
		cardQueue = cardQueueRepository.save(cardQueue);
		group.getCardQueueIds().add(cardQueue.getId());
		groupRepository.save(group);
		return cardQueue;
	}

	@Override
	public CardQueue findCardQueueById(long id) throws CardQueueDAOException {
		return null;
	}
}
