package com.android.launcher3.icons;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.UserHandle;

/* JADX INFO: loaded from: classes.dex */
public class BaseIconFactory implements AutoCloseable {
    static final boolean ATLEAST_OREO;
    private boolean mBadgeOnLeft;
    private final Canvas mCanvas;
    private final ColorExtractor mColorExtractor;
    protected final Context mContext;
    private boolean mDisableColorExtractor;
    protected final int mIconBitmapSize;
    private IconNormalizer mNormalizer;
    private final Rect mOldBounds;
    private final PackageManager mPm;
    private ShadowGenerator mShadowGenerator;
    private final boolean mShapeDetection;
    private int mWrapperBackgroundColor;
    private Drawable mWrapperIcon;

    public static int getBadgeSizeForIconSize(int i) {
        return (int) (i * 0.444f);
    }

    static {
        ATLEAST_OREO = Build.VERSION.SDK_INT >= 26;
    }

    protected BaseIconFactory(Context context, int i, int i2, boolean z) {
        this.mOldBounds = new Rect();
        this.mBadgeOnLeft = false;
        this.mWrapperBackgroundColor = -1;
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mShapeDetection = z;
        this.mIconBitmapSize = i2;
        this.mPm = applicationContext.getPackageManager();
        this.mColorExtractor = new ColorExtractor();
        Canvas canvas = new Canvas();
        this.mCanvas = canvas;
        canvas.setDrawFilter(new PaintFlagsDrawFilter(4, 2));
        clear();
    }

    protected BaseIconFactory(Context context, int i, int i2) {
        this(context, i, i2, false);
    }

    protected void clear() {
        this.mWrapperBackgroundColor = -1;
        this.mDisableColorExtractor = false;
        this.mBadgeOnLeft = false;
    }

    public ShadowGenerator getShadowGenerator() {
        if (this.mShadowGenerator == null) {
            this.mShadowGenerator = new ShadowGenerator(this.mIconBitmapSize);
        }
        return this.mShadowGenerator;
    }

    public IconNormalizer getNormalizer() {
        if (this.mNormalizer == null) {
            this.mNormalizer = new IconNormalizer(this.mContext, this.mIconBitmapSize, this.mShapeDetection);
        }
        return this.mNormalizer;
    }

