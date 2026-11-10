package com.mediatek.twoworlds.tv;

import b.a.a.a.a;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvScanDvbtBase {
    public static final int EXCHANGE_GET_TYPE_FVP_ADVISE_SCAN = 10;
    public static final int EXCHANGE_GET_TYPE_FVP_USER_SELECTION_LIST = 9;
    public static final int EXCHANGE_GET_TYPE_FVP_USER_SELECTION_QUESTION = 8;
    public static final int EXCHANGE_GET_TYPE_FVP_VALID_ANSWER_TOKEN = 11;
    public static final int EXCHANGE_GET_TYPE_IS_CUSTOM_SORTING = 7;
    public static final int EXCHANGE_GET_TYPE_RF_INFO = 1;
    public static final int EXCHANGE_GET_TYPE_UI_OP_ITA_GROUP = 4;
    public static final int EXCHANGE_GET_TYPE_UI_OP_LCNV2_CH_LST = 6;
    public static final int EXCHANGE_GET_TYPE_UI_OP_MASK = 2;
    public static final int EXCHANGE_GET_TYPE_UI_OP_NOR_FAV_NWK = 5;
    public static final int EXCHANGE_GET_TYPE_UI_OP_UK_REGION = 3;
    public static final int EXCHANGE_GET_TYPE_UNKNOWN = 0;
    public static final int EXCHANGE_HEADER_LEN = 3;
    public static final int EXCHANGE_HEADER_LEN_IDX = 1;
    public static final int EXCHANGE_HEADER_TYPE_IDX = 2;
    public static final int EXCHANGE_MAX_LEN = 251;
    public static final int EXCHANGE_PAYLOAD_1ST_IDX = 3;
    public static final int EXCHANGE_RF_DIRECTION_CURRENT = 0;
    public static final int EXCHANGE_RF_DIRECTION_NEXT = 1;
    public static final int EXCHANGE_RF_DIRECTION_PREVIOUS = -1;
    public static final int EXCHANGE_SET_TYPE_AUTO_SCAN = 1002;
    public static final int EXCHANGE_SET_TYPE_BGM_SCAN = 1014;
    public static final int EXCHANGE_SET_TYPE_CANCEL_SCAN = 1001;
    public static final int EXCHANGE_SET_TYPE_DISABLE_AUTO_UPDATE = 1017;
    public static final int EXCHANGE_SET_TYPE_DISABLE_NETWORK_CHANGE = 1015;
    public static final int EXCHANGE_SET_TYPE_FVP_USER_SELECTION_LST = 1016;
    public static final int EXCHANGE_SET_TYPE_LOAD_CONFLICT_LCN = 1013;
    public static final int EXCHANGE_SET_TYPE_MANUAL_FREQ_SCAN = 1010;
    public static final int EXCHANGE_SET_TYPE_QUICK_SCAN = 1011;
    public static final int EXCHANGE_SET_TYPE_RANGE_SCAN = 1005;
    public static final int EXCHANGE_SET_TYPE_RF_SCAN = 1004;
    public static final int EXCHANGE_SET_TYPE_STORE_CONFLICT_LCN = 1012;
    public static final int EXCHANGE_SET_TYPE_UI_OP_ITA_GROUP = 1007;
    public static final int EXCHANGE_SET_TYPE_UI_OP_LCNV2_CH_LST = 1009;
    public static final int EXCHANGE_SET_TYPE_UI_OP_NOR_FAV_NWK = 1008;
    public static final int EXCHANGE_SET_TYPE_UI_OP_UK_REGION = 1006;
    public static final int EXCHANGE_SET_TYPE_UNKNOWN = 1000;
    public static final int EXCHANGE_SET_TYPE_UPDATE_SCAN = 1003;
    public static final int EXCHANGE_TOTAL_LEN_IDX = 0;
    public static final int MAX_USER_SELECITTION_TITLE_LEN = 201;
    public static final String TAG = "MtkTvScanDvbt";
    public MtkTvScanDvbtBaseDebug debugModule = new MtkTvScanDvbtBaseDebug(this, false);

    public class FavNwk {
        public static final int MAX_NETWORK_NAME_LEN = 35;
        public int index;
        public String networkName;

        public FavNwk() {
        }
    }

    public class LCNv2ChannelList {
        public static final int MAX_LCNV2_CHANNEL_LIST_NAME_LEN = 35;
        public String channelListName;
        public int index;

        public LCNv2ChannelList() {
        }
    }

    public class LcnConflictGroup {
        public static final int MAX_LCN_GROUP_CHANNEL_NAME = 35;
        public int LCN;
        public String[] channelName;
        public int groupIdx;

        public LcnConflictGroup() {
        }
    }

    private class MtkTvScanDvbtBaseDebug {
        public RfDirection[] backDoor;
        public RfDirection[] backDoorKey;
        public boolean isDebugMode;
        public int keyNum;
        public MtkTvScanDvbtBase testDvbt;

        private void resetKey() {
            int i = 0;
            while (true) {
                RfDirection[] rfDirectionArr = this.backDoorKey;
                if (i >= rfDirectionArr.length) {
                    this.keyNum = 0;
                    return;
                } else {
                    rfDirectionArr[i] = RfDirection.CURRENT;
                    i++;
                }
            }
        }

        private void testDvbt() {
            testDvbtUiOp();
            testDvbtAllRfInfo();
        }

        private void testDvbtAllRfInfo() {
            RfInfo[] allRf = this.testDvbt.getAllRf();
            MtkTvScanDvbtBase mtkTvScanDvbtBase = this.testDvbt;
            StringBuilder sbA = a.a("allRfInfo:");
            sbA.append(allRf.length);
            mtkTvScanDvbtBase._toolLog(sbA.toString());
            for (int i = 0; i < allRf.length; i++) {
                MtkTvScanDvbtBase mtkTvScanDvbtBase2 = this.testDvbt;
                StringBuilder sbA2 = a.a("RfInfo: ");
                sbA2.append(allRf[i].rfIndex);
                sbA2.append(" ");
                sbA2.append(allRf[i].rfFrequence);
                sbA2.append(" ");
                sbA2.append(allRf[i].rfChannelName);
                mtkTvScanDvbtBase2._toolLog(sbA2.toString());
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
                MtkTvScanDvbtBase mtkTvScanDvbtBase = this.testDvbt;
                StringBuilder sbA = a.a("TargetRegion:");
                sbA.append(targetRegionArrUiOpGetTargetRegion.length);
                mtkTvScanDvbtBase._toolLog(sbA.toString());
                for (int i = 0; i < targetRegionArrUiOpGetTargetRegion.length; i++) {
                    MtkTvScanDvbtBase mtkTvScanDvbtBase2 = this.testDvbt;
                    StringBuilder sbA2 = a.a("targetRegion: ");
                    sbA2.append(targetRegionArrUiOpGetTargetRegion[i].internalIdx);
                    sbA2.append(" ");
                    sbA2.append(targetRegionArrUiOpGetTargetRegion[i].level);
                    sbA2.append(" ");
                    sbA2.append(targetRegionArrUiOpGetTargetRegion[i].primary);
                    sbA2.append(" ");
                    sbA2.append(targetRegionArrUiOpGetTargetRegion[i].secondary);
                    sbA2.append(" ");
                    sbA2.append(targetRegionArrUiOpGetTargetRegion[i].tertiary);
                    sbA2.append(" ");
                    sbA2.append(targetRegionArrUiOpGetTargetRegion[i].name);
                    mtkTvScanDvbtBase2._toolLog(sbA2.toString());
                }
            }
            if (uiOpSituationUiOpGetSituation.lcnConflictPopUp) {
                this.testDvbt._toolLog("lcnConflictPopUp");
                LcnConflictGroup[] lcnConflictGroupArrUiOpGetLcnConflictGroup = this.testDvbt.uiOpGetLcnConflictGroup();
                MtkTvScanDvbtBase mtkTvScanDvbtBase3 = this.testDvbt;
                StringBuilder sbA3 = a.a("LcnConflictGroup:");
                sbA3.append(lcnConflictGroupArrUiOpGetLcnConflictGroup.length);
                mtkTvScanDvbtBase3._toolLog(sbA3.toString());
                for (int i2 = 0; i2 < lcnConflictGroupArrUiOpGetLcnConflictGroup.length; i2++) {
                    MtkTvScanDvbtBase mtkTvScanDvbtBase4 = this.testDvbt;
                    StringBuilder sbA4 = a.a("LcnConflictGroup: ");
                    sbA4.append(lcnConflictGroupArrUiOpGetLcnConflictGroup[i2].groupIdx);
                    sbA4.append(" ");
                    sbA4.append(lcnConflictGroupArrUiOpGetLcnConflictGroup[i2].LCN);
                    mtkTvScanDvbtBase4._toolLog(sbA4.toString());
                    for (int i3 = 0; i3 < lcnConflictGroupArrUiOpGetLcnConflictGroup[i2].channelName.length; i3++) {
                        this.testDvbt._toolLog(lcnConflictGroupArrUiOpGetLcnConflictGroup[i2].channelName[i3]);
                    }
                }
            }
            if (uiOpSituationUiOpGetSituation.favouriteNeteorkPopUp) {
                this.testDvbt._toolLog("favouriteNeteorkPopUp");
                FavNwk[] favNwkArrUiOpGetFavNwk = this.testDvbt.uiOpGetFavNwk();
                MtkTvScanDvbtBase mtkTvScanDvbtBase5 = this.testDvbt;
                StringBuilder sbA5 = a.a("FavNwk:");
                sbA5.append(favNwkArrUiOpGetFavNwk.length);
                mtkTvScanDvbtBase5._toolLog(sbA5.toString());
                for (int i4 = 0; i4 < favNwkArrUiOpGetFavNwk.length; i4++) {
                    MtkTvScanDvbtBase mtkTvScanDvbtBase6 = this.testDvbt;
                    StringBuilder sbA6 = a.a("FavNwk: ");
                    sbA6.append(favNwkArrUiOpGetFavNwk[i4].index);
                    sbA6.append(" ");
                    sbA6.append(favNwkArrUiOpGetFavNwk[i4].networkName);
                    mtkTvScanDvbtBase6._toolLog(sbA6.toString());
                }
            }
            if (uiOpSituationUiOpGetSituation.lcnv2PopUp) {
                this.testDvbt._toolLog("lcnv2PopUp");
                LCNv2ChannelList[] lCNv2ChannelListArrUiOpGetLCNv2ChannelList = this.testDvbt.uiOpGetLCNv2ChannelList();
                MtkTvScanDvbtBase mtkTvScanDvbtBase7 = this.testDvbt;
                StringBuilder sbA7 = a.a("LCNv2ChannelList:");
                sbA7.append(lCNv2ChannelListArrUiOpGetLCNv2ChannelList.length);
                mtkTvScanDvbtBase7._toolLog(sbA7.toString());
                for (int i5 = 0; i5 < lCNv2ChannelListArrUiOpGetLCNv2ChannelList.length; i5++) {
                    MtkTvScanDvbtBase mtkTvScanDvbtBase8 = this.testDvbt;
                    StringBuilder sbA8 = a.a("LCNv2ChannelList: ");
                    sbA8.append(lCNv2ChannelListArrUiOpGetLCNv2ChannelList[i5].index);
                    sbA8.append(" ");
                    sbA8.append(lCNv2ChannelListArrUiOpGetLCNv2ChannelList[i5].channelListName);
                    mtkTvScanDvbtBase8._toolLog(sbA8.toString());
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
                    testDvbtUiOp();
                    testDvbtAllRfInfo();
                }
            }
        }

        public MtkTvScanDvbtBaseDebug(MtkTvScanDvbtBase mtkTvScanDvbtBase, boolean z) {
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
    }

    public enum RfDirection {
        PREVIOUS,
        CURRENT,
        NEXT
    }

    public class RfInfo {
        public static final int MAX_CHANNEL_NAME_LEN = 20;
        public String rfChannelName;
        public int rfFrequence;
        public int rfIndex;

        public RfInfo() {
        }
    }

    public enum ScanDvbtRet {
        SCAN_DVBT_RET_OK,
        SCAN_DVBT_RET_INTERNAL_ERROR
    }

    public class TargetRegion {
        public static final int MAX_REGION_NAME_LEN = 35;
        public int internalIdx;
        public int level;
        public String name;
        public int primary;
        public int secondary;
        public int tertiary;

        public TargetRegion() {
        }
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

    public class UserSelection {
        public static final int MAX_USER_SELECITTION_TEXT_LEN = 41;
        public int index;
        public String text;

        public UserSelection() {
        }
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

    private ScanDvbtRet _toolExchange(int i, int[] iArr) {
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int i2 = 0;
        if (iArr == null) {
            iArr = new int[]{0};
        }
        int length = iArr.length + 3;
        if (length > 251) {
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
        return iScanDvbtExchangeData_native >= 0 ? ScanDvbtRet.SCAN_DVBT_RET_OK : ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toolLog(String str) {
        a.c(str, "\n", TAG);
    }

    private ScanDvbtRet _toolOpOnlyTypeAndRet(int i) {
        _toolLog(a.a("Enter scanOp:", i));
        ScanDvbtRet scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_INTERNAL_ERROR;
        int[] iArr = {0, 0};
        _toolExchange(i, iArr);
        int i2 = iArr[0];
        _toolLog(a.a("ret scanOp:", i2));
        if (i2 == 0) {
            scanDvbtRet = ScanDvbtRet.SCAN_DVBT_RET_OK;
        }
        _toolLog("Leave startScan");
        return scanDvbtRet;
    }

    private int _toolRfDirectionToInt(RfDirection rfDirection) {
        if (RfDirection.PREVIOUS == rfDirection) {
            return -1;
        }
        return RfDirection.NEXT == rfDirection ? 1 : 0;
    }

    public UserSelection[] FVPGetUserSelection() {
        ArrayList arrayList = new ArrayList();
        _toolLog("FVPGetUserSelection enter");
        int i = 0;
        int i2 = 0;
        while (i == 0) {
            int[] iArr = {i, i2};
            int[] iArr2 = new int[iArr.length + 41];
            iArr2[0] = i;
            int i3 = i2 + 1;
            iArr2[1] = i2;
            _toolExchange(9, iArr2);
            int i4 = iArr2[0];
            if (i4 == 0) {
                int[] iArr3 = new int[41];
                for (int i5 = 0; i5 < iArr3.length; i5++) {
                    iArr3[i5] = iArr2[iArr.length + i5];
                }
                UserSelection userSelection = new UserSelection();
                userSelection.text = _toolConvertAsciiArrayToString(iArr3);
                userSelection.index = iArr2[1];
                _toolLog(userSelection.index + " " + userSelection.text);
                arrayList.add(userSelection);
            }
            i2 = i3;
            i = i4;
        }
        UserSelection[] userSelectionArr = new UserSelection[arrayList.size()];
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            userSelectionArr[i6] = (UserSelection) arrayList.get(i6);
        }
        _toolLog("FVPGetUserSelection leave");
        return userSelectionArr;
    }

    public String FVPGetUserSelectionTitle() {
        String str_toolConvertAsciiArrayToString;
        int[] iArr = {0, 0};
        int[] iArr2 = new int[iArr.length + 201];
        _toolLog("FVPGetUserSelectionTitle enter");
        iArr2[0] = 0;
        iArr2[1] = 0;
        _toolExchange(8, iArr2);
        if (iArr2[0] == 0) {
            int[] iArr3 = new int[201];
            for (int i = 0; i < iArr3.length; i++) {
                iArr3[i] = iArr2[iArr.length + i];
            }
            str_toolConvertAsciiArrayToString = _toolConvertAsciiArrayToString(iArr3);
            _toolLog(str_toolConvertAsciiArrayToString);
        } else {
            str_toolConvertAsciiArrayToString = "";
        }
        _toolLog("FVPGetUserSelectionTitle leave");
        return str_toolConvertAsciiArrayToString;
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

    public boolean IsAdviseScan() {
        int[] iArr = {0, 0};
        _toolLog("Enter IsAdviseScan");
        _toolExchange(10, iArr);
        int i = iArr[1];
        StringBuilder sbA = a.a("Leave IsAdviseScan:");
        sbA.append(iArr[1]);
        _toolLog(sbA.toString());
        return i != 0;
    }

    public boolean IsValidAnswerToken() {
        int[] iArr = {0, 0};
        _toolLog("Enter IsValidAnswerToken");
        _toolExchange(11, iArr);
        int i = iArr[1];
        StringBuilder sbA = a.a("Leave IsValidAnswerToken:");
        sbA.append(iArr[1]);
        _toolLog(sbA.toString());
        return i != 0;
    }

    public ScanDvbtRet cancelScan() {
        _toolLog("Enter cancelScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1001);
        _toolLog("Leave cancelScan");
        return scanDvbtRet_toolOpOnlyTypeAndRet;
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
        RfInfo[] rfInfoArr = new RfInfo[arrayList.size()];
        RfInfo[] rfInfoArr2 = new RfInfo[arrayList.size()];
        int i = 0;
        int i2 = ((RfInfo) arrayList.get(0)).rfIndex;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            rfInfoArr2[i3] = (RfInfo) arrayList.get(i3);
            if (i2 > rfInfoArr2[i3].rfIndex) {
                i2 = rfInfoArr2[i3].rfIndex;
                i = i3;
            }
        }
        for (int i4 = 0; i4 < rfInfoArr.length; i4++) {
            rfInfoArr[i4] = rfInfoArr2[(i4 + i) % rfInfoArr.length];
        }
        return rfInfoArr;
    }

    public RfInfo gotoDestinationRf(RfDirection rfDirection) {
        _toolLog(a.a("Enter getRfInfo", rfDirection));
        int i_toolRfDirectionToInt = _toolRfDirectionToInt(rfDirection);
        int[] iArr = new int[20];
        int[] iArr2 = {0, i_toolRfDirectionToInt, 0, 0};
        int[] iArr3 = new int[iArr2.length + iArr.length];
        iArr3[0] = 0;
        iArr3[1] = i_toolRfDirectionToInt;
        _toolExchange(1, iArr3);
        if (iArr3[0] != 0) {
            return null;
        }
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = iArr3[iArr2.length + i];
        }
        StringBuilder sbA = a.a("RF channel int:");
        sbA.append(iArr[0]);
        sbA.append(iArr[1]);
        sbA.append(iArr[2]);
        sbA.append(iArr[3]);
        _toolLog(sbA.toString());
        StringBuilder sbA2 = a.a("RF channel test:");
        sbA2.append(_toolConvertAsciiArrayToString(new int[]{48, 49, 50, 51, 52, 65, 98, 67}));
        _toolLog(sbA2.toString());
        RfInfo rfInfo = new RfInfo();
        rfInfo.rfChannelName = _toolConvertAsciiArrayToString(iArr);
        rfInfo.rfIndex = iArr3[2];
        rfInfo.rfFrequence = iArr3[3];
        StringBuilder sbA3 = a.a("RF channel string:");
        sbA3.append(rfInfo.rfChannelName);
        sbA3.append(" ");
        sbA3.append(rfInfo.rfIndex);
        sbA3.append(" ");
        sbA3.append(rfInfo.rfFrequence);
        _toolLog(sbA3.toString());
        _toolLog("Leave getRfInfo");
        this.debugModule.addDirection(rfDirection);
        return rfInfo;
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

    public ScanDvbtRet startAutoScan() {
        _toolLog("Enter startAutoScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1002);
        _toolLog("Leave startAutoScan");
        return scanDvbtRet_toolOpOnlyTypeAndRet;
    }

    public ScanDvbtRet startBGMScan() {
        _toolLog("Enter startBGMScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1014);
        _toolLog("Leave startBGMScan");
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

    public ScanDvbtRet startQuickScan() {
        _toolLog("Enter startQuickScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1011);
        _toolLog("Leave startQuickScan");
        return scanDvbtRet_toolOpOnlyTypeAndRet;
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

    public ScanDvbtRet startRfScan() {
        _toolLog("Enter startRfScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1004);
        _toolLog("Leave startRfScan");
        return scanDvbtRet_toolOpOnlyTypeAndRet;
    }

    public ScanDvbtRet startUpdateScan() {
        _toolLog("Enter startUpdateScan");
        ScanDvbtRet scanDvbtRet_toolOpOnlyTypeAndRet = _toolOpOnlyTypeAndRet(1003);
        _toolLog("Leave startUpdateScan");
        return scanDvbtRet_toolOpOnlyTypeAndRet;
    }

    public FavNwk[] uiOpGetFavNwk() {
        ArrayList arrayList = new ArrayList();
        UiOpSituation uiOpSituationUiOpGetSituation = uiOpGetSituation();
        _toolLog("uiOpGetFavNwk enter");
        if (uiOpSituationUiOpGetSituation.favouriteNeteorkPopUp) {
            int i = 0;
            int i2 = 0;
            while (i == 0) {
                int[] iArr = {i, i2};
                int[] iArr2 = new int[iArr.length + 35];
                iArr2[0] = i;
                int i3 = i2 + 1;
                iArr2[1] = i2;
                _toolExchange(5, iArr2);
                int i4 = iArr2[0];
                if (i4 == 0) {
                    int[] iArr3 = new int[35];
                    for (int i5 = 0; i5 < iArr3.length; i5++) {
                        iArr3[i5] = iArr2[iArr.length + i5];
                    }
                    FavNwk favNwk = new FavNwk();
                    favNwk.networkName = _toolConvertAsciiArrayToString(iArr3);
                    favNwk.index = iArr2[1];
                    _toolLog(favNwk.index + " " + favNwk.networkName);
                    arrayList.add(favNwk);
                }
                i2 = i3;
                i = i4;
            }
        }
        FavNwk[] favNwkArr = new FavNwk[arrayList.size()];
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            favNwkArr[i6] = (FavNwk) arrayList.get(i6);
        }
        _toolLog("uiOpGetFavNwk leave");
        return favNwkArr;
    }

    public LCNv2ChannelList[] uiOpGetLCNv2ChannelList() {
        ArrayList arrayList = new ArrayList();
        UiOpSituation uiOpSituationUiOpGetSituation = uiOpGetSituation();
        _toolLog("uiOpGetLCNv2ChannelList enter");
        if (uiOpSituationUiOpGetSituation.lcnv2PopUp) {
            int i = 0;
            int i2 = 0;
            while (i == 0) {
                int[] iArr = {i, i2};
                int[] iArr2 = new int[iArr.length + 35];
                iArr2[0] = i;
                int i3 = i2 + 1;
                iArr2[1] = i2;
                _toolExchange(6, iArr2);
                int i4 = iArr2[0];
                if (i4 == 0) {
                    int[] iArr3 = new int[35];
                    for (int i5 = 0; i5 < iArr3.length; i5++) {
                        iArr3[i5] = iArr2[iArr.length + i5];
                    }
                    LCNv2ChannelList lCNv2ChannelList = new LCNv2ChannelList();
                    lCNv2ChannelList.channelListName = _toolConvertAsciiArrayToString(iArr3);
                    lCNv2ChannelList.index = iArr2[1];
                    _toolLog(lCNv2ChannelList.index + " " + lCNv2ChannelList.channelListName);
                    arrayList.add(lCNv2ChannelList);
                }
                i2 = i3;
                i = i4;
            }
        }
        LCNv2ChannelList[] lCNv2ChannelListArr = new LCNv2ChannelList[arrayList.size()];
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            lCNv2ChannelListArr[i6] = (LCNv2ChannelList) arrayList.get(i6);
        }
        _toolLog("uiOpGetLCNv2ChannelList leave");
        return lCNv2ChannelListArr;
    }

    public LcnConflictGroup[] uiOpGetLcnConflictGroup() {
        ArrayList arrayList = new ArrayList();
        UiOpSituation uiOpSituationUiOpGetSituation = uiOpGetSituation();
        _toolLog("uiOpGetLcnConflictGroup enter");
        if (uiOpSituationUiOpGetSituation.lcnConflictPopUp) {
            boolean z = false;
            int i = 0;
            int i2 = 0;
            while (!z) {
                ArrayList arrayList2 = new ArrayList();
                int i3 = 0;
                int i4 = i;
                boolean z2 = z;
                int i5 = 0;
                while (i5 == 0) {
                    int[] iArr = {i5, i2, i3, i4};
                    int[] iArr2 = new int[iArr.length + 35];
                    iArr2[0] = i5;
                    iArr2[1] = i2;
                    iArr2[2] = i3;
                    iArr2[3] = 0;
                    _toolExchange(4, iArr2);
                    i5 = iArr2[0];
                    if (i5 == 0) {
                        int[] iArr3 = new int[35];
                        for (int i6 = 0; i6 < iArr3.length; i6++) {
                            iArr3[i6] = iArr2[iArr.length + i6];
                        }
                        String str_toolConvertAsciiArrayToString = _toolConvertAsciiArrayToString(iArr3);
                        int i7 = iArr2[3];
                        arrayList2.add(str_toolConvertAsciiArrayToString);
                        _toolLog(i7 + " " + str_toolConvertAsciiArrayToString);
                        i4 = i7;
                    } else if (i3 == 0) {
                        z2 = true;
                    }
                    i3++;
                }
                if (!z2) {
                    LcnConflictGroup lcnConflictGroup = new LcnConflictGroup();
                    lcnConflictGroup.LCN = i4;
                    lcnConflictGroup.groupIdx = i2;
                    lcnConflictGroup.channelName = new String[arrayList2.size()];
                    int i8 = 0;
                    while (true) {
                        String[] strArr = lcnConflictGroup.channelName;
                        if (i8 >= strArr.length) {
                            break;
                        }
                        strArr[i8] = (String) arrayList2.get(i8);
                        _toolLog(a.a("LCN Group", i4));
                        i8++;
                    }
                    arrayList.add(lcnConflictGroup);
                }
                i2++;
                z = z2;
                i = i4;
            }
        }
        LcnConflictGroup[] lcnConflictGroupArr = new LcnConflictGroup[arrayList.size()];
        for (int i9 = 0; i9 < lcnConflictGroupArr.length; i9++) {
            lcnConflictGroupArr[i9] = (LcnConflictGroup) arrayList.get(i9);
        }
        _toolLog("uiOpGetLcnConflictGroup leave");
        return lcnConflictGroupArr;
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
            StringBuilder sbA = a.a("mwScanRet OK");
            sbA.append(iArr[1]);
            sbA.append(iArr[2]);
            sbA.append(iArr[3]);
            sbA.append(iArr[4]);
            sbA.append(iArr[5]);
            _toolLog(sbA.toString());
        } else {
            _toolLog("mwScanRet NG");
        }
        _toolLog("uiOpGetSituation leave");
        return uiOpSituation;
    }

    public TargetRegion[] uiOpGetTargetRegion() {
        ArrayList arrayList = new ArrayList();
        UiOpSituation uiOpSituationUiOpGetSituation = uiOpGetSituation();
        _toolLog("uiOpGetTargetRegion enter");
        if (uiOpSituationUiOpGetSituation.targetRegionPopUp) {
            int i = 0;
            int i2 = 0;
            while (i == 0) {
                int[] iArr = {i, i2, 0, 0, 0, 0};
                int[] iArr2 = new int[iArr.length + 35];
                iArr2[0] = i;
                int i3 = i2 + 1;
                iArr2[1] = i2;
                _toolExchange(3, iArr2);
                int i4 = iArr2[0];
                if (i4 == 0) {
                    int[] iArr3 = new int[35];
                    for (int i5 = 0; i5 < iArr3.length; i5++) {
                        iArr3[i5] = iArr2[iArr.length + i5];
                    }
                    TargetRegion targetRegion = new TargetRegion();
                    targetRegion.name = _toolConvertAsciiArrayToString(iArr3);
                    targetRegion.internalIdx = iArr2[1];
                    targetRegion.level = iArr2[2];
                    targetRegion.primary = iArr2[3];
                    targetRegion.secondary = iArr2[4];
                    targetRegion.tertiary = iArr2[5];
                    _toolLog(iArr2[1] + " " + iArr2[2] + " " + iArr2[3] + " " + iArr2[4] + " " + iArr2[5] + " " + targetRegion.name);
                    arrayList.add(targetRegion);
                }
                i2 = i3;
                i = i4;
            }
        }
        TargetRegion[] targetRegionArr = new TargetRegion[arrayList.size()];
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            targetRegionArr[i6] = (TargetRegion) arrayList.get(i6);
        }
        _toolLog("uiOpGetTargetRegion leave");
        return targetRegionArr;
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
                _toolLog(a.a("channel index", i));
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
}
