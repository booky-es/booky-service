package com.booky.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booky.api.dao.UserDAO;
import com.booky.api.exception.UserServiceException;
import com.booky.api.model.User;
import com.booky.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public User signUp(User user) throws UserServiceException {
		return null;
	}

	@Override
	public List<User> findUsers(String match) {
		return userDAO.findMatchingUsers(match);
	}

	@Override
	public User loginUser(User user) throws UserServiceException {
		User userloggedIn;
		try {
			userloggedIn = userDAO.login(user);
		} catch (Exception exception) {
			throw new UserServiceException(exception);
		}
		return userloggedIn;
	}

}
