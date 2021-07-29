package de.sixbits.squeakyjava.feature.checkout.domain.usecase;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import de.sixbits.reactive.executor.PostExecutionThread;
import de.sixbits.reactive.executor.ThreadExecutor;
import de.sixbits.reactive.interactor.SingleUseCase;
import de.sixbits.squeakyjava.feature.checkout.data.PayoneerRepository;
import de.sixbits.squeakyjava.feature.checkout.domain.datamodel.PaymentMethodDataModel;
import io.reactivex.rxjava3.core.Single;

public class GetAvailablePaymentMethods extends SingleUseCase<List<PaymentMethodDataModel>, Void> {
    private static final String TAG = "GetAvailablePaymentMeth";
    private final PayoneerRepository mPayoneerRepository;

    @Inject
    GetAvailablePaymentMethods(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            PayoneerRepository payoneerRepository
    ) {
        super(threadExecutor, postExecutionThread);
        mPayoneerRepository = payoneerRepository;
    }

    @Override
    public Single<List<PaymentMethodDataModel>> buildUseCaseSingle(Void unused) {
        Log.d(TAG, "buildUseCaseSingle: " + (getPostExecutionThreadScheduler() != null) + "," + (getThreadExecutorScheduler() != null));
        return mPayoneerRepository.getAvailablePaymentMethods();
    }
}
