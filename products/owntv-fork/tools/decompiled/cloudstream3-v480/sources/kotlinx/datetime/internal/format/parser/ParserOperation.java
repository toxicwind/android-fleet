package kotlinx.datetime.internal.format.parser;

import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;

/* JADX INFO: compiled from: ParserOperation.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b`\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002J'\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¢\u0006\u0004\b\n\u0010\u000b¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lkotlinx/datetime/internal/format/parser/ParserOperation;", "Output", "", "consume", "Lkotlinx/datetime/internal/format/parser/ParseResult;", "storage", TvContractCompat.PARAM_INPUT, "", "startIndex", "", "consume-FANa98k", "(Ljava/lang/Object;Ljava/lang/CharSequence;I)Ljava/lang/Object;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface ParserOperation<Output> {
    /* JADX INFO: renamed from: consume-FANa98k */
    Object mo10577consumeFANa98k(Output storage, CharSequence input, int startIndex);
}
