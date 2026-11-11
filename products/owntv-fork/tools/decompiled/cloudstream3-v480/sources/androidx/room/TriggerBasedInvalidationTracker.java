package androidx.room;

import androidx.room.concurrent.CloseBarrier;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.google.android.gms.cast.MediaError;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: InvalidationTracker.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0000\u0018\u0000 O2\u00020\u0001:\u0001OBo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0\u0005\u0012\u000e\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0018\u0010\r\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\b\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u000e\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020&J9\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\fH\u0000¢\u0006\u0004\b-\u0010.J1\u0010/\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\n\u0012\u0004\u0012\u00020+002\u000e\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\nH\u0000¢\u0006\u0004\b2\u00103J#\u00104\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u000e\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\nH\u0002¢\u0006\u0002\u00105J\u0015\u00106\u001a\u00020\f2\u0006\u0010*\u001a\u00020+H\u0000¢\u0006\u0002\b7J\u0015\u00108\u001a\u00020\f2\u0006\u0010*\u001a\u00020+H\u0000¢\u0006\u0002\b9J\u0010\u0010:\u001a\u00020\u0010H\u0080@¢\u0006\u0004\b;\u0010<J\u001e\u0010=\u001a\u00020\u00102\u0006\u0010%\u001a\u00020>2\u0006\u0010?\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010@J\u001e\u0010A\u001a\u00020\u00102\u0006\u0010%\u001a\u00020>2\u0006\u0010?\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010@J@\u0010B\u001a\u00020\f2\u000e\u0010C\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\n2\u000e\b\u0002\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f2\u000e\b\u0002\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00100\u001fH\u0080@¢\u0006\u0004\bF\u0010GJ-\u0010H\u001a\u00020\u00102\u000e\b\u0002\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f2\u000e\b\u0002\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00100\u001fH\u0000¢\u0006\u0002\bIJ\u0014\u0010J\u001a\b\u0012\u0004\u0012\u00020\u000f0\bH\u0082@¢\u0006\u0002\u0010<J\u001c\u0010K\u001a\b\u0012\u0004\u0012\u00020\u000f0\b2\u0006\u0010%\u001a\u00020>H\u0082@¢\u0006\u0002\u0010LJ\r\u0010M\u001a\u00020\u0010H\u0000¢\u0006\u0002\bNR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\b\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\u00060\u001bj\u0002`\u001cX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001dR \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u001fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006P"}, d2 = {"Landroidx/room/TriggerBasedInvalidationTracker;", "", "database", "Landroidx/room/RoomDatabase;", "shadowTablesMap", "", "", "viewTables", "", "tableNames", "", "useTempTable", "", "onInvalidatedTablesIds", "Lkotlin/Function1;", "", "", "<init>", "(Landroidx/room/RoomDatabase;Ljava/util/Map;Ljava/util/Map;[Ljava/lang/String;ZLkotlin/jvm/functions/Function1;)V", "tableIdLookup", "tablesNames", "[Ljava/lang/String;", "observedTableStates", "Landroidx/room/ObservedTableStates;", "observedTableVersions", "Landroidx/room/ObservedTableVersions;", "pendingRefresh", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Landroidx/room/concurrent/AtomicBoolean;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onAllowRefresh", "Lkotlin/Function0;", "getOnAllowRefresh$room_runtime_release", "()Lkotlin/jvm/functions/Function0;", "setOnAllowRefresh$room_runtime_release", "(Lkotlin/jvm/functions/Function0;)V", "configureConnection", "connection", "Landroidx/sqlite/SQLiteConnection;", "createFlow", "Lkotlinx/coroutines/flow/Flow;", "resolvedTableNames", "tableIds", "", "emitInitialState", "createFlow$room_runtime_release", "([Ljava/lang/String;[IZ)Lkotlinx/coroutines/flow/Flow;", "validateTableNames", "Lkotlin/Pair;", "names", "validateTableNames$room_runtime_release", "([Ljava/lang/String;)Lkotlin/Pair;", "resolveViews", "([Ljava/lang/String;)[Ljava/lang/String;", "onObserverAdded", "onObserverAdded$room_runtime_release", "onObserverRemoved", "onObserverRemoved$room_runtime_release", "syncTriggers", "syncTriggers$room_runtime_release", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startTrackingTable", "Landroidx/room/PooledConnection;", "tableId", "(Landroidx/room/PooledConnection;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopTrackingTable", "refreshInvalidation", "tables", "onRefreshScheduled", "onRefreshCompleted", "refreshInvalidation$room_runtime_release", "([Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshInvalidationAsync", "refreshInvalidationAsync$room_runtime_release", "notifyInvalidation", "checkInvalidatedTables", "(Landroidx/room/PooledConnection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetSync", "resetSync$room_runtime_release", "Companion", "room-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TriggerBasedInvalidationTracker {
    private static final String CREATE_TRACKING_TABLE_SQL = "CREATE TEMP TABLE IF NOT EXISTS room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)";
    private static final String DROP_TRACKING_TABLE_SQL = "DROP TABLE IF EXISTS room_table_modification_log";
    private static final String INVALIDATED_COLUMN_NAME = "invalidated";
    private static final String RESET_UPDATED_TABLES_SQL = "UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1";
    private static final String SELECT_UPDATED_TABLES_SQL = "SELECT * FROM room_table_modification_log WHERE invalidated = 1";
    private static final String TABLE_ID_COLUMN_NAME = "table_id";
    private static final String UPDATE_TABLE_NAME = "room_table_modification_log";
    private final RoomDatabase database;
    private final ObservedTableStates observedTableStates;
    private final ObservedTableVersions observedTableVersions;
    private Function0<Boolean> onAllowRefresh;
    private final Function1<Set<Integer>, Unit> onInvalidatedTablesIds;
    private final AtomicBoolean pendingRefresh;
    private final Map<String, String> shadowTablesMap;
    private final Map<String, Integer> tableIdLookup;
    private final String[] tablesNames;
    private final boolean useTempTable;
    private final Map<String, Set<String>> viewTables;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] TRIGGERS = {"INSERT", "UPDATE", "DELETE"};

    /* JADX INFO: renamed from: androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1, reason: invalid class name */
    /* JADX INFO: compiled from: InvalidationTracker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker", f = "InvalidationTracker.kt", i = {0, 1}, l = {440, 448}, m = "checkInvalidatedTables", n = {"connection", "invalidatedTableIds"}, s = {"L$0", "L$0"})
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
            return TriggerBasedInvalidationTracker.this.checkInvalidatedTables(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.room.TriggerBasedInvalidationTracker$notifyInvalidation$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InvalidationTracker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker", f = "InvalidationTracker.kt", i = {0, 0}, l = {MediaError.DetailedErrorCode.HLS_MANIFEST_PLAYLIST}, m = "notifyInvalidation", n = {"this", "$this$ifNotClosed$iv"}, s = {"L$0", "L$1"})
    static final class C06441 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C06441(Continuation<? super C06441> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TriggerBasedInvalidationTracker.this.notifyInvalidation(this);
        }
    }

    /* JADX INFO: renamed from: androidx.room.TriggerBasedInvalidationTracker$startTrackingTable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InvalidationTracker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker", f = "InvalidationTracker.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {323, 328}, m = "startTrackingTable", n = {"this", "connection", "tableId", "this", "connection", "tableName", "tableId"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$2", "I$0"})
    static final class C06451 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C06451(Continuation<? super C06451> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TriggerBasedInvalidationTracker.this.startTrackingTable(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: InvalidationTracker.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker", f = "InvalidationTracker.kt", i = {0, 0}, l = {342}, m = "stopTrackingTable", n = {"connection", "tableName"}, s = {"L$0", "L$1"})
    static final class C06461 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C06461(Continuation<? super C06461> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TriggerBasedInvalidationTracker.this.stopTrackingTable(null, 0, this);
        }
    }

    static final boolean onAllowRefresh$lambda$0() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TriggerBasedInvalidationTracker(RoomDatabase database, Map<String, String> shadowTablesMap, Map<String, ? extends Set<String>> viewTables, String[] tableNames, boolean z, Function1<? super Set<Integer>, Unit> onInvalidatedTablesIds) {
        String lowerCase;
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(shadowTablesMap, "shadowTablesMap");
        Intrinsics.checkNotNullParameter(viewTables, "viewTables");
        Intrinsics.checkNotNullParameter(tableNames, "tableNames");
        Intrinsics.checkNotNullParameter(onInvalidatedTablesIds, "onInvalidatedTablesIds");
        this.database = database;
        this.shadowTablesMap = shadowTablesMap;
        this.viewTables = viewTables;
        this.useTempTable = z;
        this.onInvalidatedTablesIds = onInvalidatedTablesIds;
        this.pendingRefresh = new AtomicBoolean(false);
        this.onAllowRefresh = new Function0() { // from class: androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(TriggerBasedInvalidationTracker.onAllowRefresh$lambda$0());
            }
        };
        this.tableIdLookup = new LinkedHashMap();
        int length = tableNames.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            String lowerCase2 = tableNames[i].toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            this.tableIdLookup.put(lowerCase2, Integer.valueOf(i));
            String str = this.shadowTablesMap.get(tableNames[i]);
            if (str != null) {
                lowerCase = str.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            } else {
                lowerCase = null;
            }
            if (lowerCase != null) {
                lowerCase2 = lowerCase;
            }
            strArr[i] = lowerCase2;
        }
        this.tablesNames = strArr;
        for (Map.Entry<String, String> entry : this.shadowTablesMap.entrySet()) {
            String lowerCase3 = entry.getValue().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
            if (this.tableIdLookup.containsKey(lowerCase3)) {
                String lowerCase4 = entry.getKey().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                Map<String, Integer> map = this.tableIdLookup;
                map.put(lowerCase4, (Integer) MapsKt.getValue(map, lowerCase3));
            }
        }
        this.observedTableStates = new ObservedTableStates(this.tablesNames.length);
        this.observedTableVersions = new ObservedTableVersions(this.tablesNames.length);
    }

    public final Function0<Boolean> getOnAllowRefresh$room_runtime_release() {
        return this.onAllowRefresh;
    }

    public final void setOnAllowRefresh$room_runtime_release(Function0<Boolean> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onAllowRefresh = function0;
    }

    public final void configureConnection(SQLiteConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        SQLiteStatement sQLiteStatementPrepare = connection.prepare("PRAGMA query_only");
        try {
            SQLiteStatement sQLiteStatement = sQLiteStatementPrepare;
            sQLiteStatement.step();
            boolean z = sQLiteStatement.getBoolean(0);
            AutoCloseableKt.closeFinally(sQLiteStatementPrepare, null);
            if (z) {
                return;
            }
            SQLite.execSQL(connection, "PRAGMA temp_store = MEMORY");
            SQLite.execSQL(connection, "PRAGMA recursive_triggers = 1");
            SQLite.execSQL(connection, DROP_TRACKING_TABLE_SQL);
            if (this.useTempTable) {
                SQLite.execSQL(connection, CREATE_TRACKING_TABLE_SQL);
            } else {
                SQLite.execSQL(connection, StringsKt.replace$default(CREATE_TRACKING_TABLE_SQL, "TEMP", "", false, 4, (Object) null));
            }
            this.observedTableStates.forceNeedSync$room_runtime_release();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(sQLiteStatementPrepare, th);
                throw th2;
            }
        }
    }

    public final Flow<Set<String>> createFlow$room_runtime_release(String[] resolvedTableNames, int[] tableIds, boolean emitInitialState) {
        Intrinsics.checkNotNullParameter(resolvedTableNames, "resolvedTableNames");
        Intrinsics.checkNotNullParameter(tableIds, "tableIds");
        return FlowKt.flow(new TriggerBasedInvalidationTracker$createFlow$1(this, tableIds, emitInitialState, resolvedTableNames, null));
    }

    public final Pair<String[], int[]> validateTableNames$room_runtime_release(String[] names) {
        Intrinsics.checkNotNullParameter(names, "names");
        String[] strArrResolveViews = resolveViews(names);
        int length = strArrResolveViews.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            String str = strArrResolveViews[i];
            Map<String, Integer> map = this.tableIdLookup;
            String lowerCase = str.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            Integer num = map.get(lowerCase);
            if (num == null) {
                throw new IllegalArgumentException("There is no table with name " + str);
            }
            iArr[i] = num.intValue();
        }
        return TuplesKt.to(strArrResolveViews, iArr);
    }

    private final String[] resolveViews(String[] names) {
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        for (String str : names) {
            Map<String, Set<String>> map = this.viewTables;
            String lowerCase = str.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            Set<String> set = map.get(lowerCase);
            if (set != null) {
                setCreateSetBuilder.addAll(set);
            } else {
                setCreateSetBuilder.add(str);
            }
        }
        return (String[]) SetsKt.build(setCreateSetBuilder).toArray(new String[0]);
    }

    public final boolean onObserverAdded$room_runtime_release(int[] tableIds) {
        Intrinsics.checkNotNullParameter(tableIds, "tableIds");
        return this.observedTableStates.onObserverAdded$room_runtime_release(tableIds);
    }

    public final boolean onObserverRemoved$room_runtime_release(int[] tableIds) {
        Intrinsics.checkNotNullParameter(tableIds, "tableIds");
        return this.observedTableStates.onObserverRemoved$room_runtime_release(tableIds);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object syncTriggers$room_runtime_release(Continuation<? super Unit> continuation) throws Throwable {
        TriggerBasedInvalidationTracker$syncTriggers$1 triggerBasedInvalidationTracker$syncTriggers$1;
        Throwable th;
        CloseBarrier closeBarrier;
        if (continuation instanceof TriggerBasedInvalidationTracker$syncTriggers$1) {
            triggerBasedInvalidationTracker$syncTriggers$1 = (TriggerBasedInvalidationTracker$syncTriggers$1) continuation;
            if ((triggerBasedInvalidationTracker$syncTriggers$1.label & Integer.MIN_VALUE) != 0) {
                triggerBasedInvalidationTracker$syncTriggers$1.label -= Integer.MIN_VALUE;
            } else {
                triggerBasedInvalidationTracker$syncTriggers$1 = new TriggerBasedInvalidationTracker$syncTriggers$1(this, continuation);
            }
        } else {
            triggerBasedInvalidationTracker$syncTriggers$1 = new TriggerBasedInvalidationTracker$syncTriggers$1(this, continuation);
        }
        Object obj = triggerBasedInvalidationTracker$syncTriggers$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = triggerBasedInvalidationTracker$syncTriggers$1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CloseBarrier closeBarrier2 = this.database.getCloseBarrier();
            if (closeBarrier2.block$room_runtime_release()) {
                try {
                    RoomDatabase roomDatabase = this.database;
                    TriggerBasedInvalidationTracker$syncTriggers$2$1 triggerBasedInvalidationTracker$syncTriggers$2$1 = new TriggerBasedInvalidationTracker$syncTriggers$2$1(this, null);
                    triggerBasedInvalidationTracker$syncTriggers$1.L$0 = closeBarrier2;
                    triggerBasedInvalidationTracker$syncTriggers$1.label = 1;
                    if (roomDatabase.useConnection$room_runtime_release(false, triggerBasedInvalidationTracker$syncTriggers$2$1, triggerBasedInvalidationTracker$syncTriggers$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    closeBarrier = closeBarrier2;
                    closeBarrier.unblock$room_runtime_release();
                } catch (Throwable th2) {
                    th = th2;
                    closeBarrier = closeBarrier2;
                    closeBarrier.unblock$room_runtime_release();
                    throw th;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            closeBarrier = (CloseBarrier) triggerBasedInvalidationTracker$syncTriggers$1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                closeBarrier.unblock$room_runtime_release();
            } catch (Throwable th3) {
                th = th3;
                closeBarrier.unblock$room_runtime_release();
                throw th;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:21:0x0090  */
    /* JADX WARN: Code duplicated, block: B:23:0x0096  */
    /* JADX WARN: Code duplicated, block: B:24:0x0099  */
    /* JADX WARN: Code duplicated, block: B:29:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x007d, code lost:
    
        if (androidx.room.TransactorKt.execSQL(r13, r15, r0) == r1) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00e8, code lost:
    
        if (androidx.room.TransactorKt.execSQL(r6, r15, r0) == r1) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00ea, code lost:
    
        return r1;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00e8 -> B:28:0x00eb). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object startTrackingTable(androidx.room.PooledConnection r13, int r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.startTrackingTable(androidx.room.PooledConnection, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:16:0x0057  */
    /* JADX WARN: Code duplicated, block: B:18:0x0084 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0082 -> B:19:0x0085). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object stopTrackingTable(androidx.room.PooledConnection r9, int r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof androidx.room.TriggerBasedInvalidationTracker.C06461
            if (r0 == 0) goto L14
            r0 = r11
            androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1 r0 = (androidx.room.TriggerBasedInvalidationTracker.C06461) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1 r0 = new androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            int r8 = r0.I$1
            int r9 = r0.I$0
            java.lang.Object r10 = r0.L$2
            java.lang.String[] r10 = (java.lang.String[]) r10
            java.lang.Object r2 = r0.L$1
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r4 = r0.L$0
            androidx.room.PooledConnection r4 = (androidx.room.PooledConnection) r4
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = r10
            r10 = r4
            goto L85
        L3c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L44:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.String[] r8 = r8.tablesNames
            r8 = r8[r10]
            java.lang.String[] r10 = androidx.room.TriggerBasedInvalidationTracker.TRIGGERS
            int r11 = r10.length
            r2 = 0
            r7 = r2
            r2 = r8
            r8 = r11
            r11 = r10
            r10 = r9
            r9 = r7
        L55:
            if (r9 >= r8) goto L87
            r4 = r11[r9]
            androidx.room.TriggerBasedInvalidationTracker$Companion r5 = androidx.room.TriggerBasedInvalidationTracker.INSTANCE
            java.lang.String r4 = androidx.room.TriggerBasedInvalidationTracker.Companion.access$getTriggerName(r5, r2, r4)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "DROP TRIGGER IF EXISTS `"
            r5.<init>(r6)
            r5.append(r4)
            r4 = 96
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            r0.L$0 = r10
            r0.L$1 = r2
            r0.L$2 = r11
            r0.I$0 = r9
            r0.I$1 = r8
            r0.label = r3
            java.lang.Object r4 = androidx.room.TransactorKt.execSQL(r10, r4, r0)
            if (r4 != r1) goto L85
            return r1
        L85:
            int r9 = r9 + r3
            goto L55
        L87:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.stopTrackingTable(androidx.room.PooledConnection, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object refreshInvalidation$room_runtime_release(String[] strArr, Function0<Unit> function0, Function0<Unit> function1, Continuation<? super Boolean> continuation) {
        TriggerBasedInvalidationTracker$refreshInvalidation$1 triggerBasedInvalidationTracker$refreshInvalidation$1;
        int[] iArr;
        if (continuation instanceof TriggerBasedInvalidationTracker$refreshInvalidation$1) {
            triggerBasedInvalidationTracker$refreshInvalidation$1 = (TriggerBasedInvalidationTracker$refreshInvalidation$1) continuation;
            if ((triggerBasedInvalidationTracker$refreshInvalidation$1.label & Integer.MIN_VALUE) != 0) {
                triggerBasedInvalidationTracker$refreshInvalidation$1.label -= Integer.MIN_VALUE;
            } else {
                triggerBasedInvalidationTracker$refreshInvalidation$1 = new TriggerBasedInvalidationTracker$refreshInvalidation$1(this, continuation);
            }
        } else {
            triggerBasedInvalidationTracker$refreshInvalidation$1 = new TriggerBasedInvalidationTracker$refreshInvalidation$1(this, continuation);
        }
        Object objNotifyInvalidation = triggerBasedInvalidationTracker$refreshInvalidation$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = triggerBasedInvalidationTracker$refreshInvalidation$1.label;
        boolean z = true;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objNotifyInvalidation);
                int[] iArrComponent2 = validateTableNames$room_runtime_release(strArr).component2();
                function0.invoke();
                triggerBasedInvalidationTracker$refreshInvalidation$1.L$0 = function1;
                triggerBasedInvalidationTracker$refreshInvalidation$1.L$1 = iArrComponent2;
                triggerBasedInvalidationTracker$refreshInvalidation$1.label = 1;
                objNotifyInvalidation = notifyInvalidation(triggerBasedInvalidationTracker$refreshInvalidation$1);
                if (objNotifyInvalidation == coroutine_suspended) {
                    return coroutine_suspended;
                }
                iArr = iArrComponent2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                iArr = (int[]) triggerBasedInvalidationTracker$refreshInvalidation$1.L$1;
                function1 = (Function0) triggerBasedInvalidationTracker$refreshInvalidation$1.L$0;
                ResultKt.throwOnFailure(objNotifyInvalidation);
            }
            Set set = (Set) objNotifyInvalidation;
            if (iArr.length == 0) {
                if (set.isEmpty()) {
                    z = false;
                    break;
                }
            } else {
                int length = iArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z = false;
                        break;
                    }
                    if (set.contains(Boxing.boxInt(iArr[i2]))) {
                        break;
                    }
                    i2++;
                }
            }
            Boolean boolBoxBoolean = Boxing.boxBoolean(z);
            function1.invoke();
            return boolBoxBoolean;
        } catch (Throwable th) {
            function1.invoke();
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object refreshInvalidation$room_runtime_release$default(TriggerBasedInvalidationTracker triggerBasedInvalidationTracker, String[] strArr, Function0 function0, Function0 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = new Function0() { // from class: androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            function1 = new Function0() { // from class: androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        return triggerBasedInvalidationTracker.refreshInvalidation$room_runtime_release(strArr, function0, function1, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void refreshInvalidationAsync$room_runtime_release$default(TriggerBasedInvalidationTracker triggerBasedInvalidationTracker, Function0 function0, Function0 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = new Function0() { // from class: androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 2) != 0) {
            function1 = new Function0() { // from class: androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        triggerBasedInvalidationTracker.refreshInvalidationAsync$room_runtime_release(function0, function1);
    }

    public final void refreshInvalidationAsync$room_runtime_release(Function0<Unit> onRefreshScheduled, Function0<Unit> onRefreshCompleted) {
        Intrinsics.checkNotNullParameter(onRefreshScheduled, "onRefreshScheduled");
        Intrinsics.checkNotNullParameter(onRefreshCompleted, "onRefreshCompleted");
        if (this.pendingRefresh.compareAndSet(false, true)) {
            onRefreshScheduled.invoke();
            BuildersKt__Builders_commonKt.launch$default(this.database.getCoroutineScope(), new CoroutineName("Room Invalidation Tracker Refresh"), null, new TriggerBasedInvalidationTracker$refreshInvalidationAsync$3(this, onRefreshCompleted, null), 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:35:0x0099 A[Catch: all -> 0x00a7, TRY_LEAVE, TryCatch #1 {all -> 0x00a7, blocks: (B:33:0x008e, B:35:0x0099, B:20:0x0050, B:22:0x0059, B:25:0x0061, B:27:0x006f, B:30:0x0077), top: B:46:0x0050 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object notifyInvalidation(Continuation<? super Set<Integer>> continuation) throws Throwable {
        C06441 c06441;
        CloseBarrier closeBarrier;
        Throwable th;
        CloseBarrier closeBarrier2;
        Object objUseConnection$room_runtime_release;
        Set<Integer> set;
        if (continuation instanceof C06441) {
            c06441 = (C06441) continuation;
            if ((c06441.label & Integer.MIN_VALUE) != 0) {
                c06441.label -= Integer.MIN_VALUE;
            } else {
                c06441 = new C06441(continuation);
            }
        } else {
            c06441 = new C06441(continuation);
        }
        Object obj = c06441.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c06441.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            closeBarrier2 = (CloseBarrier) c06441.L$1;
            TriggerBasedInvalidationTracker triggerBasedInvalidationTracker = (TriggerBasedInvalidationTracker) c06441.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                closeBarrier = closeBarrier2;
                this = triggerBasedInvalidationTracker;
                objUseConnection$room_runtime_release = obj;
                set = (Set) objUseConnection$room_runtime_release;
                if (!set.isEmpty()) {
                    this.observedTableVersions.increment(set);
                    this.onInvalidatedTablesIds.invoke(set);
                }
                closeBarrier.unblock$room_runtime_release();
                return set;
            } catch (Throwable th2) {
                th = th2;
                closeBarrier2.unblock$room_runtime_release();
                throw th;
            }
        }
        ResultKt.throwOnFailure(obj);
        closeBarrier = this.database.getCloseBarrier();
        if (closeBarrier.block$room_runtime_release()) {
            try {
                if (!this.pendingRefresh.compareAndSet(true, false)) {
                    Set setEmptySet = SetsKt.emptySet();
                    closeBarrier.unblock$room_runtime_release();
                    return setEmptySet;
                }
                if (!this.onAllowRefresh.invoke().booleanValue()) {
                    Set setEmptySet2 = SetsKt.emptySet();
                    closeBarrier.unblock$room_runtime_release();
                    return setEmptySet2;
                }
                RoomDatabase roomDatabase = this.database;
                TriggerBasedInvalidationTracker$notifyInvalidation$2$invalidatedTableIds$1 triggerBasedInvalidationTracker$notifyInvalidation$2$invalidatedTableIds$1 = new TriggerBasedInvalidationTracker$notifyInvalidation$2$invalidatedTableIds$1(this, null);
                c06441.L$0 = this;
                c06441.L$1 = closeBarrier;
                c06441.label = 1;
                objUseConnection$room_runtime_release = roomDatabase.useConnection$room_runtime_release(false, triggerBasedInvalidationTracker$notifyInvalidation$2$invalidatedTableIds$1, c06441);
                if (objUseConnection$room_runtime_release == coroutine_suspended) {
                    return coroutine_suspended;
                }
                set = (Set) objUseConnection$room_runtime_release;
                if (!set.isEmpty()) {
                    this.observedTableVersions.increment(set);
                    this.onInvalidatedTablesIds.invoke(set);
                }
                closeBarrier.unblock$room_runtime_release();
                return set;
            } catch (Throwable th3) {
                th = th3;
                closeBarrier2 = closeBarrier;
                closeBarrier2.unblock$room_runtime_release();
                throw th;
            }
        }
        return SetsKt.emptySet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object checkInvalidatedTables(PooledConnection pooledConnection, Continuation<? super Set<Integer>> continuation) {
        AnonymousClass1 anonymousClass1;
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
        Object objUsePrepared = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objUsePrepared);
            Function1 function1 = new Function1() { // from class: androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TriggerBasedInvalidationTracker.checkInvalidatedTables$lambda$14((SQLiteStatement) obj);
                }
            };
            anonymousClass1.L$0 = pooledConnection;
            anonymousClass1.label = 1;
            objUsePrepared = pooledConnection.usePrepared(SELECT_UPDATED_TABLES_SQL, function1, anonymousClass1);
            if (objUsePrepared != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Set set = (Set) anonymousClass1.L$0;
            ResultKt.throwOnFailure(objUsePrepared);
            return set;
        }
        pooledConnection = (PooledConnection) anonymousClass1.L$0;
        ResultKt.throwOnFailure(objUsePrepared);
        Set set2 = (Set) objUsePrepared;
        if (!set2.isEmpty()) {
            anonymousClass1.L$0 = set2;
            anonymousClass1.label = 2;
            if (TransactorKt.execSQL(pooledConnection, RESET_UPDATED_TABLES_SQL, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return set2;
    }

    static final Set checkInvalidatedTables$lambda$14(SQLiteStatement statement) {
        Intrinsics.checkNotNullParameter(statement, "statement");
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        while (statement.step()) {
            setCreateSetBuilder.add(Integer.valueOf((int) statement.getLong(0)));
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    public final void resetSync$room_runtime_release() {
        this.observedTableStates.resetTriggerState$room_runtime_release();
    }

    /* JADX INFO: compiled from: InvalidationTracker.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/room/TriggerBasedInvalidationTracker$Companion;", "", "<init>", "()V", "TRIGGERS", "", "", "[Ljava/lang/String;", "UPDATE_TABLE_NAME", "TABLE_ID_COLUMN_NAME", "INVALIDATED_COLUMN_NAME", "CREATE_TRACKING_TABLE_SQL", "DROP_TRACKING_TABLE_SQL", "SELECT_UPDATED_TABLES_SQL", "RESET_UPDATED_TABLES_SQL", "getTriggerName", "tableName", "triggerType", "room-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getTriggerName(String tableName, String triggerType) {
            return "room_table_modification_trigger_" + tableName + '_' + triggerType;
        }
    }
}
