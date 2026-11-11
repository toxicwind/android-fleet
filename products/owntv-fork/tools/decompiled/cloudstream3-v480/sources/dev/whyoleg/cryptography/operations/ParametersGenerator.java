package dev.whyoleg.cryptography.operations;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: ParametersGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u000f\u0010\u0003\u001a\u00028\u0000H\u0096À\u0004¢\u0006\u0002\u0010\u0004J\u000f\u0010\u0005\u001a\u00028\u0000H¦\u0080\u0004¢\u0006\u0002\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Ldev/whyoleg/cryptography/operations/ParametersGenerator;", "P", "", "generateParameters", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateParametersBlocking", "()Ljava/lang/Object;", "cryptography-core"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface ParametersGenerator<P> {
    Object generateParameters(Continuation<? super P> continuation);

    P generateParametersBlocking();

    /* JADX INFO: renamed from: dev.whyoleg.cryptography.operations.ParametersGenerator$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ParametersGenerator.kt */
    public final /* synthetic */ class CC {
        public static /* synthetic */ <P> Object generateParameters$suspendImpl(ParametersGenerator<P> parametersGenerator, Continuation<? super P> continuation) {
            return parametersGenerator.generateParametersBlocking();
        }
    }
}
