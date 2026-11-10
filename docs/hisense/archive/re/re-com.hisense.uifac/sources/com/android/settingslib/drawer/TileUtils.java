package com.android.settingslib.drawer;

import android.content.Context;
import android.content.IContentProvider;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class TileUtils {
    static final String SETTING_PKG = "com.android.settings";

    static void loadTilesForAction(Context context, UserHandle userHandle, String str, Map<Pair<String, String>, Tile> map, String str2, List<Tile> list, boolean z) {
        Intent intent = new Intent(str);
        if (z) {
            intent.setPackage(SETTING_PKG);
        }
        loadActivityTiles(context, userHandle, map, str2, list, intent);
        loadProviderTiles(context, userHandle, map, str2, list, intent);
    }

    private static void loadActivityTiles(Context context, UserHandle userHandle, Map<Pair<String, String>, Tile> map, String str, List<Tile> list, Intent intent) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivitiesAsUser(intent, 128, userHandle.getIdentifier())) {
            if (resolveInfo.system) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                loadTile(userHandle, map, str, list, intent, activityInfo.metaData, activityInfo);
            }
        }
    }

    private static void loadProviderTiles(Context context, UserHandle userHandle, Map<Pair<String, String>, Tile> map, String str, List<Tile> list, Intent intent) {
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentContentProvidersAsUser(intent, 0, userHandle.getIdentifier())) {
            if (resolveInfo.system) {
                ProviderInfo providerInfo = resolveInfo.providerInfo;
                List<Bundle> switchDataFromProvider = getSwitchDataFromProvider(context, providerInfo.authority);
                if (switchDataFromProvider != null && !switchDataFromProvider.isEmpty()) {
                    Iterator<Bundle> it = switchDataFromProvider.iterator();
                    while (it.hasNext()) {
                        loadTile(userHandle, map, str, list, intent, it.next(), providerInfo);
                    }
                }
            }
        }
    }

    private static void loadTile(UserHandle userHandle, Map<Pair<String, String>, Tile> map, String str, List<Tile> list, Intent intent, Bundle bundle, ComponentInfo componentInfo) {
        Pair<String, String> pair;
        Tile activityTile;
        if ((bundle == null || !bundle.containsKey("com.android.settings.category")) && str == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Found ");
            sb.append(componentInfo.name);
            sb.append(" for intent ");
            sb.append(intent);
            sb.append(" missing metadata ");
            sb.append(bundle == null ? "" : "com.android.settings.category");
            Log.w("TileUtils", sb.toString());
            return;
        }
        String string = bundle.getString("com.android.settings.category");
        boolean z = componentInfo instanceof ProviderInfo;
        if (z) {
            pair = new Pair<>(((ProviderInfo) componentInfo).authority, bundle.getString("com.android.settings.keyhint"));
        } else {
            pair = new Pair<>(componentInfo.packageName, componentInfo.name);
        }
        Tile tile = map.get(pair);
        if (tile == null) {
            if (z) {
                activityTile = new ProviderTile((ProviderInfo) componentInfo, string, bundle);
            } else {
                activityTile = new ActivityTile((ActivityInfo) componentInfo, string);
            }
            tile = activityTile;
            map.put(pair, tile);
        } else {
            tile.setMetaData(bundle);
        }
        if (!tile.userHandle.contains(userHandle)) {
            tile.userHandle.add(userHandle);
        }
        if (list.contains(tile)) {
            return;
        }
        list.add(tile);
    }

    private static List<Bundle> getSwitchDataFromProvider(Context context, String str) {
        Bundle bundleFromUri = getBundleFromUri(context, buildUri(str, "getSwitchData"), new ArrayMap(), null);
        if (bundleFromUri != null) {
            return bundleFromUri.getParcelableArrayList("switch_data");
        }
        return null;
    }

    static Uri buildUri(String str, String str2, String str3) {
        return new Uri.Builder().scheme("content").authority(str).appendPath(str2).appendPath(str3).build();
    }

    private static Uri buildUri(String str, String str2) {
        return new Uri.Builder().scheme("content").authority(str).appendPath(str2).build();
    }

    private static Bundle getBundleFromUri(Context context, Uri uri, Map<String, IContentProvider> map, Bundle bundle) {
        IContentProvider providerFromUri;
        Pair<String, String> methodAndKey = getMethodAndKey(uri);
        if (methodAndKey == null) {
            return null;
        }
        String str = (String) methodAndKey.first;
        String str2 = (String) methodAndKey.second;
        if (TextUtils.isEmpty(str) || (providerFromUri = getProviderFromUri(context, uri, map)) == null) {
            return null;
        }
        if (!TextUtils.isEmpty(str2)) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("com.android.settings.keyhint", str2);
        }
        try {
            return providerFromUri.call(context.getPackageName(), context.getAttributionTag(), uri.getAuthority(), str, uri.toString(), bundle);
        } catch (RemoteException unused) {
            return null;
        }
    }

    private static IContentProvider getProviderFromUri(Context context, Uri uri, Map<String, IContentProvider> map) {
        if (uri == null) {
            return null;
        }
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            return null;
        }
        if (!map.containsKey(authority)) {
            map.put(authority, context.getContentResolver().acquireUnstableProvider(uri));
        }
        return map.get(authority);
    }

    private static Pair<String, String> getMethodAndKey(Uri uri) {
        List<String> pathSegments;
        if (uri == null || (pathSegments = uri.getPathSegments()) == null || pathSegments.isEmpty()) {
            return null;
        }
        return Pair.create(pathSegments.get(0), pathSegments.size() > 1 ? pathSegments.get(1) : null);
    }
}
