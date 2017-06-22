package nl.tue.demothermostat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import org.thermostatapp.util.*;

public class ThermostatActivity extends Activity {

    int vtemp = 211;

    CountDownTimer serverUpdateTimer, serverUpdateTemperatures;
    String getParam, currentTempString, targetTempString;
    Double currentTemp, targetTemp;
    TextView temp, serverTime;
    SeekBar seekBar;
    ImageView statusLed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thermostat);

        ImageView bPlus = (ImageView)findViewById(R.id.bPlus);
        bPlus.setImageResource(R.drawable.up);
        ImageView bMinus = (ImageView)findViewById(R.id.bMinus);
        ImageView bCalendar = (ImageView)findViewById(R.id.bCalendar);
        statusLed = (ImageView)findViewById(R.id.statusLed);




        //intialize a count down timer to make the updating of the servertime loop every 300 millisec
        serverUpdateTemperatures = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //create a new thread to keep polling the server time
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        currentTempString = "";
                        targetTempString = "";
                        try {
                            targetTemp = (((double)vtemp)/10.00);
                            targetTempString = String.valueOf(targetTemp);
                            HeatingSystem.put("targetTemperature", targetTempString);
                            currentTempString = HeatingSystem.get("currentTemperature");

                            currentTemp = Double.parseDouble(currentTempString);
                            /*
									HeatingSystem.get("day");
									HeatingSystem.get("time");
									HeatingSystem.get("currentTemperature");
									HeatingSystem.get("targetTemperature");
									HeatingSystem.get("dayTemperature");
									HeatingSystem.get("nightTemperature");
									HeatingSystem.get("weekProgramState");
							*/

                            if(targetTemp>currentTemp){
                                statusLed.setImageResource(R.drawable.green_status_led);
                            }else{
                                statusLed.setImageResource(R.drawable.gray_status_led);
                            }
                        } catch (Exception e) {
                            System.err.println("Error from getdata "+e);
                        }
                    }
                }).start();
            }

            @Override
            public void onFinish() {
                try{
                    loopUpdateTemp();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };

        //start the temp update timer
        new Thread(new Runnable() {
            @Override
            public void run() {
                serverUpdateTemperatures.start();
            }
        }).start();

        serverUpdateTimer = new CountDownTimer(10000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                //create a new thread to keep polling the server time
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getParam = "";
                        try {
                            getParam = HeatingSystem.get("time");
                            /*
									HeatingSystem.get("day");
									HeatingSystem.get("time");
									HeatingSystem.get("currentTemperature");
									HeatingSystem.get("targetTemperature");
									HeatingSystem.get("dayTemperature");
									HeatingSystem.get("nightTemperature");
									HeatingSystem.get("weekProgramState");
							*/
                            serverTime.post(new Runnable() {
                                @Override
                                public void run() {
                                    if(getParam != null) {
                                        serverTime.setText(getParam);
                                    }else{
                                        serverTime.setText("slow boy");
                                    }
                                }
                            });

                        } catch (Exception e) {
                            System.err.println("Error from getdata "+e);
                        }
                    }
                }).start();
            }

            @Override
            public void onFinish() {
                try{
                    loopUpdateTime();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };

        //start the time update timer
        new Thread(new Runnable() {
            @Override
            public void run() {
                serverUpdateTimer.start();
            }
        }).start();

        //textview for the temp in a custom font
        temp = (TextView)findViewById(R.id.temp);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/coolFont.ttf");
        temp.setTypeface(custom_font);

        //textview for server time
        serverTime = (TextView)findViewById(R.id.serverTime);


        //creation of the seekbar
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress(161);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                vtemp = (i+50);
                temp.setText(vtemp/10.0f + "\u00B0C");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //creation of the calender button
        bCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WeekOverview.class);
                startActivity(intent);
            }
        });

        //creating of the up and down buttons
        new ContinuousLongClickListener(bPlus, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (vtemp < 300) {
                    //statusLed.setImageResource(R.drawable.green_status_led);
                    vtemp = vtemp + 10;
                    temp.setText(vtemp / 10 + "\u00B0C");
                    seekBar.setProgress(vtemp - 50);
                }
                return false;
            }
        });

        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vtemp < 300) {
                    //statusLed.setImageResource(R.drawable.green_status_led);
                    vtemp++;
                    temp.setText(vtemp / 10 + "\u00B0C");
                    seekBar.setProgress(vtemp - 50);
                }
            }
        });

        new ContinuousLongClickListener(bMinus, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (vtemp > 50) {
                    //statusLed.setImageResource(R.drawable.gray_status_led);
                    vtemp = vtemp - 10;
                    temp.setText(vtemp / 10 + "\u00B0C");
                    seekBar.setProgress(vtemp - 50);
                }
                return false;
            }
        });

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vtemp > 50) {
                    //statusLed.setImageResource(R.drawable.gray_status_led);
                    vtemp--;
                    temp.setText(vtemp / 10 + "\u00B0C");
                    seekBar.setProgress(vtemp - 50);
                }
            }
        });
    }

    public void loopUpdateTime(){
        serverUpdateTimer.start();
    }

    public void loopUpdateTemp(){
        serverUpdateTemperatures.start();
    }

}
