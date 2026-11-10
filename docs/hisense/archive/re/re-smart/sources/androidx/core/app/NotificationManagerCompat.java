package androidx.core.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.installations.Utils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class NotificationManagerCompat {
    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    public static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    public static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
    public static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    public static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    public static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    public static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    public static final String TAG = "NotifManCompat";

    @GuardedBy("sEnabledNotificationListenersLock")
    public static String sEnabledNotificationListeners;

    @GuardedBy("sLock")
    public static SideChannelManager sSideChannelManager;
    public final Context mContext;
    public final NotificationManager mNotificationManager;
    public static final Object sEnabledNotificationListenersLock = new Object();

    @GuardedBy("sEnabledNotificationListenersLock")
    public static Set<String> sEnabledNotificationListenerPackages = new HashSet();
    public static final Object sLock = new Object();

    private static class NotifyTask implements Task {
        public final int id;
        public final Notification notif;
        public final String packageName;
        public final String tag;

        public NotifyTask(String packageName, int id, String tag, Notification notif) {
            this.packageName = packageName;
            this.id = id;
            this.tag = tag;
            this.notif = notif;
        }

        @Override // androidx.core.app.NotificationManagerCompat.Task
        public void send(INotificationSideChannel service) {
            service.notify(this.packageName, this.id, this.tag, this.notif);
        }

        @NonNull
        public String toString() {
            StringBuilder sb = new StringBuilder("NotifyTask[");
            sb.append("packageName:");
            sb.append(this.packageName);
            sb.append(", id:");
            sb.append(this.id);
            sb.append(", tag:");
            return a.a(sb, this.tag, "]");
        }
    }

    private static class ServiceConnectedEvent {
        public final ComponentName componentName;
        public final IBinder iBinder;

        public ServiceConnectedEvent(ComponentName componentName, final IBinder iBinder) {
            this.componentName = componentName;
            this.iBinder = iBinder;
        }
    }

    private static class SideChannelManager implements Handler.Callback, ServiceConnection {
        public static final int MSG_QUEUE_TASK = 0;
        public static final int MSG_RETRY_LISTENER_QUEUE = 3;
        public static final int MSG_SERVICE_CONNECTED = 1;
        public static final int MSG_SERVICE_DISCONNECTED = 2;
        public final Context mContext;
        public final Handler mHandler;
        public final Map<ComponentName, ListenerRecord> mRecordMap = new HashMap();
        public Set<String> mCachedEnabledPackages = new HashSet();
        public final HandlerThread mHandlerThread = new HandlerThread("NotificationManagerCompat");

        private static class ListenerRecord {
            public final ComponentName componentName;
            public INotificationSideChannel service;
            public boolean bound = false;
            public ArrayDeque<Task> taskQueue = new ArrayDeque<>();
            public int retryCount = 0;

            public ListenerRecord(ComponentName componentName) {
                this.componentName = componentName;
            }
        }

        public SideChannelManager(Context context) {
            this.mContext = context;
            this.mHandlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper(), this);
        }

        private boolean ensureServiceBound(ListenerRecord record) {
            if (record.bound) {
                return true;
            }
            record.bound = this.mContext.bindService(new Intent(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL).setComponent(record.componentName), this, 33);
            if (record.bound) {
                record.retryCount = 0;
            } else {
                StringBuilder sbA = a.a("Unable to bind to listener ");
                sbA.append(record.componentName);
                Log.w(NotificationManagerCompat.TAG, sbA.toString());
                this.mContext.unbindService(this);
            }
            return record.bound;
        }

        private void ensureServiceUnbound(ListenerRecord record) {
            if (record.bound) {
                this.mContext.unbindService(this);
                record.bound = false;
            }
            record.service = null;
        }

        private void handleQueueTask(Task task) {
            updateListenerMap();
            for (ListenerRecord listenerRecord : this.mRecordMap.values()) {
                listenerRecord.taskQueue.add(task);
                processListenerQueue(listenerRecord);
            }
        }

        private void handleRetryListenerQueue(ComponentName componentName) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                processListenerQueue(listenerRecord);
            }
        }

        private void handleServiceConnected(ComponentName componentName, IBinder iBinder) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                listenerRecord.service = INotificationSideChannel.Stub.asInterface(iBinder);
                listenerRecord.retryCount = 0;
                processListenerQueue(listenerRecord);
            }
        }

        private void handleServiceDisconnected(ComponentName componentName) {
            ListenerRecord listenerRecord = this.mRecordMap.get(componentName);
            if (listenerRecord != null) {
                ensureServiceUnbound(listenerRecord);
            }
        }

        private void processListenerQueue(ListenerRecord record) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                StringBuilder sbA = a.a("Processing component ");
                sbA.append(record.componentName);
                sbA.append(", ");
                sbA.append(record.taskQueue.size());
                sbA.append(" queued tasks");
                Log.d(NotificationManagerCompat.TAG, sbA.toString());
            }
            if (record.taskQueue.isEmpty()) {
                return;
            }
            if (!ensureServiceBound(record) || record.service == null) {
                scheduleListenerRetry(record);
                return;
            }
            while (true) {
                Task taskPeek = record.taskQueue.peek();
                if (taskPeek == null) {
                    break;
                }
                try {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        Log.d(NotificationManagerCompat.TAG, "Sending task " + taskPeek);
                    }
                    taskPeek.send(record.service);
                    record.taskQueue.remove();
                } catch (DeadObjectException unused) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        StringBuilder sbA2 = a.a("Remote service has died: ");
                        sbA2.append(record.componentName);
                        Log.d(NotificationManagerCompat.TAG, sbA2.toString());
                    }
                } catch (RemoteException e2) {
                    StringBuilder sbA3 = a.a("RemoteException communicating with ");
                    sbA3.append(record.componentName);
                    Log.w(NotificationManagerCompat.TAG, sbA3.toString(), e2);
                }
            }
            if (record.taskQueue.isEmpty()) {
                return;
            }
            scheduleListenerRetry(record);
        }

        private void scheduleListenerRetry(ListenerRecord record) {
            if (this.mHandler.hasMessages(3, record.componentName)) {
                return;
            }
            record.retryCount++;
            int i = record.retryCount;
            if (i <= 6) {
                int i2 = (1 << (i - 1)) * 1000;
                if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                    a.a("Scheduling retry for ", i2, " ms", NotificationManagerCompat.TAG);
                }
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(3, record.componentName), i2);
                return;
            }
            StringBuilder sbA = a.a("Giving up on delivering ");
            sbA.append(record.taskQueue.size());
            sbA.append(" tasks to ");
            sbA.append(record.componentName);
            sbA.append(" after ");
            sbA.append(record.retryCount);
            sbA.append(" retries");
            Log.w(NotificationManagerCompat.TAG, sbA.toString());
            record.taskQueue.clear();
        }

        private void updateListenerMap() {
            Set<String> enabledListenerPackages = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
            if (enabledListenerPackages.equals(this.mCachedEnabledPackages)) {
                return;
            }
            this.mCachedEnabledPackages = enabledListenerPackages;
            List<ResolveInfo> listQueryIntentServices = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL), 0);
            HashSet<ComponentName> hashSet = new HashSet();
            for (ResolveInfo resolveInfo : listQueryIntentServices) {
                if (enabledListenerPackages.contains(resolveInfo.serviceInfo.packageName)) {
                    ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        Log.w(NotificationManagerCompat.TAG, "Permission present on component " + componentName + ", not adding listener record.");
                    } else {
                        hashSet.add(componentName);
                    }
                }
            }
            for (ComponentName componentName2 : hashSet) {
                if (!this.mRecordMap.containsKey(componentName2)) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        a.b("Adding listener record for ", componentName2, NotificationManagerCompat.TAG);
                    }
                    this.mRecordMap.put(componentName2, new ListenerRecord(componentName2));
                }
            }
            Iterator<Map.Entry<ComponentName, ListenerRecord>> it = this.mRecordMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<ComponentName, ListenerRecord> next = it.next();
                if (!hashSet.contains(next.getKey())) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        StringBuilder sbA = a.a("Removing listener record for ");
                        sbA.append(next.getKey());
                        Log.d(NotificationManagerCompat.TAG, sbA.toString());
                    }
                    ensureServiceUnbound(next.getValue());
                    it.remove();
                }
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            int i = msg.what;
            if (i == 0) {
                handleQueueTask((Task) msg.obj);
                return true;
            }
            if (i == 1) {
                ServiceConnectedEvent serviceConnectedEvent = (ServiceConnectedEvent) msg.obj;
                handleServiceConnected(serviceConnectedEvent.componentName, serviceConnectedEvent.iBinder);
                return true;
            }
            if (i == 2) {
                handleServiceDisconnected((ComponentName) msg.obj);
                return true;
            }
            if (i != 3) {
                return false;
            }
            handleRetryListenerQueue((ComponentName) msg.obj);
            return true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                a.b("Connected to service ", componentName, NotificationManagerCompat.TAG);
            }
            this.mHandler.obtainMessage(1, new ServiceConnectedEvent(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                a.b("Disconnected from service ", componentName, NotificationManagerCompat.TAG);
            }
            this.mHandler.obtainMessage(2, componentName).sendToTarget();
        }

        public void queueTask(Task task) {
            this.mHandler.obtainMessage(0, task).sendToTarget();
        }
    }

    private interface Task {
        void send(INotificationSideChannel service);
    }

    public NotificationManagerCompat(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) this.mContext.getSystemService("notification");
    }

    @NonNull
    public static NotificationManagerCompat from(@NonNull Context context) {
        return new NotificationManagerCompat(context);
    }

    @NonNull
    public static Set<String> getEnabledListenerPackages(@NonNull Context context) {
        Set<String> set;
        String string = Settings.Secure.getString(context.getContentResolver(), SETTING_ENABLED_NOTIFICATION_LISTENERS);
        synchronized (sEnabledNotificationListenersLock) {
            if (string != null) {
                if (!string.equals(sEnabledNotificationListeners)) {
                    String[] strArrSplit = string.split(Utils.APP_ID_IDENTIFICATION_SUBSTRING, -1);
                    HashSet hashSet = new HashSet(strArrSplit.length);
                    for (String str : strArrSplit) {
                        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                        if (componentNameUnflattenFromString != null) {
                            hashSet.add(componentNameUnflattenFromString.getPackageName());
                        }
                    }
                    sEnabledNotificationListenerPackages = hashSet;
                    sEnabledNotificationListeners = string;
                }
                set = sEnabledNotificationListenerPackages;
            } else {
                set = sEnabledNotificationListenerPackages;
            }
            throw th;
        }
        return set;
    }

    private void pushSideChannelQueue(Task task) {
        synchronized (sLock) {
            if (sSideChannelManager == null) {
                sSideChannelManager = new SideChannelManager(this.mContext.getApplicationContext());
            }
            sSideChannelManager.queueTask(task);
        }
    }

    public static boolean useSideChannelForNotification(Notification notification) {
        Bundle extras = NotificationCompat.getExtras(notification);
        return extras != null && extras.getBoolean(EXTRA_USE_SIDE_CHANNEL);
    }

    public boolean areNotificationsEnabled() {
        int i = Build.VERSION.SDK_INT;
        return this.mNotificationManager.areNotificationsEnabled();
    }

    public void cancel(int id) {
        cancel(null, id);
    }

    public void cancelAll() {
        this.mNotificationManager.cancelAll();
        int i = Build.VERSION.SDK_INT;
    }

    public void createNotificationChannel(@NonNull NotificationChannel channel) {
        int i = Build.VERSION.SDK_INT;
        this.mNotificationManager.createNotificationChannel(channel);
    }

    public void createNotificationChannelGroup(@NonNull NotificationChannelGroup group) {
        int i = Build.VERSION.SDK_INT;
        this.mNotificationManager.createNotificationChannelGroup(group);
    }

    public void createNotificationChannelGroups(@NonNull List<NotificationChannelGroup> groups) {
        int i = Build.VERSION.SDK_INT;
        this.mNotificationManager.createNotificationChannelGroups(groups);
    }

    public void createNotificationChannelGroupsCompat(@NonNull List<NotificationChannelGroupCompat> groups) {
        int i = Build.VERSION.SDK_INT;
        if (groups.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(groups.size());
        Iterator<NotificationChannelGroupCompat> it = groups.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getNotificationChannelGroup());
        }
        this.mNotificationManager.createNotificationChannelGroups(arrayList);
    }

    public void createNotificationChannels(@NonNull List<NotificationChannel> channels) {
        int i = Build.VERSION.SDK_INT;
        this.mNotificationManager.createNotificationChannels(channels);
    }

    public void createNotificationChannelsCompat(@NonNull List<NotificationChannelCompat> channels) {
        int i = Build.VERSION.SDK_INT;
        if (channels.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(channels.size());
        Iterator<NotificationChannelCompat> it = channels.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getNotificationChannel());
        }
        this.mNotificationManager.createNotificationChannels(arrayList);
    }

    public void deleteNotificationChannel(@NonNull String channelId) {
        int i = Build.VERSION.SDK_INT;
        this.mNotificationManager.deleteNotificationChannel(channelId);
    }

    public void deleteNotificationChannelGroup(@NonNull String groupId) {
        int i = Build.VERSION.SDK_INT;
        this.mNotificationManager.deleteNotificationChannelGroup(groupId);
    }

    public void deleteUnlistedNotificationChannels(@NonNull Collection<String> channelIds) {
        int i = Build.VERSION.SDK_INT;
        for (NotificationChannel notificationChannel : this.mNotificationManager.getNotificationChannels()) {
            if (!channelIds.contains(notificationChannel.getId()) && (Build.VERSION.SDK_INT < 30 || !channelIds.contains(notificationChannel.getParentChannelId()))) {
                this.mNotificationManager.deleteNotificationChannel(notificationChannel.getId());
            }
        }
    }

    public int getImportance() {
        int i = Build.VERSION.SDK_INT;
        return this.mNotificationManager.getImportance();
    }

    @Nullable
    public NotificationChannel getNotificationChannel(@NonNull String channelId) {
        int i = Build.VERSION.SDK_INT;
        return this.mNotificationManager.getNotificationChannel(channelId);
    }

    @Nullable
    public NotificationChannelCompat getNotificationChannelCompat(@NonNull String channelId) {
        int i = Build.VERSION.SDK_INT;
        NotificationChannel notificationChannel = getNotificationChannel(channelId);
        if (notificationChannel != null) {
            return new NotificationChannelCompat(notificationChannel);
        }
        return null;
    }

    @Nullable
    public NotificationChannelGroup getNotificationChannelGroup(@NonNull String channelGroupId) {
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mNotificationManager.getNotificationChannelGroup(channelGroupId);
        }
        for (NotificationChannelGroup notificationChannelGroup : getNotificationChannelGroups()) {
            if (notificationChannelGroup.getId().equals(channelGroupId)) {
                return notificationChannelGroup;
            }
        }
        return null;
    }

    @Nullable
    public NotificationChannelGroupCompat getNotificationChannelGroupCompat(@NonNull String channelGroupId) {
        if (Build.VERSION.SDK_INT >= 28) {
            NotificationChannelGroup notificationChannelGroup = getNotificationChannelGroup(channelGroupId);
            if (notificationChannelGroup != null) {
                return new NotificationChannelGroupCompat(notificationChannelGroup);
            }
            return null;
        }
        NotificationChannelGroup notificationChannelGroup2 = getNotificationChannelGroup(channelGroupId);
        if (notificationChannelGroup2 != null) {
            return new NotificationChannelGroupCompat(notificationChannelGroup2, getNotificationChannels());
        }
        return null;
    }

    @NonNull
    public List<NotificationChannelGroup> getNotificationChannelGroups() {
        int i = Build.VERSION.SDK_INT;
        return this.mNotificationManager.getNotificationChannelGroups();
    }

    @NonNull
    public List<NotificationChannelGroupCompat> getNotificationChannelGroupsCompat() {
        int i = Build.VERSION.SDK_INT;
        List<NotificationChannelGroup> notificationChannelGroups = getNotificationChannelGroups();
        if (notificationChannelGroups.isEmpty()) {
            return Collections.emptyList();
        }
        List<NotificationChannel> listEmptyList = Build.VERSION.SDK_INT >= 28 ? Collections.emptyList() : getNotificationChannels();
        ArrayList arrayList = new ArrayList(notificationChannelGroups.size());
        for (NotificationChannelGroup notificationChannelGroup : notificationChannelGroups) {
            if (Build.VERSION.SDK_INT >= 28) {
                arrayList.add(new NotificationChannelGroupCompat(notificationChannelGroup));
            } else {
                arrayList.add(new NotificationChannelGroupCompat(notificationChannelGroup, listEmptyList));
            }
        }
        return arrayList;
    }

    @NonNull
    public List<NotificationChannel> getNotificationChannels() {
        int i = Build.VERSION.SDK_INT;
        return this.mNotificationManager.getNotificationChannels();
    }

    @NonNull
    public List<NotificationChannelCompat> getNotificationChannelsCompat() {
        int i = Build.VERSION.SDK_INT;
        List<NotificationChannel> notificationChannels = getNotificationChannels();
        if (notificationChannels.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(notificationChannels.size());
        Iterator<NotificationChannel> it = notificationChannels.iterator();
        while (it.hasNext()) {
            arrayList.add(new NotificationChannelCompat(it.next()));
        }
        return arrayList;
    }

    public void notify(int id, @NonNull Notification notification) {
        notify(null, id, notification);
    }

    public void cancel(@Nullable String tag, int id) {
        this.mNotificationManager.cancel(tag, id);
        int i = Build.VERSION.SDK_INT;
    }

    public void notify(@Nullable String tag, int id, @NonNull Notification notification) {
        if (!useSideChannelForNotification(notification)) {
            this.mNotificationManager.notify(tag, id, notification);
        } else {
            pushSideChannelQueue(new NotifyTask(this.mContext.getPackageName(), id, tag, notification));
            this.mNotificationManager.cancel(tag, id);
        }
    }

    public void createNotificationChannel(@NonNull NotificationChannelCompat channel) {
        createNotificationChannel(channel.getNotificationChannel());
    }

    public void createNotificationChannelGroup(@NonNull NotificationChannelGroupCompat group) {
        createNotificationChannelGroup(group.getNotificationChannelGroup());
    }

    @Nullable
    public NotificationChannel getNotificationChannel(@NonNull String channelId, @NonNull String conversationId) {
        if (Build.VERSION.SDK_INT >= 30) {
            return this.mNotificationManager.getNotificationChannel(channelId, conversationId);
        }
        return getNotificationChannel(channelId);
    }

    @Nullable
    public NotificationChannelCompat getNotificationChannelCompat(@NonNull String channelId, @NonNull String conversationId) {
        int i = Build.VERSION.SDK_INT;
        NotificationChannel notificationChannel = getNotificationChannel(channelId, conversationId);
        if (notificationChannel != null) {
            return new NotificationChannelCompat(notificationChannel);
        }
        return null;
    }

    private static class CancelTask implements Task {
        public final boolean all;
        public final int id;
        public final String packageName;
        public final String tag;

        public CancelTask(String packageName) {
            this.packageName = packageName;
            this.id = 0;
            this.tag = null;
            this.all = true;
        }

        @Override // androidx.core.app.NotificationManagerCompat.Task
        public void send(INotificationSideChannel service) {
            if (this.all) {
                service.cancelAll(this.packageName);
            } else {
                service.cancel(this.packageName, this.id, this.tag);
            }
        }

        @NonNull
        public String toString() {
            return "CancelTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + ", all:" + this.all + "]";
        }

        public CancelTask(String packageName, int id, String tag) {
            this.packageName = packageName;
            this.id = id;
            this.tag = tag;
            this.all = false;
        }
    }
}
