package com.booky.api.dao.impl;

import com.booky.api.constants.Messages;
import com.booky.api.dao.GroupDAO;
import com.booky.api.exception.GroupDAOException;
import com.booky.api.model.Card;
import com.booky.api.model.Group;
import com.booky.api.repository.CardRepository;
import com.booky.api.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupDAOImpl implements GroupDAO {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private CardRepository cardRepository;


	@Override
	public List<Group> findAllGroups() {
		return groupRepository.findAll();
	}

	@Override
	public Group create(Group group) {
		return groupRepository.save(group);
	}

	@Override
	public Group findOneGroup(long groupId) {
		return groupRepository.findById(groupId).orElse(null);
	}

	@Override
	public List<Card> findAllCardsInGroup(long groupId) throws GroupDAOException {
		Group group = findOneGroup(groupId);
		if(group == null) throw new GroupDAOException(Messages.GROUP_CARDS_RETRIEVAL_NO_SUCH_GROUP);

		return cardRepository.findByIdIn(group.getCardIds());
	}

}
