package me.jesuscodes.bagel;

import android.content.Context;

import okhttp3.Interceptor;

public class Bagel {

    static Bagel instance;
    private final BagelController controller;
    public Context context;

    public Bagel(Context context) {
        this(context, null);
    }

    public Bagel(Context context, BabelConfiguration configuration) {
        instance = this;
        this.context = context;
        controller = new BagelController(
                configuration == null ?
                        BabelConfiguration.defaultConfiguration()
                        : configuration);
    }

    public Interceptor buildInterceptor() {
        BagelOkHttpInterceptor i = new BagelOkHttpInterceptor();
        controller.interceptors.add(i);
        return i;
    }
}
