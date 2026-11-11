package com.fleeksoft.lang;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.text.CharsKt;

/* JADX INFO: compiled from: Character.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u0005J\u000e\u0010 \u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u0005J\u000e\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u001fJ\u000e\u0010#\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u001fJ\u000e\u0010$\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u001fJ\u0016\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u001fJ\u000e\u0010(\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005J\u000e\u0010)\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005J\u0016\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/fleeksoft/lang/Character;", "", "<init>", "()V", "MIN_SUPPLEMENTARY_CODE_POINT", "", "MAX_CODE_POINT", "SPACE_SEPARATOR", "LINE_SEPARATOR", "PARAGRAPH_SEPARATOR", "MIN_RADIX", "MAX_RADIX", "DECIMAL_DIGIT_NUMBER", MediaError.ERROR_TYPE_ERROR, "DIRECTIONALITY_UNDEFINED", "DIRECTIONALITY_LEFT_TO_RIGHT", "DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING", "DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE", "DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING", "DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE", "DIRECTIONALITY_POP_DIRECTIONAL_FORMAT", "DIRECTIONALITY_LEFT_TO_RIGHT_ISOLATE", "DIRECTIONALITY_RIGHT_TO_LEFT_ISOLATE", "DIRECTIONALITY_FIRST_STRONG_ISOLATE", "DIRECTIONALITY_POP_DIRECTIONAL_ISOLATE", "UNASSIGNED", "PRIVATE_USE", "isSupplementaryCodePoint", "", "codePoint", "highSurrogate", "", "lowSurrogate", "isSurrogate", "char", "isHighSurrogate", "isLowSurrogate", "toCodePoint", "high", "low", "isBmpCodePoint", "isValidCodePoint", "compare", "x", "y", "io-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Character {
    public static final int DECIMAL_DIGIT_NUMBER = 9;
    public static final int DIRECTIONALITY_FIRST_STRONG_ISOLATE = 21;
    public static final int DIRECTIONALITY_LEFT_TO_RIGHT = 0;
    public static final int DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING = 14;
    public static final int DIRECTIONALITY_LEFT_TO_RIGHT_ISOLATE = 19;
    public static final int DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE = 15;
    public static final int DIRECTIONALITY_POP_DIRECTIONAL_FORMAT = 18;
    public static final int DIRECTIONALITY_POP_DIRECTIONAL_ISOLATE = 22;
    public static final int DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING = 16;
    public static final int DIRECTIONALITY_RIGHT_TO_LEFT_ISOLATE = 20;
    public static final int DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE = 17;
    public static final int DIRECTIONALITY_UNDEFINED = -1;
    public static final int ERROR = -1;
    public static final Character INSTANCE = new Character();
    public static final int LINE_SEPARATOR = 13;
    public static final int MAX_CODE_POINT = 1114111;
    public static final int MAX_RADIX = 36;
    public static final int MIN_RADIX = 2;
    public static final int MIN_SUPPLEMENTARY_CODE_POINT = 65536;
    public static final int PARAGRAPH_SEPARATOR = 14;
    public static final int PRIVATE_USE = 18;
    public static final int SPACE_SEPARATOR = 12;
    public static final int UNASSIGNED = 0;

    public final int compare(char x, char y) {
        return x - y;
    }

    public final char highSurrogate(int codePoint) {
        return (char) ((codePoint >>> 10) + 55232);
    }

    public final boolean isBmpCodePoint(int codePoint) {
        return (codePoint >>> 16) == 0;
    }

    public final boolean isSupplementaryCodePoint(int codePoint) {
        return codePoint >= 65536 && codePoint < 1114112;
    }

    public final boolean isValidCodePoint(int codePoint) {
        return (codePoint >>> 16) < 17;
    }

    public final char lowSurrogate(int codePoint) {
        return (char) ((codePoint & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + 56320);
    }

    public final int toCodePoint(char high, char low) {
        return ((high << '\n') + low) - 56613888;
    }

    private Character() {
    }

    public final boolean isSurrogate(char c) {
        return CharsKt.isSurrogate(c);
    }

    public final boolean isHighSurrogate(char c) {
        return java.lang.Character.isHighSurrogate(c);
    }

    public final boolean isLowSurrogate(char c) {
        return java.lang.Character.isLowSurrogate(c);
    }
}
