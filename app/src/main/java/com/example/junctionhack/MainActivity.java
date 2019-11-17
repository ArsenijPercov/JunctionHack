package com.example.junctionhack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements AddDialog.AddDialogListener {


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference luggageRef = mRootRef.child("baggage");
    List<DatabaseReference> eventRef = new ArrayList<DatabaseReference>();
    //SharedPreferences prefs = this.getSharedPreferences(
     //       "com.example.junctionhack", Context.MODE_PRIVATE);
    ArrayList<Baggage> baggages = new ArrayList<Baggage>();
    ExpandingList expandingList = null;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.test_options_button) {
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Parser parser = new Parser();

        //getBaggage("4202cdca-9176-4b6c-aced-745f90741712");
        FloatingActionButton fab = findViewById(R.id.floating_add_baggage_id);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddDialog addDialog = new AddDialog();
                addDialog.show(getSupportFragmentManager(), "example_dialog");
            }
        });

        getBaggage("90cda708-b57d-4195-b30d-627e9f99ea36", "Test1");
        //getBaggage("e4bd8e79-59c2-42c1-be9a-34c9d5c61f18", "test1");
        //eventRef.add(luggageRef.child("/1/events"));
        //eventRef.add(luggageRef.child("/5/events"));

        expandingList = (ExpandingList) findViewById(R.id.baggage_list);
        getBaggage("e4bd8e79-59c2-42c1-be9a-34c9d5c61f18", "SKJDHSJK");
    }


    public void getBaggage(String code, final String name){
        luggageRef.orderByChild("baggageId").equalTo(code).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String prevChildKey) {
                Gson gson = new Gson();
                String jsobObjStr = gson.toJson(dataSnapshot.getValue());
                JSONArray object = null;
                try{
                    object = new JSONArray(jsobObjStr);
                } catch (JSONException e){
                    e.printStackTrace();
                }
                JSONArray jsonArray = object;


                String path = "/"+dataSnapshot.getKey()+"/events";
                eventRef.add(luggageRef.child(path));
                listen(eventRef.get(eventRef.size()-1));
                Parser parser = new Parser();
                //Log.d("checkk", jsobObjStr);
                Baggage baggage = parser.parseBaggage(jsobObjStr);
                Log.d("sansa", name);
                Log.d("sansa3", baggage.getBaggageId());
                baggage.setName(name);
                baggages.add(baggage);//OVOA DODAVA VO NIZA
                display(baggages);
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
    public void display(List<Baggage> bagg){
        for (Baggage bag : bagg){

            addBaggage(bag.getName(), R.color.generic_color, R.mipmap.baggage_icon_foreground);
            //Log.d("CHECKKKK",bag.getListEvents().get(0).toString());
            ArrayList<Events> ev = bag.getListEvents();
            for (int i =0;i<ev.size();i++){
               String res = "Status: " + ev.get(i).getType()+'\n';
                res = res + "Airport: " + ev.get(i).getAirport()+ '\n'+ "Time: " + ev.get(i).getTimestamp()+ "\n\n\n";
                addEvent(bag.getName(),res);
            }
        }
    }
    public void listen(DatabaseReference ref) {

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String prevChildKey) {
                Gson gson = new Gson();

                String jsobObjStr = gson.toJson(dataSnapshot.getValue());
                Parser parser = new Parser();
                //Log.d("Parsing",jsobObjStr);

                JSONObject eventObj = null;
                try {
                    eventObj = new JSONObject(jsobObjStr);
                }catch (JSONException err){
                    err.printStackTrace();
                }
                Events ev = parser.parseEvent(eventObj);
                String nameBag;
                for (Baggage bag: baggages){
                    if (bag.getBaggageId().compareTo(ev.getBaggageId())==0){
                        Log.d("OBJCHECK2",bag.getBaggageId() + " " + ev.getBaggageId());

                        boolean flag = false;
                        for (Events event: bag.getListEvents())
                        {
                            Log.d("OBJCHECK3",event.getEventId() + " " + ev.getEventId());
                            if (event.getEventId() == ev.getEventId()){
                                flag = true;
                            }
                        }
                        if (!flag)
                        {
                            Log.d("OBJCHECK4",eventObj.toString());
                            bag.addEvent(ev);
                            String res = "Status: " + ev.getType()+'\n';
                            res = res + "Airport: " + ev.getAirport()+ '\n'+ "Time: " + ev.getTimestamp()+ "\n\n\n";
                            addEvent(bag.getName(),res);
                        }
                    }
                }

                //display(baggages);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String jsobObjStr = dataSnapshot.getValue().toString();
                Parser parser = new Parser();
                Log.d("LISTENING", jsobObjStr);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // Log.d("RESULTTTT",dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.d("RESULTTTT", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Log.d("RESULTTTT",dataSnapshot.getKey());
            }
        });

    }
    protected void addBaggage(String title, int colorRes, int iconRes) {
        final ExpandingItem baggage = (ExpandingItem) expandingList.createNewItem(R.layout.expanding_layout);
        ImageView img = (ImageView) baggage.findViewById(R.id.luggage_info);

        if(baggage != null) {
            baggage.createSubItems(1);
            baggage.setIndicatorColorRes(colorRes);
            baggage.setIndicatorIconRes(iconRes);
            TextView txt;
            txt = (TextView) baggage.findViewById(R.id.baggage_name);
            txt.setText(title);
            TextViewCompat.setTextAppearance(txt, R.style.roboto_mono_bold);
            baggage.findViewById(R.id.remove_baggage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Remove baggage?\n")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                expandingList.removeItem(baggage);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }
    protected void addEvent(String baggageTitle, String event) {
        ExpandingItem baggage = null;

        for(int i=0; i<expandingList.getItemsCount(); i++) {
            ExpandingItem tempItem = expandingList.getItemByIndex(i);
            TextView textView = (TextView) tempItem.findViewById(R.id.baggage_name);
            if(textView.getText().toString().compareTo(baggageTitle) == 0) {
                baggage = tempItem;
            }
        }
        try {
            View view = baggage.getSubItemView(0);
            TextView textView = view.findViewById(R.id.event);
            StringBuilder oldString = new StringBuilder();
            oldString.append(textView.getText().toString());

            oldString.append(event);
            textView.setText(oldString.toString());
            TextViewCompat.setTextAppearance(textView, R.style.roboto_mono_light);
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void applyTexts(String id, String name) {
        //Log.d("HELLOO", "@");
        Log.d("THEID", id);
        getBaggage(id, name);
    }
}
