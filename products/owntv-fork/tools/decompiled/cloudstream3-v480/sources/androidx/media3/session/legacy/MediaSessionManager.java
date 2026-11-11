package androidx.media3.session.legacy;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;
import androidx.media3.common.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public final class MediaSessionManager {
    static final String TAG = "MediaSessionManager";
    private static final Object lock = new Object();
    private static volatile MediaSessionManager sessionManager;
    MediaSessionManagerImpl impl;

    interface RemoteUserInfoImpl {
        String getPackageName();

        int getPid();

        int getUid();
    }

    public static MediaSessionManager getSessionManager(Context context) {
        MediaSessionManager mediaSessionManager;
        synchronized (lock) {
            if (sessionManager == null) {
                sessionManager = new MediaSessionManager(context.getApplicationContext());
            }
            mediaSessionManager = sessionManager;
        }
        return mediaSessionManager;
    }

    private MediaSessionManager(Context context) {
        this.impl = new MediaSessionManagerImpl(context);
    }

    public boolean isTrustedForMediaControl(RemoteUserInfo remoteUserInfo) {
        return this.impl.isTrustedForMediaControl(remoteUserInfo.impl);
    }

    public static final class RemoteUserInfo {
        public static final String LEGACY_CONTROLLER = "android.media.session.MediaController";
        public static final int UNKNOWN_PID = -1;
        public static final int UNKNOWN_UID = -1;
        RemoteUserInfoImpl impl;

        public RemoteUserInfo(String str, int i, int i2) {
            if (str == null) {
                throw new NullPointerException("package shouldn't be null");
            }
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("packageName should be nonempty");
            }
            if (Build.VERSION.SDK_INT >= 28) {
                this.impl = new RemoteUserInfoImplApi28(str, i, i2);
            } else {
                this.impl = new RemoteUserInfoImplBase(str, i, i2);
            }
        }

        public RemoteUserInfo(android.media.session.MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            String packageName = RemoteUserInfoImplApi28.getPackageName(remoteUserInfo);
            if (packageName == null) {
                throw new NullPointerException("package shouldn't be null");
            }
            if (TextUtils.isEmpty(packageName)) {
                throw new IllegalArgumentException("packageName should be nonempty");
            }
            this.impl = new RemoteUserInfoImplApi28(remoteUserInfo);
        }

        public String getPackageName() {
            return this.impl.getPackageName();
        }

        public int getPid() {
            return this.impl.getPid();
        }

        public int getUid() {
            return this.impl.getUid();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof RemoteUserInfo) {
                return this.impl.equals(((RemoteUserInfo) obj).impl);
            }
            return false;
        }

        public int hashCode() {
            return this.impl.hashCode();
        }
    }

    private static class MediaSessionManagerImpl {
        private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
        private static final String PERMISSION_MEDIA_CONTENT_CONTROL = "android.permission.MEDIA_CONTENT_CONTROL";
        private static final String PERMISSION_STATUS_BAR_SERVICE = "android.permission.STATUS_BAR_SERVICE";
        private static final String TAG = "MediaSessionManager";
        ContentResolver contentResolver;
        Context context;

        MediaSessionManagerImpl(Context context) {
            this.context = context;
            this.contentResolver = context.getContentResolver();
        }

        public boolean isTrustedForMediaControl(RemoteUserInfoImpl remoteUserInfoImpl) {
            if (hasMediaControlPermission(remoteUserInfoImpl)) {
                return true;
            }
            try {
                if (this.context.getPackageManager().getApplicationInfo(remoteUserInfoImpl.getPackageName(), 0) == null) {
                    return false;
                }
                return isPermissionGranted(remoteUserInfoImpl, PERMISSION_STATUS_BAR_SERVICE) || isPermissionGranted(remoteUserInfoImpl, PERMISSION_MEDIA_CONTENT_CONTROL) || remoteUserInfoImpl.getUid() == 1000 || remoteUserInfoImpl.getUid() == Process.myUid() || isEnabledNotificationListener(remoteUserInfoImpl);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.d(TAG, "Package " + remoteUserInfoImpl.getPackageName() + " doesn't exist");
                return false;
            }
        }

        private boolean hasMediaControlPermission(RemoteUserInfoImpl remoteUserInfoImpl) {
            return this.context.checkPermission(PERMISSION_MEDIA_CONTENT_CONTROL, remoteUserInfoImpl.getPid(), remoteUserInfoImpl.getUid()) == 0;
        }

        private boolean isPermissionGranted(RemoteUserInfoImpl remoteUserInfoImpl, String str) {
            int pid = remoteUserInfoImpl.getPid();
            Context context = this.context;
            if (pid < 0) {
                return context.getPackageManager().checkPermission(str, remoteUserInfoImpl.getPackageName()) == 0;
            }
            return context.checkPermission(str, remoteUserInfoImpl.getPid(), remoteUserInfoImpl.getUid()) == 0;
        }

        boolean isEnabledNotificationListener(RemoteUserInfoImpl remoteUserInfoImpl) {
            String string = Settings.Secure.getString(this.contentResolver, ENABLED_NOTIFICATION_LISTENERS);
            if (string != null) {
                for (String str : string.split(":")) {
                    ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                    if (componentNameUnflattenFromString != null && componentNameUnflattenFromString.getPackageName().equals(remoteUserInfoImpl.getPackageName())) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private static class RemoteUserInfoImplBase implements RemoteUserInfoImpl {
        private final String packageName;
        private final int pid;
        private final int uid;

        RemoteUserInfoImplBase(String str, int i, int i2) {
            this.packageName = str;
            this.pid = i;
            this.uid = i2;
        }

        @Override // androidx.media3.session.legacy.MediaSessionManager.RemoteUserInfoImpl
        public String getPackageName() {
            return this.packageName;
        }

        @Override // androidx.media3.session.legacy.MediaSessionManager.RemoteUserInfoImpl
        public int getPid() {
            return this.pid;
        }

        @Override // androidx.media3.session.legacy.MediaSessionManager.RemoteUserInfoImpl
        public int getUid() {
            return this.uid;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RemoteUserInfoImplBase)) {
                return false;
            }
            RemoteUserInfoImplBase remoteUserInfoImplBase = (RemoteUserInfoImplBase) obj;
            if (this.pid < 0 || remoteUserInfoImplBase.pid < 0) {
                return TextUtils.equals(this.packageName, remoteUserInfoImplBase.packageName) && this.uid == remoteUserInfoImplBase.uid;
            }
            return TextUtils.equals(this.packageName, remoteUserInfoImplBase.packageName) && this.pid == remoteUserInfoImplBase.pid && this.uid == remoteUserInfoImplBase.uid;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.packageName, Integer.valueOf(this.uid));
        }
    }

    private static final class RemoteUserInfoImplApi28 extends RemoteUserInfoImplBase {
        RemoteUserInfoImplApi28(String str, int i, int i2) {
            super(str, i, i2);
        }

        RemoteUserInfoImplApi28(android.media.session.MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            super(remoteUserInfo.getPackageName(), remoteUserInfo.getPid(), remoteUserInfo.getUid());
        }

        static String getPackageName(android.media.session.MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            return remoteUserInfo.getPackageName();
        }
    }
}
