package com.lagradost.safefile;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.hippo.unifile.UniFile;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UniFileWrapper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a!\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0003¢\u0006\u0002\u0010\u0004\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006\n"}, d2 = {"safe", ExifInterface.GPS_DIRECTION_TRUE, "apiCall", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "toFile", "Lcom/lagradost/safefile/SafeFile;", "Lcom/hippo/unifile/UniFile;", "context", "Landroid/content/Context;", "library_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UniFileWrapperKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final SafeFile toFile(UniFile uniFile, Context context) {
        return new UniFileWrapper(context, uniFile);
    }

    public static final <T> T safe(Function0<? extends T> apiCall) {
        Intrinsics.checkNotNullParameter(apiCall, "apiCall");
        try {
            return apiCall.invoke();
        } catch (Throwable th) {
            SafeFileKt.logError(th);
            return null;
        }
    }
}
