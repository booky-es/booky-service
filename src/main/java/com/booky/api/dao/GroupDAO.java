package com.booky.api.dao;

import com.booky.api.exception.GroupDAOException;
import com.booky.api.model.Card;
import com.booky.api.model.Group;

import java.util.List;

public interface GroupDAO {

	List<Group> findAllGroups();
	
	Group create(Group group);

	Group updateGroup(Group group);

	Group findOneGroup(long groupId);

	List<Card> findAllCardsInGroup(long groupId) throws GroupDAOException;

}
