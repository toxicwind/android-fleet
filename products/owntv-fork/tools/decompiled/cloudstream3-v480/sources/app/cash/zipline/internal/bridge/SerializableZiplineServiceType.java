package app.cash.zipline.internal.bridge;

import app.cash.zipline.ZiplineFunction;
import app.cash.zipline.ZiplineService;
import app.cash.zipline.ZiplineServiceType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;

/* JADX INFO: compiled from: SerializableZiplineServiceType.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001d\u001eB\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tB\u0015\b\u0016\u0012\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0001¢\u0006\u0004\b\b\u0010\u000bB5\b\u0010\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\b\u0010\u0010J%\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0001¢\u0006\u0002\b\u001cR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001f"}, d2 = {"Lapp/cash/zipline/internal/bridge/SerializableZiplineServiceType;", "Lapp/cash/zipline/ZiplineServiceType;", "Lapp/cash/zipline/ZiplineService;", "name", "", "functions", "", "Lapp/cash/zipline/internal/bridge/SerializableZiplineFunction;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "type", "(Lapp/cash/zipline/ZiplineServiceType;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName", "()Ljava/lang/String;", "getFunctions", "()Ljava/util/List;", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$zipline_release", "$serializer", "Companion", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Serializable
public final class SerializableZiplineServiceType implements ZiplineServiceType<ZiplineService> {
    private final List<SerializableZiplineFunction> functions;
    private final String name;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: app.cash.zipline.internal.bridge.SerializableZiplineServiceType$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return SerializableZiplineServiceType._childSerializers$_anonymous_();
        }
    })};

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new ArrayListSerializer(SerializableZiplineFunction$$serializer.INSTANCE);
    }

    /* JADX INFO: compiled from: SerializableZiplineServiceType.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lapp/cash/zipline/internal/bridge/SerializableZiplineServiceType$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lapp/cash/zipline/internal/bridge/SerializableZiplineServiceType;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<SerializableZiplineServiceType> serializer() {
            return SerializableZiplineServiceType$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ SerializableZiplineServiceType(int i, String str, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, SerializableZiplineServiceType$$serializer.INSTANCE.getDescriptor());
        }
        this.name = str;
        this.functions = list;
    }

    public SerializableZiplineServiceType(String name, List<SerializableZiplineFunction> functions) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(functions, "functions");
        this.name = name;
        this.functions = functions;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$zipline_release(SerializableZiplineServiceType self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        output.encodeStringElement(serialDesc, 0, self.getName());
        output.encodeSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.getFunctions());
    }

    @Override // app.cash.zipline.ZiplineServiceType
    public String getName() {
        return this.name;
    }

    @Override // app.cash.zipline.ZiplineServiceType
    public List<SerializableZiplineFunction> getFunctions() {
        return this.functions;
    }

    public SerializableZiplineServiceType(ZiplineServiceType<?> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        String name = type.getName();
        Iterable functions = type.getFunctions();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(functions, 10));
        Iterator it = functions.iterator();
        while (it.hasNext()) {
            arrayList.add(new SerializableZiplineFunction((ZiplineFunction) it.next()));
        }
        this(name, CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: app.cash.zipline.internal.bridge.SerializableZiplineServiceType$special$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((SerializableZiplineFunction) t).getSignature(), ((SerializableZiplineFunction) t2).getSignature());
            }
        }));
    }
}
