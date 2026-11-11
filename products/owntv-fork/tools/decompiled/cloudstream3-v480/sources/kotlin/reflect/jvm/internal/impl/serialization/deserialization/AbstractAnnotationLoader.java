package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.serialization.SerializerExtensionProtocol;

/* JADX INFO: compiled from: AbstractAnnotationLoader.kt */
/* JADX INFO: loaded from: classes7.dex */
public abstract class AbstractAnnotationLoader<A> implements AnnotationLoader<A> {
    private final SerializerExtensionProtocol protocol;

    /* JADX INFO: compiled from: AbstractAnnotationLoader.kt */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnnotatedCallableKind.values().length];
            try {
                iArr[AnnotatedCallableKind.PROPERTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AnnotatedCallableKind.PROPERTY_GETTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AnnotatedCallableKind.PROPERTY_SETTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AbstractAnnotationLoader(SerializerExtensionProtocol protocol) {
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        this.protocol = protocol;
    }

    protected final SerializerExtensionProtocol getProtocol() {
        return this.protocol;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadClassAnnotations(ProtoContainer.Class container) {
        Intrinsics.checkNotNullParameter(container, "container");
        List<ProtoBuf.Annotation> annotationList = container.getClassProto().getAnnotationList();
        Intrinsics.checkNotNullExpressionValue(annotationList, "getAnnotationList(...)");
        return loadAnnotations(annotationList, (List) container.getClassProto().getExtension(this.protocol.getClassAnnotation()), container.getNameResolver());
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadCallableAnnotations(ProtoContainer container, MessageLite proto, AnnotatedCallableKind kind) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (proto instanceof ProtoBuf.Constructor) {
            ProtoBuf.Constructor constructor = (ProtoBuf.Constructor) proto;
            List<ProtoBuf.Annotation> annotationList = constructor.getAnnotationList();
            Intrinsics.checkNotNullExpressionValue(annotationList, "getAnnotationList(...)");
            return loadAnnotations(annotationList, (List) constructor.getExtension(this.protocol.getConstructorAnnotation()), container.getNameResolver());
        }
        if (proto instanceof ProtoBuf.Function) {
            ProtoBuf.Function function = (ProtoBuf.Function) proto;
            List<ProtoBuf.Annotation> annotationList2 = function.getAnnotationList();
            Intrinsics.checkNotNullExpressionValue(annotationList2, "getAnnotationList(...)");
            return loadAnnotations(annotationList2, (List) function.getExtension(this.protocol.getFunctionAnnotation()), container.getNameResolver());
        }
        if (!(proto instanceof ProtoBuf.Property)) {
            throw new IllegalStateException(("Unknown message: " + proto).toString());
        }
        int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i == 1) {
            ProtoBuf.Property property = (ProtoBuf.Property) proto;
            List<ProtoBuf.Annotation> annotationList3 = property.getAnnotationList();
            Intrinsics.checkNotNullExpressionValue(annotationList3, "getAnnotationList(...)");
            return loadAnnotations(annotationList3, (List) property.getExtension(this.protocol.getPropertyAnnotation()), container.getNameResolver());
        }
        if (i == 2) {
            ProtoBuf.Property property2 = (ProtoBuf.Property) proto;
            List<ProtoBuf.Annotation> getterAnnotationList = property2.getGetterAnnotationList();
            Intrinsics.checkNotNullExpressionValue(getterAnnotationList, "getGetterAnnotationList(...)");
            return loadAnnotations(getterAnnotationList, (List) property2.getExtension(this.protocol.getPropertyGetterAnnotation()), container.getNameResolver());
        }
        if (i == 3) {
            ProtoBuf.Property property3 = (ProtoBuf.Property) proto;
            List<ProtoBuf.Annotation> setterAnnotationList = property3.getSetterAnnotationList();
            Intrinsics.checkNotNullExpressionValue(setterAnnotationList, "getSetterAnnotationList(...)");
            return loadAnnotations(setterAnnotationList, (List) property3.getExtension(this.protocol.getPropertySetterAnnotation()), container.getNameResolver());
        }
        throw new IllegalStateException("Unsupported callable kind with property proto".toString());
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadPropertyBackingFieldAnnotations(ProtoContainer container, ProtoBuf.Property proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        List<ProtoBuf.Annotation> backingFieldAnnotationList = proto.getBackingFieldAnnotationList();
        Intrinsics.checkNotNullExpressionValue(backingFieldAnnotationList, "getBackingFieldAnnotationList(...)");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> propertyBackingFieldAnnotation = this.protocol.getPropertyBackingFieldAnnotation();
        return loadAnnotations(backingFieldAnnotationList, propertyBackingFieldAnnotation != null ? (List) proto.getExtension(propertyBackingFieldAnnotation) : null, container.getNameResolver());
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadPropertyDelegateFieldAnnotations(ProtoContainer container, ProtoBuf.Property proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        List<ProtoBuf.Annotation> delegateFieldAnnotationList = proto.getDelegateFieldAnnotationList();
        Intrinsics.checkNotNullExpressionValue(delegateFieldAnnotationList, "getDelegateFieldAnnotationList(...)");
        GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> propertyDelegatedFieldAnnotation = this.protocol.getPropertyDelegatedFieldAnnotation();
        return loadAnnotations(delegateFieldAnnotationList, propertyDelegatedFieldAnnotation != null ? (List) proto.getExtension(propertyDelegatedFieldAnnotation) : null, container.getNameResolver());
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadEnumEntryAnnotations(ProtoContainer container, ProtoBuf.EnumEntry proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        List<ProtoBuf.Annotation> annotationList = proto.getAnnotationList();
        Intrinsics.checkNotNullExpressionValue(annotationList, "getAnnotationList(...)");
        return loadAnnotations(annotationList, (List) proto.getExtension(this.protocol.getEnumEntryAnnotation()), container.getNameResolver());
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadValueParameterAnnotations(ProtoContainer container, MessageLite callableProto, AnnotatedCallableKind kind, int i, ProtoBuf.ValueParameter proto) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(callableProto, "callableProto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(proto, "proto");
        List<ProtoBuf.Annotation> annotationList = proto.getAnnotationList();
        Intrinsics.checkNotNullExpressionValue(annotationList, "getAnnotationList(...)");
        return loadAnnotations(annotationList, (List) proto.getExtension(this.protocol.getParameterAnnotation()), container.getNameResolver());
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadExtensionReceiverParameterAnnotations(ProtoContainer container, MessageLite proto, AnnotatedCallableKind kind) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (proto instanceof ProtoBuf.Function) {
            ProtoBuf.Function function = (ProtoBuf.Function) proto;
            List<ProtoBuf.Annotation> extensionReceiverAnnotationList = function.getExtensionReceiverAnnotationList();
            Intrinsics.checkNotNullExpressionValue(extensionReceiverAnnotationList, "getExtensionReceiverAnnotationList(...)");
            GeneratedMessageLite.GeneratedExtension<ProtoBuf.Function, List<ProtoBuf.Annotation>> functionExtensionReceiverAnnotation = this.protocol.getFunctionExtensionReceiverAnnotation();
            return loadAnnotations(extensionReceiverAnnotationList, functionExtensionReceiverAnnotation != null ? (List) function.getExtension(functionExtensionReceiverAnnotation) : null, container.getNameResolver());
        }
        if (!(proto instanceof ProtoBuf.Property)) {
            throw new IllegalStateException(("Unknown message: " + proto).toString());
        }
        int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            ProtoBuf.Property property = (ProtoBuf.Property) proto;
            List<ProtoBuf.Annotation> extensionReceiverAnnotationList2 = property.getExtensionReceiverAnnotationList();
            Intrinsics.checkNotNullExpressionValue(extensionReceiverAnnotationList2, "getExtensionReceiverAnnotationList(...)");
            GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, List<ProtoBuf.Annotation>> propertyExtensionReceiverAnnotation = this.protocol.getPropertyExtensionReceiverAnnotation();
            return loadAnnotations(extensionReceiverAnnotationList2, propertyExtensionReceiverAnnotation != null ? (List) property.getExtension(propertyExtensionReceiverAnnotation) : null, container.getNameResolver());
        }
        throw new IllegalStateException(("Unsupported callable kind with property proto for receiver annotations: " + kind).toString());
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadContextParameterAnnotations(ProtoContainer container, MessageLite callableProto, AnnotatedCallableKind kind, int i, ProtoBuf.ValueParameter valueParameter) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(callableProto, "callableProto");
        Intrinsics.checkNotNullParameter(kind, "kind");
        List<A> listLoadValueParameterAnnotations = valueParameter != null ? loadValueParameterAnnotations(container, callableProto, kind, i, valueParameter) : null;
        return listLoadValueParameterAnnotations == null ? CollectionsKt.emptyList() : listLoadValueParameterAnnotations;
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadTypeAnnotations(ProtoBuf.Type proto, NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        List<ProtoBuf.Annotation> annotationList = proto.getAnnotationList();
        Intrinsics.checkNotNullExpressionValue(annotationList, "getAnnotationList(...)");
        return loadAnnotations(annotationList, (List) proto.getExtension(this.protocol.getTypeAnnotation()), nameResolver);
    }

    @Override // kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader
    public List<A> loadTypeParameterAnnotations(ProtoBuf.TypeParameter proto, NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(proto, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        List<ProtoBuf.Annotation> annotationList = proto.getAnnotationList();
        Intrinsics.checkNotNullExpressionValue(annotationList, "getAnnotationList(...)");
        return loadAnnotations(annotationList, (List) proto.getExtension(this.protocol.getTypeParameterAnnotation()), nameResolver);
    }

    private final List<A> loadAnnotations(List<ProtoBuf.Annotation> list, List<ProtoBuf.Annotation> list2, NameResolver nameResolver) {
        List<ProtoBuf.Annotation> list3 = list;
        if (list3.isEmpty()) {
            if (list2 == null) {
                list2 = CollectionsKt.emptyList();
            }
            list3 = list2;
        }
        List<ProtoBuf.Annotation> list4 = list3;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
        Iterator<T> it = list4.iterator();
        while (it.hasNext()) {
            arrayList.add(loadAnnotation((ProtoBuf.Annotation) it.next(), nameResolver));
        }
        return arrayList;
    }
}
