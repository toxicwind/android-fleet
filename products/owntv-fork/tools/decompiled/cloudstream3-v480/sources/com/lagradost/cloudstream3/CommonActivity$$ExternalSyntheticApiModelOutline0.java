package com.lagradost.cloudstream3;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Icon;
import android.media.AudioFocusRequest;
import android.net.nsd.NsdManager$ServiceInfoCallback;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes6.dex */
public final /* synthetic */ class CommonActivity$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ NotificationChannel m(String str, CharSequence charSequence, int i) {
        return new NotificationChannel(str, charSequence, i);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ PictureInPictureParams.Builder m8386m() {
        return new PictureInPictureParams.Builder();
    }

    public static /* synthetic */ RemoteAction m(Icon icon, CharSequence charSequence, CharSequence charSequence2, PendingIntent pendingIntent) {
        return new RemoteAction(icon, charSequence, charSequence2, pendingIntent);
    }

    public static /* bridge */ /* synthetic */ AnimatedImageDrawable m(Object obj) {
        return (AnimatedImageDrawable) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ AudioFocusRequest.Builder m8387m(int i) {
        return new AudioFocusRequest.Builder(i);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ NsdManager$ServiceInfoCallback m8388m(Object obj) {
        return (NsdManager$ServiceInfoCallback) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m8390m() {
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m8393m(Object obj) {
        return obj instanceof AnimatedImageDrawable;
    }

    public static /* synthetic */ void m$1() {
    }

    public static /* synthetic */ void m$2() {
    }
}
