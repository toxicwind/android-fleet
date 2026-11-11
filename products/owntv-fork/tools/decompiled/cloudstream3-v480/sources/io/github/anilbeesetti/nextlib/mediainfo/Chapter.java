package io.github.anilbeesetti.nextlib.mediainfo;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Chapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lio/github/anilbeesetti/nextlib/mediainfo/Chapter;", "", "index", "", "start", "", TtmlNode.END, "title", "", "<init>", "(IJJLjava/lang/String;)V", "getIndex", "()I", "getStart", "()J", "getEnd", "getTitle", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "mediainfo"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Chapter {
    private final long end;
    private final int index;
    private final long start;
    private final String title;

    public static /* synthetic */ Chapter copy$default(Chapter chapter, int i, long j, long j2, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = chapter.index;
        }
        if ((i2 & 2) != 0) {
            j = chapter.start;
        }
        if ((i2 & 4) != 0) {
            j2 = chapter.end;
        }
        if ((i2 & 8) != 0) {
            str = chapter.title;
        }
        String str2 = str;
        return chapter.copy(i, j, j2, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getStart() {
        return this.start;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getEnd() {
        return this.end;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    public final Chapter copy(int index, long start, long end, String title) {
        return new Chapter(index, start, end, title);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Chapter)) {
            return false;
        }
        Chapter chapter = (Chapter) other;
        return this.index == chapter.index && this.start == chapter.start && this.end == chapter.end && Intrinsics.areEqual(this.title, chapter.title);
    }

    public int hashCode() {
        int iM = ((((this.index * 31) + UByte$$ExternalSyntheticBackport0.m(this.start)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.end)) * 31;
        String str = this.title;
        return iM + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "Chapter(index=" + this.index + ", start=" + this.start + ", end=" + this.end + ", title=" + this.title + ")";
    }

    public Chapter(int i, long j, long j2, String str) {
        this.index = i;
        this.start = j;
        this.end = j2;
        this.title = str;
    }

    public final int getIndex() {
        return this.index;
    }

    public final long getStart() {
        return this.start;
    }

    public final long getEnd() {
        return this.end;
    }

    public final String getTitle() {
        return this.title;
    }
}
