package androidx.compose.ui.platform;

import android.view.View;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidComposeView.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewTranslationCallbackS;", "", "<init>", "()V", "setViewTranslationCallback", "", "view", "Landroid/view/View;", "clearViewTranslationCallback", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AndroidComposeViewTranslationCallbackS {
    public static final int $stable = 0;
    public static final AndroidComposeViewTranslationCallbackS INSTANCE = new AndroidComposeViewTranslationCallbackS();

    private AndroidComposeViewTranslationCallbackS() {
    }

    public final void setViewTranslationCallback(View view) {
        view.setViewTranslationCallback(HapticDefaults$$ExternalSyntheticApiModelOutline0.m6039m((Object) AndroidComposeViewTranslationCallback.INSTANCE));
    }

    public final void clearViewTranslationCallback(View view) {
        view.clearViewTranslationCallback();
    }
}
