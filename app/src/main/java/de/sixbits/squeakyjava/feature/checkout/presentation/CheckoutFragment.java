package de.sixbits.squeakyjava.feature.checkout.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import de.sixbits.platform.core.BaseFragment;
import de.sixbits.squeakyjava.core.navigation.Navigator;
import de.sixbits.squeakyjava.databinding.FragmentCheckoutBinding;

@AndroidEntryPoint
public class CheckoutFragment extends BaseFragment {

    @Inject
    Navigator navigator;

    FragmentCheckoutBinding uiBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        uiBinding = FragmentCheckoutBinding.inflate(
                inflater,
                container,
                false
        );

        setupUI();

        return uiBinding.getRoot();
    }

    void setupUI() {
    }
}
