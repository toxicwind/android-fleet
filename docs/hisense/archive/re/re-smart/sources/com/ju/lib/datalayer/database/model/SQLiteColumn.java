package com.ju.lib.datalayer.database.model;

import b.a.a.a.a;
import com.ju.lib.datalayer.database.annotation.Column;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class SQLiteColumn implements Serializable {
    public static final long serialVersionUID = 3822696110072260807L;

    @Column("cid")
    public long cid;

    @Column("dflt_value")
    public String dflt_value;

    @Column("name")
    public String name;

    @Column("notnull")
    public short notnull;

    @Column("pk")
    public short pk;

    @Column("type")
    public String type;

    public String toString() {
        StringBuilder sbA = a.a("Column [cid=");
        sbA.append(this.cid);
        sbA.append(", name=");
        sbA.append(this.name);
        sbA.append(", type=");
        sbA.append(this.type);
        sbA.append(", notnull=");
        sbA.append((int) this.notnull);
        sbA.append(", dflt_value=");
        sbA.append(this.dflt_value);
        sbA.append(", pk=");
        return a.a(sbA, this.pk, "]");
    }
}
