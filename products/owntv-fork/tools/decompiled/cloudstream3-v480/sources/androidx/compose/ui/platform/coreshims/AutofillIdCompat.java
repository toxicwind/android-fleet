package androidx.compose.ui.platform.coreshims;

import android.view.autofill.AutofillId;
import androidx.compose.ui.platform.HapticDefaults$$ExternalSyntheticApiModelOutline0;

/* JADX INFO: loaded from: classes4.dex */
public class AutofillIdCompat {
    private final Object mWrappedObj;

    private AutofillIdCompat(AutofillId autofillId) {
        this.mWrappedObj = autofillId;
    }

    public static AutofillIdCompat toAutofillIdCompat(AutofillId autofillId) {
        return new AutofillIdCompat(autofillId);
    }

    public AutofillId toAutofillId() {
        return HapticDefaults$$ExternalSyntheticApiModelOutline0.m6037m(this.mWrappedObj);
    }
}
