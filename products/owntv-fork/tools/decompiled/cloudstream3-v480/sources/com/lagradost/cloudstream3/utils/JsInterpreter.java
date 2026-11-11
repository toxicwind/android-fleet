package com.lagradost.cloudstream3.utils;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.time.Duration;
import kotlin.time.TimeMark;
import kotlin.time.TimeSource;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.stream.Stream;
import org.slf4j.Marker;

/* JADX INFO: compiled from: JsInterpreter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J0\u0010\u0012\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u001c\u0010\u0015\u001a\u0018\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0016H\u0002J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0019\u001a\u00020\u0014J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0019\u001a\u00020\u0014H\u0002J\b\u0010\u001b\u001a\u00020\u0011H\u0002J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0013\u001a\u00020\u0014J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001J\u001a\u0010\u001f\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0006\u001a\u00020\fH\u0002J\u001a\u0010\"\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0006\u001a\u00020\fH\u0002J\u001a\u0010#\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020$2\u0006\u0010\u0006\u001a\u00020\fH\u0002J\u001a\u0010%\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020&2\u0006\u0010\u0006\u001a\u00020\fH\u0002J\u001a\u0010'\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020(2\u0006\u0010\u0006\u001a\u00020\fH\u0002J\"\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020!2\b\u0010\u001e\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0006\u001a\u00020\fH\u0002J \u0010+\u001a\u00020\u00142\u0006\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u0006\u001a\u00020\fH\u0002J\u001a\u0010/\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u0002002\u0006\u0010\u0006\u001a\u00020\fH\u0002J\u001c\u00101\u001a\u0004\u0018\u00010\u00012\b\u00102\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u00020\u0014H\u0002J\u001a\u00104\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u0002052\u0006\u0010\u0006\u001a\u00020\fH\u0002J(\u00106\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0001072\u0006\u00108\u001a\u00020!2\u0006\u0010\u0006\u001a\u00020\fH\u0002J\u001a\u00109\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020:2\u0006\u0010\u0006\u001a\u00020\fH\u0002J.\u0010;\u001a\u0004\u0018\u00010\u00012\b\u0010<\u001a\u0004\u0018\u00010\u00012\u000e\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00172\b\u0010>\u001a\u0004\u0018\u00010\u0001H\u0002R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/lagradost/cloudstream3/utils/JsInterpreter;", "", "maxExecutionTime", "Lkotlin/time/Duration;", "maxInstructions", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(JJLkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "globalScope", "Lcom/lagradost/cloudstream3/utils/Scope;", "instructionCount", "startMark", "Lkotlin/time/TimeMark;", "installGlobals", "", "nativeFn", "name", "", "fn", "Lkotlin/Function1;", "", "eval", "code", "evalInternal", "checkBudget", "getVar", "setVar", ES6Iterator.VALUE_PROPERTY, "execNode", "node", "Lcom/lagradost/cloudstream3/utils/Node;", "evalExpr", "evalUnary", "Lcom/lagradost/cloudstream3/utils/UnaryExpr;", "evalBinary", "Lcom/lagradost/cloudstream3/utils/BinExpr;", "evalAssign", "Lcom/lagradost/cloudstream3/utils/AssignExpr;", "assignTo", TypedValues.AttributesType.S_TARGET, "propKey", "prop", "computed", "", "evalMember", "Lcom/lagradost/cloudstream3/utils/MemberExpr;", "getMember", "obj", "key", "evalCall", "Lcom/lagradost/cloudstream3/utils/CallExpr;", "resolveCallee", "Lkotlin/Pair;", "calleeNode", "evalNew", "Lcom/lagradost/cloudstream3/utils/NewExpr;", "callAny", "callee", "args", "thisVal", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class JsInterpreter {
    private final Scope globalScope;
    private long instructionCount;
    private final long maxExecutionTime;
    private final long maxInstructions;
    private final CoroutineScope scope;
    private TimeMark startMark;

    public /* synthetic */ JsInterpreter(long j, long j2, CoroutineScope coroutineScope, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, coroutineScope);
    }

    static final Object getMember$lambda$23(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return obj;
    }

    static final Object getMember$lambda$45(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        return obj;
    }

    static final Object getMember$lambda$46(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        return obj;
    }

    private JsInterpreter(long j, long j2, CoroutineScope coroutineScope) {
        this.maxExecutionTime = j;
        this.maxInstructions = j2;
        this.scope = coroutineScope;
        this.globalScope = new Scope(null, 1, null);
        this.startMark = TimeSource.Monotonic.ValueTimeMark.m10436boximpl(TimeSource.Monotonic.INSTANCE.m10435markNowz9LOYto());
        installGlobals();
    }

    public /* synthetic */ JsInterpreter(long j, long j2, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? JsInterpreterKt.JS_DEFAULT_MAX_EXECUTION_TIME : j, (i & 2) != 0 ? 50000000L : j2, (i & 4) != 0 ? null : coroutineScope, null);
    }

    private final void installGlobals() {
        this.globalScope.define("Math", new JsObject(MapsKt.mutableMapOf(TuplesKt.to("PI", Double.valueOf(3.141592653589793d)), TuplesKt.to(ExifInterface.LONGITUDE_EAST, Double.valueOf(2.718281828459045d)), TuplesKt.to("floor", nativeFn("floor", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$0((List) obj);
            }
        })), TuplesKt.to("ceil", nativeFn("ceil", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$1((List) obj);
            }
        })), TuplesKt.to("round", nativeFn("round", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$2((List) obj);
            }
        })), TuplesKt.to("abs", nativeFn("abs", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$3((List) obj);
            }
        })), TuplesKt.to("sqrt", nativeFn("sqrt", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$4((List) obj);
            }
        })), TuplesKt.to("pow", nativeFn("pow", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$5((List) obj);
            }
        })), TuplesKt.to("log", nativeFn("log", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$6((List) obj);
            }
        })), TuplesKt.to("sin", nativeFn("sin", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$7((List) obj);
            }
        })), TuplesKt.to("cos", nativeFn("cos", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$8((List) obj);
            }
        })), TuplesKt.to("tan", nativeFn("tan", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$9((List) obj);
            }
        })), TuplesKt.to("max", nativeFn("max", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$10((List) obj);
            }
        })), TuplesKt.to("min", nativeFn("min", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$11((List) obj);
            }
        })), TuplesKt.to("random", nativeFn("random", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$12((List) obj);
            }
        })), TuplesKt.to("trunc", nativeFn("trunc", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$13((List) obj);
            }
        })), TuplesKt.to("log2", nativeFn("log2", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda55
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$14((List) obj);
            }
        })), TuplesKt.to("log10", nativeFn("log10", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda66
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$15((List) obj);
            }
        }))), null, 2, null));
        this.globalScope.define("String", new JsObject(MapsKt.mutableMapOf(TuplesKt.to("fromCharCode", nativeFn("fromCharCode", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda77
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$16((List) obj);
            }
        }))), null, 2, null));
        this.globalScope.define("parseInt", nativeFn("parseInt", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda88
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$17((List) obj);
            }
        }));
        this.globalScope.define("parseFloat", nativeFn("parseFloat", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda90
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$18((List) obj);
            }
        }));
        this.globalScope.define("isNaN", nativeFn("isNaN", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$19((List) obj);
            }
        }));
        this.globalScope.define("isFinite", nativeFn("isFinite", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$20((List) obj);
            }
        }));
        this.globalScope.define("decodeURIComponent", nativeFn("decodeURIComponent", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$21((List) obj);
            }
        }));
        this.globalScope.define("encodeURIComponent", nativeFn("encodeURIComponent", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$22((List) obj);
            }
        }));
        this.globalScope.define("escape", nativeFn("escape", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$23((List) obj);
            }
        }));
        this.globalScope.define("unescape", nativeFn("unescape", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$24((List) obj);
            }
        }));
        this.globalScope.define("eval", nativeFn("eval", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$25(this.f$0, (List) obj);
            }
        }));
        this.globalScope.define("undefined", Unit.INSTANCE);
        this.globalScope.define("NaN", Double.valueOf(Double.NaN));
        this.globalScope.define("Infinity", Double.valueOf(Double.POSITIVE_INFINITY));
        this.globalScope.define("Array", nativeFn("Array", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$26((List) obj);
            }
        }));
        this.globalScope.define("Object", new NativeFn(new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$27((List) obj);
            }
        }, "Object", MapsKt.mutableMapOf(TuplesKt.to("keys", nativeFn("keys", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$28((List) obj);
            }
        })), TuplesKt.to("values", nativeFn("values", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$29((List) obj);
            }
        })))));
        this.globalScope.define("Function", nativeFn("Function", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$30(this.f$0, (List) obj);
            }
        }));
        this.globalScope.define("Number", nativeFn("Number", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$31((List) obj);
            }
        }));
        this.globalScope.define("Boolean", nativeFn("Boolean", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$32((List) obj);
            }
        }));
        this.globalScope.define("console", new JsObject(MapsKt.mutableMapOf(TuplesKt.to("log", nativeFn("log", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$33((List) obj);
            }
        })), TuplesKt.to(MediaRouteProviderProtocol.SERVICE_DATA_ERROR, nativeFn(MediaRouteProviderProtocol.SERVICE_DATA_ERROR, new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$34((List) obj);
            }
        })), TuplesKt.to("warn", nativeFn("warn", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$35((List) obj);
            }
        }))), null, 2, null));
    }

    static final Object installGlobals$lambda$0(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(Math.floor(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$1(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(Math.ceil(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$2(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(Math.rint(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$3(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(Math.abs(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$4(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(Math.sqrt(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$5(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(Math.pow(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0)), JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 1))));
    }

    static final Object installGlobals$lambda$6(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(Math.log(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$7(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(Math.sin(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$8(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(Math.cos(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$9(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(Math.tan(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$10(List args) {
        Double dValueOf;
        Intrinsics.checkNotNullParameter(args, "args");
        Iterator it = args.iterator();
        if (it.hasNext()) {
            double number = JsInterpreterKt.toNumber(it.next());
            while (it.hasNext()) {
                number = Math.max(number, JsInterpreterKt.toNumber(it.next()));
            }
            dValueOf = Double.valueOf(number);
        } else {
            dValueOf = null;
        }
        return dValueOf == null ? Double.valueOf(Double.NEGATIVE_INFINITY) : dValueOf;
    }

    static final Object installGlobals$lambda$11(List args) {
        Double dValueOf;
        Intrinsics.checkNotNullParameter(args, "args");
        Iterator it = args.iterator();
        if (it.hasNext()) {
            double number = JsInterpreterKt.toNumber(it.next());
            while (it.hasNext()) {
                number = Math.min(number, JsInterpreterKt.toNumber(it.next()));
            }
            dValueOf = Double.valueOf(number);
        } else {
            dValueOf = null;
        }
        return dValueOf == null ? Double.valueOf(Double.POSITIVE_INFINITY) : dValueOf;
    }

    static final Object installGlobals$lambda$12(List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        return Double.valueOf(Random.INSTANCE.nextDouble());
    }

    static final Object installGlobals$lambda$13(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(MathKt.truncate(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$14(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(MathKt.log2(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$15(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(Math.log10(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$16(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return CollectionsKt.joinToString$default(args, "", null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$16$0(obj);
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence installGlobals$lambda$16$0(Object obj) {
        return String.valueOf((char) JsInterpreterKt.toNumber(obj));
    }

    static final Object installGlobals$lambda$17(List args) {
        boolean z;
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(args, "args");
        String string = StringsKt.trim((CharSequence) JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0))).toString();
        if (string.length() <= 0 || !(string.charAt(0) == '+' || string.charAt(0) == '-')) {
            z = false;
            i = 0;
        } else {
            z = string.charAt(0) == '-';
            i = 1;
        }
        Object orNull = CollectionsKt.getOrNull(args, 1);
        int number = orNull != null ? (int) JsInterpreterKt.toNumber(orNull) : 0;
        double dDigitValue = Double.NaN;
        if (number == 0 || (number >= 2 && number <= 36)) {
            int i3 = 16;
            boolean z2 = number == 0 || number == 16;
            if (number == 0) {
                number = 10;
            }
            if (z2 && (i2 = i + 1) < string.length() && string.charAt(i) == '0' && (string.charAt(i2) == 'x' || string.charAt(i2) == 'X')) {
                i += 2;
            } else {
                i3 = number;
            }
            int i4 = i;
            while (i4 < string.length() && JsInterpreterKt.digitValue(string.charAt(i4), i3) != -1) {
                i4++;
            }
            if (i4 != i) {
                dDigitValue = 0.0d;
                while (i < i4) {
                    dDigitValue = (dDigitValue * ((double) i3)) + ((double) JsInterpreterKt.digitValue(string.charAt(i), i3));
                    i++;
                }
                if (z) {
                    dDigitValue = -dDigitValue;
                }
            }
        }
        return Double.valueOf(dDigitValue);
    }

    static final Object installGlobals$lambda$18(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0)));
    }

    static final Object installGlobals$lambda$19(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Boolean.valueOf(Double.isNaN(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))));
    }

    static final Object installGlobals$lambda$20(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Boolean.valueOf(Math.abs(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0))) <= Double.MAX_VALUE);
    }

    static final Object installGlobals$lambda$21(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return StringUtils.INSTANCE.decodeUrl(JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0)));
    }

    static final Object installGlobals$lambda$22(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return StringUtils.INSTANCE.encodeUrl(JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0)));
    }

    static final Object installGlobals$lambda$23(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return StringUtils.INSTANCE.encodeUrl(JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0)));
    }

    static final Object installGlobals$lambda$24(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return StringUtils.INSTANCE.decodeUrl(JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0)));
    }

    static final Object installGlobals$lambda$25(JsInterpreter jsInterpreter, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return jsInterpreter.evalInternal(JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0)));
    }

    static final Object installGlobals$lambda$26(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        if (args.size() == 1) {
            if (args.get(0) instanceof Double) {
                Object obj = args.get(0);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Double");
                int iDoubleValue = (int) ((Double) obj).doubleValue();
                ArrayList arrayList = new ArrayList(iDoubleValue);
                for (int i = 0; i < iDoubleValue; i++) {
                    arrayList.add(Unit.INSTANCE);
                }
                return new JsList(arrayList);
            }
        }
        return new JsList(CollectionsKt.toMutableList((Collection) args));
    }

    static final Object installGlobals$lambda$27(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        if (orNull == null || (orNull instanceof Unit)) {
            return new JsObject(null, null, 3, null);
        }
        return ((orNull instanceof JsObject) || (orNull instanceof JsList)) ? orNull : new JsObject(null, null, 3, null);
    }

    static final Object installGlobals$lambda$28(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        if (!(orNull instanceof JsObject)) {
            return new JsList(null, 1, null);
        }
        Set<String> setKeySet = ((JsObject) orNull).getProps().keySet();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setKeySet, 10));
        Iterator<T> it = setKeySet.iterator();
        while (it.hasNext()) {
            arrayList.add((String) it.next());
        }
        return new JsList(CollectionsKt.toMutableList((Collection) arrayList));
    }

    static final Object installGlobals$lambda$29(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        return orNull instanceof JsObject ? new JsList(CollectionsKt.toMutableList((Collection) ((JsObject) orNull).getProps().values())) : new JsList(null, 1, null);
    }

    static final Object installGlobals$lambda$30(JsInterpreter jsInterpreter, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        return jsInterpreter.nativeFn("anonymous", new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsInterpreter.installGlobals$lambda$30$0((List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit installGlobals$lambda$30$0(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Object installGlobals$lambda$31(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0)));
    }

    static final Object installGlobals$lambda$32(List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Boolean.valueOf(JsInterpreterKt.toBoolean(CollectionsKt.getOrNull(args, 0)));
    }

    static final Unit installGlobals$lambda$33(List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        return Unit.INSTANCE;
    }

    static final Unit installGlobals$lambda$34(List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        return Unit.INSTANCE;
    }

    static final Unit installGlobals$lambda$35(List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        return Unit.INSTANCE;
    }

    private final Object nativeFn(String name, Function1<? super List<? extends Object>, ? extends Object> fn) {
        return new NativeFn(fn, name, null, 4, null);
    }

    public final Object eval(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        this.instructionCount = 0L;
        this.startMark = TimeSource.Monotonic.ValueTimeMark.m10436boximpl(TimeSource.Monotonic.INSTANCE.m10435markNowz9LOYto());
        return evalInternal(code);
    }

    private final Object evalInternal(String code) {
        try {
            List<Node> program = new Parser(new Lexer(code)).parseProgram();
            Object objExecNode = Unit.INSTANCE;
            Iterator<Node> it = program.iterator();
            while (it.hasNext()) {
                objExecNode = execNode(it.next(), this.globalScope);
            }
            return objExecNode;
        } catch (JsCancellationException e) {
            throw e;
        } catch (ReturnSignal e2) {
            return e2.getValue();
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return Unit.INSTANCE;
        }
    }

    private final void checkBudget() throws JsExecutionLimitExceeded {
        long j = this.instructionCount + 1;
        this.instructionCount = j;
        if (j >= this.maxInstructions) {
            throw new JsExecutionLimitExceeded("script exceeded max instruction count of " + this.maxInstructions);
        }
        CoroutineScope coroutineScope = this.scope;
        if (coroutineScope != null && !CoroutineScopeKt.isActive(coroutineScope)) {
            throw new JsCancellationException("script cancelled: coroutine scope is no longer active");
        }
        if ((this.instructionCount & 1023) != 0 || Duration.m10317compareToLRDsOJo(this.startMark.mo10306elapsedNowUwyO8pc(), this.maxExecutionTime) < 0) {
            return;
        }
        throw new JsExecutionLimitExceeded("script exceeded max execution time of " + ((Object) Duration.m10358toStringimpl(this.maxExecutionTime)));
    }

    public final Object getVar(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Object obj = this.globalScope.get(name);
        if (obj instanceof Unit) {
            return null;
        }
        return obj;
    }

    public final void setVar(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.globalScope.define(name, value);
    }

    private final Object execNode(Node node, Scope scope) throws JsExecutionLimitExceeded, BreakSignal, ContinueSignal, ThrowSignal, ReturnSignal {
        checkBudget();
        if (node instanceof VarDecl) {
            for (Pair<String, Node> pair : ((VarDecl) node).getDecls()) {
                String strComponent1 = pair.component1();
                Node nodeComponent2 = pair.component2();
                scope.define(strComponent1, nodeComponent2 != null ? evalExpr(nodeComponent2, scope) : null);
            }
            return Unit.INSTANCE;
        }
        if (node instanceof ExprStmt) {
            return evalExpr(((ExprStmt) node).getExpr(), scope);
        }
        if (node instanceof BlockStmt) {
            Scope scope2 = new Scope(scope);
            Object objExecNode = Unit.INSTANCE;
            Iterator<Node> it = ((BlockStmt) node).getStmts().iterator();
            while (it.hasNext()) {
                objExecNode = execNode(it.next(), scope2);
            }
            return objExecNode;
        }
        if (node instanceof ReturnStmt) {
            Node expr = ((ReturnStmt) node).getExpr();
            throw new ReturnSignal(expr != null ? evalExpr(expr, scope) : null);
        }
        if (node instanceof IfStmt) {
            IfStmt ifStmt = (IfStmt) node;
            if (JsInterpreterKt.toBoolean(evalExpr(ifStmt.getTest(), scope))) {
                return execNode(ifStmt.getCons(), scope);
            }
            Node alt = ifStmt.getAlt();
            if (alt != null) {
                return execNode(alt, scope);
            }
            return null;
        }
        if (node instanceof WhileStmt) {
            while (JsInterpreterKt.toBoolean(evalExpr(((WhileStmt) node).getTest(), scope))) {
                try {
                    try {
                        execNode(((WhileStmt) node).getBody(), scope);
                    } catch (ContinueSignal unused) {
                        Unit unit = Unit.INSTANCE;
                    }
                } catch (BreakSignal unused2) {
                }
            }
            return Unit.INSTANCE;
        }
        if (node instanceof ForStmt) {
            Scope scope3 = new Scope(scope);
            Node init = ((ForStmt) node).getInit();
            if (init != null) {
                execNode(init, scope3);
            }
            while (true) {
                try {
                    if (((ForStmt) node).getTest() != null && !JsInterpreterKt.toBoolean(evalExpr(((ForStmt) node).getTest(), scope3))) {
                        break;
                    }
                    try {
                        execNode(((ForStmt) node).getBody(), scope3);
                    } catch (ContinueSignal unused3) {
                    }
                    Node update = ((ForStmt) node).getUpdate();
                    if (update != null) {
                        evalExpr(update, scope3);
                    }
                } catch (BreakSignal unused4) {
                }
            }
            return Unit.INSTANCE;
        }
        if (node instanceof ForInStmt) {
            Object objEvalExpr = evalExpr(((ForInStmt) node).getObj(), scope);
            Scope scope4 = new Scope(scope);
            try {
                if (objEvalExpr instanceof JsObject) {
                    Iterator<String> it2 = ((JsObject) objEvalExpr).getProps().keySet().iterator();
                    while (it2.hasNext()) {
                        scope4.define(((ForInStmt) node).getDecl(), it2.next());
                        try {
                            execNode(((ForInStmt) node).getBody(), scope4);
                        } catch (ContinueSignal unused5) {
                        }
                    }
                } else if (objEvalExpr instanceof JsList) {
                    int size = ((JsList) objEvalExpr).getElements().size();
                    for (int i = 0; i < size; i++) {
                        scope4.define(((ForInStmt) node).getDecl(), Double.valueOf(i));
                        try {
                            execNode(((ForInStmt) node).getBody(), scope4);
                        } catch (ContinueSignal unused6) {
                        }
                    }
                }
            } catch (BreakSignal unused7) {
            }
            return Unit.INSTANCE;
        }
        try {
            if (!(node instanceof TryCatch)) {
                if (node instanceof ThrowStmt) {
                    throw new ThrowSignal(evalExpr(((ThrowStmt) node).getExpr(), scope));
                }
                if (node instanceof BreakStmt) {
                    throw BreakSignal.INSTANCE;
                }
                if (node instanceof ContinueStmt) {
                    throw ContinueSignal.INSTANCE;
                }
                return evalExpr(node, scope);
            }
            try {
                Iterator<Node> it3 = ((TryCatch) node).getBody().iterator();
                while (it3.hasNext()) {
                    execNode(it3.next(), scope);
                }
                List<Node> finallyBody = ((TryCatch) node).getFinallyBody();
                if (finallyBody != null) {
                    Iterator<T> it4 = finallyBody.iterator();
                    while (it4.hasNext()) {
                        execNode((Node) it4.next(), scope);
                    }
                }
            } catch (JsCancellationException e) {
                throw e;
            } catch (ThrowSignal e2) {
                if (((TryCatch) node).getCatchBody() != null) {
                    Scope scope5 = new Scope(scope);
                    if (((TryCatch) node).getCatchParam() != null) {
                        scope5.define(((TryCatch) node).getCatchParam(), e2.getValue());
                    }
                    Iterator<Node> it5 = ((TryCatch) node).getCatchBody().iterator();
                    while (it5.hasNext()) {
                        execNode(it5.next(), scope5);
                    }
                }
                List<Node> finallyBody2 = ((TryCatch) node).getFinallyBody();
                if (finallyBody2 != null) {
                    Iterator<T> it6 = finallyBody2.iterator();
                    while (it6.hasNext()) {
                        execNode((Node) it6.next(), scope);
                    }
                }
            } catch (Exception unused8) {
                List<Node> finallyBody3 = ((TryCatch) node).getFinallyBody();
                if (finallyBody3 != null) {
                    Iterator<T> it7 = finallyBody3.iterator();
                    while (it7.hasNext()) {
                        execNode((Node) it7.next(), scope);
                    }
                }
            }
            return Unit.INSTANCE;
        } catch (Throwable th) {
            List<Node> finallyBody4 = ((TryCatch) node).getFinallyBody();
            if (finallyBody4 != null) {
                Iterator<T> it8 = finallyBody4.iterator();
                while (it8.hasNext()) {
                    execNode((Node) it8.next(), scope);
                }
            }
            throw th;
        }
    }

    private final Object evalExpr(Node node, Scope scope) {
        Object objEvalExpr;
        if (node instanceof NumLit) {
            return Double.valueOf(((NumLit) node).getV());
        }
        if (node instanceof StrLit) {
            return ((StrLit) node).getV();
        }
        if (node instanceof BoolLit) {
            return Boolean.valueOf(((BoolLit) node).getV());
        }
        if (Intrinsics.areEqual(node, NullLit.INSTANCE)) {
            return null;
        }
        if (Intrinsics.areEqual(node, UndefinedLit.INSTANCE)) {
            return Unit.INSTANCE;
        }
        if (node instanceof Ident) {
            return scope.get(((Ident) node).getName());
        }
        if (!(node instanceof ArrayLit)) {
            if (!(node instanceof ObjLit)) {
                if (node instanceof FuncExpr) {
                    FuncExpr funcExpr = (FuncExpr) node;
                    JsFunction jsFunction = new JsFunction(funcExpr.getName(), funcExpr.getParams(), funcExpr.getBody(), scope);
                    if (funcExpr.getName() != null) {
                        scope.define(funcExpr.getName(), jsFunction);
                    }
                    return jsFunction;
                }
                if (!(node instanceof SeqExpr)) {
                    if (node instanceof TypeofExpr) {
                        try {
                            objEvalExpr = evalExpr(((TypeofExpr) node).getExpr(), scope);
                        } catch (Exception unused) {
                            objEvalExpr = Unit.INSTANCE;
                        }
                        if (objEvalExpr instanceof Unit) {
                            return "undefined";
                        }
                        if (objEvalExpr != null) {
                            if (objEvalExpr instanceof Double) {
                                return "number";
                            }
                            if (objEvalExpr instanceof Boolean) {
                                return TypedValues.Custom.S_BOOLEAN;
                            }
                            if (objEvalExpr instanceof String) {
                                return TypedValues.Custom.S_STRING;
                            }
                            if ((objEvalExpr instanceof JsFunction) || (objEvalExpr instanceof NativeFn)) {
                                return "function";
                            }
                        }
                        return "object";
                    }
                    if (node instanceof UnaryExpr) {
                        return evalUnary((UnaryExpr) node, scope);
                    }
                    if (node instanceof BinExpr) {
                        return evalBinary((BinExpr) node, scope);
                    }
                    if (node instanceof AssignExpr) {
                        return evalAssign((AssignExpr) node, scope);
                    }
                    if (node instanceof CondExpr) {
                        CondExpr condExpr = (CondExpr) node;
                        return JsInterpreterKt.toBoolean(evalExpr(condExpr.getTest(), scope)) ? evalExpr(condExpr.getCons(), scope) : evalExpr(condExpr.getAlt(), scope);
                    }
                    if (node instanceof MemberExpr) {
                        return evalMember((MemberExpr) node, scope);
                    }
                    if (node instanceof CallExpr) {
                        return evalCall((CallExpr) node, scope);
                    }
                    return node instanceof NewExpr ? evalNew((NewExpr) node, scope) : Unit.INSTANCE;
                }
                List<Node> exprs = ((SeqExpr) node).getExprs();
                Unit unitEvalExpr = Unit.INSTANCE;
                Iterator<T> it = exprs.iterator();
                while (it.hasNext()) {
                    unitEvalExpr = evalExpr((Node) it.next(), scope);
                }
                return unitEvalExpr;
            }
            List<Pair<String, Node>> pairs = ((ObjLit) node).getPairs();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(pairs, 10)), 16));
            Iterator<T> it2 = pairs.iterator();
            while (it2.hasNext()) {
                Pair pair = (Pair) it2.next();
                Pair pair2 = TuplesKt.to((String) pair.component1(), evalExpr((Node) pair.component2(), scope));
                linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
            }
            return new JsObject(MapsKt.toMutableMap(linkedHashMap), null, 2, null);
        }
        List<Node> elems = ((ArrayLit) node).getElems();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(elems, 10));
        Iterator<T> it3 = elems.iterator();
        while (it3.hasNext()) {
            arrayList.add(evalExpr((Node) it3.next(), scope));
        }
        return new JsList(CollectionsKt.toMutableList((Collection) arrayList));
    }

    private final Object evalUnary(UnaryExpr node, Scope scope) {
        if (Intrinsics.areEqual(node.getOp(), "++") || Intrinsics.areEqual(node.getOp(), "--")) {
            double d = Intrinsics.areEqual(node.getOp(), "++") ? 1.0d : -1.0d;
            double number = JsInterpreterKt.toNumber(evalExpr(node.getExpr(), scope));
            double d2 = d + number;
            assignTo(node.getExpr(), Double.valueOf(d2), scope);
            if (node.getPrefix()) {
                number = d2;
            }
            return Double.valueOf(number);
        }
        Object objEvalExpr = evalExpr(node.getExpr(), scope);
        String op = node.getOp();
        int iHashCode = op.hashCode();
        if (iHashCode != 33) {
            if (iHashCode != 43) {
                if (iHashCode != 45) {
                    if (iHashCode == 126 && op.equals("~")) {
                        return Double.valueOf(~((long) JsInterpreterKt.toNumber(objEvalExpr)));
                    }
                } else if (op.equals("-")) {
                    return Double.valueOf(-JsInterpreterKt.toNumber(objEvalExpr));
                }
            } else if (op.equals(Marker.ANY_NON_NULL_MARKER)) {
                return Double.valueOf(JsInterpreterKt.toNumber(objEvalExpr));
            }
        } else if (op.equals("!")) {
            return Boolean.valueOf(!JsInterpreterKt.toBoolean(objEvalExpr));
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final Object evalBinary(BinExpr node, Scope scope) {
        if (Intrinsics.areEqual(node.getOp(), "&&")) {
            Object objEvalExpr = evalExpr(node.getLeft(), scope);
            return !JsInterpreterKt.toBoolean(objEvalExpr) ? objEvalExpr : evalExpr(node.getRight(), scope);
        }
        if (Intrinsics.areEqual(node.getOp(), "||")) {
            Object objEvalExpr2 = evalExpr(node.getLeft(), scope);
            return JsInterpreterKt.toBoolean(objEvalExpr2) ? objEvalExpr2 : evalExpr(node.getRight(), scope);
        }
        Object objEvalExpr3 = evalExpr(node.getLeft(), scope);
        Object objEvalExpr4 = evalExpr(node.getRight(), scope);
        String op = node.getOp();
        boolean z = true;
        boolean zContainsKey = false;
        switch (op.hashCode()) {
            case 37:
                if (op.equals("%")) {
                    return Double.valueOf(JsInterpreterKt.toNumber(objEvalExpr3) % JsInterpreterKt.toNumber(objEvalExpr4));
                }
                break;
            case 38:
                if (op.equals("&")) {
                    return Double.valueOf(((long) JsInterpreterKt.toNumber(objEvalExpr3)) & ((long) JsInterpreterKt.toNumber(objEvalExpr4)));
                }
                break;
            case 42:
                if (op.equals(Marker.ANY_MARKER)) {
                    return Double.valueOf(JsInterpreterKt.toNumber(objEvalExpr3) * JsInterpreterKt.toNumber(objEvalExpr4));
                }
                break;
            case 43:
                if (op.equals(Marker.ANY_NON_NULL_MARKER)) {
                    if ((objEvalExpr3 instanceof JsList) || (objEvalExpr3 instanceof JsObject) || (objEvalExpr3 instanceof NativeFn) || (objEvalExpr3 instanceof JsFunction)) {
                        objEvalExpr3 = JsInterpreterKt.toJsString(objEvalExpr3);
                    }
                    if ((objEvalExpr4 instanceof JsList) || (objEvalExpr4 instanceof JsObject) || (objEvalExpr4 instanceof NativeFn) || (objEvalExpr4 instanceof JsFunction)) {
                        objEvalExpr4 = JsInterpreterKt.toJsString(objEvalExpr4);
                    }
                    if (!(objEvalExpr3 instanceof String) && !(objEvalExpr4 instanceof String)) {
                        return Double.valueOf(JsInterpreterKt.toNumber(objEvalExpr3) + JsInterpreterKt.toNumber(objEvalExpr4));
                    }
                    return JsInterpreterKt.toJsString(objEvalExpr3) + JsInterpreterKt.toJsString(objEvalExpr4);
                }
                break;
            case 45:
                if (op.equals("-")) {
                    return Double.valueOf(JsInterpreterKt.toNumber(objEvalExpr3) - JsInterpreterKt.toNumber(objEvalExpr4));
                }
                break;
            case 47:
                if (op.equals("/")) {
                    return Double.valueOf(JsInterpreterKt.toNumber(objEvalExpr3) / JsInterpreterKt.toNumber(objEvalExpr4));
                }
                break;
            case 60:
                if (op.equals("<")) {
                    return Boolean.valueOf(JsInterpreterKt.toNumber(objEvalExpr3) < JsInterpreterKt.toNumber(objEvalExpr4));
                }
                break;
            case 62:
                if (op.equals(">")) {
                    return Boolean.valueOf(JsInterpreterKt.toNumber(objEvalExpr3) > JsInterpreterKt.toNumber(objEvalExpr4));
                }
                break;
            case 94:
                if (op.equals("^")) {
                    return Double.valueOf(((long) JsInterpreterKt.toNumber(objEvalExpr3)) ^ ((long) JsInterpreterKt.toNumber(objEvalExpr4)));
                }
                break;
            case 124:
                if (op.equals("|")) {
                    return Double.valueOf(((long) JsInterpreterKt.toNumber(objEvalExpr3)) | ((long) JsInterpreterKt.toNumber(objEvalExpr4)));
                }
                break;
            case 1084:
                if (op.equals("!=")) {
                    return Boolean.valueOf(!JsInterpreterKt.looseEq(objEvalExpr3, objEvalExpr4));
                }
                break;
            case 1344:
                if (op.equals("**")) {
                    return Double.valueOf(Math.pow(JsInterpreterKt.toNumber(objEvalExpr3), JsInterpreterKt.toNumber(objEvalExpr4)));
                }
                break;
            case 1920:
                if (op.equals("<<")) {
                    return Double.valueOf(((long) JsInterpreterKt.toNumber(objEvalExpr3)) << ((int) JsInterpreterKt.toNumber(objEvalExpr4)));
                }
                break;
            case 1921:
                if (op.equals("<=")) {
                    return Boolean.valueOf(JsInterpreterKt.toNumber(objEvalExpr3) <= JsInterpreterKt.toNumber(objEvalExpr4));
                }
                break;
            case 1952:
                if (op.equals("==")) {
                    return Boolean.valueOf(JsInterpreterKt.looseEq(objEvalExpr3, objEvalExpr4));
                }
                break;
            case 1983:
                if (op.equals(">=")) {
                    return Boolean.valueOf(JsInterpreterKt.toNumber(objEvalExpr3) >= JsInterpreterKt.toNumber(objEvalExpr4));
                }
                break;
            case 1984:
                if (op.equals(">>")) {
                    return Double.valueOf(((long) JsInterpreterKt.toNumber(objEvalExpr3)) >> ((int) JsInterpreterKt.toNumber(objEvalExpr4)));
                }
                break;
            case 3365:
                if (op.equals("in")) {
                    if (objEvalExpr4 instanceof JsObject) {
                        zContainsKey = ((JsObject) objEvalExpr4).getProps().containsKey(JsInterpreterKt.toJsString(objEvalExpr3));
                    } else if (objEvalExpr4 instanceof JsList) {
                        int number = (int) JsInterpreterKt.toNumber(objEvalExpr3);
                        zContainsKey = number >= 0 && number < ((JsList) objEvalExpr4).getElements().size();
                    }
                    return Boolean.valueOf(zContainsKey);
                }
                break;
            case 33665:
                if (op.equals("!==")) {
                    return Boolean.valueOf(!JsInterpreterKt.strictEq(objEvalExpr3, objEvalExpr4));
                }
                break;
            case 60573:
                if (op.equals("===")) {
                    return Boolean.valueOf(JsInterpreterKt.strictEq(objEvalExpr3, objEvalExpr4));
                }
                break;
            case 61566:
                if (op.equals(">>>")) {
                    return Double.valueOf((((long) ((int) JsInterpreterKt.toNumber(objEvalExpr3))) & 4294967295L) >>> ((int) JsInterpreterKt.toNumber(objEvalExpr4)));
                }
                break;
            case 902025516:
                if (op.equals("instanceof")) {
                    if (objEvalExpr4 instanceof NativeFn) {
                        String name = ((NativeFn) objEvalExpr4).getName();
                        switch (name.hashCode()) {
                            case -1950496919:
                                z = !name.equals("Number") ? false : objEvalExpr3 instanceof Double;
                                break;
                            case -1939501217:
                                if (!name.equals("Object")) {
                                    z = false;
                                } else if (!(objEvalExpr3 instanceof JsObject) && !(objEvalExpr3 instanceof JsList)) {
                                    z = false;
                                }
                                break;
                            case -1808118735:
                                z = !name.equals("String") ? false : objEvalExpr3 instanceof String;
                                break;
                            case 63537721:
                                z = !name.equals("Array") ? false : objEvalExpr3 instanceof JsList;
                                break;
                            case 1445582840:
                                if (!name.equals("Function")) {
                                    z = false;
                                } else if (!(objEvalExpr3 instanceof JsFunction) && !(objEvalExpr3 instanceof NativeFn)) {
                                    z = false;
                                }
                                break;
                            case 1729365000:
                                z = !name.equals("Boolean") ? false : objEvalExpr3 instanceof Boolean;
                                break;
                            default:
                                z = false;
                                break;
                        }
                    } else if (!(objEvalExpr4 instanceof JsFunction) || !(objEvalExpr3 instanceof JsObject) || ((JsObject) objEvalExpr3).getConstructor() != objEvalExpr4) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
                break;
        }
        return Unit.INSTANCE;
    }

    private final Object evalAssign(AssignExpr node, Scope scope) {
        Object objEvalExpr = evalExpr(node.getRight(), scope);
        if (!Intrinsics.areEqual(node.getOp(), "=")) {
            Object objEvalExpr2 = evalExpr(node.getLeft(), scope);
            String op = node.getOp();
            int iHashCode = op.hashCode();
            if (iHashCode != 1208) {
                if (iHashCode != 1363) {
                    if (iHashCode != 1394) {
                        if (iHashCode != 1456) {
                            if (iHashCode != 1518) {
                                if (iHashCode == 41725 && op.equals("**=")) {
                                    objEvalExpr = Double.valueOf(Math.pow(JsInterpreterKt.toNumber(objEvalExpr2), JsInterpreterKt.toNumber(objEvalExpr)));
                                }
                            } else if (op.equals("/=")) {
                                objEvalExpr = Double.valueOf(JsInterpreterKt.toNumber(objEvalExpr2) / JsInterpreterKt.toNumber(objEvalExpr));
                            }
                        } else if (op.equals("-=")) {
                            objEvalExpr = Double.valueOf(JsInterpreterKt.toNumber(objEvalExpr2) - JsInterpreterKt.toNumber(objEvalExpr));
                        }
                    } else if (op.equals("+=")) {
                        if ((objEvalExpr2 instanceof String) || (objEvalExpr instanceof String)) {
                            objEvalExpr = JsInterpreterKt.toJsString(objEvalExpr2) + JsInterpreterKt.toJsString(objEvalExpr);
                        } else {
                            objEvalExpr = Double.valueOf(JsInterpreterKt.toNumber(objEvalExpr2) + JsInterpreterKt.toNumber(objEvalExpr));
                        }
                    }
                } else if (op.equals("*=")) {
                    objEvalExpr = Double.valueOf(JsInterpreterKt.toNumber(objEvalExpr2) * JsInterpreterKt.toNumber(objEvalExpr));
                }
            } else if (op.equals("%=")) {
                objEvalExpr = Double.valueOf(JsInterpreterKt.toNumber(objEvalExpr2) % JsInterpreterKt.toNumber(objEvalExpr));
            }
        }
        assignTo(node.getLeft(), objEvalExpr, scope);
        return objEvalExpr;
    }

    private final void assignTo(Node target, Object value, Scope scope) {
        if (target instanceof Ident) {
            scope.set(((Ident) target).getName(), value);
            return;
        }
        if (target instanceof MemberExpr) {
            MemberExpr memberExpr = (MemberExpr) target;
            Object objEvalExpr = evalExpr(memberExpr.getObj(), scope);
            String strPropKey = propKey(memberExpr.getProp(), memberExpr.getComputed(), scope);
            if (objEvalExpr instanceof JsObject) {
                ((JsObject) objEvalExpr).getProps().put(strPropKey, value);
                return;
            }
            if (objEvalExpr instanceof JsList) {
                Integer intOrNull = StringsKt.toIntOrNull(strPropKey);
                if (intOrNull != null) {
                    ((JsList) objEvalExpr).set(intOrNull.intValue(), value);
                    return;
                } else {
                    if (Intrinsics.areEqual(strPropKey, "length")) {
                        ((JsList) objEvalExpr).setLength((int) JsInterpreterKt.toNumber(value));
                        return;
                    }
                    return;
                }
            }
            return;
        }
        throw new RuntimeException("Invalid assignment target: " + target);
    }

    private final String propKey(Node prop, boolean computed, Scope scope) {
        String v;
        if (computed) {
            return JsInterpreterKt.toJsString(evalExpr(prop, scope));
        }
        StrLit strLit = prop instanceof StrLit ? (StrLit) prop : null;
        return (strLit == null || (v = strLit.getV()) == null) ? JsInterpreterKt.toJsString(evalExpr(prop, scope)) : v;
    }

    private final Object evalMember(MemberExpr node, Scope scope) {
        return getMember(evalExpr(node.getObj(), scope), propKey(node.getProp(), node.getComputed(), scope));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:291:0x0472  */
    /* JADX WARN: Code duplicated, block: B:293:0x047a  */
    /* JADX WARN: Code duplicated, block: B:295:0x0482  */
    /* JADX WARN: Code duplicated, block: B:296:0x048b  */
    /* JADX WARN: Code duplicated, block: B:299:0x0490 A[RETURN] */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x023b, code lost:
    
        if (r9.equals("trimLeft") == false) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x032b, code lost:
    
        if (r9.equals("toUpperCase") == false) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x034b, code lost:
    
        if (r9.equals("trimStart") == false) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x035a, code lost:
    
        return nativeFn("trimStart", new com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda70(r8));
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x0361, code lost:
    
        if (r9.equals("trimRight") == false) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x036b, code lost:
    
        if (r9.equals("toLocaleUpperCase") == false) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x037a, code lost:
    
        return nativeFn("toUpperCase", new com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda67(r8));
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x03c3, code lost:
    
        if (r9.equals("trimEnd") == false) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x03d2, code lost:
    
        return nativeFn("trimEnd", new com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda72(r8));
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x03ed, code lost:
    
        if (r9.equals("toLowerCase") == false) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x0437, code lost:
    
        if (r9.equals("toLocaleLowerCase") == false) goto L289;
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x0445, code lost:
    
        return nativeFn("toLowerCase", new com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda68(r8));
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.Object getMember(final java.lang.Object r8, java.lang.String r9) {
        /*
            Method dump skipped, instruction units count: 1434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.JsInterpreter.getMember(java.lang.Object, java.lang.String):java.lang.Object");
    }

    static final Object getMember$lambda$0(Object obj, List args) {
        String jsString;
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        if (orNull == null || (jsString = JsInterpreterKt.toJsString(orNull)) == null) {
            jsString = ",";
        }
        return CollectionsKt.joinToString$default(((JsList) obj).getElements(), jsString, null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda31
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return JsInterpreter.getMember$lambda$0$1(obj2);
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence getMember$lambda$0$1(Object obj) {
        return JsInterpreterKt.joinElement(obj);
    }

    static final Object getMember$lambda$1(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        CollectionsKt.reverse(((JsList) obj).getElements());
        return obj;
    }

    static final Object getMember$lambda$2(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Iterator it = args.iterator();
        while (it.hasNext()) {
            ((JsList) obj).getElements().add(it.next());
        }
        return Double.valueOf(((JsList) obj).getElements().size());
    }

    static final Object getMember$lambda$3(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        JsList jsList = (JsList) obj;
        return jsList.getElements().isEmpty() ? Unit.INSTANCE : jsList.getElements().remove(jsList.getElements().size() - 1);
    }

    static final Object getMember$lambda$4(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        JsList jsList = (JsList) obj;
        return jsList.getElements().isEmpty() ? Unit.INSTANCE : jsList.getElements().remove(0);
    }

    static final Object getMember$lambda$5(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Iterator it = CollectionsKt.reversed(args).iterator();
        while (it.hasNext()) {
            ((JsList) obj).getElements().add(0, it.next());
        }
        return Double.valueOf(((JsList) obj).getElements().size());
    }

    static final Object getMember$lambda$6(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        int number = orNull != null ? (int) JsInterpreterKt.toNumber(orNull) : 0;
        Object orNull2 = CollectionsKt.getOrNull(args, 1);
        int number2 = orNull2 != null ? (int) JsInterpreterKt.toNumber(orNull2) : ((JsList) obj).getElements().size();
        int size = ((JsList) obj).getElements().size();
        int iMax = number < 0 ? Math.max(0, size + number) : Math.min(number, size);
        int size2 = ((JsList) obj).getElements().size();
        return new JsList(CollectionsKt.toMutableList((Collection) ((JsList) obj).getElements().subList(Math.max(0, iMax), Math.max(iMax, number2 < 0 ? Math.max(0, size2 + number2) : Math.min(number2, size2)))));
    }

    static final Object getMember$lambda$7(Object obj, List args) {
        int iMax;
        Intrinsics.checkNotNullParameter(args, "args");
        int i = 0;
        Object orNull = CollectionsKt.getOrNull(args, 0);
        if (orNull != null) {
            int number = (int) JsInterpreterKt.toNumber(orNull);
            int size = ((JsList) obj).getElements().size();
            iMax = number < 0 ? Math.max(0, size + number) : Math.min(number, size);
        } else {
            iMax = 0;
        }
        Object orNull2 = CollectionsKt.getOrNull(args, 1);
        int iCoerceIn = orNull2 != null ? RangesKt.coerceIn((int) JsInterpreterKt.toNumber(orNull2), 0, ((JsList) obj).getElements().size() - iMax) : ((JsList) obj).getElements().size() - iMax;
        JsList jsList = (JsList) obj;
        JsList jsList2 = new JsList(CollectionsKt.toMutableList((Collection) jsList.getElements().subList(iMax, iMax + iCoerceIn)));
        for (int i2 = 0; i2 < iCoerceIn; i2++) {
            jsList.getElements().remove(iMax);
        }
        for (Object obj2 : CollectionsKt.drop(args, 2)) {
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            jsList.getElements().add(i + iMax, obj2);
            i = i3;
        }
        return jsList2;
    }

    static final Object getMember$lambda$8(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        int i = 0;
        Object orNull = CollectionsKt.getOrNull(args, 0);
        Object orNull2 = CollectionsKt.getOrNull(args, 1);
        int number = orNull2 != null ? (int) JsInterpreterKt.toNumber(orNull2) : 0;
        Iterator<Object> it = ((JsList) obj).getElements().iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (JsInterpreterKt.strictEq(it.next(), orNull)) {
                break;
            }
            i++;
        }
        return Double.valueOf(i < number ? -1.0d : i);
    }

    static final Object getMember$lambda$9(Object obj, JsInterpreter jsInterpreter, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        List<Object> elements = ((JsList) obj).getElements();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(elements, 10));
        int i = 0;
        for (Object obj2 : elements) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(jsInterpreter.callAny(orNull, CollectionsKt.listOf(obj2, Double.valueOf(i), obj), null));
            i = i2;
        }
        return new JsList(CollectionsKt.toMutableList((Collection) arrayList));
    }

    static final Object getMember$lambda$10(Object obj, JsInterpreter jsInterpreter, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        List<Object> elements = ((JsList) obj).getElements();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj2 : elements) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (JsInterpreterKt.toBoolean(jsInterpreter.callAny(orNull, CollectionsKt.listOf(obj2, Double.valueOf(i), obj), null))) {
                arrayList.add(obj2);
            }
            i = i2;
        }
        return new JsList(CollectionsKt.toMutableList((Collection) arrayList));
    }

    static final Unit getMember$lambda$11(Object obj, JsInterpreter jsInterpreter, List args) throws JsExecutionLimitExceeded, BreakSignal, ContinueSignal, ThrowSignal {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        int i = 0;
        for (Object obj2 : ((JsList) obj).getElements()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            jsInterpreter.callAny(orNull, CollectionsKt.listOf(obj2, Double.valueOf(i), obj), null);
            i = i2;
        }
        return Unit.INSTANCE;
    }

    static final Object getMember$lambda$12(Object obj, JsInterpreter jsInterpreter, List args) throws JsExecutionLimitExceeded, BreakSignal, ContinueSignal, ThrowSignal {
        Object objFirstOrNull;
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        if (args.size() > 1) {
            objFirstOrNull = args.get(1);
        } else {
            objFirstOrNull = CollectionsKt.firstOrNull((List<? extends Object>) ((JsList) obj).getElements());
            if (objFirstOrNull == null) {
                objFirstOrNull = Unit.INSTANCE;
            }
        }
        JsList jsList = (JsList) obj;
        int size = jsList.getElements().size();
        for (int i = args.size() > 1 ? 0 : 1; i < size; i++) {
            objFirstOrNull = jsInterpreter.callAny(orNull, CollectionsKt.listOf(objFirstOrNull, jsList.getElements().get(i), Double.valueOf(i), obj), null);
        }
        return objFirstOrNull;
    }

    static final Object getMember$lambda$13(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        JsList jsList = new JsList(CollectionsKt.toMutableList((Collection) ((JsList) obj).getElements()));
        for (Object obj2 : args) {
            if (obj2 instanceof JsList) {
                jsList.getElements().addAll(((JsList) obj2).getElements());
            } else {
                jsList.getElements().add(obj2);
            }
        }
        return jsList;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0035 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:9:0x0032  */
    static final Object getMember$lambda$14(Object obj, JsInterpreter jsInterpreter, List args) {
        Object obj2;
        Object next;
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        Iterator<T> it = ((JsList) obj).getElements().iterator();
        do {
            obj2 = null;
            if (it.hasNext()) {
                next = it.next();
            }
            if (obj2 == null) {
                return Unit.INSTANCE;
            }
            return obj2;
        } while (!JsInterpreterKt.toBoolean(jsInterpreter.callAny(orNull, CollectionsKt.listOf(next), null)));
        obj2 = next;
        if (obj2 == null) {
            return Unit.INSTANCE;
        }
        return obj2;
    }

    static final Object getMember$lambda$15(Object obj, JsInterpreter jsInterpreter, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        boolean z = false;
        Object orNull = CollectionsKt.getOrNull(args, 0);
        List<Object> elements = ((JsList) obj).getElements();
        if (!(elements instanceof Collection) || !elements.isEmpty()) {
            Iterator<T> it = elements.iterator();
            while (it.hasNext()) {
                if (JsInterpreterKt.toBoolean(jsInterpreter.callAny(orNull, CollectionsKt.listOf(it.next()), null))) {
                    z = true;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    static final Object getMember$lambda$16(Object obj, JsInterpreter jsInterpreter, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        boolean z = false;
        Object orNull = CollectionsKt.getOrNull(args, 0);
        List<Object> elements = ((JsList) obj).getElements();
        if ((elements instanceof Collection) && elements.isEmpty()) {
            z = true;
        } else {
            Iterator<T> it = elements.iterator();
            while (it.hasNext()) {
                if (!JsInterpreterKt.toBoolean(jsInterpreter.callAny(orNull, CollectionsKt.listOf(it.next()), null))) {
                }
            }
            z = true;
        }
        return Boolean.valueOf(z);
    }

    static final Object getMember$lambda$17(Object obj, final JsInterpreter jsInterpreter, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        final Object orNull = CollectionsKt.getOrNull(args, 0);
        if (orNull == null) {
            CollectionsKt.sortWith(((JsList) obj).getElements(), new Comparator() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda29
                @Override // java.util.Comparator
                public final int compare(Object obj2, Object obj3) {
                    return JsInterpreter.getMember$lambda$17$0(obj2, obj3);
                }
            });
            return obj;
        }
        CollectionsKt.sortWith(((JsList) obj).getElements(), new Comparator() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda30
            @Override // java.util.Comparator
            public final int compare(Object obj2, Object obj3) {
                return JsInterpreter.getMember$lambda$17$1(this.f$0, orNull, obj2, obj3);
            }
        });
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getMember$lambda$17$0(Object obj, Object obj2) {
        return JsInterpreterKt.toJsString(obj).compareTo(JsInterpreterKt.toJsString(obj2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getMember$lambda$17$1(JsInterpreter jsInterpreter, Object obj, Object obj2, Object obj3) {
        return (int) JsInterpreterKt.toNumber(jsInterpreter.callAny(obj, CollectionsKt.listOf(obj2, obj3), null));
    }

    static final Object getMember$lambda$18(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        List<Object> elements = ((JsList) obj).getElements();
        boolean z = false;
        if (!(elements instanceof Collection) || !elements.isEmpty()) {
            Iterator<T> it = elements.iterator();
            while (it.hasNext()) {
                if (JsInterpreterKt.looseEq(it.next(), CollectionsKt.getOrNull(args, 0))) {
                    z = true;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    static final Object getMember$lambda$19(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        return CollectionsKt.joinToString$default(((JsList) obj).getElements(), ",", null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.utils.JsInterpreter$$ExternalSyntheticLambda89
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return JsInterpreter.getMember$lambda$19$0(obj2);
            }
        }, 30, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence getMember$lambda$19$0(Object obj) {
        return JsInterpreterKt.joinElement(obj);
    }

    static final Object getMember$lambda$20(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        JsList jsList = new JsList(null, 1, null);
        for (Object obj2 : ((JsList) obj).getElements()) {
            if (obj2 instanceof JsList) {
                jsList.getElements().addAll(((JsList) obj2).getElements());
            } else {
                jsList.getElements().add(obj2);
            }
        }
        return jsList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final Object getMember$lambda$22(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        if (orNull == null || (orNull instanceof Unit)) {
            return new JsList(CollectionsKt.mutableListOf(obj));
        }
        boolean z = orNull instanceof String;
        if (z && ((CharSequence) orNull).length() == 0) {
            CharSequence charSequence = (CharSequence) obj;
            ArrayList arrayList = new ArrayList(charSequence.length());
            for (int i = 0; i < charSequence.length(); i++) {
                arrayList.add(String.valueOf(charSequence.charAt(i)));
            }
            return new JsList(CollectionsKt.toMutableList((Collection) arrayList));
        }
        if (!z) {
            List listSplit$default = StringsKt.split$default((CharSequence) obj, new String[]{JsInterpreterKt.toJsString(orNull)}, false, 0, 6, (Object) null);
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                arrayList2.add((String) it.next());
            }
            return new JsList(CollectionsKt.toMutableList((Collection) arrayList2));
        }
        List listSplit$default2 = StringsKt.split$default((CharSequence) obj, new String[]{orNull}, false, 0, 6, (Object) null);
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default2, 10));
        Iterator it2 = listSplit$default2.iterator();
        while (it2.hasNext()) {
            arrayList3.add((String) it2.next());
        }
        return new JsList(CollectionsKt.toMutableList((Collection) arrayList3));
    }

    static final Object getMember$lambda$24(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        String jsString = JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 1));
        return orNull instanceof String ? StringsKt.replaceFirst$default((String) obj, (String) orNull, jsString, false, 4, (Object) null) : StringsKt.replace$default((String) obj, JsInterpreterKt.toJsString(orNull), jsString, false, 4, (Object) null);
    }

    static final Object getMember$lambda$25(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        return StringsKt.replace$default((String) obj, JsInterpreterKt.toJsString(orNull), JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 1)), false, 4, (Object) null);
    }

    static final Object getMember$lambda$26(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(StringsKt.indexOf$default((CharSequence) obj, JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0)), 0, false, 6, (Object) null));
    }

    static final Object getMember$lambda$27(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Double.valueOf(StringsKt.lastIndexOf$default((CharSequence) obj, JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0)), 0, false, 6, (Object) null));
    }

    static final Object getMember$lambda$28(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Boolean.valueOf(StringsKt.contains$default((CharSequence) obj, (CharSequence) JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0)), false, 2, (Object) null));
    }

    static final Object getMember$lambda$29(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Boolean.valueOf(StringsKt.startsWith$default((String) obj, JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0)), false, 2, (Object) null));
    }

    static final Object getMember$lambda$30(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return Boolean.valueOf(StringsKt.endsWith$default((String) obj, JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0)), false, 2, (Object) null));
    }

    static final Object getMember$lambda$31(Object obj, List args) {
        int iMax;
        int length;
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        if (orNull != null) {
            int number = (int) JsInterpreterKt.toNumber(orNull);
            int length2 = ((String) obj).length();
            iMax = number < 0 ? Math.max(0, length2 + number) : Math.min(number, length2);
        } else {
            iMax = 0;
        }
        Object orNull2 = CollectionsKt.getOrNull(args, 1);
        if (orNull2 != null) {
            int number2 = (int) JsInterpreterKt.toNumber(orNull2);
            length = number2 < 0 ? Math.max(0, ((String) obj).length() + number2) : Math.min(number2, ((String) obj).length());
        } else {
            length = ((String) obj).length();
        }
        if (length <= iMax) {
            return "";
        }
        String strSubstring = ((String) obj).substring(iMax, length);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    static final Object getMember$lambda$32(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        int iMax = 0;
        Object orNull = CollectionsKt.getOrNull(args, 0);
        if (orNull != null) {
            int number = (int) JsInterpreterKt.toNumber(orNull);
            iMax = number < 0 ? Math.max(0, ((String) obj).length() + number) : Math.min(number, ((String) obj).length());
        }
        Object orNull2 = CollectionsKt.getOrNull(args, 1);
        int number2 = orNull2 != null ? (int) JsInterpreterKt.toNumber(orNull2) : ((String) obj).length() - iMax;
        if (number2 <= 0) {
            return "";
        }
        String str = (String) obj;
        String strSubstring = str.substring(iMax, Math.min(number2 + iMax, str.length()));
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    static final Object getMember$lambda$33(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        int iCoerceIn = orNull != null ? RangesKt.coerceIn((int) JsInterpreterKt.toNumber(orNull), 0, ((String) obj).length()) : 0;
        Object orNull2 = CollectionsKt.getOrNull(args, 1);
        int iCoerceIn2 = orNull2 != null ? RangesKt.coerceIn((int) JsInterpreterKt.toNumber(orNull2), 0, ((String) obj).length()) : ((String) obj).length();
        String strSubstring = ((String) obj).substring(Math.min(iCoerceIn, iCoerceIn2), Math.max(iCoerceIn, iCoerceIn2));
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    static final Object getMember$lambda$34(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        int number = orNull != null ? (int) JsInterpreterKt.toNumber(orNull) : 0;
        if (number < 0) {
            return "";
        }
        String str = (String) obj;
        return number >= str.length() ? "" : String.valueOf(str.charAt(number));
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0022  */
    static final Object getMember$lambda$35(Object obj, List args) {
        double dCharAt;
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        int number = orNull != null ? (int) JsInterpreterKt.toNumber(orNull) : 0;
        if (number >= 0) {
            String str = (String) obj;
            if (number >= str.length()) {
                dCharAt = Double.NaN;
            } else {
                dCharAt = str.charAt(number);
            }
        } else {
            dCharAt = Double.NaN;
        }
        return Double.valueOf(dCharAt);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0022  */
    static final Object getMember$lambda$36(Object obj, List args) {
        double dCharAt;
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        int number = orNull != null ? (int) JsInterpreterKt.toNumber(orNull) : 0;
        if (number >= 0) {
            String str = (String) obj;
            if (number >= str.length()) {
                dCharAt = Double.NaN;
            } else {
                dCharAt = str.charAt(number);
            }
        } else {
            dCharAt = Double.NaN;
        }
        return Double.valueOf(dCharAt);
    }

    static final Object getMember$lambda$37(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        String upperCase = ((String) obj).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return upperCase;
    }

    static final Object getMember$lambda$38(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        String lowerCase = ((String) obj).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    static final Object getMember$lambda$39(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        return StringsKt.trim((CharSequence) obj).toString();
    }

    static final Object getMember$lambda$40(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        return StringsKt.trimStart((CharSequence) obj).toString();
    }

    static final Object getMember$lambda$41(Object obj, List list) {
        Intrinsics.checkNotNullParameter(list, "<unused var>");
        return StringsKt.trimEnd((CharSequence) obj).toString();
    }

    static final Object getMember$lambda$42(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return StringsKt.repeat((CharSequence) obj, RangesKt.coerceAtLeast((int) JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0)), 0));
    }

    static final Object getMember$lambda$43(Object obj, List args) {
        String jsString;
        Intrinsics.checkNotNullParameter(args, "args");
        int number = (int) JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0));
        Object orNull = CollectionsKt.getOrNull(args, 1);
        if (orNull == null || (jsString = JsInterpreterKt.toJsString(orNull)) == null) {
            jsString = Stream.ID_UNKNOWN;
        }
        String str = (String) obj;
        if (str.length() >= number) {
            return str;
        }
        return StringsKt.takeLast(StringsKt.repeat(jsString, number) + str, number);
    }

    static final Object getMember$lambda$44(Object obj, List args) {
        String jsString;
        Intrinsics.checkNotNullParameter(args, "args");
        int number = (int) JsInterpreterKt.toNumber(CollectionsKt.getOrNull(args, 0));
        Object orNull = CollectionsKt.getOrNull(args, 1);
        if (orNull == null || (jsString = JsInterpreterKt.toJsString(orNull)) == null) {
            jsString = Stream.ID_UNKNOWN;
        }
        String str = (String) obj;
        if (str.length() >= number) {
            return str;
        }
        return StringsKt.take(str + StringsKt.repeat(jsString, number), number);
    }

    static final Object getMember$lambda$47(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        try {
            MatchResult matchResultFind$default = Regex.find$default(new Regex(JsInterpreterKt.toJsString(CollectionsKt.getOrNull(args, 0))), (CharSequence) obj, 0, 2, null);
            if (matchResultFind$default != null) {
                List<String> groupValues = matchResultFind$default.getGroupValues();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(groupValues, 10));
                Iterator<T> it = groupValues.iterator();
                while (it.hasNext()) {
                    arrayList.add((String) it.next());
                }
                return new JsList(CollectionsKt.toMutableList((Collection) arrayList));
            }
        } catch (Exception unused) {
        }
        return null;
    }

    static final Object getMember$lambda$49(Object obj, List args) {
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        int number = orNull != null ? (int) JsInterpreterKt.toNumber(orNull) : 10;
        if (number == 10) {
            return JsInterpreterKt.toJsString(obj);
        }
        String string = Long.toString((long) ((Number) obj).doubleValue(), CharsKt.checkRadix(number));
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    static final Object getMember$lambda$50(Object obj, List args) {
        StringBuilder sb;
        Intrinsics.checkNotNullParameter(args, "args");
        Object orNull = CollectionsKt.getOrNull(args, 0);
        int iCoerceIn = RangesKt.coerceIn(orNull != null ? (int) JsInterpreterKt.toNumber(orNull) : 0, 0, 20);
        double dPow = Math.pow(10.0d, iCoerceIn);
        double dRint = Math.rint(((Number) obj).doubleValue() * dPow) / dPow;
        String str = dRint < 0.0d ? "-" : "";
        double dAbs = Math.abs(dRint);
        long j = (long) dAbs;
        String strPadStart = StringsKt.padStart(String.valueOf((long) Math.rint((dAbs - j) * dPow)), iCoerceIn, '0');
        if (iCoerceIn == 0) {
            sb = new StringBuilder();
            sb.append(str);
            sb.append(j);
        } else {
            sb = new StringBuilder();
            sb.append(str);
            sb.append(j);
            sb.append('.');
            sb.append(strPadStart);
        }
        return sb.toString();
    }

    private final Object evalCall(CallExpr node, Scope scope) {
        Pair<Object, Object> pairResolveCallee = resolveCallee(node.getCallee(), scope);
        Object objComponent1 = pairResolveCallee.component1();
        Object objComponent2 = pairResolveCallee.component2();
        List<Node> args = node.getArgs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(args, 10));
        Iterator<T> it = args.iterator();
        while (it.hasNext()) {
            arrayList.add(evalExpr((Node) it.next(), scope));
        }
        return callAny(objComponent1, arrayList, objComponent2);
    }

    private final Pair<Object, Object> resolveCallee(Node calleeNode, Scope scope) {
        if (calleeNode instanceof MemberExpr) {
            MemberExpr memberExpr = (MemberExpr) calleeNode;
            Object objEvalExpr = evalExpr(memberExpr.getObj(), scope);
            return TuplesKt.to(getMember(objEvalExpr, propKey(memberExpr.getProp(), memberExpr.getComputed(), scope)), objEvalExpr);
        }
        return TuplesKt.to(evalExpr(calleeNode, scope), null);
    }

    private final Object evalNew(NewExpr node, Scope scope) throws JsExecutionLimitExceeded, BreakSignal, ContinueSignal, ThrowSignal {
        Object objEvalExpr = evalExpr(node.getCallee(), scope);
        List<Node> args = node.getArgs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(args, 10));
        Iterator<T> it = args.iterator();
        while (it.hasNext()) {
            arrayList.add(evalExpr((Node) it.next(), scope));
        }
        ArrayList arrayList2 = arrayList;
        if (objEvalExpr instanceof NativeFn) {
            return ((NativeFn) objEvalExpr).getFn().invoke(arrayList2);
        }
        JsObject jsObject = new JsObject(null, objEvalExpr instanceof JsFunction ? (JsFunction) objEvalExpr : null, 1, null);
        Object objCallAny = callAny(objEvalExpr, arrayList2, jsObject);
        return objCallAny instanceof JsObject ? (JsObject) objCallAny : jsObject;
    }

    private final Object callAny(Object callee, List<? extends Object> args, Object thisVal) throws JsExecutionLimitExceeded, BreakSignal, ContinueSignal, ThrowSignal {
        if (callee instanceof NativeFn) {
            return ((NativeFn) callee).getFn().invoke(args);
        }
        if (callee instanceof JsFunction) {
            JsFunction jsFunction = (JsFunction) callee;
            Scope scope = new Scope(jsFunction.getClosure());
            scope.define("this", thisVal);
            scope.define("arguments", new JsList(CollectionsKt.toMutableList((Collection) args)));
            int i = 0;
            for (Object obj : jsFunction.getParams()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                scope.define((String) obj, (i < 0 || i >= args.size()) ? Unit.INSTANCE : args.get(i));
                i = i2;
            }
            try {
                Object objExecNode = Unit.INSTANCE;
                Iterator<Node> it = ((JsFunction) callee).getBody().iterator();
                while (it.hasNext()) {
                    objExecNode = execNode(it.next(), scope);
                }
                return objExecNode;
            } catch (ReturnSignal e) {
                return e.getValue();
            }
        }
        return Unit.INSTANCE;
    }
}
