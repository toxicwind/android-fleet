package com.lagradost.cloudstream3.ui.download;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: DownloadViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class DownloadViewModel$calculateDownloadStats$1$1 extends FunctionReferenceImpl implements Function2<Long, Long, Long> {
    public static final DownloadViewModel$calculateDownloadStats$1$1 INSTANCE = new DownloadViewModel$calculateDownloadStats$1$1();

    DownloadViewModel$calculateDownloadStats$1$1() {
        super(2, Long.TYPE, "plus", "plus(J)J", 0);
    }

    public final Long invoke(long j, long j2) {
        return Long.valueOf(j + j2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Long invoke(Long l, Long l2) {
        return invoke(l.longValue(), l2.longValue());
    }
}
