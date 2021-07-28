package org.codingdojo.potterkata.services;

import org.codingdojo.potterkata.models.User;
import org.codingdojo.potterkata.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultUserService implements UserService{
    private final UserRepository repository;

    public DefaultUserService(UserRepository repository) {
        this.repository = repository;
    }

    public User find(UUID id) {
        if (id == null) {
          return null;
        }
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    public User create(String name) throws Exception {
        if (name == null) {
            throw new Exception();
        }
        User user = new User();
        user.setName(name);
        return this.repository.save(user);
    }
}
