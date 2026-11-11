package com.lagradost.cloudstream3.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.PendingIntentCompat;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: DownloadQueueService.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0001#B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0018\u001a\u00020\fH\u0016J\b\u0010\u0019\u001a\u00020\fH\u0016J\"\u0010\u001a\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0010H\u0016J\u0014\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u0010H\u0016R \u0010\u0004\u001a\u00070\u0005¢\u0006\u0002\b\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR)\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0004\u0012\u00020\f0\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/services/DownloadQueueService;", "Landroid/app/Service;", "<init>", "()V", "baseNotification", "Landroidx/core/app/NotificationCompat$Builder;", "Lorg/jspecify/annotations/NonNull;", "getBaseNotification", "()Landroidx/core/app/NotificationCompat$Builder;", "baseNotification$delegate", "Lkotlin/Lazy;", "updateNotification", "", "context", "Landroid/content/Context;", "downloads", "", "queued", "downloadEventListener", "Lkotlin/Function1;", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadActionType;", "getDownloadEventListener", "()Lkotlin/jvm/functions/Function1;", "onCreate", "onDestroy", "onStartCommand", "intent", "Landroid/content/Intent;", "flags", "startId", "onBind", "Landroid/os/IBinder;", "onTimeout", "reason", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DownloadQueueService extends Service {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DOWNLOAD_QUEUE_CHANNEL_DESCRIPTION = "App download queue notification.";
    public static final String DOWNLOAD_QUEUE_CHANNEL_ID = "cloudstream3.download.queue";
    public static final String DOWNLOAD_QUEUE_CHANNEL_NAME = "Download queue service";
    public static final int DOWNLOAD_QUEUE_NOTIFICATION_ID = 917194232;
    public static final String TAG = "DownloadQueueService";
    private static final MutableStateFlow<List<VideoDownloadManager.EpisodeDownloadInstance>> _downloadInstances;
    private static final StateFlow<List<VideoDownloadManager.EpisodeDownloadInstance>> downloadInstances;
    private static volatile boolean isRunning;
    private static final Flow<Triple<List<VideoDownloadManager.EpisodeDownloadInstance>, DownloadObjects.DownloadQueueWrapper[], Set<Integer>>> totalDownloadFlow;

    /* JADX INFO: renamed from: baseNotification$delegate, reason: from kotlin metadata */
    private final Lazy baseNotification = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.services.DownloadQueueService$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return DownloadQueueService.baseNotification_delegate$lambda$0(this.f$0);
        }
    });
    private final Function1<Pair<Integer, ? extends VideoDownloadManager.DownloadActionType>, Unit> downloadEventListener = new Function1() { // from class: com.lagradost.cloudstream3.services.DownloadQueueService$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return DownloadQueueService.downloadEventListener$lambda$0((Pair) obj);
        }
    };

    /* JADX INFO: compiled from: DownloadQueueService.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoDownloadManager.DownloadActionType.values().length];
            try {
                iArr[VideoDownloadManager.DownloadActionType.Stop.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    /* JADX INFO: compiled from: DownloadQueueService.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR8\u0010\u001c\u001a,\u0012(\u0012&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0!0\u001e0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/services/DownloadQueueService$Companion;", "", "<init>", "()V", "TAG", "", "DOWNLOAD_QUEUE_CHANNEL_ID", "DOWNLOAD_QUEUE_CHANNEL_NAME", "DOWNLOAD_QUEUE_CHANNEL_DESCRIPTION", "DOWNLOAD_QUEUE_NOTIFICATION_ID", "", "isRunning", "", "()Z", "setRunning", "(Z)V", "getIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "_downloadInstances", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance;", "downloadInstances", "Lkotlinx/coroutines/flow/StateFlow;", "getDownloadInstances", "()Lkotlinx/coroutines/flow/StateFlow;", "totalDownloadFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Triple;", "", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isRunning() {
            return DownloadQueueService.isRunning;
        }

        public final void setRunning(boolean z) {
            DownloadQueueService.isRunning = z;
        }

        public final Intent getIntent(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new Intent(context, (Class<?>) DownloadQueueService.class);
        }

        public final StateFlow<List<VideoDownloadManager.EpisodeDownloadInstance>> getDownloadInstances() {
            return DownloadQueueService.downloadInstances;
        }
    }

    static {
        MutableStateFlow<List<VideoDownloadManager.EpisodeDownloadInstance>> MutableStateFlow = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        _downloadInstances = MutableStateFlow;
        MutableStateFlow<List<VideoDownloadManager.EpisodeDownloadInstance>> mutableStateFlow = MutableStateFlow;
        downloadInstances = mutableStateFlow;
        totalDownloadFlow = FlowKt.flowCombine(FlowKt.flowCombine(mutableStateFlow, DownloadQueueManager.INSTANCE.getQueue(), new DownloadQueueService$Companion$totalDownloadFlow$1(null)), VideoDownloadManager.INSTANCE.getCurrentDownloads(), new DownloadQueueService$Companion$totalDownloadFlow$2(null));
    }

    private final NotificationCompat.Builder getBaseNotification() {
        return (NotificationCompat.Builder) this.baseNotification.getValue();
    }

    static final NotificationCompat.Builder baseNotification_delegate$lambda$0(DownloadQueueService downloadQueueService) {
        DownloadQueueService downloadQueueService2 = downloadQueueService;
        PendingIntent activity = PendingIntentCompat.getActivity(downloadQueueService2, 0, new Intent(downloadQueueService2, (Class<?>) MainActivity.class), 0, false);
        String quantityString = downloadQueueService.getResources().getQuantityString(R.plurals.downloads_active, 0);
        Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
        String str = String.format(quantityString, Arrays.copyOf(new Object[]{0}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        String quantityString2 = downloadQueueService.getResources().getQuantityString(R.plurals.downloads_queued, 0);
        Intrinsics.checkNotNullExpressionValue(quantityString2, "getQuantityString(...)");
        String str2 = String.format(quantityString2, Arrays.copyOf(new Object[]{0}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        return new NotificationCompat.Builder(downloadQueueService2, DOWNLOAD_QUEUE_CHANNEL_ID).setOngoing(true).setAutoCancel(false).setColorized(false).setOnlyAlertOnce(true).setSilent(true).setShowWhen(false).setPriority(0).setColor(UIHelper.INSTANCE.colorFromAttribute(downloadQueueService2, R.attr.colorPrimary)).setContentText(str).setSubText(str2).setContentIntent(activity).setSmallIcon(R.drawable.download_icon_load);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateNotification(final Context context, int downloads, int queued) {
        if (context.checkSelfPermission("android.permission.POST_NOTIFICATIONS") != 0) {
            return;
        }
        String quantityString = getResources().getQuantityString(R.plurals.downloads_active, downloads);
        Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
        String str = String.format(quantityString, Arrays.copyOf(new Object[]{Integer.valueOf(downloads)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        String quantityString2 = getResources().getQuantityString(R.plurals.downloads_queued, queued);
        Intrinsics.checkNotNullExpressionValue(quantityString2, "getQuantityString(...)");
        String str2 = String.format(quantityString2, Arrays.copyOf(new Object[]{Integer.valueOf(queued)}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        final Notification notificationBuild = getBaseNotification().setContentText(str).setSubText(str2).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.services.DownloadQueueService$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DownloadQueueService.updateNotification$lambda$0(context, notificationBuild);
            }
        });
    }

    static final Unit updateNotification$lambda$0(Context context, Notification notification) {
        NotificationManagerCompat.from(context).notify(DOWNLOAD_QUEUE_NOTIFICATION_ID, notification);
        return Unit.INSTANCE;
    }

    public final Function1<Pair<Integer, ? extends VideoDownloadManager.DownloadActionType>, Unit> getDownloadEventListener() {
        return this.downloadEventListener;
    }

    static final Unit downloadEventListener$lambda$0(Pair event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (WhenMappings.$EnumSwitchMapping$0[((VideoDownloadManager.DownloadActionType) event.getSecond()).ordinal()] == 1) {
            CloudStreamApp.INSTANCE.removeKey(VideoDownloadManager.KEY_RESUME_PACKAGES, String.valueOf(((Number) event.getFirst()).intValue()));
            CloudStreamApp.INSTANCE.removeKey(VideoDownloadManager.KEY_RESUME_IN_QUEUE, String.valueOf(((Number) event.getFirst()).intValue()));
            DownloadQueueManager.INSTANCE.cancelDownload(((Number) event.getFirst()).intValue());
        }
        return Unit.INSTANCE;
    }

    @Override // android.app.Service
    public void onCreate() {
        isRunning = true;
        DownloadQueueService downloadQueueService = this;
        Log.d(TAG, "Download queue service started.");
        AppContextUtils.INSTANCE.createNotificationChannel(downloadQueueService, DOWNLOAD_QUEUE_CHANNEL_ID, DOWNLOAD_QUEUE_CHANNEL_NAME, DOWNLOAD_QUEUE_CHANNEL_DESCRIPTION);
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(DOWNLOAD_QUEUE_NOTIFICATION_ID, getBaseNotification().build(), 1);
        } else {
            startForeground(DOWNLOAD_QUEUE_NOTIFICATION_ID, getBaseNotification().build());
        }
        VideoDownloadManager.INSTANCE.getDownloadEvent().plusAssign(this.downloadEventListener);
        Coroutines.INSTANCE.ioSafe(this, new DownloadQueueService$onCreate$queueJob$1(downloadQueueService, this, null)).invokeOnCompletion(new Function1() { // from class: com.lagradost.cloudstream3.services.DownloadQueueService$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadQueueService.onCreate$lambda$0(this.f$0, (Throwable) obj);
            }
        });
    }

    static final Unit onCreate$lambda$0(final DownloadQueueService downloadQueueService, Throwable th) {
        if (th != null) {
            ArchComponentExtKt.logError(th);
        }
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.services.DownloadQueueService$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DownloadQueueService.onCreate$lambda$0$0(this.f$0);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onCreate$lambda$0$0(DownloadQueueService downloadQueueService) {
        downloadQueueService.stopSelf();
        return Unit.INSTANCE;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d(TAG, "Download queue service stopped.");
        VideoDownloadManager.INSTANCE.getDownloadEvent().minusAssign(this.downloadEventListener);
        isRunning = false;
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onTimeout(int reason) {
        stopSelf();
        Log.e(TAG, "Service stopped due to timeout: " + reason);
    }
}
