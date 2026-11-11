package io.ktor.utils.io;

import androidx.tvprovider.media.tv.TvContractCompat;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.io.Buffer;
import kotlinx.io.Source;
import kotlinx.io.bytestring.ByteString;
import kotlinx.io.bytestring.ByteStringKt;

/* JADX INFO: compiled from: ByteChannelScanner.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\fH\u0080@¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014H\u0082@¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\fH\u0082@¢\u0006\u0004\b\u0017\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001c\u001a\u00020\u001b*\u00020\u0004H\u0002¢\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001fR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010 R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010!R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-¨\u0006."}, d2 = {"Lio/ktor/utils/io/ByteChannelScanner;", "", "Lio/ktor/utils/io/ByteReadChannel;", TvContractCompat.PARAM_CHANNEL, "Lkotlinx/io/bytestring/ByteString;", "matchString", "Lio/ktor/utils/io/ByteWriteChannel;", "writeChannel", "", "limit", "<init>", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlinx/io/bytestring/ByteString;Lio/ktor/utils/io/ByteWriteChannel;J)V", "", "ignoreMissing", "findNext$ktor_io", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findNext", "", "buildPartialMatchTable", "()[I", "", "advanceToNextPotentialMatch", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkFullMatch", "extra", "checkBounds", "(J)V", "", "toSingleLineString", "(Lkotlinx/io/bytestring/ByteString;)Ljava/lang/String;", "Lio/ktor/utils/io/ByteReadChannel;", "Lkotlinx/io/bytestring/ByteString;", "Lio/ktor/utils/io/ByteWriteChannel;", "J", "Lkotlinx/io/Source;", TvContractCompat.PARAM_INPUT, "Lkotlinx/io/Source;", "partialMatchTable", "[I", "Lkotlinx/io/Buffer;", "partialMatchBuffer", "Lkotlinx/io/Buffer;", "bytesRead", "", "matchIndex", "I", "ktor-io"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ByteChannelScanner {
    private long bytesRead;
    private final ByteReadChannel channel;
    private final Source input;
    private final long limit;
    private int matchIndex;
    private final ByteString matchString;
    private final Buffer partialMatchBuffer;
    private final int[] partialMatchTable;
    private final ByteWriteChannel writeChannel;

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelScanner$advanceToNextPotentialMatch$1, reason: invalid class name */
    /* JADX INFO: compiled from: ByteChannelScanner.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteChannelScanner", f = "ByteChannelScanner.kt", i = {1, 2}, l = {99, 105, 110}, m = "advanceToNextPotentialMatch", n = {"nextMatch", "nextMatch"}, s = {"J$0", "J$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelScanner.this.advanceToNextPotentialMatch(this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.utils.io.ByteChannelScanner$checkFullMatch$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ByteChannelScanner.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.utils.io.ByteChannelScanner", f = "ByteChannelScanner.kt", i = {1, 1, 1}, l = {124, 142}, m = "checkFullMatch", n = {"byte", "oldMatchIndex", "retained"}, s = {"B$0", "I$0", "J$0"}, v = 1)
    static final class C09981 extends ContinuationImpl {
        byte B$0;
        int I$0;
        long J$0;
        int label;
        /* synthetic */ Object result;

        C09981(Continuation<? super C09981> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ByteChannelScanner.this.checkFullMatch(this);
        }
    }

    public ByteChannelScanner(ByteReadChannel channel, ByteString matchString, ByteWriteChannel writeChannel, long j) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        Intrinsics.checkNotNullParameter(matchString, "matchString");
        Intrinsics.checkNotNullParameter(writeChannel, "writeChannel");
        this.channel = channel;
        this.matchString = matchString;
        this.writeChannel = writeChannel;
        this.limit = j;
        if (matchString.getSize() <= 0) {
            throw new IllegalArgumentException("Empty match string not permitted for scanning".toString());
        }
        this.input = channel.getReadBuffer();
        this.partialMatchTable = buildPartialMatchTable();
        this.partialMatchBuffer = new Buffer();
    }

    public /* synthetic */ ByteChannelScanner(ByteReadChannel byteReadChannel, ByteString byteString, ByteWriteChannel byteWriteChannel, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteReadChannel, byteString, byteWriteChannel, (i & 8) != 0 ? Long.MAX_VALUE : j);
    }

    public static /* synthetic */ Object findNext$ktor_io$default(ByteChannelScanner byteChannelScanner, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return byteChannelScanner.findNext$ktor_io(z, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0060  */
    /* JADX WARN: Code duplicated, block: B:26:0x006f A[PHI: r10 r11
      0x006f: PHI (r10v3 boolean) = (r10v6 boolean), (r10v8 boolean) binds: [B:24:0x006c, B:19:0x004b] A[DONT_GENERATE, DONT_INLINE]
      0x006f: PHI (r11v4 java.lang.Object) = (r11v13 java.lang.Object), (r11v1 java.lang.Object) binds: [B:24:0x006c, B:19:0x004b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:29:0x0078 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x007a  */
    /* JADX WARN: Code duplicated, block: B:35:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c0 A[PHI: r10
      0x00c0: PHI (r10v2 boolean) = (r10v3 boolean), (r10v6 boolean) binds: [B:27:0x0075, B:22:0x005e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:40:0x00cb A[PHI: r10
      0x00cb: PHI (r10v1 boolean) = (r10v2 boolean), (r10v9 boolean) binds: [B:38:0x00c8, B:18:0x0044] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0097, code lost:
    
        if (r11.flush(r0) == r1) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d3, code lost:
    
        if (r11 == r1) goto L42;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:35:0x00a1, please report this as an issue */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00d3 -> B:43:0x00d6). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object findNext$ktor_io(boolean r10, kotlin.coroutines.Continuation<? super java.lang.Long> r11) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelScanner.findNext$ktor_io(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final int[] buildPartialMatchTable() {
        int[] iArr = new int[this.matchString.getSize()];
        int size = this.matchString.getSize();
        int i = 0;
        for (int i2 = 1; i2 < size; i2++) {
            while (i > 0 && this.matchString.get(i2) != this.matchString.get(i)) {
                i = iArr[i - 1];
            }
            if (this.matchString.get(i2) == this.matchString.get(i)) {
                i++;
            }
            iArr[i2] = i;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:19:0x004a  */
    /* JADX WARN: Code duplicated, block: B:21:0x0052  */
    /* JADX WARN: Code duplicated, block: B:29:0x006b  */
    /* JADX WARN: Code duplicated, block: B:31:0x0086  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00b3 -> B:19:0x004a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object advanceToNextPotentialMatch(kotlin.coroutines.Continuation<? super kotlin.Unit> r18) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelScanner.advanceToNextPotentialMatch(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:19:0x004c  */
    /* JADX WARN: Code duplicated, block: B:27:0x0067  */
    /* JADX WARN: Code duplicated, block: B:29:0x0071  */
    /* JADX WARN: Code duplicated, block: B:34:0x007f  */
    /* JADX WARN: Code duplicated, block: B:39:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:48:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:50:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:51:0x0091 A[ADDED_TO_REGION, EDGE_INSN: B:51:0x0091->B:37:0x0091 BREAK  A[LOOP:0: B:32:0x007b->B:36:0x0087], REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x004a -> B:27:0x0067). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0055 -> B:22:0x0059). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object checkFullMatch(kotlin.coroutines.Continuation<? super java.lang.Boolean> r14) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.ByteChannelScanner.checkFullMatch(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void checkBounds(long extra) throws IOException {
        if (this.bytesRead + extra <= this.limit) {
            return;
        }
        throw new IOException("Limit of " + this.limit + " bytes exceeded while searching for \"" + toSingleLineString(this.matchString) + '\"');
    }

    private final String toSingleLineString(ByteString byteString) {
        return StringsKt.replace$default(ByteStringKt.decodeToString(byteString), "\n", "\\n", false, 4, (Object) null);
    }
}
