package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.drawable.IconCompat;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(16)
public class NotificationCompatJellybean {
    public static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
    public static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
    public static final String KEY_ACTION_INTENT = "actionIntent";
    public static final String KEY_ALLOWED_DATA_TYPES = "allowedDataTypes";
    public static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
    public static final String KEY_CHOICES = "choices";
    public static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
    public static final String KEY_EXTRAS = "extras";
    public static final String KEY_ICON = "icon";
    public static final String KEY_LABEL = "label";
    public static final String KEY_REMOTE_INPUTS = "remoteInputs";
    public static final String KEY_RESULT_KEY = "resultKey";
    public static final String KEY_SEMANTIC_ACTION = "semanticAction";
    public static final String KEY_SHOWS_USER_INTERFACE = "showsUserInterface";
    public static final String KEY_TITLE = "title";
    public static final String TAG = "NotificationCompat";
    public static Field sActionIconField;
    public static Field sActionIntentField;
    public static Field sActionTitleField;
    public static boolean sActionsAccessFailed;
    public static Field sActionsField;
    public static Field sExtrasField;
    public static boolean sExtrasFieldAccessFailed;
    public static final Object sExtrasLock = new Object();
    public static final Object sActionsLock = new Object();

    public static SparseArray<Bundle> buildActionExtrasMap(List<Bundle> actionExtrasList) {
        int size = actionExtrasList.size();
        SparseArray<Bundle> sparseArray = null;
        for (int i = 0; i < size; i++) {
            Bundle bundle = actionExtrasList.get(i);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                sparseArray.put(i, bundle);
            }
        }
        return sparseArray;
    }

    public static boolean ensureActionReflectionReadyLocked() {
        if (sActionsAccessFailed) {
            return false;
        }
        try {
            if (sActionsField == null) {
                Class<?> cls = Class.forName("android.app.Notification$Action");
                sActionIconField = cls.getDeclaredField("icon");
                sActionTitleField = cls.getDeclaredField(KEY_TITLE);
                sActionIntentField = cls.getDeclaredField(KEY_ACTION_INTENT);
                sActionsField = Notification.class.getDeclaredField(NotificationCompat.WearableExtender.KEY_ACTIONS);
                sActionsField.setAccessible(true);
            }
        } catch (ClassNotFoundException e2) {
            Log.e(TAG, "Unable to access notification actions", e2);
            sActionsAccessFailed = true;
        } catch (NoSuchFieldException e3) {
            Log.e(TAG, "Unable to access notification actions", e3);
            sActionsAccessFailed = true;
        }
        return !sActionsAccessFailed;
    }

    public static RemoteInput fromBundle(Bundle data) {
        ArrayList<String> stringArrayList = data.getStringArrayList(KEY_ALLOWED_DATA_TYPES);
        HashSet hashSet = new HashSet();
        if (stringArrayList != null) {
            Iterator<String> it = stringArrayList.iterator();
            while (it.hasNext()) {
                hashSet.add(it.next());
            }
        }
        return new RemoteInput(data.getString(KEY_RESULT_KEY), data.getCharSequence(KEY_LABEL), data.getCharSequenceArray(KEY_CHOICES), data.getBoolean(KEY_ALLOW_FREE_FORM_INPUT), 0, data.getBundle("extras"), hashSet);
    }

    public static RemoteInput[] fromBundleArray(Bundle[] bundles) {
        if (bundles == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[bundles.length];
        for (int i = 0; i < bundles.length; i++) {
            remoteInputArr[i] = fromBundle(bundles[i]);
        }
        return remoteInputArr;
    }

    public static NotificationCompat.Action getAction(Notification notif, int actionIndex) {
        SparseArray sparseParcelableArray;
        synchronized (sActionsLock) {
            try {
                try {
                    Object[] actionObjectsLocked = getActionObjectsLocked(notif);
                    if (actionObjectsLocked != null) {
                        Object obj = actionObjectsLocked[actionIndex];
                        Bundle extras = getExtras(notif);
                        return readAction(sActionIconField.getInt(obj), (CharSequence) sActionTitleField.get(obj), (PendingIntent) sActionIntentField.get(obj), (extras == null || (sparseParcelableArray = extras.getSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS)) == null) ? null : (Bundle) sparseParcelableArray.get(actionIndex));
                    }
                } catch (IllegalAccessException e2) {
                    Log.e(TAG, "Unable to access notification actions", e2);
                    sActionsAccessFailed = true;
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static int getActionCount(Notification notif) {
        int length;
        synchronized (sActionsLock) {
            Object[] actionObjectsLocked = getActionObjectsLocked(notif);
            length = actionObjectsLocked != null ? actionObjectsLocked.length : 0;
        }
        return length;
    }

    public static NotificationCompat.Action getActionFromBundle(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("extras");
        return new NotificationCompat.Action(bundle.getInt("icon"), bundle.getCharSequence(KEY_TITLE), (PendingIntent) bundle.getParcelable(KEY_ACTION_INTENT), bundle.getBundle("extras"), fromBundleArray(getBundleArrayFromBundle(bundle, KEY_REMOTE_INPUTS)), fromBundleArray(getBundleArrayFromBundle(bundle, KEY_DATA_ONLY_REMOTE_INPUTS)), bundle2 != null ? bundle2.getBoolean(EXTRA_ALLOW_GENERATED_REPLIES, false) : false, bundle.getInt(KEY_SEMANTIC_ACTION), bundle.getBoolean(KEY_SHOWS_USER_INTERFACE), false);
    }

    public static Object[] getActionObjectsLocked(Notification notif) {
        synchronized (sActionsLock) {
            if (!ensureActionReflectionReadyLocked()) {
                return null;
            }
            try {
                return (Object[]) sActionsField.get(notif);
            } catch (IllegalAccessException e2) {
                Log.e(TAG, "Unable to access notification actions", e2);
                sActionsAccessFailed = true;
                return null;
            }
        }
    }

    public static Bundle[] getBundleArrayFromBundle(Bundle bundle, String key) {
        Parcelable[] parcelableArray = bundle.getParcelableArray(key);
        if ((parcelableArray instanceof Bundle[]) || parcelableArray == null) {
            return (Bundle[]) parcelableArray;
        }
        Bundle[] bundleArr = (Bundle[]) Arrays.copyOf(parcelableArray, parcelableArray.length, Bundle[].class);
        bundle.putParcelableArray(key, bundleArr);
        return bundleArr;
    }

    public static Bundle getBundleForAction(NotificationCompat.Action action) {
        Bundle bundle = new Bundle();
        IconCompat iconCompat = action.getIconCompat();
        bundle.putInt("icon", iconCompat != null ? iconCompat.getResId() : 0);
        bundle.putCharSequence(KEY_TITLE, action.getTitle());
        bundle.putParcelable(KEY_ACTION_INTENT, action.getActionIntent());
        Bundle bundle2 = action.getExtras() != null ? new Bundle(action.getExtras()) : new Bundle();
        bundle2.putBoolean(EXTRA_ALLOW_GENERATED_REPLIES, action.getAllowGeneratedReplies());
        bundle.putBundle("extras", bundle2);
        bundle.putParcelableArray(KEY_REMOTE_INPUTS, toBundleArray(action.getRemoteInputs()));
        bundle.putBoolean(KEY_SHOWS_USER_INTERFACE, action.getShowsUserInterface());
        bundle.putInt(KEY_SEMANTIC_ACTION, action.getSemanticAction());
        return bundle;
    }

    public static Bundle getExtras(Notification notif) {
        synchronized (sExtrasLock) {
            if (sExtrasFieldAccessFailed) {
                return null;
            }
            try {
                if (sExtrasField == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                        Log.e(TAG, "Notification.extras field is not of type Bundle");
                        sExtrasFieldAccessFailed = true;
                        return null;
                    }
                    declaredField.setAccessible(true);
                    sExtrasField = declaredField;
                }
                Bundle bundle = (Bundle) sExtrasField.get(notif);
                if (bundle == null) {
                    bundle = new Bundle();
                    sExtrasField.set(notif, bundle);
                }
                return bundle;
            } catch (IllegalAccessException e2) {
                Log.e(TAG, "Unable to access notification extras", e2);
                sExtrasFieldAccessFailed = true;
                return null;
            } catch (NoSuchFieldException e3) {
                Log.e(TAG, "Unable to access notification extras", e3);
                sExtrasFieldAccessFailed = true;
                return null;
            }
        }
    }

    public static NotificationCompat.Action readAction(int icon, CharSequence title, PendingIntent actionIntent, Bundle extras) {
        RemoteInput[] remoteInputArrFromBundleArray;
        RemoteInput[] remoteInputArrFromBundleArray2;
        boolean z;
        if (extras != null) {
            remoteInputArrFromBundleArray = fromBundleArray(getBundleArrayFromBundle(extras, NotificationCompatExtras.EXTRA_REMOTE_INPUTS));
            remoteInputArrFromBundleArray2 = fromBundleArray(getBundleArrayFromBundle(extras, EXTRA_DATA_ONLY_REMOTE_INPUTS));
            z = extras.getBoolean(EXTRA_ALLOW_GENERATED_REPLIES);
        } else {
            remoteInputArrFromBundleArray = null;
            remoteInputArrFromBundleArray2 = null;
            z = false;
        }
        return new NotificationCompat.Action(icon, title, actionIntent, extras, remoteInputArrFromBundleArray, remoteInputArrFromBundleArray2, z, 0, true, false);
    }

    public static Bundle toBundle(RemoteInput remoteInput) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_RESULT_KEY, remoteInput.getResultKey());
        bundle.putCharSequence(KEY_LABEL, remoteInput.getLabel());
        bundle.putCharSequenceArray(KEY_CHOICES, remoteInput.getChoices());
        bundle.putBoolean(KEY_ALLOW_FREE_FORM_INPUT, remoteInput.getAllowFreeFormInput());
        bundle.putBundle("extras", remoteInput.getExtras());
        Set<String> allowedDataTypes = remoteInput.getAllowedDataTypes();
        if (allowedDataTypes != null && !allowedDataTypes.isEmpty()) {
            ArrayList<String> arrayList = new ArrayList<>(allowedDataTypes.size());
            Iterator<String> it = allowedDataTypes.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            bundle.putStringArrayList(KEY_ALLOWED_DATA_TYPES, arrayList);
        }
        return bundle;
    }

    public static Bundle[] toBundleArray(RemoteInput[] remoteInputs) {
        if (remoteInputs == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[remoteInputs.length];
        for (int i = 0; i < remoteInputs.length; i++) {
            bundleArr[i] = toBundle(remoteInputs[i]);
        }
        return bundleArr;
    }

    public static Bundle writeActionAndGetExtras(Notification.Builder builder, NotificationCompat.Action action) {
        IconCompat iconCompat = action.getIconCompat();
        builder.addAction(iconCompat != null ? iconCompat.getResId() : 0, action.getTitle(), action.getActionIntent());
        Bundle bundle = new Bundle(action.getExtras());
        if (action.getRemoteInputs() != null) {
            bundle.putParcelableArray(NotificationCompatExtras.EXTRA_REMOTE_INPUTS, toBundleArray(action.getRemoteInputs()));
        }
        if (action.getDataOnlyRemoteInputs() != null) {
            bundle.putParcelableArray(EXTRA_DATA_ONLY_REMOTE_INPUTS, toBundleArray(action.getDataOnlyRemoteInputs()));
        }
        bundle.putBoolean(EXTRA_ALLOW_GENERATED_REPLIES, action.getAllowGeneratedReplies());
        return bundle;
    }
}
