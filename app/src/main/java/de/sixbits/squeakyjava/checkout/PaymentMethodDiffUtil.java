package de.sixbits.squeakyjava.checkout;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class PaymentMethodDiffUtil extends DiffUtil.Callback {

    List<PaymentMethodDataModel> mOldList;
    List<PaymentMethodDataModel> mNewList;

    public PaymentMethodDiffUtil(List<PaymentMethodDataModel> oldList, List<PaymentMethodDataModel> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewList.get(oldItemPosition).getId().equals(mOldList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        PaymentMethodDataModel oldMethod = mOldList.get(oldItemPosition);
        PaymentMethodDataModel newMethod = mNewList.get(newItemPosition);

        return oldMethod.getName().equals(newMethod.getName()) &&
                oldMethod.getLogoUrl().equals(newMethod.getLogoUrl());
    }
}
