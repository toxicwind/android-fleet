package com.ju.lib.datacommunication.network.http.core;

/* JADX INFO: loaded from: classes2.dex */
public interface ICallback {
    void failure(HiRequest hiRequest, HiResponse.Trace trace, Exception exc);

    void response(HiResponse hiResponse);
}
