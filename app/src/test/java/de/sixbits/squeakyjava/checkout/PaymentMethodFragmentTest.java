package de.sixbits.squeakyjava.checkout;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import dagger.hilt.android.testing.HiltAndroidTest;
import de.sixbits.platform.core.Failure;
import de.sixbits.squeakyjava.R;
import de.sixbits.squeakyjava.RobolectricTest;
import de.sixbits.squeakyjava.TestHelpers;

@HiltAndroidTest
public class PaymentMethodFragmentTest extends RobolectricTest {
    Context context;

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
    public void testInitialPaymentMethodFragment_shouldSeeNoInternetError() {
        PaymentMethodFragment fragment = PaymentMethodFragment.getInstance();
        fragment.injectViewModel(paymentMethodViewModel);

        TestHelpers.launchFragmentInHiltContainer(fragment);

        assertThat(fragment.getView()).isNotNull();

        LinearLayout noInternetLayout = fragment.getView().findViewById(R.id.ll_no_internet);
        assertThat(noInternetLayout.getVisibility()).isEqualTo(View.VISIBLE);
    }
}
