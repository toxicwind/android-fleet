package com.hisense.serialInfoTest.model;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.hisense.serialInfoTest.util.HexData;
import com.hisense.serialInfoTest.util.Logger;
import com.hisense.support.tv.HisenseTvAPI;
import com.hisense.tvcm.FacFunction;
import com.hisense.tvcm.FacFunction_Adapter;
import com.hisense.tvcm.FacPropertyDemo;
import com.hisense.uifac.R;
import com.hisense.uifac.util.HisLog;
import com.hisense.uifac.util.IRootMenuUiListener;
import com.mediatek.twoworlds.tv.MtkTvTVCallbackHandler;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class SerialFacService extends Service implements IRootMenuUiListener {
    private Context mContext;
    FacBluetoothReceiver mFacBluetoothReceiver;
    private FacFunction mFacFunction;
    private FactorySerialCallbackHandler mFactorySerialCallbackHandler;
    HisenseTvAPI mHisenseTvAPI;
    private Handler mMicHandler;
    private HandlerThread mMicHandlerThread;
    SerialCommands mSerialCommands;
    private Toast mToastView;
    private WifiManager mWifiManager;
    WifiReceiver mWifiReceiver;
    String tag = "serialMessage";
    private int flag = 0;
    private final Handler mSerialHandler = new Handler() { // from class: com.hisense.serialInfoTest.model.SerialFacService.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Logger.i("handle SerialExternalControl message");
            SerialFacService.this.localOnReadFromExternalControl(message.getData().getInt("action"), message.getData().getByteArray("value"));
            super.handleMessage(message);
        }
    };
    private final Handler.Callback mMicHandlerCallback = new Handler.Callback() { // from class: com.hisense.serialInfoTest.model.SerialFacService.2
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            SerialFacService.this.displayHisToast((String) message.obj);
            return false;
        }
    };
    private byte[] mData = null;
    private byte[] mDataSSID = null;
    private byte[] mDataCICard = null;
    private boolean mResult = false;
    private int mValue = 0;

    public String HexResult(boolean z) {
        return z ? "01" : "00";
    }

    @Override // com.hisense.uifac.util.IRootMenuUiListener
    public void notifyZDMicTestResult(boolean z) {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.hisense.uifac.util.IRootMenuUiListener
    public void setMicTestResultToLog(String str, boolean z) {
    }

    private class FactorySerialCallbackHandler extends MtkTvTVCallbackHandler {
        private FactorySerialCallbackHandler() {
        }

        @Override // com.mediatek.twoworlds.tv.MtkTvTVCallbackHandler
        public int notifySerialExternalControlCallback(int i, byte[] bArr) {
            Logger.i("serial action = " + i + "  value = " + bArr);
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putInt("action", i);
            bundle.putByteArray("value", bArr);
            message.setData(bundle);
            Logger.i("send SerialExternalControl message");
            SerialFacService.this.mSerialHandler.sendMessage(message);
            return 0;
        }
    }

    private class WifiReceiver extends BroadcastReceiver {
        private WifiReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && SerialFacService.this.mDataSSID == null && "android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("wifi_state", 1);
                Logger.d("wifi state is" + intExtra);
                if (intExtra == 3) {
                    new Thread(new Runnable() { // from class: com.hisense.serialInfoTest.model.SerialFacService.WifiReceiver.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Logger.d("trying to get wireless SSID");
                            SerialFacService serialFacService = SerialFacService.this;
                            serialFacService.mDataSSID = serialFacService.mSerialCommands.getWirelessSSID();
                        }
                    }).start();
                }
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        Logger.d("SerialFacService onCreate ");
        this.mContext = getApplicationContext();
        StringBuilder sb = new StringBuilder();
        sb.append("SerialFacService mContextState  ");
        sb.append(this.mContext != null);
        Logger.d(sb.toString());
        FacPropertyDemo.initProperties();
        FacFunction_Adapter facFunction_Adapter = FacFunction_Adapter.getInstance(getApplicationContext());
        this.mFacFunction = facFunction_Adapter;
        facFunction_Adapter.initMtkManagers(this.mContext);
        this.mSerialCommands = SerialCommands.getInstance(this.mContext);
        this.mWifiManager = (WifiManager) getSystemService("wifi");
        HisLog.i("Source Service is available");
        this.mWifiReceiver = new WifiReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        registerReceiver(this.mWifiReceiver, intentFilter);
        this.mHisenseTvAPI = HisenseTvAPI.getInstance(this.mContext);
        HandlerThread handlerThread = new HandlerThread("HisenseSerialMicThread");
        this.mMicHandlerThread = handlerThread;
        handlerThread.start();
        this.mMicHandler = new Handler(this.mMicHandlerThread.getLooper(), this.mMicHandlerCallback);
        startBluetoothReceiver();
        this.mFacFunction.readDriverInfo();
        super.onCreate();
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        Logger.d(" SerialFacService onStart");
        if (this.mFactorySerialCallbackHandler != null) {
            Logger.i("FactorySerialCallback removelistener");
            this.mFactorySerialCallbackHandler.removeListener();
        }
        Logger.i("FactorySerialCallback start");
        this.mFactorySerialCallbackHandler = new FactorySerialCallbackHandler();
        if (this.flag == 0) {
            this.mHisenseTvAPI.startSerialWorkThread();
            this.mHisenseTvAPI.startTestB2BHWPortWorkThread();
            this.flag++;
        }
        Logger.i("FactorySerialCallback end");
        if (!this.mWifiManager.isWifiEnabled()) {
            this.mWifiManager.setWifiEnabled(true);
        }
        if (this.mDataCICard == null || HexData.stringToHexString("0").equals(HexData.bytesToHexString(this.mDataCICard))) {
            new Thread(new Runnable() { // from class: com.hisense.serialInfoTest.model.SerialFacService.3
                @Override // java.lang.Runnable
                public void run() {
                    Logger.d("trying to get CICard info");
                    SerialFacService serialFacService = SerialFacService.this;
                    serialFacService.mDataCICard = serialFacService.mSerialCommands.getCICardInfoAhead();
                }
            }).start();
        }
    }

    private void startBluetoothReceiver() {
        this.mFacBluetoothReceiver = new FacBluetoothReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.SCAN_MODE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("com.hisense.fac.HISENSE_FAC_SERIAL_START_ACTION");
        this.mContext.registerReceiver(this.mFacBluetoothReceiver, intentFilter);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Logger.d("SerialFacService enter onStartCommand!!");
        return super.onStartCommand(intent, 1, i2);
    }

    public void localOnReadFromExternalControl(int i, byte[] bArr) {
        String runningTime;
        int lightSensor;
        Logger.d("OnReadFromExternalControl");
        if (FacPropertyDemo.HIFAC_IS_OLED_TVS) {
            this.mSerialCommands.exitScreenProtect();
        }
        Logger.d("Action: " + Integer.toHexString(i));
        StringBuilder sb = new StringBuilder();
        sb.append("ConfigValue null ---> ");
        int iDecodeToInteger = 0;
        sb.append(bArr == null);
        Logger.d(sb.toString());
        if (bArr != null && bArr.length != 0) {
            Logger.d("print ConfigValue value ");
            Logger.d("ConfigValue length ---> " + bArr.length);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("ConfigValue null ---> ");
            sb2.append(bArr == null);
            Logger.d(sb2.toString());
            for (int i2 = 0; i2 < bArr.length; i2++) {
            }
        }
        String versions = null;
        if (this.mData != null) {
            this.mData = null;
        }
        String str = "";
        switch (i) {
            case -1610612736:
                byte[] bArrHexStringToBytes = HexData.hexStringToBytes(HexResult(true));
                this.mData = bArrHexStringToBytes;
                this.mFacFunction.writeToExternalControl(-1610612736, bArrHexStringToBytes, bArrHexStringToBytes.length);
                break;
            case -1593769984:
                boolean zExcutePowerOff = this.mSerialCommands.excutePowerOff();
                this.mResult = zExcutePowerOff;
                byte[] bArrHexStringToBytes2 = HexData.hexStringToBytes(HexResult(zExcutePowerOff));
                this.mData = bArrHexStringToBytes2;
                this.mFacFunction.writeToExternalControl(-1593769984, bArrHexStringToBytes2, bArrHexStringToBytes2.length);
                break;
            case -1593704448:
                boolean zEnterOrExistFac = this.mSerialCommands.enterOrExistFac(bArr);
                this.mResult = zEnterOrExistFac;
                byte[] bArrHexStringToBytes3 = HexData.hexStringToBytes(HexResult(zEnterOrExistFac));
                this.mData = bArrHexStringToBytes3;
                this.mFacFunction.writeToExternalControl(-1593704448, bArrHexStringToBytes3, bArrHexStringToBytes3.length);
                break;
            case -1593638912:
                boolean zChangeChannels = this.mSerialCommands.changeChannels(bArr);
                this.mResult = zChangeChannels;
                byte[] bArrHexStringToBytes4 = HexData.hexStringToBytes(HexResult(zChangeChannels));
                this.mData = bArrHexStringToBytes4;
                this.mFacFunction.writeToExternalControl(-1593638912, bArrHexStringToBytes4, bArrHexStringToBytes4.length);
                break;
            case -1593638911:
                boolean zChangeChannels4byte = this.mSerialCommands.changeChannels4byte(bArr);
                this.mResult = zChangeChannels4byte;
                byte[] bArrHexStringToBytes5 = HexData.hexStringToBytes(HexResult(zChangeChannels4byte));
                this.mData = bArrHexStringToBytes5;
                this.mFacFunction.writeToExternalControl(-1593638911, bArrHexStringToBytes5, bArrHexStringToBytes5.length);
                break;
            case -1593573376:
                boolean zSwitchAVSource = this.mSerialCommands.switchAVSource(bArr);
                this.mResult = zSwitchAVSource;
                byte[] bArrHexStringToBytes6 = HexData.hexStringToBytes(HexResult(zSwitchAVSource));
                this.mData = bArrHexStringToBytes6;
                this.mFacFunction.writeToExternalControl(-1593573376, bArrHexStringToBytes6, bArrHexStringToBytes6.length);
                break;
            case -1593507840:
                boolean zSwitchYPbPrSource = this.mSerialCommands.switchYPbPrSource(bArr);
                this.mResult = zSwitchYPbPrSource;
                byte[] bArrHexStringToBytes7 = HexData.hexStringToBytes(HexResult(zSwitchYPbPrSource));
                this.mData = bArrHexStringToBytes7;
                this.mFacFunction.writeToExternalControl(-1593507840, bArrHexStringToBytes7, bArrHexStringToBytes7.length);
                break;
            case -1593442304:
                boolean zSwitchHDMISource = this.mSerialCommands.switchHDMISource(bArr);
                this.mResult = zSwitchHDMISource;
                byte[] bArrHexStringToBytes8 = HexData.hexStringToBytes(HexResult(zSwitchHDMISource));
                this.mData = bArrHexStringToBytes8;
                this.mFacFunction.writeToExternalControl(-1593442304, bArrHexStringToBytes8, bArrHexStringToBytes8.length);
                break;
            case -1593376768:
                boolean zSwitchVGASource = this.mSerialCommands.switchVGASource(bArr);
                this.mResult = zSwitchVGASource;
                byte[] bArrHexStringToBytes9 = HexData.hexStringToBytes(HexResult(zSwitchVGASource));
                this.mData = bArrHexStringToBytes9;
                this.mFacFunction.writeToExternalControl(-1593376768, bArrHexStringToBytes9, bArrHexStringToBytes9.length);
                break;
            case -1593311232:
                boolean zSwitchDTVType = this.mSerialCommands.switchDTVType(bArr);
                this.mResult = zSwitchDTVType;
                byte[] bArrHexStringToBytes10 = HexData.hexStringToBytes(HexResult(zSwitchDTVType));
                this.mData = bArrHexStringToBytes10;
                this.mFacFunction.writeToExternalControl(-1593311232, bArrHexStringToBytes10, bArrHexStringToBytes10.length);
                break;
            case -1593245696:
                boolean volume = this.mSerialCommands.setVolume(HexData.decodeToInteger(bArr[0]));
                this.mResult = volume;
                byte[] bArrHexStringToBytes11 = HexData.hexStringToBytes(HexResult(volume));
                this.mData = bArrHexStringToBytes11;
                this.mFacFunction.writeToExternalControl(-1593245696, bArrHexStringToBytes11, bArrHexStringToBytes11.length);
                break;
            case -1593180160:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setBrightness(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes12 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes12;
                this.mFacFunction.writeToExternalControl(-1593180160, bArrHexStringToBytes12, bArrHexStringToBytes12.length);
                break;
            case -1593114624:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setContrast(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes13 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes13;
                this.mFacFunction.writeToExternalControl(-1593114624, bArrHexStringToBytes13, bArrHexStringToBytes13.length);
                break;
            case -1593049088:
                if (bArr != null && bArr.length == 1) {
                    if (FacPropertyDemo.HIFAC_MARKET.equals(FacPropertyDemo.MarketType.EM)) {
                        this.mResult = this.mSerialCommands.setPictureModeEM(HexData.decodeToInteger(bArr[0]));
                    } else {
                        this.mResult = this.mSerialCommands.setPictureMode(HexData.decodeToInteger(bArr[0]));
                    }
                }
                byte[] bArrHexStringToBytes14 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes14;
                this.mFacFunction.writeToExternalControl(-1593049088, bArrHexStringToBytes14, bArrHexStringToBytes14.length);
                break;
            case -1592983552:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setColorTemprature(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes15 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes15;
                this.mFacFunction.writeToExternalControl(-1592983552, bArrHexStringToBytes15, bArrHexStringToBytes15.length);
                break;
            case -1592918016:
                boolean zTestPattern = this.mSerialCommands.testPattern(bArr);
                this.mResult = zTestPattern;
                byte[] bArrHexStringToBytes16 = HexData.hexStringToBytes(HexResult(zTestPattern));
                this.mData = bArrHexStringToBytes16;
                this.mFacFunction.writeToExternalControl(-1592918016, bArrHexStringToBytes16, bArrHexStringToBytes16.length);
                break;
            case -1592852480:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setModelPanel(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes17 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes17;
                this.mFacFunction.writeToExternalControl(-1592852480, bArrHexStringToBytes17, bArrHexStringToBytes17.length);
                break;
            case -1592786944:
                boolean logo = this.mSerialCommands.setLogo(bArr);
                this.mResult = logo;
                byte[] bArrHexStringToBytes18 = HexData.hexStringToBytes(HexResult(logo));
                this.mData = bArrHexStringToBytes18;
                this.mFacFunction.writeToExternalControl(-1592786944, bArrHexStringToBytes18, bArrHexStringToBytes18.length);
                break;
            case -1592721408:
                boolean language = this.mSerialCommands.setLanguage(bArr);
                this.mResult = language;
                byte[] bArrHexStringToBytes19 = HexData.hexStringToBytes(HexResult(language));
                this.mData = bArrHexStringToBytes19;
                this.mFacFunction.writeToExternalControl(-1592721408, bArrHexStringToBytes19, bArrHexStringToBytes19.length);
                break;
            case -1592655872:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setColor(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes20 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes20;
                this.mFacFunction.writeToExternalControl(-1592655872, bArrHexStringToBytes20, bArrHexStringToBytes20.length);
                break;
            case -1592590336:
                break;
            case -1592459264:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setBacklight(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes21 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes21;
                this.mFacFunction.writeToExternalControl(-1592459264, bArrHexStringToBytes21, bArrHexStringToBytes21.length);
                break;
            case -1592393728:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setEnergySaving(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes22 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes22;
                this.mFacFunction.writeToExternalControl(-1592393728, bArrHexStringToBytes22, bArrHexStringToBytes22.length);
                break;
            case -1592328192:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setZoomMode(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes23 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes23;
                this.mFacFunction.writeToExternalControl(-1592328192, bArrHexStringToBytes23, bArrHexStringToBytes23.length);
                break;
            case -1592066048:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.set3DMode(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes24 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes24;
                this.mFacFunction.writeToExternalControl(-1592066048, bArrHexStringToBytes24, bArrHexStringToBytes24.length);
                break;
            case -1591934976:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setWBPattern(HexData.decodeToInteger(bArr[0]), true);
                }
                byte[] bArrHexStringToBytes25 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes25;
                this.mFacFunction.writeToExternalControl(-1591934976, bArrHexStringToBytes25, bArrHexStringToBytes25.length);
                break;
            case -1591934975:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setWBPattern(HexData.decodeToInteger(bArr[0]), false);
                }
                byte[] bArrHexStringToBytes26 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes26;
                this.mFacFunction.writeToExternalControl(-1591934975, bArrHexStringToBytes26, bArrHexStringToBytes26.length);
                break;
            case -1591738368:
                if (bArr != null) {
                    this.mResult = this.mSerialCommands.setModulePattern(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes27 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes27;
                this.mFacFunction.writeToExternalControl(-1591738368, bArrHexStringToBytes27, bArrHexStringToBytes27.length);
                break;
            case -1591607296:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setRemoteStatus(HexData.decodeToInteger(bArr[0]));
                    Logger.d("setRemoteStatus : data = " + bArr);
                }
                byte[] bArrHexStringToBytes28 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes28;
                this.mFacFunction.writeToExternalControl(-1591607296, bArrHexStringToBytes28, bArrHexStringToBytes28.length);
                break;
            case -1591541760:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setProjectId(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes29 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes29;
                this.mFacFunction.writeToExternalControl(-1591541760, bArrHexStringToBytes29, bArrHexStringToBytes29.length);
                break;
            case -1591410688:
                if (bArr != null) {
                    this.mResult = this.mFacFunction.setLvdsSscDisableOnce(bArr[0]) == 0;
                }
                byte[] bArrHexStringToBytes30 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes30;
                this.mFacFunction.writeToExternalControl(-1591410688, bArrHexStringToBytes30, bArrHexStringToBytes30.length);
                break;
            case -1591083008:
                boolean twelveVoltsStatus = this.mSerialCommands.setTwelveVoltsStatus(bArr);
                this.mResult = twelveVoltsStatus;
                byte[] bArrHexStringToBytes31 = HexData.hexStringToBytes(HexResult(twelveVoltsStatus));
                this.mData = bArrHexStringToBytes31;
                this.mFacFunction.writeToExternalControl(-1591083008, bArrHexStringToBytes31, bArrHexStringToBytes31.length);
                break;
            case -1591017472:
                boolean zInitInnertconBoard = this.mSerialCommands.initInnertconBoard();
                this.mResult = zInitInnertconBoard;
                byte[] bArrHexStringToBytes32 = HexData.hexStringToBytes(HexResult(zInitInnertconBoard));
                this.mData = bArrHexStringToBytes32;
                this.mFacFunction.writeToExternalControl(-1591017472, bArrHexStringToBytes32, bArrHexStringToBytes32.length);
                break;
            case -1577058304:
                byte[] bArrEnterDMP = this.mSerialCommands.enterDMP();
                int length = bArrEnterDMP.length;
                byte[] bArr2 = new byte[length + 1];
                this.mData = bArr2;
                bArr2[0] = 1;
                System.arraycopy(bArrEnterDMP, 0, bArr2, 1, length);
                int length2 = this.mData.length;
                Logger.d("returnLength: " + length2 + "mData :" + this.mData);
                this.mFacFunction.writeToExternalControl(-1577058304, this.mData, length2);
                break;
            case -1576992768:
                boolean zEnterDMPAutoPLay = this.mSerialCommands.enterDMPAutoPLay();
                this.mResult = zEnterDMPAutoPLay;
                byte[] bArrHexStringToBytes33 = HexData.hexStringToBytes(HexResult(zEnterDMPAutoPLay));
                this.mData = bArrHexStringToBytes33;
                this.mFacFunction.writeToExternalControl(-1576992768, bArrHexStringToBytes33, bArrHexStringToBytes33.length);
                break;
            case -1576927232:
                boolean macAddress = this.mSerialCommands.setMacAddress(bArr);
                this.mResult = macAddress;
                byte[] bArrHexStringToBytes34 = HexData.hexStringToBytes(HexResult(macAddress));
                this.mData = bArrHexStringToBytes34;
                this.mFacFunction.writeToExternalControl(-1576927232, bArrHexStringToBytes34, bArrHexStringToBytes34.length);
                break;
            case -1576861696:
                boolean lineIPAddress = this.mSerialCommands.setLineIPAddress(bArr);
                this.mResult = lineIPAddress;
                byte[] bArrHexStringToBytes35 = HexData.hexStringToBytes(HexResult(lineIPAddress));
                this.mData = bArrHexStringToBytes35;
                this.mFacFunction.writeToExternalControl(-1576861696, bArrHexStringToBytes35, bArrHexStringToBytes35.length);
                break;
            case -1576796160:
                boolean lineIPAddress2 = this.mSerialCommands.setLineIPAddress(bArr);
                this.mResult = lineIPAddress2;
                byte[] bArrHexStringToBytes36 = HexData.hexStringToBytes(HexResult(lineIPAddress2));
                this.mData = bArrHexStringToBytes36;
                this.mFacFunction.writeToExternalControl(-1576796160, bArrHexStringToBytes36, bArrHexStringToBytes36.length);
                break;
            case -1576730624:
                boolean device23bitNum = this.mSerialCommands.setDevice23bitNum(bArr);
                this.mResult = device23bitNum;
                byte[] bArrHexStringToBytes37 = HexData.hexStringToBytes(HexResult(device23bitNum));
                this.mData = bArrHexStringToBytes37;
                this.mFacFunction.writeToExternalControl(-1576730624, bArrHexStringToBytes37, bArrHexStringToBytes37.length);
                break;
            case -1575485440:
                boolean zCompareMachineName = this.mSerialCommands.compareMachineName(bArr);
                this.mResult = zCompareMachineName;
                byte[] bArrHexStringToBytes38 = HexData.hexStringToBytes(HexResult(zCompareMachineName));
                this.mData = bArrHexStringToBytes38;
                this.mFacFunction.writeToExternalControl(-1575485440, bArrHexStringToBytes38, bArrHexStringToBytes38.length);
                break;
            case -1575419904:
                boolean functionNumber = this.mSerialCommands.setFunctionNumber(bArr);
                this.mResult = functionNumber;
                byte[] bArrHexStringToBytes39 = HexData.hexStringToBytes(HexResult(functionNumber));
                this.mData = bArrHexStringToBytes39;
                this.mFacFunction.writeToExternalControl(-1575419904, bArrHexStringToBytes39, bArrHexStringToBytes39.length);
                break;
            case -1575354368:
                boolean country = this.mSerialCommands.setCountry(bArr);
                this.mResult = country;
                byte[] bArrHexStringToBytes40 = HexData.hexStringToBytes(HexResult(country));
                this.mData = bArrHexStringToBytes40;
                this.mFacFunction.writeToExternalControl(-1575354368, bArrHexStringToBytes40, bArrHexStringToBytes40.length);
                break;
            case -1575288832:
                Logger.d("exit dmp play************");
                boolean zExitDMPAutoPlay = this.mSerialCommands.exitDMPAutoPlay();
                this.mResult = zExitDMPAutoPlay;
                byte[] bArrHexStringToBytes41 = HexData.hexStringToBytes(HexResult(zExitDMPAutoPlay));
                this.mData = bArrHexStringToBytes41;
                this.mFacFunction.writeToExternalControl(-1575288832, bArrHexStringToBytes41, bArrHexStringToBytes41.length);
                break;
            case -1575157760:
                boolean chipNumber = this.mSerialCommands.setChipNumber(bArr);
                this.mResult = chipNumber;
                byte[] bArrHexStringToBytes42 = HexData.hexStringToBytes(HexResult(chipNumber));
                this.mData = bArrHexStringToBytes42;
                this.mFacFunction.writeToExternalControl(-1575157760, bArrHexStringToBytes42, bArrHexStringToBytes42.length);
                break;
            case -1575092224:
                if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                    this.mResult = this.mSerialCommands.setVcom(HexData.decodeToInteger(bArr[0]));
                } else {
                    this.mResult = false;
                }
                byte[] bArrHexStringToBytes43 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes43;
                this.mFacFunction.writeToExternalControl(-1575092224, bArrHexStringToBytes43, bArrHexStringToBytes43.length);
                break;
            case -1575092223:
                if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                    this.mResult = this.mSerialCommands.saveVcom(HexData.decodeToInteger(bArr[0]));
                } else {
                    this.mResult = false;
                }
                byte[] bArrHexStringToBytes44 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes44;
                this.mFacFunction.writeToExternalControl(-1575092223, bArrHexStringToBytes44, bArrHexStringToBytes44.length);
                break;
            case -1575026688:
                boolean balance = this.mSerialCommands.setBalance(HexData.decodeToInteger(bArr[0]));
                this.mResult = balance;
                byte[] bArrHexStringToBytes45 = HexData.hexStringToBytes(HexResult(balance));
                this.mData = bArrHexStringToBytes45;
                this.mFacFunction.writeToExternalControl(-1575026688, bArrHexStringToBytes45, bArrHexStringToBytes45.length);
                break;
            case -1574764544:
                boolean zInitFactoryChannel = this.mSerialCommands.initFactoryChannel(bArr);
                this.mResult = zInitFactoryChannel;
                byte[] bArrHexStringToBytes46 = HexData.hexStringToBytes(HexResult(zInitFactoryChannel));
                this.mData = bArrHexStringToBytes46;
                this.mFacFunction.writeToExternalControl(-1574764544, bArrHexStringToBytes46, bArrHexStringToBytes46.length);
                break;
            case -1574502400:
                boolean localDimmingSwitch = this.mSerialCommands.setLocalDimmingSwitch(HexData.decodeToInteger(bArr[0]));
                this.mResult = localDimmingSwitch;
                byte[] bArrHexStringToBytes47 = HexData.hexStringToBytes(HexResult(localDimmingSwitch));
                this.mData = bArrHexStringToBytes47;
                this.mFacFunction.writeToExternalControl(-1574502400, bArrHexStringToBytes47, bArrHexStringToBytes47.length);
                break;
            case -1574371328:
                boolean clientType = this.mSerialCommands.setClientType(bArr);
                this.mResult = clientType;
                byte[] bArrHexStringToBytes48 = HexData.hexStringToBytes(HexResult(clientType));
                this.mData = bArrHexStringToBytes48;
                this.mFacFunction.writeToExternalControl(-1574371328, bArrHexStringToBytes48, bArrHexStringToBytes48.length);
                break;
            case -1574109184:
                if (bArr[0] == 0) {
                    this.mResult = this.mSerialCommands.setLNB(HexData.decodeToInteger(bArr[1]));
                } else {
                    this.mResult = false;
                }
                byte[] bArrHexStringToBytes49 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes49;
                this.mFacFunction.writeToExternalControl(-1574109184, bArrHexStringToBytes49, bArrHexStringToBytes49.length);
                break;
            case -1572798464:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setMicDb(HexData.decodeToInteger(bArr[0]));
                    Logger.d("setMicDb : data = " + bArr);
                }
                byte[] bArrHexStringToBytes50 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes50;
                this.mFacFunction.writeToExternalControl(-1572798464, bArrHexStringToBytes50, bArrHexStringToBytes50.length);
                break;
            case -1572732928:
                int micDb = this.mSerialCommands.getMicDb();
                this.mValue = micDb;
                String strIntegerToHexString = HexData.integerToHexString(micDb);
                if (strIntegerToHexString.length() < 2) {
                    strIntegerToHexString = "0" + strIntegerToHexString;
                }
                byte[] bArrHexStringToBytes51 = HexData.hexStringToBytes2(strIntegerToHexString);
                this.mData = bArrHexStringToBytes51;
                this.mFacFunction.writeToExternalControl(-1572732928, bArrHexStringToBytes51, bArrHexStringToBytes51.length);
                break;
            case -1572143102:
                byte[] pVRKey = this.mSerialCommands.getPVRKey();
                this.mData = pVRKey;
                this.mFacFunction.writeToExternalControl(-1572143102, this.mData, pVRKey != null ? pVRKey.length : 0);
                break;
            case -1571946494:
                byte[] qDAKey = this.mSerialCommands.getQDAKey();
                this.mData = qDAKey;
                this.mFacFunction.writeToExternalControl(-1571946494, this.mData, qDAKey != null ? qDAKey.length : 0);
                break;
            case -1560281088:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setRDrvPlus(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes52 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes52;
                this.mFacFunction.writeToExternalControl(-1560281088, bArrHexStringToBytes52, bArrHexStringToBytes52.length);
                break;
            case -1560280832:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setRDrvMinus(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes53 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes53;
                this.mFacFunction.writeToExternalControl(-1560280832, bArrHexStringToBytes53, bArrHexStringToBytes53.length);
                break;
            case -1560280576:
                iDecodeToInteger = bArr.length > 1 ? (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]) : 0;
                Logger.d("0xA3030200 realValue = " + iDecodeToInteger);
                boolean rdrv = this.mSerialCommands.setRDRV(iDecodeToInteger);
                this.mResult = rdrv;
                byte[] bArrHexStringToBytes54 = HexData.hexStringToBytes(HexResult(rdrv));
                this.mData = bArrHexStringToBytes54;
                this.mFacFunction.writeToExternalControl(-1560280576, bArrHexStringToBytes54, bArrHexStringToBytes54.length);
                break;
            case -1560215552:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setGDrvPlus(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes55 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes55;
                this.mFacFunction.writeToExternalControl(-1560215552, bArrHexStringToBytes55, bArrHexStringToBytes55.length);
                break;
            case -1560215296:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setGDrvMinus(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes56 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes56;
                this.mFacFunction.writeToExternalControl(-1560215296, bArrHexStringToBytes56, bArrHexStringToBytes56.length);
                break;
            case -1560215040:
                iDecodeToInteger = bArr.length > 1 ? (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]) : 0;
                Logger.d("0xA3030200 realValue = " + iDecodeToInteger);
                boolean gdrv = this.mSerialCommands.setGDRV(iDecodeToInteger);
                this.mResult = gdrv;
                byte[] bArrHexStringToBytes57 = HexData.hexStringToBytes(HexResult(gdrv));
                this.mData = bArrHexStringToBytes57;
                this.mFacFunction.writeToExternalControl(-1560215040, bArrHexStringToBytes57, bArrHexStringToBytes57.length);
                break;
            case -1560150016:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setBDrvPlus(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes58 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes58;
                this.mFacFunction.writeToExternalControl(-1560150016, bArrHexStringToBytes58, bArrHexStringToBytes58.length);
                break;
            case -1560149760:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setBDrvMinus(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes59 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes59;
                this.mFacFunction.writeToExternalControl(-1560149760, bArrHexStringToBytes59, bArrHexStringToBytes59.length);
                break;
            case -1560149504:
                iDecodeToInteger = bArr.length > 1 ? (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]) : 0;
                Logger.d("0xA3030200 realValue = " + iDecodeToInteger);
                boolean bdrv = this.mSerialCommands.setBDRV(iDecodeToInteger);
                this.mResult = bdrv;
                byte[] bArrHexStringToBytes60 = HexData.hexStringToBytes(HexResult(bdrv));
                this.mData = bArrHexStringToBytes60;
                this.mFacFunction.writeToExternalControl(-1560149504, bArrHexStringToBytes60, bArrHexStringToBytes60.length);
                break;
            case -1560084480:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setRCutPlus(HexData.decodeToInteger(bArr[0]));
                }
                if (bArr.length > 1) {
                    int iDecodeToInteger2 = (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]);
                    Logger.d("0xA3030000 realValue = " + iDecodeToInteger2);
                    this.mResult = this.mSerialCommands.setRCutPlus(iDecodeToInteger2);
                }
                byte[] bArrHexStringToBytes61 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes61;
                this.mFacFunction.writeToExternalControl(-1560084480, bArrHexStringToBytes61, bArrHexStringToBytes61.length);
                break;
            case -1560084224:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setRCutMinus(HexData.decodeToInteger(bArr[0]));
                }
                if (bArr.length > 1) {
                    int iDecodeToInteger3 = (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]);
                    Logger.d("0xA3030100 realValue = " + iDecodeToInteger3);
                    this.mResult = this.mSerialCommands.setRCutMinus(iDecodeToInteger3);
                }
                byte[] bArrHexStringToBytes62 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes62;
                this.mFacFunction.writeToExternalControl(-1560084224, bArrHexStringToBytes62, bArrHexStringToBytes62.length);
                break;
            case -1560083968:
                iDecodeToInteger = bArr.length > 1 ? (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]) : 0;
                Logger.d("0xA3030200 realValue = " + iDecodeToInteger);
                boolean rcut = this.mSerialCommands.setRCUT(iDecodeToInteger);
                this.mResult = rcut;
                byte[] bArrHexStringToBytes63 = HexData.hexStringToBytes(HexResult(rcut));
                this.mData = bArrHexStringToBytes63;
                this.mFacFunction.writeToExternalControl(-1560083968, bArrHexStringToBytes63, bArrHexStringToBytes63.length);
                break;
            case -1560018944:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setGCutPlus(HexData.decodeToInteger(bArr[0]));
                }
                if (bArr.length > 1) {
                    int iDecodeToInteger4 = (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]);
                    Logger.d("0xA3040000 realValue = " + iDecodeToInteger4);
                    this.mResult = this.mSerialCommands.setGCutPlus(iDecodeToInteger4);
                }
                byte[] bArrHexStringToBytes64 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes64;
                this.mFacFunction.writeToExternalControl(-1560018944, bArrHexStringToBytes64, bArrHexStringToBytes64.length);
                break;
            case -1560018688:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setGCutMinus(HexData.decodeToInteger(bArr[0]));
                }
                if (bArr.length > 1) {
                    int iDecodeToInteger5 = (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]);
                    Logger.d("0xA3040100 realValue = " + iDecodeToInteger5);
                    this.mResult = this.mSerialCommands.setGCutMinus(iDecodeToInteger5);
                }
                byte[] bArrHexStringToBytes65 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes65;
                this.mFacFunction.writeToExternalControl(-1560018688, bArrHexStringToBytes65, bArrHexStringToBytes65.length);
                break;
            case -1560018432:
                iDecodeToInteger = bArr.length > 1 ? (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]) : 0;
                Logger.d("0xA3040200 realValue = " + iDecodeToInteger);
                boolean gcut = this.mSerialCommands.setGCUT(iDecodeToInteger);
                this.mResult = gcut;
                byte[] bArrHexStringToBytes66 = HexData.hexStringToBytes(HexResult(gcut));
                this.mData = bArrHexStringToBytes66;
                this.mFacFunction.writeToExternalControl(-1560018432, bArrHexStringToBytes66, bArrHexStringToBytes66.length);
                break;
            case -1559953408:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setBCutPlus(HexData.decodeToInteger(bArr[0]));
                }
                if (bArr.length > 1) {
                    int iDecodeToInteger6 = (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]);
                    Logger.d("0xA3050000 realValue = " + iDecodeToInteger6);
                    this.mResult = this.mSerialCommands.setBCutPlus(iDecodeToInteger6);
                }
                byte[] bArrHexStringToBytes67 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes67;
                this.mFacFunction.writeToExternalControl(-1559953408, bArrHexStringToBytes67, bArrHexStringToBytes67.length);
                break;
            case -1559953152:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setBCutMinus(HexData.decodeToInteger(bArr[0]));
                } else if (bArr.length > 1) {
                    int iDecodeToInteger7 = (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]);
                    Logger.d("0xA3050100 realValue = " + iDecodeToInteger7);
                    this.mResult = this.mSerialCommands.setBCutMinus(iDecodeToInteger7);
                }
                byte[] bArrHexStringToBytes68 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes68;
                this.mFacFunction.writeToExternalControl(-1559953152, bArrHexStringToBytes68, bArrHexStringToBytes68.length);
                break;
            case -1559952896:
                iDecodeToInteger = bArr.length > 1 ? (HexData.decodeToInteger(bArr[0]) * 256) + HexData.decodeToInteger(bArr[1]) : 0;
                Logger.d("0xA3040200 realValue = " + iDecodeToInteger);
                boolean bCut = this.mSerialCommands.setBCut(iDecodeToInteger);
                this.mResult = bCut;
                byte[] bArrHexStringToBytes69 = HexData.hexStringToBytes(HexResult(bCut));
                this.mData = bArrHexStringToBytes69;
                this.mFacFunction.writeToExternalControl(-1559952896, bArrHexStringToBytes69, bArrHexStringToBytes69.length);
                break;
            case -1559756800:
                boolean zCloseGamma = this.mSerialCommands.closeGamma();
                this.mResult = zCloseGamma;
                byte[] bArrHexStringToBytes70 = HexData.hexStringToBytes(HexResult(zCloseGamma));
                this.mData = bArrHexStringToBytes70;
                this.mFacFunction.writeToExternalControl(-1559756800, bArrHexStringToBytes70, bArrHexStringToBytes70.length);
                break;
            case -1559756799:
                boolean gamma = this.mSerialCommands.setGamma(bArr);
                this.mResult = gamma;
                byte[] bArrHexStringToBytes71 = HexData.hexStringToBytes(HexResult(gamma));
                this.mData = bArrHexStringToBytes71;
                this.mFacFunction.writeToExternalControl(-1559756799, bArrHexStringToBytes71, bArrHexStringToBytes71.length);
                break;
            case -1559756544:
                boolean zClose4096Gamma = this.mSerialCommands.close4096Gamma();
                this.mResult = zClose4096Gamma;
                byte[] bArrHexStringToBytes72 = HexData.hexStringToBytes(HexResult(zClose4096Gamma));
                this.mData = bArrHexStringToBytes72;
                this.mFacFunction.writeToExternalControl(-1559756544, bArrHexStringToBytes72, bArrHexStringToBytes72.length);
                break;
            case -1559756543:
                boolean z = this.mSerialCommands.set4096Gamma(bArr);
                this.mResult = z;
                byte[] bArrHexStringToBytes73 = HexData.hexStringToBytes(HexResult(z));
                this.mData = bArrHexStringToBytes73;
                this.mFacFunction.writeToExternalControl(-1559756543, bArrHexStringToBytes73, bArrHexStringToBytes73.length);
                break;
            case -1559494656:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setWBAdjustResult(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes74 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes74;
                this.mFacFunction.writeToExternalControl(-1559494656, bArrHexStringToBytes74, bArrHexStringToBytes74.length);
                break;
            case -1559429120:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setGammaAdjustResult(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes75 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes75;
                this.mFacFunction.writeToExternalControl(-1559429120, bArrHexStringToBytes75, bArrHexStringToBytes75.length);
                break;
            case -1559363584:
                byte[] colorTemList = this.mSerialCommands.getColorTemList();
                this.mData = colorTemList;
                this.mFacFunction.writeToExternalControl(-1559363584, colorTemList, colorTemList.length);
                break;
            case -1559101440:
                String gammaPara = this.mSerialCommands.getGammaPara();
                Logger.d("getGammaPara is : " + gammaPara);
                byte[] bArrHexStringToBytes76 = HexData.hexStringToBytes(HexData.stringToHexString(gammaPara));
                this.mData = bArrHexStringToBytes76;
                this.mFacFunction.writeToExternalControl(-1559101440, bArrHexStringToBytes76, bArrHexStringToBytes76.length);
                break;
            case -1558839296:
                boolean zResetWhiteBalance = this.mSerialCommands.resetWhiteBalance();
                this.mResult = zResetWhiteBalance;
                byte[] bArrHexStringToBytes77 = HexData.hexStringToBytes(HexResult(zResetWhiteBalance));
                this.mData = bArrHexStringToBytes77;
                this.mFacFunction.writeToExternalControl(-1558839296, bArrHexStringToBytes77, bArrHexStringToBytes77.length);
                break;
            case -1558773760:
                boolean gammaStartFlag = this.mSerialCommands.setGammaStartFlag();
                this.mResult = gammaStartFlag;
                byte[] bArrHexStringToBytes78 = HexData.hexStringToBytes(HexResult(gammaStartFlag));
                this.mData = bArrHexStringToBytes78;
                this.mFacFunction.writeToExternalControl(-1558773760, bArrHexStringToBytes78, bArrHexStringToBytes78.length);
                break;
            case -1558708224:
                boolean zCloseDemuraCard = this.mSerialCommands.closeDemuraCard();
                this.mResult = zCloseDemuraCard;
                byte[] bArrHexStringToBytes79 = HexData.hexStringToBytes(HexResult(zCloseDemuraCard));
                this.mData = bArrHexStringToBytes79;
                this.mFacFunction.writeToExternalControl(-1558708224, bArrHexStringToBytes79, bArrHexStringToBytes79.length);
                break;
            case -1558708223:
                if (bArr.length > 1) {
                    this.mResult = this.mSerialCommands.setDemuraCard(false, HexData.decodeToInteger(bArr[1]));
                }
                byte[] bArrHexStringToBytes80 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes80;
                this.mFacFunction.writeToExternalControl(-1558708223, bArrHexStringToBytes80, bArrHexStringToBytes80.length);
                break;
            case -1558708222:
                if (bArr.length > 1) {
                    this.mResult = this.mSerialCommands.setDemuraCard(true, HexData.decodeToInteger(bArr[1]));
                }
                byte[] bArrHexStringToBytes81 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes81;
                this.mFacFunction.writeToExternalControl(-1558708222, bArrHexStringToBytes81, bArrHexStringToBytes81.length);
                break;
            case -1558642688:
                if (bArr.length > 0) {
                    this.mResult = this.mSerialCommands.setDemuraSwitchStatus(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes82 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes82;
                this.mFacFunction.writeToExternalControl(-1558642688, bArrHexStringToBytes82, bArrHexStringToBytes82.length);
                break;
            case -1558577152:
                boolean demuraTconStatus = this.mSerialCommands.setDemuraTconStatus(0);
                this.mResult = demuraTconStatus;
                byte[] bArrHexStringToBytes83 = HexData.hexStringToBytes(HexResult(demuraTconStatus));
                this.mData = bArrHexStringToBytes83;
                this.mFacFunction.writeToExternalControl(-1558577152, bArrHexStringToBytes83, bArrHexStringToBytes83.length);
                break;
            case -1558577151:
                if (bArr.length > 0) {
                    this.mResult = this.mSerialCommands.setDemuraSPIStatus(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes84 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes84;
                this.mFacFunction.writeToExternalControl(-1558577151, bArrHexStringToBytes84, bArrHexStringToBytes84.length);
                break;
            case -1558577150:
                boolean demuraDataErase = this.mSerialCommands.setDemuraDataErase();
                this.mResult = demuraDataErase;
                byte[] bArrHexStringToBytes85 = HexData.hexStringToBytes(HexResult(demuraDataErase));
                this.mData = bArrHexStringToBytes85;
                this.mFacFunction.writeToExternalControl(-1558577150, bArrHexStringToBytes85, bArrHexStringToBytes85.length);
                break;
            case -1558577149:
                boolean demuraDataUpdate = this.mSerialCommands.setDemuraDataUpdate();
                this.mResult = demuraDataUpdate;
                byte[] bArrHexStringToBytes86 = HexData.hexStringToBytes(HexResult(demuraDataUpdate));
                this.mData = bArrHexStringToBytes86;
                this.mFacFunction.writeToExternalControl(-1558577149, bArrHexStringToBytes86, bArrHexStringToBytes86.length);
                break;
            case -1558511616:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setDemuraDebugStatus(HexData.decodeToInteger(bArr[0]));
                    Logger.d("setDemuraStatus : data = " + bArr);
                }
                byte[] bArrHexStringToBytes87 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes87;
                this.mFacFunction.writeToExternalControl(-1558511616, bArrHexStringToBytes87, bArrHexStringToBytes87.length);
                break;
            case -1543372798:
                if (bArr != null && bArr.length == 1) {
                    this.mResult = this.mSerialCommands.setForce120HzEnable(HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes88 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes88;
                this.mFacFunction.writeToExternalControl(-1543372798, bArrHexStringToBytes88, bArrHexStringToBytes88.length);
                break;
            case -1509883904:
                boolean zWriteWidevineKey = this.mSerialCommands.writeWidevineKey();
                this.mResult = zWriteWidevineKey;
                byte[] bArrHexStringToBytes89 = HexData.hexStringToBytes(HexResult(zWriteWidevineKey));
                this.mData = bArrHexStringToBytes89;
                this.mFacFunction.writeToExternalControl(-1509883904, bArrHexStringToBytes89, bArrHexStringToBytes89.length);
                break;
            case -1509818368:
                boolean zWritePlayReadyKey = this.mSerialCommands.writePlayReadyKey();
                this.mResult = zWritePlayReadyKey;
                byte[] bArrHexStringToBytes90 = HexData.hexStringToBytes(HexResult(zWritePlayReadyKey));
                this.mData = bArrHexStringToBytes90;
                this.mFacFunction.writeToExternalControl(-1509818368, bArrHexStringToBytes90, bArrHexStringToBytes90.length);
                break;
            case -1509752832:
                boolean zWriteHdcp14Key = this.mSerialCommands.writeHdcp14Key();
                this.mResult = zWriteHdcp14Key;
                byte[] bArrHexStringToBytes91 = HexData.hexStringToBytes(HexResult(zWriteHdcp14Key));
                this.mData = bArrHexStringToBytes91;
                this.mFacFunction.writeToExternalControl(-1509752832, bArrHexStringToBytes91, bArrHexStringToBytes91.length);
                break;
            case -1509687296:
                boolean zWriteHdcp22Key = this.mSerialCommands.writeHdcp22Key();
                this.mResult = zWriteHdcp22Key;
                byte[] bArrHexStringToBytes92 = HexData.hexStringToBytes(HexResult(zWriteHdcp22Key));
                this.mData = bArrHexStringToBytes92;
                this.mFacFunction.writeToExternalControl(-1509687296, bArrHexStringToBytes92, bArrHexStringToBytes92.length);
                break;
            case -1509621760:
                byte[] bArrHexStringToBytes93 = HexData.hexStringToBytes(HexResult(true));
                this.mData = bArrHexStringToBytes93;
                this.mFacFunction.writeToExternalControl(-1509621760, bArrHexStringToBytes93, bArrHexStringToBytes93.length);
                break;
            case -1509556224:
                boolean mGKKeyFromSerial = this.mSerialCommands.setMGKKeyFromSerial();
                this.mResult = mGKKeyFromSerial;
                byte[] bArrHexStringToBytes94 = HexData.hexStringToBytes(HexResult(mGKKeyFromSerial));
                this.mData = bArrHexStringToBytes94;
                this.mFacFunction.writeToExternalControl(-1509556224, bArrHexStringToBytes94, bArrHexStringToBytes94.length);
                break;
            case -1509031936:
                boolean zWriteDTCPIPKey = this.mSerialCommands.writeDTCPIPKey();
                this.mResult = zWriteDTCPIPKey;
                byte[] bArrHexStringToBytes95 = HexData.hexStringToBytes(HexResult(zWriteDTCPIPKey));
                this.mData = bArrHexStringToBytes95;
                this.mFacFunction.writeToExternalControl(-1509031936, bArrHexStringToBytes95, bArrHexStringToBytes95.length);
                break;
            case -1508966400:
                boolean zWriteMarlinKey = this.mSerialCommands.writeMarlinKey();
                this.mResult = zWriteMarlinKey;
                byte[] bArrHexStringToBytes96 = HexData.hexStringToBytes(HexResult(zWriteMarlinKey));
                this.mData = bArrHexStringToBytes96;
                this.mFacFunction.writeToExternalControl(-1508966400, bArrHexStringToBytes96, bArrHexStringToBytes96.length);
                break;
            case -1508835328:
                boolean zWriteSQVCKey = this.mSerialCommands.writeSQVCKey();
                this.mResult = zWriteSQVCKey;
                byte[] bArrHexStringToBytes97 = HexData.hexStringToBytes(HexResult(zWriteSQVCKey));
                this.mData = bArrHexStringToBytes97;
                this.mFacFunction.writeToExternalControl(-1508835328, bArrHexStringToBytes97, bArrHexStringToBytes97.length);
                break;
            case -1508245504:
                boolean zWritePVRKey = this.mSerialCommands.writePVRKey();
                this.mResult = zWritePVRKey;
                byte[] bArrHexStringToBytes98 = HexData.hexStringToBytes(HexResult(zWritePVRKey));
                this.mData = bArrHexStringToBytes98;
                this.mFacFunction.writeToExternalControl(-1508245504, bArrHexStringToBytes98, bArrHexStringToBytes98.length);
                break;
            case -1508114432:
                boolean zWriteQDAKey = this.mSerialCommands.writeQDAKey();
                this.mResult = zWriteQDAKey;
                byte[] bArrHexStringToBytes99 = HexData.hexStringToBytes(HexResult(zWriteQDAKey));
                this.mData = bArrHexStringToBytes99;
                this.mFacFunction.writeToExternalControl(-1508114432, bArrHexStringToBytes99, bArrHexStringToBytes99.length);
                break;
            case -1308622848:
                byte[] uSBConnectionNumber = this.mSerialCommands.getUSBConnectionNumber();
                this.mData = uSBConnectionNumber;
                this.mFacFunction.writeToExternalControl(-1308622848, uSBConnectionNumber, uSBConnectionNumber.length);
                break;
            case -1308557312:
                byte[] macAddress2 = this.mSerialCommands.getMacAddress();
                this.mData = macAddress2;
                this.mFacFunction.writeToExternalControl(-1308557312, macAddress2, macAddress2.length);
                break;
            case -1308491776:
                byte[] lineIPAddress3 = this.mSerialCommands.getLineIPAddress();
                this.mData = lineIPAddress3;
                this.mFacFunction.writeToExternalControl(-1308491776, lineIPAddress3, lineIPAddress3.length);
                break;
            case -1308426240:
                byte[] wirelessIP = this.mSerialCommands.getWirelessIP();
                this.mData = wirelessIP;
                this.mFacFunction.writeToExternalControl(-1308426240, wirelessIP, wirelessIP.length);
                break;
            case -1308360704:
                byte[] bArr3 = this.mDataSSID;
                if (bArr3 != null) {
                    this.mData = bArr3;
                } else {
                    this.mData = this.mSerialCommands.getWirelessSSID();
                }
                byte[] bArr4 = this.mData;
                this.mFacFunction.writeToExternalControl(-1308360704, bArr4, bArr4.length);
                break;
            case -1308295168:
                byte[] device23bitNum2 = this.mSerialCommands.getDevice23bitNum();
                this.mData = device23bitNum2;
                this.mFacFunction.writeToExternalControl(-1308295168, this.mData, device23bitNum2 != null ? device23bitNum2.length : 0);
                break;
            case -1308295167:
                byte[] originDevice23bitNum = this.mSerialCommands.getOriginDevice23bitNum();
                this.mData = originDevice23bitNum;
                this.mFacFunction.writeToExternalControl(-1308295167, this.mData, originDevice23bitNum != null ? originDevice23bitNum.length : 0);
                break;
            case -1308229632:
                if (this.mDataCICard != null && !HexData.stringToHexString("0").equals(HexData.bytesToHexString(this.mDataCICard))) {
                    this.mData = this.mDataCICard;
                } else {
                    this.mData = this.mSerialCommands.getCICardInfo();
                }
                byte[] bArr5 = this.mData;
                this.mFacFunction.writeToExternalControl(-1308229632, this.mData, bArr5 != null ? bArr5.length : 0);
                break;
            case -1307443200:
                Logger.i("get aging time");
                byte[] bArr6 = new byte[4];
                for (int i3 = 0; i3 < 4; i3++) {
                    bArr6[i3] = 0;
                }
                this.mData = this.mSerialCommands.getAgingTime();
                int i4 = 0;
                while (true) {
                    byte[] bArr7 = this.mData;
                    if (i4 >= bArr7.length) {
                        while (true) {
                            byte[] bArr8 = this.mData;
                            if (iDecodeToInteger < bArr8.length) {
                                bArr6[3 - iDecodeToInteger] = bArr8[iDecodeToInteger];
                                Logger.i("dData " + iDecodeToInteger + "is" + ((int) bArr6[iDecodeToInteger]));
                                iDecodeToInteger++;
                            } else {
                                this.mFacFunction.writeToExternalControl(i, bArr6, 4);
                            }
                        }
                    } else {
                        byte b = bArr7[bArr7.length - 1];
                        bArr7[bArr7.length - 1] = bArr7[i4];
                        bArr7[i4] = b;
                        i4++;
                    }
                    break;
                }
                break;
            case -1307377664:
                byte[] functionNumber2 = this.mSerialCommands.getFunctionNumber();
                this.mData = functionNumber2;
                this.mFacFunction.writeToExternalControl(-1307377664, this.mData, functionNumber2 != null ? functionNumber2.length : 0);
                break;
            case -1307312128:
                this.mData = this.mSerialCommands.getSoftWareVerSion();
                Logger.d("software version is" + this.mData);
                byte[] bArr9 = this.mData;
                this.mFacFunction.writeToExternalControl(i, bArr9, bArr9.length);
                break;
            case -1307312127:
                this.mData = this.mSerialCommands.getFRCVerSion();
                Logger.d("FRC version is" + this.mData);
                byte[] bArr10 = this.mData;
                this.mFacFunction.writeToExternalControl(i, this.mData, bArr10 != null ? bArr10.length : 0);
                break;
            case -1307312126:
                this.mData = this.mSerialCommands.getTconVersion();
                Logger.d("Tcon version is" + this.mData);
                byte[] bArr11 = this.mData;
                this.mFacFunction.writeToExternalControl(i, this.mData, bArr11 != null ? bArr11.length : 0);
                break;
            case -1307312125:
                this.mData = this.mSerialCommands.getBluVersion();
                Logger.d("Blu version is" + this.mData);
                byte[] bArr12 = this.mData;
                this.mFacFunction.writeToExternalControl(i, this.mData, bArr12 != null ? bArr12.length : 0);
                break;
            case -1307246592:
                byte[] country2 = this.mSerialCommands.getCountry();
                this.mData = country2;
                this.mFacFunction.writeToExternalControl(-1307246592, this.mData, country2 != null ? country2.length : 0);
                break;
            case -1307181056:
                byte[] bluetoothMacAddr = this.mSerialCommands.getBluetoothMacAddr();
                this.mData = bluetoothMacAddr;
                this.mFacFunction.writeToExternalControl(-1307181056, this.mData, bluetoothMacAddr != null ? bluetoothMacAddr.length : 0);
                break;
            case -1307115520:
                byte[] logo2 = this.mSerialCommands.getLogo();
                this.mData = logo2;
                this.mFacFunction.writeToExternalControl(-1307115520, this.mData, logo2 != null ? logo2.length : 0);
                break;
            case -1307049984:
                byte[] language2 = this.mSerialCommands.getLanguage();
                this.mData = language2;
                this.mFacFunction.writeToExternalControl(-1307049984, this.mData, language2 != null ? language2.length : 0);
                break;
            case -1306984448:
                byte[] chipNumber2 = this.mSerialCommands.getChipNumber();
                this.mData = chipNumber2;
                this.mFacFunction.writeToExternalControl(-1306984448, this.mData, chipNumber2 != null ? chipNumber2.length : 0);
                break;
            case -1306853376:
                byte[] chipID = this.mSerialCommands.getChipID();
                this.mData = chipID;
                this.mFacFunction.writeToExternalControl(-1306853376, chipID, chipID.length);
                break;
            case -1306787840:
                byte[] hdcp2xKey = this.mSerialCommands.getHdcp2xKey();
                this.mData = hdcp2xKey;
                this.mFacFunction.writeToExternalControl(-1306787840, this.mData, hdcp2xKey != null ? hdcp2xKey.length : 0);
                break;
            case -1306656768:
                if (FacPropertyDemo.HIFAC_VCOM_SUPPORT > 0) {
                    this.mData = this.mSerialCommands.getVcom();
                } else {
                    Logger.d("vcom not supported, change to 0***");
                    this.mData = HexData.hexStringToBytes("00");
                }
                byte[] bArr13 = this.mData;
                this.mFacFunction.writeToExternalControl(-1306656768, bArr13, bArr13.length);
                break;
            case -1306591232:
                byte[] hdcp1xKey = this.mSerialCommands.getHdcp1xKey();
                this.mData = hdcp1xKey;
                this.mFacFunction.writeToExternalControl(-1306591232, this.mData, hdcp1xKey != null ? hdcp1xKey.length : 0);
                break;
            case -1306460160:
                byte[] bArrScanWifiBySSID = this.mSerialCommands.scanWifiBySSID(bArr);
                this.mData = bArrScanWifiBySSID;
                this.mFacFunction.writeToExternalControl(-1306460160, this.mData, bArrScanWifiBySSID != null ? bArrScanWifiBySSID.length : 0);
                break;
            case -1306394624:
                this.mValue = this.mSerialCommands.scanBlutoothByName(bArr);
                break;
            case -1306263552:
                byte[] netflixKey = this.mSerialCommands.getNetflixKey();
                this.mData = netflixKey;
                this.mFacFunction.writeToExternalControl(-1306263552, this.mData, netflixKey != null ? netflixKey.length : 0);
                break;
            case -1306198016:
                byte[] wideVineKey = this.mSerialCommands.getWideVineKey();
                this.mData = wideVineKey;
                this.mFacFunction.writeToExternalControl(-1306198016, this.mData, wideVineKey != null ? wideVineKey.length : 0);
                break;
            case -1306066944:
                byte[] clientType2 = this.mSerialCommands.getClientType();
                this.mData = clientType2;
                this.mFacFunction.writeToExternalControl(-1306066944, this.mData, clientType2 != null ? clientType2.length : 0);
                break;
            case -1306001408:
                if (FacPropertyDemo.HIFAC_CHINA_DRM_KEY_SUPPORT) {
                    this.mData = this.mSerialCommands.getChinaDrmKey();
                } else {
                    Logger.d("china drm not supported, change to 0***");
                    this.mData = HexData.hexStringToBytes("00");
                }
                byte[] bArr14 = this.mData;
                this.mFacFunction.writeToExternalControl(-1306001408, this.mData, bArr14 != null ? bArr14.length : 0);
                break;
            case -1305935872:
                int lightSensorValue = this.mSerialCommands.getLightSensorValue(HexData.decodeToInteger(bArr[0]));
                this.mValue = lightSensorValue;
                String strIntegerToHexString2 = HexData.integerToHexString(lightSensorValue);
                if (strIntegerToHexString2.length() < 2) {
                    strIntegerToHexString2 = "0" + strIntegerToHexString2;
                }
                byte[] bArrHexStringToBytes100 = HexData.hexStringToBytes2(strIntegerToHexString2);
                this.mData = bArrHexStringToBytes100;
                this.mFacFunction.writeToExternalControl(-1305935872, bArrHexStringToBytes100, bArrHexStringToBytes100.length);
                break;
            case -1305870336:
                byte[] dtcpIpKey = this.mSerialCommands.getDtcpIpKey();
                this.mData = dtcpIpKey;
                this.mFacFunction.writeToExternalControl(-1305870336, this.mData, dtcpIpKey != null ? dtcpIpKey.length : 0);
                break;
            case -1305804800:
                byte[] marlinKey = this.mSerialCommands.getMarlinKey();
                this.mData = marlinKey;
                this.mFacFunction.writeToExternalControl(-1305804800, this.mData, marlinKey != null ? marlinKey.length : 0);
                break;
            case -1305608192:
                byte[] wifiMacAddress = this.mSerialCommands.getWifiMacAddress();
                this.mData = wifiMacAddress;
                this.mFacFunction.writeToExternalControl(-1305608192, wifiMacAddress, wifiMacAddress.length);
                break;
            case -1305542656:
                byte[] chipVersion = this.mSerialCommands.getChipVersion();
                this.mData = chipVersion;
                this.mFacFunction.writeToExternalControl(-1305542656, this.mData, chipVersion != null ? chipVersion.length : 0);
                break;
            case -1305411584:
                byte[] projectId = this.mSerialCommands.getProjectId();
                this.mData = projectId;
                this.mFacFunction.writeToExternalControl(-1305411584, this.mData, projectId != null ? projectId.length : 0);
                break;
            case -1305280512:
                byte[] seeQVaultKey = this.mSerialCommands.getSeeQVaultKey();
                this.mData = seeQVaultKey;
                this.mFacFunction.writeToExternalControl(-1305280512, this.mData, seeQVaultKey != null ? seeQVaultKey.length : 0);
                break;
            case -1305018368:
                byte[] playReadyKey = this.mSerialCommands.getPlayReadyKey();
                this.mData = playReadyKey;
                this.mFacFunction.writeToExternalControl(-1305018368, this.mData, playReadyKey != null ? playReadyKey.length : 0);
                break;
            case -1304952832:
                this.mData = this.mSerialCommands.getACASId();
                Logger.d("getACASId is" + this.mData);
                byte[] bArr15 = this.mData;
                this.mFacFunction.writeToExternalControl(i, bArr15, bArr15.length);
                break;
            case -1304887296:
                this.mData = this.mSerialCommands.getHDDSize();
                Logger.d("getHDD size is" + this.mData);
                byte[] bArr16 = this.mData;
                this.mFacFunction.writeToExternalControl(i, bArr16, bArr16.length);
                break;
            case -1304756224:
                byte[] orderCode = this.mSerialCommands.getOrderCode();
                this.mData = orderCode;
                this.mFacFunction.writeToExternalControl(-1304756224, this.mData, orderCode != null ? orderCode.length : 0);
                break;
            case -1291845632:
                int rDrv = this.mSerialCommands.getRDrv();
                this.mValue = rDrv;
                String hexString = Integer.toHexString(rDrv);
                if (hexString.length() % 2 != 0) {
                    hexString = "0" + hexString;
                }
                byte[] bArrHexStringToBytes101 = HexData.hexStringToBytes(hexString);
                this.mData = bArrHexStringToBytes101;
                this.mFacFunction.writeToExternalControl(-1291845632, bArrHexStringToBytes101, bArrHexStringToBytes101.length);
                break;
            case -1291780096:
                int gDrv = this.mSerialCommands.getGDrv();
                this.mValue = gDrv;
                String hexString2 = Integer.toHexString(gDrv);
                if (hexString2.length() % 2 != 0) {
                    hexString2 = "0" + hexString2;
                }
                byte[] bArrHexStringToBytes102 = HexData.hexStringToBytes(hexString2);
                this.mData = bArrHexStringToBytes102;
                this.mFacFunction.writeToExternalControl(-1291780096, bArrHexStringToBytes102, bArrHexStringToBytes102.length);
                break;
            case -1291714560:
                int bDrv = this.mSerialCommands.getBDrv();
                this.mValue = bDrv;
                String hexString3 = Integer.toHexString(bDrv);
                if (hexString3.length() % 2 != 0) {
                    hexString3 = "0" + hexString3;
                }
                byte[] bArrHexStringToBytes103 = HexData.hexStringToBytes(hexString3);
                this.mData = bArrHexStringToBytes103;
                this.mFacFunction.writeToExternalControl(-1291714560, bArrHexStringToBytes103, bArrHexStringToBytes103.length);
                break;
            case -1291649024:
                int rCut = this.mSerialCommands.getRCut();
                this.mValue = rCut;
                String hexString4 = Integer.toHexString(rCut);
                if (hexString4.length() % 2 != 0) {
                    hexString4 = "0" + hexString4;
                }
                byte[] bArrHexStringToBytes104 = HexData.hexStringToBytes(hexString4);
                this.mData = bArrHexStringToBytes104;
                this.mFacFunction.writeToExternalControl(-1291649024, bArrHexStringToBytes104, bArrHexStringToBytes104.length);
                break;
            case -1291583488:
                int gCut = this.mSerialCommands.getGCut();
                this.mValue = gCut;
                String hexString5 = Integer.toHexString(gCut);
                if (hexString5.length() % 2 != 0) {
                    hexString5 = "0" + hexString5;
                }
                byte[] bArrHexStringToBytes105 = HexData.hexStringToBytes(hexString5);
                this.mData = bArrHexStringToBytes105;
                this.mFacFunction.writeToExternalControl(-1291583488, bArrHexStringToBytes105, bArrHexStringToBytes105.length);
                break;
            case -1291517952:
                int bCut2 = this.mSerialCommands.getBCut();
                this.mValue = bCut2;
                String hexString6 = Integer.toHexString(bCut2);
                if (hexString6.length() % 2 != 0) {
                    hexString6 = "0" + hexString6;
                }
                byte[] bArrHexStringToBytes106 = HexData.hexStringToBytes(hexString6);
                this.mData = bArrHexStringToBytes106;
                this.mFacFunction.writeToExternalControl(-1291517952, bArrHexStringToBytes106, bArrHexStringToBytes106.length);
                break;
            case -536870912:
                if (bArr.length > 1) {
                    Logger.d("current package index is " + ((int) bArr[0]));
                    this.mSerialCommands.initSerialFile();
                    this.mSerialCommands.saveConfigFile(bArr);
                }
                break;
            case -536412160:
                this.mResult = this.mSerialCommands.startMicTest(this);
                Logger.d("startMicTest : mResult = " + this.mResult);
                byte[] bArrHexStringToBytes107 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes107;
                this.mFacFunction.writeToExternalControl(-536412160, bArrHexStringToBytes107, bArrHexStringToBytes107.length);
                break;
            case -536412159:
                int micTest = this.mSerialCommands.getMicTest();
                this.mValue = micTest;
                String strIntegerToHexString3 = HexData.integerToHexString(micTest);
                if (strIntegerToHexString3.length() < 2) {
                    strIntegerToHexString3 = "0" + strIntegerToHexString3;
                }
                byte[] bArrHexStringToBytes108 = HexData.hexStringToBytes2(strIntegerToHexString3);
                this.mData = bArrHexStringToBytes108;
                this.mFacFunction.writeToExternalControl(-536412159, bArrHexStringToBytes108, bArrHexStringToBytes108.length);
                break;
            case -536346624:
                this.mSerialCommands.startBMWTest(this, false);
                this.mResult = true;
                byte[] bArrHexStringToBytes109 = HexData.hexStringToBytes(HexResult(true));
                this.mData = bArrHexStringToBytes109;
                this.mFacFunction.writeToExternalControl(-536346624, bArrHexStringToBytes109, bArrHexStringToBytes109.length);
                break;
            case -536346623:
                byte[] bMWResult = this.mSerialCommands.getBMWResult();
                this.mData = bMWResult;
                this.mFacFunction.writeToExternalControl(-536346623, bMWResult, bMWResult.length);
                break;
            case -535887872:
                boolean serviceTag = this.mSerialCommands.setServiceTag(bArr);
                this.mResult = serviceTag;
                byte[] bArrHexStringToBytes110 = HexData.hexStringToBytes(HexResult(serviceTag));
                this.mData = bArrHexStringToBytes110;
                this.mFacFunction.writeToExternalControl(-535887872, bArrHexStringToBytes110, bArrHexStringToBytes110.length);
                break;
            case -535887871:
                byte[] serviceTag2 = this.mSerialCommands.getServiceTag();
                this.mData = serviceTag2;
                this.mFacFunction.writeToExternalControl(-535887871, this.mData, serviceTag2 != null ? serviceTag2.length : 0);
                break;
            case -251658240:
                if (bArr == null || bArr.length != 1) {
                    runningTime = "";
                } else {
                    runningTime = this.mSerialCommands.getRunningTime(HexData.decodeToInteger(bArr[0]));
                    Logger.d("getRunningTime is : " + runningTime);
                }
                String hexString7 = Integer.toHexString(Integer.parseInt(runningTime));
                Logger.d("gethexStringTime is : " + hexString7);
                Logger.d("gethexStringTime.length is : " + hexString7.length());
                while (iDecodeToInteger < 8) {
                    if (8 - iDecodeToInteger <= hexString7.length()) {
                        str = str + hexString7;
                        byte[] bArrHexStringToBytes111 = HexData.hexStringToBytes(str);
                        this.mData = bArrHexStringToBytes111;
                        int length3 = bArrHexStringToBytes111.length;
                        Logger.d("getRunningTime returnLength : " + length3);
                        this.mFacFunction.writeToExternalControl(-251658240, this.mData, length3);
                    } else {
                        str = "0" + str;
                        iDecodeToInteger++;
                    }
                    break;
                }
                byte[] bArrHexStringToBytes112 = HexData.hexStringToBytes(str);
                this.mData = bArrHexStringToBytes112;
                int length4 = bArrHexStringToBytes112.length;
                Logger.d("getRunningTime returnLength : " + length4);
                this.mFacFunction.writeToExternalControl(-251658240, this.mData, length4);
                break;
            case -251658239:
                boolean zClearOMRunningTime = this.mSerialCommands.clearOMRunningTime();
                this.mResult = zClearOMRunningTime;
                byte[] bArrHexStringToBytes113 = HexData.hexStringToBytes(HexResult(zClearOMRunningTime));
                this.mData = bArrHexStringToBytes113;
                this.mFacFunction.writeToExternalControl(-251658239, bArrHexStringToBytes113, bArrHexStringToBytes113.length);
                break;
            case -251592704:
                if (bArr != null && bArr.length == 1) {
                    iDecodeToInteger = this.mSerialCommands.getFansSpeed(HexData.decodeToInteger(bArr[0]));
                    Logger.d("getFansSpeed is : " + iDecodeToInteger);
                }
                String hexString8 = Integer.toHexString(iDecodeToInteger);
                int length5 = hexString8.length();
                if (length5 == 1) {
                    hexString8 = "000" + hexString8;
                } else if (length5 == 2) {
                    hexString8 = "00" + hexString8;
                } else if (length5 == 3) {
                    hexString8 = "0" + hexString8;
                }
                byte[] bArrHexStringToBytes114 = HexData.hexStringToBytes(hexString8);
                this.mData = bArrHexStringToBytes114;
                this.mFacFunction.writeToExternalControl(-251592704, bArrHexStringToBytes114, bArrHexStringToBytes114.length);
                break;
            case -251527168:
                if (bArr != null && bArr.length == 1) {
                    iDecodeToInteger = this.mSerialCommands.getTemps(HexData.decodeToInteger(bArr[0]));
                    Logger.d("getTemps is : " + iDecodeToInteger);
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream.write(iDecodeToInteger);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    dataOutputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                this.mFacFunction.writeToExternalControl(-251527168, byteArray, byteArray.length);
                break;
            case -251461632:
                if (bArr == null || bArr.length != 1) {
                    lightSensor = 0;
                } else {
                    lightSensor = this.mSerialCommands.getLightSensor(HexData.decodeToInteger(bArr[0]));
                    Logger.d("getRunningTime is : " + lightSensor);
                }
                byte[] bArr17 = new byte[4];
                while (iDecodeToInteger < 4) {
                    bArr17[iDecodeToInteger] = (byte) (lightSensor >>> (24 - (iDecodeToInteger * 8)));
                    iDecodeToInteger++;
                }
                this.mFacFunction.writeToExternalControl(-251461632, bArr17, 4);
                break;
            case -251396096:
                if (bArr != null && bArr.length == 1) {
                    versions = this.mSerialCommands.getVersions(HexData.decodeToInteger(bArr[0]));
                    Logger.d("getVersions is : " + versions);
                }
                byte[] bArrHexStringToBytes115 = HexData.hexStringToBytes(HexData.stringToHexString(versions != null ? versions : ""));
                this.mData = bArrHexStringToBytes115;
                this.mFacFunction.writeToExternalControl(-251396096, this.mData, bArrHexStringToBytes115 != null ? bArrHexStringToBytes115.length : 0);
                break;
            case -217513982:
                if (bArr.length > 1) {
                    this.mResult = this.mSerialCommands.setGammaEnable(HexData.decodeToInteger(bArr[1]), HexData.decodeToInteger(bArr[0]));
                }
                byte[] bArrHexStringToBytes116 = HexData.hexStringToBytes(HexResult(this.mResult));
                this.mData = bArrHexStringToBytes116;
                this.mFacFunction.writeToExternalControl(-217513982, bArrHexStringToBytes116, bArrHexStringToBytes116.length);
                break;
            default:
                Logger.d("switch to default solution.");
                byte[] bArrHexStringToBytes117 = HexData.hexStringToBytes("03");
                this.mData = bArrHexStringToBytes117;
                this.mFacFunction.writeToExternalControl(i, bArrHexStringToBytes117, bArrHexStringToBytes117.length);
                break;
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        Logger.d("SerialFacService onDestroy");
        SerialStatusReceiver.mServiceFlag = false;
        FactorySerialCallbackHandler factorySerialCallbackHandler = this.mFactorySerialCallbackHandler;
        if (factorySerialCallbackHandler != null) {
            factorySerialCallbackHandler.removeListener();
            this.mFactorySerialCallbackHandler = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override // com.hisense.uifac.util.IRootMenuUiListener
    public void showToast(String str) {
        this.mMicHandler.sendMessage(this.mMicHandler.obtainMessage(0, str));
    }

    @Override // com.hisense.uifac.util.IRootMenuUiListener
    public void setMicTestResult(String str) {
        this.mMicHandler.sendMessage(this.mMicHandler.obtainMessage(0, str));
    }

    public void displayHisToast(String str) {
        if (this.mToastView == null) {
            this.mToastView = new Toast(this.mContext);
        }
        Log.i(this.tag, "displayHisToast str = " + str);
        TextView textView = new TextView(this.mContext);
        textView.setBackgroundColor(-7829368);
        textView.setTextColor(-16776961);
        textView.setPadding(10, 10, 10, 10);
        textView.setText(str);
        textView.setTextSize(32.0f);
        this.mToastView.setView(textView);
        if (this.mContext.getResources().getString(R.string.remote_pair_success).equalsIgnoreCase(str)) {
            this.mToastView.setGravity(80, 0, 0);
        } else {
            this.mToastView.setGravity(17, 0, 0);
        }
        this.mToastView.show();
    }
}
