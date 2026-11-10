package com.hmct.cloud.sdk.a;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.installations.local.IidStore;
import com.google.firebase.storage.StorageMetadata;
import com.hmct.cloud.sdk.bean.account.CustomerInfo;
import com.hmct.cloud.sdk.bean.account.DevSerialReply;
import com.hmct.cloud.sdk.bean.account.SignonReplyInfo;
import com.hmct.cloud.sdk.bean.basp.AllocNewMacReply;
import com.hmct.cloud.sdk.bean.basp.AreaWeatherForecastInfoReply;
import com.hmct.cloud.sdk.bean.basp.AreaWeatherInfoReplay;
import com.hmct.cloud.sdk.bean.basp.CallCenterReplyInfo;
import com.hmct.cloud.sdk.bean.basp.Callcenter;
import com.hmct.cloud.sdk.bean.basp.DomainListReplyInfo;
import com.hmct.cloud.sdk.bean.basp.LocalAreaInfoReply;
import com.hmct.cloud.sdk.bean.basp.WeatherForecastInfo;
import com.hmct.cloud.sdk.bean.global.ErrorInfo;
import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.bean.remotectl.LatestVersionInfoRely;
import com.hmct.cloud.sdk.bean.wallpaper.Comment;
import com.hmct.cloud.sdk.bean.wallpaper.MetaInfo;
import com.hmct.cloud.sdk.bean.wallpaper.MobilebrowserLableReplay;
import com.hmct.cloud.sdk.bean.wallpaper.ObjectInfo;
import com.hmct.cloud.sdk.bean.wallpaper.OrderInfo;
import com.hmct.cloud.sdk.bean.wallpaper.Picture;
import com.hmct.cloud.sdk.bean.wallpaper.ResourceDetailInfo;
import com.hmct.cloud.sdk.bean.wallpaper.ResourceInfo;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeBaseReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeCommentReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeDownloadReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeHotWordReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeOrderCommitReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeOrderReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeRankReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeRecommendReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeResourceDetailReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeResourceReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeScoreReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeSearchReply;
import com.hmct.cloud.sdk.bean.wallpaper.ThemeTopicReply;
import com.hmct.cloud.sdk.utils.Params;
import com.hmct.cloud.sdk.utils.c;
import com.ju.lib.datacommunication.network.http.address.AddressResponse;
import com.ju.lib.datacommunication.network.http.address.ThirdAddressResponse;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import e.b.d;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static ThemeBaseReply A(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeBaseReply themeBaseReply = new ThemeBaseReply();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            themeBaseReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                themeBaseReply.setReplyDesc(dVarL.a(ThirdAddressResponse.DESC, ""));
                return themeBaseReply;
            }
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode(dVarL.a("errorCode", ""));
            errorInfo.setErrorName(dVarL.a("errordesc", ""));
            themeBaseReply.setError(errorInfo);
            return themeBaseReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] B(String str) {
        int length = str.length();
        int i = length / 2;
        if (length % 2 != 0) {
            str = "0" + str;
            i++;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            int i4 = i3 * 2;
            int i5 = i4 - 1;
            String strSubstring = str.substring(i2 * 2, i5);
            String strSubstring2 = str.substring(i5, i4);
            bArr[i2] = (byte) (((byte) (Byte.decode("0x" + strSubstring).byteValue() << 4)) ^ Byte.decode("0x" + strSubstring2).byteValue());
            i2 = i3;
        }
        return bArr;
    }

    public static int C(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static String D(String str) {
        return str == null ? "" : str.trim();
    }

    public static ReplyInfo a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ReplyInfo replyInfo = new ReplyInfo();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            replyInfo.setReply(dVarL.a(AddressResponse.sResultCode, 0));
            if (TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                replyInfo.setReplyDesc(dVarL.a(ThirdAddressResponse.DESC, ""));
                return replyInfo;
            }
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode(dVarL.a("errorCode", ""));
            errorInfo.setErrorName(dVarL.a("errordesc", ""));
            replyInfo.setError(errorInfo);
            return replyInfo;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static LatestVersionInfoRely b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            LatestVersionInfoRely latestVersionInfoRely = new LatestVersionInfoRely();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            latestVersionInfoRely.setReply(dVarL.a(AddressResponse.sResultCode, 0));
            if (TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                latestVersionInfoRely.setLatestVersion(dVarL.a("latestVersion", ""));
                latestVersionInfoRely.setMd5sum(dVarL.a("md5sum", ""));
                latestVersionInfoRely.setResourceFile(dVarL.a("resourceFile", ""));
                latestVersionInfoRely.setUpgradeFlag(dVarL.a("upgradeFlag", ""));
                return latestVersionInfoRely;
            }
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode(dVarL.a("errorCode", ""));
            errorInfo.setErrorName(dVarL.a("errordesc", ""));
            latestVersionInfoRely.setError(errorInfo);
            return latestVersionInfoRely;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static AllocNewMacReply c(String str) {
        ByteArrayInputStream byteArrayInputStream;
        String strTrim;
        String strTrim2;
        AllocNewMacReply allocNewMacReply = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            byteArrayInputStream = null;
        }
        try {
            try {
                try {
                    XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
                    xmlPullParserFactoryNewInstance.setNamespaceAware(true);
                    XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
                    xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
                    ErrorInfo errorInfo = null;
                    for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                        if (eventType != 0) {
                            if (eventType == 2) {
                                String name = xmlPullParserNewPullParser.getName();
                                if ("errorCode".equalsIgnoreCase(name)) {
                                    String strNextText = xmlPullParserNewPullParser.nextText();
                                    strTrim2 = strNextText != null ? strNextText.trim() : "";
                                    if (errorInfo == null) {
                                        errorInfo = new ErrorInfo();
                                    }
                                    errorInfo.setErrorCode(strTrim2);
                                } else if ("errordesc".equalsIgnoreCase(name)) {
                                    String strNextText2 = xmlPullParserNewPullParser.nextText();
                                    strTrim2 = strNextText2 != null ? strNextText2.trim() : "";
                                    if (errorInfo == null) {
                                        errorInfo = new ErrorInfo();
                                    }
                                    errorInfo.setErrorName(strTrim2);
                                } else if (AddressResponse.sResultCode.equalsIgnoreCase(name)) {
                                    String strTrim3 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim3 != null && !strTrim3.equals("")) {
                                        if (allocNewMacReply == null) {
                                            allocNewMacReply = new AllocNewMacReply();
                                        }
                                        allocNewMacReply.setReply(C(strTrim3));
                                    }
                                } else if ("mac".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim4 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim4 != null && !strTrim4.equals("")) {
                                        if (allocNewMacReply == null) {
                                            allocNewMacReply = new AllocNewMacReply();
                                        }
                                        allocNewMacReply.setMac(strTrim4);
                                    }
                                } else if ("allocResult".equalsIgnoreCase(xmlPullParserNewPullParser.getName()) && (strTrim = xmlPullParserNewPullParser.nextText().trim()) != null && !strTrim.equals("")) {
                                    if (allocNewMacReply == null) {
                                        allocNewMacReply = new AllocNewMacReply();
                                    }
                                    allocNewMacReply.setAllocResult(C(strTrim));
                                }
                            } else if (eventType == 3 && AddressResponse.sResponseTag.equalsIgnoreCase(xmlPullParserNewPullParser.getName()) && errorInfo != null) {
                                if (allocNewMacReply == null) {
                                    allocNewMacReply = new AllocNewMacReply();
                                }
                                allocNewMacReply.setError(errorInfo);
                            }
                        }
                    }
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                } catch (Throwable th) {
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        } catch (Exception e5) {
            e5.printStackTrace();
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
        }
        return allocNewMacReply;
    }

    public static ReplyInfo d(String str) {
        ByteArrayInputStream byteArrayInputStream;
        String strTrim;
        String strTrim2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            byteArrayInputStream = null;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
            ReplyInfo replyInfo = null;
            ErrorInfo errorInfo = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        if ("errorCode".equalsIgnoreCase(name)) {
                            String strNextText = xmlPullParserNewPullParser.nextText();
                            strTrim2 = strNextText != null ? strNextText.trim() : "";
                            if (errorInfo == null) {
                                errorInfo = new ErrorInfo();
                            }
                            errorInfo.setErrorCode(strTrim2);
                        } else if ("errordesc".equalsIgnoreCase(name)) {
                            String strNextText2 = xmlPullParserNewPullParser.nextText();
                            strTrim2 = strNextText2 != null ? strNextText2.trim() : "";
                            if (errorInfo == null) {
                                errorInfo = new ErrorInfo();
                            }
                            errorInfo.setErrorName(strTrim2);
                        } else if (AddressResponse.sResultCode.equalsIgnoreCase(name) && (strTrim = xmlPullParserNewPullParser.nextText().trim()) != null && !strTrim.equals("")) {
                            if (replyInfo == null) {
                                replyInfo = new ReplyInfo();
                            }
                            replyInfo.setReply(C(strTrim));
                        }
                    } else if (eventType == 3 && AddressResponse.sResponseTag.equalsIgnoreCase(xmlPullParserNewPullParser.getName()) && errorInfo != null) {
                        if (replyInfo == null) {
                            replyInfo = new ReplyInfo();
                        }
                        replyInfo.setError(errorInfo);
                    }
                }
            }
            return replyInfo;
        } catch (Exception e3) {
            e3.printStackTrace();
            if (byteArrayInputStream == null) {
                return null;
            }
            try {
                return null;
            } catch (IOException e4) {
                return null;
            }
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
        }
    }

    public static CallCenterReplyInfo e(String str) {
        ByteArrayInputStream byteArrayInputStream;
        String strTrim;
        String strTrim2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            byteArrayInputStream = null;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
            CallCenterReplyInfo callCenterReplyInfo = null;
            ArrayList arrayList = null;
            Callcenter callcenter = null;
            ErrorInfo errorInfo = null;
            ArrayList arrayList2 = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        if ("errorCode".equalsIgnoreCase(name)) {
                            String strNextText = xmlPullParserNewPullParser.nextText();
                            strTrim2 = strNextText != null ? strNextText.trim() : "";
                            if (errorInfo == null) {
                                errorInfo = new ErrorInfo();
                            }
                            errorInfo.setErrorCode(strTrim2);
                        } else if ("errorName".equalsIgnoreCase(name)) {
                            String strNextText2 = xmlPullParserNewPullParser.nextText();
                            strTrim2 = strNextText2 != null ? strNextText2.trim() : "";
                            if (errorInfo == null) {
                                errorInfo = new ErrorInfo();
                            }
                            errorInfo.setErrorName(strTrim2);
                        } else if (AddressResponse.sResultCode.equalsIgnoreCase(name)) {
                            String strTrim3 = xmlPullParserNewPullParser.nextText().trim();
                            if (strTrim3 != null && !strTrim3.equals("")) {
                                if (callCenterReplyInfo == null) {
                                    callCenterReplyInfo = new CallCenterReplyInfo();
                                }
                                callCenterReplyInfo.setReply(C(strTrim3));
                            }
                        } else if ("callcenterlist".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                            arrayList2 = new ArrayList();
                        } else if ("callcenter".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                            callcenter = new Callcenter();
                        } else if ("market".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                            String strTrim4 = xmlPullParserNewPullParser.nextText().trim();
                            if (strTrim4 != null && !strTrim4.equals("")) {
                                if (callcenter == null) {
                                    callcenter = new Callcenter();
                                }
                                callcenter.setMarket(strTrim4);
                            }
                        } else if ("country".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                            String strTrim5 = xmlPullParserNewPullParser.nextText().trim();
                            if (strTrim5 != null && !strTrim5.equals("")) {
                                if (callcenter == null) {
                                    callcenter = new Callcenter();
                                }
                                callcenter.setCountry(strTrim5);
                            }
                        } else if ("hotline".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                            String strTrim6 = xmlPullParserNewPullParser.nextText().trim();
                            if (strTrim6 != null && !strTrim6.equals("")) {
                                if (callcenter == null) {
                                    callcenter = new Callcenter();
                                }
                                callcenter.setHotline(strTrim6);
                            }
                        } else if ("faxnum".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                            String strTrim7 = xmlPullParserNewPullParser.nextText().trim();
                            if (strTrim7 != null && !strTrim7.equals("")) {
                                if (callcenter == null) {
                                    callcenter = new Callcenter();
                                }
                                callcenter.setFaxnum(strTrim7);
                            }
                        } else if ("email".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                            String strTrim8 = xmlPullParserNewPullParser.nextText().trim();
                            if (strTrim8 != null && !strTrim8.equals("")) {
                                if (callcenter == null) {
                                    callcenter = new Callcenter();
                                }
                                callcenter.setEmail(strTrim8);
                            }
                        } else if ("altcontactlist".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                            arrayList = new ArrayList();
                        } else if ("contact".equalsIgnoreCase(xmlPullParserNewPullParser.getName()) && (strTrim = xmlPullParserNewPullParser.nextText().trim()) != null && !strTrim.equals("")) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(strTrim);
                        }
                    } else if (eventType != 3) {
                        continue;
                    } else if ("altcontactlist".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                        if (arrayList != null) {
                            if (callcenter == null) {
                                callcenter = new Callcenter();
                            }
                            callcenter.setAltcontactlist(arrayList);
                        }
                    } else if ("callcenter".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                        if (callcenter != null) {
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            arrayList2.add(callcenter);
                        }
                    } else if ("callcenterlist".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                        if (arrayList2 != null) {
                            if (callCenterReplyInfo == null) {
                                callCenterReplyInfo = new CallCenterReplyInfo();
                            }
                            callCenterReplyInfo.setCallcenterlist(arrayList2);
                        }
                    } else if (AddressResponse.sResponseTag.equalsIgnoreCase(xmlPullParserNewPullParser.getName()) && errorInfo != null) {
                        if (callCenterReplyInfo == null) {
                            callCenterReplyInfo = new CallCenterReplyInfo();
                        }
                        callCenterReplyInfo.setError(errorInfo);
                    }
                }
            }
            return callCenterReplyInfo;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    public static DomainListReplyInfo f(String str) {
        String strD;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            DomainListReplyInfo domainListReplyInfo = null;
            ArrayList arrayList = null;
            HashMap<String, List<String>> map = null;
            ErrorInfo errorInfo = null;
            String strD2 = "";
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                            domainListReplyInfo = new DomainListReplyInfo();
                        } else if (name.equalsIgnoreCase(AddressResponse.sResultCode)) {
                            String strNextText = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText)) {
                                domainListReplyInfo.setReply(Integer.parseInt(strNextText));
                            }
                            if (domainListReplyInfo.getReply() != 0) {
                                ErrorInfo errorInfo2 = new ErrorInfo();
                                domainListReplyInfo.setError(errorInfo2);
                                errorInfo = errorInfo2;
                            }
                        } else if (name.equalsIgnoreCase("errorCode")) {
                            errorInfo.setErrorCode(D(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("errorDesc")) {
                            errorInfo.setErrorName(D(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase(AddressResponse.sAppServiceList)) {
                            map = new HashMap<>();
                        } else if (name.equalsIgnoreCase("addressType")) {
                            strD2 = D(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("addressList")) {
                            arrayList = new ArrayList();
                        } else if (name.equalsIgnoreCase(AddressResponse.sAddress) && (strD = D(xmlPullParserNewPullParser.nextText())) != null && !strD.equals("")) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(c.a(strD));
                        }
                    } else if (eventType != 3) {
                        continue;
                    } else {
                        String name2 = xmlPullParserNewPullParser.getName();
                        if (name2.equalsIgnoreCase(AddressResponse.sAddressInfo)) {
                            if (arrayList != null) {
                                if (map == null) {
                                    map = new HashMap<>();
                                }
                                map.put(strD2, arrayList);
                                arrayList = null;
                            }
                        } else if (name2.equalsIgnoreCase(AddressResponse.sAppServiceList) && map != null) {
                            if (domainListReplyInfo == null) {
                                domainListReplyInfo = new DomainListReplyInfo();
                            }
                            domainListReplyInfo.setDomainMap(map);
                        }
                    }
                }
            }
            return domainListReplyInfo;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static AreaWeatherInfoReplay g(String str) {
        ByteArrayInputStream byteArrayInputStream;
        String strTrim;
        String strTrim2;
        AreaWeatherInfoReplay areaWeatherInfoReplay = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            byteArrayInputStream = null;
        }
        try {
            try {
                try {
                    XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
                    xmlPullParserFactoryNewInstance.setNamespaceAware(true);
                    XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
                    xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
                    ErrorInfo errorInfo = null;
                    for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                        if (eventType != 0) {
                            if (eventType == 2) {
                                String name = xmlPullParserNewPullParser.getName();
                                if ("errorCode".equalsIgnoreCase(name)) {
                                    String strNextText = xmlPullParserNewPullParser.nextText();
                                    strTrim2 = strNextText != null ? strNextText.trim() : "";
                                    if (errorInfo == null) {
                                        errorInfo = new ErrorInfo();
                                    }
                                    errorInfo.setErrorCode(strTrim2);
                                } else if ("errorName".equalsIgnoreCase(name)) {
                                    String strNextText2 = xmlPullParserNewPullParser.nextText();
                                    strTrim2 = strNextText2 != null ? strNextText2.trim() : "";
                                    if (errorInfo == null) {
                                        errorInfo = new ErrorInfo();
                                    }
                                    errorInfo.setErrorName(strTrim2);
                                } else if (AddressResponse.sResultCode.equalsIgnoreCase(name)) {
                                    String strTrim3 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim3 != null && !strTrim3.equals("")) {
                                        if (areaWeatherInfoReplay == null) {
                                            areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                        }
                                        areaWeatherInfoReplay.setReply(C(strTrim3));
                                    }
                                } else if ("publishTime".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim4 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim4 != null && !strTrim4.equals("")) {
                                        if (areaWeatherInfoReplay == null) {
                                            areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                        }
                                        areaWeatherInfoReplay.setPublishTime(strTrim4);
                                    }
                                } else if ("temperature".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim5 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim5 != null && !strTrim5.equals("")) {
                                        if (areaWeatherInfoReplay == null) {
                                            areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                        }
                                        areaWeatherInfoReplay.setTemperature(C(strTrim5));
                                    }
                                } else if ("humidity".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim6 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim6 != null && !strTrim6.equals("")) {
                                        if (areaWeatherInfoReplay == null) {
                                            areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                        }
                                        areaWeatherInfoReplay.setHumidity(C(strTrim6));
                                    }
                                } else if ("weatherCode".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim7 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim7 != null && !strTrim7.equals("")) {
                                        if (areaWeatherInfoReplay == null) {
                                            areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                        }
                                        areaWeatherInfoReplay.setWeatherCode(strTrim7);
                                    }
                                } else if ("weatherName".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim8 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim8 != null && !strTrim8.equals("")) {
                                        if (areaWeatherInfoReplay == null) {
                                            areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                        }
                                        areaWeatherInfoReplay.setWeatherName(strTrim8);
                                    }
                                } else if ("windForce".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim9 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim9 != null && !strTrim9.equals("")) {
                                        if (areaWeatherInfoReplay == null) {
                                            areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                        }
                                        areaWeatherInfoReplay.setWindForce(C(strTrim9));
                                    }
                                } else if ("windDirectionCode".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim10 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim10 != null && !strTrim10.equals("")) {
                                        if (areaWeatherInfoReplay == null) {
                                            areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                        }
                                        areaWeatherInfoReplay.setWindDirectionCode(strTrim10);
                                    }
                                } else if ("windDirectionName".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim11 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim11 != null && !strTrim11.equals("")) {
                                        if (areaWeatherInfoReplay == null) {
                                            areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                        }
                                        areaWeatherInfoReplay.setWindDirectionName(strTrim11);
                                    }
                                } else if ("aqiPublishTime".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim12 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim12 != null && !strTrim12.equals("")) {
                                        if (areaWeatherInfoReplay == null) {
                                            areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                        }
                                        areaWeatherInfoReplay.setAqiPublishTime(strTrim12);
                                    }
                                } else if ("pm25".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim13 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim13 != null && !strTrim13.equals("")) {
                                        if (areaWeatherInfoReplay == null) {
                                            areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                        }
                                        areaWeatherInfoReplay.setPm25(C(strTrim13));
                                    }
                                } else if ("aqi".equalsIgnoreCase(xmlPullParserNewPullParser.getName()) && (strTrim = xmlPullParserNewPullParser.nextText().trim()) != null && !strTrim.equals("")) {
                                    if (areaWeatherInfoReplay == null) {
                                        areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                    }
                                    areaWeatherInfoReplay.setAqi(C(strTrim));
                                }
                            } else if (eventType == 3 && AddressResponse.sResponseTag.equalsIgnoreCase(xmlPullParserNewPullParser.getName()) && errorInfo != null) {
                                if (areaWeatherInfoReplay == null) {
                                    areaWeatherInfoReplay = new AreaWeatherInfoReplay();
                                }
                                areaWeatherInfoReplay.setError(errorInfo);
                            }
                        }
                    }
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                } catch (Throwable th) {
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e4.printStackTrace();
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
        return areaWeatherInfoReplay;
    }

    public static AreaWeatherForecastInfoReply h(String str) {
        ByteArrayInputStream byteArrayInputStream;
        String strTrim;
        String strTrim2;
        AreaWeatherForecastInfoReply areaWeatherForecastInfoReply = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            byteArrayInputStream = null;
        }
        try {
            try {
                try {
                    XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
                    xmlPullParserFactoryNewInstance.setNamespaceAware(true);
                    XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
                    xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
                    ErrorInfo errorInfo = null;
                    ArrayList arrayList = null;
                    WeatherForecastInfo weatherForecastInfo = null;
                    for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                        if (eventType != 0) {
                            if (eventType == 2) {
                                String name = xmlPullParserNewPullParser.getName();
                                if ("errorCode".equalsIgnoreCase(name)) {
                                    String strNextText = xmlPullParserNewPullParser.nextText();
                                    strTrim2 = strNextText != null ? strNextText.trim() : "";
                                    if (errorInfo == null) {
                                        errorInfo = new ErrorInfo();
                                    }
                                    errorInfo.setErrorCode(strTrim2);
                                } else if ("errorName".equalsIgnoreCase(name)) {
                                    String strNextText2 = xmlPullParserNewPullParser.nextText();
                                    strTrim2 = strNextText2 != null ? strNextText2.trim() : "";
                                    if (errorInfo == null) {
                                        errorInfo = new ErrorInfo();
                                    }
                                    errorInfo.setErrorName(strTrim2);
                                } else if (AddressResponse.sResultCode.equalsIgnoreCase(name)) {
                                    String strTrim3 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim3 != null && !strTrim3.equals("")) {
                                        if (areaWeatherForecastInfoReply == null) {
                                            areaWeatherForecastInfoReply = new AreaWeatherForecastInfoReply();
                                        }
                                        areaWeatherForecastInfoReply.setReply(C(strTrim3));
                                    }
                                } else if ("publishTime".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim4 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim4 != null && !strTrim4.equals("")) {
                                        if (areaWeatherForecastInfoReply == null) {
                                            areaWeatherForecastInfoReply = new AreaWeatherForecastInfoReply();
                                        }
                                        areaWeatherForecastInfoReply.setPublishTime(strTrim4);
                                    }
                                } else if ("weatherForecastInfo".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    weatherForecastInfo = new WeatherForecastInfo();
                                } else if ("dayTemperature".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim5 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim5 != null && !strTrim5.equals("")) {
                                        if (weatherForecastInfo == null) {
                                            weatherForecastInfo = new WeatherForecastInfo();
                                        }
                                        weatherForecastInfo.setDayTemperature(strTrim5);
                                    }
                                } else if ("dayWeatherCode".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim6 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim6 != null && !strTrim6.equals("")) {
                                        if (weatherForecastInfo == null) {
                                            weatherForecastInfo = new WeatherForecastInfo();
                                        }
                                        weatherForecastInfo.setDayWeatherCode(strTrim6);
                                    }
                                } else if ("dayWeatherName".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim7 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim7 != null && !strTrim7.equals("")) {
                                        if (weatherForecastInfo == null) {
                                            weatherForecastInfo = new WeatherForecastInfo();
                                        }
                                        weatherForecastInfo.setDayWeatherName(strTrim7);
                                    }
                                } else if ("dayWindForce".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim8 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim8 != null && !strTrim8.equals("")) {
                                        if (weatherForecastInfo == null) {
                                            weatherForecastInfo = new WeatherForecastInfo();
                                        }
                                        weatherForecastInfo.setDayWindForce(strTrim8);
                                    }
                                } else if ("dayWindDirectionCode".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim9 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim9 != null && !strTrim9.equals("")) {
                                        if (weatherForecastInfo == null) {
                                            weatherForecastInfo = new WeatherForecastInfo();
                                        }
                                        weatherForecastInfo.setDayWindDirectionCode(strTrim9);
                                    }
                                } else if ("dayWindDirectionName".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim10 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim10 != null && !strTrim10.equals("")) {
                                        if (weatherForecastInfo == null) {
                                            weatherForecastInfo = new WeatherForecastInfo();
                                        }
                                        weatherForecastInfo.setDayWindDirectionName(strTrim10);
                                    }
                                } else if ("nightTemperature".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim11 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim11 != null && !strTrim11.equals("")) {
                                        if (weatherForecastInfo == null) {
                                            weatherForecastInfo = new WeatherForecastInfo();
                                        }
                                        weatherForecastInfo.setNightTemperature(strTrim11);
                                    }
                                } else if ("nightWeatherCode".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim12 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim12 != null && !strTrim12.equals("")) {
                                        if (weatherForecastInfo == null) {
                                            weatherForecastInfo = new WeatherForecastInfo();
                                        }
                                        weatherForecastInfo.setNightWeatherCode(strTrim12);
                                    }
                                } else if ("nightWeatherName".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim13 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim13 != null && !strTrim13.equals("")) {
                                        if (weatherForecastInfo == null) {
                                            weatherForecastInfo = new WeatherForecastInfo();
                                        }
                                        weatherForecastInfo.setNightWeatherName(strTrim13);
                                    }
                                } else if ("nightWindForce".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim14 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim14 != null && !strTrim14.equals("")) {
                                        if (weatherForecastInfo == null) {
                                            weatherForecastInfo = new WeatherForecastInfo();
                                        }
                                        weatherForecastInfo.setNightWindForce(strTrim14);
                                    }
                                } else if ("nightWindDirectionCode".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim15 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim15 != null && !strTrim15.equals("")) {
                                        if (weatherForecastInfo == null) {
                                            weatherForecastInfo = new WeatherForecastInfo();
                                        }
                                        weatherForecastInfo.setNightWindDirectionCode(strTrim15);
                                    }
                                } else if ("nightWindDirectionName".equalsIgnoreCase(xmlPullParserNewPullParser.getName()) && (strTrim = xmlPullParserNewPullParser.nextText().trim()) != null && !strTrim.equals("")) {
                                    if (weatherForecastInfo == null) {
                                        weatherForecastInfo = new WeatherForecastInfo();
                                    }
                                    weatherForecastInfo.setNightWindDirectionName(strTrim);
                                }
                            } else if (eventType != 3) {
                                continue;
                            } else if (AddressResponse.sResponseTag.equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                if (errorInfo != null) {
                                    if (areaWeatherForecastInfoReply == null) {
                                        areaWeatherForecastInfoReply = new AreaWeatherForecastInfoReply();
                                    }
                                    areaWeatherForecastInfoReply.setError(errorInfo);
                                }
                            } else if ("weatherForecastList".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                if (arrayList != null) {
                                    if (areaWeatherForecastInfoReply == null) {
                                        areaWeatherForecastInfoReply = new AreaWeatherForecastInfoReply();
                                    }
                                    areaWeatherForecastInfoReply.setWeatherForecastList(arrayList);
                                }
                            } else if ("weatherForecastInfo".equalsIgnoreCase(xmlPullParserNewPullParser.getName()) && weatherForecastInfo != null) {
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                arrayList.add(weatherForecastInfo);
                            }
                        }
                    }
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            return areaWeatherForecastInfoReply;
        } catch (Throwable th) {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static LocalAreaInfoReply i(String str) {
        ByteArrayInputStream byteArrayInputStream;
        String strTrim;
        String strTrim2;
        LocalAreaInfoReply localAreaInfoReply = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            byteArrayInputStream = null;
        }
        try {
            try {
                try {
                    XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
                    xmlPullParserFactoryNewInstance.setNamespaceAware(true);
                    XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
                    xmlPullParserNewPullParser.setInput(byteArrayInputStream, "UTF-8");
                    ErrorInfo errorInfo = null;
                    for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                        if (eventType != 0) {
                            if (eventType == 2) {
                                String name = xmlPullParserNewPullParser.getName();
                                if ("errorCode".equalsIgnoreCase(name)) {
                                    String strNextText = xmlPullParserNewPullParser.nextText();
                                    strTrim2 = strNextText != null ? strNextText.trim() : "";
                                    if (errorInfo == null) {
                                        errorInfo = new ErrorInfo();
                                    }
                                    errorInfo.setErrorCode(strTrim2);
                                } else if ("errorName".equalsIgnoreCase(name)) {
                                    String strNextText2 = xmlPullParserNewPullParser.nextText();
                                    strTrim2 = strNextText2 != null ? strNextText2.trim() : "";
                                    if (errorInfo == null) {
                                        errorInfo = new ErrorInfo();
                                    }
                                    errorInfo.setErrorName(strTrim2);
                                } else if (AddressResponse.sResultCode.equalsIgnoreCase(name)) {
                                    String strTrim3 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim3 != null && !strTrim3.equals("")) {
                                        if (localAreaInfoReply == null) {
                                            localAreaInfoReply = new LocalAreaInfoReply();
                                        }
                                        localAreaInfoReply.setReply(C(strTrim3));
                                    }
                                } else if ("sysCountry".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim4 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim4 != null && !strTrim4.equals("")) {
                                        if (localAreaInfoReply == null) {
                                            localAreaInfoReply = new LocalAreaInfoReply();
                                        }
                                        localAreaInfoReply.setSysCountry(c.a(strTrim4));
                                    }
                                } else if ("sysProvince".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim5 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim5 != null && !strTrim5.equals("")) {
                                        if (localAreaInfoReply == null) {
                                            localAreaInfoReply = new LocalAreaInfoReply();
                                        }
                                        localAreaInfoReply.setSysProvince(c.a(strTrim5));
                                    }
                                } else if ("sysCity".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim6 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim6 != null && !strTrim6.equals("")) {
                                        if (localAreaInfoReply == null) {
                                            localAreaInfoReply = new LocalAreaInfoReply();
                                        }
                                        localAreaInfoReply.setSysCity(c.a(strTrim6));
                                    }
                                } else if ("tvCountry".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim7 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim7 != null && !strTrim7.equals("")) {
                                        if (localAreaInfoReply == null) {
                                            localAreaInfoReply = new LocalAreaInfoReply();
                                        }
                                        localAreaInfoReply.setTvCountry(c.a(strTrim7));
                                    }
                                } else if ("tvProvince".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim8 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim8 != null && !strTrim8.equals("")) {
                                        if (localAreaInfoReply == null) {
                                            localAreaInfoReply = new LocalAreaInfoReply();
                                        }
                                        localAreaInfoReply.setTvProvince(c.a(strTrim8));
                                    }
                                } else if ("tvCity".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                                    String strTrim9 = xmlPullParserNewPullParser.nextText().trim();
                                    if (strTrim9 != null && !strTrim9.equals("")) {
                                        if (localAreaInfoReply == null) {
                                            localAreaInfoReply = new LocalAreaInfoReply();
                                        }
                                        localAreaInfoReply.setTvCity(c.a(strTrim9));
                                    }
                                } else if ("deviceIp".equalsIgnoreCase(xmlPullParserNewPullParser.getName()) && (strTrim = xmlPullParserNewPullParser.nextText().trim()) != null && !strTrim.equals("")) {
                                    if (localAreaInfoReply == null) {
                                        localAreaInfoReply = new LocalAreaInfoReply();
                                    }
                                    localAreaInfoReply.setDeviceIp(c.a(strTrim));
                                }
                            } else if (eventType == 3 && AddressResponse.sResponseTag.equalsIgnoreCase(xmlPullParserNewPullParser.getName()) && errorInfo != null) {
                                if (localAreaInfoReply == null) {
                                    localAreaInfoReply = new LocalAreaInfoReply();
                                }
                                localAreaInfoReply.setError(errorInfo);
                            }
                        }
                    }
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                }
            } catch (Throwable th) {
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
        return localAreaInfoReply;
    }

    public static SignonReplyInfo j(String str) {
        if (TextUtils.isEmpty(str)) {
            SignonReplyInfo signonReplyInfo = new SignonReplyInfo();
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode("000001");
            errorInfo.setErrorName("xmlString is null");
            signonReplyInfo.setReply(1);
            signonReplyInfo.setError(errorInfo);
            return signonReplyInfo;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            SignonReplyInfo signonReplyInfo2 = null;
            ErrorInfo errorInfo2 = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0 && eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                        signonReplyInfo2 = new SignonReplyInfo();
                    } else if (name.equalsIgnoreCase("resultcode")) {
                        String strNextText = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText)) {
                            signonReplyInfo2.setReply(b.a(strNextText));
                            if (signonReplyInfo2.getReply() != 0) {
                                ErrorInfo errorInfo3 = new ErrorInfo();
                                signonReplyInfo2.setError(errorInfo3);
                                errorInfo2 = errorInfo3;
                            }
                        }
                    } else if (name.equalsIgnoreCase("errorcode")) {
                        errorInfo2.setErrorCode(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("errordesc")) {
                        errorInfo2.setErrorName(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase(Params.LOGINNAME)) {
                        signonReplyInfo2.setLoginName(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("subscriberid")) {
                        signonReplyInfo2.setSubscriberId(b.a(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("customerId")) {
                        signonReplyInfo2.setCustomerId(b.a(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase(IidStore.JSON_TOKEN_KEY)) {
                        signonReplyInfo2.setToken(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("tokencreatetime")) {
                        signonReplyInfo2.setTokenCreateTime(b.b(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("tokenexpiredtime")) {
                        signonReplyInfo2.setTokenExpireTime(b.a(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("refreshToken")) {
                        signonReplyInfo2.setRefreshToken(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("refreshTokenExpiredTime")) {
                        signonReplyInfo2.setRefreshTokenExpiredTime(b.a(xmlPullParserNewPullParser.nextText()));
                    }
                }
            }
            return signonReplyInfo2;
        } catch (Exception e2) {
            e2.printStackTrace();
            SignonReplyInfo signonReplyInfo3 = new SignonReplyInfo();
            ErrorInfo errorInfo4 = new ErrorInfo();
            errorInfo4.setErrorCode("000001");
            errorInfo4.setErrorName(str);
            signonReplyInfo3.setReply(1);
            signonReplyInfo3.setError(errorInfo4);
            return signonReplyInfo3;
        }
    }

    public static DevSerialReply k(String str) {
        DevSerialReply devSerialReply = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            ErrorInfo errorInfo = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0 && eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                        devSerialReply = new DevSerialReply();
                    } else if (name.equalsIgnoreCase("resultcode")) {
                        String strNextText = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText)) {
                            devSerialReply.setReply(b.a(strNextText));
                            if (devSerialReply.getReply() != 0) {
                                ErrorInfo errorInfo2 = new ErrorInfo();
                                devSerialReply.setError(errorInfo2);
                                errorInfo = errorInfo2;
                            }
                        }
                    } else if (name.equalsIgnoreCase("errorcode")) {
                        errorInfo.setErrorCode(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("errordesc")) {
                        errorInfo.setErrorName(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("devSerial")) {
                        devSerialReply.setDevSerial(xmlPullParserNewPullParser.nextText().trim());
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return devSerialReply;
    }

    public static ReplyInfo l(String str) {
        DevSerialReply devSerialReply = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            ErrorInfo errorInfo = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0 && eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                        devSerialReply = new DevSerialReply();
                    } else if (name.equalsIgnoreCase("resultcode")) {
                        String strNextText = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText)) {
                            if (devSerialReply == null) {
                                devSerialReply = new DevSerialReply();
                            }
                            devSerialReply.setReply(b.a(strNextText));
                            if (devSerialReply.getReply() != 0) {
                                ErrorInfo errorInfo2 = new ErrorInfo();
                                devSerialReply.setError(errorInfo2);
                                errorInfo = errorInfo2;
                            }
                        }
                    } else if (name.equalsIgnoreCase("errorcode")) {
                        if (errorInfo == null) {
                            errorInfo = new ErrorInfo();
                            if (devSerialReply == null) {
                                devSerialReply = new DevSerialReply();
                            }
                            devSerialReply.setError(errorInfo);
                        }
                        errorInfo.setErrorCode(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("errordesc")) {
                        if (errorInfo == null) {
                            errorInfo = new ErrorInfo();
                            if (devSerialReply == null) {
                                devSerialReply = new DevSerialReply();
                            }
                            devSerialReply.setError(errorInfo);
                        }
                        errorInfo.setErrorName(xmlPullParserNewPullParser.nextText().trim());
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return devSerialReply;
    }

    public static CustomerInfo m(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            CustomerInfo customerInfo = null;
            ErrorInfo errorInfo = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0 && eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                        customerInfo = new CustomerInfo();
                    } else if (name.equalsIgnoreCase("resultcode")) {
                        String strNextText = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText)) {
                            customerInfo.setReply(b.a(strNextText));
                            if (customerInfo.getReply() != 0) {
                                ErrorInfo errorInfo2 = new ErrorInfo();
                                customerInfo.setError(errorInfo2);
                                errorInfo = errorInfo2;
                            }
                        }
                    } else if (name.equalsIgnoreCase("errorcode")) {
                        errorInfo.setErrorCode(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("errordesc")) {
                        errorInfo.setErrorName(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("subscriberId")) {
                        String strNextText2 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText2)) {
                            customerInfo.setSubscriberId(b.a(strNextText2));
                        }
                    } else if (name.equalsIgnoreCase("serviceNumber")) {
                        customerInfo.setServiceNumber(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("mac")) {
                        customerInfo.setMac(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("deviceCode")) {
                        customerInfo.setDeviceCode(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase(NotificationCompat.CATEGORY_STATUS)) {
                        String strNextText3 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText3)) {
                            customerInfo.setStatus(b.a(strNextText3));
                        }
                    } else if (name.equalsIgnoreCase("createdTime")) {
                        String strNextText4 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText4)) {
                            customerInfo.setCreatedTime(b.b(strNextText4));
                        }
                    } else if (name.equalsIgnoreCase("activatedTime")) {
                        String strNextText5 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText5)) {
                            customerInfo.setActivatedTime(b.b(strNextText5));
                        }
                    } else if (name.equalsIgnoreCase("customerId")) {
                        String strNextText6 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText6)) {
                            customerInfo.setCustomerId(b.a(strNextText6));
                        }
                    } else if (name.equalsIgnoreCase("loginname")) {
                        customerInfo.setLoginName(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("devicename")) {
                        customerInfo.setDeviceName(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("name")) {
                        customerInfo.setName(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("sex")) {
                        String strNextText7 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText7)) {
                            customerInfo.setSex(b.a(strNextText7));
                        }
                    } else if (name.equalsIgnoreCase("email")) {
                        customerInfo.setEmail(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase(AddressResponse.sAddress)) {
                        customerInfo.setAddress(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("zipcode")) {
                        customerInfo.setZipCode(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("idtype")) {
                        String strNextText8 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText8)) {
                            customerInfo.setIdType(b.a(strNextText8));
                        }
                    } else if (name.equalsIgnoreCase("idNumber")) {
                        customerInfo.setIdNumber(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("phone")) {
                        customerInfo.setPhone(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("mobilephone")) {
                        customerInfo.setMobilePhone(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("birthday")) {
                        String strNextText9 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText9)) {
                            customerInfo.setBirthday(b.b(strNextText9));
                        }
                    } else if (name.equalsIgnoreCase("nickname")) {
                        customerInfo.setNickName(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("customerpicurl")) {
                        customerInfo.setCustomerPicUrl(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("customerpicid")) {
                        customerInfo.setCustomerPicId(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("isUseSubPin")) {
                        String strNextText10 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText10)) {
                            customerInfo.setIsUseSubPin(b.a(strNextText10));
                        }
                    } else if (name.equalsIgnoreCase("clientIp")) {
                        customerInfo.setClientIp(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("createdDate")) {
                        String strNextText11 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText11)) {
                            customerInfo.setCreatedDate(b.b(strNextText11));
                        }
                    } else if (name.equalsIgnoreCase("customerType")) {
                        String strNextText12 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText12)) {
                            customerInfo.setCustomerType(b.a(strNextText12));
                        }
                    } else if (name.equalsIgnoreCase("customerStatus")) {
                        String strNextText13 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText13)) {
                            customerInfo.setCustomerStatus(b.a(strNextText13));
                        }
                    } else if (name.equalsIgnoreCase(FirebaseAnalytics.Param.SCORE)) {
                        String strNextText14 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText14)) {
                            customerInfo.setScore(b.a(strNextText14));
                        }
                    } else if (name.equalsIgnoreCase("emailChecked")) {
                        String strNextText15 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText15)) {
                            customerInfo.setEmailChecked(b.a(strNextText15));
                        }
                    } else if (name.equalsIgnoreCase("mobilePhoneChecked")) {
                        String strNextText16 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText16)) {
                            customerInfo.setMobilePhoneChecked(b.a(strNextText16));
                        }
                    } else if (name.equalsIgnoreCase("registType")) {
                        String strNextText17 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText17)) {
                            customerInfo.setRegistType(b.a(strNextText17));
                        }
                    }
                }
            }
            return customerInfo;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ThemeRecommendReply n(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeRecommendReply themeRecommendReply = new ThemeRecommendReply();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            themeRecommendReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (!TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(dVarL.a("errorCode", ""));
                errorInfo.setErrorName(dVarL.a("errorDesc", ""));
                themeRecommendReply.setError(errorInfo);
                return themeRecommendReply;
            }
            themeRecommendReply.setUpdateFlag(dVarL.a("updateFlag", 0));
            long j = 0;
            themeRecommendReply.setLastUpdateTime(dVarL.a("lastUpdateTime", 0L));
            themeRecommendReply.setResourceType(dVarL.a("resourceType", 0));
            themeRecommendReply.setCount(dVarL.a("count", 0));
            e.b.a aVarK = dVarL.k("recommends");
            ArrayList arrayList = new ArrayList();
            if (aVarK != null) {
                int iA = aVarK.a();
                int i = 0;
                while (i < iA) {
                    d dVarD = aVarK.d(i);
                    if (dVarD != null) {
                        ResourceInfo resourceInfo = new ResourceInfo();
                        resourceInfo.setId(dVarD.a("id", j));
                        resourceInfo.setResourceType(dVarD.a("type", 0));
                        resourceInfo.setFreeFlag(dVarD.a("freeFlag", 0));
                        resourceInfo.setPrice(dVarD.a(FirebaseAnalytics.Param.PRICE, 0));
                        resourceInfo.setPicture(dVarD.a("picture", ""));
                        resourceInfo.setDisplayOrder(dVarD.a("displayOrder", 0));
                        e.b.a aVarK2 = dVarD.k("meta");
                        ArrayList arrayList2 = new ArrayList();
                        if (aVarK2 != null) {
                            int iA2 = aVarK2.a();
                            for (int i2 = 0; i2 < iA2; i2++) {
                                d dVarD2 = aVarK2.d(i2);
                                if (dVarD2 != null) {
                                    MetaInfo metaInfo = new MetaInfo();
                                    metaInfo.setLanguageId(dVarD2.a(Params.LANGUAGEID, 0));
                                    metaInfo.setName(dVarD2.a("name", ""));
                                    arrayList2.add(metaInfo);
                                }
                            }
                        }
                        resourceInfo.setMeta(arrayList2);
                        arrayList.add(resourceInfo);
                    }
                    i++;
                    j = 0;
                }
            }
            themeRecommendReply.setRecommends(arrayList);
            return themeRecommendReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ThemeRankReply o(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeRankReply themeRankReply = new ThemeRankReply();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            themeRankReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (!TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(dVarL.a("errorCode", ""));
                errorInfo.setErrorName(dVarL.a("errorDesc", ""));
                themeRankReply.setError(errorInfo);
                return themeRankReply;
            }
            themeRankReply.setUpdateFlag(dVarL.a("updateFlag", 0));
            long j = 0;
            themeRankReply.setLastUpdateTime(dVarL.a("lastUpdateTime", 0L));
            themeRankReply.setResourceType(dVarL.a("resourceType", 0));
            themeRankReply.setCount(dVarL.a("count", 0));
            e.b.a aVarK = dVarL.k("ranks");
            ArrayList arrayList = new ArrayList();
            if (aVarK != null) {
                int iA = aVarK.a();
                int i = 0;
                while (i < iA) {
                    d dVarD = aVarK.d(i);
                    if (dVarD != null) {
                        ResourceInfo resourceInfo = new ResourceInfo();
                        resourceInfo.setId(dVarD.a("id", j));
                        resourceInfo.setFreeFlag(dVarD.a("freeFlag", 0));
                        resourceInfo.setPrice(dVarD.a(FirebaseAnalytics.Param.PRICE, 0));
                        resourceInfo.setDisplayOrder(dVarD.a("displayOrder", 0));
                        resourceInfo.setPicture(dVarD.a("picture", ""));
                        e.b.a aVarK2 = dVarD.k("meta");
                        ArrayList arrayList2 = new ArrayList();
                        if (aVarK2 != null) {
                            int iA2 = aVarK2.a();
                            for (int i2 = 0; i2 < iA2; i2++) {
                                d dVarD2 = aVarK2.d(i2);
                                if (dVarD2 != null) {
                                    MetaInfo metaInfo = new MetaInfo();
                                    metaInfo.setLanguageId(dVarD2.a(Params.LANGUAGEID, 0));
                                    metaInfo.setName(dVarD2.a("name", ""));
                                    arrayList2.add(metaInfo);
                                }
                            }
                        }
                        resourceInfo.setMeta(arrayList2);
                        arrayList.add(resourceInfo);
                    }
                    i++;
                    j = 0;
                }
            }
            themeRankReply.setRanks(arrayList);
            return themeRankReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ThemeResourceReply p(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeResourceReply themeResourceReply = new ThemeResourceReply();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            themeResourceReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (!TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(dVarL.a("errorCode", ""));
                errorInfo.setErrorName(dVarL.a("errorDesc", ""));
                themeResourceReply.setError(errorInfo);
                return themeResourceReply;
            }
            themeResourceReply.setUpdateFlag(dVarL.a("updateFlag", 0));
            long j = 0;
            themeResourceReply.setLastUpdateTime(dVarL.a("lastUpdateTime", 0L));
            themeResourceReply.setResourceType(dVarL.a("resourceType", 0));
            themeResourceReply.setCount(dVarL.a("count", 0));
            e.b.a aVarK = dVarL.k("resource");
            ArrayList arrayList = new ArrayList();
            if (aVarK != null) {
                int iA = aVarK.a();
                int i = 0;
                while (i < iA) {
                    d dVarD = aVarK.d(i);
                    if (dVarD != null) {
                        ResourceInfo resourceInfo = new ResourceInfo();
                        resourceInfo.setId(dVarD.a("id", j));
                        resourceInfo.setFreeFlag(dVarD.a("freeFlag", 0));
                        resourceInfo.setPrice(dVarD.a(FirebaseAnalytics.Param.PRICE, 0));
                        resourceInfo.setLastModify(dVarD.a("lastModify", j));
                        resourceInfo.setPicture(dVarD.a("picture", ""));
                        e.b.a aVarK2 = dVarD.k("meta");
                        ArrayList arrayList2 = new ArrayList();
                        if (aVarK2 != null) {
                            int iA2 = aVarK2.a();
                            for (int i2 = 0; i2 < iA2; i2++) {
                                d dVarD2 = aVarK2.d(i2);
                                if (dVarD2 != null) {
                                    MetaInfo metaInfo = new MetaInfo();
                                    metaInfo.setLanguageId(dVarD2.a(Params.LANGUAGEID, 0));
                                    metaInfo.setName(dVarD2.a("name", ""));
                                    arrayList2.add(metaInfo);
                                }
                            }
                        }
                        resourceInfo.setMeta(arrayList2);
                        arrayList.add(resourceInfo);
                    }
                    i++;
                    j = 0;
                }
            }
            themeResourceReply.setResourceInfo(arrayList);
            return themeResourceReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ThemeResourceDetailReply q(String str) {
        ThemeResourceDetailReply themeResourceDetailReply = null;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeResourceDetailReply themeResourceDetailReply2 = new ThemeResourceDetailReply();
            try {
                d dVarL = new d(str).l(AddressResponse.sResponseTag);
                themeResourceDetailReply2.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
                if (!TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorCode(dVarL.a("errorCode", ""));
                    errorInfo.setErrorName(dVarL.a("errorDesc", ""));
                    themeResourceDetailReply2.setError(errorInfo);
                    return themeResourceDetailReply2;
                }
                d dVarL2 = dVarL.l("detail");
                ResourceDetailInfo resourceDetailInfo = new ResourceDetailInfo();
                resourceDetailInfo.setResourceType(dVarL2.a("resourceType", 0));
                resourceDetailInfo.setResourcetId(dVarL2.a("resourcetId", 0L));
                resourceDetailInfo.setFreeFlag(dVarL2.a("freeFlag", 0));
                resourceDetailInfo.setPrice(dVarL2.a(FirebaseAnalytics.Param.PRICE, 0));
                resourceDetailInfo.setIsPurchased(dVarL2.a("isPurchased", 0));
                resourceDetailInfo.setSize(dVarL2.a(StorageMetadata.SIZE_KEY, 0L));
                resourceDetailInfo.setDesigner(dVarL2.a("designer", ""));
                resourceDetailInfo.setProducer(dVarL2.a("producer", ""));
                resourceDetailInfo.setScore(dVarL2.a(FirebaseAnalytics.Param.SCORE, 0));
                resourceDetailInfo.setDownloadTimes(dVarL2.a("downloadTimes", 0));
                resourceDetailInfo.setVersion(dVarL2.a("version", ""));
                resourceDetailInfo.setLastModify(dVarL2.a("lastModify", 0L));
                resourceDetailInfo.setCustomerscore(dVarL2.a("customerscore", 0));
                e.b.a aVarK = dVarL2.k("pictureInfo");
                ArrayList arrayList = new ArrayList();
                if (aVarK != null) {
                    int iA = aVarK.a();
                    for (int i = 0; i < iA; i++) {
                        d dVarD = aVarK.d(i);
                        if (dVarD != null) {
                            Picture picture = new Picture();
                            picture.setPicture(dVarD.a("picture", ""));
                            arrayList.add(picture);
                        }
                    }
                }
                resourceDetailInfo.setPictureInfo(arrayList);
                e.b.a aVarK2 = dVarL2.k("meta");
                ArrayList arrayList2 = new ArrayList();
                if (aVarK2 != null) {
                    int iA2 = aVarK2.a();
                    for (int i2 = 0; i2 < iA2; i2++) {
                        d dVarD2 = aVarK2.d(i2);
                        if (dVarD2 != null) {
                            MetaInfo metaInfo = new MetaInfo();
                            metaInfo.setLanguageId(dVarD2.a(Params.LANGUAGEID, 0));
                            metaInfo.setName(dVarD2.a("name", ""));
                            metaInfo.setDesc(dVarD2.a(ThirdAddressResponse.DESC, ""));
                            arrayList2.add(metaInfo);
                        }
                    }
                }
                resourceDetailInfo.setMeta(arrayList2);
                themeResourceDetailReply2.setResourceDetailInfo(resourceDetailInfo);
                return themeResourceDetailReply2;
            } catch (e.b.b e2) {
                e = e2;
                themeResourceDetailReply = themeResourceDetailReply2;
            }
        } catch (e.b.b e3) {
            e = e3;
        }
        e.printStackTrace();
        return themeResourceDetailReply;
    }

    public static ThemeHotWordReply r(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeHotWordReply themeHotWordReply = new ThemeHotWordReply();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            themeHotWordReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (!TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(dVarL.a("errorCode", ""));
                errorInfo.setErrorName(dVarL.a("errorDesc", ""));
                themeHotWordReply.setError(errorInfo);
                return themeHotWordReply;
            }
            themeHotWordReply.setUpdateFlag(dVarL.a("updateFlag", 0));
            themeHotWordReply.setLastUpdateTime(dVarL.a("lastUpdateTime", 0L));
            themeHotWordReply.setCount(dVarL.a("count", 0));
            e.b.a aVarK = dVarL.k("hotWords");
            ArrayList arrayList = new ArrayList();
            if (aVarK != null) {
                int iA = aVarK.a();
                for (int i = 0; i < iA; i++) {
                    d dVarD = aVarK.d(i);
                    if (dVarD != null) {
                        arrayList.add(dVarD.a("hotWord", ""));
                    }
                }
            }
            themeHotWordReply.setHotWord(arrayList);
            return themeHotWordReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ThemeSearchReply s(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeSearchReply themeSearchReply = new ThemeSearchReply();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            themeSearchReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (!TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(dVarL.a("errorCode", ""));
                errorInfo.setErrorName(dVarL.a("errorDesc", ""));
                themeSearchReply.setError(errorInfo);
                return themeSearchReply;
            }
            themeSearchReply.setCount(dVarL.a("count", 0));
            e.b.a aVarK = dVarL.k("resource");
            ArrayList arrayList = new ArrayList();
            if (aVarK != null) {
                int iA = aVarK.a();
                for (int i = 0; i < iA; i++) {
                    d dVarD = aVarK.d(i);
                    if (dVarD != null) {
                        ResourceInfo resourceInfo = new ResourceInfo();
                        resourceInfo.setId(dVarD.a("id", 0L));
                        resourceInfo.setResourceType(dVarD.a("resourceType", 0));
                        resourceInfo.setFreeFlag(dVarD.a("freeFlag", 0));
                        resourceInfo.setPrice(dVarD.a(FirebaseAnalytics.Param.PRICE, 0));
                        resourceInfo.setLastModify(dVarD.a("lastModify", 0L));
                        resourceInfo.setPicture(dVarD.a("picture", ""));
                        e.b.a aVarK2 = dVarD.k("meta");
                        ArrayList arrayList2 = new ArrayList();
                        if (aVarK2 != null) {
                            int iA2 = aVarK2.a();
                            for (int i2 = 0; i2 < iA2; i2++) {
                                d dVarD2 = aVarK2.d(i2);
                                if (dVarD2 != null) {
                                    MetaInfo metaInfo = new MetaInfo();
                                    metaInfo.setLanguageId(dVarD2.a(Params.LANGUAGEID, 0));
                                    metaInfo.setName(dVarD2.a("name", ""));
                                    arrayList2.add(metaInfo);
                                }
                            }
                        }
                        resourceInfo.setMeta(arrayList2);
                        arrayList.add(resourceInfo);
                    }
                }
            }
            themeSearchReply.setResource(arrayList);
            return themeSearchReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ThemeOrderReply t(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeOrderReply themeOrderReply = new ThemeOrderReply();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            themeOrderReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (!TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(dVarL.a("errorCode", ""));
                errorInfo.setErrorName(dVarL.a("errorDesc", ""));
                themeOrderReply.setError(errorInfo);
                return themeOrderReply;
            }
            themeOrderReply.setCount(dVarL.a("count", 0));
            e.b.a aVarK = dVarL.k("orderList");
            ArrayList arrayList = new ArrayList();
            if (aVarK != null) {
                int iA = aVarK.a();
                for (int i = 0; i < iA; i++) {
                    d dVarD = aVarK.d(i);
                    if (dVarD != null) {
                        OrderInfo orderInfo = new OrderInfo();
                        orderInfo.setId(dVarD.a("id", 0));
                        orderInfo.setResourceType(dVarD.a("resourceType", 0));
                        orderInfo.setFreeFlag(dVarD.a("freeFlag", 0));
                        orderInfo.setPrice(dVarD.a(FirebaseAnalytics.Param.PRICE, 0));
                        orderInfo.setOrderPrice(dVarD.a("orderPrice", 0));
                        orderInfo.setLastModify(dVarD.a("lastModify", 0L));
                        orderInfo.setPicture(dVarD.a("picture", ""));
                        e.b.a aVarK2 = dVarD.k("meta");
                        ArrayList arrayList2 = new ArrayList();
                        if (aVarK2 != null) {
                            int iA2 = aVarK2.a();
                            for (int i2 = 0; i2 < iA2; i2++) {
                                d dVarD2 = aVarK2.d(i2);
                                if (dVarD2 != null) {
                                    MetaInfo metaInfo = new MetaInfo();
                                    metaInfo.setLanguageId(dVarD2.a(Params.LANGUAGEID, 0));
                                    metaInfo.setName(dVarD2.a("name", ""));
                                    arrayList2.add(metaInfo);
                                }
                            }
                        }
                        orderInfo.setMeta(arrayList2);
                        arrayList.add(orderInfo);
                    }
                }
            }
            themeOrderReply.setOrderList(arrayList);
            return themeOrderReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ThemeDownloadReply u(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeDownloadReply themeDownloadReply = new ThemeDownloadReply();
            d dVar = new d(str);
            if (!TextUtils.isEmpty(dVar.a("descriptor", ""))) {
                themeDownloadReply.setResultCode(0);
                themeDownloadReply.setFinalUrl(dVar.a("descriptor", ""));
                return themeDownloadReply;
            }
            d dVarL = dVar.l(AddressResponse.sResponseTag);
            themeDownloadReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                return themeDownloadReply;
            }
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode(dVarL.a("errorCode", ""));
            errorInfo.setErrorName(dVarL.a("errorDesc", ""));
            themeDownloadReply.setError(errorInfo);
            return themeDownloadReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ThemeScoreReply v(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeScoreReply themeScoreReply = new ThemeScoreReply();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            themeScoreReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                return themeScoreReply;
            }
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorCode(dVarL.a("errorCode", ""));
            errorInfo.setErrorName(dVarL.a("errorDesc", ""));
            themeScoreReply.setError(errorInfo);
            return themeScoreReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ThemeCommentReply w(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeCommentReply themeCommentReply = new ThemeCommentReply();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            themeCommentReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (!TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(dVarL.a("errorCode", ""));
                errorInfo.setErrorName(dVarL.a("errorDesc", ""));
                themeCommentReply.setError(errorInfo);
                return themeCommentReply;
            }
            themeCommentReply.setCount(dVarL.a("count", 0));
            themeCommentReply.setUpdateFlag(dVarL.a("updateFlag", 0));
            themeCommentReply.setLastUpdateTime(dVarL.a("lastUpdateTime", 0L));
            e.b.a aVarK = dVarL.k("comments");
            ArrayList arrayList = new ArrayList();
            if (aVarK != null) {
                int iA = aVarK.a();
                for (int i = 0; i < iA; i++) {
                    d dVarD = aVarK.d(i);
                    if (dVarD != null) {
                        Comment comment = new Comment();
                        comment.setComment(dVarD.a("comment", ""));
                        arrayList.add(comment);
                    }
                }
            }
            themeCommentReply.setComments(arrayList);
            return themeCommentReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ThemeOrderCommitReply x(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeOrderCommitReply themeOrderCommitReply = new ThemeOrderCommitReply();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            themeOrderCommitReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (!TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(dVarL.a("errorCode", ""));
                errorInfo.setErrorName(dVarL.a("errorDesc", ""));
                themeOrderCommitReply.setError(errorInfo);
                return themeOrderCommitReply;
            }
            themeOrderCommitReply.setGoodsDesc(dVarL.a("goodsDesc", ""));
            themeOrderCommitReply.setGoodsName(dVarL.a("goodsName", ""));
            themeOrderCommitReply.setNotifyUrl(dVarL.a("notifyUrl", ""));
            themeOrderCommitReply.setOrdersn(dVarL.a("ordersn", ""));
            themeOrderCommitReply.setTotalFee(dVarL.a("totalFee", ""));
            return themeOrderCommitReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static MobilebrowserLableReplay y(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            MobilebrowserLableReplay mobilebrowserLableReplay = new MobilebrowserLableReplay();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            mobilebrowserLableReplay.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (!TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(dVarL.a("errorCode", ""));
                errorInfo.setErrorName(dVarL.a("errorDesc", ""));
                mobilebrowserLableReplay.setError(errorInfo);
                return mobilebrowserLableReplay;
            }
            mobilebrowserLableReplay.setCount(dVarL.a("count", 0));
            mobilebrowserLableReplay.setUpdateFlag(dVarL.a("updateFlag", 0));
            long j = 0;
            mobilebrowserLableReplay.setLastUpdateTime(dVarL.a("lastUpdateTime", 0L));
            e.b.a aVarK = dVarL.k("objectInfos");
            ArrayList arrayList = new ArrayList();
            if (aVarK != null) {
                int iA = aVarK.a();
                int i = 0;
                while (i < iA) {
                    d dVarD = aVarK.d(i);
                    if (dVarD != null) {
                        ObjectInfo objectInfo = new ObjectInfo();
                        objectInfo.setId(dVarD.a("id", j));
                        objectInfo.setDisplayOrder(dVarD.a("displayOrder", 0));
                        objectInfo.setType(dVarD.a("type", 0));
                        objectInfo.setUrl(dVarD.a(ImagesContract.URL, ""));
                        e.b.a aVarK2 = dVarD.k("meta");
                        ArrayList arrayList2 = new ArrayList();
                        if (aVarK2 != null) {
                            int iA2 = aVarK2.a();
                            for (int i2 = 0; i2 < iA2; i2++) {
                                d dVarD2 = aVarK2.d(i2);
                                if (dVarD2 != null) {
                                    MetaInfo metaInfo = new MetaInfo();
                                    metaInfo.setLanguageId(dVarD2.a(Params.LANGUAGEID, 0));
                                    metaInfo.setName(dVarD2.a("name", ""));
                                    metaInfo.setPicture(dVarD2.a("picture", ""));
                                    arrayList2.add(metaInfo);
                                }
                            }
                        }
                        objectInfo.setMeta(arrayList2);
                        arrayList.add(objectInfo);
                    }
                    i++;
                    j = 0;
                }
            }
            mobilebrowserLableReplay.setObjectInfos(arrayList);
            return mobilebrowserLableReplay;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static ThemeTopicReply z(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ThemeTopicReply themeTopicReply = new ThemeTopicReply();
            d dVarL = new d(str).l(AddressResponse.sResponseTag);
            themeTopicReply.setResultCode(dVarL.a(AddressResponse.sResultCode, 0));
            if (!TextUtils.isEmpty(dVarL.a("errorCode", ""))) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(dVarL.a("errorCode", ""));
                errorInfo.setErrorName(dVarL.a("errorDesc", ""));
                themeTopicReply.setError(errorInfo);
                return themeTopicReply;
            }
            themeTopicReply.setCount(dVarL.a("count", 0));
            themeTopicReply.setUpdateFlag(dVarL.a("updateFlag", 0));
            long j = 0;
            themeTopicReply.setLastUpdateTime(dVarL.a("lastUpdateTime", 0L));
            themeTopicReply.setTopicType(dVarL.a("topicType", 0));
            e.b.a aVarK = dVarL.k("contents");
            ArrayList arrayList = new ArrayList();
            if (aVarK != null) {
                int iA = aVarK.a();
                int i = 0;
                while (i < iA) {
                    d dVarD = aVarK.d(i);
                    if (dVarD != null) {
                        ResourceInfo resourceInfo = new ResourceInfo();
                        resourceInfo.setId(dVarD.a("id", j));
                        resourceInfo.setResourceType(dVarD.a("type", 0));
                        resourceInfo.setFreeFlag(dVarD.a("freeFlag", 0));
                        resourceInfo.setPrice(dVarD.a(FirebaseAnalytics.Param.PRICE, 0));
                        resourceInfo.setPicture(dVarD.a("picture", ""));
                        e.b.a aVarK2 = dVarD.k("meta");
                        if (aVarK2 != null) {
                            ArrayList arrayList2 = new ArrayList();
                            for (int i2 = 0; i2 < aVarK2.a(); i2++) {
                                d dVarD2 = aVarK2.d(i2);
                                if (dVarD2 != null) {
                                    MetaInfo metaInfo = new MetaInfo();
                                    metaInfo.setLanguageId(dVarD2.a(Params.LANGUAGEID, 0));
                                    metaInfo.setName(dVarD2.a("name", ""));
                                    arrayList2.add(metaInfo);
                                }
                            }
                            resourceInfo.setMeta(arrayList2);
                        }
                        resourceInfo.setDisplayOrder(dVarD.a("displayOrder", 0));
                        arrayList.add(resourceInfo);
                    }
                    i++;
                    j = 0;
                }
            }
            themeTopicReply.setContentInfoList(arrayList);
            return themeTopicReply;
        } catch (e.b.b e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String a(Context context) {
        return context.getSharedPreferences("thirdDeviceCode", 0).getString("thirdDeviceCode", "");
    }

    public static void a(Context context, String str) {
        context.getSharedPreferences("thirdDeviceCode", 0).edit().putString("thirdDeviceCode", str).apply();
    }

    public static String b(Context context) {
        return context.getSharedPreferences("mac", 0).getString("mac", "");
    }

    public static void b(Context context, String str) {
        context.getSharedPreferences("mac", 0).edit().putString("mac", str).apply();
    }

    public static String d(Context context) {
        String string = context.getSharedPreferences("thirdDeviceCode", 0).getString("KEY_UUID", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strReplaceAll = UUID.randomUUID().toString().replaceAll(MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING, "");
        context.getSharedPreferences("thirdDeviceCode", 0).edit().putString("KEY_UUID", strReplaceAll).apply();
        return strReplaceAll;
    }

    public static String c(Context context) {
        return context.getSharedPreferences("thirdDeviceCode", 0).getString("his_deviceid", "");
    }

    public static void c(Context context, String str) {
        context.getSharedPreferences("thirdDeviceCode", 0).edit().putString("his_deviceid", str).apply();
    }
}
