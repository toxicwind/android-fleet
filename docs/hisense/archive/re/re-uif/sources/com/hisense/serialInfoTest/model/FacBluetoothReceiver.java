package com.hisense.serialInfoTest.model;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.hisense.serialInfoTest.util.HexData;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacParamManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FacBluetoothReceiver extends BroadcastReceiver {
    private Context mContext;
    private Map<String, String> mDevicesList = new HashMap();
    private Map<String, Integer> mDeviceName = new HashMap();
    private String mBlueName = null;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i("Fac_FacBluetoothReceiver", "onReceive action = " + action);
        if (action.equals("com.hisense.fac.HISENSE_FAC_SERIAL_START_ACTION")) {
            if (context != null) {
                this.mContext = context;
            }
            this.mBlueName = intent.getStringExtra("fac_bluetooth_name_serial").toLowerCase();
            Log.i("Fac_FacBluetoothReceiver", "mBlueName = " + this.mBlueName);
            checkDevice();
        }
        if ("android.bluetooth.device.action.FOUND".equals(action) || "android.bluetooth.device.action.BOND_STATE_CHANGED".equals(action)) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            Log.i("Fac_FacBluetoothReceiver", "bluetooth device = " + (bluetoothDevice.getName() + "," + bluetoothDevice.getAddress()));
            this.mDevicesList.put(bluetoothDevice.getAddress(), bluetoothDevice.getName());
            short s = intent.getExtras().getShort("android.bluetooth.device.extra.RSSI");
            String name = bluetoothDevice.getName();
            if (name != null && !name.isEmpty()) {
                name = name.toLowerCase();
                FacParamManager.mBlueResult = bluetoothDevice.getName() + "," + bluetoothDevice.getAddress() + "," + ((int) s);
            }
            Log.i("Fac_FacBluetoothReceiver", "bluetooth deviceName = " + name);
            this.mDeviceName.put(name, Integer.valueOf(Math.abs((int) s)));
            String str = this.mBlueName;
            if (str != null && !str.isEmpty() && this.mBlueName.equals(name)) {
                setResultToSerialServer(Math.abs((int) s));
                return;
            }
            checkDevice();
        }
        Log.i("Fac_FacBluetoothReceiver", "onReceive end = " + action);
    }

    private boolean checkDevice() {
        String str = this.mBlueName;
        if (str == null || str.isEmpty()) {
            Log.i("Fac_FacBluetoothReceiver", "mBlueName is null");
            return false;
        }
        Map<String, Integer> map = this.mDeviceName;
        if (map == null || map.isEmpty()) {
            Log.i("Fac_FacBluetoothReceiver", "checkDevice no device");
            return false;
        }
        Log.i("Fac_FacBluetoothReceiver", "checkDevice mDeviceName size = " + this.mDeviceName.size());
        if (this.mDeviceName.isEmpty() || !this.mDeviceName.containsKey(this.mBlueName)) {
            return false;
        }
        setResultToSerialServer(this.mDeviceName.get(this.mBlueName).intValue());
        return true;
    }

    private void setResultToSerialServer(int i) {
        Log.i("Fac_FacBluetoothReceiver", "setResultToSerialServer rssi = " + i);
        String strIntegerToHexString = HexData.integerToHexString(i);
        if (strIntegerToHexString.length() < 2) {
            strIntegerToHexString = "0" + strIntegerToHexString;
        }
        byte[] bArrHexStringToBytes2 = HexData.hexStringToBytes2(strIntegerToHexString);
        FacFunction_Adapter.getInstance(this.mContext).writeToExternalControl(-1306394624, bArrHexStringToBytes2, bArrHexStringToBytes2.length);
    }
}
