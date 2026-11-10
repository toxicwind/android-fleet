package com.ju.lib.datalayer.database.impl;

import android.database.sqlite.SQLiteDatabase;
import com.ju.lib.datalayer.database.JuOrm;
import com.ju.lib.datalayer.database.asist.Checker;
import com.ju.lib.datalayer.database.asist.CollSpliter;
import com.ju.lib.datalayer.database.asist.QueryBuilder;
import com.ju.lib.datalayer.database.asist.SQLBuilder;
import com.ju.lib.datalayer.database.asist.SQLStatement;
import com.ju.lib.datalayer.database.asist.WhereBuilder;
import com.ju.lib.datalayer.database.cache.manager.CacheManager;
import com.ju.lib.datalayer.database.config.DatabaseConfig;
import com.ju.lib.datalayer.database.model.ColumnsValue;
import com.ju.lib.datalayer.database.model.ConflictAlgorithm;
import com.ju.lib.datalayer.database.model.EntityTable;
import com.ju.lib.datalayer.database.table.TableManager;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes2.dex */
public class SingleSQLiteImpl extends JuOrm {
    public SingleSQLiteImpl(JuOrm juOrm) {
        super(juOrm);
    }

    public static synchronized JuOrm newInstance(DatabaseConfig databaseConfig) {
        return new SingleSQLiteImpl(databaseConfig);
    }

