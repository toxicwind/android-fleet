package com.lagradost.cloudstream3.extractors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByseSX.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\b\u0001\u0010\t\u001a\u00020\u0005\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003JY\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0003\u0010\t\u001a\u00020\u00052\b\b\u0003\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0014\u0010#\u001a\u00020\u000b2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020&HÖ\u0081\u0004J\n\u0010'\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/extractors/DetailsRoot;", "", "id", "", "code", "", "title", "posterUrl", "description", "createdAt", "ownerPrivate", "", "embedFrameUrl", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getId", "()J", "getCode", "()Ljava/lang/String;", "getTitle", "getPosterUrl", "getDescription", "getCreatedAt", "getOwnerPrivate", "()Z", "getEmbedFrameUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class DetailsRoot {
    private final String code;

    @JsonProperty("created_at")
    private final String createdAt;
    private final String description;

    @JsonProperty("embed_frame_url")
    private final String embedFrameUrl;
    private final long id;

    @JsonProperty("owner_private")
    private final boolean ownerPrivate;

    @JsonProperty("poster_url")
    private final String posterUrl;
    private final String title;

    public static /* synthetic */ DetailsRoot copy$default(DetailsRoot detailsRoot, long j, String str, String str2, String str3, String str4, String str5, boolean z, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            j = detailsRoot.id;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            str = detailsRoot.code;
        }
        String str7 = str;
        if ((i & 4) != 0) {
            str2 = detailsRoot.title;
        }
        String str8 = str2;
        if ((i & 8) != 0) {
            str3 = detailsRoot.posterUrl;
        }
        return detailsRoot.copy(j2, str7, str8, str3, (i & 16) != 0 ? detailsRoot.description : str4, (i & 32) != 0 ? detailsRoot.createdAt : str5, (i & 64) != 0 ? detailsRoot.ownerPrivate : z, (i & 128) != 0 ? detailsRoot.embedFrameUrl : str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPosterUrl() {
        return this.posterUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getOwnerPrivate() {
        return this.ownerPrivate;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getEmbedFrameUrl() {
        return this.embedFrameUrl;
    }

    public final DetailsRoot copy(long id, String code, String title, @JsonProperty("poster_url") String posterUrl, String description, @JsonProperty("created_at") String createdAt, @JsonProperty("owner_private") boolean ownerPrivate, @JsonProperty("embed_frame_url") String embedFrameUrl) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(posterUrl, "posterUrl");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(embedFrameUrl, "embedFrameUrl");
        return new DetailsRoot(id, code, title, posterUrl, description, createdAt, ownerPrivate, embedFrameUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DetailsRoot)) {
            return false;
        }
        DetailsRoot detailsRoot = (DetailsRoot) other;
        return this.id == detailsRoot.id && Intrinsics.areEqual(this.code, detailsRoot.code) && Intrinsics.areEqual(this.title, detailsRoot.title) && Intrinsics.areEqual(this.posterUrl, detailsRoot.posterUrl) && Intrinsics.areEqual(this.description, detailsRoot.description) && Intrinsics.areEqual(this.createdAt, detailsRoot.createdAt) && this.ownerPrivate == detailsRoot.ownerPrivate && Intrinsics.areEqual(this.embedFrameUrl, detailsRoot.embedFrameUrl);
    }

    public int hashCode() {
        return (((((((((((((NextAiring$$ExternalSyntheticBackport0.m(this.id) * 31) + this.code.hashCode()) * 31) + this.title.hashCode()) * 31) + this.posterUrl.hashCode()) * 31) + this.description.hashCode()) * 31) + this.createdAt.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.ownerPrivate)) * 31) + this.embedFrameUrl.hashCode();
    }

    public String toString() {
        return "DetailsRoot(id=" + this.id + ", code=" + this.code + ", title=" + this.title + ", posterUrl=" + this.posterUrl + ", description=" + this.description + ", createdAt=" + this.createdAt + ", ownerPrivate=" + this.ownerPrivate + ", embedFrameUrl=" + this.embedFrameUrl + ')';
    }

    public DetailsRoot(long j, String code, String title, @JsonProperty("poster_url") String posterUrl, String description, @JsonProperty("created_at") String createdAt, @JsonProperty("owner_private") boolean z, @JsonProperty("embed_frame_url") String embedFrameUrl) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(posterUrl, "posterUrl");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(embedFrameUrl, "embedFrameUrl");
        this.id = j;
        this.code = code;
        this.title = title;
        this.posterUrl = posterUrl;
        this.description = description;
        this.createdAt = createdAt;
        this.ownerPrivate = z;
        this.embedFrameUrl = embedFrameUrl;
    }

    public final long getId() {
        return this.id;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getPosterUrl() {
        return this.posterUrl;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final boolean getOwnerPrivate() {
        return this.ownerPrivate;
    }

    public final String getEmbedFrameUrl() {
        return this.embedFrameUrl;
    }
}
