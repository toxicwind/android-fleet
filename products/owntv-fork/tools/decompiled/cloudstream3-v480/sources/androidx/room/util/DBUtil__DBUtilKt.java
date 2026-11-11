package androidx.room.util;

import android.database.SQLException;
import androidx.room.PooledConnection;
import androidx.room.RoomDatabase;
import androidx.room.Transactor;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DBUtil.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000D\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aN\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042$\b\u0004\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007H\u0080H¢\u0006\u0002\u0010\u000b\u001a\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007\u001a\u0015\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0002\b\u0017¨\u0006\u0018"}, d2 = {"internalPerform", "R", "Landroidx/room/RoomDatabase;", "isReadOnly", "", "inTransaction", "block", "Lkotlin/Function2;", "Landroidx/room/PooledConnection;", "Lkotlin/coroutines/Continuation;", "", "(Landroidx/room/RoomDatabase;ZZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dropFtsSyncTriggers", "", "connection", "Landroidx/sqlite/SQLiteConnection;", "foreignKeyCheck", "db", "tableName", "", "processForeignKeyCheckFailure", "stmt", "Landroidx/sqlite/SQLiteStatement;", "processForeignKeyCheckFailure$DBUtil__DBUtilKt", "room-runtime_release"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "androidx/room/util/DBUtil")
final /* synthetic */ class DBUtil__DBUtilKt {

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.room.util.DBUtil__DBUtilKt$internalPerform$2, reason: invalid class name */
    /* JADX INFO: compiled from: DBUtil.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "R", "transactor", "Landroidx/room/Transactor;"}, k = 3, mv = {2, 0, 0}, xi = 176)
    @DebugMetadata(c = "androidx.room.util.DBUtil__DBUtilKt$internalPerform$2", f = "DBUtil.kt", i = {0, 0, 1, 1, 2, 3}, l = {56, 57, 59, 60, 65}, m = "invokeSuspend", n = {"transactor", "type", "transactor", "type", "transactor", "result"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$0"})
    public static final class AnonymousClass2<R> extends SuspendLambda implements Function2<Transactor, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<PooledConnection, Continuation<? super R>, Object> $block;
        final /* synthetic */ boolean $inTransaction;
        final /* synthetic */ boolean $isReadOnly;
        final /* synthetic */ RoomDatabase $this_internalPerform;
        /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(boolean z, boolean z2, RoomDatabase roomDatabase, Function2<? super PooledConnection, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$inTransaction = z;
            this.$isReadOnly = z2;
            this.$this_internalPerform = roomDatabase;
            this.$block = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$inTransaction, this.$isReadOnly, this.$this_internalPerform, this.$block, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Transactor transactor, Continuation<? super R> continuation) {
            return ((AnonymousClass2) create(transactor, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:39:0x00b5 A[PHI: r1 r9
          0x00b5: PHI (r1v12 androidx.room.Transactor) = (r1v9 androidx.room.Transactor), (r1v19 androidx.room.Transactor) binds: [B:37:0x00b2, B:14:0x002a] A[DONT_GENERATE, DONT_INLINE]
          0x00b5: PHI (r9v14 java.lang.Object) = (r9v12 java.lang.Object), (r9v0 java.lang.Object) binds: [B:37:0x00b2, B:14:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:41:0x00b9  */
        /* JADX WARN: Code duplicated, block: B:44:0x00c7  */
        /* JADX WARN: Code duplicated, block: B:47:0x00d1  */
        /* JADX WARN: Code duplicated, block: B:49:0x00db A[RETURN] */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0092, code lost:
        
            if (r8.$this_internalPerform.getInvalidationTracker().sync$room_runtime_release(r8) == r0) goto L52;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                Method dump skipped, instruction units count: 232
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.DBUtil__DBUtilKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        public final Object invokeSuspend$$forInline(Object obj) throws Throwable {
            Transactor.SQLiteTransactionType sQLiteTransactionType;
            Transactor transactor = (Transactor) this.L$0;
            if (this.$inTransaction) {
                if (this.$isReadOnly) {
                    sQLiteTransactionType = Transactor.SQLiteTransactionType.DEFERRED;
                } else {
                    sQLiteTransactionType = Transactor.SQLiteTransactionType.IMMEDIATE;
                }
                if (!this.$isReadOnly) {
                    AnonymousClass2<R> anonymousClass2 = this;
                    if (!((Boolean) transactor.inTransaction(anonymousClass2)).booleanValue()) {
                        this.$this_internalPerform.getInvalidationTracker().sync$room_runtime_release(anonymousClass2);
                    }
                }
                AnonymousClass2<R> anonymousClass3 = this;
                Object objWithTransaction = transactor.withTransaction(sQLiteTransactionType, new DBUtil__DBUtilKt$internalPerform$2$result$1(this.$block, null), anonymousClass3);
                if (!this.$isReadOnly && !((Boolean) transactor.inTransaction(anonymousClass3)).booleanValue()) {
                    this.$this_internalPerform.getInvalidationTracker().refreshAsync();
                }
                return objWithTransaction;
            }
            return this.$block.invoke(transactor, this);
        }
    }

    public static final <R> Object internalPerform(RoomDatabase roomDatabase, boolean z, boolean z2, Function2<? super PooledConnection, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        return roomDatabase.useConnection$room_runtime_release(z, new AnonymousClass2(z2, z, roomDatabase, function2, null), continuation);
    }

    public static final void dropFtsSyncTriggers(SQLiteConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        SQLiteStatement sQLiteStatementPrepare = connection.prepare("SELECT name FROM sqlite_master WHERE type = 'trigger'");
        try {
            SQLiteStatement sQLiteStatement = sQLiteStatementPrepare;
            while (sQLiteStatement.step()) {
                listCreateListBuilder.add(sQLiteStatement.getText(0));
            }
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(sQLiteStatementPrepare, null);
            for (String str : CollectionsKt.build(listCreateListBuilder)) {
                if (StringsKt.startsWith$default(str, "room_fts_content_sync_", false, 2, (Object) null)) {
                    SQLite.execSQL(connection, "DROP TRIGGER IF EXISTS " + str);
                }
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(sQLiteStatementPrepare, th);
                throw th2;
            }
        }
    }

    public static final void foreignKeyCheck(SQLiteConnection db, String tableName) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        SQLiteStatement sQLiteStatementPrepare = db.prepare("PRAGMA foreign_key_check(`" + tableName + "`)");
        try {
            SQLiteStatement sQLiteStatement = sQLiteStatementPrepare;
            if (sQLiteStatement.step()) {
                throw new SQLException(processForeignKeyCheckFailure$DBUtil__DBUtilKt(sQLiteStatement));
            }
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(sQLiteStatementPrepare, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(sQLiteStatementPrepare, th);
                throw th2;
            }
        }
    }

    private static final String processForeignKeyCheckFailure$DBUtil__DBUtilKt(SQLiteStatement sQLiteStatement) {
        StringBuilder sb = new StringBuilder();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        do {
            if (i == 0) {
                sb.append("Foreign key violation(s) detected in '");
                sb.append(sQLiteStatement.getText(0));
                sb.append("'.\n");
            }
            String text = sQLiteStatement.getText(3);
            if (!linkedHashMap.containsKey(text)) {
                linkedHashMap.put(text, sQLiteStatement.getText(2));
            }
            i++;
        } while (sQLiteStatement.step());
        sb.append("Number of different violations discovered: ");
        sb.append(linkedHashMap.keySet().size());
        sb.append("\nNumber of rows in violation: ");
        sb.append(i);
        sb.append("\nViolation(s) detected in the following constraint(s):\n");
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            sb.append("\tParent Table = ");
            sb.append(str2);
            sb.append(", Foreign Key Constraint Index = ");
            sb.append(str);
            sb.append("\n");
        }
        return sb.toString();
    }
}
