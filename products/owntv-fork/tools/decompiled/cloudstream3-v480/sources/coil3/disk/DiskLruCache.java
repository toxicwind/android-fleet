package coil3.disk;

import coil3.util.Collections_jvmCommonKt;
import coil3.util.Coroutines_nonJsCommonKt;
import coil3.util.FileSystemsKt;
import io.ktor.http.ContentDisposition;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.FileSystem;
import okio.ForwardingFileSystem;
import okio.Okio;
import okio.Path;
import okio.Sink;

/* JADX INFO: compiled from: DiskLruCache.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000{\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015*\u0001&\b\u0000\u0018\u0000 H2\u00060\u0001j\u0002`\u0002:\u0004EFGHB7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0006\u0010(\u001a\u00020)J\b\u0010*\u001a\u00020)H\u0002J\b\u0010+\u001a\u00020\u001fH\u0002J\u0010\u0010,\u001a\u00020)2\u0006\u0010-\u001a\u00020\u0015H\u0002J\b\u0010.\u001a\u00020)H\u0002J\b\u0010/\u001a\u00020)H\u0002J\u0017\u00100\u001a\b\u0018\u000101R\u00020\u00002\u0006\u00102\u001a\u00020\u0015H\u0086\u0002J\u0014\u00103\u001a\b\u0018\u000104R\u00020\u00002\u0006\u00102\u001a\u00020\u0015J\u0006\u0010\u001c\u001a\u00020\nJ\u001c\u00105\u001a\u00020)2\n\u00106\u001a\u000604R\u00020\u00002\u0006\u00107\u001a\u00020!H\u0002J\b\u00108\u001a\u00020!H\u0002J\u000e\u00109\u001a\u00020!2\u0006\u00102\u001a\u00020\u0015J\u0014\u0010:\u001a\u00020!2\n\u0010;\u001a\u00060\u0016R\u00020\u0000H\u0002J\b\u0010<\u001a\u00020)H\u0002J\b\u0010=\u001a\u00020)H\u0016J\u0006\u0010>\u001a\u00020)J\b\u0010?\u001a\u00020)H\u0002J\b\u0010@\u001a\u00020!H\u0002J\b\u0010A\u001a\u00020)H\u0002J\u0006\u0010B\u001a\u00020)J\b\u0010C\u001a\u00020)H\u0002J\u0010\u0010D\u001a\u00020)2\u0006\u00102\u001a\u00020\u0015H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u0015\u0012\b\u0012\u00060\u0016R\u00020\u00000\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u00060\u001aj\u0002`\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u00020&X\u0082\u0004¢\u0006\u0004\n\u0002\u0010'¨\u0006I"}, d2 = {"Lcoil3/disk/DiskLruCache;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "fileSystem", "Lokio/FileSystem;", "directory", "Lokio/Path;", "cleanupCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "maxSize", "", "appVersion", "", "valueCount", "<init>", "(Lokio/FileSystem;Lokio/Path;Lkotlin/coroutines/CoroutineContext;JII)V", "journalFile", "journalFileTmp", "journalFileBackup", "lruEntries", "", "", "Lcoil3/disk/DiskLruCache$Entry;", "cleanupScope", "Lkotlinx/coroutines/CoroutineScope;", "lock", "", "Lkotlinx/atomicfu/locks/SynchronizedObject;", ContentDisposition.Parameters.Size, "operationsSinceRewrite", "journalWriter", "Lokio/BufferedSink;", "hasJournalErrors", "", "initialized", "closed", "mostRecentTrimFailed", "mostRecentRebuildFailed", "coil3/disk/DiskLruCache$fileSystem$1", "Lcoil3/disk/DiskLruCache$fileSystem$1;", "initialize", "", "readJournal", "newJournalWriter", "readJournalLine", "line", "processJournal", "writeJournal", "get", "Lcoil3/disk/DiskLruCache$Snapshot;", "key", "edit", "Lcoil3/disk/DiskLruCache$Editor;", "completeEdit", "editor", "success", "journalRewriteRequired", "remove", "removeEntry", "entry", "checkNotClosed", "close", "flush", "trimToSize", "removeOldestEntry", "delete", "evictAll", "launchCleanup", "validateKey", "Snapshot", "Editor", "Entry", "Companion", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DiskLruCache implements AutoCloseable {
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    public static final String JOURNAL_FILE = "journal";
    public static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    public static final String JOURNAL_FILE_TMP = "journal.tmp";
    public static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    public static final String VERSION = "1";
    private final int appVersion;
    private final CoroutineScope cleanupScope;
    private boolean closed;
    private final Path directory;
    private final DiskLruCache$fileSystem$1 fileSystem;
    private boolean hasJournalErrors;
    private boolean initialized;
    private final Path journalFile;
    private final Path journalFileBackup;
    private final Path journalFileTmp;
    private BufferedSink journalWriter;
    private final Object lock;
    private final Map<String, Entry> lruEntries;
    private final long maxSize;
    private boolean mostRecentRebuildFailed;
    private boolean mostRecentTrimFailed;
    private int operationsSinceRewrite;
    private long size;
    private final int valueCount;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");

    /* JADX WARN: Type inference failed for: r4v10, types: [coil3.disk.DiskLruCache$fileSystem$1] */
    public DiskLruCache(final FileSystem fileSystem, Path path, CoroutineContext coroutineContext, long j, int i, int i2) {
        this.directory = path;
        this.maxSize = j;
        this.appVersion = i;
        this.valueCount = i2;
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0".toString());
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0".toString());
        }
        this.journalFile = path.resolve(JOURNAL_FILE);
        this.journalFileTmp = path.resolve(JOURNAL_FILE_TMP);
        this.journalFileBackup = path.resolve(JOURNAL_FILE_BACKUP);
        this.lruEntries = Collections_jvmCommonKt.LruMutableMap$default(0, 0.0f, 3, null);
        CoroutineContext coroutineContextPlus = coroutineContext.plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));
        CoroutineDispatcher dispatcher = coil3.util.UtilsKt.getDispatcher(coroutineContext);
        this.cleanupScope = CoroutineScopeKt.CoroutineScope(coroutineContextPlus.plus(CoroutineDispatcher.limitedParallelism$default(dispatcher == null ? Coroutines_nonJsCommonKt.ioCoroutineDispatcher() : dispatcher, 1, null, 2, null)));
        this.lock = new Object();
        this.fileSystem = new ForwardingFileSystem(fileSystem) { // from class: coil3.disk.DiskLruCache$fileSystem$1
            @Override // okio.ForwardingFileSystem, okio.FileSystem
            public Sink sink(Path file, boolean mustCreate) {
                Path pathParent = file.parent();
                if (pathParent != null) {
                    createDirectories(pathParent);
                }
                return super.sink(file, mustCreate);
            }
        };
    }

    public final void initialize() {
        synchronized (this.lock) {
            if (this.initialized) {
                return;
            }
            delete(this.journalFileTmp);
            if (exists(this.journalFileBackup)) {
                boolean zExists = exists(this.journalFile);
                DiskLruCache$fileSystem$1 diskLruCache$fileSystem$1 = this.fileSystem;
                if (zExists) {
                    diskLruCache$fileSystem$1.delete(this.journalFileBackup);
                } else {
                    diskLruCache$fileSystem$1.atomicMove(this.journalFileBackup, this.journalFile);
                }
            }
            if (exists(this.journalFile)) {
                try {
                    readJournal();
                    processJournal();
                    this.initialized = true;
                    return;
                } catch (IOException unused) {
                    try {
                        delete();
                        this.closed = false;
                        writeJournal();
                        this.initialized = true;
                        Unit unit = Unit.INSTANCE;
                    } catch (Throwable th) {
                        this.closed = false;
                        throw th;
                    }
                }
            }
            writeJournal();
            this.initialized = true;
            Unit unit2 = Unit.INSTANCE;
        }
    }

    private final void readJournal() throws Throwable {
        BufferedSource bufferedSourceBuffer = Okio.buffer(source(this.journalFile));
        try {
            BufferedSource bufferedSource = bufferedSourceBuffer;
            String utf8LineStrict = bufferedSource.readUtf8LineStrict();
            String utf8LineStrict2 = bufferedSource.readUtf8LineStrict();
            String utf8LineStrict3 = bufferedSource.readUtf8LineStrict();
            String utf8LineStrict4 = bufferedSource.readUtf8LineStrict();
            String utf8LineStrict5 = bufferedSource.readUtf8LineStrict();
            if (!Intrinsics.areEqual(MAGIC, utf8LineStrict) || !Intrinsics.areEqual("1", utf8LineStrict2) || !Intrinsics.areEqual(String.valueOf(this.appVersion), utf8LineStrict3) || !Intrinsics.areEqual(String.valueOf(this.valueCount), utf8LineStrict4) || utf8LineStrict5.length() > 0) {
                throw new IOException("unexpected journal header: [" + utf8LineStrict + ", " + utf8LineStrict2 + ", " + utf8LineStrict3 + ", " + utf8LineStrict4 + ", " + utf8LineStrict5 + AbstractJsonLexerKt.END_LIST);
            }
            int i = 0;
            while (true) {
                try {
                    readJournalLine(bufferedSource.readUtf8LineStrict());
                    i++;
                } catch (EOFException unused) {
                    this.operationsSinceRewrite = i - this.lruEntries.size();
                    if (bufferedSource.exhausted()) {
                        this.journalWriter = newJournalWriter();
                    } else {
                        writeJournal();
                    }
                    Unit unit = Unit.INSTANCE;
                    if (bufferedSourceBuffer != null) {
                        try {
                            bufferedSourceBuffer.close();
                        } catch (Throwable th) {
                            th = th;
                        }
                    }
                    th = null;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (bufferedSourceBuffer != null) {
                try {
                    bufferedSourceBuffer.close();
                } catch (Throwable th3) {
                    ExceptionsKt.addSuppressed(th, th3);
                }
            }
        }
        if (th != null) {
            throw th;
        }
    }

    private final BufferedSink newJournalWriter() {
        return Okio.buffer(new FaultHidingSink(appendingSink(this.journalFile), new Function1() { // from class: coil3.disk.DiskLruCache$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DiskLruCache.newJournalWriter$lambda$4(this.f$0, (IOException) obj);
            }
        }));
    }

    static final Unit newJournalWriter$lambda$4(DiskLruCache diskLruCache, IOException iOException) {
        diskLruCache.hasJournalErrors = true;
        return Unit.INSTANCE;
    }

    private final void readJournalLine(String line) throws IOException {
        String strSubstring;
        String str = line;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, ' ', 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            throw new IOException("unexpected journal line: " + line);
        }
        int i = iIndexOf$default + 1;
        int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str, ' ', i, false, 4, (Object) null);
        if (iIndexOf$default2 == -1) {
            strSubstring = line.substring(i);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            if (iIndexOf$default == 6 && StringsKt.startsWith$default(line, REMOVE, false, 2, (Object) null)) {
                this.lruEntries.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = line.substring(i, iIndexOf$default2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        }
        Map<String, Entry> map = this.lruEntries;
        Entry entry = map.get(strSubstring);
        if (entry == null) {
            entry = new Entry(strSubstring);
            map.put(strSubstring, entry);
        }
        Entry entry2 = entry;
        if (iIndexOf$default2 != -1 && iIndexOf$default == 5 && StringsKt.startsWith$default(line, CLEAN, false, 2, (Object) null)) {
            String strSubstring2 = line.substring(iIndexOf$default2 + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
            List<String> listSplit$default = StringsKt.split$default((CharSequence) strSubstring2, new char[]{' '}, false, 0, 6, (Object) null);
            entry2.setReadable(true);
            entry2.setCurrentEditor(null);
            entry2.setLengths(listSplit$default);
            return;
        }
        if (iIndexOf$default2 == -1 && iIndexOf$default == 5 && StringsKt.startsWith$default(line, DIRTY, false, 2, (Object) null)) {
            entry2.setCurrentEditor(new Editor(entry2));
        } else {
            if (iIndexOf$default2 == -1 && iIndexOf$default == 4 && StringsKt.startsWith$default(line, READ, false, 2, (Object) null)) {
                return;
            }
            throw new IOException("unexpected journal line: " + line);
        }
    }

    private final void processJournal() {
        Iterator<Entry> it = this.lruEntries.values().iterator();
        long j = 0;
        while (it.hasNext()) {
            Entry next = it.next();
            int i = 0;
            if (next.getCurrentEditor() == null) {
                int i2 = this.valueCount;
                while (i < i2) {
                    j += next.getLengths()[i];
                    i++;
                }
            } else {
                next.setCurrentEditor(null);
                int i3 = this.valueCount;
                while (i < i3) {
                    delete(next.getCleanFiles().get(i));
                    delete(next.getDirtyFiles().get(i));
                    i++;
                }
                it.remove();
            }
        }
        this.size = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void writeJournal() {
        Throwable th;
        synchronized (this.lock) {
            BufferedSink bufferedSink = this.journalWriter;
            if (bufferedSink != null) {
                bufferedSink.close();
            }
            BufferedSink bufferedSinkBuffer = Okio.buffer(sink(this.journalFileTmp, false));
            try {
                BufferedSink bufferedSink2 = bufferedSinkBuffer;
                bufferedSink2.writeUtf8(MAGIC).writeByte(10);
                bufferedSink2.writeUtf8("1").writeByte(10);
                bufferedSink2.writeDecimalLong(this.appVersion).writeByte(10);
                bufferedSink2.writeDecimalLong(this.valueCount).writeByte(10);
                bufferedSink2.writeByte(10);
                for (Entry entry : this.lruEntries.values()) {
                    if (entry.getCurrentEditor() != null) {
                        bufferedSink2.writeUtf8(DIRTY);
                        bufferedSink2.writeByte(32);
                        bufferedSink2.writeUtf8(entry.getKey());
                        bufferedSink2.writeByte(10);
                    } else {
                        bufferedSink2.writeUtf8(CLEAN);
                        bufferedSink2.writeByte(32);
                        bufferedSink2.writeUtf8(entry.getKey());
                        entry.writeLengths(bufferedSink2);
                        bufferedSink2.writeByte(10);
                    }
                }
                Unit unit = Unit.INSTANCE;
                if (bufferedSinkBuffer != null) {
                    try {
                        bufferedSinkBuffer.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                th = null;
            } catch (Throwable th3) {
                if (bufferedSinkBuffer != null) {
                    try {
                        bufferedSinkBuffer.close();
                    } catch (Throwable th4) {
                        ExceptionsKt.addSuppressed(th3, th4);
                    }
                }
                th = th3;
            }
            if (th == null) {
                boolean zExists = exists(this.journalFile);
                DiskLruCache$fileSystem$1 diskLruCache$fileSystem$1 = this.fileSystem;
                if (zExists) {
                    diskLruCache$fileSystem$1.atomicMove(this.journalFile, this.journalFileBackup);
                    atomicMove(this.journalFileTmp, this.journalFile);
                    delete(this.journalFileBackup);
                } else {
                    diskLruCache$fileSystem$1.atomicMove(this.journalFileTmp, this.journalFile);
                }
                this.journalWriter = newJournalWriter();
                this.operationsSinceRewrite = 0;
                this.hasJournalErrors = false;
                this.mostRecentRebuildFailed = false;
                Unit unit2 = Unit.INSTANCE;
            } else {
                throw th;
            }
        }
    }

    public final Snapshot get(String key) {
        Snapshot snapshot;
        synchronized (this.lock) {
            checkNotClosed();
            validateKey(key);
            initialize();
            Entry entry = this.lruEntries.get(key);
            if (entry != null && (snapshot = entry.snapshot()) != null) {
                this.operationsSinceRewrite++;
                BufferedSink bufferedSink = this.journalWriter;
                Intrinsics.checkNotNull(bufferedSink);
                bufferedSink.writeUtf8(READ);
                bufferedSink.writeByte(32);
                bufferedSink.writeUtf8(key);
                bufferedSink.writeByte(10);
                bufferedSink.flush();
                if (journalRewriteRequired()) {
                    launchCleanup();
                }
                return snapshot;
            }
            return null;
        }
    }

    public final Editor edit(String key) {
        synchronized (this.lock) {
            checkNotClosed();
            validateKey(key);
            initialize();
            Entry entry = this.lruEntries.get(key);
            if ((entry != null ? entry.getCurrentEditor() : null) != null) {
                return null;
            }
            if (entry != null && entry.getLockingSnapshotCount() != 0) {
                return null;
            }
            if (!this.mostRecentTrimFailed && !this.mostRecentRebuildFailed) {
                BufferedSink bufferedSink = this.journalWriter;
                Intrinsics.checkNotNull(bufferedSink);
                bufferedSink.writeUtf8(DIRTY);
                bufferedSink.writeByte(32);
                bufferedSink.writeUtf8(key);
                bufferedSink.writeByte(10);
                bufferedSink.flush();
                if (this.hasJournalErrors) {
                    return null;
                }
                if (entry == null) {
                    entry = new Entry(key);
                    this.lruEntries.put(key, entry);
                }
                Editor editor = new Editor(entry);
                entry.setCurrentEditor(editor);
                return editor;
            }
            launchCleanup();
            return null;
        }
    }

    public final long size() {
        long j;
        synchronized (this.lock) {
            initialize();
            j = this.size;
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void completeEdit(Editor editor, boolean success) {
        synchronized (this.lock) {
            Entry entry = editor.getEntry();
            if (!Intrinsics.areEqual(entry.getCurrentEditor(), editor)) {
                throw new IllegalStateException("Check failed.");
            }
            if (success && !entry.getZombie()) {
                int i = this.valueCount;
                for (int i2 = 0; i2 < i; i2++) {
                    if (editor.getWritten()[i2] && !exists(entry.getDirtyFiles().get(i2))) {
                        editor.abort();
                        return;
                    }
                }
                int i3 = this.valueCount;
                for (int i4 = 0; i4 < i3; i4++) {
                    Path path = entry.getDirtyFiles().get(i4);
                    Path path2 = entry.getCleanFiles().get(i4);
                    boolean zExists = exists(path);
                    DiskLruCache$fileSystem$1 diskLruCache$fileSystem$1 = this.fileSystem;
                    if (zExists) {
                        diskLruCache$fileSystem$1.atomicMove(path, path2);
                    } else {
                        FileSystemsKt.createFile$default(diskLruCache$fileSystem$1, entry.getCleanFiles().get(i4), false, 2, null);
                    }
                    long j = entry.getLengths()[i4];
                    Long size = metadata(path2).getSize();
                    long jLongValue = size != null ? size.longValue() : 0L;
                    entry.getLengths()[i4] = jLongValue;
                    this.size = (this.size - j) + jLongValue;
                }
            } else {
                int i5 = this.valueCount;
                for (int i6 = 0; i6 < i5; i6++) {
                    delete(entry.getDirtyFiles().get(i6));
                }
            }
            entry.setCurrentEditor(null);
            if (entry.getZombie()) {
                removeEntry(entry);
                return;
            }
            this.operationsSinceRewrite++;
            BufferedSink bufferedSink = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink);
            if (success || entry.getReadable()) {
                entry.setReadable(true);
                bufferedSink.writeUtf8(CLEAN);
                bufferedSink.writeByte(32);
                bufferedSink.writeUtf8(entry.getKey());
                entry.writeLengths(bufferedSink);
                bufferedSink.writeByte(10);
            } else {
                this.lruEntries.remove(entry.getKey());
                bufferedSink.writeUtf8(REMOVE);
                bufferedSink.writeByte(32);
                bufferedSink.writeUtf8(entry.getKey());
                bufferedSink.writeByte(10);
            }
            bufferedSink.flush();
            if (this.size > this.maxSize || journalRewriteRequired()) {
                launchCleanup();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean journalRewriteRequired() {
        return this.operationsSinceRewrite >= 2000;
    }

    public final boolean remove(String key) {
        synchronized (this.lock) {
            checkNotClosed();
            validateKey(key);
            initialize();
            Entry entry = this.lruEntries.get(key);
            if (entry == null) {
                return false;
            }
            boolean zRemoveEntry = removeEntry(entry);
            if (zRemoveEntry && this.size <= this.maxSize) {
                this.mostRecentTrimFailed = false;
            }
            return zRemoveEntry;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeEntry(Entry entry) throws IOException {
        BufferedSink bufferedSink;
        if (entry.getLockingSnapshotCount() > 0 && (bufferedSink = this.journalWriter) != null) {
            bufferedSink.writeUtf8(DIRTY);
            bufferedSink.writeByte(32);
            bufferedSink.writeUtf8(entry.getKey());
            bufferedSink.writeByte(10);
            bufferedSink.flush();
        }
        if (entry.getLockingSnapshotCount() > 0 || entry.getCurrentEditor() != null) {
            entry.setZombie(true);
            return true;
        }
        int i = this.valueCount;
        for (int i2 = 0; i2 < i; i2++) {
            delete(entry.getCleanFiles().get(i2));
            this.size -= entry.getLengths()[i2];
            entry.getLengths()[i2] = 0;
        }
        this.operationsSinceRewrite++;
        BufferedSink bufferedSink2 = this.journalWriter;
        if (bufferedSink2 != null) {
            bufferedSink2.writeUtf8(REMOVE);
            bufferedSink2.writeByte(32);
            bufferedSink2.writeUtf8(entry.getKey());
            bufferedSink2.writeByte(10);
            bufferedSink2.flush();
        }
        this.lruEntries.remove(entry.getKey());
        if (journalRewriteRequired()) {
            launchCleanup();
        }
        return true;
    }

    private final void checkNotClosed() {
        if (this.closed) {
            throw new IllegalStateException("cache is closed".toString());
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        synchronized (this.lock) {
            if (this.initialized && !this.closed) {
                for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[0])) {
                    Editor currentEditor = entry.getCurrentEditor();
                    if (currentEditor != null) {
                        currentEditor.detach();
                    }
                }
                trimToSize();
                CoroutineScopeKt.cancel$default(this.cleanupScope, null, 1, null);
                BufferedSink bufferedSink = this.journalWriter;
                Intrinsics.checkNotNull(bufferedSink);
                bufferedSink.close();
                this.journalWriter = null;
                this.closed = true;
                Unit unit = Unit.INSTANCE;
                return;
            }
            this.closed = true;
        }
    }

    public final void flush() {
        synchronized (this.lock) {
            if (this.initialized) {
                checkNotClosed();
                trimToSize();
                BufferedSink bufferedSink = this.journalWriter;
                Intrinsics.checkNotNull(bufferedSink);
                bufferedSink.flush();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trimToSize() {
        while (this.size > this.maxSize) {
            if (!removeOldestEntry()) {
                return;
            }
        }
        this.mostRecentTrimFailed = false;
    }

    private final boolean removeOldestEntry() throws IOException {
        for (Entry entry : this.lruEntries.values()) {
            if (!entry.getZombie()) {
                removeEntry(entry);
                return true;
            }
        }
        return false;
    }

    private final void delete() throws IOException {
        close();
        FileSystemsKt.deleteContents(this.fileSystem, this.directory);
    }

    public final void evictAll() {
        synchronized (this.lock) {
            initialize();
            for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[0])) {
                removeEntry(entry);
            }
            this.mostRecentTrimFailed = false;
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: coil3.disk.DiskLruCache$launchCleanup$1, reason: invalid class name */
    /* JADX INFO: compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "coil3.disk.DiskLruCache$launchCleanup$1", f = "DiskLruCache.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DiskLruCache.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = DiskLruCache.this.lock;
                DiskLruCache diskLruCache = DiskLruCache.this;
                synchronized (obj2) {
                    if (diskLruCache.initialized && !diskLruCache.closed) {
                        try {
                            diskLruCache.trimToSize();
                        } catch (IOException unused) {
                            diskLruCache.mostRecentTrimFailed = true;
                        }
                        try {
                            if (diskLruCache.journalRewriteRequired()) {
                                diskLruCache.writeJournal();
                            }
                        } catch (IOException unused2) {
                            diskLruCache.mostRecentRebuildFailed = true;
                            diskLruCache.journalWriter = Okio.buffer(Okio.blackhole());
                        }
                        Unit unit = Unit.INSTANCE;
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final void launchCleanup() {
        BuildersKt__Builders_commonKt.launch$default(this.cleanupScope, null, null, new AnonymousClass1(null), 3, null);
    }

    private final void validateKey(String key) {
        if (LEGAL_KEY_PATTERN.matches(key)) {
            return;
        }
        throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + key + '\"').toString());
    }

    /* JADX INFO: compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0013\u0012\n\u0010\u0003\u001a\u00060\u0004R\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\f\u0010\u0012\u001a\b\u0018\u00010\u0013R\u00020\u0005R\u0015\u0010\u0003\u001a\u00060\u0004R\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcoil3/disk/DiskLruCache$Snapshot;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "entry", "Lcoil3/disk/DiskLruCache$Entry;", "Lcoil3/disk/DiskLruCache;", "<init>", "(Lcoil3/disk/DiskLruCache;Lcoil3/disk/DiskLruCache$Entry;)V", "getEntry", "()Lcoil3/disk/DiskLruCache$Entry;", "closed", "", coil3.util.UtilsKt.SCHEME_FILE, "Lokio/Path;", "index", "", "close", "", "closeAndEdit", "Lcoil3/disk/DiskLruCache$Editor;", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class Snapshot implements AutoCloseable {
        private boolean closed;
        private final Entry entry;

        public Snapshot(Entry entry) {
            this.entry = entry;
        }

        public final Entry getEntry() {
            return this.entry;
        }

        public final Path file(int index) {
            if (this.closed) {
                throw new IllegalStateException("snapshot is closed".toString());
            }
            return this.entry.getCleanFiles().get(index);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            Object obj = DiskLruCache.this.lock;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (obj) {
                Entry entry = this.entry;
                entry.setLockingSnapshotCount(entry.getLockingSnapshotCount() - 1);
                if (this.entry.getLockingSnapshotCount() == 0 && this.entry.getZombie()) {
                    diskLruCache.removeEntry(this.entry);
                }
                Unit unit = Unit.INSTANCE;
            }
        }

        public final Editor closeAndEdit() {
            Editor editorEdit;
            Object obj = DiskLruCache.this.lock;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (obj) {
                close();
                editorEdit = diskLruCache.edit(this.entry.getKey());
            }
            return editorEdit;
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0014J\f\u0010\u0016\u001a\b\u0018\u00010\u0017R\u00020\u0004J\u0006\u0010\u0018\u001a\u00020\u0014J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\nH\u0002R\u0015\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcoil3/disk/DiskLruCache$Editor;", "", "entry", "Lcoil3/disk/DiskLruCache$Entry;", "Lcoil3/disk/DiskLruCache;", "<init>", "(Lcoil3/disk/DiskLruCache;Lcoil3/disk/DiskLruCache$Entry;)V", "getEntry", "()Lcoil3/disk/DiskLruCache$Entry;", "closed", "", "written", "", "getWritten", "()[Z", coil3.util.UtilsKt.SCHEME_FILE, "Lokio/Path;", "index", "", "detach", "", "commit", "commitAndGet", "Lcoil3/disk/DiskLruCache$Snapshot;", "abort", "complete", "success", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class Editor {
        private boolean closed;
        private final Entry entry;
        private final boolean[] written;

        public Editor(Entry entry) {
            this.entry = entry;
            this.written = new boolean[DiskLruCache.this.valueCount];
        }

        public final Entry getEntry() {
            return this.entry;
        }

        public final boolean[] getWritten() {
            return this.written;
        }

        public final Path file(int index) {
            Path path;
            Object obj = DiskLruCache.this.lock;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (obj) {
                if (this.closed) {
                    throw new IllegalStateException("editor is closed".toString());
                }
                this.written[index] = true;
                Path path2 = this.entry.getDirtyFiles().get(index);
                FileSystemsKt.createFile$default(diskLruCache.fileSystem, path2, false, 2, null);
                path = path2;
            }
            return path;
        }

        public final void detach() {
            if (Intrinsics.areEqual(this.entry.getCurrentEditor(), this)) {
                this.entry.setZombie(true);
            }
        }

        public final void commit() {
            complete(true);
        }

        public final Snapshot commitAndGet() {
            Snapshot snapshot;
            Object obj = DiskLruCache.this.lock;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (obj) {
                commit();
                snapshot = diskLruCache.get(this.entry.getKey());
            }
            return snapshot;
        }

        public final void abort() {
            complete(false);
        }

        private final void complete(boolean success) {
            Object obj = DiskLruCache.this.lock;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (obj) {
                if (this.closed) {
                    throw new IllegalStateException("editor is closed".toString());
                }
                if (Intrinsics.areEqual(this.entry.getCurrentEditor(), this)) {
                    diskLruCache.completeEdit(this, success);
                }
                this.closed = true;
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030-J\u000e\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u000200J\f\u00101\u001a\b\u0018\u000102R\u00020\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR!\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R!\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R \u0010\u001d\u001a\b\u0018\u00010\u001eR\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u00063"}, d2 = {"Lcoil3/disk/DiskLruCache$Entry;", "", "key", "", "<init>", "(Lcoil3/disk/DiskLruCache;Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "lengths", "", "getLengths", "()[J", "cleanFiles", "Ljava/util/ArrayList;", "Lokio/Path;", "Lkotlin/collections/ArrayList;", "getCleanFiles", "()Ljava/util/ArrayList;", "dirtyFiles", "getDirtyFiles", "readable", "", "getReadable", "()Z", "setReadable", "(Z)V", "zombie", "getZombie", "setZombie", "currentEditor", "Lcoil3/disk/DiskLruCache$Editor;", "Lcoil3/disk/DiskLruCache;", "getCurrentEditor", "()Lcoil3/disk/DiskLruCache$Editor;", "setCurrentEditor", "(Lcoil3/disk/DiskLruCache$Editor;)V", "lockingSnapshotCount", "", "getLockingSnapshotCount", "()I", "setLockingSnapshotCount", "(I)V", "setLengths", "", "strings", "", "writeLengths", "writer", "Lokio/BufferedSink;", "snapshot", "Lcoil3/disk/DiskLruCache$Snapshot;", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class Entry {
        private final ArrayList<Path> cleanFiles;
        private Editor currentEditor;
        private final ArrayList<Path> dirtyFiles;
        private final String key;
        private final long[] lengths;
        private int lockingSnapshotCount;
        private boolean readable;
        private boolean zombie;

        public Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new ArrayList<>(DiskLruCache.this.valueCount);
            this.dirtyFiles = new ArrayList<>(DiskLruCache.this.valueCount);
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            int i = DiskLruCache.this.valueCount;
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(i2);
                this.cleanFiles.add(DiskLruCache.this.directory.resolve(sb.toString()));
                sb.append(".tmp");
                this.dirtyFiles.add(DiskLruCache.this.directory.resolve(sb.toString()));
                sb.setLength(length);
            }
        }

        public final String getKey() {
            return this.key;
        }

        public final long[] getLengths() {
            return this.lengths;
        }

        public final ArrayList<Path> getCleanFiles() {
            return this.cleanFiles;
        }

        public final ArrayList<Path> getDirtyFiles() {
            return this.dirtyFiles;
        }

        public final boolean getReadable() {
            return this.readable;
        }

        public final void setReadable(boolean z) {
            this.readable = z;
        }

        public final boolean getZombie() {
            return this.zombie;
        }

        public final void setZombie(boolean z) {
            this.zombie = z;
        }

        public final Editor getCurrentEditor() {
            return this.currentEditor;
        }

        public final void setCurrentEditor(Editor editor) {
            this.currentEditor = editor;
        }

        public final int getLockingSnapshotCount() {
            return this.lockingSnapshotCount;
        }

        public final void setLockingSnapshotCount(int i) {
            this.lockingSnapshotCount = i;
        }

        public final void setLengths(List<String> strings) throws IOException {
            if (strings.size() != DiskLruCache.this.valueCount) {
                throw new IOException("unexpected journal line: " + strings);
            }
            try {
                int size = strings.size();
                for (int i = 0; i < size; i++) {
                    this.lengths[i] = Long.parseLong(strings.get(i));
                }
            } catch (NumberFormatException unused) {
                throw new IOException("unexpected journal line: " + strings);
            }
        }

        public final void writeLengths(BufferedSink writer) throws IOException {
            for (long j : this.lengths) {
                writer.writeByte(32).writeDecimalLong(j);
            }
        }

        public final Snapshot snapshot() {
            if (!this.readable || this.currentEditor != null || this.zombie) {
                return null;
            }
            ArrayList<Path> arrayList = this.cleanFiles;
            DiskLruCache diskLruCache = DiskLruCache.this;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (!diskLruCache.fileSystem.exists(arrayList.get(i))) {
                    try {
                        diskLruCache.removeEntry(this);
                    } catch (IOException unused) {
                    }
                    return null;
                }
            }
            this.lockingSnapshotCount++;
            return DiskLruCache.this.new Snapshot(this);
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u0014\u0010\u0007\u001a\u00020\u0005X\u0080T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003R\u0014\u0010\t\u001a\u00020\u0005X\u0080T¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003R\u0014\u0010\u000b\u001a\u00020\u0005X\u0080T¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003R\u0014\u0010\r\u001a\u00020\u0005X\u0080T¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcoil3/disk/DiskLruCache$Companion;", "", "<init>", "()V", "JOURNAL_FILE", "", "getJOURNAL_FILE$coil_core_release$annotations", "JOURNAL_FILE_TMP", "getJOURNAL_FILE_TMP$coil_core_release$annotations", "JOURNAL_FILE_BACKUP", "getJOURNAL_FILE_BACKUP$coil_core_release$annotations", "MAGIC", "getMAGIC$coil_core_release$annotations", "VERSION", "getVERSION$coil_core_release$annotations", DiskLruCache.CLEAN, DiskLruCache.DIRTY, DiskLruCache.REMOVE, DiskLruCache.READ, "LEGAL_KEY_PATTERN", "Lkotlin/text/Regex;", "coil-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getJOURNAL_FILE$coil_core_release$annotations() {
        }

        public static /* synthetic */ void getJOURNAL_FILE_BACKUP$coil_core_release$annotations() {
        }

        public static /* synthetic */ void getJOURNAL_FILE_TMP$coil_core_release$annotations() {
        }

        public static /* synthetic */ void getMAGIC$coil_core_release$annotations() {
        }

        public static /* synthetic */ void getVERSION$coil_core_release$annotations() {
        }

        private Companion() {
        }
    }
}
