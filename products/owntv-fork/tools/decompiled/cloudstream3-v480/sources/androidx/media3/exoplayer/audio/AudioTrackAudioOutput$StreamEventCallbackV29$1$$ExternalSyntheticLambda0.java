package androidx.media3.exoplayer.audio;

import androidx.media3.common.util.ListenerSet;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class AudioTrackAudioOutput$StreamEventCallbackV29$1$$ExternalSyntheticLambda0 implements ListenerSet.Event {
    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object obj) {
        ((AudioOutput.Listener) obj).onOffloadDataRequest();
    }
}
