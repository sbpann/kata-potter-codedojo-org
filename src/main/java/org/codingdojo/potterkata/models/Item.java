package org.codingdojo.potterkata.models;

import java.util.UUID;

public interface Item {
    UUID getID();
    String getName();
    Double getPrice();
}
