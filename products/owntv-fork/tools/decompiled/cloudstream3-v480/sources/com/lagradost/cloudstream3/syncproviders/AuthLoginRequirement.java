package com.lagradost.cloudstream3.syncproviders;

import androidx.autofill.HintConstants;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AuthAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "", HintConstants.AUTOFILL_HINT_PASSWORD, "", HintConstants.AUTOFILL_HINT_USERNAME, "email", "server", "<init>", "(ZZZZ)V", "getPassword", "()Z", "getUsername", "getEmail", "getServer", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AuthLoginRequirement {
    private final boolean email;
    private final boolean password;
    private final boolean server;
    private final boolean username;

    public AuthLoginRequirement() {
        this(false, false, false, false, 15, null);
    }

    public static /* synthetic */ AuthLoginRequirement copy$default(AuthLoginRequirement authLoginRequirement, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 1) != 0) {
            z = authLoginRequirement.password;
        }
        if ((i & 2) != 0) {
            z2 = authLoginRequirement.username;
        }
        if ((i & 4) != 0) {
            z3 = authLoginRequirement.email;
        }
        if ((i & 8) != 0) {
            z4 = authLoginRequirement.server;
        }
        return authLoginRequirement.copy(z, z2, z3, z4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getPassword() {
        return this.password;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getEmail() {
        return this.email;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getServer() {
        return this.server;
    }

    public final AuthLoginRequirement copy(boolean password, boolean username, boolean email, boolean server) {
        return new AuthLoginRequirement(password, username, email, server);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthLoginRequirement)) {
            return false;
        }
        AuthLoginRequirement authLoginRequirement = (AuthLoginRequirement) other;
        return this.password == authLoginRequirement.password && this.username == authLoginRequirement.username && this.email == authLoginRequirement.email && this.server == authLoginRequirement.server;
    }

    public int hashCode() {
        return (((((NextAiring$$ExternalSyntheticBackport0.m(this.password) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.username)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.email)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.server);
    }

    public String toString() {
        return "AuthLoginRequirement(password=" + this.password + ", username=" + this.username + ", email=" + this.email + ", server=" + this.server + ')';
    }

    public AuthLoginRequirement(boolean z, boolean z2, boolean z3, boolean z4) {
        this.password = z;
        this.username = z2;
        this.email = z3;
        this.server = z4;
    }

    public /* synthetic */ AuthLoginRequirement(boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4);
    }

    public final boolean getPassword() {
        return this.password;
    }

    public final boolean getUsername() {
        return this.username;
    }

    public final boolean getEmail() {
        return this.email;
    }

    public final boolean getServer() {
        return this.server;
    }
}
