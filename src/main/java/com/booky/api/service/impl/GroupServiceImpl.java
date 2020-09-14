package com.booky.api.service.impl;

import com.booky.api.constants.Messages;
import com.booky.api.context.UserContext;
import com.booky.api.dao.GroupDAO;
import com.booky.api.dao.UserDAO;
import com.booky.api.exception.GroupDAOException;
import com.booky.api.exception.GroupServiceException;
import com.booky.api.model.Card;
import com.booky.api.model.Group;
import com.booky.api.model.User;
import com.booky.api.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import java.math.BigInteger;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDAO groupDAO;

	@Autowired
	private UserDAO userDAO;

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
	public List<Group> findAllGroups() throws GroupServiceException {
		List<Group> groups;
		try {
			groups = groupDAO.findAllGroups();
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

	@Override
	public List<User> findAdminsOfGroup(long groupId) throws GroupServiceException {
		Group group = groupDAO.findOneGroup(groupId);
		if(group == null) throw new GroupServiceException(Messages.GROUP_ADMINS_RETRIEVAL_NO_SUCH_GROUP);

		BigInteger userId = UserContext.getUserFromContext().getUserId();
		if(group.getAdminIds().contains(userId)) {
			return userDAO.findUsersByIds(group.getAdminIds());
		}
		else
			throw new GroupServiceException(Messages.GROUP_ADMINS_RETRIEVAL_NOT_ADMIN);
	}

	@Override
	public void addAdminForGroup(long groupId, User user) throws GroupServiceException {
		Group group = groupDAO.findOneGroup(groupId);
		if(group == null) throw new GroupServiceException(Messages.GROUP_ADD_ADMIN_EXCEPTION_NO_SUCH_GROUP);

		User existing_user = userDAO.findUserByEmail(user.getEmail());
		if(existing_user == null) throw new GroupServiceException(Messages.GROUP_ADD_ADMIN_EXCEPTION_NO_SUCH_USER);

		if(group.getAdminIds().contains(existing_user.getUserId()))
			throw new GroupServiceException((Messages.GROUP_ADD_ADMIN_EXCEPTION_ALREADY_ADMIN));

		BigInteger userId = UserContext.getUserFromContext().getUserId();
		if(group.getAdminIds().contains(userId)) {
			group.getAdminIds().add(existing_user.getUserId());
			groupDAO.updateGroup(group);
		} else {
			throw new GroupServiceException(Messages.GROUP_ADD_ADMIN_EXCEPTION_NOT_ADMIN);
		}

	}

	@Override
	public void removeAdminFromGroup(long groupId, User user) throws GroupServiceException {
		Group group = groupDAO.findOneGroup(groupId);
		if(group == null) throw new GroupServiceException(Messages.GROUP_REMOVE_ADMIN_EXCEPTION_NO_SUCH_GROUP);

		User existing_user = userDAO.findUserByEmail(user.getEmail());
		if(existing_user == null) throw new GroupServiceException(Messages.GROUP_REMOVE_ADMIN_EXCEPTION_NO_SUCH_USER);

		BigInteger userId = UserContext.getUserFromContext().getUserId();
		if(group.getAdminIds().contains(userId)) {
			if(group.getAdminIds().size() > 1) {
				group.getAdminIds().remove(existing_user.getUserId());
				groupDAO.updateGroup(group);
			} else {
				throw new GroupServiceException(Messages.GROUP_REMOVE_ADMIN_EXCEPTION_LAST_ADMIN);
			}
		} else {
			throw new GroupServiceException(Messages.GROUP_REMOVE_ADMIN_EXCEPTION_NOT_ADMIN);
		}
	}

}
