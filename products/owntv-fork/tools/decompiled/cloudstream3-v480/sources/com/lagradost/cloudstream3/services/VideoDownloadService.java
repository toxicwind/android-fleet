package com.lagradost.cloudstream3.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt__JobKt;

/* JADX INFO: compiled from: VideoDownloadService.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\"\u0010\n\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/services/VideoDownloadService;", "Landroid/app/Service;", "<init>", "()V", "downloadScope", "Lkotlinx/coroutines/CoroutineScope;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onStartCommand", "", "flags", "startId", "onDestroy", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class VideoDownloadService extends Service {
    private final CoroutineScope downloadScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        VideoDownloadManager.DownloadActionType downloadActionType;
        if (intent != null) {
            int intExtra = intent.getIntExtra("id", -1);
            String stringExtra = intent.getStringExtra("type");
            if (intExtra != -1 && stringExtra != null) {
                int iHashCode = stringExtra.hashCode();
                if (iHashCode == -934426579) {
                    if (stringExtra.equals("resume")) {
                        downloadActionType = VideoDownloadManager.DownloadActionType.Resume;
                        BuildersKt__Builders_commonKt.launch$default(this.downloadScope, null, null, new AnonymousClass1(intExtra, downloadActionType, null), 3, null);
                    }
                    return 2;
                }
                if (iHashCode == 3540994) {
                    if (stringExtra.equals("stop")) {
                        downloadActionType = VideoDownloadManager.DownloadActionType.Stop;
                        BuildersKt__Builders_commonKt.launch$default(this.downloadScope, null, null, new AnonymousClass1(intExtra, downloadActionType, null), 3, null);
                    }
                    return 2;
                }
                if (iHashCode == 106440182 && stringExtra.equals("pause")) {
                    downloadActionType = VideoDownloadManager.DownloadActionType.Pause;
                    BuildersKt__Builders_commonKt.launch$default(this.downloadScope, null, null, new AnonymousClass1(intExtra, downloadActionType, null), 3, null);
                }
                return 2;
            }
        }
        return 2;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.services.VideoDownloadService$onStartCommand$1, reason: invalid class name */
    /* JADX INFO: compiled from: VideoDownloadService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.services.VideoDownloadService$onStartCommand$1", f = "VideoDownloadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $id;
        final /* synthetic */ VideoDownloadManager.DownloadActionType $state;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(int i, VideoDownloadManager.DownloadActionType downloadActionType, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$id = i;
            this.$state = downloadActionType;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$id, this.$state, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            VideoDownloadManager.INSTANCE.getDownloadEvent().invoke(new Pair<>(Boxing.boxInt(this.$id), this.$state));
            return Unit.INSTANCE;
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        JobKt__JobKt.cancel$default(this.downloadScope.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
        super.onDestroy();
    }
}
