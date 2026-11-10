package androidx.core.view;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.RestrictTo;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActionProvider {
    public static final String TAG = "ActionProvider(support)";
    public final Context mContext;
    public SubUiVisibilityListener mSubUiVisibilityListener;
    public VisibilityListener mVisibilityListener;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public interface SubUiVisibilityListener {
        void onSubUiVisibilityChanged(boolean isVisible);
    }

    public interface VisibilityListener {
        void onActionProviderVisibilityChanged(boolean isVisible);
    }

    public ActionProvider(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isVisible() {
        return true;
    }

    public abstract View onCreateActionView();

    public View onCreateActionView(MenuItem forItem) {
        return onCreateActionView();
    }

    public boolean onPerformDefaultAction() {
        return false;
    }

    public void onPrepareSubMenu(SubMenu subMenu) {
    }

    public boolean overridesItemVisibility() {
        return false;
    }

    public void refreshVisibility() {
        if (this.mVisibilityListener == null || !overridesItemVisibility()) {
            return;
        }
        this.mVisibilityListener.onActionProviderVisibilityChanged(isVisible());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void reset() {
        this.mVisibilityListener = null;
        this.mSubUiVisibilityListener = null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setSubUiVisibilityListener(SubUiVisibilityListener listener) {
        this.mSubUiVisibilityListener = listener;
    }

    public void setVisibilityListener(VisibilityListener listener) {
        if (this.mVisibilityListener != null && listener != null) {
            StringBuilder sbA = a.a("setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this ");
            sbA.append(ActionProvider.class.getSimpleName());
            sbA.append(" instance while it is still in use somewhere else?");
            Log.w(TAG, sbA.toString());
        }
        this.mVisibilityListener = listener;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void subUiVisibilityChanged(boolean isVisible) {
        SubUiVisibilityListener subUiVisibilityListener = this.mSubUiVisibilityListener;
        if (subUiVisibilityListener != null) {
            subUiVisibilityListener.onSubUiVisibilityChanged(isVisible);
        }
    }
}
