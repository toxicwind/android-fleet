package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes.dex */
public final class zah extends com.google.android.gms.internal.base.zab implements ISignInButtonCreator {
    public zah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
    }

    @Override // com.google.android.gms.common.internal.ISignInButtonCreator
    public final IObjectWrapper newSignInButton(IObjectWrapper iObjectWrapper, int i, int i2) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zad.zaa(parcelZaa, iObjectWrapper);
        parcelZaa.writeInt(i);
        parcelZaa.writeInt(i2);
        Parcel parcelZaa2 = zaa(1, parcelZaa);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZaa2.readStrongBinder());
        parcelZaa2.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.common.internal.ISignInButtonCreator
    public final IObjectWrapper newSignInButtonFromConfig(IObjectWrapper iObjectWrapper, SignInButtonConfig signInButtonConfig) {
        Parcel parcelZaa = zaa();
        com.google.android.gms.internal.base.zad.zaa(parcelZaa, iObjectWrapper);
        com.google.android.gms.internal.base.zad.zaa(parcelZaa, signInButtonConfig);
        Parcel parcelZaa2 = zaa(2, parcelZaa);
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZaa2.readStrongBinder());
        parcelZaa2.recycle();
        return iObjectWrapperAsInterface;
    }
}
