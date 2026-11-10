package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.tv.model.MtkTvCIMMIEnqBase;
import com.mediatek.twoworlds.tv.model.MtkTvCIMMIMenuBase;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvCIBase {
    public static final String TAG = "MtkTvCIBase";
    public int slot_id;
    public static MtkTvCIMMIMenuBase mMtkTvCIMMIMenuBase = new MtkTvCIMMIMenuBase();
    public static MtkTvCIMMIEnqBase mMtkTvCIMMIEnq = new MtkTvCIMMIEnqBase();

    public MtkTvCIBase(int i) {
        this.slot_id = i;
    }

    public static int getCamPinCaps() {
        return TVNativeWrapper.getCamPinCaps_navtive();
    }

    public static int getEnqID() {
        return TVNativeWrapper.getEnqID_navtive();
    }

    public static int getMenuListID() {
        return TVNativeWrapper.getMenuListID_navtive();
    }

    public static MtkTvCIMMIEnqBase getNewMMIEnq(int i, byte b2, byte b3, String str) {
        StringBuilder sbB = a.b("enq_id=", i, " anwDataLen=", b2, " bBlindAnswe=");
        sbB.append((int) b3);
        sbB.append(" text=");
        sbB.append(str);
        sbB.append(".\n");
        Log.d(TAG, sbB.toString());
        mMtkTvCIMMIEnq.setMMIId(i);
        mMtkTvCIMMIEnq.setAnsTextLen(b2);
        mMtkTvCIMMIEnq.setBlindAns(b3);
        mMtkTvCIMMIEnq.setText(str);
        return mMtkTvCIMMIEnq;
    }

    public static MtkTvCIMMIMenuBase getNewMMIMenu(int i, byte b2, String str, String str2, String str3, String str4) {
        Log.d(TAG, " getNewMMIMenu.\n");
        mMtkTvCIMMIMenuBase.setMMIId(i);
        mMtkTvCIMMIMenuBase.setItemNum(b2);
        mMtkTvCIMMIMenuBase.setTitle(str);
        mMtkTvCIMMIMenuBase.setSubtitle(str2);
        mMtkTvCIMMIMenuBase.setBottom(str3);
        if (b2 <= 0 || str4 == null) {
            a.a(" item_nb = ", b2, " or itemList = null, so set ItemList null.\n", TAG);
            mMtkTvCIMMIMenuBase.setItemList(null);
        } else {
            String[] strArrSplit = str4.split("#\\$#", -1);
            StringBuilder sbB = a.b("menuId=", i, " item_nb=", b2, " title=");
            a.a(sbB, str, " subTitle=", str2, " bottom=");
            sbB.append(str3);
            sbB.append(" itemlist=");
            sbB.append(str4);
            sbB.append("\n");
            Log.d(TAG, sbB.toString());
            StringBuilder sb = new StringBuilder();
            sb.append(" list.size()=");
            a.a(sb, strArrSplit.length, ".\n", TAG);
            for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                StringBuilder sbA = a.a(" list[", i2, "]=");
                sbA.append(strArrSplit[i2]);
                sbA.append("\n");
                Log.d(TAG, sbA.toString());
            }
            mMtkTvCIMMIMenuBase.setItemList(strArrSplit);
        }
        return mMtkTvCIMMIMenuBase;
    }

    public static int getSlotNum() {
        return TVNativeWrapper.getSlotNum_navtive();
    }

    public int cancelCamScan() {
        Log.d(TAG, "&&&&&, MtkTvCIBase.cancelCamScan.\n");
        TVNativeWrapper.cancelCamScan_navtive(this.slot_id);
        return 0;
    }

    public void enterMMI() {
        TVNativeWrapper.enterMMI_navtive(this.slot_id);
    }

    public int eraseCIKey() {
        return TVNativeWrapper.eraseCIKey_navtive();
    }

    public int getCIHostID(int[] iArr) {
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCIHostID.\n");
        int length = iArr.length;
        if (length < 8) {
            a.a("&&&&&, MtkTvCIBase.getCIHostID. host_id size = ", length, "(< 8) return fail.\n", TAG);
            return -1;
        }
        int cIHostID_navtive = TVNativeWrapper.getCIHostID_navtive(iArr);
        a.a("&&&&&, MtkTvCIBase.getCIHostID. return value = ", cIHostID_navtive, "\n", TAG);
        int iA = 0;
        while (iA < length) {
            iA = a.a(a.a("MtkTvCIBase: host_id[", iA, "] = "), iArr[iA], "\n", TAG, iA, 1);
        }
        return cIHostID_navtive;
    }

    public boolean getCIKeyStatus() {
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCIKeyStatus.\n");
        return TVNativeWrapper.getCIKeyStatus_navtive();
    }

    public String getCIKeyinfo() {
        return TVNativeWrapper.getCIKeyinfo_navtive();
    }

    public int getCaSystemIDMatch() {
        int caSystemIDMatch_navtive = TVNativeWrapper.getCaSystemIDMatch_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getCaSystemIDMatch. match = ", caSystemIDMatch_navtive, "\n", TAG);
        return caSystemIDMatch_navtive;
    }

    public String getCamID() {
        String camID_navtive = TVNativeWrapper.getCamID_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getCamID. CamID =", camID_navtive, ".\n", TAG);
        if (camID_navtive != null) {
            StringBuilder sbA = a.a("&&&&&, CamID.length =");
            sbA.append(camID_navtive.length());
            sbA.append(".\n");
            Log.d(TAG, sbA.toString());
        } else {
            Log.d(TAG, "&&&&&, return NULL!\n");
        }
        return camID_navtive;
    }

    public String getCamName() {
        return TVNativeWrapper.getCamName_navtive(this.slot_id);
    }

    public String getCamPinCode() {
        String camPinCode_navtive = TVNativeWrapper.getCamPinCode_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getCamPinCode, pin_code =", camPinCode_navtive, ".\n", TAG);
        if (camPinCode_navtive != null) {
            StringBuilder sbA = a.a("&&&&&, pin_code.length() =");
            sbA.append(camPinCode_navtive.length());
            sbA.append(".\n");
            Log.d(TAG, sbA.toString());
        } else {
            Log.d(TAG, "&&&&&, return NULL!\n");
        }
        return camPinCode_navtive;
    }

    public int getCamRatingValue() {
        int camRatingValue_navtive = TVNativeWrapper.getCamRatingValue_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getCamRatingValue. cam_rating = ", camRatingValue_navtive, "\n", TAG);
        return camRatingValue_navtive;
    }

    public int getCamScanReqTypeACON() {
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCamScanReqTypeACON.\n");
        int camScanReqTypeACON_navtive = TVNativeWrapper.getCamScanReqTypeACON_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getCamScanReqTypeACON. ret = ", camScanReqTypeACON_navtive, "\n", TAG);
        return camScanReqTypeACON_navtive;
    }

    public int getHostQuietTuneStatus() {
        int hostQuietTuneStatus_navtive = TVNativeWrapper.getHostQuietTuneStatus_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getHostQuietTuneStatus. quiet_tune_status = ", hostQuietTuneStatus_navtive, "\n", TAG);
        return hostQuietTuneStatus_navtive;
    }

    public int getHostTuneBrdcstStatus() {
        int hostTuneBrdcstStatus_navtive = TVNativeWrapper.getHostTuneBrdcstStatus_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getHostTuneBrdcstStatus. tune_brdcst_status = ", hostTuneBrdcstStatus_navtive, "\n", TAG);
        return hostTuneBrdcstStatus_navtive;
    }

    public int getHostTuneStatus() {
        int hostTuneStatus_navtive = TVNativeWrapper.getHostTuneStatus_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getHostTuneStatus. tune_status = ", hostTuneStatus_navtive, "\n", TAG);
        return hostTuneStatus_navtive;
    }

    public String getProfileISO639LangCode() {
        Log.d(TAG, "&&&&&, MtkTvCIBase.getProfileISO639LangCode.\n");
        String profileISO639LangCode_navtive = TVNativeWrapper.getProfileISO639LangCode_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getProfileISO639LangCode. langcode = ", profileISO639LangCode_navtive, "\n", TAG);
        return profileISO639LangCode_navtive;
    }

    public String getProfileName() {
        String profileName_navtive = TVNativeWrapper.getProfileName_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getProfileName. Name =", profileName_navtive, ".\n", TAG);
        return profileName_navtive;
    }

    public int getProfileResourceIsOpen() {
        int profileResourceIsOpen_navtive = TVNativeWrapper.getProfileResourceIsOpen_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getProfileResourceIsOpen. open = ", profileResourceIsOpen_navtive, "\n", TAG);
        return profileResourceIsOpen_navtive;
    }

    public int getProfileSupport(int i) {
        int profileSupport_navtive = TVNativeWrapper.getProfileSupport_navtive(this.slot_id, i);
        a.a("&&&&&, MtkTvCIBase.getProfileSupport. support = ", profileSupport_navtive, "\n", TAG);
        return profileSupport_navtive;
    }

    public int getProfileValid() {
        int profileValid_navtive = TVNativeWrapper.getProfileValid_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getProfileValid. valid = ", profileValid_navtive, "\n", TAG);
        return profileValid_navtive;
    }

    public int getSasCbctState() {
        int sasCbctState_navtive = TVNativeWrapper.getSasCbctState_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getSasCbctState. cbct_state = ", sasCbctState_navtive, "\n", TAG);
        return sasCbctState_navtive;
    }

    public int getSasItvState() {
        int sasItvState_navtive = TVNativeWrapper.getSasItvState_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.getSasItvState. itv_state = ", sasItvState_navtive, "\n", TAG);
        return sasItvState_navtive;
    }

    public boolean getSlotActive() {
        return TVNativeWrapper.getSlotActive_navtive(this.slot_id);
    }

    public int getSlotID() {
        return this.slot_id;
    }

    public String getVirtualChannelInfo() {
        Log.d(TAG, "&&&&&, MtkTvCIBase.getVirtualChannelInfo.\n");
        String virtualChannelInfo_navtive = TVNativeWrapper.getVirtualChannelInfo_navtive(this.slot_id);
        a.a(" MtkTvCIBase.getVirtualChannelInfo. vir_channel_info =", virtualChannelInfo_navtive, ".\n", TAG);
        return virtualChannelInfo_navtive;
    }

    public int setCamPinCode(String str) {
        return TVNativeWrapper.setCamPinCode_navtive(this.slot_id, str);
    }

    public int setEnqAnswer(int i, int i2, String str) {
        TVNativeWrapper.setEnqAnswer_navtive(this.slot_id, i, i2, str);
        return 0;
    }

    public int setHDSConfirm(int i) {
        a.a("&&&&&, MtkTvCIBase.setHDSConfirm value =", i, ".\n", TAG);
        TVNativeWrapper.setHDSConfirm_navtive(this.slot_id, i);
        Log.d(TAG, "&&&&&, MtkTvCIBase.setHDSConfirm. i4_ret = 0\n");
        return 0;
    }

    public void setMMIClose() {
        TVNativeWrapper.setMMIClose_navtive(this.slot_id);
    }

    public void setMMICloseDone() {
        TVNativeWrapper.setMMICloseDone_navtive(this.slot_id);
    }

    public int setMenuAnswer(int i, int i2) {
        TVNativeWrapper.setMenuAnswer_navtive(this.slot_id, i, i2);
        return 0;
    }

    public int setSasForceItvExit() {
        int sasForceItvExit_navtive = TVNativeWrapper.setSasForceItvExit_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.setSasForceItvExit. ret = ", sasForceItvExit_navtive, "\n", TAG);
        return sasForceItvExit_navtive;
    }

    public int setScanComplete() {
        int scanComplete_navtive = TVNativeWrapper.setScanComplete_navtive(this.slot_id);
        a.a("&&&&&, MtkTvCIBase.setScanComplete. ret = ", scanComplete_navtive, "\n", TAG);
        return scanComplete_navtive;
    }

    public int startCamScan(boolean z) {
        TVNativeWrapper.startCamScan_navtive(this.slot_id, z);
        return 0;
    }

    public int updateCIKey() {
        return TVNativeWrapper.updateCIKey_navtive();
    }

    public int updateCIKeyEx(int i) {
        Log.d(TAG, "&&&&&, MtkTvCIBase.updateCIKeyEx, cert_type:" + i + "\n");
        int iUpdateCIKeyEx_navtive = TVNativeWrapper.updateCIKeyEx_navtive(i);
        a.a("&&&&&, MtkTvCIBase.updateCIKeyEx, ret:", iUpdateCIKeyEx_navtive, "\n", TAG);
        return iUpdateCIKeyEx_navtive;
    }

    public MtkTvCIBase() {
        this.slot_id = 0;
    }
}
