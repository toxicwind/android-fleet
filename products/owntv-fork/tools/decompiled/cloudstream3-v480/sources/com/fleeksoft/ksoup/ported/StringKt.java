package com.fleeksoft.ksoup.ported;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: String.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¨\u0006\b"}, d2 = {"buildString", "", "Lkotlin/String$Companion;", "charArray", "", TypedValues.CycleType.S_WAVE_OFFSET, "", "length", "ksoup_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class StringKt {
    public static final String buildString(StringCompanionObject stringCompanionObject, char[] charArray, int i, int i2) {
        Intrinsics.checkNotNullParameter(stringCompanionObject, "<this>");
        Intrinsics.checkNotNullParameter(charArray, "charArray");
        return StringsKt.concatToString(charArray, i, i2 + i);
    }
}
