package com.lagradost.cloudstream3.ui.player;

import android.content.Context;
import androidx.media3.ui.PlayerNotificationManager;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: GeneratorPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0000*\u0001\u0004\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "com/lagradost/cloudstream3/ui/player/GeneratorPlayer$getMediaNotification$1"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.GeneratorPlayer$getMediaNotification$1$getCurrentLargeIcon$1", f = "GeneratorPlayer.kt", i = {0, 0, 0, 0}, l = {366}, m = "invokeSuspend", n = {ImagesContract.URL, "preview", "it", "$i$a$-repeat-GeneratorPlayer$getMediaNotification$1$getCurrentLargeIcon$1$1"}, nl = {367}, s = {"L$0", "L$3", "I$2", "I$3"}, v = 2)
final class GeneratorPlayer$getMediaNotification$1$getCurrentLargeIcon$1 extends SuspendLambda implements Function3<CoroutineScope, GeneratorPlayer.C08591, Continuation<? super Unit>, Object> {
    final /* synthetic */ PlayerNotificationManager.BitmapCallback $callback;
    final /* synthetic */ Context $context;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ GeneratorPlayer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GeneratorPlayer$getMediaNotification$1$getCurrentLargeIcon$1(GeneratorPlayer generatorPlayer, Context context, PlayerNotificationManager.BitmapCallback bitmapCallback, Continuation<? super GeneratorPlayer$getMediaNotification$1$getCurrentLargeIcon$1> continuation) {
        super(3, continuation);
        this.this$0 = generatorPlayer;
        this.$context = context;
        this.$callback = bitmapCallback;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, GeneratorPlayer.C08591 c08591, Continuation<? super Unit> continuation) {
        return new GeneratorPlayer$getMediaNotification$1$getCurrentLargeIcon$1(this.this$0, this.$context, this.$callback, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0066  */
    /* JADX WARN: Code duplicated, block: B:22:0x0072  */
    /* JADX WARN: Code duplicated, block: B:23:0x0076  */
    /* JADX WARN: Code duplicated, block: B:25:0x0098 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:27:0x009b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0072 -> B:26:0x0099). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0096 -> B:26:0x0099). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L2d
            if (r1 != r3) goto L25
            int r1 = r10.I$1
            int r4 = r10.I$0
            java.lang.Object r5 = r10.L$3
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            java.lang.Object r5 = r10.L$2
            androidx.media3.ui.PlayerNotificationManager$BitmapCallback r5 = (androidx.media3.ui.PlayerNotificationManager.BitmapCallback) r5
            java.lang.Object r6 = r10.L$1
            com.lagradost.cloudstream3.ui.player.GeneratorPlayer r6 = (com.lagradost.cloudstream3.ui.player.GeneratorPlayer) r6
            java.lang.Object r7 = r10.L$0
            java.lang.String r7 = (java.lang.String) r7
            kotlin.ResultKt.throwOnFailure(r11)
            goto L99
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2d:
            kotlin.ResultKt.throwOnFailure(r11)
            com.lagradost.cloudstream3.ui.player.GeneratorPlayer r11 = r10.this$0
            java.lang.Object r11 = com.lagradost.cloudstream3.ui.player.GeneratorPlayer.access$getCurrentMeta(r11)
            boolean r1 = r11 instanceof com.lagradost.cloudstream3.ui.result.ResultEpisode
            if (r1 == 0) goto L41
            com.lagradost.cloudstream3.ui.result.ResultEpisode r11 = (com.lagradost.cloudstream3.ui.result.ResultEpisode) r11
            java.lang.String r11 = r11.getPoster()
            goto L42
        L41:
            r11 = 0
        L42:
            r6 = r11
            if (r6 == 0) goto L5a
            com.lagradost.cloudstream3.utils.downloader.DownloadUtils r4 = com.lagradost.cloudstream3.utils.downloader.DownloadUtils.INSTANCE
            android.content.Context r5 = r10.$context
            r8 = 2
            r9 = 0
            r7 = 0
            android.graphics.Bitmap r11 = com.lagradost.cloudstream3.utils.downloader.DownloadUtils.getImageBitmapFromUrl$app$default(r4, r5, r6, r7, r8, r9)
            if (r11 == 0) goto L5a
            androidx.media3.ui.PlayerNotificationManager$BitmapCallback r10 = r10.$callback
            r10.onBitmap(r11)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L5a:
            com.lagradost.cloudstream3.ui.player.GeneratorPlayer r11 = r10.this$0
            androidx.media3.ui.PlayerNotificationManager$BitmapCallback r1 = r10.$callback
            r4 = 10
            r5 = r1
            r1 = r2
            r7 = r6
            r6 = r11
        L64:
            if (r1 >= r4) goto L9b
            com.lagradost.cloudstream3.ui.player.IPlayer r11 = r6.getPlayer()
            r8 = 1056964608(0x3f000000, float:0.5)
            android.graphics.Bitmap r11 = r11.getPreview(r8)
            if (r11 == 0) goto L76
            r5.onBitmap(r11)
            goto L99
        L76:
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r10.L$0 = r8
            r10.L$1 = r6
            r10.L$2 = r5
            java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r10.L$3 = r11
            r10.I$0 = r4
            r10.I$1 = r1
            r10.I$2 = r1
            r10.I$3 = r2
            r10.label = r3
            r8 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r11 = kotlinx.coroutines.DelayKt.delay(r8, r10)
            if (r11 != r0) goto L99
            return r0
        L99:
            int r1 = r1 + r3
            goto L64
        L9b:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$getMediaNotification$1$getCurrentLargeIcon$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
