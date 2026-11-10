package androidx.core.location;

import android.location.GnssStatus;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(24)
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class GnssStatusWrapper extends GnssStatusCompat {
    public final GnssStatus mWrapped;

    public GnssStatusWrapper(GnssStatus gnssStatus) {
        if (gnssStatus == null) {
            throw new NullPointerException();
        }
        this.mWrapped = gnssStatus;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof GnssStatusWrapper) {
            return this.mWrapped.equals(((GnssStatusWrapper) o).mWrapped);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getAzimuthDegrees(int satelliteIndex) {
        return this.mWrapped.getAzimuthDegrees(satelliteIndex);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getBasebandCn0DbHz(int satelliteIndex) {
        if (Build.VERSION.SDK_INT >= 30) {
            return this.mWrapped.getBasebandCn0DbHz(satelliteIndex);
        }
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCarrierFrequencyHz(int satelliteIndex) {
        int i = Build.VERSION.SDK_INT;
        return this.mWrapped.getCarrierFrequencyHz(satelliteIndex);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCn0DbHz(int satelliteIndex) {
        return this.mWrapped.getCn0DbHz(satelliteIndex);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getConstellationType(int satelliteIndex) {
        return this.mWrapped.getConstellationType(satelliteIndex);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getElevationDegrees(int satelliteIndex) {
        return this.mWrapped.getElevationDegrees(satelliteIndex);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSatelliteCount() {
        return this.mWrapped.getSatelliteCount();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSvid(int satelliteIndex) {
        return this.mWrapped.getSvid(satelliteIndex);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasAlmanacData(int satelliteIndex) {
        return this.mWrapped.hasAlmanacData(satelliteIndex);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasBasebandCn0DbHz(int satelliteIndex) {
        if (Build.VERSION.SDK_INT >= 30) {
            return this.mWrapped.hasBasebandCn0DbHz(satelliteIndex);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasCarrierFrequencyHz(int satelliteIndex) {
        int i = Build.VERSION.SDK_INT;
        return this.mWrapped.hasCarrierFrequencyHz(satelliteIndex);
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasEphemerisData(int satelliteIndex) {
        return this.mWrapped.hasEphemerisData(satelliteIndex);
    }

    public int hashCode() {
        return this.mWrapped.hashCode();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean usedInFix(int satelliteIndex) {
        return this.mWrapped.usedInFix(satelliteIndex);
    }
}
