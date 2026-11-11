package kotlinx.datetime.internal.format.parser;

import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NumberConsumer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J/\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/internal/format/parser/ConstantNumberConsumer;", "Receiver", "Lkotlinx/datetime/internal/format/parser/NumberConsumer;", "expected", "", "<init>", "(Ljava/lang/String;)V", "consume", "Lkotlinx/datetime/internal/format/parser/NumberConsumptionError;", "storage", TvContractCompat.PARAM_INPUT, "", "start", "", TtmlNode.END, "(Ljava/lang/Object;Ljava/lang/CharSequence;II)Lkotlinx/datetime/internal/format/parser/NumberConsumptionError;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ConstantNumberConsumer<Receiver> extends NumberConsumer<Receiver> {
    private final String expected;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstantNumberConsumer(String expected) {
        super(Integer.valueOf(expected.length()), "the predefined string " + expected, null);
        Intrinsics.checkNotNullParameter(expected, "expected");
        this.expected = expected;
    }

    @Override // kotlinx.datetime.internal.format.parser.NumberConsumer
    public NumberConsumptionError consume(Receiver storage, CharSequence input, int start, int end) {
        Intrinsics.checkNotNullParameter(input, "input");
        if (Intrinsics.areEqual(input.subSequence(start, end).toString(), this.expected)) {
            return null;
        }
        return new NumberConsumptionError.WrongConstant(this.expected);
    }
}
