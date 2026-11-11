package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyncViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0012JB\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/CurrentSynced;", "", "name", "", "idPrefix", "isSynced", "", "hasAccount", "icon", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/Integer;)V", "getName", "()Ljava/lang/String;", "getIdPrefix", "()Z", "getHasAccount", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/Integer;)Lcom/lagradost/cloudstream3/ui/result/CurrentSynced;", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CurrentSynced {
    private final boolean hasAccount;
    private final Integer icon;
    private final String idPrefix;
    private final boolean isSynced;
    private final String name;

    public static /* synthetic */ CurrentSynced copy$default(CurrentSynced currentSynced, String str, String str2, boolean z, boolean z2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = currentSynced.name;
        }
        if ((i & 2) != 0) {
            str2 = currentSynced.idPrefix;
        }
        if ((i & 4) != 0) {
            z = currentSynced.isSynced;
        }
        if ((i & 8) != 0) {
            z2 = currentSynced.hasAccount;
        }
        if ((i & 16) != 0) {
            num = currentSynced.icon;
        }
        Integer num2 = num;
        boolean z3 = z;
        return currentSynced.copy(str, str2, z3, z2, num2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getIdPrefix() {
        return this.idPrefix;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsSynced() {
        return this.isSynced;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getHasAccount() {
        return this.hasAccount;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getIcon() {
        return this.icon;
    }

    public final CurrentSynced copy(String name, String idPrefix, boolean isSynced, boolean hasAccount, Integer icon) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(idPrefix, "idPrefix");
        return new CurrentSynced(name, idPrefix, isSynced, hasAccount, icon);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CurrentSynced)) {
            return false;
        }
        CurrentSynced currentSynced = (CurrentSynced) other;
        return Intrinsics.areEqual(this.name, currentSynced.name) && Intrinsics.areEqual(this.idPrefix, currentSynced.idPrefix) && this.isSynced == currentSynced.isSynced && this.hasAccount == currentSynced.hasAccount && Intrinsics.areEqual(this.icon, currentSynced.icon);
    }

    public int hashCode() {
        int iHashCode = ((((((this.name.hashCode() * 31) + this.idPrefix.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isSynced)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.hasAccount)) * 31;
        Integer num = this.icon;
        return iHashCode + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "CurrentSynced(name=" + this.name + ", idPrefix=" + this.idPrefix + ", isSynced=" + this.isSynced + ", hasAccount=" + this.hasAccount + ", icon=" + this.icon + ')';
    }

    public CurrentSynced(String name, String idPrefix, boolean z, boolean z2, Integer num) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(idPrefix, "idPrefix");
        this.name = name;
        this.idPrefix = idPrefix;
        this.isSynced = z;
        this.hasAccount = z2;
        this.icon = num;
    }

    public final String getName() {
        return this.name;
    }

    public final String getIdPrefix() {
        return this.idPrefix;
    }

    public final boolean isSynced() {
        return this.isSynced;
    }

    public final boolean getHasAccount() {
        return this.hasAccount;
    }

    public final Integer getIcon() {
        return this.icon;
    }
}
