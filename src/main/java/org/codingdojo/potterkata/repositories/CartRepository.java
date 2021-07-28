package org.codingdojo.potterkata.repositories;
import org.codingdojo.potterkata.models.Cart;
import javax.validation.constraints.NotNull;

import org.codingdojo.potterkata.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUser(User user);
}
