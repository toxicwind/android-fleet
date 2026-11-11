package androidx.navigationevent.compose;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.navigationevent.NavigationEvent;
import androidx.navigationevent.NavigationEventHandler;
import androidx.navigationevent.NavigationEventInfo;
import androidx.navigationevent.NavigationEventTransitionState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: NavigationEventHandler.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B%\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001e\u001a\u00020\bH\u0014J\b\u0010\u001f\u001a\u00020\bH\u0014J\u0010\u0010 \u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\u0010\u0010!\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\"\u001a\u00020\bH\u0014J\b\u0010#\u001a\u00020\bH\u0014R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010¨\u0006$"}, d2 = {"Landroidx/navigationevent/compose/ComposeNavigationEventHandler;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/navigationevent/NavigationEventInfo;", "Landroidx/navigationevent/NavigationEventHandler;", "initialInfo", "onTransitionStateChanged", "Lkotlin/Function1;", "Landroidx/navigationevent/NavigationEventTransitionState;", "", "<init>", "(Landroidx/navigationevent/NavigationEventInfo;Lkotlin/jvm/functions/Function1;)V", "currentOnForwardCancelled", "Lkotlin/Function0;", "getCurrentOnForwardCancelled", "()Lkotlin/jvm/functions/Function0;", "setCurrentOnForwardCancelled", "(Lkotlin/jvm/functions/Function0;)V", "currentOnForwardCompleted", "getCurrentOnForwardCompleted", "setCurrentOnForwardCompleted", "currentOnBackCancelled", "getCurrentOnBackCancelled", "setCurrentOnBackCancelled", "currentOnBackCompleted", "getCurrentOnBackCompleted", "setCurrentOnBackCompleted", "onForwardStarted", NotificationCompat.CATEGORY_EVENT, "Landroidx/navigationevent/NavigationEvent;", "onForwardProgressed", "onForwardCancelled", "onForwardCompleted", "onBackStarted", "onBackProgressed", "onBackCancelled", "onBackCompleted", "navigationevent-compose"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ComposeNavigationEventHandler<T extends NavigationEventInfo> extends NavigationEventHandler<T> {
    private Function0<Unit> currentOnBackCancelled;
    private Function0<Unit> currentOnBackCompleted;
    private Function0<Unit> currentOnForwardCancelled;
    private Function0<Unit> currentOnForwardCompleted;
    private final Function1<NavigationEventTransitionState, Unit> onTransitionStateChanged;

    public /* synthetic */ ComposeNavigationEventHandler(NavigationEventInfo navigationEventInfo, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(navigationEventInfo, (i & 2) != 0 ? new Function1() { // from class: androidx.navigationevent.compose.ComposeNavigationEventHandler$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        } : function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ComposeNavigationEventHandler(T t, Function1<? super NavigationEventTransitionState, Unit> function1) {
        super(t, false, false);
        this.onTransitionStateChanged = function1;
        this.currentOnForwardCancelled = new Function0() { // from class: androidx.navigationevent.compose.ComposeNavigationEventHandler$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        };
        this.currentOnForwardCompleted = new Function0() { // from class: androidx.navigationevent.compose.ComposeNavigationEventHandler$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        };
        this.currentOnBackCancelled = new Function0() { // from class: androidx.navigationevent.compose.ComposeNavigationEventHandler$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        };
        this.currentOnBackCompleted = new Function0() { // from class: androidx.navigationevent.compose.ComposeNavigationEventHandler$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        };
    }

    public final Function0<Unit> getCurrentOnForwardCancelled() {
        return this.currentOnForwardCancelled;
    }

    public final void setCurrentOnForwardCancelled(Function0<Unit> function0) {
        this.currentOnForwardCancelled = function0;
    }

    public final Function0<Unit> getCurrentOnForwardCompleted() {
        return this.currentOnForwardCompleted;
    }

    public final void setCurrentOnForwardCompleted(Function0<Unit> function0) {
        this.currentOnForwardCompleted = function0;
    }

    public final Function0<Unit> getCurrentOnBackCancelled() {
        return this.currentOnBackCancelled;
    }

    public final void setCurrentOnBackCancelled(Function0<Unit> function0) {
        this.currentOnBackCancelled = function0;
    }

    public final Function0<Unit> getCurrentOnBackCompleted() {
        return this.currentOnBackCompleted;
    }

    public final void setCurrentOnBackCompleted(Function0<Unit> function0) {
        this.currentOnBackCompleted = function0;
    }

    @Override // androidx.navigationevent.NavigationEventHandler
    protected void onForwardStarted(NavigationEvent event) {
        this.onTransitionStateChanged.invoke(getTransitionState());
    }

    @Override // androidx.navigationevent.NavigationEventHandler
    protected void onForwardProgressed(NavigationEvent event) {
        this.onTransitionStateChanged.invoke(getTransitionState());
    }

    @Override // androidx.navigationevent.NavigationEventHandler
    protected void onForwardCancelled() {
        this.onTransitionStateChanged.invoke(getTransitionState());
        this.currentOnForwardCancelled.invoke();
    }

    @Override // androidx.navigationevent.NavigationEventHandler
    protected void onForwardCompleted() {
        this.onTransitionStateChanged.invoke(getTransitionState());
        this.currentOnForwardCompleted.invoke();
    }

    @Override // androidx.navigationevent.NavigationEventHandler
    protected void onBackStarted(NavigationEvent event) {
        this.onTransitionStateChanged.invoke(getTransitionState());
    }

    @Override // androidx.navigationevent.NavigationEventHandler
    protected void onBackProgressed(NavigationEvent event) {
        this.onTransitionStateChanged.invoke(getTransitionState());
    }

    @Override // androidx.navigationevent.NavigationEventHandler
    protected void onBackCancelled() {
        this.onTransitionStateChanged.invoke(getTransitionState());
        this.currentOnBackCancelled.invoke();
    }

    @Override // androidx.navigationevent.NavigationEventHandler
    protected void onBackCompleted() {
        this.onTransitionStateChanged.invoke(getTransitionState());
        this.currentOnBackCompleted.invoke();
    }
}
