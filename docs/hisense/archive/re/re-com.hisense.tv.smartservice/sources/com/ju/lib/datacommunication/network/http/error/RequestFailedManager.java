package com.ju.lib.datacommunication.network.http.error;

import com.google.firebase.installations.Utils;
import com.ju.lib.datacommunication.network.http.INetworkConnectDetector;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.request.HiFormBody;
import com.ju.lib.datacommunication.network.http.core.request.HiStringBody;
import com.ju.lib.datacommunication.network.http.core.signature.SignatureException;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import javax.net.ssl.SSLException;

/* JADX INFO: loaded from: classes2.dex */
public class RequestFailedManager {
    public static final String TAG = "RequestFailedManager";
    public static volatile RequestFailedManager sInstance;
    public INetworkConnectDetector mNetworkConnectDetector;
    public IRequestFailedListener requestFailedListener;

    public static RequestFailedManager getInstance() {
        if (sInstance == null) {
            synchronized (RequestFailedManager.class) {
                if (sInstance == null) {
                    sInstance = new RequestFailedManager();
                }
            }
        }
        return sInstance;
    }

    private NetworkErrorEnum parseCode(Exception exc) {
        if (exc == null) {
            return NetworkErrorEnum.UnknownError;
        }
        HttpLog.d(TAG, " parseCode class: ", exc.getClass());
        NetworkErrorEnum networkErrorEnum = NetworkErrorEnum.UnknownError;
        if (exc instanceof UnknownHostException) {
            return NetworkErrorEnum.UnknownHostException;
        }
        if (exc instanceof ConnectException) {
            return NetworkErrorEnum.ConnectException;
        }
        if (exc instanceof SocketTimeoutException) {
            return NetworkErrorEnum.SocketTimeoutException;
        }
        if (exc instanceof SSLException) {
            return NetworkErrorEnum.SSLException;
        }
        return exc instanceof SignatureException ? NetworkErrorEnum.SignatureException : networkErrorEnum;
    }

    private String parseIp(HiResponse.Trace trace) {
        if (trace == null || trace.getAddress() == null || trace.getAddress().getAddress() == null) {
            return null;
        }
        return trace.getAddress().getAddress().getHostAddress() + Utils.APP_ID_IDENTIFICATION_SUBSTRING + trace.getAddress().getPort();
    }

    public void onRequestFailed(HiRequest hiRequest, HiResponse.Trace trace, Exception exc, RequestFailedBean requestFailedBean) {
        if (hiRequest == null) {
            HttpLog.e(TAG, " onRequestFailed request should not be null ");
            return;
        }
        if (requestFailedBean == null) {
            requestFailedBean = new RequestFailedBean(parseCode(exc), hiRequest.getUrl()).failMsg(exc != null ? exc.getMessage() : "Unknown");
        }
        INetworkConnectDetector iNetworkConnectDetector = this.mNetworkConnectDetector;
        if (iNetworkConnectDetector != null && !iNetworkConnectDetector.isLocalNetworkConnected()) {
            requestFailedBean.code(NetworkErrorEnum.LocalNetworkDisConnect);
        }
        requestFailedBean.ip(parseIp(trace));
        if (hiRequest.getMethod() == HiRequest.Method.POST) {
            requestFailedBean.requestParams(parseQuery(hiRequest));
        }
        IRequestFailedListener iRequestFailedListener = this.requestFailedListener;
        if (iRequestFailedListener != null) {
            iRequestFailedListener.onException(requestFailedBean);
        } else {
            HttpLog.w(TAG, " requestFailedListener is null， requestFailedBean: ", requestFailedBean);
        }
    }

    public HashMap<String, String> parseQuery(HiRequest hiRequest) {
        HiRequest.Body body = hiRequest.getBody();
        if (body == null) {
            return null;
        }
        if (body instanceof HiFormBody) {
            return ((HiFormBody) body).getContent();
        }
        if (body instanceof HiStringBody) {
            return ((HiStringBody) body).getContent();
        }
        return null;
    }

    public void setNetworkConnectDetector(INetworkConnectDetector iNetworkConnectDetector) {
        if (iNetworkConnectDetector != null) {
            this.mNetworkConnectDetector = iNetworkConnectDetector;
        } else {
            HttpLog.e(TAG, " mNetworkConnectDetector should not be null ");
        }
    }

    public void setRequestFailedListener(IRequestFailedListener iRequestFailedListener) {
        this.requestFailedListener = iRequestFailedListener;
    }

    public void onRequestFailed(HiRequest hiRequest, HiResponse.Trace trace, Exception exc) {
        onRequestFailed(hiRequest, trace, exc, null);
    }
}
