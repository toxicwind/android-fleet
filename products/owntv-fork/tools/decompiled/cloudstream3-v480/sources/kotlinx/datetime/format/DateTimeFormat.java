package kotlinx.datetime.format;

import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DateTimeFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 \u0012*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0012J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J+\u0010\u0007\u001a\u0002H\b\"\f\b\u0001\u0010\b*\u00060\tj\u0002`\n2\u0006\u0010\u000b\u001a\u0002H\b2\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\u000fH&¢\u0006\u0002\u0010\u0010J\u0017\u0010\u0011\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000e\u001a\u00020\u000fH&¢\u0006\u0002\u0010\u0010\u0082\u0001\u0001\u0013¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lkotlinx/datetime/format/DateTimeFormat;", ExifInterface.GPS_DIRECTION_TRUE, "", "format", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)Ljava/lang/String;", "formatTo", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "appendable", "(Ljava/lang/Appendable;Ljava/lang/Object;)Ljava/lang/Appendable;", "parse", TvContractCompat.PARAM_INPUT, "", "(Ljava/lang/CharSequence;)Ljava/lang/Object;", "parseOrNull", "Companion", "Lkotlinx/datetime/format/AbstractDateTimeFormat;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface DateTimeFormat<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    String format(T value);

    <A extends Appendable> A formatTo(A appendable, T value);

    T parse(CharSequence input);

    T parseOrNull(CharSequence input);

    /* JADX INFO: compiled from: DateTimeFormat.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007¨\u0006\b"}, d2 = {"Lkotlinx/datetime/format/DateTimeFormat$Companion;", "", "<init>", "()V", "formatAsKotlinBuilderDsl", "", "format", "Lkotlinx/datetime/format/DateTimeFormat;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final String formatAsKotlinBuilderDsl(DateTimeFormat<?> format) {
            Intrinsics.checkNotNullParameter(format, "format");
            if (format instanceof AbstractDateTimeFormat) {
                return DateTimeFormatBuilderKt.builderString(((AbstractDateTimeFormat) format).getActualFormat(), DateTimeFormatKt.getAllFormatConstants());
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}
