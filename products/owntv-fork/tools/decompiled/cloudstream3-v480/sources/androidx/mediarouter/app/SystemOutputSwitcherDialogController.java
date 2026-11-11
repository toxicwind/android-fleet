package androidx.mediarouter.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaRouter2;
import android.os.Build;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class SystemOutputSwitcherDialogController {
    private static final String OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_R = "com.android.settings.panel.action.MEDIA_OUTPUT";
    private static final String OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_S = "com.android.systemui.action.LAUNCH_MEDIA_OUTPUT_DIALOG";
    private static final String OUTPUT_SWITCHER_INTENT_KEY_PACKAGE_NAME_ANDROID_R = "com.android.settings.panel.extra.PACKAGE_NAME";
    private static final String OUTPUT_SWITCHER_INTENT_KEY_PACKAGE_NAME_ANDROID_S = "package_name";
    private static final String PACKAGE_NAME_SYSTEM_UI = "com.android.systemui";

    private SystemOutputSwitcherDialogController() {
    }

    /* JADX WARN: Code duplicated, block: B:15:0x002c  */
    public static boolean showDialog(Context context) {
        boolean zShowDialogForAndroidR;
        if (Build.VERSION.SDK_INT >= 34) {
            zShowDialogForAndroidR = showDialogForAndroidUAndAbove(context);
        } else if (Build.VERSION.SDK_INT >= 31) {
            if (showDialogForAndroidSAndT(context) || showDialogForAndroidR(context)) {
                zShowDialogForAndroidR = true;
            } else {
                zShowDialogForAndroidR = false;
            }
        } else if (Build.VERSION.SDK_INT == 30) {
            zShowDialogForAndroidR = showDialogForAndroidR(context);
        } else {
            zShowDialogForAndroidR = false;
        }
        if (zShowDialogForAndroidR) {
            return true;
        }
        return isRunningOnWear(context) && showBluetoothSettingsFragment(context);
    }

    private static boolean showDialogForAndroidUAndAbove(Context context) {
        if (Build.VERSION.SDK_INT < 30) {
            return false;
        }
        MediaRouter2 api30Impl = Api30Impl.getInstance(context);
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.showSystemOutputSwitcher(api30Impl);
        }
        return false;
    }

    private static boolean showDialogForAndroidSAndT(Context context) {
        Intent intentPutExtra = new Intent().setAction(OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_S).setPackage(PACKAGE_NAME_SYSTEM_UI).putExtra("package_name", context.getPackageName());
        Iterator<ResolveInfo> it = context.getPackageManager().queryBroadcastReceivers(intentPutExtra, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && activityInfo.applicationInfo != null && (activityInfo.applicationInfo.flags & 129) != 0) {
                context.sendBroadcast(intentPutExtra);
                return true;
            }
        }
        return false;
    }

    private static boolean showDialogForAndroidR(Context context) {
        Intent intentPutExtra = new Intent().addFlags(268435456).setAction(OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_R).putExtra(OUTPUT_SWITCHER_INTENT_KEY_PACKAGE_NAME_ANDROID_R, context.getPackageName());
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intentPutExtra, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && activityInfo.applicationInfo != null) {
                ApplicationInfo applicationInfo = activityInfo.applicationInfo;
                if ((applicationInfo.flags & 129) != 0) {
                    intentPutExtra.setPackage(applicationInfo.packageName);
                    context.startActivity(intentPutExtra);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean showBluetoothSettingsFragment(Context context) {
        Intent intentPutExtra = new Intent("android.settings.BLUETOOTH_SETTINGS").addFlags(268468224).putExtra("EXTRA_CONNECTION_ONLY", true).putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 1).putExtra("EXTRA_CLOSE_ON_CONNECT", !isSuitableDeviceAlreadyConnectedAsAudioOutput(context));
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intentPutExtra, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && activityInfo.applicationInfo != null) {
                ApplicationInfo applicationInfo = activityInfo.applicationInfo;
                if ((applicationInfo.flags & 129) != 0) {
                    intentPutExtra.setPackage(applicationInfo.packageName);
                    context.startActivity(intentPutExtra);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isRunningOnWear(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    private static boolean isSuitableDeviceAlreadyConnectedAsAudioOutput(Context context) {
        return Api23Impl.isSuitableDeviceAlreadyConnectedAsAudioOutput(context);
    }

    static class Api30Impl {
        private Api30Impl() {
        }

        static MediaRouter2 getInstance(Context context) {
            return MediaRouter2.getInstance(context);
        }
    }

    static class Api34Impl {
        private Api34Impl() {
        }

        static boolean showSystemOutputSwitcher(MediaRouter2 mediaRouter2) {
            return mediaRouter2.showSystemOutputSwitcher();
        }
    }

    static final class Api23Impl {
        private Api23Impl() {
        }

        public static boolean isSuitableDeviceAlreadyConnectedAsAudioOutput(Context context) {
            for (AudioDeviceInfo audioDeviceInfo : ((AudioManager) context.getSystemService(AudioManager.class)).getDevices(2)) {
                int type = audioDeviceInfo.getType();
                if (type == 3 || type == 4 || type == 5 || type == 6 || type == 8 || type == 11 || type == 30 || type == 22 || type == 23 || type == 26 || type == 27) {
                    return true;
                }
            }
            return false;
        }
    }
}
