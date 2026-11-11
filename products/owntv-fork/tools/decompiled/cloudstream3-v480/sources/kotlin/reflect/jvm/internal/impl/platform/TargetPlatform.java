package kotlin.reflect.jvm.internal.impl.platform;

import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: TargetPlatform.kt */
/* JADX INFO: loaded from: classes7.dex */
public class TargetPlatform implements Iterable<Object>, KMappedMarker {
    private final Set<Object> componentPlatforms;

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return this.componentPlatforms.iterator();
    }

    public final Set<Object> getComponentPlatforms() {
        return this.componentPlatforms;
    }

    public String toString() {
        return PlatformUtilKt.getPresentableDescription(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TargetPlatform) && Intrinsics.areEqual(this.componentPlatforms, ((TargetPlatform) obj).componentPlatforms);
    }

    public int hashCode() {
        return this.componentPlatforms.hashCode();
    }
}
