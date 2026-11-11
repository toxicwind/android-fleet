package coil3.size;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.exifinterface.media.ExifInterface;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* JADX INFO: compiled from: ViewSizeResolver.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J\u000e\u0010\u000b\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\rJ\n\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0002J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002J\"\u0010\u0012\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0002J\u0014\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u0012\u0010\u0004\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001cÀ\u0006\u0001"}, d2 = {"Lcoil3/size/ViewSizeResolver;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "Lcoil3/size/SizeResolver;", "view", "getView", "()Landroid/view/View;", "subtractPadding", "", "getSubtractPadding", "()Z", ContentDisposition.Parameters.Size, "Lcoil3/size/Size;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSize", "getWidth", "Lcoil3/size/Dimension;", "getHeight", "getDimension", "paramSize", "", "viewSize", "paddingSize", "removePreDrawListenerSafe", "", "Landroid/view/ViewTreeObserver;", "victim", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ViewSizeResolver<T extends View> extends SizeResolver {
    boolean getSubtractPadding();

    T getView();

    @Override // coil3.size.SizeResolver
    Object size(Continuation<? super Size> continuation);

    /* JADX INFO: renamed from: coil3.size.ViewSizeResolver$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ViewSizeResolver.kt */
    public final /* synthetic */ class CC {
        public static boolean $default$getSubtractPadding(ViewSizeResolver _this) {
            return true;
        }

        /* JADX WARN: Type inference failed for: r3v0, types: [coil3.size.ViewSizeResolver$size$3$preDrawListener$1] */
        public static /* synthetic */ <T extends View> Object size$suspendImpl(final ViewSizeResolver<T> viewSizeResolver, Continuation<? super Size> continuation) {
            Size size$private$getSize = $private$getSize(viewSizeResolver);
            if (size$private$getSize != null) {
                return size$private$getSize;
            }
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
            final ViewTreeObserver viewTreeObserver = viewSizeResolver.getView().getViewTreeObserver();
            final ?? r3 = new ViewTreeObserver.OnPreDrawListener() { // from class: coil3.size.ViewSizeResolver$size$3$preDrawListener$1
                private boolean isResumed;

                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    Size size$private$getSize2 = ViewSizeResolver.CC.$private$getSize(viewSizeResolver);
                    if (size$private$getSize2 != null) {
                        ViewSizeResolver.CC.$private$removePreDrawListenerSafe(viewSizeResolver, viewTreeObserver, this);
                        if (!this.isResumed) {
                            this.isResumed = true;
                            CancellableContinuation<Size> cancellableContinuation = cancellableContinuationImpl2;
                            Result.Companion companion = Result.INSTANCE;
                            cancellableContinuation.resumeWith(Result.m8936constructorimpl(size$private$getSize2));
                        }
                    }
                    return true;
                }
            };
            viewTreeObserver.addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener) r3);
            cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: coil3.size.ViewSizeResolver$size$3$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable th) {
                    ViewSizeResolver.CC.$private$removePreDrawListenerSafe(viewSizeResolver, viewTreeObserver, r3);
                }
            });
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }

        public static Size $private$getSize(ViewSizeResolver _this) {
            Dimension dimension$private$getHeight;
            Dimension dimension$private$getWidth = $private$getWidth(_this);
            if (dimension$private$getWidth == null || (dimension$private$getHeight = $private$getHeight(_this)) == null) {
                return null;
            }
            return new Size(dimension$private$getWidth, dimension$private$getHeight);
        }

        public static Dimension $private$getWidth(ViewSizeResolver _this) {
            ViewGroup.LayoutParams layoutParams = _this.getView().getLayoutParams();
            return $private$getDimension(_this, layoutParams != null ? layoutParams.width : -1, _this.getView().getWidth(), _this.getSubtractPadding() ? _this.getView().getPaddingLeft() + _this.getView().getPaddingRight() : 0);
        }

        public static Dimension $private$getHeight(ViewSizeResolver _this) {
            ViewGroup.LayoutParams layoutParams = _this.getView().getLayoutParams();
            return $private$getDimension(_this, layoutParams != null ? layoutParams.height : -1, _this.getView().getHeight(), _this.getSubtractPadding() ? _this.getView().getPaddingTop() + _this.getView().getPaddingBottom() : 0);
        }

        public static Dimension $private$getDimension(ViewSizeResolver _this, int i, int i2, int i3) {
            if (i == -2) {
                return Dimension.Undefined.INSTANCE;
            }
            int i4 = i - i3;
            if (i4 > 0) {
                return Dimension.Pixels.m8123boximpl(DimensionKt.Dimension(i4));
            }
            int i5 = i2 - i3;
            if (i5 > 0) {
                return Dimension.Pixels.m8123boximpl(DimensionKt.Dimension(i5));
            }
            return null;
        }

        public static void $private$removePreDrawListenerSafe(ViewSizeResolver _this, ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnPreDrawListener onPreDrawListener) {
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            } else {
                _this.getView().getViewTreeObserver().removeOnPreDrawListener(onPreDrawListener);
            }
        }
    }
}
