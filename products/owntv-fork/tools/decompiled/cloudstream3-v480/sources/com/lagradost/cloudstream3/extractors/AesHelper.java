package com.lagradost.cloudstream3.extractors;

import dev.whyoleg.cryptography.CryptographyProvider;
import dev.whyoleg.cryptography.algorithms.AES;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VidStack.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007J\f\u0010\u000b\u001a\u00020\f*\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/AesHelper;", "", "<init>", "()V", "aesCbc", "Ldev/whyoleg/cryptography/algorithms/AES$CBC;", "decryptAES", "", "inputHex", "key", "iv", "hexToByteArray", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AesHelper {
    public static final AesHelper INSTANCE = new AesHelper();
    private static final AES.CBC aesCbc = (AES.CBC) CryptographyProvider.INSTANCE.getDefault().get(AES.CBC.INSTANCE);

    private AesHelper() {
    }

    public final String decryptAES(String inputHex, String key, String iv) {
        Intrinsics.checkNotNullParameter(inputHex, "inputHex");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(iv, "iv");
        byte[] bArrEncodeToByteArray = StringsKt.encodeToByteArray(key);
        return StringsKt.decodeToString(aesCbc.keyDecoder().decodeFromByteArrayBlocking(AES.Key.Format.RAW, bArrEncodeToByteArray).cipher(true).decryptWithIvBlocking(StringsKt.encodeToByteArray(iv), hexToByteArray(inputHex)));
    }

    private final byte[] hexToByteArray(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalStateException("Hex string must have an even length".toString());
        }
        List<String> listChunked = StringsKt.chunked(str, 2);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listChunked, 10));
        Iterator<T> it = listChunked.iterator();
        while (it.hasNext()) {
            arrayList.add(Byte.valueOf((byte) Integer.parseInt((String) it.next(), CharsKt.checkRadix(16))));
        }
        return CollectionsKt.toByteArray(arrayList);
    }
}
