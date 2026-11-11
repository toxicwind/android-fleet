package kotlinx.serialization.json.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.json.JsonConfiguration;

/* JADX INFO: compiled from: JsonPath.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086\u0080\u0004J\u0012\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\fH\u0086\u0080\u0004J\u0014\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0086\u0080\u0004J\n\u0010\u0015\u001a\u00020\u000eH\u0086\u0080\u0004J\n\u0010\u0016\u001a\u00020\u000eH\u0086\u0080\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0086\u0080\u0004J\u0014\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0082\u0080\u0004J\n\u0010\u001b\u001a\u00020\u000eH\u0082\u0080\u0004J\n\u0010\u001c\u001a\u00020\u0018H\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007X\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\bR\u000f\u0010\t\u001a\u00020\nX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\fX\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lkotlinx/serialization/json/internal/JsonPath;", "", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "<init>", "(Lkotlinx/serialization/json/JsonConfiguration;)V", "currentObjectPath", "", "[Ljava/lang/Object;", "indicies", "", "currentDepth", "", "pushDescriptor", "", "sd", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "updateDescriptorIndex", "index", "updateCurrentMapKey", "key", "resetCurrentMapKey", "popDescriptor", "getPath", "", "prettyString", "it", "resize", "toString", "Tombstone", "RedactedKey", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class JsonPath {
    private final JsonConfiguration configuration;
    private int currentDepth;
    private Object[] currentObjectPath;
    private int[] indicies;

    public JsonPath(JsonConfiguration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.configuration = configuration;
        this.currentObjectPath = new Object[8];
        int[] iArr = new int[8];
        for (int i = 0; i < 8; i++) {
            iArr[i] = -1;
        }
        this.indicies = iArr;
        this.currentDepth = -1;
    }

    /* JADX INFO: compiled from: JsonPath.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/serialization/json/internal/JsonPath$Tombstone;", "", "<init>", "()V", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class Tombstone {
        public static final Tombstone INSTANCE = new Tombstone();

        private Tombstone() {
        }
    }

    /* JADX INFO: compiled from: JsonPath.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/serialization/json/internal/JsonPath$RedactedKey;", "", "<init>", "()V", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class RedactedKey {
        public static final RedactedKey INSTANCE = new RedactedKey();

        private RedactedKey() {
        }
    }

    public final void pushDescriptor(SerialDescriptor sd) {
        Intrinsics.checkNotNullParameter(sd, "sd");
        int i = this.currentDepth + 1;
        this.currentDepth = i;
        if (i == this.currentObjectPath.length) {
            resize();
        }
        this.currentObjectPath[i] = sd;
    }

    public final void updateDescriptorIndex(int index) {
        this.indicies[this.currentDepth] = index;
    }

    public final void updateCurrentMapKey(Object key) {
        int[] iArr = this.indicies;
        int i = this.currentDepth;
        if (iArr[i] != -2) {
            int i2 = i + 1;
            this.currentDepth = i2;
            if (i2 == this.currentObjectPath.length) {
                resize();
            }
        }
        Object[] objArr = this.currentObjectPath;
        int i3 = this.currentDepth;
        if (!this.configuration.getExceptionsWithDebugInfo()) {
            key = RedactedKey.INSTANCE;
        }
        objArr[i3] = key;
        this.indicies[this.currentDepth] = -2;
    }

    public final void resetCurrentMapKey() {
        int[] iArr = this.indicies;
        int i = this.currentDepth;
        if (iArr[i] == -2) {
            this.currentObjectPath[i] = Tombstone.INSTANCE;
        }
    }

    public final void popDescriptor() {
        int i = this.currentDepth;
        int[] iArr = this.indicies;
        if (iArr[i] == -2) {
            iArr[i] = -1;
            this.currentDepth = i - 1;
        }
        int i2 = this.currentDepth;
        if (i2 != -1) {
            this.currentDepth = i2 - 1;
        }
    }

    public final String getPath() {
        StringBuilder sb = new StringBuilder("$");
        int i = this.currentDepth + 1;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = this.currentObjectPath[i2];
            if (obj instanceof SerialDescriptor) {
                SerialDescriptor serialDescriptor = (SerialDescriptor) obj;
                boolean zAreEqual = Intrinsics.areEqual(serialDescriptor.getKind(), StructureKind.LIST.INSTANCE);
                int[] iArr = this.indicies;
                if (zAreEqual) {
                    if (iArr[i2] != -1) {
                        sb.append("[");
                        sb.append(this.indicies[i2]);
                        sb.append("]");
                    }
                } else {
                    int i3 = iArr[i2];
                    if (i3 >= 0) {
                        sb.append(".");
                        sb.append(serialDescriptor.getElementName(i3));
                    }
                }
            } else if (obj == RedactedKey.INSTANCE) {
                sb.append("[<debug info disabled>]");
            } else if (obj != Tombstone.INSTANCE) {
                sb.append("['");
                sb.append(obj);
                sb.append("']");
            }
        }
        return sb.toString();
    }

    private final String prettyString(Object it) {
        String serialName;
        SerialDescriptor serialDescriptor = it instanceof SerialDescriptor ? (SerialDescriptor) it : null;
        return (serialDescriptor == null || (serialName = serialDescriptor.getSerialName()) == null) ? String.valueOf(it) : serialName;
    }

    private final void resize() {
        int i = this.currentDepth * 2;
        Object[] objArrCopyOf = Arrays.copyOf(this.currentObjectPath, i);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        this.currentObjectPath = objArrCopyOf;
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = -1;
        }
        ArraysKt.copyInto$default(this.indicies, iArr, 0, 0, 0, 14, (Object) null);
        this.indicies = iArr;
    }

    public String toString() {
        return getPath();
    }
}
