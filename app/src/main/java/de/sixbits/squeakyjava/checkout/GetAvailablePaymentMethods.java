package de.sixbits.squeakyjava.checkout;

import java.util.List;

import javax.inject.Inject;

import de.sixbits.reactive.executor.PostExecutionThread;
import de.sixbits.reactive.executor.ThreadExecutor;
import de.sixbits.reactive.interactor.SingleUseCase;
import io.reactivex.rxjava3.core.Single;

public class GetAvailablePaymentMethods extends SingleUseCase<List<PaymentMethodDataModel>, Void> {
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
    public Single<List<PaymentMethodDataModel>> buildUseCaseSingle(Void... params) {
        return mPayoneerRepository.getAvailablePaymentMethods();
    }
}
