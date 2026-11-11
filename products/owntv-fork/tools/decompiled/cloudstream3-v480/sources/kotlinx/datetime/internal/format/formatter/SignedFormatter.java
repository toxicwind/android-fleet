package kotlinx.datetime.internal.format.formatter;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Formatter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B6\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ)\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u00002\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0016¢\u0006\u0002\u0010\u0012R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/internal/format/formatter/SignedFormatter;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "formatter", "allSubFormatsNegative", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "alwaysOutputSign", "<init>", "(Lkotlinx/datetime/internal/format/formatter/FormatterStructure;Lkotlin/jvm/functions/Function1;Z)V", "format", "", "obj", "builder", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "minusNotRequired", "(Ljava/lang/Object;Ljava/lang/Appendable;Z)V", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SignedFormatter<T> implements FormatterStructure<T> {
    private final Function1<T, Boolean> allSubFormatsNegative;
    private final boolean alwaysOutputSign;
    private final FormatterStructure<T> formatter;

    /* JADX WARN: Multi-variable type inference failed */
    public SignedFormatter(FormatterStructure<? super T> formatter, Function1<? super T, Boolean> allSubFormatsNegative, boolean z) {
        Intrinsics.checkNotNullParameter(formatter, "formatter");
        Intrinsics.checkNotNullParameter(allSubFormatsNegative, "allSubFormatsNegative");
        this.formatter = formatter;
        this.allSubFormatsNegative = allSubFormatsNegative;
        this.alwaysOutputSign = z;
    }

    @Override // kotlinx.datetime.internal.format.formatter.FormatterStructure
    public void format(T obj, Appendable builder, boolean minusNotRequired) throws IOException {
        Character ch;
        Intrinsics.checkNotNullParameter(builder, "builder");
        if (!minusNotRequired && this.allSubFormatsNegative.invoke(obj).booleanValue()) {
            ch = '-';
        } else {
            ch = this.alwaysOutputSign ? '+' : null;
        }
        if (ch != null) {
            builder.append(ch.charValue());
        }
        this.formatter.format(obj, builder, minusNotRequired || (ch != null && ch.charValue() == '-'));
    }
}
