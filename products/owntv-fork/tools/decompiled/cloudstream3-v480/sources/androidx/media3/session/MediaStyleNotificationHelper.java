package androidx.media3.session;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public class MediaStyleNotificationHelper {
    public static final String EXTRA_MEDIA3_SESSION = "androidx.media3.session";

    private MediaStyleNotificationHelper() {
    }

    public static class MediaStyle extends NotificationCompat.Style {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int[] actionsToShowInCompact;
        int remoteDeviceIconRes;
        PendingIntent remoteDeviceIntent;
        CharSequence remoteDeviceName;
        final MediaSession session;

        @Deprecated
        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            return this;
        }

        @Deprecated
        public MediaStyle setShowCancelButton(boolean z) {
            return this;
        }

        public static SessionToken getSessionToken(Notification notification) {
            Bundle bundle;
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras == null || (bundle = extras.getBundle(MediaStyleNotificationHelper.EXTRA_MEDIA3_SESSION)) == null) {
                return null;
            }
            return SessionToken.fromBundle(bundle);
        }

        public MediaStyle(MediaSession mediaSession) {
            this.session = mediaSession;
        }

        public MediaStyle setShowActionsInCompactView(int... iArr) {
            this.actionsToShowInCompact = iArr;
            return this;
        }

        public MediaStyle setRemotePlaybackInfo(CharSequence charSequence, int i, PendingIntent pendingIntent) {
            Preconditions.checkArgument(charSequence != null);
            this.remoteDeviceName = charSequence;
            this.remoteDeviceIconRes = i;
            this.remoteDeviceIntent = pendingIntent;
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            CharSequence charSequence;
            Notification.MediaStyle mediaSession = new Notification.MediaStyle().setMediaSession(this.session.getPlatformToken());
            int[] iArr = this.actionsToShowInCompact;
            if (iArr != null) {
                mediaSession.setShowActionsInCompactView(iArr);
            }
            if (Build.VERSION.SDK_INT >= 34 && (charSequence = this.remoteDeviceName) != null) {
                Api34Impl.setRemotePlaybackInfo(mediaSession, charSequence, this.remoteDeviceIconRes, this.remoteDeviceIntent);
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(mediaSession);
            } else {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(mediaSession);
                Bundle bundle = new Bundle();
                bundle.putBundle(MediaStyleNotificationHelper.EXTRA_MEDIA3_SESSION, this.session.getToken().toBundle());
                notificationBuilderWithBuilderAccessor.getBuilder().addExtras(bundle);
            }
        }

        RemoteViews generateContentView() {
            RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(false, getContentViewLayoutResource(), true);
            int size = this.mBuilder.mActions.size();
            int[] iArr = this.actionsToShowInCompact;
            if (iArr != null) {
                int iMin = Math.min(iArr.length, 3);
                remoteViewsApplyStandardTemplate.removeAllViews(R.id.media_actions);
                if (iMin > 0) {
                    for (int i = 0; i < iMin; i++) {
                        if (i >= size) {
                            throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", Integer.valueOf(i), Integer.valueOf(size - 1)));
                        }
                        remoteViewsApplyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(this.mBuilder.mActions.get(iArr[i])));
                    }
                }
            }
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.end_padder, 0);
            return remoteViewsApplyStandardTemplate;
        }

        private RemoteViews generateMediaActionButton(NotificationCompat.Action action) {
            boolean z = action.getActionIntent() == null;
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), R.layout.media3_notification_media_action);
            IconCompat iconCompat = action.getIconCompat();
            if (iconCompat != null) {
                remoteViews.setImageViewResource(R.id.action0, iconCompat.getResId());
            }
            if (!z) {
                remoteViews.setOnClickPendingIntent(R.id.action0, action.getActionIntent());
            }
            remoteViews.setContentDescription(R.id.action0, action.getTitle());
            return remoteViews;
        }

        int getContentViewLayoutResource() {
            return R.layout.media3_notification_template_media;
        }

        RemoteViews generateBigContentView() {
            int iMin = Math.min(this.mBuilder.mActions.size(), 5);
            RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(false, getBigContentViewLayoutResource(iMin), false);
            remoteViewsApplyStandardTemplate.removeAllViews(R.id.media_actions);
            if (iMin > 0) {
                for (int i = 0; i < iMin; i++) {
                    remoteViewsApplyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(this.mBuilder.mActions.get(i)));
                }
            }
            return remoteViewsApplyStandardTemplate;
        }

        int getBigContentViewLayoutResource(int i) {
            if (i <= 3) {
                return R.layout.media3_notification_template_big_media_narrow;
            }
            return R.layout.media3_notification_template_big_media;
        }
    }

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        public DecoratedMediaCustomViewStyle(MediaSession mediaSession) {
            super(mediaSession);
        }

        @Override // androidx.media3.session.MediaStyleNotificationHelper.MediaStyle, androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT < 24) {
                super.apply(notificationBuilderWithBuilderAccessor);
                return;
            }
            Notification.DecoratedMediaCustomViewStyle decoratedMediaCustomViewStyleCreateDecoratedMediaCustomViewStyle = Api24Impl.createDecoratedMediaCustomViewStyle();
            if (this.actionsToShowInCompact != null) {
                decoratedMediaCustomViewStyleCreateDecoratedMediaCustomViewStyle.setShowActionsInCompactView(this.actionsToShowInCompact);
            }
            if (Build.VERSION.SDK_INT >= 34 && this.remoteDeviceName != null) {
                Api34Impl.setRemotePlaybackInfo(decoratedMediaCustomViewStyleCreateDecoratedMediaCustomViewStyle, this.remoteDeviceName, this.remoteDeviceIconRes, this.remoteDeviceIntent);
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(decoratedMediaCustomViewStyleCreateDecoratedMediaCustomViewStyle);
            } else {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(decoratedMediaCustomViewStyleCreateDecoratedMediaCustomViewStyle);
                Bundle bundle = new Bundle();
                bundle.putBundle(MediaStyleNotificationHelper.EXTRA_MEDIA3_SESSION, this.session.getToken().toBundle());
                notificationBuilderWithBuilderAccessor.getBuilder().addExtras(bundle);
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            boolean z = this.mBuilder.getContentView() != null;
            if (!z && this.mBuilder.getBigContentView() == null) {
                return null;
            }
            RemoteViews remoteViewsGenerateContentView = generateContentView();
            if (z) {
                buildIntoRemoteViews(remoteViewsGenerateContentView, this.mBuilder.getContentView());
            }
            setBackgroundColor(remoteViewsGenerateContentView);
            return remoteViewsGenerateContentView;
        }

        @Override // androidx.media3.session.MediaStyleNotificationHelper.MediaStyle
        int getContentViewLayoutResource() {
            if (this.mBuilder.getContentView() != null) {
                return R.layout.media3_notification_template_media_custom;
            }
            return super.getContentViewLayoutResource();
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews contentView;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getBigContentView() != null) {
                contentView = this.mBuilder.getBigContentView();
            } else {
                contentView = this.mBuilder.getContentView();
            }
            if (contentView == null) {
                return null;
            }
            RemoteViews remoteViewsGenerateBigContentView = generateBigContentView();
            buildIntoRemoteViews(remoteViewsGenerateBigContentView, contentView);
            setBackgroundColor(remoteViewsGenerateBigContentView);
            return remoteViewsGenerateBigContentView;
        }

        @Override // androidx.media3.session.MediaStyleNotificationHelper.MediaStyle
        int getBigContentViewLayoutResource(int i) {
            if (i <= 3) {
                return R.layout.media3_notification_template_big_media_narrow_custom;
            }
            return R.layout.media3_notification_template_big_media_custom;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews contentView;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getHeadsUpContentView() != null) {
                contentView = this.mBuilder.getHeadsUpContentView();
            } else {
                contentView = this.mBuilder.getContentView();
            }
            if (contentView == null) {
                return null;
            }
            RemoteViews remoteViewsGenerateBigContentView = generateBigContentView();
            buildIntoRemoteViews(remoteViewsGenerateBigContentView, contentView);
            setBackgroundColor(remoteViewsGenerateBigContentView);
            return remoteViewsGenerateBigContentView;
        }

        private void setBackgroundColor(RemoteViews remoteViews) {
            int color;
            if (this.mBuilder.getColor() != 0) {
                color = this.mBuilder.getColor();
            } else {
                color = this.mBuilder.mContext.getResources().getColor(R.color.notification_material_background_media_default_color);
            }
            remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", color);
        }
    }

    private static class Api24Impl {
        private Api24Impl() {
        }

        public static Notification.DecoratedMediaCustomViewStyle createDecoratedMediaCustomViewStyle() {
            return new Notification.DecoratedMediaCustomViewStyle();
        }
    }

    private static class Api34Impl {
        private Api34Impl() {
        }

        public static Notification.MediaStyle setRemotePlaybackInfo(Notification.MediaStyle mediaStyle, CharSequence charSequence, int i, PendingIntent pendingIntent) {
            mediaStyle.setRemotePlaybackInfo(charSequence, i, pendingIntent);
            return mediaStyle;
        }
    }
}
