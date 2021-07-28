package org.codingdojo.potterkata.models;

public class CartItem {
    public String SKU;
    public Integer number;
    public Double pricePerUnit;

    public CartItem(String SKU, Double pricePerUnit) {
        this.SKU = SKU;
        this.number = 1;
        this.pricePerUnit = pricePerUnit;
    }

    public String getSKU() {
        return this.SKU;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }
}
