package com.ju.lib.datalayer.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteClosable;
import android.database.sqlite.SQLiteDatabase;
import com.ju.lib.datalayer.database.api.IDatabase;
import com.ju.lib.datalayer.database.asist.Checker;
import com.ju.lib.datalayer.database.asist.CollSpliter;
import com.ju.lib.datalayer.database.asist.Querier;
import com.ju.lib.datalayer.database.asist.QueryBuilder;
import com.ju.lib.datalayer.database.asist.SQLBuilder;
import com.ju.lib.datalayer.database.asist.SQLiteHelper;
import com.ju.lib.datalayer.database.asist.WhereBuilder;
import com.ju.lib.datalayer.database.cache.manager.CacheManager;
import com.ju.lib.datalayer.database.config.DatabaseConfig;
import com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl;
import com.ju.lib.datalayer.database.impl.SingleSQLiteImpl;
import com.ju.lib.datalayer.database.model.ColumnsValue;
import com.ju.lib.datalayer.database.model.ConflictAlgorithm;
import com.ju.lib.datalayer.database.model.EntityTable;
import com.ju.lib.datalayer.database.model.MapProperty;
import com.ju.lib.datalayer.database.model.RelationKey;
import com.ju.lib.datalayer.database.table.TableManager;
import com.ju.lib.datalayer.database.utils.ClassUtils;
import com.ju.lib.datalayer.database.utils.DataUtils;
import com.ju.lib.datalayer.database.utils.FieldUtils;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class JuOrm extends SQLiteClosable implements IDatabase {
    public static final String TAG = "JuOrm";
    public DatabaseConfig mConfig;
    public SQLiteHelper mHelper;
    public TableManager mTableManager;
    public JuOrm otherDatabase;

    public JuOrm(JuOrm juOrm) {
        this.mHelper = juOrm.mHelper;
        this.mConfig = juOrm.mConfig;
        this.mTableManager = juOrm.mTableManager;
        this.otherDatabase = juOrm;
    }

    private void initDatabasePath(String str) {
        DatabaseConfig databaseConfig = this.mConfig;
        String path = databaseConfig.context.getDatabasePath(databaseConfig.dbName).getPath();
        JuOrmLog.i(TAG, "initDatabasePath() context database path: ", path, " dbName : ", str);
        File parentFile = new File(path).getParentFile();
        if (parentFile == null || parentFile.exists()) {
            return;
        }
        JuOrmLog.i(TAG, "initDatabasePath() create database, parent file mkdirs: ", Boolean.valueOf(parentFile.mkdirs()), "  path:", parentFile.getAbsolutePath());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <E, T> boolean keepMapping(Collection<E> collection, Collection<T> collection2) throws IllegalAccessException {
        Object obj;
        Object obj2;
        Class cls = collection.iterator().next().getClass();
        Class cls2 = collection2.iterator().next().getClass();
        EntityTable table = TableManager.getTable((Class<?>) cls);
        EntityTable table2 = TableManager.getTable((Class<?>) cls2);
        ArrayList<MapProperty> arrayList = table.mappingList;
        if (arrayList == null) {
            return false;
        }
        for (MapProperty mapProperty : arrayList) {
            Class type = mapProperty.field.getType();
            if (mapProperty.isToMany()) {
                if (ClassUtils.isCollection(type)) {
                    type = FieldUtils.getGenericType(mapProperty.field);
                } else {
                    if (!type.isArray()) {
                        throw new RuntimeException("OneToMany and ManyToMany Relation, Must use collection or array object");
                    }
                    type = FieldUtils.getComponentType(mapProperty.field);
                }
            }
            if (type == cls2) {
                ArrayList arrayList2 = new ArrayList();
                HashMap map = new HashMap();
                for (E e2 : collection) {
                    if (e2 != null && (obj2 = FieldUtils.get(table.key.field, e2)) != null) {
                        arrayList2.add(obj2.toString());
                        map.put(obj2.toString(), e2);
                    }
                }
                ArrayList<RelationKey> arrayListQueryRelation = queryRelation(cls, cls2, arrayList2);
                if (!Checker.isEmpty(arrayListQueryRelation)) {
                    HashMap map2 = new HashMap();
                    for (T t : collection2) {
                        if (t != null && (obj = FieldUtils.get(table2.key.field, t)) != null) {
                            map2.put(obj.toString(), t);
                        }
                    }
                    HashMap map3 = new HashMap();
                    for (RelationKey relationKey : arrayListQueryRelation) {
                        Object obj3 = map.get(relationKey.key1);
                        Object obj4 = map2.get(relationKey.key2);
                        if (obj3 != null && obj4 != null) {
                            if (mapProperty.isToMany()) {
                                ArrayList arrayList3 = (ArrayList) map3.get(obj3);
                                ArrayList arrayList4 = arrayList3;
                                if (arrayList3 == null) {
                                    ArrayList arrayList5 = new ArrayList();
                                    map3.put(obj3, arrayList5);
                                    arrayList4 = arrayList5;
                                }
                                arrayList4.add(obj4);
                            } else {
                                FieldUtils.set(mapProperty.field, obj3, obj4);
                            }
                        }
                    }
                    if (Checker.isEmpty(map3)) {
                        return true;
                    }
                    for (Map.Entry entry : map3.entrySet()) {
                        Object key = entry.getKey();
                        Collection<? extends E> collection3 = (Collection) entry.getValue();
                        if (ClassUtils.isCollection(type)) {
                            Collection collection4 = (Collection) FieldUtils.get(mapProperty.field, key);
                            if (collection4 == null) {
                                FieldUtils.set(mapProperty.field, key, collection3);
                            } else {
                                collection4.addAll(collection3);
                            }
                        } else if (type.isArray()) {
                            Object[] objArr = (Object[]) Array.newInstance((Class<?>) type, collection3.size());
                            collection3.toArray(objArr);
                            Object[] objArr2 = (Object[]) FieldUtils.get(mapProperty.field, key);
                            if (objArr2 == null) {
                                FieldUtils.set(mapProperty.field, key, objArr);
                            } else {
                                FieldUtils.set(mapProperty.field, key, DataUtils.concat(objArr2, objArr));
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static JuOrm newCascadeInstance(Context context, String str) {
        return newCascadeInstance(new DatabaseConfig(context, str));
    }

    public static JuOrm newSingleInstance(Context context, String str) {
        return newSingleInstance(new DatabaseConfig(context, str));
    }

    private SQLiteDatabase openOrCreateDatabase() {
        initDatabasePath(this.mConfig.dbName);
        if (this.mHelper != null) {
            justRelease();
        }
        Context applicationContext = this.mConfig.context.getApplicationContext();
        DatabaseConfig databaseConfig = this.mConfig;
        this.mHelper = new SQLiteHelper(applicationContext, databaseConfig.dbName, null, databaseConfig.dbVersion, databaseConfig.onUpdateListener);
        this.mTableManager = new TableManager(this.mConfig.dbName, this.mHelper.getReadableDatabase());
        return this.mHelper.getWritableDatabase();
    }

    public static int releaseMemory() {
        return SQLiteDatabase.releaseMemory();
    }

    public abstract JuOrm cascade();

    @Override // android.database.sqlite.SQLiteClosable, java.io.Closeable, java.lang.AutoCloseable, com.ju.lib.datalayer.database.api.IDatabase
    public synchronized void close() {
        releaseReference();
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public boolean deleteDatabase() {
        String path = this.mHelper.getWritableDatabase().getPath();
        justRelease();
        JuOrmLog.i(TAG, "deleteDatabase() delete Database path: ", path);
        return deleteDatabase(new File(path));
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public boolean dropTable(Class<?> cls) {
        return dropTable(TableManager.getTable(cls, false).name);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public DatabaseConfig getDatabaseConfig() {
        return this.mConfig;
    }

    public void justRelease() {
        SQLiteHelper sQLiteHelper = this.mHelper;
        if (sQLiteHelper != null) {
            sQLiteHelper.getWritableDatabase().close();
            this.mHelper.close();
            this.mHelper = null;
        }
        CacheManager.evictAll();
        TableManager tableManager = this.mTableManager;
        if (tableManager != null) {
            tableManager.release();
            this.mTableManager = null;
        }
    }

    public <E, T> boolean mapping(Collection<E> collection, Collection<T> collection2) {
        if (Checker.isEmpty((Collection<?>) collection) || Checker.isEmpty((Collection<?>) collection2)) {
            return false;
        }
        acquireReference();
        try {
            return keepMapping(collection2, collection) | keepMapping(collection, collection2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        } finally {
            releaseReference();
        }
    }

    @Override // android.database.sqlite.SQLiteClosable
    public void onAllReferencesReleased() {
        justRelease();
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> long queryCount(Class<T> cls) {
        return queryCount(new QueryBuilder(cls));
    }

    public ArrayList<RelationKey> queryRelation(final Class cls, final Class cls2, final List<String> list) {
        acquireReference();
        final ArrayList<RelationKey> arrayList = new ArrayList<>();
        try {
            try {
                final EntityTable table = TableManager.getTable((Class<?>) cls);
                final EntityTable table2 = TableManager.getTable((Class<?>) cls2);
                if (this.mTableManager.isSQLMapTableCreated(table.name, table2.name)) {
                    CollSpliter.split(list, 999, new CollSpliter.Spliter<String>() { // from class: com.ju.lib.datalayer.database.JuOrm.1
                        @Override // com.ju.lib.datalayer.database.asist.CollSpliter.Spliter
                        public int oneSplit(ArrayList<String> arrayList2) {
                            Querier.doQuery(JuOrm.this.mHelper.getReadableDatabase(), SQLBuilder.buildQueryRelationSql(cls, cls2, (List<String>) list), new Querier.CursorParser() { // from class: com.ju.lib.datalayer.database.JuOrm.1.1
                                @Override // com.ju.lib.datalayer.database.asist.Querier.CursorParser
                                public void parseEachCursor(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
                                    RelationKey relationKey = new RelationKey();
                                    relationKey.key1 = cursor.getString(cursor.getColumnIndex(table.name));
                                    relationKey.key2 = cursor.getString(cursor.getColumnIndex(table2.name));
                                    arrayList.add(relationKey);
                                }
                            });
                            return 0;
                        }
                    });
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return arrayList;
        } finally {
            releaseReference();
        }
    }

    public void setDebugged(boolean z) {
        this.mConfig.debugged = z;
        JuOrmLog.isPrint = z;
    }

    public abstract JuOrm single();

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public int update(WhereBuilder whereBuilder, ColumnsValue columnsValue, ConflictAlgorithm conflictAlgorithm) {
        acquireReference();
        CacheManager.clearCache(whereBuilder.getClass());
        try {
            return SQLBuilder.buildUpdateSql(whereBuilder, columnsValue, conflictAlgorithm).execUpdate(this.mHelper.getWritableDatabase());
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        } finally {
            releaseReference();
        }
    }

    public static synchronized JuOrm newCascadeInstance(DatabaseConfig databaseConfig) {
        return CascadeSQLiteImpl.newInstance(databaseConfig);
    }

    public static synchronized JuOrm newSingleInstance(DatabaseConfig databaseConfig) {
        return SingleSQLiteImpl.newInstance(databaseConfig);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public boolean dropTable(String str) {
        acquireReference();
        try {
            return SQLBuilder.buildDropTable(str).execute(this.mHelper.getWritableDatabase());
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public long queryCount(QueryBuilder queryBuilder) {
        acquireReference();
        try {
            if (!this.mTableManager.isSQLTableCreated(queryBuilder.getTableName())) {
                return 0L;
            }
            return queryBuilder.createStatementForCount().queryForLong(this.mHelper.getReadableDatabase());
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1L;
        } finally {
            releaseReference();
        }
        releaseReference();
    }

    private boolean deleteDatabase(File file) {
        acquireReference();
        try {
            if (file != null) {
                boolean zDelete = file.delete() | new File(file.getPath() + "-journal").delete() | new File(file.getPath() + "-shm").delete() | new File(file.getPath() + "-wal").delete();
                File parentFile = file.getParentFile();
                if (parentFile != null) {
                    final String str = file.getName() + "-mj";
                    boolean zDelete2 = zDelete;
                    for (File file2 : parentFile.listFiles(new FileFilter() { // from class: com.ju.lib.datalayer.database.JuOrm.2
                        @Override // java.io.FileFilter
                        public boolean accept(File file3) {
                            return file3.getName().startsWith(str);
                        }
                    })) {
                        zDelete2 |= file2.delete();
                    }
                    zDelete = zDelete2;
                }
                CacheManager.evictAll();
                releaseReference();
                return zDelete;
            }
            throw new IllegalArgumentException("file must not be null");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        } finally {
            CacheManager.evictAll();
            releaseReference();
        }
    }

    public JuOrm(DatabaseConfig databaseConfig) {
        databaseConfig.context = databaseConfig.context.getApplicationContext();
        if (databaseConfig.dbName == null) {
            databaseConfig.dbName = DatabaseConfig.DEFAULT_DB_NAME;
        }
        if (databaseConfig.dbVersion <= 0) {
            databaseConfig.dbVersion = 1;
        }
        this.mConfig = databaseConfig;
        setDebugged(databaseConfig.debugged);
        openOrCreateDatabase();
    }
}
