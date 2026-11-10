package com.ju.lib.datacommunication.network.http.builder;

import com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder;
import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.ICallback;
import com.ju.lib.datacommunication.network.http.core.deserialization.DeserializeException;
import com.ju.lib.datacommunication.network.http.core.deserialization.IDeserializer;
import com.ju.lib.datacommunication.network.http.core.deserialization.JsonDeserializer;
import com.ju.lib.datacommunication.network.http.core.signature.ISignature;
import com.ju.lib.datacommunication.network.http.error.NetworkErrorEnum;
import com.ju.lib.datacommunication.network.http.error.RequestFailedBean;
import com.ju.lib.datacommunication.network.http.error.RequestFailedManager;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class HttpRequestBuilder<T extends HttpRequestBuilder<?>> {
    public HiHttpClient mClient;
    public final HiRequest.Builder mRequestBuilder = new HiRequest.Builder();
    public String mUrl;

    public HttpRequestBuilder(HiHttpClient hiHttpClient) {
        this.mClient = hiHttpClient;
    }

    public T addHeader(String str, String str2) {
        this.mRequestBuilder.addHeader(str, str2);
        return (T) getTypeObject();
    }

    public void enqueue(ICallback iCallback) {
        onBuild(this.mRequestBuilder);
        this.mClient.enqueue(this.mRequestBuilder.build(), iCallback);
    }

    public HiResponse execute() {
        onBuild(this.mRequestBuilder);
        return this.mClient.execute(this.mRequestBuilder.build());
    }

    public abstract T getTypeObject();

    public T header(String str, String str2) {
        this.mRequestBuilder.header(str, str2);
        return (T) getTypeObject();
    }

    public T hearders(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.mRequestBuilder.header(entry.getKey(), entry.getValue());
        }
        return (T) getTypeObject();
    }

    public void onBuild(HiRequest.Builder builder) {
        builder.url(this.mUrl);
    }

    public T replaceHost(String str, String str2) {
        this.mRequestBuilder.replaceHost(str, str2);
        return (T) getTypeObject();
    }

    public T retry(int i) {
        this.mRequestBuilder.retry(i);
        return (T) getTypeObject();
    }

    public T signature(ISignature iSignature) {
        this.mRequestBuilder.signature(iSignature);
        return (T) getTypeObject();
    }

    public T tag(Object obj) {
        this.mRequestBuilder.tag(obj);
        return (T) getTypeObject();
    }

    public T tagCode(int i) {
        this.mRequestBuilder.tagCode(i);
        return (T) getTypeObject();
    }

    public T url(String str) {
        this.mUrl = str;
        return (T) getTypeObject();
    }

    public <E> E execute(Class<E> cls) {
        return (E) execute(cls, new JsonDeserializer());
    }

    public <E> E execute(Class<E> cls, IDeserializer iDeserializer) throws HttpException {
        HiResponse hiResponseExecute = execute();
        if (hiResponseExecute.isSuccessful()) {
            try {
                return (E) iDeserializer.transform(hiResponseExecute.getBody().string(), cls);
            } catch (DeserializeException e2) {
                RequestFailedManager.getInstance().onRequestFailed(hiResponseExecute.getRequest(), hiResponseExecute.getTrace(), null, new RequestFailedBean(NetworkErrorEnum.DeserializeException, this.mUrl).failMsg(e2.getMessage()));
                throw new HttpException(1002, e2);
            }
        }
        throw new HttpException(hiResponseExecute.getCode(), hiResponseExecute.getMessage());
    }
}
