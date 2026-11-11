package androidx.room.coroutines;

import androidx.room.Transactor;
import androidx.room.concurrent.ThreadLocal_jvmAndroidKt;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteDriver;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.ExceptionsKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: compiled from: ConnectionPoolImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\u000bJ@\u0010\"\u001a\u0002H#\"\u0004\b\u0000\u0010#2\u0006\u0010$\u001a\u00020\u00192\"\u0010%\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020'\u0012\n\u0012\b\u0012\u0004\u0012\u0002H#0(\u0012\u0006\u0012\u0004\u0018\u00010)0&H\u0096@¢\u0006\u0002\u0010*J\"\u0010+\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010-\u0012\u0006\u0012\u0004\u0018\u00010.0,*\u00020\rH\u0082H¢\u0006\u0002\u0010/J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u0011H\u0002J\u0010\u00103\u001a\u0002042\u0006\u0010$\u001a\u00020\u0019H\u0002J\b\u00105\u001a\u000206H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0014\u0010\u0014\u001a\u00060\u0015j\u0002`\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u001aR\u001c\u0010\u001b\u001a\u00020\u001cX\u0080\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u00067"}, d2 = {"Landroidx/room/coroutines/ConnectionPoolImpl;", "Landroidx/room/coroutines/ConnectionPool;", "driver", "Landroidx/sqlite/SQLiteDriver;", "fileName", "", "<init>", "(Landroidx/sqlite/SQLiteDriver;Ljava/lang/String;)V", "maxNumOfReaders", "", "maxNumOfWriters", "(Landroidx/sqlite/SQLiteDriver;Ljava/lang/String;II)V", "readers", "Landroidx/room/coroutines/Pool;", "writers", "threadLocal", "Ljava/lang/ThreadLocal;", "Landroidx/room/coroutines/PooledConnectionImpl;", "Landroidx/room/concurrent/ThreadLocal;", "Ljava/lang/ThreadLocal;", "_isClosed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Landroidx/room/concurrent/AtomicBoolean;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isClosed", "", "()Z", "timeout", "Lkotlin/time/Duration;", "getTimeout-UwyO8pc$room_runtime_release", "()J", "setTimeout-LRDsOJo$room_runtime_release", "(J)V", "J", "useConnection", "R", "isReadOnly", "block", "Lkotlin/Function2;", "Landroidx/room/Transactor;", "Lkotlin/coroutines/Continuation;", "", "(ZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireWithTimeout", "Lkotlin/Pair;", "Landroidx/room/coroutines/ConnectionWithLock;", "", "(Landroidx/room/coroutines/Pool;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createConnectionContext", "Lkotlin/coroutines/CoroutineContext;", "connection", "throwTimeoutException", "", "close", "", "room-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ConnectionPoolImpl implements ConnectionPool {
    private final AtomicBoolean _isClosed;
    private final SQLiteDriver driver;
    private final Pool readers;
    private final ThreadLocal<PooledConnectionImpl> threadLocal;
    private long timeout;
    private final Pool writers;

    /* JADX INFO: renamed from: androidx.room.coroutines.ConnectionPoolImpl$useConnection$1, reason: invalid class name */
    /* JADX INFO: compiled from: ConnectionPoolImpl.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.ConnectionPoolImpl", f = "ConnectionPoolImpl.kt", i = {2, 2, 2, 2, 2, 2, 2, 3, 3}, l = {114, 118, 541, 147}, m = "useConnection", n = {"this", "block", "pool", "connection", "currentContext", "connection$iv", "isReadOnly", "pool", "connection"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "L$0", "L$1"})
    static final class AnonymousClass1<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ConnectionPoolImpl.this.useConnection(false, null, this);
        }
    }

    private final boolean isClosed() {
        return this._isClosed.get();
    }

    /* JADX INFO: renamed from: getTimeout-UwyO8pc$room_runtime_release, reason: not valid java name and from getter */
    public final long getTimeout() {
        return this.timeout;
    }

    /* JADX INFO: renamed from: setTimeout-LRDsOJo$room_runtime_release, reason: not valid java name */
    public final void m7920setTimeoutLRDsOJo$room_runtime_release(long j) {
        this.timeout = j;
    }

    public ConnectionPoolImpl(final SQLiteDriver driver, final String fileName) {
        Intrinsics.checkNotNullParameter(driver, "driver");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.threadLocal = new ThreadLocal<>();
        this._isClosed = new AtomicBoolean(false);
        Duration.Companion companion = Duration.INSTANCE;
        this.timeout = DurationKt.toDuration(30, DurationUnit.SECONDS);
        this.driver = driver;
        Pool pool = new Pool(1, new Function0() { // from class: androidx.room.coroutines.ConnectionPoolImpl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return driver.open(fileName);
            }
        });
        this.readers = pool;
        this.writers = pool;
    }

    public ConnectionPoolImpl(final SQLiteDriver driver, final String fileName, int i, int i2) {
        Intrinsics.checkNotNullParameter(driver, "driver");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.threadLocal = new ThreadLocal<>();
        this._isClosed = new AtomicBoolean(false);
        Duration.Companion companion = Duration.INSTANCE;
        this.timeout = DurationKt.toDuration(30, DurationUnit.SECONDS);
        if (i <= 0) {
            throw new IllegalArgumentException("Maximum number of readers must be greater than 0".toString());
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("Maximum number of writers must be greater than 0".toString());
        }
        this.driver = driver;
        this.readers = new Pool(i, new Function0() { // from class: androidx.room.coroutines.ConnectionPoolImpl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ConnectionPoolImpl._init_$lambda$4(driver, fileName);
            }
        });
        this.writers = new Pool(i2, new Function0() { // from class: androidx.room.coroutines.ConnectionPoolImpl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return driver.open(fileName);
            }
        });
    }

    static final SQLiteConnection _init_$lambda$4(SQLiteDriver sQLiteDriver, String str) {
        SQLiteConnection sQLiteConnectionOpen = sQLiteDriver.open(str);
        SQLite.execSQL(sQLiteConnectionOpen, "PRAGMA query_only = 1");
        return sQLiteConnectionOpen;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    /* JADX WARN: Code duplicated, block: B:89:0x019d A[Catch: all -> 0x01ae, TRY_LEAVE, TryCatch #7 {all -> 0x01ae, blocks: (B:87:0x0197, B:89:0x019d), top: B:126:0x0197 }] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.room.coroutines.ConnectionPool
    public <R> Object useConnection(boolean z, Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        AnonymousClass1 anonymousClass1;
        Pool pool;
        Ref.ObjectRef objectRef;
        Throwable th;
        Pool pool2;
        CoroutineContext coroutineContext;
        Ref.ObjectRef objectRef2;
        Pool pool3;
        Ref.ObjectRef objectRef3;
        CoroutineContext coroutineContext2;
        ConnectionPoolImpl connectionPoolImpl;
        boolean z2;
        Ref.ObjectRef objectRef4;
        ConnectionPoolImpl connectionPoolImpl2;
        Ref.ObjectRef objectRef5;
        T pooledConnectionImpl;
        PooledConnectionImpl pooledConnectionImpl2;
        ConnectionPoolImpl connectionPoolImpl3 = this;
        boolean z3 = z;
        Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> function3 = function2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = connectionPoolImpl3.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = connectionPoolImpl3.new AnonymousClass1(continuation);
        }
        Object objWithContext = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        boolean z4 = true;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objWithContext);
                if (connectionPoolImpl3.isClosed()) {
                    SQLite.throwSQLiteException(21, "Connection pool is closed");
                    throw new KotlinNothingValueException();
                }
                PooledConnectionImpl connectionWrapper = connectionPoolImpl3.threadLocal.get();
                if (connectionWrapper == null) {
                    ConnectionElement connectionElement = (ConnectionElement) anonymousClass1.get$context().get(ConnectionElement.INSTANCE);
                    connectionWrapper = connectionElement != null ? connectionElement.getConnectionWrapper() : null;
                }
                if (connectionWrapper != null) {
                    if (!z3 && connectionWrapper.getIsReadOnly()) {
                        SQLite.throwSQLiteException(1, "Cannot upgrade connection from reader to writer");
                        throw new KotlinNothingValueException();
                    }
                    if (anonymousClass1.get$context().get(ConnectionElement.INSTANCE) == null) {
                        CoroutineContext coroutineContextCreateConnectionContext = connectionPoolImpl3.createConnectionContext(connectionWrapper);
                        C06472 c06472 = new C06472(function3, connectionWrapper, null);
                        anonymousClass1.label = 1;
                        Object objWithContext2 = BuildersKt.withContext(coroutineContextCreateConnectionContext, c06472, anonymousClass1);
                        if (objWithContext2 != coroutine_suspended) {
                            return objWithContext2;
                        }
                    } else {
                        anonymousClass1.label = 2;
                        Object objInvoke = function3.invoke(connectionWrapper, anonymousClass1);
                        if (objInvoke != coroutine_suspended) {
                            return objInvoke;
                        }
                    }
                } else {
                    if (z3) {
                        pool = connectionPoolImpl3.readers;
                    } else {
                        pool = connectionPoolImpl3.writers;
                    }
                    Pool pool4 = pool;
                    objectRef = new Ref.ObjectRef();
                    try {
                        coroutineContext = anonymousClass1.get$context();
                        objectRef2 = new Ref.ObjectRef();
                        try {
                            long j = connectionPoolImpl3.timeout;
                            AnonymousClass2 anonymousClass2 = new AnonymousClass2(objectRef2, pool4, null);
                            anonymousClass1.L$0 = connectionPoolImpl3;
                            anonymousClass1.L$1 = function3;
                            anonymousClass1.L$2 = pool4;
                            anonymousClass1.L$3 = objectRef;
                            anonymousClass1.L$4 = coroutineContext;
                            anonymousClass1.L$5 = objectRef2;
                            anonymousClass1.Z$0 = z3;
                            anonymousClass1.label = 3;
                            if (TimeoutKt.m10479withTimeoutKLykuaI(j, anonymousClass2, anonymousClass1) != coroutine_suspended) {
                                pool3 = pool4;
                                objectRef3 = objectRef;
                                coroutineContext2 = coroutineContext;
                                connectionPoolImpl = connectionPoolImpl3;
                                z2 = z3;
                                objectRef4 = objectRef2;
                                objectRef2 = objectRef4;
                                z3 = z2;
                                objectRef5 = objectRef3;
                                connectionPoolImpl2 = connectionPoolImpl;
                                coroutineContext = coroutineContext2;
                                th = null;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            pool3 = pool4;
                            objectRef3 = objectRef;
                            Ref.ObjectRef objectRef6 = objectRef3;
                            connectionPoolImpl2 = connectionPoolImpl3;
                            objectRef5 = objectRef6;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        pool2 = pool4;
                        throw th;
                    }
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                ResultKt.throwOnFailure(objWithContext);
                return objWithContext;
            }
            if (i == 2) {
                ResultKt.throwOnFailure(objWithContext);
                return objWithContext;
            }
            if (i != 3) {
                if (i != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef5 = (Ref.ObjectRef) anonymousClass1.L$1;
                pool2 = (Pool) anonymousClass1.L$0;
                try {
                    ResultKt.throwOnFailure(objWithContext);
                    try {
                        pooledConnectionImpl2 = (PooledConnectionImpl) objectRef5.element;
                        if (pooledConnectionImpl2 != null) {
                            pooledConnectionImpl2.markRecycled();
                            pooledConnectionImpl2.getDelegate().markReleased();
                            pool2.recycle(pooledConnectionImpl2.getDelegate());
                        }
                    } catch (Throwable unused) {
                    }
                    return objWithContext;
                } catch (Throwable th4) {
                    th = th4;
                    objectRef = objectRef5;
                    th = th;
                    try {
                        throw th;
                    } catch (Throwable th5) {
                        try {
                            PooledConnectionImpl pooledConnectionImpl3 = (PooledConnectionImpl) objectRef.element;
                            if (pooledConnectionImpl3 == null) {
                                throw th5;
                            }
                            pooledConnectionImpl3.markRecycled();
                            pooledConnectionImpl3.getDelegate().markReleased();
                            pool2.recycle(pooledConnectionImpl3.getDelegate());
                            throw th5;
                        } catch (Throwable th6) {
                            ExceptionsKt.addSuppressed(th, th6);
                            throw th5;
                        }
                    }
                }
            }
            z2 = anonymousClass1.Z$0;
            objectRef4 = (Ref.ObjectRef) anonymousClass1.L$5;
            CoroutineContext coroutineContext3 = (CoroutineContext) anonymousClass1.L$4;
            objectRef3 = (Ref.ObjectRef) anonymousClass1.L$3;
            pool3 = (Pool) anonymousClass1.L$2;
            Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> function4 = (Function2) anonymousClass1.L$1;
            connectionPoolImpl = (ConnectionPoolImpl) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(objWithContext);
                coroutineContext2 = coroutineContext3;
                function3 = function4;
                objectRef2 = objectRef4;
                z3 = z2;
                objectRef5 = objectRef3;
                connectionPoolImpl2 = connectionPoolImpl;
                coroutineContext = coroutineContext2;
                th = null;
            } catch (Throwable th7) {
                th = th7;
                objectRef2 = objectRef4;
                z3 = z2;
                connectionPoolImpl3 = connectionPoolImpl;
                coroutineContext = coroutineContext3;
                function3 = function4;
                Ref.ObjectRef objectRef7 = objectRef3;
                connectionPoolImpl2 = connectionPoolImpl3;
                objectRef5 = objectRef7;
            }
            Pair pair = TuplesKt.to(objectRef2.element, th);
            ConnectionWithLock connectionWithLock = (ConnectionWithLock) pair.component1();
            Throwable th8 = (Throwable) pair.component2();
            if (connectionWithLock != null) {
                ConnectionWithLock connectionWithLockMarkAcquired = connectionWithLock.markAcquired(coroutineContext);
                if (connectionPoolImpl2.readers == connectionPoolImpl2.writers || !z3) {
                    z4 = false;
                }
                pooledConnectionImpl = new PooledConnectionImpl(connectionWithLockMarkAcquired, z4);
            } else {
                pooledConnectionImpl = 0;
            }
            objectRef5.element = pooledConnectionImpl;
            if (th8 instanceof TimeoutCancellationException) {
                connectionPoolImpl2.throwTimeoutException(z3);
                throw new KotlinNothingValueException();
            }
            if (th8 != null) {
                throw th8;
            }
            if (objectRef5.element == 0) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            CoroutineContext coroutineContextCreateConnectionContext2 = connectionPoolImpl2.createConnectionContext((PooledConnectionImpl) objectRef5.element);
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(function3, objectRef5, null);
            anonymousClass1.L$0 = pool3;
            anonymousClass1.L$1 = objectRef5;
            anonymousClass1.L$2 = null;
            anonymousClass1.L$3 = null;
            anonymousClass1.L$4 = null;
            anonymousClass1.L$5 = null;
            anonymousClass1.label = 4;
            objWithContext = BuildersKt.withContext(coroutineContextCreateConnectionContext2, anonymousClass4, anonymousClass1);
            if (objWithContext != coroutine_suspended) {
                pool2 = pool3;
                pooledConnectionImpl2 = (PooledConnectionImpl) objectRef5.element;
                if (pooledConnectionImpl2 != null) {
                    pooledConnectionImpl2.markRecycled();
                    pooledConnectionImpl2.getDelegate().markReleased();
                    pool2.recycle(pooledConnectionImpl2.getDelegate());
                }
                return objWithContext;
            }
            return coroutine_suspended;
        } catch (Throwable th9) {
            th = th9;
            objectRef = objectRef5;
            pool2 = pool3;
            th = th;
            throw th;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.room.coroutines.ConnectionPoolImpl$useConnection$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ConnectionPoolImpl.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.ConnectionPoolImpl$useConnection$2", f = "ConnectionPoolImpl.kt", i = {}, l = {115}, m = "invokeSuspend", n = {}, s = {})
    static final class C06472<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<Transactor, Continuation<? super R>, Object> $block;
        final /* synthetic */ PooledConnectionImpl $confinedConnection;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C06472(Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> function2, PooledConnectionImpl pooledConnectionImpl, Continuation<? super C06472> continuation) {
            super(2, continuation);
            this.$block = function2;
            this.$confinedConnection = pooledConnectionImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C06472(this.$block, this.$confinedConnection, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
            return ((C06472) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            Function2<Transactor, Continuation<? super R>, Object> function2 = this.$block;
            PooledConnectionImpl pooledConnectionImpl = this.$confinedConnection;
            this.label = 1;
            Object objInvoke = function2.invoke(pooledConnectionImpl, this);
            return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.room.coroutines.ConnectionPoolImpl$useConnection$4, reason: invalid class name */
    /* JADX INFO: compiled from: ConnectionPoolImpl.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.ConnectionPoolImpl$useConnection$4", f = "ConnectionPoolImpl.kt", i = {}, l = {147}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<Transactor, Continuation<? super R>, Object> $block;
        final /* synthetic */ Ref.ObjectRef<PooledConnectionImpl> $connection;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass4(Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> function2, Ref.ObjectRef<PooledConnectionImpl> objectRef, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$block = function2;
            this.$connection = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass4(this.$block, this.$connection, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.room.coroutines.ConnectionPoolImpl$useConnection$4<R> for r3v3 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r4) {
            /*
                r3 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r3.label
                r2 = 1
                if (r1 == 0) goto L17
                if (r1 != r2) goto Lf
                kotlin.ResultKt.throwOnFailure(r4)
                return r4
            Lf:
                java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
                java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
                r3.<init>(r4)
                throw r3
            L17:
                kotlin.ResultKt.throwOnFailure(r4)
                kotlin.jvm.functions.Function2<androidx.room.Transactor, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r4 = r3.$block
                kotlin.jvm.internal.Ref$ObjectRef<androidx.room.coroutines.PooledConnectionImpl> r1 = r3.$connection
                T r1 = r1.element
                r3.label = r2
                java.lang.Object r3 = r4.invoke(r1, r3)
                if (r3 != r0) goto L29
                return r0
            L29:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.coroutines.ConnectionPoolImpl.AnonymousClass4.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final Object acquireWithTimeout(Pool pool, Continuation<? super Pair<ConnectionWithLock, ? extends Throwable>> continuation) {
        Object obj;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        try {
            obj = null;
            TimeoutKt.m10479withTimeoutKLykuaI(this.timeout, new AnonymousClass2(objectRef, pool, null), continuation);
        } catch (Throwable th) {
            obj = th;
        }
        return TuplesKt.to(objectRef.element, obj);
    }

    /* JADX INFO: renamed from: androidx.room.coroutines.ConnectionPoolImpl$acquireWithTimeout$2, reason: invalid class name */
    /* JADX INFO: compiled from: ConnectionPoolImpl.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.ConnectionPoolImpl$acquireWithTimeout$2", f = "ConnectionPoolImpl.kt", i = {}, l = {171}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<ConnectionWithLock> $connection;
        final /* synthetic */ Pool $this_acquireWithTimeout;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Ref.ObjectRef<ConnectionWithLock> objectRef, Pool pool, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$connection = objectRef;
            this.$this_acquireWithTimeout = pool;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$connection, this.$this_acquireWithTimeout, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            T t;
            Ref.ObjectRef<ConnectionWithLock> objectRef;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<ConnectionWithLock> objectRef2 = this.$connection;
                this.L$0 = objectRef2;
                this.label = 1;
                Object objAcquire = this.$this_acquireWithTimeout.acquire(this);
                if (objAcquire == coroutine_suspended) {
                    return coroutine_suspended;
                }
                t = objAcquire;
                objectRef = objectRef2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                t = obj;
            }
            objectRef.element = t;
            return Unit.INSTANCE;
        }
    }

    private final CoroutineContext createConnectionContext(PooledConnectionImpl connection) {
        return new ConnectionElement(connection).plus(ThreadLocal_jvmAndroidKt.asContextElement(this.threadLocal, connection));
    }

    private final Void throwTimeoutException(boolean isReadOnly) {
        String str = isReadOnly ? "reader" : "writer";
        StringBuilder sb = new StringBuilder();
        sb.append("Timed out attempting to acquire a " + str + " connection.");
        sb.append("\n\nWriter pool:\n");
        this.writers.dump(sb);
        sb.append("Reader pool:");
        sb.append('\n');
        this.readers.dump(sb);
        SQLite.throwSQLiteException(5, sb.toString());
        throw new KotlinNothingValueException();
    }

    @Override // androidx.room.coroutines.ConnectionPool, java.lang.AutoCloseable
    public void close() {
        if (this._isClosed.compareAndSet(false, true)) {
            this.readers.close();
            this.writers.close();
        }
    }
}