    public BitmapInfo createBadgedIconBitmap(Drawable drawable, UserHandle userHandle, boolean z) {
        return createBadgedIconBitmap(drawable, userHandle, z, false, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public BitmapInfo createBadgedIconBitmap(Drawable drawable, UserHandle userHandle, boolean z, boolean z2, float[] fArr) {
        if (fArr == null) {
            fArr = new float[1];
        }
        Drawable drawableNormalizeAndWrapToAdaptiveIcon = normalizeAndWrapToAdaptiveIcon(drawable, z, null, fArr);
        Bitmap bitmapCreateIconBitmap = createIconBitmap(drawableNormalizeAndWrapToAdaptiveIcon, fArr[0]);
        if (ATLEAST_OREO && (drawableNormalizeAndWrapToAdaptiveIcon instanceof AdaptiveIconDrawable)) {
            this.mCanvas.setBitmap(bitmapCreateIconBitmap);
            getShadowGenerator().recreateIcon(Bitmap.createBitmap(bitmapCreateIconBitmap), this.mCanvas);
            this.mCanvas.setBitmap(null);
        }
        if (z2) {
            badgeWithDrawable(bitmapCreateIconBitmap, this.mContext.getDrawable(R$drawable.ic_instant_app_badge));
        }
        if (userHandle != null) {
            Drawable userBadgedIcon = this.mPm.getUserBadgedIcon(new FixedSizeBitmapDrawable(bitmapCreateIconBitmap), userHandle);
            if (userBadgedIcon instanceof BitmapDrawable) {
                bitmapCreateIconBitmap = ((BitmapDrawable) userBadgedIcon).getBitmap();
            } else {
                bitmapCreateIconBitmap = createIconBitmap(userBadgedIcon, 1.0f);
            }
        }
        int iExtractColor = extractColor(bitmapCreateIconBitmap);
        if (drawableNormalizeAndWrapToAdaptiveIcon instanceof BitmapInfo.Extender) {
            return ((BitmapInfo.Extender) drawableNormalizeAndWrapToAdaptiveIcon).getExtendedInfo(bitmapCreateIconBitmap, iExtractColor, this);
        }
        return BitmapInfo.of(bitmapCreateIconBitmap, iExtractColor);
    }

    private Drawable normalizeAndWrapToAdaptiveIcon(Drawable drawable, boolean z, RectF rectF, float[] fArr) {
        float scale;
        if (drawable == null) {
            return null;
        }
        if (z && ATLEAST_OREO) {
            if (this.mWrapperIcon == null) {
                this.mWrapperIcon = this.mContext.getDrawable(R$drawable.adaptive_icon_drawable_wrapper).mutate();
            }
            AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) this.mWrapperIcon;
            adaptiveIconDrawable.setBounds(0, 0, 1, 1);
            boolean[] zArr = new boolean[1];
            scale = getNormalizer().getScale(drawable, rectF, adaptiveIconDrawable.getIconMask(), zArr);
            if (!(drawable instanceof AdaptiveIconDrawable) && !zArr[0]) {
                FixedScaleDrawable fixedScaleDrawable = (FixedScaleDrawable) adaptiveIconDrawable.getForeground();
                fixedScaleDrawable.setDrawable(drawable);
                fixedScaleDrawable.setScale(scale);
                scale = getNormalizer().getScale(adaptiveIconDrawable, rectF, null, null);
                ((ColorDrawable) adaptiveIconDrawable.getBackground()).setColor(this.mWrapperBackgroundColor);
                drawable = adaptiveIconDrawable;
            }
        } else {
            scale = getNormalizer().getScale(drawable, rectF, null, null);
        }
        fArr[0] = scale;
        return drawable;
    }

    public void badgeWithDrawable(Bitmap bitmap, Drawable drawable) {
        this.mCanvas.setBitmap(bitmap);
        badgeWithDrawable(this.mCanvas, drawable);
        this.mCanvas.setBitmap(null);
    }

    public void badgeWithDrawable(Canvas canvas, Drawable drawable) {
        int badgeSizeForIconSize = getBadgeSizeForIconSize(this.mIconBitmapSize);
        if (this.mBadgeOnLeft) {
            int i = this.mIconBitmapSize;
            drawable.setBounds(0, i - badgeSizeForIconSize, badgeSizeForIconSize, i);
        } else {
            int i2 = this.mIconBitmapSize;
            drawable.setBounds(i2 - badgeSizeForIconSize, i2 - badgeSizeForIconSize, i2, i2);
        }
        drawable.draw(canvas);
    }

    private Bitmap createIconBitmap(Drawable drawable, float f) {
        return createIconBitmap(drawable, f, this.mIconBitmapSize);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0081  */
    public Bitmap createIconBitmap(Drawable drawable, float f, int i) {
        int i2;
        int i3;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        if (drawable == null) {
            return bitmapCreateBitmap;
        }
        this.mCanvas.setBitmap(bitmapCreateBitmap);
        this.mOldBounds.set(drawable.getBounds());
        if (ATLEAST_OREO && (drawable instanceof AdaptiveIconDrawable)) {
            float f2 = i;
            int iMax = Math.max((int) Math.ceil(0.010416667f * f2), Math.round((f2 * (1.0f - f)) / 2.0f));
            int i4 = i - iMax;
            drawable.setBounds(iMax, iMax, i4, i4);
            drawable.draw(this.mCanvas);
        } else {
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmapCreateBitmap != null && bitmap.getDensity() == 0) {
                    bitmapDrawable.setTargetDensity(this.mContext.getResources().getDisplayMetrics());
                }
            }
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
                i2 = i;
                i3 = i2;
            } else {
                float f3 = intrinsicWidth / intrinsicHeight;
                if (intrinsicWidth > intrinsicHeight) {
                    i3 = (int) (i / f3);
                    i2 = i;
                } else if (intrinsicHeight > intrinsicWidth) {
                    i2 = (int) (i * f3);
                    i3 = i;
                } else {
                    i2 = i;
                    i3 = i2;
                }
            }
            int i5 = (i - i2) / 2;
            int i6 = (i - i3) / 2;
            drawable.setBounds(i5, i6, i2 + i5, i3 + i6);
            this.mCanvas.save();
            float f4 = i / 2;
            this.mCanvas.scale(f, f, f4, f4);
            drawable.draw(this.mCanvas);
            this.mCanvas.restore();
        }
        drawable.setBounds(this.mOldBounds);
        this.mCanvas.setBitmap(null);
        return bitmapCreateBitmap;
    }

    private int extractColor(Bitmap bitmap) {
        if (this.mDisableColorExtractor) {
            return 0;
        }
        return this.mColorExtractor.findDominantColorByHue(bitmap);
    }

    private static class FixedSizeBitmapDrawable extends BitmapDrawable {
        public FixedSizeBitmapDrawable(Bitmap bitmap) {
            super((Resources) null, bitmap);
        }

        @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return getBitmap().getWidth();
        }

        @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return getBitmap().getWidth();
        }
    }
}
