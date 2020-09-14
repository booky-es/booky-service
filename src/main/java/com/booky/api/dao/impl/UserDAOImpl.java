package com.booky.api.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.booky.api.dao.UserDAO;
import com.booky.api.exception.UserDAOException;
import com.booky.api.model.User;
import com.booky.api.repository.UserRepository;

@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findMatchingUsers(String match) {
		List<User> users_1 = userRepository.findByFirstNameStartsWith(match);
		List<User> users_2 = userRepository.findByLastNameStartsWith(match);
		for(User user : users_2) {
			if(!users_1.contains(user))
				users_1.add(user);
		}
		return users_1;
	}

	@Override
	public User login(User user) throws UserDAOException {
		User userLoggedIn = null;
		try {			
			if(!userRepository.existsById(user.getUserId())) {
				userLoggedIn = (User) userRepository.save(user);
			}
			userLoggedIn = user;
		} catch (Exception exception) {
			throw new UserDAOException(exception);
		}
		return userLoggedIn;
	}

	@Override
	public List<User> findUsersByIds(List<BigInteger> ids) {
		return userRepository.findByUserIdIn(ids);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email).get(0);
	}

}
