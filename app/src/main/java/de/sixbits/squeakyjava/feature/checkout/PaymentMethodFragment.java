package de.sixbits.squeakyjava.feature.checkout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import de.sixbits.platform.core.BaseFragment;
import de.sixbits.platform.core.ConnectivityBroadcastReceiver;
import de.sixbits.platform.core.ConnectivityCallback;
import de.sixbits.platform.core.Failure;
import de.sixbits.platform.helpers.FragmentHelper;
import de.sixbits.squeakyjava.EspressoIdlingResource;
import de.sixbits.squeakyjava.R;
import de.sixbits.squeakyjava.core.navigation.Navigator;
import de.sixbits.squeakyjava.databinding.FragmentCheckoutBinding;

@AndroidEntryPoint
public class PaymentMethodFragment extends BaseFragment implements ConnectivityCallback {
    private static final String TAG = "PaymentMethodFragment";

    @NonNull
    @Contract(" -> new")
    public static PaymentMethodFragment getInstance() {
        return new PaymentMethodFragment();
    }

    @Inject
    Navigator navigator;

    @Inject
    PaymentMethodListAdapter mPaymentMethodListAdapter;

    FragmentCheckoutBinding mUiBinding;
    PaymentMethodViewModel mPaymentMethodViewModel;
    ConnectivityBroadcastReceiver mConnectivityBroadcastReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mPaymentMethodViewModel == null) {
            mPaymentMethodViewModel = new ViewModelProvider(this).get(PaymentMethodViewModel.class);
        }
        if (mConnectivityBroadcastReceiver == null) {
            mConnectivityBroadcastReceiver = new ConnectivityBroadcastReceiver(this);
        }

        mPaymentMethodViewModel.getDataLiveData().observe(this, this::renderResult);
        mPaymentMethodViewModel.getFailureLiveData().observe(this, this::handleFailure);
        mPaymentMethodViewModel.getLoadingLiveData().observe(this, this::handleLoading);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        mUiBinding = FragmentCheckoutBinding.inflate(
                inflater,
                container,
                false
        );

        setupUI();
        setupListeners();

        mPaymentMethodViewModel.getAvailablePaymentMethods();

        return mUiBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            FragmentHelper.attachConnectivityBroadcastReceiver(
                    getActivity(),
                    mConnectivityBroadcastReceiver
            );
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity() != null) {
            FragmentHelper.deAttachConnectivityBroadcastReceiver(
                    getActivity(),
                    mConnectivityBroadcastReceiver
            );
        }
    }

    @Override
    public void onConnectionChange(Boolean connected) {
        mPaymentMethodViewModel.setIsNetworkAvailable(connected);
    }

    private void setupUI() {
        mUiBinding.rvPaymentMethods.setAdapter(mPaymentMethodListAdapter);
        mUiBinding.rvPaymentMethods.getViewTreeObserver()
                .addOnGlobalLayoutListener(() -> {
                    Log.d(TAG, "setupUI: addOnGlobalLayoutListener");
                    EspressoIdlingResource.decrement();
                });
    }

    private void setupListeners() {
        mPaymentMethodListAdapter.setOnClickListener(paymentMethodDataModel -> {
                    if (getContext() != null) {
                        navigator.showPaymentForm(
                                getContext(), paymentMethodDataModel
                        );
                    } else {
                        notify(R.string.payment_method_clicked);
                    }
                }
        );
    }

    private void renderResult(@NonNull List<PaymentMethodDataModel> methods) {
        Log.d(TAG, "renderResult: " + methods.size());
        if (methods.size() > 0) {
            EspressoIdlingResource.increment();
            mPaymentMethodListAdapter.replaceItems(methods);
            Log.d(TAG, "renderResult: " + mPaymentMethodListAdapter.getItemCount());
            showDataViews();
        } else {
            showEmptyListViews();
        }
    }

    private void handleFailure(Failure failure) {
        Log.d(TAG, "handleFailure: " + failure.toString());
        if (failure instanceof Failure.BadRequestError) {
            notifyWithAction(
                    R.string.network_error,
                    R.string.retry,
                    () -> mPaymentMethodViewModel.getAvailablePaymentMethods()
            );
        } else if (failure instanceof Failure.ConnectivityError) {
            showNoInternetViews();
        } else if (failure instanceof Failure.ServerError) {
            notify(R.string.failure_server_error);
        } else {
            notify(failure.toString());
        }
    }

    private void handleLoading(@NonNull Boolean loading) {
        Log.d(TAG, "handleLoading: " + loading);
        if (loading) {
            showProgress();
        } else {
            hideProgress();
        }
    }

    private void showEmptyListViews() {
        Log.d(TAG, "showEmptyListViews: ");
        mUiBinding.rvPaymentMethods.setVisibility(View.GONE);
        mUiBinding.llEmptyList.setVisibility(View.VISIBLE);
        mUiBinding.llNoInternet.setVisibility(View.GONE);
    }

    private void showDataViews() {
        Log.d(TAG, "showDataViews: ");
        mUiBinding.rvPaymentMethods.setVisibility(View.VISIBLE);
        mUiBinding.llEmptyList.setVisibility(View.GONE);
        mUiBinding.llNoInternet.setVisibility(View.GONE);
    }

    private void showNoInternetViews() {
        Log.d(TAG, "showNoInternetViews: ");
        mUiBinding.rvPaymentMethods.setVisibility(View.GONE);
        mUiBinding.llEmptyList.setVisibility(View.GONE);
        mUiBinding.llNoInternet.setVisibility(View.VISIBLE);
    }

    @VisibleForTesting
    void injectViewModel(PaymentMethodViewModel testViewModel) {
        this.mPaymentMethodViewModel = testViewModel;
    }
}
