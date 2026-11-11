package com.fleeksoft.ksoup.internal;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Unbaser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0080\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0003HÂ\u0003J\u0013\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0010HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/fleeksoft/ksoup/internal/Unbaser;", "", TtmlNode.RUBY_BASE, "", "<init>", "(I)V", "selector", "dict", "", "", "getDict", "()Ljava/util/Map;", "dict$delegate", "Lkotlin/Lazy;", "unbase", ES6Iterator.VALUE_PROPERTY, "", "component1", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Unbaser {
    private final int base;

    /* JADX INFO: renamed from: dict$delegate, reason: from kotlin metadata */
    private final Lazy dict;
    private final int selector;
    private static final Map<Integer, String> ALPHABET = MapsKt.mapOf(TuplesKt.to(52, "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOP"), TuplesKt.to(54, "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQR"), TuplesKt.to(62, "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"), TuplesKt.to(95, " !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~"));

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final int getBase() {
        return this.base;
    }

    public static /* synthetic */ Unbaser copy$default(Unbaser unbaser, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = unbaser.base;
        }
        return unbaser.copy(i);
    }

    public final Unbaser copy(int base) {
        return new Unbaser(base);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Unbaser) && this.base == ((Unbaser) other).base;
    }

    public int hashCode() {
        return this.base;
    }

    public String toString() {
        return "Unbaser(base=" + this.base + ")";
    }

    public Unbaser(int i) {
        this.base = i;
        int i2 = 62;
        if (i > 62) {
            i2 = 95;
        } else if (i <= 54) {
            i2 = 52;
            if (i > 52) {
                i2 = 54;
            }
        }
        this.selector = i2;
        this.dict = LazyKt.lazy(new Function0() { // from class: com.fleeksoft.ksoup.internal.Unbaser$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unbaser.dict_delegate$lambda$0(this.f$0);
            }
        });
    }

    private final Map<Character, Integer> getDict() {
        return (Map) this.dict.getValue();
    }

    static final Map dict_delegate$lambda$0(Unbaser unbaser) {
        String str = ALPHABET.get(Integer.valueOf(unbaser.selector));
        if (str == null) {
            return null;
        }
        String str2 = str;
        ArrayList arrayList = new ArrayList(str2.length());
        int i = 0;
        int i2 = 0;
        while (i < str2.length()) {
            arrayList.add(TuplesKt.to(Character.valueOf(str2.charAt(i)), Integer.valueOf(i2)));
            i++;
            i2++;
        }
        return MapsKt.toMap(arrayList);
    }

    public final int unbase(String value) {
        Integer num;
        Intrinsics.checkNotNullParameter(value, "value");
        int i = this.base;
        if (2 <= i && i < 37) {
            Integer intOrNull = StringsKt.toIntOrNull(value, i);
            if (intOrNull != null) {
                return intOrNull.intValue();
            }
            return 0;
        }
        String string = StringsKt.reversed((CharSequence) value).toString();
        int length = string.length();
        int iIntValue = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = string.charAt(i2);
            float fPow = (float) Math.pow(this.base, i2);
            Map<Character, Integer> dict = getDict();
            iIntValue += (int) (fPow * ((dict == null || (num = dict.get(Character.valueOf(cCharAt))) == null) ? 0 : num.intValue()));
        }
        return iIntValue;
    }
}
