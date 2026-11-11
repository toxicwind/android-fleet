package androidx.compose.ui.text;

import androidx.collection.IntListKt;
import androidx.collection.MutableIntList;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnnotatedString.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 D2\u00020\u0001:\u0005@ABCDB)\b\u0000\u0012\u0016\u0010\u0002\u001a\u0012\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB=\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u0003\u0012\u0014\b\u0002\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00040\u0003¢\u0006\u0004\b\b\u0010\u000eB)\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00040\u0003¢\u0006\u0004\b\b\u0010\u000fJ\u0011\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001bH\u0096\u0002J\u0018\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u001bH\u0016J\u0015\u0010!\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%¢\u0006\u0004\b&\u0010'J\u0011\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u0000H\u0087\u0002J*\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00032\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u001bJ\u001e\u0010.\u001a\u00020/2\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u001bJ\"\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00032\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u001bJ\"\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002010\u00040\u00032\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u001bJ$\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002030\u00040\u00032\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u001bH\u0007J\"\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u00040\u00032\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u001bJ\u0016\u00106\u001a\u00020/2\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010-\u001a\u00020\u001bJ\u0013\u00107\u001a\u00020/2\b\u0010)\u001a\u0004\u0018\u000108H\u0096\u0002J\b\u00109\u001a\u00020\u001bH\u0016J\b\u0010:\u001a\u00020\u0007H\u0016J\u000e\u0010;\u001a\u00020/2\u0006\u0010)\u001a\u00020\u0000J*\u0010<\u001a\u00020\u00002\"\u0010=\u001a\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00040>J0\u0010?\u001a\u00020\u00002(\u0010=\u001a$\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00040\u00030>R$\u0010\u0002\u001a\u0012\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\u0014\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00038F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0011R\"\u0010\u0017\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00040\u00038F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0011R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006E"}, d2 = {"Landroidx/compose/ui/text/AnnotatedString;", "", "annotations", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "text", "", "<init>", "(Ljava/util/List;Ljava/lang/String;)V", "spanStyles", "Landroidx/compose/ui/text/SpanStyle;", "paragraphStyles", "Landroidx/compose/ui/text/ParagraphStyle;", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "(Ljava/lang/String;Ljava/util/List;)V", "getAnnotations$ui_text", "()Ljava/util/List;", "getText", "()Ljava/lang/String;", "spanStylesOrNull", "getSpanStylesOrNull$ui_text", "getSpanStyles", "paragraphStylesOrNull", "getParagraphStylesOrNull$ui_text", "getParagraphStyles", "length", "", "getLength", "()I", "get", "", "index", "subSequence", "startIndex", "endIndex", "range", "Landroidx/compose/ui/text/TextRange;", "subSequence-5zc-tL8", "(J)Landroidx/compose/ui/text/AnnotatedString;", "plus", "other", "getStringAnnotations", "tag", "start", TtmlNode.END, "hasStringAnnotations", "", "getTtsAnnotations", "Landroidx/compose/ui/text/TtsAnnotation;", "getUrlAnnotations", "Landroidx/compose/ui/text/UrlAnnotation;", "getLinkAnnotations", "Landroidx/compose/ui/text/LinkAnnotation;", "hasLinkAnnotations", "equals", "", "hashCode", "toString", "hasEqualAnnotations", "mapAnnotations", "transform", "Lkotlin/Function1;", "flatMapAnnotations", "Range", "Builder", "Annotation", "ExhaustiveAnnotation", "Companion", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AnnotatedString implements CharSequence {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<AnnotatedString, ?> Saver = SaversKt.getAnnotatedStringSaver();
    private final List<Range<? extends Annotation>> annotations;
    private final List<Range<ParagraphStyle>> paragraphStylesOrNull;
    private final List<Range<SpanStyle>> spanStylesOrNull;
    private final String text;

    /* JADX INFO: compiled from: AnnotatedString.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001\u0082\u0001\b\u0002\u0003\u0004\u0005\u0006\u0007\b\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/text/AnnotatedString$Annotation;", "", "Landroidx/compose/ui/text/AnnotatedString$ExhaustiveAnnotation;", "Landroidx/compose/ui/text/Bullet;", "Landroidx/compose/ui/text/LinkAnnotation;", "Landroidx/compose/ui/text/ParagraphStyle;", "Landroidx/compose/ui/text/SpanStyle;", "Landroidx/compose/ui/text/StringAnnotation;", "Landroidx/compose/ui/text/TtsAnnotation;", "Landroidx/compose/ui/text/UrlAnnotation;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Annotation {
    }

    /* JADX INFO: compiled from: AnnotatedString.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/text/AnnotatedString$ExhaustiveAnnotation;", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "<init>", "()V", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class ExhaustiveAnnotation implements Annotation {
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ char charAt(int i) {
        return get(i);
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ int length() {
        return getLength();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AnnotatedString(List<? extends Range<? extends Annotation>> list, String str) {
        ArrayList arrayList;
        ArrayList arrayList2;
        this.annotations = list;
        this.text = str;
        if (list != 0) {
            int size = list.size();
            arrayList = null;
            arrayList2 = null;
            for (int i = 0; i < size; i++) {
                Range<SpanStyle> range = (Range) list.get(i);
                if (range.getItem() instanceof SpanStyle) {
                    arrayList = arrayList == null ? new ArrayList() : arrayList;
                    Intrinsics.checkNotNull(range, "null cannot be cast to non-null type androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.SpanStyle>");
                    arrayList.add(range);
                } else if (range.getItem() instanceof ParagraphStyle) {
                    arrayList2 = arrayList2 == null ? new ArrayList() : arrayList2;
                    Intrinsics.checkNotNull(range, "null cannot be cast to non-null type androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.ParagraphStyle>");
                    arrayList2.add(range);
                }
            }
        } else {
            arrayList = null;
            arrayList2 = null;
        }
        this.spanStylesOrNull = arrayList;
        this.paragraphStylesOrNull = arrayList2;
        List listSortedWith = arrayList2 != null ? CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: androidx.compose.ui.text.AnnotatedString$special$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((AnnotatedString.Range) t).getStart()), Integer.valueOf(((AnnotatedString.Range) t2).getStart()));
            }
        }) : null;
        List list2 = listSortedWith;
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        MutableIntList mutableIntListMutableIntListOf = IntListKt.mutableIntListOf(((Range) CollectionsKt.first(listSortedWith)).getEnd());
        int size2 = listSortedWith.size();
        for (int i2 = 1; i2 < size2; i2++) {
            Range range2 = (Range) listSortedWith.get(i2);
            while (true) {
                MutableIntList mutableIntList = mutableIntListMutableIntListOf;
                if (mutableIntList._size == 0) {
                    break;
                }
                int iLast = mutableIntListMutableIntListOf.last();
                if (range2.getStart() >= iLast) {
                    mutableIntListMutableIntListOf.removeAt(mutableIntList._size - 1);
                } else {
                    if (!(range2.getEnd() <= iLast)) {
                        InlineClassHelperKt.throwIllegalArgumentException("Paragraph overlap not allowed, end " + range2.getEnd() + " should be less than or equal to " + iLast);
                        break;
                    }
                    break;
                }
            }
            mutableIntListMutableIntListOf.add(range2.getEnd());
        }
    }

    public final List<Range<? extends Annotation>> getAnnotations$ui_text() {
        return this.annotations;
    }

    public final String getText() {
        return this.text;
    }

    public final List<Range<SpanStyle>> getSpanStylesOrNull$ui_text() {
        return this.spanStylesOrNull;
    }

    public final List<Range<SpanStyle>> getSpanStyles() {
        List<Range<SpanStyle>> list = this.spanStylesOrNull;
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public final List<Range<ParagraphStyle>> getParagraphStylesOrNull$ui_text() {
        return this.paragraphStylesOrNull;
    }

    public final List<Range<ParagraphStyle>> getParagraphStyles() {
        List<Range<ParagraphStyle>> list = this.paragraphStylesOrNull;
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public /* synthetic */ AnnotatedString(String str, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public AnnotatedString(String str, List<Range<SpanStyle>> list, List<Range<ParagraphStyle>> list2) {
        this((List<? extends Range<? extends Annotation>>) AnnotatedStringKt.constructAnnotationsFromSpansAndParagraphs(list, list2), str);
    }

    public /* synthetic */ AnnotatedString(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (List<? extends Range<? extends Annotation>>) ((i & 2) != 0 ? CollectionsKt.emptyList() : list));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public AnnotatedString(String str, List<? extends Range<? extends Annotation>> list) {
        List<? extends Range<? extends Annotation>> list2 = list;
        this(list2.isEmpty() ? null : list2, str);
    }

    public int getLength() {
        return this.text.length();
    }

    public char get(int index) {
        return this.text.charAt(index);
    }

    @Override // java.lang.CharSequence
    public AnnotatedString subSequence(int startIndex, int endIndex) {
        if (!(startIndex <= endIndex)) {
            InlineClassHelperKt.throwIllegalArgumentException("start (" + startIndex + ") should be less or equal to end (" + endIndex + ')');
        }
        if (startIndex == 0 && endIndex == this.text.length()) {
            return this;
        }
        String strSubstring = this.text.substring(startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return new AnnotatedString((List<? extends Range<? extends Annotation>>) AnnotatedStringKt.filterRanges(this.annotations, startIndex, endIndex), strSubstring);
    }

    /* JADX INFO: renamed from: subSequence-5zc-tL8, reason: not valid java name */
    public final AnnotatedString m6179subSequence5zctL8(long range) {
        return subSequence(TextRange.m6342getMinimpl(range), TextRange.m6341getMaximpl(range));
    }

    public final AnnotatedString plus(AnnotatedString other) {
        Builder builder = new Builder(this);
        builder.append(other);
        return builder.toAnnotatedString();
    }

    public final List<Range<String>> getStringAnnotations(String tag, int start, int end) {
        List<Range<? extends Annotation>> list = this.annotations;
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Range<? extends Annotation> range = list.get(i);
            if ((range.getItem() instanceof StringAnnotation) && Intrinsics.areEqual(tag, range.getTag()) && AnnotatedStringKt.intersect(start, end, range.getStart(), range.getEnd())) {
                arrayList.add(StringAnnotationKt.unbox(range));
            }
        }
        return arrayList;
    }

    public final boolean hasStringAnnotations(String tag, int start, int end) {
        List<Range<? extends Annotation>> list = this.annotations;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Range<? extends Annotation> range = list.get(i);
                if ((range.getItem() instanceof StringAnnotation) && Intrinsics.areEqual(tag, range.getTag()) && AnnotatedStringKt.intersect(start, end, range.getStart(), range.getEnd())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final List<Range<String>> getStringAnnotations(int start, int end) {
        List<Range<? extends Annotation>> list = this.annotations;
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Range<? extends Annotation> range = list.get(i);
            if ((range.getItem() instanceof StringAnnotation) && AnnotatedStringKt.intersect(start, end, range.getStart(), range.getEnd())) {
                arrayList.add(StringAnnotationKt.unbox(range));
            }
        }
        return arrayList;
    }

    public final List<Range<TtsAnnotation>> getTtsAnnotations(int start, int end) {
        ArrayList arrayListEmptyList;
        List<Range<? extends Annotation>> list = this.annotations;
        if (list == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        } else {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Range<? extends Annotation> range = list.get(i);
                Range<? extends Annotation> range2 = range;
                if ((range2.getItem() instanceof TtsAnnotation) && AnnotatedStringKt.intersect(start, end, range2.getStart(), range2.getEnd())) {
                    arrayList.add(range);
                }
            }
            arrayListEmptyList = arrayList;
        }
        Intrinsics.checkNotNull(arrayListEmptyList, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.TtsAnnotation>>");
        return arrayListEmptyList;
    }

    @Deprecated(message = "Use LinkAnnotation API instead", replaceWith = @ReplaceWith(expression = "getLinkAnnotations(start, end)", imports = {}))
    public final List<Range<UrlAnnotation>> getUrlAnnotations(int start, int end) {
        ArrayList arrayListEmptyList;
        List<Range<? extends Annotation>> list = this.annotations;
        if (list == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        } else {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Range<? extends Annotation> range = list.get(i);
                Range<? extends Annotation> range2 = range;
                if ((range2.getItem() instanceof UrlAnnotation) && AnnotatedStringKt.intersect(start, end, range2.getStart(), range2.getEnd())) {
                    arrayList.add(range);
                }
            }
            arrayListEmptyList = arrayList;
        }
        Intrinsics.checkNotNull(arrayListEmptyList, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.UrlAnnotation>>");
        return arrayListEmptyList;
    }

    public final List<Range<LinkAnnotation>> getLinkAnnotations(int start, int end) {
        ArrayList arrayListEmptyList;
        List<Range<? extends Annotation>> list = this.annotations;
        if (list == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        } else {
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Range<? extends Annotation> range = list.get(i);
                Range<? extends Annotation> range2 = range;
                if ((range2.getItem() instanceof LinkAnnotation) && AnnotatedStringKt.intersect(start, end, range2.getStart(), range2.getEnd())) {
                    arrayList.add(range);
                }
            }
            arrayListEmptyList = arrayList;
        }
        Intrinsics.checkNotNull(arrayListEmptyList, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.LinkAnnotation>>");
        return arrayListEmptyList;
    }

    public final boolean hasLinkAnnotations(int start, int end) {
        List<Range<? extends Annotation>> list = this.annotations;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Range<? extends Annotation> range = list.get(i);
                if ((range.getItem() instanceof LinkAnnotation) && AnnotatedStringKt.intersect(start, end, range.getStart(), range.getEnd())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnnotatedString)) {
            return false;
        }
        AnnotatedString annotatedString = (AnnotatedString) other;
        return Intrinsics.areEqual(this.text, annotatedString.text) && Intrinsics.areEqual(this.annotations, annotatedString.annotations);
    }

    public int hashCode() {
        int iHashCode = this.text.hashCode() * 31;
        List<Range<? extends Annotation>> list = this.annotations;
        return iHashCode + (list != null ? list.hashCode() : 0);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.text;
    }

    public final boolean hasEqualAnnotations(AnnotatedString other) {
        return Intrinsics.areEqual(this.annotations, other.annotations);
    }

    public final AnnotatedString mapAnnotations(Function1<? super Range<? extends Annotation>, ? extends Range<? extends Annotation>> transform) {
        Builder builder = new Builder(this);
        builder.mapAnnotations$ui_text(transform);
        return builder.toAnnotatedString();
    }

    public final AnnotatedString flatMapAnnotations(Function1<? super Range<? extends Annotation>, ? extends List<? extends Range<? extends Annotation>>> transform) {
        Builder builder = new Builder(this);
        builder.flatMapAnnotations$ui_text(transform);
        return builder.toAnnotatedString();
    }

    /* JADX INFO: compiled from: AnnotatedString.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\u000bJ\u000e\u0010\u0014\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J<\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001e\u001a\u00020\bHÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Landroidx/compose/ui/text/AnnotatedString$Range;", ExifInterface.GPS_DIRECTION_TRUE, "", "item", "start", "", TtmlNode.END, "tag", "", "<init>", "(Ljava/lang/Object;IILjava/lang/String;)V", "(Ljava/lang/Object;II)V", "getItem", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getStart", "()I", "getEnd", "getTag", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Object;IILjava/lang/String;)Landroidx/compose/ui/text/AnnotatedString$Range;", "equals", "", "other", "hashCode", "toString", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Range<T> {
        public static final int $stable = 0;
        private final int end;
        private final T item;
        private final int start;
        private final String tag;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Range copy$default(Range range, Object obj, int i, int i2, String str, int i3, Object obj2) {
            if ((i3 & 1) != 0) {
                obj = range.item;
            }
            if ((i3 & 2) != 0) {
                i = range.start;
            }
            if ((i3 & 4) != 0) {
                i2 = range.end;
            }
            if ((i3 & 8) != 0) {
                str = range.tag;
            }
            return range.copy(obj, i, i2, str);
        }

        public final T component1() {
            return this.item;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getStart() {
            return this.start;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getEnd() {
            return this.end;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTag() {
            return this.tag;
        }

        public final Range<T> copy(T item, int start, int end, String tag) {
            return new Range<>(item, start, end, tag);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Range)) {
                return false;
            }
            Range range = (Range) other;
            return Intrinsics.areEqual(this.item, range.item) && this.start == range.start && this.end == range.end && Intrinsics.areEqual(this.tag, range.tag);
        }

        public int hashCode() {
            T t = this.item;
            return ((((((t == null ? 0 : t.hashCode()) * 31) + this.start) * 31) + this.end) * 31) + this.tag.hashCode();
        }

        public String toString() {
            return "Range(item=" + this.item + ", start=" + this.start + ", end=" + this.end + ", tag=" + this.tag + ')';
        }

        public Range(T t, int i, int i2, String str) {
            this.item = t;
            this.start = i;
            this.end = i2;
            this.tag = str;
            if (i <= i2) {
                return;
            }
            InlineClassHelperKt.throwIllegalArgumentException("Reversed range is not supported");
        }

        public final int getEnd() {
            return this.end;
        }

        public final T getItem() {
            return this.item;
        }

        public final int getStart() {
            return this.start;
        }

        public final String getTag() {
            return this.tag;
        }

        public Range(T t, int i, int i2) {
            this(t, i, i2, "");
        }
    }

    /* JADX INFO: compiled from: AnnotatedString.kt */
    @Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0002UVB\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\bJ\u0015\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0007¢\u0006\u0002\b\u0017J\u0012\u0010\u0017\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u001cH\u0016J\"\u0010\u0017\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004H\u0016J\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\nJ\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004J\u001e\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004J\u001e\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004J&\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004J\u001e\u0010&\u001a\u00020\u00182\u0006\u0010'\u001a\u00020(2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004J \u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020+2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004H\u0007J\u001e\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004J\u001e\u0010,\u001a\u00020\u00182\u0006\u0010/\u001a\u0002002\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004J\u001e\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u0002032\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004J-\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004¢\u0006\u0004\b6\u00107J\u000e\u00108\u001a\u00020\u00042\u0006\u0010 \u001a\u00020!J\u000e\u00108\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\"J\u000e\u00109\u001a\u00020\u00042\u0006\u00102\u001a\u000203JD\u0010<\u001a\u0002H=\"\b\b\u0000\u0010=*\u00020\u00112\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00102\u001a\u0002032\u0017\u0010>\u001a\u0013\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u0002H=0?¢\u0006\u0002\b@¢\u0006\u0004\bA\u0010BJ>\u0010C\u001a\u0002H=\"\b\b\u0000\u0010=*\u00020\u0011*\u00020;2\n\b\u0002\u00102\u001a\u0004\u0018\u0001032\u0017\u0010>\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H=0?¢\u0006\u0002\b@¢\u0006\u0002\u0010DJ\u0016\u0010E\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\bJ\u000e\u0010F\u001a\u00020\u00042\u0006\u0010'\u001a\u00020(J\u0010\u0010G\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+H\u0007J\u000e\u0010H\u001a\u00020\u00042\u0006\u0010I\u001a\u00020JJ\u0006\u0010K\u001a\u00020\u0018J\u000e\u0010K\u001a\u00020\u00182\u0006\u0010L\u001a\u00020\u0004J\u0006\u0010M\u001a\u00020\nJ1\u0010N\u001a\u00020\u00182\"\u0010O\u001a\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00130P\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00130P0?H\u0000¢\u0006\u0002\bQJ7\u0010R\u001a\u00020\u00182(\u0010O\u001a$\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00130P\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00130P0S0?H\u0000¢\u0006\u0002\bTR\u0012\u0010\u0007\u001a\u00060\fj\u0002`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Landroidx/compose/ui/text/AnnotatedString$Builder;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "capacity", "", "<init>", "(I)V", "text", "", "(Ljava/lang/String;)V", "Landroidx/compose/ui/text/AnnotatedString;", "(Landroidx/compose/ui/text/AnnotatedString;)V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "styleStack", "", "Landroidx/compose/ui/text/AnnotatedString$Builder$MutableRange;", "", "annotations", "Landroidx/compose/ui/text/AnnotatedString$Annotation;", "length", "getLength", "()I", "append", "", "deprecated_append_returning_void", "char", "", "", "start", TtmlNode.END, "addStyle", "style", "Landroidx/compose/ui/text/SpanStyle;", "Landroidx/compose/ui/text/ParagraphStyle;", "addStringAnnotation", "tag", "annotation", "addTtsAnnotation", "ttsAnnotation", "Landroidx/compose/ui/text/TtsAnnotation;", "addUrlAnnotation", "urlAnnotation", "Landroidx/compose/ui/text/UrlAnnotation;", "addLink", ImagesContract.URL, "Landroidx/compose/ui/text/LinkAnnotation$Url;", "clickable", "Landroidx/compose/ui/text/LinkAnnotation$Clickable;", "addBullet", "bullet", "Landroidx/compose/ui/text/Bullet;", "indentation", "Landroidx/compose/ui/unit/TextUnit;", "addBullet-r9BaKPg", "(Landroidx/compose/ui/text/Bullet;JII)V", "pushStyle", "pushBullet", "bulletScope", "Landroidx/compose/ui/text/AnnotatedString$Builder$BulletScope;", "withBulletList", "R", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "withBulletList-o2QH7mI", "(JLandroidx/compose/ui/text/Bullet;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "withBulletListItem", "(Landroidx/compose/ui/text/AnnotatedString$Builder$BulletScope;Landroidx/compose/ui/text/Bullet;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "pushStringAnnotation", "pushTtsAnnotation", "pushUrlAnnotation", "pushLink", "link", "Landroidx/compose/ui/text/LinkAnnotation;", "pop", "index", "toAnnotatedString", "mapAnnotations", "transform", "Landroidx/compose/ui/text/AnnotatedString$Range;", "mapAnnotations$ui_text", "flatMapAnnotations", "", "flatMapAnnotations$ui_text", "MutableRange", "BulletScope", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Builder implements Appendable {
        public static final int $stable = 8;
        private final List<MutableRange<? extends Annotation>> annotations;
        private final BulletScope bulletScope;
        private final List<MutableRange<? extends Object>> styleStack;
        private final StringBuilder text;

        public Builder() {
            this(0, 1, null);
        }

        public Builder(int i) {
            this.text = new StringBuilder(i);
            this.styleStack = new ArrayList();
            this.annotations = new ArrayList();
            this.bulletScope = new BulletScope(this);
        }

        public /* synthetic */ Builder(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 16 : i);
        }

        /* JADX INFO: compiled from: AnnotatedString.kt */
        @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0082\b\u0018\u0000 &*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001&B+\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0005J0\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0016\"\u0004\b\u0001\u0010\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0017\u001a\u00020\u0005J\u000e\u0010\u001b\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J<\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\t\u0010%\u001a\u00020\bHÖ\u0001R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000f\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006'"}, d2 = {"Landroidx/compose/ui/text/AnnotatedString$Builder$MutableRange;", ExifInterface.GPS_DIRECTION_TRUE, "", "item", "start", "", TtmlNode.END, "tag", "", "<init>", "(Ljava/lang/Object;IILjava/lang/String;)V", "getItem", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getStart", "()I", "getEnd", "setEnd", "(I)V", "getTag", "()Ljava/lang/String;", "toRange", "Landroidx/compose/ui/text/AnnotatedString$Range;", "defaultEnd", "R", "transform", "Lkotlin/Function1;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Object;IILjava/lang/String;)Landroidx/compose/ui/text/AnnotatedString$Builder$MutableRange;", "equals", "", "other", "hashCode", "toString", "Companion", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
        private static final /* data */ class MutableRange<T> {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private int end;
            private final T item;
            private final int start;
            private final String tag;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ MutableRange copy$default(MutableRange mutableRange, Object obj, int i, int i2, String str, int i3, Object obj2) {
                if ((i3 & 1) != 0) {
                    obj = mutableRange.item;
                }
                if ((i3 & 2) != 0) {
                    i = mutableRange.start;
                }
                if ((i3 & 4) != 0) {
                    i2 = mutableRange.end;
                }
                if ((i3 & 8) != 0) {
                    str = mutableRange.tag;
                }
                return mutableRange.copy(obj, i, i2, str);
            }

            public final T component1() {
                return this.item;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final int getStart() {
                return this.start;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final int getEnd() {
                return this.end;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final String getTag() {
                return this.tag;
            }

            public final MutableRange<T> copy(T item, int start, int end, String tag) {
                return new MutableRange<>(item, start, end, tag);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof MutableRange)) {
                    return false;
                }
                MutableRange mutableRange = (MutableRange) other;
                return Intrinsics.areEqual(this.item, mutableRange.item) && this.start == mutableRange.start && this.end == mutableRange.end && Intrinsics.areEqual(this.tag, mutableRange.tag);
            }

            public int hashCode() {
                T t = this.item;
                return ((((((t == null ? 0 : t.hashCode()) * 31) + this.start) * 31) + this.end) * 31) + this.tag.hashCode();
            }

            public String toString() {
                return "MutableRange(item=" + this.item + ", start=" + this.start + ", end=" + this.end + ", tag=" + this.tag + ')';
            }

            public MutableRange(T t, int i, int i2, String str) {
                this.item = t;
                this.start = i;
                this.end = i2;
                this.tag = str;
            }

            public final T getItem() {
                return this.item;
            }

            public final int getStart() {
                return this.start;
            }

            public final int getEnd() {
                return this.end;
            }

            public final void setEnd(int i) {
                this.end = i;
            }

            public /* synthetic */ MutableRange(Object obj, int i, int i2, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(obj, i, (i3 & 4) != 0 ? Integer.MIN_VALUE : i2, (i3 & 8) != 0 ? "" : str);
            }

            public final String getTag() {
                return this.tag;
            }

            public static /* synthetic */ Range toRange$default(MutableRange mutableRange, int i, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = Integer.MIN_VALUE;
                }
                return mutableRange.toRange(i);
            }

            public final Range<T> toRange(int defaultEnd) {
                int i = this.end;
                if (i != Integer.MIN_VALUE) {
                    defaultEnd = i;
                }
                if (!(defaultEnd != Integer.MIN_VALUE)) {
                    InlineClassHelperKt.throwIllegalStateException("Item.end should be set first");
                }
                return new Range<>(this.item, this.start, defaultEnd, this.tag);
            }

            public static /* synthetic */ Range toRange$default(MutableRange mutableRange, Function1 function1, int i, int i2, Object obj) {
                if ((i2 & 2) != 0) {
                    i = Integer.MIN_VALUE;
                }
                return mutableRange.toRange(function1, i);
            }

            public final <R> Range<R> toRange(Function1<? super T, ? extends R> transform, int defaultEnd) {
                int i = this.end;
                if (i != Integer.MIN_VALUE) {
                    defaultEnd = i;
                }
                if (!(defaultEnd != Integer.MIN_VALUE)) {
                    InlineClassHelperKt.throwIllegalStateException("Item.end should be set first");
                }
                return new Range<>(transform.invoke(this.item), this.start, defaultEnd, this.tag);
            }

            /* JADX INFO: compiled from: AnnotatedString.kt */
            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0001\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/text/AnnotatedString$Builder$MutableRange$Companion;", "", "<init>", "()V", "fromRange", "Landroidx/compose/ui/text/AnnotatedString$Builder$MutableRange;", ExifInterface.GPS_DIRECTION_TRUE, "range", "Landroidx/compose/ui/text/AnnotatedString$Range;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final <T> MutableRange<T> fromRange(Range<T> range) {
                    return new MutableRange<>(range.getItem(), range.getStart(), range.getEnd(), range.getTag());
                }
            }
        }

        public Builder(String str) {
            this(0, 1, null);
            append(str);
        }

        public Builder(AnnotatedString annotatedString) {
            this(0, 1, null);
            append(annotatedString);
        }

        public final int getLength() {
            return this.text.length();
        }

        public final void append(String text) {
            this.text.append(text);
        }

        @Override // java.lang.Appendable
        public Builder append(CharSequence text) {
            if (text instanceof AnnotatedString) {
                append((AnnotatedString) text);
                return this;
            }
            this.text.append(text);
            return this;
        }

        @Override // java.lang.Appendable
        public Builder append(CharSequence text, int start, int end) {
            if (text instanceof AnnotatedString) {
                append((AnnotatedString) text, start, end);
                return this;
            }
            this.text.append(text, start, end);
            return this;
        }

        @Override // java.lang.Appendable
        public Builder append(char c) {
            this.text.append(c);
            return this;
        }

        public final void append(AnnotatedString text) {
            int length = this.text.length();
            this.text.append(text.getText());
            List<Range<? extends Annotation>> annotations$ui_text = text.getAnnotations$ui_text();
            if (annotations$ui_text != null) {
                int size = annotations$ui_text.size();
                for (int i = 0; i < size; i++) {
                    Range<? extends Annotation> range = annotations$ui_text.get(i);
                    this.annotations.add(new MutableRange<>(range.getItem(), range.getStart() + length, range.getEnd() + length, range.getTag()));
                }
            }
        }

        public final void append(AnnotatedString text, int start, int end) {
            int length = this.text.length();
            this.text.append((CharSequence) text.getText(), start, end);
            List localAnnotations$default = AnnotatedStringKt.getLocalAnnotations$default(text, start, end, null, 4, null);
            if (localAnnotations$default != null) {
                int size = localAnnotations$default.size();
                for (int i = 0; i < size; i++) {
                    Range range = (Range) localAnnotations$default.get(i);
                    this.annotations.add(new MutableRange<>(range.getItem(), range.getStart() + length, range.getEnd() + length, range.getTag()));
                }
            }
        }

        public final void addStyle(SpanStyle style, int start, int end) {
            this.annotations.add(new MutableRange<>(style, start, end, null, 8, null));
        }

        public final void addStyle(ParagraphStyle style, int start, int end) {
            this.annotations.add(new MutableRange<>(style, start, end, null, 8, null));
        }

        public final void addStringAnnotation(String tag, String annotation, int start, int end) {
            this.annotations.add(new MutableRange<>(StringAnnotation.m6292boximpl(StringAnnotation.m6293constructorimpl(annotation)), start, end, tag));
        }

        public final void addTtsAnnotation(TtsAnnotation ttsAnnotation, int start, int end) {
            this.annotations.add(new MutableRange<>(ttsAnnotation, start, end, null, 8, null));
        }

        @Deprecated(message = "Use LinkAnnotation API for links instead", replaceWith = @ReplaceWith(expression = "addLink(, start, end)", imports = {}))
        public final void addUrlAnnotation(UrlAnnotation urlAnnotation, int start, int end) {
            this.annotations.add(new MutableRange<>(urlAnnotation, start, end, null, 8, null));
        }

        public final void addLink(LinkAnnotation.Url url, int start, int end) {
            this.annotations.add(new MutableRange<>(url, start, end, null, 8, null));
        }

        public final void addLink(LinkAnnotation.Clickable clickable, int start, int end) {
            this.annotations.add(new MutableRange<>(clickable, start, end, null, 8, null));
        }

        public final void addBullet(Bullet bullet, int start, int end) {
            this.annotations.add(new MutableRange<>(bullet, start, end, null, 8, null));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX INFO: renamed from: addBullet-r9BaKPg, reason: not valid java name */
        public final void m6181addBulletr9BaKPg(Bullet bullet, long indentation, int start, int end) {
            Object[] objArr = 0 == true ? 1 : 0;
            this.annotations.add(new MutableRange<>(new ParagraphStyle(0, 0, 0L, new TextIndent(indentation, indentation, null), (PlatformParagraphStyle) objArr, (LineHeightStyle) null, 0, 0, (TextMotion) null, TypedValues.PositionType.TYPE_PERCENT_WIDTH, (DefaultConstructorMarker) null), start, end, null, 8, 0 == true ? 1 : 0));
            this.annotations.add(new MutableRange<>(bullet, start, end, 0 == true ? 1 : 0, 8, null));
        }

        public final int pushStyle(SpanStyle style) {
            MutableRange<? extends Annotation> mutableRange = new MutableRange<>(style, this.text.length(), 0, null, 12, null);
            this.styleStack.add(mutableRange);
            this.annotations.add(mutableRange);
            return this.styleStack.size() - 1;
        }

        public final int pushStyle(ParagraphStyle style) {
            MutableRange<? extends Annotation> mutableRange = new MutableRange<>(style, this.text.length(), 0, null, 12, null);
            this.styleStack.add(mutableRange);
            this.annotations.add(mutableRange);
            return this.styleStack.size() - 1;
        }

        public final int pushBullet(Bullet bullet) {
            MutableRange<? extends Annotation> mutableRange = new MutableRange<>(bullet, this.text.length(), 0, null, 12, null);
            this.styleStack.add(mutableRange);
            this.annotations.add(mutableRange);
            return this.styleStack.size() - 1;
        }

        /* JADX INFO: compiled from: AnnotatedString.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R&\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n0\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/text/AnnotatedString$Builder$BulletScope;", "", "builder", "Landroidx/compose/ui/text/AnnotatedString$Builder;", "<init>", "(Landroidx/compose/ui/text/AnnotatedString$Builder;)V", "getBuilder$ui_text", "()Landroidx/compose/ui/text/AnnotatedString$Builder;", "bulletListSettingStack", "", "Lkotlin/Pair;", "Landroidx/compose/ui/unit/TextUnit;", "Landroidx/compose/ui/text/Bullet;", "getBulletListSettingStack$ui_text", "()Ljava/util/List;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class BulletScope {
            public static final int $stable = 8;
            private final Builder builder;
            private final List<Pair<TextUnit, Bullet>> bulletListSettingStack = new ArrayList();

            public BulletScope(Builder builder) {
                this.builder = builder;
            }

            /* JADX INFO: renamed from: getBuilder$ui_text, reason: from getter */
            public final Builder getBuilder() {
                return this.builder;
            }

            public final List<Pair<TextUnit, Bullet>> getBulletListSettingStack$ui_text() {
                return this.bulletListSettingStack;
            }
        }

        /* JADX INFO: renamed from: withBulletList-o2QH7mI$default, reason: not valid java name */
        public static /* synthetic */ Object m6180withBulletListo2QH7mI$default(Builder builder, long j, Bullet bullet, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                j = Bullet.INSTANCE.m6188getDefaultIndentationXSAIIZE();
            }
            if ((i & 2) != 0) {
                bullet = Bullet.INSTANCE.getDefault();
            }
            return builder.m6182withBulletListo2QH7mI(j, bullet, function1);
        }

        /* JADX INFO: renamed from: withBulletList-o2QH7mI, reason: not valid java name */
        public final <R> R m6182withBulletListo2QH7mI(long indentation, Bullet bullet, Function1<? super BulletScope, ? extends R> block) {
            long j;
            long em;
            Pair pair = (Pair) CollectionsKt.lastOrNull((List) this.bulletScope.getBulletListSettingStack$ui_text());
            if (pair != null) {
                long packedValue = ((TextUnit) pair.getFirst()).getPackedValue();
                if (!TextUnitType.m7116equalsimpl0(TextUnit.m7087getTypeUIouoOA(packedValue), TextUnit.m7087getTypeUIouoOA(indentation))) {
                    InlineClassHelperKt.throwIllegalStateException("Indentation unit types of nested bullet lists must match. Current " + ((Object) TextUnit.m7095toStringimpl(packedValue)) + " and previous is " + ((Object) TextUnit.m7095toStringimpl(indentation)));
                }
                long jM7087getTypeUIouoOA = TextUnit.m7087getTypeUIouoOA(indentation);
                if (TextUnitType.m7116equalsimpl0(jM7087getTypeUIouoOA, TextUnitType.INSTANCE.m7121getSpUIouoOA())) {
                    em = TextUnitKt.getSp(TextUnit.m7088getValueimpl(indentation) + TextUnit.m7088getValueimpl(packedValue));
                } else {
                    em = TextUnitType.m7116equalsimpl0(jM7087getTypeUIouoOA, TextUnitType.INSTANCE.m7120getEmUIouoOA()) ? TextUnitKt.getEm(TextUnit.m7088getValueimpl(indentation) + TextUnit.m7088getValueimpl(packedValue)) : indentation;
                }
                j = em;
            } else {
                j = indentation;
            }
            int iPushStyle = pushStyle(new ParagraphStyle(0, 0, 0L, new TextIndent(j, j, null), (PlatformParagraphStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, TypedValues.PositionType.TYPE_PERCENT_WIDTH, (DefaultConstructorMarker) null));
            this.bulletScope.getBulletListSettingStack$ui_text().add(new Pair<>(TextUnit.m7078boximpl(j), bullet));
            try {
                return block.invoke(this.bulletScope);
            } finally {
                if (!this.bulletScope.getBulletListSettingStack$ui_text().isEmpty()) {
                    this.bulletScope.getBulletListSettingStack$ui_text().remove(CollectionsKt.getLastIndex(this.bulletScope.getBulletListSettingStack$ui_text()));
                }
                pop(iPushStyle);
            }
        }

        public static /* synthetic */ Object withBulletListItem$default(Builder builder, BulletScope bulletScope, Bullet bullet, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                bullet = null;
            }
            return builder.withBulletListItem(bulletScope, bullet, function1);
        }

        public final <R> R withBulletListItem(BulletScope bulletScope, Bullet bullet, Function1<? super Builder, ? extends R> function1) {
            Bullet bullet2;
            Pair pair = (Pair) CollectionsKt.lastOrNull((List) bulletScope.getBulletListSettingStack$ui_text());
            long packedValue = pair != null ? ((TextUnit) pair.getFirst()).getPackedValue() : Bullet.INSTANCE.m6188getDefaultIndentationXSAIIZE();
            if (bullet != null) {
                bullet2 = bullet;
            } else if (pair == null || (bullet2 = (Bullet) pair.getSecond()) == null) {
                bullet2 = Bullet.INSTANCE.getDefault();
            }
            int iPushStyle = bulletScope.getBuilder().pushStyle(new ParagraphStyle(0, 0, 0L, new TextIndent(packedValue, packedValue, null), (PlatformParagraphStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, TypedValues.PositionType.TYPE_PERCENT_WIDTH, (DefaultConstructorMarker) null));
            int iPushBullet = bulletScope.getBuilder().pushBullet(bullet2);
            try {
                return function1.invoke(bulletScope.getBuilder());
            } finally {
                bulletScope.getBuilder().pop(iPushBullet);
                bulletScope.getBuilder().pop(iPushStyle);
            }
        }

        public final int pushStringAnnotation(String tag, String annotation) {
            MutableRange<? extends Annotation> mutableRange = new MutableRange<>(StringAnnotation.m6292boximpl(StringAnnotation.m6293constructorimpl(annotation)), this.text.length(), 0, tag, 4, null);
            this.styleStack.add(mutableRange);
            this.annotations.add(mutableRange);
            return this.styleStack.size() - 1;
        }

        public final int pushTtsAnnotation(TtsAnnotation ttsAnnotation) {
            MutableRange<? extends Annotation> mutableRange = new MutableRange<>(ttsAnnotation, this.text.length(), 0, null, 12, null);
            this.styleStack.add(mutableRange);
            this.annotations.add(mutableRange);
            return this.styleStack.size() - 1;
        }

        @Deprecated(message = "Use LinkAnnotation API for links instead", replaceWith = @ReplaceWith(expression = "pushLink(, start, end)", imports = {}))
        public final int pushUrlAnnotation(UrlAnnotation urlAnnotation) {
            MutableRange<? extends Annotation> mutableRange = new MutableRange<>(urlAnnotation, this.text.length(), 0, null, 12, null);
            this.styleStack.add(mutableRange);
            this.annotations.add(mutableRange);
            return this.styleStack.size() - 1;
        }

        public final int pushLink(LinkAnnotation link) {
            MutableRange<? extends Annotation> mutableRange = new MutableRange<>(link, this.text.length(), 0, null, 12, null);
            this.styleStack.add(mutableRange);
            this.annotations.add(mutableRange);
            return this.styleStack.size() - 1;
        }

        public final void pop() {
            if (this.styleStack.isEmpty()) {
                InlineClassHelperKt.throwIllegalStateException("Nothing to pop.");
            }
            List<MutableRange<? extends Object>> list = this.styleStack;
            list.remove(list.size() - 1).setEnd(this.text.length());
        }

        public final void pop(int index) {
            if (!(index < this.styleStack.size())) {
                InlineClassHelperKt.throwIllegalStateException(index + " should be less than " + this.styleStack.size());
            }
            while (this.styleStack.size() - 1 >= index) {
                pop();
            }
        }

        public final AnnotatedString toAnnotatedString() {
            String string = this.text.toString();
            List<MutableRange<? extends Annotation>> list = this.annotations;
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(list.get(i).toRange(this.text.length()));
            }
            return new AnnotatedString(string, arrayList);
        }

        public final void mapAnnotations$ui_text(Function1<? super Range<? extends Annotation>, ? extends Range<? extends Annotation>> transform) {
            int size = this.annotations.size();
            for (int i = 0; i < size; i++) {
                this.annotations.set(i, MutableRange.INSTANCE.fromRange(transform.invoke(MutableRange.toRange$default(this.annotations.get(i), 0, 1, null))));
            }
        }

        public final void flatMapAnnotations$ui_text(Function1<? super Range<? extends Annotation>, ? extends List<? extends Range<? extends Annotation>>> transform) {
            List<MutableRange<? extends Annotation>> list = this.annotations;
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                List<? extends Range<? extends Annotation>> listInvoke = transform.invoke(MutableRange.toRange$default(list.get(i), 0, 1, null));
                ArrayList arrayList2 = new ArrayList(listInvoke.size());
                int size2 = listInvoke.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    arrayList2.add(MutableRange.INSTANCE.fromRange(listInvoke.get(i2)));
                }
                CollectionsKt.addAll(arrayList, arrayList2);
            }
            this.annotations.clear();
            this.annotations.addAll(arrayList);
        }
    }

    /* JADX INFO: compiled from: AnnotatedString.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/text/AnnotatedString$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/AnnotatedString;", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Saver<AnnotatedString, ?> getSaver() {
            return AnnotatedString.Saver;
        }
    }
}
