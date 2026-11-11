package com.lagradost.cloudstream3.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.PendingIntentCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.actions.temp.fcast.FcastManager;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.ApkInstaller;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.ResponseBody;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: PackageInstallerService.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 '2\u00020\u0001:\u0001'B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0082@¢\u0006\u0002\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\"\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001cH\u0016J\b\u0010!\u001a\u00020\u000eH\u0016J\u0014\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u001cH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u00070\u0007¢\u0006\u0002\b\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/services/PackageInstallerService;", "Landroid/app/Service;", "<init>", "()V", "installer", "Lcom/lagradost/cloudstream3/utils/ApkInstaller;", "baseNotification", "Landroidx/core/app/NotificationCompat$Builder;", "Lorg/jspecify/annotations/NonNull;", "getBaseNotification", "()Landroidx/core/app/NotificationCompat$Builder;", "baseNotification$delegate", "Lkotlin/Lazy;", "onCreate", "", "updateLock", "Lkotlinx/coroutines/sync/Mutex;", "downloadUpdate", "", ImagesContract.URL, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNotificationProgress", "percentage", "", "state", "Lcom/lagradost/cloudstream3/utils/ApkInstaller$InstallProgressStatus;", "onStartCommand", "", "intent", "Landroid/content/Intent;", "flags", "startId", "onDestroy", "onBind", "Landroid/os/IBinder;", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "onTimeout", "reason", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PackageInstallerService extends Service {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String EXTRA_URL = "EXTRA_URL";
    public static final String UPDATE_CHANNEL_DESCRIPTION = "App updates notification channel";
    public static final String UPDATE_CHANNEL_ID = "cloudstream3.updates";
    public static final String UPDATE_CHANNEL_NAME = "App Updates";
    public static final int UPDATE_NOTIFICATION_ID = -68454136;
    private ApkInstaller installer;

    /* JADX INFO: renamed from: baseNotification$delegate, reason: from kotlin metadata */
    private final Lazy baseNotification = LazyKt.lazy(new Function0() { // from class: com.lagradost.cloudstream3.services.PackageInstallerService$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return PackageInstallerService.baseNotification_delegate$lambda$0(this.f$0);
        }
    });
    private final Mutex updateLock = MutexKt.Mutex$default(false, 1, null);

    /* JADX INFO: compiled from: PackageInstallerService.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ApkInstaller.InstallProgressStatus.values().length];
            try {
                iArr[ApkInstaller.InstallProgressStatus.Installing.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ApkInstaller.InstallProgressStatus.Preparing.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ApkInstaller.InstallProgressStatus.Downloading.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ApkInstaller.InstallProgressStatus.Failed.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.services.PackageInstallerService$downloadUpdate$1, reason: invalid class name */
    /* JADX INFO: compiled from: PackageInstallerService.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.services.PackageInstallerService", f = "PackageInstallerService.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {ByteCode.MONITOREXIT, 83}, m = "downloadUpdate", n = {ImagesContract.URL, "$this$withLock_u24default$iv", "$i$f$withLock", ImagesContract.URL, "$this$withLock_u24default$iv", "$i$f$withLock", "$i$a$-withLock$default-PackageInstallerService$downloadUpdate$3"}, nl = {196, 84}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0", "I$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PackageInstallerService.this.downloadUpdate(null, this);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent i) {
        return null;
    }

    private final NotificationCompat.Builder getBaseNotification() {
        return (NotificationCompat.Builder) this.baseNotification.getValue();
    }

    static final NotificationCompat.Builder baseNotification_delegate$lambda$0(PackageInstallerService packageInstallerService) {
        PackageInstallerService packageInstallerService2 = packageInstallerService;
        return new NotificationCompat.Builder(packageInstallerService2, UPDATE_CHANNEL_ID).setAutoCancel(false).setColorized(true).setOnlyAlertOnce(true).setSilent(true).setPriority(0).setColor(UIHelper.INSTANCE.colorFromAttribute(packageInstallerService2, R.attr.colorPrimary)).setContentTitle(packageInstallerService.getString(R.string.update_notification_downloading)).setContentIntent(PendingIntentCompat.getActivity(packageInstallerService2, 0, new Intent(packageInstallerService2, (Class<?>) MainActivity.class), 0, false)).setSmallIcon(R.drawable.rdload);
    }

    @Override // android.app.Service
    public void onCreate() {
        AppContextUtils.INSTANCE.createNotificationChannel(this, UPDATE_CHANNEL_ID, UPDATE_CHANNEL_NAME, UPDATE_CHANNEL_DESCRIPTION);
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(UPDATE_NOTIFICATION_ID, getBaseNotification().build(), 1);
        } else {
            startForeground(UPDATE_NOTIFICATION_ID, getBaseNotification().build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:35:0x0108 A[Catch: all -> 0x0122, TRY_LEAVE, TryCatch #0 {all -> 0x0122, blocks: (B:33:0x00ea, B:35:0x0108, B:29:0x00e2), top: B:47:0x00e2 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v2, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v9 */
    public final Object downloadUpdate(String str, Continuation<? super Boolean> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Object obj;
        String str2;
        int i;
        Requests app2;
        AnonymousClass1 anonymousClass2;
        InputStream inputStreamByteStream;
        final long contentLength;
        final Ref.IntRef intRef;
        ApkInstaller apkInstaller;
        ?? r3 = "Downloading update: ";
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
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        boolean z = false;
        Object obj3 = null;
        try {
            try {
                try {
                    try {
                        if (i2 == 0) {
                            ResultKt.throwOnFailure(obj2);
                            Log.d("PackageInstallerService", "Downloading update: " + str);
                            Coroutines.INSTANCE.ioSafe(this, new AnonymousClass2(null));
                            Mutex mutex = this.updateLock;
                            anonymousClass1.L$0 = str;
                            anonymousClass1.L$1 = mutex;
                            anonymousClass1.I$0 = 0;
                            anonymousClass1.label = 1;
                            if (mutex.lock(null, anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            str2 = str;
                            r3 = mutex;
                            i = 0;
                        } else {
                            if (i2 == 1) {
                                i = anonymousClass1.I$0;
                                Mutex mutex2 = (Mutex) anonymousClass1.L$1;
                                str2 = (String) anonymousClass1.L$0;
                                ResultKt.throwOnFailure(obj2);
                                r3 = mutex2;
                            } else {
                                if (i2 != 2) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                int i3 = anonymousClass1.I$1;
                                int i4 = anonymousClass1.I$0;
                                Mutex mutex3 = (Mutex) anonymousClass1.L$1;
                                ResultKt.throwOnFailure(obj2);
                                obj = null;
                                r3 = mutex3;
                            }
                            ResponseBody body = ((NiceResponse) obj2).getBody();
                            inputStreamByteStream = body.byteStream();
                            this.installer = new ApkInstaller(this);
                            contentLength = body.getContentLength();
                            intRef = new Ref.IntRef();
                            apkInstaller = this.installer;
                            if (apkInstaller != null) {
                                apkInstaller.installApk(this, inputStreamByteStream, contentLength, new Function1() { // from class: com.lagradost.cloudstream3.services.PackageInstallerService$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj4) {
                                        return PackageInstallerService.downloadUpdate$lambda$0$0(intRef, contentLength, this, ((Integer) obj4).intValue());
                                    }
                                }, new Function1() { // from class: com.lagradost.cloudstream3.services.PackageInstallerService$$ExternalSyntheticLambda3
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj4) {
                                        return PackageInstallerService.downloadUpdate$lambda$0$1(this.f$0, (ApkInstaller.InstallProgressStatus) obj4);
                                    }
                                });
                                Unit unit = Unit.INSTANCE;
                            }
                            r3.unlock(obj);
                            return Boxing.boxBoolean(true);
                        }
                        Object obj4 = Requests.get$default(app2, str2, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 4094, null);
                        if (obj4 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj2 = obj4;
                        r3 = r3;
                        ResponseBody body2 = ((NiceResponse) obj2).getBody();
                        inputStreamByteStream = body2.byteStream();
                        this.installer = new ApkInstaller(this);
                        contentLength = body2.getContentLength();
                        intRef = new Ref.IntRef();
                        apkInstaller = this.installer;
                        if (apkInstaller != null) {
                            apkInstaller.installApk(this, inputStreamByteStream, contentLength, new Function1() { // from class: com.lagradost.cloudstream3.services.PackageInstallerService$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj5) {
                                    return PackageInstallerService.downloadUpdate$lambda$0$0(intRef, contentLength, this, ((Integer) obj5).intValue());
                                }
                            }, new Function1() { // from class: com.lagradost.cloudstream3.services.PackageInstallerService$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj5) {
                                    return PackageInstallerService.downloadUpdate$lambda$0$1(this.f$0, (ApkInstaller.InstallProgressStatus) obj5);
                                }
                            });
                            Unit unit2 = Unit.INSTANCE;
                        }
                        r3.unlock(obj);
                        return Boxing.boxBoolean(true);
                    } catch (Throwable th) {
                        th = th;
                        r3.unlock(obj);
                        throw th;
                    }
                    updateNotificationProgress(0.0f, ApkInstaller.InstallProgressStatus.Downloading);
                    app2 = MainActivityKt.getApp();
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str2);
                    anonymousClass1.L$1 = r3;
                    anonymousClass1.I$0 = i;
                    anonymousClass1.I$1 = 0;
                    anonymousClass1.label = 2;
                    z = false;
                    obj3 = null;
                    anonymousClass2 = anonymousClass1;
                    obj = null;
                } catch (Exception e) {
                    e = e;
                    ArchComponentExtKt.logError(e);
                    updateNotificationProgress(0.0f, ApkInstaller.InstallProgressStatus.Failed);
                    return Boxing.boxBoolean(false);
                }
            } catch (Exception e2) {
                e = e2;
                ArchComponentExtKt.logError(e);
                updateNotificationProgress(0.0f, ApkInstaller.InstallProgressStatus.Failed);
                return Boxing.boxBoolean(false);
            }
        } catch (Throwable th2) {
            th = th2;
            obj = obj3;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.services.PackageInstallerService$downloadUpdate$2, reason: invalid class name */
    /* JADX INFO: compiled from: PackageInstallerService.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/services/PackageInstallerService;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.services.PackageInstallerService$downloadUpdate$2", f = "PackageInstallerService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function3<CoroutineScope, PackageInstallerService, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, PackageInstallerService packageInstallerService, Continuation<? super Unit> continuation) {
            return PackageInstallerService.this.new AnonymousClass2(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            File[] fileArrListFiles = PackageInstallerService.this.getCacheDir().listFiles();
            if (fileArrListFiles != null) {
                ArrayList<File> arrayList = new ArrayList();
                for (File file : fileArrListFiles) {
                    String name = file.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    if (StringsKt.startsWith$default(name, FcastManager.APP_PREFIX, false, 2, (Object) null)) {
                        Intrinsics.checkNotNull(file);
                        if (Intrinsics.areEqual(FilesKt.getExtension(file), "apk")) {
                            arrayList.add(file);
                        }
                    }
                }
                for (File file2 : arrayList) {
                    MainActivity.Companion companion = MainActivity.INSTANCE;
                    Intrinsics.checkNotNull(file2);
                    companion.deleteFileOnExit(file2);
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit downloadUpdate$lambda$0$0(Ref.IntRef intRef, long j, PackageInstallerService packageInstallerService, int i) {
        intRef.element += i;
        if (j == 0) {
            return Unit.INSTANCE;
        }
        packageInstallerService.updateNotificationProgress(intRef.element / j, ApkInstaller.InstallProgressStatus.Downloading);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit downloadUpdate$lambda$0$1(PackageInstallerService packageInstallerService, ApkInstaller.InstallProgressStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        packageInstallerService.updateNotificationProgress(0.0f, status);
        return Unit.INSTANCE;
    }

    private final void updateNotificationProgress(float percentage, ApkInstaller.InstallProgressStatus state) {
        int i;
        int i2 = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
        if (i2 == 1) {
            i = R.string.update_notification_installing;
        } else if (i2 == 2 || i2 == 3) {
            i = R.string.update_notification_downloading;
        } else {
            if (i2 != 4) {
                throw new NoWhenBranchMatchedException();
            }
            i = R.string.update_notification_failed;
        }
        NotificationCompat.Builder contentTitle = getBaseNotification().setContentTitle(getString(i));
        if (state == ApkInstaller.InstallProgressStatus.Failed) {
            contentTitle.setSmallIcon(R.drawable.rderror);
            contentTitle.setAutoCancel(true);
        } else {
            contentTitle.setProgress(10000, MathKt.roundToInt(10000.0f * percentage), state != ApkInstaller.InstallProgressStatus.Downloading);
        }
        Notification notificationBuild = contentTitle.build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        Object systemService = getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).notify(state == ApkInstaller.InstallProgressStatus.Failed ? -68454135 : UPDATE_NOTIFICATION_ID, notificationBuild);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.services.PackageInstallerService$onStartCommand$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PackageInstallerService.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/services/PackageInstallerService;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.services.PackageInstallerService$onStartCommand$1", f = "PackageInstallerService.kt", i = {}, l = {149, 153}, m = "invokeSuspend", n = {}, nl = {153, 154}, s = {}, v = 2)
    static final class C07641 extends SuspendLambda implements Function3<CoroutineScope, PackageInstallerService, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07641(String str, Continuation<? super C07641> continuation) {
            super(3, continuation);
            this.$url = str;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, PackageInstallerService packageInstallerService, Continuation<? super Unit> continuation) {
            return PackageInstallerService.this.new C07641(this.$url, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x003c, code lost:
        
            if (kotlinx.coroutines.DelayKt.delay(10000, r5) == r0) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L1e
                if (r1 == r3) goto L1a
                if (r1 != r2) goto L12
                kotlin.ResultKt.throwOnFailure(r6)
                goto L3f
            L12:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L1a:
                kotlin.ResultKt.throwOnFailure(r6)
                goto L31
            L1e:
                kotlin.ResultKt.throwOnFailure(r6)
                com.lagradost.cloudstream3.services.PackageInstallerService r6 = com.lagradost.cloudstream3.services.PackageInstallerService.this
                java.lang.String r1 = r5.$url
                r4 = r5
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r5.label = r3
                java.lang.Object r6 = com.lagradost.cloudstream3.services.PackageInstallerService.access$downloadUpdate(r6, r1, r4)
                if (r6 != r0) goto L31
                goto L3e
            L31:
                r6 = r5
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r5.label = r2
                r1 = 10000(0x2710, double:4.9407E-320)
                java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r1, r6)
                if (r6 != r0) goto L3f
            L3e:
                return r0
            L3f:
                com.lagradost.cloudstream3.services.PackageInstallerService r5 = com.lagradost.cloudstream3.services.PackageInstallerService.this
                r5.stopSelf()
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.services.PackageInstallerService.C07641.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String stringExtra;
        if (intent != null && (stringExtra = intent.getStringExtra(EXTRA_URL)) != null) {
            Coroutines.INSTANCE.ioSafe(this, new C07641(stringExtra, null));
        }
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        ApkInstaller apkInstaller = this.installer;
        if (apkInstaller != null) {
            apkInstaller.unregisterInstallActionReceiver();
        }
        this.installer = null;
        stopSelf();
        super.onDestroy();
    }

    @Override // android.app.Service
    public void onTimeout(int reason) {
        stopSelf();
        Log.e("PackageInstallerService", "Service stopped due to timeout: " + reason);
    }

    /* JADX INFO: compiled from: PackageInstallerService.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/services/PackageInstallerService$Companion;", "", "<init>", "()V", PackageInstallerService.EXTRA_URL, "", "UPDATE_CHANNEL_ID", "UPDATE_CHANNEL_NAME", "UPDATE_CHANNEL_DESCRIPTION", "UPDATE_NOTIFICATION_ID", "", "getIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", ImagesContract.URL, "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Intent getIntent(Context context, String url) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(url, "url");
            Intent intentPutExtra = new Intent(context, (Class<?>) PackageInstallerService.class).putExtra(PackageInstallerService.EXTRA_URL, url);
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
            return intentPutExtra;
        }
    }
}
