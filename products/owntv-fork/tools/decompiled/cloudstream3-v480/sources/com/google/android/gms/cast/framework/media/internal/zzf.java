package com.google.android.gms.cast.framework.media.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzf extends com.google.android.gms.internal.cast.zza implements zzh {
    zzf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.media.internal.IFetchBitmapTask");
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zzh
    public final Bitmap zze(Uri uri) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, uri);
        Parcel parcelZzb = zzb(1, parcelZza);
        Bitmap bitmap = (Bitmap) com.google.android.gms.internal.cast.zzc.zza(parcelZzb, Bitmap.CREATOR);
        parcelZzb.recycle();
        return bitmap;
    }
}
