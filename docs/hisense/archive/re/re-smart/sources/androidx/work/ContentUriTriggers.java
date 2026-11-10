package androidx.work;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ContentUriTriggers {
    public final Set<Trigger> mTriggers = new HashSet();

    public static final class Trigger {
        public final boolean mTriggerForDescendants;

        @NonNull
        public final Uri mUri;

        public Trigger(@NonNull Uri uri, boolean triggerForDescendants) {
            this.mUri = uri;
            this.mTriggerForDescendants = triggerForDescendants;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || Trigger.class != o.getClass()) {
                return false;
            }
            Trigger trigger = (Trigger) o;
            return this.mTriggerForDescendants == trigger.mTriggerForDescendants && this.mUri.equals(trigger.mUri);
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        public int hashCode() {
            return (this.mUri.hashCode() * 31) + (this.mTriggerForDescendants ? 1 : 0);
        }

        public boolean shouldTriggerForDescendants() {
            return this.mTriggerForDescendants;
        }
    }

    public void add(@NonNull Uri uri, boolean triggerForDescendants) {
        this.mTriggers.add(new Trigger(uri, triggerForDescendants));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || ContentUriTriggers.class != o.getClass()) {
            return false;
        }
        return this.mTriggers.equals(((ContentUriTriggers) o).mTriggers);
    }

    @NonNull
    public Set<Trigger> getTriggers() {
        return this.mTriggers;
    }

    public int hashCode() {
        return this.mTriggers.hashCode();
    }

    public int size() {
        return this.mTriggers.size();
    }
}
