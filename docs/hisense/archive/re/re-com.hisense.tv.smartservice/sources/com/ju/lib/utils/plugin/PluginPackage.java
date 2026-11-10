package com.ju.lib.utils.plugin;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import b.a.a.a.a;
import dalvik.system.DexClassLoader;

/* JADX INFO: loaded from: classes2.dex */
public class PluginPackage {
    public AssetManager assetManager;
    public DexClassLoader classLoader;
    public String defaultActivity = parseDefaultActivityName();
    public PackageInfo packageInfo;
    public String packageName;
    public Resources resources;

    public PluginPackage(DexClassLoader dexClassLoader, Resources resources, PackageInfo packageInfo) {
        this.packageName = packageInfo.packageName;
        this.classLoader = dexClassLoader;
        this.assetManager = resources.getAssets();
        this.resources = resources;
        this.packageInfo = packageInfo;
    }

    private final String parseDefaultActivityName() {
        ActivityInfo[] activityInfoArr = this.packageInfo.activities;
        return (activityInfoArr == null || activityInfoArr.length <= 0) ? "" : activityInfoArr[0].name;
    }

    public String toString() {
        StringBuilder sbA = a.a("PluginPackage{packageName='");
        a.a(sbA, this.packageName, '\'', ", defaultActivity='");
        a.a(sbA, this.defaultActivity, '\'', ", classLoader=");
        sbA.append(this.classLoader);
        sbA.append(", assetManager=");
        sbA.append(this.assetManager);
        sbA.append(", resources=");
        sbA.append(this.resources);
        sbA.append(", packageInfo=");
        return a.a(sbA, (Object) this.packageInfo, '}');
    }
}
