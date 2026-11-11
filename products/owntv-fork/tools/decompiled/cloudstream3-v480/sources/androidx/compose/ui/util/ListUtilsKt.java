package androidx.compose.ui.util;

import androidx.collection.MutableScatterSet;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.ContentType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ListUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\b\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0001\n\u0002\b\u0003\u001a8\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001a8\u0010\u0006\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001a>\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001a8\u0010\n\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u000b0\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001a8\u0010\r\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u000b0\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001a?\u0010\u000e\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u000b0\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0002\u0010\u000f\u001a8\u0010\u0010\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t0\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001aD\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00130\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001aO\u0010\u0015\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00130\u0016*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00130\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0002\u0010\u000f\u001a]\u0010\u0017\u001a\u0002H\u0018\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0013\"\u0010\b\u0002\u0010\u0018*\n\u0012\u0006\b\u0000\u0012\u0002H\u00130\u0019*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u001a\u001a\u0002H\u00182\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00130\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0002\u0010\u001b\u001a?\u0010\u001c\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u000b0\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0002\u0010\u000f\u001a>\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u000b0\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001a`\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u000b0\u00052\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00130\u0005H\u0086\b\u0082\u0002\u0010\n\u0006\b\u0001\u0012\u0002\u0010\u0001\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u001a`\u0010\u001f\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010 \u001a\u0002H\u00132'\u0010!\u001a#\u0012\u0013\u0012\u0011H\u0013¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00130\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0002\u0010%\u001aY\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00020\u00032'\u0010\u0014\u001a#\u0012\u0013\u0012\u00110\t¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b('\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00130\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001a[\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00020\u00032)\u0010\u0014\u001a%\u0012\u0013\u0012\u00110\t¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b('\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00130\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001aO\u0010)\u001a\u0004\u0018\u0001H\u0013\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00130\u0016*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00130\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0002\u0010*\u001aU\u0010+\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00130\u0016*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010,\u001a\u0002H\u00132\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00130\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0002\u0010-\u001aJ\u0010.\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00130\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001a\\\u0010/\u001a\u0002H0\"\u0004\b\u0000\u00100\"\b\b\u0001\u0010\u0002*\u0002H0*\b\u0012\u0004\u0012\u0002H\u00020\u00032'\u0010!\u001a#\u0012\u0013\u0012\u0011H0¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H00\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0002\u00101\u001a|\u00102\u001a\b\u0012\u0004\u0012\u0002H30\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0013\"\u0004\b\u0002\u00103*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u00104\u001a\b\u0012\u0004\u0012\u0002H\u00130\u000326\u0010\u0014\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(5\u0012\u0013\u0012\u0011H\u0013¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(6\u0012\u0004\u0012\u0002H30\bH\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u001aF\u00107\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00130\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001a`\u00108\u001a\u000209\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010:\u001a\u00020;2\b\b\u0002\u0010<\u001a\u00020;2\b\b\u0002\u0010=\u001a\u00020;2\b\b\u0002\u0010>\u001a\u00020\t2\b\b\u0002\u0010?\u001a\u00020;2\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020;\u0018\u00010\u0005\u001aD\u0010@\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010A*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002HA0\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001aO\u0010B\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00130\u0016*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00130\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0002\u0010\u000f\u001aJ\u0010C\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130D0\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u001a\"\u0010E\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\b\b\u0000\u0010\u0002*\u00020F*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0003\u001a=\u0010G\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u000b0\u0005H\u0086\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001¢\u0006\u0002\u0010\u000f\u001a}\u0010H\u001a\u0002HI\"\u0004\b\u0000\u0010\u0002\"\f\b\u0001\u0010I*\u00060Jj\u0002`K*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010L\u001a\u0002HI2\b\b\u0002\u0010:\u001a\u00020;2\b\b\u0002\u0010<\u001a\u00020;2\b\b\u0002\u0010=\u001a\u00020;2\b\b\u0002\u0010>\u001a\u00020\t2\b\b\u0002\u0010?\u001a\u00020;2\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020;\u0018\u00010\u0005H\u0002¢\u0006\u0002\u0010M\u001a9\u0010N\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00060Jj\u0002`K2\u0006\u0010O\u001a\u0002H\u00022\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020;\u0018\u00010\u0005H\u0002¢\u0006\u0002\u0010P\u001a\u0010\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u000209H\u0001\u001a\u0010\u0010T\u001a\u00020\u00012\u0006\u0010S\u001a\u000209H\u0001¨\u0006U"}, d2 = {"fastForEach", "", ExifInterface.GPS_DIRECTION_TRUE, "", "action", "Lkotlin/Function1;", "fastForEachReversed", "fastForEachIndexed", "Lkotlin/Function2;", "", "fastAll", "", "predicate", "fastAny", "fastFirstOrNull", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "fastSumBy", "selector", "fastMap", "R", "transform", "fastMaxBy", "", "fastMapTo", "C", "", "destination", "(Ljava/util/List;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "fastLastOrNull", "fastFilter", "fastFilteredMap", "fastFold", "initial", "operation", "Lkotlin/ParameterName;", "name", "acc", "(Ljava/util/List;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "fastMapIndexed", "index", "fastMapIndexedNotNull", "fastMaxOfOrNull", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)Ljava/lang/Comparable;", "fastMaxOfOrDefault", "defaultValue", "(Ljava/util/List;Ljava/lang/Comparable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Comparable;", "fastZipWithNext", "fastReduce", ExifInterface.LATITUDE_SOUTH, "(Ljava/util/List;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "fastZip", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "other", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "fastMapNotNull", "fastJoinToString", "", "separator", "", "prefix", "postfix", "limit", "truncated", "fastDistinctBy", "K", "fastMinByOrNull", "fastFlatMap", "", "fastFilterNotNull", "", "fastFirst", "fastJoinTo", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "buffer", "(Ljava/util/List;Ljava/lang/Appendable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Appendable;", "appendElement", "element", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "throwNoSuchElementException", "", ContentType.Message.TYPE, "throwUnsupportedOperationException", "ui-util"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ListUtilsKt {
    public static final <T> void fastForEach(List<? extends T> list, Function1<? super T, Unit> function1) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            function1.invoke(list.get(i));
        }
    }

    public static final <T> void fastForEachReversed(List<? extends T> list, Function1<? super T, Unit> function1) {
        int size = list.size() - 1;
        if (size < 0) {
            return;
        }
        while (true) {
            int i = size - 1;
            function1.invoke(list.get(size));
            if (i < 0) {
                return;
            } else {
                size = i;
            }
        }
    }

    public static final <T> void fastForEachIndexed(List<? extends T> list, Function2<? super Integer, ? super T, Unit> function2) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            function2.invoke(Integer.valueOf(i), list.get(i));
        }
    }

    public static final <T, R> List<R> fastMap(List<? extends T> list, Function1<? super T, ? extends R> function1) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(function1.invoke(list.get(i)));
        }
        return arrayList;
    }

    public static final <T, R extends Comparable<? super R>> T fastMaxBy(List<? extends T> list, Function1<? super T, ? extends R> function1) {
        if (list.isEmpty()) {
            return null;
        }
        T t = list.get(0);
        R rInvoke = function1.invoke(t);
        int lastIndex = CollectionsKt.getLastIndex(list);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                T t2 = list.get(i);
                R rInvoke2 = function1.invoke(t2);
                if (rInvoke.compareTo(rInvoke2) < 0) {
                    t = t2;
                    rInvoke = rInvoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return (T) t;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.Object] */
    public static final <T> T fastLastOrNull(List<? extends T> list, Function1<? super T, Boolean> function1) {
        int size = list.size() - 1;
        if (size < 0) {
            return null;
        }
        while (true) {
            int i = size - 1;
            T t = list.get(size);
            if (function1.invoke(t).booleanValue()) {
                return t;
            }
            if (i < 0) {
                return null;
            }
            size = i;
        }
    }

    public static final <T> List<T> fastFilter(List<? extends T> list, Function1<? super T, Boolean> function1) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            T t = list.get(i);
            if (function1.invoke(t).booleanValue()) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static final <T, R> List<R> fastFilteredMap(List<? extends T> list, Function1<? super T, Boolean> function1, Function1<? super T, ? extends R> function2) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            T t = list.get(i);
            if (function1.invoke(t).booleanValue()) {
                arrayList.add(function2.invoke(t));
            }
        }
        return arrayList;
    }

    public static final <T, R> List<R> fastMapIndexed(List<? extends T> list, Function2<? super Integer, ? super T, ? extends R> function2) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(function2.invoke(Integer.valueOf(i), list.get(i)));
        }
        return arrayList;
    }

    public static final <T, R> List<R> fastMapIndexedNotNull(List<? extends T> list, Function2<? super Integer, ? super T, ? extends R> function2) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            R rInvoke = function2.invoke(Integer.valueOf(i), list.get(i));
            if (rInvoke != null) {
                arrayList.add(rInvoke);
            }
        }
        return arrayList;
    }

    public static final <T, R extends Comparable<? super R>> R fastMaxOfOrNull(List<? extends T> list, Function1<? super T, ? extends R> function1) {
        if (list.isEmpty()) {
            return null;
        }
        R rInvoke = function1.invoke(list.get(0));
        int lastIndex = CollectionsKt.getLastIndex(list);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                R rInvoke2 = function1.invoke(list.get(i));
                if (rInvoke2.compareTo(rInvoke) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    public static final <T, R extends Comparable<? super R>> R fastMaxOfOrDefault(List<? extends T> list, R r, Function1<? super T, ? extends R> function1) {
        if (list.isEmpty()) {
            return r;
        }
        R rInvoke = function1.invoke(list.get(0));
        int lastIndex = CollectionsKt.getLastIndex(list);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                R rInvoke2 = function1.invoke(list.get(i));
                if (rInvoke2.compareTo(rInvoke) > 0) {
                    rInvoke = rInvoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return rInvoke;
    }

    public static final <T, R> List<R> fastZipWithNext(List<? extends T> list, Function2<? super T, ? super T, ? extends R> function2) {
        if (list.size() <= 1) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        android.R.animator animatorVar = list.get(0);
        int lastIndex = CollectionsKt.getLastIndex(list);
        while (i < lastIndex) {
            i++;
            T t = list.get(i);
            arrayList.add(function2.invoke(animatorVar, t));
            animatorVar = t;
        }
        return arrayList;
    }

    public static final <S, T extends S> S fastReduce(List<? extends T> list, Function2<? super S, ? super T, ? extends S> function2) {
        if (list.isEmpty()) {
            throwUnsupportedOperationException("Empty collection can't be reduced.");
        }
        S sInvoke = (Object) CollectionsKt.first((List) list);
        int lastIndex = CollectionsKt.getLastIndex(list);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                sInvoke = function2.invoke(sInvoke, list.get(i));
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return sInvoke;
    }

    public static final <T, R, V> List<V> fastZip(List<? extends T> list, List<? extends R> list2, Function2<? super T, ? super R, ? extends V> function2) {
        int iMin = Math.min(list.size(), list2.size());
        ArrayList arrayList = new ArrayList(iMin);
        for (int i = 0; i < iMin; i++) {
            arrayList.add(function2.invoke(list.get(i), list2.get(i)));
        }
        return arrayList;
    }

    public static final <T, R> List<R> fastMapNotNull(List<? extends T> list, Function1<? super T, ? extends R> function1) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            R rInvoke = function1.invoke(list.get(i));
            if (rInvoke != null) {
                arrayList.add(rInvoke);
            }
        }
        return arrayList;
    }

    public static /* synthetic */ String fastJoinToString$default(List list, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
        }
        if ((i2 & 2) != 0) {
        }
        if ((i2 & 4) != 0) {
        }
        if ((i2 & 8) != 0) {
            i = -1;
        }
        if ((i2 & 16) != 0) {
        }
        if ((i2 & 32) != 0) {
            function1 = null;
        }
        CharSequence charSequence5 = charSequence4;
        Function1 function2 = function1;
        return fastJoinToString(list, charSequence, charSequence2, charSequence3, i, charSequence5, function2);
    }

    public static final <T> String fastJoinToString(List<? extends T> list, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1<? super T, ? extends CharSequence> function1) {
        return ((StringBuilder) fastJoinTo(list, new StringBuilder(), charSequence, charSequence2, charSequence3, i, charSequence4, function1)).toString();
    }

    public static final <T, K> List<T> fastDistinctBy(List<? extends T> list, Function1<? super T, ? extends K> function1) {
        MutableScatterSet mutableScatterSet = new MutableScatterSet(list.size());
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            T t = list.get(i);
            if (mutableScatterSet.add(function1.invoke(t))) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static final <T, R extends Comparable<? super R>> T fastMinByOrNull(List<? extends T> list, Function1<? super T, ? extends R> function1) {
        if (list.isEmpty()) {
            return null;
        }
        T t = list.get(0);
        R rInvoke = function1.invoke(t);
        int lastIndex = CollectionsKt.getLastIndex(list);
        int i = 1;
        if (1 <= lastIndex) {
            while (true) {
                T t2 = list.get(i);
                R rInvoke2 = function1.invoke(t2);
                if (rInvoke.compareTo(rInvoke2) > 0) {
                    t = t2;
                    rInvoke = rInvoke2;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return (T) t;
    }

    public static final <T, R> List<R> fastFlatMap(List<? extends T> list, Function1<? super T, ? extends Iterable<? extends R>> function1) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            CollectionsKt.addAll(arrayList, function1.invoke(list.get(i)));
        }
        return arrayList;
    }

    public static final <T> List<T> fastFilterNotNull(List<? extends T> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            T t = list.get(i);
            if (t != null) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T, A extends Appendable> A fastJoinTo(List<? extends T> list, A a, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, Function1<? super T, ? extends CharSequence> function1) throws IOException {
        a.append(charSequence2);
        int size = list.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            T t = list.get(i3);
            i2++;
            if (i2 > 1) {
                a.append(charSequence);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            appendElement(a, t, function1);
        }
        if (i >= 0 && i2 > i) {
            a.append(charSequence4);
        }
        a.append(charSequence3);
        return a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> void appendElement(Appendable appendable, T t, Function1<? super T, ? extends CharSequence> function1) throws IOException {
        if (function1 != null) {
            appendable.append(function1.invoke(t));
            return;
        }
        if (t == 0 ? true : t instanceof CharSequence) {
            appendable.append((CharSequence) t);
        } else if (t instanceof Character) {
            appendable.append(((Character) t).charValue());
        } else {
            appendable.append(t.toString());
        }
    }

    public static final Void throwNoSuchElementException(String str) {
        throw new NoSuchElementException(str);
    }

    public static final void throwUnsupportedOperationException(String str) {
        throw new UnsupportedOperationException(str);
    }

    public static final <T> boolean fastAll(List<? extends T> list, Function1<? super T, Boolean> function1) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!function1.invoke(list.get(i)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <T> boolean fastAny(List<? extends T> list, Function1<? super T, Boolean> function1) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (function1.invoke(list.get(i)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.lang.Object] */
    public static final <T> T fastFirstOrNull(List<? extends T> list, Function1<? super T, Boolean> function1) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            T t = list.get(i);
            if (function1.invoke(t).booleanValue()) {
                return t;
            }
        }
        return null;
    }

    public static final <T> int fastSumBy(List<? extends T> list, Function1<? super T, Integer> function1) {
        int size = list.size();
        int iIntValue = 0;
        for (int i = 0; i < size; i++) {
            iIntValue += function1.invoke(list.get(i)).intValue();
        }
        return iIntValue;
    }

    public static final <T, R, C extends Collection<? super R>> C fastMapTo(List<? extends T> list, C c, Function1<? super T, ? extends R> function1) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            c.add(function1.invoke(list.get(i)));
        }
        return c;
    }

    public static final <T, R> R fastFold(List<? extends T> list, R r, Function2<? super R, ? super T, ? extends R> function2) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            r = function2.invoke(r, list.get(i));
        }
        return r;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.lang.Object] */
    public static final <T> T fastFirst(List<? extends T> list, Function1<? super T, Boolean> function1) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            T t = list.get(i);
            if (function1.invoke(t).booleanValue()) {
                return t;
            }
        }
        throwNoSuchElementException("Collection contains no element matching the predicate.");
        throw new KotlinNothingValueException();
    }
}
