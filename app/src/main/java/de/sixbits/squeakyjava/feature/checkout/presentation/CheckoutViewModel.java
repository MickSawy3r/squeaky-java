package de.sixbits.squeakyjava.feature.checkout.presentation;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import de.sixbits.platform.core.BaseViewModel;
import de.sixbits.platform.core.Failure;
import de.sixbits.squeakyjava.feature.checkout.domain.datamodel.PaymentMethodDataModel;
import de.sixbits.squeakyjava.feature.checkout.domain.usecase.GetAvailablePaymentMethods;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

@HiltViewModel
public class CheckoutViewModel extends BaseViewModel {
    private static final String TAG = "CheckoutViewModel";
    private final GetAvailablePaymentMethods mGetAvailablePaymentMethods;

    private final MutableLiveData<List<PaymentMethodDataModel>> _data = new MutableLiveData<>();
    public LiveData<List<PaymentMethodDataModel>> data = _data;

    private Boolean isConnected = false;
    private Boolean isRequestOnQue = false;

    @Inject
    CheckoutViewModel(GetAvailablePaymentMethods getAvailablePaymentMethods) {
        mGetAvailablePaymentMethods = getAvailablePaymentMethods;
    }

    void getAvailablePaymentMethods() {
        setLoading(true);
        if (isConnected) {
            mGetAvailablePaymentMethods.execute(new PaymentMethodsObserver(), null);
        } else {
            isRequestOnQue = true;
            handleFailure(new Failure.NetworkConnection());
        }
    }

    void setIsNetworkAvailable(Boolean connected) {
        isConnected = connected;

        if (isRequestOnQue) {
            isRequestOnQue = false;
            getAvailablePaymentMethods();
        }
    }

    private class PaymentMethodsObserver extends DisposableSingleObserver<List<PaymentMethodDataModel>> {

        @Override
        public void onSuccess(@NonNull List<PaymentMethodDataModel> paymentMethodDataModels) {
            Log.d(TAG, "onSuccess: ");
            _data.postValue(paymentMethodDataModels);
            setLoading(false);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.d(TAG, "onError: " + e.getMessage() + "\n\n" + Arrays.toString(e.getStackTrace()));
            handleFailure(new Failure.NetworkConnection());
            setLoading(false);
        }
    }
}
