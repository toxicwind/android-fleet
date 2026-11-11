package dev.whyoleg.cryptography.algorithms.symmetric;

import dev.whyoleg.cryptography.BinarySize;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SymmetricKeySize.kt */
/* JADX INFO: loaded from: classes7.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "Replaced by BinarySize as wrapper class is not needed", replaceWith = @ReplaceWith(expression = "BinarySize", imports = {"dev.whyoleg.cryptography.BinarySize"}))
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0011\bG\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0011"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/symmetric/SymmetricKeySize;", "", ES6Iterator.VALUE_PROPERTY, "Ldev/whyoleg/cryptography/BinarySize;", "constructor-impl", "(I)I", "getValue-XsYwlU8", "()I", "I", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
public final class SymmetricKeySize {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int value;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SymmetricKeySize m8716boximpl(int i) {
        return new SymmetricKeySize(i);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced by BinarySize as wrapper class is not needed", replaceWith = @ReplaceWith(expression = ES6Iterator.VALUE_PROPERTY, imports = {}))
    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m8717constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m8718equalsimpl(int i, Object obj) {
        return (obj instanceof SymmetricKeySize) && BinarySize.m8659equalsimpl0(i, ((SymmetricKeySize) obj).m8723unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m8719equalsimpl0(int i, int i2) {
        return BinarySize.m8659equalsimpl0(i, i2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m8720hashCodeimpl(int i) {
        return BinarySize.m8662hashCodeimpl(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m8721toStringimpl(int i) {
        return "SymmetricKeySize(value=" + ((Object) BinarySize.m8667toStringimpl(i)) + ')';
    }

    public boolean equals(Object other) {
        return m8718equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m8720hashCodeimpl(this.value);
    }

    public String toString() {
        return m8721toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m8723unboximpl() {
        return this.value;
    }

    /* JADX INFO: compiled from: SymmetricKeySize.kt */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced by AES.Key.Size as it's not really needed outside of AES", replaceWith = @ReplaceWith(expression = "AES.Key.Size", imports = {"dev.whyoleg.cryptography.algorithms.AES"}))
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\b\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0015\u0010\n\u001a\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\f"}, d2 = {"Ldev/whyoleg/cryptography/algorithms/symmetric/SymmetricKeySize$Companion;", "", "<init>", "()V", "B128", "Ldev/whyoleg/cryptography/algorithms/symmetric/SymmetricKeySize;", "getB128-5xWg6fk", "()I", "B192", "getB192-5xWg6fk", "B256", "getB256-5xWg6fk", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getB128-5xWg6fk, reason: not valid java name */
        public final int m8724getB1285xWg6fk() {
            return SymmetricKeySize.m8717constructorimpl(BinarySize.INSTANCE.m8670getBitsM1VWhS4(128));
        }

        /* JADX INFO: renamed from: getB192-5xWg6fk, reason: not valid java name */
        public final int m8725getB1925xWg6fk() {
            return SymmetricKeySize.m8717constructorimpl(BinarySize.INSTANCE.m8670getBitsM1VWhS4(192));
        }

        /* JADX INFO: renamed from: getB256-5xWg6fk, reason: not valid java name */
        public final int m8726getB2565xWg6fk() {
            return SymmetricKeySize.m8717constructorimpl(BinarySize.INSTANCE.m8670getBitsM1VWhS4(256));
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Replaced by BinarySize as wrapper class is not needed", replaceWith = @ReplaceWith(expression = ES6Iterator.VALUE_PROPERTY, imports = {}))
    private /* synthetic */ SymmetricKeySize(int i) {
        this.value = i;
    }

    /* JADX INFO: renamed from: getValue-XsYwlU8, reason: not valid java name and from getter */
    public final int getValue() {
        return this.value;
    }
}
