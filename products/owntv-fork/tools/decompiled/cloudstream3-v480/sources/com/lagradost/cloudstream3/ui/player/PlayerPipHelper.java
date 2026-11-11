package com.lagradost.cloudstream3.ui.player;

import android.app.Activity;
import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Process;
import android.util.Rational;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.CommonActivity$$ExternalSyntheticApiModelOutline0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PlayerPipHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006J\f\u0010\u0007\u001a\u00020\u0005*\u00020\u0006H\u0002J\f\u0010\b\u001a\u00020\u0005*\u00020\u0006H\u0002J\f\u0010\t\u001a\u00020\u0005*\u00020\u0006H\u0002J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0003J*\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u000f2\b\b\u0001\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0003J*\u0010\u0016\u001a\u00020\u00172\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PlayerPipHelper;", "", "<init>", "()V", "isPIPPossible", "", "Landroid/content/Context;", "hasPIPEnabled", "hasPIPFeature", "hasPIPPermission", "getPen", "Landroid/app/PendingIntent;", "activity", "Landroid/app/Activity;", "code", "", "getRemoteAction", "Landroid/app/RemoteAction;", "id", "title", NotificationCompat.CATEGORY_EVENT, "Lcom/lagradost/cloudstream3/ui/player/CSPlayerEvent;", "updatePIPModeActions", "", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/ui/player/CSPlayerLoading;", "pipEnabled", "aspectRatio", "Landroid/util/Rational;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PlayerPipHelper {
    public static final PlayerPipHelper INSTANCE = new PlayerPipHelper();

    /* JADX INFO: compiled from: PlayerPipHelper.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CSPlayerLoading.values().length];
            try {
                iArr[CSPlayerLoading.IsBuffering.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CSPlayerLoading.IsPlaying.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private PlayerPipHelper() {
    }

    public final boolean isPIPPossible(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            return hasPIPEnabled(context) && hasPIPFeature(context);
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return false;
        }
    }

    private final boolean hasPIPEnabled(Context context) {
        try {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (defaultSharedPreferences != null) {
                return defaultSharedPreferences.getBoolean(context.getString(R.string.pip_enabled_key), true);
            }
            return true;
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return false;
        }
    }

    private final boolean hasPIPFeature(Context context) {
        return Build.VERSION.SDK_INT >= 24 && context.getPackageManager().hasSystemFeature("android.software.picture_in_picture") && hasPIPPermission(context);
    }

    private final boolean hasPIPPermission(Context context) {
        Object systemService = context.getSystemService("appops");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.AppOpsManager");
        return Build.VERSION.SDK_INT < 26 || ((AppOpsManager) systemService).checkOpNoThrow("android:picture_in_picture", Process.myUid(), context.getPackageName()) == 0;
    }

    private final PendingIntent getPen(Activity activity, int code) {
        PendingIntent broadcast = PendingIntent.getBroadcast(activity, code, new Intent(IPlayerKt.ACTION_MEDIA_CONTROL).putExtra(IPlayerKt.EXTRA_CONTROL_TYPE, code), 67108864);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
        return broadcast;
    }

    private final RemoteAction getRemoteAction(Activity activity, int id, int title, CSPlayerEvent event) {
        String string = activity.getString(title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        CommonActivity$$ExternalSyntheticApiModelOutline0.m$2();
        String str = string;
        return CommonActivity$$ExternalSyntheticApiModelOutline0.m(Icon.createWithResource(activity, id), str, str, getPen(activity, event.getValue()));
    }

    public final void updatePIPModeActions(final Activity activity, CSPlayerLoading status, final boolean pipEnabled, Rational aspectRatio) {
        Intrinsics.checkNotNullParameter(status, "status");
        int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
        if (i != 1 && i != 2) {
            pipEnabled = false;
        }
        if (Build.VERSION.SDK_INT < 26) {
            CommonActivity.INSTANCE.setPipDesired(pipEnabled);
            return;
        }
        if (activity == null) {
            return;
        }
        final ArrayList arrayList = new ArrayList();
        arrayList.add(getRemoteAction(activity, R.drawable.baseline_headphones_24, R.string.audio_singular, CSPlayerEvent.PlayAsAudio));
        if (status == CSPlayerLoading.IsPlaying) {
            arrayList.add(getRemoteAction(activity, R.drawable.netflix_pause, R.string.pause, CSPlayerEvent.Pause));
        } else {
            arrayList.add(getRemoteAction(activity, R.drawable.ic_baseline_play_arrow_24, R.string.pause, CSPlayerEvent.Play));
        }
        arrayList.add(getRemoteAction(activity, R.drawable.go_forward_30, R.string.go_forward_30, CSPlayerEvent.SeekForward));
        final Rational rational = aspectRatio != null ? new Rational(MathKt.roundToInt(RangesKt.coerceIn(aspectRatio.floatValue(), 0.41841f, 2.39f) * 100000.0f), 100000) : null;
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.PlayerPipHelper$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PlayerPipHelper.updatePIPModeActions$lambda$1(activity, rational, arrayList, pipEnabled);
            }
        });
    }

    static final Unit updatePIPModeActions$lambda$1(Activity activity, Rational rational, ArrayList arrayList, boolean z) {
        PictureInPictureParams.Builder builderM8386m = CommonActivity$$ExternalSyntheticApiModelOutline0.m8386m();
        if (Build.VERSION.SDK_INT >= 31) {
            builderM8386m.setSeamlessResizeEnabled(true);
            builderM8386m.setAutoEnterEnabled(z && INSTANCE.isPIPPossible(activity));
        } else {
            CommonActivity.INSTANCE.setPipDesired(z);
        }
        activity.setPictureInPictureParams(builderM8386m.setAspectRatio(rational).setActions(arrayList).build());
        return Unit.INSTANCE;
    }
}
