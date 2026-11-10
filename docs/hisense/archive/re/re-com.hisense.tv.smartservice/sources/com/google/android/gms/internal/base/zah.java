package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class zah extends Drawable.ConstantState {
    public int mChangingConfigurations;
    public int zaoa;

    public zah(zah zahVar) {
        if (zahVar != null) {
            this.mChangingConfigurations = zahVar.mChangingConfigurations;
            this.zaoa = zahVar.zaoa;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new zae(this);
    }
}
