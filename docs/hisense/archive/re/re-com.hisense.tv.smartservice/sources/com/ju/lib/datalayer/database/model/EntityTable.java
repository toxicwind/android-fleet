package com.ju.lib.datalayer.database.model;

import b.a.a.a.a;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class EntityTable implements Serializable {
    public static final long serialVersionUID = -3066807097025684080L;
    public Class claxx;
    public PrimaryKey key;
    public ArrayList<MapProperty> mappingList;
    public String name;
    public LinkedHashMap<String, Property> pmap;

    public void addMapping(MapProperty mapProperty) {
        if (this.mappingList == null) {
            this.mappingList = new ArrayList<>();
        }
        this.mappingList.add(mapProperty);
    }

    public Annotation getAnnotation(Class cls) {
        Class cls2 = this.claxx;
        if (cls2 != null) {
            return cls2.getAnnotation(cls);
        }
        return null;
    }

    public String toString() {
        StringBuilder sbA = a.a("EntityTable{claxx=");
        sbA.append(this.claxx);
        sbA.append(", name='");
        a.a(sbA, this.name, '\'', ", key=");
        sbA.append(this.key);
        sbA.append(", pmap=");
        sbA.append(this.pmap);
        sbA.append(", mappingList=");
        return a.a(sbA, (Object) this.mappingList, '}');
    }
}
