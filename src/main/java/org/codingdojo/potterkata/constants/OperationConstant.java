package org.codingdojo.potterkata.constants;

import java.util.ArrayList;
import java.util.Arrays;

public class OperationConstant {
    private static final String cartAddItemOperation = "ADD_ITEM";
    private static final String cartRemoveItemOperation = "REMOVE_ITEM";
    private static final ArrayList<String> allowedCartUpdateOperation = new ArrayList<>(Arrays.asList(cartAddItemOperation, cartRemoveItemOperation));

    public static ArrayList<String> getAllowedCartUpdateOperation() {
        return allowedCartUpdateOperation;
    }

    public static String getCartAddItemOperation() {
        return cartAddItemOperation;
    }

    public static String getCartRemoveItemOperation() {
        return cartRemoveItemOperation;
    }

}
