package de.sixbits.squeakyjava.feature.checkout.presentation;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import de.sixbits.platform.core.BaseViewModel;
import de.sixbits.platform.core.Failure;
import de.sixbits.squeakyjava.feature.checkout.domain.datamodel.PaymentMethodDataModel;
import de.sixbits.squeakyjava.feature.checkout.domain.usecase.GetAvailablePaymentMethods;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class CheckoutViewModel extends BaseViewModel {
    private static final String TAG = "CheckoutViewModel";
    private final GetAvailablePaymentMethods mGetAvailablePaymentMethods;

    private final MutableLiveData<List<PaymentMethodDataModel>> _data = new MutableLiveData<>();
    public LiveData<List<PaymentMethodDataModel>> data = _data;

    @Inject
    CheckoutViewModel(GetAvailablePaymentMethods getAvailablePaymentMethods) {
        mGetAvailablePaymentMethods = getAvailablePaymentMethods;
    }

    void getAvailablePaymentMethods() {
        mGetAvailablePaymentMethods.execute(new PaymentMethodsObserver());
    }

    private class PaymentMethodsObserver extends DisposableSingleObserver<List<PaymentMethodDataModel>> {

        @Override
        public void onSuccess(@NonNull List<PaymentMethodDataModel> paymentMethodDataModels) {
            _data.postValue(paymentMethodDataModels);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.d(TAG, "onError: " + e.getMessage() + "\n\n" + Arrays.toString(e.getStackTrace()));
            handleFailure(new Failure.NetworkConnection());
        }
    }
}
