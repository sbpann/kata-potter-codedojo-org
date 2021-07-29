package org.codingdojo.potterkata.services;

import org.codingdojo.potterkata.models.Cart;
import org.codingdojo.potterkata.models.Order;
import org.codingdojo.potterkata.models.User;
import org.codingdojo.potterkata.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
public class DefaultOrderService implements OrderService {

    private final OrderRepository repository;
    private final DefaultCartService cartService;
    private final DefaultUserService userService;
    public DefaultOrderService(
            OrderRepository orderRepository,
            DefaultCartService cartService,
            DefaultUserService userService) {
        this.repository = orderRepository;
        this.cartService = cartService;
        this.userService = userService;
    }

    public Order find(@NotNull UUID id) {
        Optional<Order> order = this.repository.findById(id);
        if (order.isEmpty()) {
            return null;
        }
        return order.get();
    }

    public Order create(@NotNull User user, @NotNull Cart cart) throws Exception{
        if (user == null) {
            return this.repository.findByCart(cart);
        }
        User targetUser = this.userService.find(user.getID());
        if (targetUser == null) {
            throw new Exception();
        }

        Cart targetCart = this.cartService.find(cart.getID());
        if (targetCart == null) {
            throw new Exception();
        }

        Order order = new Order();
        order.setUser(targetUser);
        order.setCart(targetCart);
        order.setCheckedOut(false);
        return this.repository.save(order);
    }

    public Order checkout(@NotNull Order order, Function<Cart, Double> discountFunction) throws Exception{
        if (this.find(order.getID()) == null) {
            throw new  Exception();
        }
        Cart cart = order.getCart();
        order.setTotalPrice(cart.getTotalPrice());
        if (discountFunction != null) {
            order.setDiscount(discountFunction.apply(cart));
        } else {
            order.setDiscount(0.);
        }
        order.setNetPrice();
        order.setCheckedOut(true);
        this.cartService.detachUser(cart);
        order.setCart(cart);
        return this.repository.save(order);
    }
}
