package com.ju.lib.utils.base;

import android.content.Context;
import android.os.Handler;
import b.a.a.a.a;
import com.ju.lib.utils.log.LogUtil;
import com.ju.lib.utils.system.Process;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes2.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    public static CrashHandler mCrashHandler;
    public Context mContext;
    public Thread.UncaughtExceptionHandler mDefaultHandler;
    public ExceptionObserver mExceptionObserver;
    public Handler mHandler = new Handler();

    public interface ExceptionObserver {
        void onFindException(String str, String str2);
    }

    public static CrashHandler getInstance() {
        if (mCrashHandler == null) {
            mCrashHandler = new CrashHandler();
        }
        return mCrashHandler;
    }

    private boolean handleException(Throwable th) {
        if (th == null) {
            return false;
        }
        LogUtil.i(TAG, "--zyl7a--handleException 1mf2--");
        th.printStackTrace();
        writeExceptionLog(th);
        return true;
    }

    public void init(Context context, ExceptionObserver exceptionObserver) {
        this.mContext = context;
        this.mExceptionObserver = exceptionObserver;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (!handleException(th) && this.mDefaultHandler != null) {
            LogUtil.i(TAG, "--zyl7a--系统默认的异常处理器来处理异常-- ");
            this.mDefaultHandler.uncaughtException(thread, th);
        } else {
            LogUtil.i(TAG, "--zyl7a-- 自定义异常处理器--");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException unused) {
                LogUtil.i(TAG, "--zyl7--Error--");
            }
            Process.quitCurrentProcess();
        }
    }

    public void writeExceptionLog(Throwable th) {
        if (this.mExceptionObserver != null) {
            String string = th.toString();
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            Throwable cause = th.getCause();
            int i = 0;
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
                i++;
                if (i > 1000) {
                    break;
                }
            }
            printWriter.close();
            String string2 = stringWriter.toString();
            LogUtil.i(TAG, a.a(string, string2));
            this.mExceptionObserver.onFindException(string, string2);
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.ju.lib.utils.base.CrashHandler.1
            @Override // java.lang.Runnable
            public void run() {
                Process.quitCurrentProcess();
            }
        }, 1500L);
    }
}
