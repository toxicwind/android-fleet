package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.ReflectJvmMapping;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ReflectionCache.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\n2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\u0019J\"\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00152\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000b0\u0019J+\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0017\u001a\u00020\u00102\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0019¢\u0006\u0002\u0010\u001bJ\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007J\u001c\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000e2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\rJ\u0014\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e2\u0006\u0010\u0017\u001a\u00020\u0013R&\u0010\u0005\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000e0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "", "reflectionCacheSize", "", "(I)V", "javaClassToKotlin", "Lcom/fasterxml/jackson/databind/util/LRUMap;", "Ljava/lang/Class;", "Lkotlin/reflect/KClass;", "javaConstructorIsCreatorAnnotated", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedConstructor;", "", "javaConstructorToKotlin", "Ljava/lang/reflect/Constructor;", "Lkotlin/reflect/KFunction;", "javaMemberIsRequired", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "javaMethodToKotlin", "Ljava/lang/reflect/Method;", "kotlinGeneratedMethod", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMethod;", "checkConstructorIsCreatorAnnotated", "key", "calc", "Lkotlin/Function1;", "isKotlinGeneratedMethod", "(Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;Lkotlin/jvm/functions/Function1;)Ljava/lang/Boolean;", "kotlinFromJava", "BooleanTriState", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ReflectionCache {
    private final LRUMap<Class<Object>, KClass<Object>> javaClassToKotlin;
    private final LRUMap<AnnotatedConstructor, Boolean> javaConstructorIsCreatorAnnotated;
    private final LRUMap<Constructor<Object>, KFunction<Object>> javaConstructorToKotlin;
    private final LRUMap<AnnotatedMember, BooleanTriState> javaMemberIsRequired;
    private final LRUMap<Method, KFunction<?>> javaMethodToKotlin;
    private final LRUMap<AnnotatedMethod, Boolean> kotlinGeneratedMethod;

    /* JADX INFO: compiled from: ReflectionCache.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \b2\u00020\u0001:\u0004\b\t\n\u000bB\u0011\b\u0004\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\f\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/Boolean;)V", "getValue", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "Companion", "Empty", "False", "True", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$True;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$False;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$Empty;", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static abstract class BooleanTriState {
        private final Boolean value;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final True TRUE = new True();
        private static final False FALSE = new False();
        private static final Empty EMPTY = new Empty();

        public /* synthetic */ BooleanTriState(Boolean bool, DefaultConstructorMarker defaultConstructorMarker) {
            this(bool);
        }

        /* JADX INFO: compiled from: ReflectionCache.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$True;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "()V", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class True extends BooleanTriState {
            public True() {
                super(true, null);
            }
        }

        private BooleanTriState(Boolean bool) {
            this.value = bool;
        }

        public final Boolean getValue() {
            return this.value;
        }

        /* JADX INFO: compiled from: ReflectionCache.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$False;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "()V", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class False extends BooleanTriState {
            public False() {
                super(false, null);
            }
        }

        /* JADX INFO: compiled from: ReflectionCache.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$Empty;", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "()V", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class Empty extends BooleanTriState {
            /* JADX WARN: Multi-variable type inference failed */
            public Empty() {
                super(null, 0 == true ? 1 : 0);
            }
        }

        /* JADX INFO: compiled from: ReflectionCache.kt */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$Companion;", "", "()V", "EMPTY", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$Empty;", "FALSE", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$False;", "TRUE", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState$True;", "fromBoolean", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/Boolean;)Lcom/fasterxml/jackson/module/kotlin/ReflectionCache$BooleanTriState;", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final BooleanTriState fromBoolean(Boolean value) {
                if (value == null) {
                    return BooleanTriState.EMPTY;
                }
                if (Intrinsics.areEqual((Object) value, (Object) true)) {
                    return BooleanTriState.TRUE;
                }
                if (Intrinsics.areEqual((Object) value, (Object) false)) {
                    return BooleanTriState.FALSE;
                }
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    public ReflectionCache(int i) {
        this.javaClassToKotlin = new LRUMap<>(i, i);
        this.javaConstructorToKotlin = new LRUMap<>(i, i);
        this.javaMethodToKotlin = new LRUMap<>(i, i);
        this.javaConstructorIsCreatorAnnotated = new LRUMap<>(i, i);
        this.javaMemberIsRequired = new LRUMap<>(i, i);
        this.kotlinGeneratedMethod = new LRUMap<>(i, i);
    }

    public final KClass<Object> kotlinFromJava(Class<Object> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        KClass<Object> kClass = this.javaClassToKotlin.get(key);
        if (kClass != null) {
            return kClass;
        }
        KClass<Object> kotlinClass = JvmClassMappingKt.getKotlinClass(key);
        KClass<Object> kClassPutIfAbsent = this.javaClassToKotlin.putIfAbsent(key, kotlinClass);
        return kClassPutIfAbsent == null ? kotlinClass : kClassPutIfAbsent;
    }

    public final KFunction<Object> kotlinFromJava(Constructor<Object> key) {
        Intrinsics.checkNotNullParameter(key, "key");
        KFunction<Object> kFunction = this.javaConstructorToKotlin.get(key);
        if (kFunction != null) {
            return kFunction;
        }
        KFunction<Object> kotlinFunction = ReflectJvmMapping.getKotlinFunction(key);
        if (kotlinFunction == null) {
            return null;
        }
        KFunction<Object> kFunctionPutIfAbsent = this.javaConstructorToKotlin.putIfAbsent(key, kotlinFunction);
        return kFunctionPutIfAbsent == null ? kotlinFunction : kFunctionPutIfAbsent;
    }

    public final KFunction<?> kotlinFromJava(Method key) {
        Intrinsics.checkNotNullParameter(key, "key");
        KFunction<?> kFunction = this.javaMethodToKotlin.get(key);
        if (kFunction != null) {
            return kFunction;
        }
        KFunction<?> kotlinFunction = ReflectJvmMapping.getKotlinFunction(key);
        if (kotlinFunction == null) {
            return null;
        }
        KFunction<?> kFunctionPutIfAbsent = this.javaMethodToKotlin.putIfAbsent(key, kotlinFunction);
        return kFunctionPutIfAbsent == null ? kotlinFunction : kFunctionPutIfAbsent;
    }

    public final boolean checkConstructorIsCreatorAnnotated(AnnotatedConstructor key, Function1<? super AnnotatedConstructor, Boolean> calc) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(calc, "calc");
        Boolean bool = this.javaConstructorIsCreatorAnnotated.get(key);
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean boolInvoke = calc.invoke(key);
        boolean zBooleanValue = boolInvoke.booleanValue();
        Boolean boolPutIfAbsent = this.javaConstructorIsCreatorAnnotated.putIfAbsent(key, boolInvoke);
        return boolPutIfAbsent == null ? zBooleanValue : boolPutIfAbsent.booleanValue();
    }

    public final Boolean javaMemberIsRequired(AnnotatedMember key, Function1<? super AnnotatedMember, Boolean> calc) {
        Boolean value;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(calc, "calc");
        BooleanTriState booleanTriState = this.javaMemberIsRequired.get(key);
        Boolean value2 = booleanTriState == null ? null : booleanTriState.getValue();
        if (value2 != null) {
            return value2;
        }
        Boolean boolInvoke = calc.invoke(key);
        BooleanTriState booleanTriStatePutIfAbsent = this.javaMemberIsRequired.putIfAbsent(key, BooleanTriState.INSTANCE.fromBoolean(boolInvoke));
        return (booleanTriStatePutIfAbsent == null || (value = booleanTriStatePutIfAbsent.getValue()) == null) ? boolInvoke : value;
    }

    public final boolean isKotlinGeneratedMethod(AnnotatedMethod key, Function1<? super AnnotatedMethod, Boolean> calc) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(calc, "calc");
        Boolean bool = this.kotlinGeneratedMethod.get(key);
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean boolInvoke = calc.invoke(key);
        boolean zBooleanValue = boolInvoke.booleanValue();
        Boolean boolPutIfAbsent = this.kotlinGeneratedMethod.putIfAbsent(key, boolInvoke);
        return boolPutIfAbsent == null ? zBooleanValue : boolPutIfAbsent.booleanValue();
    }
}
