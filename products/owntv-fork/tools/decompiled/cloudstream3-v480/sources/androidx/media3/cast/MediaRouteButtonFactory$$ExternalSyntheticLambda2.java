package androidx.media3.cast;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.android.gms.tasks.OnFailureListener;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class MediaRouteButtonFactory$$ExternalSyntheticLambda2 implements OnFailureListener {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        this.f$0.setException(exc);
    }
}
