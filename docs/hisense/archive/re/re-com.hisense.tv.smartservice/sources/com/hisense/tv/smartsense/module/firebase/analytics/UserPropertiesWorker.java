package com.hisense.tv.smartsense.module.firebase.analytics;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import b.f.b.a.a;
import b.f.b.b.a.b.g;
import com.google.firebase.analytics.FirebaseAnalytics;

/* JADX INFO: loaded from: classes2.dex */
public class UserPropertiesWorker extends Worker {
    public UserPropertiesWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    public String a() {
        String strA = g.a();
        if (strA == null) {
            return "debug";
        }
        return ((strA.compareTo("5C:34:00:FF:B5:00") >= 0 && strA.compareTo("5C:34:00:FF:B8:B0") <= 0) || (strA.compareTo("A8:82:00:AD:E5:77") >= 0 && strA.compareTo("A8:82:00:AD:E5:FF") <= 0) || (strA.compareTo("18:30:0C:6A:62:D9") >= 0 && strA.compareTo("18:30:0C:6A:62:FF") <= 0)) ? "debug" : "user";
    }

    @Override // androidx.work.Worker
    @NonNull
    public ListenableWorker.Result doWork() {
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        try {
            String strB = a.b(getApplicationContext());
            String strM = a.m(getApplicationContext());
            String strB2 = a.b();
            String strC = a.c();
            String strA = a.a(getApplicationContext());
            String strI = a.i(getApplicationContext());
            String strE = a.e();
            String strA2 = a();
            Log.d("UserPropertiesWorker", "UserProperties:countryCode=" + strB + ",chip=" + strB2 + ",tvversion=" + strM + ",chip=" + strB2 + ",deviceMsg=" + strC + ",brand=" + strA + ",model=" + strI + ",market=" + strE + ",role=" + strA2);
            firebaseAnalytics.setUserProperty("Brand", strA);
            firebaseAnalytics.setUserProperty("CountryCode", strB);
            firebaseAnalytics.setUserProperty("Chip", strB2);
            firebaseAnalytics.setUserProperty("DeviceMsg", strC);
            firebaseAnalytics.setUserProperty("TvVersion", strM);
            firebaseAnalytics.setUserProperty("Model", strI);
            firebaseAnalytics.setUserProperty("Market", strE);
            firebaseAnalytics.setUserProperty("Role", strA2);
            String serial = Build.getSerial();
            StringBuilder sb = new StringBuilder();
            sb.append("UserProperties:serialNumber=");
            sb.append(serial);
            Log.d("UserPropertiesWorker", sb.toString());
            if (!TextUtils.isEmpty(serial)) {
                firebaseAnalytics.setUserId(serial);
            }
        } catch (Exception e2) {
            Log.e("UserPropertiesWorker", e2.toString());
        }
        return new ListenableWorker.Result.Success();
    }
}
