package androidx.core.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class TaskStackBuilder implements Iterable<Intent> {
    public static final String TAG = "TaskStackBuilder";
    public final ArrayList<Intent> mIntents = new ArrayList<>();
    public final Context mSourceContext;

    public interface SupportParentable {
        @Nullable
        Intent getSupportParentActivityIntent();
    }

    public TaskStackBuilder(Context a2) {
        this.mSourceContext = a2;
    }

    @NonNull
    public static TaskStackBuilder create(@NonNull Context context) {
        return new TaskStackBuilder(context);
    }

    @Deprecated
    public static TaskStackBuilder from(Context context) {
        return new TaskStackBuilder(context);
    }

    @NonNull
    public TaskStackBuilder addNextIntent(@NonNull Intent nextIntent) {
        this.mIntents.add(nextIntent);
        return this;
    }

    @NonNull
    public TaskStackBuilder addNextIntentWithParentStack(@NonNull Intent nextIntent) {
        ComponentName component = nextIntent.getComponent();
        if (component == null) {
            component = nextIntent.resolveActivity(this.mSourceContext.getPackageManager());
        }
        if (component != null) {
            addParentStack(component);
        }
        addNextIntent(nextIntent);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public TaskStackBuilder addParentStack(@NonNull Activity sourceActivity) {
        Intent supportParentActivityIntent = sourceActivity instanceof SupportParentable ? ((SupportParentable) sourceActivity).getSupportParentActivityIntent() : null;
        if (supportParentActivityIntent == null) {
            supportParentActivityIntent = NavUtils.getParentActivityIntent(sourceActivity);
        }
        if (supportParentActivityIntent != null) {
            ComponentName component = supportParentActivityIntent.getComponent();
            if (component == null) {
                component = supportParentActivityIntent.resolveActivity(this.mSourceContext.getPackageManager());
            }
            addParentStack(component);
            addNextIntent(supportParentActivityIntent);
        }
        return this;
    }

    @Nullable
    public Intent editIntentAt(int index) {
        return this.mIntents.get(index);
    }

    @Deprecated
    public Intent getIntent(int index) {
        return editIntentAt(index);
    }

    public int getIntentCount() {
        return this.mIntents.size();
    }

    @NonNull
    public Intent[] getIntents() {
        Intent[] intentArr = new Intent[this.mIntents.size()];
        if (intentArr.length == 0) {
            return intentArr;
        }
        intentArr[0] = new Intent(this.mIntents.get(0)).addFlags(268484608);
        for (int i = 1; i < intentArr.length; i++) {
            intentArr[i] = new Intent(this.mIntents.get(i));
        }
        return intentArr;
    }

    @Nullable
    public PendingIntent getPendingIntent(int requestCode, int flags) {
        return getPendingIntent(requestCode, flags, null);
    }

    @Override // java.lang.Iterable
    @Deprecated
    public Iterator<Intent> iterator() {
        return this.mIntents.iterator();
    }

    public void startActivities() {
        startActivities(null);
    }

    @Nullable
    public PendingIntent getPendingIntent(int requestCode, int flags, @Nullable Bundle options) {
        if (this.mIntents.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
        }
        ArrayList<Intent> arrayList = this.mIntents;
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        int i = Build.VERSION.SDK_INT;
        return PendingIntent.getActivities(this.mSourceContext, requestCode, intentArr, flags, options);
    }

    public void startActivities(@Nullable Bundle options) {
        if (this.mIntents.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        ArrayList<Intent> arrayList = this.mIntents;
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        ContextCompat.startActivities(this.mSourceContext, intentArr, options);
    }

    @NonNull
    public TaskStackBuilder addParentStack(@NonNull Class<?> sourceActivityClass) {
        return addParentStack(new ComponentName(this.mSourceContext, sourceActivityClass));
    }

    public TaskStackBuilder addParentStack(ComponentName sourceActivityName) {
        int size = this.mIntents.size();
        try {
            Intent parentActivityIntent = NavUtils.getParentActivityIntent(this.mSourceContext, sourceActivityName);
            while (parentActivityIntent != null) {
                this.mIntents.add(size, parentActivityIntent);
                parentActivityIntent = NavUtils.getParentActivityIntent(this.mSourceContext, parentActivityIntent.getComponent());
            }
            return this;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e(TAG, "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e2);
        }
    }
}
