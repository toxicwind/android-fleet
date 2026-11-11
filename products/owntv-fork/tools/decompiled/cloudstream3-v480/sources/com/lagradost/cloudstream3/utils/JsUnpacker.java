package com.lagradost.cloudstream3.utils;

import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: JsUnpacker.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \n2\u00020\u0001:\u0002\t\nB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\b\u0010\b\u001a\u0004\u0018\u00010\u0003R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/utils/JsUnpacker;", "", "packedJS", "", "<init>", "(Ljava/lang/String;)V", "detect", "", "unpack", "Unbase", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JsUnpacker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final List<Integer> c = CollectionsKt.listOf((Object[]) new Integer[]{99, 111, 109, 46, 103, 111, 111, 103, 108, 101, 46, 97, 110, 100, 114, 111, 105, 100, 46, 103, 109, 115, 46, 97, 100, 115, 46, 77, 111, 98, 105, 108, 101, 65, 100, 115});
    private static final List<Integer> z = CollectionsKt.listOf((Object[]) new Integer[]{99, 111, 109, 46, 102, 97, 99, 101, 98, 111, 111, 107, 46, 97, 100, 115, 46, 65, 100});
    private String packedJS;

    public JsUnpacker(String str) {
        this.packedJS = str;
    }

    public final boolean detect() {
        String str = this.packedJS;
        Intrinsics.checkNotNull(str);
        return new Regex("eval\\(function\\(p,a,c,k,e,[rd]").containsMatchIn(StringsKt.replace$default(str, Stream.ID_UNKNOWN, "", false, 4, (Object) null));
    }

    public final String unpack() {
        int iIntValue;
        String str = this.packedJS;
        if (str == null) {
            return null;
        }
        try {
            int length = 0;
            MatchResult matchResultFind$default = Regex.find$default(new Regex("(?s)\\}\\s*\\('(.*)',\\s*(.*?),\\s*(\\d+),\\s*'(.*?)'\\.split\\('\\|'\\)"), str, 0, 2, null);
            if (matchResultFind$default != null && matchResultFind$default.getGroupValues().size() == 5) {
                String strReplace$default = StringsKt.replace$default(matchResultFind$default.getGroupValues().get(1), "\\'", "'", false, 4, (Object) null);
                String str2 = matchResultFind$default.getGroupValues().get(2);
                String str3 = matchResultFind$default.getGroupValues().get(3);
                String[] strArr = (String[]) StringsKt.split$default((CharSequence) matchResultFind$default.getGroupValues().get(4), new String[]{"|"}, false, 0, 6, (Object) null).toArray(new String[0]);
                int iIntValue2 = 36;
                try {
                    Integer intOrNull = StringsKt.toIntOrNull(str2);
                    if (intOrNull != null) {
                        iIntValue2 = intOrNull.intValue();
                    }
                } catch (Exception unused) {
                }
                try {
                    Integer intOrNull2 = StringsKt.toIntOrNull(str3);
                    iIntValue = intOrNull2 != null ? intOrNull2.intValue() : 0;
                } catch (Exception unused2) {
                }
                if (strArr.length != iIntValue) {
                    throw new Exception("Unknown p.a.c.k.e.r. encoding");
                }
                Unbase unbase = new Unbase(iIntValue2);
                Regex regex = new Regex("\\b[a-zA-Z0-9_]+\\b");
                StringBuilder sb = new StringBuilder(strReplace$default);
                for (MatchResult matchResult : Regex.findAll$default(regex, strReplace$default, 0, 2, null)) {
                    String value = matchResult.getValue();
                    int iUnbase = unbase.unbase(value);
                    String str4 = (iUnbase < 0 || iUnbase >= strArr.length) ? null : strArr[iUnbase];
                    String str5 = str4;
                    if (str5 != null && str5.length() != 0) {
                        Intrinsics.checkNotNullExpressionValue(sb.replace(matchResult.getRange().getFirst() + length, matchResult.getRange().getLast() + 1 + length, str4), "replace(...)");
                        length += str4.length() - value.length();
                    }
                }
                return sb.toString();
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        return null;
    }

    /* JADX INFO: compiled from: JsUnpacker.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\n\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bj\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0003\u0018\u0001`\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/JsUnpacker$Unbase;", "", "radix", "", "<init>", "(Lcom/lagradost/cloudstream3/utils/JsUnpacker;I)V", "ALPHABET_62", "", "ALPHABET_95", "alphabet", "dictionary", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "unbase", "str", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private final class Unbase {
        private final String ALPHABET_62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private final String ALPHABET_95 = " !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        private String alphabet;
        private HashMap<String, Integer> dictionary;
        private final int radix;

        public Unbase(int i) {
            this.radix = i;
            if (i > 36) {
                int i2 = 0;
                if (i < 62) {
                    String strSubstring = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(0, i);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    this.alphabet = strSubstring;
                } else if (63 <= i && i < 95) {
                    String strSubstring2 = " !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".substring(0, i);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                    this.alphabet = strSubstring2;
                } else if (i == 62) {
                    this.alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                } else if (i == 95) {
                    this.alphabet = " !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
                }
                this.dictionary = new HashMap<>(95);
                String str = this.alphabet;
                Intrinsics.checkNotNull(str);
                int length = str.length();
                while (i2 < length) {
                    HashMap<String, Integer> map = this.dictionary;
                    Intrinsics.checkNotNull(map);
                    String str2 = this.alphabet;
                    Intrinsics.checkNotNull(str2);
                    int i3 = i2 + 1;
                    String strSubstring3 = str2.substring(i2, i3);
                    Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                    map.put(strSubstring3, Integer.valueOf(i2));
                    i2 = i3;
                }
            }
        }

        public final int unbase(String str) {
            Intrinsics.checkNotNullParameter(str, "str");
            if (this.alphabet == null) {
                return Integer.parseInt(str, CharsKt.checkRadix(this.radix));
            }
            String string = new StringBuilder(str).reverse().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            int length = string.length();
            int i = 0;
            int iDoubleValue = 0;
            while (i < length) {
                double dPow = Math.pow(this.radix, i);
                HashMap<String, Integer> map = this.dictionary;
                Intrinsics.checkNotNull(map);
                int i2 = i + 1;
                String strSubstring = string.substring(i, i2);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                Integer num = map.get(strSubstring);
                Intrinsics.checkNotNull(num);
                iDoubleValue += (int) (dPow * num.doubleValue());
                i = i2;
            }
            return iDoubleValue;
        }
    }

    /* JADX INFO: compiled from: JsUnpacker.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u000b\u001a\u0004\u0018\u00010\f*\u00020\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/utils/JsUnpacker$Companion;", "", "<init>", "()V", "c", "", "", "getC", "()Ljava/util/List;", "z", "getZ", "load", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<Integer> getC() {
            return JsUnpacker.c;
        }

        public final List<Integer> getZ() {
            return JsUnpacker.z;
        }

        public final String load(String str) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            try {
                try {
                    int size = getC().size();
                    for (int i = 0; i < size; i++) {
                        str = getC().get(i % 4).intValue() > 270 ? str + getC().get(i % 3).intValue() : str + ((char) getC().get(i).intValue());
                    }
                    String strSubstring = str.substring(str.length() - getC().size(), str.length());
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    return Class.forName(strSubstring).getName();
                } catch (Exception unused) {
                    String strValueOf = String.valueOf((char) getC().get(2).intValue());
                    int size2 = getZ().size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        strValueOf = strValueOf + ((char) getZ().get(i2).intValue());
                    }
                    String strSubstring2 = strValueOf.substring(1, strValueOf.length());
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                    return Class.forName(strSubstring2).getName();
                }
            } catch (Exception unused2) {
                return null;
            }
        }
    }
}
