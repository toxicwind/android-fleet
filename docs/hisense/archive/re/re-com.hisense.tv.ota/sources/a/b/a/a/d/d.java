package a.b.a.a.d;

import android.text.TextUtils;
import com.hmct.cloud.sdk.bean.global.ErrorInfo;
import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.bean.upgrade.DevUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.FileInfo;
import java.io.StringReader;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static DevUpgradeReply a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i = 0;
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            DevUpgradeReply devUpgradeReply = null;
            ArrayList arrayList = null;
            FileInfo fileInfo = null;
            ErrorInfo errorInfo = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (name.equalsIgnoreCase("response")) {
                        devUpgradeReply = new DevUpgradeReply();
                    } else if (name.equalsIgnoreCase("resultCode")) {
                        String strNextText = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText)) {
                            devUpgradeReply.d(Integer.parseInt(strNextText));
                        }
                        if (devUpgradeReply.b() != 0) {
                            errorInfo = new ErrorInfo();
                            devUpgradeReply.c(errorInfo);
                        }
                    } else if (name.equalsIgnoreCase("errorCode")) {
                        errorInfo.c(c(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("errordesc")) {
                        errorInfo.d(c(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("latestVersion")) {
                        devUpgradeReply.q(c(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("updateFlag")) {
                        String strNextText2 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText2)) {
                            devUpgradeReply.u(Integer.parseInt(strNextText2));
                        }
                    } else if (name.equalsIgnoreCase("updateType")) {
                        String strNextText3 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText3)) {
                            devUpgradeReply.v(Integer.parseInt(strNextText3));
                        }
                    } else if (name.equalsIgnoreCase("ruleId")) {
                        String strNextText4 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText4)) {
                            devUpgradeReply.t(Integer.parseInt(strNextText4));
                        }
                    } else if (name.equalsIgnoreCase("versionDesc")) {
                        devUpgradeReply.w(c(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("areaId")) {
                        devUpgradeReply.k(xmlPullParserNewPullParser.nextText());
                    } else if (name.equalsIgnoreCase("areaName")) {
                        devUpgradeReply.l(xmlPullParserNewPullParser.nextText());
                    } else if (name.equalsIgnoreCase("releaseTime")) {
                        devUpgradeReply.s(xmlPullParserNewPullParser.nextText());
                    } else if (name.equalsIgnoreCase("eulaUrl")) {
                        devUpgradeReply.o(xmlPullParserNewPullParser.nextText());
                    } else if (name.equalsIgnoreCase("eulaChkSum")) {
                        devUpgradeReply.m(xmlPullParserNewPullParser.nextText());
                    } else if (name.equalsIgnoreCase("eulaSHA256Sum")) {
                        devUpgradeReply.n(xmlPullParserNewPullParser.nextText());
                    } else if (name.equalsIgnoreCase("packageType")) {
                        String strNextText5 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText5)) {
                            devUpgradeReply.r(Integer.parseInt(strNextText5));
                        }
                    } else if (name.equalsIgnoreCase("fileNum")) {
                        String strNextText6 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText6)) {
                            i = Integer.parseInt(strNextText6);
                        }
                    } else if (name.equalsIgnoreCase("fileList")) {
                        arrayList = new ArrayList(i);
                    } else if (name.equalsIgnoreCase("fileInfo")) {
                        fileInfo = new FileInfo();
                    } else if (name.equalsIgnoreCase("fileName")) {
                        fileInfo.f(xmlPullParserNewPullParser.nextText());
                    } else if (name.equalsIgnoreCase("fileVersion")) {
                        fileInfo.i(c(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("fileType")) {
                        String strNextText7 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText7)) {
                            fileInfo.h(Integer.parseInt(strNextText7));
                        }
                    } else if (name.equalsIgnoreCase("downloadUrl")) {
                        fileInfo.e(c(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("checkSum")) {
                        fileInfo.d(c(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("fileSize")) {
                        String strNextText8 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText8)) {
                            fileInfo.g(Long.parseLong(strNextText8));
                        }
                    } else if (name.equalsIgnoreCase("sha256Sum")) {
                        fileInfo.j(c(xmlPullParserNewPullParser.nextText()));
                    }
                } else if (eventType != 3) {
                    continue;
                } else {
                    String name2 = xmlPullParserNewPullParser.getName();
                    if (name2.equalsIgnoreCase("fileInfo")) {
                        arrayList.add(fileInfo);
                    } else if (name2.equalsIgnoreCase("fileList")) {
                        devUpgradeReply.p(arrayList);
                    }
                }
            }
            return devUpgradeReply;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ReplyInfo b(String str) {
        ReplyInfo replyInfo = null;
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
                if (eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (name.equalsIgnoreCase("response")) {
                        replyInfo = new ReplyInfo();
                    } else if (name.equalsIgnoreCase("resultCode")) {
                        String strNextText = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText)) {
                            replyInfo.d(Integer.parseInt(strNextText));
                            if (replyInfo.b() != 0) {
                                ErrorInfo errorInfo2 = new ErrorInfo();
                                replyInfo.c(errorInfo2);
                                errorInfo = errorInfo2;
                            }
                        }
                    } else if (name.equalsIgnoreCase("errorCode")) {
                        errorInfo.c(c(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("errordesc")) {
                        errorInfo.d(c(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("desc")) {
                        replyInfo.e(c(xmlPullParserNewPullParser.nextText()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return replyInfo;
    }

    private static String c(String str) {
        return str == null ? "" : str.trim();
    }
}
