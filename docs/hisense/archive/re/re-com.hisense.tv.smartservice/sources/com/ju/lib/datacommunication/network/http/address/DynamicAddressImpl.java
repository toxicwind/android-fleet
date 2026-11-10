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
public class DynamicAddressImpl implements DynamicAddressUseCase {
    public static DynamicAddressImpl INSTANCE = null;
    public static final int INTERVAL = 3;
    public static final int MAX_DELAY = 1440;
    public static final String TAG = "DynamicAddressImpl";
    public static final long TwentyFourHours = 86400000;
    public AddressResponse mAddressResponse;
    public Handler mHandler;
    public DynamicAddressManager manager;
    public int retryCount = 1;
    public Runnable doGetFromLocal = new Runnable() { // from class: com.ju.lib.datacommunication.network.http.address.DynamicAddressImpl.1
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                HttpLog.i(DynamicAddressImpl.TAG, "get from local start:");
                AddressResponse addressLocal = DynamicAddressImpl.this.manager.getAddressLocal();
                if (addressLocal != null) {
                    DynamicAddressImpl.this.mAddressResponse = addressLocal;
                    DynamicAddressImpl.this.decodeResponse();
                    DynamicAddressImpl.access$500(DynamicAddressImpl.this, DynamicAddressImpl.this.mCheckUpdate, 30000L);
                    if (HttpLog.isHttpStackLogAble()) {
                        HttpLog.d(DynamicAddressImpl.TAG, "doGetFromLocal mAddressResponse :", DynamicAddressImpl.this.mAddressResponse);
                    }
                } else {
                    HttpLog.i(DynamicAddressImpl.TAG, "doGetFromLocal mAddressResponse is null");
                    DynamicAddressImpl.access$500(DynamicAddressImpl.this, DynamicAddressImpl.this.doGetFromRemote, 0L);
                }
            } catch (Exception e2) {
                DynamicAddressImpl dynamicAddressImpl = DynamicAddressImpl.this;
                DynamicAddressImpl.access$500(dynamicAddressImpl, dynamicAddressImpl.doGetFromRemote, 0L);
                HttpLog.i(DynamicAddressImpl.TAG, a.a("get from local fail:", e2));
            }
        }
    };
    public Runnable mCheckUpdate = new Runnable() { // from class: com.ju.lib.datacommunication.network.http.address.DynamicAddressImpl.2
        @Override // java.lang.Runnable
        public void run() {
            DynamicAddressImpl dynamicAddressImpl = DynamicAddressImpl.this;
            long delayTime = dynamicAddressImpl.getDelayTime(dynamicAddressImpl.mAddressResponse.timeStampRet);
            HttpLog.i(DynamicAddressImpl.TAG, a.a("next update time:", delayTime));
            DynamicAddressImpl dynamicAddressImpl2 = DynamicAddressImpl.this;
            DynamicAddressImpl.access$500(dynamicAddressImpl2, dynamicAddressImpl2.doGetFromRemote, delayTime);
        }
    };
    public Runnable doGetFromRemote = new Runnable() { // from class: com.ju.lib.datacommunication.network.http.address.DynamicAddressImpl.3
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                HttpLog.i(DynamicAddressImpl.TAG, "get from remote start:");
                String str = "0";
                if (DynamicAddressImpl.this.mAddressResponse != null && DynamicAddressImpl.this.mAddressResponse.domainVersion != null && !"".equals(DynamicAddressImpl.this.mAddressResponse.domainVersion)) {
                    str = DynamicAddressImpl.this.mAddressResponse.domainVersion;
                }
                AddressResponse addressFromRemote = DynamicAddressImpl.this.manager.getAddressFromRemote(str);
                if (addressFromRemote == null || addressFromRemote.resultCode != 0) {
                    DynamicAddressImpl.access$500(DynamicAddressImpl.this, DynamicAddressImpl.this.doGetFromRemote, DynamicAddressImpl.this.retryCount * 3 * 60 * 1000);
                    if (480 > DynamicAddressImpl.this.retryCount) {
                        DynamicAddressImpl.access$808(DynamicAddressImpl.this);
                        return;
                    }
                    return;
                }
                DynamicAddressImpl.this.mAddressResponse = addressFromRemote;
                DynamicAddressImpl.this.decodeResponse();
                DynamicAddressImpl.access$500(DynamicAddressImpl.this, DynamicAddressImpl.this.doGetFromRemote, 86400000L);
                if (HttpLog.isHttpStackLogAble()) {
                    HttpLog.d(DynamicAddressImpl.TAG, "doGetFromRemote mAddressResponse :", DynamicAddressImpl.this.mAddressResponse);
                }
            } catch (Exception e2) {
                DynamicAddressImpl dynamicAddressImpl = DynamicAddressImpl.this;
                DynamicAddressImpl.access$500(dynamicAddressImpl, dynamicAddressImpl.doGetFromRemote, DynamicAddressImpl.this.retryCount * 3 * 60 * 1000);
                if (480 > DynamicAddressImpl.this.retryCount) {
                    DynamicAddressImpl.access$808(DynamicAddressImpl.this);
                }
                HttpLog.i(DynamicAddressImpl.TAG, a.a("get from remote fail:", e2));
            }
        }
    };
    public HandlerThread mThread = new HandlerThread("DnamicAddress");

    public DynamicAddressImpl(String str, String str2, String str3) {
        this.manager = new DynamicAddressManager(str, str2, str3);
        this.mThread.start();
        createHandler();
    }

    public static /* synthetic */ void access$500(DynamicAddressImpl dynamicAddressImpl, Runnable runnable, long j) {
        dynamicAddressImpl.mHandler.removeCallbacks(runnable);
        dynamicAddressImpl.mHandler.postDelayed(runnable, j);
    }

    public static /* synthetic */ int access$808(DynamicAddressImpl dynamicAddressImpl) {
        int i = dynamicAddressImpl.retryCount;
        dynamicAddressImpl.retryCount = i + 1;
        return i;
    }

    private void createHandler() {
        this.mHandler = new Handler(this.mThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decodeResponse() {
        for (AddressInfo addressInfo : this.mAddressResponse.appServiceList) {
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

    public static final DynamicAddressImpl getInstance(String str, String str2, String str3) {
        if (INSTANCE == null) {
            synchronized (DynamicAddressImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DynamicAddressImpl(str, str2, str3);
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
            if (this.mAddressResponse == null || this.mAddressResponse.appServiceList == null) {
                arrayList.add(str);
            } else {
                for (AddressInfo addressInfo : this.mAddressResponse.appServiceList) {
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
