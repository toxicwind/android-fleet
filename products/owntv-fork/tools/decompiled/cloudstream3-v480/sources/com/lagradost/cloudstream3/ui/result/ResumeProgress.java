package com.lagradost.cloudstream3.ui.result;

import com.lagradost.cloudstream3.utils.UiText;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResumeProgress;", "", "progress", "", "maxProgress", "progressLeft", "Lcom/lagradost/cloudstream3/utils/UiText;", "<init>", "(IILcom/lagradost/cloudstream3/utils/UiText;)V", "getProgress", "()I", "getMaxProgress", "getProgressLeft", "()Lcom/lagradost/cloudstream3/utils/UiText;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ResumeProgress {
    private final int maxProgress;
    private final int progress;
    private final UiText progressLeft;

    public static /* synthetic */ ResumeProgress copy$default(ResumeProgress resumeProgress, int i, int i2, UiText uiText, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = resumeProgress.progress;
        }
        if ((i3 & 2) != 0) {
            i2 = resumeProgress.maxProgress;
        }
        if ((i3 & 4) != 0) {
            uiText = resumeProgress.progressLeft;
        }
        return resumeProgress.copy(i, i2, uiText);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getMaxProgress() {
        return this.maxProgress;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final UiText getProgressLeft() {
        return this.progressLeft;
    }

    public final ResumeProgress copy(int progress, int maxProgress, UiText progressLeft) {
        Intrinsics.checkNotNullParameter(progressLeft, "progressLeft");
        return new ResumeProgress(progress, maxProgress, progressLeft);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResumeProgress)) {
            return false;
        }
        ResumeProgress resumeProgress = (ResumeProgress) other;
        return this.progress == resumeProgress.progress && this.maxProgress == resumeProgress.maxProgress && Intrinsics.areEqual(this.progressLeft, resumeProgress.progressLeft);
    }

    public int hashCode() {
        return (((this.progress * 31) + this.maxProgress) * 31) + this.progressLeft.hashCode();
    }

    public String toString() {
        return "ResumeProgress(progress=" + this.progress + ", maxProgress=" + this.maxProgress + ", progressLeft=" + this.progressLeft + ')';
    }

    public ResumeProgress(int i, int i2, UiText progressLeft) {
        Intrinsics.checkNotNullParameter(progressLeft, "progressLeft");
        this.progress = i;
        this.maxProgress = i2;
        this.progressLeft = progressLeft;
    }

    public final int getProgress() {
        return this.progress;
    }

    public final int getMaxProgress() {
        return this.maxProgress;
    }

    public final UiText getProgressLeft() {
        return this.progressLeft;
    }
}
