package kotlinx.datetime.internal.format;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.formatter.ConcatenatedFormatter;
import kotlinx.datetime.internal.format.formatter.FormatterStructure;
import kotlinx.datetime.internal.format.parser.ParserKt;
import kotlinx.datetime.internal.format.parser.ParserStructure;

/* JADX INFO: compiled from: FormatStructure.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0016R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"Lkotlinx/datetime/internal/format/ConcatenatedFormatStructure;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/internal/format/FormatStructure;", "formats", "", "Lkotlinx/datetime/internal/format/NonConcatenatedFormatStructure;", "<init>", "(Ljava/util/List;)V", "getFormats", "()Ljava/util/List;", "toString", "", "equals", "", "other", "", "hashCode", "", "parser", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "formatter", "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class ConcatenatedFormatStructure<T> implements FormatStructure<T> {
    private final List<NonConcatenatedFormatStructure<T>> formats;

    /* JADX WARN: Multi-variable type inference failed */
    public ConcatenatedFormatStructure(List<? extends NonConcatenatedFormatStructure<? super T>> formats) {
        Intrinsics.checkNotNullParameter(formats, "formats");
        this.formats = formats;
    }

    public final List<NonConcatenatedFormatStructure<T>> getFormats() {
        return this.formats;
    }

    public String toString() {
        return "ConcatenatedFormatStructure(" + CollectionsKt.joinToString$default(this.formats, ", ", null, null, 0, null, null, 62, null) + ')';
    }

    public boolean equals(Object other) {
        return (other instanceof ConcatenatedFormatStructure) && Intrinsics.areEqual(this.formats, ((ConcatenatedFormatStructure) other).formats);
    }

    public int hashCode() {
        return this.formats.hashCode();
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public ParserStructure<T> parser() {
        List<NonConcatenatedFormatStructure<T>> list = this.formats;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((NonConcatenatedFormatStructure) it.next()).parser());
        }
        return ParserKt.concat(arrayList);
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public FormatterStructure<T> formatter() {
        List<NonConcatenatedFormatStructure<T>> list = this.formats;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((NonConcatenatedFormatStructure) it.next()).formatter());
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.size() == 1) {
            return (FormatterStructure) CollectionsKt.single((List) arrayList2);
        }
        return new ConcatenatedFormatter(arrayList2);
    }
}
