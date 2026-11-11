package com.lagradost.cloudstream3;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003JJ\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/Tracker;", "", "malId", "", "kitsuId", "", "aniId", "image", "cover", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMalId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getKitsuId", "()Ljava/lang/String;", "getAniId", "getImage", "getCover", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/Tracker;", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Tracker {
    private final String aniId;
    private final String cover;
    private final String image;
    private final String kitsuId;
    private final Integer malId;

    public Tracker() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ Tracker copy$default(Tracker tracker, Integer num, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = tracker.malId;
        }
        if ((i & 2) != 0) {
            str = tracker.kitsuId;
        }
        if ((i & 4) != 0) {
            str2 = tracker.aniId;
        }
        if ((i & 8) != 0) {
            str3 = tracker.image;
        }
        if ((i & 16) != 0) {
            str4 = tracker.cover;
        }
        String str5 = str4;
        String str6 = str2;
        return tracker.copy(num, str, str6, str3, str5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getMalId() {
        return this.malId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getKitsuId() {
        return this.kitsuId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAniId() {
        return this.aniId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCover() {
        return this.cover;
    }

    public final Tracker copy(Integer malId, String kitsuId, String aniId, String image, String cover) {
        return new Tracker(malId, kitsuId, aniId, image, cover);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Tracker)) {
            return false;
        }
        Tracker tracker = (Tracker) other;
        return Intrinsics.areEqual(this.malId, tracker.malId) && Intrinsics.areEqual(this.kitsuId, tracker.kitsuId) && Intrinsics.areEqual(this.aniId, tracker.aniId) && Intrinsics.areEqual(this.image, tracker.image) && Intrinsics.areEqual(this.cover, tracker.cover);
    }

    public int hashCode() {
        Integer num = this.malId;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.kitsuId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.aniId;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.image;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.cover;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "Tracker(malId=" + this.malId + ", kitsuId=" + this.kitsuId + ", aniId=" + this.aniId + ", image=" + this.image + ", cover=" + this.cover + ')';
    }

    public Tracker(Integer num, String str, String str2, String str3, String str4) {
        this.malId = num;
        this.kitsuId = str;
        this.aniId = str2;
        this.image = str3;
        this.cover = str4;
    }

    public /* synthetic */ Tracker(Integer num, String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4);
    }

    public final Integer getMalId() {
        return this.malId;
    }

    public final String getKitsuId() {
        return this.kitsuId;
    }

    public final String getAniId() {
        return this.aniId;
    }

    public final String getImage() {
        return this.image;
    }

    public final String getCover() {
        return this.cover;
    }
}
