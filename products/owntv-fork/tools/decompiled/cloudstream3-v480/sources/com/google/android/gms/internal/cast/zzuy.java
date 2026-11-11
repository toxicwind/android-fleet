package com.google.android.gms.internal.cast;

import com.google.common.util.concurrent.ListenableFuture;
import j$.util.Objects;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import org.schabi.newpipe.extractor.stream.Stream;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
abstract class zzuy<V> extends zzvy implements ListenableFuture<V> {
    static final Object zza = new Object();
    static final zzvo zzb = new zzvo(zzux.class);
    static final boolean zzc;
    private static final zza zzd;
    volatile zzux.zzd listenersField;
    volatile Object valueField;
    volatile zze waitersField;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    abstract class zza {
        /* synthetic */ zza(zzvc zzvcVar) {
        }

        abstract zzux.zzd zza(zzuy zzuyVar, zzux.zzd zzdVar);

        abstract zze zzb(zzuy zzuyVar, zze zzeVar);

        abstract void zzc(zze zzeVar, zze zzeVar2);

        abstract void zzd(zze zzeVar, Thread thread);

        abstract boolean zze(zzuy zzuyVar, zzux.zzd zzdVar, zzux.zzd zzdVar2);

        abstract boolean zzf(zzuy zzuyVar, Object obj, Object obj2);

        abstract boolean zzg(zzuy zzuyVar, zze zzeVar, zze zzeVar2);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    final class zzb extends zza {
        private static final AtomicReferenceFieldUpdater<zze, Thread> zza = AtomicReferenceFieldUpdater.newUpdater(zze.class, Thread.class, "thread");
        private static final AtomicReferenceFieldUpdater<zze, zze> zzb = AtomicReferenceFieldUpdater.newUpdater(zze.class, zze.class, "next");
        private static final AtomicReferenceFieldUpdater<? super zzuy<?>, zze> zzc = AtomicReferenceFieldUpdater.newUpdater(zzuy.class, zze.class, "waitersField");
        private static final AtomicReferenceFieldUpdater<? super zzuy<?>, zzux.zzd> zzd = AtomicReferenceFieldUpdater.newUpdater(zzuy.class, zzux.zzd.class, "listenersField");
        private static final AtomicReferenceFieldUpdater<? super zzuy<?>, Object> zze = AtomicReferenceFieldUpdater.newUpdater(zzuy.class, Object.class, "valueField");

        private zzb() {
            throw null;
        }

        /* synthetic */ zzb(zzvc zzvcVar) {
            super(null);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final zzux.zzd zza(zzuy zzuyVar, zzux.zzd zzdVar) {
            return zzd.getAndSet(zzuyVar, zzdVar);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final zze zzb(zzuy zzuyVar, zze zzeVar) {
            return zzc.getAndSet(zzuyVar, zzeVar);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final void zzc(zze zzeVar, zze zzeVar2) {
            zzb.lazySet(zzeVar, zzeVar2);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final void zzd(zze zzeVar, Thread thread) {
            zza.lazySet(zzeVar, thread);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final boolean zze(zzuy zzuyVar, zzux.zzd zzdVar, zzux.zzd zzdVar2) {
            return zzuz.zza(zzd, zzuyVar, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final boolean zzf(zzuy zzuyVar, Object obj, Object obj2) {
            return zzuz.zza(zze, zzuyVar, obj, obj2);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final boolean zzg(zzuy zzuyVar, zze zzeVar, zze zzeVar2) {
            return zzuz.zza(zzc, zzuyVar, zzeVar, zzeVar2);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    final class zzc extends zza {
        private zzc() {
            throw null;
        }

        /* synthetic */ zzc(zzvc zzvcVar) {
            super(null);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final zzux.zzd zza(zzuy zzuyVar, zzux.zzd zzdVar) {
            zzux.zzd zzdVar2;
            synchronized (zzuyVar) {
                zzdVar2 = zzuyVar.listenersField;
                if (zzdVar2 != zzdVar) {
                    zzuyVar.listenersField = zzdVar;
                }
            }
            return zzdVar2;
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final zze zzb(zzuy zzuyVar, zze zzeVar) {
            zze zzeVar2;
            synchronized (zzuyVar) {
                zzeVar2 = zzuyVar.waitersField;
                if (zzeVar2 != zzeVar) {
                    zzuyVar.waitersField = zzeVar;
                }
            }
            return zzeVar2;
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final void zzc(zze zzeVar, zze zzeVar2) {
            zzeVar.next = zzeVar2;
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final void zzd(zze zzeVar, Thread thread) {
            zzeVar.thread = thread;
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final boolean zze(zzuy zzuyVar, zzux.zzd zzdVar, zzux.zzd zzdVar2) {
            synchronized (zzuyVar) {
                if (zzuyVar.listenersField != zzdVar) {
                    return false;
                }
                zzuyVar.listenersField = zzdVar2;
                return true;
            }
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final boolean zzf(zzuy zzuyVar, Object obj, Object obj2) {
            synchronized (zzuyVar) {
                if (zzuyVar.valueField != obj) {
                    return false;
                }
                zzuyVar.valueField = obj2;
                return true;
            }
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final boolean zzg(zzuy zzuyVar, zze zzeVar, zze zzeVar2) {
            synchronized (zzuyVar) {
                if (zzuyVar.waitersField != zzeVar) {
                    return false;
                }
                zzuyVar.waitersField = zzeVar2;
                return true;
            }
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    final class zzd extends zza {
        static final Unsafe zza;
        static final long zzb;
        static final long zzc;
        static final long zzd;
        static final long zze;
        static final long zzf;
        public static final /* synthetic */ int zzg = 0;

        static {
            Unsafe unsafe;
            try {
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (PrivilegedActionException e) {
                    throw new RuntimeException("Could not initialize intrinsics", e.getCause());
                }
            } catch (SecurityException unused) {
                unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction() { // from class: com.google.android.gms.internal.cast.zzvb
                    @Override // java.security.PrivilegedExceptionAction
                    public final Object run() throws IllegalAccessException {
                        int i = zzuy.zzd.zzg;
                        for (Field field : Unsafe.class.getDeclaredFields()) {
                            field.setAccessible(true);
                            Object obj = field.get(null);
                            if (Unsafe.class.isInstance(obj)) {
                                return (Unsafe) Unsafe.class.cast(obj);
                            }
                        }
                        throw new NoSuchFieldError("the Unsafe");
                    }
                });
            }
            try {
                zzc = unsafe.objectFieldOffset(zzuy.class.getDeclaredField("waitersField"));
                zzb = unsafe.objectFieldOffset(zzuy.class.getDeclaredField("listenersField"));
                zzd = unsafe.objectFieldOffset(zzuy.class.getDeclaredField("valueField"));
                zze = unsafe.objectFieldOffset(zze.class.getDeclaredField("thread"));
                zzf = unsafe.objectFieldOffset(zze.class.getDeclaredField("next"));
                zza = unsafe;
            } catch (NoSuchFieldException e2) {
                throw new RuntimeException(e2);
            }
        }

        private zzd() {
            throw null;
        }

        /* synthetic */ zzd(zzvc zzvcVar) {
            super(null);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final zzux.zzd zza(zzuy zzuyVar, zzux.zzd zzdVar) {
            zzux.zzd zzdVar2;
            do {
                zzdVar2 = zzuyVar.listenersField;
                if (zzdVar == zzdVar2) {
                    break;
                }
            } while (!zze(zzuyVar, zzdVar2, zzdVar));
            return zzdVar2;
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final zze zzb(zzuy zzuyVar, zze zzeVar) {
            zze zzeVar2;
            do {
                zzeVar2 = zzuyVar.waitersField;
                if (zzeVar == zzeVar2) {
                    break;
                }
            } while (!zzg(zzuyVar, zzeVar2, zzeVar));
            return zzeVar2;
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final void zzc(zze zzeVar, zze zzeVar2) {
            zza.putObject(zzeVar, zzf, zzeVar2);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final void zzd(zze zzeVar, Thread thread) {
            zza.putObject(zzeVar, zze, thread);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final boolean zze(zzuy zzuyVar, zzux.zzd zzdVar, zzux.zzd zzdVar2) {
            return zzva.zza(zza, zzuyVar, zzb, zzdVar, zzdVar2);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final boolean zzf(zzuy zzuyVar, Object obj, Object obj2) {
            return zzva.zza(zza, zzuyVar, zzd, obj, obj2);
        }

        @Override // com.google.android.gms.internal.cast.zzuy.zza
        final boolean zzg(zzuy zzuyVar, zze zzeVar, zze zzeVar2) {
            return zzva.zza(zza, zzuyVar, zzc, zzeVar, zzeVar2);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    final class zze {
        static final zze zza = new zze(false);
        volatile zze next;
        volatile Thread thread;

        zze() {
            zzuy.zzk(this, Thread.currentThread());
        }

        zze(boolean z) {
        }
    }

    static {
        boolean z;
        Throwable th;
        Throwable th2;
        zza zzcVar;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        zzc = z;
        String property = System.getProperty("java.runtime.name", "");
        zzvc zzvcVar = null;
        if (property == null || property.contains("Android")) {
            try {
                zzcVar = new zzd(zzvcVar);
            } catch (Error | Exception e) {
                try {
                    zzcVar = new zzb(zzvcVar);
                    th = null;
                    th2 = e;
                } catch (Error | Exception e2) {
                    th = e2;
                    th2 = e;
                    zzcVar = new zzc(zzvcVar);
                }
            }
        } else {
            try {
                zzcVar = new zzb(zzvcVar);
            } catch (NoClassDefFoundError unused2) {
                zzcVar = new zzc(zzvcVar);
            }
        }
        th = null;
        th2 = null;
        zzd = zzcVar;
        if (th != null) {
            zzvo zzvoVar = zzb;
            zzvoVar.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFutureState", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            zzvoVar.zza().logp(Level.SEVERE, "com.google.common.util.concurrent.AbstractFutureState", "<clinit>", "AtomicReferenceFieldUpdaterAtomicHelper is broken!", th);
        }
    }

    zzuy() {
    }

    private final void zza(zze zzeVar) {
        zzeVar.thread = null;
        while (true) {
            zze zzeVar2 = this.waitersField;
            if (zzeVar2 != zze.zza) {
                zze zzeVar3 = null;
                while (zzeVar2 != null) {
                    zze zzeVar4 = zzeVar2.next;
                    if (zzeVar2.thread != null) {
                        zzeVar3 = zzeVar2;
                    } else if (zzeVar3 != null) {
                        zzeVar3.next = zzeVar4;
                        if (zzeVar3.thread == null) {
                        }
                    } else if (!zzd.zzg(this, zzeVar2, zzeVar4)) {
                    }
                    zzeVar2 = zzeVar4;
                }
                return;
            }
            return;
        }
    }

    static /* synthetic */ void zzk(zze zzeVar, Thread thread) {
        zzd.zzd(zzeVar, thread);
    }

    static boolean zzn(zzuy zzuyVar, Object obj, Object obj2) {
        return zzd.zzf(zzuyVar, obj, obj2);
    }

    final zzux.zzd zzh(zzux.zzd zzdVar) {
        return zzd.zza(this, zzdVar);
    }

    final Object zzi() throws ExecutionException, InterruptedException {
        Object obj;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.valueField;
        if ((obj2 != null) && zzux.zze(obj2)) {
            return zzux.zza(obj2);
        }
        zze zzeVar = this.waitersField;
        if (zzeVar != zze.zza) {
            zze zzeVar2 = new zze();
            do {
                zza zzaVar = zzd;
                zzaVar.zzc(zzeVar2, zzeVar);
                if (zzaVar.zzg(this, zzeVar, zzeVar2)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            zza(zzeVar2);
                            throw new InterruptedException();
                        }
                        obj = this.valueField;
                    } while (!((obj != null) & zzux.zze(obj)));
                    return zzux.zza(obj);
                }
                zzeVar = this.waitersField;
            } while (zzeVar != zze.zza);
        }
        return zzux.zza(Objects.requireNonNull(this.valueField));
    }

    final Object zzj(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.valueField;
        boolean z = true;
        if ((obj != null) && zzux.zze(obj)) {
            return zzux.zza(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            zze zzeVar = this.waitersField;
            if (zzeVar != zze.zza) {
                zze zzeVar2 = new zze();
                while (true) {
                    zza zzaVar = zzd;
                    zzaVar.zzc(zzeVar2, zzeVar);
                    if (zzaVar.zzg(this, zzeVar, zzeVar2)) {
                        do {
                            LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                            if (Thread.interrupted()) {
                                zza(zzeVar2);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.valueField;
                            if ((obj2 != null) && zzux.zze(obj2)) {
                                return zzux.zza(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        zza(zzeVar2);
                        break;
                    }
                    zzeVar = this.waitersField;
                    if (zzeVar == zze.zza) {
                    }
                }
            }
            return zzux.zza(Objects.requireNonNull(this.valueField));
        }
        while (nanos > 0) {
            Object obj3 = this.valueField;
            if ((obj3 != null) && zzux.zze(obj3)) {
                return zzux.zza(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
        String strConcat = "Waited " + j + Stream.ID_UNKNOWN + timeUnit.toString().toLowerCase(Locale.ROOT);
        if (nanos + 1000 < 0) {
            String strConcat2 = strConcat.concat(" (plus ");
            long j2 = -nanos;
            long jConvert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
            long nanos2 = j2 - timeUnit.toNanos(jConvert);
            if (jConvert != 0 && nanos2 <= 1000) {
                z = false;
            }
            if (jConvert > 0) {
                String strConcat3 = strConcat2 + jConvert + Stream.ID_UNKNOWN + lowerCase;
                if (z) {
                    strConcat3 = strConcat3.concat(",");
                }
                strConcat2 = strConcat3.concat(Stream.ID_UNKNOWN);
            }
            if (z) {
                strConcat2 = strConcat2 + nanos2 + " nanoseconds ";
            }
            strConcat = strConcat2.concat("delay)");
        }
        if (isDone()) {
            throw new TimeoutException(strConcat.concat(" but future completed as timeout expired"));
        }
        throw new TimeoutException(strConcat + " for " + string);
    }

    final void zzl() {
        for (zze zzeVarZzb = zzd.zzb(this, zze.zza); zzeVarZzb != null; zzeVarZzb = zzeVarZzb.next) {
            Thread thread = zzeVarZzb.thread;
            if (thread != null) {
                zzeVarZzb.thread = null;
                LockSupport.unpark(thread);
            }
        }
    }

    final boolean zzm(zzux.zzd zzdVar, zzux.zzd zzdVar2) {
        return zzd.zze(this, zzdVar, zzdVar2);
    }
}
