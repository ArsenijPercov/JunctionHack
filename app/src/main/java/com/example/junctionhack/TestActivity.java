package com.example.junctionhack;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        final EditText editAirport = findViewById(R.id.put_airport);

        final EditText editBagId = findViewById(R.id.put_baggage_id);
        final EditText editEventId = findViewById(R.id.put_event_id);
        final EditText editTimestamp = findViewById(R.id.put_timestamp);
        final EditText editType = findViewById(R.id.put_type);


        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        DatabaseReference mRef =  database.getReference().child("baggage");

        Button button = findViewById(R.id.test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String airport = editAirport.getText().toString();
                String bagId = editBagId.getText().toString();
                String Timestamp = editTimestamp.getText().toString();
                String EventId = editEventId.getText().toString();
                String Type = editType.getText().toString();


            }
        });


    }
}
