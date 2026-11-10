package com.hisense.serialInfoTest.model;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.EthernetManager;
import android.net.IpConfiguration;
import android.net.LinkAddress;
import android.net.NetworkUtils;
import android.net.StaticIpConfiguration;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.hisense.serialInfoTest.testPattern.CirclePatternActivity;
import com.hisense.serialInfoTest.testPattern.ModulePatternActivity;
import com.hisense.serialInfoTest.testPattern.PatternPanelController;
import com.hisense.serialInfoTest.util.HexData;
import com.hisense.serialInfoTest.util.Logger;
import com.hisense.serialInfoTest.util.SerialSaveFile;
import com.hisense.tvcm.ApVsTILmap;
import com.hisense.tvcm.CamDisplayManager;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacParamManager;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.tvs.FacTvsFunctionUtil;
import com.hisense.uifac.ExternalStorageHelper;
import com.hisense.uifac.R;
import com.hisense.uifac.menu.util.MenuConfigManager;
import com.hisense.uifac.mic.HisenseFacMicTest;
import com.hisense.uifac.network.adapter.WifiAdmin;
import com.hisense.uifac.util.DemuraUtil;
import com.hisense.uifac.util.HisLog;
import com.hisense.uifac.util.IMtkFunCallback;
import com.hisense.uifac.util.IRootMenuUiListener;
import com.hisense.uifac.util.TcpServer;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SerialCommands {
    public static String ipString = null;
    private static long mAgingTimeLong = 0;
    public static MenuConfigManager mMenuConfigManager = null;
    private static String mSerialKeyPath = "";
    public static SerialCommands mTestCommands;
    private final int FANGCHUANHUO_NUMBER_DISPLAY_COUNT;
    Context adapterContext;
    int factoryfailedcode;
    IMtkFunCallback factoryokcallback;
    int factoryokretry;
    boolean isFactoryokbusy;
    public KeySwitcher keySwitcher = KeySwitcher.getInstance();
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private ServiceConnection mConnection;
    Context mContext;
    private int mDemuraCardType;
    private String mEthernetInterfaceName;
    private EthernetManager mEthernetManager;
    private FacFunction_Adapter mFunctionAdapter;
    HisenseFacMicTest mHisenseFacMicTest;
    private IpConfiguration mIpConfiguration;
    private IpcManager mIpcManager;
    private CamDisplayManager mSerialCamDisplayManager;
    SerialFacFunction mSerialFacFunction;
    private SerialSaveFile mSerialSaveFile;
    SharedPreferences mShared;
    private int mVcomType;
    private WifiAdmin mWifiAdmin;
    public Handler mhHandler;
    private int[] supportSources;
    private static final Intent checkKeyStatusIntent = new Intent("com.hisense.broadcom.CHECK_KEY_STATUS");
    public static List<ScanResult> mWirelessList = new ArrayList();
    private static final int[][] array = {new int[]{Serial3DMode.OFF.ordinal(), Fac3DMode.OFF.ordinal()}, new int[]{Serial3DMode.AUTO.ordinal(), Fac3DMode.AUTO.ordinal()}, new int[]{Serial3DMode.MODE_2DTO3D.ordinal(), Fac3DMode.MODE_2DTO3D.ordinal()}, new int[]{Serial3DMode.SIDEBYSIDE.ordinal(), Fac3DMode.SIDEBYSIDE.ordinal()}, new int[]{Serial3DMode.TOPANDBOTTOM.ordinal(), Fac3DMode.TOPANDBOTTOM.ordinal()}};
    public static boolean isShowVcom = false;

    private enum Fac3DMode {
        OFF,
        MODE_2DTO3D,
        SIDEBYSIDE,
        TOPANDBOTTOM,
        AUTO
    }

    private enum Serial3DMode {
        OFF,
        AUTO,
        MODE_2DTO3D,
        SIDEBYSIDE,
        TOPANDBOTTOM
    }

    private SerialCommands(Context context) {
        this.adapterContext = null;
        this.mSerialCamDisplayManager = null;
        this.mHisenseFacMicTest = null;
        new ArrayList();
        this.mFunctionAdapter = null;
        this.mVcomType = 0;
        this.mDemuraCardType = 0;
        this.mConnection = new ServiceConnection() { // from class: com.hisense.serialInfoTest.model.SerialCommands.1
            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Logger.d("IpcManager-->onServiceDisconnected");
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                SerialCommands.this.mIpcManager = IpcManager.Stub.asInterface(iBinder);
                try {
                    SerialCommands.this.supportSources = SerialCommands.this.mIpcManager.getSupportSources();
                    Logger.d("IpcManager-->onServiceConnected we have " + SerialCommands.this.supportSources.length + " sources");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
        this.mhHandler = new Handler() { // from class: com.hisense.serialInfoTest.model.SerialCommands.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Logger.d("message: " + message);
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                HisLog.i("***msg=" + message.what);
                int i = message.what;
                if (i != 6) {
                    if (i == 7) {
                        SerialCommands.mMenuConfigManager.setValue("HIFAC_STATUS", 0);
                        return;
                    } else {
                        if (i != 13) {
                            return;
                        }
                        SerialCommands.this.mhHandler.removeMessages(4);
                        if (SerialCommands.mMenuConfigManager.getDefault("HIFAC_STATUS") == 1) {
                            Logger.d("not show BigMode");
                            return;
                        }
                        return;
                    }
                }
                Logger.i("IN HANDLER. HANDLING MODEL_FACTORYOK_OFF MESSAGE");
                Logger.i(message.toString() + "msg.arg1" + message.arg1 + "msg.arg2" + message.arg2 + "msg.what" + message.what);
                SerialCommands.this.mhHandler.removeMessages(4);
            }
        };
        this.factoryokretry = 0;
        this.factoryfailedcode = -1;
        this.factoryokcallback = new IMtkFunCallback() { // from class: com.hisense.serialInfoTest.model.SerialCommands.3
            @Override // com.hisense.uifac.util.IMtkFunCallback
            public void notifyProgress(int i) {
                if (i <= 100 && i >= 0) {
                    SerialCommands.this.isFactoryokbusy = true;
                    Logger.i("factoryOk progress = " + i);
                } else if (i == 101) {
                    Logger.i("FACTORY OK CALLBACK PROGRESS == 101 RETURNED.");
                    SerialCommands serialCommands = SerialCommands.this;
                    serialCommands.isFactoryokbusy = false;
                    serialCommands.sendMessageDelayed(6, 4000);
                    SerialCommands.this.sendMessageDelayed(7, 6000);
                    SerialCommands.this.mContext.getSharedPreferences("FactoryOk_failed", 3).edit().remove("FactoryOk_failed").commit();
                } else {
                    SerialCommands serialCommands2 = SerialCommands.this;
                    serialCommands2.isFactoryokbusy = false;
                    if (serialCommands2.factoryokretry < 0) {
                        Logger.e("Factory ok failed retry " + SerialCommands.this.factoryokretry);
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        SerialCommands.this.factoryOk();
                        SerialCommands.this.factoryokretry++;
                    } else {
                        serialCommands2.factoryokretry = 0;
                        serialCommands2.sendMessageDelayed(13, 3000);
                        SerialCommands.this.mContext.getSharedPreferences("FactoryOk_failed", 3).edit().putInt("FactoryOk_failed", SerialCommands.this.factoryfailedcode).commit();
                        try {
                            Runtime.getRuntime().exec("sync");
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                SerialCommands.this.factoryfailedcode = i;
            }
        };
        this.mContext = context;
        this.mSerialFacFunction = SerialFacFunction.getInstance(context);
        mMenuConfigManager = MenuConfigManager.getInstance(this.mContext);
        this.mSerialCamDisplayManager = CamDisplayManager.getInstance(this.mContext);
        this.mEthernetManager = getEthernetManager(this.mContext);
        Logger.d("SerialCommands function");
        mSerialKeyPath = "/perm/keyname/tmp";
        startIpc(this.mContext);
        if (this.adapterContext == null) {
            try {
                this.adapterContext = this.mContext.createPackageContext("com.hisense.uifac", 3);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            this.mShared = this.adapterContext.getSharedPreferences("FacShareP", 7);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            this.FANGCHUANHUO_NUMBER_DISPLAY_COUNT = FacParamManager.FAC_SN_EM_COUNT;
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.JP)) {
            this.FANGCHUANHUO_NUMBER_DISPLAY_COUNT = FacParamManager.FAC_SN_JP_COUNT;
        } else {
            this.FANGCHUANHUO_NUMBER_DISPLAY_COUNT = FacParamManager.FAC_SN_US_COUNT;
        }
        HisenseFacMicTest hisenseFacMicTest = HisenseFacMicTest.getInstance();
        this.mHisenseFacMicTest = hisenseFacMicTest;
        hisenseFacMicTest.initMicParam(context);
        mMenuConfigManager.updateOSDC();
        this.mWifiAdmin = WifiAdmin.getinstance(this.mContext);
        this.mFunctionAdapter = FacFunction_Adapter.getInstance(this.mContext);
        startBtWifiScan();
    }

    private void startBtWifiScan() {
        while (!this.mBluetoothAdapter.isEnabled()) {
            SystemClock.sleep(1000L);
        }
        Logger.i("now BT enabled, start BT discovery");
        if (this.mBluetoothAdapter.isDiscovering()) {
            Logger.i("is discovering, cancel then start discovery");
            this.mBluetoothAdapter.cancelDiscovery();
        }
        this.mBluetoothAdapter.startDiscovery();
        if (!this.mWifiAdmin.isWifiEnabled()) {
            this.mWifiAdmin.openWifi();
        }
        Logger.i("start wifi scan");
        this.mWifiAdmin.startScan();
    }

    private void startIpc(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.hisense.intent.START_IPC");
        Intent intent2 = new Intent(createExplicitFromImplicitIntent(context, intent));
        Logger.d("startIpc");
        context.bindService(intent2, this.mConnection, 1);
    }

    public static Intent createExplicitFromImplicitIntent(Context context, Intent intent) {
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.size() != 1) {
            return null;
        }
        ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
        String str = serviceInfo.packageName;
        String str2 = serviceInfo.name;
        Logger.i("service packagename = " + str + "  service className = " + str2);
        ComponentName componentName = new ComponentName(str, str2);
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }

    public static SerialCommands getInstance(Context context) {
        SerialCommands serialCommands = mTestCommands;
        if (serialCommands != null) {
            return serialCommands;
        }
        SerialCommands serialCommands2 = new SerialCommands(context);
        mTestCommands = serialCommands2;
        return serialCommands2;
    }

    private enum BalanceMode {
        CENTER(0),
        ML(1),
        MR(2);

        private int value;

        BalanceMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    private enum TvsBalanceMode {
        CENTER(0),
        LEFT(1),
        RIGHT(2),
        WOOFER(9),
        TTL(7),
        TTR(8);

        private int value;

        TvsBalanceMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static int SerialToFac(int i) {
        Logger.i("serialValue is" + i);
        int[] iArr = array[i];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[0] == i) {
                int i3 = iArr[1];
                Logger.i("FacValue is" + i3);
                return i3;
            }
        }
        return 0;
    }

    public byte[] enterDMP() {
        this.keySwitcher.shortPressKey(230);
        String usbFileName = getUsbFileName();
        Logger.d("enterDMP path = " + usbFileName);
        if (usbFileName == null || usbFileName.isEmpty()) {
            usbFileName = "no usb device";
        }
        String strStringToHexString = HexData.stringToHexString(usbFileName);
        Logger.d("enterDMP2 path = " + usbFileName);
        return HexData.hexStringToBytes(strStringToHexString);
    }

    public boolean enterDMPAutoPLay() {
        Logger.d("test enterDMPAutoPLay");
        boolean zShortPressKey = false;
        try {
            if (this.mSerialFacFunction.doesVideoFileExistInUsb(false)) {
                zShortPressKey = this.keySwitcher.shortPressKey(4306);
            } else {
                Logger.d("enterDMPAutoPLay fail.");
            }
        } catch (Exception e) {
            Logger.d("enterDMPAutoPLay: " + e.getMessage());
        }
        return zShortPressKey;
    }

    public boolean exitDMPAutoPlay() {
        Logger.d("test exitDMPAutoPlay.");
        return this.keySwitcher.shortPressKey(4);
    }

    public byte[] getUSBConnectionNumber() {
        Logger.d("getUSBConnectionNumber.");
        return HexData.hexStringToBytes(HexData.integerToHexString(getUsbCount()));
    }

    public boolean setMacAddress(byte[] bArr) {
        boolean macAddr = true;
        try {
            if (bArr.length != 6) {
                return false;
            }
            macAddr = mMenuConfigManager.setMacAddr(HexData.byteToHexString(bArr[0]) + ":" + HexData.byteToHexString(bArr[1]) + ":" + HexData.byteToHexString(bArr[2]) + ":" + HexData.byteToHexString(bArr[3]) + ":" + HexData.byteToHexString(bArr[4]) + ":" + HexData.byteToHexString(bArr[5]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (macAddr) {
            this.mContext.sendBroadcast(checkKeyStatusIntent);
            Logger.i("send check key status broadcast");
        }
        return macAddr;
    }

    public byte[] getChipID() {
        String chipID = mMenuConfigManager.getChipID();
        Logger.d("getChipID:    " + chipID);
        return HexData.hexStringToBytes(HexData.stringToHexString(chipID));
    }

    public byte[] getMacAddress() {
        String macAddress = mMenuConfigManager.getMacAddress();
        if (macAddress == null || macAddress.isEmpty()) {
            macAddress = "000000000000";
        }
        Logger.d("getMacAddress: " + macAddress);
        String[] strArrSplit = macAddress.split("\\:");
        HexData.hexStringToBytes("000000000000");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArrSplit.length; i++) {
            Logger.d("macData[" + i + "]: " + strArrSplit[i]);
            sb.append(strArrSplit[i]);
        }
        return HexData.hexStringToBytes(sb.toString());
    }

    public boolean setLineIPAddress(byte[] bArr) {
        Logger.d("start to set Line IP.");
        if (bArr.length != 19) {
            Logger.d("set network info error(set ip lenght error)(Config data.length != 19).");
            return false;
        }
        String str = HexData.decodeToInteger(bArr[0]) + "." + HexData.decodeToInteger(bArr[1]) + "." + HexData.decodeToInteger(bArr[2]) + "." + HexData.decodeToInteger(bArr[3]);
        String str2 = HexData.decodeToInteger(bArr[5]) + "." + HexData.decodeToInteger(bArr[6]) + "." + HexData.decodeToInteger(bArr[7]) + "." + HexData.decodeToInteger(bArr[8]);
        String str3 = HexData.decodeToInteger(bArr[10]) + "." + HexData.decodeToInteger(bArr[11]) + "." + HexData.decodeToInteger(bArr[12]) + "." + HexData.decodeToInteger(bArr[13]);
        Logger.d("ipinfo: " + str + "\n subnetMaskInfo: " + str2 + "\n gatewayInfo: " + str3 + "\n dnsInfo: 0.0.0.0");
        manualConnectEthernet(str, 24, str3, "0.0.0.0");
        return true;
    }

    public void manualConnectEthernet(String str, int i, String str2, String str3) {
        HisLog.i("==========Start Ethernet Static Connect===========");
        IpConfiguration configuration = getConfiguration();
        this.mIpConfiguration = configuration;
        if (configuration == null) {
            this.mIpConfiguration = new IpConfiguration();
        }
        this.mIpConfiguration.setIpAssignment(IpConfiguration.IpAssignment.STATIC);
        StaticIpConfiguration staticIpConfiguration = new StaticIpConfiguration();
        if (str == null) {
            HisLog.d("Invalid Ip Address");
            return;
        }
        try {
            try {
                staticIpConfiguration.ipAddress = new LinkAddress((Inet4Address) NetworkUtils.numericToInetAddress(str), i);
                if (str2 != null) {
                    try {
                        staticIpConfiguration.gateway = (Inet4Address) NetworkUtils.numericToInetAddress(str2);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                if (str3 != null) {
                    try {
                        staticIpConfiguration.dnsServers.add((Inet4Address) NetworkUtils.numericToInetAddress(str3));
                    } catch (IllegalArgumentException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                this.mIpConfiguration.setStaticIpConfiguration(staticIpConfiguration);
                HisLog.d("ip: " + staticIpConfiguration.ipAddress + "\ngateway: " + staticIpConfiguration.gateway + "\ndns: " + staticIpConfiguration.dnsServers);
                HisLog.d("mIpConfiguration=" + this.mIpConfiguration);
                String str4 = "ifconfig eth0 " + staticIpConfiguration.ipAddress;
                HisLog.d("ipCommand = " + str4);
                FacFunction_Adapter.getInstance(this.mContext).setIPTakesEffectImmediately(str4);
                this.mEthernetManager.setConfiguration(this.mEthernetInterfaceName, this.mIpConfiguration);
                new TcpServer(this.mContext).start();
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
            }
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        }
    }

    public byte[] getLineIPAddress() {
        Logger.d("start to getLineIPAddress");
        byte[] bArrHexStringToBytes = HexData.hexStringToBytes("00000000010000000002000000000300000000");
        StringBuilder sb = new StringBuilder();
        try {
            String iPAddr = MenuConfigManager.getInstance(this.mContext).getIPAddr();
            ipString = iPAddr;
            sb.append(this.mSerialFacFunction.splitNetworkInfo(iPAddr));
            sb.append("01");
            sb.append("00000000");
            sb.append("02");
            sb.append("00000000");
            sb.append("03");
            sb.append("00000000");
            Logger.d("Read Line Ip hexString: " + sb.toString());
            return HexData.hexStringToBytes(sb.toString());
        } catch (Exception e) {
            Logger.e("getLineIpAddress: " + e.getMessage());
            return bArrHexStringToBytes;
        }
    }

    public IpConfiguration getConfiguration() {
        IpConfiguration configuration;
        String[] availableInterfaces = this.mEthernetManager.getAvailableInterfaces();
        if (availableInterfaces.length > 0) {
            String str = availableInterfaces[0];
            this.mEthernetInterfaceName = str;
            configuration = this.mEthernetManager.getConfiguration(str);
        } else {
            configuration = null;
        }
        if (configuration == null) {
            HisLog.i("IpConfiguration,Ethernet IpConfiguration is null,null returned");
        } else {
            HisLog.d("get Configuration:" + configuration.toString());
        }
        return configuration;
    }

    public EthernetManager getEthernetManager(Context context) {
        if (context == null) {
            HisLog.i("getEthernetManager,context is null ,null return");
            return null;
        }
        EthernetManager ethernetManager = (EthernetManager) context.getSystemService("ethernet");
        if (ethernetManager != null) {
            return ethernetManager;
        }
        HisLog.i("getEthernetManager,manager is null ,null return");
        return null;
    }

    public byte[] getWirelessIP() {
        Logger.d("getWirelessIP.");
        HexData.hexStringToBytes("00000000010000000002000000000300000000");
        return this.mSerialFacFunction.getWifiIPInfo();
    }

    public byte[] getWirelessSSID() {
        Logger.d("getWirelessSSID");
        return this.mSerialFacFunction.getScanSSIDResult();
    }

    public boolean setDevice23bitNum(byte[] bArr) {
        Logger.d("setDevice15bitNum");
        String string = "";
        for (byte b : bArr) {
            try {
                int iDecodeToInteger = HexData.decodeToInteger(b);
                if (iDecodeToInteger >= 48 && iDecodeToInteger <= 57) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(iDecodeToInteger - 48);
                    string = sb.toString();
                } else if (iDecodeToInteger >= 97 && iDecodeToInteger <= 122) {
                    string = string + ((char) (iDecodeToInteger - 32));
                } else if (iDecodeToInteger >= 65 && iDecodeToInteger <= 90) {
                    string = string + ((char) iDecodeToInteger);
                }
            } catch (Exception e) {
                Logger.e(e.getMessage());
                return false;
            }
        }
        Logger.e("setDevice15bitNum = " + string);
        Logger.e("setDevice15bitNum str.length() = " + string.length());
        if (string.length() != this.FANGCHUANHUO_NUMBER_DISPLAY_COUNT) {
            Logger.d("SN length no correct!");
            return false;
        }
        mMenuConfigManager.setNewFangChuanHuoValue(string);
        return true;
    }

    public boolean saveConfigFile(byte[] bArr) {
        Logger.d("saveConfigFile");
        for (int i = 1; i < bArr.length; i++) {
            try {
                char cDecodeToInteger = (char) HexData.decodeToInteger(bArr[i]);
                if (cDecodeToInteger != '\n' && cDecodeToInteger != '\r') {
                    this.mSerialSaveFile.saveResult(cDecodeToInteger);
                } else if (cDecodeToInteger == '\r') {
                    this.mSerialSaveFile.saveResult('\n');
                }
            } catch (Exception e) {
                Logger.e(e.getMessage());
                return false;
            }
        }
        this.mSerialSaveFile.closeWriter();
        return false;
    }

    public void initSerialFile() {
        SerialSaveFile serialSaveFile = new SerialSaveFile();
        this.mSerialSaveFile = serialSaveFile;
        serialSaveFile.initFileWriter(this.mContext, "result.txt");
    }

    public boolean setFunctionNumber(byte[] bArr) {
        Logger.d("setFunctionNumber");
        try {
            String str = new String(bArr, "UTF-8");
            Logger.e("setFunctionNumber" + str);
            FacFunction_Adapter.getInstance(this.mContext).setFunctionNumber(str);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public boolean setChipNumber(byte[] bArr) {
        Logger.d("setChipNumber");
        String string = "";
        for (byte b : bArr) {
            try {
                int iDecodeToInteger = HexData.decodeToInteger(b);
                if (iDecodeToInteger >= 48 && iDecodeToInteger <= 57) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(iDecodeToInteger - 48);
                    string = sb.toString();
                } else if (iDecodeToInteger >= 97 && iDecodeToInteger <= 122) {
                    string = string + ((char) (iDecodeToInteger - 32));
                } else if (iDecodeToInteger >= 65 && iDecodeToInteger <= 90) {
                    string = string + ((char) iDecodeToInteger);
                } else {
                    string = string + ((char) iDecodeToInteger);
                }
            } catch (Exception e) {
                Logger.e(e.getMessage());
                return false;
            }
        }
        Logger.e("setChipNumber" + string);
        FacFunction_Adapter.getInstance(this.mContext).setChipNumber(string);
        return true;
    }

    public byte[] getDevice23bitNum() {
        Logger.d("getDevice23bitNum");
        try {
            String newFangChuanHuoString = mMenuConfigManager.getNewFangChuanHuoString();
            Logger.e("getDevice23bitNum" + newFangChuanHuoString);
            return HexData.hexStringToBytes(HexData.stringToHexString(newFangChuanHuoString));
        } catch (Exception e) {
            Logger.e("getDevice23bitNum" + e.getMessage());
            return null;
        }
    }

    public byte[] getChipVersion() {
        Logger.d("getChipVersion");
        try {
            return HexData.hexStringToBytes(HexData.stringToHexString(FacFunction_Adapter.getInstance(this.mContext).getChipVersion()));
        } catch (Exception e) {
            Logger.e("getChipVersion" + e.getMessage());
            return null;
        }
    }

    public byte[] getOriginDevice23bitNum() {
        Logger.d("getOriginDevice23bitNum");
        try {
            String orignalFangChuanHuoString = mMenuConfigManager.getOrignalFangChuanHuoString();
            Logger.e("getDevice23bitNum" + orignalFangChuanHuoString);
            return HexData.hexStringToBytes(HexData.stringToHexString(orignalFangChuanHuoString));
        } catch (Exception e) {
            Logger.e("getDevice23bitNum" + e.getMessage());
            return null;
        }
    }

    public byte[] getFunctionNumber() {
        Logger.d("getFunctionNumber");
        try {
            String functionNumber = FacFunction_Adapter.getInstance(this.mContext).getFunctionNumber();
            Logger.e("getFunctionNumber" + functionNumber);
            return HexData.hexStringToBytes(HexData.stringToHexString(functionNumber));
        } catch (Exception e) {
            Logger.e("getFunctionNumber" + e.getMessage());
            return null;
        }
    }

    public byte[] getCountry() {
        Logger.d("getCountry");
        try {
            String countryName = FacFunction_Adapter.getInstance(this.mContext).getCountryName();
            Logger.e("getCountry" + countryName);
            return HexData.hexStringToBytes(HexData.stringToHexString(countryName));
        } catch (Exception e) {
            Logger.e("getCountry" + e.getMessage());
            return null;
        }
    }

    public byte[] getLanguage() {
        Logger.d("getLanguage");
        try {
            String str = FacFunction_Adapter.getInstance(this.mContext).getlanguageName();
            Logger.e("getLanguage" + str);
            return HexData.hexStringToBytes(HexData.stringToHexString(str));
        } catch (Exception e) {
            Logger.e("getLanguage" + e.getMessage());
            return null;
        }
    }

    public byte[] getLogo() {
        Logger.d("getLogo");
        try {
            String logoName = FacFunction_Adapter.getInstance(this.mContext).getLogoName();
            Logger.e("getLogo" + logoName);
            return HexData.hexStringToBytes(HexData.stringToHexString(logoName));
        } catch (Exception e) {
            Logger.e("getLogo" + e.getMessage());
            return null;
        }
    }

    public byte[] getChipNumber() {
        Logger.d("getChipNumber");
        try {
            String chipNumber = FacFunction_Adapter.getInstance(this.mContext).getChipNumber();
            Logger.e("getChipNumber" + chipNumber);
            return HexData.hexStringToBytes(HexData.stringToHexString(chipNumber));
        } catch (Exception e) {
            Logger.e("getChipNumber" + e.getMessage());
            return null;
        }
    }

    public byte[] getServiceTag() {
        Logger.d("getServiceTag");
        if (mMenuConfigManager.getLogoName().equalsIgnoreCase("Sunbrite") || mMenuConfigManager.getLogoName().equalsIgnoreCase("snb")) {
            try {
                String serviceTag = FacFunction_Adapter.getInstance(this.mContext).getServiceTag();
                Logger.e("getServiceTag:" + serviceTag);
                return HexData.hexStringToBytes(HexData.stringToHexString(serviceTag));
            } catch (Exception e) {
                Logger.e("getServiceTag:" + e.getMessage());
            }
        }
        return null;
    }

    public boolean setServiceTag(byte[] bArr) {
        Logger.d("set 16 bit Service Tag");
        if (!mMenuConfigManager.getLogoName().equalsIgnoreCase("Sunbrite") && !mMenuConfigManager.getLogoName().equalsIgnoreCase("snb")) {
            return false;
        }
        String string = "";
        for (byte b : bArr) {
            try {
                int iDecodeToInteger = HexData.decodeToInteger(b);
                if (iDecodeToInteger >= 48 && iDecodeToInteger <= 57) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(iDecodeToInteger - 48);
                    string = sb.toString();
                } else if (iDecodeToInteger >= 65 && iDecodeToInteger <= 122) {
                    string = string + ((char) iDecodeToInteger);
                }
            } catch (Exception e) {
                Logger.e(e.getMessage());
                return false;
            }
        }
        Logger.e("setServiceTag = " + string);
        Logger.e("setServiceTag str.length() = " + string.length());
        if (string.length() == 16 && string.startsWith("ST")) {
            FacFunction_Adapter.getInstance(this.mContext).setServiceTag(string);
            return true;
        }
        Logger.d("Service Tag length not correct!");
        return false;
    }

    public boolean compareMachineName(byte[] bArr) {
        byte[] bArrHexStringToBytes;
        Logger.d("getMachineName");
        try {
            String productType = FacFunction_Adapter.getInstance(this.mContext).getProductType();
            String versionInfo = FacFunction_Adapter.getInstance(this.mContext).getVersionInfo();
            String[] strArrSplit = versionInfo.split("\\.");
            String strStringToHexString = HexData.stringToHexString(productType);
            String strSubstring = strArrSplit[0].substring(1, versionInfo.indexOf("."));
            Logger.d("whole getMachineName" + productType + "(" + strSubstring + ")");
            if (!strSubstring.equalsIgnoreCase("0000")) {
                strStringToHexString = HexData.stringToHexString(productType + "(" + strSubstring + ")");
            }
            bArrHexStringToBytes = HexData.hexStringToBytes(strStringToHexString);
        } catch (Exception e) {
            Logger.e("getMachineName" + e.getMessage());
            bArrHexStringToBytes = null;
        }
        return Arrays.equals(bArrHexStringToBytes, bArr);
    }

    public boolean setCountry(byte[] bArr) {
        Logger.d("setContry");
        String string = "";
        for (byte b : bArr) {
            try {
                int iDecodeToInteger = HexData.decodeToInteger(b);
                if (iDecodeToInteger >= 48 && iDecodeToInteger <= 57) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(iDecodeToInteger - 48);
                    string = sb.toString();
                } else if (iDecodeToInteger >= 97 && iDecodeToInteger <= 122) {
                    string = string + ((char) (iDecodeToInteger - 32));
                } else if (iDecodeToInteger >= 65 && iDecodeToInteger <= 90) {
                    string = string + ((char) iDecodeToInteger);
                } else {
                    string = string + ((char) iDecodeToInteger);
                }
            } catch (Exception e) {
                Logger.e(e.getMessage());
                return false;
            }
        }
        Logger.e("setCountry" + string);
        mMenuConfigManager.setLogToTvcertificate("setCountry" + string);
        return FacFunction_Adapter.getInstance(this.mContext).setCountryString(string);
    }

    public boolean setLanguage(byte[] bArr) {
        Logger.d("setLanguage");
        String string = "";
        for (byte b : bArr) {
            try {
                int iDecodeToInteger = HexData.decodeToInteger(b);
                if (iDecodeToInteger >= 48 && iDecodeToInteger <= 57) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(iDecodeToInteger - 48);
                    string = sb.toString();
                } else if (iDecodeToInteger >= 97 && iDecodeToInteger <= 122) {
                    string = string + ((char) (iDecodeToInteger - 32));
                } else if (iDecodeToInteger >= 65 && iDecodeToInteger <= 90) {
                    string = string + ((char) iDecodeToInteger);
                } else {
                    string = string + ((char) iDecodeToInteger);
                }
            } catch (Exception e) {
                Logger.e(e.getMessage());
                return false;
            }
        }
        Logger.e("setLanguage" + string);
        mMenuConfigManager.setLogToTvcertificate("setLanguage" + string);
        return FacFunction_Adapter.getInstance(this.mContext).setLanguageString(string);
    }

    public boolean setLogo(byte[] bArr) {
        Logger.d("setLanguage");
        String string = "";
        for (byte b : bArr) {
            try {
                int iDecodeToInteger = HexData.decodeToInteger(b);
                if (iDecodeToInteger >= 48 && iDecodeToInteger <= 57) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(iDecodeToInteger - 48);
                    string = sb.toString();
                } else if (iDecodeToInteger >= 97 && iDecodeToInteger <= 122) {
                    string = string + ((char) (iDecodeToInteger - 32));
                } else if (iDecodeToInteger >= 65 && iDecodeToInteger <= 90) {
                    string = string + ((char) iDecodeToInteger);
                } else {
                    string = string + ((char) iDecodeToInteger);
                }
            } catch (Exception e) {
                Logger.e(e.getMessage());
                return false;
            }
        }
        Logger.e("setLogo" + string);
        mMenuConfigManager.setLogToTvcertificate("setLogo" + string);
        return FacFunction_Adapter.getInstance(this.mContext).setLogoString(string);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x004d  */
    /* JADX WARN: Code duplicated, block: B:18:0x006b  */
    /* JADX WARN: Instruction removed from duplicated block: B:17:0x004d, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:18:0x006b, please report this as an issue */
    public byte[] getCICardInfo() {
        String cIInfo;
        boolean z = true;
        try {
            if (FacFunction_Adapter.getInstance(this.mContext).getCurrentSource().equals(FacPropertyDemo.SourceEnum.DTV)) {
                if (!this.mSerialCamDisplayManager.enterCamMenu(this.mContext, true)) {
                    HisLog.i(" serialcommands :There is no available CA and CI Card");
                    mMenuConfigManager.displayHisToast("There is no available CA and CI Card");
                }
                Thread.sleep(1500L);
                if (z) {
                    cIInfo = this.mSerialCamDisplayManager.getCIInfo();
                    Logger.i("getCICardInfo " + cIInfo);
                    this.mSerialCamDisplayManager.exitCamMenu();
                } else {
                    cIInfo = "0";
                    Logger.i("getCICardInfo 0");
                }
                return HexData.hexStringToBytes(HexData.stringToHexString(cIInfo));
            }
            mMenuConfigManager.displayHisToast(this.mContext.getResources().getString(R.string.str_dtv_operate_warning));
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        z = false;
        if (z) {
            cIInfo = this.mSerialCamDisplayManager.getCIInfo();
            Logger.i("getCICardInfo " + cIInfo);
            this.mSerialCamDisplayManager.exitCamMenu();
        } else {
            cIInfo = "0";
            Logger.i("getCICardInfo 0");
        }
        return HexData.hexStringToBytes(HexData.stringToHexString(cIInfo));
    }

    public byte[] getCICardInfoAhead() {
        String cIInfo;
        boolean z = true;
        if (!FacFunction_Adapter.getInstance(this.mContext).getCurrentSource().equals(FacPropertyDemo.SourceEnum.DTV)) {
            z = false;
        } else if (!this.mSerialCamDisplayManager.enterCamMenu(this.mContext, true)) {
            HisLog.i(" serialcommands :There is no available CA and CI Card");
            z = false;
        }
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (z) {
            cIInfo = this.mSerialCamDisplayManager.getCIInfo();
            Logger.i("getCICardInfo " + cIInfo);
            this.mSerialCamDisplayManager.exitCamMenu();
        } else {
            cIInfo = "0";
            Logger.i("getCICardInfo 0");
        }
        return HexData.hexStringToBytes(HexData.stringToHexString(cIInfo));
    }

    public boolean setRDrvPlus(int i) {
        Logger.d("setRDrvPlus " + i);
        int rdrv = FacFunction_Adapter.getInstance(this.mContext).getRDRV() + i;
        try {
            if (rdrv <= 255) {
                FacFunction_Adapter.getInstance(this.mContext).setRDRV(rdrv);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setRDRV(255);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public int getRDrv() {
        int rdrv = FacFunction_Adapter.getInstance(this.mContext).getRDRV();
        Logger.d("getRDrv " + rdrv);
        return rdrv;
    }

    public byte[] getWideVineKey() {
        Logger.d("getWideVineKey");
        try {
            String deviceID = FacFunction_Adapter.getInstance(this.mContext).getDeviceID();
            Logger.e("getWideVineKey" + deviceID);
            return HexData.hexStringToBytes(HexData.stringToHexString(deviceID));
        } catch (Exception e) {
            Logger.e("getWideVineKey" + e.getMessage());
            return null;
        }
    }

    public boolean setRDrvMinus(int i) {
        int rdrv = FacFunction_Adapter.getInstance(this.mContext).getRDRV();
        Logger.d("setRDrvMinus " + i);
        int i2 = rdrv - i;
        try {
            if (i2 >= 0) {
                FacFunction_Adapter.getInstance(this.mContext).setRDRV(i2);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setRDRV(0);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public boolean setGDrvPlus(int i) {
        Logger.d("setGDrvPlus " + i);
        int gdrv = FacFunction_Adapter.getInstance(this.mContext).getGDRV() + i;
        try {
            if (gdrv <= 255) {
                FacFunction_Adapter.getInstance(this.mContext).setGDRV(gdrv);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setGDRV(255);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public int getGDrv() {
        int gdrv = FacFunction_Adapter.getInstance(this.mContext).getGDRV();
        Logger.d("getGDrv " + gdrv);
        return gdrv;
    }

    public boolean setGDrvMinus(int i) {
        Logger.d("setGDrvMinus " + i);
        int gdrv = FacFunction_Adapter.getInstance(this.mContext).getGDRV() - i;
        try {
            if (gdrv >= 0) {
                FacFunction_Adapter.getInstance(this.mContext).setGDRV(gdrv);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setGDRV(0);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public boolean setBDrvPlus(int i) {
        Logger.d("setBDrvPlus " + i);
        int bdrv = FacFunction_Adapter.getInstance(this.mContext).getBDRV() + i;
        try {
            if (bdrv <= 255) {
                FacFunction_Adapter.getInstance(this.mContext).setBDRV(bdrv);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setBDRV(255);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public int getBDrv() {
        int bdrv = FacFunction_Adapter.getInstance(this.mContext).getBDRV();
        Logger.d("getBDrv " + bdrv);
        return bdrv;
    }

    public boolean setBDrvMinus(int i) {
        Logger.d("setBDrvMinus " + i);
        int bdrv = FacFunction_Adapter.getInstance(this.mContext).getBDRV() - i;
        try {
            if (bdrv >= 0) {
                FacFunction_Adapter.getInstance(this.mContext).setBDRV(bdrv);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setBDRV(0);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public boolean setRCutPlus(int i) {
        Logger.d("setRCutPlus " + i);
        int rcut = FacFunction_Adapter.getInstance(this.mContext).getRCUT() + i;
        try {
            if (rcut <= 511) {
                FacFunction_Adapter.getInstance(this.mContext).setRCUT(rcut);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setRCUT(511);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public int getRCut() {
        int rcut = FacFunction_Adapter.getInstance(this.mContext).getRCUT();
        Logger.d("getRCut " + rcut);
        return rcut;
    }

    public boolean setRCutMinus(int i) {
        Logger.d("setRCutMinus " + i);
        int rcut = FacFunction_Adapter.getInstance(this.mContext).getRCUT() - i;
        try {
            if (rcut >= 0) {
                FacFunction_Adapter.getInstance(this.mContext).setRCUT(rcut);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setRCUT(0);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public boolean setGCutPlus(int i) {
        Logger.d("setGCutPlus " + i);
        int gcut = FacFunction_Adapter.getInstance(this.mContext).getGCUT() + i;
        try {
            if (gcut <= 511) {
                FacFunction_Adapter.getInstance(this.mContext).setGCUT(gcut);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setGCUT(511);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public int getGCut() {
        int gcut = FacFunction_Adapter.getInstance(this.mContext).getGCUT();
        Logger.d("getGCut " + gcut);
        return gcut;
    }

    public boolean setGCutMinus(int i) {
        Logger.d("setGCutMinus " + i);
        int gcut = FacFunction_Adapter.getInstance(this.mContext).getGCUT() - i;
        try {
            if (gcut >= 0) {
                FacFunction_Adapter.getInstance(this.mContext).setGCUT(gcut);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setGCUT(0);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public boolean setBCutPlus(int i) {
        Logger.d("setBCutPlus " + i);
        int bcut = FacFunction_Adapter.getInstance(this.mContext).getBCUT() + i;
        try {
            if (bcut <= 511) {
                FacFunction_Adapter.getInstance(this.mContext).setBCUT(bcut);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setBCUT(511);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public int getBCut() {
        int bcut = FacFunction_Adapter.getInstance(this.mContext).getBCUT();
        Logger.d("getBCut " + bcut);
        return bcut;
    }

    public boolean setBCutMinus(int i) {
        Logger.d("setBCutMinus " + i);
        int bcut = FacFunction_Adapter.getInstance(this.mContext).getBCUT() - i;
        try {
            if (bcut >= 0) {
                FacFunction_Adapter.getInstance(this.mContext).setBCUT(bcut);
                return true;
            }
            FacFunction_Adapter.getInstance(this.mContext).setBCUT(0);
            return true;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void factoryOk() {
        FacFunction_Adapter.getInstance(this.mContext).executeFactoryOk(this.factoryokcallback);
    }

    public void sendMessageDelayed(int i, int i2) {
        Message message = new Message();
        message.what = i;
        this.mhHandler.removeMessages(i);
        this.mhHandler.sendMessageDelayed(message, i2);
    }

    public boolean enterOrExistFac(byte[] bArr) {
        Logger.d("enterOrExistFactory");
        boolean zShortPressKey = false;
        if (bArr.length != 1) {
            Logger.e("enterOrExistFac data error!");
            return false;
        }
        int iDecodeToInteger = HexData.decodeToInteger(bArr[0]);
        Logger.d("factory switch value: " + iDecodeToInteger);
        switch (iDecodeToInteger) {
            case 2:
                try {
                    if (mMenuConfigManager.getDefault("HIFAC_STATUS") == 0) {
                        Logger.d("Enter factory M mode");
                        zShortPressKey = this.keySwitcher.shortPressKey(4216);
                        Thread.sleep(1000L);
                    } else {
                        Logger.d("already in factory mode");
                    }
                } catch (Exception e) {
                    Logger.e("enterOrExistFac Exception" + e.getMessage());
                    return zShortPressKey;
                }
                break;
            case 3:
                try {
                    if (mMenuConfigManager.getDefault("HIFAC_STATUS") == 1 && mMenuConfigManager.getDefault("HIFAC_AGING") != 1) {
                        Logger.d("(Exit factory M mode)exit M");
                        this.mContext.sendBroadcast(new Intent("com.hisense.broadcom.HIDE_FAC_MAINMENU"));
                        Logger.i("send hide fac menu broadcast");
                        try {
                            Thread.sleep(300L);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        boolean zShortPressKey2 = this.keySwitcher.shortPressKey(4236);
                        try {
                            mMenuConfigManager.setValue("HIFAC_STATUS", 0);
                            return zShortPressKey2;
                        } catch (Exception e3) {
                            zShortPressKey = zShortPressKey2;
                            e = e3;
                        }
                    } else {
                        if (mMenuConfigManager.getDefault("HIFAC_STATUS") == 1 && mMenuConfigManager.getDefault("HIFAC_AGING") == 1) {
                            return false;
                        }
                        Logger.d("already exit fac");
                    }
                    break;
                } catch (Exception e4) {
                    e = e4;
                }
                Logger.e("enterOrExistFac Exception" + e.getMessage());
                return zShortPressKey;
            case 4:
                try {
                    if (mMenuConfigManager.getDefault("HIFAC_STATUS") == 1 && mMenuConfigManager.getDefault("HIFAC_AGING") == 0) {
                        Logger.d("Enter Aging");
                        this.keySwitcher.shortPressKey(4223);
                    } else {
                        Logger.d("Already in Aging.");
                    }
                } catch (Exception e5) {
                    Logger.e("enterOrExistFac Exception" + e5.getMessage());
                    return false;
                }
                break;
            case 5:
                try {
                    if (mMenuConfigManager.getDefault("HIFAC_STATUS") == 1 && mMenuConfigManager.getDefault("HIFAC_AGING") == 1) {
                        Logger.d("Exit Aging.(閫�鍑鸿�佸寲)");
                        this.keySwitcher.shortPressKey(4299);
                    } else {
                        Logger.d("Already exit Aging.(宸茬粡閫�鍑鸿�佸寲鐘舵��)");
                    }
                } catch (Exception e6) {
                    Logger.e("enterOrExistFac Exception" + e6.getMessage());
                    return false;
                }
                break;
            case 6:
                Logger.d("(user reset to before outside factory.)");
                return false;
            case 7:
                try {
                    Logger.d("protectly cleaning up.");
                    FacFunction_Adapter.getInstance(this.mContext).executeClearProtectly();
                } catch (Exception e7) {
                    Logger.e("enterOrExistFac Exception" + e7.getMessage());
                    return false;
                }
                break;
            case 8:
                try {
                    Logger.d("unprotectly cleaning up");
                    FacFunction_Adapter.getInstance(this.mContext).executeClearUnprotectly();
                } catch (Exception e8) {
                    Logger.e("enterOrExistFac Exception" + e8.getMessage());
                    return false;
                }
                break;
            case 9:
                try {
                    Logger.d("(factory ok)宸ュ巶OK");
                    this.keySwitcher.shortPressKey(4251);
                } catch (Exception e9) {
                    Logger.e("enterOrExistFac Exception" + e9.getMessage());
                    return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }

    public boolean initFactoryChannel(byte[] bArr) {
        if (bArr.length != 1) {
            Logger.e(" initFactoryChannel data error!");
            return false;
        }
        int iDecodeToInteger = HexData.decodeToInteger(bArr[0]);
        Logger.d("factory switch value: " + iDecodeToInteger);
        String channelName = null;
        if (iDecodeToInteger != 0) {
            if (iDecodeToInteger != 1) {
                if (iDecodeToInteger != 2) {
                    if (iDecodeToInteger == 3) {
                        if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                            channelName = FacPropertyDemo.HIFAC_MARKET_HK ? "jiangmen_hk" : "jiangmen";
                        } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
                            channelName = "jiangmen_sa";
                        }
                    }
                } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                    channelName = FacPropertyDemo.HIFAC_MARKET_HK ? "guiyang_hk" : "guiyang";
                }
            } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                channelName = FacPropertyDemo.HIFAC_MARKET_HK ? "huangdao_hk" : "huangdao_em";
            } else {
                channelName = FacPropertyDemo.HIFAC_MARKET_SA ? "qingdao_sa" : "huangdao";
            }
        } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
            channelName = FacPropertyDemo.HIFAC_MARKET_HK ? "qingdao_hk" : "qingdao";
        } else if (FacPropertyDemo.HIFAC_MARKET_SA) {
            channelName = "huangdao_sa";
        }
        if (channelName == null) {
            channelName = SerialCommandsType.getChannelName(iDecodeToInteger);
        }
        if (channelName == null || channelName.isEmpty()) {
            return false;
        }
        mMenuConfigManager.presetChannel(channelName);
        return true;
    }

    public byte[] getAgingTime() {
        HisLog.i("SerialCommands getAgingTime!!!");
        if (this.adapterContext == null) {
            try {
                this.adapterContext = this.mContext.createPackageContext("com.hisense.uifac", 3);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        SharedPreferences sharedPreferences = this.adapterContext.getSharedPreferences("FacShareP", 4);
        this.mShared = sharedPreferences;
        mAgingTimeLong = sharedPreferences.getLong("agingTime", 0L);
        if (mMenuConfigManager.getDefault("HIFAC_AGING") == 1) {
            mAgingTimeLong += SystemClock.uptimeMillis() - this.mShared.getLong("startAgingTime", 0L);
        }
        Logger.i("long agingTime = " + mAgingTimeLong);
        int i = (int) (mAgingTimeLong / 1000);
        Logger.i("agingTimeInt = " + i + "s");
        String strIntegerToHexString = HexData.integerToHexString(i);
        Logger.i("before :" + strIntegerToHexString);
        if (strIntegerToHexString.length() % 2 != 0) {
            strIntegerToHexString = "0" + strIntegerToHexString;
        }
        Logger.i("after :" + strIntegerToHexString);
        return HexData.hexStringToBytes(strIntegerToHexString);
    }

    public byte[] getSoftWareVerSion() {
        StringBuilder sb = new StringBuilder();
        MenuConfigManager menuConfigManager = mMenuConfigManager;
        sb.append(menuConfigManager.updateStrVersion(menuConfigManager.getVersionInfo()));
        sb.append(mMenuConfigManager.getDateInfo(true));
        String string = sb.toString();
        Logger.i("softWareVersion = " + string);
        return HexData.hexStringToBytes(HexData.stringToHexString(string));
    }

    public boolean changeChannels(byte[] bArr) {
        Logger.d("changeChannels data[0]" + ((int) bArr[0]) + " data[1]" + ((int) bArr[1]));
        if (bArr.length != 2) {
            Logger.e("switchChannels number error(data.length != 2).");
            return false;
        }
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            FacFunction_Adapter.getInstance(this.mContext).getChannelListByTunerMode(0);
            FacFunction_Adapter.getInstance(this.mContext).getChannelListByTunerMode(1);
        } else {
            FacFunction_Adapter.getInstance(this.mContext).getAirChannelList();
            FacFunction_Adapter.getInstance(this.mContext).getCableChannelList();
        }
        byte b = bArr[1];
        byte b2 = bArr[0];
        Logger.d("changeChannels minor" + ((int) bArr[0]) + " major" + ((int) bArr[1]));
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            Logger.d("changeChannels channelNum is: " + HexData.byte2ToUnsignedShort(bArr));
            FacFunction_Adapter.getInstance(this.mContext).changeChannel(b);
        } else if (b2 != 0) {
            FacFunction_Adapter.getInstance(this.mContext).changeChannelForId(b, b2);
        } else {
            FacFunction_Adapter.getInstance(this.mContext).changeChannel(b);
        }
        this.keySwitcher.shortPressKey(112234);
        Logger.d("finish changing channels.");
        return true;
    }

    public boolean switchAVSource(byte[] bArr) {
        Logger.d("enter switchAVSource");
        if (bArr.length != 1) {
            Logger.e("switchAVSource data error!");
            return false;
        }
        int iDecodeToInteger = HexData.decodeToInteger(bArr[0]);
        Logger.d("switch to AV : " + iDecodeToInteger + 1);
        if (iDecodeToInteger == 0) {
            this.keySwitcher.shortPressKey(4229);
            return true;
        }
        Logger.e("switchAVSource data error, not available source");
        return false;
    }

    public boolean switchYPbPrSource(byte[] bArr) {
        Logger.d("enter switchYPbPrSource");
        if (bArr.length != 1) {
            Logger.e("switchYPbPrSource data error!");
            return false;
        }
        Logger.d("switch to YPbPr : " + HexData.decodeToInteger(bArr[0]) + 1);
        Logger.e("switchYPbPrSource data error, not available source");
        return false;
    }

    public boolean switchHDMISource(byte[] bArr) {
        Logger.d("enter switchHDMISource");
        if (bArr.length != 1) {
            Logger.e("switchHDMISource data error!");
            return false;
        }
        int iDecodeToInteger = HexData.decodeToInteger(bArr[0]);
        Logger.d("switch to HDMI : " + iDecodeToInteger);
        if (iDecodeToInteger == 0) {
            if (!FacPropertyDemo.SourceEnum.HDMI1.isEnabled() || !FacPropertyDemo.SourceEnum.HDMI1.isShown()) {
                return false;
            }
            this.mFunctionAdapter.switchSource(FacPropertyDemo.SourceEnum.HDMI1);
            this.keySwitcher.shortPressKey(112234);
            return true;
        }
        if (iDecodeToInteger == 1) {
            if (!FacPropertyDemo.SourceEnum.HDMI2.isEnabled() || !FacPropertyDemo.SourceEnum.HDMI2.isShown()) {
                return false;
            }
            this.mFunctionAdapter.switchSource(FacPropertyDemo.SourceEnum.HDMI2);
            this.keySwitcher.shortPressKey(112234);
            return true;
        }
        if (iDecodeToInteger == 2) {
            if (!FacPropertyDemo.SourceEnum.HDMI3.isEnabled() || !FacPropertyDemo.SourceEnum.HDMI3.isShown()) {
                return false;
            }
            this.mFunctionAdapter.switchSource(FacPropertyDemo.SourceEnum.HDMI3);
            this.keySwitcher.shortPressKey(112234);
            return true;
        }
        if (iDecodeToInteger == 3) {
            if (!FacPropertyDemo.SourceEnum.HDMI4.isEnabled() || !FacPropertyDemo.SourceEnum.HDMI4.isShown()) {
                return false;
            }
            this.mFunctionAdapter.switchSource(FacPropertyDemo.SourceEnum.HDMI4);
            this.keySwitcher.shortPressKey(112234);
            return true;
        }
        Logger.e("switchHDMISource data error, not available source");
        return false;
    }

    public boolean switchVGASource(byte[] bArr) {
        Logger.d("enter switchVGASource");
        if (bArr.length != 1) {
            Logger.e("switchVGASource data error!");
            return false;
        }
        int iDecodeToInteger = HexData.decodeToInteger(bArr[0]);
        Logger.d("switch to VGA : " + iDecodeToInteger + 1);
        if (iDecodeToInteger != 0) {
            Logger.e("switchHDMISource data error, not available source");
        }
        return false;
    }

    public boolean setVolume(int i) {
        if (i < 0 || i > 100) {
            return false;
        }
        Logger.d("test setVolume " + i);
        FacFunction_Adapter.getInstance(this.mContext).setVolume(i);
        return true;
    }

    public boolean setBrightness(int i) {
        Logger.d("test setBrightness " + i);
        if (i < 0 || i > 100) {
            return false;
        }
        FacFunction_Adapter.getInstance(this.mContext).setBrightness(i);
        return true;
    }

    public boolean setContrast(int i) {
        Logger.d("test setBrightness " + i);
        if (i < 0 || i > 100) {
            return false;
        }
        FacFunction_Adapter.getInstance(this.mContext).setContrast(i);
        return true;
    }

    public boolean setColor(int i) {
        Logger.d("test setColor " + i);
        FacFunction_Adapter.getInstance(this.mContext).setColor(i);
        return true;
    }

    public boolean setBacklight(int i) {
        Logger.d("test setBacklight " + i);
        if (i < 0 || i > 100) {
            return false;
        }
        FacFunction_Adapter.getInstance(this.mContext).setBacklight(i);
        return true;
    }

    public boolean setZoomMode(int i) {
        Logger.d("test setZoomMode " + i);
        int i2 = 0;
        switch (i) {
            case 0:
                i2 = 1;
                break;
            case 1:
                i2 = 2;
                break;
            case 2:
                i2 = 3;
                break;
            case 3:
                i2 = 7;
                break;
            case 4:
                i2 = 6;
                break;
            case 5:
                i2 = 5;
                break;
            case 6:
                break;
            default:
                return false;
        }
        FacFunction_Adapter.getInstance(this.mContext).setZoomMode(i2);
        return true;
    }

    public boolean set3DMode(int i) {
        Logger.d("test set3DMode " + i);
        FacFunction_Adapter.getInstance(this.mContext).set3DMode(SerialToFac(i));
        return true;
    }

    public boolean setModelPanel(int i) {
        Logger.d("test paneltype " + i);
        if (i > 0) {
            return false;
        }
        FacFunction_Adapter.getInstance(this.mContext).setModelPanel(ApVsTILmap.ApToTil(2, i));
        return true;
    }

    public boolean setPictureMode(int i) {
        Logger.d("test setPictureMode " + i);
        boolean z = FacPropertyDemo.HIFAC_MARKET_JP;
        int i2 = 0;
        if (i != 0) {
            switch (i) {
                case 3:
                    i2 = 3;
                    break;
                case 4:
                    i2 = 4;
                    break;
                case 5:
                    i2 = 5;
                    break;
                case 6:
                    i2 = 2;
                    break;
                case 7:
                    break;
                case 8:
                    i2 = 6;
                    break;
                default:
                    return false;
            }
        } else {
            i2 = 1;
        }
        FacFunction_Adapter.getInstance(this.mContext).setPictureMode(i2);
        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.keySwitcher.shortPressKey(4385);
        return true;
    }

    public boolean setPictureModeEM(int i) {
        Logger.d("test setPictureModeEM " + i);
        int i2 = 0;
        if (i == 0) {
            i2 = 1;
        } else if (i == 6) {
            i2 = 2;
        } else if (i != 7) {
            return false;
        }
        FacFunction_Adapter.getInstance(this.mContext).setPictureMode(i2);
        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.keySwitcher.shortPressKey(4385);
        return true;
    }

    public boolean testPattern(byte[] bArr) {
        PatternPanelController patternPanelController = PatternPanelController.getInstance(this.mContext);
        patternPanelController.onHidePanels();
        Logger.d("start test pattern.");
        boolean z = true;
        if (bArr.length != 1) {
            Logger.d("setPicturePattern data length error.");
            return false;
        }
        try {
            try {
                switch (HexData.decodeToInteger(bArr[0])) {
                    case 0:
                        Logger.d("switch to off pattern");
                        patternPanelController.onHidePanels();
                        mMenuConfigManager.setTestPattern(0, 0, 0, 0);
                        this.keySwitcher.shortPressKey(112238);
                        return true;
                    case 1:
                        Logger.d("switch to white pattern");
                        mMenuConfigManager.setTestPattern(1, 255, 255, 255);
                        this.keySwitcher.shortPressKey(112237);
                        return true;
                    case 2:
                        Logger.d("switch to red pattern");
                        mMenuConfigManager.setTestPattern(1, 255, 0, 0);
                        this.keySwitcher.shortPressKey(112237);
                        return true;
                    case 3:
                        Logger.d("switch to green pattern");
                        mMenuConfigManager.setTestPattern(1, 0, 255, 0);
                        this.keySwitcher.shortPressKey(112237);
                        return true;
                    case 4:
                        Logger.d("switch to blue pattern");
                        mMenuConfigManager.setTestPattern(1, 0, 0, 255);
                        this.keySwitcher.shortPressKey(112237);
                        return true;
                    case 5:
                        Logger.d("switch to black pattern");
                        if (FacPropertyDemo.HIFAC_LOCALDIMMING_SUPPORT) {
                            Logger.d("black card to set local dimming false 1");
                            mMenuConfigManager.setBackLightOn(true);
                            mMenuConfigManager.getLocalDimmingState();
                            mMenuConfigManager.setLocalDimmingSwitch(false);
                        }
                        mMenuConfigManager.setTestPattern(1, 0, 0, 0);
                        this.keySwitcher.shortPressKey(112237);
                        return true;
                    case 6:
                        Logger.d("switch to circle pattern");
                        Intent intent = new Intent(this.mContext, (Class<?>) CirclePatternActivity.class);
                        intent.setFlags(268435456);
                        this.mContext.startActivity(intent);
                        mMenuConfigManager.setTestPattern(0, 0, 0, 0);
                        return true;
                    default:
                        Logger.d("testPattern default action.");
                        return false;
                }
            } catch (Exception e) {
                e = e;
                Logger.d("testPattern error: " + e.getMessage());
                return z;
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
        }
    }

    public boolean setColorTemprature(int i) {
        int i2 = 0;
        if (i == 0) {
            i2 = 3;
        } else if (i != 2) {
            if (i != 4) {
                return false;
            }
            i2 = 5;
        }
        return FacFunction_Adapter.getInstance(this.mContext).setColorTemperature(i2);
    }

    public boolean setEnergySaving(int i) {
        int i2;
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    i2 = FacTvsFunctionUtil.TVS_SAVING_MODE_STANDARD;
                    break;
                case 8:
                    i2 = FacTvsFunctionUtil.TVS_SAVING_MODE_1;
                    break;
                case 9:
                    i2 = FacTvsFunctionUtil.TVS_SAVING_MODE_2;
                    break;
                default:
                    i2 = FacTvsFunctionUtil.TVS_SAVING_MODE_STANDARD;
                    break;
            }
            return FacFunction_Adapter.getInstance(this.mContext).setSavingMode(i2);
        }
        return FacFunction_Adapter.getInstance(this.mContext).setSavingMode(i);
    }

    public boolean setRDRV(int i) {
        if (i > 255) {
            i = 255;
        }
        FacFunction_Adapter.getInstance(this.mContext).setRDRV(i);
        return true;
    }

    public boolean setGDRV(int i) {
        if (i > 255) {
            i = 255;
        }
        FacFunction_Adapter.getInstance(this.mContext).setGDRV(i);
        return true;
    }

    public boolean setBDRV(int i) {
        if (i > 255) {
            i = 255;
        }
        FacFunction_Adapter.getInstance(this.mContext).setBDRV(i);
        return true;
    }

    public boolean setRCUT(int i) {
        FacFunction_Adapter.getInstance(this.mContext).setRCUT(i);
        return true;
    }

    public boolean setGCUT(int i) {
        FacFunction_Adapter.getInstance(this.mContext).setGCUT(i);
        return true;
    }

    public boolean setBCut(int i) {
        FacFunction_Adapter.getInstance(this.mContext).setBCUT(i);
        return true;
    }

    public boolean switchDTVType(byte[] bArr) {
        Logger.d("switchDtvType");
        if (bArr.length != 1) {
            Logger.e("switchDtvType data error!");
        } else {
            int iDecodeToInteger = HexData.decodeToInteger(bArr[0]);
            Logger.d("dtv type switch value: " + iDecodeToInteger);
            if (iDecodeToInteger == 0) {
                FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.keySwitcher.shortPressKey(4227);
            } else if (iDecodeToInteger != 1) {
                if (iDecodeToInteger == 2) {
                    if (!FacPropertyDemo.HIFAC_MARKET_HK) {
                        return false;
                    }
                    FacFunction_Adapter.getInstance(this.mContext).switchSource(FacPropertyDemo.SourceEnum.TV);
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    this.keySwitcher.shortPressKey(4304);
                } else if (iDecodeToInteger != 3) {
                    if (iDecodeToInteger != 5) {
                        if (iDecodeToInteger != 6) {
                            if (iDecodeToInteger != 7) {
                                if (iDecodeToInteger != 9) {
                                    if (iDecodeToInteger != 10) {
                                        if (iDecodeToInteger == 12) {
                                            if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                                FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                                                try {
                                                    Thread.sleep(500L);
                                                } catch (InterruptedException e3) {
                                                    e3.printStackTrace();
                                                }
                                                this.keySwitcher.shortPressKey(12);
                                            }
                                        } else {
                                            switch (iDecodeToInteger) {
                                                case 17:
                                                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                                        FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                                                        try {
                                                            Thread.sleep(500L);
                                                        } catch (InterruptedException e4) {
                                                            e4.printStackTrace();
                                                        }
                                                        this.keySwitcher.shortPressKey(8);
                                                    }
                                                    break;
                                                case 18:
                                                    if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                                        FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                                                        try {
                                                            Thread.sleep(500L);
                                                        } catch (InterruptedException e5) {
                                                            e5.printStackTrace();
                                                        }
                                                        this.keySwitcher.shortPressKey(9);
                                                    }
                                                    break;
                                                case 19:
                                                    if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
                                                        FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                                                        try {
                                                            Thread.sleep(500L);
                                                        } catch (InterruptedException e6) {
                                                            e6.printStackTrace();
                                                        }
                                                        this.keySwitcher.shortPressKey(4397);
                                                    }
                                                    break;
                                                default:
                                                    return false;
                                            }
                                        }
                                    } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                        FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                                        try {
                                            Thread.sleep(500L);
                                        } catch (InterruptedException e7) {
                                            e7.printStackTrace();
                                        }
                                        this.keySwitcher.shortPressKey(11);
                                    }
                                } else if (FacPropertyDemo.HIFAC_MARKET_JP) {
                                    FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                                    try {
                                        Thread.sleep(500L);
                                    } catch (InterruptedException e8) {
                                        e8.printStackTrace();
                                    }
                                    this.keySwitcher.shortPressKey(10);
                                }
                            } else if (!FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                                FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                                try {
                                    Thread.sleep(500L);
                                } catch (InterruptedException e9) {
                                    e9.printStackTrace();
                                }
                                this.keySwitcher.shortPressKey(4384);
                            }
                        } else if (!FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                            FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                            try {
                                Thread.sleep(500L);
                            } catch (InterruptedException e10) {
                                e10.printStackTrace();
                            }
                            this.keySwitcher.shortPressKey(4305);
                        }
                    } else if (!FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                        FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e11) {
                            e11.printStackTrace();
                        }
                        this.keySwitcher.shortPressKey(4383);
                    }
                } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                    FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e12) {
                        e12.printStackTrace();
                    }
                    this.keySwitcher.shortPressKey(4302);
                }
            } else if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                FacFunction_Adapter.getInstance(this.mContext).switchSource(0);
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e13) {
                    e13.printStackTrace();
                }
                this.keySwitcher.shortPressKey(4301);
            }
        }
        return true;
    }

    public byte[] getBluetoothMacAddr() {
        Logger.i("getBluetoothMacAddr");
        String address = BluetoothAdapter.getDefaultAdapter().getAddress();
        Logger.i("getBluetoothMacAddr" + address);
        String[] strArrSplit = address.split("\\:");
        HexData.hexStringToBytes("000000000000");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArrSplit.length; i++) {
            Logger.d("macData[" + i + "]: " + strArrSplit[i]);
            sb.append(strArrSplit[i]);
        }
        return HexData.hexStringToBytes(sb.toString());
    }

    public boolean setVcom(int i) {
        if (-1 >= i || i >= 256) {
            return false;
        }
        HisLog.i("setVcom mVcomType is : " + this.mVcomType);
        int iTransformVcomValue = transformVcomValue(i);
        HisLog.i("setVcom setValue is : " + iTransformVcomValue);
        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
            return FacFunction_Adapter.getInstance(this.mContext).setVcomVoltage2(iTransformVcomValue, this.mVcomType);
        }
        return FacFunction_Adapter.getInstance(this.mContext).setVcomVoltage(iTransformVcomValue);
    }

    private int transformVcomValue(int i) {
        HisLog.i("transformVcomValue mVcomType is : " + this.mVcomType);
        HisLog.i("transformVcomValue oriValue is : " + i);
        int i2 = FacPropertyDemo.HIFAC_MAX_VCOM_SPAN;
        int i3 = FacPropertyDemo.HIFAC_MIN_VCOM_SPAN;
        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT && 2 == this.mVcomType) {
            i2 = FacPropertyDemo.HIFAC_MAX_VCOM_DOUBLE_CELL_FHD_SPAN;
            i3 = FacPropertyDemo.HIFAC_MIN_VCOM_DOUBLE_CELL_FHD_SPAN;
        }
        HisLog.i("transformVcomValue maxVcom is : " + i2);
        HisLog.i("transformVcomValue minVcom is : " + i3);
        int iRound = (int) (Math.round(((double) ((i2 - i3) * i)) / 255.0d) + ((long) i3));
        HisLog.i("transformVcomValue setValue is : " + iRound);
        if (iRound <= 0) {
            return 0;
        }
        return iRound;
    }

    public boolean saveVcom(int i) {
        if (-1 >= i || i >= 256) {
            return false;
        }
        HisLog.i("saveVcom mVcomType is : " + this.mVcomType);
        int iTransformVcomValue = transformVcomValue(i);
        HisLog.i("saveVcom setValue is : " + iTransformVcomValue);
        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
            return FacFunction_Adapter.getInstance(this.mContext).saveVcomVoltage2(iTransformVcomValue, this.mVcomType);
        }
        return FacFunction_Adapter.getInstance(this.mContext).saveVcomVoltage(iTransformVcomValue);
    }

    public boolean setBalance(int i) {
        HisLog.i("setBalance data is : " + i);
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            if (i == TvsBalanceMode.LEFT.getValue()) {
                FacFunction_Adapter.getInstance(this.mContext).setBalanceForTweeterWoofer(1);
            } else if (i == TvsBalanceMode.RIGHT.getValue()) {
                FacFunction_Adapter.getInstance(this.mContext).setBalanceForTweeterWoofer(2);
            } else if (i == TvsBalanceMode.WOOFER.getValue()) {
                FacFunction_Adapter.getInstance(this.mContext).setBalanceForTweeterWoofer(3);
            } else if (i == TvsBalanceMode.TTL.getValue()) {
                FacFunction_Adapter.getInstance(this.mContext).setBalanceForTweeterWoofer(4);
            } else if (i == TvsBalanceMode.TTR.getValue()) {
                FacFunction_Adapter.getInstance(this.mContext).setBalanceForTweeterWoofer(5);
            } else {
                FacFunction_Adapter.getInstance(this.mContext).setBalanceForTweeterWoofer(0);
            }
        } else if (i == BalanceMode.ML.getValue()) {
            FacFunction_Adapter.getInstance(this.mContext).setMultiBalance(1);
        } else if (i == BalanceMode.MR.getValue()) {
            FacFunction_Adapter.getInstance(this.mContext).setMultiBalance(2);
        } else {
            FacFunction_Adapter.getInstance(this.mContext).setMultiBalance(0);
        }
        this.keySwitcher.shortPressKey(112233);
        return true;
    }

    public boolean setLNB(int i) {
        if (i == 0) {
            FacFunction_Adapter.getInstance(this.mContext).setLNB(1);
            return true;
        }
        if (i != 4) {
            return false;
        }
        FacFunction_Adapter.getInstance(this.mContext).setLNB(0);
        return true;
    }

    public byte[] getVcom() {
        int vcomVoltage;
        HisLog.i("getVcom mVcomType is : " + this.mVcomType);
        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
            vcomVoltage = FacFunction_Adapter.getInstance(this.mContext).getVcomVoltage2(this.mVcomType);
        } else {
            vcomVoltage = FacFunction_Adapter.getInstance(this.mContext).getVcomVoltage();
        }
        int i = FacPropertyDemo.HIFAC_MAX_VCOM_SPAN;
        int i2 = FacPropertyDemo.HIFAC_MIN_VCOM_SPAN;
        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT && 2 == this.mVcomType) {
            i = FacPropertyDemo.HIFAC_MAX_VCOM_DOUBLE_CELL_FHD_SPAN;
            i2 = FacPropertyDemo.HIFAC_MIN_VCOM_DOUBLE_CELL_FHD_SPAN;
        }
        HisLog.i("transformVcomValue maxVcom is : " + i);
        HisLog.i("transformVcomValue minVcom is : " + i2);
        int iRound = (int) Math.round((((double) ((vcomVoltage - i2) * 255)) * 1.0d) / ((double) (i - i2)));
        HisLog.i("getVcom finalValue is : " + iRound);
        if (iRound < 0) {
            iRound = 0;
        }
        return HexData.hexStringToBytes(HexData.integerToHexString(iRound));
    }

    public String getVersions(int i) {
        Logger.d("get the " + i + " version");
        if (i == 0) {
            return mMenuConfigManager.getMcuVersionInfo();
        }
        if (i == 1) {
            return mMenuConfigManager.getFRCVersion();
        }
        if (i == 2) {
            return mMenuConfigManager.getDlpMcuVersion();
        }
        if (i == 3) {
            return mMenuConfigManager.getSoundMcuVersion();
        }
        if (i == 4) {
            return mMenuConfigManager.getFPGAVersion();
        }
        Logger.e("getVersions is null");
        return "";
    }

    public int getLightSensor(int i) {
        int i2;
        int i3;
        Logger.d("get the " + i + " light sensor");
        int[] lightSensorValues = mMenuConfigManager.getLightSensorValues();
        if (i == 0) {
            i2 = lightSensorValues[2] * 256;
            i3 = lightSensorValues[3];
        } else if (i == 1) {
            i2 = lightSensorValues[4] * 256;
            i3 = lightSensorValues[5];
        } else if (i == 2) {
            i2 = lightSensorValues[6] * 256;
            i3 = lightSensorValues[7];
        } else if (i == 3) {
            i2 = lightSensorValues[8] * 256;
            i3 = lightSensorValues[9];
        } else {
            Logger.e("getLightSensor is null");
            return 0;
        }
        return i2 + i3;
    }

    public int getTemps(int i) {
        Logger.d("get the " + i + " temperature");
        int[] boardTemperature = mMenuConfigManager.getBoardTemperature();
        if (i == 0) {
            return boardTemperature[0];
        }
        if (i == 1) {
            return boardTemperature[1];
        }
        if (i == 2) {
            return boardTemperature[2];
        }
        Logger.e("getTemps is null");
        return 0;
    }

    public int getFansSpeed(int i) {
        int i2;
        Logger.d("get the " + i + " fan speed");
        int[] fansSpeed = mMenuConfigManager.getFansSpeed();
        switch (i) {
            case 0:
                return 0;
            case 1:
                i2 = fansSpeed[0];
                break;
            case 2:
                i2 = fansSpeed[1];
                break;
            case 3:
                i2 = fansSpeed[2];
                break;
            case 4:
                i2 = fansSpeed[3];
                break;
            case 5:
                i2 = fansSpeed[4];
                break;
            case 6:
                i2 = fansSpeed[5];
                break;
            default:
                Logger.e("getFansSpeed is null");
                return 0;
        }
        return i2 * 30;
    }

    public String getRunningTime(int i) {
        Logger.d("getRunningTime value = " + i);
        if (i == 0) {
            return FacFunction_Adapter.getInstance(this.mContext).getTVRunningTimeSerial();
        }
        if (i == 1) {
            return FacFunction_Adapter.getInstance(this.mContext).getLaserRuningTime();
        }
        Logger.e("running time is null");
        return "";
    }

    public boolean setWBPattern(int i, boolean z) {
        boolean zShortPressKey;
        if (i == 0) {
            SystemProperties.set("sys.vt.factory.wb_pattern_test", "false");
            this.keySwitcher.shortPressKey(4232);
            if (isShowVcom) {
                isShowVcom = false;
                this.keySwitcher.shortPressKey(4234);
            }
        } else if (i > 1 && i != 16) {
            this.keySwitcher.shortPressKey(4233);
        }
        if (i != 16) {
            if (i == 1) {
                FacFunction_Adapter.getInstance(this.mContext).setLogToFile("WhiteBalance picture is on");
                return true;
            }
            if (i != 0) {
                this.mContext.sendBroadcast(new Intent("com.hisense.broadcom.HIDE_FAC_MAINMENU"));
                Logger.i("send hide fac menu broadcast");
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return FacFunction_Adapter.getInstance(this.mContext).setSerialWBPattern(i);
        }
        isShowVcom = true;
        Logger.d("show vcom picture*****");
        this.keySwitcher.shortPressKey(4357);
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        if (FacPropertyDemo.HIFAC_DOUBLE_CELL_SUPPORT) {
            if (z) {
                this.mVcomType = 1;
                zShortPressKey = this.keySwitcher.shortPressKey(112235);
            } else {
                this.mVcomType = 2;
                zShortPressKey = this.keySwitcher.shortPressKey(112236);
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
            return zShortPressKey;
        }
        boolean zShortPressKey2 = this.keySwitcher.shortPressKey(4235);
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e4) {
            e4.printStackTrace();
        }
        return zShortPressKey2;
    }

    public boolean setModulePattern(int i) {
        Intent intent = new Intent(this.mContext, (Class<?>) ModulePatternActivity.class);
        intent.addFlags(268435456);
        Bundle bundle = new Bundle();
        bundle.putInt("index", i);
        intent.putExtras(bundle);
        this.mContext.startActivity(intent);
        return true;
    }

    public byte[] getChinaDrmKey() {
        Logger.d("getChinaDrmKey");
        try {
            String chinaDrmKey = FacFunction_Adapter.getInstance(this.mContext).getChinaDrmKey();
            Logger.e("getChinaDrmKey" + chinaDrmKey);
            return HexData.hexStringToBytes(HexData.stringToHexString(chinaDrmKey));
        } catch (Exception e) {
            Logger.e("getChinaDrmKey" + e.getMessage());
            return null;
        }
    }

    public boolean writeWidevineKey() {
        String string;
        String string2;
        mMenuConfigManager.setLogToTvcertificate("serial WidevineKey");
        File file = new File(mSerialKeyPath);
        if (file.isDirectory() && file.exists()) {
            final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX);
            File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.serialInfoTest.model.SerialCommands.4
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    if (!file2.isFile()) {
                        return false;
                    }
                    String name = file2.getName();
                    if (z) {
                        return SerialCommands.this.isAntiFoolKeyNameValid(name);
                    }
                    return !TextUtils.isEmpty(name);
                }
            });
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                Logger.i("idFiles length is " + fileArrListFiles.length);
                String string3 = fileArrListFiles[0].toString();
                Logger.i("widevine key path is " + string3);
                boolean wideVineKey = FacFunction_Adapter.getInstance(this.mContext).setWideVineKey(string3);
                FacFunction_Adapter.getInstance(this.mContext).removefactorykeyfile(string3);
                if (wideVineKey) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!mMenuConfigManager.setPlayReadyFromSerial()) {
                        string = this.mContext.getResources().getString(R.string.str_playready_key_fail);
                    } else {
                        string = this.mContext.getResources().getString(R.string.str_playready_key_yes);
                    }
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    if (mMenuConfigManager.setAttestationForSerial()) {
                        string2 = this.mContext.getResources().getString(R.string.str_attest_yes);
                    } else {
                        string2 = this.mContext.getResources().getString(R.string.str_attest_key_fail);
                    }
                    mMenuConfigManager.showToastOnRootMenu(string2 + "\n" + string);
                    try {
                        Thread.sleep(300L);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                    this.mContext.sendBroadcast(checkKeyStatusIntent);
                    Logger.i("send check key status broadcast");
                }
                return wideVineKey;
            }
            Logger.e("WIDEVINE KEY FILE NOT EXIST!!!!!!!!!!!");
        } else {
            Logger.e("tvdatabase widevine folder NOT EXIST!!!!!!!!!!!");
        }
        return false;
    }

    public boolean writeHdcp14Key() {
        mMenuConfigManager.setLogToTvcertificate("serial Hdcp14Key");
        File file = new File(mSerialKeyPath);
        if (file.isDirectory() && file.exists()) {
            final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_HDCP14_KEY_FILENAME_PREFIX);
            File[] fileArrListFiles = file.listFiles(new FileFilter(this) { // from class: com.hisense.serialInfoTest.model.SerialCommands.5
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    if (!file2.isFile()) {
                        return false;
                    }
                    String name = file2.getName();
                    if (z) {
                        return SerialCommands.mMenuConfigManager.isAntiFoolHDCPKeyNameValid(true, name);
                    }
                    return !TextUtils.isEmpty(name) && name.toLowerCase().endsWith("bin");
                }
            });
            if (fileArrListFiles != null) {
                if (fileArrListFiles.length > 0) {
                    String string = fileArrListFiles[0].toString();
                    Logger.i("hdcp1.4 key path is " + string);
                    boolean hdcp14Key = FacFunction_Adapter.getInstance(this.mContext).setHdcp14Key(string);
                    FacFunction_Adapter.getInstance(this.mContext).removefactorykeyfile(string);
                    if (hdcp14Key) {
                        this.mContext.sendBroadcast(checkKeyStatusIntent);
                        Logger.i("send check key status broadcast");
                    }
                    return hdcp14Key;
                }
                Logger.e("HDCP1.4 KEY FILE NOT EXIST!!!!!!!!!!!");
            }
        } else {
            Logger.e("tvdatabase hdcp1.4 folder NOT EXIST!!!!!!!!!!!");
        }
        return false;
    }

    public boolean writeHdcp22Key() {
        mMenuConfigManager.setLogToTvcertificate("serial Hdcp22Key");
        File file = new File(mSerialKeyPath);
        if (file.isDirectory() && file.exists()) {
            final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_HDCP2X_KEY_FILENAME_PREFIX);
            File[] fileArrListFiles = file.listFiles(new FileFilter(this) { // from class: com.hisense.serialInfoTest.model.SerialCommands.6
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    if (file2.isFile()) {
                        String name = file2.getName();
                        if (z) {
                            return SerialCommands.mMenuConfigManager.isAntiFoolHDCPKeyNameValid(false, name);
                        }
                        if (!TextUtils.isEmpty(name) && name.endsWith("enc")) {
                            return true;
                        }
                    }
                    return false;
                }
            });
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                String string = fileArrListFiles[0].toString();
                Logger.i("hdcp2.2 key path is " + string);
                boolean hdcp2xKey = FacFunction_Adapter.getInstance(this.mContext).setHdcp2xKey(string);
                FacFunction_Adapter.getInstance(this.mContext).removefactorykeyfile(string);
                if (hdcp2xKey) {
                    this.mContext.sendBroadcast(checkKeyStatusIntent);
                    Logger.i("send check key status broadcast");
                }
                return hdcp2xKey;
            }
            Logger.e("HDCP2.2 KEY FILE NOT EXIST!!!!!!!!!!!");
        } else {
            Logger.e("tvdatabase hdcp2.2 folder NOT EXIST!!!!!!!!!!!");
        }
        return false;
    }

    public boolean writePlayReadyKey() {
        File[] fileArrListFiles;
        File file = new File(mSerialKeyPath);
        if (file.isDirectory() && file.exists() && (fileArrListFiles = file.listFiles(new FileFilter(this) { // from class: com.hisense.serialInfoTest.model.SerialCommands.7
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isDirectory() && file2.getName().startsWith(FacPropertyDemo.HIFAC_PLAYREADY_KEY_FILENAME_PREFIX);
            }
        })) != null && fileArrListFiles.length > 0) {
            File file2 = fileArrListFiles[0];
            Logger.d("writePlayReadyKey rootPathFile is " + file2);
            if (file2.isDirectory() && file2.exists()) {
                Logger.d("writePlayReadyKey rootPathFile exists ");
                File[] fileArrListFiles2 = file2.listFiles(new FileFilter(this) { // from class: com.hisense.serialInfoTest.model.SerialCommands.8
                    @Override // java.io.FileFilter
                    public boolean accept(File file3) {
                        if (!file3.isFile()) {
                            return false;
                        }
                        String name = file3.getName();
                        return !TextUtils.isEmpty(name) && name.endsWith("dat");
                    }
                });
                Logger.i("writePlayReadyKey idFiles length is " + fileArrListFiles2.length);
                if (fileArrListFiles2.length == 4) {
                    boolean playReadyPrivKey = false;
                    for (int i = 0; i < 4; i++) {
                        String string = fileArrListFiles2[i].toString();
                        if (string.contains("zgpriv.dat")) {
                            playReadyPrivKey = FacFunction_Adapter.getInstance(this.mContext).setPlayReadyZgPrivKey(string);
                            Logger.i("set zgpriv.dat is " + playReadyPrivKey);
                        }
                    }
                    for (int i2 = 0; i2 < 4; i2++) {
                        String string2 = fileArrListFiles2[i2].toString();
                        if (string2.contains("priv.dat") && !string2.contains("zgpriv.dat")) {
                            playReadyPrivKey &= FacFunction_Adapter.getInstance(this.mContext).setPlayReadyPrivKey(string2);
                            Logger.i("set priv.dat is " + playReadyPrivKey);
                        }
                    }
                    int i3 = 0;
                    while (true) {
                        if (i3 >= 4) {
                            break;
                        }
                        String string3 = fileArrListFiles2[i3].toString();
                        if (string3.contains("devcerttemplate.dat")) {
                            boolean z = playReadyPrivKey && FacFunction_Adapter.getInstance(this.mContext).setPlayReadyDevCertKey(string3);
                            Logger.i("set devcerttemplate.dat is " + z);
                            playReadyPrivKey = z;
                        }
                        i3++;
                    }
                    for (int i4 = 0; i4 < 4; i4++) {
                        String string4 = fileArrListFiles2[i4].toString();
                        if (string4.contains("bgroupcert.dat")) {
                            playReadyPrivKey = playReadyPrivKey && FacFunction_Adapter.getInstance(this.mContext).setPlayReadyBgcertKey(string4);
                            Logger.i("set bgroupcert.dat is " + playReadyPrivKey);
                        }
                    }
                    if (playReadyPrivKey) {
                        Logger.i("set playReady key success!!");
                        this.mContext.sendBroadcast(checkKeyStatusIntent);
                        Logger.i("send check key status broadcast");
                        return true;
                    }
                }
            } else {
                Logger.i("writePlayReadyKey rootPathFile DOES NOT exist!!!");
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAntiFoolKeyNameValid(String str) {
        Logger.d("isAntiFoolKeyNameValid, file name: " + str);
        if (TextUtils.isEmpty(str)) {
            Logger.e("wide vine file name invalid: file name is null");
        } else {
            int iIndexOf = str.indexOf(".bin");
            if (iIndexOf > 0) {
                str = str.substring(0, iIndexOf);
            }
            if (!str.startsWith(FacPropertyDemo.HIFAC_WIDEVINE_KEY_FILENAME_PREFIX)) {
                Logger.e("wide vine file name invalid: prefix not match");
            } else {
                if (str.length() == 30) {
                    return true;
                }
                Logger.e("wide vine file name invalid: length not match");
            }
        }
        return false;
    }

    public boolean excutePowerOff() {
        boolean zShortPressKey = this.keySwitcher.shortPressKey(26);
        Logger.i("send key code POWER_OFF");
        return zShortPressKey;
    }

    public boolean setGamma(byte[] bArr) {
        int[] iArr = {1, HexData.decodeToInteger(bArr[1]), HexData.decodeToInteger(bArr[3]), HexData.decodeToInteger(bArr[5])};
        this.mContext.sendBroadcast(new Intent("com.hisense.broadcom.HIDE_FAC_MAINMENU"));
        Logger.i("send hide fac menu broadcast");
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.keySwitcher.shortPressKey(4233);
        return FacFunction_Adapter.getInstance(this.mContext).setGamma(iArr);
    }

    public boolean setForce120HzEnable(int i) {
        Logger.i("setForce120HzEnable，value:" + i);
        return FacFunction_Adapter.getInstance(this.mContext).setForce120HzEnable(i);
    }

    public boolean set4096Gamma(byte[] bArr) {
        if (bArr == null || bArr.length < 6) {
            Logger.i("set4096Gamma data error!!t");
            return false;
        }
        int iDecodeToInteger = (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]);
        Logger.i("set4096Gamma dataR = " + iDecodeToInteger);
        int iDecodeToInteger2 = (HexData.decodeToInteger(bArr[2]) * 256) + HexData.decodeToInteger(bArr[3]);
        Logger.i("set4096Gamma dataG = " + iDecodeToInteger2);
        int iDecodeToInteger3 = (HexData.decodeToInteger(bArr[4]) * 256) + HexData.decodeToInteger(bArr[5]);
        Logger.i("set4096Gamma dataB = " + iDecodeToInteger3);
        int[] iArr = {1, iDecodeToInteger, iDecodeToInteger2, iDecodeToInteger3};
        this.mContext.sendBroadcast(new Intent("com.hisense.broadcom.HIDE_FAC_MAINMENU"));
        Logger.i("send hide fac menu broadcast");
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.keySwitcher.shortPressKey(4233);
        FacFunction_Adapter.getInstance(this.mContext).setLocalDimmingSwitch(false);
        return FacFunction_Adapter.getInstance(this.mContext).set4096Gamma(iArr);
    }

    public boolean closeGamma() {
        Logger.i("serial close gamma");
        this.keySwitcher.shortPressKey(4232);
        return FacFunction_Adapter.getInstance(this.mContext).closeGamma(new int[]{0, 0, 0, 0});
    }

    public boolean close4096Gamma() {
        Logger.i("serial close gamma");
        this.keySwitcher.shortPressKey(4232);
        return FacFunction_Adapter.getInstance(this.mContext).closeGamma(new int[]{0, 0, 0, 0});
    }

    public boolean setGammaEnable(int i, int i2) {
        Logger.i("serial setGammaEnable");
        return FacFunction_Adapter.getInstance(this.mContext).setGammaEnableForSerial(i, i2);
    }

    public boolean setLocalDimmingSwitch(int i) {
        return FacFunction_Adapter.getInstance(this.mContext).setLocalDimmingSwitch(i == 1) == 0;
    }

    public boolean setWBAdjustResult(int i) {
        Logger.i("call setWBAdjustResult!!!!" + i);
        FacFunction_Adapter.getInstance(this.mContext).updateFacLogStatus(MtkTvScanDvbsBase.SB_DVBS_CONFIG_BGM_SCAN);
        FacFunction_Adapter.getInstance(this.mContext).setLogToFile("WhiteBalance adjust result = " + i);
        SharedPreferences sharedPreferences = this.adapterContext.getSharedPreferences("FacShareP", 4);
        this.mShared = sharedPreferences;
        sharedPreferences.edit().putInt("WBADJUSTRESULT", i).commit();
        this.keySwitcher.shortPressKey(4232);
        return true;
    }

    public boolean setGammaAdjustResult(int i) {
        Logger.i("call setGammaAdjustResult!!!!" + i);
        FacFunction_Adapter.getInstance(this.mContext).updateFacLogStatus(32768);
        SharedPreferences sharedPreferences = this.adapterContext.getSharedPreferences("FacShareP", 4);
        this.mShared = sharedPreferences;
        sharedPreferences.edit().putInt("GAMMAADJUSTRESULT", i).commit();
        FacFunction_Adapter.getInstance(this.mContext).endAutoGammaTunning();
        if (i == 0) {
            FacFunction_Adapter.getInstance(this.mContext).resetWhiteBalance();
        }
        this.keySwitcher.shortPressKey(4232);
        return true;
    }

    public byte[] getColorTemList() {
        int[] colorTemList = FacFunction_Adapter.getInstance(this.mContext).getColorTemList();
        String str = "";
        if (colorTemList != null) {
            for (int i = 0; i < colorTemList.length; i++) {
                str = i == 0 ? str + colorTemList[i] : str + MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING + colorTemList[i];
            }
        }
        Logger.d("getColorTemList: " + str);
        return HexData.hexStringToBytes(HexData.stringToHexString(str));
    }

    public String getGammaPara() {
        String gammaParam = FacFunction_Adapter.getInstance(this.mContext).getGammaParam();
        return gammaParam == null ? "" : gammaParam;
    }

    private int getUsbCount() {
        int i = 0;
        for (UsbDevice usbDevice : ((UsbManager) this.mContext.getSystemService("usb")).getDeviceList().values()) {
            if (usbDevice.getDeviceClass() == 0 && usbDevice.getInterface(0).getInterfaceClass() == 8) {
                i++;
            }
        }
        return i;
    }

    private String getUsbFileName() throws Throwable {
        File[] fileArrListFiles;
        List<String> externalStoragePath = ExternalStorageHelper.getExternalStoragePath(this.mContext);
        if (externalStoragePath == null || externalStoragePath.isEmpty()) {
            return null;
        }
        Iterator<String> it = externalStoragePath.iterator();
        while (it.hasNext()) {
            File file = new File(it.next());
            if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                for (File file2 : fileArrListFiles) {
                    if (!file2.getName().isEmpty() && file2.isFile()) {
                        return file2.getName();
                    }
                }
            }
        }
        return null;
    }

    public byte[] getHdcp2xKey() {
        Logger.d("getHdcp2xKey");
        try {
            String hdcp2xKey = mMenuConfigManager.getHdcp2xKey();
            Logger.e("getHdcp2xKey key = " + hdcp2xKey);
            return HexData.hexStringToBytes(HexData.stringToHexString(hdcp2xKey));
        } catch (Exception e) {
            Logger.e("getHdcp2xKey" + e.getMessage());
            return null;
        }
    }

    public byte[] getHdcp1xKey() {
        Logger.d("getHdcp1xKey");
        try {
            String hdcp14Key = mMenuConfigManager.getHdcp14Key();
            Logger.e("getHdcp1xKey key = " + hdcp14Key);
            return HexData.hexStringToBytes(HexData.stringToHexString(hdcp14Key));
        } catch (Exception e) {
            Logger.e("getHdcp1xKey" + e.getMessage());
            return null;
        }
    }

    public byte[] getPlayReadyKey() {
        Logger.d("getPlayReadyKey");
        try {
            String playReadyKey = mMenuConfigManager.getPlayReadyKey();
            Logger.e("getPlayReadyKey key = " + playReadyKey);
            return HexData.hexStringToBytes(HexData.stringToHexString(playReadyKey));
        } catch (Exception e) {
            Logger.e("getPlayReadyKey" + e.getMessage());
            return null;
        }
    }

    public boolean setProjectId(int i) {
        String string = Integer.toString(i);
        mMenuConfigManager.setLogToTvcertificate("setProjectId" + string);
        return mMenuConfigManager.setProjectId(string) == 0;
    }

    public boolean setMGKKeyFromSerial() {
        mMenuConfigManager.setLogToTvcertificate("serial MGK");
        File file = new File(mSerialKeyPath);
        if (file.isDirectory() && file.exists()) {
            final boolean z = !TextUtils.isEmpty(mMenuConfigManager.getMGKName());
            File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.hisense.serialInfoTest.model.SerialCommands.9
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    if (!file2.isFile()) {
                        return false;
                    }
                    String name = file2.getName();
                    if (z) {
                        return SerialCommands.this.isAntiFoolMGKKeyNameValid(name);
                    }
                    return !TextUtils.isEmpty(name) && name.endsWith("enc");
                }
            });
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                String string = fileArrListFiles[0].toString();
                Logger.e("the  mgk file name is     " + string);
                boolean mGKKeyForSerial = mMenuConfigManager.setMGKKeyForSerial(string);
                FacFunction_Adapter.getInstance(this.mContext).removefactorykeyfile(string);
                return mGKKeyForSerial;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAntiFoolMGKKeyNameValid(String str) {
        Logger.e("isAntiFoolMGKKeyNameValid, file name: " + str);
        if (TextUtils.isEmpty(str)) {
            Logger.e("mgk file name invalid: file name is null");
            return false;
        }
        String mGKName = mMenuConfigManager.getMGKName();
        if (!str.startsWith(mGKName)) {
            Logger.e("mgk file name invalid: prefix not match");
            return false;
        }
        if (str.length() != 30) {
            Logger.e("mgk file name invalid: length not match");
            return false;
        }
        try {
            long j = Long.parseLong(str.replaceFirst(mGKName, ""));
            if (j >= 1) {
                return true;
            }
            Logger.e("mgk file name invalid: index error, " + j);
            return false;
        } catch (Exception unused) {
            Logger.e("mgk file name invalid");
            return false;
        }
    }

    public byte[] getProjectId() {
        Logger.d("getProjectId");
        byte[] bArr = {0};
        try {
            bArr[0] = FacFunction_Adapter.getInstance(this.mContext).getProjectIdForSerial();
            Logger.e("getProjectId" + (bArr[0] & 255));
        } catch (Exception e) {
            Logger.e("getProjectId" + e.getMessage());
        }
        return bArr;
    }

    public int getLightSensorValue(int i) {
        int lightSensorValue;
        if (FacPropertyDemo.HIFAC_MARKET_JP) {
            Logger.d("getLightSensorValue mode = " + i);
            if (i < 1 || i > 3) {
                i = 1;
            }
            lightSensorValue = FacFunction_Adapter.getInstance(this.mContext).getTvsLightSensorForSerial(i);
        } else {
            lightSensorValue = FacFunction_Adapter.getInstance(this.mContext).getLightSensorValue();
        }
        Logger.d("getLightSensorValue " + lightSensorValue);
        return lightSensorValue;
    }

    public byte[] getNetflixKey() {
        Logger.d("getNetflixKey");
        try {
            String netflixKey = mMenuConfigManager.getNetflixKey();
            Logger.e("getNetflixKey key = " + netflixKey);
            return HexData.hexStringToBytes(HexData.stringToHexString(netflixKey));
        } catch (Exception e) {
            Logger.e("getNetflixKey" + e.getMessage());
            return null;
        }
    }

    public byte[] getDtcpIpKey() {
        Logger.d("getDtcpIpKey");
        try {
            String dtcpIpKey = mMenuConfigManager.getDtcpIpKey();
            Logger.e("getDtcpIpKey key = " + dtcpIpKey);
            return HexData.hexStringToBytes(HexData.stringToHexString(dtcpIpKey));
        } catch (Exception e) {
            Logger.e("getDtcpIpKey" + e.getMessage());
            return null;
        }
    }

    public byte[] getMarlinKey() {
        Logger.d("getMarlinKey");
        try {
            String marlinKey = mMenuConfigManager.getMarlinKey();
            Logger.e("getMarlinKey key = " + marlinKey);
            return HexData.hexStringToBytes(HexData.stringToHexString(marlinKey));
        } catch (Exception e) {
            Logger.e("getMarlinKey" + e.getMessage());
            return null;
        }
    }

    public byte[] getSeeQVaultKey() {
        Logger.d("getSeeQVaultKey");
        try {
            String seeQVaultKey = mMenuConfigManager.getSeeQVaultKey();
            Logger.e("getSeeQVaultKey key = " + seeQVaultKey);
            return HexData.hexStringToBytes(HexData.stringToHexString(seeQVaultKey));
        } catch (Exception e) {
            Logger.e("getSeeQVaultKey" + e.getMessage());
            return null;
        }
    }

    public byte[] getPVRKey() {
        Logger.d("getPVRKey");
        try {
            String pVRKey = mMenuConfigManager.getPVRKey();
            Logger.e("getPVRKey key = " + pVRKey);
            return HexData.hexStringToBytes(HexData.stringToHexString(pVRKey));
        } catch (Exception e) {
            Logger.e("getPVRKey" + e.getMessage());
            return null;
        }
    }

    public byte[] getWifiMacAddress() {
        String wifiMacAddress = mMenuConfigManager.getWifiMacAddress();
        Logger.d("getWifiMacAddress: " + wifiMacAddress);
        String[] strArrSplit = wifiMacAddress.split("\\:");
        HexData.hexStringToBytes("000000000000");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArrSplit.length; i++) {
            Logger.d("macData[" + i + "]: " + strArrSplit[i]);
            sb.append(strArrSplit[i]);
        }
        return HexData.hexStringToBytes(sb.toString());
    }

    public byte[] getOrderCode() {
        String productType = FacFunction_Adapter.getInstance(this.mContext).getProductType();
        Logger.d("getVersionInfo: " + productType);
        String versionInfo = mMenuConfigManager.getVersionInfo();
        Logger.d("getVersionInfo: " + versionInfo);
        String[] strArrSplit = versionInfo.split("\\.");
        if (strArrSplit == null && strArrSplit.length <= 5) {
            return null;
        }
        for (int i = 0; i < strArrSplit.length; i++) {
            Logger.d("infoSplit[" + i + "]: " + strArrSplit[i]);
        }
        StringBuilder sb = new StringBuilder();
        if (!"V0000".equals(strArrSplit[1])) {
            String strSubstring = strArrSplit[1].substring(strArrSplit[1].length() - 4, strArrSplit[1].length());
            sb.append(productType);
            sb.append("(");
            sb.append(strSubstring);
            sb.append(")");
        } else {
            sb.append(productType);
        }
        Logger.d("getOrderCode: " + sb.toString());
        return HexData.hexStringToBytes(HexData.stringToHexString(sb.toString()));
    }

    public boolean resetWhiteBalance() {
        int iResetWhiteBalance = FacFunction_Adapter.getInstance(this.mContext).resetWhiteBalance();
        Logger.i("reset White balance result = " + iResetWhiteBalance);
        return iResetWhiteBalance == 0;
    }

    public boolean setRemoteStatus(int i) {
        boolean remoteStatus = FacFunction_Adapter.getInstance(this.mContext).setRemoteStatus(i);
        Logger.i("setRemoteStatus result = " + remoteStatus);
        return remoteStatus;
    }

    public boolean setDemuraCard(boolean z, int i) {
        Logger.i("setDemuraCard isIRE = " + z);
        Logger.i("setDemuraCard data = " + i);
        DemuraUtil.setDemuraPropStatus("true");
        if (z) {
            this.keySwitcher.shortPressKey(4233);
            try {
                Thread.sleep(150L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 1) {
                this.mDemuraCardType = 0;
                this.keySwitcher.shortPressKey(5110);
            }
            return true;
        }
        this.mDemuraCardType = 1;
        byte b = (byte) (i & 255);
        Logger.i("setDemuraCard gammaData = " + ((int) b));
        return setGamma(new byte[]{b, b, b, b, b, b});
    }

    public boolean closeDemuraCard() {
        if (this.mDemuraCardType == 0) {
            DemuraUtil.setDemuraPropStatus("false");
            this.keySwitcher.shortPressKey(5112);
        } else {
            closeGamma();
        }
        this.mDemuraCardType = 0;
        return true;
    }

    public boolean setDemuraSwitchStatus(int i) {
        boolean demuraSwitchStauts = FacFunction_Adapter.getInstance(this.mContext).setDemuraSwitchStauts(i);
        Logger.i("setDemuraStatus result = " + demuraSwitchStauts);
        return demuraSwitchStauts;
    }

    public boolean setDemuraDebugStatus(int i) {
        boolean demuraToolsDriverParam;
        Logger.i("setDemuraDebugStatus demuraStatus = " + i);
        if (1 == i) {
            demuraToolsDriverParam = this.mFunctionAdapter.setDemuraToolsDriverParam(i);
            DemuraUtil.setDemuraPropStatus("true");
        } else {
            demuraToolsDriverParam = this.mFunctionAdapter.setDemuraToolsDriverParam(i);
            DemuraUtil.setDemuraPropStatus("false");
        }
        this.keySwitcher.shortPressKey(4357);
        Logger.i("setDemuraDebugStatus result = " + demuraToolsDriverParam);
        return demuraToolsDriverParam;
    }

    public boolean setMicDb(int i) {
        Logger.i("setMicDb db = " + i);
        this.mHisenseFacMicTest.setMicDb(i);
        return true;
    }

    public int getMicDb() {
        int micDb = (int) this.mHisenseFacMicTest.getMicDb();
        Logger.i("setMicDb db = " + micDb);
        return micDb;
    }

    public int getMicTest() {
        Logger.i("getMicTest");
        return 1 != FacFunction_Adapter.getInstance(this.mContext).getMicTestResult() ? 0 : 1;
    }

    public boolean startMicTest(IRootMenuUiListener iRootMenuUiListener) {
        Logger.i("setMicTest");
        this.mHisenseFacMicTest.startTestMic(iRootMenuUiListener, false);
        return true;
    }

    public byte[] scanWifiBySSID(byte[] bArr) {
        int iAbs;
        int iAbs2;
        Logger.d("scanWifiBySSID");
        String string = "";
        String str = string;
        for (byte b : bArr) {
            try {
                int iDecodeToInteger = HexData.decodeToInteger(b);
                if (iDecodeToInteger == 0) {
                    if (str.isEmpty()) {
                        str = string;
                        string = "";
                    }
                } else if (iDecodeToInteger >= 48 && iDecodeToInteger <= 57) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(iDecodeToInteger - 48);
                    string = sb.toString();
                } else if (iDecodeToInteger >= 97 && iDecodeToInteger <= 122) {
                    string = string + ((char) (iDecodeToInteger - 32));
                } else if (iDecodeToInteger >= 65 && iDecodeToInteger <= 90) {
                    string = string + ((char) iDecodeToInteger);
                } else {
                    string = string + ((char) iDecodeToInteger);
                }
            } catch (Exception e) {
                Logger.e(e.getMessage());
                return null;
            }
        }
        Logger.e("scanWifiBySSID" + string);
        Logger.e("scanWifiBySSID wifiName " + str);
        Logger.e("scanWifiBySSID wifNameTwo " + string);
        if (str.isEmpty() && string.isEmpty()) {
            return null;
        }
        byte[] bArr2 = (str.isEmpty() || string.isEmpty()) ? new byte[1] : new byte[2];
        if (this.mWifiAdmin.isWifiEnabled()) {
            List<ScanResult> wifiList = this.mWifiAdmin.getWifiList();
            mWirelessList = wifiList;
            if (wifiList == null) {
                Logger.e("scanWifiBySSID  wifi is null");
                this.mWifiAdmin.startScan();
                Thread.sleep(1000L);
                mWirelessList = this.mWifiAdmin.getWifiList();
            }
            if (mWirelessList == null) {
                return null;
            }
            iAbs = 0;
            iAbs2 = 0;
            for (ScanResult scanResult : mWirelessList) {
                Logger.e("scanWifiBySSID  wifi result = " + scanResult.toString());
                if (!str.isEmpty() && iAbs == 0 && str.equalsIgnoreCase(scanResult.SSID)) {
                    iAbs = Math.abs(scanResult.level);
                    Logger.e("scanWifiBySSID ssidOne = " + iAbs);
                }
                if (!string.isEmpty() && iAbs2 == 0 && string.equalsIgnoreCase(scanResult.SSID)) {
                    iAbs2 = Math.abs(scanResult.level);
                    Logger.e("scanWifiBySSID ssidTwo = " + iAbs2);
                }
                if (iAbs2 != 0 && iAbs != 0) {
                    break;
                }
            }
        } else {
            iAbs = 0;
            iAbs2 = 0;
        }
        if (str.isEmpty()) {
            bArr2[0] = (byte) iAbs2;
        } else if (string.isEmpty()) {
            bArr2[0] = (byte) iAbs;
        } else {
            bArr2[0] = (byte) iAbs;
            bArr2[1] = (byte) iAbs2;
        }
        return bArr2;
    }

    public boolean setClientType(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            Logger.d("setClientType");
            try {
                StringBuilder sb = new StringBuilder();
                for (byte b : bArr) {
                    sb.append((char) b);
                }
                String string = sb.toString();
                Logger.e("setClientType = " + string);
                mMenuConfigManager.setLogToTvcertificate("setClientType" + string);
                return FacFunction_Adapter.getInstance(this.mContext).setClientType(string);
            } catch (Exception e) {
                Logger.e(e.getMessage());
            }
        }
        return false;
    }

    public byte[] getClientType() {
        Logger.d("getClientType");
        try {
            String clientType = FacFunction_Adapter.getInstance(this.mContext).getClientType();
            Logger.e("getClientType" + clientType);
            return HexData.hexStringToBytes(HexData.stringToHexString(clientType));
        } catch (Exception e) {
            Logger.e("getClientType" + e.getMessage());
            return null;
        }
    }

    public int scanBlutoothByName(byte[] bArr) {
        if (this.mBluetoothAdapter.isDiscovering()) {
            Logger.i("is discovering, cancel then start discovery");
            this.mBluetoothAdapter.cancelDiscovery();
        }
        this.mBluetoothAdapter.startDiscovery();
        Logger.d("HisFac scanBlutoothByName");
        String string = "";
        for (byte b : bArr) {
            try {
                int iDecodeToInteger = HexData.decodeToInteger(b);
                if (iDecodeToInteger >= 48 && iDecodeToInteger <= 57) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    sb.append(iDecodeToInteger - 48);
                    string = sb.toString();
                } else if (iDecodeToInteger >= 97 && iDecodeToInteger <= 122) {
                    string = string + ((char) (iDecodeToInteger - 32));
                } else if (iDecodeToInteger >= 65 && iDecodeToInteger <= 90) {
                    string = string + ((char) iDecodeToInteger);
                } else {
                    string = string + ((char) iDecodeToInteger);
                }
            } catch (Exception e) {
                Logger.e(e.getMessage());
                return 0;
            }
        }
        Logger.e("HisFac scanBlutoothByName str " + string);
        Intent intent = new Intent();
        intent.setAction("com.hisense.fac.HISENSE_FAC_SERIAL_START_ACTION");
        intent.putExtra("fac_bluetooth_name_serial", string);
        this.mContext.sendBroadcast(intent);
        Logger.e("HisFac scanBlutoothByName end str " + string);
        return 0;
    }

    public boolean changeChannels4byte(byte[] bArr) {
        byte[] bArr2 = new byte[2];
        Logger.d("data =" + bArr + " ; data.length:" + bArr.length);
        Logger.d("changeChannels data[0]" + ((int) bArr[0]) + " data[1]" + ((int) bArr[1]) + " data[2]" + ((int) bArr[2]) + " data[3]" + ((int) bArr[3]));
        if (bArr.length != 4) {
            Logger.e("switchChannels number error(data.length != 4).");
            return false;
        }
        System.arraycopy(bArr, 2, bArr2, 0, 2);
        if (FacPropertyDemo.HIFAC_ATSC30_SUPPORT) {
            FacFunction_Adapter.getInstance(this.mContext).getChannelListByTunerMode(0);
            FacFunction_Adapter.getInstance(this.mContext).getChannelListByTunerMode(1);
        } else {
            FacFunction_Adapter.getInstance(this.mContext).getAirChannelList();
            FacFunction_Adapter.getInstance(this.mContext).getCableChannelList();
        }
        int iByte2ToUnsignedShort = HexData.byte2ToUnsignedShort(bArr2);
        Logger.d("changeChannels channelNum is: " + iByte2ToUnsignedShort);
        FacFunction_Adapter.getInstance(this.mContext).changeChannel(iByte2ToUnsignedShort);
        this.keySwitcher.shortPressKey(112234);
        Logger.d("finish changing channels.");
        return true;
    }

    public boolean setGammaStartFlag() {
        FacFunction_Adapter.getInstance(this.mContext).startAutoGammaTunning();
        return true;
    }

    public byte[] getFRCVerSion() {
        String fRCVersion = mMenuConfigManager.getFRCVersion();
        Logger.i("softWareVersion = " + fRCVersion);
        return HexData.hexStringToBytes(HexData.stringToHexString(fRCVersion));
    }

    public byte[] getTconVersion() {
        String tconVersion = mMenuConfigManager.getTconVersion();
        Logger.i("softWareVersion = " + tconVersion);
        return HexData.hexStringToBytes(HexData.stringToHexString(tconVersion));
    }

    public byte[] getBluVersion() {
        String backlightDriverVersion = mMenuConfigManager.getBacklightDriverVersion();
        Logger.i("softWareVersion = " + backlightDriverVersion);
        return HexData.hexStringToBytes(HexData.stringToHexString(backlightDriverVersion));
    }

    public boolean setDemuraSPIStatus(int i) {
        boolean demuraSPIStatus = this.mFunctionAdapter.setDemuraSPIStatus(i);
        Logger.i("setDemuraSPIStatus result = " + demuraSPIStatus);
        return demuraSPIStatus;
    }

    public boolean setDemuraTconStatus(int i) {
        boolean demuraTconStatus = this.mFunctionAdapter.setDemuraTconStatus(i);
        Logger.i("setDemuraTconStatus result = " + demuraTconStatus);
        return demuraTconStatus;
    }

    public boolean setDemuraDataErase() {
        boolean demuraDataErase = this.mFunctionAdapter.setDemuraDataErase();
        Logger.i("setDemuraDataErase result = " + demuraDataErase);
        return demuraDataErase;
    }

    public boolean setDemuraDataUpdate() {
        boolean demuraDataUpdate = this.mFunctionAdapter.setDemuraDataUpdate();
        Logger.i("setDemuraDataUpdate result = " + demuraDataUpdate);
        return demuraDataUpdate;
    }

    public boolean writeDTCPIPKey() {
        mMenuConfigManager.setLogToTvcertificate("serial DTCPIP");
        File file = new File(mSerialKeyPath);
        if (file.isDirectory() && file.exists()) {
            final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_DTCPIP_KEY_FILENAME_PREFIX);
            File[] fileArrListFiles = file.listFiles(new FileFilter(this) { // from class: com.hisense.serialInfoTest.model.SerialCommands.10
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    if (!file2.isFile()) {
                        return false;
                    }
                    String name = file2.getName();
                    if (z) {
                        return SerialCommands.mMenuConfigManager.isAntiFoolDTCPIPKeyNameValid(name);
                    }
                    return !TextUtils.isEmpty(name) && name.toLowerCase().endsWith("bin");
                }
            });
            if (fileArrListFiles != null) {
                if (fileArrListFiles.length > 0) {
                    String string = fileArrListFiles[0].toString();
                    Logger.i("dtcpip key path is " + string);
                    boolean dTCPIPKey = FacFunction_Adapter.getInstance(this.mContext).setDTCPIPKey(string);
                    FacFunction_Adapter.getInstance(this.mContext).removefactorykeyfile(string);
                    if (dTCPIPKey) {
                        this.mContext.sendBroadcast(checkKeyStatusIntent);
                        Logger.i("send check key status broadcast");
                    }
                    return dTCPIPKey;
                }
                Logger.e("dtcpip KEY FILE NOT EXIST!!!!!!!!!!!");
            }
        } else {
            Logger.e("tvdatabase dtcpip folder NOT EXIST!!!!!!!!!!!");
        }
        return false;
    }

    public boolean writePVRKey() {
        mMenuConfigManager.setLogToTvcertificate("serial PVR");
        File file = new File(mSerialKeyPath);
        if (file.isDirectory() && file.exists()) {
            final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_PVR_KEY_FILENAME_PREFIX);
            File[] fileArrListFiles = file.listFiles(new FileFilter(this) { // from class: com.hisense.serialInfoTest.model.SerialCommands.11
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    if (!file2.isFile()) {
                        return false;
                    }
                    String name = file2.getName();
                    if (z) {
                        return SerialCommands.mMenuConfigManager.isAntiFoolPVRKeyNameValid(name);
                    }
                    return !TextUtils.isEmpty(name) && name.toLowerCase().endsWith("bin");
                }
            });
            if (fileArrListFiles != null) {
                if (fileArrListFiles.length > 0) {
                    String string = fileArrListFiles[0].toString();
                    Logger.i("Marlin key path is " + string);
                    boolean pVRKey = FacFunction_Adapter.getInstance(this.mContext).setPVRKey(string);
                    FacFunction_Adapter.getInstance(this.mContext).removefactorykeyfile(string);
                    if (pVRKey) {
                        this.mContext.sendBroadcast(checkKeyStatusIntent);
                        Logger.i("send check key status broadcast");
                    }
                    return pVRKey;
                }
                Logger.e("PVR KEY FILE NOT EXIST!!!!!!!!!!!");
            }
        } else {
            Logger.e("tvdatabase PVR folder NOT EXIST!!!!!!!!!!!");
        }
        return false;
    }

    public boolean writeSQVCKey() {
        mMenuConfigManager.setLogToTvcertificate("serial SeeQVault");
        File file = new File(mSerialKeyPath);
        if (file.isDirectory() && file.exists()) {
            final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_SQV_KEY_FILENAME_PREFIX);
            File[] fileArrListFiles = file.listFiles(new FileFilter(this) { // from class: com.hisense.serialInfoTest.model.SerialCommands.12
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    if (!file2.isFile()) {
                        return false;
                    }
                    String name = file2.getName();
                    if (z) {
                        return SerialCommands.mMenuConfigManager.isAntiFoolSQVKeyNameValid(name);
                    }
                    return !TextUtils.isEmpty(name) && name.toLowerCase().endsWith("bin");
                }
            });
            if (fileArrListFiles != null) {
                if (fileArrListFiles.length > 0) {
                    String string = fileArrListFiles[0].toString();
                    Logger.i("SeeQVault key path is " + string);
                    boolean sQVKey = FacFunction_Adapter.getInstance(this.mContext).setSQVKey(string);
                    FacFunction_Adapter.getInstance(this.mContext).removefactorykeyfile(string);
                    if (sQVKey) {
                        this.mContext.sendBroadcast(checkKeyStatusIntent);
                        Logger.i("send check key status broadcast");
                    }
                    return sQVKey;
                }
                Logger.e("SeeQVault KEY FILE NOT EXIST!!!!!!!!!!!");
            }
        } else {
            Logger.e("tvdatabase SeeQVault folder NOT EXIST!!!!!!!!!!!");
        }
        return false;
    }

    public boolean writeMarlinKey() {
        mMenuConfigManager.setLogToTvcertificate("serial Marlin");
        File file = new File(mSerialKeyPath);
        if (file.isDirectory() && file.exists()) {
            final boolean z = !TextUtils.isEmpty(FacPropertyDemo.HIFAC_MARLIN_KEY_FILENAME_PREFIX);
            File[] fileArrListFiles = file.listFiles(new FileFilter(this) { // from class: com.hisense.serialInfoTest.model.SerialCommands.13
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    if (!file2.isFile()) {
                        return false;
                    }
                    String name = file2.getName();
                    if (z) {
                        return SerialCommands.mMenuConfigManager.isAntiFoolMarlinKeyNameValid(name);
                    }
                    return !TextUtils.isEmpty(name) && name.toLowerCase().endsWith("bin");
                }
            });
            if (fileArrListFiles != null) {
                if (fileArrListFiles.length > 0) {
                    String string = fileArrListFiles[0].toString();
                    Logger.i("Marlin key path is " + string);
                    boolean marlinKey = FacFunction_Adapter.getInstance(this.mContext).setMarlinKey(string);
                    FacFunction_Adapter.getInstance(this.mContext).removefactorykeyfile(string);
                    if (marlinKey) {
                        this.mContext.sendBroadcast(checkKeyStatusIntent);
                        Logger.i("send check key status broadcast");
                    }
                    return marlinKey;
                }
                Logger.e("Marlin KEY FILE NOT EXIST!!!!!!!!!!!");
            }
        } else {
            Logger.e("tvdatabase Marlin folder NOT EXIST!!!!!!!!!!!");
        }
        return false;
    }

    public byte[] getACASId() {
        String aCASId = FacPropertyDemo.HIFAC_MARKET_JP ? mMenuConfigManager.getACASId() : "null";
        Logger.i("getACASId = " + aCASId);
        return HexData.hexStringToBytes(HexData.stringToHexString(aCASId));
    }

    public byte[] getHDDSize() {
        String hDDSize = FacPropertyDemo.HIFAC_MARKET_JP ? mMenuConfigManager.getHDDSize() : "null";
        Logger.i("getHDDSize = " + hDDSize);
        return HexData.hexStringToBytes(HexData.stringToHexString(hDDSize));
    }

    public void exitScreenProtect() {
        this.keySwitcher.shortPressKey(5113);
    }

    public boolean clearOMRunningTime() {
        String string;
        boolean zClearOMRunningTime = FacFunction_Adapter.getInstance(this.mContext).clearOMRunningTime();
        if (zClearOMRunningTime) {
            string = this.mContext.getResources().getString(R.string.str_factorymenu_success_clear_omrunningtime);
        } else {
            string = this.mContext.getResources().getString(R.string.str_factorymenu_fail_clear_omrunningtime);
        }
        mMenuConfigManager.displayHisToast(string);
        return zClearOMRunningTime;
    }

    public boolean setTwelveVoltsStatus(byte[] bArr) {
        Logger.d("setTwelveVoltsStatus");
        int factoryTconStatus = FacFunction_Adapter.getInstance(this.mContext).getFactoryTconStatus();
        Logger.d("setTwelveVoltsStatus tconType = " + factoryTconStatus);
        if (factoryTconStatus != 1) {
            return false;
        }
        try {
            if (bArr.length != 1) {
                Logger.e(" setTwelveVoltsStatus data error!");
                return false;
            }
            int iDecodeToInteger = HexData.decodeToInteger(bArr[0]);
            Logger.d("setTwelveVoltsStatus value: " + iDecodeToInteger);
            return FacFunction_Adapter.getInstance(this.mContext).setTwelveVoltsStatus(iDecodeToInteger) == 0;
        } catch (Exception e) {
            Logger.e(e.getMessage());
            return false;
        }
    }

    public boolean initInnertconBoard() {
        Logger.d("initInnertconBoard");
        int factoryTconStatus = FacFunction_Adapter.getInstance(this.mContext).getFactoryTconStatus();
        Logger.d("initInnertconBoard tconType = " + factoryTconStatus);
        if (factoryTconStatus != 1) {
            return false;
        }
        boolean z = -1 != FacFunction_Adapter.getInstance(this.mContext).initInnertconBoard();
        Logger.i("initInnertconBoard res = " + z);
        return z;
    }

    public boolean writeQDAKey() {
        mMenuConfigManager.setLogToTvcertificate("serial QDAKey");
        File file = new File(mSerialKeyPath);
        if (file.isDirectory() && file.exists()) {
            final boolean z = !TextUtils.isEmpty(mMenuConfigManager.getQDAName());
            File[] fileArrListFiles = file.listFiles(new FileFilter(this) { // from class: com.hisense.serialInfoTest.model.SerialCommands.14
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    if (!file2.isFile()) {
                        return false;
                    }
                    String name = file2.getName();
                    if (z) {
                        return SerialCommands.mMenuConfigManager.isAntiFoolQDAKeyNameValid(name);
                    }
                    return !TextUtils.isEmpty(name) && name.endsWith("enc");
                }
            });
            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                String string = fileArrListFiles[0].toString();
                Logger.i("qda key path is " + string);
                boolean qDAKey = FacFunction_Adapter.getInstance(this.mContext).setQDAKey(string);
                FacFunction_Adapter.getInstance(this.mContext).removefactorykeyfile(string);
                String qDAKey2 = FacFunction_Adapter.getInstance(this.mContext).getQDAKey();
                if (qDAKey && qDAKey2 != null && !qDAKey2.isEmpty() && !"NG".equals(qDAKey2)) {
                    this.mContext.sendBroadcast(checkKeyStatusIntent);
                    Logger.i("send check key status broadcast");
                }
                return qDAKey;
            }
            Logger.e("qda KEY FILE NOT EXIST!!!!!!!!!!!");
        } else {
            Logger.e("tvdatabase qda folder NOT EXIST!!!!!!!!!!!");
        }
        return false;
    }

    public byte[] getQDAKey() {
        Logger.d("getQDAKey");
        try {
            String qDAKey = FacFunction_Adapter.getInstance(this.mContext).getQDAKey();
            Logger.e("getQDAKey key = " + qDAKey);
            return HexData.hexStringToBytes(HexData.stringToHexString(qDAKey));
        } catch (Exception e) {
            Logger.e("getQDAKey" + e.getMessage());
            return null;
        }
    }

    public void startBMWTest(IRootMenuUiListener iRootMenuUiListener, boolean z) {
        if (this.mWifiAdmin.isWifiEnabled()) {
            List<ScanResult> wifiList = this.mWifiAdmin.getWifiList();
            mWirelessList = wifiList;
            WifiAdmin.sortbysinal(wifiList);
            mWirelessList = wifiList;
            FacParamManager.mWifiResult = wifiList.get(0);
        }
        startTestMic(iRootMenuUiListener, false);
    }

    public byte[] getBMWResult() {
        String string = "MIC|";
        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
            String[] strArrSplit = mMenuConfigManager.getMicTestResult().split(" ");
            Logger.d("micArray.length= " + strArrSplit.length);
            Logger.d("micArray[0] " + strArrSplit[0] + "micArray[1]" + strArrSplit[1] + "micArray[2]" + strArrSplit[2]);
            StringBuilder sb = new StringBuilder();
            sb.append("MIC|");
            sb.append(strArrSplit[1]);
            sb.append(":");
            sb.append(strArrSplit[2]);
            string = sb.toString();
        }
        Logger.d("getBMWResult micReslt = " + string);
        String str = "WIFI|";
        if (FacParamManager.mWifiResult != null) {
            Logger.d("mWifiResult = " + FacParamManager.mWifiResult.toString());
            str = "WIFI|" + FacParamManager.mWifiResult.SSID + ":" + FacParamManager.mWifiResult.level;
        }
        Logger.d("getBMWResult wifiResult = " + str);
        String str2 = "BT|";
        if (!FacParamManager.mBlueResult.equalsIgnoreCase("")) {
            String[] strArrSplit2 = FacParamManager.mBlueResult.split(",");
            str2 = "BT|" + strArrSplit2[0] + ":" + strArrSplit2[2];
        }
        Logger.d("getBMWResult blueResult = " + str2);
        String str3 = string + ";" + str + ";" + str2 + ";";
        Logger.d("getBMWResult result = " + str3);
        return HexData.hexStringToBytes(HexData.stringToHexString(str3));
    }

    private void startTestMic(IRootMenuUiListener iRootMenuUiListener, boolean z) {
        if (FacPropertyDemo.HIFAC_MIC_SUPPORT) {
            String micSwitchStatus = mMenuConfigManager.getMicSwitchStatus();
            Logger.d("getMicSwitchStatus =" + micSwitchStatus);
            if ("ON".equals(micSwitchStatus)) {
                Logger.i("startTestMic");
                this.mHisenseFacMicTest.startTestMic(iRootMenuUiListener, false);
                return;
            }
            return;
        }
        Logger.d("MIC is not SUPPORT !");
    }
}
