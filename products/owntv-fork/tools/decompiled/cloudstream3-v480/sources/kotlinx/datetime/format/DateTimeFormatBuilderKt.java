package kotlinx.datetime.format;

import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import kotlinx.datetime.internal.ToKotlinCodeKt;
import kotlinx.datetime.internal.format.AlternativesParsingFormatStructure;
import kotlinx.datetime.internal.format.BasicFormatStructure;
import kotlinx.datetime.internal.format.CachedFormatStructure;
import kotlinx.datetime.internal.format.ConcatenatedFormatStructure;
import kotlinx.datetime.internal.format.ConstantFormatStructure;
import kotlinx.datetime.internal.format.FormatStructure;
import kotlinx.datetime.internal.format.NonConcatenatedFormatStructure;
import kotlinx.datetime.internal.format.OptionalFormatStructure;
import kotlinx.datetime.internal.format.SignedFormatStructure;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DateTimeFormatBuilder.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007H\u0000\u001ah\u0010\b\u001a\u00020\u0001\"\b\b\u0000\u0010\t*\u00020\n*\u0002H\t24\u0010\u000b\u001a\u001b\u0012\u0017\b\u0001\u0012\u0013\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000e0\f\"\u0013\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u0010\u001a<\u0010\u0011\u001a\u00020\u0001\"\b\b\u0000\u0010\t*\u00020\n*\u0002H\t2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u0015\u001a\u0012\u0010\u0016\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018\u001a6\u0010\u0019\u001a\u00020\u0013\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\u001a2\u001c\u0010\u001b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0013\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d0\u001c0\u0007H\u0000\"\u000e\u0010\u001e\u001a\u00020\u0013X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"secondFractionInternal", "", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithTime;", "minLength", "", "maxLength", "grouping", "", "alternativeParsing", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/datetime/format/DateTimeFormatBuilder;", "alternativeFormats", "", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "primaryFormat", "(Lkotlinx/datetime/format/DateTimeFormatBuilder;[Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "optional", "ifZero", "", "format", "(Lkotlinx/datetime/format/DateTimeFormatBuilder;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "char", ES6Iterator.VALUE_PROPERTY, "", "builderString", "Lkotlinx/datetime/internal/format/FormatStructure;", "constants", "Lkotlin/Pair;", "Lkotlinx/datetime/internal/format/CachedFormatStructure;", "CODE_INDENT", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class DateTimeFormatBuilderKt {
    private static final String CODE_INDENT = "    ";

    public static final void secondFractionInternal(DateTimeFormatBuilder.WithTime withTime, int i, int i2, List<Integer> grouping) {
        Intrinsics.checkNotNullParameter(withTime, "<this>");
        Intrinsics.checkNotNullParameter(grouping, "grouping");
        ((AbstractWithTimeBuilder) withTime).addFormatStructureForTime(new BasicFormatStructure(new FractionalSecondDirective(i, i2, grouping)));
    }

    public static final <T extends DateTimeFormatBuilder> void alternativeParsing(T t, Function1<? super T, Unit>[] alternativeFormats, Function1<? super T, Unit> primaryFormat) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(alternativeFormats, "alternativeFormats");
        Intrinsics.checkNotNullParameter(primaryFormat, "primaryFormat");
        if (t instanceof AbstractDateTimeFormatBuilder) {
            ((AbstractDateTimeFormatBuilder) t).appendAlternativeParsingImpl((Function1[]) Arrays.copyOf(alternativeFormats, alternativeFormats.length), (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(primaryFormat, 1));
            return;
        }
        throw new IllegalStateException("impossible");
    }

    public static /* synthetic */ void optional$default(DateTimeFormatBuilder dateTimeFormatBuilder, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        optional(dateTimeFormatBuilder, str, function1);
    }

    public static final <T extends DateTimeFormatBuilder> void optional(T t, String ifZero, Function1<? super T, Unit> format) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(ifZero, "ifZero");
        Intrinsics.checkNotNullParameter(format, "format");
        if (t instanceof AbstractDateTimeFormatBuilder) {
            ((AbstractDateTimeFormatBuilder) t).appendOptionalImpl(ifZero, (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(format, 1));
            return;
        }
        throw new IllegalStateException("impossible");
    }

    /* JADX INFO: renamed from: char, reason: not valid java name */
    public static final void m10570char(DateTimeFormatBuilder dateTimeFormatBuilder, char c) {
        Intrinsics.checkNotNullParameter(dateTimeFormatBuilder, "<this>");
        dateTimeFormatBuilder.chars(String.valueOf(c));
    }

    public static final <T> String builderString(FormatStructure<? super T> formatStructure, List<? extends Pair<String, ? extends CachedFormatStructure<?>>> constants) {
        Intrinsics.checkNotNullParameter(formatStructure, "<this>");
        Intrinsics.checkNotNullParameter(constants, "constants");
        if (formatStructure instanceof BasicFormatStructure) {
            return ((BasicFormatStructure) formatStructure).getDirective().getBuilderRepresentation();
        }
        if (formatStructure instanceof ConstantFormatStructure) {
            ConstantFormatStructure constantFormatStructure = (ConstantFormatStructure) formatStructure;
            if (constantFormatStructure.getString().length() == 1) {
                return "char(" + ToKotlinCodeKt.toKotlinCode(constantFormatStructure.getString().charAt(0)) + ')';
            }
            return "chars(" + ToKotlinCodeKt.toKotlinCode(constantFormatStructure.getString()) + ')';
        }
        if (formatStructure instanceof SignedFormatStructure) {
            SignedFormatStructure signedFormatStructure = (SignedFormatStructure) formatStructure;
            if ((signedFormatStructure.getFormat() instanceof BasicFormatStructure) && (((BasicFormatStructure) signedFormatStructure.getFormat()).getDirective() instanceof UtcOffsetWholeHoursDirective)) {
                return ((UtcOffsetWholeHoursDirective) ((BasicFormatStructure) signedFormatStructure.getFormat()).getDirective()).getBuilderRepresentation();
            }
            StringBuilder sb = new StringBuilder();
            if (signedFormatStructure.getWithPlusSign()) {
                sb.append("withSharedSign(outputPlus = true) {\n");
            } else {
                sb.append("withSharedSign {\n");
            }
            sb.append(StringsKt.prependIndent(builderString(signedFormatStructure.getFormat(), constants), CODE_INDENT));
            sb.append("\n}");
            return sb.toString();
        }
        if (formatStructure instanceof OptionalFormatStructure) {
            StringBuilder sb2 = new StringBuilder();
            OptionalFormatStructure optionalFormatStructure = (OptionalFormatStructure) formatStructure;
            if (Intrinsics.areEqual(optionalFormatStructure.getOnZero(), "")) {
                sb2.append("optional {\n");
            } else {
                sb2.append("optional(" + ToKotlinCodeKt.toKotlinCode(optionalFormatStructure.getOnZero()) + ") {");
                sb2.append('\n');
            }
            String strBuilderString = builderString(optionalFormatStructure.getFormat(), constants);
            if (strBuilderString.length() > 0) {
                sb2.append(StringsKt.prependIndent(strBuilderString, CODE_INDENT));
                sb2.append('\n');
            }
            sb2.append("}");
            return sb2.toString();
        }
        if (formatStructure instanceof AlternativesParsingFormatStructure) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("alternativeParsing(");
            AlternativesParsingFormatStructure alternativesParsingFormatStructure = (AlternativesParsingFormatStructure) formatStructure;
            for (FormatStructure<T> formatStructure2 : alternativesParsingFormatStructure.getFormats()) {
                sb3.append("{\n");
                String strBuilderString2 = builderString(formatStructure2, constants);
                if (strBuilderString2.length() > 0) {
                    sb3.append(StringsKt.prependIndent(strBuilderString2, CODE_INDENT));
                    sb3.append('\n');
                }
                sb3.append("}, ");
            }
            if (sb3.charAt(sb3.length() - 2) == ',') {
                for (int i = 0; i < 2; i++) {
                    Intrinsics.checkNotNullExpressionValue(sb3.deleteCharAt(sb3.length() - 1), "deleteCharAt(...)");
                }
            }
            sb3.append(") {");
            sb3.append('\n');
            sb3.append(StringsKt.prependIndent(builderString(alternativesParsingFormatStructure.getMainFormat(), constants), CODE_INDENT));
            sb3.append('\n');
            sb3.append("}");
            return sb3.toString();
        }
        if (!(formatStructure instanceof ConcatenatedFormatStructure)) {
            throw new NoWhenBranchMatchedException();
        }
        StringBuilder sb4 = new StringBuilder();
        ConcatenatedFormatStructure concatenatedFormatStructure = (ConcatenatedFormatStructure) formatStructure;
        if (!concatenatedFormatStructure.getFormats().isEmpty()) {
            int size = 0;
            while (size < concatenatedFormatStructure.getFormats().size()) {
                Iterator<? extends Pair<String, ? extends CachedFormatStructure<?>>> it = constants.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Pair<String, ? extends CachedFormatStructure<?>> next = it.next();
                        List<NonConcatenatedFormatStructure<?>> formats = next.getSecond().getFormats();
                        if (concatenatedFormatStructure.getFormats().size() - size >= formats.size()) {
                            int size2 = formats.size();
                            int i2 = 0;
                            while (true) {
                                if (i2 < size2) {
                                    if (!Intrinsics.areEqual(concatenatedFormatStructure.getFormats().get(size + i2), formats.get(i2))) {
                                        break;
                                    }
                                    i2++;
                                } else {
                                    sb4.append(next.getFirst());
                                    size += formats.size();
                                    if (size >= concatenatedFormatStructure.getFormats().size()) {
                                        break;
                                    }
                                    sb4.append('\n');
                                    break;
                                }
                            }
                        }
                    } else {
                        if (size == concatenatedFormatStructure.getFormats().size() - 1) {
                            sb4.append(builderString((FormatStructure) CollectionsKt.last((List) concatenatedFormatStructure.getFormats()), constants));
                        } else {
                            sb4.append(builderString(concatenatedFormatStructure.getFormats().get(size), constants));
                            sb4.append('\n');
                        }
                        size++;
                        break;
                    }
                }
            }
        }
        return sb4.toString();
    }
}
