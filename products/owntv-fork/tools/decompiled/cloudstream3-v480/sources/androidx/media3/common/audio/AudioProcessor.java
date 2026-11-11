package androidx.media3.common.audio;

import androidx.media3.common.Format;
import androidx.media3.common.util.Util;
import com.google.common.base.Preconditions;
import j$.util.Objects;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes4.dex */
public interface AudioProcessor {
    public static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());

    AudioFormat configure(AudioFormat audioFormat) throws UnhandledAudioFormatException;

    @Deprecated
    void flush();

    void flush(StreamMetadata streamMetadata);

    long getDurationAfterProcessorApplied(long j);

    ByteBuffer getOutput();

    boolean isActive();

    boolean isEnded();

    void queueEndOfStream();

    void queueInput(ByteBuffer byteBuffer);

    void reset();

    public static final class AudioFormat {
        public static final AudioFormat NOT_SET = new AudioFormat(-1, -1, -1);
        public final int bytesPerFrame;
        public final int channelCount;
        public final int encoding;
        public final int sampleRate;

        public AudioFormat(Format format) {
            this(format.sampleRate, format.channelCount, format.pcmEncoding);
        }

        public AudioFormat(int i, int i2, int i3) {
            this.sampleRate = i;
            this.channelCount = i2;
            this.encoding = i3;
            this.bytesPerFrame = Util.isEncodingLinearPcm(i3) ? Util.getPcmFrameSize(i3, i2) : -1;
        }

        public String toString() {
            return "AudioFormat[sampleRate=" + this.sampleRate + ", channelCount=" + this.channelCount + ", encoding=" + this.encoding + AbstractJsonLexerKt.END_LIST;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AudioFormat)) {
                return false;
            }
            AudioFormat audioFormat = (AudioFormat) obj;
            return this.sampleRate == audioFormat.sampleRate && this.channelCount == audioFormat.channelCount && this.encoding == audioFormat.encoding;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.sampleRate), Integer.valueOf(this.channelCount), Integer.valueOf(this.encoding));
        }
    }

    public static final class UnhandledAudioFormatException extends Exception {
        public final AudioFormat inputAudioFormat;

        public UnhandledAudioFormatException(AudioFormat audioFormat) {
            this("Unhandled input format:", audioFormat);
        }

        public UnhandledAudioFormatException(String str, AudioFormat audioFormat) {
            super(str + Stream.ID_UNKNOWN + audioFormat);
            this.inputAudioFormat = audioFormat;
        }
    }

    public static final class StreamMetadata {
        public static final StreamMetadata DEFAULT = new StreamMetadata(0);
        public final long positionOffsetUs;

        public StreamMetadata(long j) {
            Preconditions.checkArgument(j >= 0);
            this.positionOffsetUs = j;
        }
    }

    /* JADX INFO: renamed from: androidx.media3.common.audio.AudioProcessor$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static long $default$getDurationAfterProcessorApplied(AudioProcessor _this, long j) {
            return j;
        }

        @Deprecated
        public static void $default$flush(AudioProcessor _this) {
            throw new IllegalStateException("AudioProcessor must implement at least one #flush() overload.");
        }
    }
}
