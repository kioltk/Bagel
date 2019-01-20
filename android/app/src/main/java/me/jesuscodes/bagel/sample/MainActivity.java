package me.jesuscodes.bagel.sample;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;

import me.jesuscodes.bagel.Bagel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {

    private OkHttpClient client;
    private boolean pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bagel bagel = new Bagel(this);
        client = new OkHttpClient.Builder()
                .addInterceptor(bagel.buildInterceptor())
                .build();


    }

    @Override
    protected void onResume() {
        super.onResume();
        makeCall();
        pause = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause = true;
    }

    private void makeCall() {
        client.newCall(new Request.Builder()
                .url("https://cat-fact.herokuapp.com/facts/5887e1d85c873e0011036889")
                .build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                getWindow().getDecorView().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pause) {
                            makeCall();
                        }
                    }
                }, 3000);
            }
        });
    }
}
