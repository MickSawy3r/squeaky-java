package de.sixbits.squeakyjava.feature.checkout;

import static org.mockito.Mockito.when;

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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
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

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testGetAvailablePaymentMethods() {
        List<PaymentMethodDataModel> methods = new ArrayList<>();
        methods.add(new PaymentMethodDataModel("name", "url"));

        when(payoneerRepository.getAvailablePaymentMethods())
                .thenReturn(Single.just(methods));

        new GetAvailablePaymentMethods(
                threadExecutor,
                postExecutionThread,
                payoneerRepository
        ).buildUseCaseSingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((availableMethods) -> {
                    assert (availableMethods.size() == 1);
                    assert (availableMethods.get(0).getName().equals("name"));
                    assert (availableMethods.get(0).getLogoUrl().equals("url"));
                });
    }
}
