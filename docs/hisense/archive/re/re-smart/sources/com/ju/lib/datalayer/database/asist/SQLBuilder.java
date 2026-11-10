package com.ju.lib.datalayer.database.asist;

import android.util.SparseArray;
import b.a.a.a.a;
import com.ju.lib.datalayer.database.JuOrmLog;
import com.ju.lib.datalayer.database.annotation.Check;
import com.ju.lib.datalayer.database.annotation.Collate;
import com.ju.lib.datalayer.database.annotation.Conflict;
import com.ju.lib.datalayer.database.annotation.Default;
import com.ju.lib.datalayer.database.annotation.NotNull;
import com.ju.lib.datalayer.database.annotation.Temporary;
import com.ju.lib.datalayer.database.annotation.Unique;
import com.ju.lib.datalayer.database.annotation.UniqueCombine;
import com.ju.lib.datalayer.database.cache.manager.CacheManager;
import com.ju.lib.datalayer.database.enums.AssignType;
import com.ju.lib.datalayer.database.model.ColumnsValue;
import com.ju.lib.datalayer.database.model.ConflictAlgorithm;
import com.ju.lib.datalayer.database.model.EntityTable;
import com.ju.lib.datalayer.database.model.MapInfo;
import com.ju.lib.datalayer.database.model.MapProperty;
import com.ju.lib.datalayer.database.model.PrimaryKey;
import com.ju.lib.datalayer.database.model.Property;
import com.ju.lib.datalayer.database.table.TableManager;
import com.ju.lib.datalayer.database.utils.ClassUtils;
import com.ju.lib.datalayer.database.utils.DataUtils;
import com.ju.lib.datalayer.database.utils.FieldUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class SQLBuilder {
    public static final String AND = " AND ";
    public static final String ASC = " ASC ";
    public static final String ASTERISK = "*";
    public static final String BLANK = " ";
    public static final String CHECK = "CHECK ";
    public static final String COLLATE = "COLLATE ";
    public static final String COMMA = ",";
    public static final String COMMA_HOLDER = ",?";
    public static final String CREATE = "CREATE ";
    public static final String DEFAULT = "DEFAULT ";
    public static final String DELETE_FROM = "DELETE FROM ";
    public static final String DESC = " DESC ";
    public static final String DROP_TABLE = "DROP TABLE ";
    public static final String EQUALS_HOLDER = "=?";
    public static final String FROM = " FROM ";
    public static final String HOLDER = "?";
    public static final String IN = " IN ";
    public static final String INSERT = "INSERT ";
    public static final String INTO = "INTO ";
    public static final String LIMIT = " LIMIT ";
    public static final String NOT = " NOT ";
    public static final String NOT_NULL = "NOT NULL ";
    public static final String ON_CONFLICT = "ON CONFLICT ";
    public static final String OR = " OR ";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String PARENTHESES_LEFT = "(";
    public static final String PARENTHESES_RIGHT = ")";
    public static final String PRAGMA_TABLE_INFO = "PRAGMA table_info(";
    public static final String PRIMARY_KEY = "PRIMARY KEY ";
    public static final String PRIMARY_KEY_AUTOINCREMENT = "PRIMARY KEY AUTOINCREMENT ";
    public static final String REPLACE = "REPLACE ";
    public static final String SELECT = "SELECT ";
    public static final String SELECT_ANY_FROM = "SELECT * FROM ";
    public static final String SELECT_MAX = "SELECT MAX ";
    public static final String SELECT_TABLES = "SELECT * FROM sqlite_master WHERE type='table' ORDER BY name";
    public static final String SET = " SET ";
    public static final String TABLE_IF_NOT_EXISTS = "TABLE IF NOT EXISTS ";
    public static final String TAG = "SQLBuilder";
    public static final String TEMP = "TEMP ";
    public static final String TWO_HOLDER = "(?,?)";
    public static final int TYPE_INSERT = 1;
    public static final int TYPE_REPLACE = 2;
    public static final int TYPE_UPDATE = 3;
    public static final String UNIQUE = "UNIQUE ";
    public static final String UPDATE = "UPDATE ";
    public static final String VALUES = "VALUES";
    public static final String WHERE = " WHERE ";

    public static SQLStatement buildAddColumnSql(String str, String str2, Property property) {
        SQLStatement sQLStatement = new SQLStatement();
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE ");
        sb.append(str);
        sb.append(" ADD COLUMN ");
        sb.append(str2);
        if (property == null) {
            sb.append(DataUtils.TEXT);
        } else {
            Field field = property.field;
            sb.append(DataUtils.getSQLDataType(property.classType));
            sb.append(buildColumnDescription(field));
        }
        sQLStatement.sql = sb.toString();
        JuOrmLog.i(TAG, "buildAddColumnSql: ", sQLStatement.sql);
        return sQLStatement;
    }

    public static String buildColumnDescription(Field field) {
        StringBuilder sb = new StringBuilder();
        if (field.getAnnotation(NotNull.class) != null) {
            sb.append(NOT_NULL);
        }
        if (field.getAnnotation(Default.class) != null) {
            sb.append(DEFAULT);
            sb.append(((Default) field.getAnnotation(Default.class)).value());
            sb.append(" ");
        }
        if (field.getAnnotation(Check.class) != null) {
            sb.append("CHECK (");
            sb.append(((Check) field.getAnnotation(Check.class)).value());
            sb.append(")");
            sb.append(" ");
        }
        if (field.getAnnotation(Collate.class) != null) {
            sb.append(COLLATE);
            sb.append(((Collate) field.getAnnotation(Collate.class)).value());
            sb.append(" ");
        }
        return sb.toString();
    }

    public static SQLStatement buildColumnsObtainAll(String str) {
        return new SQLStatement(a.a(PRAGMA_TABLE_INFO, str, ")"), null);
    }

    public static SQLStatement buildCreateTable(EntityTable entityTable) {
        StringBuilder sbA = a.a(CREATE);
        if (entityTable.getAnnotation(Temporary.class) != null) {
            sbA.append(TEMP);
        }
        sbA.append(TABLE_IF_NOT_EXISTS);
        sbA.append(entityTable.name);
        sbA.append("(");
        boolean z = false;
        PrimaryKey primaryKey = entityTable.key;
        if (primaryKey != null) {
            z = true;
            if (primaryKey.assign == AssignType.AUTO_INCREMENT) {
                sbA.append(primaryKey.column);
                sbA.append(DataUtils.INTEGER);
                sbA.append(PRIMARY_KEY_AUTOINCREMENT);
            } else {
                sbA.append(primaryKey.column);
                sbA.append(DataUtils.getSQLDataType(entityTable.key.classType));
                sbA.append(PRIMARY_KEY);
                if (entityTable.key.field.getAnnotation(Conflict.class) != null) {
                    sbA.append(ON_CONFLICT);
                    sbA.append(((Conflict) entityTable.key.field.getAnnotation(Conflict.class)).value().getSql());
                    sbA.append(" ");
                }
            }
        }
        if (!Checker.isEmpty(entityTable.pmap)) {
            if (z) {
                sbA.append(",");
            }
            setupSqlCondition(sbA, entityTable);
        }
        sbA.append(")");
        return new SQLStatement(sbA.toString(), null);
    }

    public static MapInfo buildDelAllMappingSql(Class cls) {
        EntityTable table = TableManager.getTable((Class<?>) cls);
        if (Checker.isEmpty(table.mappingList)) {
            return null;
        }
        try {
            MapInfo mapInfo = new MapInfo();
            Iterator<MapProperty> it = table.mappingList.iterator();
            while (it.hasNext()) {
                EntityTable table2 = TableManager.getTable((Class<?>) getTypeByRelation(it.next()));
                mapInfo.addTable(new MapInfo.MapTable(TableManager.getMapTableName(table, table2), table.name, table2.name));
                mapInfo.addDelOldRelationSQL(buildMappingDeleteAllSql(table, table2));
            }
            return mapInfo;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static SQLStatement buildDeleteAllSql(Class<?> cls) {
        SQLStatement sQLStatement = new SQLStatement();
        EntityTable table = TableManager.getTable(cls);
        StringBuilder sbA = a.a("DELETE FROM ");
        sbA.append(table.name);
        sQLStatement.sql = sbA.toString();
        return sQLStatement;
    }

    public static SQLStatement buildDeleteSql(Object obj) {
        SQLStatement sQLStatement = new SQLStatement();
        try {
            EntityTable table = TableManager.getTable(obj);
            int i = 0;
            if (table.key != null) {
                sQLStatement.sql = "DELETE FROM " + table.name + " WHERE " + table.key.column + "=?";
                sQLStatement.bindArgs = new String[]{String.valueOf(FieldUtils.get(table.key.field, obj))};
            } else if (!Checker.isEmpty(table.pmap)) {
                StringBuilder sb = new StringBuilder();
                sb.append("DELETE FROM ");
                sb.append(table.name);
                sb.append(" WHERE ");
                Object[] objArr = new Object[table.pmap.size()];
                for (Map.Entry<String, Property> entry : table.pmap.entrySet()) {
                    if (i == 0) {
                        sb.append(entry.getKey());
                        sb.append("=?");
                    } else {
                        sb.append(" AND ");
                        sb.append(entry.getKey());
                        sb.append("=?");
                    }
                    objArr[i] = FieldUtils.get(entry.getValue().field, obj);
                    i++;
                }
                sQLStatement.sql = sb.toString();
                sQLStatement.bindArgs = objArr;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return sQLStatement;
    }

    public static SQLStatement buildDropTable(EntityTable entityTable) {
        StringBuilder sbA = a.a(DROP_TABLE);
        sbA.append(entityTable.name);
        return new SQLStatement(sbA.toString(), null);
    }

    public static SQLStatement buildGetLastRowId(EntityTable entityTable) {
        StringBuilder sbA = a.a("SELECT MAX (");
        sbA.append(entityTable.key.column);
        sbA.append(")");
        sbA.append(" FROM ");
        sbA.append(entityTable.name);
        return new SQLStatement(sbA.toString(), null);
    }

    public static SQLStatement buildInsertAllSql(Object obj, ConflictAlgorithm conflictAlgorithm) {
        return buildInsertSql(obj, false, 1, conflictAlgorithm);
    }

    public static SQLStatement buildInsertSql(Object obj, ConflictAlgorithm conflictAlgorithm) {
        return buildInsertSql(obj, true, 1, conflictAlgorithm);
    }

    public static Object[] buildInsertSqlArgsOnly(Object obj) {
        EntityTable table = TableManager.getTable(obj);
        int i = 1;
        Object[] objArr = new Object[!Checker.isEmpty(table.pmap) ? table.pmap.size() + 1 : 1];
        objArr[0] = FieldUtils.getAssignedKeyObject(table.key, obj);
        if (!Checker.isEmpty(table.pmap)) {
            Iterator<Property> it = table.pmap.values().iterator();
            while (it.hasNext()) {
                objArr[i] = FieldUtils.get(it.next().field, obj);
                i++;
            }
        }
        return objArr;
    }

    public static SQLStatement buildMappingDeleteAllSql(EntityTable entityTable, EntityTable entityTable2) {
        if (entityTable2 == null) {
            return null;
        }
        String mapTableName = TableManager.getMapTableName(entityTable, entityTable2);
        SQLStatement sQLStatement = new SQLStatement();
        sQLStatement.sql = a.a("DELETE FROM ", mapTableName);
        return sQLStatement;
    }

    public static SQLStatement buildMappingDeleteSql(Object obj, EntityTable entityTable, EntityTable entityTable2) {
        if (entityTable2 != null) {
            return buildMappingDeleteSql(TableManager.getMapTableName(entityTable, entityTable2), obj, entityTable);
        }
        return null;
    }

    public static MapInfo buildMappingInfo(Object obj, boolean z, TableManager tableManager) {
        Object obj2;
        ArrayList<SQLStatement> arrayListBuildMappingToManySql;
        EntityTable table = TableManager.getTable(obj);
        if (!Checker.isEmpty(table.mappingList)) {
            try {
                Object obj3 = FieldUtils.get(table.key.field, obj);
                if (obj3 == null) {
                    return null;
                }
                MapInfo mapInfo = new MapInfo();
                for (MapProperty mapProperty : table.mappingList) {
                    EntityTable table2 = TableManager.getTable((Class<?>) getTypeByRelation(mapProperty));
                    mapInfo.addTable(new MapInfo.MapTable(TableManager.getMapTableName(table, table2), table.name, table2.name));
                    if (tableManager.isSQLMapTableCreated(table.name, table2.name)) {
                        mapInfo.addDelOldRelationSQL(buildMappingDeleteSql(obj3, table, table2));
                    }
                    if (z && (obj2 = FieldUtils.get(mapProperty.field, obj)) != null) {
                        if (mapProperty.isToMany()) {
                            if (obj2 instanceof Collection) {
                                arrayListBuildMappingToManySql = buildMappingToManySql(obj3, table, table2, (Collection) obj2);
                            } else {
                                if (!(obj2 instanceof Object[])) {
                                    throw new RuntimeException("OneToMany and ManyToMany Relation, You must use array or collection object");
                                }
                                arrayListBuildMappingToManySql = buildMappingToManySql(obj3, table, table2, Arrays.asList((Object[]) obj2));
                            }
                            if (Checker.isEmpty(arrayListBuildMappingToManySql)) {
                                mapInfo.addNewRelationSQL(arrayListBuildMappingToManySql);
                            }
                        } else {
                            SQLStatement sQLStatementBuildMappingToOneSql = buildMappingToOneSql(obj3, table, table2, obj2);
                            if (sQLStatementBuildMappingToOneSql != null) {
                                mapInfo.addNewRelationSQL(sQLStatementBuildMappingToOneSql);
                            }
                        }
                    }
                }
                return mapInfo;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static <T> ArrayList<SQLStatement> buildMappingToManySql(final Object obj, final EntityTable entityTable, final EntityTable entityTable2, Collection<T> collection) {
        final ArrayList<SQLStatement> arrayList = new ArrayList<>();
        CollSpliter.split(collection, 499, new CollSpliter.Spliter<T>() { // from class: com.ju.lib.datalayer.database.asist.SQLBuilder.1
            @Override // com.ju.lib.datalayer.database.asist.CollSpliter.Spliter
            public int oneSplit(ArrayList<T> arrayList2) {
                SQLStatement sQLStatementBuildMappingToManySqlFragment = SQLBuilder.buildMappingToManySqlFragment(obj, entityTable, entityTable2, arrayList2);
                if (sQLStatementBuildMappingToManySqlFragment == null) {
                    return 0;
                }
                arrayList.add(sQLStatementBuildMappingToManySqlFragment);
                return 0;
            }
        });
        return arrayList;
    }

    public static SQLStatement buildMappingToManySqlFragment(Object obj, EntityTable entityTable, EntityTable entityTable2, Collection<?> collection) {
        String mapTableName = TableManager.getMapTableName(entityTable, entityTable2);
        if (Checker.isEmpty(collection)) {
            return null;
        }
        boolean z = true;
        StringBuilder sb = new StringBuilder(128);
        ArrayList arrayList = new ArrayList();
        String strValueOf = String.valueOf(obj);
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            Object assignedKeyObject = FieldUtils.getAssignedKeyObject(entityTable2.key, it.next());
            if (assignedKeyObject != null) {
                if (z) {
                    sb.append(TWO_HOLDER);
                    z = false;
                } else {
                    sb.append(",");
                    sb.append(TWO_HOLDER);
                }
                arrayList.add(strValueOf);
                arrayList.add(String.valueOf(assignedKeyObject));
            }
        }
        Object[] array = arrayList.toArray(new String[arrayList.size()]);
        if (Checker.isEmpty(array)) {
            return null;
        }
        SQLStatement sQLStatement = new SQLStatement();
        StringBuilder sbB = a.b("REPLACE INTO ", mapTableName, "(");
        sbB.append(entityTable.name);
        sbB.append(",");
        sbB.append(entityTable2.name);
        sbB.append(")");
        sbB.append(VALUES);
        sbB.append((Object) sb);
        sQLStatement.sql = sbB.toString();
        sQLStatement.bindArgs = array;
        return sQLStatement;
    }

    public static SQLStatement buildMappingToOneSql(Object obj, EntityTable entityTable, EntityTable entityTable2, Object obj2) {
        Object assignedKeyObject = FieldUtils.getAssignedKeyObject(entityTable2.key, obj2);
        if (assignedKeyObject != null) {
            return buildMappingToOneSql(TableManager.getMapTableName(entityTable, entityTable2), obj, assignedKeyObject, entityTable, entityTable2);
        }
        return null;
    }

    public static SQLStatement buildMigrateTableSql(String str, String str2, String str3) {
        SQLStatement sQLStatement = new SQLStatement();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(str2);
        sb.append(" (");
        sb.append(str3);
        sb.append(") ");
        a.a(sb, " SELECT ", str3, " FROM ", str);
        JuOrmLog.i(TAG, "migrate table:", sb.toString());
        return sQLStatement;
    }

    public static SQLStatement buildQueryMapEntitySql(EntityTable entityTable, Object obj) {
        SQLStatement sQLStatement = new SQLStatement();
        StringBuilder sbA = a.a(SELECT_ANY_FROM);
        sbA.append(entityTable.name);
        sbA.append(" WHERE ");
        sQLStatement.sql = a.a(sbA, entityTable.key.column, "=?");
        sQLStatement.bindArgs = new String[]{String.valueOf(obj)};
        return sQLStatement;
    }

    public static SQLStatement buildQueryRelationSql(Class cls, Class cls2, List<String> list) {
        return buildQueryRelationSql(cls, cls2, list, null);
    }

    public static SQLStatement buildRenameTable(String str, String str2) {
        return new SQLStatement("ALTER TABLE " + str + " RENAME TO " + str2, null);
    }

    public static SQLStatement buildReplaceAllSql(Object obj) {
        return buildInsertSql(obj, false, 2, null);
    }

    public static SQLStatement buildReplaceSql(Object obj) {
        return buildInsertSql(obj, true, 2, null);
    }

    public static SQLStatement buildTableObtainAll() {
        return new SQLStatement(SELECT_TABLES, null);
    }

    public static SQLStatement buildUpdateAllSql(Object obj, ColumnsValue columnsValue, ConflictAlgorithm conflictAlgorithm) {
        return buildUpdateSql(obj, columnsValue, conflictAlgorithm, false);
    }

    public static SQLStatement buildUpdateSql(Object obj, ColumnsValue columnsValue, ConflictAlgorithm conflictAlgorithm) {
        return buildUpdateSql(obj, columnsValue, conflictAlgorithm, true);
    }

    public static Object[] buildUpdateSqlArgsOnly(Object obj, ColumnsValue columnsValue) {
        Object[] objArr;
        int size;
        EntityTable table = TableManager.getTable(obj);
        int i = 0;
        if (columnsValue != null && columnsValue.checkColumns()) {
            int length = columnsValue.columns.length + 1;
            objArr = new Object[length];
            while (true) {
                String[] strArr = columnsValue.columns;
                if (i >= strArr.length) {
                    break;
                }
                objArr[i] = columnsValue.getValue(strArr[i]);
                if (objArr[i] == null) {
                    objArr[i] = FieldUtils.get(table.pmap.get(columnsValue.columns[i]).field, obj);
                }
                i++;
            }
            size = length;
        } else if (Checker.isEmpty(table.pmap)) {
            objArr = new Object[1];
            size = 1;
        } else {
            size = table.pmap.size() + 1;
            objArr = new Object[size];
            Iterator<Map.Entry<String, Property>> it = table.pmap.entrySet().iterator();
            while (it.hasNext()) {
                objArr[i] = FieldUtils.get(it.next().getValue().field, obj);
                i++;
            }
        }
        objArr[size - 1] = FieldUtils.getAssignedKeyObject(table.key, obj);
        return objArr;
    }

    public static Class getTypeByRelation(MapProperty mapProperty) {
        if (!mapProperty.isToMany()) {
            return mapProperty.field.getType();
        }
        Class<?> type = mapProperty.field.getType();
        if (ClassUtils.isCollection(type)) {
            return FieldUtils.getGenericType(mapProperty.field);
        }
        if (type.isArray()) {
            return FieldUtils.getComponentType(mapProperty.field);
        }
        throw new RuntimeException("OneToMany and ManyToMany Relation, you must use collection or array object");
    }

    public static void setupSqlCondition(StringBuilder sb, EntityTable entityTable) {
        SparseArray sparseArray = null;
        boolean z = false;
        for (Map.Entry<String, Property> entry : entityTable.pmap.entrySet()) {
            if (z) {
                sb.append(",");
            } else {
                z = true;
            }
            String key = entry.getKey();
            sb.append(key);
            if (entry.getValue() == null) {
                sb.append(DataUtils.TEXT);
            } else {
                Field field = entry.getValue().field;
                sb.append(DataUtils.getSQLDataType(entry.getValue().classType));
                if (field.getAnnotation(NotNull.class) != null) {
                    sb.append(NOT_NULL);
                }
                if (field.getAnnotation(Default.class) != null) {
                    sb.append(DEFAULT);
                    sb.append(((Default) field.getAnnotation(Default.class)).value());
                    sb.append(" ");
                }
                if (field.getAnnotation(Unique.class) != null) {
                    sb.append(UNIQUE);
                }
                if (field.getAnnotation(Conflict.class) != null) {
                    sb.append(ON_CONFLICT);
                    sb.append(((Conflict) field.getAnnotation(Conflict.class)).value().getSql());
                    sb.append(" ");
                }
                if (field.getAnnotation(Check.class) != null) {
                    sb.append("CHECK (");
                    sb.append(((Check) field.getAnnotation(Check.class)).value());
                    sb.append(")");
                    sb.append(" ");
                }
                if (field.getAnnotation(Collate.class) != null) {
                    sb.append(COLLATE);
                    sb.append(((Collate) field.getAnnotation(Collate.class)).value());
                    sb.append(" ");
                }
                UniqueCombine uniqueCombine = (UniqueCombine) field.getAnnotation(UniqueCombine.class);
                if (uniqueCombine != null) {
                    if (sparseArray == null) {
                        sparseArray = new SparseArray();
                    }
                    ArrayList arrayList = (ArrayList) sparseArray.get(uniqueCombine.value());
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        sparseArray.put(uniqueCombine.value(), arrayList);
                    }
                    arrayList.add(key);
                }
            }
        }
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                ArrayList arrayList2 = (ArrayList) sparseArray.valueAt(i);
                if (arrayList2.size() > 1) {
                    sb.append(",");
                    sb.append(UNIQUE);
                    sb.append("(");
                    int size2 = arrayList2.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        if (i2 != 0) {
                            sb.append(",");
                        }
                        sb.append((String) arrayList2.get(i2));
                    }
                    sb.append(")");
                }
            }
        }
    }

    public static boolean withUniqueProperty(Field field) {
        return (field.getAnnotation(Conflict.class) == null && field.getAnnotation(UniqueCombine.class) == null && field.getAnnotation(Unique.class) == null) ? false : true;
    }

    public static SQLStatement buildDropTable(String str) {
        return new SQLStatement(a.a(DROP_TABLE, str), null);
    }

    public static SQLStatement buildInsertSql(Object obj, boolean z, int i, ConflictAlgorithm conflictAlgorithm) {
        SQLStatement sQLStatement = new SQLStatement();
        try {
            EntityTable table = TableManager.getTable(obj);
            StringBuilder sb = new StringBuilder(128);
            if (i != 2) {
                sb.append(INSERT);
                if (conflictAlgorithm != null) {
                    sb.append(conflictAlgorithm.getAlgorithm());
                    sb.append(INTO);
                } else {
                    sb.append(INTO);
                }
            } else {
                sb.append(REPLACE);
                sb.append(INTO);
            }
            sb.append(table.name);
            sb.append("(");
            sb.append(table.key.column);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(")");
            sb2.append(VALUES);
            sb2.append("(");
            sb2.append("?");
            int i2 = 0;
            int size = !Checker.isEmpty(table.pmap) ? table.pmap.size() + 1 : 1;
            Object[] objArr = null;
            if (z) {
                objArr = new Object[size];
                objArr[0] = FieldUtils.getAssignedKeyObject(table.key, obj);
                i2 = 1;
            }
            if (!Checker.isEmpty(table.pmap)) {
                for (Map.Entry<String, Property> entry : table.pmap.entrySet()) {
                    sb.append(",");
                    sb.append(entry.getKey());
                    sb2.append(",?");
                    if (z) {
                        objArr[i2] = FieldUtils.get(entry.getValue().field, obj);
                    }
                    i2++;
                }
            }
            sb.append((CharSequence) sb2);
            sb.append(")");
            sQLStatement.bindArgs = objArr;
            sQLStatement.sql = sb.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return sQLStatement;
    }

    public static SQLStatement buildQueryRelationSql(Class cls, Class cls2, List<String> list, List<String> list2) {
        StringBuilder sb;
        EntityTable table = TableManager.getTable((Class<?>) cls);
        EntityTable table2 = TableManager.getTable((Class<?>) cls2);
        QueryBuilder queryBuilderQueryMappingInfo = new QueryBuilder(cls).queryMappingInfo(cls2);
        ArrayList arrayList = new ArrayList();
        if (Checker.isEmpty(list)) {
            sb = null;
        } else {
            sb = new StringBuilder();
            sb.append(table.name);
            sb.append(" IN ");
            sb.append("(");
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    sb.append("?");
                } else {
                    sb.append(",?");
                }
            }
            sb.append(")");
            arrayList.addAll(list);
        }
        if (!Checker.isEmpty(list2)) {
            if (sb == null) {
                sb = new StringBuilder();
            } else {
                sb.append(" AND ");
            }
            sb.append(table2.name);
            sb.append(" IN ");
            sb.append("(");
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                if (i2 == 0) {
                    sb.append("?");
                } else {
                    sb.append(",?");
                }
            }
            sb.append(")");
            arrayList.addAll(list2);
        }
        if (sb != null) {
            queryBuilderQueryMappingInfo.where(sb.toString(), arrayList.toArray(new String[arrayList.size()]));
        }
        return queryBuilderQueryMappingInfo.createStatement();
    }

    public static SQLStatement buildUpdateSql(Object obj, ColumnsValue columnsValue, ConflictAlgorithm conflictAlgorithm, boolean z) {
        int size;
        Object[] objArr;
        int i;
        SQLStatement sQLStatement = new SQLStatement();
        try {
            EntityTable table = TableManager.getTable(obj);
            StringBuilder sb = new StringBuilder(128);
            sb.append(UPDATE);
            if (conflictAlgorithm != null) {
                sb.append(conflictAlgorithm.getAlgorithm());
            }
            sb.append(table.name);
            sb.append(SET);
            Object[] objArr2 = null;
            if (columnsValue != null && columnsValue.checkColumns()) {
                if (z) {
                    int length = columnsValue.columns.length + 1;
                    objArr = new Object[length];
                    i = length;
                } else {
                    objArr = null;
                    i = 1;
                }
                for (int i2 = 0; i2 < columnsValue.columns.length; i2++) {
                    if (i2 > 0) {
                        sb.append(",");
                    }
                    sb.append(columnsValue.columns[i2]);
                    sb.append("=?");
                    if (z) {
                        objArr[i2] = columnsValue.getValue(columnsValue.columns[i2]);
                        if (objArr[i2] == null) {
                            objArr[i2] = FieldUtils.get(table.pmap.get(columnsValue.columns[i2]).field, obj);
                        }
                    }
                }
                size = i;
                objArr2 = objArr;
            } else if (Checker.isEmpty(table.pmap)) {
                objArr2 = z ? new Object[1] : null;
                size = 1;
            } else {
                if (z) {
                    size = table.pmap.size() + 1;
                    objArr2 = new Object[size];
                } else {
                    size = 1;
                }
                int i3 = 0;
                for (Map.Entry<String, Property> entry : table.pmap.entrySet()) {
                    if (i3 > 0) {
                        sb.append(",");
                    }
                    sb.append(entry.getKey());
                    sb.append("=?");
                    if (z) {
                        objArr2[i3] = FieldUtils.get(entry.getValue().field, obj);
                    }
                    i3++;
                }
            }
            if (z) {
                objArr2[size - 1] = FieldUtils.getAssignedKeyObject(table.key, obj);
            }
            sb.append(" WHERE ");
            sb.append(table.key.column);
            sb.append("=?");
            sQLStatement.sql = sb.toString();
            sQLStatement.bindArgs = objArr2;
            JuOrmLog.d(TAG, "buildUpdateSql() sql = ", sQLStatement.sql, " args = ", Arrays.toString(sQLStatement.bindArgs));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return sQLStatement;
    }

    public static SQLStatement buildMappingDeleteSql(String str, Object obj, EntityTable entityTable) {
        if (str == null) {
            return null;
        }
        SQLStatement sQLStatement = new SQLStatement();
        sQLStatement.sql = a.a(a.b("DELETE FROM ", str, " WHERE "), entityTable.name, "=?");
        sQLStatement.bindArgs = new Object[]{obj};
        return sQLStatement;
    }

    public static SQLStatement buildMappingToOneSql(String str, Object obj, Object obj2, EntityTable entityTable, EntityTable entityTable2) {
        if (obj2 == null) {
            return null;
        }
        StringBuilder sbA = a.a(128, INSERT, INTO, str, "(");
        sbA.append(entityTable.name);
        sbA.append(",");
        sbA.append(entityTable2.name);
        sbA.append(")");
        sbA.append(VALUES);
        sbA.append(TWO_HOLDER);
        SQLStatement sQLStatement = new SQLStatement();
        sQLStatement.sql = sbA.toString();
        sQLStatement.bindArgs = new Object[]{obj, obj2};
        return sQLStatement;
    }

    public static SQLStatement buildDeleteSql(Collection<?> collection) {
        SQLStatement sQLStatement = new SQLStatement();
        try {
            StringBuilder sb = new StringBuilder(256);
            EntityTable table = null;
            Object[] objArr = new Object[collection.size()];
            int i = 0;
            for (Object obj : collection) {
                if (i == 0) {
                    table = TableManager.getTable(obj);
                    sb.append("DELETE FROM ");
                    sb.append(table.name);
                    sb.append(" WHERE ");
                    sb.append(table.key.column);
                    sb.append(" IN ");
                    sb.append("(");
                    sb.append("?");
                } else {
                    sb.append(",?");
                }
                Object obj2 = FieldUtils.get(table.key.field, obj);
                objArr[i] = obj2;
                CacheManager.removeFromCache(obj2, obj.getClass());
                i++;
            }
            sb.append(")");
            sQLStatement.sql = sb.toString();
            sQLStatement.bindArgs = objArr;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return sQLStatement;
    }

    public static SQLStatement buildQueryRelationSql(EntityTable entityTable, EntityTable entityTable2, Object obj) {
        SQLStatement sQLStatement = new SQLStatement();
        StringBuilder sbA = a.a(SELECT_ANY_FROM);
        sbA.append(TableManager.getMapTableName(entityTable, entityTable2));
        sbA.append(" WHERE ");
        sQLStatement.sql = a.a(sbA, entityTable.name, "=?");
        sQLStatement.bindArgs = new String[]{String.valueOf(obj)};
        return sQLStatement;
    }

    public static SQLStatement buildDeleteSql(Class<?> cls, long j, long j2, String str) {
        SQLStatement sQLStatement = new SQLStatement();
        EntityTable table = TableManager.getTable(cls);
        String str2 = table.key.column;
        if (Checker.isEmpty(str)) {
            str = str2;
        }
        StringBuilder sbA = a.a("DELETE FROM ");
        a.a(sbA, table.name, " WHERE ", str2, " IN ");
        a.a(sbA, "(", "SELECT ", str2, " FROM ");
        a.a(sbA, table.name, " ORDER BY ", str, ASC);
        sbA.append(" LIMIT ");
        sbA.append(j);
        sbA.append(",");
        sbA.append(j2);
        sbA.append(")");
        sQLStatement.sql = sbA.toString();
        return sQLStatement;
    }

    public static SQLStatement buildUpdateSql(WhereBuilder whereBuilder, ColumnsValue columnsValue, ConflictAlgorithm conflictAlgorithm) {
        Object[] whereArgs;
        SQLStatement sQLStatement = new SQLStatement();
        try {
            EntityTable table = TableManager.getTable((Class<?>) whereBuilder.getTableClass());
            StringBuilder sb = new StringBuilder(128);
            sb.append(UPDATE);
            if (conflictAlgorithm != null) {
                sb.append(conflictAlgorithm.getAlgorithm());
            }
            sb.append(table.name);
            sb.append(SET);
            if (columnsValue != null && columnsValue.checkColumns()) {
                Object[] whereArgs2 = whereBuilder.getWhereArgs();
                if (whereArgs2 != null) {
                    whereArgs = new Object[columnsValue.columns.length + whereArgs2.length];
                } else {
                    whereArgs = new Object[columnsValue.columns.length];
                }
                int i = 0;
                int i2 = 0;
                while (i2 < columnsValue.columns.length) {
                    if (i2 > 0) {
                        sb.append(",");
                    }
                    sb.append(columnsValue.columns[i2]);
                    sb.append("=?");
                    whereArgs[i2] = columnsValue.getValue(columnsValue.columns[i2]);
                    i2++;
                }
                if (whereArgs2 != null) {
                    int length = whereArgs2.length;
                    while (i < length) {
                        whereArgs[i2] = whereArgs2[i];
                        i++;
                        i2++;
                    }
                }
            } else {
                whereArgs = whereBuilder.getWhereArgs();
            }
            sb.append(whereBuilder.createWhereString());
            sQLStatement.sql = sb.toString();
            sQLStatement.bindArgs = whereArgs;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return sQLStatement;
    }
}
