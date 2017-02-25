package scouting2017.matchapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static scouting2017.matchapp.FirstActivity.myAppVariables;

public class activity_fourth extends AppCompatActivity {

    @Override
    public void onBackPressed() {
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        getSupportActionBar().setTitle(Integer.toString(FirstActivity.myAppVariables.robotNumber));
        if (FirstActivity.myAppVariables.allianceColor == true) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        } else {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        }
    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent(this, FirstActivity.class) ;
        String competitionNameInfo = FirstActivity.myAppVariables.competitionName;
        String scouterNameInfo = FirstActivity.myAppVariables.scouterName;
        Integer matchNumberInfo = FirstActivity.myAppVariables.matchNumber;
        FirstActivity.myAppVariables.reset();
        FirstActivity.myAppVariables.competitionName = competitionNameInfo;
        FirstActivity.myAppVariables.scouterName = scouterNameInfo;
        FirstActivity.myAppVariables.matchNumber = matchNumberInfo + 1;
        startActivity(intent);

    }
    public void climbed (View view) {
        GameEvent climbed = new GameEvent () ;
        climbed.eventType = "climbed" ;
        climbed.eventValue = "1" ;
        climbed.eventTime = System.currentTimeMillis() ;
        myAppVariables.eventList.add(climbed) ;
    }
    public void broken (View view) {
        GameEvent broken = new GameEvent () ;
        broken.eventType = "broken" ;
        broken.eventValue = "1" ;
        broken.eventTime = System.currentTimeMillis() ;
        myAppVariables.eventList.add(broken) ;
    }
    public void createCSV (View view) {
        myAppVariables.CSVCreate(this);
    }
}
