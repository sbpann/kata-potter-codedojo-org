package org.codingdojo.potterkata.services;

import org.codingdojo.potterkata.models.Cart;
import org.codingdojo.potterkata.models.Item;
import org.codingdojo.potterkata.models.User;

import java.util.UUID;

public interface CartService {
    Cart create(User user);
    Cart find(UUID id);
    Cart findByUser(User user);
    Cart addItem(Item item, Cart cart);
    Cart removeItem(Item item, Cart cart);
}
