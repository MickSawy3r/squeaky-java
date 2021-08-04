package de.sixbits.squeakyjava;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.internal.bytecode.InstrumentationConfiguration;
import org.robolectric.util.inject.Injector;

import javax.annotation.Nonnull;

public class RoboCustomRunner  extends RobolectricTestRunner {
    public RoboCustomRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    protected RoboCustomRunner(Class<?> testClass, Injector injector) throws InitializationError {
        super(testClass, injector);
    }

    @Nonnull
    @Override
    protected InstrumentationConfiguration createClassLoaderConfig(FrameworkMethod method) {
        InstrumentationConfiguration config = super.createClassLoaderConfig(method);
        InstrumentationConfiguration.Builder builder = new InstrumentationConfiguration.Builder(config);

        builder.doNotInstrumentPackage("androidx.fragment");

        return builder.build();
    }
}
