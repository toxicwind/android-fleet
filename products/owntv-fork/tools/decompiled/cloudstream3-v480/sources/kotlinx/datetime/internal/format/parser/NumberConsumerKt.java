package kotlinx.datetime.internal.format.parser;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlinx.datetime.internal.UtilKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: NumberConsumer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0010\r\n\u0002\b\u0005\u001a;\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\u0006\u0010\u0006\u001a\u0002H\u0003H\u0002¢\u0006\u0002\u0010\u0007\u001a\u001c\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0002\u001a#\u0010\r\u001a\u0004\u0018\u00010\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0002¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"setWithoutReassigning", "Lkotlinx/datetime/internal/format/parser/NumberConsumptionError;", "Object", "Type", "Lkotlinx/datetime/internal/format/parser/AssignableField;", "receiver", ES6Iterator.VALUE_PROPERTY, "(Lkotlinx/datetime/internal/format/parser/AssignableField;Ljava/lang/Object;Ljava/lang/Object;)Lkotlinx/datetime/internal/format/parser/NumberConsumptionError;", "parseAsciiInt", "", "", "start", TtmlNode.END, "parseAsciiIntOrNull", "(Ljava/lang/CharSequence;II)Ljava/lang/Integer;", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class NumberConsumerKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <Object, Type> NumberConsumptionError setWithoutReassigning(AssignableField<? super Object, Type> assignableField, Object object, Type type) {
        Type typeTrySetWithoutReassigning = assignableField.trySetWithoutReassigning(object, type);
        if (typeTrySetWithoutReassigning == null) {
            return null;
        }
        return new NumberConsumptionError.Conflicting(typeTrySetWithoutReassigning);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int parseAsciiInt(CharSequence charSequence, int i, int i2) {
        int iAsciiDigitToInt = 0;
        while (i < i2) {
            iAsciiDigitToInt = (iAsciiDigitToInt * 10) + UtilKt.asciiDigitToInt(charSequence.charAt(i));
            i++;
        }
        return iAsciiDigitToInt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer parseAsciiIntOrNull(CharSequence charSequence, int i, int i2) {
        int iAsciiDigitToInt = 0;
        while (i < i2) {
            iAsciiDigitToInt = (iAsciiDigitToInt * 10) + UtilKt.asciiDigitToInt(charSequence.charAt(i));
            if (iAsciiDigitToInt < 0) {
                return null;
            }
            i++;
        }
        return Integer.valueOf(iAsciiDigitToInt);
    }
}
