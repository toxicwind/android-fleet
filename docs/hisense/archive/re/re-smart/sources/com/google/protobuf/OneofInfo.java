package com.google.protobuf;

/* JADX INFO: loaded from: classes2.dex */
public final class OneofInfo {
    public final java.lang.reflect.Field caseField;
    public final int id;
    public final java.lang.reflect.Field valueField;

    public OneofInfo(int i, java.lang.reflect.Field field, java.lang.reflect.Field field2) {
        this.id = i;
        this.caseField = field;
        this.valueField = field2;
    }

    public java.lang.reflect.Field getCaseField() {
        return this.caseField;
    }

    public int getId() {
        return this.id;
    }

    public java.lang.reflect.Field getValueField() {
        return this.valueField;
    }
}
