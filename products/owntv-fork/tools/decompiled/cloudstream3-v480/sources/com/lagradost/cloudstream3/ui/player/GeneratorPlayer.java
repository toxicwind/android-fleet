package com.lagradost.cloudstream3.ui.player;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.app.PendingIntentCompat;
import androidx.core.content.ContextCompat;
import androidx.core.os.EnvironmentCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerNotificationManager;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import coil3.network.internal.UtilsKt;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.databinding.DialogOnlineSubtitlesBinding;
import com.lagradost.cloudstream3.databinding.FragmentPlayerBinding;
import com.lagradost.cloudstream3.databinding.PlayerCustomLayoutBinding;
import com.lagradost.cloudstream3.databinding.PlayerSelectSourceAndSubsBinding;
import com.lagradost.cloudstream3.databinding.PlayerSelectTracksBinding;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.LifecycleKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.syncproviders.SubtitleRepo;
import com.lagradost.cloudstream3.ui.BaseAdapter;
import com.lagradost.cloudstream3.ui.BaseAdapterKt;
import com.lagradost.cloudstream3.ui.download.DownloadButtonSetup;
import com.lagradost.cloudstream3.ui.download.DownloadClickEvent;
import com.lagradost.cloudstream3.ui.player.live.LiveManagerKt;
import com.lagradost.cloudstream3.ui.player.source_priority.LinkSource;
import com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper;
import com.lagradost.cloudstream3.ui.player.source_priority.QualityProfileDialog;
import com.lagradost.cloudstream3.ui.result.EpisodeAdapter;
import com.lagradost.cloudstream3.ui.result.EpisodeClickEvent;
import com.lagradost.cloudstream3.ui.result.LinearListLayoutKt;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.ui.result.ResultFragment;
import com.lagradost.cloudstream3.ui.result.ResultViewModel2;
import com.lagradost.cloudstream3.ui.result.SyncViewModel;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment;
import com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragmentKt;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.Qualities;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import com.lagradost.cloudstream3.utils.videoskip.VideoSkipStamp;
import com.lagradost.safefile.SafeFile;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.collections.immutable.PersistentSet;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: GeneratorPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0080\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0017\n\u0002\u0010\"\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 ©\u00012\u00020\u0001:\u0004©\u0001ª\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010%\u001a\u00020\u00072\b\u0010&\u001a\u0004\u0018\u00010\u00142\u0006\u0010'\u001a\u00020\u0007H\u0002J\u0016\u0010(\u001a\u00020)2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00140!H\u0016J\b\u0010+\u001a\u00020)H\u0016J\b\u0010,\u001a\u00020)H\u0016J\b\u0010-\u001a\u00020\u0007H\u0002J\b\u0010.\u001a\u00020/H\u0002J\u0012\u00102\u001a\u00020)2\b\u00103\u001a\u0004\u0018\u00010\u0011H\u0002J \u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u001f2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u0005H\u0002J\u0010\u0010<\u001a\u00020;2\u0006\u00107\u001a\u000208H\u0002J\u0012\u0010=\u001a\u00020)2\b\u0010>\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010?\u001a\u00020)2\u0006\u0010@\u001a\u00020AH\u0016J\u0010\u0010B\u001a\u00020)2\u0006\u0010@\u001a\u00020AH\u0002J0\u0010C\u001a\u00020)2\u001e\u0010D\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u0010j\u0004\u0018\u0001`E2\u0006\u0010F\u001a\u00020\u0007H\u0002J\b\u0010G\u001a\u00020HH\u0002J\u0016\u0010I\u001a\u00020\u001f2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u0007J(\u0010M\u001a\u00020)2\u0006\u00107\u001a\u0002082\b\u0010N\u001a\u0004\u0018\u00010O2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020)0QH\u0016J\b\u0010R\u001a\u00020)H\u0002J!\u0010S\u001a\u00020)2\u0012\u0010T\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140U\"\u00020\u0014H\u0003¢\u0006\u0002\u0010VJ\u0010\u0010Z\u001a\u0002012\u0006\u0010[\u001a\u00020\\H\u0002J\b\u0010]\u001a\u00020)H\u0016J\b\u0010^\u001a\u00020)H\u0016J\u0010\u0010_\u001a\u00020)2\u0006\u0010`\u001a\u00020aH\u0016J\b\u0010b\u001a\u00020)H\u0002J\b\u0010c\u001a\u00020)H\u0002J\b\u0010d\u001a\u00020)H\u0002J\b\u0010e\u001a\u00020)H\u0016J\b\u0010f\u001a\u00020)H\u0016J\b\u0010g\u001a\u00020\u0007H\u0016J\b\u0010h\u001a\u00020)H\u0016J\b\u0010i\u001a\u00020)H\u0016J\u0018\u0010u\u001a\u00020)2\u0006\u0010v\u001a\u00020/2\u0006\u0010w\u001a\u00020/H\u0016J(\u0010x\u001a\u0004\u0018\u00010\u00142\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00140y2\u0006\u0010z\u001a\u00020\u00072\u0006\u0010{\u001a\u00020\u0007H\u0002J\b\u0010|\u001a\u00020\u0007H\u0002J\b\u0010}\u001a\u00020)H\u0002J\b\u0010~\u001a\u00020)H\u0002J\n\u0010\u007f\u001a\u0004\u0018\u00010\u001fH\u0002J\t\u0010\u0080\u0001\u001a\u00020\u001fH\u0002J\u0007\u0010\u0081\u0001\u001a\u00020)J\u001e\u0010\u0082\u0001\u001a\u00020)2\u0015\u0010\u0083\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0010J\u0016\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u001f2\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u001fH\u0002J\u0014\u0010\u0086\u0001\u001a\u00020\u001f2\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u001fH\u0002J\t\u0010\u0087\u0001\u001a\u00020)H\u0002J\u001b\u0010\u0088\u0001\u001a\u00020)2\u0007\u0010\u0089\u0001\u001a\u00020\u00052\u0007\u0010\u008a\u0001\u001a\u00020\u0005H\u0016J\u0015\u0010\u008b\u0001\u001a\u00020)2\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001H\u0002J\u000e\u0010\u008e\u0001\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010lJ\u0012\u0010\u009a\u0001\u001a\u00020)2\u0007\u0010\u009b\u0001\u001a\u00020\u0007H\u0002J\u0013\u0010\u009c\u0001\u001a\u00020)2\b\u0010\u009d\u0001\u001a\u00030\u009e\u0001H\u0016J\u0015\u0010\u009f\u0001\u001a\u00020)2\n\u0010\u009d\u0001\u001a\u0005\u0018\u00010\u009e\u0001H\u0016J\t\u0010 \u0001\u001a\u00020\u0007H\u0016J\t\u0010¡\u0001\u001a\u00020)H\u0016J\t\u0010¢\u0001\u001a\u00020)H\u0007J\u0007\u0010£\u0001\u001a\u00020)J\u0013\u0010¤\u0001\u001a\u00020)2\b\u0010¥\u0001\u001a\u00030\u008d\u0001H\u0016J\u001f\u0010¦\u0001\u001a\u00020)2\b\u0010§\u0001\u001a\u00030¨\u00012\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010W\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u001f Y*\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010U0U0XX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010j\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010o\u001a\u0004\bk\u0010l\"\u0004\bm\u0010nR\u001a\u0010p\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010r\"\u0004\bs\u0010tR\"\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u0090\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001\"\u0006\b\u0093\u0001\u0010\u0094\u0001R\u001f\u0010\u0095\u0001\u001a\u00020\u0005X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001\"\u0006\b\u0098\u0001\u0010\u0099\u0001¨\u0006«\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/GeneratorPlayer;", "Lcom/lagradost/cloudstream3/ui/player/FullScreenPlayer;", "<init>", "()V", "limitTitle", "", "showTitle", "", "showName", "showResolution", "showMediaInfo", "viewModel", "Lcom/lagradost/cloudstream3/ui/player/PlayerGeneratorViewModel;", "sync", "Lcom/lagradost/cloudstream3/ui/result/SyncViewModel;", "currentSelectedLink", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "currentSelectedSubtitles", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "currentMeta", "", "getCurrentMeta", "()Ljava/lang/Object;", "nextMeta", "getNextMeta", "isPlayerActive", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isNextEpisode", "preferredAutoSelectSubtitles", "", "allMeta", "", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "getAllMeta", "()Ljava/util/List;", "setSubtitles", MediaTrack.ROLE_SUBTITLE, "userInitiated", "embeddedSubtitlesFetched", "", "subtitles", "onTracksInfoChanged", "playerStatusChanged", "noSubtitles", "getPos", "", "currentVerifyLink", "Lkotlinx/coroutines/Job;", "loadExtractorJob", "extractorLink", "createBroadcastIntent", "Landroid/app/PendingIntent;", "action", "context", "Landroid/content/Context;", "instanceId", "cachedPlayerNotificationManager", "Landroidx/media3/ui/PlayerNotificationManager;", "getMediaNotification", "playerUpdated", "player", "onDownload", NotificationCompat.CATEGORY_EVENT, "Lcom/lagradost/cloudstream3/ui/player/DownloadEvent;", "showDownloadProgress", "loadLink", "link", "Lcom/lagradost/cloudstream3/ui/player/VideoLink;", "sameEpisode", "getMetaData", "Lcom/lagradost/cloudstream3/ui/player/GeneratorPlayer$TempMetaData;", "getName", "entry", "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;", "withLanguage", "openOnlineSubPicker", "loadResponse", "Lcom/lagradost/cloudstream3/LoadResponse;", "dismissCallback", "Lkotlin/Function0;", "openSubPicker", "addAndSelectSubtitles", "subtitleData", "", "([Lcom/lagradost/cloudstream3/ui/player/SubtitleData;)V", "subsPathPicker", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "addFirstSub", SearchIntents.EXTRA_QUERY, "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;", "showMirrorsDialogue", "showTracksDialogue", "playerError", "exception", "", "noLinksFound", "startPlayer", "showPlayerMetadata", "nextEpisode", "prevEpisode", "hasNextMirror", "nextMirror", "onDestroy", "maxEpisodeSet", "getMaxEpisodeSet", "()Ljava/lang/Integer;", "setMaxEpisodeSet", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "hasRequestedStamps", "getHasRequestedStamps", "()Z", "setHasRequestedStamps", "(Z)V", "playerPositionChanged", "position", TypedValues.TransitionType.S_DURATION, "getAutoSelectSubtitle", "", "settings", "downloads", "autoSelectFromSettings", "autoSelectFromDownloads", "autoSelectSubtitles", "getHeaderName", "getPlayerVideoTitle", "setTitle", "setPlayerDimen", "widthHeight", "videoCodecName", "mime", "audioCodecName", "updatePlayerInfo", "playerDimensionsLoaded", "width", "height", "unwrapBundle", "savedInstanceState", "Landroid/os/Bundle;", "pickLayout", "skipAnimator", "Landroid/animation/ValueAnimator;", "getSkipAnimator", "()Landroid/animation/ValueAnimator;", "setSkipAnimator", "(Landroid/animation/ValueAnimator;)V", "skipIndex", "getSkipIndex", "()I", "setSkipIndex", "(I)V", "displayTimeStamp", "show", "onTimestampSkipped", "timestamp", "Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;", "onTimestamp", "isThereEpisodes", "showEpisodesOverlay", "releasePlayer", "exitPlayer", "onSaveInstanceState", "outState", "onBindingCreated", "binding", "Lcom/lagradost/cloudstream3/databinding/FragmentPlayerBinding;", "Companion", "TempMetaData", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class GeneratorPlayer extends FullScreenPlayer {
    public static final int CHANNEL_ID = 7340;
    public static final int NOTIFICATION_ID = 2326;
    public static final String STOP_ACTION = "stopcs3";
    private PlayerNotificationManager cachedPlayerNotificationManager;
    private Pair<? extends ExtractorLink, ExtractorUri> currentSelectedLink;
    private SubtitleData currentSelectedSubtitles;
    private Job currentVerifyLink;
    private boolean hasRequestedStamps;
    private boolean isNextEpisode;
    private AtomicBoolean isPlayerActive = new AtomicBoolean(false);
    private int limitTitle;
    private Integer maxEpisodeSet;
    private String preferredAutoSelectSubtitles;
    private boolean showMediaInfo;
    private boolean showName;
    private boolean showResolution;
    private boolean showTitle;
    private ValueAnimator skipAnimator;
    private int skipIndex;
    private final ActivityResultLauncher<String[]> subsPathPicker;
    private SyncViewModel sync;
    private PlayerGeneratorViewModel viewModel;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ConcurrentHashMap<String, VideoGenerator<?>> generators = new ConcurrentHashMap<>();
    private static final SubtitleRepo[] subsProviders = AccountManager.INSTANCE.getSubtitleProviders();

    /* JADX INFO: compiled from: GeneratorPlayer.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SubtitleOrigin.values().length];
            try {
                iArr[SubtitleOrigin.URL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SubtitleOrigin.DOWNLOADED_FILE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SubtitleOrigin.EMBEDDED_IN_VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public GeneratorPlayer() {
        ActivityResultLauncher<String[]> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.OpenDocument(), new ActivityResultCallback() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda15
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda34
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return GeneratorPlayer.subsPathPicker$lambda$0$0(uri, generatorPlayer);
                    }
                });
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.subsPathPicker = activityResultLauncherRegisterForActivityResult;
    }

    /* JADX INFO: compiled from: GeneratorPlayer.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JK\u0010\r\u001a\u00020\f2\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0007\u001a\u00020\u00062(\b\u0002\u0010\u000b\u001a\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u00010\bj\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0018\u0001`\n¢\u0006\u0004\b\r\u0010\u000eR\u001d\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0018\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\t8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/GeneratorPlayer$Companion;", "", "<init>", "()V", "Lcom/lagradost/cloudstream3/ui/player/VideoGenerator;", "generator", "", "index", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "syncData", "Landroid/os/Bundle;", "newInstance", "(Lcom/lagradost/cloudstream3/ui/player/VideoGenerator;ILjava/util/HashMap;)Landroid/os/Bundle;", "", "Lcom/lagradost/cloudstream3/syncproviders/SubtitleRepo;", "subsProviders", "[Lcom/lagradost/cloudstream3/syncproviders/SubtitleRepo;", "getSubsProviders", "()[Lcom/lagradost/cloudstream3/syncproviders/SubtitleRepo;", "", "getSubsProvidersIsActive", "()Z", "subsProvidersIsActive", "NOTIFICATION_ID", "I", "CHANNEL_ID", "STOP_ACTION", "Ljava/lang/String;", "j$/util/concurrent/ConcurrentHashMap", "generators", "Lj$/util/concurrent/ConcurrentHashMap;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Bundle newInstance$default(Companion companion, VideoGenerator videoGenerator, int i, HashMap map, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                map = null;
            }
            return companion.newInstance(videoGenerator, i, map);
        }

        public final Bundle newInstance(VideoGenerator<?> generator, int index, HashMap<String, String> syncData) {
            Intrinsics.checkNotNullParameter(generator, "generator");
            Log.i(CS3IPlayerKt.TAG, "newInstance = " + syncData);
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            GeneratorPlayer.generators.put(string, generator);
            Bundle bundle = new Bundle();
            bundle.putString("uuid", string);
            bundle.putInt("index", index);
            if (syncData != null) {
                bundle.putSerializable("syncData", syncData);
            }
            return bundle;
        }

        public final SubtitleRepo[] getSubsProviders() {
            return GeneratorPlayer.subsProviders;
        }

        public final boolean getSubsProvidersIsActive() {
            return !(getSubsProviders().length == 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getCurrentMeta() {
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        GeneratorState generatorState = playerGeneratorViewModel.getState().getGeneratorState();
        if (generatorState != null) {
            return generatorState.getMeta();
        }
        return null;
    }

    private final Object getNextMeta() {
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        GeneratorState generatorState = playerGeneratorViewModel.getState().getGeneratorState();
        if (generatorState != null) {
            return generatorState.getNextMeta();
        }
        return null;
    }

    private final List<ResultEpisode> getAllMeta() {
        List<?> allMeta;
        ResultEpisode resultEpisodeCopy$default;
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        GeneratorState generatorState = playerGeneratorViewModel.getState().getGeneratorState();
        if (generatorState == null || (allMeta = generatorState.getAllMeta()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : allMeta) {
            if (obj instanceof ResultEpisode) {
                arrayList.add(obj);
            }
        }
        ArrayList<ResultEpisode> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (ResultEpisode resultEpisode : arrayList2) {
            DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Integer.valueOf(resultEpisode.getId()));
            if (viewPos != null && (resultEpisodeCopy$default = ResultEpisode.copy$default(resultEpisode, null, null, null, 0, null, null, null, null, 0, 0, viewPos.getPosition(), viewPos.getDuration(), null, null, null, null, 0, null, null, null, null, null, 4191231, null)) != null) {
                resultEpisode = resultEpisodeCopy$default;
            }
            arrayList3.add(resultEpisode);
        }
        return arrayList3;
    }

    private final boolean setSubtitles(SubtitleData subtitle, boolean userInitiated) {
        String iETF_tag;
        if (!Intrinsics.areEqual(subtitle, this.currentSelectedSubtitles) && userInitiated) {
            if (subtitle == null) {
                iETF_tag = "";
            } else {
                iETF_tag = subtitle.getIETF_tag();
            }
            if (iETF_tag != null) {
                Log.i(CS3IPlayerKt.TAG, "Set SUBTITLE_AUTO_SELECT_KEY to '" + iETF_tag + '\'');
                CloudStreamApp.INSTANCE.setKey(SubtitlesFragmentKt.SUBTITLE_AUTO_SELECT_KEY, iETF_tag);
                this.preferredAutoSelectSubtitles = iETF_tag;
            }
        }
        this.currentSelectedSubtitles = subtitle;
        return getPlayer().setPreferredSubtitles(subtitle);
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void embeddedSubtitlesFetched(List<SubtitleData> subtitles) {
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        playerGeneratorViewModel.addSubtitles(CollectionsKt.toSet(subtitles));
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onTracksInfoChanged() {
        MaterialButton materialButton;
        CurrentTracks videoTracks = getPlayer().getVideoTracks();
        PlayerCustomLayoutBinding playerBinding = getPlayerBinding();
        if (playerBinding != null && (materialButton = playerBinding.playerTracksBtt) != null) {
            MaterialButton materialButton2 = materialButton;
            boolean z = true;
            if (videoTracks.getAllVideoTracks().size() <= 1 && videoTracks.getAllAudioTracks().size() <= 1) {
                z = false;
            }
            materialButton2.setVisibility(z ? 0 : 8);
        }
        List<AudioTrack> allAudioTracks = videoTracks.getAllAudioTracks();
        if (!(allAudioTracks instanceof Collection) || !allAudioTracks.isEmpty()) {
            Iterator<T> it = allAudioTracks.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((AudioTrack) it.next()).getLanguage(), CS3IPlayer.INSTANCE.getPreferredAudioTrackLanguage())) {
                    IPlayer.CC.setPreferredAudioTrack$default(getPlayer(), CS3IPlayer.INSTANCE.getPreferredAudioTrackLanguage(), null, null, 6, null);
                    break;
                }
            }
        }
        updatePlayerInfo();
    }

    @Override // com.lagradost.cloudstream3.ui.player.FullScreenPlayer, com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerStatusChanged() {
        super.playerStatusChanged();
        if (getPlayer().getIsPlaying()) {
            PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
            if (playerGeneratorViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playerGeneratorViewModel = null;
            }
            playerGeneratorViewModel.setForceClearCache(false);
        }
    }

    private final boolean noSubtitles() {
        return setSubtitles(null, true);
    }

    private final long getPos() {
        DataStoreHelper dataStoreHelper = DataStoreHelper.INSTANCE;
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        GeneratorState generatorState = playerGeneratorViewModel.getState().getGeneratorState();
        DataStoreHelper.PosDur viewPos = dataStoreHelper.getViewPos(generatorState != null ? generatorState.getId() : null);
        if (viewPos == null || viewPos.getDuration() == 0 || (viewPos.getPosition() * 100) / viewPos.getDuration() > 95) {
            return 0L;
        }
        return viewPos.getPosition();
    }

    private final void loadExtractorJob(ExtractorLink extractorLink) {
        Job job = this.currentVerifyLink;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (extractorLink != null) {
            this.currentVerifyLink = Coroutines.INSTANCE.ioSafe(this, new GeneratorPlayer$loadExtractorJob$1$1(extractorLink, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PendingIntent createBroadcastIntent(String action, Context context, int instanceId) {
        Intent intent = new Intent(action).setPackage(context.getPackageName());
        Intrinsics.checkNotNullExpressionValue(intent, "setPackage(...)");
        intent.putExtra(PlayerNotificationManager.EXTRA_INSTANCE_ID, instanceId);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, instanceId, intent, 201326592);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
        return broadcast;
    }

    private final PlayerNotificationManager getMediaNotification(final Context context) {
        PlayerNotificationManager playerNotificationManager = this.cachedPlayerNotificationManager;
        if (playerNotificationManager != null) {
            return playerNotificationManager;
        }
        PlayerNotificationManager playerNotificationManagerBuild = new PlayerNotificationManager.Builder(context, NOTIFICATION_ID, "7340").setChannelNameResourceId(R.string.player_notification_channel_name).setChannelDescriptionResourceId(R.string.player_notification_channel_description).setMediaDescriptionAdapter(new PlayerNotificationManager.MediaDescriptionAdapter() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer.getMediaNotification.1
            @Override // androidx.media3.ui.PlayerNotificationManager.MediaDescriptionAdapter
            public /* synthetic */ CharSequence getCurrentSubText(Player player) {
                return PlayerNotificationManager.MediaDescriptionAdapter.CC.$default$getCurrentSubText(this, player);
            }

            @Override // androidx.media3.ui.PlayerNotificationManager.MediaDescriptionAdapter
            public CharSequence getCurrentContentTitle(Player player) {
                String headerName;
                Intrinsics.checkNotNullParameter(player, "player");
                Object currentMeta = GeneratorPlayer.this.getCurrentMeta();
                if (currentMeta instanceof ResultEpisode) {
                    headerName = ((ResultEpisode) currentMeta).getHeaderName();
                } else if (currentMeta instanceof ExtractorUri) {
                    ExtractorUri extractorUri = (ExtractorUri) currentMeta;
                    String headerName2 = extractorUri.getHeaderName();
                    if (headerName2 == null) {
                        headerName2 = extractorUri.getName();
                    }
                    headerName = headerName2;
                } else {
                    headerName = null;
                }
                return headerName == null ? "Unknown" : headerName;
            }

            @Override // androidx.media3.ui.PlayerNotificationManager.MediaDescriptionAdapter
            public PendingIntent createCurrentContentIntent(Player player) {
                Intrinsics.checkNotNullParameter(player, "player");
                return PendingIntentCompat.getActivity(context, 0, new Intent(context, (Class<?>) MainActivity.class), 0, false);
            }

            @Override // androidx.media3.ui.PlayerNotificationManager.MediaDescriptionAdapter
            public CharSequence getCurrentContentText(Player player) {
                Intrinsics.checkNotNullParameter(player, "player");
                Object currentMeta = GeneratorPlayer.this.getCurrentMeta();
                if (currentMeta instanceof ResultEpisode) {
                    return ((ResultEpisode) currentMeta).getName();
                }
                if (!(currentMeta instanceof ExtractorUri)) {
                    return null;
                }
                ExtractorUri extractorUri = (ExtractorUri) currentMeta;
                return extractorUri.getHeaderName() != null ? extractorUri.getName() : null;
            }

            @Override // androidx.media3.ui.PlayerNotificationManager.MediaDescriptionAdapter
            public Bitmap getCurrentLargeIcon(Player player, PlayerNotificationManager.BitmapCallback callback) {
                Intrinsics.checkNotNullParameter(player, "player");
                Intrinsics.checkNotNullParameter(callback, "callback");
                Coroutines.INSTANCE.ioSafe(this, new GeneratorPlayer$getMediaNotification$1$getCurrentLargeIcon$1(GeneratorPlayer.this, context, callback, null));
                return null;
            }
        }).setCustomActionReceiver(new PlayerNotificationManager.CustomActionReceiver() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer.getMediaNotification.2
            @Override // androidx.media3.ui.PlayerNotificationManager.CustomActionReceiver
            public Map<String, NotificationCompat.Action> createCustomActions(Context context2, int instanceId) {
                Intrinsics.checkNotNullParameter(context2, "context");
                return MapsKt.mutableMapOf(TuplesKt.to(GeneratorPlayer.STOP_ACTION, new NotificationCompat.Action(R.drawable.baseline_stop_24, context2.getString(androidx.media3.ui.R.string.exo_controls_stop_description), GeneratorPlayer.this.createBroadcastIntent(GeneratorPlayer.STOP_ACTION, context2, instanceId))));
            }

            @Override // androidx.media3.ui.PlayerNotificationManager.CustomActionReceiver
            public List<String> getCustomActions(Player player) {
                Intrinsics.checkNotNullParameter(player, "player");
                return CollectionsKt.mutableListOf(GeneratorPlayer.STOP_ACTION);
            }

            @Override // androidx.media3.ui.PlayerNotificationManager.CustomActionReceiver
            public void onCustomAction(Player player, String action, Intent intent) {
                Intrinsics.checkNotNullParameter(player, "player");
                Intrinsics.checkNotNullParameter(action, "action");
                Intrinsics.checkNotNullParameter(intent, "intent");
                if (Intrinsics.areEqual(action, GeneratorPlayer.STOP_ACTION)) {
                    GeneratorPlayer.this.exitPlayer();
                }
            }
        }).setPlayActionIconResourceId(R.drawable.ic_baseline_play_arrow_24).setPauseActionIconResourceId(R.drawable.netflix_pause).setSmallIconResourceId(R.drawable.baseline_headphones_24).setStopActionIconResourceId(R.drawable.baseline_stop_24).setRewindActionIconResourceId(R.drawable.go_back_30).setFastForwardActionIconResourceId(R.drawable.go_forward_30).setNextActionIconResourceId(R.drawable.ic_baseline_skip_next_24).setPreviousActionIconResourceId(R.drawable.baseline_skip_previous_24).build();
        playerNotificationManagerBuild.setColorized(true);
        playerNotificationManagerBuild.setUseChronometer(true);
        playerNotificationManagerBuild.setUsePreviousAction(false);
        playerNotificationManagerBuild.setUsePreviousActionInCompactView(false);
        playerNotificationManagerBuild.setUseNextAction(false);
        playerNotificationManagerBuild.setUseNextActionInCompactView(false);
        playerNotificationManagerBuild.setUseFastForwardAction(true);
        playerNotificationManagerBuild.setUseFastForwardActionInCompactView(true);
        playerNotificationManagerBuild.setUseRewindAction(true);
        playerNotificationManagerBuild.setUseFastForwardActionInCompactView(false);
        playerNotificationManagerBuild.setUseStopAction(false);
        this.cachedPlayerNotificationManager = playerNotificationManagerBuild;
        Intrinsics.checkNotNullExpressionValue(playerNotificationManagerBuild, "also(...)");
        return playerNotificationManagerBuild;
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerUpdated(Object player) {
        Context context;
        MediaSession.Token platformToken;
        super.playerUpdated(player);
        if (player == null) {
            PlayerNotificationManager playerNotificationManager = this.cachedPlayerNotificationManager;
            if (playerNotificationManager != null) {
                playerNotificationManager.setPlayer(null);
            }
            this.cachedPlayerNotificationManager = null;
            return;
        }
        if (!(player instanceof ExoPlayer) || (context = getContext()) == null) {
            return;
        }
        PlayerNotificationManager mediaNotification = getMediaNotification(context);
        mediaNotification.setPlayer((Player) player);
        androidx.media3.session.MediaSession mMediaSession = getMMediaSession();
        if (mMediaSession == null || (platformToken = mMediaSession.getPlatformToken()) == null) {
            return;
        }
        mediaNotification.setMediaSessionToken(platformToken);
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onDownload(DownloadEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        super.onDownload(event);
        showDownloadProgress(event);
    }

    private final void showDownloadProgress(final DownloadEvent event) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda19
                @Override // java.lang.Runnable
                public final void run() {
                    GeneratorPlayer.showDownloadProgress$lambda$0(this.f$0, event);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00b5  */
    static final void showDownloadProgress$lambda$0(GeneratorPlayer generatorPlayer, DownloadEvent downloadEvent) {
        TextView textView;
        TextView textView2;
        String str;
        LinearProgressIndicator linearProgressIndicator;
        PlayerCustomLayoutBinding playerBinding = generatorPlayer.getPlayerBinding();
        if (playerBinding != null && (linearProgressIndicator = playerBinding.downloadedProgress) != null) {
            boolean z = downloadEvent.getTotalBytes() <= 0 || downloadEvent.getDownloadedBytes() <= 0;
            linearProgressIndicator.setIndeterminate(z);
            if (!z) {
                linearProgressIndicator.setMax((int) (downloadEvent.getTotalBytes() / 1000));
                linearProgressIndicator.setProgress((int) (downloadEvent.getDownloadedBytes() / 1000));
            }
        }
        PlayerCustomLayoutBinding playerBinding2 = generatorPlayer.getPlayerBinding();
        TextView textView3 = playerBinding2 != null ? playerBinding2.downloadedProgressText : null;
        int i = R.string.download_size_format;
        String shortFileSize = Formatter.formatShortFileSize(generatorPlayer.getContext(), downloadEvent.getDownloadedBytes());
        Intrinsics.checkNotNullExpressionValue(shortFileSize, "formatShortFileSize(...)");
        String shortFileSize2 = Formatter.formatShortFileSize(generatorPlayer.getContext(), downloadEvent.getTotalBytes());
        Intrinsics.checkNotNullExpressionValue(shortFileSize2, "formatShortFileSize(...)");
        TextUtilKt.setText(textView3, TextUtilKt.txt(i, shortFileSize, shortFileSize2));
        String shortFileSize3 = Formatter.formatShortFileSize(generatorPlayer.getContext(), downloadEvent.getDownloadSpeed());
        PlayerCustomLayoutBinding playerBinding3 = generatorPlayer.getPlayerBinding();
        if (playerBinding3 != null && (textView2 = playerBinding3.downloadedProgressSpeedText) != null) {
            Integer connections = downloadEvent.getConnections();
            if (connections != null) {
                String str2 = String.format("%s/s - %d Connections", Arrays.copyOf(new Object[]{shortFileSize3, Integer.valueOf(connections.intValue())}, 2));
                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                if (str2 != null) {
                    str = str2;
                } else {
                    str = shortFileSize3;
                }
            } else {
                str = shortFileSize3;
            }
            textView2.setText(str);
        }
        PlayerCustomLayoutBinding playerBinding4 = generatorPlayer.getPlayerBinding();
        if (playerBinding4 == null || (textView = playerBinding4.downloadedProgressSpeedText) == null) {
            return;
        }
        textView.setVisibility(downloadEvent.getDownloadedBytes() != 0 && downloadEvent.getDownloadedBytes() - 1024 >= downloadEvent.getTotalBytes() ? 8 : 0);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x004a  */
    /* JADX WARN: Multi-variable type inference failed */
    private final void loadLink(Pair<? extends ExtractorLink, ExtractorUri> link, boolean sameEpisode) {
        boolean z;
        long j;
        Long lValueOf;
        PlayerCustomLayoutBinding playerBinding;
        LinearLayout linearLayout;
        ImageView imageView;
        CardView cardView;
        FrameLayout frameLayout;
        if (link == null) {
            return;
        }
        this.isPlayerActive.set(true);
        FragmentPlayerBinding fragmentPlayerBinding = (FragmentPlayerBinding) getBinding();
        if (fragmentPlayerBinding != null && (frameLayout = fragmentPlayerBinding.playerLoadingOverlay) != null) {
            frameLayout.setVisibility(8);
        }
        ExtractorLink first = link.getFirst();
        if ((first != null ? first.getType() : null) == ExtractorLinkType.MAGNET) {
            z = true;
        } else {
            ExtractorLink first2 = link.getFirst();
            if ((first2 != null ? first2.getType() : null) == ExtractorLinkType.TORRENT) {
                z = true;
            } else {
                z = false;
            }
        }
        PlayerCustomLayoutBinding playerBinding2 = getPlayerBinding();
        if (playerBinding2 != null && (cardView = playerBinding2.downloadHeader) != null) {
            cardView.setVisibility(8);
        }
        PlayerCustomLayoutBinding playerBinding3 = getPlayerBinding();
        if (playerBinding3 != null && (imageView = playerBinding3.downloadHeaderToggle) != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
        if (!Globals.INSTANCE.isLayout(1) && (playerBinding = getPlayerBinding()) != null && (linearLayout = playerBinding.downloadBothHeader) != null) {
            linearLayout.setVisibility(z ? 0 : 8);
        }
        showDownloadProgress(new DownloadEvent(0L, 0L, 0L, null, null, 16, null));
        this.currentSelectedLink = link;
        setPlayerDimen(null);
        setTitle();
        if (!sameEpisode) {
            this.hasRequestedStamps = false;
        }
        loadExtractorJob(link.getFirst());
        Context context = getContext();
        if (context != null) {
            ExtractorLink extractorLinkComponent1 = link.component1();
            ExtractorUri extractorUriComponent2 = link.component2();
            PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
            if (playerGeneratorViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playerGeneratorViewModel = null;
            }
            PersistentSet<SubtitleData> subtitles = playerGeneratorViewModel.getState().getSubtitles();
            IPlayer player = getPlayer();
            if (sameEpisode) {
                lValueOf = null;
            } else {
                lValueOf = Long.valueOf(this.isNextEpisode ? 0L : getPos());
            }
            PersistentSet<SubtitleData> persistentSet = subtitles;
            SubtitleData autoSelectSubtitle = sameEpisode ? this.currentSelectedSubtitles : null;
            if (autoSelectSubtitle == null) {
                autoSelectSubtitle = getAutoSelectSubtitle(persistentSet, true, true);
            }
            j = 0;
            IPlayer.CC.loadPlayer$default(player, context, sameEpisode, extractorLinkComponent1, extractorUriComponent2, lValueOf, persistentSet, autoSelectSubtitle, null, true, 128, null);
        } else {
            j = 0;
        }
        if (sameEpisode) {
            return;
        }
        getPlayer().addTimeStamps(CollectionsKt.emptyList());
        getPlayer().setSubtitleOffset(j);
    }

    /* JADX INFO: compiled from: GeneratorPlayer.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003J>\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0006HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/GeneratorPlayer$TempMetaData;", "", "episode", "", "season", "name", "", "imdbId", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getEpisode", "()Ljava/lang/Integer;", "setEpisode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSeason", "setSeason", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getImdbId", "setImdbId", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/ui/player/GeneratorPlayer$TempMetaData;", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TempMetaData {
        private Integer episode;
        private String imdbId;
        private String name;
        private Integer season;

        public TempMetaData() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ TempMetaData copy$default(TempMetaData tempMetaData, Integer num, Integer num2, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = tempMetaData.episode;
            }
            if ((i & 2) != 0) {
                num2 = tempMetaData.season;
            }
            if ((i & 4) != 0) {
                str = tempMetaData.name;
            }
            if ((i & 8) != 0) {
                str2 = tempMetaData.imdbId;
            }
            return tempMetaData.copy(num, num2, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getImdbId() {
            return this.imdbId;
        }

        public final TempMetaData copy(Integer episode, Integer season, String name, String imdbId) {
            return new TempMetaData(episode, season, name, imdbId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TempMetaData)) {
                return false;
            }
            TempMetaData tempMetaData = (TempMetaData) other;
            return Intrinsics.areEqual(this.episode, tempMetaData.episode) && Intrinsics.areEqual(this.season, tempMetaData.season) && Intrinsics.areEqual(this.name, tempMetaData.name) && Intrinsics.areEqual(this.imdbId, tempMetaData.imdbId);
        }

        public int hashCode() {
            Integer num = this.episode;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            Integer num2 = this.season;
            int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str = this.name;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.imdbId;
            return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "TempMetaData(episode=" + this.episode + ", season=" + this.season + ", name=" + this.name + ", imdbId=" + this.imdbId + ')';
        }

        public TempMetaData(Integer num, Integer num2, String str, String str2) {
            this.episode = num;
            this.season = num2;
            this.name = str;
            this.imdbId = str2;
        }

        public /* synthetic */ TempMetaData(Integer num, Integer num2, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2);
        }

        public final Integer getEpisode() {
            return this.episode;
        }

        public final void setEpisode(Integer num) {
            this.episode = num;
        }

        public final Integer getSeason() {
            return this.season;
        }

        public final void setSeason(Integer num) {
            this.season = num;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final String getImdbId() {
            return this.imdbId;
        }

        public final void setImdbId(String str) {
            this.imdbId = str;
        }
    }

    private final TempMetaData getMetaData() {
        TempMetaData tempMetaData = new TempMetaData(null, null, null, null, 15, null);
        Object currentMeta = getCurrentMeta();
        if (currentMeta instanceof ResultEpisode) {
            ResultEpisode resultEpisode = (ResultEpisode) currentMeta;
            if (!MainAPIKt.isMovieType(resultEpisode.getTvType())) {
                tempMetaData.setEpisode(Integer.valueOf(resultEpisode.getEpisode()));
                tempMetaData.setSeason(resultEpisode.getSeason());
            }
            tempMetaData.setName(resultEpisode.getHeaderName());
            return tempMetaData;
        }
        if (currentMeta instanceof ExtractorUri) {
            ExtractorUri extractorUri = (ExtractorUri) currentMeta;
            TvType tvType = extractorUri.getTvType();
            if (tvType != null && !MainAPIKt.isMovieType(tvType)) {
                tempMetaData.setEpisode(extractorUri.getEpisode());
                tempMetaData.setSeason(extractorUri.getSeason());
            }
            tempMetaData.setName(extractorUri.getHeaderName());
        }
        return tempMetaData;
    }

    public final String getName(AbstractSubtitleEntities.SubtitleEntity entry, boolean withLanguage) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        if (StringsKt.isBlank(entry.getLang()) || !withLanguage) {
            return entry.getName();
        }
        String strFromTagToLanguageName$default = SubtitleHelper.fromTagToLanguageName$default(SubtitleHelper.INSTANCE, StringsKt.trim((CharSequence) entry.getLang()).toString(), null, 2, null);
        if (strFromTagToLanguageName$default == null) {
            strFromTagToLanguageName$default = entry.getLang();
        }
        return strFromTagToLanguageName$default + ' ' + entry.getName();
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v11, types: [com.lagradost.cloudstream3.ui.player.GeneratorPlayer$openOnlineSubPicker$arrayAdapter$1] */
    /* JADX WARN: Type inference failed for: r1v8, types: [T, java.lang.String] */
    @Override // com.lagradost.cloudstream3.ui.player.FullScreenPlayer
    public void openOnlineSubPicker(final Context context, final LoadResponse loadResponse, final Function0<Unit> dismissCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dismissCallback, "dismissCallback");
        SubtitleRepo[] subtitleRepoArr = subsProviders;
        final List list = ArraysKt.toList(subtitleRepoArr);
        final boolean z = subtitleRepoArr.length == 1;
        final Dialog dialog = new Dialog(context, R.style.DialogFullscreenPlayer);
        PlayerGeneratorViewModel playerGeneratorViewModel = null;
        final DialogOnlineSubtitlesBinding dialogOnlineSubtitlesBindingInflate = DialogOnlineSubtitlesBinding.inflate(LayoutInflater.from(context), null, false);
        Intrinsics.checkNotNullExpressionValue(dialogOnlineSubtitlesBindingInflate, "inflate(...)");
        dialog.setContentView(dialogOnlineSubtitlesBindingInflate.getRoot());
        UIHelper uIHelper = UIHelper.INSTANCE;
        LinearLayout root = dialogOnlineSubtitlesBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        UIHelper.fixSystemBarsPadding$default(uIHelper, root, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = CollectionsKt.emptyList();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final int i = R.layout.sort_bottom_single_choice_double_text;
        final Context context2 = dialog.getContext();
        final ?? r0 = new ArrayAdapter<AbstractSubtitleEntities.SubtitleEntity>(i, objectRef, context, z, this, context2) { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$openOnlineSubPicker$arrayAdapter$1
            final /* synthetic */ Context $context;
            final /* synthetic */ Ref.ObjectRef<List<AbstractSubtitleEntities.SubtitleEntity>> $currentSubtitles;
            final /* synthetic */ boolean $isSingleProvider;
            final /* synthetic */ int $layout;
            final /* synthetic */ GeneratorPlayer this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context2, i);
                this.$layout = i;
                this.$currentSubtitles = objectRef;
                this.$context = context;
                this.$isSingleProvider = z;
                this.this$0 = this;
            }

            public final void setHearingImpairedIcon(ImageView imageViewEnd, int position) {
                Drawable drawable;
                if (imageViewEnd == null) {
                    return;
                }
                AbstractSubtitleEntities.SubtitleEntity subtitleEntity = (AbstractSubtitleEntities.SubtitleEntity) CollectionsKt.getOrNull(this.$currentSubtitles.element, position);
                Drawable drawable2 = null;
                if ((subtitleEntity != null ? subtitleEntity.isHearingImpaired() : false) && (drawable = ContextCompat.getDrawable(this.$context, R.drawable.ic_baseline_hearing_24)) != null) {
                    drawable.setTint(ContextCompat.getColor(this.$context, R.color.textColor));
                    drawable2 = drawable;
                }
                imageViewEnd.setImageDrawable(drawable2);
            }

            /* JADX WARN: Code duplicated, block: B:17:0x005b  */
            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int position, View convertView, ViewGroup parent) {
                String strFromTagToLanguageName$default;
                Intrinsics.checkNotNullParameter(parent, "parent");
                if (convertView == null) {
                    convertView = LayoutInflater.from(this.$context).inflate(this.$layout, (ViewGroup) null);
                }
                AbstractSubtitleEntities.SubtitleEntity item = getItem(position);
                TextView textView = (TextView) convertView.findViewById(R.id.main_text);
                TextView textView2 = (TextView) convertView.findViewById(R.id.secondary_text);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.drawable_end);
                if (textView != null) {
                    textView.setText(item != null ? this.this$0.getName(item, false) : null);
                }
                String str = "";
                if (item == null) {
                    strFromTagToLanguageName$default = "";
                } else {
                    strFromTagToLanguageName$default = SubtitleHelper.fromTagToLanguageName$default(SubtitleHelper.INSTANCE, item.getLang(), null, 2, null);
                    if (strFromTagToLanguageName$default == null) {
                        strFromTagToLanguageName$default = item.getLang();
                    }
                    if (strFromTagToLanguageName$default == null) {
                        strFromTagToLanguageName$default = "";
                    }
                }
                if (!this.$isSingleProvider && item != null) {
                    str = " · " + item.getSource();
                }
                if (textView2 != null) {
                    textView2.setText(strFromTagToLanguageName$default + str);
                }
                setHearingImpairedIcon(imageView, position);
                Intrinsics.checkNotNull(convertView);
                return convertView;
            }
        };
        dialog.show();
        dialogOnlineSubtitlesBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda50
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UIHelper.INSTANCE.dismissSafe(dialog);
            }
        });
        dialogOnlineSubtitlesBindingInflate.subtitleAdapter.setChoiceMode(1);
        dialogOnlineSubtitlesBindingInflate.subtitleAdapter.setAdapter((ListAdapter) r0);
        dialogOnlineSubtitlesBindingInflate.subtitleAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda51
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i2, long j) {
                GeneratorPlayer.openOnlineSubPicker$lambda$1(objectRef2, objectRef, adapterView, view, i2, j);
            }
        });
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = SubtitlesFragment.INSTANCE.getAutoSelectLanguageTagIETF();
        final TempMetaData metaData = getMetaData();
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(UIHelper.INSTANCE.colorFromAttribute(context, androidx.appcompat.R.attr.colorAccent));
        Intrinsics.checkNotNullExpressionValue(colorStateListValueOf, "valueOf(...)");
        dialogOnlineSubtitlesBindingInflate.searchLoadingBar.setProgressTintList(colorStateListValueOf);
        dialogOnlineSubtitlesBindingInflate.searchLoadingBar.setIndeterminateTintList(colorStateListValueOf);
        GeneratorPlayer generatorPlayer = this;
        PlayerGeneratorViewModel playerGeneratorViewModel2 = this.viewModel;
        if (playerGeneratorViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            playerGeneratorViewModel = playerGeneratorViewModel2;
        }
        LifecycleKt.observeNullable(generatorPlayer, playerGeneratorViewModel.getCurrentSubtitleYear(), new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GeneratorPlayer.openOnlineSubPicker$lambda$2(dialogOnlineSubtitlesBindingInflate, context, (Integer) obj);
            }
        });
        dialogOnlineSubtitlesBindingInflate.yearBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda53
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GeneratorPlayer.openOnlineSubPicker$lambda$3(context, this, view);
            }
        });
        dialogOnlineSubtitlesBindingInflate.subtitlesSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer.openOnlineSubPicker.5
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String query) {
                dialogOnlineSubtitlesBindingInflate.searchLoadingBar.show();
                Coroutines.INSTANCE.ioSafe(this, new GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1(query, loadResponse, metaData, objectRef3, this, list, dialogOnlineSubtitlesBindingInflate, objectRef, r0, null));
                return true;
            }
        });
        dialogOnlineSubtitlesBindingInflate.searchFilter.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda54
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GeneratorPlayer.openOnlineSubPicker$lambda$4(this.f$0, objectRef3, dialogOnlineSubtitlesBindingInflate, view);
            }
        });
        dialogOnlineSubtitlesBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GeneratorPlayer.openOnlineSubPicker$lambda$5(objectRef2, dialog, list, dialogOnlineSubtitlesBindingInflate, this, view);
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                dismissCallback.invoke();
            }
        });
        dialog.show();
        dialogOnlineSubtitlesBindingInflate.subtitlesSearch.setQuery(metaData.getName(), true);
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [T, com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities$SubtitleEntity] */
    static final void openOnlineSubPicker$lambda$1(Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, AdapterView adapterView, View view, int i, long j) {
        ?? r1 = (AbstractSubtitleEntities.SubtitleEntity) CollectionsKt.getOrNull((List) objectRef2.element, i);
        if (r1 == 0) {
            return;
        }
        objectRef.element = r1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void openOnlineSubPicker$setSubtitlesList(Ref.ObjectRef<List<AbstractSubtitleEntities.SubtitleEntity>> objectRef, GeneratorPlayer$openOnlineSubPicker$arrayAdapter$1 generatorPlayer$openOnlineSubPicker$arrayAdapter$1, List<AbstractSubtitleEntities.SubtitleEntity> list) {
        objectRef.element = list;
        generatorPlayer$openOnlineSubPicker$arrayAdapter$1.clear();
        generatorPlayer$openOnlineSubPicker$arrayAdapter$1.addAll(objectRef.element);
    }

    static final Unit openOnlineSubPicker$lambda$2(DialogOnlineSubtitlesBinding dialogOnlineSubtitlesBinding, Context context, Integer num) {
        String strValueOf;
        dialogOnlineSubtitlesBinding.subtitlesSearch.setQuery(dialogOnlineSubtitlesBinding.subtitlesSearch.getQuery(), true);
        dialogOnlineSubtitlesBinding.yearBtt.setText((num == null || (strValueOf = String.valueOf(num.intValue())) == null) ? TextUtilKt.txt(R.string.none, new Object[0]).asString(context) : strValueOf);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0098  */
    static final void openOnlineSubPicker$lambda$3(Context context, final GeneratorPlayer generatorPlayer, View view) {
        int iIntValue;
        String strAsString = TextUtilKt.txt(R.string.none, new Object[0]).asString(context);
        final List list = CollectionsKt.toList(RangesKt.downTo(Calendar.getInstance().get(1), 1900));
        List listListOf = CollectionsKt.listOf(strAsString);
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(((Number) it.next()).intValue()));
        }
        List<String> listPlus = CollectionsKt.plus((Collection) listListOf, (Iterable) arrayList);
        PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        Integer value = playerGeneratorViewModel.getCurrentSubtitleYear().getValue();
        if (value != null) {
            Integer numValueOf = Integer.valueOf(list.indexOf(Integer.valueOf(value.intValue())) + 1);
            Integer num = numValueOf.intValue() >= 0 ? numValueOf : null;
            if (num != null) {
                iIntValue = num.intValue();
            } else {
                iIntValue = 0;
            }
        } else {
            iIntValue = 0;
        }
        FragmentActivity activity = generatorPlayer.getActivity();
        if (activity != null) {
            SingleSelectionHelper.INSTANCE.showDialog(activity, listPlus, iIntValue, TextUtilKt.txt(R.string.year, new Object[0]).asString(context), true, new Function0() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratorPlayer.openOnlineSubPicker$lambda$3$4(this.f$0, list, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit openOnlineSubPicker$lambda$3$4(GeneratorPlayer generatorPlayer, List list, int i) {
        PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        playerGeneratorViewModel.setSubtitleYear((Integer) CollectionsKt.getOrNull(list, i - 1));
        return Unit.INSTANCE;
    }

    static final void openOnlineSubPicker$lambda$4(GeneratorPlayer generatorPlayer, final Ref.ObjectRef objectRef, final DialogOnlineSubtitlesBinding dialogOnlineSubtitlesBinding, View view) {
        Context context;
        String string;
        List<SubtitleHelper.LanguageMetadata> languages = SubtitleHelper.INSTANCE.getLanguages();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(languages, 10));
        for (SubtitleHelper.LanguageMetadata languageMetadata : languages) {
            arrayList.add(new Pair(languageMetadata.getIETF_tag(), SubtitleHelper.LanguageMetadata.nameNextToFlagEmoji$default(languageMetadata, null, 1, null)));
        }
        Pair pairUnzip = CollectionsKt.unzip(CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$openOnlineSubPicker$lambda$4$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                String lowerCase = StringsKt.substringAfter$default((String) ((Pair) t).getSecond(), " ", (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String lowerCase2 = StringsKt.substringAfter$default((String) ((Pair) t2).getSecond(), " ", (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                return ComparisonsKt.compareValues(lowerCase, lowerCase2);
            }
        }));
        final List list = (List) pairUnzip.component1();
        List<String> list2 = (List) pairUnzip.component2();
        FragmentActivity activity = generatorPlayer.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            int iIndexOf = list.indexOf(objectRef.element);
            if (view == null || (context = view.getContext()) == null || (string = context.getString(R.string.subs_subtitle_languages)) == null) {
                return;
            }
            singleSelectionHelper.showDialog(fragmentActivity, list2, iIndexOf, string, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratorPlayer.openOnlineSubPicker$lambda$4$3(objectRef, list, dialogOnlineSubtitlesBinding, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Object] */
    public static final Unit openOnlineSubPicker$lambda$4$3(Ref.ObjectRef objectRef, List list, DialogOnlineSubtitlesBinding dialogOnlineSubtitlesBinding, int i) {
        objectRef.element = list.get(i);
        dialogOnlineSubtitlesBinding.subtitlesSearch.setQuery(dialogOnlineSubtitlesBinding.subtitlesSearch.getQuery(), true);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final void openOnlineSubPicker$lambda$5(Ref.ObjectRef objectRef, Dialog dialog, List list, DialogOnlineSubtitlesBinding dialogOnlineSubtitlesBinding, GeneratorPlayer generatorPlayer, View view) {
        Object next;
        AbstractSubtitleEntities.SubtitleEntity subtitleEntity = (AbstractSubtitleEntities.SubtitleEntity) objectRef.element;
        if (subtitleEntity == null) {
            UIHelper.INSTANCE.dismissSafe(dialog);
            return;
        }
        Iterator it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SubtitleRepo) next).getIdPrefix(), subtitleEntity.getIdPrefix()));
        SubtitleRepo subtitleRepo = (SubtitleRepo) next;
        if (subtitleRepo == null) {
            UIHelper.INSTANCE.dismissSafe(dialog);
            return;
        }
        UIHelper uIHelper = UIHelper.INSTANCE;
        MaterialButton applyBtt = dialogOnlineSubtitlesBinding.applyBtt;
        Intrinsics.checkNotNullExpressionValue(applyBtt, "applyBtt");
        UIHelper.showProgress$default(uIHelper, applyBtt, 0, 1, null);
        Coroutines.INSTANCE.ioSafe(generatorPlayer, new GeneratorPlayer$openOnlineSubPicker$7$1(subtitleRepo, subtitleEntity, dialogOnlineSubtitlesBinding, dialog, generatorPlayer, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openSubPicker() {
        try {
            this.subsPathPicker.launch(new String[]{UtilsKt.MIME_TYPE_TEXT_PLAIN, "text/str", "application/octet-stream", "text/x-unknown", "text/vtt", "text/x-ssa", "application/ttml+xml", "application/x-mp4-vtt", "application/x-subrip"});
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addAndSelectSubtitles(SubtitleData... subtitleData) {
        Context context;
        if (subtitleData.length == 0 || (context = getContext()) == null) {
            return;
        }
        SubtitleData subtitleData2 = (SubtitleData) ArraysKt.first(subtitleData);
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        playerGeneratorViewModel.addSubtitles(ArraysKt.toSet(subtitleData));
        IPlayer player = getPlayer();
        PlayerGeneratorViewModel playerGeneratorViewModel2 = this.viewModel;
        if (playerGeneratorViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel2 = null;
        }
        player.setActiveSubtitles(playerGeneratorViewModel2.getState().getSubtitles());
        getPlayer().saveData();
        getPlayer().reloadPlayer(context);
        setSubtitles(subtitleData2, false);
        Dialog selectSourceDialog = getSelectSourceDialog();
        if (selectSourceDialog != null) {
            UIHelper.INSTANCE.dismissSafe(selectSourceDialog);
        }
        setSelectSourceDialog(null);
        CommonActivity commonActivity = CommonActivity.INSTANCE;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = context.getString(R.string.player_loaded_subtitles);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String str = String.format(string, Arrays.copyOf(new Object[]{subtitleData2.getName()}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        commonActivity.showToast(str, (Integer) 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit subsPathPicker$lambda$0$0(Uri uri, GeneratorPlayer generatorPlayer) {
        if (uri == null) {
            return Unit.INSTANCE;
        }
        Context context = generatorPlayer.getContext();
        if (context == null && (context = CloudStreamApp.INSTANCE.getContext()) == null) {
            return Unit.INSTANCE;
        }
        context.getContentResolver().takePersistableUriPermission(uri, 3);
        SafeFile safeFileFromUri = SafeFile.INSTANCE.fromUri(context, uri);
        String strName = safeFileFromUri != null ? safeFileFromUri.name() : null;
        System.out.println((Object) ("Loaded subtitle file. Selected URI path: " + uri + " - Name: " + strName));
        if (strName == null) {
            strName = uri.toString();
            Intrinsics.checkNotNullExpressionValue(strName, "toString(...)");
        }
        String str = strName;
        String string = uri.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        generatorPlayer.addAndSelectSubtitles(new SubtitleData(str, "", string, SubtitleOrigin.DOWNLOADED_FILE, PlayerSubtitleHelper.INSTANCE.toSubtitleMimeType(str), MapsKt.emptyMap(), null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$addFirstSub$1, reason: invalid class name */
    /* JADX INFO: compiled from: GeneratorPlayer.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.GeneratorPlayer$addFirstSub$1", f = "GeneratorPlayer.kt", i = {0, 0}, l = {950}, m = "invokeSuspend", n = {"$this$launch", "hasSelectASubtitle"}, nl = {1005}, s = {"L$0", "L$1"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AbstractSubtitleEntities.SubtitleSearch $query;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ GeneratorPlayer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AbstractSubtitleEntities.SubtitleSearch subtitleSearch, GeneratorPlayer generatorPlayer, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$query = subtitleSearch;
            this.this$0 = generatorPlayer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$query, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.BooleanRef booleanRef;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                this.L$0 = coroutineScope;
                this.L$1 = booleanRef2;
                this.label = 1;
                if (ParCollectionsKt.amap(ArraysKt.toList(GeneratorPlayer.INSTANCE.getSubsProviders()), new C01721(this.$query, coroutineScope, booleanRef2, this.this$0, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                booleanRef = booleanRef2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                booleanRef = (Ref.BooleanRef) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            if (!booleanRef.element && CoroutineScopeKt.isActive(coroutineScope)) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.no_subtitles, (Integer) null, 2, (Object) null);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$addFirstSub$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: GeneratorPlayer.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "provider", "Lcom/lagradost/cloudstream3/syncproviders/SubtitleRepo;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.GeneratorPlayer$addFirstSub$1$1", f = "GeneratorPlayer.kt", i = {0, 1, 1, 1}, l = {952, 980}, m = "invokeSuspend", n = {"provider", "provider", "success", "subtitleEntry"}, nl = {951, 980}, s = {"L$0", "L$0", "L$1", "L$3"}, v = 2)
        static final class C01721 extends SuspendLambda implements Function2<SubtitleRepo, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$launch;
            final /* synthetic */ Ref.BooleanRef $hasSelectASubtitle;
            final /* synthetic */ AbstractSubtitleEntities.SubtitleSearch $query;
            /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;
            final /* synthetic */ GeneratorPlayer this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01721(AbstractSubtitleEntities.SubtitleSearch subtitleSearch, CoroutineScope coroutineScope, Ref.BooleanRef booleanRef, GeneratorPlayer generatorPlayer, Continuation<? super C01721> continuation) {
                super(2, continuation);
                this.$query = subtitleSearch;
                this.$$this$launch = coroutineScope;
                this.$hasSelectASubtitle = booleanRef;
                this.this$0 = generatorPlayer;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C01721 c01721 = new C01721(this.$query, this.$$this$launch, this.$hasSelectASubtitle, this.this$0, continuation);
                c01721.L$0 = obj;
                return c01721;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(SubtitleRepo subtitleRepo, Continuation<? super Unit> continuation) {
                return ((C01721) create(subtitleRepo, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:27:0x00a7 A[PHI: r7 r8
              0x00a7: PHI (r7v7 java.util.Iterator) = 
              (r7v6 java.util.Iterator)
              (r7v8 java.util.Iterator)
              (r7v8 java.util.Iterator)
              (r7v8 java.util.Iterator)
              (r7v8 java.util.Iterator)
             binds: [B:26:0x009a, B:41:0x00e4, B:51:0x0143, B:56:0x0162, B:58:0x0168] A[DONT_GENERATE, DONT_INLINE]
              0x00a7: PHI (r8v3 java.util.List) = (r8v2 java.util.List), (r8v4 java.util.List), (r8v4 java.util.List), (r8v4 java.util.List), (r8v4 java.util.List) binds: [B:26:0x009a, B:41:0x00e4, B:51:0x0143, B:56:0x0162, B:58:0x0168] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:29:0x00ad  */
            /* JADX WARN: Code duplicated, block: B:31:0x00b9  */
            /* JADX WARN: Code restructure failed: missing block: B:11:0x005a, code lost:
            
                if (r7 == r2) goto L36;
             */
            /* JADX WARN: Code restructure failed: missing block: B:35:0x00d8, code lost:
            
                if (r9 == r2) goto L36;
             */
            /* JADX WARN: Code restructure failed: missing block: B:36:0x00da, code lost:
            
                return r2;
             */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00d8 -> B:37:0x00db). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r22) {
                /*
                    Method dump skipped, instruction units count: 387
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.player.GeneratorPlayer.AnonymousClass1.C01721.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            static final Unit invokeSuspend$lambda$1(GeneratorPlayer generatorPlayer, List list) {
                SubtitleData[] subtitleDataArr = (SubtitleData[]) list.toArray(new SubtitleData[0]);
                generatorPlayer.addAndSelectSubtitles((SubtitleData[]) Arrays.copyOf(subtitleDataArr, subtitleDataArr.length));
                return Unit.INSTANCE;
            }
        }
    }

    private final Job addFirstSub(AbstractSubtitleEntities.SubtitleSearch query) {
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        return BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(playerGeneratorViewModel), null, null, new AnonymousClass1(query, this, null), 3, null);
    }

    /* JADX WARN: Type inference failed for: r4v10, types: [T, java.util.List] */
    @Override // com.lagradost.cloudstream3.ui.player.FullScreenPlayer
    public void showMirrorsDialogue() {
        final boolean z;
        final Ref.BooleanRef booleanRef;
        Map map;
        int i;
        try {
            this.currentSelectedSubtitles = getPlayer().getCurrentPreferredSubtitle();
            final Context context = getContext();
            if (context != null) {
                final boolean isPlaying = getPlayer().getIsPlaying();
                getPlayer().handleEvent(CSPlayerEvent.Pause, PlayerEventSource.UI);
                AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
                PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
                if (playerGeneratorViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    playerGeneratorViewModel = null;
                }
                List<SubtitleData> listSortSubs = appContextUtils.sortSubs(playerGeneratorViewModel.getState().getSubtitles());
                final Dialog dialog = new Dialog(context, R.style.DialogFullscreenPlayer);
                final PlayerSelectSourceAndSubsBinding playerSelectSourceAndSubsBindingInflate = PlayerSelectSourceAndSubsBinding.inflate(LayoutInflater.from(context), null, false);
                Intrinsics.checkNotNullExpressionValue(playerSelectSourceAndSubsBindingInflate, "inflate(...)");
                dialog.setContentView(playerSelectSourceAndSubsBindingInflate.getRoot());
                UIHelper uIHelper = UIHelper.INSTANCE;
                LinearLayout root = playerSelectSourceAndSubsBindingInflate.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                UIHelper.fixSystemBarsPadding$default(uIHelper, root, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
                setSelectSourceDialog(dialog);
                dialog.show();
                final ListView sortProviders = playerSelectSourceAndSubsBindingInflate.sortProviders;
                Intrinsics.checkNotNullExpressionValue(sortProviders, "sortProviders");
                final ListView sortSubtitles = playerSelectSourceAndSubsBindingInflate.sortSubtitles;
                Intrinsics.checkNotNullExpressionValue(sortSubtitles, "sortSubtitles");
                final ListView sortSubtitlesOptions = playerSelectSourceAndSubsBindingInflate.sortSubtitlesOptions;
                Intrinsics.checkNotNullExpressionValue(sortSubtitlesOptions, "sortSubtitlesOptions");
                View viewInflate = getLayoutInflater().inflate(R.layout.sort_bottom_footer_add_choice, (ViewGroup) null);
                Intrinsics.checkNotNull(viewInflate, "null cannot be cast to non-null type android.widget.TextView");
                TextView textView = (TextView) viewInflate;
                textView.setText(context.getString(R.string.player_load_subtitles));
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.openSubPicker();
                    }
                });
                sortSubtitles.addFooterView(textView);
                final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                booleanRef2.element = true;
                playerSelectSourceAndSubsBindingInflate.subtitleSettingsBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda5
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        GeneratorPlayer.showMirrorsDialogue$lambda$0$1(this.f$0, view);
                    }
                });
                if (INSTANCE.getSubsProvidersIsActive()) {
                    PlayerGeneratorViewModel playerGeneratorViewModel2 = this.viewModel;
                    if (playerGeneratorViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        playerGeneratorViewModel2 = null;
                    }
                    GeneratorState generatorState = playerGeneratorViewModel2.getState().getGeneratorState();
                    final LoadResponse response = generatorState != null ? generatorState.getResponse() : null;
                    View viewInflate2 = getLayoutInflater().inflate(R.layout.sort_bottom_footer_add_choice, (ViewGroup) null);
                    Intrinsics.checkNotNull(viewInflate2, "null cannot be cast to non-null type android.widget.TextView");
                    TextView textView2 = (TextView) viewInflate2;
                    textView2.setText(context.getString(R.string.player_load_subtitles_online));
                    textView2.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda6
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            GeneratorPlayer.showMirrorsDialogue$lambda$0$2(booleanRef2, dialog, this, response, isPlaying, view);
                        }
                    });
                    sortSubtitles.addFooterView(textView2);
                    final TempMetaData metaData = getMetaData();
                    final String name = metaData.getName();
                    if (name == null) {
                        name = response != null ? response.getName() : null;
                    }
                    if (name != null) {
                        final String autoSelectLanguageTagIETF = SubtitlesFragment.INSTANCE.getAutoSelectLanguageTagIETF();
                        View viewInflate3 = getLayoutInflater().inflate(R.layout.sort_bottom_footer_add_choice, (ViewGroup) null);
                        Intrinsics.checkNotNull(viewInflate3, "null cannot be cast to non-null type android.widget.TextView");
                        TextView textView3 = (TextView) viewInflate3;
                        textView3.setText(context.getString(R.string.player_load_one_subtitle_online));
                        booleanRef = booleanRef2;
                        z = isPlaying;
                        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda7
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                GeneratorPlayer.showMirrorsDialogue$lambda$0$3(dialog, this, name, response, metaData, autoSelectLanguageTagIETF, view);
                            }
                        };
                        dialog = dialog;
                        textView3.setOnClickListener(onClickListener);
                        sortSubtitles.addFooterView(textView3);
                    } else {
                        z = isPlaying;
                        booleanRef = booleanRef2;
                    }
                } else {
                    z = isPlaying;
                    booleanRef = booleanRef2;
                }
                final Ref.IntRef intRef = new Ref.IntRef();
                Ref.IntRef intRef2 = new Ref.IntRef();
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = CollectionsKt.emptyList();
                showMirrorsDialogue$lambda$0$refreshLinks(objectRef, this, dialog, intRef2, intRef, context, sortProviders, getCurrentQualityProfile());
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda8
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        GeneratorPlayer.showMirrorsDialogue$lambda$0$7(booleanRef, this, z, dialogInterface);
                    }
                });
                ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.sort_bottom_single_choice);
                arrayAdapter.add(AppContextUtils.INSTANCE.html(context.getString(R.string.no_subtitles)));
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Object obj : listSortSubs) {
                    Ref.ObjectRef objectRef2 = objectRef;
                    String originalName = ((SubtitleData) obj).getOriginalName();
                    Object obj2 = linkedHashMap.get(originalName);
                    if (obj2 == null) {
                        ArrayList arrayList = new ArrayList();
                        linkedHashMap.put(originalName, arrayList);
                        obj2 = arrayList;
                    }
                    ((List) obj2).add(obj);
                    objectRef = objectRef2;
                    intRef2 = intRef2;
                }
                final Ref.ObjectRef objectRef3 = objectRef;
                final Ref.IntRef intRef3 = intRef2;
                ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    arrayList2.add(TuplesKt.to((String) entry.getKey(), CollectionsKt.sortedWith((List) entry.getValue(), new Comparator() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$showMirrorsDialogue$lambda$0$9$$inlined$sortedBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            Integer intOrNull = StringsKt.toIntOrNull(((SubtitleData) t).getNameSuffix());
                            Integer numValueOf = Integer.valueOf(intOrNull != null ? intOrNull.intValue() : 0);
                            Integer intOrNull2 = StringsKt.toIntOrNull(((SubtitleData) t2).getNameSuffix());
                            return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(intOrNull2 != null ? intOrNull2.intValue() : 0));
                        }
                    })));
                }
                Map map2 = MapsKt.toMap(arrayList2);
                final List list = CollectionsKt.toList(map2.entrySet());
                ArrayList arrayList3 = new ArrayList(map2.size());
                Iterator it = map2.entrySet().iterator();
                while (it.hasNext()) {
                    arrayList3.add(AppContextUtils.INSTANCE.html((String) ((Map.Entry) it.next()).getKey()));
                }
                ArrayList arrayList4 = arrayList3;
                Set setKeySet = map2.keySet();
                SubtitleData subtitleData = this.currentSelectedSubtitles;
                final int iIndexOf = CollectionsKt.indexOf(setKeySet, subtitleData != null ? subtitleData.getOriginalName() : null) + 1;
                final Ref.IntRef intRef4 = new Ref.IntRef();
                intRef4.element = iIndexOf;
                SubtitleData subtitleData2 = this.currentSelectedSubtitles;
                List list2 = (List) map2.get(subtitleData2 != null ? subtitleData2.getOriginalName() : null);
                final Ref.BooleanRef booleanRef3 = booleanRef;
                if (list2 != null) {
                    Iterator it2 = list2.iterator();
                    int i2 = 0;
                    while (true) {
                        if (!it2.hasNext()) {
                            map = map2;
                            i2 = -1;
                            break;
                        }
                        String nameSuffix = ((SubtitleData) it2.next()).getNameSuffix();
                        map = map2;
                        SubtitleData subtitleData3 = this.currentSelectedSubtitles;
                        if (Intrinsics.areEqual(nameSuffix, subtitleData3 != null ? subtitleData3.getNameSuffix() : null)) {
                            break;
                        }
                        i2++;
                        map2 = map;
                    }
                    i = i2;
                } else {
                    map = map2;
                    i = 0;
                }
                final Ref.IntRef intRef5 = new Ref.IntRef();
                intRef5.element = i;
                arrayAdapter.addAll(arrayList4);
                sortSubtitles.setAdapter((ListAdapter) arrayAdapter);
                sortSubtitles.setChoiceMode(1);
                sortSubtitles.setSelection(intRef4.element);
                sortSubtitles.setItemChecked(intRef4.element, true);
                final ArrayAdapter arrayAdapter2 = new ArrayAdapter(context, R.layout.sort_bottom_single_choice);
                sortSubtitlesOptions.setAdapter((ListAdapter) arrayAdapter2);
                sortSubtitlesOptions.setChoiceMode(1);
                final boolean z2 = z;
                showMirrorsDialogue$lambda$0$updateSubtitleOptionList(arrayAdapter2, list, intRef4, sortSubtitlesOptions, intRef5, context);
                final int i3 = i;
                final Map map3 = map;
                sortSubtitles.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda9
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public final void onItemClick(AdapterView adapterView, View view, int i4, long j) {
                        GeneratorPlayer.showMirrorsDialogue$lambda$0$13(map3, sortSubtitles, intRef4, intRef5, iIndexOf, i3, arrayAdapter2, list, sortSubtitlesOptions, context, adapterView, view, i4, j);
                    }
                });
                sortSubtitlesOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda10
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public final void onItemClick(AdapterView adapterView, View view, int i4, long j) {
                        GeneratorPlayer.showMirrorsDialogue$lambda$0$14(list, intRef4, sortSubtitlesOptions, sortSubtitles, intRef5, adapterView, view, i4, j);
                    }
                });
                playerSelectSourceAndSubsBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda12
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        GeneratorPlayer.showMirrorsDialogue$lambda$0$15(dialog, this, view);
                    }
                });
                showMirrorsDialogue$lambda$0$setProfileName(playerSelectSourceAndSubsBindingInflate, getCurrentQualityProfile());
                final Dialog dialog2 = dialog;
                playerSelectSourceAndSubsBindingInflate.profilesClickSettings.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda13
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        GeneratorPlayer.showMirrorsDialogue$lambda$0$16(this.f$0, playerSelectSourceAndSubsBindingInflate, objectRef3, dialog2, intRef3, intRef, context, sortProviders, view);
                    }
                });
                TextView textView4 = playerSelectSourceAndSubsBindingInflate.subtitlesEncodingFormat;
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                String[] stringArray = context.getResources().getStringArray(R.array.subtitles_encoding_list);
                Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
                String[] stringArray2 = context.getResources().getStringArray(R.array.subtitles_encoding_values);
                Intrinsics.checkNotNullExpressionValue(stringArray2, "getStringArray(...)");
                int iIndexOf2 = ArraysKt.indexOf(stringArray2, defaultSharedPreferences.getString(context.getString(R.string.subtitles_encoding_key), null));
                if (iIndexOf2 == -1) {
                    iIndexOf2 = 0;
                }
                textView4.setText(stringArray[iIndexOf2]);
                playerSelectSourceAndSubsBindingInflate.subtitlesEncodingFormat.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda14
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        GeneratorPlayer.showMirrorsDialogue$lambda$0$18(context, booleanRef3, dialog2, this, z2, view);
                    }
                });
                playerSelectSourceAndSubsBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        GeneratorPlayer.showMirrorsDialogue$lambda$0$19(intRef, intRef3, intRef4, iIndexOf, intRef5, i3, this, list, objectRef3, dialog2, view);
                    }
                });
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$1(final GeneratorPlayer generatorPlayer, View view) {
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratorPlayer.showMirrorsDialogue$lambda$0$1$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showMirrorsDialogue$lambda$0$1$0(GeneratorPlayer generatorPlayer) {
        SubtitlesFragment subtitlesFragment = new SubtitlesFragment();
        subtitlesFragment.setSystemBarsAddPadding(true);
        subtitlesFragment.show(generatorPlayer.getParentFragmentManager(), "SubtitleSettings");
        return Unit.INSTANCE;
    }

    private static final void showMirrorsDialogue$lambda$0$dismiss(boolean z, GeneratorPlayer generatorPlayer) {
        if (z) {
            IPlayer.CC.handleEvent$default(generatorPlayer.getPlayer(), CSPlayerEvent.Play, null, 2, null);
        }
        FragmentActivity activity = generatorPlayer.getActivity();
        if (activity != null) {
            UIHelper.INSTANCE.hideSystemUI(activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$2(Ref.BooleanRef booleanRef, Dialog dialog, final GeneratorPlayer generatorPlayer, LoadResponse loadResponse, final boolean z, View view) {
        booleanRef.element = false;
        UIHelper.INSTANCE.dismissSafe(dialog, generatorPlayer.getActivity());
        generatorPlayer.setSelectSourceDialog(null);
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        generatorPlayer.openOnlineSubPicker(context, loadResponse, new Function0() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda48
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratorPlayer.showMirrorsDialogue$lambda$0$2$0(z, generatorPlayer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showMirrorsDialogue$lambda$0$2$0(boolean z, GeneratorPlayer generatorPlayer) {
        showMirrorsDialogue$lambda$0$dismiss(z, generatorPlayer);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$3(Dialog dialog, GeneratorPlayer generatorPlayer, String str, LoadResponse loadResponse, TempMetaData tempMetaData, String str2, View view) {
        String aniListId;
        String malId;
        String tMDbId;
        UIHelper.INSTANCE.dismissSafe(dialog, generatorPlayer.getActivity());
        PlayerGeneratorViewModel playerGeneratorViewModel = null;
        generatorPlayer.setSelectSourceDialog(null);
        CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.loading, (Integer) null, 2, (Object) null);
        String imdbId = loadResponse != null ? LoadResponse.INSTANCE.getImdbId(loadResponse) : null;
        Integer numValueOf = (loadResponse == null || (tMDbId = LoadResponse.INSTANCE.getTMDbId(loadResponse)) == null) ? null : Integer.valueOf(Integer.parseInt(tMDbId));
        Integer numValueOf2 = (loadResponse == null || (malId = LoadResponse.INSTANCE.getMalId(loadResponse)) == null) ? null : Integer.valueOf(Integer.parseInt(malId));
        Integer numValueOf3 = (loadResponse == null || (aniListId = LoadResponse.INSTANCE.getAniListId(loadResponse)) == null) ? null : Integer.valueOf(Integer.parseInt(aniListId));
        Integer episode = tempMetaData.getEpisode();
        Integer season = tempMetaData.getSeason();
        String str3 = str2;
        if (StringsKt.isBlank(str3)) {
            str3 = null;
        }
        String str4 = str3;
        PlayerGeneratorViewModel playerGeneratorViewModel2 = generatorPlayer.viewModel;
        if (playerGeneratorViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            playerGeneratorViewModel = playerGeneratorViewModel2;
        }
        generatorPlayer.addFirstSub(new AbstractSubtitleEntities.SubtitleSearch(str, str4, imdbId, numValueOf, numValueOf2, numValueOf3, episode, season, playerGeneratorViewModel.getCurrentSubtitleYear().getValue()));
    }

    /* JADX WARN: Type inference failed for: r9v1, types: [T, java.util.List] */
    private static final void showMirrorsDialogue$lambda$0$refreshLinks(final Ref.ObjectRef<List<Pair<ExtractorLink, ExtractorUri>>> objectRef, GeneratorPlayer generatorPlayer, Dialog dialog, Ref.IntRef intRef, final Ref.IntRef intRef2, Context context, final ListView listView, int i) {
        String name;
        PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        objectRef.element = playerGeneratorViewModel.getState().sortLinks(i);
        if (objectRef.element.isEmpty()) {
            LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.sort_sources_holder);
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
                return;
            }
            return;
        }
        intRef.element = CollectionsKt.indexOf((List<? extends Pair<? extends ExtractorLink, ExtractorUri>>) objectRef.element, generatorPlayer.currentSelectedLink);
        intRef2.element = intRef.element;
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.sort_bottom_single_choice);
        List<Pair<ExtractorLink, ExtractorUri>> list = objectRef.element;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            ExtractorLink extractorLink = (ExtractorLink) pair.component1();
            ExtractorUri extractorUri = (ExtractorUri) pair.component2();
            if (extractorLink == null || (name = extractorLink.getName()) == null) {
                name = extractorUri != null ? extractorUri.getName() : "NULL";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(' ');
            sb.append(Qualities.INSTANCE.getStringByInt(extractorLink != null ? Integer.valueOf(extractorLink.getQuality()) : null));
            arrayList.add(sb.toString());
        }
        arrayAdapter.addAll(arrayList);
        listView.setChoiceMode(1);
        listView.setAdapter((ListAdapter) arrayAdapter);
        listView.setSelection(intRef2.element);
        listView.setItemChecked(intRef2.element, true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda43
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i2, long j) {
                GeneratorPlayer.showMirrorsDialogue$lambda$0$refreshLinks$5(intRef2, listView, adapterView, view, i2, j);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda45
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public final boolean onItemLongClick(AdapterView adapterView, View view, int i2, long j) {
                return GeneratorPlayer.showMirrorsDialogue$lambda$0$refreshLinks$6(objectRef, adapterView, view, i2, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$refreshLinks$5(Ref.IntRef intRef, ListView listView, AdapterView adapterView, View view, int i, long j) {
        intRef.element = i;
        listView.setItemChecked(i, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean showMirrorsDialogue$lambda$0$refreshLinks$6(Ref.ObjectRef objectRef, AdapterView adapterView, View view, int i, long j) {
        ExtractorLink extractorLink;
        String url;
        Pair pair = (Pair) CollectionsKt.getOrNull((List) objectRef.element, i);
        if (pair == null || (extractorLink = (ExtractorLink) pair.getFirst()) == null || (url = extractorLink.getUrl()) == null) {
            return true;
        }
        UIHelper.INSTANCE.clipboardHelper(TextUtilKt.txt(R.string.video_source, new Object[0]), url);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$7(Ref.BooleanRef booleanRef, GeneratorPlayer generatorPlayer, boolean z, DialogInterface dialogInterface) {
        if (booleanRef.element) {
            showMirrorsDialogue$lambda$0$dismiss(z, generatorPlayer);
        }
        generatorPlayer.setSelectSourceDialog(null);
    }

    private static final void showMirrorsDialogue$lambda$0$updateSubtitleOptionList(ArrayAdapter<Spanned> arrayAdapter, List<? extends Map.Entry<String, ? extends List<SubtitleData>>> list, Ref.IntRef intRef, ListView listView, Ref.IntRef intRef2, Context context) {
        ArrayList arrayListEmptyList;
        List list2;
        UiText uiTextTxt;
        arrayAdapter.clear();
        Map.Entry entry = (Map.Entry) CollectionsKt.getOrNull(list, intRef.element - 1);
        if (entry != null && (list2 = (List) entry.getValue()) != null) {
            List<SubtitleData> list3 = list2;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            for (SubtitleData subtitleData : list3) {
                SpannedString spannedStringHtml = AppContextUtils.INSTANCE.html(subtitleData.getNameSuffix());
                if (StringsKt.isBlank(spannedStringHtml)) {
                    int i = WhenMappings.$EnumSwitchMapping$0[subtitleData.getOrigin().ordinal()];
                    if (i == 1) {
                        uiTextTxt = TextUtilKt.txt(R.string.subtitles_from_online, new Object[0]);
                    } else if (i == 2) {
                        uiTextTxt = TextUtilKt.txt(R.string.downloaded, new Object[0]);
                    } else {
                        if (i != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        uiTextTxt = TextUtilKt.txt(R.string.subtitles_from_embedded, new Object[0]);
                    }
                    spannedStringHtml = SpannedString.valueOf(uiTextTxt.asString(context));
                }
                arrayList.add(spannedStringHtml);
            }
            arrayListEmptyList = arrayList;
        } else {
            arrayListEmptyList = CollectionsKt.emptyList();
        }
        boolean z = arrayListEmptyList.size() < 2;
        listView.setVisibility(z ? 8 : 0);
        if (z) {
            return;
        }
        arrayAdapter.addAll(arrayListEmptyList);
        listView.setSelection(intRef2.element);
        listView.setItemChecked(intRef2.element, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$13(Map map, ListView listView, Ref.IntRef intRef, Ref.IntRef intRef2, int i, int i2, ArrayAdapter arrayAdapter, List list, ListView listView2, Context context, AdapterView adapterView, View view, int i3, long j) {
        if (i3 > map.size()) {
            View view2 = listView.getAdapter().getView(i3, null, listView);
            if (view2 != null) {
                view2.performClick();
                return;
            }
            return;
        }
        if (intRef.element != i3) {
            intRef.element = i3;
            if (intRef.element != i) {
                i2 = 0;
            }
            intRef2.element = i2;
        }
        listView.setItemChecked(i3, true);
        showMirrorsDialogue$lambda$0$updateSubtitleOptionList(arrayAdapter, list, intRef, listView2, intRef2, context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$14(List list, Ref.IntRef intRef, ListView listView, ListView listView2, Ref.IntRef intRef2, AdapterView adapterView, View view, int i, long j) {
        List list2;
        Map.Entry entry = (Map.Entry) CollectionsKt.getOrNull(list, intRef.element - 1);
        if (i >= ((entry == null || (list2 = (List) entry.getValue()) == null) ? -1 : list2.size())) {
            View view2 = listView.getAdapter().getView(i, null, listView2);
            if (view2 != null) {
                view2.performClick();
                return;
            }
            return;
        }
        intRef2.element = i;
        listView.setItemChecked(i, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$15(Dialog dialog, GeneratorPlayer generatorPlayer, View view) {
        UIHelper.INSTANCE.dismissSafe(dialog, generatorPlayer.getActivity());
        generatorPlayer.setSelectSourceDialog(null);
    }

    private static final void showMirrorsDialogue$lambda$0$setProfileName(PlayerSelectSourceAndSubsBinding playerSelectSourceAndSubsBinding, int i) {
        TextUtilKt.setText(playerSelectSourceAndSubsBinding.sourceSettingsBtt, QualityDataHelper.INSTANCE.getProfileName(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$16(final GeneratorPlayer generatorPlayer, final PlayerSelectSourceAndSubsBinding playerSelectSourceAndSubsBinding, final Ref.ObjectRef objectRef, final Dialog dialog, final Ref.IntRef intRef, final Ref.IntRef intRef2, final Context context, final ListView listView, View view) {
        FragmentActivity activity = generatorPlayer.getActivity();
        if (activity == null) {
            return;
        }
        int i = R.style.DialogFullscreenPlayer;
        PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        PersistentSet<Pair<ExtractorLink, ExtractorUri>> links = playerGeneratorViewModel.getState().getLinks();
        ArrayList arrayList = new ArrayList();
        Iterator<Pair<ExtractorLink, ExtractorUri>> it = links.iterator();
        while (it.hasNext()) {
            ExtractorLink first = it.next().getFirst();
            LinkSource linkSource = first != null ? new LinkSource(first) : null;
            if (linkSource != null) {
                arrayList.add(linkSource);
            }
        }
        QualityProfileDialog qualityProfileDialog = new QualityProfileDialog(activity, i, arrayList, generatorPlayer.getCurrentQualityProfile(), new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GeneratorPlayer.showMirrorsDialogue$lambda$0$16$1(this.f$0, playerSelectSourceAndSubsBinding, (QualityDataHelper.QualityProfile) obj);
            }
        });
        qualityProfileDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda27
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                GeneratorPlayer.showMirrorsDialogue$lambda$0$16$2(this.f$0, objectRef, dialog, intRef, intRef2, context, listView, dialogInterface);
            }
        });
        qualityProfileDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showMirrorsDialogue$lambda$0$16$1(GeneratorPlayer generatorPlayer, PlayerSelectSourceAndSubsBinding playerSelectSourceAndSubsBinding, QualityDataHelper.QualityProfile profile) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        generatorPlayer.setCurrentQualityProfile(profile.getId());
        showMirrorsDialogue$lambda$0$setProfileName(playerSelectSourceAndSubsBinding, profile.getId());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$16$2(GeneratorPlayer generatorPlayer, Ref.ObjectRef objectRef, Dialog dialog, Ref.IntRef intRef, Ref.IntRef intRef2, Context context, ListView listView, DialogInterface dialogInterface) {
        PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        playerGeneratorViewModel.getState().clearSortedLinksCache();
        showMirrorsDialogue$lambda$0$refreshLinks(objectRef, generatorPlayer, dialog, intRef, intRef2, context, listView, generatorPlayer.getCurrentQualityProfile());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$18(final Context context, Ref.BooleanRef booleanRef, Dialog dialog, final GeneratorPlayer generatorPlayer, final boolean z, View view) {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String[] stringArray = context.getResources().getStringArray(R.array.subtitles_encoding_list);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        final String[] stringArray2 = context.getResources().getStringArray(R.array.subtitles_encoding_values);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "getStringArray(...)");
        String string = defaultSharedPreferences.getString(context.getString(R.string.subtitles_encoding_key), null);
        booleanRef.element = false;
        UIHelper.INSTANCE.dismissSafe(dialog, generatorPlayer.getActivity());
        generatorPlayer.setSelectSourceDialog(null);
        int iIndexOf = ArraysKt.indexOf(stringArray2, string);
        FragmentActivity activity = generatorPlayer.getActivity();
        if (activity != null) {
            SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
            FragmentActivity fragmentActivity = activity;
            List<String> list = ArraysKt.toList(stringArray);
            int i = iIndexOf == -1 ? 0 : iIndexOf;
            String string2 = context.getString(R.string.subtitles_encoding);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            singleSelectionHelper.showDialog(fragmentActivity, list, i, string2, true, new Function0() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda46
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }, new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda47
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratorPlayer.showMirrorsDialogue$lambda$0$18$1(defaultSharedPreferences, context, generatorPlayer, stringArray2, z, ((Integer) obj).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showMirrorsDialogue$lambda$0$18$1(SharedPreferences sharedPreferences, Context context, GeneratorPlayer generatorPlayer, String[] strArr, boolean z, int i) {
        Intrinsics.checkNotNull(sharedPreferences);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(context.getString(R.string.subtitles_encoding_key), strArr[i]);
        editorEdit.apply();
        CustomDecoder.INSTANCE.updateForcedEncoding(context);
        showMirrorsDialogue$lambda$0$dismiss(z, generatorPlayer);
        IPlayer.CC.seekTime$default(generatorPlayer.getPlayer(), -1L, null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMirrorsDialogue$lambda$0$19(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, int i, Ref.IntRef intRef4, int i2, GeneratorPlayer generatorPlayer, List list, Ref.ObjectRef objectRef, Dialog dialog, View view) {
        List list2;
        SubtitleData subtitleData;
        Pair<? extends ExtractorLink, ExtractorUri> pair;
        boolean subtitles = false;
        boolean z = intRef.element != intRef2.element;
        if (intRef3.element != i || intRef4.element != i2) {
            if (intRef3.element <= 0) {
                subtitles = generatorPlayer.noSubtitles();
            } else {
                Map.Entry entry = (Map.Entry) CollectionsKt.getOrNull(list, intRef3.element - 1);
                if (entry != null && (list2 = (List) entry.getValue()) != null && (subtitleData = (SubtitleData) CollectionsKt.getOrNull(list2, intRef4.element)) != null) {
                    subtitles = generatorPlayer.setSubtitles(subtitleData, true);
                }
            }
            z |= subtitles;
        }
        if (z && (pair = (Pair) CollectionsKt.getOrNull((List) objectRef.element, intRef.element)) != null) {
            generatorPlayer.loadLink(pair, true);
        }
        UIHelper.INSTANCE.dismissSafe(dialog, generatorPlayer.getActivity());
        generatorPlayer.setSelectSourceDialog(null);
    }

    /* JADX WARN: Code duplicated, block: B:106:0x02ba  */
    /* JADX WARN: Code duplicated, block: B:108:0x02db  */
    /* JADX WARN: Code duplicated, block: B:128:0x0332  */
    /* JADX WARN: Code duplicated, block: B:131:0x033f  */
    /* JADX WARN: Code duplicated, block: B:132:0x0340  */
    /* JADX WARN: Code duplicated, block: B:134:0x0343 A[Catch: Exception -> 0x03fd, TryCatch #0 {Exception -> 0x03fd, blocks: (B:3:0x0002, B:5:0x0008, B:12:0x00a7, B:19:0x00c2, B:20:0x00e1, B:22:0x00e8, B:24:0x00f0, B:25:0x00f3, B:27:0x00fb, B:32:0x0108, B:38:0x0116, B:40:0x0137, B:35:0x010f, B:39:0x0133, B:30:0x0102, B:41:0x013b, B:42:0x0146, B:46:0x0174, B:51:0x0181, B:64:0x01b3, B:65:0x01da, B:67:0x01e0, B:69:0x01f0, B:71:0x01f6, B:73:0x01fc, B:75:0x0206, B:77:0x020c, B:80:0x0214, B:81:0x0217, B:82:0x023a, B:84:0x0240, B:86:0x0248, B:87:0x024b, B:89:0x0253, B:91:0x025f, B:93:0x026b, B:100:0x02a7, B:103:0x02af, B:107:0x02bb, B:109:0x02dc, B:111:0x02ea, B:114:0x02f1, B:129:0x0336, B:134:0x0343, B:136:0x034c, B:138:0x036e, B:142:0x0379, B:147:0x0386, B:149:0x0393, B:118:0x02fd, B:121:0x0307, B:124:0x0311, B:127:0x031c, B:150:0x03c1, B:52:0x0186, B:53:0x018b, B:55:0x0191, B:57:0x019d, B:59:0x01a3, B:62:0x01af), top: B:155:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:136:0x034c A[Catch: Exception -> 0x03fd, TryCatch #0 {Exception -> 0x03fd, blocks: (B:3:0x0002, B:5:0x0008, B:12:0x00a7, B:19:0x00c2, B:20:0x00e1, B:22:0x00e8, B:24:0x00f0, B:25:0x00f3, B:27:0x00fb, B:32:0x0108, B:38:0x0116, B:40:0x0137, B:35:0x010f, B:39:0x0133, B:30:0x0102, B:41:0x013b, B:42:0x0146, B:46:0x0174, B:51:0x0181, B:64:0x01b3, B:65:0x01da, B:67:0x01e0, B:69:0x01f0, B:71:0x01f6, B:73:0x01fc, B:75:0x0206, B:77:0x020c, B:80:0x0214, B:81:0x0217, B:82:0x023a, B:84:0x0240, B:86:0x0248, B:87:0x024b, B:89:0x0253, B:91:0x025f, B:93:0x026b, B:100:0x02a7, B:103:0x02af, B:107:0x02bb, B:109:0x02dc, B:111:0x02ea, B:114:0x02f1, B:129:0x0336, B:134:0x0343, B:136:0x034c, B:138:0x036e, B:142:0x0379, B:147:0x0386, B:149:0x0393, B:118:0x02fd, B:121:0x0307, B:124:0x0311, B:127:0x031c, B:150:0x03c1, B:52:0x0186, B:53:0x018b, B:55:0x0191, B:57:0x019d, B:59:0x01a3, B:62:0x01af), top: B:155:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:137:0x036d  */
    /* JADX WARN: Code duplicated, block: B:140:0x0377  */
    /* JADX WARN: Code duplicated, block: B:141:0x0378  */
    /* JADX WARN: Code duplicated, block: B:144:0x0382  */
    /* JADX WARN: Code duplicated, block: B:145:0x0383  */
    /* JADX WARN: Code duplicated, block: B:147:0x0386 A[Catch: Exception -> 0x03fd, TryCatch #0 {Exception -> 0x03fd, blocks: (B:3:0x0002, B:5:0x0008, B:12:0x00a7, B:19:0x00c2, B:20:0x00e1, B:22:0x00e8, B:24:0x00f0, B:25:0x00f3, B:27:0x00fb, B:32:0x0108, B:38:0x0116, B:40:0x0137, B:35:0x010f, B:39:0x0133, B:30:0x0102, B:41:0x013b, B:42:0x0146, B:46:0x0174, B:51:0x0181, B:64:0x01b3, B:65:0x01da, B:67:0x01e0, B:69:0x01f0, B:71:0x01f6, B:73:0x01fc, B:75:0x0206, B:77:0x020c, B:80:0x0214, B:81:0x0217, B:82:0x023a, B:84:0x0240, B:86:0x0248, B:87:0x024b, B:89:0x0253, B:91:0x025f, B:93:0x026b, B:100:0x02a7, B:103:0x02af, B:107:0x02bb, B:109:0x02dc, B:111:0x02ea, B:114:0x02f1, B:129:0x0336, B:134:0x0343, B:136:0x034c, B:138:0x036e, B:142:0x0379, B:147:0x0386, B:149:0x0393, B:118:0x02fd, B:121:0x0307, B:124:0x0311, B:127:0x031c, B:150:0x03c1, B:52:0x0186, B:53:0x018b, B:55:0x0191, B:57:0x019d, B:59:0x01a3, B:62:0x01af), top: B:155:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:148:0x0392  */
    /* JADX WARN: Instruction removed from duplicated block: B:136:0x034c, please report this as an issue */
    @Override // com.lagradost.cloudstream3.ui.player.FullScreenPlayer
    public void showTracksDialogue() {
        int i;
        int iIntValue;
        Iterator it;
        List<AudioTrack> list;
        String label;
        String str;
        String strAudioCodecName;
        Integer channelCount;
        String str2;
        String upperCase;
        String string;
        Object objValueOf;
        try {
            Context context = getContext();
            if (context != null) {
                CurrentTracks videoTracks = getPlayer().getVideoTracks();
                final boolean isPlaying = getPlayer().getIsPlaying();
                IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.Pause, null, 2, null);
                final List listSortedWith = CollectionsKt.sortedWith(videoTracks.getAllVideoTracks(), new Comparator() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$showTracksDialogue$lambda$0$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        Integer height = ((VideoTrack) t).getHeight();
                        Integer numValueOf = height != null ? Integer.valueOf(height.intValue() * (-1)) : null;
                        Integer height2 = ((VideoTrack) t2).getHeight();
                        return ComparisonsKt.compareValues(numValueOf, height2 != null ? Integer.valueOf(height2.intValue() * (-1)) : null);
                    }
                });
                List<AudioTrack> allAudioTracks = videoTracks.getAllAudioTracks();
                PlayerSelectTracksBinding playerSelectTracksBindingInflate = PlayerSelectTracksBinding.inflate(LayoutInflater.from(context), null, false);
                Intrinsics.checkNotNullExpressionValue(playerSelectTracksBindingInflate, "inflate(...)");
                final Dialog dialog = new Dialog(context, R.style.DialogFullscreenPlayer);
                setSelectTrackDialog(dialog);
                dialog.setContentView(playerSelectTracksBindingInflate.getRoot());
                dialog.show();
                UIHelper uIHelper = UIHelper.INSTANCE;
                LinearLayout root = playerSelectTracksBindingInflate.getRoot();
                Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
                UIHelper.fixSystemBarsPadding$default(uIHelper, root, null, null, false, false, false, false, false, false, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
                final ListView videoTracksList = playerSelectTracksBindingInflate.videoTracksList;
                Intrinsics.checkNotNullExpressionValue(videoTracksList, "videoTracksList");
                final ListView autoTracksList = playerSelectTracksBindingInflate.autoTracksList;
                Intrinsics.checkNotNullExpressionValue(autoTracksList, "autoTracksList");
                LinearLayout videoTracksHolder = playerSelectTracksBindingInflate.videoTracksHolder;
                Intrinsics.checkNotNullExpressionValue(videoTracksHolder, "videoTracksHolder");
                videoTracksHolder.setVisibility(listSortedWith.size() > 1 ? 0 : 8);
                LinearLayout audioTracksHolder = playerSelectTracksBindingInflate.audioTracksHolder;
                Intrinsics.checkNotNullExpressionValue(audioTracksHolder, "audioTracksHolder");
                audioTracksHolder.setVisibility(allAudioTracks.size() > 1 ? 0 : 8);
                ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.sort_bottom_single_choice);
                List list2 = listSortedWith;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator it2 = list2.iterator();
                int i2 = 0;
                while (true) {
                    i = -1;
                    if (!it2.hasNext()) {
                        break;
                    }
                    Object next = it2.next();
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    VideoTrack videoTrack = (VideoTrack) next;
                    String label2 = videoTrack.getLabel();
                    if (label2 == null) {
                        Integer height = videoTrack.getHeight();
                        if (height != null && height.intValue() == -1) {
                            objValueOf = Integer.valueOf(i2);
                        } else {
                            Integer width = videoTrack.getWidth();
                            if (width != null && width.intValue() == -1) {
                                objValueOf = Integer.valueOf(i2);
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append(videoTrack.getWidth());
                                sb.append('x');
                                sb.append(videoTrack.getHeight());
                                objValueOf = sb.toString();
                            }
                        }
                        label2 = objValueOf.toString();
                    }
                    arrayList.add(label2);
                    i2 = i3;
                }
                arrayAdapter.addAll(arrayList);
                videoTracksList.setChoiceMode(1);
                videoTracksList.setAdapter((ListAdapter) arrayAdapter);
                final Ref.IntRef intRef = new Ref.IntRef();
                Integer numValueOf = Integer.valueOf(CollectionsKt.indexOf((List<? extends VideoTrack>) listSortedWith, videoTracks.getCurrentVideoTrack()));
                boolean z = numValueOf.intValue() != -1;
                Boolean.valueOf(z).getClass();
                if (!z) {
                    numValueOf = null;
                }
                if (numValueOf == null) {
                    Iterator it3 = listSortedWith.iterator();
                    int i4 = 0;
                    while (true) {
                        if (!it3.hasNext()) {
                            iIntValue = -1;
                            break;
                        }
                        VideoTrack videoTrack2 = (VideoTrack) it3.next();
                        VideoTrack currentVideoTrack = videoTracks.getCurrentVideoTrack();
                        if (Intrinsics.areEqual(currentVideoTrack != null ? currentVideoTrack.getId() : null, videoTrack2.getId())) {
                            iIntValue = i4;
                            break;
                        }
                        i4++;
                    }
                } else {
                    iIntValue = numValueOf.intValue();
                }
                intRef.element = iIntValue;
                videoTracksList.setSelection(intRef.element);
                videoTracksList.setItemChecked(intRef.element, true);
                videoTracksList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda28
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public final void onItemClick(AdapterView adapterView, View view, int i5, long j) {
                        GeneratorPlayer.showTracksDialogue$lambda$0$4(intRef, videoTracksList, adapterView, view, i5, j);
                    }
                });
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda29
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        GeneratorPlayer.showTracksDialogue$lambda$0$dismiss(isPlaying, this);
                    }
                });
                final Ref.IntRef intRef2 = new Ref.IntRef();
                int i5 = 0;
                for (AudioTrack audioTrack : allAudioTracks) {
                    String id = audioTrack.getId();
                    AudioTrack currentAudioTrack = videoTracks.getCurrentAudioTrack();
                    if (Intrinsics.areEqual(id, currentAudioTrack != null ? currentAudioTrack.getId() : null)) {
                        Integer formatIndex = audioTrack.getFormatIndex();
                        AudioTrack currentAudioTrack2 = videoTracks.getCurrentAudioTrack();
                        if (Intrinsics.areEqual(formatIndex, currentAudioTrack2 != null ? currentAudioTrack2.getFormatIndex() : null)) {
                            i = i5;
                            break;
                        }
                    }
                    i5++;
                }
                intRef2.element = RangesKt.coerceAtLeast(i, 0);
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(context, R.layout.sort_bottom_single_choice);
                List<AudioTrack> list3 = allAudioTracks;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                Iterator it4 = list3.iterator();
                int i6 = 0;
                while (it4.hasNext()) {
                    Object next2 = it4.next();
                    int i7 = i6 + 1;
                    if (i6 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    AudioTrack audioTrack2 = (AudioTrack) next2;
                    String language = audioTrack2.getLanguage();
                    if (language == null || (string = StringsKt.trim((CharSequence) language).toString()) == null) {
                        it = it4;
                        list = allAudioTracks;
                    } else {
                        it = it4;
                        String strFromTagToLanguageName$default = SubtitleHelper.fromTagToLanguageName$default(SubtitleHelper.INSTANCE, string, null, 2, null);
                        if (strFromTagToLanguageName$default == null) {
                            SubtitleHelper subtitleHelper = SubtitleHelper.INSTANCE;
                            list = allAudioTracks;
                            String lowerCase = StringsKt.substringBefore$default(StringsKt.replace$default(string, '_', '-', false, 4, (Object) null), '-', (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                            label = SubtitleHelper.fromTagToLanguageName$default(subtitleHelper, lowerCase, null, 2, null);
                            if (label == null) {
                            }
                        } else {
                            list = allAudioTracks;
                            label = strFromTagToLanguageName$default;
                        }
                        if (label == null) {
                            label = string;
                        }
                        label = string;
                        if (label.length() > 0) {
                            char upperCase2 = Character.toUpperCase(label.charAt(0));
                            String strSubstring = label.substring(1);
                            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                            str = upperCase2 + strSubstring;
                        } else {
                            str = label;
                        }
                        strAudioCodecName = audioCodecName(audioTrack2.getSampleMimeType());
                        channelCount = audioTrack2.getChannelCount();
                        if (channelCount != null || channelCount.intValue() <= 0) {
                            str2 = "";
                        } else if (channelCount.intValue() == 1) {
                            str2 = "Mono";
                        } else if (channelCount.intValue() == 2) {
                            str2 = "Stereo";
                        } else if (channelCount.intValue() == 6) {
                            str2 = "5.1";
                        } else if (channelCount.intValue() == 8) {
                            str2 = "7.1";
                        } else {
                            str2 = channelCount.intValue() + "ch";
                        }
                        if (!StringsKt.isBlank(str)) {
                            str = null;
                        }
                        if (str != null) {
                            str = null;
                        } else if (str.length() > 0) {
                            char upperCase3 = Character.toUpperCase(str.charAt(0));
                            String strSubstring2 = str.substring(1);
                            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                            str = upperCase3 + strSubstring2;
                        }
                        if (!StringsKt.isBlank(str2)) {
                            str2 = null;
                        }
                        if (!StringsKt.isBlank(strAudioCodecName)) {
                            strAudioCodecName = null;
                        }
                        if (strAudioCodecName != null) {
                            upperCase = strAudioCodecName.toUpperCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                        } else {
                            upperCase = null;
                        }
                        arrayList2.add(CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull((Object[]) new String[]{str, str2, upperCase}), " • ", null, null, 0, null, null, 62, null));
                        i6 = i7;
                        allAudioTracks = list;
                        it4 = it;
                    }
                    label = audioTrack2.getLabel();
                    if (label == null) {
                        label = "Audio";
                    }
                    label = string;
                    if (label.length() > 0) {
                        char upperCase4 = Character.toUpperCase(label.charAt(0));
                        String strSubstring3 = label.substring(1);
                        Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                        str = upperCase4 + strSubstring3;
                    } else {
                        str = label;
                    }
                    strAudioCodecName = audioCodecName(audioTrack2.getSampleMimeType());
                    channelCount = audioTrack2.getChannelCount();
                    if (channelCount != null) {
                        str2 = "";
                    } else {
                        str2 = "";
                    }
                    if (!StringsKt.isBlank(str)) {
                        str = null;
                    }
                    if (str != null) {
                        str = null;
                    } else if (str.length() > 0) {
                        char upperCase5 = Character.toUpperCase(str.charAt(0));
                        String strSubstring4 = str.substring(1);
                        Intrinsics.checkNotNullExpressionValue(strSubstring4, "substring(...)");
                        str = upperCase5 + strSubstring4;
                    }
                    if (!StringsKt.isBlank(str2)) {
                        str2 = null;
                    }
                    if (!StringsKt.isBlank(strAudioCodecName)) {
                        strAudioCodecName = null;
                    }
                    if (strAudioCodecName != null) {
                        upperCase = strAudioCodecName.toUpperCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                    } else {
                        upperCase = null;
                    }
                    arrayList2.add(CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull((Object[]) new String[]{str, str2, upperCase}), " • ", null, null, 0, null, null, 62, null));
                    i6 = i7;
                    allAudioTracks = list;
                    it4 = it;
                }
                final List<AudioTrack> list4 = allAudioTracks;
                arrayAdapter2.addAll(arrayList2);
                autoTracksList.setAdapter((ListAdapter) arrayAdapter2);
                autoTracksList.setChoiceMode(1);
                autoTracksList.setSelection(intRef2.element);
                autoTracksList.setItemChecked(intRef2.element, true);
                autoTracksList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda30
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public final void onItemClick(AdapterView adapterView, View view, int i8, long j) {
                        GeneratorPlayer.showTracksDialogue$lambda$0$8(intRef2, autoTracksList, adapterView, view, i8, j);
                    }
                });
                playerSelectTracksBindingInflate.cancelBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda31
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        GeneratorPlayer.showTracksDialogue$lambda$0$9(dialog, this, view);
                    }
                });
                playerSelectTracksBindingInflate.applyBtt.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda32
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        GeneratorPlayer.showTracksDialogue$lambda$0$10(list4, intRef2, this, listSortedWith, intRef, dialog, view);
                    }
                });
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTracksDialogue$lambda$0$dismiss(boolean z, GeneratorPlayer generatorPlayer) {
        if (z) {
            IPlayer.CC.handleEvent$default(generatorPlayer.getPlayer(), CSPlayerEvent.Play, null, 2, null);
        }
        FragmentActivity activity = generatorPlayer.getActivity();
        if (activity != null) {
            UIHelper.INSTANCE.hideSystemUI(activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTracksDialogue$lambda$0$4(Ref.IntRef intRef, ListView listView, AdapterView adapterView, View view, int i, long j) {
        intRef.element = i;
        listView.setItemChecked(i, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTracksDialogue$lambda$0$8(Ref.IntRef intRef, ListView listView, AdapterView adapterView, View view, int i, long j) {
        intRef.element = i;
        listView.setItemChecked(i, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTracksDialogue$lambda$0$9(Dialog dialog, GeneratorPlayer generatorPlayer, View view) {
        UIHelper.INSTANCE.dismissSafe(dialog, generatorPlayer.getActivity());
        generatorPlayer.setSelectTrackDialog(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showTracksDialogue$lambda$0$10(List list, Ref.IntRef intRef, GeneratorPlayer generatorPlayer, List list2, Ref.IntRef intRef2, Dialog dialog, View view) {
        Integer height;
        Integer width;
        AudioTrack audioTrack = (AudioTrack) CollectionsKt.getOrNull(list, intRef.element);
        generatorPlayer.getPlayer().setPreferredAudioTrack(audioTrack != null ? audioTrack.getLanguage() : null, audioTrack != null ? audioTrack.getId() : null, audioTrack != null ? audioTrack.getFormatIndex() : null);
        VideoTrack videoTrack = (VideoTrack) CollectionsKt.getOrNull(list2, intRef2.element);
        int iIntValue = (videoTrack == null || (width = videoTrack.getWidth()) == null) ? -1 : width.intValue();
        int iIntValue2 = (videoTrack == null || (height = videoTrack.getHeight()) == null) ? -1 : height.intValue();
        if (iIntValue != -1 && iIntValue2 != -1) {
            generatorPlayer.getPlayer().setMaxVideoSize(iIntValue, iIntValue2, videoTrack != null ? videoTrack.getId() : null);
        }
        UIHelper.INSTANCE.dismissSafe(dialog, generatorPlayer.getActivity());
        generatorPlayer.setSelectTrackDialog(null);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0030  */
    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerError(Throwable exception) {
        String string;
        String string2;
        ExtractorLink first;
        String referer;
        ExtractorLink first2;
        Map<String, String> headers;
        Uri uri;
        Intrinsics.checkNotNullParameter(exception, "exception");
        Pair<? extends ExtractorLink, ExtractorUri> pair = this.currentSelectedLink;
        PlayerGeneratorViewModel playerGeneratorViewModel = null;
        Object obj = EnvironmentCompat.MEDIA_UNKNOWN;
        if (pair == null) {
            string = EnvironmentCompat.MEDIA_UNKNOWN;
        } else {
            ExtractorLink first3 = pair.getFirst();
            if (first3 == null || (string = first3.getUrl()) == null) {
                ExtractorUri second = pair.getSecond();
                string = (second == null || (uri = second.getUri()) == null) ? null : uri.toString();
            }
            if (string == null) {
                string = EnvironmentCompat.MEDIA_UNKNOWN;
            }
        }
        Pair<? extends ExtractorLink, ExtractorUri> pair2 = this.currentSelectedLink;
        String str = "none";
        if (pair2 == null || (first2 = pair2.getFirst()) == null || (headers = first2.getHeaders()) == null || (string2 = headers.toString()) == null) {
            string2 = "none";
        }
        Pair<? extends ExtractorLink, ExtractorUri> pair3 = this.currentSelectedLink;
        if (pair3 != null && (first = pair3.getFirst()) != null && (referer = first.getReferer()) != null) {
            str = referer;
        }
        StringBuilder sb = new StringBuilder("playerError: ");
        sb.append(this.currentSelectedLink);
        sb.append(", type=");
        sb.append(Reflection.getOrCreateKotlinClass(exception.getClass()).getQualifiedName());
        sb.append(", message=");
        sb.append(exception.getMessage());
        sb.append(", url=");
        sb.append(string);
        sb.append(", headers=");
        sb.append(string2);
        sb.append(", referer=");
        sb.append(str);
        sb.append(", position=");
        Object position = getPlayer().getPosition();
        if (position == null) {
            position = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        sb.append(position);
        sb.append(", duration=");
        Long duration = getPlayer().getDuration();
        if (duration != null) {
            obj = duration;
        }
        sb.append(obj);
        sb.append(", isPlaying=");
        sb.append(getPlayer().getIsPlaying());
        Log.e(CS3IPlayerKt.TAG, sb.toString(), exception);
        if (!hasNextMirror()) {
            PlayerGeneratorViewModel playerGeneratorViewModel2 = this.viewModel;
            if (playerGeneratorViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playerGeneratorViewModel = playerGeneratorViewModel2;
            }
            playerGeneratorViewModel.setForceClearCache(true);
        }
        super.playerError(exception);
    }

    private final void noLinksFound() {
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        playerGeneratorViewModel.setForceClearCache(true);
        CommonActivity.INSTANCE.showToast(R.string.no_links_found_toast, (Integer) 0);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            UIHelper.popCurrentPage$default(UIHelper.INSTANCE, activity, null, 1, null);
        }
    }

    private final void startPlayer() {
        if (this.isPlayerActive.get()) {
            return;
        }
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        List<Pair<ExtractorLink, ExtractorUri>> listSortLinks = playerGeneratorViewModel.getState().sortLinks(getCurrentQualityProfile());
        if (listSortLinks.isEmpty()) {
            noLinksFound();
        } else if (this.isPlayerActive.compareAndSet(false, true)) {
            loadLink((Pair) CollectionsKt.first((List) listSortLinks), false);
            showPlayerMetadata();
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00a5  */
    private final void showPlayerMetadata() {
        FrameLayout frameLayout;
        LoadResponse response;
        String strJoinToString$default;
        String shortSeasonText;
        Context context;
        List listTake;
        PlayerCustomLayoutBinding playerBinding = getPlayerBinding();
        if (playerBinding == null || (frameLayout = playerBinding.playerMetadataScrim) == null) {
            return;
        }
        TextView textView = (TextView) frameLayout.findViewById(R.id.player_movie_title);
        ImageView imageView = (ImageView) frameLayout.findViewById(R.id.player_movie_logo);
        TextView textView2 = (TextView) frameLayout.findViewById(R.id.player_movie_meta);
        TextView textView3 = (TextView) frameLayout.findViewById(R.id.player_movie_overview);
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        String str = null;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        GeneratorState generatorState = playerGeneratorViewModel.getState().getGeneratorState();
        if (generatorState == null || (response = generatorState.getResponse()) == null) {
            return;
        }
        Object currentMeta = getCurrentMeta();
        ResultEpisode resultEpisode = currentMeta instanceof ResultEpisode ? (ResultEpisode) currentMeta : null;
        textView.setText(response.getName());
        ResultFragment resultFragment = ResultFragment.INSTANCE;
        String logoUrl = response.getLogoUrl();
        Map<String, String> posterHeaders = response.getPosterHeaders();
        Intrinsics.checkNotNull(textView);
        Intrinsics.checkNotNull(imageView);
        resultFragment.bindLogo(logoUrl, posterHeaders, imageView, textView);
        List<String> tags = response.getTags();
        if (tags == null) {
            strJoinToString$default = null;
        } else {
            if (tags.isEmpty()) {
                tags = null;
            }
            if (tags == null || (listTake = CollectionsKt.take(tags, 6)) == null) {
                strJoinToString$default = null;
            } else {
                strJoinToString$default = CollectionsKt.joinToString$default(listTake, ", ", null, null, 0, null, null, 62, null);
            }
        }
        Integer year = response.getYear();
        String strValueOf = year != null ? String.valueOf(year.intValue()) : null;
        if (MainAPIKt.isMovieType(response.getType()) || (context = getContext()) == null) {
            shortSeasonText = null;
        } else {
            shortSeasonText = AppContextUtils.INSTANCE.getShortSeasonText(context, resultEpisode != null ? Integer.valueOf(resultEpisode.getEpisode()) : null, resultEpisode != null ? resultEpisode.getSeason() : null);
        }
        Score score = response.getScore();
        if (score != null) {
            str = "⭐ " + score;
        }
        String strJoinToString$default2 = CollectionsKt.joinToString$default(ArraysKt.filterNotNull(new String[]{strJoinToString$default, strValueOf, shortSeasonText, str}), " • ", null, null, 0, null, null, 62, null);
        textView2.setText(strJoinToString$default2);
        Intrinsics.checkNotNull(textView2);
        textView2.setVisibility(!StringsKt.isBlank(strJoinToString$default2) ? 0 : 8);
        String plot = response.getPlot();
        String str2 = plot;
        if (str2 != null && !StringsKt.isBlank(str2)) {
            Intrinsics.checkNotNull(textView3);
            textView3.setVisibility(0);
            textView3.setText(AppContextUtils.INSTANCE.html(plot));
        } else {
            Intrinsics.checkNotNull(textView3);
            textView3.setVisibility(8);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void nextEpisode() {
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        PlayerGeneratorViewModel playerGeneratorViewModel2 = null;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        if (Intrinsics.areEqual((Object) playerGeneratorViewModel.hasNextEpisode(), (Object) true)) {
            this.isNextEpisode = true;
            releasePlayer();
            PlayerGeneratorViewModel playerGeneratorViewModel3 = this.viewModel;
            if (playerGeneratorViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playerGeneratorViewModel2 = playerGeneratorViewModel3;
            }
            playerGeneratorViewModel2.loadLinksNext();
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void prevEpisode() {
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        PlayerGeneratorViewModel playerGeneratorViewModel2 = null;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        if (Intrinsics.areEqual((Object) playerGeneratorViewModel.hasPrevEpisode(), (Object) true)) {
            this.isNextEpisode = true;
            releasePlayer();
            PlayerGeneratorViewModel playerGeneratorViewModel3 = this.viewModel;
            if (playerGeneratorViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playerGeneratorViewModel2 = playerGeneratorViewModel3;
            }
            playerGeneratorViewModel2.loadLinksPrev();
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public boolean hasNextMirror() {
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        List<Pair<ExtractorLink, ExtractorUri>> listSortLinks = playerGeneratorViewModel.getState().sortLinks(getCurrentQualityProfile());
        return !listSortLinks.isEmpty() && CollectionsKt.indexOf((List<? extends Pair<? extends ExtractorLink, ExtractorUri>>) listSortLinks, this.currentSelectedLink) + 1 < listSortLinks.size();
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void nextMirror() {
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        List<Pair<ExtractorLink, ExtractorUri>> listSortLinks = playerGeneratorViewModel.getState().sortLinks(getCurrentQualityProfile());
        if (listSortLinks.isEmpty()) {
            noLinksFound();
            return;
        }
        int iIndexOf = CollectionsKt.indexOf((List<? extends Pair<? extends ExtractorLink, ExtractorUri>>) listSortLinks, this.currentSelectedLink) + 1;
        if (iIndexOf >= listSortLinks.size()) {
            noLinksFound();
        } else {
            loadLink(listSortLinks.get(iIndexOf), true);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.FullScreenPlayer, com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        ResultFragment.updateUI$default(ResultFragment.INSTANCE, null, 1, null);
        Job job = this.currentVerifyLink;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        super.onDestroy();
    }

    public final Integer getMaxEpisodeSet() {
        return this.maxEpisodeSet;
    }

    public final void setMaxEpisodeSet(Integer num) {
        this.maxEpisodeSet = num;
    }

    public final boolean getHasRequestedStamps() {
        return this.hasRequestedStamps;
    }

    public final void setHasRequestedStamps(boolean z) {
        this.hasRequestedStamps = z;
    }

    /* JADX WARN: Code duplicated, block: B:71:0x0109  */
    /* JADX WARN: Code duplicated, block: B:95:0x014d  */
    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerPositionChanged(long position, long duration) {
        boolean z;
        LinearLayout linearLayout;
        ImageView imageView;
        MaterialButton materialButton;
        boolean z2;
        MaterialButton materialButton2;
        Context context;
        TvType tvType;
        Object currentMeta = getCurrentMeta();
        PlayerGeneratorViewModel playerGeneratorViewModel = null;
        ResultEpisode resultEpisode = currentMeta instanceof ResultEpisode ? (ResultEpisode) currentMeta : null;
        if (resultEpisode == null || (tvType = resultEpisode.getTvType()) == null || !MainAPIKt.isLiveStream(tvType)) {
            Object currentMeta2 = getCurrentMeta();
            ResultEpisode resultEpisode2 = currentMeta2 instanceof ResultEpisode ? (ResultEpisode) currentMeta2 : null;
            if ((resultEpisode2 != null ? resultEpisode2.getTvType() : null) != TvType.NSFW && duration > 0) {
                if (!this.hasRequestedStamps) {
                    this.hasRequestedStamps = true;
                    Context context2 = getContext();
                    if (context2 != null ? PreferenceManager.getDefaultSharedPreferences(context2).getBoolean(context2.getString(R.string.enable_skip_op_from_database), true) : true) {
                        PlayerGeneratorViewModel playerGeneratorViewModel2 = this.viewModel;
                        if (playerGeneratorViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            playerGeneratorViewModel2 = null;
                        }
                        playerGeneratorViewModel2.loadStamps(duration);
                    }
                }
                long j = (100 * position) / duration;
                DataStoreHelper dataStoreHelper = DataStoreHelper.INSTANCE;
                PlayerGeneratorViewModel playerGeneratorViewModel3 = this.viewModel;
                if (playerGeneratorViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    playerGeneratorViewModel3 = null;
                }
                GeneratorState generatorState = playerGeneratorViewModel3.getState().getGeneratorState();
                dataStoreHelper.setViewPosAndResume(generatorState != null ? generatorState.getId() : null, position, duration, getCurrentMeta(), getNextMeta());
                Object currentMeta3 = getCurrentMeta();
                if (currentMeta3 instanceof ResultEpisode) {
                    if (j >= 80) {
                        Integer num = this.maxEpisodeSet;
                        ResultEpisode resultEpisode3 = (ResultEpisode) currentMeta3;
                        if ((num != null ? num.intValue() : -1) < resultEpisode3.getEpisode() && (context = getContext()) != null && PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.episode_sync_enabled_key), true)) {
                            this.maxEpisodeSet = Integer.valueOf(resultEpisode3.getEpisode());
                            SyncViewModel syncViewModel = this.sync;
                            if (syncViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("sync");
                                syncViewModel = null;
                            }
                            Integer totalEpisodeIndex = resultEpisode3.getTotalEpisodeIndex();
                            syncViewModel.modifyMaxEpisode(totalEpisodeIndex != null ? totalEpisodeIndex.intValue() : resultEpisode3.getEpisode());
                        }
                    }
                    if (!MainAPIKt.isAnimeOp(((ResultEpisode) currentMeta3).getTvType()) || j >= 50) {
                        z = false;
                    } else {
                        z = true;
                    }
                } else {
                    z = false;
                }
                PlayerCustomLayoutBinding playerBinding = getPlayerBinding();
                if (playerBinding != null && (materialButton2 = playerBinding.playerSkipOp) != null) {
                    materialButton2.setVisibility(z ? 0 : 8);
                }
                if (Globals.INSTANCE.isLayout(1)) {
                    PlayerCustomLayoutBinding playerBinding2 = getPlayerBinding();
                    if (playerBinding2 != null && (materialButton = playerBinding2.playerSkipEpisode) != null) {
                        MaterialButton materialButton3 = materialButton;
                        if (!z) {
                            PlayerGeneratorViewModel playerGeneratorViewModel4 = this.viewModel;
                            if (playerGeneratorViewModel4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                playerGeneratorViewModel4 = null;
                            }
                            z2 = Intrinsics.areEqual((Object) playerGeneratorViewModel4.hasNextEpisode(), (Object) true);
                        }
                        materialButton3.setVisibility(z2 ? 0 : 8);
                    }
                } else {
                    PlayerGeneratorViewModel playerGeneratorViewModel5 = this.viewModel;
                    if (playerGeneratorViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        playerGeneratorViewModel5 = null;
                    }
                    boolean zAreEqual = Intrinsics.areEqual((Object) playerGeneratorViewModel5.hasNextEpisode(), (Object) true);
                    PlayerCustomLayoutBinding playerBinding3 = getPlayerBinding();
                    if (playerBinding3 != null && (imageView = playerBinding3.playerGoForward) != null) {
                        imageView.setVisibility(zAreEqual ? 0 : 8);
                    }
                    PlayerCustomLayoutBinding playerBinding4 = getPlayerBinding();
                    if (playerBinding4 != null && (linearLayout = playerBinding4.playerGoForwardRoot) != null) {
                        linearLayout.setVisibility(zAreEqual ? 0 : 8);
                    }
                }
                if (j >= 80) {
                    PlayerGeneratorViewModel playerGeneratorViewModel6 = this.viewModel;
                    if (playerGeneratorViewModel6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        playerGeneratorViewModel = playerGeneratorViewModel6;
                    }
                    playerGeneratorViewModel.preLoadNextLinks();
                }
            }
        }
    }

    private final SubtitleData getAutoSelectSubtitle(Set<SubtitleData> subtitles, boolean settings, boolean downloads) {
        Object next;
        String str = this.preferredAutoSelectSubtitles;
        Object obj = null;
        if (str == null) {
            return null;
        }
        if (downloads) {
            Iterator<T> it = AppContextUtils.INSTANCE.sortSubs(subtitles).iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                SubtitleData subtitleData = (SubtitleData) next;
                if (subtitleData.getOrigin() == SubtitleOrigin.DOWNLOADED_FILE && subtitleData.matchesLanguageCode(str)) {
                    break;
                }
            }
            SubtitleData subtitleData2 = (SubtitleData) next;
            if (subtitleData2 != null) {
                return subtitleData2;
            }
        }
        if (!settings) {
            return null;
        }
        for (Object obj2 : AppContextUtils.INSTANCE.sortSubs(subtitles)) {
            if (((SubtitleData) obj2).matchesLanguageCode(str)) {
                obj = obj2;
                break;
            }
        }
        return (SubtitleData) obj;
    }

    private final boolean autoSelectFromSettings() {
        String str = this.preferredAutoSelectSubtitles;
        SubtitleData currentPreferredSubtitle = getPlayer().getCurrentPreferredSubtitle();
        Log.i(CS3IPlayerKt.TAG, "autoSelectFromSettings = " + currentPreferredSubtitle);
        Context context = getContext();
        if (context != null) {
            if (currentPreferredSubtitle != null && (str == null || currentPreferredSubtitle.matchesLanguageCode(str))) {
                if (setSubtitles(currentPreferredSubtitle, false)) {
                    getPlayer().saveData();
                    getPlayer().reloadPlayer(context);
                    IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.Play, null, 2, null);
                    return true;
                }
            } else {
                String str2 = str;
                if (str2 != null && str2.length() != 0) {
                    PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
                    if (playerGeneratorViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        playerGeneratorViewModel = null;
                    }
                    SubtitleData autoSelectSubtitle = getAutoSelectSubtitle(playerGeneratorViewModel.getState().getSubtitles(), true, false);
                    if (autoSelectSubtitle != null && setSubtitles(autoSelectSubtitle, false)) {
                        getPlayer().saveData();
                        getPlayer().reloadPlayer(context);
                        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.Play, null, 2, null);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final void autoSelectFromDownloads() {
        Context context;
        if (getPlayer().getCurrentPreferredSubtitle() != null) {
            return;
        }
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        SubtitleData autoSelectSubtitle = getAutoSelectSubtitle(playerGeneratorViewModel.getState().getSubtitles(), false, true);
        if (autoSelectSubtitle == null || (context = getContext()) == null || !setSubtitles(autoSelectSubtitle, false)) {
            return;
        }
        getPlayer().saveData();
        getPlayer().reloadPlayer(context);
        IPlayer.CC.handleEvent$default(getPlayer(), CSPlayerEvent.Play, null, 2, null);
    }

    private final void autoSelectSubtitles() {
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratorPlayer.autoSelectSubtitles$lambda$0(this.f$0);
            }
        });
    }

    static final Unit autoSelectSubtitles$lambda$0(GeneratorPlayer generatorPlayer) {
        if (!generatorPlayer.autoSelectFromSettings()) {
            generatorPlayer.autoSelectFromDownloads();
        }
        return Unit.INSTANCE;
    }

    private final String getHeaderName() {
        Object currentMeta = getCurrentMeta();
        if (currentMeta instanceof ResultEpisode) {
            return ((ResultEpisode) currentMeta).getHeaderName();
        }
        if (currentMeta instanceof ExtractorUri) {
            return ((ExtractorUri) currentMeta).getHeaderName();
        }
        return null;
    }

    private final String getPlayerVideoTitle() {
        String headerName;
        TvType tvType;
        String name;
        Integer episode;
        Integer season;
        String str;
        Object currentMeta = getCurrentMeta();
        if (currentMeta instanceof ResultEpisode) {
            ResultEpisode resultEpisode = (ResultEpisode) currentMeta;
            headerName = resultEpisode.getHeaderName();
            name = resultEpisode.getName();
            episode = Integer.valueOf(resultEpisode.getEpisode());
            season = resultEpisode.getSeason();
            tvType = resultEpisode.getTvType();
        } else if (currentMeta instanceof ExtractorUri) {
            ExtractorUri extractorUri = (ExtractorUri) currentMeta;
            headerName = extractorUri.getHeaderName();
            name = extractorUri.getName();
            episode = extractorUri.getEpisode();
            season = extractorUri.getSeason();
            tvType = extractorUri.getTvType();
        } else {
            headerName = null;
            tvType = null;
            name = null;
            episode = null;
            season = null;
        }
        Context context = getContext();
        String str2 = "";
        if (context == null || headerName == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(headerName);
        if (!MainAPIKt.isEpisodeBased(tvType) || episode == null) {
            str = "";
        } else if (season == null) {
            str = " - " + context.getString(R.string.episode) + ' ' + episode.intValue();
        } else {
            str = " \"" + context.getString(R.string.season_short) + season.intValue() + AbstractJsonLexerKt.COLON + context.getString(R.string.episode_short) + episode.intValue() + '\"';
        }
        sb.append(str);
        String str3 = name;
        if (str3 != null && !StringsKt.isBlank(str3) && !Intrinsics.areEqual(name, headerName)) {
            str2 = " - " + name;
        }
        sb.append(str2);
        return sb.toString();
    }

    public final void setTitle() {
        ImageView imageView;
        TextView textView;
        FrameLayout frameLayout;
        TextView textView2;
        String playerVideoTitle = getPlayerVideoTitle();
        if (this.limitTitle < 0) {
            PlayerCustomLayoutBinding playerBinding = getPlayerBinding();
            if (playerBinding != null && (textView2 = playerBinding.playerVideoTitle) != null) {
                textView2.setVisibility(8);
            }
        } else {
            int length = playerVideoTitle.length();
            int i = this.limitTitle;
            int i2 = length - i;
            if (i > 0 && i2 > 3) {
                StringBuilder sb = new StringBuilder();
                String strSubstring = playerVideoTitle.substring(0, this.limitTitle - 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                sb.append(strSubstring);
                sb.append("...");
                playerVideoTitle = sb.toString();
            }
        }
        Object currentMeta = getCurrentMeta();
        PlayerGeneratorViewModel playerGeneratorViewModel = null;
        ResultEpisode resultEpisode = currentMeta instanceof ResultEpisode ? (ResultEpisode) currentMeta : null;
        Boolean boolIsFiller = resultEpisode != null ? resultEpisode.isFiller() : null;
        PlayerCustomLayoutBinding playerBinding2 = getPlayerBinding();
        if (playerBinding2 != null && (frameLayout = playerBinding2.playerEpisodeFillerHolder) != null) {
            frameLayout.setVisibility(boolIsFiller != null ? boolIsFiller.booleanValue() : false ? 0 : 8);
        }
        PlayerCustomLayoutBinding playerBinding3 = getPlayerBinding();
        if (playerBinding3 != null && (textView = playerBinding3.playerVideoTitle) != null) {
            textView.setText(playerVideoTitle);
        }
        PlayerCustomLayoutBinding playerBinding4 = getPlayerBinding();
        if (playerBinding4 == null || (imageView = playerBinding4.offlinePin) == null) {
            return;
        }
        ImageView imageView2 = imageView;
        PlayerGeneratorViewModel playerGeneratorViewModel2 = this.viewModel;
        if (playerGeneratorViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            playerGeneratorViewModel = playerGeneratorViewModel2;
        }
        imageView2.setVisibility(playerGeneratorViewModel.getGenerator() instanceof DownloadFileGenerator ? 0 : 8);
    }

    public final void setPlayerDimen(Pair<Integer, Integer> widthHeight) {
        String string;
        String name;
        ExtractorUri second;
        TextView textView;
        ExtractorLink first;
        String str = null;
        if (widthHeight != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(widthHeight.getFirst().intValue());
            sb.append('x');
            sb.append(widthHeight.getSecond().intValue());
            string = sb.toString();
        } else {
            string = null;
        }
        Pair<? extends ExtractorLink, ExtractorUri> pair = this.currentSelectedLink;
        if (pair == null || (first = pair.getFirst()) == null || (name = first.getName()) == null) {
            Pair<? extends ExtractorLink, ExtractorUri> pair2 = this.currentSelectedLink;
            name = (pair2 == null || (second = pair2.getSecond()) == null) ? null : second.getName();
        }
        String headerName = getHeaderName();
        if (headerName == null || !this.showTitle || StringsKt.isBlank(headerName)) {
            headerName = null;
        }
        if (name == null || !this.showName || StringsKt.isBlank(name)) {
            name = null;
        }
        if (string != null && this.showResolution && !StringsKt.isBlank(string)) {
            str = string;
        }
        String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull((Object[]) new String[]{headerName, name, str}), " - ", null, null, 0, null, null, 62, null);
        PlayerCustomLayoutBinding playerBinding = getPlayerBinding();
        if (playerBinding == null || (textView = playerBinding.playerVideoTitleRez) == null) {
            return;
        }
        String str2 = strJoinToString$default;
        textView.setText(str2);
        textView.setVisibility(!StringsKt.isBlank(str2) ? 0 : 8);
    }

    private final String videoCodecName(String mime) {
        if (mime == null) {
            return null;
        }
        String lowerCase = mime.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (lowerCase == null) {
            return null;
        }
        String str = lowerCase;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "avc", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "h264", false, 2, (Object) null)) {
            return "AVC";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "hevc", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "h265", false, 2, (Object) null)) {
            return "HEVC";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "av1", false, 2, (Object) null)) {
            return "AV1";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "vp9", false, 2, (Object) null)) {
            return "VP9";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "vp8", false, 2, (Object) null)) {
            return "VP8";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "/", false, 2, (Object) null)) {
            String upperCase = StringsKt.substringAfter$default(lowerCase, "/", (String) null, 2, (Object) null).toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            return upperCase;
        }
        String upperCase2 = lowerCase.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
        return upperCase2;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001b  */
    private final String audioCodecName(String mime) {
        String string;
        if (mime != null) {
            String lowerCase = mime.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null) {
                string = StringsKt.trim((CharSequence) lowerCase).toString();
            } else {
                string = null;
            }
        } else {
            string = null;
        }
        if (string == null) {
            string = "";
        }
        String str = string;
        if (StringsKt.isBlank(str)) {
            return "";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "eac3-joc", false, 2, (Object) null)) {
            return "Dolby Atmos";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "truehd", false, 2, (Object) null)) {
            return "TrueHD";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "eac3", false, 2, (Object) null)) {
            return "E-AC3";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "ac-3", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) HlsSegmentFormat.AC3, false, 2, (Object) null)) {
            return "AC3";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) HlsSegmentFormat.AAC, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "mp4a", false, 2, (Object) null)) {
            return "AAC";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "opus", false, 2, (Object) null)) {
            return "Opus";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "vorbis", false, 2, (Object) null)) {
            return "Vorbis";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) HlsSegmentFormat.MP3, false, 2, (Object) null)) {
            return "MP3";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "flac", false, 2, (Object) null)) {
            return "FLAC";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "dts", false, 2, (Object) null)) {
            return "DTS";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "pcm", false, 2, (Object) null)) {
            return "PCM";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "alac", false, 2, (Object) null)) {
            return "ALAC";
        }
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "amr", false, 2, (Object) null)) {
            return "AMR";
        }
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "/", false, 2, (Object) null)) {
            return "";
        }
        String upperCase = StringsKt.substringAfter$default(string, "/", (String) null, 2, (Object) null).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        String str2 = StringsKt.isBlank(upperCase) ? null : upperCase;
        return str2 == null ? "" : str2;
    }

    /* JADX WARN: Code duplicated, block: B:56:0x00cf  */
    private final void updatePlayerInfo() {
        String str;
        TextView textView;
        CurrentTracks videoTracks = getPlayer().getVideoTracks();
        VideoTrack currentVideoTrack = videoTracks.getCurrentVideoTrack();
        AudioTrack currentAudioTrack = videoTracks.getCurrentAudioTrack();
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.showMediaInfo = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.show_media_info_key), false);
        String str2 = null;
        String strVideoCodecName = videoCodecName(currentVideoTrack != null ? currentVideoTrack.getSampleMimeType() : null);
        String strAudioCodecName = audioCodecName(currentAudioTrack != null ? currentAudioTrack.getSampleMimeType() : null);
        String strFromTagToLanguageName$default = SubtitleHelper.fromTagToLanguageName$default(SubtitleHelper.INSTANCE, currentAudioTrack != null ? currentAudioTrack.getLanguage() : null, null, 2, null);
        String label = currentAudioTrack != null ? currentAudioTrack.getLabel() : null;
        Integer channelCount = currentAudioTrack != null ? currentAudioTrack.getChannelCount() : null;
        if (channelCount == null || channelCount.intValue() <= 0) {
            str = "";
        } else if (channelCount.intValue() == 1) {
            str = "Mono";
        } else if (channelCount.intValue() == 2) {
            str = "Stereo";
        } else if (channelCount.intValue() == 6) {
            str = "5.1";
        } else if (channelCount.intValue() == 8) {
            str = "7.1";
        } else {
            str = channelCount.intValue() + "ch";
        }
        if (strFromTagToLanguageName$default != null) {
            if (StringsKt.isBlank(strFromTagToLanguageName$default)) {
                strFromTagToLanguageName$default = null;
            }
            if (strFromTagToLanguageName$default != null) {
                if (label == null || StringsKt.isBlank(label) || !StringsKt.equals(label, strFromTagToLanguageName$default, true)) {
                }
                str2 = strFromTagToLanguageName$default;
            } else if (label != null) {
                str2 = label;
            }
        } else if (label != null && !StringsKt.isBlank(label)) {
            str2 = label;
        }
        String[] strArr = {strVideoCodecName, str2, str, strAudioCodecName};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            String str3 = strArr[i];
            String str4 = str3;
            if (!(str4 == null || StringsKt.isBlank(str4))) {
                arrayList.add(str3);
            }
        }
        String strJoinToString$default = CollectionsKt.joinToString$default(arrayList, " • ", null, null, 0, null, null, 62, null);
        PlayerCustomLayoutBinding playerBinding = getPlayerBinding();
        if (playerBinding == null || (textView = playerBinding.playerVideoInfo) == null) {
            return;
        }
        String str5 = strJoinToString$default;
        textView.setText(str5);
        textView.setVisibility(this.showMediaInfo && !StringsKt.isBlank(str5) ? 0 : 8);
    }

    @Override // com.lagradost.cloudstream3.ui.player.FullScreenPlayer, com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void playerDimensionsLoaded(int width, int height) {
        super.playerDimensionsLoaded(width, height);
        setPlayerDimen(TuplesKt.to(Integer.valueOf(width), Integer.valueOf(height)));
    }

    private final void unwrapBundle(Bundle savedInstanceState) {
        HashMap serializable;
        Log.i(CS3IPlayerKt.TAG, "unwrapBundle = " + savedInstanceState);
        if (savedInstanceState != null) {
            SyncViewModel syncViewModel = this.sync;
            if (syncViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sync");
                syncViewModel = null;
            }
            if (Build.VERSION.SDK_INT < 33) {
                Serializable serializable2 = savedInstanceState.getSerializable("syncData");
                serializable = (HashMap) (serializable2 instanceof HashMap ? serializable2 : null);
            } else {
                serializable = savedInstanceState.getSerializable("syncData", HashMap.class);
            }
            syncViewModel.addSyncs((Map) serializable);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.FullScreenPlayer, com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public Integer pickLayout() {
        return Integer.valueOf(Globals.INSTANCE.isLayout(6) ? R.layout.fragment_player_tv : R.layout.fragment_player);
    }

    public final ValueAnimator getSkipAnimator() {
        return this.skipAnimator;
    }

    public final void setSkipAnimator(ValueAnimator valueAnimator) {
        this.skipAnimator = valueAnimator;
    }

    public final int getSkipIndex() {
        return this.skipIndex;
    }

    public final void setSkipIndex(int i) {
        this.skipIndex = i;
    }

    private final void displayTimeStamp(final boolean show) {
        final MaterialButton materialButton;
        PlayerCustomLayoutBinding playerBinding;
        MaterialButton materialButton2;
        if (getTimestampShowState() == show) {
            return;
        }
        this.skipIndex++;
        setTimestampShowState(show);
        PlayerCustomLayoutBinding playerBinding2 = getPlayerBinding();
        if (playerBinding2 == null || (materialButton = playerBinding2.skipChapterButton) == null) {
            return;
        }
        int toPx = UIHelper.INSTANCE.getToPx(170);
        int toPx2 = UIHelper.INSTANCE.getToPx(10);
        int i = show ? toPx : toPx2;
        if (show) {
            toPx = toPx2;
        }
        ValueAnimator valueAnimator = this.skipAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        materialButton.setVisibility(0);
        if (show && !getIsShowing() && (playerBinding = getPlayerBinding()) != null && (materialButton2 = playerBinding.skipChapterButton) != null) {
            materialButton2.requestFocus();
        }
        ViewGroup.LayoutParams layoutParams = materialButton.getLayoutParams();
        layoutParams.width = toPx;
        materialButton.setLayoutParams(layoutParams);
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(toPx, i);
        Intrinsics.checkNotNull(valueAnimatorOfInt);
        valueAnimatorOfInt.addListener(new Animator.AnimatorListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$displayTimeStamp$lambda$0$0$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                PlayerCustomLayoutBinding playerBinding3;
                ImageView imageView;
                MaterialButton materialButton3;
                if (show) {
                    return;
                }
                PlayerCustomLayoutBinding playerBinding4 = this.getPlayerBinding();
                if (playerBinding4 != null && (materialButton3 = playerBinding4.skipChapterButton) != null) {
                    materialButton3.setVisibility(8);
                }
                if (this.getIsShowing() || (playerBinding3 = this.getPlayerBinding()) == null || (imageView = playerBinding3.playerPausePlay) == null) {
                    return;
                }
                imageView.requestFocus();
            }
        });
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda16
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                GeneratorPlayer.displayTimeStamp$lambda$0$0$1(materialButton, valueAnimator2);
            }
        });
        valueAnimatorOfInt.setDuration(500L);
        valueAnimatorOfInt.start();
        this.skipAnimator = valueAnimatorOfInt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void displayTimeStamp$lambda$0$0$1(MaterialButton materialButton, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(valueAnimator, "valueAnimator");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        int iIntValue = ((Integer) animatedValue).intValue();
        ViewGroup.LayoutParams layoutParams = materialButton.getLayoutParams();
        Intrinsics.checkNotNullExpressionValue(layoutParams, "getLayoutParams(...)");
        layoutParams.width = iIntValue;
        materialButton.setLayoutParams(layoutParams);
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onTimestampSkipped(VideoSkipStamp timestamp) {
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        displayTimeStamp(false);
    }

    @Override // com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.player.PlayerView.Callbacks
    public void onTimestamp(VideoSkipStamp timestamp) {
        MaterialButton materialButton;
        Handler handler;
        MaterialButton materialButton2;
        if (timestamp != null) {
            PlayerCustomLayoutBinding playerBinding = getPlayerBinding();
            if (playerBinding != null && (materialButton2 = playerBinding.skipChapterButton) != null) {
                TextUtilKt.setText(materialButton2, timestamp.getUiText());
            }
            displayTimeStamp(true);
            final int i = this.skipIndex;
            PlayerCustomLayoutBinding playerBinding2 = getPlayerBinding();
            if (playerBinding2 == null || (materialButton = playerBinding2.skipChapterButton) == null || (handler = materialButton.getHandler()) == null) {
                return;
            }
            handler.postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda49
                @Override // java.lang.Runnable
                public final void run() {
                    GeneratorPlayer.onTimestamp$lambda$0(this.f$0, i);
                }
            }, LiveManagerKt.LIVE_MARGIN);
            return;
        }
        displayTimeStamp(false);
    }

    static final void onTimestamp$lambda$0(GeneratorPlayer generatorPlayer, int i) {
        if (generatorPlayer.skipIndex == i) {
            generatorPlayer.displayTimeStamp(false);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.FullScreenPlayer
    public boolean isThereEpisodes() {
        List<?> allMeta;
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        GeneratorState generatorState = playerGeneratorViewModel.getState().getGeneratorState();
        Object orNull = (generatorState == null || (allMeta = generatorState.getAllMeta()) == null) ? null : CollectionsKt.getOrNull(allMeta, 1);
        return (orNull instanceof ResultEpisode ? (ResultEpisode) orNull : null) != null;
    }

    @Override // com.lagradost.cloudstream3.ui.player.FullScreenPlayer
    public void showEpisodesOverlay() {
        try {
            final PlayerCustomLayoutBinding playerBinding = getPlayerBinding();
            if (playerBinding != null) {
                RecyclerView playerEpisodeList = playerBinding.playerEpisodeList;
                Intrinsics.checkNotNullExpressionValue(playerEpisodeList, "playerEpisodeList");
                BaseAdapterKt.setRecycledViewPool(playerEpisodeList, EpisodeAdapter.INSTANCE.getSharedPool());
                playerBinding.playerEpisodeList.setAdapter(new EpisodeAdapter(false, new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return GeneratorPlayer.showEpisodesOverlay$lambda$0$0(this.f$0, playerBinding, (EpisodeClickEvent) obj);
                    }
                }, new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return GeneratorPlayer.showEpisodesOverlay$lambda$0$1((DownloadClickEvent) obj);
                    }
                }));
                LinearListLayoutKt.setLinearListLayout$default(playerBinding.playerEpisodeList, false, 0, -2, -2, -2, 2, null);
                final List<ResultEpisode> allMeta = getAllMeta();
                if (allMeta == null) {
                    allMeta = CollectionsKt.emptyList();
                }
                RecyclerView.Adapter adapter = playerBinding.playerEpisodeList.getAdapter();
                PlayerGeneratorViewModel playerGeneratorViewModel = null;
                EpisodeAdapter episodeAdapter = adapter instanceof EpisodeAdapter ? (EpisodeAdapter) adapter : null;
                if (episodeAdapter != null) {
                    BaseAdapter.submitList$default(episodeAdapter, allMeta, null, 2, null);
                }
                PlayerGeneratorViewModel playerGeneratorViewModel2 = this.viewModel;
                if (playerGeneratorViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    playerGeneratorViewModel = playerGeneratorViewModel2;
                }
                GeneratorState generatorState = playerGeneratorViewModel.getState().getGeneratorState();
                if (generatorState != null) {
                    final int index = generatorState.getIndex();
                    playerBinding.playerEpisodeList.scrollToPosition(index);
                    if (Globals.INSTANCE.isLayout(2)) {
                        playerBinding.playerEpisodeList.post(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda25
                            @Override // java.lang.Runnable
                            public final void run() {
                                GeneratorPlayer.showEpisodesOverlay$lambda$0$2$0(playerBinding, index);
                            }
                        });
                    }
                }
                final Ref.IntRef intRef = new Ref.IntRef();
                intRef.element = -1;
                playerBinding.playerEpisodeList.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$showEpisodesOverlay$1$4
                    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        int iFindFirstCompletelyVisibleItemPosition;
                        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
                        if (linearLayoutManager == null || (iFindFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition()) == -1 || iFindFirstCompletelyVisibleItemPosition == intRef.element) {
                            return;
                        }
                        intRef.element = iFindFirstCompletelyVisibleItemPosition;
                        ResultEpisode resultEpisode = (ResultEpisode) CollectionsKt.getOrNull(allMeta, iFindFirstCompletelyVisibleItemPosition);
                        if (resultEpisode != null) {
                            TextUtilKt.setText(playerBinding.playerEpisodeOverlayTitle, ResultViewModel2.INSTANCE.seasonToTxt(resultEpisode.getSeasonData(), resultEpisode.getSeasonIndex()));
                        }
                    }
                });
            }
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showEpisodesOverlay$lambda$0$0(GeneratorPlayer generatorPlayer, PlayerCustomLayoutBinding playerCustomLayoutBinding, EpisodeClickEvent episodeClick) {
        Intrinsics.checkNotNullParameter(episodeClick, "episodeClick");
        if (episodeClick.getAction() == 11) {
            generatorPlayer.isNextEpisode = false;
            generatorPlayer.releasePlayer();
            LinearLayout playerEpisodeOverlay = playerCustomLayoutBinding.playerEpisodeOverlay;
            Intrinsics.checkNotNullExpressionValue(playerEpisodeOverlay, "playerEpisodeOverlay");
            playerEpisodeOverlay.setVisibility(8);
            Integer position = episodeClick.getPosition();
            if (position != null) {
                int iIntValue = position.intValue();
                PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
                if (playerGeneratorViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    playerGeneratorViewModel = null;
                }
                playerGeneratorViewModel.loadThisEpisode(iIntValue);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showEpisodesOverlay$lambda$0$1(DownloadClickEvent downloadClickEvent) {
        Intrinsics.checkNotNullParameter(downloadClickEvent, "downloadClickEvent");
        DownloadButtonSetup.INSTANCE.handleDownloadClick(downloadClickEvent);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showEpisodesOverlay$lambda$0$2$0(PlayerCustomLayoutBinding playerCustomLayoutBinding, int i) {
        View view;
        View view2;
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = playerCustomLayoutBinding.playerEpisodeList.findViewHolderForAdapterPosition(i);
        if (viewHolderFindViewHolderForAdapterPosition != null && (view2 = viewHolderFindViewHolderForAdapterPosition.itemView) != null) {
            view2.requestFocus();
        }
        if (viewHolderFindViewHolderForAdapterPosition == null || (view = viewHolderFindViewHolderForAdapterPosition.itemView) == null) {
            return;
        }
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void releasePlayer() {
        FrameLayout frameLayout;
        MaterialButton materialButton;
        getPlayer().release();
        this.currentSelectedSubtitles = null;
        this.currentSelectedLink = null;
        this.isPlayerActive.set(false);
        FragmentPlayerBinding fragmentPlayerBinding = (FragmentPlayerBinding) getBinding();
        if (fragmentPlayerBinding != null && (materialButton = fragmentPlayerBinding.overlayLoadingSkipButton) != null) {
            materialButton.setVisibility(8);
        }
        FragmentPlayerBinding fragmentPlayerBinding2 = (FragmentPlayerBinding) getBinding();
        if (fragmentPlayerBinding2 != null && (frameLayout = fragmentPlayerBinding2.playerLoadingOverlay) != null) {
            frameLayout.setVisibility(0);
        }
        uiReset();
    }

    public final void exitPlayer() {
        PlayerView playerHostView = getPlayerHostView();
        if (playerHostView != null) {
            playerHostView.exitFullscreen();
        }
        getPlayer().release();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            UIHelper.popCurrentPage$default(UIHelper.INSTANCE, activity, null, 1, null);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.FullScreenPlayer, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        PlayerGeneratorViewModel playerGeneratorViewModel = this.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        outState.putInt("index", playerGeneratorViewModel.getEpisodeIndex());
        super.onSaveInstanceState(outState);
    }

    /* JADX WARN: Code duplicated, block: B:46:0x0134  */
    @Override // com.lagradost.cloudstream3.ui.player.FullScreenPlayer, com.lagradost.cloudstream3.ui.player.AbstractPlayerFragment, com.lagradost.cloudstream3.ui.BaseFragment, com.lagradost.cloudstream3.ui.BaseFragmentHelper
    public void onBindingCreated(final FragmentPlayerBinding binding, Bundle savedInstanceState) {
        String string;
        Integer numValueOf;
        int i;
        VideoGenerator<?> videoGenerator;
        ImageView imageView;
        CardView cardView;
        ArrayList arrayListEmptyList;
        String lowerCase;
        Intrinsics.checkNotNullParameter(binding, "binding");
        GeneratorPlayer generatorPlayer = this;
        this.viewModel = (PlayerGeneratorViewModel) new ViewModelProvider(generatorPlayer).get(PlayerGeneratorViewModel.class);
        this.sync = (SyncViewModel) new ViewModelProvider(generatorPlayer).get(SyncViewModel.class);
        PlayerGeneratorViewModel playerGeneratorViewModel = null;
        if (savedInstanceState == null || (string = savedInstanceState.getString("uuid")) == null) {
            Bundle arguments = getArguments();
            string = arguments != null ? arguments.getString("uuid") : null;
        }
        if (savedInstanceState != null) {
            i = savedInstanceState.getInt("index");
        } else {
            Bundle arguments2 = getArguments();
            if (arguments2 != null) {
                i = arguments2.getInt("index");
            } else {
                numValueOf = null;
            }
            videoGenerator = generators.get(string);
            unwrapBundle(savedInstanceState);
            unwrapBundle(getArguments());
            super.onBindingCreated(binding, savedInstanceState);
            if (videoGenerator != null || numValueOf == null) {
                exitPlayer();
            }
            PlayerGeneratorViewModel playerGeneratorViewModel2 = this.viewModel;
            if (playerGeneratorViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playerGeneratorViewModel2 = null;
            }
            playerGeneratorViewModel2.attachGenerator(videoGenerator, numValueOf.intValue());
            Context context = getContext();
            if (context != null) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                this.showName = defaultSharedPreferences.getBoolean(context.getString(R.string.show_name_key), true);
                this.showResolution = defaultSharedPreferences.getBoolean(context.getString(R.string.show_resolution_key), true);
                this.showMediaInfo = defaultSharedPreferences.getBoolean(context.getString(R.string.show_media_info_key), false);
                this.limitTitle = defaultSharedPreferences.getInt(context.getString(R.string.prefer_title_limit_key), 0);
                CustomDecoder.INSTANCE.updateForcedEncoding(context);
                PlayerGeneratorViewModel playerGeneratorViewModel3 = this.viewModel;
                if (playerGeneratorViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    playerGeneratorViewModel3 = null;
                }
                playerGeneratorViewModel3.setFilterSubByLang(defaultSharedPreferences.getBoolean(getString(R.string.filter_sub_lang_key), false));
                PlayerGeneratorViewModel playerGeneratorViewModel4 = this.viewModel;
                if (playerGeneratorViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    playerGeneratorViewModel4 = null;
                }
                if (playerGeneratorViewModel4.getFilterSubByLang()) {
                    Set<String> stringSet = defaultSharedPreferences.getStringSet(getString(R.string.provider_lang_key), SetsKt.mutableSetOf("en"));
                    PlayerGeneratorViewModel playerGeneratorViewModel5 = this.viewModel;
                    if (playerGeneratorViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        playerGeneratorViewModel5 = null;
                    }
                    if (stringSet == null) {
                        arrayListEmptyList = CollectionsKt.emptyList();
                    } else {
                        ArrayList arrayList = new ArrayList();
                        Iterator<T> it = stringSet.iterator();
                        while (it.hasNext()) {
                            String strFromTagToEnglishLanguageName = SubtitleHelper.INSTANCE.fromTagToEnglishLanguageName((String) it.next());
                            if (strFromTagToEnglishLanguageName != null) {
                                lowerCase = strFromTagToEnglishLanguageName.toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                if (lowerCase == null) {
                                    lowerCase = null;
                                }
                            } else {
                                lowerCase = null;
                            }
                            if (lowerCase != null) {
                                arrayList.add(lowerCase);
                            }
                        }
                        arrayListEmptyList = arrayList;
                    }
                    playerGeneratorViewModel5.setLangFilterList(arrayListEmptyList);
                }
            }
            unwrapBundle(savedInstanceState);
            unwrapBundle(getArguments());
            SyncViewModel syncViewModel = this.sync;
            if (syncViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sync");
                syncViewModel = null;
            }
            syncViewModel.updateUserData();
            this.preferredAutoSelectSubtitles = SubtitlesFragment.INSTANCE.getAutoSelectLanguageTagIETF();
            Pair<? extends ExtractorLink, ExtractorUri> pair = this.currentSelectedLink;
            if (pair == null) {
                PlayerGeneratorViewModel playerGeneratorViewModel6 = this.viewModel;
                if (playerGeneratorViewModel6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    playerGeneratorViewModel6 = null;
                }
                PlayerGeneratorViewModel.loadLinks$default(playerGeneratorViewModel6, null, 1, null);
            } else {
                loadLink(pair, true);
            }
            binding.overlayLoadingSkipButton.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda35
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GeneratorPlayer.onBindingCreated$lambda$1(this.f$0, view);
                }
            });
            binding.playerLoadingGoBack.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda36
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.exitPlayer();
                }
            });
            PlayerCustomLayoutBinding playerBinding = getPlayerBinding();
            if (playerBinding != null && (cardView = playerBinding.downloadHeader) != null) {
                cardView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda37
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        GeneratorPlayer.onBindingCreated$lambda$3(view);
                    }
                });
            }
            PlayerCustomLayoutBinding playerBinding2 = getPlayerBinding();
            if (playerBinding2 != null && (imageView = playerBinding2.downloadHeaderToggle) != null) {
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda38
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        GeneratorPlayer.onBindingCreated$lambda$4(this.f$0, view);
                    }
                });
            }
            GeneratorPlayer generatorPlayer2 = this;
            PlayerGeneratorViewModel playerGeneratorViewModel7 = this.viewModel;
            if (playerGeneratorViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playerGeneratorViewModel7 = null;
            }
            LifecycleKt.observe(generatorPlayer2, playerGeneratorViewModel7.getCurrentStamps(), new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda39
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratorPlayer.onBindingCreated$lambda$5(this.f$0, (VideoLive) obj);
                }
            });
            PlayerGeneratorViewModel playerGeneratorViewModel8 = this.viewModel;
            if (playerGeneratorViewModel8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playerGeneratorViewModel8 = null;
            }
            LifecycleKt.observe(generatorPlayer2, playerGeneratorViewModel8.getCurrentSubtitles(), new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda40
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratorPlayer.onBindingCreated$lambda$6(this.f$0, (VideoLive) obj);
                }
            });
            PlayerGeneratorViewModel playerGeneratorViewModel9 = this.viewModel;
            if (playerGeneratorViewModel9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playerGeneratorViewModel9 = null;
            }
            LifecycleKt.observe(generatorPlayer2, playerGeneratorViewModel9.getLoadingLinks(), new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda41
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratorPlayer.onBindingCreated$lambda$7(this.f$0, (VideoLive) obj);
                }
            });
            PlayerGeneratorViewModel playerGeneratorViewModel10 = this.viewModel;
            if (playerGeneratorViewModel10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playerGeneratorViewModel = playerGeneratorViewModel10;
            }
            LifecycleKt.observe(generatorPlayer2, playerGeneratorViewModel.getCurrentLinks(), new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda42
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return GeneratorPlayer.onBindingCreated$lambda$8(this.f$0, binding, (VideoLive) obj);
                }
            });
            return;
        }
        numValueOf = Integer.valueOf(i);
        videoGenerator = generators.get(string);
        unwrapBundle(savedInstanceState);
        unwrapBundle(getArguments());
        super.onBindingCreated(binding, savedInstanceState);
        if (videoGenerator != null) {
        }
        exitPlayer();
    }

    static final void onBindingCreated$lambda$1(GeneratorPlayer generatorPlayer, View view) {
        PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        playerGeneratorViewModel.modifyState(new Function1() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GeneratorPlayer.onBindingCreated$lambda$1$0((VideoState) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VideoState onBindingCreated$lambda$1$0(VideoState modifyState) {
        Intrinsics.checkNotNullParameter(modifyState, "$this$modifyState");
        return VideoState.copy$default(modifyState, null, null, null, new Resource.Success(Unit.INSTANCE), null, 0, 55, null);
    }

    static final void onBindingCreated$lambda$4(GeneratorPlayer generatorPlayer, View view) {
        CardView cardView;
        PlayerCustomLayoutBinding playerBinding = generatorPlayer.getPlayerBinding();
        if (playerBinding == null || (cardView = playerBinding.downloadHeader) == null) {
            return;
        }
        CardView cardView2 = cardView;
        cardView2.setVisibility(cardView2.getVisibility() == 0 ? 8 : 0);
    }

    static final Unit onBindingCreated$lambda$5(GeneratorPlayer generatorPlayer, VideoLive videoLive) {
        Intrinsics.checkNotNullParameter(videoLive, "<destruct>");
        List<VideoSkipStamp> list = (List) videoLive.component1();
        int videoLive2 = videoLive.getInstance();
        PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        if (videoLive2 != playerGeneratorViewModel.getState().getInstance()) {
            return Unit.INSTANCE;
        }
        generatorPlayer.getPlayer().addTimeStamps(list);
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$6(GeneratorPlayer generatorPlayer, VideoLive videoLive) {
        Intrinsics.checkNotNullParameter(videoLive, "<destruct>");
        Set<SubtitleData> set = (Set) videoLive.component1();
        int videoLive2 = videoLive.getInstance();
        PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        if (videoLive2 != playerGeneratorViewModel.getState().getInstance()) {
            return Unit.INSTANCE;
        }
        generatorPlayer.getPlayer().setActiveSubtitles(set);
        SubtitleData subtitleData = (SubtitleData) CollectionsKt.lastOrNull(set);
        if ((subtitleData != null ? subtitleData.getOrigin() : null) != SubtitleOrigin.DOWNLOADED_FILE) {
            generatorPlayer.autoSelectSubtitles();
        }
        return Unit.INSTANCE;
    }

    static final Unit onBindingCreated$lambda$7(GeneratorPlayer generatorPlayer, VideoLive videoLive) {
        Intrinsics.checkNotNullParameter(videoLive, "<destruct>");
        Resource resource = (Resource) videoLive.component1();
        int videoLive2 = videoLive.getInstance();
        PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        if (videoLive2 != playerGeneratorViewModel.getState().getInstance()) {
            return Unit.INSTANCE;
        }
        if (resource instanceof Resource.Loading) {
            generatorPlayer.releasePlayer();
        } else if (resource instanceof Resource.Success) {
            generatorPlayer.startPlayer();
        } else {
            if (!(resource instanceof Resource.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            CommonActivity.INSTANCE.showToast(((Resource.Failure) resource).getErrorString(), (Integer) 1);
            generatorPlayer.startPlayer();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0049  */
    static final Unit onBindingCreated$lambda$8(final GeneratorPlayer generatorPlayer, FragmentPlayerBinding fragmentPlayerBinding, VideoLive videoLive) {
        boolean z;
        Intrinsics.checkNotNullParameter(videoLive, "<destruct>");
        Set set = (Set) videoLive.component1();
        int videoLive2 = videoLive.getInstance();
        PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
        PlayerGeneratorViewModel playerGeneratorViewModel2 = null;
        if (playerGeneratorViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            playerGeneratorViewModel = null;
        }
        if (videoLive2 != playerGeneratorViewModel.getState().getInstance()) {
            return Unit.INSTANCE;
        }
        if (set.isEmpty()) {
            z = false;
        } else {
            PlayerGeneratorViewModel playerGeneratorViewModel3 = generatorPlayer.viewModel;
            if (playerGeneratorViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                playerGeneratorViewModel2 = playerGeneratorViewModel3;
            }
            VideoGenerator<?> generator = playerGeneratorViewModel2.getGenerator();
            if (generator == null || !generator.getCanSkipLoading()) {
                z = false;
            } else {
                z = true;
            }
        }
        MaterialButton overlayLoadingSkipButton = fragmentPlayerBinding.overlayLoadingSkipButton;
        Intrinsics.checkNotNullExpressionValue(overlayLoadingSkipButton, "overlayLoadingSkipButton");
        boolean z2 = overlayLoadingSkipButton.getVisibility() == 8;
        MaterialButton materialButton = fragmentPlayerBinding.overlayLoadingSkipButton;
        Intrinsics.checkNotNull(materialButton);
        materialButton.setVisibility(z ? 0 : 8);
        if (set.isEmpty()) {
            materialButton.setText(R.string.skip_loading);
        } else {
            materialButton.setText(materialButton.getContext().getString(R.string.skip_loading) + " (" + set.size() + ')');
        }
        ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return GeneratorPlayer.onBindingCreated$lambda$8$1(this.f$0);
            }
        });
        if (z && z2) {
            fragmentPlayerBinding.overlayLoadingSkipButton.requestFocus();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onBindingCreated$lambda$8$1(GeneratorPlayer generatorPlayer) {
        if (!generatorPlayer.isPlayerActive.get()) {
            PlayerGeneratorViewModel playerGeneratorViewModel = generatorPlayer.viewModel;
            if (playerGeneratorViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playerGeneratorViewModel = null;
            }
            PersistentSet<Pair<ExtractorLink, ExtractorUri>> links = playerGeneratorViewModel.getState().getLinks();
            if (!(links instanceof Collection) || !links.isEmpty()) {
                Iterator<Pair<ExtractorLink, ExtractorUri>> it = links.iterator();
                while (it.hasNext()) {
                    if (QualityDataHelper.INSTANCE.getLinkPriority(generatorPlayer.getCurrentQualityProfile(), it.next().getFirst()) >= 10) {
                        generatorPlayer.startPlayer();
                        break;
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }

    static final void onBindingCreated$lambda$3(View view) {
        if (view != null) {
            view.setVisibility(8);
        }
    }
}
