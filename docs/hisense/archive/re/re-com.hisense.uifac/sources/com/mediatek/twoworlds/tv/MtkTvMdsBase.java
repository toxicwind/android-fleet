package com.mediatek.twoworlds.tv;

import android.util.Log;
import com.mediatek.twoworlds.tv.common.MtkTvExceptionBase;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvMdsBase {
    private static final String TAG = "MtkTvMds";

    public MtkTvMdsBase() {
        Log.d(TAG, "MtkTvMdsBase object created");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v3, types: [int] */
    /* JADX WARN: Type inference failed for: r6v7 */
    public int fvpMtdtOperator(String str) throws Throwable {
        ?? r6;
        Log.d(TAG, "fvpMtdtOperator , s_msg_data:" + str);
        String str2 = null;
        boolean z = str != null && str.contains("open");
        try {
            try {
                synchronized (this) {
                    try {
                        Log.d(TAG, "fvpMtdtOperator_native begin");
                        int iFvpMtdtOperator_native = TVNativeWrapper.fvpMtdtOperator_native(str);
                        if (iFvpMtdtOperator_native != 0 && (iFvpMtdtOperator_native == 0 || !z)) {
                            throw new MtkTvExceptionBase(iFvpMtdtOperator_native, "TVNativeWrapper fvpMtdtOperator fail");
                        }
                        r6 = iFvpMtdtOperator_native;
                        Log.d(TAG, "- fvpMtdtOperator");
                        return r6;
                    } catch (Throwable th) {
                        th = th;
                        str = null;
                        try {
                            throw th;
                        } catch (MtkTvExceptionBase e) {
                            e = e;
                            str2 = str;
                            e.printStackTrace();
                            r6 = str2;
                            Log.d(TAG, "- fvpMtdtOperator");
                            return r6;
                        }
                    }
                }
            } catch (MtkTvExceptionBase e2) {
                e = e2;
                e.printStackTrace();
                r6 = str2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
