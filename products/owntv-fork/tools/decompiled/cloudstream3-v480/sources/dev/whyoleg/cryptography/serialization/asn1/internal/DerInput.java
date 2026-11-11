package dev.whyoleg.cryptography.serialization.asn1.internal;

import androidx.tvprovider.media.tv.TvContractCompat;
import dev.whyoleg.cryptography.bigint.BigInt;
import dev.whyoleg.cryptography.bigint.BigIntKt;
import dev.whyoleg.cryptography.serialization.asn1.BitArray;
import dev.whyoleg.cryptography.serialization.asn1.ContextSpecificTag;
import dev.whyoleg.cryptography.serialization.asn1.ObjectIdentifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DerInput.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\u00060\u000bj\u0002`\fH\u0086\u0080\u0004J\n\u0010\r\u001a\u00020\u0007H\u0086\u0080\u0004J\f\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0086\u0080\u0004J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0086\u0080\u0004J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0086\u0080\u0004J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0086\u0080\u0004J\u001b\u0010\u0018\u001a\u00020\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0086\u0080\u0004¢\u0006\u0004\b\u001a\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0086\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001d"}, d2 = {"Ldev/whyoleg/cryptography/serialization/asn1/internal/DerInput;", "", TvContractCompat.PARAM_INPUT, "Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayInput;", "<init>", "(Ldev/whyoleg/cryptography/serialization/asn1/internal/ByteArrayInput;)V", "eof", "", "getEof", "()Z", "peakTag", "", "Ldev/whyoleg/cryptography/serialization/asn1/internal/DerTag;", "isNotNull", "readNull", "", "readInteger", "Ldev/whyoleg/cryptography/bigint/BigInt;", "tagOverride", "Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;", "readBitString", "Ldev/whyoleg/cryptography/serialization/asn1/BitArray;", "readOctetString", "", "readObjectIdentifier", "Ldev/whyoleg/cryptography/serialization/asn1/ObjectIdentifier;", "readObjectIdentifier-iVrrli8", "(Ldev/whyoleg/cryptography/serialization/asn1/ContextSpecificTag;)Ljava/lang/String;", "readSequence", "cryptography-serialization-asn1"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DerInput {
    private final ByteArrayInput input;

    public DerInput(ByteArrayInput input) {
        Intrinsics.checkNotNullParameter(input, "input");
        this.input = input;
    }

    public final boolean getEof() {
        return this.input.getEof();
    }

    public final byte peakTag() {
        return this.input.peak();
    }

    public final boolean isNotNull() {
        return peakTag() != 5;
    }

    public final Void readNull() {
        DerInputKt.readRequestedTag(this.input, (byte) 5);
        int length = DerInputKt.readLength(this.input);
        if (length == 0) {
            return null;
        }
        throw new IllegalStateException(("NULL tag length should be zero, but was: " + length).toString());
    }

    public final BigInt readInteger(ContextSpecificTag tagOverride) {
        ByteArrayInput byteArrayInput = this.input;
        if (tagOverride == null) {
            DerInputKt.readRequestedTag(byteArrayInput, (byte) 2);
            return BigIntKt.decodeToBigInt(byteArrayInput.read(DerInputKt.readLength(byteArrayInput)));
        }
        DerInputKt.readRequestedTag(byteArrayInput, DerTagKt.getTag(tagOverride));
        int i = DerInputKt.WhenMappings.$EnumSwitchMapping$0[tagOverride.type().ordinal()];
        if (i == 1) {
            return BigIntKt.decodeToBigInt(byteArrayInput.read(DerInputKt.readLength(byteArrayInput)));
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        ByteArrayInput slice = byteArrayInput.readSlice(DerInputKt.readLength(byteArrayInput));
        DerInputKt.readRequestedTag(slice, (byte) 2);
        return BigIntKt.decodeToBigInt(slice.read(DerInputKt.readLength(slice)));
    }

    public final BitArray readBitString(ContextSpecificTag tagOverride) {
        ByteArrayInput byteArrayInput = this.input;
        if (tagOverride == null) {
            DerInputKt.readRequestedTag(byteArrayInput, (byte) 3);
            int length = DerInputKt.readLength(byteArrayInput);
            byte b = byteArrayInput.read();
            byte[] bArr = byteArrayInput.read(length - 1);
            if (bArr.length != 0) {
                int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(ArraysKt.last(bArr) | 256);
                if (b > iNumberOfTrailingZeros) {
                    throw new IllegalStateException(("Not all unused bits are zeros, expected at least " + ((int) b) + " trailing zeros, received " + iNumberOfTrailingZeros).toString());
                }
            } else if (b != 0) {
                throw new IllegalStateException(("wrong number of unused bits, expected 0, received: " + ((int) b)).toString());
            }
            return new BitArray(b, bArr);
        }
        DerInputKt.readRequestedTag(byteArrayInput, DerTagKt.getTag(tagOverride));
        int i = DerInputKt.WhenMappings.$EnumSwitchMapping$0[tagOverride.type().ordinal()];
        if (i == 1) {
            int length2 = DerInputKt.readLength(byteArrayInput);
            byte b2 = byteArrayInput.read();
            byte[] bArr2 = byteArrayInput.read(length2 - 1);
            if (bArr2.length != 0) {
                int iNumberOfTrailingZeros2 = Integer.numberOfTrailingZeros(ArraysKt.last(bArr2) | 256);
                if (b2 > iNumberOfTrailingZeros2) {
                    throw new IllegalStateException(("Not all unused bits are zeros, expected at least " + ((int) b2) + " trailing zeros, received " + iNumberOfTrailingZeros2).toString());
                }
            } else if (b2 != 0) {
                throw new IllegalStateException(("wrong number of unused bits, expected 0, received: " + ((int) b2)).toString());
            }
            return new BitArray(b2, bArr2);
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        ByteArrayInput slice = byteArrayInput.readSlice(DerInputKt.readLength(byteArrayInput));
        DerInputKt.readRequestedTag(slice, (byte) 3);
        int length3 = DerInputKt.readLength(slice);
        byte b3 = slice.read();
        byte[] bArr3 = slice.read(length3 - 1);
        if (bArr3.length != 0) {
            int iNumberOfTrailingZeros3 = Integer.numberOfTrailingZeros(ArraysKt.last(bArr3) | 256);
            if (b3 > iNumberOfTrailingZeros3) {
                throw new IllegalStateException(("Not all unused bits are zeros, expected at least " + ((int) b3) + " trailing zeros, received " + iNumberOfTrailingZeros3).toString());
            }
        } else if (b3 != 0) {
            throw new IllegalStateException(("wrong number of unused bits, expected 0, received: " + ((int) b3)).toString());
        }
        return new BitArray(b3, bArr3);
    }

    public final byte[] readOctetString(ContextSpecificTag tagOverride) {
        ByteArrayInput slice = this.input;
        if (tagOverride == null) {
            DerInputKt.readRequestedTag(slice, (byte) 4);
        } else {
            DerInputKt.readRequestedTag(slice, DerTagKt.getTag(tagOverride));
            int i = DerInputKt.WhenMappings.$EnumSwitchMapping$0[tagOverride.type().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                slice = slice.readSlice(DerInputKt.readLength(slice));
                DerInputKt.readRequestedTag(slice, (byte) 4);
            }
        }
        return slice.read(DerInputKt.readLength(slice));
    }

    /* JADX INFO: renamed from: readObjectIdentifier-iVrrli8, reason: not valid java name */
    public final String m8765readObjectIdentifieriVrrli8(ContextSpecificTag tagOverride) {
        ByteArrayInput slice = this.input;
        if (tagOverride == null) {
            DerInputKt.readRequestedTag(slice, (byte) 6);
        } else {
            DerInputKt.readRequestedTag(slice, DerTagKt.getTag(tagOverride));
            int i = DerInputKt.WhenMappings.$EnumSwitchMapping$0[tagOverride.type().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                slice = slice.readSlice(DerInputKt.readLength(slice));
                DerInputKt.readRequestedTag(slice, (byte) 6);
            }
        }
        return ObjectIdentifier.m8756constructorimpl(DerInputKt.readOidElements(slice.readSlice(DerInputKt.readLength(slice))));
    }

    public final ByteArrayInput readSequence(ContextSpecificTag tagOverride) {
        ByteArrayInput slice = this.input;
        if (tagOverride == null) {
            DerInputKt.readRequestedTag(slice, DerTagKt.DerTag_SEQUENCE);
        } else {
            DerInputKt.readRequestedTag(slice, DerTagKt.getTag(tagOverride));
            int i = DerInputKt.WhenMappings.$EnumSwitchMapping$0[tagOverride.type().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                slice = slice.readSlice(DerInputKt.readLength(slice));
                DerInputKt.readRequestedTag(slice, DerTagKt.DerTag_SEQUENCE);
            }
        }
        return slice.readSlice(DerInputKt.readLength(slice));
    }
}
