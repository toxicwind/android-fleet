package kotlinx.datetime.internal.format;

import kotlin.Metadata;

/* JADX INFO: compiled from: FieldSpec.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lkotlinx/datetime/internal/format/AbstractFieldSpec;", "Target", "Type", "Lkotlinx/datetime/internal/format/FieldSpec;", "<init>", "()V", "toString", "", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class AbstractFieldSpec<Target, Type> implements FieldSpec<Target, Type> {
    public String toString() {
        return "The field " + getName() + " (default value is " + getDefaultValue() + ')';
    }
}
