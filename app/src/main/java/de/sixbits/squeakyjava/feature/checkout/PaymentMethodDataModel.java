package de.sixbits.squeakyjava.feature.checkout;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentMethodDataModel implements Parcelable {
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

    protected PaymentMethodDataModel(Parcel in) {
        name = in.readString();
        logoUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(logoUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PaymentMethodDataModel> CREATOR = new Parcelable.Creator<PaymentMethodDataModel>() {
        @Override
        public PaymentMethodDataModel createFromParcel(Parcel in) {
            return new PaymentMethodDataModel(in);
        }

        @Override
        public PaymentMethodDataModel[] newArray(int size) {
            return new PaymentMethodDataModel[size];
        }
    };
}
