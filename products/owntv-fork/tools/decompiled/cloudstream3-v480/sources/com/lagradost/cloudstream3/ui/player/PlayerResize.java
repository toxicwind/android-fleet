package com.lagradost.cloudstream3.ui.player;

import com.lagradost.cloudstream3.R;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: AbstractPlayerFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0013\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PlayerResize;", "", "nameRes", "", "<init>", "(Ljava/lang/String;II)V", "getNameRes", "()I", "Fit", "Fill", "Zoom", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum PlayerResize {
    Fit(R.string.resize_fit),
    Fill(R.string.resize_fill),
    Zoom(R.string.resize_zoom);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int nameRes;

    public static EnumEntries<PlayerResize> getEntries() {
        return $ENTRIES;
    }

    PlayerResize(int i) {
        this.nameRes = i;
    }

    public final int getNameRes() {
        return this.nameRes;
    }
}
