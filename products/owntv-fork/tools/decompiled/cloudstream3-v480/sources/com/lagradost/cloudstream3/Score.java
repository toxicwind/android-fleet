package com.lagradost.cloudstream3;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.common.C;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.api.Log;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0013\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0006\u001a\u00020\u0003H\u0096\u0080\u0004J\u0014\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\b\u0010\n\u001a\u00020\u0003H\u0007J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003J\u0010\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003J\u0010\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\r\u001a\u00020\u0003J\u0010\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\r\u001a\u00020\u0003J\u0010\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\r\u001a\u00020\u0003J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004J6\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\u001cJ,\u0010\u0015\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\u001cR\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/Score;", "", "data", "", "<init>", "(I)V", "hashCode", "equals", "", "other", "toOld", "toByte", "", "maxScore", "toInt", "toLong", "", "toFloat", "", "toDouble", "", "toString", "", "toStringNull", "minScore", "decimals", "removeTrailingZeros", "decimalChar", "", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class Score {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int MAX = 1000000000;
    public static final int MAX_ZEROS = 9;
    public static final int MIN = 0;
    private static final String TAG = "Score";

    @JsonProperty("data")
    private final int data;

    public /* synthetic */ Score(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    private Score(@JsonProperty("data") int i) {
        this.data = i;
    }

    /* JADX INFO: renamed from: hashCode, reason: from getter */
    public int getData() {
        return this.data;
    }

    public boolean equals(Object other) {
        return (other instanceof Score) && this.data == ((Score) other).data;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "toOld() is deprecated. Use other Score methods instead.")
    public final int toOld() {
        return toInt(10000);
    }

    public final byte toByte(int maxScore) {
        return (byte) toLong(maxScore);
    }

    public static /* synthetic */ int toInt$default(Score score, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 10;
        }
        return score.toInt(i);
    }

    public final int toInt(int maxScore) {
        return (int) toLong(maxScore);
    }

    public static /* synthetic */ long toLong$default(Score score, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 10;
        }
        return score.toLong(i);
    }

    public final long toLong(int maxScore) {
        return (((long) this.data) * ((long) maxScore)) / C.NANOS_PER_SECOND;
    }

    public static /* synthetic */ float toFloat$default(Score score, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 10;
        }
        return score.toFloat(i);
    }

    public final float toFloat(int maxScore) {
        return (this.data / 1.0E9f) * maxScore;
    }

    public static /* synthetic */ double toDouble$default(Score score, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 10;
        }
        return score.toDouble(i);
    }

    public final double toDouble(int maxScore) {
        return (((double) this.data) / 1.0E9d) * ((double) maxScore);
    }

    public String toString() {
        return toString$default(this, 10, 0, false, (char) 0, 14, null);
    }

    public static /* synthetic */ String toStringNull$default(Score score, double d, int i, int i2, boolean z, char c, int i3, Object obj) throws IllegalArgumentException {
        if ((i3 & 4) != 0) {
            i2 = 1;
        }
        if ((i3 & 8) != 0) {
            z = true;
        }
        if ((i3 & 16) != 0) {
            c = '.';
        }
        return score.toStringNull(d, i, i2, z, c);
    }

    public final String toStringNull(double minScore, int maxScore, int decimals, boolean removeTrailingZeros, char decimalChar) throws IllegalArgumentException {
        if (toDouble$default(this, 0, 1, null) < minScore) {
            return null;
        }
        return toString(maxScore, decimals, removeTrailingZeros, decimalChar);
    }

    public static /* synthetic */ String toString$default(Score score, int i, int i2, boolean z, char c, int i3, Object obj) throws IllegalArgumentException {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        if ((i3 & 4) != 0) {
            z = true;
        }
        if ((i3 & 8) != 0) {
            c = '.';
        }
        return score.toString(i, i2, z, c);
    }

    public final String toString(int maxScore, int decimals, boolean removeTrailingZeros, char decimalChar) throws IllegalArgumentException {
        int i;
        if (1 > maxScore || maxScore >= 1001) {
            throw new IllegalArgumentException("maxScore ∈ [1,1000]".toString());
        }
        if (decimals < 0 || decimals >= 10) {
            throw new IllegalArgumentException("decimals ∈ [0,9]".toString());
        }
        long j = ((long) this.data) * ((long) maxScore);
        char[] cArr = new char[15];
        int i2 = 0;
        for (int i3 = 0; i3 < 15; i3++) {
            cArr[i3] = CharsKt.digitToChar((int) (j % 10));
            j /= 10;
        }
        int iMax = 9 - decimals;
        int i4 = iMax;
        int i5 = i4;
        while (i4 < 15 && cArr[i4] == '0') {
            i5++;
            i4++;
        }
        int i6 = 14;
        while (true) {
            int i7 = i6 - 1;
            if (cArr[i6] != '0') {
                break;
            }
            i2++;
            if (i7 < 0) {
                break;
            }
            i6 = i7;
        }
        StringBuilder sb = new StringBuilder();
        int iMax2 = Math.max(9, 14 - i2);
        while (true) {
            i = 8;
            if (8 >= iMax2) {
                break;
            }
            sb.append(cArr[iMax2]);
            iMax2--;
        }
        if (removeTrailingZeros) {
            iMax = Math.max(iMax, i5);
        }
        if (iMax <= 8) {
            sb.append(decimalChar);
            if (iMax <= 8) {
                while (true) {
                    sb.append(cArr[i]);
                    if (i == iMax) {
                        break;
                    }
                    i--;
                }
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\u0005¢\u0006\u0002\u0010\u0010J\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000f\u001a\u00020\u0005¢\u0006\u0002\u0010\u0012J\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\u0005¢\u0006\u0002\u0010\u0014J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\u0005J\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\rJ\u0017\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\rJ\u0017\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\rJ\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0018J\u0017\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0018J\u0017\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0011¢\u0006\u0002\u0010\u0018J\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0019J\u0017\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0019J\u0017\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0019J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\tJ\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\tJ\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/Score$Companion;", "", "<init>", "()V", "MAX", "", "MIN", "MAX_ZEROS", "TAG", "", "fromOld", "Lcom/lagradost/cloudstream3/Score;", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/Score;", TypedValues.TransitionType.S_FROM, "maxScore", "(Ljava/lang/Integer;I)Lcom/lagradost/cloudstream3/Score;", "", "(Ljava/lang/Double;I)Lcom/lagradost/cloudstream3/Score;", "", "(Ljava/lang/Float;I)Lcom/lagradost/cloudstream3/Score;", "from5", "from10", "from100", "(Ljava/lang/Double;)Lcom/lagradost/cloudstream3/Score;", "(Ljava/lang/Float;)Lcom/lagradost/cloudstream3/Score;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Score.fromOld is deprecated. Use other Score.from* methods instead.")
        public final Score fromOld(Integer value) {
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (value == null) {
                return null;
            }
            if (value.intValue() < 0 || value.intValue() > 10000) {
                Log.INSTANCE.w(Score.TAG, "old: " + value.intValue() + " ∉ [0, 10000]");
                return null;
            }
            return new Score(value.intValue() * 100000, defaultConstructorMarker);
        }

        public final Score from(Integer value, int maxScore) {
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (value == null) {
                return null;
            }
            if (value.intValue() < 0 || value.intValue() > maxScore) {
                Log.INSTANCE.w(Score.TAG, "fromInt: " + value.intValue() + " ∉ [0, " + maxScore + AbstractJsonLexerKt.END_LIST);
                return null;
            }
            return new Score((1000000000 / maxScore) * value.intValue(), defaultConstructorMarker);
        }

        public final Score from(Double value, int maxScore) {
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (value == null) {
                return null;
            }
            if (value.doubleValue() < 0.0d || value.doubleValue() > maxScore) {
                Log.INSTANCE.w(Score.TAG, "fromDouble: " + value.doubleValue() + " ∉ [0.0, " + maxScore + AbstractJsonLexerKt.END_LIST);
                return null;
            }
            return new Score(MathKt.roundToInt(((double) (1000000000 / maxScore)) * value.doubleValue()), defaultConstructorMarker);
        }

        public final Score from(Float value, int maxScore) {
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (value == null) {
                return null;
            }
            if (value.floatValue() < 0.0d || value.floatValue() > maxScore) {
                Log.INSTANCE.w(Score.TAG, "fromFloat: " + value.floatValue() + " ∉ [0.0f, " + maxScore + AbstractJsonLexerKt.END_LIST);
                return null;
            }
            return new Score(MathKt.roundToInt((1000000000 / maxScore) * value.floatValue()), defaultConstructorMarker);
        }

        public final Score from(String value, int maxScore) {
            String string;
            Double doubleOrNull;
            return from((value == null || (string = StringsKt.trim((CharSequence) value).toString()) == null || (doubleOrNull = StringsKt.toDoubleOrNull(string)) == null) ? null : Double.valueOf(Math.abs(doubleOrNull.doubleValue())), maxScore);
        }

        public final Score from5(Integer value) {
            return from(value, 5);
        }

        public final Score from10(Integer value) {
            return from(value, 10);
        }

        public final Score from100(Integer value) {
            return from(value, 100);
        }

        public final Score from5(Double value) {
            return from(value, 5);
        }

        public final Score from10(Double value) {
            return from(value, 10);
        }

        public final Score from100(Double value) {
            return from(value, 100);
        }

        public final Score from5(Float value) {
            return from(value, 5);
        }

        public final Score from10(Float value) {
            return from(value, 10);
        }

        public final Score from100(Float value) {
            return from(value, 100);
        }

        public final Score from5(String value) {
            return from(value, 5);
        }

        public final Score from10(String value) {
            return from(value, 10);
        }

        public final Score from100(String value) {
            return from(value, 100);
        }
    }
}
