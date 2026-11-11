package androidx.media3.ui;

import android.media.MediaParser$InputReader;
import android.media.MediaRoute2Info;
import android.media.RoutingSessionInfo;
import android.media.Spatializer;
import android.view.SurfaceControl;
import android.window.SurfaceSyncGroup;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class DefaultTimeBar$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ MediaParser$InputReader m(Object obj) {
        return (MediaParser$InputReader) obj;
    }

    public static /* synthetic */ MediaRoute2Info.Builder m(String str, CharSequence charSequence) {
        return new MediaRoute2Info.Builder(str, charSequence);
    }

    public static /* synthetic */ RoutingSessionInfo.Builder m(RoutingSessionInfo routingSessionInfo) {
        return new RoutingSessionInfo.Builder(routingSessionInfo);
    }

    public static /* synthetic */ RoutingSessionInfo.Builder m(String str, String str2) {
        return new RoutingSessionInfo.Builder(str, str2);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Spatializer m7854m(Object obj) {
        return (Spatializer) obj;
    }

    public static /* synthetic */ SurfaceControl.Transaction m() {
        return new SurfaceControl.Transaction();
    }

    public static /* synthetic */ SurfaceSyncGroup m(String str) {
        return new SurfaceSyncGroup(str);
    }
}
