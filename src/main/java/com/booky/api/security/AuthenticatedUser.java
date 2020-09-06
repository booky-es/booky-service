package com.booky.api.security;

import java.util.Collection;

import com.booky.api.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


public class AuthenticatedUser implements Authentication {

	private User user;

	public AuthenticatedUser(User user) {
		this.user = user;
	}
	
	public User getAuthenticatedUser() {
		return this.user;	
	}
	
	@Override
	public boolean equals(Object another) {
		return false;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {		
		return this.user != null;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		
	}

}
