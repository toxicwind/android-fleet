package com.hmct.cloud.sdk.utils;

import android.text.TextUtils;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.hmct.cloud.sdk.bean.global.ErrorInfo;
import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.bean.upgrade.AppDiffUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.AppPackageInfo;
import com.hmct.cloud.sdk.bean.upgrade.AppUpgradeInfo;
import com.hmct.cloud.sdk.bean.upgrade.AppUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.CheckAppUpgradeListReply;
import com.hmct.cloud.sdk.bean.upgrade.CheckSilentSppUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.DevUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.FileInfo;
import com.hmct.cloud.sdk.bean.upgrade.PhoneDevUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.UpgradePolicyInfo;
import com.ju.lib.datacommunication.network.http.address.AddressResponse;
import com.ju.lib.datacommunication.network.http.address.ThirdAddressResponse;
import java.io.StringReader;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Cipher f2087a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SecretKeySpec f2088b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public IvParameterSpec f2089c;

    public static AppUpgradeReply b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            AppUpgradeReply appUpgradeReply = null;
            ErrorInfo errorInfo = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                        appUpgradeReply = new AppUpgradeReply();
                    } else if (name.equalsIgnoreCase("errorCode")) {
                        appUpgradeReply.setReply(1);
                        if (errorInfo == null) {
                            errorInfo = new ErrorInfo();
                            appUpgradeReply.setError(errorInfo);
                        }
                        errorInfo.setErrorCode(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase(AddressResponse.sResultCode)) {
                        String strNextText = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText)) {
                            appUpgradeReply.setReply(Integer.parseInt(strNextText));
                        }
                    } else if (name.equalsIgnoreCase("errordesc")) {
                        appUpgradeReply.setReply(1);
                        if (errorInfo == null) {
                            errorInfo = new ErrorInfo();
                            appUpgradeReply.setError(errorInfo);
                        }
                        errorInfo.setErrorName(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase(RemoteConfigConstants.RequestFieldKey.APP_ID)) {
                        String strNextText2 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText2)) {
                            appUpgradeReply.setAppId(Long.parseLong(strNextText2));
                        }
                    } else if (name.equalsIgnoreCase("latestVersionName")) {
                        appUpgradeReply.setLatestVersionName(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("latestVersion")) {
                        appUpgradeReply.setLatestVersion(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("updateFlag")) {
                        String strNextText3 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText3)) {
                            appUpgradeReply.setUpdateFlag(Integer.parseInt(strNextText3));
                        }
                    } else if (name.equalsIgnoreCase("versionDesc")) {
                        appUpgradeReply.setVersionDesc(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("areaId")) {
                        appUpgradeReply.setAreaId(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("areaName")) {
                        appUpgradeReply.setAreaName(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("downloadUrl")) {
                        appUpgradeReply.setDownloadUrl(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("checkSum")) {
                        appUpgradeReply.setCheckSum(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("fileSize")) {
                        String strNextText4 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText4)) {
                            appUpgradeReply.setFileSize(Long.parseLong(strNextText4));
                        }
                    } else if (name.equalsIgnoreCase("logLevel")) {
                        String strNextText5 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText5)) {
                            appUpgradeReply.setLogLevel(Integer.parseInt(strNextText5));
                        }
                    } else if (name.equalsIgnoreCase("ruleId")) {
                        String strNextText6 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText6)) {
                            appUpgradeReply.setRuleId(Long.parseLong(strNextText6));
                        }
                    } else if (name.equalsIgnoreCase("switches")) {
                        String strNextText7 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText7)) {
                            appUpgradeReply.setSwitches(Long.parseLong(strNextText7));
                        }
                    } else if (name.equalsIgnoreCase("remindPolicyInfo")) {
                        String strNextText8 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText8)) {
                            appUpgradeReply.setRemindPolicyInfo(strNextText8);
                        }
                    } else if (name.equalsIgnoreCase("cleanUpType")) {
                        String strNextText9 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText9)) {
                            appUpgradeReply.setCleanUpType(Integer.parseInt(strNextText9));
                        }
                    }
                }
            }
            return appUpgradeReply;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static PhoneDevUpgradeReply c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            PhoneDevUpgradeReply phoneDevUpgradeReply = null;
            ArrayList arrayList = null;
            FileInfo fileInfo = null;
            UpgradePolicyInfo upgradePolicyInfo = null;
            ErrorInfo errorInfo = null;
            ArrayList arrayList2 = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                            phoneDevUpgradeReply = new PhoneDevUpgradeReply();
                        } else if (name.equalsIgnoreCase(AddressResponse.sResultCode)) {
                            String strNextText = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText)) {
                                phoneDevUpgradeReply.setReply(Integer.parseInt(strNextText));
                            }
                            if (phoneDevUpgradeReply.getReply() != 0) {
                                ErrorInfo errorInfo2 = new ErrorInfo();
                                phoneDevUpgradeReply.setError(errorInfo2);
                                errorInfo = errorInfo2;
                            }
                        } else if (name.equalsIgnoreCase("errorCode")) {
                            errorInfo.setErrorCode(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("errordesc")) {
                            errorInfo.setErrorName(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("upgradeGenre")) {
                            String strNextText2 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText2)) {
                                phoneDevUpgradeReply.setUpgradeGenre(Integer.parseInt(strNextText2));
                            }
                        } else if (name.equalsIgnoreCase("updateFlag")) {
                            String strNextText3 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText3)) {
                                phoneDevUpgradeReply.setUpdateFlag(Integer.parseInt(strNextText3));
                            }
                        } else if (name.equalsIgnoreCase("upgradePolicyList")) {
                            arrayList2 = new ArrayList();
                        } else if (name.equalsIgnoreCase("upgradePolicyInfo")) {
                            upgradePolicyInfo = new UpgradePolicyInfo();
                        } else if (name.equalsIgnoreCase("latestVersion")) {
                            upgradePolicyInfo.setLatestVersion(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("upgradePolicyDesc")) {
                            upgradePolicyInfo.setUpgradePolicyDesc(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("updateType")) {
                            String strNextText4 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText4)) {
                                upgradePolicyInfo.setUpdateType(Integer.parseInt(strNextText4));
                            }
                        } else if (name.equalsIgnoreCase("displayVersion")) {
                            String strNextText5 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText5)) {
                                upgradePolicyInfo.setDisplayVersion(strNextText5);
                            }
                        } else if (name.equalsIgnoreCase("checkFrequence")) {
                            String strNextText6 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText6)) {
                                upgradePolicyInfo.setCheckFrequence(Integer.parseInt(strNextText6));
                            }
                        } else if (name.equalsIgnoreCase("newCheckFrequence")) {
                            String strNextText7 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText7)) {
                                upgradePolicyInfo.setNewCheckFrequence(Integer.parseInt(strNextText7));
                            }
                        } else if (name.equalsIgnoreCase("versionDesc")) {
                            upgradePolicyInfo.setVersionDesc(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("releaseTime")) {
                            upgradePolicyInfo.setReleaseTime(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("autoUpgradeStart")) {
                            upgradePolicyInfo.setAutoUpgradeStart(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("autoUpgradeEnd")) {
                            upgradePolicyInfo.setAutoUpgradeEnd(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("wifiDownload")) {
                            String strNextText8 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText8)) {
                                upgradePolicyInfo.setWifiDownload(Integer.parseInt(strNextText8));
                            }
                        } else if (name.equalsIgnoreCase("maxDownloadTimes")) {
                            String strNextText9 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText9)) {
                                upgradePolicyInfo.setMaxDownloadTimes(Integer.parseInt(strNextText9));
                            }
                        } else if (name.equalsIgnoreCase("screenCloseTime")) {
                            String strNextText10 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText10)) {
                                upgradePolicyInfo.setScreenCloseTime(Integer.parseInt(strNextText10));
                            }
                        } else if (name.equalsIgnoreCase("packageType")) {
                            String strNextText11 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText11)) {
                                upgradePolicyInfo.setPackageType(Integer.parseInt(strNextText11));
                            }
                        } else if (name.equalsIgnoreCase("fileList")) {
                            arrayList = new ArrayList();
                        } else if (name.equalsIgnoreCase("fileInfo")) {
                            fileInfo = new FileInfo();
                        } else if (name.equalsIgnoreCase("checkSum")) {
                            fileInfo.setCheckSum(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("downloadUrl")) {
                            fileInfo.setDownloadUrl(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("sha256Sum")) {
                            fileInfo.setSha256Sum(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("fileName")) {
                            fileInfo.setFileName(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("fileSize")) {
                            String strNextText12 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText12)) {
                                fileInfo.setFileSize(Long.parseLong(strNextText12));
                            }
                        } else if (name.equalsIgnoreCase("fakeSize")) {
                            String strNextText13 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText13)) {
                                fileInfo.setFakeSize(Long.parseLong(strNextText13));
                            }
                        } else if (name.equalsIgnoreCase("fileType")) {
                            String strNextText14 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText14)) {
                                fileInfo.setFileType(Integer.parseInt(strNextText14));
                            }
                        }
                    } else if (eventType != 3) {
                        continue;
                    } else {
                        String name2 = xmlPullParserNewPullParser.getName();
                        if (name2.equalsIgnoreCase("fileInfo")) {
                            arrayList.add(fileInfo);
                        } else if (name2.equalsIgnoreCase("fileList")) {
                            upgradePolicyInfo.setFileList(arrayList);
                        } else if (name2.equalsIgnoreCase("upgradePolicyInfo")) {
                            arrayList2.add(upgradePolicyInfo);
                        } else if (name2.equalsIgnoreCase("upgradePolicyList")) {
                            phoneDevUpgradeReply.setUpgradePolicyList(arrayList2);
                        }
                    }
                }
            }
            return phoneDevUpgradeReply;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static AppDiffUpgradeReply d(String str) {
        AppDiffUpgradeReply appDiffUpgradeReply = null;
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
                    if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                        appDiffUpgradeReply = new AppDiffUpgradeReply();
                    } else if (name.equalsIgnoreCase("errorCode")) {
                        appDiffUpgradeReply.setReply(1);
                        if (errorInfo == null) {
                            errorInfo = new ErrorInfo();
                            appDiffUpgradeReply.setError(errorInfo);
                        }
                        errorInfo.setErrorCode(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase(AddressResponse.sResultCode)) {
                        String strNextText = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText)) {
                            appDiffUpgradeReply.setReply(Integer.parseInt(strNextText));
                        }
                    } else if (name.equalsIgnoreCase("errordesc")) {
                        appDiffUpgradeReply.setReply(1);
                        if (errorInfo == null) {
                            errorInfo = new ErrorInfo();
                            appDiffUpgradeReply.setError(errorInfo);
                        }
                        errorInfo.setErrorName(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase(RemoteConfigConstants.RequestFieldKey.APP_ID)) {
                        String strNextText2 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText2)) {
                            appDiffUpgradeReply.setAppId(Long.parseLong(strNextText2));
                        }
                    } else if (name.equalsIgnoreCase("latestVersionName")) {
                        appDiffUpgradeReply.setLatestVersionName(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("latestVersion")) {
                        appDiffUpgradeReply.setLatestVersion(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("updateFlag")) {
                        String strNextText3 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText3)) {
                            appDiffUpgradeReply.setUpdateFlag(Integer.parseInt(strNextText3));
                        }
                    } else if (name.equalsIgnoreCase("versionDesc")) {
                        appDiffUpgradeReply.setVersionDesc(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("areaId")) {
                        appDiffUpgradeReply.setAreaId(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("areaName")) {
                        appDiffUpgradeReply.setAreaName(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("downloadUrl")) {
                        appDiffUpgradeReply.setDownloadUrl(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("checkSum")) {
                        appDiffUpgradeReply.setCheckSum(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("fileSize")) {
                        String strNextText4 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText4)) {
                            appDiffUpgradeReply.setFileSize(Long.parseLong(strNextText4));
                        }
                    } else if (name.equalsIgnoreCase("logLevel")) {
                        String strNextText5 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText5)) {
                            appDiffUpgradeReply.setLogLevel(Integer.parseInt(strNextText5));
                        }
                    } else if (name.equalsIgnoreCase("ruleId")) {
                        String strNextText6 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText6)) {
                            appDiffUpgradeReply.setRuleId(Long.parseLong(strNextText6));
                        }
                    } else if (name.equalsIgnoreCase("switches")) {
                        String strNextText7 = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText7)) {
                            appDiffUpgradeReply.setSwitches(Long.parseLong(strNextText7));
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return appDiffUpgradeReply;
    }

    public static ReplyInfo e(String str) {
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
                if (eventType != 0 && eventType == 2) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                        replyInfo = new ReplyInfo();
                    } else if (name.equalsIgnoreCase(AddressResponse.sResultCode)) {
                        String strNextText = xmlPullParserNewPullParser.nextText();
                        if (!TextUtils.isEmpty(strNextText)) {
                            replyInfo.setReply(Integer.parseInt(strNextText));
                            if (replyInfo.getReply() != 0) {
                                ErrorInfo errorInfo2 = new ErrorInfo();
                                replyInfo.setError(errorInfo2);
                                errorInfo = errorInfo2;
                            }
                        }
                    } else if (name.equalsIgnoreCase("errorCode")) {
                        errorInfo.setErrorCode(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase("errordesc")) {
                        errorInfo.setErrorName(h(xmlPullParserNewPullParser.nextText()));
                    } else if (name.equalsIgnoreCase(ThirdAddressResponse.DESC)) {
                        replyInfo.setReplyDesc(h(xmlPullParserNewPullParser.nextText()));
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return replyInfo;
    }

    public static CheckSilentSppUpgradeReply f(String str) {
        CheckSilentSppUpgradeReply checkSilentSppUpgradeReply = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            ArrayList arrayList = null;
            AppPackageInfo appPackageInfo = null;
            ErrorInfo errorInfo = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                            checkSilentSppUpgradeReply = new CheckSilentSppUpgradeReply();
                        } else if (name.equalsIgnoreCase(AddressResponse.sResultCode)) {
                            String strNextText = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText)) {
                                checkSilentSppUpgradeReply.setReply(Integer.parseInt(strNextText));
                                if (!"0".equals(Integer.valueOf(checkSilentSppUpgradeReply.getReply()))) {
                                    ErrorInfo errorInfo2 = new ErrorInfo();
                                    checkSilentSppUpgradeReply.setError(errorInfo2);
                                    errorInfo = errorInfo2;
                                }
                            }
                        } else if (name.equalsIgnoreCase("errorCode")) {
                            errorInfo.setErrorCode(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("errordesc")) {
                            errorInfo.setErrorName(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("appPackageList")) {
                            arrayList = new ArrayList();
                        } else if (name.equalsIgnoreCase("appPackageInfo")) {
                            appPackageInfo = new AppPackageInfo();
                        } else if (name.equalsIgnoreCase("updateFlag")) {
                            appPackageInfo.setUpdateFlag(Integer.parseInt(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase(RemoteConfigConstants.RequestFieldKey.APP_ID)) {
                            appPackageInfo.setAppId(Long.parseLong(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME)) {
                            appPackageInfo.setPackageName(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("versionName")) {
                            appPackageInfo.setVersionName(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("version")) {
                            appPackageInfo.setVersion(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("versionDesc")) {
                            appPackageInfo.setVersionDesc(xmlPullParserNewPullParser.nextText());
                        }
                    } else if (eventType != 3) {
                        continue;
                    } else if ("appPackageInfo".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                        arrayList.add(appPackageInfo);
                    } else if ("appPackageList".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                        checkSilentSppUpgradeReply.setAppPackageList(arrayList);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return checkSilentSppUpgradeReply;
    }

    public static CheckAppUpgradeListReply g(String str) {
        CheckAppUpgradeListReply checkAppUpgradeListReply = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            ArrayList arrayList = null;
            AppUpgradeInfo appUpgradeInfo = null;
            ErrorInfo errorInfo = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                            checkAppUpgradeListReply = new CheckAppUpgradeListReply();
                        } else if (name.equalsIgnoreCase(AddressResponse.sResultCode)) {
                            String strNextText = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText)) {
                                checkAppUpgradeListReply.setReply(Integer.parseInt(strNextText));
                                if (!"0".equals(Integer.valueOf(checkAppUpgradeListReply.getReply()))) {
                                    ErrorInfo errorInfo2 = new ErrorInfo();
                                    checkAppUpgradeListReply.setError(errorInfo2);
                                    errorInfo = errorInfo2;
                                }
                            }
                        } else if (name.equalsIgnoreCase("errorCode")) {
                            errorInfo.setErrorCode(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("errordesc")) {
                            errorInfo.setErrorName(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("checkFrequency")) {
                            checkAppUpgradeListReply.setCheckFrequency(Integer.parseInt(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("storageThreshold")) {
                            checkAppUpgradeListReply.setStorageThreshold(Integer.parseInt(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("appUpgradeList")) {
                            arrayList = new ArrayList();
                        } else if (name.equalsIgnoreCase("appUpgradeInfo")) {
                            appUpgradeInfo = new AppUpgradeInfo();
                        } else if (name.equalsIgnoreCase(RemoteConfigConstants.RequestFieldKey.APP_ID)) {
                            appUpgradeInfo.setAppId(Long.parseLong(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME)) {
                            appUpgradeInfo.setPackageName(xmlPullParserNewPullParser.nextText());
                        }
                    } else if (eventType != 3) {
                        continue;
                    } else if ("appUpgradeInfo".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                        arrayList.add(appUpgradeInfo);
                    } else if ("appUpgradeList".equalsIgnoreCase(xmlPullParserNewPullParser.getName())) {
                        checkAppUpgradeListReply.setAppUpgradeList(arrayList);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return checkAppUpgradeListReply;
    }

    public static String h(String str) {
        return str == null ? "" : str.trim();
    }

    public final void a(byte[] bArr, byte[] bArr2) {
        this.f2088b = new SecretKeySpec(bArr, "AES");
        this.f2089c = new IvParameterSpec(bArr2);
    }

    public final byte[] a(byte[] bArr) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (this.f2087a == null) {
            this.f2087a = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.f2087a.init(2, this.f2088b, this.f2089c);
        }
        return this.f2087a.doFinal(bArr);
    }

    public static DevUpgradeReply a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
            XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
            xmlPullParserNewPullParser.setInput(new StringReader(str));
            int i = 0;
            DevUpgradeReply devUpgradeReply = null;
            ArrayList arrayList = null;
            FileInfo fileInfo = null;
            ErrorInfo errorInfo = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.next()) {
                if (eventType != 0) {
                    if (eventType == 2) {
                        String name = xmlPullParserNewPullParser.getName();
                        if (name.equalsIgnoreCase(AddressResponse.sResponseTag)) {
                            devUpgradeReply = new DevUpgradeReply();
                        } else if (name.equalsIgnoreCase(AddressResponse.sResultCode)) {
                            String strNextText = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText)) {
                                devUpgradeReply.setReply(Integer.parseInt(strNextText));
                            }
                            if (devUpgradeReply.getReply() != 0) {
                                ErrorInfo errorInfo2 = new ErrorInfo();
                                devUpgradeReply.setError(errorInfo2);
                                errorInfo = errorInfo2;
                            }
                        } else if (name.equalsIgnoreCase("errorCode")) {
                            errorInfo.setErrorCode(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("errordesc")) {
                            errorInfo.setErrorName(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("latestVersion")) {
                            devUpgradeReply.setLatestVersion(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("updateFlag")) {
                            String strNextText2 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText2)) {
                                devUpgradeReply.setUpdateFlag(Integer.parseInt(strNextText2));
                            }
                        } else if (name.equalsIgnoreCase("updateType")) {
                            String strNextText3 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText3)) {
                                devUpgradeReply.setUpdateType(Integer.parseInt(strNextText3));
                            }
                        } else if (name.equalsIgnoreCase("ruleId")) {
                            String strNextText4 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText4)) {
                                devUpgradeReply.setRuleId(Integer.parseInt(strNextText4));
                            }
                        } else if (name.equalsIgnoreCase("versionDesc")) {
                            devUpgradeReply.setVersionDesc(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("areaId")) {
                            devUpgradeReply.setAreaId(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("areaName")) {
                            devUpgradeReply.setAreaName(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("releaseTime")) {
                            devUpgradeReply.setReleaseTime(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("eulaUrl")) {
                            devUpgradeReply.setEulaUrl(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("eulaChkSum")) {
                            devUpgradeReply.setEulaChkSum(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("eulaSHA256Sum")) {
                            devUpgradeReply.setEulaSHA256Sum(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("packageType")) {
                            String strNextText5 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText5)) {
                                devUpgradeReply.setPackageType(Integer.parseInt(strNextText5));
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
                            fileInfo.setFileName(xmlPullParserNewPullParser.nextText());
                        } else if (name.equalsIgnoreCase("fileVersion")) {
                            fileInfo.setFileVersion(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("fileType")) {
                            String strNextText7 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText7)) {
                                fileInfo.setFileType(Integer.parseInt(strNextText7));
                            }
                        } else if (name.equalsIgnoreCase("downloadUrl")) {
                            fileInfo.setDownloadUrl(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("checkSum")) {
                            fileInfo.setCheckSum(h(xmlPullParserNewPullParser.nextText()));
                        } else if (name.equalsIgnoreCase("fileSize")) {
                            String strNextText8 = xmlPullParserNewPullParser.nextText();
                            if (!TextUtils.isEmpty(strNextText8)) {
                                fileInfo.setFileSize(Long.parseLong(strNextText8));
                            }
                        } else if (name.equalsIgnoreCase("sha256Sum")) {
                            fileInfo.setSha256Sum(h(xmlPullParserNewPullParser.nextText()));
                        }
                    } else if (eventType != 3) {
                        continue;
                    } else {
                        String name2 = xmlPullParserNewPullParser.getName();
                        if (name2.equalsIgnoreCase("fileInfo")) {
                            arrayList.add(fileInfo);
                        } else if (name2.equalsIgnoreCase("fileList")) {
                            devUpgradeReply.setFileList(arrayList);
                        }
                    }
                }
            }
            return devUpgradeReply;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
