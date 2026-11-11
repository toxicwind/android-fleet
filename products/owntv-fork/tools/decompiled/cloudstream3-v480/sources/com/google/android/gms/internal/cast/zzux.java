package com.google.android.gms.internal.cast;

import com.google.common.util.concurrent.ListenableFuture;
import j$.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zzux<V> extends zzuy<V> {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    final class zza {
        static final zza zza;
        static final zza zzb;
        final boolean zzc;
        final Throwable zzd;

        static {
            if (zzuy.zzc) {
                zzb = null;
                zza = null;
            } else {
                zzb = new zza(false, null);
                zza = new zza(true, null);
            }
        }

        zza(boolean z, Throwable th) {
            this.zzc = z;
            this.zzd = th;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    final class zzb<V> implements Runnable {
        final zzux<V> zza;
        final ListenableFuture<? extends V> zzb;

        @Override // java.lang.Runnable
        public final void run() {
            throw null;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    final class zzc {
        static final zzc zza = new zzc(new Throwable("Failure occurred while trying to finish a future.") { // from class: com.google.android.gms.internal.cast.zzux.zzc.1
            {
                super("Failure occurred while trying to finish a future.");
            }

            @Override // java.lang.Throwable
            public final Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable zzb;

        zzc(Throwable th) {
            th.getClass();
            this.zzb = th;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    final class zzd {
        static final zzd zza = new zzd();
        zzd next;
        final Runnable zzb;
        final Executor zzc;

        zzd() {
            this.zzb = null;
            this.zzc = null;
        }

        zzd(Runnable runnable, Executor executor) {
            this.zzb = runnable;
            this.zzc = executor;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    interface zze<V> extends ListenableFuture<V> {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    abstract class zzf<V> extends zzux<V> implements zze<V> {
        zzf() {
        }
    }

    protected zzux() {
    }

    static Object zza(Object obj) throws ExecutionException {
        if (obj instanceof zza) {
            Throwable th = ((zza) obj).zzd;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof zzc) {
            throw new ExecutionException(((zzc) obj).zzb);
        }
        if (obj == zza) {
            return null;
        }
        return obj;
    }

    static boolean zze(Object obj) {
        return !(obj instanceof zzb);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Object zzo(ListenableFuture listenableFuture) {
        Throwable thZzc;
        if (listenableFuture instanceof zze) {
            Object zzaVar = ((zzux) listenableFuture).valueField;
            if (zzaVar instanceof zza) {
                zza zzaVar2 = (zza) zzaVar;
                if (zzaVar2.zzc) {
                    Throwable th = zzaVar2.zzd;
                    zzaVar = th != null ? new zza(false, th) : zza.zzb;
                }
            }
            return Objects.requireNonNull(zzaVar);
        }
        if ((listenableFuture instanceof zzvy) && (thZzc = ((zzvy) listenableFuture).zzc()) != null) {
            return new zzc(thZzc);
        }
        boolean zIsCancelled = listenableFuture.isCancelled();
        if ((!zzc) && zIsCancelled) {
            return Objects.requireNonNull(zza.zzb);
        }
        try {
            Object objZzp = zzp(listenableFuture);
            if (!zIsCancelled) {
                return objZzp == null ? zza : objZzp;
            }
            return new zza(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + String.valueOf(listenableFuture)));
        } catch (Error | Exception e) {
            return new zzc(e);
        } catch (CancellationException e2) {
            return !zIsCancelled ? new zzc(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(String.valueOf(listenableFuture))), e2)) : new zza(false, e2);
        } catch (ExecutionException e3) {
            return zIsCancelled ? new zza(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(String.valueOf(listenableFuture))), e3)) : new zzc(e3.getCause());
        }
    }

    private static Object zzp(Future future) throws ExecutionException {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    private final void zzq(StringBuilder sb) {
        try {
            Object objZzp = zzp(this);
            sb.append("SUCCESS, result=[");
            if (objZzp == null) {
                sb.append(AbstractJsonLexerKt.NULL);
            } else if (objZzp == this) {
                sb.append("this future");
            } else {
                sb.append(objZzp.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(objZzp)));
            }
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (ExecutionException e) {
            sb.append("FAILURE, cause=[");
            sb.append(e.getCause());
            sb.append("]");
        } catch (Exception e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        }
    }

    private static void zzr(zzux zzuxVar, boolean z) {
        zzd zzdVar = null;
        while (true) {
            zzuxVar.zzl();
            zzuxVar.zzd();
            zzd zzdVar2 = zzdVar;
            zzd zzdVarZzh = zzuxVar.zzh(zzd.zza);
            zzd zzdVar3 = zzdVar2;
            while (zzdVarZzh != null) {
                zzd zzdVar4 = zzdVarZzh.next;
                zzdVarZzh.next = zzdVar3;
                zzdVar3 = zzdVarZzh;
                zzdVarZzh = zzdVar4;
            }
            while (zzdVar3 != null) {
                Runnable runnable = zzdVar3.zzb;
                zzd zzdVar5 = zzdVar3.next;
                Runnable runnable2 = (Runnable) Objects.requireNonNull(runnable);
                if (runnable2 instanceof zzb) {
                    zzb zzbVar = (zzb) runnable2;
                    zzuxVar = zzbVar.zza;
                    if (zzuxVar.valueField == zzbVar && zzn(zzuxVar, zzbVar, zzo(zzbVar.zzb))) {
                        zzdVar = zzdVar5;
                    }
                } else {
                    zzs(runnable2, (Executor) Objects.requireNonNull(zzdVar3.zzc));
                }
                zzdVar3 = zzdVar5;
            }
            return;
        }
    }

    private static void zzs(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            zzb.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFuture", "executeListener", "RuntimeException while executing runnable " + String.valueOf(runnable) + " with executor " + String.valueOf(executor), (Throwable) e);
        }
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        zzd zzdVar;
        zzgo.zzc(runnable, "Runnable was null.");
        zzgo.zzc(executor, "Executor was null.");
        if (!isDone() && (zzdVar = this.listenersField) != zzd.zza) {
            zzd zzdVar2 = new zzd(runnable, executor);
            do {
                zzdVar2.next = zzdVar;
                if (zzm(zzdVar, zzdVar2)) {
                    return;
                } else {
                    zzdVar = this.listenersField;
                }
            } while (zzdVar != zzd.zza);
        }
        zzs(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        Object objRequireNonNull;
        Object obj = this.valueField;
        if (!(obj instanceof zzb) && !(obj == null)) {
            return false;
        }
        if (zzc) {
            objRequireNonNull = new zza(z, new CancellationException("Future.cancel() was called."));
        } else {
            objRequireNonNull = Objects.requireNonNull(z ? zza.zza : zza.zzb);
        }
        boolean z2 = false;
        while (true) {
            if (zzn(this, obj, objRequireNonNull)) {
                zzr(this, z);
                if (obj instanceof zzb) {
                    ListenableFuture<? extends V> listenableFuture = ((zzb) obj).zzb;
                    if (listenableFuture instanceof zze) {
                        this = (zzux) listenableFuture;
                        obj = this.valueField;
                        if (!(obj == null) && !(obj instanceof zzb)) {
                            return true;
                        }
                        z2 = true;
                    } else {
                        listenableFuture.cancel(z);
                    }
                }
                return true;
            }
            obj = this.valueField;
            if (zze(obj)) {
                return z2;
            }
        }
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws ExecutionException, InterruptedException {
        return zzi();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.valueField instanceof zza;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        Object obj = this.valueField;
        return (obj != null) & zze(obj);
    }

    public final String toString() {
        String strConcat;
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (this.valueField instanceof zza) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            zzq(sb);
        } else {
            int length = sb.length();
            sb.append("PENDING");
            Object obj = this.valueField;
            if (obj instanceof zzb) {
                sb.append(", setFuture=[");
                ListenableFuture<? extends V> listenableFuture = ((zzb) obj).zzb;
                try {
                    if (listenableFuture == this) {
                        sb.append("this future");
                    } else {
                        sb.append(listenableFuture);
                    }
                } catch (Throwable th) {
                    zzvv.zza(th);
                    sb.append("Exception thrown from implementation: ");
                    sb.append(th.getClass());
                }
                sb.append("]");
            } else {
                try {
                    strConcat = zzb();
                    if (strConcat == null || strConcat.isEmpty()) {
                        strConcat = null;
                    }
                } catch (Throwable th2) {
                    zzvv.zza(th2);
                    strConcat = "Exception thrown from implementation: ".concat(String.valueOf(String.valueOf(th2.getClass())));
                }
                if (strConcat != null) {
                    sb.append(", info=[");
                    sb.append(strConcat);
                    sb.append("]");
                }
            }
            if (isDone()) {
                sb.delete(length, sb.length());
                zzq(sb);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected String zzb() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    @Override // com.google.android.gms.internal.cast.zzvy
    protected final Throwable zzc() {
        if (!(this instanceof zze)) {
            return null;
        }
        Object obj = this.valueField;
        if (obj instanceof zzc) {
            return ((zzc) obj).zzb;
        }
        return null;
    }

    protected void zzd() {
    }

    protected final boolean zzf(Object obj) {
        if (obj == null) {
            obj = zza;
        }
        if (!zzn(this, null, obj)) {
            return false;
        }
        zzr(this, false);
        return true;
    }

    protected final boolean zzg(Throwable th) {
        if (!zzn(this, null, new zzc(th))) {
            return false;
        }
        zzr(this, false);
        return true;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return zzj(j, timeUnit);
    }
}
