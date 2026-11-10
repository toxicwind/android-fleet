package com.ju.lib.datacommunication.network.http.builder;

import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.ICallback;
import com.ju.lib.datacommunication.network.http.core.deserialization.IDeserializer;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public final class PostFileBuilder extends PostBuilder<PostFileBuilder> {
    public File mFile;

    public PostFileBuilder(HiHttpClient hiHttpClient) {
        super(hiHttpClient);
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
    public PostFileBuilder getTypeObject() {
        return this;
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public void onBuild(HiRequest.Builder builder) {
        builder.url(this.mUrl);
        builder.post(HiRequest.createBody(this.mContentType, this.mFile));
    }

    public PostFileBuilder post(File file) {
        this.mFile = file;
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
