package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.media.AudioTrack;
import android.os.Build;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class DefaultAudioTrackProvider implements DefaultAudioSink.AudioTrackProvider {
    protected AudioTrack.Builder customizeAudioTrackBuilder(AudioTrack.Builder builder) {
        return builder;
    }

    @Override // androidx.media3.exoplayer.audio.DefaultAudioSink.AudioTrackProvider
    public /* synthetic */ int getAudioTrackChannelConfig(int i) {
        return Util.getAudioTrackChannelConfig(i);
    }

    @Override // androidx.media3.exoplayer.audio.DefaultAudioSink.AudioTrackProvider
    public final AudioTrack getAudioTrack(AudioSink.AudioTrackConfig audioTrackConfig, AudioAttributes audioAttributes, int i, Context context) {
        AudioTrack.Builder sessionId = new AudioTrack.Builder().setAudioAttributes(getAudioTrackAttributes(audioAttributes, audioTrackConfig.tunneling)).setAudioFormat(Util.getAudioFormat(audioTrackConfig.sampleRate, audioTrackConfig.channelConfig, audioTrackConfig.encoding)).setTransferMode(1).setBufferSizeInBytes(audioTrackConfig.bufferSize).setSessionId(i);
        if (Build.VERSION.SDK_INT >= 29) {
            setOffloadedPlaybackV29(sessionId, audioTrackConfig.offload);
        }
        if (Build.VERSION.SDK_INT >= 34 && context != null) {
            sessionId.setContext(context);
        }
        return customizeAudioTrackBuilder(sessionId).build();
    }

    private void setOffloadedPlaybackV29(AudioTrack.Builder builder, boolean z) {
        builder.setOffloadedPlayback(z);
    }

    private android.media.AudioAttributes getAudioTrackAttributes(AudioAttributes audioAttributes, boolean z) {
        if (z) {
            return getAudioTrackTunnelingAttributes();
        }
        return audioAttributes.getPlatformAudioAttributes();
    }

    private android.media.AudioAttributes getAudioTrackTunnelingAttributes() {
        return new android.media.AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build();
    }
}
