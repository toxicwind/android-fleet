package com.android.settingslib;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import com.android.internal.annotations.VisibleForTesting;
import com.android.launcher3.icons.IconFactory;

/* JADX INFO: loaded from: classes.dex */
public class Utils {

    @VisibleForTesting
    static final String STORAGE_MANAGER_ENABLED_PROPERTY = "ro.storage_manager.enabled";

    public static ColorStateList getColorAttr(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        try {
            return typedArrayObtainStyledAttributes.getColorStateList(0);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static Drawable getBadgedIcon(Context context, Drawable drawable, UserHandle userHandle) {
        IconFactory iconFactoryObtain = IconFactory.obtain(context);
        try {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), iconFactoryObtain.createBadgedIconBitmap(drawable, userHandle, true).icon);
            if (iconFactoryObtain != null) {
                iconFactoryObtain.close();
            }
            return bitmapDrawable;
        } catch (Throwable th) {
            if (iconFactoryObtain != null) {
                try {
                    iconFactoryObtain.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static Drawable getBadgedIcon(Context context, ApplicationInfo applicationInfo) {
        return getBadgedIcon(context, applicationInfo.loadUnbadgedIcon(context.getPackageManager()), UserHandle.getUserHandleForUid(applicationInfo.uid));
    }
}
