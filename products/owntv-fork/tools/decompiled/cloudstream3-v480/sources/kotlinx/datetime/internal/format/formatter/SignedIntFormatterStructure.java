package kotlinx.datetime.internal.format.formatter;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.MathKt;

/* JADX INFO: compiled from: FormatterOperation.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B-\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ)\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u00002\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0013R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0014"}, d2 = {"Lkotlinx/datetime/internal/format/formatter/SignedIntFormatterStructure;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "number", "Lkotlin/Function1;", "", "zeroPadding", "outputPlusOnExceededWidth", "<init>", "(Lkotlin/jvm/functions/Function1;ILjava/lang/Integer;)V", "Ljava/lang/Integer;", "format", "", "obj", "builder", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "minusNotRequired", "", "(Ljava/lang/Object;Ljava/lang/Appendable;Z)V", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SignedIntFormatterStructure<T> implements FormatterStructure<T> {
    private final Function1<T, Integer> number;
    private final Integer outputPlusOnExceededWidth;
    private final int zeroPadding;

    /* JADX WARN: Multi-variable type inference failed */
    public SignedIntFormatterStructure(Function1<? super T, Integer> number, int i, Integer num) {
        Intrinsics.checkNotNullParameter(number, "number");
        this.number = number;
        this.zeroPadding = i;
        this.outputPlusOnExceededWidth = num;
        if (i < 0) {
            throw new IllegalArgumentException(("The minimum number of digits (" + i + ") is negative").toString());
        }
        if (i <= 9) {
            return;
        }
        throw new IllegalArgumentException(("The minimum number of digits (" + i + ") exceeds the length of an Int").toString());
    }

    @Override // kotlinx.datetime.internal.format.formatter.FormatterStructure
    public void format(T obj, Appendable builder, boolean minusNotRequired) throws IOException {
        Intrinsics.checkNotNullParameter(builder, "builder");
        StringBuilder sb = new StringBuilder();
        int iIntValue = this.number.invoke(obj).intValue();
        if (minusNotRequired && iIntValue < 0) {
            iIntValue = -iIntValue;
        }
        if (this.outputPlusOnExceededWidth != null && iIntValue >= MathKt.getPOWERS_OF_TEN()[this.outputPlusOnExceededWidth.intValue()]) {
            sb.append('+');
        }
        if (Math.abs(iIntValue) >= MathKt.getPOWERS_OF_TEN()[this.zeroPadding - 1]) {
            sb.append(iIntValue);
        } else if (iIntValue >= 0) {
            sb.append(iIntValue + MathKt.getPOWERS_OF_TEN()[this.zeroPadding]);
            Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
            Intrinsics.checkNotNullExpressionValue(sb.deleteCharAt(0), "deleteCharAt(...)");
        } else {
            sb.append(iIntValue - MathKt.getPOWERS_OF_TEN()[this.zeroPadding]);
            Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
            Intrinsics.checkNotNullExpressionValue(sb.deleteCharAt(1), "deleteCharAt(...)");
        }
        builder.append(sb);
    }
}
