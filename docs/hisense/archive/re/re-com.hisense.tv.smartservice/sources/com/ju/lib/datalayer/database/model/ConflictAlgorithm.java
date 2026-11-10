package com.ju.lib.datalayer.database.model;

/* JADX INFO: loaded from: classes2.dex */
public enum ConflictAlgorithm {
    None(" "),
    Rollback(" OR ROLLBACK "),
    Abort(" OR ABORT "),
    Fail(" OR FAIL "),
    Ignore(" OR IGNORE "),
    Replace(" OR REPLACE ");

    public String algorithm;

    ConflictAlgorithm(String str) {
        this.algorithm = str;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }
}
