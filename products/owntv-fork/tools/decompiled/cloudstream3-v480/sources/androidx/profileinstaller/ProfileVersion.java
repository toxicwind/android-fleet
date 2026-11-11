package androidx.profileinstaller;

import dev.whyoleg.cryptography.serialization.asn1.internal.DerTagKt;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ProfileVersion {
    public static final int MIN_SUPPORTED_SDK = 24;
    static final byte[] V015_S = {DerTagKt.DerTag_SEQUENCE, 49, 53, 0};
    static final byte[] V010_P = {DerTagKt.DerTag_SEQUENCE, 49, DerTagKt.DerTag_SEQUENCE, 0};
    static final byte[] V009_O_MR1 = {DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 57, 0};
    static final byte[] V005_O = {DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 53, 0};
    static final byte[] V001_N = {DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 49, 0};
    static final byte[] METADATA_V001_N = {DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 49, 0};
    static final byte[] METADATA_V002 = {DerTagKt.DerTag_SEQUENCE, DerTagKt.DerTag_SEQUENCE, 50, 0};

    private ProfileVersion() {
    }

    static String dexKeySeparator(byte[] bArr) {
        return (Arrays.equals(bArr, V001_N) || Arrays.equals(bArr, V005_O)) ? ":" : "!";
    }
}
