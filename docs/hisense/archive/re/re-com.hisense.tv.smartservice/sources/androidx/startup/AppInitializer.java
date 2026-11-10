package androidx.startup;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.tracing.Trace;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class AppInitializer {
    public static final String SECTION_NAME = "Startup";
    public static volatile AppInitializer sInstance;
    public static final Object sLock = new Object();

    @NonNull
    public final Context mContext;

    @NonNull
    public final Set<Class<? extends Initializer<?>>> mDiscovered = new HashSet();

    @NonNull
    public final Map<Class<?>, Object> mInitialized = new HashMap();

    public AppInitializer(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }

    @NonNull
    public static AppInitializer getInstance(@NonNull Context context) {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new AppInitializer(context);
                }
            }
        }
        return sInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void discoverAndInitialize() {
        try {
            try {
                Trace.beginSection(SECTION_NAME);
                Bundle bundle = this.mContext.getPackageManager().getProviderInfo(new ComponentName(this.mContext.getPackageName(), InitializationProvider.class.getName()), 128).metaData;
                String string = this.mContext.getString(R.string.androidx_startup);
                if (bundle != null) {
                    HashSet hashSet = new HashSet();
                    for (String str : bundle.keySet()) {
                        if (string.equals(bundle.getString(str, null))) {
                            Class<?> cls = Class.forName(str);
                            if (Initializer.class.isAssignableFrom(cls)) {
                                this.mDiscovered.add((Class<? extends Initializer<?>>) cls);
                                doInitialize(cls, hashSet);
                            } else {
                                continue;
                            }
                        }
                    }
                }
                Trace.endSection();
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException e2) {
            throw new StartupException(e2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001f A[Catch: all -> 0x008c, TRY_LEAVE, TryCatch #2 {all -> 0x008c, blocks: (B:6:0x0009, B:7:0x0010, B:9:0x0017, B:11:0x001f, B:12:0x0022, B:14:0x003a, B:15:0x003e, B:17:0x0044, B:19:0x0052, B:20:0x0056, B:23:0x0066, B:24:0x006b, B:25:0x006c, B:29:0x0077, B:30:0x008b), top: B:40:0x0009, outer: #0, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:14:0x003a A[Catch: Throwable -> 0x0065, all -> 0x008c, TryCatch #2 {all -> 0x008c, blocks: (B:6:0x0009, B:7:0x0010, B:9:0x0017, B:11:0x001f, B:12:0x0022, B:14:0x003a, B:15:0x003e, B:17:0x0044, B:19:0x0052, B:20:0x0056, B:23:0x0066, B:24:0x006b, B:25:0x006c, B:29:0x0077, B:30:0x008b), top: B:40:0x0009, outer: #0, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:17:0x0044 A[Catch: Throwable -> 0x0065, all -> 0x008c, TryCatch #2 {all -> 0x008c, blocks: (B:6:0x0009, B:7:0x0010, B:9:0x0017, B:11:0x001f, B:12:0x0022, B:14:0x003a, B:15:0x003e, B:17:0x0044, B:19:0x0052, B:20:0x0056, B:23:0x0066, B:24:0x006b, B:25:0x006c, B:29:0x0077, B:30:0x008b), top: B:40:0x0009, outer: #0, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:25:0x006c A[Catch: all -> 0x008c, TRY_LEAVE, TryCatch #2 {all -> 0x008c, blocks: (B:6:0x0009, B:7:0x0010, B:9:0x0017, B:11:0x001f, B:12:0x0022, B:14:0x003a, B:15:0x003e, B:17:0x0044, B:19:0x0052, B:20:0x0056, B:23:0x0066, B:24:0x006b, B:25:0x006c, B:29:0x0077, B:30:0x008b), top: B:40:0x0009, outer: #0, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x0077 A[Catch: all -> 0x008c, TRY_ENTER, TryCatch #2 {all -> 0x008c, blocks: (B:6:0x0009, B:7:0x0010, B:9:0x0017, B:11:0x001f, B:12:0x0022, B:14:0x003a, B:15:0x003e, B:17:0x0044, B:19:0x0052, B:20:0x0056, B:23:0x0066, B:24:0x006b, B:25:0x006c, B:29:0x0077, B:30:0x008b), top: B:40:0x0009, outer: #0, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x0052 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:44:0x003e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:9:0x0017 A[Catch: all -> 0x008c, TryCatch #2 {all -> 0x008c, blocks: (B:6:0x0009, B:7:0x0010, B:9:0x0017, B:11:0x001f, B:12:0x0022, B:14:0x003a, B:15:0x003e, B:17:0x0044, B:19:0x0052, B:20:0x0056, B:23:0x0066, B:24:0x006b, B:25:0x006c, B:29:0x0077, B:30:0x008b), top: B:40:0x0009, outer: #0, inners: #1 }] */
    @NonNull
    public <T> T doInitialize(@NonNull Class<? extends Initializer<?>> cls, @NonNull Set<Class<?>> set) {
        T t;
        List<Class<? extends Initializer<?>>> listDependencies;
        synchronized (sLock) {
            if (Trace.isEnabled()) {
                try {
                    Trace.beginSection(cls.getSimpleName());
                    if (!set.contains(cls)) {
                        throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", cls.getName()));
                    }
                    if (this.mInitialized.containsKey(cls)) {
                        t = (T) this.mInitialized.get(cls);
                    } else {
                        set.add(cls);
                        try {
                            Initializer<?> initializerNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                            listDependencies = initializerNewInstance.dependencies();
                            if (!listDependencies.isEmpty()) {
                                for (Class<? extends Initializer<?>> cls2 : listDependencies) {
                                    if (!this.mInitialized.containsKey(cls2)) {
                                        doInitialize(cls2, set);
                                    }
                                }
                            }
                            t = (T) initializerNewInstance.create(this.mContext);
                            set.remove(cls);
                            this.mInitialized.put(cls, t);
                        } catch (Throwable th) {
                            throw new StartupException(th);
                        }
                    }
                    Trace.endSection();
                } catch (Throwable th2) {
                    Trace.endSection();
                    throw th2;
                }
            } else {
                if (!set.contains(cls)) {
                    throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", cls.getName()));
                }
                if (this.mInitialized.containsKey(cls)) {
                    set.add(cls);
                    Initializer<?> initializerNewInstance2 = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    listDependencies = initializerNewInstance2.dependencies();
                    if (!listDependencies.isEmpty()) {
                        while (r2.hasNext()) {
                            if (!this.mInitialized.containsKey(cls2)) {
                                doInitialize(cls2, set);
                            }
                        }
                    }
                    t = (T) initializerNewInstance2.create(this.mContext);
                    set.remove(cls);
                    this.mInitialized.put(cls, t);
                } else {
                    t = (T) this.mInitialized.get(cls);
                }
                Trace.endSection();
            }
            throw th;
        }
        return t;
    }

    @NonNull
    public <T> T initializeComponent(@NonNull Class<? extends Initializer<T>> cls) {
        return (T) doInitialize(cls, new HashSet());
    }

    public boolean isEagerlyInitialized(@NonNull Class<? extends Initializer<?>> cls) {
        return this.mDiscovered.contains(cls);
    }
}
