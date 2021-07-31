package de.sixbits.squeakyjava.feature.checkout.presentation;

import android.content.Context;
import android.content.Intent;

import dagger.hilt.android.AndroidEntryPoint;
import de.sixbits.platform.core.BaseFragment;
import de.sixbits.platform.core.ContainerActivity;
import de.sixbits.squeakyjava.feature.checkout.domain.datamodel.PaymentMethodDataModel;

@AndroidEntryPoint
public class PaymentFormActivity extends ContainerActivity {

    public static final String KEY_DATA = "KEY_DATA";

    static public Intent callingIntent(Context context, PaymentMethodDataModel paymentMethod) {
        Intent intent = new Intent(context, PaymentFormActivity.class);
        intent.putExtra(KEY_DATA, paymentMethod);
        return intent;
    }

    @Override
    public BaseFragment fragment() {
        return PaymentFormFragment.getInstance(getIntent().getParcelableExtra(KEY_DATA));
    }
}
