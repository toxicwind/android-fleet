package androidx.compose.ui.text.platform.extensions;

import android.text.Spannable;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.PlaceholderVerticalAlign;
import androidx.compose.ui.text.android.style.PlaceholderSpan;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import androidx.emoji2.text.EmojiSpan;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaceholderExtensions.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a,\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0002\"\u001e\u0010\u000e\u001a\u00020\f*\u00020\u000f8BX\u0082\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u001e\u0010\u0014\u001a\u00020\f*\u00020\u00158BX\u0082\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"setPlaceholders", "", "Landroid/text/Spannable;", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "density", "Landroidx/compose/ui/unit/Density;", "setPlaceholder", "placeholder", "start", "", TtmlNode.END, "spanUnit", "Landroidx/compose/ui/unit/TextUnit;", "getSpanUnit--R2X_6o$annotations", "(J)V", "getSpanUnit--R2X_6o", "(J)I", "spanVerticalAlign", "Landroidx/compose/ui/text/PlaceholderVerticalAlign;", "getSpanVerticalAlign-do9X-Gg$annotations", "(I)V", "getSpanVerticalAlign-do9X-Gg", "(I)I", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PlaceholderExtensions_androidKt {
    /* JADX INFO: renamed from: getSpanUnit--R2X_6o$annotations, reason: not valid java name */
    private static /* synthetic */ void m6617getSpanUnitR2X_6o$annotations(long j) {
    }

    /* JADX INFO: renamed from: getSpanVerticalAlign-do9X-Gg$annotations, reason: not valid java name */
    private static /* synthetic */ void m6619getSpanVerticalAligndo9XGg$annotations(int i) {
    }

    private static final void setPlaceholder(Spannable spannable, Placeholder placeholder, int i, int i2, Density density) {
        for (Object obj : spannable.getSpans(i, i2, EmojiSpan.class)) {
            spannable.removeSpan((EmojiSpan) obj);
        }
        SpannableExtensions_androidKt.setSpan(spannable, new PlaceholderSpan(TextUnit.m7088getValueimpl(placeholder.getWidth()), m6616getSpanUnitR2X_6o(placeholder.getWidth()), TextUnit.m7088getValueimpl(placeholder.getHeight()), m6616getSpanUnitR2X_6o(placeholder.getHeight()), density.getFontScale() * density.getDensity(), m6618getSpanVerticalAligndo9XGg(placeholder.getPlaceholderVerticalAlign())), i, i2);
    }

    /* JADX INFO: renamed from: getSpanUnit--R2X_6o, reason: not valid java name */
    private static final int m6616getSpanUnitR2X_6o(long j) {
        long jM7087getTypeUIouoOA = TextUnit.m7087getTypeUIouoOA(j);
        if (TextUnitType.m7116equalsimpl0(jM7087getTypeUIouoOA, TextUnitType.INSTANCE.m7121getSpUIouoOA())) {
            return 0;
        }
        return TextUnitType.m7116equalsimpl0(jM7087getTypeUIouoOA, TextUnitType.INSTANCE.m7120getEmUIouoOA()) ? 1 : 2;
    }

    /* JADX INFO: renamed from: getSpanVerticalAlign-do9X-Gg, reason: not valid java name */
    private static final int m6618getSpanVerticalAligndo9XGg(int i) {
        if (PlaceholderVerticalAlign.m6261equalsimpl0(i, PlaceholderVerticalAlign.INSTANCE.m6265getAboveBaselineJ6kI3mc())) {
            return 0;
        }
        if (PlaceholderVerticalAlign.m6261equalsimpl0(i, PlaceholderVerticalAlign.INSTANCE.m6271getTopJ6kI3mc())) {
            return 1;
        }
        if (PlaceholderVerticalAlign.m6261equalsimpl0(i, PlaceholderVerticalAlign.INSTANCE.m6266getBottomJ6kI3mc())) {
            return 2;
        }
        if (PlaceholderVerticalAlign.m6261equalsimpl0(i, PlaceholderVerticalAlign.INSTANCE.m6267getCenterJ6kI3mc())) {
            return 3;
        }
        if (PlaceholderVerticalAlign.m6261equalsimpl0(i, PlaceholderVerticalAlign.INSTANCE.m6270getTextTopJ6kI3mc())) {
            return 4;
        }
        if (PlaceholderVerticalAlign.m6261equalsimpl0(i, PlaceholderVerticalAlign.INSTANCE.m6268getTextBottomJ6kI3mc())) {
            return 5;
        }
        if (PlaceholderVerticalAlign.m6261equalsimpl0(i, PlaceholderVerticalAlign.INSTANCE.m6269getTextCenterJ6kI3mc())) {
            return 6;
        }
        throw new IllegalStateException("Invalid PlaceholderVerticalAlign".toString());
    }

    public static final void setPlaceholders(Spannable spannable, List<AnnotatedString.Range<Placeholder>> list, Density density) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AnnotatedString.Range<Placeholder> range = list.get(i);
            setPlaceholder(spannable, range.component1(), range.getStart(), range.getEnd(), density);
        }
    }
}
