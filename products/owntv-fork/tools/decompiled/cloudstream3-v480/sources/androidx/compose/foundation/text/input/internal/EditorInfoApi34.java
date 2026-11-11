package androidx.compose.foundation.text.input.internal;

import android.view.inputmethod.EditorInfo;
import androidx.compose.material3.internal.Listener$$ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;

/* JADX INFO: compiled from: EditorInfo.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/text/input/internal/EditorInfoApi34;", "", "()V", "setHandwritingGestures", "", "editorInfo", "Landroid/view/inputmethod/EditorInfo;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class EditorInfoApi34 {
    public static final EditorInfoApi34 INSTANCE = new EditorInfoApi34();

    private EditorInfoApi34() {
    }

    public final void setHandwritingGestures(EditorInfo editorInfo) {
        editorInfo.setSupportedHandwritingGestures(CollectionsKt.listOf((Object[]) new Class[]{Listener$$ExternalSyntheticApiModelOutline0.m2639m(), Listener$$ExternalSyntheticApiModelOutline0.m2646m$3(), Listener$$ExternalSyntheticApiModelOutline0.m2644m$1(), Listener$$ExternalSyntheticApiModelOutline0.m2645m$2(), Listener$$ExternalSyntheticApiModelOutline0.m2647m$4(), Listener$$ExternalSyntheticApiModelOutline0.m2648m$5(), Listener$$ExternalSyntheticApiModelOutline0.m2649m$6()}));
        editorInfo.setSupportedHandwritingGesturePreviews(SetsKt.setOf((Object[]) new Class[]{Listener$$ExternalSyntheticApiModelOutline0.m2639m(), Listener$$ExternalSyntheticApiModelOutline0.m2646m$3(), Listener$$ExternalSyntheticApiModelOutline0.m2644m$1(), Listener$$ExternalSyntheticApiModelOutline0.m2645m$2()}));
    }
}
