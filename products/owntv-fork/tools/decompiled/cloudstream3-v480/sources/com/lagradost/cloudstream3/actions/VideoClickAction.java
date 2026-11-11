package com.lagradost.cloudstream3.actions;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.core.app.ActivityOptionsCompat;
import androidx.exifinterface.media.ExifInterface;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: VideoClickAction.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0018\u001a\u0004\u0018\u0001H\u0019\"\u0004\b\u0000\u0010\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001bH\u0086@¢\u0006\u0002\u0010\u001cJ$\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"H\u0086@¢\u0006\u0002\u0010#J$\u0010$\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&H\u0086@¢\u0006\u0002\u0010'J\u0006\u0010(\u001a\u00020\u0013J\u001c\u0010)\u001a\u00020\t2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010-H&J\u001a\u0010.\u001a\u00020\t2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010-J2\u0010/\u001a\u00020\u001e2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-2\u0006\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103H¦@¢\u0006\u0002\u00104J/\u00105\u001a\u0002062\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-2\u0006\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103¢\u0006\u0002\u00107R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u00068"}, d2 = {"Lcom/lagradost/cloudstream3/actions/VideoClickAction;", "", "<init>", "()V", "name", "Lcom/lagradost/cloudstream3/utils/UiText;", "getName", "()Lcom/lagradost/cloudstream3/utils/UiText;", "oneSource", "", "getOneSource", "()Z", "isPlayer", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "getSourceTypes", "()Ljava/util/Set;", "sourcePlugin", "", "getSourcePlugin", "()Ljava/lang/String;", "setSourcePlugin", "(Ljava/lang/String;)V", "uiThread", ExifInterface.GPS_DIRECTION_TRUE, "callable", "Ljava/util/concurrent/Callable;", "(Ljava/util/concurrent/Callable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchResult", "", "intent", "Landroid/content/Intent;", "options", "Landroidx/core/app/ActivityOptionsCompat;", "(Landroid/content/Intent;Landroidx/core/app/ActivityOptionsCompat;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launch", "bundle", "Landroid/os/Bundle;", "(Landroid/content/Intent;Landroid/os/Bundle;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uniqueId", "shouldShow", "context", "Landroid/content/Context;", "video", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "shouldShowSafe", "runAction", "result", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "index", "", "(Landroid/content/Context;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runActionSafe", "Lkotlinx/coroutines/Job;", "(Landroid/content/Context;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;)Lkotlinx/coroutines/Job;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class VideoClickAction {
    private final boolean isPlayer;
    private final boolean oneSource;
    private String sourcePlugin;
    private final Set<ExtractorLinkType> sourceTypes = CollectionsKt.toSet(ExtractorLinkType.getEntries());

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.actions.VideoClickAction$uiThread$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: VideoClickAction.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.actions.VideoClickAction", f = "VideoClickAction.kt", i = {0, 0}, l = {131}, m = "uiThread", n = {"callable", "future"}, nl = {134}, s = {"L$0", "L$1"}, v = 2)
    static final class C07151<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07151(Continuation<? super C07151> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return VideoClickAction.this.uiThread(null, this);
        }
    }

    public abstract UiText getName();

    public abstract Object runAction(Context context, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation);

    public abstract boolean shouldShow(Context context, ResultEpisode video);

    public boolean getOneSource() {
        return this.oneSource;
    }

    /* JADX INFO: renamed from: isPlayer, reason: from getter */
    public boolean getIsPlayer() {
        return this.isPlayer;
    }

    public Set<ExtractorLinkType> getSourceTypes() {
        return this.sourceTypes;
    }

    public final String getSourcePlugin() {
        return this.sourcePlugin;
    }

    public final void setSourcePlugin(String str) {
        this.sourcePlugin = str;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final <T> Object uiThread(final Callable<T> callable, Continuation<? super T> continuation) throws ErrorLoadingException {
        C07151 c07151;
        if (continuation instanceof C07151) {
            c07151 = (C07151) continuation;
            if ((c07151.label & Integer.MIN_VALUE) != 0) {
                c07151.label -= Integer.MIN_VALUE;
            } else {
                c07151 = new C07151(continuation);
            }
        } else {
            c07151 = new C07151(continuation);
        }
        Object objWithContext = c07151.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07151.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objWithContext);
            FutureTask futureTask = new FutureTask(new Callable() { // from class: com.lagradost.cloudstream3.actions.VideoClickAction$$ExternalSyntheticLambda1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return VideoClickAction.uiThread$lambda$0(callable);
                }
            });
            Activity activity = CommonActivity.INSTANCE.getActivity();
            if (activity == null) {
                throw new ErrorLoadingException("No UI Activity, this should never happened");
            }
            activity.runOnUiThread(futureTask);
            CoroutineDispatcher io2 = Dispatchers.getIO();
            VideoClickAction$uiThread$result$1 videoClickAction$uiThread$result$1 = new VideoClickAction$uiThread$result$1(futureTask, null);
            c07151.L$0 = SpillingKt.nullOutSpilledVariable(callable);
            c07151.L$1 = SpillingKt.nullOutSpilledVariable(futureTask);
            c07151.label = 1;
            objWithContext = BuildersKt.withContext(io2, videoClickAction$uiThread$result$1, c07151);
            if (objWithContext == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objWithContext);
        }
        Result result = (Result) objWithContext;
        Intrinsics.checkNotNull(result);
        Object value = result.getValue();
        ResultKt.throwOnFailure(value);
        return value;
    }

    static final Result uiThread$lambda$0(Callable callable) {
        Object objM8936constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(callable.call());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        return Result.m8935boximpl(objM8936constructorimpl);
    }

    public static /* synthetic */ Object launchResult$default(VideoClickAction videoClickAction, Intent intent, ActivityOptionsCompat activityOptionsCompat, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: launchResult");
        }
        if ((i & 2) != 0) {
            activityOptionsCompat = null;
        }
        return videoClickAction.launchResult(intent, activityOptionsCompat, continuation);
    }

    public final Object launchResult(final Intent intent, final ActivityOptionsCompat activityOptionsCompat, Continuation<? super Unit> continuation) {
        if (intent == null) {
            return Unit.INSTANCE;
        }
        return uiThread(new Callable() { // from class: com.lagradost.cloudstream3.actions.VideoClickAction$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return VideoClickAction.launchResult$lambda$0(intent, activityOptionsCompat);
            }
        }, continuation);
    }

    static final Unit launchResult$lambda$0(Intent intent, ActivityOptionsCompat activityOptionsCompat) {
        ActivityResultLauncher<Intent> activityResultLauncher = MainActivity.INSTANCE.getActivityResultLauncher();
        if (activityResultLauncher == null) {
            return null;
        }
        activityResultLauncher.launch(intent, activityOptionsCompat);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object launch$default(VideoClickAction videoClickAction, Intent intent, Bundle bundle, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: launch");
        }
        if ((i & 2) != 0) {
            bundle = null;
        }
        return videoClickAction.launch(intent, bundle, continuation);
    }

    public final Object launch(final Intent intent, final Bundle bundle, Continuation<? super Unit> continuation) {
        if (intent == null) {
            return Unit.INSTANCE;
        }
        return uiThread(new Callable() { // from class: com.lagradost.cloudstream3.actions.VideoClickAction$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return VideoClickAction.launch$lambda$0(intent, bundle);
            }
        }, continuation);
    }

    static final Unit launch$lambda$0(Intent intent, Bundle bundle) {
        Activity activity = CommonActivity.INSTANCE.getActivity();
        if (activity == null) {
            return null;
        }
        activity.startActivity(intent, bundle);
        return Unit.INSTANCE;
    }

    public final String uniqueId() {
        return this.sourcePlugin + AbstractJsonLexerKt.COLON + Reflection.getOrCreateKotlinClass(getClass()).getQualifiedName();
    }

    public final boolean shouldShowSafe(Context context, ResultEpisode video) {
        try {
            return shouldShow(context, video);
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return false;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.actions.VideoClickAction$runActionSafe$1, reason: invalid class name */
    /* JADX INFO: compiled from: VideoClickAction.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/actions/VideoClickAction;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.actions.VideoClickAction$runActionSafe$1", f = "VideoClickAction.kt", i = {}, l = {192}, m = "invokeSuspend", n = {}, nl = {ByteCode.INSTANCEOF}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, VideoClickAction, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Integer $index;
        final /* synthetic */ LinkLoadingResult $result;
        final /* synthetic */ ResultEpisode $video;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$context = context;
            this.$video = resultEpisode;
            this.$result = linkLoadingResult;
            this.$index = num;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, VideoClickAction videoClickAction, Continuation<? super Unit> continuation) {
            return VideoClickAction.this.new AnonymousClass1(this.$context, this.$video, this.$result, this.$index, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r9v15 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    Object objRunAction = VideoClickAction.this.runAction(this.$context, this.$video, this.$result, this.$index, this);
                    this = objRunAction;
                    if (objRunAction == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    this = this;
                }
            } catch (ActivityNotFoundException unused) {
                CommonActivity.INSTANCE.showToast(R.string.app_not_found_error, Boxing.boxInt(1));
            } catch (ErrorLoadingException e) {
                CommonActivity.INSTANCE.showToast(e.getMessage(), Boxing.boxInt(1));
            } catch (NotImplementedError unused2) {
                CommonActivity.INSTANCE.showToast("runAction has not been implemented for " + VideoClickAction.this.getName().asStringNull(this.$context) + ", please contact the extension developer of " + VideoClickAction.this.getSourcePlugin(), Boxing.boxInt(1));
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
                CommonActivity.INSTANCE.showToast(th.toString(), Boxing.boxInt(1));
            }
            return Unit.INSTANCE;
        }
    }

    public final Job runActionSafe(Context context, ResultEpisode video, LinkLoadingResult result, Integer index) {
        Intrinsics.checkNotNullParameter(video, "video");
        Intrinsics.checkNotNullParameter(result, "result");
        return Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(context, video, result, index, null));
    }
}
