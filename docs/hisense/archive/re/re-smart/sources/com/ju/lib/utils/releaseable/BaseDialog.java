package com.ju.lib.utils.releaseable;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDialog extends Dialog {
    public static final String TAG = "BaseDialog";
    public boolean dismissHasCalled;

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.dismissHasCalled = false;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    @Deprecated
    public void dismiss() {
        this.dismissHasCalled = true;
        try {
            super.dismiss();
        } catch (Exception e2) {
            Log.d(TAG, "super.dismiss() error = " + e2);
            e2.printStackTrace();
        }
        a.b("dismiss = ", this, TAG);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a.b("onAttachedToWindow = ", this, TAG);
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a.b("onCreate = ", this, TAG);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a.b("onDetachedFromWindow = ", this, TAG);
        if (this.dismissHasCalled) {
            return;
        }
        Log.e(TAG, "onDetachedFromWindow() called , but dialog doesn't call dismiss() before , leaked here, we fix it --> " + this);
        safeDismiss();
    }

    @Override // android.app.Dialog
    public void onStart() {
        super.onStart();
        a.b("onStart = ", this, TAG);
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        a.b("onStop = ", this, TAG);
    }

    public void safeDismiss() {
        a.b("safeDismiss = ", this, TAG);
        try {
            dismiss();
        } catch (Exception e2) {
            Log.e(TAG, "dismiss error = " + e2);
            e2.printStackTrace();
        }
    }

    public void safeShow() {
        a.b("safeShow = ", this, TAG);
        try {
            show();
        } catch (Exception e2) {
            Log.e(TAG, "show error = " + e2);
            e2.printStackTrace();
        }
    }

    @Override // android.app.Dialog
    @Deprecated
    public void show() {
        this.dismissHasCalled = false;
        try {
            super.show();
        } catch (Exception e2) {
            Log.d(TAG, "super.show() error = " + e2);
            e2.printStackTrace();
        }
        a.b("show = ", this, TAG);
    }

    public BaseDialog(@NonNull Context context, int i) {
        super(context, i);
        this.dismissHasCalled = false;
    }

    public BaseDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.dismissHasCalled = false;
    }
}
