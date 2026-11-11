package androidx.compose.ui.platform;

import android.content.Context;
import android.view.PointerIcon;
import android.view.View;
import androidx.compose.ui.input.pointer.AndroidPointerIcon;
import androidx.compose.ui.input.pointer.AndroidPointerIconType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidComposeView.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewVerificationHelperMethodsN;", "", "<init>", "()V", "toAndroidPointerIcon", "Landroid/view/PointerIcon;", "context", "Landroid/content/Context;", "icon", "Landroidx/compose/ui/input/pointer/PointerIcon;", "setPointerIcon", "", "view", "Landroid/view/View;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class AndroidComposeViewVerificationHelperMethodsN {
    public static final AndroidComposeViewVerificationHelperMethodsN INSTANCE = new AndroidComposeViewVerificationHelperMethodsN();

    private AndroidComposeViewVerificationHelperMethodsN() {
    }

    public final PointerIcon toAndroidPointerIcon(Context context, androidx.compose.ui.input.pointer.PointerIcon icon) {
        if (icon instanceof AndroidPointerIcon) {
            return ((AndroidPointerIcon) icon).getPointerIcon();
        }
        return icon instanceof AndroidPointerIconType ? PointerIcon.getSystemIcon(context, ((AndroidPointerIconType) icon).getType()) : PointerIcon.getSystemIcon(context, 1000);
    }

    public final void setPointerIcon(View view, androidx.compose.ui.input.pointer.PointerIcon icon) {
        PointerIcon androidPointerIcon = toAndroidPointerIcon(view.getContext(), icon);
        if (Intrinsics.areEqual(view.getPointerIcon(), androidPointerIcon)) {
            return;
        }
        view.setPointerIcon(androidPointerIcon);
    }
}
