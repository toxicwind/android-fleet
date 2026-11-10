package com.ju.lib.datareport;

import b.a.a.a.a;
import com.ju.lib.datalayer.database.annotation.Column;
import com.ju.lib.datalayer.database.annotation.Conflict;
import com.ju.lib.datalayer.database.annotation.NotNull;
import com.ju.lib.datalayer.database.annotation.PrimaryKey;
import com.ju.lib.datalayer.database.annotation.Table;
import com.ju.lib.datalayer.database.enums.AssignType;

/* JADX INFO: loaded from: classes2.dex */
@Table("strategy")
public class StrategyBean {

    @PrimaryKey(AssignType.BY_MYSELF)
    @Conflict(com.ju.lib.datalayer.database.enums.Strategy.REPLACE)
    @Column("app_key")
    public String app_key;

    @NotNull
    @Column("content")
    public byte[] content;

    @NotNull
    @Column(Columns.POLICY)
    public long policy;

    public static class Columns {
        public static final String APP_KEY = "app_key";
        public static final String CONTENT = "content";
        public static final String POLICY = "policy";
    }

    public StrategyBean() {
    }

    public String getAppKey() {
        return this.app_key;
    }

    public byte[] getContent() {
        return this.content;
    }

    public long getPolicy() {
        return this.policy;
    }

    public void setAppKey(String str) {
        this.app_key = str;
    }

    public void setContent(byte[] bArr) {
        this.content = bArr;
    }

    public void setPolicy(int i) {
        this.policy = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("StrategyBean: ");
        sbA.append(this.app_key);
        sbA.append(", ");
        sbA.append(this.policy);
        return sbA.toString();
    }

    public StrategyBean(String str, byte[] bArr, long j) {
        this.app_key = str;
        this.content = bArr;
        this.policy = j;
    }
}
