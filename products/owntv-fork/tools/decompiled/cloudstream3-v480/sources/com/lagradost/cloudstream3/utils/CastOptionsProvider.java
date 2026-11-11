package com.lagradost.cloudstream3.utils;

import android.content.Context;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.OptionsProvider;
import com.google.android.gms.cast.framework.SessionProvider;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.ui.ControllerActivity;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: CastOptionsProvider.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/CastOptionsProvider;", "Lcom/google/android/gms/cast/framework/OptionsProvider;", "<init>", "()V", "getCastOptions", "Lcom/google/android/gms/cast/framework/CastOptions;", "context", "Landroid/content/Context;", "getAdditionalSessionProviders", "", "Lcom/google/android/gms/cast/framework/SessionProvider;", "p0", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CastOptionsProvider implements OptionsProvider {
    @Override // com.google.android.gms.cast.framework.OptionsProvider
    public CastOptions getCastOptions(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        List<String> listListOf = CollectionsKt.listOf((Object[]) new String[]{MediaIntentReceiver.ACTION_REWIND, MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK, MediaIntentReceiver.ACTION_FORWARD, MediaIntentReceiver.ACTION_STOP_CASTING});
        String qualifiedName = Reflection.getOrCreateKotlinClass(ControllerActivity.class).getQualifiedName();
        Intrinsics.checkNotNull(qualifiedName);
        NotificationOptions notificationOptionsBuild = new NotificationOptions.Builder().setTargetActivityClassName(qualifiedName).setActions(listListOf, new int[]{1, 3}).setForward30DrawableResId(R.drawable.go_forward_30).setRewind30DrawableResId(R.drawable.go_back_30).setSkipStepMs(30000L).build();
        Intrinsics.checkNotNullExpressionValue(notificationOptionsBuild, "build(...)");
        CastMediaOptions castMediaOptionsBuild = new CastMediaOptions.Builder().setNotificationOptions(notificationOptionsBuild).setExpandedControllerActivityClassName(qualifiedName).build();
        Intrinsics.checkNotNullExpressionValue(castMediaOptionsBuild, "build(...)");
        CastOptions castOptionsBuild = new CastOptions.Builder().setReceiverApplicationId(CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID).setStopReceiverApplicationWhenEndingSession(true).setCastMediaOptions(castMediaOptionsBuild).build();
        Intrinsics.checkNotNullExpressionValue(castOptionsBuild, "build(...)");
        return castOptionsBuild;
    }

    @Override // com.google.android.gms.cast.framework.OptionsProvider
    public List<SessionProvider> getAdditionalSessionProviders(Context p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        List<SessionProvider> list = Collections.EMPTY_LIST;
        Intrinsics.checkNotNullExpressionValue(list, "emptyList(...)");
        return list;
    }
}
