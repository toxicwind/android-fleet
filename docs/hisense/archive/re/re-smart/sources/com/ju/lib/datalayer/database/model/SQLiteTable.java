package com.ju.lib.datalayer.database.model;

import b.a.a.a.a;
import com.ju.lib.datalayer.database.annotation.Column;
import java.io.Serializable;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class SQLiteTable implements Serializable {
    public static final long serialVersionUID = -4487224138248514206L;
    public HashMap<String, Integer> columns;
    public boolean isTableChecked;

    @Column("name")
    public String name;

    @Column("rootpage")
    public long rootpage;

    @Column("sql")
    public String sql;

    @Column("tbl_name")
    public String tbl_name;

    @Column("type")
    public String type;

    public String toString() {
        StringBuilder sbA = a.a("SQLiteTable{type='");
        a.a(sbA, this.type, '\'', ", name='");
        a.a(sbA, this.name, '\'', ", tbl_name='");
        a.a(sbA, this.tbl_name, '\'', ", rootpage=");
        sbA.append(this.rootpage);
        sbA.append(", sql='");
        a.a(sbA, this.sql, '\'', ", isTableChecked=");
        sbA.append(this.isTableChecked);
        sbA.append(", columns=");
        return a.a(sbA, (Object) this.columns, '}');
    }
}
