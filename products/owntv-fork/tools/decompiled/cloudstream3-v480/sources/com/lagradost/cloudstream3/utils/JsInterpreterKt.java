package com.lagradost.cloudstream3.utils;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\u001a\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007\u001a9\u0010\t\u001a\u00020\n2)\b\u0002\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0012\u0004\u0018\u00010\b0\f¢\u0006\u0002\b\u000fH\u0087@¢\u0006\u0002\u0010\u0010\u001a:\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0004H\u0087@¢\u0006\u0004\b\u0016\u0010\u0017\u001aE\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u00042\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002\u001a\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020 H\u0002\u001a\u0010\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u0006H\u0002\u001a\u0012\u0010&\u001a\u00020'2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002\u001a\u0012\u0010(\u001a\u00020\u00062\b\u0010)\u001a\u0004\u0018\u00010\bH\u0002\u001a\u0012\u0010*\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002\u001a\u001c\u0010+\u001a\u00020'2\b\u0010,\u001a\u0004\u0018\u00010\b2\b\u0010-\u001a\u0004\u0018\u00010\bH\u0002\u001a\u001c\u0010.\u001a\u00020'2\b\u0010,\u001a\u0004\u0018\u00010\b2\b\u0010-\u001a\u0004\u0018\u00010\bH\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"JS_DEFAULT_MAX_EXECUTION_TIME", "Lkotlin/time/Duration;", "J", "JS_DEFAULT_MAX_INSTRUCTIONS", "", "jsValueToString", "", "v", "", "newJsContext", "Lcom/lagradost/cloudstream3/utils/JsContext;", "initializer", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "evalJs", "js", "variable", "maxExecutionTime", "maxInstructions", "evalJs-1Y68eR8", "(Ljava/lang/String;Ljava/lang/String;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "evalJsInternal", "scope", "Lkotlinx/coroutines/CoroutineScope;", "evalJsInternal-1Y68eR8", "(Ljava/lang/String;Ljava/lang/String;JJLkotlinx/coroutines/CoroutineScope;)Ljava/lang/Object;", "toNumber", "", "digitValue", "", "c", "", "radix", "stringToNumber", CmcdData.STREAMING_FORMAT_SS, "toBoolean", "", "joinElement", "e", "toJsString", "strictEq", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "looseEq", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class JsInterpreterKt {
    private static final long JS_DEFAULT_MAX_EXECUTION_TIME;
    private static final long JS_DEFAULT_MAX_INSTRUCTIONS = 50000000;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.JsInterpreterKt$newJsContext$1, reason: invalid class name */
    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.JsInterpreterKt", f = "JsInterpreter.kt", i = {0, 0, 0, 0}, l = {168}, m = "newJsContext", n = {"initializer", "scope", "$this$newJsContext_u24lambda_u240", "$i$a$-apply-JsInterpreterKt$newJsContext$3"}, nl = {168}, s = {"L$0", "L$1", "L$3", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return JsInterpreterKt.newJsContext(null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int digitValue(char c, int i) {
        int i2;
        if ('0' <= c && c < ':') {
            i2 = c - 48;
        } else {
            if ('a' > c || c >= '{') {
                if ('A' <= c && c < '[') {
                    i2 = c - 55;
                }
                return -1;
            }
            i2 = c - 87;
        }
        if (i2 < i) {
            return i2;
        }
        return -1;
    }

    static {
        Duration.Companion companion = Duration.INSTANCE;
        JS_DEFAULT_MAX_EXECUTION_TIME = DurationKt.toDuration(5, DurationUnit.SECONDS);
    }

    public static final String jsValueToString(Object obj) {
        return toJsString(obj);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.JsInterpreterKt$newJsContext$2, reason: invalid class name */
    /* JADX INFO: compiled from: JsInterpreter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/JsContext;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.JsInterpreterKt$newJsContext$2", f = "JsInterpreter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<JsContext, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(JsContext jsContext, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(jsContext, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object newJsContext(Function2<? super JsContext, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super JsContext> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = anonymousClass1.I$0;
            JsContext jsContext = (JsContext) anonymousClass1.L$2;
            ResultKt.throwOnFailure(obj);
            return jsContext;
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(anonymousClass1.get$context());
        JsContext jsContext2 = new JsContext(0L, 0L, CoroutineScope, 3, null);
        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(function2);
        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(CoroutineScope);
        anonymousClass1.L$2 = jsContext2;
        anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(jsContext2);
        anonymousClass1.I$0 = 0;
        anonymousClass1.label = 1;
        return function2.invoke(jsContext2, anonymousClass1) == coroutine_suspended ? coroutine_suspended : jsContext2;
    }

    public static /* synthetic */ Object newJsContext$default(Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = new AnonymousClass2(null);
        }
        return newJsContext(function2, continuation);
    }

    /* JADX INFO: renamed from: evalJs-1Y68eR8$default, reason: not valid java name */
    public static /* synthetic */ Object m8645evalJs1Y68eR8$default(String str, String str2, long j, long j2, Continuation continuation, int i, Object obj) throws CancellationException {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            j = JS_DEFAULT_MAX_EXECUTION_TIME;
        }
        if ((i & 8) != 0) {
            j2 = JS_DEFAULT_MAX_INSTRUCTIONS;
        }
        return m8644evalJs1Y68eR8(str, str2, j, j2, continuation);
    }

    /* JADX INFO: renamed from: evalJsInternal-1Y68eR8$default, reason: not valid java name */
    public static /* synthetic */ Object m8647evalJsInternal1Y68eR8$default(String str, String str2, long j, long j2, CoroutineScope coroutineScope, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            j = JS_DEFAULT_MAX_EXECUTION_TIME;
        }
        if ((i & 8) != 0) {
            j2 = JS_DEFAULT_MAX_INSTRUCTIONS;
        }
        if ((i & 16) != 0) {
            coroutineScope = null;
        }
        return m8646evalJsInternal1Y68eR8(str, str2, j, j2, coroutineScope);
    }

    /* JADX INFO: renamed from: evalJsInternal-1Y68eR8, reason: not valid java name */
    public static final Object m8646evalJsInternal1Y68eR8(String js, String str, long j, long j2, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(js, "js");
        JsInterpreter jsInterpreter = new JsInterpreter(j, j2, coroutineScope, null);
        return str != null ? jsInterpreter.getVar(str) : jsInterpreter.eval(js);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double toNumber(Object obj) {
        if (obj == null) {
            return 0.0d;
        }
        if (obj instanceof Unit) {
            return Double.NaN;
        }
        if (obj instanceof Double) {
            return ((Number) obj).doubleValue();
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? 1.0d : 0.0d;
        }
        if (obj instanceof String) {
            return stringToNumber((String) obj);
        }
        if (obj instanceof JsList) {
            return stringToNumber(toJsString(obj));
        }
        return Double.NaN;
    }

    private static final double stringToNumber(String str) {
        long jLongValue;
        String string = StringsKt.trim((CharSequence) str).toString();
        if (string.length() == 0) {
            return 0.0d;
        }
        int iHashCode = string.hashCode();
        if (iHashCode != -173313165) {
            if (iHashCode != 237817416) {
                if (iHashCode == 506745205 && string.equals("-Infinity")) {
                    return Double.NEGATIVE_INFINITY;
                }
            } else if (string.equals("Infinity")) {
                return Double.POSITIVE_INFINITY;
            }
        } else if (string.equals("+Infinity")) {
            return Double.POSITIVE_INFINITY;
        }
        if (StringsKt.startsWith$default(string, "0x", false, 2, (Object) null) || StringsKt.startsWith$default(string, "0X", false, 2, (Object) null)) {
            Long longOrNull = StringsKt.toLongOrNull(StringsKt.drop(string, 2), 16);
            if (longOrNull == null) {
                return Double.NaN;
            }
            jLongValue = longOrNull.longValue();
        } else if (StringsKt.startsWith$default(string, "0o", false, 2, (Object) null) || StringsKt.startsWith$default(string, "0O", false, 2, (Object) null)) {
            Long longOrNull2 = StringsKt.toLongOrNull(StringsKt.drop(string, 2), 8);
            if (longOrNull2 == null) {
                return Double.NaN;
            }
            jLongValue = longOrNull2.longValue();
        } else if (StringsKt.startsWith$default(string, "0b", false, 2, (Object) null) || StringsKt.startsWith$default(string, "0B", false, 2, (Object) null)) {
            Long longOrNull3 = StringsKt.toLongOrNull(StringsKt.drop(string, 2), 2);
            if (longOrNull3 == null) {
                return Double.NaN;
            }
            jLongValue = longOrNull3.longValue();
        } else {
            Double doubleOrNull = StringsKt.toDoubleOrNull(string);
            if (doubleOrNull != null) {
                return doubleOrNull.doubleValue();
            }
            return Double.NaN;
        }
        return jLongValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean toBoolean(Object obj) {
        if (obj == null || (obj instanceof Unit)) {
            return false;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        if (obj instanceof Double) {
            return (Intrinsics.areEqual((Double) obj, 0.0d) || Double.isNaN(((Number) obj).doubleValue())) ? false : true;
        }
        if (obj instanceof String) {
            return ((CharSequence) obj).length() > 0;
        }
        if (obj instanceof JsList) {
            return true;
        }
        boolean z = obj instanceof JsObject;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String joinElement(Object obj) {
        return (obj == null || (obj instanceof Unit)) ? "" : toJsString(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toJsString(Object obj) {
        if (obj == null) {
            return AbstractJsonLexerKt.NULL;
        }
        if (obj instanceof Unit) {
            return "undefined";
        }
        if (obj instanceof Boolean) {
            return String.valueOf(((Boolean) obj).booleanValue());
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            Number number = (Number) obj;
            return (!Intrinsics.areEqual(d, Math.floor(number.doubleValue())) || Double.isInfinite(number.doubleValue())) ? String.valueOf(number.doubleValue()) : String.valueOf((long) number.doubleValue());
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof JsList) {
            return CollectionsKt.joinToString$default(((JsList) obj).getElements(), ",", null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreterKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return JsInterpreterKt.joinElement(obj2);
                }
            }, 30, null);
        }
        if (obj instanceof JsObject) {
            return "[object Object]";
        }
        if (obj instanceof NativeFn) {
            return ((NativeFn) obj).toString();
        }
        if (!(obj instanceof JsFunction)) {
            return obj.toString();
        }
        StringBuilder sb = new StringBuilder("function ");
        String name = ((JsFunction) obj).getName();
        if (name == null) {
            name = "";
        }
        sb.append(name);
        sb.append("() { [native code] }");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean strictEq(Object obj, Object obj2) {
        if ((obj instanceof Double) && (obj2 instanceof Double)) {
            return Intrinsics.areEqual((Double) obj, (Double) obj2);
        }
        if ((obj instanceof String) && (obj2 instanceof String)) {
            return Intrinsics.areEqual(obj, obj2);
        }
        if ((obj instanceof Boolean) && (obj2 instanceof Boolean)) {
            return Intrinsics.areEqual(obj, obj2);
        }
        if (obj == null && obj2 == null) {
            return true;
        }
        return ((obj instanceof Unit) && (obj2 instanceof Unit)) || obj == obj2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean looseEq(Object obj, Object obj2) {
        if (strictEq(obj, obj2)) {
            return true;
        }
        boolean z = obj == null || (obj instanceof Unit);
        boolean z2 = obj2 == null || (obj2 instanceof Unit);
        if (z || z2) {
            return z && z2;
        }
        boolean z3 = (obj instanceof Double) || (obj instanceof Boolean);
        boolean z4 = (obj2 instanceof Double) || (obj2 instanceof Boolean);
        if (z3 || z4) {
            return toNumber(obj) == toNumber(obj2);
        }
        if ((obj instanceof String) || (obj2 instanceof String)) {
            return Intrinsics.areEqual(toJsString(obj), toJsString(obj2));
        }
        return false;
    }

    /* JADX INFO: renamed from: evalJs-1Y68eR8, reason: not valid java name */
    public static final Object m8644evalJs1Y68eR8(String str, String str2, long j, long j2, Continuation<Object> continuation) throws CancellationException {
        return m8646evalJsInternal1Y68eR8(str, str2, j, j2, CoroutineScopeKt.CoroutineScope(continuation.get$context()));
    }
}
