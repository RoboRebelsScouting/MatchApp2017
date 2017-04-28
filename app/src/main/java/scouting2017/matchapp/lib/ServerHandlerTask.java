package scouting2017.matchapp.lib;

public class ServerHandlerTask {

    public static final int EVENT_SOCKET_DISCONNECTED = 0;
    /**
     * Ask the BluetoothServer to call the disconnect method, used to deliberately drop connections
     * from the ServerHandlerThread if an exception or other error occurs
     */
    public static final int EVENT_SOCKET_DISCONNECT = 1;
}