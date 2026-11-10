package androidx.room.paging;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.paging.PositionalDataSource;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import b.a.a.a.a;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public abstract class LimitOffsetDataSource<T> extends PositionalDataSource<T> {
    public final String mCountQuery;
    public final RoomDatabase mDb;
    public final boolean mInTransaction;
    public final String mLimitOffsetQuery;
    public final InvalidationTracker.Observer mObserver;
    public final RoomSQLiteQuery mSourceQuery;

    public LimitOffsetDataSource(RoomDatabase roomDatabase, SupportSQLiteQuery supportSQLiteQuery, boolean z, String... strArr) {
        this(roomDatabase, RoomSQLiteQuery.copyFrom(supportSQLiteQuery), z, strArr);
    }

    private RoomSQLiteQuery getSQLiteQuery(int i, int i2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(this.mLimitOffsetQuery, this.mSourceQuery.getArgCount() + 2);
        roomSQLiteQueryAcquire.copyArgumentsFrom(this.mSourceQuery);
        roomSQLiteQueryAcquire.bindLong(roomSQLiteQueryAcquire.getArgCount() - 1, i2);
        roomSQLiteQueryAcquire.bindLong(roomSQLiteQueryAcquire.getArgCount(), i);
        return roomSQLiteQueryAcquire;
    }

    public abstract List<T> convertRows(Cursor cursor);

    public int countItems() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(this.mCountQuery, this.mSourceQuery.getArgCount());
        roomSQLiteQueryAcquire.copyArgumentsFrom(this.mSourceQuery);
        Cursor cursorQuery = this.mDb.query(roomSQLiteQueryAcquire);
        try {
            if (cursorQuery.moveToFirst()) {
                return cursorQuery.getInt(0);
            }
            return 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public boolean isInvalid() {
        this.mDb.getInvalidationTracker().refreshVersionsSync();
        return super.isInvalid();
    }

    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams loadInitialParams, @NonNull PositionalDataSource.LoadInitialCallback<T> loadInitialCallback) throws Throwable {
        RoomSQLiteQuery sQLiteQuery;
        List<T> listConvertRows;
        int iComputeInitialLoadPosition;
        List<T> listEmptyList = Collections.emptyList();
        this.mDb.beginTransaction();
        Cursor cursorQuery = null;
        try {
            int iCountItems = countItems();
            if (iCountItems != 0) {
                iComputeInitialLoadPosition = computeInitialLoadPosition(loadInitialParams, iCountItems);
                sQLiteQuery = getSQLiteQuery(iComputeInitialLoadPosition, computeInitialLoadSize(loadInitialParams, iComputeInitialLoadPosition, iCountItems));
                try {
                    cursorQuery = this.mDb.query(sQLiteQuery);
                    listConvertRows = convertRows(cursorQuery);
                    this.mDb.setTransactionSuccessful();
                } catch (Throwable th) {
                    th = th;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    this.mDb.endTransaction();
                    if (sQLiteQuery != null) {
                        sQLiteQuery.release();
                    }
                    throw th;
                }
            } else {
                listConvertRows = listEmptyList;
                iComputeInitialLoadPosition = 0;
                sQLiteQuery = null;
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            this.mDb.endTransaction();
            if (sQLiteQuery != null) {
                sQLiteQuery.release();
            }
            loadInitialCallback.onResult(listConvertRows, iComputeInitialLoadPosition, iCountItems);
        } catch (Throwable th2) {
            th = th2;
            sQLiteQuery = null;
        }
    }

    public void loadRange(@NonNull PositionalDataSource.LoadRangeParams loadRangeParams, @NonNull PositionalDataSource.LoadRangeCallback<T> loadRangeCallback) {
        loadRangeCallback.onResult(loadRange(loadRangeParams.startPosition, loadRangeParams.loadSize));
    }

    public LimitOffsetDataSource(RoomDatabase roomDatabase, RoomSQLiteQuery roomSQLiteQuery, boolean z, String... strArr) {
        this.mDb = roomDatabase;
        this.mSourceQuery = roomSQLiteQuery;
        this.mInTransaction = z;
        StringBuilder sbA = a.a("SELECT COUNT(*) FROM ( ");
        sbA.append(this.mSourceQuery.getSql());
        sbA.append(" )");
        this.mCountQuery = sbA.toString();
        StringBuilder sbA2 = a.a("SELECT * FROM ( ");
        sbA2.append(this.mSourceQuery.getSql());
        sbA2.append(" ) LIMIT ? OFFSET ?");
        this.mLimitOffsetQuery = sbA2.toString();
        this.mObserver = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.paging.LimitOffsetDataSource.1
            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                LimitOffsetDataSource.this.invalidate();
            }
        };
        roomDatabase.getInvalidationTracker().addWeakObserver(this.mObserver);
    }

    @NonNull
    public List<T> loadRange(int i, int i2) {
        RoomSQLiteQuery sQLiteQuery = getSQLiteQuery(i, i2);
        if (!this.mInTransaction) {
            Cursor cursorQuery = this.mDb.query(sQLiteQuery);
            try {
                return convertRows(cursorQuery);
            } finally {
                cursorQuery.close();
                sQLiteQuery.release();
            }
        }
        this.mDb.beginTransaction();
        Cursor cursorQuery2 = null;
        try {
            cursorQuery2 = this.mDb.query(sQLiteQuery);
            List<T> listConvertRows = convertRows(cursorQuery2);
            this.mDb.setTransactionSuccessful();
            return listConvertRows;
        } finally {
            if (cursorQuery2 != null) {
                cursorQuery2.close();
            }
            this.mDb.endTransaction();
            sQLiteQuery.release();
        }
    }
}
