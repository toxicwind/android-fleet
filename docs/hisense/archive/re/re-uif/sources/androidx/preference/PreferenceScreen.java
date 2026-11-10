package androidx.preference;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;

/* JADX INFO: loaded from: classes.dex */
public final class PreferenceScreen extends PreferenceGroup {
    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.preferenceScreenStyle, R.attr.preferenceScreenStyle));
    }
}
