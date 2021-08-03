package de.sixbits.squeakyjava.checkout;

import android.content.Context;
import android.content.Intent;

import dagger.hilt.android.AndroidEntryPoint;
import de.sixbits.platform.core.BaseFragment;
import de.sixbits.platform.core.ContainerActivity;

@AndroidEntryPoint
public class PaymentMethodActivity extends ContainerActivity {

    static public Intent callingIntent(Context context) {
        return new Intent(context, PaymentMethodActivity.class);
    }

    @Override
    public BaseFragment fragment() {
        return new PaymentMethodFragment();
    }
}
