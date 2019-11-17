package com.example.junctionhack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ExpandingList expandingList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandingList = (ExpandingList) findViewById(R.id.baggage_list);
        addBaggage("My baggage",  android.R.color.holo_green_light, R.mipmap.baggage_icon_foreground);
        addBaggage("My sons baggage", android.R.color.holo_blue_dark, R.mipmap.baggage_icon_foreground);
        addEvent("My sons baggage", "MISSING\nBER\n01521899\n\n");

    }
    protected void addBaggage(String title, int colorRes, int iconRes) {
        final ExpandingItem baggage = (ExpandingItem) expandingList.createNewItem(R.layout.expanding_layout);
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
 }
