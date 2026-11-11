package dev.whyoleg.cryptography.serialization.pem;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
import kotlinx.io.Source;

/* JADX INFO: compiled from: PemDocument.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ldev/whyoleg/cryptography/serialization/pem/PemDocument;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "dev.whyoleg.cryptography.serialization.pem.PemDocument$Companion$decodeToSequence$3", f = "PemDocument.kt", i = {0}, l = {371}, m = "invokeSuspend", n = {"$this$sequence"}, nl = {373}, s = {"L$0"}, v = 2)
final class PemDocument$Companion$decodeToSequence$3 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super PemDocument>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Source $source;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PemDocument$Companion$decodeToSequence$3(Source source, Continuation<? super PemDocument$Companion$decodeToSequence$3> continuation) {
        super(2, continuation);
        this.$source = source;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PemDocument$Companion$decodeToSequence$3 pemDocument$Companion$decodeToSequence$3 = new PemDocument$Companion$decodeToSequence$3(this.$source, continuation);
        pemDocument$Companion$decodeToSequence$3.L$0 = obj;
        return pemDocument$Companion$decodeToSequence$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super PemDocument> sequenceScope, Continuation<? super Unit> continuation) {
        return ((PemDocument$Companion$decodeToSequence$3) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        PemDocument pemDocumentTryDecodeFromSource;
        SequenceScope sequenceScope = (SequenceScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0 && i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        while (!this.$source.exhausted() && (pemDocumentTryDecodeFromSource = PemDocumentKt.tryDecodeFromSource(this.$source)) != null) {
            this.L$0 = sequenceScope;
            this.label = 1;
            if (sequenceScope.yield(pemDocumentTryDecodeFromSource, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
