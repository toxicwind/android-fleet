package com.fleeksoft.ksoup.nodes;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.fleeksoft.ksoup.helper.Validate;
import com.fleeksoft.ksoup.internal.QuietAppendable;
import com.fleeksoft.ksoup.internal.SharedConstants;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.parser.ParseSettings;
import com.fleeksoft.ksoup.ported.KCloneable;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Attributes.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010%\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010)\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 N2\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0003:\u0002MNB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0002J\u000e\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\rJ\u0010\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\rH\u0002J\u0011\u0010 \u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\rH\u0086\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\u00022\b\u0010\u001e\u001a\u0004\u0018\u00010\rJ\u000e\u0010\"\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\rJ\u0018\u0010#\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\r2\b\u0010\u0006\u001a\u0004\u0018\u00010\rJ\u001a\u0010$\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\r2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0014H\u0002J\u0018\u0010%\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\r2\b\u0010\u0006\u001a\u0004\u0018\u00010\rJ\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00140'J\u0006\u0010(\u001a\u00020)J\u0012\u0010&\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001e\u001a\u00020\rH\u0007J\u001a\u0010&\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\r2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0014H\u0007J\u0018\u0010*\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\r2\b\u0010\u0006\u001a\u0004\u0018\u00010\rJ\u0018\u0010%\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020)H\u0007J\u0010\u0010%\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0002H\u0007J\u0010\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u0007H\u0002J\u000e\u0010+\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\rJ\u000e\u0010-\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\rJ\u000e\u0010.\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\rJ\u000e\u0010/\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\rJ\u000e\u00100\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\rJ\u000e\u00101\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\rJ\u0006\u0010\b\u001a\u00020\u0007J\u0006\u00102\u001a\u00020)J\u000e\u00103\u001a\u00020\u001b2\u0006\u00104\u001a\u00020\u0000J\u000e\u00105\u001a\u0002062\u0006\u0010\u001e\u001a\u00020\rJ\u0014\u00107\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u000206\u0018\u00010'J\u0018\u00105\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\r2\u0006\u00108\u001a\u000206H\u0007J\u0010\u00109\u001a\b\u0012\u0004\u0012\u00020\u00020:H\u0096\u0082\u0004J\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00020<J\u0006\u0010=\u001a\u00020>J\u0006\u0010?\u001a\u00020\rJ\u0018\u0010?\u001a\u00020\u001b2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0007J\n\u0010D\u001a\u00020\rH\u0096\u0080\u0004J\u0014\u0010E\u001a\u00020)2\b\u0010F\u001a\u0004\u0018\u00010\u0014H\u0096\u0082\u0004J\n\u0010G\u001a\u00020\u0007H\u0096\u0080\u0004J\b\u0010H\u001a\u00020\u0000H\u0016J\u0006\u0010I\u001a\u00020\u001bJ\u000e\u0010J\u001a\u00020\u00072\u0006\u0010K\u001a\u00020LR \u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078\u0006@BX\u0087\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\fX\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006O"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Attributes;", "", "Lcom/fleeksoft/ksoup/nodes/Attribute;", "Lcom/fleeksoft/ksoup/ported/KCloneable;", "<init>", "()V", ES6Iterator.VALUE_PROPERTY, "", ContentDisposition.Parameters.Size, "getSize", "()I", "keys", "", "", "getKeys$ksoup_release", "()[Ljava/lang/String;", "setKeys$ksoup_release", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "vals", "", "getVals$ksoup_release", "()[Ljava/lang/Object;", "setVals$ksoup_release", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", "checkCapacity", "", "minNewSize", "indexOfKey", "key", "indexOfKeyIgnoreCase", "get", "attribute", "getIgnoreCase", "add", "addObject", "put", "userData", "", "hasUserData", "", "putIgnoreCase", "remove", "index", "removeIgnoreCase", "hasKey", "hasKeyIgnoreCase", "hasDeclaredValueForKey", "hasDeclaredValueForKeyIgnoreCase", "isEmpty", "addAll", "incoming", "sourceRange", "Lcom/fleeksoft/ksoup/nodes/Range$AttributeRange;", "getRanges", "range", "iterator", "", "asList", "", "dataset", "Lcom/fleeksoft/ksoup/nodes/Attributes$Dataset;", "html", "accum", "Lcom/fleeksoft/ksoup/internal/QuietAppendable;", "out", "Lcom/fleeksoft/ksoup/nodes/Document$OutputSettings;", "toString", "equals", "other", "hashCode", "clone", "normalize", "deduplicate", "settings", "Lcom/fleeksoft/ksoup/parser/ParseSettings;", "Dataset", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class Attributes implements Iterable<Attribute>, KCloneable<Attributes>, KMappedMarker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String EmptyString = "";
    private static final int GrowthFactor = 2;
    private static final int InitialCapacity = 3;
    private static final char InternalPrefix = '/';
    public static final int NotFound = -1;
    public static final String dataPrefix = "data-";
    private int size;
    private String[] keys = new String[3];
    private Object[] vals = new Object[3];

    public final int getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: getKeys$ksoup_release, reason: from getter */
    public final String[] getKeys() {
        return this.keys;
    }

    public final void setKeys$ksoup_release(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.keys = strArr;
    }

    /* JADX INFO: renamed from: getVals$ksoup_release, reason: from getter */
    public final Object[] getVals() {
        return this.vals;
    }

    public final void setVals$ksoup_release(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<set-?>");
        this.vals = objArr;
    }

    private final void checkCapacity(int minNewSize) {
        Validate.INSTANCE.isTrue(minNewSize >= this.size);
        String[] strArr = this.keys;
        int length = strArr.length;
        if (length >= minNewSize) {
            return;
        }
        int i = length >= 3 ? this.size * 2 : 3;
        if (minNewSize <= i) {
            minNewSize = i;
        }
        Object[] objArrCopyOf = Arrays.copyOf(strArr, minNewSize);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        this.keys = (String[]) objArrCopyOf;
        Object[] objArrCopyOf2 = Arrays.copyOf(this.vals, minNewSize);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf2, "copyOf(...)");
        this.vals = objArrCopyOf2;
    }

    public final int indexOfKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (Intrinsics.areEqual(key, this.keys[i2])) {
                return i2;
            }
        }
        return -1;
    }

    private final int indexOfKeyIgnoreCase(String key) {
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (StringsKt.equals(key, this.keys[i2], true)) {
                return i2;
            }
        }
        return -1;
    }

    public final String get(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iIndexOfKey = indexOfKey(key);
        if (iIndexOfKey == -1) {
            return "";
        }
        return INSTANCE.checkNotNull(this.vals[iIndexOfKey]);
    }

    public final Attribute attribute(String key) {
        Intrinsics.checkNotNull(key);
        int iIndexOfKey = indexOfKey(key);
        if (iIndexOfKey == -1) {
            return null;
        }
        return new Attribute(key, INSTANCE.checkNotNull(this.vals[iIndexOfKey]), this);
    }

    public final String getIgnoreCase(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iIndexOfKeyIgnoreCase = indexOfKeyIgnoreCase(key);
        if (iIndexOfKeyIgnoreCase == -1) {
            return "";
        }
        return INSTANCE.checkNotNull(this.vals[iIndexOfKeyIgnoreCase]);
    }

    public final Attributes add(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        addObject(key, value);
        return this;
    }

    private final void addObject(String key, Object value) {
        checkCapacity(this.size + 1);
        String[] strArr = this.keys;
        int i = this.size;
        strArr[i] = key;
        this.vals[i] = value;
        this.size = i + 1;
    }

    public final Attributes put(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iIndexOfKey = indexOfKey(key);
        if (iIndexOfKey != -1) {
            this.vals[iIndexOfKey] = value;
            return this;
        }
        addObject(key, value);
        return this;
    }

    public final Map<String, Object> userData() {
        int iIndexOfKey = indexOfKey(SharedConstants.UserDataKey);
        if (iIndexOfKey == -1) {
            HashMap map = new HashMap();
            addObject(SharedConstants.UserDataKey, map);
            return map;
        }
        Object obj = this.vals[iIndexOfKey];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any>");
        return TypeIntrinsics.asMutableMap(obj);
    }

    public final boolean hasUserData() {
        return hasKey(SharedConstants.UserDataKey);
    }

    public final Object userData(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (hasUserData()) {
            return userData().get(key);
        }
        return null;
    }

    public final Attributes userData(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (value == null && !hasUserData()) {
            return this;
        }
        Map<String, Object> mapUserData = userData();
        if (value == null) {
            mapUserData.remove(key);
            return this;
        }
        mapUserData.put(key, value);
        return this;
    }

    public final void putIgnoreCase(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iIndexOfKeyIgnoreCase = indexOfKeyIgnoreCase(key);
        if (iIndexOfKeyIgnoreCase != -1) {
            this.vals[iIndexOfKeyIgnoreCase] = value;
            String str = this.keys[iIndexOfKeyIgnoreCase];
            if (str == null || Intrinsics.areEqual(str, key)) {
                return;
            }
            this.keys[iIndexOfKeyIgnoreCase] = key;
            return;
        }
        addObject(key, value);
    }

    public final Attributes put(String key, boolean value) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (value) {
            putIgnoreCase(key, null);
            return this;
        }
        remove(key);
        return this;
    }

    public final Attributes put(Attribute attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        put(attribute.getKey(), attribute.getValue());
        attribute.setParent(this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void remove(int index) {
        Validate.INSTANCE.isFalse(index >= this.size);
        int i = (this.size - index) - 1;
        if (i > 0) {
            String[] strArr = this.keys;
            int i2 = index + 1;
            int i3 = i + i2;
            ArraysKt.copyInto(strArr, strArr, index, i2, i3);
            Object[] objArr = this.vals;
            ArraysKt.copyInto(objArr, objArr, index, i2, i3);
        }
        int i4 = this.size - 1;
        this.size = i4;
        this.keys[i4] = null;
        this.vals[i4] = null;
    }

    public final void remove(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iIndexOfKey = indexOfKey(key);
        if (iIndexOfKey != -1) {
            remove(iIndexOfKey);
        }
    }

    public final void removeIgnoreCase(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iIndexOfKeyIgnoreCase = indexOfKeyIgnoreCase(key);
        if (iIndexOfKeyIgnoreCase != -1) {
            remove(iIndexOfKeyIgnoreCase);
        }
    }

    public final boolean hasKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return indexOfKey(key) != -1;
    }

    public final boolean hasKeyIgnoreCase(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return indexOfKeyIgnoreCase(key) != -1;
    }

    public final boolean hasDeclaredValueForKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iIndexOfKey = indexOfKey(key);
        return (iIndexOfKey == -1 || this.vals[iIndexOfKey] == null) ? false : true;
    }

    public final boolean hasDeclaredValueForKeyIgnoreCase(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        int iIndexOfKeyIgnoreCase = indexOfKeyIgnoreCase(key);
        return (iIndexOfKeyIgnoreCase == -1 || this.vals[iIndexOfKeyIgnoreCase] == null) ? false : true;
    }

    public final int size() {
        int i = this.size;
        if (i == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Companion companion = INSTANCE;
            String str = this.keys[i3];
            Intrinsics.checkNotNull(str);
            if (!companion.isInternalKey(str)) {
                i2++;
            }
        }
        return i2;
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    public final void addAll(Attributes incoming) {
        Intrinsics.checkNotNullParameter(incoming, "incoming");
        int size = incoming.size();
        if (size == 0) {
            return;
        }
        checkCapacity(this.size + size);
        boolean z = this.size != 0;
        for (Attribute attribute : incoming) {
            if (z) {
                put(attribute);
            } else {
                addObject(attribute.getKey(), attribute.getValue());
            }
        }
    }

    public final Range.AttributeRange sourceRange(String key) {
        Map<String, Range.AttributeRange> ranges;
        Range.AttributeRange attributeRange;
        Intrinsics.checkNotNullParameter(key, "key");
        return (!hasKey(key) || (ranges = getRanges()) == null || (attributeRange = ranges.get(key)) == null) ? Range.AttributeRange.INSTANCE.getUntrackedAttr() : attributeRange;
    }

    public final Map<String, Range.AttributeRange> getRanges() {
        return TypeIntrinsics.asMutableMap(userData(SharedConstants.AttrRangeKey));
    }

    public final Attributes sourceRange(String key, Range.AttributeRange range) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(range, "range");
        LinkedHashMap ranges = getRanges();
        if (ranges == null) {
            ranges = new LinkedHashMap();
            userData(SharedConstants.AttrRangeKey, ranges);
        }
        ranges.put(key, range);
        return this;
    }

    /* JADX INFO: renamed from: com.fleeksoft.ksoup.nodes.Attributes$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: Attributes.kt */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0010)\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\n\u0010\f\u001a\u00020\rH\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u0002H\u0096\u0082\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\n\u0010\u0011\u001a\u00020\u0010H\u0096\u0080\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u0012"}, d2 = {"com/fleeksoft/ksoup/nodes/Attributes$iterator$1", "", "Lcom/fleeksoft/ksoup/nodes/Attribute;", "expectedSize", "", "getExpectedSize", "()I", "setExpectedSize", "(I)V", CmcdData.OBJECT_TYPE_INIT_SEGMENT, "getI", "setI", "hasNext", "", "next", "checkModified", "", "remove", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<Attribute>, KMutableIterator {
        private int expectedSize;
        private int i;

        AnonymousClass1() {
            this.expectedSize = Attributes.this.getSize();
        }

        public final int getExpectedSize() {
            return this.expectedSize;
        }

        public final void setExpectedSize(int i) {
            this.expectedSize = i;
        }

        public final int getI() {
            return this.i;
        }

        public final void setI(int i) {
            this.i = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkModified();
            while (this.i < Attributes.this.getSize()) {
                String str = Attributes.this.getKeys()[this.i];
                if (str == null) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                if (!Attributes.INSTANCE.isInternalKey(str)) {
                    break;
                }
                this.i++;
            }
            return this.i < Attributes.this.getSize();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Attribute next() {
            checkModified();
            if (this.i >= Attributes.this.getSize()) {
                throw new NoSuchElementException();
            }
            String str = Attributes.this.getKeys()[this.i];
            Intrinsics.checkNotNull(str);
            Attribute attribute = new Attribute(str, (String) Attributes.this.getVals()[this.i], Attributes.this);
            this.i++;
            return attribute;
        }

        private final void checkModified() {
            if (Attributes.this.getSize() != this.expectedSize) {
                throw new ConcurrentModificationException("Use Iterator#remove() instead to remove attributes while iterating.");
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            Attributes attributes = Attributes.this;
            int i = this.i - 1;
            this.i = i;
            attributes.remove(i);
            this.expectedSize--;
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Attribute> iterator() {
        return new AnonymousClass1();
    }

    public final List<Attribute> asList() {
        ArrayList arrayList = new ArrayList(this.size);
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            String str = this.keys[i2];
            Intrinsics.checkNotNull(str);
            if (!INSTANCE.isInternalKey(str)) {
                arrayList.add(new Attribute(str, (String) this.vals[i2], this));
            }
        }
        return CollectionsKt.toList(arrayList);
    }

    public final Dataset dataset() {
        return new Dataset(this);
    }

    public final String html() {
        StringBuilder sbBorrowBuilder = StringUtil.INSTANCE.borrowBuilder();
        html(QuietAppendable.INSTANCE.wrap(sbBorrowBuilder), new Document.OutputSettings(null, null, false, false, 0, 0, null, 127, null));
        return StringUtil.INSTANCE.releaseBuilder(sbBorrowBuilder);
    }

    public final void html(QuietAppendable accum, Document.OutputSettings out) {
        String validKey;
        Intrinsics.checkNotNullParameter(accum, "accum");
        Intrinsics.checkNotNullParameter(out, "out");
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            String str = this.keys[i2];
            Intrinsics.checkNotNull(str);
            if (!INSTANCE.isInternalKey(str) && (validKey = Attribute.INSTANCE.getValidKey(str, out.syntax())) != null) {
                Attribute.INSTANCE.htmlNoValidate(validKey, (String) this.vals[i2], accum.append(' '), out);
            }
        }
    }

    public String toString() {
        return html();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Attributes attributes = (Attributes) other;
        int i = this.size;
        if (i != attributes.size) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            String str = this.keys[i2];
            Intrinsics.checkNotNull(str);
            int iIndexOfKey = attributes.indexOfKey(str);
            if (iIndexOfKey == -1 || !Intrinsics.areEqual(this.vals[i2], attributes.vals[iIndexOfKey])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return (((this.size * 31) + this.keys.hashCode()) * 31) + this.vals.hashCode();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fleeksoft.ksoup.ported.KCloneable
    /* JADX INFO: renamed from: clone */
    public Attributes clone2() {
        Attributes attributes = new Attributes();
        attributes.addAll(this);
        attributes.size = this.size;
        Object[] objArrCopyOf = Arrays.copyOf(this.keys, this.size);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        attributes.keys = (String[]) objArrCopyOf;
        Object[] objArrCopyOf2 = Arrays.copyOf(this.vals, this.size);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf2, "copyOf(...)");
        attributes.vals = objArrCopyOf2;
        int iIndexOfKey = indexOfKey(SharedConstants.UserDataKey);
        if (iIndexOfKey != -1) {
            Object[] objArr = this.vals;
            Object obj = this.vals[iIndexOfKey];
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String?, kotlin.Any?>");
            objArr[iIndexOfKey] = new HashMap(TypeIntrinsics.asMutableMap(obj));
        }
        return attributes;
    }

    public final void normalize() {
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            String str = this.keys[i2];
            Intrinsics.checkNotNull(str);
            if (!INSTANCE.isInternalKey(str)) {
                String[] strArr = this.keys;
                String lowerCase = str.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                strArr[i2] = lowerCase;
            }
        }
    }

    public final int deduplicate(ParseSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        int i = 0;
        if (this.size == 0) {
            return 0;
        }
        boolean preserveAttributeCase = settings.getPreserveAttributeCase();
        int i2 = this.size;
        int i3 = 0;
        while (i < i2) {
            String str = this.keys[i];
            i++;
            int i4 = i;
            while (i4 < this.size) {
                if ((preserveAttributeCase && Intrinsics.areEqual(str, this.keys[i4])) || (!preserveAttributeCase && StringsKt.equals(str, this.keys[i4], true))) {
                    i3++;
                    remove(i4);
                    i4--;
                }
                i4++;
            }
        }
        return i3;
    }

    /* JADX INFO: compiled from: Attributes.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0086\u0002J\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0086\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Attributes$Dataset;", "", "attributes", "Lcom/fleeksoft/ksoup/nodes/Attributes;", "<init>", "(Lcom/fleeksoft/ksoup/nodes/Attributes;)V", ContentDisposition.Parameters.Size, "", "getSize", "()I", "set", "", "key", ES6Iterator.VALUE_PROPERTY, "get", "remove", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Dataset {
        private final Attributes attributes;

        public Dataset(Attributes attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            this.attributes = attributes;
        }

        public final int getSize() {
            Iterable iterable = this.attributes;
            int i = 0;
            if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                return 0;
            }
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                if (((Attribute) it.next()).isDataAttribute() && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
            return i;
        }

        public final String set(String key, String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            String strDataKey = Attributes.INSTANCE.dataKey(key);
            String str = this.attributes.hasKey(strDataKey) ? this.attributes.get(strDataKey) : null;
            this.attributes.put(strDataKey, value);
            return str;
        }

        public final String get(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            String str = Attributes.dataPrefix + key;
            if (str.length() <= 5 || !this.attributes.hasKey(str)) {
                return null;
            }
            return this.attributes.get(str);
        }

        public final void remove(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.attributes.remove(Attributes.dataPrefix + key);
        }
    }

    /* JADX INFO: compiled from: Attributes.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\r\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u000e\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/fleeksoft/ksoup/nodes/Attributes$Companion;", "", "<init>", "()V", "InternalPrefix", "", "dataPrefix", "", "EmptyString", "InitialCapacity", "", "GrowthFactor", "NotFound", "checkNotNull", ES6Iterator.VALUE_PROPERTY, "dataKey", "key", "internalKey", "isInternalKey", "", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String checkNotNull(Object value) {
            return value == null ? "" : (String) value;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String dataKey(String key) {
            return Attributes.dataPrefix + key;
        }

        public final String internalKey(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return "/" + key;
        }

        public final boolean isInternalKey(String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return key.length() > 1 && key.charAt(0) == '/';
        }
    }
}
