package com.google.android.gms.flags;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.flags.ModuleDescriptor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-flags@@17.0.1 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzb {
    private boolean zza = false;
    private zze zzb = null;

    public final <T> T zza(Flag<T> flag) {
        synchronized (this) {
            if (this.zza) {
                return flag.zza(this.zzb);
            }
            return flag.zzc();
        }
    }

    public final void zzb(Context context) {
        synchronized (this) {
            if (this.zza) {
                return;
            }
            try {
                zze zzeVarAsInterface = zzd.asInterface(DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.flags.impl.FlagProviderImpl"));
                this.zzb = zzeVarAsInterface;
                zzeVarAsInterface.init(ObjectWrapper.wrap(context));
                this.zza = true;
            } catch (RemoteException | DynamiteModule.LoadingException e) {
                Log.w("FlagValueProvider", "Failed to initialize flags module.", e);
            }
        }
    }
}
