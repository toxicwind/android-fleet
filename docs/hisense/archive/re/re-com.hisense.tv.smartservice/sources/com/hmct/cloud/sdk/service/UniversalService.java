package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.bean.UniversalBean;
import com.hmct.cloud.sdk.service.a.s;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.ISignature;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class UniversalService extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1995a;

    public UniversalService(IHttpApi iHttpApi) {
        this.f1995a = iHttpApi;
    }

    public static UniversalService getService(IHttpApi iHttpApi) {
        return s.a(iHttpApi);
    }

    public abstract UniversalBean get(String str, String str2, boolean z);

    public abstract UniversalBean get(String str, String str2, boolean z, ISignature iSignature);

    public abstract UniversalBean get(String str, String str2, boolean z, ISignature iSignature, HashMap<String, String> map, String str3, String str4);

    public abstract UniversalBean get(String str, String str2, boolean z, HashMap<String, String> map, String str3, String str4);

    public abstract UniversalBean get(String str, HashMap<String, String> map, boolean z);

    public abstract UniversalBean get(String str, HashMap<String, String> map, boolean z, ISignature iSignature);

    public abstract UniversalBean get(String str, HashMap<String, String> map, boolean z, ISignature iSignature, HashMap<String, String> map2, String str2, String str3);

    public abstract UniversalBean get(String str, HashMap<String, String> map, boolean z, HashMap<String, String> map2, String str2, String str3);

    public abstract UniversalBean post(String str, String str2, boolean z);

    public abstract UniversalBean post(String str, String str2, boolean z, ISignature iSignature);

    public abstract UniversalBean post(String str, String str2, boolean z, ISignature iSignature, HashMap<String, String> map, String str3, String str4);

    public abstract UniversalBean post(String str, String str2, boolean z, HashMap<String, String> map, String str3, String str4);

    public abstract UniversalBean post(String str, HashMap<String, String> map, boolean z);

    public abstract UniversalBean post(String str, HashMap<String, String> map, boolean z, ISignature iSignature);

    public abstract UniversalBean post(String str, HashMap<String, String> map, boolean z, ISignature iSignature, HashMap<String, String> map2, String str2, String str3);

    public abstract UniversalBean post(String str, HashMap<String, String> map, boolean z, HashMap<String, String> map2, String str2, String str3);
}
