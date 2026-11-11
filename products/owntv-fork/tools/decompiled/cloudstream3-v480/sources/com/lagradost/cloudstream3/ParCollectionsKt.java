package com.lagradost.cloudstream3;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_concurrentKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: ParCollections.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\u001ah\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\u0002*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u00052.\u0010\u0006\u001a*\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007H\u0086@¢\u0006\u0002\u0010\u000b\u001ag\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0004\"\u0004\b\u0002\u0010\u0002*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u00052.\u0010\u0006\u001a*\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007H\u0007¢\u0006\u0002\u0010\r\u001aN\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0001\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000f0\u00012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007H\u0086@¢\u0006\u0002\u0010\u0010\u001aM\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0001\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000f0\u00012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0007H\u0007¢\u0006\u0002\u0010\u0011\u001ab\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0001\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000f0\u000127\u0010\u0006\u001a3\b\u0001\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0013H\u0007¢\u0006\u0002\u0010\u0018\u001ac\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0001\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000f0\u000127\u0010\u0006\u001a3\b\u0001\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0013H\u0086@¢\u0006\u0002\u0010\u001a\u001a[\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\u0004\b\u0000\u0010\u00022>\u0010\u001c\u001a \u0012\u001c\b\u0001\u0012\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u001e0\u001d\"\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u001eH\u0007¢\u0006\u0002\u0010\u001f\u001a\\\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\u0004\b\u0000\u0010\u00022>\u0010\u001c\u001a \u0012\u001c\b\u0001\u0012\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u001e0\u001d\"\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\u001eH\u0086@¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"amap", "", "R", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "f", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apmap", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "B", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "(Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Ljava/util/List;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "apmapIndexed", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "index", "(Ljava/util/List;Lkotlin/jvm/functions/Function3;)Ljava/util/List;", "amapIndexed", "(Ljava/util/List;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "argamap", "transforms", "", "Lkotlin/Function1;", "([Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "runAllAsync", "([Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ParCollectionsKt {

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ParCollectionsKt$amap$2, reason: invalid class name */
    /* JADX INFO: compiled from: ParCollections.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ParCollectionsKt$amap$2", f = "ParCollections.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {15}, m = "invokeSuspend", n = {"$this$coroutineScope", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$map", "$i$f$mapTo", "$i$a$-map-ParCollectionsKt$amap$2$2"}, nl = {132}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "I$0", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass2<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends R>>, Object> {
        final /* synthetic */ Function2<Map.Entry<? extends K, ? extends V>, Continuation<? super R>, Object> $f;
        final /* synthetic */ Map<? extends K, V> $this_amap;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Map<? extends K, ? extends V> map, Function2<? super Map.Entry<? extends K, ? extends V>, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_amap = map;
            this.$f = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_amap, this.$f, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends R>> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0099  */
        /* JADX WARN: Code duplicated, block: B:17:0x00d2 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x00d3  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x00d3 -> B:19:0x00d4). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 220
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ParCollectionsKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <K, V, R> Object amap(Map<? extends K, ? extends V> map, Function2<? super Map.Entry<? extends K, ? extends V>, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super List<? extends R>> continuation) throws CancellationException {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(map, function2, null), continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ParCollectionsKt$apmap$1, reason: invalid class name */
    /* JADX INFO: compiled from: ParCollections.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ParCollectionsKt$apmap$1", f = "ParCollections.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {28}, m = "invokeSuspend", n = {"$this$runBlocking", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$map", "$i$f$mapTo", "$i$a$-map-ParCollectionsKt$apmap$1$2"}, nl = {132}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "I$0", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass1<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends R>>, Object> {
        final /* synthetic */ Function2<Map.Entry<? extends K, ? extends V>, Continuation<? super R>, Object> $f;
        final /* synthetic */ Map<? extends K, V> $this_apmap;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Map<? extends K, ? extends V> map, Function2<? super Map.Entry<? extends K, ? extends V>, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_apmap = map;
            this.$f = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_apmap, this.$f, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends R>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0096  */
        /* JADX WARN: Code duplicated, block: B:17:0x00cf A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x00d0  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x00d0 -> B:19:0x00d1). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 217
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ParCollectionsKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This blocks with runBlocking, and should not be used inside a suspended context", replaceWith = @ReplaceWith(expression = "amap(f)", imports = {"com.lagradost.cloudstream3.amap"}))
    public static final <K, V, R> List<R> apmap(Map<? extends K, ? extends V> map, Function2<? super Map.Entry<? extends K, ? extends V>, ? super Continuation<? super R>, ? extends Object> f) throws CancellationException {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(f, "f");
        return (List) BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new AnonymousClass1(map, f, null), 1, null);
    }

    /* JADX INFO: Add missing generic type declarations: [B] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ParCollectionsKt$amap$4, reason: invalid class name */
    /* JADX INFO: compiled from: ParCollections.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "B", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ParCollectionsKt$amap$4", f = "ParCollections.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {39}, m = "invokeSuspend", n = {"$this$coroutineScope", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$map", "$i$f$mapTo", "$i$a$-map-ParCollectionsKt$amap$4$2"}, nl = {128}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "I$0", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass4<B> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends B>>, Object> {
        final /* synthetic */ Function2<A, Continuation<? super B>, Object> $f;
        final /* synthetic */ List<A> $this_amap;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass4(List<? extends A> list, Function2<? super A, ? super Continuation<? super B>, ? extends Object> function2, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$this_amap = list;
            this.$f = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$this_amap, this.$f, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends B>> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0096  */
        /* JADX WARN: Code duplicated, block: B:17:0x00cf A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x00d0  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x00d0 -> B:19:0x00d1). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 217
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ParCollectionsKt.AnonymousClass4.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <A, B> Object amap(List<? extends A> list, Function2<? super A, ? super Continuation<? super B>, ? extends Object> function2, Continuation<? super List<? extends B>> continuation) throws CancellationException {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass4(list, function2, null), continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [B] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ParCollectionsKt$apmap$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ParCollections.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "B", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ParCollectionsKt$apmap$2", f = "ParCollections.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {52}, m = "invokeSuspend", n = {"$this$runBlocking", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$map", "$i$f$mapTo", "$i$a$-map-ParCollectionsKt$apmap$2$2"}, nl = {128}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "I$0", "I$1", "I$2"}, v = 2)
    static final class C07112<B> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends B>>, Object> {
        final /* synthetic */ Function2<A, Continuation<? super B>, Object> $f;
        final /* synthetic */ List<A> $this_apmap;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C07112(List<? extends A> list, Function2<? super A, ? super Continuation<? super B>, ? extends Object> function2, Continuation<? super C07112> continuation) {
            super(2, continuation);
            this.$this_apmap = list;
            this.$f = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07112 c07112 = new C07112(this.$this_apmap, this.$f, continuation);
            c07112.L$0 = obj;
            return c07112;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends B>> continuation) {
            return ((C07112) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x0093  */
        /* JADX WARN: Code duplicated, block: B:17:0x00cc A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:18:0x00cd  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x00cd -> B:19:0x00ce). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 214
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ParCollectionsKt.C07112.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This blocks with runBlocking, and should not be used inside a suspended context", replaceWith = @ReplaceWith(expression = "amap(f)", imports = {"com.lagradost.cloudstream3.amap"}))
    public static final <A, B> List<B> apmap(List<? extends A> list, Function2<? super A, ? super Continuation<? super B>, ? extends Object> f) throws CancellationException {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(f, "f");
        return (List) BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new C07112(list, f, null), 1, null);
    }

    /* JADX INFO: Add missing generic type declarations: [B] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ParCollectionsKt$apmapIndexed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ParCollections.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "B", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ParCollectionsKt$apmapIndexed$1", f = "ParCollections.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {65}, m = "invokeSuspend", n = {"$this$runBlocking", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$map", "$i$f$mapTo", "$i$a$-map-ParCollectionsKt$apmapIndexed$1$2"}, nl = {133}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "I$0", "I$1", "I$2"}, v = 2)
    static final class C07121<B> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends B>>, Object> {
        final /* synthetic */ Function3<Integer, A, Continuation<? super B>, Object> $f;
        final /* synthetic */ List<A> $this_apmapIndexed;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C07121(List<? extends A> list, Function3<? super Integer, ? super A, ? super Continuation<? super B>, ? extends Object> function3, Continuation<? super C07121> continuation) {
            super(2, continuation);
            this.$this_apmapIndexed = list;
            this.$f = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07121 c07121 = new C07121(this.$this_apmapIndexed, this.$f, continuation);
            c07121.L$0 = obj;
            return c07121;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends B>> continuation) {
            return ((C07121) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:18:0x009b  */
        /* JADX WARN: Code duplicated, block: B:20:0x00d4 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:21:0x00d5  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00d5 -> B:22:0x00d6). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instruction units count: 222
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ParCollectionsKt.C07121.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This blocks with runBlocking, and should not be used inside a suspended context", replaceWith = @ReplaceWith(expression = "amapIndexed(f)", imports = {"com.lagradost.cloudstream3.amapIndexed"}))
    public static final <A, B> List<B> apmapIndexed(List<? extends A> list, Function3<? super Integer, ? super A, ? super Continuation<? super B>, ? extends Object> f) throws CancellationException {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(f, "f");
        return (List) BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new C07121(list, f, null), 1, null);
    }

    /* JADX INFO: Add missing generic type declarations: [B] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ParCollectionsKt$amapIndexed$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ParCollections.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "B", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ParCollectionsKt$amapIndexed$2", f = "ParCollections.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {76}, m = "invokeSuspend", n = {"$this$coroutineScope", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$map", "$i$f$mapTo", "$i$a$-map-ParCollectionsKt$amapIndexed$2$2"}, nl = {133}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "I$0", "I$1", "I$2"}, v = 2)
    static final class C07102<B> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends B>>, Object> {
        final /* synthetic */ Function3<Integer, A, Continuation<? super B>, Object> $f;
        final /* synthetic */ List<A> $this_amapIndexed;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C07102(List<? extends A> list, Function3<? super Integer, ? super A, ? super Continuation<? super B>, ? extends Object> function3, Continuation<? super C07102> continuation) {
            super(2, continuation);
            this.$this_amapIndexed = list;
            this.$f = function3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07102 c07102 = new C07102(this.$this_amapIndexed, this.$f, continuation);
            c07102.L$0 = obj;
            return c07102;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends B>> continuation) {
            return ((C07102) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:18:0x009e  */
        /* JADX WARN: Code duplicated, block: B:20:0x00d7 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:21:0x00d8  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00d8 -> B:22:0x00d9). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instruction units count: 225
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ParCollectionsKt.C07102.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <A, B> Object amapIndexed(List<? extends A> list, Function3<? super Integer, ? super A, ? super Continuation<? super B>, ? extends Object> function3, Continuation<? super List<? extends B>> continuation) throws CancellationException {
        return CoroutineScopeKt.coroutineScope(new C07102(list, function3, null), continuation);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ParCollectionsKt$argamap$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ParCollections.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ParCollectionsKt$argamap$1", f = "ParCollections.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {103}, m = "invokeSuspend", n = {"$this$runBlocking", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$map", "$i$f$mapTo", "$i$a$-map-ParCollectionsKt$argamap$1$2"}, nl = {132}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "I$0", "I$1", "I$2"}, v = 2)
    static final class C07131<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends R>>, Object> {
        final /* synthetic */ Function1<Continuation<? super R>, Object>[] $transforms;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C07131(Function1<? super Continuation<? super R>, ? extends Object>[] function1Arr, Continuation<? super C07131> continuation) {
            super(2, continuation);
            this.$transforms = function1Arr;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07131 c07131 = new C07131(this.$transforms, continuation);
            c07131.L$0 = obj;
            return c07131;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends R>> continuation) {
            return ((C07131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0085  */
        /* JADX WARN: Code duplicated, block: B:16:0x00be A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:17:0x00bf  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x00bf -> B:18:0x00c0). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                java.lang.Object r0 = r12.L$0
                r1 = r0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r12.label
                r7 = 1
                r8 = 0
                if (r2 == 0) goto L3a
                if (r2 != r7) goto L32
                int r2 = r12.I$1
                int r3 = r12.I$0
                java.lang.Object r4 = r12.L$7
                java.util.Collection r4 = (java.util.Collection) r4
                java.lang.Object r5 = r12.L$6
                kotlinx.coroutines.Deferred r5 = (kotlinx.coroutines.Deferred) r5
                java.lang.Object r5 = r12.L$4
                java.util.Iterator r5 = (java.util.Iterator) r5
                java.lang.Object r6 = r12.L$3
                java.util.Collection r6 = (java.util.Collection) r6
                java.lang.Object r9 = r12.L$2
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                java.lang.Object r10 = r12.L$1
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                kotlin.ResultKt.throwOnFailure(r13)
                goto Lc0
            L32:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r13)
                throw r12
            L3a:
                kotlin.ResultKt.throwOnFailure(r13)
                kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super R>, java.lang.Object>[] r13 = r12.$transforms
                java.util.ArrayList r2 = new java.util.ArrayList
                int r3 = r13.length
                r2.<init>(r3)
                r9 = r2
                java.util.Collection r9 = (java.util.Collection) r9
                int r10 = r13.length
                r11 = r8
            L4a:
                if (r11 >= r10) goto L65
                r2 = r13[r11]
                com.lagradost.cloudstream3.ParCollectionsKt$argamap$1$1$1 r3 = new com.lagradost.cloudstream3.ParCollectionsKt$argamap$1$1$1
                r4 = 0
                r3.<init>(r2, r4)
                r4 = r3
                kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
                r5 = 3
                r6 = 0
                r2 = 0
                r3 = 0
                kotlinx.coroutines.Deferred r2 = kotlinx.coroutines.BuildersKt.async$default(r1, r2, r3, r4, r5, r6)
                r9.add(r2)
                int r11 = r11 + 1
                goto L4a
            L65:
                java.util.List r9 = (java.util.List) r9
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                java.util.ArrayList r13 = new java.util.ArrayList
                r2 = 10
                int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r9, r2)
                r13.<init>(r2)
                java.util.Collection r13 = (java.util.Collection) r13
                java.util.Iterator r2 = r9.iterator()
                r4 = r13
                r5 = r2
                r2 = r8
                r3 = r2
                r10 = r9
            L7f:
                boolean r13 = r5.hasNext()
                if (r13 == 0) goto Lc5
                java.lang.Object r13 = r5.next()
                r6 = r13
                kotlinx.coroutines.Deferred r6 = (kotlinx.coroutines.Deferred) r6
                java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
                r12.L$0 = r11
                java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
                r12.L$1 = r11
                java.lang.Object r11 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
                r12.L$2 = r11
                r12.L$3 = r4
                r12.L$4 = r5
                java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
                r12.L$5 = r13
                java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
                r12.L$6 = r13
                r12.L$7 = r4
                r12.I$0 = r3
                r12.I$1 = r2
                r12.I$2 = r8
                r12.label = r7
                java.lang.Object r13 = r6.await(r12)
                if (r13 != r0) goto Lbf
                return r0
            Lbf:
                r6 = r4
            Lc0:
                r4.add(r13)
                r4 = r6
                goto L7f
            Lc5:
                java.util.List r4 = (java.util.List) r4
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ParCollectionsKt.C07131.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This blocks with runBlocking, and should not be used inside a suspended context", replaceWith = @ReplaceWith(expression = "runAllAsync(transforms)", imports = {"com.lagradost.cloudstream3.runAllAsync"}))
    public static final <R> List<R> argamap(Function1<? super Continuation<? super R>, ? extends Object>... transforms) throws CancellationException {
        Intrinsics.checkNotNullParameter(transforms, "transforms");
        return (List) BuildersKt__Builders_concurrentKt.runBlockingK$default(null, new C07131(transforms, null), 1, null);
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ParCollectionsKt$runAllAsync$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ParCollections.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ParCollectionsKt$runAllAsync$2", f = "ParCollections.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {124}, m = "invokeSuspend", n = {"$this$coroutineScope", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "$i$f$map", "$i$f$mapTo", "$i$a$-map-ParCollectionsKt$runAllAsync$2$2"}, nl = {132}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "I$0", "I$1", "I$2"}, v = 2)
    static final class C07142<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends R>>, Object> {
        final /* synthetic */ Function1<Continuation<? super R>, Object>[] $transforms;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C07142(Function1<? super Continuation<? super R>, ? extends Object>[] function1Arr, Continuation<? super C07142> continuation) {
            super(2, continuation);
            this.$transforms = function1Arr;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07142 c07142 = new C07142(this.$transforms, continuation);
            c07142.L$0 = obj;
            return c07142;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends R>> continuation) {
            return ((C07142) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0088  */
        /* JADX WARN: Code duplicated, block: B:16:0x00c1 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:17:0x00c2  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x00c2 -> B:18:0x00c3). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 203
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ParCollectionsKt.C07142.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <R> Object runAllAsync(Function1<? super Continuation<? super R>, ? extends Object>[] function1Arr, Continuation<? super List<? extends R>> continuation) throws CancellationException {
        return CoroutineScopeKt.coroutineScope(new C07142(function1Arr, null), continuation);
    }
}
