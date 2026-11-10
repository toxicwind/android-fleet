package com.ju.lib.datacommunication.network.http.dns;

import com.ju.lib.datacommunication.network.http.core.HttpStack;
import com.ju.lib.datacommunication.network.http.impl.HttpStackImpl;

/* JADX INFO: loaded from: classes2.dex */
public class HttpDnsConfig {
    public static final int DEFAULT_COOL_TIME_IN_SECONDS = 180;
    public static final long DEFAULT_DNS_TTL = 300;
    public static final int DEFAULT_NETWORK_CONNECT_TRANSITIONAL_PERIOD_IN_SECONDS = 60;
    public static final double DEFAULT_TIME_OUT_IN_SECONDS = 1.2d;
    public static final int MAX_COOL_TIME = 300;
    public static final int MAX_NETWORK_DISCONNECT_DURATION = 120;
    public static final double MAX_TIME_OUT = 5.0d;
    public static final int MIN_COOL_TIME = 150;
    public static final double MIN_TIME_OUT = 0.5d;
    public static final String UNSUPPORTED_IP = "127.0.0.1";

    public static double checkConnectTimeout(double d2) {
        if (d2 < 0.5d) {
            return 0.5d;
        }
        if (d2 > 5.0d) {
            return 5.0d;
        }
        return d2;
    }

    public static int checkCoolTime(int i) {
        if (i < 150) {
            return 150;
        }
        if (i > 300) {
            return 300;
        }
        return i;
    }

    public static int checkNetworkConnectTransitionalPeriod(int i) {
        if (i > 120) {
            return 120;
        }
        return i;
    }

    public static HttpStackImpl createDnsStack(long j, HttpStackImpl httpStackImpl) {
        HttpStack.HttpStackConfig httpStackConfig = new HttpStack.HttpStackConfig();
        httpStackConfig.setConnectTimeOut(j);
        httpStackConfig.setReadTimeOut(j);
        httpStackConfig.setEnableLog(false);
        httpStackConfig.setEnableRetry(false);
        return new HttpStackImpl(httpStackConfig, httpStackImpl);
    }
}
