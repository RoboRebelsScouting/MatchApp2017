package scouting2017.matchapp;

import android.app.Activity;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static scouting2017.matchapp.R.id.numberOfGearsPlacedText;
import static scouting2017.matchapp.R.id.numberOfGroundBalls;
import static scouting2017.matchapp.R.id.numberOfGroundGears;
import static scouting2017.matchapp.R.id.numberOfHighGoalsAuto;
import static scouting2017.matchapp.R.id.numberOfHopperBalls;
import static scouting2017.matchapp.R.id.numberOfHoppersDumped;
import static scouting2017.matchapp.R.id.numberOfHoppersDumpedText;
import static scouting2017.matchapp.R.id.numberOfHumanGears;
import static scouting2017.matchapp.R.id.numberOfLowGoalsAuto;

/**
 * Created by mcgrathg19 on 1/18/2017.
 */

public class Variables {

    //public Intent firstActivity;
    public int numberGearsPlacedAuto;
    public int numberDroppedGearsAuto;
    public int numberLowGoalsAuto;
    public int numberHighGoalsAuto;
    public int numberHoppersDumpedAuto;
    public int numberHumanGears;
    public int numberGroundGears;
    public int numberGearsPlaced;
    public int numberDroppedGears;
    public int numberHopperBalls;
    public int numberHoppersDumpedTeleop;
    public List<GameEvent> eventList;
    public long startAutoTime;
    public long autoTime;
    public boolean timerStarted = false;
    public long startTeleopTime;
    public long teleopTime;
    public boolean approachBoiler;
    public boolean leaveBoiler;
    public int robotNumber;
    public boolean allianceColor = false;
    public int matchNumber;
    public String scouterName;
    public String competitionName;
    public int droppedGearTeleop;
    public int numberHighGoalsTeleop;
    public int numberLowGoalsTeleop;

    public Variables() {
        reset();
    }
    public void reset() {
        numberGearsPlacedAuto = 0;
        numberDroppedGearsAuto = 0;
        numberLowGoalsAuto = 0;
        numberHighGoalsAuto = 0;
        numberHoppersDumpedAuto = 0;
        numberHumanGears = 0;
        numberGroundGears = 0;
        numberGearsPlaced = 0;
        numberDroppedGears = 0;
        numberHopperBalls = 0;
        numberHoppersDumpedTeleop = 0;
        droppedGearTeleop = 0;
        eventList = new ArrayList<GameEvent>();
        scouterName = new String () ;
        competitionName = new String() ;
        allianceColor = false ;
        matchNumber = 0 ;
        numberHighGoalsTeleop = 0;
        numberLowGoalsTeleop = 0;
        timerStarted = false;
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

    ///////////////////////////////////////////////////////////////////////
    // not used - attempt to send file directly using bluetooth instead of
    // using share activity
    void blueToothSend(Activity theActivity, File file) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(theActivity.getApplicationContext(), "Your device does not support bluetooth", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                theActivity.startActivityForResult(enableBtIntent, 1);
            } else {
                Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

                if (pairedDevices.size() > 0) {
                    // There are paired devices. Get the name and address of each paired device.
                    for (BluetoothDevice device : pairedDevices) {
                        String deviceName = device.getName();
                        String deviceHardwareAddress = device.getAddress(); // MAC address
                        if (deviceName.equalsIgnoreCase("SCOUTINGLAPTOP2")) {
                            //Toast.makeText(theActivity.getApplicationContext(), "paired with scoutinglaptop2", Toast.LENGTH_LONG).show();
                            try {

                                //UUID DEFAULT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
                                //BluetoothSocket bsock = device.createRfcommSocketToServiceRecord(UUID.randomUUID());
                                //BluetoothSocket bsock = device.createInsecureRfcommSocketToServiceRecord(DEFAULT_UUID);
                                try {
                                    BluetoothSocket bsock = (BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(device,1);

                                    bsock.connect();

                                    OutputStream os = bsock.getOutputStream();

                                    byte[] bytes = new byte[(int)file.length()];

                                    try {
                                        BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                                        buf.read(bytes, 0, bytes.length);
                                        buf.close();

                                        os.write(bytes);

                                    } catch (FileNotFoundException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }


                                } catch (NoSuchMethodException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            return;
        }
    }
    void CSVCreate(Activity theActivity) {
        String fileName = competitionName + "-" + matchNumber + "-" + robotNumber + "-" + scouterName.trim() + "-" +
                ".csv";
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

            boolean useBluetoothActivity = true;
            if (useBluetoothActivity == true) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                theActivity.startActivityForResult(intent, 0);
            } else {
                blueToothSend(theActivity,file);
            }
        } catch (IOException e) {
            Log.e("ERROR", "File NOT Created", e);
        }
    }
}


