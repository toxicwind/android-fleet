package com.ju.lib.datacommunication.network.http;

import com.ju.lib.datacommunication.network.http.builder.GetBuilder;
import com.ju.lib.datacommunication.network.http.builder.HeadBuilder;
import com.ju.lib.datacommunication.network.http.builder.OtherRequestBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostFileBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostFormBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostMultipartBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostStringBuilder;
import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.ICallback;
import com.ju.lib.datacommunication.network.http.core.deserialization.DeserializeException;
import com.ju.lib.datacommunication.network.http.core.deserialization.IDeserializer;
import com.ju.lib.datacommunication.network.http.core.deserialization.JsonDeserializer;
import com.ju.lib.datacommunication.network.http.impl.HttpStackImpl;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes2.dex */
public class HttpApiImpl implements IHttpApi {
    public final HiHttpClient mClient;

    public HttpApiImpl(HiHttpClient hiHttpClient) {
        this.mClient = hiHttpClient;
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public void cancel(HiRequest hiRequest) {
        this.mClient.cancel(hiRequest);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public void cancelAll() {
        this.mClient.cancelAll();
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public void cancelById(int i) {
        this.mClient.cancelById(i);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public void cancelByTag(Object obj) {
        this.mClient.cancelByTag(obj);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public OtherRequestBuilder delete() {
        return new OtherRequestBuilder(this.mClient, HiRequest.Method.DELETE);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public void enqueue(HiRequest hiRequest, ICallback iCallback) {
        this.mClient.enqueue(hiRequest, iCallback);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public HiResponse execute(HiRequest hiRequest) {
        return this.mClient.execute(hiRequest);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public GetBuilder get() {
        return new GetBuilder(this.mClient);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public HiHttpClient getHttpClient() {
        return this.mClient;
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public OkHttpClient getOkHttpClient() {
        return ((HttpStackImpl) this.mClient.getStack()).getOkHttpClient();
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public String getSystemSettingsHost() {
        return this.mClient.getSystemSettingsHost();
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public HeadBuilder head() {
        return new HeadBuilder(this.mClient);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public OtherRequestBuilder patch() {
        return new OtherRequestBuilder(this.mClient, HiRequest.Method.PATCH);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public PostFileBuilder postFile() {
        return new PostFileBuilder(this.mClient);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public PostFormBuilder postForm() {
        return new PostFormBuilder(this.mClient);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public PostMultipartBuilder postMultipart() {
        return new PostMultipartBuilder(this.mClient);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public PostStringBuilder postString() {
        return new PostStringBuilder(this.mClient);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public OtherRequestBuilder put() {
        return new OtherRequestBuilder(this.mClient, HiRequest.Method.PUT);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public void cancel(int i) {
        this.mClient.cancel(i);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public <T> T execute(HiRequest hiRequest, Class<T> cls) {
        return (T) execute(hiRequest, cls, new JsonDeserializer());
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public void cancel(String str) {
        this.mClient.cancel(str);
    }

    @Override // com.ju.lib.datacommunication.network.http.IHttpApi
    public <T> T execute(HiRequest hiRequest, Class<T> cls, IDeserializer iDeserializer) throws HttpException {
        HiResponse hiResponseExecute = execute(hiRequest);
        if (hiResponseExecute.isSuccessful()) {
            try {
                return (T) iDeserializer.transform(hiResponseExecute.getBody().string(), cls);
            } catch (DeserializeException e2) {
                throw new HttpException(1002, "Parse response error! ", e2);
            }
        }
        throw new HttpException(hiResponseExecute.getCode(), hiResponseExecute.getMessage());
    }
}
