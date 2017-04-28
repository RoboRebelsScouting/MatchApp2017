package scouting2017.matchapp.lib;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

public class BluetoothServer {

    public static final int STATE_CONNECTED = 0;
    public static final int STATE_DISCONNECTED = 1;

    private static final String TAG = "BluetoothServer";

    private final BluetoothDevice mDevice;
    private BluetoothManager mParentService;
    private ServerHandlerThread mThread;
    private int mState;
    private ArrayList<ClientStateChangeListener> mStateListeners = new ArrayList<>();
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message inputMessage) {
            ServerHandlerTask task = (ServerHandlerTask) inputMessage.obj;
            switch (inputMessage.what) {
                case ServerHandlerTask.EVENT_SOCKET_DISCONNECTED:
                    notifyDisconnect();
                    break;
                case ServerHandlerTask.EVENT_SOCKET_DISCONNECT:
                    disconnect();
                    break;
            }
        }
    };

    public BluetoothServer(BluetoothSocket socket, BluetoothManager service) {
        mDevice = socket.getRemoteDevice();
        mParentService = service;
        initHandlerThread(socket);
    }

    public BluetoothDevice getBluetoothDevice() {
        return mDevice;
    }

    public int getState() {
        return mState;
    }

    public void addClientStateChangeListener(ClientStateChangeListener listener) {
        mStateListeners.add(listener);
    }

    public void removeClientStateChangeListener(ClientStateChangeListener listener) {
        mStateListeners.remove(listener);
    }

    public void disconnect() {
        mThread.stopLoop();
        mParentService.removeClient(this);
    }

    private void notifyDisconnect() {
        mState = STATE_DISCONNECTED;
        for (ClientStateChangeListener listener : mStateListeners) {
            listener.onDisconnected(this);
        }
    }

    public void setNewBluetoothSocket(BluetoothSocket socket) {
        mThread.stopLoop();
        initHandlerThread(socket);
    }

    private void initHandlerThread(BluetoothSocket socket) {
        mThread = new ServerHandlerThread(this, socket);
        mThread.start();
        mState = STATE_CONNECTED;
        for (ClientStateChangeListener listener : mStateListeners) {
            listener.onConnected(this);
        }
    }

    void handleEvent(ServerHandlerTask task, int state) {
        Message eventMessage = mHandler.obtainMessage(state, task);
        eventMessage.sendToTarget();
    }

    public interface ClientStateChangeListener {
        void onConnected(BluetoothServer client);
        void onDisconnected(BluetoothServer client);
    }
}