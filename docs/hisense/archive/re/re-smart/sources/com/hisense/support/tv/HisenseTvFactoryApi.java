package com.hisense.support.tv;

import android.os.SystemProperties;
import android.util.Log;
import b.a.a.a.a;
import com.mediatek.twoworlds.FactoryService.MtkTvKeymasterKeyAttestation;
import com.mediatek.twoworlds.tv.MtkTvDrmpBase;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class HisenseTvFactoryApi {
    public static final boolean DEBUG = true;
    public static final int E_MTK_TV_FAPI_SYS_FACTORY_CMD_SYS_CALL = 1;
    public static final String HDCP14_KEY_MTK = "HDCP14_KEY";
    public static final String HDCP22_KEY_MTK = "HDCP22_KEY";
    public static final String KM_ATTEST_KEY_MTK = "KM_ATTEST_KEY";
    public static final String MGK_ID_MTK = "MGK_ID";
    public static final String PR25_BGCERT_MTK = "PR25_BGCERT";
    public static final String PR25_DEVCERT_MTK = "PR25_DEVCERT";
    public static final String PR25_KEYBOX_MTK = "PR25_KEYBOX";
    public static final String PR25_PRIV_MTK = "PR25_PRIV";
    public static final String PR25_ZGPRIV_MTK = "PR25_ZGPRIV";
    public static final String PR30_BGCERT_MTK = "PR30_BGCERT";
    public static final String PR30_DEVCERT_MTK = "PR30_DEVCERT";
    public static final String PR30_KEYBOX_MTK = "PR30_KEYBOX";
    public static final String PR30_PRIV_MTK = "PR30_PRIV";
    public static final String PR30_ZGPRIV_MTK = "PR30_ZGPRIV";
    public static final String PR40_BGCERT_MTK = "PR_BGCERT";
    public static final String PR40_KEYBOX_MTK = "PR_KEYBOX";
    public static final String PR40_ZGPRIV_MTK = "PR_ZGPRIV";
    public static final String QDAKeyNamefilePath = "/perm/keyname/qda000.txt";
    public static final String QDA_KEY_MTK = "QDA_KEY";
    public static final int RESULT_SUCCESS = 0;
    public static final String TAG = "TV_HisenseTvFApiKey";
    public static final String WV_KEYBOX_MTK = "WVkeybox";
    public static final int factoryLogFileMaxLength = 512000;
    public static final String factoryLogPath = "/data/vendor/3rd_rw/factory/hisensefactory.log";
    public static final String factoryServicePath = "/factory/bin/factory_service";
    public static final String playreadykey40BGCERTSrcName = "bgroupcert.dat";
    public static final String playreadykey40ZGPRIVSrcName = "zgpriv.dat";

    /* JADX INFO: renamed from: com.hisense.support.tv.HisenseTvFactoryApi$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID = new int[FacKeyID.values().length];

        static {
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_WIDEVINE_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_HDCP14_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_HDCP22_ID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_ATTEST_ID.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_NETFLIX2_ID.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_NETFLIX50_ID.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_PLAYREADY25_ID.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_PLAYREADY30_ID.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_PLAYREADY40_ID.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.E_MTK_FAPI_SYSTEM_KEY_KEYMASTER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_DTCPIP_ID.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_MARLIN_ID.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_SQV000_ID.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_PVR000_ID.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[FacKeyID.KEY_QDA_ID.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    public enum FacKeyID {
        KEY_WIDEVINE_ID,
        KEY_HDCP14_ID,
        KEY_HDCP22_ID,
        KEY_ATTEST_ID,
        KEY_NETFLIX2_ID,
        KEY_NETFLIX50_ID,
        KEY_PLAYREADY25_ID,
        KEY_PLAYREADY30_ID,
        KEY_PLAYREADY40_ID,
        E_MTK_FAPI_SYSTEM_KEY_KEYMASTER,
        KEY_DTCPIP_ID,
        KEY_MARLIN_ID,
        KEY_SQV000_ID,
        KEY_PVR000_ID,
        KEY_QDA_ID
    }

    private String getKeyName(String str) {
        String strSubstring = str.substring(str.lastIndexOf("/") + 1);
        Log.d(TAG, "getKeyName, keyNameWithBin =" + strSubstring);
        int iIndexOf = strSubstring.indexOf(".");
        if (iIndexOf <= 0) {
            iIndexOf = strSubstring.length();
        }
        a.c("getKeyName, keyName=", strSubstring.substring(0, iIndexOf), TAG);
        return strSubstring;
    }

    public boolean checkAttestKey() {
        if (true != isFileExists(factoryServicePath)) {
            return true;
        }
        boolean z = false;
        try {
            if (MtkTvKeymasterKeyAttestation.testEcAttestationChainForHisenseFactoryApp() == 0) {
                Log.d(TAG, "checkAttestKey 1");
                z = true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Log.d(TAG, "checkAttestKey 2");
        return z;
    }

    public boolean checkFairPlayKey() {
        int iCheckFairPlayKey = HisenseTVNativeWrapper.checkFairPlayKey();
        a.b("checkFairPlayKey, result = ", iCheckFairPlayKey, TAG);
        return iCheckFairPlayKey == 0;
    }

    public boolean checkHDCP14Key() {
        return true != isFileExists(factoryServicePath) || HisenseTVNativeWrapper.factoryCheckKey_native(HDCP14_KEY_MTK) == 0;
    }

    public boolean checkHDCP22Key() {
        return true != isFileExists(factoryServicePath) || HisenseTVNativeWrapper.factoryCheckKey_native(HDCP22_KEY_MTK) == 0;
    }

    public boolean checkNetflixKey() {
        return true != isFileExists(factoryServicePath) || HisenseTVNativeWrapper.factoryCheckKey_native(MGK_ID_MTK) == 0;
    }

    public boolean checkPlaready25Key() {
        return true != isFileExists(factoryServicePath) || HisenseTVNativeWrapper.factoryCheckKey_native(PR25_PRIV_MTK) == 0;
    }

    public boolean checkPlaready30Key() {
        return true != isFileExists(factoryServicePath) || HisenseTVNativeWrapper.factoryCheckKey_native(PR30_PRIV_MTK) == 0;
    }

    public boolean checkPlaready40Key() {
        if (true != isFileExists(factoryServicePath)) {
            return true;
        }
        int iCheckPlayready40KeyBox_native = HisenseTVNativeWrapper.checkPlayready40KeyBox_native();
        a.b("checkPlaready40Key retValue = ", iCheckPlayready40KeyBox_native, TAG);
        return iCheckPlayready40KeyBox_native == 0;
    }

    public boolean checkQDAKey() {
        if (true != isFileExists(factoryServicePath)) {
            return true;
        }
        int iCheckQDAKey = HisenseTVNativeWrapper.checkQDAKey();
        a.b("checkQDAKey, result = ", iCheckQDAKey, TAG);
        return iCheckQDAKey == 0;
    }

    public boolean checkWideVineKey() {
        if (true != isFileExists(factoryServicePath)) {
            return true;
        }
        int iCheckWideVineKeyBox_native = HisenseTVNativeWrapper.checkWideVineKeyBox_native();
        a.b("checkWideVineKeyBox retValue = ", iCheckWideVineKeyBox_native, TAG);
        return iCheckWideVineKeyBox_native == 0;
    }

    public int clearQDAKeyInfo() {
        Log.d(TAG, "clearQDAKeyInfo  /vdow/QDA");
        return HisenseTVNativeWrapper.factoryRemoveFile_native("/vdow/QDA");
    }

    public int factoryWriteHdcp14Key(String str, boolean z) {
        Log.d(TAG, "factoryWriteHdcp14Key, srcPath=" + str);
        int iFactoryWriteKey_native = HisenseTVNativeWrapper.factoryWriteKey_native(HDCP14_KEY_MTK, str);
        return iFactoryWriteKey_native == 0 ? HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.KEY_HDCP14_ID), getKeyName(str)) : iFactoryWriteKey_native;
    }

    public int factoryWriteHdcp22Key(String str, boolean z) {
        int iFactoryWriteKey_native = HisenseTVNativeWrapper.factoryWriteKey_native(HDCP22_KEY_MTK, str);
        return iFactoryWriteKey_native == 0 ? HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.KEY_HDCP22_ID), getKeyName(str)) : iFactoryWriteKey_native;
    }

    public int factoryWriteKeyFinish() {
        return HisenseTVNativeWrapper.factoryWriteKeyFinish_native();
    }

    public int factoryWriteKeyMaster(String str, boolean z) {
        int iFactoryWriteAttestationKey_native = HisenseTVNativeWrapper.factoryWriteAttestationKey_native(str);
        return iFactoryWriteAttestationKey_native == 0 ? HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.E_MTK_FAPI_SYSTEM_KEY_KEYMASTER), getKeyName(str)) : iFactoryWriteAttestationKey_native;
    }

    public int factoryWriteNetflix50Key(String str, boolean z) {
        int iFactoryWriteKey_native = HisenseTVNativeWrapper.factoryWriteKey_native(MGK_ID_MTK, str);
        return iFactoryWriteKey_native == 0 ? HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.KEY_NETFLIX50_ID), getKeyName(str)) : iFactoryWriteKey_native;
    }

    public int factoryWriteNetflixKey2(String str, boolean z) {
        return 0;
    }

    public int factoryWritePlayReady25Key(String str, boolean z) {
        return HisenseTVNativeWrapper.factoryWriteKey_native(PR25_PRIV_MTK, str);
    }

    public int factoryWritePlayReady30Key(String str, boolean z) {
        return HisenseTVNativeWrapper.factoryWriteKey_native(PR30_PRIV_MTK, str);
    }

    public int factoryWritePlayReady40Key(String str, boolean z) {
        if (HisenseTVNativeWrapper.factoryWriteKey_native(PR40_BGCERT_MTK, str + "/" + playreadykey40BGCERTSrcName) == 0) {
            if (HisenseTVNativeWrapper.factoryWriteKey_native(PR40_ZGPRIV_MTK, str + "/" + playreadykey40ZGPRIVSrcName) == 0) {
                return HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.KEY_PLAYREADY40_ID), "playready40");
            }
        }
        return -1;
    }

    public int factoryWriteQDAKey(String str, boolean z) {
        if (true != isFileExists(factoryServicePath)) {
            Log.d(TAG, "Some Guys delete this:  /factory/bin/factory_service");
            return -1;
        }
        boolean zIsFileExists = isFileExists(QDAKeyNamefilePath);
        a.a("factoryWriteQDAKey, isFileExist existRet is ", zIsFileExists, TAG);
        if (zIsFileExists) {
            a.b("factoryWriteQDAKey, writeFile writeRet is ", HisenseTVNativeWrapper.factoryWriteFile_native(QDAKeyNamefilePath, "NG"), TAG);
        }
        int iInstallQDAKey = HisenseTVNativeWrapper.installQDAKey(str);
        a.b("factoryWriteQDAKey, retValue =", iInstallQDAKey, TAG);
        if (iInstallQDAKey != 0) {
            return iInstallQDAKey;
        }
        int iWriteKeyNameToFile = writeKeyNameToFile(FacKeyID.KEY_QDA_ID, str);
        a.b("writeKeyNameToFile, result=", iWriteKeyNameToFile, TAG);
        return iWriteKeyNameToFile;
    }

    public int factoryWriteWidevine(String str, boolean z) {
        int iFactoryWriteKey_native = HisenseTVNativeWrapper.factoryWriteKey_native(WV_KEYBOX_MTK, str);
        if (iFactoryWriteKey_native != 0) {
            return iFactoryWriteKey_native;
        }
        int iFactoryWriteFile_native = HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.KEY_WIDEVINE_ID), getKeyName(str));
        String str2 = SystemProperties.get("ro.vt.atsc3");
        if (str2 == null || str2.length() == 0) {
            Log.e(TAG, "atsc3Flag is null, or atsc3Flag.length()=0!!!");
            str2 = "false";
        }
        if (!str2.equals("true")) {
            return iFactoryWriteFile_native;
        }
        int iMountPerm_native = HisenseTVNativeWrapper.mountPerm_native();
        if (iMountPerm_native != 0) {
            return iMountPerm_native;
        }
        int iWriteDrmKey = HisensePlatformConfig.writeDrmKey();
        return iWriteDrmKey == 0 ? HisenseTVNativeWrapper.unmountPerm_native() : iWriteDrmKey;
    }

    public String getKeyNamePath(FacKeyID facKeyID) {
        String str;
        switch (facKeyID) {
            case KEY_WIDEVINE_ID:
                str = "widevine.txt";
                break;
            case KEY_HDCP14_ID:
                str = "hdcp14id.txt";
                break;
            case KEY_HDCP22_ID:
                str = "hdcp22id.txt";
                break;
            case KEY_ATTEST_ID:
                str = "attestation.txt";
                break;
            case KEY_NETFLIX2_ID:
                str = "netflix2.txt";
                break;
            case KEY_NETFLIX50_ID:
                str = "netflix50.txt";
                break;
            case KEY_PLAYREADY25_ID:
                str = "playready25.txt";
                break;
            case KEY_PLAYREADY30_ID:
                str = "playready30.txt";
                break;
            case KEY_PLAYREADY40_ID:
                str = "playready40.txt";
                break;
            case E_MTK_FAPI_SYSTEM_KEY_KEYMASTER:
                str = "keymaster.txt";
                break;
            case KEY_DTCPIP_ID:
                str = "dtcpip.txt";
                break;
            case KEY_MARLIN_ID:
                str = "marlin.txt";
                break;
            case KEY_SQV000_ID:
                str = "sqv000.txt";
                break;
            case KEY_PVR000_ID:
                str = "pvr000.txt";
                break;
            case KEY_QDA_ID:
                str = "qda000.txt";
                break;
            default:
                Log.d(TAG, "FacKeyID's file patch don't exist\n");
                str = "Name Error";
                break;
        }
        return a.a("/perm/keyname/", str);
    }

    public ArrayList<Byte> getQDAUUID() {
        return new MtkTvDrmpBase().getDrmpDeviceID();
    }

    public boolean isFileExists(String str) {
        if (str == null || str.length() == 0) {
            Log.v(TAG, "Error: Invalid parameter!");
            return false;
        }
        if (new File(str).exists()) {
            return true;
        }
        Log.v(TAG, "filePath: " + str + " does NOT exists...\n");
        return false;
    }

    public String readFactoryLog() {
        Log.v(TAG, "readFactoryLog...\n");
        return HisensePlatformConfig.readFactoryLogFile(factoryLogPath);
    }

    public String readKeyNameFromFile(FacKeyID facKeyID) {
        return HisensePlatformConfig.readFile(getKeyNamePath(facKeyID));
    }

    public int removefactorykeyfile(String str) {
        Log.d(TAG, "removefactorykeyfile  " + str);
        return HisenseTVNativeWrapper.factoryRemoveFile_native(str);
    }

    public int sendCmd(String str) {
        return 1;
    }

    public void setFactoryLog(String str) {
        if (str == null || str.length() == 0) {
            Log.v(TAG, "Error: Invalid parameter!");
            return;
        }
        Log.v(TAG, "setFactoryLog log = " + str + "\n");
        boolean zIsFileExists = isFileExists(factoryLogPath);
        String strA = a.a(str, "\n");
        if (!zIsFileExists) {
            Log.d(TAG, "setFactoryLog, writeFactoryLogFileAppend start1...");
            HisensePlatformConfig.writeFactoryLogFileAppend(factoryLogPath, strA, strA.length());
            Log.d(TAG, "setFactoryLog, writeFactoryLogFileAppend end1...");
            return;
        }
        File file = new File(factoryLogPath);
        if (file.isFile() && file.length() > 512000) {
            Log.d(TAG, "setFactoryLog, removeFile start...");
            HisensePlatformConfig.removeFile(factoryLogPath);
            Log.d(TAG, "setFactoryLog, removeFile end...");
        }
        Log.d(TAG, "setFactoryLog, writeFactoryLogFileAppend start2...");
        HisensePlatformConfig.writeFactoryLogFileAppend(factoryLogPath, strA, strA.length());
        Log.d(TAG, "setFactoryLog, writeFactoryLogFileAppend end2...");
    }

    public void syncFs() {
    }

    public int writeKeyNameToFile(FacKeyID facKeyID, String str) {
        String keyNamePath = getKeyNamePath(facKeyID);
        String strTrim = str.trim();
        String strSubstring = strTrim.substring(strTrim.lastIndexOf("/") + 1);
        int iFactoryWriteFile_native = HisenseTVNativeWrapper.factoryWriteFile_native(keyNamePath, strSubstring);
        StringBuilder sb = new StringBuilder();
        sb.append("filePath=");
        sb.append(keyNamePath);
        sb.append(",fileName=");
        sb.append(strSubstring);
        sb.append(", writeRet= ");
        a.b(sb, iFactoryWriteFile_native, TAG);
        return iFactoryWriteFile_native;
    }

    public int writeTvsKeyName(FacKeyID facKeyID, String str) {
        return HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(facKeyID), getKeyName(str));
    }
}
