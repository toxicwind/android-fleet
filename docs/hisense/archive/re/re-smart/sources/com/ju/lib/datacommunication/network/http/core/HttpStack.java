package com.ju.lib.datacommunication.network.http.core;

import b.a.a.a.a;
import com.ju.lib.datacommunication.network.http.INetworkConnectDetector;
import com.ju.lib.datacommunication.network.http.address.DynamicAddressUseCase;
import com.ju.lib.datacommunication.network.http.impl.AbsInterceptor;
import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Interceptor;

/* JADX INFO: loaded from: classes2.dex */
public abstract class HttpStack {
    public HttpStackConfig mHttpStackConfig;

    public interface BackoffRetry {
        long backoffTimeMillis(int i);
    }

    public interface Cancelable {
        void cancel();
    }

    public interface Dns {
        void failed(String str, InetAddress inetAddress);

        HostInfo parse(String str);

        void resetCustomId(String str);
    }

    public static final class HttpStackConfig {
        public BackoffRetry mBackoffRetry;
        public File mCacheDirectory;
        public long mCacheSize;
        public long mConnectTimeOut;
        public List<AbsInterceptor> mCustomInterceptors = new ArrayList();
        public List<Interceptor> mCustomNetworkInterceptors = new ArrayList();
        public Dns mDns;
        public DynamicAddressUseCase mDynamicAddress;
        public boolean mEnableLog;
        public boolean mEnableRetry;
        public HostnameVerifier mHostnameVerifier;
        public INetworkConnectDetector mNetworkConnectDetector;
        public long mReadTimeOut;
        public SSLSocketFactory mSSLSocketFactory;
        public X509TrustManager mX509TrustManager;

        public BackoffRetry getBackoffRetry() {
            return this.mBackoffRetry;
        }

        public File getCacheDirectory() {
            return this.mCacheDirectory;
        }

        public long getCacheSize() {
            return this.mCacheSize;
        }

        public long getConnectTimeOut() {
            return this.mConnectTimeOut;
        }

        public List<AbsInterceptor> getCustomInterceptors() {
            return this.mCustomInterceptors;
        }

        public List<Interceptor> getCustomNetworkInterceptors() {
            return this.mCustomNetworkInterceptors;
        }

        public Dns getDns() {
            return this.mDns;
        }

        public DynamicAddressUseCase getDynamicAddress() {
            return this.mDynamicAddress;
        }

        public HostnameVerifier getHostnameVerifier() {
            return this.mHostnameVerifier;
        }

        public INetworkConnectDetector getNetworkConnectDetector() {
            return this.mNetworkConnectDetector;
        }

        public long getReadTimeOut() {
            return this.mReadTimeOut;
        }

        public SSLSocketFactory getSSLSocketFactory() {
            return this.mSSLSocketFactory;
        }

        public X509TrustManager getX509TrustManager() {
            return this.mX509TrustManager;
        }

        public boolean isEnableLog() {
            return this.mEnableLog;
        }

        public boolean isEnableRetry() {
            return this.mEnableRetry;
        }

        public void setBackoffRetry(BackoffRetry backoffRetry) {
            this.mBackoffRetry = backoffRetry;
        }

        public void setCache(File file, long j) {
            this.mCacheDirectory = file;
            this.mCacheSize = j;
        }

        public void setConnectTimeOut(long j) {
            this.mConnectTimeOut = j;
        }

        public void setCustomInterceptors(List<AbsInterceptor> list) {
            this.mCustomInterceptors = list;
        }

        public void setCustomNetworkInterceptors(List<Interceptor> list) {
            this.mCustomNetworkInterceptors = list;
        }

        public void setDns(Dns dns) {
            this.mDns = dns;
        }

        public void setDynamicAddress(DynamicAddressUseCase dynamicAddressUseCase) {
            this.mDynamicAddress = dynamicAddressUseCase;
        }

        public void setEnableLog(boolean z) {
            this.mEnableLog = z;
        }

        public void setEnableRetry(boolean z) {
            this.mEnableRetry = z;
        }

        public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.mHostnameVerifier = hostnameVerifier;
        }

        public void setNetworkConnectDetector(INetworkConnectDetector iNetworkConnectDetector) {
            this.mNetworkConnectDetector = iNetworkConnectDetector;
        }

        public void setReadTimeOut(long j) {
            this.mReadTimeOut = j;
        }

        public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.mSSLSocketFactory = sSLSocketFactory;
        }

        public void setX509TrustManager(X509TrustManager x509TrustManager) {
            this.mX509TrustManager = x509TrustManager;
        }

        public String toString() {
            StringBuilder sbA = a.a("HttpConfig: Dns = ");
            sbA.append(this.mDns);
            sbA.append(", CacheDirectory = ");
            sbA.append(this.mCacheDirectory);
            sbA.append(", CacheSize = ");
            sbA.append(this.mCacheSize);
            sbA.append(", ReadTimeOut = ");
            sbA.append(this.mReadTimeOut);
            sbA.append(", ConnectTimeOut = ");
            sbA.append(this.mConnectTimeOut);
            sbA.append(", EnableLog = ");
            sbA.append(this.mEnableLog);
            sbA.append(", EnableRetry = ");
            sbA.append(this.mEnableRetry);
            return sbA.toString();
        }
    }

    public void bindCancelable(HiRequest hiRequest, Cancelable cancelable) {
        if (hiRequest == null) {
            throw new NullPointerException("HiRequest is null! ");
        }
        if (cancelable == null) {
            throw new NullPointerException("Cancelable is null! ");
        }
        synchronized (this) {
            if (hiRequest.getCancelable() != null) {
                throw new IllegalArgumentException("HiRequest is binded! ");
            }
            hiRequest.setCancelable(cancelable);
        }
    }

    public abstract void cancel(HiRequest hiRequest);

    public abstract void cancelAll();

    public abstract void enqueue(HiRequest hiRequest, ICallback iCallback);

    public abstract HiResponse execute(HiRequest hiRequest);

    public Cancelable getCancelable(HiRequest hiRequest) {
        Cancelable cancelable;
        if (hiRequest == null) {
            throw new NullPointerException("HiRequest is null! ");
        }
        synchronized (this) {
            cancelable = hiRequest.getCancelable();
        }
        if (cancelable != null) {
            return cancelable;
        }
        throw new IllegalArgumentException("HiRequest is not binded! ");
    }

    public HttpStackConfig getHttpStackConfig() {
        return this.mHttpStackConfig;
    }

    public abstract List<HiRequest> getRunningRequests();

    public abstract String getSystemSettingsHost();

    public abstract void initDynamicAddress();
}
