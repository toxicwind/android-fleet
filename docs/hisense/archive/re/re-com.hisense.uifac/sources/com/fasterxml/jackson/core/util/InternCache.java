package com.fasterxml.jackson.core.util;

import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class InternCache extends ConcurrentHashMap<String, String> {
    public static final InternCache instance = new InternCache();
    private static final long serialVersionUID = 1;
    private final Object lock;

    private InternCache() {
        super(MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_MEDIA_MSGQ_EMPTY, 0.8f, 4);
        this.lock = new Object();
    }

    public String intern(String str) {
        String str2 = get(str);
        if (str2 != null) {
            return str2;
        }
        if (size() >= 180) {
            synchronized (this.lock) {
                if (size() >= 180) {
                    clear();
                }
            }
        }
        String strIntern = str.intern();
        put(strIntern, strIntern);
        return strIntern;
    }
}
