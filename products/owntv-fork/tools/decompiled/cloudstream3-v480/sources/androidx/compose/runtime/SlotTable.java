package androidx.compose.runtime;

import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionGroup;
import androidx.compose.ui.spatial.RectListKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.C;
import io.ktor.http.LinkHeader;
import io.ktor.util.date.GMTDateParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010(\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J7\u0010;\u001a\u0002H<\"\u0004\b\u0000\u0010<2!\u0010=\u001a\u001d\u0012\u0013\u0012\u00110?¢\u0006\f\b@\u0012\b\bA\u0012\u0004\b\b(B\u0012\u0004\u0012\u0002H<0>H\u0086\b¢\u0006\u0002\u0010CJ7\u0010D\u001a\u0002H<\"\u0004\b\u0000\u0010<2!\u0010=\u001a\u001d\u0012\u0013\u0012\u00110E¢\u0006\f\b@\u0012\b\bA\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u0002H<0>H\u0086\b¢\u0006\u0002\u0010CJ\u0006\u0010F\u001a\u00020?J\u0006\u0010G\u001a\u00020EJ\u000e\u0010H\u001a\u00020%2\u0006\u0010I\u001a\u00020\u000bJ\u0012\u0010J\u001a\u0004\u0018\u00010%2\u0006\u0010I\u001a\u00020\u000bH\u0002J\u000e\u0010K\u001a\u00020\u000b2\u0006\u0010H\u001a\u00020%J\u000e\u0010L\u001a\u00020\u001b2\u0006\u0010H\u001a\u00020%J\u0016\u0010M\u001a\u00020\u001b2\u0006\u0010N\u001a\u00020%2\u0006\u0010H\u001a\u00020%J\u0016\u0010O\u001a\u00020\u001b2\u0006\u0010P\u001a\u00020\u000b2\u0006\u0010H\u001a\u00020%J=\u0010Q\u001a\u00020R2\u0006\u0010B\u001a\u00020?2&\u0010+\u001a\"\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020-\u0018\u00010,j\u0010\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020-\u0018\u0001`.H\u0000¢\u0006\u0002\bSJ\u008f\u0001\u0010Q\u001a\u00020R2\u0006\u0010\u001c\u001a\u00020E2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f2\u0006\u0010\u0015\u001a\u00020\u000b2\u0016\u0010#\u001a\u0012\u0012\u0004\u0012\u00020%0$j\b\u0012\u0004\u0012\u00020%`&2&\u0010+\u001a\"\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020-\u0018\u00010,j\u0010\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020-\u0018\u0001`.2\u000e\u00103\u001a\n\u0012\u0004\u0012\u000205\u0018\u000104H\u0000¢\u0006\u0004\bS\u0010TJ\u0087\u0001\u0010U\u001a\u00020R2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f2\u0006\u0010\u0015\u001a\u00020\u000b2\u0016\u0010#\u001a\u0012\u0012\u0004\u0012\u00020%0$j\b\u0012\u0004\u0012\u00020%`&2&\u0010+\u001a\"\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020-\u0018\u00010,j\u0010\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020-\u0018\u0001`.2\u000e\u00103\u001a\n\u0012\u0004\u0012\u000205\u0018\u000104H\u0000¢\u0006\u0004\bV\u0010WJ\u001d\u0010X\u001a\n\u0012\u0004\u0012\u00020Z\u0018\u00010Y2\u0006\u0010[\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\\J\u0006\u0010]\u001a\u00020\u001bJ\u0010\u0010^\u001a\u0004\u0018\u00010-2\u0006\u0010_\u001a\u00020\u000bJ\u0012\u0010`\u001a\u0004\u0018\u00010Z2\u0006\u0010_\u001a\u00020\u000bH\u0002J\u0006\u0010a\u001a\u00020RJ\u0006\u0010b\u001a\u00020RJ\u0006\u0010c\u001a\u00020RJ\u0006\u0010d\u001a\u00020eJ \u0010f\u001a\u00020\u000b*\u00060gj\u0002`h2\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010i\u001a\u00020\u000bH\u0002J\u000e\u0010j\u001a\b\u0012\u0004\u0012\u00020\u000b0YH\u0002J\u000e\u0010k\u001a\b\u0012\u0004\u0012\u00020\u000b0YH\u0002J\u000e\u0010l\u001a\b\u0012\u0004\u0012\u00020\u000b0YH\u0002J\u000e\u0010m\u001a\b\u0012\u0004\u0012\u00020\u000b0YH\u0002J\u000e\u0010n\u001a\b\u0012\u0004\u0012\u00020\u000b0YH\u0002J\u001d\u0010o\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100Y2\u0006\u0010_\u001a\u00020\u000bH\u0000¢\u0006\u0002\bpJ\u001f\u0010q\u001a\u0004\u0018\u00010\u00102\u0006\u0010_\u001a\u00020\u000b2\u0006\u0010r\u001a\u00020\u000bH\u0000¢\u0006\u0002\bsJ\u000f\u0010w\u001a\b\u0012\u0004\u0012\u00020\u00030xH\u0096\u0002J\u0012\u0010y\u001a\u0004\u0018\u00010\u00032\u0006\u0010z\u001a\u00020\u0010H\u0016R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR0\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f2\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u000e\u0010\u0017\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\u00060\u0010j\u0002`\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u001e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0006\u001a\u00020\u001b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u000bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000e\"\u0004\b!\u0010\"R*\u0010#\u001a\u0012\u0012\u0004\u0012\u00020%0$j\b\u0012\u0004\u0012\u00020%`&X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R:\u0010+\u001a\"\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020-\u0018\u00010,j\u0010\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020-\u0018\u0001`.X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\"\u00103\u001a\n\u0012\u0004\u0012\u000205\u0018\u000104X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u0010\u001eR\u001a\u0010t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bu\u0010v¨\u0006{"}, d2 = {"Landroidx/compose/runtime/SlotTable;", "Landroidx/compose/runtime/tooling/CompositionData;", "", "Landroidx/compose/runtime/tooling/CompositionGroup;", "<init>", "()V", ES6Iterator.VALUE_PROPERTY, "", "groups", "getGroups", "()[I", "", "groupsSize", "getGroupsSize", "()I", "", "", "slots", "getSlots", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "slotsSize", "getSlotsSize", "readers", "lock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "", "writer", "getWriter$runtime", "()Z", "version", "getVersion$runtime", "setVersion$runtime", "(I)V", "anchors", "Ljava/util/ArrayList;", "Landroidx/compose/runtime/Anchor;", "Lkotlin/collections/ArrayList;", "getAnchors$runtime", "()Ljava/util/ArrayList;", "setAnchors$runtime", "(Ljava/util/ArrayList;)V", "sourceInformationMap", "Ljava/util/HashMap;", "Landroidx/compose/runtime/GroupSourceInformation;", "Lkotlin/collections/HashMap;", "getSourceInformationMap$runtime", "()Ljava/util/HashMap;", "setSourceInformationMap$runtime", "(Ljava/util/HashMap;)V", "calledByMap", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/collection/MutableIntSet;", "getCalledByMap$runtime", "()Landroidx/collection/MutableIntObjectMap;", "setCalledByMap$runtime", "(Landroidx/collection/MutableIntObjectMap;)V", "isEmpty", "read", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function1;", "Landroidx/compose/runtime/SlotReader;", "Lkotlin/ParameterName;", "name", "reader", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "write", "Landroidx/compose/runtime/SlotWriter;", "openReader", "openWriter", LinkHeader.Parameters.Anchor, "index", "tryAnchor", "anchorIndex", "ownsAnchor", "inGroup", "groupAnchor", "groupContainsAnchor", "groupIndex", "close", "", "close$runtime", "(Landroidx/compose/runtime/SlotWriter;[II[Ljava/lang/Object;ILjava/util/ArrayList;Ljava/util/HashMap;Landroidx/collection/MutableIntObjectMap;)V", "setTo", "setTo$runtime", "([II[Ljava/lang/Object;ILjava/util/ArrayList;Ljava/util/HashMap;Landroidx/collection/MutableIntObjectMap;)V", "invalidateGroupsWithKey", "", "Landroidx/compose/runtime/RecomposeScopeImpl;", TypedValues.AttributesType.S_TARGET, "invalidateGroupsWithKey$runtime", "containsMark", "sourceInformationOf", "group", "findEffectiveRecomposeScope", "verifyWellFormed", "collectCalledByInformation", "collectSourceInformation", "toDebugString", "", "emitGroup", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "level", "keys", "nodes", "parentIndexes", "dataIndexes", "groupSizes", "slotsOf", "slotsOf$runtime", "slot", "slotIndex", "slot$runtime", "compositionGroups", "getCompositionGroups", "()Ljava/lang/Iterable;", "iterator", "", "find", "identityToFind", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SlotTable implements CompositionData, Iterable<CompositionGroup>, KMappedMarker {
    public static final int $stable = 8;
    private MutableIntObjectMap<MutableIntSet> calledByMap;
    private int groupsSize;
    private int readers;
    private int slotsSize;
    private HashMap<Anchor, GroupSourceInformation> sourceInformationMap;
    private int version;
    private boolean writer;
    private int[] groups = new int[0];
    private Object[] slots = new Object[0];
    private final Object lock = new Object();
    private ArrayList<Anchor> anchors = new ArrayList<>();

    public final int[] getGroups() {
        return this.groups;
    }

    public final int getGroupsSize() {
        return this.groupsSize;
    }

    public final Object[] getSlots() {
        return this.slots;
    }

    public final int getSlotsSize() {
        return this.slotsSize;
    }

    /* JADX INFO: renamed from: getWriter$runtime, reason: from getter */
    public final boolean getWriter() {
        return this.writer;
    }

    /* JADX INFO: renamed from: getVersion$runtime, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    public final void setVersion$runtime(int i) {
        this.version = i;
    }

    public final ArrayList<Anchor> getAnchors$runtime() {
        return this.anchors;
    }

    public final void setAnchors$runtime(ArrayList<Anchor> arrayList) {
        this.anchors = arrayList;
    }

    public final HashMap<Anchor, GroupSourceInformation> getSourceInformationMap$runtime() {
        return this.sourceInformationMap;
    }

    public final void setSourceInformationMap$runtime(HashMap<Anchor, GroupSourceInformation> map) {
        this.sourceInformationMap = map;
    }

    public final MutableIntObjectMap<MutableIntSet> getCalledByMap$runtime() {
        return this.calledByMap;
    }

    public final void setCalledByMap$runtime(MutableIntObjectMap<MutableIntSet> mutableIntObjectMap) {
        this.calledByMap = mutableIntObjectMap;
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public boolean isEmpty() {
        return this.groupsSize == 0;
    }

    public final <T> T read(Function1<? super SlotReader, ? extends T> block) {
        SlotReader slotReaderOpenReader = openReader();
        try {
            return block.invoke(slotReaderOpenReader);
        } finally {
            slotReaderOpenReader.close();
        }
    }

    public final <T> T write(Function1<? super SlotWriter, ? extends T> block) {
        SlotWriter slotWriterOpenWriter = openWriter();
        try {
            boolean z = true;
            return block.invoke(slotWriterOpenWriter);
        } finally {
            slotWriterOpenWriter.close(false);
        }
    }

    public final SlotReader openReader() {
        if (this.writer) {
            throw new IllegalStateException("Cannot read while a writer is pending".toString());
        }
        this.readers++;
        return new SlotReader(this);
    }

    public final SlotWriter openWriter() {
        if (this.writer) {
            ComposerKt.composeImmediateRuntimeError("Cannot start a writer when another writer is pending");
        }
        if (!(this.readers <= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot start a writer when a reader is pending");
        }
        this.writer = true;
        this.version++;
        return new SlotWriter(this);
    }

    public final Anchor anchor(int index) {
        if (this.writer) {
            ComposerKt.composeImmediateRuntimeError("use active SlotWriter to create an anchor location instead");
        }
        boolean z = false;
        if (index >= 0 && index < this.groupsSize) {
            z = true;
        }
        if (!z) {
            PreconditionsKt.throwIllegalArgumentException("Parameter index is out of range");
        }
        ArrayList<Anchor> arrayList = this.anchors;
        int iSearch = SlotTableKt.search(arrayList, index, this.groupsSize);
        if (iSearch < 0) {
            Anchor anchor = new Anchor(index);
            arrayList.add(-(iSearch + 1), anchor);
            return anchor;
        }
        return arrayList.get(iSearch);
    }

    private final Anchor tryAnchor(int index) {
        int i;
        if (this.writer) {
            ComposerKt.composeImmediateRuntimeError("use active SlotWriter to crate an anchor for location instead");
        }
        if (index < 0 || index >= (i = this.groupsSize)) {
            return null;
        }
        return SlotTableKt.find(this.anchors, index, i);
    }

    public final int anchorIndex(Anchor anchor) {
        if (this.writer) {
            ComposerKt.composeImmediateRuntimeError("Use active SlotWriter to determine anchor location instead");
        }
        if (!anchor.getValid()) {
            PreconditionsKt.throwIllegalArgumentException("Anchor refers to a group that was removed");
        }
        return anchor.getLocation();
    }

    public final boolean ownsAnchor(Anchor anchor) {
        int iSearch;
        return anchor.getValid() && (iSearch = SlotTableKt.search(this.anchors, anchor.getLocation(), this.groupsSize)) >= 0 && Intrinsics.areEqual(this.anchors.get(iSearch), anchor);
    }

    public final boolean inGroup(Anchor groupAnchor, Anchor anchor) {
        int location$runtime = groupAnchor.getLocation();
        int iGroupSize = SlotTableKt.groupSize(this.groups, location$runtime) + location$runtime;
        int location$runtime2 = anchor.getLocation();
        return location$runtime <= location$runtime2 && location$runtime2 < iGroupSize;
    }

    public final boolean groupContainsAnchor(int groupIndex, Anchor anchor) {
        if (this.writer) {
            ComposerKt.composeImmediateRuntimeError("Writer is active");
        }
        if (!(groupIndex >= 0 && groupIndex < this.groupsSize)) {
            ComposerKt.composeImmediateRuntimeError("Invalid group index");
        }
        if (ownsAnchor(anchor)) {
            int iGroupSize = SlotTableKt.groupSize(this.groups, groupIndex) + groupIndex;
            int location$runtime = anchor.getLocation();
            if (groupIndex <= location$runtime && location$runtime < iGroupSize) {
                return true;
            }
        }
        return false;
    }

    public final void close$runtime(SlotReader reader, HashMap<Anchor, GroupSourceInformation> sourceInformationMap) {
        if (!(reader.getTable() == this && this.readers > 0)) {
            ComposerKt.composeImmediateRuntimeError("Unexpected reader close()");
        }
        this.readers--;
        if (sourceInformationMap != null) {
            synchronized (this.lock) {
                HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
                if (map != null) {
                    map.putAll(sourceInformationMap);
                } else {
                    this.sourceInformationMap = sourceInformationMap;
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final void close$runtime(SlotWriter writer, int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<Anchor> anchors, HashMap<Anchor, GroupSourceInformation> sourceInformationMap, MutableIntObjectMap<MutableIntSet> calledByMap) {
        if (!(writer.getTable() == this && this.writer)) {
            PreconditionsKt.throwIllegalArgumentException("Unexpected writer close()");
        }
        this.writer = false;
        setTo$runtime(groups, groupsSize, slots, slotsSize, anchors, sourceInformationMap, calledByMap);
    }

    public final void setTo$runtime(int[] groups, int groupsSize, Object[] slots, int slotsSize, ArrayList<Anchor> anchors, HashMap<Anchor, GroupSourceInformation> sourceInformationMap, MutableIntObjectMap<MutableIntSet> calledByMap) {
        this.groups = groups;
        this.groupsSize = groupsSize;
        this.slots = slots;
        this.slotsSize = slotsSize;
        this.anchors = anchors;
        this.sourceInformationMap = sourceInformationMap;
        this.calledByMap = calledByMap;
    }

    public final List<RecomposeScopeImpl> invalidateGroupsWithKey$runtime(int target) {
        MutableIntSet mutableIntSet;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        MutableIntSet mutableIntSet2 = new MutableIntSet(0, 1, null);
        mutableIntSet2.add(target);
        mutableIntSet2.add(-3);
        MutableIntObjectMap<MutableIntSet> mutableIntObjectMap = this.calledByMap;
        if (mutableIntObjectMap != null && (mutableIntSet = mutableIntObjectMap.get(target)) != null) {
            mutableIntSet2.addAll(mutableIntSet);
        }
        SlotReader slotReaderOpenReader = openReader();
        try {
            invalidateGroupsWithKey$lambda$20$scanGroup(slotReaderOpenReader, mutableIntSet2, arrayList, booleanRef, this, arrayList2);
            Unit unit = Unit.INSTANCE;
            slotReaderOpenReader.close();
            SlotWriter slotWriterOpenWriter = openWriter();
            try {
                slotWriterOpenWriter.startGroup();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    Anchor anchor = (Anchor) arrayList.get(i);
                    if (anchor.toIndexFor(slotWriterOpenWriter) >= slotWriterOpenWriter.getCurrentGroup()) {
                        slotWriterOpenWriter.seek(anchor);
                        slotWriterOpenWriter.bashCurrentGroup();
                    }
                }
                slotWriterOpenWriter.skipToGroupEnd();
                slotWriterOpenWriter.endGroup();
                slotWriterOpenWriter.close(true);
                if (booleanRef.element) {
                    return arrayList2;
                }
                return null;
            } catch (Throwable th) {
                slotWriterOpenWriter.close(false);
                throw th;
            }
        } catch (Throwable th2) {
            slotReaderOpenReader.close();
            throw th2;
        }
    }

    private static final void invalidateGroupsWithKey$lambda$20$scanGroup(SlotReader slotReader, MutableIntSet mutableIntSet, List<Anchor> list, Ref.BooleanRef booleanRef, SlotTable slotTable, List<RecomposeScopeImpl> list2) {
        RecomposeScopeImpl recomposeScopeImplFindEffectiveRecomposeScope;
        int groupKey = slotReader.getGroupKey();
        if (mutableIntSet.contains(groupKey)) {
            if (groupKey != -3) {
                list.add(SlotReader.anchor$default(slotReader, 0, 1, null));
            }
            if (booleanRef.element) {
                RecomposeScopeImpl recomposeScopeImplFindEffectiveRecomposeScope2 = slotTable.findEffectiveRecomposeScope(slotReader.getCurrentGroup());
                if (recomposeScopeImplFindEffectiveRecomposeScope2 != null) {
                    list2.add(recomposeScopeImplFindEffectiveRecomposeScope2);
                    Anchor anchor = recomposeScopeImplFindEffectiveRecomposeScope2.getAnchor();
                    if (anchor != null && anchor.getLocation() == slotReader.getCurrentGroup() && (recomposeScopeImplFindEffectiveRecomposeScope = slotTable.findEffectiveRecomposeScope(slotReader.getParent())) != null) {
                        list2.add(recomposeScopeImplFindEffectiveRecomposeScope);
                    }
                } else {
                    booleanRef.element = false;
                    list2.clear();
                }
            }
            slotReader.skipGroup();
            return;
        }
        slotReader.startGroup();
        while (!slotReader.isGroupEnd()) {
            invalidateGroupsWithKey$lambda$20$scanGroup(slotReader, mutableIntSet, list, booleanRef, slotTable, list2);
        }
        slotReader.endGroup();
    }

    public final boolean containsMark() {
        return this.groupsSize > 0 && (this.groups[1] & 67108864) != 0;
    }

    public final GroupSourceInformation sourceInformationOf(int group) {
        Anchor anchorTryAnchor;
        HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
        if (map == null || (anchorTryAnchor = tryAnchor(group)) == null) {
            return null;
        }
        return map.get(anchorTryAnchor);
    }

    private final RecomposeScopeImpl findEffectiveRecomposeScope(int group) {
        int i = group;
        while (i > 0) {
            for (Object obj : new DataIterator(this, i)) {
                if (obj instanceof RecomposeScopeImpl) {
                    RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj;
                    if (recomposeScopeImpl.getUsed() && i != group) {
                        return recomposeScopeImpl;
                    }
                    recomposeScopeImpl.setForcedRecompose(true);
                }
            }
            i = this.groups[(i * 5) + 2];
        }
        return null;
    }

    public final void verifyWellFormed() {
        Ref.IntRef intRef = new Ref.IntRef();
        int i = -1;
        if (this.groupsSize > 0) {
            while (intRef.element < this.groupsSize) {
                verifyWellFormed$validateGroup(intRef, this, -1, intRef.element + SlotTableKt.groupSize(this.groups, intRef.element));
            }
            if (!(intRef.element == this.groupsSize)) {
                PreconditionsKt.throwIllegalStateException("Incomplete group at root " + intRef.element + " expected to be " + this.groupsSize);
            }
        }
        int length = this.slots.length;
        for (int i2 = this.slotsSize; i2 < length; i2++) {
            if (!(this.slots[i2] == null)) {
                PreconditionsKt.throwIllegalStateException("Non null value in the slot gap at index " + i2);
            }
        }
        ArrayList<Anchor> arrayList = this.anchors;
        int size = arrayList.size();
        int i3 = 0;
        while (i3 < size) {
            int indexFor = arrayList.get(i3).toIndexFor(this);
            if (!(indexFor >= 0 && indexFor <= this.groupsSize)) {
                PreconditionsKt.throwIllegalArgumentException("Invalid anchor, location out of bound");
            }
            if (!(i < indexFor)) {
                PreconditionsKt.throwIllegalArgumentException("Anchor is out of order");
            }
            i3++;
            i = indexFor;
        }
        HashMap<Anchor, GroupSourceInformation> map = this.sourceInformationMap;
        if (map != null) {
            for (Map.Entry<Anchor, GroupSourceInformation> entry : map.entrySet()) {
                Anchor key = entry.getKey();
                GroupSourceInformation value = entry.getValue();
                if (!key.getValid()) {
                    PreconditionsKt.throwIllegalArgumentException("Source map contains invalid anchor");
                }
                if (!ownsAnchor(key)) {
                    PreconditionsKt.throwIllegalArgumentException("Source map anchor is not owned by the slot table");
                }
                verifyWellFormed$verifySourceGroup(this, value);
            }
        }
    }

    private static final int verifyWellFormed$validateGroup(Ref.IntRef intRef, SlotTable slotTable, int i, int i2) {
        int i3 = intRef.element;
        int i4 = i3 + 1;
        intRef.element = i4;
        int i5 = i3 * 5;
        int i6 = slotTable.groups[i5 + 2];
        if (!(i6 == i)) {
            PreconditionsKt.throwIllegalStateException("Invalid parent index detected at " + i3 + ", expected parent index to be " + i + " found " + i6);
        }
        int iGroupSize = SlotTableKt.groupSize(slotTable.groups, i3) + i3;
        if (!(iGroupSize <= slotTable.groupsSize)) {
            PreconditionsKt.throwIllegalStateException("A group extends past the end of the table at " + i3);
        }
        if (!(iGroupSize <= i2)) {
            PreconditionsKt.throwIllegalStateException("A group extends past its parent group at " + i3);
        }
        int[] iArr = slotTable.groups;
        int i7 = i5 + 4;
        int i8 = iArr[i7];
        int i9 = i3 >= slotTable.groupsSize - 1 ? slotTable.slotsSize : iArr[(i4 * 5) + 4];
        if (!(i9 <= slotTable.slots.length)) {
            PreconditionsKt.throwIllegalStateException("Slots for " + i3 + " extend past the end of the slot table");
        }
        if (!(i8 <= i9)) {
            PreconditionsKt.throwIllegalStateException("Invalid data anchor at " + i3);
        }
        if (!(SlotTableKt.slotAnchor(slotTable.groups, i3) <= i9)) {
            PreconditionsKt.throwIllegalStateException("Slots start out of range at " + i3);
        }
        int i10 = i5 + 1;
        int i11 = slotTable.groups[i10];
        if (!(i9 - i8 >= (((i11 & 1073741824) != 0 ? 1 : 0) + ((536870912 & i11) != 0 ? 1 : 0)) + ((i11 & 268435456) != 0 ? 1 : 0))) {
            PreconditionsKt.throwIllegalStateException("Not enough slots added for group " + i3);
        }
        int[] iArr2 = slotTable.groups;
        boolean z = (iArr2[i10] & 1073741824) != 0;
        if (!((z && slotTable.slots[iArr2[i7]] == null) ? false : true)) {
            PreconditionsKt.throwIllegalStateException("No node recorded for a node group at " + i3);
        }
        int iVerifyWellFormed$validateGroup = 0;
        while (intRef.element < iGroupSize) {
            iVerifyWellFormed$validateGroup += verifyWellFormed$validateGroup(intRef, slotTable, i3, iGroupSize);
        }
        int[] iArr3 = slotTable.groups;
        int i12 = iArr3[i10] & RectListKt.Lower26Bits;
        int iGroupSize2 = SlotTableKt.groupSize(iArr3, i3);
        if (!(i12 == iVerifyWellFormed$validateGroup)) {
            PreconditionsKt.throwIllegalStateException("Incorrect node count detected at " + i3 + ", expected " + i12 + ", received " + iVerifyWellFormed$validateGroup);
        }
        int i13 = intRef.element - i3;
        if (!(iGroupSize2 == i13)) {
            PreconditionsKt.throwIllegalStateException("Incorrect slot count detected at " + i3 + ", expected " + iGroupSize2 + ", received " + i13);
        }
        int[] iArr4 = slotTable.groups;
        if ((iArr4[i10] & 201326592) != 0) {
            if (!(i3 <= 0 || (iArr4[(i * 5) + 1] & 67108864) != 0)) {
                PreconditionsKt.throwIllegalStateException("Expected group " + i + " to record it contains a mark because " + i3 + " does");
            }
        }
        if (z) {
            return 1;
        }
        return iVerifyWellFormed$validateGroup;
    }

    private static final void verifyWellFormed$verifySourceGroup(SlotTable slotTable, GroupSourceInformation groupSourceInformation) {
        ArrayList<Object> groups = groupSourceInformation.getGroups();
        if (groups != null) {
            ArrayList<Object> arrayList = groups;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                Object obj = arrayList.get(i);
                if (obj instanceof Anchor) {
                    Anchor anchor = (Anchor) obj;
                    if (!anchor.getValid()) {
                        PreconditionsKt.throwIllegalArgumentException("Source map contains invalid anchor");
                    }
                    if (!slotTable.ownsAnchor(anchor)) {
                        PreconditionsKt.throwIllegalArgumentException("Source map anchor is not owned by the slot table");
                    }
                } else if (obj instanceof GroupSourceInformation) {
                    verifyWellFormed$verifySourceGroup(slotTable, (GroupSourceInformation) obj);
                }
            }
        }
    }

    public final void collectCalledByInformation() {
        this.calledByMap = new MutableIntObjectMap<>(0, 1, null);
    }

    public final void collectSourceInformation() {
        this.sourceInformationMap = new HashMap<>();
    }

    public final String toDebugString() {
        if (this.writer) {
            return super.toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append('\n');
        int i = this.groupsSize;
        if (i > 0) {
            int iEmitGroup = 0;
            while (iEmitGroup < i) {
                iEmitGroup += emitGroup(sb, iEmitGroup, 0);
            }
        } else {
            sb.append("<EMPTY>");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final int emitGroup(StringBuilder sb, int i, int i2) {
        String sourceInformation;
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(' ');
        }
        sb.append("Group(");
        sb.append(i);
        sb.append(")");
        GroupSourceInformation groupSourceInformationSourceInformationOf = sourceInformationOf(i);
        if (groupSourceInformationSourceInformationOf != null && (sourceInformation = groupSourceInformationSourceInformationOf.getSourceInformation()) != null && (StringsKt.startsWith$default(sourceInformation, "C(", false, 2, (Object) null) || StringsKt.startsWith$default(sourceInformation, "CC(", false, 2, (Object) null))) {
            String str = sourceInformation;
            int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, "(", 0, false, 6, (Object) null) + 1;
            int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str, ')', 0, false, 6, (Object) null);
            sb.append(Stream.ID_UNKNOWN);
            String strSubstring = sourceInformation.substring(iIndexOf$default, iIndexOf$default2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            sb.append(strSubstring);
            sb.append("()");
        }
        sb.append(" key=");
        int i4 = i * 5;
        sb.append(this.groups[i4]);
        int iGroupSize = SlotTableKt.groupSize(this.groups, i);
        sb.append(", nodes=");
        int i5 = i4 + 1;
        sb.append(this.groups[i5] & RectListKt.Lower26Bits);
        sb.append(", size=");
        sb.append(iGroupSize);
        if ((this.groups[i5] & C.BUFFER_FLAG_FIRST_SAMPLE) != 0) {
            sb.append(", mark");
        }
        if ((this.groups[i5] & 67108864) != 0) {
            sb.append(", contains mark");
        }
        int iEmitGroup$dataIndex = emitGroup$dataIndex(this, i);
        int iEmitGroup = i + 1;
        int iEmitGroup$dataIndex2 = emitGroup$dataIndex(this, iEmitGroup);
        if (iEmitGroup$dataIndex < 0 || iEmitGroup$dataIndex > iEmitGroup$dataIndex2 || iEmitGroup$dataIndex2 > this.slotsSize) {
            sb.append(", *invalid data offsets " + iEmitGroup$dataIndex + '-' + iEmitGroup$dataIndex2 + GMTDateParser.ANY);
        } else {
            if ((this.groups[i5] & 536870912) != 0) {
                sb.append(" objectKey=" + SlotTableKt.summarize(String.valueOf(this.slots[SlotTableKt.objectKeyIndex(this.groups, i)]), 10));
            }
            if ((this.groups[i5] & 1073741824) != 0) {
                sb.append(" node=" + SlotTableKt.summarize(String.valueOf(this.slots[this.groups[i4 + 4]]), 10));
            }
            if ((this.groups[i5] & 268435456) != 0) {
                sb.append(" aux=" + SlotTableKt.summarize(String.valueOf(this.slots[SlotTableKt.auxIndex(this.groups, i)]), 10));
            }
            int iSlotAnchor = SlotTableKt.slotAnchor(this.groups, i);
            if (iSlotAnchor < iEmitGroup$dataIndex2) {
                sb.append(", slots=[");
                sb.append(iSlotAnchor);
                sb.append(": ");
                for (int i6 = iSlotAnchor; i6 < iEmitGroup$dataIndex2; i6++) {
                    if (i6 != iSlotAnchor) {
                        sb.append(", ");
                    }
                    sb.append(SlotTableKt.summarize(String.valueOf(this.slots[i6]), 10));
                }
                sb.append("]");
            }
        }
        sb.append('\n');
        int i7 = i + iGroupSize;
        while (iEmitGroup < i7) {
            iEmitGroup += emitGroup(sb, iEmitGroup, i2 + 1);
        }
        return iGroupSize;
    }

    private static final int emitGroup$dataIndex(SlotTable slotTable, int i) {
        return i >= slotTable.groupsSize ? slotTable.slotsSize : slotTable.groups[(i * 5) + 4];
    }

    private final List<Integer> keys() {
        return SlotTableKt.keys(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> nodes() {
        return SlotTableKt.nodeCounts(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> parentIndexes() {
        return SlotTableKt.parentAnchors(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> dataIndexes() {
        return SlotTableKt.dataAnchors(this.groups, this.groupsSize * 5);
    }

    private final List<Integer> groupSizes() {
        return SlotTableKt.groupSizes(this.groups, this.groupsSize * 5);
    }

    public final List<Object> slotsOf$runtime(int group) {
        int length;
        int[] iArr = this.groups;
        int i = iArr[(group * 5) + 4];
        int i2 = group + 1;
        if (i2 >= this.groupsSize) {
            length = this.slots.length;
        } else {
            length = iArr[(i2 * 5) + 4];
        }
        return ArraysKt.toList(this.slots).subList(i, length);
    }

    public final Object slot$runtime(int group, int slotIndex) {
        int length;
        int iSlotAnchor = SlotTableKt.slotAnchor(this.groups, group);
        int i = group + 1;
        if (i >= this.groupsSize) {
            length = this.slots.length;
        } else {
            length = this.groups[(i * 5) + 4];
        }
        return (slotIndex < 0 || slotIndex >= length - iSlotAnchor) ? Composer.INSTANCE.getEmpty() : this.slots[iSlotAnchor + slotIndex];
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public Iterable<CompositionGroup> getCompositionGroups() {
        return this;
    }

    @Override // java.lang.Iterable
    public Iterator<CompositionGroup> iterator() {
        return new GroupIterator(this, 0, this.groupsSize);
    }

    @Override // androidx.compose.runtime.tooling.CompositionData
    public CompositionGroup find(Object identityToFind) {
        return new SlotTableGroup(this, 0, 0, 4, null).find(identityToFind);
    }
}
