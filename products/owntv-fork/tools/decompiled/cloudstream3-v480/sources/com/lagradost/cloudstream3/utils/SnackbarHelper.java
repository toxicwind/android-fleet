package com.lagradost.cloudstream3.utils;

import android.R;
import android.app.Activity;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.material.snackbar.Snackbar;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SnackbarHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JB\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0012H\u0007JI\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0001\u0010\f\u001a\u00020\u000f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0012H\u0007¢\u0006\u0002\u0010\u0013JD\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0012H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/utils/SnackbarHelper;", "", "<init>", "()V", "TAG", "", "currentSnackbar", "Lcom/google/android/material/snackbar/Snackbar;", "showSnackbar", "", "act", "Landroid/app/Activity;", ContentType.Message.TYPE, "Lcom/lagradost/cloudstream3/utils/UiText;", TypedValues.TransitionType.S_DURATION, "", "actionText", "actionCallback", "Lkotlin/Function0;", "(Landroid/app/Activity;IILjava/lang/Integer;Lkotlin/jvm/functions/Function0;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SnackbarHelper {
    public static final SnackbarHelper INSTANCE = new SnackbarHelper();
    private static final String TAG = "COMPACT";
    private static Snackbar currentSnackbar;

    private SnackbarHelper() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showSnackbar$default(SnackbarHelper snackbarHelper, Activity activity, UiText uiText, int i, UiText uiText2, Function0 function0, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = -1;
        }
        snackbarHelper.showSnackbar(activity, uiText, i, (i2 & 8) != 0 ? null : uiText2, (Function0<Unit>) ((i2 & 16) != 0 ? null : function0));
    }

    public final void showSnackbar(Activity act, UiText message, int duration, UiText actionText, Function0<Unit> actionCallback) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (act == null) {
            return;
        }
        Activity activity = act;
        showSnackbar(act, message.asString(activity), duration, actionText != null ? actionText.asString(activity) : null, actionCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showSnackbar$default(SnackbarHelper snackbarHelper, Activity activity, int i, int i2, Integer num, Function0 function0, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = -1;
        }
        snackbarHelper.showSnackbar(activity, i, i2, (i3 & 8) != 0 ? null : num, (Function0<Unit>) ((i3 & 16) != 0 ? null : function0));
    }

    public final void showSnackbar(Activity act, int message, int duration, Integer actionText, Function0<Unit> actionCallback) {
        if (act == null) {
            return;
        }
        showSnackbar(act, act.getString(message), duration, actionText != null ? act.getString(actionText.intValue()) : null, actionCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void showSnackbar$default(SnackbarHelper snackbarHelper, Activity activity, String str, int i, String str2, Function0 function0, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = -1;
        }
        snackbarHelper.showSnackbar(activity, str, i, (i2 & 8) != 0 ? null : str2, (Function0<Unit>) ((i2 & 16) != 0 ? null : function0));
    }

    public final void showSnackbar(Activity act, String message, int duration, String actionText, final Function0<Unit> actionCallback) {
        if (act == null || message == null) {
            Log.INSTANCE.w("COMPACT", "Invalid showSnackbar: act = " + act + ", message = " + message);
            return;
        }
        Log.INSTANCE.i("COMPACT", "showSnackbar: " + message);
        try {
            Snackbar snackbar = currentSnackbar;
            if (snackbar != null) {
                snackbar.dismiss();
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        try {
            Snackbar snackbarMake = Snackbar.make(act.findViewById(R.id.content), message, duration);
            Intrinsics.checkNotNullExpressionValue(snackbarMake, "make(...)");
            if (actionCallback != null) {
                snackbarMake.setAction(actionText, new View.OnClickListener() { // from class: com.lagradost.cloudstream3.utils.SnackbarHelper$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        actionCallback.invoke();
                    }
                });
            }
            snackbarMake.show();
            currentSnackbar = snackbarMake;
            snackbarMake.setBackgroundTint(UIHelper.INSTANCE.colorFromAttribute(act, com.lagradost.cloudstream3.R.attr.primaryBlackBackground));
            snackbarMake.setTextColor(UIHelper.INSTANCE.colorFromAttribute(act, com.lagradost.cloudstream3.R.attr.textColor));
            Intrinsics.checkNotNull(snackbarMake.setActionTextColor(UIHelper.INSTANCE.colorFromAttribute(act, com.lagradost.cloudstream3.R.attr.colorPrimary)));
        } catch (Exception e2) {
            ArchComponentExtKt.logError(e2);
        }
    }
}
