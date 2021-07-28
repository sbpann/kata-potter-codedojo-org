package org.codingdojo.potterkata.services;

import org.codingdojo.potterkata.models.User;
import org.codingdojo.potterkata.repositories.UserRepository;

public interface UserService {
    User findByID(String id);
}
