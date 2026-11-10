package androidx.core.app;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NotificationChannelGroupCompat {
    public boolean mBlocked;
    public List<NotificationChannelCompat> mChannels;
    public String mDescription;
    public final String mId;
    public CharSequence mName;

    public static class Builder {
        public final NotificationChannelGroupCompat mGroup;

        public Builder(@NonNull String id) {
            this.mGroup = new NotificationChannelGroupCompat(id);
        }

        @NonNull
        public NotificationChannelGroupCompat build() {
            return this.mGroup;
        }

        @NonNull
        public Builder setDescription(@Nullable String description) {
            this.mGroup.mDescription = description;
            return this;
        }

        @NonNull
        public Builder setName(@Nullable CharSequence name) {
            this.mGroup.mName = name;
            return this;
        }
    }

    public NotificationChannelGroupCompat(@NonNull String id) {
        this.mChannels = Collections.emptyList();
        if (id == null) {
            throw new NullPointerException();
        }
        this.mId = id;
    }

    @RequiresApi(26)
    private List<NotificationChannelCompat> getChannelsCompat(List<NotificationChannel> channels) {
        ArrayList arrayList = new ArrayList();
        for (NotificationChannel notificationChannel : channels) {
            if (this.mId.equals(notificationChannel.getGroup())) {
                arrayList.add(new NotificationChannelCompat(notificationChannel));
            }
        }
        return arrayList;
    }

    @NonNull
    public List<NotificationChannelCompat> getChannels() {
        return this.mChannels;
    }

    @Nullable
    public String getDescription() {
        return this.mDescription;
    }

    @NonNull
    public String getId() {
        return this.mId;
    }

    @Nullable
    public CharSequence getName() {
        return this.mName;
    }

    public NotificationChannelGroup getNotificationChannelGroup() {
        int i = Build.VERSION.SDK_INT;
        NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(this.mId, this.mName);
        if (Build.VERSION.SDK_INT >= 28) {
            notificationChannelGroup.setDescription(this.mDescription);
        }
        return notificationChannelGroup;
    }

    public boolean isBlocked() {
        return this.mBlocked;
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this.mId).setName(this.mName).setDescription(this.mDescription);
    }

    @RequiresApi(28)
    public NotificationChannelGroupCompat(@NonNull NotificationChannelGroup group) {
        this(group, Collections.emptyList());
    }

    @RequiresApi(26)
    public NotificationChannelGroupCompat(@NonNull NotificationChannelGroup group, @NonNull List<NotificationChannel> allChannels) {
        this(group.getId());
        this.mName = group.getName();
        if (Build.VERSION.SDK_INT >= 28) {
            this.mDescription = group.getDescription();
        }
        if (Build.VERSION.SDK_INT >= 28) {
            this.mBlocked = group.isBlocked();
            this.mChannels = getChannelsCompat(group.getChannels());
        } else {
            this.mChannels = getChannelsCompat(allChannels);
        }
    }
}
