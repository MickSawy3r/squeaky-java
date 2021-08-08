package de.sixbits.squeakyjava.feature.checkout;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import de.sixbits.platform.core.ViewHelpers;
import de.sixbits.squeakyjava.databinding.ItemPaymentMethodBinding;

public class PaymentMethodListAdapter extends ListAdapter<
        PaymentMethodDataModel,
        PaymentMethodListAdapter.PaymentMethodItemVH> {

    private Consumer<PaymentMethodDataModel> mOnClickListener;

    public static final DiffUtil.ItemCallback<PaymentMethodDataModel> DIFF_CALLBACK = new PaymentMethodDiffUtil();

    @Inject
    PaymentMethodListAdapter() {
        super(PaymentMethodListAdapter.DIFF_CALLBACK);
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
        holder.bind(getCurrentList().get(position), mOnClickListener);
        ViewHelpers.loadFromUrl(
                holder.itemBinding.ivLogo,
                getCurrentList().get(position).getLogoUrl()
        );
    }

    @Override
    public int getItemCount() {
        return getCurrentList().size();
    }

    public void replaceItems(@NonNull List<PaymentMethodDataModel> newMethods) {
        submitList(newMethods);
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
            if (onClick != null) {
                itemView.setOnClickListener((v) -> onClick.accept(itemModel));
            }
        }
    }
}
