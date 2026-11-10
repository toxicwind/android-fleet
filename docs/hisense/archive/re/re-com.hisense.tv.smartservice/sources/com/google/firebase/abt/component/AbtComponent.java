package com.google.firebase.abt.component;

import android.content.Context;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AbtComponent {

    @GuardedBy("this")
    public final Map<String, FirebaseABTesting> abtOriginInstances = new HashMap();
    public final Provider<AnalyticsConnector> analyticsConnector;
    public final Context appContext;

    @VisibleForTesting(otherwise = 3)
    public AbtComponent(Context context, Provider<AnalyticsConnector> provider) {
        this.appContext = context;
        this.analyticsConnector = provider;
    }

    @VisibleForTesting
    public FirebaseABTesting createAbtInstance(String str) {
        return new FirebaseABTesting(this.appContext, this.analyticsConnector, str);
    }

    public synchronized FirebaseABTesting get(String str) {
        if (!this.abtOriginInstances.containsKey(str)) {
            this.abtOriginInstances.put(str, createAbtInstance(str));
        }
        return this.abtOriginInstances.get(str);
    }
}
