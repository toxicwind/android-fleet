package com.fleeksoft.ksoup.io;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SourceReaderExt.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {TypedValues.TransitionType.S_FROM, "Lcom/fleeksoft/ksoup/io/SourceReader;", "Lcom/fleeksoft/ksoup/io/SourceReader$Companion;", "byteArray", "", "ksoup_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SourceReaderExtKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "SourceReader.Companion.from(byteArray) is deprecated, use byteArray.inputStream() instead.", replaceWith = @ReplaceWith(expression = "byteArray.inputStream()", imports = {"com.fleeksoft.io.inputStream"}))
    public static final SourceReader from(SourceReader.Companion companion, byte[] byteArray) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        return new SourceReaderByteArray(byteArray);
    }
}
