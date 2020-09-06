package com.booky.api.dao;

import java.util.List;

import com.booky.api.exception.UserDAOException;
import com.booky.api.model.User;

public interface UserDAO {

	List<User> findAll() throws UserDAOException;
	
	User login(User user) throws UserDAOException;
}
