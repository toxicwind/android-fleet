package com.lagradost.cloudstream3.actions.temp.fcast;

import com.google.common.base.Ascii;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Packets.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/Opcode;", "", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/String;IB)V", "getValue", "()B", "None", "Play", "Pause", "Resume", "Stop", "Seek", "PlaybackUpdate", "VolumeUpdate", "SetVolume", "PlaybackError", "SetSpeed", "Version", "Ping", "Pong", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public enum Opcode {
    None((byte) 0),
    Play((byte) 1),
    Pause((byte) 2),
    Resume((byte) 3),
    Stop((byte) 4),
    Seek((byte) 5),
    PlaybackUpdate((byte) 6),
    VolumeUpdate((byte) 7),
    SetVolume((byte) 8),
    PlaybackError((byte) 9),
    SetSpeed((byte) 10),
    Version((byte) 11),
    Ping(Ascii.FF),
    Pong(Ascii.CR);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final byte value;

    public static EnumEntries<Opcode> getEntries() {
        return $ENTRIES;
    }

    Opcode(byte b) {
        this.value = b;
    }

    public final byte getValue() {
        return this.value;
    }
}
