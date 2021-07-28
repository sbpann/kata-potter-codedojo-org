package org.codingdojo.potterkata.repositories;

import org.codingdojo.potterkata.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
}
