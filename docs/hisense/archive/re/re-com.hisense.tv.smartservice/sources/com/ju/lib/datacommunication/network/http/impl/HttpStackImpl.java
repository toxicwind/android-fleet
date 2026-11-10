package com.ju.lib.datacommunication.network.http.impl;

import android.os.SystemProperties;
import android.text.TextUtils;
import b.a.a.a.a;
import com.google.firebase.installations.Utils;
import com.google.firebase.installations.local.IidStore;
import com.ju.lib.datacommunication.network.http.INetworkConnectDetector;
import com.ju.lib.datacommunication.network.http.address.DynamicAddressUseCase;
import com.ju.lib.datacommunication.network.http.core.HiHttpHeaders;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.HostInfo;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.HttpStack;
import com.ju.lib.datacommunication.network.http.core.ICallback;
import com.ju.lib.datacommunication.network.http.core.signature.ISignature;
import com.ju.lib.datacommunication.network.http.core.signature.SignatureException;
import com.ju.lib.datacommunication.network.http.debug.DebugHelper;
import com.ju.lib.datacommunication.network.http.debug.DebugTrace;
import com.ju.lib.datacommunication.network.http.error.NetworkErrorEnum;
import com.ju.lib.datacommunication.network.http.error.RequestFailedBean;
import com.ju.lib.datacommunication.network.http.error.RequestFailedManager;
import com.ju.lib.datacommunication.network.http.impl.interceptor.ConnectionFailedInterceptor;
import com.ju.lib.datacommunication.network.http.impl.interceptor.ContentLengthInterceptor;
import com.ju.lib.datacommunication.network.http.impl.interceptor.DynamicAddressInterceptor;
import com.ju.lib.datacommunication.network.http.impl.interceptor.HostReplaceInterceptor;
import com.ju.lib.datacommunication.network.http.impl.interceptor.HttpsDegradeInterceptor;
import com.ju.lib.datacommunication.network.http.impl.interceptor.RetryInterceptor;
import com.ju.lib.datacommunication.network.http.impl.networkinterceptor.HeadHostReplaceInterceptor;
import com.ju.lib.datacommunication.network.http.impl.networkinterceptor.LogInterceptor;
import com.ju.lib.datacommunication.network.http.impl.networkinterceptor.RedirectHostReplaceInterceptor;
import com.ju.lib.datacommunication.network.http.impl.networkinterceptor.TraceInterceptor;
import com.ju.lib.datacommunication.network.http.utils.CacheUtils;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import com.ju.lib.datacommunication.network.http.utils.ReflectUtil;
import d.h;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dns;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes2.dex */
public class HttpStackImpl extends HttpStack {
    public static final String TAG = "HTTP2.Stack";
    public final OkHttpClient mClient;
    public final HttpStack.Dns mDns;
    public final DynamicAddressUseCase mDynamicAddress;
    public final INetworkConnectDetector mNetworkConnectDetector;
    public final String mSystemSettingsHostDomain;

    /* JADX INFO: renamed from: com.ju.lib.datacommunication.network.http.impl.HttpStackImpl$4, reason: invalid class name */
    public class AnonymousClass4 implements Dns {
        public final /* synthetic */ HttpStack.Dns val$dns;

        public AnonymousClass4(HttpStack.Dns dns) {
            this.val$dns = dns;
        }

