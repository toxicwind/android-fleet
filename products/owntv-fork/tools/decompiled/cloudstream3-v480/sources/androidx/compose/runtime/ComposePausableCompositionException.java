package androidx.compose.runtime;

import androidx.collection.IntList;
import androidx.collection.ObjectList;
import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PausableComposition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002BA\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/ComposePausableCompositionException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "instances", "Landroidx/collection/ObjectList;", "", "reused", "operations", "Landroidx/collection/IntList;", "lastOperation", "", "cause", "", "<init>", "(Landroidx/collection/ObjectList;Landroidx/collection/ObjectList;Landroidx/collection/IntList;ILjava/lang/Throwable;)V", "operationsSequence", "Lkotlin/sequences/Sequence;", "", ContentType.Message.TYPE, "getMessage$annotations", "()V", "getMessage", "()Ljava/lang/String;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ComposePausableCompositionException extends Exception {
    private final ObjectList<Object> instances;
    private final int lastOperation;
    private final IntList operations;
    private final ObjectList<Object> reused;

    public static /* synthetic */ void getMessage$annotations() {
    }

    public ComposePausableCompositionException(ObjectList<Object> objectList, ObjectList<Object> objectList2, IntList intList, int i, Throwable th) {
        super(th);
        this.instances = objectList;
        this.reused = objectList2;
        this.operations = intList;
        this.lastOperation = i;
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.ComposePausableCompositionException$operationsSequence$1, reason: invalid class name */
    /* JADX INFO: compiled from: PausableComposition.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.ComposePausableCompositionException$operationsSequence$1", f = "PausableComposition.kt", i = {0, 0, 0, 0}, l = {554}, m = "invokeSuspend", n = {"$this$sequence", "currentOperation", "currentInstance", "currentReused"}, s = {"L$0", "I$0", "I$1", "I$2"})
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = ComposePausableCompositionException.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super String> sequenceScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int i;
            SequenceScope sequenceScope;
            int i2;
            int i3;
            String str;
            int i4;
            int i5;
            String str2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i6 = this.label;
            if (i6 == 0) {
                ResultKt.throwOnFailure(obj);
                i = 0;
                sequenceScope = (SequenceScope) this.L$0;
                i2 = 0;
                i3 = 0;
            } else {
                if (i6 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i7 = this.I$2;
                int i8 = this.I$1;
                int i9 = this.I$0;
                sequenceScope = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                i2 = i8;
                i3 = i7;
                i = i9;
            }
            while (i < Math.min(ComposePausableCompositionException.this.lastOperation, ComposePausableCompositionException.this.operations._size)) {
                int i10 = i + 1;
                int i11 = ComposePausableCompositionException.this.operations.get(i);
                switch (i11) {
                    case 0:
                        str = "up";
                        break;
                    case 1:
                        int i12 = i2 + 1;
                        str = "down " + ComposePausableCompositionException.this.instances.get(i2);
                        i2 = i12;
                        break;
                    case 2:
                        str = "remove " + ComposePausableCompositionException.this.operations.get(i10) + ' ' + ComposePausableCompositionException.this.operations.get(i + 2);
                        i10 = i + 3;
                        break;
                    case 3:
                        str = "move " + ComposePausableCompositionException.this.operations.get(i10) + ' ' + ComposePausableCompositionException.this.operations.get(i + 2) + ' ' + ComposePausableCompositionException.this.operations.get(i + 3);
                        i10 = i + 4;
                        break;
                    case 4:
                        str = "clear";
                        break;
                    case 5:
                        i4 = i + 2;
                        i5 = i2 + 1;
                        str2 = "insertBottomUp " + ComposePausableCompositionException.this.operations.get(i10) + ' ' + ComposePausableCompositionException.this.instances.get(i2);
                        str = str2;
                        i10 = i4;
                        i2 = i5;
                        break;
                    case 6:
                        i4 = i + 2;
                        i5 = i2 + 1;
                        str2 = "insertTopDown " + ComposePausableCompositionException.this.operations.get(i10) + ' ' + ComposePausableCompositionException.this.instances.get(i2);
                        str = str2;
                        i10 = i4;
                        i2 = i5;
                        break;
                    case 7:
                        int i13 = i2 + 1;
                        Object obj2 = ComposePausableCompositionException.this.instances.get(i2);
                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>");
                        i2 += 2;
                        str = "apply " + ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj2, 2)) + ' ' + ComposePausableCompositionException.this.instances.get(i13);
                        break;
                    case 8:
                        str = "reuse " + ComposePausableCompositionException.this.reused.get(i3);
                        i3++;
                        break;
                    default:
                        str = "unknown op: " + i11;
                        break;
                }
                this.L$0 = sequenceScope;
                this.I$0 = i10;
                this.I$1 = i2;
                this.I$2 = i3;
                this.label = 1;
                if (sequenceScope.yield(i + ": " + str, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i = i10;
            }
            return Unit.INSTANCE;
        }
    }

    private final Sequence<String> operationsSequence() {
        return SequencesKt.sequence(new AnonymousClass1(null));
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return StringsKt.trimMargin$default("\n            |Exception while applying pausable composition. Last 10 operations:\n            |" + CollectionsKt.joinToString$default(CollectionsKt.takeLast(SequencesKt.toList(operationsSequence()), 10), "\n", null, null, 0, null, null, 62, null) + "\n            ", null, 1, null);
    }
}
