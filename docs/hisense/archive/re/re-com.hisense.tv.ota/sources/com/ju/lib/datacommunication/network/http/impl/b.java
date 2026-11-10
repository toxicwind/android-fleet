package com.ju.lib.datacommunication.network.http.impl;

import java.util.regex.Pattern;
import okhttp3.O;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements O {
    protected static boolean b(String str) {
        return Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$").matcher(str).matches();
    }

    protected abstract InterceptorPriority c();
}
