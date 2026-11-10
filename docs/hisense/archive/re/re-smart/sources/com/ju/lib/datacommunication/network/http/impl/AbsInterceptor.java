package com.ju.lib.datacommunication.network.http.impl;

import com.ju.lib.datacommunication.annotation.JuMethodAnnotation;
import java.util.regex.Pattern;
import okhttp3.Interceptor;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbsInterceptor implements Interceptor {
    public static boolean checkIP(String str) {
        return Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$").matcher(str).matches();
    }

    @JuMethodAnnotation(description = "获取拦截器优先级", parameters = "", returnDes = "InterceptorPriority")
    public abstract InterceptorPriority priority();
}
