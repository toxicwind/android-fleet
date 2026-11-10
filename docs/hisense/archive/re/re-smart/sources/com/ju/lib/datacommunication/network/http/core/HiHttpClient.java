package com.ju.lib.datacommunication.network.http.core;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.HttpConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class HiHttpClient {
    public static final int ERROR_IO = 1001;
    public static final int ERROR_PARSE = 1002;
    public static final int ERROR_SIGNATURE = 1003;
    public static final int ERROR_UNKNOW = -1;
    public static final String HTTP_COMMON_TAG = "HTTP2.";
    public static final int PAUSE_REQUEST_DELAY = 60;
    public static int sPauseAllRequest;
    public Map<HiRequest, ICallback> mQueue = new HashMap();
    public final HttpStack mStack;
    public static int sDelay = SystemProperties.getInt("persist.jhk.debug.netdelay", 60);
    public static List<IListener> sResumeListeners = new ArrayList();
    public static long sMaxResumeRequestDuration = 60000;

    /* JADX INFO: renamed from: com.ju.lib.datacommunication.network.http.core.HiHttpClient$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$ju$lib$datacommunication$network$http$HttpConfig$PauseNetworkRequestConfig$Mode = new int[HttpConfig.PauseNetworkRequestConfig.Mode.values().length];

        static {
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$HttpConfig$PauseNetworkRequestConfig$Mode[HttpConfig.PauseNetworkRequestConfig.Mode.Pause.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$HttpConfig$PauseNetworkRequestConfig$Mode[HttpConfig.PauseNetworkRequestConfig.Mode.PauseWhenStartup.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$HttpConfig$PauseNetworkRequestConfig$Mode[HttpConfig.PauseNetworkRequestConfig.Mode.NotPause.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private interface IListener {
        void onResume();
    }

    public HiHttpClient(HttpStack httpStack) {
        this.mStack = httpStack;
        if (isPauseAllRequest()) {
            synchronized (sResumeListeners) {
                sResumeListeners.add(new IListener() { // from class: com.ju.lib.datacommunication.network.http.core.HiHttpClient.1
                    @Override // com.ju.lib.datacommunication.network.http.core.HiHttpClient.IListener
                    public void onResume() {
                        HiHttpClient.this.resumeHandleQueue();
                    }
                });
                if (sMaxResumeRequestDuration >= 0) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.ju.lib.datacommunication.network.http.core.HiHttpClient.2
                        @Override // java.lang.Runnable
                        public void run() {
                            HiHttpClient.sPauseAllRequest = 0;
                            HiHttpClient.sMaxResumeRequestDuration = -1L;
                            HiHttpClient.resumeRequest();
                        }
                    }, sMaxResumeRequestDuration);
                }
                sMaxResumeRequestDuration = -2L;
            }
        }
        StringBuilder sbA = a.a("sMaxResumeRequestDuration = ");
        sbA.append(sMaxResumeRequestDuration);
        Log.w("HTTP2.HiHttpClient", sbA.toString());
        Log.w("HTTP2.HiHttpClient", "default mDelay = " + sDelay);
        Log.w("HTTP2.HiHttpClient", "default mPauseAllRequest = " + sPauseAllRequest);
    }

    public static boolean isPauseAllRequest() {
        return sPauseAllRequest > 0;
    }

    public static synchronized void pauseIncrease(HttpConfig.PauseNetworkRequestConfig pauseNetworkRequestConfig) {
        sPauseAllRequest++;
        if (sMaxResumeRequestDuration != -1) {
            return;
        }
        if (pauseNetworkRequestConfig.maxResumeRequestDuration < 0) {
            sMaxResumeRequestDuration = 0L;
        } else {
            long j = pauseNetworkRequestConfig.maxResumeRequestDuration;
            if (j > 60000) {
                sMaxResumeRequestDuration = 60000L;
            } else {
                sMaxResumeRequestDuration = j;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeHandleQueue() {
        if (this.mQueue.size() == 0) {
            return;
        }
        StringBuilder sbA = a.a("mQuenue size = ");
        sbA.append(this.mQueue.size());
        Log.w(HTTP_COMMON_TAG, sbA.toString());
        synchronized (this.mQueue) {
            for (Map.Entry<HiRequest, ICallback> entry : this.mQueue.entrySet()) {
                this.mStack.enqueue(entry.getKey(), entry.getValue());
            }
            this.mQueue.clear();
        }
    }

    public static synchronized void resumeIncrease() {
        if (sPauseAllRequest == 0) {
            return;
        }
        sPauseAllRequest--;
    }

    public static void resumeRequest() {
        resumeIncrease();
        if (isPauseAllRequest()) {
            return;
        }
        synchronized (HiHttpClient.class) {
            Iterator<IListener> it = sResumeListeners.iterator();
            while (it.hasNext()) {
                it.next().onResume();
            }
            sResumeListeners.clear();
        }
    }

    public static void setPauseRequestConfig(HttpConfig.PauseNetworkRequestConfig pauseNetworkRequestConfig) {
        if (pauseNetworkRequestConfig == null) {
            return;
        }
        int iOrdinal = pauseNetworkRequestConfig.mode.ordinal();
        if (iOrdinal != 1) {
            if (iOrdinal != 2) {
                return;
            }
            pauseIncrease(pauseNetworkRequestConfig);
        } else if (SystemClock.elapsedRealtime() / 1000 < sDelay) {
            pauseIncrease(pauseNetworkRequestConfig);
        }
    }

    private void waitExecute() {
        while (isPauseAllRequest()) {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void cancel(HiRequest hiRequest) {
        this.mStack.cancel(hiRequest);
    }

    public void cancelAll() {
        this.mStack.cancelAll();
    }

    public void cancelById(int i) {
        for (HiRequest hiRequest : this.mStack.getRunningRequests()) {
            if (hiRequest.getId() == i) {
                this.mStack.cancel(hiRequest);
                return;
            }
        }
    }

    public void cancelByTag(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Tag is null! ");
        }
        for (HiRequest hiRequest : this.mStack.getRunningRequests()) {
            if (obj == hiRequest.getTag()) {
                this.mStack.cancel(hiRequest);
            }
        }
    }

    public void enqueue(HiRequest hiRequest, ICallback iCallback) {
        if (isPauseAllRequest()) {
            synchronized (this.mQueue) {
                this.mQueue.put(hiRequest, iCallback);
            }
        } else {
            resumeHandleQueue();
            if (hiRequest == null) {
                return;
            }
            this.mStack.enqueue(hiRequest, iCallback);
        }
    }

    public HiResponse execute(HiRequest hiRequest) {
        waitExecute();
        return this.mStack.execute(hiRequest);
    }

    public HttpStack getStack() {
        return this.mStack;
    }

    public String getSystemSettingsHost() {
        return this.mStack.getSystemSettingsHost();
    }

    public void initDynamicAddress() {
        HttpStack httpStack = this.mStack;
        if (httpStack != null) {
            httpStack.initDynamicAddress();
        }
    }

    public void cancel(int i) {
        for (HiRequest hiRequest : this.mStack.getRunningRequests()) {
            if (hiRequest.getTagCode() == i) {
                this.mStack.cancel(hiRequest);
            }
        }
    }

    public void cancel(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (HiRequest hiRequest : this.mStack.getRunningRequests()) {
            if (str.equals(hiRequest.getUrl())) {
                this.mStack.cancel(hiRequest);
            }
        }
    }
}
