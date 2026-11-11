package kotlinx.datetime.internal.format;

import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: FormatStructure.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\u0004\b\u0000\u0010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0002¨\u0006\u0006"}, d2 = {"basicFormats", "", "Lkotlinx/datetime/internal/format/FieldFormatDirective;", ExifInterface.GPS_DIRECTION_TRUE, "format", "Lkotlinx/datetime/internal/format/FormatStructure;", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class FormatStructureKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> List<FieldFormatDirective<T>> basicFormats(FormatStructure<? super T> formatStructure) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        basicFormats$lambda$2$rec(listCreateListBuilder, formatStructure);
        return CollectionsKt.build(listCreateListBuilder);
    }

    private static final <T> void basicFormats$lambda$2$rec(List<FieldFormatDirective<T>> list, FormatStructure<? super T> formatStructure) {
        if (formatStructure instanceof BasicFormatStructure) {
            list.add(((BasicFormatStructure) formatStructure).getDirective());
            return;
        }
        if (!(formatStructure instanceof ConcatenatedFormatStructure)) {
            if (formatStructure instanceof ConstantFormatStructure) {
                return;
            }
            if (formatStructure instanceof SignedFormatStructure) {
                basicFormats$lambda$2$rec(list, ((SignedFormatStructure) formatStructure).getFormat());
                return;
            }
            if (formatStructure instanceof AlternativesParsingFormatStructure) {
                AlternativesParsingFormatStructure alternativesParsingFormatStructure = (AlternativesParsingFormatStructure) formatStructure;
                basicFormats$lambda$2$rec(list, alternativesParsingFormatStructure.getMainFormat());
                Iterator<T> it = alternativesParsingFormatStructure.getFormats().iterator();
                while (it.hasNext()) {
                    basicFormats$lambda$2$rec(list, (FormatStructure) it.next());
                }
                return;
            }
            if (!(formatStructure instanceof OptionalFormatStructure)) {
                throw new NoWhenBranchMatchedException();
            }
            basicFormats$lambda$2$rec(list, ((OptionalFormatStructure) formatStructure).getFormat());
            return;
        }
        Iterator<T> it2 = ((ConcatenatedFormatStructure) formatStructure).getFormats().iterator();
        while (it2.hasNext()) {
            basicFormats$lambda$2$rec(list, (NonConcatenatedFormatStructure) it2.next());
        }
    }
}
