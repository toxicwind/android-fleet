package com.ju.lib.datalayer.database.table;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import b.a.a.a.a;
import com.ju.lib.datalayer.database.JuOrmLog;
import com.ju.lib.datalayer.database.annotation.Column;
import com.ju.lib.datalayer.database.annotation.Mapping;
import com.ju.lib.datalayer.database.annotation.Table;
import com.ju.lib.datalayer.database.asist.Checker;
import com.ju.lib.datalayer.database.asist.Querier;
import com.ju.lib.datalayer.database.asist.SQLBuilder;
import com.ju.lib.datalayer.database.asist.SQLStatement;
import com.ju.lib.datalayer.database.asist.Transaction;
import com.ju.lib.datalayer.database.enums.AssignType;
import com.ju.lib.datalayer.database.model.EntityTable;
import com.ju.lib.datalayer.database.model.MapProperty;
import com.ju.lib.datalayer.database.model.PrimaryKey;
import com.ju.lib.datalayer.database.model.Property;
import com.ju.lib.datalayer.database.model.SQLiteColumn;
import com.ju.lib.datalayer.database.model.SQLiteTable;
import com.ju.lib.datalayer.database.utils.DataUtils;
import com.ju.lib.datalayer.database.utils.FieldUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class TableManager {
    public static final String TAG = "TableManager";
    public String dbName;
    public final HashMap<String, SQLiteTable> mSqlTableMap = new HashMap<>();
    public static final String[] ID = {"id", "_id"};
    public static final HashMap<String, EntityTable> mEntityTableMap = new HashMap<>();

    public TableManager(String str, SQLiteDatabase sQLiteDatabase) {
        this.dbName = "";
        this.dbName = str;
        initSqlTable(sQLiteDatabase);
    }

    private boolean checkExistAndColumns(SQLiteDatabase sQLiteDatabase, EntityTable entityTable) {
        SQLiteTable sQLiteTable = this.mSqlTableMap.get(entityTable.name);
        if (sQLiteTable == null) {
            JuOrmLog.d(TAG, "checkExistAndColumns() Table [", entityTable.name, "] Not Exist");
            return false;
        }
        JuOrmLog.d(TAG, "checkExistAndColumns() Table ", entityTable.name, " Exist");
        if (!sQLiteTable.isTableChecked) {
            sQLiteTable.isTableChecked = true;
            JuOrmLog.i(TAG, "checkExistAndColumns() Table ", entityTable.name, " check column now.");
            PrimaryKey primaryKey = entityTable.key;
            if (primaryKey != null && sQLiteTable.columns.get(primaryKey.column) == null) {
                SQLBuilder.buildDropTable(sQLiteTable.name).execute(sQLiteDatabase);
                JuOrmLog.i(TAG, "checkExistAndColumns() Table [", entityTable.name, "] Primary Key has changed, so drop and recreate it later.");
                return false;
            }
            if (entityTable.pmap != null) {
                ArrayList<String> arrayList = new ArrayList();
                for (String str : entityTable.pmap.keySet()) {
                    if (sQLiteTable.columns.get(str) == null) {
                        arrayList.add(str);
                    }
                }
                if (!Checker.isEmpty(arrayList)) {
                    for (String str2 : arrayList) {
                        LinkedHashMap<String, Property> linkedHashMap = entityTable.pmap;
                        Property property = linkedHashMap == null ? null : linkedHashMap.get(str2);
                        if (property != null && SQLBuilder.withUniqueProperty(property.field)) {
                            SQLBuilder.buildDropTable(sQLiteTable.name).execute(sQLiteDatabase);
                            JuOrmLog.i(TAG, "checkExistAndColumns() Table [", entityTable.name, "] Unique Key has changed, so drop and recreate it later.");
                            return false;
                        }
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        sQLiteTable.columns.put((String) it.next(), 1);
                    }
                    JuOrmLog.i(TAG, "checkExistAndColumns() Table [", entityTable.name, "] add ", Integer.valueOf(insertNewColunms(sQLiteDatabase, entityTable.name, arrayList, entityTable)), " new column ： ", arrayList);
                }
            }
        }
        return true;
    }

    public static void checkPrimaryKey(PrimaryKey primaryKey) {
        if (primaryKey.isAssignedBySystem()) {
            if (!FieldUtils.isNumber(primaryKey.field.getType())) {
                throw new RuntimeException(a.a(new StringBuilder(), AssignType.AUTO_INCREMENT, " Auto increment primary key must be a number ...\n 错误提示：自增主键必须设置为数字类型"));
            }
        } else {
            if (!primaryKey.isAssignedByMyself()) {
                throw new RuntimeException(" Primary key without Assign Type ...\n 错误提示：主键无类型");
            }
            if (String.class != primaryKey.field.getType() && !FieldUtils.isNumber(primaryKey.field.getType())) {
                throw new RuntimeException(a.a(new StringBuilder(), AssignType.BY_MYSELF, " Custom primary key must be string or number ...\n 错误提示：自定义主键值必须为String或者Number类型"));
            }
        }
    }

    private boolean createTable(SQLiteDatabase sQLiteDatabase, EntityTable entityTable) {
        return SQLBuilder.buildCreateTable(entityTable).execute(sQLiteDatabase);
    }

    private boolean dropTable(SQLiteDatabase sQLiteDatabase, String str) {
        return SQLBuilder.buildDropTable(str).execute(sQLiteDatabase);
    }

    public static EntityTable getEntityTable(String str) {
        return mEntityTableMap.get(str);
    }

    public static String getMapTableName(Class cls, Class cls2) {
        return getMapTableName(getTableName(cls), getTableName(cls2));
    }

    private EntityTable getMappingTable(String str, String str2, String str3) {
        EntityTable entityTable = getEntityTable(this.dbName + str);
        if (entityTable != null) {
            return entityTable;
        }
        EntityTable entityTable2 = new EntityTable();
        entityTable2.name = str;
        entityTable2.pmap = new LinkedHashMap<>();
        entityTable2.pmap.put(str2, null);
        entityTable2.pmap.put(str3, null);
        putEntityTable(this.dbName + str, entityTable2);
        return entityTable2;
    }

    public static EntityTable getTable(Object obj) {
        return getTable(obj.getClass(), true);
    }

    public static String getTableName(Class<?> cls) {
        Table table = (Table) cls.getAnnotation(Table.class);
        return table != null ? table.value() : cls.getName().replaceAll("\\.", "_");
    }

    private void initAllTablesFromSQLite(SQLiteDatabase sQLiteDatabase) {
        synchronized (this.mSqlTableMap) {
            if (Checker.isEmpty(this.mSqlTableMap)) {
                SQLStatement sQLStatementBuildTableObtainAll = SQLBuilder.buildTableObtainAll();
                final EntityTable table = getTable(SQLiteTable.class, false);
                Querier.doQuery(sQLiteDatabase, sQLStatementBuildTableObtainAll, new Querier.CursorParser() { // from class: com.ju.lib.datalayer.database.table.TableManager.1
                    @Override // com.ju.lib.datalayer.database.asist.Querier.CursorParser
                    public void parseEachCursor(SQLiteDatabase sQLiteDatabase2, Cursor cursor) throws IllegalAccessException {
                        SQLiteTable sQLiteTable = new SQLiteTable();
                        DataUtils.injectDataToObject(cursor, sQLiteTable, table);
                        ArrayList<String> allColumnsFromSQLite = TableManager.this.getAllColumnsFromSQLite(sQLiteDatabase2, sQLiteTable.name);
                        if (Checker.isEmpty(allColumnsFromSQLite)) {
                            allColumnsFromSQLite = TableManager.this.transformSqlToColumns(sQLiteTable.sql);
                        }
                        sQLiteTable.columns = new HashMap<>();
                        Iterator<String> it = allColumnsFromSQLite.iterator();
                        while (it.hasNext()) {
                            sQLiteTable.columns.put(it.next(), 1);
                        }
                        TableManager.this.mSqlTableMap.put(sQLiteTable.name, sQLiteTable);
                    }
                });
            }
        }
    }

    private int insertNewColunms(SQLiteDatabase sQLiteDatabase, final String str, final List<String> list, final EntityTable entityTable) {
        Integer num = !Checker.isEmpty(list) ? (Integer) Transaction.execute(sQLiteDatabase, new Transaction.Worker<Integer>() { // from class: com.ju.lib.datalayer.database.table.TableManager.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.ju.lib.datalayer.database.asist.Transaction.Worker
            public Integer doTransaction(SQLiteDatabase sQLiteDatabase2) {
                for (String str2 : list) {
                    JuOrmLog.d(TableManager.TAG, "insertNewColunms:", str, ",", str2);
                    String str3 = str;
                    LinkedHashMap<String, Property> linkedHashMap = entityTable.pmap;
                    SQLBuilder.buildAddColumnSql(str3, str2, linkedHashMap == null ? null : linkedHashMap.get(str2)).execute(sQLiteDatabase2);
                }
                return Integer.valueOf(list.size());
            }
        }) : null;
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private boolean migrateTable(SQLiteDatabase sQLiteDatabase, String str, String str2, HashMap<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        for (String str3 : map.keySet()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(str3);
        }
        return SQLBuilder.buildMigrateTableSql(str, str2, sb.toString()).execute(sQLiteDatabase);
    }

    public static EntityTable putEntityTable(String str, EntityTable entityTable) {
        return mEntityTableMap.put(str, entityTable);
    }

    private void putNewSqlTableIntoMap(EntityTable entityTable) {
        JuOrmLog.i(TAG, "putNewSqlTableIntoMap() Table [", entityTable.name, "] Create Success");
        SQLiteTable sQLiteTable = new SQLiteTable();
        sQLiteTable.name = entityTable.name;
        sQLiteTable.columns = new HashMap<>();
        PrimaryKey primaryKey = entityTable.key;
        if (primaryKey != null) {
            sQLiteTable.columns.put(primaryKey.column, 1);
        }
        LinkedHashMap<String, Property> linkedHashMap = entityTable.pmap;
        if (linkedHashMap != null) {
            Iterator<String> it = linkedHashMap.keySet().iterator();
            while (it.hasNext()) {
                sQLiteTable.columns.put(it.next(), 1);
            }
        }
        sQLiteTable.isTableChecked = true;
        this.mSqlTableMap.put(sQLiteTable.name, sQLiteTable);
    }

    private boolean renameTable(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        return SQLBuilder.buildRenameTable(str, str2).execute(sQLiteDatabase);
    }

    public synchronized void checkOrCreateMappingTable(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        EntityTable mappingTable = getMappingTable(str, str2, str3);
        if (!checkExistAndColumns(sQLiteDatabase, mappingTable) && createTable(sQLiteDatabase, mappingTable)) {
            putNewSqlTableIntoMap(mappingTable);
        }
    }

    public EntityTable checkOrCreateTable(SQLiteDatabase sQLiteDatabase, Object obj) {
        return checkOrCreateTable(sQLiteDatabase, (Class) obj.getClass());
    }

    public void clearSqlTable() {
        synchronized (this.mSqlTableMap) {
            this.mSqlTableMap.clear();
        }
    }

    public ArrayList<String> getAllColumnsFromSQLite(SQLiteDatabase sQLiteDatabase, String str) {
        final EntityTable table = getTable(SQLiteColumn.class, false);
        final ArrayList<String> arrayList = new ArrayList<>();
        Querier.doQuery(sQLiteDatabase, SQLBuilder.buildColumnsObtainAll(str), new Querier.CursorParser() { // from class: com.ju.lib.datalayer.database.table.TableManager.3
            @Override // com.ju.lib.datalayer.database.asist.Querier.CursorParser
            public void parseEachCursor(SQLiteDatabase sQLiteDatabase2, Cursor cursor) throws IllegalAccessException {
                SQLiteColumn sQLiteColumn = new SQLiteColumn();
                DataUtils.injectDataToObject(cursor, sQLiteColumn, table);
                arrayList.add(sQLiteColumn.name);
            }
        });
        return arrayList;
    }

    public void initSqlTable(SQLiteDatabase sQLiteDatabase) {
        initAllTablesFromSQLite(sQLiteDatabase);
    }

    public boolean isSQLMapTableCreated(String str, String str2) {
        return this.mSqlTableMap.get(getMapTableName(str, str2)) != null;
    }

    public boolean isSQLTableCreated(String str) {
        return this.mSqlTableMap.get(str) != null;
    }

    public void release() {
        clearSqlTable();
        mEntityTableMap.clear();
    }

    public ArrayList<String> transformSqlToColumns(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOf = str.indexOf("(");
        int iLastIndexOf = str.lastIndexOf(")");
        if (iIndexOf <= 0 || iLastIndexOf <= 0) {
            return null;
        }
        String strSubstring = str.substring(iIndexOf + 1, iLastIndexOf);
        String[] strArrSplit = strSubstring.split(",");
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str2 : strArrSplit) {
            String strTrim = str2.trim();
            int iIndexOf2 = strTrim.indexOf(" ");
            if (iIndexOf2 > 0) {
                strTrim = strTrim.substring(0, iIndexOf2);
            }
            arrayList.add(strTrim);
        }
        JuOrmLog.e(TAG, "transformSqlToColumns() get table columns（", arrayList, ") , Origin SQL is: ", strSubstring);
        return arrayList;
    }

    public boolean upgradeTable(SQLiteDatabase sQLiteDatabase, SQLiteTable sQLiteTable, EntityTable entityTable) {
        String str = sQLiteTable.name;
        String strA = a.a(str, "_upgrd_tmp");
        if (!renameTable(sQLiteDatabase, str, strA)) {
            dropTable(sQLiteDatabase, str);
            JuOrmLog.i(TAG, "Fail to rename table, drop table[", str, "].");
            return false;
        }
        if (!createTable(sQLiteDatabase, entityTable)) {
            JuOrmLog.i(TAG, "Fail to create table.");
            return false;
        }
        putNewSqlTableIntoMap(entityTable);
        boolean zMigrateTable = migrateTable(sQLiteDatabase, strA, str, sQLiteTable.columns);
        if (!zMigrateTable) {
            JuOrmLog.i(TAG, "Fail to migrate table.");
        }
        dropTable(sQLiteDatabase, strA);
        JuOrmLog.i(TAG, "Success to migrate table.");
        return zMigrateTable;
    }

    public static String getMapTableName(EntityTable entityTable, EntityTable entityTable2) {
        return getMapTableName(entityTable.name, entityTable2.name);
    }

    public static EntityTable getTable(Class<?> cls) {
        return getTable(cls, true);
    }

    public synchronized EntityTable checkOrCreateTable(SQLiteDatabase sQLiteDatabase, Class cls) {
        EntityTable table;
        table = getTable((Class<?>) cls);
        if (!checkExistAndColumns(sQLiteDatabase, table) && createTable(sQLiteDatabase, table)) {
            putNewSqlTableIntoMap(table);
        }
        return table;
    }

    public static String getMapTableName(String str, String str2) {
        if (str.compareTo(str2) < 0) {
            return a.a(str, "_", str2);
        }
        return a.a(str2, "_", str);
    }

    public static synchronized EntityTable getTable(Class<?> cls, boolean z) {
        EntityTable entityTable;
        entityTable = getEntityTable(cls.getName());
        if (entityTable == null) {
            entityTable = new EntityTable();
            entityTable.claxx = cls;
            entityTable.name = getTableName(cls);
            entityTable.pmap = new LinkedHashMap<>();
            for (Field field : FieldUtils.getAllDeclaredFields(cls)) {
                Column column = (Column) field.getAnnotation(Column.class);
                Property property = new Property(column != null ? column.value() : field.getName(), field);
                com.ju.lib.datalayer.database.annotation.PrimaryKey primaryKey = (com.ju.lib.datalayer.database.annotation.PrimaryKey) field.getAnnotation(com.ju.lib.datalayer.database.annotation.PrimaryKey.class);
                if (primaryKey != null) {
                    entityTable.key = new PrimaryKey(property, primaryKey.value());
                    checkPrimaryKey(entityTable.key);
                } else {
                    Mapping mapping = (Mapping) field.getAnnotation(Mapping.class);
                    if (mapping != null) {
                        entityTable.addMapping(new MapProperty(property, mapping.value()));
                    } else {
                        entityTable.pmap.put(property.column, property);
                    }
                }
            }
            if (entityTable.key == null) {
                for (String str : entityTable.pmap.keySet()) {
                    for (String str2 : ID) {
                        if (str2.equalsIgnoreCase(str)) {
                            Property property2 = entityTable.pmap.get(str);
                            if (property2.field.getType() == String.class) {
                                entityTable.pmap.remove(str);
                                entityTable.key = new PrimaryKey(property2, AssignType.BY_MYSELF);
                                break;
                            }
                            if (FieldUtils.isNumber(property2.field.getType())) {
                                entityTable.pmap.remove(str);
                                entityTable.key = new PrimaryKey(property2, AssignType.AUTO_INCREMENT);
                                break;
                            }
                        }
                    }
                    if (entityTable.key != null) {
                        break;
                    }
                }
            }
            if (z && entityTable.key == null) {
                throw new RuntimeException("你必须为[" + entityTable.claxx.getSimpleName() + "]设置主键(you must set the primary key...)\n 提示：在对象的属性上加PrimaryKey注解来设置主键。");
            }
            putEntityTable(cls.getName(), entityTable);
        }
        return entityTable;
    }
}
