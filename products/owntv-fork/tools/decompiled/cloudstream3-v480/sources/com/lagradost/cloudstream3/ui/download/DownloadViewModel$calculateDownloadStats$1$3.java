package com.lagradost.cloudstream3.ui.download;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: DownloadViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class DownloadViewModel$calculateDownloadStats$1$3 extends FunctionReferenceImpl implements Function2<Integer, Integer, Integer> {
    public static final DownloadViewModel$calculateDownloadStats$1$3 INSTANCE = new DownloadViewModel$calculateDownloadStats$1$3();

    DownloadViewModel$calculateDownloadStats$1$3() {
        super(2, Integer.TYPE, "plus", "plus(I)I", 0);
    }

    public final Integer invoke(int i, int i2) {
        return Integer.valueOf(i + i2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Integer invoke(Integer num, Integer num2) {
        return invoke(num.intValue(), num2.intValue());
    }
}
