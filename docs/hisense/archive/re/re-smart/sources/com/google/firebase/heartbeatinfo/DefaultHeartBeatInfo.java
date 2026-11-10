package com.google.firebase.heartbeatinfo;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Lazy;
import com.google.firebase.heartbeatinfo.DefaultHeartBeatInfo;
import com.google.firebase.heartbeatinfo.HeartBeatInfoStorage;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class DefaultHeartBeatInfo implements HeartBeatInfo {
    public static final ThreadFactory THREAD_FACTORY = new ThreadFactory() { // from class: b.e.b.e.a
        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return DefaultHeartBeatInfo.a(runnable);
        }
    };
    public final Executor backgroundExecutor;
    public final Set<HeartBeatConsumer> consumers;
    public Provider<HeartBeatInfoStorage> storageProvider;

    public DefaultHeartBeatInfo(final Context context, Set<HeartBeatConsumer> set) {
        Lazy lazy = new Lazy(new Provider() { // from class: b.e.b.e.c
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return HeartBeatInfoStorage.getInstance(context);
            }
        });
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), THREAD_FACTORY);
        this.storageProvider = lazy;
        this.consumers = set;
        this.backgroundExecutor = threadPoolExecutor;
    }

    public static /* synthetic */ Thread a(Runnable runnable) {
        return new Thread(runnable, "heartbeat-information-executor");
    }

    @NonNull
    public static Component<HeartBeatInfo> component() {
        return Component.builder(HeartBeatInfo.class).add(Dependency.required(Context.class)).add(Dependency.setOf(HeartBeatConsumer.class)).factory(new ComponentFactory() { // from class: b.e.b.e.b
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return DefaultHeartBeatInfo.a(componentContainer);
            }
        }).build();
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatInfo
    public Task<List<HeartBeatResult>> getAndClearStoredHeartBeatInfo() {
        return Tasks.call(this.backgroundExecutor, new Callable() { // from class: b.e.b.e.e
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f588a.a();
            }
        });
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatInfo
    @NonNull
    public HeartBeatInfo.HeartBeat getHeartBeatCode(@NonNull String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean zShouldSendSdkHeartBeat = this.storageProvider.get().shouldSendSdkHeartBeat(str, jCurrentTimeMillis);
        boolean zShouldSendGlobalHeartBeat = this.storageProvider.get().shouldSendGlobalHeartBeat(jCurrentTimeMillis);
        if (zShouldSendSdkHeartBeat && zShouldSendGlobalHeartBeat) {
            return HeartBeatInfo.HeartBeat.COMBINED;
        }
        if (zShouldSendGlobalHeartBeat) {
            return HeartBeatInfo.HeartBeat.GLOBAL;
        }
        return zShouldSendSdkHeartBeat ? HeartBeatInfo.HeartBeat.SDK : HeartBeatInfo.HeartBeat.NONE;
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatInfo
    public Task<Void> storeHeartBeatInfo(@NonNull final String str) {
        return this.consumers.size() <= 0 ? Tasks.forResult(null) : Tasks.call(this.backgroundExecutor, new Callable() { // from class: b.e.b.e.d
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f586a.a(str);
            }
        });
    }

    public /* synthetic */ List a() {
        HeartBeatInfo.HeartBeat heartBeat;
        ArrayList arrayList = new ArrayList();
        HeartBeatInfoStorage heartBeatInfoStorage = this.storageProvider.get();
        List<SdkHeartBeatResult> storedHeartBeats = heartBeatInfoStorage.getStoredHeartBeats(true);
        long lastGlobalHeartBeat = heartBeatInfoStorage.getLastGlobalHeartBeat();
        for (SdkHeartBeatResult sdkHeartBeatResult : storedHeartBeats) {
            boolean zIsSameDateUtc = HeartBeatInfoStorage.isSameDateUtc(lastGlobalHeartBeat, sdkHeartBeatResult.getMillis());
            if (zIsSameDateUtc) {
                heartBeat = HeartBeatInfo.HeartBeat.COMBINED;
            } else {
                heartBeat = HeartBeatInfo.HeartBeat.SDK;
            }
            if (zIsSameDateUtc) {
                lastGlobalHeartBeat = sdkHeartBeatResult.getMillis();
            }
            arrayList.add(new AutoValue_HeartBeatResult(sdkHeartBeatResult.getSdkName(), sdkHeartBeatResult.getMillis(), heartBeat));
        }
        if (lastGlobalHeartBeat > 0) {
            heartBeatInfoStorage.updateGlobalHeartBeat(lastGlobalHeartBeat);
        }
        return arrayList;
    }

    @VisibleForTesting
    public DefaultHeartBeatInfo(Provider<HeartBeatInfoStorage> provider, Set<HeartBeatConsumer> set, Executor executor) {
        this.storageProvider = provider;
        this.consumers = set;
        this.backgroundExecutor = executor;
    }

    public /* synthetic */ Void a(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (!this.storageProvider.get().shouldSendSdkHeartBeat(str, jCurrentTimeMillis)) {
            return null;
        }
        this.storageProvider.get().storeHeartBeatInformation(str, jCurrentTimeMillis);
        return null;
    }

    public static /* synthetic */ HeartBeatInfo a(ComponentContainer componentContainer) {
        return new DefaultHeartBeatInfo((Context) componentContainer.get(Context.class), componentContainer.setOf(HeartBeatConsumer.class));
    }
}
