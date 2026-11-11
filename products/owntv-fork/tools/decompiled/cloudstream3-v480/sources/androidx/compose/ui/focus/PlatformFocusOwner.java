package androidx.compose.ui.focus;

import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlatformFocusOwner.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b`\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\b\bJ\b\u0010\t\u001a\u00020\nH&J\u0017\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0004\b\f\u0010\rJ\n\u0010\u000e\u001a\u0004\u0018\u00010\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/focus/PlatformFocusOwner;", "", "requestOwnerFocus", "", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "previouslyFocusedRect", "Landroidx/compose/ui/geometry/Rect;", "requestOwnerFocus-7o62pno", "clearOwnerFocus", "", "moveFocusInChildren", "moveFocusInChildren-3ESFkO8", "(I)Z", "getEmbeddedViewFocusRect", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface PlatformFocusOwner {
    void clearOwnerFocus();

    Rect getEmbeddedViewFocusRect();

    /* JADX INFO: renamed from: moveFocusInChildren-3ESFkO8, reason: not valid java name */
    boolean mo3811moveFocusInChildren3ESFkO8(int focusDirection);

    /* JADX INFO: renamed from: requestOwnerFocus-7o62pno, reason: not valid java name */
    boolean mo3812requestOwnerFocus7o62pno(FocusDirection focusDirection, Rect previouslyFocusedRect);
}
