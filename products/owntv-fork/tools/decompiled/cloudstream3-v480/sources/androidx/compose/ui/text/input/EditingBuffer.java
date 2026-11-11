package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: EditingBuffer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0012\b\u0007\u0018\u0000 =2\u00020\u0001:\u0001=B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\tJ\r\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cJ\u0016\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\rH\u0080\u0002¢\u0006\u0002\b(J%\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¢\u0006\u0002\b/J%\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\bH\u0000¢\u0006\u0002\b/J\u001d\u00100\u001a\u00020,2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0000¢\u0006\u0002\b1J\u001d\u00102\u001a\u00020,2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0000¢\u0006\u0002\b3J\u001d\u00104\u001a\u00020,2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\rH\u0000¢\u0006\u0002\b5J\r\u00106\u001a\u00020,H\u0000¢\u0006\u0002\b7J\r\u00108\u001a\u00020,H\u0000¢\u0006\u0002\b9J\b\u0010:\u001a\u00020\bH\u0016J\r\u0010;\u001a\u00020\u0003H\u0000¢\u0006\u0002\b<R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001e\u0010\u0016\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u001e\u0010\u0018\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0004\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R$\u0010\"\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\r8@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u0014\u0010)\u001a\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u0010¨\u0006>"}, d2 = {"Landroidx/compose/ui/text/input/EditingBuffer;", "", "text", "Landroidx/compose/ui/text/AnnotatedString;", "selection", "Landroidx/compose/ui/text/TextRange;", "<init>", "(Landroidx/compose/ui/text/AnnotatedString;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "", "(Ljava/lang/String;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "gapBuffer", "Landroidx/compose/ui/text/input/PartialGapBuffer;", ES6Iterator.VALUE_PROPERTY, "", "selectionStart", "getSelectionStart$ui_text", "()I", "setSelectionStart", "(I)V", "selectionEnd", "getSelectionEnd$ui_text", "setSelectionEnd", "compositionStart", "getCompositionStart$ui_text", "compositionEnd", "getCompositionEnd$ui_text", "hasComposition", "", "hasComposition$ui_text", "composition", "getComposition-MzsxiRA$ui_text", "()Landroidx/compose/ui/text/TextRange;", "getSelection-d9O1mEE$ui_text", "()J", "cursor", "getCursor$ui_text", "setCursor$ui_text", "get", "", "index", "get$ui_text", "length", "getLength$ui_text", "replace", "", "start", TtmlNode.END, "replace$ui_text", "delete", "delete$ui_text", "setSelection", "setSelection$ui_text", "setComposition", "setComposition$ui_text", "cancelComposition", "cancelComposition$ui_text", "commitComposition", "commitComposition$ui_text", "toString", "toAnnotatedString", "toAnnotatedString$ui_text", "Companion", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class EditingBuffer {
    public static final int NOWHERE = -1;
    private int compositionEnd;
    private int compositionStart;
    private final PartialGapBuffer gapBuffer;
    private int selectionEnd;
    private int selectionStart;
    public static final int $stable = 8;

    public /* synthetic */ EditingBuffer(AnnotatedString annotatedString, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(annotatedString, j);
    }

    public /* synthetic */ EditingBuffer(String str, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j);
    }

    private EditingBuffer(AnnotatedString annotatedString, long j) {
        this.gapBuffer = new PartialGapBuffer(annotatedString.getText());
        this.selectionStart = TextRange.m6342getMinimpl(j);
        this.selectionEnd = TextRange.m6341getMaximpl(j);
        this.compositionStart = -1;
        this.compositionEnd = -1;
        int iM6342getMinimpl = TextRange.m6342getMinimpl(j);
        int iM6341getMaximpl = TextRange.m6341getMaximpl(j);
        if (iM6342getMinimpl < 0 || iM6342getMinimpl > annotatedString.length()) {
            throw new IndexOutOfBoundsException("start (" + iM6342getMinimpl + ") offset is outside of text region " + annotatedString.length());
        }
        if (iM6341getMaximpl < 0 || iM6341getMaximpl > annotatedString.length()) {
            throw new IndexOutOfBoundsException("end (" + iM6341getMaximpl + ") offset is outside of text region " + annotatedString.length());
        }
        if (iM6342getMinimpl <= iM6341getMaximpl) {
            return;
        }
        throw new IllegalArgumentException("Do not set reversed range: " + iM6342getMinimpl + " > " + iM6341getMaximpl);
    }

    /* JADX INFO: renamed from: getSelectionStart$ui_text, reason: from getter */
    public final int getSelectionStart() {
        return this.selectionStart;
    }

    private final void setSelectionStart(int i) {
        if (!(i >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("Cannot set selectionStart to a negative value: " + i);
        }
        this.selectionStart = i;
    }

    /* JADX INFO: renamed from: getSelectionEnd$ui_text, reason: from getter */
    public final int getSelectionEnd() {
        return this.selectionEnd;
    }

    private final void setSelectionEnd(int i) {
        if (!(i >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("Cannot set selectionEnd to a negative value: " + i);
        }
        this.selectionEnd = i;
    }

    /* JADX INFO: renamed from: getCompositionStart$ui_text, reason: from getter */
    public final int getCompositionStart() {
        return this.compositionStart;
    }

    /* JADX INFO: renamed from: getCompositionEnd$ui_text, reason: from getter */
    public final int getCompositionEnd() {
        return this.compositionEnd;
    }

    public final boolean hasComposition$ui_text() {
        return this.compositionStart != -1;
    }

    /* JADX INFO: renamed from: getComposition-MzsxiRA$ui_text, reason: not valid java name */
    public final TextRange m6507getCompositionMzsxiRA$ui_text() {
        if (hasComposition$ui_text()) {
            return TextRange.m6332boximpl(TextRangeKt.TextRange(this.compositionStart, this.compositionEnd));
        }
        return null;
    }

    /* JADX INFO: renamed from: getSelection-d9O1mEE$ui_text, reason: not valid java name */
    public final long m6508getSelectiond9O1mEE$ui_text() {
        return TextRangeKt.TextRange(this.selectionStart, this.selectionEnd);
    }

    public final int getCursor$ui_text() {
        int i = this.selectionStart;
        int i2 = this.selectionEnd;
        if (i == i2) {
            return i2;
        }
        return -1;
    }

    public final void setCursor$ui_text(int i) {
        setSelection$ui_text(i, i);
    }

    public final char get$ui_text(int index) {
        return this.gapBuffer.get(index);
    }

    public final int getLength$ui_text() {
        return this.gapBuffer.getLength();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private EditingBuffer(String str, long j) {
        this(new AnnotatedString(str, null, 2, 0 == true ? 1 : 0), j, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    public final void replace$ui_text(int start, int end, AnnotatedString text) {
        replace$ui_text(start, end, text.getText());
    }

    public final void replace$ui_text(int start, int end, String text) {
        if (start < 0 || start > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("start (" + start + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (end < 0 || end > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("end (" + end + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (start > end) {
            throw new IllegalArgumentException("Do not set reversed range: " + start + " > " + end);
        }
        this.gapBuffer.replace(start, end, text);
        setSelectionStart(text.length() + start);
        setSelectionEnd(start + text.length());
        this.compositionStart = -1;
        this.compositionEnd = -1;
    }

    public final void delete$ui_text(int start, int end) {
        long jTextRange = TextRangeKt.TextRange(start, end);
        this.gapBuffer.replace(start, end, "");
        long jM6509updateRangeAfterDeletepWDy79M = EditingBufferKt.m6509updateRangeAfterDeletepWDy79M(TextRangeKt.TextRange(this.selectionStart, this.selectionEnd), jTextRange);
        setSelectionStart(TextRange.m6342getMinimpl(jM6509updateRangeAfterDeletepWDy79M));
        setSelectionEnd(TextRange.m6341getMaximpl(jM6509updateRangeAfterDeletepWDy79M));
        if (hasComposition$ui_text()) {
            long jM6509updateRangeAfterDeletepWDy79M2 = EditingBufferKt.m6509updateRangeAfterDeletepWDy79M(TextRangeKt.TextRange(this.compositionStart, this.compositionEnd), jTextRange);
            if (TextRange.m6338getCollapsedimpl(jM6509updateRangeAfterDeletepWDy79M2)) {
                commitComposition$ui_text();
            } else {
                this.compositionStart = TextRange.m6342getMinimpl(jM6509updateRangeAfterDeletepWDy79M2);
                this.compositionEnd = TextRange.m6341getMaximpl(jM6509updateRangeAfterDeletepWDy79M2);
            }
        }
    }

    public final void setSelection$ui_text(int start, int end) {
        if (start < 0 || start > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("start (" + start + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (end < 0 || end > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("end (" + end + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (start > end) {
            throw new IllegalArgumentException("Do not set reversed range: " + start + " > " + end);
        }
        setSelectionStart(start);
        setSelectionEnd(end);
    }

    public final void setComposition$ui_text(int start, int end) {
        if (start < 0 || start > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("start (" + start + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (end < 0 || end > this.gapBuffer.getLength()) {
            throw new IndexOutOfBoundsException("end (" + end + ") offset is outside of text region " + this.gapBuffer.getLength());
        }
        if (start >= end) {
            throw new IllegalArgumentException("Do not set reversed or empty range: " + start + " > " + end);
        }
        this.compositionStart = start;
        this.compositionEnd = end;
    }

    public final void cancelComposition$ui_text() {
        replace$ui_text(this.compositionStart, this.compositionEnd, "");
        this.compositionStart = -1;
        this.compositionEnd = -1;
    }

    public final void commitComposition$ui_text() {
        this.compositionStart = -1;
        this.compositionEnd = -1;
    }

    public String toString() {
        return this.gapBuffer.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final AnnotatedString toAnnotatedString$ui_text() {
        return new AnnotatedString(toString(), null, 2, 0 == true ? 1 : 0);
    }
}
