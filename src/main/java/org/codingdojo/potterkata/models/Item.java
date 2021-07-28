package org.codingdojo.potterkata.models;

import java.util.UUID;

public interface Item {
    UUID getId();
    String getName();
    Double getPrice();
}
