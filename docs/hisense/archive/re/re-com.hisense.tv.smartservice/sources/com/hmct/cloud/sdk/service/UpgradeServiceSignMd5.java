package com.hmct.cloud.sdk.service;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import com.hmct.cloud.sdk.bean.upgrade.AppDiffUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.AppUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.CheckAppUpgradeListReply;
import com.hmct.cloud.sdk.bean.upgrade.CheckSilentSppUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.DevUpgradeReply;
import com.hmct.cloud.sdk.bean.upgrade.PhoneDevUpgradeReply;
import com.hmct.cloud.sdk.service.a.u;
import com.ju.lib.datacommunication.network.http.IHttpApi;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class UpgradeServiceSignMd5 extends HiCloudService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IHttpApi f1997a;

    public UpgradeServiceSignMd5(IHttpApi iHttpApi) {
        this.f1997a = iHttpApi;
    }

    public static UpgradeServiceSignMd5 getService(IHttpApi iHttpApi) {
        return u.a(iHttpApi);
    }

    public abstract AppDiffUpgradeReply checkAppDiffVersion(String str, boolean z, HashMap<String, String> map);

    public abstract CheckAppUpgradeListReply checkAppUpgradeList(String str, boolean z, HashMap<String, String> map);

    public abstract AppUpgradeReply checkAppVersion(String str, boolean z, HashMap<String, String> map);

    public abstract CheckSilentSppUpgradeReply checkSilentSppUpgrade(String str, boolean z, HashMap<String, String> map);

    public abstract DevUpgradeReply checkVersion(String str, boolean z, HashMap<String, String> map);

    public abstract PhoneDevUpgradeReply detectVersion(String str, boolean z, HashMap<String, String> map);

    public abstract ReplyInfo reportPhoneUpgradeEvent(String str, boolean z, HashMap<String, String> map);

    public abstract ReplyInfo reportSilentAppUpgradeEvent(String str, boolean z, HashMap<String, String> map);

    public abstract ReplyInfo reportUpgradeEvent(String str, boolean z, HashMap<String, String> map);
}
