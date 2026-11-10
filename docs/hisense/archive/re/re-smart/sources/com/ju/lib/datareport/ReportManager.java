package com.ju.lib.datareport;

import android.content.Context;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.ju.lib.datareport.dynamic.DynamicConfigure;
import com.ju.lib.utils.log.LogUtil;
import com.ju.lib.utils.thread.JuThreadFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
public class ReportManager {
    public static volatile ReportManager INSTANCE = null;
    public static final String TAG = "ReportManager";
    public Map<String, String> mBusinessExtParams;
    public Context mContext;
    public String mDeviceId;
    public DynamicConfigure mDynamicConfigure;
    public Executor mExecutor;
    public Map<String, Reporter> mReporters;
    public final String mStrategyUrl;
    public HandlerThread mThread;
    public String mVersion;

    public ReportManager(Context context, String str, String str2, String str3) {
        this.mContext = context.getApplicationContext();
        this.mDeviceId = str;
        this.mVersion = str2;
        this.mStrategyUrl = str3;
        long count = ReportDatabase.getInstance(context).getCount(3);
        LogUtil.d(TAG, "crash count: ", Long.valueOf(count));
        if (count > 0) {
            Reporter.startReportService(context, str, str2, str3);
        }
    }

    public static final ReportManager getInstance(Context context, String str, String str2) {
        if (INSTANCE == null) {
            synchronized (ReportManager.class) {
                if (INSTANCE == null) {
                    if (TextUtils.isEmpty(str)) {
                        throw new IllegalArgumentException("deviceId is empty! ");
                    }
                    INSTANCE = new ReportManager(context, str, str2, "");
                }
            }
        }
        return INSTANCE;
    }

    public Reporter getReporter(String str) {
        Reporter reporter;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("key is empty! ");
        }
        synchronized (this) {
            if (this.mReporters == null) {
                this.mThread = new HandlerThread("REPORT", 10);
                this.mThread.start();
                this.mExecutor = Executors.newCachedThreadPool(new JuThreadFactory("baseLib-ReportManager"));
                this.mReporters = new HashMap();
                reporter = null;
            } else {
                reporter = this.mReporters.get(str);
            }
            if (reporter == null) {
                reporter = new Reporter(this.mContext, this.mThread.getLooper(), this.mExecutor, this.mDeviceId, str, this.mVersion, this.mStrategyUrl);
                LogUtil.i(TAG, "Reporter: " + str);
                this.mReporters.put(str, reporter);
            }
            reporter.setBusinessExtParams(this.mBusinessExtParams);
        }
        return reporter;
    }

    public DynamicConfigure getmDynamicConfigure() {
        return this.mDynamicConfigure;
    }

    public void setBusinessExtParams(Map<String, String> map) {
        synchronized (this) {
            this.mBusinessExtParams = map;
        }
    }

    public ReportManager setDynamicConfigure(DynamicConfigure dynamicConfigure) {
        Map<String, Reporter> map;
        LogUtil.i(TAG, "setDynamicConfigure dynamicConfigure:", dynamicConfigure);
        DynamicConfigure dynamicConfigure2 = this.mDynamicConfigure;
        if (dynamicConfigure2 != null && !dynamicConfigure2.isSame(dynamicConfigure) && (map = this.mReporters) != null) {
            for (Map.Entry<String, Reporter> entry : map.entrySet()) {
                if (entry.getValue() != null) {
                    entry.getValue().updateStrategy();
                }
            }
        }
        this.mDynamicConfigure = dynamicConfigure;
        return INSTANCE;
    }

    public static final ReportManager getInstance(Context context, String str, String str2, String str3) {
        if (INSTANCE == null) {
            synchronized (ReportManager.class) {
                if (INSTANCE == null) {
                    if (!TextUtils.isEmpty(str)) {
                        INSTANCE = new ReportManager(context, str, str2, str3);
                    } else {
                        throw new IllegalArgumentException("deviceId is empty! ");
                    }
                }
            }
        }
        return INSTANCE;
    }

    public static final ReportManager getInstance() {
        return INSTANCE;
    }
}
