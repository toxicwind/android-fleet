package com.hmct.cloud.sdk.bean.account;

import com.hmct.cloud.sdk.bean.global.ReplyInfo;

/* JADX INFO: loaded from: classes2.dex */
public class SignonReplyInfo extends ReplyInfo {
    public static final long serialVersionUID = -6341669852010467246L;
    public int customerId;
    public String loginName;
    public String refreshToken;
    public int refreshTokenExpiredTime;
    public int subscriberId;
    public String token;
    public long tokenCreateTime;
    public int tokenExpireTime;

    public int getCustomerId() {
        return this.customerId;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public int getRefreshTokenExpiredTime() {
        return this.refreshTokenExpiredTime;
    }

    public int getSubscriberId() {
        return this.subscriberId;
    }

    public String getToken() {
        return this.token;
    }

    public long getTokenCreateTime() {
        return this.tokenCreateTime;
    }

    public int getTokenExpireTime() {
        return this.tokenExpireTime;
    }

    public void setCustomerId(int i) {
        this.customerId = i;
    }

    public void setLoginName(String str) {
        this.loginName = str;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }

    public void setRefreshTokenExpiredTime(int i) {
        this.refreshTokenExpiredTime = i;
    }

    public void setSubscriberId(int i) {
        this.subscriberId = i;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setTokenCreateTime(long j) {
        this.tokenCreateTime = j;
    }

    public void setTokenExpireTime(int i) {
        this.tokenExpireTime = i;
    }
}
