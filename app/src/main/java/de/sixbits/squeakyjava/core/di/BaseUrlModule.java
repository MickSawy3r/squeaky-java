package de.sixbits.squeakyjava.core.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import de.sixbits.squeakyjava.BuildConfig;

@Module
@InstallIn(SingletonComponent.class)
public class BaseUrlModule {

    @Provides
    @Singleton
    @BaseUrl
    String provideBaseUrl() {
        return BuildConfig.BASE_URL;
    }
}
