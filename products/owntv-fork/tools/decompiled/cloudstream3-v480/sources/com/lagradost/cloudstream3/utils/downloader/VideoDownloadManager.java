package com.lagradost.cloudstream3.utils.downloader;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.PendingIntentCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.preference.PreferenceManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.IDownloadableMinimum;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.services.VideoDownloadService;
import com.lagradost.cloudstream3.ui.player.RepoLinkGenerator;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.player.source_priority.QualityDataHelper;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreKt;
import com.lagradost.cloudstream3.utils.Event;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.M3u8Helper;
import com.lagradost.cloudstream3.utils.M3u8Helper2;
import com.lagradost.cloudstream3.utils.SubtitleUtils;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.safefile.SafeFile;
import com.lagradost.safefile.SafeFileKt;
import io.ktor.http.ContentDisposition;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000þ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\n\u0093\u0001\u0094\u0001\u0095\u0001\u0096\u0001\u0097\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\f\u0010F\u001a\u00020G*\u00020\u0007H\u0002J\u000e\u0010H\u001a\u00020G2\u0006\u0010\u0006\u001a\u00020\u0007J\u0086\u0001\u0010I\u001a\u0004\u0018\u00010J2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010K\u001a\u0004\u0018\u00010\u00112\b\u0010L\u001a\u0004\u0018\u00010\u00112\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u0002032\u0006\u0010P\u001a\u00020,2\u0006\u0010Q\u001a\u00020,2\u0018\u0010R\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020G0S2\n\b\u0002\u0010T\u001a\u0004\u0018\u00010,2\n\b\u0002\u0010U\u001a\u0004\u0018\u00010,2\u0006\u0010V\u001a\u00020,H\u0083@¢\u0006\u0002\u0010WJ0\u0010X\u001a\u00020Y2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010Z\u001a\u00020\u00112\b\u0010[\u001a\u0004\u0018\u00010\u00112\u0006\u0010\\\u001a\u00020\u00112\u0006\u0010]\u001a\u00020EJ0\u0010X\u001a\u00020Y2\u0006\u0010^\u001a\u00020_2\u0006\u0010Z\u001a\u00020\u00112\b\u0010[\u001a\u0004\u0018\u00010\u00112\u0006\u0010\\\u001a\u00020\u00112\u0006\u0010]\u001a\u00020EJX\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020\u00112\u0012\u0010c\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110d2\u0006\u0010e\u001a\u00020\u00112\u0006\u0010f\u001a\u00020,2\b\b\u0002\u0010g\u001a\u00020,2\b\b\u0002\u0010h\u001a\u00020\u00052\b\b\u0002\u0010i\u001a\u00020,H\u0086@¢\u0006\u0002\u0010jJp\u0010k\u001a\u00020&2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010l\u001a\u00020m2\u0006\u0010Z\u001a\u00020\u00112\u0006\u0010[\u001a\u00020\u00112\u0006\u0010\\\u001a\u00020\u00112\u0006\u0010]\u001a\u00020E2\b\u0010n\u001a\u0004\u0018\u00010\u00052\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00020q\u0012\u0004\u0012\u00020G0p2\b\b\u0002\u0010r\u001a\u00020\u00052\b\b\u0002\u0010s\u001a\u00020,H\u0086@¢\u0006\u0002\u0010tJ`\u0010u\u001a\u00020&2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010l\u001a\u00020v2\u0006\u0010Z\u001a\u00020\u00112\u0006\u0010[\u001a\u00020\u00112\b\u0010n\u001a\u0004\u0018\u00010\u00052\b\u0010w\u001a\u0004\u0018\u00010\u00052\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00020q\u0012\u0004\u0012\u00020G0p2\b\b\u0002\u0010r\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010xJ\u0018\u0010y\u001a\u00020\u00112\u0006\u0010Z\u001a\u00020\u00112\u0006\u0010\\\u001a\u00020\u0011H\u0002J^\u0010z\u001a\u00020&2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010K\u001a\u0004\u0018\u00010\u00112\b\u0010[\u001a\u0004\u0018\u00010\u00112\u0006\u0010M\u001a\u00020N2\u0006\u0010l\u001a\u00020v2\u0018\u0010R\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020G0S2\b\b\u0002\u0010]\u001a\u00020EH\u0082@¢\u0006\u0002\u0010{J\u0018\u0010|\u001a\u0004\u0018\u00010}2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010~\u001a\u00020\u0005JD\u0010\u007f\u001a\u00020G2\u0006\u0010\u0006\u001a\u00020\u00072\r\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\b\u0010\u0081\u0001\u001a\u00030\u0082\u00012\u001b\b\u0002\u0010\u0083\u0001\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b\u0012\u0004\u0012\u00020G0pJ\u0019\u0010\u0084\u0001\u001a\u00020E2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010~\u001a\u00020\u0005H\u0002J\u0019\u0010\u0085\u0001\u001a\u00020E2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010~\u001a\u00020\u0005H\u0002J\u001a\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0087\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010~\u001a\u00020\u0005J\u001a\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010~\u001a\u00020\u0005JA\u0010\u008a\u0001\u001a\u00020N2\b\u0010\u008b\u0001\u001a\u00030\u008c\u00012\u0007\u0010\u008d\u0001\u001a\u00020\u00112\u0007\u0010\u008e\u0001\u001a\u00020\u00112\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u00112\u0007\u0010\u0090\u0001\u001a\u00020E2\b\u0010\u0091\u0001\u001a\u00030\u0092\u0001R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0019\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\u001b\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0016R\u0011\u0010\u001d\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\u001f\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b \u0010\u0016R\u0011\u0010!\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\"\u0010\u0016R\u0011\u0010#\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b$\u0010\u0016R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000R-\u00101\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020302j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000203`4¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R#\u00107\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002030908¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000508¢\u0006\b\n\u0000\u001a\u0004\b=\u0010;R#\u0010>\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020?0908¢\u0006\b\n\u0000\u001a\u0004\b@\u0010;R)\u0010A\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020,0B08¢\u0006\b\n\u0000\u001a\u0004\bC\u0010;R\u000e\u0010D\u001a\u00020EX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0098\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager;", "", "<init>", "()V", "maxConcurrentDownloads", "", "context", "Landroid/content/Context;", "maxConcurrentConnections", "_currentDownloads", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "currentDownloads", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentDownloads", "()Lkotlinx/coroutines/flow/StateFlow;", "TAG", "", "DOWNLOAD_NOTIFICATION_TAG", "USER_AGENT", "imgDone", "getImgDone", "()I", "imgDownloading", "getImgDownloading", "imgPaused", "getImgPaused", "imgStopped", "getImgStopped", "imgError", "getImgError", "pressToPauseIcon", "getPressToPauseIcon", "pressToResumeIcon", "getPressToResumeIcon", "pressToStopIcon", "getPressToStopIcon", "DOWNLOAD_INVALID_INPUT", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadStatus;", "DOWNLOAD_SUCCESS", "DOWNLOAD_STOPPED", "DOWNLOAD_FAILED", "DOWNLOAD_PARTIAL_SUCCESS", "DOWNLOAD_PARTIAL_MIN_SIZE", "", "DOWNLOAD_BAD_CONFIG", "KEY_RESUME_PACKAGES", "KEY_DOWNLOAD_INFO", "KEY_RESUME_IN_QUEUE", "downloadStatus", "Ljava/util/HashMap;", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "Lkotlin/collections/HashMap;", "getDownloadStatus", "()Ljava/util/HashMap;", "downloadStatusEvent", "Lcom/lagradost/cloudstream3/utils/Event;", "Lkotlin/Pair;", "getDownloadStatusEvent", "()Lcom/lagradost/cloudstream3/utils/Event;", "downloadDeleteEvent", "getDownloadDeleteEvent", "downloadEvent", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadActionType;", "getDownloadEvent", "downloadProgressEvent", "Lkotlin/Triple;", "getDownloadProgressEvent", "hasCreatedNotChannel", "", "createNotificationChannel", "", "cancelAllDownloadNotifications", "createDownloadNotification", "Landroid/app/Notification;", "source", "linkName", "ep", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;", "state", "progress", "total", "notificationCallback", "Lkotlin/Function2;", "hlsProgress", "hlsTotal", "bytesPerSecond", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;JJLkotlin/jvm/functions/Function2;Ljava/lang/Long;Ljava/lang/Long;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setupStream", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$StreamData;", "name", "folder", "extension", "tryResume", "baseFile", "Lcom/lagradost/safefile/SafeFile;", "streamLazy", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$LazyStreamDownloadData;", ImagesContract.URL, "headers", "", "referer", "startByte", "chuckSize", "bufferSize", "maximumSmallSize", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;JJIJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadThing", "link", "Lcom/lagradost/cloudstream3/IDownloadableMinimum;", "parentId", "createNotificationCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$CreateNotificationMetadata;", "parallelConnections", "minimumSize", "(Landroid/content/Context;Lcom/lagradost/cloudstream3/IDownloadableMinimum;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;Lkotlin/jvm/functions/Function1;IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadHLS", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "startIndex", "(Landroid/content/Context;Lcom/lagradost/cloudstream3/utils/ExtractorLink;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDisplayName", "downloadSingleEpisode", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;Lcom/lagradost/cloudstream3/utils/ExtractorLink;Lkotlin/jvm/functions/Function2;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDownloadFileInfo", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadedFileInfoResult;", "id", "deleteFilesAndUpdateSettings", "ids", "scope", "Lkotlinx/coroutines/CoroutineScope;", "onComplete", "deleteFileAndUpdateSettings", "deleteFile", "getDownloadResumePackage", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadResumePackage;", "getDownloadQueuePackage", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "getDownloadEpisodeMetadata", "episode", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "titleName", "apiName", "currentPoster", "currentIsMovie", "tvType", "Lcom/lagradost/cloudstream3/TvType;", "DownloadType", "DownloadActionType", "DownloadMetaData", "LazyStreamDownloadData", "EpisodeDownloadInstance", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class VideoDownloadManager {
    private static final DownloadObjects.DownloadStatus DOWNLOAD_BAD_CONFIG;
    private static final DownloadObjects.DownloadStatus DOWNLOAD_FAILED;
    private static final DownloadObjects.DownloadStatus DOWNLOAD_INVALID_INPUT;
    private static final String DOWNLOAD_NOTIFICATION_TAG = "FROM_DOWNLOADER";
    public static final long DOWNLOAD_PARTIAL_MIN_SIZE = 52428800;
    private static final DownloadObjects.DownloadStatus DOWNLOAD_PARTIAL_SUCCESS;
    private static final DownloadObjects.DownloadStatus DOWNLOAD_STOPPED;
    private static final DownloadObjects.DownloadStatus DOWNLOAD_SUCCESS;
    public static final VideoDownloadManager INSTANCE = new VideoDownloadManager();
    public static final String KEY_DOWNLOAD_INFO = "download_info";
    public static final String KEY_RESUME_IN_QUEUE = "download_resume_queue_key";
    public static final String KEY_RESUME_PACKAGES = "download_resume_2";
    public static final String TAG = "VDM";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";
    private static final MutableStateFlow<Set<Integer>> _currentDownloads;
    private static final StateFlow<Set<Integer>> currentDownloads;
    private static final Event<Integer> downloadDeleteEvent;
    private static final Event<Pair<Integer, DownloadActionType>> downloadEvent;
    private static final Event<Triple<Integer, Long, Long>> downloadProgressEvent;
    private static final HashMap<Integer, DownloadType> downloadStatus;
    private static final Event<Pair<Integer, DownloadType>> downloadStatusEvent;
    private static boolean hasCreatedNotChannel;

    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadActionType;", "", "<init>", "(Ljava/lang/String;I)V", "Pause", "Resume", "Stop", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum DownloadActionType {
        Pause,
        Resume,
        Stop;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<DownloadActionType> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "", "<init>", "(Ljava/lang/String;I)V", "IsPaused", "IsDownloading", "IsDone", "IsFailed", "IsStopped", "IsPending", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum DownloadType {
        IsPaused,
        IsDownloading,
        IsDone,
        IsFailed,
        IsStopped,
        IsPending;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<DownloadType> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[DownloadType.values().length];
            try {
                iArr[DownloadType.IsDone.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DownloadType.IsDownloading.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DownloadType.IsPaused.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DownloadType.IsFailed.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DownloadType.IsStopped.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DownloadType.IsPending.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[DownloadActionType.values().length];
            try {
                iArr2[DownloadActionType.Resume.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[DownloadActionType.Pause.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[DownloadActionType.Stop.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ExtractorLinkType.values().length];
            try {
                iArr3[ExtractorLinkType.M3U8.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr3[ExtractorLinkType.VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$createDownloadNotification$1, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager", f = "DownloadManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {306}, m = "createDownloadNotification", n = {"context", "source", "linkName", "ep", "state", "notificationCallback", "hlsProgress", "hlsTotal", "builder", "rowTwoExtra", "rowTwo", "downloadFormat", "progress", "total", "bytesPerSecond"}, nl = {309}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "J$0", "J$1", "J$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        long J$1;
        long J$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return VideoDownloadManager.this.createDownloadNotification(null, null, null, null, null, 0L, 0L, null, null, null, 0L, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadSingleEpisode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager", f = "DownloadManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1526, 1538}, m = "downloadSingleEpisode", n = {"context", "source", "folder", "ep", "link", "notificationCallback", "name", "extractorJob", "callback", "startIndex", "tryResume", "context", "source", "folder", "ep", "link", "notificationCallback", "name", "extractorJob", "callback", "tryResume"}, nl = {1558, 1558}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0"}, v = 2)
    static final class C09601 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C09601(Continuation<? super C09601> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return VideoDownloadManager.this.downloadSingleEpisode(null, null, null, null, null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$streamLazy$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager", f = "DownloadManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {899, 915}, m = "streamLazy", n = {ImagesContract.URL, "headers", "referer", "startByte", "chuckSize", "bufferSize", "maximumSmallSize", ImagesContract.URL, "headers", "referer", "headRequest", "contentLength", "startByte", "chuckSize", "bufferSize", "maximumSmallSize"}, nl = {900, 931}, s = {"L$0", "L$1", "L$2", "J$0", "J$1", "I$0", "J$2", "L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "J$1", "I$0", "J$2"}, v = 2)
    static final class C09621 extends ContinuationImpl {
        int I$0;
        long J$0;
        long J$1;
        long J$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C09621(Continuation<? super C09621> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return VideoDownloadManager.this.streamLazy(null, null, null, 0L, 0L, 0, 0L, this);
        }
    }

    private VideoDownloadManager() {
    }

    public final int maxConcurrentDownloads(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultSharedPreferences != null) {
            return defaultSharedPreferences.getInt(context.getString(R.string.download_parallel_key), 3);
        }
        return 3;
    }

    private final int maxConcurrentConnections(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultSharedPreferences != null) {
            return defaultSharedPreferences.getInt(context.getString(R.string.download_concurrent_key), 3);
        }
        return 3;
    }

    static {
        MutableStateFlow<Set<Integer>> MutableStateFlow = StateFlowKt.MutableStateFlow(SetsKt.emptySet());
        _currentDownloads = MutableStateFlow;
        currentDownloads = MutableStateFlow;
        DOWNLOAD_INVALID_INPUT = new DownloadObjects.DownloadStatus(false, true, false);
        DOWNLOAD_SUCCESS = new DownloadObjects.DownloadStatus(false, false, true);
        DOWNLOAD_STOPPED = new DownloadObjects.DownloadStatus(false, false, true);
        DOWNLOAD_FAILED = new DownloadObjects.DownloadStatus(true, true, false);
        DOWNLOAD_PARTIAL_SUCCESS = new DownloadObjects.DownloadStatus(true, false, true);
        DOWNLOAD_BAD_CONFIG = new DownloadObjects.DownloadStatus(false, false, false);
        downloadStatus = new HashMap<>();
        downloadStatusEvent = new Event<>();
        downloadDeleteEvent = new Event<>();
        downloadEvent = new Event<>();
        downloadProgressEvent = new Event<>();
    }

    public final StateFlow<Set<Integer>> getCurrentDownloads() {
        return currentDownloads;
    }

    public final int getImgDone() {
        return R.drawable.rddone;
    }

    public final int getImgDownloading() {
        return R.drawable.rdload;
    }

    public final int getImgPaused() {
        return R.drawable.rdpause;
    }

    public final int getImgStopped() {
        return R.drawable.rderror;
    }

    public final int getImgError() {
        return R.drawable.rderror;
    }

    public final int getPressToPauseIcon() {
        return R.drawable.ic_baseline_pause_24;
    }

    public final int getPressToResumeIcon() {
        return R.drawable.ic_baseline_play_arrow_24;
    }

    public final int getPressToStopIcon() {
        return R.drawable.baseline_stop_24;
    }

    public final HashMap<Integer, DownloadType> getDownloadStatus() {
        return downloadStatus;
    }

    public final Event<Pair<Integer, DownloadType>> getDownloadStatusEvent() {
        return downloadStatusEvent;
    }

    public final Event<Integer> getDownloadDeleteEvent() {
        return downloadDeleteEvent;
    }

    public final Event<Pair<Integer, DownloadActionType>> getDownloadEvent() {
        return downloadEvent;
    }

    public final Event<Triple<Integer, Long, Long>> getDownloadProgressEvent() {
        return downloadProgressEvent;
    }

    private final void createNotificationChannel(Context context) {
        hasCreatedNotChannel = true;
        AppContextUtils.INSTANCE.createNotificationChannel(context, DownloadManagerKt.DOWNLOAD_CHANNEL_ID, DownloadManagerKt.DOWNLOAD_CHANNEL_NAME, DownloadManagerKt.DOWNLOAD_CHANNEL_DESCRIPT);
    }

    public final void cancelAllDownloadNotifications(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(context);
        Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(...)");
        List<StatusBarNotification> activeNotifications = notificationManagerCompatFrom.getActiveNotifications();
        Intrinsics.checkNotNullExpressionValue(activeNotifications, "getActiveNotifications(...)");
        for (StatusBarNotification statusBarNotification : activeNotifications) {
            if (Intrinsics.areEqual(statusBarNotification.getTag(), DOWNLOAD_NOTIFICATION_TAG)) {
                notificationManagerCompatFrom.cancel(DOWNLOAD_NOTIFICATION_TAG, statusBarNotification.getId());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:100:0x03f4  */
    /* JADX WARN: Code duplicated, block: B:101:0x03f9 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:103:0x043e A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x0507 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:129:0x0515 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:132:0x0523 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:136:0x0536 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:138:0x0553  */
    /* JADX WARN: Code duplicated, block: B:140:0x0556 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:141:0x0558  */
    /* JADX WARN: Code duplicated, block: B:144:0x0561  */
    /* JADX WARN: Code duplicated, block: B:145:0x0564  */
    /* JADX WARN: Code duplicated, block: B:148:0x0595  */
    /* JADX WARN: Code duplicated, block: B:150:0x0598 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:151:0x059a A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:154:0x05a5 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:155:0x05aa A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:158:0x05b9  */
    /* JADX WARN: Code duplicated, block: B:160:0x05bc A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:161:0x05be A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:164:0x05cb A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:165:0x05d2 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:169:0x05e8 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:172:0x060b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:174:0x060e A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:184:0x055b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:185:0x059f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:186:0x05c5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x02ff A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0024  */
    /* JADX WARN: Code duplicated, block: B:82:0x0345 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:83:0x0365  */
    /* JADX WARN: Code duplicated, block: B:86:0x036d A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x0378  */
    /* JADX WARN: Code duplicated, block: B:90:0x0387 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:91:0x038b A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:93:0x0392  */
    /* JADX WARN: Code duplicated, block: B:94:0x0395 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:96:0x03b2 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:97:0x03d0 A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Code duplicated, block: B:98:0x03ed A[Catch: Exception -> 0x009b, TryCatch #0 {Exception -> 0x009b, blocks: (B:12:0x007a, B:70:0x0287, B:72:0x028b, B:78:0x02ba, B:80:0x033d, B:82:0x0345, B:84:0x0369, B:86:0x036d, B:88:0x037c, B:89:0x0384, B:90:0x0387, B:106:0x0473, B:107:0x0476, B:91:0x038b, B:95:0x03a6, B:104:0x045a, B:116:0x04ea, B:118:0x04ee, B:120:0x04f2, B:167:0x05e4, B:169:0x05e8, B:170:0x05eb, B:174:0x060e, B:122:0x04f6, B:124:0x04fc, B:126:0x0507, B:127:0x0511, B:129:0x0515, B:130:0x051f, B:132:0x0523, B:133:0x0528, B:134:0x0530, B:136:0x0536, B:146:0x0566, B:151:0x059a, B:156:0x05ae, B:161:0x05be, B:166:0x05d9, B:162:0x05c5, B:163:0x05ca, B:164:0x05cb, B:165:0x05d2, B:152:0x059f, B:153:0x05a4, B:154:0x05a5, B:155:0x05aa, B:142:0x055b, B:143:0x0560, B:94:0x0395, B:96:0x03b2, B:97:0x03d0, B:98:0x03ed, B:102:0x040a, B:101:0x03f9, B:103:0x043e, B:79:0x02ff, B:22:0x00b0, B:23:0x00eb, B:24:0x00ee, B:178:0x061c, B:179:0x061f, B:25:0x00f2, B:31:0x010f, B:33:0x011e, B:35:0x0129, B:36:0x0145, B:38:0x0149, B:41:0x014e, B:43:0x0152, B:46:0x016b, B:48:0x0171, B:50:0x0188, B:52:0x018e, B:54:0x0194, B:62:0x0210, B:64:0x0223, B:66:0x0229, B:108:0x0477, B:109:0x0481, B:110:0x0484, B:176:0x0618, B:177:0x061b, B:111:0x0488, B:112:0x04a5, B:115:0x04e0, B:114:0x04c4, B:55:0x01ce, B:57:0x01d4, B:58:0x01fa, B:61:0x0206, B:45:0x015b, B:26:0x00f7, B:27:0x00fc, B:28:0x0101, B:29:0x0106, B:30:0x010b), top: B:182:0x0038 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:94:0x0395, please report this as an issue */
    public final Object createDownloadNotification(Context context, String str, String str2, DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata, DownloadType downloadType, long j, long j2, Function2<? super Integer, ? super Notification, Unit> function2, Long l, Long l2, long j3, Continuation<? super Notification> continuation) {
        AnonymousClass1 anonymousClass1;
        int imgDone;
        NotificationCompat.Builder smallIcon;
        String str3;
        String string;
        int i;
        DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata2;
        DownloadType downloadType2;
        String str4;
        DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata3;
        Long l3;
        long j4;
        Context context2;
        String str5;
        String str6;
        Long l4;
        NotificationCompat.Builder builder;
        Context context3;
        Object obj;
        NotificationCompat.Builder builder2;
        String str7;
        String str8;
        ArrayList<DownloadActionType> arrayList;
        int i2;
        int i3;
        String str9;
        int i4;
        int pressToResumeIcon;
        int i5;
        String string2;
        Notification notificationBuild;
        NotificationManagerCompat notificationManagerCompatFrom;
        String str10;
        long jLongValue;
        String strValueOf;
        String str11;
        String str12;
        long j5;
        long j6;
        String str13;
        String estimatedTimeLeft$app;
        String string3;
        char c;
        String string4;
        String str14;
        Context context4 = context;
        long j7 = j;
        long j8 = j2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i6 = anonymousClass1.label;
        String str15 = "";
        try {
            if (i6 == 0) {
                ResultKt.throwOnFailure(obj2);
                if (j8 <= 0) {
                    return null;
                }
                NotificationCompat.Builder contentTitle = new NotificationCompat.Builder(context4, DownloadManagerKt.DOWNLOAD_CHANNEL_ID).setAutoCancel(true).setColorized(true).setOnlyAlertOnce(true).setShowWhen(false).setPriority(0).setColor(UIHelper.INSTANCE.colorFromAttribute(context4, R.attr.colorPrimary)).setContentTitle(downloadEpisodeMetadata.getMainName());
                switch (WhenMappings.$EnumSwitchMapping$0[downloadType.ordinal()]) {
                    case 1:
                        imgDone = getImgDone();
                        break;
                    case 2:
                        imgDone = getImgDownloading();
                        break;
                    case 3:
                        imgDone = getImgPaused();
                        break;
                    case 4:
                        imgDone = getImgError();
                        break;
                    case 5:
                        imgDone = getImgStopped();
                        break;
                    case 6:
                        imgDone = getImgDownloading();
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                smallIcon = contentTitle.setSmallIcon(imgDone);
                Intrinsics.checkNotNullExpressionValue(smallIcon, "setSmallIcon(...)");
                if (downloadEpisodeMetadata.getSourceApiName() != null) {
                    smallIcon.setSubText(downloadEpisodeMetadata.getSourceApiName());
                }
                if (str != null) {
                    Intent intent = new Intent(context4, (Class<?>) MainActivity.class);
                    intent.setData(Uri.parse(str));
                    intent.setFlags(268468224);
                    smallIcon.setContentIntent(PendingIntentCompat.getActivity(context4, 0, intent, 0, false));
                }
                if (downloadType == DownloadType.IsDownloading || downloadType == DownloadType.IsPaused) {
                    str3 = "format(...)";
                    smallIcon.setProgress((int) (j8 / 1000), (int) (j7 / 1000), false);
                } else {
                    if (downloadType == DownloadType.IsPending) {
                        smallIcon.setProgress(0, 0, true);
                    }
                    str3 = "format(...)";
                }
                String str16 = downloadEpisodeMetadata.getName() != null ? " - " + downloadEpisodeMetadata.getName() + '\n' : "";
                if (downloadEpisodeMetadata.getSeason() != null && downloadEpisodeMetadata.getEpisode() != null) {
                    string = context4.getString(R.string.season_short) + downloadEpisodeMetadata.getSeason().intValue() + AbstractJsonLexerKt.COLON + context4.getString(R.string.episode_short) + downloadEpisodeMetadata.getEpisode().intValue() + str16;
                } else if (downloadEpisodeMetadata.getEpisode() != null) {
                    string = context4.getString(R.string.episode) + ' ' + downloadEpisodeMetadata.getEpisode().intValue() + str16;
                } else {
                    StringBuilder sb = new StringBuilder();
                    String name = downloadEpisodeMetadata.getName();
                    if (name == null) {
                        name = "";
                    }
                    sb.append(name);
                    sb.append("");
                    string = sb.toString();
                }
                String string5 = context4.getString(R.string.download_format);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                String str17 = str16;
                if (Build.VERSION.SDK_INT >= 26) {
                    if (downloadEpisodeMetadata.getPoster() != null) {
                        CoroutineDispatcher io2 = Dispatchers.getIO();
                        VideoDownloadManager$createDownloadNotification$poster$1 videoDownloadManager$createDownloadNotification$poster$1 = new VideoDownloadManager$createDownloadNotification$poster$1(context4, downloadEpisodeMetadata, null);
                        anonymousClass1.L$0 = context4;
                        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(str);
                        str4 = str2;
                        anonymousClass1.L$2 = str4;
                        anonymousClass1.L$3 = downloadEpisodeMetadata;
                        anonymousClass1.L$4 = downloadType;
                        anonymousClass1.L$5 = function2;
                        anonymousClass1.L$6 = l;
                        anonymousClass1.L$7 = l2;
                        anonymousClass1.L$8 = smallIcon;
                        anonymousClass1.L$9 = SpillingKt.nullOutSpilledVariable(str17);
                        anonymousClass1.L$10 = string;
                        anonymousClass1.L$11 = string5;
                        anonymousClass1.J$0 = j7;
                        anonymousClass1.J$1 = j8;
                        anonymousClass1.J$2 = j3;
                        anonymousClass1.label = 1;
                        Object objWithContext = BuildersKt.withContext(io2, videoDownloadManager$createDownloadNotification$poster$1, anonymousClass1);
                        if (objWithContext == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        downloadType2 = downloadType;
                        l3 = l;
                        j4 = j3;
                        context3 = context4;
                        obj = objWithContext;
                        builder2 = smallIcon;
                        str7 = string;
                        str8 = string5;
                        function2 = function2;
                        l4 = l2;
                    } else {
                        str15 = "";
                        str4 = str2;
                        downloadEpisodeMetadata3 = downloadEpisodeMetadata;
                        downloadType2 = downloadType;
                        l3 = l;
                        j4 = j3;
                        context2 = context4;
                        str5 = string;
                        str6 = string5;
                        l4 = l2;
                        builder = smallIcon;
                        function2 = function2;
                    }
                    if (l3 == null && l4 != null) {
                        jLongValue = (l3.longValue() * 100) / l4.longValue();
                        String strValueOf2 = String.valueOf(l3.longValue());
                        strValueOf = String.valueOf(l4.longValue());
                        String str18 = String.format(" - %.1f MB", Arrays.copyOf(new Object[]{Boxing.boxFloat(j7 / 1000000.0f)}, 1));
                        str10 = str3;
                        Intrinsics.checkNotNullExpressionValue(str18, str10);
                        str12 = str18;
                        str11 = strValueOf2;
                    } else {
                        str10 = str3;
                        jLongValue = (100 * j7) / j8;
                        String str19 = String.format("%.1f MB", Arrays.copyOf(new Object[]{Boxing.boxFloat(j7 / 1000000.0f)}, 1));
                        Intrinsics.checkNotNullExpressionValue(str19, str10);
                        strValueOf = String.format("%.1f MB", Arrays.copyOf(new Object[]{Boxing.boxFloat(j8 / 1000000.0f)}, 1));
                        Intrinsics.checkNotNullExpressionValue(strValueOf, str10);
                        str11 = str19;
                        str12 = str15;
                    }
                    j5 = j8;
                    j6 = jLongValue;
                    if (downloadType2 == DownloadType.IsDownloading) {
                        str13 = String.format(" (%.1f MB/s)", Arrays.copyOf(new Object[]{Boxing.boxFloat(j4 / 1000000.0f)}, 1));
                        Intrinsics.checkNotNullExpressionValue(str13, str10);
                    } else {
                        str13 = str15;
                    }
                    if (downloadType2 == DownloadType.IsDownloading) {
                        estimatedTimeLeft$app = DownloadUtils.INSTANCE.getEstimatedTimeLeft$app(context2, j4, j7, j5);
                    } else {
                        estimatedTimeLeft$app = str15;
                    }
                    switch (WhenMappings.$EnumSwitchMapping$0[downloadType2.ordinal()]) {
                        case 1:
                            string3 = String.format(str6, Arrays.copyOf(new Object[]{context2.getString(R.string.download_done), str5}, 2));
                            Intrinsics.checkNotNullExpressionValue(string3, str10);
                            break;
                        case 2:
                        case 3:
                            StringBuilder sb2 = new StringBuilder();
                            if (str4 == null) {
                                string4 = str15;
                                c = '\n';
                            } else {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(str4);
                                c = '\n';
                                sb3.append('\n');
                                string4 = sb3.toString();
                            }
                            sb2.append(string4);
                            sb2.append(str5);
                            sb2.append(c);
                            sb2.append(j6);
                            sb2.append(" % (");
                            sb2.append(str11);
                            sb2.append('/');
                            sb2.append(strValueOf);
                            sb2.append(')');
                            sb2.append(str12);
                            sb2.append(str13);
                            sb2.append(' ');
                            sb2.append(estimatedTimeLeft$app);
                            string3 = sb2.toString();
                            break;
                        case 4:
                            string3 = String.format(str6, Arrays.copyOf(new Object[]{context2.getString(R.string.download_failed), str5}, 2));
                            Intrinsics.checkNotNullExpressionValue(string3, str10);
                            break;
                        case 5:
                            string3 = String.format(str6, Arrays.copyOf(new Object[]{context2.getString(R.string.download_canceled), str5}, 2));
                            Intrinsics.checkNotNullExpressionValue(string3, str10);
                            break;
                        case 6:
                            StringBuilder sb4 = new StringBuilder();
                            if (str4 == null) {
                                str14 = str15;
                            } else {
                                str14 = str4 + '\n';
                            }
                            sb4.append(str14);
                            sb4.append(str5);
                            string3 = sb4.toString();
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                    NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
                    bigTextStyle.bigText(string3);
                    builder.setStyle(bigTextStyle);
                    downloadEpisodeMetadata2 = downloadEpisodeMetadata3;
                    context4 = context2;
                    smallIcon = builder;
                    i = 0;
                } else {
                    String str20 = str3;
                    switch (WhenMappings.$EnumSwitchMapping$0[downloadType.ordinal()]) {
                        case 1:
                            i = 0;
                            string = String.format(string5, Arrays.copyOf(new Object[]{context4.getString(R.string.download_done), string}, 2));
                            Intrinsics.checkNotNullExpressionValue(string, str20);
                            smallIcon.setContentText(string);
                            downloadEpisodeMetadata2 = downloadEpisodeMetadata;
                            downloadType2 = downloadType;
                            break;
                        case 2:
                        case 3:
                        case 6:
                            i = 0;
                            smallIcon.setContentText(string);
                            downloadEpisodeMetadata2 = downloadEpisodeMetadata;
                            downloadType2 = downloadType;
                            break;
                        case 4:
                            string = String.format(string5, Arrays.copyOf(new Object[]{context4.getString(R.string.download_failed), string}, 2));
                            Intrinsics.checkNotNullExpressionValue(string, str20);
                            i = 0;
                            smallIcon.setContentText(string);
                            downloadEpisodeMetadata2 = downloadEpisodeMetadata;
                            downloadType2 = downloadType;
                            break;
                        case 5:
                            string = String.format(string5, Arrays.copyOf(new Object[]{context4.getString(R.string.download_canceled), string}, 2));
                            Intrinsics.checkNotNullExpressionValue(string, str20);
                            i = 0;
                            smallIcon.setContentText(string);
                            downloadEpisodeMetadata2 = downloadEpisodeMetadata;
                            downloadType2 = downloadType;
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                }
                Function2<? super Integer, ? super Notification, Unit> function3 = function2;
                if ((downloadType2 != DownloadType.IsDownloading || downloadType2 == DownloadType.IsPaused || downloadType2 == DownloadType.IsPending) && Build.VERSION.SDK_INT >= 26) {
                    arrayList = new ArrayList();
                    if (downloadType2 == DownloadType.IsDownloading) {
                        arrayList.add(DownloadActionType.Pause);
                        arrayList.add(DownloadActionType.Stop);
                    }
                    if (downloadType2 == DownloadType.IsPaused) {
                        arrayList.add(DownloadActionType.Resume);
                        arrayList.add(DownloadActionType.Stop);
                    }
                    if (downloadType2 == DownloadType.IsPending) {
                        arrayList.add(DownloadActionType.Stop);
                    }
                    i2 = i;
                    for (DownloadActionType downloadActionType : arrayList) {
                        int i7 = i2 + 1;
                        Intent intent2 = new Intent(context4, (Class<?>) VideoDownloadService.class);
                        i3 = WhenMappings.$EnumSwitchMapping$1[downloadActionType.ordinal()];
                        if (i3 == 1) {
                            str9 = "resume";
                        } else if (i3 == 2) {
                            str9 = "pause";
                        } else {
                            if (i3 != 3) {
                                throw new NoWhenBranchMatchedException();
                            }
                            str9 = "stop";
                        }
                        intent2.putExtra("type", str9);
                        intent2.putExtra("id", downloadEpisodeMetadata2.getId());
                        PendingIntent service = PendingIntent.getService(context4, (i2 * 1000000) + 4337 + downloadEpisodeMetadata2.getId(), intent2, 201326592);
                        Intrinsics.checkNotNullExpressionValue(service, "getService(...)");
                        i4 = WhenMappings.$EnumSwitchMapping$1[downloadActionType.ordinal()];
                        if (i4 == 1) {
                            pressToResumeIcon = getPressToResumeIcon();
                        } else if (i4 == 2) {
                            pressToResumeIcon = getPressToPauseIcon();
                        } else {
                            if (i4 != 3) {
                                throw new NoWhenBranchMatchedException();
                            }
                            pressToResumeIcon = getPressToStopIcon();
                        }
                        i5 = WhenMappings.$EnumSwitchMapping$1[downloadActionType.ordinal()];
                        if (i5 == 1) {
                            string2 = context4.getString(R.string.resume);
                        } else if (i5 == 2) {
                            string2 = context4.getString(R.string.pause);
                        } else {
                            if (i5 != 3) {
                                throw new NoWhenBranchMatchedException();
                            }
                            string2 = context4.getString(R.string.cancel);
                        }
                        smallIcon.addAction(new NotificationCompat.Action(pressToResumeIcon, string2, service));
                        i2 = i7;
                    }
                }
                if (!hasCreatedNotChannel) {
                    createNotificationChannel(context4);
                }
                notificationBuild = smallIcon.build();
                Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
                function3.invoke(Boxing.boxInt(downloadEpisodeMetadata2.getId()), notificationBuild);
                notificationManagerCompatFrom = NotificationManagerCompat.from(context4);
                if (ActivityCompat.checkSelfPermission(context4, "android.permission.POST_NOTIFICATIONS") != 0) {
                    return null;
                }
                notificationManagerCompatFrom.notify(DOWNLOAD_NOTIFICATION_TAG, downloadEpisodeMetadata2.getId(), notificationBuild);
                return notificationBuild;
            }
            if (i6 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            long j9 = anonymousClass1.J$2;
            long j10 = anonymousClass1.J$1;
            long j11 = anonymousClass1.J$0;
            String str21 = (String) anonymousClass1.L$11;
            String str22 = (String) anonymousClass1.L$10;
            NotificationCompat.Builder builder3 = (NotificationCompat.Builder) anonymousClass1.L$8;
            l4 = (Long) anonymousClass1.L$7;
            l3 = (Long) anonymousClass1.L$6;
            Function2<? super Integer, ? super Notification, Unit> function4 = (Function2) anonymousClass1.L$5;
            DownloadType downloadType3 = (DownloadType) anonymousClass1.L$4;
            downloadEpisodeMetadata = (DownloadObjects.DownloadEpisodeMetadata) anonymousClass1.L$3;
            String str23 = (String) anonymousClass1.L$2;
            Context context5 = (Context) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj2);
            str7 = str22;
            str3 = "format(...)";
            j4 = j9;
            str4 = str23;
            context3 = context5;
            obj = obj2;
            builder2 = builder3;
            function2 = function4;
            str8 = str21;
            downloadType2 = downloadType3;
            j7 = j11;
            j8 = j10;
            DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata4 = downloadEpisodeMetadata;
            Bitmap bitmap = (Bitmap) obj;
            if (bitmap != null) {
                builder2.setLargeIcon(bitmap);
            }
            String str24 = str8;
            builder = builder2;
            str6 = str24;
            context2 = context3;
            downloadEpisodeMetadata3 = downloadEpisodeMetadata4;
            str5 = str7;
            if (l3 == null) {
                str10 = str3;
                jLongValue = (100 * j7) / j8;
                String str110 = String.format("%.1f MB", Arrays.copyOf(new Object[]{Boxing.boxFloat(j7 / 1000000.0f)}, 1));
                Intrinsics.checkNotNullExpressionValue(str110, str10);
                strValueOf = String.format("%.1f MB", Arrays.copyOf(new Object[]{Boxing.boxFloat(j8 / 1000000.0f)}, 1));
                Intrinsics.checkNotNullExpressionValue(strValueOf, str10);
                str11 = str110;
                str12 = str15;
            } else {
                str10 = str3;
                jLongValue = (100 * j7) / j8;
                String str111 = String.format("%.1f MB", Arrays.copyOf(new Object[]{Boxing.boxFloat(j7 / 1000000.0f)}, 1));
                Intrinsics.checkNotNullExpressionValue(str111, str10);
                strValueOf = String.format("%.1f MB", Arrays.copyOf(new Object[]{Boxing.boxFloat(j8 / 1000000.0f)}, 1));
                Intrinsics.checkNotNullExpressionValue(strValueOf, str10);
                str11 = str111;
                str12 = str15;
            }
            j5 = j8;
            j6 = jLongValue;
            if (downloadType2 == DownloadType.IsDownloading) {
                str13 = String.format(" (%.1f MB/s)", Arrays.copyOf(new Object[]{Boxing.boxFloat(j4 / 1000000.0f)}, 1));
                Intrinsics.checkNotNullExpressionValue(str13, str10);
            } else {
                str13 = str15;
            }
            if (downloadType2 == DownloadType.IsDownloading) {
                estimatedTimeLeft$app = DownloadUtils.INSTANCE.getEstimatedTimeLeft$app(context2, j4, j7, j5);
            } else {
                estimatedTimeLeft$app = str15;
            }
            switch (WhenMappings.$EnumSwitchMapping$0[downloadType2.ordinal()]) {
                case 1:
                    string3 = String.format(str6, Arrays.copyOf(new Object[]{context2.getString(R.string.download_done), str5}, 2));
                    Intrinsics.checkNotNullExpressionValue(string3, str10);
                    break;
                case 2:
                case 3:
                    StringBuilder sb5 = new StringBuilder();
                    if (str4 == null) {
                        string4 = str15;
                        c = '\n';
                    } else {
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append(str4);
                        c = '\n';
                        sb6.append('\n');
                        string4 = sb6.toString();
                    }
                    sb5.append(string4);
                    sb5.append(str5);
                    sb5.append(c);
                    sb5.append(j6);
                    sb5.append(" % (");
                    sb5.append(str11);
                    sb5.append('/');
                    sb5.append(strValueOf);
                    sb5.append(')');
                    sb5.append(str12);
                    sb5.append(str13);
                    sb5.append(' ');
                    sb5.append(estimatedTimeLeft$app);
                    string3 = sb5.toString();
                    break;
                case 4:
                    string3 = String.format(str6, Arrays.copyOf(new Object[]{context2.getString(R.string.download_failed), str5}, 2));
                    Intrinsics.checkNotNullExpressionValue(string3, str10);
                    break;
                case 5:
                    string3 = String.format(str6, Arrays.copyOf(new Object[]{context2.getString(R.string.download_canceled), str5}, 2));
                    Intrinsics.checkNotNullExpressionValue(string3, str10);
                    break;
                case 6:
                    StringBuilder sb7 = new StringBuilder();
                    if (str4 == null) {
                        str14 = str15;
                    } else {
                        str14 = str4 + '\n';
                    }
                    sb7.append(str14);
                    sb7.append(str5);
                    string3 = sb7.toString();
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            NotificationCompat.BigTextStyle bigTextStyle2 = new NotificationCompat.BigTextStyle();
            bigTextStyle2.bigText(string3);
            builder.setStyle(bigTextStyle2);
            downloadEpisodeMetadata2 = downloadEpisodeMetadata3;
            context4 = context2;
            smallIcon = builder;
            i = 0;
            Function2<? super Integer, ? super Notification, Unit> function5 = function2;
            if (downloadType2 != DownloadType.IsDownloading) {
                arrayList = new ArrayList();
                if (downloadType2 == DownloadType.IsDownloading) {
                    arrayList.add(DownloadActionType.Pause);
                    arrayList.add(DownloadActionType.Stop);
                }
                if (downloadType2 == DownloadType.IsPaused) {
                    arrayList.add(DownloadActionType.Resume);
                    arrayList.add(DownloadActionType.Stop);
                }
                if (downloadType2 == DownloadType.IsPending) {
                    arrayList.add(DownloadActionType.Stop);
                }
                i2 = i;
                while (r3.hasNext()) {
                    int i8 = i2 + 1;
                    Intent intent3 = new Intent(context4, (Class<?>) VideoDownloadService.class);
                    i3 = WhenMappings.$EnumSwitchMapping$1[downloadActionType.ordinal()];
                    if (i3 == 1) {
                        str9 = "resume";
                    } else if (i3 == 2) {
                        str9 = "pause";
                    } else {
                        if (i3 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        str9 = "stop";
                    }
                    intent3.putExtra("type", str9);
                    intent3.putExtra("id", downloadEpisodeMetadata2.getId());
                    PendingIntent service2 = PendingIntent.getService(context4, (i2 * 1000000) + 4337 + downloadEpisodeMetadata2.getId(), intent3, 201326592);
                    Intrinsics.checkNotNullExpressionValue(service2, "getService(...)");
                    i4 = WhenMappings.$EnumSwitchMapping$1[downloadActionType.ordinal()];
                    if (i4 == 1) {
                        pressToResumeIcon = getPressToResumeIcon();
                    } else if (i4 == 2) {
                        pressToResumeIcon = getPressToPauseIcon();
                    } else {
                        if (i4 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        pressToResumeIcon = getPressToStopIcon();
                    }
                    i5 = WhenMappings.$EnumSwitchMapping$1[downloadActionType.ordinal()];
                    if (i5 == 1) {
                        string2 = context4.getString(R.string.resume);
                    } else if (i5 == 2) {
                        string2 = context4.getString(R.string.pause);
                    } else {
                        if (i5 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        string2 = context4.getString(R.string.cancel);
                    }
                    smallIcon.addAction(new NotificationCompat.Action(pressToResumeIcon, string2, service2));
                    i2 = i8;
                }
            } else {
                arrayList = new ArrayList();
                if (downloadType2 == DownloadType.IsDownloading) {
                    arrayList.add(DownloadActionType.Pause);
                    arrayList.add(DownloadActionType.Stop);
                }
                if (downloadType2 == DownloadType.IsPaused) {
                    arrayList.add(DownloadActionType.Resume);
                    arrayList.add(DownloadActionType.Stop);
                }
                if (downloadType2 == DownloadType.IsPending) {
                    arrayList.add(DownloadActionType.Stop);
                }
                i2 = i;
                while (r3.hasNext()) {
                    int i9 = i2 + 1;
                    Intent intent4 = new Intent(context4, (Class<?>) VideoDownloadService.class);
                    i3 = WhenMappings.$EnumSwitchMapping$1[downloadActionType.ordinal()];
                    if (i3 == 1) {
                        str9 = "resume";
                    } else if (i3 == 2) {
                        str9 = "pause";
                    } else {
                        if (i3 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        str9 = "stop";
                    }
                    intent4.putExtra("type", str9);
                    intent4.putExtra("id", downloadEpisodeMetadata2.getId());
                    PendingIntent service3 = PendingIntent.getService(context4, (i2 * 1000000) + 4337 + downloadEpisodeMetadata2.getId(), intent4, 201326592);
                    Intrinsics.checkNotNullExpressionValue(service3, "getService(...)");
                    i4 = WhenMappings.$EnumSwitchMapping$1[downloadActionType.ordinal()];
                    if (i4 == 1) {
                        pressToResumeIcon = getPressToResumeIcon();
                    } else if (i4 == 2) {
                        pressToResumeIcon = getPressToPauseIcon();
                    } else {
                        if (i4 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        pressToResumeIcon = getPressToStopIcon();
                    }
                    i5 = WhenMappings.$EnumSwitchMapping$1[downloadActionType.ordinal()];
                    if (i5 == 1) {
                        string2 = context4.getString(R.string.resume);
                    } else if (i5 == 2) {
                        string2 = context4.getString(R.string.pause);
                    } else {
                        if (i5 != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        string2 = context4.getString(R.string.cancel);
                    }
                    smallIcon.addAction(new NotificationCompat.Action(pressToResumeIcon, string2, service3));
                    i2 = i9;
                }
            }
            if (!hasCreatedNotChannel) {
                createNotificationChannel(context4);
            }
            notificationBuild = smallIcon.build();
            Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
            function5.invoke(Boxing.boxInt(downloadEpisodeMetadata2.getId()), notificationBuild);
            notificationManagerCompatFrom = NotificationManagerCompat.from(context4);
            if (ActivityCompat.checkSelfPermission(context4, "android.permission.POST_NOTIFICATIONS") != 0) {
                return null;
            }
            notificationManagerCompatFrom.notify(DOWNLOAD_NOTIFICATION_TAG, downloadEpisodeMetadata2.getId(), notificationBuild);
            return notificationBuild;
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return null;
        }
    }

    public final DownloadObjects.StreamData setupStream(Context context, String name, String folder, String extension, boolean tryResume) throws IOException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(extension, "extension");
        SafeFile first = DownloadFileManagement.INSTANCE.getBasePath$app(context).getFirst();
        if (first != null || (first = DownloadFileManagement.INSTANCE.getDefaultDir(context)) != null) {
            return setupStream(first, name, folder, extension, tryResume);
        }
        throw new IOException("Bad config");
    }

    public final DownloadObjects.StreamData setupStream(SafeFile baseFile, String name, String folder, String extension, boolean tryResume) throws IOException {
        Pair pair;
        Intrinsics.checkNotNullParameter(baseFile, "baseFile");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(extension, "extension");
        String displayName = getDisplayName(name, extension);
        SafeFile safeFileGotoDirectory = baseFile.gotoDirectory(folder, true);
        if (safeFileGotoDirectory == null) {
            throw new IOException("Cant create directory");
        }
        SafeFile safeFileFindFile$default = SafeFile.DefaultImpls.findFile$default(safeFileGotoDirectory, displayName, false, 2, null);
        if (safeFileFindFile$default == null || !Intrinsics.areEqual((Object) safeFileFindFile$default.exists(), (Object) true)) {
            pair = TuplesKt.to(safeFileGotoDirectory.createFileOrThrow(displayName), 0L);
        } else if (tryResume) {
            pair = TuplesKt.to(safeFileFindFile$default, Long.valueOf(safeFileFindFile$default.lengthOrThrow()));
        } else {
            safeFileFindFile$default.deleteOrThrow();
            pair = TuplesKt.to(safeFileGotoDirectory.createFileOrThrow(displayName), 0L);
        }
        return new DownloadObjects.StreamData(((Number) pair.component2()).longValue(), (SafeFile) pair.component1());
    }

    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 h2\u00020\u0001:\u0001hB\u009f\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\u000e\u00101\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u0006J\u0006\u00103\u001a\u000204J\u0014\u00109\u001a\u00020\u000e2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000e08J\u0006\u0010;\u001a\u00020\u000eJ\b\u0010?\u001a\u00020\u000eH\u0002J\u000e\u0010@\u001a\u00020\u000e2\u0006\u0010A\u001a\u00020\u0017J\b\u0010B\u001a\u00020\u000eH\u0016J\u0006\u0010I\u001a\u00020\u000eJ\r\u0010J\u001a\u00020\u000eH\u0003¢\u0006\u0002\bKJ\b\u0010L\u001a\u00020\u000eH\u0002J\u000e\u0010M\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u0006J\u000e\u0010N\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u0006J\u000e\u0010O\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u0006J\u000e\u0010P\u001a\u00020\u000e2\u0006\u0010Q\u001a\u00020\u0003J\u0010\u0010R\u001a\u0004\u0018\u00010\u0003HÂ\u0003¢\u0006\u0002\u0010,J\t\u0010S\u001a\u00020\u0003HÂ\u0003J\t\u0010T\u001a\u00020\u0006HÆ\u0003J\t\u0010U\u001a\u00020\u0006HÆ\u0003J\u0010\u0010V\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\"J\t\u0010W\u001a\u00020\u0006HÂ\u0003J\t\u0010X\u001a\u00020\u0006HÂ\u0003J\u0015\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fHÂ\u0003J\t\u0010Z\u001a\u00020\u0010HÂ\u0003J\t\u0010[\u001a\u00020\u0012HÆ\u0003J\t\u0010\\\u001a\u00020\u0003HÆ\u0003J\u0010\u0010]\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010,J\t\u0010^\u001a\u00020\u0003HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0017HÂ\u0003J®\u0001\u0010`\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÆ\u0001¢\u0006\u0002\u0010aJ\u0014\u0010b\u001a\u00020\u00122\b\u0010c\u001a\u0004\u0018\u00010dHÖ\u0083\u0004J\n\u0010e\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010f\u001a\u00020gHÖ\u0081\u0004R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u001e\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010&R\u001a\u0010\u0013\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010\u0015\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010(\"\u0004\b0\u0010*R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00105\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b6\u0010\u001cR\u0016\u00107\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010<\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020>0=\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010D\u001a\u00020\u00102\u0006\u0010C\u001a\u00020\u00108F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bE\u0010F\"\u0004\bG\u0010H¨\u0006i"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadMetaData;", "Ljava/io/Closeable;", "id", "", "linkHash", "bytesDownloaded", "", "bytesWritten", "totalBytes", "lastUpdatedMs", "lastDownloadedBytes", "createNotificationCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$CreateNotificationMetadata;", "", "internalType", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "isHLS", "", "hlsProgress", "hlsTotal", "hlsWrittenProgress", "downloadFileInfoTemplate", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadedFileInfo;", "<init>", "(Ljava/lang/Integer;IJJLjava/lang/Long;JJLkotlin/jvm/functions/Function1;Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;ZILjava/lang/Integer;ILcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadedFileInfo;)V", "Ljava/lang/Integer;", "getBytesDownloaded", "()J", "setBytesDownloaded", "(J)V", "getBytesWritten", "setBytesWritten", "getTotalBytes", "()Ljava/lang/Long;", "setTotalBytes", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "()Z", "getHlsProgress", "()I", "setHlsProgress", "(I)V", "getHlsTotal", "()Ljava/lang/Integer;", "setHlsTotal", "(Ljava/lang/Integer;)V", "getHlsWrittenProgress", "setHlsWrittenProgress", "setResumeLength", "length", "failedStatus", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadStatus;", "approxTotalBytes", "getApproxTotalBytes", "stopListener", "Lkotlin/Function0;", "setOnStop", "callback", "removeStopListener", "downloadEventListener", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadActionType;", "updateFileInfo", "setDownloadFileInfoTemplate", "template", "close", ES6Iterator.VALUE_PROPERTY, "type", "getType", "()Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "setType", "(Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;)V", "onDelete", "notify", "DownloadMetaDataNotify", "checkNotification", "addBytes", "addBytesWritten", "addSegment", "setWrittenSegment", "segmentIndex", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/Integer;IJJLjava/lang/Long;JJLkotlin/jvm/functions/Function1;Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;ZILjava/lang/Integer;ILcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadedFileInfo;)Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadMetaData;", "equals", "other", "", "hashCode", "toString", "", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DownloadMetaData implements Closeable {
        public static final long UPDATE_RATE_MS = 1000;
        private long bytesDownloaded;
        private long bytesWritten;
        private final Function1<DownloadObjects.CreateNotificationMetadata, Unit> createNotificationCallback;
        private final Function1<Pair<Integer, ? extends DownloadActionType>, Unit> downloadEventListener;
        private DownloadObjects.DownloadedFileInfo downloadFileInfoTemplate;
        private int hlsProgress;
        private Integer hlsTotal;
        private int hlsWrittenProgress;
        private final Integer id;
        private DownloadType internalType;
        private final boolean isHLS;
        private long lastDownloadedBytes;
        private long lastUpdatedMs;
        private final int linkHash;
        private Function0<Unit> stopListener;
        private Long totalBytes;

        /* JADX INFO: compiled from: DownloadManager.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[DownloadActionType.values().length];
                try {
                    iArr[DownloadActionType.Pause.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[DownloadActionType.Stop.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[DownloadActionType.Resume.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        private final DownloadObjects.DownloadedFileInfo getDownloadFileInfoTemplate() {
            return this.downloadFileInfoTemplate;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final int getLinkHash() {
            return this.linkHash;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        private final long getLastUpdatedMs() {
            return this.lastUpdatedMs;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        private final long getLastDownloadedBytes() {
            return this.lastDownloadedBytes;
        }

        private final Function1<DownloadObjects.CreateNotificationMetadata, Unit> component8() {
            return this.createNotificationCallback;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        private final DownloadType getInternalType() {
            return this.internalType;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DownloadMetaData copy$default(DownloadMetaData downloadMetaData, Integer num, int i, long j, long j2, Long l, long j3, long j4, Function1 function1, DownloadType downloadType, boolean z, int i2, Integer num2, int i3, DownloadObjects.DownloadedFileInfo downloadedFileInfo, int i4, Object obj) {
            Integer num3 = (i4 & 1) != 0 ? downloadMetaData.id : num;
            return downloadMetaData.copy(num3, (i4 & 2) != 0 ? downloadMetaData.linkHash : i, (i4 & 4) != 0 ? downloadMetaData.bytesDownloaded : j, (i4 & 8) != 0 ? downloadMetaData.bytesWritten : j2, (i4 & 16) != 0 ? downloadMetaData.totalBytes : l, (i4 & 32) != 0 ? downloadMetaData.lastUpdatedMs : j3, (i4 & 64) != 0 ? downloadMetaData.lastDownloadedBytes : j4, (i4 & 128) != 0 ? downloadMetaData.createNotificationCallback : function1, (i4 & 256) != 0 ? downloadMetaData.internalType : downloadType, (i4 & 512) != 0 ? downloadMetaData.isHLS : z, (i4 & 1024) != 0 ? downloadMetaData.hlsProgress : i2, (i4 & 2048) != 0 ? downloadMetaData.hlsTotal : num2, (i4 & 4096) != 0 ? downloadMetaData.hlsWrittenProgress : i3, (i4 & 8192) != 0 ? downloadMetaData.downloadFileInfoTemplate : downloadedFileInfo);
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final boolean getIsHLS() {
            return this.isHLS;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final int getHlsProgress() {
            return this.hlsProgress;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Integer getHlsTotal() {
            return this.hlsTotal;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final int getHlsWrittenProgress() {
            return this.hlsWrittenProgress;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getBytesDownloaded() {
            return this.bytesDownloaded;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getBytesWritten() {
            return this.bytesWritten;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Long getTotalBytes() {
            return this.totalBytes;
        }

        public final DownloadMetaData copy(Integer id, int linkHash, long bytesDownloaded, long bytesWritten, Long totalBytes, long lastUpdatedMs, long lastDownloadedBytes, Function1<? super DownloadObjects.CreateNotificationMetadata, Unit> createNotificationCallback, DownloadType internalType, boolean isHLS, int hlsProgress, Integer hlsTotal, int hlsWrittenProgress, DownloadObjects.DownloadedFileInfo downloadFileInfoTemplate) {
            Intrinsics.checkNotNullParameter(createNotificationCallback, "createNotificationCallback");
            Intrinsics.checkNotNullParameter(internalType, "internalType");
            return new DownloadMetaData(id, linkHash, bytesDownloaded, bytesWritten, totalBytes, lastUpdatedMs, lastDownloadedBytes, createNotificationCallback, internalType, isHLS, hlsProgress, hlsTotal, hlsWrittenProgress, downloadFileInfoTemplate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadMetaData)) {
                return false;
            }
            DownloadMetaData downloadMetaData = (DownloadMetaData) other;
            return Intrinsics.areEqual(this.id, downloadMetaData.id) && this.linkHash == downloadMetaData.linkHash && this.bytesDownloaded == downloadMetaData.bytesDownloaded && this.bytesWritten == downloadMetaData.bytesWritten && Intrinsics.areEqual(this.totalBytes, downloadMetaData.totalBytes) && this.lastUpdatedMs == downloadMetaData.lastUpdatedMs && this.lastDownloadedBytes == downloadMetaData.lastDownloadedBytes && Intrinsics.areEqual(this.createNotificationCallback, downloadMetaData.createNotificationCallback) && this.internalType == downloadMetaData.internalType && this.isHLS == downloadMetaData.isHLS && this.hlsProgress == downloadMetaData.hlsProgress && Intrinsics.areEqual(this.hlsTotal, downloadMetaData.hlsTotal) && this.hlsWrittenProgress == downloadMetaData.hlsWrittenProgress && Intrinsics.areEqual(this.downloadFileInfoTemplate, downloadMetaData.downloadFileInfoTemplate);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (((((((num == null ? 0 : num.hashCode()) * 31) + this.linkHash) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.bytesDownloaded)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.bytesWritten)) * 31;
            Long l = this.totalBytes;
            int iHashCode2 = (((((((((((((iHashCode + (l == null ? 0 : l.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.lastUpdatedMs)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.lastDownloadedBytes)) * 31) + this.createNotificationCallback.hashCode()) * 31) + this.internalType.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isHLS)) * 31) + this.hlsProgress) * 31;
            Integer num2 = this.hlsTotal;
            int iHashCode3 = (((iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31) + this.hlsWrittenProgress) * 31;
            DownloadObjects.DownloadedFileInfo downloadedFileInfo = this.downloadFileInfoTemplate;
            return iHashCode3 + (downloadedFileInfo != null ? downloadedFileInfo.hashCode() : 0);
        }

        public String toString() {
            return "DownloadMetaData(id=" + this.id + ", linkHash=" + this.linkHash + ", bytesDownloaded=" + this.bytesDownloaded + ", bytesWritten=" + this.bytesWritten + ", totalBytes=" + this.totalBytes + ", lastUpdatedMs=" + this.lastUpdatedMs + ", lastDownloadedBytes=" + this.lastDownloadedBytes + ", createNotificationCallback=" + this.createNotificationCallback + ", internalType=" + this.internalType + ", isHLS=" + this.isHLS + ", hlsProgress=" + this.hlsProgress + ", hlsTotal=" + this.hlsTotal + ", hlsWrittenProgress=" + this.hlsWrittenProgress + ", downloadFileInfoTemplate=" + this.downloadFileInfoTemplate + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DownloadMetaData(Integer num, int i, long j, long j2, Long l, long j3, long j4, Function1<? super DownloadObjects.CreateNotificationMetadata, Unit> createNotificationCallback, DownloadType internalType, boolean z, int i2, Integer num2, int i3, DownloadObjects.DownloadedFileInfo downloadedFileInfo) {
            Intrinsics.checkNotNullParameter(createNotificationCallback, "createNotificationCallback");
            Intrinsics.checkNotNullParameter(internalType, "internalType");
            this.id = num;
            this.linkHash = i;
            this.bytesDownloaded = j;
            this.bytesWritten = j2;
            this.totalBytes = l;
            this.lastUpdatedMs = j3;
            this.lastDownloadedBytes = j4;
            this.createNotificationCallback = createNotificationCallback;
            this.internalType = internalType;
            this.isHLS = z;
            this.hlsProgress = i2;
            this.hlsTotal = num2;
            this.hlsWrittenProgress = i3;
            this.downloadFileInfoTemplate = downloadedFileInfo;
            Function1<Pair<Integer, ? extends DownloadActionType>, Unit> function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$DownloadMetaData$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VideoDownloadManager.DownloadMetaData.downloadEventListener$lambda$0(this.f$0, (Pair) obj);
                }
            };
            this.downloadEventListener = function1;
            if (num != null) {
                VideoDownloadManager.INSTANCE.getDownloadEvent().plusAssign(function1);
            }
        }

        public final long getBytesDownloaded() {
            return this.bytesDownloaded;
        }

        public final void setBytesDownloaded(long j) {
            this.bytesDownloaded = j;
        }

        public final long getBytesWritten() {
            return this.bytesWritten;
        }

        public final void setBytesWritten(long j) {
            this.bytesWritten = j;
        }

        public final Long getTotalBytes() {
            return this.totalBytes;
        }

        public final void setTotalBytes(Long l) {
            this.totalBytes = l;
        }

        public /* synthetic */ DownloadMetaData(Integer num, int i, long j, long j2, Long l, long j3, long j4, Function1 function1, DownloadType downloadType, boolean z, int i2, Integer num2, int i3, DownloadObjects.DownloadedFileInfo downloadedFileInfo, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(num, i, (i4 & 4) != 0 ? 0L : j, (i4 & 8) != 0 ? 0L : j2, (i4 & 16) != 0 ? null : l, (i4 & 32) != 0 ? 0L : j3, (i4 & 64) != 0 ? 0L : j4, function1, (i4 & 256) != 0 ? DownloadType.IsPending : downloadType, z, (i4 & 1024) != 0 ? 0 : i2, (i4 & 2048) != 0 ? null : num2, (i4 & 4096) != 0 ? 0 : i3, (i4 & 8192) != 0 ? null : downloadedFileInfo);
        }

        public final boolean isHLS() {
            return this.isHLS;
        }

        public final int getHlsProgress() {
            return this.hlsProgress;
        }

        public final void setHlsProgress(int i) {
            this.hlsProgress = i;
        }

        public final Integer getHlsTotal() {
            return this.hlsTotal;
        }

        public final void setHlsTotal(Integer num) {
            this.hlsTotal = num;
        }

        public final int getHlsWrittenProgress() {
            return this.hlsWrittenProgress;
        }

        public final void setHlsWrittenProgress(int i) {
            this.hlsWrittenProgress = i;
        }

        public final void setResumeLength(long length) {
            this.bytesDownloaded = length;
            this.bytesWritten = length;
            this.lastDownloadedBytes = length;
        }

        public final DownloadObjects.DownloadStatus failedStatus() {
            return this.bytesWritten > VideoDownloadManager.DOWNLOAD_PARTIAL_MIN_SIZE ? VideoDownloadManager.DOWNLOAD_PARTIAL_SUCCESS : VideoDownloadManager.DOWNLOAD_FAILED;
        }

        public final long getApproxTotalBytes() {
            Long lValueOf;
            Long l = this.totalBytes;
            if (l != null) {
                return l.longValue();
            }
            Integer num = this.hlsTotal;
            if (num != null) {
                lValueOf = Long.valueOf((long) (this.bytesDownloaded * (num.intValue() / this.hlsProgress)));
            } else {
                lValueOf = null;
            }
            if (lValueOf != null) {
                return lValueOf.longValue();
            }
            return this.bytesDownloaded;
        }

        public final void setOnStop(Function0<Unit> callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.stopListener = callback;
        }

        public final void removeStopListener() {
            this.stopListener = null;
        }

        static final Unit downloadEventListener$lambda$0(DownloadMetaData downloadMetaData, Pair event) {
            Intrinsics.checkNotNullParameter(event, "event");
            int iIntValue = ((Number) event.getFirst()).intValue();
            Integer num = downloadMetaData.id;
            if (num != null && iIntValue == num.intValue()) {
                int i = WhenMappings.$EnumSwitchMapping$0[((DownloadActionType) event.getSecond()).ordinal()];
                if (i == 1) {
                    downloadMetaData.setType(DownloadType.IsPaused);
                } else if (i == 2) {
                    downloadMetaData.setType(DownloadType.IsStopped);
                    Function0<Unit> function0 = downloadMetaData.stopListener;
                    if (function0 != null) {
                        function0.invoke();
                    }
                    downloadMetaData.stopListener = null;
                } else {
                    if (i != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    downloadMetaData.setType(DownloadType.IsDownloading);
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:29:0x0087  */
        /* JADX WARN: Code duplicated, block: B:36:0x009d A[Catch: Exception -> 0x00af, TRY_LEAVE, TryCatch #0 {Exception -> 0x00af, blocks: (B:12:0x002f, B:15:0x003b, B:20:0x0055, B:27:0x0081, B:30:0x0088, B:36:0x009d, B:35:0x0098, B:26:0x0077, B:19:0x004b, B:23:0x005c, B:16:0x003d, B:32:0x008c), top: B:59:0x002f, inners: #1, #2, #3, #5 }] */
        /* JADX WARN: Code duplicated, block: B:50:0x00d7  */
        /* JADX WARN: Code duplicated, block: B:64:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
        private final void updateFileInfo() {
            DownloadObjects.DownloadedFileInfo downloadedFileInfo;
            long approxTotalBytes;
            Object objDecodeFromString;
            Object objM8936constructorimpl;
            KSerializer kSerializer;
            if (this.id == null || (downloadedFileInfo = this.downloadFileInfoTemplate) == null) {
                return;
            }
            if (getApproxTotalBytes() <= this.bytesDownloaded) {
                CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
                String strValueOf = String.valueOf(this.id.intValue());
                Context context = companion.getContext();
                if (context != null) {
                    DataStore dataStore = DataStore.INSTANCE;
                    try {
                        String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(VideoDownloadManager.KEY_DOWNLOAD_INFO, strValueOf), null);
                        if (string == null) {
                            objDecodeFromString = null;
                        } else {
                            AppUtils appUtils = AppUtils.INSTANCE;
                            try {
                                Result.Companion companion2 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadedFileInfo"));
                            } catch (Throwable th) {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                            }
                            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                    objM8936constructorimpl = null;
                                }
                                kSerializer = (KSerializer) objM8936constructorimpl;
                                if (kSerializer != null) {
                                    try {
                                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                    } catch (SerializationException e) {
                                        ArchComponentExtKt.logError(e);
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$DownloadMetaData$updateFileInfo$lambda$0$$inlined$getKey$1
                                        });
                                    } catch (Throwable unused) {
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$DownloadMetaData$updateFileInfo$lambda$0$$inlined$getKey$1
                                        });
                                    }
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$DownloadMetaData$updateFileInfo$lambda$0$$inlined$getKey$1
                                    });
                                }
                            } else {
                                try {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadedFileInfo.class), null, 2, null));
                                } catch (Throwable th2) {
                                    Result.Companion companion5 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                                }
                                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                    objM8936constructorimpl = null;
                                }
                                kSerializer = (KSerializer) objM8936constructorimpl;
                                if (kSerializer != null) {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$DownloadMetaData$updateFileInfo$lambda$0$$inlined$getKey$1
                                    });
                                }
                            }
                        }
                    } catch (Exception unused2) {
                    }
                } else {
                    objDecodeFromString = null;
                }
                DownloadObjects.DownloadedFileInfo downloadedFileInfo2 = (DownloadObjects.DownloadedFileInfo) objDecodeFromString;
                if (downloadedFileInfo2 != null) {
                    Integer linkHash = downloadedFileInfo2.getLinkHash();
                    int i = this.linkHash;
                    if (linkHash != null && linkHash.intValue() == i) {
                        Long l = this.totalBytes;
                        approxTotalBytes = l != null ? l.longValue() : Math.max(downloadedFileInfo2.getTotalBytes(), this.bytesDownloaded);
                    } else {
                        approxTotalBytes = getApproxTotalBytes();
                    }
                } else {
                    approxTotalBytes = getApproxTotalBytes();
                }
            } else {
                approxTotalBytes = getApproxTotalBytes();
            }
            CloudStreamApp.INSTANCE.setKey(VideoDownloadManager.KEY_DOWNLOAD_INFO, String.valueOf(this.id.intValue()), DownloadObjects.DownloadedFileInfo.copy$default(downloadedFileInfo, approxTotalBytes, null, null, this.isHLS ? String.valueOf(this.hlsWrittenProgress) : null, null, Integer.valueOf(this.linkHash), 22, null));
        }

        public final void setDownloadFileInfoTemplate(DownloadObjects.DownloadedFileInfo template) {
            Intrinsics.checkNotNullParameter(template, "template");
            this.downloadFileInfoTemplate = template;
            updateFileInfo();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.isHLS || this.totalBytes == null) {
                updateFileInfo();
            }
            if (this.id != null) {
                VideoDownloadManager.INSTANCE.getDownloadEvent().minusAssign(this.downloadEventListener);
                VideoDownloadManager.INSTANCE.getDownloadStatus().remove(this.id);
            }
            this.stopListener = null;
        }

        public final DownloadType getType() {
            return this.internalType;
        }

        public final void setType(DownloadType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            this.internalType = value;
            DownloadMetaDataNotify();
        }

        public final void onDelete() {
            this.bytesDownloaded = 0L;
            this.hlsWrittenProgress = 0;
            this.hlsProgress = 0;
            if (this.id != null) {
                VideoDownloadManager.INSTANCE.getDownloadDeleteEvent().invoke(this.id);
            }
            DownloadMetaDataNotify();
        }

        private final void DownloadMetaDataNotify() {
            long jCoerceIn = RangesKt.coerceIn(System.currentTimeMillis() - this.lastUpdatedMs, 100L, 10000L);
            long j = this.bytesDownloaded;
            long j2 = ((j - this.lastDownloadedBytes) * 1000) / jCoerceIn;
            this.lastDownloadedBytes = j;
            this.lastUpdatedMs = System.currentTimeMillis();
            try {
                long approxTotalBytes = getApproxTotalBytes();
                boolean z = this.isHLS;
                Function1<DownloadObjects.CreateNotificationMetadata, Unit> function1 = this.createNotificationCallback;
                if (z) {
                    DownloadType downloadType = this.internalType;
                    long j3 = this.bytesDownloaded;
                    Integer num = this.hlsTotal;
                    function1.invoke(new DownloadObjects.CreateNotificationMetadata(downloadType, j3, approxTotalBytes, Long.valueOf(this.hlsProgress), num != null ? Long.valueOf(num.intValue()) : null, j2));
                } else {
                    function1.invoke(new DownloadObjects.CreateNotificationMetadata(this.internalType, this.bytesDownloaded, approxTotalBytes, null, null, j2, 24, null));
                }
                if (this.isHLS) {
                    updateFileInfo();
                }
                if (this.internalType == DownloadType.IsStopped || this.internalType == DownloadType.IsFailed) {
                    Function0<Unit> function0 = this.stopListener;
                    if (function0 != null) {
                        function0.invoke();
                    }
                    this.stopListener = null;
                }
                if (this.id != null) {
                    VideoDownloadManager.INSTANCE.getDownloadStatus().put(this.id, getType());
                    VideoDownloadManager.INSTANCE.getDownloadProgressEvent().invoke(new Triple<>(this.id, Long.valueOf(this.bytesDownloaded), Long.valueOf(approxTotalBytes)));
                    VideoDownloadManager.INSTANCE.getDownloadStatusEvent().invoke(TuplesKt.to(this.id, getType()));
                }
            } catch (Throwable th) {
                ArchComponentExtKt.logError(th);
            }
        }

        private final void checkNotification() {
            if (this.lastUpdatedMs + 1000 > System.currentTimeMillis()) {
                return;
            }
            DownloadMetaDataNotify();
        }

        public final void addBytes(long length) {
            this.bytesDownloaded += length;
            if (getType() == DownloadType.IsDownloading) {
                checkNotification();
            }
        }

        public final void addBytesWritten(long length) {
            this.bytesWritten += length;
        }

        public final void addSegment(long length) {
            this.hlsProgress++;
            addBytes(length);
        }

        public final void setWrittenSegment(int segmentIndex) {
            this.hlsWrittenProgress = segmentIndex + 1;
            updateFileInfo();
        }
    }

    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012JW\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\"2-\u0010#\u001a)\b\u0001\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0'\u0012\u0006\u0012\u0004\u0018\u00010\u00010$¢\u0006\u0002\b)H\u0082@¢\u0006\u0002\u0010*JW\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u000e2\b\b\u0002\u0010-\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\"2-\u0010#\u001a)\b\u0001\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0'\u0012\u0006\u0012\u0004\u0018\u00010\u00010$¢\u0006\u0002\b)H\u0086@¢\u0006\u0002\u0010.J\t\u0010/\u001a\u00020\u0003HÂ\u0003J\u0015\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005HÂ\u0003J\t\u00101\u001a\u00020\u0003HÂ\u0003J\t\u00102\u001a\u00020\bHÂ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u00104\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0014J\t\u00105\u001a\u00020\nHÆ\u0003J\t\u00106\u001a\u00020\u000eHÆ\u0003J\t\u00107\u001a\u00020\u0010HÆ\u0003Jx\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010HÆ\u0001¢\u0006\u0002\u00109J\u0014\u0010:\u001a\u00020\u00102\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010<\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010=\u001a\u00020\u0003HÖ\u0081\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001a¨\u0006>"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$LazyStreamDownloadData;", "", ImagesContract.URL, "", "headers", "", "referer", "chuckStartByte", "", "totalLength", "", "downloadLength", "chuckSize", "bufferSize", "", "isResumed", "", "<init>", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;[JLjava/lang/Long;Ljava/lang/Long;JIZ)V", "getTotalLength", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDownloadLength", "getChuckSize", "()J", "getBufferSize", "()I", "()Z", ContentDisposition.Parameters.Size, "getSize", "resolve", "startByte", "endByte", "buffer", "", "callback", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$LazyStreamDownloadResponse;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(JLjava/lang/Long;[BLkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolveSafe", "index", "retries", "(II[BLkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;[JLjava/lang/Long;Ljava/lang/Long;JIZ)Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$LazyStreamDownloadData;", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LazyStreamDownloadData {
        private final int bufferSize;
        private final long chuckSize;
        private final long[] chuckStartByte;
        private final Long downloadLength;
        private final Map<String, String> headers;
        private final boolean isResumed;
        private final String referer;
        private final Long totalLength;
        private final String url;

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final String getUrl() {
            return this.url;
        }

        private final Map<String, String> component2() {
            return this.headers;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        private final String getReferer() {
            return this.referer;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        private final long[] getChuckStartByte() {
            return this.chuckStartByte;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LazyStreamDownloadData copy$default(LazyStreamDownloadData lazyStreamDownloadData, String str, Map map, String str2, long[] jArr, Long l, Long l2, long j, int i, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = lazyStreamDownloadData.url;
            }
            if ((i2 & 2) != 0) {
                map = lazyStreamDownloadData.headers;
            }
            if ((i2 & 4) != 0) {
                str2 = lazyStreamDownloadData.referer;
            }
            if ((i2 & 8) != 0) {
                jArr = lazyStreamDownloadData.chuckStartByte;
            }
            if ((i2 & 16) != 0) {
                l = lazyStreamDownloadData.totalLength;
            }
            if ((i2 & 32) != 0) {
                l2 = lazyStreamDownloadData.downloadLength;
            }
            if ((i2 & 64) != 0) {
                j = lazyStreamDownloadData.chuckSize;
            }
            if ((i2 & 128) != 0) {
                i = lazyStreamDownloadData.bufferSize;
            }
            if ((i2 & 256) != 0) {
                z = lazyStreamDownloadData.isResumed;
            }
            long j2 = j;
            Long l3 = l;
            Long l4 = l2;
            String str3 = str2;
            long[] jArr2 = jArr;
            return lazyStreamDownloadData.copy(str, map, str3, jArr2, l3, l4, j2, i, z);
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Long getTotalLength() {
            return this.totalLength;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Long getDownloadLength() {
            return this.downloadLength;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final long getChuckSize() {
            return this.chuckSize;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getBufferSize() {
            return this.bufferSize;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final boolean getIsResumed() {
            return this.isResumed;
        }

        public final LazyStreamDownloadData copy(String url, Map<String, String> headers, String referer, long[] chuckStartByte, Long totalLength, Long downloadLength, long chuckSize, int bufferSize, boolean isResumed) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(headers, "headers");
            Intrinsics.checkNotNullParameter(referer, "referer");
            Intrinsics.checkNotNullParameter(chuckStartByte, "chuckStartByte");
            return new LazyStreamDownloadData(url, headers, referer, chuckStartByte, totalLength, downloadLength, chuckSize, bufferSize, isResumed);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LazyStreamDownloadData)) {
                return false;
            }
            LazyStreamDownloadData lazyStreamDownloadData = (LazyStreamDownloadData) other;
            return Intrinsics.areEqual(this.url, lazyStreamDownloadData.url) && Intrinsics.areEqual(this.headers, lazyStreamDownloadData.headers) && Intrinsics.areEqual(this.referer, lazyStreamDownloadData.referer) && Intrinsics.areEqual(this.chuckStartByte, lazyStreamDownloadData.chuckStartByte) && Intrinsics.areEqual(this.totalLength, lazyStreamDownloadData.totalLength) && Intrinsics.areEqual(this.downloadLength, lazyStreamDownloadData.downloadLength) && this.chuckSize == lazyStreamDownloadData.chuckSize && this.bufferSize == lazyStreamDownloadData.bufferSize && this.isResumed == lazyStreamDownloadData.isResumed;
        }

        public int hashCode() {
            int iHashCode = ((((((this.url.hashCode() * 31) + this.headers.hashCode()) * 31) + this.referer.hashCode()) * 31) + Arrays.hashCode(this.chuckStartByte)) * 31;
            Long l = this.totalLength;
            int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.downloadLength;
            return ((((((iHashCode2 + (l2 != null ? l2.hashCode() : 0)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.chuckSize)) * 31) + this.bufferSize) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isResumed);
        }

        public String toString() {
            return "LazyStreamDownloadData(url=" + this.url + ", headers=" + this.headers + ", referer=" + this.referer + ", chuckStartByte=" + Arrays.toString(this.chuckStartByte) + ", totalLength=" + this.totalLength + ", downloadLength=" + this.downloadLength + ", chuckSize=" + this.chuckSize + ", bufferSize=" + this.bufferSize + ", isResumed=" + this.isResumed + ')';
        }

        public LazyStreamDownloadData(String url, Map<String, String> headers, String referer, long[] chuckStartByte, Long l, Long l2, long j, int i, boolean z) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(headers, "headers");
            Intrinsics.checkNotNullParameter(referer, "referer");
            Intrinsics.checkNotNullParameter(chuckStartByte, "chuckStartByte");
            this.url = url;
            this.headers = headers;
            this.referer = referer;
            this.chuckStartByte = chuckStartByte;
            this.totalLength = l;
            this.downloadLength = l2;
            this.chuckSize = j;
            this.bufferSize = i;
            this.isResumed = z;
        }

        public final Long getTotalLength() {
            return this.totalLength;
        }

        public final Long getDownloadLength() {
            return this.downloadLength;
        }

        public final long getChuckSize() {
            return this.chuckSize;
        }

        public final int getBufferSize() {
            return this.bufferSize;
        }

        public final boolean isResumed() {
            return this.isResumed;
        }

        public final int getSize() {
            return this.chuckStartByte.length;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object resolve(long j, Long l, byte[] bArr, Function3<? super CoroutineScope, ? super DownloadObjects.LazyStreamDownloadResponse, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Long> continuation) {
            return BuildersKt.withContext(Dispatchers.getIO(), new VideoDownloadManager$LazyStreamDownloadData$resolve$2(j, l, this, bArr, function3, null), continuation);
        }

        public static /* synthetic */ Object resolveSafe$default(LazyStreamDownloadData lazyStreamDownloadData, int i, int i2, byte[] bArr, Function3 function3, Continuation continuation, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                i2 = 3;
            }
            return lazyStreamDownloadData.resolveSafe(i, i2, bArr, function3, continuation);
        }

        /* JADX WARN: Code duplicated, block: B:22:0x0087 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:23:0x0088  */
        /* JADX WARN: Code duplicated, block: B:28:0x00a6 A[Catch: all -> 0x00be, CancellationException -> 0x00cd, IllegalStateException -> 0x00d2, TryCatch #3 {all -> 0x00be, blocks: (B:12:0x0041, B:24:0x0099, B:26:0x00a1, B:28:0x00a6, B:30:0x00ae), top: B:45:0x0041 }] */
        /* JADX WARN: Code duplicated, block: B:30:0x00ae A[Catch: all -> 0x00be, CancellationException -> 0x00cd, IllegalStateException -> 0x00d2, TRY_LEAVE, TryCatch #3 {all -> 0x00be, blocks: (B:12:0x0041, B:24:0x0099, B:26:0x00a1, B:28:0x00a6, B:30:0x00ae), top: B:45:0x0041 }] */
        /* JADX WARN: Code duplicated, block: B:43:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:7:0x001a  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0088 -> B:24:0x0099). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        public final java.lang.Object resolveSafe(int r19, int r20, byte[] r21, kotlin.jvm.functions.Function3<? super kotlinx.coroutines.CoroutineScope, ? super com.lagradost.cloudstream3.utils.downloader.DownloadObjects.LazyStreamDownloadResponse, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r22, kotlin.coroutines.Continuation<? super java.lang.Boolean> r23) {
            /*
                Method dump skipped, instruction units count: 225
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager.LazyStreamDownloadData.resolveSafe(int, int, byte[], kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public static /* synthetic */ Object streamLazy$default(VideoDownloadManager videoDownloadManager, String str, Map map, String str2, long j, long j2, int i, long j3, Continuation continuation, int i2, Object obj) {
        long j4 = (i2 & 16) != 0 ? 10485760L : j2;
        return videoDownloadManager.streamLazy(str, map, str2, j, j4, (i2 & 32) != 0 ? 8192 : i, (i2 & 64) != 0 ? 2 * j4 : j3, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x013d  */
    /* JADX WARN: Code duplicated, block: B:53:0x0213  */
    /* JADX WARN: Code duplicated, block: B:63:0x025f  */
    /* JADX WARN: Code duplicated, block: B:66:0x0266  */
    /* JADX WARN: Code duplicated, block: B:73:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:74:0x02ad  */
    /* JADX WARN: Code duplicated, block: B:76:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:84:0x02e2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:85:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:86:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:90:0x02f9 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:92:0x02fd  */
    public final Object streamLazy(String str, Map<String, String> map, String str2, long j, long j2, int i, long j3, Continuation<? super LazyStreamDownloadData> continuation) {
        C09621 c09621;
        Object obj;
        C09621 c09622;
        String str3;
        String str4;
        long j4;
        long j5;
        int i2;
        long j6;
        Object obj2;
        Map<String, String> map2;
        Long l;
        String string;
        int i3;
        String str5;
        long j7;
        Long l2;
        int i4;
        Object obj3;
        Map<String, String> map3;
        String str6;
        long j8;
        long j9;
        Long l3;
        String str7;
        boolean z;
        boolean z2;
        Long lBoxLong;
        long[] jArr;
        boolean z3;
        Long l4;
        boolean z4;
        String str8;
        NiceResponse niceResponse;
        String str9;
        Long longOrNull;
        String string2;
        if (continuation instanceof C09621) {
            c09621 = (C09621) continuation;
            if ((c09621.label & Integer.MIN_VALUE) != 0) {
                c09621.label -= Integer.MIN_VALUE;
            } else {
                c09621 = new C09621(continuation);
            }
        } else {
            c09621 = new C09621(continuation);
        }
        Object obj4 = c09621.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = c09621.label;
        String str10 = "bytes";
        if (i5 != 0) {
            if (i5 == 1) {
                long j10 = c09621.J$2;
                int i6 = c09621.I$0;
                long j11 = c09621.J$1;
                long j12 = c09621.J$0;
                String str11 = (String) c09621.L$2;
                Map<String, String> map4 = (Map) c09621.L$1;
                str3 = (String) c09621.L$0;
                ResultKt.throwOnFailure(obj4);
                obj2 = obj4;
                obj = coroutine_suspended;
                map2 = map4;
                c09622 = c09621;
                i2 = i6;
                str4 = str11;
                j5 = j11;
                j4 = j12;
                j6 = j10;
            } else {
                if (i5 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                long j13 = c09621.J$2;
                int i7 = c09621.I$0;
                j9 = c09621.J$1;
                j8 = c09621.J$0;
                Long l5 = (Long) c09621.L$4;
                str6 = (String) c09621.L$2;
                map3 = (Map) c09621.L$1;
                String str12 = (String) c09621.L$0;
                ResultKt.throwOnFailure(obj4);
                j7 = j13;
                i4 = i7;
                l2 = l5;
                str8 = "toLowerCase(...)";
                obj3 = obj4;
                str5 = str12;
                str10 = "bytes";
                i3 = 2;
            }
            niceResponse = (NiceResponse) obj3;
            if (l2 == null) {
                str9 = niceResponse.getHeaders().get("Content-Range");
                if (str9 != null || (string2 = StringsKt.trim((CharSequence) str9).toString()) == null) {
                    str7 = str7;
                    l3 = null;
                    longOrNull = l3;
                } else {
                    String lowerCase = string2.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, str8);
                    if (lowerCase != null) {
                        l3 = null;
                        if (StringsKt.startsWith$default(lowerCase, str10, false, i3, (Object) null)) {
                            longOrNull = StringsKt.toLongOrNull(StringsKt.substringAfter$default(lowerCase, "/", (String) null, i3, (Object) null));
                            str7 = TAG;
                        } else {
                            str7 = str7;
                            Log.v(str7, "Unknown Content-Range unit: " + lowerCase);
                        }
                    } else {
                        str7 = str7;
                        l3 = null;
                    }
                    longOrNull = l3;
                }
                l2 = longOrNull;
            } else {
                str7 = str7;
                l3 = null;
            }
            int code = niceResponse.getCode();
            str3 = str5;
            j4 = j8;
            map2 = map3;
            str4 = str6;
            l = l2;
            z = code == 206;
            Log.d(str7, "Starting stream with url=" + str3 + ", startByte=" + j4 + ", contentLength=" + l + ", hasRangeSupport=" + z);
            if (z) {
                z2 = true;
                if (l != null || l.longValue() < j7) {
                    if (l != null) {
                        lBoxLong = Boxing.boxLong(l.longValue() - j4);
                    } else {
                        lBoxLong = l3;
                    }
                    z2 = true;
                    z3 = false;
                    jArr = new long[]{j4};
                } else {
                    Long lBoxLong2 = Boxing.boxLong(l.longValue() - j4);
                    int iLongValue = (int) (((lBoxLong2.longValue() + j9) - 1) / j9);
                    long[] jArr2 = new long[iLongValue];
                    int i8 = 0;
                    while (true) {
                        l4 = lBoxLong2;
                        if (i8 >= iLongValue) {
                            break;
                        }
                        jArr2[i8] = (((long) i8) * j9) + j4;
                        i8++;
                        lBoxLong2 = l4;
                        z2 = true;
                    }
                    lBoxLong = l4;
                    jArr = jArr2;
                    z3 = false;
                }
            } else {
                z2 = true;
                z3 = false;
                jArr = new long[]{0};
                lBoxLong = l;
            }
            if (j4 > 0 || !z) {
                z4 = z3;
            } else {
                z4 = z2;
            }
            return new LazyStreamDownloadData(str3, map2, str4, jArr, l, lBoxLong, j9, i4, z4);
        }
        ResultKt.throwOnFailure(obj4);
        if (j2 <= 1000) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        obj = coroutine_suspended;
        Requests app2 = MainActivityKt.getApp();
        c09621.L$0 = str;
        c09621.L$1 = map;
        c09621.L$2 = str2;
        c09621.J$0 = j;
        c09621.J$1 = j2;
        c09621.I$0 = i;
        c09621.J$2 = j3;
        c09621.label = 1;
        C09621 c09623 = c09621;
        Object objHead$default = Requests.head$default(app2, str, map, str2, null, null, false, 0, null, 0L, null, false, null, c09623, 3064, null);
        c09622 = c09623;
        if (objHead$default == obj) {
            return obj;
        }
        str3 = str;
        str4 = str2;
        j4 = j;
        j5 = j2;
        i2 = i;
        j6 = j3;
        obj2 = objHead$default;
        map2 = map;
        NiceResponse niceResponse2 = (NiceResponse) obj2;
        Long size = niceResponse2.getSize();
        l = (size == null || size.longValue() > 0) ? size : null;
        Object obj5 = obj;
        long j14 = j6;
        String str13 = niceResponse2.getHeaders().get("Accept-Ranges");
        if (str13 != null) {
            String lowerCase2 = str13.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            if (lowerCase2 != null) {
                string = StringsKt.trim((CharSequence) lowerCase2).toString();
            } else {
                string = null;
            }
        } else {
            string = null;
        }
        if (!Intrinsics.areEqual(string, "none")) {
            if (!Intrinsics.areEqual(string, str10)) {
                String str14 = niceResponse2.getHeaders().get("Accept-Ranges");
                if (str14 != null) {
                    Boxing.boxInt(Log.v(str7, "Unknown Accept-Ranges tag: " + str14));
                }
                Requests app3 = MainActivityKt.getApp();
                StringBuilder sb = new StringBuilder("bytes=0-");
                sb.append(l != null ? Math.min(Math.max(l.longValue() - 1, 3L), 1023L) : 1023L);
                Map mapPlus = MapsKt.plus(map2, MapsKt.mapOf(TuplesKt.to("Range", sb.toString())));
                c09622.L$0 = str3;
                c09622.L$1 = map2;
                c09622.L$2 = str4;
                c09622.L$3 = SpillingKt.nullOutSpilledVariable(niceResponse2);
                c09622.L$4 = l;
                c09622.J$0 = j4;
                c09622.J$1 = j5;
                c09622.I$0 = i2;
                c09622.J$2 = j14;
                i3 = 2;
                c09622.label = 2;
                long j15 = j4;
                long j16 = j5;
                C09621 c09624 = c09622;
                str5 = str3;
                j7 = j14;
                l2 = l;
                i4 = i2;
                Map<String, String> map5 = map2;
                obj3 = Requests.get$default(app3, str5, mapPlus, str4, null, null, false, 0, null, 0L, null, false, null, c09624, 3064, null);
                if (obj3 == obj5) {
                    return obj5;
                }
                map3 = map5;
                str6 = str4;
                j8 = j15;
                j9 = j16;
                niceResponse = (NiceResponse) obj3;
                if (l2 == null) {
                    str9 = niceResponse.getHeaders().get("Content-Range");
                    if (str9 != null) {
                        str7 = str7;
                        l3 = null;
                        longOrNull = l3;
                    } else {
                        str7 = str7;
                        l3 = null;
                        longOrNull = l3;
                    }
                    l2 = longOrNull;
                } else {
                    str7 = str7;
                    l3 = null;
                }
                int code2 = niceResponse.getCode();
                str3 = str5;
                j4 = j8;
                map2 = map3;
                str4 = str6;
                l = l2;
                if (code2 == 206) {
                }
                Log.d(str7, "Starting stream with url=" + str3 + ", startByte=" + j4 + ", contentLength=" + l + ", hasRangeSupport=" + z);
                if (z) {
                    z2 = true;
                    z3 = false;
                    jArr = new long[]{0};
                    lBoxLong = l;
                } else {
                    z2 = true;
                    if (l != null) {
                        if (l != null) {
                            lBoxLong = Boxing.boxLong(l.longValue() - j4);
                        } else {
                            lBoxLong = l3;
                        }
                        z2 = true;
                        z3 = false;
                        jArr = new long[]{j4};
                    } else {
                        if (l != null) {
                            lBoxLong = Boxing.boxLong(l.longValue() - j4);
                        } else {
                            lBoxLong = l3;
                        }
                        z2 = true;
                        z3 = false;
                        jArr = new long[]{j4};
                    }
                }
                if (j4 > 0) {
                    z4 = z3;
                } else {
                    z4 = z3;
                }
                return new LazyStreamDownloadData(str3, map2, str4, jArr, l, lBoxLong, j9, i4, z4);
            }
            i4 = i2;
            j9 = j5;
            j7 = j14;
            l3 = null;
            Log.d(str7, "Starting stream with url=" + str3 + ", startByte=" + j4 + ", contentLength=" + l + ", hasRangeSupport=" + z);
            if (z) {
                z2 = true;
                z3 = false;
                jArr = new long[]{0};
                lBoxLong = l;
            } else {
                z2 = true;
                if (l != null) {
                    if (l != null) {
                        lBoxLong = Boxing.boxLong(l.longValue() - j4);
                    } else {
                        lBoxLong = l3;
                    }
                    z2 = true;
                    z3 = false;
                    jArr = new long[]{j4};
                } else {
                    if (l != null) {
                        lBoxLong = Boxing.boxLong(l.longValue() - j4);
                    } else {
                        lBoxLong = l3;
                    }
                    z2 = true;
                    z3 = false;
                    jArr = new long[]{j4};
                }
            }
            if (j4 > 0) {
                z4 = z3;
            } else {
                z4 = z3;
            }
            return new LazyStreamDownloadData(str3, map2, str4, jArr, l, lBoxLong, j9, i4, z4);
        }
        i4 = i2;
        j9 = j5;
        j7 = j14;
        l3 = null;
        Log.d(str7, "Starting stream with url=" + str3 + ", startByte=" + j4 + ", contentLength=" + l + ", hasRangeSupport=" + z);
        if (z) {
            z2 = true;
            z3 = false;
            jArr = new long[]{0};
            lBoxLong = l;
        } else {
            z2 = true;
            if (l != null) {
                if (l != null) {
                    lBoxLong = Boxing.boxLong(l.longValue() - j4);
                } else {
                    lBoxLong = l3;
                }
                z2 = true;
                z3 = false;
                jArr = new long[]{j4};
            } else {
                if (l != null) {
                    lBoxLong = Boxing.boxLong(l.longValue() - j4);
                } else {
                    lBoxLong = l3;
                }
                z2 = true;
                z3 = false;
                jArr = new long[]{j4};
            }
        }
        if (j4 > 0) {
            z4 = z3;
        } else {
            z4 = z3;
        }
        return new LazyStreamDownloadData(str3, map2, str4, jArr, l, lBoxLong, j9, i4, z4);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadThing$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadStatus;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadThing$2", f = "DownloadManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1037, 1212}, m = "invokeSuspend", n = {"$this$withContext", "fileStream", TtmlNode.TAG_METADATA, "baseFile", "basePath", "displayName", "stream", "$this$withContext", "fileStream", TtmlNode.TAG_METADATA, "baseFile", "basePath", "displayName", "stream", "items", "currentMutex", "current", "fileMutex", "pendingData", "fileChecker", "jobs"}, nl = {1049, 1213}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13"}, v = 2)
    static final class C09612 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DownloadObjects.DownloadStatus>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Function1<DownloadObjects.CreateNotificationMetadata, Unit> $createNotificationCallback;
        final /* synthetic */ String $extension;
        final /* synthetic */ String $folder;
        final /* synthetic */ IDownloadableMinimum $link;
        final /* synthetic */ long $minimumSize;
        final /* synthetic */ String $name;
        final /* synthetic */ int $parallelConnections;
        final /* synthetic */ Integer $parentId;
        final /* synthetic */ boolean $tryResume;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C09612(int i, IDownloadableMinimum iDownloadableMinimum, Integer num, Function1<? super DownloadObjects.CreateNotificationMetadata, Unit> function1, Context context, String str, String str2, String str3, boolean z, long j, Continuation<? super C09612> continuation) {
            super(2, continuation);
            this.$parallelConnections = i;
            this.$link = iDownloadableMinimum;
            this.$parentId = num;
            this.$createNotificationCallback = function1;
            this.$context = context;
            this.$name = str;
            this.$extension = str2;
            this.$folder = str3;
            this.$tryResume = z;
            this.$minimumSize = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09612 c09612 = new C09612(this.$parallelConnections, this.$link, this.$parentId, this.$createNotificationCallback, this.$context, this.$name, this.$extension, this.$folder, this.$tryResume, this.$minimumSize, continuation);
            c09612.L$0 = obj;
            return c09612;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super DownloadObjects.DownloadStatus> continuation) {
            return ((C09612) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:101:0x03db A[Catch: all -> 0x0052, IOException -> 0x0055, TRY_ENTER, TRY_LEAVE, TryCatch #9 {IOException -> 0x0055, all -> 0x0052, blocks: (B:7:0x004b, B:74:0x0351, B:76:0x0361, B:77:0x0366, B:79:0x036e, B:85:0x0381, B:87:0x0389, B:93:0x03ad, B:95:0x03b7, B:101:0x03db), top: B:132:0x004b }] */
        /* JADX WARN: Code duplicated, block: B:104:0x03ea  */
        /* JADX WARN: Code duplicated, block: B:116:0x0410  */
        /* JADX WARN: Code duplicated, block: B:58:0x022a  */
        /* JADX WARN: Code duplicated, block: B:62:0x02ae A[Catch: all -> 0x03f3, IOException -> 0x03f7, TRY_LEAVE, TryCatch #10 {IOException -> 0x03f7, all -> 0x03f3, blocks: (B:43:0x01aa, B:45:0x01b2, B:47:0x01c0, B:53:0x01e4, B:55:0x01ea, B:57:0x01f6, B:59:0x022c, B:60:0x02a8, B:62:0x02ae, B:65:0x02c8, B:70:0x02ea), top: B:131:0x01aa }] */
        /* JADX WARN: Code duplicated, block: B:73:0x034a  */
        /* JADX WARN: Code duplicated, block: B:76:0x0361 A[Catch: all -> 0x0052, IOException -> 0x0055, TryCatch #9 {IOException -> 0x0055, all -> 0x0052, blocks: (B:7:0x004b, B:74:0x0351, B:76:0x0361, B:77:0x0366, B:79:0x036e, B:85:0x0381, B:87:0x0389, B:93:0x03ad, B:95:0x03b7, B:101:0x03db), top: B:132:0x004b }] */
        /* JADX WARN: Code duplicated, block: B:79:0x036e A[Catch: all -> 0x0052, IOException -> 0x0055, TRY_LEAVE, TryCatch #9 {IOException -> 0x0055, all -> 0x0052, blocks: (B:7:0x004b, B:74:0x0351, B:76:0x0361, B:77:0x0366, B:79:0x036e, B:85:0x0381, B:87:0x0389, B:93:0x03ad, B:95:0x03b7, B:101:0x03db), top: B:132:0x004b }] */
        /* JADX WARN: Code duplicated, block: B:82:0x0378  */
        /* JADX WARN: Code duplicated, block: B:85:0x0381 A[Catch: all -> 0x0052, IOException -> 0x0055, TRY_ENTER, TryCatch #9 {IOException -> 0x0055, all -> 0x0052, blocks: (B:7:0x004b, B:74:0x0351, B:76:0x0361, B:77:0x0366, B:79:0x036e, B:85:0x0381, B:87:0x0389, B:93:0x03ad, B:95:0x03b7, B:101:0x03db), top: B:132:0x004b }] */
        /* JADX WARN: Code duplicated, block: B:87:0x0389 A[Catch: all -> 0x0052, IOException -> 0x0055, TRY_LEAVE, TryCatch #9 {IOException -> 0x0055, all -> 0x0052, blocks: (B:7:0x004b, B:74:0x0351, B:76:0x0361, B:77:0x0366, B:79:0x036e, B:85:0x0381, B:87:0x0389, B:93:0x03ad, B:95:0x03b7, B:101:0x03db), top: B:132:0x004b }] */
        /* JADX WARN: Code duplicated, block: B:90:0x03a4  */
        /* JADX WARN: Code duplicated, block: B:93:0x03ad A[Catch: all -> 0x0052, IOException -> 0x0055, TRY_ENTER, TryCatch #9 {IOException -> 0x0055, all -> 0x0052, blocks: (B:7:0x004b, B:74:0x0351, B:76:0x0361, B:77:0x0366, B:79:0x036e, B:85:0x0381, B:87:0x0389, B:93:0x03ad, B:95:0x03b7, B:101:0x03db), top: B:132:0x004b }] */
        /* JADX WARN: Code duplicated, block: B:95:0x03b7 A[Catch: all -> 0x0052, IOException -> 0x0055, TRY_LEAVE, TryCatch #9 {IOException -> 0x0055, all -> 0x0052, blocks: (B:7:0x004b, B:74:0x0351, B:76:0x0361, B:77:0x0366, B:79:0x036e, B:85:0x0381, B:87:0x0389, B:93:0x03ad, B:95:0x03b7, B:101:0x03db), top: B:132:0x004b }] */
        /* JADX WARN: Code duplicated, block: B:98:0x03d2  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r27v2, types: [java.lang.Object, kotlin.collections.IntIterator] */
        /* JADX WARN: Type inference failed for: r3v0, types: [com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$DownloadMetaData, int] */
        /* JADX WARN: Type inference failed for: r3v32, types: [T, com.lagradost.cloudstream3.utils.downloader.DownloadObjects$StreamData] */
        /* JADX WARN: Type inference failed for: r3v35, types: [T, java.io.OutputStream] */
        /* JADX WARN: Type inference failed for: r4v0, types: [kotlin.jvm.internal.Ref$ObjectRef] */
        /* JADX WARN: Type inference failed for: r9v1, types: [T, com.lagradost.cloudstream3.utils.downloader.DownloadObjects$StreamData] */
        /* JADX WARN: Type inference failed for: r9v4, types: [T, java.io.OutputStream] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            DownloadMetaData downloadMetaData;
            Ref.ObjectRef objectRef;
            DownloadMetaData downloadMetaData2;
            String strComponent2;
            String displayName;
            Ref.ObjectRef objectRef2;
            Object objStreamLazy$default;
            SafeFile safeFile;
            Ref.ObjectRef objectRef3;
            LazyStreamDownloadData lazyStreamDownloadData;
            Mutex mutexMutex$default;
            ?? it;
            Mutex mutexMutex$default2;
            HashMap map;
            Job jobLaunch$default;
            ArrayList arrayList;
            Iterator<Integer> it2;
            Job job;
            final ArrayList arrayList2;
            Ref.ObjectRef objectRef4;
            Job job2;
            int i;
            DownloadMetaData downloadMetaData3;
            Ref.ObjectRef objectRef5;
            OutputStream outputStream;
            OutputStream outputStream2;
            OutputStream outputStream3;
            OutputStream outputStream4;
            OutputStream outputStream5;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r3 = this.label;
            ?? r4 = 0;
            try {
                if (r3 == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.$parallelConnections < 1) {
                        return VideoDownloadManager.DOWNLOAD_INVALID_INPUT;
                    }
                    Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
                    downloadMetaData = new DownloadMetaData(this.$parentId, this.$link.getUrl().hashCode(), 0L, 0L, Boxing.boxLong(0L), 0L, 0L, this.$createNotificationCallback, null, false, 0, null, 0, null, 15720, null);
                    try {
                        Pair<SafeFile, String> basePath$app = DownloadFileManagement.INSTANCE.getBasePath$app(this.$context);
                        SafeFile safeFileComponent1 = basePath$app.component1();
                        strComponent2 = basePath$app.component2();
                        displayName = VideoDownloadManager.INSTANCE.getDisplayName(this.$name, this.$extension);
                        if (safeFileComponent1 == null) {
                            DownloadObjects.DownloadStatus downloadStatus = VideoDownloadManager.DOWNLOAD_BAD_CONFIG;
                            OutputStream outputStream6 = (OutputStream) objectRef6.element;
                            if (outputStream6 != null) {
                                SafeFileKt.closeQuietly(outputStream6);
                            }
                            downloadMetaData.close();
                            return downloadStatus;
                        }
                        objectRef2 = new Ref.ObjectRef();
                        objectRef2.element = VideoDownloadManager.INSTANCE.setupStream(safeFileComponent1, this.$name, this.$folder, this.$extension, this.$tryResume);
                        objectRef6.element = ((DownloadObjects.StreamData) objectRef2.element).open();
                        downloadMetaData.setResumeLength(((DownloadObjects.StreamData) objectRef2.element).getStartAt());
                        downloadMetaData.setType(DownloadType.IsPending);
                        VideoDownloadManager videoDownloadManager = VideoDownloadManager.INSTANCE;
                        String strReplace$default = StringsKt.replace$default(this.$link.getUrl(), Stream.ID_UNKNOWN, "%20", false, 4, (Object) null);
                        String referer = this.$link.getReferer();
                        long startAt = ((DownloadObjects.StreamData) objectRef2.element).getStartAt();
                        this.L$0 = coroutineScope;
                        this.L$1 = objectRef6;
                        this.L$2 = downloadMetaData;
                        this.L$3 = safeFileComponent1;
                        this.L$4 = strComponent2;
                        this.L$5 = displayName;
                        this.L$6 = objectRef2;
                        this.label = 1;
                        objStreamLazy$default = VideoDownloadManager.streamLazy$default(videoDownloadManager, strReplace$default, DownloadUtils.INSTANCE.appendAndDontOverride$app(this.$link.getHeaders(), MapsKt.mapOf(TuplesKt.to("user-agent", VideoDownloadManager.USER_AGENT))), referer, startAt, 0L, 0, 0L, this, 112, null);
                        if (objStreamLazy$default != coroutine_suspended) {
                            safeFile = safeFileComponent1;
                            objectRef3 = objectRef6;
                            String str = displayName;
                            String str2 = strComponent2;
                            lazyStreamDownloadData = (LazyStreamDownloadData) objStreamLazy$default;
                            if (lazyStreamDownloadData.getTotalLength() == null) {
                            }
                            if (lazyStreamDownloadData.isResumed()) {
                            }
                            downloadMetaData.setTotalBytes(lazyStreamDownloadData.getTotalLength());
                            downloadMetaData.setType(DownloadType.IsDownloading);
                            downloadMetaData.setDownloadFileInfoTemplate(new DownloadObjects.DownloadedFileInfo(downloadMetaData.getApproxTotalBytes(), this.$folder, str, null, str2, null, 40, null));
                            mutexMutex$default = MutexKt.Mutex$default(false, 1, null);
                            it = RangesKt.until(0, lazyStreamDownloadData.getSize()).iterator();
                            mutexMutex$default2 = MutexKt.Mutex$default(false, 1, null);
                            map = new HashMap();
                            jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), null, new VideoDownloadManager$downloadThing$2$fileChecker$1(objectRef2, mutexMutex$default2, downloadMetaData, null), 2, null);
                            IntRange intRangeUntil = RangesKt.until(0, this.$parallelConnections);
                            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
                            it2 = intRangeUntil.iterator();
                            while (it2.hasNext()) {
                                ((IntIterator) it2).nextInt();
                                LazyStreamDownloadData lazyStreamDownloadData2 = lazyStreamDownloadData;
                                downloadMetaData3 = downloadMetaData;
                                objectRef5 = objectRef3;
                                arrayList.add(BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), null, new VideoDownloadManager$downloadThing$2$jobs$1$1(lazyStreamDownloadData2, mutexMutex$default2, mutexMutex$default, downloadMetaData3, objectRef5, map, it, null), 2, null));
                                jobLaunch$default = jobLaunch$default;
                                lazyStreamDownloadData = lazyStreamDownloadData2;
                            }
                            LazyStreamDownloadData lazyStreamDownloadData3 = lazyStreamDownloadData;
                            job = jobLaunch$default;
                            arrayList2 = arrayList;
                            downloadMetaData.setOnStop(new Function0() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadThing$2$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return VideoDownloadManager.C09612.invokeSuspend$lambda$1(arrayList2);
                                }
                            });
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                            this.L$1 = objectRef3;
                            this.L$2 = downloadMetaData;
                            this.L$3 = SpillingKt.nullOutSpilledVariable(safeFile);
                            this.L$4 = SpillingKt.nullOutSpilledVariable(str2);
                            this.L$5 = SpillingKt.nullOutSpilledVariable(str);
                            this.L$6 = objectRef2;
                            this.L$7 = SpillingKt.nullOutSpilledVariable(lazyStreamDownloadData3);
                            this.L$8 = SpillingKt.nullOutSpilledVariable(mutexMutex$default);
                            this.L$9 = SpillingKt.nullOutSpilledVariable(it);
                            this.L$10 = SpillingKt.nullOutSpilledVariable(mutexMutex$default2);
                            this.L$11 = SpillingKt.nullOutSpilledVariable(map);
                            this.L$12 = job;
                            this.L$13 = SpillingKt.nullOutSpilledVariable(arrayList2);
                            this.label = 2;
                            if (DownloadUtils.INSTANCE.join$app(arrayList2, this) != coroutine_suspended) {
                                objectRef4 = objectRef2;
                                job2 = job;
                                downloadMetaData2 = downloadMetaData;
                                objectRef = objectRef3;
                                i = 1;
                                Job.CC.cancel$default(job2, (CancellationException) null, i, (Object) null);
                                downloadMetaData2.removeStopListener();
                                if (!((DownloadObjects.StreamData) objectRef4.element).getExists()) {
                                    downloadMetaData2.setType(DownloadType.IsStopped);
                                }
                                if (downloadMetaData2.getType() == DownloadType.IsFailed) {
                                    DownloadObjects.DownloadStatus downloadStatusFailedStatus = downloadMetaData2.failedStatus();
                                    outputStream5 = (OutputStream) objectRef.element;
                                    if (outputStream5 != null) {
                                        SafeFileKt.closeQuietly(outputStream5);
                                    }
                                    downloadMetaData2.close();
                                    return downloadStatusFailedStatus;
                                }
                                if (downloadMetaData2.getType() == DownloadType.IsStopped) {
                                    SafeFileKt.closeQuietly((Closeable) objectRef.element);
                                    downloadMetaData2.onDelete();
                                    ((DownloadObjects.StreamData) objectRef4.element).delete();
                                    DownloadObjects.DownloadStatus downloadStatus2 = VideoDownloadManager.DOWNLOAD_STOPPED;
                                    outputStream4 = (OutputStream) objectRef.element;
                                    if (outputStream4 != null) {
                                        SafeFileKt.closeQuietly(outputStream4);
                                    }
                                    downloadMetaData2.close();
                                    return downloadStatus2;
                                }
                                if (downloadMetaData2.getBytesDownloaded() < this.$minimumSize) {
                                    downloadMetaData2.setType(DownloadType.IsDone);
                                    DownloadObjects.DownloadStatus downloadStatus3 = VideoDownloadManager.DOWNLOAD_SUCCESS;
                                    outputStream2 = (OutputStream) objectRef.element;
                                    if (outputStream2 != null) {
                                        SafeFileKt.closeQuietly(outputStream2);
                                    }
                                    downloadMetaData2.close();
                                    return downloadStatus3;
                                }
                                SafeFileKt.closeQuietly((Closeable) objectRef.element);
                                downloadMetaData2.onDelete();
                                ((DownloadObjects.StreamData) objectRef4.element).delete();
                                DownloadObjects.DownloadStatus downloadStatus4 = VideoDownloadManager.DOWNLOAD_INVALID_INPUT;
                                outputStream3 = (OutputStream) objectRef.element;
                                if (outputStream3 != null) {
                                    SafeFileKt.closeQuietly(outputStream3);
                                }
                                downloadMetaData2.close();
                                return downloadStatus4;
                            }
                        }
                        return coroutine_suspended;
                    } catch (IOException e) {
                        e = e;
                        ArchComponentExtKt.logError(e);
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        objectRef = objectRef6;
                        downloadMetaData2 = downloadMetaData;
                        ArchComponentExtKt.logError(th);
                        downloadMetaData2.setType(DownloadType.IsFailed);
                        DownloadObjects.DownloadStatus downloadStatusFailedStatus2 = downloadMetaData2.failedStatus();
                        outputStream = (OutputStream) objectRef.element;
                        if (outputStream != null) {
                            SafeFileKt.closeQuietly(outputStream);
                        }
                        downloadMetaData2.close();
                        return downloadStatusFailedStatus2;
                    }
                }
                if (r3 == 1) {
                    Ref.ObjectRef objectRef7 = (Ref.ObjectRef) this.L$6;
                    displayName = (String) this.L$5;
                    strComponent2 = (String) this.L$4;
                    SafeFile safeFile2 = (SafeFile) this.L$3;
                    DownloadMetaData downloadMetaData4 = (DownloadMetaData) this.L$2;
                    objectRef3 = (Ref.ObjectRef) this.L$1;
                    try {
                        ResultKt.throwOnFailure(obj);
                        objStreamLazy$default = obj;
                        objectRef2 = objectRef7;
                        safeFile = safeFile2;
                        downloadMetaData = downloadMetaData4;
                        String str3 = displayName;
                        String str4 = strComponent2;
                        try {
                            lazyStreamDownloadData = (LazyStreamDownloadData) objStreamLazy$default;
                            if (lazyStreamDownloadData.getTotalLength() == null && lazyStreamDownloadData.getTotalLength().longValue() < this.$minimumSize) {
                                SafeFileKt.closeQuietly((Closeable) objectRef3.element);
                                downloadMetaData.onDelete();
                                ((DownloadObjects.StreamData) objectRef2.element).delete();
                                DownloadObjects.DownloadStatus downloadStatus5 = VideoDownloadManager.DOWNLOAD_INVALID_INPUT;
                                OutputStream outputStream7 = (OutputStream) objectRef3.element;
                                if (outputStream7 != null) {
                                    SafeFileKt.closeQuietly(outputStream7);
                                }
                                downloadMetaData.close();
                                return downloadStatus5;
                            }
                            if (lazyStreamDownloadData.isResumed() && ((DownloadObjects.StreamData) objectRef2.element).getStartAt() > 0) {
                                SafeFileKt.closeQuietly((Closeable) objectRef3.element);
                                ((DownloadObjects.StreamData) objectRef2.element).delete();
                                downloadMetaData.setResumeLength(0L);
                                objectRef2.element = VideoDownloadManager.INSTANCE.setupStream(safeFile, this.$name, this.$folder, this.$extension, false);
                                objectRef3.element = ((DownloadObjects.StreamData) objectRef2.element).open();
                            }
                            downloadMetaData.setTotalBytes(lazyStreamDownloadData.getTotalLength());
                            downloadMetaData.setType(DownloadType.IsDownloading);
                            downloadMetaData.setDownloadFileInfoTemplate(new DownloadObjects.DownloadedFileInfo(downloadMetaData.getApproxTotalBytes(), this.$folder, str3, null, str4, null, 40, null));
                            mutexMutex$default = MutexKt.Mutex$default(false, 1, null);
                            it = RangesKt.until(0, lazyStreamDownloadData.getSize()).iterator();
                            mutexMutex$default2 = MutexKt.Mutex$default(false, 1, null);
                            map = new HashMap();
                            jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), null, new VideoDownloadManager$downloadThing$2$fileChecker$1(objectRef2, mutexMutex$default2, downloadMetaData, null), 2, null);
                            IntRange intRangeUntil2 = RangesKt.until(0, this.$parallelConnections);
                            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil2, 10));
                            it2 = intRangeUntil2.iterator();
                            while (it2.hasNext()) {
                                ((IntIterator) it2).nextInt();
                                LazyStreamDownloadData lazyStreamDownloadData4 = lazyStreamDownloadData;
                                downloadMetaData3 = downloadMetaData;
                                objectRef5 = objectRef3;
                                try {
                                    arrayList.add(BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), null, new VideoDownloadManager$downloadThing$2$jobs$1$1(lazyStreamDownloadData4, mutexMutex$default2, mutexMutex$default, downloadMetaData3, objectRef5, map, it, null), 2, null));
                                    jobLaunch$default = jobLaunch$default;
                                    lazyStreamDownloadData = lazyStreamDownloadData4;
                                } catch (IOException e2) {
                                    e = e2;
                                    downloadMetaData = downloadMetaData3;
                                    objectRef3 = objectRef5;
                                    ArchComponentExtKt.logError(e);
                                    throw e;
                                } catch (Throwable th2) {
                                    th = th2;
                                    downloadMetaData = downloadMetaData3;
                                    objectRef3 = objectRef5;
                                    downloadMetaData2 = downloadMetaData;
                                    objectRef = objectRef3;
                                    ArchComponentExtKt.logError(th);
                                    downloadMetaData2.setType(DownloadType.IsFailed);
                                    DownloadObjects.DownloadStatus downloadStatusFailedStatus3 = downloadMetaData2.failedStatus();
                                    outputStream = (OutputStream) objectRef.element;
                                    if (outputStream != null) {
                                        SafeFileKt.closeQuietly(outputStream);
                                    }
                                    downloadMetaData2.close();
                                    return downloadStatusFailedStatus3;
                                }
                            }
                            LazyStreamDownloadData lazyStreamDownloadData5 = lazyStreamDownloadData;
                            job = jobLaunch$default;
                            arrayList2 = arrayList;
                            downloadMetaData.setOnStop(new Function0() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadThing$2$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return VideoDownloadManager.C09612.invokeSuspend$lambda$1(arrayList2);
                                }
                            });
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                            this.L$1 = objectRef3;
                            this.L$2 = downloadMetaData;
                            this.L$3 = SpillingKt.nullOutSpilledVariable(safeFile);
                            this.L$4 = SpillingKt.nullOutSpilledVariable(str4);
                            this.L$5 = SpillingKt.nullOutSpilledVariable(str3);
                            this.L$6 = objectRef2;
                            this.L$7 = SpillingKt.nullOutSpilledVariable(lazyStreamDownloadData5);
                            this.L$8 = SpillingKt.nullOutSpilledVariable(mutexMutex$default);
                            this.L$9 = SpillingKt.nullOutSpilledVariable(it);
                            this.L$10 = SpillingKt.nullOutSpilledVariable(mutexMutex$default2);
                            this.L$11 = SpillingKt.nullOutSpilledVariable(map);
                            this.L$12 = job;
                            this.L$13 = SpillingKt.nullOutSpilledVariable(arrayList2);
                            this.label = 2;
                            if (DownloadUtils.INSTANCE.join$app(arrayList2, this) != coroutine_suspended) {
                                objectRef4 = objectRef2;
                                job2 = job;
                                downloadMetaData2 = downloadMetaData;
                                objectRef = objectRef3;
                                i = 1;
                            }
                            return coroutine_suspended;
                        } catch (IOException e3) {
                            e = e3;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        ArchComponentExtKt.logError(e);
                        throw e;
                    } catch (Throwable th4) {
                        th = th4;
                        downloadMetaData2 = downloadMetaData4;
                        objectRef = objectRef3;
                        ArchComponentExtKt.logError(th);
                        downloadMetaData2.setType(DownloadType.IsFailed);
                        DownloadObjects.DownloadStatus downloadStatusFailedStatus4 = downloadMetaData2.failedStatus();
                        outputStream = (OutputStream) objectRef.element;
                        if (outputStream != null) {
                            SafeFileKt.closeQuietly(outputStream);
                        }
                        downloadMetaData2.close();
                        return downloadStatusFailedStatus4;
                    }
                } else {
                    if (r3 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    job2 = (Job) this.L$12;
                    objectRef4 = (Ref.ObjectRef) this.L$6;
                    downloadMetaData2 = (DownloadMetaData) this.L$2;
                    objectRef = (Ref.ObjectRef) this.L$1;
                    try {
                        ResultKt.throwOnFailure(obj);
                        i = 1;
                    } catch (IOException e5) {
                        e = e5;
                        ArchComponentExtKt.logError(e);
                        throw e;
                    } catch (Throwable th5) {
                        th = th5;
                        ArchComponentExtKt.logError(th);
                        downloadMetaData2.setType(DownloadType.IsFailed);
                        DownloadObjects.DownloadStatus downloadStatusFailedStatus5 = downloadMetaData2.failedStatus();
                        outputStream = (OutputStream) objectRef.element;
                        if (outputStream != null) {
                            SafeFileKt.closeQuietly(outputStream);
                        }
                        downloadMetaData2.close();
                        return downloadStatusFailedStatus5;
                    }
                }
                Job.CC.cancel$default(job2, (CancellationException) null, i, (Object) null);
                downloadMetaData2.removeStopListener();
                if (!((DownloadObjects.StreamData) objectRef4.element).getExists()) {
                    downloadMetaData2.setType(DownloadType.IsStopped);
                }
                if (downloadMetaData2.getType() == DownloadType.IsFailed) {
                    DownloadObjects.DownloadStatus downloadStatusFailedStatus6 = downloadMetaData2.failedStatus();
                    outputStream5 = (OutputStream) objectRef.element;
                    if (outputStream5 != null) {
                        SafeFileKt.closeQuietly(outputStream5);
                    }
                    downloadMetaData2.close();
                    return downloadStatusFailedStatus6;
                }
                if (downloadMetaData2.getType() == DownloadType.IsStopped) {
                    SafeFileKt.closeQuietly((Closeable) objectRef.element);
                    downloadMetaData2.onDelete();
                    ((DownloadObjects.StreamData) objectRef4.element).delete();
                    DownloadObjects.DownloadStatus downloadStatus6 = VideoDownloadManager.DOWNLOAD_STOPPED;
                    outputStream4 = (OutputStream) objectRef.element;
                    if (outputStream4 != null) {
                        SafeFileKt.closeQuietly(outputStream4);
                    }
                    downloadMetaData2.close();
                    return downloadStatus6;
                }
                if (downloadMetaData2.getBytesDownloaded() < this.$minimumSize) {
                    downloadMetaData2.setType(DownloadType.IsDone);
                    DownloadObjects.DownloadStatus downloadStatus7 = VideoDownloadManager.DOWNLOAD_SUCCESS;
                    outputStream2 = (OutputStream) objectRef.element;
                    if (outputStream2 != null) {
                        SafeFileKt.closeQuietly(outputStream2);
                    }
                    downloadMetaData2.close();
                    return downloadStatus7;
                }
                SafeFileKt.closeQuietly((Closeable) objectRef.element);
                downloadMetaData2.onDelete();
                ((DownloadObjects.StreamData) objectRef4.element).delete();
                DownloadObjects.DownloadStatus downloadStatus8 = VideoDownloadManager.DOWNLOAD_INVALID_INPUT;
                outputStream3 = (OutputStream) objectRef.element;
                if (outputStream3 != null) {
                    SafeFileKt.closeQuietly(outputStream3);
                }
                downloadMetaData2.close();
                return downloadStatus8;
            } catch (Throwable th6) {
                OutputStream outputStream8 = (OutputStream) r4.element;
                if (outputStream8 != null) {
                    SafeFileKt.closeQuietly(outputStream8);
                }
                r3.close();
                throw th6;
            }
        }

        static final Unit invokeSuspend$lambda$1(List list) {
            DownloadUtils.INSTANCE.cancel$app(list);
            return Unit.INSTANCE;
        }
    }

    public final Object downloadThing(Context context, IDownloadableMinimum iDownloadableMinimum, String str, String str2, String str3, boolean z, Integer num, Function1<? super DownloadObjects.CreateNotificationMetadata, Unit> function1, int i, long j, Continuation<? super DownloadObjects.DownloadStatus> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C09612(i, iDownloadableMinimum, num, function1, context, str, str3, str2, z, j, null), continuation);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadHLS$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadStatus;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadHLS$2", f = "DownloadManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1321, 1439}, m = "invokeSuspend", n = {"$this$withContext", TtmlNode.TAG_METADATA, "fileStream", "extension", "baseFile", "basePath", "displayName", "stream", "m3u8", "startAt", "$this$withContext", TtmlNode.TAG_METADATA, "fileStream", "extension", "baseFile", "basePath", "displayName", "stream", "m3u8", "items", "currentMutex", "current", "fileMutex", "pendingData", "fileChecker", "jobs", "startAt"}, nl = {1323, 1440}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "I$0"}, v = 2)
    static final class C09592 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DownloadObjects.DownloadStatus>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Function1<DownloadObjects.CreateNotificationMetadata, Unit> $createNotificationCallback;
        final /* synthetic */ String $folder;
        final /* synthetic */ ExtractorLink $link;
        final /* synthetic */ String $name;
        final /* synthetic */ int $parallelConnections;
        final /* synthetic */ Integer $parentId;
        final /* synthetic */ Integer $startIndex;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C09592(int i, ExtractorLink extractorLink, Integer num, Function1<? super DownloadObjects.CreateNotificationMetadata, Unit> function1, Integer num2, Context context, String str, String str2, Continuation<? super C09592> continuation) {
            super(2, continuation);
            this.$parallelConnections = i;
            this.$link = extractorLink;
            this.$parentId = num;
            this.$createNotificationCallback = function1;
            this.$startIndex = num2;
            this.$context = context;
            this.$name = str;
            this.$folder = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C09592 c09592 = new C09592(this.$parallelConnections, this.$link, this.$parentId, this.$createNotificationCallback, this.$startIndex, this.$context, this.$name, this.$folder, continuation);
            c09592.L$0 = obj;
            return c09592;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super DownloadObjects.DownloadStatus> continuation) {
            return ((C09592) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:101:0x03a4 A[DONT_GENERATE] */
        /* JADX WARN: Code duplicated, block: B:55:0x0274 A[Catch: all -> 0x038a, TRY_LEAVE, TryCatch #5 {all -> 0x038a, blocks: (B:52:0x020d, B:53:0x026e, B:55:0x0274), top: B:118:0x020d }] */
        /* JADX WARN: Code duplicated, block: B:65:0x0315  */
        /* JADX WARN: Code duplicated, block: B:68:0x0328 A[Catch: all -> 0x0059, TryCatch #4 {all -> 0x0059, blocks: (B:7:0x0052, B:66:0x031c, B:68:0x0328, B:69:0x032d, B:71:0x0335, B:77:0x0348, B:79:0x0350, B:85:0x0370), top: B:117:0x0052 }] */
        /* JADX WARN: Code duplicated, block: B:71:0x0335 A[Catch: all -> 0x0059, TRY_LEAVE, TryCatch #4 {all -> 0x0059, blocks: (B:7:0x0052, B:66:0x031c, B:68:0x0328, B:69:0x032d, B:71:0x0335, B:77:0x0348, B:79:0x0350, B:85:0x0370), top: B:117:0x0052 }] */
        /* JADX WARN: Code duplicated, block: B:74:0x033f  */
        /* JADX WARN: Code duplicated, block: B:77:0x0348 A[Catch: all -> 0x0059, TRY_ENTER, TryCatch #4 {all -> 0x0059, blocks: (B:7:0x0052, B:66:0x031c, B:68:0x0328, B:69:0x032d, B:71:0x0335, B:77:0x0348, B:79:0x0350, B:85:0x0370), top: B:117:0x0052 }] */
        /* JADX WARN: Code duplicated, block: B:79:0x0350 A[Catch: all -> 0x0059, TRY_LEAVE, TryCatch #4 {all -> 0x0059, blocks: (B:7:0x0052, B:66:0x031c, B:68:0x0328, B:69:0x032d, B:71:0x0335, B:77:0x0348, B:79:0x0350, B:85:0x0370), top: B:117:0x0052 }] */
        /* JADX WARN: Code duplicated, block: B:82:0x0367  */
        /* JADX WARN: Code duplicated, block: B:85:0x0370 A[Catch: all -> 0x0059, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x0059, blocks: (B:7:0x0052, B:66:0x031c, B:68:0x0328, B:69:0x032d, B:71:0x0335, B:77:0x0348, B:79:0x0350, B:85:0x0370), top: B:117:0x0052 }] */
        /* JADX WARN: Code duplicated, block: B:88:0x037f  */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r29v2, types: [java.lang.Object, kotlin.collections.IntIterator] */
        /* JADX WARN: Type inference failed for: r6v6, types: [T, java.io.OutputStream] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.ObjectRef objectRef;
            DownloadMetaData downloadMetaData;
            String strComponent2;
            String displayName;
            M3u8Helper.M3u8Stream m3u8Stream;
            Object objHslLazy$default;
            String str;
            Ref.ObjectRef objectRef2;
            DownloadObjects.StreamData streamData;
            int i;
            DownloadMetaData downloadMetaData2;
            SafeFile safeFile;
            Ref.ObjectRef objectRef3;
            M3u8Helper2.LazyHlsDownloadData lazyHlsDownloadData;
            Mutex mutexMutex$default;
            ?? it;
            Mutex mutexMutex$default2;
            HashMap map;
            Job jobLaunch$default;
            ArrayList arrayList;
            Iterator<Integer> it2;
            final ArrayList arrayList2;
            DownloadObjects.StreamData streamData2;
            Job job;
            int i2;
            DownloadMetaData downloadMetaData3;
            OutputStream outputStream;
            OutputStream outputStream2;
            OutputStream outputStream3;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 != 0) {
                if (i3 == 1) {
                    int i4 = this.I$0;
                    M3u8Helper.M3u8Stream m3u8Stream2 = (M3u8Helper.M3u8Stream) this.L$8;
                    DownloadObjects.StreamData streamData3 = (DownloadObjects.StreamData) this.L$7;
                    String str2 = (String) this.L$6;
                    String str3 = (String) this.L$5;
                    safeFile = (SafeFile) this.L$4;
                    str = (String) this.L$3;
                    Ref.ObjectRef objectRef4 = (Ref.ObjectRef) this.L$2;
                    downloadMetaData2 = (DownloadMetaData) this.L$1;
                    try {
                        ResultKt.throwOnFailure(obj);
                        i = i4;
                        m3u8Stream = m3u8Stream2;
                        streamData = streamData3;
                        displayName = str2;
                        strComponent2 = str3;
                        objectRef2 = objectRef4;
                        objHslLazy$default = obj;
                        try {
                            lazyHlsDownloadData = (M3u8Helper2.LazyHlsDownloadData) objHslLazy$default;
                            downloadMetaData2.setHlsTotal(Boxing.boxInt(lazyHlsDownloadData.getSize()));
                            downloadMetaData2.setType(DownloadType.IsDownloading);
                            mutexMutex$default = MutexKt.Mutex$default(false, 1, null);
                            it = RangesKt.until(i, lazyHlsDownloadData.getSize()).iterator();
                            mutexMutex$default2 = MutexKt.Mutex$default(false, 1, null);
                            map = new HashMap();
                            jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), null, new VideoDownloadManager$downloadHLS$2$fileChecker$1(streamData, mutexMutex$default2, downloadMetaData2, null), 2, null);
                            IntRange intRangeUntil = RangesKt.until(0, this.$parallelConnections);
                            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
                            it2 = intRangeUntil.iterator();
                            while (it2.hasNext()) {
                                ((IntIterator) it2).nextInt();
                                Mutex mutex = mutexMutex$default2;
                                downloadMetaData3 = downloadMetaData2;
                                try {
                                    objectRef3 = objectRef2;
                                    try {
                                        arrayList.add(BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), null, new VideoDownloadManager$downloadHLS$2$jobs$1$1(mutex, mutexMutex$default, lazyHlsDownloadData, downloadMetaData3, it, objectRef2, map, null), 2, null));
                                        objectRef2 = objectRef3;
                                        mutexMutex$default2 = mutex;
                                    } catch (Throwable th) {
                                        th = th;
                                        objectRef = objectRef3;
                                        downloadMetaData = downloadMetaData2;
                                        ArchComponentExtKt.logError(th);
                                        downloadMetaData.setType(DownloadType.IsFailed);
                                        return downloadMetaData.failedStatus();
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    downloadMetaData2 = downloadMetaData3;
                                    objectRef3 = objectRef2;
                                    objectRef = objectRef3;
                                    downloadMetaData = downloadMetaData2;
                                    ArchComponentExtKt.logError(th);
                                    downloadMetaData.setType(DownloadType.IsFailed);
                                    return downloadMetaData.failedStatus();
                                }
                            }
                            Mutex mutex2 = mutexMutex$default2;
                            objectRef3 = objectRef2;
                            arrayList2 = arrayList;
                            downloadMetaData2.setOnStop(new Function0() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadHLS$2$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return VideoDownloadManager.C09592.invokeSuspend$lambda$1(arrayList2);
                                }
                            });
                            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                            this.L$1 = downloadMetaData2;
                            this.L$2 = objectRef3;
                            this.L$3 = SpillingKt.nullOutSpilledVariable(str);
                            this.L$4 = SpillingKt.nullOutSpilledVariable(safeFile);
                            this.L$5 = SpillingKt.nullOutSpilledVariable(strComponent2);
                            this.L$6 = SpillingKt.nullOutSpilledVariable(displayName);
                            this.L$7 = streamData;
                            this.L$8 = SpillingKt.nullOutSpilledVariable(m3u8Stream);
                            this.L$9 = SpillingKt.nullOutSpilledVariable(lazyHlsDownloadData);
                            this.L$10 = SpillingKt.nullOutSpilledVariable(mutexMutex$default);
                            this.L$11 = SpillingKt.nullOutSpilledVariable(it);
                            this.L$12 = SpillingKt.nullOutSpilledVariable(mutex2);
                            this.L$13 = SpillingKt.nullOutSpilledVariable(map);
                            this.L$14 = jobLaunch$default;
                            this.L$15 = SpillingKt.nullOutSpilledVariable(arrayList2);
                            this.I$0 = i;
                            this.label = 2;
                            if (DownloadUtils.INSTANCE.join$app(arrayList2, this) != coroutine_suspended) {
                                streamData2 = streamData;
                                objectRef = objectRef3;
                                job = jobLaunch$default;
                                downloadMetaData = downloadMetaData2;
                                i2 = 1;
                            }
                            return coroutine_suspended;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        objectRef = objectRef4;
                        downloadMetaData = downloadMetaData2;
                        ArchComponentExtKt.logError(th);
                        downloadMetaData.setType(DownloadType.IsFailed);
                        return downloadMetaData.failedStatus();
                    }
                } else {
                    if (i3 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    job = (Job) this.L$14;
                    streamData2 = (DownloadObjects.StreamData) this.L$7;
                    objectRef = (Ref.ObjectRef) this.L$2;
                    downloadMetaData = (DownloadMetaData) this.L$1;
                    try {
                        ResultKt.throwOnFailure(obj);
                        i2 = 1;
                    } catch (Throwable th5) {
                        th = th5;
                    }
                }
                Job.CC.cancel$default(job, (CancellationException) null, i2, (Object) null);
                downloadMetaData.removeStopListener();
                if (!streamData2.getExists()) {
                    downloadMetaData.setType(DownloadType.IsStopped);
                }
                if (downloadMetaData.getType() == DownloadType.IsFailed) {
                    DownloadObjects.DownloadStatus downloadStatusFailedStatus = downloadMetaData.failedStatus();
                    outputStream3 = (OutputStream) objectRef.element;
                    if (outputStream3 != null) {
                        SafeFileKt.closeQuietly(outputStream3);
                    }
                    downloadMetaData.close();
                    return downloadStatusFailedStatus;
                }
                if (downloadMetaData.getType() == DownloadType.IsStopped) {
                    downloadMetaData.setType(DownloadType.IsDone);
                    DownloadObjects.DownloadStatus downloadStatus = VideoDownloadManager.DOWNLOAD_SUCCESS;
                    outputStream = (OutputStream) objectRef.element;
                    if (outputStream != null) {
                        SafeFileKt.closeQuietly(outputStream);
                    }
                    downloadMetaData.close();
                    return downloadStatus;
                }
                SafeFileKt.closeQuietly((Closeable) objectRef.element);
                downloadMetaData.onDelete();
                streamData2.delete();
                DownloadObjects.DownloadStatus downloadStatus2 = VideoDownloadManager.DOWNLOAD_STOPPED;
                outputStream2 = (OutputStream) objectRef.element;
                if (outputStream2 != null) {
                    SafeFileKt.closeQuietly(outputStream2);
                }
                downloadMetaData.close();
                return downloadStatus2;
            }
            ResultKt.throwOnFailure(obj);
            if (this.$parallelConnections < 1) {
                return VideoDownloadManager.DOWNLOAD_INVALID_INPUT;
            }
            DownloadMetaData downloadMetaData4 = new DownloadMetaData(this.$parentId, this.$link.getUrl().hashCode(), 0L, 0L, null, 0L, 0L, this.$createNotificationCallback, null, true, 0, null, 0, null, 15740, null);
            objectRef = new Ref.ObjectRef();
            try {
                Integer num = this.$startIndex;
                int iIntValue = num != null ? num.intValue() : 0;
                Pair<SafeFile, String> basePath$app = DownloadFileManagement.INSTANCE.getBasePath$app(this.$context);
                SafeFile safeFileComponent1 = basePath$app.component1();
                strComponent2 = basePath$app.component2();
                if (safeFileComponent1 == null) {
                    DownloadObjects.DownloadStatus downloadStatus3 = VideoDownloadManager.DOWNLOAD_BAD_CONFIG;
                    OutputStream outputStream4 = (OutputStream) objectRef.element;
                    if (outputStream4 != null) {
                        SafeFileKt.closeQuietly(outputStream4);
                    }
                    downloadMetaData4.close();
                    return downloadStatus3;
                }
                displayName = VideoDownloadManager.INSTANCE.getDisplayName(this.$name, "mp4");
                DownloadObjects.StreamData streamData4 = VideoDownloadManager.INSTANCE.setupStream(safeFileComponent1, this.$name, this.$folder, "mp4", iIntValue > 0);
                if (!streamData4.getResume()) {
                    iIntValue = 0;
                }
                objectRef.element = streamData4.open();
                downloadMetaData4.setResumeLength(streamData4.getStartAt());
                downloadMetaData4.setHlsProgress(iIntValue);
                downloadMetaData4.setHlsWrittenProgress(iIntValue);
                downloadMetaData4.setType(DownloadType.IsPending);
                downloadMetaData4.setDownloadFileInfoTemplate(new DownloadObjects.DownloadedFileInfo(0L, this.$folder, displayName, null, strComponent2, null, 40, null));
                M3u8Helper.M3u8Stream m3u8Stream3 = new M3u8Helper.M3u8Stream(this.$link.getUrl(), Boxing.boxInt(this.$link.getQuality()), DownloadUtils.INSTANCE.appendAndDontOverride$app(this.$link.getHeaders(), MapsKt.plus(MapsKt.mapOf(TuplesKt.to("user-agent", VideoDownloadManager.USER_AGENT)), !StringsKt.isBlank(this.$link.getReferer()) ? MapsKt.mapOf(TuplesKt.to("referer", this.$link.getReferer())) : MapsKt.emptyMap())));
                this.L$0 = coroutineScope;
                this.L$1 = downloadMetaData4;
                this.L$2 = objectRef;
                this.L$3 = SpillingKt.nullOutSpilledVariable(r13);
                this.L$4 = SpillingKt.nullOutSpilledVariable(safeFileComponent1);
                this.L$5 = SpillingKt.nullOutSpilledVariable(strComponent2);
                this.L$6 = SpillingKt.nullOutSpilledVariable(displayName);
                this.L$7 = streamData4;
                this.L$8 = SpillingKt.nullOutSpilledVariable(m3u8Stream3);
                this.I$0 = iIntValue;
                this.label = 1;
                m3u8Stream = m3u8Stream3;
                objHslLazy$default = M3u8Helper2.hslLazy$default(M3u8Helper2.INSTANCE, m3u8Stream, true, true, 0, this, 8, null);
                if (objHslLazy$default != coroutine_suspended) {
                    str = "mp4";
                    objectRef2 = objectRef;
                    streamData = streamData4;
                    i = iIntValue;
                    downloadMetaData2 = downloadMetaData4;
                    safeFile = safeFileComponent1;
                    lazyHlsDownloadData = (M3u8Helper2.LazyHlsDownloadData) objHslLazy$default;
                    downloadMetaData2.setHlsTotal(Boxing.boxInt(lazyHlsDownloadData.getSize()));
                    downloadMetaData2.setType(DownloadType.IsDownloading);
                    mutexMutex$default = MutexKt.Mutex$default(false, 1, null);
                    it = RangesKt.until(i, lazyHlsDownloadData.getSize()).iterator();
                    mutexMutex$default2 = MutexKt.Mutex$default(false, 1, null);
                    map = new HashMap();
                    jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), null, new VideoDownloadManager$downloadHLS$2$fileChecker$1(streamData, mutexMutex$default2, downloadMetaData2, null), 2, null);
                    IntRange intRangeUntil2 = RangesKt.until(0, this.$parallelConnections);
                    arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil2, 10));
                    it2 = intRangeUntil2.iterator();
                    while (it2.hasNext()) {
                        ((IntIterator) it2).nextInt();
                        Mutex mutex3 = mutexMutex$default2;
                        downloadMetaData3 = downloadMetaData2;
                        objectRef3 = objectRef2;
                        arrayList.add(BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), null, new VideoDownloadManager$downloadHLS$2$jobs$1$1(mutex3, mutexMutex$default, lazyHlsDownloadData, downloadMetaData3, it, objectRef2, map, null), 2, null));
                        objectRef2 = objectRef3;
                        mutexMutex$default2 = mutex3;
                    }
                    Mutex mutex4 = mutexMutex$default2;
                    objectRef3 = objectRef2;
                    arrayList2 = arrayList;
                    downloadMetaData2.setOnStop(new Function0() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadHLS$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return VideoDownloadManager.C09592.invokeSuspend$lambda$1(arrayList2);
                        }
                    });
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = downloadMetaData2;
                    this.L$2 = objectRef3;
                    this.L$3 = SpillingKt.nullOutSpilledVariable(str);
                    this.L$4 = SpillingKt.nullOutSpilledVariable(safeFile);
                    this.L$5 = SpillingKt.nullOutSpilledVariable(strComponent2);
                    this.L$6 = SpillingKt.nullOutSpilledVariable(displayName);
                    this.L$7 = streamData;
                    this.L$8 = SpillingKt.nullOutSpilledVariable(m3u8Stream);
                    this.L$9 = SpillingKt.nullOutSpilledVariable(lazyHlsDownloadData);
                    this.L$10 = SpillingKt.nullOutSpilledVariable(mutexMutex$default);
                    this.L$11 = SpillingKt.nullOutSpilledVariable(it);
                    this.L$12 = SpillingKt.nullOutSpilledVariable(mutex4);
                    this.L$13 = SpillingKt.nullOutSpilledVariable(map);
                    this.L$14 = jobLaunch$default;
                    this.L$15 = SpillingKt.nullOutSpilledVariable(arrayList2);
                    this.I$0 = i;
                    this.label = 2;
                    if (DownloadUtils.INSTANCE.join$app(arrayList2, this) != coroutine_suspended) {
                        streamData2 = streamData;
                        objectRef = objectRef3;
                        job = jobLaunch$default;
                        downloadMetaData = downloadMetaData2;
                        i2 = 1;
                        Job.CC.cancel$default(job, (CancellationException) null, i2, (Object) null);
                        downloadMetaData.removeStopListener();
                        if (!streamData2.getExists()) {
                            downloadMetaData.setType(DownloadType.IsStopped);
                        }
                        if (downloadMetaData.getType() == DownloadType.IsFailed) {
                            DownloadObjects.DownloadStatus downloadStatusFailedStatus2 = downloadMetaData.failedStatus();
                            outputStream3 = (OutputStream) objectRef.element;
                            if (outputStream3 != null) {
                                SafeFileKt.closeQuietly(outputStream3);
                            }
                            downloadMetaData.close();
                            return downloadStatusFailedStatus2;
                        }
                        if (downloadMetaData.getType() == DownloadType.IsStopped) {
                            downloadMetaData.setType(DownloadType.IsDone);
                            DownloadObjects.DownloadStatus downloadStatus4 = VideoDownloadManager.DOWNLOAD_SUCCESS;
                            outputStream = (OutputStream) objectRef.element;
                            if (outputStream != null) {
                                SafeFileKt.closeQuietly(outputStream);
                            }
                            downloadMetaData.close();
                            return downloadStatus4;
                        }
                        SafeFileKt.closeQuietly((Closeable) objectRef.element);
                        downloadMetaData.onDelete();
                        streamData2.delete();
                        DownloadObjects.DownloadStatus downloadStatus5 = VideoDownloadManager.DOWNLOAD_STOPPED;
                        outputStream2 = (OutputStream) objectRef.element;
                        if (outputStream2 != null) {
                            SafeFileKt.closeQuietly(outputStream2);
                        }
                        downloadMetaData.close();
                        return downloadStatus5;
                    }
                }
                return coroutine_suspended;
            } catch (Throwable th6) {
                th = th6;
                downloadMetaData = downloadMetaData4;
            }
            try {
                ArchComponentExtKt.logError(th);
                downloadMetaData.setType(DownloadType.IsFailed);
                return downloadMetaData.failedStatus();
            } finally {
                OutputStream outputStream5 = (OutputStream) objectRef.element;
                if (outputStream5 != null) {
                    SafeFileKt.closeQuietly(outputStream5);
                }
                downloadMetaData.close();
            }
        }

        static final Unit invokeSuspend$lambda$1(List list) {
            DownloadUtils.INSTANCE.cancel$app(list);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object downloadHLS(Context context, ExtractorLink extractorLink, String str, String str2, Integer num, Integer num2, Function1<? super DownloadObjects.CreateNotificationMetadata, Unit> function1, int i, Continuation<? super DownloadObjects.DownloadStatus> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C09592(i, extractorLink, num, function1, num2, context, str, str2, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getDisplayName(String name, String extension) {
        return name + '.' + extension;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001e  */
    /* JADX WARN: Code duplicated, block: B:89:0x0213  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0, types: [com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v15, types: [kotlinx.coroutines.Job] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    public final Object downloadSingleEpisode(final Context context, final String str, String str2, final DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata, final ExtractorLink extractorLink, final Function2<? super Integer, ? super Notification, Unit> function2, boolean z, Continuation<? super DownloadObjects.DownloadStatus> continuation) {
        C09601 c09601;
        Job job;
        Job job2;
        Job job3;
        Job job4;
        Integer intOrNull;
        Object objDecodeFromString;
        String extraInfo;
        Object objM8936constructorimpl;
        ?? r1 = this;
        if (continuation instanceof C09601) {
            c09601 = (C09601) continuation;
            if ((c09601.label & Integer.MIN_VALUE) != 0) {
                c09601.label -= Integer.MIN_VALUE;
            } else {
                c09601 = new C09601(continuation);
            }
        } else {
            c09601 = new C09601(continuation);
        }
        C09601 c09602 = c09601;
        Object objDownloadHLS = c09602.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c09602.label;
        CancellationException cancellationException = null;
        if (i == 0) {
            ResultKt.throwOnFailure(objDownloadHLS);
            if (extractorLink.getType() == ExtractorLinkType.MAGNET || extractorLink.getType() == ExtractorLinkType.TORRENT || extractorLink.getType() == ExtractorLinkType.DASH) {
                return DOWNLOAD_INVALID_INPUT;
            }
            String fileName$app = DownloadFileManagement.INSTANCE.getFileName$app(context, downloadEpisodeMetadata);
            Job jobIoSafe = Coroutines.INSTANCE.ioSafe(r1, new VideoDownloadManager$downloadSingleEpisode$extractorJob$1(extractorLink, null));
            Function1 function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VideoDownloadManager.downloadSingleEpisode$lambda$0(context, str, extractorLink, downloadEpisodeMetadata, function2, (DownloadObjects.CreateNotificationMetadata) obj);
                }
            };
            try {
                int i2 = WhenMappings.$EnumSwitchMapping$2[extractorLink.getType().ordinal()];
                try {
                    if (i2 == 1) {
                        if (z) {
                            DataStore dataStore = DataStore.INSTANCE;
                            try {
                                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(KEY_DOWNLOAD_INFO, String.valueOf(downloadEpisodeMetadata.getId())), null);
                                if (string == null) {
                                    objDecodeFromString = null;
                                } else {
                                    AppUtils appUtils = AppUtils.INSTANCE;
                                    try {
                                        Result.Companion companion = Result.INSTANCE;
                                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadedFileInfo"));
                                    } catch (Throwable th) {
                                        Result.Companion companion2 = Result.INSTANCE;
                                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                                    }
                                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                                        try {
                                            Result.Companion companion3 = Result.INSTANCE;
                                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadedFileInfo.class), null, 2, null));
                                        } catch (Throwable th2) {
                                            Result.Companion companion4 = Result.INSTANCE;
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
                                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadSingleEpisode$$inlined$getKey$1
                                            });
                                        } catch (Throwable unused) {
                                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadSingleEpisode$$inlined$getKey$1
                                            });
                                        }
                                    } else {
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$downloadSingleEpisode$$inlined$getKey$1
                                        });
                                    }
                                }
                            } catch (Exception unused2) {
                            }
                            if (objDecodeFromString == null) {
                                objDecodeFromString = null;
                            }
                            DownloadObjects.DownloadedFileInfo downloadedFileInfo = (DownloadObjects.DownloadedFileInfo) objDecodeFromString;
                            if (downloadedFileInfo == null || (extraInfo = downloadedFileInfo.getExtraInfo()) == null) {
                                intOrNull = null;
                            } else {
                                intOrNull = StringsKt.toIntOrNull(extraInfo);
                            }
                        } else {
                            intOrNull = null;
                        }
                        String str3 = str2 == null ? "" : str2;
                        try {
                            Integer numBoxInt = Boxing.boxInt(downloadEpisodeMetadata.getId());
                            int iMaxConcurrentConnections = maxConcurrentConnections(context);
                            c09602.L$0 = SpillingKt.nullOutSpilledVariable(context);
                            c09602.L$1 = SpillingKt.nullOutSpilledVariable(str);
                            c09602.L$2 = SpillingKt.nullOutSpilledVariable(str2);
                            c09602.L$3 = SpillingKt.nullOutSpilledVariable(downloadEpisodeMetadata);
                            c09602.L$4 = SpillingKt.nullOutSpilledVariable(extractorLink);
                            c09602.L$5 = SpillingKt.nullOutSpilledVariable(function2);
                            c09602.L$6 = SpillingKt.nullOutSpilledVariable(fileName$app);
                            job3 = jobIoSafe;
                            try {
                                c09602.L$7 = job3;
                                c09602.L$8 = SpillingKt.nullOutSpilledVariable(function1);
                                c09602.L$9 = SpillingKt.nullOutSpilledVariable(intOrNull);
                                c09602.Z$0 = z;
                                c09602.label = 1;
                                objDownloadHLS = downloadHLS(context, extractorLink, fileName$app, str3, numBoxInt, intOrNull, function1, iMaxConcurrentConnections, c09602);
                                if (objDownloadHLS != coroutine_suspended) {
                                    job = job3;
                                    Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
                                    return objDownloadHLS;
                                }
                            } catch (Throwable unused3) {
                                r1 = job3;
                                return DOWNLOAD_FAILED;
                            }
                        } catch (Throwable unused4) {
                            job3 = jobIoSafe;
                        }
                    } else if (i2 == 2) {
                        try {
                            ExtractorLink extractorLink2 = extractorLink;
                            String str4 = str2 == null ? "" : str2;
                            Integer numBoxInt2 = Boxing.boxInt(downloadEpisodeMetadata.getId());
                            int iMaxConcurrentConnections2 = maxConcurrentConnections(context);
                            c09602.L$0 = SpillingKt.nullOutSpilledVariable(context);
                            c09602.L$1 = SpillingKt.nullOutSpilledVariable(str);
                            c09602.L$2 = SpillingKt.nullOutSpilledVariable(str2);
                            c09602.L$3 = SpillingKt.nullOutSpilledVariable(downloadEpisodeMetadata);
                            c09602.L$4 = SpillingKt.nullOutSpilledVariable(extractorLink);
                            c09602.L$5 = SpillingKt.nullOutSpilledVariable(function2);
                            c09602.L$6 = SpillingKt.nullOutSpilledVariable(fileName$app);
                            c09602.L$7 = jobIoSafe;
                            c09602.L$8 = SpillingKt.nullOutSpilledVariable(function1);
                            c09602.Z$0 = z;
                            c09602.label = 2;
                            job4 = jobIoSafe;
                            try {
                                objDownloadHLS = r1.downloadThing(context, extractorLink2, fileName$app, str4, "mp4", z, numBoxInt2, function1, iMaxConcurrentConnections2, 10485760L, c09602);
                                if (objDownloadHLS != coroutine_suspended) {
                                    job2 = job4;
                                    cancellationException = null;
                                    Job.CC.cancel$default(job2, cancellationException, 1, (Object) cancellationException);
                                    return objDownloadHLS;
                                }
                            } catch (Throwable unused5) {
                                cancellationException = null;
                                r1 = job4;
                                return DOWNLOAD_FAILED;
                            }
                        } catch (Throwable unused6) {
                            job4 = jobIoSafe;
                        }
                    } else {
                        throw new IllegalArgumentException("Unsupported download type");
                    }
                    return coroutine_suspended;
                } catch (Throwable unused7) {
                }
            } catch (Throwable unused8) {
                job3 = jobIoSafe;
            }
        } else {
            try {
                if (i == 1) {
                    boolean z2 = c09602.Z$0;
                    job = (Job) c09602.L$7;
                    ResultKt.throwOnFailure(objDownloadHLS);
                    Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    return objDownloadHLS;
                }
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                boolean z3 = c09602.Z$0;
                job2 = (Job) c09602.L$7;
                ResultKt.throwOnFailure(objDownloadHLS);
                Job.CC.cancel$default(job2, cancellationException, 1, (Object) cancellationException);
                return objDownloadHLS;
            } catch (Throwable unused9) {
                try {
                    return DOWNLOAD_FAILED;
                } finally {
                    Job.CC.cancel$default((Job) r1, cancellationException, 1, (Object) cancellationException);
                }
            }
        }
    }

    static final Unit downloadSingleEpisode$lambda$0(Context context, String str, ExtractorLink extractorLink, DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata, Function2 function2, DownloadObjects.CreateNotificationMetadata meta) {
        Intrinsics.checkNotNullParameter(meta, "meta");
        Coroutines.INSTANCE.main(INSTANCE, new VideoDownloadManager$downloadSingleEpisode$callback$1$1(context, str, extractorLink, downloadEpisodeMetadata, meta, function2, null));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x006a  */
    /* JADX WARN: Code duplicated, block: B:28:0x0080 A[Catch: Exception -> 0x0092, TRY_LEAVE, TryCatch #5 {Exception -> 0x0092, blocks: (B:4:0x0012, B:7:0x001e, B:12:0x0038, B:19:0x0064, B:22:0x006b, B:28:0x0080, B:27:0x007b, B:18:0x005a, B:11:0x002e, B:15:0x003f, B:8:0x0020, B:24:0x006f), top: B:53:0x0012, inners: #2, #3, #6 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public final DownloadObjects.DownloadedFileInfoResult getDownloadFileInfo(Context context, int id) {
        Object objDecodeFromString;
        SafeFile file$app;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(KEY_DOWNLOAD_INFO, String.valueOf(id)), null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadedFileInfo"));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$getDownloadFileInfo$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$getDownloadFileInfo$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$getDownloadFileInfo$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadedFileInfo.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$getDownloadFileInfo$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
                objDecodeFromString = null;
            }
            DownloadObjects.DownloadedFileInfo downloadedFileInfo = (DownloadObjects.DownloadedFileInfo) objDecodeFromString;
            if (downloadedFileInfo != null && (file$app = DownloadFileManagement.INSTANCE.toFile$app(downloadedFileInfo, context)) != null && !Intrinsics.areEqual((Object) file$app.exists(), (Object) false)) {
                return new DownloadObjects.DownloadedFileInfoResult(file$app.lengthOrThrow(), downloadedFileInfo.getTotalBytes(), file$app.uriOrThrow());
            }
            return null;
        } catch (Exception e2) {
            ArchComponentExtKt.logError(e2);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void deleteFilesAndUpdateSettings$default(VideoDownloadManager videoDownloadManager, Context context, Set set, CoroutineScope coroutineScope, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return VideoDownloadManager.deleteFilesAndUpdateSettings$lambda$0((Set) obj2);
                }
            };
        }
        videoDownloadManager.deleteFilesAndUpdateSettings(context, set, coroutineScope, function1);
    }

    static final Unit deleteFilesAndUpdateSettings$lambda$0(Set it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$deleteFilesAndUpdateSettings$2, reason: invalid class name */
    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$deleteFilesAndUpdateSettings$2", f = "DownloadManager.kt", i = {0, 0}, l = {1600}, m = "invokeSuspend", n = {"$this$launchSafe", "deleteJobs"}, nl = {1602}, s = {"L$0", "L$1"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Set<Integer> $ids;
        final /* synthetic */ Function1<Set<Integer>, Unit> $onComplete;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Set<Integer> set, Function1<? super Set<Integer>, Unit> function1, Context context, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$ids = set;
            this.$onComplete = function1;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$ids, this.$onComplete, this.$context, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Set<Integer> set = this.$ids;
                Context context = this.$context;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
                Iterator<T> it = set.iterator();
                while (it.hasNext()) {
                    arrayList.add(BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new VideoDownloadManager$deleteFilesAndUpdateSettings$2$deleteJobs$1$1(((Number) it.next()).intValue(), context, null), 3, null));
                }
                ArrayList arrayList2 = arrayList;
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(arrayList2);
                this.label = 1;
                obj = AwaitKt.awaitAll(arrayList2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            for (Object obj2 : (List) obj) {
                if (((Boolean) ((Pair) obj2).getSecond()).booleanValue()) {
                    arrayList3.add(obj2);
                } else {
                    arrayList4.add(obj2);
                }
            }
            Pair pair = new Pair(arrayList3, arrayList4);
            List list = (List) pair.component1();
            List list2 = (List) pair.component2();
            List list3 = list;
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            Iterator it2 = list3.iterator();
            while (it2.hasNext()) {
                arrayList5.add(Boxing.boxInt(((Number) ((Pair) it2.next()).getFirst()).intValue()));
            }
            Set<Integer> set2 = CollectionsKt.toSet(arrayList5);
            if (list2.isEmpty()) {
                Boxing.boxInt(Log.i("FileDeletion", "All files deleted successfully"));
            } else {
                Iterator it3 = list2.iterator();
                while (it3.hasNext()) {
                    Log.e("FileDeletion", "Failed to delete file with ID: " + ((Number) ((Pair) it3.next()).component1()).intValue());
                }
            }
            this.$onComplete.invoke(set2);
            return Unit.INSTANCE;
        }
    }

    public final void deleteFilesAndUpdateSettings(Context context, Set<Integer> ids, CoroutineScope scope, Function1<? super Set<Integer>, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ids, "ids");
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        ArchComponentExtKt.launchSafe$default(scope, Dispatchers.getIO(), null, new AnonymousClass2(ids, onComplete, context, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean deleteFileAndUpdateSettings(Context context, int id) {
        boolean zDeleteFile = deleteFile(context, id);
        if (zDeleteFile) {
            DataStore.INSTANCE.removeKey(context, KEY_DOWNLOAD_INFO, String.valueOf(id));
        }
        return zDeleteFile;
    }

    /* JADX WARN: Code duplicated, block: B:41:0x00bf  */
    private final boolean deleteFile(Context context, int id) {
        boolean z;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        DataStore dataStore = DataStore.INSTANCE;
        Object obj = null;
        try {
            String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(KEY_DOWNLOAD_INFO, String.valueOf(id)), null);
            if (string != null) {
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadedFileInfo"));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadedFileInfo.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
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
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$deleteFile$$inlined$getKey$1
                        });
                    } catch (Throwable unused) {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$deleteFile$$inlined$getKey$1
                        });
                    }
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$deleteFile$$inlined$getKey$1
                    });
                }
                obj = objDecodeFromString;
            }
        } catch (Exception unused2) {
        }
        DownloadObjects.DownloadedFileInfo downloadedFileInfo = (DownloadObjects.DownloadedFileInfo) obj;
        if (downloadedFileInfo == null) {
            return false;
        }
        SafeFile file$app = DownloadFileManagement.INSTANCE.toFile$app(downloadedFileInfo, context);
        if (!(file$app != null ? Intrinsics.areEqual((Object) file$app.delete(), (Object) true) : false)) {
            z = file$app != null ? Intrinsics.areEqual((Object) file$app.exists(), (Object) false) : false;
        }
        if (z) {
            SubtitleUtils.INSTANCE.deleteMatchingSubtitles(context, downloadedFileInfo);
            downloadEvent.invoke(TuplesKt.to(Integer.valueOf(id), DownloadActionType.Stop));
            downloadProgressEvent.invoke(new Triple<>(Integer.valueOf(id), 0L, 0L));
            downloadStatusEvent.invoke(TuplesKt.to(Integer.valueOf(id), DownloadType.IsStopped));
            downloadDeleteEvent.invoke(Integer.valueOf(id));
        }
        return z;
    }

    public final DownloadObjects.DownloadResumePackage getDownloadResumePackage(Context context, int id) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(context, "context");
        DataStore dataStore = DataStore.INSTANCE;
        Object obj = null;
        try {
            String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(KEY_RESUME_PACKAGES, String.valueOf(id)), null);
            if (string != null) {
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadResumePackage"));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadResumePackage.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
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
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadResumePackage>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$getDownloadResumePackage$$inlined$getKey$1
                        });
                    } catch (Throwable unused) {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadResumePackage>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$getDownloadResumePackage$$inlined$getKey$1
                        });
                    }
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadResumePackage>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$getDownloadResumePackage$$inlined$getKey$1
                    });
                }
                obj = objDecodeFromString;
            }
        } catch (Exception unused2) {
        }
        return (DownloadObjects.DownloadResumePackage) obj;
    }

    public final DownloadObjects.DownloadQueueWrapper getDownloadQueuePackage(Context context, int id) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Intrinsics.checkNotNullParameter(context, "context");
        DataStore dataStore = DataStore.INSTANCE;
        Object obj = null;
        try {
            String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(KEY_RESUME_IN_QUEUE, String.valueOf(id)), null);
            if (string != null) {
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadQueueWrapper"));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadQueueWrapper.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
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
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadQueueWrapper>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$getDownloadQueuePackage$$inlined$getKey$1
                        });
                    } catch (Throwable unused) {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadQueueWrapper>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$getDownloadQueuePackage$$inlined$getKey$1
                        });
                    }
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadQueueWrapper>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$getDownloadQueuePackage$$inlined$getKey$1
                    });
                }
                obj = objDecodeFromString;
            }
        } catch (Exception unused2) {
        }
        return (DownloadObjects.DownloadQueueWrapper) obj;
    }

    public final DownloadObjects.DownloadEpisodeMetadata getDownloadEpisodeMetadata(ResultEpisode episode, String titleName, String apiName, String currentPoster, boolean currentIsMovie, TvType tvType) {
        Intrinsics.checkNotNullParameter(episode, "episode");
        Intrinsics.checkNotNullParameter(titleName, "titleName");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(tvType, "tvType");
        int id = episode.getId();
        int parentId = episode.getParentId();
        String strSanitizeFilename$app$default = DownloadFileManagement.sanitizeFilename$app$default(DownloadFileManagement.INSTANCE, titleName, false, 2, null);
        String poster = episode.getPoster();
        if (poster == null) {
            poster = currentPoster;
        }
        return new DownloadObjects.DownloadEpisodeMetadata(id, parentId, strSanitizeFilename$app$default, apiName, poster, episode.getName(), currentIsMovie ? null : episode.getSeason(), currentIsMovie ? null : Integer.valueOf(episode.getEpisode()), tvType);
    }

    /* JADX INFO: compiled from: DownloadManager.kt */
    @Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 62\u00020\u0001:\u00016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J0\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020#2\u0018\u0010$\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u00190%H\u0082@¢\u0006\u0002\u0010(J\"\u0010)\u001a\u00020\u00192\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010#H\u0082@¢\u0006\u0002\u0010-J\u000e\u0010!\u001a\u00020\u0019H\u0082@¢\u0006\u0002\u0010.J\u0006\u0010)\u001a\u00020\u0019J&\u0010/\u001a\u00020\u00192\f\u00100\u001a\b\u0012\u0004\u0012\u000202012\u000e\u00103\u001a\n\u0012\u0004\u0012\u000204\u0018\u000101H\u0002J\u000e\u00105\u001a\u00020\u0019H\u0082@¢\u0006\u0002\u0010.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0015\"\u0004\b\u001e\u0010\u0017R$\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017¨\u00067"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance;", "", "context", "Landroid/content/Context;", "downloadQueueWrapper", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "<init>", "(Landroid/content/Context;Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;)V", "getContext", "()Landroid/content/Context;", "getDownloadQueueWrapper", "()Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadQueueWrapper;", "TAG", "", "subtitleDownloadJob", "Lkotlinx/coroutines/Job;", "downloadJob", "linkLoadingJob", ES6Iterator.VALUE_PROPERTY, "", "isCompleted", "()Z", "setCompleted", "(Z)V", "cancelDownload", "", "cleanup", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$DownloadType;", "isCancelled", "setCancelled", "isFailed", "setFailed", "downloadFromResume", "downloadResumePackage", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadResumePackage;", "notificationCallback", "Lkotlin/Function2;", "", "Landroid/app/Notification;", "(Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadResumePackage;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startDownload", "info", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadItem;", "pkg", "(Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadItem;Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadResumePackage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadEpisodeWithLinks", CloudStreamPackage.LINKS_EXTRA, "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", CloudStreamPackage.SUBTITLE_EXTRA, "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "downloadEpisodeWithoutLinks", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class EpisodeDownloadInstance {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String TAG;
        private final Context context;
        private Job downloadJob;
        private final DownloadObjects.DownloadQueueWrapper downloadQueueWrapper;
        private boolean isCancelled;
        private boolean isCompleted;
        private boolean isFailed;
        private Job linkLoadingJob;
        private Job subtitleDownloadJob;

        public EpisodeDownloadInstance(Context context, DownloadObjects.DownloadQueueWrapper downloadQueueWrapper) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(downloadQueueWrapper, "downloadQueueWrapper");
            this.context = context;
            this.downloadQueueWrapper = downloadQueueWrapper;
            this.TAG = "EpisodeDownloadInstance";
        }

        public final Context getContext() {
            return this.context;
        }

        public final DownloadObjects.DownloadQueueWrapper getDownloadQueueWrapper() {
            return this.downloadQueueWrapper;
        }

        /* JADX INFO: renamed from: isCompleted, reason: from getter */
        public final boolean getIsCompleted() {
            return this.isCompleted;
        }

        public final void setCompleted(boolean z) {
            this.isCompleted = z;
            if (z) {
                CloudStreamApp.INSTANCE.removeKey(VideoDownloadManager.KEY_RESUME_IN_QUEUE, String.valueOf(this.downloadQueueWrapper.getId()));
                DownloadQueueManager.INSTANCE.forceRefreshQueue();
            }
        }

        public final void cancelDownload() {
            Job job = this.subtitleDownloadJob;
            if (job != null) {
                JobKt__JobKt.cancel$default(job, "Cancel call from cancelDownload", null, 2, null);
            }
            Job job2 = this.linkLoadingJob;
            if (job2 != null) {
                JobKt__JobKt.cancel$default(job2, "Cancel call from cancelDownload", null, 2, null);
            }
            setCancelled(true);
        }

        private final void cleanup(DownloadType status) {
            CloudStreamApp.INSTANCE.removeKey(VideoDownloadManager.KEY_RESUME_IN_QUEUE, String.valueOf(this.downloadQueueWrapper.getId()));
            final int id = this.downloadQueueWrapper.getId();
            ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return VideoDownloadManager.EpisodeDownloadInstance.cleanup$lambda$0(this.f$0, id);
                }
            });
            VideoDownloadManager.INSTANCE.getDownloadStatusEvent().invoke(new Pair<>(Integer.valueOf(id), status));
            VideoDownloadManager.INSTANCE.getDownloadStatus().put(Integer.valueOf(id), status);
            VideoDownloadManager.INSTANCE.getDownloadEvent().invoke(new Pair<>(Integer.valueOf(id), DownloadActionType.Stop));
            DownloadQueueManager.INSTANCE.forceRefreshQueue();
        }

        static final Unit cleanup$lambda$0(EpisodeDownloadInstance episodeDownloadInstance, int i) {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            DataStore dataStore = DataStore.INSTANCE;
            Object obj = null;
            try {
                String string = dataStore.getSharedPrefs(episodeDownloadInstance.context).getString(dataStore.getFolderName(VideoDownloadManager.KEY_DOWNLOAD_INFO, String.valueOf(i)), null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadedFileInfo"));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(DownloadObjects.DownloadedFileInfo.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
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
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$cleanup$lambda$0$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$cleanup$lambda$0$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<DownloadObjects.DownloadedFileInfo>() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$cleanup$lambda$0$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
            DownloadObjects.DownloadedFileInfo downloadedFileInfo = (DownloadObjects.DownloadedFileInfo) obj;
            if (downloadedFileInfo != null) {
                SubtitleUtils.INSTANCE.deleteMatchingSubtitles(episodeDownloadInstance.context, downloadedFileInfo);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: isCancelled, reason: from getter */
        public final boolean getIsCancelled() {
            return this.isCancelled;
        }

        public final void setCancelled(boolean z) {
            boolean z2 = this.isCancelled;
            this.isCancelled = z;
            if (!z || z2) {
                return;
            }
            cleanup(DownloadType.IsStopped);
        }

        /* JADX INFO: renamed from: isFailed, reason: from getter */
        public final boolean getIsFailed() {
            return this.isFailed;
        }

        public final void setFailed(boolean z) {
            boolean z2 = this.isFailed;
            this.isFailed = z;
            if (!z || z2) {
                return;
            }
            cleanup(DownloadType.IsFailed);
        }

        /* JADX INFO: compiled from: DownloadManager.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance$Companion;", "", "<init>", "()V", "displayNotification", "", "context", "Landroid/content/Context;", "id", "", "notification", "Landroid/app/Notification;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final void displayNotification(final Context context, final int id, final Notification notification) {
                ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$Companion$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return VideoDownloadManager.EpisodeDownloadInstance.Companion.displayNotification$lambda$0(context, id, notification);
                    }
                });
            }

            static final Unit displayNotification$lambda$0(Context context, int i, Notification notification) {
                if (context.checkSelfPermission("android.permission.POST_NOTIFICATIONS") != 0) {
                    return Unit.INSTANCE;
                }
                NotificationManagerCompat.from(context).notify(VideoDownloadManager.DOWNLOAD_NOTIFICATION_TAG, i, notification);
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:119:0x0101 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:55:0x0169  */
        /* JADX WARN: Code duplicated, block: B:58:0x017e A[Catch: all -> 0x0210, Exception -> 0x0213, TRY_LEAVE, TryCatch #12 {Exception -> 0x0213, all -> 0x0210, blocks: (B:56:0x0176, B:58:0x017e), top: B:117:0x0176 }] */
        /* JADX WARN: Code duplicated, block: B:61:0x01c1  */
        /* JADX WARN: Code duplicated, block: B:66:0x01d5  */
        /* JADX WARN: Code duplicated, block: B:69:0x01ea A[Catch: all -> 0x0085, Exception -> 0x020d, TryCatch #3 {Exception -> 0x020d, blocks: (B:67:0x01e4, B:69:0x01ea, B:70:0x01ef, B:72:0x01f5, B:75:0x0200, B:76:0x0208), top: B:112:0x01e4 }] */
        /* JADX WARN: Code duplicated, block: B:70:0x01ef A[Catch: all -> 0x0085, Exception -> 0x020d, TryCatch #3 {Exception -> 0x020d, blocks: (B:67:0x01e4, B:69:0x01ea, B:70:0x01ef, B:72:0x01f5, B:75:0x0200, B:76:0x0208), top: B:112:0x01e4 }] */
        /* JADX WARN: Code duplicated, block: B:72:0x01f5 A[Catch: all -> 0x0085, Exception -> 0x020d, TryCatch #3 {Exception -> 0x020d, blocks: (B:67:0x01e4, B:69:0x01ea, B:70:0x01ef, B:72:0x01f5, B:75:0x0200, B:76:0x0208), top: B:112:0x01e4 }] */
        /* JADX WARN: Code duplicated, block: B:7:0x0018  */
        /* JADX WARN: Code duplicated, block: B:95:0x0223  */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x01d3, code lost:
        
            r0 = e;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r15v1 */
        /* JADX WARN: Type inference failed for: r15v2, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r15v4 */
        /* JADX WARN: Type inference failed for: r4v14, types: [int] */
        /* JADX WARN: Type inference failed for: r4v22 */
        /* JADX WARN: Type inference failed for: r4v34 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:61:0x01c1 -> B:62:0x01c9). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x01d5 -> B:112:0x01e4). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object downloadFromResume(com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadResumePackage r21, kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super android.app.Notification, kotlin.Unit> r22, kotlin.coroutines.Continuation<? super kotlin.Unit> r23) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 661
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager.EpisodeDownloadInstance.downloadFromResume(com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadResumePackage, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:7:0x0014  */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00d9, code lost:
        
            if (downloadFromResume(r8, r9, r0) == r1) goto L40;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object startDownload(com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadItem r7, com.lagradost.cloudstream3.utils.downloader.DownloadObjects.DownloadResumePackage r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
            /*
                Method dump skipped, instruction units count: 234
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager.EpisodeDownloadInstance.startDownload(com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadItem, com.lagradost.cloudstream3.utils.downloader.DownloadObjects$DownloadResumePackage, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit startDownload$lambda$0$0(EpisodeDownloadInstance episodeDownloadInstance, int i, Notification notification) {
            Intrinsics.checkNotNullParameter(notification, "notification");
            INSTANCE.displayNotification(episodeDownloadInstance.context, i, notification);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit startDownload$lambda$1$0(EpisodeDownloadInstance episodeDownloadInstance, int i, Notification notification) {
            Intrinsics.checkNotNullParameter(notification, "notification");
            INSTANCE.displayNotification(episodeDownloadInstance.context, i, notification);
            return Unit.INSTANCE;
        }

        static final Unit startDownload$lambda$2(EpisodeDownloadInstance episodeDownloadInstance, int i, Notification notification) {
            Intrinsics.checkNotNullParameter(notification, "notification");
            INSTANCE.displayNotification(episodeDownloadInstance.context, i, notification);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Object downloadFromResume(Continuation<? super Unit> continuation) {
            Object objDownloadFromResume;
            DownloadObjects.DownloadResumePackage resumePackage = this.downloadQueueWrapper.getResumePackage();
            return (resumePackage != null && (objDownloadFromResume = downloadFromResume(resumePackage, new Function2() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return VideoDownloadManager.EpisodeDownloadInstance.downloadFromResume$lambda$2(this.f$0, ((Integer) obj).intValue(), (Notification) obj2);
                }
            }, continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objDownloadFromResume : Unit.INSTANCE;
        }

        static final Unit downloadFromResume$lambda$2(EpisodeDownloadInstance episodeDownloadInstance, int i, Notification notification) {
            Intrinsics.checkNotNullParameter(notification, "notification");
            INSTANCE.displayNotification(episodeDownloadInstance.context, i, notification);
            return Unit.INSTANCE;
        }

        public final void startDownload() {
            Log.d(this.TAG, "Starting download " + this.downloadQueueWrapper.getId());
            CloudStreamApp.INSTANCE.setKey(VideoDownloadManager.KEY_RESUME_IN_QUEUE, String.valueOf(this.downloadQueueWrapper.getId()), this.downloadQueueWrapper);
            Coroutines.INSTANCE.ioSafe(this, new VideoDownloadManager$EpisodeDownloadInstance$startDownload$5(this, null));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void downloadEpisodeWithLinks(List<? extends ExtractorLink> links, List<SubtitleData> subs) {
            DownloadObjects.DownloadQueueItem downloadItem = this.downloadQueueWrapper.getDownloadItem();
            if (downloadItem == null) {
                return;
            }
            try {
                CloudStreamApp.INSTANCE.setKey(DataStoreKt.DOWNLOAD_HEADER_CACHE, String.valueOf(downloadItem.getResultId()), new DownloadObjects.DownloadHeaderCached(downloadItem.getApiName(), downloadItem.getResultUrl(), downloadItem.getResultType(), downloadItem.getResultName(), downloadItem.getResultPoster(), System.currentTimeMillis(), downloadItem.getResultId()));
                CloudStreamApp.INSTANCE.setKey(DataStore.INSTANCE.getFolderName(DataStoreKt.DOWNLOAD_EPISODE_CACHE, String.valueOf(downloadItem.getResultId())), String.valueOf(downloadItem.getEpisode().getId()), new DownloadObjects.DownloadEpisodeCached(downloadItem.getEpisode().getName(), downloadItem.getEpisode().getPoster(), downloadItem.getEpisode().getEpisode(), downloadItem.getEpisode().getSeason(), downloadItem.getResultId(), downloadItem.getEpisode().getScore(), downloadItem.getEpisode().getDescription(), System.currentTimeMillis(), downloadItem.getEpisode().getId()));
                DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata = VideoDownloadManager.INSTANCE.getDownloadEpisodeMetadata(downloadItem.getEpisode(), downloadItem.getResultName(), downloadItem.getApiName(), downloadItem.getResultPoster(), downloadItem.isMovie(), downloadItem.getResultType());
                String folder$app = DownloadFileManagement.INSTANCE.getFolder$app(downloadItem.getResultType(), downloadItem.getResultName());
                this.downloadJob = Coroutines.INSTANCE.ioSafe(this, new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$1(this, new DownloadObjects.DownloadItem("downloadpage/" + downloadItem.getResultId(), folder$app, downloadEpisodeMetadata, links), null));
                this.subtitleDownloadJob = Coroutines.INSTANCE.ioSafe(this, new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$2(subs, this, downloadEpisodeMetadata, folder$app, null));
            } catch (Exception e) {
                if (this.downloadJob == null) {
                    setFailed(true);
                }
                ArchComponentExtKt.logError(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:36:0x0179  */
        /* JADX WARN: Code duplicated, block: B:38:0x0180  */
        /* JADX WARN: Code duplicated, block: B:40:0x0186  */
        /* JADX WARN: Code duplicated, block: B:43:0x018d  */
        /* JADX WARN: Code duplicated, block: B:45:0x019f  */
        /* JADX WARN: Code duplicated, block: B:48:0x01bd  */
        /* JADX WARN: Code duplicated, block: B:54:0x01cf A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:56:? A[LOOP:0: B:46:0x01b7->B:56:?, LOOP_END, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:7:0x0018  */
        /* JADX WARN: Multi-variable type inference failed */
        public final Object downloadEpisodeWithoutLinks(Continuation<? super Unit> continuation) {
            VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1 videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1;
            DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata;
            Object obj;
            Continuation continuation2;
            Object objCreateDownloadNotification;
            VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1 videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$2;
            DownloadObjects.DownloadQueueItem downloadQueueItem;
            RepoLinkGenerator repoLinkGenerator;
            Set<SubtitleData> set;
            Set set2;
            Set<SubtitleData> set3;
            Set set4;
            DownloadObjects.DownloadQueueItem downloadQueueItem2;
            Job job;
            boolean z;
            if (continuation instanceof VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1) {
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1 = (VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1) continuation;
                if ((videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.label & Integer.MIN_VALUE) != 0) {
                    videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.label -= Integer.MIN_VALUE;
                } else {
                    videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1 = new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1(this, continuation);
                }
            } else {
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1 = new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1(this, continuation);
            }
            Object obj2 = videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.label;
            int i2 = 2;
            LoadResponse loadResponse = null;
            Object[] objArr = 0;
            if (i == 0) {
                ResultKt.throwOnFailure(obj2);
                DownloadObjects.DownloadQueueItem downloadItem = this.downloadQueueWrapper.getDownloadItem();
                if (downloadItem == null) {
                    return Unit.INSTANCE;
                }
                RepoLinkGenerator repoLinkGenerator2 = new RepoLinkGenerator(CollectionsKt.listOf(downloadItem.getEpisode()), loadResponse, i2, objArr == true ? 1 : 0);
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata2 = VideoDownloadManager.INSTANCE.getDownloadEpisodeMetadata(downloadItem.getEpisode(), downloadItem.getResultName(), downloadItem.getApiName(), downloadItem.getResultPoster(), downloadItem.isMovie(), downloadItem.getResultType());
                VideoDownloadManager videoDownloadManager = VideoDownloadManager.INSTANCE;
                Context context = this.context;
                String apiName = downloadItem.getApiName();
                String strAsString = TextUtilKt.txt(R.string.loading, new Object[0]).asString(this.context);
                DownloadType downloadType = DownloadType.IsPending;
                Function2 function2 = new Function2() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        return VideoDownloadManager.EpisodeDownloadInstance.downloadEpisodeWithoutLinks$lambda$0(((Integer) obj3).intValue(), (Notification) obj4);
                    }
                };
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$0 = downloadItem;
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$1 = repoLinkGenerator2;
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$2 = linkedHashSet;
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$3 = linkedHashSet2;
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$4 = SpillingKt.nullOutSpilledVariable(downloadEpisodeMetadata2);
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.label = 1;
                downloadEpisodeMetadata = downloadEpisodeMetadata2;
                VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1 videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$3 = videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1;
                obj = coroutine_suspended;
                continuation2 = null;
                objCreateDownloadNotification = videoDownloadManager.createDownloadNotification(context, apiName, strAsString, downloadEpisodeMetadata, downloadType, 0L, 1L, function2, null, null, 0L, videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$3);
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$2 = videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$3;
                if (objCreateDownloadNotification != obj) {
                    downloadQueueItem = downloadItem;
                    repoLinkGenerator = repoLinkGenerator2;
                    set = linkedHashSet2;
                    set2 = linkedHashSet;
                }
                return obj;
            }
            if (i == 1) {
                DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata3 = (DownloadObjects.DownloadEpisodeMetadata) videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$4;
                set = (Set) videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$3;
                set2 = (Set) videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$2;
                repoLinkGenerator = (RepoLinkGenerator) videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$1;
                downloadQueueItem = (DownloadObjects.DownloadQueueItem) videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$0;
                ResultKt.throwOnFailure(obj2);
                downloadEpisodeMetadata = downloadEpisodeMetadata3;
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$2 = videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1;
                obj = coroutine_suspended;
                objCreateDownloadNotification = obj2;
                continuation2 = null;
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                set3 = (Set) videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$3;
                set4 = (Set) videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$2;
                downloadQueueItem2 = (DownloadObjects.DownloadQueueItem) videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$1.L$0;
                ResultKt.throwOnFailure(obj2);
                continuation2 = null;
            }
            downloadQueueItem = downloadQueueItem2;
            set = set3;
            set2 = set4;
            NotificationManagerCompat.from(this.context).cancel(VideoDownloadManager.DOWNLOAD_NOTIFICATION_TAG, downloadQueueItem.getEpisode().getId());
            job = this.linkLoadingJob;
            if (job != null) {
                z = true;
                if (job.isCancelled()) {
                    setCancelled(true);
                    return Unit.INSTANCE;
                }
            } else {
                z = true;
            }
            if (set2.isEmpty()) {
                Coroutines.INSTANCE.main(this, new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$5(continuation2));
                setFailed(z);
                return Unit.INSTANCE;
            }
            Coroutines.INSTANCE.main(this, new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$6(continuation2));
            for (final QualityDataHelper.QualityProfile qualityProfile : QualityDataHelper.INSTANCE.getProfiles()) {
                if (qualityProfile.getTypes().contains(QualityDataHelper.QualityProfileType.Download)) {
                    downloadEpisodeWithLinks(CollectionsKt.sortedWith(set2, new Comparator() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$$inlined$sortedBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(Integer.valueOf(-QualityDataHelper.INSTANCE.getLinkPriority(qualityProfile.getId(), (ExtractorLink) t)), Integer.valueOf(-QualityDataHelper.INSTANCE.getLinkPriority(qualityProfile.getId(), (ExtractorLink) t2)));
                        }
                    }), AppContextUtils.INSTANCE.sortSubs(set));
                    return Unit.INSTANCE;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
            Notification notification = (Notification) objCreateDownloadNotification;
            if (notification != null) {
                INSTANCE.displayNotification(this.context, downloadQueueItem.getEpisode().getId(), notification);
            }
            Job jobIoSafe = Coroutines.INSTANCE.ioSafe(this, new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$4(repoLinkGenerator, set2, set, continuation2));
            this.linkLoadingJob = jobIoSafe;
            if (jobIoSafe != null) {
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$2.L$0 = downloadQueueItem;
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$2.L$1 = SpillingKt.nullOutSpilledVariable(repoLinkGenerator);
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$2.L$2 = set2;
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$2.L$3 = set;
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$2.L$4 = SpillingKt.nullOutSpilledVariable(downloadEpisodeMetadata);
                videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$2.label = 2;
                if (jobIoSafe.join(videoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$2) != obj) {
                    set3 = set;
                    set4 = set2;
                    downloadQueueItem2 = downloadQueueItem;
                    downloadQueueItem = downloadQueueItem2;
                    set = set3;
                    set2 = set4;
                }
                return obj;
            }
            NotificationManagerCompat.from(this.context).cancel(VideoDownloadManager.DOWNLOAD_NOTIFICATION_TAG, downloadQueueItem.getEpisode().getId());
            job = this.linkLoadingJob;
            if (job != null) {
                z = true;
                if (job.isCancelled()) {
                    setCancelled(true);
                    return Unit.INSTANCE;
                }
            } else {
                z = true;
            }
            if (set2.isEmpty()) {
                Coroutines.INSTANCE.main(this, new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$5(continuation2));
                setFailed(z);
                return Unit.INSTANCE;
            }
            Coroutines.INSTANCE.main(this, new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$6(continuation2));
            while (r1.hasNext()) {
                if (qualityProfile.getTypes().contains(QualityDataHelper.QualityProfileType.Download)) {
                    downloadEpisodeWithLinks(CollectionsKt.sortedWith(set2, new Comparator() { // from class: com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithoutLinks$$inlined$sortedBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(Integer.valueOf(-QualityDataHelper.INSTANCE.getLinkPriority(qualityProfile.getId(), (ExtractorLink) t)), Integer.valueOf(-QualityDataHelper.INSTANCE.getLinkPriority(qualityProfile.getId(), (ExtractorLink) t2)));
                        }
                    }), AppContextUtils.INSTANCE.sortSubs(set));
                    return Unit.INSTANCE;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }

        static final Unit downloadEpisodeWithoutLinks$lambda$0(int i, Notification notification) {
            Intrinsics.checkNotNullParameter(notification, "<unused var>");
            return Unit.INSTANCE;
        }
    }
}
