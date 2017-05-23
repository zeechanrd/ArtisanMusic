package com.howshea.artisanmusic.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by haipo
 * on 2016/10/13.
 *
 */

public class HttpRequest {

    private static final String BASE_URL = "https://bing.ioliu.cn/v1/";

    private static final int DEFAULT_TIMEOUT = 5;

    private ApiService mApiService;

    private HttpRequest() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Retrofit mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    public static HttpRequest getInstance() {
        return HttpRequestHolder.sInstance;
    }

    //静态内部类单例模式
    private static class HttpRequestHolder {
        private static final HttpRequest sInstance = new HttpRequest();
    }


//    public Observable<HomePage> getHomePage(){
//        return mApiService.getHome()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

}
