package com.lagradost.cloudstream3.utils;

import android.widget.TextView;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: TextUtil.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0019\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b\u0004\u001a)\u0010\u0000\u001a\u00020\u00012\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0002\u0010\n\u001a5\u0010\u0000\u001a\u0004\u0018\u00010\u00012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0016\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\b\"\u0004\u0018\u00010\tH\u0007¢\u0006\u0004\b\u0004\u0010\u000b\u001a\u0016\u0010\f\u001a\u00020\r*\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001\u001a\u0016\u0010\u0010\u001a\u00020\r*\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001¨\u0006\u0011"}, d2 = {"txt", "Lcom/lagradost/cloudstream3/utils/UiText;", ES6Iterator.VALUE_PROPERTY, "", "txtNull", "resId", "", "args", "", "", "(I[Ljava/lang/Object;)Lcom/lagradost/cloudstream3/utils/UiText;", "(Ljava/lang/Integer;[Ljava/lang/Object;)Lcom/lagradost/cloudstream3/utils/UiText;", "setText", "", "Landroid/widget/TextView;", "text", "setTextHtml", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TextUtilKt {
    public static final UiText txt(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new UiText.DynamicString(value);
    }

    public static final UiText txtNull(String str) {
        if (str == null) {
            return null;
        }
        return new UiText.DynamicString(str);
    }

    public static final UiText txt(int i, Object... args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return new UiText.StringResource(i, ArraysKt.toList(args));
    }

    public static final void setText(TextView textView, UiText uiText) {
        if (textView == null) {
            return;
        }
        if (uiText != null) {
            String strAsStringNull = uiText.asStringNull(textView.getContext());
            boolean z = true;
            if (strAsStringNull == null) {
                strAsStringNull = null;
            } else if (textView.getMaxLines() == 1) {
                strAsStringNull = StringsKt.replace$default(strAsStringNull, "\n", Stream.ID_UNKNOWN, false, 4, (Object) null);
            }
            TextView textView2 = textView;
            String str = strAsStringNull;
            if (str != null && !StringsKt.isBlank(str)) {
                z = false;
            }
            textView2.setVisibility(z ? 8 : 0);
            textView.setText(str);
            return;
        }
        textView.setVisibility(8);
    }

    public static final void setTextHtml(TextView textView, UiText uiText) {
        if (textView == null) {
            return;
        }
        if (uiText != null) {
            String strAsStringNull = uiText.asStringNull(textView.getContext());
            String str = strAsStringNull;
            textView.setVisibility(str == null || StringsKt.isBlank(str) ? 8 : 0);
            textView.setText(AppContextUtils.INSTANCE.html(strAsStringNull));
            return;
        }
        textView.setVisibility(8);
    }

    public static final UiText txtNull(Integer num, Object... args) {
        Intrinsics.checkNotNullParameter(args, "args");
        if (num == null) {
            return null;
        }
        for (Object obj : args) {
            if (obj == null) {
                return null;
            }
        }
        return new UiText.StringResource(num.intValue(), CollectionsKt.toList(ArraysKt.filterNotNull(args)));
    }
}
