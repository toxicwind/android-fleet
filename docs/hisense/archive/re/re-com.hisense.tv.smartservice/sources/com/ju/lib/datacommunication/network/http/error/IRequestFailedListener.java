package com.ju.lib.datacommunication.network.http.error;

import com.ju.lib.datacommunication.annotation.JuMethodAnnotation;

/* JADX INFO: loaded from: classes2.dex */
public interface IRequestFailedListener {
    @JuMethodAnnotation(description = "网络请求出错时，回调", parameters = "RequestFailedBean", returnDes = "")
    void onException(RequestFailedBean requestFailedBean);
}
