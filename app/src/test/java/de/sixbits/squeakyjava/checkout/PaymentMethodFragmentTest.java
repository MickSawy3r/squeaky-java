package de.sixbits.squeakyjava.checkout;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import android.content.Context;
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

import java.util.List;

import dagger.hilt.android.testing.HiltAndroidTest;
import de.sixbits.platform.core.Failure;
import de.sixbits.squeakyjava.R;
import de.sixbits.squeakyjava.RobolectricTest;
import de.sixbits.squeakyjava.factory.PaymentMethodsResponseFactory;
import de.sixbits.squeakyjava.helper.HiltTestHelpers;

@HiltAndroidTest
public class PaymentMethodFragmentTest extends RobolectricTest {
    Context context;

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
        context = getApplicationContext();

        when(paymentMethodViewModel.getDataLiveData()).thenReturn(dataLiveData);
        when(paymentMethodViewModel.getFailureLiveData()).thenReturn(failureLiveData);
        when(paymentMethodViewModel.getLoadingLiveData()).thenReturn(loadingLiveData);
    }

    @Test
    public void testInitialState_shouldSeeNoInternetError() {
        PaymentMethodFragment fragment = PaymentMethodFragment.getInstance();
        fragment.injectViewModel(paymentMethodViewModel);

        HiltTestHelpers.launchFragmentInHiltContainer(fragment);

        assertThat(fragment.getView()).isNotNull();

        LinearLayout noInternetLayout = fragment.getView().findViewById(R.id.ll_no_internet);
        assertThat(noInternetLayout.getVisibility()).isEqualTo(View.VISIBLE);
    }

    @Test
    public void testDataState_shouldShowDataList() {
        PaymentMethodFragment fragment = PaymentMethodFragment.getInstance();
        fragment.injectViewModel(paymentMethodViewModel);

        HiltTestHelpers.launchFragmentInHiltContainer(fragment);

        assertThat(fragment.getView()).isNotNull();
        dataLiveData.postValue(PaymentMethodsResponseFactory.getPaymentMethodList());

        assertThat(fragment.getView()).isNotNull();
        RecyclerView rvPaymentList = fragment.getView().findViewById(R.id.rv_payment_methods);
        assertThat(rvPaymentList.getVisibility()).isEqualTo(View.VISIBLE);

        assertThat(rvPaymentList.getAdapter()).isNotNull();
        assertThat(rvPaymentList.getAdapter().getItemCount()).isEqualTo(1);
    }
}
