package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.full.KCallables;
import kotlin.reflect.jvm.KCallablesJvm;
import kotlin.reflect.jvm.ReflectJvmMapping;

/* JADX INFO: compiled from: KotlinValueInstantiator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ/\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016¢\u0006\u0002\u0010\u0014J\f\u0010\u0015\u001a\u00020\u0006*\u00020\u0011H\u0002J\f\u0010\u0016\u001a\u00020\u0006*\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinValueInstantiator;", "Lcom/fasterxml/jackson/databind/deser/std/StdValueInstantiator;", "src", "cache", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "nullToEmptyCollection", "", "nullToEmptyMap", "nullIsSameAsDefault", "strictNullChecks", "(Lcom/fasterxml/jackson/databind/deser/std/StdValueInstantiator;Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;ZZZZ)V", "createFromObjectWith", "", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "props", "", "Lcom/fasterxml/jackson/databind/deser/SettableBeanProperty;", "buffer", "Lcom/fasterxml/jackson/databind/deser/impl/PropertyValueBuffer;", "(Lcom/fasterxml/jackson/databind/DeserializationContext;[Lcom/fasterxml/jackson/databind/deser/SettableBeanProperty;Lcom/fasterxml/jackson/databind/deser/impl/PropertyValueBuffer;)Ljava/lang/Object;", "hasInjectableValueId", "isPrimitive", "Lkotlin/reflect/KParameter;", "jackson-module-kotlin"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class KotlinValueInstantiator extends StdValueInstantiator {
    private final ReflectionCache cache;
    private final boolean nullIsSameAsDefault;
    private final boolean nullToEmptyCollection;
    private final boolean nullToEmptyMap;
    private final boolean strictNullChecks;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinValueInstantiator(StdValueInstantiator src, ReflectionCache cache, boolean z, boolean z2, boolean z3, boolean z4) {
        super(src);
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.checkNotNullParameter(cache, "cache");
        this.cache = cache;
        this.nullToEmptyCollection = z;
        this.nullToEmptyMap = z2;
        this.nullIsSameAsDefault = z3;
        this.strictNullChecks = z4;
    }

    /* JADX WARN: Code duplicated, block: B:140:0x029f  */
    /* JADX WARN: Code duplicated, block: B:151:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:154:0x02da  */
    /* JADX WARN: Code duplicated, block: B:157:0x02fc  */
    /* JADX WARN: Code duplicated, block: B:161:0x030e  */
    /* JADX WARN: Code duplicated, block: B:164:0x0324  */
    /* JADX WARN: Code duplicated, block: B:171:0x033a  */
    /* JADX WARN: Code duplicated, block: B:174:0x035a A[LOOP:3: B:170:0x0338->B:174:0x035a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:175:0x035f  */
    /* JADX WARN: Code duplicated, block: B:178:0x036b A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:223:0x0300 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:224:0x02e6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:225:? A[LOOP:2: B:152:0x02d4->B:225:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:226:0x0363 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:227:0x033e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:0x017f  */
    @Override // com.fasterxml.jackson.databind.deser.ValueInstantiator
    public Object createFromObjectWith(DeserializationContext ctxt, SettableBeanProperty[] props, PropertyValueBuffer buffer) throws IllegalAccessException, DatabindException {
        KFunction<Object> kFunctionKotlinFromJava;
        Object parameter;
        int i;
        int i2;
        KParameter[] kParameterArr;
        KType type;
        String str;
        KType kType;
        String str2;
        String str3;
        KType type2;
        KType kType2;
        String str4;
        String str5;
        KType type3;
        KTypeProjection kTypeProjection;
        KType type4;
        Object[] objArr;
        int length;
        int i3;
        KTypeProjection kTypeProjection2;
        KType type5;
        Map map;
        Iterator it;
        KType type6;
        Field field;
        Object objectInstance;
        SettableBeanProperty[] props2 = props;
        PropertyValueBuffer buffer2 = buffer;
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        Intrinsics.checkNotNullParameter(props2, "props");
        Intrinsics.checkNotNullParameter(buffer2, "buffer");
        AnnotatedWithParams annotatedWithParams = this._withArgsCreator;
        if (annotatedWithParams instanceof AnnotatedConstructor) {
            ReflectionCache reflectionCache = this.cache;
            AnnotatedElement annotated = this._withArgsCreator.getAnnotated();
            if (annotated == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.reflect.Constructor<kotlin.Any>");
            }
            kFunctionKotlinFromJava = reflectionCache.kotlinFromJava((Constructor<Object>) annotated);
        } else {
            if (!(annotatedWithParams instanceof AnnotatedMethod)) {
                throw new IllegalStateException(Intrinsics.stringPlus("Expected a constructor or method to create a Kotlin object, instead found ", this._withArgsCreator.getAnnotated().getClass().getName()));
            }
            ReflectionCache reflectionCache2 = this.cache;
            AnnotatedElement annotated2 = this._withArgsCreator.getAnnotated();
            if (annotated2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.reflect.Method");
            }
            kFunctionKotlinFromJava = reflectionCache2.kotlinFromJava((Method) annotated2);
        }
        KFunction<Object> kFunction = kFunctionKotlinFromJava;
        if (kFunction == null) {
            return super.createFromObjectWith(ctxt, props, buffer);
        }
        KFunction<Object> kFunction2 = kFunction;
        if (KCallables.getExtensionReceiverParameter(kFunction2) != null) {
            return super.createFromObjectWith(ctxt, props, buffer);
        }
        int length2 = (KCallables.getInstanceParameter(kFunction2) != null ? 1 : 0) + props2.length;
        KParameter[] kParameterArr2 = new KParameter[length2];
        Object[] objArr2 = new Object[length2];
        if (KCallables.getInstanceParameter(kFunction2) != null) {
            KParameter instanceParameter = KCallables.getInstanceParameter(kFunction2);
            Intrinsics.checkNotNull(instanceParameter);
            KClass kotlinClass = JvmClassMappingKt.getKotlinClass(TypesKt.erasedType(instanceParameter.getType()));
            if (!kotlinClass.isCompanion()) {
                return super.createFromObjectWith(ctxt, props, buffer);
            }
            try {
                objectInstance = kotlinClass.getObjectInstance();
                parameter = null;
                i = 0;
            } catch (IllegalAccessException e) {
                Field[] fields = JvmClassMappingKt.getJavaClass(kotlinClass).getEnclosingClass().getFields();
                Intrinsics.checkNotNullExpressionValue(fields, "possibleCompanion.java.enclosingClass.fields");
                Field[] fieldArr = fields;
                int length3 = fieldArr.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length3) {
                        i = 0;
                        field = null;
                        break;
                    }
                    field = fieldArr[i4];
                    i = 0;
                    Class<?> type7 = field.getType();
                    Intrinsics.checkNotNullExpressionValue(type7, "it.type");
                    if (JvmClassMappingKt.getKotlinClass(type7).isCompanion()) {
                        break;
                    }
                    i4++;
                }
                Field field2 = field;
                if (field2 == null) {
                    throw e;
                }
                boolean zIsAccessible = field2.isAccessible();
                if ((!zIsAccessible && ctxt.getConfig().isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)) || (zIsAccessible && ctxt.getConfig().isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS))) {
                    field2.setAccessible(true);
                }
                parameter = null;
                Object obj = field2.get(null);
                if (obj == null) {
                    throw e;
                }
                objectInstance = obj;
            }
            objArr2[i] = objectInstance;
            kParameterArr2[i] = KCallables.getInstanceParameter(kFunction2);
            i2 = 1;
        } else {
            parameter = null;
            i = 0;
            i2 = 0;
        }
        Iterator it2 = KCallables.getValueParameters(kFunction2).iterator();
        int i5 = i;
        while (it2.hasNext()) {
            Object next = it2.next();
            i5++;
            if (i5 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KParameter kParameter = (KParameter) next;
            SettableBeanProperty settableBeanProperty = props2[i5];
            boolean zHasParameter = buffer2.hasParameter(settableBeanProperty);
            if (zHasParameter || !kParameter.isOptional()) {
                if (zHasParameter || isPrimitive(kParameter) || hasInjectableValueId(settableBeanProperty)) {
                    parameter = buffer2.getParameter(settableBeanProperty);
                    if (!this.nullIsSameAsDefault || parameter != null || !kParameter.isOptional()) {
                    }
                } else if (!kParameter.getType().getIsMarkedNullable()) {
                    JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
                    parameter = valueDeserializer == null ? null : valueDeserializer.getAbsentValue(ctxt);
                }
                if (parameter == null && ((this.nullToEmptyCollection && settableBeanProperty.getType().isCollectionLikeType()) || (this.nullToEmptyMap && settableBeanProperty.getType().isMapLikeType()))) {
                    parameter = new NullsAsEmptyProvider(settableBeanProperty.getValueDeserializer()).getNullValue(ctxt);
                }
                boolean z = ReflectJvmMapping.getJavaType(kParameter.getType()) instanceof TypeVariable;
                Iterator it3 = it2;
                if ((parameter == null && !zHasParameter && settableBeanProperty.isRequired()) || (!z && parameter == null && !kParameter.getType().getIsMarkedNullable())) {
                    MissingKotlinParameterException missingKotlinParameterException = new MissingKotlinParameterException(kParameter, ctxt.getParser(), "Instantiation of " + ((Object) getValueTypeDesc()) + " value failed for JSON property " + ((Object) settableBeanProperty.getName()) + " due to missing (therefore NULL) value for creator parameter " + ((Object) kParameter.getName()) + " which is a non-nullable type");
                    Class<?> valueClass = getValueClass();
                    String name = settableBeanProperty.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "jsonProp.name");
                    JsonMappingException jsonMappingExceptionWrapWithPath = ExtensionsKt.wrapWithPath(missingKotlinParameterException, valueClass, name);
                    Intrinsics.checkNotNullExpressionValue(jsonMappingExceptionWrapWithPath, "MissingKotlinParameterEx…alueClass, jsonProp.name)");
                    throw jsonMappingExceptionWrapWithPath;
                }
                if (!this.strictNullChecks || parameter == null) {
                    kParameterArr = kParameterArr2;
                } else {
                    if (settableBeanProperty.getType().isCollectionLikeType()) {
                        kParameterArr = kParameterArr2;
                        KTypeProjection kTypeProjection3 = (KTypeProjection) CollectionsKt.getOrNull(kParameter.getType().getArguments(), i);
                        if (kTypeProjection3 != null && (type6 = kTypeProjection3.getType()) != null && !type6.getIsMarkedNullable()) {
                            Collection collection = (Collection) parameter;
                            if (!(collection instanceof Collection) || !collection.isEmpty()) {
                                Iterator it4 = collection.iterator();
                                while (true) {
                                    if (it4.hasNext()) {
                                        if (it4.next() == null) {
                                            type = kParameter.getType().getArguments().get(0).getType();
                                            str = "collection";
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (settableBeanProperty.getType().isMapLikeType()) {
                            kType = type;
                            str2 = str;
                            kTypeProjection2 = (KTypeProjection) CollectionsKt.getOrNull(kParameter.getType().getArguments(), 1);
                            if (kTypeProjection2 == null && (type5 = kTypeProjection2.getType()) != null && !type5.getIsMarkedNullable()) {
                                map = (Map) parameter;
                                if (!map.isEmpty()) {
                                    it = map.entrySet().iterator();
                                    while (true) {
                                        if (it.hasNext()) {
                                            if (((Map.Entry) it.next()).getValue() == null) {
                                                type2 = kParameter.getType().getArguments().get(1).getType();
                                                str3 = "map";
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            if (settableBeanProperty.getType().isArrayType()) {
                                kType2 = type2;
                                str4 = str3;
                                kTypeProjection = (KTypeProjection) CollectionsKt.getOrNull(kParameter.getType().getArguments(), 0);
                                if (kTypeProjection == null && (type4 = kTypeProjection.getType()) != null && !type4.getIsMarkedNullable()) {
                                    objArr = (Object[]) parameter;
                                    length = objArr.length;
                                    i3 = 0;
                                    while (true) {
                                        if (i3 < length) {
                                            if (objArr[i3] == null) {
                                                type3 = kParameter.getType().getArguments().get(0).getType();
                                                i = 0;
                                                str5 = "array";
                                                break;
                                            }
                                            i3++;
                                        }
                                    }
                                }
                                if (str5 != null && type3 != null) {
                                    MissingKotlinParameterException missingKotlinParameterException2 = new MissingKotlinParameterException(kParameter, ctxt.getParser(), "Instantiation of " + type3 + ' ' + ((Object) str5) + " failed for JSON property " + ((Object) settableBeanProperty.getName()) + " due to null value in a " + ((Object) str5) + " that does not allow null values");
                                    Class<?> valueClass2 = getValueClass();
                                    String name2 = settableBeanProperty.getName();
                                    Intrinsics.checkNotNullExpressionValue(name2, "jsonProp.name");
                                    JsonMappingException jsonMappingExceptionWrapWithPath2 = ExtensionsKt.wrapWithPath(missingKotlinParameterException2, valueClass2, name2);
                                    Intrinsics.checkNotNullExpressionValue(jsonMappingExceptionWrapWithPath2, "MissingKotlinParameterEx…alueClass, jsonProp.name)");
                                    throw jsonMappingExceptionWrapWithPath2;
                                }
                            } else {
                                kType2 = type2;
                                str4 = str3;
                            }
                            i = 0;
                            str5 = str4;
                            type3 = kType2;
                            if (str5 != null) {
                                MissingKotlinParameterException missingKotlinParameterException3 = new MissingKotlinParameterException(kParameter, ctxt.getParser(), "Instantiation of " + type3 + ' ' + ((Object) str5) + " failed for JSON property " + ((Object) settableBeanProperty.getName()) + " due to null value in a " + ((Object) str5) + " that does not allow null values");
                                Class<?> valueClass3 = getValueClass();
                                String name3 = settableBeanProperty.getName();
                                Intrinsics.checkNotNullExpressionValue(name3, "jsonProp.name");
                                JsonMappingException jsonMappingExceptionWrapWithPath3 = ExtensionsKt.wrapWithPath(missingKotlinParameterException3, valueClass3, name3);
                                Intrinsics.checkNotNullExpressionValue(jsonMappingExceptionWrapWithPath3, "MissingKotlinParameterEx…alueClass, jsonProp.name)");
                                throw jsonMappingExceptionWrapWithPath3;
                            }
                        } else {
                            kType = type;
                            str2 = str;
                        }
                        str3 = str2;
                        type2 = kType;
                        if (settableBeanProperty.getType().isArrayType()) {
                            kType2 = type2;
                            str4 = str3;
                            kTypeProjection = (KTypeProjection) CollectionsKt.getOrNull(kParameter.getType().getArguments(), 0);
                            if (kTypeProjection == null) {
                                break;
                            }
                            objArr = (Object[]) parameter;
                            length = objArr.length;
                            i3 = 0;
                            while (true) {
                                if (i3 < length) {
                                    if (objArr[i3] == null) {
                                        type3 = kParameter.getType().getArguments().get(0).getType();
                                        i = 0;
                                        str5 = "array";
                                        break;
                                    }
                                    i3++;
                                }
                            }
                            if (str5 != null) {
                                MissingKotlinParameterException missingKotlinParameterException4 = new MissingKotlinParameterException(kParameter, ctxt.getParser(), "Instantiation of " + type3 + ' ' + ((Object) str5) + " failed for JSON property " + ((Object) settableBeanProperty.getName()) + " due to null value in a " + ((Object) str5) + " that does not allow null values");
                                Class<?> valueClass4 = getValueClass();
                                String name4 = settableBeanProperty.getName();
                                Intrinsics.checkNotNullExpressionValue(name4, "jsonProp.name");
                                JsonMappingException jsonMappingExceptionWrapWithPath4 = ExtensionsKt.wrapWithPath(missingKotlinParameterException4, valueClass4, name4);
                                Intrinsics.checkNotNullExpressionValue(jsonMappingExceptionWrapWithPath4, "MissingKotlinParameterEx…alueClass, jsonProp.name)");
                                throw jsonMappingExceptionWrapWithPath4;
                            }
                        } else {
                            kType2 = type2;
                            str4 = str3;
                        }
                        i = 0;
                        str5 = str4;
                        type3 = kType2;
                        if (str5 != null) {
                            MissingKotlinParameterException missingKotlinParameterException5 = new MissingKotlinParameterException(kParameter, ctxt.getParser(), "Instantiation of " + type3 + ' ' + ((Object) str5) + " failed for JSON property " + ((Object) settableBeanProperty.getName()) + " due to null value in a " + ((Object) str5) + " that does not allow null values");
                            Class<?> valueClass5 = getValueClass();
                            String name5 = settableBeanProperty.getName();
                            Intrinsics.checkNotNullExpressionValue(name5, "jsonProp.name");
                            JsonMappingException jsonMappingExceptionWrapWithPath5 = ExtensionsKt.wrapWithPath(missingKotlinParameterException5, valueClass5, name5);
                            Intrinsics.checkNotNullExpressionValue(jsonMappingExceptionWrapWithPath5, "MissingKotlinParameterEx…alueClass, jsonProp.name)");
                            throw jsonMappingExceptionWrapWithPath5;
                        }
                    } else {
                        kParameterArr = kParameterArr2;
                    }
                    type = null;
                    str = null;
                    if (settableBeanProperty.getType().isMapLikeType()) {
                        kType = type;
                        str2 = str;
                        kTypeProjection2 = (KTypeProjection) CollectionsKt.getOrNull(kParameter.getType().getArguments(), 1);
                        if (kTypeProjection2 == null) {
                            break;
                        }
                        map = (Map) parameter;
                        if (!map.isEmpty()) {
                            break;
                        }
                        it = map.entrySet().iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (((Map.Entry) it.next()).getValue() == null) {
                                    type2 = kParameter.getType().getArguments().get(1).getType();
                                    str3 = "map";
                                    break;
                                }
                            }
                        }
                        if (settableBeanProperty.getType().isArrayType()) {
                            kType2 = type2;
                            str4 = str3;
                            kTypeProjection = (KTypeProjection) CollectionsKt.getOrNull(kParameter.getType().getArguments(), 0);
                            if (kTypeProjection == null) {
                                break;
                            }
                            objArr = (Object[]) parameter;
                            length = objArr.length;
                            i3 = 0;
                            while (true) {
                                if (i3 < length) {
                                    if (objArr[i3] == null) {
                                        type3 = kParameter.getType().getArguments().get(0).getType();
                                        i = 0;
                                        str5 = "array";
                                        break;
                                    }
                                    i3++;
                                }
                            }
                            if (str5 != null) {
                                MissingKotlinParameterException missingKotlinParameterException6 = new MissingKotlinParameterException(kParameter, ctxt.getParser(), "Instantiation of " + type3 + ' ' + ((Object) str5) + " failed for JSON property " + ((Object) settableBeanProperty.getName()) + " due to null value in a " + ((Object) str5) + " that does not allow null values");
                                Class<?> valueClass6 = getValueClass();
                                String name6 = settableBeanProperty.getName();
                                Intrinsics.checkNotNullExpressionValue(name6, "jsonProp.name");
                                JsonMappingException jsonMappingExceptionWrapWithPath6 = ExtensionsKt.wrapWithPath(missingKotlinParameterException6, valueClass6, name6);
                                Intrinsics.checkNotNullExpressionValue(jsonMappingExceptionWrapWithPath6, "MissingKotlinParameterEx…alueClass, jsonProp.name)");
                                throw jsonMappingExceptionWrapWithPath6;
                            }
                        } else {
                            kType2 = type2;
                            str4 = str3;
                        }
                        i = 0;
                        str5 = str4;
                        type3 = kType2;
                        if (str5 != null) {
                            MissingKotlinParameterException missingKotlinParameterException7 = new MissingKotlinParameterException(kParameter, ctxt.getParser(), "Instantiation of " + type3 + ' ' + ((Object) str5) + " failed for JSON property " + ((Object) settableBeanProperty.getName()) + " due to null value in a " + ((Object) str5) + " that does not allow null values");
                            Class<?> valueClass7 = getValueClass();
                            String name7 = settableBeanProperty.getName();
                            Intrinsics.checkNotNullExpressionValue(name7, "jsonProp.name");
                            JsonMappingException jsonMappingExceptionWrapWithPath7 = ExtensionsKt.wrapWithPath(missingKotlinParameterException7, valueClass7, name7);
                            Intrinsics.checkNotNullExpressionValue(jsonMappingExceptionWrapWithPath7, "MissingKotlinParameterEx…alueClass, jsonProp.name)");
                            throw jsonMappingExceptionWrapWithPath7;
                        }
                    } else {
                        kType = type;
                        str2 = str;
                    }
                    str3 = str2;
                    type2 = kType;
                    if (settableBeanProperty.getType().isArrayType()) {
                        kType2 = type2;
                        str4 = str3;
                        kTypeProjection = (KTypeProjection) CollectionsKt.getOrNull(kParameter.getType().getArguments(), 0);
                        if (kTypeProjection == null) {
                            break;
                        }
                        objArr = (Object[]) parameter;
                        length = objArr.length;
                        i3 = 0;
                        while (true) {
                            if (i3 < length) {
                                if (objArr[i3] == null) {
                                    type3 = kParameter.getType().getArguments().get(0).getType();
                                    i = 0;
                                    str5 = "array";
                                    break;
                                }
                                i3++;
                            }
                        }
                        if (str5 != null) {
                            MissingKotlinParameterException missingKotlinParameterException8 = new MissingKotlinParameterException(kParameter, ctxt.getParser(), "Instantiation of " + type3 + ' ' + ((Object) str5) + " failed for JSON property " + ((Object) settableBeanProperty.getName()) + " due to null value in a " + ((Object) str5) + " that does not allow null values");
                            Class<?> valueClass8 = getValueClass();
                            String name8 = settableBeanProperty.getName();
                            Intrinsics.checkNotNullExpressionValue(name8, "jsonProp.name");
                            JsonMappingException jsonMappingExceptionWrapWithPath8 = ExtensionsKt.wrapWithPath(missingKotlinParameterException8, valueClass8, name8);
                            Intrinsics.checkNotNullExpressionValue(jsonMappingExceptionWrapWithPath8, "MissingKotlinParameterEx…alueClass, jsonProp.name)");
                            throw jsonMappingExceptionWrapWithPath8;
                        }
                    } else {
                        kType2 = type2;
                        str4 = str3;
                    }
                    i = 0;
                    str5 = str4;
                    type3 = kType2;
                    if (str5 != null) {
                        MissingKotlinParameterException missingKotlinParameterException9 = new MissingKotlinParameterException(kParameter, ctxt.getParser(), "Instantiation of " + type3 + ' ' + ((Object) str5) + " failed for JSON property " + ((Object) settableBeanProperty.getName()) + " due to null value in a " + ((Object) str5) + " that does not allow null values");
                        Class<?> valueClass9 = getValueClass();
                        String name9 = settableBeanProperty.getName();
                        Intrinsics.checkNotNullExpressionValue(name9, "jsonProp.name");
                        JsonMappingException jsonMappingExceptionWrapWithPath9 = ExtensionsKt.wrapWithPath(missingKotlinParameterException9, valueClass9, name9);
                        Intrinsics.checkNotNullExpressionValue(jsonMappingExceptionWrapWithPath9, "MissingKotlinParameterEx…alueClass, jsonProp.name)");
                        throw jsonMappingExceptionWrapWithPath9;
                    }
                }
                objArr2[i2] = parameter;
                kParameterArr[i2] = kParameter;
                i2++;
                props2 = props;
                buffer2 = buffer;
                kParameterArr2 = kParameterArr;
                it2 = it3;
            }
            parameter = null;
        }
        KParameter[] kParameterArr3 = kParameterArr2;
        if (i2 == length2 && KCallables.getInstanceParameter(kFunction2) == null) {
            return super.createFromObjectWith(ctxt, objArr2);
        }
        boolean zIsAccessible2 = KCallablesJvm.isAccessible(kFunction2);
        if ((!zIsAccessible2 && ctxt.getConfig().isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS)) || (zIsAccessible2 && ctxt.getConfig().isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS))) {
            KCallablesJvm.setAccessible(kFunction2, true);
        }
        Map<KParameter, ? extends Object> linkedHashMap = new LinkedHashMap<>();
        ArrayList arrayList = new ArrayList(length2);
        int i6 = i;
        while (i6 < length2) {
            KParameter kParameter2 = kParameterArr3[i6];
            int i7 = i + 1;
            if (kParameter2 != null) {
                linkedHashMap.put(kParameter2, objArr2[i]);
            }
            arrayList.add(Unit.INSTANCE);
            i6++;
            i = i7;
        }
        return kFunction.callBy(linkedHashMap);
    }

    private final boolean isPrimitive(KParameter kParameter) {
        Type javaType = ReflectJvmMapping.getJavaType(kParameter.getType());
        if (javaType instanceof Class) {
            return ((Class) javaType).isPrimitive();
        }
        return false;
    }

    private final boolean hasInjectableValueId(SettableBeanProperty settableBeanProperty) {
        return settableBeanProperty.getInjectableValueId() != null;
    }
}