    @Override // com.ju.lib.datalayer.database.JuOrm
    public JuOrm cascade() {
        if (this.otherDatabase == null) {
            this.otherDatabase = new CascadeSQLiteImpl(this);
        }
        return this.otherDatabase;
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public int delete(Object obj) {
        if (!this.mTableManager.isSQLTableCreated(TableManager.getTable(obj).name)) {
            return -1;
        }
        acquireReference();
        try {
            return SQLBuilder.buildDeleteSql(obj).execDelete(this.mHelper.getWritableDatabase());
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int deleteAll(Class<T> cls) {
        if (!this.mTableManager.isSQLTableCreated(TableManager.getTable(cls, false).name)) {
            return -1;
        }
        acquireReference();
        try {
            return SQLBuilder.buildDeleteAllSql(cls).execDelete(this.mHelper.getWritableDatabase());
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        } finally {
            CacheManager.clearCache(cls);
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public long insert(Object obj) {
        return insert(obj, (ConflictAlgorithm) null);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> ArrayList<T> query(QueryBuilder<T> queryBuilder) {
        if (!this.mTableManager.isSQLTableCreated(TableManager.getTable(queryBuilder.getQueryClass(), false).name)) {
            return new ArrayList<>();
        }
        acquireReference();
        try {
            return queryBuilder.createStatement().query(this.mHelper.getReadableDatabase(), queryBuilder.getQueryClass());
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> ArrayList<T> queryAll(Class<T> cls) {
        return query(new QueryBuilder<>(cls));
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> T queryById(long j, Class<T> cls) {
        return (T) queryById(String.valueOf(j), cls);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public long save(Object obj) {
        acquireReference();
        try {
            SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
            this.mTableManager.checkOrCreateTable(writableDatabase, obj);
            return SQLBuilder.buildReplaceSql(obj).execInsert(writableDatabase, obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1L;
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.JuOrm
    public JuOrm single() {
        return this;
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public int update(Object obj) {
        return update(obj, (ColumnsValue) null, (ConflictAlgorithm) null);
    }

    public SingleSQLiteImpl(DatabaseConfig databaseConfig) {
        super(databaseConfig);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public long insert(Object obj, ConflictAlgorithm conflictAlgorithm) {
        acquireReference();
        try {
            SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
            this.mTableManager.checkOrCreateTable(writableDatabase, obj);
            return SQLBuilder.buildInsertSql(obj, conflictAlgorithm).execInsert(writableDatabase, obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1L;
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> T queryById(String str, Class<T> cls) {
        EntityTable table = TableManager.getTable(cls, false);
        if (!this.mTableManager.isSQLTableCreated(table.name)) {
            return null;
        }
        acquireReference();
        try {
            String[] strArr = {str};
            ArrayList<T> arrayListQuery = new QueryBuilder(cls).where(table.key.column + "=?", strArr).createStatement().query(this.mHelper.getReadableDatabase(), cls);
            if (Checker.isEmpty(arrayListQuery)) {
                return null;
            }
            return arrayListQuery.get(0);
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public int update(Object obj, ConflictAlgorithm conflictAlgorithm) {
        return update(obj, (ColumnsValue) null, conflictAlgorithm);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public int update(Object obj, ColumnsValue columnsValue, ConflictAlgorithm conflictAlgorithm) {
        acquireReference();
        try {
            SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
            this.mTableManager.checkOrCreateTable(writableDatabase, obj);
            return SQLBuilder.buildUpdateSql(obj, columnsValue, conflictAlgorithm).execUpdate(writableDatabase);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int save(Collection<T> collection) {
        acquireReference();
        try {
            try {
                if (!Checker.isEmpty((Collection<?>) collection)) {
                    SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
                    T next = collection.iterator().next();
                    this.mTableManager.checkOrCreateTable(writableDatabase, next);
                    return SQLBuilder.buildReplaceAllSql(next).execInsertCollection(writableDatabase, collection);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return -1;
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int delete(Collection<T> collection) {
        acquireReference();
        try {
            try {
                if (!Checker.isEmpty((Collection<?>) collection)) {
                    if (this.mTableManager.isSQLTableCreated(TableManager.getTable(collection.iterator().next()).name)) {
                        final SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
                        writableDatabase.beginTransaction();
                        try {
                            int iSplit = CollSpliter.split(collection, 999, new CollSpliter.Spliter<T>() { // from class: com.ju.lib.datalayer.database.impl.SingleSQLiteImpl.1
                                @Override // com.ju.lib.datalayer.database.asist.CollSpliter.Spliter
                                public int oneSplit(ArrayList<T> arrayList) {
                                    return SQLBuilder.buildDeleteSql((Collection<?>) arrayList).execDeleteCollection(writableDatabase, arrayList);
                                }
                            });
                            writableDatabase.setTransactionSuccessful();
                            writableDatabase.endTransaction();
                            releaseReference();
                            return iSplit;
                        } catch (Throwable th) {
                            writableDatabase.endTransaction();
                            throw th;
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            releaseReference();
            return -1;
        } catch (Throwable th2) {
            releaseReference();
            throw th2;
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int insert(Collection<T> collection) {
        return insert((Collection) collection, (ConflictAlgorithm) null);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int insert(Collection<T> collection, ConflictAlgorithm conflictAlgorithm) {
        acquireReference();
        try {
            try {
                if (!Checker.isEmpty((Collection<?>) collection)) {
                    SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
                    T next = collection.iterator().next();
                    SQLStatement sQLStatementBuildInsertAllSql = SQLBuilder.buildInsertAllSql(next, conflictAlgorithm);
                    this.mTableManager.checkOrCreateTable(writableDatabase, next);
                    return sQLStatementBuildInsertAllSql.execInsertCollection(writableDatabase, collection);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return -1;
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int update(Collection<T> collection) {
        return update((Collection) collection, (ColumnsValue) null, (ConflictAlgorithm) null);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int update(Collection<T> collection, ConflictAlgorithm conflictAlgorithm) {
        return update((Collection) collection, (ColumnsValue) null, conflictAlgorithm);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int update(Collection<T> collection, ColumnsValue columnsValue, ConflictAlgorithm conflictAlgorithm) {
        acquireReference();
        try {
            try {
                if (!Checker.isEmpty((Collection<?>) collection)) {
                    SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
                    T next = collection.iterator().next();
                    this.mTableManager.checkOrCreateTable(writableDatabase, next);
                    return SQLBuilder.buildUpdateAllSql(next, columnsValue, conflictAlgorithm).execUpdateCollection(writableDatabase, collection, columnsValue);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return -1;
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public int delete(WhereBuilder whereBuilder) {
        if (!this.mTableManager.isSQLTableCreated(TableManager.getTable(whereBuilder.getTableClass(), false).name)) {
            return -1;
        }
        acquireReference();
        try {
            return whereBuilder.createStatementDelete().execDelete(this.mHelper.getWritableDatabase());
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        } finally {
            CacheManager.clearCache(WhereBuilder.class);
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int delete(Class<T> cls, long j, long j2, String str) {
        if (!this.mTableManager.isSQLTableCreated(TableManager.getTable(cls, false).name)) {
            return -1;
        }
        acquireReference();
        try {
            if (j >= 0 && j2 >= j) {
                if (j != 0) {
                    j--;
                }
                long j3 = j;
                long j4 = j2 == 2147483647L ? -1L : j2 - j3;
                CacheManager.clearCache(cls);
                int iExecDelete = SQLBuilder.buildDeleteSql(cls, j3, j4, str).execDelete(this.mHelper.getWritableDatabase());
                releaseReference();
                return iExecDelete;
            }
            throw new RuntimeException("start must >=0 and smaller than end");
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        } finally {
            releaseReference();
        }
    }
}
