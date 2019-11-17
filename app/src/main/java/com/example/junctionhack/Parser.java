package com.example.junctionhack;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Parser{

  public Baggage parseBaggage(String object){
    Log.d("Resultat", object);

        JSONObject jsonObj = null;
        try {
          jsonObj = new JSONObject(object);
          Log.d("Parsing",object);
          JSONObject b = jsonObj.getJSONObject("baggage");

          String customerId = b.getString("customerId");
          String id = b.getString("baggageId");
          String rushbag = b.getString("rushbag");
          String special = b.getString("special");
          Float weight = (float) b.getDouble("weight");
          JSONArray events= b.getJSONArray("events");

          List<Events> listTemp = new ArrayList<Events>();
          for (int i =0;i< events.length();i++){
            listTemp.add(parseEvent(events.getJSONObject(i)));
          }
          Baggage new_baggage = new Baggage(id, customerId, rushbag == "Y" ? true:false ,weight,special,listTemp);
          return new_baggage;
          //Log.d("Resultat", "id = " +new_baggage.getBaggageId());
          //Log.d("Resultat", "custid = " +new_baggage.getCustomerId());

        } catch (JSONException e) {

          e.printStackTrace();
          return null;
        }
      }





    public Events parseEvent(JSONObject jsonobj){
      try {
        //Log.d("Parsing",jsonobj.toString());
      String airport = jsonobj.getString("airport");
        //Log.d("Parsing","~~~~~");
      String eventID = jsonobj.getString("eventId");


        String baggageID = jsonobj.getString("baggageId");
        //Log.d("Parsing","~~~~~2");

        String timestamp = jsonobj.getString("timestamp");
      String type= jsonobj.getString("type");
      return new Events(baggageID,eventID,airport,timestamp,type);
      } catch (JSONException e) {

      e.printStackTrace();
      return null;
    }
  }



}
