package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.view.inputmethod.InputContentInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class InputContentInfoCompat {
    public final InputContentInfoCompatImpl mImpl;

    private static final class InputContentInfoCompatBaseImpl implements InputContentInfoCompatImpl {

        @NonNull
        public final Uri mContentUri;

        @NonNull
        public final ClipDescription mDescription;

        @Nullable
        public final Uri mLinkUri;

        public InputContentInfoCompatBaseImpl(@NonNull Uri contentUri, @NonNull ClipDescription description, @Nullable Uri linkUri) {
            this.mContentUri = contentUri;
            this.mDescription = description;
            this.mLinkUri = linkUri;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @NonNull
        public Uri getContentUri() {
            return this.mContentUri;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @NonNull
        public ClipDescription getDescription() {
            return this.mDescription;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @Nullable
        public Object getInputContentInfo() {
            return null;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @Nullable
        public Uri getLinkUri() {
            return this.mLinkUri;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void releasePermission() {
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void requestPermission() {
        }
    }

    private interface InputContentInfoCompatImpl {
        @NonNull
        Uri getContentUri();

        @NonNull
        ClipDescription getDescription();

        @Nullable
        Object getInputContentInfo();

        @Nullable
        Uri getLinkUri();

        void releasePermission();

        void requestPermission();
    }

    public InputContentInfoCompat(@NonNull Uri contentUri, @NonNull ClipDescription description, @Nullable Uri linkUri) {
        int i = Build.VERSION.SDK_INT;
        this.mImpl = new InputContentInfoCompatApi25Impl(contentUri, description, linkUri);
    }

    @Nullable
    public static InputContentInfoCompat wrap(@Nullable Object inputContentInfo) {
        if (inputContentInfo == null) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        return new InputContentInfoCompat(new InputContentInfoCompatApi25Impl(inputContentInfo));
    }

    @NonNull
    public Uri getContentUri() {
        return this.mImpl.getContentUri();
    }

    @NonNull
    public ClipDescription getDescription() {
        return this.mImpl.getDescription();
    }

    @Nullable
    public Uri getLinkUri() {
        return this.mImpl.getLinkUri();
    }

    public void releasePermission() {
        this.mImpl.releasePermission();
    }

    public void requestPermission() {
        this.mImpl.requestPermission();
    }

    @Nullable
    public Object unwrap() {
        return this.mImpl.getInputContentInfo();
    }

    @RequiresApi(25)
    private static final class InputContentInfoCompatApi25Impl implements InputContentInfoCompatImpl {

        @NonNull
        public final InputContentInfo mObject;

        public InputContentInfoCompatApi25Impl(@NonNull Object inputContentInfo) {
            this.mObject = (InputContentInfo) inputContentInfo;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @NonNull
        public Uri getContentUri() {
            return this.mObject.getContentUri();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @NonNull
        public ClipDescription getDescription() {
            return this.mObject.getDescription();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @Nullable
        public Object getInputContentInfo() {
            return this.mObject;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        @Nullable
        public Uri getLinkUri() {
            return this.mObject.getLinkUri();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void releasePermission() {
            this.mObject.releasePermission();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.InputContentInfoCompatImpl
        public void requestPermission() {
            this.mObject.requestPermission();
        }

        public InputContentInfoCompatApi25Impl(@NonNull Uri contentUri, @NonNull ClipDescription description, @Nullable Uri linkUri) {
            this.mObject = new InputContentInfo(contentUri, description, linkUri);
        }
    }

    public InputContentInfoCompat(@NonNull InputContentInfoCompatImpl impl) {
        this.mImpl = impl;
    }
}
