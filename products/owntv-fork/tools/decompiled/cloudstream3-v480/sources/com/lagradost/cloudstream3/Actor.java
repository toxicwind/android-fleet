package com.lagradost.cloudstream3;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/Actor;", "", "name", "", "image", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getImage", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Actor {
    private final String image;
    private final String name;

    public static /* synthetic */ Actor copy$default(Actor actor, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = actor.name;
        }
        if ((i & 2) != 0) {
            str2 = actor.image;
        }
        return actor.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    public final Actor copy(String name, String image) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new Actor(name, image);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Actor)) {
            return false;
        }
        Actor actor = (Actor) other;
        return Intrinsics.areEqual(this.name, actor.name) && Intrinsics.areEqual(this.image, actor.image);
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        String str = this.image;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "Actor(name=" + this.name + ", image=" + this.image + ')';
    }

    public Actor(String name, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.image = str;
    }

    public /* synthetic */ Actor(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2);
    }

    public final String getName() {
        return this.name;
    }

    public final String getImage() {
        return this.image;
    }
}
