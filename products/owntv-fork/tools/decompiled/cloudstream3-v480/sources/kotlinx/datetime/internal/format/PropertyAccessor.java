package kotlinx.datetime.internal.format;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty1;

/* JADX INFO: compiled from: FieldSpec.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B'\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\f\u001a\u0004\u0018\u00018\u00012\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000fJ\u0017\u0010\u0010\u001a\u0004\u0018\u00018\u00012\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lkotlinx/datetime/internal/format/PropertyAccessor;", "Object", "Field", "Lkotlinx/datetime/internal/format/Accessor;", "property", "Lkotlin/reflect/KMutableProperty1;", "name", "", "<init>", "(Lkotlin/reflect/KMutableProperty1;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "trySetWithoutReassigning", TtmlNode.RUBY_CONTAINER, "newValue", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getter", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PropertyAccessor<Object, Field> implements Accessor<Object, Field> {
    private final String name;
    private final KMutableProperty1<Object, Field> property;

    @Override // kotlinx.datetime.internal.format.Accessor
    public /* synthetic */ Object getterNotNull(Object obj) {
        return Accessor.CC.$default$getterNotNull(this, obj);
    }

    public PropertyAccessor(KMutableProperty1<Object, Field> property, String name) {
        Intrinsics.checkNotNullParameter(property, "property");
        Intrinsics.checkNotNullParameter(name, "name");
        this.property = property;
        this.name = name;
    }

    public /* synthetic */ PropertyAccessor(KMutableProperty1 kMutableProperty1, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kMutableProperty1, (i & 2) != 0 ? kMutableProperty1.getName() : str);
    }

    @Override // kotlinx.datetime.internal.format.parser.AssignableField
    public String getName() {
        return this.name;
    }

    @Override // kotlinx.datetime.internal.format.parser.AssignableField
    public Field trySetWithoutReassigning(Object container, Field newValue) {
        Field field = this.property.get(container);
        if (field == null) {
            this.property.set(container, newValue);
            return null;
        }
        if (Intrinsics.areEqual(field, newValue)) {
            return null;
        }
        return field;
    }

    @Override // kotlinx.datetime.internal.format.Accessor
    public Field getter(Object container) {
        return this.property.get(container);
    }
}
