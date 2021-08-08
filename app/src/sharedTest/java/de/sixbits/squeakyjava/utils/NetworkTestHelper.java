package de.sixbits.squeakyjava.utils;

import org.jetbrains.annotations.NotNull;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.ResponseBody;

public class NetworkTestHelper {
    @NotNull
    public static Interceptor provideMockInterceptor(String fileName) {
        return chain -> {
            String payload = FileTestHelpers.loadJson(fileName);

            return chain.proceed(chain.request())
                    .newBuilder()
                    .code(200)
                    .protocol(Protocol.HTTP_2)
                    .message(payload)
                    .body(ResponseBody.create(payload, MediaType.parse("application/json")))
                    .addHeader("content-type", "application/json")
                    .build();
        };
    }
}
