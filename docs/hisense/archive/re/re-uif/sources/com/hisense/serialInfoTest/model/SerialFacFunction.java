package com.hisense.serialInfoTest.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.NetworkInfo;
import android.net.RouteInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import com.hisense.serialInfoTest.util.HexData;
import com.hisense.serialInfoTest.util.Logger;
import com.hisense.tvcm.FacParamManager;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.ExternalStorageHelper;
import com.hisense.uifac.util.HisLog;
import java.io.File;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SerialFacFunction {
    private static SerialFacFunction mSerialFac;
    private List<ScanResult> listResult;
    Context mContext;
    private ScanResult mScanResult;
    WifiManager mWifiManager;
    private String VIDEO_NAME_TO_PLAY = "VIDEO_TEST";
    private String videoFileDirctory = "";
    private String videoFileName = "";

    private SerialFacFunction(Context context) {
        this.mContext = context;
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        this.mWifiManager = wifiManager;
        wifiManager.setWifiEnabled(true);
    }

    public static SerialFacFunction getInstance(Context context) {
        if (mSerialFac == null) {
            mSerialFac = new SerialFacFunction(context);
        }
        return mSerialFac;
    }

    public boolean doesVideoFileExistInUsb(boolean z) throws Throwable {
        HisLog.i("<nxl_dbg>\t doesVideoFileExistInUsb \t search began:");
        this.videoFileDirctory = "";
        this.videoFileName = "";
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath != null && !externalStoragePath.isEmpty()) {
            Iterator<String> it = externalStoragePath.iterator();
            while (it.hasNext()) {
                File file = new File(it.next());
                HisLog.i("doesVideoFileExistInUsb \t mountUsb:" + file.toString());
                if (file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        if (file2.isFile() && file2.getName().contains("VIDEO_TEST")) {
                            HisLog.i("doesVideoFileExistInUsb \t contains const part." + this.videoFileDirctory + " \t" + this.videoFileName);
                            String[] strArrSplit = file2.getName().split("\\.");
                            if (z) {
                                if (strArrSplit[0].equals("3D_VIDEO_TEST")) {
                                    this.videoFileDirctory = file2.getPath().substring(0, file2.getPath().lastIndexOf("/"));
                                    this.videoFileName = file2.getName();
                                    HisLog.i(" doesVideoFileExistInUsb found 3d file.\t" + this.videoFileDirctory + " \t" + this.videoFileName);
                                    return true;
                                }
                            } else {
                                if (FacPropertyDemo.HIFAC_4K_VIDEO_SUPPORT) {
                                    this.VIDEO_NAME_TO_PLAY = "4K_VIDEO_TEST";
                                }
                                String str = FacParamManager.FAC_DMP_FILE_NAME;
                                this.VIDEO_NAME_TO_PLAY = str;
                                if (strArrSplit[0].equals(str)) {
                                    this.videoFileDirctory = file2.getPath().substring(0, file2.getPath().lastIndexOf("/"));
                                    this.videoFileName = file2.getName();
                                    HisLog.i(" doesVideoFileExistInUsb \tthe following line is the file to play");
                                    HisLog.i(" doesVideoFileExistInUsb found 2d file.\t" + this.videoFileDirctory + " \t" + this.videoFileName);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        HisLog.i("There is no external storage!!!");
        return false;
    }

    public byte[] getScanSSIDResult() {
        String string = HexData.stringToHexString("no_wifi") + "FF" + HexData.integerToHexString(0);
        HisLog.i("wifi is open?" + this.mWifiManager.isWifiEnabled());
        if (this.mWifiManager.isWifiEnabled()) {
            this.mWifiManager.startScan();
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Logger.d("getScanSSIDResult");
            List<ScanResult> scanResults = this.mWifiManager.getScanResults();
            sortbysinal(scanResults);
            this.listResult = scanResults;
            if (scanResults != null && scanResults.size() > 0) {
                this.mScanResult = this.listResult.get(0);
                Logger.d("the first scan result is " + this.mScanResult.SSID);
                ScanResult scanResult = this.mScanResult;
                int i = scanResult.level;
                if (i < 0) {
                    scanResult.level = Math.abs(i);
                }
                Logger.i("SSID is" + this.mScanResult.SSID + "level is " + this.mScanResult.level);
                StringBuilder sb = new StringBuilder();
                sb.append(HexData.stringToHexString(this.mScanResult.SSID));
                sb.append("FF");
                sb.append(HexData.integerToHexString(this.mScanResult.level));
                string = sb.toString();
                Logger.i("hexSSID is" + string);
            } else {
                Logger.d("has no scan result");
            }
        }
        return HexData.hexStringToBytes(string);
    }

    public byte[] getWifiIPInfo() {
        Logger.d("SerailFacFunction getWifiIPInfo.");
        byte[] bArrHexStringToBytes = HexData.hexStringToBytes("00000000010000000002000000000300000000");
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        int iAddNetwork = wifiManager.addNetwork(wifiConfiguration);
        wifiManager.updateNetwork(wifiConfiguration);
        wifiManager.saveConfiguration();
        try {
            Logger.d("EnableWifi: " + wifiManager.enableNetwork(iAddNetwork, true));
            String wifiIpAddresses = checkWifiNetworkInfo(this.mContext).equals("CONNECTED") ? getWifiIpAddresses(this.mContext, 0) : null;
            String wifiIpAddresses2 = checkWifiNetworkInfo(this.mContext).equals("CONNECTED") ? getWifiIpAddresses(this.mContext, 1) : null;
            String wifiIpAddresses3 = checkWifiNetworkInfo(this.mContext).equals("CONNECTED") ? getWifiIpAddresses(this.mContext, 2) : null;
            String wifiIpAddresses4 = checkWifiNetworkInfo(this.mContext).equals("CONNECTED") ? getWifiIpAddresses(this.mContext, 3) : null;
            Logger.d("ipinfo: " + wifiIpAddresses + "\n subnetMaskInfo: " + wifiIpAddresses2 + "\n gatewayInfo: " + wifiIpAddresses3 + "\n dnsInfo: " + wifiIpAddresses4);
            return HexData.hexStringToBytes(splitNetworkInfo(wifiIpAddresses) + "01" + splitNetworkInfo(wifiIpAddresses2) + "02" + splitNetworkInfo(wifiIpAddresses3) + "03" + splitNetworkInfo(wifiIpAddresses4));
        } catch (Exception e) {
            Logger.e("getWifiInfo:" + e.getMessage());
            return bArrHexStringToBytes;
        }
    }

    public String splitNetworkInfo(String str) {
        Logger.d("splitNetworkInfo: " + str);
        StringBuilder sb = new StringBuilder();
        for (String str2 : str.split("\\.")) {
            sb.append(HexData.integerToHexString(Integer.parseInt(str2)));
        }
        return sb.toString();
    }

    public static String checkWifiNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo.State state = NetworkInfo.State.UNKNOWN;
        if (connectivityManager != null && connectivityManager.getNetworkInfo(1) != null) {
            state = connectivityManager.getNetworkInfo(1).getState();
        }
        return state.toString();
    }

    public static String getWifiIpAddresses(Context context, int i) {
        LinkProperties linkProperties = ((ConnectivityManager) context.getSystemService("connectivity")).getLinkProperties(1);
        if (linkProperties == null) {
            return null;
        }
        String str = "";
        if (i == 0) {
            Iterator it = linkProperties.getAddresses().iterator();
            if (!it.hasNext()) {
                return null;
            }
            while (it.hasNext()) {
                str = str + ((InetAddress) it.next()).getHostAddress();
                if (it.hasNext()) {
                    str = str + ".";
                }
            }
            return str;
        }
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return null;
                }
                return "";
            }
            for (RouteInfo routeInfo : linkProperties.getRoutes()) {
                if (routeInfo.isDefaultRoute()) {
                    String string = routeInfo.getGateway().getHostAddress().toString();
                    Logger.d("gateway: " + string);
                    return string;
                }
            }
            return "";
        }
        Iterator it2 = linkProperties.getAddresses().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        String str2 = "";
        while (it2.hasNext()) {
            str2 = str2 + ((InetAddress) it2.next()).getHostAddress();
            if (it2.hasNext()) {
                str2 = str2 + ".";
            }
        }
        int i2 = Integer.parseInt(str2.split("\\.")[0]);
        if (i2 >= 1 && i2 <= 126) {
            return "255.0.0.0";
        }
        if (i2 >= 128 && i2 <= 191) {
            return "255.255.0.0";
        }
        if (i2 >= 192 && i2 <= 223) {
            return "255.255.255.0";
        }
        return i2 + "";
    }

    public static List<ScanResult> sortbysinal(List<ScanResult> list) {
        if (list != null) {
            Collections.sort(list, new Comparator<ScanResult>() { // from class: com.hisense.serialInfoTest.model.SerialFacFunction.1
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
}
