package org.codingdojo.potterkata.models;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name="\"order\"")
public class Order extends AbstractPersistable<UUID> {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne
    private Cart cart;
    @ManyToOne
    private User user;
    private Double totalPrice;
    private Double discount;
    private Double netPrice;
    @NotNull
    private Boolean checkedOut;

    public UUID getID() {
        return this.id;
    }

    public Cart getCart() {
        return this.cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    public void setNetPrice() {
        this.netPrice = this.getTotalPrice() - this.getDiscount();
    }
    public Double getNetPrice() {
        return this.netPrice;
    }
    public void setCheckedOut(Boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        return jsonObject.put("id", id)
                .put("user", user.getName())
                .put("cart", cart.getID())
                .put("items", cart.getItems().stream().map(cartItem -> {
                    JSONObject item = new JSONObject();
                    item.put("id", cartItem.getID().toString())
                        .put("name", cartItem.getNumber())
                        .put("number", cartItem.getNumber())
                        .put("pricePerUnit", cartItem.getPricePerUnit());
                    return item.toString();
                }))
                .put("totalPrice", totalPrice)
                .put("discount", discount)
                .put("netPrice",netPrice)
                .put("checkedOut", checkedOut);
    }
}
