package com.hippo.unifile;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
final class ResourcesContract {

    public static class OpenResourceResult {
        public int id;
        public String name;
        public String p;
        public Resources r;
    }

    private ResourcesContract() {
    }

    public static OpenResourceResult openResource(Context context, Uri uri) {
        int identifier;
        String resourceEntryName;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            return null;
        }
        try {
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                return null;
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    identifier = Integer.parseInt(pathSegments.get(0));
                    resourceEntryName = resourcesForApplication.getResourceEntryName(identifier);
                } catch (Resources.NotFoundException | NumberFormatException unused) {
                    return null;
                }
            } else {
                if (size != 2) {
                    return null;
                }
                resourceEntryName = pathSegments.get(1);
                identifier = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
            }
            if (identifier == 0 || resourceEntryName == null) {
                return null;
            }
            OpenResourceResult openResourceResult = new OpenResourceResult();
            openResourceResult.r = resourcesForApplication;
            openResourceResult.p = authority;
            openResourceResult.id = identifier;
            openResourceResult.name = resourceEntryName;
            return openResourceResult;
        } catch (PackageManager.NameNotFoundException unused2) {
            return null;
        }
    }
}
