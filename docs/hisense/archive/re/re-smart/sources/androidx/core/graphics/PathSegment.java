package androidx.core.graphics;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class PathSegment {
    public final PointF mEnd;
    public final float mEndFraction;
    public final PointF mStart;
    public final float mStartFraction;

    public PathSegment(@NonNull PointF start, float startFraction, @NonNull PointF end, float endFraction) {
        Preconditions.checkNotNull(start, "start == null");
        this.mStart = start;
        this.mStartFraction = startFraction;
        Preconditions.checkNotNull(end, "end == null");
        this.mEnd = end;
        this.mEndFraction = endFraction;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PathSegment)) {
            return false;
        }
        PathSegment pathSegment = (PathSegment) o;
        return Float.compare(this.mStartFraction, pathSegment.mStartFraction) == 0 && Float.compare(this.mEndFraction, pathSegment.mEndFraction) == 0 && this.mStart.equals(pathSegment.mStart) && this.mEnd.equals(pathSegment.mEnd);
    }

    @NonNull
    public PointF getEnd() {
        return this.mEnd;
    }

    public float getEndFraction() {
        return this.mEndFraction;
    }

    @NonNull
    public PointF getStart() {
        return this.mStart;
    }

    public float getStartFraction() {
        return this.mStartFraction;
    }

    public int hashCode() {
        int iHashCode = this.mStart.hashCode() * 31;
        float f2 = this.mStartFraction;
        int iHashCode2 = (this.mEnd.hashCode() + ((iHashCode + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31)) * 31;
        float f3 = this.mEndFraction;
        return iHashCode2 + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0);
    }

    public String toString() {
        StringBuilder sbA = a.a("PathSegment{start=");
        sbA.append(this.mStart);
        sbA.append(", startFraction=");
        sbA.append(this.mStartFraction);
        sbA.append(", end=");
        sbA.append(this.mEnd);
        sbA.append(", endFraction=");
        sbA.append(this.mEndFraction);
        sbA.append('}');
        return sbA.toString();
    }
}
