package com.mediatek.twoworlds.FactoryService;

import android.util.Log;
import com.mediatek.twoworlds.tv.TVNativeWrapper;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MtkTvFactoryServiceBase {
    static {
        new HashMap();
    }

    public int factoryWriteKey(String str, String str2) {
        Log.d("MtkTvFactoryService", "Enter factoryWriteKey(keyType" + str + ", srcPath=" + str2 + "\n");
        return TVNativeWrapper.factoryWriteKey_native(str, str2);
    }

    public int factoryWriteKeyFinish() {
        Log.d("MtkTvFactoryService", "Enter factoryWriteKeyFinish\n");
        return TVNativeWrapper.factoryWriteKeyFinish_native();
    }
}
