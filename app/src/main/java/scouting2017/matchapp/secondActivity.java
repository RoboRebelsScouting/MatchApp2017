package scouting2017.matchapp;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TimeFormatException;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;


public class secondActivity extends AppCompatActivity {
    public Handler autoTimer = new Handler() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        FirstActivity.myAppVariables.autoTime = 15000 ;
        TextView autoTimerText = (TextView) findViewById(R.id.autoTimerText) ;
        autoTimerText.setText(String.valueOf (FirstActivity.myAppVariables.autoTime/1000));
        autoTimer.postDelayed(updateTimer, 1000);
        getSupportActionBar().setTitle(Integer.toString(FirstActivity.myAppVariables.robotNumber));
    }
    private final Runnable updateTimer = new Runnable () {
        public void run() {
            try {
                FirstActivity.myAppVariables.autoTime -= 1000 ;
                TextView autoTimerText = (TextView) findViewById(R.id.autoTimerText) ;
                autoTimerText.setText(String.valueOf (FirstActivity.myAppVariables.autoTime/1000));
                if (FirstActivity.myAppVariables.autoTime <= 0) {
                    toTeleop ((View)findViewById(R.id.activity_second)) ;
                }
                else {
                    autoTimer.postDelayed (this, 1000) ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } ;
    public void toTeleop(View view) {
        Intent intent = new Intent(this, thirdActivity.class) ;
        autoTimer.removeCallbacks(updateTimer);
        startActivity (intent);
    }
    public void gearPlaced (View view) {
        FirstActivity.myAppVariables.numberGearsPlacedAuto ++;
        TextView numberOfGearsPlacedText = (TextView) findViewById(R.id.numberOfGearsPlacedText) ;
        numberOfGearsPlacedText.setText(Integer.toString (FirstActivity.myAppVariables.numberGearsPlacedAuto));
        GameEvent gearPlacedAuto = new GameEvent () ;
        gearPlacedAuto.eventType = "gearPlacedAuto" ;
        gearPlacedAuto.eventValue = "1" ;
        gearPlacedAuto.eventTime = System.currentTimeMillis() ;
        FirstActivity.myAppVariables.eventList.add(gearPlacedAuto) ;
    }
    public void droppedGear (View view) {
        FirstActivity.myAppVariables.numberDroppedGearsAuto ++;
        TextView numberOfDroppedGearsText = (TextView) findViewById(R.id.numberOfDroppedGearsText) ;
        numberOfDroppedGearsText.setText(Integer.toString (FirstActivity.myAppVariables.numberDroppedGearsAuto));
        GameEvent droppedGearAuto = new GameEvent () ;
        droppedGearAuto.eventType = "droppedGearAuto" ;
        droppedGearAuto.eventValue = "1" ;
        droppedGearAuto.eventTime = System.currentTimeMillis() ;
        FirstActivity.myAppVariables.eventList.add(droppedGearAuto) ;
    }
    public void lowGoal (View view) {
        FirstActivity.myAppVariables.numberLowGoalsAuto ++;
        TextView numberOfLowGoalsText = (TextView) findViewById(R.id.numberOfLowGoalsText);
        numberOfLowGoalsText.setText(Integer.toString(FirstActivity.myAppVariables.numberLowGoalsAuto));
        GameEvent lowGoalAuto = new GameEvent () ;
        lowGoalAuto.eventType = "lowGoalAuto" ;
        lowGoalAuto.eventValue = "1" ;
        lowGoalAuto.eventTime = System.currentTimeMillis() ;
        FirstActivity.myAppVariables.eventList.add(lowGoalAuto) ;
    }
    public void highGoal (View view) {
        FirstActivity.myAppVariables.numberHighGoalsAuto ++;
        TextView numberOfHighGoalsText = (TextView) findViewById(R.id.numberOfHighGoalsText);
        numberOfHighGoalsText.setText(Integer.toString(FirstActivity.myAppVariables.numberHighGoalsAuto));
        GameEvent highGoalAuto = new GameEvent () ;
        highGoalAuto.eventType = "highGoalAuto" ;
        highGoalAuto.eventValue = "1" ;
        highGoalAuto.eventTime = System.currentTimeMillis() ;
        FirstActivity.myAppVariables.eventList.add(highGoalAuto) ;
    }
    public void hopperDumpedAuto (View view) {
        FirstActivity.myAppVariables.numberHoppersDumpedAuto ++;
        TextView numberOfHoppersDumpedText = (TextView) findViewById(R.id.numberOfHoppersDumpedText);
        numberOfHoppersDumpedText.setText(Integer.toString(FirstActivity.myAppVariables.numberHoppersDumpedAuto));
        GameEvent hopperDumpedAuto = new GameEvent () ;
        hopperDumpedAuto.eventType = "hopperDumpedAuto" ;
        hopperDumpedAuto.eventValue = "1" ;
        hopperDumpedAuto.eventTime = System.currentTimeMillis() ;
        FirstActivity.myAppVariables.eventList.add(hopperDumpedAuto) ;
    }
    public void minusHopperDumpedAuto (View view) {
        if (FirstActivity.myAppVariables.numberHoppersDumpedAuto > 0) {
            FirstActivity.myAppVariables.numberHoppersDumpedAuto-- ;
        }
        TextView numberOfHoppersDumpedText = (TextView) findViewById(R.id.numberOfHoppersDumpedText);
        numberOfHoppersDumpedText.setText(Integer.toString(FirstActivity.myAppVariables.numberHoppersDumpedAuto));
        GameEvent minusHopperDumpedAuto = new GameEvent () ;
        minusHopperDumpedAuto.eventType = "hopperDumpedAuto" ;
        minusHopperDumpedAuto.eventValue = "1" ;
        minusHopperDumpedAuto.eventTime = System.currentTimeMillis() ;
        FirstActivity.myAppVariables.eventList.add(minusHopperDumpedAuto) ;
    }
    public void minusLowGoalAuto (View view) {
        if (FirstActivity.myAppVariables.numberLowGoalsAuto > 0) {
            FirstActivity.myAppVariables.numberLowGoalsAuto--;
        }
        TextView numberOfLowGoalsText = (TextView) findViewById(R.id.numberOfLowGoalsText);
        numberOfLowGoalsText.setText(Integer.toString(FirstActivity.myAppVariables.numberLowGoalsAuto));
        GameEvent minusLowGoalAuto = new GameEvent();
        minusLowGoalAuto.eventType = "lowGoalAuto";
        minusLowGoalAuto.eventValue = "1";
        minusLowGoalAuto.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusLowGoalAuto);
    }
    public void minusHighGoalAuto (View view) {
        if (FirstActivity.myAppVariables.numberHighGoalsAuto > 0) {
            FirstActivity.myAppVariables.numberHighGoalsAuto--;
        }
        TextView numberOfHighGoalsText = (TextView) findViewById(R.id.numberOfHighGoalsText);
        numberOfHighGoalsText.setText(Integer.toString(FirstActivity.myAppVariables.numberHighGoalsAuto));
        GameEvent minusHighGoalAuto = new GameEvent () ;
        minusHighGoalAuto.eventType = "highGoalAuto" ;
        minusHighGoalAuto.eventValue = "1" ;
        minusHighGoalAuto.eventTime = System.currentTimeMillis() ;
        FirstActivity.myAppVariables.eventList.add(minusHighGoalAuto) ;
    }
    public void minusGearPlacedAuto (View view) {
        if (FirstActivity.myAppVariables.numberGearsPlacedAuto > 0) {
            FirstActivity.myAppVariables.numberGearsPlacedAuto--;
        }
        TextView numberOfGearsPlacedText = (TextView) findViewById(R.id.numberOfGearsPlacedText) ;
        numberOfGearsPlacedText.setText(Integer.toString (FirstActivity.myAppVariables.numberGearsPlacedAuto));
        GameEvent minusGearPlacedAuto = new GameEvent () ;
        minusGearPlacedAuto.eventType = "gearPlacedAuto" ;
        minusGearPlacedAuto.eventValue = "1" ;
        minusGearPlacedAuto.eventTime = System.currentTimeMillis() ;
        FirstActivity.myAppVariables.eventList.add(minusGearPlacedAuto) ;
    }
    public void minusDroppedGearAuto (View view) {
        if (FirstActivity.myAppVariables.numberDroppedGearsAuto > 0) {
            FirstActivity.myAppVariables.numberDroppedGearsAuto--;
        }
        TextView numberOfDroppedGearsText = (TextView) findViewById(R.id.numberOfDroppedGearsText) ;
        numberOfDroppedGearsText.setText(Integer.toString (FirstActivity.myAppVariables.numberDroppedGearsAuto));
        GameEvent minusDroppedGearAuto = new GameEvent () ;
        minusDroppedGearAuto.eventType = "droppedGearAuto" ;
        minusDroppedGearAuto.eventValue = "1" ;
        minusDroppedGearAuto.eventTime = System.currentTimeMillis() ;
        FirstActivity.myAppVariables.eventList.add(minusDroppedGearAuto) ;
    }
    public void crossBaseline (View view) {
        TextView numberOfDroppedGearsText = (TextView) findViewById(R.id.crossBaselineAuto) ;
        GameEvent crossBaselineAuto = new GameEvent () ;
        crossBaselineAuto.eventType = "crossBaselineAuto" ;
        crossBaselineAuto.eventValue = "1" ;
        crossBaselineAuto.eventTime = System.currentTimeMillis() ;
        FirstActivity.myAppVariables.eventList.add(crossBaselineAuto) ;
    }
}
