package com.lagradost.cloudstream3.utils.downloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableKt;
import coil3.Image;
import coil3.ImageLoader;
import coil3.Image_androidKt;
import coil3.request.ImageRequest;
import coil3.request.ImageResult;
import coil3.request.SuccessResult;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DownloadUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.DownloadUtils$getImageBitmapFromUrl$1$bitmap$1", f = "DownloadUtils.kt", i = {}, l = {48}, m = "invokeSuspend", n = {}, nl = {49}, s = {}, v = 2)
final class DownloadUtils$getImageBitmapFromUrl$1$bitmap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ ImageLoader $imageLoader;
    final /* synthetic */ ImageRequest $request;
    final /* synthetic */ Context $this_getImageBitmapFromUrl;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DownloadUtils$getImageBitmapFromUrl$1$bitmap$1(ImageLoader imageLoader, ImageRequest imageRequest, Context context, Continuation<? super DownloadUtils$getImageBitmapFromUrl$1$bitmap$1> continuation) {
        super(2, continuation);
        this.$imageLoader = imageLoader;
        this.$request = imageRequest;
        this.$this_getImageBitmapFromUrl = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DownloadUtils$getImageBitmapFromUrl$1$bitmap$1(this.$imageLoader, this.$request, this.$this_getImageBitmapFromUrl, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((DownloadUtils$getImageBitmapFromUrl$1$bitmap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Image image;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.$imageLoader.execute(this.$request, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        ImageResult imageResult = (ImageResult) obj;
        SuccessResult successResult = imageResult instanceof SuccessResult ? (SuccessResult) imageResult : null;
        if (successResult != null && (image = successResult.getImage()) != null) {
            Resources resources = this.$this_getImageBitmapFromUrl.getApplicationContext().getResources();
            Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
            Drawable drawableAsDrawable = Image_androidKt.asDrawable(image, resources);
            if (drawableAsDrawable != null) {
                return DrawableKt.toBitmap$default(drawableAsDrawable, 0, 0, null, 7, null);
            }
        }
        return null;
    }
}
