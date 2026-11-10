package kotlin.jvm.internal;

import kotlin.reflect.KMutableProperty$Setter;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference extends PropertyReference implements KProperty {
    @NotNull
    public abstract /* synthetic */ KMutableProperty$Setter<R> getSetter();

    public MutablePropertyReference() {
    }

    public MutablePropertyReference(Object obj) {
        super(obj);
    }
}
