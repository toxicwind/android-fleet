package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import e.b.b;
import e.b.d;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "WebImageCreator")
public final class WebImage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WebImage> CREATOR = new zae();

    @SafeParcelable.VersionField(id = 1)
    public final int zali;

    @SafeParcelable.Field(getter = "getUrl", id = 2)
    public final Uri zani;

    @SafeParcelable.Field(getter = "getWidth", id = 3)
    public final int zanj;

    @SafeParcelable.Field(getter = "getHeight", id = 4)
    public final int zank;

    @SafeParcelable.Constructor
    public WebImage(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) Uri uri, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3) {
        this.zali = i;
        this.zani = uri;
        this.zanj = i2;
        this.zank = i3;
    }

    public static Uri zaa(d dVar) {
        if (dVar.f2163b.containsKey(ImagesContract.URL)) {
            try {
                return Uri.parse(dVar.h(ImagesContract.URL));
            } catch (b unused) {
            }
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof WebImage)) {
            WebImage webImage = (WebImage) obj;
            if (Objects.equal(this.zani, webImage.zani) && this.zanj == webImage.zanj && this.zank == webImage.zank) {
                return true;
            }
        }
        return false;
    }

    public final int getHeight() {
        return this.zank;
    }

    public final Uri getUrl() {
        return this.zani;
    }

    public final int getWidth() {
        return this.zanj;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zani, Integer.valueOf(this.zanj), Integer.valueOf(this.zank)});
    }

    @KeepForSdk
    public final d toJson() {
        d dVar = new d();
        try {
            dVar.a(ImagesContract.URL, (Object) this.zani.toString());
            dVar.b("width", this.zanj);
            dVar.b("height", this.zank);
        } catch (b unused) {
        }
        return dVar;
    }

    public final String toString() {
        return String.format(Locale.US, "Image %dx%d %s", Integer.valueOf(this.zanj), Integer.valueOf(this.zank), this.zani.toString());
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        int i2 = this.zali;
        parcel.writeInt(262145);
        parcel.writeInt(i2);
        SafeParcelWriter.writeParcelable(parcel, 2, getUrl(), i, false);
        int width = getWidth();
        parcel.writeInt(262147);
        parcel.writeInt(width);
        int height = getHeight();
        parcel.writeInt(262148);
        parcel.writeInt(height);
        SafeParcelWriter.zzb(parcel, iBeginObjectHeader);
    }

    public WebImage(Uri uri, int i, int i2) {
        this(1, uri, i, i2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    public WebImage(Uri uri) {
        this(uri, 0, 0);
    }

    @KeepForSdk
    public WebImage(d dVar) {
        this(zaa(dVar), dVar.a("width", 0), dVar.a("height", 0));
    }
}
