package org.codingdojo.potterkata.models;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Orders extends AbstractPersistable<UUID> {

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
    public void setCheckedOut(Boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        return jsonObject.put("id", id)
                .put("cart", cart.getId())
                .put("user", user.getName())
                .put("totalPrice", totalPrice)
                .put("discount", discount)
                .put("netPrice",netPrice)
                .put("checkedOut", checkedOut);
    }
}
