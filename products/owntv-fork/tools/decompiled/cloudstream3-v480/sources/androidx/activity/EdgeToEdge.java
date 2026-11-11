package androidx.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.core.view.ViewGroupKt;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EdgeToEdge.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u000b\u001a\u00020\f*\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0002\b\u0011\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001c\u0010\u0006\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\u0005\"\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"DefaultLightScrim", "", "getDefaultLightScrim$annotations", "()V", "getDefaultLightScrim", "()I", "DefaultDarkScrim", "getDefaultDarkScrim$annotations", "getDefaultDarkScrim", "Impl", "Landroidx/activity/EdgeToEdgeImpl;", "enableEdgeToEdge", "", "Landroidx/activity/ComponentActivity;", "statusBarStyle", "Landroidx/activity/SystemBarStyle;", "navigationBarStyle", "enable", "activity"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class EdgeToEdge {
    private static EdgeToEdgeImpl Impl;
    private static final int DefaultLightScrim = Color.argb(230, 255, 255, 255);
    private static final int DefaultDarkScrim = Color.argb(128, 27, 27, 27);

    public static final void enable(ComponentActivity componentActivity) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        enable$default(componentActivity, null, null, 3, null);
    }

    public static final void enable(ComponentActivity componentActivity, SystemBarStyle statusBarStyle) {
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        Intrinsics.checkNotNullParameter(statusBarStyle, "statusBarStyle");
        enable$default(componentActivity, statusBarStyle, null, 2, null);
    }

    public static /* synthetic */ void getDefaultDarkScrim$annotations() {
    }

    public static /* synthetic */ void getDefaultLightScrim$annotations() {
    }

    public static final int getDefaultLightScrim() {
        return DefaultLightScrim;
    }

    public static final int getDefaultDarkScrim() {
        return DefaultDarkScrim;
    }

    public static /* synthetic */ void enable$default(ComponentActivity componentActivity, SystemBarStyle systemBarStyle, SystemBarStyle systemBarStyle2, int i, Object obj) {
        if ((i & 1) != 0) {
            systemBarStyle = SystemBarStyle.Companion.auto$default(SystemBarStyle.INSTANCE, 0, 0, null, 4, null);
        }
        if ((i & 2) != 0) {
            systemBarStyle2 = SystemBarStyle.Companion.auto$default(SystemBarStyle.INSTANCE, DefaultLightScrim, DefaultDarkScrim, null, 4, null);
        }
        enable(componentActivity, systemBarStyle, systemBarStyle2);
    }

    /* JADX WARN: Type inference failed for: r8v2, types: [androidx.activity.EdgeToEdge$enableEdgeToEdge$1$2] */
    public static final void enable(final ComponentActivity componentActivity, final SystemBarStyle statusBarStyle, final SystemBarStyle navigationBarStyle) {
        EdgeToEdgeApi23 edgeToEdgeApi23;
        Intrinsics.checkNotNullParameter(componentActivity, "<this>");
        Intrinsics.checkNotNullParameter(statusBarStyle, "statusBarStyle");
        Intrinsics.checkNotNullParameter(navigationBarStyle, "navigationBarStyle");
        final View decorView = componentActivity.getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
        EdgeToEdgeImpl edgeToEdgeImpl = Impl;
        if (edgeToEdgeImpl == null) {
            if (Build.VERSION.SDK_INT >= 35) {
                edgeToEdgeApi23 = new EdgeToEdgeApi35();
            } else if (Build.VERSION.SDK_INT >= 30) {
                edgeToEdgeApi23 = new EdgeToEdgeApi30();
            } else if (Build.VERSION.SDK_INT >= 29) {
                edgeToEdgeApi23 = new EdgeToEdgeApi29();
            } else if (Build.VERSION.SDK_INT >= 28) {
                edgeToEdgeApi23 = new EdgeToEdgeApi28();
            } else if (Build.VERSION.SDK_INT >= 26) {
                edgeToEdgeApi23 = new EdgeToEdgeApi26();
            } else {
                edgeToEdgeApi23 = new EdgeToEdgeApi23();
            }
            edgeToEdgeImpl = edgeToEdgeApi23;
            Impl = edgeToEdgeImpl;
        }
        final EdgeToEdgeImpl edgeToEdgeImpl2 = edgeToEdgeImpl;
        final Runnable runnable = new Runnable() { // from class: androidx.activity.EdgeToEdge$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EdgeToEdge.enableEdgeToEdge$lambda$1(edgeToEdgeImpl2, statusBarStyle, navigationBarStyle, componentActivity, decorView);
            }
        };
        ViewGroup viewGroup = (ViewGroup) decorView;
        Iterator<View> it = ViewGroupKt.getChildren(viewGroup).iterator();
        while (it.hasNext()) {
            if (it.next().getTag() instanceof EdgeToEdgeImpl) {
                runnable.run();
                Window window = componentActivity.getWindow();
                Intrinsics.checkNotNullExpressionValue(window, "getWindow(...)");
                edgeToEdgeImpl2.adjustLayoutInDisplayCutoutMode(window);
            }
        }
        final Context context = viewGroup.getContext();
        ?? r8 = new View(context) { // from class: androidx.activity.EdgeToEdge$enableEdgeToEdge$1$2
            @Override // android.view.View
            protected void onConfigurationChanged(Configuration newConfig) {
                Intrinsics.checkNotNullParameter(newConfig, "newConfig");
                runnable.run();
            }
        };
        r8.setTag(edgeToEdgeImpl2);
        r8.setVisibility(8);
        r8.setWillNotDraw(true);
        viewGroup.addView((View) r8);
        runnable.run();
        Window window2 = componentActivity.getWindow();
        Intrinsics.checkNotNullExpressionValue(window2, "getWindow(...)");
        edgeToEdgeImpl2.adjustLayoutInDisplayCutoutMode(window2);
    }

    static final void enableEdgeToEdge$lambda$1(EdgeToEdgeImpl edgeToEdgeImpl, SystemBarStyle systemBarStyle, SystemBarStyle systemBarStyle2, ComponentActivity componentActivity, View view) {
        Window window = componentActivity.getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "getWindow(...)");
        Function1<Resources, Boolean> detectDarkMode$activity = systemBarStyle.getDetectDarkMode$activity();
        Resources resources = view.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
        boolean zBooleanValue = detectDarkMode$activity.invoke(resources).booleanValue();
        Function1<Resources, Boolean> detectDarkMode$activity2 = systemBarStyle2.getDetectDarkMode$activity();
        Resources resources2 = view.getResources();
        Intrinsics.checkNotNullExpressionValue(resources2, "getResources(...)");
        edgeToEdgeImpl.setUp(systemBarStyle, systemBarStyle2, window, view, zBooleanValue, detectDarkMode$activity2.invoke(resources2).booleanValue());
    }
}
