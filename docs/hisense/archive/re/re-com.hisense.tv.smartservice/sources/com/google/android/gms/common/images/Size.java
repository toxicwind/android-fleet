package com.google.android.gms.common.images;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class Size {
    public final int zanj;
    public final int zank;

    public Size(int i, int i2) {
        this.zanj = i;
        this.zank = i2;
    }

    public static Size parseSize(String str) {
        if (str == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int iIndexOf = str.indexOf(42);
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf(120);
        }
        if (iIndexOf < 0) {
            zah(str);
            throw null;
        }
        try {
            return new Size(Integer.parseInt(str.substring(0, iIndexOf)), Integer.parseInt(str.substring(iIndexOf + 1)));
        } catch (NumberFormatException unused) {
            zah(str);
            throw null;
        }
    }

    public static NumberFormatException zah(String str) {
        throw new NumberFormatException(a.a(a.a((Object) str, 16), "Invalid Size: \"", str, "\""));
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            if (this.zanj == size.zanj && this.zank == size.zank) {
                return true;
            }
        }
        return false;
    }

    public final int getHeight() {
        return this.zank;
    }

    public final int getWidth() {
        return this.zanj;
    }

    public final int hashCode() {
        int i = this.zank;
        int i2 = this.zanj;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }

    public final String toString() {
        int i = this.zanj;
        int i2 = this.zank;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i);
        sb.append("x");
        sb.append(i2);
        return sb.toString();
    }
}
