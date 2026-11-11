package io.ktor.util.converters;

import io.ktor.util.reflect.TypeInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: ConversionService.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ'\u0010\r\u001a\u0004\u0018\u00010\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00072\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000f¢\u0006\u0004\b\u0011\u0010\u0012J%\u0010\u0013\u001a\u0004\u0018\u00010\u00042\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0005\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lio/ktor/util/converters/DefaultConversionService;", "Lio/ktor/util/converters/ConversionService;", "<init>", "()V", "", ES6Iterator.VALUE_PROPERTY, "", "", "toValues", "(Ljava/lang/Object;)Ljava/util/List;", "values", "Lio/ktor/util/reflect/TypeInfo;", "type", "fromValues", "(Ljava/util/List;Lio/ktor/util/reflect/TypeInfo;)Ljava/lang/Object;", "Lkotlin/reflect/KClass;", "klass", "fromValue", "(Ljava/lang/String;Lkotlin/reflect/KClass;)Ljava/lang/Object;", "convertPrimitives", "(Lkotlin/reflect/KClass;Ljava/lang/String;)Ljava/lang/Object;", "typeName", "", "throwConversionException", "(Ljava/lang/String;)Ljava/lang/Void;", "ktor-utils"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DefaultConversionService implements ConversionService {
    public static final DefaultConversionService INSTANCE = new DefaultConversionService();

    private DefaultConversionService() {
    }

    @Override // io.ktor.util.converters.ConversionService
    public List<String> toValues(Object value) throws DataConversionException {
        if (value == null) {
            return CollectionsKt.emptyList();
        }
        List<String> listPlatformDefaultToValues = ConversionServiceJvmKt.platformDefaultToValues(value);
        if (listPlatformDefaultToValues != null) {
            return listPlatformDefaultToValues;
        }
        if (!(value instanceof Iterable)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(value.getClass());
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE)) || Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Float.TYPE)) || Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE)) || Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE)) || Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Short.TYPE)) || Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Character.TYPE)) || Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE)) || Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                return CollectionsKt.listOf(value.toString());
            }
            throw new DataConversionException("Class " + orCreateKotlinClass + " is not supported in default data conversion service");
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = ((Iterable) value).iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, INSTANCE.toValues(it.next()));
        }
        return arrayList;
    }

    @Override // io.ktor.util.converters.ConversionService
    public Object fromValues(List<String> values, TypeInfo type) throws DataConversionException {
        List<KTypeProjection> arguments;
        KTypeProjection kTypeProjection;
        KType type2;
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(type, "type");
        if (values.isEmpty()) {
            return null;
        }
        if (Intrinsics.areEqual(type.getType(), Reflection.getOrCreateKotlinClass(List.class)) || Intrinsics.areEqual(type.getType(), Reflection.getOrCreateKotlinClass(List.class))) {
            KType kotlinType = type.getKotlinType();
            KClassifier classifier = (kotlinType == null || (arguments = kotlinType.getArguments()) == null || (kTypeProjection = (KTypeProjection) CollectionsKt.single((List) arguments)) == null || (type2 = kTypeProjection.getType()) == null) ? null : type2.getClassifier();
            KClass<?> kClass = classifier instanceof KClass ? (KClass) classifier : null;
            if (kClass != null) {
                List<String> list = values;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(INSTANCE.fromValue((String) it.next(), kClass));
                }
                return arrayList;
            }
        }
        if (values.isEmpty()) {
            throw new DataConversionException("There are no values when trying to construct single value " + type);
        }
        if (values.size() > 1) {
            throw new DataConversionException("There are multiple values when trying to construct single value " + type);
        }
        return fromValue((String) CollectionsKt.single((List) values), type.getType());
    }

    public final Object fromValue(String value, KClass<?> klass) throws DataConversionException {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(klass, "klass");
        Object objConvertPrimitives = convertPrimitives(klass, value);
        if (objConvertPrimitives != null) {
            return objConvertPrimitives;
        }
        Object objPlatformDefaultFromValues = ConversionServiceJvmKt.platformDefaultFromValues(value, klass);
        if (objPlatformDefaultFromValues != null) {
            return objPlatformDefaultFromValues;
        }
        throwConversionException(klass.toString());
        throw new KotlinNothingValueException();
    }

    private final Object convertPrimitives(KClass<?> klass, String value) {
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            return Integer.valueOf(Integer.parseInt(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Float.TYPE))) {
            return Float.valueOf(Float.parseFloat(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return Double.valueOf(Double.parseDouble(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            return Long.valueOf(Long.parseLong(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Short.TYPE))) {
            return Short.valueOf(Short.parseShort(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Character.TYPE))) {
            return Character.valueOf(StringsKt.single(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            return Boolean.valueOf(Boolean.parseBoolean(value));
        }
        if (Intrinsics.areEqual(klass, Reflection.getOrCreateKotlinClass(String.class))) {
            return value;
        }
        return null;
    }

    private final Void throwConversionException(String typeName) throws DataConversionException {
        throw new DataConversionException("Type " + typeName + " is not supported in default data conversion service");
    }
}
