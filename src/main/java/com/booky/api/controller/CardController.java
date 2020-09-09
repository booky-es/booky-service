package com.booky.api.controller;

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
		Card newCArd = new Card();
		newCArd.setGroupId(card.getGroupId());
		newCArd.setTitle(card.getTitle());
		newCArd.setUrl(card.getUrl());
		try {
			cardService.createCard(newCArd);
		} catch (CardServiceException exception) {
			throw new BookyException(exception);
		} catch (Exception exception) {
			throw new BookyException(Messages.CARD_CREATION_EXCEPTION);
		}
		card.setId(newCArd.getId());
		card.setShortUrl("shorturl/1");
		LOGGER.info("createCard : End ");
		return card;
	}


	/**
	 * Controller method to get a card by Id
	 *
	 * @return Group
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
}
