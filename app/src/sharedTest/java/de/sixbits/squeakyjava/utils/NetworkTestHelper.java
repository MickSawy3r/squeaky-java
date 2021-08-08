package de.sixbits.squeakyjava.utils;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.ResponseBody;

public class NetworkTestHelper {
    @NotNull
    public static Interceptor provideMockInterceptor(String fileName, Integer statusCode) {
        return chain -> {
            String payload = FileTestHelpers.loadJson(fileName);

            return chain.proceed(chain.request())
                    .newBuilder()
                    .code(statusCode)
                    .protocol(Protocol.HTTP_2)
                    .message(payload)
                    .body(ResponseBody.create(payload, MediaType.parse("application/json")))
                    .addHeader("content-type", "application/json")
                    .build();
        };
    }

    @NonNull
    @Contract(pure = true)
    public static Interceptor provideMockInterceptor(String fileName) {
        return provideMockInterceptor(fileName, 200);
    }
}
