package com.hisense.uifac.network.adapter;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacParamManager;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import com.hisense.uifac.menu.commonview.MenuSetUpListView;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.hisense.uifac.menu.util.MenuDataContainer;
import com.hisense.uifac.network.adapter.WifiAdmin.WireLessAdapter;
import com.hisense.uifac.network.adapter.WifiAdmin.WireLessSAAdapter;
import com.mediatek.twoworlds.tv.common.MtkTvConfigTypeBase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class WifiBluetoochCombinedDialog extends Dialog implements AdapterView.OnItemClickListener {
    public static BluetoothAdapter mBluetoothAdapter;
    public static BluetoothSocket mBluetoothSocket;
    private static BluetoothScanner mScanner;
    ListView BTDevicesListView;
    private MenuSetUpListView BlueToothDevicesListView;
    BluetoothSingleItemAdapter DevicesAdapter;
    List<String> DevicesList;
    IntentFilter intent;
    boolean isFirstReceiveWifiResult;
    private TextView mBluethothTextView;
    Context mContext;
    private NetworkListView mF7listview;
    FacFunction_Adapter mFacFunction_Adapter;
    private List<SetConfigListViewAdapter.DataItem> mGroup_BlueToothList;
    private List<SetConfigListViewAdapter.DataItem> mGroup_F7network;
    IntentFilter mItendFiliter;
    private SetConfigListViewAdapter mListViewAdpter_Bluetooth;
    private SetConfigListViewAdapter mListViewAdpter_F7network;
    MenuConfigManager mMenuConfigManager;
    MenuDataContainer mMenuDataContainer;
    private SharedPreferences mSharedPreferences;
    Handler mWifiActivityHandler;
    WifiAdmin mWifiAdmin;
    WifiBroadcastReceiver mWifiBroadcastReceiver;
    private TextView mWifiMacTextView;
    ListView mWifilistview;
    FacWifiAdapter mwirelessadapter;
    private final BroadcastReceiver searchDevicesBroadcastReceiver;
    public static List<ScanResult> mWirelessList = new ArrayList();
    static boolean isConnectionInProgress = false;

    private void dismissingWifiDialogPart() {
        Message message = new Message();
        message.what = 0;
        this.mWifiActivityHandler.removeMessages(0);
        this.mWifiActivityHandler.sendMessage(message);
    }

    private void dismissingBluetoothDialogPart() {
        mScanner.pause();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        dismissingWifiDialogPart();
        dismissingBluetoothDialogPart();
        super.dismiss();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 || i == 82 || i == 4212 || i == 4357) {
            dismiss();
            return true;
        }
        if (i == 4390 || i == 4252) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void initializeWifiFields(Context context, int i, Handler handler) {
        this.mListViewAdpter_F7network = new SetConfigListViewAdapter(this.mContext);
        this.mWifiAdmin = WifiAdmin.getinstance(this.mContext);
        IntentFilter intentFilter = new IntentFilter();
        this.mItendFiliter = intentFilter;
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.mItendFiliter.addAction("android.net.wifi.STATE_CHANGE");
        this.mItendFiliter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.mItendFiliter.addAction("android.net.conn.BACKGROUND_DATA_SETTING_CHANGED");
        this.mWifiBroadcastReceiver = new WifiBroadcastReceiver();
        new keyinput(this);
        this.mWifiActivityHandler = handler;
        Log.i("Fac_WifiBluetoochCombinedDialog", "initialzeWifiFields finished.");
    }

    private void initialzeBluetoothFields(Context context, int i, Handler handler) {
        this.mListViewAdpter_Bluetooth = new SetConfigListViewAdapter(this.mContext);
        mScanner = new BluetoothScanner();
        Log.i("Fac_WifiBluetoochCombinedDialog", "initialzeBluetoothFields finished.");
    }

    public WifiBluetoochCombinedDialog(Context context, int i, Handler handler) {
        super(context, i);
        this.isFirstReceiveWifiResult = true;
        this.DevicesList = new ArrayList();
        this.mSharedPreferences = null;
        this.searchDevicesBroadcastReceiver = new BroadcastReceiver() { // from class: com.hisense.uifac.network.adapter.WifiBluetoochCombinedDialog.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                Bundle extras = intent.getExtras();
                for (Object obj : extras.keySet().toArray()) {
                    Log.i("Fac_WifiBluetoochCombinedDialog", "keyName=" + String.valueOf(extras.get(obj.toString())));
                }
                if ("android.bluetooth.device.action.FOUND".equals(action) || "android.bluetooth.device.action.BOND_STATE_CHANGED".equals(action)) {
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    String str = bluetoothDevice.getName() + "," + bluetoothDevice.getAddress() + "," + ((int) intent.getExtras().getShort("android.bluetooth.device.extra.RSSI"));
                    Log.i("Fac_WifiBluetoochCombinedDialog", "device.getName()==" + str);
                    WifiBluetoochCombinedDialog.this.mSharedPreferences.edit().putBoolean(WifiBluetoochCombinedDialog.this.mContext.getResources().getString(R.string.str_test_info_bt), true).commit();
                    if (!WifiBluetoochCombinedDialog.this.isSameBluetoothDevice(bluetoothDevice.getAddress())) {
                        WifiBluetoochCombinedDialog.this.DevicesList.add(str);
                    }
                    WifiBluetoochCombinedDialog wifiBluetoochCombinedDialog = WifiBluetoochCombinedDialog.this;
                    List<String> list = wifiBluetoochCombinedDialog.DevicesList;
                    WifiBluetoochCombinedDialog.sortbysinal(list);
                    wifiBluetoochCombinedDialog.DevicesList = list;
                    Log.i("Fac_WifiBluetoochCombinedDialog", "lstDevices" + WifiBluetoochCombinedDialog.this.DevicesList);
                    FacParamManager.mBlueResult = WifiBluetoochCombinedDialog.this.DevicesList.get(0);
                    WifiBluetoochCombinedDialog.this.DevicesAdapter.notifyDataSetChanged();
                }
            }
        };
        this.mContext = context;
        this.mMenuConfigManager = MenuConfigManager.getInstance(context);
        this.mMenuDataContainer = MenuDataContainer.getInstance(this.mContext);
        this.mFacFunction_Adapter = FacFunction_Adapter.getInstance(this.mContext);
        this.mSharedPreferences = this.mContext.getSharedPreferences("FacShareP", 0);
        initializeWifiFields(context, i, handler);
        initialzeBluetoothFields(context, i, handler);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void onCreateWifiPart() {
        this.mF7listview = (NetworkListView) findViewById(R.id.facmenu_wifi_bluetooth_wifi_sublistView);
        this.mMenuDataContainer.loadNetworkSetting();
        this.mF7listview.initData(this.mMenuDataContainer.getFacDataGroup_NetworkSetting(), 6, null, 0);
        List currentList = this.mF7listview.getCurrentList();
        this.mGroup_F7network = currentList;
        this.mListViewAdpter_F7network.setmGroup(currentList);
        this.mF7listview.setAdapter((ListAdapter) this.mListViewAdpter_F7network);
        this.mF7listview.setFocusable(false);
        this.mF7listview.setFocusableInTouchMode(false);
        TextView textView = (TextView) findViewById(R.id.facmenu_wifi_bluetooth_blue_mac);
        this.mWifiMacTextView = textView;
        textView.setText(getWifiMacAddress());
        this.mWifilistview = (ListView) findViewById(R.id.facmenu_wifi_bluetooth_wifi_wifiaplistview);
        if (FacPropertyDemo.HIFAC_MARKET_SA) {
            WifiAdmin wifiAdmin = this.mWifiAdmin;
            Objects.requireNonNull(wifiAdmin);
            this.mwirelessadapter = wifiAdmin.new WireLessSAAdapter(mWirelessList, this.mContext);
        } else {
            WifiAdmin wifiAdmin2 = this.mWifiAdmin;
            Objects.requireNonNull(wifiAdmin2);
            this.mwirelessadapter = wifiAdmin2.new WireLessAdapter(mWirelessList, this.mContext);
        }
        this.mWifilistview.setAdapter((ListAdapter) this.mwirelessadapter);
        this.mWifilistview.setOnItemClickListener(this);
        this.mWifilistview.setFocusable(false);
        this.mWifilistview.setFocusableInTouchMode(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void onCreateBluetoothPart() {
        this.BlueToothDevicesListView = (MenuSetUpListView) findViewById(R.id.facmenu_wifi_bluetooth_bluetooth_sublistView);
        this.mMenuDataContainer.loadBlueToothSetting();
        this.BlueToothDevicesListView.initData(this.mMenuDataContainer.getFacDataGroup_BluetoothSetting(), 6, null, 0);
        List currentList = this.BlueToothDevicesListView.getCurrentList();
        this.mGroup_BlueToothList = currentList;
        this.mListViewAdpter_Bluetooth.setmGroup(currentList);
        this.BlueToothDevicesListView.setAdapter((ListAdapter) this.mListViewAdpter_Bluetooth);
        this.BlueToothDevicesListView.setVisibility(0);
        this.BlueToothDevicesListView.setFocusable(false);
        this.BlueToothDevicesListView.setFocusableInTouchMode(false);
        this.BTDevicesListView = (ListView) findViewById(R.id.facmenu_wifi_bluetooth_bluetooth_aplistview);
        BluetoothSingleItemAdapter bluetoothSingleItemAdapter = new BluetoothSingleItemAdapter(this.DevicesList, this.mContext);
        this.DevicesAdapter = bluetoothSingleItemAdapter;
        this.BTDevicesListView.setAdapter((ListAdapter) bluetoothSingleItemAdapter);
        this.BTDevicesListView.setOnItemClickListener(new ItemClickEvent());
        this.BTDevicesListView.setFocusable(false);
        this.BTDevicesListView.setFocusableInTouchMode(false);
        TextView textView = (TextView) findViewById(R.id.facmenu_wifi_bluetooth_wifi_mac);
        this.mBluethothTextView = textView;
        textView.setText(this.mMenuConfigManager.getBTMacAddress());
        IntentFilter intentFilter = new IntentFilter();
        this.intent = intentFilter;
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        this.intent.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        this.intent.addAction("android.bluetooth.adapter.action.SCAN_MODE_CHANGED");
        this.intent.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.DevicesList.clear();
        BluetoothAdapter bluetoothAdapter = mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
            for (int i = 0; i < bondedDevices.size(); i++) {
                Log.i("Fac_WifiBluetoochCombinedDialog", "device name = " + bondedDevices.iterator().next().getName());
            }
            if (mBluetoothAdapter.isDiscovering()) {
                Log.i("Fac_WifiBluetoochCombinedDialog", "is discovering, cancel then start discovery0");
                mBluetoothAdapter.cancelDiscovery();
                try {
                    Thread.currentThread();
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.i("Fac_WifiBluetoochCombinedDialog", "bluetoothStartDiscoveryOk ==" + mBluetoothAdapter.startDiscovery());
            Log.i("Fac_WifiBluetoochCombinedDialog", "onCreateBluetoothPart finished.");
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        Log.i("Fac_WifiBluetoochCombinedDialog", "wifi_blueTooth dialog.onCreate");
        super.onCreate(bundle);
        setContentView(R.layout.wifi_bluetooth_combined_dialog);
        setTitle(R.string.str_fac_wifi_bluetooth_combined);
        onCreateWifiPart();
        onCreateBluetoothPart();
        this.mMenuConfigManager.updateFacLogStatus(4096);
        this.mMenuConfigManager.updateFacLogStatus(8192);
        this.mMenuConfigManager.setLogToTvcertificate("show wifi blu");
    }

    private void onStartWifiPart() {
        this.mContext.registerReceiver(this.mWifiBroadcastReceiver, this.mItendFiliter);
        Log.i("Fac_WifiBluetoochCombinedDialog", "onStart");
        if (!this.mWifiAdmin.isWifiEnabled()) {
            this.mWifiAdmin.openWifi();
        }
        this.isFirstReceiveWifiResult = true;
        this.mSharedPreferences.edit().putBoolean(this.mContext.getResources().getString(R.string.str_test_info_wifi), true).commit();
        if (this.mWifiAdmin.isWifiEnabled()) {
            List<ScanResult> wifiList = this.mWifiAdmin.getWifiList();
            mWirelessList = wifiList;
            if (wifiList != null && wifiList.size() > 0) {
                List<ScanResult> list = mWirelessList;
                WifiAdmin.sortbysinal(list);
                mWirelessList = list;
                this.mwirelessadapter.setList(list);
                FacParamManager.mWifiResult = mWirelessList.get(0);
                FacWifiAdapter facWifiAdapter = this.mwirelessadapter;
                if (facWifiAdapter != null) {
                    facWifiAdapter.notifyDataSetChanged();
                    Log.i("Fac_WifiBluetoochCombinedDialog", "notifyDataSetChanged over");
                }
            }
            this.mWifiAdmin.startScan();
            return;
        }
        this.mMenuConfigManager.displayHisToast(this.mContext.getResources().getString(R.string.str_facmenu_bluetooth_is_not_enable) + "\n");
    }

    private void onStartBluetoothPart() {
        this.mContext.registerReceiver(this.searchDevicesBroadcastReceiver, this.intent);
        BluetoothAdapter bluetoothAdapter = mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.enable();
            Log.i("Fac_WifiBluetoochCombinedDialog", "mBluetoothAdapter.enable(); called.");
            mScanner.forceScan();
        }
        super.onStart();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        onStartWifiPart();
        onStartBluetoothPart();
        if (FacPropertyDemo.HIFAC_MARKET_SA) {
            this.mFacFunction_Adapter.setWifiBTStatusToLog();
        }
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        Log.i("Fac_WifiBluetoochCombinedDialog", "onStop");
        this.mContext.unregisterReceiver(this.mWifiBroadcastReceiver);
        this.mContext.unregisterReceiver(this.searchDevicesBroadcastReceiver);
        BluetoothAdapter bluetoothAdapter = mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
        }
        this.isFirstReceiveWifiResult = false;
    }

    public class WifiBroadcastReceiver extends BroadcastReceiver {
        public WifiBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i("Fac_WifiBluetoochCombinedDialog", "fac wifi action = " + intent.getAction());
            if (intent.getAction().equals("android.net.wifi.SCAN_RESULTS")) {
                WifiBluetoochCombinedDialog wifiBluetoochCombinedDialog = WifiBluetoochCombinedDialog.this;
                boolean z = wifiBluetoochCombinedDialog.isFirstReceiveWifiResult;
                if (z) {
                    wifiBluetoochCombinedDialog.isFirstReceiveWifiResult = !z;
                    Log.i("Fac_WifiBluetoochCombinedDialog", "GET AP:\n");
                    WifiBluetoochCombinedDialog.this.mSharedPreferences.edit().putBoolean(WifiBluetoochCombinedDialog.this.mContext.getResources().getString(R.string.str_test_info_wifi), true).commit();
                    WifiBluetoochCombinedDialog.mWirelessList = WifiBluetoochCombinedDialog.this.mWifiAdmin.getWifiList();
                    WifiBluetoochCombinedDialog.this.mWifilistview.setVisibility(0);
                    Log.e("Fac_WifiBluetoochCombinedDialog", "GET AP:\n" + WifiBluetoochCombinedDialog.mWirelessList);
                    if (WifiBluetoochCombinedDialog.mWirelessList.size() > 0) {
                        List<ScanResult> list = WifiBluetoochCombinedDialog.mWirelessList;
                        WifiAdmin.sortbysinal(list);
                        WifiBluetoochCombinedDialog.mWirelessList = list;
                        FacParamManager.mWifiResult = list.get(0);
                        WifiBluetoochCombinedDialog.this.mwirelessadapter.setList(WifiBluetoochCombinedDialog.mWirelessList);
                        FacWifiAdapter facWifiAdapter = WifiBluetoochCombinedDialog.this.mwirelessadapter;
                        if (facWifiAdapter != null) {
                            facWifiAdapter.notifyDataSetChanged();
                            Log.i("Fac_WifiBluetoochCombinedDialog", "notifyDataSetChanged over");
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE") || intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
                Log.i("Fac_WifiBluetoochCombinedDialog", intent.getAction());
                if (WifiBluetoochCombinedDialog.this.mWifiAdmin.isWifiEnabled()) {
                    List<ScanResult> wifiList = WifiBluetoochCombinedDialog.this.mWifiAdmin.getWifiList();
                    WifiBluetoochCombinedDialog.mWirelessList = wifiList;
                    if (wifiList == null) {
                        Log.i("Fac_WifiBluetoochCombinedDialog", "wifi list is null");
                        WifiBluetoochCombinedDialog.this.mWifiAdmin.startScan();
                    } else if (wifiList.size() > 0) {
                        Log.i("Fac_WifiBluetoochCombinedDialog", "wifi list not null");
                        List<ScanResult> list2 = WifiBluetoochCombinedDialog.mWirelessList;
                        WifiAdmin.sortbysinal(list2);
                        WifiBluetoochCombinedDialog.mWirelessList = list2;
                        WifiBluetoochCombinedDialog.this.mwirelessadapter.setList(list2);
                        FacParamManager.mWifiResult = WifiBluetoochCombinedDialog.mWirelessList.get(0);
                        FacWifiAdapter facWifiAdapter2 = WifiBluetoochCombinedDialog.this.mwirelessadapter;
                        if (facWifiAdapter2 != null) {
                            facWifiAdapter2.notifyDataSetChanged();
                            Log.i("Fac_WifiBluetoochCombinedDialog", "notifyDataSetChanged over");
                            WifiBluetoochCombinedDialog.this.mSharedPreferences.edit().putBoolean(WifiBluetoochCombinedDialog.this.mContext.getResources().getString(R.string.str_test_info_wifi), true).commit();
                        }
                    }
                    WifiBluetoochCombinedDialog.this.isFirstReceiveWifiResult = true;
                } else {
                    WifiBluetoochCombinedDialog.this.mwirelessadapter.setList(null);
                    FacWifiAdapter facWifiAdapter3 = WifiBluetoochCombinedDialog.this.mwirelessadapter;
                    if (facWifiAdapter3 != null) {
                        facWifiAdapter3.notifyDataSetChanged();
                    }
                }
                Log.e("Fac_WifiBluetoochCombinedDialog", "wifi satate = " + WifiBluetoochCombinedDialog.this.mWifiAdmin.isWifiEnabled());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSameBluetoothDevice(String str) {
        for (int i = 0; i < this.DevicesList.size(); i++) {
            String[] strArrSplit = this.DevicesList.get(i).split(",");
            if (str != null && str.equalsIgnoreCase(strArrSplit[1])) {
                return true;
            }
        }
        return false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Log.e("Fac_WifiBluetoochCombinedDialog", "onItemClick\n" + mWirelessList.get(i) + "\n");
    }

    class keyinput {
        public void reset() {
        }

        keyinput(WifiBluetoochCombinedDialog wifiBluetoochCombinedDialog) {
            reset();
        }
    }

    private static class BluetoothScanner extends Handler {
        private int mRetry;

        private BluetoothScanner() {
            this.mRetry = 0;
        }

        void forceScan() {
            removeMessages(0);
            sendEmptyMessage(0);
        }

        void pause() {
            this.mRetry = 0;
            removeMessages(0);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Log.i("Fac_WifiBluetoochCombinedDialog", "bluetooth Scan...");
            BluetoothAdapter bluetoothAdapter = WifiBluetoochCombinedDialog.mBluetoothAdapter;
            if (bluetoothAdapter != null) {
                if (bluetoothAdapter.isDiscovering()) {
                    Log.i("Fac_WifiBluetoochCombinedDialog", "is discovering, cancel then start discovery");
                    WifiBluetoochCombinedDialog.mBluetoothAdapter.cancelDiscovery();
                    try {
                        Thread.currentThread();
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Fac_WifiBluetoochCombinedDialog", "start");
                if (WifiBluetoochCombinedDialog.mBluetoothAdapter.startDiscovery()) {
                    this.mRetry = 0;
                } else {
                    int i = this.mRetry + 1;
                    this.mRetry = i;
                    if (i >= 3) {
                        this.mRetry = 0;
                        return;
                    }
                }
                sendEmptyMessageDelayed(0, 5000L);
            }
        }
    }

    class ItemClickEvent implements AdapterView.OnItemClickListener {
        ItemClickEvent() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            String str = WifiBluetoochCombinedDialog.this.DevicesList.get(i);
            System.out.println("lstDevices.get(arg2)" + str);
            String[] strArrSplit = str.split("\\|");
            String str2 = strArrSplit[1];
            Log.e("address", strArrSplit[1]);
            UUID uuidFromString = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            BluetoothAdapter bluetoothAdapter = WifiBluetoochCombinedDialog.mBluetoothAdapter;
            if (bluetoothAdapter != null) {
                try {
                    WifiBluetoochCombinedDialog.mBluetoothSocket = bluetoothAdapter.getRemoteDevice(str2).createRfcommSocketToServiceRecord(uuidFromString);
                    Thread thread = new Thread(new Runnable(this) { // from class: com.hisense.uifac.network.adapter.WifiBluetoochCombinedDialog.ItemClickEvent.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (WifiBluetoochCombinedDialog.mBluetoothSocket == null) {
                                    return;
                                }
                                WifiBluetoochCombinedDialog.isConnectionInProgress = true;
                                WifiBluetoochCombinedDialog.mBluetoothSocket.connect();
                                WifiBluetoochCombinedDialog.isConnectionInProgress = false;
                            } catch (IOException e) {
                                WifiBluetoochCombinedDialog.isConnectionInProgress = false;
                                Log.e("Fac_WifiBluetoochCombinedDialog", "BlueTooth connection threw an IOException");
                                e.printStackTrace();
                            }
                        }
                    });
                    if (WifiBluetoochCombinedDialog.isConnectionInProgress) {
                        return;
                    }
                    thread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<String> sortbysinal(List<String> list) {
        if (list != null) {
            Collections.sort(list, new Comparator<String>() { // from class: com.hisense.uifac.network.adapter.WifiBluetoochCombinedDialog.2
                @Override // java.util.Comparator
                public int compare(String str, String str2) {
                    String[] strArrSplit = str.split(",");
                    String[] strArrSplit2 = str2.split(",");
                    int length = strArrSplit.length - 1;
                    int length2 = strArrSplit2.length - 1;
                    if (Integer.valueOf(strArrSplit[length]).intValue() > Integer.valueOf(strArrSplit2[length2]).intValue()) {
                        return -1;
                    }
                    return Integer.valueOf(strArrSplit[length]).intValue() < Integer.valueOf(strArrSplit2[length2]).intValue() ? 1 : 0;
                }
            });
        }
        return list;
    }

    class Holder {
        TextView address;
        TextView name;
        TextView rssi;
        TextView rssiColor;

        Holder(WifiBluetoochCombinedDialog wifiBluetoochCombinedDialog) {
        }
    }

    public class BluetoothSingleItemAdapter extends BaseAdapter {
        Context context;
        LayoutInflater la;
        List<String> list;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public BluetoothSingleItemAdapter(List<String> list, Context context) {
            this.list = new ArrayList();
            this.list = list;
            this.context = context;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<String> list = this.list;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.list.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            Holder holder;
            if (view == null) {
                LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.context);
                this.la = layoutInflaterFrom;
                if (FacPropertyDemo.HIFAC_MARKET_SA) {
                    view = layoutInflaterFrom.inflate(R.layout.device_bluetooth_item_sa, (ViewGroup) null);
                } else {
                    view = layoutInflaterFrom.inflate(R.layout.device_bluetooth_item, (ViewGroup) null);
                }
                holder = new Holder(WifiBluetoochCombinedDialog.this);
                holder.name = (TextView) view.findViewById(R.id.name);
                holder.address = (TextView) view.findViewById(R.id.address);
                holder.rssi = (TextView) view.findViewById(R.id.rssi);
                if (FacPropertyDemo.HIFAC_MARKET_SA) {
                    holder.rssiColor = (TextView) view.findViewById(R.id.sa_blutooth_signal_color);
                }
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            String[] strArrSplit = this.list.get(i).split(",");
            holder.name.setText(strArrSplit[0]);
            holder.address.setText(strArrSplit[1]);
            holder.rssi.setText(strArrSplit[2]);
            if (FacPropertyDemo.HIFAC_MARKET_SA) {
                if (strArrSplit[2] != null && !strArrSplit[2].isEmpty() && Integer.valueOf(strArrSplit[2]).intValue() < 0) {
                    holder.rssiColor.setBackgroundColor(-16711936);
                } else {
                    holder.rssiColor.setBackgroundColor(-65536);
                }
            }
            return view;
        }
    }

    private String getWifiMacAddress() {
        WifiInfo connectionInfo = ((WifiManager) this.mContext.getSystemService("wifi")).getConnectionInfo();
        Log.d("Fac_WifiBluetoochCombinedDialog", MtkTvConfigTypeBase.S3166_CFG_COUNT_MAC + connectionInfo);
        if (connectionInfo == null || connectionInfo.getMacAddress().equals("00:00:00:00:00:00")) {
            return "";
        }
        return "" + connectionInfo.getMacAddress();
    }
}
