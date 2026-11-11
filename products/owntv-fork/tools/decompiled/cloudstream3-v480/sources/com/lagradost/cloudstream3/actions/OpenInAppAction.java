package com.lagradost.cloudstream3.actions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OpenInAppAction.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010\u0013\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J2\u0010\u0018\u001a\u00020\u00192\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0096@¢\u0006\u0002\u0010\u001eJ8\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH¦@¢\u0006\u0002\u0010\"J\u001a\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\b\u0010 \u001a\u0004\u0018\u00010!H&J\u0018\u0010&\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\b\u0010 \u001a\u0004\u0018\u00010!R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0012¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/actions/OpenInAppAction;", "Lcom/lagradost/cloudstream3/actions/VideoClickAction;", "appName", "Lcom/lagradost/cloudstream3/utils/UiText;", "packageName", "", "intentClass", "action", "<init>", "(Lcom/lagradost/cloudstream3/utils/UiText;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAppName", "()Lcom/lagradost/cloudstream3/utils/UiText;", "getPackageName", "()Ljava/lang/String;", "name", "getName", "isPlayer", "", "()Z", "shouldShow", "context", "Landroid/content/Context;", "video", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "runAction", "", "result", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "index", "", "(Landroid/content/Context;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "putExtra", "intent", "Landroid/content/Intent;", "(Landroid/content/Context;Landroid/content/Intent;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onResult", "activity", "Landroid/app/Activity;", "onResultSafe", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class OpenInAppAction extends VideoClickAction {
    private final String action;
    private final UiText appName;
    private final String intentClass;
    private final boolean isPlayer;
    private final String packageName;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.actions.OpenInAppAction$runAction$1, reason: invalid class name */
    /* JADX INFO: compiled from: OpenInAppAction.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.actions.OpenInAppAction", f = "OpenInAppAction.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {101, 103}, m = "runAction$suspendImpl", n = {"$this", "context", "video", "result", "index", "intent", "$this", "context", "video", "result", "index", "intent"}, nl = {102, 104}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OpenInAppAction.runAction$suspendImpl(OpenInAppAction.this, null, null, null, null, this);
        }
    }

    public abstract void onResult(Activity activity, Intent intent);

    public abstract Object putExtra(Context context, Intent intent, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation);

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public Object runAction(Context context, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation) {
        return runAction$suspendImpl(this, context, resultEpisode, linkLoadingResult, num, continuation);
    }

    public OpenInAppAction(UiText appName, String packageName, String str, String action) {
        Intrinsics.checkNotNullParameter(appName, "appName");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(action, "action");
        this.appName = appName;
        this.packageName = packageName;
        this.intentClass = str;
        this.action = action;
        this.isPlayer = true;
    }

    public UiText getAppName() {
        return this.appName;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public /* synthetic */ OpenInAppAction(UiText uiText, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(uiText, str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? "android.intent.action.VIEW" : str3);
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public UiText getName() {
        return TextUtilKt.txt(R.string.episode_action_play_in_format, getAppName());
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    /* JADX INFO: renamed from: isPlayer, reason: from getter */
    public boolean getIsPlayer() {
        return this.isPlayer;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public boolean shouldShow(Context context, ResultEpisode video) {
        boolean z = false;
        if (context != null && !AppContextUtils.INSTANCE.isAppInstalled(context, getPackageName())) {
            z = true;
        }
        return !z;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00fe, code lost:
    
        if (com.lagradost.cloudstream3.actions.VideoClickAction.launchResult$default(r9, r10, null, r7, 2, null) == r0) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object runAction$suspendImpl(com.lagradost.cloudstream3.actions.OpenInAppAction r9, android.content.Context r10, com.lagradost.cloudstream3.ui.result.ResultEpisode r11, com.lagradost.cloudstream3.ui.result.LinkLoadingResult r12, java.lang.Integer r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.actions.OpenInAppAction.runAction$suspendImpl(com.lagradost.cloudstream3.actions.OpenInAppAction, android.content.Context, com.lagradost.cloudstream3.ui.result.ResultEpisode, com.lagradost.cloudstream3.ui.result.LinkLoadingResult, java.lang.Integer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onResultSafe(Activity activity, Intent intent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        try {
            onResult(activity, intent);
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
        }
    }
}
