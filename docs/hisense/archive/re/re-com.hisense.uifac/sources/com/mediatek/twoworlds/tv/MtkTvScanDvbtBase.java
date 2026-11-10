package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvScanDvbtBase {
    private static final int EXCHANGE_GET_TYPE_FVP_ADVISE_SCAN = 10;
    private static final int EXCHANGE_GET_TYPE_FVP_USER_SELECTION_LIST = 9;
    private static final int EXCHANGE_GET_TYPE_FVP_USER_SELECTION_QUESTION = 8;
    private static final int EXCHANGE_GET_TYPE_FVP_VALID_ANSWER_TOKEN = 11;
    private static final int EXCHANGE_GET_TYPE_IS_CUSTOM_SORTING = 7;
    private static final int EXCHANGE_GET_TYPE_RF_INFO = 1;
    private static final int EXCHANGE_GET_TYPE_UI_OP_ITA_GROUP = 4;
    private static final int EXCHANGE_GET_TYPE_UI_OP_LCNV2_CH_LST = 6;
    private static final int EXCHANGE_GET_TYPE_UI_OP_MASK = 2;
    private static final int EXCHANGE_GET_TYPE_UI_OP_NOR_FAV_NWK = 5;
    private static final int EXCHANGE_GET_TYPE_UI_OP_UK_REGION = 3;
    private static final int EXCHANGE_GET_TYPE_UNKNOWN = 0;
    private static final int EXCHANGE_HEADER_LEN = 3;
    private static final int EXCHANGE_HEADER_LEN_IDX = 1;
    private static final int EXCHANGE_HEADER_TYPE_IDX = 2;
    private static final int EXCHANGE_MAX_LEN = 251;
    private static final int EXCHANGE_PAYLOAD_1ST_IDX = 3;
    private static final int EXCHANGE_RF_DIRECTION_CURRENT = 0;
    private static final int EXCHANGE_RF_DIRECTION_NEXT = 1;
    private static final int EXCHANGE_RF_DIRECTION_PREVIOUS = -1;
    private static final int EXCHANGE_SET_TYPE_AUTO_SCAN = 1002;
    private static final int EXCHANGE_SET_TYPE_BGM_SCAN = 1014;
    private static final int EXCHANGE_SET_TYPE_CANCEL_SCAN = 1001;
    private static final int EXCHANGE_SET_TYPE_DISABLE_AUTO_UPDATE = 1017;
    private static final int EXCHANGE_SET_TYPE_DISABLE_NETWORK_CHANGE = 1015;
    private static final int EXCHANGE_SET_TYPE_FVP_USER_SELECTION_LST = 1016;
    private static final int EXCHANGE_SET_TYPE_LOAD_CONFLICT_LCN = 1013;
    private static final int EXCHANGE_SET_TYPE_MANUAL_FREQ_SCAN = 1010;
    private static final int EXCHANGE_SET_TYPE_QUICK_SCAN = 1011;
    private static final int EXCHANGE_SET_TYPE_RANGE_SCAN = 1005;
    private static final int EXCHANGE_SET_TYPE_RF_SCAN = 1004;
    private static final int EXCHANGE_SET_TYPE_STORE_CONFLICT_LCN = 1012;
    private static final int EXCHANGE_SET_TYPE_UI_OP_ITA_GROUP = 1007;
    private static final int EXCHANGE_SET_TYPE_UI_OP_LCNV2_CH_LST = 1009;
    private static final int EXCHANGE_SET_TYPE_UI_OP_NOR_FAV_NWK = 1008;
    private static final int EXCHANGE_SET_TYPE_UI_OP_UK_REGION = 1006;
    private static final int EXCHANGE_SET_TYPE_UNKNOWN = 1000;
    private static final int EXCHANGE_SET_TYPE_UPDATE_SCAN = 1003;
    private static final int EXCHANGE_TOTAL_LEN_IDX = 0;
    private static final int MAX_USER_SELECITTION_TITLE_LEN = 201;
    private static final String TAG = "MtkTvScanDvbt";
    MtkTvScanDvbtBaseDebug debugModule = new MtkTvScanDvbtBaseDebug(this, false);

    public enum RfDirection {
        PREVIOUS,
        CURRENT,
        NEXT
    }

    public enum ScanDvbtRet {
        SCAN_DVBT_RET_OK,
        SCAN_DVBT_RET_INTERNAL_ERROR
    }

    public ScanDvbtRet startAutoScan() {
        _toolLog("Enter startAutoScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1002);
        _toolLog("Leave startAutoScan");
        return scanDvbtRet_toolOpOnlyTypeAndRet;
    }

    public ScanDvbtRet startAutoScan(boolean z) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, 0};
        _toolLog("Enter startAutoScan");
        _toolLog(z + " ");
        if (z) {
            iArr[1] = 1;
        }
        _toolExchange(1002, iArr);
        int i = iArr[0];
        if (i != 0) {
            return null;
        }
        if (i == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        }
        _toolLog("Leave startAutoScan");
        return scanDvbtRet;
    }

    public ScanDvbtRet startUpdateScan() {
        _toolLog("Enter startUpdateScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1003);
        _toolLog("Leave startUpdateScan");
        return scanDvbtRet_toolOpOnlyTypeAndRet;
    }

    public ScanDvbtRet startRfScan() {
        _toolLog("Enter startRfScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1004);
        _toolLog("Leave startRfScan");
        return scanDvbtRet_toolOpOnlyTypeAndRet;
    }

    public ScanDvbtRet startQuickScan() {
        _toolLog("Enter startQuickScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1011);
        _toolLog("Leave startQuickScan");
        return scanDvbtRet_toolOpOnlyTypeAndRet;
    }

    public ScanDvbtRet startManualFreqScan(int i) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, i};
        _toolLog("Enter startManualFreqScan");
        _toolLog(i + " ");
        _toolExchange(1010, iArr);
        int i2 = iArr[0];
        if (i2 != 0) {
            return null;
        }
        if (i2 == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        }
        _toolLog("Leave startManualFreqScan");
        return scanDvbtRet;
    }

    public ScanDvbtRet startBGMScan() {
        _toolLog("Enter startBGMScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1014);
        _toolLog("Leave startBGMScan");
        return scanDvbtRet_toolOpOnlyTypeAndRet;
    }

    public ScanDvbtRet startBGMScan(boolean z) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, 0};
        _toolLog("Enter startBGMScan");
        _toolLog(z + " ");
        if (z) {
            iArr[1] = 1;
        }
        _toolExchange(1014, iArr);
        int i = iArr[0];
        if (i != 0) {
            return null;
        }
        if (i == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        }
        _toolLog("Leave startBGMScan");
        return scanDvbtRet;
    }

    public ScanDvbtRet startRangeScan(RfInfo rfInfo, RfInfo rfInfo2) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, rfInfo.rfIndex, rfInfo2.rfIndex};
        _toolLog("Enter startRangeScan");
        _toolLog(rfInfo.rfChannelName + " " + rfInfo.rfIndex + "--->" + rfInfo2.rfChannelName + " " + rfInfo2.rfIndex);
        _toolExchange(1005, iArr);
        int i = iArr[0];
        if (i != 0) {
            return null;
        }
        if (i == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        }
        _toolLog("Leave startRangeScan");
        return scanDvbtRet;
    }

    public ScanDvbtRet setDisableNetworkChangeForCrnt(boolean z) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, 0};
        _toolLog("Enter setDisableNetworkChangeForCrnt");
        _toolLog(z + " ");
        if (z) {
            iArr[1] = 1;
        }
        _toolExchange(1015, iArr);
        int i = iArr[0];
        if (i != 0) {
            return null;
        }
        if (i == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        }
        _toolLog("Leave setDisableNetworkChangeForCrnt");
        return scanDvbtRet;
    }

    public ScanDvbtRet setDisableMonitor(boolean z) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, 0};
        _toolLog("Enter setDisableMonitor");
        _toolLog(z + " ");
        if (z) {
            iArr[1] = 1;
        }
        _toolExchange(1017, iArr);
        int i = iArr[0];
        if (i != 0) {
            return null;
        }
        if (i == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        }
        _toolLog("Leave setDisableMonitor");
        return scanDvbtRet;
    }

    public RfInfo[] getAllRf() {
        ArrayList arrayList = new ArrayList();
        _toolLog("getAllRf enter");
        RfInfo rfInfoGotoDestinationRf = gotoDestinationRf(RfDirection.CURRENT);
        if (rfInfoGotoDestinationRf == null) {
            return null;
        }
        arrayList.add(rfInfoGotoDestinationRf);
        RfInfo rfInfoGotoDestinationRf2 = gotoDestinationRf(RfDirection.NEXT);
        while (rfInfoGotoDestinationRf.rfIndex != rfInfoGotoDestinationRf2.rfIndex) {
            arrayList.add(rfInfoGotoDestinationRf2);
            rfInfoGotoDestinationRf2 = gotoDestinationRf(RfDirection.NEXT);
        }
        int size = arrayList.size();
        RfInfo[] rfInfoArr = new RfInfo[size];
        RfInfo[] rfInfoArr2 = new RfInfo[arrayList.size()];
        int i = ((RfInfo) arrayList.get(0)).rfIndex;
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            rfInfoArr2[i3] = (RfInfo) arrayList.get(i3);
            if (i > rfInfoArr2[i3].rfIndex) {
                i = rfInfoArr2[i3].rfIndex;
                i2 = i3;
            }
        }
        for (int i4 = 0; i4 < size; i4++) {
            rfInfoArr[i4] = rfInfoArr2[(i4 + i2) % size];
        }
        return rfInfoArr;
    }

    public class TargetRegion {
        private static final int MAX_REGION_NAME_LEN = 35;
        public int internalIdx;
        public int level;
        public String name;
        public int primary;
        public int secondary;
        public int tertiary;

        public TargetRegion() {
        }
    }

    public TargetRegion[] uiOpGetTargetRegion() {
        TargetRegion targetRegion = new TargetRegion();
        ArrayList arrayList = new ArrayList();
        UiOpSituation uiOpSituationUiOpGetSituation = uiOpGetSituation();
        _toolLog("uiOpGetTargetRegion enter");
        if (uiOpSituationUiOpGetSituation.targetRegionPopUp) {
            int i = 0;
            while (i == 0) {
                int i2 = targetRegion.internalIdx;
                int[] iArr = new int[41];
                iArr[0] = i;
                targetRegion.internalIdx = i2 + 1;
                iArr[1] = i2;
                _toolExchange(3, iArr);
                int i3 = iArr[0];
                if (i3 == 0) {
                    int[] iArr2 = new int[35];
                    for (int i4 = 0; i4 < 35; i4++) {
                        iArr2[i4] = iArr[6 + i4];
                    }
                    TargetRegion targetRegion2 = new TargetRegion();
                    targetRegion2.name = _toolConvertAsciiArrayToString(iArr2);
                    targetRegion2.internalIdx = iArr[1];
                    targetRegion2.level = iArr[2];
                    targetRegion2.primary = iArr[3];
                    targetRegion2.secondary = iArr[4];
                    targetRegion2.tertiary = iArr[5];
                    _toolLog(iArr[1] + " " + iArr[2] + " " + iArr[3] + " " + iArr[4] + " " + iArr[5] + " " + targetRegion2.name);
                    arrayList.add(targetRegion2);
                }
                i = i3;
            }
        }
        TargetRegion[] targetRegionArr = new TargetRegion[arrayList.size()];
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            targetRegionArr[i5] = (TargetRegion) arrayList.get(i5);
        }
        _toolLog("uiOpGetTargetRegion leave");
        return targetRegionArr;
    }

    public ScanDvbtRet uiOpSetTargetRegion(TargetRegion targetRegion) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, targetRegion.internalIdx};
        _toolLog(targetRegion.internalIdx + " " + targetRegion.level + " " + targetRegion.primary + " " + targetRegion.secondary + " " + targetRegion.tertiary + " " + targetRegion.name);
        _toolExchange(1006, iArr);
        if (iArr[0] == 0) {
            return ScanDvbtRet.SCAN_DVBT_RET_OK;
        }
        _toolLog("uiOpSetTargetRegion FAIL");
        return scanDvbtRet;
    }

    public class LcnConflictGroup {
        private static final int MAX_LCN_GROUP_CHANNEL_NAME = 35;
        public int LCN;
        public String[] channelName;
        public int groupIdx;

        public LcnConflictGroup() {
        }
    }

    public LcnConflictGroup[] uiOpGetLcnConflictGroup() {
        LcnConflictGroup lcnConflictGroup = new LcnConflictGroup();
        ArrayList arrayList = new ArrayList();
        UiOpSituation uiOpSituationUiOpGetSituation = uiOpGetSituation();
        _toolLog("uiOpGetLcnConflictGroup enter");
        if (uiOpSituationUiOpGetSituation.lcnConflictPopUp) {
            boolean z = false;
            int i = 0;
            while (!z) {
                ArrayList arrayList2 = new ArrayList();
                int i2 = 0;
                int i3 = 0;
                while (i2 == 0) {
                    int[] iArr = new int[39];
                    iArr[0] = i2;
                    iArr[1] = i;
                    iArr[2] = i3;
                    iArr[3] = 0;
                    _toolExchange(4, iArr);
                    int i4 = iArr[0];
                    if (i4 == 0) {
                        int[] iArr2 = new int[35];
                        for (int i5 = 0; i5 < 35; i5++) {
                            iArr2[i5] = iArr[4 + i5];
                        }
                        String str_toolConvertAsciiArrayToString = _toolConvertAsciiArrayToString(iArr2);
                        lcnConflictGroup.LCN = iArr[3];
                        arrayList2.add(str_toolConvertAsciiArrayToString);
                        _toolLog(lcnConflictGroup.LCN + " " + str_toolConvertAsciiArrayToString);
                    } else if (i3 == 0) {
                        z = true;
                    }
                    i3++;
                    i2 = i4;
                }
                if (!z) {
                    LcnConflictGroup lcnConflictGroup2 = new LcnConflictGroup();
                    lcnConflictGroup2.LCN = lcnConflictGroup.LCN;
                    lcnConflictGroup2.groupIdx = i;
                    lcnConflictGroup2.channelName = new String[arrayList2.size()];
                    int i6 = 0;
                    while (true) {
                        String[] strArr = lcnConflictGroup2.channelName;
                        if (i6 >= strArr.length) {
                            break;
                        }
                        strArr[i6] = (String) arrayList2.get(i6);
                        _toolLog("LCN Group" + lcnConflictGroup.LCN);
                        i6++;
                    }
                    arrayList.add(lcnConflictGroup2);
                }
                i++;
            }
        }
        int size = arrayList.size();
        LcnConflictGroup[] lcnConflictGroupArr = new LcnConflictGroup[size];
        for (int i7 = 0; i7 < size; i7++) {
            lcnConflictGroupArr[i7] = (LcnConflictGroup) arrayList.get(i7);
        }
        _toolLog("uiOpGetLcnConflictGroup leave");
        return lcnConflictGroupArr;
    }

    public ScanDvbtRet uiOpSetLcnConflictGroup(LcnConflictGroup lcnConflictGroup, String str) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        _toolLog(lcnConflictGroup.LCN + " " + lcnConflictGroup.groupIdx + " " + str);
        int i = 0;
        while (true) {
            String[] strArr = lcnConflictGroup.channelName;
            if (i >= strArr.length) {
                i = 0;
                break;
            }
            if (strArr[i].equals(str)) {
                _toolLog("channel index" + i);
                break;
            }
            i++;
        }
        int[] iArr = {0, lcnConflictGroup.groupIdx, i};
        _toolExchange(1007, iArr);
        if (iArr[0] == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        } else {
            _toolLog("uiOpSetLcnConflictGroup FAIL");
        }
        _toolLog("uiOpSetLcnConflictGroup leave");
        return scanDvbtRet;
    }

    public class UiOpSituation {
        public boolean favouriteNeteorkPopUp;
        public boolean lcnConflictPopUp;
        public boolean lcnv2PopUp;
        public boolean storeSvcPopUp;
        public boolean targetRegionPopUp;

        public UiOpSituation() {
        }
    }

    public UiOpSituation uiOpGetSituation() {
        int[] iArr = {0, 0, 0, 0, 0, 0};
        UiOpSituation uiOpSituation = new UiOpSituation();
        _toolLog("uiOpGetSituation enter");
        _toolExchange(2, iArr);
        if (iArr[0] == 0) {
            uiOpSituation.storeSvcPopUp = iArr[1] != 0;
            uiOpSituation.targetRegionPopUp = iArr[2] != 0;
            uiOpSituation.lcnConflictPopUp = iArr[3] != 0;
            uiOpSituation.favouriteNeteorkPopUp = iArr[4] != 0;
            uiOpSituation.lcnv2PopUp = iArr[5] != 0;
            _toolLog("mwScanRet OK" + iArr[1] + iArr[2] + iArr[3] + iArr[4] + iArr[5]);
        } else {
            _toolLog("mwScanRet NG");
        }
        _toolLog("uiOpGetSituation leave");
        return uiOpSituation;
    }

    public class FavNwk {
        private static final int MAX_NETWORK_NAME_LEN = 35;
        public int index;
        public String networkName;

        public FavNwk() {
        }
    }

    public FavNwk[] uiOpGetFavNwk() {
        FavNwk favNwk = new FavNwk();
        ArrayList arrayList = new ArrayList();
        UiOpSituation uiOpSituationUiOpGetSituation = uiOpGetSituation();
        _toolLog("uiOpGetFavNwk enter");
        if (uiOpSituationUiOpGetSituation.favouriteNeteorkPopUp) {
            int i = 0;
            while (i == 0) {
                int i2 = favNwk.index;
                int[] iArr = new int[37];
                iArr[0] = i;
                favNwk.index = i2 + 1;
                iArr[1] = i2;
                _toolExchange(5, iArr);
                int i3 = iArr[0];
                if (i3 == 0) {
                    int[] iArr2 = new int[35];
                    for (int i4 = 0; i4 < 35; i4++) {
                        iArr2[i4] = iArr[2 + i4];
                    }
                    FavNwk favNwk2 = new FavNwk();
                    favNwk2.networkName = _toolConvertAsciiArrayToString(iArr2);
                    favNwk2.index = iArr[1];
                    _toolLog(favNwk2.index + " " + favNwk2.networkName);
                    arrayList.add(favNwk2);
                }
                i = i3;
            }
        }
        FavNwk[] favNwkArr = new FavNwk[arrayList.size()];
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            favNwkArr[i5] = (FavNwk) arrayList.get(i5);
        }
        _toolLog("uiOpGetFavNwk leave");
        return favNwkArr;
    }

    public ScanDvbtRet uiOpSetFavNwk(FavNwk favNwk) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, favNwk.index};
        _toolLog(favNwk.networkName + " " + favNwk.index);
        _toolExchange(1008, iArr);
        if (iArr[0] == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        } else {
            _toolLog("uiOpSetFavNwk FAIL");
        }
        _toolLog("uiOpSetFavNwk leave");
        return scanDvbtRet;
    }

    public class LCNv2ChannelList {
        private static final int MAX_LCNV2_CHANNEL_LIST_NAME_LEN = 35;
        public String channelListName;
        public int index;

        public LCNv2ChannelList() {
        }
    }

    public LCNv2ChannelList[] uiOpGetLCNv2ChannelList() {
        LCNv2ChannelList lCNv2ChannelList = new LCNv2ChannelList();
        ArrayList arrayList = new ArrayList();
        UiOpSituation uiOpSituationUiOpGetSituation = uiOpGetSituation();
        _toolLog("uiOpGetLCNv2ChannelList enter");
        if (uiOpSituationUiOpGetSituation.lcnv2PopUp) {
            int i = 0;
            while (i == 0) {
                int i2 = lCNv2ChannelList.index;
                int[] iArr = new int[37];
                iArr[0] = i;
                lCNv2ChannelList.index = i2 + 1;
                iArr[1] = i2;
                _toolExchange(6, iArr);
                int i3 = iArr[0];
                if (i3 == 0) {
                    int[] iArr2 = new int[35];
                    for (int i4 = 0; i4 < 35; i4++) {
                        iArr2[i4] = iArr[2 + i4];
                    }
                    LCNv2ChannelList lCNv2ChannelList2 = new LCNv2ChannelList();
                    lCNv2ChannelList2.channelListName = _toolConvertAsciiArrayToString(iArr2);
                    lCNv2ChannelList2.index = iArr[1];
                    _toolLog(lCNv2ChannelList2.index + " " + lCNv2ChannelList2.channelListName);
                    arrayList.add(lCNv2ChannelList2);
                }
                i = i3;
            }
        }
        LCNv2ChannelList[] lCNv2ChannelListArr = new LCNv2ChannelList[arrayList.size()];
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            lCNv2ChannelListArr[i5] = (LCNv2ChannelList) arrayList.get(i5);
        }
        _toolLog("uiOpGetLCNv2ChannelList leave");
        return lCNv2ChannelListArr;
    }

    public ScanDvbtRet uiOpSetLCNv2ChannelList(LCNv2ChannelList lCNv2ChannelList) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, lCNv2ChannelList.index};
        _toolLog(lCNv2ChannelList.channelListName + " " + lCNv2ChannelList.index);
        _toolExchange(1009, iArr);
        if (iArr[0] == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        } else {
            _toolLog("uiOpSetLCNv2ChannelList FAIL");
        }
        _toolLog("uiOpSetLCNv2ChannelList leave");
        return scanDvbtRet;
    }

    public ScanDvbtRet cancelScan() {
        _toolLog("Enter cancelScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1001);
        _toolLog("Leave cancelScan");
        return scanDvbtRet_toolOpOnlyTypeAndRet;
    }

    public class RfInfo {
        private static final int MAX_CHANNEL_NAME_LEN = 20;
        public String rfChannelName;
        public int rfFrequence;
        public int rfIndex;

        public RfInfo() {
        }
    }

    public RfInfo gotoDestinationRf(RfDirection rfDirection) {
        _toolLog("Enter getRfInfo" + rfDirection);
        int i_toolRfDirectionToInt = _toolRfDirectionToInt(rfDirection);
        int[] iArr = new int[20];
        int[] iArr2 = new int[24];
        iArr2[0] = 0;
        iArr2[1] = i_toolRfDirectionToInt;
        _toolExchange(1, iArr2);
        if (iArr2[0] != 0) {
            return null;
        }
        for (int i = 0; i < 20; i++) {
            iArr[i] = iArr2[4 + i];
        }
        _toolLog("RF channel int:" + iArr[0] + iArr[1] + iArr[2] + iArr[3]);
        StringBuilder sb = new StringBuilder();
        sb.append("RF channel test:");
        sb.append(_toolConvertAsciiArrayToString(new int[]{48, 49, 50, 51, 52, 65, 98, 67}));
        _toolLog(sb.toString());
        RfInfo rfInfo = new RfInfo();
        rfInfo.rfChannelName = _toolConvertAsciiArrayToString(iArr);
        rfInfo.rfIndex = iArr2[2];
        rfInfo.rfFrequence = iArr2[3];
        _toolLog("RF channel string:" + rfInfo.rfChannelName + " " + rfInfo.rfIndex + " " + rfInfo.rfFrequence);
        _toolLog("Leave getRfInfo");
        this.debugModule.addDirection(rfDirection);
        return rfInfo;
    }

    private class MtkTvScanDvbtBaseDebug {
        RfDirection[] backDoor;
        RfDirection[] backDoorKey;
        boolean isDebugMode;
        int keyNum;
        MtkTvScanDvbtBase testDvbt;

        private MtkTvScanDvbtBaseDebug(MtkTvScanDvbtBase mtkTvScanDvbtBase, boolean z) {
            RfDirection rfDirection = RfDirection.NEXT;
            RfDirection rfDirection2 = RfDirection.PREVIOUS;
            this.backDoor = new RfDirection[]{rfDirection, rfDirection, rfDirection2, rfDirection2};
            RfDirection rfDirection3 = RfDirection.CURRENT;
            this.backDoorKey = new RfDirection[]{rfDirection3, rfDirection3, rfDirection3, rfDirection3};
            this.keyNum = 0;
            this.isDebugMode = z;
            resetKey();
            this.testDvbt = mtkTvScanDvbtBase;
        }

        private void resetKey() {
            int i = 0;
            while (true) {
                RfDirection[] rfDirectionArr = this.backDoorKey;
                if (i < rfDirectionArr.length) {
                    rfDirectionArr[i] = RfDirection.CURRENT;
                    i++;
                } else {
                    this.keyNum = 0;
                    return;
                }
            }
        }

        private void testDvbt() {
            testDvbtUiOp();
            testDvbtAllRfInfo();
        }

        private void testDvbtAllRfInfo() {
            RfInfo[] allRf = this.testDvbt.getAllRf();
            this.testDvbt._toolLog("allRfInfo:" + allRf.length);
            for (int i = 0; i < allRf.length; i++) {
                this.testDvbt._toolLog("RfInfo: " + allRf[i].rfIndex + " " + allRf[i].rfFrequence + " " + allRf[i].rfChannelName);
            }
        }

        private void testDvbtUiOp() {
            UiOpSituation uiOpSituationUiOpGetSituation = this.testDvbt.uiOpGetSituation();
            if (uiOpSituationUiOpGetSituation.storeSvcPopUp) {
                this.testDvbt._toolLog("storeSvcPopUp");
            }
            if (uiOpSituationUiOpGetSituation.targetRegionPopUp) {
                this.testDvbt._toolLog("targetRegionPopUp");
                TargetRegion[] targetRegionArrUiOpGetTargetRegion = this.testDvbt.uiOpGetTargetRegion();
                this.testDvbt._toolLog("TargetRegion:" + targetRegionArrUiOpGetTargetRegion.length);
                for (int i = 0; i < targetRegionArrUiOpGetTargetRegion.length; i++) {
                    this.testDvbt._toolLog("targetRegion: " + targetRegionArrUiOpGetTargetRegion[i].internalIdx + " " + targetRegionArrUiOpGetTargetRegion[i].level + " " + targetRegionArrUiOpGetTargetRegion[i].primary + " " + targetRegionArrUiOpGetTargetRegion[i].secondary + " " + targetRegionArrUiOpGetTargetRegion[i].tertiary + " " + targetRegionArrUiOpGetTargetRegion[i].name);
                }
            }
            if (uiOpSituationUiOpGetSituation.lcnConflictPopUp) {
                this.testDvbt._toolLog("lcnConflictPopUp");
                LcnConflictGroup[] lcnConflictGroupArrUiOpGetLcnConflictGroup = this.testDvbt.uiOpGetLcnConflictGroup();
                this.testDvbt._toolLog("LcnConflictGroup:" + lcnConflictGroupArrUiOpGetLcnConflictGroup.length);
                for (int i2 = 0; i2 < lcnConflictGroupArrUiOpGetLcnConflictGroup.length; i2++) {
                    this.testDvbt._toolLog("LcnConflictGroup: " + lcnConflictGroupArrUiOpGetLcnConflictGroup[i2].groupIdx + " " + lcnConflictGroupArrUiOpGetLcnConflictGroup[i2].LCN);
                    for (int i3 = 0; i3 < lcnConflictGroupArrUiOpGetLcnConflictGroup[i2].channelName.length; i3++) {
                        this.testDvbt._toolLog(lcnConflictGroupArrUiOpGetLcnConflictGroup[i2].channelName[i3]);
                    }
                }
            }
            if (uiOpSituationUiOpGetSituation.favouriteNeteorkPopUp) {
                this.testDvbt._toolLog("favouriteNeteorkPopUp");
                FavNwk[] favNwkArrUiOpGetFavNwk = this.testDvbt.uiOpGetFavNwk();
                this.testDvbt._toolLog("FavNwk:" + favNwkArrUiOpGetFavNwk.length);
                for (int i4 = 0; i4 < favNwkArrUiOpGetFavNwk.length; i4++) {
                    this.testDvbt._toolLog("FavNwk: " + favNwkArrUiOpGetFavNwk[i4].index + " " + favNwkArrUiOpGetFavNwk[i4].networkName);
                }
            }
            if (uiOpSituationUiOpGetSituation.lcnv2PopUp) {
                this.testDvbt._toolLog("lcnv2PopUp");
                LCNv2ChannelList[] lCNv2ChannelListArrUiOpGetLCNv2ChannelList = this.testDvbt.uiOpGetLCNv2ChannelList();
                this.testDvbt._toolLog("LCNv2ChannelList:" + lCNv2ChannelListArrUiOpGetLCNv2ChannelList.length);
                for (int i5 = 0; i5 < lCNv2ChannelListArrUiOpGetLCNv2ChannelList.length; i5++) {
                    this.testDvbt._toolLog("LCNv2ChannelList: " + lCNv2ChannelListArrUiOpGetLCNv2ChannelList[i5].index + " " + lCNv2ChannelListArrUiOpGetLCNv2ChannelList[i5].channelListName);
                }
            }
        }

        public void addDirection(RfDirection rfDirection) {
            if (this.isDebugMode) {
                if (RfDirection.CURRENT == rfDirection) {
                    this.testDvbt._toolLog("reset back door key");
                    resetKey();
                    return;
                }
                RfDirection[] rfDirectionArr = this.backDoorKey;
                int i = this.keyNum;
                rfDirectionArr[i % rfDirectionArr.length] = rfDirection;
                this.keyNum = (i + 1) % rfDirectionArr.length;
                int i2 = 0;
                boolean z = true;
                while (true) {
                    RfDirection[] rfDirectionArr2 = this.backDoor;
                    if (i2 >= rfDirectionArr2.length) {
                        break;
                    }
                    z = z && rfDirectionArr2[i2] == this.backDoorKey[i2];
                    i2++;
                }
                this.testDvbt._toolLog("add key: " + rfDirection);
                if (z) {
                    this.testDvbt._toolLog("open the door");
                    testDvbt();
                }
            }
        }
    }

    public boolean IsAdviseScan() {
        int[] iArr = {0, 0};
        _toolLog("Enter IsAdviseScan");
        _toolExchange(10, iArr);
        int i = iArr[1];
        _toolLog("Leave IsAdviseScan:" + iArr[1]);
        return i != 0;
    }

    public boolean IsValidAnswerToken() {
        int[] iArr = {0, 0};
        _toolLog("Enter IsValidAnswerToken");
        _toolExchange(11, iArr);
        int i = iArr[1];
        _toolLog("Leave IsValidAnswerToken:" + iArr[1]);
        return i != 0;
    }

    public class UserSelection {
        private static final int MAX_USER_SELECITTION_TEXT_LEN = 41;
        public int index;
        public String text;

        public UserSelection() {
        }
    }

    public String FVPGetUserSelectionTitle() {
        String str_toolConvertAsciiArrayToString;
        int[] iArr = new int[MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TOKEN_ACQUISITION_ERROR];
        _toolLog("FVPGetUserSelectionTitle enter");
        iArr[0] = 0;
        iArr[1] = 0;
        _toolExchange(8, iArr);
        if (iArr[0] == 0) {
            int[] iArr2 = new int[201];
            for (int i = 0; i < 201; i++) {
                iArr2[i] = iArr[2 + i];
            }
            str_toolConvertAsciiArrayToString = _toolConvertAsciiArrayToString(iArr2);
            _toolLog(str_toolConvertAsciiArrayToString);
        } else {
            str_toolConvertAsciiArrayToString = "";
        }
        _toolLog("FVPGetUserSelectionTitle leave");
        return str_toolConvertAsciiArrayToString;
    }

    public UserSelection[] FVPGetUserSelection() {
        UserSelection userSelection = new UserSelection();
        ArrayList arrayList = new ArrayList();
        _toolLog("FVPGetUserSelection enter");
        int i = 0;
        while (i == 0) {
            int i2 = userSelection.index;
            int[] iArr = new int[43];
            iArr[0] = i;
            userSelection.index = i2 + 1;
            iArr[1] = i2;
            _toolExchange(9, iArr);
            int i3 = iArr[0];
            if (i3 == 0) {
                int[] iArr2 = new int[41];
                for (int i4 = 0; i4 < 41; i4++) {
                    iArr2[i4] = iArr[2 + i4];
                }
                UserSelection userSelection2 = new UserSelection();
                userSelection2.text = _toolConvertAsciiArrayToString(iArr2);
                userSelection2.index = iArr[1];
                _toolLog(userSelection2.index + " " + userSelection2.text);
                arrayList.add(userSelection2);
            }
            i = i3;
        }
        UserSelection[] userSelectionArr = new UserSelection[arrayList.size()];
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            userSelectionArr[i5] = (UserSelection) arrayList.get(i5);
        }
        _toolLog("FVPGetUserSelection leave");
        return userSelectionArr;
    }

    public ScanDvbtRet FVPSetUserSelection(UserSelection userSelection) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, userSelection.index};
        _toolLog("FVPSetUserSelection enter");
        _toolLog(userSelection.text + " " + userSelection.index);
        _toolExchange(1016, iArr);
        if (iArr[0] == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        } else {
            _toolLog("FVPSetUserSelection FAIL");
        }
        _toolLog("FVPSetUserSelection leave");
        return scanDvbtRet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toolLog(String str) {
        Log.d(TAG, str + "\n");
    }

    private String _toolConvertAsciiArrayToString(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        if (iArr == null) {
            return null;
        }
        for (int i : iArr) {
            sb.append((char) i);
        }
        return sb.toString();
    }

    private int _toolRfDirectionToInt(RfDirection rfDirection) {
        if (RfDirection.PREVIOUS == rfDirection) {
            return -1;
        }
        return RfDirection.NEXT == rfDirection ? 1 : 0;
    }

    private ScanDvbtRet _toolOpOnlyTypeAndRet(int i) {
        _toolLog("Enter scanOp:" + i);
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, 0};
        _toolExchange(i, iArr);
        int i2 = iArr[0];
        _toolLog("ret scanOp:" + i2);
        if (i2 == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        }
        _toolLog("Leave startScan");
        return scanDvbtRet;
    }

    private ScanDvbtRet _toolExchange(int i, int[] iArr) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int i2 = 0;
        if (iArr == null) {
            iArr = new int[]{0};
        }
        int length = iArr.length + 3;
        if (length > EXCHANGE_MAX_LEN) {
            _toolLog("[Error]bigger than EXCHANGE_MAX_LEN");
            return ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        }
        int[] iArr2 = new int[length];
        iArr2[0] = length;
        iArr2[1] = 3;
        iArr2[2] = i;
        int i3 = 0;
        for (int i4 = 3; i4 < length; i4++) {
            iArr2[i4] = iArr[i3];
            i3++;
        }
        int iScanDvbtExchangeData_native = TVNativeWrapper.ScanDvbtExchangeData_native(iArr2);
        for (int i5 = 3; i5 < length; i5++) {
            iArr[i2] = iArr2[i5];
            i2++;
        }
        if (iScanDvbtExchangeData_native >= 0) {
            return ScanDvbtRet.SCAN_DVBT_RET_OK;
        }
        return ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
    }
}
