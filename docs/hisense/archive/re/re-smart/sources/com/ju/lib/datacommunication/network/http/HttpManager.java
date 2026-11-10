package com.ju.lib.datacommunication.network.http;

import b.a.a.a.a;
import com.ju.lib.datacommunication.annotation.JuMethodAnnotation;
import com.ju.lib.datacommunication.network.http.address.DynamicAddressImpl;
import com.ju.lib.datacommunication.network.http.address.DynamicAddressUseCase;
import com.ju.lib.datacommunication.network.http.address.ThirdDynamicAddressImpl;
import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HttpStack;
import com.ju.lib.datacommunication.network.http.debug.DebugHelper;
import com.ju.lib.datacommunication.network.http.dns.HttpDns;
import com.ju.lib.datacommunication.network.http.dns.HttpDnsConfig;
import com.ju.lib.datacommunication.network.http.dns.Tools;
import com.ju.lib.datacommunication.network.http.error.RequestFailedManager;
import com.ju.lib.datacommunication.network.http.impl.AbsInterceptor;
import com.ju.lib.datacommunication.network.http.impl.HttpStackImpl;
import java.util.List;
import okhttp3.Interceptor;

/* JADX INFO: loaded from: classes2.dex */
public class HttpManager {
    public static HttpStack.Dns DNS;
    public static volatile IHttpApi HTTP_API;
    public static HttpConfig HTTP_CONFIG;
    public static final String TAG;
    public static DynamicAddressUseCase mDynamicAddress;

    static {
        StringBuilder sbA = a.a(HiHttpClient.HTTP_COMMON_TAG);
        sbA.append(HttpManager.class.getSimpleName());
        TAG = sbA.toString();
        mDynamicAddress = null;
    }

    public static HttpStackImpl createDnsStack(HttpConfig httpConfig, HttpStackImpl httpStackImpl) {
        return HttpDnsConfig.createDnsStack(Tools.toMilliSecond(1.2d), httpStackImpl);
    }

    public static HttpStack.Dns getDns() {
        HttpStack.Dns dns;
        synchronized (HttpManager.class) {
            dns = DNS;
        }
        return dns;
    }

    public static final IHttpApi getHttpApi() {
        if (HTTP_API == null) {
            synchronized (HttpManager.class) {
                if (HTTP_API == null) {
                    setup(new HttpConfig());
                    return HTTP_API;
                }
            }
        }
        return HTTP_API;
    }

    public static final HttpConfig obtainHttpConfig() {
        synchronized (HttpManager.class) {
            if (HTTP_CONFIG == null) {
                return new HttpConfig();
            }
            return new HttpConfig(HTTP_CONFIG);
        }
    }

    public static void resetCustomId(String str) {
        HttpStack.Dns dns = DNS;
        if (dns != null) {
            dns.resetCustomId(str);
        }
    }

    @JuMethodAnnotation(description = "当需要恢复时，调用这个接口；必须和setPauseRequestConfig成对使用", parameters = "", returnDes = "")
    public static void resumeRequest() {
        HiHttpClient.resumeRequest();
    }

    public static void setup(HttpConfig httpConfig) {
        if (DebugHelper.isDebugModel()) {
            DebugHelper.getInstance().init(httpConfig.getmAppPackageName());
        }
        HttpStack.HttpStackConfig httpStackConfig = new HttpStack.HttpStackConfig();
        httpStackConfig.setConnectTimeOut(httpConfig.getConnectTimeOut());
        httpStackConfig.setReadTimeOut(httpConfig.getReadTimeOut());
        httpStackConfig.setEnableLog(httpConfig.isEnableLog());
        httpStackConfig.setEnableRetry(true);
        httpStackConfig.setBackoffRetry(httpConfig.getBackoffRetry());
        HiHttpClient.setPauseRequestConfig(httpConfig.getPauseNetworkRequestConfig());
        if (httpConfig.getCacheSize() > 0) {
            httpStackConfig.setCache(httpConfig.getCacheDirectory(), httpConfig.getCacheSize());
        }
        if (httpConfig.getX509TrustManager() != null && httpConfig.getSSLSocketFactory() != null) {
            httpStackConfig.setSSLSocketFactory(httpConfig.getSSLSocketFactory());
            httpStackConfig.setX509TrustManager(httpConfig.getX509TrustManager());
        }
        HttpStackImpl httpStackImplCreateDnsStack = null;
        if (httpConfig.isHttpDnsFlag()) {
            httpStackImplCreateDnsStack = HTTP_API == null ? createDnsStack(httpConfig, null) : createDnsStack(httpConfig, (HttpStackImpl) HTTP_API.getHttpClient().getStack());
            DNS = new HttpDns(new HiHttpClient(httpStackImplCreateDnsStack), httpConfig.getGslbCacheDirectory(), httpConfig.getDeviceId(), httpConfig.getmAppVersionCode(), httpConfig.getmAppVersionName(), httpConfig.getmAppPackageName(), httpConfig.getmJuAppVersion(), httpConfig.getGSLBListener(), httpConfig.getNetworkConnectDetector());
            httpStackConfig.setDns(DNS);
        }
        if (httpConfig.getmDynamicAddressDirectory() != null) {
            if (httpConfig.isJUUIDynamicAddressConfig()) {
                mDynamicAddress = DynamicAddressImpl.getInstance(httpConfig.getmDynamicAddressDirectory().getPath(), httpConfig.getmAppVersion(), httpConfig.getDeviceId());
            } else {
                mDynamicAddress = ThirdDynamicAddressImpl.getInstance(httpConfig.getmDynamicAddressDirectory().getPath(), httpConfig.getmAppVersion(), httpConfig.getDeviceId(), httpConfig.getDynamicAddressParam());
            }
            httpStackConfig.setDynamicAddress(mDynamicAddress);
        }
        if (httpConfig.getRequestFailedListener() != null) {
            RequestFailedManager.getInstance().setRequestFailedListener(httpConfig.getRequestFailedListener());
            RequestFailedManager.getInstance().setNetworkConnectDetector(httpConfig.getNetworkConnectDetector());
        }
        List<AbsInterceptor> customInterceptors = httpConfig.getCustomInterceptors();
        if (customInterceptors != null && !customInterceptors.isEmpty()) {
            httpStackConfig.setCustomInterceptors(customInterceptors);
        }
        List<Interceptor> customNetworkInterceptors = httpConfig.getCustomNetworkInterceptors();
        if (customNetworkInterceptors != null && !customNetworkInterceptors.isEmpty()) {
            httpStackConfig.setCustomNetworkInterceptors(customNetworkInterceptors);
        }
        httpStackConfig.setNetworkConnectDetector(httpConfig.getNetworkConnectDetector());
        HiHttpClient hiHttpClient = new HiHttpClient(new HttpStackImpl(httpStackConfig, httpStackImplCreateDnsStack));
        HTTP_API = new HttpApiImpl(hiHttpClient);
        hiHttpClient.initDynamicAddress();
    }

    @JuMethodAnnotation(description = "通过给定的选项配置HiHttpClient", parameters = "", returnDes = "通过当前config创建的IHttpApi对象")
    public static final IHttpApi setupHttpApi(HttpConfig httpConfig) {
        IHttpApi iHttpApi;
        if (httpConfig == null) {
            throw new NullPointerException("HttpConfig is null! ");
        }
        synchronized (HttpManager.class) {
            HTTP_CONFIG = new HttpConfig(httpConfig);
            setup(HTTP_CONFIG);
            iHttpApi = HTTP_API;
        }
        return iHttpApi;
    }
}
