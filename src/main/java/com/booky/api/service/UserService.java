package com.booky.api.service;


import com.booky.api.model.User;
import com.booky.api.exception.UserServiceException;

import java.util.List;

public interface UserService {

	User signUp(User user) throws UserServiceException;

	List<User> findUsers(String match);
	
	User loginUser(User user) throws UserServiceException;
}
