package de.sixbits.squeakyjava.feature.checkout.domain.usecase;

import java.util.List;

import de.sixbits.reactive.executor.PostExecutionThread;
import de.sixbits.reactive.executor.ThreadExecutor;
import de.sixbits.reactive.interactor.SingleUseCase;
import de.sixbits.squeakyjava.feature.checkout.data.PayoneerRepository;
import de.sixbits.squeakyjava.feature.checkout.domain.datamodel.PaymentMethodDataModel;
import io.reactivex.rxjava3.core.Single;

public class GetAvailablePaymentMethods extends SingleUseCase<List<PaymentMethodDataModel>, Void> {

    private final PayoneerRepository mPayoneerRepository;

    GetAvailablePaymentMethods(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread,
            PayoneerRepository payoneerRepository
    ) {
        super(threadExecutor, postExecutionThread);
        mPayoneerRepository = payoneerRepository;
    }

    @Override
    public Single<List<PaymentMethodDataModel>> buildUseCaseSingle() {
        return mPayoneerRepository.getAvailablePaymentMethods();
    }
}
