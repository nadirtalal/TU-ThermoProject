package nl.tue.demothermostat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class ThermostatActivity extends Activity {

    // aeu
    int vtemp = 21;
    TextView temp;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);

        /*
        *
        *
        *
        * */
        ImageView bPlus = (ImageView)findViewById(R.id.bPlus);
        bPlus.setImageResource(R.drawable.add_button);

        // -- Button action
        ImageView bMinus = (ImageView)findViewById(R.id.bMinus);
        temp = (TextView)findViewById(R.id.temp);
        Button weekOverview = (Button)findViewById(R.id.week_overview);

        weekOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), WeekOverview.class);
                startActivity(intent);
            }
        });
         // --
        Button testingWS = (Button)findViewById(R.id.testing_ws);

        testingWS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TestingWS.class);
                startActivity(intent);
            }
        });

        // ACTION
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress((vtemp-15));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                vtemp = i+15;
                temp.setText((i+15) + " \u2103");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vtemp++;
                temp.setText(vtemp + " \u2103");
                seekBar.setProgress((vtemp-15));
            }
        });
        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vtemp--;
                temp.setText(vtemp + " \u2103");
                seekBar.setProgress((vtemp-15));
            }
        });
    }
}
