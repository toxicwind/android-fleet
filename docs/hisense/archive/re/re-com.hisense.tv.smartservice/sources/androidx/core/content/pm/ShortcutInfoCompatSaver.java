package androidx.core.content.pm;

import androidx.annotation.AnyThread;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public abstract class ShortcutInfoCompatSaver<T> {

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static class NoopImpl extends ShortcutInfoCompatSaver<Void> {
        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        public /* bridge */ /* synthetic */ Void addShortcuts(List shortcuts) {
            return addShortcuts2((List<ShortcutInfoCompat>) shortcuts);
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        /* JADX INFO: renamed from: addShortcuts, reason: avoid collision after fix types in other method */
        public Void addShortcuts2(List<ShortcutInfoCompat> shortcuts) {
            return null;
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        public Void removeAllShortcuts() {
            return null;
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        public /* bridge */ /* synthetic */ Void removeShortcuts(List shortcutIds) {
            return removeShortcuts2((List<String>) shortcutIds);
        }

        @Override // androidx.core.content.pm.ShortcutInfoCompatSaver
        /* JADX INFO: renamed from: removeShortcuts, reason: avoid collision after fix types in other method */
        public Void removeShortcuts2(List<String> shortcutIds) {
            return null;
        }
    }

    @AnyThread
    public abstract T addShortcuts(List<ShortcutInfoCompat> shortcuts);

    @WorkerThread
    public List<ShortcutInfoCompat> getShortcuts() {
        return new ArrayList();
    }

    @AnyThread
    public abstract T removeAllShortcuts();

    @AnyThread
    public abstract T removeShortcuts(List<String> shortcutIds);
}
