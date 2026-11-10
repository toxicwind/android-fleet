package com.ju.lib.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestListener;
import com.ju.lib.utils.log.LogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class ImageViewLoadImageStatusListener extends BaseLoadImageStatusListener {
    public static final String TAG = "GlideLoadImageStatusListener";
    public Context mContext;
    public Drawable mErrorDrawable;
    public Glide mGlide;
    public int mOutHeight;
    public int mOutWidth;
    public Drawable mPlaceDrawable;
    public RequestListener mRequestListener;
    public ImageView.ScaleType mScaleType;
    public ImageView mView;
    public Transformation mTransformation = UnTransTransformation.TRANSFORMATION;
    public boolean mIsSetTransformation = false;

    /* JADX INFO: renamed from: com.ju.lib.utils.glide.ImageViewLoadImageStatusListener$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public ImageViewLoadImageStatusListener(Context context, ImageView imageView, ImageView.ScaleType scaleType, Drawable drawable, Drawable drawable2) {
        this.mContext = context;
        this.mView = imageView;
        this.mScaleType = scaleType;
        this.mPlaceDrawable = drawable;
        this.mErrorDrawable = drawable2;
        this.mGlide = Glide.get(this.mContext);
    }

    private void applyCenterCrop() {
        setTransformation(new CenterCrop(this.mGlide.getBitmapPool()));
    }

    private void applyFitCenter() {
        setTransformation(new FitCenter(this.mGlide.getBitmapPool()));
    }

    private void setImageContent(Object obj) {
        ImageView imageView = this.mView;
        if (imageView == null || obj == null) {
            setImageError();
            return;
        }
        if (obj instanceof Bitmap) {
            Bitmap bitmap = (Bitmap) transform((Bitmap) obj, imageView).get();
            if (bitmap != null) {
                LogUtil.d(TAG, " setImageContent , mView:", this.mView, ",transBitmap:", bitmap, ", object:", obj);
                this.mView.setImageBitmap(bitmap);
                return;
            }
            return;
        }
        if (!(obj instanceof BitmapDrawable)) {
            if (obj instanceof Drawable) {
                LogUtil.d(TAG, " setImageContent , mView:", imageView, ",object:", obj);
                this.mView.setImageDrawable((Drawable) obj);
                return;
            }
            return;
        }
        Bitmap bitmap2 = (Bitmap) transform(((BitmapDrawable) obj).getBitmap(), this.mView).get();
        if (bitmap2 != null) {
            LogUtil.d(TAG, " setImageContent , mView:", this.mView, ",transBitmap:", bitmap2, ", object:", obj);
            this.mView.setImageBitmap(bitmap2);
        }
    }

    private void setImageError() {
        ImageView imageView = this.mView;
        if (imageView == null || this.mErrorDrawable == null) {
            return;
        }
        ImageView.ScaleType scaleType = this.mScaleType;
        if (scaleType != null) {
            imageView.setScaleType(scaleType);
        }
        this.mView.setImageDrawable(this.mErrorDrawable);
    }

    private void setImagedPlaceholder() {
        ImageView imageView = this.mView;
        if (imageView == null || this.mPlaceDrawable == null) {
            return;
        }
        ImageView.ScaleType scaleType = this.mScaleType;
        if (scaleType != null) {
            imageView.setScaleType(scaleType);
        }
        this.mView.setImageDrawable(this.mPlaceDrawable);
    }

    private Resource<Bitmap> transform(Bitmap bitmap, ImageView imageView) {
        ImageView.ScaleType scaleType;
        LogUtil.i(TAG, "transform(Bitmap bitmap, ImageView imageView):", bitmap, imageView);
        if (bitmap == null) {
            return null;
        }
        if (this.mScaleType == null) {
            this.mScaleType = imageView.getScaleType();
        }
        if (!this.mIsSetTransformation && (scaleType = this.mScaleType) != null) {
            int i = AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()];
            if (i == 1) {
                applyCenterCrop();
            } else if (i == 2 || i == 3 || i == 4) {
                applyFitCenter();
            }
        }
        if (this.mOutWidth <= 0 || this.mOutHeight <= 0) {
            if (imageView.getWidth() <= 0 || imageView.getHeight() <= 0) {
                this.mOutWidth = bitmap.getWidth();
                this.mOutHeight = bitmap.getHeight();
            } else {
                this.mOutWidth = imageView.getWidth();
                this.mOutHeight = imageView.getHeight();
            }
        }
        return transform(BitmapResource.obtain(bitmap, this.mGlide.getBitmapPool()));
    }

    private Resource<Bitmap> transformEncodeAndTranscode(Bitmap bitmap, ImageView imageView) {
        return transform(bitmap, imageView);
    }

    @Override // com.ju.lib.utils.glide.GlideUtils.ILoadImageStatusListener
    public void loadFail(String str, Exception exc) {
        LogUtil.e(TAG, "loadFail(String url, Exception e):", str, exc);
        setImageError();
        RequestListener requestListener = this.mRequestListener;
    }

    @Override // com.ju.lib.utils.glide.GlideUtils.ILoadImageStatusListener
    public void loadSuccess(String str, Object obj) {
        LogUtil.i(TAG, "loadSuccess(String url, Object object):", str, obj);
        setImageContent(obj);
        RequestListener requestListener = this.mRequestListener;
    }

    public void setListener(RequestListener requestListener) {
        this.mRequestListener = requestListener;
    }

    public void setOverride(int i, int i2) {
        this.mOutWidth = i;
        this.mOutHeight = i2;
    }

    public void setTransformation(Transformation<Bitmap>... transformationArr) {
        if (transformationArr.length < 1) {
            LogUtil.d(TAG, "setTransformation transformations.length < 1");
            return;
        }
        this.mIsSetTransformation = true;
        if (transformationArr.length == 1) {
            this.mTransformation = transformationArr[0];
        } else {
            this.mTransformation = new MultiTransformation(transformationArr);
        }
    }

    @Override // com.ju.lib.utils.glide.GlideUtils.ILoadImageStatusListener
    public void startLoad(String str) {
        LogUtil.i(TAG, "startLoad(String url):", str);
        setImagedPlaceholder();
    }

    private Resource<Bitmap> transform(Resource<Bitmap> resource) {
        if (resource == null) {
            return null;
        }
        Resource<Bitmap> resourceTransform = this.mTransformation.transform(resource, this.mOutWidth, this.mOutHeight);
        if (canRecycleOriginResource() && !resource.equals(resourceTransform)) {
            LogUtil.d(TAG, "recycle Resource<Bitmap>:", resource, ",T:", resource.get());
            resource.recycle();
        }
        return resourceTransform;
    }
}
