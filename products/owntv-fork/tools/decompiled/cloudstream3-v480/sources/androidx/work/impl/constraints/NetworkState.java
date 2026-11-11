package androidx.work.impl.constraints;

import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;

/* JADX INFO: compiled from: NetworkState.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J;\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\n¨\u0006\u0017"}, d2 = {"Landroidx/work/impl/constraints/NetworkState;", "", "isConnected", "", "isValidated", "isMetered", "isNotRoaming", "isBlocked", "<init>", "(ZZZZZ)V", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class NetworkState {
    private final boolean isBlocked;
    private final boolean isConnected;
    private final boolean isMetered;
    private final boolean isNotRoaming;
    private final boolean isValidated;

    public static /* synthetic */ NetworkState copy$default(NetworkState networkState, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, Object obj) {
        if ((i & 1) != 0) {
            z = networkState.isConnected;
        }
        if ((i & 2) != 0) {
            z2 = networkState.isValidated;
        }
        if ((i & 4) != 0) {
            z3 = networkState.isMetered;
        }
        if ((i & 8) != 0) {
            z4 = networkState.isNotRoaming;
        }
        if ((i & 16) != 0) {
            z5 = networkState.isBlocked;
        }
        boolean z6 = z5;
        boolean z7 = z3;
        return networkState.copy(z, z2, z7, z4, z6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsConnected() {
        return this.isConnected;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsValidated() {
        return this.isValidated;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsMetered() {
        return this.isMetered;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsNotRoaming() {
        return this.isNotRoaming;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsBlocked() {
        return this.isBlocked;
    }

    public final NetworkState copy(boolean isConnected, boolean isValidated, boolean isMetered, boolean isNotRoaming, boolean isBlocked) {
        return new NetworkState(isConnected, isValidated, isMetered, isNotRoaming, isBlocked);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NetworkState)) {
            return false;
        }
        NetworkState networkState = (NetworkState) other;
        return this.isConnected == networkState.isConnected && this.isValidated == networkState.isValidated && this.isMetered == networkState.isMetered && this.isNotRoaming == networkState.isNotRoaming && this.isBlocked == networkState.isBlocked;
    }

    public int hashCode() {
        return (((((((UByte$$ExternalSyntheticBackport0.m(this.isConnected) * 31) + UByte$$ExternalSyntheticBackport0.m(this.isValidated)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.isMetered)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.isNotRoaming)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.isBlocked);
    }

    public String toString() {
        return "NetworkState(isConnected=" + this.isConnected + ", isValidated=" + this.isValidated + ", isMetered=" + this.isMetered + ", isNotRoaming=" + this.isNotRoaming + ", isBlocked=" + this.isBlocked + ')';
    }

    public NetworkState(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.isConnected = z;
        this.isValidated = z2;
        this.isMetered = z3;
        this.isNotRoaming = z4;
        this.isBlocked = z5;
    }

    public final boolean isConnected() {
        return this.isConnected;
    }

    public final boolean isValidated() {
        return this.isValidated;
    }

    public final boolean isMetered() {
        return this.isMetered;
    }

    public final boolean isNotRoaming() {
        return this.isNotRoaming;
    }

    public final boolean isBlocked() {
        return this.isBlocked;
    }
}
