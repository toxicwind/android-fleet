package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import android.util.Base64;
import com.google.firebase.installations.Utils;
import com.hmct.cloud.sdk.bean.UniversalBean;
import com.hmct.cloud.sdk.service.UniversalService;
import com.hmct.cloud.sdk.utils.AESUtil;
import com.hmct.cloud.sdk.utils.Params;
import com.hmct.cloud.sdk.utils.SDKUtil;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.builder.GetBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostFormBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostStringBuilder;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.ISignature;
import com.ju.lib.datacommunication.network.http.core.signature.JsonSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class s extends UniversalService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile UniversalService f2053b;

    public s(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static UniversalService a(IHttpApi iHttpApi) {
        if (f2053b == null) {
            synchronized (s.class) {
                if (f2053b == null) {
                    f2053b = new s(iHttpApi);
                }
            }
        }
        return f2053b;
    }

    public static HashMap<String, String> b(HashMap<String, String> map) {
        if (map != null && map.size() != 0 && map.containsKey(Params.APPSECRET)) {
            map.put(Params.APPSECRET, new AESUtil().Encrypt(map.get(Params.APPSECRET), "D5B6D8584F94B432", "205681D89D731A8F"));
        }
        return map;
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean get(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        if (z) {
            return a(((UniversalService) this).f1995a, str, str2, JsonSignature.createJsonSignature(), a(str2, (HashMap<String, String>) null, (String) null, (String) null));
        }
        IHttpApi iHttpApi = ((UniversalService) this).f1995a;
        StringBuilder sbB = b.a.a.a.a.b(str2, "&sign=");
        sbB.append(HiCloudKey.encyptRSA(str2, HiCloudKey.getPublicKey()));
        return a(iHttpApi, str, sbB.toString(), JsonSignature.createJsonSignature(), (Map<String, String>) null);
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean post(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        if (z) {
            return a(((UniversalService) this).f1995a, str, str2, JsonSignature.createJsonSignature(), a(str2, (HashMap<String, String>) null, (String) null, (String) null), "application/json");
        }
        IHttpApi iHttpApi = ((UniversalService) this).f1995a;
        StringBuilder sbB = b.a.a.a.a.b(str2, "&sign=");
        sbB.append(HiCloudKey.encyptRSA(str2, HiCloudKey.getPublicKey()));
        return a(iHttpApi, str, sbB.toString(), JsonSignature.createJsonSignature(), (Map<String, String>) null, "application/json");
    }

    /* JADX WARN: Code duplicated, block: B:11:0x004d A[Catch: HttpException -> 0x00a3, TryCatch #0 {HttpException -> 0x00a3, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x0047, B:11:0x004d, B:14:0x009f, B:12:0x0074, B:13:0x0097, B:7:0x002d), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:12:0x0074 A[Catch: HttpException -> 0x00a3, TryCatch #0 {HttpException -> 0x00a3, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x0047, B:11:0x004d, B:14:0x009f, B:12:0x0074, B:13:0x0097, B:7:0x002d), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:13:0x0097 A[Catch: HttpException -> 0x00a3, TryCatch #0 {HttpException -> 0x00a3, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x0047, B:11:0x004d, B:14:0x009f, B:12:0x0074, B:13:0x0097, B:7:0x002d), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x002d A[Catch: HttpException -> 0x00a3, TryCatch #0 {HttpException -> 0x00a3, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x0047, B:11:0x004d, B:14:0x009f, B:12:0x0074, B:13:0x0097, B:7:0x002d), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:9:0x0047 A[Catch: HttpException -> 0x00a3, TryCatch #0 {HttpException -> 0x00a3, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x0047, B:11:0x004d, B:14:0x009f, B:12:0x0074, B:13:0x0097, B:7:0x002d), top: B:19:0x0008 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:11:0x004d, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:12:0x0074, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    public static UniversalBean b(IHttpApi iHttpApi, String str, Map<String, String> map, ISignature iSignature, Map<String, String> map2) {
        HiResponse hiResponseExecute;
        UniversalBean universalBean = new UniversalBean();
        if (map2 != null) {
            try {
                if (!map2.isEmpty()) {
                    hiResponseExecute = ((PostFormBuilder) ((PostFormBuilder) ((PostFormBuilder) iHttpApi.postForm().url(str)).params(map).hearders(map2)).signature(iSignature)).execute();
                } else {
                    hiResponseExecute = ((PostFormBuilder) ((PostFormBuilder) iHttpApi.postForm().url(str)).params(map).signature(iSignature)).execute();
                }
                if (hiResponseExecute != null) {
                    if (hiResponseExecute.isSuccessful()) {
                        universalBean.setSuccess(true);
                        String strString = hiResponseExecute.getBody().string();
                        universalBean.setResult(strString);
                        HttpLog.d("UniversalService", "result = " + strString);
                    } else {
                        universalBean.setSuccess(false);
                        universalBean.setErrorMsg(hiResponseExecute.getCode() + " " + hiResponseExecute.getMessage());
                    }
                } else {
                    universalBean.setSuccess(false);
                    universalBean.setErrorMsg("hiResponse is NULL");
                }
                universalBean.setResponse(hiResponseExecute);
            } catch (HttpException e2) {
                e2.printStackTrace();
                universalBean.setSuccess(false);
                universalBean.setErrorMsg(SDKUtil.toUTF_8(e2.getMessage() + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getCause()));
            }
        } else {
            hiResponseExecute = ((PostFormBuilder) ((PostFormBuilder) iHttpApi.postForm().url(str)).params(map).signature(iSignature)).execute();
            if (hiResponseExecute != null) {
                if (hiResponseExecute.isSuccessful()) {
                    universalBean.setSuccess(true);
                    String strString2 = hiResponseExecute.getBody().string();
                    universalBean.setResult(strString2);
                    HttpLog.d("UniversalService", "result = " + strString2);
                } else {
                    universalBean.setSuccess(false);
                    universalBean.setErrorMsg(hiResponseExecute.getCode() + " " + hiResponseExecute.getMessage());
                }
            } else {
                universalBean.setSuccess(false);
                universalBean.setErrorMsg("hiResponse is NULL");
            }
            universalBean.setResponse(hiResponseExecute);
        }
        return universalBean;
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean get(String str, HashMap<String, String> map, boolean z) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        b(map);
        if (z) {
            return a(((UniversalService) this).f1995a, str, map, JsonSignature.createJsonSignature(), a(map, (HashMap<String, String>) null, (String) null, (String) null));
        }
        return a(((UniversalService) this).f1995a, str, HiCloudKey.encyptMapRSA(map, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), (Map<String, String>) null);
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean post(String str, HashMap<String, String> map, boolean z) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        b(map);
        if (z) {
            return b(((UniversalService) this).f1995a, str, map, JsonSignature.createJsonSignature(), a(map, (HashMap<String, String>) null, (String) null, (String) null));
        }
        return b(((UniversalService) this).f1995a, str, HiCloudKey.encyptMapRSA(map, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), null);
    }

    public static Map<String, String> a(HashMap<String, String> map, HashMap<String, String> map2, String str, String str2) {
        String strA;
        HashMap map3 = new HashMap();
        if (TextUtils.isEmpty(str2)) {
            strA = HiCloudKey.encyptMapMD5(map);
        } else {
            strA = a(map, str2);
            map3.put(Params.APPKEY, str);
        }
        map3.put("X-Sign-For", strA);
        if (map2 != null && !map2.isEmpty()) {
            map3.putAll(map2);
        }
        return map3;
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean get(String str, String str2, boolean z, ISignature iSignature) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        if (z) {
            return a(((UniversalService) this).f1995a, str, str2, iSignature, a(str2, (HashMap<String, String>) null, (String) null, (String) null));
        }
        IHttpApi iHttpApi = ((UniversalService) this).f1995a;
        StringBuilder sbB = b.a.a.a.a.b(str2, "&sign=");
        sbB.append(HiCloudKey.encyptRSA(str2, HiCloudKey.getPublicKey()));
        return a(iHttpApi, str, sbB.toString(), iSignature, (Map<String, String>) null);
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean post(String str, String str2, boolean z, ISignature iSignature) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        if (z) {
            return a(((UniversalService) this).f1995a, str, str2, iSignature, a(str2, (HashMap<String, String>) null, (String) null, (String) null), "application/json");
        }
        IHttpApi iHttpApi = ((UniversalService) this).f1995a;
        StringBuilder sbB = b.a.a.a.a.b(str2, "&sign=");
        sbB.append(HiCloudKey.encyptRSA(str2, HiCloudKey.getPublicKey()));
        return a(iHttpApi, str, sbB.toString(), iSignature, (Map<String, String>) null, "application/json");
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean get(String str, HashMap<String, String> map, boolean z, ISignature iSignature) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        b(map);
        if (z) {
            return a(((UniversalService) this).f1995a, str, map, iSignature, a(map, (HashMap<String, String>) null, (String) null, (String) null));
        }
        return a(((UniversalService) this).f1995a, str, HiCloudKey.encyptMapRSA(map, HiCloudKey.getPublicKey(), "sign"), iSignature, (Map<String, String>) null);
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean post(String str, HashMap<String, String> map, boolean z, ISignature iSignature) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        b(map);
        if (z) {
            return b(((UniversalService) this).f1995a, str, map, iSignature, a(map, (HashMap<String, String>) null, (String) null, (String) null));
        }
        return b(((UniversalService) this).f1995a, str, HiCloudKey.encyptMapRSA(map, HiCloudKey.getPublicKey(), "sign"), iSignature, null);
    }

    public static Map<String, String> a(String str, HashMap<String, String> map, String str2, String str3) {
        String strA;
        HashMap map2 = new HashMap();
        if (TextUtils.isEmpty(str3)) {
            strA = HiCloudKey.encyptStringMD5(str);
        } else {
            strA = a(str, str3);
            map2.put(Params.APPKEY, str2);
        }
        map2.put("X-Sign-For", strA);
        if (map != null && !map.isEmpty()) {
            map2.putAll(map);
        }
        return map2;
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean get(String str, String str2, boolean z, HashMap<String, String> map, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        if (z) {
            return a(((UniversalService) this).f1995a, str, str2, JsonSignature.createJsonSignature(), a(str2, map, str3, str4));
        }
        IHttpApi iHttpApi = ((UniversalService) this).f1995a;
        StringBuilder sbB = b.a.a.a.a.b(str2, "&sign=");
        sbB.append(HiCloudKey.encyptRSA(str2, HiCloudKey.getPublicKey()));
        return a(iHttpApi, str, sbB.toString(), JsonSignature.createJsonSignature(), map);
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean post(String str, String str2, boolean z, HashMap<String, String> map, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        if (z) {
            return a(((UniversalService) this).f1995a, str, str2, JsonSignature.createJsonSignature(), a(str2, map, str3, str4), "application/json");
        }
        IHttpApi iHttpApi = ((UniversalService) this).f1995a;
        StringBuilder sbB = b.a.a.a.a.b(str2, "&sign=");
        sbB.append(HiCloudKey.encyptRSA(str2, HiCloudKey.getPublicKey()));
        return a(iHttpApi, str, sbB.toString(), JsonSignature.createJsonSignature(), map, "application/json");
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String strA = b.a.a.a.a.a(str, str2);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            return Base64.encodeToString(messageDigest.digest(strA.getBytes("UTF-8")), 2);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return str;
        }
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean get(String str, HashMap<String, String> map, boolean z, HashMap<String, String> map2, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        b(map);
        if (z) {
            return a(((UniversalService) this).f1995a, str, map, JsonSignature.createJsonSignature(), a(map, map2, str2, str3));
        }
        return a(((UniversalService) this).f1995a, str, HiCloudKey.encyptMapRSA(map, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), map2);
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean post(String str, HashMap<String, String> map, boolean z, HashMap<String, String> map2, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        b(map);
        if (z) {
            return b(((UniversalService) this).f1995a, str, map, JsonSignature.createJsonSignature(), a(map, map2, str2, str3));
        }
        return b(((UniversalService) this).f1995a, str, HiCloudKey.encyptMapRSA(map, HiCloudKey.getPublicKey(), "sign"), JsonSignature.createJsonSignature(), map2);
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean get(String str, String str2, boolean z, ISignature iSignature, HashMap<String, String> map, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        if (z) {
            return a(((UniversalService) this).f1995a, str, str2, iSignature, a(str2, map, str3, str4));
        }
        IHttpApi iHttpApi = ((UniversalService) this).f1995a;
        StringBuilder sbB = b.a.a.a.a.b(str2, "&sign=");
        sbB.append(HiCloudKey.encyptRSA(str2, HiCloudKey.getPublicKey()));
        return a(iHttpApi, str, sbB.toString(), iSignature, map);
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean post(String str, String str2, boolean z, ISignature iSignature, HashMap<String, String> map, String str3, String str4) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        if (z) {
            return a(((UniversalService) this).f1995a, str, str2, iSignature, a(str2, map, str3, str4), "application/json");
        }
        IHttpApi iHttpApi = ((UniversalService) this).f1995a;
        StringBuilder sbB = b.a.a.a.a.b(str2, "&sign=");
        sbB.append(HiCloudKey.encyptRSA(str2, HiCloudKey.getPublicKey()));
        return a(iHttpApi, str, sbB.toString(), iSignature, map, "application/json");
    }

    public static String a(Map<String, String> map, String str) {
        if (map != null && !map.isEmpty()) {
            HashMap map2 = new HashMap();
            map2.putAll(map);
            try {
                String str2 = a((Map<String, String>) map2) + str;
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                return Base64.encodeToString(messageDigest.digest(str2.getBytes("UTF-8")), 2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean get(String str, HashMap<String, String> map, boolean z, ISignature iSignature, HashMap<String, String> map2, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        b(map);
        if (z) {
            return a(((UniversalService) this).f1995a, str, map, iSignature, a(map, map2, str2, str3));
        }
        return a(((UniversalService) this).f1995a, str, HiCloudKey.encyptMapRSA(map, HiCloudKey.getPublicKey(), "sign"), iSignature, map2);
    }

    @Override // com.hmct.cloud.sdk.service.UniversalService
    public UniversalBean post(String str, HashMap<String, String> map, boolean z, ISignature iSignature, HashMap<String, String> map2, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UniversalService", "url is null");
            return null;
        }
        b(map);
        if (z) {
            return b(((UniversalService) this).f1995a, str, map, iSignature, a(map, map2, str2, str3));
        }
        return b(((UniversalService) this).f1995a, str, HiCloudKey.encyptMapRSA(map, HiCloudKey.getPublicKey(), "sign"), iSignature, map2);
    }

    public static String a(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            String str2 = map.get(str);
            if (!TextUtils.isEmpty(str2)) {
                stringBuffer.append("&" + str + "=" + str2);
            }
        }
        String string = stringBuffer.toString();
        return string.startsWith("&") ? string.replaceFirst("&", "") : string;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0051 A[Catch: HttpException -> 0x00a7, TryCatch #0 {HttpException -> 0x00a7, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x004b, B:11:0x0051, B:14:0x00a3, B:12:0x0078, B:13:0x009b, B:7:0x002f), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:12:0x0078 A[Catch: HttpException -> 0x00a7, TryCatch #0 {HttpException -> 0x00a7, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x004b, B:11:0x0051, B:14:0x00a3, B:12:0x0078, B:13:0x009b, B:7:0x002f), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:13:0x009b A[Catch: HttpException -> 0x00a7, TryCatch #0 {HttpException -> 0x00a7, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x004b, B:11:0x0051, B:14:0x00a3, B:12:0x0078, B:13:0x009b, B:7:0x002f), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x002f A[Catch: HttpException -> 0x00a7, TryCatch #0 {HttpException -> 0x00a7, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x004b, B:11:0x0051, B:14:0x00a3, B:12:0x0078, B:13:0x009b, B:7:0x002f), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:9:0x004b A[Catch: HttpException -> 0x00a7, TryCatch #0 {HttpException -> 0x00a7, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x004b, B:11:0x0051, B:14:0x00a3, B:12:0x0078, B:13:0x009b, B:7:0x002f), top: B:19:0x0008 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:11:0x0051, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:12:0x0078, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    public static UniversalBean a(IHttpApi iHttpApi, String str, Map<String, String> map, ISignature iSignature, Map<String, String> map2) {
        HiResponse hiResponseExecute;
        UniversalBean universalBean = new UniversalBean();
        if (map2 != null) {
            try {
                if (!map2.isEmpty()) {
                    hiResponseExecute = ((GetBuilder) ((GetBuilder) ((GetBuilder) iHttpApi.get().url(str)).addQueryParams(map).hearders(map2)).signature(iSignature)).execute();
                } else {
                    hiResponseExecute = ((GetBuilder) ((GetBuilder) iHttpApi.get().url(str)).addQueryParams(map).signature(iSignature)).execute();
                }
                if (hiResponseExecute != null) {
                    if (hiResponseExecute.isSuccessful()) {
                        universalBean.setSuccess(true);
                        String strString = hiResponseExecute.getBody().string();
                        universalBean.setResult(strString);
                        HttpLog.d("UniversalService", "result = " + strString);
                    } else {
                        universalBean.setSuccess(false);
                        universalBean.setErrorMsg(hiResponseExecute.getCode() + " " + hiResponseExecute.getMessage());
                    }
                } else {
                    universalBean.setSuccess(false);
                    universalBean.setErrorMsg("hiResponse is NULL");
                }
                universalBean.setResponse(hiResponseExecute);
            } catch (HttpException e2) {
                e2.printStackTrace();
                universalBean.setSuccess(false);
                universalBean.setErrorMsg(SDKUtil.toUTF_8(e2.getMessage() + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getCause()));
            }
        } else {
            hiResponseExecute = ((GetBuilder) ((GetBuilder) iHttpApi.get().url(str)).addQueryParams(map).signature(iSignature)).execute();
            if (hiResponseExecute != null) {
                if (hiResponseExecute.isSuccessful()) {
                    universalBean.setSuccess(true);
                    String strString2 = hiResponseExecute.getBody().string();
                    universalBean.setResult(strString2);
                    HttpLog.d("UniversalService", "result = " + strString2);
                } else {
                    universalBean.setSuccess(false);
                    universalBean.setErrorMsg(hiResponseExecute.getCode() + " " + hiResponseExecute.getMessage());
                }
            } else {
                universalBean.setSuccess(false);
                universalBean.setErrorMsg("hiResponse is NULL");
            }
            universalBean.setResponse(hiResponseExecute);
        }
        return universalBean;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0059 A[Catch: HttpException -> 0x00af, TryCatch #0 {HttpException -> 0x00af, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x0053, B:11:0x0059, B:14:0x00ab, B:12:0x0080, B:13:0x00a3, B:7:0x0033), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:12:0x0080 A[Catch: HttpException -> 0x00af, TryCatch #0 {HttpException -> 0x00af, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x0053, B:11:0x0059, B:14:0x00ab, B:12:0x0080, B:13:0x00a3, B:7:0x0033), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:13:0x00a3 A[Catch: HttpException -> 0x00af, TryCatch #0 {HttpException -> 0x00af, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x0053, B:11:0x0059, B:14:0x00ab, B:12:0x0080, B:13:0x00a3, B:7:0x0033), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0033 A[Catch: HttpException -> 0x00af, TryCatch #0 {HttpException -> 0x00af, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x0053, B:11:0x0059, B:14:0x00ab, B:12:0x0080, B:13:0x00a3, B:7:0x0033), top: B:19:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:9:0x0053 A[Catch: HttpException -> 0x00af, TryCatch #0 {HttpException -> 0x00af, blocks: (B:4:0x0008, B:6:0x000e, B:9:0x0053, B:11:0x0059, B:14:0x00ab, B:12:0x0080, B:13:0x00a3, B:7:0x0033), top: B:19:0x0008 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:11:0x0059, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:12:0x0080, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    public static UniversalBean a(IHttpApi iHttpApi, String str, String str2, ISignature iSignature, Map<String, String> map) {
        HiResponse hiResponseExecute;
        UniversalBean universalBean = new UniversalBean();
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    hiResponseExecute = ((GetBuilder) ((GetBuilder) ((GetBuilder) iHttpApi.get().url(str)).addQueryParams(SDKUtil.getMapFromJson(str2)).hearders(map)).signature(iSignature)).execute();
                } else {
                    hiResponseExecute = ((GetBuilder) ((GetBuilder) iHttpApi.get().url(str)).addQueryParams(SDKUtil.getMapFromJson(str2)).signature(iSignature)).execute();
                }
                if (hiResponseExecute != null) {
                    if (hiResponseExecute.isSuccessful()) {
                        universalBean.setSuccess(true);
                        String strString = hiResponseExecute.getBody().string();
                        universalBean.setResult(strString);
                        HttpLog.d("UniversalService", "result = " + strString);
                    } else {
                        universalBean.setSuccess(false);
                        universalBean.setErrorMsg(hiResponseExecute.getCode() + " " + hiResponseExecute.getMessage());
                    }
                } else {
                    universalBean.setSuccess(false);
                    universalBean.setErrorMsg("hiResponse is NULL");
                }
                universalBean.setResponse(hiResponseExecute);
            } catch (HttpException e2) {
                e2.printStackTrace();
                universalBean.setSuccess(false);
                universalBean.setErrorMsg(SDKUtil.toUTF_8(e2.getMessage() + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getCause()));
            }
        } else {
            hiResponseExecute = ((GetBuilder) ((GetBuilder) iHttpApi.get().url(str)).addQueryParams(SDKUtil.getMapFromJson(str2)).signature(iSignature)).execute();
            if (hiResponseExecute != null) {
                if (hiResponseExecute.isSuccessful()) {
                    universalBean.setSuccess(true);
                    String strString2 = hiResponseExecute.getBody().string();
                    universalBean.setResult(strString2);
                    HttpLog.d("UniversalService", "result = " + strString2);
                } else {
                    universalBean.setSuccess(false);
                    universalBean.setErrorMsg(hiResponseExecute.getCode() + " " + hiResponseExecute.getMessage());
                }
            } else {
                universalBean.setSuccess(false);
                universalBean.setErrorMsg("hiResponse is NULL");
            }
            universalBean.setResponse(hiResponseExecute);
        }
        return universalBean;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0058 A[Catch: HttpException -> 0x00f3, TryCatch #0 {HttpException -> 0x00f3, blocks: (B:4:0x0008, B:6:0x000e, B:8:0x0014, B:15:0x0097, B:17:0x009d, B:20:0x00ef, B:18:0x00c4, B:19:0x00e7, B:9:0x0033, B:10:0x0058, B:12:0x005e, B:13:0x0077), top: B:25:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:12:0x005e A[Catch: HttpException -> 0x00f3, TryCatch #0 {HttpException -> 0x00f3, blocks: (B:4:0x0008, B:6:0x000e, B:8:0x0014, B:15:0x0097, B:17:0x009d, B:20:0x00ef, B:18:0x00c4, B:19:0x00e7, B:9:0x0033, B:10:0x0058, B:12:0x005e, B:13:0x0077), top: B:25:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:13:0x0077 A[Catch: HttpException -> 0x00f3, TryCatch #0 {HttpException -> 0x00f3, blocks: (B:4:0x0008, B:6:0x000e, B:8:0x0014, B:15:0x0097, B:17:0x009d, B:20:0x00ef, B:18:0x00c4, B:19:0x00e7, B:9:0x0033, B:10:0x0058, B:12:0x005e, B:13:0x0077), top: B:25:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x0097 A[Catch: HttpException -> 0x00f3, TryCatch #0 {HttpException -> 0x00f3, blocks: (B:4:0x0008, B:6:0x000e, B:8:0x0014, B:15:0x0097, B:17:0x009d, B:20:0x00ef, B:18:0x00c4, B:19:0x00e7, B:9:0x0033, B:10:0x0058, B:12:0x005e, B:13:0x0077), top: B:25:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:17:0x009d A[Catch: HttpException -> 0x00f3, TryCatch #0 {HttpException -> 0x00f3, blocks: (B:4:0x0008, B:6:0x000e, B:8:0x0014, B:15:0x0097, B:17:0x009d, B:20:0x00ef, B:18:0x00c4, B:19:0x00e7, B:9:0x0033, B:10:0x0058, B:12:0x005e, B:13:0x0077), top: B:25:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x00c4 A[Catch: HttpException -> 0x00f3, TryCatch #0 {HttpException -> 0x00f3, blocks: (B:4:0x0008, B:6:0x000e, B:8:0x0014, B:15:0x0097, B:17:0x009d, B:20:0x00ef, B:18:0x00c4, B:19:0x00e7, B:9:0x0033, B:10:0x0058, B:12:0x005e, B:13:0x0077), top: B:25:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:19:0x00e7 A[Catch: HttpException -> 0x00f3, TryCatch #0 {HttpException -> 0x00f3, blocks: (B:4:0x0008, B:6:0x000e, B:8:0x0014, B:15:0x0097, B:17:0x009d, B:20:0x00ef, B:18:0x00c4, B:19:0x00e7, B:9:0x0033, B:10:0x0058, B:12:0x005e, B:13:0x0077), top: B:25:0x0008 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:17:0x009d, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:18:0x00c4, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    public static UniversalBean a(IHttpApi iHttpApi, String str, String str2, ISignature iSignature, Map<String, String> map, String str3) {
        HiResponse hiResponseExecute;
        UniversalBean universalBean = new UniversalBean();
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    if (TextUtils.isEmpty(str3)) {
                        hiResponseExecute = ((PostStringBuilder) ((PostStringBuilder) ((PostStringBuilder) iHttpApi.postString().url(str)).content(str2).hearders(map)).signature(iSignature)).execute();
                    } else {
                        hiResponseExecute = ((PostStringBuilder) ((PostStringBuilder) ((PostStringBuilder) iHttpApi.postString().url(str)).content(str2).contentType(str3).hearders(map)).signature(iSignature)).execute();
                    }
                } else if (TextUtils.isEmpty(str3)) {
                    hiResponseExecute = ((PostStringBuilder) ((PostStringBuilder) iHttpApi.postString().url(str)).content(str2).signature(iSignature)).execute();
                } else {
                    hiResponseExecute = ((PostStringBuilder) ((PostStringBuilder) iHttpApi.postString().url(str)).content(str2).contentType(str3).signature(iSignature)).execute();
                }
                if (hiResponseExecute != null) {
                    if (hiResponseExecute.isSuccessful()) {
                        universalBean.setSuccess(true);
                        String strString = hiResponseExecute.getBody().string();
                        universalBean.setResult(strString);
                        HttpLog.d("UniversalService", "result = " + strString);
                    } else {
                        universalBean.setSuccess(false);
                        universalBean.setErrorMsg(hiResponseExecute.getCode() + " " + hiResponseExecute.getMessage());
                    }
                } else {
                    universalBean.setSuccess(false);
                    universalBean.setErrorMsg("hiResponse is NULL");
                }
                universalBean.setResponse(hiResponseExecute);
            } catch (HttpException e2) {
                e2.printStackTrace();
                universalBean.setSuccess(false);
                universalBean.setErrorMsg(SDKUtil.toUTF_8(e2.getMessage() + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getCause()));
            }
        } else {
            if (TextUtils.isEmpty(str3)) {
                hiResponseExecute = ((PostStringBuilder) ((PostStringBuilder) iHttpApi.postString().url(str)).content(str2).signature(iSignature)).execute();
            } else {
                hiResponseExecute = ((PostStringBuilder) ((PostStringBuilder) iHttpApi.postString().url(str)).content(str2).contentType(str3).signature(iSignature)).execute();
            }
            if (hiResponseExecute != null) {
                if (hiResponseExecute.isSuccessful()) {
                    universalBean.setSuccess(true);
                    String strString2 = hiResponseExecute.getBody().string();
                    universalBean.setResult(strString2);
                    HttpLog.d("UniversalService", "result = " + strString2);
                } else {
                    universalBean.setSuccess(false);
                    universalBean.setErrorMsg(hiResponseExecute.getCode() + " " + hiResponseExecute.getMessage());
                }
            } else {
                universalBean.setSuccess(false);
                universalBean.setErrorMsg("hiResponse is NULL");
            }
            universalBean.setResponse(hiResponseExecute);
        }
        return universalBean;
    }
}
