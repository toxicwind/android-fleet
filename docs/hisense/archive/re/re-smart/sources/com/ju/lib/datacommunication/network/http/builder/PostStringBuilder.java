package com.ju.lib.datacommunication.network.http.builder;

import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.ICallback;
import com.ju.lib.datacommunication.network.http.core.deserialization.IDeserializer;
import com.ju.lib.datacommunication.network.http.core.request.HiStringBody;

/* JADX INFO: loaded from: classes2.dex */
public final class PostStringBuilder extends PostBuilder<PostStringBuilder> {
    public String mContent;

    public PostStringBuilder(HiHttpClient hiHttpClient) {
        super(hiHttpClient);
    }

    public PostStringBuilder content(String str) {
        this.mContent = str;
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
    public PostStringBuilder getTypeObject() {
        return this;
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public void onBuild(HiRequest.Builder builder) {
        builder.url(this.mUrl);
        builder.post(new HiStringBody(HiRequest.createBody(this.mContentType, this.mContent), this.mContent));
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
