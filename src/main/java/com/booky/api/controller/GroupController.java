package com.booky.api.controller;

import com.booky.api.constants.Messages;
import com.booky.api.context.UserContext;
import com.booky.api.exception.BookyException;
import com.booky.api.exception.GroupServiceException;
import com.booky.api.model.Card;
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
	public Group createGroup(@RequestBody Group group) throws BookyException {
		LOGGER.info("createGroup : Begin ");
		group.getAdminIds().add(UserContext.getUserFromContext().getUserId());
		try {
			group = groupService.create(group);
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
	public List<Group> getAllGroups() throws BookyException {
		LOGGER.info("getAllGroups : Begin ");
		List<Group> groups;
		try {
			groups = groupService.findAllGroups();
		} catch (GroupServiceException exception) {
			LOGGER.error("Error while retrieving all Groups of the user{}", exception);
			throw new BookyException(Messages.ALL_GROUPS_RETRIEVAL_EXCEPTION);
		}
		LOGGER.info("getAllGroups : End ");
		return groups;
	}


	/**
	 * Controller method for the user to retrieve all cards in a group
	 *
	 * @return List<Card>
	 * @throws BookyException
	 */
	@ApiOperation(value = "Get all Cards in a Group")
	@GetMapping("/groups/{id}/cards")
	public List<Card> getAllCardsInGroup(@PathVariable("id") long groupId) throws BookyException {
		LOGGER.info("getAllCardsInGroup : Begin ");
		List<Card> cards;
		try {
			cards = groupService.findAllCardsInGroup(groupId);
		} catch (GroupServiceException exception) {
			throw new BookyException(exception);
		}catch(Exception exception) {
			throw new BookyException(Messages.GROUP_CARDS_RETRIEVAL_EXCEPTION);
		}
		LOGGER.info("getAllCardsInGroup : End ");
		return cards;
	}
}
