package com.lagradost.cloudstream3.ui.result;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.internal.ViewUtils;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.AnimeLoadResponse;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.EpisodeResponse;
import com.lagradost.cloudstream3.LiveStreamLoadResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivity;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.NextAiring;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchQuality;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.SeasonData;
import com.lagradost.cloudstream3.SimklSyncServices;
import com.lagradost.cloudstream3.TorrentLoadResponse;
import com.lagradost.cloudstream3.TrailerData;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.actions.AlwaysAskAction;
import com.lagradost.cloudstream3.actions.VideoClickAction;
import com.lagradost.cloudstream3.actions.VideoClickActionHolder;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.ui.APIRepository;
import com.lagradost.cloudstream3.ui.WatchType;
import com.lagradost.cloudstream3.ui.player.ExtractorUri;
import com.lagradost.cloudstream3.ui.player.GeneratorPlayer;
import com.lagradost.cloudstream3.ui.player.IGeneratorKt;
import com.lagradost.cloudstream3.ui.player.RepoLinkGenerator;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.CastHelper;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.Editor;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.FillerEpisodeCheck;
import com.lagradost.cloudstream3.utils.Qualities;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import com.lagradost.cloudstream3.utils.downloader.DownloadFileManagement;
import com.lagradost.cloudstream3.utils.downloader.DownloadObjects;
import com.lagradost.cloudstream3.utils.downloader.DownloadQueueManager;
import com.lagradost.cloudstream3.utils.downloader.DownloadUtils;
import io.ktor.http.MimesKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0080\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u0095\u00022\u00020\u0001:\u0006\u0094\u0002\u0095\u0002\u0096\u0002B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\rJV\u0010\u008a\u0001\u001a\u00020\r2\b\u0010\u008b\u0001\u001a\u00030\u0083\u00012\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u00012\u000b\b\u0002\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u00052*\b\u0002\u0010\u008f\u0001\u001a#\u0012\u0016\u0012\u00140!¢\u0006\u000f\b\u0091\u0001\u0012\n\b\u0092\u0001\u0012\u0005\b\b(\u0093\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0090\u0001J)\u0010\u0094\u0001\u001a\u00020\r2\n\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u00012\u0007\u0010\u0097\u0001\u001a\u00020\u00122\t\b\u0002\u0010\u0098\u0001\u001a\u00020!H\u0002JA\u0010\u0099\u0001\u001a\u00020\r2\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u00012,\b\u0002\u0010\u008f\u0001\u001a%\u0012\u0018\u0012\u0016\u0018\u00010!¢\u0006\u000f\b\u0091\u0001\u0012\n\b\u0092\u0001\u0012\u0005\b\b(\u009a\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0090\u0001JC\u0010\u009b\u0001\u001a\u00030\u009c\u00012\u0007\u0010\u009d\u0001\u001a\u00020\u00122\u0007\u0010\u009e\u0001\u001a\u00020\u001b2\u0007\u0010\u009f\u0001\u001a\u00020\u001b2\t\u0010 \u0001\u001a\u0004\u0018\u00010\u001b2\u0007\u0010¡\u0001\u001a\u00020!2\b\u0010¢\u0001\u001a\u00030£\u0001H\u0002JA\u0010¤\u0001\u001a\u00020\r2\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u00012,\b\u0002\u0010\u008f\u0001\u001a%\u0012\u0018\u0012\u0016\u0018\u00010!¢\u0006\u000f\b\u0091\u0001\u0012\n\b\u0092\u0001\u0012\u0005\b\b(\u009a\u0001\u0012\u0004\u0012\u00020\r\u0018\u00010\u0090\u0001J\u0081\u0001\u0010¥\u0001\u001a\u00020\r2\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u00012\b\u0010¦\u0001\u001a\u00030§\u00012\b\u0010¨\u0001\u001a\u00030©\u00012\u000e\u0010ª\u0001\u001a\t\u0012\u0005\u0012\u00030«\u00010\u00112F\u0010¬\u0001\u001aA\u0012\u0016\u0012\u00140!¢\u0006\u000f\b\u0091\u0001\u0012\n\b\u0092\u0001\u0012\u0005\b\b(®\u0001\u0012\u001e\u0012\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0011¢\u0006\u000f\b\u0091\u0001\u0012\n\b\u0092\u0001\u0012\u0005\b\b(¯\u0001\u0012\u0004\u0012\u00020\r0\u00ad\u0001H\u0003J\"\u0010°\u0001\u001a\u0004\u0018\u00010\u001b2\u0015\u0010±\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u000fH\u0002J\"\u0010²\u0001\u001a\u0004\u0018\u00010\u001b2\u0015\u0010±\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u000fH\u0002JG\u0010\u0094\u0001\u001a\u00020\r2\n\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u00012\u0007\u0010\u0097\u0001\u001a\u00020\u00122\u000e\u0010³\u0001\u001a\t\u0012\u0005\u0012\u00030´\u00010\u00112\u000e\u0010µ\u0001\u001a\t\u0012\u0005\u0012\u00030¶\u00010\u00112\u0007\u0010·\u0001\u001a\u00020\u0007H\u0002J\u0007\u0010¸\u0001\u001a\u00020\rJO\u0010¹\u0001\u001a\u00020\r2\u0007\u0010º\u0001\u001a\u00020?2\r\u0010»\u0001\u001a\b\u0012\u0004\u0012\u00020?0\u00112(\u0010¼\u0001\u001a#\b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\r0½\u0001\u0012\u0007\u0012\u0005\u0018\u00010¾\u00010\u00ad\u0001¢\u0006\u0003\u0010¿\u0001J`\u0010¹\u0001\u001a\u00020\r2\u0007\u0010º\u0001\u001a\u00020?2\u0019\u0010»\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020\u00070>0\u00112(\u0010¼\u0001\u001a#\b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\r0½\u0001\u0012\u0007\u0012\u0005\u0018\u00010¾\u00010\u00ad\u0001H\u0003¢\u0006\u0006\bÀ\u0001\u0010¿\u0001J\u0080\u0001\u0010Á\u0001\u001a\u00020\r2\u0007\u0010\u0097\u0001\u001a\u00020\u00122\u0007\u0010\u0098\u0001\u001a\u00020!2\u0011\b\u0002\u0010Â\u0001\u001a\n\u0012\u0005\u0012\u00030Ä\u00010Ã\u00012\t\b\u0002\u0010Å\u0001\u001a\u00020!2\t\b\u0002\u0010Æ\u0001\u001a\u00020!24\u0010Ç\u0001\u001a/\b\u0001\u0012\u0005\u0012\u00030É\u0001\u0012\u0005\u0012\u00030Ê\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\r0½\u0001\u0012\u0007\u0012\u0005\u0018\u00010¾\u00010È\u0001¢\u0006\u0003\bË\u0001H\u0002¢\u0006\u0003\u0010Ì\u0001JZ\u0010Ï\u0001\u001a\u00020\r2\u0007\u0010\u0097\u0001\u001a\u00020\u00122\u000f\u0010Â\u0001\u001a\n\u0012\u0005\u0012\u00030Ä\u00010Ã\u00012\u0007\u0010º\u0001\u001a\u00020?2\t\b\u0002\u0010Æ\u0001\u001a\u00020!2!\u0010¼\u0001\u001a\u001c\u0012\u0011\u0012\u000f\u0012\u0005\u0012\u00030Ê\u0001\u0012\u0004\u0012\u00020\u00070>\u0012\u0004\u0012\u00020\r0\u0090\u0001H\u0002J>\u0010Ð\u0001\u001a\u00020\r2\u0007\u0010\u0097\u0001\u001a\u00020\u00122\u0007\u0010º\u0001\u001a\u00020?2!\u0010¼\u0001\u001a\u001c\u0012\u0011\u0012\u000f\u0012\u0005\u0012\u00030Ê\u0001\u0012\u0004\u0012\u00020\u00070>\u0012\u0004\u0012\u00020\r0\u0090\u0001H\u0002J\u0007\u0010Ñ\u0001\u001a\u00020\rJQ\u0010Á\u0001\u001a\u00030Ê\u0001*\u00030É\u00012\u0007\u0010\u0097\u0001\u001a\u00020\u00122\u0007\u0010\u0098\u0001\u001a\u00020!2\u0011\b\u0002\u0010Â\u0001\u001a\n\u0012\u0005\u0012\u00030Ä\u00010Ã\u00012\t\b\u0002\u0010Å\u0001\u001a\u00020!2\t\b\u0002\u0010Æ\u0001\u001a\u00020!H\u0082@¢\u0006\u0003\u0010Ò\u0001J\u0012\u0010Ó\u0001\u001a\u00030Î\u00012\b\u0010Ô\u0001\u001a\u00030Õ\u0001J\u0007\u0010Ö\u0001\u001a\u00020\rJ3\u0010×\u0001\u001a\u00020\r2\b\u0010Ø\u0001\u001a\u00030Ù\u00012\u000e\u0010Ú\u0001\u001a\t\u0012\u0004\u0012\u00020\u001b0Û\u00012\b\u0010Ü\u0001\u001a\u00030Ý\u0001H\u0002¢\u0006\u0003\u0010Þ\u0001J>\u0010ß\u0001\u001a.\u0012\u0004\u0012\u00020\u0007\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u001b0Û\u00010à\u0001j\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u001b0Û\u0001`á\u00012\u0007\u0010â\u0001\u001a\u00020\u0007H\u0002J\u001a\u0010ã\u0001\u001a\u00020\r2\b\u0010Ô\u0001\u001a\u00030Õ\u0001H\u0082@¢\u0006\u0003\u0010ä\u0001JI\u0010å\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020!0>2\u0007\u0010æ\u0001\u001a\u00020\u00052\t\u0010ç\u0001\u001a\u0004\u0018\u00010\u00192\u0017\b\u0002\u0010è\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u000fH\u0082@¢\u0006\u0003\u0010é\u0001J'\u0010ê\u0001\u001a\u00020\r2\u0007\u0010ç\u0001\u001a\u00020\u00192\u0015\u0010è\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u000fJ\u0019\u0010ë\u0001\u001a\u00020\r2\u0007\u0010ª\u0001\u001a\u00020\u0005H\u0082@¢\u0006\u0003\u0010ì\u0001J\u0010\u0010í\u0001\u001a\u00020\r2\u0007\u0010\u008b\u0001\u001a\u00020\u0017J\u0010\u0010î\u0001\u001a\u00020\r2\u0007\u0010ï\u0001\u001a\u00020\u0014J\u0010\u0010ð\u0001\u001a\u00020\r2\u0007\u0010â\u0001\u001a\u00020\u0007J\u0010\u0010ñ\u0001\u001a\u00020\r2\u0007\u0010ò\u0001\u001a\u00020\u001eJ\n\u0010A\u001a\u0004\u0018\u00010\u0012H\u0002J \u0010<\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0007\u0010ó\u0001\u001a\u00020\u00102\u0007\u0010ï\u0001\u001a\u00020\u0014H\u0002J&\u0010ô\u0001\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0007\u0010õ\u0001\u001a\u00020\u001eH\u0002J\t\u0010ö\u0001\u001a\u00020\rH\u0002J\u0007\u0010÷\u0001\u001a\u00020\rJ\u0012\u0010ø\u0001\u001a\u00020\r2\u0007\u0010\u008e\u0001\u001a\u00020\u0005H\u0002J\u0012\u0010ù\u0001\u001a\u00020\r2\u0007\u0010\u008e\u0001\u001a\u00020\u0005H\u0002J\"\u0010ú\u0001\u001a\u00020!2\u0007\u0010û\u0001\u001a\u00020\u001e2\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0002J*\u0010ü\u0001\u001a\u00020\r2\t\u0010ó\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010ï\u0001\u001a\u0004\u0018\u00010\u00142\t\u0010õ\u0001\u001a\u0004\u0018\u00010\u001eH\u0002J=\u0010ý\u0001\u001a\u00020\r2\u0007\u0010\u008e\u0001\u001a\u00020\u00052\u0007\u0010þ\u0001\u001a\u00020\u00072\u0007\u0010ÿ\u0001\u001a\u00020#2\u0007\u0010\u0080\u0002\u001a\u00020!2\u0007\u0010ë\u0001\u001a\u00020!H\u0082@¢\u0006\u0003\u0010\u0081\u0002J+\u0010\u0082\u0002\u001a\u00020\r2\u0007\u0010\u008e\u0001\u001a\u00020\u00052\u0007\u0010þ\u0001\u001a\u00020\u00072\u0007\u0010ë\u0001\u001a\u00020!H\u0082@¢\u0006\u0003\u0010\u0083\u0002J\t\u0010\u0084\u0002\u001a\u00020\rH\u0002J\u000b\u0010\u0085\u0002\u001a\u0004\u0018\u00010vH\u0002J\u0013\u0010\u0086\u0002\u001a\u00030Î\u00012\u0007\u0010\u008e\u0001\u001a\u00020\u0005H\u0002J)\u0010H\u001a\b\u0012\u0004\u0012\u00020F0\u00112\u0007\u0010\u008e\u0001\u001a\u00020\u00052\t\b\u0002\u0010\u0087\u0002\u001a\u00020\u0007H\u0082@¢\u0006\u0003\u0010\u0088\u0002J\u001b\u0010\u0089\u0002\u001a\u00020\r2\u0007\u0010\u008e\u0001\u001a\u00020\u00052\u0007\u0010ÿ\u0001\u001a\u00020#H\u0002J\u0007\u0010\u008a\u0002\u001a\u00020!J\"\u0010\u008b\u0002\u001a\u00030Î\u00012\n\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u00012\n\u0010\u008c\u0002\u001a\u0005\u0018\u00010\u008d\u0002H\u0002J\u0011\u0010\u008e\u0002\u001a\u00030Î\u00012\u0007\u0010\u008f\u0002\u001a\u00020SJO\u0010\u0090\u0002\u001a\u00030Î\u00012\n\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0096\u00012\u0007\u0010\u0091\u0002\u001a\u00020\u001b2\u0007\u0010\u009f\u0001\u001a\u00020\u001b2\u0007\u0010\u0092\u0002\u001a\u00020!2\u0007\u0010\u0093\u0002\u001a\u00020\u00172\n\u0010\u008c\u0002\u001a\u0005\u0018\u00010\u008d\u00022\t\b\u0002\u0010\u0086\u0002\u001a\u00020!R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00110\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0012\u0010(\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010)R\u001e\u0010*\u001a\u0012\u0012\u0004\u0012\u00020\u00070+j\b\u0012\u0004\u0012\u00020\u0007`,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u00100\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010)R\u0012\u00101\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010)R\u001c\u00102\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000205\u0018\u00010403X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u00106\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000205\u0018\u00010407¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\"\u0010:\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0018\u00010403X\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010;\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0018\u00010407¢\u0006\b\n\u0000\u001a\u0004\b<\u00109R(\u0010=\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020\u00120>\u0018\u00010403X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010@\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020\u00120>\u0018\u00010407¢\u0006\b\n\u0000\u001a\u0004\bA\u00109R\u0016\u0010B\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?03X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010C\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?07¢\u0006\b\n\u0000\u001a\u0004\bD\u00109R\u001a\u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0\u001103X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010G\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0\u001107¢\u0006\b\n\u0000\u001a\u0004\bH\u00109R(\u0010I\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010?\u0012\u0004\u0012\u00020\u00170>0\u001103X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010J\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010?\u0012\u0004\u0012\u00020\u00170>0\u001107¢\u0006\b\n\u0000\u001a\u0004\bK\u00109R(\u0010L\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010?\u0012\u0004\u0012\u00020\u00140>0\u001103X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010M\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010?\u0012\u0004\u0012\u00020\u00140>0\u001107¢\u0006\b\n\u0000\u001a\u0004\bN\u00109R(\u0010O\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010?\u0012\u0004\u0012\u00020\u00070>0\u001103X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010P\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010?\u0012\u0004\u0012\u00020\u00070>0\u001107¢\u0006\b\n\u0000\u001a\u0004\bQ\u00109R\u001a\u0010R\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0\u001103X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0\u001107¢\u0006\b\n\u0000\u001a\u0004\bU\u00109R\u0016\u0010V\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?03X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010W\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?07¢\u0006\b\n\u0000\u001a\u0004\bX\u00109R\u0016\u0010Y\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?03X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010Z\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?07¢\u0006\b\n\u0000\u001a\u0004\b[\u00109R\u0014\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u000703X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010]\u001a\b\u0012\u0004\u0012\u00020\u000707¢\u0006\b\n\u0000\u001a\u0004\b^\u00109R&\u0010_\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020\u001e0>0\u001103X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010`\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020\u001e0>0\u001107¢\u0006\b\n\u0000\u001a\u0004\ba\u00109R\u0016\u0010b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?03X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?07¢\u0006\b\n\u0000\u001a\u0004\bd\u00109R\u0016\u0010e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?03X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?07¢\u0006\b\n\u0000\u001a\u0004\bg\u00109R\u0014\u0010h\u001a\b\u0012\u0004\u0012\u00020\u000703X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010i\u001a\b\u0012\u0004\u0012\u00020\u000707¢\u0006\b\n\u0000\u001a\u0004\bj\u00109R\u0014\u0010k\u001a\b\u0012\u0004\u0012\u00020\u000703X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010l\u001a\b\u0012\u0004\u0012\u00020\u000707¢\u0006\b\n\u0000\u001a\u0004\bm\u00109R\u0014\u0010n\u001a\b\u0012\u0004\u0012\u00020\u000703X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010o\u001a\b\u0012\u0004\u0012\u00020\u000707¢\u0006\b\n\u0000\u001a\u0004\bp\u00109R\u0016\u0010q\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010r03X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010s\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010r07¢\u0006\b\n\u0000\u001a\u0004\bt\u00109R\u0016\u0010u\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010v03X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010w\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010v07¢\u0006\b\n\u0000\u001a\u0004\bx\u00109R\u0016\u0010y\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b03X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010z\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b07¢\u0006\b\n\u0000\u001a\u0004\b{\u00109R\u0016\u0010|\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!03X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010}\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!07¢\u0006\b\n\u0000\u001a\u0004\b~\u00109R\u0016\u0010\u007f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!03X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0080\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!07¢\u0006\t\n\u0000\u001a\u0005\b\u0081\u0001\u00109R\u0016\u0010\u0082\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u000103X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0084\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u0001078F¢\u0006\u0007\u001a\u0005\b\u0085\u0001\u00109R\u0018\u0010\u0086\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0087\u000103X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0088\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0087\u000107¢\u0006\t\n\u0000\u001a\u0005\b\u0089\u0001\u00109R\u0012\u0010Í\u0001\u001a\u0005\u0018\u00010Î\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0097\u0002"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "currentResponse", "Lcom/lagradost/cloudstream3/LoadResponse;", "EPISODE_RANGE_SIZE", "", "getEPISODE_RANGE_SIZE", "()I", "setEPISODE_RANGE_SIZE", "(I)V", "clear", "", "currentEpisodes", "", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2$EpisodeIndexer;", "", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "currentRanges", "Lcom/lagradost/cloudstream3/ui/result/EpisodeRange;", "currentSeasons", "currentDubStatus", "Lcom/lagradost/cloudstream3/DubStatus;", "currentMeta", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncResult;", "currentSync", "", "currentIndex", "currentSorting", "Lcom/lagradost/cloudstream3/ui/result/EpisodeSortType;", "currentRange", "currentShowFillers", "", "currentRepo", "Lcom/lagradost/cloudstream3/ui/APIRepository;", "getCurrentRepo", "()Lcom/lagradost/cloudstream3/ui/APIRepository;", "setCurrentRepo", "(Lcom/lagradost/cloudstream3/ui/APIRepository;)V", "currentId", "Ljava/lang/Integer;", "fillers", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "generator", "Lcom/lagradost/cloudstream3/ui/player/RepoLinkGenerator;", "preferDubStatus", "preferStartEpisode", "preferStartSeason", "_page", "Landroidx/lifecycle/MutableLiveData;", "Lcom/lagradost/cloudstream3/mvvm/Resource;", "Lcom/lagradost/cloudstream3/ui/result/ResultData;", "page", "Landroidx/lifecycle/LiveData;", "getPage", "()Landroidx/lifecycle/LiveData;", "_episodes", "episodes", "getEpisodes", "_movie", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/UiText;", "movie", "getMovie", "_episodesCountText", "episodesCountText", "getEpisodesCountText", "_trailers", "Lcom/lagradost/cloudstream3/ui/result/ExtractedTrailerData;", "trailers", "getTrailers", "_dubSubSelections", "dubSubSelections", "getDubSubSelections", "_rangeSelections", "rangeSelections", "getRangeSelections", "_seasonSelections", "seasonSelections", "getSeasonSelections", "_recommendations", "Lcom/lagradost/cloudstream3/SearchResponse;", "recommendations", "getRecommendations", "_selectedRange", "selectedRange", "getSelectedRange", "_selectedSorting", "selectedSorting", "getSelectedSorting", "_selectedSortingIndex", "selectedSortingIndex", "getSelectedSortingIndex", "_sortSelections", "sortSelections", "getSortSelections", "_selectedSeason", "selectedSeason", "getSelectedSeason", "_selectedDubStatus", "selectedDubStatus", "getSelectedDubStatus", "_selectedRangeIndex", "selectedRangeIndex", "getSelectedRangeIndex", "_selectedSeasonIndex", "selectedSeasonIndex", "getSelectedSeasonIndex", "_selectedDubStatusIndex", "selectedDubStatusIndex", "getSelectedDubStatusIndex", "_loadedLinks", "Lcom/lagradost/cloudstream3/ui/result/LinkProgress;", "loadedLinks", "getLoadedLinks", "_resumeWatching", "Lcom/lagradost/cloudstream3/ui/result/ResumeWatchingStatus;", "resumeWatching", "getResumeWatching", "_episodeSynopsis", "episodeSynopsis", "getEpisodeSynopsis", "_subscribeStatus", "subscribeStatus", "getSubscribeStatus", "_favoriteStatus", "favoriteStatus", "getFavoriteStatus", "_watchStatus", "Lcom/lagradost/cloudstream3/ui/WatchType;", "watchStatus", "getWatchStatus", "_selectPopup", "Lcom/lagradost/cloudstream3/ui/result/SelectPopup;", "selectPopup", "getSelectPopup", "updateWatchStatus", NotificationCompat.CATEGORY_STATUS, "context", "Landroid/content/Context;", "loadResponse", "statusChangedCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "statusChanged", "startChromecast", "activity", "Landroid/app/Activity;", "result", "isVisible", "toggleSubscriptionStatus", "newStatus", "getMeta", "Lcom/lagradost/cloudstream3/utils/downloader/DownloadObjects$DownloadEpisodeMetadata;", "episode", "titleName", "apiName", "currentPoster", "currentIsMovie", "tvType", "Lcom/lagradost/cloudstream3/TvType;", "toggleFavoriteStatus", "checkAndWarnDuplicates", "listType", "Lcom/lagradost/cloudstream3/ui/result/LibraryListType;", "checkDuplicateData", "Lcom/lagradost/cloudstream3/ui/result/CheckDuplicateData;", "data", "Lcom/lagradost/cloudstream3/utils/DataStoreHelper$LibrarySearchResponse;", "checkDuplicatesCallback", "Lkotlin/Function2;", "shouldContinue", "duplicateIds", "getImdbIdFromSyncData", "syncData", "getTMDbIdFromSyncData", CloudStreamPackage.LINKS_EXTRA, "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", CloudStreamPackage.SUBTITLE_EXTRA, "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "startIndex", "cancelLinks", "postPopup", "text", "options", "callback", "Lkotlin/coroutines/Continuation;", "", "(Lcom/lagradost/cloudstream3/utils/UiText;Ljava/util/List;Lkotlin/jvm/functions/Function2;)V", "postPopupArray", "loadLinks", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "clearCache", "isCasting", "work", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "Lkotlin/ExtensionFunctionType;", "(Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;ZLjava/util/Set;ZZLkotlin/jvm/functions/Function3;)V", "currentLoadLinkJob", "Lkotlinx/coroutines/Job;", "acquireSingleLink", "acquireSingleSubtitle", "skipLoading", "(Lkotlinx/coroutines/CoroutineScope;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;ZLjava/util/Set;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleAction", "click", "Lcom/lagradost/cloudstream3/ui/result/EpisodeClickEvent;", "releaseEpisodeSynopsis", "markEpisodes", "editor", "Lcom/lagradost/cloudstream3/utils/Editor;", "episodeIds", "", "watchState", "Lcom/lagradost/cloudstream3/ui/result/VideoWatchState;", "(Lcom/lagradost/cloudstream3/utils/Editor;[Ljava/lang/String;Lcom/lagradost/cloudstream3/ui/result/VideoWatchState;)V", "getEpisodesIdsBySeason", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "season", "handleEpisodeClickEvent", "(Lcom/lagradost/cloudstream3/ui/result/EpisodeClickEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "applyMeta", "resp", "meta", "syncs", "(Lcom/lagradost/cloudstream3/LoadResponse;Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncResult;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setMeta", "updateFillers", "(Lcom/lagradost/cloudstream3/LoadResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "changeDubStatus", "changeRange", "range", "changeSeason", "setSort", "sortType", "indexer", "getSortedEpisodes", "sorting", "postMovie", "reloadEpisodes", "postSubscription", "postFavorites", "shouldEnableSort", "type", "postEpisodeRange", "postSuccessful", "mainId", "apiRepository", "updateEpisodes", "(Lcom/lagradost/cloudstream3/LoadResponse;ILcom/lagradost/cloudstream3/ui/APIRepository;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postEpisodes", "(Lcom/lagradost/cloudstream3/LoadResponse;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postResume", "resume", "loadTrailers", "limit", "(Lcom/lagradost/cloudstream3/LoadResponse;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postPage", "hasLoaded", "handleAutoStart", "autostart", "Lcom/lagradost/cloudstream3/ui/result/AutoResume;", "loadSmall", "searchResponse", "load", ImagesContract.URL, "showFillers", "dubStatus", "EpisodeIndexer", "Companion", "LoadResponseFromSearch", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ResultViewModel2 extends ViewModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "RVM2";
    private final MutableLiveData<List<Pair<UiText, DubStatus>>> _dubSubSelections;
    private final MutableLiveData<String> _episodeSynopsis;
    private final MutableLiveData<Resource<List<ResultEpisode>>> _episodes;
    private final MutableLiveData<UiText> _episodesCountText;
    private final MutableLiveData<Boolean> _favoriteStatus;
    private final MutableLiveData<LinkProgress> _loadedLinks;
    private final MutableLiveData<Resource<Pair<UiText, ResultEpisode>>> _movie;
    private final MutableLiveData<Resource<ResultData>> _page;
    private final MutableLiveData<List<Pair<UiText, EpisodeRange>>> _rangeSelections;
    private final MutableLiveData<List<SearchResponse>> _recommendations;
    private final MutableLiveData<ResumeWatchingStatus> _resumeWatching;
    private final MutableLiveData<List<Pair<UiText, Integer>>> _seasonSelections;
    private final MutableLiveData<SelectPopup> _selectPopup;
    private final MutableLiveData<UiText> _selectedDubStatus;
    private final MutableLiveData<Integer> _selectedDubStatusIndex;
    private final MutableLiveData<UiText> _selectedRange;
    private final MutableLiveData<Integer> _selectedRangeIndex;
    private final MutableLiveData<UiText> _selectedSeason;
    private final MutableLiveData<Integer> _selectedSeasonIndex;
    private final MutableLiveData<UiText> _selectedSorting;
    private final MutableLiveData<Integer> _selectedSortingIndex;
    private final MutableLiveData<List<Pair<UiText, EpisodeSortType>>> _sortSelections;
    private final MutableLiveData<Boolean> _subscribeStatus;
    private final MutableLiveData<List<ExtractedTrailerData>> _trailers;
    private final MutableLiveData<WatchType> _watchStatus;
    private Integer currentId;
    private EpisodeIndexer currentIndex;
    private Job currentLoadLinkJob;
    private SyncAPI.SyncResult currentMeta;
    private EpisodeRange currentRange;
    private APIRepository currentRepo;
    private LoadResponse currentResponse;
    private boolean currentShowFillers;
    private EpisodeSortType currentSorting;
    private Map<String, String> currentSync;
    private final LiveData<List<Pair<UiText, DubStatus>>> dubSubSelections;
    private final LiveData<String> episodeSynopsis;
    private final LiveData<Resource<List<ResultEpisode>>> episodes;
    private final LiveData<UiText> episodesCountText;
    private final LiveData<Boolean> favoriteStatus;
    private RepoLinkGenerator generator;
    private final LiveData<LinkProgress> loadedLinks;
    private final LiveData<Resource<Pair<UiText, ResultEpisode>>> movie;
    private final LiveData<Resource<ResultData>> page;
    private DubStatus preferDubStatus;
    private Integer preferStartEpisode;
    private Integer preferStartSeason;
    private final LiveData<List<Pair<UiText, EpisodeRange>>> rangeSelections;
    private final LiveData<List<SearchResponse>> recommendations;
    private final LiveData<ResumeWatchingStatus> resumeWatching;
    private final LiveData<List<Pair<UiText, Integer>>> seasonSelections;
    private final LiveData<SelectPopup> selectPopup;
    private final LiveData<UiText> selectedDubStatus;
    private final LiveData<Integer> selectedDubStatusIndex;
    private final LiveData<UiText> selectedRange;
    private final LiveData<Integer> selectedRangeIndex;
    private final LiveData<UiText> selectedSeason;
    private final LiveData<Integer> selectedSeasonIndex;
    private final LiveData<UiText> selectedSorting;
    private final LiveData<Integer> selectedSortingIndex;
    private final LiveData<List<Pair<UiText, EpisodeSortType>>> sortSelections;
    private final LiveData<Boolean> subscribeStatus;
    private final LiveData<List<ExtractedTrailerData>> trailers;
    private int EPISODE_RANGE_SIZE = 20;
    private Map<EpisodeIndexer, ? extends List<ResultEpisode>> currentEpisodes = MapsKt.emptyMap();
    private Map<EpisodeIndexer, ? extends List<EpisodeRange>> currentRanges = MapsKt.emptyMap();
    private List<Integer> currentSeasons = CollectionsKt.emptyList();
    private List<? extends DubStatus> currentDubStatus = CollectionsKt.emptyList();
    private HashSet<Integer> fillers = new HashSet<>();

    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[LibraryListType.values().length];
            try {
                iArr[LibraryListType.BOOKMARKS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LibraryListType.FAVORITES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LibraryListType.SUBSCRIPTIONS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[EpisodeSortType.values().length];
            try {
                iArr2[EpisodeSortType.NUMBER_ASC.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[EpisodeSortType.NUMBER_DESC.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[EpisodeSortType.RATING_HIGH_LOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[EpisodeSortType.RATING_LOW_HIGH.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[EpisodeSortType.DATE_NEWEST.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[EpisodeSortType.DATE_OLDEST.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[TvType.values().length];
            try {
                iArr3[TvType.Torrent.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr3[TvType.TvSeries.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$applyMeta$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2", f = "ResultViewModel2.kt", i = {0, 0, 0, 0, 0, 0}, l = {1710}, m = "applyMeta", n = {"resp", "meta", "syncs", "updateEpisodes", "$this$applyMeta_u24lambda_u240", "$i$a$-apply-ResultViewModel2$applyMeta$out$1"}, nl = {1802}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "I$0"}, v = 2)
    static final class C08821 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C08821(Continuation<? super C08821> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ResultViewModel2.this.applyMeta(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$loadLinks$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2", f = "ResultViewModel2.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {1283}, m = "loadLinks", n = {"$this$loadLinks", "result", "sourceTypes", "tempGenerator", CloudStreamPackage.LINKS_EXTRA, CloudStreamPackage.SUBTITLE_EXTRA, "isVisible", "clearCache", "isCasting"}, nl = {1304}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "Z$1", "Z$2"}, v = 2)
    static final class C08882 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        boolean Z$1;
        boolean Z$2;
        int label;
        /* synthetic */ Object result;

        C08882(Continuation<? super C08882> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ResultViewModel2.this.loadLinks(null, null, false, null, false, false, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$postEpisodes$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2", f = "ResultViewModel2.kt", i = {0, 0, 0}, l = {2175}, m = "postEpisodes", n = {"loadResponse", "mainId", "updateFillers"}, nl = {2178}, s = {"L$0", "I$0", "Z$0"}, v = 2)
    static final class C08911 extends ContinuationImpl {
        int I$0;
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C08911(Continuation<? super C08911> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ResultViewModel2.this.postEpisodes(null, 0, false, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$updateFillers$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2", f = "ResultViewModel2.kt", i = {0}, l = {1835}, m = "updateFillers", n = {"data"}, nl = {1837}, s = {"L$0"}, v = 2)
    static final class C08951 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C08951(Continuation<? super C08951> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ResultViewModel2.this.updateFillers(null, this);
        }
    }

    public ResultViewModel2() {
        MutableLiveData<Resource<ResultData>> mutableLiveData = new MutableLiveData<>(null);
        this._page = mutableLiveData;
        this.page = mutableLiveData;
        MutableLiveData<Resource<List<ResultEpisode>>> mutableLiveData2 = new MutableLiveData<>(new Resource.Loading(null, 1, null));
        this._episodes = mutableLiveData2;
        this.episodes = mutableLiveData2;
        MutableLiveData<Resource<Pair<UiText, ResultEpisode>>> mutableLiveData3 = new MutableLiveData<>(null);
        this._movie = mutableLiveData3;
        this.movie = mutableLiveData3;
        MutableLiveData<UiText> mutableLiveData4 = new MutableLiveData<>(null);
        this._episodesCountText = mutableLiveData4;
        this.episodesCountText = mutableLiveData4;
        MutableLiveData<List<ExtractedTrailerData>> mutableLiveData5 = new MutableLiveData<>(new ArrayList());
        this._trailers = mutableLiveData5;
        this.trailers = mutableLiveData5;
        MutableLiveData<List<Pair<UiText, DubStatus>>> mutableLiveData6 = new MutableLiveData<>(CollectionsKt.emptyList());
        this._dubSubSelections = mutableLiveData6;
        this.dubSubSelections = mutableLiveData6;
        MutableLiveData<List<Pair<UiText, EpisodeRange>>> mutableLiveData7 = new MutableLiveData<>(CollectionsKt.emptyList());
        this._rangeSelections = mutableLiveData7;
        this.rangeSelections = mutableLiveData7;
        MutableLiveData<List<Pair<UiText, Integer>>> mutableLiveData8 = new MutableLiveData<>(CollectionsKt.emptyList());
        this._seasonSelections = mutableLiveData8;
        this.seasonSelections = mutableLiveData8;
        MutableLiveData<List<SearchResponse>> mutableLiveData9 = new MutableLiveData<>(CollectionsKt.emptyList());
        this._recommendations = mutableLiveData9;
        this.recommendations = mutableLiveData9;
        MutableLiveData<UiText> mutableLiveData10 = new MutableLiveData<>(null);
        this._selectedRange = mutableLiveData10;
        this.selectedRange = mutableLiveData10;
        MutableLiveData<UiText> mutableLiveData11 = new MutableLiveData<>(null);
        this._selectedSorting = mutableLiveData11;
        this.selectedSorting = mutableLiveData11;
        MutableLiveData<Integer> mutableLiveData12 = new MutableLiveData<>(-1);
        this._selectedSortingIndex = mutableLiveData12;
        this.selectedSortingIndex = mutableLiveData12;
        MutableLiveData<List<Pair<UiText, EpisodeSortType>>> mutableLiveData13 = new MutableLiveData<>(CollectionsKt.emptyList());
        this._sortSelections = mutableLiveData13;
        this.sortSelections = mutableLiveData13;
        MutableLiveData<UiText> mutableLiveData14 = new MutableLiveData<>(null);
        this._selectedSeason = mutableLiveData14;
        this.selectedSeason = mutableLiveData14;
        MutableLiveData<UiText> mutableLiveData15 = new MutableLiveData<>(null);
        this._selectedDubStatus = mutableLiveData15;
        this.selectedDubStatus = mutableLiveData15;
        MutableLiveData<Integer> mutableLiveData16 = new MutableLiveData<>(-1);
        this._selectedRangeIndex = mutableLiveData16;
        this.selectedRangeIndex = mutableLiveData16;
        MutableLiveData<Integer> mutableLiveData17 = new MutableLiveData<>(-1);
        this._selectedSeasonIndex = mutableLiveData17;
        this.selectedSeasonIndex = mutableLiveData17;
        MutableLiveData<Integer> mutableLiveData18 = new MutableLiveData<>(-1);
        this._selectedDubStatusIndex = mutableLiveData18;
        this.selectedDubStatusIndex = mutableLiveData18;
        MutableLiveData<LinkProgress> mutableLiveData19 = new MutableLiveData<>(null);
        this._loadedLinks = mutableLiveData19;
        this.loadedLinks = mutableLiveData19;
        MutableLiveData<ResumeWatchingStatus> mutableLiveData20 = new MutableLiveData<>(null);
        this._resumeWatching = mutableLiveData20;
        this.resumeWatching = mutableLiveData20;
        MutableLiveData<String> mutableLiveData21 = new MutableLiveData<>(null);
        this._episodeSynopsis = mutableLiveData21;
        this.episodeSynopsis = mutableLiveData21;
        MutableLiveData<Boolean> mutableLiveData22 = new MutableLiveData<>(null);
        this._subscribeStatus = mutableLiveData22;
        this.subscribeStatus = mutableLiveData22;
        MutableLiveData<Boolean> mutableLiveData23 = new MutableLiveData<>(null);
        this._favoriteStatus = mutableLiveData23;
        this.favoriteStatus = mutableLiveData23;
        this._watchStatus = new MutableLiveData<>(WatchType.NONE);
        MutableLiveData<SelectPopup> mutableLiveData24 = new MutableLiveData<>(null);
        this._selectPopup = mutableLiveData24;
        this.selectPopup = mutableLiveData24;
    }

    public final int getEPISODE_RANGE_SIZE() {
        return this.EPISODE_RANGE_SIZE;
    }

    public final void setEPISODE_RANGE_SIZE(int i) {
        this.EPISODE_RANGE_SIZE = i;
    }

    public final void clear() {
        this.currentResponse = null;
        this._page.postValue(null);
    }

    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2$EpisodeIndexer;", "", "dubStatus", "Lcom/lagradost/cloudstream3/DubStatus;", "season", "", "<init>", "(Lcom/lagradost/cloudstream3/DubStatus;I)V", "getDubStatus", "()Lcom/lagradost/cloudstream3/DubStatus;", "getSeason", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class EpisodeIndexer {
        private final DubStatus dubStatus;
        private final int season;

        public static /* synthetic */ EpisodeIndexer copy$default(EpisodeIndexer episodeIndexer, DubStatus dubStatus, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                dubStatus = episodeIndexer.dubStatus;
            }
            if ((i2 & 2) != 0) {
                i = episodeIndexer.season;
            }
            return episodeIndexer.copy(dubStatus, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final DubStatus getDubStatus() {
            return this.dubStatus;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getSeason() {
            return this.season;
        }

        public final EpisodeIndexer copy(DubStatus dubStatus, int season) {
            Intrinsics.checkNotNullParameter(dubStatus, "dubStatus");
            return new EpisodeIndexer(dubStatus, season);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EpisodeIndexer)) {
                return false;
            }
            EpisodeIndexer episodeIndexer = (EpisodeIndexer) other;
            return this.dubStatus == episodeIndexer.dubStatus && this.season == episodeIndexer.season;
        }

        public int hashCode() {
            return (this.dubStatus.hashCode() * 31) + this.season;
        }

        public String toString() {
            return "EpisodeIndexer(dubStatus=" + this.dubStatus + ", season=" + this.season + ')';
        }

        public EpisodeIndexer(DubStatus dubStatus, int i) {
            Intrinsics.checkNotNullParameter(dubStatus, "dubStatus");
            this.dubStatus = dubStatus;
            this.season = i;
        }

        public final DubStatus getDubStatus() {
            return this.dubStatus;
        }

        public final int getSeason() {
            return this.season;
        }
    }

    public final APIRepository getCurrentRepo() {
        return this.currentRepo;
    }

    public final void setCurrentRepo(APIRepository aPIRepository) {
        this.currentRepo = aPIRepository;
    }

    public final LiveData<Resource<ResultData>> getPage() {
        return this.page;
    }

    public final LiveData<Resource<List<ResultEpisode>>> getEpisodes() {
        return this.episodes;
    }

    /* JADX INFO: renamed from: getMovie, reason: collision with other method in class */
    public final LiveData<Resource<Pair<UiText, ResultEpisode>>> m8575getMovie() {
        return this.movie;
    }

    public final LiveData<UiText> getEpisodesCountText() {
        return this.episodesCountText;
    }

    public final LiveData<List<ExtractedTrailerData>> getTrailers() {
        return this.trailers;
    }

    public final LiveData<List<Pair<UiText, DubStatus>>> getDubSubSelections() {
        return this.dubSubSelections;
    }

    public final LiveData<List<Pair<UiText, EpisodeRange>>> getRangeSelections() {
        return this.rangeSelections;
    }

    public final LiveData<List<Pair<UiText, Integer>>> getSeasonSelections() {
        return this.seasonSelections;
    }

    public final LiveData<List<SearchResponse>> getRecommendations() {
        return this.recommendations;
    }

    public final LiveData<UiText> getSelectedRange() {
        return this.selectedRange;
    }

    public final LiveData<UiText> getSelectedSorting() {
        return this.selectedSorting;
    }

    public final LiveData<Integer> getSelectedSortingIndex() {
        return this.selectedSortingIndex;
    }

    public final LiveData<List<Pair<UiText, EpisodeSortType>>> getSortSelections() {
        return this.sortSelections;
    }

    public final LiveData<UiText> getSelectedSeason() {
        return this.selectedSeason;
    }

    public final LiveData<UiText> getSelectedDubStatus() {
        return this.selectedDubStatus;
    }

    public final LiveData<Integer> getSelectedRangeIndex() {
        return this.selectedRangeIndex;
    }

    public final LiveData<Integer> getSelectedSeasonIndex() {
        return this.selectedSeasonIndex;
    }

    public final LiveData<Integer> getSelectedDubStatusIndex() {
        return this.selectedDubStatusIndex;
    }

    public final LiveData<LinkProgress> getLoadedLinks() {
        return this.loadedLinks;
    }

    public final LiveData<ResumeWatchingStatus> getResumeWatching() {
        return this.resumeWatching;
    }

    public final LiveData<String> getEpisodeSynopsis() {
        return this.episodeSynopsis;
    }

    public final LiveData<Boolean> getSubscribeStatus() {
        return this.subscribeStatus;
    }

    public final LiveData<Boolean> getFavoriteStatus() {
        return this.favoriteStatus;
    }

    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000fJ%\u0010\u0010\u001a\u0004\u0018\u00010\r*\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u0002J \u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\b0\u00152\u0006\u0010\u0018\u001a\u00020\u0017J<\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\b0\u00152\u0018\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\b0\u00152\u0006\u0010\u001c\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2$Companion;", "", "<init>", "()V", "TAG", "", "getSeason", "Lcom/lagradost/cloudstream3/SeasonData;", "", "season", "", "(Ljava/util/List;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/SeasonData;", "seasonToTxt", "Lcom/lagradost/cloudstream3/utils/UiText;", "seasonData", "(Lcom/lagradost/cloudstream3/SeasonData;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/utils/UiText;", "getSeasonTxt", "(Ljava/util/List;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/utils/UiText;", "filterName", "name", "singleMap", "", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2$EpisodeIndexer;", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "ep", "getRanges", "Lcom/lagradost/cloudstream3/ui/result/EpisodeRange;", "allEpisodes", "EPISODE_RANGE_SIZE", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final SeasonData getSeason(List<SeasonData> list, Integer num) {
            Object obj = null;
            if (num == null || list == null) {
                return null;
            }
            for (Object obj2 : list) {
                int season = ((SeasonData) obj2).getSeason();
                if (num != null && season == num.intValue()) {
                    obj = obj2;
                    break;
                }
            }
            return (SeasonData) obj;
        }

        /* JADX WARN: Code duplicated, block: B:22:0x0044  */
        public final UiText seasonToTxt(SeasonData seasonData, Integer season) {
            String str;
            Integer displaySeason;
            String name;
            if (season != null && season.intValue() == 0) {
                return TextUtilKt.txt(R.string.no_season, new Object[0]);
            }
            if ((seasonData != null ? seasonData.getName() : null) != null && seasonData.getDisplaySeason() == null) {
                return TextUtilKt.txtNull(seasonData.getName());
            }
            if (seasonData == null || (name = seasonData.getName()) == null) {
                str = "";
            } else {
                str = Stream.ID_UNKNOWN + name;
                if (str == null) {
                    str = "";
                }
            }
            Integer numValueOf = Integer.valueOf(R.string.season_format);
            UiText uiTextTxt = TextUtilKt.txt(R.string.season, new Object[0]);
            if (seasonData != null && (displaySeason = seasonData.getDisplaySeason()) != null) {
                season = displaySeason;
            }
            return TextUtilKt.txtNull(numValueOf, uiTextTxt, season, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final UiText getSeasonTxt(List<SeasonData> list, Integer num) {
            return seasonToTxt(getSeason(list, num), num);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String filterName(String name) {
            List<String> groupValues;
            String str;
            if (name == null) {
                return null;
            }
            MatchResult matchResultFind$default = Regex.find$default(new Regex("^[eE]pisode [0-9]*(.*)"), name, 0, 2, null);
            if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = groupValues.get(1)) == null || str.length() != 0) {
                return name;
            }
            return null;
        }

        public final Map<EpisodeIndexer, List<ResultEpisode>> singleMap(ResultEpisode ep) {
            Intrinsics.checkNotNullParameter(ep, "ep");
            return MapsKt.mapOf(TuplesKt.to(new EpisodeIndexer(DubStatus.None, 0), CollectionsKt.listOf(ep)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Map<EpisodeIndexer, List<EpisodeRange>> getRanges(Map<EpisodeIndexer, ? extends List<ResultEpisode>> allEpisodes, int EPISODE_RANGE_SIZE) {
            Set<EpisodeIndexer> setKeySet = allEpisodes.keySet();
            ArrayList arrayList = new ArrayList();
            for (EpisodeIndexer episodeIndexer : setKeySet) {
                List<ResultEpisode> list = allEpisodes.get(episodeIndexer);
                Pair pair = null;
                if (list != null) {
                    int i = 0;
                    if (list.size() <= EPISODE_RANGE_SIZE + 10) {
                        int size = list.size();
                        List<ResultEpisode> list2 = list;
                        Iterator<T> it = list2.iterator();
                        if (!it.hasNext()) {
                            throw new NoSuchElementException();
                        }
                        int episode = ((ResultEpisode) it.next()).getEpisode();
                        while (it.hasNext()) {
                            int episode2 = ((ResultEpisode) it.next()).getEpisode();
                            if (episode > episode2) {
                                episode = episode2;
                            }
                        }
                        Iterator<T> it2 = list2.iterator();
                        if (it2.hasNext()) {
                            int episode3 = ((ResultEpisode) it2.next()).getEpisode();
                            while (it2.hasNext()) {
                                int episode4 = ((ResultEpisode) it2.next()).getEpisode();
                                if (episode3 < episode4) {
                                    episode3 = episode4;
                                }
                            }
                            pair = TuplesKt.to(episodeIndexer, CollectionsKt.listOf(new EpisodeRange(0, size, episode, episode3)));
                        } else {
                            throw new NoSuchElementException();
                        }
                    } else if (!list.isEmpty()) {
                        ArrayList arrayList2 = new ArrayList();
                        ResultEpisode resultEpisode = (ResultEpisode) CollectionsKt.first((List) list);
                        int size2 = list.size();
                        int episode5 = resultEpisode.getEpisode();
                        int episode6 = resultEpisode.getEpisode();
                        int i2 = episode5;
                        int i3 = 0;
                        while (i < size2) {
                            i3 += EPISODE_RANGE_SIZE;
                            int i4 = i2;
                            int i5 = i;
                            while (i5 < size2 && list.get(i5).getEpisode() <= i3) {
                                int episode7 = list.get(i5).getEpisode();
                                if (episode7 < i4) {
                                    i4 = episode7;
                                }
                                if (episode7 > episode6) {
                                    episode6 = episode7;
                                }
                                i5++;
                            }
                            int i6 = i5 - i;
                            if (i6 > 0) {
                                arrayList2.add(new EpisodeRange(i, i6, i4, episode6));
                                int i7 = i5;
                                i2 = Integer.MAX_VALUE;
                                episode6 = Integer.MIN_VALUE;
                                i = i7;
                            } else {
                                i = i5;
                                i2 = i4;
                            }
                        }
                        pair = TuplesKt.to(episodeIndexer, arrayList2);
                    }
                }
                if (pair != null) {
                    arrayList.add(pair);
                }
            }
            return MapsKt.toMap(arrayList);
        }
    }

    public final LiveData<WatchType> getWatchStatus() {
        return this._watchStatus;
    }

    public final LiveData<SelectPopup> getSelectPopup() {
        return this.selectPopup;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void updateWatchStatus$default(ResultViewModel2 resultViewModel2, WatchType watchType, Context context, LoadResponse loadResponse, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            loadResponse = null;
        }
        if ((i & 8) != 0) {
            function1 = null;
        }
        resultViewModel2.updateWatchStatus(watchType, context, loadResponse, function1);
    }

    public final void updateWatchStatus(final WatchType status, Context context, LoadResponse loadResponse, final Function1<? super Boolean, Unit> statusChangedCallback) {
        Integer num;
        Pair pair;
        List<DataStoreHelper.BookmarkedData> listEmptyList;
        Intrinsics.checkNotNullParameter(status, "status");
        if (loadResponse == null || (pair = TuplesKt.to(loadResponse, Integer.valueOf(ResultViewModel2Kt.getId(loadResponse)))) == null) {
            LoadResponse loadResponse2 = this.currentResponse;
            if (loadResponse2 == null || (num = this.currentId) == null) {
                return;
            }
            num.intValue();
            pair = TuplesKt.to(loadResponse2, num);
        }
        final LoadResponse loadResponse3 = (LoadResponse) pair.component1();
        final int iIntValue = ((Number) pair.component2()).intValue();
        final WatchType resultWatchState = DataStoreHelper.INSTANCE.getResultWatchState(iIntValue);
        if (resultWatchState == WatchType.NONE && status != WatchType.NONE) {
            listEmptyList = DataStoreHelper.INSTANCE.getAllBookmarkedData();
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        checkAndWarnDuplicates(context, LibraryListType.BOOKMARKS, new CheckDuplicateData(loadResponse3.getName(), loadResponse3.getYear(), loadResponse3.getSyncData()), listEmptyList, new Function2() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ResultViewModel2.updateWatchStatus$lambda$1(iIntValue, status, loadResponse3, resultWatchState, this, statusChangedCallback, ((Boolean) obj).booleanValue(), (List) obj2);
            }
        });
    }

    static final Unit updateWatchStatus$lambda$1(int i, WatchType watchType, LoadResponse loadResponse, WatchType watchType2, ResultViewModel2 resultViewModel2, Function1 function1, boolean z, List duplicateIds) {
        Intrinsics.checkNotNullParameter(duplicateIds, "duplicateIds");
        if (!z) {
            return Unit.INSTANCE;
        }
        if (!duplicateIds.isEmpty()) {
            Iterator it = duplicateIds.iterator();
            while (it.hasNext()) {
                DataStoreHelper.INSTANCE.deleteBookmarkedData((Integer) it.next());
            }
        }
        DataStoreHelper.INSTANCE.setResultWatchState(Integer.valueOf(i), watchType.getInternalId());
        if (watchType != WatchType.NONE) {
            DataStoreHelper.BookmarkedData bookmarkedData = DataStoreHelper.INSTANCE.getBookmarkedData(Integer.valueOf(i));
            DataStoreHelper dataStoreHelper = DataStoreHelper.INSTANCE;
            Integer numValueOf = Integer.valueOf(i);
            long bookmarkedTime = bookmarkedData != null ? bookmarkedData.getBookmarkedTime() : APIHolder.INSTANCE.getUnixTimeMS();
            SearchQuality searchQuality = null;
            Map map = null;
            dataStoreHelper.setBookmarkedData(numValueOf, new DataStoreHelper.BookmarkedData(bookmarkedTime, Integer.valueOf(i), APIHolder.INSTANCE.getUnixTimeMS(), loadResponse.getName(), loadResponse.getUrl(), loadResponse.getApiName(), loadResponse.getType(), loadResponse.getPosterUrl(), loadResponse.getYear(), loadResponse.getSyncData(), searchQuality, map, loadResponse.getPlot(), loadResponse.getScore(), loadResponse.getTags(), 3072, null));
        }
        if (watchType2 != watchType) {
            MainActivity.INSTANCE.getBookmarksUpdatedEvent().invoke(true);
            MainActivity.INSTANCE.getReloadLibraryEvent().invoke(true);
        }
        resultViewModel2._watchStatus.postValue(watchType);
        if (function1 != null) {
            function1.invoke(true);
        }
        return Unit.INSTANCE;
    }

    static /* synthetic */ void startChromecast$default(ResultViewModel2 resultViewModel2, Activity activity, ResultEpisode resultEpisode, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        resultViewModel2.startChromecast(activity, resultEpisode, z);
    }

    private final void startChromecast(Activity activity, ResultEpisode result, boolean isVisible) {
        if (activity == null) {
            return;
        }
        loadLinks$default(this, result, isVisible, IGeneratorKt.getLOADTYPE_CHROMECAST(), false, true, new C08931(activity, result, null), 8, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$startChromecast$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "data", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$startChromecast$1", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08931 extends SuspendLambda implements Function3<CoroutineScope, LinkLoadingResult, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        final /* synthetic */ ResultEpisode $result;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08931(Activity activity, ResultEpisode resultEpisode, Continuation<? super C08931> continuation) {
            super(3, continuation);
            this.$activity = activity;
            this.$result = resultEpisode;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, LinkLoadingResult linkLoadingResult, Continuation<? super Unit> continuation) {
            C08931 c08931 = ResultViewModel2.this.new C08931(this.$activity, this.$result, continuation);
            c08931.L$0 = linkLoadingResult;
            return c08931.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            LinkLoadingResult linkLoadingResult = (LinkLoadingResult) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ResultViewModel2.this.startChromecast(this.$activity, this.$result, linkLoadingResult.getLinks(), linkLoadingResult.getSubs(), 0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void toggleSubscriptionStatus$default(ResultViewModel2 resultViewModel2, Context context, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        resultViewModel2.toggleSubscriptionStatus(context, function1);
    }

    public final void toggleSubscriptionStatus(Context context, final Function1<? super Boolean, Unit> statusChangedCallback) {
        Integer num;
        Boolean value = this._subscribeStatus.getValue();
        if (value != null) {
            boolean zBooleanValue = value.booleanValue();
            final LoadResponse loadResponse = this.currentResponse;
            if (loadResponse == null || (num = this.currentId) == null) {
                return;
            }
            final int iIntValue = num.intValue();
            if (zBooleanValue) {
                DataStoreHelper.INSTANCE.removeSubscribedData(num);
                if (statusChangedCallback != null) {
                    statusChangedCallback.invoke(false);
                }
                this._subscribeStatus.postValue(loadResponse instanceof EpisodeResponse ? false : null);
                MainActivity.INSTANCE.getReloadLibraryEvent().invoke(true);
                return;
            }
            if (loadResponse instanceof EpisodeResponse) {
                checkAndWarnDuplicates(context, LibraryListType.SUBSCRIPTIONS, new CheckDuplicateData(loadResponse.getName(), loadResponse.getYear(), loadResponse.getSyncData()), DataStoreHelper.INSTANCE.getAllSubscriptions(), new Function2() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ResultViewModel2.toggleSubscriptionStatus$lambda$0(statusChangedCallback, iIntValue, loadResponse, this, ((Boolean) obj).booleanValue(), (List) obj2);
                    }
                });
            }
        }
    }

    static final Unit toggleSubscriptionStatus$lambda$0(Function1 function1, int i, LoadResponse loadResponse, ResultViewModel2 resultViewModel2, boolean z, List duplicateIds) {
        Intrinsics.checkNotNullParameter(duplicateIds, "duplicateIds");
        if (!z) {
            if (function1 != null) {
                function1.invoke(null);
            }
            return Unit.INSTANCE;
        }
        if (!duplicateIds.isEmpty()) {
            Iterator it = duplicateIds.iterator();
            while (it.hasNext()) {
                DataStoreHelper.INSTANCE.removeSubscribedData((Integer) it.next());
            }
        }
        DataStoreHelper.SubscribedData subscribedData = DataStoreHelper.INSTANCE.getSubscribedData(Integer.valueOf(i));
        DataStoreHelper.INSTANCE.setSubscribedData(Integer.valueOf(i), new DataStoreHelper.SubscribedData(subscribedData != null ? subscribedData.getSubscribedTime() : APIHolder.INSTANCE.getUnixTimeMS(), ((EpisodeResponse) loadResponse).getLatestEpisodes(), Integer.valueOf(i), APIHolder.INSTANCE.getUnixTimeMS(), loadResponse.getName(), loadResponse.getUrl(), loadResponse.getApiName(), loadResponse.getType(), loadResponse.getPosterUrl(), loadResponse.getYear(), loadResponse.getSyncData(), null, null, loadResponse.getPlot(), loadResponse.getScore(), loadResponse.getTags(), 6144, null));
        resultViewModel2._subscribeStatus.postValue(true);
        if (function1 != null) {
            function1.invoke(true);
        }
        MainActivity.INSTANCE.getReloadLibraryEvent().invoke(true);
        return Unit.INSTANCE;
    }

    private final DownloadObjects.DownloadEpisodeMetadata getMeta(ResultEpisode episode, String titleName, String apiName, String currentPoster, boolean currentIsMovie, TvType tvType) {
        int id = episode.getId();
        int parentId = episode.getParentId();
        String strSanitizeFilename$app$default = DownloadFileManagement.sanitizeFilename$app$default(DownloadFileManagement.INSTANCE, titleName, false, 2, null);
        String poster = episode.getPoster();
        if (poster != null) {
            currentPoster = poster;
        }
        return new DownloadObjects.DownloadEpisodeMetadata(id, parentId, strSanitizeFilename$app$default, apiName, currentPoster, episode.getName(), currentIsMovie ? null : episode.getSeason(), currentIsMovie ? null : Integer.valueOf(episode.getEpisode()), tvType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void toggleFavoriteStatus$default(ResultViewModel2 resultViewModel2, Context context, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        resultViewModel2.toggleFavoriteStatus(context, function1);
    }

    public final void toggleFavoriteStatus(Context context, final Function1<? super Boolean, Unit> statusChangedCallback) {
        Integer num;
        Boolean value = this._favoriteStatus.getValue();
        if (value != null) {
            boolean zBooleanValue = value.booleanValue();
            final LoadResponse loadResponse = this.currentResponse;
            if (loadResponse == null || (num = this.currentId) == null) {
                return;
            }
            final int iIntValue = num.intValue();
            if (zBooleanValue) {
                DataStoreHelper.INSTANCE.removeFavoritesData(num);
                if (statusChangedCallback != null) {
                    statusChangedCallback.invoke(false);
                }
                this._favoriteStatus.postValue(false);
                MainActivity.INSTANCE.getReloadLibraryEvent().invoke(true);
                return;
            }
            checkAndWarnDuplicates(context, LibraryListType.FAVORITES, new CheckDuplicateData(loadResponse.getName(), loadResponse.getYear(), loadResponse.getSyncData()), DataStoreHelper.INSTANCE.getAllFavorites(), new Function2() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ResultViewModel2.toggleFavoriteStatus$lambda$0(statusChangedCallback, iIntValue, loadResponse, this, ((Boolean) obj).booleanValue(), (List) obj2);
                }
            });
        }
    }

    static final Unit toggleFavoriteStatus$lambda$0(Function1 function1, int i, LoadResponse loadResponse, ResultViewModel2 resultViewModel2, boolean z, List duplicateIds) {
        Intrinsics.checkNotNullParameter(duplicateIds, "duplicateIds");
        if (!z) {
            if (function1 != null) {
                function1.invoke(null);
            }
            return Unit.INSTANCE;
        }
        if (!duplicateIds.isEmpty()) {
            Iterator it = duplicateIds.iterator();
            while (it.hasNext()) {
                DataStoreHelper.INSTANCE.removeFavoritesData((Integer) it.next());
            }
        }
        DataStoreHelper.FavoritesData favoritesData = DataStoreHelper.INSTANCE.getFavoritesData(Integer.valueOf(i));
        DataStoreHelper.INSTANCE.setFavoritesData(Integer.valueOf(i), new DataStoreHelper.FavoritesData(favoritesData != null ? favoritesData.getFavoritesTime() : APIHolder.INSTANCE.getUnixTimeMS(), Integer.valueOf(i), APIHolder.INSTANCE.getUnixTimeMS(), loadResponse.getName(), loadResponse.getUrl(), loadResponse.getApiName(), loadResponse.getType(), loadResponse.getPosterUrl(), loadResponse.getYear(), loadResponse.getSyncData(), null, null, loadResponse.getPlot(), loadResponse.getScore(), loadResponse.getTags(), 3072, null));
        resultViewModel2._favoriteStatus.postValue(true);
        if (function1 != null) {
            function1.invoke(true);
        }
        MainActivity.INSTANCE.getReloadLibraryEvent().invoke(true);
        return Unit.INSTANCE;
    }

    private final void checkAndWarnDuplicates(final Context context, final LibraryListType listType, CheckDuplicateData checkDuplicateData, List<? extends DataStoreHelper.LibrarySearchResponse> data, final Function2<? super Boolean, ? super List<Integer>, Unit> checkDuplicatesCallback) {
        int i;
        String string;
        int stringRes;
        final Regex regex = new Regex("\\s+");
        Map<String, String> syncData = checkDuplicateData.getSyncData();
        final String imdbIdFromSyncData = getImdbIdFromSyncData(syncData);
        final String tMDbIdFromSyncData = getTMDbIdFromSyncData(syncData);
        final String str = syncData != null ? syncData.get(AccountManager.INSTANCE.getMalApi().getIdPrefix()) : null;
        final String str2 = syncData != null ? syncData.get(AccountManager.INSTANCE.getAniListApi().getIdPrefix()) : null;
        final String strCheckAndWarnDuplicates$normalizeString = checkAndWarnDuplicates$normalizeString(regex, checkDuplicateData.getName());
        Integer year = checkDuplicateData.getYear();
        ArrayList arrayList = new ArrayList();
        for (Object obj : data) {
            final DataStoreHelper.LibrarySearchResponse librarySearchResponse = (DataStoreHelper.LibrarySearchResponse) obj;
            final Map<String, String> syncData2 = librarySearchResponse.getSyncData();
            final boolean z = Intrinsics.areEqual(year, librarySearchResponse.getYear()) || year == null || librarySearchResponse.getYear() == null;
            Integer num = year;
            List listListOf = CollectionsKt.listOf((Object[]) new Function0[]{new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(ResultViewModel2.checkAndWarnDuplicates$lambda$0$0(imdbIdFromSyncData, this, syncData2));
                }
            }, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(ResultViewModel2.checkAndWarnDuplicates$lambda$0$1(tMDbIdFromSyncData, this, syncData2));
                }
            }, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(ResultViewModel2.checkAndWarnDuplicates$lambda$0$2(str, syncData2));
                }
            }, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(ResultViewModel2.checkAndWarnDuplicates$lambda$0$3(str2, syncData2));
                }
            }, new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(ResultViewModel2.checkAndWarnDuplicates$lambda$0$4(strCheckAndWarnDuplicates$normalizeString, librarySearchResponse, z, regex));
                }
            }});
            if (!(listListOf instanceof Collection) || !listListOf.isEmpty()) {
                Iterator it = listListOf.iterator();
                while (it.hasNext()) {
                    if (((Boolean) ((Function0) it.next()).invoke()).booleanValue()) {
                        arrayList.add(obj);
                        break;
                    }
                }
            }
            year = num;
        }
        final ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty() || context == null) {
            checkDuplicatesCallback.invoke(true, CollectionsKt.emptyList());
            return;
        }
        if (arrayList2.size() > 1) {
            i = R.string.duplicate_replace_all;
        } else {
            i = R.string.duplicate_replace;
        }
        if (arrayList2.size() == 1) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[listType.ordinal()];
            if (i2 == 1) {
                DataStoreHelper dataStoreHelper = DataStoreHelper.INSTANCE;
                Integer id = ((DataStoreHelper.LibrarySearchResponse) arrayList2.get(0)).getId();
                stringRes = dataStoreHelper.getResultWatchState(id != null ? id.intValue() : 0).getStringRes();
            } else if (i2 == 2) {
                stringRes = R.string.favorites_list_name;
            } else {
                if (i2 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                stringRes = R.string.subscription_list_name;
            }
            string = context.getString(R.string.duplicate_message_single, checkAndWarnDuplicates$normalizeString(regex, ((DataStoreHelper.LibrarySearchResponse) arrayList2.get(0)).getName()) + " (" + context.getString(stringRes) + ") — " + ((DataStoreHelper.LibrarySearchResponse) arrayList2.get(0)).getApiName());
        } else {
            string = context.getString(R.string.duplicate_message_multiple, CollectionsKt.joinToString$default(arrayList2, "\n", null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return ResultViewModel2.checkAndWarnDuplicates$lambda$1(listType, context, regex, (DataStoreHelper.LibrarySearchResponse) obj2);
                }
            }, 30, null));
        }
        Intrinsics.checkNotNull(string);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i3) {
                ResultViewModel2.checkAndWarnDuplicates$lambda$2(checkDuplicatesCallback, arrayList2, dialogInterface, i3);
            }
        };
        AppContextUtils appContextUtils = AppContextUtils.INSTANCE;
        AlertDialog alertDialogShow = builder.setTitle(R.string.duplicate_title).setMessage(string).setPositiveButton(R.string.duplicate_add, onClickListener).setNegativeButton(R.string.duplicate_cancel, onClickListener).setNeutralButton(i, onClickListener).show();
        Intrinsics.checkNotNullExpressionValue(alertDialogShow, "show(...)");
        AppContextUtils.setDefaultFocus$default(appContextUtils, alertDialogShow, 0, 1, null);
    }

    private static final String checkAndWarnDuplicates$normalizeString(Regex regex, String str) {
        return regex.replace(StringsKt.trim((CharSequence) str).toString(), Stream.ID_UNKNOWN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean checkAndWarnDuplicates$lambda$0$0(String str, ResultViewModel2 resultViewModel2, Map map) {
        return str != null && Intrinsics.areEqual(resultViewModel2.getImdbIdFromSyncData(map), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean checkAndWarnDuplicates$lambda$0$1(String str, ResultViewModel2 resultViewModel2, Map map) {
        return str != null && Intrinsics.areEqual(resultViewModel2.getTMDbIdFromSyncData(map), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean checkAndWarnDuplicates$lambda$0$2(String str, Map map) {
        if (str != null) {
            return Intrinsics.areEqual(map != null ? (String) map.get(AccountManager.INSTANCE.getMalApi().getIdPrefix()) : null, str);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean checkAndWarnDuplicates$lambda$0$3(String str, Map map) {
        if (str != null) {
            return Intrinsics.areEqual(map != null ? (String) map.get(AccountManager.INSTANCE.getAniListApi().getIdPrefix()) : null, str);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean checkAndWarnDuplicates$lambda$0$4(String str, DataStoreHelper.LibrarySearchResponse librarySearchResponse, boolean z, Regex regex) {
        return Intrinsics.areEqual(str, checkAndWarnDuplicates$normalizeString(regex, librarySearchResponse.getName())) && z;
    }

    static final CharSequence checkAndWarnDuplicates$lambda$1(LibraryListType libraryListType, Context context, Regex regex, DataStoreHelper.LibrarySearchResponse it) {
        int stringRes;
        Intrinsics.checkNotNullParameter(it, "it");
        int i = WhenMappings.$EnumSwitchMapping$0[libraryListType.ordinal()];
        if (i == 1) {
            DataStoreHelper dataStoreHelper = DataStoreHelper.INSTANCE;
            Integer id = it.getId();
            stringRes = dataStoreHelper.getResultWatchState(id != null ? id.intValue() : 0).getStringRes();
        } else if (i == 2) {
            stringRes = R.string.favorites_list_name;
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            stringRes = R.string.subscription_list_name;
        }
        return "• " + it.getApiName() + ": " + checkAndWarnDuplicates$normalizeString(regex, it.getName()) + " (" + context.getString(stringRes) + ')';
    }

    static final void checkAndWarnDuplicates$lambda$2(Function2 function2, List list, DialogInterface dialogInterface, int i) {
        if (i != -3) {
            if (i == -2) {
                function2.invoke(false, CollectionsKt.emptyList());
                return;
            } else {
                if (i != -1) {
                    return;
                }
                function2.invoke(true, CollectionsKt.emptyList());
                return;
            }
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((DataStoreHelper.LibrarySearchResponse) it.next()).getId());
        }
        function2.invoke(true, arrayList);
    }

    private final String getImdbIdFromSyncData(final Map<String, String> syncData) {
        return (String) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ResultViewModel2.getImdbIdFromSyncData$lambda$0(syncData);
            }
        });
    }

    static final String getImdbIdFromSyncData$lambda$0(Map map) {
        String str = LoadResponse.INSTANCE.readIdFromString(map != null ? (String) map.get(AccountManager.INSTANCE.getSimklApi().getIdPrefix()) : null).get(SimklSyncServices.Imdb);
        if (Intrinsics.areEqual(str, AbstractJsonLexerKt.NULL)) {
            return null;
        }
        return str;
    }

    private final String getTMDbIdFromSyncData(final Map<String, String> syncData) {
        return (String) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ResultViewModel2.getTMDbIdFromSyncData$lambda$0(syncData);
            }
        });
    }

    static final String getTMDbIdFromSyncData$lambda$0(Map map) {
        String str = LoadResponse.INSTANCE.readIdFromString(map != null ? (String) map.get(AccountManager.INSTANCE.getSimklApi().getIdPrefix()) : null).get(SimklSyncServices.Tmdb);
        if (Intrinsics.areEqual(str, AbstractJsonLexerKt.NULL)) {
            return null;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startChromecast(Activity activity, ResultEpisode result, List<? extends ExtractorLink> links, List<SubtitleData> subs, int startIndex) {
        LoadResponse loadResponse;
        List<ResultEpisode> list;
        if (activity == null || (loadResponse = this.currentResponse) == null) {
            return;
        }
        Map<EpisodeIndexer, ? extends List<ResultEpisode>> map = this.currentEpisodes;
        EpisodeIndexer episodeIndexer = this.currentIndex;
        if (episodeIndexer == null || (list = map.get(episodeIndexer)) == null) {
            return;
        }
        Coroutines.INSTANCE.main(this, new C08942(activity, loadResponse, result, list, links, subs, startIndex, null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$startChromecast$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$startChromecast$2", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08942 extends SuspendLambda implements Function2<ResultViewModel2, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        final /* synthetic */ List<ResultEpisode> $eps;
        final /* synthetic */ List<ExtractorLink> $links;
        final /* synthetic */ LoadResponse $response;
        final /* synthetic */ ResultEpisode $result;
        final /* synthetic */ int $startIndex;
        final /* synthetic */ List<SubtitleData> $subs;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08942(Activity activity, LoadResponse loadResponse, ResultEpisode resultEpisode, List<ResultEpisode> list, List<? extends ExtractorLink> list2, List<SubtitleData> list3, int i, Continuation<? super C08942> continuation) {
            super(2, continuation);
            this.$activity = activity;
            this.$response = loadResponse;
            this.$result = resultEpisode;
            this.$eps = list;
            this.$links = list2;
            this.$subs = list3;
            this.$startIndex = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08942(this.$activity, this.$response, this.$result, this.$eps, this.$links, this.$subs, this.$startIndex, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ResultViewModel2 resultViewModel2, Continuation<? super Unit> continuation) {
            return ((C08942) create(resultViewModel2, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CastSession castSession = CommonActivity.INSTANCE.getCastSession(this.$activity);
            if (castSession != null) {
                Boxing.boxBoolean(CastHelper.INSTANCE.startCast(castSession, this.$response.getApiName(), LoadResponse.INSTANCE.isMovie(this.$response), this.$response.getName(), this.$response.getPosterUrl(), this.$result.getIndex(), this.$eps, this.$links, this.$subs, Boxing.boxInt(this.$startIndex), Boxing.boxLong(ResultFragmentKt.getRealPosition(this.$result))));
            }
            return Unit.INSTANCE;
        }
    }

    public final void cancelLinks() {
        Job job = this.currentLoadLinkJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.currentLoadLinkJob = null;
        this._loadedLinks.postValue(null);
    }

    public final void postPopup(UiText text, List<? extends UiText> options, final Function2<? super Integer, ? super Continuation<? super Unit>, ? extends Object> callback) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this._selectPopup.postValue(new SelectPopup.SelectText(text, options, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultViewModel2.postPopup$lambda$0(this.f$0, callback, (Integer) obj);
            }
        }));
    }

    static final Unit postPopup$lambda$0(ResultViewModel2 resultViewModel2, Function2 function2, Integer num) {
        ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(resultViewModel2), null, null, new ResultViewModel2$postPopup$1$1(resultViewModel2, function2, num, null), 3, null);
        return Unit.INSTANCE;
    }

    private final void postPopupArray(UiText text, List<? extends Pair<? extends UiText, Integer>> options, final Function2<? super Integer, ? super Continuation<? super Unit>, ? extends Object> callback) {
        this._selectPopup.postValue(new SelectPopup.SelectArray(text, options, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultViewModel2.postPopup$lambda$1(this.f$0, callback, (Integer) obj);
            }
        }));
    }

    static final Unit postPopup$lambda$1(ResultViewModel2 resultViewModel2, Function2 function2, Integer num) {
        ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(resultViewModel2), null, null, new ResultViewModel2$postPopup$2$1(resultViewModel2, function2, num, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void loadLinks$default(ResultViewModel2 resultViewModel2, ResultEpisode resultEpisode, boolean z, Set set, boolean z2, boolean z3, Function3 function3, int i, Object obj) {
        if ((i & 4) != 0) {
            set = IGeneratorKt.getLOADTYPE_ALL();
        }
        resultViewModel2.loadLinks(resultEpisode, z, set, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? false : z3, function3);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$loadLinks$1", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08871 extends SuspendLambda implements Function3<CoroutineScope, ResultViewModel2, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $clearCache;
        final /* synthetic */ boolean $isCasting;
        final /* synthetic */ boolean $isVisible;
        final /* synthetic */ ResultEpisode $result;
        final /* synthetic */ Set<ExtractorLinkType> $sourceTypes;
        final /* synthetic */ Function3<CoroutineScope, LinkLoadingResult, Continuation<? super Unit>, Object> $work;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08871(ResultEpisode resultEpisode, boolean z, Set<? extends ExtractorLinkType> set, boolean z2, boolean z3, Function3<? super CoroutineScope, ? super LinkLoadingResult, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super C08871> continuation) {
            super(3, continuation);
            this.$result = resultEpisode;
            this.$isVisible = z;
            this.$sourceTypes = set;
            this.$clearCache = z2;
            this.$isCasting = z3;
            this.$work = function3;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, ResultViewModel2 resultViewModel2, Continuation<? super Unit> continuation) {
            C08871 c08871 = ResultViewModel2.this.new C08871(this.$result, this.$isVisible, this.$sourceTypes, this.$clearCache, this.$isCasting, this.$work, continuation);
            c08871.L$0 = coroutineScope;
            return c08871.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C01751(ResultViewModel2.this, this.$result, this.$isVisible, this.$sourceTypes, this.$clearCache, this.$isCasting, JobKt.getJob(coroutineScope.getCoroutineContext()), this.$work, null), 3, null);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$loadLinks$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ResultViewModel2.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$loadLinks$1$1", f = "ResultViewModel2.kt", i = {0, 1, 1}, l = {1203, 1215}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", CloudStreamPackage.LINKS_EXTRA}, nl = {1212, MimesKt.INITIAL_MIMES_LIST_SIZE}, s = {"L$0", "L$0", "L$1"}, v = 2)
        static final class C01751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $clearCache;
            final /* synthetic */ boolean $isCasting;
            final /* synthetic */ boolean $isVisible;
            final /* synthetic */ Job $parentJob;
            final /* synthetic */ ResultEpisode $result;
            final /* synthetic */ Set<ExtractorLinkType> $sourceTypes;
            final /* synthetic */ Function3<CoroutineScope, LinkLoadingResult, Continuation<? super Unit>, Object> $work;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ ResultViewModel2 this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01751(ResultViewModel2 resultViewModel2, ResultEpisode resultEpisode, boolean z, Set<? extends ExtractorLinkType> set, boolean z2, boolean z3, Job job, Function3<? super CoroutineScope, ? super LinkLoadingResult, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super C01751> continuation) {
                super(2, continuation);
                this.this$0 = resultViewModel2;
                this.$result = resultEpisode;
                this.$isVisible = z;
                this.$sourceTypes = set;
                this.$clearCache = z2;
                this.$isCasting = z3;
                this.$parentJob = job;
                this.$work = function3;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C01751 c01751 = new C01751(this.this$0, this.$result, this.$isVisible, this.$sourceTypes, this.$clearCache, this.$isCasting, this.$parentJob, this.$work, continuation);
                c01751.L$0 = obj;
                return c01751;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:18:0x0066, code lost:
            
                if (r1.invoke(r2, r11, r10) == r0) goto L19;
             */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r11) {
                /*
                    r10 = this;
                    java.lang.Object r0 = r10.L$0
                    r2 = r0
                    kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r10.label
                    r9 = 2
                    r3 = 1
                    if (r1 == 0) goto L27
                    if (r1 == r3) goto L23
                    if (r1 != r9) goto L1b
                    java.lang.Object r10 = r10.L$1
                    com.lagradost.cloudstream3.ui.result.LinkLoadingResult r10 = (com.lagradost.cloudstream3.ui.result.LinkLoadingResult) r10
                    kotlin.ResultKt.throwOnFailure(r11)
                    goto L69
                L1b:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r11)
                    throw r10
                L23:
                    kotlin.ResultKt.throwOnFailure(r11)
                    goto L45
                L27:
                    kotlin.ResultKt.throwOnFailure(r11)
                    com.lagradost.cloudstream3.ui.result.ResultViewModel2 r1 = r10.this$0
                    r11 = r3
                    com.lagradost.cloudstream3.ui.result.ResultEpisode r3 = r10.$result
                    boolean r4 = r10.$isVisible
                    java.util.Set<com.lagradost.cloudstream3.utils.ExtractorLinkType> r5 = r10.$sourceTypes
                    boolean r6 = r10.$clearCache
                    boolean r7 = r10.$isCasting
                    r8 = r10
                    kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                    r10.L$0 = r2
                    r10.label = r11
                    java.lang.Object r11 = com.lagradost.cloudstream3.ui.result.ResultViewModel2.access$loadLinks(r1, r2, r3, r4, r5, r6, r7, r8)
                    if (r11 != r0) goto L45
                    goto L68
                L45:
                    com.lagradost.cloudstream3.ui.result.LinkLoadingResult r11 = (com.lagradost.cloudstream3.ui.result.LinkLoadingResult) r11
                    kotlinx.coroutines.Job r1 = r10.$parentJob
                    boolean r1 = r1.isCancelled()
                    if (r1 == 0) goto L52
                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                    return r10
                L52:
                    kotlin.jvm.functions.Function3<kotlinx.coroutines.CoroutineScope, com.lagradost.cloudstream3.ui.result.LinkLoadingResult, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r10.$work
                    java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
                    r10.L$0 = r3
                    java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
                    r10.L$1 = r3
                    r10.label = r9
                    java.lang.Object r10 = r1.invoke(r2, r11, r10)
                    if (r10 != r0) goto L69
                L68:
                    return r0
                L69:
                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.result.ResultViewModel2.C08871.C01751.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    private final void loadLinks(ResultEpisode result, boolean isVisible, Set<? extends ExtractorLinkType> sourceTypes, boolean clearCache, boolean isCasting, Function3<? super CoroutineScope, ? super LinkLoadingResult, ? super Continuation<? super Unit>, ? extends Object> work) {
        Job job = this.currentLoadLinkJob;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.currentLoadLinkJob = Coroutines.INSTANCE.ioSafe(this, new C08871(result, isVisible, sourceTypes, clearCache, isCasting, work, null));
    }

    static /* synthetic */ void acquireSingleLink$default(ResultViewModel2 resultViewModel2, ResultEpisode resultEpisode, Set set, UiText uiText, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        resultViewModel2.acquireSingleLink(resultEpisode, set, uiText, z, function1);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$acquireSingleLink$1, reason: invalid class name */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", CloudStreamPackage.LINKS_EXTRA, "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$acquireSingleLink$1", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, LinkLoadingResult, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Pair<LinkLoadingResult, Integer>, Unit> $callback;
        final /* synthetic */ UiText $text;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(UiText uiText, Function1<? super Pair<LinkLoadingResult, Integer>, Unit> function1, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$text = uiText;
            this.$callback = function1;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, LinkLoadingResult linkLoadingResult, Continuation<? super Unit> continuation) {
            AnonymousClass1 anonymousClass1 = ResultViewModel2.this.new AnonymousClass1(this.$text, this.$callback, continuation);
            anonymousClass1.L$0 = linkLoadingResult;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            LinkLoadingResult linkLoadingResult = (LinkLoadingResult) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ResultViewModel2 resultViewModel2 = ResultViewModel2.this;
            UiText uiText = this.$text;
            List<ExtractorLink> links = linkLoadingResult.getLinks();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(links, 10));
            for (ExtractorLink extractorLink : links) {
                arrayList.add(TextUtilKt.txt(extractorLink.getName() + ' ' + Qualities.INSTANCE.getStringByInt(Boxing.boxInt(extractorLink.getQuality()))));
            }
            resultViewModel2.postPopup(uiText, arrayList, new AnonymousClass2(this.$callback, linkLoadingResult, null));
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$acquireSingleLink$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: ResultViewModel2.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$acquireSingleLink$1$2", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass2 extends SuspendLambda implements Function2<Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Pair<LinkLoadingResult, Integer>, Unit> $callback;
            final /* synthetic */ LinkLoadingResult $links;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Function1<? super Pair<LinkLoadingResult, Integer>, Unit> function1, LinkLoadingResult linkLoadingResult, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$callback = function1;
                this.$links = linkLoadingResult;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$callback, this.$links, continuation);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Integer num, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(num, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Integer num = (Integer) this.L$0;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Function1<Pair<LinkLoadingResult, Integer>, Unit> function1 = this.$callback;
                LinkLoadingResult linkLoadingResult = this.$links;
                if (num == null) {
                    return Unit.INSTANCE;
                }
                function1.invoke(TuplesKt.to(linkLoadingResult, Boxing.boxInt(num.intValue())));
                return Unit.INSTANCE;
            }
        }
    }

    private final void acquireSingleLink(ResultEpisode result, Set<? extends ExtractorLinkType> sourceTypes, UiText text, boolean isCasting, Function1<? super Pair<LinkLoadingResult, Integer>, Unit> callback) {
        loadLinks$default(this, result, true, sourceTypes, false, isCasting, new AnonymousClass1(text, callback, null), 8, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$acquireSingleSubtitle$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", CloudStreamPackage.LINKS_EXTRA, "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$acquireSingleSubtitle$1", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08811 extends SuspendLambda implements Function3<CoroutineScope, LinkLoadingResult, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Pair<LinkLoadingResult, Integer>, Unit> $callback;
        final /* synthetic */ UiText $text;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08811(UiText uiText, Function1<? super Pair<LinkLoadingResult, Integer>, Unit> function1, Continuation<? super C08811> continuation) {
            super(3, continuation);
            this.$text = uiText;
            this.$callback = function1;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, LinkLoadingResult linkLoadingResult, Continuation<? super Unit> continuation) {
            C08811 c08811 = ResultViewModel2.this.new C08811(this.$text, this.$callback, continuation);
            c08811.L$0 = linkLoadingResult;
            return c08811.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            LinkLoadingResult linkLoadingResult = (LinkLoadingResult) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ResultViewModel2 resultViewModel2 = ResultViewModel2.this;
            UiText uiText = this.$text;
            List<SubtitleData> subs = linkLoadingResult.getSubs();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(subs, 10));
            Iterator<T> it = subs.iterator();
            while (it.hasNext()) {
                arrayList.add(TextUtilKt.txt(((SubtitleData) it.next()).getName()));
            }
            resultViewModel2.postPopup(uiText, arrayList, new AnonymousClass2(this.$callback, linkLoadingResult, null));
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$acquireSingleSubtitle$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: ResultViewModel2.kt */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$acquireSingleSubtitle$1$2", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
        static final class AnonymousClass2 extends SuspendLambda implements Function2<Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Pair<LinkLoadingResult, Integer>, Unit> $callback;
            final /* synthetic */ LinkLoadingResult $links;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Function1<? super Pair<LinkLoadingResult, Integer>, Unit> function1, LinkLoadingResult linkLoadingResult, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$callback = function1;
                this.$links = linkLoadingResult;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$callback, this.$links, continuation);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Integer num, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(num, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Integer num = (Integer) this.L$0;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Function1<Pair<LinkLoadingResult, Integer>, Unit> function1 = this.$callback;
                LinkLoadingResult linkLoadingResult = this.$links;
                if (num == null) {
                    return Unit.INSTANCE;
                }
                function1.invoke(TuplesKt.to(linkLoadingResult, Boxing.boxInt(num.intValue())));
                return Unit.INSTANCE;
            }
        }
    }

    private final void acquireSingleSubtitle(ResultEpisode result, UiText text, Function1<? super Pair<LinkLoadingResult, Integer>, Unit> callback) {
        loadLinks$default(this, result, true, null, false, false, new C08811(text, callback, null), 28, null);
    }

    public final void skipLoading() {
        Job job = this.currentLoadLinkJob;
        if (job != null) {
            JobKt__JobKt.cancelChildren$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.currentLoadLinkJob = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:46:0x0102  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    public final Object loadLinks(final CoroutineScope coroutineScope, ResultEpisode resultEpisode, final boolean z, Set<? extends ExtractorLinkType> set, boolean z2, boolean z3, Continuation<? super LinkLoadingResult> continuation) {
        C08882 c08882;
        LinkedHashSet linkedHashSet;
        final LinkedHashSet linkedHashSet2;
        Set<SubtitleData> set2;
        Set set3;
        LoadResponse loadResponse;
        Map<String, String> mapEmptyMap;
        if (continuation instanceof C08882) {
            c08882 = (C08882) continuation;
            if ((c08882.label & Integer.MIN_VALUE) != 0) {
                c08882.label -= Integer.MIN_VALUE;
            } else {
                c08882 = new C08882(continuation);
            }
        } else {
            c08882 = new C08882(continuation);
        }
        C08882 c08883 = c08882;
        Object obj = c08883.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08883.label;
        LoadResponse loadResponse2 = null;
        Object[] objArr = 0;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                RepoLinkGenerator repoLinkGenerator = new RepoLinkGenerator(CollectionsKt.listOf(resultEpisode), loadResponse2, 2, objArr == true ? 1 : 0);
                final LinkedHashSet linkedHashSet3 = new LinkedHashSet();
                final LinkedHashSet linkedHashSet4 = new LinkedHashSet();
                try {
                    loadLinks$updatePage(z, coroutineScope, this, linkedHashSet3, linkedHashSet4);
                    Function1<? super Pair<? extends ExtractorLink, ExtractorUri>, Unit> function1 = new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda21
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return ResultViewModel2.loadLinks$lambda$0(linkedHashSet3, z, coroutineScope, this, linkedHashSet4, (Pair) obj2);
                        }
                    };
                    linkedHashSet2 = linkedHashSet4;
                    try {
                        Function1<? super SubtitleData, Unit> function2 = new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda22
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return ResultViewModel2.loadLinks$lambda$1(linkedHashSet2, z, coroutineScope, this, linkedHashSet3, (SubtitleData) obj2);
                            }
                        };
                        linkedHashSet = linkedHashSet3;
                        try {
                            c08883.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                            c08883.L$1 = SpillingKt.nullOutSpilledVariable(resultEpisode);
                            c08883.L$2 = SpillingKt.nullOutSpilledVariable(set);
                            c08883.L$3 = SpillingKt.nullOutSpilledVariable(repoLinkGenerator);
                            c08883.L$4 = linkedHashSet;
                            c08883.L$5 = linkedHashSet2;
                            c08883.Z$0 = z;
                            c08883.Z$1 = z2;
                            c08883.Z$2 = z3;
                            c08883.label = 1;
                            if (repoLinkGenerator.generateLinks(z2, set, function1, function2, 0, z3, c08883) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } catch (CancellationException unused) {
                        } catch (Exception e) {
                            e = e;
                            set2 = linkedHashSet2;
                            set3 = linkedHashSet;
                            ArchComponentExtKt.logError(e);
                            this._loadedLinks.postValue(null);
                            List<ExtractorLink> listSortUrls = MainAPIKt.sortUrls(set3);
                            List<SubtitleData> listSortSubs = AppContextUtils.INSTANCE.sortSubs(set2);
                            loadResponse = this.currentResponse;
                            if (loadResponse != null) {
                                mapEmptyMap = MapsKt.emptyMap();
                            } else {
                                mapEmptyMap = MapsKt.emptyMap();
                            }
                            return new LinkLoadingResult(listSortUrls, listSortSubs, new HashMap(mapEmptyMap));
                        }
                    } catch (CancellationException unused2) {
                        linkedHashSet = linkedHashSet3;
                    } catch (Exception e2) {
                        e = e2;
                        linkedHashSet = linkedHashSet3;
                    }
                } catch (CancellationException unused3) {
                    linkedHashSet = linkedHashSet3;
                    linkedHashSet2 = linkedHashSet4;
                } catch (Exception e3) {
                    e = e3;
                    linkedHashSet = linkedHashSet3;
                    linkedHashSet2 = linkedHashSet4;
                }
                set2 = linkedHashSet2;
                set3 = linkedHashSet;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                boolean z4 = c08883.Z$2;
                boolean z5 = c08883.Z$1;
                boolean z6 = c08883.Z$0;
                set2 = (Set) c08883.L$5;
                set3 = (Set) c08883.L$4;
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (CancellationException unused4) {
                } catch (Exception e4) {
                    e = e4;
                    ArchComponentExtKt.logError(e);
                }
            }
            this._loadedLinks.postValue(null);
            List<ExtractorLink> listSortUrls2 = MainAPIKt.sortUrls(set3);
            List<SubtitleData> listSortSubs2 = AppContextUtils.INSTANCE.sortSubs(set2);
            loadResponse = this.currentResponse;
            if (loadResponse != null || (mapEmptyMap = loadResponse.getSyncData()) == null) {
                mapEmptyMap = MapsKt.emptyMap();
            }
            return new LinkLoadingResult(listSortUrls2, listSortSubs2, new HashMap(mapEmptyMap));
        } catch (Throwable th) {
            this._loadedLinks.postValue(null);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object loadLinks$default(ResultViewModel2 resultViewModel2, CoroutineScope coroutineScope, ResultEpisode resultEpisode, boolean z, Set set, boolean z2, boolean z3, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            set = IGeneratorKt.getLOADTYPE_ALL();
        }
        return resultViewModel2.loadLinks(coroutineScope, resultEpisode, z, set, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? false : z3, continuation);
    }

    private static final void loadLinks$updatePage(boolean z, CoroutineScope coroutineScope, ResultViewModel2 resultViewModel2, Set<ExtractorLink> set, Set<SubtitleData> set2) {
        if (z && CoroutineScopeKt.isActive(coroutineScope)) {
            resultViewModel2._loadedLinks.postValue(new LinkProgress(set.size(), set2.size()));
        }
    }

    static final Unit loadLinks$lambda$0(Set set, boolean z, CoroutineScope coroutineScope, ResultViewModel2 resultViewModel2, Set set2, Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<destruct>");
        ExtractorLink extractorLink = (ExtractorLink) pair.component1();
        if (extractorLink != null) {
            set.add(extractorLink);
            loadLinks$updatePage(z, coroutineScope, resultViewModel2, set, set2);
        }
        return Unit.INSTANCE;
    }

    static final Unit loadLinks$lambda$1(Set set, boolean z, CoroutineScope coroutineScope, ResultViewModel2 resultViewModel2, Set set2, SubtitleData sub) {
        Intrinsics.checkNotNullParameter(sub, "sub");
        set.add(sub);
        loadLinks$updatePage(z, coroutineScope, resultViewModel2, set2, set);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleAction$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleAction$1", f = "ResultViewModel2.kt", i = {}, l = {1316}, m = "invokeSuspend", n = {}, nl = {1317}, s = {}, v = 2)
    static final class C08831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ EpisodeClickEvent $click;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08831(EpisodeClickEvent episodeClickEvent, Continuation<? super C08831> continuation) {
            super(2, continuation);
            this.$click = episodeClickEvent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ResultViewModel2.this.new C08831(this.$click, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (ResultViewModel2.this.handleEpisodeClickEvent(this.$click, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final Job handleAction(EpisodeClickEvent click) {
        Intrinsics.checkNotNullParameter(click, "click");
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C08831(click, null), 3, null);
    }

    public final void releaseEpisodeSynopsis() {
        this._episodeSynopsis.postValue(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markEpisodes(Editor editor, String[] episodeIds, VideoWatchState watchState) {
        String json = AppUtils.INSTANCE.toJson(watchState);
        for (String str : episodeIds) {
            if (DataStoreHelper.INSTANCE.getVideoWatchState(Integer.valueOf(Integer.parseInt(str))) != watchState) {
                editor.setKeyRaw(DataStore.INSTANCE.getFolderName(DataStoreHelper.INSTANCE.m8635getCurrentAccount() + "/video_watch_state", str), json);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HashMap<Integer, String[]> getEpisodesIdsBySeason(final int season) {
        Sequence<Pair> sequenceFlatMap = SequencesKt.flatMap(SequencesKt.filter(CollectionsKt.asSequence(this.currentEpisodes.entrySet()), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ResultViewModel2.getEpisodesIdsBySeason$lambda$0(season, this, (Map.Entry) obj));
            }
        }), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultViewModel2.getEpisodesIdsBySeason$lambda$1((Map.Entry) obj);
            }
        });
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Pair pair : sequenceFlatMap) {
            Integer numValueOf = Integer.valueOf(((Number) pair.getFirst()).intValue());
            Object obj = linkedHashMap.get(numValueOf);
            if (obj == null) {
                obj = (List) new ArrayList();
                linkedHashMap.put(numValueOf, obj);
            }
            ((List) obj).add((String) pair.getSecond());
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry.getKey(), (String[]) ((List) entry.getValue()).toArray(new String[0]));
        }
        HashMap<Integer, String[]> map = (HashMap) MapsKt.toMap(linkedHashMap2, new HashMap());
        if (season != 0) {
            map.remove(0);
        }
        return map;
    }

    static final boolean getEpisodesIdsBySeason$lambda$0(int i, ResultViewModel2 resultViewModel2, Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return ((EpisodeIndexer) it.getKey()).getSeason() <= i && ((EpisodeIndexer) it.getKey()).getDubStatus() == resultViewModel2.preferDubStatus;
    }

    static final Sequence getEpisodesIdsBySeason$lambda$1(final Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "entry");
        return SequencesKt.map(CollectionsKt.asSequence((Iterable) entry.getValue()), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ResultViewModel2.getEpisodesIdsBySeason$lambda$1$0(entry, (ResultEpisode) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair getEpisodesIdsBySeason$lambda$1$0(Map.Entry entry, ResultEpisode it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return TuplesKt.to(Integer.valueOf(((EpisodeIndexer) entry.getKey()).getSeason()), String.valueOf(it.getId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object handleEpisodeClickEvent(final EpisodeClickEvent episodeClickEvent, Continuation<? super Unit> continuation) {
        Map<String, String> mapEmptyMap;
        String nameFull;
        int i;
        int i2;
        int i3 = 0;
        switch (episodeClickEvent.getAction()) {
            case 1:
                LoadResponse loadResponse = this.currentResponse;
                if (loadResponse == null || (mapEmptyMap = loadResponse.getSyncData()) == null) {
                    mapEmptyMap = MapsKt.emptyMap();
                }
                HashMap<String, String> map = new HashMap<>(mapEmptyMap);
                RepoLinkGenerator repoLinkGenerator = this.generator;
                if (repoLinkGenerator == null) {
                    return Unit.INSTANCE;
                }
                Iterator<ResultEpisode> it = repoLinkGenerator.getVideos().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        i3 = -1;
                    } else if (it.next().getId() != episodeClickEvent.getData().getId()) {
                        i3++;
                    }
                }
                LoadResponse loadResponse2 = this.currentResponse;
                if ((loadResponse2 != null ? loadResponse2.getType() : null) == TvType.CustomMedia) {
                    Object objGenerateLinks = repoLinkGenerator.generateLinks(true, IGeneratorKt.getLOADTYPE_ALL(), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ResultViewModel2.handleEpisodeClickEvent$lambda$5((Pair) obj);
                        }
                    }, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ResultViewModel2.handleEpisodeClickEvent$lambda$6((SubtitleData) obj);
                        }
                    }, i3, false, continuation);
                    return objGenerateLinks == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objGenerateLinks : Unit.INSTANCE;
                }
                Activity activity = CommonActivity.INSTANCE.getActivity();
                if (activity != null) {
                    UIHelper.navigate$default(UIHelper.INSTANCE, activity, R.id.global_to_navigation_player, GeneratorPlayer.INSTANCE.newInstance(repoLinkGenerator, i3, map), null, 4, null);
                }
                break;
                break;
            case 2:
            case 3:
            case 9:
            case 13:
            case 16:
            case 17:
            default:
                final VideoClickAction actionById = VideoClickActionHolder.INSTANCE.getActionById(episodeClickEvent.getAction());
                if (actionById == null) {
                    return Unit.INSTANCE;
                }
                if (actionById instanceof AlwaysAskAction) {
                    Activity activity2 = CommonActivity.INSTANCE.getActivity();
                    if (activity2 != null) {
                        AtomicList<VideoClickAction> players = VideoClickActionHolder.INSTANCE.getPlayers(activity2);
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(TuplesKt.to(TextUtilKt.txt(R.string.episode_action_play_in_app, new Object[0]), Boxing.boxInt(1)));
                        AtomicList<VideoClickAction> atomicListFilter = players.filter(new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda12
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return Boolean.valueOf(ResultViewModel2.handleEpisodeClickEvent$lambda$7$0((VideoClickAction) obj));
                            }
                        });
                        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(atomicListFilter, 10));
                        for (VideoClickAction videoClickAction : atomicListFilter) {
                            UiText name = videoClickAction.getName();
                            Integer numUniqueIdToId = VideoClickActionHolder.INSTANCE.uniqueIdToId(videoClickAction.uniqueId());
                            arrayList2.add(TuplesKt.to(name, Boxing.boxInt(numUniqueIdToId != null ? numUniqueIdToId.intValue() : 1)));
                        }
                        arrayList.addAll(arrayList2);
                        postPopupArray(TextUtilKt.txt(R.string.player_pref, new Object[0]), arrayList, new ResultViewModel2$handleEpisodeClickEvent$11$3(this, episodeClickEvent, null));
                    }
                    return Unit.INSTANCE;
                }
                Activity activity3 = CommonActivity.INSTANCE.getActivity();
                if (activity3 != null) {
                    DataStore.INSTANCE.setKey(activity3, "last_click_action", actionById.uniqueId());
                }
                if (actionById.getOneSource()) {
                    acquireSingleLink$default(this, episodeClickEvent.getData(), actionById.getSourceTypes(), actionById.getName(), false, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ResultViewModel2.handleEpisodeClickEvent$lambda$8(actionById, episodeClickEvent, (Pair) obj);
                        }
                    }, 8, null);
                } else {
                    loadLinks$default(this, episodeClickEvent.getData(), true, actionById.getSourceTypes(), false, false, new AnonymousClass13(actionById, episodeClickEvent, null), 24, null);
                }
                break;
                break;
            case 4:
                startChromecast$default(this, CommonActivity.INSTANCE.getActivity(), episodeClickEvent.getData(), false, 4, null);
                break;
            case 5:
                acquireSingleLink(episodeClickEvent.getData(), IGeneratorKt.getLOADTYPE_CHROMECAST(), TextUtilKt.txt(R.string.episode_action_chromecast_mirror, new Object[0]), true, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ResultViewModel2.handleEpisodeClickEvent$lambda$3(this.f$0, episodeClickEvent, (Pair) obj);
                    }
                });
                break;
            case 6:
                LoadResponse loadResponse3 = this.currentResponse;
                if (loadResponse3 == null) {
                    return Unit.INSTANCE;
                }
                DownloadQueueManager.INSTANCE.addToQueue(new DownloadObjects.DownloadQueueItem(episodeClickEvent.getData(), LoadResponse.INSTANCE.isMovie(loadResponse3), loadResponse3.getName(), loadResponse3.getType(), loadResponse3.getPosterUrl(), loadResponse3.getApiName(), ResultViewModel2Kt.getId(loadResponse3), loadResponse3.getUrl(), null, null, ViewUtils.EDGE_TO_EDGE_FLAGS, null).toWrapper());
                break;
                break;
            case 7:
                final LoadResponse loadResponse4 = this.currentResponse;
                if (loadResponse4 == null) {
                    return Unit.INSTANCE;
                }
                acquireSingleLink$default(this, episodeClickEvent.getData(), IGeneratorKt.getLOADTYPE_INAPP_DOWNLOAD(), TextUtilKt.txt(R.string.episode_action_download_mirror, new Object[0]), false, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ResultViewModel2.handleEpisodeClickEvent$lambda$2(episodeClickEvent, loadResponse4, (Pair) obj);
                    }
                }, 8, null);
                break;
                break;
            case 8:
                Coroutines.INSTANCE.ioSafe(this, new AnonymousClass6(episodeClickEvent, null));
                CommonActivity.INSTANCE.showToast(R.string.links_reloaded_toast, Boxing.boxInt(0));
                break;
            case 10:
                ArrayList arrayList3 = new ArrayList();
                Activity activity4 = CommonActivity.INSTANCE.getActivity();
                if (activity4 != null && AppContextUtils.INSTANCE.isConnectedToChromecast(activity4)) {
                    arrayList3.addAll(CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(TextUtilKt.txt(R.string.episode_action_chromecast_episode, new Object[0]), Boxing.boxInt(4)), TuplesKt.to(TextUtilKt.txt(R.string.episode_action_chromecast_mirror, new Object[0]), Boxing.boxInt(5))}));
                }
                arrayList3.add(TuplesKt.to(TextUtilKt.txt(R.string.episode_action_play_in_app, new Object[0]), Boxing.boxInt(1)));
                arrayList3.addAll(CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(TextUtilKt.txt(R.string.episode_action_auto_download, new Object[0]), Boxing.boxInt(6)), TuplesKt.to(TextUtilKt.txt(R.string.episode_action_download_mirror, new Object[0]), Boxing.boxInt(7)), TuplesKt.to(TextUtilKt.txt(R.string.episode_action_download_subtitle, new Object[0]), Boxing.boxInt(14)), TuplesKt.to(TextUtilKt.txt(R.string.episode_action_reload_links, new Object[0]), Boxing.boxInt(8))}));
                arrayList3.addAll(VideoClickActionHolder.INSTANCE.makeOptionMap(CommonActivity.INSTANCE.getActivity(), episodeClickEvent.getData()));
                if (!CollectionsKt.listOf((Object[]) new TvType[]{TvType.Movie, TvType.AnimeMovie}).contains(episodeClickEvent.getData().getTvType())) {
                    boolean z = DataStoreHelper.INSTANCE.getVideoWatchState(Boxing.boxInt(episodeClickEvent.getData().getId())) == VideoWatchState.Watched;
                    if (z) {
                        i = R.string.action_remove_from_watched;
                    } else {
                        i = R.string.action_mark_as_watched;
                    }
                    if (z) {
                        i2 = R.string.action_remove_mark_watched_up_to_this_episode;
                    } else {
                        i2 = R.string.action_mark_watched_up_to_this_episode;
                    }
                    arrayList3.add(TuplesKt.to(TextUtilKt.txt(i, new Object[0]), Boxing.boxInt(18)));
                    arrayList3.add(TuplesKt.to(TextUtilKt.txt(i2, new Object[0]), Boxing.boxInt(19)));
                }
                Activity activity5 = CommonActivity.INSTANCE.getActivity();
                if (activity5 == null || (nameFull = AppContextUtils.INSTANCE.getNameFull(activity5, episodeClickEvent.getData().getName(), Boxing.boxInt(episodeClickEvent.getData().getEpisode()), episodeClickEvent.getData().getSeason())) == null) {
                    nameFull = "";
                }
                postPopupArray(TextUtilKt.txt(nameFull), arrayList3, new C08852(episodeClickEvent, null));
                break;
            case 11:
                Activity activity6 = CommonActivity.INSTANCE.getActivity();
                if (activity6 != null) {
                    Activity activity7 = activity6;
                    if (AppContextUtils.INSTANCE.isConnectedToChromecast(activity7)) {
                        Object objHandleEpisodeClickEvent = handleEpisodeClickEvent(EpisodeClickEvent.copy$default(episodeClickEvent, null, 4, null, 5, null), continuation);
                        if (objHandleEpisodeClickEvent == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            return objHandleEpisodeClickEvent;
                        }
                    } else {
                        Object objHandleEpisodeClickEvent2 = handleEpisodeClickEvent(EpisodeClickEvent.copy$default(episodeClickEvent, null, EpisodeAdapter.INSTANCE.getPlayerAction(activity7), null, 5, null), continuation);
                        if (objHandleEpisodeClickEvent2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            return objHandleEpisodeClickEvent2;
                        }
                    }
                }
                break;
            case 12:
                CommonActivity.INSTANCE.showToast(R.string.play_episode_toast, Boxing.boxInt(0));
                break;
            case 14:
                final LoadResponse loadResponse5 = this.currentResponse;
                if (loadResponse5 == null) {
                    return Unit.INSTANCE;
                }
                acquireSingleSubtitle(episodeClickEvent.getData(), TextUtilKt.txt(R.string.episode_action_download_subtitle, new Object[0]), new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ResultViewModel2.handleEpisodeClickEvent$lambda$1(this.f$0, episodeClickEvent, loadResponse5, (Pair) obj);
                    }
                });
                break;
                break;
            case 15:
                this._episodeSynopsis.postValue(episodeClickEvent.getData().getDescription());
                break;
            case 18:
                if (DataStoreHelper.INSTANCE.getVideoWatchState(Boxing.boxInt(episodeClickEvent.getData().getId())) == VideoWatchState.Watched) {
                    DataStoreHelper.INSTANCE.setVideoWatchState(Boxing.boxInt(episodeClickEvent.getData().getId()), VideoWatchState.None);
                } else {
                    DataStoreHelper.INSTANCE.setVideoWatchState(Boxing.boxInt(episodeClickEvent.getData().getId()), VideoWatchState.Watched);
                }
                reloadEpisodes();
                break;
            case 19:
                Coroutines.INSTANCE.ioSafe(this, new AnonymousClass10(episodeClickEvent, this, null));
                break;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleEpisodeClickEvent$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n"}, d2 = {"<anonymous>", "", "result", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleEpisodeClickEvent$2", f = "ResultViewModel2.kt", i = {0}, l = {1411}, m = "invokeSuspend", n = {"result"}, nl = {1414}, s = {"L$0"}, v = 2)
    static final class C08852 extends SuspendLambda implements Function2<Integer, Continuation<? super Unit>, Object> {
        final /* synthetic */ EpisodeClickEvent $click;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08852(EpisodeClickEvent episodeClickEvent, Continuation<? super C08852> continuation) {
            super(2, continuation);
            this.$click = episodeClickEvent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08852 c08852 = ResultViewModel2.this.new C08852(this.$click, continuation);
            c08852.L$0 = obj;
            return c08852;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Integer num, Continuation<? super Unit> continuation) {
            return ((C08852) create(num, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Integer num = (Integer) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ResultViewModel2 resultViewModel2 = ResultViewModel2.this;
                EpisodeClickEvent episodeClickEvent = this.$click;
                if (num != null) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(num);
                    this.label = 1;
                    if (resultViewModel2.handleEpisodeClickEvent(EpisodeClickEvent.copy$default(episodeClickEvent, null, num.intValue(), null, 5, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    static final Unit handleEpisodeClickEvent$lambda$1(ResultViewModel2 resultViewModel2, EpisodeClickEvent episodeClickEvent, LoadResponse loadResponse, Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<destruct>");
        LinkLoadingResult linkLoadingResult = (LinkLoadingResult) pair.component1();
        DownloadUtils.INSTANCE.downloadSubtitle(CommonActivity.INSTANCE.getActivity(), linkLoadingResult.getSubs().get(((Number) pair.component2()).intValue()), resultViewModel2.getMeta(episodeClickEvent.getData(), loadResponse.getName(), loadResponse.getApiName(), loadResponse.getPosterUrl(), LoadResponse.INSTANCE.isMovie(loadResponse), loadResponse.getType()));
        CommonActivity.INSTANCE.showToast(R.string.download_started, (Integer) 0);
        return Unit.INSTANCE;
    }

    static final Unit handleEpisodeClickEvent$lambda$2(EpisodeClickEvent episodeClickEvent, LoadResponse loadResponse, Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<destruct>");
        LinkLoadingResult linkLoadingResult = (LinkLoadingResult) pair.component1();
        DownloadQueueManager.INSTANCE.addToQueue(new DownloadObjects.DownloadQueueItem(episodeClickEvent.getData(), LoadResponse.INSTANCE.isMovie(loadResponse), loadResponse.getName(), loadResponse.getType(), loadResponse.getPosterUrl(), loadResponse.getApiName(), ResultViewModel2Kt.getId(loadResponse), loadResponse.getUrl(), CollectionsKt.listOf(linkLoadingResult.getLinks().get(((Number) pair.component2()).intValue())), linkLoadingResult.getSubs()).toWrapper());
        CommonActivity.INSTANCE.showToast(R.string.download_started, (Integer) 0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleEpisodeClickEvent$6, reason: invalid class name */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleEpisodeClickEvent$6", f = "ResultViewModel2.kt", i = {0}, l = {1518}, m = "invokeSuspend", n = {"$this$ioSafe"}, nl = {1524}, s = {"L$0"}, v = 2)
    static final class AnonymousClass6 extends SuspendLambda implements Function3<CoroutineScope, ResultViewModel2, Continuation<? super Unit>, Object> {
        final /* synthetic */ EpisodeClickEvent $click;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass6(EpisodeClickEvent episodeClickEvent, Continuation<? super AnonymousClass6> continuation) {
            super(3, continuation);
            this.$click = episodeClickEvent;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, ResultViewModel2 resultViewModel2, Continuation<? super Unit> continuation) {
            AnonymousClass6 anonymousClass6 = ResultViewModel2.this.new AnonymousClass6(this.$click, continuation);
            anonymousClass6.L$0 = coroutineScope;
            return anonymousClass6.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.label = 1;
                if (ResultViewModel2.loadLinks$default(ResultViewModel2.this, coroutineScope, this.$click.getData(), false, IGeneratorKt.getLOADTYPE_INAPP(), true, false, this, 16, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    static final Unit handleEpisodeClickEvent$lambda$3(ResultViewModel2 resultViewModel2, EpisodeClickEvent episodeClickEvent, Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<destruct>");
        LinkLoadingResult linkLoadingResult = (LinkLoadingResult) pair.component1();
        resultViewModel2.startChromecast(CommonActivity.INSTANCE.getActivity(), episodeClickEvent.getData(), linkLoadingResult.getLinks(), linkLoadingResult.getSubs(), ((Number) pair.component2()).intValue());
        return Unit.INSTANCE;
    }

    static final Unit handleEpisodeClickEvent$lambda$5(Pair it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit handleEpisodeClickEvent$lambda$6(SubtitleData it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleEpisodeClickEvent$10, reason: invalid class name */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleEpisodeClickEvent$10", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass10 extends SuspendLambda implements Function3<CoroutineScope, ResultViewModel2, Continuation<? super Unit>, Object> {
        final /* synthetic */ EpisodeClickEvent $click;
        int label;
        final /* synthetic */ ResultViewModel2 this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass10(EpisodeClickEvent episodeClickEvent, ResultViewModel2 resultViewModel2, Continuation<? super AnonymousClass10> continuation) {
            super(3, continuation);
            this.$click = episodeClickEvent;
            this.this$0 = resultViewModel2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, ResultViewModel2 resultViewModel2, Continuation<? super Unit> continuation) {
            return new AnonymousClass10(this.$click, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Context context = CloudStreamApp.INSTANCE.getContext();
            Editor editor = context != null ? DataStore.INSTANCE.editor(context, false) : null;
            if (editor != null) {
                ResultEpisode data = this.$click.getData();
                Integer season = data.getSeason();
                Pair pair = TuplesKt.to(Boxing.boxInt(season != null ? season.intValue() : 0), Boxing.boxInt(data.getEpisode()));
                int iIntValue = ((Number) pair.component1()).intValue();
                int iIntValue2 = ((Number) pair.component2()).intValue();
                VideoWatchState videoWatchState = DataStoreHelper.INSTANCE.getVideoWatchState(Boxing.boxInt(this.$click.getData().getId())) == VideoWatchState.Watched ? VideoWatchState.None : VideoWatchState.Watched;
                HashMap episodesIdsBySeason = this.this$0.getEpisodesIdsBySeason(iIntValue);
                Set<Integer> setKeySet = episodesIdsBySeason.keySet();
                Intrinsics.checkNotNullExpressionValue(setKeySet, "<get-keys>(...)");
                ResultViewModel2 resultViewModel2 = this.this$0;
                for (Integer num : setKeySet) {
                    String[] strArr = (String[]) episodesIdsBySeason.get(num);
                    if (strArr == null) {
                        strArr = new String[0];
                    }
                    if (num != null && num.intValue() == iIntValue) {
                        strArr = (String[]) ArraysKt.sliceArray(strArr, RangesKt.until(0, iIntValue2));
                    }
                    resultViewModel2.markEpisodes(editor, strArr, videoWatchState);
                }
                editor.apply();
                this.this$0.reloadEpisodes();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean handleEpisodeClickEvent$lambda$7$0(VideoClickAction it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !(it instanceof AlwaysAskAction);
    }

    static final Unit handleEpisodeClickEvent$lambda$8(VideoClickAction videoClickAction, EpisodeClickEvent episodeClickEvent, Pair pair) {
        Intrinsics.checkNotNullParameter(pair, "<destruct>");
        videoClickAction.runActionSafe(CommonActivity.INSTANCE.getActivity(), episodeClickEvent.getData(), (LinkLoadingResult) pair.component1(), Integer.valueOf(((Number) pair.component2()).intValue()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleEpisodeClickEvent$13, reason: invalid class name */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", CloudStreamPackage.LINKS_EXTRA, "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleEpisodeClickEvent$13", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass13 extends SuspendLambda implements Function3<CoroutineScope, LinkLoadingResult, Continuation<? super Unit>, Object> {
        final /* synthetic */ VideoClickAction $action;
        final /* synthetic */ EpisodeClickEvent $click;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass13(VideoClickAction videoClickAction, EpisodeClickEvent episodeClickEvent, Continuation<? super AnonymousClass13> continuation) {
            super(3, continuation);
            this.$action = videoClickAction;
            this.$click = episodeClickEvent;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, LinkLoadingResult linkLoadingResult, Continuation<? super Unit> continuation) {
            AnonymousClass13 anonymousClass13 = new AnonymousClass13(this.$action, this.$click, continuation);
            anonymousClass13.L$0 = linkLoadingResult;
            return anonymousClass13.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            LinkLoadingResult linkLoadingResult = (LinkLoadingResult) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$action.runActionSafe(CommonActivity.INSTANCE.getActivity(), this.$click.getData(), linkLoadingResult, null);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object applyMeta(LoadResponse loadResponse, SyncAPI.SyncResult syncResult, Map<String, String> map, Continuation<? super Pair<? extends LoadResponse, Boolean>> continuation) {
        C08821 c08821;
        Ref.BooleanRef booleanRef;
        ArrayList list;
        Set setUnion;
        LoadResponse loadResponse2 = loadResponse;
        if (continuation instanceof C08821) {
            c08821 = (C08821) continuation;
            if ((c08821.label & Integer.MIN_VALUE) != 0) {
                c08821.label -= Integer.MIN_VALUE;
            } else {
                c08821 = new C08821(continuation);
            }
        } else {
            c08821 = new C08821(continuation);
        }
        Object obj = c08821.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08821.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            Log.i(TAG, "applyMeta");
            if (syncResult != null) {
                Integer duration = loadResponse2.getDuration();
                if (duration == null) {
                    duration = syncResult.getDuration();
                }
                loadResponse2.setDuration(duration);
                Score score = loadResponse2.getScore();
                if (score == null) {
                    score = syncResult.getPublicScore();
                }
                loadResponse2.setScore(score);
                List<String> tags = loadResponse2.getTags();
                if (tags == null) {
                    tags = syncResult.getGenres();
                }
                loadResponse2.setTags(tags);
                String plot = loadResponse2.getPlot();
                loadResponse2.setPlot((plot == null || StringsKt.isBlank(plot)) ? syncResult.getSynopsis() : loadResponse2.getPlot());
                String posterUrl = loadResponse2.getPosterUrl();
                if (posterUrl == null && (posterUrl = syncResult.getPosterUrl()) == null) {
                    posterUrl = syncResult.getBackgroundPosterUrl();
                }
                loadResponse2.setPosterUrl(posterUrl);
                List<ActorData> actors = loadResponse2.getActors();
                if (actors == null) {
                    actors = syncResult.getActors();
                }
                loadResponse2.setActors(actors);
                if (loadResponse2 instanceof EpisodeResponse) {
                    EpisodeResponse episodeResponse = (EpisodeResponse) loadResponse2;
                    NextAiring nextAiring = episodeResponse.getNextAiring();
                    if (nextAiring == null) {
                        nextAiring = syncResult.getNextAiring();
                    }
                    episodeResponse.setNextAiring(nextAiring);
                }
                ArrayList arrayList = new ArrayList();
                AtomicList<MainAPI> atomicListFilter = APIHolder.INSTANCE.getApis().filter(new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return Boolean.valueOf(ResultViewModel2.applyMeta$lambda$0$0((MainAPI) obj2));
                    }
                });
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(atomicListFilter, 10));
                Iterator<MainAPI> it = atomicListFilter.iterator();
                while (it.hasNext()) {
                    arrayList2.add(it.next().getName());
                }
                ArrayList arrayList3 = arrayList2;
                List<SyncAPI.SyncSearchResult> recommendations = syncResult.getRecommendations();
                if (recommendations != null) {
                    for (SyncAPI.SyncSearchResult syncSearchResult : recommendations) {
                        Iterator it2 = arrayList3.iterator();
                        while (it2.hasNext()) {
                            arrayList.add(SyncAPI.SyncSearchResult.copy$default(syncSearchResult, null, (String) it2.next(), null, null, null, null, null, null, null, null, 1021, null));
                        }
                    }
                }
                List<SearchResponse> recommendations2 = loadResponse2.getRecommendations();
                if (recommendations2 == null || (setUnion = CollectionsKt.union(recommendations2, arrayList)) == null || (list = CollectionsKt.toList(setUnion)) == null) {
                    list = arrayList;
                }
                loadResponse2.setRecommendations(list);
            }
            for (Map.Entry<String, String> entry : (map == null ? MapsKt.emptyMap() : map).entrySet()) {
                loadResponse2.getSyncData().put(entry.getKey(), entry.getValue());
            }
            Function1[] function1Arr = {new ResultViewModel2$applyMeta$out$1$2(loadResponse2, null), new ResultViewModel2$applyMeta$out$1$3(syncResult, loadResponse2, null), new ResultViewModel2$applyMeta$out$1$4(loadResponse2, booleanRef2, null)};
            c08821.L$0 = SpillingKt.nullOutSpilledVariable(loadResponse2);
            c08821.L$1 = SpillingKt.nullOutSpilledVariable(syncResult);
            c08821.L$2 = SpillingKt.nullOutSpilledVariable(map);
            c08821.L$3 = booleanRef2;
            c08821.L$4 = loadResponse2;
            c08821.L$5 = SpillingKt.nullOutSpilledVariable(loadResponse2);
            c08821.I$0 = 0;
            c08821.label = 1;
            if (ParCollectionsKt.runAllAsync(function1Arr, c08821) == coroutine_suspended) {
                return coroutine_suspended;
            }
            booleanRef = booleanRef2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c08821.I$0;
            loadResponse2 = (LoadResponse) c08821.L$4;
            booleanRef = (Ref.BooleanRef) c08821.L$3;
            ResultKt.throwOnFailure(obj);
        }
        return TuplesKt.to(loadResponse2, Boxing.boxBoolean(booleanRef.element));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object applyMeta$default(ResultViewModel2 resultViewModel2, LoadResponse loadResponse, SyncAPI.SyncResult syncResult, Map map, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            map = null;
        }
        return resultViewModel2.applyMeta(loadResponse, syncResult, map, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean applyMeta$lambda$0$0(MainAPI it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.contains((CharSequence) it.getName(), (CharSequence) "gogoanime", true) || StringsKt.contains((CharSequence) it.getName(), (CharSequence) "9anime", true);
    }

    public final void setMeta(SyncAPI.SyncResult meta, Map<String, String> syncs) {
        Intrinsics.checkNotNullParameter(meta, "meta");
        if (Intrinsics.areEqual(this.currentMeta, meta) && Intrinsics.areEqual(this.currentSync, syncs)) {
            Log.i(TAG, "setMeta same");
        } else {
            Log.i(TAG, "setMeta");
            ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C08921(meta, syncs, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$setMeta$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$setMeta$1", f = "ResultViewModel2.kt", i = {0, 1, 1, 1}, l = {1816, 1823}, m = "invokeSuspend", n = {"$this$launchSafe", "$this$launchSafe", ES6Iterator.VALUE_PROPERTY, "updateEpisodes"}, nl = {1823, 1830}, s = {"L$0", "L$0", "L$1", "L$2"}, v = 2)
    static final class C08921 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SyncAPI.SyncResult $meta;
        final /* synthetic */ Map<String, String> $syncs;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08921(SyncAPI.SyncResult syncResult, Map<String, String> map, Continuation<? super C08921> continuation) {
            super(2, continuation);
            this.$meta = syncResult;
            this.$syncs = map;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08921 c08921 = ResultViewModel2.this.new C08921(this.$meta, this.$syncs, continuation);
            c08921.L$0 = obj;
            return c08921;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08921) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x00ab, code lost:
        
            if (r4.postSuccessful(r5, r6, r7, r12.booleanValue(), false, r11) == r1) goto L26;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                r11 = this;
                java.lang.Object r0 = r11.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r11.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L2b
                if (r2 == r4) goto L27
                if (r2 != r3) goto L1f
                java.lang.Object r0 = r11.L$2
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                java.lang.Object r11 = r11.L$1
                com.lagradost.cloudstream3.LoadResponse r11 = (com.lagradost.cloudstream3.LoadResponse) r11
                kotlin.ResultKt.throwOnFailure(r12)
                goto Lae
            L1f:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r12)
                throw r11
            L27:
                kotlin.ResultKt.throwOnFailure(r12)
                goto L5e
            L2b:
                kotlin.ResultKt.throwOnFailure(r12)
                com.lagradost.cloudstream3.ui.result.ResultViewModel2 r12 = com.lagradost.cloudstream3.ui.result.ResultViewModel2.this
                com.lagradost.cloudstream3.syncproviders.SyncAPI$SyncResult r2 = r11.$meta
                com.lagradost.cloudstream3.ui.result.ResultViewModel2.access$setCurrentMeta$p(r12, r2)
                com.lagradost.cloudstream3.ui.result.ResultViewModel2 r12 = com.lagradost.cloudstream3.ui.result.ResultViewModel2.this
                java.util.Map<java.lang.String, java.lang.String> r2 = r11.$syncs
                com.lagradost.cloudstream3.ui.result.ResultViewModel2.access$setCurrentSync$p(r12, r2)
                com.lagradost.cloudstream3.utils.Coroutines r12 = com.lagradost.cloudstream3.utils.Coroutines.INSTANCE
                com.lagradost.cloudstream3.ui.result.ResultViewModel2$setMeta$1$1 r2 = new com.lagradost.cloudstream3.ui.result.ResultViewModel2$setMeta$1$1
                com.lagradost.cloudstream3.ui.result.ResultViewModel2 r5 = com.lagradost.cloudstream3.ui.result.ResultViewModel2.this
                com.lagradost.cloudstream3.syncproviders.SyncAPI$SyncResult r6 = r11.$meta
                java.util.Map<java.lang.String, java.lang.String> r7 = r11.$syncs
                r8 = 0
                r2.<init>(r5, r6, r7, r8)
                kotlin.jvm.functions.Function3 r2 = (kotlin.jvm.functions.Function3) r2
                r5 = r11
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                r11.L$0 = r6
                r11.label = r4
                java.lang.Object r12 = r12.ioWork(r0, r2, r5)
                if (r12 != r1) goto L5e
                goto Lad
            L5e:
                kotlin.Pair r12 = (kotlin.Pair) r12
                java.lang.Object r2 = r12.component1()
                r5 = r2
                com.lagradost.cloudstream3.LoadResponse r5 = (com.lagradost.cloudstream3.LoadResponse) r5
                java.lang.Object r12 = r12.component2()
                java.lang.Boolean r12 = (java.lang.Boolean) r12
                com.lagradost.cloudstream3.ui.result.ResultViewModel2 r4 = com.lagradost.cloudstream3.ui.result.ResultViewModel2.this
                if (r5 != 0) goto L74
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            L74:
                java.lang.Integer r2 = com.lagradost.cloudstream3.ui.result.ResultViewModel2.access$getCurrentId$p(r4)
                if (r2 == 0) goto Lb4
                int r6 = r2.intValue()
                com.lagradost.cloudstream3.ui.result.ResultViewModel2 r2 = com.lagradost.cloudstream3.ui.result.ResultViewModel2.this
                com.lagradost.cloudstream3.ui.APIRepository r7 = r2.getCurrentRepo()
                if (r7 != 0) goto L89
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            L89:
                if (r12 == 0) goto Lb1
                boolean r8 = r12.booleanValue()
                r10 = r11
                kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
                java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r0)
                r11.L$0 = r0
                java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
                r11.L$1 = r0
                java.lang.Object r12 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
                r11.L$2 = r12
                r11.label = r3
                r9 = 0
                java.lang.Object r11 = com.lagradost.cloudstream3.ui.result.ResultViewModel2.access$postSuccessful(r4, r5, r6, r7, r8, r9, r10)
                if (r11 != r1) goto Lae
            Lad:
                return r1
            Lae:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            Lb1:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            Lb4:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.result.ResultViewModel2.C08921.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$setMeta$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ResultViewModel2.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/LoadResponse;", "", "Lkotlinx/coroutines/CoroutineScope;", "it"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$setMeta$1$1", f = "ResultViewModel2.kt", i = {0, 0}, l = {1818}, m = "invokeSuspend", n = {"resp", "$i$a$-let-ResultViewModel2$setMeta$1$1$1"}, nl = {1820}, s = {"L$0", "I$0"}, v = 2)
        static final class C01761 extends SuspendLambda implements Function3<CoroutineScope, CoroutineScope, Continuation<? super Pair<? extends LoadResponse, ? extends Boolean>>, Object> {
            final /* synthetic */ SyncAPI.SyncResult $meta;
            final /* synthetic */ Map<String, String> $syncs;
            int I$0;
            Object L$0;
            int label;
            final /* synthetic */ ResultViewModel2 this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01761(ResultViewModel2 resultViewModel2, SyncAPI.SyncResult syncResult, Map<String, String> map, Continuation<? super C01761> continuation) {
                super(3, continuation);
                this.this$0 = resultViewModel2;
                this.$meta = syncResult;
                this.$syncs = map;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, Continuation<? super Pair<? extends LoadResponse, ? extends Boolean>> continuation) {
                return invoke2(coroutineScope, coroutineScope2, (Continuation<? super Pair<? extends LoadResponse, Boolean>>) continuation);
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, CoroutineScope coroutineScope2, Continuation<? super Pair<? extends LoadResponse, Boolean>> continuation) {
                return new C01761(this.this$0, this.$meta, this.$syncs, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return obj;
                }
                ResultKt.throwOnFailure(obj);
                LoadResponse loadResponse = this.this$0.currentResponse;
                if (loadResponse != null) {
                    ResultViewModel2 resultViewModel2 = this.this$0;
                    SyncAPI.SyncResult syncResult = this.$meta;
                    Map<String, String> map = this.$syncs;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(loadResponse);
                    this.I$0 = 0;
                    this.label = 1;
                    Object objApplyMeta = resultViewModel2.applyMeta(loadResponse, syncResult, map, this);
                    return objApplyMeta == coroutine_suspended ? coroutine_suspended : objApplyMeta;
                }
                return TuplesKt.to(null, null);
            }
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$updateFillers$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, d2 = {"<anonymous>", "Ljava/util/HashSet;", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$updateFillers$2", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08962 extends SuspendLambda implements Function3<CoroutineScope, ResultViewModel2, Continuation<? super HashSet<Integer>>, Object> {
        final /* synthetic */ LoadResponse $data;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08962(LoadResponse loadResponse, Continuation<? super C08962> continuation) {
            super(3, continuation);
            this.$data = loadResponse;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, ResultViewModel2 resultViewModel2, Continuation<? super HashSet<Integer>> continuation) {
            return new C08962(this.$data, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return FillerEpisodeCheck.INSTANCE.getFillerEpisodes(this.$data);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object updateFillers(LoadResponse loadResponse, Continuation<? super Unit> continuation) {
        C08951 c08951;
        if (continuation instanceof C08951) {
            c08951 = (C08951) continuation;
            if ((c08951.label & Integer.MIN_VALUE) != 0) {
                c08951.label -= Integer.MIN_VALUE;
            } else {
                c08951 = new C08951(continuation);
            }
        } else {
            c08951 = new C08951(continuation);
        }
        Object objIoWorkSafe = c08951.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08951.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objIoWorkSafe);
            Coroutines coroutines = Coroutines.INSTANCE;
            C08962 c08962 = new C08962(loadResponse, null);
            c08951.L$0 = SpillingKt.nullOutSpilledVariable(loadResponse);
            c08951.L$1 = this;
            c08951.label = 1;
            objIoWorkSafe = coroutines.ioWorkSafe(this, c08962, c08951);
            if (objIoWorkSafe == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            this = (ResultViewModel2) c08951.L$1;
            ResultKt.throwOnFailure(objIoWorkSafe);
        }
        HashSet<Integer> hashSet = (HashSet) objIoWorkSafe;
        if (hashSet == null) {
            hashSet = new HashSet<>();
        }
        this.fillers = hashSet;
        return Unit.INSTANCE;
    }

    public final void changeDubStatus(DubStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        EpisodeIndexer episodeIndexer = this.currentIndex;
        EpisodeIndexer episodeIndexerCopy$default = episodeIndexer != null ? EpisodeIndexer.copy$default(episodeIndexer, status, 0, 2, null) : null;
        EpisodeRange episodeRange = this.currentRange;
        EpisodeSortType resultsSortingMode = this.currentSorting;
        if (resultsSortingMode == null) {
            resultsSortingMode = DataStoreHelper.INSTANCE.getResultsSortingMode();
        }
        postEpisodeRange(episodeIndexerCopy$default, episodeRange, resultsSortingMode);
    }

    public final void changeRange(EpisodeRange range) {
        Intrinsics.checkNotNullParameter(range, "range");
        EpisodeIndexer episodeIndexer = this.currentIndex;
        EpisodeSortType resultsSortingMode = this.currentSorting;
        if (resultsSortingMode == null) {
            resultsSortingMode = DataStoreHelper.INSTANCE.getResultsSortingMode();
        }
        postEpisodeRange(episodeIndexer, range, resultsSortingMode);
    }

    public final void changeSeason(int season) {
        EpisodeIndexer episodeIndexer = this.currentIndex;
        EpisodeIndexer episodeIndexerCopy$default = episodeIndexer != null ? EpisodeIndexer.copy$default(episodeIndexer, null, season, 1, null) : null;
        EpisodeRange episodeRange = this.currentRange;
        EpisodeSortType resultsSortingMode = this.currentSorting;
        if (resultsSortingMode == null) {
            resultsSortingMode = DataStoreHelper.INSTANCE.getResultsSortingMode();
        }
        postEpisodeRange(episodeIndexerCopy$default, episodeRange, resultsSortingMode);
    }

    public final void setSort(EpisodeSortType sortType) {
        Intrinsics.checkNotNullParameter(sortType, "sortType");
        DataStoreHelper.INSTANCE.setResultsSortingMode(sortType);
        postEpisodeRange(this.currentIndex, this.currentRange, sortType);
    }

    private final ResultEpisode getMovie() {
        List list;
        ResultEpisode resultEpisode;
        Map.Entry entry = (Map.Entry) CollectionsKt.firstOrNull(this.currentEpisodes.entrySet());
        if (entry == null || (list = (List) entry.getValue()) == null || (resultEpisode = (ResultEpisode) CollectionsKt.firstOrNull(list)) == null) {
            return null;
        }
        DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Integer.valueOf(resultEpisode.getId()));
        return ResultEpisode.copy$default(resultEpisode, null, null, null, 0, null, null, null, null, 0, 0, viewPos != null ? viewPos.getPosition() : 0L, viewPos != null ? viewPos.getDuration() : 0L, null, null, null, null, 0, null, null, null, null, null, 4191231, null);
    }

    private final List<ResultEpisode> getEpisodes(EpisodeIndexer indexer, EpisodeRange range) {
        List<ResultEpisode> list = this.currentEpisodes.get(indexer);
        if (list != null) {
            int iMin = Math.min(list.size(), range.getStartIndex());
            List<ResultEpisode> listSubList = list.subList(iMin, Math.min(list.size(), range.getLength() + iMin));
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSubList, 10));
            for (ResultEpisode resultEpisode : listSubList) {
                DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Integer.valueOf(resultEpisode.getId()));
                VideoWatchState videoWatchState = DataStoreHelper.INSTANCE.getVideoWatchState(Integer.valueOf(resultEpisode.getId()));
                if (videoWatchState == null) {
                    videoWatchState = VideoWatchState.None;
                }
                VideoWatchState videoWatchState2 = videoWatchState;
                long duration = 0;
                long position = viewPos != null ? viewPos.getPosition() : 0L;
                if (viewPos != null) {
                    duration = viewPos.getDuration();
                }
                arrayList.add(ResultEpisode.copy$default(resultEpisode, null, null, null, 0, null, null, null, null, 0, 0, position, duration, null, null, null, null, 0, videoWatchState2, null, null, null, null, 4060159, null));
            }
            return arrayList;
        }
        return CollectionsKt.emptyList();
    }

    private final List<ResultEpisode> getSortedEpisodes(List<ResultEpisode> episodes, EpisodeSortType sorting) {
        switch (WhenMappings.$EnumSwitchMapping$1[sorting.ordinal()]) {
            case 1:
                return CollectionsKt.sortedWith(episodes, new Comparator() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$getSortedEpisodes$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(((ResultEpisode) t).getEpisode()), Integer.valueOf(((ResultEpisode) t2).getEpisode()));
                    }
                });
            case 2:
                return CollectionsKt.sortedWith(episodes, new Comparator() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$getSortedEpisodes$$inlined$sortedByDescending$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(((ResultEpisode) t2).getEpisode()), Integer.valueOf(((ResultEpisode) t).getEpisode()));
                    }
                });
            case 3:
                return CollectionsKt.sortedWith(episodes, new Comparator() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$getSortedEpisodes$$inlined$sortedByDescending$2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        Score score = ((ResultEpisode) t2).getScore();
                        Double dValueOf = Double.valueOf(score != null ? Score.toDouble$default(score, 0, 1, null) : 0.0d);
                        Score score2 = ((ResultEpisode) t).getScore();
                        return ComparisonsKt.compareValues(dValueOf, Double.valueOf(score2 != null ? Score.toDouble$default(score2, 0, 1, null) : 0.0d));
                    }
                });
            case 4:
                return CollectionsKt.sortedWith(episodes, new Comparator() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$getSortedEpisodes$$inlined$sortedBy$2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        Score score = ((ResultEpisode) t).getScore();
                        Double dValueOf = Double.valueOf(score != null ? Score.toDouble$default(score, 0, 1, null) : 0.0d);
                        Score score2 = ((ResultEpisode) t2).getScore();
                        return ComparisonsKt.compareValues(dValueOf, Double.valueOf(score2 != null ? Score.toDouble$default(score2, 0, 1, null) : 0.0d));
                    }
                });
            case 5:
                return CollectionsKt.sortedWith(episodes, new Comparator() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$getSortedEpisodes$$inlined$sortedByDescending$3
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(((ResultEpisode) t2).getAirDate(), ((ResultEpisode) t).getAirDate());
                    }
                });
            case 6:
                return CollectionsKt.sortedWith(episodes, new Comparator() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$getSortedEpisodes$$inlined$sortedBy$3
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(((ResultEpisode) t).getAirDate(), ((ResultEpisode) t2).getAirDate());
                    }
                });
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final void postMovie() {
        Integer numValueOf;
        LoadResponse loadResponse = this.currentResponse;
        this._episodes.postValue(null);
        if (loadResponse == null) {
            this._movie.postValue(null);
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$2[loadResponse.getType().ordinal()];
        if (i == 1) {
            numValueOf = Integer.valueOf(R.string.play_torrent_button);
        } else if (i == 2) {
            numValueOf = Integer.valueOf(R.string.play_full_series_button);
        } else if (MainAPIKt.isLiveStream(loadResponse.getType())) {
            numValueOf = Integer.valueOf(R.string.play_livestream_button);
        } else {
            numValueOf = LoadResponse.INSTANCE.isMovie(loadResponse) ? Integer.valueOf(R.string.play_movie_button) : null;
        }
        UiText uiTextTxtNull = TextUtilKt.txtNull(numValueOf, new Object[0]);
        ResultEpisode movie = getMovie();
        this._episodes.postValue(null);
        if (uiTextTxtNull == null || movie == null) {
            this._movie.postValue(null);
        } else {
            this._movie.postValue(new Resource.Success(TuplesKt.to(uiTextTxtNull, movie)));
        }
    }

    public final void reloadEpisodes() {
        EpisodeRange episodeRange;
        LoadResponse loadResponse = this.currentResponse;
        if (loadResponse != null && LoadResponse.INSTANCE.isMovie(loadResponse)) {
            postMovie();
        } else {
            MutableLiveData<Resource<List<ResultEpisode>>> mutableLiveData = this._episodes;
            EpisodeIndexer episodeIndexer = this.currentIndex;
            if (episodeIndexer == null || (episodeRange = this.currentRange) == null) {
                return;
            }
            List<ResultEpisode> episodes = getEpisodes(episodeIndexer, episodeRange);
            EpisodeSortType episodeSortType = this.currentSorting;
            if (episodeSortType == null) {
                return;
            }
            mutableLiveData.postValue(new Resource.Success(getSortedEpisodes(episodes, episodeSortType)));
            this._movie.postValue(null);
        }
        postResume();
    }

    private final void postSubscription(LoadResponse loadResponse) {
        int id = ResultViewModel2Kt.getId(loadResponse);
        DataStoreHelper.SubscribedData subscribedData = DataStoreHelper.INSTANCE.getSubscribedData(Integer.valueOf(id));
        if (MainAPIKt.isEpisodeBased(loadResponse)) {
            DataStoreHelper.INSTANCE.updateSubscribedData(Integer.valueOf(id), subscribedData, loadResponse instanceof EpisodeResponse ? (EpisodeResponse) loadResponse : null);
            this._subscribeStatus.postValue(Boolean.valueOf(subscribedData != null));
            return;
        }
        MutableLiveData<Boolean> mutableLiveData = this._subscribeStatus;
        if (subscribedData != null) {
            mutableLiveData.postValue(true);
        } else {
            mutableLiveData.postValue(null);
        }
    }

    private final void postFavorites(LoadResponse loadResponse) {
        this._favoriteStatus.postValue(Boolean.valueOf(DataStoreHelper.INSTANCE.getFavoritesData(Integer.valueOf(ResultViewModel2Kt.getId(loadResponse))) != null));
    }

    private final boolean shouldEnableSort(EpisodeSortType type, List<ResultEpisode> episodes) {
        List<ResultEpisode> list = episodes;
        if (list == null || list.isEmpty()) {
            return false;
        }
        switch (WhenMappings.$EnumSwitchMapping$1[type.ordinal()]) {
            case 1:
            case 2:
                return true;
            case 3:
            case 4:
                List<ResultEpisode> list2 = episodes;
                if ((list2 instanceof Collection) && list2.isEmpty()) {
                    return false;
                }
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    if (((ResultEpisode) it.next()).getScore() != null) {
                        return true;
                    }
                }
                return false;
            case 5:
            case 6:
                List<ResultEpisode> list3 = episodes;
                if ((list3 instanceof Collection) && list3.isEmpty()) {
                    return false;
                }
                Iterator<T> it2 = list3.iterator();
                while (it2.hasNext()) {
                    if (((ResultEpisode) it2.next()).getAirDate() != null) {
                        return true;
                    }
                }
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Code duplicated, block: B:78:0x017b  */
    private final void postEpisodeRange(EpisodeIndexer indexer, EpisodeRange range, EpisodeSortType sorting) {
        Object next;
        ArrayList arrayListEmptyList;
        UiText seasonTxt;
        UiText uiTextTxt;
        RepoLinkGenerator repoLinkGenerator;
        UiText uiTextTxt2;
        EpisodeSortType episodeSortType = sorting;
        if (range == null || indexer == null || episodeSortType == null) {
            return;
        }
        List<EpisodeRange> list = this.currentRanges.get(indexer);
        if ((list == null || !list.contains(range)) && list != null) {
            Iterator<T> it = list.iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    int iAbs = Math.abs(((EpisodeRange) next).getStartEpisode() - range.getStartEpisode());
                    do {
                        Object next2 = it.next();
                        int iAbs2 = Math.abs(((EpisodeRange) next2).getStartEpisode() - range.getStartEpisode());
                        if (iAbs > iAbs2) {
                            next = next2;
                            iAbs = iAbs2;
                        }
                    } while (it.hasNext());
                }
            } else {
                next = null;
            }
            EpisodeRange episodeRange = (EpisodeRange) next;
            if (episodeRange != null) {
                postEpisodeRange(indexer, episodeRange, episodeSortType);
                return;
            }
        }
        LoadResponse loadResponse = this.currentResponse;
        boolean z = loadResponse != null && LoadResponse.INSTANCE.isMovie(loadResponse);
        this.currentIndex = indexer;
        this.currentRange = range;
        MutableLiveData<List<Pair<UiText, EpisodeRange>>> mutableLiveData = this._rangeSelections;
        if (list != null) {
            List<EpisodeRange> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (EpisodeRange episodeRange2 : list2) {
                arrayList.add(TuplesKt.to(TextUtilKt.txt(R.string.episodes_range, Integer.valueOf(episodeRange2.getStartEpisode()), Integer.valueOf(episodeRange2.getEndEpisode())), episodeRange2));
            }
            arrayListEmptyList = arrayList;
        } else {
            arrayListEmptyList = CollectionsKt.emptyList();
        }
        mutableLiveData.postValue(arrayListEmptyList);
        List<ResultEpisode> list3 = this.currentEpisodes.get(indexer);
        Integer numValueOf = list3 != null ? Integer.valueOf(list3.size()) : null;
        this._episodesCountText.postValue(z ? null : TextUtilKt.txtNull(Integer.valueOf(R.string.episode_format), numValueOf, TextUtilKt.txt((numValueOf != null && numValueOf.intValue() == 1) ? R.string.episode : R.string.episodes, new Object[0])));
        this._selectedSeasonIndex.postValue(Integer.valueOf(this.currentSeasons.indexOf(Integer.valueOf(indexer.getSeason()))));
        MutableLiveData<UiText> mutableLiveData2 = this._selectedSeason;
        if (z || this.currentSeasons.size() <= 1) {
            seasonTxt = null;
        } else {
            Companion companion = INSTANCE;
            LoadResponse loadResponse2 = this.currentResponse;
            EpisodeResponse episodeResponse = loadResponse2 instanceof EpisodeResponse ? (EpisodeResponse) loadResponse2 : null;
            seasonTxt = companion.getSeasonTxt(episodeResponse != null ? episodeResponse.getSeasonNames() : null, Integer.valueOf(indexer.getSeason()));
        }
        mutableLiveData2.postValue(seasonTxt);
        this._selectedRangeIndex.postValue(Integer.valueOf(list != null ? list.indexOf(range) : -1));
        MutableLiveData<UiText> mutableLiveData3 = this._selectedRange;
        if (z) {
            uiTextTxt = null;
        } else {
            List<EpisodeRange> list4 = this.currentRanges.get(indexer);
            if ((list4 != null ? list4.size() : 0) > 1) {
                uiTextTxt = TextUtilKt.txt(R.string.episodes_range, Integer.valueOf(range.getStartEpisode()), Integer.valueOf(range.getEndEpisode()));
            } else {
                uiTextTxt = null;
            }
        }
        mutableLiveData3.postValue(uiTextTxt);
        this._selectedDubStatusIndex.postValue(Integer.valueOf(this.currentDubStatus.indexOf(indexer.getDubStatus())));
        this._selectedDubStatus.postValue((z || this.currentDubStatus.size() <= 1) ? null : ResultViewModel2Kt.txt(indexer.getDubStatus()));
        Integer num = this.currentId;
        if (num != null) {
            int iIntValue = num.intValue();
            DataStoreHelper.INSTANCE.setDub(iIntValue, indexer.getDubStatus());
            DataStoreHelper.INSTANCE.setResultSeason(iIntValue, Integer.valueOf(indexer.getSeason()));
            DataStoreHelper.INSTANCE.setResultEpisode(iIntValue, Integer.valueOf(range.getStartEpisode()));
        }
        this.preferStartEpisode = Integer.valueOf(range.getStartEpisode());
        this.preferStartSeason = Integer.valueOf(indexer.getSeason());
        this.preferDubStatus = indexer.getDubStatus();
        if (z) {
            ResultEpisode movie = getMovie();
            repoLinkGenerator = movie != null ? new RepoLinkGenerator(CollectionsKt.listOf(movie), this.currentResponse) : null;
        } else {
            Map<EpisodeIndexer, ? extends List<ResultEpisode>> map = this.currentEpisodes;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<EpisodeIndexer, ? extends List<ResultEpisode>> entry : map.entrySet()) {
                if (entry.getKey().getDubStatus() == indexer.getDubStatus()) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            List listSortedWith = CollectionsKt.sortedWith(MapsKt.toList(linkedHashMap), new Comparator() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$postEpisodeRange$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((ResultViewModel2.EpisodeIndexer) ((Pair) t).getFirst()).getSeason()), Integer.valueOf(((ResultViewModel2.EpisodeIndexer) ((Pair) t2).getFirst()).getSeason()));
                }
            });
            ArrayList arrayList2 = new ArrayList();
            Iterator it2 = listSortedWith.iterator();
            while (it2.hasNext()) {
                CollectionsKt.addAll(arrayList2, (Iterable) ((Pair) it2.next()).getSecond());
            }
            repoLinkGenerator = new RepoLinkGenerator(arrayList2, this.currentResponse);
        }
        this.generator = repoLinkGenerator;
        if (z) {
            this._sortSelections.postValue(CollectionsKt.emptyList());
            this._selectedSortingIndex.postValue(-1);
            this._selectedSorting.postValue(null);
            postMovie();
            return;
        }
        List<ResultEpisode> episodes = getEpisodes(indexer, range);
        if (episodes.size() <= 1) {
            this._sortSelections.postValue(CollectionsKt.emptyList());
            this._selectedSortingIndex.postValue(-1);
            this._selectedSorting.postValue(null);
            this._episodes.postValue(new Resource.Success(episodes));
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(TuplesKt.to(TextUtilKt.txt(R.string.sort_episodes_number_asc, new Object[0]), EpisodeSortType.NUMBER_ASC));
        arrayList3.add(TuplesKt.to(TextUtilKt.txt(R.string.sort_episodes_number_desc, new Object[0]), EpisodeSortType.NUMBER_DESC));
        if (shouldEnableSort(EpisodeSortType.RATING_HIGH_LOW, episodes)) {
            arrayList3.add(TuplesKt.to(TextUtilKt.txt(R.string.sort_episodes_rating_high_low, new Object[0]), EpisodeSortType.RATING_HIGH_LOW));
            arrayList3.add(TuplesKt.to(TextUtilKt.txt(R.string.sort_episodes_rating_low_high, new Object[0]), EpisodeSortType.RATING_LOW_HIGH));
        }
        if (shouldEnableSort(EpisodeSortType.DATE_NEWEST, episodes)) {
            arrayList3.add(TuplesKt.to(TextUtilKt.txt(R.string.sort_episodes_date_newest, new Object[0]), EpisodeSortType.DATE_NEWEST));
            arrayList3.add(TuplesKt.to(TextUtilKt.txt(R.string.sort_episodes_date_oldest, new Object[0]), EpisodeSortType.DATE_OLDEST));
        }
        Iterator it3 = arrayList3.iterator();
        int i = 0;
        while (true) {
            if (!it3.hasNext()) {
                i = -1;
                break;
            } else if (((Pair) it3.next()).getSecond() == episodeSortType) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            episodeSortType = EpisodeSortType.NUMBER_ASC;
            i = 0;
        }
        this.currentSorting = episodeSortType;
        this._sortSelections.postValue(arrayList3);
        this._selectedSortingIndex.postValue(Integer.valueOf(i));
        MutableLiveData<UiText> mutableLiveData4 = this._selectedSorting;
        switch (WhenMappings.$EnumSwitchMapping$1[episodeSortType.ordinal()]) {
            case 1:
                uiTextTxt2 = TextUtilKt.txt(R.string.sort_button_episode, "↑");
                break;
            case 2:
                uiTextTxt2 = TextUtilKt.txt(R.string.sort_button_episode, "↓");
                break;
            case 3:
                uiTextTxt2 = TextUtilKt.txt(R.string.sort_button_rating, "↓");
                break;
            case 4:
                uiTextTxt2 = TextUtilKt.txt(R.string.sort_button_rating, "↑");
                break;
            case 5:
                uiTextTxt2 = TextUtilKt.txt(R.string.sort_button_date, "↓");
                break;
            case 6:
                uiTextTxt2 = TextUtilKt.txt(R.string.sort_button_date, "↑");
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        mutableLiveData4.postValue(uiTextTxt2);
        this._episodes.postValue(new Resource.Success(getSortedEpisodes(episodes, episodeSortType)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object postSuccessful(LoadResponse loadResponse, int i, APIRepository aPIRepository, boolean z, boolean z2, Continuation<? super Unit> continuation) {
        this.currentId = Boxing.boxInt(i);
        this.currentResponse = loadResponse;
        postPage(loadResponse, aPIRepository);
        postSubscription(loadResponse);
        postFavorites(loadResponse);
        this._watchStatus.postValue(DataStoreHelper.INSTANCE.getResultWatchState(i));
        if (z) {
            Object objPostEpisodes = postEpisodes(loadResponse, i, z2, continuation);
            return objPostEpisodes == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPostEpisodes : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:164:0x0557 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:165:0x0559  */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    public final Object postEpisodes(LoadResponse loadResponse, int i, boolean z, Continuation<? super Unit> continuation) {
        C08911 c08911;
        int i2;
        int i3;
        int i4;
        LinkedHashMap linkedHashMapEmptyMap;
        Object next;
        Object next2;
        int startEpisode;
        Integer num;
        ArrayList arrayList;
        LoadResponse loadResponse2 = loadResponse;
        if (continuation instanceof C08911) {
            c08911 = (C08911) continuation;
            if ((c08911.label & Integer.MIN_VALUE) != 0) {
                c08911.label -= Integer.MIN_VALUE;
            } else {
                c08911 = new C08911(continuation);
            }
        } else {
            c08911 = new C08911(continuation);
        }
        Object obj = c08911.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = c08911.label;
        EpisodeRange episodeRange = null;
        if (i5 == 0) {
            ResultKt.throwOnFailure(obj);
            this._episodes.postValue(new Resource.Loading(null, 1, null));
            if (z) {
                c08911.L$0 = loadResponse2;
                i2 = i;
                c08911.I$0 = i2;
                c08911.Z$0 = z;
                c08911.label = 1;
                if (updateFillers(loadResponse2, c08911) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                i2 = i;
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            boolean z2 = c08911.Z$0;
            int i6 = c08911.I$0;
            LoadResponse loadResponse3 = (LoadResponse) c08911.L$0;
            ResultKt.throwOnFailure(obj);
            i2 = i6;
            loadResponse2 = loadResponse3;
        }
        int i7 = i2;
        if (loadResponse2 instanceof AnimeLoadResponse) {
            HashSet hashSet = new HashSet();
            linkedHashMapEmptyMap = new LinkedHashMap();
            AnimeLoadResponse animeLoadResponse = (AnimeLoadResponse) loadResponse2;
            Map<DubStatus, List<Episode>> episodes = animeLoadResponse.getEpisodes();
            ArrayList arrayList2 = new ArrayList(episodes.size());
            for (Map.Entry<DubStatus, List<Episode>> entry : episodes.entrySet()) {
                int id = entry.getKey().getId();
                int i8 = 0;
                for (Episode episode : entry.getValue()) {
                    int i9 = i8 + 1;
                    Integer episode2 = episode.getEpisode();
                    int iIntValue = episode2 != null ? episode2.intValue() : i9;
                    int i10 = i7 + iIntValue + (1000000 * id);
                    Integer season = episode.getSeason();
                    int iIntValue2 = i10 + (season != null ? season.intValue() * 10000 : 0);
                    Integer season2 = episode.getSeason();
                    Integer numBoxInt = season2 != null ? Boxing.boxInt(animeLoadResponse.getTotalEpisodeIndex(iIntValue, season2.intValue())) : null;
                    if (hashSet.contains(Boxing.boxInt(iIntValue2))) {
                        arrayList = arrayList2;
                    } else {
                        hashSet.add(Boxing.boxInt(iIntValue2));
                        Companion companion = INSTANCE;
                        SeasonData season3 = companion.getSeason(animeLoadResponse.getSeasonNames(), episode.getSeason());
                        int i11 = i7;
                        arrayList = arrayList2;
                        ResultEpisode resultEpisodeBuildResultEpisode = ResultFragmentKt.buildResultEpisode(animeLoadResponse.getName(), companion.filterName(episode.getName()), episode.getPosterUrl(), iIntValue, episode.getSeason(), season3 != null ? season3.getDisplaySeason() : episode.getSeason(), episode.getData(), animeLoadResponse.getApiName(), iIntValue2, i8, episode.getScore(), episode.getDescription(), Boxing.boxBoolean(this.fillers.contains(Boxing.boxInt(iIntValue))), animeLoadResponse.getType(), i11, numBoxInt, episode.getDate(), episode.getRunTime(), season3);
                        i7 = i11;
                        Integer seasonIndex = resultEpisodeBuildResultEpisode.getSeasonIndex();
                        EpisodeIndexer episodeIndexer = new EpisodeIndexer(entry.getKey(), seasonIndex != null ? seasonIndex.intValue() : 0);
                        List<ResultEpisode> list = linkedHashMapEmptyMap.get(episodeIndexer);
                        if (list != null) {
                            Boxing.boxBoolean(list.add(resultEpisodeBuildResultEpisode));
                        } else {
                            linkedHashMapEmptyMap.put(episodeIndexer, CollectionsKt.mutableListOf(resultEpisodeBuildResultEpisode));
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                    arrayList2 = arrayList;
                    i8 = i9;
                }
                arrayList2.add(Unit.INSTANCE);
            }
            i3 = 100000;
            i4 = 0;
        } else {
            i3 = 100000;
            i4 = 0;
            if (loadResponse2 instanceof TvSeriesLoadResponse) {
                linkedHashMapEmptyMap = new LinkedHashMap();
                HashSet hashSet2 = new HashSet();
                TvSeriesLoadResponse tvSeriesLoadResponse = (TvSeriesLoadResponse) loadResponse2;
                int i12 = 0;
                for (Episode episode3 : CollectionsKt.sortedWith(tvSeriesLoadResponse.getEpisodes(), new Comparator() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2$postEpisodes$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        Episode episode4 = (Episode) t;
                        Integer season4 = episode4.getSeason();
                        int iIntValue3 = season4 != null ? season4.intValue() * 10000 : 0;
                        Integer episode5 = episode4.getEpisode();
                        Integer numValueOf = Integer.valueOf(iIntValue3 + (episode5 != null ? episode5.intValue() : 0));
                        Episode episode6 = (Episode) t2;
                        Integer season5 = episode6.getSeason();
                        int iIntValue4 = season5 != null ? season5.intValue() * 10000 : 0;
                        Integer episode7 = episode6.getEpisode();
                        return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(iIntValue4 + (episode7 != null ? episode7.intValue() : 0)));
                    }
                })) {
                    int i13 = i12 + 1;
                    Integer episode4 = episode3.getEpisode();
                    int iIntValue3 = episode4 != null ? episode4.intValue() : i13;
                    Integer season4 = episode3.getSeason();
                    int iIntValue4 = i7 + (season4 != null ? season4.intValue() * 100000 : 0) + iIntValue3 + 1;
                    if (!hashSet2.contains(Boxing.boxInt(iIntValue4))) {
                        hashSet2.add(Boxing.boxInt(iIntValue4));
                        Companion companion2 = INSTANCE;
                        SeasonData season5 = companion2.getSeason(tvSeriesLoadResponse.getSeasonNames(), episode3.getSeason());
                        Integer season6 = episode3.getSeason();
                        int i14 = i7;
                        ResultEpisode resultEpisodeBuildResultEpisode2 = ResultFragmentKt.buildResultEpisode(tvSeriesLoadResponse.getName(), companion2.filterName(episode3.getName()), episode3.getPosterUrl(), iIntValue3, episode3.getSeason(), season5 != null ? season5.getDisplaySeason() : episode3.getSeason(), episode3.getData(), tvSeriesLoadResponse.getApiName(), iIntValue4, i12, episode3.getScore(), episode3.getDescription(), null, tvSeriesLoadResponse.getType(), i14, season6 != null ? Boxing.boxInt(tvSeriesLoadResponse.getTotalEpisodeIndex(iIntValue3, season6.intValue())) : null, episode3.getDate(), episode3.getRunTime(), season5);
                        i7 = i14;
                        Integer seasonIndex2 = resultEpisodeBuildResultEpisode2.getSeasonIndex();
                        EpisodeIndexer episodeIndexer2 = new EpisodeIndexer(DubStatus.None, seasonIndex2 != null ? seasonIndex2.intValue() : 0);
                        List<ResultEpisode> list2 = linkedHashMapEmptyMap.get(episodeIndexer2);
                        if (list2 != null) {
                            Boxing.boxBoolean(list2.add(resultEpisodeBuildResultEpisode2));
                        } else {
                            linkedHashMapEmptyMap.put(episodeIndexer2, CollectionsKt.mutableListOf(resultEpisodeBuildResultEpisode2));
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                    i12 = i13;
                }
            } else if (loadResponse2 instanceof MovieLoadResponse) {
                MovieLoadResponse movieLoadResponse = (MovieLoadResponse) loadResponse2;
                linkedHashMapEmptyMap = INSTANCE.singleMap(ResultFragmentKt.buildResultEpisode(movieLoadResponse.getName(), (458752 & 2) != 0 ? null : movieLoadResponse.getName(), (458752 & 4) != 0 ? null : null, 0, (458752 & 16) != 0 ? null : null, (458752 & 32) != 0 ? null : null, movieLoadResponse.getDataUrl(), movieLoadResponse.getApiName(), i7, 0, (458752 & 1024) != 0 ? null : null, (458752 & 2048) != 0 ? null : null, (458752 & 4096) != 0 ? null : null, movieLoadResponse.getType(), i7, (32768 & 458752) != 0 ? null : null, (65536 & 458752) != 0 ? null : null, (131072 & 458752) != 0 ? null : null, (458752 & 262144) != 0 ? null : null));
            } else if (loadResponse2 instanceof LiveStreamLoadResponse) {
                LiveStreamLoadResponse liveStreamLoadResponse = (LiveStreamLoadResponse) loadResponse2;
                linkedHashMapEmptyMap = INSTANCE.singleMap(ResultFragmentKt.buildResultEpisode(liveStreamLoadResponse.getName(), (458752 & 2) != 0 ? null : liveStreamLoadResponse.getName(), (458752 & 4) != 0 ? null : null, 0, (458752 & 16) != 0 ? null : null, (458752 & 32) != 0 ? null : null, liveStreamLoadResponse.getDataUrl(), liveStreamLoadResponse.getApiName(), i7, 0, (458752 & 1024) != 0 ? null : null, (458752 & 2048) != 0 ? null : null, (458752 & 4096) != 0 ? null : null, liveStreamLoadResponse.getType(), i7, (32768 & 458752) != 0 ? null : null, (65536 & 458752) != 0 ? null : null, (131072 & 458752) != 0 ? null : null, (458752 & 262144) != 0 ? null : null));
            } else if (loadResponse2 instanceof TorrentLoadResponse) {
                Companion companion3 = INSTANCE;
                TorrentLoadResponse torrentLoadResponse = (TorrentLoadResponse) loadResponse2;
                String name = torrentLoadResponse.getName();
                String name2 = torrentLoadResponse.getName();
                String torrent = torrentLoadResponse.getTorrent();
                if (torrent == null && (torrent = torrentLoadResponse.getMagnet()) == null) {
                    torrent = "";
                }
                linkedHashMapEmptyMap = companion3.singleMap(ResultFragmentKt.buildResultEpisode(name, (458752 & 2) != 0 ? null : name2, (458752 & 4) != 0 ? null : null, 0, (458752 & 16) != 0 ? null : null, (458752 & 32) != 0 ? null : null, torrent, torrentLoadResponse.getApiName(), i7, 0, (458752 & 1024) != 0 ? null : null, (458752 & 2048) != 0 ? null : null, (458752 & 4096) != 0 ? null : null, torrentLoadResponse.getType(), i7, (32768 & 458752) != 0 ? null : null, (65536 & 458752) != 0 ? null : null, (131072 & 458752) != 0 ? null : null, (458752 & 262144) != 0 ? null : null));
            } else {
                linkedHashMapEmptyMap = MapsKt.emptyMap();
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (EpisodeIndexer episodeIndexer3 : linkedHashMapEmptyMap.keySet()) {
            linkedHashSet.add(Boxing.boxInt(episodeIndexer3.getSeason()));
            linkedHashSet2.add(episodeIndexer3.getDubStatus());
        }
        LinkedHashSet<DubStatus> linkedHashSet3 = linkedHashSet2;
        this.currentDubStatus = CollectionsKt.toList(linkedHashSet3);
        LinkedHashSet linkedHashSet4 = linkedHashSet;
        this.currentSeasons = CollectionsKt.toList(linkedHashSet4);
        MutableLiveData<List<Pair<UiText, DubStatus>>> mutableLiveData = this._dubSubSelections;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(linkedHashSet3, 10));
        for (DubStatus dubStatus : linkedHashSet3) {
            arrayList3.add(TuplesKt.to(ResultViewModel2Kt.txt(dubStatus), dubStatus));
        }
        mutableLiveData.postValue(arrayList3);
        if (loadResponse2 instanceof EpisodeResponse) {
            MutableLiveData<List<Pair<UiText, Integer>>> mutableLiveData2 = this._seasonSelections;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(linkedHashSet4, 10));
            Iterator it = linkedHashSet4.iterator();
            while (it.hasNext()) {
                int iIntValue5 = ((Number) it.next()).intValue();
                arrayList4.add(TuplesKt.to(INSTANCE.getSeasonTxt(((EpisodeResponse) loadResponse2).getSeasonNames(), Boxing.boxInt(iIntValue5)), Boxing.boxInt(iIntValue5)));
            }
            mutableLiveData2.postValue(arrayList4);
        }
        this.currentEpisodes = linkedHashMapEmptyMap;
        Map<EpisodeIndexer, ? extends List<EpisodeRange>> ranges = INSTANCE.getRanges(linkedHashMapEmptyMap, this.EPISODE_RANGE_SIZE);
        this.currentRanges = ranges;
        Iterator<T> it2 = ranges.keySet().iterator();
        if (it2.hasNext()) {
            next = it2.next();
            if (it2.hasNext()) {
                EpisodeIndexer episodeIndexer4 = (EpisodeIndexer) next;
                int season7 = episodeIndexer4.getSeason();
                Integer num2 = this.preferStartSeason;
                int iAbs = Math.abs(season7 - (num2 != null ? num2.intValue() : 1)) + (episodeIndexer4.getDubStatus() == this.preferDubStatus ? i4 : i3);
                do {
                    Object next3 = it2.next();
                    EpisodeIndexer episodeIndexer5 = (EpisodeIndexer) next3;
                    int season8 = episodeIndexer5.getSeason();
                    Integer num3 = this.preferStartSeason;
                    int iAbs2 = Math.abs(season8 - (num3 != null ? num3.intValue() : 1)) + (episodeIndexer5.getDubStatus() == this.preferDubStatus ? i4 : i3);
                    if (iAbs > iAbs2) {
                        next = next3;
                        iAbs = iAbs2;
                    }
                } while (it2.hasNext());
            }
        } else {
            next = null;
        }
        EpisodeIndexer episodeIndexer6 = (EpisodeIndexer) next;
        List<EpisodeRange> list3 = ranges.get(episodeIndexer6);
        if (list3 != null) {
            Iterator<T> it3 = list3.iterator();
            do {
                if (!it3.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = it3.next();
                startEpisode = ((EpisodeRange) next2).getStartEpisode();
                num = this.preferStartEpisode;
            } while (startEpisode < (num != null ? num.intValue() : i4));
            EpisodeRange episodeRange2 = (EpisodeRange) next2;
            if (episodeRange2 != null) {
                episodeRange = episodeRange2;
            } else if (list3 != null) {
                episodeRange = (EpisodeRange) CollectionsKt.lastOrNull((List) list3);
            }
        } else if (list3 != null) {
            episodeRange = (EpisodeRange) CollectionsKt.lastOrNull((List) list3);
        }
        postEpisodeRange(episodeIndexer6, episodeRange, DataStoreHelper.INSTANCE.getResultsSortingMode());
        postResume();
        return Unit.INSTANCE;
    }

    private final void postResume() {
        this._resumeWatching.postValue(resume());
    }

    private final ResumeWatchingStatus resume() {
        Integer episodeId;
        Object next;
        Integer num = this.currentId;
        if (num != null) {
            int iIntValue = num.intValue();
            DownloadObjects.ResumeWatching lastWatched = DataStoreHelper.INSTANCE.getLastWatched(num);
            Integer numValueOf = lastWatched != null ? Integer.valueOf(lastWatched.getParentId()) : null;
            if (numValueOf != null && numValueOf.intValue() == iIntValue && (episodeId = lastWatched.getEpisodeId()) != null) {
                int iIntValue2 = episodeId.intValue();
                LoadResponse loadResponse = this.currentResponse;
                if (loadResponse == null) {
                    return null;
                }
                Iterator it = CollectionsKt.flatten(this.currentEpisodes.values()).iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (((ResultEpisode) next).getId() != iIntValue2);
                ResultEpisode resultEpisode = (ResultEpisode) next;
                if (resultEpisode == null) {
                    return null;
                }
                boolean zIsMovie = LoadResponse.INSTANCE.isMovie(loadResponse);
                DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(lastWatched.getEpisodeId());
                return new ResumeWatchingStatus(viewPos != null ? new ResumeProgress((int) (viewPos.getPosition() / 1000), (int) (viewPos.getDuration() / 1000), TextUtilKt.txt(R.string.resume_remaining, AccountManager.INSTANCE.secondsToReadable((int) ((viewPos.getDuration() - viewPos.getPosition()) / 1000), "0 mins"))) : null, zIsMovie, resultEpisode);
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$loadTrailers$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$loadTrailers$1", f = "ResultViewModel2.kt", i = {}, l = {2440}, m = "invokeSuspend", n = {}, nl = {2439}, s = {}, v = 2)
    static final class C08901 extends SuspendLambda implements Function3<CoroutineScope, ResultViewModel2, Continuation<? super Unit>, Object> {
        final /* synthetic */ LoadResponse $loadResponse;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08901(LoadResponse loadResponse, Continuation<? super C08901> continuation) {
            super(3, continuation);
            this.$loadResponse = loadResponse;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, ResultViewModel2 resultViewModel2, Continuation<? super Unit> continuation) {
            return ResultViewModel2.this.new C08901(this.$loadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableLiveData mutableLiveData;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutableLiveData mutableLiveData2 = ResultViewModel2.this._trailers;
                this.L$0 = mutableLiveData2;
                this.label = 1;
                Object trailers = ResultViewModel2.this.getTrailers(this.$loadResponse, 3, this);
                if (trailers == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = trailers;
                mutableLiveData = mutableLiveData2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableLiveData = (MutableLiveData) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            mutableLiveData.postValue(obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job loadTrailers(LoadResponse loadResponse) {
        return Coroutines.INSTANCE.ioSafe(this, new C08901(loadResponse, null));
    }

    static /* synthetic */ Object getTrailers$default(ResultViewModel2 resultViewModel2, LoadResponse loadResponse, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return resultViewModel2.getTrailers(loadResponse, i, continuation);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$getTrailers$2, reason: invalid class name */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lcom/lagradost/cloudstream3/ui/result/ExtractedTrailerData;", "Lkotlin/collections/ArrayList;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$getTrailers$2", f = "ResultViewModel2.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {2454}, m = "invokeSuspend", n = {"returnlist", "$this$takeWhile$iv", "list$iv", "item$iv", "list", "$i$f$takeWhile", "$i$a$-takeWhile-ResultViewModel2$getTrailers$2$1"}, nl = {2492}, s = {"L$0", "L$1", "L$2", "L$4", "L$5", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<ExtractedTrailerData>>, Object> {
        final /* synthetic */ int $limit;
        final /* synthetic */ LoadResponse $loadResponse;
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(LoadResponse loadResponse, int i, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$loadResponse = loadResponse;
            this.$limit = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$loadResponse, this.$limit, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ArrayList<ExtractedTrailerData>> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x005f  */
        /* JADX WARN: Code duplicated, block: B:13:0x0090 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:17:0x00b0 A[LOOP:0: B:15:0x00aa->B:17:0x00b0, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:20:0x00dc  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x008e -> B:14:0x0091). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 228
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.result.ResultViewModel2.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getTrailers(LoadResponse loadResponse, int i, Continuation<? super List<ExtractedTrailerData>> continuation) {
        return CoroutineScopeKt.coroutineScope(new AnonymousClass2(loadResponse, i, null), continuation);
    }

    private final void postPage(LoadResponse loadResponse, APIRepository apiRepository) {
        MutableLiveData<List<SearchResponse>> mutableLiveData = this._recommendations;
        List<SearchResponse> recommendations = loadResponse.getRecommendations();
        if (recommendations == null) {
            recommendations = CollectionsKt.emptyList();
        }
        mutableLiveData.postValue(recommendations);
        this._page.postValue(new Resource.Success(ResultViewModel2Kt.toResultData(loadResponse, apiRepository)));
    }

    public final boolean hasLoaded() {
        return this.currentResponse != null;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleAutoStart$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$handleAutoStart$1", f = "ResultViewModel2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C08841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        final /* synthetic */ AutoResume $autostart;
        int label;
        final /* synthetic */ ResultViewModel2 this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08841(AutoResume autoResume, Activity activity, ResultViewModel2 resultViewModel2, Continuation<? super C08841> continuation) {
            super(2, continuation);
            this.$autostart = autoResume;
            this.$activity = activity;
            this.this$0 = resultViewModel2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C08841(this.$autostart, this.$activity, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:25:0x005f  */
        /* JADX WARN: Code duplicated, block: B:27:0x0067  */
        /* JADX WARN: Code duplicated, block: B:29:0x0081  */
        /* JADX WARN: Code duplicated, block: B:32:0x008d  */
        /* JADX WARN: Code duplicated, block: B:44:0x00b6  */
        /* JADX WARN: Code duplicated, block: B:45:0x00b8  */
        /* JADX WARN: Code duplicated, block: B:48:0x00c4  */
        /* JADX WARN: Code duplicated, block: B:58:0x00eb  */
        /* JADX WARN: Code duplicated, block: B:60:0x00ee  */
        /* JADX WARN: Code duplicated, block: B:79:0x009a A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:80:0x00b0 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:84:0x0087 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:87:0x00d1 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:89:0x00e0 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:92:0x00be A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:93:0x00be A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Integer episode;
            ResultEpisode resultEpisode;
            AutoResume autoResume;
            int iIntValue;
            List list;
            ResultEpisode resultEpisode2;
            Integer season;
            Iterator it;
            Object next;
            ResultEpisode resultEpisode3;
            ResultEpisode resultEpisode4;
            Integer season2;
            Object next2;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AutoResume autoResume2 = this.$autostart;
            if (autoResume2 == null || this.$activity == null) {
                return Unit.INSTANCE;
            }
            int startAction = autoResume2.getStartAction();
            if (startAction == 1) {
                List<ResultEpisode> list2 = (List) this.this$0.currentEpisodes.get(this.this$0.currentIndex);
                if (list2 != null) {
                    ResultViewModel2 resultViewModel2 = this.this$0;
                    Activity activity = this.$activity;
                    for (ResultEpisode resultEpisode5 : list2) {
                        if (ResultFragmentKt.getWatchProgress(resultEpisode5) <= 0.9d) {
                            resultViewModel2.handleAction(new EpisodeClickEvent(EpisodeAdapter.INSTANCE.getPlayerAction(activity), resultEpisode5));
                            break;
                        }
                    }
                }
            } else if (startAction == 2) {
                List listFlatten = CollectionsKt.flatten(this.this$0.currentEpisodes.values());
                Integer id = this.$autostart.getId();
                Object obj2 = null;
                if (id == null) {
                    episode = this.$autostart.getEpisode();
                    if (episode != null) {
                        ResultViewModel2 resultViewModel3 = this.this$0;
                        autoResume = this.$autostart;
                        iIntValue = episode.intValue();
                        list = (List) resultViewModel3.currentEpisodes.get(resultViewModel3.currentIndex);
                        if (list != null) {
                            it = list.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    next = null;
                                    break;
                                }
                                next = it.next();
                                resultEpisode4 = (ResultEpisode) next;
                                if (resultEpisode4.getEpisode() == iIntValue) {
                                    season2 = resultEpisode4.getSeason();
                                    int iIntValue2 = autoResume.getEpisode().intValue();
                                    if (season2 != null) {
                                        break;
                                        break;
                                    }
                                    continue;
                                }
                            }
                            resultEpisode3 = (ResultEpisode) next;
                            if (resultEpisode3 == null) {
                                for (Object obj3 : listFlatten) {
                                    resultEpisode2 = (ResultEpisode) obj3;
                                    if (resultEpisode2.getEpisode() == iIntValue) {
                                        season = resultEpisode2.getSeason();
                                        int iIntValue3 = autoResume.getEpisode().intValue();
                                        if (season != null) {
                                            obj2 = obj3;
                                            break;
                                        }
                                        continue;
                                    }
                                }
                                obj2 = (ResultEpisode) obj2;
                            } else {
                                obj2 = resultEpisode3;
                            }
                        } else {
                            while (r8.hasNext()) {
                                resultEpisode2 = (ResultEpisode) obj3;
                                if (resultEpisode2.getEpisode() == iIntValue) {
                                    season = resultEpisode2.getSeason();
                                    int iIntValue4 = autoResume.getEpisode().intValue();
                                    if (season != null) {
                                        obj2 = obj3;
                                        break;
                                    }
                                    continue;
                                }
                            }
                            obj2 = (ResultEpisode) obj2;
                        }
                    }
                    if (obj2 == null) {
                        return Unit.INSTANCE;
                    }
                    resultEpisode = obj2;
                } else {
                    int iIntValue5 = id.intValue();
                    Iterator it2 = listFlatten.iterator();
                    do {
                        if (!it2.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it2.next();
                    } while (((ResultEpisode) next2).getId() != iIntValue5);
                    ResultEpisode resultEpisode6 = (ResultEpisode) next2;
                    resultEpisode = resultEpisode6;
                    if (resultEpisode6 == null) {
                        episode = this.$autostart.getEpisode();
                        if (episode != null) {
                            ResultViewModel2 resultViewModel4 = this.this$0;
                            autoResume = this.$autostart;
                            iIntValue = episode.intValue();
                            list = (List) resultViewModel4.currentEpisodes.get(resultViewModel4.currentIndex);
                            if (list != null) {
                                it = list.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        next = null;
                                        break;
                                    }
                                    next = it.next();
                                    resultEpisode4 = (ResultEpisode) next;
                                    if (resultEpisode4.getEpisode() == iIntValue) {
                                        season2 = resultEpisode4.getSeason();
                                        int iIntValue6 = autoResume.getEpisode().intValue();
                                        if (season2 != null && season2.intValue() == iIntValue6) {
                                            break;
                                        }
                                    }
                                }
                                resultEpisode3 = (ResultEpisode) next;
                                if (resultEpisode3 == null) {
                                    while (r8.hasNext()) {
                                        resultEpisode2 = (ResultEpisode) obj3;
                                        if (resultEpisode2.getEpisode() == iIntValue) {
                                            season = resultEpisode2.getSeason();
                                            int iIntValue7 = autoResume.getEpisode().intValue();
                                            if (season != null && season.intValue() == iIntValue7) {
                                                obj2 = obj3;
                                                break;
                                            }
                                        }
                                    }
                                    obj2 = (ResultEpisode) obj2;
                                } else {
                                    obj2 = resultEpisode3;
                                }
                            } else {
                                while (r8.hasNext()) {
                                    resultEpisode2 = (ResultEpisode) obj3;
                                    if (resultEpisode2.getEpisode() == iIntValue) {
                                        season = resultEpisode2.getSeason();
                                        int iIntValue8 = autoResume.getEpisode().intValue();
                                        if (season != null) {
                                            obj2 = obj3;
                                            break;
                                        }
                                        continue;
                                    }
                                }
                                obj2 = (ResultEpisode) obj2;
                            }
                        }
                        if (obj2 == null) {
                            return Unit.INSTANCE;
                        }
                        resultEpisode = obj2;
                    }
                }
                this.this$0.handleAction(new EpisodeClickEvent(EpisodeAdapter.INSTANCE.getPlayerAction(this.$activity), resultEpisode));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job handleAutoStart(Activity activity, AutoResume autostart) {
        return ArchComponentExtKt.launchSafe$default(ViewModelKt.getViewModelScope(this), null, null, new C08841(autostart, activity, this, null), 3, null);
    }

    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010$\n\u0002\bX\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0097\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\n\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000f\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019\u0012\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001b\u0012\u0016\b\u0002\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001d\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010!\u001a\u00020\u0003\u0012\b\u0010\"\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b#\u0010$J\t\u0010]\u001a\u00020\u0003HÆ\u0003J\t\u0010^\u001a\u00020\u0003HÆ\u0003J\t\u0010_\u001a\u00020\u0003HÆ\u0003J\t\u0010`\u001a\u00020\u0007HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010b\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u00104J\u000b\u0010c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\rHÆ\u0003J\u0011\u0010e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000fHÆ\u0003J\u0010\u0010f\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u00104J\u000f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012HÆ\u0003J\u0011\u0010h\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000fHÆ\u0003J\u0011\u0010i\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u000fHÆ\u0003J\t\u0010j\u001a\u00020\u0019HÆ\u0003J\u0015\u0010k\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001bHÆ\u0003J\u0017\u0010l\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001dHÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010p\u001a\u00020\u0003HÆ\u0003J\u0010\u0010q\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u00104Jª\u0002\u0010r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\n2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u000f2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001b2\u0016\b\u0002\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010sJ\u0014\u0010t\u001a\u00020\u00192\b\u0010u\u001a\u0004\u0018\u00010vHÖ\u0083\u0004J\n\u0010w\u001a\u00020\nHÖ\u0081\u0004J\n\u0010x\u001a\u00020\u0003HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\u0004\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010&\"\u0004\b*\u0010(R\u001a\u0010\u0005\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010&\"\u0004\b,\u0010(R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010&\"\u0004\b2\u0010(R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u0010\n\u0002\u00107\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010&\"\u0004\b9\u0010(R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\"\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u0010\n\u0002\u00107\u001a\u0004\bB\u00104\"\u0004\bC\u00106R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010?\"\u0004\bE\u0010AR\"\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010?\"\u0004\bG\u0010AR\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010?\"\u0004\bI\u0010AR\u001a\u0010\u0018\u001a\u00020\u0019X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR&\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR(\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001dX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010O\"\u0004\bS\u0010QR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010&\"\u0004\bU\u0010(R\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010&\"\u0004\bW\u0010(R\u001c\u0010 \u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010&\"\u0004\bY\u0010(R\u001a\u0010!\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010&\"\u0004\b[\u0010(R\u0015\u0010\"\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u00107\u001a\u0004\b\\\u00104¨\u0006y"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2$LoadResponseFromSearch;", "Lcom/lagradost/cloudstream3/LoadResponse;", "name", "", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "year", "", "plot", "score", "Lcom/lagradost/cloudstream3/Score;", "tags", "", TypedValues.TransitionType.S_DURATION, "trailers", "", "Lcom/lagradost/cloudstream3/TrailerData;", "recommendations", "Lcom/lagradost/cloudstream3/SearchResponse;", "actors", "Lcom/lagradost/cloudstream3/ActorData;", "comingSoon", "", "syncData", "", "posterHeaders", "", "backgroundPosterUrl", "logoUrl", "contentRating", "uniqueUrl", "id", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getUrl", "setUrl", "getApiName", "setApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getPlot", "setPlot", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "getDuration", "setDuration", "getTrailers", "setTrailers", "getRecommendations", "setRecommendations", "getActors", "setActors", "getComingSoon", "()Z", "setComingSoon", "(Z)V", "getSyncData", "()Ljava/util/Map;", "setSyncData", "(Ljava/util/Map;)V", "getPosterHeaders", "setPosterHeaders", "getBackgroundPosterUrl", "setBackgroundPosterUrl", "getLogoUrl", "setLogoUrl", "getContentRating", "setContentRating", "getUniqueUrl", "setUniqueUrl", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2$LoadResponseFromSearch;", "equals", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LoadResponseFromSearch implements LoadResponse {
        private List<ActorData> actors;
        private String apiName;
        private String backgroundPosterUrl;
        private boolean comingSoon;
        private String contentRating;
        private Integer duration;
        private final Integer id;
        private String logoUrl;
        private String name;
        private String plot;
        private Map<String, String> posterHeaders;
        private String posterUrl;
        private List<? extends SearchResponse> recommendations;
        private Score score;
        private Map<String, String> syncData;
        private List<String> tags;
        private List<TrailerData> trailers;
        private TvType type;
        private String uniqueUrl;
        private String url;
        private Integer year;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LoadResponseFromSearch copy$default(LoadResponseFromSearch loadResponseFromSearch, String str, String str2, String str3, TvType tvType, String str4, Integer num, String str5, Score score, List list, Integer num2, List list2, List list3, List list4, boolean z, Map map, Map map2, String str6, String str7, String str8, String str9, Integer num3, int i, Object obj) {
            Integer num4;
            String str10;
            String str11 = (i & 1) != 0 ? loadResponseFromSearch.name : str;
            String str12 = (i & 2) != 0 ? loadResponseFromSearch.url : str2;
            String str13 = (i & 4) != 0 ? loadResponseFromSearch.apiName : str3;
            TvType tvType2 = (i & 8) != 0 ? loadResponseFromSearch.type : tvType;
            String str14 = (i & 16) != 0 ? loadResponseFromSearch.posterUrl : str4;
            Integer num5 = (i & 32) != 0 ? loadResponseFromSearch.year : num;
            String str15 = (i & 64) != 0 ? loadResponseFromSearch.plot : str5;
            Score score2 = (i & 128) != 0 ? loadResponseFromSearch.score : score;
            List list5 = (i & 256) != 0 ? loadResponseFromSearch.tags : list;
            Integer num6 = (i & 512) != 0 ? loadResponseFromSearch.duration : num2;
            List list6 = (i & 1024) != 0 ? loadResponseFromSearch.trailers : list2;
            List list7 = (i & 2048) != 0 ? loadResponseFromSearch.recommendations : list3;
            List list8 = (i & 4096) != 0 ? loadResponseFromSearch.actors : list4;
            boolean z2 = (i & 8192) != 0 ? loadResponseFromSearch.comingSoon : z;
            String str16 = str11;
            Map map3 = (i & 16384) != 0 ? loadResponseFromSearch.syncData : map;
            Map map4 = (i & 32768) != 0 ? loadResponseFromSearch.posterHeaders : map2;
            String str17 = (i & 65536) != 0 ? loadResponseFromSearch.backgroundPosterUrl : str6;
            String str18 = (i & 131072) != 0 ? loadResponseFromSearch.logoUrl : str7;
            String str19 = (i & 262144) != 0 ? loadResponseFromSearch.contentRating : str8;
            String str20 = (i & 524288) != 0 ? loadResponseFromSearch.uniqueUrl : str9;
            if ((i & 1048576) != 0) {
                str10 = str20;
                num4 = loadResponseFromSearch.id;
            } else {
                num4 = num3;
                str10 = str20;
            }
            return loadResponseFromSearch.copy(str16, str12, str13, tvType2, str14, num5, str15, score2, list5, num6, list6, list7, list8, z2, map3, map4, str17, str18, str19, str10, num4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getDuration() {
            return this.duration;
        }

        public final List<TrailerData> component11() {
            return this.trailers;
        }

        public final List<SearchResponse> component12() {
            return this.recommendations;
        }

        public final List<ActorData> component13() {
            return this.actors;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final boolean getComingSoon() {
            return this.comingSoon;
        }

        public final Map<String, String> component15() {
            return this.syncData;
        }

        public final Map<String, String> component16() {
            return this.posterHeaders;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getBackgroundPosterUrl() {
            return this.backgroundPosterUrl;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final String getLogoUrl() {
            return this.logoUrl;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final String getContentRating() {
            return this.contentRating;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final String getUniqueUrl() {
            return this.uniqueUrl;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getApiName() {
            return this.apiName;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getPosterUrl() {
            return this.posterUrl;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getPlot() {
            return this.plot;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Score getScore() {
            return this.score;
        }

        public final List<String> component9() {
            return this.tags;
        }

        public final LoadResponseFromSearch copy(String name, String url, String apiName, TvType type, String posterUrl, Integer year, String plot, Score score, List<String> tags, Integer duration, List<TrailerData> trailers, List<? extends SearchResponse> recommendations, List<ActorData> actors, boolean comingSoon, Map<String, String> syncData, Map<String, String> posterHeaders, String backgroundPosterUrl, String logoUrl, String contentRating, String uniqueUrl, Integer id) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(trailers, "trailers");
            Intrinsics.checkNotNullParameter(syncData, "syncData");
            Intrinsics.checkNotNullParameter(uniqueUrl, "uniqueUrl");
            return new LoadResponseFromSearch(name, url, apiName, type, posterUrl, year, plot, score, tags, duration, trailers, recommendations, actors, comingSoon, syncData, posterHeaders, backgroundPosterUrl, logoUrl, contentRating, uniqueUrl, id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LoadResponseFromSearch)) {
                return false;
            }
            LoadResponseFromSearch loadResponseFromSearch = (LoadResponseFromSearch) other;
            return Intrinsics.areEqual(this.name, loadResponseFromSearch.name) && Intrinsics.areEqual(this.url, loadResponseFromSearch.url) && Intrinsics.areEqual(this.apiName, loadResponseFromSearch.apiName) && this.type == loadResponseFromSearch.type && Intrinsics.areEqual(this.posterUrl, loadResponseFromSearch.posterUrl) && Intrinsics.areEqual(this.year, loadResponseFromSearch.year) && Intrinsics.areEqual(this.plot, loadResponseFromSearch.plot) && Intrinsics.areEqual(this.score, loadResponseFromSearch.score) && Intrinsics.areEqual(this.tags, loadResponseFromSearch.tags) && Intrinsics.areEqual(this.duration, loadResponseFromSearch.duration) && Intrinsics.areEqual(this.trailers, loadResponseFromSearch.trailers) && Intrinsics.areEqual(this.recommendations, loadResponseFromSearch.recommendations) && Intrinsics.areEqual(this.actors, loadResponseFromSearch.actors) && this.comingSoon == loadResponseFromSearch.comingSoon && Intrinsics.areEqual(this.syncData, loadResponseFromSearch.syncData) && Intrinsics.areEqual(this.posterHeaders, loadResponseFromSearch.posterHeaders) && Intrinsics.areEqual(this.backgroundPosterUrl, loadResponseFromSearch.backgroundPosterUrl) && Intrinsics.areEqual(this.logoUrl, loadResponseFromSearch.logoUrl) && Intrinsics.areEqual(this.contentRating, loadResponseFromSearch.contentRating) && Intrinsics.areEqual(this.uniqueUrl, loadResponseFromSearch.uniqueUrl) && Intrinsics.areEqual(this.id, loadResponseFromSearch.id);
        }

        public int hashCode() {
            int iHashCode = ((((((this.name.hashCode() * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31) + this.type.hashCode()) * 31;
            String str = this.posterUrl;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Integer num = this.year;
            int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            String str2 = this.plot;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Score score = this.score;
            int data = (iHashCode4 + (score == null ? 0 : score.getData())) * 31;
            List<String> list = this.tags;
            int iHashCode5 = (data + (list == null ? 0 : list.hashCode())) * 31;
            Integer num2 = this.duration;
            int iHashCode6 = (((iHashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31) + this.trailers.hashCode()) * 31;
            List<? extends SearchResponse> list2 = this.recommendations;
            int iHashCode7 = (iHashCode6 + (list2 == null ? 0 : list2.hashCode())) * 31;
            List<ActorData> list3 = this.actors;
            int iHashCode8 = (((((iHashCode7 + (list3 == null ? 0 : list3.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.comingSoon)) * 31) + this.syncData.hashCode()) * 31;
            Map<String, String> map = this.posterHeaders;
            int iHashCode9 = (iHashCode8 + (map == null ? 0 : map.hashCode())) * 31;
            String str3 = this.backgroundPosterUrl;
            int iHashCode10 = (iHashCode9 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.logoUrl;
            int iHashCode11 = (iHashCode10 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.contentRating;
            int iHashCode12 = (((iHashCode11 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.uniqueUrl.hashCode()) * 31;
            Integer num3 = this.id;
            return iHashCode12 + (num3 != null ? num3.hashCode() : 0);
        }

        public String toString() {
            return "LoadResponseFromSearch(name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", year=" + this.year + ", plot=" + this.plot + ", score=" + this.score + ", tags=" + this.tags + ", duration=" + this.duration + ", trailers=" + this.trailers + ", recommendations=" + this.recommendations + ", actors=" + this.actors + ", comingSoon=" + this.comingSoon + ", syncData=" + this.syncData + ", posterHeaders=" + this.posterHeaders + ", backgroundPosterUrl=" + this.backgroundPosterUrl + ", logoUrl=" + this.logoUrl + ", contentRating=" + this.contentRating + ", uniqueUrl=" + this.uniqueUrl + ", id=" + this.id + ')';
        }

        public LoadResponseFromSearch(String name, String url, String apiName, TvType type, String str, Integer num, String str2, Score score, List<String> list, Integer num2, List<TrailerData> trailers, List<? extends SearchResponse> list2, List<ActorData> list3, boolean z, Map<String, String> syncData, Map<String, String> map, String str3, String str4, String str5, String uniqueUrl, Integer num3) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(trailers, "trailers");
            Intrinsics.checkNotNullParameter(syncData, "syncData");
            Intrinsics.checkNotNullParameter(uniqueUrl, "uniqueUrl");
            this.name = name;
            this.url = url;
            this.apiName = apiName;
            this.type = type;
            this.posterUrl = str;
            this.year = num;
            this.plot = str2;
            this.score = score;
            this.tags = list;
            this.duration = num2;
            this.trailers = trailers;
            this.recommendations = list2;
            this.actors = list3;
            this.comingSoon = z;
            this.syncData = syncData;
            this.posterHeaders = map;
            this.backgroundPosterUrl = str3;
            this.logoUrl = str4;
            this.contentRating = str5;
            this.uniqueUrl = uniqueUrl;
            this.id = num3;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public /* bridge */ Integer getRating() {
            return LoadResponse.CC.$default$getRating(this);
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public /* bridge */ void setRating(Integer num) {
            LoadResponse.CC.$default$setRating(this, num);
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public String getName() {
            return this.name;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.name = str;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public String getUrl() {
            return this.url;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.url = str;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public String getApiName() {
            return this.apiName;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setApiName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.apiName = str;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public TvType getType() {
            return this.type;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setType(TvType tvType) {
            Intrinsics.checkNotNullParameter(tvType, "<set-?>");
            this.type = tvType;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public String getPosterUrl() {
            return this.posterUrl;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setPosterUrl(String str) {
            this.posterUrl = str;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public Integer getYear() {
            return this.year;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setYear(Integer num) {
            this.year = num;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public String getPlot() {
            return this.plot;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setPlot(String str) {
            this.plot = str;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public Score getScore() {
            return this.score;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setScore(Score score) {
            this.score = score;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public List<String> getTags() {
            return this.tags;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setTags(List<String> list) {
            this.tags = list;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public Integer getDuration() {
            return this.duration;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setDuration(Integer num) {
            this.duration = num;
        }

        public /* synthetic */ LoadResponseFromSearch(String str, String str2, String str3, TvType tvType, String str4, Integer num, String str5, Score score, List list, Integer num2, List list2, List list3, List list4, boolean z, Map map, Map map2, String str6, String str7, String str8, String str9, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, tvType, str4, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : score, (i & 256) != 0 ? null : list, (i & 512) != 0 ? null : num2, (i & 1024) != 0 ? new ArrayList() : list2, (i & 2048) != 0 ? null : list3, (i & 4096) != 0 ? null : list4, (i & 8192) != 0 ? false : z, (i & 16384) != 0 ? new LinkedHashMap() : map, (32768 & i) != 0 ? null : map2, (65536 & i) != 0 ? null : str6, (131072 & i) != 0 ? null : str7, (262144 & i) != 0 ? null : str8, (i & 524288) != 0 ? str2 : str9, num3);
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public List<TrailerData> getTrailers() {
            return this.trailers;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setTrailers(List<TrailerData> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.trailers = list;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public List<SearchResponse> getRecommendations() {
            return this.recommendations;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setRecommendations(List<? extends SearchResponse> list) {
            this.recommendations = list;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public List<ActorData> getActors() {
            return this.actors;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setActors(List<ActorData> list) {
            this.actors = list;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public boolean getComingSoon() {
            return this.comingSoon;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setComingSoon(boolean z) {
            this.comingSoon = z;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public Map<String, String> getSyncData() {
            return this.syncData;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setSyncData(Map<String, String> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.syncData = map;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public Map<String, String> getPosterHeaders() {
            return this.posterHeaders;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setPosterHeaders(Map<String, String> map) {
            this.posterHeaders = map;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public String getBackgroundPosterUrl() {
            return this.backgroundPosterUrl;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setBackgroundPosterUrl(String str) {
            this.backgroundPosterUrl = str;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public String getLogoUrl() {
            return this.logoUrl;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setLogoUrl(String str) {
            this.logoUrl = str;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public String getContentRating() {
            return this.contentRating;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setContentRating(String str) {
            this.contentRating = str;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public String getUniqueUrl() {
            return this.uniqueUrl;
        }

        @Override // com.lagradost.cloudstream3.LoadResponse
        public void setUniqueUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.uniqueUrl = str;
        }

        public final Integer getId() {
            return this.id;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$loadSmall$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$loadSmall$1", f = "ResultViewModel2.kt", i = {0, 0, 0, 0, 0}, l = {2603}, m = "invokeSuspend", n = {ImagesContract.URL, "api", "repo", "response", "mainId"}, nl = {2610}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 2)
    static final class C08891 extends SuspendLambda implements Function3<CoroutineScope, ResultViewModel2, Continuation<? super Unit>, Object> {
        final /* synthetic */ SearchResponse $searchResponse;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ ResultViewModel2 this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08891(SearchResponse searchResponse, ResultViewModel2 resultViewModel2, Continuation<? super C08891> continuation) {
            super(3, continuation);
            this.$searchResponse = searchResponse;
            this.this$0 = resultViewModel2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, ResultViewModel2 resultViewModel2, Continuation<? super Unit> continuation) {
            return new C08891(this.$searchResponse, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String url = this.$searchResponse.getUrl();
                this.this$0._page.postValue(new Resource.Loading(url));
                this.this$0._episodes.postValue(new Resource.Loading(null, 1, null));
                MainAPI apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(this.$searchResponse.getApiName());
                if (apiFromNameNull == null && (apiFromNameNull = APIHolder.INSTANCE.getApiFromUrlNull(this.$searchResponse.getUrl())) == null) {
                    apiFromNameNull = APIRepository.INSTANCE.getNoneApi();
                }
                APIRepository aPIRepository = new APIRepository(apiFromNameNull);
                String name = this.$searchResponse.getName();
                String url2 = this.$searchResponse.getUrl();
                String name2 = apiFromNameNull.getName();
                TvType type = this.$searchResponse.getType();
                if (type == null) {
                    type = TvType.Others;
                }
                LoadResponseFromSearch loadResponseFromSearch = new LoadResponseFromSearch(name, url2, name2, type, this.$searchResponse.getPosterUrl(), null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, this.$searchResponse.getId(), 1048544, null);
                SearchResponse searchResponse = this.$searchResponse;
                if (searchResponse instanceof SyncAPI.LibraryItem) {
                    SyncAPI.LibraryItem libraryItem = (SyncAPI.LibraryItem) searchResponse;
                    loadResponseFromSearch.setPlot(libraryItem.getPlot());
                    Score personalRating = libraryItem.getPersonalRating();
                    if (personalRating == null) {
                        personalRating = libraryItem.getScore();
                    }
                    loadResponseFromSearch.setScore(personalRating);
                    loadResponseFromSearch.setTags(libraryItem.getTags());
                }
                if (searchResponse instanceof DataStoreHelper.BookmarkedData) {
                    DataStoreHelper.BookmarkedData bookmarkedData = (DataStoreHelper.BookmarkedData) searchResponse;
                    loadResponseFromSearch.setPlot(bookmarkedData.getPlot());
                    loadResponseFromSearch.setScore(bookmarkedData.getScore());
                    loadResponseFromSearch.setTags(bookmarkedData.getTags());
                }
                LoadResponseFromSearch loadResponseFromSearch2 = loadResponseFromSearch;
                int id = ResultViewModel2Kt.getId(loadResponseFromSearch2);
                this.L$0 = SpillingKt.nullOutSpilledVariable(url);
                this.L$1 = SpillingKt.nullOutSpilledVariable(apiFromNameNull);
                this.L$2 = SpillingKt.nullOutSpilledVariable(aPIRepository);
                this.L$3 = SpillingKt.nullOutSpilledVariable(loadResponseFromSearch);
                this.I$0 = id;
                this.label = 1;
                if (this.this$0.postSuccessful(loadResponseFromSearch2, id, aPIRepository, false, false, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final Job loadSmall(SearchResponse searchResponse) {
        Intrinsics.checkNotNullParameter(searchResponse, "searchResponse");
        return Coroutines.INSTANCE.ioSafe(this, new C08891(searchResponse, this, null));
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.result.ResultViewModel2$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/result/ResultViewModel2;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.ResultViewModel2$load$1", f = "ResultViewModel2.kt", i = {0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3}, l = {2642, 2661, 2668, 2693}, m = "invokeSuspend", n = {"$this$ioSafe", "api", "$this$ioSafe", "api", "validUrlResource", "validUrl", "repo", "$this$ioSafe", "api", "validUrlResource", "validUrl", "repo", "data", "$this$ioSafe", "api", "validUrlResource", "validUrl", "repo", "data", "loadResponse", "mainId"}, nl = {2649, 2662, 2671, 2700}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "I$0"}, v = 2)
    static final class C08861 extends SuspendLambda implements Function3<CoroutineScope, ResultViewModel2, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        final /* synthetic */ String $apiName;
        final /* synthetic */ AutoResume $autostart;
        final /* synthetic */ DubStatus $dubStatus;
        final /* synthetic */ boolean $loadTrailers;
        final /* synthetic */ boolean $showFillers;
        final /* synthetic */ String $url;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08861(String str, DubStatus dubStatus, boolean z, String str2, boolean z2, Activity activity, AutoResume autoResume, Continuation<? super C08861> continuation) {
            super(3, continuation);
            this.$url = str;
            this.$dubStatus = dubStatus;
            this.$showFillers = z;
            this.$apiName = str2;
            this.$loadTrailers = z2;
            this.$activity = activity;
            this.$autostart = autoResume;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, ResultViewModel2 resultViewModel2, Continuation<? super Unit> continuation) {
            C08861 c08861 = ResultViewModel2.this.new C08861(this.$url, this.$dubStatus, this.$showFillers, this.$apiName, this.$loadTrailers, this.$activity, this.$autostart, continuation);
            c08861.L$0 = coroutineScope;
            return c08861.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:36:0x0144  */
        /* JADX WARN: Code duplicated, block: B:37:0x014f  */
        /* JADX WARN: Code duplicated, block: B:39:0x0153  */
        /* JADX WARN: Code duplicated, block: B:41:0x0159  */
        /* JADX WARN: Code duplicated, block: B:43:0x015c  */
        /* JADX WARN: Code duplicated, block: B:46:0x0188  */
        /* JADX WARN: Code duplicated, block: B:49:0x0195  */
        /* JADX WARN: Code duplicated, block: B:51:0x0198  */
        /* JADX WARN: Code duplicated, block: B:53:0x01a6  */
        /* JADX WARN: Code duplicated, block: B:56:0x01c4  */
        /* JADX WARN: Code duplicated, block: B:59:0x01f3  */
        /* JADX WARN: Code duplicated, block: B:68:0x0259  */
        /* JADX WARN: Code duplicated, block: B:70:0x025d  */
        /* JADX WARN: Code duplicated, block: B:74:0x0268  */
        /* JADX WARN: Code duplicated, block: B:76:0x0270  */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x0243, code lost:
        
            if (r23.this$0.postSuccessful(r10, r11, r12, true, r23.$showFillers, r23) == r2) goto L62;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r24) throws com.lagradost.cloudstream3.mvvm.DebugException {
            /*
                Method dump skipped, instruction units count: 630
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.ui.result.ResultViewModel2.C08861.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Job load(Activity activity, String url, String apiName, boolean showFillers, DubStatus dubStatus, AutoResume autostart, boolean loadTrailers) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(dubStatus, "dubStatus");
        return Coroutines.INSTANCE.ioSafe(this, new C08861(url, dubStatus, showFillers, apiName, loadTrailers, activity, autostart, null));
    }
}
