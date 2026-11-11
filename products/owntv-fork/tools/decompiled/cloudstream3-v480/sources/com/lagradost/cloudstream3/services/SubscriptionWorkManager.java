package com.lagradost.cloudstream3.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import androidx.core.app.PendingIntentCompat;
import androidx.work.Constraints;
import androidx.work.CoroutineWorker;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.EpisodeResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.TimeoutKt;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: SubscriptionWorkManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\u0018H\u0096@¢\u0006\u0002\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u00070\u000b¢\u0006\u0002\b\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\r\u001a\u00070\u000b¢\u0006\u0002\b\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/services/SubscriptionWorkManager;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "getContext", "()Landroid/content/Context;", "progressNotificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "Lorg/jspecify/annotations/NonNull;", "updateNotificationBuilder", "notificationManager", "Landroid/app/NotificationManager;", "updateProgress", "", "max", "", "progress", "indeterminate", "", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SubscriptionWorkManager extends CoroutineWorker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Context context;
    private final NotificationManager notificationManager;
    private final NotificationCompat.Builder progressNotificationBuilder;
    private final NotificationCompat.Builder updateNotificationBuilder;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.services.SubscriptionWorkManager$doWork$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubscriptionWorkManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.services.SubscriptionWorkManager", f = "SubscriptionWorkManager.kt", i = {0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3}, l = {116, 131, 132, 134}, m = "doWork", n = {"foregroundInfo", "foregroundInfo", "subscriptions", "progress", "max", "foregroundInfo", "subscriptions", "progress", "max", "foregroundInfo", "subscriptions", "progress", "max"}, nl = {118, 132, 134, 217}, s = {"L$0", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubscriptionWorkManager.this.doWork(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubscriptionWorkManager(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.context = context;
        NotificationCompat.Builder progress = new NotificationCompat.Builder(context, SubscriptionWorkManagerKt.SUBSCRIPTION_CHANNEL_ID).setAutoCancel(false).setColorized(true).setOnlyAlertOnce(true).setSilent(true).setPriority(0).setColor(UIHelper.INSTANCE.colorFromAttribute(context, R.attr.colorPrimary)).setContentTitle(context.getString(R.string.subscription_in_progress_notification)).setSmallIcon(com.google.android.gms.cast.framework.R.drawable.quantum_ic_refresh_white_24).setProgress(0, 0, true);
        Intrinsics.checkNotNullExpressionValue(progress, "setProgress(...)");
        this.progressNotificationBuilder = progress;
        NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(context, SubscriptionWorkManagerKt.SUBSCRIPTION_CHANNEL_ID).setColorized(true).setOnlyAlertOnce(true).setAutoCancel(true).setPriority(0).setColor(UIHelper.INSTANCE.colorFromAttribute(context, R.attr.colorPrimary)).setSmallIcon(R.drawable.ic_cloudstream_monochrome_big);
        Intrinsics.checkNotNullExpressionValue(smallIcon, "setSmallIcon(...)");
        this.updateNotificationBuilder = smallIcon;
        Object systemService = context.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        this.notificationManager = (NotificationManager) systemService;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: SubscriptionWorkManager.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/services/SubscriptionWorkManager$Companion;", "", "<init>", "()V", "enqueuePeriodicWork", "", "context", "Landroid/content/Context;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void enqueuePeriodicWork(Context context) {
            if (context == null) {
                return;
            }
            WorkManager.INSTANCE.getInstance(context).enqueueUniquePeriodicWork(SubscriptionWorkManagerKt.SUBSCRIPTION_WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) SubscriptionWorkManager.class, 6L, TimeUnit.HOURS).addTag(SubscriptionWorkManagerKt.SUBSCRIPTION_WORK_NAME).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateProgress(int max, int progress, boolean indeterminate) {
        this.notificationManager.notify(SubscriptionWorkManagerKt.SUBSCRIPTION_NOTIFICATION_ID, this.progressNotificationBuilder.setProgress(max, progress, indeterminate).build());
    }

    /* JADX WARN: Code duplicated, block: B:46:0x0128  */
    /* JADX WARN: Code duplicated, block: B:47:0x0129  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x014d, code lost:
    
        if (com.lagradost.cloudstream3.ParCollectionsKt.amap(r5, r13, r0) == r1) goto L50;
     */
    @Override // androidx.work.CoroutineWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object doWork(kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r13) {
        /*
            Method dump skipped, instruction units count: 356
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.services.SubscriptionWorkManager.doWork(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.services.SubscriptionWorkManager$doWork$2, reason: invalid class name */
    /* JADX INFO: compiled from: SubscriptionWorkManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "savedData", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$SubscribedData;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.services.SubscriptionWorkManager$doWork$2", f = "SubscriptionWorkManager.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {140, ByteCode.ATHROW}, m = "invokeSuspend", n = {"savedData", "api", "id", "savedData", "api", "response", "dubPreference", "latestEpisodes", "latestPreferredEpisode", "updateHeader", "updateDescription", "intent", BaseGmsClient.KEY_PENDING_INTENT, "id", "shouldUpdate", "latestEpisode"}, nl = {142, 201}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "I$0", "Z$0", "I$1"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<DataStoreHelper.SubscribedData, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $max;
        final /* synthetic */ Ref.IntRef $progress;
        int I$0;
        int I$1;
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(int i, Ref.IntRef intRef, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$max = i;
            this.$progress = intRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = SubscriptionWorkManager.this.new AnonymousClass2(this.$max, this.$progress, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(DataStoreHelper.SubscribedData subscribedData, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(subscribedData, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int iIntValue;
            MainAPI apiFromNameNull;
            Object objWithTimeoutOrNull;
            char c;
            Pair pair;
            String strAsString;
            Object objIoWork;
            PendingIntent pendingIntent;
            String str;
            DataStoreHelper.SubscribedData subscribedData = (DataStoreHelper.SubscribedData) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Integer id = subscribedData.getId();
                    if (id == null) {
                        return null;
                    }
                    iIntValue = id.intValue();
                    apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(subscribedData.getApiName());
                    if (apiFromNameNull == null) {
                        return null;
                    }
                    this.L$0 = subscribedData;
                    this.L$1 = apiFromNameNull;
                    this.I$0 = iIntValue;
                    this.label = 1;
                    objWithTimeoutOrNull = TimeoutKt.withTimeoutOrNull(60000L, new SubscriptionWorkManager$doWork$2$response$1(apiFromNameNull, subscribedData, null), this);
                    if (objWithTimeoutOrNull == coroutine_suspended) {
                    }
                    return coroutine_suspended;
                }
                if (i == 1) {
                    iIntValue = this.I$0;
                    apiFromNameNull = (MainAPI) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    objWithTimeoutOrNull = obj;
                } else {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i2 = this.I$0;
                    pendingIntent = (PendingIntent) this.L$9;
                    String str2 = (String) this.L$7;
                    str = (String) this.L$6;
                    ResultKt.throwOnFailure(obj);
                    strAsString = str2;
                    iIntValue = i2;
                    objIoWork = obj;
                }
                Notification notificationBuild = SubscriptionWorkManager.this.updateNotificationBuilder.setContentTitle(str).setContentText(strAsString).setContentIntent(pendingIntent).setLargeIcon((Bitmap) objIoWork).build();
                Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
                SubscriptionWorkManager.this.notificationManager.notify(iIntValue, notificationBuild);
                SubscriptionWorkManager subscriptionWorkManager = SubscriptionWorkManager.this;
                int i3 = this.$max;
                this.$progress.element++;
                subscriptionWorkManager.updateProgress(i3, this.$progress.element, false);
                return Unit.INSTANCE;
                EpisodeResponse episodeResponse = (EpisodeResponse) objWithTimeoutOrNull;
                if (episodeResponse == null) {
                    return null;
                }
                DubStatus dub = DataStoreHelper.INSTANCE.getDub(iIntValue);
                if (dub == null) {
                    if (AppContextUtils.INSTANCE.getApiDubstatusSettings(SubscriptionWorkManager.this.getContext()).contains(DubStatus.Dubbed)) {
                        dub = DubStatus.Dubbed;
                    } else {
                        dub = DubStatus.Subbed;
                    }
                }
                Map<DubStatus, Integer> latestEpisodes = episodeResponse.getLatestEpisodes();
                Integer num = latestEpisodes.get(dub);
                if (num != null) {
                    Integer num2 = subscribedData.getLastSeenEpisodeCount().get(dub);
                    pair = TuplesKt.to(Boxing.boxBoolean(num.intValue() > (num2 != null ? num2.intValue() : Integer.MIN_VALUE)), num);
                    c = 1;
                } else {
                    Integer num3 = latestEpisodes.get(DubStatus.None);
                    int iIntValue2 = num3 != null ? num3.intValue() : Integer.MIN_VALUE;
                    c = 1;
                    Integer num4 = subscribedData.getLastSeenEpisodeCount().get(DubStatus.None);
                    pair = TuplesKt.to(Boxing.boxBoolean(iIntValue2 > (num4 != null ? num4.intValue() : Integer.MIN_VALUE)), Boxing.boxInt(iIntValue2));
                }
                boolean zBooleanValue = ((Boolean) pair.component1()).booleanValue();
                int iIntValue3 = ((Number) pair.component2()).intValue();
                DataStoreHelper.INSTANCE.updateSubscribedData(Boxing.boxInt(iIntValue), subscribedData, episodeResponse);
                if (zBooleanValue) {
                    String name = subscribedData.getName();
                    int i4 = R.string.subscription_episode_released;
                    Integer numBoxInt = Boxing.boxInt(iIntValue3);
                    String name2 = subscribedData.getName();
                    Object[] objArr = new Object[2];
                    objArr[0] = numBoxInt;
                    objArr[c] = name2;
                    strAsString = TextUtilKt.txt(i4, objArr).asString(SubscriptionWorkManager.this.getContext());
                    Intent intent = new Intent(SubscriptionWorkManager.this.getContext(), (Class<?>) MainActivity.class);
                    intent.setData(Uri.parse(subscribedData.getUrl()));
                    intent.setFlags(268468224);
                    Intent intentPutExtra = intent.putExtra(MainActivity.API_NAME_EXTRA_KEY, apiFromNameNull.getName());
                    Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
                    PendingIntent activity = PendingIntentCompat.getActivity(SubscriptionWorkManager.this.getContext(), 0, intentPutExtra, 0, false);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(subscribedData);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(apiFromNameNull);
                    this.L$2 = SpillingKt.nullOutSpilledVariable(episodeResponse);
                    this.L$3 = SpillingKt.nullOutSpilledVariable(dub);
                    this.L$4 = SpillingKt.nullOutSpilledVariable(latestEpisodes);
                    this.L$5 = SpillingKt.nullOutSpilledVariable(num);
                    this.L$6 = name;
                    this.L$7 = strAsString;
                    this.L$8 = SpillingKt.nullOutSpilledVariable(intentPutExtra);
                    this.L$9 = activity;
                    this.I$0 = iIntValue;
                    this.Z$0 = zBooleanValue;
                    this.I$1 = iIntValue3;
                    this.label = 2;
                    objIoWork = Coroutines.INSTANCE.ioWork(SubscriptionWorkManager.this, new SubscriptionWorkManager$doWork$2$poster$1(subscribedData, SubscriptionWorkManager.this, null), this);
                    if (objIoWork != coroutine_suspended) {
                        pendingIntent = activity;
                        str = name;
                        Notification notificationBuild2 = SubscriptionWorkManager.this.updateNotificationBuilder.setContentTitle(str).setContentText(strAsString).setContentIntent(pendingIntent).setLargeIcon((Bitmap) objIoWork).build();
                        Intrinsics.checkNotNullExpressionValue(notificationBuild2, "build(...)");
                        SubscriptionWorkManager.this.notificationManager.notify(iIntValue, notificationBuild2);
                    }
                    return coroutine_suspended;
                }
                SubscriptionWorkManager subscriptionWorkManager2 = SubscriptionWorkManager.this;
                int i5 = this.$max;
                this.$progress.element++;
                subscriptionWorkManager2.updateProgress(i5, this.$progress.element, false);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
            }
        }
    }
}
