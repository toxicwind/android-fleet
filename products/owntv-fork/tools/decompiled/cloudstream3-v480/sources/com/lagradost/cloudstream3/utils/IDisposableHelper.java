package com.lagradost.cloudstream3.utils;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IDisposable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\u0004\u001a\u00020\u0005\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00062\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00050\n¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/IDisposableHelper;", "", "<init>", "()V", "using", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/lagradost/cloudstream3/utils/IDisposable;", "disposeObject", "work", "Lkotlin/Function1;", "(Lcom/lagradost/cloudstream3/utils/IDisposable;Lkotlin/jvm/functions/Function1;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class IDisposableHelper {
    public static final IDisposableHelper INSTANCE = new IDisposableHelper();

    private IDisposableHelper() {
    }

    public final <T extends IDisposable> void using(T disposeObject, Function1<? super T, Unit> work) {
        Intrinsics.checkNotNullParameter(disposeObject, "disposeObject");
        Intrinsics.checkNotNullParameter(work, "work");
        work.invoke(disposeObject);
        disposeObject.dispose();
    }
}
