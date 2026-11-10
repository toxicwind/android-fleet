package com.ju.lib.datacommunication.network.http.address;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public class ThirdDynamicAddressImpl implements DynamicAddressUseCase {
    public static ThirdDynamicAddressImpl INSTANCE = null;
    public static final int INTERVAL = 3;
    public static final int MAX_DELAY = 1440;
    public static final String TAG = "ThirdDynamicAddressImpl";
    public static final long TwentyFourHours = 86400000;
    public ThirdAddressResponse mAddressResponse;
    public Handler mHandler;
    public ThirdDynamicAddressManager manager;
    public int retryCount = 1;
    public Runnable doGetFromLocal = new Runnable() { // from class: com.ju.lib.datacommunication.network.http.address.ThirdDynamicAddressImpl.1
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                HttpLog.i(ThirdDynamicAddressImpl.TAG, "get from local start:");
                ThirdAddressResponse addressLocal = ThirdDynamicAddressImpl.this.manager.getAddressLocal();
                if (addressLocal != null) {
                    ThirdDynamicAddressImpl.this.mAddressResponse = addressLocal;
                    ThirdDynamicAddressImpl.this.decodeResponse();
                    ThirdDynamicAddressImpl.access$500(ThirdDynamicAddressImpl.this, ThirdDynamicAddressImpl.this.mCheckUpdate, 30000L);
                    if (HttpLog.isHttpStackLogAble()) {
                        HttpLog.d(ThirdDynamicAddressImpl.TAG, "doGetFromLocal mAddressResponse :", ThirdDynamicAddressImpl.this.mAddressResponse);
                    }
                } else {
                    HttpLog.i(ThirdDynamicAddressImpl.TAG, "doGetFromLocal mAddressResponse is null");
                    ThirdDynamicAddressImpl.access$500(ThirdDynamicAddressImpl.this, ThirdDynamicAddressImpl.this.doGetFromRemote, 0L);
                }
            } catch (Exception e2) {
                ThirdDynamicAddressImpl thirdDynamicAddressImpl = ThirdDynamicAddressImpl.this;
                ThirdDynamicAddressImpl.access$500(thirdDynamicAddressImpl, thirdDynamicAddressImpl.doGetFromRemote, 0L);
                HttpLog.i(ThirdDynamicAddressImpl.TAG, a.a("get from local fail:", e2));
            }
        }
    };
    public Runnable mCheckUpdate = new Runnable() { // from class: com.ju.lib.datacommunication.network.http.address.ThirdDynamicAddressImpl.2
        @Override // java.lang.Runnable
        public void run() {
            ThirdDynamicAddressImpl thirdDynamicAddressImpl = ThirdDynamicAddressImpl.this;
            long delayTime = thirdDynamicAddressImpl.getDelayTime(thirdDynamicAddressImpl.mAddressResponse.timeStamp);
            HttpLog.i(ThirdDynamicAddressImpl.TAG, a.a("next update time:", delayTime));
            ThirdDynamicAddressImpl thirdDynamicAddressImpl2 = ThirdDynamicAddressImpl.this;
            ThirdDynamicAddressImpl.access$500(thirdDynamicAddressImpl2, thirdDynamicAddressImpl2.doGetFromRemote, delayTime);
        }
    };
    public Runnable doGetFromRemote = new Runnable() { // from class: com.ju.lib.datacommunication.network.http.address.ThirdDynamicAddressImpl.3
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                HttpLog.i(ThirdDynamicAddressImpl.TAG, "get from remote start:");
                ThirdAddressResponse addressFromRemote = ThirdDynamicAddressImpl.this.manager.getAddressFromRemote();
                if (addressFromRemote == null || addressFromRemote.code != 0) {
                    ThirdDynamicAddressImpl.access$500(ThirdDynamicAddressImpl.this, ThirdDynamicAddressImpl.this.doGetFromRemote, ThirdDynamicAddressImpl.this.retryCount * 3 * 60 * 1000);
                    if (480 > ThirdDynamicAddressImpl.this.retryCount) {
                        ThirdDynamicAddressImpl.access$808(ThirdDynamicAddressImpl.this);
                    }
                } else {
                    ThirdDynamicAddressImpl.this.mAddressResponse = addressFromRemote;
                    ThirdDynamicAddressImpl.this.decodeResponse();
                    ThirdDynamicAddressImpl.access$500(ThirdDynamicAddressImpl.this, ThirdDynamicAddressImpl.this.doGetFromRemote, 86400000L);
                    if (HttpLog.isHttpStackLogAble()) {
                        HttpLog.d(ThirdDynamicAddressImpl.TAG, "doGetFromRemote mAddressResponse :", ThirdDynamicAddressImpl.this.mAddressResponse);
                    }
                }
            } catch (Exception e2) {
                ThirdDynamicAddressImpl thirdDynamicAddressImpl = ThirdDynamicAddressImpl.this;
                ThirdDynamicAddressImpl.access$500(thirdDynamicAddressImpl, thirdDynamicAddressImpl.doGetFromRemote, ThirdDynamicAddressImpl.this.retryCount * 3 * 60 * 1000);
                if (480 > ThirdDynamicAddressImpl.this.retryCount) {
                    ThirdDynamicAddressImpl.access$808(ThirdDynamicAddressImpl.this);
                }
                HttpLog.i(ThirdDynamicAddressImpl.TAG, a.a("get from remote fail:", e2));
            }
        }
    };
    public HandlerThread mThread = new HandlerThread("DnamicAddress");

    public ThirdDynamicAddressImpl(String str, String str2, String str3, DynamicAddressParam dynamicAddressParam) {
        this.manager = new ThirdDynamicAddressManager(str, str2, str3, dynamicAddressParam);
        this.mThread.start();
        createHandler();
    }

    public static /* synthetic */ void access$500(ThirdDynamicAddressImpl thirdDynamicAddressImpl, Runnable runnable, long j) {
        thirdDynamicAddressImpl.mHandler.removeCallbacks(runnable);
        thirdDynamicAddressImpl.mHandler.postDelayed(runnable, j);
    }

    public static /* synthetic */ int access$808(ThirdDynamicAddressImpl thirdDynamicAddressImpl) {
        int i = thirdDynamicAddressImpl.retryCount;
        thirdDynamicAddressImpl.retryCount = i + 1;
        return i;
    }

    private void createHandler() {
        this.mHandler = new Handler(this.mThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decodeResponse() {
        for (AddressInfo addressInfo : this.mAddressResponse.payload) {
            List<String> list = addressInfo.addressList;
            if (list != null && !list.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                Iterator<String> it = addressInfo.addressList.iterator();
                while (it.hasNext()) {
                    arrayList.add(DynamicAddressTools.AESDecrypt(it.next()));
                }
                if (!arrayList.isEmpty()) {
                    addressInfo.addressList = arrayList;
                }
            }
        }
        if (HttpLog.isHttpStackLogAble()) {
            HttpLog.i(TAG, "decode end mAddressResponse.payload :", this.mAddressResponse.payload);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getDelayTime(long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        HttpLog.i(TAG, a.a("next update time nowTime:", jCurrentTimeMillis), a.a(",timeStampRet:", j));
        if (j > 0 && jCurrentTimeMillis >= j && jCurrentTimeMillis - j < 86400000) {
            return (j + 86400000) - jCurrentTimeMillis;
        }
        return 0L;
    }

    public static final ThirdDynamicAddressImpl getInstance(String str, String str2, String str3, DynamicAddressParam dynamicAddressParam) {
        if (INSTANCE == null) {
            synchronized (ThirdDynamicAddressImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThirdDynamicAddressImpl(str, str2, str3, dynamicAddressParam);
                }
            }
        }
        return INSTANCE;
    }

    private void requestAddressList(Runnable runnable, long j) {
        this.mHandler.removeCallbacks(runnable);
        this.mHandler.postDelayed(runnable, j);
    }

    @Override // com.ju.lib.datacommunication.network.http.address.DynamicAddressUseCase
    public void destroy() {
        onDestroy();
    }

    @Override // com.ju.lib.datacommunication.network.http.address.DynamicAddressUseCase
    public List<String> getHost(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (this.mAddressResponse == null || this.mAddressResponse.payload == null) {
                arrayList.add(str);
            } else {
                for (AddressInfo addressInfo : this.mAddressResponse.payload) {
                    if (str.equals(addressInfo.addresstype)) {
                        List<String> list = addressInfo.addressList;
                        if (list == null || list.isEmpty()) {
                            if (HttpLog.isHttpStackLogAble()) {
                                HttpLog.i(TAG, "address found null:" + str);
                            }
                            arrayList.add(str);
                            return arrayList;
                        }
                        List<String> list2 = addressInfo.addressList;
                        if (HttpLog.isHttpStackLogAble()) {
                            HttpLog.i(TAG, "get address sucess:" + list2);
                        }
                        return list2;
                    }
                }
                arrayList.add(str);
            }
            return arrayList;
        } catch (Exception e2) {
            HttpLog.i(TAG, a.a("get address fail:", e2));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(str);
            return arrayList2;
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.address.DynamicAddressUseCase
    public String getRandomHost(String str) {
        List<String> host = getHost(str);
        return (host == null || host.isEmpty()) ? str : host.get(new Random().nextInt(host.size()));
    }

    @TargetApi(5)
    public void onDestroy() {
        HandlerThread handlerThread = this.mThread;
        if (handlerThread == null || !handlerThread.isAlive()) {
            return;
        }
        this.mThread.quit();
    }

    @Override // com.ju.lib.datacommunication.network.http.address.DynamicAddressUseCase
    public void start() {
        requestAddressList(this.doGetFromLocal, 0L);
    }
}
