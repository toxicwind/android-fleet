package com.ju.lib.datacommunication.network.http;

import android.text.TextUtils;
import b.a.a.a.a;
import com.ju.lib.datacommunication.annotation.JuClassAnnotation;
import com.ju.lib.datacommunication.annotation.JuMethodAnnotation;
import com.ju.lib.datacommunication.network.http.address.DynamicAddressParam;
import com.ju.lib.datacommunication.network.http.core.HttpStack;
import com.ju.lib.datacommunication.network.http.dns.IGSLBListener;
import com.ju.lib.datacommunication.network.http.error.IRequestFailedListener;
import com.ju.lib.datacommunication.network.http.impl.AbsInterceptor;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Interceptor;

/* JADX INFO: loaded from: classes2.dex */
public class HttpConfig {
    public static final String TAG = "HTTP2.CONFIG";
    public String mAppPackageName;
    public String mAppVersion;
    public String mAppVersionCode;
    public String mAppVersionName;
    public HttpStack.BackoffRetry mBackoffRetry;
    public File mCacheDirectory;
    public long mCacheSize;
    public long mConnectTimeOut;
    public List<AbsInterceptor> mCustomInterceptors;
    public List<Interceptor> mCustomNetworkInterceptors;
    public String mDeviceId;
    public File mDynamicAddressDirectory;
    public DynamicAddressParam mDynamicAddressParam;
    public boolean mEnableLog;
    public IGSLBListener mGSLBListener;
    public File mGslbCacheDirectory;
    public boolean mHttpDnsFlag;
    public boolean mIsJUUIDynamicAddressConfig;
    public String mJuAppVersion;
    public INetworkConnectDetector mNetworkConnectDetector;
    public PauseNetworkRequestConfig mPauseNetworkRequestConfig;
    public long mReadTimeOut;
    public IRequestFailedListener mRequestFailedListener;
    public SSLSocketFactory mSSLSocketFactory;
    public X509TrustManager mX509TrustManager;

    @JuClassAnnotation(description = "网络暂停类型，NotPause : 不暂停; PauseWhenStartup : 执行时，需要考虑是否是开机阶段; Pause : 执行时，无论什么阶段都需要暂停")
    public static class PauseNetworkRequestConfig {
        public long maxResumeRequestDuration;
        public Mode mode;

        public enum Mode {
            NotPause,
            PauseWhenStartup,
            Pause
        }

        public PauseNetworkRequestConfig(Mode mode, long j) {
            this.mode = mode;
            this.maxResumeRequestDuration = j;
        }
    }

    public HttpConfig() {
        this.mReadTimeOut = 5000L;
        this.mConnectTimeOut = 5000L;
        this.mCacheSize = 0L;
        this.mHttpDnsFlag = false;
        this.mDeviceId = "";
        this.mEnableLog = true;
        this.mAppVersion = "";
        this.mIsJUUIDynamicAddressConfig = true;
        this.mAppVersionCode = "";
        this.mAppVersionName = "";
        this.mAppPackageName = "";
        this.mJuAppVersion = "";
        this.mCustomInterceptors = new ArrayList();
        this.mCustomNetworkInterceptors = new ArrayList();
        this.mPauseNetworkRequestConfig = null;
        this.mRequestFailedListener = null;
    }

    @JuMethodAnnotation(description = "增加自定义拦截器。由于内部已经存在域名替换拦截器，如果外部拦截器进行域名修改，会导致内部域名拦截器失效。", parameters = "Interceptor", returnDes = "HttpConfig")
    public HttpConfig addCustomInterceptor(AbsInterceptor absInterceptor) {
        if (absInterceptor != null) {
            this.mCustomInterceptors.add(absInterceptor);
        }
        return this;
    }

    public HttpConfig addCustomNetworkInterceptor(Interceptor interceptor) {
        if (interceptor != null) {
            this.mCustomNetworkInterceptors.add(interceptor);
        }
        return this;
    }

