package org.mozilla.javascript.commonjs.module.provider;

import java.net.URLConnection;

/* JADX INFO: loaded from: classes8.dex */
public interface UrlConnectionExpiryCalculator {
    long calculateExpiry(URLConnection uRLConnection);
}
