package com.example.junctionhack;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Parser{
  TextView resField;
  protected void onCreate(Bundle savedInstanceState) {
    //this.getReq();
  }

  public void getReq(){
    OkHttpClient client = new OkHttpClient();
    //String url = "https://junction.dev.qoco.fi/api/ping";
    String key = "jmdSHjy6WPaXwoR75E6mJ1ImhxKPRJb51v6DBS0A";
    HttpUrl.Builder urlBuilder = HttpUrl.parse("https://junction.dev.qoco.fi/api/ping").newBuilder();
    urlBuilder.addQueryParameter("key", key);
    //urlBuilder.addQueryParameter("q", "android");
    //urlBuilder.addQueryParameter("rsz", "8");
    String url = urlBuilder.build().toString();
    //url = url +
    Request request = new Request.Builder()
            .url(url)
            .addHeader("x-api-key","jmdSHjy6WPaXwoR75E6mJ1ImhxKPRJb51v6DBS0A")
            .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        String mMessage = e.getMessage().toString();
        Log.w("failure Response", mMessage);
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        String mMessage = response.body().string();
        Log.d("Resultat", mMessage);
      }
    });
  }
}