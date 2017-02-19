package scouting2017.matchapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static scouting2017.matchapp.R.id.numberOfGearsPickedUpText;
import static scouting2017.matchapp.R.id.numberOfGearsPlacedText;
import static scouting2017.matchapp.R.id.numberOfGroundBalls;
import static scouting2017.matchapp.R.id.numberOfGroundGears;
import static scouting2017.matchapp.R.id.numberOfHighGoalsText;
import static scouting2017.matchapp.R.id.numberOfHopperBalls;
import static scouting2017.matchapp.R.id.numberOfHoppersDumped;
import static scouting2017.matchapp.R.id.numberOfHoppersDumpedText;
import static scouting2017.matchapp.R.id.numberOfHumanGears;
import static scouting2017.matchapp.R.id.numberOfLowGoalsText;

/**
 * Created by mcgrathg19 on 1/18/2017.
 */

public class Variables {
    public int numberGearsPickedUpAuto;
    public int numberGearsPlacedAuto;
    public int numberDroppedGearsAuto;
    public int numberLowGoalsAuto;
    public int numberHighGoalsAuto;
    public int numberHoppersDumpedAuto;
    public int numberHumanGears;
    public int numberGroundGears;
    public int numberOfGearsPlaced;
    public int numberOfGearsDropped;
    public int numberHopperBalls;
    public int numberHoppersDumpedTeleop;
    public int numberGroundBalls;
    public List<GameEvent> eventList;
    public long startAutoTime;
    public long autoTime;
    public boolean approachBoiler;
    public boolean leaveBoiler;
    public int robotNumber;
    public int matchNumber;
    public String scouterName;
    public String competitionName;
    public String notes;
    public boolean climbed;
    public int droppedGearTeleop;

    public Variables() {
        reset();
    }
    public void reset() {
        numberGearsPickedUpAuto = 0;
        numberGearsPlacedAuto = 0;
        numberDroppedGearsAuto = 0;
        numberLowGoalsAuto = 0;
        numberHighGoalsAuto = 0;
        numberHoppersDumpedAuto = 0;
        numberHumanGears = 0;
        numberGroundGears = 0;
        numberOfGearsPlaced = 0;
        numberOfGearsDropped = 0;
        numberHopperBalls = 0;
        numberHoppersDumpedTeleop = 0;
        numberGroundBalls = 0;
        droppedGearTeleop = 0;
        eventList = new ArrayList<GameEvent>();
    }

    /* some random code Olivia has
     public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    } */

    public File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), albumName) ;
        if (!file.mkdirs()) {
            Log.e("ERROR", "directory NOT Created");
        }
        return file;
    }

    void CSVCreate(Activity theActivity) {
        String fileName = competitionName + "-" + matchNumber + "-" + robotNumber + "-" + scouterName.trim() + "-" +
                ".CSV";
        File directory = getAlbumStorageDir("/FRC2017");
        File file = new File(directory,fileName);
        try {
            FileWriter writer = new FileWriter(file);
            String lineOne = competitionName + "," + matchNumber + "," + robotNumber + "," + scouterName.trim() ;

            writer.write(lineOne + "\n");

            for (int c = 0; c < eventList.size(); c++) {
                String output = "";
                Long timeSinceStart = (eventList.get(c).eventTime - startAutoTime)/1000 ;
                output = timeSinceStart + "," +
                        eventList.get(c).eventType + "," +
                        eventList.get(c).eventValue;

                writer.write(output + "\n");

            }

            writer.close();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            theActivity.startActivityForResult(intent, 0);
        } catch (IOException e) {
            Log.e("ERROR", "File NOT Created", e);
        }
    }
}


