package com.booky.api.controller;

import com.booky.api.constants.Messages;
import com.booky.api.exception.BookyException;
import com.booky.api.exception.CardQueueServiceException;
import com.booky.api.model.CardQueue;
import com.booky.api.service.CardQueueService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CardQueueController {

	private final Logger LOGGER = LoggerFactory.getLogger(CardQueueController.class);

	@Autowired
	private CardQueueService cardQueueService;


	/**
	 * Controller method for the user to retrieve all cards in queue for a group
	 *
	 * @return List<CardQueue>
	 * @throws BookyException
	 */
	@ApiOperation(value = "Get all Cards in queue for a Group")
	@GetMapping("/queue/group/{id}")
	public List<CardQueue> getAllCardsInGroupQueue(@PathVariable("id") long groupId) throws BookyException {
		LOGGER.info("getAllCardsInGroupQueue : Begin ");
		List<CardQueue> cards;
		try {
			cards = cardQueueService.getAllCardQueueOfGroup(groupId);
		} catch (CardQueueServiceException exception) {
			throw new BookyException(exception);
		}catch(Exception exception) {
			throw new BookyException(Messages.GET_ALL_QUEUE_CARD_EXCEPTION);
		}
		LOGGER.info("getAllCardsInGroupQueue : End ");
		return cards;
	}


	/**
	 * Controller method to approve a card in queue by Id
	 *
	 * @return
	 * @throws BookyException
	 */
	@ApiOperation(value = "Approve a Card")
	@PostMapping("/queue/{id}")
	public void approveCardFromQueue(@PathVariable("id") long id) throws BookyException {
		LOGGER.info("approveQueueCard : Begin ");
		try {

			cardQueueService.approveCardFromQueue(id);

		} catch (CardQueueServiceException exception) {
			LOGGER.error("Error while approving a Card from queue {}", exception);
			throw new BookyException(exception);
		} catch (Exception exception) {
			LOGGER.error("Error while approving a Card from queue {}", exception);
			throw new BookyException(Messages.DELETE_QUEUE_CARD_EXCEPTION);
		} finally {
			LOGGER.info("approveQueueCard : End ");
		}
	}

	/**
	 * Controller method to disapprove a card in queue by Id
	 *
	 * @return
	 * @throws BookyException
	 */
	@ApiOperation(value = "Disapprove a Card")
	@DeleteMapping("/queue/{id}")
	public void rejectCardFromQueue(@PathVariable("id") long id) throws BookyException {
		LOGGER.info("deleteQueueCard : Begin ");
		try {
			cardQueueService.rejectCardFromQueue(id);

		} catch (CardQueueServiceException exception) {
			LOGGER.error("Error while deleting a Card fom queue {}", exception);
			throw new BookyException(exception);
		} catch (Exception exception) {
			LOGGER.error("Error while deleting a Card from queue {}", exception);
			throw new BookyException(Messages.DELETE_QUEUE_CARD_EXCEPTION);
		} finally {
			LOGGER.info("deleteQueueCard : End ");
		}
	}
}
