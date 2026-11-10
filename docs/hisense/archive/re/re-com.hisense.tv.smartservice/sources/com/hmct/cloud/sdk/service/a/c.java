package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.bean.remotectl.LatestVersionInfoRely;
import com.hmct.cloud.sdk.service.DeviceRemoteCtlService;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.utils.Constants;
import com.hmct.cloud.sdk.utils.Params;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class c extends DeviceRemoteCtlService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile DeviceRemoteCtlService f2007b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2008c = "networkError";

    public c(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static DeviceRemoteCtlService a(IHttpApi iHttpApi) {
        if (f2007b == null) {
            synchronized (c.class) {
                if (f2007b == null) {
                    f2007b = new c(iHttpApi);
                }
            }
        }
        return f2007b;
    }

    @Override // com.hmct.cloud.sdk.service.DeviceRemoteCtlService
    public LatestVersionInfoRely getLatestVersionInfo(String str, boolean z, HashMap<String, String> map) {
        String strA;
        try {
            if (TextUtils.isEmpty(str)) {
                HttpLog.e("DeviceRemoteCtlService", "domainName is null");
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
                map2.put(Params.FORMAT, "1");
                map2.put(Params.LANGUAGEID, "0");
                a(map2);
                strA = a(((DeviceRemoteCtlService) this).f1978a, HiCloudService.a(str, "device/remoteCtl/getLatestVersionInfo", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), "{\"response\":{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\",\"resultcode\":1,\"errorcode\":\"000000\",\"errordesc\":\"networkError\"}}", f2008c);
            }
            return com.hmct.cloud.sdk.a.a.b(strA);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.DeviceRemoteCtlService
    public ReplyInfo uploadCmdResult(String str, boolean z, HashMap<String, String> map) {
        String strB;
        try {
            if (TextUtils.isEmpty(str)) {
                HttpLog.e("DeviceRemoteCtlService", "domainName is null");
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
                map2.put(Params.FORMAT, "1");
                map2.put(Params.LANGUAGEID, "0");
                a(map2);
                HttpLog.d("DeviceRemoteCtlService", "postparams is " + map2);
                strB = b(((DeviceRemoteCtlService) this).f1978a, HiCloudService.a(str, "device/remoteCtl/uploadCmdResult", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), "{\"response\":{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\",\"resultcode\":1,\"errorcode\":\"000000\",\"errordesc\":\"networkError\"}}", f2008c);
            }
            return com.hmct.cloud.sdk.a.a.a(strB);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
