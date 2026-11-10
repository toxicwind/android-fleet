package com.ju.lib.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import b.a.a.a.a;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.transcode.GlideBitmapDrawableTranscoder;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.ju.lib.utils.log.LogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class TargetLoadImageStatusListener extends BaseLoadImageStatusListener {
    public static final int RESOURCE_TYPE_BITMAP = 1;
    public static final int RESOURCE_TYPE_BITMAP_DRAWABLE = 0;
    public static final String TAG = "TargetLoadImageStatusListener";
    public Context mContext;
    public Drawable mErrorDrawable;
    public Glide mGlide;
    public int mOutHeight;
    public int mOutWidth;
    public Drawable mPlaceDrawable;
    public RequestListener mRequestListener;
    public ImageView.ScaleType mScaleType;
    public BaseTarget mTarget;
    public Transformation mTransformation = UnTransTransformation.TRANSFORMATION;
    public int mResourceType = 0;
    public boolean mIsSetTransformation = false;

    /* JADX INFO: renamed from: com.ju.lib.utils.glide.TargetLoadImageStatusListener$1, reason: invalid class name */
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

    public TargetLoadImageStatusListener(Context context, BaseTarget baseTarget, ImageView.ScaleType scaleType, Drawable drawable, Drawable drawable2) {
        this.mContext = context;
        this.mTarget = baseTarget;
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

    private void onResourceReady(Resource<GlideBitmapDrawable> resource, BaseTarget baseTarget) {
        LogUtil.d(TAG, "onResourceReady transDrawableResource:", resource, ", target:", baseTarget, ", mResourceType:", Integer.valueOf(this.mResourceType));
        GlideBitmapDrawable glideBitmapDrawable = (GlideBitmapDrawable) resource.get();
        int i = this.mResourceType;
        if (i == 0) {
            baseTarget.onResourceReady(glideBitmapDrawable, (GlideAnimation) null);
        } else if (i == 1) {
            baseTarget.onResourceReady(glideBitmapDrawable.getBitmap(), (GlideAnimation) null);
        } else {
            setImageError(new Exception("unknown the T of Resource<T>"));
        }
    }

    private void setImageContent(Object obj) {
        BaseTarget baseTarget = this.mTarget;
        if (baseTarget == null || obj == null) {
            setImageError(new Exception("can't parse image"));
            return;
        }
        if (obj instanceof Bitmap) {
            onResourceReady(transcode(transform((Bitmap) obj, baseTarget)), this.mTarget);
        } else if (obj instanceof BitmapDrawable) {
            onResourceReady(transformEncodeAndTranscode(((BitmapDrawable) obj).getBitmap(), this.mTarget), this.mTarget);
        } else {
            setImageError(new Exception(a.a("can deal with image result for the class type:", obj.getClass().getName())));
        }
    }

    private void setImageError(Exception exc) {
        BaseTarget baseTarget = this.mTarget;
        if (baseTarget != null) {
            baseTarget.onLoadFailed(exc, this.mErrorDrawable);
        }
    }

    private void setImagedPlaceholder() {
        BaseTarget baseTarget = this.mTarget;
        if (baseTarget != null) {
            baseTarget.onLoadStarted(this.mPlaceDrawable);
        }
    }

    private Resource<GlideBitmapDrawable> transcode(Resource<Bitmap> resource) {
        if (resource == null) {
            return null;
        }
        return new GlideBitmapDrawableTranscoder(this.mContext).transcode(resource);
    }

    private Resource<Bitmap> transform(Bitmap bitmap, BaseTarget baseTarget) {
        LogUtil.i(TAG, "transform(Bitmap bitmap, ImageView baseTarget):", bitmap, baseTarget);
        if (bitmap == null) {
            return null;
        }
        ImageView.ScaleType scaleType = this.mScaleType;
        if (scaleType != null) {
            int i = AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()];
            if (i == 1) {
                applyCenterCrop();
            } else if (i == 2 || i == 3 || i == 4) {
                applyFitCenter();
            }
        }
        if (this.mOutWidth <= 0 || this.mOutHeight <= 0) {
            this.mOutWidth = bitmap.getWidth();
            this.mOutHeight = bitmap.getHeight();
        }
        return transform(BitmapResource.obtain(bitmap, this.mGlide.getBitmapPool()));
    }

    private Resource<GlideBitmapDrawable> transformEncodeAndTranscode(Bitmap bitmap, BaseTarget baseTarget) {
        return transcode(transform(bitmap, baseTarget));
    }

    public void asBitmap() {
        this.mResourceType = 1;
    }

    @Override // com.ju.lib.utils.glide.GlideUtils.ILoadImageStatusListener
    public void loadFail(String str, Exception exc) {
        LogUtil.e(TAG, "loadFail(String url, Exception e):", str, exc);
        setImageError(exc);
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

    public void setTransformation(Transformation... transformationArr) {
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
