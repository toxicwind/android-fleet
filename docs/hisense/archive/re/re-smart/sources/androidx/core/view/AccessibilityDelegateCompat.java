package androidx.core.view;

import android.os.Build;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.view.accessibility.AccessibilityClickableSpanCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityDelegateCompat {
    public static final View.AccessibilityDelegate DEFAULT_DELEGATE = new View.AccessibilityDelegate();
    public final View.AccessibilityDelegate mBridge;
    public final View.AccessibilityDelegate mOriginalDelegate;

    static final class AccessibilityDelegateAdapter extends View.AccessibilityDelegate {
        public final AccessibilityDelegateCompat mCompat;

        public AccessibilityDelegateAdapter(AccessibilityDelegateCompat compat) {
            this.mCompat = compat;
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
            return this.mCompat.dispatchPopulateAccessibilityEvent(host, event);
        }

        @Override // android.view.View.AccessibilityDelegate
        @RequiresApi(16)
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View host) {
            AccessibilityNodeProviderCompat accessibilityNodeProvider = this.mCompat.getAccessibilityNodeProvider(host);
            if (accessibilityNodeProvider != null) {
                return (AccessibilityNodeProvider) accessibilityNodeProvider.getProvider();
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            this.mCompat.onInitializeAccessibilityEvent(host, event);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(info);
            accessibilityNodeInfoCompat.setScreenReaderFocusable(ViewCompat.isScreenReaderFocusable(host));
            accessibilityNodeInfoCompat.setHeading(ViewCompat.isAccessibilityHeading(host));
            accessibilityNodeInfoCompat.setPaneTitle(ViewCompat.getAccessibilityPaneTitle(host));
            accessibilityNodeInfoCompat.setStateDescription(ViewCompat.getStateDescription(host));
            this.mCompat.onInitializeAccessibilityNodeInfo(host, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.addSpansToExtras(info.getText(), host);
            List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actionList = AccessibilityDelegateCompat.getActionList(host);
            for (int i = 0; i < actionList.size(); i++) {
                accessibilityNodeInfoCompat.addAction(actionList.get(i));
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
            this.mCompat.onPopulateAccessibilityEvent(host, event);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
            return this.mCompat.onRequestSendAccessibilityEvent(host, child, event);
        }

        @Override // android.view.View.AccessibilityDelegate
        public boolean performAccessibilityAction(View host, int action, Bundle args) {
            return this.mCompat.performAccessibilityAction(host, action, args);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View host, int eventType) {
            this.mCompat.sendAccessibilityEvent(host, eventType);
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
            this.mCompat.sendAccessibilityEventUnchecked(host, event);
        }
    }

    public AccessibilityDelegateCompat() {
        this(DEFAULT_DELEGATE);
    }

    public static List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> getActionList(View view) {
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> list = (List) view.getTag(R.id.tag_accessibility_actions);
        return list == null ? Collections.emptyList() : list;
    }

    private boolean isSpanStillValid(ClickableSpan span, View view) {
        if (span != null) {
            ClickableSpan[] clickableSpans = AccessibilityNodeInfoCompat.getClickableSpans(view.createAccessibilityNodeInfo().getText());
            for (int i = 0; clickableSpans != null && i < clickableSpans.length; i++) {
                if (span.equals(clickableSpans[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean performClickableSpanAction(int clickableSpanId, View host) {
        WeakReference weakReference;
        SparseArray sparseArray = (SparseArray) host.getTag(R.id.tag_accessibility_clickable_spans);
        if (sparseArray == null || (weakReference = (WeakReference) sparseArray.get(clickableSpanId)) == null) {
            return false;
        }
        ClickableSpan clickableSpan = (ClickableSpan) weakReference.get();
        if (!isSpanStillValid(clickableSpan, host)) {
            return false;
        }
        clickableSpan.onClick(host);
        return true;
    }

    public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
        return this.mOriginalDelegate.dispatchPopulateAccessibilityEvent(host, event);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
        int i = Build.VERSION.SDK_INT;
        AccessibilityNodeProvider accessibilityNodeProvider = this.mOriginalDelegate.getAccessibilityNodeProvider(host);
        if (accessibilityNodeProvider != null) {
            return new AccessibilityNodeProviderCompat(accessibilityNodeProvider);
        }
        return null;
    }

    public View.AccessibilityDelegate getBridge() {
        return this.mBridge;
    }

    public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
        this.mOriginalDelegate.onInitializeAccessibilityEvent(host, event);
    }

    public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
        this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(host, info.unwrap());
    }

    public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
        this.mOriginalDelegate.onPopulateAccessibilityEvent(host, event);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
        return this.mOriginalDelegate.onRequestSendAccessibilityEvent(host, child, event);
    }

    public boolean performAccessibilityAction(View host, int action, Bundle args) {
        List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actionList = getActionList(host);
        boolean zPerformAccessibilityAction = false;
        for (int i = 0; i < actionList.size(); i++) {
            AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat = actionList.get(i);
            if (accessibilityActionCompat.getId() == action) {
                zPerformAccessibilityAction = accessibilityActionCompat.perform(host, args);
                break;
            }
        }
        if (!zPerformAccessibilityAction) {
            int i2 = Build.VERSION.SDK_INT;
            zPerformAccessibilityAction = this.mOriginalDelegate.performAccessibilityAction(host, action, args);
        }
        return (zPerformAccessibilityAction || action != R.id.accessibility_action_clickable_span) ? zPerformAccessibilityAction : performClickableSpanAction(args.getInt(AccessibilityClickableSpanCompat.SPAN_ID, -1), host);
    }

    public void sendAccessibilityEvent(View host, int eventType) {
        this.mOriginalDelegate.sendAccessibilityEvent(host, eventType);
    }

    public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
        this.mOriginalDelegate.sendAccessibilityEventUnchecked(host, event);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public AccessibilityDelegateCompat(View.AccessibilityDelegate originalDelegate) {
        this.mOriginalDelegate = originalDelegate;
        this.mBridge = new AccessibilityDelegateAdapter(this);
    }
}
