package com.lagradost.cloudstream3.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.palette.graphics.Palette;
import androidx.preference.PreferenceManager;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec;
import com.google.android.material.progressindicator.IndeterminateDrawable;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.settings.Globals;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: UIHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\f\u001a\u00020\r*\u00020\u000eJ9\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\b\u0003\u0010\u0016\u001a\u00020\u00052\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0018J\n\u0010\u0019\u001a\u00020\u0010*\u00020\u001aJ\u0016\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0010\u0010 \u001a\u00020\u00102\b\u0010!\u001a\u0004\u0018\u00010\"J\u0014\u0010#\u001a\u00020\u0005*\u00020\u000e2\b\b\u0002\u0010$\u001a\u00020\rJ\n\u0010%\u001a\u00020\u0010*\u00020&J\f\u0010'\u001a\u00020\u0010*\u0004\u0018\u00010(J\n\u0010%\u001a\u00020\u0010*\u00020\u001aJ,\u0010)\u001a\u00020\u0010*\u0004\u0018\u00010\u001a2\u0006\u0010*\u001a\u00020\u00052\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.J.\u0010/\u001a\u00020\u0010*\u00020\u000e2\n\u00100\u001a\u0006\u0012\u0002\b\u0003012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,2\n\b\u0002\u00102\u001a\u0004\u0018\u000103J\u0016\u00104\u001a\u00020\u0010*\u0002052\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0015J \u00107\u001a\u00020\u0005*\u00020\u000e2\b\b\u0001\u00108\u001a\u00020\u00052\b\b\u0002\u00109\u001a\u00020\bH\u0007J\u0016\u0010:\u001a\u00020\u0005*\u00020\u000e2\b\b\u0001\u0010;\u001a\u00020\u0005H\u0007J\u001a\u0010<\u001a\u00020\u00052\b\b\u0001\u0010=\u001a\u00020\u00052\u0006\u0010>\u001a\u00020\bH\u0007J*\u0010G\u001a\u00020\u00102\u0006\u0010H\u001a\u00020\u00152\u0006\u0010I\u001a\u00020J2\u0012\u0010K\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020\u00100LJ\n\u0010M\u001a\u00020\u0010*\u00020\u001aJ\n\u0010N\u001a\u00020\u0010*\u00020\u001aJ\u0014\u0010O\u001a\u00020\u0010*\u00020\u001a2\b\b\u0001\u0010P\u001a\u00020\u0005J\n\u0010Q\u001a\u00020\u0005*\u00020\u000eJ\u0010\u0010R\u001a\u00020\u00102\b\u0010S\u001a\u0004\u0018\u00010(J\u0010\u0010T\u001a\u00020\u00102\b\u0010S\u001a\u0004\u0018\u00010(Jg\u0010U\u001a\u00020\u00102\u0006\u0010S\u001a\u00020(2\n\b\u0003\u0010V\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010W\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010X\u001a\u00020\r2\b\b\u0002\u0010Y\u001a\u00020\r2\b\b\u0002\u0010Z\u001a\u00020\r2\b\b\u0002\u0010[\u001a\u00020\r2\b\b\u0002\u0010\\\u001a\u00020\r2\b\b\u0002\u0010]\u001a\u00020\r¢\u0006\u0002\u0010^J\n\u0010_\u001a\u00020\u0005*\u00020\u000eJ\f\u0010`\u001a\u00020\r*\u0004\u0018\u00010\u000eJ\u0012\u0010a\u001a\u00020\u0010*\u00020\u001a2\u0006\u0010b\u001a\u00020\rJ\n\u0010c\u001a\u00020\u0010*\u00020\u001aJ\u0010\u0010%\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010(J\u0010\u0010d\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010(J\u0016\u0010e\u001a\u00020\u0010*\u0004\u0018\u00010f2\b\u00100\u001a\u0004\u0018\u00010\u001aJ\f\u0010e\u001a\u00020\u0010*\u0004\u0018\u00010fJ\u0014\u0010g\u001a\u00020\u0010*\u00020h2\b\b\u0003\u0010i\u001a\u00020\u0005J\n\u0010j\u001a\u00020\u0010*\u00020hJ?\u0010k\u001a\u00020l*\u00020(2\u0018\u0010m\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050n0\u00142\u0017\u0010o\u001a\u0013\u0012\u0004\u0012\u00020p\u0012\u0004\u0012\u00020\u00100L¢\u0006\u0002\bqH\u0007J?\u0010r\u001a\u00020l*\u00020(2\u0018\u0010m\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00150n0\u00142\u0017\u0010o\u001a\u0013\u0012\u0004\u0012\u00020p\u0012\u0004\u0012\u00020\u00100L¢\u0006\u0002\bqH\u0007R\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0004\u001a\u00020\b*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\tR\u0015\u0010\n\u001a\u00020\u0005*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0015\u0010\n\u001a\u00020\b*\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR6\u0010?\u001a\u001e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020A0@j\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020A`BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010F¨\u0006s"}, d2 = {"Lcom/lagradost/cloudstream3/utils/UIHelper;", "", "<init>", "()V", "toPx", "", "getToPx", "(I)I", "", "(F)F", "toDp", "getToDp", "checkWrite", "", "Landroid/content/Context;", "populateChips", "", "view", "Lcom/google/android/material/chip/ChipGroup;", "tags", "", "", "style", "textColor", "(Lcom/google/android/material/chip/ChipGroup;Ljava/util/List;ILjava/lang/Integer;)V", "requestRW", "Landroid/app/Activity;", "clipboardHelper", "label", "Lcom/lagradost/cloudstream3/utils/UiText;", "text", "", "setListViewHeightBasedOnItems", "listView", "Landroid/widget/ListView;", "getSpanCount", "isHorizontal", "hideKeyboard", "Landroidx/fragment/app/Fragment;", "setAppBarNoScrollFlagsOnTV", "Landroid/view/View;", "navigate", "navigationId", "args", "Landroid/os/Bundle;", "navOptions", "Landroidx/navigation/NavOptions;", "openActivity", "activity", "Ljava/lang/Class;", "baseIntent", "Landroid/content/Intent;", "popCurrentPage", "Landroidx/fragment/app/FragmentActivity;", "fromBackPressedCallback", "getResourceColor", "resource", "alphaFactor", "colorFromAttribute", "attribute", "adjustAlpha", "color", "factor", "createPaletteAsyncCache", "Ljava/util/HashMap;", "Landroidx/palette/graphics/Palette;", "Lkotlin/collections/HashMap;", "getCreatePaletteAsyncCache", "()Ljava/util/HashMap;", "setCreatePaletteAsyncCache", "(Ljava/util/HashMap;)V", "createPaletteAsync", ImagesContract.URL, "bitmap", "Landroid/graphics/Bitmap;", "callback", "Lkotlin/Function1;", "hideSystemUI", "enableEdgeToEdgeCompat", "setNavigationBarColorCompat", "resourceId", "getStatusBarHeight", "fixPaddingStatusbarMargin", "v", "fixPaddingStatusbarView", "fixSystemBarsPadding", "heightResId", "widthResId", "padTop", "padBottom", "padLeft", "padRight", "overlayCutout", "fixIme", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;ZZZZZZ)V", "getNavigationBarHeight", "isBottomLayout", "changeStatusBarState", "hide", "showSystemUI", "showInputMethod", "dismissSafe", "Landroid/app/Dialog;", "showProgress", "Lcom/google/android/material/button/MaterialButton;", "tintColor", "hideProgress", "popupMenuNoIcons", "Landroidx/appcompat/widget/PopupMenu;", "items", "Lkotlin/Pair;", "onMenuItemClick", "Landroid/view/MenuItem;", "Lkotlin/ExtensionFunctionType;", "popupMenuNoIconsAndNoStringRes", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class UIHelper {
    public static final UIHelper INSTANCE = new UIHelper();
    private static HashMap<String, Palette> createPaletteAsyncCache = new HashMap<>();

    private UIHelper() {
    }

    public final int getToPx(int i) {
        return (int) (i * Resources.getSystem().getDisplayMetrics().density);
    }

    public final float getToPx(float f) {
        return f * Resources.getSystem().getDisplayMetrics().density;
    }

    public final int getToDp(int i) {
        return (int) (i / Resources.getSystem().getDisplayMetrics().density);
    }

    public final float getToDp(float f) {
        return f / Resources.getSystem().getDisplayMetrics().density;
    }

    public final boolean checkWrite(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 || Build.VERSION.SDK_INT >= 33;
    }

    public static /* synthetic */ void populateChips$default(UIHelper uIHelper, ChipGroup chipGroup, List list, int i, Integer num, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = R.style.ChipFilled;
        }
        if ((i2 & 8) != 0) {
            num = Integer.valueOf(R.attr.white);
        }
        uIHelper.populateChips(chipGroup, list, i, num);
    }

    public final void populateChips(ChipGroup view, List<String> tags, int style, Integer textColor) {
        Intrinsics.checkNotNullParameter(tags, "tags");
        if (view == null) {
            return;
        }
        view.removeAllViews();
        Context context = view.getContext();
        if (context == null) {
            return;
        }
        for (String str : CollectionsKt.take(tags, 10)) {
            Chip chip = new Chip(context);
            ChipDrawable chipDrawableCreateFromAttributes = ChipDrawable.createFromAttributes(context, null, 0, style);
            Intrinsics.checkNotNullExpressionValue(chipDrawableCreateFromAttributes, "createFromAttributes(...)");
            chip.setChipDrawable(chipDrawableCreateFromAttributes);
            chip.setText(str);
            chip.setChecked(false);
            chip.setCheckable(false);
            chip.setFocusable(false);
            chip.setClickable(false);
            if (textColor != null) {
                chip.setTextColor(INSTANCE.colorFromAttribute(context, textColor.intValue()));
            }
            view.addView(chip);
        }
    }

    public final void requestRW(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.MANAGE_EXTERNAL_STORAGE"}, 1337);
    }

    public final void clipboardHelper(UiText label, CharSequence text) {
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(text, "text");
        Context context = CloudStreamApp.INSTANCE.getContext();
        if (context == null) {
            return;
        }
        try {
            ClipData clipDataNewPlainText = ClipData.newPlainText(label.asString(context), text);
            String strAsString = TextUtilKt.txt(R.string.toast_copied, new Object[0]).asString(context);
            ClipboardManager clipboardManager = (ClipboardManager) ContextCompat.getSystemService(context, ClipboardManager.class);
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(clipDataNewPlainText);
            }
            if (Build.VERSION.SDK_INT <= 32) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, label.asString(context) + ' ' + strAsString, (Integer) null, 2, (Object) null);
            }
        } catch (Throwable th) {
            Log.e("ClipboardService", String.valueOf(th));
            if (th instanceof SecurityException) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.clipboard_permission_error, (Integer) null, 2, (Object) null);
            } else if (th instanceof TransactionTooLargeException) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.clipboard_too_large, (Integer) null, 2, (Object) null);
            } else {
                CommonActivity.INSTANCE.showToast(R.string.clipboard_unknown_error, (Integer) 1);
            }
        }
    }

    public final void setListViewHeightBasedOnItems(ListView listView) {
        ListAdapter adapter;
        if (listView == null || (adapter = listView.getAdapter()) == null) {
            return;
        }
        int count = adapter.getCount();
        int measuredHeight = 0;
        for (int i = 0; i < count; i++) {
            View view = adapter.getView(i, null, listView);
            Intrinsics.checkNotNullExpressionValue(view, "getView(...)");
            view.measure(0, 0);
            measuredHeight += view.getMeasuredHeight();
        }
        int dividerHeight = listView.getDividerHeight() * (count - 1);
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        Intrinsics.checkNotNullExpressionValue(layoutParams, "getLayoutParams(...)");
        layoutParams.height = measuredHeight + dividerHeight;
        listView.setLayoutParams(layoutParams);
        listView.requestLayout();
    }

    public static /* synthetic */ int getSpanCount$default(UIHelper uIHelper, Context context, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return uIHelper.getSpanCount(context, z);
    }

    public final int getSpanCount(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getResources().getConfiguration().orientation == 2 ? z ? 3 : 6 : z ? 2 : 3;
    }

    public final void hideKeyboard(Fragment fragment) {
        Window window;
        View decorView;
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        FragmentActivity activity = fragment.getActivity();
        if (activity != null && (window = activity.getWindow()) != null && (decorView = window.getDecorView()) != null) {
            decorView.clearFocus();
        }
        View view = fragment.getView();
        if (view != null) {
            INSTANCE.hideKeyboard(view);
        }
    }

    public final void setAppBarNoScrollFlagsOnTV(View view) {
        if (!Globals.INSTANCE.isLayout(6) || view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            AppBarLayout.LayoutParams layoutParams2 = (AppBarLayout.LayoutParams) layoutParams;
            layoutParams2.setScrollFlags(0);
            view.setLayoutParams(layoutParams2);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.google.android.material.appbar.AppBarLayout.LayoutParams");
    }

    public final void hideKeyboard(Activity activity) {
        View rootView;
        View decorView;
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Window window = activity.getWindow();
        if (window != null && (decorView = window.getDecorView()) != null) {
            decorView.clearFocus();
        }
        View viewFindViewById = activity.findViewById(android.R.id.content);
        if (viewFindViewById == null || (rootView = viewFindViewById.getRootView()) == null) {
            return;
        }
        INSTANCE.hideKeyboard(rootView);
    }

    public static /* synthetic */ void navigate$default(UIHelper uIHelper, Activity activity, int i, Bundle bundle, NavOptions navOptions, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bundle = null;
        }
        if ((i2 & 4) != 0) {
            navOptions = null;
        }
        uIHelper.navigate(activity, i, bundle, navOptions);
    }

    public final void navigate(final Activity activity, final int i, final Bundle bundle, final NavOptions navOptions) {
        final String str = "NavComponent";
        if (activity instanceof FragmentActivity) {
            try {
                ((FragmentActivity) activity).runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.utils.UIHelper$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        UIHelper.navigate$lambda$0(activity, str, i, bundle, navOptions);
                    }
                });
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
            }
        }
    }

    static final void navigate$lambda$0(Activity activity, String str, int i, Bundle bundle, NavOptions navOptions) {
        NavController navController;
        Fragment fragmentFindFragmentById = ((FragmentActivity) activity).getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavHostFragment navHostFragment = fragmentFindFragmentById instanceof NavHostFragment ? (NavHostFragment) fragmentFindFragmentById : null;
        Log.i(str, "Navigating to fragment: " + i);
        if (navHostFragment == null || (navController = navHostFragment.getNavController()) == null) {
            return;
        }
        navController.navigate(i, bundle, navOptions);
    }

    public static /* synthetic */ void openActivity$default(UIHelper uIHelper, Context context, Class cls, Bundle bundle, Intent intent, int i, Object obj) {
        if ((i & 2) != 0) {
            bundle = null;
        }
        if ((i & 4) != 0) {
            intent = null;
        }
        uIHelper.openActivity(context, cls, bundle, intent);
    }

    public final void openActivity(Context context, Class<?> activity, Bundle bundle, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (intent == null) {
            try {
                intent = new Intent();
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
                return;
            }
        }
        intent.setClass(context, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        Log.i("NavComponent", "Navigating to Activity: " + activity.getSimpleName());
        context.startActivity(intent);
    }

    public static /* synthetic */ void popCurrentPage$default(UIHelper uIHelper, FragmentActivity fragmentActivity, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        uIHelper.popCurrentPage(fragmentActivity, str);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.UIHelper$popCurrentPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: UIHelper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/fragment/app/FragmentActivity;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.UIHelper$popCurrentPage$1", f = "UIHelper.kt", i = {}, l = {299}, m = "invokeSuspend", n = {}, nl = {300}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<FragmentActivity, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $fromBackPressedCallback;
        final /* synthetic */ FragmentActivity $this_popCurrentPage;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, FragmentActivity fragmentActivity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$fromBackPressedCallback = str;
            this.$this_popCurrentPage = fragmentActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$fromBackPressedCallback, this.$this_popCurrentPage, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FragmentActivity fragmentActivity, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(fragmentActivity, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:22:0x0072  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$fromBackPressedCallback != null) {
                    BackPressedCallbackHelper.INSTANCE.disableBackPressedCallback(this.$this_popCurrentPage, this.$fromBackPressedCallback);
                }
                if (!this.$this_popCurrentPage.getSupportFragmentManager().isStateSaved()) {
                    Log.d("popFragment", "Destroying Fragment");
                    this.$this_popCurrentPage.getOnBackPressedDispatcher().onBackPressed();
                } else {
                    this.label = 1;
                    if (DelayKt.delay(100L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                if (this.$fromBackPressedCallback != null) {
                    BackPressedCallbackHelper.INSTANCE.enableBackPressedCallback(this.$this_popCurrentPage, this.$fromBackPressedCallback);
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (!this.$this_popCurrentPage.getSupportFragmentManager().isStateSaved()) {
                Log.d("popFragment", "Destroying after delay");
                this.$this_popCurrentPage.getOnBackPressedDispatcher().onBackPressed();
            }
            if (this.$fromBackPressedCallback != null) {
                BackPressedCallbackHelper.INSTANCE.enableBackPressedCallback(this.$this_popCurrentPage, this.$fromBackPressedCallback);
            }
            return Unit.INSTANCE;
        }
    }

    public final void popCurrentPage(FragmentActivity fragmentActivity, String str) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Coroutines.INSTANCE.main(fragmentActivity, new AnonymousClass1(str, fragmentActivity, null));
    }

    public static /* synthetic */ int getResourceColor$default(UIHelper uIHelper, Context context, int i, float f, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f = 1.0f;
        }
        return uIHelper.getResourceColor(context, i, f);
    }

    public final int getResourceColor(Context context, int i, float f) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        int iColorFromAttribute = colorFromAttribute(context, i);
        return f < 1.0f ? adjustAlpha(iColorFromAttribute, f) : iColorFromAttribute;
    }

    public final int colorFromAttribute(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, new int[]{i}, 0, 0);
        int color = typedArrayObtainStyledAttributes.getColor(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return color;
    }

    public final int adjustAlpha(int color, float factor) {
        return Color.argb(MathKt.roundToInt(((color >> 24) & 255) * factor), (color >> 16) & 255, (color >> 8) & 255, color & 255);
    }

    public final HashMap<String, Palette> getCreatePaletteAsyncCache() {
        return createPaletteAsyncCache;
    }

    public final void setCreatePaletteAsyncCache(HashMap<String, Palette> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        createPaletteAsyncCache = map;
    }

    public final void createPaletteAsync(final String url, Bitmap bitmap, final Function1<? super Palette, Unit> callback) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Palette palette = createPaletteAsyncCache.get(url);
        if (palette != null) {
            callback.invoke(palette);
        } else {
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() { // from class: com.lagradost.cloudstream3.utils.UIHelper$$ExternalSyntheticLambda5
                @Override // androidx.palette.graphics.Palette.PaletteAsyncListener
                public final void onGenerated(Palette palette2) {
                    UIHelper.createPaletteAsync$lambda$1(url, callback, palette2);
                }
            });
        }
    }

    static final void createPaletteAsync$lambda$1(String str, Function1 function1, Palette palette) {
        if (palette != null) {
            createPaletteAsyncCache.put(str, palette);
            function1.invoke(palette);
        }
    }

    public final void hideSystemUI(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        if (Build.VERSION.SDK_INT >= 30) {
            WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(activity.getWindow(), activity.getWindow().getDecorView());
            Intrinsics.checkNotNullExpressionValue(insetsController, "getInsetsController(...)");
            insetsController.setSystemBarsBehavior(2);
            insetsController.hide(WindowInsetsCompat.Type.systemBars());
            return;
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    public final void enableEdgeToEdgeCompat(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        if (Build.VERSION.SDK_INT < 30) {
            return;
        }
        WindowCompat.enableEdgeToEdge(activity.getWindow());
    }

    public final void setNavigationBarColorCompat(Activity activity, int i) {
        Window window;
        Intrinsics.checkNotNullParameter(activity, "<this>");
        if (Build.VERSION.SDK_INT < 30 && (window = activity.getWindow()) != null) {
            window.setNavigationBarColor(colorFromAttribute(activity, i));
        }
    }

    public final int getStatusBarHeight(Context context) {
        int identifier;
        Intrinsics.checkNotNullParameter(context, "<this>");
        if (!Globals.INSTANCE.isLayout(6) && (identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public final void fixPaddingStatusbarMargin(View v) {
        Context context;
        if (v == null || (context = v.getContext()) == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            ViewGroup.LayoutParams layoutParams2 = v.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
            int i = marginLayoutParams2 != null ? marginLayoutParams2.leftMargin : 0;
            ViewGroup.LayoutParams layoutParams3 = v.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : null;
            int statusBarHeight = (marginLayoutParams3 != null ? marginLayoutParams3.topMargin : 0) + INSTANCE.getStatusBarHeight(context);
            ViewGroup.LayoutParams layoutParams4 = v.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams4 = layoutParams4 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams4 : null;
            int i2 = marginLayoutParams4 != null ? marginLayoutParams4.rightMargin : 0;
            ViewGroup.LayoutParams layoutParams5 = v.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams5 = layoutParams5 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams5 : null;
            marginLayoutParams.setMargins(i, statusBarHeight, i2, marginLayoutParams5 != null ? marginLayoutParams5.bottomMargin : 0);
        }
        v.setLayoutParams(layoutParams);
    }

    public final void fixPaddingStatusbarView(View v) {
        Context context;
        if (v == null || (context = v.getContext()) == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        layoutParams.height = getStatusBarHeight(context);
        v.setLayoutParams(layoutParams);
    }

    public static /* synthetic */ void fixSystemBarsPadding$default(UIHelper uIHelper, View view, Integer num, Integer num2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            num2 = null;
        }
        if ((i & 8) != 0) {
            z = true;
        }
        if ((i & 16) != 0) {
            z2 = true;
        }
        if ((i & 32) != 0) {
            z3 = true;
        }
        if ((i & 64) != 0) {
            z4 = true;
        }
        if ((i & 128) != 0) {
            z5 = true;
        }
        if ((i & 256) != 0) {
            z6 = false;
        }
        uIHelper.fixSystemBarsPadding(view, num, num2, z, z2, z3, z4, z5, z6);
    }

    public final void fixSystemBarsPadding(View v, final Integer heightResId, final Integer widthResId, final boolean padTop, final boolean padBottom, final boolean padLeft, final boolean padRight, final boolean overlayCutout, final boolean fixIme) {
        Context context;
        Intrinsics.checkNotNullParameter(v, "v");
        if (Build.VERSION.SDK_INT >= 30) {
            ViewCompat.setOnApplyWindowInsetsListener(v, new OnApplyWindowInsetsListener() { // from class: com.lagradost.cloudstream3.utils.UIHelper$$ExternalSyntheticLambda3
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    return UIHelper.fixSystemBarsPadding$lambda$0(padRight, padLeft, fixIme, padBottom, padTop, heightResId, widthResId, overlayCutout, view, windowInsetsCompat);
                }
            });
        } else {
            if (!padTop || (context = v.getContext()) == null) {
                return;
            }
            v.setPadding(v.getPaddingLeft(), getStatusBarHeight(context), v.getPaddingRight(), v.getPaddingBottom());
        }
    }

    static final WindowInsetsCompat fixSystemBarsPadding$lambda$0(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, Integer num, Integer num2, boolean z6, View view, WindowInsetsCompat windowInsets) {
        DisplayCutoutCompat displayCutout;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        boolean z7 = AppContextUtils.INSTANCE.isRtl(view) ? z : z2;
        if (AppContextUtils.INSTANCE.isRtl(view)) {
            z = z2;
        }
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout() | (z3 ? WindowInsetsCompat.Type.ime() : 0));
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        view.setPadding(z7 ? insets.left : view.getPaddingLeft(), z5 ? insets.top : view.getPaddingTop(), z ? insets.right : view.getPaddingRight(), z4 ? insets.bottom : view.getPaddingBottom());
        if (num != null) {
            int dimensionPixelSize = view.getResources().getDimensionPixelSize(num.intValue());
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.height = dimensionPixelSize + insets.bottom;
                view.setLayoutParams(layoutParams);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            }
        }
        if (num2 != null) {
            int dimensionPixelSize2 = view.getResources().getDimensionPixelSize(num2.intValue());
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 != null) {
                int i = AppContextUtils.INSTANCE.isRtl(view) ? insets.right : insets.left;
                if (i > 0) {
                    dimensionPixelSize2 += i;
                }
                layoutParams2.width = dimensionPixelSize2;
                view.setLayoutParams(layoutParams2);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            }
        }
        if (z6 && Globals.INSTANCE.isLayout(1) && (displayCutout = windowInsets.getDisplayCutout()) != null) {
            int safeInsetLeft = !z7 ? 0 : displayCutout.getSafeInsetLeft();
            int safeInsetRight = z ? displayCutout.getSafeInsetRight() : 0;
            view.getOverlay().clear();
            if (safeInsetLeft > 0 || safeInsetRight > 0) {
                view.getOverlay().add(new CutoutOverlayDrawable(view, safeInsetLeft, safeInsetRight));
            }
        }
        return WindowInsetsCompat.CONSUMED;
    }

    public final int getNavigationBarHeight(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public final boolean isBottomLayout(Context context) {
        if (context == null) {
            return true;
        }
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.bottom_title_key), true);
    }

    public final void changeStatusBarState(Activity activity, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        try {
            if (z) {
                if (Build.VERSION.SDK_INT >= 30) {
                    WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(activity.getWindow(), activity.getWindow().getDecorView());
                    Intrinsics.checkNotNullExpressionValue(insetsController, "getInsetsController(...)");
                    insetsController.hide(WindowInsetsCompat.Type.statusBars());
                    return;
                }
                activity.getWindow().setFlags(1024, 1024);
                return;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                WindowInsetsControllerCompat insetsController2 = WindowCompat.getInsetsController(activity.getWindow(), activity.getWindow().getDecorView());
                Intrinsics.checkNotNullExpressionValue(insetsController2, "getInsetsController(...)");
                insetsController2.show(WindowInsetsCompat.Type.statusBars());
                return;
            }
            activity.getWindow().clearFlags(1024);
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
        }
    }

    public final void showSystemUI(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        if (Build.VERSION.SDK_INT >= 30) {
            WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(activity.getWindow(), activity.getWindow().getDecorView());
            Intrinsics.checkNotNullExpressionValue(insetsController, "getInsetsController(...)");
            if (Globals.INSTANCE.isLayout(4)) {
                insetsController.show(WindowInsetsCompat.Type.navigationBars());
                insetsController.hide(WindowInsetsCompat.Type.statusBars());
                return;
            } else {
                insetsController.show(WindowInsetsCompat.Type.systemBars());
                return;
            }
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(1280);
        changeStatusBarState(activity, Globals.INSTANCE.isLayout(4));
    }

    public final void hideKeyboard(View view) {
        InputMethodManager inputMethodManager;
        if (view == null || (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public final void showInputMethod(View view) {
        InputMethodManager inputMethodManager;
        if (view == null || (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.showSoftInput(view, 0);
    }

    public final void dismissSafe(Dialog dialog, Activity activity) {
        if (dialog == null || !dialog.isShowing() || activity == null || activity.isFinishing()) {
            return;
        }
        dialog.dismiss();
    }

    public final void dismissSafe(Dialog dialog) {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        Activity activity = CommonActivity.INSTANCE.getActivity();
        if (activity == null || !activity.isFinishing()) {
            dialog.dismiss();
        }
    }

    public static /* synthetic */ void showProgress$default(UIHelper uIHelper, MaterialButton materialButton, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = materialButton.getIconTint().getDefaultColor();
        }
        uIHelper.showProgress(materialButton, i);
    }

    public final void showProgress(final MaterialButton materialButton, final int i) {
        Intrinsics.checkNotNullParameter(materialButton, "<this>");
        Coroutines_androidKt.runOnMainThreadNative(new Function0() { // from class: com.lagradost.cloudstream3.utils.UIHelper$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UIHelper.showProgress$lambda$0(materialButton, i);
            }
        });
    }

    static final Unit showProgress$lambda$0(MaterialButton materialButton, int i) {
        if (materialButton.getIcon() instanceof IndeterminateDrawable) {
            return Unit.INSTANCE;
        }
        CircularProgressIndicatorSpec circularProgressIndicatorSpec = new CircularProgressIndicatorSpec(materialButton.getContext(), null, 0, com.google.android.material.R.style.Widget_Material3_CircularProgressIndicator_ExtraSmall);
        circularProgressIndicatorSpec.indicatorColors = new int[]{i};
        IndeterminateDrawable<CircularProgressIndicatorSpec> indeterminateDrawableCreateCircularDrawable = IndeterminateDrawable.createCircularDrawable(materialButton.getContext(), circularProgressIndicatorSpec);
        Intrinsics.checkNotNullExpressionValue(indeterminateDrawableCreateCircularDrawable, "createCircularDrawable(...)");
        materialButton.m8217x11712a47(indeterminateDrawableCreateCircularDrawable);
        if (materialButton.getTag(R.id.text1) == null) {
            materialButton.setTag(R.id.text1, materialButton.getText());
        }
        materialButton.setText("");
        return Unit.INSTANCE;
    }

    public final void hideProgress(final MaterialButton materialButton) {
        Intrinsics.checkNotNullParameter(materialButton, "<this>");
        Coroutines_androidKt.runOnMainThreadNative(new Function0() { // from class: com.lagradost.cloudstream3.utils.UIHelper$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UIHelper.hideProgress$lambda$0(materialButton);
            }
        });
    }

    static final Unit hideProgress$lambda$0(MaterialButton materialButton) {
        Object tag = materialButton.getTag(R.id.text1);
        materialButton.setText(tag instanceof String ? (String) tag : null);
        materialButton.m8217x11712a47(null);
        return Unit.INSTANCE;
    }

    public final PopupMenu popupMenuNoIcons(View view, List<Pair<Integer, Integer>> items, final Function1<? super MenuItem, Unit> onMenuItemClick) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(onMenuItemClick, "onMenuItemClick");
        PopupMenu popupMenu = new PopupMenu(new ContextThemeWrapper(view.getContext(), R.style.PopupMenu), view, 0, androidx.appcompat.R.attr.actionOverflowMenuStyle, 0);
        Iterator<T> it = items.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            popupMenu.getMenu().add(0, ((Number) pair.component1()).intValue(), 0, ((Number) pair.component2()).intValue());
        }
        Menu menu = popupMenu.getMenu();
        MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
        if (menuBuilder != null) {
            menuBuilder.setOptionalIconsVisible(true);
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.lagradost.cloudstream3.utils.UIHelper$$ExternalSyntheticLambda4
            @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return UIHelper.popupMenuNoIcons$lambda$1(onMenuItemClick, menuItem);
            }
        });
        popupMenu.show();
        return popupMenu;
    }

    static final boolean popupMenuNoIcons$lambda$1(Function1 function1, MenuItem menuItem) {
        Intrinsics.checkNotNull(menuItem);
        function1.invoke(menuItem);
        return true;
    }

    public final PopupMenu popupMenuNoIconsAndNoStringRes(View view, List<Pair<Integer, String>> items, final Function1<? super MenuItem, Unit> onMenuItemClick) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(onMenuItemClick, "onMenuItemClick");
        PopupMenu popupMenu = new PopupMenu(new ContextThemeWrapper(view.getContext(), R.style.PopupMenu), view, 0, androidx.appcompat.R.attr.actionOverflowMenuStyle, 0);
        Iterator<T> it = items.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            popupMenu.getMenu().add(0, ((Number) pair.component1()).intValue(), 0, (String) pair.component2());
        }
        Menu menu = popupMenu.getMenu();
        MenuBuilder menuBuilder = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
        if (menuBuilder != null) {
            menuBuilder.setOptionalIconsVisible(true);
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.lagradost.cloudstream3.utils.UIHelper$$ExternalSyntheticLambda0
            @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return UIHelper.popupMenuNoIconsAndNoStringRes$lambda$1(onMenuItemClick, menuItem);
            }
        });
        popupMenu.show();
        return popupMenu;
    }

    static final boolean popupMenuNoIconsAndNoStringRes$lambda$1(Function1 function1, MenuItem menuItem) {
        Intrinsics.checkNotNull(menuItem);
        function1.invoke(menuItem);
        return true;
    }
}
