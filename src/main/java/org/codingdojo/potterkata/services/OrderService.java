package org.codingdojo.potterkata.services;

import org.codingdojo.potterkata.models.Cart;
import org.codingdojo.potterkata.models.Order;
import org.codingdojo.potterkata.models.User;

import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.function.Function;

public interface OrderService {
    Order create(@NotNull User user, @NotNull Cart cart) throws Exception;
    Order find(@NotNull UUID id);
    Order checkout(@NotNull Order order, Function<Cart, Double> discountFunction) throws Exception;
}
