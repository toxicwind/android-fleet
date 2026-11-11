package androidx.compose.ui.text.input;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.AnnotatedStringKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: EditProcessor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0014\u0010\u0011\u001a\u00020\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013J\u0006\u0010\u0015\u001a\u00020\u0005J \u0010\u0016\u001a\u00020\u00172\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0014H\u0002J\f\u0010\u0019\u001a\u00020\u0017*\u00020\u0014H\u0002R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Landroidx/compose/ui/text/input/EditProcessor;", "", "<init>", "()V", ES6Iterator.VALUE_PROPERTY, "Landroidx/compose/ui/text/input/TextFieldValue;", "mBufferState", "getMBufferState$ui_text", "()Landroidx/compose/ui/text/input/TextFieldValue;", "Landroidx/compose/ui/text/input/EditingBuffer;", "mBuffer", "getMBuffer$ui_text", "()Landroidx/compose/ui/text/input/EditingBuffer;", "reset", "", "textInputSession", "Landroidx/compose/ui/text/input/TextInputSession;", "apply", "editCommands", "", "Landroidx/compose/ui/text/input/EditCommand;", "toTextFieldValue", "generateBatchErrorMessage", "", "failedCommand", "toStringForLog", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class EditProcessor {
    public static final int $stable = 8;
    private TextFieldValue mBufferState = new TextFieldValue(AnnotatedStringKt.emptyAnnotatedString(), TextRange.INSTANCE.m6349getZerod9O1mEE(), (TextRange) null, (DefaultConstructorMarker) null);
    private EditingBuffer mBuffer = new EditingBuffer(this.mBufferState.getAnnotatedString(), this.mBufferState.getSelection(), (DefaultConstructorMarker) null);

    /* JADX INFO: renamed from: getMBufferState$ui_text, reason: from getter */
    public final TextFieldValue getMBufferState() {
        return this.mBufferState;
    }

    /* JADX INFO: renamed from: getMBuffer$ui_text, reason: from getter */
    public final EditingBuffer getMBuffer() {
        return this.mBuffer;
    }

    public final void reset(TextFieldValue value, TextInputSession textInputSession) {
        boolean zAreEqual = Intrinsics.areEqual(value.getComposition(), this.mBuffer.m6507getCompositionMzsxiRA$ui_text());
        boolean z = true;
        boolean z2 = false;
        if (!Intrinsics.areEqual(this.mBufferState.getAnnotatedString().getText(), value.getAnnotatedString().getText())) {
            this.mBuffer = new EditingBuffer(value.getAnnotatedString(), value.getSelection(), (DefaultConstructorMarker) null);
        } else if (TextRange.m6337equalsimpl0(this.mBufferState.getSelection(), value.getSelection())) {
            z = false;
        } else {
            this.mBuffer.setSelection$ui_text(TextRange.m6342getMinimpl(value.getSelection()), TextRange.m6341getMaximpl(value.getSelection()));
            z2 = true;
            z = false;
        }
        if (value.getComposition() == null) {
            this.mBuffer.commitComposition$ui_text();
        } else if (!TextRange.m6338getCollapsedimpl(value.getComposition().getPackedValue())) {
            this.mBuffer.setComposition$ui_text(TextRange.m6342getMinimpl(value.getComposition().getPackedValue()), TextRange.m6341getMaximpl(value.getComposition().getPackedValue()));
        }
        if (z || (!z2 && !zAreEqual)) {
            this.mBuffer.commitComposition$ui_text();
            value = TextFieldValue.m6590copy3r_uNRQ$default(value, (AnnotatedString) null, 0L, (TextRange) null, 3, (Object) null);
        }
        TextFieldValue textFieldValue = this.mBufferState;
        this.mBufferState = value;
        if (textInputSession != null) {
            textInputSession.updateState(textFieldValue, value);
        }
    }

    public final TextFieldValue toTextFieldValue() {
        return this.mBufferState;
    }

    private final String generateBatchErrorMessage(List<? extends EditCommand> editCommands, final EditCommand failedCommand) {
        StringBuilder sb = new StringBuilder();
        sb.append("Error while applying EditCommand batch to buffer (length=" + this.mBuffer.getLength$ui_text() + ", composition=" + this.mBuffer.m6507getCompositionMzsxiRA$ui_text() + ", selection=" + ((Object) TextRange.m6347toStringimpl(this.mBuffer.m6508getSelectiond9O1mEE$ui_text())) + "):");
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        sb.append('\n');
        Intrinsics.checkNotNullExpressionValue(sb, "append(...)");
        CollectionsKt.joinTo(editCommands, sb, (60 & 2) != 0 ? ", " : "\n", (60 & 4) != 0 ? "" : null, (60 & 8) != 0 ? "" : null, (60 & 16) != 0 ? -1 : 0, (60 & 32) != 0 ? "..." : null, (60 & 64) != 0 ? null : new Function1() { // from class: androidx.compose.ui.text.input.EditProcessor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EditProcessor.generateBatchErrorMessage$lambda$4$lambda$3(failedCommand, this, (EditCommand) obj);
            }
        });
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    static final CharSequence generateBatchErrorMessage$lambda$4$lambda$3(EditCommand editCommand, EditProcessor editProcessor, EditCommand editCommand2) {
        return (editCommand == editCommand2 ? " > " : "   ") + editProcessor.toStringForLog(editCommand2);
    }

    private final String toStringForLog(EditCommand editCommand) {
        if (editCommand instanceof CommitTextCommand) {
            StringBuilder sb = new StringBuilder("CommitTextCommand(text.length=");
            CommitTextCommand commitTextCommand = (CommitTextCommand) editCommand;
            sb.append(commitTextCommand.getText().length());
            sb.append(", newCursorPosition=");
            sb.append(commitTextCommand.getNewCursorPosition());
            sb.append(')');
            return sb.toString();
        }
        if (editCommand instanceof SetComposingTextCommand) {
            StringBuilder sb2 = new StringBuilder("SetComposingTextCommand(text.length=");
            SetComposingTextCommand setComposingTextCommand = (SetComposingTextCommand) editCommand;
            sb2.append(setComposingTextCommand.getText().length());
            sb2.append(", newCursorPosition=");
            sb2.append(setComposingTextCommand.getNewCursorPosition());
            sb2.append(')');
            return sb2.toString();
        }
        if (editCommand instanceof SetComposingRegionCommand) {
            return ((SetComposingRegionCommand) editCommand).toString();
        }
        if (editCommand instanceof DeleteSurroundingTextCommand) {
            return ((DeleteSurroundingTextCommand) editCommand).toString();
        }
        if (editCommand instanceof DeleteSurroundingTextInCodePointsCommand) {
            return ((DeleteSurroundingTextInCodePointsCommand) editCommand).toString();
        }
        if (editCommand instanceof SetSelectionCommand) {
            return ((SetSelectionCommand) editCommand).toString();
        }
        if (editCommand instanceof FinishComposingTextCommand) {
            return ((FinishComposingTextCommand) editCommand).toString();
        }
        if (editCommand instanceof BackspaceCommand) {
            return ((BackspaceCommand) editCommand).toString();
        }
        if (editCommand instanceof MoveCursorCommand) {
            return ((MoveCursorCommand) editCommand).toString();
        }
        if (editCommand instanceof DeleteAllCommand) {
            return ((DeleteAllCommand) editCommand).toString();
        }
        StringBuilder sb3 = new StringBuilder("Unknown EditCommand: ");
        String simpleName = Reflection.getOrCreateKotlinClass(editCommand.getClass()).getSimpleName();
        if (simpleName == null) {
            simpleName = "{anonymous EditCommand}";
        }
        sb3.append(simpleName);
        return sb3.toString();
    }

    public final TextFieldValue apply(List<? extends EditCommand> editCommands) {
        EditCommand editCommand = null;
        try {
            int size = editCommands.size();
            int i = 0;
            EditCommand editCommand2 = null;
            while (i < size) {
                try {
                    EditCommand editCommand3 = editCommands.get(i);
                    try {
                        editCommand3.applyTo(this.mBuffer);
                        i++;
                        editCommand2 = editCommand3;
                    } catch (Exception e) {
                        e = e;
                        editCommand = editCommand3;
                        throw new RuntimeException(generateBatchErrorMessage(editCommands, editCommand), e);
                    }
                } catch (Exception e2) {
                    e = e2;
                    editCommand = editCommand2;
                }
            }
            AnnotatedString annotatedString$ui_text = this.mBuffer.toAnnotatedString$ui_text();
            long jM6508getSelectiond9O1mEE$ui_text = this.mBuffer.m6508getSelectiond9O1mEE$ui_text();
            TextRange textRangeM6332boximpl = TextRange.m6332boximpl(jM6508getSelectiond9O1mEE$ui_text);
            textRangeM6332boximpl.getPackedValue();
            TextRange textRange = TextRange.m6343getReversedimpl(this.mBufferState.getSelection()) ? null : textRangeM6332boximpl;
            TextFieldValue textFieldValue = new TextFieldValue(annotatedString$ui_text, textRange != null ? textRange.getPackedValue() : TextRangeKt.TextRange(TextRange.m6341getMaximpl(jM6508getSelectiond9O1mEE$ui_text), TextRange.m6342getMinimpl(jM6508getSelectiond9O1mEE$ui_text)), this.mBuffer.m6507getCompositionMzsxiRA$ui_text(), (DefaultConstructorMarker) null);
            this.mBufferState = textFieldValue;
            return textFieldValue;
        } catch (Exception e3) {
            e = e3;
        }
    }
}
