package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.VoiceService;
import com.hmct.cloud.sdk.utils.AESUtil;
import com.hmct.cloud.sdk.utils.Params;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.util.HashMap;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class x extends VoiceService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile VoiceService f2064b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2065c = "{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\"}";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f2066d = "networkError";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f2067e = "D5B6D8584F94B432";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static String f2068f = "205681D89D731A8F";

    public x(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static VoiceService a(IHttpApi iHttpApi) {
        if (f2064b == null) {
            synchronized (x.class) {
                if (f2064b == null) {
                    f2064b = new x(iHttpApi);
                }
            }
        }
        return f2064b;
    }

    @Override // com.hmct.cloud.sdk.service.VoiceService
    public String getCodeAuthUrl(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "voiceservice/get_codeauth_url", map);
    }

    @Override // com.hmct.cloud.sdk.service.VoiceService
    public String getDevicePortalUrl(String str, boolean z, HashMap<String, String> map) {
        return a(str, z, "voiceservice/get_deviceportal_url", map);
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        String str3;
        String strByte2string = null;
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("VoiceService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map == null || map.isEmpty()) {
            str3 = "";
        } else {
            str3 = map.get(Params.APPSECRET);
            map2.putAll(map);
        }
        HashMap<String, String> map3 = new HashMap<>();
        map3.put("version", "2.1");
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        map3.put("timeStamp", sb.toString());
        map3.put(Params.FORMAT, "1");
        map3.put(Params.LANGUAGEID, "1");
        map3.put(Params.INVOKESOURCE, "1");
        map3.put(Params.RANDSTR, UUID.randomUUID().toString());
        setSystemParams(map3);
        a(map2);
        String str4 = f2067e;
        String str5 = f2068f;
        if (TextUtils.isEmpty(str3)) {
            strByte2string = "";
        } else {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(str4.getBytes("UTF-8"), "AES");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(str5.getBytes("UTF-8"));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(1, secretKeySpec, ivParameterSpec);
                strByte2string = AESUtil.byte2string(cipher.doFinal(str3.getBytes("UTF-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        map2.put(Params.APPSECRET, strByte2string);
        return b(((VoiceService) this).f2000a, HiCloudService.a(str, str2, z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2065c, f2066d);
    }
}
