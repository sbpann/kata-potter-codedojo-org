package org.codingdojo.potterkata.requests;

import javax.validation.constraints.NotNull;

public record CartUpdateRequest(@NotNull String itemID, @NotNull String operation) {

}
