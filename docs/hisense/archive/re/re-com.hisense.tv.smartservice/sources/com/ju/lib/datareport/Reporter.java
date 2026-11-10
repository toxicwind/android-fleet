package com.ju.lib.datareport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import b.a.a.a.a;
import com.ju.lib.datareport.dynamic.DynamicConfigure;
import e.b.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class Reporter {
    public static final String KEY_EVENT_POS = "eventPos";
    public static final String KEY_EVENT_TYPE = "eventType";
    public static final String STRATEGY_URL = "api-gps.hismarttv.com";
    public static final String TAG = "Reporter";
    public static final int TYPE_BUSINESS = 2;
    public static final int TYPE_CRASH = 3;
    public static final int TYPE_DEBUG = 1;
    public static final int TYPE_EXCEPTION = 0;
    public ConstantlyReportExecutor constantlyReportExecutor = new ConstantlyReportExecutor();
    public String mAppKey;
    public Map<String, String> mBusinessExtParams;
    public Context mContext;
    public String mDeviceId;
    public ReportExecutor mReportExcutor;
    public final String mStrategyUrl;
    public String mVersion;

    public interface StrategyListener {
        void onFinished(Strategy strategy);
    }

    public Reporter(Context context, Looper looper, Executor executor, String str, String str2, String str3, String str4) {
        this.mContext = context;
        this.mReportExcutor = new ReportExecutor(context, looper, executor, str, str2, str3, this.constantlyReportExecutor, str4);
        this.mAppKey = str2;
        this.mDeviceId = str;
        this.mVersion = str3;
        if (TextUtils.isEmpty(str4)) {
            this.mStrategyUrl = STRATEGY_URL;
        } else {
            this.mStrategyUrl = str4;
        }
    }

    private void addBusinessExtParams(Map<String, String> map) {
        if (map == null || this.mBusinessExtParams == null) {
            return;
        }
        String map2 = getMap(map, KEY_EVENT_TYPE);
        if (!TextUtils.isEmpty(map2) && Integer.parseInt(map2.substring(0, 1)) == 2) {
            map.putAll(this.mBusinessExtParams);
        }
    }

    private ReportBean getBean(int i, Map<String, String> map) {
        String map2 = getMap(map, KEY_EVENT_TYPE);
        String map3 = getMap(map, KEY_EVENT_POS);
        if (TextUtils.isEmpty(map3) || TextUtils.isEmpty(map2)) {
            return null;
        }
        return new ReportBean(this.mAppKey, i, a.a(map2, map3), new d((Map<?, ?>) map).toString(), System.currentTimeMillis() / 1000);
    }

    public static DynamicConfigure getDynamicConfigure() {
        if (ReportManager.INSTANCE != null) {
            return ReportManager.INSTANCE.getmDynamicConfigure();
        }
        printException("ReportManager.getInstance() is null");
        return null;
    }

    private String getEventPos(Map<String, String> map) {
        return getMap(map, KEY_EVENT_POS);
    }

    private String getEventType(Map<String, String> map) {
        return getMap(map, KEY_EVENT_TYPE);
    }

    private String getMap(Map<String, String> map, String str) {
        String str2 = map.get(str);
        return TextUtils.isEmpty(str2) ? map.get(str.toLowerCase()) : str2;
    }

    public static void printException(String str) {
        new Exception(str).printStackTrace();
    }

    public static void startReportService(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, (Class<?>) ReportService.class);
        intent.putExtra(ReportService.KEY_DEVICE_ID, str);
        intent.putExtra("version", str2);
        intent.putExtra(ReportService.KEY_STRATEGY_HOST, str3);
        Bundle bundle = new Bundle();
        bundle.putParcelable(ReportService.KEY_DYNAMIC_CONFIGURE, getDynamicConfigure());
        intent.putExtras(bundle);
        context.startService(intent);
    }

    public boolean checkEventCode(String str) {
        Set<String> tags;
        Strategy strategy = this.mReportExcutor.getStrategy();
        if (strategy == null || (tags = strategy.getTags()) == null || tags.isEmpty()) {
            return false;
        }
        return tags.contains(str);
    }

    public void constantlyReport(Map<String, String> map) {
        addBusinessExtParams(map);
        ReportBean bean = getBean(map);
        if (bean == null) {
            return;
        }
        if (this.constantlyReportExecutor.isReady() && this.constantlyReportExecutor.isConstantlyReport(bean)) {
            this.constantlyReportExecutor.report(bean);
        } else {
            this.mReportExcutor.report(bean);
        }
    }

    public String getStrategyExtParam(String str) {
        Strategy strategy = this.mReportExcutor.getStrategy();
        if (strategy == null) {
            return null;
        }
        return strategy.getExt().get(str);
    }

    public Set<String> getStrategyExtParamKeys() {
        Strategy strategy = this.mReportExcutor.getStrategy();
        if (strategy == null) {
            return null;
        }
        return strategy.getExt().keySet();
    }

    public boolean isStrategyReady() {
        return this.mReportExcutor.getStrategy() != null;
    }

    public void report(Map<String, String> map) {
        addBusinessExtParams(map);
        ReportBean bean = getBean(map);
        if (bean == null) {
            return;
        }
        this.mReportExcutor.report(bean);
    }

    public void reportCrash(Map<String, String> map) {
        ReportBean bean = getBean(3, map);
        if (bean == null) {
            return;
        }
        ReportDatabase.getInstance(this.mContext).insert(bean);
        startReportService(this.mContext, this.mDeviceId, this.mVersion, this.mStrategyUrl);
    }

    public void setBusinessExtParams(Map<String, String> map) {
        this.mBusinessExtParams = map;
    }

    public void updateStrategy() {
        this.mReportExcutor.updateStrategy();
    }

    public void report(Map<String, String>... mapArr) {
        report(Arrays.asList(mapArr));
    }

    public void report(Collection<Map<String, String>> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Map<String, String> map : collection) {
            addBusinessExtParams(map);
            ReportBean bean = getBean(map);
            if (bean != null) {
                arrayList.add(bean);
            }
        }
        this.mReportExcutor.report(arrayList);
    }

    private ReportBean getBean(Map<String, String> map) {
        String map2 = getMap(map, KEY_EVENT_TYPE);
        if (TextUtils.isEmpty(map2)) {
            return null;
        }
        return getBean(Integer.parseInt(map2.substring(0, 1)), map);
    }
}
