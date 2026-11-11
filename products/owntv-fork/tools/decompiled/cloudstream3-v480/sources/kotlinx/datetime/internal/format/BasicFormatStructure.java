package kotlinx.datetime.internal.format;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.formatter.FormatterStructure;
import kotlinx.datetime.internal.format.parser.ParserStructure;

/* JADX INFO: compiled from: FormatStructure.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H\u0016J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lkotlinx/datetime/internal/format/BasicFormatStructure;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/internal/format/NonConcatenatedFormatStructure;", "directive", "Lkotlinx/datetime/internal/format/FieldFormatDirective;", "<init>", "(Lkotlinx/datetime/internal/format/FieldFormatDirective;)V", "getDirective", "()Lkotlinx/datetime/internal/format/FieldFormatDirective;", "toString", "", "equals", "", "other", "", "hashCode", "", "parser", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "formatter", "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BasicFormatStructure<T> implements NonConcatenatedFormatStructure<T> {
    private final FieldFormatDirective<T> directive;

    /* JADX WARN: Multi-variable type inference failed */
    public BasicFormatStructure(FieldFormatDirective<? super T> directive) {
        Intrinsics.checkNotNullParameter(directive, "directive");
        this.directive = directive;
    }

    public final FieldFormatDirective<T> getDirective() {
        return this.directive;
    }

    public String toString() {
        return "BasicFormatStructure(" + this.directive + ')';
    }

    public boolean equals(Object other) {
        return (other instanceof BasicFormatStructure) && Intrinsics.areEqual(this.directive, ((BasicFormatStructure) other).directive);
    }

    public int hashCode() {
        return this.directive.hashCode();
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public ParserStructure<T> parser() {
        return this.directive.parser();
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public FormatterStructure<T> formatter() {
        return this.directive.formatter();
    }
}
