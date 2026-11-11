package dev.whyoleg.cryptography.serialization.pem;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.io.encoding.Base64;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequenceScope;
import kotlinx.io.bytestring.Base64Kt;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.ByteStringKt;

/* JADX INFO: compiled from: PemDocument.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ldev/whyoleg/cryptography/serialization/pem/PemDocument;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "dev.whyoleg.cryptography.serialization.pem.PemDocument$Companion$decodeToSequence$2", f = "PemDocument.kt", i = {0, 0}, l = {314}, m = "invokeSuspend", n = {"$this$sequence", "startIndex"}, nl = {316}, s = {"L$0", "L$1"}, v = 2)
final class PemDocument$Companion$decodeToSequence$2 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super PemDocument>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteString $bytes;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PemDocument$Companion$decodeToSequence$2(ByteString byteString, Continuation<? super PemDocument$Companion$decodeToSequence$2> continuation) {
        super(2, continuation);
        this.$bytes = byteString;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PemDocument$Companion$decodeToSequence$2 pemDocument$Companion$decodeToSequence$2 = new PemDocument$Companion$decodeToSequence$2(this.$bytes, continuation);
        pemDocument$Companion$decodeToSequence$2.L$0 = obj;
        return pemDocument$Companion$decodeToSequence$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super PemDocument> sequenceScope, Continuation<? super Unit> continuation) {
        return ((PemDocument$Companion$decodeToSequence$2) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref.IntRef intRef;
        SequenceScope sequenceScope = (SequenceScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            intRef = new Ref.IntRef();
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            intRef = (Ref.IntRef) this.L$1;
            ResultKt.throwOnFailure(obj);
        }
        while (intRef.element < this.$bytes.getSize()) {
            ByteString byteString = this.$bytes;
            int iIndexOf = ByteStringKt.indexOf(byteString, PemDocumentKt.BEGIN_BYTES, intRef.element);
            PemDocument pemDocument = null;
            if (iIndexOf != -1) {
                int iIndexOf2 = ByteStringKt.indexOf(byteString, (byte) 10, PemDocumentKt.BEGIN_BYTES.length + iIndexOf);
                if (iIndexOf2 != -1) {
                    int iIndexOf3 = ByteStringKt.indexOf(byteString, PemDocumentKt.SUFFIX_BYTES, PemDocumentKt.BEGIN_BYTES.length + iIndexOf);
                    if (iIndexOf3 == -1 || iIndexOf3 > iIndexOf2) {
                        PemDocumentKt.throwPemMissingBeginLabelSuffix();
                        throw new KotlinNothingValueException();
                    }
                    ByteString byteStringSubstring = byteString.substring(iIndexOf + PemDocumentKt.BEGIN_BYTES.length, iIndexOf3);
                    int iIndexOf4 = ByteStringKt.indexOf(byteString, PemDocumentKt.END_BYTES, iIndexOf2);
                    if (iIndexOf4 != -1) {
                        int iIndexOf5 = ByteStringKt.indexOf(byteString, (byte) 10, PemDocumentKt.END_BYTES.length + iIndexOf4);
                        int iIndexOf6 = ByteStringKt.indexOf(byteString, PemDocumentKt.SUFFIX_BYTES, PemDocumentKt.END_BYTES.length + iIndexOf4);
                        if (iIndexOf6 == -1 || (iIndexOf5 != -1 && iIndexOf6 > iIndexOf5)) {
                            PemDocumentKt.throwPemMissingEndLabelSuffix();
                            throw new KotlinNothingValueException();
                        }
                        ByteString byteStringSubstring2 = byteString.substring(PemDocumentKt.END_BYTES.length + iIndexOf4, iIndexOf6);
                        if (!Intrinsics.areEqual(byteStringSubstring2, byteStringSubstring)) {
                            PemDocumentKt.throwPemBeginEndLabelMismatch(ByteStringKt.decodeToString(byteStringSubstring), ByteStringKt.decodeToString(byteStringSubstring2));
                            throw new KotlinNothingValueException();
                        }
                        intRef.element = iIndexOf5 == -1 ? iIndexOf6 + PemDocumentKt.SUFFIX_BYTES.length : iIndexOf5 + 1;
                        pemDocument = new PemDocument(PemLabel.m8802constructorimpl(ByteStringKt.decodeToString(byteStringSubstring)), Base64Kt.decodeToByteString(Base64.INSTANCE.getPem(), byteString, iIndexOf2 + 1, iIndexOf4), (DefaultConstructorMarker) null);
                    } else {
                        PemDocumentKt.throwPemMissingEndLabel();
                        throw new KotlinNothingValueException();
                    }
                } else {
                    PemDocumentKt.throwPemMissingNewLineAfterBeginLabel();
                    throw new KotlinNothingValueException();
                }
            }
            if (pemDocument == null) {
                break;
            }
            this.L$0 = sequenceScope;
            this.L$1 = intRef;
            this.label = 1;
            if (sequenceScope.yield(pemDocument, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
