package com.lagradost.cloudstream3.utils;

import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedCallback;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BackPressedCallbackHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\n\u001a\u00020\u000b*\u00020\u00062\u0006\u0010\f\u001a\u00020\b2\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b0\u000e¢\u0006\u0002\b\u0010J\u0012\u0010\u0011\u001a\u00020\u000b*\u00020\u00062\u0006\u0010\f\u001a\u00020\bJ\u0012\u0010\u0012\u001a\u00020\u000b*\u00020\u00062\u0006\u0010\f\u001a\u00020\bJ\u0012\u0010\u0013\u001a\u00020\u000b*\u00020\u00062\u0006\u0010\f\u001a\u00020\bR&\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/utils/BackPressedCallbackHelper;", "", "<init>", "()V", "backPressedCallbacks", "Ljava/util/WeakHashMap;", "Landroidx/activity/ComponentActivity;", "", "", "Landroidx/activity/OnBackPressedCallback;", "attachBackPressedCallback", "", "id", "callback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/utils/BackPressedCallbackHelper$CallbackHelper;", "Lkotlin/ExtensionFunctionType;", "disableBackPressedCallback", "enableBackPressedCallback", "detachBackPressedCallback", "CallbackHelper", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BackPressedCallbackHelper {
    public static final BackPressedCallbackHelper INSTANCE = new BackPressedCallbackHelper();
    private static final WeakHashMap<ComponentActivity, Map<String, OnBackPressedCallback>> backPressedCallbacks = new WeakHashMap<>();

    private BackPressedCallbackHelper() {
    }

    /* JADX INFO: compiled from: BackPressedCallbackHelper.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/utils/BackPressedCallbackHelper$CallbackHelper;", "", "activityRef", "Ljava/lang/ref/WeakReference;", "Landroidx/activity/ComponentActivity;", "callback", "Landroidx/activity/OnBackPressedCallback;", "<init>", "(Ljava/lang/ref/WeakReference;Landroidx/activity/OnBackPressedCallback;)V", "runDefault", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class CallbackHelper {
        private final WeakReference<ComponentActivity> activityRef;
        private final OnBackPressedCallback callback;

        public CallbackHelper(WeakReference<ComponentActivity> activityRef, OnBackPressedCallback callback) {
            Intrinsics.checkNotNullParameter(activityRef, "activityRef");
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.activityRef = activityRef;
            this.callback = callback;
        }

        public final void runDefault() {
            ComponentActivity componentActivity = this.activityRef.get();
            if (componentActivity == null) {
                return;
            }
            boolean isEnabled = this.callback.getIsEnabled();
            this.callback.setEnabled(false);
            try {
                componentActivity.getOnBackPressedDispatcher().onBackPressed();
            } finally {
                this.callback.setEnabled(isEnabled);
            }
        }
    }

    public final void attachBackPressedCallback(ComponentActivity componentActivity, String id, final Function1<? super CallbackHelper, Unit> callback) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(callback, "callback");
        WeakHashMap<ComponentActivity, Map<String, OnBackPressedCallback>> weakHashMap = backPressedCallbacks;
        LinkedHashMap linkedHashMap = weakHashMap.get(componentActivity);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap();
            weakHashMap.put(componentActivity, linkedHashMap);
        }
        Map<String, OnBackPressedCallback> map = linkedHashMap;
        if (map.containsKey(id)) {
            return;
        }
        final WeakReference weakReference = new WeakReference(componentActivity);
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback() { // from class: com.lagradost.cloudstream3.utils.BackPressedCallbackHelper$attachBackPressedCallback$newCallback$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                callback.invoke(new BackPressedCallbackHelper.CallbackHelper(weakReference, this));
            }
        };
        map.put(id, onBackPressedCallback);
        componentActivity.getOnBackPressedDispatcher().addCallback(componentActivity, onBackPressedCallback);
    }

    public final void disableBackPressedCallback(ComponentActivity componentActivity, String id) {
        OnBackPressedCallback onBackPressedCallback;
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        Intrinsics.checkNotNullParameter(id, "id");
        Map<String, OnBackPressedCallback> map = backPressedCallbacks.get(componentActivity);
        if (map == null || (onBackPressedCallback = map.get(id)) == null) {
            return;
        }
        onBackPressedCallback.setEnabled(false);
    }

    public final void enableBackPressedCallback(ComponentActivity componentActivity, String id) {
        OnBackPressedCallback onBackPressedCallback;
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        Intrinsics.checkNotNullParameter(id, "id");
        Map<String, OnBackPressedCallback> map = backPressedCallbacks.get(componentActivity);
        if (map == null || (onBackPressedCallback = map.get(id)) == null) {
            return;
        }
        onBackPressedCallback.setEnabled(true);
    }

    public final void detachBackPressedCallback(ComponentActivity componentActivity, String id) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        Intrinsics.checkNotNullParameter(id, "id");
        WeakHashMap<ComponentActivity, Map<String, OnBackPressedCallback>> weakHashMap = backPressedCallbacks;
        Map<String, OnBackPressedCallback> map = weakHashMap.get(componentActivity);
        if (map == null) {
            return;
        }
        OnBackPressedCallback onBackPressedCallback = map.get(id);
        if (onBackPressedCallback != null) {
            onBackPressedCallback.remove();
            map.remove(id);
        }
        if (map.isEmpty()) {
            weakHashMap.remove(componentActivity);
        }
    }
}
