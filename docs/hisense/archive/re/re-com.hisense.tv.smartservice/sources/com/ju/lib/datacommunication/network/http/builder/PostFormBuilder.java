package com.ju.lib.datacommunication.network.http.builder;

import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.ICallback;
import com.ju.lib.datacommunication.network.http.core.deserialization.IDeserializer;
import com.ju.lib.datacommunication.network.http.core.request.HiFormBody;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class PostFormBuilder extends PostBuilder<PostFormBuilder> {
    public HiFormBody.Builder mBuilder;

    public PostFormBuilder(HiHttpClient hiHttpClient) {
        super(hiHttpClient);
        this.mBuilder = new HiFormBody.Builder();
    }

    public PostFormBuilder addParams(String str, String str2) {
        this.mBuilder.add(str, str2);
        return this;
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
    public PostFormBuilder getTypeObject() {
        return this;
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public void onBuild(HiRequest.Builder builder) {
        builder.url(this.mUrl);
        builder.post(this.mBuilder.build());
    }

    public PostFormBuilder params(Map<String, String> map) {
        this.mBuilder.add(map);
        return this;
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public /* bridge */ /* synthetic */ Object execute(Class cls) {
        return super.execute(cls);
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public /* bridge */ /* synthetic */ Object execute(Class cls, IDeserializer iDeserializer) {
        return super.execute(cls, iDeserializer);
    }
}
