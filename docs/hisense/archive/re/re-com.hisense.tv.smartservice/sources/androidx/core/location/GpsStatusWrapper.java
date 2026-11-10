package androidx.core.location;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.os.Build;
import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class GpsStatusWrapper extends GnssStatusCompat {
    public static final int BEIDOU_PRN_COUNT = 35;
    public static final int BEIDOU_PRN_OFFSET = 200;
    public static final int GLONASS_PRN_COUNT = 24;
    public static final int GLONASS_PRN_OFFSET = 64;
    public static final int GPS_PRN_COUNT = 32;
    public static final int GPS_PRN_OFFSET = 0;
    public static final int QZSS_SVID_MAX = 200;
    public static final int QZSS_SVID_MIN = 193;
    public static final int SBAS_PRN_MAX = 64;
    public static final int SBAS_PRN_MIN = 33;
    public static final int SBAS_PRN_OFFSET = -87;

    @GuardedBy("mWrapped")
    public Iterator<GpsSatellite> mCachedIterator;

    @GuardedBy("mWrapped")
    public int mCachedIteratorPosition;

    @GuardedBy("mWrapped")
    public GpsSatellite mCachedSatellite;

    @GuardedBy("mWrapped")
    public int mCachedSatelliteCount;
    public final GpsStatus mWrapped;

    public GpsStatusWrapper(GpsStatus gpsStatus) {
        if (gpsStatus == null) {
            throw new NullPointerException();
        }
        this.mWrapped = gpsStatus;
        this.mCachedSatelliteCount = -1;
        this.mCachedIterator = this.mWrapped.getSatellites().iterator();
        this.mCachedIteratorPosition = -1;
        this.mCachedSatellite = null;
    }

    public static int getConstellationFromPrn(int prn) {
        if (prn > 0 && prn <= 32) {
            return 1;
        }
        if (prn >= 33 && prn <= 64) {
            return 2;
        }
        if (prn > 64 && prn <= 88) {
            return 3;
        }
        if (prn <= 200 || prn > 235) {
            return (prn < 193 || prn > 200) ? 0 : 4;
        }
        return 5;
    }

    private GpsSatellite getSatellite(int satelliteIndex) {
        GpsSatellite gpsSatellite;
        synchronized (this.mWrapped) {
            if (satelliteIndex < this.mCachedIteratorPosition) {
                this.mCachedIterator = this.mWrapped.getSatellites().iterator();
                this.mCachedIteratorPosition = -1;
            }
            while (this.mCachedIteratorPosition < satelliteIndex) {
                this.mCachedIteratorPosition++;
                if (!this.mCachedIterator.hasNext()) {
                    this.mCachedSatellite = null;
                    break;
                }
                this.mCachedSatellite = this.mCachedIterator.next();
            }
            gpsSatellite = this.mCachedSatellite;
        }
        Preconditions.checkNotNull(gpsSatellite);
        return gpsSatellite;
    }

    public static int getSvidFromPrn(int prn) {
        int constellationFromPrn = getConstellationFromPrn(prn);
        if (constellationFromPrn == 2) {
            return prn + 87;
        }
        if (constellationFromPrn != 3) {
            return constellationFromPrn != 5 ? prn : prn - 200;
        }
        return prn - 64;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof GpsStatusWrapper) {
            return this.mWrapped.equals(((GpsStatusWrapper) o).mWrapped);
        }
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getAzimuthDegrees(int satelliteIndex) {
        return getSatellite(satelliteIndex).getAzimuth();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getBasebandCn0DbHz(int satelliteIndex) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCarrierFrequencyHz(int satelliteIndex) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getCn0DbHz(int satelliteIndex) {
        return getSatellite(satelliteIndex).getSnr();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getConstellationType(int satelliteIndex) {
        int i = Build.VERSION.SDK_INT;
        return getConstellationFromPrn(getSatellite(satelliteIndex).getPrn());
    }

    @Override // androidx.core.location.GnssStatusCompat
    public float getElevationDegrees(int satelliteIndex) {
        return getSatellite(satelliteIndex).getElevation();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSatelliteCount() {
        int i;
        synchronized (this.mWrapped) {
            if (this.mCachedSatelliteCount == -1) {
                for (GpsSatellite gpsSatellite : this.mWrapped.getSatellites()) {
                    this.mCachedSatelliteCount++;
                }
                this.mCachedSatelliteCount++;
            }
            i = this.mCachedSatelliteCount;
        }
        return i;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public int getSvid(int satelliteIndex) {
        int i = Build.VERSION.SDK_INT;
        return getSvidFromPrn(getSatellite(satelliteIndex).getPrn());
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasAlmanacData(int satelliteIndex) {
        return getSatellite(satelliteIndex).hasAlmanac();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasBasebandCn0DbHz(int satelliteIndex) {
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasCarrierFrequencyHz(int satelliteIndex) {
        return false;
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean hasEphemerisData(int satelliteIndex) {
        return getSatellite(satelliteIndex).hasEphemeris();
    }

    public int hashCode() {
        return this.mWrapped.hashCode();
    }

    @Override // androidx.core.location.GnssStatusCompat
    public boolean usedInFix(int satelliteIndex) {
        return getSatellite(satelliteIndex).usedInFix();
    }
}
