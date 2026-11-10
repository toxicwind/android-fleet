package androidx.mediarouter.media;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
final class MediaRouterJellybeanMr1 {

    public interface Callback extends MediaRouterJellybean.Callback {
        void onRoutePresentationDisplayChanged(Object obj);
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy(callback);
    }

    public static final class RouteInfo {
        public static boolean isEnabled(Object obj) {
            return ((android.media.MediaRouter.RouteInfo) obj).isEnabled();
        }

        public static Display getPresentationDisplay(Object obj) {
            try {
                return ((android.media.MediaRouter.RouteInfo) obj).getPresentationDisplay();
            } catch (NoSuchMethodError e) {
                Log.w("MediaRouterJellybeanMr1", "Cannot get presentation display for the route.", e);
                return null;
            }
        }
    }

    public static final class ActiveScanWorkaround implements Runnable {
        private boolean mActivelyScanningWifiDisplays;
        private final DisplayManager mDisplayManager;
        private final Handler mHandler;
        private Method mScanWifiDisplaysMethod;

        public ActiveScanWorkaround(Context context, Handler handler) {
            if (Build.VERSION.SDK_INT != 17) {
                throw new UnsupportedOperationException();
            }
            this.mDisplayManager = (DisplayManager) context.getSystemService("display");
            this.mHandler = handler;
            try {
                this.mScanWifiDisplaysMethod = DisplayManager.class.getMethod("scanWifiDisplays", new Class[0]);
            } catch (NoSuchMethodException unused) {
            }
        }

        public void setActiveScanRouteTypes(int i) {
            if ((i & 2) != 0) {
                if (this.mActivelyScanningWifiDisplays) {
                    return;
                }
                if (this.mScanWifiDisplaysMethod != null) {
                    this.mActivelyScanningWifiDisplays = true;
                    this.mHandler.post(this);
                    return;
                } else {
                    Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays because the DisplayManager.scanWifiDisplays() method is not available on this device.");
                    return;
                }
            }
            if (this.mActivelyScanningWifiDisplays) {
                this.mActivelyScanningWifiDisplays = false;
                this.mHandler.removeCallbacks(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mActivelyScanningWifiDisplays) {
                try {
                    this.mScanWifiDisplaysMethod.invoke(this.mDisplayManager, new Object[0]);
                } catch (IllegalAccessException e) {
                    Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", e);
                } catch (InvocationTargetException e2) {
                    Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", e2);
                }
                this.mHandler.postDelayed(this, 15000L);
            }
        }
    }

    public static final class IsConnectingWorkaround {
        private Method mGetStatusCodeMethod;
        private int mStatusConnecting;

        public IsConnectingWorkaround() {
            if (Build.VERSION.SDK_INT != 17) {
                throw new UnsupportedOperationException();
            }
            try {
                this.mStatusConnecting = android.media.MediaRouter.RouteInfo.class.getField("STATUS_CONNECTING").getInt(null);
                this.mGetStatusCodeMethod = android.media.MediaRouter.RouteInfo.class.getMethod("getStatusCode", new Class[0]);
            } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException unused) {
            }
        }

        public boolean isConnecting(Object obj) {
            android.media.MediaRouter.RouteInfo routeInfo = (android.media.MediaRouter.RouteInfo) obj;
            Method method = this.mGetStatusCodeMethod;
            if (method == null) {
                return false;
            }
            try {
                return ((Integer) method.invoke(routeInfo, new Object[0])).intValue() == this.mStatusConnecting;
            } catch (IllegalAccessException | InvocationTargetException unused) {
                return false;
            }
        }
    }

    static class CallbackProxy<T extends Callback> extends MediaRouterJellybean.CallbackProxy<T> {
        public CallbackProxy(T t) {
            super(t);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRoutePresentationDisplayChanged(android.media.MediaRouter mediaRouter, android.media.MediaRouter.RouteInfo routeInfo) {
            ((Callback) this.mCallback).onRoutePresentationDisplayChanged(routeInfo);
        }
    }
}
