package io.ktor.util;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: CryptoJvm.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.util.CryptoKt__CryptoJvmKt", f = "CryptoJvm.kt", i = {0, 0, 0, 0}, l = {110}, m = "generateNonceLong$CryptoKt__CryptoJvmKt", n = {"initial", "$this$generateNonceLong_u24lambda_u240", "length", "$i$a$-buildString-CryptoKt__CryptoJvmKt$generateNonceLong$2"}, s = {"L$0", "L$2", "I$0", "I$1"}, v = 1)
final class CryptoKt__CryptoJvmKt$generateNonceLong$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    CryptoKt__CryptoJvmKt$generateNonceLong$1(Continuation<? super CryptoKt__CryptoJvmKt$generateNonceLong$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CryptoKt__CryptoJvmKt.generateNonceLong$CryptoKt__CryptoJvmKt(null, 0, this);
    }
}
