package com.lagradost.cloudstream3.ui.result;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\rJ\u0017\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007HÆ\u0003J<\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/CheckDuplicateData;", "", "name", "", "year", "", "syncData", "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;)V", "getName", "()Ljava/lang/String;", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSyncData", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;)Lcom/lagradost/cloudstream3/ui/result/CheckDuplicateData;", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CheckDuplicateData {
    private final String name;
    private final Map<String, String> syncData;
    private final Integer year;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CheckDuplicateData copy$default(CheckDuplicateData checkDuplicateData, String str, Integer num, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = checkDuplicateData.name;
        }
        if ((i & 2) != 0) {
            num = checkDuplicateData.year;
        }
        if ((i & 4) != 0) {
            map = checkDuplicateData.syncData;
        }
        return checkDuplicateData.copy(str, num, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getYear() {
        return this.year;
    }

    public final Map<String, String> component3() {
        return this.syncData;
    }

    public final CheckDuplicateData copy(String name, Integer year, Map<String, String> syncData) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new CheckDuplicateData(name, year, syncData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CheckDuplicateData)) {
            return false;
        }
        CheckDuplicateData checkDuplicateData = (CheckDuplicateData) other;
        return Intrinsics.areEqual(this.name, checkDuplicateData.name) && Intrinsics.areEqual(this.year, checkDuplicateData.year) && Intrinsics.areEqual(this.syncData, checkDuplicateData.syncData);
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        Integer num = this.year;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Map<String, String> map = this.syncData;
        return iHashCode2 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "CheckDuplicateData(name=" + this.name + ", year=" + this.year + ", syncData=" + this.syncData + ')';
    }

    public CheckDuplicateData(String name, Integer num, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.year = num;
        this.syncData = map;
    }

    public final String getName() {
        return this.name;
    }

    public final Integer getYear() {
        return this.year;
    }

    public final Map<String, String> getSyncData() {
        return this.syncData;
    }
}
