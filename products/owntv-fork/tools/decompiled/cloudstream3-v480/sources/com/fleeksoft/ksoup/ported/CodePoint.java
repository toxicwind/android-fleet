package com.fleeksoft.ksoup.ported;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: CodePoint.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\n\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0003J\u0006\u0010\u000e\u001a\u00020\u000fJ\n\u0010\u0010\u001a\u00020\u000fH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/fleeksoft/ksoup/ported/CodePoint;", "", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(I)V", "getValue", "()I", "charCount", "getCharCount", "toChars", "", "destination", TypedValues.CycleType.S_WAVE_OFFSET, "toUnicodeNotation", "", "toString", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CodePoint {
    private final int charCount;
    private final int value;

    public CodePoint(int i) {
        this.value = i;
        this.charCount = i >= Character.INSTANCE.getMIN_SUPPLEMENTARY_CODE_POINT() ? 2 : 1;
        if (!Character.INSTANCE.isValidCodePoint(i)) {
            throw new IllegalArgumentException("Not a valid code point".toString());
        }
    }

    public final int getValue() {
        return this.value;
    }

    public final int getCharCount() {
        return this.charCount;
    }

    public final char[] toChars() {
        return Character.INSTANCE.toChars(this.value);
    }

    public final int toChars(char[] destination, int offset) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        return Character.INSTANCE.toChars(this.value, destination, offset);
    }

    public final String toUnicodeNotation() {
        String string = Integer.toString(this.value, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String upperCase = string.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return "U+" + StringsKt.padStart(upperCase, 4, '0');
    }

    public String toString() {
        return StringsKt.concatToString(toChars());
    }
}
