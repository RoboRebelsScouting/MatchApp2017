package scouting2017.matchapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static scouting2017.matchapp.R.id.approachBoiler;
import static scouting2017.matchapp.R.id.ballsPickedUpHopper;
import static scouting2017.matchapp.R.id.droppedGearTeleop;
import static scouting2017.matchapp.R.id.fiveHighGoal;
import static scouting2017.matchapp.R.id.fiveLowGoal;
import static scouting2017.matchapp.R.id.gearPickedUpGround;
import static scouting2017.matchapp.R.id.gearPickedUpHuman;
import static scouting2017.matchapp.R.id.gearPlacedTeleop;
import static scouting2017.matchapp.R.id.hopperDumpedTeleop;
import static scouting2017.matchapp.R.id.leaveBoiler;
import static scouting2017.matchapp.R.id.minusDroppedGearTeleop;
import static scouting2017.matchapp.R.id.minusGearPlacedTeleop;
import static scouting2017.matchapp.R.id.minusGroundGear;
import static scouting2017.matchapp.R.id.minusHighGoalTeleop;
import static scouting2017.matchapp.R.id.minusHopper;
import static scouting2017.matchapp.R.id.minusHopperBalls;
import static scouting2017.matchapp.R.id.minusHumanGear;
import static scouting2017.matchapp.R.id.minusLowGoalTeleop;
import static scouting2017.matchapp.R.id.oneHighGoal;
import static scouting2017.matchapp.R.id.oneLowGoal;
import static scouting2017.matchapp.R.id.submitButton;
import static scouting2017.matchapp.R.id.tenHighGoal;
import static scouting2017.matchapp.R.id.tenLowGoal;
import static scouting2017.matchapp.R.id.zeroHighGoal;
import static scouting2017.matchapp.R.id.zeroLowGoal;

public class thirdActivity extends AppCompatActivity {
    public Handler teleopTimer = new Handler();

