package com.fleeksoft.ksoup.ported;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Character.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001\u001a\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001\u001a%\u0010\u0006\u001a\u0002H\u0007\"\f\b\u0000\u0010\u0007*\u00060\bj\u0002`\t*\u0002H\u00072\u0006\u0010\n\u001a\u00020\u0001¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"codePointValueAt", "", "", "index", "codePointAt", "Lcom/fleeksoft/ksoup/ported/CodePoint;", "appendCodePoint", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "codePoint", "(Ljava/lang/Appendable;I)Ljava/lang/Appendable;", "ksoup_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CharacterKt {
    public static final int codePointValueAt(CharSequence charSequence, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (i < 0 || i >= charSequence.length()) {
            throw new IndexOutOfBoundsException();
        }
        char cCharAt = charSequence.charAt(i);
        if (java.lang.Character.isHighSurrogate(cCharAt) && (i2 = i + 1) < charSequence.length()) {
            char cCharAt2 = charSequence.charAt(i2);
            if (java.lang.Character.isLowSurrogate(cCharAt2)) {
                return Character.INSTANCE.toCodePoint(cCharAt, cCharAt2);
            }
        }
        return cCharAt;
    }

    public static final CodePoint codePointAt(CharSequence charSequence, int i) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return CodePointKt.toCodePoint(codePointValueAt(charSequence, i));
    }

    public static final <T extends Appendable> T appendCodePoint(T t, int i) throws IOException {
        Intrinsics.checkNotNullParameter(t, "<this>");
        if (Character.INSTANCE.isBmpCodePoint(i)) {
            t.append((char) i);
            return t;
        }
        t.append(Character.INSTANCE.highSurrogate(i));
        t.append(Character.INSTANCE.lowSurrogate(i));
        return t;
    }
}
