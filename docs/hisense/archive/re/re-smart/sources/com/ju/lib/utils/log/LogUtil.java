package com.ju.lib.utils.log;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import b.a.a.a.a;
import com.google.firebase.installations.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class LogUtil {
    public static final String APP_VERSION = "";
    public static final String FILECONTENT_ENCODE = "UTF-8";
    public static final int LOGCAT_MESSAGE_MAX_LENTH = 3072;
    public static final long LOGFILE_BUFFER_MAX = 131072;
    public static final long LOGFILE_DELAY = 6000;
    public static String LOGFILE_PATH = null;
    public static final long LOGFILE_THREAD_EXIT_DELAY = 300000;
    public static final String LOGPRE_APPVER = "|";
    public static final String LOGPRE_SPLIT = "|";
    public static final String LOGPRE_TIME_FORMAT = "yyyyMMdd-HH:mm:ss:SSS|";
    public static final String LOG_PATH = "/benefm/";
    public static final String LOG_SPLIT = " ";
    public static final int MAX_LOGFILE_LENGTH = 3145728;
    public static final int MAX_LOGFILE_NUMBER = 6;
    public static final int MSG_LOG_FILE_THREAD_EXIT = 2;
    public static final int MSG_SAVE_LOG_FILE = 1;
    public static StringBuilder logFileBuffer;
    public static Handler logFileHandler;
    public static final String TAG = "JHK";
    public static boolean sIsDevelopMode = Log.isLoggable(TAG, 3);

    private static class LogFileHandler extends Handler {
        public LogFileHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                synchronized (LogUtil.class) {
                    LogUtil.logFileHandler.removeMessages(1);
                    LogUtil.flushLogFileBufferLocked();
                    LogUtil.logFileHandler.sendEmptyMessageDelayed(2, 300000L);
                }
                return;
            }
            if (i != 2) {
                return;
            }
            synchronized (LogUtil.class) {
                if (!LogUtil.logFileHandler.hasMessages(1)) {
                    LogUtil.logFileHandler.getLooper().quit();
                    LogUtil.logFileHandler = null;
                }
            }
        }
    }

    public static String buildMessage(Object[] objArr, String... strArr) {
        if (objArr != null) {
            try {
                if (objArr.length != 0) {
                    StringBuilder sb = new StringBuilder();
                    if (strArr != null) {
                        for (String str : strArr) {
                            sb.append(str);
                        }
                    }
                    for (Object obj : objArr) {
                        sb.append(" ");
                        sb.append(obj);
                    }
                    return sb.toString();
                }
            } catch (Exception e2) {
                StringBuilder sbA = a.a("buildMessage error --->> ");
                sbA.append(e2.getMessage());
                Log.e(TAG, sbA.toString());
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static void d(String str, Object... objArr) {
        String strBuildMessage;
        int length;
        if (!sIsDevelopMode || (strBuildMessage = buildMessage(objArr, "|")) == null) {
            return;
        }
        if (strBuildMessage.length() <= 3072) {
            Log.i(str, strBuildMessage);
            return;
        }
        do {
            Log.i(str, strBuildMessage.substring(0, LOGCAT_MESSAGE_MAX_LENTH));
            strBuildMessage = strBuildMessage.substring(LOGCAT_MESSAGE_MAX_LENTH);
            length = strBuildMessage.length();
        } while (length > 3072);
        if (length > 0) {
            Log.i(str, strBuildMessage);
        }
    }

    public static void e(String str, Object... objArr) {
        int length;
        String strBuildMessage = buildMessage(objArr, "|");
        if (strBuildMessage != null) {
            if (strBuildMessage.length() <= 3072) {
                Log.e(str, strBuildMessage);
                return;
            }
            do {
                Log.e(str, strBuildMessage.substring(0, LOGCAT_MESSAGE_MAX_LENTH));
                strBuildMessage = strBuildMessage.substring(LOGCAT_MESSAGE_MAX_LENTH);
                length = strBuildMessage.length();
            } while (length > 3072);
            if (length > 0) {
                Log.e(str, strBuildMessage);
            }
        }
    }

    public static void f(String str, Object... objArr) {
        String strBuildMessage = buildMessage(objArr, getTimePrefix(), a.a(str, "|"), "|");
        if (strBuildMessage != null) {
            putLogFileBuffer(strBuildMessage);
        }
    }

    public static void f_d(String str, Object... objArr) {
        f(str, objArr);
    }

    public static void flushLogFileBufferLocked() {
        synchronized (LogUtil.class) {
            if (logFileBuffer == null) {
                return;
            }
            String string = logFileBuffer.toString();
            logFileBuffer = null;
            File logFile = getLogFile();
            if (logFile != null) {
                writeFile(logFile, string, true);
            } else {
                w(TAG, "get log file failed.");
            }
        }
    }

    public static File getLogFile() throws Throwable {
        if (LOGFILE_PATH == null) {
            String processNameProc = getProcessNameProc();
            String strReplaceAll = processNameProc == null ? "health" : processNameProc.replaceAll(Utils.APP_ID_IDENTIFICATION_SUBSTRING, "_");
            try {
                LOGFILE_PATH = Environment.getExternalStorageDirectory().getCanonicalPath() + "/benefm/" + strReplaceAll + "/";
                File file = new File(LOGFILE_PATH);
                if (!file.exists() && !file.mkdirs()) {
                    w(TAG, "create log directory failed");
                    w(TAG, "path : ", file.getAbsolutePath());
                    return null;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        File file2 = new File(a.a(new StringBuilder(), LOGFILE_PATH, "log.0"));
        if (file2.exists() && file2.length() > 3145728) {
            File file3 = new File(LOGFILE_PATH + "log.5");
            if (file3.exists() && !file3.delete()) {
                w(TAG, "delete log file failed");
                return null;
            }
            for (int i = 4; i >= 0; i--) {
                File file4 = new File(LOGFILE_PATH + "log." + i);
                if (file4.exists()) {
                    if (!file4.renameTo(new File(LOGFILE_PATH + "log." + (i + 1)))) {
                        w(TAG, "rename log file failed");
                        return null;
                    }
                }
            }
        }
        return file2;
    }

    public static String getProcessName() {
        return getProcessNameProc();
    }

    public static String getProcessNameProc() throws Throwable {
        BufferedReader bufferedReader;
        StringBuilder sbA = a.a("/proc/");
        sbA.append(Process.myPid());
        sbA.append("/cmdline");
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(sbA.toString()), "iso-8859-1"));
            try {
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        int i = bufferedReader.read();
                        if (i <= 0) {
                            break;
                        }
                        sb.append((char) i);
                        th = th;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                    String string = sb.toString();
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return string;
                } catch (IOException e4) {
                    e = e4;
                    d(TAG, "get process name (proc) failed: ", e.getMessage());
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
            }
        } catch (IOException e6) {
            e = e6;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getTimePrefix() {
        return new SimpleDateFormat("yyyyMMdd-HH:mm:ss:SSS|", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis()));
    }

    public static void i(String str, Object... objArr) {
        int length;
        String strBuildMessage = buildMessage(objArr, "|");
        if (strBuildMessage != null) {
            if (strBuildMessage.length() <= 3072) {
                Log.i(str, strBuildMessage);
                return;
            }
            do {
                Log.i(str, strBuildMessage.substring(0, LOGCAT_MESSAGE_MAX_LENTH));
                strBuildMessage = strBuildMessage.substring(LOGCAT_MESSAGE_MAX_LENTH);
                length = strBuildMessage.length();
            } while (length > 3072);
            if (length > 0) {
                Log.i(str, strBuildMessage);
            }
        }
    }

    public static void putLogFileBuffer(String str) {
        synchronized (LogUtil.class) {
            if (logFileBuffer == null) {
                logFileBuffer = new StringBuilder(str);
            } else {
                logFileBuffer.append(str);
            }
            logFileBuffer.append("\n");
            boolean z = ((long) logFileBuffer.length()) >= 131072;
            if (logFileHandler == null) {
                HandlerThread handlerThread = new HandlerThread("logfile_thread");
                handlerThread.start();
                logFileHandler = new LogFileHandler(handlerThread.getLooper());
                logFileHandler.sendEmptyMessageDelayed(1, z ? 0L : 6000L);
            } else {
                logFileHandler.removeMessages(2);
                if (z) {
                    logFileHandler.removeMessages(1);
                    logFileHandler.sendEmptyMessage(1);
                } else if (!logFileHandler.hasMessages(1)) {
                    logFileHandler.sendEmptyMessageDelayed(1, 6000L);
                }
            }
        }
    }

    public static void v(String str, Object... objArr) {
        String strBuildMessage;
        int length;
        if (!sIsDevelopMode || (strBuildMessage = buildMessage(objArr, "|")) == null) {
            return;
        }
        if (strBuildMessage.length() <= 3072) {
            Log.i(str, strBuildMessage);
            return;
        }
        do {
            Log.i(str, strBuildMessage.substring(0, LOGCAT_MESSAGE_MAX_LENTH));
            strBuildMessage = strBuildMessage.substring(LOGCAT_MESSAGE_MAX_LENTH);
            length = strBuildMessage.length();
        } while (length > 3072);
        if (length > 0) {
            Log.i(str, strBuildMessage);
        }
    }

    public static void w(String str, Object... objArr) {
        int length;
        String strBuildMessage = buildMessage(objArr, "|");
        if (strBuildMessage != null) {
            if (strBuildMessage.length() <= 3072) {
                Log.w(str, strBuildMessage);
                return;
            }
            do {
                Log.w(str, strBuildMessage.substring(0, LOGCAT_MESSAGE_MAX_LENTH));
                strBuildMessage = strBuildMessage.substring(LOGCAT_MESSAGE_MAX_LENTH);
                length = strBuildMessage.length();
            } while (length > 3072);
            if (length > 0) {
                Log.w(str, strBuildMessage);
            }
        }
    }

    public static void writeFile(File file, String str, boolean z) throws Throwable {
        byte[] bytes;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            w(TAG, "write file failed: ", e2.getMessage());
            bytes = null;
        }
        if (bytes != null) {
            writeFile(file, bytes, z);
        }
    }

    public static void f_d(String str, Throwable th, Object... objArr) {
        f(str, th, objArr);
    }

    public static void f(String str, Throwable th, Object... objArr) {
        String strBuildMessage = buildMessage(objArr, getTimePrefix(), a.a(str, "|"), "|");
        if (strBuildMessage != null) {
            putLogFileBuffer(strBuildMessage);
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            try {
                printWriter.close();
                stringWriter.close();
            } catch (IOException unused) {
                w(TAG, "print stace trace failed");
            }
            putLogFileBuffer(stringWriter.toString());
        }
    }

    public static void writeFile(File file, byte[] bArr, boolean z) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file, z);
                    try {
                        fileOutputStream2.write(bArr);
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (IOException e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        w(TAG, "write file failed: ", e.getMessage());
                        if (fileOutputStream == null) {
                        } else {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException unused2) {
        }
    }

    public static void e(String str, Throwable th, Object... objArr) {
        int length;
        String strBuildMessage = buildMessage(objArr, "|");
        if (strBuildMessage != null) {
            if (strBuildMessage.length() <= 3072) {
                Log.e(str, strBuildMessage, th);
                return;
            }
            do {
                Log.e(str, strBuildMessage.substring(0, LOGCAT_MESSAGE_MAX_LENTH));
                strBuildMessage = strBuildMessage.substring(LOGCAT_MESSAGE_MAX_LENTH);
                length = strBuildMessage.length();
            } while (length > 3072);
            if (length > 0) {
                Log.e(str, strBuildMessage);
            }
            Log.e(str, "", th);
        }
    }

    public static void i(String str, Throwable th, Object... objArr) {
        int length;
        String strBuildMessage = buildMessage(objArr, "|");
        if (strBuildMessage != null) {
            if (strBuildMessage.length() <= 3072) {
                Log.i(str, strBuildMessage, th);
                return;
            }
            do {
                Log.i(str, strBuildMessage.substring(0, LOGCAT_MESSAGE_MAX_LENTH));
                strBuildMessage = strBuildMessage.substring(LOGCAT_MESSAGE_MAX_LENTH);
                length = strBuildMessage.length();
            } while (length > 3072);
            if (length > 0) {
                Log.i(str, strBuildMessage);
            }
            Log.i(str, "", th);
        }
    }

    public static void w(String str, Throwable th, Object... objArr) {
        int length;
        String strBuildMessage = buildMessage(objArr, "|");
        if (strBuildMessage != null) {
            if (strBuildMessage.length() <= 3072) {
                Log.w(str, strBuildMessage, th);
                return;
            }
            do {
                Log.w(str, strBuildMessage.substring(0, LOGCAT_MESSAGE_MAX_LENTH));
                strBuildMessage = strBuildMessage.substring(LOGCAT_MESSAGE_MAX_LENTH);
                length = strBuildMessage.length();
            } while (length > 3072);
            if (length > 0) {
                Log.w(str, strBuildMessage);
            }
            Log.w(str, "", th);
        }
    }

    public static void d(String str, Throwable th, Object... objArr) {
        String strBuildMessage;
        int length;
        if (!sIsDevelopMode || (strBuildMessage = buildMessage(objArr, "|")) == null) {
            return;
        }
        if (strBuildMessage.length() <= 3072) {
            Log.i(str, strBuildMessage, th);
            return;
        }
        do {
            Log.i(str, strBuildMessage.substring(0, LOGCAT_MESSAGE_MAX_LENTH));
            strBuildMessage = strBuildMessage.substring(LOGCAT_MESSAGE_MAX_LENTH);
            length = strBuildMessage.length();
        } while (length > 3072);
        if (length > 0) {
            Log.i(str, strBuildMessage);
        }
        Log.i(str, "", th);
    }

    public static void v(String str, Throwable th, Object... objArr) {
        String strBuildMessage;
        int length;
        if (!sIsDevelopMode || (strBuildMessage = buildMessage(objArr, "|")) == null) {
            return;
        }
        if (strBuildMessage.length() <= 3072) {
            Log.i(str, strBuildMessage, th);
            return;
        }
        do {
            Log.i(str, strBuildMessage.substring(0, LOGCAT_MESSAGE_MAX_LENTH));
            strBuildMessage = strBuildMessage.substring(LOGCAT_MESSAGE_MAX_LENTH);
            length = strBuildMessage.length();
        } while (length > 3072);
        if (length > 0) {
            Log.i(str, strBuildMessage);
        }
        Log.i(str, "", th);
    }
}
