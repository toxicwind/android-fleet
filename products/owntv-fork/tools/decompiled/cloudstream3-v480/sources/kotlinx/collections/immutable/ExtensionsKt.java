package kotlinx.collections.immutable;

import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import kotlinx.collections.immutable.implementations.immutableList.UtilsKt;
import kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder;
import kotlinx.collections.immutable.implementations.immutableSet.PersistentHashSet;
import kotlinx.collections.immutable.implementations.immutableSet.PersistentHashSetBuilder;
import kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMap;
import kotlinx.collections.immutable.implementations.persistentOrderedMap.PersistentOrderedMapBuilder;
import kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSet;
import kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSetBuilder;

/* JADX INFO: compiled from: extensions.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000x\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001c\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a<\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0086\bø\u0001\u0000\u001a<\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0018\u0010\u0003\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b\u0012\u0004\u0012\u00020\u00060\u0004H\u0086\bø\u0001\u0000\u001aV\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u001e\u0010\u0003\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\f\u0012\u0004\u0012\u00020\u00060\u0004H\u0086\bø\u0001\u0000\u001a,\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\u0006\u0010\u0010\u001a\u0002H\u000fH\u0086\n¢\u0006\u0002\u0010\u0011\u001a,\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\u0006\u0010\u0010\u001a\u0002H\u000fH\u0086\n¢\u0006\u0002\u0010\u0011\u001a-\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0014H\u0086\u0002\u001a4\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\u000e\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015H\u0086\u0002¢\u0006\u0002\u0010\u0016\u001a-\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0017H\u0086\u0002\u001a-\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0014H\u0086\u0002\u001a4\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\u000e\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015H\u0086\u0002¢\u0006\u0002\u0010\u0016\u001a-\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0017H\u0086\u0002\u001a,\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00072\u0006\u0010\u0010\u001a\u0002H\u000fH\u0086\n¢\u0006\u0002\u0010\u0018\u001a,\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00072\u0006\u0010\u0010\u001a\u0002H\u000fH\u0086\n¢\u0006\u0002\u0010\u0018\u001a-\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0014H\u0086\u0002\u001a4\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015H\u0086\u0002¢\u0006\u0002\u0010\u0019\u001a-\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0017H\u0086\u0002\u001a-\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0014H\u0086\u0002\u001a4\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015H\u0086\u0002¢\u0006\u0002\u0010\u0019\u001a-\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0017H\u0086\u0002\u001a,\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00012\u0006\u0010\u0010\u001a\u0002H\u000fH\u0086\n¢\u0006\u0002\u0010\u001a\u001a,\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00012\u0006\u0010\u0010\u001a\u0002H\u000fH\u0086\n¢\u0006\u0002\u0010\u001a\u001a-\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0014H\u0086\u0002\u001a4\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00012\u000e\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015H\u0086\u0002¢\u0006\u0002\u0010\u001b\u001a-\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0017H\u0086\u0002\u001a-\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0014H\u0086\u0002\u001a4\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00012\u000e\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015H\u0086\u0002¢\u0006\u0002\u0010\u001b\u001a-\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0017H\u0086\u0002\u001a-\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0014H\u0086\u0004\u001a-\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0014H\u0086\u0004\u001aG\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001eH\u0086\n\u001aM\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u0018\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e0\u0014H\u0086\n\u001aT\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u001a\u0010\u001f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e0\u0015H\u0086\n¢\u0006\u0002\u0010 \u001aM\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u0018\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e0\u0017H\u0086\n\u001aI\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u0014\u0010!\u001a\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\"H\u0086\n\u001aF\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u0014\u0010!\u001a\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\"\u001aJ\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u0018\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e0\u0014\u001aQ\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u001a\u0010\u001f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e0\u0015¢\u0006\u0002\u0010 \u001aJ\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u0018\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e0\u0017\u001a@\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u0006\u0010$\u001a\u0002H\nH\u0086\u0002¢\u0006\u0002\u0010%\u001aA\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H\n0\u0014H\u0086\u0002\u001aH\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\u000e\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\n0\u0015H\u0086\u0002¢\u0006\u0002\u0010'\u001aA\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u0010\u0012\u0006\b\u0001\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t2\f\u0010&\u001a\b\u0012\u0004\u0012\u0002H\n0\u0017H\u0086\u0002\u001a+\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f2\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015\"\u0002H\u000f¢\u0006\u0002\u0010)\u001a\u0012\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f\u001a+\u0010*\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f2\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015\"\u0002H\u000f¢\u0006\u0002\u0010+\u001a\u0012\u0010*\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f\u001a+\u0010,\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f2\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015\"\u0002H\u000f¢\u0006\u0002\u0010+\u001a\u0012\u0010,\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f\u001aO\u0010-\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b2*\u0010\u001f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e0\u0015\"\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e¢\u0006\u0002\u0010.\u001a\u001e\u0010-\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b\u001aO\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b2*\u0010\u001f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e0\u0015\"\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e¢\u0006\u0002\u0010.\u001a\u001e\u0010/\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b\u001a-\u00100\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000f2\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015\"\u0002H\u000fH\u0007¢\u0006\u0002\u0010)\u001a\u0014\u00100\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\u0004\b\u0000\u0010\u000fH\u0007\u001a-\u00101\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f2\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015\"\u0002H\u000fH\u0007¢\u0006\u0002\u0010+\u001a\u0014\u00101\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000fH\u0007\u001a-\u00102\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0001\"\u0004\b\u0000\u0010\u000f2\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000f0\u0015\"\u0002H\u000fH\u0007¢\u0006\u0002\u0010+\u001aQ\u00103\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b2*\u0010\u001f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e0\u0015\"\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001eH\u0007¢\u0006\u0002\u0010.\u001aQ\u00104\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b2*\u0010\u001f\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001e0\u0015\"\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\u001eH\u0007¢\u0006\u0002\u0010.\u001a\u001c\u00105\u001a\b\u0012\u0004\u0012\u0002H\u000206\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0014\u001a#\u00105\u001a\b\u0012\u0004\u0012\u0002H\u000206\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0015¢\u0006\u0002\u00107\u001a\u001c\u00105\u001a\b\u0012\u0004\u0012\u0002H\u000206\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0017\u001a\u0010\u00105\u001a\b\u0012\u0004\u0012\u00020806*\u000209\u001a\u001c\u0010:\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0014\u001a#\u0010:\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0015¢\u0006\u0002\u0010)\u001a\u001c\u0010:\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0017\u001a\u0010\u0010:\u001a\b\u0012\u0004\u0012\u0002080\u0007*\u000209\u001a\u001c\u0010;\u001a\b\u0012\u0004\u0012\u0002H\u00020<\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0014\u001a#\u0010;\u001a\b\u0012\u0004\u0012\u0002H\u00020<\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0015¢\u0006\u0002\u0010=\u001a\u001c\u0010;\u001a\b\u0012\u0004\u0012\u0002H\u00020<\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0017\u001a\u0010\u0010;\u001a\b\u0012\u0004\u0012\u0002080\u0001*\u000209\u001a\u001c\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0014\u001a#\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0015¢\u0006\u0002\u0010+\u001a\u001c\u0010>\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0017\u001a\u0010\u0010>\u001a\b\u0012\u0004\u0012\u0002080\u0001*\u000209\u001a\u001c\u0010?\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0014\u001a#\u0010?\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0015¢\u0006\u0002\u0010+\u001a\u001c\u0010?\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0017\u001a\u0010\u0010?\u001a\b\u0012\u0004\u0012\u0002080\u0001*\u000209\u001a.\u0010@\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0A\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\"\u001a.\u0010B\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\"\u001a.\u0010C\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\t\"\u0004\b\u0000\u0010\n\"\u0004\b\u0001\u0010\u000b*\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u000b0\"\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006D"}, d2 = {"mutate", "Lkotlinx/collections/immutable/PersistentSet;", ExifInterface.GPS_DIRECTION_TRUE, "mutator", "Lkotlin/Function1;", "", "", "Lkotlinx/collections/immutable/PersistentList;", "", "Lkotlinx/collections/immutable/PersistentMap;", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "plus", "Lkotlinx/collections/immutable/PersistentCollection;", ExifInterface.LONGITUDE_EAST, "element", "(Lkotlinx/collections/immutable/PersistentCollection;Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentCollection;", "minus", "elements", "", "", "(Lkotlinx/collections/immutable/PersistentCollection;[Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentCollection;", "Lkotlin/sequences/Sequence;", "(Lkotlinx/collections/immutable/PersistentList;Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentList;", "(Lkotlinx/collections/immutable/PersistentList;[Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentList;", "(Lkotlinx/collections/immutable/PersistentSet;Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentSet;", "(Lkotlinx/collections/immutable/PersistentSet;[Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentSet;", "intersect", "pair", "Lkotlin/Pair;", "pairs", "(Lkotlinx/collections/immutable/PersistentMap;[Lkotlin/Pair;)Lkotlinx/collections/immutable/PersistentMap;", "map", "", "putAll", "key", "(Lkotlinx/collections/immutable/PersistentMap;Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentMap;", "keys", "(Lkotlinx/collections/immutable/PersistentMap;[Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentMap;", "persistentListOf", "([Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentList;", "persistentSetOf", "([Ljava/lang/Object;)Lkotlinx/collections/immutable/PersistentSet;", "persistentHashSetOf", "persistentMapOf", "([Lkotlin/Pair;)Lkotlinx/collections/immutable/PersistentMap;", "persistentHashMapOf", "immutableListOf", "immutableSetOf", "immutableHashSetOf", "immutableMapOf", "immutableHashMapOf", "toImmutableList", "Lkotlinx/collections/immutable/ImmutableList;", "([Ljava/lang/Object;)Lkotlinx/collections/immutable/ImmutableList;", "", "", "toPersistentList", "toImmutableSet", "Lkotlinx/collections/immutable/ImmutableSet;", "([Ljava/lang/Object;)Lkotlinx/collections/immutable/ImmutableSet;", "toPersistentSet", "toPersistentHashSet", "toImmutableMap", "Lkotlinx/collections/immutable/ImmutableMap;", "toPersistentMap", "toPersistentHashMap", "kotlinx-collections-immutable"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ExtensionsKt {
    public static final <T> PersistentSet<T> mutate(PersistentSet<? extends T> persistentSet, Function1<? super Set<T>, Unit> mutator) {
        Intrinsics.checkNotNullParameter(persistentSet, "<this>");
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        PersistentSet.Builder<? extends T> builder = persistentSet.builder();
        mutator.invoke(builder);
        return builder.build();
    }

    public static final <T> PersistentList<T> mutate(PersistentList<? extends T> persistentList, Function1<? super List<T>, Unit> mutator) {
        Intrinsics.checkNotNullParameter(persistentList, "<this>");
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        PersistentList.Builder<? extends T> builder = persistentList.builder();
        mutator.invoke(builder);
        return builder.build();
    }

    public static final <K, V> PersistentMap<K, V> mutate(PersistentMap<? extends K, ? extends V> persistentMap, Function1<? super Map<K, V>, Unit> mutator) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(mutator, "mutator");
        PersistentMap.Builder<? extends K, ? extends V> builder = persistentMap.builder();
        mutator.invoke(builder);
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> PersistentCollection<E> plus(PersistentCollection<? extends E> persistentCollection, E e) {
        Intrinsics.checkNotNullParameter(persistentCollection, "<this>");
        return persistentCollection.add((Object) e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> PersistentCollection<E> minus(PersistentCollection<? extends E> persistentCollection, E e) {
        Intrinsics.checkNotNullParameter(persistentCollection, "<this>");
        return persistentCollection.remove((Object) e);
    }

    public static final <E> PersistentCollection<E> plus(PersistentCollection<? extends E> persistentCollection, Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentCollection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof Collection) {
            return persistentCollection.addAll((Collection<? extends Object>) elements);
        }
        PersistentCollection.Builder<? extends E> builder = persistentCollection.builder();
        CollectionsKt.addAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentCollection<E> plus(PersistentCollection<? extends E> persistentCollection, E[] elements) {
        Intrinsics.checkNotNullParameter(persistentCollection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentCollection.Builder<? extends E> builder = persistentCollection.builder();
        CollectionsKt.addAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentCollection<E> plus(PersistentCollection<? extends E> persistentCollection, Sequence<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentCollection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentCollection.Builder<? extends E> builder = persistentCollection.builder();
        CollectionsKt.addAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentCollection<E> minus(PersistentCollection<? extends E> persistentCollection, Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentCollection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof Collection) {
            return persistentCollection.removeAll((Collection<? extends Object>) elements);
        }
        PersistentCollection.Builder<? extends E> builder = persistentCollection.builder();
        CollectionsKt.removeAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentCollection<E> minus(PersistentCollection<? extends E> persistentCollection, E[] elements) {
        Intrinsics.checkNotNullParameter(persistentCollection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentCollection.Builder<? extends E> builder = persistentCollection.builder();
        CollectionsKt.removeAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentCollection<E> minus(PersistentCollection<? extends E> persistentCollection, Sequence<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentCollection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentCollection.Builder<? extends E> builder = persistentCollection.builder();
        CollectionsKt.removeAll(builder, elements);
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> PersistentList<E> plus(PersistentList<? extends E> persistentList, E e) {
        Intrinsics.checkNotNullParameter(persistentList, "<this>");
        return persistentList.add((Object) e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> PersistentList<E> minus(PersistentList<? extends E> persistentList, E e) {
        Intrinsics.checkNotNullParameter(persistentList, "<this>");
        return persistentList.remove((Object) e);
    }

    public static final <E> PersistentList<E> plus(PersistentList<? extends E> persistentList, Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentList, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof Collection) {
            return persistentList.addAll((Collection<? extends Object>) elements);
        }
        PersistentList.Builder<? extends E> builder = persistentList.builder();
        CollectionsKt.addAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentList<E> minus(PersistentList<? extends E> persistentList, Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentList, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof Collection) {
            return persistentList.removeAll((Collection<? extends Object>) elements);
        }
        PersistentList.Builder<? extends E> builder = persistentList.builder();
        CollectionsKt.removeAll(builder, elements);
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> PersistentSet<E> plus(PersistentSet<? extends E> persistentSet, E e) {
        Intrinsics.checkNotNullParameter(persistentSet, "<this>");
        return persistentSet.add((Object) e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> PersistentSet<E> minus(PersistentSet<? extends E> persistentSet, E e) {
        Intrinsics.checkNotNullParameter(persistentSet, "<this>");
        return persistentSet.remove((Object) e);
    }

    public static final <E> PersistentSet<E> plus(PersistentSet<? extends E> persistentSet, Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentSet, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof Collection) {
            return persistentSet.addAll((Collection<? extends Object>) elements);
        }
        PersistentSet.Builder<? extends E> builder = persistentSet.builder();
        CollectionsKt.addAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentSet<E> minus(PersistentSet<? extends E> persistentSet, Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentSet, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof Collection) {
            return persistentSet.removeAll((Collection<? extends Object>) elements);
        }
        PersistentSet.Builder<? extends E> builder = persistentSet.builder();
        CollectionsKt.removeAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentSet<E> intersect(PersistentSet<? extends E> persistentSet, Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentSet, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        if (elements instanceof Collection) {
            return persistentSet.retainAll((Collection<? extends Object>) elements);
        }
        PersistentSet.Builder<? extends E> builder = persistentSet.builder();
        CollectionsKt.retainAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentSet<E> intersect(PersistentCollection<? extends E> persistentCollection, Iterable<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentCollection, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        return intersect(toPersistentSet(persistentCollection), (Iterable) elements);
    }

    public static final <K, V> PersistentMap<K, V> plus(PersistentMap<? extends K, ? extends V> persistentMap, Pair<? extends K, ? extends V> pair) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(pair, "pair");
        return persistentMap.put((Object) pair.getFirst(), (Object) pair.getSecond());
    }

    public static final <K, V> PersistentMap<K, V> plus(PersistentMap<? extends K, ? extends V> persistentMap, Iterable<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return putAll(persistentMap, pairs);
    }

    public static final <K, V> PersistentMap<K, V> plus(PersistentMap<? extends K, ? extends V> persistentMap, Pair<? extends K, ? extends V>[] pairs) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return putAll(persistentMap, pairs);
    }

    public static final <K, V> PersistentMap<K, V> plus(PersistentMap<? extends K, ? extends V> persistentMap, Sequence<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return putAll(persistentMap, pairs);
    }

    public static final <K, V> PersistentMap<K, V> plus(PersistentMap<? extends K, ? extends V> persistentMap, Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(map, "map");
        return putAll(persistentMap, map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> PersistentMap<K, V> putAll(PersistentMap<? extends K, ? extends V> persistentMap, Map<? extends K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(map, "map");
        return persistentMap.putAll((Map<? extends Object, ? extends Object>) map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> PersistentMap<K, V> minus(PersistentMap<? extends K, ? extends V> persistentMap, K k) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        return persistentMap.remove((Object) k);
    }

    public static final <E> PersistentList<E> persistentListOf(E... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return UtilsKt.persistentVectorOf().addAll((Collection) ArraysKt.asList(elements));
    }

    public static final <E> PersistentList<E> persistentListOf() {
        return UtilsKt.persistentVectorOf();
    }

    public static final <E> PersistentSet<E> persistentSetOf(E... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return PersistentOrderedSet.INSTANCE.emptyOf$kotlinx_collections_immutable().addAll((Collection) ArraysKt.asList(elements));
    }

    public static final <E> PersistentSet<E> persistentSetOf() {
        return PersistentOrderedSet.INSTANCE.emptyOf$kotlinx_collections_immutable();
    }

    public static final <E> PersistentSet<E> persistentHashSetOf(E... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return PersistentHashSet.INSTANCE.emptyOf$kotlinx_collections_immutable().addAll((Collection) ArraysKt.asList(elements));
    }

    public static final <E> PersistentSet<E> persistentHashSetOf() {
        return PersistentHashSet.INSTANCE.emptyOf$kotlinx_collections_immutable();
    }

    public static final <K, V> PersistentMap<K, V> persistentMapOf(Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        PersistentOrderedMap<K, V> persistentOrderedMapEmptyOf$kotlinx_collections_immutable = PersistentOrderedMap.INSTANCE.emptyOf$kotlinx_collections_immutable();
        Intrinsics.checkNotNull(persistentOrderedMapEmptyOf$kotlinx_collections_immutable, "null cannot be cast to non-null type kotlinx.collections.immutable.PersistentMap<K of kotlinx.collections.immutable.ExtensionsKt.mutate, V of kotlinx.collections.immutable.ExtensionsKt.mutate>");
        PersistentMap.Builder<K, V> builder = persistentOrderedMapEmptyOf$kotlinx_collections_immutable.builder();
        MapsKt.putAll(builder, pairs);
        return builder.build();
    }

    public static final <K, V> PersistentMap<K, V> persistentMapOf() {
        return PersistentOrderedMap.INSTANCE.emptyOf$kotlinx_collections_immutable();
    }

    public static final <K, V> PersistentMap<K, V> persistentHashMapOf(Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        PersistentHashMap<K, V> persistentHashMapEmptyOf$kotlinx_collections_immutable = PersistentHashMap.INSTANCE.emptyOf$kotlinx_collections_immutable();
        Intrinsics.checkNotNull(persistentHashMapEmptyOf$kotlinx_collections_immutable, "null cannot be cast to non-null type kotlinx.collections.immutable.PersistentMap<K of kotlinx.collections.immutable.ExtensionsKt.mutate, V of kotlinx.collections.immutable.ExtensionsKt.mutate>");
        PersistentMap.Builder<K, V> builder = persistentHashMapEmptyOf$kotlinx_collections_immutable.builder();
        MapsKt.putAll(builder, pairs);
        return builder.build();
    }

    public static final <K, V> PersistentMap<K, V> persistentHashMapOf() {
        return PersistentHashMap.INSTANCE.emptyOf$kotlinx_collections_immutable();
    }

    @Deprecated(message = "Use persistentListOf instead.", replaceWith = @ReplaceWith(expression = "persistentListOf(*elements)", imports = {}))
    public static final <E> PersistentList<E> immutableListOf(E... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return persistentListOf(Arrays.copyOf(elements, elements.length));
    }

    @Deprecated(message = "Use persistentListOf instead.", replaceWith = @ReplaceWith(expression = "persistentListOf()", imports = {}))
    public static final <E> PersistentList<E> immutableListOf() {
        return persistentListOf();
    }

    @Deprecated(message = "Use persistentSetOf instead.", replaceWith = @ReplaceWith(expression = "persistentSetOf(*elements)", imports = {}))
    public static final <E> PersistentSet<E> immutableSetOf(E... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return persistentSetOf(Arrays.copyOf(elements, elements.length));
    }

    @Deprecated(message = "Use persistentSetOf instead.", replaceWith = @ReplaceWith(expression = "persistentSetOf()", imports = {}))
    public static final <E> PersistentSet<E> immutableSetOf() {
        return persistentSetOf();
    }

    @Deprecated(message = "Use persistentHashSetOf instead.", replaceWith = @ReplaceWith(expression = "persistentHashSetOf(*elements)", imports = {}))
    public static final <E> PersistentSet<E> immutableHashSetOf(E... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return persistentHashSetOf(Arrays.copyOf(elements, elements.length));
    }

    @Deprecated(message = "Use persistentMapOf instead.", replaceWith = @ReplaceWith(expression = "persistentMapOf(*pairs)", imports = {}))
    public static final <K, V> PersistentMap<K, V> immutableMapOf(Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return persistentMapOf((Pair[]) Arrays.copyOf(pairs, pairs.length));
    }

    @Deprecated(message = "Use persistentHashMapOf instead.", replaceWith = @ReplaceWith(expression = "persistentHashMapOf(*pairs)", imports = {}))
    public static final <K, V> PersistentMap<K, V> immutableHashMapOf(Pair<? extends K, ? extends V>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        return persistentHashMapOf((Pair[]) Arrays.copyOf(pairs, pairs.length));
    }

    public static final <T> ImmutableList<T> toImmutableList(Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        ImmutableList<T> immutableList = iterable instanceof ImmutableList ? (ImmutableList) iterable : null;
        return immutableList == null ? toPersistentList(iterable) : immutableList;
    }

    public static final <T> ImmutableList<T> toImmutableList(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return toPersistentList(tArr);
    }

    public static final <T> ImmutableList<T> toImmutableList(Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return toPersistentList(sequence);
    }

    public static final ImmutableList<Character> toImmutableList(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return toPersistentList(charSequence);
    }

    public static final <T> PersistentList<T> toPersistentList(Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        PersistentList<T> persistentList = iterable instanceof PersistentList ? (PersistentList) iterable : null;
        if (persistentList != null) {
            return persistentList;
        }
        PersistentList.Builder builder = iterable instanceof PersistentList.Builder ? (PersistentList.Builder) iterable : null;
        PersistentList<T> persistentListBuild = builder != null ? builder.build() : null;
        return persistentListBuild == null ? plus(persistentListOf(), (Iterable) iterable) : persistentListBuild;
    }

    public static final <T> PersistentList<T> toPersistentList(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return plus(persistentListOf(), (Object[]) tArr);
    }

    public static final <T> PersistentList<T> toPersistentList(Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return plus(persistentListOf(), (Sequence) sequence);
    }

    public static final PersistentList<Character> toPersistentList(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        PersistentList.Builder builder = persistentListOf().builder();
        StringsKt.toCollection(charSequence, builder);
        return builder.build();
    }

    public static final <T> ImmutableSet<T> toImmutableSet(Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        ImmutableSet<T> immutableSet = iterable instanceof ImmutableSet ? (ImmutableSet) iterable : null;
        if (immutableSet != null) {
            return immutableSet;
        }
        PersistentSet.Builder builder = iterable instanceof PersistentSet.Builder ? (PersistentSet.Builder) iterable : null;
        PersistentSet persistentSetBuild = builder != null ? builder.build() : null;
        if (persistentSetBuild != null) {
            return persistentSetBuild;
        }
        return plus(persistentSetOf(), (Iterable) iterable);
    }

    public static final <T> ImmutableSet<T> toImmutableSet(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return toPersistentSet(tArr);
    }

    public static final <T> ImmutableSet<T> toImmutableSet(Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return toPersistentSet(sequence);
    }

    public static final PersistentSet<Character> toImmutableSet(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        return toPersistentSet(charSequence);
    }

    public static final <T> PersistentSet<T> toPersistentSet(Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        PersistentOrderedSet persistentOrderedSet = iterable instanceof PersistentOrderedSet ? (PersistentOrderedSet) iterable : null;
        if (persistentOrderedSet != null) {
            return persistentOrderedSet;
        }
        PersistentOrderedSetBuilder persistentOrderedSetBuilder = iterable instanceof PersistentOrderedSetBuilder ? (PersistentOrderedSetBuilder) iterable : null;
        PersistentSet<T> persistentSetBuild = persistentOrderedSetBuilder != null ? persistentOrderedSetBuilder.build() : null;
        return persistentSetBuild == null ? plus(PersistentOrderedSet.INSTANCE.emptyOf$kotlinx_collections_immutable(), (Iterable) iterable) : persistentSetBuild;
    }

    public static final <T> PersistentSet<T> toPersistentSet(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return plus(persistentSetOf(), (Object[]) tArr);
    }

    public static final <T> PersistentSet<T> toPersistentSet(Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return plus(persistentSetOf(), (Sequence) sequence);
    }

    public static final PersistentSet<Character> toPersistentSet(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        PersistentSet.Builder builder = persistentSetOf().builder();
        StringsKt.toCollection(charSequence, builder);
        return builder.build();
    }

    public static final <T> PersistentSet<T> toPersistentHashSet(Iterable<? extends T> iterable) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        PersistentHashSet persistentHashSet = iterable instanceof PersistentHashSet ? (PersistentHashSet) iterable : null;
        if (persistentHashSet != null) {
            return persistentHashSet;
        }
        PersistentHashSetBuilder persistentHashSetBuilder = iterable instanceof PersistentHashSetBuilder ? (PersistentHashSetBuilder) iterable : null;
        PersistentHashSet persistentHashSetBuild = persistentHashSetBuilder != null ? persistentHashSetBuilder.build() : null;
        if (persistentHashSetBuild != null) {
            return persistentHashSetBuild;
        }
        return plus(PersistentHashSet.INSTANCE.emptyOf$kotlinx_collections_immutable(), (Iterable) iterable);
    }

    public static final <T> PersistentSet<T> toPersistentHashSet(T[] tArr) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return plus(persistentHashSetOf(), (Object[]) tArr);
    }

    public static final <T> PersistentSet<T> toPersistentHashSet(Sequence<? extends T> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return plus(persistentHashSetOf(), (Sequence) sequence);
    }

    public static final PersistentSet<Character> toPersistentHashSet(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        PersistentSet.Builder builder = persistentHashSetOf().builder();
        StringsKt.toCollection(charSequence, builder);
        return builder.build();
    }

    public static final <K, V> ImmutableMap<K, V> toImmutableMap(Map<K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        ImmutableMap<K, V> immutableMap = map instanceof ImmutableMap ? (ImmutableMap) map : null;
        if (immutableMap != null) {
            return immutableMap;
        }
        PersistentMap.Builder builder = map instanceof PersistentMap.Builder ? (PersistentMap.Builder) map : null;
        PersistentMap<K, V> persistentMapBuild = builder != null ? builder.build() : null;
        if (persistentMapBuild != null) {
            return persistentMapBuild;
        }
        return persistentMapOf().putAll((Map) map);
    }

    public static final <K, V> PersistentMap<K, V> toPersistentMap(Map<K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        PersistentOrderedMap persistentOrderedMap = map instanceof PersistentOrderedMap ? (PersistentOrderedMap) map : null;
        if (persistentOrderedMap != null) {
            return persistentOrderedMap;
        }
        PersistentOrderedMapBuilder persistentOrderedMapBuilder = map instanceof PersistentOrderedMapBuilder ? (PersistentOrderedMapBuilder) map : null;
        PersistentMap<K, V> persistentMapBuild = persistentOrderedMapBuilder != null ? persistentOrderedMapBuilder.build() : null;
        return persistentMapBuild == null ? PersistentOrderedMap.INSTANCE.emptyOf$kotlinx_collections_immutable().putAll((Map) map) : persistentMapBuild;
    }

    public static final <K, V> PersistentMap<K, V> toPersistentHashMap(Map<K, ? extends V> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        PersistentHashMap persistentHashMap = map instanceof PersistentHashMap ? (PersistentHashMap) map : null;
        if (persistentHashMap != null) {
            return persistentHashMap;
        }
        PersistentHashMapBuilder persistentHashMapBuilder = map instanceof PersistentHashMapBuilder ? (PersistentHashMapBuilder) map : null;
        PersistentHashMap<K, V> persistentHashMapBuild = persistentHashMapBuilder != null ? persistentHashMapBuilder.build() : null;
        if (persistentHashMapBuild != null) {
            return persistentHashMapBuild;
        }
        return PersistentHashMap.INSTANCE.emptyOf$kotlinx_collections_immutable().putAll((Map) map);
    }

    public static final <E> PersistentList<E> plus(PersistentList<? extends E> persistentList, E[] elements) {
        Intrinsics.checkNotNullParameter(persistentList, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentList.Builder<? extends E> builder = persistentList.builder();
        CollectionsKt.addAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentList<E> plus(PersistentList<? extends E> persistentList, Sequence<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentList, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentList.Builder<? extends E> builder = persistentList.builder();
        CollectionsKt.addAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentList<E> minus(PersistentList<? extends E> persistentList, E[] elements) {
        Intrinsics.checkNotNullParameter(persistentList, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentList.Builder<? extends E> builder = persistentList.builder();
        CollectionsKt.removeAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentList<E> minus(PersistentList<? extends E> persistentList, Sequence<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentList, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentList.Builder<? extends E> builder = persistentList.builder();
        CollectionsKt.removeAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentSet<E> plus(PersistentSet<? extends E> persistentSet, E[] elements) {
        Intrinsics.checkNotNullParameter(persistentSet, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentSet.Builder<? extends E> builder = persistentSet.builder();
        CollectionsKt.addAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentSet<E> plus(PersistentSet<? extends E> persistentSet, Sequence<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentSet, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentSet.Builder<? extends E> builder = persistentSet.builder();
        CollectionsKt.addAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentSet<E> minus(PersistentSet<? extends E> persistentSet, E[] elements) {
        Intrinsics.checkNotNullParameter(persistentSet, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentSet.Builder<? extends E> builder = persistentSet.builder();
        CollectionsKt.removeAll(builder, elements);
        return builder.build();
    }

    public static final <E> PersistentSet<E> minus(PersistentSet<? extends E> persistentSet, Sequence<? extends E> elements) {
        Intrinsics.checkNotNullParameter(persistentSet, "<this>");
        Intrinsics.checkNotNullParameter(elements, "elements");
        PersistentSet.Builder<? extends E> builder = persistentSet.builder();
        CollectionsKt.removeAll(builder, elements);
        return builder.build();
    }

    public static final <K, V> PersistentMap<K, V> putAll(PersistentMap<? extends K, ? extends V> persistentMap, Iterable<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        PersistentMap.Builder<? extends K, ? extends V> builder = persistentMap.builder();
        MapsKt.putAll(builder, pairs);
        return builder.build();
    }

    public static final <K, V> PersistentMap<K, V> putAll(PersistentMap<? extends K, ? extends V> persistentMap, Pair<? extends K, ? extends V>[] pairs) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        PersistentMap.Builder<? extends K, ? extends V> builder = persistentMap.builder();
        MapsKt.putAll(builder, pairs);
        return builder.build();
    }

    public static final <K, V> PersistentMap<K, V> putAll(PersistentMap<? extends K, ? extends V> persistentMap, Sequence<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        PersistentMap.Builder<? extends K, ? extends V> builder = persistentMap.builder();
        MapsKt.putAll(builder, pairs);
        return builder.build();
    }

    public static final <K, V> PersistentMap<K, V> minus(PersistentMap<? extends K, ? extends V> persistentMap, Iterable<? extends K> keys) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(keys, "keys");
        PersistentMap.Builder<? extends K, ? extends V> builder = persistentMap.builder();
        CollectionsKt.removeAll(builder.keySet(), keys);
        return builder.build();
    }

    public static final <K, V> PersistentMap<K, V> minus(PersistentMap<? extends K, ? extends V> persistentMap, K[] keys) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(keys, "keys");
        PersistentMap.Builder<? extends K, ? extends V> builder = persistentMap.builder();
        CollectionsKt.removeAll(builder.keySet(), keys);
        return builder.build();
    }

    public static final <K, V> PersistentMap<K, V> minus(PersistentMap<? extends K, ? extends V> persistentMap, Sequence<? extends K> keys) {
        Intrinsics.checkNotNullParameter(persistentMap, "<this>");
        Intrinsics.checkNotNullParameter(keys, "keys");
        PersistentMap.Builder<? extends K, ? extends V> builder = persistentMap.builder();
        CollectionsKt.removeAll(builder.keySet(), keys);
        return builder.build();
    }
}
