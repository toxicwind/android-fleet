package com.fleeksoft.ksoup.parser;

import com.fleeksoft.ksoup.exception.UncheckedIOException;
import com.fleeksoft.ksoup.internal.SoftPool;
import com.fleeksoft.ksoup.internal.StringUtil;
import com.fleeksoft.ksoup.ported.StringKt;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: CharacterReader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b!\u0018\u0000 [2\u00060\u0001j\u0002`\u0002:\u0001[B\u0015\b\u0016\u0012\n\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\u0006\u0010\u001c\u001a\u00020\u0017J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\b\u0010 \u001a\u00020\u001eH\u0002J\u0006\u0010!\u001a\u00020\u001eJ\u0006\u0010\"\u001a\u00020\u001eJ\u0006\u0010#\u001a\u00020\u001eJ\u0006\u0010$\u001a\u00020\u0011J\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u0017J\u0006\u0010'\u001a\u00020\u0017J\u0006\u0010(\u001a\u00020\u0011J\u000e\u0010(\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u0011J\u0006\u0010)\u001a\u00020\u0011J\u000e\u0010)\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u0011J\u0006\u0010*\u001a\u00020\tJ\u0010\u0010+\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u0011H\u0002J\b\u0010,\u001a\u00020\u001eH\u0002J\u0006\u0010-\u001a\u00020\u0017J\b\u0010.\u001a\u00020\u0017H\u0002J\u0006\u0010/\u001a\u000200J\u0006\u00101\u001a\u000200J\u0006\u00102\u001a\u00020\u001eJ\u0006\u00103\u001a\u00020\u001eJ\u000e\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u000200J\u000e\u00104\u001a\u00020\u00112\u0006\u00106\u001a\u000207J\u000e\u00108\u001a\u00020\t2\u0006\u00105\u001a\u000200J\u000e\u00108\u001a\u00020\t2\u0006\u00106\u001a\u00020\tJ\u001a\u00109\u001a\u00020\t2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u00170;J\"\u00109\u001a\u00020\t2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u00170;2\u0006\u0010<\u001a\u00020\u0011J\u0012\u0010=\u001a\u00020\t2\n\u0010>\u001a\u00020\u000f\"\u000200J\u0012\u0010?\u001a\u00020\u0011*\u00020\u000f2\u0006\u00105\u001a\u000200J\u0012\u0010@\u001a\u00020\t2\n\u0010>\u001a\u00020\u000f\"\u000200J\u0006\u0010A\u001a\u00020\tJ\u000e\u0010B\u001a\u00020\t2\u0006\u0010C\u001a\u00020\u0017J\u0006\u0010D\u001a\u00020\tJ\u0006\u0010E\u001a\u00020\tJ\u0006\u0010F\u001a\u00020\tJ\u0006\u0010G\u001a\u00020\tJ\u0006\u0010H\u001a\u00020\tJ\u0006\u0010I\u001a\u00020\tJ\u0006\u0010J\u001a\u00020\tJ\u000e\u0010K\u001a\u00020\u00172\u0006\u00105\u001a\u000200J\u000e\u0010K\u001a\u00020\u00172\u0006\u00106\u001a\u00020\tJ\u000e\u0010L\u001a\u00020\u00172\u0006\u00106\u001a\u00020\tJ\u0012\u0010M\u001a\u00020\u00172\n\u00106\u001a\u00020\u000f\"\u000200J\u000e\u0010N\u001a\u00020\u00172\u0006\u00106\u001a\u00020\u000fJ\u0006\u0010O\u001a\u00020\u0017J\u0006\u0010P\u001a\u00020\u0017J\u000e\u0010Q\u001a\u00020\u00172\u0006\u00106\u001a\u00020\tJ\u000e\u0010R\u001a\u00020\u00172\u0006\u00106\u001a\u00020\tJ\u000e\u0010U\u001a\u00020\u00172\u0006\u00106\u001a\u00020\tJ\n\u0010V\u001a\u00020\tH\u0096\u0080\u0004J\u001e\u0010W\u001a\u00020\u00172\u0006\u0010X\u001a\u00020\u00112\u0006\u0010Y\u001a\u00020\u00112\u0006\u0010Z\u001a\u00020\tR\u001a\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0016\u0010\u0003\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0011\u0018\u0001`\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010S\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\\"}, d2 = {"Lcom/fleeksoft/ksoup/parser/CharacterReader;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "reader", "Ljava/io/Reader;", "Lcom/fleeksoft/io/Reader;", "<init>", "(Ljava/io/Reader;)V", "html", "", "(Ljava/lang/String;)V", "stringCache", "", "[Ljava/lang/String;", "charBuf", "", "bufPos", "", "bufLength", "fillPoint", "consumed", "bufMark", "readFully", "", "newlinePositions", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "lineNumberOffset", "isClosed", "close", "", "bufferUp", "doBufferUp", "mark", "unmark", "rewindToMark", CloudStreamPackage.POSITION_EXTRA, "trackNewlines", "track", "isTrackNewlines", "lineNumber", "columnNumber", "posLineCol", "lineNumIndex", "scanBufferForNewlines", "isEmpty", "isEmptyNoBufferUp", "current", "", "consume", "unconsume", "advance", "nextIndexOf", "c", "seq", "", "consumeTo", "consumeMatching", "func", "Lkotlin/Function1;", "maxLength", "consumeToAny", "chars", "binarySearch", "consumeToAnySorted", "consumeData", "consumeAttributeQuoted", "single", "consumeRawData", "consumeTagName", "consumeToEnd", "consumeLetterSequence", "consumeLetterThenDigitSequence", "consumeHexSequence", "consumeDigitSequence", "matches", "matchesIgnoreCase", "matchesAny", "matchesAnySorted", "matchesAsciiAlpha", "matchesDigit", "matchConsume", "matchConsumeIgnoreCase", "lastIcSeq", "lastIcIndex", "containsIgnoreCase", "toString", "rangeEquals", "start", PeertubeParsingHelper.COUNT_KEY, "cached", "Companion", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CharacterReader implements AutoCloseable {
    public static final int BufferSize = 2048;
    public static final char EOF = 65535;
    private static final int MaxStringCacheLen = 12;
    public static final int RefillPoint = 1024;
    private static final int RewindLimit = 1024;
    private static final int StringCacheSize = 512;
    private int bufLength;
    private int bufMark;
    private int bufPos;
    private char[] charBuf;
    private int consumed;
    private int fillPoint;
    private int lastIcIndex;
    private String lastIcSeq;
    private int lineNumberOffset;
    private ArrayList<Integer> newlinePositions;
    private boolean readFully;
    private Reader reader;
    private String[] stringCache;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final SoftPool<String[]> StringPool = new SoftPool<>(new Function0() { // from class: com.fleeksoft.ksoup.parser.CharacterReader$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CharacterReader.StringPool$lambda$0();
        }
    });
    private static final SoftPool<char[]> BufferPool = new SoftPool<>(new Function0() { // from class: com.fleeksoft.ksoup.parser.CharacterReader$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CharacterReader.BufferPool$lambda$0();
        }
    });

    static final boolean consumeAttributeQuoted$lambda$0(boolean z, char c) {
        if (c == 0 || c == '&') {
            return false;
        }
        if (z) {
            return c != '\'';
        }
        return c != '\"';
    }

    static final boolean consumeData$lambda$0(char c) {
        return (c == '&' || c == '<' || c == 0) ? false : true;
    }

    static final boolean consumeRawData$lambda$0(char c) {
        return (c == '<' || c == 0) ? false : true;
    }

    static final boolean consumeTagName$lambda$0(char c) {
        return (c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' ' || c == '/' || c == '>') ? false : true;
    }

    public CharacterReader(Reader reader) throws UncheckedIOException {
        Intrinsics.checkNotNullParameter(reader, "reader");
        this.bufMark = -1;
        this.lineNumberOffset = 1;
        this.reader = reader;
        this.charBuf = BufferPool.borrow();
        this.stringCache = StringPool.borrow();
        bufferUp();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CharacterReader(String html) {
        this(new StringReader(html));
        Intrinsics.checkNotNullParameter(html, "html");
    }

    public final boolean isClosed() {
        return this.reader == null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.AutoCloseable
    public void close() {
        try {
            Reader reader = this.reader;
            if (reader != null) {
                reader.close();
            }
        } catch (IOException unused) {
        } finally {
            this.reader = null;
            char[] cArr = this.charBuf;
            if (cArr != null) {
                ArraysKt.fill$default(cArr, (char) 0, 0, 0, 6, (Object) null);
            }
            char[] cArr2 = this.charBuf;
            if (cArr2 != null) {
                BufferPool.release(cArr2);
            }
            this.charBuf = null;
            String[] strArr = this.stringCache;
            if (strArr != null) {
                StringPool.release(strArr);
            }
            this.stringCache = null;
        }
    }

    private final void bufferUp() throws UncheckedIOException {
        if (this.readFully || this.bufPos < this.fillPoint || this.bufMark != -1) {
            return;
        }
        doBufferUp();
    }

    private final void doBufferUp() throws UncheckedIOException {
        char[] cArr;
        int i = this.consumed;
        int i2 = this.bufPos;
        this.consumed = i + i2;
        int i3 = this.bufLength - i2;
        this.bufLength = i3;
        if (i3 > 0 && (cArr = this.charBuf) != null) {
            Intrinsics.checkNotNull(cArr);
            int i4 = this.bufPos;
            ArraysKt.copyInto(cArr, cArr, 0, i4, this.bufLength + i4);
        }
        this.bufPos = 0;
        while (this.bufLength < 2048) {
            try {
                Reader reader = this.reader;
                Intrinsics.checkNotNull(reader);
                char[] cArr2 = this.charBuf;
                Intrinsics.checkNotNull(cArr2);
                int i5 = this.bufLength;
                char[] cArr3 = this.charBuf;
                Intrinsics.checkNotNull(cArr3);
                int i6 = reader.read(cArr2, i5, cArr3.length - this.bufLength);
                if (i6 == -1) {
                    this.readFully = true;
                    break;
                } else if (i6 == 0) {
                    break;
                } else {
                    this.bufLength += i6;
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        this.fillPoint = Math.min(this.bufLength, 1024);
        scanBufferForNewlines();
        this.lastIcSeq = null;
    }

    public final void mark() throws UncheckedIOException {
        if (this.bufLength - this.bufPos < 1024) {
            this.fillPoint = 0;
        }
        bufferUp();
        this.bufMark = this.bufPos;
    }

    public final void unmark() {
        this.bufMark = -1;
    }

    public final void rewindToMark() throws UncheckedIOException {
        int i = this.bufMark;
        if (i == -1) {
            throw new UncheckedIOException(new IOException("Mark invalid"));
        }
        this.bufPos = i;
        unmark();
    }

    public final int pos() {
        return this.consumed + this.bufPos;
    }

    /* JADX INFO: renamed from: readFully, reason: from getter */
    public final boolean getReadFully() {
        return this.readFully;
    }

    public final void trackNewlines(boolean track) {
        if (track && this.newlinePositions == null) {
            this.newlinePositions = new ArrayList<>(25);
            scanBufferForNewlines();
        } else {
            if (track) {
                return;
            }
            this.newlinePositions = null;
        }
    }

    public final boolean isTrackNewlines() {
        return this.newlinePositions != null;
    }

    public final int lineNumber() {
        return lineNumber(pos());
    }

    public final int lineNumber(int pos) {
        if (!isTrackNewlines()) {
            return 1;
        }
        int iLineNumIndex = lineNumIndex(pos);
        int i = this.lineNumberOffset;
        return iLineNumIndex == -1 ? i : iLineNumIndex + i + 1;
    }

    public final int columnNumber() {
        return columnNumber(pos());
    }

    public final int columnNumber(int pos) {
        int iLineNumIndex;
        if (isTrackNewlines() && (iLineNumIndex = lineNumIndex(pos)) != -1) {
            ArrayList<Integer> arrayList = this.newlinePositions;
            Intrinsics.checkNotNull(arrayList);
            Integer num = arrayList.get(iLineNumIndex);
            Intrinsics.checkNotNullExpressionValue(num, "get(...)");
            return (pos - num.intValue()) + 1;
        }
        return pos + 1;
    }

    public final String posLineCol() {
        return lineNumber() + ":" + columnNumber();
    }

    private final int lineNumIndex(int pos) {
        if (!isTrackNewlines()) {
            return 0;
        }
        ArrayList<Integer> arrayList = this.newlinePositions;
        Intrinsics.checkNotNull(arrayList);
        int iBinarySearch$default = CollectionsKt.binarySearch$default(arrayList, Integer.valueOf(pos), 0, 0, 6, (Object) null);
        return iBinarySearch$default < -1 ? Math.abs(iBinarySearch$default) - 2 : iBinarySearch$default;
    }

    private final void scanBufferForNewlines() {
        if (isTrackNewlines()) {
            ArrayList<Integer> arrayList = this.newlinePositions;
            Intrinsics.checkNotNull(arrayList);
            if (!arrayList.isEmpty()) {
                int iLineNumIndex = lineNumIndex(this.consumed);
                if (iLineNumIndex == -1) {
                    iLineNumIndex = 0;
                }
                ArrayList<Integer> arrayList2 = this.newlinePositions;
                Intrinsics.checkNotNull(arrayList2);
                Integer num = arrayList2.get(iLineNumIndex);
                Intrinsics.checkNotNullExpressionValue(num, "get(...)");
                int iIntValue = num.intValue();
                this.lineNumberOffset += iLineNumIndex;
                ArrayList<Integer> arrayList3 = this.newlinePositions;
                Intrinsics.checkNotNull(arrayList3);
                arrayList3.clear();
                ArrayList<Integer> arrayList4 = this.newlinePositions;
                Intrinsics.checkNotNull(arrayList4);
                arrayList4.add(Integer.valueOf(iIntValue));
            }
            int i = this.bufLength;
            for (int i2 = this.bufPos; i2 < i; i2++) {
                char[] cArr = this.charBuf;
                Intrinsics.checkNotNull(cArr);
                if (cArr[i2] == '\n') {
                    ArrayList<Integer> arrayList5 = this.newlinePositions;
                    Intrinsics.checkNotNull(arrayList5);
                    arrayList5.add(Integer.valueOf(this.consumed + 1 + i2));
                }
            }
        }
    }

    public final boolean isEmpty() throws UncheckedIOException {
        bufferUp();
        return this.bufPos >= this.bufLength;
    }

    private final boolean isEmptyNoBufferUp() {
        return this.bufPos >= this.bufLength;
    }

    public final char current() throws UncheckedIOException {
        bufferUp();
        if (isEmptyNoBufferUp()) {
            return (char) 65535;
        }
        char[] cArr = this.charBuf;
        Intrinsics.checkNotNull(cArr);
        return cArr[this.bufPos];
    }

    public final char consume() throws UncheckedIOException {
        char c;
        bufferUp();
        if (isEmptyNoBufferUp()) {
            c = 65535;
        } else {
            char[] cArr = this.charBuf;
            Intrinsics.checkNotNull(cArr);
            c = cArr[this.bufPos];
        }
        this.bufPos++;
        return c;
    }

    public final void unconsume() throws UncheckedIOException {
        int i = this.bufPos;
        if (i < 1) {
            throw new UncheckedIOException(new IOException("WTF: No buffer left to unconsume."));
        }
        this.bufPos = i - 1;
    }

    public final void advance() {
        this.bufPos++;
    }

    public final int nextIndexOf(char c) throws UncheckedIOException {
        bufferUp();
        int i = this.bufLength;
        for (int i2 = this.bufPos; i2 < i; i2++) {
            char[] cArr = this.charBuf;
            Intrinsics.checkNotNull(cArr);
            if (c == cArr[i2]) {
                return i2 - this.bufPos;
            }
        }
        return -1;
    }

    public final int nextIndexOf(CharSequence seq) throws UncheckedIOException {
        char[] cArr;
        Intrinsics.checkNotNullParameter(seq, "seq");
        bufferUp();
        char cCharAt = seq.charAt(0);
        int i = this.bufPos;
        while (i < this.bufLength) {
            char[] cArr2 = this.charBuf;
            Intrinsics.checkNotNull(cArr2);
            int i2 = 1;
            if (cCharAt != cArr2[i]) {
                do {
                    i++;
                    if (i >= this.bufLength) {
                        break;
                    }
                    cArr = this.charBuf;
                    Intrinsics.checkNotNull(cArr);
                } while (cCharAt != cArr[i]);
            }
            int i3 = i + 1;
            int length = (seq.length() + i3) - 1;
            int i4 = this.bufLength;
            if (i < i4 && length <= i4) {
                int i5 = i3;
                while (i5 < length) {
                    char cCharAt2 = seq.charAt(i2);
                    char[] cArr3 = this.charBuf;
                    Intrinsics.checkNotNull(cArr3);
                    if (cCharAt2 != cArr3[i5]) {
                        break;
                    }
                    i5++;
                    i2++;
                }
                if (i5 == length) {
                    return i - this.bufPos;
                }
            }
            i = i3;
        }
        return -1;
    }

    public final String consumeTo(char c) throws UncheckedIOException {
        int iNextIndexOf = nextIndexOf(c);
        if (iNextIndexOf == -1) {
            return consumeToEnd();
        }
        String strCacheString = INSTANCE.cacheString(this.charBuf, this.stringCache, this.bufPos, iNextIndexOf);
        this.bufPos += iNextIndexOf;
        return strCacheString;
    }

    public final String consumeTo(String seq) throws UncheckedIOException {
        Intrinsics.checkNotNullParameter(seq, "seq");
        int iNextIndexOf = nextIndexOf(seq);
        if (iNextIndexOf == -1) {
            if (this.bufLength - this.bufPos < seq.length()) {
                return consumeToEnd();
            }
            int length = (this.bufLength - seq.length()) + 1;
            Companion companion = INSTANCE;
            char[] cArr = this.charBuf;
            String[] strArr = this.stringCache;
            int i = this.bufPos;
            String strCacheString = companion.cacheString(cArr, strArr, i, length - i);
            this.bufPos = length;
            return strCacheString;
        }
        String strCacheString2 = INSTANCE.cacheString(this.charBuf, this.stringCache, this.bufPos, iNextIndexOf);
        this.bufPos += iNextIndexOf;
        return strCacheString2;
    }

    public final String consumeMatching(Function1<? super Character, Boolean> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        return consumeMatching(func, -1);
    }

    public final String consumeMatching(Function1<? super Character, Boolean> func, int maxLength) throws UncheckedIOException {
        Intrinsics.checkNotNullParameter(func, "func");
        bufferUp();
        int i = this.bufPos;
        int i2 = this.bufLength;
        char[] cArr = this.charBuf;
        int i3 = i;
        while (i3 < i2 && (maxLength == -1 || i3 - i < maxLength)) {
            Intrinsics.checkNotNull(cArr);
            if (!func.invoke(Character.valueOf(cArr[i3])).booleanValue()) {
                break;
            }
            i3++;
        }
        this.bufPos = i3;
        return i3 > i ? INSTANCE.cacheString(this.charBuf, this.stringCache, i, i3 - i) : "";
    }

    public final String consumeToAny(final char... chars) {
        Intrinsics.checkNotNullParameter(chars, "chars");
        return consumeMatching(new Function1() { // from class: com.fleeksoft.ksoup.parser.CharacterReader$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CharacterReader.consumeToAny$lambda$0(chars, ((Character) obj).charValue()));
            }
        });
    }

    public final int binarySearch(char[] cArr, char c) {
        Intrinsics.checkNotNullParameter(cArr, "<this>");
        return ArraysKt.indexOf(cArr, c);
    }

    static final boolean consumeToAnySorted$lambda$0(CharacterReader characterReader, char[] cArr, char c) {
        return characterReader.binarySearch(cArr, c) < 0;
    }

    public final String consumeToAnySorted(final char... chars) {
        Intrinsics.checkNotNullParameter(chars, "chars");
        return consumeMatching(new Function1() { // from class: com.fleeksoft.ksoup.parser.CharacterReader$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CharacterReader.consumeToAnySorted$lambda$0(this.f$0, chars, ((Character) obj).charValue()));
            }
        });
    }

    public final String consumeData() {
        return consumeMatching(new Function1() { // from class: com.fleeksoft.ksoup.parser.CharacterReader$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CharacterReader.consumeData$lambda$0(((Character) obj).charValue()));
            }
        });
    }

    public final String consumeAttributeQuoted(final boolean single) {
        return consumeMatching(new Function1() { // from class: com.fleeksoft.ksoup.parser.CharacterReader$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CharacterReader.consumeAttributeQuoted$lambda$0(single, ((Character) obj).charValue()));
            }
        });
    }

    public final String consumeRawData() {
        return consumeMatching(new Function1() { // from class: com.fleeksoft.ksoup.parser.CharacterReader$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CharacterReader.consumeRawData$lambda$0(((Character) obj).charValue()));
            }
        });
    }

    public final String consumeTagName() {
        return consumeMatching(new Function1() { // from class: com.fleeksoft.ksoup.parser.CharacterReader$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CharacterReader.consumeTagName$lambda$0(((Character) obj).charValue()));
            }
        });
    }

    public final String consumeToEnd() throws UncheckedIOException {
        bufferUp();
        Companion companion = INSTANCE;
        char[] cArr = this.charBuf;
        String[] strArr = this.stringCache;
        int i = this.bufPos;
        String strCacheString = companion.cacheString(cArr, strArr, i, this.bufLength - i);
        this.bufPos = this.bufLength;
        return strCacheString;
    }

    public final String consumeLetterSequence() {
        return consumeMatching(new Function1() { // from class: com.fleeksoft.ksoup.parser.CharacterReader$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(Character.isLetter(((Character) obj).charValue()));
            }
        });
    }

    public final String consumeLetterThenDigitSequence() throws UncheckedIOException {
        bufferUp();
        int i = this.bufPos;
        while (this.bufPos < this.bufLength) {
            StringUtil stringUtil = StringUtil.INSTANCE;
            char[] cArr = this.charBuf;
            Intrinsics.checkNotNull(cArr);
            if (!stringUtil.isAsciiLetter(cArr[this.bufPos])) {
                break;
            }
            this.bufPos++;
        }
        while (!isEmptyNoBufferUp()) {
            StringUtil stringUtil2 = StringUtil.INSTANCE;
            char[] cArr2 = this.charBuf;
            Intrinsics.checkNotNull(cArr2);
            if (!stringUtil2.isDigit(cArr2[this.bufPos])) {
                break;
            }
            this.bufPos++;
        }
        return INSTANCE.cacheString(this.charBuf, this.stringCache, i, this.bufPos - i);
    }

    public final String consumeHexSequence() {
        return consumeMatching(new Function1() { // from class: com.fleeksoft.ksoup.parser.CharacterReader$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(StringUtil.INSTANCE.isHexDigit(((Character) obj).charValue()));
            }
        });
    }

    static final boolean consumeDigitSequence$lambda$0(char c) {
        return Intrinsics.compare((int) c, 48) >= 0 && Intrinsics.compare((int) c, 57) <= 0;
    }

    public final String consumeDigitSequence() {
        return consumeMatching(new Function1() { // from class: com.fleeksoft.ksoup.parser.CharacterReader$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CharacterReader.consumeDigitSequence$lambda$0(((Character) obj).charValue()));
            }
        });
    }

    public final boolean matches(char c) {
        if (isEmpty()) {
            return false;
        }
        char[] cArr = this.charBuf;
        Intrinsics.checkNotNull(cArr);
        return cArr[this.bufPos] == c;
    }

    public final boolean matches(String seq) throws UncheckedIOException {
        Intrinsics.checkNotNullParameter(seq, "seq");
        bufferUp();
        int length = seq.length();
        if (length > this.bufLength - this.bufPos) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = seq.charAt(i);
            char[] cArr = this.charBuf;
            Intrinsics.checkNotNull(cArr);
            if (cCharAt != cArr[this.bufPos + i]) {
                return false;
            }
        }
        return true;
    }

    public final boolean matchesIgnoreCase(String seq) throws UncheckedIOException {
        Intrinsics.checkNotNullParameter(seq, "seq");
        bufferUp();
        int length = seq.length();
        if (length > this.bufLength - this.bufPos) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = seq.charAt(i);
            char[] cArr = this.charBuf;
            Intrinsics.checkNotNull(cArr);
            char c = cArr[this.bufPos + i];
            if (cCharAt != c && Character.toUpperCase(cCharAt) != Character.toUpperCase(c)) {
                return false;
            }
        }
        return true;
    }

    public final boolean matchesAny(char... seq) throws UncheckedIOException {
        Intrinsics.checkNotNullParameter(seq, "seq");
        if (isEmpty()) {
            return false;
        }
        bufferUp();
        char[] cArr = this.charBuf;
        Intrinsics.checkNotNull(cArr);
        char c = cArr[this.bufPos];
        for (char c2 : seq) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    public final boolean matchesAnySorted(char[] seq) throws UncheckedIOException {
        Intrinsics.checkNotNullParameter(seq, "seq");
        bufferUp();
        if (isEmpty()) {
            return false;
        }
        char[] cArr = this.charBuf;
        Intrinsics.checkNotNull(cArr);
        return ArraysKt.contains(seq, cArr[this.bufPos]);
    }

    public final boolean matchesAsciiAlpha() {
        if (isEmpty()) {
            return false;
        }
        StringUtil stringUtil = StringUtil.INSTANCE;
        char[] cArr = this.charBuf;
        Intrinsics.checkNotNull(cArr);
        return stringUtil.isAsciiLetter(cArr[this.bufPos]);
    }

    public final boolean matchesDigit() {
        if (isEmpty()) {
            return false;
        }
        StringUtil stringUtil = StringUtil.INSTANCE;
        char[] cArr = this.charBuf;
        Intrinsics.checkNotNull(cArr);
        return stringUtil.isDigit(cArr[this.bufPos]);
    }

    public final boolean matchConsume(String seq) throws UncheckedIOException {
        Intrinsics.checkNotNullParameter(seq, "seq");
        bufferUp();
        if (!matches(seq)) {
            return false;
        }
        this.bufPos += seq.length();
        return true;
    }

    public final boolean matchConsumeIgnoreCase(String seq) {
        Intrinsics.checkNotNullParameter(seq, "seq");
        if (!matchesIgnoreCase(seq)) {
            return false;
        }
        this.bufPos += seq.length();
        return true;
    }

    public final boolean containsIgnoreCase(String seq) throws UncheckedIOException {
        Intrinsics.checkNotNullParameter(seq, "seq");
        if (Intrinsics.areEqual(seq, this.lastIcSeq)) {
            int i = this.lastIcIndex;
            if (i == -1) {
                return false;
            }
            if (i >= this.bufPos) {
                return true;
            }
        }
        this.lastIcSeq = seq;
        String lowerCase = seq.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        int iNextIndexOf = nextIndexOf(lowerCase);
        if (iNextIndexOf > -1) {
            this.lastIcIndex = this.bufPos + iNextIndexOf;
            return true;
        }
        String upperCase = seq.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        int iNextIndexOf2 = nextIndexOf(upperCase);
        boolean z = iNextIndexOf2 > -1;
        this.lastIcIndex = z ? this.bufPos + iNextIndexOf2 : -1;
        return z;
    }

    public String toString() {
        if (this.bufLength - this.bufPos < 0) {
            return "";
        }
        char[] cArr = this.charBuf;
        Intrinsics.checkNotNull(cArr);
        int i = this.bufPos;
        return StringsKt.concatToString(cArr, i, (this.bufLength - i) + i);
    }

    public final boolean rangeEquals(int start, int count, String cached) {
        Intrinsics.checkNotNullParameter(cached, "cached");
        return INSTANCE.rangeEquals(this.charBuf, start, count, cached);
    }

    /* JADX INFO: compiled from: CharacterReader.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0019\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\u0010\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\u0017J(\u0010\u0018\u001a\u00020\u00192\b\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/fleeksoft/ksoup/parser/CharacterReader$Companion;", "", "<init>", "()V", "EOF", "", "MaxStringCacheLen", "", "StringCacheSize", "StringPool", "Lcom/fleeksoft/ksoup/internal/SoftPool;", "", "", "BufferPool", "", "BufferSize", "RefillPoint", "RewindLimit", "cacheString", "charBuf", "stringCache", "start", PeertubeParsingHelper.COUNT_KEY, "([C[Ljava/lang/String;II)Ljava/lang/String;", "rangeEquals", "", "cached", "ksoup_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String cacheString(char[] charBuf, String[] stringCache, int start, int count) {
            if (count > 12) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Intrinsics.checkNotNull(charBuf);
                return StringKt.buildString(stringCompanionObject, charBuf, start, count);
            }
            if (count < 1) {
                return "";
            }
            int i = count + start;
            int i2 = 0;
            for (int i3 = start; i3 < i; i3++) {
                Intrinsics.checkNotNull(charBuf);
                i2 = (i2 * 31) + charBuf[i3];
            }
            int i4 = i2 & 511;
            Intrinsics.checkNotNull(stringCache);
            String str = stringCache[i4];
            if (str != null && rangeEquals(charBuf, start, count, str)) {
                return str;
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Intrinsics.checkNotNull(charBuf);
            String strBuildString = StringKt.buildString(stringCompanionObject2, charBuf, start, count);
            stringCache[i4] = strBuildString;
            return strBuildString;
        }

        public final boolean rangeEquals(char[] charBuf, int start, int count, String cached) {
            Intrinsics.checkNotNullParameter(cached, "cached");
            if (count != cached.length()) {
                return false;
            }
            int i = 0;
            while (true) {
                int i2 = count - 1;
                if (count == 0) {
                    return true;
                }
                Intrinsics.checkNotNull(charBuf);
                int i3 = start + 1;
                int i4 = i + 1;
                if (charBuf[start] != cached.charAt(i)) {
                    return false;
                }
                start = i3;
                count = i2;
                i = i4;
            }
        }
    }

    static final String[] StringPool$lambda$0() {
        return new String[512];
    }

    static final char[] BufferPool$lambda$0() {
        return new char[2048];
    }

    static final boolean consumeToAny$lambda$0(char[] cArr, char c) {
        for (char c2 : cArr) {
            if (c == c2) {
                return false;
            }
        }
        return true;
    }
}
