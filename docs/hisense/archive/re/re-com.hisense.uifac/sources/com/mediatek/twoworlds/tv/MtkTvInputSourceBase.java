package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvExceptionBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvInputSourceBase {
    public static final String INPUT_OUTPUT_MAIN = "main";
    public static final String INPUT_OUTPUT_SUB = "sub";
    public static final int INPUT_RET_FAIL = -1;
    public static final int INPUT_RET_OK = 0;
    public static final String INPUT_TYPE_ATV = "atv";
    public static final String INPUT_TYPE_AV = "av";
    public static final String INPUT_TYPE_COMPONENT = "component";
    public static final String INPUT_TYPE_COMPOSITE = "composite";
    public static final String INPUT_TYPE_DTV = "dtv";
    public static final String INPUT_TYPE_HDMI = "hdmi";
    public static final String INPUT_TYPE_SCART = "scart";
    public static final String INPUT_TYPE_SVIDEO = "svideo";
    public static final String INPUT_TYPE_TV = "tv";
    public static final String INPUT_TYPE_VGA = "vga";
    public static final int MAX_SOURCE_NUM = 12;
    public static final String TAG = "TV_MtkTvInputSource";
    private static InputSourceRecord[] mtkTvInputSourceRecord;
    private static int sourceNumber;

    public int init() {
        return 0;
    }

    public enum InputState {
        INPUT_STATE_UNKNOWN(0),
        INPUT_STATE_NORMAL(1),
        INPUT_STATE_PIP(2),
        INPUT_STATE_POP(3);

        private int mInputValue;

        InputState(int i) {
            this.mInputValue = i;
        }

        public int getInputValue() {
            return this.mInputValue;
        }
    }

    public enum InputSourceType {
        INP_SRC_TYPE_UNKNOWN(0),
        INP_SRC_TYPE_TV(1),
        INP_SRC_TYPE_AV(2),
        INP_SRC_TYPE_1394(3),
        INP_SRC_TYPE_VTRL(4),
        INP_SRC_TYPE_MM(5);

        private int mInputValue;

        InputSourceType(int i) {
            this.mInputValue = i;
        }

        public int getInputValue() {
            return this.mInputValue;
        }
    }

    public enum InputDeviceType {
        TV(0),
        COMPOSITE(1),
        SVIDEO(2),
        SCART(3),
        COMPONENT(4),
        HDMI(5),
        VGA(6),
        RESERVED(7),
        MAX_NUM(8);

        private int mInputValue;

        InputDeviceType(int i) {
            this.mInputValue = i;
        }

        public int getInputValue() {
            return this.mInputValue;
        }
    }

    public static class InputSourceRecord {
        public static final int INPS_TYPE_NUMERICAL_COMPONENT = 4;
        public static final int INPS_TYPE_NUMERICAL_COMPOSITE = 1;
        public static final int INPS_TYPE_NUMERICAL_HDMI = 5;
        public static final int INPS_TYPE_NUMERICAL_MAX_NUM = 8;
        public static final int INPS_TYPE_NUMERICAL_RESERVED = 7;
        public static final int INPS_TYPE_NUMERICAL_SCART = 3;
        public static final int INPS_TYPE_NUMERICAL_SVIDEO = 2;
        public static final int INPS_TYPE_NUMERICAL_TV = 0;
        public static final int INPS_TYPE_NUMERICAL_VGA = 6;
        private static final String TAG = "InputSourceRecord";
        private int id;
        private int iidCount;
        private int inputType;
        private int internalIdx;
        private String sourceName;
        private InputSourceType sourceType;

        public InputSourceRecord() {
        }

        public InputSourceRecord(int i, int i2, int i3, String str, InputSourceType inputSourceType, int i4) {
            this.id = i;
            this.internalIdx = i2;
            this.iidCount = i3;
            this.sourceName = str;
            this.sourceType = inputSourceType;
            this.inputType = i4;
        }

        public int getId() {
            return this.id;
        }

        public int getInternalIdx() {
            return this.internalIdx;
        }

        public int getIidCount() {
            return this.iidCount;
        }

        public InputSourceType getSourceType() {
            return this.sourceType;
        }

        public InputDeviceType getInputType() {
            switch (this.inputType) {
                case 0:
                    return InputDeviceType.TV;
                case 1:
                    return InputDeviceType.COMPOSITE;
                case 2:
                    return InputDeviceType.SVIDEO;
                case 3:
                    return InputDeviceType.SCART;
                case 4:
                    return InputDeviceType.COMPONENT;
                case 5:
                    return InputDeviceType.HDMI;
                case 6:
                    return InputDeviceType.VGA;
                case 7:
                    return InputDeviceType.RESERVED;
                default:
                    return InputDeviceType.MAX_NUM;
            }
        }

        public String getSourceName() {
            return this.sourceName;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void setInternalIdx(int i) {
            this.internalIdx = i;
        }

        public void setIidCount(int i) {
            this.iidCount = i;
        }

        public void setSourceType(InputSourceType inputSourceType) {
            this.sourceType = inputSourceType;
        }

        public void setInputType(int i) {
            this.inputType = i;
        }

        public void setSourceName(String str) {
            this.sourceName = str;
        }

        public void clone(InputSourceRecord inputSourceRecord) {
            this.id = inputSourceRecord.id;
            this.internalIdx = inputSourceRecord.internalIdx;
            this.iidCount = inputSourceRecord.iidCount;
            this.sourceName = inputSourceRecord.sourceName;
            this.sourceType = inputSourceRecord.sourceType;
            this.inputType = inputSourceRecord.inputType;
        }
    }

    public MtkTvInputSourceBase() {
        try {
            int inputSourceTotalNumber = getInputSourceTotalNumber();
            sourceNumber = inputSourceTotalNumber;
            mtkTvInputSourceRecord = new InputSourceRecord[inputSourceTotalNumber];
            for (int i = 0; i < sourceNumber; i++) {
                mtkTvInputSourceRecord[i] = new InputSourceRecord();
                int inputSourceRecbyidx = getInputSourceRecbyidx(i, mtkTvInputSourceRecord[i]);
                if (inputSourceRecbyidx != 0) {
                    Log.d(TAG, "!!!!!FAIL to Create a instance of class MtkTvInputSource\n");
                    throw new MtkTvExceptionBase(inputSourceRecbyidx, "getCurrentChannel_native fail");
                }
            }
        } catch (MtkTvExceptionBase e) {
            e.printStackTrace();
        }
    }

    public int getInputSourceTotalNumber() {
        Log.d(TAG, "Enter getInputSourceTotalNumber\n");
        int inputSourceTotalNumber_native = TVNativeWrapper.getInputSourceTotalNumber_native();
        if (inputSourceTotalNumber_native < 0) {
            try {
                Log.d(TAG, "Fail to getInputSourceTotalNumber, get totalnum fail \n");
                throw new MtkTvExceptionBase("getInputSourceTotalNumber fail");
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        return inputSourceTotalNumber_native;
    }

    public int getInputSourceRecbyidx(int i, InputSourceRecord inputSourceRecord) {
        Log.d(TAG, "Enter getInputSourceRecbyidx, idx = " + i + "\n");
        return TVNativeWrapper.getInputSourceRecbyidx_native(i, inputSourceRecord);
    }

    public String getCurrentInputSourceName() {
        return TVNativeWrapper.getCurrentInputSourceName_native();
    }

    public String getCurrentInputSourceName(String str) {
        return TVNativeWrapper.getCurrentInputSourceName_native(str);
    }

    public String getInputSourceNamebySourceid(int i) {
        String inputSourceNamebySourceid_native;
        int i2 = 0;
        while (true) {
            inputSourceNamebySourceid_native = null;
            try {
                if (i2 >= sourceNumber) {
                    break;
                }
                if (i == mtkTvInputSourceRecord[i2].getId()) {
                    inputSourceNamebySourceid_native = TVNativeWrapper.getInputSourceNamebySourceid_native(i);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to getInputSourceNamebySourceid, source_id illegal \n");
            throw new MtkTvExceptionBase("getInputSourceNamebySourceid fail");
        }
        return inputSourceNamebySourceid_native;
    }

    public int changeInputSourcebySourceid(int i) {
        Log.d(TAG, "Enter changeInputSourcebySourceid, source_id = " + i + "\n");
        int iChangeInputSourcebySourceid_native = 0;
        int i2 = 0;
        while (i2 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i2].getId()) {
                    iChangeInputSourcebySourceid_native = TVNativeWrapper.changeInputSourcebySourceid_native(i);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to changeInputSourcebySourceid, source_id illegal \n");
            throw new MtkTvExceptionBase("changeInputSourcebySourceid fail");
        }
        return iChangeInputSourcebySourceid_native;
    }

    public int changeInputSourcebySourceid(int i, String str) {
        Log.d(TAG, "Enter changeInputSourcebySourceid, source_id = " + i + ", designate path = " + str + "\n");
        int iChangeInputSourcebySourceid_native = 0;
        int i2 = 0;
        while (i2 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i2].getId() && (str.equals("main") || str.equals("sub"))) {
                    iChangeInputSourcebySourceid_native = TVNativeWrapper.changeInputSourcebySourceid_native(i, str);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to changeInputSourcebySourceid, source_id or path illegal \n");
            throw new MtkTvExceptionBase("changeInputSourcebySourceid fail");
        }
        return iChangeInputSourcebySourceid_native;
    }

    public boolean isBlockEx(int i) {
        boolean zIsBlockEx_native = false;
        int i2 = 0;
        while (i2 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i2].getId()) {
                    zIsBlockEx_native = TVNativeWrapper.isBlockEx_native(i);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to isBlock, source_id illegal \n");
            throw new MtkTvExceptionBase("isBlock fail");
        }
        Log.d(TAG, "Leave isBlockEx, source_id = " + i + " isblock = " + zIsBlockEx_native + "\n");
        return zIsBlockEx_native;
    }

    public boolean isBlock(int i) {
        boolean zIsBlock_native = false;
        int i2 = 0;
        while (i2 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i2].getId()) {
                    zIsBlock_native = TVNativeWrapper.isBlock_native(i);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to isBlock, source_id illegal \n");
            throw new MtkTvExceptionBase("isBlock fail");
        }
        Log.d(TAG, "Leave isBlock, source_id = " + i + " isblock = " + zIsBlock_native + "\n");
        return zIsBlock_native;
    }

    public int block(int i, boolean z) {
        Log.d(TAG, "Enter block, source_id = " + i + ", block = " + z + "\n");
        int iBlock_native = 0;
        int i2 = 0;
        while (i2 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i2].getId()) {
                    iBlock_native = TVNativeWrapper.block_native(i, z);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to block, source_id illegal \n");
            throw new MtkTvExceptionBase("block fail");
        }
        return iBlock_native;
    }

    public int temp_unblock(int i, boolean z) {
        Log.d(TAG, "Enter block, source_id = " + i + ", block = " + z + "\n");
        int iTemp_unblock_native = 0;
        int i2 = 0;
        while (i2 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i2].getId()) {
                    iTemp_unblock_native = TVNativeWrapper.temp_unblock_native(i, z);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to block, source_id illegal \n");
            throw new MtkTvExceptionBase("block fail");
        }
        return iTemp_unblock_native;
    }

    public boolean isAutoDetectPlugStatus(int i) {
        boolean zIsAutoDetectPlugStatus_native = false;
        int i2 = 0;
        while (i2 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i2].getId()) {
                    zIsAutoDetectPlugStatus_native = TVNativeWrapper.isAutoDetectPlugStatus_native(i);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to isAutoDetectPlugStatus, source_id illegal \n");
            throw new MtkTvExceptionBase("isAutoDetectPlugStatus fail");
        }
        Log.d(TAG, "Leave isAutoDetectPlugStatus, source_id = " + i + " autodetect = " + zIsAutoDetectPlugStatus_native + "\n");
        return zIsAutoDetectPlugStatus_native;
    }

    public int setAutoDetectPlugStatus(int i, boolean z) {
        Log.d(TAG, "Enter setAutoDetectPlugStatus, source_id = " + i + ", needDetect = " + z + "\n");
        int autoDetectPlugStatus_native = 0;
        int i2 = 0;
        while (i2 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i2].getId()) {
                    autoDetectPlugStatus_native = TVNativeWrapper.setAutoDetectPlugStatus_native(i, z);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to setAutoDetectPlugStatus, source_id illegal \n");
            throw new MtkTvExceptionBase("setAutoDetectPlugStatus fail");
        }
        return autoDetectPlugStatus_native;
    }

    public int getInputLabelIdx(int i) {
        int inputLabelIdx_native = 0;
        int i2 = 0;
        while (i2 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i2].getId()) {
                    inputLabelIdx_native = TVNativeWrapper.getInputLabelIdx_native(i);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to getInputLabelIdx, source_id illegal \n");
            throw new MtkTvExceptionBase("getInputLabelIdx fail");
        }
        Log.d(TAG, "Leave getInputLabelIdx, labelidx = " + inputLabelIdx_native + "\n");
        return inputLabelIdx_native;
    }

    public int setInputLabelIdx(int i, int i2) {
        Log.d(TAG, "Enter setInputLabelIdx, source_id = " + i + ", labelidex = " + i2 + "\n");
        int inputLabelIdx_native = 0;
        int i3 = 0;
        while (i3 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i3].getId()) {
                    inputLabelIdx_native = TVNativeWrapper.setInputLabelIdx_native(i, i2);
                    break;
                }
                i3++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i3 >= sourceNumber) {
            Log.d(TAG, "Fail to setInputLabelIdx, source_id illegal \n");
            throw new MtkTvExceptionBase("setInputLabelIdx fail");
        }
        return inputLabelIdx_native;
    }

    public String getInputLabelUserDefName(int i) {
        String str = new String("");
        int i2 = 0;
        while (i2 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i2].getId()) {
                    str = TVNativeWrapper.getInputLabelUserDefName_native(i);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to getInputLabelUserDefName, source_id illegal \n");
            throw new MtkTvExceptionBase("getInputLabelUserDefName fail");
        }
        Log.d(TAG, "Leave getInputLabelUserDefName, source_id = " + i + " userdefname = " + str + "\n");
        return str;
    }

    public int setInputLabelUserDefName(int i, String str) {
        Log.d(TAG, "Enter setInputLabelUserDefName, source_id = " + i + ", userdef = " + str + "\n");
        int inputLabelUserDefName_native = 0;
        int i2 = 0;
        while (i2 < sourceNumber) {
            try {
                if (i == mtkTvInputSourceRecord[i2].getId()) {
                    inputLabelUserDefName_native = TVNativeWrapper.setInputLabelUserDefName_native(i, str);
                    break;
                }
                i2++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i2 >= sourceNumber) {
            Log.d(TAG, "Fail to setInputLabelUserDefName, source_id illegal \n");
            throw new MtkTvExceptionBase("setInputLabelUserDefName fail");
        }
        return inputLabelUserDefName_native;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0032  */
    /* JADX WARN: Code duplicated, block: B:18:0x0033 A[Catch: MtkTvExceptionBase -> 0x0040, TryCatch #0 {MtkTvExceptionBase -> 0x0040, blocks: (B:3:0x0004, B:5:0x0008, B:7:0x0012, B:9:0x001c, B:10:0x0025, B:15:0x002d, B:18:0x0033, B:19:0x003f), top: B:24:0x0004 }] */
    public boolean getExternalDeviceHasSignal(InputDeviceType inputDeviceType, int i) {
        boolean z = false;
        for (int i2 = 0; i2 < sourceNumber; i2++) {
            try {
                if (inputDeviceType == mtkTvInputSourceRecord[i2].getInputType() && i == mtkTvInputSourceRecord[i2].getInternalIdx()) {
                    switch (AnonymousClass1.$SwitchMap$com$mediatek$twoworlds$tv$MtkTvInputSourceBase$InputDeviceType[inputDeviceType.ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            z = true;
                            break;
                    }
                    if (z) {
                        if (z) {
                            Log.d(TAG, "Fail to getExternalDeviceHasSignal, param illegal \n");
                            throw new MtkTvExceptionBase("getExternalDeviceHasSignal fail");
                        }
                        boolean externalDeviceHasSignal_native = TVNativeWrapper.getExternalDeviceHasSignal_native(inputDeviceType, i);
                        Log.d(TAG, "Leave getExternalDeviceHasSignal, hasSignal = " + externalDeviceHasSignal_native + "\n");
                        return externalDeviceHasSignal_native;
                    }
                }
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (z) {
            Log.d(TAG, "Fail to getExternalDeviceHasSignal, param illegal \n");
            throw new MtkTvExceptionBase("getExternalDeviceHasSignal fail");
        }
        boolean externalDeviceHasSignal_native2 = TVNativeWrapper.getExternalDeviceHasSignal_native(inputDeviceType, i);
        Log.d(TAG, "Leave getExternalDeviceHasSignal, hasSignal = " + externalDeviceHasSignal_native2 + "\n");
        return externalDeviceHasSignal_native2;
    }

    /* JADX INFO: renamed from: com.mediatek.twoworlds.tv.MtkTvInputSourceBase$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$mediatek$twoworlds$tv$MtkTvInputSourceBase$InputDeviceType;

        static {
            int[] iArr = new int[InputDeviceType.values().length];
            $SwitchMap$com$mediatek$twoworlds$tv$MtkTvInputSourceBase$InputDeviceType = iArr;
            try {
                iArr[InputDeviceType.COMPOSITE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$mediatek$twoworlds$tv$MtkTvInputSourceBase$InputDeviceType[InputDeviceType.SVIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$mediatek$twoworlds$tv$MtkTvInputSourceBase$InputDeviceType[InputDeviceType.SCART.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$mediatek$twoworlds$tv$MtkTvInputSourceBase$InputDeviceType[InputDeviceType.COMPONENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$mediatek$twoworlds$tv$MtkTvInputSourceBase$InputDeviceType[InputDeviceType.HDMI.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$mediatek$twoworlds$tv$MtkTvInputSourceBase$InputDeviceType[InputDeviceType.VGA.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public int inputSyncStop(String str, boolean z) {
        Log.d(TAG, "Enter inputSyncStop\n");
        return TVNativeWrapper.inputSyncStop_native(str, z);
    }

    public boolean queryConflict(int i, int i2) {
        boolean zQueryConflict_native = false;
        int i3 = 0;
        while (i3 < sourceNumber && i != mtkTvInputSourceRecord[i3].getId()) {
            try {
                i3++;
            } catch (MtkTvExceptionBase e) {
                e.printStackTrace();
            }
        }
        if (i3 >= sourceNumber) {
            Log.d(TAG, "Fail to queryConflict, sourceId1 illegal \n");
            throw new MtkTvExceptionBase("queryConflict fail");
        }
        int i4 = 0;
        while (i4 < sourceNumber && i2 != mtkTvInputSourceRecord[i4].getId()) {
            i4++;
        }
        if (i4 >= sourceNumber) {
            Log.d(TAG, "Fail to queryConflict, sourceId2 illegal \n");
            throw new MtkTvExceptionBase("queryConflict fail");
        }
        zQueryConflict_native = TVNativeWrapper.queryConflict_native(i, i2);
        Log.d(TAG, "Leave queryConflict, sourceId1 = " + i + "sourceId2 = " + i2 + "isConflict = " + zQueryConflict_native + "\n");
        return zQueryConflict_native;
    }

    public int getMhlPortNum() {
        return TVNativeWrapper.getMhlPortNum_native();
    }

    public boolean checkIsMenuTvBlock() {
        Log.d(TAG, "Enter checkIsMenuTvBlock\n");
        return TVNativeWrapper.menuIsTvBlock_native();
    }

    public int setScartAutoJump(boolean z) {
        Log.d(TAG, "Enter setScartAutoJump, enable = " + z + "\n");
        return TVNativeWrapper.setScartAutoJump_native(z);
    }
}
