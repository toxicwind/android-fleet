package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class FieldAttributes {
    public FieldAttributes(Field field) {
        C$Gson$Preconditions.checkNotNull(field);
    }
}
