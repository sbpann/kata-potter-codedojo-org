package org.codingdojo.potterkata.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class operations {
    private final ArrayList<String> allowedCartUpdateOperation = new ArrayList<String>(Arrays.asList("ADD_ITEM", "REMOVE_ITEM"));

    public ArrayList<String> getAllowedCartUpdateOperation() {
        return allowedCartUpdateOperation;
    }
}
