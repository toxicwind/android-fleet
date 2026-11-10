package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.MessageService;
import com.hmct.cloud.sdk.utils.Constants;
import com.hmct.cloud.sdk.utils.Params;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class n extends MessageService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile MessageService f2039b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2040c = "networkError";

    public n(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static MessageService a(IHttpApi iHttpApi) {
        if (f2039b == null) {
            synchronized (n.class) {
                if (f2039b == null) {
                    f2039b = new n(iHttpApi);
                }
            }
        }
        return f2039b;
    }

    @Override // com.hmct.cloud.sdk.service.MessageService
    public String getMsg(String str, boolean z, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UpgradeService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        map2.put("version", Constants.DEFAULTAPIVERSION);
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        map2.put("timeStamp", sb.toString());
        map2.put(Params.FORMAT, "1");
        map2.put(Params.LANGUAGEID, "0");
        map2.put(Params.INVOKESOURCE, "1");
        a(map2);
        return b(((MessageService) this).f1990a, HiCloudService.a(str, "message/get_msg", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", f2040c);
    }

    @Override // com.hmct.cloud.sdk.service.MessageService
    public String getMsgChannel(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "message/get_msg_channel", map);
    }

    @Override // com.hmct.cloud.sdk.service.MessageService
    public String getMsgConfigs(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "message/get_msg_configs", map);
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UpgradeService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        map2.put("version", Constants.DEFAULTAPIVERSION);
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        map2.put("timeStamp", sb.toString());
        map2.put(Params.FORMAT, "1");
        map2.put(Params.LANGUAGEID, "0");
        map2.put(Params.INVOKESOURCE, "1");
        a(map2);
        return a(((MessageService) this).f1990a, HiCloudService.a(str, str2, z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", f2040c);
    }
}
