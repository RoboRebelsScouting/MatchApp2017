
package scouting2017.matchapp;

import android.content.Intent;
import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class FirstActivity extends AppCompatActivity {

    public static Variables myAppVariables ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAppVariables = new Variables () ;
        setContentView(R.layout.activity_first);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy");
        TextView eventNameText = (TextView) findViewById(R.id.enterEvent);

        try {
            long currentTimeInMillis = System.currentTimeMillis();

            if ((currentTimeInMillis >= sdf.parse("Feb 17 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Feb 19 2017").getTime() )) {
                eventNameText.setText("Week_0");
            } else if ((currentTimeInMillis >= sdf.parse("Mar 8 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Mar 12 2017").getTime() )) {
                eventNameText.setText("WPI");
            } else if ((currentTimeInMillis >= sdf.parse("Mar 23 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Mar 27 2017").getTime() )) {
                eventNameText.setText("Bryant");
            } else if ((currentTimeInMillis >= sdf.parse("Apr 4 2017").getTime()) &&
                    (currentTimeInMillis < sdf.parse("Apr 9 2017").getTime() )) {
                eventNameText.setText("NE_Champs");
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
        myAppVariables.startAutoTime = System.currentTimeMillis() ;
        startActivity(intent);
    }
}
