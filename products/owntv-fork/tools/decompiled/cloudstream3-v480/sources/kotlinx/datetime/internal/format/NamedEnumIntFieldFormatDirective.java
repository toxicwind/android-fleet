package kotlinx.datetime.internal.format;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.datetime.internal.format.formatter.FormatterStructure;
import kotlinx.datetime.internal.format.formatter.StringFormatterStructure;
import kotlinx.datetime.internal.format.parser.AssignableField;
import kotlinx.datetime.internal.format.parser.ParserStructure;
import kotlinx.datetime.internal.format.parser.StringSetParserOperation;

/* JADX INFO: compiled from: FieldFormatDirective.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u0016B7\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lkotlinx/datetime/internal/format/NamedEnumIntFieldFormatDirective;", "Target", "Type", "Lkotlinx/datetime/internal/format/FieldFormatDirective;", "field", "Lkotlinx/datetime/internal/format/FieldSpec;", "mapping", "", "", "name", "<init>", "(Lkotlinx/datetime/internal/format/FieldSpec;Ljava/util/Map;Ljava/lang/String;)V", "getField", "()Lkotlinx/datetime/internal/format/FieldSpec;", "reverseMapping", "getStringValue", TypedValues.AttributesType.S_TARGET, "(Ljava/lang/Object;)Ljava/lang/String;", "formatter", "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "parser", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "AssignableString", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class NamedEnumIntFieldFormatDirective<Target, Type> implements FieldFormatDirective<Target> {
    private final FieldSpec<Target, Type> field;
    private final Map<Type, String> mapping;
    private final String name;
    private final Map<String, Type> reverseMapping;

    /* JADX WARN: Multi-variable type inference failed */
    public NamedEnumIntFieldFormatDirective(FieldSpec<? super Target, Type> field, Map<Type, String> mapping, String name) {
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(mapping, "mapping");
        Intrinsics.checkNotNullParameter(name, "name");
        this.field = field;
        this.mapping = mapping;
        this.name = name;
        Set<Map.Entry<Type, String>> setEntrySet = mapping.entrySet();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(setEntrySet, 10)), 16));
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Pair pair = TuplesKt.to(entry.getValue(), entry.getKey());
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        this.reverseMapping = linkedHashMap;
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public final FieldSpec<Target, Type> getField() {
        return this.field;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getStringValue(Target target) {
        Type type = this.field.getAccessor().getterNotNull(target);
        String str = this.mapping.get(this.field.getAccessor().getterNotNull(target));
        if (str != null) {
            return str;
        }
        return "The value " + type + " of " + this.field.getName() + " does not have a corresponding string representation";
    }

    /* JADX INFO: compiled from: FieldFormatDirective.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\bR\u0014\u0010\t\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lkotlinx/datetime/internal/format/NamedEnumIntFieldFormatDirective$AssignableString;", "Lkotlinx/datetime/internal/format/parser/AssignableField;", "", "<init>", "(Lkotlinx/datetime/internal/format/NamedEnumIntFieldFormatDirective;)V", "trySetWithoutReassigning", TtmlNode.RUBY_CONTAINER, "newValue", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;", "name", "getName", "()Ljava/lang/String;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class AssignableString implements AssignableField<Target, String> {
        public AssignableString() {
        }

        @Override // kotlinx.datetime.internal.format.parser.AssignableField
        public String trySetWithoutReassigning(Target container, String newValue) {
            Intrinsics.checkNotNullParameter(newValue, "newValue");
            Accessor<Target, Type> accessor = NamedEnumIntFieldFormatDirective.this.getField().getAccessor();
            Object obj = ((NamedEnumIntFieldFormatDirective) NamedEnumIntFieldFormatDirective.this).reverseMapping.get(newValue);
            Intrinsics.checkNotNull(obj);
            Type typeTrySetWithoutReassigning = accessor.trySetWithoutReassigning(container, obj);
            if (typeTrySetWithoutReassigning != null) {
                return (String) ((NamedEnumIntFieldFormatDirective) NamedEnumIntFieldFormatDirective.this).mapping.get(typeTrySetWithoutReassigning);
            }
            return null;
        }

        @Override // kotlinx.datetime.internal.format.parser.AssignableField
        public String getName() {
            return ((NamedEnumIntFieldFormatDirective) NamedEnumIntFieldFormatDirective.this).name;
        }
    }

    /* JADX INFO: renamed from: kotlinx.datetime.internal.format.NamedEnumIntFieldFormatDirective$formatter$1, reason: invalid class name */
    /* JADX INFO: compiled from: FieldFormatDirective.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Target, String> {
        AnonymousClass1(Object obj) {
            super(1, obj, NamedEnumIntFieldFormatDirective.class, "getStringValue", "getStringValue(Ljava/lang/Object;)Ljava/lang/String;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final String invoke(Target target) {
            return ((NamedEnumIntFieldFormatDirective) this.receiver).getStringValue(target);
        }
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public FormatterStructure<Target> formatter() {
        return new StringFormatterStructure(new AnonymousClass1(this));
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public ParserStructure<Target> parser() {
        return new ParserStructure<>(CollectionsKt.listOf(new StringSetParserOperation(this.mapping.values(), new AssignableString(), "one of " + this.mapping.values() + " for " + this.name)), CollectionsKt.emptyList());
    }
}
