package com.booky.api.dao;

import com.booky.api.exception.GroupDAOException;
import com.booky.api.model.Card;
import com.booky.api.model.Group;

import java.util.List;

public interface GroupDAO {

	List<Group> findMyGroups() throws GroupDAOException;
	
	Group create(Group group) throws GroupDAOException;

	Group findOneGroup(long groupId) throws GroupDAOException;

	List<Card> findAllCardsInGroup(long groupId) throws GroupDAOException;

}
