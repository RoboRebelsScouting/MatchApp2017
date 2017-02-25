package scouting2017.matchapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class thirdActivity extends AppCompatActivity {
    public Handler teleopTimer = new Handler() ;
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
        FirstActivity.myAppVariables.teleopTime = 135000 ;
        TextView teleopTimerText = (TextView) findViewById(R.id.teleopTimerText) ;
        Long timeRemaining = FirstActivity.myAppVariables.teleopTime/1000 ;
        Long remainingMinutes = timeRemaining/60 ;
        Long remainingSeconds = timeRemaining-(remainingMinutes*60) ;
        if (remainingSeconds<10) {
            teleopTimerText.setText(remainingMinutes + ":0" + remainingSeconds);
        } else {
            teleopTimerText.setText(remainingMinutes + ":" + remainingSeconds);
        }
        teleopTimer.postDelayed(updateTimer, 1000);
    }

    public void toEndOfGame(View view) {
        Intent intent = new Intent(this, activity_fourth.class);
        startActivity(intent);
        teleopTimer.removeCallbacks(updateTimer);
    }

    private final Runnable updateTimer = new Runnable() {
        public void run() {
            try {
                FirstActivity.myAppVariables.teleopTime -= 1000;
                TextView teleopTimerText = (TextView) findViewById(R.id.teleopTimerText);
                Long timeRemaining = FirstActivity.myAppVariables.teleopTime/1000 ;
                Long remainingMinutes = timeRemaining/60 ;
                Long remainingSeconds = timeRemaining-(remainingMinutes*60) ;
                if (remainingSeconds<10) {
                    teleopTimerText.setText(remainingMinutes + ":0" + remainingSeconds);
                } else {
                    teleopTimerText.setText(remainingMinutes + ":" + remainingSeconds);
                }
                if (FirstActivity.myAppVariables.teleopTime > 0) {
                    teleopTimer.postDelayed (this, 1000) ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } ;
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

        public void groundGearPickedUp (View view) {
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
        }

        public void leaveBoiler(View view) {
            FirstActivity.myAppVariables.leaveBoiler = true;
            GameEvent leftBoiler = new GameEvent();
            leftBoiler.eventType = "leaveBoiler";
            leftBoiler.eventValue = "1";
            leftBoiler.eventTime = System.currentTimeMillis();
            FirstActivity.myAppVariables.eventList.add(leftBoiler);
        }

        public void lowGoal(View view) {
            // this is the button that was just pressed
            TextView lowGoalText = (TextView) findViewById(view.getId());

            // this is the text view that display the result
            TextView numberOfLowGoalsTeleop = (TextView) findViewById(R.id.numberOfLowGoalsTeleop);

            // increment my variables by the amount of goals just pressed
            FirstActivity.myAppVariables.numberLowGoalsTeleop += Integer.parseInt(lowGoalText.getText().toString()) ;

            // update the text view with the new value of goals scored
            numberOfLowGoalsTeleop.setText(Integer.toString(FirstActivity.myAppVariables.numberLowGoalsTeleop));

            GameEvent lowGoal = new GameEvent();
            lowGoal.eventType = "lowGoal";
            lowGoal.eventValue = lowGoalText.getText().toString();
            lowGoal.eventTime = System.currentTimeMillis();
            FirstActivity.myAppVariables.eventList.add(lowGoal);
        }

        public void highGoal(View view) {
            TextView highGoalText = (TextView) findViewById(view.getId());
            TextView numberOfHighGoalsTeleop = (TextView) findViewById(R.id.numberOfHighGoalsTeleop);
            FirstActivity.myAppVariables.numberHighGoalsTeleop += Integer.parseInt(highGoalText.getText().toString()) ;
            numberOfHighGoalsTeleop.setText(Integer.toString(FirstActivity.myAppVariables.numberHighGoalsTeleop));
            GameEvent highGoal = new GameEvent();
            highGoal.eventType = "highGoal";
            highGoal.eventValue = highGoalText.getText().toString();
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
