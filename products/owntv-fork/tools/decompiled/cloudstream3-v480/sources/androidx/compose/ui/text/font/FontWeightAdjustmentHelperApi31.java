package androidx.compose.ui.text.font;

import android.content.Context;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidFontResolveInterceptor.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/text/font/FontWeightAdjustmentHelperApi31;", "", "<init>", "()V", "fontWeightAdjustment", "", "context", "Landroid/content/Context;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FontWeightAdjustmentHelperApi31 {
    public static final int $stable = 0;
    public static final FontWeightAdjustmentHelperApi31 INSTANCE = new FontWeightAdjustmentHelperApi31();

    private FontWeightAdjustmentHelperApi31() {
    }

    public final int fontWeightAdjustment(Context context) {
        return context.getResources().getConfiguration().fontWeightAdjustment;
    }
}
