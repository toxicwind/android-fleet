package androidx.mediarouter.media;

/* JADX INFO: loaded from: classes.dex */
abstract class RemoteControlClientCompat {

    public static final class PlaybackInfo {
        public int playbackStream;
        public int playbackType;
        public int volume;
        public String volumeControlId;
        public int volumeHandling = 0;
        public int volumeMax;
    }

    public abstract void setPlaybackInfo(PlaybackInfo playbackInfo);
}
