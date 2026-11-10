package com.regza.cesi;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: CesiConnection.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Error {

    @Nullable
    private final Long detailed;

    @Nullable
    private final Long messageID;

    @Nullable
    private final Long messageType;

    @Nullable
    private final Long status;

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Error)) {
            return false;
        }
        Error error = (Error) obj;
        return Intrinsics.areEqual(this.messageID, error.messageID) && Intrinsics.areEqual(this.messageType, error.messageType) && Intrinsics.areEqual(this.status, error.status) && Intrinsics.areEqual(this.detailed, error.detailed);
    }

    public int hashCode() {
        Long l = this.messageID;
        int iHashCode = (l != null ? l.hashCode() : 0) * 31;
        Long l2 = this.messageType;
        int iHashCode2 = (iHashCode + (l2 != null ? l2.hashCode() : 0)) * 31;
        Long l3 = this.status;
        int iHashCode3 = (iHashCode2 + (l3 != null ? l3.hashCode() : 0)) * 31;
        Long l4 = this.detailed;
        return iHashCode3 + (l4 != null ? l4.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "Error(messageID=" + this.messageID + ", messageType=" + this.messageType + ", status=" + this.status + ", detailed=" + this.detailed + ")";
    }

    public Error(@Nullable Long l, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4) {
        this.messageID = l;
        this.messageType = l2;
        this.status = l3;
        this.detailed = l4;
    }
}
