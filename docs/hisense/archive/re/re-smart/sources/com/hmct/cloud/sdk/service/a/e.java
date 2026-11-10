package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.bean.basp.AllocNewMacReply;
import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.service.EsnService;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.utils.Constants;
import com.hmct.cloud.sdk.utils.Params;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.XmlSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class e extends EsnService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile EsnService f2012b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2013c = "networkError";

    public e(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static EsnService a(IHttpApi iHttpApi) {
        if (f2012b == null) {
            synchronized (e.class) {
                if (f2012b == null) {
                    f2012b = new e(iHttpApi);
                }
            }
        }
        return f2012b;
    }

    @Override // com.hmct.cloud.sdk.service.EsnService
    public AllocNewMacReply allocNewMac(String str, boolean z, HashMap<String, String> map) {
        String strA;
        try {
            if (TextUtils.isEmpty(str)) {
                HttpLog.e("BaspService", "domainName is null");
                strA = null;
            } else {
                HashMap<String, String> map2 = new HashMap<>();
                if (map != null && !map.isEmpty()) {
                    map2.putAll(map);
                }
                map2.put("version", Constants.DEFAULTAPIVERSION);
                StringBuilder sb = new StringBuilder();
                sb.append(System.currentTimeMillis() / 1000);
                map2.put("timeStamp", sb.toString());
                map2.put(Params.FORMAT, "0");
                map2.put(Params.LANGUAGEID, "0");
                a(map2);
                strA = a(((EsnService) this).f1980a, HiCloudService.a(str, "esnservice/alloc_new_mac", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), new XmlSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", f2013c);
            }
            return com.hmct.cloud.sdk.a.a.c(strA);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.EsnService
    public ReplyInfo reportAllocNewMacEvent(String str, boolean z, HashMap<String, String> map) {
        String strB;
        try {
            if (TextUtils.isEmpty(str)) {
                HttpLog.e("BaspService", "domainName is null");
                strB = null;
            } else {
                HashMap<String, String> map2 = new HashMap<>();
                if (map != null && !map.isEmpty()) {
                    map2.putAll(map);
                }
                map2.put("version", Constants.DEFAULTAPIVERSION);
                StringBuilder sb = new StringBuilder();
                sb.append(System.currentTimeMillis() / 1000);
                map2.put("timeStamp", sb.toString());
                map2.put(Params.FORMAT, "0");
                map2.put(Params.LANGUAGEID, "0");
                a(map2);
                HttpLog.d("BaspService", "postparams is " + map2);
                strB = b(((EsnService) this).f1980a, HiCloudService.a(str, "esnservice/report_alloc_new_mac_event", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), new XmlSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", f2013c);
            }
            return com.hmct.cloud.sdk.a.a.d(strB);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
