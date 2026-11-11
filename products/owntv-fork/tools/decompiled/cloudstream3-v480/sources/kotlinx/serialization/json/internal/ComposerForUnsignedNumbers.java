package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Composers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\u0012\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\fH\u0096\u0080\u0004J\u0012\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rH\u0096\u0080\u0004J\u0012\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000eH\u0096\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/json/internal/ComposerForUnsignedNumbers;", "Lkotlinx/serialization/json/internal/Composer;", "writer", "Lkotlinx/serialization/json/internal/InternalJsonWriter;", "forceQuoting", "", "<init>", "(Lkotlinx/serialization/json/internal/InternalJsonWriter;Z)V", "print", "", "v", "", "", "", "", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ComposerForUnsignedNumbers extends Composer {
    private final boolean forceQuoting;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerForUnsignedNumbers(InternalJsonWriter writer, boolean z) {
        super(writer);
        Intrinsics.checkNotNullParameter(writer, "writer");
        this.forceQuoting = z;
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(int v) {
        boolean z = this.forceQuoting;
        int iM9042constructorimpl = UInt.m9042constructorimpl(v);
        if (z) {
            printQuoted(UByte$$ExternalSyntheticBackport0.m9007m(iM9042constructorimpl, 10));
        } else {
            print(UByte$$ExternalSyntheticBackport0.m9007m(iM9042constructorimpl, 10));
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(long v) {
        boolean z = this.forceQuoting;
        long jM9121constructorimpl = ULong.m9121constructorimpl(v);
        if (z) {
            printQuoted(UByte$$ExternalSyntheticBackport0.m(jM9121constructorimpl, 10));
        } else {
            print(UByte$$ExternalSyntheticBackport0.m(jM9121constructorimpl, 10));
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(byte v) {
        boolean z = this.forceQuoting;
        String strM8998toStringimpl = UByte.m8998toStringimpl(UByte.m8954constructorimpl(v));
        if (z) {
            printQuoted(strM8998toStringimpl);
        } else {
            print(strM8998toStringimpl);
        }
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(short v) {
        boolean z = this.forceQuoting;
        String strM9272toStringimpl = UShort.m9272toStringimpl(UShort.m9228constructorimpl(v));
        if (z) {
            printQuoted(strM9272toStringimpl);
        } else {
            print(strM9272toStringimpl);
        }
    }
}
