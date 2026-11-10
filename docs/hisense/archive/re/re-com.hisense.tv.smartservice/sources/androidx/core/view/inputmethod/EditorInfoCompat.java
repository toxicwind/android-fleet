package androidx.core.view.inputmethod;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
public final class EditorInfoCompat {
    public static final String CONTENT_MIME_TYPES_INTEROP_KEY = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    public static final String CONTENT_MIME_TYPES_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    public static final String CONTENT_SELECTION_END_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END";
    public static final String CONTENT_SELECTION_HEAD_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD";
    public static final String CONTENT_SURROUNDING_TEXT_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT";
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int IME_FLAG_FORCE_ASCII = Integer.MIN_VALUE;
    public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;

    @VisibleForTesting
    public static final int MAX_INITIAL_SELECTION_LENGTH = 1024;

    @VisibleForTesting
    public static final int MEMORY_EFFICIENT_TEXT_LENGTH = 2048;

    @RequiresApi(30)
    private static class Impl30 {
        public static CharSequence getInitialSelectedText(@NonNull EditorInfo editorInfo, int flags) {
            return editorInfo.getInitialSelectedText(flags);
        }

        public static CharSequence getInitialTextAfterCursor(@NonNull EditorInfo editorInfo, int length, int flags) {
            return editorInfo.getInitialTextAfterCursor(length, flags);
        }

        public static CharSequence getInitialTextBeforeCursor(@NonNull EditorInfo editorInfo, int length, int flags) {
            return editorInfo.getInitialTextBeforeCursor(length, flags);
        }

        public static void setInitialSurroundingSubText(@NonNull EditorInfo editorInfo, CharSequence sourceText, int subTextStart) {
            editorInfo.setInitialSurroundingSubText(sourceText, subTextStart);
        }
    }

    @Deprecated
    public EditorInfoCompat() {
    }

    @NonNull
    public static String[] getContentMimeTypes(EditorInfo editorInfo) {
        int i = Build.VERSION.SDK_INT;
        String[] strArr = editorInfo.contentMimeTypes;
        return strArr != null ? strArr : EMPTY_STRING_ARRAY;
    }

    @Nullable
    public static CharSequence getInitialSelectedText(@NonNull EditorInfo editorInfo, int flags) {
        CharSequence charSequence;
        if (Build.VERSION.SDK_INT >= 30) {
            return editorInfo.getInitialSelectedText(flags);
        }
        if (editorInfo.extras == null) {
            return null;
        }
        int i = editorInfo.initialSelStart;
        int i2 = editorInfo.initialSelEnd;
        if (i > i2) {
            i = i2;
        }
        int i3 = editorInfo.initialSelStart;
        int i4 = editorInfo.initialSelEnd;
        if (i3 <= i4) {
            i3 = i4;
        }
        int i5 = editorInfo.extras.getInt(CONTENT_SELECTION_HEAD_KEY);
        int i6 = editorInfo.extras.getInt(CONTENT_SELECTION_END_KEY);
        int i7 = i3 - i;
        if (editorInfo.initialSelStart < 0 || editorInfo.initialSelEnd < 0 || i6 - i5 != i7 || (charSequence = editorInfo.extras.getCharSequence(CONTENT_SURROUNDING_TEXT_KEY)) == null) {
            return null;
        }
        return (flags & 1) != 0 ? charSequence.subSequence(i5, i6) : TextUtils.substring(charSequence, i5, i6);
    }

    @Nullable
    public static CharSequence getInitialTextAfterCursor(@NonNull EditorInfo editorInfo, int length, int flags) {
        CharSequence charSequence;
        if (Build.VERSION.SDK_INT >= 30) {
            return editorInfo.getInitialTextAfterCursor(length, flags);
        }
        Bundle bundle = editorInfo.extras;
        if (bundle == null || (charSequence = bundle.getCharSequence(CONTENT_SURROUNDING_TEXT_KEY)) == null) {
            return null;
        }
        int i = editorInfo.extras.getInt(CONTENT_SELECTION_END_KEY);
        int iMin = Math.min(length, charSequence.length() - i);
        return (flags & 1) != 0 ? charSequence.subSequence(i, iMin + i) : TextUtils.substring(charSequence, i, iMin + i);
    }

