package nl.tue.demothermostat;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.*;

/**
 * Created by Roy on 22-6-2017.
 */

public class Monday extends Activity {

    ImageButton deleteButton1;
    ImageButton deleteButton2;
    ImageButton deleteButton3;
    ImageButton deleteButton4;
    ImageButton deleteButton5;
    ImageButton deleteButtonAll;
    EditText startTime1;
    EditText startTime2;
    EditText startTime3;
    EditText startTime4;
    EditText startTime5;
    EditText endTime1;
    EditText endTime2;
    EditText endTime3;
    EditText endTime4;
    EditText endTime5;

    String midnight = "00:00";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_switches);

        deleteButton1 = (ImageButton) findViewById(R.id.deleteButton1);
        deleteButton2 = (ImageButton) findViewById(R.id.deleteButton2);
        deleteButton3 = (ImageButton) findViewById(R.id.deleteButton3);
        deleteButton4 = (ImageButton) findViewById(R.id.deleteButton4);
        deleteButton5 = (ImageButton) findViewById(R.id.deleteButton5);
        deleteButtonAll = (ImageButton) findViewById(R.id.deleteButtonAll);

        deleteButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime1.setText(midnight);
                endTime1.setText(midnight);
            }
        });

        deleteButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime2.setText(midnight);
                endTime2.setText(midnight);
            }
        });

        deleteButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime3.setText(midnight);
                endTime3.setText(midnight);
            }
        });

        deleteButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime4.setText(midnight);
                endTime4.setText(midnight);
            }
        });

        deleteButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime5.setText(midnight);
                endTime5.setText(midnight);
            }
        });

        deleteButtonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime1.setText(midnight);
                endTime1.setText(midnight);
                startTime2.setText(midnight);
                endTime2.setText(midnight);
                startTime3.setText(midnight);
                endTime3.setText(midnight);
                startTime4.setText(midnight);
                endTime4.setText(midnight);
                startTime5.setText(midnight);
                endTime5.setText(midnight);
            }
        });

        startTime1 = (EditText) findViewById(R.id.startTime1);
        startTime2 = (EditText) findViewById(R.id.startTime2);
        startTime3 = (EditText) findViewById(R.id.startTime3);
        startTime4 = (EditText) findViewById(R.id.startTime4);
        startTime5 = (EditText) findViewById(R.id.startTime5);

        endTime1 = (EditText) findViewById(R.id.endTime1);
        endTime2 = (EditText) findViewById(R.id.endTime2);
        endTime3 = (EditText) findViewById(R.id.endTime3);
        endTime4 = (EditText) findViewById(R.id.endTime4);
        endTime5 = (EditText) findViewById(R.id.endTime5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    WeekProgram wpg = HeatingSystem.getWeekProgram();
                    startTime1.setText(wpg.data.get("monday").get(8).getTime());
                    endTime1.setText(wpg.data.get("monday").get(9).getTime());

                } catch (Exception e) {
                     e.printStackTrace();
                }
            }
        }).start();



    }
}