package com.lagradost.cloudstream3.ui.player;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/CSPlayerEvent;", "", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "Pause", "Play", "SeekForward", "SeekBack", "SkipCurrentChapter", "NextEpisode", "PrevEpisode", "PlayPauseToggle", "ToggleMute", "Restart", "PlayAsAudio", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum CSPlayerEvent {
    Pause(0),
    Play(1),
    SeekForward(2),
    SeekBack(3),
    SkipCurrentChapter(4),
    NextEpisode(5),
    PrevEpisode(6),
    PlayPauseToggle(7),
    ToggleMute(8),
    Restart(9),
    PlayAsAudio(10);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int value;

    public static EnumEntries<CSPlayerEvent> getEntries() {
        return $ENTRIES;
    }

    CSPlayerEvent(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
