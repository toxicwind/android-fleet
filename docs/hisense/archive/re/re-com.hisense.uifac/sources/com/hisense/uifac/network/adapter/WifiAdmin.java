package com.hisense.uifac.network.adapter;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.hisense.uifac.R;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiAdmin {
    static WifiAdmin mInstance;
    private static WifiManager mWifiManager;
    private Accesspoint mAccesspoint;
    private WifiInfo mLastWifiInfo = null;
    private Scanner mScanner;
    private List<ScanResult> mWifiList;

    public class Accesspoint {
        public Accesspoint(WifiAdmin wifiAdmin) {
        }

        public int getCapabilities(String str) {
            if (str.contains("WEP")) {
                return 1;
            }
            if (str.contains("PSK")) {
                return 2;
            }
            return str.contains("EAP") ? 3 : 0;
        }
    }

    public class WireLessAdapter extends FacWifiAdapter {
        Context context;
        LayoutInflater la;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public WireLessAdapter(List<ScanResult> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<ScanResult> list = this.list;
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
                view = layoutInflaterFrom.inflate(R.layout.network_wireless_item, (ViewGroup) null);
                holder = new Holder(WifiAdmin.this);
                holder.ssid = (TextView) view.findViewById(R.id.ssid);
                holder.encrypt = (ImageView) view.findViewById(R.id.encrypt);
                holder.connect = (TextView) view.findViewById(R.id.connect);
                holder.digitalWeight = (TextView) view.findViewById(R.id.digitalweight);
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            ScanResult scanResult = this.list.get(i);
            holder.ssid.setText(scanResult.SSID);
            if (scanResult.SSID.equals(WifiAdmin.mWifiManager.getConnectionInfo().getSSID())) {
                holder.connect.setText(R.string.Network_WIFI_Connected);
            } else {
                holder.connect.setText("");
            }
            if (WifiAdmin.this.mAccesspoint.getCapabilities(scanResult.capabilities) == 0) {
                holder.encrypt.setBackgroundResource(R.drawable.unlock);
            } else {
                holder.encrypt.setBackgroundResource(R.drawable.lock);
            }
            holder.digitalWeight.setText(String.valueOf(scanResult.level));
            return view;
        }

        @Override // com.hisense.uifac.network.adapter.FacWifiAdapter
        public void setList(List<ScanResult> list) {
            this.list = list;
        }
    }

    class SAHolder {
        TextView digitalWeight;
        ImageView encrypt;
        TextView levelColor;
        TextView ssid;

        SAHolder(WifiAdmin wifiAdmin) {
        }
    }

    public class WireLessSAAdapter extends FacWifiAdapter {
        Context context;
        LayoutInflater la;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public WireLessSAAdapter(List<ScanResult> list, Context context) {
            Log.i("Fac_WifiAdmin", "WireLessSAAdapter");
            this.list = list;
            this.context = context;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<ScanResult> list = this.list;
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
            SAHolder sAHolder;
            if (view == null) {
                LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.context);
                this.la = layoutInflaterFrom;
                view = layoutInflaterFrom.inflate(R.layout.network_wireless_item_sa, (ViewGroup) null);
                sAHolder = new SAHolder(WifiAdmin.this);
                sAHolder.ssid = (TextView) view.findViewById(R.id.ssid_sa);
                sAHolder.encrypt = (ImageView) view.findViewById(R.id.encrypt_sa);
                sAHolder.digitalWeight = (TextView) view.findViewById(R.id.digitalweight_sa);
                sAHolder.levelColor = (TextView) view.findViewById(R.id.level_color);
                view.setTag(sAHolder);
            } else {
                sAHolder = (SAHolder) view.getTag();
            }
            ScanResult scanResult = this.list.get(i);
            sAHolder.ssid.setText(scanResult.SSID);
            if (WifiAdmin.this.mAccesspoint.getCapabilities(scanResult.capabilities) == 0) {
                sAHolder.encrypt.setBackgroundResource(R.drawable.unlock);
            } else {
                sAHolder.encrypt.setBackgroundResource(R.drawable.lock);
            }
            sAHolder.digitalWeight.setText(String.valueOf(scanResult.level));
            if (scanResult.level > -65) {
                sAHolder.levelColor.setBackgroundColor(-16711936);
            } else {
                sAHolder.levelColor.setBackgroundColor(-65536);
            }
            return view;
        }

        @Override // com.hisense.uifac.network.adapter.FacWifiAdapter
        public void setList(List<ScanResult> list) {
            this.list = list;
        }
    }

    private static class Scanner extends Handler {
        private int mRetry;

        private Scanner() {
            this.mRetry = 0;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Log.e("Fac_WifiAdmin", "wifi Scan...");
            if (WifiAdmin.mWifiManager.startScan()) {
                this.mRetry = 0;
                return;
            }
            int i = this.mRetry + 1;
            this.mRetry = i;
            if (i >= 3) {
                this.mRetry = 0;
            }
        }

        void forceScan() {
            sendEmptyMessage(0);
        }

        void pause() {
            this.mRetry = 0;
            removeMessages(0);
        }
    }

    class Holder {
        TextView connect;
        TextView digitalWeight;
        ImageView encrypt;
        TextView ssid;

        Holder(WifiAdmin wifiAdmin) {
        }
    }

    public static WifiAdmin getinstance() {
        return mInstance;
    }

    public static WifiAdmin getinstance(Context context) {
        if (mInstance == null) {
            new WifiAdmin(context);
        }
        return mInstance;
    }

    public static List<ScanResult> sortbysinal(List<ScanResult> list) {
        if (list != null) {
            Collections.sort(list, new Comparator<ScanResult>() { // from class: com.hisense.uifac.network.adapter.WifiAdmin.1
                @Override // java.util.Comparator
                public int compare(ScanResult scanResult, ScanResult scanResult2) {
                    int i = scanResult.level;
                    int i2 = scanResult2.level;
                    if (i > i2) {
                        return -1;
                    }
                    return i < i2 ? 1 : 0;
                }
            });
        }
        return list;
    }

    public WifiAdmin(Context context) {
        mWifiManager = (WifiManager) context.getSystemService("wifi");
        try {
            this.mScanner = new Scanner();
        } catch (Exception e) {
            Log.e("Fac_WifiAdmin", "new Scanner failed!");
            e.printStackTrace();
        }
        mInstance = this;
        this.mAccesspoint = new Accesspoint(this);
    }

    public void closeWifi() {
        if (mWifiManager.isWifiEnabled()) {
            this.mScanner.pause();
            Log.i("Fac_WifiAdmin", "close wifi");
            mWifiManager.disconnect();
            mWifiManager.setWifiEnabled(false);
        }
    }

    public List<ScanResult> getWifiList() {
        List<ScanResult> scanResults = mWifiManager.getScanResults();
        this.mWifiList = scanResults;
        return scanResults;
    }

    public boolean isWifiEnabled() {
        return mWifiManager.getWifiState() == 3;
    }

    public void openWifi() {
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
            if (this.mScanner == null) {
                try {
                    this.mScanner = new Scanner();
                } catch (Exception e) {
                    Log.e("Fac_WifiAdmin", "new Scanner failed!");
                    e.printStackTrace();
                }
            }
            Log.i("Fac_WifiAdmin", "open wifi");
            return;
        }
        if (this.mScanner == null) {
            try {
                this.mScanner = new Scanner();
            } catch (Exception e2) {
                Log.e("Fac_WifiAdmin", "new Scanner failed!");
                e2.printStackTrace();
            }
        }
    }

    public void stopscan() {
        this.mScanner.pause();
    }

    public void startScan() {
        this.mScanner.forceScan();
    }

    public void disconnectWifi() {
        if (mWifiManager.isWifiEnabled()) {
            WifiInfo connectionInfo = mWifiManager.getConnectionInfo();
            this.mLastWifiInfo = connectionInfo;
            if (connectionInfo != null) {
                Log.d("Fac_WifiAdmin", "disconnectWifi mLastWifiInfo.SSID = " + this.mLastWifiInfo.getSSID() + ", mLastWifiInfo.BSSID = " + this.mLastWifiInfo.getBSSID() + ", mLastWifiInfo.networkId = " + this.mLastWifiInfo.getNetworkId());
            }
            mWifiManager.disconnect();
            List<WifiConfiguration> configuredNetworks = mWifiManager.getConfiguredNetworks();
            if (configuredNetworks != null) {
                for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                    Log.d("Fac_WifiAdmin", "disconnectWifi   config.SSID = " + wifiConfiguration.SSID + ", config.networkId = " + wifiConfiguration.networkId);
                    mWifiManager.disableNetwork(wifiConfiguration.networkId);
                }
            }
        }
    }

    public void reconnectWifi() {
        if (mWifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = this.mLastWifiInfo;
            if (wifiInfo != null) {
                int networkId = wifiInfo.getNetworkId();
                Log.d("Fac_WifiAdmin", "reconnectWifi mLastWifiInfo.networkId = " + networkId);
                for (WifiConfiguration wifiConfiguration : mWifiManager.getConfiguredNetworks()) {
                    Log.d("Fac_WifiAdmin", "reconnectWifi   config.SSID = " + wifiConfiguration.SSID + ", config.networkId = " + wifiConfiguration.networkId);
                    if (mWifiManager.enableNetwork(wifiConfiguration.networkId, true)) {
                        Log.d("Fac_WifiAdmin", "reconnectWifi   config.status = " + wifiConfiguration.status);
                        if (wifiConfiguration.status == 1) {
                            wifiConfiguration.status = 2;
                        }
                        mWifiManager.updateNetwork(wifiConfiguration);
                    }
                }
                if (networkId != -1) {
                    mWifiManager.connect(networkId, null);
                }
            } else {
                Log.e("Fac_WifiAdmin", "mLastWifiInfo is null.");
            }
            this.mLastWifiInfo = null;
        }
    }
}
