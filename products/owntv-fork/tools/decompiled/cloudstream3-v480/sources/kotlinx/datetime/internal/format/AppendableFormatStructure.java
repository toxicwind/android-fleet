package kotlinx.datetime.internal.format;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Builder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tJ\u0014\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/datetime/internal/format/AppendableFormatStructure;", ExifInterface.GPS_DIRECTION_TRUE, "", "<init>", "()V", "list", "", "Lkotlinx/datetime/internal/format/NonConcatenatedFormatStructure;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lkotlinx/datetime/internal/format/ConcatenatedFormatStructure;", "add", "", "format", "Lkotlinx/datetime/internal/format/FormatStructure;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AppendableFormatStructure<T> {
    private final List<NonConcatenatedFormatStructure<T>> list = new ArrayList();

    public final ConcatenatedFormatStructure<T> build() {
        return new ConcatenatedFormatStructure<>(this.list);
    }

    public final void add(FormatStructure<? super T> format) {
        Intrinsics.checkNotNullParameter(format, "format");
        if (format instanceof NonConcatenatedFormatStructure) {
            this.list.add(format);
        } else {
            if (!(format instanceof ConcatenatedFormatStructure)) {
                throw new NoWhenBranchMatchedException();
            }
            Iterator<T> it = ((ConcatenatedFormatStructure) format).getFormats().iterator();
            while (it.hasNext()) {
                this.list.add((NonConcatenatedFormatStructure) it.next());
            }
        }
    }
}
