package androidx.core.location;

import android.location.GnssStatus;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.DoNotInline;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.collection.SimpleArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.os.ExecutorCompat;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import b.a.a.a.a;
import java.lang.reflect.Field;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes.dex */
public final class LocationManagerCompat {
    public static final long GET_CURRENT_LOCATION_TIMEOUT_MS = 30000;
    public static final long MAX_CURRENT_LOCATION_AGE_MS = 10000;
    public static final long PRE_N_LOOPER_TIMEOUT_S = 5;
    public static Field sContextField;

    @GuardedBy("sGnssStatusListeners")
    public static final SimpleArrayMap<Object, Object> sGnssStatusListeners = new SimpleArrayMap<>();

    /* JADX INFO: renamed from: androidx.core.location.LocationManagerCompat$3, reason: invalid class name */
    public class AnonymousClass3 implements Callable<Boolean> {
        public final /* synthetic */ LocationManager val$locationManager;
        public final /* synthetic */ GpsStatusTransport val$myTransport;

        public AnonymousClass3(final LocationManager val$locationManager, final GpsStatusTransport val$myTransport) {
            this.val$locationManager = val$locationManager;
            this.val$myTransport = val$myTransport;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        public Boolean call() {
            return Boolean.valueOf(this.val$locationManager.addGpsStatusListener(this.val$myTransport));
        }
    }

    @RequiresApi(28)
    private static class Api28Impl {
        @DoNotInline
        public static String getGnssHardwareModelName(LocationManager locationManager) {
            return locationManager.getGnssHardwareModelName();
        }

        @DoNotInline
        public static int getGnssYearOfHardware(LocationManager locationManager) {
            return locationManager.getGnssYearOfHardware();
        }

        @DoNotInline
        public static boolean isLocationEnabled(LocationManager locationManager) {
            return locationManager.isLocationEnabled();
        }
    }

    @RequiresApi(30)
    private static class Api30Impl {
        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        @DoNotInline
        public static void getCurrentLocation(LocationManager locationManager, @NonNull String provider, @Nullable CancellationSignal cancellationSignal, @NonNull Executor executor, @NonNull final Consumer<Location> consumer) {
            locationManager.getCurrentLocation(provider, cancellationSignal != null ? (android.os.CancellationSignal) cancellationSignal.getCancellationSignalObject() : null, executor, new java.util.function.Consumer<Location>() { // from class: androidx.core.location.LocationManagerCompat.Api30Impl.1
                @Override // java.util.function.Consumer
                public void accept(Location location) {
                    consumer.accept(location);
                }
            });
        }
    }

    private static final class CancellableLocationListener implements LocationListener {
        public Consumer<Location> mConsumer;
        public final Executor mExecutor;
        public final LocationManager mLocationManager;
        public final Handler mTimeoutHandler = new Handler(Looper.getMainLooper());

        @Nullable
        public Runnable mTimeoutRunnable;

        @GuardedBy("this")
        public boolean mTriggered;

        public CancellableLocationListener(LocationManager locationManager, Executor executor, Consumer<Location> consumer) {
            this.mLocationManager = locationManager;
            this.mExecutor = executor;
            this.mConsumer = consumer;
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        private void cleanup() {
            this.mConsumer = null;
            this.mLocationManager.removeUpdates(this);
            Runnable runnable = this.mTimeoutRunnable;
            if (runnable != null) {
                this.mTimeoutHandler.removeCallbacks(runnable);
                this.mTimeoutRunnable = null;
            }
        }

        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public void cancel() {
            synchronized (this) {
                if (this.mTriggered) {
                    return;
                }
                this.mTriggered = true;
                cleanup();
            }
        }

        @Override // android.location.LocationListener
        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public void onLocationChanged(@Nullable final Location location) {
            synchronized (this) {
                if (this.mTriggered) {
                    return;
                }
                this.mTriggered = true;
                final Consumer<Location> consumer = this.mConsumer;
                this.mExecutor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat.CancellableLocationListener.2
                    @Override // java.lang.Runnable
                    public void run() {
                        consumer.accept(location);
                    }
                });
                cleanup();
            }
        }

        @Override // android.location.LocationListener
        @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
        public void onProviderDisabled(@NonNull String p) {
            onLocationChanged((Location) null);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(@NonNull String provider) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void startTimeout(long timeoutMs) {
            synchronized (this) {
                if (this.mTriggered) {
                    return;
                }
                this.mTimeoutRunnable = new Runnable() { // from class: androidx.core.location.LocationManagerCompat.CancellableLocationListener.1
                    @Override // java.lang.Runnable
                    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
                    public void run() {
                        CancellableLocationListener cancellableLocationListener = CancellableLocationListener.this;
                        cancellableLocationListener.mTimeoutRunnable = null;
                        cancellableLocationListener.onLocationChanged((Location) null);
                    }
                };
                this.mTimeoutHandler.postDelayed(this.mTimeoutRunnable, timeoutMs);
            }
        }
    }

