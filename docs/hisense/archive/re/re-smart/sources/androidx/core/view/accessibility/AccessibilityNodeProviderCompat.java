package androidx.core.view.accessibility;

import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityNodeProviderCompat {
    public static final int HOST_VIEW_ID = -1;
    public final Object mProvider;

    @RequiresApi(16)
    static class AccessibilityNodeProviderApi16 extends AccessibilityNodeProvider {
        public final AccessibilityNodeProviderCompat mCompat;

        public AccessibilityNodeProviderApi16(AccessibilityNodeProviderCompat compat) {
            this.mCompat = compat;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int virtualViewId) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompatCreateAccessibilityNodeInfo = this.mCompat.createAccessibilityNodeInfo(virtualViewId);
            if (accessibilityNodeInfoCompatCreateAccessibilityNodeInfo == null) {
                return null;
            }
            return accessibilityNodeInfoCompatCreateAccessibilityNodeInfo.unwrap();
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String text, int virtualViewId) {
            List<AccessibilityNodeInfoCompat> listFindAccessibilityNodeInfosByText = this.mCompat.findAccessibilityNodeInfosByText(text, virtualViewId);
            if (listFindAccessibilityNodeInfosByText == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int size = listFindAccessibilityNodeInfosByText.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(listFindAccessibilityNodeInfosByText.get(i).unwrap());
            }
            return arrayList;
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int virtualViewId, int action, Bundle arguments) {
            return this.mCompat.performAction(virtualViewId, action, arguments);
        }
    }

    @RequiresApi(19)
    static class AccessibilityNodeProviderApi19 extends AccessibilityNodeProviderApi16 {
        public AccessibilityNodeProviderApi19(AccessibilityNodeProviderCompat compat) {
            super(compat);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int focus) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompatFindFocus = this.mCompat.findFocus(focus);
            if (accessibilityNodeInfoCompatFindFocus == null) {
                return null;
            }
            return accessibilityNodeInfoCompatFindFocus.unwrap();
        }
    }

    @RequiresApi(26)
    static class AccessibilityNodeProviderApi26 extends AccessibilityNodeProviderApi19 {
        public AccessibilityNodeProviderApi26(AccessibilityNodeProviderCompat compat) {
            super(compat);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public void addExtraDataToAccessibilityNodeInfo(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey, Bundle arguments) {
            this.mCompat.addExtraDataToAccessibilityNodeInfo(virtualViewId, new AccessibilityNodeInfoCompat(info), extraDataKey, arguments);
        }
    }

    public AccessibilityNodeProviderCompat() {
        int i = Build.VERSION.SDK_INT;
        this.mProvider = new AccessibilityNodeProviderApi26(this);
    }

    public void addExtraDataToAccessibilityNodeInfo(int virtualViewId, @NonNull AccessibilityNodeInfoCompat info, @NonNull String extraDataKey, @Nullable Bundle arguments) {
    }

    @Nullable
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int virtualViewId) {
        return null;
    }

    @Nullable
    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String text, int virtualViewId) {
        return null;
    }

    @Nullable
    public AccessibilityNodeInfoCompat findFocus(int focus) {
        return null;
    }

    public Object getProvider() {
        return this.mProvider;
    }

    public boolean performAction(int virtualViewId, int action, Bundle arguments) {
        return false;
    }

    public AccessibilityNodeProviderCompat(Object provider) {
        this.mProvider = provider;
    }
}
