package de.sixbits.squeakyjava.feature.checkout;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.LinearLayout;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.testing.HiltAndroidTest;
import de.sixbits.platform.core.Failure;
import de.sixbits.squeakyjava.R;
import de.sixbits.squeakyjava.RobolectricTest;
import de.sixbits.squeakyjava.utils.HiltTestHelpers;
import de.sixbits.squeakyjava.utils.PaymentMethodsResponseFactory;

@HiltAndroidTest
public class PaymentMethodFragmentTest extends RobolectricTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    MutableLiveData<List<PaymentMethodDataModel>> dataLiveData = new MutableLiveData<>();
    MutableLiveData<Failure> failureLiveData = new MutableLiveData<>();

    @Mock
    PaymentMethodViewModel paymentMethodViewModel;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        when(paymentMethodViewModel.getDataLiveData()).thenReturn(dataLiveData);
        when(paymentMethodViewModel.getFailureLiveData()).thenReturn(failureLiveData);
        when(paymentMethodViewModel.getLoadingLiveData()).thenReturn(loadingLiveData);
    }

    @Test
    public void testErrorState_shouldSeeNoInternetError() {
        // Given I open the Payment Method Screen
        PaymentMethodFragment fragment = PaymentMethodFragment.getInstance();
        fragment.injectViewModel(paymentMethodViewModel);
        HiltTestHelpers.launchFragmentInHiltContainer(fragment);

        // And the screen is visible
        assertThat(fragment.getView()).isNotNull();
        RecyclerView rvPaymentList = fragment.getView().findViewById(R.id.rv_payment_methods);
        LinearLayout noInternetLayout = fragment.getView().findViewById(R.id.ll_no_internet);

        // When I have no internet and request the methods
        failureLiveData.postValue(new CheckoutFailure.ConnectivityError());

        // Then I should see a no internet error message
        assertThat(fragment.getView()).isNotNull();
        assertThat(noInternetLayout.getVisibility()).isEqualTo(View.VISIBLE);

        // And The data list should be invisible
        assertThat(rvPaymentList.getVisibility()).isEqualTo(View.GONE);
    }

    @Test
    public void testDataState_shouldShowDataList() {
        // Given I open the Payment Method Screen
        PaymentMethodFragment fragment = PaymentMethodFragment.getInstance();
        fragment.injectViewModel(paymentMethodViewModel);
        HiltTestHelpers.launchFragmentInHiltContainer(fragment);

        // When I get data
        dataLiveData.postValue(PaymentMethodsResponseFactory.getPaymentMethodList());

        // And the screen is visible
        assertThat(fragment.getView()).isNotNull();
        RecyclerView rvPaymentList = fragment.getView().findViewById(R.id.rv_payment_methods);
        LinearLayout noInternetLayout = fragment.getView().findViewById(R.id.ll_no_internet);

        // Then I should see the data list
        assertThat(rvPaymentList.getVisibility()).isEqualTo(View.VISIBLE);

        // And the data list should contain the same item numbers as the result
        assertThat(rvPaymentList.getAdapter()).isNotNull();
        assertThat(rvPaymentList.getAdapter().getItemCount())
                .isEqualTo(PaymentMethodsResponseFactory.getPaymentMethodList().size());

        // And No error should show
        assertThat(noInternetLayout.getVisibility()).isEqualTo(View.GONE);
    }

    @Test
    public void testDataState_withNoMethods_shouldEmptyListMessage() {
        // Given I open the Payment Method Screen
        PaymentMethodFragment fragment = PaymentMethodFragment.getInstance();
        fragment.injectViewModel(paymentMethodViewModel);
        HiltTestHelpers.launchFragmentInHiltContainer(fragment);

        // When I get data
        dataLiveData.postValue(new ArrayList<>());

        // And the screen is visible
        assertThat(fragment.getView()).isNotNull();
        RecyclerView rvPaymentList = fragment.getView().findViewById(R.id.rv_payment_methods);
        LinearLayout noInternetLayout = fragment.getView().findViewById(R.id.ll_no_internet);
        LinearLayout emptyListLayout = fragment.getView().findViewById(R.id.ll_empty_list);

        // Then I should not see the data list
        assertThat(rvPaymentList.getVisibility()).isEqualTo(View.GONE);

        // And I should see and empty list page
        assertThat(emptyListLayout.getVisibility()).isEqualTo(View.VISIBLE);

        // And No error should show
        assertThat(noInternetLayout.getVisibility()).isEqualTo(View.GONE);
    }
}
