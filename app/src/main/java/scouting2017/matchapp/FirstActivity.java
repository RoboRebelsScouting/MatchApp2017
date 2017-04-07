
package scouting2017.matchapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class FirstActivity extends AppCompatActivity {

    public static Variables myAppVariables ;
    public static Activity appActivity;

    @Override
    public void onBackPressed() {
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appActivity = this;

        if (myAppVariables == null) {
            myAppVariables = new Variables () ;
            myAppVariables.startBluetooth(this);
        }
        setContentView(R.layout.activity_first);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
        TextView eventNameText = (TextView) findViewById(R.id.enterEvent);
        EditText scouterNameText = (EditText) findViewById(R.id.enterName);
        EditText matchNumberText = (EditText) findViewById(R.id.enterMatch);
        allianceColorToggleButton();
        if (!myAppVariables.scouterName.equals("")) {
            scouterNameText.setText(myAppVariables.scouterName);
        }
        if (!myAppVariables.scouterName.equals("")) {
            matchNumberText.setText(Integer.toString(myAppVariables.matchNumber));
        }
        try {
            long currentTimeInMillis = System.currentTimeMillis();

            if ((currentTimeInMillis >= sdf.parse("Feb 17 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Feb 19 2017").getTime() )) {
                eventNameText.setText("Week_0");
            } else if ((currentTimeInMillis >= sdf.parse("Feb 19 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Mar 12 2017").getTime() )) {
                eventNameText.setText("WPI");
            } else if ((currentTimeInMillis >= sdf.parse("Mar 13 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Mar 27 2017").getTime() )) {
                eventNameText.setText("Bryant");
            } else if ((currentTimeInMillis >= sdf.parse("Mar 28 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Apr 9 2017").getTime() )) {
                eventNameText.setText("UNH");
            }  else {
                eventNameText.setText("Worlds");
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    public void toAuto(View view) {

        EditText t = (EditText) findViewById(R.id.enterRobot);
        if (t.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a Robot Number", Toast.LENGTH_LONG).show();
            return;
        }
        FirstActivity.myAppVariables.robotNumber = Integer.parseInt(t.getText().toString());
        EditText g = (EditText) findViewById(R.id.enterMatch);
        if (g.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a Match Number", Toast.LENGTH_LONG).show();
            return;
        }
        FirstActivity.myAppVariables.matchNumber = Integer.parseInt(g.getText().toString());
        TextView c = (TextView) findViewById(R.id.enterEvent);
        FirstActivity.myAppVariables.competitionName = c.getText().toString();
        EditText f = (EditText) findViewById(R.id.enterName);
        if (f.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Enter Scouter Name", Toast.LENGTH_LONG).show();
            return;
        }
        FirstActivity.myAppVariables.scouterName = f.getText().toString();
        Intent intent = new Intent(this, secondActivity.class);
        myAppVariables.startAutoTime = System.currentTimeMillis();
        startActivity(intent);
    }
    public void allianceColorToggleButton(){
        ToggleButton allianceColor = (ToggleButton) findViewById(R.id.allianceColor);
        allianceColor.setChecked(myAppVariables.allianceColor);
        if (myAppVariables.allianceColor) {
            //RED
            allianceColor.getBackground().setColorFilter(new LightingColorFilter(0xFF0000FF, 0xFF0000FF));
        } else {
            //BLUE
            allianceColor.getBackground().setColorFilter(new LightingColorFilter (0xFFFF0000,0xFFFF0000));
        }
    }
    public void allianceColor(View view) {
        ToggleButton allianceColor = (ToggleButton) findViewById(R.id.allianceColor);
        if (allianceColor.isChecked()) {
            //RED
            allianceColor.getBackground().setColorFilter(new LightingColorFilter(0xFF0000FF, 0xFF0000FF));
        } else {
            //BLUE
            allianceColor.getBackground().setColorFilter(new LightingColorFilter (0xFFFF0000,0xFFFF0000));
        }
        String allianceColorButtonText = allianceColor.getText().toString();
        if (allianceColorButtonText.equals("Blue Alliance")) {
            FirstActivity.myAppVariables.allianceColor = true ;
        } else {
            FirstActivity.myAppVariables.allianceColor = false ;
        }
    }
}
