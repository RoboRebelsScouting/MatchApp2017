package scouting2017.matchapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static scouting2017.matchapp.FirstActivity.myAppVariables;

public class activity_fourth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
    }
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent(this, FirstActivity.class) ;
        String competitionNameInfo = myAppVariables.competitionName;
        myAppVariables.reset();
        myAppVariables.competitionName = competitionNameInfo;
        startActivity(intent);
    }
    public void notes (View view) {
        TextView matchNotes = (TextView) findViewById(R.id.notes);
        matchNotes.setText(myAppVariables.notes);
        GameEvent notes = new GameEvent () ;
        notes.eventType = "notes" ;
        notes.eventValue = "1" ;
        notes.eventTime = System.currentTimeMillis() ;
        myAppVariables.eventList.add(notes) ;
    }
    public void climbed (View view) {
        TextView matchClimb = (TextView) findViewById(R.id.climbed);
        matchClimb.setText(Boolean.toString(myAppVariables.climbed));
        GameEvent climbed = new GameEvent () ;
        climbed.eventType = "climbed" ;
        climbed.eventValue = "1" ;
        climbed.eventTime = System.currentTimeMillis() ;
        myAppVariables.eventList.add(climbed) ;
    }
    public void createCSV (View view) {
        myAppVariables.CSVCreate(this);
    }
}
