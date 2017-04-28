package scouting2017.matchapp.lib;

public class BluetoothManager {

    private BluetoothManager mManager;

    private BluetoothServer mServer;

    public synchronized BluetoothManager getBluetoothManager() {
        if (mManager == null) {
            mManager = new BluetoothManager();
        }

        return mManager;
    }
}