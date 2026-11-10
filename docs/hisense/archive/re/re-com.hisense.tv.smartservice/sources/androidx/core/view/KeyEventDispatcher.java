package androidx.core.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class KeyEventDispatcher {
    public static boolean sActionBarFieldsFetched = false;
    public static Method sActionBarOnMenuKeyMethod = null;
    public static boolean sDialogFieldsFetched = false;
    public static Field sDialogKeyListenerField;

    public interface Component {
        boolean superDispatchKeyEvent(KeyEvent event);
    }

    public static boolean actionBarOnMenuKeyEventPre28(ActionBar actionBar, KeyEvent event) {
        if (!sActionBarFieldsFetched) {
            try {
                sActionBarOnMenuKeyMethod = actionBar.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
            } catch (NoSuchMethodException unused) {
            }
            sActionBarFieldsFetched = true;
        }
        Method method = sActionBarOnMenuKeyMethod;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(actionBar, event)).booleanValue();
            } catch (IllegalAccessException | InvocationTargetException unused2) {
            }
        }
        return false;
    }

    public static boolean activitySuperDispatchKeyEventPre28(Activity activity, KeyEvent event) {
        activity.onUserInteraction();
        Window window = activity.getWindow();
        if (window.hasFeature(8)) {
            ActionBar actionBar = activity.getActionBar();
            if (event.getKeyCode() == 82 && actionBar != null && actionBarOnMenuKeyEventPre28(actionBar, event)) {
                return true;
            }
        }
        if (window.superDispatchKeyEvent(event)) {
            return true;
        }
        View decorView = window.getDecorView();
        if (ViewCompat.dispatchUnhandledKeyEventBeforeCallback(decorView, event)) {
            return true;
        }
        return event.dispatch(activity, decorView != null ? decorView.getKeyDispatcherState() : null, activity);
    }

    public static boolean dialogSuperDispatchKeyEventPre28(Dialog dialog, KeyEvent event) {
        DialogInterface.OnKeyListener dialogKeyListenerPre28 = getDialogKeyListenerPre28(dialog);
        if (dialogKeyListenerPre28 != null && dialogKeyListenerPre28.onKey(dialog, event.getKeyCode(), event)) {
            return true;
        }
        Window window = dialog.getWindow();
        if (window.superDispatchKeyEvent(event)) {
            return true;
        }
        View decorView = window.getDecorView();
        if (ViewCompat.dispatchUnhandledKeyEventBeforeCallback(decorView, event)) {
            return true;
        }
        return event.dispatch(dialog, decorView != null ? decorView.getKeyDispatcherState() : null, dialog);
    }

    public static boolean dispatchBeforeHierarchy(@NonNull View root, @NonNull KeyEvent event) {
        return ViewCompat.dispatchUnhandledKeyEventBeforeHierarchy(root, event);
    }

    public static boolean dispatchKeyEvent(@NonNull Component component, @Nullable View root, @Nullable Window.Callback callback, @NonNull KeyEvent event) {
        if (component == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return component.superDispatchKeyEvent(event);
        }
        if (callback instanceof Activity) {
            return activitySuperDispatchKeyEventPre28((Activity) callback, event);
        }
        if (callback instanceof Dialog) {
            return dialogSuperDispatchKeyEventPre28((Dialog) callback, event);
        }
        return (root != null && ViewCompat.dispatchUnhandledKeyEventBeforeCallback(root, event)) || component.superDispatchKeyEvent(event);
    }

    public static DialogInterface.OnKeyListener getDialogKeyListenerPre28(Dialog dialog) {
        if (!sDialogFieldsFetched) {
            try {
                sDialogKeyListenerField = Dialog.class.getDeclaredField("mOnKeyListener");
                sDialogKeyListenerField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            sDialogFieldsFetched = true;
        }
        Field field = sDialogKeyListenerField;
        if (field == null) {
            return null;
        }
        try {
            return (DialogInterface.OnKeyListener) field.get(dialog);
        } catch (IllegalAccessException unused2) {
            return null;
        }
    }
}
