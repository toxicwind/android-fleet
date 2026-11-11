package coil3.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import coil3.ImageLoaders_androidKt;
import coil3.RealImageLoader;
import coil3.memory.MemoryCache;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SystemCallbacks.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 '2\u00020\u0001:\u0003%&'B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\u001c\u001a\u00020!H\u0016J\u001d\u0010\"\u001a\u00020!2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020!0$H\u0082\bR7\u0010\u0006\u001a\"\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00030\u00030\u0007j\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00030\u0003`\t¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00060\u000fR\u00020\u0000¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00060\u0014R\u00020\u0000¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u001b@BX\u0086\u000e¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\u001f¨\u0006("}, d2 = {"Lcoil3/util/AndroidSystemCallbacks;", "Lcoil3/util/SystemCallbacks;", "strongImageLoaderReference", "Lcoil3/RealImageLoader;", "<init>", "(Lcoil3/RealImageLoader;)V", "imageLoader", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "Lcoil3/util/WeakReference;", "getImageLoader$annotations", "()V", "getImageLoader", "()Ljava/lang/ref/WeakReference;", "activityCallbacks", "Lcoil3/util/AndroidSystemCallbacks$ActivityCallbacks;", "getActivityCallbacks$annotations", "getActivityCallbacks", "()Lcoil3/util/AndroidSystemCallbacks$ActivityCallbacks;", "componentCallbacks", "Lcoil3/util/AndroidSystemCallbacks$ComponentCallbacks;", "getComponentCallbacks$annotations", "getComponentCallbacks", "()Lcoil3/util/AndroidSystemCallbacks$ComponentCallbacks;", "application", "Landroid/content/Context;", ES6Iterator.VALUE_PROPERTY, "", "shutdown", "getShutdown$annotations", "getShutdown", "()Z", "registerMemoryPressureCallbacks", "", "withImageLoader", "block", "Lkotlin/Function1;", "ActivityCallbacks", "ComponentCallbacks", "Companion", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AndroidSystemCallbacks implements SystemCallbacks {
    private static final Companion Companion = new Companion(null);
    private static final String TAG = "AndroidSystemCallbacks";
    private final ActivityCallbacks activityCallbacks;
    private Context application;
    private final ComponentCallbacks componentCallbacks = new ComponentCallbacks();
    private final WeakReference<RealImageLoader> imageLoader;
    private boolean shutdown;

    public static /* synthetic */ void getActivityCallbacks$annotations() {
    }

    public static /* synthetic */ void getComponentCallbacks$annotations() {
    }

    public static /* synthetic */ void getImageLoader$annotations() {
    }

    public static /* synthetic */ void getShutdown$annotations() {
    }

    public AndroidSystemCallbacks(RealImageLoader realImageLoader) {
        this.imageLoader = new WeakReference<>(realImageLoader);
        this.activityCallbacks = new ActivityCallbacks(realImageLoader);
    }

    public final WeakReference<RealImageLoader> getImageLoader() {
        return this.imageLoader;
    }

    public final ActivityCallbacks getActivityCallbacks() {
        return this.activityCallbacks;
    }

    public final ComponentCallbacks getComponentCallbacks() {
        return this.componentCallbacks;
    }

    public final boolean getShutdown() {
        return this.shutdown;
    }

    @Override // coil3.util.SystemCallbacks
    public synchronized void shutdown() {
        if (this.shutdown) {
            return;
        }
        this.shutdown = true;
        Context context = this.application;
        if (context != null) {
            this.activityCallbacks.unregister(context);
            context.unregisterComponentCallbacks(this.componentCallbacks);
        }
        this.imageLoader.clear();
    }

    private final void withImageLoader(Function1<? super RealImageLoader, Unit> block) {
        RealImageLoader realImageLoader = getImageLoader().get();
        if (realImageLoader != null) {
            block.invoke(realImageLoader);
        } else {
            shutdown();
        }
    }

    /* JADX INFO: compiled from: SystemCallbacks.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcoil3/util/AndroidSystemCallbacks$ActivityCallbacks;", "Lcoil3/util/DefaultActivityLifecycleCallbacks;", "strongImageLoaderReference", "Lcoil3/RealImageLoader;", "<init>", "(Lcoil3/util/AndroidSystemCallbacks;Lcoil3/RealImageLoader;)V", "backgroundMaxSizePercent", "", "register", "", "context", "Landroid/content/Context;", "unregister", "onActivityStarted", "activity", "Landroid/app/Activity;", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class ActivityCallbacks implements DefaultActivityLifecycleCallbacks {
        private final double backgroundMaxSizePercent;

        @Override // coil3.util.DefaultActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public /* synthetic */ void onActivityCreated(Activity activity, Bundle bundle) {
            DefaultActivityLifecycleCallbacks.CC.$default$onActivityCreated(this, activity, bundle);
        }

        @Override // coil3.util.DefaultActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public /* synthetic */ void onActivityDestroyed(Activity activity) {
            DefaultActivityLifecycleCallbacks.CC.$default$onActivityDestroyed(this, activity);
        }

        @Override // coil3.util.DefaultActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public /* synthetic */ void onActivityPaused(Activity activity) {
            DefaultActivityLifecycleCallbacks.CC.$default$onActivityPaused(this, activity);
        }

        @Override // coil3.util.DefaultActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public /* synthetic */ void onActivityResumed(Activity activity) {
            DefaultActivityLifecycleCallbacks.CC.$default$onActivityResumed(this, activity);
        }

        @Override // coil3.util.DefaultActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public /* synthetic */ void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            DefaultActivityLifecycleCallbacks.CC.$default$onActivitySaveInstanceState(this, activity, bundle);
        }

        @Override // coil3.util.DefaultActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public /* synthetic */ void onActivityStopped(Activity activity) {
            DefaultActivityLifecycleCallbacks.CC.$default$onActivityStopped(this, activity);
        }

        public ActivityCallbacks(RealImageLoader realImageLoader) {
            this.backgroundMaxSizePercent = ImageLoaders_androidKt.getMemoryCacheMaxSizePercentWhileInBackground(realImageLoader.getOptions());
        }

        public final void register(Context context) {
            if (this.backgroundMaxSizePercent == 1.0d) {
                return;
            }
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type android.app.Application");
            ((Application) applicationContext).registerActivityLifecycleCallbacks(this);
            AndroidSystemCallbacks androidSystemCallbacks = AndroidSystemCallbacks.this;
            RealImageLoader realImageLoader = androidSystemCallbacks.getImageLoader().get();
            if (realImageLoader != null) {
                MemoryCache memoryCache = realImageLoader.getMemoryCache();
                if (memoryCache != null) {
                    memoryCache.setMaxSize((long) (this.backgroundMaxSizePercent * memoryCache.getInitialMaxSize()));
                    Logger logger = realImageLoader.getOptions().getLogger();
                    if (logger != null) {
                        Logger.Level level = Logger.Level.Verbose;
                        if (logger.getMinLevel().compareTo(level) <= 0) {
                            logger.log(AndroidSystemCallbacks.TAG, level, "Restricting " + memoryCache + "'s max size to " + memoryCache.getMaxSize() + " bytes.", null);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            androidSystemCallbacks.shutdown();
        }

        public final void unregister(Context context) {
            if (this.backgroundMaxSizePercent == 1.0d) {
                return;
            }
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type android.app.Application");
            ((Application) applicationContext).unregisterActivityLifecycleCallbacks(this);
            AndroidSystemCallbacks androidSystemCallbacks = AndroidSystemCallbacks.this;
            RealImageLoader realImageLoader = androidSystemCallbacks.getImageLoader().get();
            if (realImageLoader != null) {
                MemoryCache memoryCache = realImageLoader.getMemoryCache();
                if (memoryCache != null) {
                    memoryCache.setMaxSize(memoryCache.getInitialMaxSize());
                    Logger logger = realImageLoader.getOptions().getLogger();
                    if (logger != null) {
                        Logger.Level level = Logger.Level.Verbose;
                        if (logger.getMinLevel().compareTo(level) <= 0) {
                            logger.log(AndroidSystemCallbacks.TAG, level, "Restoring " + memoryCache + "'s max size to " + memoryCache.getMaxSize() + " bytes.", null);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            androidSystemCallbacks.shutdown();
        }

        @Override // coil3.util.DefaultActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            unregister(activity);
        }
    }

    /* JADX INFO: compiled from: SystemCallbacks.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcoil3/util/AndroidSystemCallbacks$ComponentCallbacks;", "Landroid/content/ComponentCallbacks2;", "<init>", "(Lcoil3/util/AndroidSystemCallbacks;)V", "onTrimMemory", "", "level", "", "onLowMemory", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class ComponentCallbacks implements ComponentCallbacks2 {
        public ComponentCallbacks() {
        }

        @Override // android.content.ComponentCallbacks2
        public void onTrimMemory(int level) {
            MemoryCache memoryCache;
            AndroidSystemCallbacks androidSystemCallbacks = AndroidSystemCallbacks.this;
            synchronized (androidSystemCallbacks) {
                RealImageLoader realImageLoader = androidSystemCallbacks.getImageLoader().get();
                if (realImageLoader != null) {
                    Logger logger = realImageLoader.getOptions().getLogger();
                    if (logger != null) {
                        Logger.Level level2 = Logger.Level.Verbose;
                        if (logger.getMinLevel().compareTo(level2) <= 0) {
                            logger.log(AndroidSystemCallbacks.TAG, level2, "trimMemory, level=" + level, null);
                        }
                    }
                    if (level >= 40) {
                        MemoryCache memoryCache2 = realImageLoader.getMemoryCache();
                        if (memoryCache2 != null) {
                            memoryCache2.clear();
                        }
                    } else if (level >= 20) {
                        androidSystemCallbacks.getActivityCallbacks().register(realImageLoader.getOptions().getApplication());
                    } else if (level >= 10 && (memoryCache = realImageLoader.getMemoryCache()) != null) {
                        memoryCache.trimToSize(memoryCache.getSize() / 2);
                    }
                } else {
                    androidSystemCallbacks.shutdown();
                }
                Unit unit = Unit.INSTANCE;
            }
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
            onTrimMemory(80);
        }

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration newConfig) {
            AndroidSystemCallbacks androidSystemCallbacks = AndroidSystemCallbacks.this;
            synchronized (androidSystemCallbacks) {
                if (androidSystemCallbacks.getImageLoader().get() == null) {
                    androidSystemCallbacks.shutdown();
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: compiled from: SystemCallbacks.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcoil3/util/AndroidSystemCallbacks$Companion;", "", "<init>", "()V", "TAG", "", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // coil3.util.SystemCallbacks
    public synchronized void registerMemoryPressureCallbacks() {
        RealImageLoader realImageLoader = getImageLoader().get();
        if (realImageLoader != null) {
            if (this.application == null) {
                Context application = realImageLoader.getOptions().getApplication();
                this.application = application;
                application.registerComponentCallbacks(this.componentCallbacks);
            }
        } else {
            shutdown();
        }
    }
}
