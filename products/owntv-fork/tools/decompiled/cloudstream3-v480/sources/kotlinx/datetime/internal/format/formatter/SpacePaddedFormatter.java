package kotlinx.datetime.internal.format.formatter;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Formatter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J)\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u00002\n\u0010\u000b\u001a\u00060\fj\u0002`\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlinx/datetime/internal/format/formatter/SpacePaddedFormatter;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "formatter", "padding", "", "<init>", "(Lkotlinx/datetime/internal/format/formatter/FormatterStructure;I)V", "format", "", "obj", "builder", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "minusNotRequired", "", "(Ljava/lang/Object;Ljava/lang/Appendable;Z)V", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SpacePaddedFormatter<T> implements FormatterStructure<T> {
    private final FormatterStructure<T> formatter;
    private final int padding;

    /* JADX WARN: Multi-variable type inference failed */
    public SpacePaddedFormatter(FormatterStructure<? super T> formatter, int i) {
        Intrinsics.checkNotNullParameter(formatter, "formatter");
        this.formatter = formatter;
        this.padding = i;
    }

    @Override // kotlinx.datetime.internal.format.formatter.FormatterStructure
    public void format(T obj, Appendable builder, boolean minusNotRequired) throws IOException {
        Intrinsics.checkNotNullParameter(builder, "builder");
        StringBuilder sb = new StringBuilder();
        this.formatter.format(obj, sb, minusNotRequired);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "let(...)");
        int length = this.padding - string.length();
        for (int i = 0; i < length; i++) {
            builder.append(' ');
        }
        builder.append(string);
    }
}