    public HttpStack.BackoffRetry getBackoffRetry() {
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

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public DynamicAddressParam getDynamicAddressParam() {
        return this.mDynamicAddressParam;
    }

    public IGSLBListener getGSLBListener() {
        return this.mGSLBListener;
    }

    public File getGslbCacheDirectory() {
        return this.mGslbCacheDirectory;
    }

    public INetworkConnectDetector getNetworkConnectDetector() {
        return this.mNetworkConnectDetector;
    }

    public PauseNetworkRequestConfig getPauseNetworkRequestConfig() {
        return this.mPauseNetworkRequestConfig;
    }

    public long getReadTimeOut() {
        return this.mReadTimeOut;
    }

    public IRequestFailedListener getRequestFailedListener() {
        return this.mRequestFailedListener;
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.mSSLSocketFactory;
    }

    public X509TrustManager getX509TrustManager() {
        return this.mX509TrustManager;
    }

    public String getmAppPackageName() {
        return this.mAppPackageName;
    }

    public String getmAppVersion() {
        return this.mAppVersion;
    }

    public String getmAppVersionCode() {
        return this.mAppVersionCode;
    }

    public String getmAppVersionName() {
        return this.mAppVersionName;
    }

    public File getmDynamicAddressDirectory() {
        return this.mDynamicAddressDirectory;
    }

    public String getmJuAppVersion() {
        return this.mJuAppVersion;
    }

    public boolean isEnableLog() {
        return this.mEnableLog;
    }

    public boolean isHttpDnsFlag() {
        return this.mHttpDnsFlag;
    }

    public boolean isJUUIDynamicAddressConfig() {
        return this.mIsJUUIDynamicAddressConfig;
    }

    public HttpConfig setBackoffRetry(HttpStack.BackoffRetry backoffRetry) {
        this.mBackoffRetry = backoffRetry;
        return this;
    }

    public HttpConfig setCache(File file, long j) {
        if (j <= 0) {
            this.mCacheDirectory = null;
        } else {
            if (file == null) {
                throw new IllegalArgumentException("Directory is null! ");
            }
            if (file.exists()) {
                if (!file.isDirectory()) {
                    throw new IllegalArgumentException("Directory is not a folder! ");
                }
            } else if (!file.mkdirs()) {
                throw new IllegalArgumentException("Directory can not create! ");
            }
            this.mCacheDirectory = file;
        }
        this.mCacheSize = j;
        return this;
    }

    public HttpConfig setConnectTimeOut(long j) {
        this.mConnectTimeOut = j;
        return this;
    }

    public HttpConfig setDynamicAddress(String str, File file) {
        this.mAppVersion = str;
        this.mDynamicAddressDirectory = file;
        this.mIsJUUIDynamicAddressConfig = true;
        return this;
    }

    public HttpConfig setEnableLog(boolean z) {
        this.mEnableLog = z;
        return this;
    }

    public HttpConfig setHttpDns(boolean z, String str, File file, String str2, String str3, String str4, String str5) {
        return setHttpDns(z, str, file, null, str2, str3, str4, str5);
    }

    public HttpConfig setNetworkConnectDetector(INetworkConnectDetector iNetworkConnectDetector) {
        this.mNetworkConnectDetector = iNetworkConnectDetector;
        return this;
    }

    @JuMethodAnnotation(description = "在初始化时，设置是否暂停网络请求", parameters = "", returnDes = "返回HttpConfig")
    public HttpConfig setPauseNetworkRequestConfig(PauseNetworkRequestConfig pauseNetworkRequestConfig) {
        this.mPauseNetworkRequestConfig = pauseNetworkRequestConfig;
        return this;
    }

    public HttpConfig setReadTimeOut(long j) {
        this.mReadTimeOut = j;
        return this;
    }

    @JuMethodAnnotation(description = "在初始化时，设置网络请求失败监听者，用来上报日志", parameters = "", returnDes = "返回HttpConfig")
    public HttpConfig setRequestFailedListener(IRequestFailedListener iRequestFailedListener) {
        this.mRequestFailedListener = iRequestFailedListener;
        return this;
    }

    @JuMethodAnnotation(description = "https连接时各种参数设定，两个参数要同时不为空才有效", parameters = "sslSocketFactory: https连接时的各种参数设定(加解密协议、证书校验实例生成等); trustManager: https证书校验时对客户端与服务端进行合法性校验;", returnDes = "返回HttpConfig")
    public HttpConfig sslSocketFactory(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
        this.mSSLSocketFactory = sSLSocketFactory;
        this.mX509TrustManager = x509TrustManager;
        return this;
    }

    public HttpConfig setHttpDns(boolean z, String str, File file, IGSLBListener iGSLBListener, String str2, String str3, String str4, String str5) {
        if (!z) {
            this.mDeviceId = "";
        } else {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Http dns need a real devicdId! ");
            }
            HttpLog.d(TAG, a.a("deviceId = ", str));
            this.mDeviceId = str;
            if (file != null) {
                file.mkdirs();
            }
        }
        this.mAppPackageName = str4;
        this.mAppVersionCode = str2;
        this.mAppVersionName = str3;
        this.mJuAppVersion = str5;
        this.mGslbCacheDirectory = file;
        this.mHttpDnsFlag = z;
        this.mGSLBListener = iGSLBListener;
        return this;
    }

