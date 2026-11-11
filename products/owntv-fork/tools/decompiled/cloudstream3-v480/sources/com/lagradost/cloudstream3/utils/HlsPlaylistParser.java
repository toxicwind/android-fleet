package com.lagradost.cloudstream3.utils;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import io.ktor.http.ContentType;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: HlsPlaylistParser.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b1\n\u0002\u0018\u0002\n\u0002\bG\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001:\u001c§\u0001¨\u0001©\u0001ª\u0001«\u0001¬\u0001\u00ad\u0001®\u0001¯\u0001°\u0001±\u0001²\u0001³\u0001´\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010|\u001a\u0002072\u0006\u0010}\u001a\u00020\u0005H\u0002J\u001a\u0010~\u001a\u00020\u007f2\u0007\u0010\u0080\u0001\u001a\u00020\u00052\u0007\u0010\u0081\u0001\u001a\u000207H\u0002J$\u0010\u0082\u0001\u001a\u00020\u007f2\u0007\u0010\u0080\u0001\u001a\u00020\u00052\u0007\u0010\u0081\u0001\u001a\u0002072\u0007\u0010\u0083\u0001\u001a\u00020\u007fH\u0002J1\u0010\u0084\u0001\u001a\u00020\u00052\u0007\u0010\u0080\u0001\u001a\u00020\u00052\u0007\u0010\u0081\u0001\u001a\u0002072\u0014\u0010\u0085\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0086\u0001H\u0002J@\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00052\u0007\u0010\u0080\u0001\u001a\u00020\u00052\u0007\u0010\u0081\u0001\u001a\u0002072\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u00052\u0016\u0010\u0085\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0086\u0001H\u0002J*\u0010\u0088\u0001\u001a\u00020\u00052\u0007\u0010\u0089\u0001\u001a\u00020\u00052\u0016\u0010\u0085\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0086\u0001H\u0002J5\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00052\u0007\u0010\u0080\u0001\u001a\u00020\u00052\u0007\u0010\u0081\u0001\u001a\u0002072\u0016\u0010\u0085\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0086\u0001H\u0002J4\u0010\u008a\u0001\u001a\u0005\u0018\u00010\u008b\u00012\u0007\u0010\u0080\u0001\u001a\u00020\u00052\u0007\u0010\u008c\u0001\u001a\u00020\u00052\u0014\u0010\u0085\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0086\u0001H\u0002J\u0012\u0010\u008d\u0001\u001a\u00020\u00052\u0007\u0010\u008e\u0001\u001a\u00020\u0005H\u0002J\u0012\u0010\u008f\u0001\u001a\u00020\u007f2\u0007\u0010\u0080\u0001\u001a\u00020\u0005H\u0002J(\u0010\u0090\u0001\u001a\u00020\u007f2\u0007\u0010\u0080\u0001\u001a\u00020\u00052\u0014\u0010\u0085\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0086\u0001H\u0002J&\u0010\u0091\u0001\u001a\u00030\u0092\u00012\u0007\u0010\u0080\u0001\u001a\u00020\u00052\u0007\u0010\u0081\u0001\u001a\u0002072\b\u0010\u0083\u0001\u001a\u00030\u0092\u0001H\u0002J2\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0094\u00012\u001b\u0010\u0095\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0094\u00010\u0096\u0001j\n\u0012\u0005\u0012\u00030\u0094\u0001`\u0097\u00012\u0007\u0010\u0098\u0001\u001a\u00020\u0005H\u0002J2\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u0094\u00012\u001b\u0010\u0095\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0094\u00010\u0096\u0001j\n\u0012\u0005\u0012\u00030\u0094\u0001`\u0097\u00012\u0007\u0010\u0098\u0001\u001a\u00020\u0005H\u0002J2\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u0094\u00012\u001b\u0010\u0095\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0094\u00010\u0096\u0001j\n\u0012\u0005\u0012\u00030\u0094\u0001`\u0097\u00012\u0007\u0010\u0098\u0001\u001a\u00020\u0005H\u0002J6\u0010\u009b\u0001\u001a\u00030\u0092\u00012\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u00052\t\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u00052\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00052\t\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u0005H\u0002J\u001c\u0010 \u0001\u001a\u0005\u0018\u00010¡\u00012\u0007\u0010¢\u0001\u001a\u00020\u00052\u0007\u0010£\u0001\u001a\u00020\u0005J#\u0010¤\u0001\u001a\u00030¡\u00012\u000e\u0010¥\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050¦\u00012\u0007\u0010¢\u0001\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010r\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010v\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010w\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010z\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010{\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006µ\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser;", "", "<init>", "()V", "LOG_TAG", "", "PLAYLIST_HEADER", "TAG_PREFIX", "TAG_VERSION", "TAG_PLAYLIST_TYPE", "TAG_DEFINE", "TAG_SERVER_CONTROL", "TAG_STREAM_INF", "TAG_PART_INF", "TAG_PART", "TAG_I_FRAME_STREAM_INF", "TAG_IFRAME", "TAG_MEDIA", "TAG_TARGET_DURATION", "TAG_DISCONTINUITY", "TAG_DISCONTINUITY_SEQUENCE", "TAG_PROGRAM_DATE_TIME", "TAG_INIT_SEGMENT", "TAG_INDEPENDENT_SEGMENTS", "TAG_MEDIA_DURATION", "TAG_MEDIA_SEQUENCE", "TAG_START", "TAG_ENDLIST", "TAG_KEY", "TAG_SESSION_KEY", "TAG_BYTERANGE", "TAG_GAP", "TAG_SKIP", "TAG_PRELOAD_HINT", "TAG_RENDITION_REPORT", "TAG_DATERANGE", "TYPE_AUDIO", "TYPE_VIDEO", "TYPE_SUBTITLES", "TYPE_CLOSED_CAPTIONS", "TYPE_PART", "TYPE_MAP", "METHOD_NONE", "METHOD_AES_128", "METHOD_SAMPLE_AES", "METHOD_SAMPLE_AES_CENC", "METHOD_SAMPLE_AES_CTR", "KEYFORMAT_PLAYREADY", "KEYFORMAT_IDENTITY", "KEYFORMAT_WIDEVINE_PSSH_BINARY", "KEYFORMAT_WIDEVINE_PSSH_JSON", "BOOLEAN_TRUE", "BOOLEAN_FALSE", "ATTR_CLOSED_CAPTIONS_NONE", "REGEX_AVERAGE_BANDWIDTH", "Lkotlin/text/Regex;", "REGEX_VIDEO", "REGEX_AUDIO", "REGEX_SUBTITLES", "REGEX_CLOSED_CAPTIONS", "REGEX_BANDWIDTH", "REGEX_CHANNELS", "REGEX_VIDEO_RANGE", "REGEX_CODECS", "REGEX_SUPPLEMENTAL_CODECS", "REGEX_RESOLUTION", "REGEX_FRAME_RATE", "REGEX_TARGET_DURATION", "REGEX_ATTR_DURATION", "REGEX_ATTR_DURATION_PREFIXED", "REGEX_PART_TARGET_DURATION", "REGEX_VERSION", "REGEX_PLAYLIST_TYPE", "REGEX_CAN_SKIP_UNTIL", "REGEX_CAN_SKIP_DATE_RANGES", "REGEX_SKIPPED_SEGMENTS", "REGEX_HOLD_BACK", "REGEX_PART_HOLD_BACK", "REGEX_CAN_BLOCK_RELOAD", "REGEX_MEDIA_SEQUENCE", "REGEX_MEDIA_DURATION", "REGEX_MEDIA_TITLE", "REGEX_LAST_MSN", "REGEX_LAST_PART", "REGEX_TIME_OFFSET", "REGEX_BYTERANGE", "REGEX_ATTR_BYTERANGE", "REGEX_BYTERANGE_START", "REGEX_BYTERANGE_LENGTH", "REGEX_METHOD", "REGEX_KEYFORMAT", "REGEX_KEYFORMATVERSIONS", "REGEX_URI", "REGEX_IV", "REGEX_TYPE", "REGEX_PRELOAD_HINT_TYPE", "REGEX_LANGUAGE", "REGEX_NAME", "REGEX_GROUP_ID", "REGEX_CHARACTERISTICS", "REGEX_INSTREAM_ID", "REGEX_AUTOSELECT", "REGEX_DEFAULT", "REGEX_FORCED", "REGEX_INDEPENDENT", "REGEX_GAP", "REGEX_PRECISE", "REGEX_VALUE", "REGEX_IMPORT", "REGEX_ID", "REGEX_CLASS", "REGEX_START_DATE", "REGEX_CUE", "REGEX_END_DATE", "REGEX_PLANNED_DURATION", "REGEX_END_ON_NEXT", "REGEX_ASSET_URI", "REGEX_ASSET_LIST_URI", "REGEX_RESUME_OFFSET", "REGEX_PLAYOUT_LIMIT", "REGEX_SNAP", "REGEX_RESTRICT", "REGEX_VARIABLE_REFERENCE", "REGEX_CLIENT_DEFINED_ATTRIBUTE_PREFIX", "compileBooleanAttrPattern", "attribute", "parseIntAttr", "", "line", "pattern", "parseOptionalIntAttr", "defaultValue", "parseStringAttr", "variableDefinitions", "", "parseOptionalStringAttr", "replaceVariableReferences", TypedValues.Custom.S_STRING, "parseDrmSchemeData", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$SchemeData;", "keyFormat", "parseEncryptionScheme", "method", "parseSelectionFlags", "parseRoleFlags", "parseOptionalBooleanAttribute", "", "getVariantWithVideoGroup", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Variant;", "variants", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "groupId", "getVariantWithAudioGroup", "getVariantWithSubtitleGroup", "isDolbyVisionFormat", "videoRange", "codecs", "supplementalCodecs", "supplementalProfiles", "parse", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$HlsMultivariantPlaylist;", "baseUri", "text", "parseMultivariantPlaylist", "iterator", "", "SchemeData", "Util", "UrlUtil", "C", "MimeTypes", "Mp4Box", "PsshAtomUtil", "ParserException", "DrmInitData", "Variant", "Rendition", "HlsMultivariantPlaylist", "VariantInfo", "Format", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HlsPlaylistParser {
    private static final String ATTR_CLOSED_CAPTIONS_NONE = "CLOSED-CAPTIONS=NONE";
    private static final String BOOLEAN_FALSE = "NO";
    private static final String BOOLEAN_TRUE = "YES";
    public static final HlsPlaylistParser INSTANCE;
    private static final String KEYFORMAT_IDENTITY = "identity";
    private static final String KEYFORMAT_PLAYREADY = "com.microsoft.playready";
    private static final String KEYFORMAT_WIDEVINE_PSSH_BINARY = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed";
    private static final String KEYFORMAT_WIDEVINE_PSSH_JSON = "com.widevine";
    private static final String LOG_TAG = "HlsPlaylistParser";
    private static final String METHOD_AES_128 = "AES-128";
    private static final String METHOD_NONE = "NONE";
    private static final String METHOD_SAMPLE_AES = "SAMPLE-AES";
    private static final String METHOD_SAMPLE_AES_CENC = "SAMPLE-AES-CENC";
    private static final String METHOD_SAMPLE_AES_CTR = "SAMPLE-AES-CTR";
    private static final String PLAYLIST_HEADER = "#EXTM3U";
    private static final Regex REGEX_ASSET_LIST_URI;
    private static final Regex REGEX_ASSET_URI;
    private static final Regex REGEX_ATTR_BYTERANGE;
    private static final Regex REGEX_ATTR_DURATION;
    private static final Regex REGEX_ATTR_DURATION_PREFIXED;
    private static final Regex REGEX_AUDIO;
    private static final Regex REGEX_AUTOSELECT;
    private static final Regex REGEX_AVERAGE_BANDWIDTH;
    private static final Regex REGEX_BANDWIDTH;
    private static final Regex REGEX_BYTERANGE;
    private static final Regex REGEX_BYTERANGE_LENGTH;
    private static final Regex REGEX_BYTERANGE_START;
    private static final Regex REGEX_CAN_BLOCK_RELOAD;
    private static final Regex REGEX_CAN_SKIP_DATE_RANGES;
    private static final Regex REGEX_CAN_SKIP_UNTIL;
    private static final Regex REGEX_CHANNELS;
    private static final Regex REGEX_CHARACTERISTICS;
    private static final Regex REGEX_CLASS;
    private static final Regex REGEX_CLIENT_DEFINED_ATTRIBUTE_PREFIX;
    private static final Regex REGEX_CLOSED_CAPTIONS;
    private static final Regex REGEX_CODECS;
    private static final Regex REGEX_CUE;
    private static final Regex REGEX_DEFAULT;
    private static final Regex REGEX_END_DATE;
    private static final Regex REGEX_END_ON_NEXT;
    private static final Regex REGEX_FORCED;
    private static final Regex REGEX_FRAME_RATE;
    private static final Regex REGEX_GAP;
    private static final Regex REGEX_GROUP_ID;
    private static final Regex REGEX_HOLD_BACK;
    private static final Regex REGEX_ID;
    private static final Regex REGEX_IMPORT;
    private static final Regex REGEX_INDEPENDENT;
    private static final Regex REGEX_INSTREAM_ID;
    private static final Regex REGEX_IV;
    private static final Regex REGEX_KEYFORMAT;
    private static final Regex REGEX_KEYFORMATVERSIONS;
    private static final Regex REGEX_LANGUAGE;
    private static final Regex REGEX_LAST_MSN;
    private static final Regex REGEX_LAST_PART;
    private static final Regex REGEX_MEDIA_DURATION;
    private static final Regex REGEX_MEDIA_SEQUENCE;
    private static final Regex REGEX_MEDIA_TITLE;
    private static final Regex REGEX_METHOD;
    private static final Regex REGEX_NAME;
    private static final Regex REGEX_PART_HOLD_BACK;
    private static final Regex REGEX_PART_TARGET_DURATION;
    private static final Regex REGEX_PLANNED_DURATION;
    private static final Regex REGEX_PLAYLIST_TYPE;
    private static final Regex REGEX_PLAYOUT_LIMIT;
    private static final Regex REGEX_PRECISE;
    private static final Regex REGEX_PRELOAD_HINT_TYPE;
    private static final Regex REGEX_RESOLUTION;
    private static final Regex REGEX_RESTRICT;
    private static final Regex REGEX_RESUME_OFFSET;
    private static final Regex REGEX_SKIPPED_SEGMENTS;
    private static final Regex REGEX_SNAP;
    private static final Regex REGEX_START_DATE;
    private static final Regex REGEX_SUBTITLES;
    private static final Regex REGEX_SUPPLEMENTAL_CODECS;
    private static final Regex REGEX_TARGET_DURATION;
    private static final Regex REGEX_TIME_OFFSET;
    private static final Regex REGEX_TYPE;
    private static final Regex REGEX_URI;
    private static final Regex REGEX_VALUE;
    private static final Regex REGEX_VARIABLE_REFERENCE;
    private static final Regex REGEX_VERSION;
    private static final Regex REGEX_VIDEO;
    private static final Regex REGEX_VIDEO_RANGE;
    private static final String TAG_BYTERANGE = "#EXT-X-BYTERANGE";
    private static final String TAG_DATERANGE = "#EXT-X-DATERANGE";
    private static final String TAG_DEFINE = "#EXT-X-DEFINE";
    private static final String TAG_DISCONTINUITY = "#EXT-X-DISCONTINUITY";
    private static final String TAG_DISCONTINUITY_SEQUENCE = "#EXT-X-DISCONTINUITY-SEQUENCE";
    private static final String TAG_ENDLIST = "#EXT-X-ENDLIST";
    private static final String TAG_GAP = "#EXT-X-GAP";
    private static final String TAG_IFRAME = "#EXT-X-I-FRAMES-ONLY";
    private static final String TAG_INDEPENDENT_SEGMENTS = "#EXT-X-INDEPENDENT-SEGMENTS";
    private static final String TAG_INIT_SEGMENT = "#EXT-X-MAP";
    private static final String TAG_I_FRAME_STREAM_INF = "#EXT-X-I-FRAME-STREAM-INF";
    private static final String TAG_KEY = "#EXT-X-KEY";
    private static final String TAG_MEDIA = "#EXT-X-MEDIA";
    private static final String TAG_MEDIA_DURATION = "#EXTINF";
    private static final String TAG_MEDIA_SEQUENCE = "#EXT-X-MEDIA-SEQUENCE";
    private static final String TAG_PART = "#EXT-X-PART";
    private static final String TAG_PART_INF = "#EXT-X-PART-INF";
    private static final String TAG_PLAYLIST_TYPE = "#EXT-X-PLAYLIST-TYPE";
    private static final String TAG_PREFIX = "#EXT";
    private static final String TAG_PRELOAD_HINT = "#EXT-X-PRELOAD-HINT";
    private static final String TAG_PROGRAM_DATE_TIME = "#EXT-X-PROGRAM-DATE-TIME";
    private static final String TAG_RENDITION_REPORT = "#EXT-X-RENDITION-REPORT";
    private static final String TAG_SERVER_CONTROL = "#EXT-X-SERVER-CONTROL";
    private static final String TAG_SESSION_KEY = "#EXT-X-SESSION-KEY";
    private static final String TAG_SKIP = "#EXT-X-SKIP";
    private static final String TAG_START = "#EXT-X-START";
    private static final String TAG_STREAM_INF = "#EXT-X-STREAM-INF";
    private static final String TAG_TARGET_DURATION = "#EXT-X-TARGETDURATION";
    private static final String TAG_VERSION = "#EXT-X-VERSION";
    private static final String TYPE_AUDIO = "AUDIO";
    private static final String TYPE_CLOSED_CAPTIONS = "CLOSED-CAPTIONS";
    private static final String TYPE_MAP = "MAP";
    private static final String TYPE_PART = "PART";
    private static final String TYPE_SUBTITLES = "SUBTITLES";
    private static final String TYPE_VIDEO = "VIDEO";

    private HlsPlaylistParser() {
    }

    static {
        HlsPlaylistParser hlsPlaylistParser = new HlsPlaylistParser();
        INSTANCE = hlsPlaylistParser;
        REGEX_AVERAGE_BANDWIDTH = new Regex("AVERAGE-BANDWIDTH=(\\d+)\\b");
        REGEX_VIDEO = new Regex("VIDEO=\"(.+?)\"");
        REGEX_AUDIO = new Regex("AUDIO=\"(.+?)\"");
        REGEX_SUBTITLES = new Regex("SUBTITLES=\"(.+?)\"");
        REGEX_CLOSED_CAPTIONS = new Regex("CLOSED-CAPTIONS=\"(.+?)\"");
        REGEX_BANDWIDTH = new Regex("[^-]BANDWIDTH=(\\d+)\\b");
        REGEX_CHANNELS = new Regex("CHANNELS=\"(.+?)\"");
        REGEX_VIDEO_RANGE = new Regex("VIDEO-RANGE=(SDR|PQ|HLG)");
        REGEX_CODECS = new Regex("CODECS=\"(.+?)\"");
        REGEX_SUPPLEMENTAL_CODECS = new Regex("SUPPLEMENTAL-CODECS=\"(.+?)\"");
        REGEX_RESOLUTION = new Regex("RESOLUTION=(\\d+x\\d+)");
        REGEX_FRAME_RATE = new Regex("FRAME-RATE=([\\d\\.]+)\\b");
        REGEX_TARGET_DURATION = new Regex("#EXT-X-TARGETDURATION:(\\d+)\\b");
        REGEX_ATTR_DURATION = new Regex("DURATION=([\\d\\.]+)\\b");
        REGEX_ATTR_DURATION_PREFIXED = new Regex("[:,]DURATION=([\\d\\.]+)\\b");
        REGEX_PART_TARGET_DURATION = new Regex("PART-TARGET=([\\d\\.]+)\\b");
        REGEX_VERSION = new Regex("#EXT-X-VERSION:(\\d+)\\b");
        REGEX_PLAYLIST_TYPE = new Regex("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
        REGEX_CAN_SKIP_UNTIL = new Regex("CAN-SKIP-UNTIL=([\\d\\.]+)\\b");
        REGEX_CAN_SKIP_DATE_RANGES = hlsPlaylistParser.compileBooleanAttrPattern("CAN-SKIP-DATERANGES");
        REGEX_SKIPPED_SEGMENTS = new Regex("SKIPPED-SEGMENTS=(\\d+)\\b");
        REGEX_HOLD_BACK = new Regex("[:|,]HOLD-BACK=([\\d\\.]+)\\b");
        REGEX_PART_HOLD_BACK = new Regex("PART-HOLD-BACK=([\\d\\.]+)\\b");
        REGEX_CAN_BLOCK_RELOAD = hlsPlaylistParser.compileBooleanAttrPattern("CAN-BLOCK-RELOAD");
        REGEX_MEDIA_SEQUENCE = new Regex("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
        REGEX_MEDIA_DURATION = new Regex("#EXTINF:([\\d\\.]+)\\b");
        REGEX_MEDIA_TITLE = new Regex("#EXTINF:[\\d\\.]+\\b,(.+)");
        REGEX_LAST_MSN = new Regex("LAST-MSN=(\\d+)\\b");
        REGEX_LAST_PART = new Regex("LAST-PART=(\\d+)\\b");
        REGEX_TIME_OFFSET = new Regex("TIME-OFFSET=(-?[\\d\\.]+)\\b");
        REGEX_BYTERANGE = new Regex("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
        REGEX_ATTR_BYTERANGE = new Regex("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
        REGEX_BYTERANGE_START = new Regex("BYTERANGE-START=(\\d+)\\b");
        REGEX_BYTERANGE_LENGTH = new Regex("BYTERANGE-LENGTH=(\\d+)\\b");
        REGEX_METHOD = new Regex("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(?:,|$)");
        REGEX_KEYFORMAT = new Regex("KEYFORMAT=\"(.+?)\"");
        REGEX_KEYFORMATVERSIONS = new Regex("KEYFORMATVERSIONS=\"(.+?)\"");
        REGEX_URI = new Regex("URI=\"(.+?)\"");
        REGEX_IV = new Regex("IV=([^,.*]+)");
        REGEX_TYPE = new Regex("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
        REGEX_PRELOAD_HINT_TYPE = new Regex("TYPE=(PART|MAP)");
        REGEX_LANGUAGE = new Regex("LANGUAGE=\"(.+?)\"");
        REGEX_NAME = new Regex("NAME=\"(.+?)\"");
        REGEX_GROUP_ID = new Regex("GROUP-ID=\"(.+?)\"");
        REGEX_CHARACTERISTICS = new Regex("CHARACTERISTICS=\"(.+?)\"");
        REGEX_INSTREAM_ID = new Regex("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
        REGEX_AUTOSELECT = hlsPlaylistParser.compileBooleanAttrPattern("AUTOSELECT");
        REGEX_DEFAULT = hlsPlaylistParser.compileBooleanAttrPattern("DEFAULT");
        REGEX_FORCED = hlsPlaylistParser.compileBooleanAttrPattern("FORCED");
        REGEX_INDEPENDENT = hlsPlaylistParser.compileBooleanAttrPattern("INDEPENDENT");
        REGEX_GAP = hlsPlaylistParser.compileBooleanAttrPattern("GAP");
        REGEX_PRECISE = hlsPlaylistParser.compileBooleanAttrPattern("PRECISE");
        REGEX_VALUE = new Regex("VALUE=\"(.+?)\"");
        REGEX_IMPORT = new Regex("IMPORT=\"(.+?)\"");
        REGEX_ID = new Regex("[:,]ID=\"(.+?)\"");
        REGEX_CLASS = new Regex("CLASS=\"(.+?)\"");
        REGEX_START_DATE = new Regex("START-DATE=\"(.+?)\"");
        REGEX_CUE = new Regex("CUE=\"(.+?)\"");
        REGEX_END_DATE = new Regex("END-DATE=\"(.+?)\"");
        REGEX_PLANNED_DURATION = new Regex("PLANNED-DURATION=([\\d.]+)\\b");
        REGEX_END_ON_NEXT = hlsPlaylistParser.compileBooleanAttrPattern("END-ON-NEXT");
        REGEX_ASSET_URI = new Regex("X-ASSET-URI=\"(.+?)\"");
        REGEX_ASSET_LIST_URI = new Regex("X-ASSET-LIST=\"(.+?)\"");
        REGEX_RESUME_OFFSET = new Regex("X-RESUME-OFFSET=(-?[\\d.]+)\\b");
        REGEX_PLAYOUT_LIMIT = new Regex("X-PLAYOUT-LIMIT=([\\d.]+)\\b");
        REGEX_SNAP = new Regex("X-SNAP=\"(.+?)\"");
        REGEX_RESTRICT = new Regex("X-RESTRICT=\"(.+?)\"");
        REGEX_VARIABLE_REFERENCE = new Regex("\\{\\$([a-zA-Z0-9\\-_]+)\\}");
        REGEX_CLIENT_DEFINED_ATTRIBUTE_PREFIX = new Regex("\\b(X-[A-Z0-9-]+)=");
    }

    private final Regex compileBooleanAttrPattern(String attribute) {
        return new Regex(attribute + "=(NO|YES)");
    }

    private final int parseIntAttr(String line, Regex pattern) throws ParserException {
        return Integer.parseInt(parseStringAttr(line, pattern, MapsKt.emptyMap()));
    }

    private final int parseOptionalIntAttr(String line, Regex pattern, int defaultValue) {
        List<String> groupValues;
        String str;
        MatchResult matchResultFind$default = Regex.find$default(pattern, line, 0, 2, null);
        return (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = groupValues.get(1)) == null) ? defaultValue : Integer.parseInt(str);
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J3\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$SchemeData;", "", "uuid", "Ljava/util/UUID;", "licenseServerUrl", "", "mimeType", "data", "", "<init>", "(Ljava/util/UUID;Ljava/lang/String;Ljava/lang/String;[B)V", "getUuid", "()Ljava/util/UUID;", "getLicenseServerUrl", "()Ljava/lang/String;", "getMimeType", "getData", "()[B", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SchemeData {
        private final byte[] data;
        private final String licenseServerUrl;
        private final String mimeType;
        private final UUID uuid;

        public static /* synthetic */ SchemeData copy$default(SchemeData schemeData, UUID uuid, String str, String str2, byte[] bArr, int i, Object obj) {
            if ((i & 1) != 0) {
                uuid = schemeData.uuid;
            }
            if ((i & 2) != 0) {
                str = schemeData.licenseServerUrl;
            }
            if ((i & 4) != 0) {
                str2 = schemeData.mimeType;
            }
            if ((i & 8) != 0) {
                bArr = schemeData.data;
            }
            return schemeData.copy(uuid, str, str2, bArr);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UUID getUuid() {
            return this.uuid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLicenseServerUrl() {
            return this.licenseServerUrl;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMimeType() {
            return this.mimeType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final byte[] getData() {
            return this.data;
        }

        public final SchemeData copy(UUID uuid, String licenseServerUrl, String mimeType, byte[] data) {
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            Intrinsics.checkNotNullParameter(data, "data");
            return new SchemeData(uuid, licenseServerUrl, mimeType, data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SchemeData)) {
                return false;
            }
            SchemeData schemeData = (SchemeData) other;
            return Intrinsics.areEqual(this.uuid, schemeData.uuid) && Intrinsics.areEqual(this.licenseServerUrl, schemeData.licenseServerUrl) && Intrinsics.areEqual(this.mimeType, schemeData.mimeType) && Intrinsics.areEqual(this.data, schemeData.data);
        }

        public int hashCode() {
            int iHashCode = this.uuid.hashCode() * 31;
            String str = this.licenseServerUrl;
            return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.mimeType.hashCode()) * 31) + Arrays.hashCode(this.data);
        }

        public String toString() {
            return "SchemeData(uuid=" + this.uuid + ", licenseServerUrl=" + this.licenseServerUrl + ", mimeType=" + this.mimeType + ", data=" + Arrays.toString(this.data) + ')';
        }

        public SchemeData(UUID uuid, String str, String mimeType, byte[] data) {
            Intrinsics.checkNotNullParameter(uuid, "uuid");
            Intrinsics.checkNotNullParameter(mimeType, "mimeType");
            Intrinsics.checkNotNullParameter(data, "data");
            this.uuid = uuid;
            this.licenseServerUrl = str;
            this.mimeType = mimeType;
            this.data = data;
        }

        public /* synthetic */ SchemeData(UUID uuid, String str, String str2, byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(uuid, (i & 2) != 0 ? null : str, str2, bArr);
        }

        public final UUID getUuid() {
            return this.uuid;
        }

        public final String getLicenseServerUrl() {
            return this.licenseServerUrl;
        }

        public final String getMimeType() {
            return this.mimeType;
        }

        public final byte[] getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\rJ\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Util;", "", "<init>", "()V", "splitAtFirst", "", "", ES6Iterator.VALUE_PROPERTY, "regex", "(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;", "split", "splitCodecs", "codecs", "(Ljava/lang/String;)[Ljava/lang/String;", "getCodecsOfType", "trackType", "", "getCodecsWithoutType", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Util {
        public static final Util INSTANCE = new Util();

        private Util() {
        }

        public final String[] splitAtFirst(String value, String regex) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(regex, "regex");
            return (String[]) new Regex(regex).split(value, 2).toArray(new String[0]);
        }

        public final String[] split(String value, String regex) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(regex, "regex");
            return (String[]) new Regex(regex).split(value, 0).toArray(new String[0]);
        }

        public final String[] splitCodecs(String codecs) {
            String str = codecs;
            if (str == null || str.length() == 0) {
                return new String[0];
            }
            return split(StringsKt.trim((CharSequence) str).toString(), "(\\s*,\\s*)");
        }

        public final String getCodecsOfType(String codecs, int trackType) {
            String[] strArrSplitCodecs = splitCodecs(codecs);
            if (strArrSplitCodecs.length == 0) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (String str : strArrSplitCodecs) {
                if (trackType == MimeTypes.INSTANCE.getTrackTypeOfCodec(str)) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(str);
                }
            }
            if (sb.length() > 0) {
                return sb.toString();
            }
            return null;
        }

        public final String getCodecsWithoutType(String codecs, int trackType) {
            String[] strArrSplitCodecs = splitCodecs(codecs);
            if (strArrSplitCodecs.length == 0) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (String str : strArrSplitCodecs) {
                if (trackType != MimeTypes.INSTANCE.getTrackTypeOfCodec(str)) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(str);
                }
            }
            if (sb.length() > 0) {
                return sb.toString();
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007J\u001c\u0010\u000f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0002J$\u0010\u0010\u001a\u00020\u00072\n\u0010\u0011\u001a\u00060\u0012j\u0002`\u00132\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\nH\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0007H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$UrlUtil;", "", "<init>", "()V", "resolveToUrl", "Lio/ktor/http/Url;", "baseUrl", "", "referenceUrl", "INDEX_COUNT", "", "SCHEME_COLON", "PATH", "QUERY", "FRAGMENT", "resolve", "removeDotSegments", ImagesContract.URL, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", TypedValues.CycleType.S_WAVE_OFFSET, "limit", "getUrlIndices", "", "urlString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class UrlUtil {
        private static final int FRAGMENT = 3;
        private static final int INDEX_COUNT = 4;
        public static final UrlUtil INSTANCE = new UrlUtil();
        private static final int PATH = 1;
        private static final int QUERY = 2;
        private static final int SCHEME_COLON = 0;

        private UrlUtil() {
        }

        public final Url resolveToUrl(String baseUrl, String referenceUrl) {
            return URLUtilsKt.Url(resolve(baseUrl, referenceUrl));
        }

        private final String resolve(String baseUrl, String referenceUrl) {
            StringBuilder sb = new StringBuilder();
            String str = baseUrl == null ? "" : baseUrl;
            String str2 = referenceUrl != null ? referenceUrl : "";
            int[] urlIndices = getUrlIndices(str2);
            if (urlIndices[0] != -1) {
                sb.append(str2);
                removeDotSegments(sb, urlIndices[1], urlIndices[2]);
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                return string;
            }
            int[] urlIndices2 = getUrlIndices(str);
            if (urlIndices[3] == 0) {
                sb.append((CharSequence) str, 0, urlIndices2[3]);
                sb.append(str2);
                String string2 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
                return string2;
            }
            if (urlIndices[2] == 0) {
                sb.append((CharSequence) str, 0, urlIndices2[2]);
                sb.append(str2);
                String string3 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                return string3;
            }
            int i = urlIndices[1];
            if (i != 0) {
                int i2 = urlIndices2[0] + 1;
                sb.append((CharSequence) str, 0, i2);
                sb.append(str2);
                return removeDotSegments(sb, urlIndices[1] + i2, i2 + urlIndices[2]);
            }
            if (str2.charAt(i) == '/') {
                sb.append((CharSequence) str, 0, urlIndices2[1]);
                sb.append(str2);
                int i3 = urlIndices2[1];
                return removeDotSegments(sb, i3, urlIndices[2] + i3);
            }
            int i4 = urlIndices2[0] + 2;
            int i5 = urlIndices2[1];
            if (i4 < i5 && i5 == urlIndices2[2]) {
                sb.append((CharSequence) str, 0, i5);
                sb.append('/');
                sb.append(str2);
                int i6 = urlIndices2[1];
                return removeDotSegments(sb, i6, urlIndices[2] + i6 + 1);
            }
            String str3 = str;
            int iLastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) str3, '/', urlIndices2[2] - 1, false, 4, (Object) null);
            int i7 = iLastIndexOf$default == -1 ? urlIndices2[1] : iLastIndexOf$default + 1;
            sb.append((CharSequence) str3, 0, i7);
            sb.append(str2);
            return removeDotSegments(sb, urlIndices2[1], i7 + urlIndices[2]);
        }

        private final String removeDotSegments(StringBuilder url, int offset, int limit) {
            int i;
            int i2;
            if (offset >= limit) {
                String string = url.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                return string;
            }
            if (url.charAt(offset) == '/') {
                offset++;
            }
            int i3 = offset;
            int iLastIndexOf = i3;
            while (i3 <= limit) {
                if (i3 == limit) {
                    i = i3;
                } else if (url.charAt(i3) == '/') {
                    i = i3 + 1;
                } else {
                    i3++;
                }
                int i4 = iLastIndexOf + 1;
                if (i3 == i4 && url.charAt(iLastIndexOf) == '.') {
                    url.delete(iLastIndexOf, i);
                    i2 = i - iLastIndexOf;
                } else {
                    if (i3 == iLastIndexOf + 2 && url.charAt(iLastIndexOf) == '.' && url.charAt(i4) == '.') {
                        iLastIndexOf = url.lastIndexOf("/", iLastIndexOf - 2) + 1;
                        int i5 = iLastIndexOf > offset ? iLastIndexOf : offset;
                        url.delete(i5, i);
                        i2 = i - i5;
                    } else {
                        iLastIndexOf = i3 + 1;
                    }
                    i3 = iLastIndexOf;
                }
                limit -= i2;
                i3 = iLastIndexOf;
            }
            String string2 = url.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            return string2;
        }

        private final int[] getUrlIndices(String urlString) {
            int iIndexOf$default;
            int[] iArr = new int[4];
            String str = urlString;
            if (str == null || str.length() == 0) {
                iArr[0] = -1;
                return iArr;
            }
            int length = urlString.length();
            int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str, '#', 0, false, 6, (Object) null);
            if (iIndexOf$default2 != -1) {
                length = iIndexOf$default2;
            }
            int iIndexOf$default3 = StringsKt.indexOf$default((CharSequence) str, '?', 0, false, 6, (Object) null);
            int i = (iIndexOf$default3 == -1 || iIndexOf$default3 > length) ? length : iIndexOf$default3;
            int iIndexOf$default4 = StringsKt.indexOf$default((CharSequence) str, '/', 0, false, 6, (Object) null);
            int i2 = (iIndexOf$default4 == -1 || iIndexOf$default4 > i) ? i : iIndexOf$default4;
            int iIndexOf$default5 = StringsKt.indexOf$default((CharSequence) str, AbstractJsonLexerKt.COLON, 0, false, 6, (Object) null);
            int i3 = iIndexOf$default5 > i2 ? -1 : iIndexOf$default5;
            int i4 = i3 + 2;
            if (i4 < i && urlString.charAt(i3 + 1) == '/' && urlString.charAt(i4) == '/') {
                iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, '/', i3 + 3, false, 4, (Object) null);
                if (iIndexOf$default == -1 || iIndexOf$default > i) {
                    iIndexOf$default = i;
                }
            } else {
                iIndexOf$default = i3 + 1;
            }
            iArr[0] = i3;
            iArr[1] = iIndexOf$default;
            iArr[2] = i;
            iArr[3] = length;
            return iArr;
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u000e\u0010\f\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0012X\u0086T¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$C;", "", "<init>", "()V", "CLEARKEY_UUID", "Ljava/util/UUID;", "getCLEARKEY_UUID", "()Ljava/util/UUID;", "WIDEVINE_UUID", "getWIDEVINE_UUID", "PLAYREADY_UUID", "getPLAYREADY_UUID", "CENC_TYPE_cenc", "", "CENC_TYPE_cbc1", "CENC_TYPE_cens", "CENC_TYPE_cbcs", "ROLE_FLAG_MAIN", "", "ROLE_FLAG_ALTERNATE", "ROLE_FLAG_SUPPLEMENTARY", "ROLE_FLAG_COMMENTARY", "ROLE_FLAG_DUB", "ROLE_FLAG_EMERGENCY", "ROLE_FLAG_CAPTION", "ROLE_FLAG_SUBTITLE", "ROLE_FLAG_SIGN", "ROLE_FLAG_DESCRIBES_VIDEO", "ROLE_FLAG_DESCRIBES_MUSIC_AND_SOUND", "ROLE_FLAG_ENHANCED_DIALOG_INTELLIGIBILITY", "ROLE_FLAG_TRANSCRIBES_DIALOG", "ROLE_FLAG_EASY_TO_READ", "ROLE_FLAG_TRICK_PLAY", "TRACK_TYPE_NONE", "TRACK_TYPE_UNKNOWN", "TRACK_TYPE_DEFAULT", "TRACK_TYPE_AUDIO", "TRACK_TYPE_VIDEO", "TRACK_TYPE_TEXT", "TRACK_TYPE_IMAGE", "TRACK_TYPE_METADATA", "TRACK_TYPE_CAMERA_MOTION", "SELECTION_FLAG_DEFAULT", "SELECTION_FLAG_FORCED", "SELECTION_FLAG_AUTOSELECT", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class C {
        public static final String CENC_TYPE_cbc1 = "cbc1";
        public static final String CENC_TYPE_cbcs = "cbcs";
        public static final String CENC_TYPE_cenc = "cenc";
        public static final String CENC_TYPE_cens = "cens";
        public static final int ROLE_FLAG_ALTERNATE = 2;
        public static final int ROLE_FLAG_CAPTION = 64;
        public static final int ROLE_FLAG_COMMENTARY = 8;
        public static final int ROLE_FLAG_DESCRIBES_MUSIC_AND_SOUND = 1024;
        public static final int ROLE_FLAG_DESCRIBES_VIDEO = 512;
        public static final int ROLE_FLAG_DUB = 16;
        public static final int ROLE_FLAG_EASY_TO_READ = 8192;
        public static final int ROLE_FLAG_EMERGENCY = 32;
        public static final int ROLE_FLAG_ENHANCED_DIALOG_INTELLIGIBILITY = 2048;
        public static final int ROLE_FLAG_MAIN = 1;
        public static final int ROLE_FLAG_SIGN = 256;
        public static final int ROLE_FLAG_SUBTITLE = 128;
        public static final int ROLE_FLAG_SUPPLEMENTARY = 4;
        public static final int ROLE_FLAG_TRANSCRIBES_DIALOG = 4096;
        public static final int ROLE_FLAG_TRICK_PLAY = 16384;
        public static final int SELECTION_FLAG_AUTOSELECT = 4;
        public static final int SELECTION_FLAG_DEFAULT = 1;
        public static final int SELECTION_FLAG_FORCED = 2;
        public static final int TRACK_TYPE_AUDIO = 1;
        public static final int TRACK_TYPE_CAMERA_MOTION = 6;
        public static final int TRACK_TYPE_DEFAULT = 0;
        public static final int TRACK_TYPE_IMAGE = 4;
        public static final int TRACK_TYPE_METADATA = 5;
        public static final int TRACK_TYPE_NONE = -2;
        public static final int TRACK_TYPE_TEXT = 3;
        public static final int TRACK_TYPE_UNKNOWN = -1;
        public static final int TRACK_TYPE_VIDEO = 2;
        public static final C INSTANCE = new C();
        private static final UUID CLEARKEY_UUID = new UUID(-2129748144642739255L, 8654423357094679310L);
        private static final UUID WIDEVINE_UUID = new UUID(-1301668207276963122L, -6645017420763422227L);
        private static final UUID PLAYREADY_UUID = new UUID(-7348484286925749626L, -6083546864340672619L);

        private C() {
        }

        public final UUID getCLEARKEY_UUID() {
            return CLEARKEY_UUID;
        }

        public final UUID getWIDEVINE_UUID() {
            return WIDEVINE_UUID;
        }

        public final UUID getPLAYREADY_UUID() {
            return PLAYREADY_UUID;
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\bl\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0087\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010s\u001a\u00020t2\b\u0010u\u001a\u0004\u0018\u00010\u00052\b\u0010v\u001a\u0004\u0018\u00010\u0005J\u0012\u0010w\u001a\u0004\u0018\u00010x2\b\u0010y\u001a\u0004\u0018\u00010\u0005J\u0010\u0010z\u001a\u0004\u0018\u00010\u00052\u0006\u0010{\u001a\u00020|J\u0012\u0010}\u001a\u0004\u0018\u00010\u00052\b\u0010~\u001a\u0004\u0018\u00010\u0005J\u0015\u0010\u007f\u001a\u0004\u0018\u00010\u00052\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0005H\u0002J\u0012\u0010\u0081\u0001\u001a\u00020t2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0005J\u0012\u0010\u0082\u0001\u001a\u00020t2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0005J\u0012\u0010\u0083\u0001\u001a\u00020t2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0005J\u0012\u0010\u0084\u0001\u001a\u00020t2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0005J\u0012\u0010\u0085\u0001\u001a\u00020|2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0005J\u0011\u0010\u0086\u0001\u001a\u00020|2\b\u0010y\u001a\u0004\u0018\u00010\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0088\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$MimeTypes;", "", "<init>", "()V", "BASE_TYPE_VIDEO", "", "BASE_TYPE_AUDIO", "BASE_TYPE_TEXT", "BASE_TYPE_IMAGE", "BASE_TYPE_APPLICATION", "VIDEO_MP4", "VIDEO_MATROSKA", "VIDEO_WEBM", "VIDEO_H263", "VIDEO_H264", "VIDEO_APV", "VIDEO_H265", "VIDEO_VP8", "VIDEO_VP9", "VIDEO_AV1", "VIDEO_MP2T", "VIDEO_MP4V", "VIDEO_MPEG", "VIDEO_PS", "VIDEO_MPEG2", "VIDEO_VC1", "VIDEO_DIVX", "VIDEO_FLV", "VIDEO_DOLBY_VISION", "VIDEO_OGG", "VIDEO_AVI", "VIDEO_MJPEG", "VIDEO_MP42", "VIDEO_MP43", "VIDEO_MV_HEVC", "VIDEO_RAW", "VIDEO_UNKNOWN", "AUDIO_MP4", "AUDIO_AAC", "AUDIO_MATROSKA", "AUDIO_WEBM", "AUDIO_MPEG", "AUDIO_MPEG_L1", "AUDIO_MPEG_L2", "AUDIO_MPEGH_MHA1", "AUDIO_MPEGH_MHM1", "AUDIO_RAW", "AUDIO_ALAW", "AUDIO_MLAW", "AUDIO_AC3", "AUDIO_E_AC3", "AUDIO_E_AC3_JOC", "AUDIO_AC4", "AUDIO_TRUEHD", "AUDIO_DTS", "AUDIO_DTS_HD", "AUDIO_DTS_EXPRESS", "AUDIO_DTS_X", "AUDIO_VORBIS", "AUDIO_OPUS", "AUDIO_AMR", "AUDIO_AMR_NB", "AUDIO_AMR_WB", "AUDIO_FLAC", "AUDIO_ALAC", "AUDIO_MSGSM", "AUDIO_OGG", "AUDIO_WAV", "AUDIO_MIDI", "AUDIO_IAMF", "AUDIO_EXOPLAYER_MIDI", "AUDIO_UNKNOWN", "TEXT_VTT", "TEXT_SSA", "TEXT_UNKNOWN", "APPLICATION_MP4", "APPLICATION_WEBM", "APPLICATION_MATROSKA", "APPLICATION_MPD", "APPLICATION_M3U8", "APPLICATION_SS", "APPLICATION_ID3", "APPLICATION_CEA608", "APPLICATION_CEA708", "APPLICATION_SUBRIP", "APPLICATION_TTML", "APPLICATION_TX3G", "APPLICATION_MP4VTT", "APPLICATION_MP4CEA608", "APPLICATION_VOBSUB", "APPLICATION_PGS", "APPLICATION_SCTE35", "APPLICATION_SDP", "APPLICATION_CAMERA_MOTION", "APPLICATION_DEPTH_METADATA", "APPLICATION_EMSG", "APPLICATION_DVBSUBS", "APPLICATION_EXIF", "APPLICATION_ICY", "APPLICATION_AIT", "APPLICATION_RTSP", "APPLICATION_MEDIA3_CUES", "APPLICATION_EXTERNALLY_LOADED_IMAGE", "IMAGE_JPEG", "IMAGE_JPEG_R", "IMAGE_PNG", "IMAGE_HEIF", "IMAGE_HEIC", "IMAGE_AVIF", "IMAGE_BMP", "IMAGE_WEBP", "IMAGE_RAW", "CODEC_E_AC3_JOC", "MP4A_RFC_6381_CODEC_PATTERN", "Lkotlin/text/Regex;", "isDolbyVisionCodec", "", "codecs", "supplementalCodecs", "getObjectTypeFromMp4aRFC6381CodecString", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$MimeTypes$Mp4aObjectType;", "codec", "getMimeTypeFromMp4ObjectType", "objectType", "", "getMediaMimeType", "codecOrNull", "getTopLevelType", "mimeType", "isAudio", "isVideo", "isText", "isImage", "getTrackType", "getTrackTypeOfCodec", "Mp4aObjectType", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class MimeTypes {
        public static final String APPLICATION_AIT = "application/vnd.dvb.ait";
        public static final String APPLICATION_CAMERA_MOTION = "application/x-camera-motion";
        public static final String APPLICATION_CEA608 = "application/cea-608";
        public static final String APPLICATION_CEA708 = "application/cea-708";
        public static final String APPLICATION_DEPTH_METADATA = "application/x-depth-metadata";
        public static final String APPLICATION_DVBSUBS = "application/dvbsubs";
        public static final String APPLICATION_EMSG = "application/x-emsg";
        public static final String APPLICATION_EXIF = "application/x-exif";
        public static final String APPLICATION_EXTERNALLY_LOADED_IMAGE = "application/x-image-uri";
        public static final String APPLICATION_ICY = "application/x-icy";
        public static final String APPLICATION_ID3 = "application/id3";
        public static final String APPLICATION_M3U8 = "application/x-mpegURL";
        public static final String APPLICATION_MATROSKA = "application/x-matroska";
        public static final String APPLICATION_MEDIA3_CUES = "application/x-media3-cues";
        public static final String APPLICATION_MP4 = "application/mp4";
        public static final String APPLICATION_MP4CEA608 = "application/x-mp4-cea-608";
        public static final String APPLICATION_MP4VTT = "application/x-mp4-vtt";
        public static final String APPLICATION_MPD = "application/dash+xml";
        public static final String APPLICATION_PGS = "application/pgs";
        public static final String APPLICATION_RTSP = "application/x-rtsp";
        public static final String APPLICATION_SCTE35 = "application/x-scte35";
        public static final String APPLICATION_SDP = "application/sdp";
        public static final String APPLICATION_SS = "application/vnd.ms-sstr+xml";
        public static final String APPLICATION_SUBRIP = "application/x-subrip";
        public static final String APPLICATION_TTML = "application/ttml+xml";
        public static final String APPLICATION_TX3G = "application/x-quicktime-tx3g";
        public static final String APPLICATION_VOBSUB = "application/vobsub";
        public static final String APPLICATION_WEBM = "application/webm";
        public static final String AUDIO_AAC = "audio/mp4a-latm";
        public static final String AUDIO_AC3 = "audio/ac3";
        public static final String AUDIO_AC4 = "audio/ac4";
        public static final String AUDIO_ALAC = "audio/alac";
        public static final String AUDIO_ALAW = "audio/g711-alaw";
        public static final String AUDIO_AMR = "audio/amr";
        public static final String AUDIO_AMR_NB = "audio/3gpp";
        public static final String AUDIO_AMR_WB = "audio/amr-wb";
        public static final String AUDIO_DTS = "audio/vnd.dts";
        public static final String AUDIO_DTS_EXPRESS = "audio/vnd.dts.hd;profile=lbr";
        public static final String AUDIO_DTS_HD = "audio/vnd.dts.hd";
        public static final String AUDIO_DTS_X = "audio/vnd.dts.uhd;profile=p2";
        public static final String AUDIO_EXOPLAYER_MIDI = "audio/x-exoplayer-midi";
        public static final String AUDIO_E_AC3 = "audio/eac3";
        public static final String AUDIO_E_AC3_JOC = "audio/eac3-joc";
        public static final String AUDIO_FLAC = "audio/flac";
        public static final String AUDIO_IAMF = "audio/iamf";
        public static final String AUDIO_MATROSKA = "audio/x-matroska";
        public static final String AUDIO_MIDI = "audio/midi";
        public static final String AUDIO_MLAW = "audio/g711-mlaw";
        public static final String AUDIO_MP4 = "audio/mp4";
        public static final String AUDIO_MPEG = "audio/mpeg";
        public static final String AUDIO_MPEGH_MHA1 = "audio/mha1";
        public static final String AUDIO_MPEGH_MHM1 = "audio/mhm1";
        public static final String AUDIO_MPEG_L1 = "audio/mpeg-L1";
        public static final String AUDIO_MPEG_L2 = "audio/mpeg-L2";
        public static final String AUDIO_MSGSM = "audio/gsm";
        public static final String AUDIO_OGG = "audio/ogg";
        public static final String AUDIO_OPUS = "audio/opus";
        public static final String AUDIO_RAW = "audio/raw";
        public static final String AUDIO_TRUEHD = "audio/true-hd";
        public static final String AUDIO_UNKNOWN = "audio/x-unknown";
        public static final String AUDIO_VORBIS = "audio/vorbis";
        public static final String AUDIO_WAV = "audio/wav";
        public static final String AUDIO_WEBM = "audio/webm";
        public static final String BASE_TYPE_APPLICATION = "application";
        public static final String BASE_TYPE_AUDIO = "audio";
        public static final String BASE_TYPE_IMAGE = "image";
        public static final String BASE_TYPE_TEXT = "text";
        public static final String BASE_TYPE_VIDEO = "video";
        public static final String CODEC_E_AC3_JOC = "ec+3";
        public static final String IMAGE_AVIF = "image/avif";
        public static final String IMAGE_BMP = "image/bmp";
        public static final String IMAGE_HEIC = "image/heic";
        public static final String IMAGE_HEIF = "image/heif";
        public static final String IMAGE_JPEG = "image/jpeg";
        public static final String IMAGE_JPEG_R = "image/jpeg_r";
        public static final String IMAGE_PNG = "image/png";
        public static final String IMAGE_RAW = "image/raw";
        public static final String IMAGE_WEBP = "image/webp";
        public static final MimeTypes INSTANCE = new MimeTypes();
        private static final Regex MP4A_RFC_6381_CODEC_PATTERN = new Regex("^mp4a\\.([a-zA-Z0-9]{2})(?:\\.([0-9]{1,2}))?$");
        public static final String TEXT_SSA = "text/x-ssa";
        public static final String TEXT_UNKNOWN = "text/x-unknown";
        public static final String TEXT_VTT = "text/vtt";
        public static final String VIDEO_APV = "video/apv";
        public static final String VIDEO_AV1 = "video/av01";
        public static final String VIDEO_AVI = "video/x-msvideo";
        public static final String VIDEO_DIVX = "video/divx";
        public static final String VIDEO_DOLBY_VISION = "video/dolby-vision";
        public static final String VIDEO_FLV = "video/x-flv";
        public static final String VIDEO_H263 = "video/3gpp";
        public static final String VIDEO_H264 = "video/avc";
        public static final String VIDEO_H265 = "video/hevc";
        public static final String VIDEO_MATROSKA = "video/x-matroska";
        public static final String VIDEO_MJPEG = "video/mjpeg";
        public static final String VIDEO_MP2T = "video/mp2t";
        public static final String VIDEO_MP4 = "video/mp4";
        public static final String VIDEO_MP42 = "video/mp42";
        public static final String VIDEO_MP43 = "video/mp43";
        public static final String VIDEO_MP4V = "video/mp4v-es";
        public static final String VIDEO_MPEG = "video/mpeg";
        public static final String VIDEO_MPEG2 = "video/mpeg2";
        public static final String VIDEO_MV_HEVC = "video/mv-hevc";
        public static final String VIDEO_OGG = "video/ogg";
        public static final String VIDEO_PS = "video/mp2p";
        public static final String VIDEO_RAW = "video/raw";
        public static final String VIDEO_UNKNOWN = "video/x-unknown";
        public static final String VIDEO_VC1 = "video/wvc1";
        public static final String VIDEO_VP8 = "video/x-vnd.on2.vp8";
        public static final String VIDEO_VP9 = "video/x-vnd.on2.vp9";
        public static final String VIDEO_WEBM = "video/webm";

        private MimeTypes() {
        }

        /* JADX INFO: compiled from: HlsPlaylistParser.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$MimeTypes$Mp4aObjectType;", "", "objectTypeIndication", "", "audioObjectTypeIndication", "<init>", "(II)V", "getObjectTypeIndication", "()I", "getAudioObjectTypeIndication", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class Mp4aObjectType {
            private final int audioObjectTypeIndication;
            private final int objectTypeIndication;

            public static /* synthetic */ Mp4aObjectType copy$default(Mp4aObjectType mp4aObjectType, int i, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i = mp4aObjectType.objectTypeIndication;
                }
                if ((i3 & 2) != 0) {
                    i2 = mp4aObjectType.audioObjectTypeIndication;
                }
                return mp4aObjectType.copy(i, i2);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final int getObjectTypeIndication() {
                return this.objectTypeIndication;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final int getAudioObjectTypeIndication() {
                return this.audioObjectTypeIndication;
            }

            public final Mp4aObjectType copy(int objectTypeIndication, int audioObjectTypeIndication) {
                return new Mp4aObjectType(objectTypeIndication, audioObjectTypeIndication);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Mp4aObjectType)) {
                    return false;
                }
                Mp4aObjectType mp4aObjectType = (Mp4aObjectType) other;
                return this.objectTypeIndication == mp4aObjectType.objectTypeIndication && this.audioObjectTypeIndication == mp4aObjectType.audioObjectTypeIndication;
            }

            public int hashCode() {
                return (this.objectTypeIndication * 31) + this.audioObjectTypeIndication;
            }

            public String toString() {
                return "Mp4aObjectType(objectTypeIndication=" + this.objectTypeIndication + ", audioObjectTypeIndication=" + this.audioObjectTypeIndication + ')';
            }

            public Mp4aObjectType(int i, int i2) {
                this.objectTypeIndication = i;
                this.audioObjectTypeIndication = i2;
            }

            public final int getAudioObjectTypeIndication() {
                return this.audioObjectTypeIndication;
            }

            public final int getObjectTypeIndication() {
                return this.objectTypeIndication;
            }
        }

        public final boolean isDolbyVisionCodec(String codecs, String supplementalCodecs) {
            if (codecs == null) {
                return false;
            }
            if (StringsKt.startsWith$default(codecs, "dvhe", false, 2, (Object) null) || StringsKt.startsWith$default(codecs, "dvh1", false, 2, (Object) null)) {
                return true;
            }
            if (supplementalCodecs == null) {
                return false;
            }
            return (StringsKt.startsWith$default(supplementalCodecs, "dvhe", false, 2, (Object) null) && StringsKt.startsWith$default(codecs, "hev1", false, 2, (Object) null)) || (StringsKt.startsWith$default(supplementalCodecs, "dvh1", false, 2, (Object) null) && StringsKt.startsWith$default(codecs, "hvc1", false, 2, (Object) null)) || ((StringsKt.startsWith$default(supplementalCodecs, "dvav", false, 2, (Object) null) && StringsKt.startsWith$default(codecs, "avc3", false, 2, (Object) null)) || ((StringsKt.startsWith$default(supplementalCodecs, "dva1", false, 2, (Object) null) && StringsKt.startsWith$default(codecs, "avc1", false, 2, (Object) null)) || (StringsKt.startsWith$default(supplementalCodecs, "dav1", false, 2, (Object) null) && StringsKt.startsWith$default(codecs, "av01", false, 2, (Object) null))));
        }

        public final Mp4aObjectType getObjectTypeFromMp4aRFC6381CodecString(String codec) {
            Regex regex = MP4A_RFC_6381_CODEC_PATTERN;
            if (codec == null) {
                return null;
            }
            MatchResult matchResultFind$default = Regex.find$default(regex, codec, 0, 2, null);
            List<String> groupValues = matchResultFind$default != null ? matchResultFind$default.getGroupValues() : null;
            List<String> list = groupValues;
            if (list != null && !list.isEmpty()) {
                String str = groupValues.get(1);
                String str2 = (String) CollectionsKt.getOrNull(groupValues, 2);
                try {
                    return new Mp4aObjectType(Integer.parseInt(str, CharsKt.checkRadix(16)), str2 != null ? Integer.parseInt(str2) : 0);
                } catch (NumberFormatException unused) {
                }
            }
            return null;
        }

        public final String getMimeTypeFromMp4ObjectType(int objectType) {
            if (objectType == 32) {
                return "video/mp4v-es";
            }
            if (objectType == 33) {
                return "video/avc";
            }
            if (objectType == 35) {
                return "video/hevc";
            }
            if (objectType == 64) {
                return "audio/mp4a-latm";
            }
            if (objectType == 163) {
                return "video/wvc1";
            }
            if (objectType == 177) {
                return "video/x-vnd.on2.vp9";
            }
            if (objectType == 221) {
                return "audio/vorbis";
            }
            if (objectType == 165) {
                return "audio/ac3";
            }
            if (objectType != 166) {
                switch (objectType) {
                    case 96:
                    case 97:
                    case 98:
                    case 99:
                    case 100:
                    case 101:
                        return "video/mpeg2";
                    case 102:
                    case 103:
                    case 104:
                        return "audio/mp4a-latm";
                    case 105:
                    case 107:
                        return "audio/mpeg";
                    case 106:
                        return "video/mpeg";
                    case 108:
                        return "image/jpeg";
                    default:
                        switch (objectType) {
                            case 169:
                            case 172:
                                return "audio/vnd.dts";
                            case 170:
                            case 171:
                                return "audio/vnd.dts.hd";
                            case 173:
                                return "audio/opus";
                            case 174:
                                return "audio/ac4";
                            default:
                                return null;
                        }
                }
            }
            return "audio/eac3";
        }

        public final String getMediaMimeType(String codecOrNull) {
            Mp4aObjectType objectTypeFromMp4aRFC6381CodecString;
            String mimeTypeFromMp4ObjectType = null;
            if (codecOrNull == null) {
                return null;
            }
            String lowerCase = StringsKt.trim((CharSequence) codecOrNull).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (StringsKt.startsWith$default(lowerCase, "avc1", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "avc3", false, 2, (Object) null)) {
                return "video/avc";
            }
            if (StringsKt.startsWith$default(lowerCase, "hev1", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "hvc1", false, 2, (Object) null)) {
                return "video/hevc";
            }
            if (StringsKt.startsWith$default(lowerCase, "dvav", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "dva1", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "dvhe", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "dvh1", false, 2, (Object) null)) {
                return "video/dolby-vision";
            }
            if (StringsKt.startsWith$default(lowerCase, "av01", false, 2, (Object) null)) {
                return "video/av01";
            }
            if (StringsKt.startsWith$default(lowerCase, "vp9", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "vp09", false, 2, (Object) null)) {
                return "video/x-vnd.on2.vp9";
            }
            if (StringsKt.startsWith$default(lowerCase, "vp8", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "vp08", false, 2, (Object) null)) {
                return "video/x-vnd.on2.vp8";
            }
            if (StringsKt.startsWith$default(lowerCase, "mp4a", false, 2, (Object) null)) {
                if (StringsKt.startsWith$default(lowerCase, "mp4a.", false, 2, (Object) null) && (objectTypeFromMp4aRFC6381CodecString = getObjectTypeFromMp4aRFC6381CodecString(lowerCase)) != null) {
                    mimeTypeFromMp4ObjectType = getMimeTypeFromMp4ObjectType(objectTypeFromMp4aRFC6381CodecString.getObjectTypeIndication());
                }
                return mimeTypeFromMp4ObjectType == null ? "audio/mp4a-latm" : mimeTypeFromMp4ObjectType;
            }
            if (StringsKt.startsWith$default(lowerCase, "mha1", false, 2, (Object) null)) {
                return "audio/mha1";
            }
            if (StringsKt.startsWith$default(lowerCase, "mhm1", false, 2, (Object) null)) {
                return "audio/mhm1";
            }
            if (StringsKt.startsWith$default(lowerCase, "ac-3", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "dac3", false, 2, (Object) null)) {
                return "audio/ac3";
            }
            if (StringsKt.startsWith$default(lowerCase, "ec-3", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "dec3", false, 2, (Object) null)) {
                return "audio/eac3";
            }
            if (StringsKt.startsWith$default(lowerCase, "ec+3", false, 2, (Object) null)) {
                return "audio/eac3-joc";
            }
            if (StringsKt.startsWith$default(lowerCase, "ac-4", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "dac4", false, 2, (Object) null)) {
                return "audio/ac4";
            }
            if (StringsKt.startsWith$default(lowerCase, "dtsc", false, 2, (Object) null)) {
                return "audio/vnd.dts";
            }
            if (StringsKt.startsWith$default(lowerCase, "dtse", false, 2, (Object) null)) {
                return "audio/vnd.dts.hd;profile=lbr";
            }
            if (StringsKt.startsWith$default(lowerCase, "dtsh", false, 2, (Object) null) || StringsKt.startsWith$default(lowerCase, "dtsl", false, 2, (Object) null)) {
                return "audio/vnd.dts.hd";
            }
            if (StringsKt.startsWith$default(lowerCase, "dtsx", false, 2, (Object) null)) {
                return "audio/vnd.dts.uhd;profile=p2";
            }
            if (StringsKt.startsWith$default(lowerCase, "opus", false, 2, (Object) null)) {
                return "audio/opus";
            }
            if (StringsKt.startsWith$default(lowerCase, "vorbis", false, 2, (Object) null)) {
                return "audio/vorbis";
            }
            if (StringsKt.startsWith$default(lowerCase, "flac", false, 2, (Object) null)) {
                return "audio/flac";
            }
            if (StringsKt.startsWith$default(lowerCase, "stpp", false, 2, (Object) null)) {
                return "application/ttml+xml";
            }
            if (StringsKt.startsWith$default(lowerCase, "wvtt", false, 2, (Object) null)) {
                return "text/vtt";
            }
            String str = lowerCase;
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "cea708", false, 2, (Object) null)) {
                return "application/cea-708";
            }
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "eia608", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "cea608", false, 2, (Object) null)) {
                return "application/cea-608";
            }
            return null;
        }

        private final String getTopLevelType(String mimeType) {
            int iIndexOf$default;
            if (mimeType == null || (iIndexOf$default = StringsKt.indexOf$default((CharSequence) mimeType, '/', 0, false, 6, (Object) null)) == -1) {
                return null;
            }
            String strSubstring = mimeType.substring(0, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return strSubstring;
        }

        public final boolean isAudio(String mimeType) {
            return Intrinsics.areEqual("audio", getTopLevelType(mimeType));
        }

        public final boolean isVideo(String mimeType) {
            return Intrinsics.areEqual("video", getTopLevelType(mimeType));
        }

        public final boolean isText(String mimeType) {
            return Intrinsics.areEqual("text", getTopLevelType(mimeType)) || Intrinsics.areEqual("application/x-media3-cues", mimeType) || Intrinsics.areEqual("application/cea-608", mimeType) || Intrinsics.areEqual("application/cea-708", mimeType) || Intrinsics.areEqual("application/x-mp4-cea-608", mimeType) || Intrinsics.areEqual("application/x-subrip", mimeType) || Intrinsics.areEqual("application/ttml+xml", mimeType) || Intrinsics.areEqual("application/x-quicktime-tx3g", mimeType) || Intrinsics.areEqual("application/x-mp4-vtt", mimeType) || Intrinsics.areEqual("application/vobsub", mimeType) || Intrinsics.areEqual("application/pgs", mimeType) || Intrinsics.areEqual("application/dvbsubs", mimeType);
        }

        public final boolean isImage(String mimeType) {
            return Intrinsics.areEqual("image", getTopLevelType(mimeType)) || Intrinsics.areEqual("application/x-image-uri", mimeType);
        }

        public final int getTrackType(String mimeType) {
            String str = mimeType;
            if (str == null || str.length() == 0) {
                return -1;
            }
            if (isAudio(mimeType)) {
                return 1;
            }
            if (isVideo(mimeType)) {
                return 2;
            }
            if (isText(mimeType)) {
                return 3;
            }
            if (isImage(mimeType)) {
                return 4;
            }
            if (Intrinsics.areEqual("application/id3", mimeType) || Intrinsics.areEqual("application/x-emsg", mimeType) || Intrinsics.areEqual("application/x-scte35", mimeType) || Intrinsics.areEqual("application/x-icy", mimeType) || Intrinsics.areEqual("application/vnd.dvb.ait", mimeType)) {
                return 5;
            }
            return Intrinsics.areEqual("application/x-camera-motion", mimeType) ? 6 : -1;
        }

        public final int getTrackTypeOfCodec(String codec) {
            return getTrackType(getMediaMimeType(codec));
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Mp4Box;", "", "<init>", "()V", "FULL_HEADER_SIZE", "", "TYPE_pssh", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Mp4Box {
        public static final int FULL_HEADER_SIZE = 12;
        public static final Mp4Box INSTANCE = new Mp4Box();
        public static final int TYPE_pssh = 1886614376;

        private Mp4Box() {
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$PsshAtomUtil;", "", "<init>", "()V", "buildPsshAtom", "", "systemId", "Ljava/util/UUID;", "keyIds", "", "data", "(Ljava/util/UUID;[Ljava/util/UUID;[B)[B", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class PsshAtomUtil {
        public static final PsshAtomUtil INSTANCE = new PsshAtomUtil();

        private PsshAtomUtil() {
        }

        public final byte[] buildPsshAtom(UUID systemId, UUID[] keyIds, byte[] data) {
            Intrinsics.checkNotNullParameter(systemId, "systemId");
            int length = (data != null ? data.length : 0) + 32;
            if (keyIds != null) {
                length += (keyIds.length * 16) + 4;
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
            Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(...)");
            byteBufferAllocate.putInt(length);
            byteBufferAllocate.putInt(1886614376);
            byteBufferAllocate.putInt(keyIds != null ? 16777216 : 0);
            byteBufferAllocate.putLong(systemId.getMostSignificantBits());
            byteBufferAllocate.putLong(systemId.getLeastSignificantBits());
            if (keyIds != null) {
                byteBufferAllocate.putInt(keyIds.length);
                for (UUID uuid : keyIds) {
                    byteBufferAllocate.putLong(uuid.getMostSignificantBits());
                    byteBufferAllocate.putLong(uuid.getLeastSignificantBits());
                }
            }
            if (data != null && data.length != 0) {
                byteBufferAllocate.putInt(data.length);
                byteBufferAllocate.put(data);
            } else {
                byteBufferAllocate.putInt(0);
            }
            byte[] bArrArray = byteBufferAllocate.array();
            Intrinsics.checkNotNullExpressionValue(bArrArray, "array(...)");
            return bArrArray;
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J5\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$ParserException;", "Ljava/io/IOException;", ContentType.Message.TYPE, "", "cause", "", "contentIsMalformed", "", "dataType", "", "<init>", "(Ljava/lang/String;Ljava/lang/Throwable;ZI)V", "getMessage", "()Ljava/lang/String;", "getCause", "()Ljava/lang/Throwable;", "getContentIsMalformed", "()Z", "getDataType", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "toString", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ParserException extends IOException {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final int DATA_TYPE_MANIFEST = 4;
        private final Throwable cause;
        private final boolean contentIsMalformed;
        private final int dataType;
        private final String message;

        public static /* synthetic */ ParserException copy$default(ParserException parserException, String str, Throwable th, boolean z, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = parserException.message;
            }
            if ((i2 & 2) != 0) {
                th = parserException.cause;
            }
            if ((i2 & 4) != 0) {
                z = parserException.contentIsMalformed;
            }
            if ((i2 & 8) != 0) {
                i = parserException.dataType;
            }
            return parserException.copy(str, th, z, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Throwable getCause() {
            return this.cause;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getContentIsMalformed() {
            return this.contentIsMalformed;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getDataType() {
            return this.dataType;
        }

        public final ParserException copy(String message, Throwable cause, boolean contentIsMalformed, int dataType) {
            return new ParserException(message, cause, contentIsMalformed, dataType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParserException)) {
                return false;
            }
            ParserException parserException = (ParserException) other;
            return Intrinsics.areEqual(this.message, parserException.message) && Intrinsics.areEqual(this.cause, parserException.cause) && this.contentIsMalformed == parserException.contentIsMalformed && this.dataType == parserException.dataType;
        }

        public int hashCode() {
            String str = this.message;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Throwable th = this.cause;
            return ((((iHashCode + (th != null ? th.hashCode() : 0)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.contentIsMalformed)) * 31) + this.dataType;
        }

        @Override // java.lang.Throwable
        public String toString() {
            return "ParserException(message=" + this.message + ", cause=" + this.cause + ", contentIsMalformed=" + this.contentIsMalformed + ", dataType=" + this.dataType + ')';
        }

        public ParserException(String str, Throwable th, boolean z, int i) {
            super(str, th);
            this.message = str;
            this.cause = th;
            this.contentIsMalformed = z;
            this.dataType = i;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return this.message;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }

        public final boolean getContentIsMalformed() {
            return this.contentIsMalformed;
        }

        public final int getDataType() {
            return this.dataType;
        }

        /* JADX INFO: compiled from: HlsPlaylistParser.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$ParserException$Companion;", "", "<init>", "()V", "DATA_TYPE_MANIFEST", "", "createForMalformedManifest", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$ParserException;", ContentType.Message.TYPE, "", "cause", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final ParserException createForMalformedManifest(String message, Throwable cause) {
                return new ParserException(message, cause, true, 4);
            }
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ(\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$DrmInitData;", "", "schemeType", "", "schemeDatas", "", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$SchemeData;", "<init>", "(Ljava/lang/String;[Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$SchemeData;)V", "getSchemeType", "()Ljava/lang/String;", "getSchemeDatas", "()[Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$SchemeData;", "[Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$SchemeData;", "component1", "component2", "copy", "(Ljava/lang/String;[Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$SchemeData;)Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$DrmInitData;", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class DrmInitData {
        private final SchemeData[] schemeDatas;
        private final String schemeType;

        public static /* synthetic */ DrmInitData copy$default(DrmInitData drmInitData, String str, SchemeData[] schemeDataArr, int i, Object obj) {
            if ((i & 1) != 0) {
                str = drmInitData.schemeType;
            }
            if ((i & 2) != 0) {
                schemeDataArr = drmInitData.schemeDatas;
            }
            return drmInitData.copy(str, schemeDataArr);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSchemeType() {
            return this.schemeType;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final SchemeData[] getSchemeDatas() {
            return this.schemeDatas;
        }

        public final DrmInitData copy(String schemeType, SchemeData[] schemeDatas) {
            Intrinsics.checkNotNullParameter(schemeType, "schemeType");
            Intrinsics.checkNotNullParameter(schemeDatas, "schemeDatas");
            return new DrmInitData(schemeType, schemeDatas);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DrmInitData)) {
                return false;
            }
            DrmInitData drmInitData = (DrmInitData) other;
            return Intrinsics.areEqual(this.schemeType, drmInitData.schemeType) && Intrinsics.areEqual(this.schemeDatas, drmInitData.schemeDatas);
        }

        public int hashCode() {
            return (this.schemeType.hashCode() * 31) + Arrays.hashCode(this.schemeDatas);
        }

        public String toString() {
            return "DrmInitData(schemeType=" + this.schemeType + ", schemeDatas=" + Arrays.toString(this.schemeDatas) + ')';
        }

        public DrmInitData(String schemeType, SchemeData[] schemeDatas) {
            Intrinsics.checkNotNullParameter(schemeType, "schemeType");
            Intrinsics.checkNotNullParameter(schemeDatas, "schemeDatas");
            this.schemeType = schemeType;
            this.schemeDatas = schemeDatas;
        }

        public final String getSchemeType() {
            return this.schemeType;
        }

        public final SchemeData[] getSchemeDatas() {
            return this.schemeDatas;
        }
    }

    private final String parseStringAttr(String line, Regex pattern, Map<String, String> variableDefinitions) throws ParserException {
        String optionalStringAttr = parseOptionalStringAttr(line, pattern, variableDefinitions);
        if (optionalStringAttr != null) {
            return optionalStringAttr;
        }
        throw ParserException.INSTANCE.createForMalformedManifest("Couldn't match " + pattern + " in " + line, null);
    }

    private final String parseOptionalStringAttr(String line, Regex pattern, String defaultValue, Map<String, String> variableDefinitions) {
        List<String> groupValues;
        String str;
        MatchResult matchResultFind$default = Regex.find$default(pattern, line, 0, 2, null);
        if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null && (str = groupValues.get(1)) != null) {
            defaultValue = str;
        }
        return (variableDefinitions == null || variableDefinitions.isEmpty() || defaultValue == null) ? defaultValue : replaceVariableReferences(defaultValue, variableDefinitions);
    }

    private final String replaceVariableReferences(String string, final Map<String, String> variableDefinitions) {
        return REGEX_VARIABLE_REFERENCE.replace(string, new Function1() { // from class: com.lagradost.cloudstream3.utils.HlsPlaylistParser$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HlsPlaylistParser.replaceVariableReferences$lambda$0(variableDefinitions, (MatchResult) obj);
            }
        });
    }

    static final CharSequence replaceVariableReferences$lambda$0(Map map, MatchResult matchResult) {
        String str;
        Intrinsics.checkNotNullParameter(matchResult, "matchResult");
        return (map == null || (str = (String) map.get(matchResult.getGroupValues().get(1))) == null) ? matchResult.getValue() : str;
    }

    private final String parseOptionalStringAttr(String line, Regex pattern, Map<String, String> variableDefinitions) {
        return parseOptionalStringAttr(line, pattern, null, variableDefinitions);
    }

    private final SchemeData parseDrmSchemeData(String line, String keyFormat, Map<String, String> variableDefinitions) throws ParserException {
        String optionalStringAttr = parseOptionalStringAttr(line, REGEX_KEYFORMATVERSIONS, "1", variableDefinitions);
        if (Intrinsics.areEqual(KEYFORMAT_WIDEVINE_PSSH_BINARY, keyFormat)) {
            String stringAttr = parseStringAttr(line, REGEX_URI, variableDefinitions);
            UUID widevine_uuid = C.INSTANCE.getWIDEVINE_UUID();
            String strSubstring = stringAttr.substring(StringsKt.indexOf$default((CharSequence) stringAttr, AbstractJsonLexerKt.COMMA, 0, false, 6, (Object) null));
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            return new SchemeData(widevine_uuid, null, "video/mp4", MainAPIKt.base64DecodeArray(strSubstring), 2, null);
        }
        if (Intrinsics.areEqual(KEYFORMAT_WIDEVINE_PSSH_JSON, keyFormat)) {
            return new SchemeData(C.INSTANCE.getWIDEVINE_UUID(), null, "hls", StringsKt.encodeToByteArray(line), 2, null);
        }
        if (!Intrinsics.areEqual(KEYFORMAT_PLAYREADY, keyFormat) || !Intrinsics.areEqual("1", optionalStringAttr)) {
            return null;
        }
        String stringAttr2 = parseStringAttr(line, REGEX_URI, variableDefinitions);
        String strSubstring2 = stringAttr2.substring(StringsKt.indexOf$default((CharSequence) stringAttr2, AbstractJsonLexerKt.COMMA, 0, false, 6, (Object) null));
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
        byte[] bArrBuildPsshAtom = PsshAtomUtil.INSTANCE.buildPsshAtom(C.INSTANCE.getPLAYREADY_UUID(), null, MainAPIKt.base64DecodeArray(strSubstring2));
        return new SchemeData(C.INSTANCE.getPLAYREADY_UUID(), null, "video/mp4", bArrBuildPsshAtom, 2, null);
    }

    private final String parseEncryptionScheme(String method) {
        if (Intrinsics.areEqual(METHOD_SAMPLE_AES_CENC, method) || Intrinsics.areEqual(METHOD_SAMPLE_AES_CTR, method)) {
            return "cenc";
        }
        return "cbcs";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    private final int parseSelectionFlags(String line) {
        boolean optionalBooleanAttribute = parseOptionalBooleanAttribute(line, REGEX_DEFAULT, false);
        ?? r0 = optionalBooleanAttribute;
        if (parseOptionalBooleanAttribute(line, REGEX_FORCED, false)) {
            r0 = (optionalBooleanAttribute ? 1 : 0) | 2;
        }
        return parseOptionalBooleanAttribute(line, REGEX_AUTOSELECT, false) ? r0 | 4 : r0;
    }

    private final int parseRoleFlags(String line, Map<String, String> variableDefinitions) {
        String optionalStringAttr = parseOptionalStringAttr(line, REGEX_CHARACTERISTICS, variableDefinitions);
        String str = optionalStringAttr;
        int i = 0;
        if (str != null && str.length() != 0) {
            String[] strArrSplit = Util.INSTANCE.split(optionalStringAttr, ",");
            i = ArraysKt.contains(strArrSplit, "public.accessibility.describes-video") ? 512 : 0;
            if (ArraysKt.contains(strArrSplit, "public.accessibility.transcribes-spoken-dialog")) {
                i |= 4096;
            }
            if (ArraysKt.contains(strArrSplit, "public.accessibility.describes-music-and-sound")) {
                i |= 1024;
            }
            if (ArraysKt.contains(strArrSplit, "public.easy-to-read")) {
                return i | 8192;
            }
        }
        return i;
    }

    private final boolean parseOptionalBooleanAttribute(String line, Regex pattern, boolean defaultValue) {
        List<String> groupValues;
        String str = null;
        MatchResult matchResultFind$default = Regex.find$default(pattern, line, 0, 2, null);
        if (matchResultFind$default != null && (groupValues = matchResultFind$default.getGroupValues()) != null) {
            str = groupValues.get(1);
        }
        return str != null ? Intrinsics.areEqual(str, BOOLEAN_TRUE) : defaultValue;
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0017J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0007HÆ\u0003JM\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010#\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020&HÖ\u0081\u0004J\n\u0010'\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Variant;", "", ImagesContract.URL, "Lio/ktor/http/Url;", "format", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Format;", "videoGroupId", "", "audioGroupId", "subtitleGroupId", "captionGroupId", "<init>", "(Lio/ktor/http/Url;Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Format;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getUrl", "()Lio/ktor/http/Url;", "getFormat", "()Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Format;", "getVideoGroupId", "()Ljava/lang/String;", "getAudioGroupId", "getSubtitleGroupId", "getCaptionGroupId", "isPlayableStandalone", "", "playlist", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$HlsMultivariantPlaylist;", "isTrickPlay", "mustContainAudio", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Variant {
        private final String audioGroupId;
        private final String captionGroupId;
        private final Format format;
        private final String subtitleGroupId;
        private final Url url;
        private final String videoGroupId;

        public static /* synthetic */ Variant copy$default(Variant variant, Url url, Format format, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                url = variant.url;
            }
            if ((i & 2) != 0) {
                format = variant.format;
            }
            if ((i & 4) != 0) {
                str = variant.videoGroupId;
            }
            if ((i & 8) != 0) {
                str2 = variant.audioGroupId;
            }
            if ((i & 16) != 0) {
                str3 = variant.subtitleGroupId;
            }
            if ((i & 32) != 0) {
                str4 = variant.captionGroupId;
            }
            String str5 = str3;
            String str6 = str4;
            return variant.copy(url, format, str, str2, str5, str6);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Url getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Format getFormat() {
            return this.format;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getVideoGroupId() {
            return this.videoGroupId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getAudioGroupId() {
            return this.audioGroupId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSubtitleGroupId() {
            return this.subtitleGroupId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getCaptionGroupId() {
            return this.captionGroupId;
        }

        public final Variant copy(Url url, Format format, String videoGroupId, String audioGroupId, String subtitleGroupId, String captionGroupId) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(format, "format");
            return new Variant(url, format, videoGroupId, audioGroupId, subtitleGroupId, captionGroupId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Variant)) {
                return false;
            }
            Variant variant = (Variant) other;
            return Intrinsics.areEqual(this.url, variant.url) && Intrinsics.areEqual(this.format, variant.format) && Intrinsics.areEqual(this.videoGroupId, variant.videoGroupId) && Intrinsics.areEqual(this.audioGroupId, variant.audioGroupId) && Intrinsics.areEqual(this.subtitleGroupId, variant.subtitleGroupId) && Intrinsics.areEqual(this.captionGroupId, variant.captionGroupId);
        }

        public int hashCode() {
            int iHashCode = ((this.url.hashCode() * 31) + this.format.hashCode()) * 31;
            String str = this.videoGroupId;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.audioGroupId;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.subtitleGroupId;
            int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.captionGroupId;
            return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            return "Variant(url=" + this.url + ", format=" + this.format + ", videoGroupId=" + this.videoGroupId + ", audioGroupId=" + this.audioGroupId + ", subtitleGroupId=" + this.subtitleGroupId + ", captionGroupId=" + this.captionGroupId + ')';
        }

        public Variant(Url url, Format format, String str, String str2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(format, "format");
            this.url = url;
            this.format = format;
            this.videoGroupId = str;
            this.audioGroupId = str2;
            this.subtitleGroupId = str3;
            this.captionGroupId = str4;
        }

        public final Url getUrl() {
            return this.url;
        }

        public final Format getFormat() {
            return this.format;
        }

        public final String getVideoGroupId() {
            return this.videoGroupId;
        }

        public final String getAudioGroupId() {
            return this.audioGroupId;
        }

        public final String getSubtitleGroupId() {
            return this.subtitleGroupId;
        }

        public final String getCaptionGroupId() {
            return this.captionGroupId;
        }

        public final boolean isPlayableStandalone(HlsMultivariantPlaylist playlist) {
            Intrinsics.checkNotNullParameter(playlist, "playlist");
            return mustContainAudio(playlist) && !isTrickPlay();
        }

        public final boolean isTrickPlay() {
            return (this.format.getRoleFlags() & 16384) != 0;
        }

        public final boolean mustContainAudio(HlsMultivariantPlaylist playlist) {
            Object next;
            Intrinsics.checkNotNullParameter(playlist, "playlist");
            if (this.audioGroupId == null) {
                return true;
            }
            Iterator<T> it = playlist.getAudios().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((Rendition) next).getGroupId(), this.audioGroupId));
            Rendition rendition = (Rendition) next;
            return (rendition != null ? rendition.getUrl() : null) == null;
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J3\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0007HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Rendition;", "", ImagesContract.URL, "Lio/ktor/http/Url;", "format", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Format;", "groupId", "", "name", "<init>", "(Lio/ktor/http/Url;Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Format;Ljava/lang/String;Ljava/lang/String;)V", "getUrl", "()Lio/ktor/http/Url;", "getFormat", "()Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Format;", "getGroupId", "()Ljava/lang/String;", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Rendition {
        private final Format format;
        private final String groupId;
        private final String name;
        private final Url url;

        public static /* synthetic */ Rendition copy$default(Rendition rendition, Url url, Format format, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                url = rendition.url;
            }
            if ((i & 2) != 0) {
                format = rendition.format;
            }
            if ((i & 4) != 0) {
                str = rendition.groupId;
            }
            if ((i & 8) != 0) {
                str2 = rendition.name;
            }
            return rendition.copy(url, format, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Url getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Format getFormat() {
            return this.format;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getGroupId() {
            return this.groupId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final Rendition copy(Url url, Format format, String groupId, String name) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(groupId, "groupId");
            Intrinsics.checkNotNullParameter(name, "name");
            return new Rendition(url, format, groupId, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Rendition)) {
                return false;
            }
            Rendition rendition = (Rendition) other;
            return Intrinsics.areEqual(this.url, rendition.url) && Intrinsics.areEqual(this.format, rendition.format) && Intrinsics.areEqual(this.groupId, rendition.groupId) && Intrinsics.areEqual(this.name, rendition.name);
        }

        public int hashCode() {
            Url url = this.url;
            return ((((((url == null ? 0 : url.hashCode()) * 31) + this.format.hashCode()) * 31) + this.groupId.hashCode()) * 31) + this.name.hashCode();
        }

        public String toString() {
            return "Rendition(url=" + this.url + ", format=" + this.format + ", groupId=" + this.groupId + ", name=" + this.name + ')';
        }

        public Rendition(Url url, Format format, String groupId, String name) {
            Intrinsics.checkNotNullParameter(format, "format");
            Intrinsics.checkNotNullParameter(groupId, "groupId");
            Intrinsics.checkNotNullParameter(name, "name");
            this.url = url;
            this.format = format;
            this.groupId = groupId;
            this.name = name;
        }

        public final Url getUrl() {
            return this.url;
        }

        public final Format getFormat() {
            return this.format;
        }

        public final String getGroupId() {
            return this.groupId;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b#\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B©\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u0005\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0005\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0011\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\t0\u0005HÆ\u0003J\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00020\t0\u0005HÆ\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\t0\u0005HÆ\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\t0\u0005HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0011\u00101\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0005HÆ\u0003J\u0015\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00130\u0005HÆ\u0003J\t\u00104\u001a\u00020\u0015HÆ\u0003JÁ\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00052\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u0015HÆ\u0001J\u0014\u00106\u001a\u00020\u00152\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00108\u001a\u000209HÖ\u0081\u0004J\n\u0010:\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0011¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006;"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$HlsMultivariantPlaylist;", "", "baseUri", "", "tags", "", "variants", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Variant;", "videos", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Rendition;", "audios", "subtitles", "closedCaptions", "muxedAudioFormat", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Format;", "muxedCaptionFormats", "variableDefinitions", "", "sessionKeyDrmInitData", "Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$DrmInitData;", "hasIndependentSegments", "", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Format;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Z)V", "getBaseUri", "()Ljava/lang/String;", "getTags", "()Ljava/util/List;", "getVariants", "getVideos", "getAudios", "getSubtitles", "getClosedCaptions", "getMuxedAudioFormat", "()Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Format;", "getMuxedCaptionFormats", "getVariableDefinitions", "()Ljava/util/Map;", "getSessionKeyDrmInitData", "getHasIndependentSegments", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HlsMultivariantPlaylist {
        private final List<Rendition> audios;
        private final String baseUri;
        private final List<Rendition> closedCaptions;
        private final boolean hasIndependentSegments;
        private final Format muxedAudioFormat;
        private final List<Format> muxedCaptionFormats;
        private final List<DrmInitData> sessionKeyDrmInitData;
        private final List<Rendition> subtitles;
        private final List<String> tags;
        private final Map<String, String> variableDefinitions;
        private final List<Variant> variants;
        private final List<Rendition> videos;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ HlsMultivariantPlaylist copy$default(HlsMultivariantPlaylist hlsMultivariantPlaylist, String str, List list, List list2, List list3, List list4, List list5, List list6, Format format, List list7, Map map, List list8, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = hlsMultivariantPlaylist.baseUri;
            }
            if ((i & 2) != 0) {
                list = hlsMultivariantPlaylist.tags;
            }
            if ((i & 4) != 0) {
                list2 = hlsMultivariantPlaylist.variants;
            }
            if ((i & 8) != 0) {
                list3 = hlsMultivariantPlaylist.videos;
            }
            if ((i & 16) != 0) {
                list4 = hlsMultivariantPlaylist.audios;
            }
            if ((i & 32) != 0) {
                list5 = hlsMultivariantPlaylist.subtitles;
            }
            if ((i & 64) != 0) {
                list6 = hlsMultivariantPlaylist.closedCaptions;
            }
            if ((i & 128) != 0) {
                format = hlsMultivariantPlaylist.muxedAudioFormat;
            }
            if ((i & 256) != 0) {
                list7 = hlsMultivariantPlaylist.muxedCaptionFormats;
            }
            if ((i & 512) != 0) {
                map = hlsMultivariantPlaylist.variableDefinitions;
            }
            if ((i & 1024) != 0) {
                list8 = hlsMultivariantPlaylist.sessionKeyDrmInitData;
            }
            if ((i & 2048) != 0) {
                z = hlsMultivariantPlaylist.hasIndependentSegments;
            }
            List list9 = list8;
            boolean z2 = z;
            List list10 = list7;
            Map map2 = map;
            List list11 = list6;
            Format format2 = format;
            List list12 = list4;
            List list13 = list5;
            return hlsMultivariantPlaylist.copy(str, list, list2, list3, list12, list13, list11, format2, list10, map2, list9, z2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBaseUri() {
            return this.baseUri;
        }

        public final Map<String, String> component10() {
            return this.variableDefinitions;
        }

        public final List<DrmInitData> component11() {
            return this.sessionKeyDrmInitData;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final boolean getHasIndependentSegments() {
            return this.hasIndependentSegments;
        }

        public final List<String> component2() {
            return this.tags;
        }

        public final List<Variant> component3() {
            return this.variants;
        }

        public final List<Rendition> component4() {
            return this.videos;
        }

        public final List<Rendition> component5() {
            return this.audios;
        }

        public final List<Rendition> component6() {
            return this.subtitles;
        }

        public final List<Rendition> component7() {
            return this.closedCaptions;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Format getMuxedAudioFormat() {
            return this.muxedAudioFormat;
        }

        public final List<Format> component9() {
            return this.muxedCaptionFormats;
        }

        public final HlsMultivariantPlaylist copy(String baseUri, List<String> tags, List<Variant> variants, List<Rendition> videos, List<Rendition> audios, List<Rendition> subtitles, List<Rendition> closedCaptions, Format muxedAudioFormat, List<Format> muxedCaptionFormats, Map<String, String> variableDefinitions, List<DrmInitData> sessionKeyDrmInitData, boolean hasIndependentSegments) {
            Intrinsics.checkNotNullParameter(baseUri, "baseUri");
            Intrinsics.checkNotNullParameter(tags, "tags");
            Intrinsics.checkNotNullParameter(variants, "variants");
            Intrinsics.checkNotNullParameter(videos, "videos");
            Intrinsics.checkNotNullParameter(audios, "audios");
            Intrinsics.checkNotNullParameter(subtitles, "subtitles");
            Intrinsics.checkNotNullParameter(closedCaptions, "closedCaptions");
            Intrinsics.checkNotNullParameter(variableDefinitions, "variableDefinitions");
            Intrinsics.checkNotNullParameter(sessionKeyDrmInitData, "sessionKeyDrmInitData");
            return new HlsMultivariantPlaylist(baseUri, tags, variants, videos, audios, subtitles, closedCaptions, muxedAudioFormat, muxedCaptionFormats, variableDefinitions, sessionKeyDrmInitData, hasIndependentSegments);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HlsMultivariantPlaylist)) {
                return false;
            }
            HlsMultivariantPlaylist hlsMultivariantPlaylist = (HlsMultivariantPlaylist) other;
            return Intrinsics.areEqual(this.baseUri, hlsMultivariantPlaylist.baseUri) && Intrinsics.areEqual(this.tags, hlsMultivariantPlaylist.tags) && Intrinsics.areEqual(this.variants, hlsMultivariantPlaylist.variants) && Intrinsics.areEqual(this.videos, hlsMultivariantPlaylist.videos) && Intrinsics.areEqual(this.audios, hlsMultivariantPlaylist.audios) && Intrinsics.areEqual(this.subtitles, hlsMultivariantPlaylist.subtitles) && Intrinsics.areEqual(this.closedCaptions, hlsMultivariantPlaylist.closedCaptions) && Intrinsics.areEqual(this.muxedAudioFormat, hlsMultivariantPlaylist.muxedAudioFormat) && Intrinsics.areEqual(this.muxedCaptionFormats, hlsMultivariantPlaylist.muxedCaptionFormats) && Intrinsics.areEqual(this.variableDefinitions, hlsMultivariantPlaylist.variableDefinitions) && Intrinsics.areEqual(this.sessionKeyDrmInitData, hlsMultivariantPlaylist.sessionKeyDrmInitData) && this.hasIndependentSegments == hlsMultivariantPlaylist.hasIndependentSegments;
        }

        public int hashCode() {
            int iHashCode = ((((((((((((this.baseUri.hashCode() * 31) + this.tags.hashCode()) * 31) + this.variants.hashCode()) * 31) + this.videos.hashCode()) * 31) + this.audios.hashCode()) * 31) + this.subtitles.hashCode()) * 31) + this.closedCaptions.hashCode()) * 31;
            Format format = this.muxedAudioFormat;
            int iHashCode2 = (iHashCode + (format == null ? 0 : format.hashCode())) * 31;
            List<Format> list = this.muxedCaptionFormats;
            return ((((((iHashCode2 + (list != null ? list.hashCode() : 0)) * 31) + this.variableDefinitions.hashCode()) * 31) + this.sessionKeyDrmInitData.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.hasIndependentSegments);
        }

        public String toString() {
            return "HlsMultivariantPlaylist(baseUri=" + this.baseUri + ", tags=" + this.tags + ", variants=" + this.variants + ", videos=" + this.videos + ", audios=" + this.audios + ", subtitles=" + this.subtitles + ", closedCaptions=" + this.closedCaptions + ", muxedAudioFormat=" + this.muxedAudioFormat + ", muxedCaptionFormats=" + this.muxedCaptionFormats + ", variableDefinitions=" + this.variableDefinitions + ", sessionKeyDrmInitData=" + this.sessionKeyDrmInitData + ", hasIndependentSegments=" + this.hasIndependentSegments + ')';
        }

        public HlsMultivariantPlaylist(String baseUri, List<String> tags, List<Variant> variants, List<Rendition> videos, List<Rendition> audios, List<Rendition> subtitles, List<Rendition> closedCaptions, Format format, List<Format> list, Map<String, String> variableDefinitions, List<DrmInitData> sessionKeyDrmInitData, boolean z) {
            Intrinsics.checkNotNullParameter(baseUri, "baseUri");
            Intrinsics.checkNotNullParameter(tags, "tags");
            Intrinsics.checkNotNullParameter(variants, "variants");
            Intrinsics.checkNotNullParameter(videos, "videos");
            Intrinsics.checkNotNullParameter(audios, "audios");
            Intrinsics.checkNotNullParameter(subtitles, "subtitles");
            Intrinsics.checkNotNullParameter(closedCaptions, "closedCaptions");
            Intrinsics.checkNotNullParameter(variableDefinitions, "variableDefinitions");
            Intrinsics.checkNotNullParameter(sessionKeyDrmInitData, "sessionKeyDrmInitData");
            this.baseUri = baseUri;
            this.tags = tags;
            this.variants = variants;
            this.videos = videos;
            this.audios = audios;
            this.subtitles = subtitles;
            this.closedCaptions = closedCaptions;
            this.muxedAudioFormat = format;
            this.muxedCaptionFormats = list;
            this.variableDefinitions = variableDefinitions;
            this.sessionKeyDrmInitData = sessionKeyDrmInitData;
            this.hasIndependentSegments = z;
        }

        public /* synthetic */ HlsMultivariantPlaylist(String str, List list, List list2, List list3, List list4, List list5, List list6, Format format, List list7, Map map, List list8, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, list, list2, list3, list4, list5, list6, (i & 128) != 0 ? null : format, list7, map, list8, z);
        }

        public final String getBaseUri() {
            return this.baseUri;
        }

        public final List<String> getTags() {
            return this.tags;
        }

        public final List<Variant> getVariants() {
            return this.variants;
        }

        public final List<Rendition> getVideos() {
            return this.videos;
        }

        public final List<Rendition> getAudios() {
            return this.audios;
        }

        public final List<Rendition> getSubtitles() {
            return this.subtitles;
        }

        public final List<Rendition> getClosedCaptions() {
            return this.closedCaptions;
        }

        public final Format getMuxedAudioFormat() {
            return this.muxedAudioFormat;
        }

        public final List<Format> getMuxedCaptionFormats() {
            return this.muxedCaptionFormats;
        }

        public final Map<String, String> getVariableDefinitions() {
            return this.variableDefinitions;
        }

        public final List<DrmInitData> getSessionKeyDrmInitData() {
            return this.sessionKeyDrmInitData;
        }

        public final boolean getHasIndependentSegments() {
            return this.hasIndependentSegments;
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0006HÆ\u0003JM\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$VariantInfo;", "", "averageBitrate", "", "peakBitrate", "videoGroupId", "", "audioGroupId", "subtitleGroupId", "captionGroupId", "<init>", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAverageBitrate", "()I", "getPeakBitrate", "getVideoGroupId", "()Ljava/lang/String;", "getAudioGroupId", "getSubtitleGroupId", "getCaptionGroupId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VariantInfo {
        private final String audioGroupId;
        private final int averageBitrate;
        private final String captionGroupId;
        private final int peakBitrate;
        private final String subtitleGroupId;
        private final String videoGroupId;

        public static /* synthetic */ VariantInfo copy$default(VariantInfo variantInfo, int i, int i2, String str, String str2, String str3, String str4, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = variantInfo.averageBitrate;
            }
            if ((i3 & 2) != 0) {
                i2 = variantInfo.peakBitrate;
            }
            if ((i3 & 4) != 0) {
                str = variantInfo.videoGroupId;
            }
            if ((i3 & 8) != 0) {
                str2 = variantInfo.audioGroupId;
            }
            if ((i3 & 16) != 0) {
                str3 = variantInfo.subtitleGroupId;
            }
            if ((i3 & 32) != 0) {
                str4 = variantInfo.captionGroupId;
            }
            String str5 = str3;
            String str6 = str4;
            return variantInfo.copy(i, i2, str, str2, str5, str6);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getAverageBitrate() {
            return this.averageBitrate;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getPeakBitrate() {
            return this.peakBitrate;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getVideoGroupId() {
            return this.videoGroupId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getAudioGroupId() {
            return this.audioGroupId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSubtitleGroupId() {
            return this.subtitleGroupId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getCaptionGroupId() {
            return this.captionGroupId;
        }

        public final VariantInfo copy(int averageBitrate, int peakBitrate, String videoGroupId, String audioGroupId, String subtitleGroupId, String captionGroupId) {
            return new VariantInfo(averageBitrate, peakBitrate, videoGroupId, audioGroupId, subtitleGroupId, captionGroupId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VariantInfo)) {
                return false;
            }
            VariantInfo variantInfo = (VariantInfo) other;
            return this.averageBitrate == variantInfo.averageBitrate && this.peakBitrate == variantInfo.peakBitrate && Intrinsics.areEqual(this.videoGroupId, variantInfo.videoGroupId) && Intrinsics.areEqual(this.audioGroupId, variantInfo.audioGroupId) && Intrinsics.areEqual(this.subtitleGroupId, variantInfo.subtitleGroupId) && Intrinsics.areEqual(this.captionGroupId, variantInfo.captionGroupId);
        }

        public int hashCode() {
            int i = ((this.averageBitrate * 31) + this.peakBitrate) * 31;
            String str = this.videoGroupId;
            int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.audioGroupId;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.subtitleGroupId;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.captionGroupId;
            return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            return "VariantInfo(averageBitrate=" + this.averageBitrate + ", peakBitrate=" + this.peakBitrate + ", videoGroupId=" + this.videoGroupId + ", audioGroupId=" + this.audioGroupId + ", subtitleGroupId=" + this.subtitleGroupId + ", captionGroupId=" + this.captionGroupId + ')';
        }

        public VariantInfo(int i, int i2, String str, String str2, String str3, String str4) {
            this.averageBitrate = i;
            this.peakBitrate = i2;
            this.videoGroupId = str;
            this.audioGroupId = str2;
            this.subtitleGroupId = str3;
            this.captionGroupId = str4;
        }

        public /* synthetic */ VariantInfo(int i, int i2, String str, String str2, String str3, String str4, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? -1 : i, i2, (i3 & 4) != 0 ? null : str, (i3 & 8) != 0 ? null : str2, (i3 & 16) != 0 ? null : str3, (i3 & 32) != 0 ? null : str4);
        }

        public final int getAverageBitrate() {
            return this.averageBitrate;
        }

        public final int getPeakBitrate() {
            return this.peakBitrate;
        }

        public final String getVideoGroupId() {
            return this.videoGroupId;
        }

        public final String getAudioGroupId() {
            return this.audioGroupId;
        }

        public final String getSubtitleGroupId() {
            return this.subtitleGroupId;
        }

        public final String getCaptionGroupId() {
            return this.captionGroupId;
        }
    }

    /* JADX INFO: compiled from: HlsPlaylistParser.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b/\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 F2\u00020\u0001:\u0001FBÇ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0007HÆ\u0003J\t\u00102\u001a\u00020\u0007HÆ\u0003J\t\u00103\u001a\u00020\u0007HÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\t\u00105\u001a\u00020\u0007HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0007HÆ\u0003J\t\u0010:\u001a\u00020\u0007HÆ\u0003J\t\u0010;\u001a\u00020\u0012HÆ\u0003J\t\u0010<\u001a\u00020\u0007HÆ\u0003J\t\u0010=\u001a\u00020\u0012HÆ\u0003J\t\u0010>\u001a\u00020\u0007HÆ\u0003J\t\u0010?\u001a\u00020\u0007HÆ\u0003JÉ\u0001\u0010@\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0016\u001a\u00020\u0007HÆ\u0001J\u0014\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010D\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010E\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0011\u0010\u0010\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001eR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0013\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001eR\u0011\u0010\u0014\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b+\u0010)R\u0011\u0010\u0015\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001eR\u0011\u0010\u0016\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001e¨\u0006G"}, d2 = {"Lcom/lagradost/cloudstream3/utils/HlsPlaylistParser$Format;", "", "id", "", "label", "language", "selectionFlags", "", "roleFlags", "averageBitrate", "peakBitrate", "bitrate", "codecs", "containerMimeType", "sampleMimeType", "width", "height", "frameRate", "", "rotationDegrees", "pixelWidthHeightRatio", "channelCount", "accessibilityChannel", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IIFIFII)V", "getId", "()Ljava/lang/String;", "getLabel", "getLanguage", "getSelectionFlags", "()I", "getRoleFlags", "getAverageBitrate", "getPeakBitrate", "getBitrate", "getCodecs", "getContainerMimeType", "getSampleMimeType", "getWidth", "getHeight", "getFrameRate", "()F", "getRotationDegrees", "getPixelWidthHeightRatio", "getChannelCount", "getAccessibilityChannel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Format {
        public static final int NO_VALUE = -1;
        private final int accessibilityChannel;
        private final int averageBitrate;
        private final int bitrate;
        private final int channelCount;
        private final String codecs;
        private final String containerMimeType;
        private final float frameRate;
        private final int height;
        private final String id;
        private final String label;
        private final String language;
        private final int peakBitrate;
        private final float pixelWidthHeightRatio;
        private final int roleFlags;
        private final int rotationDegrees;
        private final String sampleMimeType;
        private final int selectionFlags;
        private final int width;

        public Format() {
            this(null, null, null, 0, 0, 0, 0, 0, null, null, null, 0, 0, 0.0f, 0, 0.0f, 0, 0, 262143, null);
        }

        public static /* synthetic */ Format copy$default(Format format, String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, String str4, String str5, String str6, int i6, int i7, float f, int i8, float f2, int i9, int i10, int i11, Object obj) {
            int i12;
            int i13;
            String str7 = (i11 & 1) != 0 ? format.id : str;
            String str8 = (i11 & 2) != 0 ? format.label : str2;
            String str9 = (i11 & 4) != 0 ? format.language : str3;
            int i14 = (i11 & 8) != 0 ? format.selectionFlags : i;
            int i15 = (i11 & 16) != 0 ? format.roleFlags : i2;
            int i16 = (i11 & 32) != 0 ? format.averageBitrate : i3;
            int i17 = (i11 & 64) != 0 ? format.peakBitrate : i4;
            int i18 = (i11 & 128) != 0 ? format.bitrate : i5;
            String str10 = (i11 & 256) != 0 ? format.codecs : str4;
            String str11 = (i11 & 512) != 0 ? format.containerMimeType : str5;
            String str12 = (i11 & 1024) != 0 ? format.sampleMimeType : str6;
            int i19 = (i11 & 2048) != 0 ? format.width : i6;
            int i20 = (i11 & 4096) != 0 ? format.height : i7;
            float f3 = (i11 & 8192) != 0 ? format.frameRate : f;
            String str13 = str7;
            int i21 = (i11 & 16384) != 0 ? format.rotationDegrees : i8;
            float f4 = (i11 & 32768) != 0 ? format.pixelWidthHeightRatio : f2;
            int i22 = (i11 & 65536) != 0 ? format.channelCount : i9;
            if ((i11 & 131072) != 0) {
                i13 = i22;
                i12 = format.accessibilityChannel;
            } else {
                i12 = i10;
                i13 = i22;
            }
            return format.copy(str13, str8, str9, i14, i15, i16, i17, i18, str10, str11, str12, i19, i20, f3, i21, f4, i13, i12);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getContainerMimeType() {
            return this.containerMimeType;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getSampleMimeType() {
            return this.sampleMimeType;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final int getWidth() {
            return this.width;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final int getHeight() {
            return this.height;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final float getFrameRate() {
            return this.frameRate;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final int getRotationDegrees() {
            return this.rotationDegrees;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final float getPixelWidthHeightRatio() {
            return this.pixelWidthHeightRatio;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final int getChannelCount() {
            return this.channelCount;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final int getAccessibilityChannel() {
            return this.accessibilityChannel;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getSelectionFlags() {
            return this.selectionFlags;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getRoleFlags() {
            return this.roleFlags;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getAverageBitrate() {
            return this.averageBitrate;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getPeakBitrate() {
            return this.peakBitrate;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getBitrate() {
            return this.bitrate;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getCodecs() {
            return this.codecs;
        }

        public final Format copy(String id, String label, String language, int selectionFlags, int roleFlags, int averageBitrate, int peakBitrate, int bitrate, String codecs, String containerMimeType, String sampleMimeType, int width, int height, float frameRate, int rotationDegrees, float pixelWidthHeightRatio, int channelCount, int accessibilityChannel) {
            return new Format(id, label, language, selectionFlags, roleFlags, averageBitrate, peakBitrate, bitrate, codecs, containerMimeType, sampleMimeType, width, height, frameRate, rotationDegrees, pixelWidthHeightRatio, channelCount, accessibilityChannel);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Format)) {
                return false;
            }
            Format format = (Format) other;
            return Intrinsics.areEqual(this.id, format.id) && Intrinsics.areEqual(this.label, format.label) && Intrinsics.areEqual(this.language, format.language) && this.selectionFlags == format.selectionFlags && this.roleFlags == format.roleFlags && this.averageBitrate == format.averageBitrate && this.peakBitrate == format.peakBitrate && this.bitrate == format.bitrate && Intrinsics.areEqual(this.codecs, format.codecs) && Intrinsics.areEqual(this.containerMimeType, format.containerMimeType) && Intrinsics.areEqual(this.sampleMimeType, format.sampleMimeType) && this.width == format.width && this.height == format.height && Float.compare(this.frameRate, format.frameRate) == 0 && this.rotationDegrees == format.rotationDegrees && Float.compare(this.pixelWidthHeightRatio, format.pixelWidthHeightRatio) == 0 && this.channelCount == format.channelCount && this.accessibilityChannel == format.accessibilityChannel;
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.label;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.language;
            int iHashCode3 = (((((((((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31) + this.averageBitrate) * 31) + this.peakBitrate) * 31) + this.bitrate) * 31;
            String str4 = this.codecs;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.containerMimeType;
            int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.sampleMimeType;
            return ((((((((((((((iHashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31) + this.width) * 31) + this.height) * 31) + Float.floatToIntBits(this.frameRate)) * 31) + this.rotationDegrees) * 31) + Float.floatToIntBits(this.pixelWidthHeightRatio)) * 31) + this.channelCount) * 31) + this.accessibilityChannel;
        }

        public String toString() {
            return "Format(id=" + this.id + ", label=" + this.label + ", language=" + this.language + ", selectionFlags=" + this.selectionFlags + ", roleFlags=" + this.roleFlags + ", averageBitrate=" + this.averageBitrate + ", peakBitrate=" + this.peakBitrate + ", bitrate=" + this.bitrate + ", codecs=" + this.codecs + ", containerMimeType=" + this.containerMimeType + ", sampleMimeType=" + this.sampleMimeType + ", width=" + this.width + ", height=" + this.height + ", frameRate=" + this.frameRate + ", rotationDegrees=" + this.rotationDegrees + ", pixelWidthHeightRatio=" + this.pixelWidthHeightRatio + ", channelCount=" + this.channelCount + ", accessibilityChannel=" + this.accessibilityChannel + ')';
        }

        public Format(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, String str4, String str5, String str6, int i6, int i7, float f, int i8, float f2, int i9, int i10) {
            this.id = str;
            this.label = str2;
            this.language = str3;
            this.selectionFlags = i;
            this.roleFlags = i2;
            this.averageBitrate = i3;
            this.peakBitrate = i4;
            this.bitrate = i5;
            this.codecs = str4;
            this.containerMimeType = str5;
            this.sampleMimeType = str6;
            this.width = i6;
            this.height = i7;
            this.frameRate = f;
            this.rotationDegrees = i8;
            this.pixelWidthHeightRatio = f2;
            this.channelCount = i9;
            this.accessibilityChannel = i10;
        }

        public /* synthetic */ Format(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, String str4, String str5, String str6, int i6, int i7, float f, int i8, float f2, int i9, int i10, int i11, DefaultConstructorMarker defaultConstructorMarker) {
            this((i11 & 1) != 0 ? null : str, (i11 & 2) != 0 ? null : str2, (i11 & 4) != 0 ? null : str3, (i11 & 8) != 0 ? 0 : i, (i11 & 16) != 0 ? 0 : i2, (i11 & 32) != 0 ? -1 : i3, (i11 & 64) != 0 ? -1 : i4, (i11 & 128) != 0 ? -1 : i5, (i11 & 256) != 0 ? null : str4, (i11 & 512) != 0 ? null : str5, (i11 & 1024) == 0 ? str6 : null, (i11 & 2048) != 0 ? -1 : i6, (i11 & 4096) != 0 ? -1 : i7, (i11 & 8192) != 0 ? -1.0f : f, (i11 & 16384) != 0 ? 0 : i8, (i11 & 32768) != 0 ? 1.0f : f2, (i11 & 65536) != 0 ? -1 : i9, (i11 & 131072) != 0 ? -1 : i10);
        }

        public final String getId() {
            return this.id;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final int getSelectionFlags() {
            return this.selectionFlags;
        }

        public final int getRoleFlags() {
            return this.roleFlags;
        }

        public final int getAverageBitrate() {
            return this.averageBitrate;
        }

        public final int getPeakBitrate() {
            return this.peakBitrate;
        }

        public final int getBitrate() {
            return this.bitrate;
        }

        public final String getCodecs() {
            return this.codecs;
        }

        public final String getContainerMimeType() {
            return this.containerMimeType;
        }

        public final String getSampleMimeType() {
            return this.sampleMimeType;
        }

        public final int getWidth() {
            return this.width;
        }

        public final int getHeight() {
            return this.height;
        }

        public final float getFrameRate() {
            return this.frameRate;
        }

        public final int getRotationDegrees() {
            return this.rotationDegrees;
        }

        public final float getPixelWidthHeightRatio() {
            return this.pixelWidthHeightRatio;
        }

        public final int getChannelCount() {
            return this.channelCount;
        }

        public final int getAccessibilityChannel() {
            return this.accessibilityChannel;
        }
    }

    private final Variant getVariantWithVideoGroup(ArrayList<Variant> variants, String groupId) {
        Object next;
        Iterator<T> it = variants.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(groupId, ((Variant) next).getVideoGroupId())) {
                return (Variant) next;
            }
        }
        next = null;
        return (Variant) next;
    }

    private final Variant getVariantWithAudioGroup(ArrayList<Variant> variants, String groupId) {
        Object next;
        Iterator<T> it = variants.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(groupId, ((Variant) next).getAudioGroupId())) {
                return (Variant) next;
            }
        }
        next = null;
        return (Variant) next;
    }

    private final Variant getVariantWithSubtitleGroup(ArrayList<Variant> variants, String groupId) {
        Object next;
        Iterator<T> it = variants.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(groupId, ((Variant) next).getSubtitleGroupId())) {
                return (Variant) next;
            }
        }
        next = null;
        return (Variant) next;
    }

    private final boolean isDolbyVisionFormat(String videoRange, String codecs, String supplementalCodecs, String supplementalProfiles) {
        if (!MimeTypes.INSTANCE.isDolbyVisionCodec(codecs, supplementalCodecs)) {
            return false;
        }
        if (supplementalCodecs == null) {
            return true;
        }
        if (videoRange == null || supplementalProfiles == null) {
            return false;
        }
        return (!Intrinsics.areEqual(videoRange, "PQ") || Intrinsics.areEqual(supplementalProfiles, "db1p")) && (!Intrinsics.areEqual(videoRange, "SDR") || Intrinsics.areEqual(supplementalProfiles, "db2g")) && (!Intrinsics.areEqual(videoRange, "HLG") || StringsKt.startsWith$default(supplementalProfiles, "db4", false, 2, (Object) null));
    }

    public final HlsMultivariantPlaylist parse(String baseUri, String text) {
        Intrinsics.checkNotNullParameter(baseUri, "baseUri");
        Intrinsics.checkNotNullParameter(text, "text");
        List<String> listLines = StringsKt.lines(text);
        List<String> list = listLines;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (StringsKt.startsWith$default((String) it.next(), TAG_STREAM_INF, false, 2, (Object) null)) {
                    return parseMultivariantPlaylist(listLines.iterator(), baseUri);
                }
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:119:0x04e3  */
    /* JADX WARN: Code duplicated, block: B:120:0x04ec A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:121:0x04ee  */
    /* JADX WARN: Code duplicated, block: B:54:0x01bd  */
    private final HlsMultivariantPlaylist parseMultivariantPlaylist(Iterator<String> iterator, String baseUri) throws IOException {
        String mediaMimeType;
        int i;
        String str;
        String mediaMimeType2;
        String str2;
        Format format;
        Format formatCopy$default;
        boolean z;
        String str3;
        String str4;
        int i2;
        int i3;
        Url urlResolveToUrl;
        String str5 = baseUri;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        ArrayList<Variant> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            map2 = map2;
            if (!iterator.hasNext()) {
                ArrayList arrayList10 = arrayList4;
                ArrayList arrayList11 = arrayList5;
                ArrayList arrayList12 = arrayList7;
                ArrayList arrayList13 = arrayList8;
                boolean z4 = z2;
                boolean z5 = z3;
                HashSet hashSet = new HashSet();
                ArrayList arrayList14 = new ArrayList();
                for (Object obj : arrayList) {
                    if (hashSet.add(((Variant) obj).getUrl())) {
                        arrayList14.add(obj);
                    }
                }
                ArrayList arrayList15 = arrayList14;
                int size = arrayList6.size();
                int i4 = 0;
                Format format2 = null;
                while (i4 < size) {
                    Object obj2 = arrayList6.get(i4);
                    Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
                    String str6 = (String) obj2;
                    HashMap map3 = map2;
                    String stringAttr = parseStringAttr(str6, REGEX_GROUP_ID, map3);
                    String stringAttr2 = parseStringAttr(str6, REGEX_NAME, map3);
                    int i5 = size;
                    StringBuilder sb = new StringBuilder();
                    sb.append(stringAttr);
                    ArrayList arrayList16 = arrayList15;
                    sb.append(AbstractJsonLexerKt.COLON);
                    sb.append(stringAttr2);
                    Format format3 = new Format(sb.toString(), stringAttr2, parseOptionalStringAttr(str6, REGEX_LANGUAGE, map3), parseSelectionFlags(str6), parseRoleFlags(str6, map3), 0, 0, 0, null, "application/x-mpegURL", null, 0, 0, 0.0f, 0, 0.0f, 0, 0, 261600, null);
                    String optionalStringAttr = parseOptionalStringAttr(str6, REGEX_URI, map3);
                    Url urlResolveToUrl2 = optionalStringAttr == null ? null : UrlUtil.INSTANCE.resolveToUrl(str5, optionalStringAttr);
                    String stringAttr3 = parseStringAttr(str6, REGEX_TYPE, map3);
                    switch (stringAttr3.hashCode()) {
                        case -959297733:
                            arrayList2 = arrayList2;
                            if (stringAttr3.equals(TYPE_SUBTITLES)) {
                                Variant variantWithSubtitleGroup = getVariantWithSubtitleGroup(arrayList, stringAttr);
                                if (variantWithSubtitleGroup != null) {
                                    String codecsOfType = Util.INSTANCE.getCodecsOfType(variantWithSubtitleGroup.getFormat().getCodecs(), 3);
                                    format3 = Format.copy$default(format3, null, null, null, 0, 0, 0, 0, 0, codecsOfType, null, null, 0, 0, 0.0f, 0, 0.0f, 0, 0, 261887, null);
                                    mediaMimeType = MimeTypes.INSTANCE.getMediaMimeType(codecsOfType);
                                } else {
                                    mediaMimeType = null;
                                }
                                if (mediaMimeType == null) {
                                    mediaMimeType = "text/vtt";
                                }
                                String str7 = mediaMimeType;
                                if (urlResolveToUrl2 != null) {
                                    arrayList10 = arrayList10;
                                    Boolean.valueOf(arrayList10.add(new Rendition(urlResolveToUrl2, Format.copy$default(format3, null, null, null, 0, 0, 0, 0, 0, null, null, str7, 0, 0, 0.0f, 0, 0.0f, 0, 0, 261119, null), stringAttr, stringAttr2)));
                                } else {
                                    arrayList10 = arrayList10;
                                    Unit unit = Unit.INSTANCE;
                                }
                            } else {
                                Unit unit2 = Unit.INSTANCE;
                            }
                            break;
                        case -333210994:
                            arrayList2 = arrayList2;
                            if (stringAttr3.equals(TYPE_CLOSED_CAPTIONS)) {
                                String stringAttr4 = parseStringAttr(str6, REGEX_INSTREAM_ID, map3);
                                if (StringsKt.startsWith$default(stringAttr4, "CC", false, 2, (Object) null)) {
                                    String strSubstring = stringAttr4.substring(2);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                                    i = Integer.parseInt(strSubstring);
                                    str = "application/cea-608";
                                } else {
                                    String strSubstring2 = stringAttr4.substring(7);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                                    i = Integer.parseInt(strSubstring2);
                                    str = "application/cea-708";
                                }
                                Boolean.valueOf(arrayList9.add(Format.copy$default(format3, null, null, null, 0, 0, 0, 0, 0, null, null, str, 0, 0, 0.0f, 0, 0.0f, 0, i, 130047, null)));
                                arrayList10 = arrayList10;
                            } else {
                                Unit unit3 = Unit.INSTANCE;
                            }
                            break;
                        case 62628790:
                            if (stringAttr3.equals(TYPE_AUDIO)) {
                                Variant variantWithAudioGroup = getVariantWithAudioGroup(arrayList, stringAttr);
                                if (variantWithAudioGroup != null) {
                                    String codecsOfType2 = Util.INSTANCE.getCodecsOfType(variantWithAudioGroup.getFormat().getCodecs(), 1);
                                    format3 = Format.copy$default(format3, null, null, null, 0, 0, 0, 0, 0, codecsOfType2, null, null, 0, 0, 0.0f, 0, 0.0f, 0, 0, 261887, null);
                                    mediaMimeType2 = MimeTypes.INSTANCE.getMediaMimeType(codecsOfType2);
                                } else {
                                    mediaMimeType2 = null;
                                }
                                String optionalStringAttr2 = parseOptionalStringAttr(str6, REGEX_CHANNELS, map3);
                                if (optionalStringAttr2 != null) {
                                    Format formatCopy$default2 = Format.copy$default(format3, null, null, null, 0, 0, 0, 0, 0, null, null, null, 0, 0, 0.0f, 0, 0.0f, Integer.parseInt(Util.INSTANCE.splitAtFirst(optionalStringAttr2, "/")[0]), 0, 196607, null);
                                    if (Intrinsics.areEqual("audio/eac3", mediaMimeType2) && StringsKt.endsWith$default(optionalStringAttr2, "/JOC", false, 2, (Object) null)) {
                                        format3 = Format.copy$default(formatCopy$default2, null, null, null, 0, 0, 0, 0, 0, "ec+3", null, null, 0, 0, 0.0f, 0, 0.0f, 0, 0, 261887, null);
                                        str2 = "audio/eac3-joc";
                                    } else {
                                        str2 = mediaMimeType2;
                                        format = formatCopy$default2;
                                    }
                                    formatCopy$default = Format.copy$default(format, null, null, null, 0, 0, 0, 0, 0, null, null, str2, 0, 0, 0.0f, 0, 0.0f, 0, 0, 261119, null);
                                    if (urlResolveToUrl2 != null) {
                                        arrayList3.add(new Rendition(urlResolveToUrl2, formatCopy$default, stringAttr, stringAttr2));
                                    } else if (variantWithAudioGroup != null) {
                                        format2 = formatCopy$default;
                                    }
                                    Unit unit4 = Unit.INSTANCE;
                                    arrayList10 = arrayList10;
                                } else {
                                    str2 = mediaMimeType2;
                                }
                                format = format3;
                                formatCopy$default = Format.copy$default(format, null, null, null, 0, 0, 0, 0, 0, null, null, str2, 0, 0, 0.0f, 0, 0.0f, 0, 0, 261119, null);
                                if (urlResolveToUrl2 != null) {
                                    arrayList3.add(new Rendition(urlResolveToUrl2, formatCopy$default, stringAttr, stringAttr2));
                                } else if (variantWithAudioGroup != null) {
                                    format2 = formatCopy$default;
                                }
                                Unit unit5 = Unit.INSTANCE;
                                arrayList10 = arrayList10;
                            } else {
                                arrayList2 = arrayList2;
                                Unit unit6 = Unit.INSTANCE;
                            }
                            break;
                        case 81665115:
                            if (stringAttr3.equals(TYPE_VIDEO)) {
                                Variant variantWithVideoGroup = getVariantWithVideoGroup(arrayList, stringAttr);
                                if (variantWithVideoGroup != null) {
                                    Format format4 = variantWithVideoGroup.getFormat();
                                    format3 = Format.copy$default(format3, null, null, null, 0, 0, 0, 0, 0, Util.INSTANCE.getCodecsOfType(format4.getCodecs(), 2), null, null, format4.getWidth(), format4.getHeight(), format4.getFrameRate(), 0, 0.0f, 0, 0, 247551, null);
                                }
                                Format format5 = format3;
                                if (urlResolveToUrl2 == null) {
                                    Unit unit7 = Unit.INSTANCE;
                                } else {
                                    Boolean.valueOf(arrayList2.add(new Rendition(urlResolveToUrl2, format5, stringAttr, stringAttr2)));
                                }
                                arrayList2 = arrayList2;
                                arrayList10 = arrayList10;
                            } else {
                                arrayList2 = arrayList2;
                                Unit unit8 = Unit.INSTANCE;
                            }
                            break;
                        default:
                            arrayList2 = arrayList2;
                            Unit unit9 = Unit.INSTANCE;
                            break;
                    }
                    i4++;
                    str5 = baseUri;
                    arrayList10 = arrayList10;
                    arrayList2 = arrayList2;
                    arrayList15 = arrayList16;
                    size = i5;
                }
                ArrayList arrayList17 = arrayList15;
                ArrayList arrayList18 = arrayList2;
                ArrayList arrayList19 = arrayList10;
                if (z4) {
                    arrayList9 = new ArrayList();
                }
                return new HlsMultivariantPlaylist(baseUri, arrayList13, arrayList17, arrayList18, arrayList3, arrayList19, arrayList11, format2, arrayList9, map2, arrayList12, z5);
            }
            arrayList5 = arrayList5;
            String next = iterator.next();
            boolean z6 = z2;
            arrayList4 = arrayList4;
            boolean z7 = z3;
            if (StringsKt.startsWith$default(next, TAG_PREFIX, false, 2, (Object) null)) {
                arrayList8.add(next);
            }
            boolean zStartsWith$default = StringsKt.startsWith$default(next, TAG_I_FRAME_STREAM_INF, false, 2, (Object) null);
            arrayList8 = arrayList8;
            if (StringsKt.startsWith$default(next, TAG_DEFINE, false, 2, (Object) null)) {
                HashMap map4 = map2;
                map4.put(parseStringAttr(next, REGEX_NAME, map4), parseStringAttr(next, REGEX_VALUE, map4));
            } else if (Intrinsics.areEqual(next, TAG_INDEPENDENT_SEGMENTS)) {
                z2 = z6;
                z3 = true;
            } else {
                if (StringsKt.startsWith$default(next, TAG_MEDIA, false, 2, (Object) null)) {
                    arrayList6.add(next);
                } else if (StringsKt.startsWith$default(next, TAG_SESSION_KEY, false, 2, (Object) null)) {
                    HashMap map5 = map2;
                    String optionalStringAttr3 = parseOptionalStringAttr(next, REGEX_KEYFORMAT, KEYFORMAT_IDENTITY, map5);
                    Intrinsics.checkNotNull(optionalStringAttr3);
                    SchemeData drmSchemeData = parseDrmSchemeData(next, optionalStringAttr3, map5);
                    if (drmSchemeData != null) {
                        arrayList7.add(new DrmInitData(parseEncryptionScheme(parseStringAttr(next, REGEX_METHOD, map5)), new SchemeData[]{drmSchemeData}));
                    }
                } else if (StringsKt.startsWith$default(next, TAG_STREAM_INF, false, 2, (Object) null) || zStartsWith$default) {
                    ArrayList arrayList20 = arrayList7;
                    boolean zContains$default = z6 | StringsKt.contains$default((CharSequence) next, (CharSequence) ATTR_CLOSED_CAPTIONS_NONE, false, 2, (Object) null);
                    int i6 = zStartsWith$default ? 16384 : 0;
                    int intAttr = parseIntAttr(next, REGEX_BANDWIDTH);
                    int optionalIntAttr = parseOptionalIntAttr(next, REGEX_AVERAGE_BANDWIDTH, -1);
                    HashMap map6 = map2;
                    String optionalStringAttr4 = parseOptionalStringAttr(next, REGEX_VIDEO_RANGE, map6);
                    String optionalStringAttr5 = parseOptionalStringAttr(next, REGEX_CODECS, map6);
                    String optionalStringAttr6 = parseOptionalStringAttr(next, REGEX_SUPPLEMENTAL_CODECS, map6);
                    if (optionalStringAttr6 != null) {
                        z = zStartsWith$default;
                        String[] strArrSplit = Util.INSTANCE.split(Util.INSTANCE.splitAtFirst(optionalStringAttr6, ",")[0], "/");
                        str4 = strArrSplit[0];
                        str3 = strArrSplit.length > 1 ? strArrSplit[1] : null;
                    } else {
                        z = zStartsWith$default;
                        str3 = null;
                        str4 = null;
                    }
                    String codecsOfType3 = Util.INSTANCE.getCodecsOfType(optionalStringAttr5, 2);
                    if (isDolbyVisionFormat(optionalStringAttr4, codecsOfType3, str4, str3)) {
                        if (str4 == null) {
                            str4 = codecsOfType3;
                        }
                        String codecsWithoutType = Util.INSTANCE.getCodecsWithoutType(optionalStringAttr5, 2);
                        optionalStringAttr5 = codecsWithoutType != null ? str4 + AbstractJsonLexerKt.COMMA + codecsWithoutType : str4;
                    }
                    String str8 = optionalStringAttr5;
                    String optionalStringAttr7 = parseOptionalStringAttr(next, REGEX_RESOLUTION, map6);
                    if (optionalStringAttr7 != null) {
                        String[] strArrSplit2 = Util.INSTANCE.split(optionalStringAttr7, "x");
                        int i7 = Integer.parseInt(strArrSplit2[0]);
                        int i8 = Integer.parseInt(strArrSplit2[1]);
                        if (i7 <= 0 || i8 <= 0) {
                            i2 = -1;
                            i3 = -1;
                        } else {
                            i3 = i8;
                            i2 = i7;
                        }
                    } else {
                        i2 = -1;
                        i3 = -1;
                    }
                    String optionalStringAttr8 = parseOptionalStringAttr(next, REGEX_FRAME_RATE, map6);
                    float f = optionalStringAttr8 != null ? Float.parseFloat(optionalStringAttr8) : -1.0f;
                    String optionalStringAttr9 = parseOptionalStringAttr(next, REGEX_VIDEO, map6);
                    String optionalStringAttr10 = parseOptionalStringAttr(next, REGEX_AUDIO, map6);
                    String optionalStringAttr11 = parseOptionalStringAttr(next, REGEX_SUBTITLES, map6);
                    String optionalStringAttr12 = parseOptionalStringAttr(next, REGEX_CLOSED_CAPTIONS, map6);
                    if (z) {
                        urlResolveToUrl = UrlUtil.INSTANCE.resolveToUrl(str5, parseStringAttr(next, REGEX_URI, map6));
                    } else {
                        if (!iterator.hasNext()) {
                            throw ParserException.INSTANCE.createForMalformedManifest("#EXT-X-STREAM-INF must be followed by another line", null);
                        }
                        urlResolveToUrl = UrlUtil.INSTANCE.resolveToUrl(str5, replaceVariableReferences(iterator.next(), map6));
                    }
                    Url url = urlResolveToUrl;
                    arrayList.add(new Variant(url, new Format(String.valueOf(arrayList.size()), null, null, 0, i6, optionalIntAttr, intAttr, 0, str8, "application/x-mpegURL", null, i2, i3, f, 0, 0.0f, 0, 0, 246926, null), optionalStringAttr9, optionalStringAttr10, optionalStringAttr11, optionalStringAttr12));
                    ArrayList arrayList21 = (ArrayList) map.get(url);
                    if (arrayList21 == null) {
                        arrayList21 = new ArrayList();
                        map.put(url, arrayList21);
                    }
                    arrayList21.add(new VariantInfo(optionalIntAttr, intAttr, optionalStringAttr9, optionalStringAttr10, optionalStringAttr11, optionalStringAttr12));
                    z3 = z7;
                    arrayList7 = arrayList20;
                    z2 = zContains$default;
                }
                z2 = z6;
                z3 = z7;
            }
            z2 = z6;
            z3 = z7;
        }
    }
}
