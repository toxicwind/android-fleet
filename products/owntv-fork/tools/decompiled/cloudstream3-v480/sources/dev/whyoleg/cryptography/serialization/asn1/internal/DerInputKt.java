package dev.whyoleg.cryptography.serialization.asn1.internal;

import androidx.exifinterface.media.ExifInterface;
import dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: DerInput.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001aH\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u00072\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00010\t¢\u0006\u0002\b\nH\u0082\u0088\u0004¢\u0006\u0002\u0010\u000b\u001a\u001a\u0010\f\u001a\u00020\r*\u00020\u00022\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0082\u0080\u0004\u001a\u000e\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0082\u0080\u0004\u001a\u000e\u0010\u0010\u001a\u00020\u0011*\u00020\u0002H\u0082\u0080\u0004\u001a\u000e\u0010\u0012\u001a\u00020\u000f*\u00020\u0002H\u0082\u0080\u0004¨\u0006\u0013"}, d2 = {"readTagWithOverride", ExifInterface.GPS_DIRECTION_TRUE, "Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayInput;", "tagOverride", "Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;", "tag", "", "Ldev/whyoleg/cryptography/serialization/asn1/internal/DerTag;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayInput;Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;BLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "readRequestedTag", "", "readLength", "", "readOidElements", "", "readOidElement", "cryptography-serialization-asn1"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DerInputKt {

    /* JADX INFO: compiled from: DerInput.kt */
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

    private static final <T> T readTagWithOverride(ByteArrayInput byteArrayInput, ContextSpecificTag contextSpecificTag, byte b, Function1<? super ByteArrayInput, ? extends T> function1) {
        if (contextSpecificTag == null) {
            readRequestedTag(byteArrayInput, b);
            return function1.invoke(byteArrayInput);
        }
        readRequestedTag(byteArrayInput, DerTagKt.getTag(contextSpecificTag));
        int i = WhenMappings.$EnumSwitchMapping$0[contextSpecificTag.type().ordinal()];
        if (i == 1) {
            return function1.invoke(byteArrayInput);
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        ByteArrayInput slice = byteArrayInput.readSlice(readLength(byteArrayInput));
        readRequestedTag(slice, b);
        return function1.invoke(slice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void readRequestedTag(ByteArrayInput byteArrayInput, byte b) {
        byte b2 = byteArrayInput.read();
        if (b2 == b) {
            return;
        }
        throw new IllegalStateException(("Requested tag '" + DerTagKt.DerTag_name(b) + "', received: '" + DerTagKt.DerTag_name(b2) + '\'').toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int readLength(ByteArrayInput byteArrayInput) {
        byte b = byteArrayInput.read();
        if (b >= 0) {
            return b;
        }
        int i = b & 127;
        if (i > 4) {
            throw new IllegalStateException(("Supported number of bytes for tag length are in range 1..4, but was: " + i + ' ').toString());
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = (i2 << 8) + (byteArrayInput.read() & 255);
        }
        if (i2 > 0) {
            return i2;
        }
        throw new IllegalStateException(("length overflow: " + i2).toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String readOidElements(ByteArrayInput byteArrayInput) {
        StringBuilder sb = new StringBuilder();
        int oidElement = readOidElement(byteArrayInput);
        if (oidElement < 40) {
            sb.append("0.");
            sb.append(oidElement);
        } else if (oidElement < 80) {
            sb.append("1.");
            sb.append(oidElement - 40);
        } else {
            sb.append("2.");
            sb.append(oidElement - 80);
        }
        while (!byteArrayInput.getEof()) {
            sb.append('.');
            sb.append(readOidElement(byteArrayInput));
        }
        return sb.toString();
    }

    private static final int readOidElement(ByteArrayInput byteArrayInput) {
        byte b;
        int i = 0;
        do {
            b = byteArrayInput.read();
            i = (i << 7) + (b & 127);
        } while ((b & 128) == 128);
        if (i >= 0) {
            return i;
        }
        throw new IllegalStateException(("element overflow: " + i).toString());
    }
}
