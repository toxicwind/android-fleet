package com.google.firebase.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;

/* JADX INFO: loaded from: classes.dex */
public class DataCollectionConfigStorage {

    @VisibleForTesting
    public static final String DATA_COLLECTION_DEFAULT_ENABLED = "firebase_data_collection_default_enabled";
    public static final String FIREBASE_APP_PREFS = "com.google.firebase.common.prefs:";
    public boolean dataCollectionDefaultEnabled = readAutoDataCollectionEnabled();
    public final Context deviceProtectedContext;
    public final Publisher publisher;
    public final SharedPreferences sharedPreferences;

    public DataCollectionConfigStorage(Context context, String str, Publisher publisher) {
        this.deviceProtectedContext = directBootSafe(context);
        this.sharedPreferences = this.deviceProtectedContext.getSharedPreferences(FIREBASE_APP_PREFS + str, 0);
        this.publisher = publisher;
    }

    public static Context directBootSafe(Context context) {
        int i = Build.VERSION.SDK_INT;
        return ContextCompat.createDeviceProtectedStorageContext(context);
    }

    private boolean readAutoDataCollectionEnabled() {
        return this.sharedPreferences.contains(DATA_COLLECTION_DEFAULT_ENABLED) ? this.sharedPreferences.getBoolean(DATA_COLLECTION_DEFAULT_ENABLED, true) : readManifestDataCollectionEnabled();
    }

    private boolean readManifestDataCollectionEnabled() {
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = this.deviceProtectedContext.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(this.deviceProtectedContext.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(DATA_COLLECTION_DEFAULT_ENABLED)) {
                return true;
            }
            return applicationInfo.metaData.getBoolean(DATA_COLLECTION_DEFAULT_ENABLED);
        } catch (PackageManager.NameNotFoundException unused) {
            return true;
        }
    }

    private synchronized void updateDataCollectionDefaultEnabled(boolean z) {
        if (this.dataCollectionDefaultEnabled != z) {
            this.dataCollectionDefaultEnabled = z;
            this.publisher.publish(new Event<>(DataCollectionDefaultChange.class, new DataCollectionDefaultChange(z)));
        }
    }

    public synchronized boolean isEnabled() {
        return this.dataCollectionDefaultEnabled;
    }

    public synchronized void setEnabled(Boolean bool) {
        try {
            if (bool == null) {
                this.sharedPreferences.edit().remove(DATA_COLLECTION_DEFAULT_ENABLED).apply();
                updateDataCollectionDefaultEnabled(readManifestDataCollectionEnabled());
            } else {
                boolean zEquals = Boolean.TRUE.equals(bool);
                this.sharedPreferences.edit().putBoolean(DATA_COLLECTION_DEFAULT_ENABLED, zEquals).apply();
                updateDataCollectionDefaultEnabled(zEquals);
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
