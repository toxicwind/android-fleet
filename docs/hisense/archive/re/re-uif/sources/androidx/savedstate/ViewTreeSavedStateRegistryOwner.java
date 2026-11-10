package androidx.savedstate;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeSavedStateRegistryOwner {
    public static void set(View view, SavedStateRegistryOwner savedStateRegistryOwner) {
        view.setTag(R$id.view_tree_saved_state_registry_owner, savedStateRegistryOwner);
    }
}
