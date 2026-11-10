package com.ju.lib.utils.file;

import android.content.Context;
import android.os.Environment;
import b.a.a.a.a;
import com.ju.lib.utils.log.LogUtil;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class FilePathConstants {

    /* JADX INFO: renamed from: com.ju.lib.utils.file.FilePathConstants$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$ju$lib$utils$file$FilePathConstants$Type = new int[Type.values().length];

        static {
            try {
                $SwitchMap$com$ju$lib$utils$file$FilePathConstants$Type[Type.HEADIMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$file$FilePathConstants$Type[Type.OTHER_IMAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$file$FilePathConstants$Type[Type.HEADIMAGE_SD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$file$FilePathConstants$Type[Type.LOG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$file$FilePathConstants$Type[Type.DOWNLOAD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$file$FilePathConstants$Type[Type.ECG.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'SDCARD_TESTSO' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    private static final class FirstLevelDirectoryType {
        public static final /* synthetic */ FirstLevelDirectoryType[] $VALUES;
        public static final FirstLevelDirectoryType CACHE;
        public static final FirstLevelDirectoryType CURRENT_APPLICAITON;
        public static final FirstLevelDirectoryType SDCARD_TESTSO;
        public int index;
        public String nameId;

        static {
            StringBuilder sb = new StringBuilder();
            sb.append(FilePathConstants.getSDPath());
            sb.append(File.separator);
            sb.append("benefm");
            SDCARD_TESTSO = new FirstLevelDirectoryType("SDCARD_TESTSO", 0, a.a(sb, File.separator, "testso"), 1);
            CURRENT_APPLICAITON = new FirstLevelDirectoryType("CURRENT_APPLICAITON", 1, null, 2);
            CACHE = new FirstLevelDirectoryType("CACHE", 2, FilePathConstants.getDownloadCachePath(), 3);
            $VALUES = new FirstLevelDirectoryType[]{SDCARD_TESTSO, CURRENT_APPLICAITON, CACHE};
        }

        public FirstLevelDirectoryType(String str, int i, String str2, int i2) {
            super(str, i);
            this.nameId = str2;
            this.index = i2;
        }

        public static FirstLevelDirectoryType valueOf(String str) {
            return (FirstLevelDirectoryType) Enum.valueOf(FirstLevelDirectoryType.class, str);
        }

        public static FirstLevelDirectoryType[] values() {
            return (FirstLevelDirectoryType[]) $VALUES.clone();
        }

        public int getIndex() {
            return this.index;
        }

        public String getNameId() {
            return this.nameId;
        }

        public void setNameId(String str) {
            this.nameId = str;
        }
    }

    private enum SecondLevelDirectoryType {
        PHOTO("photos", 1),
        DOWNLOAD("download", 2),
        LOG("log", 3),
        DATA("data", 4);

        public int index;
        public String nameId;

        SecondLevelDirectoryType(String str, int i) {
            this.nameId = str;
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        public String getNameId() {
            return this.nameId;
        }
    }

    private enum ThirdLevelDirectoryType {
        HEADIMAGE("headimage", 1),
        ECG("ecg", 2),
        OTHER("other", 3);

        public int index;
        public String nameId;

        ThirdLevelDirectoryType(String str, int i) {
            this.nameId = str;
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        public String getNameId() {
            return this.nameId;
        }
    }

    public enum Type {
        HEADIMAGE,
        OTHER_IMAGE,
        HEADIMAGE_SD,
        LOG,
        DOWNLOAD,
        ECG,
        OTHER
    }

    public static synchronized String checkPath(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.e("FilePathConstants", "checkPath() path.mkdirs() fail");
        }
        String absolutePath = file.getAbsolutePath();
        LogUtil.i("FilePathConstants", "checkPath() str : ", str, "   ret : ", absolutePath);
        return absolutePath;
    }

    public static String constructFileFullPath(Context context, String str, Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(getDirectory(context, type));
        return a.a(sb, File.separator, str);
    }

    public static synchronized String getCurrentApplicationPath(Context context) {
        return context == null ? null : context.getFilesDir().getAbsolutePath();
    }

    public static synchronized String getCurrentApplicationSharePreferencePath(Context context) {
        String str;
        if (context == null) {
            str = null;
        } else {
            str = "/data/data/" + context.getPackageName().toString() + "/shared_prefs";
        }
        return str;
    }

    public static String getDirectory(Context context, Type type) {
        if (context == null) {
            return null;
        }
        int iOrdinal = type.ordinal();
        if (iOrdinal == 0) {
            return getPath(FirstLevelDirectoryType.CURRENT_APPLICAITON, SecondLevelDirectoryType.PHOTO, ThirdLevelDirectoryType.HEADIMAGE, context);
        }
        if (iOrdinal == 1) {
            return getPath(FirstLevelDirectoryType.CURRENT_APPLICAITON, SecondLevelDirectoryType.PHOTO, ThirdLevelDirectoryType.OTHER, context);
        }
        if (iOrdinal == 2) {
            return getPath(FirstLevelDirectoryType.SDCARD_TESTSO, SecondLevelDirectoryType.PHOTO, null, context);
        }
        if (iOrdinal == 3) {
            return getPath(FirstLevelDirectoryType.SDCARD_TESTSO, SecondLevelDirectoryType.LOG, null, context);
        }
        if (iOrdinal != 4) {
            return iOrdinal != 5 ? "" : getPath(FirstLevelDirectoryType.SDCARD_TESTSO, SecondLevelDirectoryType.DATA, ThirdLevelDirectoryType.ECG, context);
        }
        return getPath(FirstLevelDirectoryType.SDCARD_TESTSO, SecondLevelDirectoryType.DOWNLOAD, null, context);
    }

    public static synchronized String getDownloadCachePath() {
        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
        if (downloadCacheDirectory == null) {
            return null;
        }
        if (!downloadCacheDirectory.canWrite()) {
            return null;
        }
        return downloadCacheDirectory.toString();
    }

    public static synchronized String getFirstLevelDirectory(Context context, FirstLevelDirectoryType firstLevelDirectoryType) {
        if (firstLevelDirectoryType == FirstLevelDirectoryType.CURRENT_APPLICAITON) {
            String currentApplicationPath = getCurrentApplicationPath(context);
            if (currentApplicationPath == null) {
                return null;
            }
            FirstLevelDirectoryType.CURRENT_APPLICAITON.setNameId(currentApplicationPath);
        }
        return firstLevelDirectoryType.getNameId();
    }

    public static String getHeadPhotosPath(Context context) {
        return getPath(FirstLevelDirectoryType.CURRENT_APPLICAITON, SecondLevelDirectoryType.PHOTO, ThirdLevelDirectoryType.HEADIMAGE, context);
    }

    public static String getLogPath() {
        return getPath(FirstLevelDirectoryType.SDCARD_TESTSO, SecondLevelDirectoryType.LOG, null, null);
    }

    public static String getOtherPhotosPath(Context context) {
        return getPath(FirstLevelDirectoryType.CURRENT_APPLICAITON, SecondLevelDirectoryType.PHOTO, ThirdLevelDirectoryType.OTHER, context);
    }

    public static synchronized String getPath(FirstLevelDirectoryType firstLevelDirectoryType, SecondLevelDirectoryType secondLevelDirectoryType, ThirdLevelDirectoryType thirdLevelDirectoryType, Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        String firstLevelDirectory = getFirstLevelDirectory(context, firstLevelDirectoryType);
        if (firstLevelDirectory == null) {
            LogUtil.e("FilePathConstants()", "getPath() str is null");
            return firstLevelDirectory;
        }
        stringBuffer.append(firstLevelDirectory);
        if (secondLevelDirectoryType == null) {
            return stringBuffer.toString();
        }
        stringBuffer.append(File.separator);
        stringBuffer.append(secondLevelDirectoryType.getNameId());
        if (thirdLevelDirectoryType == null) {
            return checkPath(stringBuffer.toString());
        }
        stringBuffer.append(File.separator);
        stringBuffer.append(thirdLevelDirectoryType.getNameId());
        return checkPath(stringBuffer.toString());
    }

    public static synchronized String getSDPath() {
        String absolutePath;
        absolutePath = null;
        File externalStorageDirectory = Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : null;
        if (externalStorageDirectory != null) {
            absolutePath = externalStorageDirectory.getAbsolutePath();
        }
        return absolutePath;
    }

    public static String getSdCardPhotosPath() {
        return getPath(FirstLevelDirectoryType.SDCARD_TESTSO, SecondLevelDirectoryType.PHOTO, null, null);
    }

    public static String getSoftwareDownloadPath() {
        return getPath(FirstLevelDirectoryType.SDCARD_TESTSO, SecondLevelDirectoryType.DOWNLOAD, null, null);
    }

    public static boolean isWebUrl(String str) {
        return str != null && str.startsWith("http");
    }

    public static FilePathModel parseUri(Context context, String str, Type type) {
        String strConstructFileFullPath;
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(47);
        if (iLastIndexOf >= 0) {
            try {
                String strSubstring = str.substring(iLastIndexOf + 1);
                str = strSubstring.endsWith("0") ? strSubstring.substring(0, strSubstring.length() - 1) : strSubstring;
                strConstructFileFullPath = str;
            } catch (Exception e2) {
                LogUtil.e("FilePathConstants.parseUri", str, e2.getMessage());
                return null;
            }
        } else {
            strConstructFileFullPath = constructFileFullPath(context, str, type);
        }
        return new FilePathModel(strConstructFileFullPath, str, type);
    }
}
