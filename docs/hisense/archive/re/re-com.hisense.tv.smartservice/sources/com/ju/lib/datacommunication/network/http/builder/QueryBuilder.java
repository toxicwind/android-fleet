package com.ju.lib.datacommunication.network.http.builder;

import com.ju.lib.datacommunication.network.http.builder.QueryBuilder;
import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class QueryBuilder<T extends QueryBuilder<?>> extends HttpRequestBuilder<T> {
    public Map<String, String> mQuery;

    public QueryBuilder(HiHttpClient hiHttpClient) {
        super(hiHttpClient);
        this.mQuery = new HashMap();
    }

    public T addQueryParams(Map<String, String> map) {
        this.mQuery.putAll(map);
        return getTypeObject();
    }

    @Override // com.ju.lib.datacommunication.network.http.builder.HttpRequestBuilder
    public void onBuild(HiRequest.Builder builder) {
        builder.get();
        builder.url(this.mUrl, this.mQuery);
    }

    public T addQueryParams(String str, String str2) {
        this.mQuery.put(str, str2);
        return getTypeObject();
    }
}
