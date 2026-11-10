package com.google.firebase.firestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.firestore.util.Util;

/* JADX INFO: loaded from: classes.dex */
public class GeoPoint implements Comparable<GeoPoint> {
    public final double latitude;
    public final double longitude;

    public GeoPoint(double d2, double d3) {
        if (Double.isNaN(d2) || d2 < -90.0d || d2 > 90.0d) {
            throw new IllegalArgumentException("Latitude must be in the range of [-90, 90]");
        }
        if (Double.isNaN(d3) || d3 < -180.0d || d3 > 180.0d) {
            throw new IllegalArgumentException("Longitude must be in the range of [-180, 180]");
        }
        this.latitude = d2;
        this.longitude = d3;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof GeoPoint)) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) obj;
        return this.latitude == geoPoint.latitude && this.longitude == geoPoint.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.latitude);
        int i = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        return (i * 31) + ((int) ((jDoubleToLongBits2 >>> 32) ^ jDoubleToLongBits2));
    }

    @NonNull
    public String toString() {
        StringBuilder sbA = a.a("GeoPoint { latitude=");
        sbA.append(this.latitude);
        sbA.append(", longitude=");
        sbA.append(this.longitude);
        sbA.append(" }");
        return sbA.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull GeoPoint geoPoint) {
        int iCompareDoubles = Util.compareDoubles(this.latitude, geoPoint.latitude);
        return iCompareDoubles == 0 ? Util.compareDoubles(this.longitude, geoPoint.longitude) : iCompareDoubles;
    }
}
