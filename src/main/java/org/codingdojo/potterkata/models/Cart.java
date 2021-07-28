package org.codingdojo.potterkata.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.*;

@Entity
public class Cart extends AbstractPersistable<UUID> {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne
    private User user;
    @OneToMany
    private List<CartItem> items;

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getItems() {
        return this.items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        Double total = 0.0;
        for (CartItem cartItem: this.getItems()) {
            total = total + (cartItem.getNumber() * cartItem.getPricePerUnit());
        }
        return total;
    }
}
