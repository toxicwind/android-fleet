package com.google.android.gms.internal.cast;

import android.content.Context;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionProvider;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzai extends SessionProvider {
    private final CastOptions zza;
    private final zzbf zzb;

    public zzai(Context context, CastOptions castOptions, zzbf zzbfVar) {
        super(context, castOptions.getSupportedNamespaces().isEmpty() ? CastMediaControlIntent.categoryForCast(castOptions.getReceiverApplicationId()) : CastMediaControlIntent.categoryForCast(castOptions.getReceiverApplicationId(), castOptions.getSupportedNamespaces()));
        this.zza = castOptions;
        this.zzb = zzbfVar;
    }

    @Override // com.google.android.gms.cast.framework.SessionProvider
    public final Session createSession(String str) {
        Context context = getContext();
        String category = getCategory();
        Context context2 = getContext();
        CastOptions castOptions = this.zza;
        zzbf zzbfVar = this.zzb;
        return new CastSession(context, category, str, castOptions, zzbfVar, new com.google.android.gms.cast.framework.media.internal.zzu(context2, castOptions, zzbfVar));
    }

    @Override // com.google.android.gms.cast.framework.SessionProvider
    public final boolean isSessionRecoverable() {
        return this.zza.getResumeSavedSession();
    }
}
