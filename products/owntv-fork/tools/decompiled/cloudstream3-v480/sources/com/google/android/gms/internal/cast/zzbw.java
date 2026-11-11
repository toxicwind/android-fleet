package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzbw {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = new Logger("AnalyticsConsent");
    private final long zzc;
    private final Handler zzd;
    private final zzfd zze;

    public zzbw(Context context, long j) {
        Api api = zzfj.zza;
        this.zze = new zzfd(context, new zzfi());
        this.zzc = j;
        this.zzd = new zzet(Looper.getMainLooper());
    }

    static /* synthetic */ void zzb(TaskCompletionSource taskCompletionSource, Exception exc) {
        zzb.d(exc, "get checkbox consent failed", new Object[0]);
        taskCompletionSource.trySetResult(false);
    }

    static /* synthetic */ void zzc(TaskCompletionSource taskCompletionSource) {
        zzb.d("get checkbox consent timed out", new Object[0]);
        taskCompletionSource.trySetResult(false);
    }

    public final synchronized Task zza() {
        final TaskCompletionSource taskCompletionSource;
        taskCompletionSource = new TaskCompletionSource();
        TaskApiCall.Builder builder = TaskApiCall.builder();
        final zzfd zzfdVar = this.zze;
        zzfdVar.doRead(builder.run(new RemoteCall() { // from class: com.google.android.gms.internal.cast.zzfb
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) throws RemoteException {
                ((zzfp) ((zzfv) obj).getService()).zze(new zzfc(zzfdVar, (TaskCompletionSource) obj2));
            }
        }).setMethodKey(4501).build()).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.gms.internal.cast.zzbt
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                zzfe zzfeVar = (zzfe) obj;
                int i = zzbw.zza;
                boolean z = false;
                if (zzfeVar != null && zzfeVar.zza()) {
                    z = true;
                }
                taskCompletionSource.trySetResult(Boolean.valueOf(z));
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.google.android.gms.internal.cast.zzbu
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                zzbw.zzb(taskCompletionSource, exc);
            }
        });
        this.zzd.postDelayed(new Runnable() { // from class: com.google.android.gms.internal.cast.zzbv
            @Override // java.lang.Runnable
            public final void run() {
                zzbw.zzc(taskCompletionSource);
            }
        }, this.zzc * 1000);
        return taskCompletionSource.getTask();
    }
}
