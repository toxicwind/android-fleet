package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J)\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResumeWatchingStatus;", "", "progress", "Lcom/lagradost/cloudstream3/ui/result/ResumeProgress;", "isMovie", "", "result", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "<init>", "(Lcom/lagradost/cloudstream3/ui/result/ResumeProgress;ZLcom/lagradost/cloudstream3/ui/result/ResultEpisode;)V", "getProgress", "()Lcom/lagradost/cloudstream3/ui/result/ResumeProgress;", "()Z", "getResult", "()Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ResumeWatchingStatus {
    private final boolean isMovie;
    private final ResumeProgress progress;
    private final ResultEpisode result;

    public static /* synthetic */ ResumeWatchingStatus copy$default(ResumeWatchingStatus resumeWatchingStatus, ResumeProgress resumeProgress, boolean z, ResultEpisode resultEpisode, int i, Object obj) {
        if ((i & 1) != 0) {
            resumeProgress = resumeWatchingStatus.progress;
        }
        if ((i & 2) != 0) {
            z = resumeWatchingStatus.isMovie;
        }
        if ((i & 4) != 0) {
            resultEpisode = resumeWatchingStatus.result;
        }
        return resumeWatchingStatus.copy(resumeProgress, z, resultEpisode);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ResumeProgress getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsMovie() {
        return this.isMovie;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ResultEpisode getResult() {
        return this.result;
    }

    public final ResumeWatchingStatus copy(ResumeProgress progress, boolean isMovie, ResultEpisode result) {
        Intrinsics.checkNotNullParameter(result, "result");
        return new ResumeWatchingStatus(progress, isMovie, result);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResumeWatchingStatus)) {
            return false;
        }
        ResumeWatchingStatus resumeWatchingStatus = (ResumeWatchingStatus) other;
        return Intrinsics.areEqual(this.progress, resumeWatchingStatus.progress) && this.isMovie == resumeWatchingStatus.isMovie && Intrinsics.areEqual(this.result, resumeWatchingStatus.result);
    }

    public int hashCode() {
        ResumeProgress resumeProgress = this.progress;
        return ((((resumeProgress == null ? 0 : resumeProgress.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isMovie)) * 31) + this.result.hashCode();
    }

    public String toString() {
        return "ResumeWatchingStatus(progress=" + this.progress + ", isMovie=" + this.isMovie + ", result=" + this.result + ')';
    }

    public ResumeWatchingStatus(ResumeProgress resumeProgress, boolean z, ResultEpisode result) {
        Intrinsics.checkNotNullParameter(result, "result");
        this.progress = resumeProgress;
        this.isMovie = z;
        this.result = result;
    }

    public final ResumeProgress getProgress() {
        return this.progress;
    }

    public final boolean isMovie() {
        return this.isMovie;
    }

    public final ResultEpisode getResult() {
        return this.result;
    }
}
