package androidx.activity.compose;

import androidx.activity.FullyDrawnReporter;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: ReportDrawn.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\r\u001a\u00020\u0002H\u0096\u0082\u0004J\u0006\u0010\u000e\u001a\u00020\u0002J\u0016\u0010\u000f\u001a\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0001\u0012\u0004\u0012\u00020\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/activity/compose/ReportDrawnComposition;", "Lkotlin/Function0;", "", "fullyDrawnReporter", "Landroidx/activity/FullyDrawnReporter;", "predicate", "", "<init>", "(Landroidx/activity/FullyDrawnReporter;Lkotlin/jvm/functions/Function0;)V", "snapshotStateObserver", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "checkReporter", "Lkotlin/Function1;", "invoke", "removeReporter", "observeReporter", "activity-compose"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ReportDrawnComposition implements Function0<Unit> {
    private final Function1<Function0<Boolean>, Unit> checkReporter;
    private final FullyDrawnReporter fullyDrawnReporter;
    private final Function0<Boolean> predicate;
    private final SnapshotStateObserver snapshotStateObserver;

    public ReportDrawnComposition(FullyDrawnReporter fullyDrawnReporter, Function0<Boolean> function0) {
        this.fullyDrawnReporter = fullyDrawnReporter;
        this.predicate = function0;
        SnapshotStateObserver snapshotStateObserver = new SnapshotStateObserver(new Function1() { // from class: androidx.activity.compose.ReportDrawnComposition$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ReportDrawnComposition.snapshotStateObserver$lambda$0((Function0) obj);
            }
        });
        snapshotStateObserver.start();
        this.snapshotStateObserver = snapshotStateObserver;
        this.checkReporter = new ReportDrawnComposition$checkReporter$1(this);
        fullyDrawnReporter.addOnReportDrawnListener(this);
        if (fullyDrawnReporter.isFullyDrawnReported()) {
            return;
        }
        fullyDrawnReporter.addReporter();
        observeReporter(function0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    static final Unit snapshotStateObserver$lambda$0(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2() {
        this.snapshotStateObserver.clear();
        this.snapshotStateObserver.stop();
    }

    public final void removeReporter() {
        this.snapshotStateObserver.clear(this.predicate);
        if (!this.fullyDrawnReporter.isFullyDrawnReported()) {
            this.fullyDrawnReporter.removeReporter();
        }
        invoke2();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void observeReporter(final Function0<Boolean> predicate) {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        this.snapshotStateObserver.observeReads(predicate, this.checkReporter, new Function0() { // from class: androidx.activity.compose.ReportDrawnComposition$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ReportDrawnComposition.observeReporter$lambda$0(booleanRef, predicate);
            }
        });
        if (booleanRef.element) {
            removeReporter();
        }
    }

    static final Unit observeReporter$lambda$0(Ref.BooleanRef booleanRef, Function0 function0) {
        booleanRef.element = ((Boolean) function0.invoke()).booleanValue();
        return Unit.INSTANCE;
    }
}
