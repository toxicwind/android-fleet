package androidx.core.location;

import android.annotation.SuppressLint;
import android.location.GnssStatus;
import android.location.GpsStatus;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public abstract class GnssStatusCompat {

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_BEIDOU = 5;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_GALILEO = 6;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_GLONASS = 3;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_GPS = 1;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_IRNSS = 7;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_QZSS = 4;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_SBAS = 2;

    @SuppressLint({"InlinedApi"})
    public static final int CONSTELLATION_UNKNOWN = 0;

    public static abstract class Callback {
        public void onFirstFix(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int ttffMillis) {
        }

        public void onSatelliteStatusChanged(@NonNull GnssStatusCompat status) {
        }

        public void onStarted() {
        }

        public void onStopped() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface ConstellationType {
    }

    @NonNull
    @RequiresApi(24)
    public static GnssStatusCompat wrap(@NonNull GnssStatus gnssStatus) {
        return new GnssStatusWrapper(gnssStatus);
    }

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 360.0d)
    public abstract float getAzimuthDegrees(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 63.0d)
    public abstract float getBasebandCn0DbHz(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)
    public abstract float getCarrierFrequencyHz(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    @FloatRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, to = 63.0d)
    public abstract float getCn0DbHz(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    public abstract int getConstellationType(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    @FloatRange(from = -90.0d, to = 90.0d)
    public abstract float getElevationDegrees(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    @IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG)
    public abstract int getSatelliteCount();

    @IntRange(from = 1, to = 200)
    public abstract int getSvid(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    public abstract boolean hasAlmanacData(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    public abstract boolean hasBasebandCn0DbHz(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    public abstract boolean hasCarrierFrequencyHz(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    public abstract boolean hasEphemerisData(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    public abstract boolean usedInFix(@IntRange(from = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) int satelliteIndex);

    @NonNull
    @SuppressLint({"ReferencesDeprecated"})
    public static GnssStatusCompat wrap(@NonNull GpsStatus gpsStatus) {
        return new GpsStatusWrapper(gpsStatus);
    }
}
