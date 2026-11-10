package com.ju.lib.datareport.dynamic;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicResponseResultType {
    public static final int FAILURE = 1;
    public static final int SUCESS = 0;
    public static final int SUCESS_NO_CHANGES = 2;
    public static final int SUCESS_UNIVERSAL = 3;
    public int type;

    public DynamicResponseResultType() {
        this.type = 1;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("DynamicResponseResultType{type=");
        sbA.append(this.type);
        sbA.append('}');
        return sbA.toString();
    }

    public DynamicResponseResultType(int i) {
        this.type = 1;
        this.type = i;
    }
}
