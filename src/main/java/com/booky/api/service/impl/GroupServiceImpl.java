package com.booky.api.service.impl;

import com.booky.api.dao.CardDAO;
import com.booky.api.dao.GroupDAO;
import com.booky.api.exception.CardServiceException;
import com.booky.api.exception.GroupDAOException;
import com.booky.api.exception.GroupServiceException;
import com.booky.api.model.Card;
import com.booky.api.model.Group;
import com.booky.api.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDAO groupDAO;

	@Override
	public Group create(Group group) throws GroupServiceException {
		try {
			group = groupDAO.create(group);
		} catch (Exception exception) {
			throw new GroupServiceException(exception);
		}
		return group;
	}

	@Override
	public List<Group> findMyGroups() throws GroupServiceException {
		List<Group> groups;
		try {
			groups = groupDAO.findMyGroups();
		} catch (Exception exception) {
			throw new GroupServiceException(exception);
		}
		return groups;
	}

	@Override
	public Group findOneGroup(long groupId) throws GroupServiceException {
		Group group;
		try {
			group = groupDAO.findOneGroup(groupId);
		} catch (Exception exception) {
			throw new GroupServiceException(exception);
		}
		return group;
	}

	@Override
	public List<Card> findAllCardsInGroup(long groupId) throws GroupServiceException {
		List<Card> cards;
		try {
			cards = groupDAO.findAllCardsInGroup(groupId);
		} catch (GroupDAOException exception) {
			throw new GroupServiceException(exception);
		}
		return cards;
	}

}
