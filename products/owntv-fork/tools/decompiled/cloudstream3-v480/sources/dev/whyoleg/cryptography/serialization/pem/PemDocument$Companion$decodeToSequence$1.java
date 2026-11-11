package dev.whyoleg.cryptography.serialization.pem;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* JADX INFO: compiled from: PemDocument.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ldev/whyoleg/cryptography/serialization/pem/PemDocument;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "dev.whyoleg.cryptography.serialization.pem.PemDocument$Companion$decodeToSequence$1", f = "PemDocument.kt", i = {0, 0}, l = {201}, m = "invokeSuspend", n = {"$this$sequence", "startIndex"}, nl = {203}, s = {"L$0", "L$1"}, v = 2)
final class PemDocument$Companion$decodeToSequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super PemDocument>, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $text;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PemDocument$Companion$decodeToSequence$1(String str, Continuation<? super PemDocument$Companion$decodeToSequence$1> continuation) {
        super(2, continuation);
        this.$text = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PemDocument$Companion$decodeToSequence$1 pemDocument$Companion$decodeToSequence$1 = new PemDocument$Companion$decodeToSequence$1(this.$text, continuation);
        pemDocument$Companion$decodeToSequence$1.L$0 = obj;
        return pemDocument$Companion$decodeToSequence$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super PemDocument> sequenceScope, Continuation<? super Unit> continuation) {
        return ((PemDocument$Companion$decodeToSequence$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0035  */
    /* JADX WARN: Code duplicated, block: B:13:0x004a  */
    /* JADX WARN: Code duplicated, block: B:14:0x004d  */
    /* JADX WARN: Code duplicated, block: B:16:0x005a  */
    /* JADX WARN: Code duplicated, block: B:18:0x0065 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:33:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:35:0x00e5 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:45:0x010d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00e3 -> B:36:0x00e6). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            Method dump skipped, instruction units count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: dev.whyoleg.cryptography.serialization.pem.PemDocument$Companion$decodeToSequence$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
