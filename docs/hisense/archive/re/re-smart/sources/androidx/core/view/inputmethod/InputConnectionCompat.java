package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class InputConnectionCompat {
    public static final String COMMIT_CONTENT_ACTION = "androidx.core.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
    public static final String COMMIT_CONTENT_CONTENT_URI_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_URI";
    public static final String COMMIT_CONTENT_CONTENT_URI_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_URI";
    public static final String COMMIT_CONTENT_DESCRIPTION_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
    public static final String COMMIT_CONTENT_DESCRIPTION_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_DESCRIPTION";
    public static final String COMMIT_CONTENT_FLAGS_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
    public static final String COMMIT_CONTENT_FLAGS_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_FLAGS";
    public static final String COMMIT_CONTENT_INTEROP_ACTION = "android.support.v13.view.inputmethod.InputConnectionCompat.COMMIT_CONTENT";
    public static final String COMMIT_CONTENT_LINK_URI_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
    public static final String COMMIT_CONTENT_LINK_URI_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_LINK_URI";
    public static final String COMMIT_CONTENT_OPTS_INTEROP_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
    public static final String COMMIT_CONTENT_OPTS_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_OPTS";
    public static final String COMMIT_CONTENT_RESULT_INTEROP_RECEIVER_KEY = "android.support.v13.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
    public static final String COMMIT_CONTENT_RESULT_RECEIVER_KEY = "androidx.core.view.inputmethod.InputConnectionCompat.CONTENT_RESULT_RECEIVER";
    public static final int INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;

    /* JADX INFO: renamed from: androidx.core.view.inputmethod.InputConnectionCompat$2, reason: invalid class name */
    public class AnonymousClass2 extends InputConnectionWrapper {
        public final /* synthetic */ OnCommitContentListener val$listener;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(InputConnection target, boolean mutable, final OnCommitContentListener val$listener) {
            super(target, mutable);
            this.val$listener = val$listener;
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean performPrivateCommand(String action, Bundle data) {
            if (InputConnectionCompat.handlePerformPrivateCommand(action, data, this.val$listener)) {
                return true;
            }
            return super.performPrivateCommand(action, data);
        }
    }

    public interface OnCommitContentListener {
        boolean onCommitContent(InputContentInfoCompat inputContentInfo, int flags, Bundle opts);
    }

    @Deprecated
    public InputConnectionCompat() {
    }

    public static boolean commitContent(@NonNull InputConnection inputConnection, @NonNull EditorInfo editorInfo, @NonNull InputContentInfoCompat inputContentInfo, int flags, @Nullable Bundle opts) {
        boolean z;
        ClipDescription description = inputContentInfo.getDescription();
        String[] contentMimeTypes = EditorInfoCompat.getContentMimeTypes(editorInfo);
        int length = contentMimeTypes.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (description.hasMimeType(contentMimeTypes[i])) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            return false;
        }
        int i2 = Build.VERSION.SDK_INT;
        return inputConnection.commitContent((InputContentInfo) inputContentInfo.unwrap(), flags, opts);
    }

    @NonNull
    public static InputConnection createWrapper(@NonNull InputConnection inputConnection, @NonNull EditorInfo editorInfo, @NonNull final OnCommitContentListener onCommitContentListener) {
        if (inputConnection == null) {
            throw new IllegalArgumentException("inputConnection must be non-null");
        }
        if (editorInfo == null) {
            throw new IllegalArgumentException("editorInfo must be non-null");
        }
        if (onCommitContentListener == null) {
            throw new IllegalArgumentException("onCommitContentListener must be non-null");
        }
        int i = Build.VERSION.SDK_INT;
        return new InputConnectionWrapper(inputConnection, false) { // from class: androidx.core.view.inputmethod.InputConnectionCompat.1
            @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
            public boolean commitContent(InputContentInfo inputContentInfo, int flags, Bundle opts) {
                if (onCommitContentListener.onCommitContent(InputContentInfoCompat.wrap(inputContentInfo), flags, opts)) {
                    return true;
                }
                return super.commitContent(inputContentInfo, flags, opts);
            }
        };
    }

    public static boolean handlePerformPrivateCommand(@Nullable String action, @NonNull Bundle data, @NonNull OnCommitContentListener onCommitContentListener) throws Throwable {
        boolean z;
        ResultReceiver resultReceiver;
        if (data == null) {
            return false;
        }
        if (TextUtils.equals(COMMIT_CONTENT_ACTION, action)) {
            z = false;
        } else {
            if (!TextUtils.equals(COMMIT_CONTENT_INTEROP_ACTION, action)) {
                return false;
            }
            z = true;
        }
        try {
            resultReceiver = (ResultReceiver) data.getParcelable(z ? COMMIT_CONTENT_RESULT_INTEROP_RECEIVER_KEY : COMMIT_CONTENT_RESULT_RECEIVER_KEY);
            try {
                Uri uri = (Uri) data.getParcelable(z ? COMMIT_CONTENT_CONTENT_URI_INTEROP_KEY : COMMIT_CONTENT_CONTENT_URI_KEY);
                ClipDescription clipDescription = (ClipDescription) data.getParcelable(z ? COMMIT_CONTENT_DESCRIPTION_INTEROP_KEY : COMMIT_CONTENT_DESCRIPTION_KEY);
                boolean zOnCommitContent = (uri == null || clipDescription == null) ? false : onCommitContentListener.onCommitContent(new InputContentInfoCompat(uri, clipDescription, (Uri) data.getParcelable(z ? COMMIT_CONTENT_LINK_URI_INTEROP_KEY : COMMIT_CONTENT_LINK_URI_KEY)), data.getInt(z ? COMMIT_CONTENT_FLAGS_INTEROP_KEY : COMMIT_CONTENT_FLAGS_KEY), (Bundle) data.getParcelable(z ? COMMIT_CONTENT_OPTS_INTEROP_KEY : COMMIT_CONTENT_OPTS_KEY));
                if (resultReceiver != null) {
                    resultReceiver.send(zOnCommitContent ? 1 : 0, null);
                }
                return zOnCommitContent;
            } catch (Throwable th) {
                th = th;
                if (resultReceiver != null) {
                    resultReceiver.send(0, null);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            resultReceiver = null;
        }
    }
}
