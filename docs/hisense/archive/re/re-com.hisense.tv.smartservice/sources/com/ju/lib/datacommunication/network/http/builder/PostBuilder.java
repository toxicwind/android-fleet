package com.ju.lib.datacommunication.network.http.builder;

import com.ju.lib.datacommunication.network.http.builder.PostBuilder;
import com.ju.lib.datacommunication.network.http.core.HiHttpClient;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PostBuilder<T extends PostBuilder<?>> extends HttpRequestBuilder<T> {
    public String mContentType;

    public PostBuilder(HiHttpClient hiHttpClient) {
        super(hiHttpClient);
    }

    public T contentType(String str) {
        this.mContentType = str;
        return getTypeObject();
    }
}
