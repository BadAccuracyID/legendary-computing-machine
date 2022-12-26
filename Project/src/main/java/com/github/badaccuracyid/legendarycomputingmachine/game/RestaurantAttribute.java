package com.github.badaccuracyid.legendarycomputingmachine.game;

public class RestaurantAttribute {

    private int maxCustomers = 5;
    private int patienceModifier = 0;
    private int customerAttractionModifier = 0;

    public int getMaxCustomers() {
        return maxCustomers;
    }

    public void setMaxCustomers(int maxCustomers) {
        this.maxCustomers = maxCustomers;
    }

    public int getPatienceModifier() {
        return patienceModifier;
    }

    public void setPatienceModifier(int patienceModifier) {
        this.patienceModifier = patienceModifier;
    }

    public int getCustomerAttractionModifier() {
        return customerAttractionModifier;
    }

    public void setCustomerAttractionModifier(int customerAttractionModifier) {
        this.customerAttractionModifier = customerAttractionModifier;
    }

}
