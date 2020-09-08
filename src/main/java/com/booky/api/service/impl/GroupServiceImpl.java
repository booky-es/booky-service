package com.booky.api.service.impl;

import com.booky.api.dao.GroupDAO;
import com.booky.api.exception.GroupServiceException;
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

}
