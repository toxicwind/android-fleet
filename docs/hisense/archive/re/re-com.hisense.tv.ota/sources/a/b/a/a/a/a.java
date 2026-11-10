package a.b.a.a.a;

import android.content.Context;
import android.text.TextUtils;
import com.hmct.cloud.sdk.bean.account.SignonReplyInfo;
import com.hmct.cloud.sdk.bean.global.ErrorInfo;
import java.io.StringReader;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(Context context) {
        String string = context.getSharedPreferences("thirdDeviceCode", 0).getString("KEY_UUID", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strReplaceAll = UUID.randomUUID().toString().replaceAll("-", "");
        context.getSharedPreferences("thirdDeviceCode", 0).edit().putString("KEY_UUID", strReplaceAll).apply();
        return strReplaceAll;
    }

    public static SignonReplyInfo b(String str) {
        if (TextUtils.isEmpty(str)) {
            SignonReplyInfo signonReplyInfo = new SignonReplyInfo();
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.c("000001");
            errorInfo.d("xmlString is null");
            signonReplyInfo.d(1);
            signonReplyInfo.c(errorInfo);
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
                if (eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (name.equalsIgnoreCase("response")) {
                        signonReplyInfo2 = new SignonReplyInfo();
                    } else if (name.equalsIgnoreCase("resultcode")) {
                        String strNextText = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText)) {
                            signonReplyInfo2.d(b.a(strNextText));
                            if (signonReplyInfo2.b() != 0) {
                                ErrorInfo errorInfo3 = new ErrorInfo();
                                signonReplyInfo2.c(errorInfo3);
                                errorInfo2 = errorInfo3;
                            }
                        }
                    } else if (name.equalsIgnoreCase("errorcode")) {
                        errorInfo2.c(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("errordesc")) {
                        errorInfo2.d(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("loginName")) {
                        signonReplyInfo2.h(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("subscriberid")) {
                        signonReplyInfo2.k(b.a(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("customerId")) {
                        signonReplyInfo2.g(b.a(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("token")) {
                        signonReplyInfo2.l(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("tokencreatetime")) {
                        signonReplyInfo2.m(b.b(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("tokenexpiredtime")) {
                        signonReplyInfo2.n(b.a(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("refreshToken")) {
                        signonReplyInfo2.i(xmlPullParserNewPullParser.nextText().trim());
                    } else if (name.equalsIgnoreCase("refreshTokenExpiredTime")) {
                        signonReplyInfo2.j(b.a(xmlPullParserNewPullParser.nextText()));
                    }
                }
            }
            return signonReplyInfo2;
        } catch (Exception e) {
            e.printStackTrace();
            SignonReplyInfo signonReplyInfo3 = new SignonReplyInfo();
            ErrorInfo errorInfo4 = new ErrorInfo();
            errorInfo4.c("000001");
            errorInfo4.d(str);
            signonReplyInfo3.d(1);
            signonReplyInfo3.c(errorInfo4);
            return signonReplyInfo3;
        }
    }
}
