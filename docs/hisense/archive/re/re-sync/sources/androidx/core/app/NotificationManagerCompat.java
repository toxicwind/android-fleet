package androidx.core.app;

import a.a.a.a.a;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayDeque;
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

    public static class NotifyTask implements Task {
        public final int id;
        public final Notification notif;
        public final String packageName;
        public final String tag;

        public NotifyTask(String str, int i, String str2, Notification notification) {
            this.packageName = str;
            this.id = i;
            this.tag = str2;
            this.notif = notification;
        }

        @Override // androidx.core.app.NotificationManagerCompat.Task
        public void send(a aVar) {
            aVar.notify(this.packageName, this.id, this.tag, this.notif);
        }

        public String toString() {
            return "NotifyTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + "]";
        }
    }

    public static class ServiceConnectedEvent {
        public final ComponentName componentName;
        public final IBinder iBinder;

        public ServiceConnectedEvent(ComponentName componentName, IBinder iBinder) {
            this.componentName = componentName;
            this.iBinder = iBinder;
        }
    }

    public static class SideChannelManager implements Handler.Callback, ServiceConnection {
        public static final int MSG_QUEUE_TASK = 0;
        public static final int MSG_RETRY_LISTENER_QUEUE = 3;
        public static final int MSG_SERVICE_CONNECTED = 1;
        public static final int MSG_SERVICE_DISCONNECTED = 2;
        public final Context mContext;
        public final Handler mHandler;
        public final HandlerThread mHandlerThread;
        public final Map<ComponentName, ListenerRecord> mRecordMap = new HashMap();
        public Set<String> mCachedEnabledPackages = new HashSet();

        public static class ListenerRecord {
            public final ComponentName componentName;
            public a service;
            public boolean bound = false;
            public ArrayDeque<Task> taskQueue = new ArrayDeque<>();
            public int retryCount = 0;

            public ListenerRecord(ComponentName componentName) {
                this.componentName = componentName;
            }
        }

        public SideChannelManager(Context context) {
            this.mContext = context;
            HandlerThread handlerThread = new HandlerThread("NotificationManagerCompat");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper(), this);
        }

        private boolean ensureServiceBound(ListenerRecord listenerRecord) {
            if (listenerRecord.bound) {
                return true;
            }
            boolean zBindService = this.mContext.bindService(new Intent(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL).setComponent(listenerRecord.componentName), this, 33);
            listenerRecord.bound = zBindService;
            if (zBindService) {
                listenerRecord.retryCount = 0;
            } else {
                StringBuilder sbC = b.a.a.a.a.c("Unable to bind to listener ");
                sbC.append(listenerRecord.componentName);
                Log.w(NotificationManagerCompat.TAG, sbC.toString());
                this.mContext.unbindService(this);
            }
            return listenerRecord.bound;
        }

        private void ensureServiceUnbound(ListenerRecord listenerRecord) {
            if (listenerRecord.bound) {
                this.mContext.unbindService(this);
                listenerRecord.bound = false;
            }
            listenerRecord.service = null;
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
                listenerRecord.service = a.AbstractBinderC0000a.asInterface(iBinder);
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

        private void processListenerQueue(ListenerRecord listenerRecord) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                StringBuilder sbC = b.a.a.a.a.c("Processing component ");
                sbC.append(listenerRecord.componentName);
                sbC.append(", ");
                sbC.append(listenerRecord.taskQueue.size());
                sbC.append(" queued tasks");
                Log.d(NotificationManagerCompat.TAG, sbC.toString());
            }
            if (listenerRecord.taskQueue.isEmpty()) {
                return;
            }
            if (!ensureServiceBound(listenerRecord) || listenerRecord.service == null) {
                scheduleListenerRetry(listenerRecord);
                return;
            }
            while (true) {
                Task taskPeek = listenerRecord.taskQueue.peek();
                if (taskPeek == null) {
                    break;
                }
                try {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        Log.d(NotificationManagerCompat.TAG, "Sending task " + taskPeek);
                    }
                    taskPeek.send(listenerRecord.service);
                    listenerRecord.taskQueue.remove();
                } catch (DeadObjectException unused) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        StringBuilder sbC2 = b.a.a.a.a.c("Remote service has died: ");
                        sbC2.append(listenerRecord.componentName);
                        Log.d(NotificationManagerCompat.TAG, sbC2.toString());
                    }
                } catch (RemoteException e) {
                    StringBuilder sbC3 = b.a.a.a.a.c("RemoteException communicating with ");
                    sbC3.append(listenerRecord.componentName);
                    Log.w(NotificationManagerCompat.TAG, sbC3.toString(), e);
                }
            }
            if (listenerRecord.taskQueue.isEmpty()) {
                return;
            }
            scheduleListenerRetry(listenerRecord);
        }

        private void scheduleListenerRetry(ListenerRecord listenerRecord) {
            if (this.mHandler.hasMessages(3, listenerRecord.componentName)) {
                return;
            }
            int i = listenerRecord.retryCount + 1;
            listenerRecord.retryCount = i;
            if (i > 6) {
                StringBuilder sbC = b.a.a.a.a.c("Giving up on delivering ");
                sbC.append(listenerRecord.taskQueue.size());
                sbC.append(" tasks to ");
                sbC.append(listenerRecord.componentName);
                sbC.append(" after ");
                sbC.append(listenerRecord.retryCount);
                sbC.append(" retries");
                Log.w(NotificationManagerCompat.TAG, sbC.toString());
                listenerRecord.taskQueue.clear();
                return;
            }
            int i2 = (1 << (i - 1)) * 1000;
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                Log.d(NotificationManagerCompat.TAG, "Scheduling retry for " + i2 + " ms");
            }
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(3, listenerRecord.componentName), i2);
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
                    ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                    ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
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
                        Log.d(NotificationManagerCompat.TAG, "Adding listener record for " + componentName2);
                    }
                    this.mRecordMap.put(componentName2, new ListenerRecord(componentName2));
                }
            }
            Iterator<Map.Entry<ComponentName, ListenerRecord>> it = this.mRecordMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<ComponentName, ListenerRecord> next = it.next();
                if (!hashSet.contains(next.getKey())) {
                    if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                        StringBuilder sbC = b.a.a.a.a.c("Removing listener record for ");
                        sbC.append(next.getKey());
                        Log.d(NotificationManagerCompat.TAG, sbC.toString());
                    }
                    ensureServiceUnbound(next.getValue());
                    it.remove();
                }
            }
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                handleQueueTask((Task) message.obj);
                return true;
            }
            if (i == 1) {
                ServiceConnectedEvent serviceConnectedEvent = (ServiceConnectedEvent) message.obj;
                handleServiceConnected(serviceConnectedEvent.componentName, serviceConnectedEvent.iBinder);
                return true;
            }
            if (i == 2) {
                handleServiceDisconnected((ComponentName) message.obj);
                return true;
            }
            if (i != 3) {
                return false;
            }
            handleRetryListenerQueue((ComponentName) message.obj);
            return true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                Log.d(NotificationManagerCompat.TAG, "Connected to service " + componentName);
            }
            this.mHandler.obtainMessage(1, new ServiceConnectedEvent(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                Log.d(NotificationManagerCompat.TAG, "Disconnected from service " + componentName);
            }
            this.mHandler.obtainMessage(2, componentName).sendToTarget();
        }

        public void queueTask(Task task) {
            this.mHandler.obtainMessage(0, task).sendToTarget();
        }
    }

    public interface Task {
        void send(a aVar);
    }

    public NotificationManagerCompat(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
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
                    String[] strArrSplit = string.split(":", -1);
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
        return this.mNotificationManager.areNotificationsEnabled();
    }

    public void cancel(int i) {
        cancel(null, i);
    }

    public void cancelAll() {
        this.mNotificationManager.cancelAll();
    }

    public void createNotificationChannel(@NonNull NotificationChannel notificationChannel) {
        this.mNotificationManager.createNotificationChannel(notificationChannel);
    }

    public void createNotificationChannelGroup(@NonNull NotificationChannelGroup notificationChannelGroup) {
        this.mNotificationManager.createNotificationChannelGroup(notificationChannelGroup);
    }

    public void createNotificationChannelGroups(@NonNull List<NotificationChannelGroup> list) {
        this.mNotificationManager.createNotificationChannelGroups(list);
    }

    public void createNotificationChannels(@NonNull List<NotificationChannel> list) {
        this.mNotificationManager.createNotificationChannels(list);
    }

    public void deleteNotificationChannel(@NonNull String str) {
        this.mNotificationManager.deleteNotificationChannel(str);
    }

    public void deleteNotificationChannelGroup(@NonNull String str) {
        this.mNotificationManager.deleteNotificationChannelGroup(str);
    }

    public int getImportance() {
        return this.mNotificationManager.getImportance();
    }

    @Nullable
    public NotificationChannel getNotificationChannel(@NonNull String str) {
        return this.mNotificationManager.getNotificationChannel(str);
    }

    @Nullable
    public NotificationChannelGroup getNotificationChannelGroup(@NonNull String str) {
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mNotificationManager.getNotificationChannelGroup(str);
        }
        for (NotificationChannelGroup notificationChannelGroup : getNotificationChannelGroups()) {
            if (notificationChannelGroup.getId().equals(str)) {
                return notificationChannelGroup;
            }
        }
        return null;
    }

    @NonNull
    public List<NotificationChannelGroup> getNotificationChannelGroups() {
        return this.mNotificationManager.getNotificationChannelGroups();
    }

    @NonNull
    public List<NotificationChannel> getNotificationChannels() {
        return this.mNotificationManager.getNotificationChannels();
    }

    public void notify(int i, @NonNull Notification notification) {
        notify(null, i, notification);
    }

    public void cancel(@Nullable String str, int i) {
        this.mNotificationManager.cancel(str, i);
    }

    public void notify(@Nullable String str, int i, @NonNull Notification notification) {
        if (!useSideChannelForNotification(notification)) {
            this.mNotificationManager.notify(str, i, notification);
        } else {
            pushSideChannelQueue(new NotifyTask(this.mContext.getPackageName(), i, str, notification));
            this.mNotificationManager.cancel(str, i);
        }
    }

    public static class CancelTask implements Task {
        public final boolean all;
        public final int id;
        public final String packageName;
        public final String tag;

        public CancelTask(String str) {
            this.packageName = str;
            this.id = 0;
            this.tag = null;
            this.all = true;
        }

        @Override // androidx.core.app.NotificationManagerCompat.Task
        public void send(a aVar) {
            if (this.all) {
                aVar.cancelAll(this.packageName);
            } else {
                aVar.cancel(this.packageName, this.id, this.tag);
            }
        }

        public String toString() {
            return "CancelTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + ", all:" + this.all + "]";
        }

        public CancelTask(String str, int i, String str2) {
            this.packageName = str;
            this.id = i;
            this.tag = str2;
            this.all = false;
        }
    }
}
