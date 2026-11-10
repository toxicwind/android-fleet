package com.ju.lib.datacommunication.network.http.core.callback;

import com.ju.lib.datacommunication.network.http.core.HiResponse;

/* JADX INFO: loaded from: classes2.dex */
public abstract class StringCallback extends AbstractCallback<String> {
    @Override // com.ju.lib.datacommunication.network.http.core.callback.AbstractCallback
    public final String parse(HiResponse hiResponse) {
        return hiResponse.getBody().string();
    }
}
