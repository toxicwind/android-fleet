package dev.whyoleg.cryptography.serialization.asn1.internal;

import dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.UStringsKt;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: DerTag.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u0016\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0001j\u0002`\u0003H\u0080\u0080\u0004\u001a\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0081\u0080\u0004\u001a\u0016\u0010\u0015\u001a\u00020\u0016*\u00020\n2\u0006\u0010\u0017\u001a\u00020\nH\u0082\u0080\u0004\"\u0013\u0010\u0002\u001a\u00060\u0001j\u0002`\u0003X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u0013\u0010\u0004\u001a\u00060\u0001j\u0002`\u0003X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u0013\u0010\u0005\u001a\u00060\u0001j\u0002`\u0003X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u0013\u0010\u0006\u001a\u00060\u0001j\u0002`\u0003X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u0013\u0010\u0007\u001a\u00060\u0001j\u0002`\u0003X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u0013\u0010\b\u001a\u00060\u0001j\u0002`\u0003X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\t\u001a\u00020\nX\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u000b\u001a\u00020\nX\u0082Ô\b¢\u0006\u0002\n\u0000\"\u001d\u0010\u000e\u001a\u00060\u0001j\u0002`\u0003*\u00020\u00108@X\u0080\u0084\b¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u0018"}, d2 = {"DerTag", "", "DerTag_INTEGER", "Ldev/whyoleg/cryptography/serialization/asn1/internal/DerTag;", "DerTag_BIT_STRING", "DerTag_OCTET_STRING", "DerTag_NULL", "DerTag_OID", "DerTag_SEQUENCE", "DerTag_CLASS_CONTEXT_SPECIFIC", "", "DerTag_FORM_CONSTRUCTED", "DerTag_name", "", "tag", "getElementContextSpecificTag", "Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "index", "getTag", "(Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;)B", "hasFlag", "", "flag", "cryptography-serialization-asn1"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DerTagKt {
    public static final byte DerTag_BIT_STRING = 3;
    private static final int DerTag_CLASS_CONTEXT_SPECIFIC = 128;
    private static final int DerTag_FORM_CONSTRUCTED = 32;
    public static final byte DerTag_INTEGER = 2;
    public static final byte DerTag_NULL = 5;
    public static final byte DerTag_OCTET_STRING = 4;
    public static final byte DerTag_OID = 6;
    public static final byte DerTag_SEQUENCE = 48;

    /* JADX INFO: compiled from: DerTag.kt */
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

    private static final boolean hasFlag(int i, int i2) {
        return (i & i2) == i2;
    }

    public static final String DerTag_name(byte b) {
        if (b == 2) {
            return "INTEGER";
        }
        if (b == 3) {
            return "BIT_STRING";
        }
        if (b == 4) {
            return "OCTET_STRING";
        }
        if (b == 5) {
            return "NULL";
        }
        if (b == 6) {
            return "OID";
        }
        if (b == 48) {
            return "SEQUENCE";
        }
        if (hasFlag(b, 128)) {
            if (hasFlag(b, 32)) {
                return "CONTEXT_SPECIFIC:EXPLICIT[" + ((Object) UByte.m8998toStringimpl(UByte.m8954constructorimpl((byte) (b - 160)))) + AbstractJsonLexerKt.END_LIST;
            }
            return "CONTEXT_SPECIFIC:IMPLICIT[" + ((Object) UByte.m8998toStringimpl(UByte.m8954constructorimpl((byte) (b - 128)))) + AbstractJsonLexerKt.END_LIST;
        }
        return "UNKNOWN[0x" + UStringsKt.m10303toStringLxnNnR4(UByte.m8954constructorimpl(b), 16) + AbstractJsonLexerKt.END_LIST;
    }

    @ExperimentalSerializationApi
    public static final ContextSpecificTag getElementContextSpecificTag(SerialDescriptor serialDescriptor, int i) {
        Object next;
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Iterator<T> it = serialDescriptor.getElementAnnotations(i).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(((Annotation) next) instanceof ContextSpecificTag));
        ContextSpecificTag contextSpecificTag = next instanceof ContextSpecificTag ? (ContextSpecificTag) next : null;
        if (contextSpecificTag == null) {
            return null;
        }
        if (contextSpecificTag.classIndex() < 31) {
            return contextSpecificTag;
        }
        throw new IllegalStateException(("Context specific tag class must be less than 31, but was " + ((int) contextSpecificTag.classIndex())).toString());
    }

    public static final byte getTag(ContextSpecificTag contextSpecificTag) {
        Intrinsics.checkNotNullParameter(contextSpecificTag, "<this>");
        byte bClassIndex = contextSpecificTag.classIndex();
        int i = bClassIndex | 128;
        int i2 = WhenMappings.$EnumSwitchMapping$0[contextSpecificTag.type().ordinal()];
        if (i2 == 1) {
            return (byte) i;
        }
        if (i2 == 2) {
            return (byte) (bClassIndex | 160);
        }
        throw new NoWhenBranchMatchedException();
    }
}
