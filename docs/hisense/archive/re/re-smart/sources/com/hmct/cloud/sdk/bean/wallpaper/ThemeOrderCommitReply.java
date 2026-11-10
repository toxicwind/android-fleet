package com.hmct.cloud.sdk.bean.wallpaper;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeOrderCommitReply extends ThemeBaseReply {
    public static final long serialVersionUID = -4893242410459566142L;
    public String goodsDesc;
    public String goodsName;
    public String notifyUrl;
    public String ordersn;
    public String totalFee;

    public String getGoodsDesc() {
        return this.goodsDesc;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    public String getOrdersn() {
        return this.ordersn;
    }

    public String getTotalFee() {
        return this.totalFee;
    }

    public void setGoodsDesc(String str) {
        this.goodsDesc = str;
    }

    public void setGoodsName(String str) {
        this.goodsName = str;
    }

    public void setNotifyUrl(String str) {
        this.notifyUrl = str;
    }

    public void setOrdersn(String str) {
        this.ordersn = str;
    }

    public void setTotalFee(String str) {
        this.totalFee = str;
    }
}
