package de.sixbits.squeakyjava;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;
import de.sixbits.squeakyjava.core.di.BaseUrl;
import de.sixbits.squeakyjava.core.di.BaseUrlModule;

@Module
@TestInstallIn(
        components = SingletonComponent.class,
        replaces = BaseUrlModule.class
)
public class MockBaseUrlModule {
    @Provides
    @Singleton
    @BaseUrl
    public String provideBaseUrl() {
        return "http://localhost:8888/";
    }
}
