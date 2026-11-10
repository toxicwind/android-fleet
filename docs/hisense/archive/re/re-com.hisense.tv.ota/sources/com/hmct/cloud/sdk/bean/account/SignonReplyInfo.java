package com.hmct.cloud.sdk.bean.account;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;

/* JADX INFO: loaded from: classes.dex */
public class SignonReplyInfo extends ReplyInfo {
    private static final long serialVersionUID = -6341669852010467246L;
    private int customerId;
    private String loginName;
    private String refreshToken;
    private int refreshTokenExpiredTime;
    private int subscriberId;
    private String token;
    private long tokenCreateTime;
    private int tokenExpireTime;

    public String f() {
        return this.token;
    }

    public void g(int i) {
        this.customerId = i;
    }

    public void h(String str) {
        this.loginName = str;
    }

    public void i(String str) {
        this.refreshToken = str;
    }

    public void j(int i) {
        this.refreshTokenExpiredTime = i;
    }

    public void k(int i) {
        this.subscriberId = i;
    }

    public void l(String str) {
        this.token = str;
    }

    public void m(long j) {
        this.tokenCreateTime = j;
    }

    public void n(int i) {
        this.tokenExpireTime = i;
    }
}
