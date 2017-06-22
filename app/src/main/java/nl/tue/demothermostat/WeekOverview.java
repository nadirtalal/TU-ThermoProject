package nl.tue.demothermostat;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.thermostatapp.util.*;

/**
 * Created by nstash on 06/05/15.
 */


public class WeekOverview extends Activity {

    Button bMonday;
    Button bTuesday;
    Button bWednesday;
    Button bThursday;
    Button bFriday;
    Button bSaturday;
    Button bSunday;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_overview);


        bMonday = (Button) findViewById(R.id.bMonday);
        bTuesday = (Button) findViewById(R.id.bTuesday);
        bWednesday = (Button) findViewById(R.id.bWednesday);
        bThursday = (Button) findViewById(R.id.bThursday);
        bFriday = (Button) findViewById(R.id.bFriday);
        bSaturday = (Button) findViewById(R.id.bSaturday);
        bSunday = (Button) findViewById(R.id.bSunday);

        bMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeekOverview.this, Monday.class);
                WeekOverview.this.startActivity(intent);
            }
        });

        bTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeekOverview.this, Tuesday.class);
                WeekOverview.this.startActivity(intent);
            }
        });

        bWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeekOverview.this, Wednesday.class);
                WeekOverview.this.startActivity(intent);
            }
        });

        bThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeekOverview.this, Thursday.class);
                WeekOverview.this.startActivity(intent);
            }
        });

        bFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeekOverview.this, Friday.class);
                WeekOverview.this.startActivity(intent);
            }
        });

        bSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeekOverview.this, Saturday.class);
                WeekOverview.this.startActivity(intent);
            }
        });

        bSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WeekOverview.this, Sunday.class);
                WeekOverview.this.startActivity(intent);
            }
        });
    }
}