    @Override
    public void onBackPressed() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        getSupportActionBar().setTitle(Integer.toString(FirstActivity.myAppVariables.robotNumber));
        if (FirstActivity.myAppVariables.allianceColor == true) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        } else {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
        }
        FirstActivity.myAppVariables.teleopTime = 135000;
        TextView teleopTimerText = (TextView) findViewById(R.id.teleopTimerText);
        Long timeRemaining = FirstActivity.myAppVariables.teleopTime / 1000;
        Long remainingMinutes = timeRemaining / 60;
        Long remainingSeconds = timeRemaining - (remainingMinutes * 60);
        if (remainingSeconds < 10) {
            teleopTimerText.setText(remainingMinutes + ":0" + remainingSeconds);
        } else {
            teleopTimerText.setText(remainingMinutes + ":" + remainingSeconds);
        }
        teleopTimer.postDelayed(updateTimer, 1000);
        toggleGoal();
        ImageButton hopperDumpedTeleop = (ImageButton)findViewById(R.id.hopperDumpedTeleop);
    }

    public void toEndOfGame(View view) {
        if (FirstActivity.myAppVariables.teleopTime < 120000) {
            Intent intent = new Intent(this, activity_fourth.class);
            startActivity(intent);
            teleopTimer.removeCallbacks(updateTimer);
        }
    }

    private final Runnable updateTimer = new Runnable() {
        public void run() {
            try {
                FirstActivity.myAppVariables.teleopTime -= 1000;
                TextView teleopTimerText = (TextView) findViewById(R.id.teleopTimerText);
                Long timeRemaining = FirstActivity.myAppVariables.teleopTime / 1000;
                Long remainingMinutes = timeRemaining / 60;
                Long remainingSeconds = timeRemaining - (remainingMinutes * 60);
                if (remainingSeconds < 10) {
                    teleopTimerText.setText(remainingMinutes + ":0" + remainingSeconds);
                } else {
                    teleopTimerText.setText(remainingMinutes + ":" + remainingSeconds);
                }
                if (FirstActivity.myAppVariables.teleopTime > 0) {
                    teleopTimer.postDelayed(this, 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    public void toggleAll () {
        ImageButton hopperDumpedTeleop = (ImageButton)findViewById(R.id.hopperDumpedTeleop);
        hopperDumpedTeleop.setEnabled (!hopperDumpedTeleop.isEnabled());
        if (!hopperDumpedTeleop.isEnabled()) {
            hopperDumpedTeleop.setAlpha(0.5f);
        } else {
            hopperDumpedTeleop.setAlpha(1.0f);
        }
        ImageButton ballsPickedUpHopper = (ImageButton)findViewById(R.id.ballsPickedUpHopper);
        ballsPickedUpHopper.setEnabled (!ballsPickedUpHopper.isEnabled());
        if (!ballsPickedUpHopper.isEnabled()){
            ballsPickedUpHopper.setAlpha(0.5f);
        }else{
            ballsPickedUpHopper.setAlpha(1.0f);
        }
        ImageButton gearPickedUpGround = (ImageButton)findViewById(R.id.gearPickedUpGround);
        gearPickedUpGround.setEnabled (!gearPickedUpGround.isEnabled());
        if (!gearPickedUpGround.isEnabled()){
            gearPickedUpGround.setAlpha(0.5f);
        }else{
            gearPickedUpGround.setAlpha(1.0f);
        }
        ImageButton gearPickedUpHuman = (ImageButton)findViewById(R.id.gearPickedUpHuman);
        gearPickedUpHuman.setEnabled (!gearPickedUpHuman.isEnabled());
        if (!gearPickedUpHuman.isEnabled()){
            gearPickedUpHuman.setAlpha(0.5f);
        }else{
            gearPickedUpHuman.setAlpha(1.0f);
        }
        ImageButton droppedGearTeleop = (ImageButton)findViewById(R.id.droppedGearTeleop);
        droppedGearTeleop.setEnabled (!droppedGearTeleop.isEnabled());
        if (!droppedGearTeleop.isEnabled()){
            droppedGearTeleop.setAlpha(0.5f);
        }else{
            droppedGearTeleop.setAlpha(1.0f);
        }
        ImageButton gearPlacedTeleop = (ImageButton)findViewById(R.id.gearPlacedTeleop);
        gearPlacedTeleop.setEnabled (!gearPlacedTeleop.isEnabled());
        if (!gearPlacedTeleop.isEnabled()){
            gearPlacedTeleop.setAlpha(0.5f);
        }else{
            gearPlacedTeleop.setAlpha(1.0f);
        }
        Button minusHopper = (Button)findViewById(R.id.minusHopper);
        minusHopper.setEnabled (!minusHopper.isEnabled());
        if (!minusHopper.isEnabled()){
            minusHopper.setAlpha(0.5f);
        }else{
            minusHopper.setAlpha(1.0f);
        }
        Button minusHopperBalls = (Button)findViewById(R.id.minusHopperBalls);
        minusHopperBalls.setEnabled (!minusHopperBalls.isEnabled());
        if (!minusHopperBalls.isEnabled()){
            minusHopperBalls.setAlpha(0.5f);
        }else{
            minusHopperBalls.setAlpha(1.0f);
        }
        Button minusGroundGear = (Button)findViewById(R.id.minusGroundGear);
        minusGroundGear.setEnabled (!minusGroundGear.isEnabled());
        if (!minusGroundGear.isEnabled()){
            minusGroundGear.setAlpha(0.5f);
        }else{
            minusGroundGear.setAlpha(1.0f);
        }
        Button minusHumanGear = (Button)findViewById(R.id.minusHumanGear);
        minusHumanGear.setEnabled (!minusHumanGear.isEnabled());
        if (!minusHumanGear.isEnabled()){
            minusHumanGear.setAlpha(0.5f);
        }else{
            minusHumanGear.setAlpha(1.0f);
        }
        Button minusDroppedGearTeleop = (Button)findViewById(R.id.minusDroppedGearTeleop);
        minusDroppedGearTeleop.setEnabled (!minusDroppedGearTeleop.isEnabled());
        if (!minusDroppedGearTeleop.isEnabled()){
            minusDroppedGearTeleop.setAlpha(0.5f);
        }else{
            minusDroppedGearTeleop.setAlpha(1.0f);
        }
        Button minusGearPlacedTeleop = (Button)findViewById(R.id.minusGearPlacedTeleop);
        minusGearPlacedTeleop.setEnabled (!minusGearPlacedTeleop.isEnabled());
        if (!minusGearPlacedTeleop.isEnabled()){
            minusGearPlacedTeleop.setAlpha(0.5f);
        }else{
            minusGearPlacedTeleop.setAlpha(1.0f);
        }
        Button approachBoiler = (Button)findViewById(R.id.approachBoiler);
        approachBoiler.setEnabled (!approachBoiler.isEnabled());
        if (!approachBoiler.isEnabled()){
            approachBoiler.setAlpha(0.5f);
        }else {
            approachBoiler.setAlpha(1.0f);
        }
        Button submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setEnabled (!submitButton.isEnabled());
        if (!submitButton.isEnabled()){
            submitButton.setAlpha(0.5f);
        }else{
            submitButton.setAlpha(1.0f);
        }
    }

    public void toggleButtons(){
        toggleAll();
        toggleGoal();
    }

    public void toggleGoal () {
        Button leaveBoiler = (Button)findViewById(R.id.leaveBoiler);
        leaveBoiler.setEnabled (!leaveBoiler.isEnabled());
        if (!leaveBoiler.isEnabled()) {
            leaveBoiler.setAlpha(0.5f);
        } else {
            leaveBoiler.setAlpha(1.0f);
        }
        Button zeroLowGoal = (Button)findViewById(R.id.zeroLowGoal);
        zeroLowGoal.setEnabled (!zeroLowGoal.isEnabled());
        if (!zeroLowGoal.isEnabled()){
            zeroLowGoal.setAlpha(0.5f);
        }else{
            zeroLowGoal.setAlpha(1.0f);
        }
        Button oneLowGoal = (Button)findViewById(R.id.oneLowGoal);
        oneLowGoal.setEnabled (!oneLowGoal.isEnabled());
        if (!oneLowGoal.isEnabled()){
            oneLowGoal.setAlpha(0.5f);
        }else{
            oneLowGoal.setAlpha(1.0f);
        }
        Button fiveLowGoal = (Button)findViewById(R.id.fiveLowGoal);
        fiveLowGoal.setEnabled(!fiveLowGoal.isEnabled());
        if (!fiveLowGoal.isEnabled()){
            fiveLowGoal.setAlpha(0.5f);
        }else{
            fiveLowGoal.setAlpha(1.0f);
        }
        Button tenLowGoal = (Button)findViewById(R.id.tenLowGoal);
        tenLowGoal.setEnabled (!tenLowGoal.isEnabled());
        if (!tenLowGoal.isEnabled()){
            tenLowGoal.setAlpha(0.5f);
        }else{
            tenLowGoal.setAlpha(1.0f);
        }
        Button zeroHighGoal = (Button)findViewById(R.id.zeroHighGoal);
        zeroHighGoal.setEnabled (!zeroHighGoal.isEnabled());
        if (!zeroHighGoal.isEnabled()){
            zeroHighGoal.setAlpha(0.5f);
        }else{
            zeroHighGoal.setAlpha(1.0f);
        }
        Button oneHighGoal = (Button)findViewById(R.id.oneHighGoal);
        oneHighGoal.setEnabled (!oneHighGoal.isEnabled());
        if (!oneHighGoal.isEnabled()){
            oneHighGoal.setAlpha(0.5f);
        }else{
            oneHighGoal.setAlpha(1.0f);
        }
        Button fiveHighGoal = (Button)findViewById(R.id.fiveHighGoal);
        fiveHighGoal.setEnabled (!fiveHighGoal.isEnabled());
        if (!fiveHighGoal.isEnabled()){
            fiveHighGoal.setAlpha(0.5f);
        }else{
            fiveHighGoal.setAlpha(1.0f);
        }
        Button tenHighGoal = (Button)findViewById(R.id.tenHighGoal);
        tenHighGoal.setEnabled (!tenHighGoal.isEnabled());
        if (!tenHighGoal.isEnabled()){
            tenHighGoal.setAlpha(0.5f);
        }else{
            tenHighGoal.setAlpha(1.0f);
        }
        Button minusLowGoalTeleop = (Button)findViewById(R.id.minusLowGoalTeleop);
        minusLowGoalTeleop.setEnabled (!minusLowGoalTeleop.isEnabled());
        if (!minusLowGoalTeleop.isEnabled()){
            minusLowGoalTeleop.setAlpha(0.5f);
        }else{
            minusLowGoalTeleop.setAlpha(1.0f);
        }
        Button minusHighGoalTeleop = (Button)findViewById(R.id.minusHighGoalTeleop);
        minusHighGoalTeleop.setEnabled (!minusHighGoalTeleop.isEnabled());
        if (!minusHighGoalTeleop.isEnabled()){
            minusHighGoalTeleop.setAlpha(0.5f);
        }else{
            minusHighGoalTeleop.setAlpha(1.0f);
        }
    }

    public void humanGearPickedUp(View view) {
        FirstActivity.myAppVariables.numberHumanGears++;
        TextView numberOfHumanGears = (TextView) findViewById(R.id.numberOfHumanGears);
        numberOfHumanGears.setText(Integer.toString(FirstActivity.myAppVariables.numberHumanGears));
        GameEvent humanGearPickedUpTeleop = new GameEvent();
        humanGearPickedUpTeleop.eventType = "humanGearPickedUp";
        humanGearPickedUpTeleop.eventValue = "1";
        humanGearPickedUpTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(humanGearPickedUpTeleop);
    }

    public void groundGearPickedUp(View view) {
        FirstActivity.myAppVariables.numberGroundGears++;
        TextView numberOfGroundGears = (TextView) findViewById(R.id.numberOfGroundGears);
        numberOfGroundGears.setText(Integer.toString(FirstActivity.myAppVariables.numberGroundGears));
        GameEvent groundGearPickedUpTeleop = new GameEvent();
        groundGearPickedUpTeleop.eventType = "groundGearPickedUp";
        groundGearPickedUpTeleop.eventValue = "1";
        groundGearPickedUpTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(groundGearPickedUpTeleop);
    }

    public void hopperBalls(View view) {
        FirstActivity.myAppVariables.numberHopperBalls++;
        TextView numberOfHopperBalls = (TextView) findViewById(R.id.numberOfHopperBalls);
        numberOfHopperBalls.setText(Integer.toString(FirstActivity.myAppVariables.numberHopperBalls));
        GameEvent hopperBallsTeleop = new GameEvent();
        hopperBallsTeleop.eventType = "hopperBalls";
        hopperBallsTeleop.eventValue = "1";
        hopperBallsTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(hopperBallsTeleop);
    }

    public void hopperDumpedTeleop(View view) {
        if (FirstActivity.myAppVariables.numberHoppersDumpedTeleop < 5) {
            FirstActivity.myAppVariables.numberHoppersDumpedTeleop++;
        } else {
            return;
        }
        TextView numberOfHoppersDumped = (TextView) findViewById(R.id.numberOfHoppersDumped);
        numberOfHoppersDumped.setText(Integer.toString(FirstActivity.myAppVariables.numberHoppersDumpedTeleop));
        GameEvent hopperDumpedTeleop = new GameEvent();
        hopperDumpedTeleop.eventType = "hopperDumpedTeleop";
        hopperDumpedTeleop.eventValue = "1";
        hopperDumpedTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(hopperDumpedTeleop);
    }

    public void gearPlacedTeleop(View view) {
        FirstActivity.myAppVariables.numberGearsPlaced++;
        TextView numberOfGearsPlaced = (TextView) findViewById(R.id.numberOfGearsPlaced);
        numberOfGearsPlaced.setText(Integer.toString(FirstActivity.myAppVariables.numberGearsPlaced));
        GameEvent gearPlacedTeleop = new GameEvent();
        gearPlacedTeleop.eventType = "gearPlacedTeleop";
        gearPlacedTeleop.eventValue = "1";
        gearPlacedTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(gearPlacedTeleop);
    }

    public void droppedGearTeleop(View view) {
        FirstActivity.myAppVariables.numberDroppedGears++;
        TextView numberOfDroppedGears = (TextView) findViewById(R.id.numberOfGearsDropped);
        numberOfDroppedGears.setText(Integer.toString(FirstActivity.myAppVariables.numberDroppedGears));
        GameEvent droppedGearTeleop = new GameEvent();
        droppedGearTeleop.eventType = "droppedGearTeleop";
        droppedGearTeleop.eventValue = "1";
        droppedGearTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(droppedGearTeleop);
    }

    public void approachBoiler(View view) {
        FirstActivity.myAppVariables.approachBoiler = true;
        GameEvent approachedBoiler = new GameEvent();
        approachedBoiler.eventType = "approachBoiler";
        approachedBoiler.eventValue = "1";
        approachedBoiler.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(approachedBoiler);
        toggleButtons();
    }

    public void leaveBoiler(View view) {
        FirstActivity.myAppVariables.leaveBoiler = true;
        GameEvent leftBoiler = new GameEvent();
        leftBoiler.eventType = "leaveBoiler";
        leftBoiler.eventValue = "1";
        leftBoiler.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(leftBoiler);
        toggleButtons();
    }

    public void lowGoal(View view) {
        // this is the button that was just pressed
        TextView lowGoalText = (TextView) findViewById(view.getId());
        // this is the text view that display the result
        TextView numberOfLowGoalsTeleop = (TextView) findViewById(R.id.numberOfLowGoalsTeleop);
        Integer lowGoalButtonValue = 0;
        if (!lowGoalText.getText().toString().equalsIgnoreCase("X")) {
            lowGoalButtonValue = Integer.parseInt(lowGoalText.getText().toString());
        }
        // increment my variables by the amount of goals just pressed
        FirstActivity.myAppVariables.numberLowGoalsTeleop += lowGoalButtonValue;
        // update the text view with the new value of goals scored
        numberOfLowGoalsTeleop.setText(Integer.toString(FirstActivity.myAppVariables.numberLowGoalsTeleop));

        GameEvent lowGoal = new GameEvent();
        lowGoal.eventType = "lowGoal";
        lowGoal.eventValue = lowGoalButtonValue.toString();
        lowGoal.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(lowGoal);
    }

    public void highGoal(View view) {
        //this is the button
        TextView highGoalText = (TextView) findViewById(view.getId());
        TextView numberOfHighGoalsTeleop = (TextView) findViewById(R.id.numberOfHighGoalsTeleop);
        Integer highGoalButtonValue = 0;
        if (!highGoalText.getText().toString().equalsIgnoreCase("X")) {
            highGoalButtonValue = Integer.parseInt(highGoalText.getText().toString());
        }
        FirstActivity.myAppVariables.numberHighGoalsTeleop += highGoalButtonValue;
        numberOfHighGoalsTeleop.setText(Integer.toString(FirstActivity.myAppVariables.numberHighGoalsTeleop));
        GameEvent highGoal = new GameEvent();
        highGoal.eventType = "highGoal";
        highGoal.eventValue = highGoalButtonValue.toString();
        highGoal.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(highGoal);
    }

    public void minusLowGoalTeleop(View view) {
        if (FirstActivity.myAppVariables.numberLowGoalsTeleop > 0) {
            FirstActivity.myAppVariables.numberLowGoalsTeleop--;
        }
        TextView numberOfLowGoalsTeleop = (TextView) findViewById(R.id.numberOfLowGoalsTeleop);
        numberOfLowGoalsTeleop.setText(Integer.toString(FirstActivity.myAppVariables.numberLowGoalsTeleop));
        GameEvent minusLowGoalTeleop = new GameEvent();
        minusLowGoalTeleop.eventType = "lowGoalsTeleop";
        minusLowGoalTeleop.eventValue = "1";
        minusLowGoalTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusLowGoalTeleop);
    }

    public void minusHighGoalTeleop(View view) {
        if (FirstActivity.myAppVariables.numberHighGoalsTeleop > 0) {
            FirstActivity.myAppVariables.numberHighGoalsTeleop--;
        }
        TextView numberOfHighGoalsTeleop = (TextView) findViewById(R.id.numberOfHighGoalsTeleop);
        numberOfHighGoalsTeleop.setText(Integer.toString(FirstActivity.myAppVariables.numberHighGoalsTeleop));
        GameEvent minusHighGoalsTeleop = new GameEvent();
        minusHighGoalsTeleop.eventType = "highGoalsTeleop";
        minusHighGoalsTeleop.eventValue = "1";
        minusHighGoalsTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusHighGoalsTeleop);
    }

    public void minusGroundGear(View view) {
        if (FirstActivity.myAppVariables.numberGroundGears > 0) {
            FirstActivity.myAppVariables.numberGroundGears--;
        }
        TextView numberOfGroundGears = (TextView) findViewById(R.id.numberOfGroundGears);
        numberOfGroundGears.setText(Integer.toString(FirstActivity.myAppVariables.numberGroundGears));
        GameEvent minusGroundGear = new GameEvent();
        minusGroundGear.eventType = "groundGearPickedUp";
        minusGroundGear.eventValue = "1";
        minusGroundGear.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusGroundGear);
    }

    public void minusHumanGear(View view) {
        if (FirstActivity.myAppVariables.numberHumanGears > 0) {
            FirstActivity.myAppVariables.numberHumanGears--;
        }
        TextView numberOfHumanGears = (TextView) findViewById(R.id.numberOfHumanGears);
        numberOfHumanGears.setText(Integer.toString(FirstActivity.myAppVariables.numberHumanGears));
        GameEvent minusHumanGear = new GameEvent();
        minusHumanGear.eventType = "humanGearPickedUp";
        minusHumanGear.eventValue = "1";
        minusHumanGear.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusHumanGear);
    }

    public void minusGearPlacedTeleop(View view) {
        if (FirstActivity.myAppVariables.numberGearsPlaced > 0) {
            FirstActivity.myAppVariables.numberGearsPlaced--;
        }
        TextView numberOfGearsPlaced = (TextView) findViewById(R.id.numberOfGearsPlaced);
        numberOfGearsPlaced.setText(Integer.toString(FirstActivity.myAppVariables.numberGearsPlaced));
        GameEvent minusGearPlacedTeleop = new GameEvent();
        minusGearPlacedTeleop.eventType = "gearPlacedTeleop";
        minusGearPlacedTeleop.eventValue = "1";
        minusGearPlacedTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusGearPlacedTeleop);
    }

    public void minusDroppedGearTeleop(View view) {
        if (FirstActivity.myAppVariables.numberDroppedGears > 0) {
            FirstActivity.myAppVariables.numberDroppedGears--;
        }
        TextView numberOfGearsDropped = (TextView) findViewById(R.id.numberOfGearsDropped);
        numberOfGearsDropped.setText(Integer.toString(FirstActivity.myAppVariables.numberDroppedGears));
        GameEvent minusDroppedGearTeleop = new GameEvent();
        minusDroppedGearTeleop.eventType = "droppedGearTeleop";
        minusDroppedGearTeleop.eventValue = "1";
        minusDroppedGearTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusDroppedGearTeleop);
    }

    public void minusHopperTeleop(View view) {
        if (FirstActivity.myAppVariables.numberHoppersDumpedTeleop > 0) {
            FirstActivity.myAppVariables.numberHoppersDumpedTeleop--;
        }
        TextView numberOfHoppersDumped = (TextView) findViewById(R.id.numberOfHoppersDumped);
        numberOfHoppersDumped.setText(Integer.toString(FirstActivity.myAppVariables.numberHoppersDumpedTeleop));
        GameEvent minusHopperTeleop = new GameEvent();
        minusHopperTeleop.eventType = "hopperDumpedTeleop";
        minusHopperTeleop.eventValue = "1";
        minusHopperTeleop.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusHopperTeleop);
    }

    public void minusHopperBalls(View view) {
        if (FirstActivity.myAppVariables.numberHopperBalls > 0) {
            FirstActivity.myAppVariables.numberHopperBalls--;
        }
        TextView numberOfHopperBalls = (TextView) findViewById(R.id.numberOfHopperBalls);
        numberOfHopperBalls.setText(Integer.toString(FirstActivity.myAppVariables.numberHopperBalls));
        GameEvent minusHopperBalls = new GameEvent();
        minusHopperBalls.eventType = "hopperBalls";
        minusHopperBalls.eventValue = "1";
        minusHopperBalls.eventTime = System.currentTimeMillis();
        FirstActivity.myAppVariables.eventList.add(minusHopperBalls);
    }
}