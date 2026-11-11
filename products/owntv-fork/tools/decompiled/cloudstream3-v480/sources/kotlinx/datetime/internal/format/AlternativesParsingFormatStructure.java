package kotlinx.datetime.internal.format;

import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.formatter.FormatterStructure;
import kotlinx.datetime.internal.format.parser.ParserStructure;

/* JADX INFO: compiled from: FormatStructure.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B)\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lkotlinx/datetime/internal/format/AlternativesParsingFormatStructure;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/internal/format/NonConcatenatedFormatStructure;", "mainFormat", "Lkotlinx/datetime/internal/format/FormatStructure;", "formats", "", "<init>", "(Lkotlinx/datetime/internal/format/FormatStructure;Ljava/util/List;)V", "getMainFormat", "()Lkotlinx/datetime/internal/format/FormatStructure;", "getFormats", "()Ljava/util/List;", "toString", "", "equals", "", "other", "", "hashCode", "", "parser", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "formatter", "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AlternativesParsingFormatStructure<T> implements NonConcatenatedFormatStructure<T> {
    private final List<FormatStructure<T>> formats;
    private final FormatStructure<T> mainFormat;

    /* JADX WARN: Multi-variable type inference failed */
    public AlternativesParsingFormatStructure(FormatStructure<? super T> mainFormat, List<? extends FormatStructure<? super T>> formats) {
        Intrinsics.checkNotNullParameter(mainFormat, "mainFormat");
        Intrinsics.checkNotNullParameter(formats, "formats");
        this.mainFormat = mainFormat;
        this.formats = formats;
    }

    public final FormatStructure<T> getMainFormat() {
        return this.mainFormat;
    }

    public final List<FormatStructure<T>> getFormats() {
        return this.formats;
    }

    public String toString() {
        return "AlternativesParsing(" + this.formats + ')';
    }

    public boolean equals(Object other) {
        if (!(other instanceof AlternativesParsingFormatStructure)) {
            return false;
        }
        AlternativesParsingFormatStructure alternativesParsingFormatStructure = (AlternativesParsingFormatStructure) other;
        return Intrinsics.areEqual(this.mainFormat, alternativesParsingFormatStructure.mainFormat) && Intrinsics.areEqual(this.formats, alternativesParsingFormatStructure.formats);
    }

    public int hashCode() {
        return (this.mainFormat.hashCode() * 31) + this.formats.hashCode();
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public ParserStructure<T> parser() {
        List listEmptyList = CollectionsKt.emptyList();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(this.mainFormat.parser());
        Iterator<FormatStructure<T>> it = this.formats.iterator();
        while (it.hasNext()) {
            listCreateListBuilder.add(it.next().parser());
        }
        return new ParserStructure<>(listEmptyList, CollectionsKt.build(listCreateListBuilder));
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public FormatterStructure<T> formatter() {
        return this.mainFormat.formatter();
    }
}
