package com.ju.lib.datalayer.database.asist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import b.a.a.a.a;
import com.ju.lib.datalayer.database.JuOrmLog;
import com.ju.lib.datalayer.database.cache.manager.CacheManager;
import com.ju.lib.datalayer.database.model.ColumnsValue;
import com.ju.lib.datalayer.database.model.EntityTable;
import com.ju.lib.datalayer.database.model.MapInfo;
import com.ju.lib.datalayer.database.model.Property;
import com.ju.lib.datalayer.database.table.TableManager;
import com.ju.lib.datalayer.database.utils.ClassUtils;
import com.ju.lib.datalayer.database.utils.DataUtils;
import com.ju.lib.datalayer.database.utils.FieldUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class SQLStatement implements Serializable {
    public static final int IN_TOP_LIMIT = 999;
    public static final short NONE = -1;
    public static final short NORMAL = 0;
    public static final String TAG = "SQLStatement";
    public static final long serialVersionUID = 6923157594094532226L;
    public Object[] bindArgs;
    public SQLiteStatement mStatement;
    public String sql;

    public SQLStatement() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mapRelationToDb(Object obj, final boolean z, final boolean z2, SQLiteDatabase sQLiteDatabase, final TableManager tableManager) {
        final MapInfo mapInfoBuildMappingInfo = SQLBuilder.buildMappingInfo(obj, z, tableManager);
        if (mapInfoBuildMappingInfo == null || mapInfoBuildMappingInfo.isEmpty()) {
            return;
        }
        Transaction.execute(sQLiteDatabase, new Transaction.Worker<Boolean>() { // from class: com.ju.lib.datalayer.database.asist.SQLStatement.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ju.lib.datalayer.database.asist.Transaction.Worker
            public Boolean doTransaction(SQLiteDatabase sQLiteDatabase2) {
                ArrayList<SQLStatement> arrayList;
                if (z && z2) {
                    for (MapInfo.MapTable mapTable : mapInfoBuildMappingInfo.tableList) {
                        tableManager.checkOrCreateMappingTable(sQLiteDatabase2, mapTable.name, mapTable.column1, mapTable.column2);
                    }
                }
                ArrayList<SQLStatement> arrayList2 = mapInfoBuildMappingInfo.delOldRelationSQL;
                if (arrayList2 != null) {
                    Iterator<SQLStatement> it = arrayList2.iterator();
                    while (it.hasNext()) {
                        JuOrmLog.v(SQLStatement.TAG, "mapRelationToDb() mapping success, rowID: ", Long.valueOf(it.next().execDelete(sQLiteDatabase2)));
                    }
                }
                if (z && (arrayList = mapInfoBuildMappingInfo.mapNewRelationSQL) != null) {
                    Iterator<SQLStatement> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        JuOrmLog.v(SQLStatement.TAG, "mapRelationToDb() mapping success, rowID: ", Long.valueOf(it2.next().execInsert(sQLiteDatabase2)));
                    }
                }
                return true;
            }
        });
    }

    private void printSQL() {
    }

    private void realease() {
        SQLiteStatement sQLiteStatement = this.mStatement;
        if (sQLiteStatement != null) {
            sQLiteStatement.close();
        }
        this.sql = null;
        this.bindArgs = null;
        this.mStatement = null;
    }

    public void bind(int i, Object obj) {
        if (obj == null) {
            this.mStatement.bindNull(i);
            return;
        }
        if ((obj instanceof CharSequence) || (obj instanceof Boolean) || (obj instanceof Character)) {
            this.mStatement.bindString(i, String.valueOf(obj));
            return;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            this.mStatement.bindDouble(i, ((Number) obj).doubleValue());
            return;
        }
        if (obj instanceof Number) {
            this.mStatement.bindLong(i, ((Number) obj).longValue());
            return;
        }
        if (obj instanceof Date) {
            this.mStatement.bindLong(i, ((Date) obj).getTime());
            return;
        }
        if (obj instanceof byte[]) {
            this.mStatement.bindBlob(i, (byte[]) obj);
        } else if (obj instanceof Serializable) {
            this.mStatement.bindBlob(i, DataUtils.objectToByte(obj));
        } else {
            this.mStatement.bindNull(i);
        }
    }

    public int execDelete(SQLiteDatabase sQLiteDatabase) {
        return execDeleteWithMapping(sQLiteDatabase, null, null);
    }

    public int execDeleteCollection(SQLiteDatabase sQLiteDatabase, Collection<?> collection) {
        return execDeleteCollectionWithMapping(sQLiteDatabase, collection, null);
    }

    public int execDeleteCollectionWithMapping(SQLiteDatabase sQLiteDatabase, final Collection<?> collection, final TableManager tableManager) {
        this.mStatement = sQLiteDatabase.compileStatement(this.sql);
        if (this.bindArgs != null) {
            int i = 0;
            while (true) {
                Object[] objArr = this.bindArgs;
                if (i >= objArr.length) {
                    break;
                }
                int i2 = i + 1;
                bind(i2, objArr[i]);
                i = i2;
            }
        }
        int i3 = Build.VERSION.SDK_INT;
        int iExecuteUpdateDelete = this.mStatement.executeUpdateDelete();
        JuOrmLog.v(TAG, "execDeleteCollectionWithMapping() SQL execute delete, changed rows : ", Integer.valueOf(iExecuteUpdateDelete));
        realease();
        if (tableManager != null) {
            JuOrmLog.i(TAG, "execDeleteCollectionWithMapping() Exec delete collection mapping: ", (Boolean) Transaction.execute(sQLiteDatabase, new Transaction.Worker<Boolean>() { // from class: com.ju.lib.datalayer.database.asist.SQLStatement.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.ju.lib.datalayer.database.asist.Transaction.Worker
                public Boolean doTransaction(SQLiteDatabase sQLiteDatabase2) {
                    Iterator it = collection.iterator();
                    boolean z = true;
                    while (it.hasNext()) {
                        SQLStatement.this.mapRelationToDb(it.next(), false, z, sQLiteDatabase2, tableManager);
                        z = false;
                    }
                    return true;
                }
            }));
        }
        return iExecuteUpdateDelete;
    }

    public int execDeleteWithMapping(SQLiteDatabase sQLiteDatabase, Object obj, TableManager tableManager) {
        this.mStatement = sQLiteDatabase.compileStatement(this.sql);
        if (this.bindArgs != null) {
            int i = 0;
            while (true) {
                Object[] objArr = this.bindArgs;
                if (i >= objArr.length) {
                    break;
                }
                int i2 = i + 1;
                bind(i2, objArr[i]);
                i = i2;
            }
        }
        int i3 = Build.VERSION.SDK_INT;
        int iExecuteUpdateDelete = this.mStatement.executeUpdateDelete();
        JuOrmLog.v(TAG, "execDeleteWithMapping() SQL execute delete, changed rows--> ", Integer.valueOf(iExecuteUpdateDelete));
        realease();
        if (obj != null) {
            if (tableManager != null) {
                mapRelationToDb(obj, false, false, sQLiteDatabase, tableManager);
            }
            CacheManager.removeFromCache(FieldUtils.getPrimaryKey(obj), obj.getClass());
        }
        return iExecuteUpdateDelete;
    }

    public long execInsert(SQLiteDatabase sQLiteDatabase) {
        return execInsertWithMapping(sQLiteDatabase, null, null);
    }

    public int execInsertCollection(SQLiteDatabase sQLiteDatabase, Collection<?> collection) {
        return execInsertCollectionWithMapping(sQLiteDatabase, collection, null);
    }

    public int execInsertCollectionWithMapping(SQLiteDatabase sQLiteDatabase, Collection<?> collection, TableManager tableManager) throws Throwable {
        Object assignedKeyObject;
        sQLiteDatabase.beginTransaction();
        JuOrmLog.i(TAG, "execInsertCollectionWithMapping() BeginTransaction[insert col]");
        try {
            try {
                try {
                    this.mStatement = sQLiteDatabase.compileStatement(this.sql);
                    Iterator<?> it = collection.iterator();
                    EntityTable table = null;
                    boolean z = true;
                    while (true) {
                        int i = 2;
                        if (!it.hasNext()) {
                            JuOrmLog.i(TAG, "execInsertCollectionWithMapping() Exec insert [", Integer.valueOf(collection.size()), "] rows , SQL: ", this.sql);
                            sQLiteDatabase.setTransactionSuccessful();
                            JuOrmLog.i(TAG, "execInsertCollectionWithMapping()[insert col] Successful");
                            int size = collection.size();
                            realease();
                            sQLiteDatabase.endTransaction();
                            return size;
                        }
                        this.mStatement.clearBindings();
                        Object next = it.next();
                        if (table == null) {
                            table = TableManager.getTable(next);
                        }
                        EntityTable entityTable = table;
                        if (entityTable.key != null) {
                            assignedKeyObject = FieldUtils.getAssignedKeyObject(entityTable.key, next);
                            bind(1, assignedKeyObject);
                        } else {
                            assignedKeyObject = null;
                            i = 1;
                        }
                        if (!Checker.isEmpty(entityTable.pmap)) {
                            Iterator<Property> it2 = entityTable.pmap.values().iterator();
                            while (it2.hasNext()) {
                                bind(i, FieldUtils.get(it2.next().field, next));
                                i++;
                            }
                        }
                        long jExecuteInsert = this.mStatement.executeInsert();
                        FieldUtils.setKeyValueIfneed(next, entityTable.key, assignedKeyObject, jExecuteInsert);
                        if (tableManager != null) {
                            mapRelationToDb(next, true, z, sQLiteDatabase, tableManager);
                            z = false;
                        }
                        CacheManager.saveToCache(Long.valueOf(jExecuteInsert), next);
                        table = entityTable;
                        th = th;
                        realease();
                        sQLiteDatabase.endTransaction();
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    JuOrmLog.e(TAG, "execInsertCollectionWithMapping() [insert col] Failed");
                    e.printStackTrace();
                    realease();
                    sQLiteDatabase.endTransaction();
                    return -1;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e3) {
            e = e3;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public long execInsertWithMapping(SQLiteDatabase sQLiteDatabase, Object obj, TableManager tableManager) {
        this.mStatement = sQLiteDatabase.compileStatement(this.sql);
        Object obj2 = null;
        if (!Checker.isEmpty(this.bindArgs)) {
            obj2 = this.bindArgs[0];
            int i = 0;
            while (true) {
                Object[] objArr = this.bindArgs;
                if (i >= objArr.length) {
                    break;
                }
                int i2 = i + 1;
                bind(i2, objArr[i]);
                i = i2;
            }
        }
        JuOrmLog.i(TAG, "execInsertWithMapping() execute bind over ");
        try {
            long jExecuteInsert = this.mStatement.executeInsert();
            realease();
            JuOrmLog.i(TAG, "execInsertWithMapping() execute insert over. insert rowId : ", Long.valueOf(jExecuteInsert), " sql: ", this.sql);
            if (obj != null) {
                FieldUtils.setKeyValueIfneed(obj, TableManager.getTable(obj).key, obj2, jExecuteInsert);
                CacheManager.saveToCache(Long.valueOf(jExecuteInsert), obj);
            }
            if (tableManager != null) {
                mapRelationToDb(obj, true, true, sQLiteDatabase, tableManager);
            }
            return jExecuteInsert;
        } catch (Throwable th) {
            realease();
            throw th;
        }
    }

    public int execUpdate(SQLiteDatabase sQLiteDatabase) {
        return execUpdateWithMapping(sQLiteDatabase, null, null);
    }

    public int execUpdateCollection(SQLiteDatabase sQLiteDatabase, Collection<?> collection, ColumnsValue columnsValue) {
        return execUpdateCollectionWithMapping(sQLiteDatabase, collection, columnsValue, null);
    }

    public int execUpdateCollectionWithMapping(SQLiteDatabase sQLiteDatabase, Collection<?> collection, ColumnsValue columnsValue, TableManager tableManager) {
        sQLiteDatabase.beginTransaction();
        JuOrmLog.d(TAG, "execUpdateCollectionWithMapping()");
        try {
            this.mStatement = sQLiteDatabase.compileStatement(this.sql);
            EntityTable table = null;
            boolean z = true;
            for (Object obj : collection) {
                this.mStatement.clearBindings();
                if (table == null) {
                    table = TableManager.getTable(obj);
                }
                this.bindArgs = SQLBuilder.buildUpdateSqlArgsOnly(obj, columnsValue);
                if (!Checker.isEmpty(this.bindArgs)) {
                    int i = 0;
                    while (i < this.bindArgs.length) {
                        int i2 = i + 1;
                        bind(i2, this.bindArgs[i]);
                        i = i2;
                    }
                }
                this.mStatement.execute();
                if (tableManager != null) {
                    mapRelationToDb(obj, true, z, sQLiteDatabase, tableManager);
                    z = false;
                }
                CacheManager.saveToCache(FieldUtils.getPrimaryKey(obj), obj.getClass());
            }
            JuOrmLog.i(TAG, "execUpdateCollectionWithMapping() size:", Integer.valueOf(collection.size()), ", SQL: ", this.sql);
            sQLiteDatabase.setTransactionSuccessful();
            return collection.size();
        } catch (Exception e2) {
            JuOrmLog.e(TAG, "execUpdateCollectionWithMapping() Failed");
            e2.printStackTrace();
            return -1;
        } finally {
            realease();
            sQLiteDatabase.endTransaction();
        }
    }

    public int execUpdateWithMapping(SQLiteDatabase sQLiteDatabase, Object obj, TableManager tableManager) {
        this.mStatement = sQLiteDatabase.compileStatement(this.sql);
        if (!Checker.isEmpty(this.bindArgs)) {
            int i = 0;
            while (true) {
                Object[] objArr = this.bindArgs;
                if (i >= objArr.length) {
                    break;
                }
                int i2 = i + 1;
                bind(i2, objArr[i]);
                i = i2;
            }
        }
        int i3 = Build.VERSION.SDK_INT;
        int iExecuteUpdateDelete = this.mStatement.executeUpdateDelete();
        realease();
        JuOrmLog.i(TAG, "execUpdateWithMapping() rows : ", Integer.valueOf(iExecuteUpdateDelete));
        if (tableManager != null && obj != null) {
            mapRelationToDb(obj, true, true, sQLiteDatabase, tableManager);
            CacheManager.saveToCache(FieldUtils.getPrimaryKey(obj), obj.getClass());
        }
        return iExecuteUpdateDelete;
    }

    public boolean execute(SQLiteDatabase sQLiteDatabase) {
        try {
            this.mStatement = sQLiteDatabase.compileStatement(this.sql);
            if (this.bindArgs != null) {
                int i = 0;
                while (i < this.bindArgs.length) {
                    int i2 = i + 1;
                    bind(i2, this.bindArgs[i]);
                    i = i2;
                }
            }
            this.mStatement.execute();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        } finally {
            realease();
        }
    }

    public <T> ArrayList<T> query(SQLiteDatabase sQLiteDatabase, final Class<T> cls) {
        final ArrayList<T> arrayList = new ArrayList<>();
        try {
            final EntityTable table = TableManager.getTable(cls, false);
            Querier.doQuery(sQLiteDatabase, this, new Querier.CursorParser() { // from class: com.ju.lib.datalayer.database.asist.SQLStatement.2
                public String key;
                public int keyIndex = -1;

                {
                    this.key = table.key.column;
                }

                @Override // com.ju.lib.datalayer.database.asist.Querier.CursorParser
                public void parseEachCursor(SQLiteDatabase sQLiteDatabase2, Cursor cursor) throws IllegalAccessException {
                    if (this.keyIndex == -1) {
                        this.keyIndex = cursor.getColumnIndex(this.key);
                    }
                    Object fromCache = CacheManager.getFromCache(FieldUtils.getPrimaryKeyFromCursor(cursor, table.key, this.keyIndex), cls);
                    if (fromCache == null || fromCache.getClass() != cls) {
                        fromCache = ClassUtils.newInstance(cls);
                        DataUtils.injectDataToObject(cursor, fromCache, table);
                        CacheManager.saveToCache(FieldUtils.getPrimaryKey(fromCache), fromCache);
                    }
                    arrayList.add(fromCache);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public long queryForLong(SQLiteDatabase sQLiteDatabase) {
        long jSimpleQueryForLong = 0;
        try {
            try {
                this.mStatement = sQLiteDatabase.compileStatement(this.sql);
                if (this.bindArgs != null) {
                    int i = 0;
                    while (i < this.bindArgs.length) {
                        int i2 = i + 1;
                        bind(i2, this.bindArgs[i]);
                        i = i2;
                    }
                }
                jSimpleQueryForLong = this.mStatement.simpleQueryForLong();
                JuOrmLog.i(TAG, "queryForLong() query for count : ", Long.valueOf(jSimpleQueryForLong));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return jSimpleQueryForLong;
        } finally {
            realease();
        }
    }

    public <T> T queryOneEntity(SQLiteDatabase sQLiteDatabase, final Class<T> cls) {
        final EntityTable table = TableManager.getTable(cls, false);
        return (T) Querier.doQuery(sQLiteDatabase, this, new Querier.CursorParser<T>() { // from class: com.ju.lib.datalayer.database.asist.SQLStatement.3
            public T t;

            @Override // com.ju.lib.datalayer.database.asist.Querier.CursorParser
            public void parseEachCursor(SQLiteDatabase sQLiteDatabase2, Cursor cursor) throws IllegalAccessException {
                this.t = (T) ClassUtils.newInstance(cls);
                DataUtils.injectDataToObject(cursor, this.t, table);
                stopParse();
            }

            @Override // com.ju.lib.datalayer.database.asist.Querier.CursorParser
            public T returnResult() {
                CacheManager.saveToCache(FieldUtils.getPrimaryKey(this.t), this.t);
                return this.t;
            }
        });
    }

    public String toString() {
        StringBuilder sbA = a.a("SQLStatement [sql=");
        sbA.append(this.sql);
        sbA.append(", bindArgs=");
        sbA.append(Arrays.toString(this.bindArgs));
        sbA.append(", mStatement=");
        return a.a(sbA, this.mStatement, "]");
    }

    public SQLStatement(String str, Object[] objArr) {
        this.sql = str;
        this.bindArgs = objArr;
    }

    public long execInsert(SQLiteDatabase sQLiteDatabase, Object obj) {
        return execInsertWithMapping(sQLiteDatabase, obj, null);
    }
}
