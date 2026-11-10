package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements LifecycleEventObserver {
    public final ClassesInfoCache.CallbackInfo mInfo;
    public final Object mWrapped;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.mWrapped = obj;
        this.mInfo = ClassesInfoCache.sInstance.getInfo(this.mWrapped.getClass());
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        this.mInfo.invokeCallbacks(lifecycleOwner, event, this.mWrapped);
    }
}
