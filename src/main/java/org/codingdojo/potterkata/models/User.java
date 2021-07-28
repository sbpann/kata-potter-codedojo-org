package org.codingdojo.potterkata.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class User extends AbstractPersistable<UUID> {
    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private String name;

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
