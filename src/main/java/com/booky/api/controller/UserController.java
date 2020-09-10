package com.booky.api.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.booky.api.constants.Messages;
import com.booky.api.exception.BookyException;
import com.booky.api.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booky.api.model.JwtResponse;
import com.booky.api.security.GoogleTokenService;
import com.booky.api.security.JwtTokenProvider;
import com.booky.api.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private GoogleTokenService googleTokenService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	/**
	 * Controller method for the user to login and create new JWT token after
	 * validating the google token received
	 *
	 * @return User
	 * @throws BookyException
	 */
	@ApiOperation(value = "User Login")
	@GetMapping("/login")
	public JwtResponse loginUser(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws BookyException {
		LOGGER.info("loginUser : Begin ");
		String googleToken = request.getHeader("Google-Token");
		String JWT = "";
		if (googleToken != null && StringUtils.hasText(googleToken)) {
			try {
				GoogleIdToken.Payload payload = googleTokenService.verifyGoogleToken(googleToken);
				BigInteger userId = new BigInteger(payload.getSubject());
				String email = payload.getEmail();
				String lastName = (String) payload.get("family_name");
				String firstName = (String) payload.get("given_name");
				User user = new User(userId, email, lastName, firstName);
				user = userService.loginUser(user);
				JWT = jwtTokenProvider.generateJwtToken(response, user);
			} catch (Exception ex) {
				LOGGER.error("loginUser : Exception " + ex.getMessage());
				throw new BookyException(Messages.USER_LOGIN_EXCEPTION);
			}
		}
		if(JWT == null || JWT.isEmpty()) {
			throw new BookyException("Not able to login!!!");
		}
		LOGGER.info("loginUser : End ");
		return new JwtResponse(JWT);

	}

	/**
	 * Controller method to verify authorization
	 * 
	 * @return true
	 */
	@ApiOperation(value = "Authorization Check")
	@GetMapping("/authorize")
	public boolean authorizeEndPoint() {
		LOGGER.info("authorizeEndPoint : success");
		return true;
	}
}
