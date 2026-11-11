package androidx.work.impl;

import androidx.work.WorkInfo;
import androidx.work.WorkerParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorkLauncher.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/work/impl/WorkLauncher;", "", "startWork", "", "workSpecId", "Landroidx/work/impl/StartStopToken;", "runtimeExtras", "Landroidx/work/WorkerParameters$RuntimeExtras;", "stopWork", "reason", "", "stopWorkWithReason", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface WorkLauncher {
    void startWork(StartStopToken workSpecId);

    void startWork(StartStopToken workSpecId, WorkerParameters.RuntimeExtras runtimeExtras);

    void stopWork(StartStopToken workSpecId);

    void stopWork(StartStopToken workSpecId, int reason);

    void stopWorkWithReason(StartStopToken workSpecId, int reason);

    /* JADX INFO: renamed from: androidx.work.impl.WorkLauncher$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: WorkLauncher.kt */
    public final /* synthetic */ class CC {
        public static void $default$startWork(WorkLauncher _this, StartStopToken workSpecId) {
            Intrinsics.checkNotNullParameter(workSpecId, "workSpecId");
            _this.startWork(workSpecId, null);
        }

        public static void $default$stopWork(WorkLauncher _this, StartStopToken workSpecId) {
            Intrinsics.checkNotNullParameter(workSpecId, "workSpecId");
            _this.stopWork(workSpecId, WorkInfo.STOP_REASON_UNKNOWN);
        }

        public static void $default$stopWorkWithReason(WorkLauncher _this, StartStopToken workSpecId, int i) {
            Intrinsics.checkNotNullParameter(workSpecId, "workSpecId");
            _this.stopWork(workSpecId, i);
        }
    }
}
