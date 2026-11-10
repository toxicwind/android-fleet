package com.google.firebase;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.google.common.base.Ascii;
import com.google.firebase.FirebaseCommonRegistrar;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.heartbeatinfo.DefaultHeartBeatInfo;
import com.google.firebase.platforminfo.DefaultUserAgentPublisher;
import com.google.firebase.platforminfo.KotlinDetector;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.mediatek.twoworlds.tv.MtkTvInputSourceBase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseCommonRegistrar implements ComponentRegistrar {
    public static final String ANDROID_INSTALLER = "android-installer";
    public static final String ANDROID_PLATFORM = "android-platform";
    public static final String DEVICE_BRAND = "device-brand";
    public static final String DEVICE_MODEL = "device-model";
    public static final String DEVICE_NAME = "device-name";
    public static final String FIREBASE_ANDROID = "fire-android";
    public static final String FIREBASE_COMMON = "fire-core";
    public static final String KOTLIN = "kotlin";
    public static final String MIN_SDK = "android-min-sdk";
    public static final String TARGET_SDK = "android-target-sdk";

    public static /* synthetic */ String a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return applicationInfo != null ? String.valueOf(applicationInfo.targetSdkVersion) : "";
    }

    public static /* synthetic */ String b(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return "";
        }
        int i = Build.VERSION.SDK_INT;
        return String.valueOf(applicationInfo.minSdkVersion);
    }

    public static /* synthetic */ String c(Context context) {
        int i = Build.VERSION.SDK_INT;
        if (context.getPackageManager().hasSystemFeature("android.hardware.type.television")) {
            return MtkTvInputSourceBase.INPUT_TYPE_TV;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (context.getPackageManager().hasSystemFeature("android.hardware.type.watch")) {
            return "watch";
        }
        int i3 = Build.VERSION.SDK_INT;
        if (context.getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
            return "auto";
        }
        int i4 = Build.VERSION.SDK_INT;
        return context.getPackageManager().hasSystemFeature("android.hardware.type.embedded") ? "embedded" : "";
    }

    public static /* synthetic */ String d(Context context) {
        String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        return installerPackageName != null ? safeValue(installerPackageName) : "";
    }

    public static String safeValue(String str) {
        return str.replace(Ascii.CASE_MASK, '_').replace('/', '_');
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(DefaultUserAgentPublisher.component());
        arrayList.add(DefaultHeartBeatInfo.component());
        arrayList.add(LibraryVersionComponent.create("fire-android", String.valueOf(Build.VERSION.SDK_INT)));
        arrayList.add(LibraryVersionComponent.create("fire-core", "20.0.0"));
        arrayList.add(LibraryVersionComponent.create(DEVICE_NAME, safeValue(Build.PRODUCT)));
        arrayList.add(LibraryVersionComponent.create(DEVICE_MODEL, safeValue(Build.DEVICE)));
        arrayList.add(LibraryVersionComponent.create(DEVICE_BRAND, safeValue(Build.BRAND)));
        arrayList.add(LibraryVersionComponent.fromContext(TARGET_SDK, new LibraryVersionComponent.VersionExtractor() { // from class: b.e.b.c
            @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
            public final String extract(Object obj) {
                return FirebaseCommonRegistrar.a((Context) obj);
            }
        }));
        arrayList.add(LibraryVersionComponent.fromContext(MIN_SDK, new LibraryVersionComponent.VersionExtractor() { // from class: b.e.b.e
            @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
            public final String extract(Object obj) {
                return FirebaseCommonRegistrar.b((Context) obj);
            }
        }));
        arrayList.add(LibraryVersionComponent.fromContext(ANDROID_PLATFORM, new LibraryVersionComponent.VersionExtractor() { // from class: b.e.b.d
            @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
            public final String extract(Object obj) {
                return FirebaseCommonRegistrar.c((Context) obj);
            }
        }));
        arrayList.add(LibraryVersionComponent.fromContext(ANDROID_INSTALLER, new LibraryVersionComponent.VersionExtractor() { // from class: b.e.b.b
            @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
            public final String extract(Object obj) {
                return FirebaseCommonRegistrar.d((Context) obj);
            }
        }));
        String strDetectVersion = KotlinDetector.detectVersion();
        if (strDetectVersion != null) {
            arrayList.add(LibraryVersionComponent.create("kotlin", strDetectVersion));
        }
        return arrayList;
    }
}
