package androidx.core.app;

import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.ArraySet;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    public RemoteViews mBigContentView;
    public final Notification.Builder mBuilder;
    public final NotificationCompat.Builder mBuilderCompat;
    public RemoteViews mContentView;
    public final Context mContext;
    public int mGroupAlertBehavior;
    public RemoteViews mHeadsUpContentView;
    public final List<Bundle> mActionExtrasList = new ArrayList();
    public final Bundle mExtras = new Bundle();

    public NotificationCompatBuilder(NotificationCompat.Builder b2) {
        this.mBuilderCompat = b2;
        Context context = b2.mContext;
        this.mContext = context;
        int i = Build.VERSION.SDK_INT;
        this.mBuilder = new Notification.Builder(context, b2.mChannelId);
        Notification notification = b2.mNotification;
        this.mBuilder.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, b2.mTickerView).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(b2.mContentTitle).setContentText(b2.mContentText).setContentInfo(b2.mContentInfo).setContentIntent(b2.mContentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(b2.mFullScreenIntent, (notification.flags & 128) != 0).setLargeIcon(b2.mLargeIcon).setNumber(b2.mNumber).setProgress(b2.mProgressMax, b2.mProgress, b2.mProgressIndeterminate);
        int i2 = Build.VERSION.SDK_INT;
        this.mBuilder.setSubText(b2.mSubText).setUsesChronometer(b2.mUseChronometer).setPriority(b2.mPriority);
        Iterator<NotificationCompat.Action> it = b2.mActions.iterator();
        while (it.hasNext()) {
            addAction(it.next());
        }
        Bundle bundle = b2.mExtras;
        if (bundle != null) {
            this.mExtras.putAll(bundle);
        }
        int i3 = Build.VERSION.SDK_INT;
        this.mContentView = b2.mContentView;
        this.mBigContentView = b2.mBigContentView;
        this.mBuilder.setShowWhen(b2.mShowWhen);
        int i4 = Build.VERSION.SDK_INT;
        this.mBuilder.setLocalOnly(b2.mLocalOnly).setGroup(b2.mGroupKey).setGroupSummary(b2.mGroupSummary).setSortKey(b2.mSortKey);
        this.mGroupAlertBehavior = b2.mGroupAlertBehavior;
        int i5 = Build.VERSION.SDK_INT;
        this.mBuilder.setCategory(b2.mCategory).setColor(b2.mColor).setVisibility(b2.mVisibility).setPublicVersion(b2.mPublicVersion).setSound(notification.sound, notification.audioAttributes);
        List listCombineLists = Build.VERSION.SDK_INT < 28 ? combineLists(getPeople(b2.mPersonList), b2.mPeople) : b2.mPeople;
        if (listCombineLists != null && !listCombineLists.isEmpty()) {
            Iterator it2 = listCombineLists.iterator();
            while (it2.hasNext()) {
                this.mBuilder.addPerson((String) it2.next());
            }
        }
        this.mHeadsUpContentView = b2.mHeadsUpContentView;
        if (b2.mInvisibleActions.size() > 0) {
            Bundle bundle2 = b2.getExtras().getBundle(NotificationCompat.CarExtender.EXTRA_CAR_EXTENDER);
            bundle2 = bundle2 == null ? new Bundle() : bundle2;
            Bundle bundle3 = new Bundle(bundle2);
            Bundle bundle4 = new Bundle();
            for (int i6 = 0; i6 < b2.mInvisibleActions.size(); i6++) {
                bundle4.putBundle(Integer.toString(i6), NotificationCompatJellybean.getBundleForAction(b2.mInvisibleActions.get(i6)));
            }
            bundle2.putBundle(NotificationCompat.CarExtender.EXTRA_INVISIBLE_ACTIONS, bundle4);
            bundle3.putBundle(NotificationCompat.CarExtender.EXTRA_INVISIBLE_ACTIONS, bundle4);
            b2.getExtras().putBundle(NotificationCompat.CarExtender.EXTRA_CAR_EXTENDER, bundle2);
            this.mExtras.putBundle(NotificationCompat.CarExtender.EXTRA_CAR_EXTENDER, bundle3);
        }
        int i7 = Build.VERSION.SDK_INT;
        Icon icon = b2.mSmallIcon;
        if (icon != null) {
            this.mBuilder.setSmallIcon(icon);
        }
        int i8 = Build.VERSION.SDK_INT;
        this.mBuilder.setExtras(b2.mExtras).setRemoteInputHistory(b2.mRemoteInputHistory);
        RemoteViews remoteViews = b2.mContentView;
        if (remoteViews != null) {
            this.mBuilder.setCustomContentView(remoteViews);
        }
        RemoteViews remoteViews2 = b2.mBigContentView;
        if (remoteViews2 != null) {
            this.mBuilder.setCustomBigContentView(remoteViews2);
        }
        RemoteViews remoteViews3 = b2.mHeadsUpContentView;
        if (remoteViews3 != null) {
            this.mBuilder.setCustomHeadsUpContentView(remoteViews3);
        }
        int i9 = Build.VERSION.SDK_INT;
        this.mBuilder.setBadgeIconType(b2.mBadgeIcon).setSettingsText(b2.mSettingsText).setShortcutId(b2.mShortcutId).setTimeoutAfter(b2.mTimeout).setGroupAlertBehavior(b2.mGroupAlertBehavior);
        if (b2.mColorizedSet) {
            this.mBuilder.setColorized(b2.mColorized);
        }
        if (!TextUtils.isEmpty(b2.mChannelId)) {
            this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            Iterator<Person> it3 = b2.mPersonList.iterator();
            while (it3.hasNext()) {
                this.mBuilder.addPerson(it3.next().toAndroidPerson());
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.mBuilder.setAllowSystemGeneratedContextualActions(b2.mAllowSystemGeneratedContextualActions);
            this.mBuilder.setBubbleMetadata(NotificationCompat.BubbleMetadata.toPlatform(b2.mBubbleMetadata));
            LocusIdCompat locusIdCompat = b2.mLocusId;
            if (locusIdCompat != null) {
                this.mBuilder.setLocusId(locusIdCompat.toLocusId());
            }
        }
        if (b2.mSilent) {
            if (this.mBuilderCompat.mGroupSummary) {
                this.mGroupAlertBehavior = 2;
            } else {
                this.mGroupAlertBehavior = 1;
            }
            this.mBuilder.setVibrate(null);
            this.mBuilder.setSound(null);
            notification.defaults &= -2;
            notification.defaults &= -3;
            this.mBuilder.setDefaults(notification.defaults);
            int i10 = Build.VERSION.SDK_INT;
            if (TextUtils.isEmpty(this.mBuilderCompat.mGroupKey)) {
                this.mBuilder.setGroup(NotificationCompat.GROUP_KEY_SILENT);
            }
            this.mBuilder.setGroupAlertBehavior(this.mGroupAlertBehavior);
        }
    }

    private void addAction(NotificationCompat.Action action) {
        int i = Build.VERSION.SDK_INT;
        IconCompat iconCompat = action.getIconCompat();
        int i2 = Build.VERSION.SDK_INT;
        Notification.Action.Builder builder = new Notification.Action.Builder(iconCompat != null ? iconCompat.toIcon() : null, action.getTitle(), action.getActionIntent());
        if (action.getRemoteInputs() != null) {
            for (android.app.RemoteInput remoteInput : RemoteInput.fromCompat(action.getRemoteInputs())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        Bundle bundle = action.getExtras() != null ? new Bundle(action.getExtras()) : new Bundle();
        bundle.putBoolean(NotificationCompatJellybean.EXTRA_ALLOW_GENERATED_REPLIES, action.getAllowGeneratedReplies());
        int i3 = Build.VERSION.SDK_INT;
        builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
        bundle.putInt(NotificationCompat.Action.EXTRA_SEMANTIC_ACTION, action.getSemanticAction());
        if (Build.VERSION.SDK_INT >= 28) {
            builder.setSemanticAction(action.getSemanticAction());
        }
        if (Build.VERSION.SDK_INT >= 29) {
            builder.setContextual(action.isContextual());
        }
        bundle.putBoolean(NotificationCompat.Action.EXTRA_SHOWS_USER_INTERFACE, action.getShowsUserInterface());
        builder.addExtras(bundle);
        this.mBuilder.addAction(builder.build());
    }

    @Nullable
    public static List<String> combineLists(@Nullable final List<String> first, @Nullable final List<String> second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        ArraySet arraySet = new ArraySet(second.size() + first.size());
        arraySet.addAll(first);
        arraySet.addAll(second);
        return new ArrayList(arraySet);
    }

    @Nullable
    public static List<String> getPeople(@Nullable final List<Person> people) {
        if (people == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(people.size());
        Iterator<Person> it = people.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().resolveToLegacyUri());
        }
        return arrayList;
    }

    private void removeSoundAndVibration(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults &= -2;
        notification.defaults &= -3;
    }

    public Notification build() {
        Bundle extras;
        RemoteViews remoteViewsMakeHeadsUpContentView;
        RemoteViews remoteViewsMakeBigContentView;
        NotificationCompat.Style style = this.mBuilderCompat.mStyle;
        if (style != null) {
            style.apply(this);
        }
        RemoteViews remoteViewsMakeContentView = style != null ? style.makeContentView(this) : null;
        Notification notificationBuildInternal = buildInternal();
        if (remoteViewsMakeContentView != null) {
            notificationBuildInternal.contentView = remoteViewsMakeContentView;
        } else {
            RemoteViews remoteViews = this.mBuilderCompat.mContentView;
            if (remoteViews != null) {
                notificationBuildInternal.contentView = remoteViews;
            }
        }
        int i = Build.VERSION.SDK_INT;
        if (style != null && (remoteViewsMakeBigContentView = style.makeBigContentView(this)) != null) {
            notificationBuildInternal.bigContentView = remoteViewsMakeBigContentView;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (style != null && (remoteViewsMakeHeadsUpContentView = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this)) != null) {
            notificationBuildInternal.headsUpContentView = remoteViewsMakeHeadsUpContentView;
        }
        int i3 = Build.VERSION.SDK_INT;
        if (style != null && (extras = NotificationCompat.getExtras(notificationBuildInternal)) != null) {
            style.addCompatExtras(extras);
        }
        return notificationBuildInternal;
    }

    public Notification buildInternal() {
        int i = Build.VERSION.SDK_INT;
        return this.mBuilder.build();
    }

    @Override // androidx.core.app.NotificationBuilderWithBuilderAccessor
    public Notification.Builder getBuilder() {
        return this.mBuilder;
    }

    public Context getContext() {
        return this.mContext;
    }
}
