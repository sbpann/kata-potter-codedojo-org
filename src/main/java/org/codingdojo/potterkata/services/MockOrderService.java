package org.codingdojo.potterkata.services;

import org.codingdojo.potterkata.models.Cart;
import org.codingdojo.potterkata.models.Order;
import org.codingdojo.potterkata.models.User;

import java.util.UUID;
import java.util.function.Function;

public class MockOrderService implements OrderService{

    public Order find(UUID id) {
        return new Order();
    }

    public Order create(User user, Cart cart) throws Exception {
        Order order = new Order();
        order.setUser(user);
        order.setCart(cart);
        return order;
    }

    public Order checkout(Order order, Function<Cart, Double> discountFunction) {
        Cart cart = order.getCart();
        order.setTotalPrice(cart.getTotalPrice());
        if (discountFunction != null) {
            order.setDiscount(discountFunction.apply(cart));
        } else {
            order.setDiscount(0.);
        }
        order.setNetPrice();
        order.setCheckedOut(true);
        return order;
    }
}
