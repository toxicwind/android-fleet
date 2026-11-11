package androidx.compose.ui.platform;

import androidx.compose.ui.text.AnnotatedString;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: ClipboardManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated(message = "Use Clipboard instead, which supports suspend functions.", replaceWith = @ReplaceWith(expression = "Clipboard", imports = {"androidx.compose.ui.platform.Clipboard"}))
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\u0007\u001a\u00020\bH\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\nH\u0016R\u0018\u0010\r\u001a\u00060\u000ej\u0002`\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/platform/ClipboardManager;", "", "setText", "", "annotatedString", "Landroidx/compose/ui/text/AnnotatedString;", "getText", "hasText", "", "getClip", "Landroidx/compose/ui/platform/ClipEntry;", "setClip", "clipEntry", "nativeClipboard", "Landroid/content/ClipboardManager;", "Landroidx/compose/ui/platform/NativeClipboard;", "getNativeClipboard", "()Landroid/content/ClipboardManager;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface ClipboardManager {
    ClipEntry getClip();

    /* JADX INFO: renamed from: getNativeClipboard */
    android.content.ClipboardManager getClipboardManager();

    AnnotatedString getText();

    boolean hasText();

    void setClip(ClipEntry clipEntry);

    void setText(AnnotatedString annotatedString);

    /* JADX INFO: renamed from: androidx.compose.ui.platform.ClipboardManager$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ClipboardManager.kt */
    public final /* synthetic */ class CC {
        public static ClipEntry $default$getClip(ClipboardManager _this) {
            return null;
        }

        public static void $default$setClip(ClipboardManager _this, ClipEntry clipEntry) {
        }

        public static boolean $default$hasText(ClipboardManager _this) {
            AnnotatedString text = _this.getText();
            return text != null && text.length() > 0;
        }

        public static android.content.ClipboardManager $default$getNativeClipboard(ClipboardManager _this) {
            throw new UnsupportedOperationException("This platform does not offer a native Clipboard");
        }
    }
}
