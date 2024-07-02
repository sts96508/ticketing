package com.jira.ticketing.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("user with id " + id + " not found");
    }

    public UserNotFoundException(String username) {
        super("user with username " + username + " not found");
    }

}
