package com.ju.lib.datacommunication.network.http.builder;

import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.ICallback;
import com.ju.lib.datacommunication.network.http.core.deserialization.IDeserializer;

/* JADX INFO: loaded from: classes2.dex */
public final class GetBuilder extends QueryBuilder<GetBuilder> {
    public GetBuilder(HiHttpClient hiHttpClient) {
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
    public GetBuilder getTypeObject() {
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
