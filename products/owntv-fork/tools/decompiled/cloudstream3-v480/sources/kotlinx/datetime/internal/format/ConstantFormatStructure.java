package kotlinx.datetime.internal.format;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.datetime.internal.UtilKt;
import kotlinx.datetime.internal.format.formatter.ConstantStringFormatterStructure;
import kotlinx.datetime.internal.format.formatter.FormatterStructure;
import kotlinx.datetime.internal.format.parser.ConstantNumberConsumer;
import kotlinx.datetime.internal.format.parser.NumberSpanParserOperation;
import kotlinx.datetime.internal.format.parser.ParserStructure;
import kotlinx.datetime.internal.format.parser.PlainStringParserOperation;

/* JADX INFO: compiled from: FormatStructure.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\u0004H\u0016J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0014"}, d2 = {"Lkotlinx/datetime/internal/format/ConstantFormatStructure;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/internal/format/NonConcatenatedFormatStructure;", TypedValues.Custom.S_STRING, "", "<init>", "(Ljava/lang/String;)V", "getString", "()Ljava/lang/String;", "toString", "equals", "", "other", "", "hashCode", "", "parser", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "formatter", "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ConstantFormatStructure<T> implements NonConcatenatedFormatStructure<T> {
    private final String string;

    public ConstantFormatStructure(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        this.string = string;
    }

    public final String getString() {
        return this.string;
    }

    public String toString() {
        return "ConstantFormatStructure(" + this.string + ')';
    }

    public boolean equals(Object other) {
        return (other instanceof ConstantFormatStructure) && Intrinsics.areEqual(this.string, ((ConstantFormatStructure) other).string);
    }

    public int hashCode() {
        return this.string.hashCode();
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public ParserStructure<T> parser() {
        List listBuild;
        if (this.string.length() == 0) {
            listBuild = CollectionsKt.emptyList();
        } else {
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            boolean zIsAsciiDigit = UtilKt.isAsciiDigit(this.string.charAt(0));
            String strSubstring = this.string;
            String strSubstring2 = "";
            if (zIsAsciiDigit) {
                int length = strSubstring.length();
                for (int i = 0; i < length; i++) {
                    if (!UtilKt.isAsciiDigit(strSubstring.charAt(i))) {
                        strSubstring = strSubstring.substring(0, i);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                        break;
                    }
                }
                listCreateListBuilder.add(new NumberSpanParserOperation(CollectionsKt.listOf(new ConstantNumberConsumer(strSubstring))));
                String str = this.string;
                int length2 = str.length();
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        strSubstring = "";
                        break;
                    }
                    if (!UtilKt.isAsciiDigit(str.charAt(i2))) {
                        strSubstring = str.substring(i2);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                        break;
                    }
                    i2++;
                }
            }
            String str2 = strSubstring;
            if (str2.length() > 0) {
                if (!UtilKt.isAsciiDigit(strSubstring.charAt(strSubstring.length() - 1))) {
                    listCreateListBuilder.add(new PlainStringParserOperation(strSubstring));
                } else {
                    for (int lastIndex = StringsKt.getLastIndex(str2); -1 < lastIndex; lastIndex--) {
                        if (!UtilKt.isAsciiDigit(strSubstring.charAt(lastIndex))) {
                            strSubstring2 = strSubstring.substring(0, lastIndex + 1);
                            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                            break;
                        }
                    }
                    listCreateListBuilder.add(new PlainStringParserOperation(strSubstring2));
                    for (int lastIndex2 = StringsKt.getLastIndex(str2); -1 < lastIndex2; lastIndex2--) {
                        if (!UtilKt.isAsciiDigit(strSubstring.charAt(lastIndex2))) {
                            strSubstring = strSubstring.substring(lastIndex2 + 1);
                            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                            break;
                        }
                    }
                    listCreateListBuilder.add(new NumberSpanParserOperation(CollectionsKt.listOf(new ConstantNumberConsumer(strSubstring))));
                }
            }
            listBuild = CollectionsKt.build(listCreateListBuilder);
        }
        return new ParserStructure<>(listBuild, CollectionsKt.emptyList());
    }

    @Override // kotlinx.datetime.internal.format.FormatStructure
    public FormatterStructure<T> formatter() {
        return new ConstantStringFormatterStructure(this.string);
    }
}
