package com.ju.lib.datareport;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.text.TextUtils;
import b.a.a.a.a;
import com.ju.lib.datareport.dynamic.DynamicConfigure;
import com.ju.lib.utils.log.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ReportService extends Service {
    public static final String KEY_DEVICE_ID = "device_id";
    public static final String KEY_DYNAMIC_CONFIGURE = "dynamic_configure";
    public static final String KEY_STRATEGY_HOST = "strategyHost";
    public static final String KEY_VERSION = "version";
    public static final int MSG_WORK = 1;
    public static final String TAG = "ReportService";
    public static final String WAKELOCK_TAG = "com.ju.crash.wakelock";
    public HandlerThread mThread;
    public PowerManager.WakeLock mWakeLock;
    public Handler mWorkHandler;
    public Map<String, Strategy> mStrategys = new HashMap();
    public Handler.Callback mWorkCallback = new Handler.Callback() { // from class: com.ju.lib.datareport.ReportService.1
        private Strategy getStrategy(String str, String str2, String str3, String str4) {
            Strategy strategy = (Strategy) ReportService.this.mStrategys.get(str2);
            if (strategy != null) {
                return strategy;
            }
            Strategy strategy2 = new StrategyHelper(ReportService.this.getApplicationContext(), str2, str, str3, str4).getStrategy();
            ReportService.this.mStrategys.put(str2, strategy2);
            return strategy2;
        }

        private Map<String, List<ReportBean>> transfer(List<ReportBean> list) {
            if (list == null || list.isEmpty()) {
                return Collections.emptyMap();
            }
            HashMap map = new HashMap();
            for (ReportBean reportBean : list) {
                List arrayList = (List) map.get(reportBean.getAppKey());
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    map.put(reportBean.getAppKey(), arrayList);
                }
                arrayList.add(reportBean);
            }
            return map;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 1) {
                return true;
            }
            ReportService.this.mWorkHandler.removeMessages(1);
            Data data = (Data) message.obj;
            String str = data.mDeviceId;
            String str2 = data.mVersion;
            String str3 = data.mStrategyHost;
            StringBuilder sbA = a.a("handleMessage isDynamic = ");
            sbA.append(ReportService.this.isDynamic);
            LogUtil.i(ReportService.TAG, sbA.toString());
            StringBuilder sbA2 = a.a("handleMessage dynamicConfigure = ");
            sbA2.append(ReportService.this.dynamicConfigure);
            LogUtil.i(ReportService.TAG, sbA2.toString());
            List<ReportBean> listQuery = ReportDatabase.getInstance(ReportService.this.getApplicationContext()).query(3);
            LogUtil.i(ReportService.TAG, a.a("crash list = ", listQuery));
            for (Map.Entry<String, List<ReportBean>> entry : transfer(listQuery).entrySet()) {
                String key = entry.getKey();
                LogUtil.i(ReportService.TAG, a.a("key = ", key));
                Strategy strategy = ReportService.this.isDynamic ? getStrategy(str, key, str2, ReportService.this.dynamicConfigure) : getStrategy(str, key, str2, str3);
                if (strategy == null || strategy.getExceptionStrategy() == null || TextUtils.isEmpty(strategy.getExceptionStrategy().getUrl())) {
                    LogUtil.i(ReportService.TAG, a.a("strategy = ", strategy));
                    break;
                }
                ReportPoster reportPoster = new ReportPoster(strategy);
                try {
                    List<ReportBean> value = entry.getValue();
                    if (ReportService.this.isDynamic) {
                        reportPoster.post(strategy.getExceptionStrategy().getUrl(), value, 0);
                    } else {
                        reportPoster.post(strategy.getExceptionStrategy().getUrl(), value);
                    }
                    ReportDatabase.getInstance(ReportService.this.getApplicationContext()).delete(value);
                } catch (Exception e2) {
                    LogUtil.e(ReportService.TAG, e2, "post log error! ");
                }
            }
            if (ReportService.this.mWorkHandler.hasMessages(1)) {
                LogUtil.i(ReportService.TAG, "crash message loop! ");
            } else {
                LogUtil.i(ReportService.TAG, "stop service! ");
                ReportService.this.stopSelf();
            }
            return true;
        }

        private Strategy getStrategy(String str, String str2, String str3, DynamicConfigure dynamicConfigure) {
            Strategy strategy = (Strategy) ReportService.this.mStrategys.get(str2);
            if (strategy != null) {
                return strategy;
            }
            Strategy strategy2 = new StrategyHelper(ReportService.this.getApplicationContext(), str2, str, str3, "").setCustomConfigure(dynamicConfigure).getStrategy();
            ReportService.this.mStrategys.put(str2, strategy2);
            return strategy2;
        }
    };
    public DynamicConfigure dynamicConfigure = null;
    public boolean isDynamic = false;

    private static final class Data {
        public final String mDeviceId;
        public final String mStrategyHost;
        public final String mVersion;

        public Data(String str, String str2, String str3) {
            this.mDeviceId = str;
            this.mVersion = str2;
            this.mStrategyHost = str3;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    @SuppressLint({"InvalidWakeLockTag"})
    public void onCreate() {
        super.onCreate();
        LogUtil.i(TAG, "ReportService onCreate");
        this.mThread = new HandlerThread("REPORT", 10);
        this.mThread.start();
        this.mWorkHandler = new Handler(this.mThread.getLooper(), this.mWorkCallback);
        this.mWakeLock = ((PowerManager) getSystemService("power")).newWakeLock(1, WAKELOCK_TAG);
        this.mWakeLock.setReferenceCounted(false);
        this.mWakeLock.acquire();
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.i(TAG, "ReportService onDestroy");
        this.mThread.quit();
        this.mWakeLock.release();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String stringExtra;
        String stringExtra2;
        String stringExtra3 = null;
        if (intent != null) {
            stringExtra3 = intent.getStringExtra(KEY_DEVICE_ID);
            stringExtra2 = intent.getStringExtra("version");
            stringExtra = intent.getStringExtra(KEY_STRATEGY_HOST);
            this.dynamicConfigure = (DynamicConfigure) intent.getParcelableExtra(KEY_DYNAMIC_CONFIGURE);
            StringBuilder sbA = a.a("onStartCommand dynamicConfigure = ");
            sbA.append(this.dynamicConfigure);
            LogUtil.i(TAG, sbA.toString());
            DynamicConfigure dynamicConfigure = this.dynamicConfigure;
            if (dynamicConfigure != null) {
                this.isDynamic = dynamicConfigure.isDynamic();
            }
        } else {
            stringExtra = "";
            stringExtra2 = null;
        }
        LogUtil.i(TAG, "deviceId = " + stringExtra3 + ", version = " + stringExtra2);
        if (TextUtils.isEmpty(stringExtra3)) {
            stopSelf();
        } else {
            this.mWorkHandler.obtainMessage(1, new Data(stringExtra3, stringExtra2, stringExtra)).sendToTarget();
        }
        super.onStartCommand(intent, i, i2);
        return 3;
    }
}
