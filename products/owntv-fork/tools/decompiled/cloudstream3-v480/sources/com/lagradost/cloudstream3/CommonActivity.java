package com.lagradost.cloudstream3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewGroupKt;
import androidx.preference.PreferenceManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigationrail.NavigationRailView;
import com.lagradost.cloudstream3.actions.OpenInAppAction;
import com.lagradost.cloudstream3.actions.VideoClickAction;
import com.lagradost.cloudstream3.actions.VideoClickActionHolder;
import com.lagradost.cloudstream3.databinding.ToastBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.ui.player.PlayerPipHelper;
import com.lagradost.cloudstream3.ui.player.Torrent;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.Event;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import io.ktor.http.ContentType;
import java.lang.ref.WeakReference;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.NewPipe;

/* JADX INFO: compiled from: CommonActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0007J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u0004\u0018\u00010\u0006H\u0007J!\u0010>\u001a\u00020\u000e2\b\b\u0001\u0010?\u001a\u00020\u00172\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010AJ!\u0010>\u001a\u00020\u000e2\b\u0010?\u001a\u0004\u0018\u00010B2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010CJ!\u0010>\u001a\u00020\u000e2\b\u0010?\u001a\u0004\u0018\u00010D2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010EJ\"\u0010>\u001a\u00020\u000e2\b\u0010F\u001a\u0004\u0018\u00010\u00062\u0006\u0010G\u001a\u00020D2\u0006\u0010@\u001a\u00020\u0017H\u0007J-\u0010>\u001a\u00020\u000e2\b\u0010F\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010?\u001a\u00020\u00172\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0017H\u0007¢\u0006\u0002\u0010HJ-\u0010>\u001a\u00020\u000e2\b\u0010F\u001a\u0004\u0018\u00010\u00062\b\u0010?\u001a\u0004\u0018\u00010B2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u0017H\u0007¢\u0006\u0002\u0010JJ\u001a\u0010K\u001a\u00020\u000e2\b\u0010L\u001a\u0004\u0018\u00010M2\b\u0010N\u001a\u0004\u0018\u00010BJ\n\u0010O\u001a\u00020\u000e*\u00020MJ\u000e\u0010P\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020\u0006J\f\u0010Q\u001a\u00020\u000e*\u00020\u0006H\u0002J\u000e\u0010R\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020\u0006J\u000e\u0010S\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020\u0006J\u0010\u0010T\u001a\u00020\u00172\u0006\u0010F\u001a\u00020\u0006H\u0002J\u0010\u0010U\u001a\u00020\u000e2\b\u0010F\u001a\u0004\u0018\u00010\u0006J\u001a\u0010V\u001a\u0004\u0018\u00010W2\u0006\u0010X\u001a\u00020W2\u0006\u0010Y\u001a\u00020\u0017H\u0002J\f\u0010Z\u001a\u00020!*\u00020WH\u0002J4\u0010[\u001a\u0004\u0018\u00010W2\b\u0010\\\u001a\u0004\u0018\u00010\u00012\u0006\u0010]\u001a\u00020W2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u00172\b\b\u0002\u0010a\u001a\u00020\u0017J.\u0010b\u001a\u0004\u0018\u00010W2\b\u0010\\\u001a\u0004\u0018\u00010\u00012\b\u0010]\u001a\u0004\u0018\u00010W2\u0006\u0010^\u001a\u00020_2\b\b\u0002\u0010a\u001a\u00020\u0017J)\u0010c\u001a\u0004\u0018\u00010!2\b\u0010F\u001a\u0004\u0018\u00010\u00062\u0006\u0010d\u001a\u00020\u00172\b\u0010e\u001a\u0004\u0018\u000100¢\u0006\u0002\u0010fJ!\u0010g\u001a\u0004\u0018\u00010!2\b\u0010F\u001a\u0004\u0018\u00010\u00062\b\u0010e\u001a\u0004\u0018\u000100¢\u0006\u0002\u0010hR\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00068F@BX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u001c\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\u001e\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0019R\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\"\"\u0004\b&\u0010$R#\u0010'\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170)0(¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00170(¢\u0006\b\n\u0000\u001a\u0004\b-\u0010+R6\u0010.\u001a\u001e\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u000100\u0012\u0004\u0012\u00020!0)\u0012\u0004\u0012\u00020!\u0018\u00010/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0019\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0019\"\u0004\b;\u00108R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020BX\u0086T¢\u0006\u0002\n\u0000¨\u0006i"}, d2 = {"Lcom/lagradost/cloudstream3/CommonActivity;", "", "<init>", "()V", "_activity", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", ES6Iterator.VALUE_PROPERTY, "activity", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "setActivityInstance", "", "newActivity", "getCastSession", "Lcom/google/android/gms/cast/framework/CastSession;", "displayMetrics", "Landroid/util/DisplayMetrics;", "getDisplayMetrics", "()Landroid/util/DisplayMetrics;", "screenWidth", "", "getScreenWidth", "()I", "screenHeight", "getScreenHeight", "screenWidthWithOrientation", "getScreenWidthWithOrientation", "screenHeightWithOrientation", "getScreenHeightWithOrientation", "isPipDesired", "", "()Z", "setPipDesired", "(Z)V", "isInPIPMode", "setInPIPMode", "onColorSelectedEvent", "Lcom/lagradost/cloudstream3/utils/Event;", "Lkotlin/Pair;", "getOnColorSelectedEvent", "()Lcom/lagradost/cloudstream3/utils/Event;", "onDialogDismissedEvent", "getOnDialogDismissedEvent", "keyEventListener", "Lkotlin/Function1;", "Landroid/view/KeyEvent;", "getKeyEventListener", "()Lkotlin/jvm/functions/Function1;", "setKeyEventListener", "(Lkotlin/jvm/functions/Function1;)V", "appliedTheme", "getAppliedTheme", "setAppliedTheme", "(I)V", "appliedColor", "getAppliedColor", "setAppliedColor", "currentToast", "Landroid/widget/Toast;", "showToast", ContentType.Message.TYPE, TypedValues.TransitionType.S_DURATION, "(ILjava/lang/Integer;)V", "", "(Ljava/lang/String;Ljava/lang/Integer;)V", "Lcom/lagradost/cloudstream3/utils/UiText;", "(Lcom/lagradost/cloudstream3/utils/UiText;Ljava/lang/Integer;)V", "act", "text", "(Landroid/app/Activity;ILjava/lang/Integer;)V", "TAG", "(Landroid/app/Activity;Ljava/lang/String;Ljava/lang/Integer;)V", "setLocale", "context", "Landroid/content/Context;", "languageTag", "updateLocale", "init", "enterPIPMode", "onUserLeaveHint", "updateTheme", "mapSystemTheme", "loadThemes", "localLook", "Landroid/view/View;", TypedValues.TransitionType.S_FROM, "id", "hasContent", "continueGetNextFocus", "root", "view", "direction", "Lcom/lagradost/cloudstream3/FocusDirection;", "nextId", "depth", "getNextFocus", "onKeyDown", "keyCode", NotificationCompat.CATEGORY_EVENT, "(Landroid/app/Activity;ILandroid/view/KeyEvent;)Ljava/lang/Boolean;", "dispatchKeyEvent", "(Landroid/app/Activity;Landroid/view/KeyEvent;)Ljava/lang/Boolean;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CommonActivity {
    public static final CommonActivity INSTANCE = new CommonActivity();
    public static final String TAG = "COMPACT";
    private static WeakReference<Activity> _activity;
    private static int appliedColor;
    private static int appliedTheme;
    private static Toast currentToast;
    private static final DisplayMetrics displayMetrics;
    private static boolean isInPIPMode;
    private static boolean isPipDesired;
    private static Function1<? super Pair<? extends KeyEvent, Boolean>, Boolean> keyEventListener;
    private static final Event<Pair<Integer, Integer>> onColorSelectedEvent;
    private static final Event<Integer> onDialogDismissedEvent;

    /* JADX INFO: compiled from: CommonActivity.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusDirection.values().length];
            try {
                iArr[FocusDirection.Start.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusDirection.Up.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusDirection.End.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusDirection.Down.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final Boolean onKeyDown(Activity act, int keyCode, KeyEvent event) {
        return null;
    }

    private CommonActivity() {
    }

    public final Activity getActivity() {
        WeakReference<Activity> weakReference = _activity;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private final void setActivity(Activity activity) {
        _activity = new WeakReference<>(activity);
    }

    public final void setActivityInstance(Activity newActivity) {
        setActivity(newActivity);
    }

    public final CastSession getCastSession(Activity activity) {
        SessionManager mSessionManager;
        MainActivity mainActivity = (MainActivity) activity;
        if (mainActivity == null || (mSessionManager = mainActivity.getMSessionManager()) == null) {
            return null;
        }
        return mSessionManager.getCurrentCastSession();
    }

    static {
        DisplayMetrics displayMetrics2 = Resources.getSystem().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics2, "getDisplayMetrics(...)");
        displayMetrics = displayMetrics2;
        onColorSelectedEvent = new Event<>();
        onDialogDismissedEvent = new Event<>();
    }

    public final DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }

    public final int getScreenWidth() {
        DisplayMetrics displayMetrics2 = displayMetrics;
        return Math.max(displayMetrics2.widthPixels, displayMetrics2.heightPixels);
    }

    public final int getScreenHeight() {
        DisplayMetrics displayMetrics2 = displayMetrics;
        return Math.min(displayMetrics2.widthPixels, displayMetrics2.heightPixels);
    }

    public final int getScreenWidthWithOrientation() {
        return displayMetrics.widthPixels;
    }

    public final int getScreenHeightWithOrientation() {
        return displayMetrics.heightPixels;
    }

    public final boolean isPipDesired() {
        return isPipDesired;
    }

    public final void setPipDesired(boolean z) {
        isPipDesired = z;
    }

    public final boolean isInPIPMode() {
        return isInPIPMode;
    }

    public final void setInPIPMode(boolean z) {
        isInPIPMode = z;
    }

    public final Event<Pair<Integer, Integer>> getOnColorSelectedEvent() {
        return onColorSelectedEvent;
    }

    public final Event<Integer> getOnDialogDismissedEvent() {
        return onDialogDismissedEvent;
    }

    public final Function1<Pair<? extends KeyEvent, Boolean>, Boolean> getKeyEventListener() {
        return keyEventListener;
    }

    public final void setKeyEventListener(Function1<? super Pair<? extends KeyEvent, Boolean>, Boolean> function1) {
        keyEventListener = function1;
    }

    public final int getAppliedTheme() {
        return appliedTheme;
    }

    public final void setAppliedTheme(int i) {
        appliedTheme = i;
    }

    public final int getAppliedColor() {
        return appliedColor;
    }

    public final void setAppliedColor(int i) {
        appliedColor = i;
    }

    public static /* synthetic */ void showToast$default(CommonActivity commonActivity, int i, Integer num, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            num = null;
        }
        commonActivity.showToast(i, num);
    }

    public final void showToast(final int message, final Integer duration) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.CommonActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                Activity activity2 = activity;
                CommonActivity.INSTANCE.showToast(activity2, activity2.getString(message), duration);
            }
        });
    }

    public static /* synthetic */ void showToast$default(CommonActivity commonActivity, String str, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        commonActivity.showToast(str, num);
    }

    public final void showToast(final String message, final Integer duration) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.CommonActivity$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                CommonActivity.INSTANCE.showToast(activity, message, duration);
            }
        });
    }

    public static /* synthetic */ void showToast$default(CommonActivity commonActivity, UiText uiText, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        commonActivity.showToast(uiText, num);
    }

    public final void showToast(final UiText message, final Integer duration) {
        final Activity activity = getActivity();
        if (activity == null || message == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.CommonActivity$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                Activity activity2 = activity;
                CommonActivity.INSTANCE.showToast(activity2, message.asString(activity2), duration);
            }
        });
    }

    public final void showToast(Activity act, UiText text, int duration) {
        String strAsStringNull;
        Intrinsics.checkNotNullParameter(text, "text");
        if (act == null || (strAsStringNull = text.asStringNull(act)) == null) {
            return;
        }
        INSTANCE.showToast(act, strAsStringNull, Integer.valueOf(duration));
    }

    public static /* synthetic */ void showToast$default(CommonActivity commonActivity, Activity activity, int i, Integer num, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            num = null;
        }
        commonActivity.showToast(activity, i, num);
    }

    public final void showToast(Activity act, int message, Integer duration) {
        if (act == null) {
            return;
        }
        showToast(act, act.getString(message), duration);
    }

    public static /* synthetic */ void showToast$default(CommonActivity commonActivity, Activity activity, String str, Integer num, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        commonActivity.showToast(activity, str, num);
    }

    public final void showToast(Activity act, String message, Integer duration) {
        if (act == null || message == null) {
            Log.w(TAG, "invalid showToast act = " + act + " message = " + message);
            return;
        }
        Log.i(TAG, "showToast = " + message);
        try {
            Toast toast = currentToast;
            if (toast != null) {
                toast.cancel();
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
        try {
            ToastBinding toastBindingInflate = ToastBinding.inflate(act.getLayoutInflater());
            Intrinsics.checkNotNullExpressionValue(toastBindingInflate, "inflate(...)");
            toastBindingInflate.text.setText(StringsKt.trim((CharSequence) message).toString());
            Toast toast2 = new Toast(act);
            toast2.setDuration(duration != null ? duration.intValue() : 0);
            toast2.setGravity(81, 0, UIHelper.INSTANCE.getToPx(5));
            toast2.setView(toastBindingInflate.getRoot());
            currentToast = toast2;
            toast2.show();
            Handler handler = new Handler(Looper.getMainLooper());
            final WeakReference weakReference = new WeakReference(toast2);
            handler.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.CommonActivity$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    CommonActivity.showToast$lambda$4(weakReference);
                }
            }, 10000L);
        } catch (Exception e2) {
            ArchComponentExtKt.logError(e2);
        }
    }

    static final void showToast$lambda$4(WeakReference weakReference) {
        if (Intrinsics.areEqual(weakReference.get(), currentToast)) {
            currentToast = null;
        }
    }

    public final void setLocale(Context context, String languageTag) {
        if (context == null || languageTag == null) {
            return;
        }
        Locale localeForLanguageTag = Locale.forLanguageTag(languageTag);
        Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
        Configuration configuration = resources.getConfiguration();
        Locale.setDefault(localeForLanguageTag);
        configuration.setLocale(localeForLanguageTag);
        if (Build.VERSION.SDK_INT >= 24) {
            context.createConfigurationContext(configuration);
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public final void updateLocale(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        setLocale(context, PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.locale_key), null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.CommonActivity$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: CommonActivity.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/CommonActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.CommonActivity$init$1", f = "CommonActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, CommonActivity, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, CommonActivity commonActivity, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Torrent.INSTANCE.deleteAllFiles();
            return Unit.INSTANCE;
        }
    }

    public final void init(final Activity act) {
        Intrinsics.checkNotNullParameter(act, "act");
        setActivityInstance(act);
        Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(null));
        Activity activity = getActivity();
        ComponentActivity componentActivity = activity instanceof ComponentActivity ? (ComponentActivity) activity : null;
        if (componentActivity == null) {
            return;
        }
        ComponentActivity componentActivity2 = componentActivity;
        updateLocale(componentActivity2);
        Globals.INSTANCE.updateTv(componentActivity2);
        AccountManager.INSTANCE.initMainAPI();
        NewPipe.init(DownloaderTestImpl.INSTANCE.getInstance());
        MainActivity.INSTANCE.setActivityResultLauncher(componentActivity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.lagradost.cloudstream3.CommonActivity$$ExternalSyntheticLambda8
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                CommonActivity.init$lambda$0(act, (ActivityResult) obj);
            }
        }));
        if (Build.VERSION.SDK_INT < 33 || ContextCompat.checkSelfPermission(componentActivity2, "android.permission.POST_NOTIFICATIONS") == 0) {
            return;
        }
        componentActivity.registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.lagradost.cloudstream3.CommonActivity$$ExternalSyntheticLambda9
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                Log.d(CommonActivity.TAG, "Notification permission: " + ((Boolean) obj).booleanValue());
            }
        }).launch("android.permission.POST_NOTIFICATIONS");
    }

    /* JADX WARN: Code duplicated, block: B:24:0x006d  */
    /* JADX WARN: Code duplicated, block: B:31:0x0083 A[Catch: Exception -> 0x0095, TRY_LEAVE, TryCatch #3 {Exception -> 0x0095, blocks: (B:7:0x0019, B:10:0x0025, B:15:0x003b, B:22:0x0067, B:25:0x006e, B:31:0x0083, B:30:0x007e, B:21:0x005d, B:14:0x0031, B:11:0x0027, B:18:0x0042, B:27:0x0072), top: B:48:0x0019, inners: #0, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static final void init$lambda$0(Activity activity, ActivityResult result) {
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.getResultCode() == -1) {
            Context context = CloudStreamApp.INSTANCE.getContext();
            if (context != null) {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(context).getString("last_click_action", null);
                    if (string == null) {
                        objDecodeFromString = null;
                    } else {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                objM8936constructorimpl = null;
                            }
                            kSerializer = (KSerializer) objM8936constructorimpl;
                            if (kSerializer != null) {
                                try {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                } catch (SerializationException e) {
                                    ArchComponentExtKt.logError(e);
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.CommonActivity$init$lambda$0$$inlined$getKey$1
                                    });
                                } catch (Throwable unused) {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.CommonActivity$init$lambda$0$$inlined$getKey$1
                                    });
                                }
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.CommonActivity$init$lambda$0$$inlined$getKey$1
                                });
                            }
                        } else {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                objM8936constructorimpl = null;
                            }
                            kSerializer = (KSerializer) objM8936constructorimpl;
                            if (kSerializer != null) {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.CommonActivity$init$lambda$0$$inlined$getKey$1
                                });
                            }
                        }
                    }
                } catch (Exception unused2) {
                    objDecodeFromString = null;
                }
            } else {
                objDecodeFromString = null;
            }
            String str = (String) objDecodeFromString;
            if (str == null) {
                return;
            }
            Log.d(TAG, "Loading action " + str + " result handler");
            VideoClickAction byUniqueId = VideoClickActionHolder.INSTANCE.getByUniqueId(str);
            OpenInAppAction openInAppAction = byUniqueId instanceof OpenInAppAction ? (OpenInAppAction) byUniqueId : null;
            if (openInAppAction == null) {
                return;
            }
            openInAppAction.onResultSafe(activity, result.getData());
            CloudStreamApp.INSTANCE.removeKey("last_click_action");
            CloudStreamApp.INSTANCE.removeKey("last_opened");
        }
    }

    private final void enterPIPMode(Activity activity) {
        if (isPipDesired && PlayerPipHelper.INSTANCE.isPIPPossible(activity)) {
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    try {
                        CommonActivity$$ExternalSyntheticApiModelOutline0.m8390m();
                        Boolean.valueOf(activity.enterPictureInPictureMode(CommonActivity$$ExternalSyntheticApiModelOutline0.m8386m().build()));
                        return;
                    } catch (Exception unused) {
                        activity.enterPictureInPictureMode();
                        Unit unit = Unit.INSTANCE;
                        return;
                    }
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    activity.enterPictureInPictureMode();
                }
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
            }
        }
    }

    public final void onUserLeaveHint(Activity act) {
        Intrinsics.checkNotNullParameter(act, "act");
        if (Build.VERSION.SDK_INT >= 31) {
            return;
        }
        enterPIPMode(act);
    }

    public final void updateTheme(Activity act) {
        Intrinsics.checkNotNullParameter(act, "act");
        if (!Intrinsics.areEqual(PreferenceManager.getDefaultSharedPreferences(act).getString(act.getString(R.string.app_theme_key), "AmoledLight"), "System") || Build.VERSION.SDK_INT < 29) {
            return;
        }
        loadThemes(act);
    }

    private final int mapSystemTheme(Activity act) {
        if (Build.VERSION.SDK_INT >= 29) {
            if ((act.getResources().getConfiguration().uiMode & 48) == 16) {
                return R.style.LightMode;
            }
            return R.style.AppTheme;
        }
        return R.style.AppTheme;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:144:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:48:0x009a  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void loadThemes(Activity act) {
        int iMapSystemTheme;
        int i;
        if (act == null) {
            return;
        }
        Activity activity = act;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String string = defaultSharedPreferences.getString(act.getString(R.string.app_theme_key), "AmoledLight");
        if (string != null) {
            switch (string.hashCode()) {
                case -1803461041:
                    if (string.equals("System")) {
                        iMapSystemTheme = mapSystemTheme(act);
                    }
                    break;
                case -1321965081:
                    if (string.equals("Lavender")) {
                        iMapSystemTheme = R.style.LavenderMode;
                    }
                    break;
                case -717889190:
                    if (string.equals("Dracula")) {
                        iMapSystemTheme = R.style.DraculaMode;
                    }
                    break;
                case 64266207:
                    if (string.equals("Black")) {
                        iMapSystemTheme = R.style.AppTheme;
                    }
                    break;
                case 73417974:
                    if (string.equals("Light")) {
                        iMapSystemTheme = R.style.LightMode;
                    }
                    break;
                case 74526875:
                    iMapSystemTheme = (!string.equals("Monet") || Build.VERSION.SDK_INT < 31) ? R.style.AppTheme : R.style.MonetMode;
                    break;
                case 1964972424:
                    if (string.equals("Amoled")) {
                        iMapSystemTheme = R.style.AmoledMode;
                    }
                    break;
                case 2012013167:
                    if (string.equals("SilentBlue")) {
                        iMapSystemTheme = R.style.SilentBlueMode;
                    }
                    break;
                case 2078007406:
                    if (string.equals("AmoledLight")) {
                        iMapSystemTheme = R.style.AmoledModeLight;
                    }
                    break;
                default:
                    iMapSystemTheme = R.style.AppTheme;
                    break;
            }
        } else {
            iMapSystemTheme = R.style.AppTheme;
        }
        String string2 = defaultSharedPreferences.getString(act.getString(R.string.primary_color_key), "Normal");
        if (string2 != null) {
            switch (string2.hashCode()) {
                case -2114143920:
                    if (string2.equals("NavyBlue")) {
                        i = R.style.OverlayPrimaryColorNavyBlue;
                    }
                    break;
                case -1997434736:
                    if (string2.equals("Maroon")) {
                        i = R.style.OverlayPrimaryColorMaroon;
                    }
                    break;
                case -1984634121:
                    i = (!string2.equals("Monet2") || Build.VERSION.SDK_INT < 31) ? R.style.OverlayPrimaryColorNormal : R.style.OverlayPrimaryColorMonetTwo;
                    break;
                case -1955878649:
                    if (string2.equals("Normal")) {
                        i = R.style.OverlayPrimaryColorNormal;
                    }
                    break;
                case -1924984242:
                    if (string2.equals("Orange")) {
                        i = R.style.OverlayPrimaryColorOrange;
                    }
                    break;
                case -1893076004:
                    if (string2.equals("Purple")) {
                        i = R.style.OverlayPrimaryColorPurple;
                    }
                    break;
                case -1321965081:
                    if (string2.equals("Lavender")) {
                        i = R.style.OverlayPrimaryColorLavender;
                    }
                    break;
                case -482481309:
                    if (string2.equals("CoolBlue")) {
                        i = R.style.OverlayPrimaryColorCoolBlue;
                    }
                    break;
                case -243530526:
                    if (string2.equals("DandelionYellow")) {
                        i = R.style.OverlayPrimaryColorDandelionYellow;
                    }
                    break;
                case 82033:
                    if (string2.equals("Red")) {
                        i = R.style.OverlayPrimaryColorRed;
                    }
                    break;
                case 2227967:
                    if (string2.equals("Grey")) {
                        i = R.style.OverlayPrimaryColorGrey;
                    }
                    break;
                case 2487702:
                    if (string2.equals("Pink")) {
                        i = R.style.OverlayPrimaryColorPink;
                    }
                    break;
                case 64459030:
                    if (string2.equals("Brown")) {
                        i = R.style.OverlayPrimaryColorBrown;
                    }
                    break;
                case 69066467:
                    if (string2.equals("Green")) {
                        i = R.style.OverlayPrimaryColorGreen;
                    }
                    break;
                case 74526875:
                    i = (!string2.equals("Monet") || Build.VERSION.SDK_INT < 31) ? R.style.OverlayPrimaryColorNormal : R.style.OverlayPrimaryColorMonet;
                    break;
                case 76884678:
                    if (string2.equals("Party")) {
                        i = R.style.OverlayPrimaryColorParty;
                    }
                    break;
                case 83549193:
                    if (string2.equals("White")) {
                        i = R.style.OverlayPrimaryColorWhite;
                    }
                    break;
                case 129648909:
                    if (string2.equals("DarkGreen")) {
                        i = R.style.OverlayPrimaryColorDarkGreen;
                    }
                    break;
                case 1375944721:
                    if (string2.equals("CarnationPink")) {
                        i = R.style.OverlayPrimaryColorCarnationPink;
                    }
                    break;
                case 1627490871:
                    if (string2.equals("GreenApple")) {
                        i = R.style.OverlayPrimaryColorGreenApple;
                    }
                    break;
                case 1982479237:
                    if (string2.equals("Banana")) {
                        i = R.style.OverlayPrimaryColorBanana;
                    }
                    break;
                default:
                    i = R.style.OverlayPrimaryColorNormal;
                    break;
            }
        } else {
            i = R.style.OverlayPrimaryColorNormal;
        }
        act.getTheme().applyStyle(iMapSystemTheme, true);
        act.getTheme().applyStyle(i, true);
        appliedTheme = iMapSystemTheme;
        appliedColor = i;
        Globals.INSTANCE.updateTv(activity);
        if (Globals.INSTANCE.isLayout(2)) {
            act.getTheme().applyStyle(R.style.AppThemeTvOverlay, true);
        }
        act.getTheme().applyStyle(R.style.LoadedStyle, true);
    }

    private final View localLook(View from, int id) {
        if (id == -1) {
            return null;
        }
        for (int i = 0; i < 16; i++) {
            View viewFindViewById = from.findViewById(id);
            if (viewFindViewById != null) {
                return viewFindViewById;
            }
            Object parent = from.getParent();
            from = parent instanceof View ? (View) parent : null;
            if (from == null) {
                break;
            }
        }
        return null;
    }

    private final boolean hasContent(View view) {
        if (view.isShown()) {
            return ((view instanceof ViewGroup) && ((ViewGroup) view).getChildCount() == 0) ? false : true;
        }
        return false;
    }

    public static /* synthetic */ View continueGetNextFocus$default(CommonActivity commonActivity, Object obj, View view, FocusDirection focusDirection, int i, int i2, int i3, Object obj2) {
        if ((i3 & 16) != 0) {
            i2 = 0;
        }
        return commonActivity.continueGetNextFocus(obj, view, focusDirection, i, i2);
    }

    public final View continueGetNextFocus(Object root, View view, FocusDirection direction, int nextId, int depth) {
        View viewFindViewById;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(direction, "direction");
        View viewFindViewById2 = null;
        if (nextId == -1) {
            return null;
        }
        if (root instanceof Activity) {
            viewFindViewById = ((Activity) root).findViewById(nextId);
        } else {
            viewFindViewById = root instanceof View ? ((View) root).getRootView().findViewById(nextId) : null;
        }
        if (viewFindViewById == null) {
            return null;
        }
        View viewLocalLook = localLook(view, nextId);
        if (viewLocalLook != null) {
            viewFindViewById = viewLocalLook;
        }
        boolean zHasContent = hasContent(viewFindViewById);
        ViewGroup viewGroup = viewFindViewById instanceof ViewGroup ? (ViewGroup) viewFindViewById : null;
        boolean z = false;
        if (viewGroup != null && viewGroup.getDescendantFocusability() == 262144 && viewGroup.getChildCount() != 0) {
            z = true;
        }
        if (!viewFindViewById.isFocusable() && zHasContent && !z) {
            return null;
        }
        if (!zHasContent) {
            if (Intrinsics.areEqual(viewFindViewById, view)) {
                return null;
            }
            return getNextFocus(root, viewFindViewById, direction, depth + 1);
        }
        if (!(viewFindViewById instanceof ChipGroup)) {
            if ((viewFindViewById instanceof NavigationRailView) && (viewFindViewById2 = viewFindViewById.findViewById(((NavigationRailView) viewFindViewById).getSelectedItemId())) == null) {
                viewFindViewById2 = viewFindViewById.findViewById(R.id.navigation_home);
            }
        } else {
            for (View view2 : ViewGroupKt.getChildren((ViewGroup) viewFindViewById)) {
                View view3 = view2;
                if (view3.isFocusable() && view3.isShown()) {
                    viewFindViewById2 = view2;
                    break;
                }
            }
            viewFindViewById2 = viewFindViewById2;
        }
        return viewFindViewById2 != null ? viewFindViewById2 : viewFindViewById;
    }

    public static /* synthetic */ View getNextFocus$default(CommonActivity commonActivity, Object obj, View view, FocusDirection focusDirection, int i, int i2, Object obj2) {
        if ((i2 & 8) != 0) {
            i = 0;
        }
        return commonActivity.getNextFocus(obj, view, focusDirection, i);
    }

    public final View getNextFocus(Object root, View view, FocusDirection direction, int depth) {
        int nextFocusLeftId;
        Intrinsics.checkNotNullParameter(direction, "direction");
        if (view == null || depth >= 10 || root == null) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[direction.ordinal()];
        if (i != 1) {
            if (i == 2) {
                nextFocusLeftId = view.getNextFocusUpId();
            } else if (i != 3) {
                if (i != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                nextFocusLeftId = view.getNextFocusDownId();
            } else if (AppContextUtils.INSTANCE.isRtl(view)) {
                nextFocusLeftId = view.getNextFocusLeftId();
            } else {
                nextFocusLeftId = view.getNextFocusRightId();
            }
        } else if (AppContextUtils.INSTANCE.isRtl(view)) {
            nextFocusLeftId = view.getNextFocusRightId();
        } else {
            nextFocusLeftId = view.getNextFocusLeftId();
        }
        if (nextFocusLeftId == -1 && (nextFocusLeftId = view.getNextFocusForwardId()) == -1) {
            return null;
        }
        return continueGetNextFocus(root, view, direction, nextFocusLeftId, depth);
    }

    public final Boolean dispatchKeyEvent(Activity act, KeyEvent event) {
        Activity activity;
        View nextFocus$default;
        if (act == null) {
            return null;
        }
        View currentFocus = act.getCurrentFocus();
        if (event != null) {
            int keyCode = event.getKeyCode();
            if (currentFocus != null && event.getAction() == 0) {
                switch (keyCode) {
                    case 19:
                        activity = act;
                        nextFocus$default = getNextFocus$default(INSTANCE, activity, currentFocus, FocusDirection.Up, 0, 8, null);
                        break;
                    case 20:
                        activity = act;
                        nextFocus$default = getNextFocus$default(INSTANCE, activity, currentFocus, FocusDirection.Down, 0, 8, null);
                        break;
                    case 21:
                        activity = act;
                        nextFocus$default = getNextFocus$default(INSTANCE, activity, currentFocus, FocusDirection.Start, 0, 8, null);
                        break;
                    case 22:
                        activity = act;
                        nextFocus$default = getNextFocus$default(INSTANCE, activity, currentFocus, FocusDirection.End, 0, 8, null);
                        break;
                    default:
                        activity = act;
                        nextFocus$default = null;
                        break;
                }
                if (nextFocus$default != null) {
                    nextFocus$default.requestFocus();
                    Function1<? super Pair<? extends KeyEvent, Boolean>, Boolean> function1 = keyEventListener;
                    if (function1 != null) {
                        function1.invoke(new Pair(event, true));
                    }
                    return true;
                }
                if ((keyCode == 23 || keyCode == 66) && ((activity.getCurrentFocus() instanceof SearchView) || (activity.getCurrentFocus() instanceof SearchView.SearchAutoComplete))) {
                    UIHelper uIHelper = UIHelper.INSTANCE;
                    View currentFocus2 = activity.getCurrentFocus();
                    uIHelper.showInputMethod(currentFocus2 != null ? currentFocus2.findFocus() : null);
                }
            }
        }
        Function1<? super Pair<? extends KeyEvent, Boolean>, Boolean> function2 = keyEventListener;
        return (function2 == null || !function2.invoke(new Pair(event, false)).booleanValue()) ? null : true;
    }
}
