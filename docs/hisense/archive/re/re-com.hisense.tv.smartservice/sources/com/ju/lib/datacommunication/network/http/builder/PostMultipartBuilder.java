package com.ju.lib.datacommunication.network.http.builder;

import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiHttpHeaders;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.ICallback;
import com.ju.lib.datacommunication.network.http.core.deserialization.IDeserializer;
import com.ju.lib.datacommunication.network.http.core.request.HiMultipartBody;

/* JADX INFO: loaded from: classes2.dex */
public class PostMultipartBuilder extends PostBuilder<PostMultipartBuilder> {
    public HiMultipartBody.Builder mBuilder;

    public PostMultipartBuilder(HiHttpClient hiHttpClient) {
        super(hiHttpClient);
        this.mBuilder = new HiMultipartBody.Builder();
    }

    public PostMultipartBuilder addFormDataPart(String str, String str2) {
        return addPart(HiMultipartBody.Part.createFormData(str, str2));
    }

    public PostMultipartBuilder addPart(HiRequest.Body body) {
        return addPart(HiMultipartBody.Part.create(body));
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public /* bridge */ /* synthetic */ void enqueue(ICallback iCallback) {
        super.enqueue(iCallback);
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public /* bridge */ /* synthetic */ HiResponse execute() {
        return super.execute();
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public PostMultipartBuilder getTypeObject() {
        return this;
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public void onBuild(HiRequest.Builder builder) {
        builder.url(this.mUrl);
        builder.post(this.mBuilder.build());
    }

    public PostMultipartBuilder addFormDataPart(String str, String str2, HiRequest.Body body) {
        return addPart(HiMultipartBody.Part.createFormData(str, str2, body));
    }

    public PostMultipartBuilder addPart(HiHttpHeaders hiHttpHeaders, HiRequest.Body body) {
        return addPart(HiMultipartBody.Part.create(hiHttpHeaders, body));
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public /* bridge */ /* synthetic */ Object execute(Class cls) {
        return super.execute(cls);
    }

    public PostMultipartBuilder addPart(HiMultipartBody.Part part) {
        this.mBuilder.addPart(part);
        return getTypeObject();
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public /* bridge */ /* synthetic */ Object execute(Class cls, IDeserializer iDeserializer) {
        return super.execute(cls, iDeserializer);
    }
}
