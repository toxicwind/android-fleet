package com.ju.lib.datalayer.database.model;

import com.ju.lib.datalayer.database.enums.Relation;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class MapProperty extends Property {
    public static final String PRIMARYKEY = " PRIMARY KEY ";
    public Relation relation;

    public MapProperty(Property property, Relation relation) {
        super(property.column, property.field);
        this.relation = relation;
    }

    public boolean isToMany() {
        Relation relation = this.relation;
        return relation == Relation.ManyToMany || relation == Relation.OneToMany;
    }

    public boolean isToOne() {
        Relation relation = this.relation;
        return relation == Relation.ManyToOne || relation == Relation.OneToOne;
    }

    public MapProperty(String str, Field field, Relation relation) {
        super(str, field);
        this.relation = relation;
    }
}
