package com.mediatek.twoworlds.tv;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class MtkTvMdsBase {
    public static final String TAG = "MtkTvMds";

    public MtkTvMdsBase() {
        Log.d(TAG, "MtkTvMdsBase object created");
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x003e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int fvpMtdtOperator(java.lang.String r5) throws java.lang.Throwable {
        /*
            r4 = this;
            java.lang.String r0 = "fvpMtdtOperator , s_msg_data:"
            java.lang.String r1 = "MtkTvMds"
            b.a.a.a.a.c(r0, r5, r1)
            r0 = 0
            if (r5 == 0) goto L14
            java.lang.String r1 = "open"
            boolean r1 = r5.contains(r1)
            if (r1 == 0) goto L14
            r1 = 1
            goto L15
        L14:
            r1 = r0
        L15:
            monitor-enter(r4)     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L40
            java.lang.String r2 = "MtkTvMds"
            java.lang.String r3 = "fvpMtdtOperator_native begin"
            android.util.Log.d(r2, r3)     // Catch: java.lang.Throwable -> L35
            int r5 = com.mediatek.twoworlds.tv.TVNativeWrapper.fvpMtdtOperator_native(r5)     // Catch: java.lang.Throwable -> L35
            if (r5 == 0) goto L30
            if (r5 == 0) goto L28
            if (r1 == 0) goto L28
            goto L30
        L28:
            com.mediatek.twoworlds.tv.common.MtkTvExceptionBase r0 = new com.mediatek.twoworlds.tv.common.MtkTvExceptionBase     // Catch: java.lang.Throwable -> L32
            java.lang.String r1 = "TVNativeWrapper fvpMtdtOperator fail"
            r0.<init>(r5, r1)     // Catch: java.lang.Throwable -> L32
            throw r0     // Catch: java.lang.Throwable -> L32
        L30:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L32
            goto L45
        L32:
            r0 = move-exception
            r1 = r5
            goto L38
        L35:
            r5 = move-exception
            r1 = r0
            r0 = r5
        L38:
            r5 = r4
        L39:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L3e
            throw r0     // Catch: com.mediatek.twoworlds.tv.common.MtkTvExceptionBase -> L3b
        L3b:
            r5 = move-exception
            r0 = r1
            goto L41
        L3e:
            r0 = move-exception
            goto L39
        L40:
            r5 = move-exception
        L41:
            r5.printStackTrace()
            r5 = r0
        L45:
            java.lang.String r0 = "MtkTvMds"
            java.lang.String r1 = "- fvpMtdtOperator"
            android.util.Log.d(r0, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mediatek.twoworlds.tv.MtkTvMdsBase.fvpMtdtOperator(java.lang.String):int");
    }
}
