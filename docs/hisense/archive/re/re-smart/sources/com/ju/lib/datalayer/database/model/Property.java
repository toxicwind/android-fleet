package com.ju.lib.datalayer.database.model;

import b.a.a.a.a;
import com.ju.lib.datalayer.database.utils.DataUtils;
import java.io.Serializable;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class Property implements Serializable {
    public int classType;
    public String column;
    public Field field;

    public Property(String str, Field field) {
        this.classType = 0;
        this.column = str;
        this.field = field;
        if (this.classType <= 0) {
            this.classType = DataUtils.getFieldClassType(field);
        }
    }

    public String toString() {
        StringBuilder sbA = a.a("Property{column='");
        a.a(sbA, this.column, '\'', ", field=");
        sbA.append(this.field);
        sbA.append(", classType=");
        sbA.append(this.classType);
        sbA.append('}');
        return sbA.toString();
    }

    public Property(String str, Field field, int i) {
        this.classType = 0;
        this.column = str;
        this.field = field;
        if (i <= 0) {
            this.classType = DataUtils.getFieldClassType(field);
        }
        this.classType = i;
    }
}
