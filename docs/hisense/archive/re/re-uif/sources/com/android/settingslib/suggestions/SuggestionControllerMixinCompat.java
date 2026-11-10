package com.android.settingslib.suggestions;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

/* JADX WARN: Unexpected interfaces in signature: [androidx.lifecycle.LifecycleObserver, java.lang.Object<java.util.List<android.service.settings.suggestions.Suggestion>>] */
/* JADX INFO: loaded from: classes.dex */
public class SuggestionControllerMixinCompat implements Object {
    private final SuggestionController mSuggestionController;

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        this.mSuggestionController.start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        this.mSuggestionController.stop();
    }
}
