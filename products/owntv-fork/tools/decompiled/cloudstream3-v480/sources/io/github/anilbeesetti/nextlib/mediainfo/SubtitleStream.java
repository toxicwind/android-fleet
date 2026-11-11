package io.github.anilbeesetti.nextlib.mediainfo;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubtitleStream.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J?\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001d"}, d2 = {"Lio/github/anilbeesetti/nextlib/mediainfo/SubtitleStream;", "", "index", "", "title", "", "codecName", "language", "disposition", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getIndex", "()I", "getTitle", "()Ljava/lang/String;", "getCodecName", "getLanguage", "getDisposition", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "mediainfo"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SubtitleStream {
    private final String codecName;
    private final int disposition;
    private final int index;
    private final String language;
    private final String title;

    public static /* synthetic */ SubtitleStream copy$default(SubtitleStream subtitleStream, int i, String str, String str2, String str3, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = subtitleStream.index;
        }
        if ((i3 & 2) != 0) {
            str = subtitleStream.title;
        }
        if ((i3 & 4) != 0) {
            str2 = subtitleStream.codecName;
        }
        if ((i3 & 8) != 0) {
            str3 = subtitleStream.language;
        }
        if ((i3 & 16) != 0) {
            i2 = subtitleStream.disposition;
        }
        int i4 = i2;
        String str4 = str2;
        return subtitleStream.copy(i, str, str4, str3, i4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCodecName() {
        return this.codecName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getDisposition() {
        return this.disposition;
    }

    public final SubtitleStream copy(int index, String title, String codecName, String language, int disposition) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        return new SubtitleStream(index, title, codecName, language, disposition);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubtitleStream)) {
            return false;
        }
        SubtitleStream subtitleStream = (SubtitleStream) other;
        return this.index == subtitleStream.index && Intrinsics.areEqual(this.title, subtitleStream.title) && Intrinsics.areEqual(this.codecName, subtitleStream.codecName) && Intrinsics.areEqual(this.language, subtitleStream.language) && this.disposition == subtitleStream.disposition;
    }

    public int hashCode() {
        int i = this.index * 31;
        String str = this.title;
        int iHashCode = (((i + (str == null ? 0 : str.hashCode())) * 31) + this.codecName.hashCode()) * 31;
        String str2 = this.language;
        return ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.disposition;
    }

    public String toString() {
        return "SubtitleStream(index=" + this.index + ", title=" + this.title + ", codecName=" + this.codecName + ", language=" + this.language + ", disposition=" + this.disposition + ")";
    }

    public SubtitleStream(int i, String str, String codecName, String str2, int i2) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        this.index = i;
        this.title = str;
        this.codecName = codecName;
        this.language = str2;
        this.disposition = i2;
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getCodecName() {
        return this.codecName;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final int getDisposition() {
        return this.disposition;
    }
}
