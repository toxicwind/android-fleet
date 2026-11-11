package com.lagradost.cloudstream3.ui.player;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.MediaSession;
import androidx.media3.ui.DefaultTimeBar;
import androidx.media3.ui.R;
import androidx.media3.ui.SubtitleView;
import androidx.media3.ui.TimeBar;
import androidx.preference.PreferenceManager;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import com.github.rubensousa.previewseekbar.PreviewBar;
import com.github.rubensousa.previewseekbar.PreviewLoader;
import com.github.rubensousa.previewseekbar.media3.PreviewTimeBar;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.CommonActivity$$ExternalSyntheticApiModelOutline0;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.player.live.LivePreviewTimeBar;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.ui.subtitles.SaveCaptionStyle;
import com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UserPreferenceDelegate;
import com.lagradost.cloudstream3.utils.videoskip.VideoSkipStamp;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.DebugKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PlayerView.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 î\u00012\u00020\u0001:\u0004î\u0001ï\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\u0006\u0010 \u001a\u00020\u001eJ\u0006\u0010!\u001a\u00020\u001eJ\u0006\u0010\"\u001a\u00020\u001eJ\u0010\u0010Á\u0001\u001a\u00020\u001e2\u0007\u0010Â\u0001\u001a\u00020\u0014J\u0007\u0010Ã\u0001\u001a\u00020\u001eJ\u0019\u0010Ç\u0001\u001a\u00020\u001e2\u0010\b\u0002\u0010È\u0001\u001a\t\u0012\u0004\u0012\u00020\u001e0É\u0001J\u0007\u0010Ê\u0001\u001a\u00020\u001eJ\u0007\u0010Ë\u0001\u001a\u00020\u001eJ\u0010\u0010Ì\u0001\u001a\u00020\u001e2\u0007\u0010Í\u0001\u001a\u00020\u0003J\u0007\u0010Î\u0001\u001a\u00020\u001eJ\u001c\u0010Ï\u0001\u001a\u00020\u001e2\u0007\u0010Ð\u0001\u001a\u00020\r2\n\u0010Ñ\u0001\u001a\u0005\u0018\u00010Ò\u0001J\u0012\u0010Ó\u0001\u001a\u00020\u001e2\u0007\u0010Ô\u0001\u001a\u00020\rH\u0002J\u0019\u0010Õ\u0001\u001a\u00020\u001e2\u0007\u0010Ö\u0001\u001a\u0002082\u0007\u0010×\u0001\u001a\u000208J\t\u0010Ø\u0001\u001a\u00020\u001eH\u0002J\u0014\u0010Ù\u0001\u001a\u00020\u001e2\t\u0010)\u001a\u0005\u0018\u00010Ú\u0001H\u0002J\u0013\u0010Û\u0001\u001a\u00020\u001e2\b\u0010Ü\u0001\u001a\u00030À\u0001H\u0002J\u0013\u0010Ý\u0001\u001a\u00020\u001e2\b\u0010Þ\u0001\u001a\u00030ß\u0001H\u0007J\u0007\u0010à\u0001\u001a\u00020\u001eJ\u0019\u0010á\u0001\u001a\u00020\u001e2\u0007\u0010á\u0001\u001a\u00020\u001c2\u0007\u0010â\u0001\u001a\u00020\rJ\u001a\u0010á\u0001\u001a\u00020\u001e2\b\u0010á\u0001\u001a\u00030ã\u00012\u0007\u0010â\u0001\u001a\u00020\rJ\u0007\u0010ä\u0001\u001a\u00020\u001cJ\u0013\u0010å\u0001\u001a\u00020\u001e2\b\u0010æ\u0001\u001a\u00030ç\u0001H\u0007J\u0010\u0010è\u0001\u001a\u00020\u001e2\u0007\u0010é\u0001\u001a\u00020\rJ\u0007\u0010ê\u0001\u001a\u00020\u001eJ\u0013\u0010ë\u0001\u001a\u00020\u001e2\n\b\u0002\u0010ì\u0001\u001a\u00030®\u0001J\u0007\u0010í\u0001\u001a\u00020\u001eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R(\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\f\u001a\u0004\u0018\u00010\u00148F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u000f\"\u0004\b6\u0010\u0011R\u001a\u00107\u001a\u000208X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001c\u0010=\u001a\u0004\u0018\u00010>X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u0010\u0010C\u001a\u0004\u0018\u00010DX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020GX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010H\u001a\u0004\u0018\u00010IX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u001c\u0010N\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u001c\u0010S\u001a\u0004\u0018\u00010TX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR\u001c\u0010Y\u001a\u0004\u0018\u00010ZX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u001c\u0010_\u001a\u0004\u0018\u00010`X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010b\"\u0004\bc\u0010dR\u001c\u0010e\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010P\"\u0004\bg\u0010RR\u001c\u0010h\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010P\"\u0004\bj\u0010RR\u001c\u0010k\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010\u0017\"\u0004\bm\u0010\u0019R\u001c\u0010n\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010\u0017\"\u0004\bp\u0010\u0019R\u001c\u0010q\u001a\u0004\u0018\u00010rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\u001c\u0010w\u001a\u0004\u0018\u00010rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010t\"\u0004\by\u0010vR\u001c\u0010z\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b{\u0010\u0017\"\u0004\b|\u0010\u0019R\u001c\u0010}\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b~\u0010\u0017\"\u0004\b\u007f\u0010\u0019R\u001f\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0081\u0001\u0010\u0017\"\u0005\b\u0082\u0001\u0010\u0019R\u001f\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0084\u0001\u0010\u0017\"\u0005\b\u0085\u0001\u0010\u0019R\"\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0087\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001\"\u0006\b\u008a\u0001\u0010\u008b\u0001R\u001f\u0010\u008c\u0001\u001a\u0004\u0018\u00010TX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0001\u0010V\"\u0005\b\u008e\u0001\u0010XR\u001f\u0010\u008f\u0001\u001a\u0004\u0018\u00010ZX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0090\u0001\u0010\\\"\u0005\b\u0091\u0001\u0010^R\u001f\u0010\u0092\u0001\u001a\u0004\u0018\u00010ZX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0093\u0001\u0010\\\"\u0005\b\u0094\u0001\u0010^R\"\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0087\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0096\u0001\u0010\u0089\u0001\"\u0006\b\u0097\u0001\u0010\u008b\u0001R\u001f\u0010\u0098\u0001\u001a\u0004\u0018\u00010TX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0099\u0001\u0010V\"\u0005\b\u009a\u0001\u0010XR\u001f\u0010\u009b\u0001\u001a\u0004\u0018\u00010ZX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009c\u0001\u0010\\\"\u0005\b\u009d\u0001\u0010^R\u001f\u0010\u009e\u0001\u001a\u0004\u0018\u00010ZX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009f\u0001\u0010\\\"\u0005\b \u0001\u0010^R\u001f\u0010¡\u0001\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¢\u0001\u0010\u0017\"\u0005\b£\u0001\u0010\u0019R\u001f\u0010¤\u0001\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¥\u0001\u0010P\"\u0005\b¦\u0001\u0010RR\u0011\u0010§\u0001\u001a\u0004\u0018\u00010rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010¨\u0001\u001a\u0004\u0018\u00010rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010©\u0001\u001a\u0004\u0018\u00010rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010ª\u0001\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010«\u0001\u001a\u0005\u0018\u00010¬\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u00ad\u0001\u001a\u00030®\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¯\u0001\u0010°\u0001\"\u0006\b±\u0001\u0010²\u0001R\u001d\u0010³\u0001\u001a\u00020\rX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b³\u0001\u0010\u000f\"\u0005\b´\u0001\u0010\u0011R\u001d\u0010µ\u0001\u001a\u00020\rX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¶\u0001\u0010\u000f\"\u0005\b·\u0001\u0010\u0011R1\u0010¹\u0001\u001a\u00020\r2\u0007\u0010¸\u0001\u001a\u00020\r8F@FX\u0086\u008e\u0002¢\u0006\u0016\n\u0006\b¼\u0001\u0010½\u0001\u001a\u0005\bº\u0001\u0010\u000f\"\u0005\b»\u0001\u0010\u0011R\u001d\u0010¾\u0001\u001a\u0010\u0012\u0005\u0012\u00030À\u0001\u0012\u0004\u0012\u00020\u001e0¿\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010Ä\u0001\u001a\u00020\rX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÅ\u0001\u0010\u000f\"\u0005\bÆ\u0001\u0010\u0011¨\u0006ð\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PlayerView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "gestureHelper", "Lcom/lagradost/cloudstream3/ui/player/PlayerGestureHelper;", "getGestureHelper", "()Lcom/lagradost/cloudstream3/ui/player/PlayerGestureHelper;", ES6Iterator.VALUE_PROPERTY, "", "isFullScreen", "()Z", "setFullScreen", "(Z)V", "isLocked", "setLocked", "Landroid/view/View;", "videoOutline", "getVideoOutline", "()Landroid/view/View;", "setVideoOutline", "(Landroid/view/View;)V", "handleVolumeKey", "keyCode", "", "verifyVolume", "", "setupKeyEventListener", "releaseKeyEventListener", "requestUpdateBrightnessOverlayOnNextLayout", "releaseOverlayLayoutListener", "callbacks", "Lcom/lagradost/cloudstream3/ui/player/PlayerView$Callbacks;", "getCallbacks", "()Lcom/lagradost/cloudstream3/ui/player/PlayerView$Callbacks;", "setCallbacks", "(Lcom/lagradost/cloudstream3/ui/player/PlayerView$Callbacks;)V", "player", "Lcom/lagradost/cloudstream3/ui/player/IPlayer;", "getPlayer", "()Lcom/lagradost/cloudstream3/ui/player/IPlayer;", "setPlayer", "(Lcom/lagradost/cloudstream3/ui/player/IPlayer;)V", "resizeMode", "getResizeMode", "()I", "setResizeMode", "(I)V", "hasPipModeSupport", "getHasPipModeSupport", "setHasPipModeSupport", "currentPlayerStatus", "Lcom/lagradost/cloudstream3/ui/player/CSPlayerLoading;", "getCurrentPlayerStatus", "()Lcom/lagradost/cloudstream3/ui/player/CSPlayerLoading;", "setCurrentPlayerStatus", "(Lcom/lagradost/cloudstream3/ui/player/CSPlayerLoading;)V", "mMediaSession", "Landroidx/media3/session/MediaSession;", "getMMediaSession", "()Landroidx/media3/session/MediaSession;", "setMMediaSession", "(Landroidx/media3/session/MediaSession;)V", "pipReceiver", "Landroid/content/BroadcastReceiver;", "autoHideToken", "autoHideHandler", "Landroid/os/Handler;", "subView", "Landroidx/media3/ui/SubtitleView;", "getSubView", "()Landroidx/media3/ui/SubtitleView;", "setSubView", "(Landroidx/media3/ui/SubtitleView;)V", "playerPausePlayHolderHolder", "getPlayerPausePlayHolderHolder", "()Landroid/widget/FrameLayout;", "setPlayerPausePlayHolderHolder", "(Landroid/widget/FrameLayout;)V", "playerPausePlay", "Landroid/widget/ImageView;", "getPlayerPausePlay", "()Landroid/widget/ImageView;", "setPlayerPausePlay", "(Landroid/widget/ImageView;)V", "playerBuffering", "Landroid/widget/ProgressBar;", "getPlayerBuffering", "()Landroid/widget/ProgressBar;", "setPlayerBuffering", "(Landroid/widget/ProgressBar;)V", "exoPlayerView", "Landroidx/media3/ui/PlayerView;", "getExoPlayerView", "()Landroidx/media3/ui/PlayerView;", "setExoPlayerView", "(Landroidx/media3/ui/PlayerView;)V", "piphide", "getPiphide", "setPiphide", "subtitleHolder", "getSubtitleHolder", "setSubtitleHolder", "playerRew", "getPlayerRew$app", "setPlayerRew$app", "playerFfwd", "getPlayerFfwd$app", "setPlayerFfwd$app", "exoRewText", "Landroid/widget/TextView;", "getExoRewText$app", "()Landroid/widget/TextView;", "setExoRewText$app", "(Landroid/widget/TextView;)V", "exoFfwdText", "getExoFfwdText$app", "setExoFfwdText$app", "playerCenterMenu", "getPlayerCenterMenu$app", "setPlayerCenterMenu$app", "playerRewHolder", "getPlayerRewHolder$app", "setPlayerRewHolder$app", "playerFfwdHolder", "getPlayerFfwdHolder$app", "setPlayerFfwdHolder$app", "playerVideoHolder", "getPlayerVideoHolder$app", "setPlayerVideoHolder$app", "playerProgressbarLeftHolder", "Landroid/widget/RelativeLayout;", "getPlayerProgressbarLeftHolder", "()Landroid/widget/RelativeLayout;", "setPlayerProgressbarLeftHolder", "(Landroid/widget/RelativeLayout;)V", "playerProgressbarLeftIcon", "getPlayerProgressbarLeftIcon", "setPlayerProgressbarLeftIcon", "playerProgressbarLeftLevel1", "getPlayerProgressbarLeftLevel1", "setPlayerProgressbarLeftLevel1", "playerProgressbarLeftLevel2", "getPlayerProgressbarLeftLevel2", "setPlayerProgressbarLeftLevel2", "playerProgressbarRightHolder", "getPlayerProgressbarRightHolder", "setPlayerProgressbarRightHolder", "playerProgressbarRightIcon", "getPlayerProgressbarRightIcon", "setPlayerProgressbarRightIcon", "playerProgressbarRightLevel1", "getPlayerProgressbarRightLevel1", "setPlayerProgressbarRightLevel1", "playerProgressbarRightLevel2", "getPlayerProgressbarRightLevel2", "setPlayerProgressbarRightLevel2", "playerSpeedupButton", "getPlayerSpeedupButton$app", "setPlayerSpeedupButton$app", "playerHolder", "getPlayerHolder", "setPlayerHolder", "exoDuration", "timeLeft", "exoPosition", "timeLive", "exoProgress", "Lcom/lagradost/cloudstream3/ui/player/live/LivePreviewTimeBar;", "seekTime", "", "getSeekTime", "()J", "setSeekTime", "(J)V", "isVerticalOrientation", "setVerticalOrientation", "autoPlayerRotateEnabled", "getAutoPlayerRotateEnabled", "setAutoPlayerRotateEnabled", "<set-?>", "durationMode", "getDurationMode", "setDurationMode", "durationMode$delegate", "Lcom/lagradost/cloudstream3/utils/UserPreferenceDelegate;", "subStyleListener", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "bindViews", "root", "initialize", "fullscreenNotch", "getFullscreenNotch", "setFullscreenNotch", "enterFullscreen", "updateOrientation", "Lkotlin/Function0;", "exitFullscreen", "onStop", "onResume", "ctx", "release", "onPictureInPictureModeChanged", "isInPictureInPictureMode", "activity", "Landroid/app/Activity;", "keepScreenOn", DebugKt.DEBUG_PROPERTY_VALUE_ON, "updateIsPlaying", "wasPlaying", "isPlaying", "requestAudioFocus", "playerUpdated", "", "onSubStyleChanged", "style", "playerError", "exception", "", "nextResize", "resize", "showToast", "Lcom/lagradost/cloudstream3/ui/player/PlayerResize;", "dynamicOrientation", "mainCallback", NotificationCompat.CATEGORY_EVENT, "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", "setRemainingTimeCounter", "showRemaining", "updateRemainingTime", "scheduleAutoHide", "delayMs", "cancelAutoHide", "Companion", "Callbacks", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PlayerView extends FrameLayout {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl(PlayerView.class, "durationMode", "getDurationMode()Z", 0)};
    private static final String TAG = "PlayerView";
    private final Handler autoHideHandler;
    private int autoHideToken;
    private boolean autoPlayerRotateEnabled;
    private Callbacks callbacks;
    private CSPlayerLoading currentPlayerStatus;

    /* JADX INFO: renamed from: durationMode$delegate, reason: from kotlin metadata */
    private final UserPreferenceDelegate durationMode;
    private TextView exoDuration;
    private TextView exoFfwdText;
    private androidx.media3.ui.PlayerView exoPlayerView;
    private TextView exoPosition;
    private LivePreviewTimeBar exoProgress;
    private TextView exoRewText;
    private boolean fullscreenNotch;
    private final PlayerGestureHelper gestureHelper;
    private boolean hasPipModeSupport;
    private boolean isVerticalOrientation;
    private MediaSession mMediaSession;
    private BroadcastReceiver pipReceiver;
    private FrameLayout piphide;
    private IPlayer player;
    private ProgressBar playerBuffering;
    private View playerCenterMenu;
    private View playerFfwd;
    private View playerFfwdHolder;
    private FrameLayout playerHolder;
    private ImageView playerPausePlay;
    private FrameLayout playerPausePlayHolderHolder;
    private RelativeLayout playerProgressbarLeftHolder;
    private ImageView playerProgressbarLeftIcon;
    private ProgressBar playerProgressbarLeftLevel1;
    private ProgressBar playerProgressbarLeftLevel2;
    private RelativeLayout playerProgressbarRightHolder;
    private ImageView playerProgressbarRightIcon;
    private ProgressBar playerProgressbarRightLevel1;
    private ProgressBar playerProgressbarRightLevel2;
    private View playerRew;
    private View playerRewHolder;
    private View playerSpeedupButton;
    private View playerVideoHolder;
    private int resizeMode;
    private long seekTime;
    private final Function1<SaveCaptionStyle, Unit> subStyleListener;
    private SubtitleView subView;
    private FrameLayout subtitleHolder;
    private TextView timeLeft;
    private View timeLive;

    /* JADX INFO: compiled from: PlayerView.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayerResize.values().length];
            try {
                iArr[PlayerResize.Fill.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PlayerResize.Fit.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PlayerResize.Zoom.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PlayerView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.gestureHelper = new PlayerGestureHelper(this);
        this.player = new CS3IPlayer();
        this.hasPipModeSupport = true;
        this.currentPlayerStatus = CSPlayerLoading.IsBuffering;
        this.autoHideHandler = new Handler(Looper.getMainLooper());
        this.seekTime = 10000L;
        this.durationMode = new UserPreferenceDelegate("duration_mode", false);
        this.subStyleListener = new PlayerView$subStyleListener$1(this);
        this.fullscreenNotch = true;
    }

    public /* synthetic */ PlayerView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public final PlayerGestureHelper getGestureHelper() {
        return this.gestureHelper;
    }

    public final boolean isFullScreen() {
        return this.gestureHelper.getIsFullScreen();
    }

    public final void setFullScreen(boolean z) {
        this.gestureHelper.setFullScreen(z);
    }

    public final boolean isLocked() {
        return this.gestureHelper.getIsLocked();
    }

    public final void setLocked(boolean z) {
        this.gestureHelper.setLocked(z);
    }

    public final View getVideoOutline() {
        return this.gestureHelper.getVideoOutline();
    }

    public final void setVideoOutline(View view) {
        this.gestureHelper.setVideoOutline(view);
    }

    public final boolean handleVolumeKey(int keyCode) {
        return this.gestureHelper.handleVolumeKey(keyCode);
    }

    public final void verifyVolume() {
        this.gestureHelper.verifyVolume();
    }

    public final void setupKeyEventListener() {
        this.gestureHelper.setupKeyEventListener();
    }

    public final void releaseKeyEventListener() {
        this.gestureHelper.releaseKeyEventListener();
    }

    public final void requestUpdateBrightnessOverlayOnNextLayout() {
        this.gestureHelper.requestUpdateBrightnessOverlayOnNextLayout();
    }

    public final void releaseOverlayLayoutListener() {
        this.gestureHelper.releaseOverlayLayoutListener();
    }

    /* JADX INFO: compiled from: PlayerView.kt */
    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\u0018\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\u0016\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016J\u0012\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0018\u001a\u00020\u0003H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0003H\u0016J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!H\u0016J\u0012\u0010\"\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u0016J\b\u0010$\u001a\u00020\u0003H\u0016J\u0010\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u001aH\u0016J\u0010\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u001aH\u0016J\b\u0010+\u001a\u00020\u0003H\u0016J\u0012\u0010,\u001a\u00020\u00032\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020\u0003H\u0016J\u0018\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u00020\u001a2\u0006\u00102\u001a\u00020\u001aH\u0016J\b\u00103\u001a\u00020\u0003H\u0016¨\u00064À\u0006\u0003"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PlayerView$Callbacks;", "", "nextEpisode", "", "prevEpisode", "playerPositionChanged", "position", "", TypedValues.TransitionType.S_DURATION, "playerStatusChanged", "playerDimensionsLoaded", "width", "", "height", "subtitlesChanged", "embeddedSubtitlesFetched", "subtitles", "", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "onTracksInfoChanged", "onTimestamp", "timestamp", "Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;", "onTimestampSkipped", "exitedPipMode", "hasNextMirror", "", "nextMirror", "onDownload", NotificationCompat.CATEGORY_EVENT, "Lcom/lagradost/cloudstream3/ui/player/DownloadEvent;", "playerError", "exception", "", "playerUpdated", "player", "onSingleTap", "onHoldSpeedUp", "show", "onBrightnessExtra", "alpha", "", "isUIShowing", "onTouchDown", "onSeekPreviewText", "text", "", "onHidePlayerUI", "onGestureEnd", "hadSwipe", "wasUiShowing", "onAutoHideUI", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface Callbacks {
        void embeddedSubtitlesFetched(List<SubtitleData> subtitles);

        void exitedPipMode();

        boolean hasNextMirror();

        boolean isUIShowing();

        void nextEpisode();

        void nextMirror();

        void onAutoHideUI();

        void onBrightnessExtra(float alpha);

        void onDownload(DownloadEvent event);

        void onGestureEnd(boolean hadSwipe, boolean wasUiShowing);

        void onHidePlayerUI();

        void onHoldSpeedUp(boolean show);

        void onSeekPreviewText(String text);

        void onSingleTap();

        void onTimestamp(VideoSkipStamp timestamp);

        void onTimestampSkipped(VideoSkipStamp timestamp);

        void onTouchDown();

        void onTracksInfoChanged();

        void playerDimensionsLoaded(int width, int height);

        void playerError(Throwable exception);

        void playerPositionChanged(long position, long duration);

        void playerStatusChanged();

        void playerUpdated(Object player);

        void prevEpisode();

        void subtitlesChanged();

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.PlayerView$Callbacks$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: PlayerView.kt */
        public final /* synthetic */ class CC {
            public static void $default$exitedPipMode(Callbacks _this) {
            }

            public static boolean $default$hasNextMirror(Callbacks _this) {
                return false;
            }

            public static boolean $default$isUIShowing(Callbacks _this) {
                return false;
            }

            public static void $default$nextEpisode(Callbacks _this) {
            }

            public static void $default$nextMirror(Callbacks _this) {
            }

            public static void $default$onAutoHideUI(Callbacks _this) {
            }

            public static void $default$onBrightnessExtra(Callbacks _this, float f) {
            }

            public static void $default$onGestureEnd(Callbacks _this, boolean z, boolean z2) {
            }

            public static void $default$onHidePlayerUI(Callbacks _this) {
            }

            public static void $default$onHoldSpeedUp(Callbacks _this, boolean z) {
            }

            public static void $default$onSeekPreviewText(Callbacks _this, String str) {
            }

            public static void $default$onSingleTap(Callbacks _this) {
            }

            public static void $default$onTimestamp(Callbacks _this, VideoSkipStamp videoSkipStamp) {
            }

            public static void $default$onTouchDown(Callbacks _this) {
            }

            public static void $default$onTracksInfoChanged(Callbacks _this) {
            }

            public static void $default$playerDimensionsLoaded(Callbacks _this, int i, int i2) {
            }

            public static void $default$playerPositionChanged(Callbacks _this, long j, long j2) {
            }

            public static void $default$playerStatusChanged(Callbacks _this) {
            }

            public static void $default$playerUpdated(Callbacks _this, Object obj) {
            }

            public static void $default$prevEpisode(Callbacks _this) {
            }

            public static void $default$subtitlesChanged(Callbacks _this) {
            }
        }

        /* JADX INFO: compiled from: PlayerView.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static void nextEpisode(Callbacks callbacks) {
                CC.$default$nextEpisode(callbacks);
            }

            @Deprecated
            public static void prevEpisode(Callbacks callbacks) {
                CC.$default$prevEpisode(callbacks);
            }

            @Deprecated
            public static void playerPositionChanged(Callbacks callbacks, long j, long j2) {
                CC.$default$playerPositionChanged(callbacks, j, j2);
            }

            @Deprecated
            public static void playerStatusChanged(Callbacks callbacks) {
                CC.$default$playerStatusChanged(callbacks);
            }

            @Deprecated
            public static void playerDimensionsLoaded(Callbacks callbacks, int i, int i2) {
                CC.$default$playerDimensionsLoaded(callbacks, i, i2);
            }

            @Deprecated
            public static void subtitlesChanged(Callbacks callbacks) {
                CC.$default$subtitlesChanged(callbacks);
            }

            @Deprecated
            public static void embeddedSubtitlesFetched(Callbacks callbacks, List<SubtitleData> subtitles) {
                Intrinsics.checkNotNullParameter(subtitles, "subtitles");
                Intrinsics.checkNotNullParameter(subtitles, "subtitles");
            }

            @Deprecated
            public static void onTracksInfoChanged(Callbacks callbacks) {
                CC.$default$onTracksInfoChanged(callbacks);
            }

            @Deprecated
            public static void onTimestamp(Callbacks callbacks, VideoSkipStamp videoSkipStamp) {
                CC.$default$onTimestamp(callbacks, videoSkipStamp);
            }

            @Deprecated
            public static void onTimestampSkipped(Callbacks callbacks, VideoSkipStamp timestamp) {
                Intrinsics.checkNotNullParameter(timestamp, "timestamp");
                Intrinsics.checkNotNullParameter(timestamp, "timestamp");
            }

            @Deprecated
            public static void exitedPipMode(Callbacks callbacks) {
                CC.$default$exitedPipMode(callbacks);
            }

            @Deprecated
            public static boolean hasNextMirror(Callbacks callbacks) {
                return CC.$default$hasNextMirror(callbacks);
            }

            @Deprecated
            public static void nextMirror(Callbacks callbacks) {
                CC.$default$nextMirror(callbacks);
            }

            @Deprecated
            public static void onDownload(Callbacks callbacks, DownloadEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                Intrinsics.checkNotNullParameter(event, "event");
            }

            @Deprecated
            public static void playerError(Callbacks callbacks, Throwable exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                Intrinsics.checkNotNullParameter(exception, "exception");
            }

            @Deprecated
            public static void playerUpdated(Callbacks callbacks, Object obj) {
                CC.$default$playerUpdated(callbacks, obj);
            }

            @Deprecated
            public static void onSingleTap(Callbacks callbacks) {
                CC.$default$onSingleTap(callbacks);
            }

            @Deprecated
            public static void onHoldSpeedUp(Callbacks callbacks, boolean z) {
                CC.$default$onHoldSpeedUp(callbacks, z);
            }

            @Deprecated
            public static void onBrightnessExtra(Callbacks callbacks, float f) {
                CC.$default$onBrightnessExtra(callbacks, f);
            }

            @Deprecated
            public static boolean isUIShowing(Callbacks callbacks) {
                return CC.$default$isUIShowing(callbacks);
            }

            @Deprecated
            public static void onTouchDown(Callbacks callbacks) {
                CC.$default$onTouchDown(callbacks);
            }

            @Deprecated
            public static void onSeekPreviewText(Callbacks callbacks, String str) {
                CC.$default$onSeekPreviewText(callbacks, str);
            }

            @Deprecated
            public static void onHidePlayerUI(Callbacks callbacks) {
                CC.$default$onHidePlayerUI(callbacks);
            }

            @Deprecated
            public static void onGestureEnd(Callbacks callbacks, boolean z, boolean z2) {
                CC.$default$onGestureEnd(callbacks, z, z2);
            }

            @Deprecated
            public static void onAutoHideUI(Callbacks callbacks) {
                CC.$default$onAutoHideUI(callbacks);
            }
        }
    }

    public final Callbacks getCallbacks() {
        return this.callbacks;
    }

    public final void setCallbacks(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    public final IPlayer getPlayer() {
        return this.player;
    }

    public final void setPlayer(IPlayer iPlayer) {
        Intrinsics.checkNotNullParameter(iPlayer, "<set-?>");
        this.player = iPlayer;
    }

    public final int getResizeMode() {
        return this.resizeMode;
    }

    public final void setResizeMode(int i) {
        this.resizeMode = i;
    }

    public final boolean getHasPipModeSupport() {
        return this.hasPipModeSupport;
    }

    public final void setHasPipModeSupport(boolean z) {
        this.hasPipModeSupport = z;
    }

    public final CSPlayerLoading getCurrentPlayerStatus() {
        return this.currentPlayerStatus;
    }

    public final void setCurrentPlayerStatus(CSPlayerLoading cSPlayerLoading) {
        Intrinsics.checkNotNullParameter(cSPlayerLoading, "<set-?>");
        this.currentPlayerStatus = cSPlayerLoading;
    }

    public final MediaSession getMMediaSession() {
        return this.mMediaSession;
    }

    public final void setMMediaSession(MediaSession mediaSession) {
        this.mMediaSession = mediaSession;
    }

    public final SubtitleView getSubView() {
        return this.subView;
    }

    public final void setSubView(SubtitleView subtitleView) {
        this.subView = subtitleView;
    }

    public final FrameLayout getPlayerPausePlayHolderHolder() {
        return this.playerPausePlayHolderHolder;
    }

    public final void setPlayerPausePlayHolderHolder(FrameLayout frameLayout) {
        this.playerPausePlayHolderHolder = frameLayout;
    }

    public final ImageView getPlayerPausePlay() {
        return this.playerPausePlay;
    }

    public final void setPlayerPausePlay(ImageView imageView) {
        this.playerPausePlay = imageView;
    }

    public final ProgressBar getPlayerBuffering() {
        return this.playerBuffering;
    }

    public final void setPlayerBuffering(ProgressBar progressBar) {
        this.playerBuffering = progressBar;
    }

    public final androidx.media3.ui.PlayerView getExoPlayerView() {
        return this.exoPlayerView;
    }

    public final void setExoPlayerView(androidx.media3.ui.PlayerView playerView) {
        this.exoPlayerView = playerView;
    }

    public final FrameLayout getPiphide() {
        return this.piphide;
    }

    public final void setPiphide(FrameLayout frameLayout) {
        this.piphide = frameLayout;
    }

    public final FrameLayout getSubtitleHolder() {
        return this.subtitleHolder;
    }

    public final void setSubtitleHolder(FrameLayout frameLayout) {
        this.subtitleHolder = frameLayout;
    }

    /* JADX INFO: renamed from: getPlayerRew$app, reason: from getter */
    public final View getPlayerRew() {
        return this.playerRew;
    }

    public final void setPlayerRew$app(View view) {
        this.playerRew = view;
    }

    /* JADX INFO: renamed from: getPlayerFfwd$app, reason: from getter */
    public final View getPlayerFfwd() {
        return this.playerFfwd;
    }

    public final void setPlayerFfwd$app(View view) {
        this.playerFfwd = view;
    }

    /* JADX INFO: renamed from: getExoRewText$app, reason: from getter */
    public final TextView getExoRewText() {
        return this.exoRewText;
    }

    public final void setExoRewText$app(TextView textView) {
        this.exoRewText = textView;
    }

    /* JADX INFO: renamed from: getExoFfwdText$app, reason: from getter */
    public final TextView getExoFfwdText() {
        return this.exoFfwdText;
    }

    public final void setExoFfwdText$app(TextView textView) {
        this.exoFfwdText = textView;
    }

    /* JADX INFO: renamed from: getPlayerCenterMenu$app, reason: from getter */
    public final View getPlayerCenterMenu() {
        return this.playerCenterMenu;
    }

    public final void setPlayerCenterMenu$app(View view) {
        this.playerCenterMenu = view;
    }

    /* JADX INFO: renamed from: getPlayerRewHolder$app, reason: from getter */
    public final View getPlayerRewHolder() {
        return this.playerRewHolder;
    }

    public final void setPlayerRewHolder$app(View view) {
        this.playerRewHolder = view;
    }

    /* JADX INFO: renamed from: getPlayerFfwdHolder$app, reason: from getter */
    public final View getPlayerFfwdHolder() {
        return this.playerFfwdHolder;
    }

    public final void setPlayerFfwdHolder$app(View view) {
        this.playerFfwdHolder = view;
    }

    /* JADX INFO: renamed from: getPlayerVideoHolder$app, reason: from getter */
    public final View getPlayerVideoHolder() {
        return this.playerVideoHolder;
    }

    public final void setPlayerVideoHolder$app(View view) {
        this.playerVideoHolder = view;
    }

    public final RelativeLayout getPlayerProgressbarLeftHolder() {
        return this.playerProgressbarLeftHolder;
    }

    public final void setPlayerProgressbarLeftHolder(RelativeLayout relativeLayout) {
        this.playerProgressbarLeftHolder = relativeLayout;
    }

    public final ImageView getPlayerProgressbarLeftIcon() {
        return this.playerProgressbarLeftIcon;
    }

    public final void setPlayerProgressbarLeftIcon(ImageView imageView) {
        this.playerProgressbarLeftIcon = imageView;
    }

    public final ProgressBar getPlayerProgressbarLeftLevel1() {
        return this.playerProgressbarLeftLevel1;
    }

    public final void setPlayerProgressbarLeftLevel1(ProgressBar progressBar) {
        this.playerProgressbarLeftLevel1 = progressBar;
    }

    public final ProgressBar getPlayerProgressbarLeftLevel2() {
        return this.playerProgressbarLeftLevel2;
    }

    public final void setPlayerProgressbarLeftLevel2(ProgressBar progressBar) {
        this.playerProgressbarLeftLevel2 = progressBar;
    }

    public final RelativeLayout getPlayerProgressbarRightHolder() {
        return this.playerProgressbarRightHolder;
    }

    public final void setPlayerProgressbarRightHolder(RelativeLayout relativeLayout) {
        this.playerProgressbarRightHolder = relativeLayout;
    }

    public final ImageView getPlayerProgressbarRightIcon() {
        return this.playerProgressbarRightIcon;
    }

    public final void setPlayerProgressbarRightIcon(ImageView imageView) {
        this.playerProgressbarRightIcon = imageView;
    }

    public final ProgressBar getPlayerProgressbarRightLevel1() {
        return this.playerProgressbarRightLevel1;
    }

    public final void setPlayerProgressbarRightLevel1(ProgressBar progressBar) {
        this.playerProgressbarRightLevel1 = progressBar;
    }

    public final ProgressBar getPlayerProgressbarRightLevel2() {
        return this.playerProgressbarRightLevel2;
    }

    public final void setPlayerProgressbarRightLevel2(ProgressBar progressBar) {
        this.playerProgressbarRightLevel2 = progressBar;
    }

    /* JADX INFO: renamed from: getPlayerSpeedupButton$app, reason: from getter */
    public final View getPlayerSpeedupButton() {
        return this.playerSpeedupButton;
    }

    public final void setPlayerSpeedupButton$app(View view) {
        this.playerSpeedupButton = view;
    }

    public final FrameLayout getPlayerHolder() {
        return this.playerHolder;
    }

    public final void setPlayerHolder(FrameLayout frameLayout) {
        this.playerHolder = frameLayout;
    }

    public final long getSeekTime() {
        return this.seekTime;
    }

    public final void setSeekTime(long j) {
        this.seekTime = j;
    }

    /* JADX INFO: renamed from: isVerticalOrientation, reason: from getter */
    public final boolean getIsVerticalOrientation() {
        return this.isVerticalOrientation;
    }

    public final void setVerticalOrientation(boolean z) {
        this.isVerticalOrientation = z;
    }

    public final boolean getAutoPlayerRotateEnabled() {
        return this.autoPlayerRotateEnabled;
    }

    public final void setAutoPlayerRotateEnabled(boolean z) {
        this.autoPlayerRotateEnabled = z;
    }

    public final boolean getDurationMode() {
        return ((Boolean) this.durationMode.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public final void setDurationMode(boolean z) {
        this.durationMode.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    public final void bindViews(View root) {
        Intrinsics.checkNotNullParameter(root, "root");
        this.exoDuration = (TextView) root.findViewById(R.id.exo_duration);
        this.exoFfwdText = (TextView) root.findViewById(com.lagradost.cloudstream3.R.id.exo_ffwd_text);
        this.exoPlayerView = (androidx.media3.ui.PlayerView) root.findViewById(com.lagradost.cloudstream3.R.id.player_view);
        this.exoPosition = (TextView) root.findViewById(com.lagradost.cloudstream3.R.id.exo_position);
        this.exoRewText = (TextView) root.findViewById(com.lagradost.cloudstream3.R.id.exo_rew_text);
        this.piphide = (FrameLayout) root.findViewById(com.lagradost.cloudstream3.R.id.piphide);
        this.playerBuffering = (ProgressBar) root.findViewById(com.lagradost.cloudstream3.R.id.player_buffering);
        this.playerCenterMenu = root.findViewById(com.lagradost.cloudstream3.R.id.player_center_menu);
        this.playerFfwd = root.findViewById(com.lagradost.cloudstream3.R.id.player_ffwd);
        this.playerFfwdHolder = root.findViewById(com.lagradost.cloudstream3.R.id.player_ffwd_holder);
        this.playerHolder = (FrameLayout) root.findViewById(com.lagradost.cloudstream3.R.id.player_holder);
        this.playerPausePlay = (ImageView) root.findViewById(com.lagradost.cloudstream3.R.id.player_pause_play);
        this.playerPausePlayHolderHolder = (FrameLayout) root.findViewById(com.lagradost.cloudstream3.R.id.player_pause_play_holder_holder);
        this.playerProgressbarLeftHolder = (RelativeLayout) root.findViewById(com.lagradost.cloudstream3.R.id.player_progressbar_left_holder);
        this.playerProgressbarLeftIcon = (ImageView) root.findViewById(com.lagradost.cloudstream3.R.id.player_progressbar_left_icon);
        this.playerProgressbarLeftLevel1 = (ProgressBar) root.findViewById(com.lagradost.cloudstream3.R.id.player_progressbar_left_level1);
        this.playerProgressbarLeftLevel2 = (ProgressBar) root.findViewById(com.lagradost.cloudstream3.R.id.player_progressbar_left_level2);
        this.playerProgressbarRightHolder = (RelativeLayout) root.findViewById(com.lagradost.cloudstream3.R.id.player_progressbar_right_holder);
        this.playerProgressbarRightIcon = (ImageView) root.findViewById(com.lagradost.cloudstream3.R.id.player_progressbar_right_icon);
        this.playerProgressbarRightLevel1 = (ProgressBar) root.findViewById(com.lagradost.cloudstream3.R.id.player_progressbar_right_level1);
        this.playerProgressbarRightLevel2 = (ProgressBar) root.findViewById(com.lagradost.cloudstream3.R.id.player_progressbar_right_level2);
        this.playerRew = root.findViewById(com.lagradost.cloudstream3.R.id.player_rew);
        this.playerRewHolder = root.findViewById(com.lagradost.cloudstream3.R.id.player_rew_holder);
        this.playerSpeedupButton = root.findViewById(com.lagradost.cloudstream3.R.id.player_speedup_button);
        this.playerVideoHolder = root.findViewById(com.lagradost.cloudstream3.R.id.player_video_holder);
        this.subtitleHolder = (FrameLayout) root.findViewById(com.lagradost.cloudstream3.R.id.subtitle_holder);
        this.timeLeft = (TextView) root.findViewById(com.lagradost.cloudstream3.R.id.time_left);
        this.timeLive = root.findViewById(com.lagradost.cloudstream3.R.id.time_live);
    }

    public final void initialize() {
        DefaultTimeBar defaultTimeBar;
        int resizeMode = DataStoreHelper.INSTANCE.getResizeMode();
        this.resizeMode = resizeMode;
        resize(resizeMode, false);
        this.player.releaseCallbacks();
        this.player.initCallbacks(new AnonymousClass1(this), CollectionsKt.listOf((Object[]) new Integer[]{50, 80, 90, 80}));
        if (this.player instanceof CS3IPlayer) {
            androidx.media3.ui.PlayerView playerView = this.exoPlayerView;
            PreviewTimeBar previewTimeBar = playerView != null ? (PreviewTimeBar) playerView.findViewById(com.lagradost.cloudstream3.R.id.exo_progress) : null;
            this.exoProgress = previewTimeBar instanceof LivePreviewTimeBar ? (LivePreviewTimeBar) previewTimeBar : null;
            androidx.media3.ui.PlayerView playerView2 = this.exoPlayerView;
            final ImageView imageView = playerView2 != null ? (ImageView) playerView2.findViewById(com.lagradost.cloudstream3.R.id.previewImageView) : null;
            androidx.media3.ui.PlayerView playerView3 = this.exoPlayerView;
            FrameLayout frameLayout = playerView3 != null ? (FrameLayout) playerView3.findViewById(com.lagradost.cloudstream3.R.id.previewFrameLayout) : null;
            if (Globals.INSTANCE.isLayout(2) && frameLayout != null) {
                frameLayout.setVisibility(8);
            }
            if (previewTimeBar != null && imageView != null && frameLayout != null && !Globals.INSTANCE.isLayout(2)) {
                previewTimeBar.addOnScrubListener(new AnonymousClass2(previewTimeBar, new Ref.BooleanRef()));
                previewTimeBar.attachPreviewView(frameLayout);
                previewTimeBar.setPreviewLoader(new PreviewLoader() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView$$ExternalSyntheticLambda9
                    @Override // com.github.rubensousa.previewseekbar.PreviewLoader
                    public final void loadPreview(long j, long j2) {
                        PlayerView.initialize$lambda$0(this.f$0, imageView, j, j2);
                    }
                });
            }
            androidx.media3.ui.PlayerView playerView4 = this.exoPlayerView;
            SubtitleView subtitleView = playerView4 != null ? (SubtitleView) playerView4.findViewById(R.id.exo_subtitles) : null;
            this.subView = subtitleView;
            IPlayer iPlayer = this.player;
            CS3IPlayer cS3IPlayer = iPlayer instanceof CS3IPlayer ? (CS3IPlayer) iPlayer : null;
            if (cS3IPlayer != null) {
                cS3IPlayer.initSubtitles(subtitleView, this.subtitleHolder, CustomDecoder.INSTANCE.getStyle());
            }
            IPlayer iPlayer2 = this.player;
            CS3IPlayer cS3IPlayer2 = iPlayer2 instanceof CS3IPlayer ? (CS3IPlayer) iPlayer2 : null;
            if (cS3IPlayer2 != null) {
                IPreviewGenerator imageGenerator = cS3IPlayer2.getImageGenerator();
                PreviewGenerator previewGenerator = imageGenerator instanceof PreviewGenerator ? (PreviewGenerator) imageGenerator : null;
                if (previewGenerator != null) {
                    previewGenerator.setParams(ImageParams.INSTANCE.new16by9(CommonActivity.INSTANCE.getScreenWidth()));
                }
            }
            androidx.media3.ui.PlayerView playerView5 = this.exoPlayerView;
            if (playerView5 != null && (defaultTimeBar = (DefaultTimeBar) playerView5.findViewById(com.lagradost.cloudstream3.R.id.exo_progress)) != null) {
                defaultTimeBar.addListener(new TimeBar.OnScrubListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView.initialize.5
                    @Override // androidx.media3.ui.TimeBar.OnScrubListener
                    public void onScrubMove(TimeBar timeBar, long position) {
                        Intrinsics.checkNotNullParameter(timeBar, "timeBar");
                    }

                    @Override // androidx.media3.ui.TimeBar.OnScrubListener
                    public void onScrubStart(TimeBar timeBar, long position) {
                        Intrinsics.checkNotNullParameter(timeBar, "timeBar");
                    }

                    @Override // androidx.media3.ui.TimeBar.OnScrubListener
                    public void onScrubStop(TimeBar timeBar, long position, boolean canceled) {
                        Long duration;
                        Intrinsics.checkNotNullParameter(timeBar, "timeBar");
                        if (canceled || (duration = PlayerView.this.getPlayer().getDuration()) == null) {
                            return;
                        }
                        long jLongValue = duration.longValue();
                        Long position2 = PlayerView.this.getPlayer().getPosition();
                        if (position2 != null) {
                            PlayerView.this.mainCallback(new PositionEvent(PlayerEventSource.UI, position2.longValue(), position, jLongValue));
                        }
                    }
                });
            }
            try {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                this.seekTime = ((long) defaultSharedPreferences.getInt(getContext().getString(com.lagradost.cloudstream3.R.string.double_tap_seek_time_key), 10)) * 1000;
                this.autoPlayerRotateEnabled = defaultSharedPreferences.getBoolean(getContext().getString(com.lagradost.cloudstream3.R.string.auto_rotate_video_key), true);
            } catch (Exception unused) {
            }
            int i = (int) (this.seekTime / 1000);
            TextView textView = this.exoRewText;
            if (textView != null) {
                String string = getContext().getString(com.lagradost.cloudstream3.R.string.rew_text_regular_format);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                textView.setText(str);
            }
            TextView textView2 = this.exoFfwdText;
            if (textView2 != null) {
                String string2 = getContext().getString(com.lagradost.cloudstream3.R.string.ffw_text_regular_format);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String str2 = String.format(string2, Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                textView2.setText(str2);
            }
            ImageView imageView2 = this.playerPausePlay;
            if (imageView2 != null) {
                imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView$$ExternalSyntheticLambda10
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PlayerView.initialize$lambda$2(this.f$0, view);
                    }
                });
            }
            View view = this.playerRew;
            if (view != null) {
                view.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView$$ExternalSyntheticLambda11
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        PlayerView.initialize$lambda$3(this.f$0, view2);
                    }
                });
            }
            View view2 = this.playerFfwd;
            if (view2 != null) {
                view2.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView$$ExternalSyntheticLambda12
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view3) {
                        PlayerView.initialize$lambda$4(this.f$0, view3);
                    }
                });
            }
            SubtitlesFragment.INSTANCE.getApplyStyleEvent().plusAssign(this.subStyleListener);
            try {
                SharedPreferences defaultSharedPreferences2 = PreferenceManager.getDefaultSharedPreferences(getContext());
                IPlayer iPlayer3 = this.player;
                CS3IPlayer cS3IPlayer3 = iPlayer3 instanceof CS3IPlayer ? (CS3IPlayer) iPlayer3 : null;
                if (cS3IPlayer3 == null) {
                    return;
                }
                cS3IPlayer3.setCacheSize(((long) defaultSharedPreferences2.getInt(getContext().getString(com.lagradost.cloudstream3.R.string.video_buffer_size_key), 0)) * 1048576);
                cS3IPlayer3.setSimpleCacheSize(((long) defaultSharedPreferences2.getInt(getContext().getString(com.lagradost.cloudstream3.R.string.video_buffer_disk_key), 0)) * 1048576);
                cS3IPlayer3.setVideoBufferMs(((long) defaultSharedPreferences2.getInt(getContext().getString(com.lagradost.cloudstream3.R.string.video_buffer_length_key), 0)) * 1000);
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
            }
            TextView textView3 = this.exoDuration;
            if (textView3 != null) {
                textView3.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView$$ExternalSyntheticLambda13
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view3) {
                        this.f$0.setRemainingTimeCounter(true);
                    }
                });
            }
            TextView textView4 = this.timeLeft;
            if (textView4 != null) {
                textView4.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView$$ExternalSyntheticLambda5
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view3) {
                        this.f$0.setRemainingTimeCounter(false);
                    }
                });
            }
            TextView textView5 = this.exoPosition;
            if (textView5 != null) {
                textView5.addTextChangedListener(new TextWatcher() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView$initialize$$inlined$doOnTextChanged$1
                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable s) {
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence text, int start, int before, int count) {
                        this.this$0.updateRemainingTime();
                    }
                });
            }
            this.gestureHelper.initialize();
            setupKeyEventListener();
            setRemainingTimeCounter(getDurationMode() || Globals.INSTANCE.isLayout(2));
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.PlayerView$initialize$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerView.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<PlayerEvent, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, PlayerView.class, "mainCallback", "mainCallback(Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PlayerEvent playerEvent) {
            invoke2(playerEvent);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(PlayerEvent p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((PlayerView) this.receiver).mainCallback(p0);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.PlayerView$initialize$2, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerView.kt */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\"\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\f"}, d2 = {"com/lagradost/cloudstream3/ui/player/PlayerView$initialize$2", "Lcom/github/rubensousa/previewseekbar/PreviewBar$OnScrubListener;", "onScrubStart", "", "previewBar", "Lcom/github/rubensousa/previewseekbar/PreviewBar;", "onScrubMove", "progress", "", "fromUser", "", "onScrubStop", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AnonymousClass2 implements PreviewBar.OnScrubListener {
        final /* synthetic */ PreviewTimeBar $progressBar;
        final /* synthetic */ Ref.BooleanRef $resume;

        @Override // com.github.rubensousa.previewseekbar.PreviewBar.OnScrubListener
        public void onScrubMove(PreviewBar previewBar, int progress, boolean fromUser) {
        }

        AnonymousClass2(PreviewTimeBar previewTimeBar, Ref.BooleanRef booleanRef) {
            this.$progressBar = previewTimeBar;
            this.$resume = booleanRef;
        }

        @Override // com.github.rubensousa.previewseekbar.PreviewBar.OnScrubListener
        public void onScrubStart(PreviewBar previewBar) {
            SubtitleView subView;
            IPlayer player = PlayerView.this.getPlayer();
            CS3IPlayer cS3IPlayer = player instanceof CS3IPlayer ? (CS3IPlayer) player : null;
            if (cS3IPlayer == null) {
                return;
            }
            boolean zHasPreview = cS3IPlayer.hasPreview();
            this.$progressBar.setPreviewEnabled(zHasPreview);
            this.$resume.element = cS3IPlayer.getIsPlaying();
            if (this.$resume.element) {
                cS3IPlayer.handleEvent(CSPlayerEvent.Pause, PlayerEventSource.Player);
            }
            if (!zHasPreview || (subView = PlayerView.this.getSubView()) == null) {
                return;
            }
            subView.setVisibility(8);
        }

        @Override // com.github.rubensousa.previewseekbar.PreviewBar.OnScrubListener
        public void onScrubStop(final PreviewBar previewBar) {
            IPlayer player = PlayerView.this.getPlayer();
            CS3IPlayer cS3IPlayer = player instanceof CS3IPlayer ? (CS3IPlayer) player : null;
            if (cS3IPlayer == null) {
                return;
            }
            if (this.$resume.element) {
                cS3IPlayer.handleEvent(CSPlayerEvent.Play, PlayerEventSource.Player);
            }
            SubtitleView subView = PlayerView.this.getSubView();
            if (subView != null) {
                final PlayerView playerView = PlayerView.this;
                subView.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView$initialize$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PlayerView.AnonymousClass2.onScrubStop$lambda$0(previewBar, playerView);
                    }
                }, 200L);
            }
        }

        static final void onScrubStop$lambda$0(PreviewBar previewBar, PlayerView playerView) {
            SubtitleView subView;
            if ((previewBar != null && previewBar.isPreviewEnabled() && previewBar.isShowingPreview()) || (subView = playerView.getSubView()) == null) {
                return;
            }
            subView.setVisibility(0);
        }
    }

    static final void initialize$lambda$0(PlayerView playerView, ImageView imageView, long j, long j2) {
        IPlayer iPlayer = playerView.player;
        CS3IPlayer cS3IPlayer = iPlayer instanceof CS3IPlayer ? (CS3IPlayer) iPlayer : null;
        if (cS3IPlayer == null) {
            return;
        }
        Bitmap preview = cS3IPlayer.getPreview(j / j2);
        imageView.setVisibility(preview == null ? 8 : 0);
        imageView.setImageBitmap(preview);
    }

    static final void initialize$lambda$2(PlayerView playerView, View view) {
        scheduleAutoHide$default(playerView, 0L, 1, null);
        if (playerView.currentPlayerStatus == CSPlayerLoading.IsEnded && Globals.INSTANCE.isLayout(1)) {
            playerView.player.handleEvent(CSPlayerEvent.Restart, PlayerEventSource.UI);
        } else {
            playerView.player.handleEvent(CSPlayerEvent.PlayPauseToggle, PlayerEventSource.UI);
        }
    }

    static final void initialize$lambda$3(PlayerView playerView, View view) {
        scheduleAutoHide$default(playerView, 0L, 1, null);
        playerView.gestureHelper.rewind();
    }

    static final void initialize$lambda$4(PlayerView playerView, View view) {
        scheduleAutoHide$default(playerView, 0L, 1, null);
        playerView.gestureHelper.fastForward();
    }

    public final boolean getFullscreenNotch() {
        return this.fullscreenNotch;
    }

    public final void setFullscreenNotch(boolean z) {
        this.fullscreenNotch = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void enterFullscreen$default(PlayerView playerView, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = new Function0() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            };
        }
        playerView.enterFullscreen(function0);
    }

    public final void enterFullscreen(Function0<Unit> updateOrientation) {
        Window window;
        Window window2;
        Intrinsics.checkNotNullParameter(updateOrientation, "updateOrientation");
        Context context = getContext();
        WindowManager.LayoutParams attributes = null;
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (isFullScreen()) {
            if (activity != null) {
                UIHelper.INSTANCE.hideSystemUI(activity);
            }
            if (Build.VERSION.SDK_INT >= 28 && this.fullscreenNotch) {
                if (activity != null && (window2 = activity.getWindow()) != null) {
                    attributes = window2.getAttributes();
                }
                if (attributes != null) {
                    attributes.layoutInDisplayCutoutMode = 1;
                }
                if (activity != null && (window = activity.getWindow()) != null) {
                    window.setAttributes(attributes);
                }
            }
        }
        updateOrientation.invoke();
    }

    public final void exitFullscreen() {
        Window window;
        Window window2;
        Context context = getContext();
        WindowManager.LayoutParams attributes = null;
        Activity activity = context instanceof Activity ? (Activity) context : null;
        this.gestureHelper.resetZoomToDefault();
        if (activity != null) {
            activity.setRequestedOrientation(2);
        }
        if (activity != null && (window2 = activity.getWindow()) != null) {
            attributes = window2.getAttributes();
        }
        if (attributes != null) {
            attributes.screenBrightness = -1.0f;
        }
        if (Build.VERSION.SDK_INT >= 28 && attributes != null) {
            attributes.layoutInDisplayCutoutMode = 0;
        }
        if (activity != null && (window = activity.getWindow()) != null) {
            window.setAttributes(attributes);
        }
        if (activity != null) {
            UIHelper.INSTANCE.showSystemUI(activity);
        }
    }

    public final void onStop() {
        this.player.onStop();
    }

    public final void onResume(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        this.player.onResume(ctx);
    }

    public final void release() {
        this.player.release();
        this.player.releaseCallbacks();
        this.player = new CS3IPlayer();
        PlayerPipHelper playerPipHelper = PlayerPipHelper.INSTANCE;
        Context context = getContext();
        playerPipHelper.updatePIPModeActions(context instanceof Activity ? (Activity) context : null, CSPlayerLoading.IsPaused, false, null);
        MediaSession mediaSession = this.mMediaSession;
        if (mediaSession != null) {
            mediaSession.release();
        }
        this.mMediaSession = null;
        androidx.media3.ui.PlayerView playerView = this.exoPlayerView;
        if (playerView != null) {
            playerView.setPlayer(null);
        }
        SubtitlesFragment.INSTANCE.getApplyStyleEvent().minusAssign(this.subStyleListener);
        this.gestureHelper.release();
        this.autoHideHandler.removeCallbacksAndMessages(null);
        keepScreenOn(false);
    }

    public final void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, final Activity activity) {
        try {
            CommonActivity.INSTANCE.setInPIPMode(isInPictureInPictureMode);
            FrameLayout frameLayout = this.piphide;
            if (isInPictureInPictureMode) {
                if (frameLayout != null) {
                    frameLayout.setVisibility(8);
                }
                this.pipReceiver = new BroadcastReceiver() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView.onPictureInPictureModeChanged.1
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context, Intent intent) {
                        Intrinsics.checkNotNullParameter(context, "context");
                        Intrinsics.checkNotNullParameter(intent, "intent");
                        if (Intrinsics.areEqual(IPlayerKt.ACTION_MEDIA_CONTROL, intent.getAction())) {
                            PlayerView.this.getPlayer().handleEvent(CSPlayerEvent.getEntries().get(intent.getIntExtra(IPlayerKt.EXTRA_CONTROL_TYPE, 0)), PlayerEventSource.UI);
                        }
                    }
                };
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(IPlayerKt.ACTION_MEDIA_CONTROL);
                if (Build.VERSION.SDK_INT >= 33) {
                    if (activity != null) {
                        activity.registerReceiver(this.pipReceiver, intentFilter, 2);
                    }
                } else if (activity != null) {
                    activity.registerReceiver(this.pipReceiver, intentFilter);
                }
                CSPlayerLoading cSPlayerLoading = this.player.getIsPlaying() ? CSPlayerLoading.IsPlaying : CSPlayerLoading.IsPaused;
                updateIsPlaying(cSPlayerLoading, cSPlayerLoading);
                return;
            }
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
            }
            Callbacks callbacks = this.callbacks;
            if (callbacks != null) {
                callbacks.exitedPipMode();
            }
            final BroadcastReceiver broadcastReceiver = this.pipReceiver;
            if (broadcastReceiver != null) {
            }
            if (activity != null) {
                UIHelper.INSTANCE.hideSystemUI(activity);
            }
            UIHelper.INSTANCE.hideKeyboard(this);
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onPictureInPictureModeChanged$lambda$1$0(Activity activity, BroadcastReceiver broadcastReceiver) {
        if (activity == null) {
            return null;
        }
        activity.unregisterReceiver(broadcastReceiver);
        return Unit.INSTANCE;
    }

    private final void keepScreenOn(boolean on) {
        Window window;
        Context context = getContext();
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity == null || (window = activity.getWindow()) == null) {
            return;
        }
        if (on) {
            window.addFlags(128);
        } else {
            window.clearFlags(128);
        }
    }

    public final void updateIsPlaying(CSPlayerLoading wasPlaying, CSPlayerLoading isPlaying) {
        ImageView imageView;
        Intrinsics.checkNotNullParameter(wasPlaying, "wasPlaying");
        Intrinsics.checkNotNullParameter(isPlaying, "isPlaying");
        boolean z = false;
        boolean z2 = true;
        boolean z3 = CSPlayerLoading.IsPlaying == isPlaying;
        boolean z4 = CSPlayerLoading.IsBuffering == isPlaying;
        this.currentPlayerStatus = isPlaying;
        keepScreenOn(z3 || z4);
        FrameLayout frameLayout = this.playerPausePlayHolderHolder;
        if (z4) {
            if (frameLayout != null) {
                frameLayout.setVisibility(8);
            }
            ProgressBar progressBar = this.playerBuffering;
            if (progressBar != null) {
                progressBar.setVisibility(0);
            }
        } else {
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
            }
            ProgressBar progressBar2 = this.playerBuffering;
            if (progressBar2 != null) {
                progressBar2.setVisibility(8);
            }
            if (isPlaying == CSPlayerLoading.IsEnded && Globals.INSTANCE.isLayout(1)) {
                ImageView imageView2 = this.playerPausePlay;
                if (imageView2 != null) {
                    imageView2.setImageResource(com.lagradost.cloudstream3.R.drawable.ic_baseline_replay_24);
                }
            } else {
                ImageView imageView3 = this.playerPausePlay;
                if (wasPlaying != isPlaying) {
                    if (imageView3 != null) {
                        imageView3.setImageResource(z3 ? com.lagradost.cloudstream3.R.drawable.play_to_pause : com.lagradost.cloudstream3.R.drawable.pause_to_play);
                    }
                    ImageView imageView4 = this.playerPausePlay;
                    Drawable drawable = imageView4 != null ? imageView4.getDrawable() : null;
                    if (Build.VERSION.SDK_INT > 28 && CommonActivity$$ExternalSyntheticApiModelOutline0.m8393m((Object) drawable)) {
                        CommonActivity$$ExternalSyntheticApiModelOutline0.m((Object) drawable).start();
                        z = true;
                    }
                    if (drawable instanceof AnimatedVectorDrawable) {
                        ((AnimatedVectorDrawable) drawable).start();
                        z = true;
                    }
                    if (drawable instanceof AnimatedVectorDrawableCompat) {
                        ((AnimatedVectorDrawableCompat) drawable).start();
                    } else {
                        z2 = z;
                    }
                    if (!z2 && (imageView = this.playerPausePlay) != null) {
                        imageView.setImageResource(z3 ? com.lagradost.cloudstream3.R.drawable.netflix_pause : com.lagradost.cloudstream3.R.drawable.netflix_play);
                    }
                } else if (imageView3 != null) {
                    imageView3.setImageResource(z3 ? com.lagradost.cloudstream3.R.drawable.netflix_pause : com.lagradost.cloudstream3.R.drawable.netflix_play);
                }
            }
        }
        PlayerPipHelper playerPipHelper = PlayerPipHelper.INSTANCE;
        Context context = getContext();
        playerPipHelper.updatePIPModeActions(context instanceof Activity ? (Activity) context : null, isPlaying, this.hasPipModeSupport, this.player.getAspectRatio());
    }

    private final void requestAudioFocus() {
        if (Build.VERSION.SDK_INT >= 26) {
            Context context = getContext();
            Activity activity = context instanceof Activity ? (Activity) context : null;
            if (activity != null) {
                AppContextUtils.INSTANCE.requestLocalAudioFocus(activity, AppContextUtils.INSTANCE.getFocusRequest());
            }
        }
    }

    private final void playerUpdated(Object player) {
        if (player instanceof ExoPlayer) {
            MediaSession mediaSession = this.mMediaSession;
            if (mediaSession != null) {
                mediaSession.release();
            }
            Player player2 = (Player) player;
            this.mMediaSession = new MediaSession.Builder(getContext(), player2).setId(String.valueOf(System.currentTimeMillis())).build();
            androidx.media3.ui.PlayerView playerView = this.exoPlayerView;
            if (playerView != null) {
                playerView.setShowMultiWindowTimeBar(true);
            }
            androidx.media3.ui.PlayerView playerView2 = this.exoPlayerView;
            if (playerView2 != null) {
                playerView2.setPlayer(player2);
            }
            androidx.media3.ui.PlayerView playerView3 = this.exoPlayerView;
            if (playerView3 != null) {
                playerView3.performClick();
            }
        }
        Callbacks callbacks = this.callbacks;
        if (callbacks != null) {
            callbacks.playerUpdated(player);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onSubStyleChanged(SaveCaptionStyle style) {
        this.player.updateSubtitleStyle(style);
        IPlayer.CC.seekTime$default(this.player, -1L, null, 2, null);
    }

    private static final void playerError$showErrorToast(PlayerView playerView, String str) {
        Callbacks callbacks = playerView.callbacks;
        if (callbacks != null && callbacks.hasNextMirror()) {
            CommonActivity.INSTANCE.showToast(str, (Integer) 0);
            Callbacks callbacks2 = playerView.callbacks;
            if (callbacks2 != null) {
                callbacks2.nextMirror();
                return;
            }
            return;
        }
        CommonActivity.INSTANCE.showToast(playerView.getContext().getString(com.lagradost.cloudstream3.R.string.no_links_found_toast) + '\n' + str, (Integer) 1);
        Context context = playerView.getContext();
        FragmentActivity fragmentActivity = context instanceof FragmentActivity ? (FragmentActivity) context : null;
        if (fragmentActivity != null) {
            UIHelper.popCurrentPage$default(UIHelper.INSTANCE, fragmentActivity, null, 1, null);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void playerError(Throwable exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        if (exception instanceof PlaybackException) {
            String message = exception.getMessage();
            if (message == null) {
                message = "";
            }
            PlaybackException playbackException = (PlaybackException) exception;
            String errorCodeName = playbackException.getErrorCodeName();
            Intrinsics.checkNotNullExpressionValue(errorCodeName, "getErrorCodeName(...)");
            int i = playbackException.errorCode;
            if (i != 17 && i != 19) {
                if (i != 1001 && i != 1003) {
                    if (i != 2008) {
                        if (i != 5002) {
                            switch (i) {
                                case 2000:
                                case 2005:
                                case 2006:
                                    break;
                                case 2001:
                                case 2002:
                                case 2003:
                                case 2004:
                                    break;
                                default:
                                    switch (i) {
                                        case PlaybackException.ERROR_CODE_PARSING_CONTAINER_MALFORMED /* 3001 */:
                                        case PlaybackException.ERROR_CODE_PARSING_MANIFEST_MALFORMED /* 3002 */:
                                            playerError$showErrorToast(this, getContext().getString(com.lagradost.cloudstream3.R.string.encoding_error) + '\n' + errorCodeName + " (" + i + ")\n" + message);
                                            break;
                                        case PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED /* 3003 */:
                                            break;
                                        default:
                                            switch (i) {
                                                case PlaybackException.ERROR_CODE_DECODER_INIT_FAILED /* 4001 */:
                                                case PlaybackException.ERROR_CODE_DECODER_QUERY_FAILED /* 4002 */:
                                                case PlaybackException.ERROR_CODE_DECODING_FAILED /* 4003 */:
                                                    break;
                                                case PlaybackException.ERROR_CODE_DECODING_FORMAT_EXCEEDS_CAPABILITIES /* 4004 */:
                                                case PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED /* 4005 */:
                                                    playerError$showErrorToast(this, getContext().getString(com.lagradost.cloudstream3.R.string.unsupported_error) + '\n' + errorCodeName + " (" + i + ")\n" + message);
                                                    break;
                                                default:
                                                    playerError$showErrorToast(this, getContext().getString(com.lagradost.cloudstream3.R.string.unexpected_error) + '\n' + errorCodeName + " (" + i + ")\n" + message);
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            return;
                        }
                    }
                    playerError$showErrorToast(this, getContext().getString(com.lagradost.cloudstream3.R.string.source_error) + '\n' + errorCodeName + " (" + i + ")\n" + message);
                    return;
                }
                playerError$showErrorToast(this, getContext().getString(com.lagradost.cloudstream3.R.string.remote_error) + '\n' + errorCodeName + " (" + i + ")\n" + message);
                return;
            }
            playerError$showErrorToast(this, getContext().getString(com.lagradost.cloudstream3.R.string.render_error) + '\n' + errorCodeName + " (" + i + ")\n" + message);
            return;
        }
        if (exception instanceof SocketTimeoutException) {
            playerError$showErrorToast(this, getContext().getString(com.lagradost.cloudstream3.R.string.remote_error) + '\n' + exception.getMessage());
            return;
        }
        if (exception instanceof ErrorLoadingException) {
            String message2 = exception.getMessage();
            if (message2 != null) {
                playerError$showErrorToast(this, message2);
                return;
            } else {
                playerError$showErrorToast(this, exception.toString());
                return;
            }
        }
        String message3 = exception.getMessage();
        if (message3 != null) {
            playerError$showErrorToast(this, message3);
        } else {
            playerError$showErrorToast(this, exception.toString());
        }
    }

    public final void nextResize() {
        int size = (this.resizeMode + 1) % PlayerResize.getEntries().size();
        this.resizeMode = size;
        resize(size, true);
    }

    public final void resize(int resize, boolean showToast) {
        this.gestureHelper.clearZoomState();
        resize(PlayerResize.getEntries().get(resize), showToast);
    }

    public final void resize(PlayerResize resize, boolean showToast) {
        Intrinsics.checkNotNullParameter(resize, "resize");
        DataStoreHelper.INSTANCE.setResizeMode(resize.ordinal());
        int i = WhenMappings.$EnumSwitchMapping$0[resize.ordinal()];
        int i2 = 3;
        if (i != 1) {
            if (i == 2) {
                i2 = 0;
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                i2 = 4;
            }
        }
        androidx.media3.ui.PlayerView playerView = this.exoPlayerView;
        if (playerView != null) {
            playerView.setResizeMode(i2);
        }
        if (showToast) {
            CommonActivity.INSTANCE.showToast(resize.getNameRes(), (Integer) 0);
        }
    }

    public final int dynamicOrientation() {
        return (!Globals.INSTANCE.isLayout(6) && this.autoPlayerRotateEnabled && this.isVerticalOrientation) ? 7 : 6;
    }

    public final void mainCallback(PlayerEvent event) {
        Callbacks callbacks;
        Intrinsics.checkNotNullParameter(event, "event");
        boolean z = event instanceof DownloadEvent;
        if (!z) {
            Log.i(TAG, "Handle event: " + event);
        }
        if (z) {
            Callbacks callbacks2 = this.callbacks;
            if (callbacks2 != null) {
                callbacks2.onDownload((DownloadEvent) event);
                return;
            }
            return;
        }
        if (event instanceof ResizedEvent) {
            ResizedEvent resizedEvent = (ResizedEvent) event;
            if (resizedEvent.getWidth() > 0 && resizedEvent.getHeight() > 0) {
                this.isVerticalOrientation = !Globals.INSTANCE.isLayout(6) && resizedEvent.getHeight() > resizedEvent.getWidth();
            }
            Callbacks callbacks3 = this.callbacks;
            if (callbacks3 != null) {
                callbacks3.playerDimensionsLoaded(resizedEvent.getWidth(), resizedEvent.getHeight());
                return;
            }
            return;
        }
        if (event instanceof PlayerAttachedEvent) {
            playerUpdated(((PlayerAttachedEvent) event).getPlayer());
            return;
        }
        if (event instanceof SubtitlesUpdatedEvent) {
            Callbacks callbacks4 = this.callbacks;
            if (callbacks4 != null) {
                callbacks4.subtitlesChanged();
                return;
            }
            return;
        }
        if (event instanceof TimestampSkippedEvent) {
            Callbacks callbacks5 = this.callbacks;
            if (callbacks5 != null) {
                callbacks5.onTimestampSkipped(((TimestampSkippedEvent) event).getTimestamp());
                return;
            }
            return;
        }
        if (event instanceof TimestampInvokedEvent) {
            Callbacks callbacks6 = this.callbacks;
            if (callbacks6 != null) {
                callbacks6.onTimestamp(((TimestampInvokedEvent) event).getTimestamp());
                return;
            }
            return;
        }
        if (event instanceof TracksChangedEvent) {
            Callbacks callbacks7 = this.callbacks;
            if (callbacks7 != null) {
                callbacks7.onTracksInfoChanged();
                return;
            }
            return;
        }
        if (event instanceof EmbeddedSubtitlesFetchedEvent) {
            Callbacks callbacks8 = this.callbacks;
            if (callbacks8 != null) {
                callbacks8.embeddedSubtitlesFetched(((EmbeddedSubtitlesFetchedEvent) event).getTracks());
                return;
            }
            return;
        }
        if (event instanceof ErrorEvent) {
            Callbacks callbacks9 = this.callbacks;
            ErrorEvent errorEvent = (ErrorEvent) event;
            if (callbacks9 != null) {
                callbacks9.playerError(errorEvent.getError());
                return;
            } else {
                playerError(errorEvent.getError());
                return;
            }
        }
        if (event instanceof RequestAudioFocusEvent) {
            requestAudioFocus();
            return;
        }
        if (event instanceof EpisodeSeekEvent) {
            int offset = ((EpisodeSeekEvent) event).getOffset();
            if (offset != -1) {
                if (offset == 1 && (callbacks = this.callbacks) != null) {
                    callbacks.nextEpisode();
                    return;
                }
                return;
            }
            Callbacks callbacks10 = this.callbacks;
            if (callbacks10 != null) {
                callbacks10.prevEpisode();
                return;
            }
            return;
        }
        if (event instanceof StatusEvent) {
            StatusEvent statusEvent = (StatusEvent) event;
            updateIsPlaying(statusEvent.getWasPlaying(), statusEvent.isPlaying());
            scheduleAutoHide$default(this, 0L, 1, null);
            Callbacks callbacks11 = this.callbacks;
            if (callbacks11 != null) {
                callbacks11.playerStatusChanged();
                return;
            }
            return;
        }
        if (event instanceof PositionEvent) {
            Callbacks callbacks12 = this.callbacks;
            if (callbacks12 != null) {
                PositionEvent positionEvent = (PositionEvent) event;
                callbacks12.playerPositionChanged(positionEvent.getToMs(), positionEvent.getDurationMs());
                return;
            }
            return;
        }
        if (event instanceof VideoEndedEvent) {
            Context context = getContext();
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (defaultSharedPreferences == null || !defaultSharedPreferences.getBoolean(context.getString(com.lagradost.cloudstream3.R.string.autoplay_next_key), true)) {
                return;
            }
            this.player.handleEvent(CSPlayerEvent.NextEpisode, PlayerEventSource.Player);
            return;
        }
        if (event instanceof PauseEvent) {
            return;
        }
        boolean z2 = event instanceof PlayEvent;
    }

    public final void setRemainingTimeCounter(boolean showRemaining) {
        setDurationMode(showRemaining);
        TextView textView = this.exoDuration;
        if (textView != null) {
            textView.setVisibility(showRemaining ? 4 : 0);
        }
        TextView textView2 = this.timeLeft;
        if (textView2 != null) {
            textView2.setVisibility(showRemaining ? 0 : 8);
        }
        if (showRemaining) {
            updateRemainingTime();
        }
    }

    public final void updateRemainingTime() {
        Long duration = this.player.getDuration();
        Long position = this.player.getPosition();
        LivePreviewTimeBar livePreviewTimeBar = this.exoProgress;
        if (livePreviewTimeBar != null && livePreviewTimeBar.isAtLiveEdge()) {
            TextView textView = this.timeLeft;
            if (textView != null) {
                textView.setAlpha(0.0f);
            }
            TextView textView2 = this.exoDuration;
            if (textView2 != null) {
                textView2.setAlpha(0.0f);
            }
            View view = this.timeLive;
            if (view != null) {
                view.setVisibility(0);
            }
        } else {
            TextView textView3 = this.timeLeft;
            if (textView3 != null) {
                textView3.setAlpha(1.0f);
            }
            TextView textView4 = this.exoDuration;
            if (textView4 != null) {
                textView4.setAlpha(1.0f);
            }
            View view2 = this.timeLive;
            if (view2 != null) {
                view2.setVisibility(8);
            }
        }
        if (duration == null || duration.longValue() <= 1 || position == null) {
            return;
        }
        long jLongValue = ((duration.longValue() - position.longValue()) + 500) / 1000;
        TextView textView5 = this.timeLeft;
        if (textView5 != null) {
            textView5.setText("-" + DateUtils.formatElapsedTime(jLongValue));
        }
    }

    public static /* synthetic */ void scheduleAutoHide$default(PlayerView playerView, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 3000;
        }
        playerView.scheduleAutoHide(j);
    }

    public final void scheduleAutoHide(long delayMs) {
        final int i = this.autoHideToken + 1;
        this.autoHideToken = i;
        this.autoHideHandler.removeCallbacksAndMessages(null);
        this.autoHideHandler.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.PlayerView$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                PlayerView.scheduleAutoHide$lambda$0(i, this);
            }
        }, delayMs);
    }

    static final void scheduleAutoHide$lambda$0(int i, PlayerView playerView) {
        Callbacks callbacks;
        Callbacks callbacks2;
        if (i != playerView.autoHideToken || playerView.gestureHelper.getIsCurrentTouchValid() || (callbacks = playerView.callbacks) == null || !callbacks.isUIShowing() || (callbacks2 = playerView.callbacks) == null) {
            return;
        }
        callbacks2.onAutoHideUI();
    }

    public final void cancelAutoHide() {
        this.autoHideToken++;
        this.autoHideHandler.removeCallbacksAndMessages(null);
    }
}
