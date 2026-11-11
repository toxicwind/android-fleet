package com.lagradost.cloudstream3.utils;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageInstaller;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.services.PackageInstallerService;
import io.ktor.http.ContentDisposition;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PackageInstaller.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000O\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\t\u0018\u0000 \u001d2\u00020\u0001:\u0003\u001d\u001e\u001fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JU\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\f0\u00142\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\f0\u0014J\b\u0010\u001b\u001a\u00020\fH\u0002J\u0006\u0010\u001c\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/utils/ApkInstaller;", "", NotificationCompat.CATEGORY_SERVICE, "Lcom/lagradost/cloudstream3/services/PackageInstallerService;", "<init>", "(Lcom/lagradost/cloudstream3/services/PackageInstallerService;)V", "packageInstaller", "Landroid/content/pm/PackageInstaller;", "installActionReceiver", "com/lagradost/cloudstream3/utils/ApkInstaller$installActionReceiver$1", "Lcom/lagradost/cloudstream3/utils/ApkInstaller$installActionReceiver$1;", "installApk", "", "context", "Landroid/content/Context;", "inputStream", "Ljava/io/InputStream;", ContentDisposition.Parameters.Size, "", "installProgress", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "bytesRead", "installProgressStatus", "Lcom/lagradost/cloudstream3/utils/ApkInstaller$InstallProgressStatus;", "registerInstallActionReceiver", "unregisterInstallActionReceiver", "Companion", "DelayedInstaller", "InstallProgressStatus", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ApkInstaller {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "ApkInstaller";
    private static DelayedInstaller delayedInstaller;
    private static boolean isReceiverRegistered;
    private final ApkInstaller$installActionReceiver$1 installActionReceiver;
    private final PackageInstaller packageInstaller;
    private final PackageInstallerService service;

    /* JADX INFO: compiled from: PackageInstaller.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ApkInstaller$InstallProgressStatus;", "", "<init>", "(Ljava/lang/String;I)V", "Preparing", "Downloading", "Installing", "Failed", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum InstallProgressStatus {
        Preparing,
        Downloading,
        Installing,
        Failed;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<InstallProgressStatus> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.lagradost.cloudstream3.utils.ApkInstaller$installActionReceiver$1] */
    public ApkInstaller(PackageInstallerService service) {
        Intrinsics.checkNotNullParameter(service, "service");
        this.service = service;
        PackageInstaller packageInstaller = service.getPackageManager().getPackageInstaller();
        Intrinsics.checkNotNullExpressionValue(packageInstaller, "getPackageInstaller(...)");
        this.packageInstaller = packageInstaller;
        this.installActionReceiver = new BroadcastReceiver() { // from class: com.lagradost.cloudstream3.utils.ApkInstaller$installActionReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                if (intent.getIntExtra("android.content.pm.extra.STATUS", 1) == -1) {
                    Intent intent2 = (Intent) (Build.VERSION.SDK_INT < 33 ? intent.getParcelableExtra("android.intent.extra.INTENT") : intent.getParcelableExtra("android.intent.extra.INTENT", Intent.class));
                    if (intent2 != null) {
                        intent2.addFlags(268435456);
                    }
                    context.startActivity(intent2);
                }
            }
        };
        registerInstallActionReceiver();
    }

    /* JADX INFO: compiled from: PackageInstaller.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0004\u001a\b\u0018\u00010\u0005R\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ApkInstaller$Companion;", "", "<init>", "()V", "delayedInstaller", "Lcom/lagradost/cloudstream3/utils/ApkInstaller$DelayedInstaller;", "Lcom/lagradost/cloudstream3/utils/ApkInstaller;", "getDelayedInstaller", "()Lcom/lagradost/cloudstream3/utils/ApkInstaller$DelayedInstaller;", "setDelayedInstaller", "(Lcom/lagradost/cloudstream3/utils/ApkInstaller$DelayedInstaller;)V", "isReceiverRegistered", "", "TAG", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DelayedInstaller getDelayedInstaller() {
            return ApkInstaller.delayedInstaller;
        }

        public final void setDelayedInstaller(DelayedInstaller delayedInstaller) {
            ApkInstaller.delayedInstaller = delayedInstaller;
        }
    }

    /* JADX INFO: compiled from: PackageInstaller.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ApkInstaller$DelayedInstaller;", "", "session", "Landroid/content/pm/PackageInstaller$Session;", "intent", "Landroid/content/IntentSender;", "<init>", "(Lcom/lagradost/cloudstream3/utils/ApkInstaller;Landroid/content/pm/PackageInstaller$Session;Landroid/content/IntentSender;)V", "startInstallation", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public final class DelayedInstaller {
        private final IntentSender intent;
        private final PackageInstaller.Session session;
        final /* synthetic */ ApkInstaller this$0;

        public DelayedInstaller(ApkInstaller apkInstaller, PackageInstaller.Session session, IntentSender intent) {
            Intrinsics.checkNotNullParameter(session, "session");
            Intrinsics.checkNotNullParameter(intent, "intent");
            this.this$0 = apkInstaller;
            this.session = session;
            this.intent = intent;
        }

        public final boolean startInstallation() {
            boolean z;
            try {
                this.session.commit(this.intent);
                z = true;
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
                z = false;
            }
            ApkInstaller.INSTANCE.setDelayedInstaller(null);
            return z;
        }
    }

    public final void installApk(Context context, InputStream inputStream, long size, Function1<? super Integer, Unit> installProgress, Function1<? super InstallProgressStatus, Unit> installProgressStatus) {
        int i;
        Intent intent;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(installProgress, "installProgress");
        Intrinsics.checkNotNullParameter(installProgressStatus, "installProgressStatus");
        installProgressStatus.invoke(InstallProgressStatus.Preparing);
        Integer num = null;
        try {
            PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(1);
            if (Build.VERSION.SDK_INT >= 31) {
                sessionParams.setRequireUserAction(2);
            }
            int iCreateSession = this.packageInstaller.createSession(sessionParams);
            Integer numValueOf = Integer.valueOf(iCreateSession);
            try {
                sessionParams.setSize(size);
                PackageInstaller packageInstaller = this.packageInstaller;
                numValueOf.getClass();
                PackageInstaller.Session sessionOpenSession = packageInstaller.openSession(iCreateSession);
                Intrinsics.checkNotNullExpressionValue(sessionOpenSession, "openSession(...)");
                installProgressStatus.invoke(InstallProgressStatus.Downloading);
                OutputStream outputStreamOpenWrite = sessionOpenSession.openWrite(context.getPackageName(), 0L, size);
                try {
                    OutputStream outputStream = outputStreamOpenWrite;
                    byte[] bArr = new byte[4096];
                    int i2 = inputStream.read(bArr);
                    while (true) {
                        i = 0;
                        if (i2 < 0) {
                            break;
                        }
                        outputStream.write(bArr, 0, i2);
                        i2 = inputStream.read(bArr);
                        installProgress.invoke(Integer.valueOf(i2));
                        e = e;
                        num = numValueOf;
                        ArchComponentExtKt.logError(e);
                        this.service.unregisterReceiver(this.installActionReceiver);
                        installProgressStatus.invoke(InstallProgressStatus.Failed);
                        if (num != null) {
                            this.packageInstaller.abandonSession(num.intValue());
                            return;
                        }
                        return;
                    }
                    sessionOpenSession.fsync(outputStream);
                    inputStream.close();
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(outputStreamOpenWrite, null);
                    if (Build.VERSION.SDK_INT >= 34) {
                        intent = new Intent(this.service, (Class<?>) PackageInstallerService.class).setAction(PackageInstallerKt.INSTALL_ACTION);
                    } else {
                        intent = new Intent(PackageInstallerKt.INSTALL_ACTION);
                    }
                    Intrinsics.checkNotNull(intent);
                    if (Build.VERSION.SDK_INT >= 34) {
                        i = 33554432;
                    } else if (Build.VERSION.SDK_INT >= 31) {
                        i = 67108864;
                    }
                    PackageInstallerService packageInstallerService = this.service;
                    numValueOf.getClass();
                    IntentSender intentSender = PendingIntent.getBroadcast(packageInstallerService, iCreateSession, intent, i).getIntentSender();
                    Intrinsics.checkNotNullExpressionValue(intentSender, "getIntentSender(...)");
                    if (Build.VERSION.SDK_INT >= 31 && context.getPackageManager().canRequestPackageInstalls()) {
                        delayedInstaller = new DelayedInstaller(this, sessionOpenSession, intentSender);
                        Coroutines.INSTANCE.main(this, new AnonymousClass2(context, null));
                    } else {
                        installProgressStatus.invoke(InstallProgressStatus.Installing);
                        sessionOpenSession.commit(intentSender);
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(outputStreamOpenWrite, th);
                        throw th2;
                    }
                }
            } catch (Exception e) {
                e = e;
                num = numValueOf;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ApkInstaller$installApk$2, reason: invalid class name */
    /* JADX INFO: compiled from: PackageInstaller.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/utils/ApkInstaller;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ApkInstaller$installApk$2", f = "PackageInstaller.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ApkInstaller, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Context context, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ApkInstaller apkInstaller, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(apkInstaller, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Toast.makeText(this.$context, R.string.delayed_update_notice, 1).show();
            return Unit.INSTANCE;
        }
    }

    private final void registerInstallActionReceiver() {
        if (isReceiverRegistered) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PackageInstallerKt.INSTALL_ACTION);
        Log.d(TAG, "Registering install action event receiver");
        Context context = CloudStreamApp.INSTANCE.getContext();
        if (context != null) {
            IntentHelpersKt.registerBroadcastReceiver(context, this.installActionReceiver, intentFilter);
        }
        isReceiverRegistered = true;
    }

    public final void unregisterInstallActionReceiver() {
        if (isReceiverRegistered) {
            Log.d(TAG, "Unregistering install action event receiver");
            try {
                Context context = CloudStreamApp.INSTANCE.getContext();
                if (context != null) {
                    context.unregisterReceiver(this.installActionReceiver);
                }
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
            }
            isReceiverRegistered = false;
        }
    }
}
