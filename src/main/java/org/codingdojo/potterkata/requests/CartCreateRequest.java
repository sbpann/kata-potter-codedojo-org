package org.codingdojo.potterkata.requests;

import javax.validation.constraints.NotNull;

public record CartCreateRequest(@NotNull String userID) {

}
