package com.hmct.cloud.sdk.service.a;

import android.text.TextUtils;
import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.bean.upgrade.AppDiffUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.AppUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.CheckAppUpgradeListReply;
import com.hmct.cloud.sdk.bean.upgrade.CheckSilentSppUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.DevUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.PhoneDevUpgradeReply;
import com.hmct.cloud.sdk.service.HiCloudService;
import com.hmct.cloud.sdk.service.UpgradeService;
import com.hmct.cloud.sdk.utils.Constants;
import com.hmct.cloud.sdk.utils.Params;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import com.ju.lib.datacommunication.network.http.core.signature.HiCloudKey;
import com.ju.lib.datacommunication.network.http.core.signature.XmlSignature;
import com.ju.lib.datacommunication.network.http.utils.HttpLog;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class t extends UpgradeService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile UpgradeService f2054b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f2055c = "networkError";

    public t(IHttpApi iHttpApi) {
        super(iHttpApi);
    }

    public static UpgradeService a(IHttpApi iHttpApi) {
        if (f2054b == null) {
            synchronized (t.class) {
                if (f2054b == null) {
                    f2054b = new t(iHttpApi);
                }
            }
        }
        return f2054b;
    }

    private String b(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UpgradeService", "domainName is null");
            return null;
        }
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
        map2.put(Params.INVOKESOURCE, "1");
        a(map2);
        HttpLog.d("UpgradeService", "postparams is " + map2);
        return b(((UpgradeService) this).f1996a, HiCloudService.a(str, str2, z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), new XmlSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", f2055c);
    }

    @Override // com.hmct.cloud.sdk.service.UpgradeService
    public AppDiffUpgradeReply checkAppDiffVersion(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.utils.b.d(a(str, z, "upgrade/check_app_diff_version", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.UpgradeService
    public CheckAppUpgradeListReply checkAppUpgradeList(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.utils.b.g(a(str, z, "upgrade/check_app_upgrade_list", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.UpgradeService
    public AppUpgradeReply checkAppVersion(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.utils.b.b(a(str, z, "upgrade/check_app_version", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.UpgradeService
    public CheckSilentSppUpgradeReply checkSilentSppUpgrade(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.utils.b.f(a(str, z, "upgrade/check_silent_app_upgrade", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.UpgradeService
    public DevUpgradeReply checkVersion(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.utils.b.a(b(str, z, "upgrade/check_version", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.UpgradeService
    public PhoneDevUpgradeReply detectVersion(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.utils.b.c(b(str, z, "upgrade/detect_version", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.UpgradeService
    public ReplyInfo reportPhoneUpgradeEvent(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.utils.b.e(b(str, z, "upgrade/report_phone_upgrade_event", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.UpgradeService
    public ReplyInfo reportSilentAppUpgradeEvent(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.utils.b.e(b(str, z, "upgrade/report_silent_app_upgrade_event", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.hmct.cloud.sdk.service.UpgradeService
    public ReplyInfo reportUpgradeEvent(String str, boolean z, HashMap<String, String> map) {
        try {
            return com.hmct.cloud.sdk.utils.b.e(b(str, z, "upgrade/report_upgrade_event", map));
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private String a(String str, boolean z, String str2, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            HttpLog.e("UpgradeService", "domainName is null");
            return null;
        }
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
        map2.put(Params.INVOKESOURCE, "1");
        a(map2);
        return a(((UpgradeService) this).f1996a, HiCloudService.a(str, str2, z), HiCloudKey.encyptMapRSA(map2, HiCloudKey.getPublicKey(), "sign"), new XmlSignature(), "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><resultCode>1</resultCode><errorCode>000000</errorCode><errorDesc>networkError</errorDesc><error_code>000000</error_code><error_name>networkError</error_name></response>", f2055c);
    }
}
