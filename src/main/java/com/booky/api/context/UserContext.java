package com.booky.api.context;

import com.booky.api.model.User;
import com.booky.api.security.AuthenticatedUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContext {

    public static User getUserFromContext() {
        return ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication()).getAuthenticatedUser();
    }
}
