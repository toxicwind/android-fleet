package com.ju.lib.utils.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.GifRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.ju.lib.utils.log.LogUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class GlideUtils {
    public static final String ASSETS_IMAGE_PATH_PREFIX = "file:///android_asset/";
    public static final int ASSETS_IMAGE_PATH_TYPE = 2;
    public static final String COMPONENT_FLAG = "Component";
    public static final int DRAWABLE_IMAGE_PATH_TYPE = 1;
    public static final String LOCAL_IMAGE_PREFIX = "file://";
    public static final String RESOURCE_IMAGE_PATH_PREFIX = "file://resource:";
    public static final String STORAGE_FILE_PATH_PREFIX = "file://storage:";
    public static final int STORAGE_IMAGE_PATH_TYPE = 0;
    public static final String TAG = "GlideUtils";
    public static final int UNKNOWN_IMAGE_PATH_TYPE = -1;
    public static BitmapFactory.Options mOptions;
    public static IResourcesComponentHandle mResourcesComponentHandle;
    public static GlideRequestListener sGlideRequestListener;
    public static int sMaxHeight;
    public static int sMaxWidth;

    /* JADX INFO: renamed from: com.ju.lib.utils.glide.GlideUtils$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$ju$lib$utils$glide$GlideUtils$CornerType = new int[CornerType.values().length];

        static {
            try {
                $SwitchMap$com$ju$lib$utils$glide$GlideUtils$CornerType[CornerType.ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$glide$GlideUtils$CornerType[CornerType.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$glide$GlideUtils$CornerType[CornerType.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$glide$GlideUtils$CornerType[CornerType.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$glide$GlideUtils$CornerType[CornerType.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$glide$GlideUtils$CornerType[CornerType.LEFT_BOTTOM.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$glide$GlideUtils$CornerType[CornerType.LEFT_TOP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$glide$GlideUtils$CornerType[CornerType.RIGHT_TOP.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$ju$lib$utils$glide$GlideUtils$CornerType[CornerType.RIGHT_BOTTOM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public enum CornerType {
        NONE,
        ALL,
        LEFT_TOP,
        RIGHT_TOP,
        RIGHT_BOTTOM,
        LEFT_BOTTOM,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    interface ILoadImageStatusListener {
        void loadFail(String str, Exception exc);

        void loadSuccess(String str, Object obj);

        void startLoad(String str);
    }

    public interface IResourcesComponentHandle {
        Resources getComponentResources(String str);
    }

    public static void callBackLoadLocalImageFail(String str, String str2, Exception exc, ILoadImageStatusListener iLoadImageStatusListener) {
        if (exc == null && TextUtils.isEmpty(str2)) {
            exc = new Exception("unknown error");
        } else if (exc == null) {
            exc = new Exception(str2);
        }
        if (iLoadImageStatusListener != null) {
            iLoadImageStatusListener.loadFail(str, exc);
        }
    }

    public static void callBackLoadLocalImageStart(String str, ILoadImageStatusListener iLoadImageStatusListener) {
        if (iLoadImageStatusListener != null) {
            iLoadImageStatusListener.startLoad(str);
        }
    }

    public static void callBackLoadLocalImageSuccess(int i, String str, ILoadImageStatusListener iLoadImageStatusListener, Object obj) {
        if (iLoadImageStatusListener instanceof BaseLoadImageStatusListener) {
            ((BaseLoadImageStatusListener) iLoadImageStatusListener).setLoadImageType(i);
        }
        if (iLoadImageStatusListener != null) {
            iLoadImageStatusListener.loadSuccess(str, obj);
        }
    }

    public static boolean checkComponentNameValid(String str, String str2, ILoadImageStatusListener iLoadImageStatusListener) {
        if (!TextUtils.isEmpty(str2)) {
            return true;
        }
        callBackLoadLocalImageFail(str, "componentName is empty", null, iLoadImageStatusListener);
        logForDebug("checkComponentNameValid(", str2, iLoadImageStatusListener, ")", "componentName is empty");
        LogUtil.e(TAG, "checkComponentNameValid(", str2, iLoadImageStatusListener, ")", "componentName is empty");
        return false;
    }

    public static void clear(View view) {
        try {
            logForDebug("clear(", view, ")");
            Glide.clear(view);
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("clear(", view, ")", e2);
        }
    }

    public static void clearMemory(Context context) {
        try {
            logForDebug("clearMemory(", context, ")");
            Glide.get(context).clearMemory();
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("clearMemory(", context, ")", e2);
        }
    }

    public static void clipCorner(final CornerType cornerType, View view, final int i) {
        if (cornerType == null || view == null || cornerType == CornerType.NONE) {
            return;
        }
        int i2 = Build.VERSION.SDK_INT;
        view.setOutlineProvider(new ViewOutlineProvider() { // from class: com.ju.lib.utils.glide.GlideUtils.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline outline) {
                switch (cornerType.ordinal()) {
                    case 1:
                        outline.setRoundRect(0, 0, view2.getWidth(), view2.getHeight(), i);
                        break;
                    case 2:
                        int width = view2.getWidth() + i;
                        int height = view2.getHeight();
                        int i3 = i;
                        outline.setRoundRect(0, 0, width, height + i3, i3);
                        break;
                    case 3:
                        int i4 = -i;
                        int width2 = view2.getWidth();
                        int height2 = view2.getHeight();
                        int i5 = i;
                        outline.setRoundRect(i4, 0, width2, height2 + i5, i5);
                        break;
                    case 4:
                        int i6 = i;
                        outline.setRoundRect(-i6, -i6, view2.getWidth(), view2.getHeight(), i);
                        break;
                    case 5:
                        outline.setRoundRect(0, -i, i + view2.getWidth(), view2.getHeight(), i);
                        break;
                    case 6:
                        outline.setRoundRect(0, 0, view2.getWidth() + i, view2.getHeight(), i);
                        break;
                    case 7:
                        int width3 = view2.getWidth();
                        int height3 = view2.getHeight();
                        int i7 = i;
                        outline.setRoundRect(0, 0, width3, height3 + i7, i7);
                        break;
                    case 8:
                        outline.setRoundRect(-i, 0, view2.getWidth(), view2.getHeight(), i);
                        break;
                    case 9:
                        outline.setRoundRect(0, -i, view2.getWidth(), view2.getHeight(), i);
                        break;
                }
            }
        });
        view.setClipToOutline(true);
    }

    public static void dealWithComponent(String str, String str2, int i, String str3, ILoadImageStatusListener iLoadImageStatusListener) throws Throwable {
        if (i == 0) {
            dealWithComponentStorageImage(str, str2, str3, iLoadImageStatusListener);
            return;
        }
        if (i == 1) {
            dealWithComponentDrawableImage(str, str2, str3, iLoadImageStatusListener);
        } else {
            if (i == 2) {
                dealWithComponentAssetsImage(str, str2, str3, iLoadImageStatusListener);
                return;
            }
            callBackLoadLocalImageFail(str, "unknown component local Url type", null, iLoadImageStatusListener);
            LogUtil.e(TAG, "dealWithComponent(", Integer.valueOf(i), str3, iLoadImageStatusListener, ")", "unknown component local Url type");
            logForDebug("dealWithComponent(", Integer.valueOf(i), str3, iLoadImageStatusListener, ")", "unknown component local Url type");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x006c -> B:41:0x00ad). Please report as a decompilation issue!!! */
    public static void dealWithComponentAssetsImage(String str, String str2, String str3, ILoadImageStatusListener iLoadImageStatusListener) throws Throwable {
        Resources componentResources;
        Throwable th;
        ?? r10;
        Object[] objArr;
        if (checkComponentNameValid(str, str2, iLoadImageStatusListener) && (componentResources = getComponentResources(str, str2, iLoadImageStatusListener)) != null) {
            ?? r11 = 0;
            r11 = 0;
            r11 = 0;
            r11 = 0;
            try {
                try {
                    try {
                        InputStream inputStreamOpen = componentResources.getAssets().open(str3.substring(22, str3.length()));
                        try {
                            Bitmap bitmapDecodeStream = mOptions != null ? BitmapFactory.decodeStream(inputStreamOpen, null, mOptions) : BitmapFactory.decodeStream(inputStreamOpen);
                            if (bitmapDecodeStream == null) {
                                callBackLoadLocalImageFail(str, "load bitmap is null", null, iLoadImageStatusListener);
                                objArr = new Object[]{"dealWithComponentAssetsImage(", str2, str3, iLoadImageStatusListener, ")", "load bitmap is null"};
                                LogUtil.e(TAG, objArr);
                            } else {
                                callBackLoadLocalImageSuccess(2, str, iLoadImageStatusListener, bitmapDecodeStream);
                            }
                            if (inputStreamOpen != null) {
                                r11 = objArr;
                                inputStreamOpen.close();
                            }
                        } catch (IOException e2) {
                            e = e2;
                            r11 = inputStreamOpen;
                            e.printStackTrace();
                            callBackLoadLocalImageFail(str, "IOException", e, iLoadImageStatusListener);
                            logForDebug("dealWithComponentAssetsImage(", str2, str3, iLoadImageStatusListener, ")", "IOException");
                            LogUtil.e(TAG, "dealWithComponentAssetsImage(", str2, str3, iLoadImageStatusListener, ")", "IOException");
                            if (r11 != 0) {
                                r11.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            r10 = inputStreamOpen;
                            if (r10 == 0) {
                                throw th;
                            }
                            try {
                                r10.close();
                                throw th;
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                throw th;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        r10 = r11;
                    }
                } catch (IOException e4) {
                    e = e4;
                }
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            r11 = objArr;
        }
    }

    public static void dealWithComponentDrawableImage(String str, String str2, String str3, ILoadImageStatusListener iLoadImageStatusListener) {
        Resources componentResources;
        if (checkComponentNameValid(str, str2, iLoadImageStatusListener) && (componentResources = getComponentResources(str, str2, iLoadImageStatusListener)) != null) {
            String strSubstring = str3.substring(16, str3.length());
            try {
                Drawable drawable = componentResources.getDrawable(Integer.parseInt(strSubstring));
                if (drawable == null) {
                    callBackLoadLocalImageFail(str, "load resDrawable is null", null, iLoadImageStatusListener);
                } else {
                    callBackLoadLocalImageSuccess(1, str, iLoadImageStatusListener, drawable);
                }
            } catch (Exception e2) {
                String str4 = "get drawable error, id:" + strSubstring;
                callBackLoadLocalImageFail(str, str4, e2, iLoadImageStatusListener);
                logForDebug("dealWithComponentDrawableImage(", str2, str3, iLoadImageStatusListener, ")", str4);
                LogUtil.e(TAG, "dealWithComponentDrawableImage(", str2, str3, iLoadImageStatusListener, ")", str4);
            }
        }
    }

    public static void dealWithComponentStorageImage(String str, String str2, String str3, ILoadImageStatusListener iLoadImageStatusListener) {
        Bitmap bitmapDecodeFile;
        String strSubstring = str3.substring(15, str3.length());
        if (new File(strSubstring).exists()) {
            BitmapFactory.Options options = mOptions;
            bitmapDecodeFile = options != null ? BitmapFactory.decodeFile(strSubstring, options) : BitmapFactory.decodeFile(strSubstring);
        } else {
            bitmapDecodeFile = null;
        }
        if (bitmapDecodeFile == null) {
            callBackLoadLocalImageFail(str, "load bitmap is null", null, iLoadImageStatusListener);
        } else {
            callBackLoadLocalImageSuccess(0, str, iLoadImageStatusListener, bitmapDecodeFile);
        }
    }

    public static void dealWithComponentUrlId(Context context, int i, ILoadImageStatusListener iLoadImageStatusListener) {
        String strValueOf = String.valueOf(i);
        if (iLoadImageStatusListener != null) {
            iLoadImageStatusListener.startLoad(strValueOf);
        }
        logForDebug("dealWithComponentUrlId(", context, Integer.valueOf(i), iLoadImageStatusListener, ")");
        try {
            Drawable drawable = context.getResources().getDrawable(i);
            if (drawable != null) {
                callBackLoadLocalImageSuccess(1, strValueOf, iLoadImageStatusListener, drawable);
            } else {
                LogUtil.e(TAG, "dealWithComponentUrlId(", context, Integer.valueOf(i), iLoadImageStatusListener, ")", "load urlDrawable is null");
                callBackLoadLocalImageFail(strValueOf, "load urlDrawable is null", null, iLoadImageStatusListener);
            }
        } catch (Exception e2) {
            callBackLoadLocalImageFail(strValueOf, "context.getDrawable(urlId) error", e2, iLoadImageStatusListener);
            logForDebug("dealWithComponentUrlId(", context, Integer.valueOf(i), iLoadImageStatusListener, ")", "context.getDrawable(urlId) error");
            LogUtil.e(TAG, "dealWithComponentUrlId(", context, Integer.valueOf(i), iLoadImageStatusListener, ")", "context.getDrawable(urlId) error");
        }
    }

    public static void fillInBuilder(DrawableRequestBuilder drawableRequestBuilder, int i, int i2, int i3, int i4, Transformation<Bitmap>[] transformationArr, ImageView imageView, BaseTarget baseTarget) {
        if (i > 0) {
            drawableRequestBuilder.placeholder(i);
        }
        if (i2 > 0) {
            drawableRequestBuilder.error(i2);
        }
        if (i3 > 0 && i4 > 0) {
            drawableRequestBuilder.override(i3, i4);
        }
        if (transformationArr.length > 0) {
            drawableRequestBuilder.transform(transformationArr);
        }
        if (imageView != null) {
            drawableRequestBuilder.into(imageView);
        } else if ((i3 <= 0 || i4 <= 0) && transformationArr.length <= 0) {
            setOverride(drawableRequestBuilder).into(baseTarget);
        } else {
            drawableRequestBuilder.into(baseTarget);
        }
    }

    public static BitmapPool getBitmapPool(Context context) {
        BitmapPool bitmapPool = null;
        try {
            bitmapPool = Glide.get(context).getBitmapPool();
            logForDebug("getBitmapPool(", context, ")", bitmapPool);
            return bitmapPool;
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("getBitmapPool(", context, ")", e2);
            return bitmapPool;
        }
    }

    public static Resources getComponentResources(String str, String str2, ILoadImageStatusListener iLoadImageStatusListener) {
        IResourcesComponentHandle iResourcesComponentHandle = mResourcesComponentHandle;
        Resources componentResources = iResourcesComponentHandle != null ? iResourcesComponentHandle.getComponentResources(str2) : null;
        if (componentResources == null) {
            callBackLoadLocalImageFail(str, "can't get componentResources", null, iLoadImageStatusListener);
            logForDebug("getComponentResources(", str, str2, iLoadImageStatusListener, ")", "can't get componentResources");
            LogUtil.e(TAG, "getComponentResources(", str, str2, iLoadImageStatusListener, ")", "can't get componentResources");
        }
        return componentResources;
    }

    public static Drawable getDrawable(Context context, int i) {
        if (context == null) {
            printException("context is null");
            return null;
        }
        if (i <= 0) {
            return null;
        }
        return context.getResources().getDrawable(i);
    }

    public static DrawableRequestBuilder<String> getDrawableRequestBuilder(DrawableTypeRequest<String> drawableTypeRequest, Drawable drawable) {
        return drawable == null ? drawableTypeRequest.centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE) : drawableTypeRequest.placeholder(drawable).centerCrop().error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE);
    }

    @Nullable
    public static RequestListener getGlideRequestListener() {
        return sGlideRequestListener;
    }

    public static int getLocalUrlType(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (str.startsWith(ASSETS_IMAGE_PATH_PREFIX) && str.length() > 22) {
            return 2;
        }
        if (!str.startsWith(RESOURCE_IMAGE_PATH_PREFIX) || str.length() <= 16) {
            return (!str.startsWith(STORAGE_FILE_PATH_PREFIX) || str.length() <= 15) ? -1 : 0;
        }
        return 1;
    }

    public static void init(@NonNull IGlideExceptionHandler iGlideExceptionHandler) {
        sGlideRequestListener = new GlideRequestListener(iGlideExceptionHandler);
    }

    public static boolean isComponentLocalUrlValid(String[] strArr) {
        return strArr != null && strArr.length >= 3 && COMPONENT_FLAG.equals(strArr[0]);
    }

    public static boolean isLocalImageLoad(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(COMPONENT_FLAG);
    }

    public static void load(Context context, String str, Drawable drawable, ImageView imageView, Transformation<Bitmap>... transformationArr) {
        load(context, str, drawable, imageView, CornerType.NONE, 0, transformationArr);
    }

    public static <T> void loadAsBitMap(DrawableTypeRequest<T> drawableTypeRequest, Drawable drawable, Drawable drawable2, int i, int i2, Object obj, Transformation<Bitmap>... transformationArr) {
        if (drawableTypeRequest == null) {
            printException("request is null");
            return;
        }
        GlideRequestListener glideRequestListener = sGlideRequestListener;
        if (glideRequestListener != null) {
            drawableTypeRequest.listener(glideRequestListener);
        }
        load((GenericRequestBuilder) drawableTypeRequest.asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE), drawable, drawable2, i, i2, obj, transformationArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void loadDrawableWithTarget(Context context, String str, int i, int i2, int i3, BaseTarget baseTarget, BitmapTransformation bitmapTransformation) throws Throwable {
        char c2 = '\b';
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadDrawableWithTarget(", context, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), baseTarget, bitmapTransformation, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, null, drawable, drawable);
                    targetLoadImageStatusListener.setOverride(i2, i3);
                    targetLoadImageStatusListener.setTransformation(bitmapTransformation);
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (i2 > 0 && i3 > 0) {
                    drawableTypeRequestLoad.transform(new BitmapTransformation[]{bitmapTransformation}).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(drawable).error(drawable).into(baseTarget);
                    return;
                }
                drawableTypeRequestLoad.transform(new BitmapTransformation[]{bitmapTransformation}).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(drawable).error(drawable).into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            c2 = 1;
        }
        e.printStackTrace();
        Object[] objArr = new Object[10];
        objArr[0] = "loadDrawableWithTarget(";
        objArr[c2] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = Integer.valueOf(i2);
        objArr[5] = Integer.valueOf(i3);
        objArr[6] = baseTarget;
        objArr[7] = bitmapTransformation;
        objArr[8] = ")";
        objArr[9] = e;
        logForDebug(objArr);
    }

    public static void loadGifImageView(Fragment fragment, String str, int i, ImageView imageView) {
        try {
            try {
                Drawable drawable = getDrawable(fragment.getContext(), i);
                logForDebug("loadGifImageView(", fragment, str, Integer.valueOf(i), imageView, ")", drawable);
                loadGifImageView(fragment, str, drawable, imageView);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadGifImageView(", fragment, str, Integer.valueOf(i), imageView, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void loadGifWithTarget(Context context, String str, BaseTarget baseTarget, Transformation<Bitmap> transformation) {
        try {
            logForDebug("loadGifWithTarget(", context, str, baseTarget, transformation, ")");
            DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
            if (sGlideRequestListener != null) {
                drawableTypeRequestLoad.listener(sGlideRequestListener);
            }
            drawableTypeRequestLoad.asGif().transformFrame(new Transformation[]{transformation}).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(baseTarget);
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("loadGifWithTarget(", context, str, baseTarget, transformation, ")", e2);
        }
    }

    public static void loadImageAsBitmap(Context context, String str, int i, ImageView imageView) throws Throwable {
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageAsBitmap(", context, str, Integer.valueOf(i), imageView, ")", drawable);
            loadImageAsBitmap(context, str, drawable, imageView);
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("loadImageAsBitmap(", context, str, Integer.valueOf(i), imageView, ")", e2);
        }
    }

    public static void loadImageAsBitmapWithTarget(Context context, String str, int i, int i2, int i3, BaseTarget baseTarget) throws Throwable {
        int i4 = 7;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageAsBitmapWithTarget(", context, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), baseTarget, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, null, drawable, drawable);
                    targetLoadImageStatusListener.setOverride(i2, i3);
                    targetLoadImageStatusListener.asBitmap();
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (i2 > 0 && i3 > 0) {
                    drawableTypeRequestLoad.asBitmap().placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(i2, i3).error(drawable).into(baseTarget);
                    return;
                }
                setOverride(drawableTypeRequestLoad.asBitmap().placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(drawable)).into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i4 = 9;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i4];
        objArr[0] = "loadImageAsBitmapWithTarget(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = Integer.valueOf(i2);
        objArr[5] = Integer.valueOf(i3);
        objArr[6] = baseTarget;
        objArr[7] = ")";
        objArr[8] = e;
        logForDebug(objArr);
    }

    public static void loadImageCenterCrop(Context context, String str, int i, ImageView imageView) throws Throwable {
        int i2 = 5;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageCenterCrop(", context, str, Integer.valueOf(i), imageView, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, ImageView.ScaleType.CENTER_CROP, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).centerCrop().error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageCenterCrop(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageCenterCropWithTarget(Context context, String str, int i, BaseTarget baseTarget) throws Throwable {
        int i2 = 5;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageCenterCropWithTarget(", context, str, Integer.valueOf(i), baseTarget, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new TargetLoadImageStatusListener(context, baseTarget, ImageView.ScaleType.CENTER_CROP, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                (drawable == null ? drawableTypeRequestLoad.centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE) : drawableTypeRequestLoad.placeholder(drawable).centerCrop().error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE)).into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageCenterCropWithTarget(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = baseTarget;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageFitCenter(Context context, String str, int i, ImageView imageView) throws Throwable {
        int i2 = 5;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageFitCenter(", context, str, Integer.valueOf(i), imageView, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, ImageView.ScaleType.FIT_CENTER, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.fitCenter().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).fitCenter().error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageFitCenter(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageSkipMemory(Context context, String str, int i, ImageView imageView) throws Throwable {
        char c2 = 5;
        int i2 = 4;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageSkipMemory(", context, str, Integer.valueOf(i), imageView, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).skipMemoryCache(true).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            c2 = 1;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageSkipMemory(";
        objArr[c2] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageSkipMemoryWithTarget(Context context, String str, int i, int i2, BaseTarget baseTarget) throws Throwable {
        char c2 = 6;
        try {
            logForDebug("loadImageSkipMemoryWithTarget(", context, str, Integer.valueOf(i), Integer.valueOf(i2), baseTarget, ")");
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, null, null, null);
                    targetLoadImageStatusListener.setOverride(i, i2);
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (i > 0 && i2 > 0) {
                    drawableTypeRequestLoad.skipMemoryCache(true).override(i, i2).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(baseTarget);
                    return;
                }
                setOverride(drawableTypeRequestLoad.skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE)).into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            c2 = 1;
        }
        e.printStackTrace();
        Object[] objArr = new Object[8];
        objArr[0] = "loadImageSkipMemoryWithTarget(";
        objArr[c2] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = Integer.valueOf(i2);
        objArr[5] = baseTarget;
        objArr[6] = ")";
        objArr[7] = e;
        logForDebug(objArr);
    }

    public static void loadImageView(Context context, String str, int i, ImageView imageView) throws Throwable {
        loadImageView(context, str, i, imageView, CornerType.NONE, 0);
    }

    public static void loadImageViewForPoster(Context context, String str, int i, ImageView imageView) throws Throwable {
        int i2 = 5;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageViewForPoster(", context, str, Integer.valueOf(i), imageView, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.dontAnimate().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.dontAnimate().placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageViewForPoster(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageViewNoError(Context context, String str, int i, ImageView imageView) throws Throwable {
        int i2 = 5;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageViewNoError(", context, str, Integer.valueOf(i), imageView, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, null, drawable, null));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageViewNoError(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageViewNoPlace(Context context, String str, int i, ImageView imageView) throws Throwable {
        loadImageViewNoPlace(context, str, i, imageView, CornerType.NONE, 0);
    }

    public static void loadImageViewSkipCache(Context context, String str, int i, ImageView imageView) throws Throwable {
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageViewSkipCache(", context, str, Integer.valueOf(i), imageView, ")", drawable);
            loadImageViewSkipCache(context, str, drawable, imageView);
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("loadImageViewSkipCache(", context, str, Integer.valueOf(i), imageView, ")", e2);
        }
    }

    public static void loadImageViewWithLargeNoCache(Context context, String str, int i, ImageView imageView, int i2, int i3) throws Throwable {
        char c2 = 7;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageViewWithLargeNoCache(", context, str, Integer.valueOf(i), imageView, Integer.valueOf(i2), Integer.valueOf(i3), ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable);
                    imageViewLoadImageStatusListener.setOverride(i2, i3);
                    loadLocalImage(str, imageViewLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.override(i2, i3).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).override(i2, i3).error(drawable).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            c2 = 1;
        }
        e.printStackTrace();
        Object[] objArr = new Object[9];
        objArr[0] = "loadImageViewWithLargeNoCache(";
        objArr[c2] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = Integer.valueOf(i2);
        objArr[6] = Integer.valueOf(i3);
        objArr[7] = ")";
        objArr[8] = e;
        logForDebug(objArr);
    }

    public static void loadImageViewWithPlaceAndError(Context context, String str, int i, int i2, ImageView imageView) throws Throwable {
        int i3 = 6;
        try {
            Drawable drawable = getDrawable(context, i);
            Drawable drawable2 = getDrawable(context, i2);
            logForDebug("loadImageViewWithPlaceAndError(", context, str, Integer.valueOf(i), Integer.valueOf(i2), imageView, ")", drawable, drawable2);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable2));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).error(drawable2).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i3 = 8;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i3];
        objArr[0] = "loadImageViewWithPlaceAndError(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = Integer.valueOf(i2);
        objArr[5] = imageView;
        objArr[6] = ")";
        objArr[7] = e;
        logForDebug(objArr);
    }

    public static void loadImageViewWithThumb(Context context, String str, int i, float f2, ImageView imageView) {
        try {
            logForDebug("loadImageViewWithThumb(", context, str, Integer.valueOf(i), Float.valueOf(f2), imageView, ")");
            try {
                try {
                    Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).thumbnail(f2).crossFade(i).into(imageView);
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    logForDebug("loadImageViewWithThumb(", context, str, Integer.valueOf(i), Float.valueOf(f2), imageView, ")", e);
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    public static void loadImageViewWithoutClear(Context context, String str, int i, final ImageView imageView, BitmapTransformation... bitmapTransformationArr) throws Throwable {
        try {
            GlideDrawableImageViewTarget glideDrawableImageViewTarget = new GlideDrawableImageViewTarget(imageView) { // from class: com.ju.lib.utils.glide.GlideUtils.2
                public void onLoadCleared(Drawable drawable) {
                }

                public void onLoadStarted(Drawable drawable) {
                    super.onLoadStarted(drawable);
                }

                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, GlideAnimation glideAnimation) {
                    onResourceReady((GlideDrawable) obj, (GlideAnimation<? super GlideDrawable>) glideAnimation);
                }

                public void onResourceReady(GlideDrawable glideDrawable, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    super.onResourceReady(glideDrawable, glideAnimation);
                    imageView.setImageDrawable(glideDrawable);
                }
            };
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageViewWithoutClear(", context, str, Integer.valueOf(i), imageView, bitmapTransformationArr, ")", drawable);
            if (isLocalImageLoad(str)) {
                TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, glideDrawableImageViewTarget, null, drawable, null);
                targetLoadImageStatusListener.setTransformation(bitmapTransformationArr);
                loadLocalImage(str, targetLoadImageStatusListener);
                return;
            }
            DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
            if (sGlideRequestListener != null) {
                drawableTypeRequestLoad.listener(sGlideRequestListener);
            }
            try {
                load(drawableTypeRequestLoad, drawable, drawable, 0, 0, (Object) glideDrawableImageViewTarget, (Transformation<Bitmap>[]) bitmapTransformationArr);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageViewWithoutClear(", context, str, Integer.valueOf(i), imageView, bitmapTransformationArr, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v1 */
    public static void loadImageWithAll(Context context, String str, int i, int i2, int i3, RequestListener requestListener, BaseTarget baseTarget) throws Throwable {
        int i4 = ")";
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageWithAll(", context, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), requestListener, baseTarget, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, null, drawable, drawable);
                    targetLoadImageStatusListener.setOverride(i2, i3);
                    targetLoadImageStatusListener.setListener(requestListener);
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                if (i2 > 0 && i3 > 0) {
                    Glide.with(context).load(str).listener(requestListener).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(drawable).override(i2, i3).error(drawable).into(baseTarget);
                    return;
                }
                setOverride(Glide.with(context).load(str).listener(requestListener).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(drawable).error(drawable)).into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i4 = 10;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i4];
        objArr[0] = "loadImageWithAll(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = Integer.valueOf(i2);
        objArr[5] = Integer.valueOf(i3);
        objArr[6] = requestListener;
        objArr[7] = baseTarget;
        objArr[8] = ")";
        objArr[9] = e;
        logForDebug(objArr);
    }

    public static void loadImageWithCallback(Context context, String str, GlideDrawableImageViewTarget glideDrawableImageViewTarget) throws Throwable {
        logForDebug("loadImageWithCallback(", context, str, glideDrawableImageViewTarget, ")");
        if (isLocalImageLoad(str)) {
            loadLocalImage(str, new TargetLoadImageStatusListener(context, glideDrawableImageViewTarget, null, null, null));
        } else {
            Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(glideDrawableImageViewTarget);
        }
    }

    public static void loadImageWithListener(Context context, String str, int i, ImageView imageView, CornerType cornerType, int i2, RequestListener requestListener) throws Throwable {
        int i3 = 6;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageWithListener(", context, str, Integer.valueOf(i), imageView, requestListener, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable);
                    imageViewLoadImageStatusListener.setListener(requestListener);
                    loadLocalImage(str, imageViewLoadImageStatusListener);
                    return;
                } else {
                    if (drawable == null) {
                        Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).into(imageView);
                    } else {
                        Glide.with(context).load(str).placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).into(imageView);
                    }
                    clipCorner(cornerType, imageView, i2);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i3 = 8;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i3];
        objArr[0] = "loadImageWithListener(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = requestListener;
        objArr[6] = ")";
        objArr[7] = e;
        logForDebug(objArr);
    }

    public static void loadImageWithListenerNoPlaceWithError(Context context, String str, int i, ImageView imageView, RequestListener requestListener) throws Throwable {
        int i2 = 6;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageWithListenerNoPlaceWithError(", context, str, Integer.valueOf(i), imageView, requestListener, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, null, drawable);
                    imageViewLoadImageStatusListener.setListener(requestListener);
                    loadLocalImage(str, imageViewLoadImageStatusListener);
                    return;
                } else if (drawable == null) {
                    Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).into(imageView);
                    return;
                } else {
                    Glide.with(context).load(str).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 8;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageWithListenerNoPlaceWithError(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = requestListener;
        objArr[6] = ")";
        objArr[7] = e;
        logForDebug(objArr);
    }

    public static void loadImageWithTarget(Context context, String str, int i, BaseTarget baseTarget) throws Throwable {
        int i2 = 5;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageWithTarget(", context, str, Integer.valueOf(i), baseTarget, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new TargetLoadImageStatusListener(context, baseTarget, null, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                setOverride(drawable == null ? drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.SOURCE) : drawableTypeRequestLoad.placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE)).into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageWithTarget(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = baseTarget;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageWithTargetAsBitmap(Context context, String str, int i, int i2, int i3, BaseTarget baseTarget) throws Throwable {
        int i4 = 8;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageWithTargetAsBitmap(", context, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), baseTarget, baseTarget, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, null, drawable, drawable);
                    targetLoadImageStatusListener.setOverride(i2, i3);
                    targetLoadImageStatusListener.asBitmap();
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (i2 > 0 && i3 > 0) {
                    drawableTypeRequestLoad.asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(drawable).override(i2, i3).error(drawable).into(baseTarget);
                    return;
                }
                setOverride(drawableTypeRequestLoad.asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(drawable).error(drawable)).into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i4 = 10;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i4];
        objArr[0] = "loadImageWithTargetAsBitmap(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = Integer.valueOf(i2);
        objArr[5] = Integer.valueOf(i3);
        objArr[6] = baseTarget;
        objArr[7] = baseTarget;
        objArr[8] = ")";
        objArr[9] = e;
        logForDebug(objArr);
    }

    public static void loadLocalImage(String str, ILoadImageStatusListener iLoadImageStatusListener) throws Throwable {
        if (iLoadImageStatusListener != null) {
            iLoadImageStatusListener.startLoad(str);
        }
        if (TextUtils.isEmpty(str)) {
            callBackLoadLocalImageFail(str, "url is empty", null, iLoadImageStatusListener);
            LogUtil.i(TAG, "loadLocalImage(", str, iLoadImageStatusListener, ")", "url is empty");
            logForDebug("loadLocalImage(", str, iLoadImageStatusListener, ")", "url is empty");
            return;
        }
        String[] strArrSplit = str.split("@");
        boolean zIsComponentLocalUrlValid = isComponentLocalUrlValid(strArrSplit);
        LogUtil.i(TAG, "componentLocalUrlValid:", Boolean.valueOf(zIsComponentLocalUrlValid));
        if (zIsComponentLocalUrlValid) {
            int localUrlType = getLocalUrlType(strArrSplit[2]);
            LogUtil.i(TAG, "localUrlType:", Integer.valueOf(localUrlType));
            dealWithComponent(str, strArrSplit[1], localUrlType, strArrSplit[2], iLoadImageStatusListener);
        } else {
            callBackLoadLocalImageFail(str, "can't parse url to load image", null, iLoadImageStatusListener);
            logForDebug("loadLocalImage(", str, iLoadImageStatusListener, ")", "can't parse url to load image");
            LogUtil.e(TAG, "loadLocalImage(", str, iLoadImageStatusListener, ")", "can't parse url to load image");
        }
    }

    public static void loadNoPlace(Context context, String str, Drawable drawable, ImageView imageView, Transformation<Bitmap>... transformationArr) {
        try {
            logForDebug("loadNoPlace(", context, str, drawable, imageView, transformationArr, ")");
            DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
            if (sGlideRequestListener != null) {
                drawableTypeRequestLoad.listener(sGlideRequestListener);
            }
            if (drawable == null) {
                if (transformationArr.length == 0) {
                    drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.bitmapTransform(transformationArr).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            }
            if (transformationArr.length == 0) {
                drawableTypeRequestLoad.error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            } else {
                drawableTypeRequestLoad.bitmapTransform(transformationArr).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("loadNoPlace(", context, str, drawable, imageView, transformationArr, ")", e2);
        }
    }

    public static void logForDebug(Object... objArr) {
        if (!Log.isLoggable(TAG, 3) || objArr == null || objArr.length == 0) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Object obj : objArr) {
            stringBuffer.append(obj);
            stringBuffer.append(", ");
        }
        Log.d(TAG, stringBuffer.toString());
    }

    public static void onDestroy(Context context) {
        try {
            logForDebug("onDestroy(", context, ")");
            Glide.with(context).onDestroy();
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("onDestroy(", context, ")", e2);
        }
    }

    public static void pauseRequests(Context context) {
        try {
            logForDebug("pauseRequests(", context, ")");
            Glide.with(context).pauseRequests();
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("pauseRequests(", context, ")", e2);
        }
    }

    public static void pauseRequestsRecursive(Context context) {
        try {
            logForDebug("pauseRequestsRecursive(", context, ")");
            Glide.with(context).pauseRequestsRecursive();
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("pauseRequestsRecursive(", context, ")", e2);
        }
    }

    public static void preloadPicture(Context context, String str, int i) {
        try {
            DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
            if (sGlideRequestListener != null) {
                drawableTypeRequestLoad.listener(sGlideRequestListener);
            }
            Drawable drawable = getDrawable(context, i);
            logForDebug("preloadPicture(", context, str, Integer.valueOf(i), ")", drawable);
            if (drawable == null) {
                drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.SOURCE).preload();
            } else {
                drawableTypeRequestLoad.placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).preload();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("preloadPicture(", context, str, Integer.valueOf(i), ")", e2);
        }
    }

    public static void printException(String str) {
        LogUtil.e(TAG, NotificationCompat.CATEGORY_MESSAGE);
        new Exception(str).printStackTrace();
    }

    public static void resumeRequests(Context context) {
        try {
            logForDebug("resumeRequests(", context, ")");
            Glide.with(context).resumeRequests();
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("resumeRequests(", context, ")", e2);
        }
    }

    public static void resumeRequestsRecursive(Context context) {
        try {
            logForDebug("resumeRequestsRecursive(", context, ")");
            Glide.with(context).resumeRequestsRecursive();
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("resumeRequestsRecursive(", context, ")", e2);
        }
    }

    public static void setLocalLoadBitmapOption(BitmapFactory.Options options) {
        mOptions = options;
    }

    public static <T> DrawableRequestBuilder<T> setOverride(DrawableRequestBuilder<T> drawableRequestBuilder) {
        int i;
        int i2 = sMaxWidth;
        return (i2 <= 0 || (i = sMaxHeight) <= 0) ? drawableRequestBuilder : drawableRequestBuilder.override(i2, i);
    }

    public static void setResourcesHandle(IResourcesComponentHandle iResourcesComponentHandle) {
        mResourcesComponentHandle = iResourcesComponentHandle;
    }

    public static Bitmap syncLoadImage(Context context, String str, long j) {
        try {
            logForDebug("syncLoadImage(", context, str, Long.valueOf(j), ")");
            return (Bitmap) Glide.with(context).load(str).asBitmap().into(Integer.MIN_VALUE, Integer.MIN_VALUE).get(j, TimeUnit.MILLISECONDS);
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("syncLoadImage(", context, str, Long.valueOf(j), ")", e2);
            return null;
        }
    }

    public static void init(@NonNull IGlideExceptionHandler iGlideExceptionHandler, int i, int i2) {
        sGlideRequestListener = new GlideRequestListener(iGlideExceptionHandler);
        sMaxWidth = i;
        sMaxHeight = i2;
    }

    public static void load(Context context, String str, Drawable drawable, ImageView imageView, CornerType cornerType, int i, Transformation<Bitmap>... transformationArr) {
        try {
            logForDebug("load(", context, str, drawable, imageView, transformationArr, ")");
            DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
            if (sGlideRequestListener != null) {
                drawableTypeRequestLoad.listener(sGlideRequestListener);
            }
            if (drawable == null) {
                if (transformationArr.length == 0) {
                    drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                } else {
                    drawableTypeRequestLoad.bitmapTransform(transformationArr).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                }
            } else if (transformationArr.length == 0) {
                drawableTypeRequestLoad.placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            } else {
                drawableTypeRequestLoad.placeholder(drawable).bitmapTransform(transformationArr).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            }
            clipCorner(cornerType, imageView, i);
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("load(", context, str, drawable, imageView, transformationArr, ")", e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r18v1 */
    /* JADX WARN: Type inference failed for: r18v3, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object[]] */
    public static void loadImageView(Context context, String str, int i, ImageView imageView, CornerType cornerType, int i2) throws Throwable {
        ?? ValueOf;
        int i3 = 6;
        try {
            Drawable drawable = getDrawable(context, i);
            LogUtil.i(TAG, "loadImageView(", context, str, Integer.valueOf(i), imageView, ")", drawable);
            boolean zIsLocalImageLoad = isLocalImageLoad(str);
            ValueOf = Boolean.valueOf(zIsLocalImageLoad);
            LogUtil.i(TAG, new Object[]{"localImageLoad:", ValueOf});
            try {
                if (zIsLocalImageLoad) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                }
                clipCorner(cornerType, imageView, i2);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            ValueOf = 2;
            i3 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i3];
        objArr[0] = "loadImageView(";
        objArr[1] = context;
        objArr[ValueOf] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageViewNoPlace(Context context, String str, int i, ImageView imageView, CornerType cornerType, int i2) throws Throwable {
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageViewNoPlace(", context, str, Integer.valueOf(i), imageView, ")", drawable);
            try {
                loadImageViewNoPlace(context, str, drawable, imageView, cornerType, i2);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageViewNoPlace(", context, str, Integer.valueOf(i), imageView, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static <T> BitmapRequestBuilder<T, Bitmap> setOverride(BitmapRequestBuilder<T, Bitmap> bitmapRequestBuilder) {
        int i;
        int i2 = sMaxWidth;
        return (i2 <= 0 || (i = sMaxHeight) <= 0) ? bitmapRequestBuilder : bitmapRequestBuilder.override(i2, i);
    }

    public static void clear(BaseTarget baseTarget) {
        try {
            logForDebug("clear(", baseTarget, ")");
            Glide.clear(baseTarget);
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("clear(", baseTarget, ")", e2);
        }
    }

    public static <T> GenericRequestBuilder setOverride(GifRequestBuilder<T> gifRequestBuilder) {
        int i;
        int i2 = sMaxWidth;
        return (i2 <= 0 || (i = sMaxHeight) <= 0) ? gifRequestBuilder : gifRequestBuilder.override(i2, i);
    }

    public static void loadGifImageView(Fragment fragment, String str, Drawable drawable, ImageView imageView) {
        try {
            DrawableTypeRequest drawableTypeRequestLoad = Glide.with(fragment).load(str);
            if (sGlideRequestListener != null) {
                drawableTypeRequestLoad.listener(sGlideRequestListener);
            }
            logForDebug("loadGifImageView(", fragment, str, drawable, imageView, ")", drawable);
            if (drawable == null) {
                drawableTypeRequestLoad.asGif().dontAnimate().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            } else {
                drawableTypeRequestLoad.asGif().dontAnimate().placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("loadGifImageView(", fragment, str, drawable, imageView, ")", e2);
        }
    }

    public static void loadImageAsBitmap(Context context, String str, Drawable drawable, ImageView imageView) throws Throwable {
        int i = 4;
        try {
            logForDebug("loadImageAsBitmap(", context, str, imageView, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.asBitmap().placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i = 6;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i];
        objArr[0] = "loadImageAsBitmap(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = imageView;
        objArr[4] = ")";
        objArr[5] = e;
        logForDebug(objArr);
    }

    public static void loadImageViewSkipCache(Context context, String str, Drawable drawable, ImageView imageView) throws Throwable {
        int i = 4;
        try {
            logForDebug("loadImageViewSkipCache(", context, str, drawable, imageView, ")");
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i = 6;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i];
        objArr[0] = "loadImageViewSkipCache(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = imageView;
        objArr[4] = ")";
        objArr[5] = e;
        logForDebug(objArr);
    }

    public static void loadImageViewNoPlace(Context context, String str, Drawable drawable, ImageView imageView, CornerType cornerType, int i) throws Throwable {
        int i2 = 4;
        try {
            logForDebug("loadImageViewNoPlace(", context, str, imageView, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, null, null, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                } else {
                    drawableTypeRequestLoad.error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                }
                clipCorner(cornerType, imageView, i);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 6;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageViewNoPlace(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = imageView;
        objArr[4] = ")";
        objArr[5] = e;
        logForDebug(objArr);
    }

    public static GenericRequestBuilder setOverride(GenericRequestBuilder genericRequestBuilder) {
        int i;
        int i2 = sMaxWidth;
        return (i2 <= 0 || (i = sMaxHeight) <= 0) ? genericRequestBuilder : genericRequestBuilder.override(i2, i);
    }

    public static void loadImageViewNoError(Context context, String str, int i, ImageView imageView, Transformation<Bitmap>... transformationArr) throws Throwable {
        loadImageViewNoError(context, str, i, imageView, CornerType.NONE, 0, transformationArr);
    }

    public static void loadImageViewForPoster(Context context, String str, Drawable drawable, ImageView imageView) throws Throwable {
        int i = 4;
        try {
            logForDebug("loadImageViewForPoster(", context, str, imageView, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.dontAnimate().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.dontAnimate().placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i = 6;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i];
        objArr[0] = "loadImageViewForPoster(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = imageView;
        objArr[4] = ")";
        objArr[5] = e;
        logForDebug(objArr);
    }

    public static void loadImageViewNoError(Context context, String str, int i, ImageView imageView, CornerType cornerType, int i2, Transformation<Bitmap>... transformationArr) throws Throwable {
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageViewNoError(", context, str, Integer.valueOf(i), imageView, transformationArr, ")", drawable);
            try {
                loadImageViewNoError(context, str, drawable, imageView, cornerType, i2, transformationArr);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageViewNoError(", context, str, Integer.valueOf(i), imageView, transformationArr, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void loadImageCenterCropWithTarget(Context context, String str, int i, BaseTarget baseTarget, Transformation<Bitmap>... transformationArr) throws Throwable {
        int i2 = 5;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageCenterCropWithTarget(", context, str, Integer.valueOf(i), baseTarget, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, ImageView.ScaleType.CENTER_CROP, drawable, drawable);
                    targetLoadImageStatusListener.setTransformation(transformationArr);
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (transformationArr != null) {
                    drawableTypeRequestLoad.transform(transformationArr).into(baseTarget);
                    return;
                } else {
                    getDrawableRequestBuilder(drawableTypeRequestLoad, drawable).into(baseTarget);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageCenterCropWithTarget(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = baseTarget;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageFitCenter(Context context, String str, int i, ImageView imageView, int i2, int i3) throws Throwable {
        int i4 = 7;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageFitCenter(", context, str, Integer.valueOf(i), imageView, Integer.valueOf(i2), Integer.valueOf(i3), ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, ImageView.ScaleType.FIT_CENTER, drawable, drawable);
                    imageViewLoadImageStatusListener.setOverride(i2, i3);
                    loadLocalImage(str, imageViewLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.override(i2, i3).fitCenter().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).override(i2, i3).fitCenter().error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i4 = 9;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i4];
        objArr[0] = "loadImageFitCenter(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = Integer.valueOf(i2);
        objArr[6] = Integer.valueOf(i3);
        objArr[7] = ")";
        objArr[8] = e;
        logForDebug(objArr);
    }

    public static void loadImageWithListener(Context context, String str, Drawable drawable, ImageView imageView, RequestListener requestListener) throws Throwable {
        loadImageWithListener(context, str, drawable, imageView, CornerType.NONE, 0, requestListener, (Transformation<Bitmap>[]) new Transformation[0]);
    }

    public static void loadImageWithTarget(Context context, String str, int i, int i2, int i3, BaseTarget baseTarget) throws Throwable {
        int i4 = 7;
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageWithTarget(", context, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), baseTarget, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, null, drawable, drawable);
                    targetLoadImageStatusListener.setOverride(i2, i3);
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (i2 > 0 && i3 > 0) {
                    drawableTypeRequestLoad.placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(i2, i3).error(drawable).into(baseTarget);
                    return;
                }
                setOverride(drawableTypeRequestLoad.placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(drawable)).into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i4 = 9;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i4];
        objArr[0] = "loadImageWithTarget(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = Integer.valueOf(i2);
        objArr[5] = Integer.valueOf(i3);
        objArr[6] = baseTarget;
        objArr[7] = ")";
        objArr[8] = e;
        logForDebug(objArr);
    }

    public static void loadGifImageView(Context context, String str, int i, ImageView imageView) {
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadGifImageView(", context, str, Integer.valueOf(i), imageView, ")", drawable);
            loadGifImageView(context, str, drawable, imageView);
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("loadGifImageView(", context, str, Integer.valueOf(i), imageView, ")", e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r7v1 */
    public static void loadImageWithAll(Context context, String str, int i, int i2, int i3, RequestListener requestListener, BaseTarget baseTarget, Transformation<Bitmap>... transformationArr) throws Throwable {
        DrawableRequestBuilder drawableRequestBuilderError;
        int i4 = context;
        try {
            Drawable drawable = getDrawable(i4, i);
            logForDebug("loadImageWithAll(", i4, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), requestListener, baseTarget, transformationArr, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, null, drawable, drawable);
                    targetLoadImageStatusListener.setOverride(i2, i3);
                    targetLoadImageStatusListener.setListener(requestListener);
                    targetLoadImageStatusListener.setTransformation(transformationArr);
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                if (i2 > 0 && i3 > 0) {
                    if (transformationArr.length == 0) {
                        Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).placeholder(drawable).override(i2, i3).error(drawable).into(baseTarget);
                        return;
                    } else {
                        Glide.with(context).load(str).bitmapTransform(transformationArr).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).placeholder(drawable).override(i2, i3).error(drawable).into(baseTarget);
                        return;
                    }
                }
                if (transformationArr.length == 0) {
                    drawableRequestBuilderError = Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).placeholder(drawable).error(drawable);
                } else {
                    drawableRequestBuilderError = Glide.with(context).load(str).bitmapTransform(transformationArr).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).placeholder(drawable).error(drawable);
                }
                drawableRequestBuilderError.into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i4 = 11;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i4];
        objArr[0] = "loadImageWithAll(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = Integer.valueOf(i2);
        objArr[5] = Integer.valueOf(i3);
        objArr[6] = requestListener;
        objArr[7] = baseTarget;
        objArr[8] = transformationArr;
        objArr[9] = ")";
        objArr[10] = e;
        logForDebug(objArr);
    }

    public static void loadImageWithListener(Context context, String str, int i, ImageView imageView, RequestListener requestListener) throws Throwable {
        loadImageWithListener(context, str, i, imageView, CornerType.NONE, 0, requestListener);
    }

    public static void loadImageView(Context context, Uri uri, int i, ImageView imageView) throws Throwable {
        int i2 = 4;
        try {
            Drawable drawable = getDrawable(context, i);
            LogUtil.i(TAG, "loadImageView(", context, uri, Integer.valueOf(i), imageView, ")", drawable);
            String path = uri.getPath();
            try {
                if (isLocalImageLoad(path)) {
                    loadLocalImage(path, new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(uri);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageView(";
        objArr[1] = context;
        objArr[2] = uri;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2, types: [int] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    public static void loadImageViewWithLargeNoCache(Context context, Uri uri, int i, ImageView imageView, int i2, int i3) throws Throwable {
        ?? r2;
        int i4 = ")";
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageViewWithLargeNoCache(", context, uri, Integer.valueOf(i), imageView, Integer.valueOf(i2), Integer.valueOf(i3), ")", drawable);
            String path = uri.getPath();
            try {
                if (isLocalImageLoad(path)) {
                    try {
                        ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable);
                        imageViewLoadImageStatusListener.setOverride(i2, i3);
                        loadLocalImage(path, imageViewLoadImageStatusListener);
                        return;
                    } catch (Exception e2) {
                        e = e2;
                        i4 = 9;
                        r2 = 1;
                    }
                } else {
                    i4 = 9;
                    DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(uri);
                    GlideRequestListener glideRequestListener = sGlideRequestListener;
                    if (glideRequestListener != null) {
                        r2 = glideRequestListener;
                        GlideRequestListener glideRequestListener2 = sGlideRequestListener;
                        drawableTypeRequestLoad.listener(glideRequestListener2);
                        r2 = glideRequestListener2;
                    }
                    try {
                        r2 = glideRequestListener;
                        if (drawable == null) {
                            r2 = 1;
                            drawableTypeRequestLoad.override(i2, i3).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                        } else {
                            r2 = 1;
                            drawableTypeRequestLoad.placeholder(drawable).override(i2, i3).error(drawable).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                        }
                        return;
                    } catch (Exception e3) {
                        e = e3;
                    }
                }
            } catch (Exception e4) {
                e = e4;
                r2 = 1;
            }
        } catch (Exception e5) {
            e = e5;
            r2 = 1;
            i4 = 9;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i4];
        objArr[0] = "loadImageViewWithLargeNoCache(";
        objArr[r2] = context;
        objArr[2] = uri;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = Integer.valueOf(i2);
        objArr[6] = Integer.valueOf(i3);
        objArr[7] = ")";
        objArr[8] = e;
        logForDebug(objArr);
    }

    public static void loadImageWithListener(Context context, String str, int i, ImageView imageView, CornerType cornerType, int i2, RequestListener requestListener, Transformation<Bitmap>... transformationArr) throws Throwable {
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageWithListener(", context, str, Integer.valueOf(i), imageView, requestListener, ")", drawable);
            try {
                loadImageWithListener(context, str, drawable, imageView, cornerType, i2, requestListener, transformationArr);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageWithListener(", context, str, Integer.valueOf(i), imageView, requestListener, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void load(DrawableTypeRequest<?> drawableTypeRequest, Context context, int i, ImageView imageView, Transformation<Bitmap>... transformationArr) {
        load(drawableTypeRequest, context, i, imageView, CornerType.NONE, 0, transformationArr);
    }

    public static void loadImageAsBitmap(Context context, String str, int i, ImageView imageView, Transformation<Bitmap>... transformationArr) throws Throwable {
        loadImageAsBitmap(context, str, i, imageView, CornerType.NONE, 0, transformationArr);
    }

    public static void load(DrawableTypeRequest<?> drawableTypeRequest, Context context, int i, ImageView imageView, CornerType cornerType, int i2, Transformation<Bitmap>... transformationArr) {
        load(drawableTypeRequest, context, getDrawable(context, i), imageView, cornerType, i2, transformationArr);
    }

    public static void loadImageAsBitmap(Context context, String str, int i, ImageView imageView, CornerType cornerType, int i2, Transformation<Bitmap>... transformationArr) throws Throwable {
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageAsBitmap(", context, str, Integer.valueOf(i), imageView, transformationArr, ")", drawable);
            try {
                loadImageAsBitmap(context, str, drawable, imageView, cornerType, i2, transformationArr);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageAsBitmap(", context, str, Integer.valueOf(i), imageView, transformationArr, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void loadImageViewNoError(Context context, String str, Drawable drawable, ImageView imageView, CornerType cornerType, int i, Transformation<Bitmap>... transformationArr) throws Throwable {
        int i2 = 5;
        try {
            logForDebug("loadImageViewNoError(", context, str, imageView, transformationArr, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, drawable, null);
                    imageViewLoadImageStatusListener.setTransformation(transformationArr);
                    loadLocalImage(str, imageViewLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    if (transformationArr.length == 0) {
                        drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    } else {
                        drawableTypeRequestLoad.bitmapTransform(transformationArr).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    }
                } else if (transformationArr.length == 0) {
                    drawableTypeRequestLoad.placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                } else {
                    drawableTypeRequestLoad.bitmapTransform(transformationArr).placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                }
                clipCorner(cornerType, imageView, i);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageViewNoError(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = imageView;
        objArr[4] = transformationArr;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageViewNoPlace(Fragment fragment, String str, int i, ImageView imageView, Transformation<Bitmap>... transformationArr) throws Throwable {
        try {
            if (isLocalImageLoad(str)) {
                try {
                    Drawable drawable = getDrawable(fragment.getContext(), i);
                    logForDebug("loadImageViewNoPlace(", fragment, str, Integer.valueOf(i), imageView, transformationArr, ")", drawable);
                    ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(fragment.getContext(), imageView, null, null, drawable);
                    imageViewLoadImageStatusListener.setTransformation(transformationArr);
                    loadLocalImage(str, imageViewLoadImageStatusListener);
                    return;
                } catch (Exception e2) {
                    e = e2;
                }
            } else {
                try {
                    load(fragment, str, 0, i, 0, 0, imageView, transformationArr);
                    return;
                } catch (Exception e3) {
                    e = e3;
                }
            }
        } catch (Exception e4) {
            e = e4;
        }
        e.printStackTrace();
        logForDebug("loadImageViewNoPlace(", fragment, str, Integer.valueOf(i), imageView, transformationArr, ")", e);
    }

    public static void loadImageWithTargetAsBitmap(Context context, String str, int i, int i2, int i3, BaseTarget baseTarget, Transformation<Bitmap>... transformationArr) throws Throwable {
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageWithTargetAsBitmap(", context, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), baseTarget, transformationArr, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, null, drawable, drawable);
                    targetLoadImageStatusListener.setOverride(i2, i3);
                    targetLoadImageStatusListener.setTransformation(transformationArr);
                    targetLoadImageStatusListener.asBitmap();
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                try {
                    loadAsBitMap(Glide.with(context).load(str), drawable, drawable, i2, i3, baseTarget, transformationArr);
                    return;
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e = e4;
        }
        e.printStackTrace();
        logForDebug("loadImageWithTargetAsBitmap(", context, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), baseTarget, transformationArr, ")", e);
    }

    public static void loadDrawableWithTarget(Context context, String str, int i, int i2, int i3, BaseTarget baseTarget) throws Throwable {
        loadDrawableWithTarget(context, str, i, i2, i3, baseTarget, null);
    }

    public static void load(DrawableTypeRequest<?> drawableTypeRequest, Context context, Drawable drawable, ImageView imageView, CornerType cornerType, int i, Transformation<Bitmap>... transformationArr) {
        GlideRequestListener glideRequestListener = sGlideRequestListener;
        if (glideRequestListener != null) {
            drawableTypeRequest.listener(glideRequestListener);
        }
        if (drawable == null) {
            if (transformationArr.length == 0) {
                drawableTypeRequest.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            } else {
                drawableTypeRequest.bitmapTransform(transformationArr).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            }
        } else if (transformationArr.length == 0) {
            drawableTypeRequest.placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        } else {
            drawableTypeRequest.bitmapTransform(transformationArr).placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
        }
        clipCorner(cornerType, imageView, i);
    }

    public static void loadGifImageView(Context context, String str, Drawable drawable, ImageView imageView) {
        try {
            DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
            if (sGlideRequestListener != null) {
                drawableTypeRequestLoad.listener(sGlideRequestListener);
            }
            logForDebug("loadGifImageView(", context, str, drawable, imageView, ")");
            if (drawable == null) {
                drawableTypeRequestLoad.asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            } else {
                drawableTypeRequestLoad.asGif().placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("loadGifImageView(", context, str, imageView, ")", e2);
        }
    }

    public static void loadImageWithListener(Context context, String str, Drawable drawable, ImageView imageView, CornerType cornerType, int i, RequestListener requestListener, Transformation<Bitmap>... transformationArr) throws Throwable {
        int i2 = 5;
        try {
            logForDebug("loadImageWithListener(", context, str, imageView, requestListener, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable);
                    imageViewLoadImageStatusListener.setListener(requestListener);
                    imageViewLoadImageStatusListener.setTransformation(transformationArr);
                    loadLocalImage(str, imageViewLoadImageStatusListener);
                    return;
                }
                if (drawable == null) {
                    if (transformationArr.length == 0) {
                        Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).into(imageView);
                    } else {
                        Glide.with(context).load(str).bitmapTransform(transformationArr).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).into(imageView);
                    }
                } else if (transformationArr.length == 0) {
                    Glide.with(context).load(str).placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).into(imageView);
                } else {
                    Glide.with(context).load(str).bitmapTransform(transformationArr).placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(requestListener).into(imageView);
                }
                clipCorner(cornerType, imageView, i);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageWithListener(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = imageView;
        objArr[4] = requestListener;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageAsBitmap(Context context, String str, Drawable drawable, ImageView imageView, CornerType cornerType, int i, Transformation<Bitmap>... transformationArr) throws Throwable {
        int i2 = 5;
        try {
            logForDebug("loadImageAsBitmap(", context, str, imageView, transformationArr, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable);
                    imageViewLoadImageStatusListener.setTransformation(transformationArr);
                    loadLocalImage(str, imageViewLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    if (transformationArr.length == 0) {
                        drawableTypeRequestLoad.asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    } else {
                        drawableTypeRequestLoad.asBitmap().transform(transformationArr).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    }
                } else if (transformationArr.length == 0) {
                    drawableTypeRequestLoad.asBitmap().placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                } else {
                    drawableTypeRequestLoad.asBitmap().transform(transformationArr).placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                }
                clipCorner(cornerType, imageView, i);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 7;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageAsBitmap(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = imageView;
        objArr[4] = transformationArr;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageViewNoPlace(Context context, String str, int i, ImageView imageView, Transformation<Bitmap>... transformationArr) throws Throwable {
        int i2 = 6;
        try {
            try {
                if (isLocalImageLoad(str)) {
                    Drawable drawable = getDrawable(context, i);
                    logForDebug("loadImageViewNoPlace(", context, str, Integer.valueOf(i), imageView, transformationArr, ")", drawable);
                    ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, null, drawable);
                    imageViewLoadImageStatusListener.setTransformation(transformationArr);
                    loadLocalImage(str, imageViewLoadImageStatusListener);
                    return;
                }
                loadNoPlace(context, str, getDrawable(context, i), imageView, transformationArr);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i2 = 8;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i2];
        objArr[0] = "loadImageViewNoPlace(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = imageView;
        objArr[5] = transformationArr;
        objArr[6] = ")";
        objArr[7] = e;
        logForDebug(objArr);
    }

    public static void loadGifImageView(Context context, String str, int i, int i2, int i3, ImageView imageView) {
        try {
            DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
            if (sGlideRequestListener != null) {
                drawableTypeRequestLoad.listener(sGlideRequestListener);
            }
            logForDebug("loadGifImageView(", context, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), imageView, ")");
            try {
                if (i2 > 0 && i3 > 0) {
                    Drawable drawable = getDrawable(context, i);
                    drawableTypeRequestLoad.asGif().placeholder(drawable).override(i2, i3).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                } else {
                    drawableTypeRequestLoad.asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadGifImageView(", context, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), imageView, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void loadImageView(Fragment fragment, String str, int i, ImageView imageView) throws Throwable {
        try {
            try {
                Drawable drawable = getDrawable(fragment.getContext(), i);
                logForDebug("loadImageView(", fragment, str, Integer.valueOf(i), imageView, ")", drawable);
                loadImageView(fragment, str, drawable, imageView);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageView(", fragment, str, Integer.valueOf(i), imageView, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void loadImageWithTarget(Fragment fragment, String str, int i, int i2, int i3, BaseTarget baseTarget) throws Throwable {
        int i4 = 7;
        try {
            Drawable drawable = getDrawable(fragment.getContext(), i);
            logForDebug("loadImageWithTarget(", fragment, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), baseTarget, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(fragment.getContext(), baseTarget, null, drawable, drawable);
                    targetLoadImageStatusListener.setOverride(i2, i3);
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(fragment).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (i2 > 0 && i3 > 0) {
                    drawableTypeRequestLoad.placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(i2, i3).error(drawable).into(baseTarget);
                    return;
                }
                setOverride(drawableTypeRequestLoad.placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(drawable)).into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i4 = 9;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i4];
        objArr[0] = "loadImageWithTarget(";
        objArr[1] = fragment;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = Integer.valueOf(i2);
        objArr[5] = Integer.valueOf(i3);
        objArr[6] = baseTarget;
        objArr[7] = ")";
        objArr[8] = e;
        logForDebug(objArr);
    }

    public static void loadImageWithTargetAsBitmap(Context context, String str, Drawable drawable, int i, int i2, BaseTarget baseTarget, Transformation<Bitmap>... transformationArr) throws Throwable {
        logForDebug("loadImageWithTargetAsBitmap(", context, str, drawable, Integer.valueOf(i), Integer.valueOf(i2), baseTarget, transformationArr, ")", drawable);
        try {
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, null, drawable, drawable);
                    targetLoadImageStatusListener.setOverride(i, i2);
                    targetLoadImageStatusListener.setTransformation(transformationArr);
                    targetLoadImageStatusListener.asBitmap();
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                try {
                    loadAsBitMap(Glide.with(context).load(str), drawable, drawable, i, i2, baseTarget, transformationArr);
                    return;
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e = e4;
        }
        e.printStackTrace();
        logForDebug("loadImageWithTargetAsBitmap(", context, str, drawable, Integer.valueOf(i), Integer.valueOf(i2), baseTarget, transformationArr, ")", e);
    }

    public static void load(Fragment fragment, String str, int i, int i2, int i3, int i4, Object obj, Transformation<Bitmap>... transformationArr) {
        if (fragment == null) {
            printException("context is null");
        } else {
            logForDebug("load(", fragment, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), obj, transformationArr, ")");
            load((DrawableTypeRequest<?>) Glide.with(fragment).load(str), i, i2, i3, i4, obj, transformationArr);
        }
    }

    public static void loadImageView(Fragment fragment, String str, Drawable drawable, ImageView imageView) throws Throwable {
        int i = 4;
        try {
            logForDebug("loadImageView(", fragment, str, imageView, ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(fragment.getContext(), imageView, null, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(fragment).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i = 6;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i];
        objArr[0] = "loadImageView(";
        objArr[1] = fragment;
        objArr[2] = str;
        objArr[3] = imageView;
        objArr[4] = ")";
        objArr[5] = e;
        logForDebug(objArr);
    }

    public static void load(DrawableTypeRequest<?> drawableTypeRequest, int i, int i2, int i3, int i4, Object obj, Transformation<Bitmap>... transformationArr) {
        ImageView imageView;
        BaseTarget baseTarget;
        if (drawableTypeRequest == null) {
            printException("request is null");
            return;
        }
        GlideRequestListener glideRequestListener = sGlideRequestListener;
        if (glideRequestListener != null) {
            drawableTypeRequest.listener(glideRequestListener);
        }
        DrawableRequestBuilder drawableRequestBuilderDiskCacheStrategy = drawableTypeRequest.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        if (drawableRequestBuilderDiskCacheStrategy == null) {
            printException("builder is null");
            return;
        }
        if (obj instanceof BaseTarget) {
            baseTarget = (BaseTarget) obj;
            imageView = null;
        } else if (obj instanceof ImageView) {
            imageView = (ImageView) obj;
            baseTarget = null;
        } else {
            printException("null == view && null == target");
            return;
        }
        try {
            fillInBuilder(drawableRequestBuilderDiskCacheStrategy, i, i2, i3, i4, transformationArr, imageView, baseTarget);
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("load(", drawableTypeRequest, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), obj, transformationArr, ")", e2);
        }
    }

    public static void loadGifImageView(Context context, int i, int i2, ImageView imageView) {
        try {
            DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(Integer.valueOf(i));
            if (sGlideRequestListener != null) {
                drawableTypeRequestLoad.listener(sGlideRequestListener);
            }
            try {
                Drawable drawable = getDrawable(context, i2);
                logForDebug("loadGifImageView(", context, Integer.valueOf(i), Integer.valueOf(i2), imageView, ")", drawable);
                if (drawable == null) {
                    drawableTypeRequestLoad.asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                } else {
                    drawableTypeRequestLoad.asGif().placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadGifImageView(", context, Integer.valueOf(i), Integer.valueOf(i2), imageView, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void loadImageWithListener(Context context, String str, int i, ImageView imageView, RequestListener requestListener, Transformation... transformationArr) throws Throwable {
        loadImageWithListener(context, str, i, imageView, CornerType.NONE, 0, requestListener, (Transformation<Bitmap>[]) transformationArr);
    }

    public static void loadImageWithTarget(Fragment fragment, String str, int i, int i2, int i3, BaseTarget baseTarget, Transformation<Bitmap>... transformationArr) throws Throwable {
        try {
            if (isLocalImageLoad(str)) {
                try {
                    try {
                        Drawable drawable = getDrawable(fragment.getContext(), i);
                        logForDebug("loadImageWithTarget(", fragment, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), baseTarget, transformationArr, ")", drawable);
                        TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(fragment.getContext(), baseTarget, null, drawable, drawable);
                        targetLoadImageStatusListener.setOverride(i2, i3);
                        targetLoadImageStatusListener.setTransformation(transformationArr);
                        loadLocalImage(str, targetLoadImageStatusListener);
                        return;
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        logForDebug("loadImageWithTarget(", fragment, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), baseTarget, transformationArr, ")", e);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } else {
                try {
                    load(fragment, str, i, i, i2, i3, baseTarget, transformationArr);
                    return;
                } catch (Exception e4) {
                    e = e4;
                }
            }
        } catch (Exception e5) {
            e = e5;
        }
        e.printStackTrace();
        logForDebug("loadImageWithTarget(", fragment, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), baseTarget, transformationArr, ")", e);
    }

    public static void loadImageView(Context context, String str, Drawable drawable, ImageView imageView) throws Throwable {
        loadImageView(context, str, drawable, imageView, CornerType.NONE, 0);
    }

    public static void loadImageView(Context context, String str, Drawable drawable, ImageView imageView, CornerType cornerType, int i) throws Throwable {
        try {
            logForDebug("loadImageView(", context, str, drawable, imageView, ")");
            try {
                if (isLocalImageLoad(str)) {
                    loadLocalImage(str, new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable));
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                drawableTypeRequestLoad.placeholder(drawable).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                clipCorner(cornerType, imageView, i);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
        }
        e.printStackTrace();
        logForDebug("loadImageView(", context, str, drawable, imageView, ")", e);
    }

    public static <T> void load(DrawableTypeRequest<T> drawableTypeRequest, Drawable drawable, Drawable drawable2, int i, int i2, Object obj, Transformation<Bitmap>... transformationArr) {
        if (drawableTypeRequest == null) {
            printException("request is null");
            return;
        }
        GlideRequestListener glideRequestListener = sGlideRequestListener;
        if (glideRequestListener != null) {
            drawableTypeRequest.listener(glideRequestListener);
        }
        load((GenericRequestBuilder) drawableTypeRequest.diskCacheStrategy(DiskCacheStrategy.SOURCE), drawable, drawable2, i, i2, obj, transformationArr);
    }

    public static void load(GenericRequestBuilder genericRequestBuilder, Drawable drawable, Drawable drawable2, int i, int i2, Object obj, Transformation<Bitmap>... transformationArr) {
        ImageView imageView;
        if (genericRequestBuilder == null) {
            printException("builder is null");
            return;
        }
        Target target = null;
        if (obj instanceof BaseTarget) {
            target = (BaseTarget) obj;
            imageView = null;
        } else if (obj instanceof ImageView) {
            imageView = (ImageView) obj;
        } else {
            printException("null == view && null == target");
            return;
        }
        if (drawable != null) {
            try {
                genericRequestBuilder.placeholder(drawable);
            } catch (Exception e2) {
                e2.printStackTrace();
                logForDebug("load(", genericRequestBuilder, drawable, drawable2, Integer.valueOf(i), Integer.valueOf(i2), obj, transformationArr, ")", e2);
                return;
            }
        }
        if (drawable2 != null) {
            genericRequestBuilder.error(drawable2);
        }
        if (i > 0 && i2 > 0) {
            genericRequestBuilder.override(i, i2);
        }
        if (transformationArr.length > 0) {
            genericRequestBuilder.transform(transformationArr);
        }
        if (imageView != null) {
            genericRequestBuilder.into(imageView);
        } else if ((i > 0 && i2 > 0) || transformationArr.length > 0) {
            genericRequestBuilder.into(target);
        } else {
            setOverride(genericRequestBuilder).into(target);
        }
    }

    public static void loadImageWithTarget(Context context, String str, int i, int i2, int i3, int i4, BaseTarget baseTarget) throws Throwable {
        int i5 = 8;
        try {
            Drawable drawable = getDrawable(context, i);
            Drawable drawable2 = getDrawable(context, i2);
            logForDebug("loadImageWithTarget(", context, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), baseTarget, ")", drawable, drawable2);
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, null, drawable, drawable2);
                    targetLoadImageStatusListener.setOverride(i3, i4);
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (i3 > 0 && i4 > 0) {
                    drawableTypeRequestLoad.placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(i3, i4).error(drawable2).into(baseTarget);
                    return;
                }
                setOverride(drawableTypeRequestLoad.placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(drawable2)).into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i5 = 10;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i5];
        objArr[0] = "loadImageWithTarget(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = Integer.valueOf(i2);
        objArr[5] = Integer.valueOf(i3);
        objArr[6] = Integer.valueOf(i4);
        objArr[7] = baseTarget;
        objArr[8] = ")";
        objArr[9] = e;
        logForDebug(objArr);
    }

    public static void loadImageView(Context context, int i, int i2, ImageView imageView) {
        int i3;
        try {
            Drawable drawable = getDrawable(context, i2);
            logForDebug("loadImageView(", context, Integer.valueOf(i), Integer.valueOf(i2), imageView, ")", drawable);
            i3 = 7;
            try {
                try {
                    dealWithComponentUrlId(context, i, new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable));
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    Object[] objArr = new Object[i3];
                    objArr[0] = "loadImageView(";
                    objArr[1] = context;
                    objArr[2] = Integer.valueOf(i);
                    objArr[3] = Integer.valueOf(i2);
                    objArr[4] = imageView;
                    objArr[5] = ")";
                    objArr[6] = e;
                    logForDebug(objArr);
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e = e4;
            i3 = 7;
        }
    }

    public static void loadImageView(Context context, int i, int i2, ImageView imageView, int i3, int i4) {
        int i5;
        try {
            Drawable drawable = getDrawable(context, i2);
            logForDebug("loadImageView(", context, Integer.valueOf(i), Integer.valueOf(i2), imageView, Integer.valueOf(i3), Integer.valueOf(i4), ")", drawable);
            i5 = 9;
            try {
                ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable);
                try {
                    imageViewLoadImageStatusListener.setOverride(i3, i4);
                    try {
                        dealWithComponentUrlId(context, i, imageViewLoadImageStatusListener);
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        Object[] objArr = new Object[i5];
                        objArr[0] = "loadImageView(";
                        objArr[1] = context;
                        objArr[2] = Integer.valueOf(i);
                        objArr[3] = Integer.valueOf(i2);
                        objArr[4] = imageView;
                        objArr[5] = Integer.valueOf(i3);
                        objArr[6] = Integer.valueOf(i4);
                        objArr[7] = ")";
                        objArr[8] = e;
                        logForDebug(objArr);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Exception e5) {
            e = e5;
            i5 = 9;
        }
    }

    public static void loadImageView(Context context, String str, int i, ImageView imageView, int i2, int i3) throws Throwable {
        try {
            Drawable drawable = getDrawable(context, i);
            logForDebug("loadImageView(", context, str, Integer.valueOf(i), imageView, Integer.valueOf(i2), Integer.valueOf(i3), ")", drawable);
            try {
                loadImageView(context, str, drawable, imageView, i2, i3);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageView(", context, str, Integer.valueOf(i), imageView, Integer.valueOf(i2), Integer.valueOf(i3), ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void loadImageWithTarget(Context context, String str, BaseTarget baseTarget, BitmapTransformation bitmapTransformation) throws Throwable {
        char c2 = 6;
        try {
            logForDebug("loadImageWithTarget(", context, str, baseTarget, bitmapTransformation, ")");
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(context, baseTarget, null, null, null);
                    targetLoadImageStatusListener.setTransformation(bitmapTransformation);
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                } else {
                    DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                    if (sGlideRequestListener != null) {
                        drawableTypeRequestLoad.listener(sGlideRequestListener);
                    }
                    drawableTypeRequestLoad.transform(new BitmapTransformation[]{bitmapTransformation}).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(baseTarget);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            c2 = 1;
        }
        e.printStackTrace();
        Object[] objArr = new Object[7];
        objArr[0] = "loadImageWithTarget(";
        objArr[c2] = context;
        objArr[2] = str;
        objArr[3] = baseTarget;
        objArr[4] = bitmapTransformation;
        objArr[5] = ")";
        objArr[6] = e;
        logForDebug(objArr);
    }

    public static void loadImageView(Context context, String str, Drawable drawable, ImageView imageView, int i, int i2) throws Throwable {
        int i3 = 8;
        try {
            logForDebug("loadImageView(", context, str, drawable, imageView, Integer.valueOf(i), Integer.valueOf(i2), ")", drawable);
            try {
                if (isLocalImageLoad(str)) {
                    ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable);
                    imageViewLoadImageStatusListener.setOverride(i, i2);
                    loadLocalImage(str, imageViewLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(context).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (drawable == null) {
                    drawableTypeRequestLoad.override(i, i2).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                } else {
                    drawableTypeRequestLoad.placeholder(drawable).override(i, i2).error(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
                    return;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i3 = 9;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i3];
        objArr[0] = "loadImageView(";
        objArr[1] = context;
        objArr[2] = str;
        objArr[3] = drawable;
        objArr[4] = imageView;
        objArr[5] = Integer.valueOf(i);
        objArr[6] = Integer.valueOf(i2);
        objArr[7] = ")";
        objArr[8] = e;
        logForDebug(objArr);
    }

    public static void loadImageWithTarget(Fragment fragment, String str, int i, int i2, int i3, int i4, BaseTarget baseTarget) throws Throwable {
        try {
            Drawable drawable = getDrawable(fragment.getContext(), i);
            logForDebug("loadImageWithTarget(", fragment, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), baseTarget, ")", drawable);
            try {
                loadImageWithTarget(fragment, str, drawable, i2, i3, i4, baseTarget);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageWithTarget(", fragment, str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), baseTarget, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void loadImageView(Fragment fragment, String str, int i, ImageView imageView, Transformation<Bitmap>... transformationArr) throws Throwable {
        try {
            if (isLocalImageLoad(str)) {
                try {
                    Drawable drawable = getDrawable(fragment.getContext(), i);
                    logForDebug("loadImageView(", fragment, str, Integer.valueOf(i), imageView, transformationArr, ")", drawable);
                    ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(fragment.getContext(), imageView, null, drawable, drawable);
                    imageViewLoadImageStatusListener.setTransformation(transformationArr);
                    loadLocalImage(str, imageViewLoadImageStatusListener);
                    return;
                } catch (Exception e2) {
                    e = e2;
                }
            } else {
                try {
                    load(fragment, str, i, i, 0, 0, imageView, transformationArr);
                    return;
                } catch (Exception e3) {
                    e = e3;
                }
            }
        } catch (Exception e4) {
            e = e4;
        }
        e.printStackTrace();
        logForDebug("loadImageView(", fragment, str, Integer.valueOf(i), imageView, transformationArr, ")", e);
    }

    public static void loadImageWithTarget(Fragment fragment, String str, Drawable drawable, int i, int i2, int i3, BaseTarget baseTarget) throws Throwable {
        int i4 = 7;
        try {
            Drawable drawable2 = getDrawable(fragment.getContext(), i);
            logForDebug("loadImageWithTarget(", fragment, str, drawable, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), baseTarget, ")", drawable, drawable2);
            try {
                if (isLocalImageLoad(str)) {
                    TargetLoadImageStatusListener targetLoadImageStatusListener = new TargetLoadImageStatusListener(fragment.getContext(), baseTarget, null, drawable, drawable2);
                    targetLoadImageStatusListener.setOverride(i2, i3);
                    loadLocalImage(str, targetLoadImageStatusListener);
                    return;
                }
                DrawableTypeRequest drawableTypeRequestLoad = Glide.with(fragment).load(str);
                if (sGlideRequestListener != null) {
                    drawableTypeRequestLoad.listener(sGlideRequestListener);
                }
                if (i2 > 0 && i3 > 0) {
                    drawableTypeRequestLoad.placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(i2, i3).error(drawable2).into(baseTarget);
                    return;
                }
                setOverride(drawableTypeRequestLoad.placeholder(drawable).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(drawable2)).into(baseTarget);
                return;
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            i4 = 9;
        }
        e.printStackTrace();
        Object[] objArr = new Object[i4];
        objArr[0] = "loadImageWithTarget(";
        objArr[1] = fragment;
        objArr[2] = str;
        objArr[3] = Integer.valueOf(i);
        objArr[4] = Integer.valueOf(i2);
        objArr[5] = Integer.valueOf(i3);
        objArr[6] = baseTarget;
        objArr[7] = ")";
        objArr[8] = e;
        logForDebug(objArr);
    }

    public static void loadImageView(Context context, String str, int i, ImageView imageView, Transformation<Bitmap>... transformationArr) throws Throwable {
        loadImageView(context, str, i, imageView, CornerType.NONE, 0, transformationArr);
    }

    public static void loadImageView(Context context, String str, int i, ImageView imageView, CornerType cornerType, int i2, Transformation<Bitmap>... transformationArr) throws Throwable {
        try {
            if (isLocalImageLoad(str)) {
                Drawable drawable = getDrawable(context, i);
                logForDebug("loadImageView(", context, str, Integer.valueOf(i), imageView, transformationArr, ")", drawable);
                ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable);
                imageViewLoadImageStatusListener.setTransformation(transformationArr);
                loadLocalImage(str, imageViewLoadImageStatusListener);
                return;
            }
            try {
                load(context, str, getDrawable(context, i), imageView, cornerType, i2, transformationArr);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageView(", context, str, Integer.valueOf(i), imageView, transformationArr, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void loadImageView(Context context, String str, Drawable drawable, ImageView imageView, Transformation<Bitmap>... transformationArr) throws Throwable {
        loadImageView(context, str, drawable, imageView, CornerType.NONE, 0, transformationArr);
    }

    public static void loadImageView(Context context, String str, Drawable drawable, ImageView imageView, CornerType cornerType, int i, Transformation<Bitmap>... transformationArr) throws Throwable {
        logForDebug("loadImageView(", context, str, drawable, imageView, transformationArr, ")");
        try {
            if (isLocalImageLoad(str)) {
                ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable);
                imageViewLoadImageStatusListener.setTransformation(transformationArr);
                loadLocalImage(str, imageViewLoadImageStatusListener);
                return;
            }
            load(context, str, drawable, imageView, cornerType, i, transformationArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            logForDebug("loadImageView(", context, str, drawable, imageView, transformationArr, ")", e2);
        }
    }

    public static void loadImageView(Context context, byte[] bArr, int i, ImageView imageView, Transformation<Bitmap>... transformationArr) {
        loadImageView(context, bArr, i, imageView, CornerType.NONE, 0, transformationArr);
    }

    public static void loadImageView(Context context, byte[] bArr, int i, ImageView imageView, CornerType cornerType, int i2, Transformation<Bitmap>... transformationArr) {
        try {
            logForDebug("loadImageView(", context, bArr, Integer.valueOf(i), imageView, transformationArr, ")");
            try {
                load((DrawableTypeRequest<?>) Glide.with(context).load(bArr), context, i, imageView, cornerType, i2, transformationArr);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageView(", context, bArr, Integer.valueOf(i), imageView, transformationArr, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void loadImageView(Context context, int i, int i2, ImageView imageView, Transformation<Bitmap>... transformationArr) {
        char c2;
        try {
            logForDebug("loadImageView(", context, Integer.valueOf(i), Integer.valueOf(i2), imageView, transformationArr, ")");
            Drawable drawable = getDrawable(context, i2);
            c2 = 7;
            try {
                ImageViewLoadImageStatusListener imageViewLoadImageStatusListener = new ImageViewLoadImageStatusListener(context, imageView, null, drawable, drawable);
                imageViewLoadImageStatusListener.setTransformation(transformationArr);
                try {
                    dealWithComponentUrlId(context, i, imageViewLoadImageStatusListener);
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    Object[] objArr = new Object[8];
                    objArr[0] = "loadImageView(";
                    objArr[1] = context;
                    objArr[2] = Integer.valueOf(i);
                    objArr[3] = Integer.valueOf(i2);
                    objArr[4] = imageView;
                    objArr[5] = transformationArr;
                    objArr[6] = ")";
                    objArr[c2] = e;
                    logForDebug(objArr);
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
            e = e4;
            c2 = 7;
        }
    }

    public static void loadImageView(Context context, int i, int i2, ImageView imageView, CornerType cornerType, int i3, Transformation<Bitmap>... transformationArr) {
        try {
            logForDebug("loadImageView(", context, Integer.valueOf(i), Integer.valueOf(i2), imageView, transformationArr, ")");
            getDrawable(context, i2);
            try {
                load((DrawableTypeRequest<?>) Glide.with(context).load(getDrawable(context, i)), context, i2, imageView, cornerType, i3, transformationArr);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageView(", context, Integer.valueOf(i), Integer.valueOf(i2), imageView, transformationArr, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static void loadImageView(Context context, int i, Drawable drawable, ImageView imageView, CornerType cornerType, int i2, Transformation<Bitmap>... transformationArr) {
        try {
            logForDebug("loadImageView(", context, Integer.valueOf(i), imageView, transformationArr, ")");
            try {
                load((DrawableTypeRequest<?>) Glide.with(context).load(getDrawable(context, i)), context, drawable, imageView, cornerType, i2, transformationArr);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                logForDebug("loadImageView(", context, Integer.valueOf(i), imageView, transformationArr, ")", e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }
}
