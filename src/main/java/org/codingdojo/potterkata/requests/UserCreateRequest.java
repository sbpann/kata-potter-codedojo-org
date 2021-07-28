package org.codingdojo.potterkata.requests;

import javax.validation.constraints.NotNull;

public record UserCreateRequest(@NotNull String name) {

}
