package org.codingdojo.potterkata.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class CartItem extends AbstractPersistable<UUID> {

    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private UUID SKU;
    @NotNull
    private Integer number;
    @NotNull
    private Double pricePerUnit;

    public UUID getID() {
        return this.id;
    }

    public UUID getSKU() {
        return this.SKU;
    }

    public void setSKU(UUID id) {
        this.SKU = id;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPricePerUnit() {
        return this.pricePerUnit;
    }

    public void srtPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
