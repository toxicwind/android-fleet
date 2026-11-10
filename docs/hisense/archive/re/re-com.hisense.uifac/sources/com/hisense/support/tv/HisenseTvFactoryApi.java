package com.hisense.support.tv;

import android.os.SystemProperties;
import android.util.Log;
import com.mediatek.twoworlds.FactoryService.MtkTvKeymasterKeyAttestation;
import com.mediatek.twoworlds.tv.MtkTvDrmpBase;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class HisenseTvFactoryApi {

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

    public int sendCmd(String str) {
        return 1;
    }

    public void syncFs() {
    }

    public int factoryWritePlayReady30Key(String str, boolean z) {
        return HisenseTVNativeWrapper.factoryWriteKey_native("PR30_PRIV", str);
    }

    public int factoryWritePlayReady25Key(String str, boolean z) {
        return HisenseTVNativeWrapper.factoryWriteKey_native("PR25_PRIV", str);
    }

    public int factoryWriteHdcp22Key(String str, boolean z) {
        int iFactoryWriteKey_native = HisenseTVNativeWrapper.factoryWriteKey_native("HDCP22_KEY", str);
        return iFactoryWriteKey_native == 0 ? HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.KEY_HDCP22_ID), getKeyName(str)) : iFactoryWriteKey_native;
    }

    public int factoryWriteHdcp14Key(String str, boolean z) {
        Log.d("TV_HisenseTvFApiKey", "factoryWriteHdcp14Key, srcPath=" + str);
        int iFactoryWriteKey_native = HisenseTVNativeWrapper.factoryWriteKey_native("HDCP14_KEY", str);
        return iFactoryWriteKey_native == 0 ? HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.KEY_HDCP14_ID), getKeyName(str)) : iFactoryWriteKey_native;
    }

    private String getKeyName(String str) {
        String strSubstring = str.substring(str.lastIndexOf("/") + 1);
        Log.d("TV_HisenseTvFApiKey", "getKeyName, keyNameWithBin =" + strSubstring);
        int iIndexOf = strSubstring.indexOf(".");
        if (iIndexOf <= 0) {
            iIndexOf = strSubstring.length();
        }
        Log.d("TV_HisenseTvFApiKey", "getKeyName, keyName=" + strSubstring.substring(0, iIndexOf));
        return strSubstring;
    }

    public int factoryWriteKeyMaster(String str, boolean z) {
        int iFactoryWriteAttestationKey_native = HisenseTVNativeWrapper.factoryWriteAttestationKey_native(str);
        return iFactoryWriteAttestationKey_native == 0 ? HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.E_MTK_FAPI_SYSTEM_KEY_KEYMASTER), getKeyName(str)) : iFactoryWriteAttestationKey_native;
    }

    public int factoryWriteNetflix50Key(String str, boolean z) {
        int iFactoryWriteKey_native = HisenseTVNativeWrapper.factoryWriteKey_native("MGK_ID", str);
        return iFactoryWriteKey_native == 0 ? HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.KEY_NETFLIX50_ID), getKeyName(str)) : iFactoryWriteKey_native;
    }

    public int factoryWriteWidevine(String str, boolean z) {
        int iFactoryWriteKey_native = HisenseTVNativeWrapper.factoryWriteKey_native("WVkeybox", str);
        if (iFactoryWriteKey_native != 0) {
            return iFactoryWriteKey_native;
        }
        int iFactoryWriteFile_native = HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.KEY_WIDEVINE_ID), getKeyName(str));
        String str2 = SystemProperties.get("ro.vt.atsc3");
        if (str2 == null || str2.length() == 0) {
            Log.e("TV_HisenseTvFApiKey", "atsc3Flag is null, or atsc3Flag.length()=0!!!");
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

    public int factoryWritePlayReady40Key(String str, boolean z) {
        if (HisenseTVNativeWrapper.factoryWriteKey_native("PR_BGCERT", str + "/bgroupcert.dat") == 0) {
            if (HisenseTVNativeWrapper.factoryWriteKey_native("PR_ZGPRIV", str + "/zgpriv.dat") == 0) {
                return HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(FacKeyID.KEY_PLAYREADY40_ID), "playready40");
            }
        }
        return -1;
    }

    public boolean checkNetflixKey() {
        return true != isFileExists("/factory/bin/factory_service") || HisenseTVNativeWrapper.factoryCheckKey_native("MGK_ID") == 0;
    }

    public boolean checkWideVineKey() {
        if (true != isFileExists("/factory/bin/factory_service")) {
            return true;
        }
        int iCheckWideVineKeyBox_native = HisenseTVNativeWrapper.checkWideVineKeyBox_native();
        Log.d("TV_HisenseTvFApiKey", "checkWideVineKeyBox retValue = " + iCheckWideVineKeyBox_native);
        return iCheckWideVineKeyBox_native == 0;
    }

    public boolean checkHDCP14Key() {
        return true != isFileExists("/factory/bin/factory_service") || HisenseTVNativeWrapper.factoryCheckKey_native("HDCP14_KEY") == 0;
    }

    public boolean checkHDCP22Key() {
        return true != isFileExists("/factory/bin/factory_service") || HisenseTVNativeWrapper.factoryCheckKey_native("HDCP22_KEY") == 0;
    }

    public boolean checkPlaready30Key() {
        return true != isFileExists("/factory/bin/factory_service") || HisenseTVNativeWrapper.factoryCheckKey_native("PR30_PRIV") == 0;
    }

    public boolean checkPlaready25Key() {
        return true != isFileExists("/factory/bin/factory_service") || HisenseTVNativeWrapper.factoryCheckKey_native("PR25_PRIV") == 0;
    }

    public boolean checkAttestKey() {
        boolean z = true;
        if (true != isFileExists("/factory/bin/factory_service")) {
            return true;
        }
        boolean z2 = false;
        try {
            if (MtkTvKeymasterKeyAttestation.testEcAttestationChainForHisenseFactoryApp() == 0) {
                Log.d("TV_HisenseTvFApiKey", "checkAttestKey 1");
            } else {
                z = false;
            }
            z2 = z;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("TV_HisenseTvFApiKey", "checkAttestKey 2");
        return z2;
    }

    public boolean checkPlaready40Key() {
        if (true != isFileExists("/factory/bin/factory_service")) {
            return true;
        }
        int iCheckPlayready40KeyBox_native = HisenseTVNativeWrapper.checkPlayready40KeyBox_native();
        Log.d("TV_HisenseTvFApiKey", "checkPlaready40Key retValue = " + iCheckPlayready40KeyBox_native);
        return iCheckPlayready40KeyBox_native == 0;
    }

    public int writeKeyNameToFile(FacKeyID facKeyID, String str) {
        String keyNamePath = getKeyNamePath(facKeyID);
        String strTrim = str.trim();
        String strSubstring = strTrim.substring(strTrim.lastIndexOf("/") + 1);
        int iFactoryWriteFile_native = HisenseTVNativeWrapper.factoryWriteFile_native(keyNamePath, strSubstring);
        Log.d("TV_HisenseTvFApiKey", "filePath=" + keyNamePath + ",fileName=" + strSubstring + ", writeRet= " + iFactoryWriteFile_native);
        return iFactoryWriteFile_native;
    }

    public int factoryWriteQDAKey(String str, boolean z) {
        if (true != isFileExists("/factory/bin/factory_service")) {
            Log.d("TV_HisenseTvFApiKey", "Some Guys delete this:  /factory/bin/factory_service");
            return -1;
        }
        boolean zIsFileExists = isFileExists("/perm/keyname/qda000.txt");
        Log.d("TV_HisenseTvFApiKey", "factoryWriteQDAKey, isFileExist existRet is " + zIsFileExists);
        if (zIsFileExists) {
            Log.d("TV_HisenseTvFApiKey", "factoryWriteQDAKey, writeFile writeRet is " + HisenseTVNativeWrapper.factoryWriteFile_native("/perm/keyname/qda000.txt", "NG"));
        }
        int iInstallQDAKey = HisenseTVNativeWrapper.installQDAKey(str);
        Log.d("TV_HisenseTvFApiKey", "factoryWriteQDAKey, retValue =" + iInstallQDAKey);
        if (iInstallQDAKey != 0) {
            return iInstallQDAKey;
        }
        int iWriteKeyNameToFile = writeKeyNameToFile(FacKeyID.KEY_QDA_ID, str);
        Log.d("TV_HisenseTvFApiKey", "writeKeyNameToFile, result=" + iWriteKeyNameToFile);
        return iWriteKeyNameToFile;
    }

    public boolean checkQDAKey() {
        if (true != isFileExists("/factory/bin/factory_service")) {
            return true;
        }
        int iCheckQDAKey = HisenseTVNativeWrapper.checkQDAKey();
        Log.d("TV_HisenseTvFApiKey", "checkQDAKey, result = " + iCheckQDAKey);
        return iCheckQDAKey == 0;
    }

    public ArrayList<Byte> getQDAUUID() {
        return new MtkTvDrmpBase().getDrmpDeviceID();
    }

    /* JADX INFO: renamed from: com.hisense.support.tv.HisenseTvFactoryApi$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID;

        static {
            int[] iArr = new int[FacKeyID.values().length];
            $SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID = iArr;
            try {
                iArr[FacKeyID.KEY_WIDEVINE_ID.ordinal()] = 1;
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

    public String getKeyNamePath(FacKeyID facKeyID) {
        String str;
        switch (AnonymousClass1.$SwitchMap$com$hisense$support$tv$HisenseTvFactoryApi$FacKeyID[facKeyID.ordinal()]) {
            case 1:
                str = "widevine.txt";
                break;
            case 2:
                str = "hdcp14id.txt";
                break;
            case 3:
                str = "hdcp22id.txt";
                break;
            case 4:
                str = "attestation.txt";
                break;
            case 5:
                str = "netflix2.txt";
                break;
            case 6:
                str = "netflix50.txt";
                break;
            case 7:
                str = "playready25.txt";
                break;
            case 8:
                str = "playready30.txt";
                break;
            case 9:
                str = "playready40.txt";
                break;
            case 10:
                str = "keymaster.txt";
                break;
            case 11:
                str = "dtcpip.txt";
                break;
            case 12:
                str = "marlin.txt";
                break;
            case 13:
                str = "sqv000.txt";
                break;
            case 14:
                str = "pvr000.txt";
                break;
            case 15:
                str = "qda000.txt";
                break;
            default:
                Log.d("TV_HisenseTvFApiKey", "FacKeyID's file patch don't exist\n");
                str = "Name Error";
                break;
        }
        return "/perm/keyname/" + str;
    }

    public String readKeyNameFromFile(FacKeyID facKeyID) {
        return HisensePlatformConfig.readFile(getKeyNamePath(facKeyID));
    }

    public int writeTvsKeyName(FacKeyID facKeyID, String str) {
        return HisenseTVNativeWrapper.factoryWriteFile_native(getKeyNamePath(facKeyID), getKeyName(str));
    }

    public boolean isFileExists(String str) {
        if (str == null || str.length() == 0) {
            Log.v("TV_HisenseTvFApiKey", "Error: Invalid parameter!");
            return false;
        }
        if (new File(str).exists()) {
            return true;
        }
        Log.v("TV_HisenseTvFApiKey", "filePath: " + str + " does NOT exists...\n");
        return false;
    }

    public void setFactoryLog(String str) {
        if (str == null || str.length() == 0) {
            Log.v("TV_HisenseTvFApiKey", "Error: Invalid parameter!");
            return;
        }
        Log.v("TV_HisenseTvFApiKey", "setFactoryLog log = " + str + "\n");
        String str2 = str + "\n";
        if (!isFileExists("/data/vendor/3rd_rw/factory/hisensefactory.log")) {
            Log.d("TV_HisenseTvFApiKey", "setFactoryLog, writeFactoryLogFileAppend start1...");
            HisensePlatformConfig.writeFactoryLogFileAppend("/data/vendor/3rd_rw/factory/hisensefactory.log", str2, str2.length());
            Log.d("TV_HisenseTvFApiKey", "setFactoryLog, writeFactoryLogFileAppend end1...");
            return;
        }
        File file = new File("/data/vendor/3rd_rw/factory/hisensefactory.log");
        if (file.isFile() && file.length() > 512000) {
            Log.d("TV_HisenseTvFApiKey", "setFactoryLog, removeFile start...");
            HisensePlatformConfig.removeFile("/data/vendor/3rd_rw/factory/hisensefactory.log");
            Log.d("TV_HisenseTvFApiKey", "setFactoryLog, removeFile end...");
        }
        Log.d("TV_HisenseTvFApiKey", "setFactoryLog, writeFactoryLogFileAppend start2...");
        HisensePlatformConfig.writeFactoryLogFileAppend("/data/vendor/3rd_rw/factory/hisensefactory.log", str2, str2.length());
        Log.d("TV_HisenseTvFApiKey", "setFactoryLog, writeFactoryLogFileAppend end2...");
    }

    public String readFactoryLog() {
        Log.v("TV_HisenseTvFApiKey", "readFactoryLog...\n");
        return HisensePlatformConfig.readFactoryLogFile("/data/vendor/3rd_rw/factory/hisensefactory.log");
    }

    public int removefactorykeyfile(String str) {
        Log.d("TV_HisenseTvFApiKey", "removefactorykeyfile  " + str);
        return HisenseTVNativeWrapper.factoryRemoveFile_native(str);
    }
}
