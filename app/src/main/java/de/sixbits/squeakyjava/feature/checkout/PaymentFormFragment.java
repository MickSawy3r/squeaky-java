package de.sixbits.squeakyjava.feature.checkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.Contract;

import dagger.hilt.android.AndroidEntryPoint;
import de.sixbits.platform.core.BaseFragment;
import de.sixbits.platform.helpers.FragmentHelper;
import de.sixbits.squeakyjava.R;
import de.sixbits.squeakyjava.databinding.FragmentPaymentBinding;

@AndroidEntryPoint
public class PaymentFormFragment extends BaseFragment {
    private static final String KEY_PAYMENT_METHOD = "PAYMENT_METHOD";

    @NonNull
    @Contract("_ -> new")
    public static PaymentFormFragment getInstance(PaymentMethodDataModel paymentMethod) {
        return FragmentHelper.getLoadedFragment(
                new PaymentFormFragment(),
                KEY_PAYMENT_METHOD,
                paymentMethod
        );
    }

    private PaymentMethodDataModel paymentMethodDataModel;
    private FragmentPaymentBinding uiBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            if (getArguments().getParcelable(KEY_PAYMENT_METHOD) != null) {
                paymentMethodDataModel = getArguments().getParcelable(KEY_PAYMENT_METHOD);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        uiBinding = FragmentPaymentBinding.inflate(
                inflater,
                container,
                false
        );

        renderResult();

        return uiBinding.getRoot();
    }

    void renderResult() {
        if (paymentMethodDataModel == null) {
            uiBinding.tvPayment.setText(R.string.err_empty_payment_method);
            return;
        }

        if (paymentMethodDataModel.getName() != null && !paymentMethodDataModel.getName().isEmpty()) {
            uiBinding.tvPayment.setText(paymentMethodDataModel.getName());
        }
    }
}
