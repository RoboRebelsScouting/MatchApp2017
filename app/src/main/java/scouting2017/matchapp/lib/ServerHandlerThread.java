package scouting2017.matchapp.lib;

import android.bluetooth.BluetoothSocket;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ServerHandlerThread extends Thread {

    private static final String TAG = "ServerHandlerThread";

    private static final String SOCKET_DISCONNECTED_MESSAGE = "bt socket closed, read return: -1";

    private static final short MESSAGE_FILE = 1;
    private static final short MESSAGE_SCOUT_CHANGE = 2;
    private static final short MESSAGE_SCOUT_SET = 3;
    private static final short MESSAGE_TEAM_CHANGE = 4;
    private static final short MESSAGE_TEAM_SET = 5;
    private static final short MESSAGE_TEAM_REQUEST = 6;

    private BluetoothServer mServer;
    private BluetoothSocket mSocket;
    private InputStream mInputStream;
    private OutputStream mOutputStream;

    public ServerHandlerThread(BluetoothServer server, BluetoothSocket socket) {
        mServer = server;
        setSocket(socket);
    }

    private void setSocket(BluetoothSocket socket) {
        mSocket = socket;

        try {
            mInputStream = socket.getInputStream();
        } catch (IOException e) {
            Log.e(TAG, "Couldn't create InputStream");
            e.printStackTrace();
        }

        try {
            mOutputStream = socket.getOutputStream();
        } catch (IOException e) {
            Log.e(TAG, "Couldn't create OutputStream");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            mSocket.connect();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (!interrupted()) {
            try {
                // Read first to bytes as message type header
                short msgType = readShort();
                Log.v(TAG, "Read Header from incoming message: " + msgType);

                // Read following bytes based on message type
                switch (msgType) {
                    // TODO: Process incomng messages here
                }
            } catch (IOException e) {
                if (e.getMessage().equals(SOCKET_DISCONNECTED_MESSAGE)) {
                    mServer.handleEvent(null, ServerHandlerTask.EVENT_SOCKET_DISCONNECTED);
                } else {
                    mServer.handleEvent(null, ServerHandlerTask.EVENT_SOCKET_DISCONNECT);
                    Log.e(TAG, "IOException occurred, deliberately dropping connection with " +
                            "client");
                }
                e.printStackTrace();
                break;
            }
        }
        Log.v(TAG, "Loop broken");
    }

    void stopLoop() {
        interrupt();

        try {
            mInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte readByte() throws IOException {
        byte[] byteTmp = new byte[1];
        mInputStream.read(byteTmp);
        return byteTmp[0];
    }

    private short readShort() throws IOException {
        byte[] shortTmp = new byte[2];
        mInputStream.read(shortTmp);
        ByteBuffer bb = ByteBuffer.wrap(shortTmp);
        return bb.getShort();
    }

    private void writeShort(short in) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.putShort(in);
        mOutputStream.write(bb.array());
    }

    private int readInt() throws IOException {
        byte[] intTmp = new byte[4];
        mInputStream.read(intTmp);
        ByteBuffer bb = ByteBuffer.wrap(intTmp);
        return bb.getInt();
    }

    private void writeInt(int in) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(in);
        mOutputStream.write(bb.array());
    }
}