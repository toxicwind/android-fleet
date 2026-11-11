package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.InlineClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.MultiFieldValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.model.RigidTypeMarker;

/* JADX INFO: compiled from: ValueClassUtil.kt */
/* JADX INFO: loaded from: classes7.dex */
public final class ValueClassUtilKt {
    public static final <T extends RigidTypeMarker> ValueClassRepresentation<T> loadValueClassRepresentation(ProtoBuf.Class r4, boolean z, NameResolver nameResolver, TypeTable typeTable, Function1<? super ProtoBuf.Type, ? extends T> typeDeserializer, Function1<? super Name, ? extends T> typeOfPublicProperty) {
        T tInvoke;
        Intrinsics.checkNotNullParameter(r4, "<this>");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        Intrinsics.checkNotNullParameter(typeDeserializer, "typeDeserializer");
        Intrinsics.checkNotNullParameter(typeOfPublicProperty, "typeOfPublicProperty");
        if (r4.hasInlineClassUnderlyingPropertyName()) {
            Name name = NameResolverUtilKt.getName(nameResolver, r4.getInlineClassUnderlyingPropertyName());
            ProtoBuf.Type typeInlineClassUnderlyingType = ProtoTypeTableUtilKt.inlineClassUnderlyingType(r4, typeTable);
            if ((typeInlineClassUnderlyingType == null || (tInvoke = typeDeserializer.invoke(typeInlineClassUnderlyingType)) == null) && (tInvoke = typeOfPublicProperty.invoke(name)) == null) {
                throw new IllegalStateException(("cannot determine underlying type for value class " + NameResolverUtilKt.getName(nameResolver, r4.getFqName()) + " with property " + name).toString());
            }
            return new InlineClassRepresentation(name, tInvoke);
        }
        if (!z || !Flags.IS_VALUE_CLASS.get(r4.getFlags()).booleanValue()) {
            return null;
        }
        List<ProtoBuf.Constructor> constructorList = r4.getConstructorList();
        Intrinsics.checkNotNullExpressionValue(constructorList, "getConstructorList(...)");
        Iterator<T> it = constructorList.iterator();
        boolean z2 = false;
        Object obj = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z2) {
                    break;
                }
                break;
            }
            Object next = it.next();
            if (!Flags.IS_SECONDARY.get(((ProtoBuf.Constructor) next).getFlags()).booleanValue()) {
                if (!z2) {
                    z2 = true;
                    obj = next;
                }
            }
            obj = null;
            break;
        }
        ProtoBuf.Constructor constructor = (ProtoBuf.Constructor) obj;
        if (constructor == null) {
            return null;
        }
        List<ProtoBuf.ValueParameter> valueParameterList = constructor.getValueParameterList();
        Intrinsics.checkNotNullExpressionValue(valueParameterList, "getValueParameterList(...)");
        List<ProtoBuf.ValueParameter> list = valueParameterList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ProtoBuf.ValueParameter valueParameter : list) {
            Name name2 = NameResolverUtilKt.getName(nameResolver, valueParameter.getName());
            Intrinsics.checkNotNull(valueParameter);
            arrayList.add(TuplesKt.to(name2, typeDeserializer.invoke(ProtoTypeTableUtilKt.type(valueParameter, typeTable))));
        }
        return new MultiFieldValueClassRepresentation(arrayList);
    }
}