    public HttpConfig setDynamicAddress(DynamicAddressParam dynamicAddressParam, File file) {
        this.mAppVersion = dynamicAddressParam.getAppVersion();
        this.mDynamicAddressDirectory = file;
        this.mIsJUUIDynamicAddressConfig = false;
        this.mDynamicAddressParam = dynamicAddressParam;
        return this;
    }

    public HttpConfig setHttpDns(boolean z, String str, File file) {
        return setHttpDns(z, str, file, null, "", "", "", "");
    }

    public HttpConfig(HttpConfig httpConfig) {
        this.mReadTimeOut = 5000L;
        this.mConnectTimeOut = 5000L;
        this.mCacheSize = 0L;
        this.mHttpDnsFlag = false;
        this.mDeviceId = "";
        this.mEnableLog = true;
        this.mAppVersion = "";
        this.mIsJUUIDynamicAddressConfig = true;
        this.mAppVersionCode = "";
        this.mAppVersionName = "";
        this.mAppPackageName = "";
        this.mJuAppVersion = "";
        this.mCustomInterceptors = new ArrayList();
        this.mCustomNetworkInterceptors = new ArrayList();
        this.mPauseNetworkRequestConfig = null;
        this.mRequestFailedListener = null;
        this.mReadTimeOut = httpConfig.mReadTimeOut;
        this.mConnectTimeOut = httpConfig.mConnectTimeOut;
        this.mCacheSize = httpConfig.mCacheSize;
        this.mCacheDirectory = httpConfig.mCacheDirectory;
        this.mHttpDnsFlag = httpConfig.mHttpDnsFlag;
        this.mDeviceId = httpConfig.mDeviceId;
        this.mGslbCacheDirectory = httpConfig.mGslbCacheDirectory;
        this.mBackoffRetry = httpConfig.mBackoffRetry;
        this.mEnableLog = httpConfig.mEnableLog;
        this.mAppVersion = httpConfig.mAppVersion;
        this.mDynamicAddressDirectory = httpConfig.mDynamicAddressDirectory;
        this.mAppPackageName = httpConfig.mAppPackageName;
        this.mAppVersionCode = httpConfig.mAppVersionCode;
        this.mAppVersionName = httpConfig.mAppVersionName;
        this.mJuAppVersion = httpConfig.mJuAppVersion;
        this.mCustomInterceptors = httpConfig.mCustomInterceptors;
        this.mCustomNetworkInterceptors = httpConfig.mCustomNetworkInterceptors;
        this.mGSLBListener = httpConfig.mGSLBListener;
        this.mNetworkConnectDetector = httpConfig.mNetworkConnectDetector;
        this.mIsJUUIDynamicAddressConfig = httpConfig.mIsJUUIDynamicAddressConfig;
        DynamicAddressParam dynamicAddressParam = httpConfig.mDynamicAddressParam;
        if (dynamicAddressParam != null) {
            this.mDynamicAddressParam = dynamicAddressParam.copyObject();
        }
        this.mPauseNetworkRequestConfig = httpConfig.mPauseNetworkRequestConfig;
        this.mRequestFailedListener = httpConfig.mRequestFailedListener;
        this.mX509TrustManager = httpConfig.mX509TrustManager;
        this.mSSLSocketFactory = httpConfig.mSSLSocketFactory;
    }
}
