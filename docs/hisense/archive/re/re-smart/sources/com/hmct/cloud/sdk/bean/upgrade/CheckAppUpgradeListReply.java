package com.hmct.cloud.sdk.bean.upgrade;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CheckAppUpgradeListReply extends ReplyInfo {
    public static final long serialVersionUID = 8013844044434912075L;
    public List<AppUpgradeInfo> appUpgradeList;
    public int checkFrequency;
    public int storageThreshold;

    public List<AppUpgradeInfo> getAppUpgradeList() {
        return this.appUpgradeList;
    }

    public int getCheckFrequency() {
        return this.checkFrequency;
    }

    public int getStorageThreshold() {
        return this.storageThreshold;
    }

    public void setAppUpgradeList(List<AppUpgradeInfo> list) {
        this.appUpgradeList = list;
    }

    public void setCheckFrequency(int i) {
        this.checkFrequency = i;
    }

    public void setStorageThreshold(int i) {
        this.storageThreshold = i;
    }
}
