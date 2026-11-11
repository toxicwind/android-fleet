package com.lagradost.cloudstream3.extractors.helper;

import androidx.core.view.MotionEventCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.lagradost.cloudstream3.utils.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NineAnimeHelper.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u0016\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u0016\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005J\u0016\u0010\u000e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005J\u0018\u0010\u000f\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u000e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/helper/NineAnimeHelper;", "", "<init>", "()V", "nineAnimeKey", "", "cipherKey", "encodeVrf", "text", "mainKey", "decodeVrf", "encrypt", TvContractCompat.PARAM_INPUT, "key", "cipher", "decrypt", "encode", "decode", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NineAnimeHelper {
    public static final NineAnimeHelper INSTANCE = new NineAnimeHelper();
    private static final String cipherKey = "kMXzgyNzT3k5dYab";
    private static final String nineAnimeKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private NineAnimeHelper() {
    }

    public final String encodeVrf(String text, String mainKey) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(mainKey, "mainKey");
        return encode(encrypt(cipher(mainKey, encode(text)), nineAnimeKey));
    }

    public final String decodeVrf(String text, String mainKey) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(mainKey, "mainKey");
        return decode(cipher(mainKey, decrypt(text, nineAnimeKey)));
    }

    public final String encrypt(String input, String key) throws Exception {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(key, "key");
        String str = input;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > 255) {
                throw new Exception("illegal characters!");
            }
        }
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, str.length() - 1, 3);
        String str2 = "";
        if (progressionLastElement >= 0) {
            int i2 = 0;
            while (true) {
                int[] iArr = {-1, -1, -1, -1};
                iArr[0] = input.charAt(i2) >> 2;
                iArr[1] = (input.charAt(i2) & 3) << 4;
                int i3 = i2 + 1;
                if (input.length() > i3) {
                    iArr[1] = iArr[1] | (input.charAt(i3) >> 4);
                    iArr[2] = (input.charAt(i3) & 15) << 2;
                }
                int i4 = i2 + 2;
                if (input.length() > i4) {
                    iArr[2] = iArr[2] | (input.charAt(i4) >> 6);
                    iArr[3] = input.charAt(i4) & '?';
                }
                for (int i5 = 0; i5 < 4; i5++) {
                    int i6 = iArr[i5];
                    if (i6 == -1) {
                        str2 = str2 + '=';
                    } else if (i6 >= 0 && i6 < 64) {
                        str2 = str2 + key.charAt(i6);
                    }
                }
                if (i2 == progressionLastElement) {
                    break;
                }
                i2 += 3;
            }
        }
        return str2;
    }

    public final String cipher(String key, String text) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(text, "text");
        int[] iArr = new int[256];
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            iArr[i2] = i2;
        }
        Iterator<Integer> it = ArraysKt.getIndices(iArr).iterator();
        int iCharAt = 0;
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            iCharAt = ((iCharAt + iArr[iNextInt]) + key.charAt(iNextInt % key.length())) % 256;
            int i3 = iArr[iNextInt];
            iArr[iNextInt] = iArr[iCharAt];
            iArr[iCharAt] = i3;
        }
        IntRange indices = StringsKt.getIndices(text);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(indices, 10));
        Iterator<Integer> it2 = indices.iterator();
        int i4 = 0;
        while (it2.hasNext()) {
            int iNextInt2 = ((IntIterator) it2).nextInt();
            i = (i + 1) % 256;
            int i5 = iArr[i];
            i4 = (i4 + i5) % 256;
            iArr[i] = iArr[i4];
            iArr[i4] = i5;
            arrayList.add(Character.valueOf((char) (text.charAt(iNextInt2) ^ iArr[(iArr[i] + iArr[i4]) % 256])));
        }
        return CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null);
    }

    private final String decrypt(String input, String key) throws Exception {
        String str = input;
        String str2 = "";
        if (new Regex("[\\t\\n\\f\\r]").replace(str, "").length() % 4 == 0) {
            input = new Regex("==?$").replace(str, "");
        }
        if (input.length() % 4 != 1) {
            String str3 = input;
            if (!new Regex("[^+/0-9A-Za-z]").containsMatchIn(str3)) {
                int length = str3.length();
                int i = 0;
                int iIndexOf$default = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    iIndexOf$default = (iIndexOf$default << 6) | StringsKt.indexOf$default((CharSequence) key, input.charAt(i2), 0, false, 6, (Object) null);
                    i += 6;
                    if (24 == i) {
                        str2 = ((str2 + ((char) ((16711680 & iIndexOf$default) >> 16))) + ((char) ((iIndexOf$default & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8))) + ((char) (iIndexOf$default & 255));
                        i = 0;
                        iIndexOf$default = 0;
                    }
                }
                if (12 == i) {
                    return str2 + ((char) (iIndexOf$default >> 4));
                }
                if (18 != i) {
                    return str2;
                }
                int i3 = iIndexOf$default >> 2;
                return (str2 + ((char) ((i3 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8))) + ((char) (i3 & 255));
            }
        }
        throw new Exception("bad input");
    }

    public final String encode(String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        return StringUtils.INSTANCE.encodeUrl(input);
    }

    private final String decode(String input) {
        return StringUtils.INSTANCE.decodeUrl(input);
    }
}
