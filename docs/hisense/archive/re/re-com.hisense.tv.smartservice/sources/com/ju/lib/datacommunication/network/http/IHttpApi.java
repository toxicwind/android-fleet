package com.ju.lib.datacommunication.network.http;

import com.ju.lib.datacommunication.annotation.JuMethodAnnotation;
import com.ju.lib.datacommunication.network.http.builder.GetBuilder;
import com.ju.lib.datacommunication.network.http.builder.HeadBuilder;
import com.ju.lib.datacommunication.network.http.builder.OtherRequestBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostFileBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostFormBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostMultipartBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostStringBuilder;
import com.ju.lib.datacommunication.network.http.core.HiHttpClient;
import com.ju.lib.datacommunication.network.http.core.HiRequest;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.ICallback;
import com.ju.lib.datacommunication.network.http.core.deserialization.IDeserializer;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes2.dex */
public interface IHttpApi {
    void cancel(int i);

    void cancel(HiRequest hiRequest);

    void cancel(String str);

    void cancelAll();

    void cancelById(int i);

    void cancelByTag(Object obj);

    OtherRequestBuilder delete();

    void enqueue(HiRequest hiRequest, ICallback iCallback);

    HiResponse execute(HiRequest hiRequest);

    <T> T execute(HiRequest hiRequest, Class<T> cls);

    <T> T execute(HiRequest hiRequest, Class<T> cls, IDeserializer iDeserializer);

    GetBuilder get();

    HiHttpClient getHttpClient();

    OkHttpClient getOkHttpClient();

    @JuMethodAnnotation(description = "获取整机预埋的域名", parameters = "", returnDes = "String")
    String getSystemSettingsHost();

    HeadBuilder head();

    OtherRequestBuilder patch();

    PostFileBuilder postFile();

    PostFormBuilder postForm();

    PostMultipartBuilder postMultipart();

    PostStringBuilder postString();

    OtherRequestBuilder put();
}
