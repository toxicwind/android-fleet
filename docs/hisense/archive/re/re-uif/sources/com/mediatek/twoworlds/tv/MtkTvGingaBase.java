package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvGingaAppExtendedDataBase;
import com.mediatek.twoworlds.tv.model.MtkTvGingaAppInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvGingaWebServiceClientInfoBase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvGingaBase {
    public static final String TAG = "TV_MtkTvGingaBase";

    public enum gingaDialogAckType {
        GINGA_DLG_ACK_DISMISS,
        GINGA_DLG_ACK_TRUST_CLIENT,
        GINGA_DLG_ACK_TIMEOUT,
        GINGA_DLG_ACK_INTERRUPTED,
        GINGA_DLG_ACK_CHOOSE_APP
    }

    public int startApplication(String str) {
        Log.d(TAG, "startApplication\n");
        return TVNativeWrapper.startApplication_native(str);
    }

    public int stopApplication(String str) {
        Log.d(TAG, "stopApplication\n");
        return TVNativeWrapper.stopApplication_native(str);
    }

    public int getApplicationInfo() {
        Log.d(TAG, "getApplicationInfo\n");
        return TVNativeWrapper.getApplicationInfo_native();
    }

    public int startGinga() {
        Log.d(TAG, "startGinga\n");
        return TVNativeWrapper.startGinga_native();
    }

    public int stopGinga() {
        Log.d(TAG, "stopGinga\n");
        return TVNativeWrapper.stopGinga_native();
    }

    public int warningStartCC(boolean z) {
        Log.d(TAG, "warningStartCC\n");
        return TVNativeWrapper.warningStartCC_native(z);
    }

    public int warningStartGingaApp(boolean z) {
        Log.d(TAG, "warningStartGingaApp\n");
        return TVNativeWrapper.warningStartGingaApp_native(z);
    }

    public List<MtkTvGingaAppInfoBase> getApplicationInfoList() {
        ArrayList arrayList = new ArrayList();
        Log.d(TAG, "getApplicationInfoList begin");
        int applicationInfoList_native = TVNativeWrapper.getApplicationInfoList_native(arrayList);
        Log.d(TAG, "getApplicationInfoList: count=" + arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Log.d(TAG, " getApplicationInfoList: AppId=" + ((MtkTvGingaAppInfoBase) arrayList.get(i)).getAppId() + ",AppName=" + ((MtkTvGingaAppInfoBase) arrayList.get(i)).getAppName() + ",IsRuning=" + ((MtkTvGingaAppInfoBase) arrayList.get(i)).isRunning() + ",IsPersitent=" + ((MtkTvGingaAppInfoBase) arrayList.get(i)).isPersistent());
        }
        Log.d(TAG, "- getApplicationInfoList. ret=" + applicationInfoList_native);
        return arrayList;
    }

    public boolean isGingaWindowResize() {
        Log.d(TAG, "isGingaWindowResize\n");
        return TVNativeWrapper.getGingaScreenModeisEnable_native();
    }

    public List<MtkTvGingaWebServiceClientInfoBase> getWebServiceClient() {
        ArrayList arrayList = new ArrayList();
        Log.d(TAG, "getWebServiceClient begin");
        int webServiceClient_native = TVNativeWrapper.getWebServiceClient_native(arrayList);
        Log.d(TAG, "getWebServiceClient: count=" + arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Log.d(TAG, " getWebServiceClient: WscName=" + ((MtkTvGingaWebServiceClientInfoBase) arrayList.get(i)).getWscName() + ",WscUUID=" + ((MtkTvGingaWebServiceClientInfoBase) arrayList.get(i)).getWscUUID() + ",WscState=" + ((MtkTvGingaWebServiceClientInfoBase) arrayList.get(i)).getWscState());
        }
        Log.d(TAG, "- getWebServiceClient. ret=" + webServiceClient_native);
        return arrayList;
    }

    public int markWebServiceTrustClient(String str, boolean z) {
        Log.d(TAG, "markWebServiceTrustClient sWscUUID= " + str + "isTrusted= " + z);
        return TVNativeWrapper.markWebServiceTrustClient_native(str, z);
    }

    public int removeWebServiceClient(String str) {
        Log.d(TAG, "removeWebServiceClient sWSCUUID= " + str);
        return TVNativeWrapper.removeWebServiceClient_native(str);
    }

    public int ackDialog(int i) {
        Log.d(TAG, "ackDialog, type= " + i);
        return TVNativeWrapper.ackDialog_native(i);
    }

    public int ackTrustDialog(boolean z) {
        Log.d(TAG, "ackTrustDialog bTrusted=: " + z);
        return TVNativeWrapper.ackTrustDialog_native(z);
    }

    public int ackChooseAppDialog(String str, String str2, boolean z) {
        Log.d(TAG, "ackChooseAppDialog targetAppName= " + str + "targetConName= " + str2 + "isNative= " + z);
        return TVNativeWrapper.ackChooseAppDialog_native(str, str2, z);
    }

    public int getAppExtendedData(String str, MtkTvGingaAppExtendedDataBase mtkTvGingaAppExtendedDataBase) {
        Log.d(TAG, "getAppExtendedData sAppID = " + str);
        int appExtendedData_native = TVNativeWrapper.getAppExtendedData_native(str, mtkTvGingaAppExtendedDataBase);
        if (mtkTvGingaAppExtendedDataBase != null) {
            Log.d(TAG, " getAppExtendedData: AppId=" + str + ",Descr=" + mtkTvGingaAppExtendedDataBase.getDescrInfo() + ",Ver=" + mtkTvGingaAppExtendedDataBase.getVerInfo() + ",IconPath=" + mtkTvGingaAppExtendedDataBase.getIconPath() + ",Size=" + mtkTvGingaAppExtendedDataBase.getSize());
        }
        return appExtendedData_native;
    }
}
