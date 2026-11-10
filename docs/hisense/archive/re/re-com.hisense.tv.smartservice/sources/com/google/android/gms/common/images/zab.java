package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zaj;

/* JADX INFO: loaded from: classes.dex */
public abstract class zab {
    public final zaa zamz;
    public int zanb;
    public int zana = 0;
    public boolean zanc = false;
    public boolean zand = true;
    public boolean zane = false;
    public boolean zanf = true;

    public zab(Uri uri, int i) {
        this.zanb = 0;
        this.zamz = new zaa(uri);
        this.zanb = i;
    }

    public final void zaa(Context context, Bitmap bitmap, boolean z) {
        Asserts.checkNotNull(bitmap);
        zaa(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    public abstract void zaa(Drawable drawable, boolean z, boolean z2, boolean z3);

    public final void zaa(Context context, zaj zajVar) {
        if (this.zanf) {
            zaa(null, false, true, false);
        }
    }

    public final void zaa(Context context, zaj zajVar, boolean z) {
        int i = this.zanb;
        zaa(i != 0 ? context.getResources().getDrawable(i) : null, z, false, false);
    }

    public final boolean zaa(boolean z, boolean z2) {
        return (!this.zand || z2 || z) ? false : true;
    }
}
