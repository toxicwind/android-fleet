package com.lagradost.cloudstream3.services;

import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.work.Constraints;
import androidx.work.CoroutineWorker;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ForegroundInfo;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.BackupUtils;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BackupWorkManager.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\r\u001a\u00020\u000eH\u0096@¢\u0006\u0002\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u00070\u000b¢\u0006\u0002\b\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/services/BackupWorkManager;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "getContext", "()Landroid/content/Context;", "backupNotificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "Lorg/jspecify/annotations/NonNull;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class BackupWorkManager extends CoroutineWorker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final NotificationCompat.Builder backupNotificationBuilder;
    private final Context context;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.services.BackupWorkManager$doWork$1, reason: invalid class name */
    /* JADX INFO: compiled from: BackupWorkManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.services.BackupWorkManager", f = "BackupWorkManager.kt", i = {0}, l = {91}, m = "doWork", n = {"foregroundInfo"}, nl = {93}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BackupWorkManager.this.doWork(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BackupWorkManager(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.context = context;
        NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(context, BackupWorkManagerKt.BACKUP_CHANNEL_ID).setColorized(true).setOnlyAlertOnce(true).setSilent(true).setAutoCancel(true).setContentTitle(context.getString(R.string.pref_category_backup)).setPriority(0).setColor(UIHelper.INSTANCE.colorFromAttribute(context, R.attr.colorPrimary)).setSmallIcon(R.drawable.ic_cloudstream_monochrome_big);
        Intrinsics.checkNotNullExpressionValue(smallIcon, "setSmallIcon(...)");
        this.backupNotificationBuilder = smallIcon;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: BackupWorkManager.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/services/BackupWorkManager$Companion;", "", "<init>", "()V", "enqueuePeriodicWork", "", "context", "Landroid/content/Context;", "intervalHours", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void enqueuePeriodicWork(Context context, long intervalHours) {
            if (context == null) {
                return;
            }
            if (intervalHours == 0) {
                WorkManager.INSTANCE.getInstance(context).cancelUniqueWork(BackupWorkManagerKt.BACKUP_WORK_NAME);
                return;
            }
            WorkManager.INSTANCE.getInstance(context).enqueueUniquePeriodicWork(BackupWorkManagerKt.BACKUP_WORK_NAME, ExistingPeriodicWorkPolicy.UPDATE, new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) BackupWorkManager.class, intervalHours, TimeUnit.HOURS).addTag(BackupWorkManagerKt.BACKUP_WORK_NAME).setConstraints(new Constraints.Builder().setRequiresStorageNotLow(true).build()).build());
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.work.CoroutineWorker
    public Object doWork(Continuation<? super ListenableWorker.Result> continuation) {
        AnonymousClass1 anonymousClass1;
        ForegroundInfo foregroundInfo;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AppContextUtils.INSTANCE.createNotificationChannel(this.context, BackupWorkManagerKt.BACKUP_CHANNEL_ID, BackupWorkManagerKt.BACKUP_CHANNEL_NAME, BackupWorkManagerKt.BACKUP_CHANNEL_DESCRIPTION);
            if (Build.VERSION.SDK_INT >= 29) {
                foregroundInfo = new ForegroundInfo(BackupWorkManagerKt.BACKUP_NOTIFICATION_ID, this.backupNotificationBuilder.build(), 1);
            } else {
                foregroundInfo = new ForegroundInfo(BackupWorkManagerKt.BACKUP_NOTIFICATION_ID, this.backupNotificationBuilder.build());
            }
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(foregroundInfo);
            anonymousClass1.label = 1;
            if (setForeground(foregroundInfo, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        BackupUtils.INSTANCE.backup(this.context);
        ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
        Intrinsics.checkNotNullExpressionValue(resultSuccess, "success(...)");
        return resultSuccess;
    }
}
