package androidx.room;

import android.os.CancellationSignal;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.SQLiteConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: CoroutinesRoom.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/room/CoroutinesRoom;", "", "<init>", "()V", "Companion", "room-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CoroutinesRoom {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Deprecated(message = "No longer called by generated implementation")
    @JvmStatic
    public static final <R> Flow<R> createFlow(RoomDatabase roomDatabase, boolean z, String[] strArr, Callable<R> callable) {
        return INSTANCE.createFlow(roomDatabase, z, strArr, callable);
    }

    @Deprecated(message = "No longer called by generated implementation")
    @JvmStatic
    public static final <R> Object execute(RoomDatabase roomDatabase, boolean z, CancellationSignal cancellationSignal, Callable<R> callable, Continuation<? super R> continuation) {
        return INSTANCE.execute(roomDatabase, z, cancellationSignal, callable, continuation);
    }

    @Deprecated(message = "No longer called by generated implementation")
    @JvmStatic
    public static final <R> Object execute(RoomDatabase roomDatabase, boolean z, Callable<R> callable, Continuation<? super R> continuation) {
        return INSTANCE.execute(roomDatabase, z, callable, continuation);
    }

    private CoroutinesRoom() {
    }

    /* JADX INFO: compiled from: CoroutinesRoom.android.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00050\u000bH\u0087@¢\u0006\u0002\u0010\fJ<\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00050\u000bH\u0087@¢\u0006\u0002\u0010\u000fJJ\u0010\u0010\u001a\r\u0012\t\u0012\u0007H\u0005¢\u0006\u0002\b\u00120\u0011\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00050\u000bH\u0007¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/room/CoroutinesRoom$Companion;", "", "<init>", "()V", "execute", "R", "db", "Landroidx/room/RoomDatabase;", "inTransaction", "", "callable", "Ljava/util/concurrent/Callable;", "(Landroidx/room/RoomDatabase;ZLjava/util/concurrent/Callable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancellationSignal", "Landroid/os/CancellationSignal;", "(Landroidx/room/RoomDatabase;ZLandroid/os/CancellationSignal;Ljava/util/concurrent/Callable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/jvm/JvmSuppressWildcards;", "tableNames", "", "", "(Landroidx/room/RoomDatabase;Z[Ljava/lang/String;Ljava/util/concurrent/Callable;)Lkotlinx/coroutines/flow/Flow;", "room-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Deprecated(message = "No longer called by generated implementation")
        @JvmStatic
        public final <R> Object execute(RoomDatabase roomDatabase, boolean z, Callable<R> callable, Continuation<? super R> continuation) {
            CoroutinesRoom$Companion$execute$1 coroutinesRoom$Companion$execute$1;
            if (continuation instanceof CoroutinesRoom$Companion$execute$1) {
                coroutinesRoom$Companion$execute$1 = (CoroutinesRoom$Companion$execute$1) continuation;
                if ((coroutinesRoom$Companion$execute$1.label & Integer.MIN_VALUE) != 0) {
                    coroutinesRoom$Companion$execute$1.label -= Integer.MIN_VALUE;
                } else {
                    coroutinesRoom$Companion$execute$1 = new CoroutinesRoom$Companion$execute$1(this, continuation);
                }
            } else {
                coroutinesRoom$Companion$execute$1 = new CoroutinesRoom$Companion$execute$1(this, continuation);
            }
            Object coroutineContext = coroutinesRoom$Companion$execute$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = coroutinesRoom$Companion$execute$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(coroutineContext);
                if (roomDatabase.isOpenInternal() && roomDatabase.inTransaction()) {
                    return callable.call();
                }
                coroutinesRoom$Companion$execute$1.L$0 = callable;
                coroutinesRoom$Companion$execute$1.label = 1;
                coroutineContext = DBUtil.getCoroutineContext(roomDatabase, z, coroutinesRoom$Companion$execute$1);
                if (coroutineContext != coroutine_suspended) {
                }
            }
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(coroutineContext);
                return coroutineContext;
            }
            callable = (Callable) coroutinesRoom$Companion$execute$1.L$0;
            ResultKt.throwOnFailure(coroutineContext);
            CoroutinesRoom$Companion$execute$2 coroutinesRoom$Companion$execute$2 = new CoroutinesRoom$Companion$execute$2(callable, null);
            coroutinesRoom$Companion$execute$1.L$0 = null;
            coroutinesRoom$Companion$execute$1.label = 2;
            Object objWithContext = BuildersKt.withContext((CoroutineContext) coroutineContext, coroutinesRoom$Companion$execute$2, coroutinesRoom$Companion$execute$1);
            return objWithContext == coroutine_suspended ? coroutine_suspended : objWithContext;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        @Deprecated(message = "No longer called by generated implementation")
        @JvmStatic
        public final <R> Object execute(RoomDatabase roomDatabase, boolean z, final CancellationSignal cancellationSignal, Callable<R> callable, Continuation<? super R> continuation) {
            CoroutinesRoom$Companion$execute$3 coroutinesRoom$Companion$execute$3;
            if (continuation instanceof CoroutinesRoom$Companion$execute$3) {
                coroutinesRoom$Companion$execute$3 = (CoroutinesRoom$Companion$execute$3) continuation;
                if ((coroutinesRoom$Companion$execute$3.label & Integer.MIN_VALUE) != 0) {
                    coroutinesRoom$Companion$execute$3.label -= Integer.MIN_VALUE;
                } else {
                    coroutinesRoom$Companion$execute$3 = new CoroutinesRoom$Companion$execute$3(this, continuation);
                }
            } else {
                coroutinesRoom$Companion$execute$3 = new CoroutinesRoom$Companion$execute$3(this, continuation);
            }
            Object coroutineContext = coroutinesRoom$Companion$execute$3.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = coroutinesRoom$Companion$execute$3.label;
            if (i == 0) {
                ResultKt.throwOnFailure(coroutineContext);
                if (roomDatabase.isOpenInternal() && roomDatabase.inTransaction()) {
                    return callable.call();
                }
                coroutinesRoom$Companion$execute$3.L$0 = roomDatabase;
                coroutinesRoom$Companion$execute$3.L$1 = cancellationSignal;
                coroutinesRoom$Companion$execute$3.L$2 = callable;
                coroutinesRoom$Companion$execute$3.label = 1;
                coroutineContext = DBUtil.getCoroutineContext(roomDatabase, z, coroutinesRoom$Companion$execute$3);
                if (coroutineContext != coroutine_suspended) {
                }
            }
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(coroutineContext);
                return coroutineContext;
            }
            callable = (Callable) coroutinesRoom$Companion$execute$3.L$2;
            cancellationSignal = (CancellationSignal) coroutinesRoom$Companion$execute$3.L$1;
            roomDatabase = (RoomDatabase) coroutinesRoom$Companion$execute$3.L$0;
            ResultKt.throwOnFailure(coroutineContext);
            CoroutineContext coroutineContext2 = (CoroutineContext) coroutineContext;
            coroutinesRoom$Companion$execute$3.L$0 = roomDatabase;
            coroutinesRoom$Companion$execute$3.L$1 = cancellationSignal;
            coroutinesRoom$Companion$execute$3.L$2 = callable;
            coroutinesRoom$Companion$execute$3.L$3 = coroutineContext2;
            coroutinesRoom$Companion$execute$3.label = 2;
            CoroutinesRoom$Companion$execute$3 coroutinesRoom$Companion$execute$4 = coroutinesRoom$Companion$execute$3;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(coroutinesRoom$Companion$execute$4), 1);
            cancellableContinuationImpl.initCancellability();
            CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
            final Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(roomDatabase.getCoroutineScope(), coroutineContext2, null, new CoroutinesRoom$Companion$execute$4$job$1(callable, cancellableContinuationImpl2, null), 2, null);
            cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: androidx.room.CoroutinesRoom$Companion$execute$4$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                    invoke2(th);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Throwable th) {
                    CancellationSignal cancellationSignal2 = cancellationSignal;
                    if (cancellationSignal2 != null) {
                        cancellationSignal2.cancel();
                    }
                    Job.CC.cancel$default(jobLaunch$default, (CancellationException) null, 1, (Object) null);
                }
            });
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(coroutinesRoom$Companion$execute$4);
            }
            return result == coroutine_suspended ? coroutine_suspended : result;
        }

        static final Object createFlow$lambda$1(Callable callable, SQLiteConnection it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return callable.call();
        }

        @Deprecated(message = "No longer called by generated implementation")
        @JvmStatic
        public final <R> Flow<R> createFlow(RoomDatabase db, boolean inTransaction, String[] tableNames, final Callable<R> callable) {
            Intrinsics.checkNotNullParameter(db, "db");
            Intrinsics.checkNotNullParameter(tableNames, "tableNames");
            Intrinsics.checkNotNullParameter(callable, "callable");
            return FlowUtil.createFlow(db, inTransaction, tableNames, new Function1() { // from class: androidx.room.CoroutinesRoom$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return CoroutinesRoom.Companion.createFlow$lambda$1(callable, (SQLiteConnection) obj);
                }
            });
        }
    }
}