    @RequiresApi(30)
    private static class GnssStatusTransport extends GnssStatus.Callback {
        public final GnssStatusCompat.Callback mCallback;

        public GnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int ttffMillis) {
            this.mCallback.onFirstFix(ttffMillis);
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus status) {
            this.mCallback.onSatelliteStatusChanged(new GnssStatusWrapper(status));
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            this.mCallback.onStarted();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            this.mCallback.onStopped();
        }
    }

    private static class GpsStatusTransport implements GpsStatus.Listener {
        public final GnssStatusCompat.Callback mCallback;

        @Nullable
        public volatile Executor mExecutor;
        public final LocationManager mLocationManager;

        public GpsStatusTransport(LocationManager locationManager, GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mLocationManager = locationManager;
            this.mCallback = callback;
        }

        @Override // android.location.GpsStatus.Listener
        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        public void onGpsStatusChanged(int event) {
            GpsStatus gpsStatus;
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            if (event == 1) {
                executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat.GpsStatusTransport.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (GpsStatusTransport.this.mExecutor != executor) {
                            return;
                        }
                        GpsStatusTransport.this.mCallback.onStarted();
                    }
                });
                return;
            }
            if (event == 2) {
                executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat.GpsStatusTransport.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (GpsStatusTransport.this.mExecutor != executor) {
                            return;
                        }
                        GpsStatusTransport.this.mCallback.onStopped();
                    }
                });
                return;
            }
            if (event != 3) {
                if (event == 4 && (gpsStatus = this.mLocationManager.getGpsStatus(null)) != null) {
                    final GpsStatusWrapper gpsStatusWrapper = new GpsStatusWrapper(gpsStatus);
                    executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat.GpsStatusTransport.4
                        @Override // java.lang.Runnable
                        public void run() {
                            if (GpsStatusTransport.this.mExecutor != executor) {
                                return;
                            }
                            GpsStatusTransport.this.mCallback.onSatelliteStatusChanged(gpsStatusWrapper);
                        }
                    });
                    return;
                }
                return;
            }
            GpsStatus gpsStatus2 = this.mLocationManager.getGpsStatus(null);
            if (gpsStatus2 != null) {
                final int timeToFirstFix = gpsStatus2.getTimeToFirstFix();
                executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat.GpsStatusTransport.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (GpsStatusTransport.this.mExecutor != executor) {
                            return;
                        }
                        GpsStatusTransport.this.mCallback.onFirstFix(timeToFirstFix);
                    }
                });
            }
        }

        public void register(Executor executor) {
            Preconditions.checkState(this.mExecutor == null, null);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }
    }

    private static final class InlineHandlerExecutor implements Executor {
        public final Handler mHandler;

        public InlineHandlerExecutor(@NonNull Handler handler) {
            if (handler == null) {
                throw new NullPointerException();
            }
            this.mHandler = handler;
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable command) {
            if (Looper.myLooper() == this.mHandler.getLooper()) {
                command.run();
                return;
            }
            Handler handler = this.mHandler;
            if (command == null) {
                throw new NullPointerException();
            }
            if (!handler.post(command)) {
                throw new RejectedExecutionException(a.a(new StringBuilder(), this.mHandler, " is shutting down"));
            }
        }
    }

    @RequiresApi(24)
    private static class PreRGnssStatusTransport extends GnssStatus.Callback {
        public final GnssStatusCompat.Callback mCallback;

        @Nullable
        public volatile Executor mExecutor;

        public PreRGnssStatusTransport(GnssStatusCompat.Callback callback) {
            Preconditions.checkArgument(callback != null, "invalid null callback");
            this.mCallback = callback;
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(final int ttffMillis) {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.3
                @Override // java.lang.Runnable
                public void run() {
                    if (PreRGnssStatusTransport.this.mExecutor != executor) {
                        return;
                    }
                    PreRGnssStatusTransport.this.mCallback.onFirstFix(ttffMillis);
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(final GnssStatus status) {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.4
                @Override // java.lang.Runnable
                public void run() {
                    if (PreRGnssStatusTransport.this.mExecutor != executor) {
                        return;
                    }
                    PreRGnssStatusTransport.this.mCallback.onSatelliteStatusChanged(new GnssStatusWrapper(status));
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.1
                @Override // java.lang.Runnable
                public void run() {
                    if (PreRGnssStatusTransport.this.mExecutor != executor) {
                        return;
                    }
                    PreRGnssStatusTransport.this.mCallback.onStarted();
                }
            });
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat.PreRGnssStatusTransport.2
                @Override // java.lang.Runnable
                public void run() {
                    if (PreRGnssStatusTransport.this.mExecutor != executor) {
                        return;
                    }
                    PreRGnssStatusTransport.this.mCallback.onStopped();
                }
            });
        }

        public void register(Executor executor) {
            Preconditions.checkArgument(executor != null, "invalid null executor");
            Preconditions.checkState(this.mExecutor == null, null);
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public static void getCurrentLocation(@NonNull LocationManager locationManager, @NonNull String provider, @Nullable CancellationSignal cancellationSignal, @NonNull Executor executor, @NonNull final Consumer<Location> consumer) {
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.getCurrentLocation(locationManager, provider, cancellationSignal, executor, consumer);
            return;
        }
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        final Location lastKnownLocation = locationManager.getLastKnownLocation(provider);
        if (lastKnownLocation != null && SystemClock.elapsedRealtime() - LocationCompat.getElapsedRealtimeMillis(lastKnownLocation) < 10000) {
            executor.execute(new Runnable() { // from class: androidx.core.location.LocationManagerCompat.1
                @Override // java.lang.Runnable
                public void run() {
                    consumer.accept(lastKnownLocation);
                }
            });
            return;
        }
        final CancellableLocationListener cancellableLocationListener = new CancellableLocationListener(locationManager, executor, consumer);
        locationManager.requestLocationUpdates(provider, 0L, 0.0f, cancellableLocationListener, Looper.getMainLooper());
        if (cancellationSignal != null) {
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.core.location.LocationManagerCompat.2
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
                public void onCancel() {
                    cancellableLocationListener.cancel();
                }
            });
        }
        cancellableLocationListener.startTimeout(30000L);
    }

    @Nullable
    public static String getGnssHardwareModelName(@NonNull LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return locationManager.getGnssHardwareModelName();
        }
        return null;
    }

    public static int getGnssYearOfHardware(@NonNull LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return locationManager.getGnssYearOfHardware();
        }
        return 0;
    }

    public static boolean isLocationEnabled(@NonNull LocationManager locationManager) {
        if (Build.VERSION.SDK_INT >= 28) {
            return locationManager.isLocationEnabled();
        }
        return locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps");
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean registerGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull GnssStatusCompat.Callback callback, @NonNull Handler handler) {
        return Build.VERSION.SDK_INT >= 30 ? registerGnssStatusCallback(locationManager, new ExecutorCompat.HandlerExecutor(handler), callback) : registerGnssStatusCallback(locationManager, new InlineHandlerExecutor(handler), callback);
    }

    public static void unregisterGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            synchronized (sGnssStatusListeners) {
                GnssStatus.Callback callback2 = (GnssStatusTransport) sGnssStatusListeners.remove(callback);
                if (callback2 != null) {
                    locationManager.unregisterGnssStatusCallback(callback2);
                }
            }
            return;
        }
        synchronized (sGnssStatusListeners) {
            PreRGnssStatusTransport preRGnssStatusTransport = (PreRGnssStatusTransport) sGnssStatusListeners.remove(callback);
            if (preRGnssStatusTransport != null) {
                preRGnssStatusTransport.unregister();
                locationManager.unregisterGnssStatusCallback(preRGnssStatusTransport);
            }
        }
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean registerGnssStatusCallback(@NonNull LocationManager locationManager, @NonNull Executor executor, @NonNull GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            return registerGnssStatusCallback(locationManager, null, executor, callback);
        }
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper == null) {
            looperMyLooper = Looper.getMainLooper();
        }
        return registerGnssStatusCallback(locationManager, new Handler(looperMyLooper), executor, callback);
    }

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    public static boolean registerGnssStatusCallback(final LocationManager locationManager, Handler baseHandler, Executor executor, GnssStatusCompat.Callback callback) {
        if (Build.VERSION.SDK_INT >= 30) {
            synchronized (sGnssStatusListeners) {
                GnssStatus.Callback gnssStatusTransport = (GnssStatusTransport) sGnssStatusListeners.get(callback);
                if (gnssStatusTransport == null) {
                    gnssStatusTransport = new GnssStatusTransport(callback);
                }
                if (!locationManager.registerGnssStatusCallback(executor, gnssStatusTransport)) {
                    return false;
                }
                sGnssStatusListeners.put(callback, gnssStatusTransport);
                return true;
            }
        }
        Preconditions.checkArgument(baseHandler != null);
        synchronized (sGnssStatusListeners) {
            PreRGnssStatusTransport preRGnssStatusTransport = (PreRGnssStatusTransport) sGnssStatusListeners.get(callback);
            if (preRGnssStatusTransport == null) {
                preRGnssStatusTransport = new PreRGnssStatusTransport(callback);
            } else {
                preRGnssStatusTransport.unregister();
            }
            preRGnssStatusTransport.register(executor);
            if (!locationManager.registerGnssStatusCallback(preRGnssStatusTransport, baseHandler)) {
                return false;
            }
            sGnssStatusListeners.put(callback, preRGnssStatusTransport);
            return true;
        }
    }
}
