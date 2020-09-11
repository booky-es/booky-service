package com.booky.api.controller;

import com.booky.api.constants.CardStatus;
import com.booky.api.constants.Messages;
import com.booky.api.exception.BookyException;
import com.booky.api.exception.CardServiceException;
import com.booky.api.model.Card;
import com.booky.api.model.CreateCard;
import com.booky.api.service.CardService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CardController {

	private final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

	@Autowired
	private CardService cardService;


	/**
	 * Controller method for the user to create a new card
	 *
	 * @return Card
	 * @throws BookyException
	 */
	@ApiOperation(value = "Create Card")
	@PostMapping("/cards")
	public CreateCard createCard(@RequestBody CreateCard card) throws BookyException {
		LOGGER.info("createCard : Begin ");
		try {
			card = cardService.createCard(card);
		} catch (CardServiceException exception) {
			throw new BookyException(exception);
		} catch (Exception exception) {
			throw new BookyException(Messages.CARD_CREATION_EXCEPTION);
		}
		LOGGER.info("createCard : End ");
		return card;
	}


	/**
	 * Controller method to get a card by Id
	 *
	 * @return Card
	 * @throws BookyException
	 */
	@ApiOperation(value = "Retrieve a Card")
	@GetMapping("/cards/{id}")
	public Card retrieveCard(@PathVariable("id") long id) throws BookyException {
		LOGGER.info("retrieveCard : Begin ");
		Card card;
		try {
			card = cardService.findOneCard(id);
		} catch (CardServiceException exception) {
			LOGGER.error("Error while retrieving a Card {}", exception);
			throw new BookyException(Messages.CARD_RETRIEVAL_EXCEPTION);
		}
		LOGGER.info("retrieveCard : End ");
		return card;
	}

	/**
	 * Controller method to update a card by Id
	 *
	 * @return Card
	 * @throws BookyException
	 */
	@ApiOperation(value = "Update a Card")
	@PostMapping("/cards/{id}")
	public CreateCard updateCard(@PathVariable("id") long id, @RequestBody CreateCard card) throws BookyException {
		LOGGER.info("updateCard : Begin ");
		try {
			card = cardService.updateCard(card);
		} catch (CardServiceException exception) {
			LOGGER.error("Error while updating a Card {}", exception);
			throw new BookyException(Messages.CARD_UPDATE_EXCEPTION);
		}
		LOGGER.info("updateCard : End ");
		return card;
	}

	/**
	 * Controller method to delete a card by Id
	 *
	 * @return CardStatus
	 * @throws BookyException
	 */
	@ApiOperation(value = "Delete a Card")
	@DeleteMapping("/cards/{id}")
	public CardStatus deleteCard(@PathVariable("id") long id) throws BookyException {
		LOGGER.info("deleteCard : Begin ");
		try {
				return cardService.deleteCard(id);

		} catch (CardServiceException exception) {
			LOGGER.error("Error while deleting a Card {}", exception);
			throw new BookyException(exception);
		} catch (Exception exception) {
			LOGGER.error("Error while deleting a Card {}", exception);
			throw new BookyException(Messages.CARD_DELETE_EXCEPTION);
		} finally {
			LOGGER.info("deleteCard : End ");
		}
	}
}
