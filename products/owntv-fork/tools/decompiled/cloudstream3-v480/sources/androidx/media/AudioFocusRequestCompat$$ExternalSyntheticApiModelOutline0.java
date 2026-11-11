package androidx.media;

import android.app.ForegroundServiceStartNotAllowedException;
import android.media.AudioFocusRequest;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputContentInfo;
import java.util.Map;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ ForegroundServiceStartNotAllowedException m(Object obj) {
        return (ForegroundServiceStartNotAllowedException) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ AudioFocusRequest m7345m(Object obj) {
        return (AudioFocusRequest) obj;
    }

    public static /* synthetic */ android.media.session.MediaSessionManager.RemoteUserInfo m(String str, int i, int i2) {
        return new android.media.session.MediaSessionManager.RemoteUserInfo(str, i, i2);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ WindowInsetsController.OnControllableInsetsChangedListener m7347m(Object obj) {
        return (WindowInsetsController.OnControllableInsetsChangedListener) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ AccessibilityNodeInfo.CollectionItemInfo.Builder m7349m() {
        return new AccessibilityNodeInfo.CollectionItemInfo.Builder();
    }

    public static /* synthetic */ AccessibilityNodeInfo.TouchDelegateInfo m(Map map) {
        return new AccessibilityNodeInfo.TouchDelegateInfo(map);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ InputContentInfo m7351m(Object obj) {
        return (InputContentInfo) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m7357m(Object obj) {
        return obj instanceof ForegroundServiceStartNotAllowedException;
    }
}
