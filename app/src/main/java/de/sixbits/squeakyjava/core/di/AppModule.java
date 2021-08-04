package de.sixbits.squeakyjava.core.di;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import de.sixbits.platform.core.NetworkHandler;
import de.sixbits.squeakyjava.BuildConfig;
import de.sixbits.squeakyjava.feature.checkout.PayoneerRepository;
import de.sixbits.squeakyjava.feature.checkout.PayoneerApi;
import de.sixbits.squeakyjava.feature.checkout.PayoneerRemoteDataSource;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .client(createClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @NotNull
    private OkHttpClient createClient()  {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClientBuilder
                    .addInterceptor(loggingInterceptor);
        }
        return okHttpClientBuilder.build();
    }


    @Provides
    @Singleton
    NetworkHandler provideNetworkHandler(@ApplicationContext Context context) {
        return new NetworkHandler(context);
    }

    @Provides
    @Singleton
    PayoneerApi providePayoneerApi(Retrofit retrofit) {
        return retrofit.create(PayoneerApi.class);
    }

    @Provides
    @Singleton
    PayoneerRemoteDataSource providePayoneerRemoteDataSource(PayoneerApi payoneerApi) {
        return new PayoneerRemoteDataSource(payoneerApi);
    }

    @Provides
    @Singleton
    PayoneerRepository provideMoviesRepository(
            PayoneerRemoteDataSource dataSource,
            NetworkHandler networkHandler
    ) {
        return new PayoneerRepository(dataSource, networkHandler);
    }
}
