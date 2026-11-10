package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.model.MtkTvCIMMIEnqBase;
import com.mediatek.twoworlds.tv.model.MtkTvCIMMIMenuBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvCIBase {
    public static final String TAG = "MtkTvCIBase";
    int slot_id;
    private static MtkTvCIMMIMenuBase mMtkTvCIMMIMenuBase = new MtkTvCIMMIMenuBase();
    private static MtkTvCIMMIEnqBase mMtkTvCIMMIEnq = new MtkTvCIMMIEnqBase();

    protected MtkTvCIBase(int i) {
        this.slot_id = i;
    }

    protected MtkTvCIBase() {
        this.slot_id = 0;
    }

    public int getSlotID() {
        return this.slot_id;
    }

    public static int getSlotNum() {
        return TVNativeWrapper.getSlotNum_navtive();
    }

    public static int getMenuListID() {
        return TVNativeWrapper.getMenuListID_navtive();
    }

    public static int getEnqID() {
        return TVNativeWrapper.getEnqID_navtive();
    }

    public String getCamName() {
        return TVNativeWrapper.getCamName_navtive(this.slot_id);
    }

    public String getCamID() {
        String camID_navtive = TVNativeWrapper.getCamID_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCamID. CamID =" + camID_navtive + ".\n");
        if (camID_navtive != null) {
            Log.d(TAG, "&&&&&, CamID.length =" + camID_navtive.length() + ".\n");
        } else {
            Log.d(TAG, "&&&&&, return NULL!\n");
        }
        return camID_navtive;
    }

    public boolean getSlotActive() {
        return TVNativeWrapper.getSlotActive_navtive(this.slot_id);
    }

    public int setMenuAnswer(int i, int i2) {
        TVNativeWrapper.setMenuAnswer_navtive(this.slot_id, i, i2);
        return 0;
    }

    public int setEnqAnswer(int i, int i2, String str) {
        TVNativeWrapper.setEnqAnswer_navtive(this.slot_id, i, i2, str);
        return 0;
    }

    public void setMMIClose() {
        TVNativeWrapper.setMMIClose_navtive(this.slot_id);
    }

    public void setMMICloseDone() {
        TVNativeWrapper.setMMICloseDone_navtive(this.slot_id);
    }

    public void enterMMI() {
        TVNativeWrapper.enterMMI_navtive(this.slot_id);
    }

    public int startCamScan(boolean z) {
        TVNativeWrapper.startCamScan_navtive(this.slot_id, z);
        return 0;
    }

    public int cancelCamScan() {
        Log.d(TAG, "&&&&&, MtkTvCIBase.cancelCamScan.\n");
        TVNativeWrapper.cancelCamScan_navtive(this.slot_id);
        return 0;
    }

    public int updateCIKey() {
        return TVNativeWrapper.updateCIKey_navtive();
    }

    public int updateCIKeyEx(int i) {
        Log.d(TAG, "&&&&&, MtkTvCIBase.updateCIKeyEx, cert_type:" + i + "\n");
        int iUpdateCIKeyEx_navtive = TVNativeWrapper.updateCIKeyEx_navtive(i);
        Log.d(TAG, "&&&&&, MtkTvCIBase.updateCIKeyEx, ret:" + iUpdateCIKeyEx_navtive + "\n");
        return iUpdateCIKeyEx_navtive;
    }

    public int eraseCIKey() {
        return TVNativeWrapper.eraseCIKey_navtive();
    }

    public String getCIKeyinfo() {
        return TVNativeWrapper.getCIKeyinfo_navtive();
    }

    public boolean getCIKeyStatus() {
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCIKeyStatus.\n");
        return TVNativeWrapper.getCIKeyStatus_navtive();
    }

    public int getCIHostID(int[] iArr) {
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCIHostID.\n");
        int length = iArr.length;
        if (length < 8) {
            Log.d(TAG, "&&&&&, MtkTvCIBase.getCIHostID. host_id size = " + length + "(< 8) return fail.\n");
            return -1;
        }
        int cIHostID_navtive = TVNativeWrapper.getCIHostID_navtive(iArr);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCIHostID. return value = " + cIHostID_navtive + "\n");
        for (int i = 0; i < length; i++) {
            Log.d(TAG, "MtkTvCIBase: host_id[" + i + "] = " + iArr[i] + "\n");
        }
        return cIHostID_navtive;
    }

    public int setCamPinCode(String str) {
        return TVNativeWrapper.setCamPinCode_navtive(this.slot_id, str);
    }

    public String getCamPinCode() {
        String camPinCode_navtive = TVNativeWrapper.getCamPinCode_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCamPinCode, pin_code =" + camPinCode_navtive + ".\n");
        if (camPinCode_navtive != null) {
            Log.d(TAG, "&&&&&, pin_code.length() =" + camPinCode_navtive.length() + ".\n");
        } else {
            Log.d(TAG, "&&&&&, return NULL!\n");
        }
        return camPinCode_navtive;
    }

    public static int getCamPinCaps() {
        return TVNativeWrapper.getCamPinCaps_navtive();
    }

    public int getCamRatingValue() {
        int camRatingValue_navtive = TVNativeWrapper.getCamRatingValue_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCamRatingValue. cam_rating = " + camRatingValue_navtive + "\n");
        return camRatingValue_navtive;
    }

    public int getCaSystemIDMatch() {
        int caSystemIDMatch_navtive = TVNativeWrapper.getCaSystemIDMatch_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCaSystemIDMatch. match = " + caSystemIDMatch_navtive + "\n");
        return caSystemIDMatch_navtive;
    }

    public int getProfileValid() {
        int profileValid_navtive = TVNativeWrapper.getProfileValid_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getProfileValid. valid = " + profileValid_navtive + "\n");
        return profileValid_navtive;
    }

    public int getProfileSupport(int i) {
        int profileSupport_navtive = TVNativeWrapper.getProfileSupport_navtive(this.slot_id, i);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getProfileSupport. support = " + profileSupport_navtive + "\n");
        return profileSupport_navtive;
    }

    public int getProfileResourceIsOpen() {
        int profileResourceIsOpen_navtive = TVNativeWrapper.getProfileResourceIsOpen_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getProfileResourceIsOpen. open = " + profileResourceIsOpen_navtive + "\n");
        return profileResourceIsOpen_navtive;
    }

    public String getProfileName() {
        String profileName_navtive = TVNativeWrapper.getProfileName_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getProfileName. Name =" + profileName_navtive + ".\n");
        return profileName_navtive;
    }

    public String getProfileISO639LangCode() {
        Log.d(TAG, "&&&&&, MtkTvCIBase.getProfileISO639LangCode.\n");
        String profileISO639LangCode_navtive = TVNativeWrapper.getProfileISO639LangCode_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getProfileISO639LangCode. langcode = " + profileISO639LangCode_navtive + "\n");
        return profileISO639LangCode_navtive;
    }

    public int getSasItvState() {
        int sasItvState_navtive = TVNativeWrapper.getSasItvState_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getSasItvState. itv_state = " + sasItvState_navtive + "\n");
        return sasItvState_navtive;
    }

    public int setSasForceItvExit() {
        int sasForceItvExit_navtive = TVNativeWrapper.setSasForceItvExit_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.setSasForceItvExit. ret = " + sasForceItvExit_navtive + "\n");
        return sasForceItvExit_navtive;
    }

    public int getSasCbctState() {
        int sasCbctState_navtive = TVNativeWrapper.getSasCbctState_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getSasCbctState. cbct_state = " + sasCbctState_navtive + "\n");
        return sasCbctState_navtive;
    }

    public int getHostTuneStatus() {
        int hostTuneStatus_navtive = TVNativeWrapper.getHostTuneStatus_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getHostTuneStatus. tune_status = " + hostTuneStatus_navtive + "\n");
        return hostTuneStatus_navtive;
    }

    public int getHostTuneBrdcstStatus() {
        int hostTuneBrdcstStatus_navtive = TVNativeWrapper.getHostTuneBrdcstStatus_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getHostTuneBrdcstStatus. tune_brdcst_status = " + hostTuneBrdcstStatus_navtive + "\n");
        return hostTuneBrdcstStatus_navtive;
    }

    public int getHostQuietTuneStatus() {
        int hostQuietTuneStatus_navtive = TVNativeWrapper.getHostQuietTuneStatus_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getHostQuietTuneStatus. quiet_tune_status = " + hostQuietTuneStatus_navtive + "\n");
        return hostQuietTuneStatus_navtive;
    }

    public int setHDSConfirm(int i) {
        Log.d(TAG, "&&&&&, MtkTvCIBase.setHDSConfirm value =" + i + ".\n");
        TVNativeWrapper.setHDSConfirm_navtive(this.slot_id, i);
        Log.d(TAG, "&&&&&, MtkTvCIBase.setHDSConfirm. i4_ret = 0\n");
        return 0;
    }

    public String getVirtualChannelInfo() {
        Log.d(TAG, "&&&&&, MtkTvCIBase.getVirtualChannelInfo.\n");
        String virtualChannelInfo_navtive = TVNativeWrapper.getVirtualChannelInfo_navtive(this.slot_id);
        Log.d(TAG, " MtkTvCIBase.getVirtualChannelInfo. vir_channel_info =" + virtualChannelInfo_navtive + ".\n");
        return virtualChannelInfo_navtive;
    }

    public int setScanComplete() {
        int scanComplete_navtive = TVNativeWrapper.setScanComplete_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.setScanComplete. ret = " + scanComplete_navtive + "\n");
        return scanComplete_navtive;
    }

    public int getCamScanReqTypeACON() {
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCamScanReqTypeACON.\n");
        int camScanReqTypeACON_navtive = TVNativeWrapper.getCamScanReqTypeACON_navtive(this.slot_id);
        Log.d(TAG, "&&&&&, MtkTvCIBase.getCamScanReqTypeACON. ret = " + camScanReqTypeACON_navtive + "\n");
        return camScanReqTypeACON_navtive;
    }

    private static MtkTvCIMMIEnqBase getNewMMIEnq(int i, byte b, byte b2, String str) {
        Log.d(TAG, "enq_id=" + i + " anwDataLen=" + ((int) b) + " bBlindAnswe=" + ((int) b2) + " text=" + str + ".\n");
        mMtkTvCIMMIEnq.setMMIId(i);
        mMtkTvCIMMIEnq.setAnsTextLen(b);
        mMtkTvCIMMIEnq.setBlindAns(b2);
        mMtkTvCIMMIEnq.setText(str);
        return mMtkTvCIMMIEnq;
    }

    private static MtkTvCIMMIMenuBase getNewMMIMenu(int i, byte b, String str, String str2, String str3, String str4) {
        Log.d(TAG, " getNewMMIMenu.\n");
        mMtkTvCIMMIMenuBase.setMMIId(i);
        mMtkTvCIMMIMenuBase.setItemNum(b);
        mMtkTvCIMMIMenuBase.setTitle(str);
        mMtkTvCIMMIMenuBase.setSubtitle(str2);
        mMtkTvCIMMIMenuBase.setBottom(str3);
        if (b > 0 && str4 != null) {
            String[] strArrSplit = str4.split("#\\$#", -1);
            Log.d(TAG, "menuId=" + i + " item_nb=" + ((int) b) + " title=" + str + " subTitle=" + str2 + " bottom=" + str3 + " itemlist=" + str4 + "\n");
            StringBuilder sb = new StringBuilder();
            sb.append(" list.size()=");
            sb.append(strArrSplit.length);
            sb.append(".\n");
            Log.d(TAG, sb.toString());
            for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                Log.d(TAG, " list[" + i2 + "]=" + strArrSplit[i2] + "\n");
            }
            mMtkTvCIMMIMenuBase.setItemList(strArrSplit);
        } else {
            Log.d(TAG, " item_nb = " + ((int) b) + " or itemList = null, so set ItemList null.\n");
            mMtkTvCIMMIMenuBase.setItemList(null);
        }
        return mMtkTvCIMMIMenuBase;
    }
}
