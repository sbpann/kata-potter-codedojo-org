package org.codingdojo.potterkata;

import org.codingdojo.potterkata.constants.DiscountFunctionConstant;
import org.codingdojo.potterkata.models.Cart;
import org.codingdojo.potterkata.models.CartItem;
import org.codingdojo.potterkata.models.Order;
import org.codingdojo.potterkata.models.User;
import org.codingdojo.potterkata.services.MockOrderService;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountFunctionTest {

    List<CartItem> bookGenerator(
            Integer book1,
            Integer book2,
            Integer book3,
            Integer book4,
            Integer book5
            ) {
        List<CartItem> items = new ArrayList<>();
        if (book1 > 0) {
            CartItem item1 = new CartItem();
            item1.setSKU(UUID.fromString("b566f350-3f2e-4762-b157-3b4393c8136f"));
            item1.setName("Potter#1");
            item1.setNumber(book1);
            item1.setPricePerUnit(8.);
            items.add(item1);
        }

        if (book2 > 0) {
            CartItem item2 = new CartItem();
            item2.setSKU(UUID.fromString("ff265b9b-58f5-4b71-b0c1-128f3dc5c355"));
            item2.setName("Potter#2");
            item2.setNumber(book2);
            item2.setPricePerUnit(8.);
            items.add(item2);
        }

        if (book3 > 0) {
            CartItem item3 = new CartItem();
            item3.setSKU(UUID.fromString("d49e05f5-3d42-4b04-9748-0c6139b3c19d"));
            item3.setName("Potter#3");
            item3.setNumber(book3);
            item3.setPricePerUnit(8.);
            items.add(item3);
        }

        if (book4 > 0) {
            CartItem item4 = new CartItem();
            item4.setSKU(UUID.fromString("d713a563-95bb-49db-978f-727ba5f52617"));
            item4.setName("Potter#4");
            item4.setNumber(book4);
            item4.setPricePerUnit(8.);
            items.add(item4);

        }
        if (book5 > 0) {
            CartItem item5 = new CartItem();
            item5.setSKU(UUID.fromString("2b256193-287d-40ec-8587-adb0d8d852ab"));
            item5.setName("Potter#5");
            item5.setNumber(book5);
            item5.setPricePerUnit(8.);
            items.add(item5);
        }
        return items;
    }
    void checkout(List<CartItem> items, Double expectedPrice) throws Exception {
        Cart cart = new Cart();
        User user = new User();
        user.setName("John");
        cart.setUser(user);

        cart.setItems(items);

        Order order = new Order();
        order.setCart(cart);
        order.setUser(user);

        MockOrderService mockOrderService = new MockOrderService();
        order = mockOrderService.checkout(order, DiscountFunctionConstant.potterKataDiscountFunction);
        assertEquals(expectedPrice, order.getNetPrice());
    }

    @Test
    void checkoutTestExample(){
        assertDoesNotThrow(
                () -> checkout(bookGenerator(2,2,2,1,1),51.20));
    }

    @Test
    void checkoutTestBasics(){
        assertDoesNotThrow(
                () -> checkout(bookGenerator(0,0,0,0,0),0.));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(0,1,0,0,0),8.));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(0,0,1,0,0),8.));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(0,0,0,1,0),8.));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(0,0,0,0,1),8.));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(0,3,0,0,0),8.*3));
    }

    @Test
    void checkoutTestSimpleDiscounts(){
        assertDoesNotThrow(
                () -> checkout(bookGenerator(1,1,0,0,0),8*2*0.95));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(1,0,1,0,1),8*3*0.9));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(1,1,1,0,1),8*4*0.8));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(1,1,1,1,1),8*5*0.75));
    }

    @Test
    void checkoutTestSeveralDiscounts(){
        assertDoesNotThrow(
                () -> checkout(bookGenerator(2,1,0,0,0),8 + (8 * 2 * 0.95)));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(2,2,0,0,0),2 * (8 * 2 * 0.95)));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(2,1,2,1,0),(8 * 4 * 0.8) + (8 * 2 * 0.95)));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(1,2,1,1,1),8 + (8 * 5 * 0.75)));
    }

    @Test
    void checkoutTestEdgeCases(){
        assertDoesNotThrow(
                () -> checkout(bookGenerator(2,2,2,1,1),2 * (8 * 4 * 0.8)));
        assertDoesNotThrow(
                () -> checkout(bookGenerator(5,5,4,5,4),3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8)));
    }
}