package dev.whyoleg.cryptography.serialization.pem;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PemContent.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0080\u0004¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"ensurePemLabel", "Ldev/whyoleg/cryptography/serialization/pem/PemContent;", "label", "Ldev/whyoleg/cryptography/serialization/pem/PemLabel;", "ensurePemLabel-fi-TaOo", "(Ldev/whyoleg/cryptography/serialization/pem/PemContent;Ljava/lang/String;)Ldev/whyoleg/cryptography/serialization/pem/PemContent;", "cryptography-serialization-pem"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PemContentKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Migrate to `PemDocument`")
    /* JADX INFO: renamed from: ensurePemLabel-fi-TaOo, reason: not valid java name */
    public static final PemContent m8799ensurePemLabelfiTaOo(PemContent ensurePemLabel, String label) {
        Intrinsics.checkNotNullParameter(ensurePemLabel, "$this$ensurePemLabel");
        Intrinsics.checkNotNullParameter(label, "label");
        if (PemLabel.m8804equalsimpl0(ensurePemLabel.getLabel(), label)) {
            return ensurePemLabel;
        }
        throw new IllegalStateException(("Wrong PEM label, expected " + ((Object) PemLabel.m8806toStringimpl(label)) + ", actual " + ((Object) PemLabel.m8806toStringimpl(ensurePemLabel.getLabel()))).toString());
    }
}
