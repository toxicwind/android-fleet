package com.lagradost.cloudstream3;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.kotlin.ExtensionsKt;
import com.google.android.gms.cast.CredentialsData;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.uwetrottmann.tmdb2.TmdbHelper;
import io.ktor.http.MimesKt;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLBuilderKt;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.encoding.Base64;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.time.Clock;
import kotlin.time.Instant;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.datetime.LocalDate;
import kotlinx.datetime.OverloadMarker;
import kotlinx.datetime.TimeZone;
import kotlinx.datetime.TimeZoneKt__TimeZoneJvmKt;
import kotlinx.datetime.format.DateTimeComponents;
import kotlinx.datetime.format.DateTimeComponentsKt;
import kotlinx.datetime.format.DateTimeFormat;
import kotlinx.datetime.format.DateTimeFormatBuilder;
import kotlinx.datetime.format.DateTimeFormatBuilderKt;
import kotlinx.datetime.format.UnicodeKt;
import kotlinx.datetime.internal.DateCalculationsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.mozilla.javascript.Context;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000Î\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00012\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a%\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u001a2\u0012\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u001c\"\u00020\u0014¢\u0006\u0002\u0010\u001d\u001a=\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u001a2*\u0010\u001b\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u001e0\u001c\"\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\u0010\u001f\u001a-\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u00012\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u001a2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010%\u001a-\u0010 \u001a\u00020!2\u0006\u0010&\u001a\u00020'2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u001a2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010(\u001a\u001f\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020)2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010*\u001a%\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020)0\u001a2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010+\u001a%\u0010,\u001a\u00020-2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u001a2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010.\u001a!\u0010/\u001a\u00020-*\b\u0012\u0004\u0012\u00020#0\u001a2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010.\u001a\u000e\u00100\u001a\u00020\u00012\u0006\u00101\u001a\u00020\u0001\u001a\u000e\u00102\u001a\u0002032\u0006\u00101\u001a\u00020\u0001\u001a\u000e\u00104\u001a\u00020\u00012\u0006\u00105\u001a\u000203\u001a\u0016\u00106\u001a\u0004\u0018\u00010\u0001*\u0002072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001\u001a\u0012\u00108\u001a\u00020\u0001*\u0002072\u0006\u0010\u0015\u001a\u00020\u0001\u001a\u001a\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u001a2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020:0<\u001a\u000e\u0010=\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u0001\u001a\u0012\u0010?\u001a\u0004\u0018\u00010\u00012\b\u0010>\u001a\u0004\u0018\u00010\u0001\u001a\u000e\u0010@\u001a\u00020\u00012\u0006\u0010>\u001a\u00020\u0001\u001a\u000e\u0010A\u001a\u00020BH\u0086@¢\u0006\u0002\u0010C\u001a\u0010\u0010D\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0015\u001a\u00020\u0001\u001a\u0012\u0010E\u001a\u0004\u0018\u00010\u00012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001\u001a\n\u0010F\u001a\u00020\u0018*\u00020G\u001a\n\u0010H\u001a\u00020\u0018*\u00020G\u001a\n\u0010I\u001a\u00020\u0018*\u00020G\u001a\n\u0010J\u001a\u00020\u0018*\u00020G\u001aI\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012)\b\u0002\u0010N\u001a#\b\u0001\u0012\u0004\u0012\u00020L\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0P\u0012\u0006\u0012\u0004\u0018\u00010R0O¢\u0006\u0002\bSH\u0086@¢\u0006\u0002\u0010T\u001aA\u0010U\u001a\u00020V2\u0006\u0010\u0015\u001a\u00020\u00012)\b\u0002\u0010N\u001a#\b\u0001\u0012\u0004\u0012\u00020V\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0P\u0012\u0006\u0012\u0004\u0018\u00010R0O¢\u0006\u0002\bSH\u0086@¢\u0006\u0002\u0010W\u001a\u0012\u0010X\u001a\u0004\u0018\u00010Y2\b\u00101\u001a\u0004\u0018\u00010\u0001\u001a\u0012\u0010Z\u001a\u00020\u0001*\u0002072\u0006\u0010\u0015\u001a\u00020\u0001\u001aI\u0010[\u001a\u00020\\*\u0002072\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010]\u001a\u00020G2\b\b\u0002\u0010^\u001a\u00020\u00182\u0019\b\u0002\u0010N\u001a\u0013\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020Q0_¢\u0006\u0002\bS\u001aI\u0010`\u001a\u00020a*\u0002072\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010]\u001a\u00020G2\b\b\u0002\u0010^\u001a\u00020\u00182\u0019\b\u0002\u0010N\u001a\u0013\u0012\u0004\u0012\u00020a\u0012\u0004\u0012\u00020Q0_¢\u0006\u0002\bS\u001aI\u0010b\u001a\u00020c*\u0002072\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010]\u001a\u00020G2\b\b\u0002\u0010^\u001a\u00020\u00182\u0019\b\u0002\u0010N\u001a\u0013\u0012\u0004\u0012\u00020c\u0012\u0004\u0012\u00020Q0_¢\u0006\u0002\bS\u001aI\u0010d\u001a\u00020e*\u0002072\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010]\u001a\u00020G2\b\b\u0002\u0010^\u001a\u00020\u00182\u0019\b\u0002\u0010N\u001a\u0013\u0012\u0004\u0012\u00020e\u0012\u0004\u0012\u00020Q0_¢\u0006\u0002\bS\u001aI\u0010f\u001a\u00020g*\u0002072\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010]\u001a\u00020G2\b\b\u0002\u0010^\u001a\u00020\u00182\u0019\b\u0002\u0010N\u001a\u0013\u0012\u0004\u0012\u00020g\u0012\u0004\u0012\u00020Q0_¢\u0006\u0002\bS\u001a\u0012\u0010h\u001a\u00020Q*\u00020#2\u0006\u0010i\u001a\u00020\u0001\u001a,\u0010j\u001a\u00020Q*\u00020#2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0016\b\u0002\u0010k\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0018\u00010l\u001a,\u0010j\u001a\u00020Q*\u00020m2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0016\b\u0002\u0010k\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0018\u00010l\u001a#\u0010n\u001a\u00020Q*\u00020g2\u0006\u0010o\u001a\u00020p2\n\b\u0002\u0010q\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010r\u001a#\u0010n\u001a\u00020Q*\u00020g2\u0006\u0010s\u001a\u00020\u00182\n\b\u0002\u0010q\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010t\u001a\u0019\u0010u\u001a\u00020Q*\u00020g2\b\u0010q\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010v\u001a\u0019\u0010w\u001a\u00020Q*\u00020g2\b\u0010q\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010v\u001a7\u0010n\u001a\u00020Q*\u00020g2\u0006\u0010x\u001a\u00020\u00182\u0006\u0010y\u001a\u00020\u00182\n\b\u0002\u0010z\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010{\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010|\u001a#\u0010n\u001a\u00020Q*\u00020g2\u0006\u0010o\u001a\u00020\u00012\n\b\u0002\u0010q\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010}\u001a\u0018\u0010~\u001a\u0004\u0018\u00010\u000f2\b\u0010\u007f\u001a\u0004\u0018\u00010\u0001¢\u0006\u0003\u0010\u0080\u0001\u001a\r\u0010\u0081\u0001\u001a\u00020\u0018*\u0004\u0018\u00010m\u001a\r\u0010\u0082\u0001\u001a\u00020\u0018*\u0004\u0018\u00010m\u001a\r\u0010\u0081\u0001\u001a\u00020\u0018*\u0004\u0018\u00010G\u001a\u000b\u0010\u0083\u0001\u001a\u00020\u0001*\u00020G\u001a#\u0010\u0084\u0001\u001a\u00020Q*\u00030\u0085\u00012\r\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020\u00010\u001aH\u0007¢\u0006\u0003\b\u0087\u0001\u001a$\u0010\u0084\u0001\u001a\u00020Q*\u00030\u0085\u00012\u000e\u0010\u0086\u0001\u001a\t\u0012\u0005\u0012\u00030\u0088\u00010\u001aH\u0007¢\u0006\u0003\b\u0089\u0001\u001ak\u0010\u008a\u0001\u001a\u00030\u008b\u0001*\u0002072\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u000b\b\u0002\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u00012\u000b\b\u0002\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u00012*\b\u0002\u0010N\u001a$\b\u0001\u0012\u0005\u0012\u00030\u008b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0P\u0012\u0006\u0012\u0004\u0018\u00010R0O¢\u0006\u0002\bSH\u0086@¢\u0006\u0003\u0010\u008e\u0001\u001a%\u0010\u008f\u0001\u001a\u00020Q*\u00030\u0090\u00012\u0006\u0010o\u001a\u00020p2\u000f\u0010q\u001a\u000b\u0012\u0005\u0012\u00030\u0091\u0001\u0018\u00010\u001a\u001ad\u0010\u0092\u0001\u001a\u00030\u0090\u0001*\u0002072\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010]\u001a\u00020G2\t\b\u0002\u0010\u0093\u0001\u001a\u00020\u00182*\b\u0002\u0010N\u001a$\b\u0001\u0012\u0005\u0012\u00030\u0090\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0P\u0012\u0006\u0012\u0004\u0018\u00010R0O¢\u0006\u0002\bSH\u0086@¢\u0006\u0003\u0010\u0094\u0001\u001aZ\u0010\u0095\u0001\u001a\u00030\u0096\u0001*\u0002072\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u0007\u0010\u0097\u0001\u001a\u00020\u00012*\b\u0002\u0010N\u001a$\b\u0001\u0012\u0005\u0012\u00030\u0096\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0P\u0012\u0006\u0012\u0004\u0018\u00010R0O¢\u0006\u0002\bSH\u0086@¢\u0006\u0003\u0010\u0098\u0001\u001ak\u0010\u0099\u0001\u001a\u00030\u009a\u0001\"\u0005\b\u0000\u0010\u009b\u0001*\u0002072\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010]\u001a\u00020G2\t\u0010&\u001a\u0005\u0018\u0001H\u009b\u00012*\b\u0002\u0010N\u001a$\b\u0001\u0012\u0005\u0012\u00030\u009a\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0P\u0012\u0006\u0012\u0004\u0018\u00010R0O¢\u0006\u0002\bSH\u0086@¢\u0006\u0003\u0010\u009c\u0001\u001ab\u0010\u0099\u0001\u001a\u00030\u009a\u0001*\u0002072\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010]\u001a\u00020G2\u0007\u0010\u0097\u0001\u001a\u00020\u00012*\b\u0002\u0010N\u001a$\b\u0001\u0012\u0005\u0012\u00030\u009a\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0P\u0012\u0006\u0012\u0004\u0018\u00010R0O¢\u0006\u0002\bSH\u0086@¢\u0006\u0003\u0010\u009d\u0001\u001a\"\u0010\u009e\u0001\u001a\u00020Q*\u00030\u0091\u00012\t\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u00012\t\b\u0002\u0010 \u0001\u001a\u00020\u0001\u001a\u001a\u0010\u009e\u0001\u001a\u00020Q*\u00030\u0091\u00012\n\u0010\u009f\u0001\u001a\u0005\u0018\u00010¡\u0001H\u0007\u001a\u001a\u0010\u009e\u0001\u001a\u00020Q*\u00030\u0091\u00012\n\u0010\u009f\u0001\u001a\u0005\u0018\u00010¢\u0001H\u0007\u001a\u0018\u0010\u009e\u0001\u001a\u00020Q*\u00030\u0091\u00012\n\u0010\u009f\u0001\u001a\u0005\u0018\u00010£\u0001\u001a:\u0010¤\u0001\u001a\u00030\u0091\u0001*\u0002072\u0006\u0010\u0015\u001a\u00020\u00012\u001a\b\u0002\u0010N\u001a\u0014\u0012\u0005\u0012\u00030\u0091\u0001\u0012\u0004\u0012\u00020Q0_¢\u0006\u0002\bS2\b\b\u0002\u0010^\u001a\u00020\u0018\u001a>\u0010¤\u0001\u001a\u00030\u0091\u0001\"\u0005\b\u0000\u0010\u009b\u0001*\u0002072\u0007\u0010&\u001a\u0003H\u009b\u00012\u001a\b\u0002\u0010N\u001a\u0014\u0012\u0005\u0012\u00030\u0091\u0001\u0012\u0004\u0012\u00020Q0_¢\u0006\u0002\bS¢\u0006\u0003\u0010¥\u0001\u001ah\u0010¦\u0001\u001a\u00030§\u0001*\u0002072\u0006\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010]\u001a\u00020G2\r\u0010q\u001a\t\u0012\u0005\u0012\u00030\u0091\u00010\u001a2*\b\u0002\u0010N\u001a$\b\u0001\u0012\u0005\u0012\u00030§\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0P\u0012\u0006\u0012\u0004\u0018\u00010R0O¢\u0006\u0002\bSH\u0086@¢\u0006\u0003\u0010¨\u0001\u001a\u0018\u0010©\u0001\u001a\b\u0012\u0004\u0012\u00020\u00010\u001a2\t\u0010ª\u0001\u001a\u0004\u0018\u00010\u0001\u001a\u0014\u0010«\u0001\u001a\u00020\u00182\t\u0010¬\u0001\u001a\u0004\u0018\u00010\u0001H\u0007\u001a\u0017\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u000f*\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0003\u0010\u0080\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u000e\u0010\r\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000¨\u0006®\u0001"}, d2 = {"AllLanguagesName", "", "USER_AGENT", "json", "Lkotlinx/serialization/json/Json;", "getJson$annotations", "()V", "getJson", "()Lkotlinx/serialization/json/Json;", "mapper", "Lcom/fasterxml/jackson/databind/json/JsonMapper;", "getMapper", "()Lcom/fasterxml/jackson/databind/json/JsonMapper;", MainAPIKt.PROVIDER_STATUS_KEY, "PROVIDER_STATUS_BETA_ONLY", "", "PROVIDER_STATUS_SLOW", "PROVIDER_STATUS_OK", "PROVIDER_STATUS_DOWN", "mainPage", "Lcom/lagradost/cloudstream3/MainPageData;", ImagesContract.URL, "name", "horizontalImages", "", "mainPageOf", "", "elements", "", "([Lcom/lagradost/cloudstream3/MainPageData;)Ljava/util/List;", "Lkotlin/Pair;", "([Lkotlin/Pair;)Ljava/util/List;", "newHomePageResponse", "Lcom/lagradost/cloudstream3/HomePageResponse;", "list", "Lcom/lagradost/cloudstream3/SearchResponse;", "hasNext", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;)Lcom/lagradost/cloudstream3/HomePageResponse;", "data", "Lcom/lagradost/cloudstream3/MainPageRequest;", "(Lcom/lagradost/cloudstream3/MainPageRequest;Ljava/util/List;Ljava/lang/Boolean;)Lcom/lagradost/cloudstream3/HomePageResponse;", "Lcom/lagradost/cloudstream3/HomePageList;", "(Lcom/lagradost/cloudstream3/HomePageList;Ljava/lang/Boolean;)Lcom/lagradost/cloudstream3/HomePageResponse;", "(Ljava/util/List;Ljava/lang/Boolean;)Lcom/lagradost/cloudstream3/HomePageResponse;", "newSearchResponseList", "Lcom/lagradost/cloudstream3/SearchResponseList;", "(Ljava/util/List;Ljava/lang/Boolean;)Lcom/lagradost/cloudstream3/SearchResponseList;", "toNewSearchResponseList", "base64Decode", TypedValues.Custom.S_STRING, "base64DecodeArray", "", "base64Encode", "array", "fixUrlNull", "Lcom/lagradost/cloudstream3/MainAPI;", "fixUrl", "sortUrls", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "urls", "", "capitalizeString", "str", "capitalizeStringNullable", "fixTitle", "getRhinoContext", "Lorg/mozilla/javascript/Context;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "imdbUrlToId", "imdbUrlToIdNullable", "isMovieType", "Lcom/lagradost/cloudstream3/TvType;", "isAudioType", "isLiveStream", "isAnimeOp", "newSubtitleFile", "Lcom/lagradost/cloudstream3/SubtitleFile;", "lang", "initializer", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newAudioFile", "Lcom/lagradost/cloudstream3/AudioFile;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getQualityFromString", "Lcom/lagradost/cloudstream3/SearchQuality;", "updateUrl", "newTorrentSearchResponse", "Lcom/lagradost/cloudstream3/TorrentSearchResponse;", "type", "fix", "Lkotlin/Function1;", "newMovieSearchResponse", "Lcom/lagradost/cloudstream3/MovieSearchResponse;", "newLiveSearchResponse", "Lcom/lagradost/cloudstream3/LiveSearchResponse;", "newTvSeriesSearchResponse", "Lcom/lagradost/cloudstream3/TvSeriesSearchResponse;", "newAnimeSearchResponse", "Lcom/lagradost/cloudstream3/AnimeSearchResponse;", "addQuality", "quality", "addPoster", "headers", "", "Lcom/lagradost/cloudstream3/LoadResponse;", "addDubStatus", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/DubStatus;", "episodes", "(Lcom/lagradost/cloudstream3/AnimeSearchResponse;Lcom/lagradost/cloudstream3/DubStatus;Ljava/lang/Integer;)V", "isDub", "(Lcom/lagradost/cloudstream3/AnimeSearchResponse;ZLjava/lang/Integer;)V", "addDub", "(Lcom/lagradost/cloudstream3/AnimeSearchResponse;Ljava/lang/Integer;)V", "addSub", "dubExist", "subExist", "dubEpisodes", "subEpisodes", "(Lcom/lagradost/cloudstream3/AnimeSearchResponse;ZZLjava/lang/Integer;Ljava/lang/Integer;)V", "(Lcom/lagradost/cloudstream3/AnimeSearchResponse;Ljava/lang/String;Ljava/lang/Integer;)V", "getDurationFromString", TvContractCompat.PARAM_INPUT, "(Ljava/lang/String;)Ljava/lang/Integer;", "isEpisodeBased", "isAnimeBased", "getFolderPrefix", "addSeasonNames", "Lcom/lagradost/cloudstream3/EpisodeResponse;", "names", "addSeasonNamesString", "Lcom/lagradost/cloudstream3/SeasonData;", "addSeasonNamesSeasonData", "newTorrentLoadResponse", "Lcom/lagradost/cloudstream3/TorrentLoadResponse;", "magnet", "torrent", "(Lcom/lagradost/cloudstream3/MainAPI;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addEpisodes", "Lcom/lagradost/cloudstream3/AnimeLoadResponse;", "Lcom/lagradost/cloudstream3/Episode;", "newAnimeLoadResponse", "comingSoonIfNone", "(Lcom/lagradost/cloudstream3/MainAPI;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;ZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newLiveStreamLoadResponse", "Lcom/lagradost/cloudstream3/LiveStreamLoadResponse;", "dataUrl", "(Lcom/lagradost/cloudstream3/MainAPI;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newMovieLoadResponse", "Lcom/lagradost/cloudstream3/MovieLoadResponse;", ExifInterface.GPS_DIRECTION_TRUE, "(Lcom/lagradost/cloudstream3/MainAPI;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcom/lagradost/cloudstream3/MainAPI;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addDate", "date", "format", "Lkotlinx/datetime/LocalDate;", "Lkotlin/time/Instant;", "Ljava/util/Date;", "newEpisode", "(Lcom/lagradost/cloudstream3/MainAPI;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lcom/lagradost/cloudstream3/Episode;", "newTvSeriesLoadResponse", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;", "(Lcom/lagradost/cloudstream3/MainAPI;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchUrls", "text", "isUpcoming", "dateString", "toRatingInt", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MainAPIKt {
    public static final String AllLanguagesName = "universal";
    public static final int PROVIDER_STATUS_BETA_ONLY = 3;
    public static final int PROVIDER_STATUS_DOWN = 0;
    public static final String PROVIDER_STATUS_KEY = "PROVIDER_STATUS_KEY";
    public static final int PROVIDER_STATUS_OK = 1;
    public static final int PROVIDER_STATUS_SLOW = 2;
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/149.0.0.0 Safari/537.36";
    private static final Json json = JsonKt.Json$default(null, new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return MainAPIKt.json$lambda$0((JsonBuilder) obj);
        }
    }, 1, null);
    private static final JsonMapper mapper;

    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TvType.values().length];
            try {
                iArr[TvType.AnimeMovie.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TvType.Live.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TvType.Movie.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TvType.Torrent.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TvType.Video.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[TvType.Audio.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[TvType.AudioBook.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[TvType.Music.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[TvType.Podcast.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[TvType.Anime.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[TvType.AsianDrama.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[TvType.Cartoon.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[TvType.TvSeries.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[TvType.CustomMedia.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[TvType.Documentary.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[TvType.NSFW.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[TvType.OVA.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[TvType.Others.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$getRhinoContext$1, reason: invalid class name */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt", f = "MainAPI.kt", i = {}, l = {806}, m = "getRhinoContext", n = {}, nl = {-1}, s = {}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainAPIKt.getRhinoContext(this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newAnimeLoadResponse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt", f = "MainAPI.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {2351}, m = "newAnimeLoadResponse", n = {"$this$newAnimeLoadResponse", "name", ImagesContract.URL, "type", "initializer", "builder", "comingSoonIfNone"}, nl = {2352}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0"}, v = 2)
    static final class C06941 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C06941(Continuation<? super C06941> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainAPIKt.newAnimeLoadResponse(null, null, null, null, false, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newAudioFile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt", f = "MainAPI.kt", i = {0, 0, 0}, l = {1215}, m = "newAudioFile", n = {ImagesContract.URL, "initializer", "builder"}, nl = {MimesKt.INITIAL_MIMES_LIST_SIZE}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class C06961 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C06961(Continuation<? super C06961> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainAPIKt.newAudioFile(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newLiveStreamLoadResponse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt", f = "MainAPI.kt", i = {0, 0, 0, 0, 0, 0}, l = {2408}, m = "newLiveStreamLoadResponse", n = {"$this$newLiveStreamLoadResponse", "name", ImagesContract.URL, "dataUrl", "initializer", "builder"}, nl = {2409}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 2)
    static final class C06981 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C06981(Continuation<? super C06981> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainAPIKt.newLiveStreamLoadResponse(null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newMovieLoadResponse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt", f = "MainAPI.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {2451, 2469}, m = "newMovieLoadResponse", n = {"$this$newMovieLoadResponse", "name", ImagesContract.URL, "type", "data", "initializer", "$this$newMovieLoadResponse", "name", ImagesContract.URL, "type", "data", "initializer", "dataUrl", "builder"}, nl = {2458, 2470}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"}, v = 2)
    static final class C07001<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C07001(Continuation<? super C07001> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainAPIKt.newMovieLoadResponse((MainAPI) null, (String) null, (String) null, (TvType) null, (Object) null, (Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object>) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newMovieLoadResponse$3, reason: invalid class name */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt", f = "MainAPI.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {2489}, m = "newMovieLoadResponse", n = {"$this$newMovieLoadResponse", "name", ImagesContract.URL, "type", "dataUrl", "initializer", "builder"}, nl = {2490}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class AnonymousClass3 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainAPIKt.newMovieLoadResponse((MainAPI) null, (String) null, (String) null, (TvType) null, (String) null, (Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object>) null, (Continuation<? super MovieLoadResponse>) this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newSubtitleFile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt", f = "MainAPI.kt", i = {0, 0, 0, 0}, l = {1187}, m = "newSubtitleFile", n = {"lang", ImagesContract.URL, "initializer", "builder"}, nl = {1189}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C07021 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C07021(Continuation<? super C07021> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainAPIKt.newSubtitleFile(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newTorrentLoadResponse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt", f = "MainAPI.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {2265}, m = "newTorrentLoadResponse", n = {"$this$newTorrentLoadResponse", "name", ImagesContract.URL, "magnet", "torrent", "initializer", "builder"}, nl = {2266}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class C07041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C07041(Continuation<? super C07041> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainAPIKt.newTorrentLoadResponse(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newTvSeriesLoadResponse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt", f = "MainAPI.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {2691}, m = "newTvSeriesLoadResponse", n = {"$this$newTvSeriesLoadResponse", "name", ImagesContract.URL, "type", "episodes", "initializer", "builder"}, nl = {2692}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class C07061 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C07061(Continuation<? super C07061> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MainAPIKt.newTvSeriesLoadResponse(null, null, null, null, null, null, this);
        }
    }

    public static /* synthetic */ void getJson$annotations() {
    }

    static {
        JsonMapper jsonMapperBuild = JsonMapper.builder().addModule(ExtensionsKt.kotlinModule$default(null, 1, null)).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();
        Intrinsics.checkNotNull(jsonMapperBuild);
        mapper = jsonMapperBuild;
    }

    public static final Json getJson() {
        return json;
    }

    static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setEncodeDefaults(true);
        Json.setExplicitNulls(false);
        Json.setIgnoreUnknownKeys(true);
        return Unit.INSTANCE;
    }

    public static final JsonMapper getMapper() {
        return mapper;
    }

    public static /* synthetic */ MainPageData mainPage$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return mainPage(str, str2, z);
    }

    public static final MainPageData mainPage(String url, String name, boolean z) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(name, "name");
        return new MainPageData(name, url, z);
    }

    public static final List<MainPageData> mainPageOf(MainPageData... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ArraysKt.toList(elements);
    }

    public static /* synthetic */ HomePageResponse newHomePageResponse$default(String str, List list, Boolean bool, int i, Object obj) {
        if ((i & 4) != 0) {
            bool = null;
        }
        return newHomePageResponse(str, (List<? extends SearchResponse>) list, bool);
    }

    public static final HomePageResponse newHomePageResponse(String name, List<? extends SearchResponse> list, Boolean bool) {
        boolean zBooleanValue;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(list, "list");
        List listListOf = CollectionsKt.listOf(new HomePageList(name, list, false, 4, null));
        if (bool != null) {
            zBooleanValue = bool.booleanValue();
        } else {
            zBooleanValue = !list.isEmpty();
        }
        return new HomePageResponse(listListOf, zBooleanValue);
    }

    public static /* synthetic */ HomePageResponse newHomePageResponse$default(MainPageRequest mainPageRequest, List list, Boolean bool, int i, Object obj) {
        if ((i & 4) != 0) {
            bool = null;
        }
        return newHomePageResponse(mainPageRequest, (List<? extends SearchResponse>) list, bool);
    }

    public static final HomePageResponse newHomePageResponse(MainPageRequest data, List<? extends SearchResponse> list, Boolean bool) {
        boolean zBooleanValue;
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(list, "list");
        List listListOf = CollectionsKt.listOf(new HomePageList(data.getName(), list, data.getHorizontalImages()));
        if (bool != null) {
            zBooleanValue = bool.booleanValue();
        } else {
            zBooleanValue = !list.isEmpty();
        }
        return new HomePageResponse(listListOf, zBooleanValue);
    }

    public static /* synthetic */ HomePageResponse newHomePageResponse$default(HomePageList homePageList, Boolean bool, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = null;
        }
        return newHomePageResponse(homePageList, bool);
    }

    public static final HomePageResponse newHomePageResponse(HomePageList list, Boolean bool) {
        boolean zBooleanValue;
        Intrinsics.checkNotNullParameter(list, "list");
        List listListOf = CollectionsKt.listOf(list);
        if (bool != null) {
            zBooleanValue = bool.booleanValue();
        } else {
            zBooleanValue = !list.getList().isEmpty();
        }
        return new HomePageResponse(listListOf, zBooleanValue);
    }

    public static /* synthetic */ HomePageResponse newHomePageResponse$default(List list, Boolean bool, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = null;
        }
        return newHomePageResponse((List<HomePageList>) list, bool);
    }

    public static final HomePageResponse newHomePageResponse(List<HomePageList> list, Boolean bool) {
        boolean zBooleanValue;
        Intrinsics.checkNotNullParameter(list, "list");
        if (bool != null) {
            zBooleanValue = bool.booleanValue();
        } else {
            List<HomePageList> list2 = list;
            if ((list2 instanceof Collection) && list2.isEmpty()) {
                zBooleanValue = false;
            } else {
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    if (!((HomePageList) it.next()).getList().isEmpty()) {
                        zBooleanValue = true;
                    }
                }
                zBooleanValue = false;
            }
        }
        return new HomePageResponse(list, zBooleanValue);
    }

    public static /* synthetic */ SearchResponseList newSearchResponseList$default(List list, Boolean bool, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = null;
        }
        return newSearchResponseList(list, bool);
    }

    public static final SearchResponseList newSearchResponseList(List<? extends SearchResponse> list, Boolean bool) {
        boolean zBooleanValue;
        Intrinsics.checkNotNullParameter(list, "list");
        if (bool != null) {
            zBooleanValue = bool.booleanValue();
        } else {
            zBooleanValue = !list.isEmpty();
        }
        return new SearchResponseList(list, zBooleanValue);
    }

    public static /* synthetic */ SearchResponseList toNewSearchResponseList$default(List list, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = null;
        }
        return toNewSearchResponseList(list, bool);
    }

    public static final SearchResponseList toNewSearchResponseList(List<? extends SearchResponse> list, Boolean bool) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return newSearchResponseList(list, bool);
    }

    public static final String base64Decode(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        byte[] bArrBase64DecodeArray = base64DecodeArray(string);
        StringBuilder sb = new StringBuilder(bArrBase64DecodeArray.length);
        for (byte b : bArrBase64DecodeArray) {
            sb.append((char) (b & 255));
        }
        return sb.toString();
    }

    public static final byte[] base64DecodeArray(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return Base64.decode$default(Base64.INSTANCE, string, 0, 0, 6, (Object) null);
    }

    public static final String base64Encode(byte[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return Base64.encode$default(Base64.INSTANCE, array, 0, 0, 6, null);
    }

    public static final String fixUrlNull(MainAPI mainAPI, String str) {
        Intrinsics.checkNotNullParameter(mainAPI, "<this>");
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        return fixUrl(mainAPI, str);
    }

    public static final String fixUrl(MainAPI mainAPI, String url) {
        Intrinsics.checkNotNullParameter(mainAPI, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        if (StringsKt.startsWith$default(url, "http", false, 2, (Object) null) || StringsKt.startsWith$default(url, "{\"", false, 2, (Object) null) || StringsKt.startsWith$default(url, "[", false, 2, (Object) null)) {
            return url;
        }
        String str = url;
        if (str.length() == 0) {
            return "";
        }
        if (StringsKt.startsWith$default(url, "//", false, 2, (Object) null)) {
            return "https:" + url;
        }
        if (StringsKt.startsWith$default((CharSequence) str, '/', false, 2, (Object) null)) {
            return mainAPI.getMainUrl() + url;
        }
        return mainAPI.getMainUrl() + '/' + url;
    }

    public static final List<ExtractorLink> sortUrls(Set<? extends ExtractorLink> urls) {
        Intrinsics.checkNotNullParameter(urls, "urls");
        return CollectionsKt.sortedWith(urls, new Comparator() { // from class: com.lagradost.cloudstream3.MainAPIKt$sortUrls$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(-((ExtractorLink) t).getQuality()), Integer.valueOf(-((ExtractorLink) t2).getQuality()));
            }
        });
    }

    public static final String capitalizeString(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        String strCapitalizeStringNullable = capitalizeStringNullable(str);
        return strCapitalizeStringNullable == null ? str : strCapitalizeStringNullable;
    }

    public static final String capitalizeStringNullable(String str) {
        if (str == null) {
            return null;
        }
        try {
            if (str.length() <= 0) {
                return str;
            }
            StringBuilder sb = new StringBuilder();
            sb.append((Object) CharsKt.titlecase(str.charAt(0)));
            String strSubstring = str.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            sb.append(strSubstring);
            return sb.toString();
        } catch (Exception unused) {
            return str;
        }
    }

    public static final String fixTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        return CollectionsKt.joinToString$default(StringsKt.split$default((CharSequence) str, new String[]{Stream.ID_UNKNOWN}, false, 0, 6, (Object) null), Stream.ID_UNKNOWN, null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainAPIKt.fixTitle$lambda$0((String) obj);
            }
        }, 30, null);
    }

    static final CharSequence fixTitle$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String lowerCase = it.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (lowerCase.length() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append((Object) CharsKt.titlecase(lowerCase.charAt(0)));
            String strSubstring = lowerCase.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            sb.append(strSubstring);
            lowerCase = sb.toString();
        }
        return lowerCase;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$getRhinoContext$2, reason: invalid class name */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, d2 = {"<anonymous>", "Lorg/mozilla/javascript/Context;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/utils/Coroutines;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt$getRhinoContext$2", f = "MainAPI.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function3<CoroutineScope, Coroutines, Continuation<? super Context>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, Coroutines coroutines, Continuation<? super Context> continuation) {
            return new AnonymousClass2(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Context contextEnter = Context.enter();
            contextEnter.initSafeStandardObjects();
            contextEnter.setInterpretedMode(true);
            return contextEnter;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object getRhinoContext(Continuation<? super Context> continuation) {
        AnonymousClass1 anonymousClass1;
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
        Object objMainWork = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objMainWork);
            Coroutines coroutines = Coroutines.INSTANCE;
            Coroutines coroutines2 = Coroutines.INSTANCE;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
            anonymousClass1.label = 1;
            objMainWork = coroutines.mainWork(coroutines2, anonymousClass2, anonymousClass1);
            if (objMainWork == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objMainWork);
        }
        Intrinsics.checkNotNullExpressionValue(objMainWork, "mainWork(...)");
        return objMainWork;
    }

    public static final String imdbUrlToId(String url) {
        List<String> groupValues;
        List<String> groupValues2;
        String str;
        Intrinsics.checkNotNullParameter(url, "url");
        String str2 = url;
        MatchResult matchResultFind$default = Regex.find$default(new Regex("/title/(tt[0-9]*)"), str2, 0, 2, null);
        if (matchResultFind$default != null && (groupValues2 = matchResultFind$default.getGroupValues()) != null && (str = groupValues2.get(1)) != null) {
            return str;
        }
        MatchResult matchResultFind$default2 = Regex.find$default(new Regex("tt[0-9]{5,}"), str2, 0, 2, null);
        if (matchResultFind$default2 == null || (groupValues = matchResultFind$default2.getGroupValues()) == null) {
            return null;
        }
        return groupValues.get(0);
    }

    public static final String imdbUrlToIdNullable(String str) {
        if (str == null) {
            return null;
        }
        return imdbUrlToId(str);
    }

    public static final boolean isMovieType(TvType tvType) {
        Intrinsics.checkNotNullParameter(tvType, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[tvType.ordinal()];
        return i == 1 || i == 2 || i == 3 || i == 4 || i == 5;
    }

    public static final boolean isAudioType(TvType tvType) {
        Intrinsics.checkNotNullParameter(tvType, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[tvType.ordinal()]) {
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
            default:
                return false;
        }
    }

    public static final boolean isLiveStream(TvType tvType) {
        Intrinsics.checkNotNullParameter(tvType, "<this>");
        return tvType == TvType.Live;
    }

    public static final boolean isAnimeOp(TvType tvType) {
        Intrinsics.checkNotNullParameter(tvType, "<this>");
        return tvType == TvType.Anime || tvType == TvType.OVA;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object newSubtitleFile(String str, String str2, Function2<? super SubtitleFile, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super SubtitleFile> continuation) {
        C07021 c07021;
        if (continuation instanceof C07021) {
            c07021 = (C07021) continuation;
            if ((c07021.label & Integer.MIN_VALUE) != 0) {
                c07021.label -= Integer.MIN_VALUE;
            } else {
                c07021 = new C07021(continuation);
            }
        } else {
            c07021 = new C07021(continuation);
        }
        Object obj = c07021.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07021.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            SubtitleFile subtitleFile = (SubtitleFile) c07021.L$3;
            ResultKt.throwOnFailure(obj);
            return subtitleFile;
        }
        ResultKt.throwOnFailure(obj);
        SubtitleFile subtitleFile2 = new SubtitleFile(str, str2);
        c07021.L$0 = SpillingKt.nullOutSpilledVariable(str);
        c07021.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        c07021.L$2 = SpillingKt.nullOutSpilledVariable(function2);
        c07021.L$3 = subtitleFile2;
        c07021.label = 1;
        return function2.invoke(subtitleFile2, c07021) == coroutine_suspended ? coroutine_suspended : subtitleFile2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newSubtitleFile$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/SubtitleFile;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt$newSubtitleFile$2", f = "MainAPI.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    public static final class C07032 extends SuspendLambda implements Function2<SubtitleFile, Continuation<? super Unit>, Object> {
        int label;

        C07032(Continuation<? super C07032> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07032(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SubtitleFile subtitleFile, Continuation<? super Unit> continuation) {
            return ((C07032) create(subtitleFile, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object newSubtitleFile$default(String str, String str2, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            function2 = new C07032(null);
        }
        return newSubtitleFile(str, str2, function2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public static final Object newAudioFile(String str, Function2<? super AudioFile, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super AudioFile> continuation) {
        C06961 c06961;
        if (continuation instanceof C06961) {
            c06961 = (C06961) continuation;
            if ((c06961.label & Integer.MIN_VALUE) != 0) {
                c06961.label -= Integer.MIN_VALUE;
            } else {
                c06961 = new C06961(continuation);
            }
        } else {
            c06961 = new C06961(continuation);
        }
        Object obj = c06961.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c06961.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AudioFile audioFile = (AudioFile) c06961.L$2;
            ResultKt.throwOnFailure(obj);
            return audioFile;
        }
        ResultKt.throwOnFailure(obj);
        AudioFile audioFile2 = new AudioFile(str, null, 2, null);
        c06961.L$0 = SpillingKt.nullOutSpilledVariable(str);
        c06961.L$1 = SpillingKt.nullOutSpilledVariable(function2);
        c06961.L$2 = audioFile2;
        c06961.label = 1;
        return function2.invoke(audioFile2, c06961) == coroutine_suspended ? coroutine_suspended : audioFile2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newAudioFile$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/AudioFile;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt$newAudioFile$2", f = "MainAPI.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    public static final class C06972 extends SuspendLambda implements Function2<AudioFile, Continuation<? super Unit>, Object> {
        int label;

        C06972(Continuation<? super C06972> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C06972(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AudioFile audioFile, Continuation<? super Unit> continuation) {
            return ((C06972) create(audioFile, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object newAudioFile$default(String str, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function2 = new C06972(null);
        }
        return newAudioFile(str, function2, continuation);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final SearchQuality getQualityFromString(String str) {
        if (str == null) {
            return null;
        }
        String lowerCase = StringsKt.trim((CharSequence) str).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String strReplace$default = StringsKt.replace$default(lowerCase, Stream.ID_UNKNOWN, "", false, 4, (Object) null);
        switch (strReplace$default.hashCode()) {
            case -1385972001:
                if (strReplace$default.equals("bluray")) {
                    return SearchQuality.BlueRay;
                }
                return null;
            case -1367739670:
                if (strReplace$default.equals("camrip")) {
                    return SearchQuality.CamRip;
                }
                return null;
            case -1360595129:
                if (strReplace$default.equals("telecine")) {
                    return SearchQuality.Telecine;
                }
                return null;
            case -1360103099:
                if (strReplace$default.equals("telesync")) {
                    return SearchQuality.Telesync;
                }
                return null;
            case -1327781603:
                if (strReplace$default.equals("highquality")) {
                    return SearchQuality.HQ;
                }
                return null;
            case -1319984697:
                if (strReplace$default.equals("dvdrip")) {
                    return SearchQuality.DVD;
                }
                return null;
            case -1319983920:
                if (strReplace$default.equals("dvdscr")) {
                    return SearchQuality.DVD;
                }
                return null;
            case -791790267:
                if (strReplace$default.equals("webrip")) {
                    return SearchQuality.WebRip;
                }
                return null;
            case -289214347:
                if (strReplace$default.equals("highdefinition")) {
                    return SearchQuality.HD;
                }
                return null;
            case -15830640:
                if (strReplace$default.equals("blueray")) {
                    return SearchQuality.BlueRay;
                }
                return null;
            case 1719:
                if (strReplace$default.equals("4k")) {
                    return SearchQuality.FourK;
                }
                return null;
            case 3152:
                if (strReplace$default.equals("br")) {
                    return SearchQuality.BlueRay;
                }
                return null;
            case 3324:
                if (strReplace$default.equals("hd")) {
                    return SearchQuality.HD;
                }
                return null;
            case 3337:
                if (strReplace$default.equals("hq")) {
                    return SearchQuality.HQ;
                }
                return null;
            case 3665:
                if (strReplace$default.equals("sd")) {
                    return SearchQuality.SD;
                }
                return null;
            case 3695:
                if (strReplace$default.equals("tc")) {
                    return SearchQuality.Telecine;
                }
                return null;
            case 3711:
                if (strReplace$default.equals(HlsSegmentFormat.TS)) {
                    return SearchQuality.Telesync;
                }
                return null;
            case 3801:
                if (strReplace$default.equals("wp")) {
                    return SearchQuality.WorkPrint;
                }
                return null;
            case 97643:
                if (strReplace$default.equals("blu")) {
                    return SearchQuality.BlueRay;
                }
                return null;
            case 98255:
                if (strReplace$default.equals("cam")) {
                    return SearchQuality.Cam;
                }
                return null;
            case 99858:
                if (strReplace$default.equals("dvd")) {
                    return SearchQuality.DVD;
                }
                return null;
            case 101346:
                if (strReplace$default.equals("fhd")) {
                    return SearchQuality.HD;
                }
                return null;
            case 103158:
                if (strReplace$default.equals("hdr")) {
                    return SearchQuality.HDR;
                }
                return null;
            case 112921:
                if (strReplace$default.equals("rip")) {
                    return SearchQuality.CamRip;
                }
                return null;
            case 113729:
                if (strReplace$default.equals("sdr")) {
                    return SearchQuality.SDR;
                }
                return null;
            case 115761:
                if (strReplace$default.equals("uhd")) {
                    return SearchQuality.UHD;
                }
                return null;
            case 117588:
                if (strReplace$default.equals(CredentialsData.CREDENTIALS_TYPE_WEB)) {
                    return SearchQuality.WebRip;
                }
                return null;
            case 3027034:
                if (strReplace$default.equals("blue")) {
                    return SearchQuality.BlueRay;
                }
                return null;
            case 3198059:
                if (strReplace$default.equals("hdtc")) {
                    return SearchQuality.HdCam;
                }
                return null;
            case 3198075:
                if (strReplace$default.equals("hdts")) {
                    return SearchQuality.HdCam;
                }
                return null;
            case 3198078:
                if (strReplace$default.equals("hdtv")) {
                    return SearchQuality.HD;
                }
                return null;
            case 99123539:
                if (strReplace$default.equals("hdcam")) {
                    return SearchQuality.HdCam;
                }
                return null;
            case 99138205:
                if (strReplace$default.equals("hdrip")) {
                    return SearchQuality.HD;
                }
                return null;
            case 113005276:
                if (strReplace$default.equals("webdl")) {
                    return SearchQuality.WebRip;
                }
                return null;
            case 1106161212:
                if (strReplace$default.equals("workprint")) {
                    return SearchQuality.WorkPrint;
                }
                return null;
            case 1312628413:
                if (strReplace$default.equals("standard")) {
                    return SearchQuality.SD;
                }
                return null;
            default:
                return null;
        }
    }

    public static final String updateUrl(MainAPI mainAPI, String url) {
        Intrinsics.checkNotNullParameter(mainAPI, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        try {
            Url Url = URLUtilsKt.Url(url);
            Url Url2 = URLUtilsKt.Url(mainAPI.getMainUrl());
            URLBuilder uRLBuilder = new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null);
            URLUtilsKt.takeFrom(uRLBuilder, Url2);
            uRLBuilder.setUser(Url.getUser());
            uRLBuilder.setPassword(Url.getPassword());
            URLBuilderKt.setEncodedPath(uRLBuilder, Url.getEncodedPath());
            uRLBuilder.setFragment(Url.getFragment());
            uRLBuilder.getParameters().clear();
            uRLBuilder.getParameters().appendAll(Url.getParameters());
            return uRLBuilder.buildString();
        } catch (Throwable th) {
            ArchComponentExtKt.logError(th);
            return url;
        }
    }

    public static /* synthetic */ TorrentSearchResponse newTorrentSearchResponse$default(MainAPI mainAPI, String str, String str2, TvType tvType, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            tvType = TvType.Torrent;
        }
        TvType tvType2 = tvType;
        if ((i & 8) != 0) {
            z = true;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MainAPIKt.newTorrentSearchResponse$lambda$0((TorrentSearchResponse) obj2);
                }
            };
        }
        return newTorrentSearchResponse(mainAPI, str, str2, tvType2, z2, function1);
    }

    static final Unit newTorrentSearchResponse$lambda$0(TorrentSearchResponse torrentSearchResponse) {
        Intrinsics.checkNotNullParameter(torrentSearchResponse, "<this>");
        return Unit.INSTANCE;
    }

    public static final TorrentSearchResponse newTorrentSearchResponse(MainAPI mainAPI, String name, String str, TvType type, boolean z, Function1<? super TorrentSearchResponse, Unit> initializer) {
        String url = str;
        Intrinsics.checkNotNullParameter(mainAPI, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        if (z) {
            url = fixUrl(mainAPI, url);
        }
        TorrentSearchResponse torrentSearchResponse = new TorrentSearchResponse(name, url, mainAPI.getName(), type, null, null, null, null, 224, null);
        initializer.invoke(torrentSearchResponse);
        return torrentSearchResponse;
    }

    public static /* synthetic */ MovieSearchResponse newMovieSearchResponse$default(MainAPI mainAPI, String str, String str2, TvType tvType, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            tvType = TvType.Movie;
        }
        TvType tvType2 = tvType;
        if ((i & 8) != 0) {
            z = true;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MainAPIKt.newMovieSearchResponse$lambda$0((MovieSearchResponse) obj2);
                }
            };
        }
        return newMovieSearchResponse(mainAPI, str, str2, tvType2, z2, function1);
    }

    static final Unit newMovieSearchResponse$lambda$0(MovieSearchResponse movieSearchResponse) {
        Intrinsics.checkNotNullParameter(movieSearchResponse, "<this>");
        return Unit.INSTANCE;
    }

    public static final MovieSearchResponse newMovieSearchResponse(MainAPI mainAPI, String name, String str, TvType type, boolean z, Function1<? super MovieSearchResponse, Unit> initializer) {
        String url = str;
        Intrinsics.checkNotNullParameter(mainAPI, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        if (z) {
            url = fixUrl(mainAPI, url);
        }
        MovieSearchResponse movieSearchResponse = new MovieSearchResponse(name, url, mainAPI.getName(), type, null, null, null, null, null, null, 1008, null);
        initializer.invoke(movieSearchResponse);
        return movieSearchResponse;
    }

    public static /* synthetic */ LiveSearchResponse newLiveSearchResponse$default(MainAPI mainAPI, String str, String str2, TvType tvType, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            tvType = TvType.Live;
        }
        TvType tvType2 = tvType;
        if ((i & 8) != 0) {
            z = true;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MainAPIKt.newLiveSearchResponse$lambda$0((LiveSearchResponse) obj2);
                }
            };
        }
        return newLiveSearchResponse(mainAPI, str, str2, tvType2, z2, function1);
    }

    static final Unit newLiveSearchResponse$lambda$0(LiveSearchResponse liveSearchResponse) {
        Intrinsics.checkNotNullParameter(liveSearchResponse, "<this>");
        return Unit.INSTANCE;
    }

    public static final LiveSearchResponse newLiveSearchResponse(MainAPI mainAPI, String name, String str, TvType type, boolean z, Function1<? super LiveSearchResponse, Unit> initializer) {
        String url = str;
        Intrinsics.checkNotNullParameter(mainAPI, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        if (z) {
            url = fixUrl(mainAPI, url);
        }
        LiveSearchResponse liveSearchResponse = new LiveSearchResponse(name, url, mainAPI.getName(), type, null, null, null, null, null, null, 1008, null);
        initializer.invoke(liveSearchResponse);
        return liveSearchResponse;
    }

    public static /* synthetic */ TvSeriesSearchResponse newTvSeriesSearchResponse$default(MainAPI mainAPI, String str, String str2, TvType tvType, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            tvType = TvType.TvSeries;
        }
        TvType tvType2 = tvType;
        if ((i & 8) != 0) {
            z = true;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MainAPIKt.newTvSeriesSearchResponse$lambda$0((TvSeriesSearchResponse) obj2);
                }
            };
        }
        return newTvSeriesSearchResponse(mainAPI, str, str2, tvType2, z2, function1);
    }

    static final Unit newTvSeriesSearchResponse$lambda$0(TvSeriesSearchResponse tvSeriesSearchResponse) {
        Intrinsics.checkNotNullParameter(tvSeriesSearchResponse, "<this>");
        return Unit.INSTANCE;
    }

    public static final TvSeriesSearchResponse newTvSeriesSearchResponse(MainAPI mainAPI, String name, String str, TvType type, boolean z, Function1<? super TvSeriesSearchResponse, Unit> initializer) {
        String url = str;
        Intrinsics.checkNotNullParameter(mainAPI, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        if (z) {
            url = fixUrl(mainAPI, url);
        }
        TvSeriesSearchResponse tvSeriesSearchResponse = new TvSeriesSearchResponse(name, url, mainAPI.getName(), type, null, null, null, null, null, null, null, 2032, null);
        initializer.invoke(tvSeriesSearchResponse);
        return tvSeriesSearchResponse;
    }

    public static /* synthetic */ AnimeSearchResponse newAnimeSearchResponse$default(MainAPI mainAPI, String str, String str2, TvType tvType, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            tvType = TvType.Anime;
        }
        TvType tvType2 = tvType;
        if ((i & 8) != 0) {
            z = true;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MainAPIKt.newAnimeSearchResponse$lambda$0((AnimeSearchResponse) obj2);
                }
            };
        }
        return newAnimeSearchResponse(mainAPI, str, str2, tvType2, z2, function1);
    }

    static final Unit newAnimeSearchResponse$lambda$0(AnimeSearchResponse animeSearchResponse) {
        Intrinsics.checkNotNullParameter(animeSearchResponse, "<this>");
        return Unit.INSTANCE;
    }

    public static final AnimeSearchResponse newAnimeSearchResponse(MainAPI mainAPI, String name, String str, TvType type, boolean z, Function1<? super AnimeSearchResponse, Unit> initializer) {
        String url = str;
        Intrinsics.checkNotNullParameter(mainAPI, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        if (z) {
            url = fixUrl(mainAPI, url);
        }
        AnimeSearchResponse animeSearchResponse = new AnimeSearchResponse(name, url, mainAPI.getName(), type, null, null, null, null, null, null, null, null, null, 8176, null);
        initializer.invoke(animeSearchResponse);
        return animeSearchResponse;
    }

    public static final void addQuality(SearchResponse searchResponse, String quality) {
        Intrinsics.checkNotNullParameter(searchResponse, "<this>");
        Intrinsics.checkNotNullParameter(quality, "quality");
        searchResponse.setQuality(getQualityFromString(quality));
    }

    public static /* synthetic */ void addPoster$default(SearchResponse searchResponse, String str, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            map = null;
        }
        addPoster(searchResponse, str, (Map<String, String>) map);
    }

    public static final void addPoster(SearchResponse searchResponse, String str, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(searchResponse, "<this>");
        searchResponse.setPosterUrl(str);
        searchResponse.setPosterHeaders(map);
    }

    public static /* synthetic */ void addPoster$default(LoadResponse loadResponse, String str, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            map = null;
        }
        addPoster(loadResponse, str, (Map<String, String>) map);
    }

    public static final void addPoster(LoadResponse loadResponse, String str, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(loadResponse, "<this>");
        loadResponse.setPosterUrl(str);
        loadResponse.setPosterHeaders(map);
    }

    public static /* synthetic */ void addDubStatus$default(AnimeSearchResponse animeSearchResponse, DubStatus dubStatus, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        addDubStatus(animeSearchResponse, dubStatus, num);
    }

    public static final void addDubStatus(AnimeSearchResponse animeSearchResponse, DubStatus status, Integer num) {
        Intrinsics.checkNotNullParameter(animeSearchResponse, "<this>");
        Intrinsics.checkNotNullParameter(status, "status");
        Set<DubStatus> dubStatus = animeSearchResponse.getDubStatus();
        if (dubStatus != null) {
            dubStatus.add(status);
        } else {
            dubStatus = SetsKt.mutableSetOf(status);
        }
        animeSearchResponse.setDubStatus(dubStatus);
        TvType type = animeSearchResponse.getType();
        if ((type == null || !isMovieType(type)) && num != null && num.intValue() > 0) {
            animeSearchResponse.getEpisodes().put(status, num);
        }
    }

    public static /* synthetic */ void addDubStatus$default(AnimeSearchResponse animeSearchResponse, boolean z, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        addDubStatus(animeSearchResponse, z, num);
    }

    public static final void addDubStatus(AnimeSearchResponse animeSearchResponse, boolean z, Integer num) {
        Intrinsics.checkNotNullParameter(animeSearchResponse, "<this>");
        addDubStatus(animeSearchResponse, z ? DubStatus.Dubbed : DubStatus.Subbed, num);
    }

    public static final void addDub(AnimeSearchResponse animeSearchResponse, Integer num) {
        Intrinsics.checkNotNullParameter(animeSearchResponse, "<this>");
        if (num == null || num.intValue() <= 0) {
            return;
        }
        addDubStatus(animeSearchResponse, DubStatus.Dubbed, num);
    }

    public static final void addSub(AnimeSearchResponse animeSearchResponse, Integer num) {
        Intrinsics.checkNotNullParameter(animeSearchResponse, "<this>");
        if (num == null || num.intValue() <= 0) {
            return;
        }
        addDubStatus(animeSearchResponse, DubStatus.Subbed, num);
    }

    public static /* synthetic */ void addDubStatus$default(AnimeSearchResponse animeSearchResponse, boolean z, boolean z2, Integer num, Integer num2, int i, Object obj) {
        if ((i & 4) != 0) {
            num = null;
        }
        if ((i & 8) != 0) {
            num2 = null;
        }
        addDubStatus(animeSearchResponse, z, z2, num, num2);
    }

    public static final void addDubStatus(AnimeSearchResponse animeSearchResponse, boolean z, boolean z2, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(animeSearchResponse, "<this>");
        if (z) {
            addDubStatus(animeSearchResponse, DubStatus.Dubbed, num);
        }
        if (z2) {
            addDubStatus(animeSearchResponse, DubStatus.Subbed, num2);
        }
    }

    public static /* synthetic */ void addDubStatus$default(AnimeSearchResponse animeSearchResponse, String str, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        addDubStatus(animeSearchResponse, str, num);
    }

    public static final void addDubStatus(AnimeSearchResponse animeSearchResponse, String status, Integer num) {
        Intrinsics.checkNotNullParameter(animeSearchResponse, "<this>");
        Intrinsics.checkNotNullParameter(status, "status");
        String str = status;
        if (StringsKt.contains((CharSequence) str, (CharSequence) "(dub)", true)) {
            addDubStatus(animeSearchResponse, DubStatus.Dubbed, num);
        } else if (StringsKt.contains((CharSequence) str, (CharSequence) "(sub)", true)) {
            addDubStatus(animeSearchResponse, DubStatus.Subbed, num);
        }
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:48:0x00e9  */
    public static final Integer getDurationFromString(String str) throws IOException {
        String strReplace$default;
        List<String> groupValues;
        Integer intOrNull;
        List<String> groupValues2;
        if (str != null) {
            String str2 = str;
            String string = StringsKt.trim((CharSequence) str2).toString();
            if (string != null && (strReplace$default = StringsKt.replace$default(string, Stream.ID_UNKNOWN, "", false, 4, (Object) null)) != null) {
                Iterator it = Regex.findAll$default(new Regex("(\\d+\\shr)|(\\d+\\shour)|(\\d+\\smin)|(\\d+\\ssec)"), str2, 0, 2, null).iterator();
                int i = 0;
                while (it.hasNext()) {
                    String value = ((MatchResult) it.next()).getValue();
                    if (!StringsKt.isBlank(value)) {
                        StringBuilder sb = new StringBuilder();
                        int length = value.length();
                        for (int i2 = 0; i2 < length; i2++) {
                            char cCharAt = value.charAt(i2);
                            if (Character.isDigit(cCharAt)) {
                                sb.append(cCharAt);
                            }
                        }
                        int i3 = Integer.parseInt(StringsKt.trim((CharSequence) sb.toString()).toString());
                        StringBuilder sb2 = new StringBuilder();
                        int length2 = value.length();
                        for (int i4 = 0; i4 < length2; i4++) {
                            char cCharAt2 = value.charAt(i4);
                            if (!Character.isDigit(cCharAt2)) {
                                sb2.append(cCharAt2);
                            }
                        }
                        String string2 = StringsKt.trim((CharSequence) sb2.toString()).toString();
                        int iHashCode = string2.hashCode();
                        if (iHashCode != 3338) {
                            if (iHashCode != 108114) {
                                if (iHashCode != 113745) {
                                    if (iHashCode == 3208676 && string2.equals("hour")) {
                                        i3 *= DateCalculationsKt.SECONDS_PER_HOUR;
                                    }
                                } else if (!string2.equals("sec")) {
                                }
                                i3 = 0;
                            } else if (string2.equals("min")) {
                                i3 *= 60;
                            } else {
                                i3 = 0;
                            }
                        } else if (string2.equals("hr")) {
                            i3 *= DateCalculationsKt.SECONDS_PER_HOUR;
                        } else {
                            i3 = 0;
                        }
                        i += i3;
                    }
                }
                if (i > 0) {
                    return Integer.valueOf(i / 60);
                }
                String str3 = strReplace$default;
                MatchResult matchResultFind$default = Regex.find$default(new Regex("([0-9]*)h.*?([0-9]*)m"), str3, 0, 2, null);
                if (matchResultFind$default != null && (groupValues2 = matchResultFind$default.getGroupValues()) != null && groupValues2.size() == 3) {
                    Integer intOrNull2 = StringsKt.toIntOrNull(groupValues2.get(1));
                    Integer intOrNull3 = StringsKt.toIntOrNull(groupValues2.get(2));
                    if (intOrNull3 != null && intOrNull2 != null) {
                        return Integer.valueOf((intOrNull2.intValue() * 60) + intOrNull3.intValue());
                    }
                }
                MatchResult matchResultFind$default2 = Regex.find$default(new Regex("([0-9]*)m"), str3, 0, 2, null);
                if (matchResultFind$default2 != null && (groupValues = matchResultFind$default2.getGroupValues()) != null && groupValues.size() == 2 && (intOrNull = StringsKt.toIntOrNull(groupValues.get(1))) != null) {
                    return intOrNull;
                }
            }
        }
        return null;
    }

    public static final boolean isEpisodeBased(LoadResponse loadResponse) {
        return loadResponse != null && (loadResponse instanceof EpisodeResponse) && isEpisodeBased(loadResponse.getType());
    }

    public static final boolean isAnimeBased(LoadResponse loadResponse) {
        if (loadResponse == null) {
            return false;
        }
        return loadResponse.getType() == TvType.Anime || loadResponse.getType() == TvType.OVA;
    }

    public static final boolean isEpisodeBased(TvType tvType) {
        switch (tvType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[tvType.ordinal()]) {
            case 10:
            case 11:
            case 12:
            case 13:
                return true;
            default:
                return false;
        }
    }

    public static final String getFolderPrefix(TvType tvType) {
        Intrinsics.checkNotNullParameter(tvType, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[tvType.ordinal()]) {
            case 1:
                return "Movies";
            case 2:
                return "LiveStreams";
            case 3:
                return "Movies";
            case 4:
                return "Torrents";
            case 5:
                return "Videos";
            case 6:
                return "Audio";
            case 7:
                return "AudioBooks";
            case 8:
                return "Music";
            case 9:
                return "Podcasts";
            case 10:
                return "Anime";
            case 11:
                return "AsianDramas";
            case 12:
                return "Cartoons";
            case 13:
                return "TVSeries";
            case 14:
                return "Media";
            case 15:
                return "Documentaries";
            case 16:
                return "NSFW";
            case 17:
                return "OVAs";
            case 18:
                return "Others";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final void addSeasonNamesString(EpisodeResponse episodeResponse, List<String> names) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(episodeResponse, "<this>");
        Intrinsics.checkNotNullParameter(names, "names");
        if (names.isEmpty()) {
            arrayList = null;
        } else {
            List<String> list = names;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                arrayList2.add(new SeasonData(i2, (String) obj, null, 4, null));
                i = i2;
            }
            arrayList = arrayList2;
        }
        episodeResponse.setSeasonNames(arrayList);
    }

    public static final void addSeasonNamesSeasonData(EpisodeResponse episodeResponse, List<SeasonData> names) {
        Intrinsics.checkNotNullParameter(episodeResponse, "<this>");
        Intrinsics.checkNotNullParameter(names, "names");
        List<SeasonData> list = names;
        if (list.isEmpty()) {
            list = null;
        }
        episodeResponse.setSeasonNames(list);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object newTorrentLoadResponse(MainAPI mainAPI, String str, String str2, String str3, String str4, Function2<? super TorrentLoadResponse, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super TorrentLoadResponse> continuation) {
        C07041 c07041;
        String str5;
        if (continuation instanceof C07041) {
            c07041 = (C07041) continuation;
            if ((c07041.label & Integer.MIN_VALUE) != 0) {
                c07041.label -= Integer.MIN_VALUE;
            } else {
                c07041 = new C07041(continuation);
            }
        } else {
            c07041 = new C07041(continuation);
        }
        Object obj = c07041.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07041.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            TorrentLoadResponse torrentLoadResponse = (TorrentLoadResponse) c07041.L$6;
            ResultKt.throwOnFailure(obj);
            return torrentLoadResponse;
        }
        ResultKt.throwOnFailure(obj);
        String str6 = str3;
        TorrentLoadResponse torrentLoadResponse2 = new TorrentLoadResponse(str, str2, mainAPI.getName(), str3, str4, null, null, null, null, null, null, null, null, null, null, (str6 == null || StringsKt.isBlank(str6)) && ((str5 = str4) == null || StringsKt.isBlank(str5)), null, null, null, null, null, null, 4161472, null);
        c07041.L$0 = SpillingKt.nullOutSpilledVariable(mainAPI);
        c07041.L$1 = SpillingKt.nullOutSpilledVariable(str);
        c07041.L$2 = SpillingKt.nullOutSpilledVariable(str2);
        c07041.L$3 = SpillingKt.nullOutSpilledVariable(str3);
        c07041.L$4 = SpillingKt.nullOutSpilledVariable(str4);
        c07041.L$5 = SpillingKt.nullOutSpilledVariable(function2);
        c07041.L$6 = torrentLoadResponse2;
        c07041.label = 1;
        return function2.invoke(torrentLoadResponse2, c07041) == coroutine_suspended ? coroutine_suspended : torrentLoadResponse2;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newTorrentLoadResponse$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TorrentLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt$newTorrentLoadResponse$2", f = "MainAPI.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C07052 extends SuspendLambda implements Function2<TorrentLoadResponse, Continuation<? super Unit>, Object> {
        int label;

        C07052(Continuation<? super C07052> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07052(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(TorrentLoadResponse torrentLoadResponse, Continuation<? super Unit> continuation) {
            return ((C07052) create(torrentLoadResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object newTorrentLoadResponse$default(MainAPI mainAPI, String str, String str2, String str3, String str4, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        if ((i & 8) != 0) {
            str4 = null;
        }
        if ((i & 16) != 0) {
            function2 = new C07052(null);
        }
        return newTorrentLoadResponse(mainAPI, str, str2, str3, str4, function2, continuation);
    }

    public static final void addEpisodes(AnimeLoadResponse animeLoadResponse, DubStatus status, List<Episode> list) {
        Intrinsics.checkNotNullParameter(animeLoadResponse, "<this>");
        Intrinsics.checkNotNullParameter(status, "status");
        List<Episode> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        Map<DubStatus, List<Episode>> episodes = animeLoadResponse.getEpisodes();
        List<Episode> listEmptyList = animeLoadResponse.getEpisodes().get(status);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        episodes.put(status, CollectionsKt.plus((Collection) listEmptyList, (Iterable) list));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object newAnimeLoadResponse(MainAPI mainAPI, String str, String str2, TvType tvType, boolean z, Function2<? super AnimeLoadResponse, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super AnimeLoadResponse> continuation) {
        C06941 c06941;
        boolean z2;
        AnimeLoadResponse animeLoadResponse;
        if (continuation instanceof C06941) {
            c06941 = (C06941) continuation;
            if ((c06941.label & Integer.MIN_VALUE) != 0) {
                c06941.label -= Integer.MIN_VALUE;
            } else {
                c06941 = new C06941(continuation);
            }
        } else {
            c06941 = new C06941(continuation);
        }
        Object obj = c06941.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c06941.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AnimeLoadResponse animeLoadResponse2 = new AnimeLoadResponse(null, null, str, str2, mainAPI.getName(), tvType, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null, 134217667, null);
            c06941.L$0 = SpillingKt.nullOutSpilledVariable(mainAPI);
            c06941.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c06941.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            c06941.L$3 = SpillingKt.nullOutSpilledVariable(tvType);
            c06941.L$4 = SpillingKt.nullOutSpilledVariable(function2);
            c06941.L$5 = animeLoadResponse2;
            z2 = z;
            c06941.Z$0 = z2;
            c06941.label = 1;
            if (function2.invoke(animeLoadResponse2, c06941) == coroutine_suspended) {
                return coroutine_suspended;
            }
            animeLoadResponse = animeLoadResponse2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            boolean z3 = c06941.Z$0;
            animeLoadResponse = (AnimeLoadResponse) c06941.L$5;
            ResultKt.throwOnFailure(obj);
            z2 = z3;
        }
        if (z2) {
            animeLoadResponse.setComingSoon(true);
            Iterator<DubStatus> it = animeLoadResponse.getEpisodes().keySet().iterator();
            while (it.hasNext()) {
                List<Episode> list = animeLoadResponse.getEpisodes().get(it.next());
                if (list != null && !list.isEmpty()) {
                    animeLoadResponse.setComingSoon(false);
                    break;
                }
            }
        }
        return animeLoadResponse;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newAnimeLoadResponse$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/AnimeLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt$newAnimeLoadResponse$2", f = "MainAPI.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C06952 extends SuspendLambda implements Function2<AnimeLoadResponse, Continuation<? super Unit>, Object> {
        int label;

        C06952(Continuation<? super C06952> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C06952(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AnimeLoadResponse animeLoadResponse, Continuation<? super Unit> continuation) {
            return ((C06952) create(animeLoadResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object newAnimeLoadResponse$default(MainAPI mainAPI, String str, String str2, TvType tvType, boolean z, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            function2 = new C06952(null);
        }
        return newAnimeLoadResponse(mainAPI, str, str2, tvType, z2, function2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object newLiveStreamLoadResponse(MainAPI mainAPI, String str, String str2, String str3, Function2<? super LiveStreamLoadResponse, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super LiveStreamLoadResponse> continuation) {
        C06981 c06981;
        if (continuation instanceof C06981) {
            c06981 = (C06981) continuation;
            if ((c06981.label & Integer.MIN_VALUE) != 0) {
                c06981.label -= Integer.MIN_VALUE;
            } else {
                c06981 = new C06981(continuation);
            }
        } else {
            c06981 = new C06981(continuation);
        }
        Object obj = c06981.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c06981.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            LiveStreamLoadResponse liveStreamLoadResponse = (LiveStreamLoadResponse) c06981.L$5;
            ResultKt.throwOnFailure(obj);
            return liveStreamLoadResponse;
        }
        ResultKt.throwOnFailure(obj);
        LiveStreamLoadResponse liveStreamLoadResponse2 = new LiveStreamLoadResponse(str, str2, mainAPI.getName(), str3, null, null, null, null, null, null, null, null, null, null, StringsKt.isBlank(str3), null, null, null, null, null, null, 2080752, null);
        c06981.L$0 = SpillingKt.nullOutSpilledVariable(mainAPI);
        c06981.L$1 = SpillingKt.nullOutSpilledVariable(str);
        c06981.L$2 = SpillingKt.nullOutSpilledVariable(str2);
        c06981.L$3 = SpillingKt.nullOutSpilledVariable(str3);
        c06981.L$4 = SpillingKt.nullOutSpilledVariable(function2);
        c06981.L$5 = liveStreamLoadResponse2;
        c06981.label = 1;
        return function2.invoke(liveStreamLoadResponse2, c06981) == coroutine_suspended ? coroutine_suspended : liveStreamLoadResponse2;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newLiveStreamLoadResponse$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/LiveStreamLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt$newLiveStreamLoadResponse$2", f = "MainAPI.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C06992 extends SuspendLambda implements Function2<LiveStreamLoadResponse, Continuation<? super Unit>, Object> {
        int label;

        C06992(Continuation<? super C06992> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C06992(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(LiveStreamLoadResponse liveStreamLoadResponse, Continuation<? super Unit> continuation) {
            return ((C06992) create(liveStreamLoadResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object newLiveStreamLoadResponse$default(MainAPI mainAPI, String str, String str2, String str3, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            function2 = new C06992(null);
        }
        return newLiveStreamLoadResponse(mainAPI, str, str2, str3, function2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Object newMovieLoadResponse(MainAPI mainAPI, String str, String str2, TvType tvType, T t, Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super MovieLoadResponse> continuation) {
        C07001 c07001;
        String json2;
        if (continuation instanceof C07001) {
            c07001 = (C07001) continuation;
            if ((c07001.label & Integer.MIN_VALUE) != 0) {
                c07001.label -= Integer.MIN_VALUE;
            } else {
                c07001 = new C07001(continuation);
            }
        } else {
            c07001 = new C07001(continuation);
        }
        C07001 c07002 = c07001;
        Object obj = c07002.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07002.label;
        if (i != 0) {
            if (i == 1) {
                Object obj2 = c07002.L$4;
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            MovieLoadResponse movieLoadResponse = (MovieLoadResponse) c07002.L$7;
            Object obj3 = c07002.L$4;
            ResultKt.throwOnFailure(obj);
            return movieLoadResponse;
        }
        ResultKt.throwOnFailure(obj);
        if (t instanceof String) {
            c07002.L$0 = SpillingKt.nullOutSpilledVariable(mainAPI);
            c07002.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07002.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            c07002.L$3 = SpillingKt.nullOutSpilledVariable(tvType);
            c07002.L$4 = SpillingKt.nullOutSpilledVariable(t);
            c07002.L$5 = SpillingKt.nullOutSpilledVariable(function2);
            c07002.label = 1;
            Object objNewMovieLoadResponse = newMovieLoadResponse(mainAPI, str, str2, tvType, (String) t, function2, (Continuation<? super MovieLoadResponse>) c07002);
            if (objNewMovieLoadResponse != coroutine_suspended) {
                return objNewMovieLoadResponse;
            }
        } else {
            if (t == 0 || (json2 = AppUtils.INSTANCE.toJson(t)) == null) {
                json2 = "";
            }
            String str3 = json2;
            MovieLoadResponse movieLoadResponse2 = new MovieLoadResponse(str, str2, mainAPI.getName(), tvType, str3, null, null, null, null, null, null, null, null, null, StringsKt.isBlank(str3), null, null, null, null, null, null, 2080736, null);
            c07002.L$0 = SpillingKt.nullOutSpilledVariable(mainAPI);
            c07002.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07002.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            c07002.L$3 = SpillingKt.nullOutSpilledVariable(tvType);
            c07002.L$4 = SpillingKt.nullOutSpilledVariable(t);
            c07002.L$5 = SpillingKt.nullOutSpilledVariable(function2);
            c07002.L$6 = SpillingKt.nullOutSpilledVariable(str3);
            c07002.L$7 = movieLoadResponse2;
            c07002.label = 2;
            if (function2.invoke(movieLoadResponse2, c07002) != coroutine_suspended) {
                return movieLoadResponse2;
            }
        }
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newMovieLoadResponse$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt$newMovieLoadResponse$2", f = "MainAPI.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C07012 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        int label;

        C07012(Continuation<? super C07012> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07012(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MovieLoadResponse movieLoadResponse, Continuation<? super Unit> continuation) {
            return ((C07012) create(movieLoadResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object newMovieLoadResponse$default(MainAPI mainAPI, String str, String str2, TvType tvType, Object obj, Function2 function2, Continuation continuation, int i, Object obj2) {
        if ((i & 16) != 0) {
            function2 = new C07012(null);
        }
        return newMovieLoadResponse(mainAPI, str, str2, tvType, obj, (Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object>) function2, (Continuation<? super MovieLoadResponse>) continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object newMovieLoadResponse(MainAPI mainAPI, String str, String str2, TvType tvType, String str3, Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super MovieLoadResponse> continuation) {
        AnonymousClass3 anonymousClass3;
        if (continuation instanceof AnonymousClass3) {
            anonymousClass3 = (AnonymousClass3) continuation;
            if ((anonymousClass3.label & Integer.MIN_VALUE) != 0) {
                anonymousClass3.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass3 = new AnonymousClass3(continuation);
            }
        } else {
            anonymousClass3 = new AnonymousClass3(continuation);
        }
        Object obj = anonymousClass3.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass3.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            MovieLoadResponse movieLoadResponse = (MovieLoadResponse) anonymousClass3.L$6;
            ResultKt.throwOnFailure(obj);
            return movieLoadResponse;
        }
        ResultKt.throwOnFailure(obj);
        MovieLoadResponse movieLoadResponse2 = new MovieLoadResponse(str, str2, mainAPI.getName(), tvType, str3, null, null, null, null, null, null, null, null, null, StringsKt.isBlank(str3), null, null, null, null, null, null, 2080736, null);
        anonymousClass3.L$0 = SpillingKt.nullOutSpilledVariable(mainAPI);
        anonymousClass3.L$1 = SpillingKt.nullOutSpilledVariable(str);
        anonymousClass3.L$2 = SpillingKt.nullOutSpilledVariable(str2);
        anonymousClass3.L$3 = SpillingKt.nullOutSpilledVariable(tvType);
        anonymousClass3.L$4 = SpillingKt.nullOutSpilledVariable(str3);
        anonymousClass3.L$5 = SpillingKt.nullOutSpilledVariable(function2);
        anonymousClass3.L$6 = movieLoadResponse2;
        anonymousClass3.label = 1;
        return function2.invoke(movieLoadResponse2, anonymousClass3) == coroutine_suspended ? coroutine_suspended : movieLoadResponse2;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newMovieLoadResponse$4, reason: invalid class name */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt$newMovieLoadResponse$4", f = "MainAPI.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MovieLoadResponse movieLoadResponse, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(movieLoadResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object newMovieLoadResponse$default(MainAPI mainAPI, String str, String str2, TvType tvType, String str3, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 16) != 0) {
            function2 = new AnonymousClass4(null);
        }
        return newMovieLoadResponse(mainAPI, str, str2, tvType, str3, (Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object>) function2, (Continuation<? super MovieLoadResponse>) continuation);
    }

    public static /* synthetic */ void addDate$default(Episode episode, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = TmdbHelper.TMDB_DATE_PATTERN;
        }
        addDate(episode, str, str2);
    }

    public static final void addDate(Episode episode, String str, final String format) {
        Object objM8936constructorimpl;
        Object objM8936constructorimpl2;
        Object objM8936constructorimpl3;
        Intrinsics.checkNotNullParameter(episode, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        if (str == null) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            try {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl2 = Result.m8936constructorimpl(Long.valueOf(Instant.INSTANCE.parse(str).toEpochMilliseconds()));
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) != null) {
                DateTimeComponents dateTimeComponents = DateTimeComponentsKt.parse(DateTimeComponents.INSTANCE, str, DateTimeComponents.INSTANCE.Format(new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MainAPIKt.addDate$lambda$0$1$0(format, (DateTimeFormatBuilder.WithDateTimeComponents) obj);
                    }
                }));
                try {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM8936constructorimpl3 = Result.m8936constructorimpl(Long.valueOf(DateTimeComponents.toInstantUsingOffset$default(dateTimeComponents, null, 1, null).toEpochMilliseconds()));
                } catch (Throwable th2) {
                    Result.Companion companion5 = Result.INSTANCE;
                    objM8936constructorimpl3 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl3) != null) {
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        objM8936constructorimpl3 = Result.m8936constructorimpl(Long.valueOf(TimeZoneKt__TimeZoneJvmKt.toInstant$default(dateTimeComponents.toLocalDateTime(), TimeZone.INSTANCE.currentSystemDefault(), (OverloadMarker) null, 2, (Object) null).toEpochMilliseconds()));
                    } catch (Throwable th3) {
                        Result.Companion companion7 = Result.INSTANCE;
                        objM8936constructorimpl3 = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl3) != null) {
                    objM8936constructorimpl3 = Long.valueOf(TimeZoneKt__TimeZoneJvmKt.atStartOfDayIn$default(dateTimeComponents.toLocalDate(), TimeZone.INSTANCE.currentSystemDefault(), null, 2, null).toEpochMilliseconds());
                }
                objM8936constructorimpl2 = Long.valueOf(((Number) objM8936constructorimpl3).longValue());
            }
            objM8936constructorimpl = Result.m8936constructorimpl(Long.valueOf(((Number) objM8936constructorimpl2).longValue()));
        } catch (Throwable th4) {
            Result.Companion companion8 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th4));
        }
        Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(objM8936constructorimpl);
        if (thM8939exceptionOrNullimpl != null) {
            ArchComponentExtKt.logError(thM8939exceptionOrNullimpl);
        }
        episode.setDate((Long) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addDate$lambda$0$1$0(String str, DateTimeFormatBuilder.WithDateTimeComponents Format) {
        Intrinsics.checkNotNullParameter(Format, "$this$Format");
        UnicodeKt.byUnicodePattern(Format, str);
        return Unit.INSTANCE;
    }

    public static final void addDate(Episode episode, LocalDate localDate) {
        Instant instantAtStartOfDayIn$default;
        Intrinsics.checkNotNullParameter(episode, "<this>");
        Long lValueOf = null;
        if (localDate != null && (instantAtStartOfDayIn$default = TimeZoneKt__TimeZoneJvmKt.atStartOfDayIn$default(localDate, TimeZone.INSTANCE.currentSystemDefault(), null, 2, null)) != null) {
            lValueOf = Long.valueOf(instantAtStartOfDayIn$default.toEpochMilliseconds());
        }
        episode.setDate(lValueOf);
    }

    public static final void addDate(Episode episode, Instant instant) {
        Intrinsics.checkNotNullParameter(episode, "<this>");
        episode.setDate(instant != null ? Long.valueOf(instant.toEpochMilliseconds()) : null);
    }

    public static final void addDate(Episode episode, Date date) {
        Intrinsics.checkNotNullParameter(episode, "<this>");
        episode.setDate(date != null ? Long.valueOf(date.getTime()) : null);
    }

    public static /* synthetic */ Episode newEpisode$default(MainAPI mainAPI, String str, Function1 function1, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return MainAPIKt.newEpisode$lambda$0((Episode) obj2);
                }
            };
        }
        if ((i & 4) != 0) {
            z = true;
        }
        return newEpisode(mainAPI, str, function1, z);
    }

    static final Unit newEpisode$lambda$0(Episode episode) {
        Intrinsics.checkNotNullParameter(episode, "<this>");
        return Unit.INSTANCE;
    }

    public static final Episode newEpisode(MainAPI mainAPI, String url, Function1<? super Episode, Unit> initializer, boolean z) {
        Intrinsics.checkNotNullParameter(mainAPI, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        if (z) {
            url = fixUrl(mainAPI, url);
        }
        Episode episode = new Episode(url, null, null, null, null, null, null, null, null, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
        initializer.invoke(episode);
        return episode;
    }

    public static /* synthetic */ Episode newEpisode$default(MainAPI mainAPI, Object obj, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return MainAPIKt.newEpisode$lambda$1((Episode) obj3);
                }
            };
        }
        return newEpisode(mainAPI, obj, function1);
    }

    static final Unit newEpisode$lambda$1(Episode episode) {
        Intrinsics.checkNotNullParameter(episode, "<this>");
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Episode newEpisode(MainAPI mainAPI, T t, Function1<? super Episode, Unit> initializer) throws ErrorLoadingException {
        String json2;
        Intrinsics.checkNotNullParameter(mainAPI, "<this>");
        Intrinsics.checkNotNullParameter(initializer, "initializer");
        if (t instanceof String) {
            return newEpisode$default(mainAPI, (String) t, initializer, false, 4, null);
        }
        if (t != 0 && (json2 = AppUtils.INSTANCE.toJson(t)) != null) {
            Episode episode = new Episode(json2, null, null, null, null, null, null, null, null, TypedValues.PositionType.TYPE_POSITION_TYPE, null);
            initializer.invoke(episode);
            return episode;
        }
        throw new ErrorLoadingException("invalid newEpisode");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object newTvSeriesLoadResponse(MainAPI mainAPI, String str, String str2, TvType tvType, List<Episode> list, Function2<? super TvSeriesLoadResponse, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super TvSeriesLoadResponse> continuation) {
        C07061 c07061;
        if (continuation instanceof C07061) {
            c07061 = (C07061) continuation;
            if ((c07061.label & Integer.MIN_VALUE) != 0) {
                c07061.label -= Integer.MIN_VALUE;
            } else {
                c07061 = new C07061(continuation);
            }
        } else {
            c07061 = new C07061(continuation);
        }
        Object obj = c07061.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07061.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            TvSeriesLoadResponse tvSeriesLoadResponse = (TvSeriesLoadResponse) c07061.L$6;
            ResultKt.throwOnFailure(obj);
            return tvSeriesLoadResponse;
        }
        ResultKt.throwOnFailure(obj);
        TvSeriesLoadResponse tvSeriesLoadResponse2 = new TvSeriesLoadResponse(str, str2, mainAPI.getName(), tvType, list, null, null, null, null, null, null, null, null, null, null, list.isEmpty(), null, null, null, null, null, null, null, null, 16744416, null);
        c07061.L$0 = SpillingKt.nullOutSpilledVariable(mainAPI);
        c07061.L$1 = SpillingKt.nullOutSpilledVariable(str);
        c07061.L$2 = SpillingKt.nullOutSpilledVariable(str2);
        c07061.L$3 = SpillingKt.nullOutSpilledVariable(tvType);
        c07061.L$4 = SpillingKt.nullOutSpilledVariable(list);
        c07061.L$5 = SpillingKt.nullOutSpilledVariable(function2);
        c07061.L$6 = tvSeriesLoadResponse2;
        c07061.label = 1;
        return function2.invoke(tvSeriesLoadResponse2, c07061) == coroutine_suspended ? coroutine_suspended : tvSeriesLoadResponse2;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.MainAPIKt$newTvSeriesLoadResponse$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.MainAPIKt$newTvSeriesLoadResponse$2", f = "MainAPI.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C07072 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        int label;

        C07072(Continuation<? super C07072> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07072(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(TvSeriesLoadResponse tvSeriesLoadResponse, Continuation<? super Unit> continuation) {
            return ((C07072) create(tvSeriesLoadResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object newTvSeriesLoadResponse$default(MainAPI mainAPI, String str, String str2, TvType tvType, List list, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 16) != 0) {
            function2 = new C07072(null);
        }
        return newTvSeriesLoadResponse(mainAPI, str, str2, tvType, list, function2, continuation);
    }

    public static final List<String> fetchUrls(String str) {
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return CollectionsKt.emptyList();
        }
        return SequencesKt.toList(SequencesKt.map(Regex.findAll$default(new Regex("(https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*))"), str2, 0, 2, null), new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MainAPIKt.fetchUrls$lambda$0((MatchResult) obj);
            }
        }));
    }

    static final String fetchUrls$lambda$0(MatchResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.removeSurrounding(StringsKt.trim((CharSequence) it.getValue()).toString(), (CharSequence) "\"");
    }

    public static final boolean isUpcoming(String str) {
        Object objM8936constructorimpl;
        Object objM8936constructorimpl2;
        try {
            Result.Companion companion = Result.INSTANCE;
            DateTimeFormat<DateTimeComponents> dateTimeFormatFormat = DateTimeComponents.INSTANCE.Format(new Function1() { // from class: com.lagradost.cloudstream3.MainAPIKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MainAPIKt.isUpcoming$lambda$0$0((DateTimeFormatBuilder.WithDateTimeComponents) obj);
                }
            });
            DateTimeComponents.Companion companion2 = DateTimeComponents.INSTANCE;
            if (str == null) {
                return false;
            }
            DateTimeComponents dateTimeComponents = DateTimeComponentsKt.parse(companion2, str, dateTimeFormatFormat);
            boolean z = true;
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl2 = Result.m8936constructorimpl(DateTimeComponents.toInstantUsingOffset$default(dateTimeComponents, null, 1, null));
            } catch (Throwable th) {
                Result.Companion companion4 = Result.INSTANCE;
                objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) != null) {
                try {
                    Result.Companion companion5 = Result.INSTANCE;
                    objM8936constructorimpl2 = Result.m8936constructorimpl(TimeZoneKt__TimeZoneJvmKt.toInstant$default(dateTimeComponents.toLocalDateTime(), TimeZone.INSTANCE.currentSystemDefault(), (OverloadMarker) null, 2, (Object) null));
                } catch (Throwable th2) {
                    Result.Companion companion6 = Result.INSTANCE;
                    objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                }
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) != null) {
                objM8936constructorimpl2 = TimeZoneKt__TimeZoneJvmKt.atStartOfDayIn$default(dateTimeComponents.toLocalDate(), TimeZone.INSTANCE.currentSystemDefault(), null, 2, null);
            }
            if (Clock.System.INSTANCE.now().compareTo((Instant) objM8936constructorimpl2) >= 0) {
                z = false;
            }
            objM8936constructorimpl = Result.m8936constructorimpl(Boolean.valueOf(z));
        } catch (Throwable th3) {
            Result.Companion companion7 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
        }
        Throwable thM8939exceptionOrNullimpl = Result.m8939exceptionOrNullimpl(objM8936constructorimpl);
        if (thM8939exceptionOrNullimpl != null) {
            ArchComponentExtKt.logError(thM8939exceptionOrNullimpl);
        }
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            objM8936constructorimpl = false;
        }
        return ((Boolean) objM8936constructorimpl).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit isUpcoming$lambda$0$0(DateTimeFormatBuilder.WithDateTimeComponents Format) {
        Intrinsics.checkNotNullParameter(Format, "$this$Format");
        DateTimeFormatBuilder.WithDateTimeComponents withDateTimeComponents = Format;
        DateTimeFormatBuilder.WithYearMonth.CC.year$default(withDateTimeComponents, null, 1, null);
        DateTimeFormatBuilder.WithDateTimeComponents withDateTimeComponents2 = Format;
        DateTimeFormatBuilderKt.m10570char(withDateTimeComponents2, '-');
        DateTimeFormatBuilder.WithYearMonth.CC.monthNumber$default(withDateTimeComponents, null, 1, null);
        DateTimeFormatBuilderKt.m10570char(withDateTimeComponents2, '-');
        DateTimeFormatBuilder.WithDate.CC.day$default(Format, null, 1, null);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "toRatingInt() is deprecated. Use new score API instead.")
    public static final Integer toRatingInt(String str) {
        String strReplace$default;
        String string;
        Double doubleOrNull;
        if (str == null || (strReplace$default = StringsKt.replace$default(str, Stream.ID_UNKNOWN, "", false, 4, (Object) null)) == null || (string = StringsKt.trim((CharSequence) strReplace$default).toString()) == null || (doubleOrNull = StringsKt.toDoubleOrNull(string)) == null) {
            return null;
        }
        return Integer.valueOf((int) (Math.abs(doubleOrNull.doubleValue()) * 1000.0d));
    }

    public static final List<MainPageData> mainPageOf(Pair<String, String>... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        ArrayList arrayList = new ArrayList(elements.length);
        for (Pair<String, String> pair : elements) {
            arrayList.add(new MainPageData(pair.component2(), pair.component1(), false, 4, null));
        }
        return arrayList;
    }
}
