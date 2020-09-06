package com.booky.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtResponse {

	@JsonProperty(value="jwt_token")
	private String jwt_token;

	public JwtResponse(String jWT) {
		this.jwt_token = jWT;
	}

}
