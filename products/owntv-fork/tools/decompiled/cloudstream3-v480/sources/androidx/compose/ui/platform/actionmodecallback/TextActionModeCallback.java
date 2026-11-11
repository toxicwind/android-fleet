package androidx.compose.ui.platform.actionmodecallback;

import android.os.Build;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextActionModeCallback.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B}\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$J\u001a\u0010%\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$J\u001a\u0010&\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010'\u001a\u0004\u0018\u00010(J\u0006\u0010)\u001a\u00020\u0004J\u0015\u0010*\u001a\u00020\u00042\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b+J\u001d\u0010,\u001a\u00020\u00042\u0006\u0010#\u001a\u00020$2\u0006\u0010'\u001a\u00020-H\u0000¢\u0006\u0002\b.J(\u0010/\u001a\u00020\u00042\u0006\u0010#\u001a\u00020$2\u0006\u0010'\u001a\u00020-2\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0002R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0016R\"\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u000f\"\u0004\b\u0018\u0010\u0016R\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0016R\"\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000f\"\u0004\b\u001c\u0010\u0016R\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000f\"\u0004\b\u001e\u0010\u0016¨\u00061"}, d2 = {"Landroidx/compose/ui/platform/actionmodecallback/TextActionModeCallback;", "", "onActionModeDestroy", "Lkotlin/Function0;", "", "rect", "Landroidx/compose/ui/geometry/Rect;", "onCopyRequested", "onPasteRequested", "onCutRequested", "onSelectAllRequested", "onAutofillRequested", "<init>", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/geometry/Rect;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getOnActionModeDestroy", "()Lkotlin/jvm/functions/Function0;", "getRect", "()Landroidx/compose/ui/geometry/Rect;", "setRect", "(Landroidx/compose/ui/geometry/Rect;)V", "getOnCopyRequested", "setOnCopyRequested", "(Lkotlin/jvm/functions/Function0;)V", "getOnPasteRequested", "setOnPasteRequested", "getOnCutRequested", "setOnCutRequested", "getOnSelectAllRequested", "setOnSelectAllRequested", "getOnAutofillRequested", "setOnAutofillRequested", "onCreateActionMode", "", "mode", "Landroid/view/ActionMode;", "menu", "Landroid/view/Menu;", "onPrepareActionMode", "onActionItemClicked", "item", "Landroid/view/MenuItem;", "onDestroyActionMode", "updateMenuItems", "updateMenuItems$ui_release", "addMenuItem", "Landroidx/compose/ui/platform/actionmodecallback/MenuItemOption;", "addMenuItem$ui_release", "addOrRemoveMenuItem", "callback", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextActionModeCallback {
    public static final int $stable = 8;
    private final Function0<Unit> onActionModeDestroy;
    private Function0<Unit> onAutofillRequested;
    private Function0<Unit> onCopyRequested;
    private Function0<Unit> onCutRequested;
    private Function0<Unit> onPasteRequested;
    private Function0<Unit> onSelectAllRequested;
    private Rect rect;

    public TextActionModeCallback() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public TextActionModeCallback(Function0<Unit> function0, Rect rect, Function0<Unit> function1, Function0<Unit> function2, Function0<Unit> function3, Function0<Unit> function4, Function0<Unit> function5) {
        this.onActionModeDestroy = function0;
        this.rect = rect;
        this.onCopyRequested = function1;
        this.onPasteRequested = function2;
        this.onCutRequested = function3;
        this.onSelectAllRequested = function4;
        this.onAutofillRequested = function5;
    }

    public final Function0<Unit> getOnActionModeDestroy() {
        return this.onActionModeDestroy;
    }

    public /* synthetic */ TextActionModeCallback(Function0 function0, Rect rect, Function0 function1, Function0 function2, Function0 function3, Function0 function4, Function0 function5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : function0, (i & 2) != 0 ? Rect.INSTANCE.getZero() : rect, (i & 4) != 0 ? null : function1, (i & 8) != 0 ? null : function2, (i & 16) != 0 ? null : function3, (i & 32) != 0 ? null : function4, (i & 64) != 0 ? null : function5);
    }

    public final Rect getRect() {
        return this.rect;
    }

    public final void setRect(Rect rect) {
        this.rect = rect;
    }

    public final Function0<Unit> getOnCopyRequested() {
        return this.onCopyRequested;
    }

    public final void setOnCopyRequested(Function0<Unit> function0) {
        this.onCopyRequested = function0;
    }

    public final Function0<Unit> getOnPasteRequested() {
        return this.onPasteRequested;
    }

    public final void setOnPasteRequested(Function0<Unit> function0) {
        this.onPasteRequested = function0;
    }

    public final Function0<Unit> getOnCutRequested() {
        return this.onCutRequested;
    }

    public final void setOnCutRequested(Function0<Unit> function0) {
        this.onCutRequested = function0;
    }

    public final Function0<Unit> getOnSelectAllRequested() {
        return this.onSelectAllRequested;
    }

    public final void setOnSelectAllRequested(Function0<Unit> function0) {
        this.onSelectAllRequested = function0;
    }

    public final Function0<Unit> getOnAutofillRequested() {
        return this.onAutofillRequested;
    }

    public final void setOnAutofillRequested(Function0<Unit> function0) {
        this.onAutofillRequested = function0;
    }

    public final boolean onCreateActionMode(ActionMode mode, Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null menu".toString());
        }
        if (mode == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null mode".toString());
        }
        if (this.onCopyRequested != null) {
            addMenuItem$ui_release(menu, MenuItemOption.Copy);
        }
        if (this.onPasteRequested != null) {
            addMenuItem$ui_release(menu, MenuItemOption.Paste);
        }
        if (this.onCutRequested != null) {
            addMenuItem$ui_release(menu, MenuItemOption.Cut);
        }
        if (this.onSelectAllRequested != null) {
            addMenuItem$ui_release(menu, MenuItemOption.SelectAll);
        }
        if (this.onAutofillRequested == null || Build.VERSION.SDK_INT < 26) {
            return true;
        }
        addMenuItem$ui_release(menu, MenuItemOption.Autofill);
        return true;
    }

    public final boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        if (mode == null || menu == null) {
            return false;
        }
        updateMenuItems$ui_release(menu);
        return true;
    }

    public final boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        Intrinsics.checkNotNull(item);
        int itemId = item.getItemId();
        if (itemId == MenuItemOption.Copy.getId()) {
            Function0<Unit> function0 = this.onCopyRequested;
            if (function0 != null) {
                function0.invoke();
            }
        } else if (itemId == MenuItemOption.Paste.getId()) {
            Function0<Unit> function1 = this.onPasteRequested;
            if (function1 != null) {
                function1.invoke();
            }
        } else if (itemId == MenuItemOption.Cut.getId()) {
            Function0<Unit> function2 = this.onCutRequested;
            if (function2 != null) {
                function2.invoke();
            }
        } else if (itemId == MenuItemOption.SelectAll.getId()) {
            Function0<Unit> function3 = this.onSelectAllRequested;
            if (function3 != null) {
                function3.invoke();
            }
        } else {
            if (itemId != MenuItemOption.Autofill.getId()) {
                return false;
            }
            Function0<Unit> function4 = this.onAutofillRequested;
            if (function4 != null) {
                function4.invoke();
            }
        }
        if (mode == null) {
            return true;
        }
        mode.finish();
        return true;
    }

    public final void onDestroyActionMode() {
        Function0<Unit> function0 = this.onActionModeDestroy;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public final void updateMenuItems$ui_release(Menu menu) {
        addOrRemoveMenuItem(menu, MenuItemOption.Copy, this.onCopyRequested);
        addOrRemoveMenuItem(menu, MenuItemOption.Paste, this.onPasteRequested);
        addOrRemoveMenuItem(menu, MenuItemOption.Cut, this.onCutRequested);
        addOrRemoveMenuItem(menu, MenuItemOption.SelectAll, this.onSelectAllRequested);
        addOrRemoveMenuItem(menu, MenuItemOption.Autofill, this.onAutofillRequested);
    }

    public final void addMenuItem$ui_release(Menu menu, MenuItemOption item) {
        menu.add(0, item.getId(), item.getOrder(), item.getTitleResource()).setShowAsAction(1);
    }

    private final void addOrRemoveMenuItem(Menu menu, MenuItemOption item, Function0<Unit> callback) {
        if (callback != null && menu.findItem(item.getId()) == null) {
            addMenuItem$ui_release(menu, item);
        } else {
            if (callback != null || menu.findItem(item.getId()) == null) {
                return;
            }
            menu.removeItem(item.getId());
        }
    }
}
