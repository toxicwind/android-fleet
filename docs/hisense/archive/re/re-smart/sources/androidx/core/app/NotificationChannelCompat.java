package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public class NotificationChannelCompat {
    public static final String DEFAULT_CHANNEL_ID = "miscellaneous";
    public static final int DEFAULT_LIGHT_COLOR = 0;
    public static final boolean DEFAULT_SHOW_BADGE = true;
    public AudioAttributes mAudioAttributes;
    public boolean mBypassDnd;
    public boolean mCanBubble;
    public String mConversationId;
    public String mDescription;
    public String mGroupId;

    @NonNull
    public final String mId;
    public int mImportance;
    public boolean mImportantConversation;
    public int mLightColor;
    public boolean mLights;
    public int mLockscreenVisibility;
    public CharSequence mName;
    public String mParentId;
    public boolean mShowBadge;
    public Uri mSound;
    public boolean mVibrationEnabled;
    public long[] mVibrationPattern;

    public static class Builder {
        public final NotificationChannelCompat mChannel;

        public Builder(@NonNull String id, int importance) {
            this.mChannel = new NotificationChannelCompat(id, importance);
        }

        @NonNull
        public NotificationChannelCompat build() {
            return this.mChannel;
        }

        @NonNull
        public Builder setConversationId(@NonNull String parentChannelId, @NonNull String conversationId) {
            if (Build.VERSION.SDK_INT >= 30) {
                NotificationChannelCompat notificationChannelCompat = this.mChannel;
                notificationChannelCompat.mParentId = parentChannelId;
                notificationChannelCompat.mConversationId = conversationId;
            }
            return this;
        }

        @NonNull
        public Builder setDescription(@Nullable String description) {
            this.mChannel.mDescription = description;
            return this;
        }

        @NonNull
        public Builder setGroup(@Nullable String groupId) {
            this.mChannel.mGroupId = groupId;
            return this;
        }

        @NonNull
        public Builder setImportance(int importance) {
            this.mChannel.mImportance = importance;
            return this;
        }

        @NonNull
        public Builder setLightColor(int argb) {
            this.mChannel.mLightColor = argb;
            return this;
        }

        @NonNull
        public Builder setLightsEnabled(boolean lights) {
            this.mChannel.mLights = lights;
            return this;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence name) {
            this.mChannel.mName = name;
            return this;
        }

        @NonNull
        public Builder setShowBadge(boolean showBadge) {
            this.mChannel.mShowBadge = showBadge;
            return this;
        }

        @NonNull
        public Builder setSound(@Nullable Uri sound, @Nullable AudioAttributes audioAttributes) {
            NotificationChannelCompat notificationChannelCompat = this.mChannel;
            notificationChannelCompat.mSound = sound;
            notificationChannelCompat.mAudioAttributes = audioAttributes;
            return this;
        }

        @NonNull
        public Builder setVibrationEnabled(boolean vibration) {
            this.mChannel.mVibrationEnabled = vibration;
            return this;
        }

        @NonNull
        public Builder setVibrationPattern(@Nullable long[] vibrationPattern) {
            this.mChannel.mVibrationEnabled = vibrationPattern != null && vibrationPattern.length > 0;
            this.mChannel.mVibrationPattern = vibrationPattern;
            return this;
        }
    }

    public NotificationChannelCompat(@NonNull String id, int importance) {
        this.mShowBadge = true;
        this.mSound = Settings.System.DEFAULT_NOTIFICATION_URI;
        this.mLightColor = 0;
        if (id == null) {
            throw new NullPointerException();
        }
        this.mId = id;
        this.mImportance = importance;
        int i = Build.VERSION.SDK_INT;
        this.mAudioAttributes = Notification.AUDIO_ATTRIBUTES_DEFAULT;
    }

    public boolean canBubble() {
        return this.mCanBubble;
    }

    public boolean canBypassDnd() {
        return this.mBypassDnd;
    }

    public boolean canShowBadge() {
        return this.mShowBadge;
    }

    @Nullable
    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    @Nullable
    public String getConversationId() {
        return this.mConversationId;
    }

    @Nullable
    public String getDescription() {
        return this.mDescription;
    }

    @Nullable
    public String getGroup() {
        return this.mGroupId;
    }

    @NonNull
    public String getId() {
        return this.mId;
    }

    public int getImportance() {
        return this.mImportance;
    }

    public int getLightColor() {
        return this.mLightColor;
    }

    public int getLockscreenVisibility() {
        return this.mLockscreenVisibility;
    }

    @Nullable
    public CharSequence getName() {
        return this.mName;
    }

    public NotificationChannel getNotificationChannel() {
        String str;
        String str2;
        int i = Build.VERSION.SDK_INT;
        NotificationChannel notificationChannel = new NotificationChannel(this.mId, this.mName, this.mImportance);
        notificationChannel.setDescription(this.mDescription);
        notificationChannel.setGroup(this.mGroupId);
        notificationChannel.setShowBadge(this.mShowBadge);
        notificationChannel.setSound(this.mSound, this.mAudioAttributes);
        notificationChannel.enableLights(this.mLights);
        notificationChannel.setLightColor(this.mLightColor);
        notificationChannel.setVibrationPattern(this.mVibrationPattern);
        notificationChannel.enableVibration(this.mVibrationEnabled);
        if (Build.VERSION.SDK_INT >= 30 && (str = this.mParentId) != null && (str2 = this.mConversationId) != null) {
            notificationChannel.setConversationId(str, str2);
        }
        return notificationChannel;
    }

    @Nullable
    public String getParentChannelId() {
        return this.mParentId;
    }

    @Nullable
    public Uri getSound() {
        return this.mSound;
    }

    @Nullable
    public long[] getVibrationPattern() {
        return this.mVibrationPattern;
    }

    public boolean isImportantConversation() {
        return this.mImportantConversation;
    }

    public boolean shouldShowLights() {
        return this.mLights;
    }

    public boolean shouldVibrate() {
        return this.mVibrationEnabled;
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this.mId, this.mImportance).setName(this.mName).setDescription(this.mDescription).setGroup(this.mGroupId).setShowBadge(this.mShowBadge).setSound(this.mSound, this.mAudioAttributes).setLightsEnabled(this.mLights).setLightColor(this.mLightColor).setVibrationEnabled(this.mVibrationEnabled).setVibrationPattern(this.mVibrationPattern).setConversationId(this.mParentId, this.mConversationId);
    }

    @RequiresApi(26)
    public NotificationChannelCompat(@NonNull NotificationChannel channel) {
        this(channel.getId(), channel.getImportance());
        this.mName = channel.getName();
        this.mDescription = channel.getDescription();
        this.mGroupId = channel.getGroup();
        this.mShowBadge = channel.canShowBadge();
        this.mSound = channel.getSound();
        this.mAudioAttributes = channel.getAudioAttributes();
        this.mLights = channel.shouldShowLights();
        this.mLightColor = channel.getLightColor();
        this.mVibrationEnabled = channel.shouldVibrate();
        this.mVibrationPattern = channel.getVibrationPattern();
        if (Build.VERSION.SDK_INT >= 30) {
            this.mParentId = channel.getParentChannelId();
            this.mConversationId = channel.getConversationId();
        }
        this.mBypassDnd = channel.canBypassDnd();
        this.mLockscreenVisibility = channel.getLockscreenVisibility();
        if (Build.VERSION.SDK_INT >= 29) {
            this.mCanBubble = channel.canBubble();
        }
        if (Build.VERSION.SDK_INT >= 30) {
            this.mImportantConversation = channel.isImportantConversation();
        }
    }
}
