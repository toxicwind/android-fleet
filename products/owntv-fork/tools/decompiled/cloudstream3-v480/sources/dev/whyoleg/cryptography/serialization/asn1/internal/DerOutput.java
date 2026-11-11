package dev.whyoleg.cryptography.serialization.asn1.internal;

import dev.whyoleg.cryptography.bigint.BigInt;
import dev.whyoleg.cryptography.bigint.BigIntKt;
import dev.whyoleg.cryptography.serialization.asn1.BitArray;
import dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DerOutput.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004J\u001c\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0080\u0004J\u001c\u0010\r\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086\u0080\u0004J\u001c\u0010\u0010\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086\u0080\u0004J#\u0010\u0013\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0014H\u0086\u0080\u0004¢\u0006\u0004\b\u0015\u0010\u0016J\u001c\u0010\u0017\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\u0000H\u0086\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/internal/DerOutput;", "", "output", "Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayOutput;", "<init>", "(Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayOutput;)V", "writeNull", "", "writeInteger", "tagOverride", "Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;", ES6Iterator.VALUE_PROPERTY, "Ldev/whyoleg/cryptography/bigint/BigInt;", "writeBitString", "bits", "Ldev/whyoleg/cryptography/serialization/asn1/BitArray;", "writeOctetString", "bytes", "", "writeObjectIdentifier", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "writeObjectIdentifier-IHeWLgs", "(Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;Ljava/lang/String;)V", "writeSequence", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DerOutput {
    private final ByteArrayOutput output;

    public DerOutput(ByteArrayOutput output) {
        Intrinsics.checkNotNullParameter(output, "output");
        this.output = output;
    }

    public final void writeNull() {
        this.output.write((byte) 5);
        DerOutputKt.writeLength(this.output, 0);
    }

    public final void writeInteger(ContextSpecificTag tagOverride, BigInt value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ByteArrayOutput byteArrayOutput = this.output;
        if (tagOverride == null) {
            byteArrayOutput.write((byte) 2);
            DerOutputKt.writeBytes(byteArrayOutput, BigIntKt.encodeToByteArray(value));
            return;
        }
        byteArrayOutput.write(DerTagKt.getTag(tagOverride));
        int i = DerOutputKt.WhenMappings.$EnumSwitchMapping$0[tagOverride.type().ordinal()];
        if (i == 1) {
            DerOutputKt.writeBytes(byteArrayOutput, BigIntKt.encodeToByteArray(value));
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            ByteArrayOutput byteArrayOutput2 = new ByteArrayOutput();
            byteArrayOutput2.write((byte) 2);
            DerOutputKt.writeBytes(byteArrayOutput2, BigIntKt.encodeToByteArray(value));
            DerOutputKt.writeBytes(byteArrayOutput, byteArrayOutput2);
        }
    }

    public final void writeBitString(ContextSpecificTag tagOverride, BitArray bits) {
        Intrinsics.checkNotNullParameter(bits, "bits");
        ByteArrayOutput byteArrayOutput = this.output;
        if (tagOverride == null) {
            byteArrayOutput.write((byte) 3);
            DerOutputKt.writeLength(byteArrayOutput, bits.getByteArray().length + 1);
            byteArrayOutput.write(bits.getUnusedBits());
            byteArrayOutput.write(bits.getByteArray());
            return;
        }
        byteArrayOutput.write(DerTagKt.getTag(tagOverride));
        int i = DerOutputKt.WhenMappings.$EnumSwitchMapping$0[tagOverride.type().ordinal()];
        if (i == 1) {
            DerOutputKt.writeLength(byteArrayOutput, bits.getByteArray().length + 1);
            byteArrayOutput.write(bits.getUnusedBits());
            byteArrayOutput.write(bits.getByteArray());
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            ByteArrayOutput byteArrayOutput2 = new ByteArrayOutput();
            byteArrayOutput2.write((byte) 3);
            DerOutputKt.writeLength(byteArrayOutput2, bits.getByteArray().length + 1);
            byteArrayOutput2.write(bits.getUnusedBits());
            byteArrayOutput2.write(bits.getByteArray());
            DerOutputKt.writeBytes(byteArrayOutput, byteArrayOutput2);
        }
    }

    public final void writeOctetString(ContextSpecificTag tagOverride, byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        ByteArrayOutput byteArrayOutput = this.output;
        if (tagOverride == null) {
            byteArrayOutput.write((byte) 4);
            DerOutputKt.writeBytes(byteArrayOutput, bytes);
            return;
        }
        byteArrayOutput.write(DerTagKt.getTag(tagOverride));
        int i = DerOutputKt.WhenMappings.$EnumSwitchMapping$0[tagOverride.type().ordinal()];
        if (i == 1) {
            DerOutputKt.writeBytes(byteArrayOutput, bytes);
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            ByteArrayOutput byteArrayOutput2 = new ByteArrayOutput();
            byteArrayOutput2.write((byte) 4);
            DerOutputKt.writeBytes(byteArrayOutput2, bytes);
            DerOutputKt.writeBytes(byteArrayOutput, byteArrayOutput2);
        }
    }

    /* JADX INFO: renamed from: writeObjectIdentifier-IHeWLgs, reason: not valid java name */
    public final void m8766writeObjectIdentifierIHeWLgs(ContextSpecificTag tagOverride, String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        ByteArrayOutput byteArrayOutput = this.output;
        if (tagOverride == null) {
            byteArrayOutput.write((byte) 6);
            ByteArrayOutput byteArrayOutput2 = new ByteArrayOutput();
            DerOutputKt.writeOidElements(byteArrayOutput2, StringsKt.split$default((CharSequence) value, new String[]{"."}, false, 0, 6, (Object) null));
            DerOutputKt.writeBytes(byteArrayOutput, byteArrayOutput2);
            return;
        }
        byteArrayOutput.write(DerTagKt.getTag(tagOverride));
        int i = DerOutputKt.WhenMappings.$EnumSwitchMapping$0[tagOverride.type().ordinal()];
        if (i == 1) {
            ByteArrayOutput byteArrayOutput3 = new ByteArrayOutput();
            DerOutputKt.writeOidElements(byteArrayOutput3, StringsKt.split$default((CharSequence) value, new String[]{"."}, false, 0, 6, (Object) null));
            DerOutputKt.writeBytes(byteArrayOutput, byteArrayOutput3);
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            ByteArrayOutput byteArrayOutput4 = new ByteArrayOutput();
            byteArrayOutput4.write((byte) 6);
            ByteArrayOutput byteArrayOutput5 = new ByteArrayOutput();
            DerOutputKt.writeOidElements(byteArrayOutput5, StringsKt.split$default((CharSequence) value, new String[]{"."}, false, 0, 6, (Object) null));
            DerOutputKt.writeBytes(byteArrayOutput4, byteArrayOutput5);
            DerOutputKt.writeBytes(byteArrayOutput, byteArrayOutput4);
        }
    }

    public final void writeSequence(ContextSpecificTag tagOverride, DerOutput bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        ByteArrayOutput byteArrayOutput = this.output;
        if (tagOverride == null) {
            byteArrayOutput.write(DerTagKt.DerTag_SEQUENCE);
            DerOutputKt.writeBytes(byteArrayOutput, bytes.output);
            return;
        }
        byteArrayOutput.write(DerTagKt.getTag(tagOverride));
        int i = DerOutputKt.WhenMappings.$EnumSwitchMapping$0[tagOverride.type().ordinal()];
        if (i == 1) {
            DerOutputKt.writeBytes(byteArrayOutput, bytes.output);
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            ByteArrayOutput byteArrayOutput2 = new ByteArrayOutput();
            byteArrayOutput2.write(DerTagKt.DerTag_SEQUENCE);
            DerOutputKt.writeBytes(byteArrayOutput2, bytes.output);
            DerOutputKt.writeBytes(byteArrayOutput, byteArrayOutput2);
        }
    }
}
