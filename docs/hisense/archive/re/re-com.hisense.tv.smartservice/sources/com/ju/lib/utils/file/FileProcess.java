package com.ju.lib.utils.file;

import android.content.Context;
import android.os.Parcelable;
import com.ju.lib.utils.base.JuPlatException;
import com.ju.lib.utils.base.persistence.ParcelablePersistence;
import com.ju.lib.utils.jsonxml.JsonUtil;
import com.ju.lib.utils.jsonxml.JsonUtil1;
import com.ju.lib.utils.log.LogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class FileProcess {
    public static final int BUFF_SIZE = 1048576;
    public static final String TAG = "FileProcess";
    public static Map<Integer, String> sExtraErrors = new HashMap();

    /* JADX INFO: renamed from: com.ju.lib.utils.file.FileProcess$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$ju$lib$utils$file$FileProcess$ParentClassType = new int[ParentClassType.values().length];

        static {
            try {
                $SwitchMap$com$ju$lib$utils$file$FileProcess$ParentClassType[ParentClassType.Parcelable.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$file$FileProcess$ParentClassType[ParentClassType.Serializable.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$file$FileProcess$ParentClassType[ParentClassType.Other.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static final class ErrorCode {
        public static final int FILE_NOT_EXIT = 134;
        public static final int NOT_DIRECTORY = 136;
        public static final int NOT_FILE = 135;
        public static final int PARAM_IS_INVALID = 133;
    }

    enum ParentClassType {
        Serializable,
        Parcelable,
        Other
    }

    static {
        sExtraErrors.put(133, "param is invalid");
        sExtraErrors.put(134, "file not exit");
        sExtraErrors.put(135, "it is not file");
        sExtraErrors.put(136, "it is not directory");
    }

    public static synchronized void checkFile(File file) {
        try {
            if (file == null) {
                throw new JuPlatException(133, sExtraErrors.get(133));
            }
            if (!file.exists()) {
                throw new JuPlatException(134, sExtraErrors.get(134));
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public static void cleanInternalCache(Context context) {
        deleteFileOrDirectory(context.getCacheDir());
    }

    public static synchronized void clearDirectory(File file) {
        checkFile(file);
        if (!file.isDirectory()) {
            throw new JuPlatException(136, sExtraErrors.get(136));
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                deleteFileOrDirectory(file2);
            }
        } else {
            LogUtil.i(TAG, "clearDirectory() childFile is null");
        }
    }

    /* JADX WARN: Code duplicated, block: B:106:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x00f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:96:0x0104 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r9v11, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v31 */
    /* JADX WARN: Type inference failed for: r9v32 */
    /* JADX WARN: Type inference failed for: r9v33 */
    /* JADX WARN: Type inference failed for: r9v34 */
    /* JADX WARN: Type inference failed for: r9v35 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public static boolean copyFile(String str, String str2, boolean z) throws Throwable {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        int i;
        File file = new File(str);
        File file2 = new File(str2);
        if (!file.exists()) {
            LogUtil.i(TAG, "copyFile() source file not exist.");
            return false;
        }
        if (!file.isFile()) {
            LogUtil.i(TAG, "copyFile() source file not a file.");
            return false;
        }
        if (!file.canRead()) {
            LogUtil.i(TAG, "copyFile() source file can't read.");
            return false;
        }
        if (file2.exists() && z && !file2.delete()) {
            LogUtil.i(TAG, "copyFile() before copy File, delete fail");
        }
        ?? r9 = 0;
        fileOutputStream = null;
        FileInputStream fileInputStream2 = null;
        FileOutputStream fileOutputStream2 = null;
        r9 = 0;
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            i = fileInputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, i);
                        }
                        fileOutputStream.flush();
                        try {
                            fileOutputStream.close();
                            r9 = i;
                        } catch (Exception e2) {
                            Object[] objArr = {"copyFile(2)", e2.getMessage()};
                            LogUtil.e(TAG, objArr);
                            r9 = objArr;
                        }
                        try {
                            fileInputStream.close();
                        } catch (Exception e3) {
                            r9 = new Object[]{"copyFile(3)", e3.getMessage()};
                            LogUtil.e(TAG, r9);
                        }
                    } catch (IOException e4) {
                        e = e4;
                        fileInputStream2 = fileInputStream;
                        fileOutputStream = fileOutputStream;
                        try {
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = fileInputStream2;
                            r9 = fileOutputStream;
                            if (r9 != 0) {
                                try {
                                    r9.close();
                                } catch (Exception e5) {
                                    LogUtil.e(TAG, "copyFile(2)", e5.getMessage());
                                }
                            }
                            if (fileInputStream != null) {
                                throw th;
                            }
                            try {
                                fileInputStream.close();
                                throw th;
                            } catch (Exception e6) {
                                LogUtil.e(TAG, "copyFile(3)", e6.getMessage());
                                throw th;
                            }
                        }
                    } catch (Exception e7) {
                        e = e7;
                        fileOutputStream2 = fileOutputStream;
                        LogUtil.e(TAG, "copyFile(1)", e.getMessage());
                        r9 = fileOutputStream2;
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                                r9 = fileOutputStream2;
                            } catch (Exception e8) {
                                Object[] objArr2 = {"copyFile(2)", e8.getMessage()};
                                LogUtil.e(TAG, objArr2);
                                r9 = objArr2;
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e9) {
                                r9 = new Object[]{"copyFile(3)", e9.getMessage()};
                                LogUtil.e(TAG, r9);
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        r9 = fileOutputStream;
                        if (r9 != 0) {
                            r9.close();
                        }
                        if (fileInputStream != null) {
                            throw th;
                        }
                        fileInputStream.close();
                        throw th;
                    }
                } catch (IOException e10) {
                    e = e10;
                    fileOutputStream = null;
                } catch (Exception e11) {
                    e = e11;
                }
            } catch (Throwable th3) {
                th = th3;
                if (r9 != 0) {
                    r9.close();
                }
                if (fileInputStream != null) {
                    throw th;
                }
                fileInputStream.close();
                throw th;
            }
        } catch (IOException e12) {
            e = e12;
            fileOutputStream = null;
        } catch (Exception e13) {
            e = e13;
            fileInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            if (r9 != 0) {
                r9.close();
            }
            if (fileInputStream != null) {
                throw th;
            }
            fileInputStream.close();
            throw th;
        }
        return true;
    }

    public static boolean deleteFile(String str) {
        File file = new File(str);
        if (!file.exists()) {
            LogUtil.i(TAG, "deleteFile() source file not exist.");
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        LogUtil.i(TAG, "deleteFile() source file not a file.");
        return false;
    }

    public static synchronized void deleteFileOrDirectory(File file) {
        checkFile(file);
        if (file.isFile()) {
            LogUtil.i(TAG, "deleteFileOrDirectory() delete file : ", Boolean.valueOf(file.delete()));
        } else {
            if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                    for (File file2 : fileArrListFiles) {
                        deleteFileOrDirectory(file2);
                    }
                    LogUtil.i(TAG, "deleteFileOrDirectory() delete dir : ", Boolean.valueOf(file.delete()));
                }
                LogUtil.i(TAG, "deleteFileOrDirectory() delete empty dir : ", Boolean.valueOf(file.delete()));
                return;
            }
            LogUtil.i(TAG, "deleteFileOrDirectory(),", file.getAbsolutePath(), " is not file nor directory");
        }
    }

    public static synchronized void deleteFileOrDirectoryWithNotBreak(File file) {
        if (!isFileExit(file)) {
            LogUtil.w(TAG, "deleteFileOrDirectoryWithNotBreak() file not exit");
            return;
        }
        if (file.isFile()) {
            LogUtil.i(TAG, "deleteFileOrDirectoryWithNotBreak() delete file : ", Boolean.valueOf(file.delete()));
        } else {
            if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                    for (File file2 : fileArrListFiles) {
                        deleteFileOrDirectoryWithNotBreak(file2);
                    }
                    LogUtil.i(TAG, "deleteFileOrDirectoryWithNotBreak() delete dir : ", Boolean.valueOf(file.delete()));
                }
                LogUtil.i(TAG, "deleteFileOrDirectoryWithNotBreak() delete empty dir : ", Boolean.valueOf(file.delete()));
                return;
            }
            LogUtil.i(TAG, "deleteFileOrDirectoryWithNotBreak(),", file.getAbsolutePath(), " is not file nor directory");
        }
    }

    public static synchronized long getFileOccupiedSpace(File file) {
        checkFile(file);
        return file.length();
    }

    public static synchronized void getFilesOfFolder(String str, List<String> list, int i) {
        try {
            if (str == null) {
                throw new JuPlatException(133, sExtraErrors.get(133));
            }
            getFilesOfFolder(new File(str), list, i);
        } catch (Throwable th) {
            throw th;
        }
    }

    public static synchronized long getFolderOccupiedSpace(File file) {
        checkFile(file);
        File[] fileArrListFiles = file.listFiles();
        long folderOccupiedSpace = 0;
        if (fileArrListFiles == null) {
            return 0L;
        }
        for (int i = 0; i < fileArrListFiles.length; i++) {
            folderOccupiedSpace += fileArrListFiles[i].isDirectory() ? getFolderOccupiedSpace(fileArrListFiles[i]) : fileArrListFiles[i].length();
        }
        return folderOccupiedSpace;
    }

    public static final ParentClassType getParentClassType(Class cls) {
        ParentClassType parentClassType = ParentClassType.Other;
        if (Parcelable.class.isAssignableFrom(cls)) {
            return ParentClassType.Parcelable;
        }
        return Serializable.class.isAssignableFrom(cls) ? ParentClassType.Serializable : parentClassType;
    }

    public static boolean isDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isDirectory();
        }
        return false;
    }

    public static boolean isExists(Context context, String str, FilePathConstants.Type type) {
        return isExists(FilePathConstants.getDirectory(context, type));
    }

    public static boolean isFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0011  */
    public static synchronized boolean isFileExit(File file) {
        boolean z;
        if (file == null) {
            z = false;
        } else if (file.exists()) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public static synchronized boolean mkDir(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        return file.getParentFile().mkdirs();
    }

    public static synchronized String readFromFile(String str) {
        String string;
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        try {
            if (str == null) {
                throw new JuPlatException(133, sExtraErrors.get(133));
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder();
            string = null;
            try {
                try {
                    fileInputStream = new FileInputStream(str);
                    try {
                        bufferedReader2 = new BufferedReader(new InputStreamReader(fileInputStream, Charset.defaultCharset()));
                        while (true) {
                            try {
                                String line = bufferedReader2.readLine();
                                if (line == null) {
                                    break;
                                }
                                sb.append(line);
                            } catch (FileNotFoundException unused) {
                                LogUtil.w(TAG, "readFromFile()  FileNotFoundException path : ", str);
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                if (bufferedReader2 != null) {
                                    try {
                                        bufferedReader2.close();
                                    } catch (IOException e3) {
                                        e = e3;
                                        e.printStackTrace();
                                    }
                                }
                            } catch (IOException e4) {
                                e = e4;
                                e.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                }
                                if (bufferedReader2 != null) {
                                    try {
                                        bufferedReader2.close();
                                    } catch (IOException e6) {
                                        e = e6;
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        string = sb.toString();
                        try {
                            fileInputStream.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                        try {
                            bufferedReader2.close();
                        } catch (IOException e8) {
                            e = e8;
                            e.printStackTrace();
                        }
                    } catch (FileNotFoundException unused2) {
                        bufferedReader2 = null;
                    } catch (IOException e9) {
                        e = e9;
                        bufferedReader2 = null;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = null;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e10) {
                                e10.printStackTrace();
                            }
                        }
                        if (bufferedReader == null) {
                            throw th;
                        }
                        try {
                            bufferedReader.close();
                            throw th;
                        } catch (IOException e11) {
                            e11.printStackTrace();
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (FileNotFoundException unused3) {
                fileInputStream = null;
                bufferedReader2 = null;
            } catch (IOException e12) {
                e = e12;
                fileInputStream = null;
                bufferedReader2 = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                bufferedReader = null;
            }
            LogUtil.d(TAG, "readFromFile()  path : ", str, ", duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        } catch (Throwable th4) {
            throw th4;
        }
        return string;
    }

    /* JADX WARN: Code duplicated, block: B:49:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static synchronized <T> T readObjectFromFile(String str, Type type) {
        T t;
        FileReader fileReader;
        try {
            if (str == null) {
                throw new JuPlatException(0, "readMapFromFilePath path param is null");
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            t = null;
            try {
                fileReader = new FileReader(str);
                try {
                    try {
                        t = (T) JsonUtil1.fromJson(fileReader, type);
                        try {
                            fileReader.close();
                        } catch (IOException e2) {
                            e = e2;
                            e.printStackTrace();
                        }
                    } catch (FileNotFoundException unused) {
                        LogUtil.w(TAG, "readMapFromFilePath()  FileNotFoundException path : ", str);
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e3) {
                                e = e3;
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException unused2) {
                fileReader = null;
            } catch (Throwable th2) {
                th = th2;
                fileReader = null;
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
            LogUtil.d(TAG, "readMapFromFilePath()  path : ", str, ", duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        } catch (Throwable th3) {
            throw th3;
        }
        return t;
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static synchronized <T> T readSerializableFromFile(String str, Class<T> cls) {
        T t;
        FileInputStream fileInputStream;
        try {
            if (cls == null) {
                throw new JuPlatException(133, sExtraErrors.get(133));
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            t = null;
            try {
                try {
                    fileInputStream = new FileInputStream(str);
                    try {
                        t = (T) JsonUtil.parse(fileInputStream, cls);
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            e = e2;
                            e.printStackTrace();
                        }
                    } catch (FileNotFoundException unused) {
                        LogUtil.w(TAG, "readObjectFromFile(stream)  FileNotFoundException path : ", str);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e3) {
                                e = e3;
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException unused2) {
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
            LogUtil.i(TAG, "readObjectFromFile(stream)  duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        } catch (Throwable th3) {
            throw th3;
        }
        return t;
    }

    public static synchronized void saveObjectToFile(Object obj, String str) {
        try {
            if (str == null || obj == null) {
                throw new JuPlatException(133, sExtraErrors.get(133));
            }
            File file = new File(str);
            if (file.exists() && !file.delete()) {
                LogUtil.e(TAG, "saveObjectToFile(), delete exist file ", str, " fail");
                return;
            }
            int iOrdinal = getParentClassType(obj.getClass()).ordinal();
            if (iOrdinal == 0) {
                saveSerializableToFile((Serializable) obj, str);
            } else if (iOrdinal == 1) {
                new ParcelablePersistence().write(str, obj);
            } else if (iOrdinal == 2) {
                throw new JuPlatException(0, "saveObjectToFile() classOfT must extend Serializable or Parcelable");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public static synchronized void saveSerializableToFile(Serializable serializable, String str) {
        if (str == null || serializable == null) {
            throw new JuPlatException(133, sExtraErrors.get(133));
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strSerialize = serializable instanceof String ? (String) serializable : JsonUtil.serialize(serializable);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    new File(file.getParent()).mkdirs();
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(strSerialize.getBytes(Charset.defaultCharset()));
                    fileOutputStream2.close();
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e2) {
                        e = e2;
                        e.printStackTrace();
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    LogUtil.w(TAG, "saveObjectToFile()  exception : ", e.toString());
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e = e4;
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e6) {
            e = e6;
        }
        LogUtil.d(TAG, "saveObjectToFile()  path : ", str, ", duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [int] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public static File zip(String str, String str2) throws Throwable {
        ?? zipOutputStream;
        int length;
        ?? r5;
        File file = new File(str2);
        File file2 = new File(str);
        int i = 2;
        i = 2;
        i = 2;
        i = 2;
        i = 2;
        ?? r6 = 0;
        ?? r7 = 0;
        try {
            try {
                zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
                try {
                    LogUtil.i(TAG, "zip()  srcFile.isFile() =", Boolean.valueOf(file2.isFile()));
                    boolean zIsFile = file2.isFile();
                    try {
                        if (zIsFile) {
                            zipFileOrDirectory(zipOutputStream, file2, "");
                            r5 = zIsFile;
                        } else {
                            File[] fileArrListFiles = file2.listFiles();
                            if (fileArrListFiles != null && (length = fileArrListFiles.length) > 0) {
                                r5 = zIsFile;
                                r5 = length;
                                LogUtil.i(TAG, "zip()  file.count =", Integer.valueOf(fileArrListFiles.length));
                                int i2 = 0;
                                while (true) {
                                    r5 = i2;
                                    if (i2 >= fileArrListFiles.length) {
                                        break;
                                    }
                                    zipFileOrDirectory(zipOutputStream, fileArrListFiles[i2], "");
                                    i2++;
                                }
                                zipOutputStream.close();
                                r6 = r5;
                                return file;
                            }
                        }
                        r5 = zIsFile;
                        r5 = length;
                        r5 = zIsFile;
                        zipOutputStream.close();
                        r6 = r5;
                    } catch (IOException e2) {
                        Object[] objArr = {"zip()2", e2.getMessage()};
                        LogUtil.e(TAG, objArr);
                        r6 = r5;
                        i = objArr;
                    }
                } catch (IOException e3) {
                    e = e3;
                    r7 = zipOutputStream;
                    LogUtil.e(TAG, "zip()1", e.getMessage());
                    r6 = r7;
                    if (r7 != 0) {
                        try {
                            r7.close();
                            r6 = r7;
                        } catch (IOException e4) {
                            Object[] objArr2 = {"zip()2", e4.getMessage()};
                            LogUtil.e(TAG, objArr2);
                            r6 = r7;
                            i = objArr2;
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (zipOutputStream != 0) {
                        try {
                            zipOutputStream.close();
                        } catch (IOException e5) {
                            Object[] objArr3 = new Object[i];
                            objArr3[0] = "zip()2";
                            objArr3[1] = e5.getMessage();
                            LogUtil.e(TAG, objArr3);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                zipOutputStream = r6;
            }
        } catch (IOException e6) {
            e = e6;
        }
        return file;
    }

    public static void zipFileOrDirectory(ZipOutputStream zipOutputStream, File file, String str) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            try {
                if (file.isDirectory()) {
                    File[] fileArrListFiles = file.listFiles();
                    if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                        for (File file2 : fileArrListFiles) {
                            zipFileOrDirectory(zipOutputStream, file2, str + file.getName() + "/");
                        }
                    }
                } else {
                    byte[] bArr = new byte[1048576];
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
                        while (true) {
                            int i = fileInputStream2.read(bArr);
                            if (i == -1) {
                                break;
                            } else {
                                zipOutputStream.write(bArr, 0, i);
                            }
                        }
                        zipOutputStream.closeEntry();
                        fileInputStream = fileInputStream2;
                    } catch (IOException e2) {
                        e = e2;
                        fileInputStream = fileInputStream2;
                        LogUtil.e(TAG, "zipFileOrDirectory(1)", e.getMessage());
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                                return;
                            } catch (IOException e3) {
                                LogUtil.e(TAG, "zipFileOrDirectory(2)", e3.getMessage());
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e4) {
                                LogUtil.e(TAG, "zipFileOrDirectory(2)", e4.getMessage());
                            }
                        }
                        throw th;
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        LogUtil.e(TAG, "zipFileOrDirectory(2)", e5.getMessage());
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e = e6;
        }
    }

    public static boolean isExists(String str) {
        return new File(str).exists();
    }

    public static synchronized void getFilesOfFolder(File file, List<String> list, int i) {
        getFilesOfFolder(file, list, i, true);
    }

    public static synchronized void getFilesOfFolder(File file, List<String> list, int i, boolean z) {
        checkFile(file);
        if ((i == 0 || i == 1) && list != null) {
            if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
                        if (fileArrListFiles[i2].isDirectory() && z) {
                            getFilesOfFolder(fileArrListFiles[i2], list, i);
                        } else if (i != 0) {
                            list.add(fileArrListFiles[i2].getAbsolutePath());
                        } else {
                            list.add(fileArrListFiles[i2].getName());
                        }
                    }
                } else {
                    throw new JuPlatException(1, "fileList is null");
                }
            } else {
                throw new JuPlatException(136, sExtraErrors.get(136));
            }
        } else {
            throw new JuPlatException(133, sExtraErrors.get(133));
        }
    }

    public static synchronized <T> T readObjectFromFile(String str, Class<T> cls) {
        T t;
        try {
            if (cls != null) {
                t = (T) null;
                int iOrdinal = getParentClassType(cls).ordinal();
                if (iOrdinal == 0) {
                    t = (T) readSerializableFromFile(str, cls);
                } else if (iOrdinal == 1) {
                    t = (T) new ParcelablePersistence().read(str, cls.getClassLoader());
                } else if (iOrdinal == 2) {
                    throw new JuPlatException(0, "readObjectFromFile() classOfT must extend Serializable or Parcelable");
                }
            } else {
                throw new JuPlatException(133, sExtraErrors.get(133));
            }
        } catch (Throwable th) {
            throw th;
        }
        return t;
    }
}
