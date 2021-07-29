package de.sixbits.squeakyjava.feature.checkout.domain.datamodel;

public class PaymentMethodDataModel {
    private final String name;
    private final String logoUrl;

    public PaymentMethodDataModel(String name, String logoUrl) {
        this.name = name;
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }
}
