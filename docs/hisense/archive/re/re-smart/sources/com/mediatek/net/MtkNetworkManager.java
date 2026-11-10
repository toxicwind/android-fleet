package com.mediatek.net;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.MtkTvConfig;
import com.mediatek.twoworlds.tv.MtkTvNetwork;

/* JADX INFO: loaded from: classes2.dex */
public class MtkNetworkManager {
    public static final String TAG = "MtkNetworkManager";
    public static MtkNetworkManager mInstance;
    public MtkTvConfig mMtkTvConfig;
    public MtkNetworkNative mNative;
    public MtkTvNetwork mNetwork;

    public MtkNetworkManager() {
        this.mNetwork = null;
        this.mMtkTvConfig = null;
        this.mNative = null;
        Log.d(TAG, "Create MtkNetworkManager");
        this.mNetwork = MtkTvNetwork.getInstance();
        this.mMtkTvConfig = MtkTvConfig.getInstance();
        this.mNative = new MtkNetworkNative("wlan0");
    }

    public static MtkNetworkManager getInstance() {
        if (mInstance == null) {
            mInstance = new MtkNetworkManager();
        }
        return mInstance;
    }

    public boolean enableWolAndWoWL(boolean z) {
        boolean z2 = setEnableWoWL(z) && setEnableWol(z);
        a.a("enableWolAndWoWL api result is ", z2, TAG);
        return z2;
    }

    public boolean isEnableWol() {
        boolean ethernetWolCtl = this.mNetwork.getEthernetWolCtl();
        a.a("isEnableWol api result is ", ethernetWolCtl, TAG);
        return ethernetWolCtl;
    }

    public boolean isEnanbleWoWL() {
        boolean wifiWolCtl = this.mNetwork.getWifiWolCtl();
        a.a("isEnanbleWoWL api result is ", wifiWolCtl, TAG);
        return wifiWolCtl;
    }

    public boolean isWifiCSAEnable() {
        int configValue = this.mMtkTvConfig.getConfigValue(1, "g_misc__wifi_csa");
        a.b("isWifiCSAEnable api result is ", configValue, TAG);
        return configValue == 1;
    }

    public boolean isWifiPsAwakeEnable() {
        int configValue = this.mMtkTvConfig.getConfigValue(1, "g_misc__ps_awake");
        a.b("isWifiPsAwakeEnable api result is ", configValue, TAG);
        return configValue == 1;
    }

    public boolean isWoPacketEnable() {
        int configValue = this.mMtkTvConfig.getConfigValue(1, "g_network__wake_on_packet");
        a.b("isWoPacketEnable api getTvConfig value is ", configValue, TAG);
        return configValue == 1;
    }

    public boolean isWolAndWowlEnable() {
        boolean z = isEnanbleWoWL() && isEnableWol();
        a.a("isWolAndWowlEnable api result is ", z, TAG);
        return z;
    }

    public boolean setEnableWifiCSA(boolean z) {
        MtkNetworkNative mtkNetworkNative = this.mNative;
        boolean enableWifiCSANative = MtkNetworkNative.setEnableWifiCSANative(z);
        a.a("setEnableWifiCSA api driver result is ", enableWifiCSANative, TAG);
        if (enableWifiCSANative) {
            enableWifiCSANative = this.mMtkTvConfig.setConfigValue(1, "g_misc__wifi_csa", z ? 1 : 0, 0) == 0;
            a.a("setEnableWifiCSA api TvConfig result is ", enableWifiCSANative, TAG);
        }
        return enableWifiCSANative;
    }

    public boolean setEnableWifiPsAwake(boolean z) {
        MtkNetworkNative mtkNetworkNative = this.mNative;
        boolean enableWifiPsAwakeNative = MtkNetworkNative.setEnableWifiPsAwakeNative(z);
        a.a("setEnableWifiPsAwake api driver result is ", enableWifiPsAwakeNative, TAG);
        if (enableWifiPsAwakeNative) {
            enableWifiPsAwakeNative = this.mMtkTvConfig.setConfigValue(1, "g_misc__ps_awake", z ? 1 : 0, 0) == 0;
            a.a("setEnableWifiPsAwake api TvConfig result is ", enableWifiPsAwakeNative, TAG);
        }
        return enableWifiPsAwakeNative;
    }

    public boolean setEnableWoPacket(boolean z) {
        MtkNetworkNative mtkNetworkNative = this.mNative;
        boolean woPacketNative = MtkNetworkNative.setWoPacketNative(z);
        a.a("setEnableWoPacket api driver result is ", woPacketNative, TAG);
        if (woPacketNative) {
            woPacketNative = this.mMtkTvConfig.setConfigValue(1, "g_network__wake_on_packet", z ? 1 : 0, 0) == 0;
            a.a("setEnableWoPacket api TvConfig result is ", woPacketNative, TAG);
        }
        return woPacketNative;
    }

    public boolean setEnableWoWL(boolean z) {
        MtkNetworkNative mtkNetworkNative = this.mNative;
        boolean zWifiNativeSetWowl = MtkNetworkNative.wifiNativeSetWowl(z);
        a.a("setEnableWoWL api driver result is ", zWifiNativeSetWowl, TAG);
        if (!zWifiNativeSetWowl) {
            return zWifiNativeSetWowl;
        }
        boolean wifiWolCtl = this.mNetwork.setWifiWolCtl(z);
        a.a("setEnableWoWL api TvConfig result is ", wifiWolCtl, TAG);
        return wifiWolCtl;
    }

    public boolean setEnableWol(boolean z) {
        boolean ethernetWolCtl = this.mNetwork.setEthernetWolCtl(z);
        a.a("setEnableWol api TvConfig result is ", ethernetWolCtl, TAG);
        return ethernetWolCtl;
    }

    public boolean setWifiPsAwakeInterval(int i) {
        MtkNetworkNative mtkNetworkNative = this.mNative;
        boolean wifiPsAwakeIntervalNative = MtkNetworkNative.setWifiPsAwakeIntervalNative(i);
        a.a("setWifiPsAwakeInterval api result is ", wifiPsAwakeIntervalNative, TAG);
        return wifiPsAwakeIntervalNative;
    }
}
