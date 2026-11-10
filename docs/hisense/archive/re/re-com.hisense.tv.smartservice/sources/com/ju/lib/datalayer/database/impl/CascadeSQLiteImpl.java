package com.ju.lib.datalayer.database.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.ju.lib.datalayer.database.JuOrm;
import com.ju.lib.datalayer.database.asist.Checker;
import com.ju.lib.datalayer.database.asist.Querier;
import com.ju.lib.datalayer.database.asist.QueryBuilder;
import com.ju.lib.datalayer.database.asist.SQLBuilder;
import com.ju.lib.datalayer.database.asist.SQLStatement;
import com.ju.lib.datalayer.database.asist.Transaction;
import com.ju.lib.datalayer.database.asist.WhereBuilder;
import com.ju.lib.datalayer.database.config.DatabaseConfig;
import com.ju.lib.datalayer.database.model.ColumnsValue;
import com.ju.lib.datalayer.database.model.ConflictAlgorithm;
import com.ju.lib.datalayer.database.model.EntityTable;
import com.ju.lib.datalayer.database.model.MapProperty;
import com.ju.lib.datalayer.database.model.PrimaryKey;
import com.ju.lib.datalayer.database.model.Property;
import com.ju.lib.datalayer.database.model.RelationKey;
import com.ju.lib.datalayer.database.table.TableManager;
import com.ju.lib.datalayer.database.utils.ClassUtils;
import com.ju.lib.datalayer.database.utils.DataUtils;
import com.ju.lib.datalayer.database.utils.FieldUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CascadeSQLiteImpl extends JuOrm {
    public static final int TYPE_DELETE = 3;
    public static final int TYPE_INSERT = 1;
    public static final int TYPE_UPDATE = 2;

    public CascadeSQLiteImpl(JuOrm juOrm) {
        super(juOrm);
    }

    public static /* synthetic */ long access$000(CascadeSQLiteImpl cascadeSQLiteImpl, Object obj, SQLiteDatabase sQLiteDatabase, HashMap map) {
        cascadeSQLiteImpl.mTableManager.checkOrCreateTable(sQLiteDatabase, obj);
        return cascadeSQLiteImpl.insertRecursive(SQLBuilder.buildReplaceSql(obj), obj, sQLiteDatabase, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int checkTableAndDeleteRecursive(Object obj, SQLiteDatabase sQLiteDatabase, HashMap<String, Integer> map) {
        if (this.mTableManager.isSQLTableCreated(TableManager.getTable(obj).name)) {
            return deleteRecursive(SQLBuilder.buildDeleteSql(obj), obj, sQLiteDatabase, map);
        }
        return -1;
    }

    private <T> ArrayList<T> checkTableAndQuery(final Class<T> cls, QueryBuilder queryBuilder) {
        acquireReference();
        final ArrayList<T> arrayList = new ArrayList<>();
        try {
            try {
                final EntityTable table = TableManager.getTable(cls, false);
                if (this.mTableManager.isSQLTableCreated(table.name)) {
                    final HashMap<String, Object> map = new HashMap<>();
                    HashMap<String, Integer> map2 = new HashMap<>();
                    SQLiteDatabase readableDatabase = this.mHelper.getReadableDatabase();
                    Querier.doQuery(readableDatabase, queryBuilder.createStatement(), new Querier.CursorParser() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.5
                        @Override // com.ju.lib.datalayer.database.asist.Querier.CursorParser
                        public void parseEachCursor(SQLiteDatabase sQLiteDatabase, Cursor cursor) throws IllegalAccessException {
                            Object objNewInstance = ClassUtils.newInstance(cls);
                            DataUtils.injectDataToObject(cursor, objNewInstance, table);
                            arrayList.add(objNewInstance);
                            map.put(table.name + FieldUtils.get(table.key.field, objNewInstance), objNewInstance);
                        }
                    });
                    Iterator<T> it = arrayList.iterator();
                    while (it.hasNext()) {
                        queryForMappingRecursive(it.next(), readableDatabase, map2, map);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return arrayList;
        } finally {
            releaseReference();
        }
    }

    private long checkTableAndSaveRecursive(Object obj, SQLiteDatabase sQLiteDatabase, HashMap<String, Integer> map) {
        this.mTableManager.checkOrCreateTable(sQLiteDatabase, obj);
        return insertRecursive(SQLBuilder.buildReplaceSql(obj), obj, sQLiteDatabase, map);
    }

    private <T> int deleteCollectionIfTableHasCreated(final Collection<T> collection) {
        Integer num;
        if (Checker.isEmpty((Collection<?>) collection)) {
            return -1;
        }
        final Iterator<T> it = collection.iterator();
        final T next = it.next();
        if (!this.mTableManager.isSQLTableCreated(TableManager.getTable(next).name) || (num = (Integer) Transaction.execute(this.mHelper.getWritableDatabase(), new Transaction.Worker<Integer>() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.12
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ju.lib.datalayer.database.asist.Transaction.Worker
            public Integer doTransaction(SQLiteDatabase sQLiteDatabase) {
                HashMap map = new HashMap();
                SQLStatement sQLStatementBuildDeleteSql = SQLBuilder.buildDeleteSql(next);
                CascadeSQLiteImpl.this.deleteRecursive(sQLStatementBuildDeleteSql, next, sQLiteDatabase, map);
                while (it.hasNext()) {
                    Object next2 = it.next();
                    sQLStatementBuildDeleteSql.bindArgs = CascadeSQLiteImpl.getDeleteStatementArgs(next2);
                    CascadeSQLiteImpl.this.deleteRecursive(sQLStatementBuildDeleteSql, next2, sQLiteDatabase, map);
                }
                return Integer.valueOf(collection.size());
            }
        })) == null) {
            return -1;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int deleteRecursive(SQLStatement sQLStatement, Object obj, SQLiteDatabase sQLiteDatabase, HashMap<String, Integer> map) {
        EntityTable table = TableManager.getTable(obj);
        Object obj2 = FieldUtils.get(table.key.field, obj);
        if (map.get(table.name + obj2) != null) {
            return -1;
        }
        int iExecDelete = sQLStatement.execDelete(sQLiteDatabase);
        map.put(table.name + obj2, 1);
        handleMapping(obj2, obj, sQLiteDatabase, false, map);
        return iExecDelete;
    }

    public static Object[] getDeleteStatementArgs(Object obj) {
        EntityTable table = TableManager.getTable(obj);
        PrimaryKey primaryKey = table.key;
        int i = 0;
        if (primaryKey != null) {
            return new String[]{String.valueOf(FieldUtils.get(primaryKey.field, obj))};
        }
        if (Checker.isEmpty(table.pmap)) {
            return null;
        }
        Object[] objArr = new Object[table.pmap.size()];
        Iterator<Property> it = table.pmap.values().iterator();
        while (it.hasNext()) {
            objArr[i] = FieldUtils.get(it.next().field, obj);
            i++;
        }
        return objArr;
    }

    private long handleEntityRecursive(int i, SQLStatement sQLStatement, Object obj, SQLiteDatabase sQLiteDatabase, HashMap<String, Integer> map) {
        Object obj2;
        int iExecUpdate;
        EntityTable table = TableManager.getTable(obj);
        Object obj3 = FieldUtils.get(table.key.field, obj);
        long jExecInsert = -1;
        if (map.get(table.name + obj3) != null) {
            return -1L;
        }
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    iExecUpdate = sQLStatement.execDelete(sQLiteDatabase);
                }
                obj2 = obj3;
            } else {
                iExecUpdate = sQLStatement.execUpdate(sQLiteDatabase);
            }
            jExecInsert = iExecUpdate;
            obj2 = obj3;
        } else {
            jExecInsert = sQLStatement.execInsert(sQLiteDatabase, obj);
            obj2 = FieldUtils.get(table.key.field, obj);
        }
        map.put(table.name + obj2, 1);
        handleMapping(obj2, obj, sQLiteDatabase, i != 3, map);
        return jExecInsert;
    }

    private void handleMapToMany(EntityTable entityTable, EntityTable entityTable2, Object obj, Collection collection, SQLiteDatabase sQLiteDatabase, boolean z, HashMap<String, Integer> map) {
        if (collection != null) {
            for (Object obj2 : collection) {
                if (obj2 != null) {
                    if (z) {
                        this.mTableManager.checkOrCreateTable(sQLiteDatabase, obj2);
                        insertRecursive(SQLBuilder.buildReplaceSql(obj2), obj2, sQLiteDatabase, map);
                    } else {
                        checkTableAndDeleteRecursive(obj2, sQLiteDatabase, map);
                    }
                }
            }
        }
        String mapTableName = TableManager.getMapTableName(entityTable, entityTable2);
        this.mTableManager.checkOrCreateMappingTable(sQLiteDatabase, mapTableName, entityTable.name, entityTable2.name);
        SQLBuilder.buildMappingDeleteSql(mapTableName, obj, entityTable).execDelete(sQLiteDatabase);
        if (!z || Checker.isEmpty((Collection<?>) collection)) {
            return;
        }
        ArrayList<SQLStatement> arrayListBuildMappingToManySql = SQLBuilder.buildMappingToManySql(obj, entityTable, entityTable2, collection);
        if (Checker.isEmpty(arrayListBuildMappingToManySql)) {
            return;
        }
        Iterator<SQLStatement> it = arrayListBuildMappingToManySql.iterator();
        while (it.hasNext()) {
            it.next().execInsert(sQLiteDatabase);
        }
    }

    private void handleMapToOne(EntityTable entityTable, EntityTable entityTable2, Object obj, Object obj2, SQLiteDatabase sQLiteDatabase, boolean z, HashMap<String, Integer> map) {
        SQLStatement sQLStatementBuildMappingToOneSql;
        if (obj2 != null) {
            if (z) {
                this.mTableManager.checkOrCreateTable(sQLiteDatabase, obj2);
                insertRecursive(SQLBuilder.buildReplaceSql(obj2), obj2, sQLiteDatabase, map);
            } else {
                checkTableAndDeleteRecursive(obj2, sQLiteDatabase, map);
            }
        }
        String mapTableName = TableManager.getMapTableName(entityTable, entityTable2);
        this.mTableManager.checkOrCreateMappingTable(sQLiteDatabase, mapTableName, entityTable.name, entityTable2.name);
        SQLBuilder.buildMappingDeleteSql(mapTableName, obj, entityTable).execDelete(sQLiteDatabase);
        if (!z || obj2 == null || (sQLStatementBuildMappingToOneSql = SQLBuilder.buildMappingToOneSql(mapTableName, obj, FieldUtils.get(entityTable2.key.field, obj2), entityTable, entityTable2)) == null) {
            return;
        }
        sQLStatementBuildMappingToOneSql.execInsert(sQLiteDatabase);
    }

    private void handleMapping(Object obj, Object obj2, SQLiteDatabase sQLiteDatabase, boolean z, HashMap<String, Integer> map) {
        EntityTable table = TableManager.getTable(obj2);
        ArrayList<MapProperty> arrayList = table.mappingList;
        if (arrayList != null) {
            for (MapProperty mapProperty : arrayList) {
                if (mapProperty.isToOne()) {
                    handleMapToOne(table, TableManager.getTable(mapProperty.field.getType()), obj, FieldUtils.get(mapProperty.field, obj2), sQLiteDatabase, z, map);
                } else if (mapProperty.isToMany()) {
                    Object obj3 = FieldUtils.get(mapProperty.field, obj2);
                    if (ClassUtils.isCollection(mapProperty.field.getType())) {
                        handleMapToMany(table, TableManager.getTable(FieldUtils.getGenericType(mapProperty.field)), obj, (Collection) obj3, sQLiteDatabase, z, map);
                    } else {
                        if (!mapProperty.field.getType().isArray()) {
                            throw new RuntimeException("OneToMany and ManyToMany Relation, you must use collection or array object");
                        }
                        handleMapToMany(table, TableManager.getTable(FieldUtils.getComponentType(mapProperty.field)), obj, obj3 != null ? Arrays.asList((Object[]) obj3) : null, sQLiteDatabase, z, map);
                    }
                } else {
                    continue;
                }
            }
        }
    }

    private <T> int insertCollection(final Collection<T> collection, final ConflictAlgorithm conflictAlgorithm) {
        Integer num;
        if (Checker.isEmpty((Collection<?>) collection) || (num = (Integer) Transaction.execute(this.mHelper.getWritableDatabase(), new Transaction.Worker<Integer>() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ju.lib.datalayer.database.asist.Transaction.Worker
            public Integer doTransaction(SQLiteDatabase sQLiteDatabase) {
                HashMap map = new HashMap();
                Iterator it = collection.iterator();
                Object next = it.next();
                SQLStatement sQLStatementBuildInsertSql = SQLBuilder.buildInsertSql(next, conflictAlgorithm);
                CascadeSQLiteImpl.this.mTableManager.checkOrCreateTable(sQLiteDatabase, next);
                CascadeSQLiteImpl.this.insertRecursive(sQLStatementBuildInsertSql, next, sQLiteDatabase, map);
                while (it.hasNext()) {
                    Object next2 = it.next();
                    sQLStatementBuildInsertSql.bindArgs = SQLBuilder.buildInsertSqlArgsOnly(next2);
                    CascadeSQLiteImpl.this.insertRecursive(sQLStatementBuildInsertSql, next2, sQLiteDatabase, map);
                }
                return Integer.valueOf(collection.size());
            }
        })) == null) {
            return -1;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long insertRecursive(SQLStatement sQLStatement, Object obj, SQLiteDatabase sQLiteDatabase, HashMap<String, Integer> map) {
        EntityTable table = TableManager.getTable(obj);
        if (map.get(table.name + FieldUtils.get(table.key.field, obj)) != null) {
            return -1L;
        }
        long jExecInsert = sQLStatement.execInsert(sQLiteDatabase, obj);
        Object obj2 = FieldUtils.get(table.key.field, obj);
        map.put(table.name + obj2, 1);
        handleMapping(obj2, obj, sQLiteDatabase, true, map);
        return jExecInsert;
    }

    public static synchronized JuOrm newInstance(DatabaseConfig databaseConfig) {
        return new CascadeSQLiteImpl(databaseConfig);
    }

    private void queryForMappingRecursive(Object obj, SQLiteDatabase sQLiteDatabase, HashMap<String, Integer> map, HashMap<String, Object> map2) throws IllegalAccessException {
        EntityTable table = TableManager.getTable(obj);
        Object assignedKeyObject = FieldUtils.getAssignedKeyObject(table.key, obj);
        String str = table.name + assignedKeyObject;
        if (map.get(str) == null) {
            map.put(str, 1);
            ArrayList<MapProperty> arrayList = table.mappingList;
            if (arrayList != null) {
                for (MapProperty mapProperty : arrayList) {
                    if (mapProperty.isToOne()) {
                        queryMapToOne(table, assignedKeyObject, obj, mapProperty.field, sQLiteDatabase, map, map2);
                    } else if (mapProperty.isToMany()) {
                        queryMapToMany(table, assignedKeyObject, obj, mapProperty.field, sQLiteDatabase, map, map2);
                    }
                }
            }
        }
    }

    private void queryMapToMany(EntityTable entityTable, Object obj, Object obj2, Field field, SQLiteDatabase sQLiteDatabase, HashMap<String, Integer> map, final HashMap<String, Object> map2) throws IllegalAccessException {
        Class<?> componentType;
        String str = "OneToMany and ManyToMany Relation, you must use collection or array object";
        if (Collection.class.isAssignableFrom(field.getType())) {
            componentType = FieldUtils.getGenericType(field);
        } else {
            if (!field.getType().isArray()) {
                throw new RuntimeException("OneToMany and ManyToMany Relation, you must use collection or array object");
            }
            componentType = field.getType().getComponentType();
        }
        final Class<?> cls = componentType;
        final EntityTable table = TableManager.getTable(cls);
        if (this.mTableManager.isSQLMapTableCreated(entityTable.name, table.name)) {
            SQLStatement sQLStatementBuildQueryRelationSql = SQLBuilder.buildQueryRelationSql(entityTable, table, obj);
            final ArrayList arrayList = new ArrayList();
            Querier.doQuery(sQLiteDatabase, sQLStatementBuildQueryRelationSql, new Querier.CursorParser() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.7
                @Override // com.ju.lib.datalayer.database.asist.Querier.CursorParser
                public void parseEachCursor(SQLiteDatabase sQLiteDatabase2, Cursor cursor) {
                    arrayList.add(cursor.getString(cursor.getColumnIndex(table.name)));
                }
            });
            if (Checker.isEmpty(arrayList)) {
                return;
            }
            final ArrayList arrayList2 = new ArrayList();
            boolean z = true;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                Object obj3 = map2.get(table.name + ((String) arrayList.get(size)));
                if (obj3 != null) {
                    arrayList2.add(obj3);
                    arrayList.remove(size);
                }
            }
            int i = 0;
            int i2 = 0;
            while (i < arrayList.size()) {
                int i3 = i2 + 1;
                int i4 = i3 * 999;
                List listSubList = arrayList.subList(i, Math.min(arrayList.size(), i4));
                final EntityTable entityTable2 = table;
                Querier.doQuery(sQLiteDatabase, new QueryBuilder(cls).whereIn(table.key.column, listSubList.toArray(new String[listSubList.size()])).createStatement(), new Querier.CursorParser() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.8
                    @Override // com.ju.lib.datalayer.database.asist.Querier.CursorParser
                    public void parseEachCursor(SQLiteDatabase sQLiteDatabase2, Cursor cursor) throws IllegalAccessException {
                        Object objNewInstance = ClassUtils.newInstance(cls);
                        DataUtils.injectDataToObject(cursor, objNewInstance, entityTable2);
                        arrayList2.add(objNewInstance);
                        map2.put(entityTable2.name + FieldUtils.get(entityTable2.key.field, objNewInstance), objNewInstance);
                    }
                });
                arrayList = arrayList;
                z = z;
                str = str;
                i = i4;
                i2 = i3;
                table = table;
            }
            boolean z2 = z;
            String str2 = str;
            if (Checker.isEmpty(arrayList2)) {
                return;
            }
            if (Collection.class.isAssignableFrom(field.getType())) {
                Collection collection = (Collection) ClassUtils.newCollectionForField(field);
                collection.addAll(arrayList2);
                field.setAccessible(z2);
                field.set(obj2, collection);
            } else {
                if (!field.getType().isArray()) {
                    throw new RuntimeException(str2);
                }
                Object array = arrayList2.toArray((Object[]) Array.newInstance(cls, arrayList2.size()));
                field.setAccessible(z2);
                field.set(obj2, array);
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                queryForMappingRecursive(it.next(), sQLiteDatabase, map, map2);
            }
        }
    }

    private void queryMapToOne(final EntityTable entityTable, Object obj, Object obj2, Field field, SQLiteDatabase sQLiteDatabase, HashMap<String, Integer> map, HashMap<String, Object> map2) throws IllegalAccessException {
        final EntityTable table = TableManager.getTable(field.getType());
        if (this.mTableManager.isSQLMapTableCreated(entityTable.name, table.name)) {
            SQLStatement sQLStatementBuildQueryRelationSql = SQLBuilder.buildQueryRelationSql(entityTable, table, obj);
            final RelationKey relationKey = new RelationKey();
            Querier.doQuery(sQLiteDatabase, sQLStatementBuildQueryRelationSql, new Querier.CursorParser() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.6
                @Override // com.ju.lib.datalayer.database.asist.Querier.CursorParser
                public void parseEachCursor(SQLiteDatabase sQLiteDatabase2, Cursor cursor) {
                    relationKey.key1 = cursor.getString(cursor.getColumnIndex(entityTable.name));
                    relationKey.key2 = cursor.getString(cursor.getColumnIndex(table.name));
                    stopParse();
                }
            });
            if (relationKey.isOK()) {
                String str = table.name + relationKey.key2;
                Object objQueryOneEntity = map2.get(str);
                if (objQueryOneEntity == null) {
                    objQueryOneEntity = SQLBuilder.buildQueryMapEntitySql(table, relationKey.key2).queryOneEntity(sQLiteDatabase, table.claxx);
                    map2.put(str, objQueryOneEntity);
                }
                if (objQueryOneEntity != null) {
                    field.setAccessible(true);
                    field.set(obj2, objQueryOneEntity);
                    queryForMappingRecursive(objQueryOneEntity, sQLiteDatabase, map, map2);
                }
            }
        }
    }

    private <T> int saveCollection(final Collection<T> collection) {
        Integer num;
        if (Checker.isEmpty((Collection<?>) collection) || (num = (Integer) Transaction.execute(this.mHelper.getWritableDatabase(), new Transaction.Worker<Integer>() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.9
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ju.lib.datalayer.database.asist.Transaction.Worker
            public Integer doTransaction(SQLiteDatabase sQLiteDatabase) {
                HashMap map = new HashMap();
                Iterator it = collection.iterator();
                Object next = it.next();
                SQLStatement sQLStatementBuildReplaceSql = SQLBuilder.buildReplaceSql(next);
                CascadeSQLiteImpl.this.mTableManager.checkOrCreateTable(sQLiteDatabase, next);
                CascadeSQLiteImpl.this.insertRecursive(sQLStatementBuildReplaceSql, next, sQLiteDatabase, map);
                while (it.hasNext()) {
                    Object next2 = it.next();
                    sQLStatementBuildReplaceSql.bindArgs = SQLBuilder.buildInsertSqlArgsOnly(next2);
                    CascadeSQLiteImpl.this.insertRecursive(sQLStatementBuildReplaceSql, next2, sQLiteDatabase, map);
                }
                return Integer.valueOf(collection.size());
            }
        })) == null) {
            return -1;
        }
        return num.intValue();
    }

    private <T> int updateCollection(final Collection<T> collection, final ColumnsValue columnsValue, final ConflictAlgorithm conflictAlgorithm) {
        Integer num;
        if (Checker.isEmpty((Collection<?>) collection) || (num = (Integer) Transaction.execute(this.mHelper.getWritableDatabase(), new Transaction.Worker<Integer>() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.11
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ju.lib.datalayer.database.asist.Transaction.Worker
            public Integer doTransaction(SQLiteDatabase sQLiteDatabase) {
                HashMap map = new HashMap();
                Iterator it = collection.iterator();
                Object next = it.next();
                SQLStatement sQLStatementBuildUpdateSql = SQLBuilder.buildUpdateSql(next, columnsValue, conflictAlgorithm);
                CascadeSQLiteImpl.this.mTableManager.checkOrCreateTable(sQLiteDatabase, next);
                CascadeSQLiteImpl.this.updateRecursive(sQLStatementBuildUpdateSql, next, sQLiteDatabase, map);
                while (it.hasNext()) {
                    Object next2 = it.next();
                    sQLStatementBuildUpdateSql.bindArgs = SQLBuilder.buildUpdateSqlArgsOnly(next2, columnsValue);
                    CascadeSQLiteImpl.this.updateRecursive(sQLStatementBuildUpdateSql, next2, sQLiteDatabase, map);
                }
                return Integer.valueOf(collection.size());
            }
        })) == null) {
            return -1;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int updateRecursive(SQLStatement sQLStatement, Object obj, SQLiteDatabase sQLiteDatabase, HashMap<String, Integer> map) {
        EntityTable table = TableManager.getTable(obj);
        if (map.get(table.name + FieldUtils.get(table.key.field, obj)) != null) {
            return -1;
        }
        int iExecUpdate = sQLStatement.execUpdate(sQLiteDatabase);
        Object obj2 = FieldUtils.get(table.key.field, obj);
        map.put(table.name + obj2, 1);
        handleMapping(obj2, obj, sQLiteDatabase, true, map);
        return iExecUpdate;
    }

    @Override // com.ju.lib.datalayer.database.JuOrm
    public JuOrm cascade() {
        return this;
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public int delete(final Object obj) {
        acquireReference();
        try {
            try {
                Integer num = (Integer) Transaction.execute(this.mHelper.getWritableDatabase(), new Transaction.Worker<Integer>() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.4
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.ju.lib.datalayer.database.asist.Transaction.Worker
                    public Integer doTransaction(SQLiteDatabase sQLiteDatabase) {
                        return Integer.valueOf(CascadeSQLiteImpl.this.checkTableAndDeleteRecursive(obj, sQLiteDatabase, new HashMap()));
                    }
                });
                if (num != null) {
                    return num.intValue();
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
    public <T> int deleteAll(Class<T> cls) {
        acquireReference();
        try {
            return delete((Collection) query(new QueryBuilder(cls).columns(new String[]{TableManager.getTable((Class<?>) cls).key.column})));
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public long insert(Object obj) {
        return insert(obj, (ConflictAlgorithm) null);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> ArrayList<T> query(QueryBuilder<T> queryBuilder) {
        return checkTableAndQuery(queryBuilder.getQueryClass(), queryBuilder);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> ArrayList<T> queryAll(Class<T> cls) {
        return checkTableAndQuery(cls, new QueryBuilder(cls));
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> T queryById(long j, Class<T> cls) {
        return (T) queryById(String.valueOf(j), cls);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public long save(final Object obj) {
        acquireReference();
        try {
            Long l = (Long) Transaction.execute(this.mHelper.getWritableDatabase(), new Transaction.Worker<Long>() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.ju.lib.datalayer.database.asist.Transaction.Worker
                public Long doTransaction(SQLiteDatabase sQLiteDatabase) {
                    return Long.valueOf(CascadeSQLiteImpl.access$000(CascadeSQLiteImpl.this, obj, sQLiteDatabase, new HashMap()));
                }
            });
            return l == null ? -1L : l.longValue();
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.JuOrm
    public JuOrm single() {
        if (this.otherDatabase == null) {
            this.otherDatabase = new SingleSQLiteImpl(this);
        }
        return this.otherDatabase;
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public int update(Object obj) {
        return update(obj, (ColumnsValue) null, (ConflictAlgorithm) null);
    }

    public CascadeSQLiteImpl(DatabaseConfig databaseConfig) {
        super(databaseConfig);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public long insert(final Object obj, final ConflictAlgorithm conflictAlgorithm) {
        acquireReference();
        long jLongValue = -1;
        try {
            try {
                Long l = (Long) Transaction.execute(this.mHelper.getWritableDatabase(), new Transaction.Worker<Long>() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.ju.lib.datalayer.database.asist.Transaction.Worker
                    public Long doTransaction(SQLiteDatabase sQLiteDatabase) {
                        CascadeSQLiteImpl.this.mTableManager.checkOrCreateTable(sQLiteDatabase, obj);
                        return Long.valueOf(CascadeSQLiteImpl.this.insertRecursive(SQLBuilder.buildInsertSql(obj, conflictAlgorithm), obj, sQLiteDatabase, new HashMap()));
                    }
                });
                if (l != null) {
                    jLongValue = l.longValue();
                }
                return jLongValue;
            } catch (Exception e2) {
                e2.printStackTrace();
                return -1L;
            }
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> T queryById(String str, Class<T> cls) {
        ArrayList<T> arrayListCheckTableAndQuery = checkTableAndQuery(cls, new QueryBuilder(cls).whereEquals(TableManager.getTable((Class<?>) cls).key.column, String.valueOf(str)));
        if (Checker.isEmpty(arrayListCheckTableAndQuery)) {
            return null;
        }
        return arrayListCheckTableAndQuery.get(0);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public int update(Object obj, ConflictAlgorithm conflictAlgorithm) {
        return update(obj, (ColumnsValue) null, conflictAlgorithm);
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public int update(final Object obj, final ColumnsValue columnsValue, final ConflictAlgorithm conflictAlgorithm) {
        acquireReference();
        int iIntValue = -1;
        try {
            try {
                Integer num = (Integer) Transaction.execute(this.mHelper.getWritableDatabase(), new Transaction.Worker<Integer>() { // from class: com.ju.lib.datalayer.database.impl.CascadeSQLiteImpl.3
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.ju.lib.datalayer.database.asist.Transaction.Worker
                    public Integer doTransaction(SQLiteDatabase sQLiteDatabase) {
                        HashMap map = new HashMap();
                        SQLStatement sQLStatementBuildUpdateSql = SQLBuilder.buildUpdateSql(obj, columnsValue, conflictAlgorithm);
                        CascadeSQLiteImpl.this.mTableManager.checkOrCreateTable(sQLiteDatabase, obj);
                        return Integer.valueOf(CascadeSQLiteImpl.this.updateRecursive(sQLStatementBuildUpdateSql, obj, sQLiteDatabase, map));
                    }
                });
                if (num != null) {
                    iIntValue = num.intValue();
                }
                return iIntValue;
            } catch (Exception e2) {
                e2.printStackTrace();
                return -1;
            }
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int save(Collection<T> collection) {
        acquireReference();
        try {
            return saveCollection(collection);
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int delete(Collection<T> collection) {
        acquireReference();
        try {
            return deleteCollectionIfTableHasCreated(collection);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        } finally {
            releaseReference();
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
            return insertCollection(collection, conflictAlgorithm);
        } catch (Exception e2) {
            e2.printStackTrace();
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
            return updateCollection(collection, columnsValue, conflictAlgorithm);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public int delete(WhereBuilder whereBuilder) {
        acquireReference();
        try {
            try {
                deleteCollectionIfTableHasCreated(query(new QueryBuilder(whereBuilder.getTableClass()).columns(new String[]{TableManager.getTable((Class<?>) whereBuilder.getTableClass()).key.column}).where(whereBuilder)));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return -1;
        } finally {
            releaseReference();
        }
    }

    @Override // com.ju.lib.datalayer.database.api.IDatabase
    public <T> int delete(Class<T> cls, long j, long j2, String str) {
        acquireReference();
        try {
            if (j >= 0 && j2 >= j) {
                if (j != 0) {
                    j--;
                }
                long j3 = j2 == 2147483647L ? -1L : j2 - j;
                EntityTable table = TableManager.getTable((Class<?>) cls);
                int iDelete = delete((Collection) query(new QueryBuilder(cls).limit(j + "," + j3).appendOrderAscBy(str).columns(new String[]{table.key.column})));
                releaseReference();
                return iDelete;
            }
            throw new RuntimeException("start must >=0 and smaller than end");
        } catch (Throwable th) {
            releaseReference();
            throw th;
        }
    }
}
