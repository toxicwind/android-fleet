package androidx.core.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import androidx.core.content.LocusIdCompat;
import androidx.core.view.DragAndDropPermissionsCompat;
import b.a.a.a.a;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ActivityCompat extends ContextCompat {
    public static PermissionCompatDelegate sDelegate;

    /* JADX INFO: renamed from: androidx.core.app.ActivityCompat$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        public final /* synthetic */ Activity val$activity;
        public final /* synthetic */ String[] val$permissions;
        public final /* synthetic */ int val$requestCode;

        public AnonymousClass1(final String[] val$permissions, final Activity val$activity, final int val$requestCode) {
            this.val$permissions = val$permissions;
            this.val$activity = val$activity;
            this.val$requestCode = val$requestCode;
        }

        @Override // java.lang.Runnable
        public void run() {
            int[] iArr = new int[this.val$permissions.length];
            PackageManager packageManager = this.val$activity.getPackageManager();
            String packageName = this.val$activity.getPackageName();
            int length = this.val$permissions.length;
            for (int i = 0; i < length; i++) {
                iArr[i] = packageManager.checkPermission(this.val$permissions[i], packageName);
            }
            ((OnRequestPermissionsResultCallback) this.val$activity).onRequestPermissionsResult(this.val$requestCode, this.val$permissions, iArr);
        }
    }

    /* JADX INFO: renamed from: androidx.core.app.ActivityCompat$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        public final /* synthetic */ Activity val$activity;

        public AnonymousClass2(final Activity val$activity) {
            this.val$activity = val$activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.val$activity.isFinishing() || ActivityRecreator.recreate(this.val$activity)) {
                return;
            }
            this.val$activity.recreate();
        }
    }

    @RequiresApi(30)
    static class Api30Impl {
        public static void setLocusContext(@NonNull final Activity activity, @Nullable final LocusIdCompat locusId, @Nullable final Bundle bundle) {
            activity.setLocusContext(locusId == null ? null : locusId.toLocusId(), bundle);
        }
    }

    public interface OnRequestPermissionsResultCallback {
        void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
    }

    public interface PermissionCompatDelegate {
        boolean onActivityResult(@NonNull Activity activity, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int requestCode, int resultCode, @Nullable Intent data);

        boolean requestPermissions(@NonNull Activity activity, @NonNull String[] permissions, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int requestCode);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int requestCode);
    }

    @RequiresApi(21)
    private static class SharedElementCallback21Impl extends android.app.SharedElementCallback {
        public final SharedElementCallback mCallback;

        public SharedElementCallback21Impl(SharedElementCallback callback) {
            this.mCallback = callback;
        }

        @Override // android.app.SharedElementCallback
        public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
            return this.mCallback.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
        }

        @Override // android.app.SharedElementCallback
        public View onCreateSnapshotView(Context context, Parcelable snapshot) {
            return this.mCallback.onCreateSnapshotView(context, snapshot);
        }

        @Override // android.app.SharedElementCallback
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            this.mCallback.onMapSharedElements(names, sharedElements);
        }

        @Override // android.app.SharedElementCallback
        public void onRejectSharedElements(List<View> rejectedSharedElements) {
            this.mCallback.onRejectSharedElements(rejectedSharedElements);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
            this.mCallback.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
        }

        @Override // android.app.SharedElementCallback
        public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
            this.mCallback.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
        }

        @Override // android.app.SharedElementCallback
        @RequiresApi(23)
        public void onSharedElementsArrived(List<String> sharedElementNames, List<View> sharedElements, final android.app.SharedElementCallback.OnSharedElementsReadyListener listener) {
            this.mCallback.onSharedElementsArrived(sharedElementNames, sharedElements, new SharedElementCallback.OnSharedElementsReadyListener() { // from class: androidx.core.app.ActivityCompat.SharedElementCallback21Impl.1
                @Override // androidx.core.app.SharedElementCallback.OnSharedElementsReadyListener
                public void onSharedElementsReady() {
                    listener.onSharedElementsReady();
                }
            });
        }
    }

    public static void finishAffinity(@NonNull Activity activity) {
        int i = Build.VERSION.SDK_INT;
        activity.finishAffinity();
    }

    public static void finishAfterTransition(@NonNull Activity activity) {
        int i = Build.VERSION.SDK_INT;
        activity.finishAfterTransition();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static PermissionCompatDelegate getPermissionCompatDelegate() {
        return sDelegate;
    }

    @Nullable
    public static Uri getReferrer(@NonNull Activity activity) {
        int i = Build.VERSION.SDK_INT;
        return activity.getReferrer();
    }

    @Deprecated
    public static boolean invalidateOptionsMenu(Activity activity) {
        activity.invalidateOptionsMenu();
        return true;
    }

    public static void postponeEnterTransition(@NonNull Activity activity) {
        int i = Build.VERSION.SDK_INT;
        activity.postponeEnterTransition();
    }

    public static void recreate(@NonNull final Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
        } else {
            if (ActivityRecreator.recreate(activity)) {
                return;
            }
            activity.recreate();
        }
    }

    @Nullable
    public static DragAndDropPermissionsCompat requestDragAndDropPermissions(Activity activity, DragEvent dragEvent) {
        return DragAndDropPermissionsCompat.request(activity, dragEvent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void requestPermissions(@NonNull final Activity activity, @NonNull final String[] permissions, @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) final int requestCode) {
        PermissionCompatDelegate permissionCompatDelegate = sDelegate;
        if (permissionCompatDelegate == null || !permissionCompatDelegate.requestPermissions(activity, permissions, requestCode)) {
            for (String str : permissions) {
                if (TextUtils.isEmpty(str)) {
                    StringBuilder sbA = a.a("Permission request for permissions ");
                    sbA.append(Arrays.toString(permissions));
                    sbA.append(" must not contain null or empty values");
                    throw new IllegalArgumentException(sbA.toString());
                }
            }
            int i = Build.VERSION.SDK_INT;
            if (activity instanceof RequestPermissionsRequestCodeValidator) {
                ((RequestPermissionsRequestCodeValidator) activity).validateRequestPermissionsRequestCode(requestCode);
            }
            activity.requestPermissions(permissions, requestCode);
        }
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull Activity activity, @IdRes int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (T) activity.requireViewById(i);
        }
        T t = (T) activity.findViewById(i);
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this Activity");
    }

    public static void setEnterSharedElementCallback(@NonNull Activity activity, @Nullable SharedElementCallback callback) {
        int i = Build.VERSION.SDK_INT;
        activity.setEnterSharedElementCallback(callback != null ? new SharedElementCallback21Impl(callback) : null);
    }

    public static void setExitSharedElementCallback(@NonNull Activity activity, @Nullable SharedElementCallback callback) {
        int i = Build.VERSION.SDK_INT;
        activity.setExitSharedElementCallback(callback != null ? new SharedElementCallback21Impl(callback) : null);
    }

    public static void setLocusContext(@NonNull final Activity activity, @Nullable final LocusIdCompat locusId, @Nullable final Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.setLocusContext(activity, locusId, bundle);
        }
    }

    public static void setPermissionCompatDelegate(@Nullable PermissionCompatDelegate delegate) {
        sDelegate = delegate;
    }

    public static boolean shouldShowRequestPermissionRationale(@NonNull Activity activity, @NonNull String permission) {
        int i = Build.VERSION.SDK_INT;
        return activity.shouldShowRequestPermissionRationale(permission);
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int requestCode, @Nullable Bundle options) {
        int i = Build.VERSION.SDK_INT;
        activity.startActivityForResult(intent, requestCode, options);
    }

    public static void startIntentSenderForResult(@NonNull Activity activity, @NonNull IntentSender intent, int requestCode, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) throws IntentSender.SendIntentException {
        int i = Build.VERSION.SDK_INT;
        activity.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    public static void startPostponedEnterTransition(@NonNull Activity activity) {
        int i = Build.VERSION.SDK_INT;
        activity.startPostponedEnterTransition();
    }
}
