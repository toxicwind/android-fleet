package com.ju.lib.datalayer.database.model;

import com.ju.lib.datalayer.database.enums.AssignType;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class PrimaryKey extends Property {
    public AssignType assign;

    public PrimaryKey(Property property, AssignType assignType) {
        super(property.column, property.field, property.classType);
        this.assign = assignType;
    }

    public boolean isAssignedByMyself() {
        return this.assign == AssignType.BY_MYSELF;
    }

    public boolean isAssignedBySystem() {
        return this.assign == AssignType.AUTO_INCREMENT;
    }

    public PrimaryKey(String str, Field field, int i, AssignType assignType) {
        super(str, field, i);
        this.assign = assignType;
    }
}
