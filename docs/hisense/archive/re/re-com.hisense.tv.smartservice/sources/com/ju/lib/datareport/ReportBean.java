package com.ju.lib.datareport;

import b.a.a.a.a;
import com.ju.lib.datalayer.database.annotation.Column;
import com.ju.lib.datalayer.database.annotation.NotNull;
import com.ju.lib.datalayer.database.annotation.PrimaryKey;
import com.ju.lib.datalayer.database.annotation.Table;
import com.ju.lib.datalayer.database.enums.AssignType;

/* JADX INFO: loaded from: classes2.dex */
@Table("report")
public class ReportBean {

    @NotNull
    @Column("app_key")
    public String app_key;

    @NotNull
    @Column("data")
    public String data;

    @NotNull
    @Column(Columns.EVENT_CODE)
    public String event_code;

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("id")
    public long id;

    @NotNull
    @Column("time")
    public long time;

    @NotNull
    @Column("type")
    public int type;

    public static class Columns {
        public static final String APP_KEY = "app_key";
        public static final String DATA = "data";
        public static final String EVENT_CODE = "event_code";
        public static final String ID = "id";
        public static final String TIME = "time";
        public static final String TYPE = "type";
    }

    public ReportBean() {
    }

    public String getAppKey() {
        return this.app_key;
    }

    public String getData() {
        return this.data;
    }

    public String getEventCode() {
        return this.event_code;
    }

    public long getId() {
        return this.id;
    }

    public long getTime() {
        return this.time;
    }

    public int getType() {
        return this.type;
    }

    public void setAppKey(String str) {
        this.app_key = str;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setEventCode(String str) {
        this.event_code = str;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        StringBuilder sbA = a.a("ReportBean: id = ");
        sbA.append(this.id);
        sbA.append(", app_key = ");
        sbA.append(this.app_key);
        sbA.append(", type = ");
        sbA.append(this.type);
        sbA.append(", ");
        sbA.append("event_code = ");
        sbA.append(this.event_code);
        sbA.append(", time = ");
        sbA.append(this.time);
        sbA.append(", data = ");
        sbA.append(this.data);
        return sbA.toString();
    }

    public ReportBean(String str, int i, String str2, String str3, long j) {
        this.app_key = str;
        this.type = i;
        this.event_code = str2;
        this.data = str3;
        this.time = j;
    }
}
