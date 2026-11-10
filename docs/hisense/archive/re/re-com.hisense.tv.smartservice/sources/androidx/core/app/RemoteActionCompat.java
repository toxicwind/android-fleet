package androidx.core.app;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcelable;

/* JADX INFO: loaded from: classes.dex */
public final class RemoteActionCompat implements VersionedParcelable {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PendingIntent mActionIntent;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CharSequence mContentDescription;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean mEnabled;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public IconCompat mIcon;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean mShouldShowIcon;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CharSequence mTitle;

    public RemoteActionCompat(@NonNull IconCompat icon, @NonNull CharSequence title, @NonNull CharSequence contentDescription, @NonNull PendingIntent intent) {
        if (icon == null) {
            throw new NullPointerException();
        }
        this.mIcon = icon;
        if (title == null) {
            throw new NullPointerException();
        }
        this.mTitle = title;
        if (contentDescription == null) {
            throw new NullPointerException();
        }
        this.mContentDescription = contentDescription;
        if (intent == null) {
            throw new NullPointerException();
        }
        this.mActionIntent = intent;
        this.mEnabled = true;
        this.mShouldShowIcon = true;
    }

    @NonNull
    @RequiresApi(26)
    public static RemoteActionCompat createFromRemoteAction(@NonNull RemoteAction remoteAction) {
        if (remoteAction == null) {
            throw new NullPointerException();
        }
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat(IconCompat.createFromIcon(remoteAction.getIcon()), remoteAction.getTitle(), remoteAction.getContentDescription(), remoteAction.getActionIntent());
        remoteActionCompat.setEnabled(remoteAction.isEnabled());
        if (Build.VERSION.SDK_INT >= 28) {
            remoteActionCompat.setShouldShowIcon(remoteAction.shouldShowIcon());
        }
        return remoteActionCompat;
    }

    @NonNull
    public PendingIntent getActionIntent() {
        return this.mActionIntent;
    }

    @NonNull
    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    @NonNull
    public IconCompat getIcon() {
        return this.mIcon;
    }

    @NonNull
    public CharSequence getTitle() {
        return this.mTitle;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.mEnabled = enabled;
    }

    public void setShouldShowIcon(boolean shouldShowIcon) {
        this.mShouldShowIcon = shouldShowIcon;
    }

    public boolean shouldShowIcon() {
        return this.mShouldShowIcon;
    }

    @NonNull
    @RequiresApi(26)
    public RemoteAction toRemoteAction() {
        RemoteAction remoteAction = new RemoteAction(this.mIcon.toIcon(), this.mTitle, this.mContentDescription, this.mActionIntent);
        remoteAction.setEnabled(isEnabled());
        if (Build.VERSION.SDK_INT >= 28) {
            remoteAction.setShouldShowIcon(shouldShowIcon());
        }
        return remoteAction;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public RemoteActionCompat() {
    }

    public RemoteActionCompat(@NonNull RemoteActionCompat other) {
        if (other != null) {
            this.mIcon = other.mIcon;
            this.mTitle = other.mTitle;
            this.mContentDescription = other.mContentDescription;
            this.mActionIntent = other.mActionIntent;
            this.mEnabled = other.mEnabled;
            this.mShouldShowIcon = other.mShouldShowIcon;
            return;
        }
        throw new NullPointerException();
    }
}
