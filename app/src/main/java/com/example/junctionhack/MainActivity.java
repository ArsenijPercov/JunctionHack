package com.example.junctionhack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ExpandingList expandingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandingList = (ExpandingList) findViewById(R.id.baggage_list);

    }
    protected void addBaggage(String title, ArrayList<ArrayList<String>> events, int colorRes, int iconRes) {
        final ExpandingItem baggage = (ExpandingItem) expandingList.createNewItem(R.layout.expanding_layout);
        if(baggage != null) {
            baggage.setIndicatorColorRes(colorRes);
            baggage.setIndicatorIconRes(iconRes);
            TextView txt;
            txt = (TextView) baggage.findViewById(R.id.baggage_name);
            txt.setText(title);
            baggage.createSubItems(events.size());
            for(int i=0; i<events.size(); i++) {
                View view = baggage.getSubItemView(i);
                TextView type = (TextView) view.findViewById(R.id.event_type);
                type.setText(events.get(i).get(0));
                TextView airport = (TextView) view.findViewById(R.id.event_airport);
                type.setText(events.get(i).get(1));
                TextView timestamp = (TextView) view.findViewById(R.id.event_timestamp);
                type.setText(events.get(i).get(2));
            }
            baggage.findViewById(R.id.remove_baggage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    expandingList.removeItem(baggage);
                }
            });
        }
    }
}
