package androidx.compose.ui.text;

import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: AnnotatedString.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\r\u001a@\u0010\u0000\u001a\u0012\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\u0018\u00010\u00012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00020\u0001H\u0002\u001a \u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00020\u0001*\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0000\u001a*\u0010\u000b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0002\u0018\u00010\u0001*\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u001aD\u0010\u000f\u001a\u0012\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\u0018\u00010\u0001*\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0002\u001a\u001c\u0010\u0013\u001a\u00020\t*\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u001aa\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0001\"\u0004\b\u0000\u0010\u0015*\u00020\t2\u0006\u0010\n\u001a\u00020\u00072>\b\u0004\u0010\u0016\u001a8\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00070\u0002¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u0002H\u00150\u0017H\u0080\b\u001a\u0014\u0010\u001c\u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u001a\u0014\u0010\u001f\u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u001a\u0014\u0010 \u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u001a\u0014\u0010!\u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u001a=\u0010\"\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u00052\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0086\b¢\u0006\u0002\u0010(\u001a?\u0010\"\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u0010&\u001a\u00020\u00072\u0019\b\u0004\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0086\b¢\u0006\u0002\u0010)\u001aG\u0010*\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0019\b\u0004\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0086\b¢\u0006\u0002\u0010.\u001a?\u0010*\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u0010/\u001a\u0002002\u0019\b\u0004\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0086\b¢\u0006\u0002\u00101\u001a?\u0010*\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u00102\u001a\u0002032\u0019\b\u0004\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0087\b¢\u0006\u0002\u00104\u001a=\u00105\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020$*\u00020%2\u0006\u00106\u001a\u0002072\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u0002H#0\u0011¢\u0006\u0002\b'H\u0086\b¢\u0006\u0002\u00108\u001aD\u00109\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u0002\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00152\u0016\u0010:\u001a\u0012\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00150\u0002\u0018\u00010\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u001a\"\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020,2\u0006\u0010=\u001a\u00020\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0007\u001a\u0016\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020,2\u0006\u0010\u001b\u001a\u00020\u0007\u001a\"\u0010>\u001a\u00020\t2\u0017\u0010?\u001a\u0013\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020@0\u0011¢\u0006\u0002\b'H\u0086\b\u001a(\u0010A\u001a\u00020\u00122\u0006\u0010B\u001a\u00020\r2\u0006\u0010C\u001a\u00020\r2\u0006\u0010D\u001a\u00020\r2\u0006\u0010E\u001a\u00020\rH\u0000\u001a(\u0010F\u001a\u00020\u00122\u0006\u0010G\u001a\u00020\r2\u0006\u0010H\u001a\u00020\r2\u0006\u0010I\u001a\u00020\r2\u0006\u0010J\u001a\u00020\rH\u0000\u001a\b\u0010L\u001a\u00020\tH\u0000\"\u000e\u0010K\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"constructAnnotationsFromSpansAndParagraphs", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "spanStyles", "Landroidx/compose/ui/text/SpanStyle;", "paragraphStyles", "Landroidx/compose/ui/text/ParagraphStyle;", "normalizedParagraphStyles", "Landroidx/compose/ui/text/AnnotatedString;", "defaultParagraphStyle", "getLocalParagraphStyles", "start", "", TtmlNode.END, "getLocalAnnotations", "predicate", "Lkotlin/Function1;", "", "substringWithoutParagraphStyles", "mapEachParagraphStyle", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "annotatedString", "paragraphStyle", "toUpperCase", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "toLowerCase", "capitalize", "decapitalize", "withStyle", "R", "", "Landroidx/compose/ui/text/AnnotatedString$Builder;", "style", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/SpanStyle;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/ParagraphStyle;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "withAnnotation", "tag", "", "annotation", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ttsAnnotation", "Landroidx/compose/ui/text/TtsAnnotation;", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/TtsAnnotation;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "urlAnnotation", "Landroidx/compose/ui/text/UrlAnnotation;", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/UrlAnnotation;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "withLink", "link", "Landroidx/compose/ui/text/LinkAnnotation;", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/LinkAnnotation;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "filterRanges", "ranges", "AnnotatedString", "text", "spanStyle", "buildAnnotatedString", "builder", "", "contains", "baseStart", "baseEnd", "targetStart", "targetEnd", "intersect", "lStart", "lEnd", "rStart", "rEnd", "EmptyAnnotatedString", "emptyAnnotatedString", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AnnotatedStringKt {
    private static final AnnotatedString EmptyAnnotatedString = new AnnotatedString("", null, 2, 0 == true ? 1 : 0);

    public static final boolean contains(int i, int i2, int i3, int i4) {
        if (i <= i3 && i4 <= i2) {
            if (i2 == i4) {
                if ((i3 == i4) == (i == i2)) {
                }
            }
            return true;
        }
        return false;
    }

    public static final boolean intersect(int i, int i2, int i3, int i4) {
        return ((i < i4) & (i3 < i2)) | (((i == i2) | (i3 == i4)) & (i == i3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> constructAnnotationsFromSpansAndParagraphs(List<AnnotatedString.Range<SpanStyle>> list, List<AnnotatedString.Range<ParagraphStyle>> list2) {
        if (list.isEmpty() && list2.isEmpty()) {
            return null;
        }
        if (list2.isEmpty()) {
            return list;
        }
        if (list.isEmpty()) {
            return list2;
        }
        ArrayList arrayList = new ArrayList(list.size() + list2.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(list.get(i));
        }
        int size2 = list2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            arrayList.add(list2.get(i2));
        }
        return arrayList;
    }

    public static final List<AnnotatedString.Range<ParagraphStyle>> normalizedParagraphStyles(AnnotatedString annotatedString, ParagraphStyle paragraphStyle) {
        List listEmptyList;
        List<AnnotatedString.Range<ParagraphStyle>> paragraphStylesOrNull$ui_text = annotatedString.getParagraphStylesOrNull$ui_text();
        if (paragraphStylesOrNull$ui_text == null || (listEmptyList = CollectionsKt.sortedWith(paragraphStylesOrNull$ui_text, new Comparator() { // from class: androidx.compose.ui.text.AnnotatedStringKt$normalizedParagraphStyles$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((AnnotatedString.Range) t).getStart()), Integer.valueOf(((AnnotatedString.Range) t2).getStart()));
            }
        })) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        ArrayDeque arrayDeque = new ArrayDeque();
        int size = listEmptyList.size();
        int end = 0;
        for (int i = 0; i < size; i++) {
            AnnotatedString.Range range = (AnnotatedString.Range) listEmptyList.get(i);
            AnnotatedString.Range rangeCopy$default = AnnotatedString.Range.copy$default(range, paragraphStyle.merge((ParagraphStyle) range.getItem()), 0, 0, null, 14, null);
            while (end < rangeCopy$default.getStart()) {
                ArrayDeque arrayDeque2 = arrayDeque;
                if (arrayDeque2.isEmpty()) {
                    break;
                }
                AnnotatedString.Range range2 = (AnnotatedString.Range) arrayDeque.last();
                if (rangeCopy$default.getStart() < range2.getEnd()) {
                    arrayList.add(new AnnotatedString.Range(range2.getItem(), end, rangeCopy$default.getStart()));
                    end = rangeCopy$default.getStart();
                } else {
                    arrayList.add(new AnnotatedString.Range(range2.getItem(), end, range2.getEnd()));
                    end = range2.getEnd();
                    while (!arrayDeque2.isEmpty() && end == ((AnnotatedString.Range) arrayDeque.last()).getEnd()) {
                        arrayDeque.removeLast();
                    }
                }
            }
            if (end < rangeCopy$default.getStart()) {
                arrayList.add(new AnnotatedString.Range(paragraphStyle, end, rangeCopy$default.getStart()));
                end = rangeCopy$default.getStart();
            }
            AnnotatedString.Range range3 = (AnnotatedString.Range) arrayDeque.lastOrNull();
            if (range3 != null) {
                if (range3.getStart() == rangeCopy$default.getStart() && range3.getEnd() == rangeCopy$default.getEnd()) {
                    arrayDeque.removeLast();
                    arrayDeque.add(new AnnotatedString.Range(((ParagraphStyle) range3.getItem()).merge((ParagraphStyle) rangeCopy$default.getItem()), rangeCopy$default.getStart(), rangeCopy$default.getEnd()));
                } else if (range3.getStart() == range3.getEnd()) {
                    arrayList.add(new AnnotatedString.Range(range3.getItem(), range3.getStart(), range3.getEnd()));
                    arrayDeque.removeLast();
                    arrayDeque.add(new AnnotatedString.Range(rangeCopy$default.getItem(), rangeCopy$default.getStart(), rangeCopy$default.getEnd()));
                } else {
                    if (range3.getEnd() < rangeCopy$default.getEnd()) {
                        throw new IllegalArgumentException();
                    }
                    arrayDeque.add(new AnnotatedString.Range(((ParagraphStyle) range3.getItem()).merge((ParagraphStyle) rangeCopy$default.getItem()), rangeCopy$default.getStart(), rangeCopy$default.getEnd()));
                }
            } else {
                arrayDeque.add(new AnnotatedString.Range(rangeCopy$default.getItem(), rangeCopy$default.getStart(), rangeCopy$default.getEnd()));
            }
        }
        while (end <= annotatedString.getText().length()) {
            ArrayDeque arrayDeque3 = arrayDeque;
            if (arrayDeque3.isEmpty()) {
                break;
            }
            AnnotatedString.Range range4 = (AnnotatedString.Range) arrayDeque.last();
            arrayList.add(new AnnotatedString.Range(range4.getItem(), end, range4.getEnd()));
            end = range4.getEnd();
            while (!arrayDeque3.isEmpty() && end == ((AnnotatedString.Range) arrayDeque.last()).getEnd()) {
                arrayDeque.removeLast();
            }
        }
        if (end < annotatedString.getText().length()) {
            arrayList.add(new AnnotatedString.Range(paragraphStyle, end, annotatedString.getText().length()));
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new AnnotatedString.Range(paragraphStyle, 0, 0));
        }
        return arrayList;
    }

    private static final List<AnnotatedString.Range<ParagraphStyle>> getLocalParagraphStyles(AnnotatedString annotatedString, int i, int i2) {
        List<AnnotatedString.Range<ParagraphStyle>> paragraphStylesOrNull$ui_text;
        if (i == i2 || (paragraphStylesOrNull$ui_text = annotatedString.getParagraphStylesOrNull$ui_text()) == null) {
            return null;
        }
        if (i == 0 && i2 >= annotatedString.getText().length()) {
            return paragraphStylesOrNull$ui_text;
        }
        ArrayList arrayList = new ArrayList(paragraphStylesOrNull$ui_text.size());
        int size = paragraphStylesOrNull$ui_text.size();
        for (int i3 = 0; i3 < size; i3++) {
            AnnotatedString.Range<ParagraphStyle> range = paragraphStylesOrNull$ui_text.get(i3);
            if (intersect(i, i2, range.getStart(), range.getEnd())) {
                ArrayList arrayList2 = arrayList;
                ParagraphStyle item = range.getItem();
                int start = range.getStart();
                if (start < i) {
                    start = i;
                }
                if (start > i2) {
                    start = i2;
                }
                int i4 = start - i;
                int end = range.getEnd();
                if (end < i) {
                    end = i;
                }
                if (end > i2) {
                    end = i2;
                }
                arrayList2.add(new AnnotatedString.Range(item, i4, end - i));
            }
        }
        return arrayList;
    }

    static /* synthetic */ List getLocalAnnotations$default(AnnotatedString annotatedString, int i, int i2, Function1 function1, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            function1 = null;
        }
        return getLocalAnnotations(annotatedString, i, i2, function1);
    }

    private static final List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> getLocalAnnotations(AnnotatedString annotatedString, int i, int i2, Function1<? super AnnotatedString.Annotation, Boolean> function1) {
        List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> annotations$ui_text;
        if (i == i2 || (annotations$ui_text = annotatedString.getAnnotations$ui_text()) == null) {
            return null;
        }
        if (i != 0 || i2 < annotatedString.getText().length()) {
            ArrayList arrayList = new ArrayList(annotations$ui_text.size());
            int size = annotations$ui_text.size();
            for (int i3 = 0; i3 < size; i3++) {
                AnnotatedString.Range<? extends AnnotatedString.Annotation> range = annotations$ui_text.get(i3);
                if ((function1 != null ? function1.invoke(range.getItem()).booleanValue() : true) && intersect(i, i2, range.getStart(), range.getEnd())) {
                    arrayList.add(new AnnotatedString.Range(range.getItem(), RangesKt.coerceIn(range.getStart(), i, i2) - i, RangesKt.coerceIn(range.getEnd(), i, i2) - i, range.getTag()));
                }
            }
            return arrayList;
        }
        if (function1 == null) {
            return annotations$ui_text;
        }
        ArrayList arrayList2 = new ArrayList(annotations$ui_text.size());
        int size2 = annotations$ui_text.size();
        for (int i4 = 0; i4 < size2; i4++) {
            AnnotatedString.Range<? extends AnnotatedString.Annotation> range2 = annotations$ui_text.get(i4);
            if (function1.invoke(range2.getItem()).booleanValue()) {
                arrayList2.add(range2);
            }
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnnotatedString substringWithoutParagraphStyles(AnnotatedString annotatedString, int i, int i2) {
        String strSubstring;
        if (i != i2) {
            strSubstring = annotatedString.getText().substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        } else {
            strSubstring = "";
        }
        List<AnnotatedString.Range<? extends AnnotatedString.Annotation>> localAnnotations = getLocalAnnotations(annotatedString, i, i2, new Function1() { // from class: androidx.compose.ui.text.AnnotatedStringKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(AnnotatedStringKt.substringWithoutParagraphStyles$lambda$10((AnnotatedString.Annotation) obj));
            }
        });
        if (localAnnotations == null) {
            localAnnotations = CollectionsKt.emptyList();
        }
        return new AnnotatedString(strSubstring, localAnnotations);
    }

    static final boolean substringWithoutParagraphStyles$lambda$10(AnnotatedString.Annotation annotation) {
        return !(annotation instanceof ParagraphStyle);
    }

    public static final <T> List<T> mapEachParagraphStyle(AnnotatedString annotatedString, ParagraphStyle paragraphStyle, Function2<? super AnnotatedString, ? super AnnotatedString.Range<ParagraphStyle>, ? extends T> function2) {
        List<AnnotatedString.Range<ParagraphStyle>> listNormalizedParagraphStyles = normalizedParagraphStyles(annotatedString, paragraphStyle);
        ArrayList arrayList = new ArrayList(listNormalizedParagraphStyles.size());
        int size = listNormalizedParagraphStyles.size();
        for (int i = 0; i < size; i++) {
            AnnotatedString.Range<ParagraphStyle> range = listNormalizedParagraphStyles.get(i);
            arrayList.add(function2.invoke(substringWithoutParagraphStyles(annotatedString, range.getStart(), range.getEnd()), range));
        }
        return arrayList;
    }

    public static /* synthetic */ AnnotatedString toUpperCase$default(AnnotatedString annotatedString, LocaleList localeList, int i, Object obj) {
        if ((i & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return toUpperCase(annotatedString, localeList);
    }

    public static final AnnotatedString toUpperCase(AnnotatedString annotatedString, final LocaleList localeList) {
        return JvmAnnotatedString_jvmKt.transform(annotatedString, new Function3() { // from class: androidx.compose.ui.text.AnnotatedStringKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AnnotatedStringKt.toUpperCase$lambda$12(localeList, (String) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        });
    }

    static final String toUpperCase$lambda$12(LocaleList localeList, String str, int i, int i2) {
        String strSubstring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return StringKt.toUpperCase(strSubstring, localeList);
    }

    public static /* synthetic */ AnnotatedString toLowerCase$default(AnnotatedString annotatedString, LocaleList localeList, int i, Object obj) {
        if ((i & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return toLowerCase(annotatedString, localeList);
    }

    public static final AnnotatedString toLowerCase(AnnotatedString annotatedString, final LocaleList localeList) {
        return JvmAnnotatedString_jvmKt.transform(annotatedString, new Function3() { // from class: androidx.compose.ui.text.AnnotatedStringKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AnnotatedStringKt.toLowerCase$lambda$13(localeList, (String) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        });
    }

    static final String toLowerCase$lambda$13(LocaleList localeList, String str, int i, int i2) {
        String strSubstring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return StringKt.toLowerCase(strSubstring, localeList);
    }

    public static /* synthetic */ AnnotatedString capitalize$default(AnnotatedString annotatedString, LocaleList localeList, int i, Object obj) {
        if ((i & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return capitalize(annotatedString, localeList);
    }

    public static final AnnotatedString capitalize(AnnotatedString annotatedString, final LocaleList localeList) {
        return JvmAnnotatedString_jvmKt.transform(annotatedString, new Function3() { // from class: androidx.compose.ui.text.AnnotatedStringKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AnnotatedStringKt.capitalize$lambda$14(localeList, (String) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        });
    }

    static final String capitalize$lambda$14(LocaleList localeList, String str, int i, int i2) {
        if (i == 0) {
            String strSubstring = str.substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return StringKt.capitalize(strSubstring, localeList);
        }
        String strSubstring2 = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        return strSubstring2;
    }

    public static /* synthetic */ AnnotatedString decapitalize$default(AnnotatedString annotatedString, LocaleList localeList, int i, Object obj) {
        if ((i & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return decapitalize(annotatedString, localeList);
    }

    public static final AnnotatedString decapitalize(AnnotatedString annotatedString, final LocaleList localeList) {
        return JvmAnnotatedString_jvmKt.transform(annotatedString, new Function3() { // from class: androidx.compose.ui.text.AnnotatedStringKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return AnnotatedStringKt.decapitalize$lambda$15(localeList, (String) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        });
    }

    static final String decapitalize$lambda$15(LocaleList localeList, String str, int i, int i2) {
        if (i == 0) {
            String strSubstring = str.substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return StringKt.decapitalize(strSubstring, localeList);
        }
        String strSubstring2 = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        return strSubstring2;
    }

    public static final <R> R withStyle(AnnotatedString.Builder builder, SpanStyle spanStyle, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int iPushStyle = builder.pushStyle(spanStyle);
        try {
            return function1.invoke(builder);
        } finally {
            builder.pop(iPushStyle);
        }
    }

    public static final <R> R withStyle(AnnotatedString.Builder builder, ParagraphStyle paragraphStyle, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int iPushStyle = builder.pushStyle(paragraphStyle);
        try {
            return function1.invoke(builder);
        } finally {
            builder.pop(iPushStyle);
        }
    }

    public static final <R> R withAnnotation(AnnotatedString.Builder builder, String str, String str2, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int iPushStringAnnotation = builder.pushStringAnnotation(str, str2);
        try {
            return function1.invoke(builder);
        } finally {
            builder.pop(iPushStringAnnotation);
        }
    }

    public static final <R> R withAnnotation(AnnotatedString.Builder builder, TtsAnnotation ttsAnnotation, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int iPushTtsAnnotation = builder.pushTtsAnnotation(ttsAnnotation);
        try {
            return function1.invoke(builder);
        } finally {
            builder.pop(iPushTtsAnnotation);
        }
    }

    @Deprecated(message = "Use LinkAnnotation API for links instead", replaceWith = @ReplaceWith(expression = "withLink(, block)", imports = {}))
    public static final <R> R withAnnotation(AnnotatedString.Builder builder, UrlAnnotation urlAnnotation, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int iPushUrlAnnotation = builder.pushUrlAnnotation(urlAnnotation);
        try {
            return function1.invoke(builder);
        } finally {
            builder.pop(iPushUrlAnnotation);
        }
    }

    public static final <R> R withLink(AnnotatedString.Builder builder, LinkAnnotation linkAnnotation, Function1<? super AnnotatedString.Builder, ? extends R> function1) {
        int iPushLink = builder.pushLink(linkAnnotation);
        try {
            return function1.invoke(builder);
        } finally {
            builder.pop(iPushLink);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> List<AnnotatedString.Range<T>> filterRanges(List<? extends AnnotatedString.Range<? extends T>> list, int i, int i2) {
        if (!(i <= i2)) {
            InlineClassHelperKt.throwIllegalArgumentException("start (" + i + ") should be less than or equal to end (" + i2 + ')');
        }
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            AnnotatedString.Range<? extends T> range = list.get(i3);
            if (intersect(i, i2, range.getStart(), range.getEnd())) {
                arrayList.add(new AnnotatedString.Range(range.getItem(), Math.max(i, range.getStart()) - i, Math.min(i2, range.getEnd()) - i, range.getTag()));
            }
        }
        ArrayList arrayList2 = arrayList;
        return arrayList2.isEmpty() ? null : arrayList2;
    }

    public static /* synthetic */ AnnotatedString AnnotatedString$default(String str, SpanStyle spanStyle, ParagraphStyle paragraphStyle, int i, Object obj) {
        if ((i & 4) != 0) {
            paragraphStyle = null;
        }
        return AnnotatedString(str, spanStyle, paragraphStyle);
    }

    public static final AnnotatedString AnnotatedString(String str, SpanStyle spanStyle, ParagraphStyle paragraphStyle) {
        return new AnnotatedString(str, CollectionsKt.listOf(new AnnotatedString.Range(spanStyle, 0, str.length())), paragraphStyle == null ? CollectionsKt.emptyList() : CollectionsKt.listOf(new AnnotatedString.Range(paragraphStyle, 0, str.length())));
    }

    public static final AnnotatedString AnnotatedString(String str, ParagraphStyle paragraphStyle) {
        return new AnnotatedString(str, CollectionsKt.emptyList(), CollectionsKt.listOf(new AnnotatedString.Range(paragraphStyle, 0, str.length())));
    }

    public static final AnnotatedString buildAnnotatedString(Function1<? super AnnotatedString.Builder, Unit> function1) {
        AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, null);
        function1.invoke(builder);
        return builder.toAnnotatedString();
    }

    public static final AnnotatedString emptyAnnotatedString() {
        return EmptyAnnotatedString;
    }
}
