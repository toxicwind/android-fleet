package kotlinx.datetime.internal.format.formatter;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.DecimalFraction;
import kotlinx.datetime.internal.MathKt;

/* JADX INFO: compiled from: FormatterOperation.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B9\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0004\b\u000b\u0010\fJ)\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u00002\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¢\u0006\u0002\u0010\u0015R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lkotlinx/datetime/internal/format/formatter/DecimalFractionFormatterStructure;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "number", "Lkotlin/Function1;", "Lkotlinx/datetime/internal/DecimalFraction;", "minDigits", "", "maxDigits", "zerosToAdd", "", "<init>", "(Lkotlin/jvm/functions/Function1;IILjava/util/List;)V", "format", "", "obj", "builder", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "minusNotRequired", "", "(Ljava/lang/Object;Ljava/lang/Appendable;Z)V", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DecimalFractionFormatterStructure<T> implements FormatterStructure<T> {
    private final int maxDigits;
    private final int minDigits;
    private final Function1<T, DecimalFraction> number;
    private final List<Integer> zerosToAdd;

    /* JADX WARN: Multi-variable type inference failed */
    public DecimalFractionFormatterStructure(Function1<? super T, DecimalFraction> number, int i, int i2, List<Integer> zerosToAdd) {
        Intrinsics.checkNotNullParameter(number, "number");
        Intrinsics.checkNotNullParameter(zerosToAdd, "zerosToAdd");
        this.number = number;
        this.minDigits = i;
        this.maxDigits = i2;
        this.zerosToAdd = zerosToAdd;
        if (1 > i || i >= 10) {
            throw new IllegalArgumentException(("The minimum number of digits (" + i + ") is not in range 1..9").toString());
        }
        if (i > i2 || i2 >= 10) {
            throw new IllegalArgumentException(("The maximum number of digits (" + i2 + ") is not in range " + i + "..9").toString());
        }
    }

    @Override // kotlinx.datetime.internal.format.formatter.FormatterStructure
    public void format(T obj, Appendable builder, boolean minusNotRequired) throws IOException {
        Intrinsics.checkNotNullParameter(builder, "builder");
        int iFractionalPartWithNDigits = this.number.invoke(obj).fractionalPartWithNDigits(this.maxDigits);
        int i = 0;
        while (this.maxDigits > this.minDigits + i) {
            int i2 = i + 1;
            if (iFractionalPartWithNDigits % MathKt.getPOWERS_OF_TEN()[i2] != 0) {
                break;
            } else {
                i = i2;
            }
        }
        int iIntValue = this.zerosToAdd.get((this.maxDigits - i) - 1).intValue();
        if (i >= iIntValue) {
            i -= iIntValue;
        }
        String strSubstring = String.valueOf((iFractionalPartWithNDigits / MathKt.getPOWERS_OF_TEN()[i]) + MathKt.getPOWERS_OF_TEN()[this.maxDigits - i]).substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        builder.append(strSubstring);
    }
}
