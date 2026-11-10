package com.hmct.cloud.sdk.service;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import b.a.a.a.a;
import com.google.firebase.installations.Utils;
import com.hmct.cloud.sdk.utils.Constants;
import com.hmct.cloud.sdk.utils.SDKUtil;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.builder.GetBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostFormBuilder;
import com.ju.lib.datacommunication.network.http.builder.PostStringBuilder;
import com.ju.lib.datacommunication.network.http.core.HiResponse;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.signature.ISignature;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/* JADX INFO: loaded from: classes2.dex */
public abstract class HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashMap<String, String> f1983a;

    /* JADX WARN: Code duplicated, block: B:11:0x004f A[Catch: HttpException -> 0x0081, TryCatch #0 {HttpException -> 0x0081, blocks: (B:4:0x000a, B:6:0x0010, B:9:0x0049, B:11:0x004f, B:12:0x0068, B:7:0x002f), top: B:18:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:12:0x0068 A[Catch: HttpException -> 0x0081, TRY_LEAVE, TryCatch #0 {HttpException -> 0x0081, blocks: (B:4:0x000a, B:6:0x0010, B:9:0x0049, B:11:0x004f, B:12:0x0068, B:7:0x002f), top: B:18:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:7:0x002f A[Catch: HttpException -> 0x0081, TryCatch #0 {HttpException -> 0x0081, blocks: (B:4:0x000a, B:6:0x0010, B:9:0x0049, B:11:0x004f, B:12:0x0068, B:7:0x002f), top: B:18:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:9:0x0049 A[Catch: HttpException -> 0x0081, TryCatch #0 {HttpException -> 0x0081, blocks: (B:4:0x000a, B:6:0x0010, B:9:0x0049, B:11:0x004f, B:12:0x0068, B:7:0x002f), top: B:18:0x000a }] */
    /* JADX WARN: Instruction removed from duplicated block: B:11:0x004f, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    public static String b(IHttpApi iHttpApi, String str, Map<String, String> map, ISignature iSignature, String str2, String str3, Map<String, String> map2) {
        HiResponse hiResponseExecute;
        if (map2 != null) {
            try {
                if (map2.isEmpty()) {
                    hiResponseExecute = ((PostFormBuilder) ((PostFormBuilder) iHttpApi.postForm().url(str)).params(map).signature(iSignature)).execute();
                } else {
                    hiResponseExecute = ((PostFormBuilder) ((PostFormBuilder) ((PostFormBuilder) iHttpApi.postForm().url(str)).params(map).hearders(map2)).signature(iSignature)).execute();
                }
                if (hiResponseExecute != null) {
                    if (hiResponseExecute.isSuccessful()) {
                        String strString = hiResponseExecute.getBody().string();
                        SDKUtil.LogD("HttpHandler", "result=" + strString);
                        str2 = strString;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(hiResponseExecute.getCode());
                        str2 = str2.replace(str3, sb.toString().replace("\"", ""));
                    }
                }
            } catch (HttpException e2) {
                e2.printStackTrace();
                str2 = str2.replace(str3, SDKUtil.toUTF_8(e2 + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getMessage() + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getCause()).replace("\"", ""));
            }
        } else {
            hiResponseExecute = ((PostFormBuilder) ((PostFormBuilder) iHttpApi.postForm().url(str)).params(map).signature(iSignature)).execute();
            if (hiResponseExecute != null) {
                if (hiResponseExecute.isSuccessful()) {
                    String strString2 = hiResponseExecute.getBody().string();
                    SDKUtil.LogD("HttpHandler", "result=" + strString2);
                    str2 = strString2;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(hiResponseExecute.getCode());
                    str2 = str2.replace(str3, sb2.toString().replace("\"", ""));
                }
            }
        }
        SDKUtil.LogD("HttpHandler", "result=" + str2);
        return str2;
    }

    public String a(String str, int i) {
        return str;
    }

    public final void a(HashMap<String, String> map) {
        HashMap<String, String> map2 = this.f1983a;
        if (map2 == null || map2.isEmpty()) {
            return;
        }
        map.putAll(this.f1983a);
    }

    public void addSystemParams(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.f1983a == null) {
            this.f1983a = new HashMap<>();
        }
        this.f1983a.put(str, str2);
    }

    public void setSystemParams(HashMap<String, String> map) {
        this.f1983a = map;
    }

    public static String a(String str, String str2, boolean z) {
        StringBuilder sb;
        if (z) {
            sb = new StringBuilder("https://" + str);
        } else {
            sb = new StringBuilder("http://" + str);
        }
        return a.a(sb, "/", str2);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0053 A[Catch: HttpException -> 0x0085, TryCatch #0 {HttpException -> 0x0085, blocks: (B:4:0x000a, B:6:0x0010, B:9:0x004d, B:11:0x0053, B:12:0x006c, B:7:0x0031), top: B:18:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:12:0x006c A[Catch: HttpException -> 0x0085, TRY_LEAVE, TryCatch #0 {HttpException -> 0x0085, blocks: (B:4:0x000a, B:6:0x0010, B:9:0x004d, B:11:0x0053, B:12:0x006c, B:7:0x0031), top: B:18:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0031 A[Catch: HttpException -> 0x0085, TryCatch #0 {HttpException -> 0x0085, blocks: (B:4:0x000a, B:6:0x0010, B:9:0x004d, B:11:0x0053, B:12:0x006c, B:7:0x0031), top: B:18:0x000a }] */
    /* JADX WARN: Code duplicated, block: B:9:0x004d A[Catch: HttpException -> 0x0085, TryCatch #0 {HttpException -> 0x0085, blocks: (B:4:0x000a, B:6:0x0010, B:9:0x004d, B:11:0x0053, B:12:0x006c, B:7:0x0031), top: B:18:0x000a }] */
    /* JADX WARN: Instruction removed from duplicated block: B:11:0x0053, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    public static String a(IHttpApi iHttpApi, String str, Map<String, String> map, ISignature iSignature, String str2, String str3, Map<String, String> map2) {
        HiResponse hiResponseExecute;
        if (map2 != null) {
            try {
                if (!map2.isEmpty()) {
                    hiResponseExecute = ((GetBuilder) ((GetBuilder) ((GetBuilder) iHttpApi.get().url(str)).addQueryParams(map).hearders(map2)).signature(iSignature)).execute();
                } else {
                    hiResponseExecute = ((GetBuilder) ((GetBuilder) iHttpApi.get().url(str)).addQueryParams(map).signature(iSignature)).execute();
                }
                if (hiResponseExecute != null) {
                    if (hiResponseExecute.isSuccessful()) {
                        String strString = hiResponseExecute.getBody().string();
                        SDKUtil.LogD("HttpHandler", "result=" + strString);
                        str2 = strString;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(hiResponseExecute.getCode());
                        str2 = str2.replace(str3, sb.toString().replace("\"", ""));
                    }
                }
            } catch (HttpException e2) {
                e2.printStackTrace();
                str2 = str2.replace(str3, SDKUtil.toUTF_8(e2 + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getMessage() + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getCause()).replace("\"", ""));
            }
        } else {
            hiResponseExecute = ((GetBuilder) ((GetBuilder) iHttpApi.get().url(str)).addQueryParams(map).signature(iSignature)).execute();
            if (hiResponseExecute != null) {
                if (hiResponseExecute.isSuccessful()) {
                    String strString2 = hiResponseExecute.getBody().string();
                    SDKUtil.LogD("HttpHandler", "result=" + strString2);
                    str2 = strString2;
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(hiResponseExecute.getCode());
                    str2 = str2.replace(str3, sb2.toString().replace("\"", ""));
                }
            }
        }
        SDKUtil.LogD("HttpHandler", "result=" + str2);
        return str2;
    }

    public final String b(IHttpApi iHttpApi, String str, Map<String, String> map, ISignature iSignature, String str2, String str3) {
        return b(iHttpApi, str, map, iSignature, str2, str3, null);
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00d4 A[Catch: Exception -> 0x00d0, TRY_LEAVE, TryCatch #0 {Exception -> 0x00d0, blocks: (B:44:0x00cc, B:48:0x00d4), top: B:64:0x00cc }] */
    /* JADX WARN: Code duplicated, block: B:59:0x0100 A[Catch: Exception -> 0x00fc, TRY_LEAVE, TryCatch #3 {Exception -> 0x00fc, blocks: (B:55:0x00f8, B:59:0x0100), top: B:68:0x00f8 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.String] */
    public final String b(String str, int i) throws Throwable {
        ?? Group;
        GZIPInputStream gZIPInputStream;
        String str2 = "error in decompress data : ";
        if (str != 0) {
            ?? r1 = "";
            if (!str.equals("")) {
                if (str.equals("")) {
                    Group = str;
                } else {
                    Matcher matcher = Pattern.compile("<" + Constants.COMPRESSCONTENTTAG + ">(.*?)</" + Constants.COMPRESSCONTENTTAG + ">").matcher(str);
                    Group = "";
                    while (matcher.find()) {
                        Group = matcher.group(1);
                    }
                }
                if (Group != 0 && !Group.equals("")) {
                    ?? Replace = str.replace(Group, "");
                    ?? str3 = 0;
                    str3 = 0;
                    str3 = 0;
                    str3 = 0;
                    try {
                        try {
                            gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(Base64.decode((String) Group, 0)));
                            try {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                try {
                                    byte[] bArr = new byte[8192];
                                    while (true) {
                                        int i2 = gZIPInputStream.read(bArr);
                                        if (i2 == -1) {
                                            break;
                                        }
                                        byteArrayOutputStream.write(bArr, 0, i2);
                                    }
                                    str3 = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                                    try {
                                        byteArrayOutputStream.close();
                                        gZIPInputStream.close();
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                        Log.e(Constants.LOGTAG, "error in decompress data : " + e2.getMessage());
                                    }
                                    r1 = str3;
                                } catch (IOException e3) {
                                    e = e3;
                                    str3 = byteArrayOutputStream;
                                    e.printStackTrace();
                                    Log.e(Constants.LOGTAG, "error in decompress data : " + e.getMessage());
                                    if (str3 != 0) {
                                        try {
                                            str3.close();
                                            if (gZIPInputStream != null) {
                                                gZIPInputStream.close();
                                            }
                                        } catch (Exception e4) {
                                            e4.printStackTrace();
                                            Log.e(Constants.LOGTAG, "error in decompress data : " + e4.getMessage());
                                        }
                                    } else if (gZIPInputStream != null) {
                                        gZIPInputStream.close();
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    str3 = byteArrayOutputStream;
                                    if (str3 != 0) {
                                        try {
                                            str3.close();
                                            if (gZIPInputStream != null) {
                                                gZIPInputStream.close();
                                            }
                                        } catch (Exception e5) {
                                            e5.printStackTrace();
                                            Log.e(Constants.LOGTAG, str2 + e5.getMessage());
                                            throw th;
                                        }
                                    } else if (gZIPInputStream != null) {
                                        gZIPInputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (IOException e6) {
                                e = e6;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (IOException e7) {
                        e = e7;
                        gZIPInputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        gZIPInputStream = null;
                    }
                    str2 = "<compressContent></compressContent>";
                    return Replace.replace("<compressContent></compressContent>", r1);
                }
            }
        }
        return str;
    }

    public final String a(IHttpApi iHttpApi, String str, Map<String, String> map, ISignature iSignature, String str2, String str3) {
        return a(iHttpApi, str, map, iSignature, str2, str3, (Map<String, String>) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String a(IHttpApi iHttpApi, String str, String str2, ISignature iSignature, String str3, String str4, Map<String, String> map) {
        HiResponse hiResponseExecute;
        try {
            if (!map.isEmpty()) {
                hiResponseExecute = ((GetBuilder) ((GetBuilder) ((GetBuilder) iHttpApi.get().url(str)).addQueryParams(SDKUtil.getMapFromJson(str2)).hearders(map)).signature(iSignature)).execute();
            } else {
                hiResponseExecute = ((GetBuilder) ((GetBuilder) iHttpApi.get().url(str)).addQueryParams(SDKUtil.getMapFromJson(str2)).signature(iSignature)).execute();
            }
            if (hiResponseExecute != null) {
                if (hiResponseExecute.isSuccessful()) {
                    String strString = hiResponseExecute.getBody().string();
                    SDKUtil.LogD("HttpHandler", "result=" + strString);
                    str3 = strString;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(hiResponseExecute.getCode());
                    str3 = str3.replace(str4, sb.toString().replace("\"", ""));
                }
            }
        } catch (HttpException e2) {
            e2.printStackTrace();
            str3 = str3.replace(str4, SDKUtil.toUTF_8(e2 + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getMessage() + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getCause()).replace("\"", ""));
        }
        SDKUtil.LogD("HttpHandler", "result=" + str3);
        return str3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String a(IHttpApi iHttpApi, String str, String str2, ISignature iSignature, String str3, String str4, Map<String, String> map, String str5) {
        HiResponse hiResponseExecute;
        try {
            if (!map.isEmpty()) {
                if (TextUtils.isEmpty(str5)) {
                    hiResponseExecute = ((PostStringBuilder) ((PostStringBuilder) ((PostStringBuilder) iHttpApi.postString().url(str)).content(str2).hearders(map)).signature(iSignature)).execute();
                } else {
                    hiResponseExecute = ((PostStringBuilder) ((PostStringBuilder) ((PostStringBuilder) iHttpApi.postString().url(str)).content(str2).contentType(str5).hearders(map)).signature(iSignature)).execute();
                }
            } else if (TextUtils.isEmpty(str5)) {
                hiResponseExecute = ((PostStringBuilder) ((PostStringBuilder) iHttpApi.postString().url(str)).content(str2).signature(iSignature)).execute();
            } else {
                hiResponseExecute = ((PostStringBuilder) ((PostStringBuilder) iHttpApi.postString().url(str)).content(str2).contentType(str5).signature(iSignature)).execute();
            }
            if (hiResponseExecute != null) {
                if (hiResponseExecute.isSuccessful()) {
                    String strString = hiResponseExecute.getBody().string();
                    SDKUtil.LogD("HttpHandler", "result=" + strString);
                    str3 = strString;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(hiResponseExecute.getCode());
                    str3 = str3.replace(str4, sb.toString().replace("\"", ""));
                }
            }
        } catch (HttpException e2) {
            e2.printStackTrace();
            str3 = str3.replace(str4, SDKUtil.toUTF_8(e2 + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getMessage() + Utils.APP_ID_IDENTIFICATION_SUBSTRING + e2.getCause()).replace("\"", ""));
        }
        SDKUtil.LogD("HttpHandler", "result=" + str3);
        return str3;
    }
}
