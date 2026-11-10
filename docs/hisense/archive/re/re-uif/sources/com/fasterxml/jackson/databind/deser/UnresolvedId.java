package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.util.ClassUtil;

/* JADX INFO: loaded from: classes.dex */
public class UnresolvedId {
    private final Object _id;
    private final JsonLocation _location;
    private final Class<?> _type;

    public String toString() {
        return String.format("Object id [%s] (for %s) at %s", this._id, ClassUtil.nameOf(this._type), this._location);
    }
}
