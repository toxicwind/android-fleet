package com.lagradost.cloudstream3.ui.player;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Rational;
import android.widget.FrameLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.collection.SieveCacheKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.database.StandaloneDatabaseProvider;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor;
import androidx.media3.datasource.cache.SimpleCache;
import androidx.media3.datasource.cronet.CronetDataSource;
import androidx.media3.datasource.okhttp.OkHttpDataSource;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.DefaultLivePlaybackSpeedControl;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.dash.DashMediaSource;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.drm.FrameworkMediaDrm;
import androidx.media3.exoplayer.drm.HttpMediaDrmCallback;
import androidx.media3.exoplayer.drm.KeyRequestInfo;
import androidx.media3.exoplayer.drm.LocalMediaDrmCallback;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.source.ClippingMediaSource;
import androidx.media3.exoplayer.source.ConcatenatingMediaSource;
import androidx.media3.exoplayer.source.ConcatenatingMediaSource2;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MergingMediaSource;
import androidx.media3.exoplayer.source.SingleSampleMediaSource;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.text.TextRenderer;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.ui.SubtitleView;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import androidx.preference.PreferenceManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.collect.ImmutableList;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.AudioFile;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.ui.player.live.LiveHelper;
import com.lagradost.cloudstream3.ui.player.live.LiveManager;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.ui.subtitles.SaveCaptionStyle;
import com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.DrmExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkPlayList;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.PlayListItem;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.videoskip.VideoSkipStamp;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.uuid.Uuid;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.chromium.net.CronetEngine;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: CS3IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000È\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0006*\u0002¿\u0001\b\u0007\u0018\u0000 Ã\u00012\u00020\u0001:\u0006Á\u0001Â\u0001Ã\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010/\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0002\u00100J\u000f\u00101\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0002\u00100J\b\u00102\u001a\u00020\u0007H\u0016J\b\u00103\u001a\u00020\"H\u0016J\u0010\u0010=\u001a\u00020<2\u0006\u0010=\u001a\u00020;H\u0007J\b\u0010A\u001a\u00020<H\u0016J.\u0010B\u001a\u00020<2\u0014\b\u0001\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020<0:2\u000e\u00108\u001a\n\u0012\u0004\u0012\u00020*\u0018\u000105H\u0017J\n\u0010C\u001a\u000207*\u000207J$\u0010D\u001a\u00020<2\b\u0010E\u001a\u0004\u0018\u00010F2\b\u0010G\u001a\u0004\u0018\u00010H2\b\u0010I\u001a\u0004\u0018\u00010JJ\u0012\u0010K\u001a\u0004\u0018\u00010L2\u0006\u0010M\u001a\u00020\"H\u0016J\b\u0010N\u001a\u00020\u0007H\u0016Je\u0010O\u001a\u00020<2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020\u00072\b\u0010S\u001a\u0004\u0018\u00010%2\b\u0010T\u001a\u0004\u0018\u00010'2\b\u0010U\u001a\u0004\u0018\u00010\u000e2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020X0W2\b\u0010Y\u001a\u0004\u0018\u00010X2\b\u0010Z\u001a\u0004\u0018\u00010\u00072\u0006\u0010[\u001a\u00020\u0007H\u0016¢\u0006\u0002\u0010\\J\u0016\u0010]\u001a\u00020<2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020X0WH\u0016J*\u0010_\u001a\u0010\u0012\u0004\u0012\u00020`\u0012\u0004\u0012\u00020*\u0018\u000106*\b\u0012\u0004\u0012\u00020a052\b\u0010b\u001a\u0004\u0018\u000107H\u0002J\"\u0010c\u001a\u00020<2\u0006\u0010d\u001a\u00020*2\u0006\u0010e\u001a\u00020*2\b\u0010b\u001a\u0004\u0018\u000107H\u0016J+\u0010f\u001a\u00020<2\b\u0010g\u001a\u0004\u0018\u0001072\b\u0010b\u001a\u0004\u0018\u0001072\b\u0010h\u001a\u0004\u0018\u00010*H\u0016¢\u0006\u0002\u0010iJ$\u0010j\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020k\u0012\u0004\u0012\u00020*0605*\b\u0012\u0004\u0012\u00020a05H\u0002J\u001e\u0010j\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020k\u0012\u0004\u0012\u00020*0605*\u00020aH\u0002J\u001b\u0010l\u001a\u00020m*\u00020k2\b\u0010h\u001a\u0004\u0018\u00010*H\u0002¢\u0006\u0002\u0010nJ\f\u0010o\u001a\u00020p*\u00020kH\u0002J\f\u0010q\u001a\u00020r*\u00020kH\u0002J\b\u0010s\u001a\u00020tH\u0016J\u0012\u0010u\u001a\u00020\u00072\b\u0010Y\u001a\u0004\u0018\u00010XH\u0016J\u0010\u0010w\u001a\u00020<2\u0006\u0010x\u001a\u00020\u000eH\u0016J\b\u0010y\u001a\u00020\u000eH\u0016J\u000e\u0010z\u001a\b\u0012\u0004\u0012\u00020{05H\u0016J\n\u0010|\u001a\u0004\u0018\u00010XH\u0016J\n\u0010}\u001a\u0004\u0018\u00010~H\u0016J\u0010\u0010\u007f\u001a\u00020<2\u0006\u0010I\u001a\u00020JH\u0016J\t\u0010\u0080\u0001\u001a\u00020<H\u0016J\u0014\u0010\u0081\u0001\u001a\u00020<2\t\b\u0002\u0010\u0082\u0001\u001a\u00020\u0007H\u0002J\t\u0010\u0083\u0001\u001a\u00020<H\u0016J\t\u0010\u0084\u0001\u001a\u00020<H\u0016J\u0011\u0010\u0085\u0001\u001a\u00020<2\u0006\u0010P\u001a\u00020QH\u0016J\t\u0010\u0086\u0001\u001a\u00020<H\u0016J\u0012\u0010\u0087\u0001\u001a\u00020<2\u0007\u0010\u0088\u0001\u001a\u00020\"H\u0016J\u001f\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u008a\u00012\u000b\b\u0002\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0003\u0010\u008c\u0001J&\u0010\u008d\u0001\u001a\u00020<2\u000b\b\u0002\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u008e\u0001\u001a\u00030\u008f\u0001¢\u0006\u0003\u0010\u0090\u0001J\u001c\u0010\u0091\u0001\u001a\u00020<2\u0007\u0010\u0092\u0001\u001a\u00020\u000e2\b\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0016J\u001c\u0010\u0093\u0001\u001a\u00020<2\u0007\u0010\u0092\u0001\u001a\u00020\u000e2\b\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0016J \u0010\u0091\u0001\u001a\u00020<*\u00020\t2\u0007\u0010\u0092\u0001\u001a\u00020\u000e2\b\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0002J\u001c\u0010\u0094\u0001\u001a\u00020<2\u0007\u0010=\u001a\u00030\u0095\u00012\b\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0016J\u0013\u0010\u0097\u0001\u001a\u00030\u0098\u00012\u0007\u0010\u0099\u0001\u001a\u000207H\u0002J®\u0001\u0010\u009a\u0001\u001a\u00020\t2\u0006\u0010P\u001a\u00020Q2\u000e\u0010\u009b\u0001\u001a\t\u0012\u0005\u0012\u00030\u009c\u0001052\u000e\u0010\u009d\u0001\u001a\t\u0012\u0005\u0012\u00030\u009e\u0001052\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\"2\u0007\u0010\u009f\u0001\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\f\b\u0002\u0010 \u0001\u001a\u0005\u0018\u00010¡\u00012\t\b\u0002\u0010¢\u0001\u001a\u00020\u00072\f\b\u0002\u0010£\u0001\u001a\u0005\u0018\u00010¤\u00012\u000b\b\u0002\u0010¥\u0001\u001a\u0004\u0018\u00010*2\u0010\b\u0002\u0010¦\u0001\u001a\t\u0012\u0005\u0012\u00030§\u000105H\u0002¢\u0006\u0003\u0010¨\u0001JQ\u0010©\u0001\u001a\u00020<2\u0006\u0010P\u001a\u00020Q2\u000e\u0010ª\u0001\u001a\t\u0012\u0005\u0012\u00030\u009c\u0001052\u000e\u0010\u009d\u0001\u001a\t\u0012\u0005\u0012\u00030\u009e\u0001052\u0010\b\u0002\u0010¦\u0001\u001a\t\u0012\u0005\u0012\u00030§\u0001052\f\b\u0002\u0010 \u0001\u001a\u0005\u0018\u00010¡\u0001H\u0002J\u0019\u0010¬\u0001\u001a\u00020<2\u000e\u0010\u00ad\u0001\u001a\t\u0012\u0005\u0012\u00030\u008a\u000105H\u0016J\u0007\u0010®\u0001\u001a\u00020<J\u0019\u0010¯\u0001\u001a\u00020<2\u0006\u0010P\u001a\u00020Q2\u0006\u0010T\u001a\u00020'H\u0002JC\u0010°\u0001\u001a\u001b\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u009e\u000105\u0012\n\u0012\b\u0012\u0004\u0012\u00020X05062\n\u0010±\u0001\u001a\u0005\u0018\u00010²\u00012\u0007\u0010³\u0001\u001a\u00020-2\n\u0010´\u0001\u001a\u0005\u0018\u00010µ\u0001H\u0002J,\u0010¶\u0001\u001a\t\u0012\u0005\u0012\u00030§\u0001052\u000e\u0010·\u0001\u001a\t\u0012\u0005\u0012\u00030¸\u0001052\n\u0010´\u0001\u001a\u0005\u0018\u00010µ\u0001H\u0002J\t\u0010¹\u0001\u001a\u00020\u0007H\u0016J\u0019\u0010º\u0001\u001a\u00020<2\u0006\u0010P\u001a\u00020Q2\u0006\u0010S\u001a\u00020%H\u0003J$\u0010»\u0001\u001a\u00020<2\u0006\u0010P\u001a\u00020Q2\u0006\u0010S\u001a\u00020%2\t\b\u0002\u0010¼\u0001\u001a\u00020\u0007H\u0003J\u0011\u0010½\u0001\u001a\u00020<2\u0006\u0010P\u001a\u00020QH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\t@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R \u00104\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u00070605X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00108\u001a\n\u0012\u0004\u0012\u00020*\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u00109\u001a\u0010\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020<\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010>\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u001f\"\u0004\b?\u0010@R\u0010\u0010^\u001a\u0004\u0018\u00010XX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010v\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0096\u0001\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010«\u0001\u001a\t\u0012\u0005\u0012\u00030\u008a\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010¾\u0001\u001a\u00030¿\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010À\u0001¨\u0006Ä\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/CS3IPlayer;", "Lcom/lagradost/cloudstream3/ui/player/IPlayer;", "<init>", "()V", "playerListener", "Landroidx/media3/common/Player$Listener;", "isPlaying", "", ES6Iterator.VALUE_PROPERTY, "Landroidx/media3/exoplayer/ExoPlayer;", "exoPlayer", "setExoPlayer", "(Landroidx/media3/exoplayer/ExoPlayer;)V", "cacheSize", "", "getCacheSize", "()J", "setCacheSize", "(J)V", "simpleCacheSize", "getSimpleCacheSize", "setSimpleCacheSize", "videoBufferMs", "getVideoBufferMs", "setVideoBufferMs", "imageGenerator", "Lcom/lagradost/cloudstream3/ui/player/IPreviewGenerator;", "getImageGenerator", "()Lcom/lagradost/cloudstream3/ui/player/IPreviewGenerator;", "seekActionTime", "isMediaSeekable", "()Z", "ignoreSSL", "playBackSpeed", "", "lastMuteVolume", "currentLink", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "currentDownloadedFile", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "hasUsedFirstRender", "currentWindow", "", "playbackPosition", "subtitleHelper", "Lcom/lagradost/cloudstream3/ui/player/PlayerSubtitleHelper;", "isAudioOnlyBackground", "getDuration", "()Ljava/lang/Long;", "getPosition", "getIsPlaying", "getPlaybackSpeed", "playerSelectedSubtitleTracks", "", "Lkotlin/Pair;", "", "requestedListeningPercentages", "eventHandler", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", "", NotificationCompat.CATEGORY_EVENT, "isPlayerActive", "setPlayerActive", "(Z)V", "releaseCallbacks", "initCallbacks", "stripTrackId", "initSubtitles", "subView", "Landroidx/media3/ui/SubtitleView;", "subHolder", "Landroid/widget/FrameLayout;", "style", "Lcom/lagradost/cloudstream3/ui/subtitles/SaveCaptionStyle;", "getPreview", "Landroid/graphics/Bitmap;", "fraction", "hasPreview", "loadPlayer", "context", "Landroid/content/Context;", "sameEpisode", "link", "data", "startPosition", "subtitles", "", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", MediaTrack.ROLE_SUBTITLE, "autoPlay", "preview", "(Landroid/content/Context;ZLcom/lagradost/cloudstream3/utils/ExtractorLink;Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;Ljava/lang/Long;Ljava/util/Set;Lcom/lagradost/cloudstream3/ui/player/SubtitleData;Ljava/lang/Boolean;Z)V", "setActiveSubtitles", "currentSubtitles", "getTrack", "Landroidx/media3/common/TrackGroup;", "Landroidx/media3/common/Tracks$Group;", "id", "setMaxVideoSize", "width", "height", "setPreferredAudioTrack", "trackLanguage", "formatIndex", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getFormats", "Landroidx/media3/common/Format;", "toAudioTrack", "Lcom/lagradost/cloudstream3/ui/player/AudioTrack;", "(Landroidx/media3/common/Format;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/ui/player/AudioTrack;", "toSubtitleTrack", "Lcom/lagradost/cloudstream3/ui/player/TextTrack;", "toVideoTrack", "Lcom/lagradost/cloudstream3/ui/player/VideoTrack;", "getVideoTracks", "Lcom/lagradost/cloudstream3/ui/player/CurrentTracks;", "setPreferredSubtitles", "currentSubtitleOffset", "setSubtitleOffset", TypedValues.CycleType.S_WAVE_OFFSET, "getSubtitleOffset", "getSubtitleCues", "Lcom/lagradost/cloudstream3/ui/player/SubtitleCue;", "getCurrentPreferredSubtitle", "getAspectRatio", "Landroid/util/Rational;", "updateSubtitleStyle", "saveData", "releasePlayer", "saveTime", "onStop", "onPause", "onResume", "release", "setPlaybackSpeed", "speed", "getCurrentTimestamp", "Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;", "writePosition", "(Ljava/lang/Long;)Lcom/lagradost/cloudstream3/utils/videoskip/VideoSkipStamp;", "updatedTime", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "(Ljava/lang/Long;Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;)V", "seekTime", "time", "seekTo", "handleEvent", "Lcom/lagradost/cloudstream3/ui/player/CSPlayerEvent;", "eventLooperIndex", "torrentEventLooper", "Lkotlinx/coroutines/Job;", "hash", "buildExoPlayer", "mediaItemSlices", "Lcom/lagradost/cloudstream3/ui/player/CS3IPlayer$MediaItemSlice;", "subSources", "Landroidx/media3/exoplayer/source/SingleSampleMediaSource;", "subtitleOffset", "onlineSource", "Landroidx/media3/datasource/HttpDataSource$Factory;", "playWhenReady", "trackSelector", "Landroidx/media3/exoplayer/trackselection/TrackSelector;", "maxVideoHeight", "audioSources", "Landroidx/media3/exoplayer/source/MediaSource;", "(Landroid/content/Context;Ljava/util/List;Ljava/util/List;IJFJJJLandroidx/media3/datasource/HttpDataSource$Factory;ZLandroidx/media3/exoplayer/trackselection/TrackSelector;Ljava/lang/Integer;Ljava/util/List;)Landroidx/media3/exoplayer/ExoPlayer;", "loadExo", "mediaSlices", "lastTimeStamps", "addTimeStamps", "timeStamps", "onRenderFirst", "loadOfflinePlayer", "getSubSources", "offlineSourceFactory", "Landroidx/media3/datasource/DataSource$Factory;", "subHelper", "interceptor", "Lokhttp3/Interceptor;", "getAudioSources", "audioTracks", "Lcom/lagradost/cloudstream3/AudioFile;", "isActive", "loadTorrent", "loadOnlinePlayer", "retry", "reloadPlayer", "tracksAnalyticsListener", "com/lagradost/cloudstream3/ui/player/CS3IPlayer$tracksAnalyticsListener$1", "Lcom/lagradost/cloudstream3/ui/player/CS3IPlayer$tracksAnalyticsListener$1;", "MediaItemSlice", "DrmMetadata", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CS3IPlayer implements IPlayer {
    private static final int CRONET_TIMEOUT_MS = 15000;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile int activePlayers;
    private static CronetEngine cronetEngine;
    private static volatile int cronetReleasedId;
    private static CustomSubtitleDecoderFactory currentSubtitleDecoder;
    private static TextRenderer currentTextRenderer;
    private static String preferredAudioTrackLanguage;
    private static SimpleCache simpleCache;
    private long cacheSize;
    private ExtractorUri currentDownloadedFile;
    private ExtractorLink currentLink;
    private long currentSubtitleOffset;
    private SubtitleData currentSubtitles;
    private int currentWindow;
    private Function1<? super PlayerEvent, Unit> eventHandler;
    private int eventLooperIndex;
    private ExoPlayer exoPlayer;
    private boolean hasUsedFirstRender;
    private boolean isAudioOnlyBackground;
    private volatile boolean isPlayerActive;
    private boolean isPlaying;
    private long playbackPosition;
    private Player.Listener playerListener;
    private List<Integer> requestedListeningPercentages;
    private long simpleCacheSize;
    private long videoBufferMs;
    private final IPreviewGenerator imageGenerator = IPreviewGenerator.INSTANCE.m8511new();
    private final long seekActionTime = 30000;
    private boolean ignoreSSL = true;
    private float playBackSpeed = 1.0f;
    private float lastMuteVolume = 1.0f;
    private final PlayerSubtitleHelper subtitleHelper = new PlayerSubtitleHelper();
    private List<Pair<String, Boolean>> playerSelectedSubtitleTracks = CollectionsKt.emptyList();
    private List<VideoSkipStamp> lastTimeStamps = CollectionsKt.emptyList();
    private final CS3IPlayer$tracksAnalyticsListener$1 tracksAnalyticsListener = new AnalyticsListener() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$tracksAnalyticsListener$1
        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioAttributesChanged(AnalyticsListener.EventTime eventTime, AudioAttributes audioAttributes) {
            AnalyticsListener.CC.$default$onAudioAttributesChanged(this, eventTime, audioAttributes);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
            AnalyticsListener.CC.$default$onAudioCodecError(this, eventTime, exc);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j) {
            AnalyticsListener.CC.$default$onAudioDecoderInitialized(this, eventTime, str, j);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j, long j2) {
            AnalyticsListener.CC.$default$onAudioDecoderInitialized(this, eventTime, str, j, j2);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
            AnalyticsListener.CC.$default$onAudioDecoderReleased(this, eventTime, str);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
            AnalyticsListener.CC.$default$onAudioEnabled(this, eventTime, decoderCounters);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioPositionAdvancing(AnalyticsListener.EventTime eventTime, long j) {
            AnalyticsListener.CC.$default$onAudioPositionAdvancing(this, eventTime, j);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioSessionIdChanged(AnalyticsListener.EventTime eventTime, int i) {
            AnalyticsListener.CC.$default$onAudioSessionIdChanged(this, eventTime, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioSinkError(AnalyticsListener.EventTime eventTime, Exception exc) {
            AnalyticsListener.CC.$default$onAudioSinkError(this, eventTime, exc);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioTrackInitialized(AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
            AnalyticsListener.CC.$default$onAudioTrackInitialized(this, eventTime, audioTrackConfig);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioTrackReleased(AnalyticsListener.EventTime eventTime, AudioSink.AudioTrackConfig audioTrackConfig) {
            AnalyticsListener.CC.$default$onAudioTrackReleased(this, eventTime, audioTrackConfig);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAudioUnderrun(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
            AnalyticsListener.CC.$default$onAudioUnderrun(this, eventTime, i, j, j2);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onAvailableCommandsChanged(AnalyticsListener.EventTime eventTime, Player.Commands commands) {
            AnalyticsListener.CC.$default$onAvailableCommandsChanged(this, eventTime, commands);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
            AnalyticsListener.CC.$default$onBandwidthEstimate(this, eventTime, i, j, j2);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onCues(AnalyticsListener.EventTime eventTime, CueGroup cueGroup) {
            AnalyticsListener.CC.$default$onCues(this, eventTime, cueGroup);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onCues(AnalyticsListener.EventTime eventTime, List list) {
            AnalyticsListener.CC.$default$onCues(this, eventTime, list);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDeviceInfoChanged(AnalyticsListener.EventTime eventTime, DeviceInfo deviceInfo) {
            AnalyticsListener.CC.$default$onDeviceInfoChanged(this, eventTime, deviceInfo);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDeviceVolumeChanged(AnalyticsListener.EventTime eventTime, int i, boolean z) {
            AnalyticsListener.CC.$default$onDeviceVolumeChanged(this, eventTime, i, z);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
            AnalyticsListener.CC.$default$onDownstreamFormatChanged(this, eventTime, mediaLoadData);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDrmKeysLoaded(AnalyticsListener.EventTime eventTime) {
            AnalyticsListener.CC.$default$onDrmKeysLoaded(this, eventTime);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDrmKeysLoaded(AnalyticsListener.EventTime eventTime, KeyRequestInfo keyRequestInfo) {
            AnalyticsListener.CC.$default$onDrmKeysLoaded(this, eventTime, keyRequestInfo);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDrmKeysRemoved(AnalyticsListener.EventTime eventTime) {
            AnalyticsListener.CC.$default$onDrmKeysRemoved(this, eventTime);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDrmKeysRestored(AnalyticsListener.EventTime eventTime) {
            AnalyticsListener.CC.$default$onDrmKeysRestored(this, eventTime);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime) {
            AnalyticsListener.CC.$default$onDrmSessionAcquired(this, eventTime);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime, int i) {
            AnalyticsListener.CC.$default$onDrmSessionAcquired(this, eventTime, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDrmSessionManagerError(AnalyticsListener.EventTime eventTime, Exception exc) {
            AnalyticsListener.CC.$default$onDrmSessionManagerError(this, eventTime, exc);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDrmSessionReleased(AnalyticsListener.EventTime eventTime) {
            AnalyticsListener.CC.$default$onDrmSessionReleased(this, eventTime);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDroppedSeeksWhileScrubbing(AnalyticsListener.EventTime eventTime, int i) {
            AnalyticsListener.CC.$default$onDroppedSeeksWhileScrubbing(this, eventTime, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onDroppedVideoFrames(AnalyticsListener.EventTime eventTime, int i, long j) {
            AnalyticsListener.CC.$default$onDroppedVideoFrames(this, eventTime, i, j);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onEvents(Player player, AnalyticsListener.Events events) {
            AnalyticsListener.CC.$default$onEvents(this, player, events);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onIsLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
            AnalyticsListener.CC.$default$onIsLoadingChanged(this, eventTime, z);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onIsPlayingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
            AnalyticsListener.CC.$default$onIsPlayingChanged(this, eventTime, z);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onLoadCanceled(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            AnalyticsListener.CC.$default$onLoadCanceled(this, eventTime, loadEventInfo, mediaLoadData);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onLoadCompleted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            AnalyticsListener.CC.$default$onLoadCompleted(this, eventTime, loadEventInfo, mediaLoadData);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            AnalyticsListener.CC.$default$onLoadError(this, eventTime, loadEventInfo, mediaLoadData, iOException, z);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onLoadStarted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            AnalyticsListener.CC.$default$onLoadStarted(this, eventTime, loadEventInfo, mediaLoadData);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onLoadStarted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i) {
            AnalyticsListener.CC.$default$onLoadStarted(this, eventTime, loadEventInfo, mediaLoadData, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
            AnalyticsListener.CC.$default$onLoadingChanged(this, eventTime, z);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onMaxSeekToPreviousPositionChanged(AnalyticsListener.EventTime eventTime, long j) {
            AnalyticsListener.CC.$default$onMaxSeekToPreviousPositionChanged(this, eventTime, j);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onMediaItemTransition(AnalyticsListener.EventTime eventTime, MediaItem mediaItem, int i) {
            AnalyticsListener.CC.$default$onMediaItemTransition(this, eventTime, mediaItem, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onMediaMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
            AnalyticsListener.CC.$default$onMediaMetadataChanged(this, eventTime, mediaMetadata);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onMetadata(AnalyticsListener.EventTime eventTime, androidx.media3.common.Metadata metadata) {
            AnalyticsListener.CC.$default$onMetadata(this, eventTime, metadata);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onPlayWhenReadyChanged(AnalyticsListener.EventTime eventTime, boolean z, int i) {
            AnalyticsListener.CC.$default$onPlayWhenReadyChanged(this, eventTime, z, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onPlaybackParametersChanged(AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
            AnalyticsListener.CC.$default$onPlaybackParametersChanged(this, eventTime, playbackParameters);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onPlaybackStateChanged(AnalyticsListener.EventTime eventTime, int i) {
            AnalyticsListener.CC.$default$onPlaybackStateChanged(this, eventTime, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onPlaybackSuppressionReasonChanged(AnalyticsListener.EventTime eventTime, int i) {
            AnalyticsListener.CC.$default$onPlaybackSuppressionReasonChanged(this, eventTime, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onPlayerError(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
            AnalyticsListener.CC.$default$onPlayerError(this, eventTime, playbackException);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onPlayerErrorChanged(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
            AnalyticsListener.CC.$default$onPlayerErrorChanged(this, eventTime, playbackException);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onPlayerReleased(AnalyticsListener.EventTime eventTime) {
            AnalyticsListener.CC.$default$onPlayerReleased(this, eventTime);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onPlayerStateChanged(AnalyticsListener.EventTime eventTime, boolean z, int i) {
            AnalyticsListener.CC.$default$onPlayerStateChanged(this, eventTime, z, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onPlaylistMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
            AnalyticsListener.CC.$default$onPlaylistMetadataChanged(this, eventTime, mediaMetadata);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, int i) {
            AnalyticsListener.CC.$default$onPositionDiscontinuity(this, eventTime, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            AnalyticsListener.CC.$default$onPositionDiscontinuity(this, eventTime, positionInfo, positionInfo2, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onRenderedFirstFrame(AnalyticsListener.EventTime eventTime, Object obj, long j) {
            AnalyticsListener.CC.$default$onRenderedFirstFrame(this, eventTime, obj, j);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onRendererReadyChanged(AnalyticsListener.EventTime eventTime, int i, int i2, boolean z) {
            AnalyticsListener.CC.$default$onRendererReadyChanged(this, eventTime, i, i2, z);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onRepeatModeChanged(AnalyticsListener.EventTime eventTime, int i) {
            AnalyticsListener.CC.$default$onRepeatModeChanged(this, eventTime, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onSeekBackIncrementChanged(AnalyticsListener.EventTime eventTime, long j) {
            AnalyticsListener.CC.$default$onSeekBackIncrementChanged(this, eventTime, j);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onSeekForwardIncrementChanged(AnalyticsListener.EventTime eventTime, long j) {
            AnalyticsListener.CC.$default$onSeekForwardIncrementChanged(this, eventTime, j);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onSeekStarted(AnalyticsListener.EventTime eventTime) {
            AnalyticsListener.CC.$default$onSeekStarted(this, eventTime);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onShuffleModeChanged(AnalyticsListener.EventTime eventTime, boolean z) {
            AnalyticsListener.CC.$default$onShuffleModeChanged(this, eventTime, z);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onSkipSilenceEnabledChanged(AnalyticsListener.EventTime eventTime, boolean z) {
            AnalyticsListener.CC.$default$onSkipSilenceEnabledChanged(this, eventTime, z);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onSurfaceSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2) {
            AnalyticsListener.CC.$default$onSurfaceSizeChanged(this, eventTime, i, i2);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onTimelineChanged(AnalyticsListener.EventTime eventTime, int i) {
            AnalyticsListener.CC.$default$onTimelineChanged(this, eventTime, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onTrackSelectionParametersChanged(AnalyticsListener.EventTime eventTime, TrackSelectionParameters trackSelectionParameters) {
            AnalyticsListener.CC.$default$onTrackSelectionParametersChanged(this, eventTime, trackSelectionParameters);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onTracksChanged(AnalyticsListener.EventTime eventTime, Tracks tracks) {
            AnalyticsListener.CC.$default$onTracksChanged(this, eventTime, tracks);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onUpstreamDiscarded(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
            AnalyticsListener.CC.$default$onUpstreamDiscarded(this, eventTime, mediaLoadData);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onVideoCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
            AnalyticsListener.CC.$default$onVideoCodecError(this, eventTime, exc);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j) {
            AnalyticsListener.CC.$default$onVideoDecoderInitialized(this, eventTime, str, j);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j, long j2) {
            AnalyticsListener.CC.$default$onVideoDecoderInitialized(this, eventTime, str, j, j2);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onVideoDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
            AnalyticsListener.CC.$default$onVideoDecoderReleased(this, eventTime, str);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onVideoEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
            AnalyticsListener.CC.$default$onVideoEnabled(this, eventTime, decoderCounters);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onVideoFrameProcessingOffset(AnalyticsListener.EventTime eventTime, long j, int i) {
            AnalyticsListener.CC.$default$onVideoFrameProcessingOffset(this, eventTime, j, i);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2, int i3, float f) {
            AnalyticsListener.CC.$default$onVideoSizeChanged(this, eventTime, i, i2, i3, f);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
            AnalyticsListener.CC.$default$onVideoSizeChanged(this, eventTime, videoSize);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public /* synthetic */ void onVolumeChanged(AnalyticsListener.EventTime eventTime, float f) {
            AnalyticsListener.CC.$default$onVolumeChanged(this, eventTime, f);
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public void onVideoInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            Intrinsics.checkNotNullParameter(eventTime, "eventTime");
            Intrinsics.checkNotNullParameter(format, "format");
            this.this$0.event(new TracksChangedEvent(null, 1, null));
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public void onAudioInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            Intrinsics.checkNotNullParameter(eventTime, "eventTime");
            Intrinsics.checkNotNullParameter(format, "format");
            this.this$0.event(new TracksChangedEvent(null, 1, null));
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public void onVideoDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
            Intrinsics.checkNotNullParameter(eventTime, "eventTime");
            Intrinsics.checkNotNullParameter(decoderCounters, "decoderCounters");
            this.this$0.event(new TracksChangedEvent(null, 1, null));
        }

        @Override // androidx.media3.exoplayer.analytics.AnalyticsListener
        public void onAudioDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
            Intrinsics.checkNotNullParameter(eventTime, "eventTime");
            Intrinsics.checkNotNullParameter(decoderCounters, "decoderCounters");
            this.this$0.event(new TracksChangedEvent(null, 1, null));
        }
    };

    /* JADX INFO: compiled from: CS3IPlayer.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            int[] iArr = new int[SubtitleStatus.values().length];
            try {
                iArr[SubtitleStatus.REQUIRES_RELOAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SubtitleStatus.NOT_FOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SubtitleStatus.IS_ACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CSPlayerEvent.values().length];
            try {
                iArr2[CSPlayerEvent.Play.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[CSPlayerEvent.Pause.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[CSPlayerEvent.ToggleMute.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[CSPlayerEvent.PlayPauseToggle.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[CSPlayerEvent.SeekForward.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[CSPlayerEvent.SeekBack.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[CSPlayerEvent.Restart.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[CSPlayerEvent.NextEpisode.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[CSPlayerEvent.PrevEpisode.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr2[CSPlayerEvent.SkipCurrentChapter.ordinal()] = 10;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr2[CSPlayerEvent.PlayAsAudio.ordinal()] = 11;
            } catch (NoSuchFieldError unused14) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[SubtitleOrigin.values().length];
            try {
                iArr3[SubtitleOrigin.DOWNLOADED_FILE.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr3[SubtitleOrigin.EMBEDDED_IN_VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr3[SubtitleOrigin.URL.ordinal()] = 3;
            } catch (NoSuchFieldError unused17) {
            }
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[ExtractorLinkType.values().length];
            try {
                iArr4[ExtractorLinkType.M3U8.ordinal()] = 1;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr4[ExtractorLinkType.DASH.ordinal()] = 2;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr4[ExtractorLinkType.VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr4[ExtractorLinkType.TORRENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr4[ExtractorLinkType.MAGNET.ordinal()] = 5;
            } catch (NoSuchFieldError unused22) {
            }
            $EnumSwitchMapping$3 = iArr4;
        }
    }

    static final boolean loadOnlinePlayer$lambda$6(String str, SSLSession sSLSession) {
        Intrinsics.checkNotNullParameter(str, "<unused var>");
        Intrinsics.checkNotNullParameter(sSLSession, "<unused var>");
        return true;
    }

    public final long getCacheSize() {
        return this.cacheSize;
    }

    public final void setCacheSize(long j) {
        this.cacheSize = j;
    }

    public final long getSimpleCacheSize() {
        return this.simpleCacheSize;
    }

    public final void setSimpleCacheSize(long j) {
        this.simpleCacheSize = j;
    }

    public final long getVideoBufferMs() {
        return this.videoBufferMs;
    }

    public final void setVideoBufferMs(long j) {
        this.videoBufferMs = j;
    }

    public final IPreviewGenerator getImageGenerator() {
        return this.imageGenerator;
    }

    private final boolean isMediaSeekable() {
        ExoPlayer exoPlayer = this.exoPlayer;
        return exoPlayer != null && exoPlayer.isCommandAvailable(16) && exoPlayer.isCurrentMediaItemSeekable();
    }

    /* JADX INFO: compiled from: CS3IPlayer.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/CS3IPlayer$MediaItemSlice;", "", "mediaItem", "Landroidx/media3/common/MediaItem;", "durationUs", "", "drm", "Lcom/lagradost/cloudstream3/ui/player/CS3IPlayer$DrmMetadata;", "<init>", "(Landroidx/media3/common/MediaItem;JLcom/lagradost/cloudstream3/ui/player/CS3IPlayer$DrmMetadata;)V", "getMediaItem", "()Landroidx/media3/common/MediaItem;", "getDurationUs", "()J", "getDrm", "()Lcom/lagradost/cloudstream3/ui/player/CS3IPlayer$DrmMetadata;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class MediaItemSlice {
        private final DrmMetadata drm;
        private final long durationUs;
        private final MediaItem mediaItem;

        public static /* synthetic */ MediaItemSlice copy$default(MediaItemSlice mediaItemSlice, MediaItem mediaItem, long j, DrmMetadata drmMetadata, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaItem = mediaItemSlice.mediaItem;
            }
            if ((i & 2) != 0) {
                j = mediaItemSlice.durationUs;
            }
            if ((i & 4) != 0) {
                drmMetadata = mediaItemSlice.drm;
            }
            return mediaItemSlice.copy(mediaItem, j, drmMetadata);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final MediaItem getMediaItem() {
            return this.mediaItem;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getDurationUs() {
            return this.durationUs;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final DrmMetadata getDrm() {
            return this.drm;
        }

        public final MediaItemSlice copy(MediaItem mediaItem, long durationUs, DrmMetadata drm) {
            Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
            return new MediaItemSlice(mediaItem, durationUs, drm);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MediaItemSlice)) {
                return false;
            }
            MediaItemSlice mediaItemSlice = (MediaItemSlice) other;
            return Intrinsics.areEqual(this.mediaItem, mediaItemSlice.mediaItem) && this.durationUs == mediaItemSlice.durationUs && Intrinsics.areEqual(this.drm, mediaItemSlice.drm);
        }

        public int hashCode() {
            int iHashCode = ((this.mediaItem.hashCode() * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.durationUs)) * 31;
            DrmMetadata drmMetadata = this.drm;
            return iHashCode + (drmMetadata == null ? 0 : drmMetadata.hashCode());
        }

        public String toString() {
            return "MediaItemSlice(mediaItem=" + this.mediaItem + ", durationUs=" + this.durationUs + ", drm=" + this.drm + ')';
        }

        public MediaItemSlice(MediaItem mediaItem, long j, DrmMetadata drmMetadata) {
            Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
            this.mediaItem = mediaItem;
            this.durationUs = j;
            this.drm = drmMetadata;
        }

        public /* synthetic */ MediaItemSlice(MediaItem mediaItem, long j, DrmMetadata drmMetadata, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(mediaItem, j, (i & 4) != 0 ? null : drmMetadata);
        }

        public final MediaItem getMediaItem() {
            return this.mediaItem;
        }

        public final long getDurationUs() {
            return this.durationUs;
        }

        public final DrmMetadata getDrm() {
            return this.drm;
        }
    }

    /* JADX INFO: compiled from: CS3IPlayer.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bc\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\"\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u000b¢\u0006\u0004\b\f\u0010\rJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J%\u0010\u001c\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u000bHÆ\u0003Ji\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032$\b\u0002\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u000bHÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR-\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nj\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/CS3IPlayer$DrmMetadata;", "", "kid", "", "key", "uuid", "Ljava/util/UUID;", "kty", "licenseUrl", "keyRequestParameters", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/UUID;Ljava/lang/String;Ljava/lang/String;Ljava/util/HashMap;)V", "getKid", "()Ljava/lang/String;", "getKey", "getUuid", "()Ljava/util/UUID;", "getKty", "getLicenseUrl", "getKeyRequestParameters", "()Ljava/util/HashMap;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DrmMetadata {
        private final String key;
        private final HashMap<String, String> keyRequestParameters;
        private final String kid;
        private final String kty;
        private final String licenseUrl;
        private final UUID uuid;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DrmMetadata copy$default(DrmMetadata drmMetadata, String str, String str2, UUID uuid, String str3, String str4, HashMap map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = drmMetadata.kid;
            }
            if ((i & 2) != 0) {
                str2 = drmMetadata.key;
            }
            if ((i & 4) != 0) {
                uuid = drmMetadata.uuid;
            }
            if ((i & 8) != 0) {
                str3 = drmMetadata.kty;
            }
            if ((i & 16) != 0) {
                str4 = drmMetadata.licenseUrl;
            }
            if ((i & 32) != 0) {
                map = drmMetadata.keyRequestParameters;
            }
            String str5 = str4;
            HashMap map2 = map;
            return drmMetadata.copy(str, str2, uuid, str3, str5, map2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getKid() {
            return this.kid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getKey() {
            return this.key;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final UUID getUuid() {
            return this.uuid;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getKty() {
            return this.kty;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getLicenseUrl() {
            return this.licenseUrl;
        }

        public final HashMap<String, String> component6() {
            return this.keyRequestParameters;
        }

        public final DrmMetadata copy(String kid, String key, UUID uuid, String kty, String licenseUrl, HashMap<String, String> keyRequestParameters) {
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Intrinsics.checkNotNullParameter(keyRequestParameters, "keyRequestParameters");
            return new DrmMetadata(kid, key, uuid, kty, licenseUrl, keyRequestParameters);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DrmMetadata)) {
                return false;
            }
            DrmMetadata drmMetadata = (DrmMetadata) other;
            return Intrinsics.areEqual(this.kid, drmMetadata.kid) && Intrinsics.areEqual(this.key, drmMetadata.key) && Intrinsics.areEqual(this.uuid, drmMetadata.uuid) && Intrinsics.areEqual(this.kty, drmMetadata.kty) && Intrinsics.areEqual(this.licenseUrl, drmMetadata.licenseUrl) && Intrinsics.areEqual(this.keyRequestParameters, drmMetadata.keyRequestParameters);
        }

        public int hashCode() {
            String str = this.kid;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.key;
            int iHashCode2 = (((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + this.uuid.hashCode()) * 31;
            String str3 = this.kty;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.licenseUrl;
            return ((iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + this.keyRequestParameters.hashCode();
        }

        public String toString() {
            return "DrmMetadata(kid=" + this.kid + ", key=" + this.key + ", uuid=" + this.uuid + ", kty=" + this.kty + ", licenseUrl=" + this.licenseUrl + ", keyRequestParameters=" + this.keyRequestParameters + ')';
        }

        public DrmMetadata(String str, String str2, UUID uuid, String str3, String str4, HashMap<String, String> keyRequestParameters) {
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Intrinsics.checkNotNullParameter(keyRequestParameters, "keyRequestParameters");
            this.kid = str;
            this.key = str2;
            this.uuid = uuid;
            this.kty = str3;
            this.licenseUrl = str4;
            this.keyRequestParameters = keyRequestParameters;
        }

        public /* synthetic */ DrmMetadata(String str, String str2, UUID uuid, String str3, String str4, HashMap map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, uuid, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, map);
        }

        public final String getKid() {
            return this.kid;
        }

        public final String getKey() {
            return this.key;
        }

        public final UUID getUuid() {
            return this.uuid;
        }

        public final String getKty() {
            return this.kty;
        }

        public final String getLicenseUrl() {
            return this.licenseUrl;
        }

        public final HashMap<String, String> getKeyRequestParameters() {
            return this.keyRequestParameters;
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public Long getDuration() {
        ExoPlayer exoPlayer = this.exoPlayer;
        if (exoPlayer != null) {
            return Long.valueOf(exoPlayer.getDuration());
        }
        return null;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public Long getPosition() {
        ExoPlayer exoPlayer = this.exoPlayer;
        if (exoPlayer != null) {
            return Long.valueOf(exoPlayer.getCurrentPosition());
        }
        return null;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public boolean getIsPlaying() {
        return this.isPlaying;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    /* JADX INFO: renamed from: getPlaybackSpeed, reason: from getter */
    public float getPlayBackSpeed() {
        return this.playBackSpeed;
    }

    public final void event(final PlayerEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Looper.getMainLooper().isCurrentThread()) {
            Function1<? super PlayerEvent, Unit> function1 = this.eventHandler;
            if (function1 != null) {
                function1.invoke(event);
                return;
            }
            return;
        }
        Coroutines.INSTANCE.runOnMainThread(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CS3IPlayer.event$lambda$0(this.f$0, event);
            }
        });
    }

    static final Unit event$lambda$0(CS3IPlayer cS3IPlayer, PlayerEvent playerEvent) {
        Function1<? super PlayerEvent, Unit> function1 = cS3IPlayer.eventHandler;
        if (function1 != null) {
            function1.invoke(playerEvent);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: isPlayerActive, reason: from getter */
    public final boolean getIsPlayerActive() {
        return this.isPlayerActive;
    }

    public final void setPlayerActive(boolean z) {
        this.isPlayerActive = z;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void releaseCallbacks() {
        this.eventHandler = null;
        if (this.isPlayerActive) {
            this.isPlayerActive = false;
            activePlayers--;
            INSTANCE.releaseCronetEngine();
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void initCallbacks(Function1<? super PlayerEvent, Unit> eventHandler, List<Integer> requestedListeningPercentages) {
        Intrinsics.checkNotNullParameter(eventHandler, "eventHandler");
        this.requestedListeningPercentages = requestedListeningPercentages;
        this.eventHandler = eventHandler;
        if (this.isPlayerActive) {
            return;
        }
        this.isPlayerActive = true;
        activePlayers++;
    }

    public final String stripTrackId(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return new Regex("^\\d+:").replace(str, "");
    }

    public final void initSubtitles(SubtitleView subView, FrameLayout subHolder, SaveCaptionStyle style) {
        this.subtitleHelper.initSubtitles(subView, subHolder, style);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public Bitmap getPreview(float fraction) {
        return this.imageGenerator.getPreviewImage(fraction);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public boolean hasPreview() {
        ExoPlayer exoPlayer = this.exoPlayer;
        if (exoPlayer == null || !exoPlayer.isCurrentMediaItemDynamic()) {
            return this.imageGenerator.hasPreview();
        }
        return false;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void loadPlayer(Context context, boolean sameEpisode, ExtractorLink link, ExtractorUri data, Long startPosition, Set<SubtitleData> subtitles, SubtitleData subtitle, Boolean autoPlay, boolean preview) throws DebugException {
        PreviewGenerator previewGenerator;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        Log.i(CS3IPlayerKt.TAG, "loadPlayer");
        if (sameEpisode) {
            saveData();
        } else {
            this.currentSubtitles = subtitle;
            this.playbackPosition = 0L;
        }
        if (startPosition != null) {
            this.playbackPosition = startPosition.longValue();
        }
        this.isPlaying = autoPlay != null ? autoPlay.booleanValue() : this.isPlaying;
        releasePlayer$default(this, false, 1, null);
        if (link != null) {
            IPreviewGenerator iPreviewGenerator = this.imageGenerator;
            previewGenerator = iPreviewGenerator instanceof PreviewGenerator ? (PreviewGenerator) iPreviewGenerator : null;
            if (previewGenerator != null) {
                if (preview) {
                    previewGenerator.load(link, sameEpisode);
                } else {
                    previewGenerator.clear(sameEpisode);
                }
            }
            loadOnlinePlayer$default(this, context, link, false, 4, null);
            return;
        }
        if (data != null) {
            IPreviewGenerator iPreviewGenerator2 = this.imageGenerator;
            previewGenerator = iPreviewGenerator2 instanceof PreviewGenerator ? (PreviewGenerator) iPreviewGenerator2 : null;
            if (previewGenerator != null) {
                if (preview) {
                    previewGenerator.load(context, data, sameEpisode);
                } else {
                    previewGenerator.clear(sameEpisode);
                }
            }
            loadOfflinePlayer(context, data);
            return;
        }
        throw new IllegalArgumentException("Requires link or uri");
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void setActiveSubtitles(Set<SubtitleData> subtitles) {
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        Log.i(CS3IPlayerKt.TAG, "setActiveSubtitles " + subtitles.size());
        this.subtitleHelper.setAllSubtitles(subtitles);
    }

    private final Pair<TrackGroup, Integer> getTrack(List<Tracks.Group> list, String str) {
        Object next;
        String str2;
        if (str == null) {
            return null;
        }
        for (Tracks.Group group : list) {
            IntRange intRangeUntil = RangesKt.until(0, group.getMediaTrackGroup().length);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
            Iterator<Integer> it = intRangeUntil.iterator();
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                arrayList.add(TuplesKt.to(group.getTrackFormat(iNextInt), Integer.valueOf(iNextInt)));
            }
            Iterator it2 = arrayList.iterator();
            do {
                if (!it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
                str2 = ((Format) ((Pair) next).getFirst()).id;
            } while (!Intrinsics.areEqual(str2 != null ? stripTrackId(str2) : null, str));
            Pair pair = (Pair) next;
            Pair<TrackGroup, Integer> pair2 = pair != null ? TuplesKt.to(group.getMediaTrackGroup(), pair.getSecond()) : null;
            if (pair2 != null) {
                return pair2;
            }
        }
        return null;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void setMaxVideoSize(int width, int height, String id) {
        TrackSelectionParameters trackSelectionParameters;
        TrackSelectionParameters.Builder builderBuildUpon;
        TrackSelectionParameters.Builder maxVideoSize;
        TrackSelectionParameters trackSelectionParametersBuild;
        Pair<TrackGroup, Integer> track;
        TrackSelectionParameters trackSelectionParameters2;
        TrackSelectionParameters.Builder builderBuildUpon2;
        TrackSelectionParameters.Builder overrideForType;
        TrackSelectionParameters trackSelectionParametersBuild2;
        Tracks currentTracks;
        ImmutableList<Tracks.Group> groups;
        if (id != null) {
            ExoPlayer exoPlayer = this.exoPlayer;
            if (exoPlayer == null || (currentTracks = exoPlayer.getCurrentTracks()) == null || (groups = currentTracks.getGroups()) == null) {
                track = null;
            } else {
                ArrayList arrayList = new ArrayList();
                for (Tracks.Group group : groups) {
                    if (group.getType() == 2) {
                        arrayList.add(group);
                    }
                }
                track = getTrack(arrayList, id);
            }
            if (track != null) {
                ExoPlayer exoPlayer2 = this.exoPlayer;
                if (exoPlayer2 == null || exoPlayer2 == null || (trackSelectionParameters2 = exoPlayer2.getTrackSelectionParameters()) == null || (builderBuildUpon2 = trackSelectionParameters2.buildUpon()) == null || (overrideForType = builderBuildUpon2.setOverrideForType(new TrackSelectionOverride(track.getFirst(), track.getSecond().intValue()))) == null || (trackSelectionParametersBuild2 = overrideForType.build()) == null) {
                    return;
                }
                exoPlayer2.setTrackSelectionParameters(trackSelectionParametersBuild2);
                return;
            }
        }
        ExoPlayer exoPlayer3 = this.exoPlayer;
        if (exoPlayer3 == null || exoPlayer3 == null || (trackSelectionParameters = exoPlayer3.getTrackSelectionParameters()) == null || (builderBuildUpon = trackSelectionParameters.buildUpon()) == null || (maxVideoSize = builderBuildUpon.setMaxVideoSize(width, height)) == null || (trackSelectionParametersBuild = maxVideoSize.build()) == null) {
            return;
        }
        exoPlayer3.setTrackSelectionParameters(trackSelectionParametersBuild);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void setPreferredAudioTrack(String trackLanguage, String id, Integer formatIndex) {
        TrackSelectionParameters trackSelectionParameters;
        TrackSelectionParameters.Builder builderBuildUpon;
        TrackSelectionParameters.Builder preferredAudioLanguage;
        TrackSelectionParameters trackSelectionParametersBuild;
        Tracks currentTracks;
        ImmutableList<Tracks.Group> groups;
        TrackSelectionParameters trackSelectionParametersBuild2;
        Object next;
        TrackSelectionParameters trackSelectionParameters2;
        TrackSelectionParameters.Builder builderBuildUpon2;
        TrackSelectionParameters.Builder overrideForType;
        INSTANCE.setPreferredAudioTrackLanguage(trackLanguage);
        if (id != null) {
            int iIntValue = formatIndex != null ? formatIndex.intValue() : 0;
            ExoPlayer exoPlayer = this.exoPlayer;
            if (exoPlayer != null && (currentTracks = exoPlayer.getCurrentTracks()) != null && (groups = currentTracks.getGroups()) != null) {
                ArrayList arrayList = new ArrayList();
                for (Tracks.Group group : groups) {
                    if (group.getType() == 1) {
                        arrayList.add(group);
                    }
                }
                Iterator it = arrayList.iterator();
                loop1: while (true) {
                    trackSelectionParametersBuild2 = null;
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    Tracks.Group group2 = (Tracks.Group) next;
                    Intrinsics.checkNotNull(group2);
                    List<Pair<Format, Integer>> formats = getFormats(group2);
                    if (!(formats instanceof Collection) || !formats.isEmpty()) {
                        Iterator<T> it2 = formats.iterator();
                        while (it2.hasNext()) {
                            if (Intrinsics.areEqual(((Format) ((Pair) it2.next()).component1()).id, id)) {
                                break loop1;
                            }
                        }
                    }
                }
                Tracks.Group group3 = (Tracks.Group) next;
                if (group3 != null) {
                    ExoPlayer exoPlayer2 = this.exoPlayer;
                    if (exoPlayer2 != null && (trackSelectionParameters2 = exoPlayer2.getTrackSelectionParameters()) != null && (builderBuildUpon2 = trackSelectionParameters2.buildUpon()) != null && (overrideForType = builderBuildUpon2.setOverrideForType(new TrackSelectionOverride(group3.getMediaTrackGroup(), iIntValue))) != null) {
                        trackSelectionParametersBuild2 = overrideForType.build();
                    }
                    if (trackSelectionParametersBuild2 != null) {
                        ExoPlayer exoPlayer3 = this.exoPlayer;
                        if (exoPlayer3 != null) {
                            exoPlayer3.setTrackSelectionParameters(trackSelectionParametersBuild2);
                            return;
                        }
                        return;
                    }
                }
            }
        }
        ExoPlayer exoPlayer4 = this.exoPlayer;
        if (exoPlayer4 == null || exoPlayer4 == null || (trackSelectionParameters = exoPlayer4.getTrackSelectionParameters()) == null || (builderBuildUpon = trackSelectionParameters.buildUpon()) == null || (preferredAudioLanguage = builderBuildUpon.setPreferredAudioLanguage(trackLanguage)) == null || (trackSelectionParametersBuild = preferredAudioLanguage.build()) == null) {
            return;
        }
        exoPlayer4.setTrackSelectionParameters(trackSelectionParametersBuild);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Pair<Format, Integer>> getFormats(List<Tracks.Group> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, getFormats((Tracks.Group) it.next()));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Pair<Format, Integer>> getFormats(Tracks.Group group) {
        IntRange intRangeUntil = RangesKt.until(0, group.getMediaTrackGroup().length);
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            int iNextInt = ((IntIterator) it).nextInt();
            Pair pair = group.isSupported() ? TuplesKt.to(group.getMediaTrackGroup().getFormat(iNextInt), Integer.valueOf(iNextInt)) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return arrayList;
    }

    private final AudioTrack toAudioTrack(Format format, Integer num) {
        return new AudioTrack(format.id, format.label, format.language, format.sampleMimeType, Integer.valueOf(format.channelCount), Integer.valueOf(num != null ? num.intValue() : 0));
    }

    private final TextTrack toSubtitleTrack(Format format) {
        String str = format.id;
        return new TextTrack(str != null ? stripTrackId(str) : null, format.label, format.language, format.sampleMimeType);
    }

    private final VideoTrack toVideoTrack(Format format) {
        String str = format.id;
        return new VideoTrack(str != null ? stripTrackId(str) : null, format.label, format.language, Integer.valueOf(format.width), Integer.valueOf(format.height), format.sampleMimeType);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public CurrentTracks getVideoTracks() {
        Format videoFormat;
        Tracks currentTracks;
        ImmutableList<Tracks.Group> groups;
        ExoPlayer exoPlayer = this.exoPlayer;
        Iterable iterableEmptyList = (exoPlayer == null || (currentTracks = exoPlayer.getCurrentTracks()) == null || (groups = currentTracks.getGroups()) == null) ? CollectionsKt.emptyList() : groups;
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterableEmptyList) {
            if (((Tracks.Group) obj).getType() == 2) {
                arrayList.add(obj);
            }
        }
        List<Pair<Format, Integer>> formats = getFormats(arrayList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(formats, 10));
        Iterator<T> it = formats.iterator();
        while (it.hasNext()) {
            arrayList2.add(toVideoTrack((Format) ((Pair) it.next()).getFirst()));
        }
        ArrayList arrayList3 = arrayList2;
        ArrayList<Tracks.Group> arrayList4 = new ArrayList();
        for (Object obj2 : iterableEmptyList) {
            if (((Tracks.Group) obj2).getType() == 1) {
                arrayList4.add(obj2);
            }
        }
        ArrayList arrayList5 = new ArrayList();
        VideoTrack videoTrack = null;
        AudioTrack audioTrack = null;
        for (Tracks.Group group : arrayList4) {
            Intrinsics.checkNotNull(group);
            List<Pair<Format, Integer>> formats2 = getFormats(group);
            ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(formats2, 10));
            Iterator<T> it2 = formats2.iterator();
            while (it2.hasNext()) {
                Pair pair = (Pair) it2.next();
                Format format = (Format) pair.component1();
                int iIntValue = ((Number) pair.component2()).intValue();
                AudioTrack audioTrack2 = toAudioTrack(format, Integer.valueOf(iIntValue));
                if (group.isTrackSelected(iIntValue)) {
                    audioTrack = audioTrack2;
                }
                arrayList6.add(audioTrack2);
            }
            CollectionsKt.addAll(arrayList5, arrayList6);
        }
        ArrayList arrayList7 = arrayList5;
        ArrayList arrayList8 = new ArrayList();
        for (Object obj3 : iterableEmptyList) {
            if (((Tracks.Group) obj3).getType() == 3) {
                arrayList8.add(obj3);
            }
        }
        List<Pair<Format, Integer>> formats3 = getFormats(arrayList8);
        ArrayList arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(formats3, 10));
        Iterator<T> it3 = formats3.iterator();
        while (it3.hasNext()) {
            arrayList9.add(toSubtitleTrack((Format) ((Pair) it3.next()).getFirst()));
        }
        ArrayList arrayList10 = arrayList9;
        ArrayList arrayList11 = new ArrayList();
        for (Object obj4 : arrayList10) {
            TextTrack textTrack = (TextTrack) obj4;
            List<Pair<String, Boolean>> list = this.playerSelectedSubtitleTracks;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it4 = list.iterator();
                while (it4.hasNext()) {
                    Pair pair2 = (Pair) it4.next();
                    if (((Boolean) pair2.getSecond()).booleanValue() && Intrinsics.areEqual(pair2.getFirst(), textTrack.getId())) {
                        arrayList11.add(obj4);
                        break;
                    }
                }
            }
        }
        ArrayList arrayList12 = arrayList11;
        ExoPlayer exoPlayer2 = this.exoPlayer;
        if (exoPlayer2 != null && (videoFormat = exoPlayer2.getVideoFormat()) != null) {
            videoTrack = toVideoTrack(videoFormat);
        }
        return new CurrentTracks(videoTrack, audioTrack, arrayList12, arrayList3, arrayList7, arrayList10);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public boolean setPreferredSubtitles(SubtitleData subtitle) {
        Tracks currentTracks;
        ImmutableList<Tracks.Group> groups;
        Log.i(CS3IPlayerKt.TAG, "setPreferredSubtitles init " + subtitle);
        this.currentSubtitles = subtitle;
        ExoPlayer exoPlayer = this.exoPlayer;
        TrackSelector trackSelector = exoPlayer != null ? exoPlayer.getTrackSelector() : null;
        DefaultTrackSelector defaultTrackSelector = trackSelector instanceof DefaultTrackSelector ? (DefaultTrackSelector) trackSelector : null;
        if (defaultTrackSelector == null) {
            return false;
        }
        if (subtitle == null) {
            defaultTrackSelector.setParameters(defaultTrackSelector.buildUponParameters().setTrackTypeDisabled(3, true).clearOverridesOfType(3));
            return false;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this.subtitleHelper.subtitleStatus(subtitle).ordinal()];
        if (i == 1) {
            Log.i(CS3IPlayerKt.TAG, "setPreferredSubtitles REQUIRES_RELOAD");
            return true;
        }
        if (i == 2) {
            Log.i(CS3IPlayerKt.TAG, "setPreferredSubtitles NOT_FOUND");
            return true;
        }
        if (i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        Log.i(CS3IPlayerKt.TAG, "setPreferredSubtitles IS_ACTIVE");
        ExoPlayer exoPlayer2 = this.exoPlayer;
        if (exoPlayer2 != null && (currentTracks = exoPlayer2.getCurrentTracks()) != null && (groups = currentTracks.getGroups()) != null) {
            ArrayList arrayList = new ArrayList();
            for (Tracks.Group group : groups) {
                if (group.getType() == 3) {
                    arrayList.add(group);
                }
            }
            Pair<TrackGroup, Integer> track = getTrack(arrayList, subtitle.getId());
            if (track != null) {
                defaultTrackSelector.setParameters(defaultTrackSelector.buildUponParameters().setTrackTypeDisabled(3, false).setOverrideForType(new TrackSelectionOverride(track.component1(), track.component2().intValue())));
            }
        }
        return false;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void setSubtitleOffset(long offset) {
        TextRenderer textRenderer;
        ExoPlayer exoPlayer;
        this.currentSubtitleOffset = offset;
        CustomDecoder.INSTANCE.setSubtitleOffset(offset);
        TextRenderer textRenderer2 = currentTextRenderer;
        if (((textRenderer2 == null || textRenderer2.getState() != 1) && ((textRenderer = currentTextRenderer) == null || textRenderer.getState() != 2)) || (exoPlayer = this.exoPlayer) == null) {
            return;
        }
        long jLongValue = Long.valueOf(exoPlayer.getCurrentPosition()).longValue();
        TextRenderer textRenderer3 = currentTextRenderer;
        if (textRenderer3 != null) {
            textRenderer3.resetPosition(jLongValue, false);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    /* JADX INFO: renamed from: getSubtitleOffset, reason: from getter */
    public long getCurrentSubtitleOffset() {
        return this.currentSubtitleOffset;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public List<SubtitleCue> getSubtitleCues() {
        List<SubtitleCue> subtitleCues;
        CustomSubtitleDecoderFactory customSubtitleDecoderFactory = currentSubtitleDecoder;
        return (customSubtitleDecoderFactory == null || (subtitleCues = customSubtitleDecoderFactory.getSubtitleCues()) == null) ? CollectionsKt.emptyList() : subtitleCues;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public SubtitleData getCurrentPreferredSubtitle() {
        Object next;
        Iterator<T> it = this.subtitleHelper.getAllSubtitles().iterator();
        while (it.hasNext()) {
            next = it.next();
            SubtitleData subtitleData = (SubtitleData) next;
            List<Pair<String, Boolean>> list = this.playerSelectedSubtitleTracks;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it2 = list.iterator();
                while (it2.hasNext()) {
                    Pair pair = (Pair) it2.next();
                    String str = (String) pair.component1();
                    if (((Boolean) pair.component2()).booleanValue() && Intrinsics.areEqual(subtitleData.getId(), str)) {
                        return (SubtitleData) next;
                    }
                }
            }
        }
        next = null;
        return (SubtitleData) next;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public Rational getAspectRatio() {
        Format videoFormat;
        ExoPlayer exoPlayer = this.exoPlayer;
        if (exoPlayer == null || (videoFormat = exoPlayer.getVideoFormat()) == null) {
            return null;
        }
        return new Rational(videoFormat.width, videoFormat.height);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void updateSubtitleStyle(SaveCaptionStyle style) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.subtitleHelper.setSubStyle(style);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void saveData() {
        Log.i(CS3IPlayerKt.TAG, "saveData");
        updatedTime$default(this, null, null, 3, null);
        ExoPlayer exoPlayer = this.exoPlayer;
        if (exoPlayer != null) {
            this.playbackPosition = exoPlayer.getCurrentPosition();
            this.currentWindow = exoPlayer.getCurrentMediaItemIndex();
            this.isPlaying = exoPlayer.isPlaying();
        }
    }

    static /* synthetic */ void releasePlayer$default(CS3IPlayer cS3IPlayer, boolean z, int i, Object obj) throws DebugException {
        if ((i & 1) != 0) {
            z = true;
        }
        cS3IPlayer.releasePlayer(z);
    }

    private final void releasePlayer(boolean saveTime) throws DebugException {
        Log.i(CS3IPlayerKt.TAG, "releasePlayer");
        this.eventLooperIndex++;
        if (saveTime) {
            updatedTime$default(this, null, null, 3, null);
        }
        currentTextRenderer = null;
        currentSubtitleDecoder = null;
        ExoPlayer exoPlayer = this.exoPlayer;
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false);
            try {
                exoPlayer.pause();
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
            }
            Player.Listener listener = this.playerListener;
            if (listener != null) {
                exoPlayer.removeListener(listener);
                this.playerListener = null;
            }
            exoPlayer.stop();
            exoPlayer.release();
        }
        setExoPlayer(null);
        event(new PlayerAttachedEvent(null, null, 2, null));
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void onStop() {
        Log.i(CS3IPlayerKt.TAG, "onStop");
        saveData();
        if (this.isAudioOnlyBackground) {
            return;
        }
        handleEvent(CSPlayerEvent.Pause, PlayerEventSource.Player);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void onPause() {
        Log.i(CS3IPlayerKt.TAG, "onPause");
        saveData();
        if (this.isAudioOnlyBackground) {
            return;
        }
        handleEvent(CSPlayerEvent.Pause, PlayerEventSource.Player);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void onResume(Context context) throws DebugException {
        Intrinsics.checkNotNullParameter(context, "context");
        this.isAudioOnlyBackground = false;
        if (this.exoPlayer == null) {
            reloadPlayer(context);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void release() throws DebugException {
        this.imageGenerator.release();
        releasePlayer$default(this, false, 1, null);
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void setPlaybackSpeed(float speed) {
        ExoPlayer exoPlayer = this.exoPlayer;
        if (exoPlayer != null) {
            exoPlayer.setPlaybackSpeed(speed);
        }
        this.playBackSpeed = speed;
    }

    /* JADX INFO: compiled from: CS3IPlayer.kt */
    @Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0005H\u0002J(\u0010\u0017\u001a\u00020\u00182\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J$\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\f\u0010&\u001a\u00020'*\u00020\u001fH\u0002J\u001a\u0010(\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020!H\u0002J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u000fH\u0002J\u0018\u0010-\u001a\u00020.2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010/\u001a\u000200H\u0002J\u0018\u0010-\u001a\u00020.2\u0006\u0010,\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000fH\u0002J\u001f\u00102\u001a\u0002032\u0006\u0010\u001e\u001a\u00020\u001f2\b\u00104\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0002\u00105R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f8F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000109X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/CS3IPlayer$Companion;", "", "<init>", "()V", "CRONET_TIMEOUT_MS", "", "cronetEngine", "Lorg/chromium/net/CronetEngine;", "activePlayers", "cronetReleasedId", "releaseCronetEngine", "", "releaseCronetEngineInstantly", "id", ES6Iterator.VALUE_PROPERTY, "", "preferredAudioTrackLanguage", "getPreferredAudioTrackLanguage", "()Ljava/lang/String;", "setPreferredAudioTrackLanguage", "(Ljava/lang/String;)V", "simpleCache", "Landroidx/media3/datasource/cache/SimpleCache;", "createOnlineSource", "Landroidx/media3/datasource/HttpDataSource$Factory;", "headers", "", "interceptor", "Lokhttp3/Interceptor;", "tryCreateEngine", "context", "Landroid/content/Context;", "diskCacheSize", "", "createVideoSource", "link", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "engine", "createOfflineSource", "Landroidx/media3/datasource/DataSource$Factory;", "getCache", "cacheSize", "getMediaItemBuilder", "Landroidx/media3/common/MediaItem$Builder;", "mimeType", "getMediaItem", "Landroidx/media3/common/MediaItem;", "uri", "Landroid/net/Uri;", ImagesContract.URL, "getTrackSelector", "Landroidx/media3/exoplayer/trackselection/TrackSelector;", "maxVideoHeight", "(Landroid/content/Context;Ljava/lang/Integer;)Landroidx/media3/exoplayer/trackselection/TrackSelector;", "currentSubtitleDecoder", "Lcom/lagradost/cloudstream3/ui/player/CustomSubtitleDecoderFactory;", "currentTextRenderer", "Landroidx/media3/exoplayer/text/TextRenderer;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void releaseCronetEngine() {
            if (CS3IPlayer.cronetEngine == null) {
                return;
            }
            CS3IPlayer.cronetReleasedId++;
            final int i = CS3IPlayer.cronetReleasedId;
            if (new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$Companion$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CS3IPlayer.INSTANCE.releaseCronetEngineInstantly(i);
                }
            }, 60000L)) {
                return;
            }
            releaseCronetEngineInstantly(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void releaseCronetEngineInstantly(int id) {
            if (CS3IPlayer.activePlayers == 0 && id == CS3IPlayer.cronetReleasedId) {
                try {
                    CronetEngine cronetEngine = CS3IPlayer.cronetEngine;
                    if (cronetEngine != null) {
                        cronetEngine.shutdown();
                    }
                } catch (Throwable th) {
                    try {
                        ArchComponentExtKt.logError(th);
                    } finally {
                        Log.d(CS3IPlayerKt.TAG, "CronetEngine shutdown");
                        CS3IPlayer.cronetEngine = null;
                    }
                }
            }
        }

        public final String getPreferredAudioTrackLanguage() {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            String str = CS3IPlayer.preferredAudioTrackLanguage;
            if (str != null) {
                return str;
            }
            CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
            String str2 = DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/preferred_audio_language";
            Object obj = CS3IPlayer.preferredAudioTrackLanguage;
            Context context = companion.getContext();
            if (context != null) {
                try {
                    String string = DataStore.INSTANCE.getSharedPrefs(context).getString(str2, null);
                    if (string != null) {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                        } catch (Throwable th) {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                            try {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion5 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$Companion$special$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$Companion$special$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<String>() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$Companion$special$$inlined$getKey$1
                            });
                        }
                        obj = objDecodeFromString;
                    }
                } catch (Exception unused2) {
                    obj = null;
                }
            } else {
                obj = null;
            }
            String str3 = (String) obj;
            if (str3 == null) {
                return null;
            }
            CS3IPlayer.preferredAudioTrackLanguage = str3;
            return str3;
        }

        public final void setPreferredAudioTrackLanguage(String str) {
            CloudStreamApp.INSTANCE.setKey(DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/preferred_audio_language", str);
            CS3IPlayer.preferredAudioTrackLanguage = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final HttpDataSource.Factory createOnlineSource(Map<String, String> headers, Interceptor interceptor) {
            OkHttpClient okHttpClientBuild;
            if (interceptor == null) {
                okHttpClientBuild = MainActivityKt.getApp().getBaseClient();
            } else {
                okHttpClientBuild = MainActivityKt.getApp().getBaseClient().newBuilder().addInterceptor(interceptor).build();
            }
            OkHttpDataSource.Factory userAgent = new OkHttpDataSource.Factory(okHttpClientBuild).setUserAgent(MainAPIKt.USER_AGENT);
            Intrinsics.checkNotNullExpressionValue(userAgent, "setUserAgent(...)");
            if (headers != null && !headers.isEmpty()) {
                userAgent.setDefaultRequestProperties(headers);
            }
            return userAgent;
        }

        public final CronetEngine tryCreateEngine(Context context, long diskCacheSize) {
            Intrinsics.checkNotNullParameter(context, "context");
            CronetEngine cronetEngine = CS3IPlayer.cronetEngine;
            if (cronetEngine != null) {
                return cronetEngine;
            }
            try {
                File file = new File(context.getCacheDir(), "CronetEngine");
                FilesKt.deleteRecursively(file);
                if (!file.exists()) {
                    file.mkdirs();
                }
                CronetEngine cronetEngineBuild = new CronetEngine.Builder(context).enableBrotli(true).enableHttp2(true).enableQuic(true).setStoragePath(file.getAbsolutePath()).setLibraryLoader(null).enableHttpCache(3, diskCacheSize).build();
                Log.d(CS3IPlayerKt.TAG, "Created CronetEngine with cache at " + file.getAbsolutePath());
                Companion companion = CS3IPlayer.INSTANCE;
                CS3IPlayer.cronetEngine = cronetEngineBuild;
                return cronetEngineBuild;
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final HttpDataSource.Factory createVideoSource(ExtractorLink link, CronetEngine engine, Interceptor interceptor) {
            Object next;
            String str;
            OkHttpDataSource.Factory factory;
            Iterator<T> it = link.getHeaders().entrySet().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!StringsKt.equals((String) ((Map.Entry) next).getKey(), "User-Agent", true));
            Map.Entry entry = (Map.Entry) next;
            if (entry == null || (str = (String) entry.getValue()) == null) {
                str = MainAPIKt.USER_AGENT;
            }
            if (interceptor != null) {
                Log.d(CS3IPlayerKt.TAG, "Using OkHttpDataSource for " + link);
                OkHttpDataSource.Factory userAgent = new OkHttpDataSource.Factory(MainActivityKt.getApp().getBaseClient().newBuilder().addInterceptor(interceptor).build()).setUserAgent(str);
                Intrinsics.checkNotNull(userAgent);
                factory = userAgent;
            } else if (engine == null) {
                Log.d(CS3IPlayerKt.TAG, "Using DefaultHttpDataSource for " + link);
                OkHttpDataSource.Factory userAgent2 = new OkHttpDataSource.Factory(MainActivityKt.getApp().getBaseClient()).setUserAgent(str);
                Intrinsics.checkNotNull(userAgent2);
                factory = userAgent2;
            } else {
                Log.d(CS3IPlayerKt.TAG, "Using CronetDataSource for " + link);
                CronetDataSource.Factory handleSetCookieRequests = new CronetDataSource.Factory(engine, Executors.newSingleThreadExecutor()).setUserAgent(str).setConnectionTimeoutMs(CS3IPlayer.CRONET_TIMEOUT_MS).setReadTimeoutMs(CS3IPlayer.CRONET_TIMEOUT_MS).setResetTimeoutOnRedirects(true).setHandleSetCookieRequests(true);
                Intrinsics.checkNotNull(handleSetCookieRequests);
                factory = handleSetCookieRequests;
            }
            factory.setDefaultRequestProperties(MapsKt.plus(StringsKt.isBlank(link.getReferer()) ? MapsKt.emptyMap() : MapsKt.mapOf(TuplesKt.to("referer", link.getReferer())), link.getHeaders()));
            return factory;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final DataSource.Factory createOfflineSource(Context context) {
            return new DefaultDataSource.Factory(context, new DefaultHttpDataSource.Factory().setUserAgent(MainAPIKt.USER_AGENT));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final SimpleCache getCache(Context context, long cacheSize) {
            try {
                StandaloneDatabaseProvider standaloneDatabaseProvider = new StandaloneDatabaseProvider(context);
                File file = new File(context.getCacheDir(), "exoplayer");
                MainActivity.INSTANCE.deleteFileOnExit(file);
                return new SimpleCache(file, new LeastRecentlyUsedCacheEvictor(cacheSize), standaloneDatabaseProvider);
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
                return null;
            }
        }

        private final MediaItem.Builder getMediaItemBuilder(String mimeType) {
            MediaItem.Builder mimeType2 = new MediaItem.Builder().setMimeType(mimeType);
            Intrinsics.checkNotNullExpressionValue(mimeType2, "setMimeType(...)");
            return mimeType2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final MediaItem getMediaItem(String mimeType, Uri uri) {
            MediaItem mediaItemBuild = getMediaItemBuilder(mimeType).setUri(uri).build();
            Intrinsics.checkNotNullExpressionValue(mediaItemBuild, "build(...)");
            return mediaItemBuild;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final MediaItem getMediaItem(String mimeType, String url) {
            MediaItem mediaItemBuild = getMediaItemBuilder(mimeType).setUri(url).build();
            Intrinsics.checkNotNullExpressionValue(mediaItemBuild, "build(...)");
            return mediaItemBuild;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final TrackSelector getTrackSelector(Context context, Integer maxVideoHeight) {
            DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(context);
            defaultTrackSelector.setParameters(defaultTrackSelector.buildUponParameters().setMaxVideoSize(Integer.MAX_VALUE, maxVideoHeight != null ? maxVideoHeight.intValue() : Integer.MAX_VALUE).setPreferredAudioLanguage((String) null).build());
            return defaultTrackSelector;
        }
    }

    static /* synthetic */ VideoSkipStamp getCurrentTimestamp$default(CS3IPlayer cS3IPlayer, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            l = null;
        }
        return cS3IPlayer.getCurrentTimestamp(l);
    }

    private final VideoSkipStamp getCurrentTimestamp(Long writePosition) {
        if (writePosition != null || (writePosition = getPosition()) != null) {
            long jLongValue = writePosition.longValue();
            for (VideoSkipStamp videoSkipStamp : this.lastTimeStamps) {
                if (videoSkipStamp.getTimestamp().getStartMs() <= jLongValue && 301 + jLongValue < videoSkipStamp.getTimestamp().getEndMs()) {
                    return videoSkipStamp;
                }
            }
        }
        return null;
    }

    public static /* synthetic */ void updatedTime$default(CS3IPlayer cS3IPlayer, Long l, PlayerEventSource playerEventSource, int i, Object obj) {
        if ((i & 1) != 0) {
            l = null;
        }
        if ((i & 2) != 0) {
            playerEventSource = PlayerEventSource.Player;
        }
        cS3IPlayer.updatedTime(l, playerEventSource);
    }

    public final void updatedTime(Long writePosition, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (writePosition == null) {
            ExoPlayer exoPlayer = this.exoPlayer;
            writePosition = exoPlayer != null ? Long.valueOf(exoPlayer.getCurrentPosition()) : null;
        }
        VideoSkipStamp currentTimestamp = getCurrentTimestamp(writePosition);
        if (currentTimestamp != null) {
            event(new TimestampInvokedEvent(currentTimestamp, source));
        }
        ExoPlayer exoPlayer2 = this.exoPlayer;
        Long lValueOf = exoPlayer2 != null ? Long.valueOf(exoPlayer2.getContentDuration()) : null;
        if (lValueOf == null || writePosition == null) {
            return;
        }
        ExoPlayer exoPlayer3 = this.exoPlayer;
        event(new PositionEvent(source, exoPlayer3 != null ? exoPlayer3.getCurrentPosition() : 0L, writePosition.longValue(), lValueOf.longValue()));
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void seekTime(long time, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        ExoPlayer exoPlayer = this.exoPlayer;
        if (exoPlayer != null) {
            seekTime(exoPlayer, time, source);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void seekTo(long time, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (isMediaSeekable()) {
            updatedTime(Long.valueOf(time), source);
            ExoPlayer exoPlayer = this.exoPlayer;
            if (exoPlayer != null) {
                exoPlayer.seekTo(time);
                return;
            }
            return;
        }
        Log.i(CS3IPlayerKt.TAG, "Media is not seekable, we can not seek to " + time);
    }

    private final void seekTime(ExoPlayer exoPlayer, long j, PlayerEventSource playerEventSource) {
        if (isMediaSeekable()) {
            updatedTime(Long.valueOf(exoPlayer.getCurrentPosition() + j), playerEventSource);
            exoPlayer.seekTo(exoPlayer.getCurrentPosition() + j);
        } else {
            Log.i(CS3IPlayerKt.TAG, "Media is not seekable, we can not seek to " + j);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void handleEvent(CSPlayerEvent event, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(source, "source");
        Log.i(CS3IPlayerKt.TAG, "handleEvent " + event.name());
        try {
            ExoPlayer exoPlayer = this.exoPlayer;
            if (exoPlayer != null) {
                switch (WhenMappings.$EnumSwitchMapping$1[event.ordinal()]) {
                    case 1:
                        event(new PlayEvent(source));
                        if (exoPlayer.getPlaybackState() == 1) {
                            final long currentPosition = exoPlayer.getCurrentPosition();
                            ExoPlayer exoPlayer2 = this.exoPlayer;
                            if (exoPlayer2 != null) {
                                exoPlayer2.addListener(new Player.Listener() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$handleEvent$1$1
                                    private boolean seekApplied;

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                                        Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onAudioSessionIdChanged(int i) {
                                        Player.Listener.CC.$default$onAudioSessionIdChanged(this, i);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
                                        Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onCues(CueGroup cueGroup) {
                                        Player.Listener.CC.$default$onCues(this, cueGroup);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onCues(List list) {
                                        Player.Listener.CC.$default$onCues(this, list);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
                                        Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onDeviceVolumeChanged(int i, boolean z) {
                                        Player.Listener.CC.$default$onDeviceVolumeChanged(this, i, z);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onEvents(Player player, Player.Events events) {
                                        Player.Listener.CC.$default$onEvents(this, player, events);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onIsLoadingChanged(boolean z) {
                                        Player.Listener.CC.$default$onIsLoadingChanged(this, z);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onIsPlayingChanged(boolean z) {
                                        Player.Listener.CC.$default$onIsPlayingChanged(this, z);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onLoadingChanged(boolean z) {
                                        Player.Listener.CC.$default$onLoadingChanged(this, z);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
                                        Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i) {
                                        Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, i);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                                        Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onMetadata(androidx.media3.common.Metadata metadata) {
                                        Player.Listener.CC.$default$onMetadata(this, metadata);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i) {
                                        Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, i);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                                        Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i) {
                                        Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, i);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlayerError(PlaybackException playbackException) {
                                        Player.Listener.CC.$default$onPlayerError(this, playbackException);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
                                        Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlayerStateChanged(boolean z, int i) {
                                        Player.Listener.CC.$default$onPlayerStateChanged(this, z, i);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                                        Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPositionDiscontinuity(int i) {
                                        Player.Listener.CC.$default$onPositionDiscontinuity(this, i);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
                                        Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, i);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onRenderedFirstFrame() {
                                        Player.Listener.CC.$default$onRenderedFirstFrame(this);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onRepeatModeChanged(int i) {
                                        Player.Listener.CC.$default$onRepeatModeChanged(this, i);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onSeekBackIncrementChanged(long j) {
                                        Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
                                        Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
                                        Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
                                        Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
                                        Player.Listener.CC.$default$onSurfaceSizeChanged(this, i, i2);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onTimelineChanged(Timeline timeline, int i) {
                                        Player.Listener.CC.$default$onTimelineChanged(this, timeline, i);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
                                        Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onTracksChanged(Tracks tracks) {
                                        Player.Listener.CC.$default$onTracksChanged(this, tracks);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                                        Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onVolumeChanged(float f) {
                                        Player.Listener.CC.$default$onVolumeChanged(this, f);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public void onPlaybackStateChanged(int playbackState) {
                                        if (this.seekApplied || playbackState != 3) {
                                            return;
                                        }
                                        this.seekApplied = true;
                                        ExoPlayer exoPlayer3 = this.this$0.exoPlayer;
                                        if (exoPlayer3 != null) {
                                            exoPlayer3.seekTo(this.this$0.currentWindow, currentPosition);
                                        }
                                        ExoPlayer exoPlayer4 = this.this$0.exoPlayer;
                                        if (exoPlayer4 != null) {
                                            exoPlayer4.removeListener(this);
                                        }
                                    }
                                });
                            }
                            exoPlayer.prepare();
                        }
                        exoPlayer.play();
                        return;
                    case 2:
                        event(new PauseEvent(source));
                        exoPlayer.pause();
                        return;
                    case 3:
                        if (exoPlayer.getVolume() <= 0.0f) {
                            exoPlayer.setVolume(this.lastMuteVolume);
                            return;
                        } else {
                            this.lastMuteVolume = exoPlayer.getVolume();
                            exoPlayer.setVolume(0.0f);
                            return;
                        }
                    case 4:
                        if (exoPlayer.isPlaying()) {
                            handleEvent(CSPlayerEvent.Pause, source);
                            return;
                        } else {
                            handleEvent(CSPlayerEvent.Play, source);
                            return;
                        }
                    case 5:
                        seekTime(exoPlayer, this.seekActionTime, source);
                        return;
                    case 6:
                        seekTime(exoPlayer, -this.seekActionTime, source);
                        return;
                    case 7:
                        seekTo(0L, source);
                        return;
                    case 8:
                        event(new EpisodeSeekEvent(1, source));
                        return;
                    case 9:
                        event(new EpisodeSeekEvent(-1, source));
                        return;
                    case 10:
                        VideoSkipStamp currentTimestamp$default = getCurrentTimestamp$default(this, null, 1, null);
                        if (currentTimestamp$default != null) {
                            if (currentTimestamp$default.getSkipToNextEpisode()) {
                                handleEvent(CSPlayerEvent.NextEpisode, source);
                            } else {
                                exoPlayer.seekTo(currentTimestamp$default.getTimestamp().getEndMs() + 1);
                            }
                            event(new TimestampSkippedEvent(currentTimestamp$default, source));
                            return;
                        }
                        return;
                    case 11:
                        this.isAudioOnlyBackground = true;
                        Activity activity = CommonActivity.INSTANCE.getActivity();
                        if (activity != null) {
                            activity.moveTaskToBack(false);
                            return;
                        }
                        return;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
        } catch (Throwable th) {
            Log.e(CS3IPlayerKt.TAG, "handleEvent error", th);
            event(new ErrorEvent(th, null, 2, null));
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.CS3IPlayer$torrentEventLooper$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CS3IPlayer.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/player/CS3IPlayer;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.CS3IPlayer$torrentEventLooper$1", f = "CS3IPlayer.kt", i = {0, 1}, l = {1046, 1059}, m = "invokeSuspend", n = {"currentIndex", "currentIndex"}, nl = {1047, 1061}, s = {"I$0", "I$0"}, v = 2)
    static final class C08581 extends SuspendLambda implements Function3<CoroutineScope, CS3IPlayer, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hash;
        int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08581(String str, Continuation<? super C08581> continuation) {
            super(3, continuation);
            this.$hash = str;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, CS3IPlayer cS3IPlayer, Continuation<? super Unit> continuation) {
            return CS3IPlayer.this.new C08581(this.$hash, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:15:0x003d A[PHI: r0
          0x003d: PHI (r0v10 int) = (r0v4 int), (r0v6 int), (r0v20 int) binds: [B:14:0x0029, B:30:0x00b7, B:6:0x0010] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:17:0x0046  */
        /* JADX WARN: Code duplicated, block: B:24:0x006e A[Catch: all -> 0x0026, NullPointerException -> 0x00a9, TryCatch #2 {NullPointerException -> 0x00a9, all -> 0x0026, blocks: (B:19:0x004e, B:22:0x0060, B:24:0x006e, B:26:0x0079, B:10:0x0020), top: B:35:0x0020 }] */
        /* JADX WARN: Code duplicated, block: B:25:0x0078  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00b7 -> B:15:0x003d). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            int i;
            int i2;
            Object obj2;
            Double downloadSpeed;
            Long lBoxLong;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                CS3IPlayer.this.eventLooperIndex += 2;
                i = CS3IPlayer.this.eventLooperIndex + 1;
            } else {
                if (i3 == 1) {
                    i2 = this.I$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        obj2 = obj;
                    } catch (NullPointerException unused) {
                        i = i2;
                        this.I$0 = i;
                        this.label = 2;
                        if (DelayKt.delay(1000L, this) != coroutine_suspended) {
                            i2 = i;
                            if (CS3IPlayer.this.eventLooperIndex > i2) {
                            }
                            return Unit.INSTANCE;
                        }
                        return coroutine_suspended;
                    } catch (Throwable th) {
                        ArchComponentExtKt.logError(th);
                        i = i2;
                        this.I$0 = i;
                        this.label = 2;
                        if (DelayKt.delay(1000L, this) != coroutine_suspended) {
                            i2 = i;
                            if (CS3IPlayer.this.eventLooperIndex > i2) {
                            }
                            return Unit.INSTANCE;
                        }
                        return coroutine_suspended;
                    }
                    Torrent.TorrentStatus torrentStatus = (Torrent.TorrentStatus) obj2;
                    CS3IPlayer cS3IPlayer = CS3IPlayer.this;
                    Integer activePeers = torrentStatus.getActivePeers();
                    downloadSpeed = torrentStatus.getDownloadSpeed();
                    if (downloadSpeed != null) {
                        lBoxLong = Boxing.boxLong((long) downloadSpeed.doubleValue());
                    } else {
                        lBoxLong = null;
                    }
                    Intrinsics.checkNotNull(lBoxLong);
                    long jLongValue = lBoxLong.longValue();
                    Long torrentSize = torrentStatus.getTorrentSize();
                    Intrinsics.checkNotNull(torrentSize);
                    long jLongValue2 = torrentSize.longValue();
                    Long bytesRead = torrentStatus.getBytesRead();
                    Intrinsics.checkNotNull(bytesRead);
                    cS3IPlayer.event(new DownloadEvent(bytesRead.longValue(), jLongValue2, jLongValue, activePeers, null, 16, null));
                    i = i2;
                    this.I$0 = i;
                    this.label = 2;
                    if (DelayKt.delay(1000L, this) != coroutine_suspended) {
                    }
                    return coroutine_suspended;
                }
                if (i3 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                ResultKt.throwOnFailure(obj);
            }
            i2 = i;
            if (CS3IPlayer.this.eventLooperIndex > i2 && CS3IPlayer.this.eventHandler != null) {
                this.I$0 = i2;
                this.label = 1;
                obj2 = Torrent.INSTANCE.get(this.$hash, this);
                if (obj2 != coroutine_suspended) {
                    Torrent.TorrentStatus torrentStatus2 = (Torrent.TorrentStatus) obj2;
                    CS3IPlayer cS3IPlayer2 = CS3IPlayer.this;
                    Integer activePeers2 = torrentStatus2.getActivePeers();
                    downloadSpeed = torrentStatus2.getDownloadSpeed();
                    if (downloadSpeed != null) {
                        lBoxLong = Boxing.boxLong((long) downloadSpeed.doubleValue());
                    } else {
                        lBoxLong = null;
                    }
                    Intrinsics.checkNotNull(lBoxLong);
                    long jLongValue3 = lBoxLong.longValue();
                    Long torrentSize2 = torrentStatus2.getTorrentSize();
                    Intrinsics.checkNotNull(torrentSize2);
                    long jLongValue4 = torrentSize2.longValue();
                    Long bytesRead2 = torrentStatus2.getBytesRead();
                    Intrinsics.checkNotNull(bytesRead2);
                    cS3IPlayer2.event(new DownloadEvent(bytesRead2.longValue(), jLongValue4, jLongValue3, activePeers2, null, 16, null));
                    i = i2;
                    this.I$0 = i;
                    this.label = 2;
                    if (DelayKt.delay(1000L, this) != coroutine_suspended) {
                        i2 = i;
                        if (CS3IPlayer.this.eventLooperIndex > i2) {
                        }
                        return Unit.INSTANCE;
                    }
                }
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job torrentEventLooper(String hash) {
        return Coroutines.INSTANCE.ioSafe(this, new C08581(hash, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ ExoPlayer buildExoPlayer$default(CS3IPlayer cS3IPlayer, Context context, List list, List list2, int i, long j, float f, long j2, long j3, long j4, HttpDataSource.Factory factory, boolean z, TrackSelector trackSelector, Integer num, List list3, int i2, Object obj) {
        return cS3IPlayer.buildExoPlayer(context, list, list2, i, j, f, j2, j3, j4, (i2 & 512) != 0 ? null : factory, (i2 & 1024) != 0 ? true : z, (i2 & 2048) != 0 ? null : trackSelector, (i2 & 4096) != 0 ? null : num, (i2 & 8192) != 0 ? CollectionsKt.emptyList() : list3);
    }

    /* JADX WARN: Code duplicated, block: B:46:0x0200 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:47:0x0202  */
    /* JADX WARN: Code duplicated, block: B:50:0x0256  */
    /* JADX WARN: Code duplicated, block: B:52:0x025c  */
    private final ExoPlayer buildExoPlayer(final Context context, List<MediaItemSlice> mediaItemSlices, List<SingleSampleMediaSource> subSources, int currentWindow, long playbackPosition, float playBackSpeed, final long subtitleOffset, long cacheSize, long videoBufferMs, HttpDataSource.Factory onlineSource, boolean playWhenReady, TrackSelector trackSelector, Integer maxVideoHeight, List<? extends MediaSource> audioSources) {
        CacheDataSource.Factory factory;
        DefaultMediaSourceFactory defaultMediaSourceFactory;
        ConcatenatingMediaSource concatenatingMediaSource;
        MediaSource mediaSourceCreateMediaSource;
        DashMediaSource dashMediaSourceCreateMediaSource;
        ExoPlayer.Builder loadControl = new ExoPlayer.Builder(context).setMediaSourceFactory(new DefaultMediaSourceFactory(context).setLiveTargetOffsetMs(5000L)).setLivePlaybackSpeedControl(new DefaultLivePlaybackSpeedControl.Builder().setFallbackMaxPlaybackSpeed(1.03f).setFallbackMinPlaybackSpeed(0.97f).build()).setRenderersFactory(new RenderersFactory() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$$ExternalSyntheticLambda2
            @Override // androidx.media3.exoplayer.RenderersFactory
            public final Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
                return CS3IPlayer.buildExoPlayer$lambda$0(context, this, subtitleOffset, handler, videoRendererEventListener, audioRendererEventListener, textOutput, metadataOutput);
            }

            @Override // androidx.media3.exoplayer.RenderersFactory
            public /* synthetic */ Renderer createSecondaryRenderer(Renderer renderer, Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
                return RenderersFactory.CC.$default$createSecondaryRenderer(this, renderer, handler, videoRendererEventListener, audioRendererEventListener, textOutput, metadataOutput);
            }
        }).setTrackSelector(trackSelector == null ? INSTANCE.getTrackSelector(context, maxVideoHeight) : trackSelector).setSeekParameters(new SeekParameters(300000L, 300000L)).setLoadControl(new DefaultLoadControl.Builder().setTargetBufferBytes(cacheSize <= 0 ? -1 : cacheSize > SieveCacheKt.NodeLinkMask ? Integer.MAX_VALUE : (int) cacheSize).setBackBuffer(30000, true).setBufferDurationsMs(50000, videoBufferMs <= 0 ? 50000 : (int) videoBufferMs, 1000, 2000).build());
        Intrinsics.checkNotNullExpressionValue(loadControl, "setLoadControl(...)");
        UpdatedDefaultExtractorsFactory fragmentedMp4ExtractorFlags = new UpdatedDefaultExtractorsFactory().setFragmentedMp4ExtractorFlags(256);
        if (onlineSource == null) {
            factory = null;
        } else {
            if (simpleCache == null) {
                simpleCache = INSTANCE.getCache(context, this.simpleCacheSize);
            }
            factory = new CacheDataSource.Factory();
            SimpleCache simpleCache2 = simpleCache;
            if (simpleCache2 != null) {
                factory.setCache(simpleCache2);
            }
            factory.setUpstreamDataSourceFactory(onlineSource);
        }
        if (factory != null) {
            defaultMediaSourceFactory = new DefaultMediaSourceFactory(factory, fragmentedMp4ExtractorFlags);
        } else {
            defaultMediaSourceFactory = new DefaultMediaSourceFactory(context, fragmentedMp4ExtractorFlags);
        }
        if (mediaItemSlices.size() == 1) {
            MediaItemSlice mediaItemSlice = (MediaItemSlice) CollectionsKt.first((List) mediaItemSlices);
            DrmMetadata drm = mediaItemSlice.getDrm();
            if (drm != null) {
                UUID uuid = drm.getUuid();
                Uuid clearkey_drm_uuid = ExtractorApiKt.getCLEARKEY_DRM_UUID();
                CacheDataSource.Factory factory2 = factory;
                if (Intrinsics.areEqual(uuid, new UUID(clearkey_drm_uuid.getMostSignificantBits(), clearkey_drm_uuid.getLeastSignificantBits()))) {
                    if (factory2 == null) {
                        throw new IllegalArgumentException("Must supply onlineSource");
                    }
                    byte[] bytes = ("{\"keys\":[{\"kty\":\"" + drm.getKty() + "\",\"k\":\"" + drm.getKey() + "\",\"kid\":\"" + drm.getKid() + "\"}],\"type\":\"temporary\"}").getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                    final DefaultDrmSessionManager defaultDrmSessionManagerBuild = new DefaultDrmSessionManager.Builder().setPlayClearSamplesWithoutKeys(true).setMultiSession(false).setKeyRequestParameters(drm.getKeyRequestParameters()).setUuidAndExoMediaDrmProvider(drm.getUuid(), FrameworkMediaDrm.DEFAULT_PROVIDER).build(new LocalMediaDrmCallback(bytes));
                    Intrinsics.checkNotNullExpressionValue(defaultDrmSessionManagerBuild, "build(...)");
                    dashMediaSourceCreateMediaSource = new DashMediaSource.Factory(factory2).setDrmSessionManagerProvider(new DrmSessionManagerProvider() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$$ExternalSyntheticLambda3
                        @Override // androidx.media3.exoplayer.drm.DrmSessionManagerProvider
                        public final DrmSessionManager get(MediaItem mediaItem) {
                            return CS3IPlayer.buildExoPlayer$lambda$2$0(defaultDrmSessionManagerBuild, mediaItem);
                        }
                    }).createMediaSource(mediaItemSlice.getMediaItem());
                } else {
                    Uuid widevine_drm_uuid = ExtractorApiKt.getWIDEVINE_DRM_UUID();
                    if (!Intrinsics.areEqual(uuid, new UUID(widevine_drm_uuid.getMostSignificantBits(), widevine_drm_uuid.getLeastSignificantBits()))) {
                        Uuid playready_drm_uuid = ExtractorApiKt.getPLAYREADY_DRM_UUID();
                        if (Intrinsics.areEqual(uuid, new UUID(playready_drm_uuid.getMostSignificantBits(), playready_drm_uuid.getLeastSignificantBits()))) {
                            if (factory2 != null) {
                                throw new IllegalArgumentException("Must supply onlineSource");
                            }
                            CacheDataSource.Factory factory3 = factory2;
                            final DefaultDrmSessionManager defaultDrmSessionManagerBuild2 = new DefaultDrmSessionManager.Builder().setPlayClearSamplesWithoutKeys(true).setMultiSession(true).setKeyRequestParameters(drm.getKeyRequestParameters()).setUuidAndExoMediaDrmProvider(drm.getUuid(), FrameworkMediaDrm.DEFAULT_PROVIDER).build(new HttpMediaDrmCallback(drm.getLicenseUrl(), factory3));
                            Intrinsics.checkNotNullExpressionValue(defaultDrmSessionManagerBuild2, "build(...)");
                            dashMediaSourceCreateMediaSource = new DashMediaSource.Factory(factory3).setDrmSessionManagerProvider(new DrmSessionManagerProvider() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$$ExternalSyntheticLambda4
                                @Override // androidx.media3.exoplayer.drm.DrmSessionManagerProvider
                                public final DrmSessionManager get(MediaItem mediaItem) {
                                    return CS3IPlayer.buildExoPlayer$lambda$2$1(defaultDrmSessionManagerBuild2, mediaItem);
                                }
                            }).createMediaSource(mediaItemSlice.getMediaItem());
                        } else {
                            Log.e(CS3IPlayerKt.TAG, "DRM Metadata class is not supported: " + Reflection.getOrCreateKotlinClass(drm.getClass()).getSimpleName());
                            dashMediaSourceCreateMediaSource = null;
                        }
                    } else {
                        if (factory2 != null) {
                            throw new IllegalArgumentException("Must supply onlineSource");
                        }
                        CacheDataSource.Factory factory4 = factory2;
                        final DefaultDrmSessionManager defaultDrmSessionManagerBuild3 = new DefaultDrmSessionManager.Builder().setPlayClearSamplesWithoutKeys(true).setMultiSession(true).setKeyRequestParameters(drm.getKeyRequestParameters()).setUuidAndExoMediaDrmProvider(drm.getUuid(), FrameworkMediaDrm.DEFAULT_PROVIDER).build(new HttpMediaDrmCallback(drm.getLicenseUrl(), factory4));
                        Intrinsics.checkNotNullExpressionValue(defaultDrmSessionManagerBuild3, "build(...)");
                        dashMediaSourceCreateMediaSource = new DashMediaSource.Factory(factory4).setDrmSessionManagerProvider(new DrmSessionManagerProvider() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$$ExternalSyntheticLambda4
                            @Override // androidx.media3.exoplayer.drm.DrmSessionManagerProvider
                            public final DrmSessionManager get(MediaItem mediaItem) {
                                return CS3IPlayer.buildExoPlayer$lambda$2$1(defaultDrmSessionManagerBuild3, mediaItem);
                            }
                        }).createMediaSource(mediaItemSlice.getMediaItem());
                    }
                }
                if (dashMediaSourceCreateMediaSource != null) {
                    mediaSourceCreateMediaSource = dashMediaSourceCreateMediaSource;
                } else {
                    mediaSourceCreateMediaSource = defaultMediaSourceFactory.createMediaSource(mediaItemSlice.getMediaItem());
                }
            } else {
                mediaSourceCreateMediaSource = defaultMediaSourceFactory.createMediaSource(mediaItemSlice.getMediaItem());
            }
        } else {
            try {
                ConcatenatingMediaSource2.Builder builder = new ConcatenatingMediaSource2.Builder();
                for (MediaItemSlice mediaItemSlice2 : mediaItemSlices) {
                    builder.add(new ClippingMediaSource(defaultMediaSourceFactory.createMediaSource(mediaItemSlice2.getMediaItem()), mediaItemSlice2.getDurationUs()));
                }
                ConcatenatingMediaSource2 concatenatingMediaSource2Build = builder.build();
                Intrinsics.checkNotNull(concatenatingMediaSource2Build);
                concatenatingMediaSource = concatenatingMediaSource2Build;
            } catch (IllegalArgumentException unused) {
                ConcatenatingMediaSource concatenatingMediaSource2 = new ConcatenatingMediaSource(new MediaSource[0]);
                for (MediaItemSlice mediaItemSlice3 : mediaItemSlices) {
                    concatenatingMediaSource2.addMediaSource(new ClippingMediaSource(defaultMediaSourceFactory.createMediaSource(mediaItemSlice3.getMediaItem()), mediaItemSlice3.getDurationUs()));
                }
                concatenatingMediaSource = concatenatingMediaSource2;
            }
            mediaSourceCreateMediaSource = concatenatingMediaSource;
        }
        Intrinsics.checkNotNull(mediaSourceCreateMediaSource);
        ExoPlayer exoPlayerBuild = loadControl.build();
        exoPlayerBuild.setPlayWhenReady(playWhenReady);
        exoPlayerBuild.seekTo(currentWindow, playbackPosition);
        MediaSource[] mediaSourceArr = (MediaSource[]) CollectionsKt.plus((Collection) CollectionsKt.plus((Collection) CollectionsKt.listOf(mediaSourceCreateMediaSource), (Iterable) subSources), (Iterable) audioSources).toArray(new MediaSource[0]);
        exoPlayerBuild.setMediaSource(new MergingMediaSource((MediaSource[]) Arrays.copyOf(mediaSourceArr, mediaSourceArr.length)), playbackPosition);
        exoPlayerBuild.setHandleAudioBecomingNoisy(true);
        exoPlayerBuild.setPlaybackSpeed(playBackSpeed);
        exoPlayerBuild.addAnalyticsListener(this.tracksAnalyticsListener);
        Intrinsics.checkNotNullExpressionValue(exoPlayerBuild, "apply(...)");
        return exoPlayerBuild;
    }

    static final Renderer[] buildExoPlayer$lambda$0(Context context, final CS3IPlayer cS3IPlayer, long j, Handler eventHandler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataRendererOutput) {
        Pair pair;
        FixedNextRenderersFactory defaultRenderersFactory;
        Intrinsics.checkNotNullParameter(eventHandler, "eventHandler");
        Intrinsics.checkNotNullParameter(videoRendererEventListener, "videoRendererEventListener");
        Intrinsics.checkNotNullParameter(audioRendererEventListener, "audioRendererEventListener");
        Intrinsics.checkNotNullParameter(textOutput, "<unused var>");
        Intrinsics.checkNotNullParameter(metadataRendererOutput, "metadataRendererOutput");
        int i = PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(R.string.software_decoding_key), -1);
        if (i == 0) {
            pair = TuplesKt.to(true, false);
        } else if (i != 1) {
            pair = i != 2 ? TuplesKt.to(Boolean.valueOf(Globals.INSTANCE.isLayout(5)), false) : TuplesKt.to(true, true);
        } else {
            pair = TuplesKt.to(false, false);
        }
        boolean zBooleanValue = ((Boolean) pair.component1()).booleanValue();
        boolean zBooleanValue2 = ((Boolean) pair.component2()).booleanValue();
        if (zBooleanValue) {
            FixedNextRenderersFactory fixedNextRenderersFactory = new FixedNextRenderersFactory(context);
            fixedNextRenderersFactory.setEnableDecoderFallback(true);
            fixedNextRenderersFactory.setExtensionRendererMode(zBooleanValue2 ? 2 : 1);
            defaultRenderersFactory = fixedNextRenderersFactory;
        } else {
            defaultRenderersFactory = new DefaultRenderersFactory(context);
        }
        DefaultRenderersFactory defaultRenderersFactory2 = defaultRenderersFactory;
        final SaveCaptionStyle style = CustomDecoder.INSTANCE.getStyle();
        TextOutput textOutput2 = new TextOutput() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$$ExternalSyntheticLambda5
            @Override // androidx.media3.exoplayer.text.TextOutput
            public final void onCues(CueGroup cueGroup) {
                CS3IPlayer.buildExoPlayer$lambda$0$1(this.f$0, style, cueGroup);
            }

            @Override // androidx.media3.exoplayer.text.TextOutput
            public /* synthetic */ void onCues(List list) {
                TextOutput.CC.$default$onCues(this, list);
            }
        };
        Renderer[] rendererArrCreateRenderers = defaultRenderersFactory2.createRenderers(eventHandler, videoRendererEventListener, audioRendererEventListener, textOutput2, metadataRendererOutput);
        Intrinsics.checkNotNullExpressionValue(rendererArrCreateRenderers, "createRenderers(...)");
        Renderer[] rendererArr = rendererArrCreateRenderers;
        ArrayList arrayList = new ArrayList(rendererArr.length);
        for (TextRenderer textRenderer : rendererArr) {
            if (textRenderer instanceof TextRenderer) {
                CustomDecoder.INSTANCE.setSubtitleOffset(j);
                CustomSubtitleDecoderFactory customSubtitleDecoderFactory = new CustomSubtitleDecoderFactory();
                TextRenderer textRenderer2 = new TextRenderer(textOutput2, eventHandler.getLooper(), customSubtitleDecoderFactory);
                textRenderer2.experimentalSetLegacyDecodingEnabled(true);
                currentTextRenderer = textRenderer2;
                currentSubtitleDecoder = customSubtitleDecoderFactory;
                textRenderer = textRenderer2;
            }
            arrayList.add(textRenderer);
        }
        return (Renderer[]) arrayList.toArray(new Renderer[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void buildExoPlayer$lambda$0$1(CS3IPlayer cS3IPlayer, SaveCaptionStyle saveCaptionStyle, CueGroup cue) {
        Cue.Builder builderBuildUpon;
        Cue.Builder text;
        Cue.Builder builderFixSubtitleAlignment;
        Cue.Builder builderApplyStyle;
        Intrinsics.checkNotNullParameter(cue, "cue");
        ImmutableList<Cue> cues = cue.cues;
        Intrinsics.checkNotNullExpressionValue(cues, "cues");
        List list = CollectionsKt.toList(cues);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : list) {
            if (((Cue) obj).bitmap != null) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list2 = (List) pair.component1();
        List list3 = (List) pair.component2();
        List<Cue> list4 = list2;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
        for (Cue cue2 : list4) {
            SubtitlesFragment.Companion companion = SubtitlesFragment.INSTANCE;
            CustomDecoder.Companion companion2 = CustomDecoder.INSTANCE;
            Cue.Builder builderBuildUpon2 = cue2.buildUpon();
            Intrinsics.checkNotNullExpressionValue(builderBuildUpon2, "buildUpon(...)");
            arrayList3.add(companion.applyStyle(companion2.fixSubtitleAlignment(builderBuildUpon2), saveCaptionStyle).build());
        }
        ArrayList arrayList4 = arrayList3;
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : list3) {
            Cue cue3 = (Cue) obj2;
            Pair pair2 = TuplesKt.to(Integer.valueOf(cue3.lineAnchor), Integer.valueOf((int) (cue3.position * 1000.0f)));
            Object obj3 = linkedHashMap.get(pair2);
            if (obj3 == null) {
                obj3 = (List) new ArrayList();
                linkedHashMap.put(pair2, obj3);
            }
            ((List) obj3).add(obj2);
        }
        ArrayList arrayList5 = new ArrayList();
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            List list5 = (List) ((Map.Entry) it.next()).getValue();
            hashSet.clear();
            StringsKt.clear(sb);
            Iterator it2 = list5.iterator();
            int i = 0;
            while (it2.hasNext()) {
                CharSequence charSequence = ((Cue) it2.next()).text;
                if (charSequence != null && hashSet.add(charSequence)) {
                    i++;
                    if (i > 1) {
                        sb.append('\n');
                    }
                    sb.append(StringsKt.trim(charSequence));
                }
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            Cue cue4 = (Cue) CollectionsKt.firstOrNull(list5);
            Cue cueBuild = (cue4 == null || (builderBuildUpon = cue4.buildUpon()) == null || (text = builderBuildUpon.setText(string)) == null || (builderFixSubtitleAlignment = CustomDecoder.INSTANCE.fixSubtitleAlignment(text)) == null || (builderApplyStyle = SubtitlesFragment.INSTANCE.applyStyle(builderFixSubtitleAlignment, saveCaptionStyle)) == null) ? null : builderApplyStyle.build();
            if (cueBuild != null) {
                arrayList5.add(cueBuild);
            }
        }
        List<Cue> listPlus = CollectionsKt.plus((Collection) arrayList4, (Iterable) arrayList5);
        SubtitleView subtitleView = cS3IPlayer.subtitleHelper.getSubtitleView();
        if (subtitleView != null) {
            subtitleView.setCues(listPlus);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DrmSessionManager buildExoPlayer$lambda$2$0(DefaultDrmSessionManager defaultDrmSessionManager, MediaItem it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return defaultDrmSessionManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DrmSessionManager buildExoPlayer$lambda$2$1(DefaultDrmSessionManager defaultDrmSessionManager, MediaItem it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return defaultDrmSessionManager;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void loadExo$default(CS3IPlayer cS3IPlayer, Context context, List list, List list2, List list3, HttpDataSource.Factory factory, int i, Object obj) {
        if ((i & 8) != 0) {
            list3 = CollectionsKt.emptyList();
        }
        List list4 = list3;
        if ((i & 16) != 0) {
            factory = null;
        }
        cS3IPlayer.loadExo(context, list, list2, list4, factory);
    }

    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [com.lagradost.cloudstream3.ui.player.PlayerEventSource, kotlin.jvm.internal.DefaultConstructorMarker] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    private final void loadExo(Context context, List<MediaItemSlice> mediaSlices, List<SingleSampleMediaSource> subSources, List<? extends MediaSource> audioSources, HttpDataSource.Factory onlineSource) {
        String str;
        int i;
        ?? r5;
        ExoPlayer exoPlayer;
        Log.i(CS3IPlayerKt.TAG, "loadExo");
        int i2 = PreferenceManager.getDefaultSharedPreferences(context).getInt(context.getString(AppContextUtils.INSTANCE.isUsingMobileData(context) ? R.string.quality_pref_mobile_data_key : R.string.quality_pref_key), Integer.MAX_VALUE);
        try {
            this.hasUsedFirstRender = false;
            try {
                int i3 = this.currentWindow;
                long j = this.playbackPosition;
                float f = this.playBackSpeed;
                long j2 = this.cacheSize;
                long j3 = this.videoBufferMs;
                boolean z = this.isPlaying;
                long j4 = this.currentSubtitleOffset;
                Integer numValueOf = Integer.valueOf(i2);
                str = CS3IPlayerKt.TAG;
                i = 2;
                try {
                    setExoPlayer(buildExoPlayer$default(this, context, mediaSlices, subSources, i3, j, f, j4, j2, j3, onlineSource, z, null, numValueOf, audioSources, 2048, null));
                    r5 = 0;
                    try {
                        event(new PlayerAttachedEvent(this.exoPlayer, null, 2, null));
                        ExoPlayer exoPlayer2 = this.exoPlayer;
                        if (exoPlayer2 != null) {
                            exoPlayer2.prepare();
                        }
                        if (onlineSource == null) {
                            long j5 = this.playbackPosition;
                            ExoPlayer exoPlayer3 = this.exoPlayer;
                            if (j5 > (exoPlayer3 != null ? exoPlayer3.getDuration() : 0L) && (exoPlayer = this.exoPlayer) != null) {
                                exoPlayer.addListener(new Player.Listener() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer.loadExo.1
                                    private boolean seekApplied;

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                                        Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onAudioSessionIdChanged(int i4) {
                                        Player.Listener.CC.$default$onAudioSessionIdChanged(this, i4);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
                                        Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onCues(CueGroup cueGroup) {
                                        Player.Listener.CC.$default$onCues(this, cueGroup);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onCues(List list) {
                                        Player.Listener.CC.$default$onCues(this, list);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
                                        Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onDeviceVolumeChanged(int i4, boolean z2) {
                                        Player.Listener.CC.$default$onDeviceVolumeChanged(this, i4, z2);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onEvents(Player player, Player.Events events) {
                                        Player.Listener.CC.$default$onEvents(this, player, events);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onIsLoadingChanged(boolean z2) {
                                        Player.Listener.CC.$default$onIsLoadingChanged(this, z2);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onIsPlayingChanged(boolean z2) {
                                        Player.Listener.CC.$default$onIsPlayingChanged(this, z2);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onLoadingChanged(boolean z2) {
                                        Player.Listener.CC.$default$onLoadingChanged(this, z2);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j6) {
                                        Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j6);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i4) {
                                        Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, i4);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                                        Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onMetadata(androidx.media3.common.Metadata metadata) {
                                        Player.Listener.CC.$default$onMetadata(this, metadata);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlayWhenReadyChanged(boolean z2, int i4) {
                                        Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z2, i4);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                                        Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i4) {
                                        Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, i4);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlayerError(PlaybackException playbackException) {
                                        Player.Listener.CC.$default$onPlayerError(this, playbackException);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
                                        Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlayerStateChanged(boolean z2, int i4) {
                                        Player.Listener.CC.$default$onPlayerStateChanged(this, z2, i4);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                                        Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPositionDiscontinuity(int i4) {
                                        Player.Listener.CC.$default$onPositionDiscontinuity(this, i4);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i4) {
                                        Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, i4);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onRenderedFirstFrame() {
                                        Player.Listener.CC.$default$onRenderedFirstFrame(this);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onRepeatModeChanged(int i4) {
                                        Player.Listener.CC.$default$onRepeatModeChanged(this, i4);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onSeekBackIncrementChanged(long j6) {
                                        Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j6);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onSeekForwardIncrementChanged(long j6) {
                                        Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j6);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onShuffleModeEnabledChanged(boolean z2) {
                                        Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z2);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z2) {
                                        Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z2);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onSurfaceSizeChanged(int i4, int i5) {
                                        Player.Listener.CC.$default$onSurfaceSizeChanged(this, i4, i5);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onTimelineChanged(Timeline timeline, int i4) {
                                        Player.Listener.CC.$default$onTimelineChanged(this, timeline, i4);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
                                        Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onTracksChanged(Tracks tracks) {
                                        Player.Listener.CC.$default$onTracksChanged(this, tracks);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                                        Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public /* synthetic */ void onVolumeChanged(float f2) {
                                        Player.Listener.CC.$default$onVolumeChanged(this, f2);
                                    }

                                    @Override // androidx.media3.common.Player.Listener
                                    public void onPlaybackStateChanged(int playbackState) {
                                        if (this.seekApplied || playbackState != 3) {
                                            return;
                                        }
                                        this.seekApplied = true;
                                        ExoPlayer exoPlayer4 = CS3IPlayer.this.exoPlayer;
                                        if (exoPlayer4 != null) {
                                            exoPlayer4.seekTo(CS3IPlayer.this.currentWindow, CS3IPlayer.this.playbackPosition);
                                        }
                                        ExoPlayer exoPlayer5 = CS3IPlayer.this.exoPlayer;
                                        if (exoPlayer5 != null) {
                                            exoPlayer5.removeListener(this);
                                        }
                                    }
                                });
                            }
                        }
                        ExoPlayer exoPlayer4 = this.exoPlayer;
                        if (exoPlayer4 != null) {
                            event(new StatusEvent(CSPlayerLoading.IsBuffering, CSPlayerLoading.IsBuffering, null, 4, null));
                            this.isPlaying = exoPlayer4.isPlaying();
                        }
                        if (mediaSlices.isEmpty() && subSources.isEmpty()) {
                            return;
                        }
                        LiveHelper.INSTANCE.registerPlayer(this.exoPlayer);
                        ExoPlayer exoPlayer5 = this.exoPlayer;
                        if (exoPlayer5 != null) {
                            AnonymousClass3 anonymousClass3 = new AnonymousClass3(context);
                            this.playerListener = anonymousClass3;
                            exoPlayer5.addListener(anonymousClass3);
                        }
                    } catch (Throwable th) {
                        th = th;
                        Log.e(str, "loadExo error", th);
                        event(new ErrorEvent(th, r5, i, r5));
                    }
                } catch (Throwable th2) {
                    th = th2;
                    r5 = 0;
                }
            } catch (Throwable th3) {
                th = th3;
                str = CS3IPlayerKt.TAG;
                i = 2;
                r5 = 0;
            }
        } catch (Throwable th4) {
            th = th4;
            str = CS3IPlayerKt.TAG;
            i = 2;
            r5 = 0;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.CS3IPlayer$loadExo$3, reason: invalid class name */
    /* JADX INFO: compiled from: CS3IPlayer.kt */
    @Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0003H\u0016¨\u0006\u0015"}, d2 = {"com/lagradost/cloudstream3/ui/player/CS3IPlayer$loadExo$3", "Landroidx/media3/common/Player$Listener;", "onTracksChanged", "", "tracks", "Landroidx/media3/common/Tracks;", "onPlayerStateChanged", "playWhenReady", "", "playbackState", "", "onPlayerError", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "Landroidx/media3/common/PlaybackException;", "onIsPlayingChanged", "isPlaying", "onPlaybackStateChanged", "onVideoSizeChanged", "videoSize", "Landroidx/media3/common/VideoSize;", "onRenderedFirstFrame", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AnonymousClass3 implements Player.Listener {
        final /* synthetic */ Context $context;

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onAudioSessionIdChanged(int i) {
            Player.Listener.CC.$default$onAudioSessionIdChanged(this, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
            Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onCues(CueGroup cueGroup) {
            Player.Listener.CC.$default$onCues(this, cueGroup);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onCues(List list) {
            Player.Listener.CC.$default$onCues(this, list);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onDeviceVolumeChanged(int i, boolean z) {
            Player.Listener.CC.$default$onDeviceVolumeChanged(this, i, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onEvents(Player player, Player.Events events) {
            Player.Listener.CC.$default$onEvents(this, player, events);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onIsLoadingChanged(boolean z) {
            Player.Listener.CC.$default$onIsLoadingChanged(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onLoadingChanged(boolean z) {
            Player.Listener.CC.$default$onLoadingChanged(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
            Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i) {
            Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onMetadata(androidx.media3.common.Metadata metadata) {
            Player.Listener.CC.$default$onMetadata(this, metadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i) {
            Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i) {
            Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
            Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
            Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPositionDiscontinuity(int i) {
            Player.Listener.CC.$default$onPositionDiscontinuity(this, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onRepeatModeChanged(int i) {
            Player.Listener.CC.$default$onRepeatModeChanged(this, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSeekBackIncrementChanged(long j) {
            Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
            Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
            Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
            Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
            Player.Listener.CC.$default$onSurfaceSizeChanged(this, i, i2);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onTimelineChanged(Timeline timeline, int i) {
            Player.Listener.CC.$default$onTimelineChanged(this, timeline, i);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
            Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
        }

        @Override // androidx.media3.common.Player.Listener
        public /* synthetic */ void onVolumeChanged(float f) {
            Player.Listener.CC.$default$onVolumeChanged(this, f);
        }

        AnonymousClass3(Context context) {
            this.$context = context;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTracksChanged(final Tracks tracks) {
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            final CS3IPlayer cS3IPlayer = CS3IPlayer.this;
            ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$loadExo$3$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return CS3IPlayer.AnonymousClass3.onTracksChanged$lambda$0(tracks, cS3IPlayer);
                }
            });
        }

        static final Unit onTracksChanged$lambda$0(Tracks tracks, CS3IPlayer cS3IPlayer) {
            SubtitleData subtitleData;
            String str;
            String strStripTrackId;
            ImmutableList<Tracks.Group> groups = tracks.getGroups();
            Intrinsics.checkNotNullExpressionValue(groups, "getGroups(...)");
            ArrayList arrayList = new ArrayList();
            for (Tracks.Group group : groups) {
                if (group.getType() == 3) {
                    arrayList.add(group);
                }
            }
            ArrayList<Tracks.Group> arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            for (Tracks.Group group2 : arrayList2) {
                Intrinsics.checkNotNull(group2);
                List formats = cS3IPlayer.getFormats(group2);
                ArrayList arrayList4 = new ArrayList();
                Iterator it = formats.iterator();
                while (it.hasNext()) {
                    String str2 = ((Format) ((Pair) it.next()).component1()).id;
                    Pair pair = (str2 == null || (strStripTrackId = cS3IPlayer.stripTrackId(str2)) == null) ? null : TuplesKt.to(strStripTrackId, Boolean.valueOf(group2.isSelected()));
                    if (pair != null) {
                        arrayList4.add(pair);
                    }
                }
                arrayList3.add(arrayList4);
            }
            cS3IPlayer.playerSelectedSubtitleTracks = CollectionsKt.flatten(arrayList3);
            ImmutableList<Tracks.Group> groups2 = tracks.getGroups();
            Intrinsics.checkNotNullExpressionValue(groups2, "getGroups(...)");
            ArrayList arrayList5 = new ArrayList();
            for (Tracks.Group group3 : groups2) {
                if (group3.getType() == 3) {
                    arrayList5.add(group3);
                }
            }
            List formats2 = cS3IPlayer.getFormats(arrayList5);
            ArrayList arrayList6 = new ArrayList();
            Iterator it2 = formats2.iterator();
            while (it2.hasNext()) {
                Format format = (Format) ((Pair) it2.next()).component1();
                if (format.id == null || format.language == null || ((str = format.language) != null && StringsKt.startsWith$default(str, "-", false, 2, (Object) null))) {
                    subtitleData = null;
                } else {
                    String strFromTagToLanguageName$default = SubtitleHelper.fromTagToLanguageName$default(SubtitleHelper.INSTANCE, format.language, null, 2, null);
                    if (strFromTagToLanguageName$default == null) {
                        strFromTagToLanguageName$default = format.language;
                        Intrinsics.checkNotNull(strFromTagToLanguageName$default);
                    }
                    String str3 = strFromTagToLanguageName$default;
                    String str4 = format.label;
                    if (str4 == null) {
                        str4 = "";
                    }
                    String str5 = str4;
                    String str6 = format.id;
                    Intrinsics.checkNotNull(str6);
                    String strStripTrackId2 = cS3IPlayer.stripTrackId(str6);
                    SubtitleOrigin subtitleOrigin = SubtitleOrigin.EMBEDDED_IN_VIDEO;
                    String str7 = format.sampleMimeType;
                    if (str7 == null) {
                        str7 = "application/x-subrip";
                    }
                    subtitleData = new SubtitleData(str3, str5, strStripTrackId2, subtitleOrigin, str7, MapsKt.emptyMap(), format.language);
                }
                if (subtitleData != null) {
                    arrayList6.add(subtitleData);
                }
            }
            cS3IPlayer.event(new EmbeddedSubtitlesFetchedEvent(arrayList6, null, 2, null));
            cS3IPlayer.event(new TracksChangedEvent(null, 1, null));
            cS3IPlayer.event(new SubtitlesUpdatedEvent(null, 1, null));
            return Unit.INSTANCE;
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            CSPlayerLoading cSPlayerLoading;
            ExoPlayer exoPlayer = CS3IPlayer.this.exoPlayer;
            if (exoPlayer != null) {
                CS3IPlayer cS3IPlayer = CS3IPlayer.this;
                CSPlayerLoading cSPlayerLoading2 = cS3IPlayer.isPlaying ? CSPlayerLoading.IsPlaying : CSPlayerLoading.IsPaused;
                if (playbackState == 2) {
                    cSPlayerLoading = CSPlayerLoading.IsBuffering;
                } else if (playbackState == 4) {
                    cSPlayerLoading = CSPlayerLoading.IsEnded;
                } else {
                    cSPlayerLoading = exoPlayer.isPlaying() ? CSPlayerLoading.IsPlaying : CSPlayerLoading.IsPaused;
                }
                cS3IPlayer.event(new StatusEvent(cSPlayerLoading2, cSPlayerLoading, null, 4, null));
                cS3IPlayer.isPlaying = exoPlayer.isPlaying();
            }
            if (playbackState == 3) {
                CS3IPlayer.this.onRenderFirst();
            }
            if (playWhenReady) {
                if (playbackState == 2) {
                    CS3IPlayer.updatedTime$default(CS3IPlayer.this, null, PlayerEventSource.Player, 1, null);
                } else {
                    if (playbackState != 4) {
                        return;
                    }
                    CS3IPlayer.this.event(new VideoEndedEvent(null, 1, null));
                }
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayerError(PlaybackException error) {
            long duration;
            ExoPlayer exoPlayer;
            Intrinsics.checkNotNullParameter(error, "error");
            if (error.errorCode == 2001 && ((exoPlayer = CS3IPlayer.this.exoPlayer) == null || exoPlayer.getDuration() != C.TIME_UNSET)) {
                ExoPlayer exoPlayer2 = CS3IPlayer.this.exoPlayer;
                if (exoPlayer2 != null) {
                    exoPlayer2.prepare();
                }
            } else if (error.errorCode == 1002) {
                ExoPlayer exoPlayer3 = CS3IPlayer.this.exoPlayer;
                if (exoPlayer3 != null) {
                    exoPlayer3.seekToDefaultPosition();
                }
                ExoPlayer exoPlayer4 = CS3IPlayer.this.exoPlayer;
                if (exoPlayer4 != null) {
                    exoPlayer4.prepare();
                }
            } else {
                boolean z = error.getCause() instanceof HlsPlaylistTracker.PlaylistStuckException;
                CS3IPlayer cS3IPlayer = CS3IPlayer.this;
                if (z) {
                    ExoPlayer exoPlayer5 = cS3IPlayer.exoPlayer;
                    if (exoPlayer5 != null) {
                        duration = exoPlayer5.getCurrentPosition();
                    } else {
                        ExoPlayer exoPlayer6 = CS3IPlayer.this.exoPlayer;
                        duration = exoPlayer6 != null ? exoPlayer6.getDuration() : 0L;
                    }
                    LiveManager liveManager = LiveHelper.INSTANCE.getLiveManager(CS3IPlayer.this.exoPlayer);
                    long timeAheadOfLive = liveManager != null ? liveManager.getTimeAheadOfLive(duration) : 0L;
                    CS3IPlayer cS3IPlayer2 = CS3IPlayer.this;
                    if (timeAheadOfLive > 100) {
                        ExoPlayer exoPlayer7 = cS3IPlayer2.exoPlayer;
                        if (exoPlayer7 != null) {
                            exoPlayer7.seekTo(duration - timeAheadOfLive);
                        }
                    } else {
                        ExoPlayer exoPlayer8 = cS3IPlayer2.exoPlayer;
                        if (exoPlayer8 != null) {
                            exoPlayer8.seekToDefaultPosition();
                        }
                    }
                    ExoPlayer exoPlayer9 = CS3IPlayer.this.exoPlayer;
                    if (exoPlayer9 != null) {
                        exoPlayer9.prepare();
                    }
                } else {
                    cS3IPlayer.event(new ErrorEvent(error, null, 2, null));
                }
            }
            Player.Listener.CC.$default$onPlayerError(this, error);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onIsPlayingChanged(boolean isPlaying) {
            Player.Listener.CC.$default$onIsPlayingChanged(this, isPlaying);
            if (isPlaying) {
                CS3IPlayer.this.event(new RequestAudioFocusEvent(null, 1, null));
                CS3IPlayer.this.onRenderFirst();
            }
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int playbackState) {
            SharedPreferences defaultSharedPreferences;
            Player.Listener.CC.$default$onPlaybackStateChanged(this, playbackState);
            if (playbackState != 2) {
                if (playbackState == 4 && (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.$context)) != null && defaultSharedPreferences.getBoolean(this.$context.getString(R.string.autoplay_next_key), true)) {
                    CS3IPlayer.this.handleEvent(CSPlayerEvent.NextEpisode, PlayerEventSource.Player);
                    return;
                }
                return;
            }
            CS3IPlayer.updatedTime$default(CS3IPlayer.this, null, PlayerEventSource.Player, 1, null);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onVideoSizeChanged(VideoSize videoSize) {
            Intrinsics.checkNotNullParameter(videoSize, "videoSize");
            Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
            CS3IPlayer.this.event(new ResizedEvent(videoSize.height, videoSize.width, null, 4, null));
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRenderedFirstFrame() {
            Player.Listener.CC.$default$onRenderedFirstFrame(this);
            CS3IPlayer.this.onRenderFirst();
            CS3IPlayer.updatedTime$default(CS3IPlayer.this, null, PlayerEventSource.Player, 1, null);
        }
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void addTimeStamps(List<VideoSkipStamp> timeStamps) {
        PlayerMessage playerMessageCreateMessage;
        PlayerMessage looper;
        PlayerMessage position;
        PlayerMessage deleteAfterDelivery;
        Intrinsics.checkNotNullParameter(timeStamps, "timeStamps");
        this.lastTimeStamps = timeStamps;
        for (VideoSkipStamp videoSkipStamp : timeStamps) {
            ExoPlayer exoPlayer = this.exoPlayer;
            if (exoPlayer != null && (playerMessageCreateMessage = exoPlayer.createMessage(new PlayerMessage.Target() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$$ExternalSyntheticLambda1
                @Override // androidx.media3.exoplayer.PlayerMessage.Target
                public final void handleMessage(int i, Object obj) {
                    CS3IPlayer.addTimeStamps$lambda$0$0(this.f$0, i, obj);
                }
            })) != null && (looper = playerMessageCreateMessage.setLooper(Looper.getMainLooper())) != null && (position = looper.setPosition(videoSkipStamp.getTimestamp().getStartMs())) != null && (deleteAfterDelivery = position.setDeleteAfterDelivery(false)) != null) {
                deleteAfterDelivery.send();
            }
        }
        updatedTime$default(this, null, PlayerEventSource.Player, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addTimeStamps$lambda$0$0(CS3IPlayer cS3IPlayer, int i, Object obj) {
        updatedTime$default(cS3IPlayer, null, PlayerEventSource.Player, 1, null);
    }

    public final void onRenderFirst() {
        List<Integer> list;
        if (this.hasUsedFirstRender) {
            return;
        }
        Log.i(CS3IPlayerKt.TAG, "Rendered first frame");
        this.hasUsedFirstRender = true;
        setPreferredSubtitles(this.currentSubtitles);
        ExoPlayer exoPlayer = this.exoPlayer;
        Format videoFormat = exoPlayer != null ? exoPlayer.getVideoFormat() : null;
        Integer numValueOf = videoFormat != null ? Integer.valueOf(videoFormat.width) : null;
        Integer numValueOf2 = videoFormat != null ? Integer.valueOf(videoFormat.height) : null;
        if (numValueOf2 == null || numValueOf == null) {
            return;
        }
        event(new ResizedEvent(numValueOf2.intValue(), numValueOf.intValue(), null, 4, null));
        updatedTime$default(this, null, null, 3, null);
        ExoPlayer exoPlayer2 = this.exoPlayer;
        if (exoPlayer2 == null || (list = this.requestedListeningPercentages) == null) {
            return;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            exoPlayer2.createMessage(new PlayerMessage.Target() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$$ExternalSyntheticLambda0
                @Override // androidx.media3.exoplayer.PlayerMessage.Target
                public final void handleMessage(int i, Object obj) {
                    CS3IPlayer.updatedTime$default(this.f$0, null, null, 3, null);
                }
            }).setLooper(Looper.getMainLooper()).setPosition((exoPlayer2.getContentDuration() * ((long) ((Number) it.next()).intValue())) / 100).setDeleteAfterDelivery(false).send();
        }
    }

    private final void loadOfflinePlayer(Context context, ExtractorUri data) {
        Log.i(CS3IPlayerKt.TAG, "loadOfflinePlayer");
        try {
            this.currentDownloadedFile = data;
            Companion companion = INSTANCE;
            MediaItem mediaItem = companion.getMediaItem("video/mp4", data.getUri());
            Pair<List<SingleSampleMediaSource>, List<SubtitleData>> subSources = getSubSources(companion.createOfflineSource(context), this.subtitleHelper, null);
            List<SingleSampleMediaSource> listComponent1 = subSources.component1();
            this.subtitleHelper.setActiveSubtitles(CollectionsKt.toSet(subSources.component2()));
            loadExo$default(this, context, CollectionsKt.listOf(new MediaItemSlice(mediaItem, Long.MIN_VALUE, null, 4, null)), listComponent1, null, null, 24, null);
        } catch (Throwable th) {
            Log.e(CS3IPlayerKt.TAG, "loadOfflinePlayer error", th);
            event(new ErrorEvent(th, null, 2, null));
        }
    }

    private final Pair<List<SingleSampleMediaSource>, List<SubtitleData>> getSubSources(DataSource.Factory offlineSourceFactory, PlayerSubtitleHelper subHelper, Interceptor interceptor) {
        SingleSampleMediaSource singleSampleMediaSourceCreateMediaSource;
        ArrayList arrayList = new ArrayList();
        Set<SubtitleData> allSubtitles = subHelper.getAllSubtitles();
        ArrayList arrayList2 = new ArrayList();
        for (SubtitleData subtitleData : allSubtitles) {
            MediaItem.SubtitleConfiguration subtitleConfigurationBuild = new MediaItem.SubtitleConfiguration.Builder(Uri.parse(subtitleData.getFixedUrl())).setMimeType(subtitleData.getMimeType()).setLanguage("_" + subtitleData.getName()).setId(subtitleData.getId()).setSelectionFlags(0).build();
            Intrinsics.checkNotNullExpressionValue(subtitleConfigurationBuild, "build(...)");
            int i = WhenMappings.$EnumSwitchMapping$2[subtitleData.getOrigin().ordinal()];
            if (i == 1 || i == 2) {
                if (offlineSourceFactory != null) {
                    arrayList.add(subtitleData);
                    singleSampleMediaSourceCreateMediaSource = new SingleSampleMediaSource.Factory(offlineSourceFactory).createMediaSource(subtitleConfigurationBuild, C.TIME_UNSET);
                } else {
                    singleSampleMediaSourceCreateMediaSource = null;
                }
            } else if (i == 3) {
                HttpDataSource.Factory factoryCreateOnlineSource = INSTANCE.createOnlineSource(subtitleData.getHeaders(), interceptor);
                arrayList.add(subtitleData);
                singleSampleMediaSourceCreateMediaSource = new SingleSampleMediaSource.Factory(factoryCreateOnlineSource).createMediaSource(subtitleConfigurationBuild, C.TIME_UNSET);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            if (singleSampleMediaSourceCreateMediaSource != null) {
                arrayList2.add(singleSampleMediaSourceCreateMediaSource);
            }
        }
        return new Pair<>(arrayList2, arrayList);
    }

    private final List<MediaSource> getAudioSources(List<AudioFile> audioTracks, Interceptor interceptor) {
        MediaSource mediaSourceCreateMediaSource;
        ArrayList arrayList = new ArrayList();
        for (AudioFile audioFile : audioTracks) {
            try {
                Companion companion = INSTANCE;
                mediaSourceCreateMediaSource = new DefaultMediaSourceFactory(companion.createOnlineSource(audioFile.getHeaders(), interceptor)).createMediaSource(companion.getMediaItem("audio/x-unknown", audioFile.getUrl()));
            } catch (Exception e) {
                Log.e(CS3IPlayerKt.TAG, "Failed to create audio source for " + audioFile.getUrl() + ": " + e.getMessage());
                mediaSourceCreateMediaSource = null;
            }
            if (mediaSourceCreateMediaSource != null) {
                arrayList.add(mediaSourceCreateMediaSource);
            }
        }
        return arrayList;
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public boolean isActive() {
        return this.exoPlayer != null;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.player.CS3IPlayer$loadTorrent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CS3IPlayer.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/player/CS3IPlayer;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.CS3IPlayer$loadTorrent$1", f = "CS3IPlayer.kt", i = {}, l = {1782}, m = "invokeSuspend", n = {}, nl = {1783}, s = {}, v = 2)
    static final class C08571 extends SuspendLambda implements Function3<CoroutineScope, CS3IPlayer, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ ExtractorLink $link;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08571(ExtractorLink extractorLink, Context context, Continuation<? super C08571> continuation) {
            super(3, continuation);
            this.$link = extractorLink;
            this.$context = context;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, CS3IPlayer cS3IPlayer, Continuation<? super Unit> continuation) {
            return CS3IPlayer.this.new C08571(this.$link, this.$context, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (CS3IPlayer.this.exoPlayer == null) {
                        return Unit.INSTANCE;
                    }
                    this.label = 1;
                    obj = Torrent.INSTANCE.transformLink(this.$link, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Pair pair = (Pair) obj;
                final ExtractorLink extractorLink = (ExtractorLink) pair.component1();
                final String hash = ((Torrent.TorrentStatus) pair.component2()).getHash();
                if (CS3IPlayer.this.exoPlayer == null) {
                    return Unit.INSTANCE;
                }
                Coroutines coroutines = Coroutines.INSTANCE;
                final CS3IPlayer cS3IPlayer = CS3IPlayer.this;
                final Context context = this.$context;
                coroutines.runOnMainThread(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$loadTorrent$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CS3IPlayer.C08571.invokeSuspend$lambda$0(cS3IPlayer, hash, context, extractorLink);
                    }
                });
                return Unit.INSTANCE;
            } catch (Throwable th) {
                CS3IPlayer.this.event(new ErrorEvent(th, null, 2, null));
            }
        }

        static final Unit invokeSuspend$lambda$0(CS3IPlayer cS3IPlayer, String str, Context context, ExtractorLink extractorLink) throws DebugException {
            if (cS3IPlayer.exoPlayer == null) {
                return Unit.INSTANCE;
            }
            CS3IPlayer.releasePlayer$default(cS3IPlayer, false, 1, null);
            if (str != null) {
                cS3IPlayer.torrentEventLooper(str);
            }
            CS3IPlayer.loadOnlinePlayer$default(cS3IPlayer, context, extractorLink, false, 4, null);
            return Unit.INSTANCE;
        }
    }

    private final void loadTorrent(Context context, ExtractorLink link) {
        Coroutines.INSTANCE.ioSafe(this, new C08571(link, context, null));
    }

    static /* synthetic */ void loadOnlinePlayer$default(CS3IPlayer cS3IPlayer, Context context, ExtractorLink extractorLink, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        cS3IPlayer.loadOnlinePlayer(context, extractorLink, z);
    }

    private final void loadOnlinePlayer(final Context context, final ExtractorLink link, boolean retry) {
        String str;
        ArrayList arrayListListOf;
        ArrayList arrayList;
        Log.i(CS3IPlayerKt.TAG, "loadOnlinePlayer " + link);
        try {
            int i = WhenMappings.$EnumSwitchMapping$3[link.getType().ordinal()];
            if (i == 1) {
                str = "application/x-mpegURL";
            } else if (i == 2) {
                str = "application/dash+xml";
            } else if (i == 3) {
                str = "video/mp4";
            } else {
                if (i != 4 && i != 5) {
                    throw new NoWhenBranchMatchedException();
                }
                List listSorted = ArraysKt.sorted((Comparable[]) TvType.getEntries().toArray(new TvType[0]));
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : listSorted) {
                    if (((TvType) obj) != TvType.NSFW) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = arrayList2;
                ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    arrayList4.add(Integer.valueOf(((TvType) it.next()).ordinal()));
                }
                ArrayList arrayList5 = arrayList4;
                ArrayList arrayList6 = arrayList5;
                ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList6, 10));
                Iterator it2 = arrayList6.iterator();
                while (it2.hasNext()) {
                    arrayList7.add(String.valueOf(((Number) it2.next()).intValue()));
                }
                try {
                    Set<String> stringSet = PreferenceManager.getDefaultSharedPreferences(context).getStringSet(context.getString(R.string.prefer_media_type_key), CollectionsKt.toSet(arrayList7));
                    if (stringSet != null) {
                        ArrayList arrayList8 = new ArrayList();
                        for (String str2 : stringSet) {
                            Intrinsics.checkNotNull(str2);
                            Integer intOrNull = StringsKt.toIntOrNull(str2);
                            if (intOrNull != null) {
                                intOrNull.intValue();
                            } else {
                                intOrNull = null;
                            }
                            if (intOrNull != null) {
                                arrayList8.add(intOrNull);
                            }
                        }
                        arrayList = arrayList8;
                    } else {
                        arrayList = null;
                    }
                } catch (Throwable unused) {
                }
                if (arrayList != null) {
                    arrayList5 = arrayList;
                }
                if (!arrayList5.contains(Integer.valueOf(TvType.Torrent.ordinal()))) {
                    String string = context.getString(R.string.torrent_preferred_media);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    event(new ErrorEvent(new ErrorLoadingException(string), null, 2, null));
                    return;
                }
                if (Intrinsics.areEqual((Object) Torrent.INSTANCE.getHasAcceptedTorrentForThisSession(), (Object) false)) {
                    String string2 = context.getString(R.string.torrent_not_accepted);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    event(new ErrorEvent(new ErrorLoadingException(string2), null, 2, null));
                    return;
                }
                if (!retry) {
                    releasePlayer$default(this, false, 1, null);
                    loadExo$default(this, context, CollectionsKt.emptyList(), CollectionsKt.emptyList(), null, null, 24, null);
                }
                event(new StatusEvent(CSPlayerLoading.IsPlaying, CSPlayerLoading.IsBuffering, null, 4, null));
                if (Intrinsics.areEqual((Object) Torrent.INSTANCE.getHasAcceptedTorrentForThisSession(), (Object) true)) {
                    loadTorrent(context, link);
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$$ExternalSyntheticLambda7
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i2) {
                        CS3IPlayer.loadOnlinePlayer$lambda$4(this.f$0, context, link, dialogInterface, i2);
                    }
                };
                AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
                AlertDialog alertDialogShow = builder.setTitle(R.string.play_torrent_button).setMessage(R.string.torrent_info).setCancelable(false).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$$ExternalSyntheticLambda8
                    @Override // android.content.DialogInterface.OnCancelListener
                    public final void onCancel(DialogInterface dialogInterface) {
                        CS3IPlayer.loadOnlinePlayer$lambda$5(context, this, dialogInterface);
                    }
                }).setPositiveButton(R.string.ok, onClickListener).setNegativeButton(R.string.go_back, onClickListener).show();
                Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
                AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
                return;
            }
            this.currentLink = link;
            if (this.ignoreSSL) {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                Intrinsics.checkNotNullExpressionValue(sSLContext, "getInstance(...)");
                sSLContext.init(null, new SSLTrustManager[]{new SSLTrustManager()}, new SecureRandom());
                sSLContext.createSSLEngine();
                HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { // from class: com.lagradost.cloudstream3.ui.player.CS3IPlayer$$ExternalSyntheticLambda9
                    @Override // javax.net.ssl.HostnameVerifier
                    public final boolean verify(String str3, SSLSession sSLSession) {
                        return CS3IPlayer.loadOnlinePlayer$lambda$6(str3, sSLSession);
                    }
                });
                HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
            }
            if (!(link instanceof ExtractorLinkPlayList)) {
                if (link instanceof DrmExtractorLink) {
                    MediaItem mediaItem = INSTANCE.getMediaItem(str, ((DrmExtractorLink) link).getUrl());
                    String kid = ((DrmExtractorLink) link).getKid();
                    String key = ((DrmExtractorLink) link).getKey();
                    Uuid uuid = ((DrmExtractorLink) link).getUuid();
                    arrayListListOf = CollectionsKt.listOf(new MediaItemSlice(mediaItem, Long.MIN_VALUE, new DrmMetadata(kid, key, new UUID(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits()), ((DrmExtractorLink) link).getKty(), ((DrmExtractorLink) link).getLicenseUrl(), ((DrmExtractorLink) link).getKeyRequestParameters())));
                } else {
                    arrayListListOf = CollectionsKt.listOf(new MediaItemSlice(INSTANCE.getMediaItem(str, link.getUrl()), Long.MIN_VALUE, null, 4, null));
                }
            } else {
                List<PlayListItem> playlist = ((ExtractorLinkPlayList) link).getPlaylist();
                ArrayList arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(playlist, 10));
                for (PlayListItem playListItem : playlist) {
                    arrayList9.add(new MediaItemSlice(INSTANCE.getMediaItem(str, playListItem.getUrl()), playListItem.getDurationUs(), null, 4, null));
                }
                arrayListListOf = arrayList9;
            }
            List<MediaItemSlice> list = arrayListListOf;
            if (this.playbackPosition == 0 && (link.getType() == ExtractorLinkType.M3U8 || link.getType() == ExtractorLinkType.DASH)) {
                this.playbackPosition = C.TIME_UNSET;
            }
            MainAPI apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(link.getSource());
            Interceptor videoInterceptor = apiFromNameNull != null ? apiFromNameNull.getVideoInterceptor(link) : null;
            Companion companion = INSTANCE;
            HttpDataSource.Factory factoryCreateVideoSource = companion.createVideoSource(link, companion.tryCreateEngine(context, this.simpleCacheSize), videoInterceptor);
            Pair<List<SingleSampleMediaSource>, List<SubtitleData>> subSources = getSubSources(companion.createOfflineSource(context), this.subtitleHelper, videoInterceptor);
            List<SingleSampleMediaSource> listComponent1 = subSources.component1();
            List<SubtitleData> listComponent2 = subSources.component2();
            List<MediaSource> audioSources = getAudioSources(link.getAudioTracks(), videoInterceptor);
            this.subtitleHelper.setActiveSubtitles(CollectionsKt.toSet(listComponent2));
            loadExo(context, list, listComponent1, audioSources, factoryCreateVideoSource);
        } catch (Throwable th) {
            Log.e(CS3IPlayerKt.TAG, "loadOnlinePlayer error", th);
            event(new ErrorEvent(th, null, 2, null));
        }
    }

    static final void loadOnlinePlayer$lambda$4(CS3IPlayer cS3IPlayer, Context context, ExtractorLink extractorLink, DialogInterface dialogInterface, int i) {
        if (i != -2) {
            if (i != -1) {
                return;
            }
            Torrent.INSTANCE.setHasAcceptedTorrentForThisSession(true);
            cS3IPlayer.loadTorrent(context, extractorLink);
            return;
        }
        Torrent.INSTANCE.setHasAcceptedTorrentForThisSession(false);
        String string = context.getString(R.string.torrent_not_accepted);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        cS3IPlayer.event(new ErrorEvent(new ErrorLoadingException(string), null, 2, null));
    }

    static final void loadOnlinePlayer$lambda$5(Context context, CS3IPlayer cS3IPlayer, DialogInterface dialogInterface) {
        String string = context.getString(R.string.torrent_not_accepted);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        cS3IPlayer.event(new ErrorEvent(new ErrorLoadingException(string), null, 2, null));
    }

    @Override // com.lagradost.cloudstream3.ui.player.IPlayer
    public void reloadPlayer(Context context) throws DebugException {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i(CS3IPlayerKt.TAG, "reloadPlayer");
        releasePlayer(false);
        ExtractorLink extractorLink = this.currentLink;
        if (extractorLink != null) {
            loadOnlinePlayer$default(this, context, extractorLink, false, 4, null);
            return;
        }
        ExtractorUri extractorUri = this.currentDownloadedFile;
        if (extractorUri != null) {
            loadOfflinePlayer(context, extractorUri);
        }
    }

    private final void setExoPlayer(ExoPlayer exoPlayer) throws DebugException {
        if (!AppDebug.INSTANCE.isDebug() || this.exoPlayer == null || exoPlayer == null) {
            this.exoPlayer = exoPlayer;
            return;
        }
        throw new DebugException("Previous player instance should be released!");
    }
}
