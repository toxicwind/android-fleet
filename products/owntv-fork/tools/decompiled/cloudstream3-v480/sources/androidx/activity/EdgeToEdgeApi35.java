package androidx.activity;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.core.view.ViewGroupKt;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.core.view.insets.ColorProtection;
import androidx.core.view.insets.Protection;
import androidx.core.view.insets.ProtectionLayout;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EdgeToEdge.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0017¨\u0006\u0010"}, d2 = {"Landroidx/activity/EdgeToEdgeApi35;", "Landroidx/activity/EdgeToEdgeApi30;", "<init>", "()V", "setUp", "", "statusBarStyle", "Landroidx/activity/SystemBarStyle;", "navigationBarStyle", "window", "Landroid/view/Window;", "view", "Landroid/view/View;", "statusBarIsDark", "", "navigationBarIsDark", "activity"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class EdgeToEdgeApi35 extends EdgeToEdgeApi30 {
    @Override // androidx.activity.EdgeToEdgeApi29, androidx.activity.EdgeToEdgeApi26, androidx.activity.EdgeToEdgeImpl
    public void setUp(SystemBarStyle statusBarStyle, SystemBarStyle navigationBarStyle, Window window, View view, boolean statusBarIsDark, boolean navigationBarIsDark) {
        Intrinsics.checkNotNullParameter(statusBarStyle, "statusBarStyle");
        Intrinsics.checkNotNullParameter(navigationBarStyle, "navigationBarStyle");
        Intrinsics.checkNotNullParameter(window, "window");
        Intrinsics.checkNotNullParameter(view, "view");
        WindowCompat.setDecorFitsSystemWindows(window, false);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
        int scrimWithEnforcedContrast$activity = statusBarStyle.getScrimWithEnforcedContrast$activity(statusBarIsDark);
        int scrimWithEnforcedContrast$activity2 = navigationBarStyle.getScrimWithEnforcedContrast$activity(navigationBarIsDark);
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup != null) {
            Iterator<View> it = ViewGroupKt.getChildren(viewGroup).iterator();
            while (true) {
                if (!it.hasNext()) {
                    if (scrimWithEnforcedContrast$activity != 0 || scrimWithEnforcedContrast$activity2 != 0) {
                        List listListOf = CollectionsKt.listOf((Object[]) new ColorProtection[]{new ColorProtection(2, scrimWithEnforcedContrast$activity), new ColorProtection(1, scrimWithEnforcedContrast$activity2), new ColorProtection(4, scrimWithEnforcedContrast$activity2), new ColorProtection(8, scrimWithEnforcedContrast$activity2)});
                        ProtectionLayout protectionLayout = new ProtectionLayout(((ViewGroup) view).getContext(), (List<Protection>) listListOf);
                        protectionLayout.setTag(listListOf);
                        viewGroup.addView(protectionLayout);
                        break;
                    }
                    break;
                }
                Object tag = it.next().getTag();
                if (tag instanceof List) {
                    List list = (List) tag;
                    if (list.size() == 4 && (list.get(0) instanceof ColorProtection)) {
                        for (Object obj : (Iterable) tag) {
                            if ((obj instanceof ColorProtection ? (ColorProtection) obj : null) != null) {
                                ColorProtection colorProtection = (ColorProtection) obj;
                                int side = colorProtection.getSide();
                                if (side == 1) {
                                    colorProtection.setColor(scrimWithEnforcedContrast$activity2);
                                } else if (side == 2) {
                                    colorProtection.setColor(scrimWithEnforcedContrast$activity);
                                } else if (side == 4) {
                                    colorProtection.setColor(scrimWithEnforcedContrast$activity2);
                                } else if (side == 8) {
                                    colorProtection.setColor(scrimWithEnforcedContrast$activity2);
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        window.setNavigationBarContrastEnforced(navigationBarStyle.getNightMode() == 0);
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(window, view);
        windowInsetsControllerCompat.setAppearanceLightStatusBars(!statusBarIsDark);
        windowInsetsControllerCompat.setAppearanceLightNavigationBars(!navigationBarIsDark);
    }
}
