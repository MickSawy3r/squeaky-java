package de.sixbits.squeakyjava.checkout;

import static org.mockito.Mockito.when;
import static com.google.common.truth.Truth.assertWithMessage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import de.sixbits.reactive.executor.PostExecutionThread;
import de.sixbits.reactive.executor.ThreadExecutor;
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class GetAvailablePaymentMethodsTest {

    @Mock
    PostExecutionThread postExecutionThread;

    @Mock
    ThreadExecutor threadExecutor;

    @Mock
    PayoneerRepository payoneerRepository;

    @Before
    public void setup() {
        RxJavaPlugins.setIoSchedulerHandler((v) -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler((v) -> Schedulers.trampoline());
    }

    @Test
    public void testGetAvailablePaymentMethods() {
        // Given the server contains 1 payment method
        PaymentMethodDataModel paymentMethodItem = new PaymentMethodDataModel("1", "name", "url");
        List<PaymentMethodDataModel> methods = new ArrayList<>();
        methods.add(paymentMethodItem);

        when(payoneerRepository.getAvailablePaymentMethods())
                .thenReturn(Single.just(methods));

        // When I request available methods
        List<PaymentMethodDataModel> availableMethods = new GetAvailablePaymentMethods(
                threadExecutor,
                postExecutionThread,
                payoneerRepository
        ).buildUseCaseSingle()
                .test()
                .values()
                .get(0);

        // Then I should get 1 available payment method
        assertWithMessage("Checking the available methods size")
                .that(availableMethods.size())
                .isEqualTo(1);

        // And I should get a payment method name
        assertWithMessage("Checking the first payment methods name")
                .that(availableMethods.get(0).getName())
                .isEqualTo(paymentMethodItem.getName());

        // And I should get a payment method url
        assertWithMessage("Checking the first payment methods logo url")
                .that(availableMethods.get(0).getLogoUrl())
                .isEqualTo("url");
    }
}
