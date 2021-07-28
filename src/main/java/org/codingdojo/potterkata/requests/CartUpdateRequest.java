package org.codingdojo.potterkata.requests;

import javax.validation.constraints.NotNull;

public record CartAddItemRequest(@NotNull String itemID) {
    public String getItemID() {
        return this.itemID;
    }
}
