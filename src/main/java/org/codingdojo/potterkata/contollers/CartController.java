package org.codingdojo.potterkata.contollers;

import org.codingdojo.potterkata.constants.OperationConstants;
import org.codingdojo.potterkata.models.Book;
import org.codingdojo.potterkata.models.Cart;
import org.codingdojo.potterkata.models.Order;
import org.codingdojo.potterkata.models.User;
import org.codingdojo.potterkata.requests.CartUpdateRequest;
import org.codingdojo.potterkata.requests.CartCreateRequest;
import org.codingdojo.potterkata.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.UUID;

@RestController
public class CartController {
    private final DefaultCartService cartService;
    private final DefaultUserService userService;
    private final DefaultBookService bookService;
    private final DefaultOrderService orderService;


    public CartController(
            DefaultCartService cartService,
            DefaultUserService userService,
            DefaultBookService bookService,
            DefaultOrderService orderService) {
        this.cartService = cartService;
        this.userService = userService;
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @PostMapping("/cart")
    Cart create(@RequestBody CartCreateRequest request) throws HttpClientErrorException {
        User user = this.userService.find(UUID.fromString(request.userID()));
        if (user == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return this.cartService.create(user);
    }

    @PutMapping("/cart/{id}")
    Cart update(@PathVariable String id, @RequestBody CartUpdateRequest request) throws HttpClientErrorException {
        Cart cart = this.cartService.find(UUID.fromString(id));
        if (cart == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        if (!OperationConstants.getAllowedCartUpdateOperation().contains(request.operation())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        Book book = this.bookService.find(UUID.fromString(request.itemID()));
        if (book == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        if (request.operation().equals(OperationConstants.getCartAddItemOperation())) {
            return this.cartService.addItem(book, cart);
        }

        if (request.operation().equals(OperationConstants.getCartRemoveItemOperation())) {
            return this.cartService.removeItem(book, cart);
        }

        return cart;
    }

    @PostMapping("/cart/{id}/checkout")
    Order checkout(@PathVariable String id) throws HttpClientErrorException {
        Cart cart = this.cartService.find(UUID.fromString(id));
        if (cart == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        Order order;
        try {
            order = this.orderService.create(cart.getUser(), cart);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            //TODO: Add discount function from https://codingdojo.org/kata/Potter
            return orderService.checkout(order, null);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