    @Nullable
    public static CharSequence getInitialTextBeforeCursor(@NonNull EditorInfo editorInfo, int length, int flags) {
        CharSequence charSequence;
        if (Build.VERSION.SDK_INT >= 30) {
            return editorInfo.getInitialTextBeforeCursor(length, flags);
        }
        Bundle bundle = editorInfo.extras;
        if (bundle == null || (charSequence = bundle.getCharSequence(CONTENT_SURROUNDING_TEXT_KEY)) == null) {
            return null;
        }
        int i = editorInfo.extras.getInt(CONTENT_SELECTION_HEAD_KEY);
        int iMin = Math.min(length, i);
        return (flags & 1) != 0 ? charSequence.subSequence(i - iMin, i) : TextUtils.substring(charSequence, i - iMin, i);
    }

    public static int getProtocol(EditorInfo editorInfo) {
        int i = Build.VERSION.SDK_INT;
        return 1;
    }

    public static boolean isCutOnSurrogate(CharSequence sourceText, int cutPosition, int policy) {
        if (policy == 0) {
            return Character.isLowSurrogate(sourceText.charAt(cutPosition));
        }
        if (policy != 1) {
            return false;
        }
        return Character.isHighSurrogate(sourceText.charAt(cutPosition));
    }

    public static boolean isPasswordInputType(int inputType) {
        int i = inputType & 4095;
        return i == 129 || i == 225 || i == 18;
    }

    public static void setContentMimeTypes(@NonNull EditorInfo editorInfo, @Nullable String[] contentMimeTypes) {
        int i = Build.VERSION.SDK_INT;
        editorInfo.contentMimeTypes = contentMimeTypes;
    }

    public static void setInitialSurroundingSubText(@NonNull EditorInfo editorInfo, @NonNull CharSequence subText, int subTextStart) {
        if (subText == null) {
            throw new NullPointerException();
        }
        if (Build.VERSION.SDK_INT >= 30) {
            editorInfo.setInitialSurroundingSubText(subText, subTextStart);
            return;
        }
        int i = editorInfo.initialSelStart;
        int i2 = editorInfo.initialSelEnd;
        int i3 = i > i2 ? i2 - subTextStart : i - subTextStart;
        int i4 = editorInfo.initialSelStart;
        int i5 = editorInfo.initialSelEnd;
        int i6 = i4 > i5 ? i4 - subTextStart : i5 - subTextStart;
        int length = subText.length();
        if (subTextStart < 0 || i3 < 0 || i6 > length) {
            setSurroundingText(editorInfo, null, 0, 0);
            return;
        }
        if (isPasswordInputType(editorInfo.inputType)) {
            setSurroundingText(editorInfo, null, 0, 0);
        } else if (length <= 2048) {
            setSurroundingText(editorInfo, subText, i3, i6);
        } else {
            trimLongSurroundingText(editorInfo, subText, i3, i6);
        }
    }

    public static void setInitialSurroundingText(@NonNull EditorInfo editorInfo, @NonNull CharSequence sourceText) {
        if (Build.VERSION.SDK_INT >= 30) {
            editorInfo.setInitialSurroundingSubText(sourceText, 0);
        } else {
            setInitialSurroundingSubText(editorInfo, sourceText, 0);
        }
    }

    public static void setSurroundingText(EditorInfo editorInfo, CharSequence surroundingText, int selectionHead, int selectionEnd) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence(CONTENT_SURROUNDING_TEXT_KEY, surroundingText != null ? new SpannableStringBuilder(surroundingText) : null);
        editorInfo.extras.putInt(CONTENT_SELECTION_HEAD_KEY, selectionHead);
        editorInfo.extras.putInt(CONTENT_SELECTION_END_KEY, selectionEnd);
    }

    public static void trimLongSurroundingText(EditorInfo editorInfo, CharSequence subText, int selStart, int selEnd) {
        int i = selEnd - selStart;
        int i2 = i > 1024 ? 0 : i;
        int i3 = 2048 - i2;
        int iMin = Math.min(subText.length() - selEnd, i3 - Math.min(selStart, (int) (((double) i3) * 0.8d)));
        int iMin2 = Math.min(selStart, i3 - iMin);
        int i4 = selStart - iMin2;
        if (isCutOnSurrogate(subText, i4, 0)) {
            i4++;
            iMin2--;
        }
        if (isCutOnSurrogate(subText, (selEnd + iMin) - 1, 1)) {
            iMin--;
        }
        CharSequence charSequenceConcat = i2 != i ? TextUtils.concat(subText.subSequence(i4, i4 + iMin2), subText.subSequence(selEnd, iMin + selEnd)) : subText.subSequence(i4, iMin2 + i2 + iMin + i4);
        int i5 = iMin2 + 0;
        setSurroundingText(editorInfo, charSequenceConcat, i5, i2 + i5);
    }
}
