package nl.tue.demothermostat;


import android.app.Activity;
import android.os.Bundle;

import org.thermostatapp.util.HeatingSystem;
import org.thermostatapp.util.WeekProgram;

/**
 * Created by Roy on 22-6-2017.
 */

public class Sunday extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_switches);

    }
}