package com.example.junctionhack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
        addBaggage("My baggage", "FIRST EVENT", android.R.color.holo_green_light, R.mipmap.baggage_icon_foreground);
        addBaggage("My sons baggage","SECOND EVENT", android.R.color.holo_blue_dark, R.mipmap.baggage_icon_foreground);
        addEvent("My sons baggage", "MISSING\nBER\n01521899\n\n");

    }
    protected void addBaggage(String title, String event, int colorRes, int iconRes) {
        final ExpandingItem baggage = (ExpandingItem) expandingList.createNewItem(R.layout.expanding_layout);
        if(baggage != null) {

            baggage.createSubItem();
            TextView textView = baggage.findViewById(R.id.event);
            textView.setText(event);
            baggage.setIndicatorColorRes(colorRes);
            baggage.setIndicatorIconRes(iconRes);
            TextView txt;
            txt = (TextView) baggage.findViewById(R.id.baggage_name);
            txt.setText(title);
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
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        /*StringBuilder oldString = new StringBuilder();


        TextView view = (TextView) baggage.getSubItemView(0);
        oldString.append(view.getText().toString());
        expandingList.removeItem(baggage);

        StringBuilder newString = new StringBuilder();
        newString.append(oldString.toString());

        final ExpandingItem baggage = (ExpandingItem) expandingList.createNewItem(R.layout.expanding_layout);
        if(baggage != null) {
            baggage.setIndicatorColorRes(colorRes);
            baggage.setIndicatorIconRes(iconRes);
            TextView txt;
            txt = (TextView) baggage.findViewById(R.id.baggage_name);
            txt.setText(title);




        baggage.createSubItem();
        baggage.createSubItem();

        View vieww = baggage.getSubItemView(0);
        TextView txxt = (TextView) vieww.findViewById(R.id.event);
        txxt.setText(oldString.toString());

        int idx = baggage.getSubItemsCount()-1;
        View view = baggage.getSubItemView(idx);
        StringBuilder result = new StringBuilder();
        for(String s : event) {
            result.append(s + "\n");
        }
        result.append("\n");
        TextView text = (TextView) view.findViewById(R.id.event);
        text.setText(result.toString());
        */
    }
 }
