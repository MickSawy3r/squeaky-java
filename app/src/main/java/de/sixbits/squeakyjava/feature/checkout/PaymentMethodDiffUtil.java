package de.sixbits.squeakyjava.feature.checkout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class PaymentMethodDiffUtil extends DiffUtil.ItemCallback<PaymentMethodDataModel> {

    @Override
    public boolean areItemsTheSame(@NonNull PaymentMethodDataModel oldItem, @NonNull PaymentMethodDataModel newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull PaymentMethodDataModel oldItem, @NonNull PaymentMethodDataModel newItem) {
        return oldItem.getLogoUrl().equals(newItem.getLogoUrl()) &&
                oldItem.getName().equals(newItem.getName());
    }
}
