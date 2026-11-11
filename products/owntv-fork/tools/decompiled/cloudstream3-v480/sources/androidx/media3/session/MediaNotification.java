package androidx.media3.session;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/* JADX INFO: loaded from: classes4.dex */
public final class MediaNotification {
    public static final String NOTIFICATION_DISMISSED_EVENT_KEY = "androidx.media3.session.NOTIFICATION_DISMISSED_EVENT_KEY";
    public final Notification notification;
    public final int notificationId;

    public interface Provider {

        public interface Callback {
            void onNotificationChanged(MediaNotification mediaNotification);
        }

        MediaNotification createNotification(MediaSession mediaSession, ImmutableList<CommandButton> immutableList, ActionFactory actionFactory, Callback callback);

        boolean handleCustomCommand(MediaSession mediaSession, String str, Bundle bundle);
    }

    public interface ActionFactory {
        NotificationCompat.Action createCustomAction(MediaSession mediaSession, IconCompat iconCompat, CharSequence charSequence, String str, Bundle bundle);

        NotificationCompat.Action createCustomActionFromCustomCommandButton(MediaSession mediaSession, CommandButton commandButton);

        NotificationCompat.Action createMediaAction(MediaSession mediaSession, IconCompat iconCompat, CharSequence charSequence, int i);

        PendingIntent createMediaActionPendingIntent(MediaSession mediaSession, long j);

        PendingIntent createNotificationDismissalIntent(MediaSession mediaSession);

        /* JADX INFO: renamed from: androidx.media3.session.MediaNotification$ActionFactory$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
        }
    }

    public MediaNotification(int i, Notification notification) {
        this.notificationId = i;
        this.notification = (Notification) Preconditions.checkNotNull(notification);
    }
}
