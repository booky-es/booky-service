package com.booky.api.dao;

import java.math.BigInteger;
import java.util.List;

import com.booky.api.exception.UserDAOException;
import com.booky.api.model.User;

public interface UserDAO {

	List<User> findMatchingUsers(String match);
	
	User login(User user) throws UserDAOException;

	List<User> findUsersByIds(List<BigInteger> ids);

	User findUserByEmail(String email);


}
