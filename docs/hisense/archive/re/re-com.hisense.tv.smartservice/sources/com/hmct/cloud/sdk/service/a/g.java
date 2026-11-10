package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.bean.account.CustomerInfo;
import com.hmct.cloud.sdk.bean.account.DevSerialReply;
import com.hmct.cloud.sdk.bean.account.SignonReplyInfo;
import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.service.HiCloudAccountService;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.utils.AESUtil;
import com.hmct.cloud.sdk.utils.Constants;
import com.hmct.cloud.sdk.utils.Params;
import com.hmct.cloud.sdk.utils.SDKUtil;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.core.signature.XmlSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class g extends HiCloudAccountService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f2017b = "D5B6D8584F94B432";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2018c = "205681D89D731A8F";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile HiCloudAccountService f2019d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f2020e = "{\"resultCode\":1,\"errorCode\":\"000000\",\"errorDesc\":\"networkError\"}";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static String f2021f = "networkError";

    public g(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static HiCloudAccountService a(IHttpApi iHttpApi) {
        if (f2019d == null) {
            synchronized (g.class) {
                if (f2019d == null) {
                    f2019d = new g(iHttpApi);
                }
            }
        }
        return f2019d;
    }

    public static HashMap<String, String> b(HashMap<String, String> map) {
        map.put("version", Constants.DEFAULTAPIVERSION);
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        map.put("timeStamp", sb.toString());
        map.put(Params.FORMAT, "0");
        map.put(Params.LANGUAGEID, "0");
        map.put(Params.INVOKESOURCE, "1");
        map.put(Params.RANDSTR, UUID.randomUUID().toString());
        return map;
    }

    @Override // com.hmct.cloud.sdk.service.HiCloudAccountService
    public ReplyInfo createSignflagByToken(String str, boolean z, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("HiCloudAccountService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        b(map2);
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        map2.put(Params.SERVICETYPE, a(map.get(Params.DEVICEID)));
        try {
            return com.hmct.cloud.sdk.a.a.l(b(((HiCloudAccountService) this).f1982a, HiCloudService.a(str, "aaa/create_signflag_by_token", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), new XmlSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", "networkError"));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.HiCloudAccountService
    public String delThirdBindInfo(String str, boolean z, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("HiCloudAccountService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        map2.put(Params.FORMAT, "1");
        return b(((HiCloudAccountService) this).f1982a, HiCloudService.a(str, "custserv/share/del_third_bind_info", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2020e, f2021f);
    }

    @Override // com.hmct.cloud.sdk.service.HiCloudAccountService
    public CustomerInfo getCustomerInfo(String str, boolean z, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("HiCloudAccountService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        b(map2);
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        map2.remove(Params.INVOKESOURCE);
        try {
            return com.hmct.cloud.sdk.a.a.m(a(((HiCloudAccountService) this).f1982a, HiCloudService.a(str, "cam/user/get_customer_info", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), new XmlSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", "networkError"));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.HiCloudAccountService
    public DevSerialReply getDevSerial(String str, boolean z, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("HiCloudAccountService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        b(map2);
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        map2.remove(Params.INVOKESOURCE);
        try {
            return com.hmct.cloud.sdk.a.a.k(b(((HiCloudAccountService) this).f1982a, HiCloudService.a(str, "aaa/get_dev_serial", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), new XmlSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", "networkError"));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.HiCloudAccountService
    public String getSignInfoByCode(String str, boolean z, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("HiCloudAccountService", "domainName is null");
            return null;
        }
        HashMap map2 = new HashMap();
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        map2.put("version", Constants.DEFAULTAPIVERSION);
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000);
        map2.put("timeStamp", sb.toString());
        map2.put(Params.INVOKESOURCE, "1");
        map2.put(Params.RANDSTR, UUID.randomUUID().toString());
        map2.put(Params.FORMAT, "1");
        return b(((HiCloudAccountService) this).f1982a, HiCloudService.a(str, "aaa/get_signinfo_by_code", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2020e, f2021f);
    }

    @Override // com.hmct.cloud.sdk.service.HiCloudAccountService
    public String getThirdBindInfo(String str, boolean z, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("HiCloudAccountService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        b(map2);
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        map2.put(Params.FORMAT, "1");
        map2.remove(Params.INVOKESOURCE);
        return a(((HiCloudAccountService) this).f1982a, HiCloudService.a(str, "cam/share/get_third_bind_info", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2020e, f2021f);
    }

    @Override // com.hmct.cloud.sdk.service.HiCloudAccountService
    public SignonReplyInfo refreshToken2(String str, boolean z, HashMap<String, String> map) {
        String strByte2string;
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("HiCloudAccountService", "domainName is null");
            return null;
        }
        String str2 = map.get(Params.APPSECRET);
        HashMap<String, String> map2 = new HashMap<>();
        b(map2);
        if (!map.isEmpty()) {
            map2.putAll(map);
        }
        a(map2);
        String str3 = f2017b;
        String str4 = f2018c;
        if (TextUtils.isEmpty(str2)) {
            strByte2string = "";
        } else {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(str3.getBytes("UTF-8"), "AES");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(str4.getBytes("UTF-8"));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(1, secretKeySpec, ivParameterSpec);
                strByte2string = AESUtil.byte2string(cipher.doFinal(str2.getBytes("UTF-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
                strByte2string = null;
            }
        }
        map2.put(Params.APPSECRET, strByte2string);
        try {
            return com.hmct.cloud.sdk.a.a.j(b(((HiCloudAccountService) this).f1982a, HiCloudService.a(str, "aaa/sign_in_refreshtoken", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), new XmlSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", "networkError"));
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.HiCloudAccountService
    public SignonReplyInfo signIn(String str, boolean z, HashMap<String, String> map) {
        String strByte2string;
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("HiCloudAccountService", "domainName is null");
            return null;
        }
        String str2 = map.get(Params.DEVICEID);
        String str3 = map.get(Params.LOGINNAME);
        String str4 = map.get(Params.PASSWORD);
        String str5 = map.get(Params.APPSECRET);
        HashMap<String, String> map2 = new HashMap<>();
        b(map2);
        if (!map.isEmpty()) {
            map2.putAll(map);
        }
        String str6 = map2.get("timeStamp");
        String strByte2string2 = "";
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str6) || TextUtils.isEmpty(str4)) {
            strByte2string = "";
        } else {
            byte[] bArrMd5Encode = SDKUtil.md5Encode(str4);
            byte[] bArrMd5Encode2 = SDKUtil.md5Encode(str3 + str6);
            SDKUtil.rc4_crypt(bArrMd5Encode, bArrMd5Encode2);
            strByte2string = SDKUtil.byte2string(bArrMd5Encode2);
        }
        map2.put("signature", strByte2string);
        map2.remove(Params.PASSWORD);
        map2.put(Params.SERVICETYPE, a(str2));
        String str7 = f2017b;
        String str8 = f2018c;
        if (!TextUtils.isEmpty(str5)) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(str7.getBytes("UTF-8"), "AES");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(str8.getBytes("UTF-8"));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(1, secretKeySpec, ivParameterSpec);
                strByte2string2 = AESUtil.byte2string(cipher.doFinal(str5.getBytes("UTF-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
                strByte2string2 = null;
            }
        }
        map2.put(Params.APPSECRET, strByte2string2);
        a(map2);
        try {
            return com.hmct.cloud.sdk.a.a.j(b(((HiCloudAccountService) this).f1982a, HiCloudService.a(str, "aaa/sign_in", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), new XmlSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", "networkError"));
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.HiCloudAccountService
    public String thirdTokenLoginV2(String str, boolean z, HashMap<String, String> map) {
        String str2;
        String strByte2string = null;
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("HiCloudAccountService", "domainName is null");
            return null;
        }
        HashMap<String, String> map2 = new HashMap<>();
        b(map2);
        if (map == null || map.isEmpty()) {
            str2 = "";
        } else {
            str2 = map.get(Params.APPSECRET);
            map2.putAll(map);
        }
        a(map2);
        String str3 = f2017b;
        String str4 = f2018c;
        if (TextUtils.isEmpty(str2)) {
            strByte2string = "";
        } else {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(str3.getBytes("UTF-8"), "AES");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(str4.getBytes("UTF-8"));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(1, secretKeySpec, ivParameterSpec);
                strByte2string = AESUtil.byte2string(cipher.doFinal(str2.getBytes("UTF-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        map2.put(Params.APPSECRET, strByte2string);
        map2.put(Params.FORMAT, "1");
        map2.remove(Params.INVOKESOURCE);
        return a(((HiCloudAccountService) this).f1982a, HiCloudService.a(str, "cam/share/third_token_login_v2", z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), f2020e, f2021f);
    }

    public static String a(String str) {
        if (str != null && str.length() > 6) {
            String strSubstring = str.substring(3, 6);
            if ("006".equalsIgnoreCase(strSubstring)) {
                return "6";
            }
            if ("007".equalsIgnoreCase(strSubstring)) {
                return "7";
            }
            if ("008".equalsIgnoreCase(strSubstring)) {
                return "8";
            }
            if ("003".equalsIgnoreCase(strSubstring)) {
                return "9";
            }
            if ("010".equalsIgnoreCase(strSubstring)) {
                return "10";
            }
            if ("00b".equalsIgnoreCase(strSubstring)) {
                return "7";
            }
            if ("f00".equalsIgnoreCase(strSubstring)) {
                return "11";
            }
        }
        return "99";
    }
}
