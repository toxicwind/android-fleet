package androidx.core.widget;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.Selection;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.view.ContentInfoCompat;
import androidx.core.view.OnReceiveContentListener;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public final class TextViewOnReceiveContentListener implements OnReceiveContentListener {
    public static final String LOG_TAG = "ReceiveContent";

    @RequiresApi(16)
    private static final class Api16Impl {
        public static CharSequence coerce(@NonNull Context context, @NonNull ClipData.Item item, int flags) {
            if ((flags & 1) == 0) {
                return item.coerceToStyledText(context);
            }
            CharSequence charSequenceCoerceToText = item.coerceToText(context);
            return charSequenceCoerceToText instanceof Spanned ? charSequenceCoerceToText.toString() : charSequenceCoerceToText;
        }
    }

    private static final class ApiImpl {
        public static CharSequence coerce(@NonNull Context context, @NonNull ClipData.Item item, int flags) {
            CharSequence charSequenceCoerceToText = item.coerceToText(context);
            return ((flags & 1) == 0 || !(charSequenceCoerceToText instanceof Spanned)) ? charSequenceCoerceToText : charSequenceCoerceToText.toString();
        }
    }

    public static CharSequence coerceToText(@NonNull Context context, @NonNull ClipData.Item item, int flags) {
        int i = Build.VERSION.SDK_INT;
        return Api16Impl.coerce(context, item, flags);
    }

    public static void replaceSelection(@NonNull Editable editable, @NonNull CharSequence replacement) {
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        int iMax = Math.max(0, Math.min(selectionStart, selectionEnd));
        int iMax2 = Math.max(0, Math.max(selectionStart, selectionEnd));
        Selection.setSelection(editable, iMax2);
        editable.replace(iMax, iMax2, replacement);
    }

    @Override // androidx.core.view.OnReceiveContentListener
    @Nullable
    public ContentInfoCompat onReceiveContent(@NonNull View view, @NonNull ContentInfoCompat payload) {
        if (Log.isLoggable(LOG_TAG, 3)) {
            a.b("onReceive: ", payload, LOG_TAG);
        }
        if (payload.getSource() == 2) {
            return payload;
        }
        ClipData clip = payload.getClip();
        int flags = payload.getFlags();
        TextView textView = (TextView) view;
        Editable editable = (Editable) textView.getText();
        Context context = textView.getContext();
        boolean z = false;
        for (int i = 0; i < clip.getItemCount(); i++) {
            CharSequence charSequenceCoerceToText = coerceToText(context, clip.getItemAt(i), flags);
            if (charSequenceCoerceToText != null) {
                if (z) {
                    editable.insert(Selection.getSelectionEnd(editable), "\n");
                    editable.insert(Selection.getSelectionEnd(editable), charSequenceCoerceToText);
                } else {
                    replaceSelection(editable, charSequenceCoerceToText);
                    z = true;
                }
            }
        }
        return null;
    }
}
