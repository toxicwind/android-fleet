package c.a.b;

import java.util.concurrent.Executor;

/* JADX INFO: renamed from: c.a.b.dc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class ExecutorC0105dc implements Executor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0130ic f1243a;

    public ExecutorC0105dc(C0130ic c0130ic) {
        this.f1243a = c0130ic;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.f1243a.t.a().execute(runnable);
    }
}
