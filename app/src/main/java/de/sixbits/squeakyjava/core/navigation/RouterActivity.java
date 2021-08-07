package de.sixbits.squeakyjava.core.navigation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import de.sixbits.squeakyjava.core.navigation.Navigator;

@AndroidEntryPoint
public class RouterActivity extends AppCompatActivity {
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.showPaymentMethod(this);
    }
}
