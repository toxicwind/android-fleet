package b.e.b.i;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCanceledListener;

/* JADX INFO: compiled from: lambda */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n implements OnCanceledListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final /* synthetic */ CancellationTokenSource f660a;

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        this.f660a.cancel();
    }
}
