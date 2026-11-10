package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.BitmapCompat;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class RoundedBitmapDrawableFactory {
    public static final String TAG = "RoundedBitmapDrawableFa";

    private static class DefaultRoundedBitmapDrawable extends RoundedBitmapDrawable {
        public DefaultRoundedBitmapDrawable(Resources res, Bitmap bitmap) {
            super(res, bitmap);
        }

        @Override // androidx.core.graphics.drawable.RoundedBitmapDrawable
        public void gravityCompatApply(int gravity, int bitmapWidth, int bitmapHeight, Rect bounds, Rect outRect) {
            int i = Build.VERSION.SDK_INT;
            Gravity.apply(gravity, bitmapWidth, bitmapHeight, bounds, outRect, 0);
        }

        @Override // androidx.core.graphics.drawable.RoundedBitmapDrawable
        public boolean hasMipMap() {
            Bitmap bitmap = this.mBitmap;
            return bitmap != null && BitmapCompat.hasMipMap(bitmap);
        }

        @Override // androidx.core.graphics.drawable.RoundedBitmapDrawable
        public void setMipMap(boolean mipMap) {
            Bitmap bitmap = this.mBitmap;
            if (bitmap != null) {
                int i = Build.VERSION.SDK_INT;
                bitmap.setHasMipMap(mipMap);
                invalidateSelf();
            }
        }
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources res, @Nullable Bitmap bitmap) {
        int i = Build.VERSION.SDK_INT;
        return new RoundedBitmapDrawable21(res, bitmap);
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources res, @NonNull String filepath) {
        RoundedBitmapDrawable roundedBitmapDrawableCreate = create(res, BitmapFactory.decodeFile(filepath));
        if (roundedBitmapDrawableCreate.getBitmap() == null) {
            Log.w(TAG, "RoundedBitmapDrawable cannot decode " + filepath);
        }
        return roundedBitmapDrawableCreate;
    }

    @NonNull
    public static RoundedBitmapDrawable create(@NonNull Resources res, @NonNull InputStream is) {
        RoundedBitmapDrawable roundedBitmapDrawableCreate = create(res, BitmapFactory.decodeStream(is));
        if (roundedBitmapDrawableCreate.getBitmap() == null) {
            Log.w(TAG, "RoundedBitmapDrawable cannot decode " + is);
        }
        return roundedBitmapDrawableCreate;
    }
}
