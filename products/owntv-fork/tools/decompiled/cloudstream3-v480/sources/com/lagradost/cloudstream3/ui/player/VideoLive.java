package com.lagradost.cloudstream3.ui.player;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PlayerGeneratorViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\r\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J(\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/VideoLive;", ExifInterface.GPS_DIRECTION_TRUE, "", ES6Iterator.VALUE_PROPERTY, "instance", "", "<init>", "(Ljava/lang/Object;I)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getInstance", "()I", "component1", "component2", "copy", "(Ljava/lang/Object;I)Lcom/lagradost/cloudstream3/ui/player/VideoLive;", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class VideoLive<T> {
    private final int instance;
    private final T value;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VideoLive copy$default(VideoLive videoLive, Object obj, int i, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            obj = videoLive.value;
        }
        if ((i2 & 2) != 0) {
            i = videoLive.instance;
        }
        return videoLive.copy(obj, i);
    }

    public final T component1() {
        return this.value;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getInstance() {
        return this.instance;
    }

    public final VideoLive<T> copy(T value, int instance) {
        return new VideoLive<>(value, instance);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VideoLive)) {
            return false;
        }
        VideoLive videoLive = (VideoLive) other;
        return Intrinsics.areEqual(this.value, videoLive.value) && this.instance == videoLive.instance;
    }

    public int hashCode() {
        T t = this.value;
        return ((t == null ? 0 : t.hashCode()) * 31) + this.instance;
    }

    public String toString() {
        return "VideoLive(value=" + this.value + ", instance=" + this.instance + ')';
    }

    public VideoLive(T t, int i) {
        this.value = t;
        this.instance = i;
    }

    public final T getValue() {
        return this.value;
    }

    public final int getInstance() {
        return this.instance;
    }
}
