package com.lagradost.cloudstream3;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J7\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/ActorData;", "", "actor", "Lcom/lagradost/cloudstream3/Actor;", "role", "Lcom/lagradost/cloudstream3/ActorRole;", "roleString", "", "voiceActor", "<init>", "(Lcom/lagradost/cloudstream3/Actor;Lcom/lagradost/cloudstream3/ActorRole;Ljava/lang/String;Lcom/lagradost/cloudstream3/Actor;)V", "getActor", "()Lcom/lagradost/cloudstream3/Actor;", "getRole", "()Lcom/lagradost/cloudstream3/ActorRole;", "getRoleString", "()Ljava/lang/String;", "getVoiceActor", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ActorData {
    private final Actor actor;
    private final ActorRole role;
    private final String roleString;
    private final Actor voiceActor;

    public static /* synthetic */ ActorData copy$default(ActorData actorData, Actor actor, ActorRole actorRole, String str, Actor actor2, int i, Object obj) {
        if ((i & 1) != 0) {
            actor = actorData.actor;
        }
        if ((i & 2) != 0) {
            actorRole = actorData.role;
        }
        if ((i & 4) != 0) {
            str = actorData.roleString;
        }
        if ((i & 8) != 0) {
            actor2 = actorData.voiceActor;
        }
        return actorData.copy(actor, actorRole, str, actor2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Actor getActor() {
        return this.actor;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ActorRole getRole() {
        return this.role;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRoleString() {
        return this.roleString;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Actor getVoiceActor() {
        return this.voiceActor;
    }

    public final ActorData copy(Actor actor, ActorRole role, String roleString, Actor voiceActor) {
        Intrinsics.checkNotNullParameter(actor, "actor");
        return new ActorData(actor, role, roleString, voiceActor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActorData)) {
            return false;
        }
        ActorData actorData = (ActorData) other;
        return Intrinsics.areEqual(this.actor, actorData.actor) && this.role == actorData.role && Intrinsics.areEqual(this.roleString, actorData.roleString) && Intrinsics.areEqual(this.voiceActor, actorData.voiceActor);
    }

    public int hashCode() {
        int iHashCode = this.actor.hashCode() * 31;
        ActorRole actorRole = this.role;
        int iHashCode2 = (iHashCode + (actorRole == null ? 0 : actorRole.hashCode())) * 31;
        String str = this.roleString;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Actor actor = this.voiceActor;
        return iHashCode3 + (actor != null ? actor.hashCode() : 0);
    }

    public String toString() {
        return "ActorData(actor=" + this.actor + ", role=" + this.role + ", roleString=" + this.roleString + ", voiceActor=" + this.voiceActor + ')';
    }

    public ActorData(Actor actor, ActorRole actorRole, String str, Actor actor2) {
        Intrinsics.checkNotNullParameter(actor, "actor");
        this.actor = actor;
        this.role = actorRole;
        this.roleString = str;
        this.voiceActor = actor2;
    }

    public /* synthetic */ ActorData(Actor actor, ActorRole actorRole, String str, Actor actor2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(actor, (i & 2) != 0 ? null : actorRole, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : actor2);
    }

    public final Actor getActor() {
        return this.actor;
    }

    public final ActorRole getRole() {
        return this.role;
    }

    public final String getRoleString() {
        return this.roleString;
    }

    public final Actor getVoiceActor() {
        return this.voiceActor;
    }
}
