package com.ju.lib.datalayer.database.asist;

import b.a.a.a.a;
import com.ju.lib.datalayer.database.table.TableManager;

/* JADX INFO: loaded from: classes2.dex */
public class WhereBuilder {
    public static final String AND = " AND ";
    public static final String COMMA_HOLDER = ",?";
    public static final String DELETE = "DELETE FROM ";
    public static final String EQUAL_HOLDER = "=?";
    public static final String GREATER_THAN_HOLDER = ">?";
    public static final String HOLDER = "?";
    public static final String IN = " IN ";
    public static final String LESS_THAN_HOLDER = "<?";
    public static final String NOT = " NOT ";
    public static final String NOTHING = "";
    public static final String NOT_EQUAL_HOLDER = "!=?";
    public static final String OR = " OR ";
    public static final String PARENTHESES_LEFT = "(";
    public static final String PARENTHESES_RIGHT = ")";
    public static final String WHERE = " WHERE ";
    public Class tableClass;
    public String where;
    public Object[] whereArgs;

    public WhereBuilder(Class cls) {
        this.tableClass = cls;
    }

    private String buildWhereIn(String str, int i) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(" IN ");
        sb.append("(");
        sb.append("?");
        for (int i2 = 1; i2 < i; i2++) {
            sb.append(",?");
        }
        sb.append(")");
        return sb.toString();
    }

    private String buildWhereNotIn(String str, int i) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(" NOT ");
        sb.append(" IN ");
        sb.append("(");
        sb.append("?");
        for (int i2 = 1; i2 < i; i2++) {
            sb.append(",?");
        }
        sb.append(")");
        return sb.toString();
    }

    public static WhereBuilder create(Class cls) {
        return new WhereBuilder(cls);
    }

    public WhereBuilder and(String str, Object... objArr) {
        return append(" AND ", str, objArr);
    }

    public WhereBuilder andEquals(String str, Object obj) {
        return append(" AND ", a.a(str, "=?"), obj);
    }

    public WhereBuilder andIn(String str, Object... objArr) {
        return append(" AND ", buildWhereIn(str, objArr.length), objArr);
    }

    public WhereBuilder append(String str, String str2, Object... objArr) {
        if (this.where == null) {
            this.where = str2;
            this.whereArgs = objArr;
        } else {
            if (str != null) {
                this.where = a.a(new StringBuilder(), this.where, str);
            }
            this.where = a.a(new StringBuilder(), this.where, str2);
            Object[] objArr2 = this.whereArgs;
            if (objArr2 == null) {
                this.whereArgs = objArr;
            } else {
                Object[] objArr3 = new Object[objArr2.length + objArr.length];
                System.arraycopy(objArr2, 0, objArr3, 0, objArr2.length);
                System.arraycopy(objArr, 0, objArr3, this.whereArgs.length, objArr.length);
                this.whereArgs = objArr3;
            }
        }
        return this;
    }

    public SQLStatement createStatementDelete() {
        SQLStatement sQLStatement = new SQLStatement();
        StringBuilder sbA = a.a("DELETE FROM ");
        sbA.append(TableManager.getTableName(this.tableClass));
        sbA.append(createWhereString());
        sQLStatement.sql = sbA.toString();
        sQLStatement.bindArgs = transToStringArray();
        return sQLStatement;
    }

    public String createWhereString() {
        if (this.where == null) {
            return "";
        }
        StringBuilder sbA = a.a(" WHERE ");
        sbA.append(this.where);
        return sbA.toString();
    }

    public WhereBuilder equals(String str, Object obj) {
        return append(null, a.a(str, "=?"), obj);
    }

    public Class getTableClass() {
        return this.tableClass;
    }

    public String getWhere() {
        return this.where;
    }

    public Object[] getWhereArgs() {
        return this.whereArgs;
    }

    public WhereBuilder greaterThan(String str, Object obj) {
        return append(null, a.a(str, GREATER_THAN_HOLDER), obj);
    }

    public WhereBuilder in(String str, Object... objArr) {
        return append(null, buildWhereIn(str, objArr.length), objArr);
    }

    public WhereBuilder lessThan(String str, Object obj) {
        return append(null, a.a(str, LESS_THAN_HOLDER), obj);
    }

    public WhereBuilder noEquals(String str, Object obj) {
        return append(null, a.a(str, NOT_EQUAL_HOLDER), obj);
    }

    public WhereBuilder not() {
        if (this.where != null) {
            this.where = a.a(new StringBuilder(), this.where, " NOT ");
        }
        return this;
    }

    public WhereBuilder notIn(String str, Object... objArr) {
        return append(null, buildWhereNotIn(str, objArr.length), objArr);
    }

    public WhereBuilder or(String str, Object... objArr) {
        return append(" OR ", str, objArr);
    }

    public WhereBuilder orEquals(String str, Object obj) {
        return append(" OR ", a.a(str, "=?"), obj);
    }

    public WhereBuilder orIn(String str, Object... objArr) {
        return append(" OR ", buildWhereIn(str, objArr.length), objArr);
    }

    public void setWhere(String str) {
        this.where = str;
    }

    public void setWhereArgs(Object[] objArr) {
        this.whereArgs = objArr;
    }

    public String[] transToStringArray() {
        Object[] objArr = this.whereArgs;
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        if (objArr instanceof String[]) {
            return (String[]) objArr;
        }
        String[] strArr = new String[objArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = String.valueOf(this.whereArgs[i]);
        }
        return strArr;
    }

    public WhereBuilder where(String str, Object... objArr) {
        this.where = str;
        this.whereArgs = objArr;
        return this;
    }

    public static WhereBuilder create(Class cls, String str, Object[] objArr) {
        return new WhereBuilder(cls, str, objArr);
    }

    public WhereBuilder and() {
        if (this.where != null) {
            this.where = a.a(new StringBuilder(), this.where, " AND ");
        }
        return this;
    }

    public WhereBuilder or() {
        if (this.where != null) {
            this.where = a.a(new StringBuilder(), this.where, " OR ");
        }
        return this;
    }

    public WhereBuilder(Class cls, String str, Object[] objArr) {
        this.where = str;
        this.whereArgs = objArr;
        this.tableClass = cls;
    }
}
