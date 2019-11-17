package com.example.junctionhack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference luggageRef = mRootRef.child("baggage");
    List<DatabaseReference> eventRef = new ArrayList<DatabaseReference>();
    //SharedPreferences prefs = this.getSharedPreferences(
     //       "com.example.junctionhack", Context.MODE_PRIVATE);
    List<Baggage> baggages = new ArrayList<Baggage>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Parser parser = new Parser();

        //getBaggage("4202cdca-9176-4b6c-aced-745f90741712");
        getBaggage("e4bd8e79-59c2-42c1-be9a-34c9d5c61f18");
        //eventRef.add(luggageRef.child("/1/events"));
        //eventRef.add(luggageRef.child("/5/events"));


    }


    public void getBaggage(String code){

        luggageRef.orderByChild("baggageId").equalTo(code).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String prevChildKey) {
                String jsobObjStr = dataSnapshot.getValue().toString();
                String path = "/"+dataSnapshot.getKey()+"/events";
                eventRef.add(luggageRef.child(path));
                listen(eventRef.get(eventRef.size()-1));
                Parser parser = new Parser();
                Baggage baggage = parser.parseBaggage(jsobObjStr);
                baggages.add(baggage);
                //Log.d("RESULTTTT",jsobObjStr);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //Log.d("RESULTTTT",dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // Log.d("RESULTTTT",dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                //Log.d("RESULTTTT",dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Log.d("RESULTTTT",dataSnapshot.getKey());
            }
        });
    }
    public void listen(DatabaseReference ref){

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String prevChildKey) {
                String jsobObjStr = dataSnapshot.getValue().toString();
                Parser parser = new Parser();
                //Log.d("Parsing",jsobObjStr);

                JSONObject eventObj = null;
                /*try {
                    //eventObj = new JSONObject(jsobObjStr);
                    Log.d("Parsing",jsobObjStr);
                    //eventObj = eventObj.getJSONObject("events");///MAke proper json


                }catch (JSONException err){

                }*/
                //Events ev = parser.parseEvent(eventObj);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String jsobObjStr = dataSnapshot.getValue().toString();
                Parser parser = new Parser();
                Log.d("LISTENING",jsobObjStr);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // Log.d("RESULTTTT",dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.d("RESULTTTT",dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Log.d("RESULTTTT",dataSnapshot.getKey());
            }
        });
    }
}
