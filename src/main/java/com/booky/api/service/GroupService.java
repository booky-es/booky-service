package com.booky.api.service;


import com.booky.api.exception.GroupServiceException;
import com.booky.api.model.Card;
import com.booky.api.model.Group;
import com.booky.api.model.User;

import java.util.List;

public interface GroupService {

	Group create(Group group) throws GroupServiceException;

	List<Group> findAllGroups() throws GroupServiceException;

	Group findOneGroup(long groupId) throws GroupServiceException;

	List<Card> findAllCardsInGroup(long groupId) throws GroupServiceException;

	List<User> findAdminsOfGroup(long groupId) throws GroupServiceException;

	void addAdminForGroup(long groupId, User user) throws GroupServiceException;

	void removeAdminFromGroup(long groupId, User user) throws GroupServiceException;
}
