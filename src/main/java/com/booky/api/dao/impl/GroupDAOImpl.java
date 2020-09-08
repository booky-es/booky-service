package com.booky.api.dao.impl;

import com.booky.api.context.UserContext;
import com.booky.api.dao.GroupDAO;
import com.booky.api.exception.GroupDAOException;
import com.booky.api.model.Group;
import com.booky.api.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupDAOImpl implements GroupDAO {

	@Autowired
	private GroupRepository groupRepository;

	@Override
	public List<Group> findMyGroups() throws GroupDAOException {
		List<Group> groups = null;
		try {
			groups = groupRepository.findByAdminIds(UserContext.getUserFromContext().getUserId());
		} catch (Exception exception) {
			throw new GroupDAOException(exception);
		}
		return groups;
	}

	@Override
	public Group create(Group group) throws GroupDAOException {
		try {
			group = groupRepository.save(group);
		} catch (Exception exception) {
			throw new GroupDAOException(exception);
		}
		return group;
	}

	@Override
	public Group findOneGroup(long groupId) throws GroupDAOException {
		Group group;
		try {
			group = groupRepository.findById(groupId).orElse(null);
		} catch (Exception exception) {
			throw new GroupDAOException(exception);
		}
		return group;
	}

}