        @Override // okhttp3.Dns
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            HostInfo hostInfo = this.val$dns.parse(str);
            if (hostInfo == null) {
                throw new UnknownHostException(str);
            }
            List<InetAddress> inetAddress = hostInfo.getInetAddress();
            if (inetAddress == null || inetAddress.isEmpty()) {
                throw new UnknownHostException(str);
            }
            List<InetAddress> proxyInetAddress = hostInfo.getProxyInetAddress();
            if (proxyInetAddress != null && !proxyInetAddress.isEmpty()) {
                inetAddress.addAll(proxyInetAddress);
            }
            HttpLog.d("HTTP2.Stack", "inetAddresseslist=", inetAddress);
            return inetAddress;
        }
    }

    /* JADX INFO: renamed from: com.ju.lib.datacommunication.network.http.impl.HttpStackImpl$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        public static final /* synthetic */ int[] $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method = new int[HiRequest.Method.values().length];

        static {
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.POST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.HEAD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.PUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.DELETE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$ju$lib$datacommunication$network$http$core$HiRequest$Method[HiRequest.Method.PATCH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private static class CancelableImpl implements HttpStack.Cancelable {
        public final Call mCall;

        public CancelableImpl(Call call) {
            this.mCall = call;
        }

        @Override // com.ju.lib.datacommunication.network.http.core.HttpStack.Cancelable
        public void cancel() {
            this.mCall.cancel();
        }
    }

    private static final class HiResponseBody extends HiResponse.Body {
        public ResponseBody mBody;

        public HiResponseBody(ResponseBody responseBody) {
            this.mBody = responseBody;
        }

        @Override // com.ju.lib.datacommunication.network.http.core.HiResponse.Body
        public byte[] bytes() throws HttpException {
            try {
                return this.mBody.bytes();
            } catch (IOException e2) {
                throw new HttpException(1001, "Connect error! ", e2);
            }
        }

        @Override // com.ju.lib.datacommunication.network.http.core.HiResponse.Body
        public long contentLength() {
            return this.mBody.contentLength();
        }

        @Override // com.ju.lib.datacommunication.network.http.core.HiResponse.Body
        public InputStream getInputStream() {
            return this.mBody.byteStream();
        }

        @Override // com.ju.lib.datacommunication.network.http.core.HiResponse.Body
        public String string() throws HttpException {
            try {
                return this.mBody.string();
            } catch (IOException e2) {
                throw new HttpException(1001, "Connect error! ", e2);
            }
        }
    }

    private static final class OkHttpRequestBody extends RequestBody {
        public HiRequest.Body mBody;

        public OkHttpRequestBody(HiRequest.Body body) {
            this.mBody = body;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() {
            return this.mBody.contentLength();
        }

        @Override // okhttp3.RequestBody
        public MediaType contentType() {
            String strContentType = this.mBody.contentType();
            if (strContentType == null) {
                return null;
            }
            return MediaType.parse(strContentType);
        }

        @Override // okhttp3.RequestBody
        public void writeTo(h hVar) {
            this.mBody.writeTo(hVar.l());
        }
    }

    public static final class RequestTag {
        public DebugTrace debugTrace;
        public final HiRequest mRequest;
        public HiResponse.Trace mTrace;

        public RequestTag(HiRequest hiRequest) {
            this.mRequest = hiRequest;
        }
    }

    public HttpStackImpl(HttpStack.HttpStackConfig httpStackConfig) {
        this(httpStackConfig, null);
    }

    private void addList(List<HiRequest> list, List<Call> list2) {
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        Iterator<Call> it = list2.iterator();
        while (it.hasNext()) {
            Object objTag = it.next().request().tag();
            if (objTag instanceof RequestTag) {
                list.add(((RequestTag) objTag).mRequest);
            }
        }
    }

    private Request change(HiRequest hiRequest, RequestTag requestTag) {
        Request.Builder builderTag = new Request.Builder().url(hiRequest.getUrl()).tag(requestTag);
        HiHttpHeaders headers = hiRequest.getHeaders();
        for (int i = 0; i < headers.size(); i++) {
            builderTag.addHeader(headers.getName(i), headers.getValue(i));
        }
        int iOrdinal = hiRequest.getMethod().ordinal();
        if (iOrdinal == 0) {
            builderTag.get();
        } else if (iOrdinal == 1) {
            builderTag.head();
        } else if (iOrdinal == 2) {
            builderTag.put(change(hiRequest.getBody()));
        } else if (iOrdinal == 3) {
            builderTag.delete(change(hiRequest.getBody()));
        } else if (iOrdinal == 4) {
            builderTag.post(change(hiRequest.getBody()));
        } else {
            if (iOrdinal != 5) {
                StringBuilder sbA = a.a("METHOD = ");
                sbA.append(hiRequest.getMethod());
                throw new IllegalArgumentException(sbA.toString());
            }
            builderTag.patch(change(hiRequest.getBody()));
        }
        return builderTag.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLocalNetworkConnect(IOException iOException) {
        INetworkConnectDetector iNetworkConnectDetector;
        if (iOException == null || (iNetworkConnectDetector = this.mNetworkConnectDetector) == null || iNetworkConnectDetector.isLocalNetworkConnected()) {
            return;
        }
        String message = iOException.getMessage();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(message);
        stringBuffer.append(INetworkConnectDetector.LOCAL_NETWORK_NOT_CONNECTED);
        try {
            ReflectUtil.setField(Throwable.class, iOException, "detailMessage", stringBuffer.toString());
        } catch (Exception e2) {
            HttpLog.e("HTTP2.Stack", e2.getMessage());
        }
    }

    private String getSystemSettingsHostInternal() {
        String str = SystemProperties.get("persist.his.domain", "");
        HttpLog.i("HTTP2.Stack", "persist.his.domain = ", str);
        return str;
    }

    private boolean isValidHostDomain(String str) {
        String[] strArr = {"~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "?", "|", "/", ";", Utils.APP_ID_IDENTIFICATION_SUBSTRING, ",", "=", "+", IidStore.JSON_ENCODED_PREFIX, "}", "[", "]"};
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.contains(str2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HiResponse parseResponse(HiRequest hiRequest, Response response, RequestTag requestTag) throws Exception {
        try {
            return parseResponseImpl(hiRequest, response, requestTag);
        } catch (Exception e2) {
            RequestFailedManager.getInstance().onRequestFailed(hiRequest, requestTag.mTrace, e2);
            throw e2;
        }
    }

    private HiResponse parseResponseImpl(HiRequest hiRequest, Response response, RequestTag requestTag) throws SignatureException {
        ISignature signature = hiRequest.getSignature();
        HttpLog.i("HTTP2.Stack", "response: ", Integer.valueOf(hiRequest.getId()), ", ", Integer.valueOf(response.code()));
        if (!response.isSuccessful()) {
            RequestFailedManager.getInstance().onRequestFailed(hiRequest, requestTag.mTrace, null, new RequestFailedBean(NetworkErrorEnum.UnhappyResponse, hiRequest.getUrl()).subCode(String.valueOf(response.code())).failMsg(response.message()));
        } else if (signature != null) {
            String strString = response.body().string();
            String strHeader = response.header("X-Sign-For");
            String strVerifySigner = TextUtils.isEmpty(strHeader) ? signature.verifySigner(strString) : signature.verifySigner(strString, strHeader);
            if (TextUtils.isEmpty(strVerifySigner)) {
                throw new SignatureException(strVerifySigner);
            }
            HttpLog.i("HTTP2.Stack", "Signer checked! ");
            response = response.newBuilder().body(ResponseBody.create(response.body().contentType(), strVerifySigner)).build();
        }
        if (DebugHelper.isDebugModel()) {
            DebugHelper.getInstance().handleResponse(response, requestTag.debugTrace);
        }
        return change(response, hiRequest);
    }

    private void setInterceptor(HttpStack.HttpStackConfig httpStackConfig, OkHttpClient.Builder builder) {
        ArrayList arrayList = new ArrayList();
        DynamicAddressUseCase dynamicAddressUseCase = this.mDynamicAddress;
        if (dynamicAddressUseCase != null) {
            arrayList.add(new DynamicAddressInterceptor(dynamicAddressUseCase));
        }
        if (this.mSystemSettingsHostDomain != null) {
            HttpLog.i("HTTP2.Stack", "setInterceptor(), has set host domain");
            arrayList.add(new HostReplaceInterceptor(this.mSystemSettingsHostDomain));
        } else {
            HttpLog.i("HTTP2.Stack", "setInterceptor(), has not set valid host domain");
        }
        arrayList.add(new HttpsDegradeInterceptor());
        arrayList.add(new ContentLengthInterceptor());
        if (httpStackConfig.isEnableRetry()) {
            arrayList.add(new RetryInterceptor(httpStackConfig.getBackoffRetry()));
        }
        HttpStack.Dns dns = this.mDns;
        if (dns != null) {
            arrayList.add(new ConnectionFailedInterceptor(dns));
        }
        List<AbsInterceptor> customInterceptors = httpStackConfig.getCustomInterceptors();
        if (customInterceptors != null && !customInterceptors.isEmpty()) {
            arrayList.addAll(customInterceptors);
        }
        Collections.sort(arrayList, new Comparator<AbsInterceptor>() { // from class: com.ju.lib.datacommunication.network.http.impl.HttpStackImpl.1
            @Override // java.util.Comparator
            public int compare(AbsInterceptor absInterceptor, AbsInterceptor absInterceptor2) {
                return absInterceptor.priority().compareTo(absInterceptor2.priority());
            }
        });
        builder.interceptors().addAll(arrayList);
        StringBuilder sbA = a.a(" interceptors: ");
        sbA.append(builder.interceptors());
        HttpLog.i("HTTP2.Stack", sbA.toString());
    }

    private void setNetworkInterceptor(HttpStack.HttpStackConfig httpStackConfig, OkHttpClient.Builder builder) {
        if (httpStackConfig.isEnableLog()) {
            builder.addNetworkInterceptor(new LogInterceptor());
        }
        builder.addNetworkInterceptor(new HeadHostReplaceInterceptor());
        builder.addNetworkInterceptor(new TraceInterceptor());
        if (this.mSystemSettingsHostDomain != null) {
            HttpLog.i("HTTP2.Stack", "setNetworkInterceptor(), has set host domain");
            builder.addNetworkInterceptor(new RedirectHostReplaceInterceptor(this.mSystemSettingsHostDomain));
        } else {
            HttpLog.i("HTTP2.Stack", "setNetworkInterceptor(), has not set valid host domain");
        }
        List<Interceptor> customNetworkInterceptors = httpStackConfig.getCustomNetworkInterceptors();
        if (customNetworkInterceptors == null || customNetworkInterceptors.isEmpty()) {
            return;
        }
        for (Interceptor interceptor : customNetworkInterceptors) {
            HttpLog.i("HTTP2.Stack", "networkInterceptor: ", interceptor);
            builder.addNetworkInterceptor(interceptor);
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HttpStack
    public void cancel(HiRequest hiRequest) {
        if (HttpLog.isHttpStackLogAble()) {
            HttpLog.i("HTTP2.Stack", "cancel: ", Integer.valueOf(hiRequest.getId()), ", ", hiRequest.getUrl());
        }
        getCancelable(hiRequest).cancel();
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HttpStack
    public void cancelAll() {
        HttpLog.d("HTTP2.Stack", "cancelAll");
        this.mClient.dispatcher().cancelAll();
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HttpStack
    public void enqueue(final HiRequest hiRequest, final ICallback iCallback) {
        HttpLog.i("HTTP2.Stack", "enqueue: ", Integer.valueOf(hiRequest.getId()), ", ", hiRequest.getUrl());
        final RequestTag requestTag = new RequestTag(hiRequest);
        if (DebugHelper.isDebugModel()) {
            requestTag.debugTrace = DebugHelper.getInstance().buildDebugTrace(hiRequest, iCallback);
        }
        Call callNewCall = this.mClient.newCall(change(hiRequest, requestTag));
        bindCancelable(hiRequest, new CancelableImpl(callNewCall));
        if (iCallback == null) {
            callNewCall.enqueue(new Callback() { // from class: com.ju.lib.datacommunication.network.http.impl.HttpStackImpl.2
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    HttpLog.w("HTTP2.Stack", iOException, "failure: ", call.request().url());
                    RequestFailedManager.getInstance().onRequestFailed(hiRequest, requestTag.mTrace, iOException);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) {
                    HttpLog.i("HTTP2.Stack", "response: ", Integer.valueOf(hiRequest.getId()), ", ", Integer.valueOf(response.code()));
                    if (response.body() != null) {
                        response.body().close();
                    }
                }
            });
        } else {
            callNewCall.enqueue(new Callback() { // from class: com.ju.lib.datacommunication.network.http.impl.HttpStackImpl.3
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    HttpLog.w("HTTP2.Stack", iOException, "failure: ", call.request().url());
                    HttpStackImpl.this.checkLocalNetworkConnect(iOException);
                    iCallback.failure(hiRequest, requestTag.mTrace, iOException);
                    RequestFailedManager.getInstance().onRequestFailed(hiRequest, requestTag.mTrace, iOException);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) {
                    try {
                        iCallback.response(HttpStackImpl.this.parseResponse(hiRequest, response, requestTag));
                    } catch (HttpException e2) {
                        HttpLog.w("HTTP2.Stack", e2, "Async http error! ");
                        if (e2.getCode() == 1002) {
                            RequestFailedManager requestFailedManager = RequestFailedManager.getInstance();
                            HiRequest hiRequest2 = hiRequest;
                            requestFailedManager.onRequestFailed(hiRequest2, requestTag.mTrace, null, new RequestFailedBean(NetworkErrorEnum.DeserializeException, hiRequest2.getUrl()).failMsg(e2.getMessage()));
                        }
                    } catch (SignatureException e3) {
                        iCallback.failure(hiRequest, requestTag.mTrace, new HttpException(1003, "Verify signature error! ", e3));
                    } catch (Exception e4) {
                        iCallback.failure(hiRequest, requestTag.mTrace, e4);
                    }
                }
            });
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HttpStack
    public HiResponse execute(HiRequest hiRequest) throws HttpException {
        String url = hiRequest.getUrl();
        if (TextUtils.isEmpty(url) || url.startsWith("null") || url.startsWith("NULL")) {
            throw new HttpException(-1, "url is invalid");
        }
        HttpLog.i("HTTP2.Stack", " execute: ", Integer.valueOf(hiRequest.getId()), ", ", hiRequest.getUrl());
        RequestTag requestTag = new RequestTag(hiRequest);
        if (DebugHelper.isDebugModel()) {
            requestTag.debugTrace = DebugHelper.getInstance().buildDebugTrace(hiRequest, null);
        }
        try {
            try {
                Call callNewCall = this.mClient.newCall(change(hiRequest, requestTag));
                bindCancelable(hiRequest, new CancelableImpl(callNewCall));
                try {
                    return parseResponse(hiRequest, callNewCall.execute(), requestTag);
                } catch (IOException e2) {
                    RequestFailedManager.getInstance().onRequestFailed(hiRequest, requestTag.mTrace, e2);
                    throw e2;
                }
            } catch (SignatureException e3) {
                throw new HttpException(1003, "Verify signature error! ", e3);
            }
        } catch (IOException e4) {
            checkLocalNetworkConnect(e4);
            HttpException httpException = new HttpException(1001, "Connect error! ", e4);
            httpException.setTrace(requestTag.mTrace);
            throw httpException;
        }
    }

    public OkHttpClient getOkHttpClient() {
        return this.mClient;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HttpStack
    public List<HiRequest> getRunningRequests() {
        ArrayList arrayList = new ArrayList();
        addList(arrayList, this.mClient.dispatcher().queuedCalls());
        addList(arrayList, this.mClient.dispatcher().runningCalls());
        return arrayList;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HttpStack
    public String getSystemSettingsHost() {
        return this.mSystemSettingsHostDomain;
    }

    @Override // com.ju.lib.datacommunication.network.http.core.HttpStack
    public void initDynamicAddress() {
        DynamicAddressUseCase dynamicAddressUseCase = this.mDynamicAddress;
        if (dynamicAddressUseCase != null) {
            dynamicAddressUseCase.start();
        }
    }

    public HttpStackImpl(HttpStack.HttpStackConfig httpStackConfig, HttpStackImpl httpStackImpl) {
        OkHttpClient.Builder builderNewBuilder;
        HttpLog.d("HTTP2.Stack", httpStackConfig);
        this.mHttpStackConfig = httpStackConfig;
        if (httpStackImpl == null) {
            builderNewBuilder = new OkHttpClient.Builder();
        } else {
            builderNewBuilder = httpStackImpl.mClient.newBuilder();
            builderNewBuilder.interceptors().clear();
            builderNewBuilder.networkInterceptors().clear();
        }
        String systemSettingsHostInternal = getSystemSettingsHostInternal();
        this.mSystemSettingsHostDomain = isValidHostDomain(systemSettingsHostInternal) ? systemSettingsHostInternal : null;
        this.mDynamicAddress = httpStackConfig.getDynamicAddress();
        this.mNetworkConnectDetector = httpStackConfig.getNetworkConnectDetector();
        this.mDns = httpStackConfig.getDns();
        HttpStack.Dns dns = this.mDns;
        if (dns != null) {
            builderNewBuilder.dns(new AnonymousClass4(dns));
        } else {
            builderNewBuilder.dns(Dns.SYSTEM);
        }
        if (httpStackConfig.getCacheDirectory() == null || httpStackConfig.getCacheSize() <= 0) {
            builderNewBuilder.cache(null);
        } else {
            builderNewBuilder.cache(CacheUtils.initDiskCache(httpStackConfig.getCacheDirectory(), httpStackConfig.getCacheSize()));
        }
        if (httpStackConfig.getConnectTimeOut() > 0) {
            builderNewBuilder.connectTimeout(httpStackConfig.getConnectTimeOut(), TimeUnit.MILLISECONDS);
        }
        if (httpStackConfig.getReadTimeOut() > 0) {
            builderNewBuilder.readTimeout(httpStackConfig.getReadTimeOut(), TimeUnit.MILLISECONDS);
        }
        if (httpStackConfig.getSSLSocketFactory() != null && httpStackConfig.getX509TrustManager() != null) {
            builderNewBuilder.sslSocketFactory(httpStackConfig.getSSLSocketFactory(), httpStackConfig.getX509TrustManager());
        }
        if (httpStackConfig.getHostnameVerifier() != null) {
            builderNewBuilder.hostnameVerifier(httpStackConfig.getHostnameVerifier());
        }
        setInterceptor(httpStackConfig, builderNewBuilder);
        setNetworkInterceptor(httpStackConfig, builderNewBuilder);
        this.mClient = builderNewBuilder.build();
    }

    private Dns change(HttpStack.Dns dns) {
        return new AnonymousClass4(dns);
    }

    private RequestBody change(HiRequest.Body body) {
        if (body == null) {
            return null;
        }
        return new OkHttpRequestBody(body);
    }

    private HiResponse change(Response response, HiRequest hiRequest) {
        HiResponse.Builder builder = new HiResponse.Builder();
        builder.request(hiRequest).code(response.code()).message(response.message());
        Headers headers = response.headers();
        for (int i = 0; i < headers.size(); i++) {
            builder.addHeader(headers.name(i), headers.value(i));
        }
        Object objTag = response.request().tag();
        if (objTag instanceof RequestTag) {
            builder.trace(((RequestTag) objTag).mTrace);
        }
        if (response.body() != null) {
            builder.body(change(response.body()));
        }
        return builder.build();
    }

    private HiResponse.Body change(ResponseBody responseBody) {
        if (responseBody == null) {
            return null;
        }
        return new HiResponseBody(responseBody);
    }
}
