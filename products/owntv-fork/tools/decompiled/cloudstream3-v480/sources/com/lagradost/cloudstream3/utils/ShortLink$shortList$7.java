package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* JADX INFO: compiled from: UnshortenUrl.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
final /* synthetic */ class ShortLink$shortList$7 extends AdaptedFunctionReference implements Function2<String, Continuation<? super String>, Object>, SuspendFunction {
    ShortLink$shortList$7(Object obj) {
        super(2, obj, ShortLink.class, "unshortenDavisonbarker", "unshortenDavisonbarker(Ljava/lang/String;)Ljava/lang/String;", 4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(String str, Continuation<? super String> continuation) {
        return ShortLink.shortList$unshortenDavisonbarker((ShortLink) this.receiver, str, continuation);
    }
}
