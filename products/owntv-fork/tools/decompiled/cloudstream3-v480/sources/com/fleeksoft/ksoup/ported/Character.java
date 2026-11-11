package com.fleeksoft.ksoup.ported;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.fasterxml.jackson.core.base.GeneratorBase;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

/* JADX INFO: compiled from: Character.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0005J\u000e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005J\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005J\u000e\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0005J\u000e\u0010\u001c\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0005J\u001e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0005J\u000e\u0010\"\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u0005J\u001e\u0010\"\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0005R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\tX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/fleeksoft/ksoup/ported/Character;", "", "<init>", "()V", "MIN_SUPPLEMENTARY_CODE_POINT", "", "getMIN_SUPPLEMENTARY_CODE_POINT", "()I", "MIN_HIGH_SURROGATE", "", "MIN_LOW_SURROGATE", "MAX_LOW_SURROGATE", "MIN_SURROGATE", "MAX_SURROGATE", "getMAX_SURROGATE", "()C", "MAX_CODE_POINT", "toCodePoint", "high", "low", "isDigit", "", "codePoint", "Lcom/fleeksoft/ksoup/ported/CodePoint;", "codePointValue", "isValidCodePoint", "isBmpCodePoint", "highSurrogate", "lowSurrogate", "toSurrogates", "", "dst", "", "index", "toChars", "dstIndex", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Character {
    public static final int MAX_CODE_POINT = 1114111;
    public static final char MAX_LOW_SURROGATE = 57343;
    public static final char MIN_HIGH_SURROGATE = 55296;
    public static final char MIN_LOW_SURROGATE = 56320;
    public static final char MIN_SURROGATE = 55296;
    public static final Character INSTANCE = new Character();
    private static final int MIN_SUPPLEMENTARY_CODE_POINT = 65536;
    private static final char MAX_SURROGATE = 57343;

    public final boolean isBmpCodePoint(int codePoint) {
        return (codePoint >>> 16) == 0;
    }

    public final boolean isValidCodePoint(int codePoint) {
        return (codePoint >>> 16) < 17;
    }

    public final char lowSurrogate(int codePoint) {
        return (char) ((codePoint & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320);
    }

    private Character() {
    }

    public final int getMIN_SUPPLEMENTARY_CODE_POINT() {
        return MIN_SUPPLEMENTARY_CODE_POINT;
    }

    public final char getMAX_SURROGATE() {
        return MAX_SURROGATE;
    }

    public final int toCodePoint(char high, char low) {
        return (high << '\n') + low + (MIN_SUPPLEMENTARY_CODE_POINT - 56679424);
    }

    public final boolean isDigit(CodePoint codePoint) {
        Intrinsics.checkNotNullParameter(codePoint, "codePoint");
        return java.lang.Character.isDigit((char) codePoint.getValue());
    }

    public final boolean isDigit(int codePointValue) {
        return java.lang.Character.isDigit((char) codePointValue);
    }

    public final char highSurrogate(int codePoint) {
        return (char) ((codePoint >>> 10) + (GeneratorBase.SURR1_FIRST - (MIN_SUPPLEMENTARY_CODE_POINT >>> 10)));
    }

    public final void toSurrogates(int codePoint, char[] dst, int index) {
        Intrinsics.checkNotNullParameter(dst, "dst");
        dst[index + 1] = lowSurrogate(codePoint);
        dst[index] = highSurrogate(codePoint);
    }

    public final char[] toChars(int codePoint) {
        if (isBmpCodePoint(codePoint)) {
            return new char[]{(char) codePoint};
        }
        if (isValidCodePoint(codePoint)) {
            char[] cArr = new char[2];
            toSurrogates(codePoint, cArr, 0);
            return cArr;
        }
        String string = Integer.toString(codePoint, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String upperCase = string.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        throw new IllegalArgumentException("Not a valid Unicode code point: 0x" + upperCase);
    }

    public final int toChars(int codePoint, char[] dst, int dstIndex) {
        Intrinsics.checkNotNullParameter(dst, "dst");
        if (isBmpCodePoint(codePoint)) {
            dst[dstIndex] = (char) codePoint;
            return 1;
        }
        if (isValidCodePoint(codePoint)) {
            toSurrogates(codePoint, dst, dstIndex);
            return 2;
        }
        String string = Integer.toString(codePoint, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String upperCase = string.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        throw new IllegalArgumentException("Not a valid Unicode code point: 0x" + upperCase);
    }
}
