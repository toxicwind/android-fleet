package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class HeartBeatInfoStorage {
    public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy z");
    public static final String GLOBAL = "fire-global";
    public static final int HEART_BEAT_COUNT_LIMIT = 200;
    public static final String HEART_BEAT_COUNT_TAG = "fire-count";
    public static final String PREFERENCES_NAME = "FirebaseAppHeartBeat";
    public static final String STORAGE_PREFERENCES_NAME = "FirebaseAppHeartBeatStorage";
    public static HeartBeatInfoStorage instance;
    public final SharedPreferences heartBeatSharedPreferences;
    public final SharedPreferences sharedPreferences;

    public HeartBeatInfoStorage(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
        this.heartBeatSharedPreferences = context.getSharedPreferences(STORAGE_PREFERENCES_NAME, 0);
    }

    private synchronized void cleanUpStoredHeartBeats() {
        long j = this.sharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0L);
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<String, ?>> it = this.heartBeatSharedPreferences.getAll().entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(Long.parseLong(it.next().getKey())));
        }
        Collections.sort(arrayList);
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            this.heartBeatSharedPreferences.edit().remove(String.valueOf((Long) it2.next())).apply();
            j--;
            this.sharedPreferences.edit().putLong(HEART_BEAT_COUNT_TAG, j).apply();
            if (j <= 100) {
                return;
            }
        }
    }

    public static synchronized HeartBeatInfoStorage getInstance(Context context) {
        if (instance == null) {
            instance = new HeartBeatInfoStorage(context);
        }
        return instance;
    }

    public static boolean isSameDateUtc(long j, long j2) {
        return !FORMATTER.format(new Date(j)).equals(FORMATTER.format(new Date(j2)));
    }

    public synchronized void clearStoredHeartBeats() {
        this.heartBeatSharedPreferences.edit().clear().apply();
        this.sharedPreferences.edit().remove(HEART_BEAT_COUNT_TAG).apply();
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    @VisibleForTesting
    public int getHeartBeatCount() {
        return (int) this.sharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0L);
    }

    public synchronized long getLastGlobalHeartBeat() {
        return this.sharedPreferences.getLong(GLOBAL, -1L);
    }

    public synchronized List<SdkHeartBeatResult> getStoredHeartBeats(boolean z) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (Map.Entry<String, ?> entry : this.heartBeatSharedPreferences.getAll().entrySet()) {
            arrayList.add(SdkHeartBeatResult.create((String) entry.getValue(), Long.parseLong(entry.getKey())));
        }
        Collections.sort(arrayList);
        if (z) {
            clearStoredHeartBeats();
        }
        return arrayList;
    }

    public synchronized boolean shouldSendGlobalHeartBeat(long j) {
        return shouldSendSdkHeartBeat(GLOBAL, j);
    }

    public synchronized boolean shouldSendSdkHeartBeat(String str, long j) {
        if (!this.sharedPreferences.contains(str)) {
            this.sharedPreferences.edit().putLong(str, j).apply();
            return true;
        }
        if (!isSameDateUtc(this.sharedPreferences.getLong(str, -1L), j)) {
            return false;
        }
        this.sharedPreferences.edit().putLong(str, j).apply();
        return true;
    }

    public synchronized void storeHeartBeatInformation(String str, long j) {
        long j2 = this.sharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0L);
        this.heartBeatSharedPreferences.edit().putString(String.valueOf(j), str).apply();
        long j3 = j2 + 1;
        this.sharedPreferences.edit().putLong(HEART_BEAT_COUNT_TAG, j3).apply();
        if (j3 > 200) {
            cleanUpStoredHeartBeats();
        }
    }

    public synchronized void updateGlobalHeartBeat(long j) {
        this.sharedPreferences.edit().putLong(GLOBAL, j).apply();
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    @VisibleForTesting
    public HeartBeatInfoStorage(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2) {
        this.sharedPreferences = sharedPreferences;
        this.heartBeatSharedPreferences = sharedPreferences2;
    }
}
