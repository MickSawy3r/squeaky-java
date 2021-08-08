package de.sixbits.squeakyjava.feature.checkout;

import static com.google.common.truth.Truth.assertThat;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import de.sixbits.platform.core.Failure;
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class PaymentMethodViewModelTest {
    @Mock
    GetAvailablePaymentMethods getAvailablePaymentMethods;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        RxJavaPlugins.setIoSchedulerHandler((v) -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler((v) -> Schedulers.trampoline());
    }

    @Test
    public void testInitialViewModelState() {
        // Given I just opened the app
        PaymentMethodViewModel viewModel = new PaymentMethodViewModel(
                getAvailablePaymentMethods
        );

        // When the app is opened
        // Then that app should not be loading
        assertThat(viewModel.getLoadingLiveData().getValue()).isNotNull();
        assertThat(viewModel.getLoadingLiveData().getValue()).isFalse();

        // There should be no data
        assertThat(viewModel.getDataLiveData().getValue()).isNull();

        // And there should be no failures
        assertThat(viewModel.getFailureLiveData().getValue()).isNull();
    }

    @Test
    public void testRequestMethods() {
        // Given I open the app
        PaymentMethodViewModel viewModel = new PaymentMethodViewModel(
                getAvailablePaymentMethods
        );

        // And I have internet connection
        viewModel.setIsNetworkAvailable(true);

        // When I request payment methods
        viewModel.getAvailablePaymentMethods();

        // Then I should request the payment method list
        Mockito.verify(getAvailablePaymentMethods, Mockito.times(1)).execute(
                Mockito.any()
        );

        // And I should get a loading indication
        assertThat(viewModel.getLoadingLiveData().getValue()).isTrue();

        // And failures should be null
        assertThat(viewModel.getFailureLiveData().getValue()).isNull();
    }

    @Test
    public void testNoInternet_shouldProduceNetworkFailure() {
        // Given I open the app
        PaymentMethodViewModel viewModel = new PaymentMethodViewModel(
                getAvailablePaymentMethods
        );

        // And I don't have internet connection
        viewModel.setIsNetworkAvailable(false);

        // When I request payment methods
        viewModel.getAvailablePaymentMethods();

        // Then I should not request the payment method list
        Mockito.verify(getAvailablePaymentMethods, Mockito.times(0)).execute(
                Mockito.any()
        );

        // And I should not get a loading indication
        assertThat(viewModel.getLoadingLiveData().getValue()).isFalse();

        // And failures should be NetworkFailure
        assertThat(viewModel.getFailureLiveData().getValue())
                .isInstanceOf(Failure.ConnectivityError.class);
    }

    @Test
    public void testQueRequests() {
        // Given I open the app
        PaymentMethodViewModel viewModel = new PaymentMethodViewModel(
                getAvailablePaymentMethods
        );

        // And I don't have internet connection
        viewModel.setIsNetworkAvailable(false);

        // When I request payment methods
        viewModel.getAvailablePaymentMethods();

        // Then I should not request the payment method list
        Mockito.verify(getAvailablePaymentMethods, Mockito.times(0)).execute(
                Mockito.any()
        );

        // And failures should be NetworkFailure
        assertThat(viewModel.getFailureLiveData().getValue())
                .isInstanceOf(Failure.ConnectivityError.class);

        // When I get internet connectivity
        viewModel.setIsNetworkAvailable(true);

        // Then I should request payment methods automatically
        Mockito.verify(getAvailablePaymentMethods, Mockito.times(1))
                .execute(Mockito.any());
    }
}
