package com.lagradost.cloudstream3.utils;

import com.lagradost.cloudstream3.MainAPI;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TestingUtils.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.TestingUtils$getDeferredProviderTests$1$1", f = "TestingUtils.kt", i = {0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {282, 293, 301, 306}, m = "invokeSuspend", n = {"logger", "logger", "homepage", "homePageList", "searchQueries", "logger", "homepage", "homePageList", "searchQueries", "searchResults", "$this$any$iv", "element$iv", "searchResponse", "$i$f$any", "$i$a$-any-TestingUtils$getDeferredProviderTests$1$1$result$success$1", "logger", "homepage", "homePageList", "searchQueries", "searchResults", "$this$any$iv", "element$iv", "searchResponse", "loadResponse", "$i$f$any", "$i$a$-any-TestingUtils$getDeferredProviderTests$1$1$result$success$1"}, nl = {283, 294, 302, 308}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$8", "L$9", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$8", "L$9", "L$10", "I$0", "I$1"}, v = 2)
final class TestingUtils$getDeferredProviderTests$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MainAPI $api;
    final /* synthetic */ Function2<MainAPI, TestingUtils.TestResultProvider, Unit> $callback;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    TestingUtils$getDeferredProviderTests$1$1(MainAPI mainAPI, Function2<? super MainAPI, ? super TestingUtils.TestResultProvider, Unit> function2, Continuation<? super TestingUtils$getDeferredProviderTests$1$1> continuation) {
        super(2, continuation);
        this.$api = mainAPI;
        this.$callback = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TestingUtils$getDeferredProviderTests$1$1(this.$api, this.$callback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TestingUtils$getDeferredProviderTests$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:51:0x01e4 A[Catch: all -> 0x032a, TRY_LEAVE, TryCatch #1 {all -> 0x032a, blocks: (B:9:0x0042, B:64:0x02bf, B:76:0x02f3, B:77:0x0319, B:49:0x01de, B:51:0x01e4, B:19:0x00a7, B:43:0x01a4, B:45:0x01ce, B:48:0x01d9, B:22:0x00b3, B:28:0x00e9, B:30:0x00fb, B:33:0x0102, B:36:0x010c, B:37:0x0131, B:39:0x0137, B:40:0x0161, B:35:0x0108, B:25:0x00c1), top: B:85:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:54:0x0247  */
    /* JADX WARN: Code duplicated, block: B:57:0x0256  */
    /* JADX WARN: Code duplicated, block: B:58:0x0260 A[Catch: all -> 0x02ed, TryCatch #2 {all -> 0x02ed, blocks: (B:55:0x0250, B:58:0x0260, B:60:0x0269, B:67:0x02d3), top: B:86:0x0250 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x0269 A[Catch: all -> 0x02ed, TRY_LEAVE, TryCatch #2 {all -> 0x02ed, blocks: (B:55:0x0250, B:58:0x0260, B:60:0x0269, B:67:0x02d3), top: B:86:0x0250 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x02bb  */
    /* JADX WARN: Code duplicated, block: B:66:0x02ce  */
    /* JADX WARN: Code duplicated, block: B:70:0x02e5  */
    /* JADX WARN: Code duplicated, block: B:71:0x02e7  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x0256 -> B:69:0x02e3). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:63:0x02bb -> B:64:0x02bf). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:68:0x02d8 -> B:69:0x02e3). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            Method dump skipped, instruction units count: 832
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.TestingUtils$getDeferredProviderTests$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
