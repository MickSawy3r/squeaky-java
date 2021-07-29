package de.sixbits.squeakyjava.feature.checkout.domain.datamodel;

public class PaymentMethodDataModel {
    private final String name;
    private final String imageUrl;

    public PaymentMethodDataModel(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
