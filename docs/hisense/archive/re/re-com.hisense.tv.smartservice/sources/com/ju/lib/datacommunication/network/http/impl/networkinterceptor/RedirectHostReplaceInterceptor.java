package com.ju.lib.datacommunication.network.http.impl.networkinterceptor;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.google.firebase.storage.network.NetworkRequest;
import com.ju.lib.datacommunication.network.http.impl.AbsHostReplaceInterceptor;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.net.URL;
import okhttp3.Interceptor;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class RedirectHostReplaceInterceptor extends AbsHostReplaceInterceptor {
    public static final String TAG = "HTTP2.302Interceptor";

    public RedirectHostReplaceInterceptor(String str) {
        super(str);
    }

    private Response handleRedirectHost(Response response) {
        if (response == null) {
            return null;
        }
        int iCode = response.code();
        String strMethod = response.request().method();
        if (iCode != 307 && iCode != 308) {
            switch (iCode) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return response;
            }
        } else if (!strMethod.equals(NetworkRequest.GET) && !strMethod.equals("HEAD")) {
            return response;
        }
        String strHeader = response.header(HttpHeaders.LOCATION);
        if (TextUtils.isEmpty(strHeader)) {
            return response;
        }
        try {
            URL url = new URL(strHeader);
            if (!needChangedHost(url.getHost())) {
                return response;
            }
            String strReplaceFirst = strHeader.replaceFirst(url.getHost(), createNewHost(url.getHost()));
            HttpLog.i(getTag(), "handleRedirectHost(), location = ", strHeader, " , newLocation = ", strReplaceFirst);
            return response.newBuilder().header(HttpHeaders.LOCATION, strReplaceFirst).build();
        } catch (Exception e2) {
            e2.printStackTrace();
            return response;
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsHostReplaceInterceptor
    public String getTag() {
        return TAG;
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsHostReplaceInterceptor
    public Response interceptCustomImp(Interceptor.Chain chain) {
        if (HttpLog.isHttpStackLogAble()) {
            HttpLog.i(getTag(), "interceptCustomImp(), request = ", chain.request());
        }
        Response responseProceed = chain.proceed(chain.request());
        Response responseHandleRedirectHost = handleRedirectHost(responseProceed);
        if (HttpLog.isHttpStackLogAble()) {
            HttpLog.i(getTag(), "interceptCustomImp(), originalResponse = ", responseProceed.toString(), " ,-------- header = ", responseProceed.headers().toString());
            HttpLog.i(getTag(), "interceptCustomImp(), response = ", responseHandleRedirectHost.toString(), " ,-------- header = ", responseHandleRedirectHost.headers().toString());
        }
        return responseHandleRedirectHost;
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.AbsInterceptor
    public InterceptorPriority priority() {
        return InterceptorPriority.NORMAL;
    }
}
