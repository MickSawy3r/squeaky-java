package de.sixbits.squeakyjava.feature.checkout.presentation;

import android.content.Context;
import android.content.Intent;

import dagger.hilt.android.AndroidEntryPoint;
import de.sixbits.platform.core.BaseFragment;
import de.sixbits.platform.core.ContainerActivity;

@AndroidEntryPoint
public class CheckoutActivity extends ContainerActivity {

    static public Intent callingIntent(Context context) {
        return new Intent(context, CheckoutActivity.class);
    }

    @Override
    public BaseFragment fragment() {
        return new CheckoutFragment();
    }
}
