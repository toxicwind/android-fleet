package androidx.activity.compose;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import androidx.compose.runtime.CompositionLocalAccessorScope;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: LocalActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0019\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"LocalActivity", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroid/app/Activity;", "getLocalActivity", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "activity-compose"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LocalActivityKt {
    private static final ProvidableCompositionLocal<Activity> LocalActivity = CompositionLocalKt.compositionLocalWithComputedDefaultOf(new Function1() { // from class: androidx.activity.compose.LocalActivityKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return LocalActivityKt.LocalActivity$lambda$0((CompositionLocalAccessorScope) obj);
        }
    });

    public static final ProvidableCompositionLocal<Activity> getLocalActivity() {
        return LocalActivity;
    }

    static final Activity LocalActivity$lambda$0(CompositionLocalAccessorScope compositionLocalAccessorScope) {
        Context baseContext = (Context) compositionLocalAccessorScope.getCurrentValue(AndroidCompositionLocals_androidKt.getLocalContext());
        while (baseContext instanceof ContextWrapper) {
            if (!(baseContext instanceof Activity)) {
                baseContext = ((ContextWrapper) baseContext).getBaseContext();
            } else {
                return (Activity) baseContext;
            }
        }
        baseContext = null;
        return (Activity) baseContext;
    }
}
