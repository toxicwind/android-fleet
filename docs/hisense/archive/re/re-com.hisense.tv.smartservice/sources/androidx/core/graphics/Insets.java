package androidx.core.graphics;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Insets {

    @NonNull
    public static final Insets NONE = new Insets(0, 0, 0, 0);
    public final int bottom;
    public final int left;
    public final int right;
    public final int top;

    public Insets(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @NonNull
    public static Insets add(@NonNull Insets a2, @NonNull Insets b2) {
        return of(a2.left + b2.left, a2.top + b2.top, a2.right + b2.right, a2.bottom + b2.bottom);
    }

    @NonNull
    public static Insets max(@NonNull Insets a2, @NonNull Insets b2) {
        return of(Math.max(a2.left, b2.left), Math.max(a2.top, b2.top), Math.max(a2.right, b2.right), Math.max(a2.bottom, b2.bottom));
    }

    @NonNull
    public static Insets min(@NonNull Insets a2, @NonNull Insets b2) {
        return of(Math.min(a2.left, b2.left), Math.min(a2.top, b2.top), Math.min(a2.right, b2.right), Math.min(a2.bottom, b2.bottom));
    }

    @NonNull
    public static Insets of(int left, int top, int right, int bottom) {
        return (left == 0 && top == 0 && right == 0 && bottom == 0) ? NONE : new Insets(left, top, right, bottom);
    }

    @NonNull
    public static Insets subtract(@NonNull Insets a2, @NonNull Insets b2) {
        return of(a2.left - b2.left, a2.top - b2.top, a2.right - b2.right, a2.bottom - b2.bottom);
    }

    @NonNull
    @RequiresApi(api = 29)
    public static Insets toCompatInsets(@NonNull android.graphics.Insets insets) {
        return of(insets.left, insets.top, insets.right, insets.bottom);
    }

    @NonNull
    @Deprecated
    @RequiresApi(api = 29)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Insets wrap(@NonNull android.graphics.Insets insets) {
        return of(insets.left, insets.top, insets.right, insets.bottom);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Insets.class != o.getClass()) {
            return false;
        }
        Insets insets = (Insets) o;
        return this.bottom == insets.bottom && this.left == insets.left && this.right == insets.right && this.top == insets.top;
    }

    public int hashCode() {
        return (((((this.left * 31) + this.top) * 31) + this.right) * 31) + this.bottom;
    }

    @NonNull
    @RequiresApi(api = 29)
    public android.graphics.Insets toPlatformInsets() {
        return android.graphics.Insets.of(this.left, this.top, this.right, this.bottom);
    }

    public String toString() {
        StringBuilder sbA = a.a("Insets{left=");
        sbA.append(this.left);
        sbA.append(", top=");
        sbA.append(this.top);
        sbA.append(", right=");
        sbA.append(this.right);
        sbA.append(", bottom=");
        sbA.append(this.bottom);
        sbA.append('}');
        return sbA.toString();
    }

    @NonNull
    public static Insets of(@NonNull Rect r) {
        return of(r.left, r.top, r.right, r.bottom);
    }
}
