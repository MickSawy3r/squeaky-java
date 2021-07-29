package de.sixbits.squeakyjava.feature.checkout.presentation;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import de.sixbits.platform.core.BaseFragment;
import de.sixbits.platform.core.ConnectivityBroadcastReceiver;
import de.sixbits.platform.core.ConnectivityCallback;
import de.sixbits.platform.core.Failure;
import de.sixbits.squeakyjava.R;
import de.sixbits.squeakyjava.core.navigation.Navigator;
import de.sixbits.squeakyjava.databinding.FragmentCheckoutBinding;
import de.sixbits.squeakyjava.feature.checkout.domain.datamodel.PaymentMethodDataModel;

@AndroidEntryPoint
public class CheckoutFragment extends BaseFragment implements ConnectivityCallback {
    private static final String TAG = "CheckoutFragment";

    @Inject
    Navigator navigator;

    @Inject
    PaymentMethodListAdapter mPaymentMethodListAdapter;

    FragmentCheckoutBinding mUiBinding;
    CheckoutViewModel mCheckoutViewModel;
    ConnectivityBroadcastReceiver mConnectivityBroadcastReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCheckoutViewModel = new ViewModelProvider(this).get(CheckoutViewModel.class);
        mConnectivityBroadcastReceiver = new ConnectivityBroadcastReceiver(this);

        mCheckoutViewModel.data.observe(this, this::renderResult);
        mCheckoutViewModel.failure.observe(this, this::handleFailure);
        mCheckoutViewModel.loading.observe(this, this::handleLoading);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        mUiBinding = FragmentCheckoutBinding.inflate(
                inflater,
                container,
                false
        );

        setupUI();
        setupListeners();

        mCheckoutViewModel.getAvailablePaymentMethods();

        return mUiBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            Log.d(TAG, "onResume: Attach Broadcast To Activity");
            getActivity().registerReceiver(
                    mConnectivityBroadcastReceiver,
                    new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
            );
            getActivity().registerReceiver(
                    mConnectivityBroadcastReceiver,
                    new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED")
            );
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity() != null) {
            Log.d(TAG, "onPause: DeAttach Broadcast To Activity");
            getActivity().unregisterReceiver(mConnectivityBroadcastReceiver);
        }
    }

    void setupUI() {
        mUiBinding.rvPaymentMethods.setAdapter(mPaymentMethodListAdapter);
    }

    void setupListeners() {
        mPaymentMethodListAdapter.setOnClickListener(paymentMethodDataModel ->
                Log.d(TAG, "setupListeners: " + paymentMethodDataModel.getName())
        );
    }

    void renderResult(List<PaymentMethodDataModel> methods) {
        mPaymentMethodListAdapter.replaceItems(methods);

        mUiBinding.rvPaymentMethods.setVisibility(View.VISIBLE);
        mUiBinding.llNoInternet.setVisibility(View.GONE);
    }

    void handleFailure(Failure failure) {
        if (failure instanceof Failure.NetworkConnection) {
            mUiBinding.rvPaymentMethods.setVisibility(View.GONE);
            mUiBinding.llNoInternet.setVisibility(View.VISIBLE);
        } else {
            notify(R.string.failure_server_error);
        }
    }

    void handleLoading(@NonNull Boolean loading) {
        if (loading) {
            showProgress();
        } else {
            hideProgress();
        }
    }

    @Override
    public void onConnectionChange(Boolean connected) {
        mCheckoutViewModel.setIsNetworkAvailable(connected);
    }
}
