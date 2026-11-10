package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.LocusId;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import androidx.annotation.ColorInt;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.content.LocusIdCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NotificationCompat {
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_LOCATION_SHARING = "location_sharing";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_MISSED_CALL = "missed_call";
    public static final String CATEGORY_NAVIGATION = "navigation";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_STOPWATCH = "stopwatch";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    public static final String CATEGORY_WORKOUT = "workout";

    @ColorInt
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_BIG_TEXT = "android.bigText";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_CHANNEL_GROUP_ID = "android.intent.extra.CHANNEL_GROUP_ID";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_CHANNEL_ID = "android.intent.extra.CHANNEL_ID";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_CHRONOMETER_COUNT_DOWN = "android.chronometerCountDown";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_COLORIZED = "android.colorized";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_COMPAT_TEMPLATE = "androidx.core.app.extra.COMPAT_TEMPLATE";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_HISTORIC_MESSAGES = "android.messages.historic";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_INFO_TEXT = "android.infoText";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_MESSAGES = "android.messages";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_NOTIFICATION_ID = "android.intent.extra.NOTIFICATION_ID";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_NOTIFICATION_TAG = "android.intent.extra.NOTIFICATION_TAG";

    @SuppressLint({"ActionValue"})
    @Deprecated
    public static final String EXTRA_PEOPLE = "android.people";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_PEOPLE_LIST = "android.people.list";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_PICTURE = "android.picture";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_PROGRESS = "android.progress";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SMALL_ICON = "android.icon";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SUB_TEXT = "android.subText";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TEMPLATE = "android.template";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TEXT = "android.text";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TEXT_LINES = "android.textLines";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TITLE = "android.title";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_BUBBLE = 4096;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;

    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final String GROUP_KEY_SILENT = "silent";

    @SuppressLint({"ActionValue"})
    public static final String INTENT_CATEGORY_NOTIFICATION_PREFERENCES = "android.intent.category.NOTIFICATION_PREFERENCES";
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    public static class Action {
        public static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
        public static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        public PendingIntent actionIntent;

        @Deprecated
        public int icon;
        public boolean mAllowGeneratedReplies;
        public final RemoteInput[] mDataOnlyRemoteInputs;
        public final Bundle mExtras;

        @Nullable
        public IconCompat mIcon;
        public final boolean mIsContextual;
        public final RemoteInput[] mRemoteInputs;
        public final int mSemanticAction;
        public boolean mShowsUserInterface;
        public CharSequence title;

        public static final class Builder {
            public boolean mAllowGeneratedReplies;
            public final Bundle mExtras;
            public final IconCompat mIcon;
            public final PendingIntent mIntent;
            public boolean mIsContextual;
            public ArrayList<RemoteInput> mRemoteInputs;
            public int mSemanticAction;
            public boolean mShowsUserInterface;
            public final CharSequence mTitle;

            public Builder(@Nullable IconCompat icon, @Nullable CharSequence title, @Nullable PendingIntent intent) {
                this(icon, title, intent, new Bundle(), null, true, 0, true, false);
            }

            private void checkContextualActionNullFields() {
                if (this.mIsContextual && this.mIntent == null) {
                    throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
                }
            }

            @NonNull
            @RequiresApi(19)
            @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
            public static Builder fromAndroidAction(@NonNull Notification.Action action) {
                int i = Build.VERSION.SDK_INT;
                Builder builder = action.getIcon() != null ? new Builder(IconCompat.createFromIcon(action.getIcon()), action.title, action.actionIntent) : new Builder(action.icon, action.title, action.actionIntent);
                int i2 = Build.VERSION.SDK_INT;
                android.app.RemoteInput[] remoteInputs = action.getRemoteInputs();
                if (remoteInputs != null && remoteInputs.length != 0) {
                    for (android.app.RemoteInput remoteInput : remoteInputs) {
                        builder.addRemoteInput(RemoteInput.fromPlatform(remoteInput));
                    }
                }
                int i3 = Build.VERSION.SDK_INT;
                builder.mAllowGeneratedReplies = action.getAllowGeneratedReplies();
                if (Build.VERSION.SDK_INT >= 28) {
                    builder.setSemanticAction(action.getSemanticAction());
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    builder.setContextual(action.isContextual());
                }
                return builder;
            }

            @NonNull
            public Builder addExtras(@Nullable Bundle extras) {
                if (extras != null) {
                    this.mExtras.putAll(extras);
                }
                return this;
            }

            @NonNull
            public Builder addRemoteInput(@Nullable RemoteInput remoteInput) {
                if (this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList<>();
                }
                if (remoteInput != null) {
                    this.mRemoteInputs.add(remoteInput);
                }
                return this;
            }

            @NonNull
            public Action build() {
                checkContextualActionNullFields();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList<RemoteInput> arrayList3 = this.mRemoteInputs;
                if (arrayList3 != null) {
                    for (RemoteInput remoteInput : arrayList3) {
                        if (remoteInput.isDataOnly()) {
                            arrayList.add(remoteInput);
                        } else {
                            arrayList2.add(remoteInput);
                        }
                    }
                }
                RemoteInput[] remoteInputArr = arrayList.isEmpty() ? null : (RemoteInput[]) arrayList.toArray(new RemoteInput[arrayList.size()]);
                return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayList2.isEmpty() ? null : (RemoteInput[]) arrayList2.toArray(new RemoteInput[arrayList2.size()]), remoteInputArr, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface, this.mIsContextual);
            }

            @NonNull
            public Builder extend(@NonNull Extender extender) {
                extender.extend(this);
                return this;
            }

            @NonNull
            public Bundle getExtras() {
                return this.mExtras;
            }

            @NonNull
            public Builder setAllowGeneratedReplies(boolean allowGeneratedReplies) {
                this.mAllowGeneratedReplies = allowGeneratedReplies;
                return this;
            }

            @NonNull
            public Builder setContextual(boolean isContextual) {
                this.mIsContextual = isContextual;
                return this;
            }

            @NonNull
            public Builder setSemanticAction(int semanticAction) {
                this.mSemanticAction = semanticAction;
                return this;
            }

            @NonNull
            public Builder setShowsUserInterface(boolean showsUserInterface) {
                this.mShowsUserInterface = showsUserInterface;
                return this;
            }

            public Builder(int icon, @Nullable CharSequence title, @Nullable PendingIntent intent) {
                this(icon != 0 ? IconCompat.createWithResource(null, "", icon) : null, title, intent, new Bundle(), null, true, 0, true, false);
            }

            public Builder(@NonNull Action action) {
                this(action.getIconCompat(), action.title, action.actionIntent, new Bundle(action.mExtras), action.getRemoteInputs(), action.getAllowGeneratedReplies(), action.getSemanticAction(), action.mShowsUserInterface, action.isContextual());
            }

            public Builder(@Nullable IconCompat icon, @Nullable CharSequence title, @Nullable PendingIntent intent, @NonNull Bundle extras, @Nullable RemoteInput[] remoteInputs, boolean allowGeneratedReplies, int semanticAction, boolean showsUserInterface, boolean isContextual) {
                this.mAllowGeneratedReplies = true;
                this.mShowsUserInterface = true;
                this.mIcon = icon;
                this.mTitle = Builder.limitCharSequenceLength(title);
                this.mIntent = intent;
                this.mExtras = extras;
                this.mRemoteInputs = remoteInputs == null ? null : new ArrayList<>(Arrays.asList(remoteInputs));
                this.mAllowGeneratedReplies = allowGeneratedReplies;
                this.mSemanticAction = semanticAction;
                this.mShowsUserInterface = showsUserInterface;
                this.mIsContextual = isContextual;
            }
        }

        public interface Extender {
            @NonNull
            Builder extend(@NonNull Builder builder);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }

        public static final class WearableExtender implements Extender {
            public static final int DEFAULT_FLAGS = 1;
            public static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            public static final int FLAG_AVAILABLE_OFFLINE = 1;
            public static final int FLAG_HINT_DISPLAY_INLINE = 4;
            public static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            public static final String KEY_CANCEL_LABEL = "cancelLabel";
            public static final String KEY_CONFIRM_LABEL = "confirmLabel";
            public static final String KEY_FLAGS = "flags";
            public static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            public CharSequence mCancelLabel;
            public CharSequence mConfirmLabel;
            public int mFlags;
            public CharSequence mInProgressLabel;

            public WearableExtender() {
                this.mFlags = 1;
            }

            private void setFlag(int mask, boolean value) {
                if (value) {
                    this.mFlags = mask | this.mFlags;
                } else {
                    this.mFlags = (~mask) & this.mFlags;
                }
            }

            @Override // androidx.core.app.NotificationCompat.Action.Extender
            @NonNull
            public Builder extend(@NonNull Builder builder) {
                Bundle bundle = new Bundle();
                int i = this.mFlags;
                if (i != 1) {
                    bundle.putInt("flags", i);
                }
                CharSequence charSequence = this.mInProgressLabel;
                if (charSequence != null) {
                    bundle.putCharSequence(KEY_IN_PROGRESS_LABEL, charSequence);
                }
                CharSequence charSequence2 = this.mConfirmLabel;
                if (charSequence2 != null) {
                    bundle.putCharSequence(KEY_CONFIRM_LABEL, charSequence2);
                }
                CharSequence charSequence3 = this.mCancelLabel;
                if (charSequence3 != null) {
                    bundle.putCharSequence(KEY_CANCEL_LABEL, charSequence3);
                }
                builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
                return builder;
            }

            @Nullable
            @Deprecated
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            @Nullable
            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            public boolean getHintDisplayActionInline() {
                return (this.mFlags & 4) != 0;
            }

            public boolean getHintLaunchesActivity() {
                return (this.mFlags & 2) != 0;
            }

            @Nullable
            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            public boolean isAvailableOffline() {
                return (this.mFlags & 1) != 0;
            }

            @NonNull
            public WearableExtender setAvailableOffline(boolean availableOffline) {
                setFlag(1, availableOffline);
                return this;
            }

            @NonNull
            @Deprecated
            public WearableExtender setCancelLabel(@Nullable CharSequence label) {
                this.mCancelLabel = label;
                return this;
            }

            @NonNull
            @Deprecated
            public WearableExtender setConfirmLabel(@Nullable CharSequence label) {
                this.mConfirmLabel = label;
                return this;
            }

            @NonNull
            public WearableExtender setHintDisplayActionInline(boolean hintDisplayInline) {
                setFlag(4, hintDisplayInline);
                return this;
            }

            @NonNull
            public WearableExtender setHintLaunchesActivity(boolean hintLaunchesActivity) {
                setFlag(2, hintLaunchesActivity);
                return this;
            }

            @NonNull
            @Deprecated
            public WearableExtender setInProgressLabel(@Nullable CharSequence label) {
                this.mInProgressLabel = label;
                return this;
            }

            @NonNull
            /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
            public WearableExtender m2clone() {
                WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.mFlags = this.mFlags;
                wearableExtender.mInProgressLabel = this.mInProgressLabel;
                wearableExtender.mConfirmLabel = this.mConfirmLabel;
                wearableExtender.mCancelLabel = this.mCancelLabel;
                return wearableExtender;
            }

            public WearableExtender(@NonNull Action action) {
                this.mFlags = 1;
                Bundle bundle = action.getExtras().getBundle("android.wearable.EXTENSIONS");
                if (bundle != null) {
                    this.mFlags = bundle.getInt("flags", 1);
                    this.mInProgressLabel = bundle.getCharSequence(KEY_IN_PROGRESS_LABEL);
                    this.mConfirmLabel = bundle.getCharSequence(KEY_CONFIRM_LABEL);
                    this.mCancelLabel = bundle.getCharSequence(KEY_CANCEL_LABEL);
                }
            }
        }

        public Action(int icon, @Nullable CharSequence title, @Nullable PendingIntent intent) {
            this(icon != 0 ? IconCompat.createWithResource(null, "", icon) : null, title, intent);
        }

        @Nullable
        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        @Nullable
        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.mDataOnlyRemoteInputs;
        }

        @NonNull
        public Bundle getExtras() {
            return this.mExtras;
        }

        @Deprecated
        public int getIcon() {
            return this.icon;
        }

        @Nullable
        public IconCompat getIconCompat() {
            int i;
            if (this.mIcon == null && (i = this.icon) != 0) {
                this.mIcon = IconCompat.createWithResource(null, "", i);
            }
            return this.mIcon;
        }

        @Nullable
        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public boolean getShowsUserInterface() {
            return this.mShowsUserInterface;
        }

        @Nullable
        public CharSequence getTitle() {
            return this.title;
        }

        public boolean isContextual() {
            return this.mIsContextual;
        }

        public Action(@Nullable IconCompat icon, @Nullable CharSequence title, @Nullable PendingIntent intent) {
            this(icon, title, intent, new Bundle(), (RemoteInput[]) null, (RemoteInput[]) null, true, 0, true, false);
        }

        public Action(int icon, @Nullable CharSequence title, @Nullable PendingIntent intent, @Nullable Bundle extras, @Nullable RemoteInput[] remoteInputs, @Nullable RemoteInput[] dataOnlyRemoteInputs, boolean allowGeneratedReplies, int semanticAction, boolean showsUserInterface, boolean isContextual) {
            this(icon != 0 ? IconCompat.createWithResource(null, "", icon) : null, title, intent, extras, remoteInputs, dataOnlyRemoteInputs, allowGeneratedReplies, semanticAction, showsUserInterface, isContextual);
        }

        public Action(@Nullable IconCompat icon, @Nullable CharSequence title, @Nullable PendingIntent intent, @Nullable Bundle extras, @Nullable RemoteInput[] remoteInputs, @Nullable RemoteInput[] dataOnlyRemoteInputs, boolean allowGeneratedReplies, int semanticAction, boolean showsUserInterface, boolean isContextual) {
            this.mShowsUserInterface = true;
            this.mIcon = icon;
            if (icon != null && icon.getType() == 2) {
                this.icon = icon.getResId();
            }
            this.title = Builder.limitCharSequenceLength(title);
            this.actionIntent = intent;
            this.mExtras = extras == null ? new Bundle() : extras;
            this.mRemoteInputs = remoteInputs;
            this.mDataOnlyRemoteInputs = dataOnlyRemoteInputs;
            this.mAllowGeneratedReplies = allowGeneratedReplies;
            this.mSemanticAction = semanticAction;
            this.mShowsUserInterface = showsUserInterface;
            this.mIsContextual = isContextual;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface BadgeIconType {
    }

    public static class BigPictureStyle extends Style {
        public static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$BigPictureStyle";
        public IconCompat mBigLargeIcon;
        public boolean mBigLargeIconSet;
        public Bitmap mPicture;

        @RequiresApi(16)
        private static class Api16Impl {
            @RequiresApi(16)
            public static void setBigLargeIcon(Notification.BigPictureStyle style, Bitmap icon) {
                style.bigLargeIcon(icon);
            }

            @RequiresApi(16)
            public static void setSummaryText(Notification.BigPictureStyle style, CharSequence text) {
                style.setSummaryText(text);
            }
        }

        @RequiresApi(23)
        private static class Api23Impl {
            @RequiresApi(23)
            public static void setBigLargeIcon(Notification.BigPictureStyle style, Icon icon) {
                style.bigLargeIcon(icon);
            }
        }

        public BigPictureStyle() {
        }

        @Nullable
        public static IconCompat asIconCompat(@Nullable Parcelable bitmapOrIcon) {
            if (bitmapOrIcon == null) {
                return null;
            }
            int i = Build.VERSION.SDK_INT;
            if (bitmapOrIcon instanceof Icon) {
                return IconCompat.createFromIcon((Icon) bitmapOrIcon);
            }
            if (bitmapOrIcon instanceof Bitmap) {
                return IconCompat.createWithBitmap((Bitmap) bitmapOrIcon);
            }
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            Notification.BigPictureStyle bigPictureStyleBigPicture = new Notification.BigPictureStyle(builder.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
            if (this.mBigLargeIconSet) {
                if (this.mBigLargeIcon == null) {
                    bigPictureStyleBigPicture.bigLargeIcon((Bitmap) null);
                } else {
                    int i2 = Build.VERSION.SDK_INT;
                    bigPictureStyleBigPicture.bigLargeIcon(this.mBigLargeIcon.toIcon(builder instanceof NotificationCompatBuilder ? ((NotificationCompatBuilder) builder).getContext() : null));
                }
            }
            if (this.mSummaryTextSet) {
                bigPictureStyleBigPicture.setSummaryText(this.mSummaryText);
            }
        }

        @NonNull
        public BigPictureStyle bigLargeIcon(@Nullable Bitmap b2) {
            this.mBigLargeIcon = b2 == null ? null : IconCompat.createWithBitmap(b2);
            this.mBigLargeIconSet = true;
            return this;
        }

        @NonNull
        public BigPictureStyle bigPicture(@Nullable Bitmap b2) {
            this.mPicture = b2;
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void clearCompatExtraKeys(@NonNull Bundle extras) {
            super.clearCompatExtraKeys(extras);
            extras.remove(NotificationCompat.EXTRA_LARGE_ICON_BIG);
            extras.remove(NotificationCompat.EXTRA_PICTURE);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void restoreFromCompatExtras(@NonNull Bundle extras) {
            super.restoreFromCompatExtras(extras);
            if (extras.containsKey(NotificationCompat.EXTRA_LARGE_ICON_BIG)) {
                this.mBigLargeIcon = asIconCompat(extras.getParcelable(NotificationCompat.EXTRA_LARGE_ICON_BIG));
                this.mBigLargeIconSet = true;
            }
            this.mPicture = (Bitmap) extras.getParcelable(NotificationCompat.EXTRA_PICTURE);
        }

        @NonNull
        public BigPictureStyle setBigContentTitle(@Nullable CharSequence title) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(title);
            return this;
        }

        @NonNull
        public BigPictureStyle setSummaryText(@Nullable CharSequence cs) {
            this.mSummaryText = Builder.limitCharSequenceLength(cs);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigPictureStyle(@Nullable Builder builder) {
            setBuilder(builder);
        }
    }

    public static class BigTextStyle extends Style {
        public static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$BigTextStyle";
        public CharSequence mBigText;

        public BigTextStyle() {
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void addCompatExtras(@NonNull Bundle extras) {
            super.addCompatExtras(extras);
            int i = Build.VERSION.SDK_INT;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            Notification.BigTextStyle bigTextStyleBigText = new Notification.BigTextStyle(builder.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
            if (this.mSummaryTextSet) {
                bigTextStyleBigText.setSummaryText(this.mSummaryText);
            }
        }

        @NonNull
        public BigTextStyle bigText(@Nullable CharSequence cs) {
            this.mBigText = Builder.limitCharSequenceLength(cs);
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void clearCompatExtraKeys(@NonNull Bundle extras) {
            super.clearCompatExtraKeys(extras);
            extras.remove(NotificationCompat.EXTRA_BIG_TEXT);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void restoreFromCompatExtras(@NonNull Bundle extras) {
            super.restoreFromCompatExtras(extras);
            this.mBigText = extras.getCharSequence(NotificationCompat.EXTRA_BIG_TEXT);
        }

        @NonNull
        public BigTextStyle setBigContentTitle(@Nullable CharSequence title) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(title);
            return this;
        }

        @NonNull
        public BigTextStyle setSummaryText(@Nullable CharSequence cs) {
            this.mSummaryText = Builder.limitCharSequenceLength(cs);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigTextStyle(@Nullable Builder builder) {
            setBuilder(builder);
        }
    }

    public static final class BubbleMetadata {
        public static final int FLAG_AUTO_EXPAND_BUBBLE = 1;
        public static final int FLAG_SUPPRESS_NOTIFICATION = 2;
        public PendingIntent mDeleteIntent;
        public int mDesiredHeight;

        @DimenRes
        public int mDesiredHeightResId;
        public int mFlags;
        public IconCompat mIcon;
        public PendingIntent mPendingIntent;
        public String mShortcutId;

        @RequiresApi(29)
        private static class Api29Impl {
            @Nullable
            @RequiresApi(29)
            public static BubbleMetadata fromPlatform(@Nullable Notification.BubbleMetadata platformMetadata) {
                if (platformMetadata == null || platformMetadata.getIntent() == null) {
                    return null;
                }
                Builder suppressNotification = new Builder(platformMetadata.getIntent(), IconCompat.createFromIcon(platformMetadata.getIcon())).setAutoExpandBubble(platformMetadata.getAutoExpandBubble()).setDeleteIntent(platformMetadata.getDeleteIntent()).setSuppressNotification(platformMetadata.isNotificationSuppressed());
                if (platformMetadata.getDesiredHeight() != 0) {
                    suppressNotification.setDesiredHeight(platformMetadata.getDesiredHeight());
                }
                if (platformMetadata.getDesiredHeightResId() != 0) {
                    suppressNotification.setDesiredHeightResId(platformMetadata.getDesiredHeightResId());
                }
                return suppressNotification.build();
            }

            @Nullable
            @RequiresApi(29)
            public static Notification.BubbleMetadata toPlatform(@Nullable BubbleMetadata compatMetadata) {
                if (compatMetadata == null || compatMetadata.getIntent() == null) {
                    return null;
                }
                Notification.BubbleMetadata.Builder suppressNotification = new Notification.BubbleMetadata.Builder().setIcon(compatMetadata.getIcon().toIcon()).setIntent(compatMetadata.getIntent()).setDeleteIntent(compatMetadata.getDeleteIntent()).setAutoExpandBubble(compatMetadata.getAutoExpandBubble()).setSuppressNotification(compatMetadata.isNotificationSuppressed());
                if (compatMetadata.getDesiredHeight() != 0) {
                    suppressNotification.setDesiredHeight(compatMetadata.getDesiredHeight());
                }
                if (compatMetadata.getDesiredHeightResId() != 0) {
                    suppressNotification.setDesiredHeightResId(compatMetadata.getDesiredHeightResId());
                }
                return suppressNotification.build();
            }
        }

        @RequiresApi(30)
        private static class Api30Impl {
            @Nullable
            @RequiresApi(30)
            public static BubbleMetadata fromPlatform(@Nullable Notification.BubbleMetadata platformMetadata) {
                if (platformMetadata == null) {
                    return null;
                }
                Builder builder = platformMetadata.getShortcutId() != null ? new Builder(platformMetadata.getShortcutId()) : new Builder(platformMetadata.getIntent(), IconCompat.createFromIcon(platformMetadata.getIcon()));
                builder.setAutoExpandBubble(platformMetadata.getAutoExpandBubble()).setDeleteIntent(platformMetadata.getDeleteIntent()).setSuppressNotification(platformMetadata.isNotificationSuppressed());
                if (platformMetadata.getDesiredHeight() != 0) {
                    builder.setDesiredHeight(platformMetadata.getDesiredHeight());
                }
                if (platformMetadata.getDesiredHeightResId() != 0) {
                    builder.setDesiredHeightResId(platformMetadata.getDesiredHeightResId());
                }
                return builder.build();
            }

            @Nullable
            @RequiresApi(30)
            public static Notification.BubbleMetadata toPlatform(@Nullable BubbleMetadata compatMetadata) {
                if (compatMetadata == null) {
                    return null;
                }
                Notification.BubbleMetadata.Builder builder = compatMetadata.getShortcutId() != null ? new Notification.BubbleMetadata.Builder(compatMetadata.getShortcutId()) : new Notification.BubbleMetadata.Builder(compatMetadata.getIntent(), compatMetadata.getIcon().toIcon());
                builder.setDeleteIntent(compatMetadata.getDeleteIntent()).setAutoExpandBubble(compatMetadata.getAutoExpandBubble()).setSuppressNotification(compatMetadata.isNotificationSuppressed());
                if (compatMetadata.getDesiredHeight() != 0) {
                    builder.setDesiredHeight(compatMetadata.getDesiredHeight());
                }
                if (compatMetadata.getDesiredHeightResId() != 0) {
                    builder.setDesiredHeightResId(compatMetadata.getDesiredHeightResId());
                }
                return builder.build();
            }
        }

        public static final class Builder {
            public PendingIntent mDeleteIntent;
            public int mDesiredHeight;

            @DimenRes
            public int mDesiredHeightResId;
            public int mFlags;
            public IconCompat mIcon;
            public PendingIntent mPendingIntent;
            public String mShortcutId;

            @Deprecated
            public Builder() {
            }

            @NonNull
            private Builder setFlag(int mask, boolean value) {
                if (value) {
                    this.mFlags = mask | this.mFlags;
                } else {
                    this.mFlags = (~mask) & this.mFlags;
                }
                return this;
            }

            @NonNull
            @SuppressLint({"SyntheticAccessor"})
            public BubbleMetadata build() {
                if (this.mShortcutId == null && this.mPendingIntent == null) {
                    throw new NullPointerException("Must supply pending intent or shortcut to bubble");
                }
                if (this.mShortcutId == null && this.mIcon == null) {
                    throw new NullPointerException("Must supply an icon or shortcut for the bubble");
                }
                BubbleMetadata bubbleMetadata = new BubbleMetadata(this.mPendingIntent, this.mDeleteIntent, this.mIcon, this.mDesiredHeight, this.mDesiredHeightResId, this.mFlags, this.mShortcutId);
                bubbleMetadata.setFlags(this.mFlags);
                return bubbleMetadata;
            }

            @NonNull
            public Builder setAutoExpandBubble(boolean shouldExpand) {
                setFlag(1, shouldExpand);
                return this;
            }

            @NonNull
            public Builder setDeleteIntent(@Nullable PendingIntent deleteIntent) {
                this.mDeleteIntent = deleteIntent;
                return this;
            }

            @NonNull
            public Builder setDesiredHeight(@Dimension(unit = 0) int height) {
                this.mDesiredHeight = Math.max(height, 0);
                this.mDesiredHeightResId = 0;
                return this;
            }

            @NonNull
            public Builder setDesiredHeightResId(@DimenRes int heightResId) {
                this.mDesiredHeightResId = heightResId;
                this.mDesiredHeight = 0;
                return this;
            }

            @NonNull
            public Builder setIcon(@NonNull IconCompat icon) {
                if (this.mShortcutId != null) {
                    throw new IllegalStateException("Created as a shortcut bubble, cannot set an Icon. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                }
                if (icon == null) {
                    throw new NullPointerException("Bubbles require non-null icon");
                }
                this.mIcon = icon;
                return this;
            }

            @NonNull
            public Builder setIntent(@NonNull PendingIntent intent) {
                if (this.mShortcutId != null) {
                    throw new IllegalStateException("Created as a shortcut bubble, cannot set a PendingIntent. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                }
                if (intent == null) {
                    throw new NullPointerException("Bubble requires non-null pending intent");
                }
                this.mPendingIntent = intent;
                return this;
            }

            @NonNull
            public Builder setSuppressNotification(boolean shouldSuppressNotif) {
                setFlag(2, shouldSuppressNotif);
                return this;
            }

            @RequiresApi(30)
            public Builder(@NonNull String shortcutId) {
                if (TextUtils.isEmpty(shortcutId)) {
                    throw new NullPointerException("Bubble requires a non-null shortcut id");
                }
                this.mShortcutId = shortcutId;
            }

            public Builder(@NonNull PendingIntent intent, @NonNull IconCompat icon) {
                if (intent == null) {
                    throw new NullPointerException("Bubble requires non-null pending intent");
                }
                if (icon != null) {
                    this.mPendingIntent = intent;
                    this.mIcon = icon;
                    return;
                }
                throw new NullPointerException("Bubbles require non-null icon");
            }
        }

        @Nullable
        public static BubbleMetadata fromPlatform(@Nullable Notification.BubbleMetadata platformMetadata) {
            if (platformMetadata == null) {
                return null;
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                return Api30Impl.fromPlatform(platformMetadata);
            }
            if (i == 29) {
                return Api29Impl.fromPlatform(platformMetadata);
            }
            return null;
        }

        @Nullable
        public static Notification.BubbleMetadata toPlatform(@Nullable BubbleMetadata compatMetadata) {
            if (compatMetadata == null) {
                return null;
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 30) {
                return Api30Impl.toPlatform(compatMetadata);
            }
            if (i == 29) {
                return Api29Impl.toPlatform(compatMetadata);
            }
            return null;
        }

        public boolean getAutoExpandBubble() {
            return (this.mFlags & 1) != 0;
        }

        @Nullable
        public PendingIntent getDeleteIntent() {
            return this.mDeleteIntent;
        }

        @Dimension(unit = 0)
        public int getDesiredHeight() {
            return this.mDesiredHeight;
        }

        @DimenRes
        public int getDesiredHeightResId() {
            return this.mDesiredHeightResId;
        }

        @Nullable
        @SuppressLint({"InvalidNullConversion"})
        public IconCompat getIcon() {
            return this.mIcon;
        }

        @Nullable
        @SuppressLint({"InvalidNullConversion"})
        public PendingIntent getIntent() {
            return this.mPendingIntent;
        }

        @Nullable
        public String getShortcutId() {
            return this.mShortcutId;
        }

        public boolean isNotificationSuppressed() {
            return (this.mFlags & 2) != 0;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void setFlags(int flags) {
            this.mFlags = flags;
        }

        public BubbleMetadata(@Nullable PendingIntent expandIntent, @Nullable PendingIntent deleteIntent, @Nullable IconCompat icon, int height, @DimenRes int heightResId, int flags, @Nullable String shortcutId) {
            this.mPendingIntent = expandIntent;
            this.mIcon = icon;
            this.mDesiredHeight = height;
            this.mDesiredHeightResId = heightResId;
            this.mDeleteIntent = deleteIntent;
            this.mFlags = flags;
            this.mShortcutId = shortcutId;
        }
    }

    public static class Builder {
        public static final int MAX_CHARSEQUENCE_LENGTH = 5120;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public ArrayList<Action> mActions;
        public boolean mAllowSystemGeneratedContextualActions;
        public int mBadgeIcon;
        public RemoteViews mBigContentView;
        public BubbleMetadata mBubbleMetadata;
        public String mCategory;
        public String mChannelId;
        public boolean mChronometerCountDown;
        public int mColor;
        public boolean mColorized;
        public boolean mColorizedSet;
        public CharSequence mContentInfo;
        public PendingIntent mContentIntent;
        public CharSequence mContentText;
        public CharSequence mContentTitle;
        public RemoteViews mContentView;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Context mContext;
        public Bundle mExtras;
        public PendingIntent mFullScreenIntent;
        public int mGroupAlertBehavior;
        public String mGroupKey;
        public boolean mGroupSummary;
        public RemoteViews mHeadsUpContentView;
        public ArrayList<Action> mInvisibleActions;
        public Bitmap mLargeIcon;
        public boolean mLocalOnly;
        public LocusIdCompat mLocusId;
        public Notification mNotification;
        public int mNumber;

        @Deprecated
        public ArrayList<String> mPeople;

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public ArrayList<Person> mPersonList;
        public int mPriority;
        public int mProgress;
        public boolean mProgressIndeterminate;
        public int mProgressMax;
        public Notification mPublicVersion;
        public CharSequence[] mRemoteInputHistory;
        public CharSequence mSettingsText;
        public String mShortcutId;
        public boolean mShowWhen;
        public boolean mSilent;
        public Icon mSmallIcon;
        public String mSortKey;
        public Style mStyle;
        public CharSequence mSubText;
        public RemoteViews mTickerView;
        public long mTimeout;
        public boolean mUseChronometer;
        public int mVisibility;

        @RequiresApi(19)
        public Builder(@NonNull Context context, @NonNull Notification notification) {
            ArrayList parcelableArrayList;
            this(context, NotificationCompat.getChannelId(notification));
            Bundle bundle = notification.extras;
            Style styleExtractStyleFromNotification = Style.extractStyleFromNotification(notification);
            setContentTitle(NotificationCompat.getContentTitle(notification)).setContentText(NotificationCompat.getContentText(notification)).setContentInfo(NotificationCompat.getContentInfo(notification)).setSubText(NotificationCompat.getSubText(notification)).setSettingsText(NotificationCompat.getSettingsText(notification)).setStyle(styleExtractStyleFromNotification).setContentIntent(notification.contentIntent).setGroup(NotificationCompat.getGroup(notification)).setGroupSummary(NotificationCompat.isGroupSummary(notification)).setLocusId(NotificationCompat.getLocusId(notification)).setWhen(notification.when).setShowWhen(NotificationCompat.getShowWhen(notification)).setUsesChronometer(NotificationCompat.getUsesChronometer(notification)).setAutoCancel(NotificationCompat.getAutoCancel(notification)).setOnlyAlertOnce(NotificationCompat.getOnlyAlertOnce(notification)).setOngoing(NotificationCompat.getOngoing(notification)).setLocalOnly(NotificationCompat.getLocalOnly(notification)).setLargeIcon(notification.largeIcon).setBadgeIconType(NotificationCompat.getBadgeIconType(notification)).setCategory(NotificationCompat.getCategory(notification)).setBubbleMetadata(NotificationCompat.getBubbleMetadata(notification)).setNumber(notification.number).setTicker(notification.tickerText).setContentIntent(notification.contentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(notification.fullScreenIntent, NotificationCompat.getHighPriority(notification)).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setDefaults(notification.defaults).setPriority(notification.priority).setColor(NotificationCompat.getColor(notification)).setVisibility(NotificationCompat.getVisibility(notification)).setPublicVersion(NotificationCompat.getPublicVersion(notification)).setSortKey(NotificationCompat.getSortKey(notification)).setTimeoutAfter(NotificationCompat.getTimeoutAfter(notification)).setShortcutId(NotificationCompat.getShortcutId(notification)).setProgress(bundle.getInt(NotificationCompat.EXTRA_PROGRESS_MAX), bundle.getInt(NotificationCompat.EXTRA_PROGRESS), bundle.getBoolean(NotificationCompat.EXTRA_PROGRESS_INDETERMINATE)).setAllowSystemGeneratedContextualActions(NotificationCompat.getAllowSystemGeneratedContextualActions(notification)).setSmallIcon(notification.icon, notification.iconLevel).addExtras(getExtrasWithoutDuplicateData(notification, styleExtractStyleFromNotification));
            int i = Build.VERSION.SDK_INT;
            this.mSmallIcon = notification.getSmallIcon();
            Notification.Action[] actionArr = notification.actions;
            if (actionArr != null && actionArr.length != 0) {
                for (Notification.Action action : actionArr) {
                    addAction(Action.Builder.fromAndroidAction(action).build());
                }
            }
            int i2 = Build.VERSION.SDK_INT;
            List<Action> invisibleActions = NotificationCompat.getInvisibleActions(notification);
            if (!invisibleActions.isEmpty()) {
                Iterator<Action> it = invisibleActions.iterator();
                while (it.hasNext()) {
                    addInvisibleAction(it.next());
                }
            }
            String[] stringArray = notification.extras.getStringArray(NotificationCompat.EXTRA_PEOPLE);
            if (stringArray != null && stringArray.length != 0) {
                for (String str : stringArray) {
                    addPerson(str);
                }
            }
            if (Build.VERSION.SDK_INT >= 28 && (parcelableArrayList = notification.extras.getParcelableArrayList(NotificationCompat.EXTRA_PEOPLE_LIST)) != null && !parcelableArrayList.isEmpty()) {
                Iterator it2 = parcelableArrayList.iterator();
                while (it2.hasNext()) {
                    addPerson(Person.fromAndroidPerson((android.app.Person) it2.next()));
                }
            }
            int i3 = Build.VERSION.SDK_INT;
            if (bundle.containsKey(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN)) {
                setChronometerCountDown(bundle.getBoolean(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN));
            }
            int i4 = Build.VERSION.SDK_INT;
            if (bundle.containsKey(NotificationCompat.EXTRA_COLORIZED)) {
                setColorized(bundle.getBoolean(NotificationCompat.EXTRA_COLORIZED));
            }
        }

        @Nullable
        @RequiresApi(19)
        public static Bundle getExtrasWithoutDuplicateData(@NonNull Notification notification, @Nullable Style style) {
            Bundle bundle = notification.extras;
            if (bundle == null) {
                return null;
            }
            Bundle bundle2 = new Bundle(bundle);
            bundle2.remove(NotificationCompat.EXTRA_TITLE);
            bundle2.remove(NotificationCompat.EXTRA_TEXT);
            bundle2.remove(NotificationCompat.EXTRA_INFO_TEXT);
            bundle2.remove(NotificationCompat.EXTRA_SUB_TEXT);
            bundle2.remove(NotificationCompat.EXTRA_CHANNEL_ID);
            bundle2.remove(NotificationCompat.EXTRA_CHANNEL_GROUP_ID);
            bundle2.remove(NotificationCompat.EXTRA_SHOW_WHEN);
            bundle2.remove(NotificationCompat.EXTRA_PROGRESS);
            bundle2.remove(NotificationCompat.EXTRA_PROGRESS_MAX);
            bundle2.remove(NotificationCompat.EXTRA_PROGRESS_INDETERMINATE);
            bundle2.remove(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN);
            bundle2.remove(NotificationCompat.EXTRA_COLORIZED);
            bundle2.remove(NotificationCompat.EXTRA_PEOPLE_LIST);
            bundle2.remove(NotificationCompat.EXTRA_PEOPLE);
            bundle2.remove(NotificationCompatExtras.EXTRA_SORT_KEY);
            bundle2.remove(NotificationCompatExtras.EXTRA_GROUP_KEY);
            bundle2.remove(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
            bundle2.remove(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
            bundle2.remove(NotificationCompatExtras.EXTRA_ACTION_EXTRAS);
            Bundle bundle3 = bundle2.getBundle(CarExtender.EXTRA_CAR_EXTENDER);
            if (bundle3 != null) {
                Bundle bundle4 = new Bundle(bundle3);
                bundle4.remove(CarExtender.EXTRA_INVISIBLE_ACTIONS);
                bundle2.putBundle(CarExtender.EXTRA_CAR_EXTENDER, bundle4);
            }
            if (style != null) {
                style.clearCompatExtraKeys(bundle2);
            }
            return bundle2;
        }

        @Nullable
        public static CharSequence limitCharSequenceLength(@Nullable CharSequence cs) {
            return (cs != null && cs.length() > 5120) ? cs.subSequence(0, MAX_CHARSEQUENCE_LENGTH) : cs;
        }

        @Nullable
        private Bitmap reduceLargeIconSize(@Nullable Bitmap icon) {
            if (icon == null || Build.VERSION.SDK_INT >= 27) {
                return icon;
            }
            Resources resources = this.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_width);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_height);
            if (icon.getWidth() <= dimensionPixelSize && icon.getHeight() <= dimensionPixelSize2) {
                return icon;
            }
            double dMin = Math.min(((double) dimensionPixelSize) / ((double) Math.max(1, icon.getWidth())), ((double) dimensionPixelSize2) / ((double) Math.max(1, icon.getHeight())));
            return Bitmap.createScaledBitmap(icon, (int) Math.ceil(((double) icon.getWidth()) * dMin), (int) Math.ceil(((double) icon.getHeight()) * dMin), true);
        }

        private void setFlag(int mask, boolean value) {
            if (value) {
                Notification notification = this.mNotification;
                notification.flags = mask | notification.flags;
            } else {
                Notification notification2 = this.mNotification;
                notification2.flags = (~mask) & notification2.flags;
            }
        }

        private boolean useExistingRemoteView() {
            Style style = this.mStyle;
            return style == null || !style.displayCustomViewInline();
        }

        @NonNull
        public Builder addAction(int icon, @Nullable CharSequence title, @Nullable PendingIntent intent) {
            this.mActions.add(new Action(icon, title, intent));
            return this;
        }

        @NonNull
        public Builder addExtras(@Nullable Bundle extras) {
            if (extras != null) {
                Bundle bundle = this.mExtras;
                if (bundle == null) {
                    this.mExtras = new Bundle(extras);
                } else {
                    bundle.putAll(extras);
                }
            }
            return this;
        }

        @NonNull
        @RequiresApi(21)
        public Builder addInvisibleAction(int icon, @Nullable CharSequence title, @Nullable PendingIntent intent) {
            this.mInvisibleActions.add(new Action(icon, title, intent));
            return this;
        }

        @NonNull
        @Deprecated
        public Builder addPerson(@Nullable String uri) {
            if (uri != null && !uri.isEmpty()) {
                this.mPeople.add(uri);
            }
            return this;
        }

        @NonNull
        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        @NonNull
        public Builder clearActions() {
            this.mActions.clear();
            return this;
        }

        @NonNull
        public Builder clearInvisibleActions() {
            this.mInvisibleActions.clear();
            Bundle bundle = this.mExtras.getBundle(CarExtender.EXTRA_CAR_EXTENDER);
            if (bundle != null) {
                Bundle bundle2 = new Bundle(bundle);
                bundle2.remove(CarExtender.EXTRA_INVISIBLE_ACTIONS);
                this.mExtras.putBundle(CarExtender.EXTRA_CAR_EXTENDER, bundle2);
            }
            return this;
        }

        @NonNull
        public Builder clearPeople() {
            this.mPersonList.clear();
            this.mPeople.clear();
            return this;
        }

        @Nullable
        @SuppressLint({"BuilderSetStyle"})
        public RemoteViews createBigContentView() {
            RemoteViews remoteViewsMakeBigContentView;
            int i = Build.VERSION.SDK_INT;
            if (this.mBigContentView != null && useExistingRemoteView()) {
                return this.mBigContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder = new NotificationCompatBuilder(this);
            Style style = this.mStyle;
            if (style != null && (remoteViewsMakeBigContentView = style.makeBigContentView(notificationCompatBuilder)) != null) {
                return remoteViewsMakeBigContentView;
            }
            Notification notificationBuild = notificationCompatBuilder.build();
            int i2 = Build.VERSION.SDK_INT;
            return Notification.Builder.recoverBuilder(this.mContext, notificationBuild).createBigContentView();
        }

        @Nullable
        @SuppressLint({"BuilderSetStyle"})
        public RemoteViews createContentView() {
            RemoteViews remoteViewsMakeContentView;
            if (this.mContentView != null && useExistingRemoteView()) {
                return this.mContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder = new NotificationCompatBuilder(this);
            Style style = this.mStyle;
            if (style != null && (remoteViewsMakeContentView = style.makeContentView(notificationCompatBuilder)) != null) {
                return remoteViewsMakeContentView;
            }
            Notification notificationBuild = notificationCompatBuilder.build();
            int i = Build.VERSION.SDK_INT;
            return Notification.Builder.recoverBuilder(this.mContext, notificationBuild).createContentView();
        }

        @Nullable
        @SuppressLint({"BuilderSetStyle"})
        public RemoteViews createHeadsUpContentView() {
            RemoteViews remoteViewsMakeHeadsUpContentView;
            int i = Build.VERSION.SDK_INT;
            if (this.mHeadsUpContentView != null && useExistingRemoteView()) {
                return this.mHeadsUpContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder = new NotificationCompatBuilder(this);
            Style style = this.mStyle;
            if (style != null && (remoteViewsMakeHeadsUpContentView = style.makeHeadsUpContentView(notificationCompatBuilder)) != null) {
                return remoteViewsMakeHeadsUpContentView;
            }
            Notification notificationBuild = notificationCompatBuilder.build();
            int i2 = Build.VERSION.SDK_INT;
            return Notification.Builder.recoverBuilder(this.mContext, notificationBuild).createHeadsUpContentView();
        }

        @NonNull
        public Builder extend(@NonNull Extender extender) {
            extender.extend(this);
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews getBigContentView() {
            return this.mBigContentView;
        }

        @Nullable
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public BubbleMetadata getBubbleMetadata() {
            return this.mBubbleMetadata;
        }

        @ColorInt
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public int getColor() {
            return this.mColor;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews getContentView() {
            return this.mContentView;
        }

        @NonNull
        public Bundle getExtras() {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews getHeadsUpContentView() {
            return this.mHeadsUpContentView;
        }

        @NonNull
        @Deprecated
        public Notification getNotification() {
            return build();
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public int getPriority() {
            return this.mPriority;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public long getWhenIfShowing() {
            if (this.mShowWhen) {
                return this.mNotification.when;
            }
            return 0L;
        }

        @NonNull
        public Builder setAllowSystemGeneratedContextualActions(boolean allowed) {
            this.mAllowSystemGeneratedContextualActions = allowed;
            return this;
        }

        @NonNull
        public Builder setAutoCancel(boolean autoCancel) {
            setFlag(16, autoCancel);
            return this;
        }

        @NonNull
        public Builder setBadgeIconType(int icon) {
            this.mBadgeIcon = icon;
            return this;
        }

        @NonNull
        public Builder setBubbleMetadata(@Nullable BubbleMetadata data) {
            this.mBubbleMetadata = data;
            return this;
        }

        @NonNull
        public Builder setCategory(@Nullable String category) {
            this.mCategory = category;
            return this;
        }

        @NonNull
        public Builder setChannelId(@NonNull String channelId) {
            this.mChannelId = channelId;
            return this;
        }

        @NonNull
        @RequiresApi(24)
        public Builder setChronometerCountDown(boolean countsDown) {
            this.mChronometerCountDown = countsDown;
            getExtras().putBoolean(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN, countsDown);
            return this;
        }

        @NonNull
        public Builder setColor(@ColorInt int argb) {
            this.mColor = argb;
            return this;
        }

        @NonNull
        public Builder setColorized(boolean colorize) {
            this.mColorized = colorize;
            this.mColorizedSet = true;
            return this;
        }

        @NonNull
        public Builder setContent(@Nullable RemoteViews views) {
            this.mNotification.contentView = views;
            return this;
        }

        @NonNull
        public Builder setContentInfo(@Nullable CharSequence info) {
            this.mContentInfo = limitCharSequenceLength(info);
            return this;
        }

        @NonNull
        public Builder setContentIntent(@Nullable PendingIntent intent) {
            this.mContentIntent = intent;
            return this;
        }

        @NonNull
        public Builder setContentText(@Nullable CharSequence text) {
            this.mContentText = limitCharSequenceLength(text);
            return this;
        }

        @NonNull
        public Builder setContentTitle(@Nullable CharSequence title) {
            this.mContentTitle = limitCharSequenceLength(title);
            return this;
        }

        @NonNull
        public Builder setCustomBigContentView(@Nullable RemoteViews contentView) {
            this.mBigContentView = contentView;
            return this;
        }

        @NonNull
        public Builder setCustomContentView(@Nullable RemoteViews contentView) {
            this.mContentView = contentView;
            return this;
        }

        @NonNull
        public Builder setCustomHeadsUpContentView(@Nullable RemoteViews contentView) {
            this.mHeadsUpContentView = contentView;
            return this;
        }

        @NonNull
        public Builder setDefaults(int defaults) {
            Notification notification = this.mNotification;
            notification.defaults = defaults;
            if ((defaults & 4) != 0) {
                notification.flags |= 1;
            }
            return this;
        }

        @NonNull
        public Builder setDeleteIntent(@Nullable PendingIntent intent) {
            this.mNotification.deleteIntent = intent;
            return this;
        }

        @NonNull
        public Builder setExtras(@Nullable Bundle extras) {
            this.mExtras = extras;
            return this;
        }

        @NonNull
        public Builder setFullScreenIntent(@Nullable PendingIntent intent, boolean highPriority) {
            this.mFullScreenIntent = intent;
            setFlag(128, highPriority);
            return this;
        }

        @NonNull
        public Builder setGroup(@Nullable String groupKey) {
            this.mGroupKey = groupKey;
            return this;
        }

        @NonNull
        public Builder setGroupAlertBehavior(int groupAlertBehavior) {
            this.mGroupAlertBehavior = groupAlertBehavior;
            return this;
        }

        @NonNull
        public Builder setGroupSummary(boolean isGroupSummary) {
            this.mGroupSummary = isGroupSummary;
            return this;
        }

        @NonNull
        public Builder setLargeIcon(@Nullable Bitmap icon) {
            this.mLargeIcon = reduceLargeIconSize(icon);
            return this;
        }

        @NonNull
        public Builder setLights(@ColorInt int argb, int onMs, int offMs) {
            Notification notification = this.mNotification;
            notification.ledARGB = argb;
            notification.ledOnMS = onMs;
            notification.ledOffMS = offMs;
            int i = (notification.ledOnMS == 0 || notification.ledOffMS == 0) ? 0 : 1;
            Notification notification2 = this.mNotification;
            notification2.flags = i | (notification2.flags & (-2));
            return this;
        }

        @NonNull
        public Builder setLocalOnly(boolean b2) {
            this.mLocalOnly = b2;
            return this;
        }

        @NonNull
        public Builder setLocusId(@Nullable final LocusIdCompat locusId) {
            this.mLocusId = locusId;
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setNotificationSilent() {
            this.mSilent = true;
            return this;
        }

        @NonNull
        public Builder setNumber(int number) {
            this.mNumber = number;
            return this;
        }

        @NonNull
        public Builder setOngoing(boolean ongoing) {
            setFlag(2, ongoing);
            return this;
        }

        @NonNull
        public Builder setOnlyAlertOnce(boolean onlyAlertOnce) {
            setFlag(8, onlyAlertOnce);
            return this;
        }

        @NonNull
        public Builder setPriority(int pri) {
            this.mPriority = pri;
            return this;
        }

        @NonNull
        public Builder setProgress(int max, int progress, boolean indeterminate) {
            this.mProgressMax = max;
            this.mProgress = progress;
            this.mProgressIndeterminate = indeterminate;
            return this;
        }

        @NonNull
        public Builder setPublicVersion(@Nullable Notification n) {
            this.mPublicVersion = n;
            return this;
        }

        @NonNull
        public Builder setRemoteInputHistory(@Nullable CharSequence[] text) {
            this.mRemoteInputHistory = text;
            return this;
        }

        @NonNull
        public Builder setSettingsText(@Nullable CharSequence text) {
            this.mSettingsText = limitCharSequenceLength(text);
            return this;
        }

        @NonNull
        public Builder setShortcutId(@Nullable String shortcutId) {
            this.mShortcutId = shortcutId;
            return this;
        }

        @NonNull
        public Builder setShortcutInfo(@Nullable final ShortcutInfoCompat shortcutInfo) {
            if (shortcutInfo == null) {
                return this;
            }
            this.mShortcutId = shortcutInfo.getId();
            if (this.mLocusId == null) {
                if (shortcutInfo.getLocusId() != null) {
                    this.mLocusId = shortcutInfo.getLocusId();
                } else if (shortcutInfo.getId() != null) {
                    this.mLocusId = new LocusIdCompat(shortcutInfo.getId());
                }
            }
            if (this.mContentTitle == null) {
                setContentTitle(shortcutInfo.getShortLabel());
            }
            return this;
        }

        @NonNull
        public Builder setShowWhen(boolean show) {
            this.mShowWhen = show;
            return this;
        }

        @NonNull
        public Builder setSilent(boolean silent) {
            this.mSilent = silent;
            return this;
        }

        @NonNull
        @RequiresApi(23)
        public Builder setSmallIcon(@NonNull IconCompat icon) {
            this.mSmallIcon = icon.toIcon(this.mContext);
            return this;
        }

        @NonNull
        public Builder setSortKey(@Nullable String sortKey) {
            this.mSortKey = sortKey;
            return this;
        }

        @NonNull
        public Builder setSound(@Nullable Uri sound) {
            Notification notification = this.mNotification;
            notification.sound = sound;
            notification.audioStreamType = -1;
            int i = Build.VERSION.SDK_INT;
            notification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
            return this;
        }

        @NonNull
        public Builder setStyle(@Nullable Style style) {
            if (this.mStyle != style) {
                this.mStyle = style;
                Style style2 = this.mStyle;
                if (style2 != null) {
                    style2.setBuilder(this);
                }
            }
            return this;
        }

        @NonNull
        public Builder setSubText(@Nullable CharSequence text) {
            this.mSubText = limitCharSequenceLength(text);
            return this;
        }

        @NonNull
        public Builder setTicker(@Nullable CharSequence tickerText) {
            this.mNotification.tickerText = limitCharSequenceLength(tickerText);
            return this;
        }

        @NonNull
        public Builder setTimeoutAfter(long durationMs) {
            this.mTimeout = durationMs;
            return this;
        }

        @NonNull
        public Builder setUsesChronometer(boolean b2) {
            this.mUseChronometer = b2;
            return this;
        }

        @NonNull
        public Builder setVibrate(@Nullable long[] pattern) {
            this.mNotification.vibrate = pattern;
            return this;
        }

        @NonNull
        public Builder setVisibility(int visibility) {
            this.mVisibility = visibility;
            return this;
        }

        @NonNull
        public Builder setWhen(long when) {
            this.mNotification.when = when;
            return this;
        }

        @NonNull
        public Builder addAction(@Nullable Action action) {
            if (action != null) {
                this.mActions.add(action);
            }
            return this;
        }

        @NonNull
        @RequiresApi(21)
        public Builder addInvisibleAction(@Nullable Action action) {
            if (action != null) {
                this.mInvisibleActions.add(action);
            }
            return this;
        }

        @NonNull
        public Builder setSmallIcon(int icon) {
            this.mNotification.icon = icon;
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setTicker(@Nullable CharSequence tickerText, @Nullable RemoteViews views) {
            this.mNotification.tickerText = limitCharSequenceLength(tickerText);
            this.mTickerView = views;
            return this;
        }

        @NonNull
        public Builder addPerson(@Nullable final Person person) {
            if (person != null) {
                this.mPersonList.add(person);
            }
            return this;
        }

        @NonNull
        public Builder setSmallIcon(int icon, int level) {
            Notification notification = this.mNotification;
            notification.icon = icon;
            notification.iconLevel = level;
            return this;
        }

        @NonNull
        public Builder setSound(@Nullable Uri sound, int streamType) {
            Notification notification = this.mNotification;
            notification.sound = sound;
            notification.audioStreamType = streamType;
            int i = Build.VERSION.SDK_INT;
            notification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(streamType).build();
            return this;
        }

        public Builder(@NonNull Context context, @NonNull String channelId) {
            this.mActions = new ArrayList<>();
            this.mPersonList = new ArrayList<>();
            this.mInvisibleActions = new ArrayList<>();
            this.mShowWhen = true;
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            this.mNotification = new Notification();
            this.mContext = context;
            this.mChannelId = channelId;
            this.mNotification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList<>();
            this.mAllowSystemGeneratedContextualActions = true;
        }

        @Deprecated
        public Builder(@NonNull Context context) {
            this(context, (String) null);
        }
    }

    public static class DecoratedCustomViewStyle extends Style {
        public static final int MAX_ACTION_BUTTONS = 3;
        public static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle";

        private RemoteViews createRemoteViews(RemoteViews innerView, boolean showActions) {
            int iMin;
            boolean z = true;
            RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(true, R.layout.notification_template_custom_big, false);
            remoteViewsApplyStandardTemplate.removeAllViews(R.id.actions);
            List<Action> nonContextualActions = getNonContextualActions(this.mBuilder.mActions);
            if (!showActions || nonContextualActions == null || (iMin = Math.min(nonContextualActions.size(), 3)) <= 0) {
                z = false;
            } else {
                for (int i = 0; i < iMin; i++) {
                    remoteViewsApplyStandardTemplate.addView(R.id.actions, generateActionButton(nonContextualActions.get(i)));
                }
            }
            int i2 = z ? 0 : 8;
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.actions, i2);
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.action_divider, i2);
            buildIntoRemoteViews(remoteViewsApplyStandardTemplate, innerView);
            return remoteViewsApplyStandardTemplate;
        }

        private RemoteViews generateActionButton(Action action) {
            boolean z = action.actionIntent == null;
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), z ? R.layout.notification_action_tombstone : R.layout.notification_action);
            IconCompat iconCompat = action.getIconCompat();
            if (iconCompat != null) {
                remoteViews.setImageViewBitmap(R.id.action_image, createColoredBitmap(iconCompat, this.mBuilder.mContext.getResources().getColor(R.color.notification_action_color_filter)));
            }
            remoteViews.setTextViewText(R.id.action_text, action.title);
            if (!z) {
                remoteViews.setOnClickPendingIntent(R.id.action_container, action.actionIntent);
            }
            int i = Build.VERSION.SDK_INT;
            remoteViews.setContentDescription(R.id.action_container, action.title);
            return remoteViews;
        }

        public static List<Action> getNonContextualActions(List<Action> actions) {
            if (actions == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Action action : actions) {
                if (!action.isContextual()) {
                    arrayList.add(action);
                }
            }
            return arrayList;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            builder.getBuilder().setStyle(new Notification.DecoratedCustomViewStyle());
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public boolean displayCustomViewInline() {
            return true;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            return null;
        }
    }

    public interface Extender {
        @NonNull
        Builder extend(@NonNull Builder builder);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface GroupAlertBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface NotificationVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface StreamType {
    }

    public static abstract class Style {
        public CharSequence mBigContentTitle;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Builder mBuilder;
        public CharSequence mSummaryText;
        public boolean mSummaryTextSet = false;

        private int calculateTopPadding() {
            Resources resources = this.mBuilder.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_top_pad);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
            float fConstrain = (constrain(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
            return Math.round((fConstrain * dimensionPixelSize2) + ((1.0f - fConstrain) * dimensionPixelSize));
        }

        public static float constrain(float amount, float low, float high) {
            if (amount < low) {
                return low;
            }
            return amount > high ? high : amount;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Nullable
        public static Style constructCompatStyleByName(@Nullable String templateClass) {
            if (templateClass == null) {
                return null;
            }
            byte b2 = -1;
            switch (templateClass.hashCode()) {
                case -716705180:
                    if (templateClass.equals(DecoratedCustomViewStyle.TEMPLATE_CLASS_NAME)) {
                        b2 = 3;
                    }
                    break;
                case -171946061:
                    if (templateClass.equals(BigPictureStyle.TEMPLATE_CLASS_NAME)) {
                        b2 = 1;
                    }
                    break;
                case 912942987:
                    if (templateClass.equals(InboxStyle.TEMPLATE_CLASS_NAME)) {
                        b2 = 2;
                    }
                    break;
                case 919595044:
                    if (templateClass.equals(BigTextStyle.TEMPLATE_CLASS_NAME)) {
                        b2 = 0;
                    }
                    break;
                case 2090799565:
                    if (templateClass.equals(MessagingStyle.TEMPLATE_CLASS_NAME)) {
                        b2 = 4;
                    }
                    break;
            }
            if (b2 == 0) {
                return new BigTextStyle();
            }
            if (b2 == 1) {
                return new BigPictureStyle();
            }
            if (b2 == 2) {
                return new InboxStyle();
            }
            if (b2 == 3) {
                return new DecoratedCustomViewStyle();
            }
            if (b2 != 4) {
                return null;
            }
            return new MessagingStyle();
        }

        @Nullable
        public static Style constructCompatStyleByPlatformName(@Nullable String platformTemplateClass) {
            if (platformTemplateClass == null) {
                return null;
            }
            int i = Build.VERSION.SDK_INT;
            if (platformTemplateClass.equals(Notification.BigPictureStyle.class.getName())) {
                return new BigPictureStyle();
            }
            if (platformTemplateClass.equals(Notification.BigTextStyle.class.getName())) {
                return new BigTextStyle();
            }
            if (platformTemplateClass.equals(Notification.InboxStyle.class.getName())) {
                return new InboxStyle();
            }
            int i2 = Build.VERSION.SDK_INT;
            if (platformTemplateClass.equals(Notification.MessagingStyle.class.getName())) {
                return new MessagingStyle();
            }
            if (platformTemplateClass.equals(Notification.DecoratedCustomViewStyle.class.getName())) {
                return new DecoratedCustomViewStyle();
            }
            return null;
        }

        @Nullable
        public static Style constructCompatStyleForBundle(@NonNull Bundle extras) {
            Style styleConstructCompatStyleByName = constructCompatStyleByName(extras.getString(NotificationCompat.EXTRA_COMPAT_TEMPLATE));
            if (styleConstructCompatStyleByName != null) {
                return styleConstructCompatStyleByName;
            }
            if (extras.containsKey(NotificationCompat.EXTRA_SELF_DISPLAY_NAME) || extras.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                return new MessagingStyle();
            }
            if (extras.containsKey(NotificationCompat.EXTRA_PICTURE)) {
                return new BigPictureStyle();
            }
            if (extras.containsKey(NotificationCompat.EXTRA_BIG_TEXT)) {
                return new BigTextStyle();
            }
            return extras.containsKey(NotificationCompat.EXTRA_TEXT_LINES) ? new InboxStyle() : constructCompatStyleByPlatformName(extras.getString(NotificationCompat.EXTRA_TEMPLATE));
        }

        @Nullable
        public static Style constructStyleForExtras(@NonNull Bundle extras) {
            Style styleConstructCompatStyleForBundle = constructCompatStyleForBundle(extras);
            if (styleConstructCompatStyleForBundle == null) {
                return null;
            }
            try {
                styleConstructCompatStyleForBundle.restoreFromCompatExtras(extras);
                return styleConstructCompatStyleForBundle;
            } catch (ClassCastException unused) {
                return null;
            }
        }

        private Bitmap createIconWithBackground(int iconId, int size, int iconSize, int color) {
            int i = R.drawable.notification_icon_background;
            if (color == 0) {
                color = 0;
            }
            Bitmap bitmapCreateColoredBitmap = createColoredBitmap(i, color, size);
            Canvas canvas = new Canvas(bitmapCreateColoredBitmap);
            Drawable drawableMutate = this.mBuilder.mContext.getResources().getDrawable(iconId).mutate();
            drawableMutate.setFilterBitmap(true);
            int i2 = (size - iconSize) / 2;
            int i3 = iconSize + i2;
            drawableMutate.setBounds(i2, i2, i3, i3);
            drawableMutate.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
            drawableMutate.draw(canvas);
            return bitmapCreateColoredBitmap;
        }

        @Nullable
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public static Style extractStyleFromNotification(@NonNull Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras == null) {
                return null;
            }
            return constructStyleForExtras(extras);
        }

        private void hideNormalContent(RemoteViews outerView) {
            outerView.setViewVisibility(R.id.title, 8);
            outerView.setViewVisibility(R.id.text2, 8);
            outerView.setViewVisibility(R.id.text, 8);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void addCompatExtras(@NonNull Bundle extras) {
            if (this.mSummaryTextSet) {
                extras.putCharSequence(NotificationCompat.EXTRA_SUMMARY_TEXT, this.mSummaryText);
            }
            CharSequence charSequence = this.mBigContentTitle;
            if (charSequence != null) {
                extras.putCharSequence(NotificationCompat.EXTRA_TITLE_BIG, charSequence);
            }
            String className = getClassName();
            if (className != null) {
                extras.putString(NotificationCompat.EXTRA_COMPAT_TEMPLATE, className);
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
        }

        /* JADX WARN: Code duplicated, block: B:34:0x010e  */
        /* JADX WARN: Code duplicated, block: B:36:0x011b  */
        /* JADX WARN: Code duplicated, block: B:37:0x0127  */
        /* JADX WARN: Code duplicated, block: B:38:0x012c  */
        /* JADX WARN: Code duplicated, block: B:40:0x012f  */
        /* JADX WARN: Code duplicated, block: B:42:0x0133  */
        /* JADX WARN: Code duplicated, block: B:46:0x0155  */
        /* JADX WARN: Code duplicated, block: B:48:0x015b  */
        /* JADX WARN: Code duplicated, block: B:50:0x0186  */
        /* JADX WARN: Code duplicated, block: B:51:0x018e  */
        /* JADX WARN: Code duplicated, block: B:52:0x01a1  */
        /* JADX WARN: Code duplicated, block: B:55:0x01a6  */
        /* JADX WARN: Code duplicated, block: B:56:0x01a8  */
        /* JADX WARN: Code duplicated, block: B:60:0x01b1  */
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews applyStandardTemplate(boolean showSmallIcon, int resId, boolean fitIn1U) {
            boolean z;
            boolean z2;
            CharSequence charSequence;
            boolean z3;
            int i;
            boolean z4;
            CharSequence charSequence2;
            Resources resources = this.mBuilder.mContext.getResources();
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), resId);
            this.mBuilder.getPriority();
            int i2 = Build.VERSION.SDK_INT;
            Builder builder = this.mBuilder;
            if (builder.mLargeIcon != null) {
                remoteViews.setViewVisibility(R.id.icon, 0);
                remoteViews.setImageViewBitmap(R.id.icon, this.mBuilder.mLargeIcon);
                if (showSmallIcon && this.mBuilder.mNotification.icon != 0) {
                    int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_right_icon_size);
                    int dimensionPixelSize2 = dimensionPixelSize - (resources.getDimensionPixelSize(R.dimen.notification_small_icon_background_padding) * 2);
                    int i3 = Build.VERSION.SDK_INT;
                    Builder builder2 = this.mBuilder;
                    remoteViews.setImageViewBitmap(R.id.right_icon, createIconWithBackground(builder2.mNotification.icon, dimensionPixelSize, dimensionPixelSize2, builder2.getColor()));
                    remoteViews.setViewVisibility(R.id.right_icon, 0);
                }
            } else if (showSmallIcon && builder.mNotification.icon != 0) {
                remoteViews.setViewVisibility(R.id.icon, 0);
                int i4 = Build.VERSION.SDK_INT;
                int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.notification_large_icon_width) - resources.getDimensionPixelSize(R.dimen.notification_big_circle_margin);
                int dimensionPixelSize4 = resources.getDimensionPixelSize(R.dimen.notification_small_icon_size_as_large);
                Builder builder3 = this.mBuilder;
                remoteViews.setImageViewBitmap(R.id.icon, createIconWithBackground(builder3.mNotification.icon, dimensionPixelSize3, dimensionPixelSize4, builder3.getColor()));
            }
            CharSequence charSequence3 = this.mBuilder.mContentTitle;
            if (charSequence3 != null) {
                remoteViews.setTextViewText(R.id.title, charSequence3);
            }
            CharSequence charSequence4 = this.mBuilder.mContentText;
            boolean z5 = true;
            if (charSequence4 != null) {
                remoteViews.setTextViewText(R.id.text, charSequence4);
                z = true;
            } else {
                z = false;
            }
            int i5 = Build.VERSION.SDK_INT;
            Builder builder4 = this.mBuilder;
            CharSequence charSequence5 = builder4.mContentInfo;
            if (charSequence5 == null) {
                if (builder4.mNumber > 0) {
                    if (this.mBuilder.mNumber > resources.getInteger(R.integer.status_bar_notification_info_maxnum)) {
                        remoteViews.setTextViewText(R.id.info, resources.getString(R.string.status_bar_notification_info_overflow));
                    } else {
                        remoteViews.setTextViewText(R.id.info, NumberFormat.getIntegerInstance().format(this.mBuilder.mNumber));
                    }
                    remoteViews.setViewVisibility(R.id.info, 0);
                } else {
                    remoteViews.setViewVisibility(R.id.info, 8);
                    z2 = false;
                }
                charSequence = this.mBuilder.mSubText;
                if (charSequence != null) {
                    int i6 = Build.VERSION.SDK_INT;
                    remoteViews.setTextViewText(R.id.text, charSequence);
                    charSequence2 = this.mBuilder.mContentText;
                    if (charSequence2 != null) {
                        remoteViews.setTextViewText(R.id.text2, charSequence2);
                        remoteViews.setViewVisibility(R.id.text2, 0);
                        z3 = true;
                    } else {
                        remoteViews.setViewVisibility(R.id.text2, 8);
                        z3 = false;
                    }
                } else {
                    z3 = false;
                }
                if (z3) {
                    int i7 = Build.VERSION.SDK_INT;
                    if (fitIn1U) {
                        remoteViews.setTextViewTextSize(R.id.text, 0, resources.getDimensionPixelSize(R.dimen.notification_subtext_size));
                    }
                    remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
                }
                if (this.mBuilder.getWhenIfShowing() != 0) {
                    z5 = z2;
                } else if (this.mBuilder.mUseChronometer) {
                    int i8 = Build.VERSION.SDK_INT;
                    remoteViews.setViewVisibility(R.id.chronometer, 0);
                    remoteViews.setLong(R.id.chronometer, "setBase", (SystemClock.elapsedRealtime() - System.currentTimeMillis()) + this.mBuilder.getWhenIfShowing());
                    remoteViews.setBoolean(R.id.chronometer, "setStarted", true);
                    z4 = this.mBuilder.mChronometerCountDown;
                    if (z4) {
                        int i9 = Build.VERSION.SDK_INT;
                        remoteViews.setChronometerCountDown(R.id.chronometer, z4);
                    }
                } else {
                    remoteViews.setViewVisibility(R.id.time, 0);
                    remoteViews.setLong(R.id.time, "setTime", this.mBuilder.getWhenIfShowing());
                }
                int i10 = R.id.right_side;
                if (z5) {
                    i = 0;
                } else {
                    i = 8;
                }
                remoteViews.setViewVisibility(i10, i);
                remoteViews.setViewVisibility(R.id.line3, z ? 0 : 8);
                return remoteViews;
            }
            remoteViews.setTextViewText(R.id.info, charSequence5);
            remoteViews.setViewVisibility(R.id.info, 0);
            z = true;
            z2 = true;
            charSequence = this.mBuilder.mSubText;
            if (charSequence != null) {
                int i11 = Build.VERSION.SDK_INT;
                remoteViews.setTextViewText(R.id.text, charSequence);
                charSequence2 = this.mBuilder.mContentText;
                if (charSequence2 != null) {
                    remoteViews.setTextViewText(R.id.text2, charSequence2);
                    remoteViews.setViewVisibility(R.id.text2, 0);
                    z3 = true;
                } else {
                    remoteViews.setViewVisibility(R.id.text2, 8);
                    z3 = false;
                }
            } else {
                z3 = false;
            }
            if (z3) {
                int i12 = Build.VERSION.SDK_INT;
                if (fitIn1U) {
                    remoteViews.setTextViewTextSize(R.id.text, 0, resources.getDimensionPixelSize(R.dimen.notification_subtext_size));
                }
                remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
            }
            if (this.mBuilder.getWhenIfShowing() != 0) {
                z5 = z2;
            } else if (this.mBuilder.mUseChronometer) {
                int i13 = Build.VERSION.SDK_INT;
                remoteViews.setViewVisibility(R.id.chronometer, 0);
                remoteViews.setLong(R.id.chronometer, "setBase", (SystemClock.elapsedRealtime() - System.currentTimeMillis()) + this.mBuilder.getWhenIfShowing());
                remoteViews.setBoolean(R.id.chronometer, "setStarted", true);
                z4 = this.mBuilder.mChronometerCountDown;
                if (z4) {
                    int i14 = Build.VERSION.SDK_INT;
                    remoteViews.setChronometerCountDown(R.id.chronometer, z4);
                }
            } else {
                remoteViews.setViewVisibility(R.id.time, 0);
                remoteViews.setLong(R.id.time, "setTime", this.mBuilder.getWhenIfShowing());
            }
            int i15 = R.id.right_side;
            if (z5) {
                i = 0;
            } else {
                i = 8;
            }
            remoteViews.setViewVisibility(i15, i);
            remoteViews.setViewVisibility(R.id.line3, z ? 0 : 8);
            return remoteViews;
        }

        @Nullable
        public Notification build() {
            Builder builder = this.mBuilder;
            if (builder != null) {
                return builder.build();
            }
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void buildIntoRemoteViews(RemoteViews outerView, RemoteViews innerView) {
            hideNormalContent(outerView);
            outerView.removeAllViews(R.id.notification_main_column);
            outerView.addView(R.id.notification_main_column, innerView.clone());
            outerView.setViewVisibility(R.id.notification_main_column, 0);
            int i = Build.VERSION.SDK_INT;
            outerView.setViewPadding(R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void clearCompatExtraKeys(@NonNull Bundle extras) {
            extras.remove(NotificationCompat.EXTRA_SUMMARY_TEXT);
            extras.remove(NotificationCompat.EXTRA_TITLE_BIG);
            extras.remove(NotificationCompat.EXTRA_COMPAT_TEMPLATE);
        }

        public Bitmap createColoredBitmap(@NonNull IconCompat icon, int color) {
            return createColoredBitmap(icon, color, 0);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public boolean displayCustomViewInline() {
            return false;
        }

        @Nullable
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public String getClassName() {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor builder) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor builder) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor builder) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void restoreFromCompatExtras(@NonNull Bundle extras) {
            if (extras.containsKey(NotificationCompat.EXTRA_SUMMARY_TEXT)) {
                this.mSummaryText = extras.getCharSequence(NotificationCompat.EXTRA_SUMMARY_TEXT);
                this.mSummaryTextSet = true;
            }
            this.mBigContentTitle = extras.getCharSequence(NotificationCompat.EXTRA_TITLE_BIG);
        }

        public void setBuilder(@Nullable Builder builder) {
            if (this.mBuilder != builder) {
                this.mBuilder = builder;
                Builder builder2 = this.mBuilder;
                if (builder2 != null) {
                    builder2.setStyle(this);
                }
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Bitmap createColoredBitmap(int iconId, int color) {
            return createColoredBitmap(IconCompat.createWithResource(this.mBuilder.mContext, iconId), color, 0);
        }

        private Bitmap createColoredBitmap(int iconId, int color, int size) {
            return createColoredBitmap(IconCompat.createWithResource(this.mBuilder.mContext, iconId), color, size);
        }

        private Bitmap createColoredBitmap(@NonNull IconCompat icon, int color, int size) {
            Drawable drawableLoadDrawable = icon.loadDrawable(this.mBuilder.mContext);
            int intrinsicWidth = size == 0 ? drawableLoadDrawable.getIntrinsicWidth() : size;
            if (size == 0) {
                size = drawableLoadDrawable.getIntrinsicHeight();
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, size, Bitmap.Config.ARGB_8888);
            drawableLoadDrawable.setBounds(0, 0, intrinsicWidth, size);
            if (color != 0) {
                drawableLoadDrawable.mutate().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            }
            drawableLoadDrawable.draw(new Canvas(bitmapCreateBitmap));
            return bitmapCreateBitmap;
        }
    }

    public static final class WearableExtender implements Extender {
        public static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
        public static final int DEFAULT_FLAGS = 1;
        public static final int DEFAULT_GRAVITY = 80;
        public static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        public static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        public static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        public static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        public static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        public static final int FLAG_HINT_HIDE_ICON = 2;
        public static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        public static final int FLAG_START_SCROLL_BOTTOM = 8;
        public static final String KEY_ACTIONS = "actions";
        public static final String KEY_BACKGROUND = "background";
        public static final String KEY_BRIDGE_TAG = "bridgeTag";
        public static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        public static final String KEY_CONTENT_ICON = "contentIcon";
        public static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        public static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        public static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        public static final String KEY_DISMISSAL_ID = "dismissalId";
        public static final String KEY_DISPLAY_INTENT = "displayIntent";
        public static final String KEY_FLAGS = "flags";
        public static final String KEY_GRAVITY = "gravity";
        public static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        public static final String KEY_PAGES = "pages";

        @Deprecated
        public static final int SCREEN_TIMEOUT_LONG = -1;

        @Deprecated
        public static final int SCREEN_TIMEOUT_SHORT = 0;

        @Deprecated
        public static final int SIZE_DEFAULT = 0;

        @Deprecated
        public static final int SIZE_FULL_SCREEN = 5;

        @Deprecated
        public static final int SIZE_LARGE = 4;

        @Deprecated
        public static final int SIZE_MEDIUM = 3;

        @Deprecated
        public static final int SIZE_SMALL = 2;

        @Deprecated
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        public ArrayList<Action> mActions;
        public Bitmap mBackground;
        public String mBridgeTag;
        public int mContentActionIndex;
        public int mContentIcon;
        public int mContentIconGravity;
        public int mCustomContentHeight;
        public int mCustomSizePreset;
        public String mDismissalId;
        public PendingIntent mDisplayIntent;
        public int mFlags;
        public int mGravity;
        public int mHintScreenTimeout;
        public ArrayList<Notification> mPages;

        public WearableExtender() {
            this.mActions = new ArrayList<>();
            this.mFlags = 1;
            this.mPages = new ArrayList<>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
        }

        @RequiresApi(20)
        public static Notification.Action getActionFromActionCompat(Action actionCompat) {
            int i = Build.VERSION.SDK_INT;
            IconCompat iconCompat = actionCompat.getIconCompat();
            Notification.Action.Builder builder = new Notification.Action.Builder(iconCompat == null ? null : iconCompat.toIcon(), actionCompat.getTitle(), actionCompat.getActionIntent());
            Bundle bundle = actionCompat.getExtras() != null ? new Bundle(actionCompat.getExtras()) : new Bundle();
            bundle.putBoolean(NotificationCompatJellybean.EXTRA_ALLOW_GENERATED_REPLIES, actionCompat.getAllowGeneratedReplies());
            int i2 = Build.VERSION.SDK_INT;
            builder.setAllowGeneratedReplies(actionCompat.getAllowGeneratedReplies());
            builder.addExtras(bundle);
            RemoteInput[] remoteInputs = actionCompat.getRemoteInputs();
            if (remoteInputs != null) {
                for (android.app.RemoteInput remoteInput : RemoteInput.fromCompat(remoteInputs)) {
                    builder.addRemoteInput(remoteInput);
                }
            }
            return builder.build();
        }

        private void setFlag(int mask, boolean value) {
            if (value) {
                this.mFlags = mask | this.mFlags;
            } else {
                this.mFlags = (~mask) & this.mFlags;
            }
        }

        @NonNull
        public WearableExtender addAction(@NonNull Action action) {
            this.mActions.add(action);
            return this;
        }

        @NonNull
        public WearableExtender addActions(@NonNull List<Action> actions) {
            this.mActions.addAll(actions);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender addPage(@NonNull Notification page) {
            this.mPages.add(page);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender addPages(@NonNull List<Notification> pages) {
            this.mPages.addAll(pages);
            return this;
        }

        @NonNull
        public WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Extender
        @NonNull
        public Builder extend(@NonNull Builder builder) {
            Bundle bundle = new Bundle();
            if (!this.mActions.isEmpty()) {
                int i = Build.VERSION.SDK_INT;
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>(this.mActions.size());
                for (Action action : this.mActions) {
                    int i2 = Build.VERSION.SDK_INT;
                    arrayList.add(getActionFromActionCompat(action));
                }
                bundle.putParcelableArrayList(KEY_ACTIONS, arrayList);
            }
            int i3 = this.mFlags;
            if (i3 != 1) {
                bundle.putInt("flags", i3);
            }
            PendingIntent pendingIntent = this.mDisplayIntent;
            if (pendingIntent != null) {
                bundle.putParcelable(KEY_DISPLAY_INTENT, pendingIntent);
            }
            if (!this.mPages.isEmpty()) {
                ArrayList<Notification> arrayList2 = this.mPages;
                bundle.putParcelableArray(KEY_PAGES, (Parcelable[]) arrayList2.toArray(new Notification[arrayList2.size()]));
            }
            Bitmap bitmap = this.mBackground;
            if (bitmap != null) {
                bundle.putParcelable(KEY_BACKGROUND, bitmap);
            }
            int i4 = this.mContentIcon;
            if (i4 != 0) {
                bundle.putInt(KEY_CONTENT_ICON, i4);
            }
            int i5 = this.mContentIconGravity;
            if (i5 != 8388613) {
                bundle.putInt(KEY_CONTENT_ICON_GRAVITY, i5);
            }
            int i6 = this.mContentActionIndex;
            if (i6 != -1) {
                bundle.putInt(KEY_CONTENT_ACTION_INDEX, i6);
            }
            int i7 = this.mCustomSizePreset;
            if (i7 != 0) {
                bundle.putInt(KEY_CUSTOM_SIZE_PRESET, i7);
            }
            int i8 = this.mCustomContentHeight;
            if (i8 != 0) {
                bundle.putInt(KEY_CUSTOM_CONTENT_HEIGHT, i8);
            }
            int i9 = this.mGravity;
            if (i9 != 80) {
                bundle.putInt(KEY_GRAVITY, i9);
            }
            int i10 = this.mHintScreenTimeout;
            if (i10 != 0) {
                bundle.putInt(KEY_HINT_SCREEN_TIMEOUT, i10);
            }
            String str = this.mDismissalId;
            if (str != null) {
                bundle.putString(KEY_DISMISSAL_ID, str);
            }
            String str2 = this.mBridgeTag;
            if (str2 != null) {
                bundle.putString(KEY_BRIDGE_TAG, str2);
            }
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }

        @NonNull
        public List<Action> getActions() {
            return this.mActions;
        }

        @Nullable
        @Deprecated
        public Bitmap getBackground() {
            return this.mBackground;
        }

        @Nullable
        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        @Deprecated
        public int getContentIcon() {
            return this.mContentIcon;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 1) != 0;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        @Nullable
        public String getDismissalId() {
            return this.mDismissalId;
        }

        @Nullable
        @Deprecated
        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        @Deprecated
        public int getGravity() {
            return this.mGravity;
        }

        @Deprecated
        public boolean getHintAmbientBigPicture() {
            return (this.mFlags & 32) != 0;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 16) != 0;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.mFlags & 64) != 0;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.mFlags & 2) != 0;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 4) != 0;
        }

        @NonNull
        @Deprecated
        public List<Notification> getPages() {
            return this.mPages;
        }

        public boolean getStartScrollBottom() {
            return (this.mFlags & 8) != 0;
        }

        @NonNull
        @Deprecated
        public WearableExtender setBackground(@Nullable Bitmap background) {
            this.mBackground = background;
            return this;
        }

        @NonNull
        public WearableExtender setBridgeTag(@Nullable String bridgeTag) {
            this.mBridgeTag = bridgeTag;
            return this;
        }

        @NonNull
        public WearableExtender setContentAction(int actionIndex) {
            this.mContentActionIndex = actionIndex;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setContentIcon(int icon) {
            this.mContentIcon = icon;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setContentIconGravity(int contentIconGravity) {
            this.mContentIconGravity = contentIconGravity;
            return this;
        }

        @NonNull
        public WearableExtender setContentIntentAvailableOffline(boolean contentIntentAvailableOffline) {
            setFlag(1, contentIntentAvailableOffline);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setCustomContentHeight(int height) {
            this.mCustomContentHeight = height;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setCustomSizePreset(int sizePreset) {
            this.mCustomSizePreset = sizePreset;
            return this;
        }

        @NonNull
        public WearableExtender setDismissalId(@Nullable String dismissalId) {
            this.mDismissalId = dismissalId;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setDisplayIntent(@Nullable PendingIntent intent) {
            this.mDisplayIntent = intent;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setGravity(int gravity) {
            this.mGravity = gravity;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setHintAmbientBigPicture(boolean hintAmbientBigPicture) {
            setFlag(32, hintAmbientBigPicture);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setHintAvoidBackgroundClipping(boolean hintAvoidBackgroundClipping) {
            setFlag(16, hintAvoidBackgroundClipping);
            return this;
        }

        @NonNull
        public WearableExtender setHintContentIntentLaunchesActivity(boolean hintContentIntentLaunchesActivity) {
            setFlag(64, hintContentIntentLaunchesActivity);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setHintHideIcon(boolean hintHideIcon) {
            setFlag(2, hintHideIcon);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setHintScreenTimeout(int timeout) {
            this.mHintScreenTimeout = timeout;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setHintShowBackgroundOnly(boolean hintShowBackgroundOnly) {
            setFlag(4, hintShowBackgroundOnly);
            return this;
        }

        @NonNull
        public WearableExtender setStartScrollBottom(boolean startScrollBottom) {
            setFlag(8, startScrollBottom);
            return this;
        }

        @NonNull
        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public WearableExtender m3clone() {
            WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.mActions = new ArrayList<>(this.mActions);
            wearableExtender.mFlags = this.mFlags;
            wearableExtender.mDisplayIntent = this.mDisplayIntent;
            wearableExtender.mPages = new ArrayList<>(this.mPages);
            wearableExtender.mBackground = this.mBackground;
            wearableExtender.mContentIcon = this.mContentIcon;
            wearableExtender.mContentIconGravity = this.mContentIconGravity;
            wearableExtender.mContentActionIndex = this.mContentActionIndex;
            wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
            wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
            wearableExtender.mGravity = this.mGravity;
            wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
            wearableExtender.mDismissalId = this.mDismissalId;
            wearableExtender.mBridgeTag = this.mBridgeTag;
            return wearableExtender;
        }

        public WearableExtender(@NonNull Notification notification) {
            this.mActions = new ArrayList<>();
            this.mFlags = 1;
            this.mPages = new ArrayList<>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
            Bundle extras = NotificationCompat.getExtras(notification);
            Bundle bundle = extras != null ? extras.getBundle("android.wearable.EXTENSIONS") : null;
            if (bundle != null) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList(KEY_ACTIONS);
                int i = Build.VERSION.SDK_INT;
                if (parcelableArrayList != null) {
                    Action[] actionArr = new Action[parcelableArrayList.size()];
                    for (int i2 = 0; i2 < actionArr.length; i2++) {
                        int i3 = Build.VERSION.SDK_INT;
                        actionArr[i2] = NotificationCompat.getActionCompatFromAction((Notification.Action) parcelableArrayList.get(i2));
                    }
                    Collections.addAll(this.mActions, actionArr);
                }
                this.mFlags = bundle.getInt("flags", 1);
                this.mDisplayIntent = (PendingIntent) bundle.getParcelable(KEY_DISPLAY_INTENT);
                Notification[] notificationArrayFromBundle = NotificationCompat.getNotificationArrayFromBundle(bundle, KEY_PAGES);
                if (notificationArrayFromBundle != null) {
                    Collections.addAll(this.mPages, notificationArrayFromBundle);
                }
                this.mBackground = (Bitmap) bundle.getParcelable(KEY_BACKGROUND);
                this.mContentIcon = bundle.getInt(KEY_CONTENT_ICON);
                this.mContentIconGravity = bundle.getInt(KEY_CONTENT_ICON_GRAVITY, 8388613);
                this.mContentActionIndex = bundle.getInt(KEY_CONTENT_ACTION_INDEX, -1);
                this.mCustomSizePreset = bundle.getInt(KEY_CUSTOM_SIZE_PRESET, 0);
                this.mCustomContentHeight = bundle.getInt(KEY_CUSTOM_CONTENT_HEIGHT);
                this.mGravity = bundle.getInt(KEY_GRAVITY, 80);
                this.mHintScreenTimeout = bundle.getInt(KEY_HINT_SCREEN_TIMEOUT);
                this.mDismissalId = bundle.getString(KEY_DISMISSAL_ID);
                this.mBridgeTag = bundle.getString(KEY_BRIDGE_TAG);
            }
        }
    }

    @Deprecated
    public NotificationCompat() {
    }

    @Nullable
    public static Action getAction(@NonNull Notification notification, int actionIndex) {
        int i = Build.VERSION.SDK_INT;
        return getActionCompatFromAction(notification.actions[actionIndex]);
    }

    @NonNull
    @RequiresApi(20)
    public static Action getActionCompatFromAction(@NonNull Notification.Action action) {
        RemoteInput[] remoteInputArr;
        int i;
        android.app.RemoteInput[] remoteInputs = action.getRemoteInputs();
        if (remoteInputs == null) {
            remoteInputArr = null;
        } else {
            RemoteInput[] remoteInputArr2 = new RemoteInput[remoteInputs.length];
            for (int i2 = 0; i2 < remoteInputs.length; i2++) {
                android.app.RemoteInput remoteInput = remoteInputs[i2];
                remoteInputArr2[i2] = new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), Build.VERSION.SDK_INT >= 29 ? remoteInput.getEditChoicesBeforeSending() : 0, remoteInput.getExtras(), null);
            }
            remoteInputArr = remoteInputArr2;
        }
        int i3 = Build.VERSION.SDK_INT;
        boolean z = action.getExtras().getBoolean(NotificationCompatJellybean.EXTRA_ALLOW_GENERATED_REPLIES) || action.getAllowGeneratedReplies();
        boolean z2 = action.getExtras().getBoolean(Action.EXTRA_SHOWS_USER_INTERFACE, true);
        int semanticAction = Build.VERSION.SDK_INT >= 28 ? action.getSemanticAction() : action.getExtras().getInt(Action.EXTRA_SEMANTIC_ACTION, 0);
        boolean zIsContextual = Build.VERSION.SDK_INT >= 29 ? action.isContextual() : false;
        int i4 = Build.VERSION.SDK_INT;
        if (action.getIcon() != null || (i = action.icon) == 0) {
            return new Action(action.getIcon() != null ? IconCompat.createFromIconOrNullIfZeroResId(action.getIcon()) : null, action.title, action.actionIntent, action.getExtras(), remoteInputArr, (RemoteInput[]) null, z, semanticAction, z2, zIsContextual);
        }
        return new Action(i, action.title, action.actionIntent, action.getExtras(), remoteInputArr, (RemoteInput[]) null, z, semanticAction, z2, zIsContextual);
    }

    public static int getActionCount(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        Notification.Action[] actionArr = notification.actions;
        if (actionArr != null) {
            return actionArr.length;
        }
        return 0;
    }

    public static boolean getAllowSystemGeneratedContextualActions(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 29) {
            return notification.getAllowSystemGeneratedContextualActions();
        }
        return false;
    }

    public static boolean getAutoCancel(@NonNull Notification notification) {
        return (notification.flags & 16) != 0;
    }

    public static int getBadgeIconType(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.getBadgeIconType();
    }

    @Nullable
    public static BubbleMetadata getBubbleMetadata(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 29) {
            return BubbleMetadata.fromPlatform(notification.getBubbleMetadata());
        }
        return null;
    }

    @Nullable
    public static String getCategory(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.category;
    }

    @Nullable
    public static String getChannelId(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.getChannelId();
    }

    public static int getColor(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.color;
    }

    @Nullable
    @RequiresApi(19)
    public static CharSequence getContentInfo(@NonNull Notification notification) {
        return notification.extras.getCharSequence(EXTRA_INFO_TEXT);
    }

    @Nullable
    @RequiresApi(19)
    public static CharSequence getContentText(@NonNull Notification notification) {
        return notification.extras.getCharSequence(EXTRA_TEXT);
    }

    @Nullable
    @RequiresApi(19)
    public static CharSequence getContentTitle(@NonNull Notification notification) {
        return notification.extras.getCharSequence(EXTRA_TITLE);
    }

    @Nullable
    public static Bundle getExtras(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.extras;
    }

    @Nullable
    public static String getGroup(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.getGroup();
    }

    public static int getGroupAlertBehavior(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.getGroupAlertBehavior();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static boolean getHighPriority(@NonNull Notification notification) {
        return (notification.flags & 128) != 0;
    }

    @NonNull
    @RequiresApi(21)
    public static List<Action> getInvisibleActions(@NonNull Notification notification) {
        Bundle bundle;
        ArrayList arrayList = new ArrayList();
        int i = Build.VERSION.SDK_INT;
        Bundle bundle2 = notification.extras.getBundle(CarExtender.EXTRA_CAR_EXTENDER);
        if (bundle2 != null && (bundle = bundle2.getBundle(CarExtender.EXTRA_INVISIBLE_ACTIONS)) != null) {
            for (int i2 = 0; i2 < bundle.size(); i2++) {
                arrayList.add(NotificationCompatJellybean.getActionFromBundle(bundle.getBundle(Integer.toString(i2))));
            }
        }
        return arrayList;
    }

    public static boolean getLocalOnly(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return (notification.flags & 256) != 0;
    }

    @Nullable
    public static LocusIdCompat getLocusId(@NonNull Notification notification) {
        LocusId locusId;
        if (Build.VERSION.SDK_INT < 29 || (locusId = notification.getLocusId()) == null) {
            return null;
        }
        return LocusIdCompat.toLocusIdCompat(locusId);
    }

    @NonNull
    public static Notification[] getNotificationArrayFromBundle(@NonNull Bundle bundle, @NonNull String key) {
        Parcelable[] parcelableArray = bundle.getParcelableArray(key);
        if ((parcelableArray instanceof Notification[]) || parcelableArray == null) {
            return (Notification[]) parcelableArray;
        }
        Notification[] notificationArr = new Notification[parcelableArray.length];
        for (int i = 0; i < parcelableArray.length; i++) {
            notificationArr[i] = (Notification) parcelableArray[i];
        }
        bundle.putParcelableArray(key, notificationArr);
        return notificationArr;
    }

    public static boolean getOngoing(@NonNull Notification notification) {
        return (notification.flags & 2) != 0;
    }

    public static boolean getOnlyAlertOnce(@NonNull Notification notification) {
        return (notification.flags & 8) != 0;
    }

    @NonNull
    public static List<Person> getPeople(@NonNull Notification notification) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 28) {
            ArrayList parcelableArrayList = notification.extras.getParcelableArrayList(EXTRA_PEOPLE_LIST);
            if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    arrayList.add(Person.fromAndroidPerson((android.app.Person) it.next()));
                }
            }
        } else {
            String[] stringArray = notification.extras.getStringArray(EXTRA_PEOPLE);
            if (stringArray != null && stringArray.length != 0) {
                for (String str : stringArray) {
                    arrayList.add(new Person.Builder().setUri(str).build());
                }
            }
        }
        return arrayList;
    }

    @Nullable
    public static Notification getPublicVersion(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.publicVersion;
    }

    @Nullable
    public static CharSequence getSettingsText(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.getSettingsText();
    }

    @Nullable
    public static String getShortcutId(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.getShortcutId();
    }

    @RequiresApi(19)
    public static boolean getShowWhen(@NonNull Notification notification) {
        return notification.extras.getBoolean(EXTRA_SHOW_WHEN);
    }

    @Nullable
    public static String getSortKey(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.getSortKey();
    }

    @Nullable
    @RequiresApi(19)
    public static CharSequence getSubText(@NonNull Notification notification) {
        return notification.extras.getCharSequence(EXTRA_SUB_TEXT);
    }

    public static long getTimeoutAfter(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.getTimeoutAfter();
    }

    @RequiresApi(19)
    public static boolean getUsesChronometer(@NonNull Notification notification) {
        return notification.extras.getBoolean(EXTRA_SHOW_CHRONOMETER);
    }

    public static int getVisibility(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return notification.visibility;
    }

    public static boolean isGroupSummary(@NonNull Notification notification) {
        int i = Build.VERSION.SDK_INT;
        return (notification.flags & 512) != 0;
    }

    public static final class CarExtender implements Extender {

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        public static final String EXTRA_COLOR = "app_color";
        public static final String EXTRA_CONVERSATION = "car_conversation";

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
        public static final String EXTRA_LARGE_ICON = "large_icon";
        public static final String KEY_AUTHOR = "author";
        public static final String KEY_MESSAGES = "messages";
        public static final String KEY_ON_READ = "on_read";
        public static final String KEY_ON_REPLY = "on_reply";
        public static final String KEY_PARTICIPANTS = "participants";
        public static final String KEY_REMOTE_INPUT = "remote_input";
        public static final String KEY_TEXT = "text";
        public static final String KEY_TIMESTAMP = "timestamp";
        public int mColor;
        public Bitmap mLargeIcon;
        public UnreadConversation mUnreadConversation;

        @Deprecated
        public static class UnreadConversation {
            public final long mLatestTimestamp;
            public final String[] mMessages;
            public final String[] mParticipants;
            public final PendingIntent mReadPendingIntent;
            public final RemoteInput mRemoteInput;
            public final PendingIntent mReplyPendingIntent;

            public static class Builder {
                public long mLatestTimestamp;
                public final List<String> mMessages = new ArrayList();
                public final String mParticipant;
                public PendingIntent mReadPendingIntent;
                public RemoteInput mRemoteInput;
                public PendingIntent mReplyPendingIntent;

                public Builder(@NonNull String name) {
                    this.mParticipant = name;
                }

                @NonNull
                public Builder addMessage(@Nullable String message) {
                    if (message != null) {
                        this.mMessages.add(message);
                    }
                    return this;
                }

                @NonNull
                public UnreadConversation build() {
                    List<String> list = this.mMessages;
                    return new UnreadConversation((String[]) list.toArray(new String[list.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[]{this.mParticipant}, this.mLatestTimestamp);
                }

                @NonNull
                public Builder setLatestTimestamp(long timestamp) {
                    this.mLatestTimestamp = timestamp;
                    return this;
                }

                @NonNull
                public Builder setReadPendingIntent(@Nullable PendingIntent pendingIntent) {
                    this.mReadPendingIntent = pendingIntent;
                    return this;
                }

                @NonNull
                public Builder setReplyAction(@Nullable PendingIntent pendingIntent, @Nullable RemoteInput remoteInput) {
                    this.mRemoteInput = remoteInput;
                    this.mReplyPendingIntent = pendingIntent;
                    return this;
                }
            }

            public UnreadConversation(@Nullable String[] messages, @Nullable RemoteInput remoteInput, @Nullable PendingIntent replyPendingIntent, @Nullable PendingIntent readPendingIntent, @Nullable String[] participants, long latestTimestamp) {
                this.mMessages = messages;
                this.mRemoteInput = remoteInput;
                this.mReadPendingIntent = readPendingIntent;
                this.mReplyPendingIntent = replyPendingIntent;
                this.mParticipants = participants;
                this.mLatestTimestamp = latestTimestamp;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }

            @Nullable
            public String[] getMessages() {
                return this.mMessages;
            }

            @Nullable
            public String getParticipant() {
                String[] strArr = this.mParticipants;
                if (strArr.length > 0) {
                    return strArr[0];
                }
                return null;
            }

            @Nullable
            public String[] getParticipants() {
                return this.mParticipants;
            }

            @Nullable
            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            @Nullable
            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            @Nullable
            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }
        }

        public CarExtender() {
            this.mColor = 0;
        }

        @RequiresApi(21)
        public static Bundle getBundleForUnreadConversation(@NonNull UnreadConversation uc) {
            Bundle bundle = new Bundle();
            String str = (uc.getParticipants() == null || uc.getParticipants().length <= 1) ? null : uc.getParticipants()[0];
            Parcelable[] parcelableArr = new Parcelable[uc.getMessages().length];
            for (int i = 0; i < parcelableArr.length; i++) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("text", uc.getMessages()[i]);
                bundle2.putString(KEY_AUTHOR, str);
                parcelableArr[i] = bundle2;
            }
            bundle.putParcelableArray(KEY_MESSAGES, parcelableArr);
            RemoteInput remoteInput = uc.getRemoteInput();
            if (remoteInput != null) {
                bundle.putParcelable(KEY_REMOTE_INPUT, new android.app.RemoteInput.Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build());
            }
            bundle.putParcelable(KEY_ON_REPLY, uc.getReplyPendingIntent());
            bundle.putParcelable(KEY_ON_READ, uc.getReadPendingIntent());
            bundle.putStringArray(KEY_PARTICIPANTS, uc.getParticipants());
            bundle.putLong(KEY_TIMESTAMP, uc.getLatestTimestamp());
            return bundle;
        }

        @RequiresApi(21)
        public static UnreadConversation getUnreadConversationFromBundle(@Nullable Bundle b2) {
            String[] strArr;
            boolean z;
            RemoteInput remoteInput = null;
            if (b2 == null) {
                return null;
            }
            Parcelable[] parcelableArray = b2.getParcelableArray(KEY_MESSAGES);
            if (parcelableArray != null) {
                String[] strArr2 = new String[parcelableArray.length];
                int i = 0;
                while (true) {
                    if (i >= strArr2.length) {
                        z = true;
                        break;
                    }
                    if (parcelableArray[i] instanceof Bundle) {
                        strArr2[i] = ((Bundle) parcelableArray[i]).getString("text");
                        if (strArr2[i] != null) {
                            i++;
                        }
                    }
                    z = false;
                    break;
                }
                if (!z) {
                    return null;
                }
                strArr = strArr2;
            } else {
                strArr = null;
            }
            PendingIntent pendingIntent = (PendingIntent) b2.getParcelable(KEY_ON_READ);
            PendingIntent pendingIntent2 = (PendingIntent) b2.getParcelable(KEY_ON_REPLY);
            android.app.RemoteInput remoteInput2 = (android.app.RemoteInput) b2.getParcelable(KEY_REMOTE_INPUT);
            String[] stringArray = b2.getStringArray(KEY_PARTICIPANTS);
            if (stringArray == null || stringArray.length != 1) {
                return null;
            }
            if (remoteInput2 != null) {
                remoteInput = new RemoteInput(remoteInput2.getResultKey(), remoteInput2.getLabel(), remoteInput2.getChoices(), remoteInput2.getAllowFreeFormInput(), Build.VERSION.SDK_INT >= 29 ? remoteInput2.getEditChoicesBeforeSending() : 0, remoteInput2.getExtras(), null);
            }
            return new UnreadConversation(strArr, remoteInput, pendingIntent2, pendingIntent, stringArray, b2.getLong(KEY_TIMESTAMP));
        }

        @Override // androidx.core.app.NotificationCompat.Extender
        @NonNull
        public Builder extend(@NonNull Builder builder) {
            int i = Build.VERSION.SDK_INT;
            Bundle bundle = new Bundle();
            Bitmap bitmap = this.mLargeIcon;
            if (bitmap != null) {
                bundle.putParcelable(EXTRA_LARGE_ICON, bitmap);
            }
            int i2 = this.mColor;
            if (i2 != 0) {
                bundle.putInt(EXTRA_COLOR, i2);
            }
            UnreadConversation unreadConversation = this.mUnreadConversation;
            if (unreadConversation != null) {
                bundle.putBundle(EXTRA_CONVERSATION, getBundleForUnreadConversation(unreadConversation));
            }
            builder.getExtras().putBundle(EXTRA_CAR_EXTENDER, bundle);
            return builder;
        }

        @ColorInt
        public int getColor() {
            return this.mColor;
        }

        @Nullable
        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        @Nullable
        @Deprecated
        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }

        @NonNull
        public CarExtender setColor(@ColorInt int color) {
            this.mColor = color;
            return this;
        }

        @NonNull
        public CarExtender setLargeIcon(@Nullable Bitmap largeIcon) {
            this.mLargeIcon = largeIcon;
            return this;
        }

        @NonNull
        @Deprecated
        public CarExtender setUnreadConversation(@Nullable UnreadConversation unreadConversation) {
            this.mUnreadConversation = unreadConversation;
            return this;
        }

        public CarExtender(@NonNull Notification notification) {
            this.mColor = 0;
            int i = Build.VERSION.SDK_INT;
            Bundle bundle = NotificationCompat.getExtras(notification) == null ? null : NotificationCompat.getExtras(notification).getBundle(EXTRA_CAR_EXTENDER);
            if (bundle != null) {
                this.mLargeIcon = (Bitmap) bundle.getParcelable(EXTRA_LARGE_ICON);
                this.mColor = bundle.getInt(EXTRA_COLOR, 0);
                this.mUnreadConversation = getUnreadConversationFromBundle(bundle.getBundle(EXTRA_CONVERSATION));
            }
        }
    }

    public static class InboxStyle extends Style {
        public static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$InboxStyle";
        public ArrayList<CharSequence> mTexts = new ArrayList<>();

        public InboxStyle() {
        }

        @NonNull
        public InboxStyle addLine(@Nullable CharSequence cs) {
            if (cs != null) {
                this.mTexts.add(Builder.limitCharSequenceLength(cs));
            }
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            int i = Build.VERSION.SDK_INT;
            Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(builder.getBuilder()).setBigContentTitle(this.mBigContentTitle);
            if (this.mSummaryTextSet) {
                bigContentTitle.setSummaryText(this.mSummaryText);
            }
            Iterator<CharSequence> it = this.mTexts.iterator();
            while (it.hasNext()) {
                bigContentTitle.addLine(it.next());
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void clearCompatExtraKeys(@NonNull Bundle extras) {
            super.clearCompatExtraKeys(extras);
            extras.remove(NotificationCompat.EXTRA_TEXT_LINES);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void restoreFromCompatExtras(@NonNull Bundle extras) {
            super.restoreFromCompatExtras(extras);
            this.mTexts.clear();
            if (extras.containsKey(NotificationCompat.EXTRA_TEXT_LINES)) {
                Collections.addAll(this.mTexts, extras.getCharSequenceArray(NotificationCompat.EXTRA_TEXT_LINES));
            }
        }

        @NonNull
        public InboxStyle setBigContentTitle(@Nullable CharSequence title) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(title);
            return this;
        }

        @NonNull
        public InboxStyle setSummaryText(@Nullable CharSequence cs) {
            this.mSummaryText = Builder.limitCharSequenceLength(cs);
            this.mSummaryTextSet = true;
            return this;
        }

        public InboxStyle(@Nullable Builder builder) {
            setBuilder(builder);
        }
    }

    public static class MessagingStyle extends Style {
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        public static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$MessagingStyle";

        @Nullable
        public CharSequence mConversationTitle;

        @Nullable
        public Boolean mIsGroupConversation;
        public Person mUser;
        public final List<Message> mMessages = new ArrayList();
        public final List<Message> mHistoricMessages = new ArrayList();

        public MessagingStyle() {
        }

        @Nullable
        public static MessagingStyle extractMessagingStyleFromNotification(@NonNull Notification notification) {
            Style styleExtractStyleFromNotification = Style.extractStyleFromNotification(notification);
            if (styleExtractStyleFromNotification instanceof MessagingStyle) {
                return (MessagingStyle) styleExtractStyleFromNotification;
            }
            return null;
        }

        @Nullable
        private Message findLatestIncomingMessage() {
            int size = this.mMessages.size();
            while (true) {
                size--;
                if (size < 0) {
                    if (this.mMessages.isEmpty()) {
                        return null;
                    }
                    List<Message> list = this.mMessages;
                    return list.get(list.size() - 1);
                }
                Message message = this.mMessages.get(size);
                if (message.getPerson() != null && !TextUtils.isEmpty(message.getPerson().getName())) {
                    return message;
                }
            }
        }

        private boolean hasMessagesWithoutSender() {
            for (int size = this.mMessages.size() - 1; size >= 0; size--) {
                Message message = this.mMessages.get(size);
                if (message.getPerson() != null && message.getPerson().getName() == null) {
                    return true;
                }
            }
            return false;
        }

        @NonNull
        private TextAppearanceSpan makeFontColorSpan(int color) {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(color), null);
        }

        private CharSequence makeMessageLine(@NonNull Message message) {
            BidiFormatter bidiFormatter = BidiFormatter.getInstance();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int i = Build.VERSION.SDK_INT;
            int color = 1 != 0 ? ViewCompat.MEASURED_STATE_MASK : -1;
            CharSequence name = message.getPerson() == null ? "" : message.getPerson().getName();
            if (TextUtils.isEmpty(name)) {
                name = this.mUser.getName();
                if (1 != 0 && this.mBuilder.getColor() != 0) {
                    color = this.mBuilder.getColor();
                }
            }
            CharSequence charSequenceUnicodeWrap = bidiFormatter.unicodeWrap(name);
            spannableStringBuilder.append(charSequenceUnicodeWrap);
            spannableStringBuilder.setSpan(makeFontColorSpan(color), spannableStringBuilder.length() - charSequenceUnicodeWrap.length(), spannableStringBuilder.length(), 33);
            spannableStringBuilder.append((CharSequence) "  ").append(bidiFormatter.unicodeWrap(message.getText() != null ? message.getText() : ""));
            return spannableStringBuilder;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void addCompatExtras(@NonNull Bundle extras) {
            super.addCompatExtras(extras);
            extras.putCharSequence(NotificationCompat.EXTRA_SELF_DISPLAY_NAME, this.mUser.getName());
            extras.putBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER, this.mUser.toBundle());
            extras.putCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE, this.mConversationTitle);
            if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                extras.putCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE, this.mConversationTitle);
            }
            if (!this.mMessages.isEmpty()) {
                extras.putParcelableArray(NotificationCompat.EXTRA_MESSAGES, Message.getBundleArrayForMessages(this.mMessages));
            }
            if (!this.mHistoricMessages.isEmpty()) {
                extras.putParcelableArray(NotificationCompat.EXTRA_HISTORIC_MESSAGES, Message.getBundleArrayForMessages(this.mHistoricMessages));
            }
            Boolean bool = this.mIsGroupConversation;
            if (bool != null) {
                extras.putBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION, bool.booleanValue());
            }
        }

        @NonNull
        public MessagingStyle addHistoricMessage(@Nullable Message message) {
            if (message != null) {
                this.mHistoricMessages.add(message);
                if (this.mHistoricMessages.size() > 25) {
                    this.mHistoricMessages.remove(0);
                }
            }
            return this;
        }

        @NonNull
        @Deprecated
        public MessagingStyle addMessage(@Nullable CharSequence text, long timestamp, @Nullable CharSequence sender) {
            this.mMessages.add(new Message(text, timestamp, new Person.Builder().setName(sender).build()));
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
            setGroupConversation(isGroupConversation());
            Notification.MessagingStyle messagingStyle = Build.VERSION.SDK_INT >= 28 ? new Notification.MessagingStyle(this.mUser.toAndroidPerson()) : new Notification.MessagingStyle(this.mUser.getName());
            Iterator<Message> it = this.mMessages.iterator();
            while (it.hasNext()) {
                messagingStyle.addMessage(it.next().toAndroidMessage());
            }
            int i = Build.VERSION.SDK_INT;
            Iterator<Message> it2 = this.mHistoricMessages.iterator();
            while (it2.hasNext()) {
                messagingStyle.addHistoricMessage(it2.next().toAndroidMessage());
            }
            if (this.mIsGroupConversation.booleanValue() || Build.VERSION.SDK_INT >= 28) {
                messagingStyle.setConversationTitle(this.mConversationTitle);
            }
            if (Build.VERSION.SDK_INT >= 28) {
                messagingStyle.setGroupConversation(this.mIsGroupConversation.booleanValue());
            }
            messagingStyle.setBuilder(builder.getBuilder());
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void clearCompatExtraKeys(@NonNull Bundle extras) {
            super.clearCompatExtraKeys(extras);
            extras.remove(NotificationCompat.EXTRA_MESSAGING_STYLE_USER);
            extras.remove(NotificationCompat.EXTRA_SELF_DISPLAY_NAME);
            extras.remove(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            extras.remove(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            extras.remove(NotificationCompat.EXTRA_MESSAGES);
            extras.remove(NotificationCompat.EXTRA_HISTORIC_MESSAGES);
            extras.remove(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        @Nullable
        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        @NonNull
        public List<Message> getHistoricMessages() {
            return this.mHistoricMessages;
        }

        @NonNull
        public List<Message> getMessages() {
            return this.mMessages;
        }

        @NonNull
        public Person getUser() {
            return this.mUser;
        }

        @Nullable
        @Deprecated
        public CharSequence getUserDisplayName() {
            return this.mUser.getName();
        }

        public boolean isGroupConversation() {
            Builder builder = this.mBuilder;
            if (builder != null && builder.mContext.getApplicationInfo().targetSdkVersion < 28 && this.mIsGroupConversation == null) {
                return this.mConversationTitle != null;
            }
            Boolean bool = this.mIsGroupConversation;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void restoreFromCompatExtras(@NonNull Bundle extras) {
            super.restoreFromCompatExtras(extras);
            this.mMessages.clear();
            if (extras.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                this.mUser = Person.fromBundle(extras.getBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER));
            } else {
                this.mUser = new Person.Builder().setName(extras.getString(NotificationCompat.EXTRA_SELF_DISPLAY_NAME)).build();
            }
            this.mConversationTitle = extras.getCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            if (this.mConversationTitle == null) {
                this.mConversationTitle = extras.getCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            }
            Parcelable[] parcelableArray = extras.getParcelableArray(NotificationCompat.EXTRA_MESSAGES);
            if (parcelableArray != null) {
                this.mMessages.addAll(Message.getMessagesFromBundleArray(parcelableArray));
            }
            Parcelable[] parcelableArray2 = extras.getParcelableArray(NotificationCompat.EXTRA_HISTORIC_MESSAGES);
            if (parcelableArray2 != null) {
                this.mHistoricMessages.addAll(Message.getMessagesFromBundleArray(parcelableArray2));
            }
            if (extras.containsKey(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)) {
                this.mIsGroupConversation = Boolean.valueOf(extras.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION));
            }
        }

        @NonNull
        public MessagingStyle setConversationTitle(@Nullable CharSequence conversationTitle) {
            this.mConversationTitle = conversationTitle;
            return this;
        }

        @NonNull
        public MessagingStyle setGroupConversation(boolean isGroupConversation) {
            this.mIsGroupConversation = Boolean.valueOf(isGroupConversation);
            return this;
        }

        @Deprecated
        public MessagingStyle(@NonNull CharSequence userDisplayName) {
            this.mUser = new Person.Builder().setName(userDisplayName).build();
        }

        public static final class Message {
            public static final String KEY_DATA_MIME_TYPE = "type";
            public static final String KEY_DATA_URI = "uri";
            public static final String KEY_EXTRAS_BUNDLE = "extras";
            public static final String KEY_NOTIFICATION_PERSON = "sender_person";
            public static final String KEY_PERSON = "person";
            public static final String KEY_SENDER = "sender";
            public static final String KEY_TEXT = "text";
            public static final String KEY_TIMESTAMP = "time";

            @Nullable
            public String mDataMimeType;

            @Nullable
            public Uri mDataUri;
            public Bundle mExtras;

            @Nullable
            public final Person mPerson;
            public final CharSequence mText;
            public final long mTimestamp;

            public Message(@Nullable CharSequence text, long timestamp, @Nullable Person person) {
                this.mExtras = new Bundle();
                this.mText = text;
                this.mTimestamp = timestamp;
                this.mPerson = person;
            }

            @NonNull
            public static Bundle[] getBundleArrayForMessages(@NonNull List<Message> messages) {
                Bundle[] bundleArr = new Bundle[messages.size()];
                int size = messages.size();
                for (int i = 0; i < size; i++) {
                    bundleArr[i] = messages.get(i).toBundle();
                }
                return bundleArr;
            }

            @Nullable
            public static Message getMessageFromBundle(@NonNull Bundle bundle) {
                Person personBuild;
                try {
                    if (bundle.containsKey("text") && bundle.containsKey("time")) {
                        if (bundle.containsKey(KEY_PERSON)) {
                            personBuild = Person.fromBundle(bundle.getBundle(KEY_PERSON));
                        } else if (!bundle.containsKey(KEY_NOTIFICATION_PERSON) || Build.VERSION.SDK_INT < 28) {
                            personBuild = bundle.containsKey(KEY_SENDER) ? new Person.Builder().setName(bundle.getCharSequence(KEY_SENDER)).build() : null;
                        } else {
                            personBuild = Person.fromAndroidPerson((android.app.Person) bundle.getParcelable(KEY_NOTIFICATION_PERSON));
                        }
                        Message message = new Message(bundle.getCharSequence("text"), bundle.getLong("time"), personBuild);
                        if (bundle.containsKey("type") && bundle.containsKey("uri")) {
                            message.setData(bundle.getString("type"), (Uri) bundle.getParcelable("uri"));
                        }
                        if (bundle.containsKey("extras")) {
                            message.getExtras().putAll(bundle.getBundle("extras"));
                        }
                        return message;
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }

            @NonNull
            public static List<Message> getMessagesFromBundleArray(@NonNull Parcelable[] bundles) {
                Message messageFromBundle;
                ArrayList arrayList = new ArrayList(bundles.length);
                for (int i = 0; i < bundles.length; i++) {
                    if ((bundles[i] instanceof Bundle) && (messageFromBundle = getMessageFromBundle((Bundle) bundles[i])) != null) {
                        arrayList.add(messageFromBundle);
                    }
                }
                return arrayList;
            }

            @NonNull
            private Bundle toBundle() {
                Bundle bundle = new Bundle();
                CharSequence charSequence = this.mText;
                if (charSequence != null) {
                    bundle.putCharSequence("text", charSequence);
                }
                bundle.putLong("time", this.mTimestamp);
                Person person = this.mPerson;
                if (person != null) {
                    bundle.putCharSequence(KEY_SENDER, person.getName());
                    if (Build.VERSION.SDK_INT >= 28) {
                        bundle.putParcelable(KEY_NOTIFICATION_PERSON, this.mPerson.toAndroidPerson());
                    } else {
                        bundle.putBundle(KEY_PERSON, this.mPerson.toBundle());
                    }
                }
                String str = this.mDataMimeType;
                if (str != null) {
                    bundle.putString("type", str);
                }
                Uri uri = this.mDataUri;
                if (uri != null) {
                    bundle.putParcelable("uri", uri);
                }
                Bundle bundle2 = this.mExtras;
                if (bundle2 != null) {
                    bundle.putBundle("extras", bundle2);
                }
                return bundle;
            }

            @Nullable
            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            @Nullable
            public Uri getDataUri() {
                return this.mDataUri;
            }

            @NonNull
            public Bundle getExtras() {
                return this.mExtras;
            }

            @Nullable
            public Person getPerson() {
                return this.mPerson;
            }

            @Nullable
            @Deprecated
            public CharSequence getSender() {
                Person person = this.mPerson;
                if (person == null) {
                    return null;
                }
                return person.getName();
            }

            @Nullable
            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            @NonNull
            public Message setData(@Nullable String dataMimeType, @Nullable Uri dataUri) {
                this.mDataMimeType = dataMimeType;
                this.mDataUri = dataUri;
                return this;
            }

            @NonNull
            @RequiresApi(24)
            @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
            public Notification.MessagingStyle.Message toAndroidMessage() {
                Notification.MessagingStyle.Message message;
                Person person = getPerson();
                if (Build.VERSION.SDK_INT >= 28) {
                    message = new Notification.MessagingStyle.Message(getText(), getTimestamp(), person != null ? person.toAndroidPerson() : null);
                } else {
                    message = new Notification.MessagingStyle.Message(getText(), getTimestamp(), person != null ? person.getName() : null);
                }
                if (getDataMimeType() != null) {
                    message.setData(getDataMimeType(), getDataUri());
                }
                return message;
            }

            @Deprecated
            public Message(@Nullable CharSequence text, long timestamp, @Nullable CharSequence sender) {
                this(text, timestamp, new Person.Builder().setName(sender).build());
            }
        }

        @NonNull
        public MessagingStyle addMessage(@Nullable CharSequence text, long timestamp, @Nullable Person person) {
            addMessage(new Message(text, timestamp, person));
            return this;
        }

        @NonNull
        public MessagingStyle addMessage(@Nullable Message message) {
            if (message != null) {
                this.mMessages.add(message);
                if (this.mMessages.size() > 25) {
                    this.mMessages.remove(0);
                }
            }
            return this;
        }

        public MessagingStyle(@NonNull Person user) {
            if (!TextUtils.isEmpty(user.getName())) {
                this.mUser = user;
                return;
            }
            throw new IllegalArgumentException("User's name must not be empty.");
        }
    }
}
