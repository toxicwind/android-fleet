package androidx.activity.compose;

import androidx.activity.compose.internal.BackHandlerCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: BackHandler.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\r\u001a\u00020\bH\u0016R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Landroidx/activity/compose/ComposeBackHandler;", "Landroidx/activity/compose/internal/BackHandlerCompat;", "info", "Landroidx/activity/compose/BackHandlerInfo;", "<init>", "(Landroidx/activity/compose/BackHandlerInfo;)V", "currentOnBackCompleted", "Lkotlin/Function0;", "", "getCurrentOnBackCompleted", "()Lkotlin/jvm/functions/Function0;", "setCurrentOnBackCompleted", "(Lkotlin/jvm/functions/Function0;)V", "onBackCompleted", "activity-compose"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ComposeBackHandler extends BackHandlerCompat {
    private Function0<Unit> currentOnBackCompleted;

    public ComposeBackHandler(BackHandlerInfo backHandlerInfo) {
        super(backHandlerInfo);
        this.currentOnBackCompleted = new Function0() { // from class: androidx.activity.compose.ComposeBackHandler$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        };
    }

    public final Function0<Unit> getCurrentOnBackCompleted() {
        return this.currentOnBackCompleted;
    }

    public final void setCurrentOnBackCompleted(Function0<Unit> function0) {
        this.currentOnBackCompleted = function0;
    }

    @Override // androidx.activity.compose.internal.BackHandlerCompat
    public void onBackCompleted() {
        this.currentOnBackCompleted.invoke();
    }
}
