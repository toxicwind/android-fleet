package com.lagradost.cloudstream3.utils;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.cast.MediaTrack;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J=\u0010\u0004\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0006*\u0002H\u00062$\b\u0001\u0010\u0007\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0007¢\u0006\u0002\u0010\u000bJH\u0010\f\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0006*\u0002H\u00062/\b\u0001\u0010\u0007\u001a)\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u0010JQ\u0010\u0011\u001a\u0004\u0018\u0001H\u0006\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0012*\u0002H\u00122/\b\u0001\u0010\u0007\u001a)\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r¢\u0006\u0002\b\u000fH\u0087@¢\u0006\u0002\u0010\u0013JO\u0010\u0014\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0012*\u0002H\u00122/\b\u0001\u0010\u0007\u001a)\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r¢\u0006\u0002\b\u000fH\u0087@¢\u0006\u0002\u0010\u0013JO\u0010\u0015\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0012*\u0002H\u00122/\b\u0001\u0010\u0007\u001a)\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\t\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r¢\u0006\u0002\b\u000fH\u0087@¢\u0006\u0002\u0010\u0013J\u0018\u0010\u0016\u001a\u00020\n2\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\n0\u0017H\u0007J-\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0019\"\u0004\b\u0000\u0010\u00062\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00060\u001b\"\u0002H\u0006H\u0007¢\u0006\u0002\u0010\u001cJ+\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00060\u001e\"\u0004\b\u0000\u0010\u00062\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00060\u001b\"\u0002H\u0006¢\u0006\u0002\u0010\u001f¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/utils/Coroutines;", "", "<init>", "()V", MediaTrack.ROLE_MAIN, "Lkotlinx/coroutines/Job;", ExifInterface.GPS_DIRECTION_TRUE, "work", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "ioSafe", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/Job;", "ioWorkSafe", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ioWork", "mainWork", "runOnMainThread", "Lkotlin/Function0;", "atomicListOf", "Lcom/lagradost/cloudstream3/utils/AtomicMutableList;", "items", "", "([Ljava/lang/Object;)Lcom/lagradost/cloudstream3/utils/AtomicMutableList;", "threadSafeListOf", "", "([Ljava/lang/Object;)Ljava/util/List;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Coroutines {
    public static final Coroutines INSTANCE = new Coroutines();

    private Coroutines() {
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Coroutines$main$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Coroutines.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.Coroutines$main$1", f = "Coroutines.kt", i = {}, l = {20}, m = "invokeSuspend", n = {}, nl = {21}, s = {}, v = 2)
    static final class C09211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ T $value;
        final /* synthetic */ Function2<T, Continuation<? super Unit>, Object> $work;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C09211(Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> function2, T t, Continuation<? super C09211> continuation) {
            super(2, continuation);
            this.$work = function2;
            this.$value = t;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09211(this.$work, this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function2<T, Continuation<? super Unit>, Object> function2 = this.$work;
                T t = this.$value;
                this.label = 1;
                if (function2.invoke(t, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final <T> Job main(T t, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> work) {
        Intrinsics.checkNotNullParameter(work, "work");
        return ArchComponentExtKt.launchSafe$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new C09211(work, t, null), 3, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Coroutines$ioSafe$1, reason: invalid class name */
    /* JADX INFO: compiled from: Coroutines.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.Coroutines$ioSafe$1", f = "Coroutines.kt", i = {0}, l = {28}, m = "invokeSuspend", n = {"$this$launchSafe"}, nl = {29}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ T $value;
        final /* synthetic */ Function3<CoroutineScope, T, Continuation<? super Unit>, Object> $work;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function3<? super CoroutineScope, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3, T t, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$work = function3;
            this.$value = t;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$work, this.$value, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function3<CoroutineScope, T, Continuation<? super Unit>, Object> function3 = this.$work;
                T t = this.$value;
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.label = 1;
                if (function3.invoke(coroutineScope, t, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final <T> Job ioSafe(T t, Function3<? super CoroutineScope, ? super T, ? super Continuation<? super Unit>, ? extends Object> work) {
        Intrinsics.checkNotNullParameter(work, "work");
        return ArchComponentExtKt.launchSafe$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(work, t, null), 3, null);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Coroutines$ioWorkSafe$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Coroutines.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.Coroutines$ioWorkSafe$2", f = "Coroutines.kt", i = {0}, l = {37}, m = "invokeSuspend", n = {"$this$withContext"}, nl = {38}, s = {"L$0"}, v = 2)
    static final class C09202<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ V $value;
        final /* synthetic */ Function3<CoroutineScope, V, Continuation<? super T>, Object> $work;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C09202(Function3<? super CoroutineScope, ? super V, ? super Continuation<? super T>, ? extends Object> function3, V v, Continuation<? super C09202> continuation) {
            super(2, continuation);
            this.$work = function3;
            this.$value = v;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09202 c09202 = new C09202(this.$work, this.$value, continuation);
            c09202.L$0 = obj;
            return c09202;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((C09202) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return obj;
                }
                ResultKt.throwOnFailure(obj);
                Function3<CoroutineScope, V, Continuation<? super T>, Object> function3 = this.$work;
                V v = this.$value;
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.label = 1;
                Object objInvoke = function3.invoke(coroutineScope, v, this);
                return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
                return null;
            }
        }
    }

    public final <T, V> Object ioWorkSafe(V v, Function3<? super CoroutineScope, ? super V, ? super Continuation<? super T>, ? extends Object> function3, Continuation<? super T> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C09202(function3, v, null), continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Coroutines$ioWork$2, reason: invalid class name */
    /* JADX INFO: compiled from: Coroutines.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.Coroutines$ioWork$2", f = "Coroutines.kt", i = {0}, l = {49}, m = "invokeSuspend", n = {"$this$withContext"}, nl = {-1}, s = {"L$0"}, v = 2)
    static final class AnonymousClass2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ V $value;
        final /* synthetic */ Function3<CoroutineScope, V, Continuation<? super T>, Object> $work;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function3<? super CoroutineScope, ? super V, ? super Continuation<? super T>, ? extends Object> function3, V v, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$work = function3;
            this.$value = v;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$work, this.$value, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
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
            Function3<CoroutineScope, V, Continuation<? super T>, Object> function3 = this.$work;
            V v = this.$value;
            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
            this.label = 1;
            Object objInvoke = function3.invoke(coroutineScope, v, this);
            return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
        }
    }

    public final <T, V> Object ioWork(V v, Function3<? super CoroutineScope, ? super V, ? super Continuation<? super T>, ? extends Object> function3, Continuation<? super T> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(function3, v, null), continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.Coroutines$mainWork$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Coroutines.kt */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.Coroutines$mainWork$2", f = "Coroutines.kt", i = {0}, l = {57}, m = "invokeSuspend", n = {"$this$withContext"}, nl = {-1}, s = {"L$0"}, v = 2)
    static final class C09222<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ V $value;
        final /* synthetic */ Function3<CoroutineScope, V, Continuation<? super T>, Object> $work;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C09222(Function3<? super CoroutineScope, ? super V, ? super Continuation<? super T>, ? extends Object> function3, V v, Continuation<? super C09222> continuation) {
            super(2, continuation);
            this.$work = function3;
            this.$value = v;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09222 c09222 = new C09222(this.$work, this.$value, continuation);
            c09222.L$0 = obj;
            return c09222;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((C09222) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
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
            Function3<CoroutineScope, V, Continuation<? super T>, Object> function3 = this.$work;
            V v = this.$value;
            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
            this.label = 1;
            Object objInvoke = function3.invoke(coroutineScope, v, this);
            return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
        }
    }

    public final <T, V> Object mainWork(V v, Function3<? super CoroutineScope, ? super V, ? super Continuation<? super T>, ? extends Object> function3, Continuation<? super T> continuation) {
        return BuildersKt.withContext(Dispatchers.getMain(), new C09222(function3, v, null), continuation);
    }

    public final void runOnMainThread(Function0<Unit> work) {
        Intrinsics.checkNotNullParameter(work, "work");
        Coroutines_androidKt.runOnMainThreadNative(work);
    }

    public final <T> AtomicMutableList<T> atomicListOf(T... items) {
        Intrinsics.checkNotNullParameter(items, "items");
        return new AtomicMutableList<>(ArraysKt.toMutableList(items));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> List<T> threadSafeListOf(T... items) {
        Intrinsics.checkNotNullParameter(items, "items");
        return atomicListOf(Arrays.copyOf(items, items.length));
    }
}
