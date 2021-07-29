package org.codingdojo.potterkata.services;

import org.codingdojo.potterkata.models.Cart;
import org.codingdojo.potterkata.models.CartItem;
import org.codingdojo.potterkata.models.Item;
import org.codingdojo.potterkata.models.User;
import org.codingdojo.potterkata.repositories.CartItemRepository;
import org.codingdojo.potterkata.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultCartService implements CartService{

    private final CartRepository repository;
    private final CartItemRepository itemRepository;

    public DefaultCartService(CartRepository repository, CartItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    public Cart create(User user) {
        if (findByUser(user) != null) {
            return findByUser(user);
        }
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setItems(new ArrayList<>());
        return this.repository.save(cart);
    }

    public Cart find(UUID id) {
        Optional<Cart> cart = this.repository.findById(id);
        if (cart.isEmpty()) {
            return null;
        }
        return cart.get();
    }

    public Cart findByUser(User user) {
        Optional<Cart> cart = this.repository.findByUser(user);
        if (cart.isEmpty()) {
            return null;
        }
        return cart.get();
    }

    public Cart addItem(Item item, Cart cart) {
        Optional<CartItem> addedCartItem = cart.getItems().stream().filter(cartItem -> cartItem.getSKU().equals(item.getID())).findFirst();
        if (addedCartItem.isEmpty()) {
            CartItem cartItem = new CartItem();
            cartItem.setSKU(item.getID());
            cartItem.setName(item.getName());
            cartItem.setPricePerUnit(item.getPrice());
            cartItem.setNumber(1);
            this.itemRepository.save(cartItem);
            cart.getItems().add(cartItem);
            return this.repository.save(cart);
        }

        CartItem cartItem = addedCartItem.get();
        cartItem.setNumber(cartItem.getNumber() + 1);
        this.itemRepository.save(cartItem);
        return this.find(cart.getID());
    }


    public Cart removeItem(Item item, Cart cart) {
        Optional<CartItem> addedCartItem = cart.getItems().stream().filter(cartItem -> cartItem.getSKU().equals(item.getID())).findFirst();
        if (addedCartItem.isEmpty()) {
            return cart;
        }

        if (addedCartItem.get().getNumber().equals(1)) {
            this.itemRepository.delete(addedCartItem.get());
            cart.getItems().remove(addedCartItem.get());
            return this.repository.save(cart);
        }

        CartItem cartItem = addedCartItem.get();
        cartItem.setNumber(cartItem.getNumber() - 1);
        this.itemRepository.delete(cartItem);
        return this.find(cart.getID());
    }
}
