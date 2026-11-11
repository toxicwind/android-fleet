package kotlinx.datetime.internal.format.parser;

import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: NumberConsumer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002B\u001b\b\u0004\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ/\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H&¢\u0006\u0002\u0010\u0015R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0082\u0001\u0004\u0016\u0017\u0018\u0019¨\u0006\u001a"}, d2 = {"Lkotlinx/datetime/internal/format/parser/NumberConsumer;", "Receiver", "", "length", "", "whatThisExpects", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;)V", "getLength", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getWhatThisExpects", "()Ljava/lang/String;", "consume", "Lkotlinx/datetime/internal/format/parser/NumberConsumptionError;", "storage", TvContractCompat.PARAM_INPUT, "", "start", TtmlNode.END, "(Ljava/lang/Object;Ljava/lang/CharSequence;II)Lkotlinx/datetime/internal/format/parser/NumberConsumptionError;", "Lkotlinx/datetime/internal/format/parser/ConstantNumberConsumer;", "Lkotlinx/datetime/internal/format/parser/FractionPartConsumer;", "Lkotlinx/datetime/internal/format/parser/ReducedIntConsumer;", "Lkotlinx/datetime/internal/format/parser/UnsignedIntConsumer;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class NumberConsumer<Receiver> {
    private final Integer length;
    private final String whatThisExpects;

    public /* synthetic */ NumberConsumer(Integer num, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str);
    }

    public abstract NumberConsumptionError consume(Receiver storage, CharSequence input, int start, int end);

    private NumberConsumer(Integer num, String str) {
        this.length = num;
        this.whatThisExpects = str;
    }

    public Integer getLength() {
        return this.length;
    }

    public final String getWhatThisExpects() {
        return this.whatThisExpects;
    }
}
