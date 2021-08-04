package de.sixbits.squeakyjava.feature.checkout;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.sixbits.platform.core.ViewHelpers;
import de.sixbits.squeakyjava.databinding.ItemPaymentMethodBinding;

public class PaymentMethodListAdapter extends RecyclerView.Adapter<PaymentMethodListAdapter.PaymentMethodItemVH> {
    private final List<PaymentMethodDataModel> mMethods;
    private Consumer<PaymentMethodDataModel> mOnClickListener;

    @Inject
    PaymentMethodListAdapter() {
        mMethods = new ArrayList<>();
    }

    @NotNull
    @Override
    public PaymentMethodItemVH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPaymentMethodBinding methodItem = ItemPaymentMethodBinding.inflate(layoutInflater, parent, false);
        return new PaymentMethodItemVH(methodItem);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PaymentMethodItemVH holder, int position) {
        holder.bind(mMethods.get(position), mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mMethods.size();
    }

    public void replaceItems(List<PaymentMethodDataModel> newMethods) {
        mMethods.clear();
        mMethods.addAll(newMethods);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new PaymentMethodDiffUtil(this.mMethods, newMethods));
        diffResult.dispatchUpdatesTo(this);
    }

    public void setOnClickListener(Consumer<PaymentMethodDataModel> onItemClick) {
        mOnClickListener = onItemClick;
    }

    static class PaymentMethodItemVH extends RecyclerView.ViewHolder {
        ItemPaymentMethodBinding itemBinding;

        PaymentMethodItemVH(ItemPaymentMethodBinding item) {
            super(item.getRoot());
            itemBinding = item;
        }

        void bind(PaymentMethodDataModel itemModel, Consumer<PaymentMethodDataModel> onClick) {
            itemBinding.tvMethodName.setText(itemModel.getName());
            ViewHelpers.loadFromUrl(itemBinding.ivLogo, itemModel.getLogoUrl());
            if (onClick != null) {
                itemView.setOnClickListener((v) -> onClick.accept(itemModel));
            }
        }
    }
}
