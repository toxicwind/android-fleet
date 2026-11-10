package androidx.room;

import androidx.annotation.RestrictTo;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public abstract class SharedSQLiteStatement {
    public final RoomDatabase mDatabase;
    public final AtomicBoolean mLock = new AtomicBoolean(false);
    public volatile SupportSQLiteStatement mStmt;

    public SharedSQLiteStatement(RoomDatabase roomDatabase) {
        this.mDatabase = roomDatabase;
    }

    private SupportSQLiteStatement createNewStatement() {
        return this.mDatabase.compileStatement(createQuery());
    }

    private SupportSQLiteStatement getStmt(boolean z) {
        if (!z) {
            return createNewStatement();
        }
        if (this.mStmt == null) {
            this.mStmt = createNewStatement();
        }
        return this.mStmt;
    }

    public SupportSQLiteStatement acquire() {
        assertNotMainThread();
        return getStmt(this.mLock.compareAndSet(false, true));
    }

    public void assertNotMainThread() {
        this.mDatabase.assertNotMainThread();
    }

    public abstract String createQuery();

    public void release(SupportSQLiteStatement supportSQLiteStatement) {
        if (supportSQLiteStatement == this.mStmt) {
            this.mLock.set(false);
        }
    }
}
