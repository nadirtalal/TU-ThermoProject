package nl.tue.demothermostat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class ThermostatActivity extends Activity {

    int vtemp = 211;
    TextView temp;
    SeekBar seekBar;
    ImageView statusLed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);

        ImageView bPlus = (ImageView)findViewById(R.id.bPlus);
        bPlus.setImageResource(R.drawable.up);

        // -- Button action
        ImageView bMinus = (ImageView)findViewById(R.id.bMinus);
        ImageView bCalendar = (ImageView)findViewById(R.id.bCalendar);
        statusLed = (ImageView)findViewById(R.id.statusLed);
        temp = (TextView)findViewById(R.id.temp);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/coolFont.ttf");
        temp.setTypeface(custom_font);

        bCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WeekOverview.class);
                startActivity(intent);
            }
        });

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
        //TODO implement the holding button to increase function
        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusLed.setImageResource(R.drawable.green_status_led);
                vtemp++;
                temp.setText(vtemp/10 + "\u00B0C");
                seekBar.setProgress(vtemp-50);
            }
        });
        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statusLed.setImageResource(R.drawable.gray_status_led);
                vtemp--;
                temp.setText(vtemp/10 + "\u00B0C");
                seekBar.setProgress(vtemp-50);
            }
        });
    }
}
