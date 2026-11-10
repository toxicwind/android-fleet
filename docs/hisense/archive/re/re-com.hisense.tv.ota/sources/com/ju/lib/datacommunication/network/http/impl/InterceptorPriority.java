package com.ju.lib.datacommunication.network.http.impl;

/* JADX INFO: loaded from: classes.dex */
public enum InterceptorPriority {
    FIRST,
    DYNAMIC_DOMAIN_SELECT,
    HTTPS_UPGRADE,
    THIRD_PARTY_DOMAIN_ADAPT,
    HTTPS_DEGRADE,
    AFTER_DOMAIN_CHANGED,
    NORMAL,
    BEFORE_SIGN,
    SIGNATURE,
    LAST
}
