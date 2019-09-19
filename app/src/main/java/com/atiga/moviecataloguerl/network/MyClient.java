package com.atiga.moviecataloguerl.network;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClient {
    private static RestApi service;

    public static RestApi getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (service == null) {
            Retrofit client = new Retrofit.Builder()
                    .client(getUnsafeOkHttpClient())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            service = client.create(RestApi.class);
        }
        return service;
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            return new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            Request original = chain.request();
                            Request.Builder builder = original.newBuilder();
                            Request request = builder.build();

                            return chain.proceed(request);
                        }
                    }).connectTimeout(180, TimeUnit.SECONDS)
                    .readTimeout(180, TimeUnit.SECONDS)
                    .writeTimeout(180, TimeUnit.SECONDS).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
