package com.android.launcher3.icons;

import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;

/* JADX INFO: loaded from: classes.dex */
public class GraphicsUtils {
    static /* synthetic */ void lambda$static$0() {
    }

    static {
        $$Lambda$GraphicsUtils$W6f4e52z7SPvYCk05ydbedScRFQ __lambda_graphicsutils_w6f4e52z7spvyck05ydbedscrfq = new Runnable() { // from class: com.android.launcher3.icons.-$$Lambda$GraphicsUtils$W6f4e52z7SPvYCk05ydbedScRFQ
            @Override // java.lang.Runnable
            public final void run() {
                GraphicsUtils.lambda$static$0();
            }
        };
    }

    public static int getArea(Region region) {
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        int iWidth = 0;
        while (regionIterator.next(rect)) {
            iWidth += rect.width() * rect.height();
        }
        return iWidth;
    }
}
