package androidx.compose.ui.window;

import android.view.WindowManager;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidDialog.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/window/Api28Impl;", "", "<init>", "()V", "setLayoutInDisplayCutout", "", "attrs", "Landroid/view/WindowManager$LayoutParams;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class Api28Impl {
    public static final Api28Impl INSTANCE = new Api28Impl();

    private Api28Impl() {
    }

    public final void setLayoutInDisplayCutout(WindowManager.LayoutParams attrs) {
        attrs.layoutInDisplayCutoutMode = 3;
    }
}
