package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvGingaAppExtendedDataBase;
import com.mediatek.twoworlds.tv.model.MtkTvGingaAppInfoBase;
import com.mediatek.twoworlds.tv.model.MtkTvGingaWebServiceClientInfoBase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvGingaBase {
    public static final String TAG = "TV_MtkTvGingaBase";

    public enum gingaDialogAckType {
        GINGA_DLG_ACK_DISMISS,
        GINGA_DLG_ACK_TRUST_CLIENT,
        GINGA_DLG_ACK_TIMEOUT,
        GINGA_DLG_ACK_INTERRUPTED,
        GINGA_DLG_ACK_CHOOSE_APP
    }

    public int ackChooseAppDialog(String str, String str2, boolean z) {
        Log.d(TAG, "ackChooseAppDialog targetAppName= " + str + "targetConName= " + str2 + "isNative= " + z);
        return TVNativeWrapper.ackChooseAppDialog_native(str, str2, z);
    }

    public int ackDialog(int i) {
        Log.d(TAG, "ackDialog, type= " + i);
        return TVNativeWrapper.ackDialog_native(i);
    }

    public int ackTrustDialog(boolean z) {
        Log.d(TAG, "ackTrustDialog bTrusted=: " + z);
        return TVNativeWrapper.ackTrustDialog_native(z);
    }

    public int getAppExtendedData(String str, MtkTvGingaAppExtendedDataBase mtkTvGingaAppExtendedDataBase) {
        Log.d(TAG, "getAppExtendedData sAppID = " + str);
        int appExtendedData_native = TVNativeWrapper.getAppExtendedData_native(str, mtkTvGingaAppExtendedDataBase);
        if (mtkTvGingaAppExtendedDataBase != null) {
            StringBuilder sbB = a.b(" getAppExtendedData: AppId=", str, ",Descr=");
            sbB.append(mtkTvGingaAppExtendedDataBase.getDescrInfo());
            sbB.append(",Ver=");
            sbB.append(mtkTvGingaAppExtendedDataBase.getVerInfo());
            sbB.append(",IconPath=");
            sbB.append(mtkTvGingaAppExtendedDataBase.getIconPath());
            sbB.append(",Size=");
            sbB.append(mtkTvGingaAppExtendedDataBase.getSize());
            Log.d(TAG, sbB.toString());
        }
        return appExtendedData_native;
    }

    public int getApplicationInfo() {
        Log.d(TAG, "getApplicationInfo\n");
        return TVNativeWrapper.getApplicationInfo_native();
    }

    public List<MtkTvGingaAppInfoBase> getApplicationInfoList() {
        ArrayList arrayList = new ArrayList();
        Log.d(TAG, "getApplicationInfoList begin");
        int applicationInfoList_native = TVNativeWrapper.getApplicationInfoList_native(arrayList);
        StringBuilder sbA = a.a("getApplicationInfoList: count=");
        sbA.append(arrayList.size());
        Log.d(TAG, sbA.toString());
        for (int i = 0; i < arrayList.size(); i++) {
            StringBuilder sbA2 = a.a(" getApplicationInfoList: AppId=");
            sbA2.append(((MtkTvGingaAppInfoBase) arrayList.get(i)).getAppId());
            sbA2.append(",AppName=");
            sbA2.append(((MtkTvGingaAppInfoBase) arrayList.get(i)).getAppName());
            sbA2.append(",IsRuning=");
            sbA2.append(((MtkTvGingaAppInfoBase) arrayList.get(i)).isRunning());
            sbA2.append(",IsPersitent=");
            sbA2.append(((MtkTvGingaAppInfoBase) arrayList.get(i)).isPersistent());
            Log.d(TAG, sbA2.toString());
        }
        a.b("- getApplicationInfoList. ret=", applicationInfoList_native, TAG);
        return arrayList;
    }

    public List<MtkTvGingaWebServiceClientInfoBase> getWebServiceClient() {
        ArrayList arrayList = new ArrayList();
        Log.d(TAG, "getWebServiceClient begin");
        int webServiceClient_native = TVNativeWrapper.getWebServiceClient_native(arrayList);
        StringBuilder sbA = a.a("getWebServiceClient: count=");
        sbA.append(arrayList.size());
        Log.d(TAG, sbA.toString());
        for (int i = 0; i < arrayList.size(); i++) {
            StringBuilder sbA2 = a.a(" getWebServiceClient: WscName=");
            sbA2.append(((MtkTvGingaWebServiceClientInfoBase) arrayList.get(i)).getWscName());
            sbA2.append(",WscUUID=");
            sbA2.append(((MtkTvGingaWebServiceClientInfoBase) arrayList.get(i)).getWscUUID());
            sbA2.append(",WscState=");
            sbA2.append(((MtkTvGingaWebServiceClientInfoBase) arrayList.get(i)).getWscState());
            Log.d(TAG, sbA2.toString());
        }
        a.b("- getWebServiceClient. ret=", webServiceClient_native, TAG);
        return arrayList;
    }

    public boolean isGingaWindowResize() {
        Log.d(TAG, "isGingaWindowResize\n");
        return TVNativeWrapper.getGingaScreenModeisEnable_native();
    }

    public int markWebServiceTrustClient(String str, boolean z) {
        Log.d(TAG, "markWebServiceTrustClient sWscUUID= " + str + "isTrusted= " + z);
        return TVNativeWrapper.markWebServiceTrustClient_native(str, z);
    }

    public int removeWebServiceClient(String str) {
        Log.d(TAG, "removeWebServiceClient sWSCUUID= " + str);
        return TVNativeWrapper.removeWebServiceClient_native(str);
    }

    public int startApplication(String str) {
        Log.d(TAG, "startApplication\n");
        return TVNativeWrapper.startApplication_native(str);
    }

    public int startGinga() {
        Log.d(TAG, "startGinga\n");
        return TVNativeWrapper.startGinga_native();
    }

    public int stopApplication(String str) {
        Log.d(TAG, "stopApplication\n");
        return TVNativeWrapper.stopApplication_native(str);
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
}
