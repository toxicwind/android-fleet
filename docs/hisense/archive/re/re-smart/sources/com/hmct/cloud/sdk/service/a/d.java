package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.ElecManualService;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class d extends ElecManualService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile ElecManualService f2009b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2010c = "{\"success\":false,\"error\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2011d = "networkError";

    public d(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static ElecManualService a(IHttpApi iHttpApi) {
        if (f2009b == null) {
            synchronized (d.class) {
                if (f2009b == null) {
                    f2009b = new d(iHttpApi);
                }
            }
        }
        return f2009b;
    }

    @Override // com.hmct.cloud.sdk.service.ElecManualService
    public String elecDelete(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "elec_info/elec_delete", map);
    }

    @Override // com.hmct.cloud.sdk.service.ElecManualService
    public String elecInfoUpload(String str, boolean z, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("ElecManualService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        return b(((ElecManualService) this).f1979a, HiCloudService.a(str, "elec_info/upload", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2010c, f2011d);
    }

    @Override // com.hmct.cloud.sdk.service.ElecManualService
    public String elecQuery(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "elec_info/elec_query", map);
    }

    @Override // com.hmct.cloud.sdk.service.ElecManualService
    public String fuzzySearch(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "elec_info/fuzzy_search", map);
    }

    @Override // com.hmct.cloud.sdk.service.ElecManualService
    public String getElecInfoDownloadDetail(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "elec_info/download_detail", map);
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("ElecManualService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        return a(((ElecManualService) this).f1979a, HiCloudService.a(str, str2, z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2010c, f2011d);
    }
}
