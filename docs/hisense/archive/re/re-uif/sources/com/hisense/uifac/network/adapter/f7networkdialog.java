package com.hisense.uifac.network.adapter;

import android.app.Dialog;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.adapter.SetConfigListViewAdapter;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.hisense.uifac.menu.util.MenuDataContainer;
import com.hisense.uifac.network.adapter.WifiAdmin.WireLessAdapter;
import com.hisense.uifac.network.adapter.WifiAdmin.WireLessSAAdapter;
import com.hisense.uifac.util.HisLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class f7networkdialog extends Dialog implements AdapterView.OnItemClickListener {
    public static List<ScanResult> mWirelessList = new ArrayList();
    Handler mActivityHandler;
    Context mContext;
    private NetworkListView mF7listview;
    private List<SetConfigListViewAdapter.DataItem> mGroup_F7network;
    IntentFilter mItendFiliter;
    private SetConfigListViewAdapter mListViewAdpter_F7network;
    MenuDataContainer mMenuDataContainer;
    private SharedPreferences mSharedPreferences;
    WifiAdmin mWifiAdmin;
    WifiBroadcastReceiver mWifiBroadcastReceiver;
    private TextView mWifiMacView;
    ListView mWifilistview;
    FacWifiAdapter mwirelessadapter;

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public class WifiBroadcastReceiver extends BroadcastReceiver {
        public WifiBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i("Fac_f7networkdialog", intent.getAction());
            if (intent.getAction().equals("android.net.wifi.SCAN_RESULTS")) {
                Log.i("Fac_f7networkdialog", "GET AP:\n");
                f7networkdialog.this.mSharedPreferences.edit().putBoolean(f7networkdialog.this.mContext.getResources().getString(R.string.str_test_info_wifi), true).commit();
                f7networkdialog.mWirelessList = f7networkdialog.this.mWifiAdmin.getWifiList();
                f7networkdialog.this.mWifilistview.setVisibility(0);
                List<ScanResult> list = f7networkdialog.mWirelessList;
                WifiAdmin.sortbysinal(list);
                f7networkdialog.mWirelessList = list;
                f7networkdialog.this.mwirelessadapter.setList(list);
                FacWifiAdapter facWifiAdapter = f7networkdialog.this.mwirelessadapter;
                if (facWifiAdapter != null) {
                    facWifiAdapter.notifyDataSetChanged();
                    return;
                }
                return;
            }
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE") || intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
                if (f7networkdialog.this.mWifiAdmin.isWifiEnabled()) {
                    f7networkdialog.this.mWifiAdmin.startScan();
                } else {
                    f7networkdialog.this.mwirelessadapter.setList(null);
                    FacWifiAdapter facWifiAdapter2 = f7networkdialog.this.mwirelessadapter;
                    if (facWifiAdapter2 != null) {
                        facWifiAdapter2.notifyDataSetChanged();
                    }
                }
                Log.e("Fac_f7networkdialog", "wifi satate = " + f7networkdialog.this.mWifiAdmin.isWifiEnabled());
            }
        }
    }

    class keyinput {
        public void reset() {
        }

        keyinput(f7networkdialog f7networkdialogVar) {
            reset();
        }
    }

    public f7networkdialog(Context context, int i, Handler handler) {
        super(context, i);
        this.mSharedPreferences = null;
        this.mContext = context;
        MenuConfigManager.getInstance(context);
        this.mMenuDataContainer = MenuDataContainer.getInstance(this.mContext);
        this.mListViewAdpter_F7network = new SetConfigListViewAdapter(this.mContext);
        this.mWifiAdmin = WifiAdmin.getinstance(this.mContext);
        this.mSharedPreferences = this.mContext.getSharedPreferences("FacShareP", 0);
        IntentFilter intentFilter = new IntentFilter();
        this.mItendFiliter = intentFilter;
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.mItendFiliter.addAction("android.net.wifi.STATE_CHANGE");
        this.mItendFiliter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.mItendFiliter.addAction("android.net.conn.BACKGROUND_DATA_SETTING_CHANGED");
        this.mWifiBroadcastReceiver = new WifiBroadcastReceiver();
        new keyinput(this);
        this.mActivityHandler = handler;
        Log.i("Fac_f7networkdialog", "on Constructor");
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.mWifiAdmin.stopscan();
        Message message = new Message();
        message.what = 0;
        this.mActivityHandler.removeMessages(0);
        this.mActivityHandler.sendMessage(message);
        super.dismiss();
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 || i == 82 || i == 4212) {
            dismiss();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
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
    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        String string;
        Log.i("Fac_f7networkdialog", "onCreate");
        super.onCreate(bundle);
        setContentView(R.layout.network);
        setTitle(R.string.Network);
        this.mF7listview = (NetworkListView) findViewById(R.id.facmenu_network_sublistView);
        this.mMenuDataContainer.loadNetworkSetting();
        this.mF7listview.initData(this.mMenuDataContainer.getFacDataGroup_NetworkSetting(), 6, null, 0);
        List currentList = this.mF7listview.getCurrentList();
        this.mGroup_F7network = currentList;
        this.mListViewAdpter_F7network.setmGroup(currentList);
        this.mF7listview.setAdapter((ListAdapter) this.mListViewAdpter_F7network);
        this.mF7listview.requestFocus();
        this.mWifilistview = (ListView) findViewById(R.id.facmenu_network_wifiaplistview);
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
        this.mWifiMacView = (TextView) findViewById(R.id.wifi_mac);
        WifiInfo connectionInfo = ((WifiManager) getContext().getSystemService("wifi")).getConnectionInfo();
        String string2 = this.mContext.getResources().getString(R.string.str_wifimac);
        HisLog.i("wifi mac is " + connectionInfo.getMacAddress());
        if (connectionInfo != null && !connectionInfo.getMacAddress().equals("00:00:00:00:00:00")) {
            string = string2 + connectionInfo.getMacAddress();
        } else {
            string = this.mContext.getResources().getString(R.string.str_wifimac_error);
        }
        this.mWifiMacView.setText(string);
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        this.mContext.registerReceiver(this.mWifiBroadcastReceiver, this.mItendFiliter);
        Log.i("Fac_f7networkdialog", "onStart");
        this.mWifiAdmin.openWifi();
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        this.mContext.unregisterReceiver(this.mWifiBroadcastReceiver);
        Log.i("Fac_f7networkdialog", "onCreate");
    }
}
