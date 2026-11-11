package androidx.biometric.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: AuthenticationUiStateObserver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b \u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0005H$J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/biometric/internal/AuthenticationUiStateObserver;", "", "<init>", "()V", "uiStateObserverJob", "Lkotlinx/coroutines/Job;", "createObserverJob", "connectObservers", "", "disconnectObservers", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class AuthenticationUiStateObserver {
    private Job uiStateObserverJob;

    protected abstract Job createObserverJob();

    public final void connectObservers() {
        this.uiStateObserverJob = createObserverJob();
    }

    public final void disconnectObservers() {
        Job job = this.uiStateObserverJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.uiStateObserverJob = null;
    }
}
