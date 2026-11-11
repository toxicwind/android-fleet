package com.lagradost.cloudstream3.utils;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageUtil.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/utils/UiImage;", "", "<init>", "()V", "Image", "Drawable", "Bitmap", "Lcom/lagradost/cloudstream3/utils/UiImage$Bitmap;", "Lcom/lagradost/cloudstream3/utils/UiImage$Drawable;", "Lcom/lagradost/cloudstream3/utils/UiImage$Image;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class UiImage {
    public /* synthetic */ UiImage(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: ImageUtil.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0017\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J+\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/utils/UiImage$Image;", "Lcom/lagradost/cloudstream3/utils/UiImage;", ImagesContract.URL, "", "headers", "", "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "getUrl", "()Ljava/lang/String;", "getHeaders", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Image extends UiImage {
        private final Map<String, String> headers;
        private final String url;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Image copy$default(Image image, String str, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = image.url;
            }
            if ((i & 2) != 0) {
                map = image.headers;
            }
            return image.copy(str, map);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final Map<String, String> component2() {
            return this.headers;
        }

        public final Image copy(String url, Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(url, "url");
            return new Image(url, headers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Image)) {
                return false;
            }
            Image image = (Image) other;
            return Intrinsics.areEqual(this.url, image.url) && Intrinsics.areEqual(this.headers, image.headers);
        }

        public int hashCode() {
            int iHashCode = this.url.hashCode() * 31;
            Map<String, String> map = this.headers;
            return iHashCode + (map == null ? 0 : map.hashCode());
        }

        public String toString() {
            return "Image(url=" + this.url + ", headers=" + this.headers + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Image(String url, Map<String, String> map) {
            super(null);
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
            this.headers = map;
        }

        public /* synthetic */ Image(String str, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : map);
        }

        public final String getUrl() {
            return this.url;
        }

        public final Map<String, String> getHeaders() {
            return this.headers;
        }
    }

    private UiImage() {
    }

    /* JADX INFO: compiled from: ImageUtil.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/utils/UiImage$Drawable;", "Lcom/lagradost/cloudstream3/utils/UiImage;", "resId", "", "<init>", "(I)V", "getResId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Drawable extends UiImage {
        private final int resId;

        public static /* synthetic */ Drawable copy$default(Drawable drawable, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = drawable.resId;
            }
            return drawable.copy(i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getResId() {
            return this.resId;
        }

        public final Drawable copy(int resId) {
            return new Drawable(resId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Drawable) && this.resId == ((Drawable) other).resId;
        }

        public int hashCode() {
            return this.resId;
        }

        public String toString() {
            return "Drawable(resId=" + this.resId + ')';
        }

        public Drawable(int i) {
            super(null);
            this.resId = i;
        }

        public final int getResId() {
            return this.resId;
        }
    }

    /* JADX INFO: compiled from: ImageUtil.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/utils/UiImage$Bitmap;", "Lcom/lagradost/cloudstream3/utils/UiImage;", "bitmap", "Landroid/graphics/Bitmap;", "<init>", "(Landroid/graphics/Bitmap;)V", "getBitmap", "()Landroid/graphics/Bitmap;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Bitmap extends UiImage {
        private final android.graphics.Bitmap bitmap;

        public static /* synthetic */ Bitmap copy$default(Bitmap bitmap, android.graphics.Bitmap bitmap2, int i, Object obj) {
            if ((i & 1) != 0) {
                bitmap2 = bitmap.bitmap;
            }
            return bitmap.copy(bitmap2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final android.graphics.Bitmap getBitmap() {
            return this.bitmap;
        }

        public final Bitmap copy(android.graphics.Bitmap bitmap) {
            Intrinsics.checkNotNullParameter(bitmap, "bitmap");
            return new Bitmap(bitmap);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Bitmap) && Intrinsics.areEqual(this.bitmap, ((Bitmap) other).bitmap);
        }

        public int hashCode() {
            return this.bitmap.hashCode();
        }

        public String toString() {
            return "Bitmap(bitmap=" + this.bitmap + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Bitmap(android.graphics.Bitmap bitmap) {
            super(null);
            Intrinsics.checkNotNullParameter(bitmap, "bitmap");
            this.bitmap = bitmap;
        }

        public final android.graphics.Bitmap getBitmap() {
            return this.bitmap;
        }
    }
}
