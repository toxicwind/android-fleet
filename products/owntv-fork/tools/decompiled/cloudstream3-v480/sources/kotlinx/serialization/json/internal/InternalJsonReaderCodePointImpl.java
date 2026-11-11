package kotlinx.serialization.json.internal;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: JsonStreams.kt */
/* JADX INFO: loaded from: classes5.dex */
@JsonFriendModuleApi
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005H¦\u0080\u0004J\n\u0010\u0006\u001a\u00020\u0007H¦\u0080\u0004J\"\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0086\u0080\u0004R\u0013\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0010"}, d2 = {"Lkotlinx/serialization/json/internal/InternalJsonReaderCodePointImpl;", "Lkotlinx/serialization/json/internal/InternalJsonReader;", "<init>", "()V", "exhausted", "", "nextCodePoint", "", "bufferedChar", "", "Ljava/lang/Character;", "read", "buffer", "", "bufferOffset", PeertubeParsingHelper.COUNT_KEY, "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class InternalJsonReaderCodePointImpl implements InternalJsonReader {
    private Character bufferedChar;

    public abstract boolean exhausted();

    public abstract int nextCodePoint();

    @Override // kotlinx.serialization.json.internal.InternalJsonReader
    public final int read(char[] buffer, int bufferOffset, int count) {
        int i;
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        Character ch = this.bufferedChar;
        if (ch != null) {
            Intrinsics.checkNotNull(ch);
            buffer[bufferOffset] = ch.charValue();
            this.bufferedChar = null;
            i = 1;
        } else {
            i = 0;
        }
        while (i < count && !exhausted()) {
            int iNextCodePoint = nextCodePoint();
            if (iNextCodePoint <= 65535) {
                buffer[bufferOffset + i] = (char) iNextCodePoint;
                i++;
            } else {
                char c = (char) ((iNextCodePoint >>> 10) + 55232);
                char c2 = (char) ((iNextCodePoint & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320);
                buffer[bufferOffset + i] = c;
                int i2 = i + 1;
                if (i2 < count) {
                    buffer[i2 + bufferOffset] = c2;
                    i += 2;
                } else {
                    this.bufferedChar = Character.valueOf(c2);
                    i = i2;
                }
            }
        }
        if (i > 0) {
            return i;
        }
        return -1;
    }
}
