package androidx.activity.compose.internal;

import androidx.activity.OnBackPressedDispatcher;
import androidx.navigationevent.NavigationEventDispatcher;
import kotlin.Metadata;

/* JADX INFO: compiled from: BackHandlerDispatcherCompat.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/activity/compose/internal/BackHandlerDispatcherCompat;", "", "navigationEventDispatcher", "Landroidx/navigationevent/NavigationEventDispatcher;", "onBackPressedDispatcher", "Landroidx/activity/OnBackPressedDispatcher;", "<init>", "(Landroidx/navigationevent/NavigationEventDispatcher;Landroidx/activity/OnBackPressedDispatcher;)V", "addHandler", "", "handler", "Landroidx/activity/compose/internal/BackHandlerCompat;", "removeHandler", "activity-compose"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BackHandlerDispatcherCompat {
    public static final int $stable = 8;
    private final NavigationEventDispatcher navigationEventDispatcher;
    private final OnBackPressedDispatcher onBackPressedDispatcher;

    public BackHandlerDispatcherCompat(NavigationEventDispatcher navigationEventDispatcher, OnBackPressedDispatcher onBackPressedDispatcher) {
        this.navigationEventDispatcher = navigationEventDispatcher;
        this.onBackPressedDispatcher = onBackPressedDispatcher;
        if ((navigationEventDispatcher == null ? onBackPressedDispatcher : navigationEventDispatcher) == null) {
            throw new IllegalArgumentException("At least one dispatcher (NavigationEventDispatcher or OnBackPressedDispatcher) must be non-null.".toString());
        }
    }

    public final void addHandler(BackHandlerCompat handler) {
        NavigationEventDispatcher navigationEventDispatcher = this.navigationEventDispatcher;
        if (navigationEventDispatcher != null) {
            NavigationEventDispatcher.addHandler$default(navigationEventDispatcher, handler.getNavigationEventHandler(), 0, 2, null);
            return;
        }
        OnBackPressedDispatcher onBackPressedDispatcher = this.onBackPressedDispatcher;
        if (onBackPressedDispatcher != null) {
            onBackPressedDispatcher.addCallback(handler.getOnBackPressedCallback());
            return;
        }
        throw new IllegalStateException("Unreachable".toString());
    }

    public final void removeHandler(BackHandlerCompat handler) throws Exception {
        if (this.navigationEventDispatcher != null) {
            handler.getNavigationEventHandler().remove();
        } else {
            if (this.onBackPressedDispatcher == null) {
                throw new IllegalStateException("Unreachable".toString());
            }
            handler.getOnBackPressedCallback().remove();
        }
    }
}
