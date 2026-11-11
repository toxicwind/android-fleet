package app.cash.zipline.internal.bridge;

import androidx.exifinterface.media.ExifInterface;
import app.cash.zipline.ZiplineService;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Typography;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: ZiplineServiceAdapter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\u0001\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0001\u001a\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\bH\u0001\u001a \u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0002¨\u0006\u000e"}, d2 = {"ziplineServiceAdapter", "Lapp/cash/zipline/internal/bridge/ZiplineServiceAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "Lapp/cash/zipline/ZiplineService;", "serialName", "", "typeName", "serializers", "", "Lkotlinx/serialization/KSerializer;", "descriptorName", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "visited", "", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ZiplineServiceAdapterKt {
    public static final <T extends ZiplineService> ZiplineServiceAdapter<T> ziplineServiceAdapter(ZiplineServiceAdapter<T> ziplineServiceAdapter) {
        Intrinsics.checkNotNullParameter(ziplineServiceAdapter, "ziplineServiceAdapter");
        return ziplineServiceAdapter;
    }

    public static final <T extends ZiplineService> ZiplineServiceAdapter<T> ziplineServiceAdapter() {
        throw new IllegalStateException("unexpected call to ziplineServiceAdapter(): is the Zipline plugin configured?".toString());
    }

    public static final String serialName(String typeName, List<? extends KSerializer<?>> serializers) {
        Intrinsics.checkNotNullParameter(typeName, "typeName");
        Intrinsics.checkNotNullParameter(serializers, "serializers");
        StringBuilder sb = new StringBuilder();
        sb.append(typeName);
        CollectionsKt.joinTo(serializers, sb, (60 & 2) != 0 ? ", " : ",", (60 & 4) != 0 ? "" : "<", (60 & 8) != 0 ? "" : ">", (60 & 16) != 0 ? -1 : 0, (60 & 32) != 0 ? "..." : null, (60 & 64) != 0 ? null : new Function1() { // from class: app.cash.zipline.internal.bridge.ZiplineServiceAdapterKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ZiplineServiceAdapterKt.serialName$lambda$0$0((KSerializer) obj);
            }
        });
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence serialName$lambda$0$0(KSerializer it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return descriptorName$default(it.getDescriptor(), null, 2, null);
    }

    static /* synthetic */ String descriptorName$default(SerialDescriptor serialDescriptor, Set set, int i, Object obj) {
        if ((i & 2) != 0) {
            set = new LinkedHashSet();
        }
        return descriptorName(serialDescriptor, set);
    }

    private static final String descriptorName(SerialDescriptor serialDescriptor, Set<SerialDescriptor> set) {
        if (!set.add(serialDescriptor)) {
            return "[CIRCULAR]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(serialDescriptor.getSerialName());
        if (serialDescriptor.getElementsCount() > 0) {
            sb.append(Typography.less);
            IntRange intRangeUntil = RangesKt.until(0, serialDescriptor.getElementsCount());
            int first = intRangeUntil.getFirst();
            int last = intRangeUntil.getLast();
            if (first <= last) {
                while (true) {
                    sb.append(descriptorName(serialDescriptor.getElementDescriptor(first), set));
                    if (first < intRangeUntil.getLast()) {
                        sb.append(AbstractJsonLexerKt.COMMA);
                    }
                    if (first == last) {
                        break;
                    }
                    first++;
                }
            }
            sb.append(Typography.greater);
        }
        return sb.toString();
    }
}
