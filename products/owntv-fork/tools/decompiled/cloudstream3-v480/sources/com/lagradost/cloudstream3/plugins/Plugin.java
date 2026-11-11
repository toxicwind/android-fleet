package com.lagradost.cloudstream3.plugins;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.lagradost.cloudstream3.actions.VideoClickAction;
import com.lagradost.cloudstream3.actions.VideoClickActionHolder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Plugin.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R7\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/plugins/Plugin;", "Lcom/lagradost/cloudstream3/plugins/BasePlugin;", "<init>", "()V", "load", "", "context", "Landroid/content/Context;", "registerVideoClickAction", "element", "Lcom/lagradost/cloudstream3/actions/VideoClickAction;", "resources", "Landroid/content/res/Resources;", "getResources", "()Landroid/content/res/Resources;", "setResources", "(Landroid/content/res/Resources;)V", "openSettings", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "getOpenSettings", "()Lkotlin/jvm/functions/Function1;", "setOpenSettings", "(Lkotlin/jvm/functions/Function1;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class Plugin extends BasePlugin {
    private Function1<? super Context, Unit> openSettings;
    private Resources resources;

    public void load(Context context) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "context");
        load();
    }

    public final void registerVideoClickAction(VideoClickAction element) {
        Intrinsics.checkNotNullParameter(element, "element");
        Log.i(BasePluginKt.PLUGIN_TAG, "Adding " + element.getName() + " VideoClickAction");
        element.setSourcePlugin(getFilename());
        VideoClickActionHolder.INSTANCE.getAllVideoClickActions().add(element);
    }

    public final Resources getResources() {
        return this.resources;
    }

    public final void setResources(Resources resources) {
        this.resources = resources;
    }

    public final Function1<Context, Unit> getOpenSettings() {
        return this.openSettings;
    }

    public final void setOpenSettings(Function1<? super Context, Unit> function1) {
        this.openSettings = function1;
    }
}
