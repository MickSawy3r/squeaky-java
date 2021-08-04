package de.sixbits.squeakyjava.checkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;
import de.sixbits.platform.core.BaseFragment;
import de.sixbits.platform.helpers.FragmentHelper;
import de.sixbits.squeakyjava.R;
import de.sixbits.squeakyjava.databinding.FragmentPaymentBinding;

@AndroidEntryPoint
public class PaymentFormFragment extends BaseFragment {
    private static final String KEY_PAYMENT_METHOD = "PAYMENT_METHOD";

    public static PaymentFormFragment getInstance(PaymentMethodDataModel paymentMethod) {
        return FragmentHelper.getLoadedFragment(
                new PaymentFormFragment(),
                KEY_PAYMENT_METHOD,
                paymentMethod
        );
    }

    private PaymentMethodDataModel paymentMethodDataModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            paymentMethodDataModel = getArguments().getParcelable(KEY_PAYMENT_METHOD);
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        FragmentPaymentBinding uiBinding = FragmentPaymentBinding.inflate(
                inflater,
                container,
                false
        );

        if (paymentMethodDataModel.getName() != null && !paymentMethodDataModel.getName().isEmpty()) {
            uiBinding.tvPayment.setText(paymentMethodDataModel.getName());
        } else {
            uiBinding.tvPayment.setText(R.string.err_empty_payment_method);
        }

        return uiBinding.getRoot();
    }
}
