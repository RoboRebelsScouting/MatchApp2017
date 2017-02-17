
package scouting2017.matchapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class FirstActivity extends AppCompatActivity {

    public static Variables myAppVariables ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAppVariables = new Variables () ;
        setContentView(R.layout.activity_first);
    }

    public void toAuto(View view) {


        EditText t = (EditText) findViewById(R.id.enterRobot);
        FirstActivity.myAppVariables.robotNumber = Integer.parseInt(t.getText().toString());
        EditText g = (EditText) findViewById(R.id.enterMatch);
        FirstActivity.myAppVariables.matchNumber = Integer.parseInt(g.getText().toString());
        TextView c = (TextView) findViewById(R.id.enterEvent);
        FirstActivity.myAppVariables.competitionName = c.getText().toString();
        EditText f = (EditText) findViewById(R.id.enterName);
        FirstActivity.myAppVariables.scouterName = f.getText().toString();
        Intent intent = new Intent(this, secondActivity.class);
        myAppVariables.startAutoTime = System.currentTimeMillis() ;
        startActivity(intent);
    }
}
