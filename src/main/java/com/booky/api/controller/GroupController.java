package com.booky.api.controller;

import com.booky.api.constants.Messages;
import com.booky.api.context.UserContext;
import com.booky.api.exception.BookyException;
import com.booky.api.exception.GroupServiceException;
import com.booky.api.model.CreateGroup;
import com.booky.api.model.Group;
import com.booky.api.service.GroupService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class GroupController {

	private final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	private GroupService groupService;


	/**
	 * Controller method for the user to create a new group
	 *
	 * @return Group
	 * @throws BookyException
	 */
	@ApiOperation(value = "Create Group")
	@PostMapping("/groups")
	public CreateGroup createGroup(@RequestBody CreateGroup group) throws BookyException {
		LOGGER.info("createGroup : Begin ");
		Group newGroup = new Group();
		newGroup.setContext(group.getContext());
		newGroup.getAdminIds().add(UserContext.getUserFromContext().getUserId());
		try {
			groupService.create(newGroup);
			group.setId(newGroup.getId());
		} catch (GroupServiceException exception) {
			LOGGER.error("Error while creating Group {}", exception);
			throw new BookyException(Messages.GROUP_CREATION_EXCEPTION);
		}
		LOGGER.info("createGroup : End ");

		return group;

	}


	/**
	 * Controller method to get a group by Id
	 *
	 * @return Group
	 * @throws BookyException
	 */
	@ApiOperation(value = "Retrieve a Group")
	@GetMapping("/groups/{id}")
	public Group retrieveGroup(@PathVariable("id") long groupId) throws BookyException {
		LOGGER.info("retrieveGroup : Begin ");
		Group group;
		try {
			group = groupService.findOneGroup(groupId);
		} catch (GroupServiceException exception) {
			LOGGER.error("Error while retrieving a Group {}", exception);
			throw new BookyException(Messages.GROUP_RETRIEVAL_EXCEPTION);
		}
		LOGGER.info("retrieveGroup : End ");
		return group;
	}

	/**
	 * Controller method to get all groups
	 *
	 * @return List<Group>
	 * @throws BookyException
	 */
	@ApiOperation(value = "Retrieve all Groups of a User")
	@GetMapping("/groups")
	public List<Group> getAllMyGroups() throws BookyException {
		LOGGER.info("getAllGroups : Begin ");
		List<Group> groups;
		try {
			groups = groupService.findMyGroups();
		} catch (GroupServiceException exception) {
			LOGGER.error("Error while retrieving all Groups of the user{}", exception);
			throw new BookyException(Messages.ALL_GROUPS_RETRIEVAL_EXCEPTION);
		}
		LOGGER.info("getAllGroups : End ");
		return groups;
	}
}
