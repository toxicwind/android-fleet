package app.cash.zipline;

import app.cash.zipline.internal.LogAndroidKt;
import app.cash.zipline.internal.bridge.CallChannel;
import app.cash.zipline.internal.bridge.CallChannelKt;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: QuickJs.kt */
/* JADX INFO: loaded from: classes5.dex */
@EngineApi
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u000e\b\u0007\u0018\u0000 :2\u00060\u0001j\u0002`\u00022\u00020\u0003:\u0001:B\u0011\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"J\u0015\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0000¢\u0006\u0002\b(J\r\u0010)\u001a\u00020'H\u0000¢\u0006\u0002\b*J\u0006\u0010+\u001a\u00020%J\u0016\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"J\u0010\u0010/\u001a\u0004\u0018\u00010 2\u0006\u00100\u001a\u00020-J\b\u00101\u001a\u00020%H\u0016J\b\u00102\u001a\u00020%H\u0004J\u0011\u00103\u001a\u00020%2\u0006\u0010\u0004\u001a\u00020\u0005H\u0082 J\u0019\u00104\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00105\u001a\u00020\"H\u0082 J!\u00106\u001a\u00020%2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00105\u001a\u00020\"2\u0006\u00107\u001a\u00020'H\u0082 J\u001b\u0010/\u001a\u0004\u0018\u00010 2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00100\u001a\u00020-H\u0082 J!\u0010,\u001a\u00020-2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0082 J\u001b\u0010\u0010\u001a\u00020%2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0082 J\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0004\u001a\u00020\u0005H\u0082 J\u0019\u0010\u0018\u001a\u00020%2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u0005H\u0082 J\u0019\u0010\u001b\u001a\u00020%2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0082 J\u0011\u0010+\u001a\u00020%2\u0006\u0010\u0004\u001a\u00020\u0005H\u0082 J\u0019\u0010\u001e\u001a\u00020%2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00109\u001a\u00020\u0005H\u0082 R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0007R(\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\t\"\u0004\b\u0018\u0010\u0007R$\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\t\"\u0004\b\u001b\u0010\u0007R$\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\t\"\u0004\b\u001e\u0010\u0007¨\u0006;"}, d2 = {"Lapp/cash/zipline/QuickJs;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "Ljava/io/Closeable;", "context", "", "<init>", "(J)V", "getContext$zipline_release", "()J", "setContext$zipline_release", ES6Iterator.VALUE_PROPERTY, "Lapp/cash/zipline/InterruptHandler;", "interruptHandler", "getInterruptHandler", "()Lapp/cash/zipline/InterruptHandler;", "setInterruptHandler", "(Lapp/cash/zipline/InterruptHandler;)V", "memoryUsage", "Lapp/cash/zipline/MemoryUsage;", "getMemoryUsage", "()Lapp/cash/zipline/MemoryUsage;", "memoryLimit", "getMemoryLimit", "setMemoryLimit", "gcThreshold", "getGcThreshold", "setGcThreshold", "maxStackSize", "getMaxStackSize", "setMaxStackSize", "evaluate", "", "script", "", "fileName", "initOutboundChannel", "", "outboundChannel", "Lapp/cash/zipline/internal/bridge/CallChannel;", "initOutboundChannel$zipline_release", "getInboundChannel", "getInboundChannel$zipline_release", "gc", "compile", "", "sourceCode", "execute", "bytecode", "close", "finalize", "destroyContext", "getInboundCallChannel", "name", "setOutboundCallChannel", "callChannel", "limit", "stackSize", "Companion", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class QuickJs implements AutoCloseable, Closeable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private long context;
    private long gcThreshold;
    private InterruptHandler interruptHandler;
    private long maxStackSize;
    private long memoryLimit;

    public /* synthetic */ QuickJs(long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(j);
    }

    private final native byte[] compile(long context, String sourceCode, String fileName);

    @JvmStatic
    public static final QuickJs create() {
        return INSTANCE.create();
    }

    @JvmStatic
    public static final native long createContext();

    private final native void destroyContext(long context);

    private final native Object execute(long context, byte[] bytecode);

    private final native void gc(long context);

    private final native long getInboundCallChannel(long context, String name);

    private final native MemoryUsage memoryUsage(long context);

    private final native void setGcThreshold(long context, long gcThreshold);

    private final native void setInterruptHandler(long context, InterruptHandler interruptHandler);

    private final native void setMaxStackSize(long context, long stackSize);

    private final native void setMemoryLimit(long context, long limit);

    private final native void setOutboundCallChannel(long context, String name, CallChannel callChannel);

    private QuickJs(long j) {
        this.context = j;
        this.memoryLimit = -1L;
        this.gcThreshold = -1L;
        this.maxStackSize = -1L;
    }

    /* JADX INFO: renamed from: getContext$zipline_release, reason: from getter */
    public final long getContext() {
        return this.context;
    }

    public final void setContext$zipline_release(long j) {
        this.context = j;
    }

    /* JADX INFO: compiled from: QuickJs.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\t\u0010\u0006\u001a\u00020\u0007H\u0087 R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lapp/cash/zipline/QuickJs$Companion;", "", "<init>", "()V", "create", "Lapp/cash/zipline/QuickJs;", "createContext", "", "version", "", "getVersion", "()Ljava/lang/String;", "zipline_release"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final long createContext() {
            return QuickJs.createContext();
        }

        private Companion() {
        }

        @JvmStatic
        public final QuickJs create() {
            long jCreateContext = createContext();
            if (jCreateContext == 0) {
                throw new OutOfMemoryError("Cannot create QuickJs instance");
            }
            QuickJs quickJs = new QuickJs(jCreateContext, null);
            quickJs.setMemoryLimit(-1L);
            quickJs.setGcThreshold(262144L);
            quickJs.setMaxStackSize(524288L);
            return quickJs;
        }

        public final String getVersion() {
            return HostMainBuildConfigKt.quickJsVersion;
        }
    }

    static {
        QuickJsNativeLoaderKt.loadNativeLibrary();
    }

    public static /* synthetic */ Object evaluate$default(QuickJs quickJs, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "?";
        }
        return quickJs.evaluate(str, str2);
    }

    public final InterruptHandler getInterruptHandler() {
        return this.interruptHandler;
    }

    public final void setInterruptHandler(InterruptHandler interruptHandler) {
        this.interruptHandler = interruptHandler;
        setInterruptHandler(this.context, interruptHandler);
    }

    public final MemoryUsage getMemoryUsage() {
        MemoryUsage memoryUsage = memoryUsage(this.context);
        if (memoryUsage != null) {
            return memoryUsage;
        }
        throw new AssertionError();
    }

    public final long getMemoryLimit() {
        return this.memoryLimit;
    }

    public final void setMemoryLimit(long j) {
        this.memoryLimit = j;
        setMemoryLimit(this.context, j);
    }

    public final long getGcThreshold() {
        return this.gcThreshold;
    }

    public final void setGcThreshold(long j) {
        this.gcThreshold = j;
        setGcThreshold(this.context, j);
    }

    public final long getMaxStackSize() {
        return this.maxStackSize;
    }

    public final void setMaxStackSize(long j) {
        this.maxStackSize = j;
        setMaxStackSize(this.context, j);
    }

    public final Object evaluate(String script, String fileName) {
        Intrinsics.checkNotNullParameter(script, "script");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return execute(compile(script, fileName));
    }

    public final void initOutboundChannel$zipline_release(CallChannel outboundChannel) {
        Intrinsics.checkNotNullParameter(outboundChannel, "outboundChannel");
        setOutboundCallChannel(this.context, CallChannelKt.OUTBOUND_CHANNEL_NAME, outboundChannel);
    }

    public final CallChannel getInboundChannel$zipline_release() {
        long inboundCallChannel = getInboundCallChannel(this.context, CallChannelKt.INBOUND_CHANNEL_NAME);
        if (inboundCallChannel == 0) {
            throw new OutOfMemoryError("Cannot create QuickJs proxy to inbound channel");
        }
        return new JniCallChannel(this, inboundCallChannel);
    }

    public final void gc() {
        gc(this.context);
    }

    public final byte[] compile(String sourceCode, String fileName) {
        Intrinsics.checkNotNullParameter(sourceCode, "sourceCode");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return compile(this.context, sourceCode, fileName);
    }

    public final Object execute(byte[] bytecode) {
        Intrinsics.checkNotNullParameter(bytecode, "bytecode");
        return execute(this.context, bytecode);
    }

    @Override // java.lang.AutoCloseable, java.io.Closeable
    public void close() {
        long j = this.context;
        if (j != 0) {
            this.context = 0L;
            destroyContext(j);
        }
    }

    protected final void finalize() {
        if (this.context != 0) {
            LogAndroidKt.log("warn", "QuickJs instance leaked!", null);
        }
    }
}
