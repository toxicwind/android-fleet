package com.hmct.cloud.sdk.bean.upgrade;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneDevUpgradeReply extends ReplyInfo {
    public static final long serialVersionUID = -3652550744579013468L;
    public int updateFlag;
    public int upgradeGenre;
    public List<UpgradePolicyInfo> upgradePolicyList;

    public int getUpdateFlag() {
        return this.updateFlag;
    }

    public int getUpgradeGenre() {
        return this.upgradeGenre;
    }

    public List<UpgradePolicyInfo> getUpgradePolicyList() {
        return this.upgradePolicyList;
    }

    public void setUpdateFlag(int i) {
        this.updateFlag = i;
    }

    public void setUpgradeGenre(int i) {
        this.upgradeGenre = i;
    }

    public void setUpgradePolicyList(List<UpgradePolicyInfo> list) {
        this.upgradePolicyList = list;
    }
}
