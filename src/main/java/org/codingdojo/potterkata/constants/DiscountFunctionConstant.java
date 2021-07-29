package org.codingdojo.potterkata.constants;

import org.codingdojo.potterkata.models.Cart;
import org.codingdojo.potterkata.models.CartItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class DiscountFunctionConstant {
    public static final Function<Cart, Double> potterKataDiscountFunction = (targetCart) -> {
        List<CartItem> targetItems = targetCart.getItems();
        if (targetCart.getItems().size() < 2) {
            return 0.;
        }

        targetItems.sort((CartItem a, CartItem b) -> {
            if (a.getNumber().equals(b.getNumber())) {
                return 0;
            }
            return b.getNumber() > a.getNumber() ? 1 : -1;
        });

        Integer maxBasket = targetItems.get(0).getNumber();
        List<Integer> baskets = new ArrayList<>();
        for (int i = 0; i < maxBasket; i++) {
            baskets.add(1);
        }


        HashMap<Integer, Integer> discountTable = new HashMap<>();
        discountTable.put(2, 5);
        discountTable.put(3, 10);
        discountTable.put(4, 20);
        discountTable.put(5, 25);

        for (int i = 1; i < targetItems.size(); i++) {
            CartItem currentSKU = targetItems.get(i);
            Integer currentSKUNumber = currentSKU.getNumber();
            if (currentSKUNumber >= maxBasket) {
                for (int j = 0; j < baskets.size(); j++) {
                    baskets.set(j, baskets.get(j) + 1);
                    currentSKUNumber = currentSKUNumber - 1;
                }
            }

            while (currentSKUNumber > 0) {
                List<Double> discountedHere = new ArrayList<>();
                for (int j = 0; j < baskets.size(); j++) {
                    List<Integer> calculator = new ArrayList<>(baskets);
                    calculator.set(j, calculator.get(j) + 1);
                    Double discounted = 0.;
                    for (Integer valueAt: calculator) {
                        if (valueAt < 2) {
                            continue;
                        }
                        Integer discountPercent;
                        if (valueAt > 5) {
                            discountPercent = discountTable.get(5);
                        } else {
                            discountPercent = discountTable.get(valueAt);
                        }
                        discounted = discounted + ((discountPercent.doubleValue() / 100.) * 8. * (valueAt <= 5 ? valueAt : 5));
                    }
                    discountedHere.add(discounted);
                }
                Double maximumDiscounted = Collections.max(discountedHere);
                Integer whereToAdd = discountedHere.indexOf(maximumDiscounted);
                baskets.set(whereToAdd, baskets.get(whereToAdd) + 1);
                currentSKUNumber = currentSKUNumber - 1;
            }
        }

        Double discount = 0.;
        for (Integer group : baskets) {
            if (group < 2) {
                continue;
            }
            Integer discountPercent;
            if (group > 5) {
                discountPercent = discountTable.get(5);
            } else {
                discountPercent = discountTable.get(group);
            }

            discount = discount + ((discountPercent.doubleValue() / 100.) * 8. * (group <= 5 ? group : 5));
        }

        return discount;
    };
}
