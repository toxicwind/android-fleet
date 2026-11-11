package dev.whyoleg.cryptography.serialization.asn1.internal;

import dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: DerOutput.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\u001a=\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u00072\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\nH\u0082\u0088\u0004\u001a\u0016\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0082\u0080\u0004\u001a\u0016\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002H\u0082\u0080\u0004\u001a'\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\nH\u0082\u0088\u0004\u001a\u0016\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0082\u0080\u0004\u001a\u001c\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0082\u0080\u0004\u001a\u0016\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0010H\u0082\u0080\u0004¨\u0006\u0017"}, d2 = {"writeTagWithOverride", "", "Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayOutput;", "tagOverride", "Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;", "tag", "", "Ldev/whyoleg/cryptography/serialization/asn1/internal/DerTag;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "writeBytes", "bytes", "", "writeLength", "length", "", "writeOidElements", "elements", "", "", "writeOidElement", "element", "cryptography-serialization-asn1"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DerOutputKt {

    /* JADX INFO: compiled from: DerOutput.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContextSpecificTag.TagType.values().length];
            try {
                iArr[ContextSpecificTag.TagType.IMPLICIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ContextSpecificTag.TagType.EXPLICIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static final void writeTagWithOverride(ByteArrayOutput byteArrayOutput, ContextSpecificTag contextSpecificTag, byte b, Function1<? super ByteArrayOutput, Unit> function1) {
        if (contextSpecificTag == null) {
            byteArrayOutput.write(b);
            function1.invoke(byteArrayOutput);
            return;
        }
        byteArrayOutput.write(DerTagKt.getTag(contextSpecificTag));
        int i = WhenMappings.$EnumSwitchMapping$0[contextSpecificTag.type().ordinal()];
        if (i == 1) {
            function1.invoke(byteArrayOutput);
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            ByteArrayOutput byteArrayOutput2 = new ByteArrayOutput();
            byteArrayOutput2.write(b);
            function1.invoke(byteArrayOutput2);
            writeBytes(byteArrayOutput, byteArrayOutput2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void writeBytes(ByteArrayOutput byteArrayOutput, byte[] bArr) {
        writeLength(byteArrayOutput, bArr.length);
        byteArrayOutput.write(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void writeBytes(ByteArrayOutput byteArrayOutput, ByteArrayOutput byteArrayOutput2) {
        writeLength(byteArrayOutput, byteArrayOutput2.getPosition());
        byteArrayOutput.write(byteArrayOutput2);
    }

    private static final void writeBytes(ByteArrayOutput byteArrayOutput, Function1<? super ByteArrayOutput, Unit> function1) {
        ByteArrayOutput byteArrayOutput2 = new ByteArrayOutput();
        function1.invoke(byteArrayOutput2);
        writeBytes(byteArrayOutput, byteArrayOutput2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void writeLength(ByteArrayOutput byteArrayOutput, int i) {
        if (i < 128) {
            byteArrayOutput.write(i);
            return;
        }
        int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(i) / 8;
        int i2 = 4 - iNumberOfLeadingZeros;
        byteArrayOutput.write(i2 | 128);
        for (int i3 = 0; i3 < i2; i3++) {
            byteArrayOutput.write(i >>> (((3 - iNumberOfLeadingZeros) - i3) * 8));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void writeOidElements(ByteArrayOutput byteArrayOutput, List<String> list) {
        if (list.size() < 2) {
            throw new IllegalStateException(("at least 2 components expected but was " + list.size()).toString());
        }
        writeOidElement(byteArrayOutput, (writeOidElements$element(list, 0) * 40) + writeOidElements$element(list, 1));
        int size = list.size() - 2;
        for (int i = 0; i < size; i++) {
            writeOidElement(byteArrayOutput, writeOidElements$element(list, i + 2));
        }
    }

    private static final int writeOidElements$element(List<String> list, int i) {
        return Integer.parseInt(list.get(i));
    }

    private static final void writeOidElement(ByteArrayOutput byteArrayOutput, int i) {
        if (i < 128) {
            byteArrayOutput.write(i);
            return;
        }
        int iNumberOfLeadingZeros = (32 - Integer.numberOfLeadingZeros(i)) / 7;
        for (int i2 = 0; i2 < iNumberOfLeadingZeros; i2++) {
            int i3 = i >>> ((iNumberOfLeadingZeros - i2) * 7);
            if (i3 != 0) {
                byteArrayOutput.write((i3 & 127) | 128);
            }
        }
        byteArrayOutput.write(i & 127);
    }
}
