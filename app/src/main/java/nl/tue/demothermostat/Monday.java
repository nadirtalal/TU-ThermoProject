package nl.tue.demothermostat;


import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import org.thermostatapp.util.HeatingSystem;
import java.net.ConnectException;
import org.thermostatapp.util.*;

/**
 * Created by Roy on 22-6-2017.
 */

public class Monday extends Activity {

    String day = "Monday";
    Switch[] days;
    Switch[] nights;

    ImageButton deleteButton1;
    ImageButton deleteButton2;
    ImageButton deleteButton3;
    ImageButton deleteButton4;
    ImageButton deleteButton5;
    ImageButton deleteButtonAll;
    ImageButton imageButtonApply;
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

    ImageButton setDay;
    EditText dayTemp;
    EditText nightTemp;

    String dayTemperature;
    String nightTemperature;
    String midnight = "00:00";
    WeekProgram wpg;

    //Context context = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;
    Toast toastTemp;
    Toast toastPlanning;
    Toast toastInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_switches);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        toastTemp = Toast.makeText(this, "Day and night temperatures have been updated", duration);
        toastPlanning = Toast.makeText(this, day + "'s program has been updated", duration);
        toastInput = Toast.makeText(this, "Invalid input detected, all input should adhere to 24 hour notation", duration);
        setDay = (ImageButton) findViewById(R.id.setDay);
        dayTemp = (EditText) findViewById(R.id.dayTemp);
        nightTemp = (EditText) findViewById(R.id.nightTemp);


        //make a listener for the update day/night temp button, it updates the temps on server and give pop-up
        setDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dayTemperature = (dayTemp.getText().toString());
                        nightTemperature = (nightTemp.getText().toString());
                        try {
                            HeatingSystem.put("dayTemperature", dayTemperature);
                            HeatingSystem.put("nightTemperature", nightTemperature);
                            toastTemp.show();
                        } catch (InvalidInputValueException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });


        deleteButton1 = (ImageButton) findViewById(R.id.deleteButton1);
        deleteButton2 = (ImageButton) findViewById(R.id.deleteButton2);
        deleteButton3 = (ImageButton) findViewById(R.id.deleteButton3);
        deleteButton4 = (ImageButton) findViewById(R.id.deleteButton4);
        deleteButton5 = (ImageButton) findViewById(R.id.deleteButton5);
        deleteButtonAll = (ImageButton) findViewById(R.id.deleteButtonAll);
        imageButtonApply = (ImageButton) findViewById(R.id.imageButtonApply);

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

        //listerner for the apply button, it get the current weekprogram and replaces the day
        //also check if every input is in valid 24H notation
        imageButtonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    wpg = HeatingSystem.getWeekProgram();

                    if(Switch.isValidTimeSyntax(String.valueOf(endTime1.getText())) ){
                        wpg.data.get(day).get(0).setTime(String.valueOf(endTime1.getText()));
                        wpg.data.get(day).get(0).setType("night");
                    }else{
                        toastInput.show();
                        return;
                    }
                    if(Switch.isValidTimeSyntax(String.valueOf(endTime2.getText())) ){
                        wpg.data.get(day).get(1).setTime(String.valueOf(endTime2.getText()));
                        wpg.data.get(day).get(1).setType("night");
                    }else{
                        toastInput.show();
                        return;
                    }
                    if(Switch.isValidTimeSyntax(String.valueOf(endTime3.getText())) ){
                        wpg.data.get(day).get(2).setTime(String.valueOf(endTime3.getText()));
                        wpg.data.get(day).get(2).setType("night");
                    }else{
                        toastInput.show();
                        return;
                    }
                    if(Switch.isValidTimeSyntax(String.valueOf(endTime4.getText())) ){
                        wpg.data.get(day).get(3).setTime(String.valueOf(endTime4.getText()));
                        wpg.data.get(day).get(3).setType("night");
                    }else{
                        toastInput.show();
                        return;
                    }
                    if(Switch.isValidTimeSyntax(String.valueOf(endTime5.getText())) ){
                        wpg.data.get(day).get(4).setTime(String.valueOf(endTime5.getText()));
                        wpg.data.get(day).get(4).setType("night");
                    }else{
                        toastInput.show();
                        return;
                    }
                    if(Switch.isValidTimeSyntax(String.valueOf(startTime1.getText())) ){
                        wpg.data.get(day).get(5).setTime(String.valueOf(startTime1.getText()));
                        wpg.data.get(day).get(5).setType("day");
                    }else{
                        toastInput.show();
                        return;
                    }
                    if(Switch.isValidTimeSyntax(String.valueOf(startTime2.getText())) ){
                        wpg.data.get(day).get(6).setTime(String.valueOf(startTime2.getText()));
                        wpg.data.get(day).get(6).setType("day");
                    }else{
                        toastInput.show();
                        return;
                    }
                    if(Switch.isValidTimeSyntax(String.valueOf(startTime3.getText())) ){
                        wpg.data.get(day).get(7).setTime(String.valueOf(startTime3.getText()));
                        wpg.data.get(day).get(7).setType("day");
                    }else{
                        toastInput.show();
                        return;
                    }
                    if(Switch.isValidTimeSyntax(String.valueOf(startTime4.getText())) ){
                        wpg.data.get(day).get(8).setTime(String.valueOf(startTime4.getText()));
                        wpg.data.get(day).get(8).setType("day");
                    }else{
                        toastInput.show();
                        return;
                    }
                    if(Switch.isValidTimeSyntax(String.valueOf(startTime5.getText())) ){
                        wpg.data.get(day).get(9).setTime(String.valueOf(startTime5.getText()));
                        wpg.data.get(day).get(9).setType("day");
                    }else{
                        toastInput.show();
                        return;
                    }

                    HeatingSystem.setWeekProgram(wpg);

                    toastPlanning.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                    wpg = HeatingSystem.getWeekProgram();
                    int dayN = 0;
                    int nightN = 0;
                    days = new Switch[5];

                    nights = new Switch[5];

                    for (int i = 0; i < 10; i++) {
                        Switch current = (wpg.data.get(day).get(i));
                        if (current.getType().equals("day")) {
                            days[dayN] = current;
                            dayN++;
                        } else {
                            nights[nightN] = current;
                            nightN++;
                        }
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            endTime1.setText(nights[0].getTime());
                            endTime2.setText(nights[1].getTime());
                            endTime3.setText(nights[2].getTime());
                            endTime4.setText(nights[3].getTime());
                            endTime5.setText(nights[4].getTime());
                            startTime1.setText(days[0].getTime());
                            startTime2.setText(days[1].getTime());
                            startTime3.setText(days[2].getTime());
                            startTime4.setText(days[3].getTime());
                            startTime5.setText(days[4].getTime());
                        }});

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dayTemperature = HeatingSystem.get("dayTemperature");
                    nightTemperature = HeatingSystem.get("nightTemperature");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                             dayTemp.setText(dayTemperature);
                            nightTemp.setText(nightTemperature);
                        }});
                } catch (ConnectException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}