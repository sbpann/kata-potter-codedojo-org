package org.codingdojo.potterkata.services;

import org.codingdojo.potterkata.models.User;

import java.util.UUID;

public interface UserService {
    User find(UUID id);
    User create(String name) throws Exception;
}
