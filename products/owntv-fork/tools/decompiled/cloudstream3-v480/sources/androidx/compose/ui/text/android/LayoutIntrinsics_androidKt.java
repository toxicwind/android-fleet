package androidx.compose.ui.text.android;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.MetricAffectingSpan;
import androidx.compose.ui.text.android.style.LetterSpacingSpanEm;
import androidx.compose.ui.text.android.style.LetterSpacingSpanPx;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;

/* JADX INFO: compiled from: LayoutIntrinsics.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a \u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003¨\u0006\f"}, d2 = {"stripNonMetricAffectingCharSpans", "", "getStripNonMetricAffectingCharSpans$annotations", "()V", "stripNonMetricAffectingCharacterStyleSpans", "", "charSequence", "shouldIncreaseMaxIntrinsic", "desiredWidth", "", "textPaint", "Landroid/text/TextPaint;", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LayoutIntrinsics_androidKt {
    private static final boolean stripNonMetricAffectingCharSpans = true;

    private static /* synthetic */ void getStripNonMetricAffectingCharSpans$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence stripNonMetricAffectingCharacterStyleSpans(CharSequence charSequence) {
        CharacterStyle[] characterStyleArr;
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            if (SpannedExtensions_androidKt.hasSpan(spanned, CharacterStyle.class) && (characterStyleArr = (CharacterStyle[]) spanned.getSpans(0, charSequence.length(), CharacterStyle.class)) != null && characterStyleArr.length != 0) {
                Iterator it = ArrayIteratorKt.iterator(characterStyleArr);
                SpannableString spannableString = null;
                while (it.hasNext()) {
                    CharacterStyle characterStyle = (CharacterStyle) it.next();
                    if (!(characterStyle instanceof MetricAffectingSpan)) {
                        if (spannableString == null) {
                            spannableString = new SpannableString(charSequence);
                        }
                        spannableString.removeSpan(characterStyle);
                    }
                }
                if (spannableString != null) {
                    return spannableString;
                }
            }
        }
        return charSequence;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldIncreaseMaxIntrinsic(float f, CharSequence charSequence, TextPaint textPaint) {
        if (f == 0.0f) {
            return false;
        }
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            if (SpannedExtensions_androidKt.hasSpan(spanned, LetterSpacingSpanPx.class) || SpannedExtensions_androidKt.hasSpan(spanned, LetterSpacingSpanEm.class)) {
                return true;
            }
        }
        return textPaint.getLetterSpacing() != 0.0f;
    }
}
