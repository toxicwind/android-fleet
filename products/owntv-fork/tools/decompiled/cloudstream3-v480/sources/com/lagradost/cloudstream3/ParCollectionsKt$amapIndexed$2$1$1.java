package com.lagradost.cloudstream3;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Add missing generic type declarations: [B] */
/* JADX INFO: compiled from: ParCollections.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "B", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ParCollectionsKt$amapIndexed$2$1$1", f = "ParCollections.kt", i = {}, l = {76}, m = "invokeSuspend", n = {}, nl = {-1}, s = {}, v = 2)
final class ParCollectionsKt$amapIndexed$2$1$1<B> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super B>, Object> {
    final /* synthetic */ A $a;
    final /* synthetic */ Function3<Integer, A, Continuation<? super B>, Object> $f;
    final /* synthetic */ int $index;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ParCollectionsKt$amapIndexed$2$1$1(Function3<? super Integer, ? super A, ? super Continuation<? super B>, ? extends Object> function3, int i, A a, Continuation<? super ParCollectionsKt$amapIndexed$2$1$1> continuation) {
        super(2, continuation);
        this.$f = function3;
        this.$index = i;
        this.$a = a;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ParCollectionsKt$amapIndexed$2$1$1(this.$f, this.$index, this.$a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super B> continuation) {
        return ((ParCollectionsKt$amapIndexed$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        Function3<Integer, A, Continuation<? super B>, Object> function3 = this.$f;
        Integer numBoxInt = Boxing.boxInt(this.$index);
        A a = this.$a;
        this.label = 1;
        Object objInvoke = function3.invoke(numBoxInt, a, this);
        return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
    }
}
