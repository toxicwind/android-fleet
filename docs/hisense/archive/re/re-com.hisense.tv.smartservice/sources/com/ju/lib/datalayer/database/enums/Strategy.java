package com.ju.lib.datalayer.database.enums;

/* JADX INFO: loaded from: classes2.dex */
public enum Strategy {
    ROLLBACK(" ROLLBACK "),
    ABORT(" ABORT "),
    FAIL(" FAIL "),
    IGNORE(" IGNORE "),
    REPLACE(" REPLACE ");

    public String sql;

    Strategy(String str) {
        this.sql = str;
    }

    public String getSql() {
        return this.sql;
    }
}
