package okhttp3.internal.cache;

import b.a.a.a.a;
import com.google.common.net.InetAddresses;
import d.B;
import d.C;
import d.h;
import d.i;
import d.t;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.internal.Util;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;

/* JADX INFO: loaded from: classes2.dex */
public final class DiskLruCache implements Closeable, Flushable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final long ANY_SEQUENCE_NUMBER = -1;
    public static final String CLEAN = "CLEAN";
    public static final String DIRTY = "DIRTY";
    public static final String JOURNAL_FILE = "journal";
    public static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    public static final String JOURNAL_FILE_TEMP = "journal.tmp";
    public static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    public static final String MAGIC = "libcore.io.DiskLruCache";
    public static final String READ = "READ";
    public static final String REMOVE = "REMOVE";
    public static final String VERSION_1 = "1";
    public final int appVersion;
    public boolean closed;
    public final File directory;
    public final Executor executor;
    public final FileSystem fileSystem;
    public boolean hasJournalErrors;
    public boolean initialized;
    public final File journalFile;
    public final File journalFileBackup;
    public final File journalFileTmp;
    public h journalWriter;
    public long maxSize;
    public boolean mostRecentRebuildFailed;
    public boolean mostRecentTrimFailed;
    public int redundantOpCount;
    public final int valueCount;
    public long size = 0;
    public final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    public long nextSequenceNumber = 0;
    public final Runnable cleanupRunnable = new Runnable() { // from class: okhttp3.internal.cache.DiskLruCache.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (DiskLruCache.this) {
                if ((!DiskLruCache.this.initialized) || DiskLruCache.this.closed) {
                    return;
                }
                try {
                    DiskLruCache.this.trimToSize();
                } catch (IOException unused) {
                    DiskLruCache.this.mostRecentTrimFailed = true;
                }
                try {
                    if (DiskLruCache.this.journalRebuildRequired()) {
                        DiskLruCache.this.rebuildJournal();
                        DiskLruCache.this.redundantOpCount = 0;
                    }
                } catch (IOException unused2) {
                    DiskLruCache.this.mostRecentRebuildFailed = true;
                    DiskLruCache.this.journalWriter = t.a(t.a());
                }
            }
        }
    };

    public final class Editor {
        public boolean done;
        public final Entry entry;
        public final boolean[] written;

        public Editor(Entry entry) {
            this.entry = entry;
            this.written = entry.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }

        public void abort() {
            synchronized (DiskLruCache.this) {
                if (this.done) {
                    throw new IllegalStateException();
                }
                if (this.entry.currentEditor == this) {
                    DiskLruCache.this.completeEdit(this, false);
                }
                this.done = true;
            }
        }

        public void abortUnlessCommitted() {
            synchronized (DiskLruCache.this) {
                if (!this.done && this.entry.currentEditor == this) {
                    try {
                        DiskLruCache.this.completeEdit(this, false);
                    } catch (IOException unused) {
                    }
                }
            }
        }

        public void commit() {
            synchronized (DiskLruCache.this) {
                if (this.done) {
                    throw new IllegalStateException();
                }
                if (this.entry.currentEditor == this) {
                    DiskLruCache.this.completeEdit(this, true);
                }
                this.done = true;
            }
        }

        public void detach() {
            if (this.entry.currentEditor != this) {
                return;
            }
            int i = 0;
            while (true) {
                DiskLruCache diskLruCache = DiskLruCache.this;
                if (i >= diskLruCache.valueCount) {
                    this.entry.currentEditor = null;
                    return;
                } else {
                    try {
                        diskLruCache.fileSystem.delete(this.entry.dirtyFiles[i]);
                    } catch (IOException unused) {
                    }
                    i++;
                }
            }
        }

        public B newSink(int i) {
            synchronized (DiskLruCache.this) {
                if (this.done) {
                    throw new IllegalStateException();
                }
                if (this.entry.currentEditor != this) {
                    return t.a();
                }
                if (!this.entry.readable) {
                    this.written[i] = true;
                }
                try {
                    return new FaultHidingSink(DiskLruCache.this.fileSystem.sink(this.entry.dirtyFiles[i])) { // from class: okhttp3.internal.cache.DiskLruCache.Editor.1
                        @Override // okhttp3.internal.cache.FaultHidingSink
                        public void onException(IOException iOException) {
                            synchronized (DiskLruCache.this) {
                                Editor.this.detach();
                            }
                        }
                    };
                } catch (FileNotFoundException unused) {
                    return t.a();
                }
            }
        }

        public C newSource(int i) {
            synchronized (DiskLruCache.this) {
                if (this.done) {
                    throw new IllegalStateException();
                }
                if (!this.entry.readable || this.entry.currentEditor != this) {
                    return null;
                }
                try {
                    return DiskLruCache.this.fileSystem.source(this.entry.cleanFiles[i]);
                } catch (FileNotFoundException unused) {
                    return null;
                }
            }
        }
    }

    private final class Entry {
        public final File[] cleanFiles;
        public Editor currentEditor;
        public final File[] dirtyFiles;
        public final String key;
        public final long[] lengths;
        public boolean readable;
        public long sequenceNumber;

        public Entry(String str) {
            this.key = str;
            int i = DiskLruCache.this.valueCount;
            this.lengths = new long[i];
            this.cleanFiles = new File[i];
            this.dirtyFiles = new File[i];
            StringBuilder sb = new StringBuilder(str);
            sb.append(InetAddresses.IPV4_DELIMITER);
            int length = sb.length();
            for (int i2 = 0; i2 < DiskLruCache.this.valueCount; i2++) {
                sb.append(i2);
                this.cleanFiles[i2] = new File(DiskLruCache.this.directory, sb.toString());
                sb.append(".tmp");
                this.dirtyFiles[i2] = new File(DiskLruCache.this.directory, sb.toString());
                sb.setLength(length);
            }
        }

        private IOException invalidLengths(String[] strArr) throws IOException {
            StringBuilder sbA = a.a("unexpected journal line: ");
            sbA.append(Arrays.toString(strArr));
            throw new IOException(sbA.toString());
        }

        public void setLengths(String[] strArr) throws IOException {
            if (strArr.length != DiskLruCache.this.valueCount) {
                invalidLengths(strArr);
                throw null;
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.lengths[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    invalidLengths(strArr);
                    throw null;
                }
            }
        }

        public Snapshot snapshot() {
            if (!Thread.holdsLock(DiskLruCache.this)) {
                throw new AssertionError();
            }
            C[] cArr = new C[DiskLruCache.this.valueCount];
            long[] jArr = (long[]) this.lengths.clone();
            for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                try {
                    cArr[i] = DiskLruCache.this.fileSystem.source(this.cleanFiles[i]);
                } catch (FileNotFoundException unused) {
                    for (int i2 = 0; i2 < DiskLruCache.this.valueCount && cArr[i2] != null; i2++) {
                        Util.closeQuietly(cArr[i2]);
                    }
                    try {
                        DiskLruCache.this.removeEntry(this);
                        return null;
                    } catch (IOException unused2) {
                        return null;
                    }
                }
            }
            return DiskLruCache.this.new Snapshot(this.key, this.sequenceNumber, cArr, jArr);
        }

        public void writeLengths(h hVar) {
            for (long j : this.lengths) {
                hVar.writeByte(32).e(j);
            }
        }
    }

    public final class Snapshot implements Closeable {
        public final String key;
        public final long[] lengths;
        public final long sequenceNumber;
        public final C[] sources;

        public Snapshot(String str, long j, C[] cArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.sources = cArr;
            this.lengths = jArr;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (C c2 : this.sources) {
                Util.closeQuietly(c2);
            }
        }

        public Editor edit() {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public long getLength(int i) {
            return this.lengths[i];
        }

        public C getSource(int i) {
            return this.sources[i];
        }

        public String key() {
            return this.key;
        }
    }

    public DiskLruCache(FileSystem fileSystem, File file, int i, int i2, long j, Executor executor) {
        this.fileSystem = fileSystem;
        this.directory = file;
        this.appVersion = i;
        this.journalFile = new File(file, JOURNAL_FILE);
        this.journalFileTmp = new File(file, JOURNAL_FILE_TEMP);
        this.journalFileBackup = new File(file, JOURNAL_FILE_BACKUP);
        this.valueCount = i2;
        this.maxSize = j;
        this.executor = executor;
    }

    private synchronized void checkNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public static DiskLruCache create(FileSystem fileSystem, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 > 0) {
            return new DiskLruCache(fileSystem, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
        }
        throw new IllegalArgumentException("valueCount <= 0");
    }

    private h newJournalWriter() {
        return t.a(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile)) { // from class: okhttp3.internal.cache.DiskLruCache.2
            public static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // okhttp3.internal.cache.FaultHidingSink
            public void onException(IOException iOException) {
                DiskLruCache.this.hasJournalErrors = true;
            }
        });
    }

    private void processJournal() {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            int i = 0;
            if (next.currentEditor == null) {
                while (i < this.valueCount) {
                    this.size += next.lengths[i];
                    i++;
                }
            } else {
                next.currentEditor = null;
                while (i < this.valueCount) {
                    this.fileSystem.delete(next.cleanFiles[i]);
                    this.fileSystem.delete(next.dirtyFiles[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    private void readJournal() {
        i iVarA = t.a(this.fileSystem.source(this.journalFile));
        try {
            String strB = iVarA.b();
            String strB2 = iVarA.b();
            String strB3 = iVarA.b();
            String strB4 = iVarA.b();
            String strB5 = iVarA.b();
            if (!MAGIC.equals(strB) || !"1".equals(strB2) || !Integer.toString(this.appVersion).equals(strB3) || !Integer.toString(this.valueCount).equals(strB4) || !"".equals(strB5)) {
                throw new IOException("unexpected journal header: [" + strB + ", " + strB2 + ", " + strB4 + ", " + strB5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    readJournalLine(iVarA.b());
                    i++;
                } catch (EOFException unused) {
                    this.redundantOpCount = i - this.lruEntries.size();
                    if (iVarA.e()) {
                        this.journalWriter = newJournalWriter();
                    } else {
                        rebuildJournal();
                    }
                    Util.closeQuietly(iVarA);
                    return;
                }
            }
        } catch (Throwable th) {
            Util.closeQuietly(iVarA);
            throw th;
        }
    }

    private void readJournalLine(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException(a.a("unexpected journal line: ", str));
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i);
            if (iIndexOf == 6 && str.startsWith(REMOVE)) {
                this.lruEntries.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iIndexOf2);
        }
        Entry entry = this.lruEntries.get(strSubstring);
        if (entry == null) {
            entry = new Entry(strSubstring);
            this.lruEntries.put(strSubstring, entry);
        }
        if (iIndexOf2 != -1 && iIndexOf == 5 && str.startsWith(CLEAN)) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            entry.readable = true;
            entry.currentEditor = null;
            entry.setLengths(strArrSplit);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == 5 && str.startsWith(DIRTY)) {
            entry.currentEditor = new Editor(entry);
        } else if (iIndexOf2 != -1 || iIndexOf != 4 || !str.startsWith(READ)) {
            throw new IOException(a.a("unexpected journal line: ", str));
        }
    }

    private void validateKey(String str) {
        if (!LEGAL_KEY_PATTERN.matcher(str).matches()) {
            throw new IllegalArgumentException(a.a("keys must match regex [a-z0-9_-]{1,120}: \"", str, "\""));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.initialized && !this.closed) {
            for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
            this.closed = true;
            return;
        }
        this.closed = true;
    }

    public synchronized void completeEdit(Editor editor, boolean z) {
        Entry entry = editor.entry;
        if (entry.currentEditor != editor) {
            throw new IllegalStateException();
        }
        if (z && !entry.readable) {
            for (int i = 0; i < this.valueCount; i++) {
                if (!editor.written[i]) {
                    editor.abort();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                }
                if (!this.fileSystem.exists(entry.dirtyFiles[i])) {
                    editor.abort();
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < this.valueCount; i2++) {
            File file = entry.dirtyFiles[i2];
            if (!z) {
                this.fileSystem.delete(file);
            } else if (this.fileSystem.exists(file)) {
                File file2 = entry.cleanFiles[i2];
                this.fileSystem.rename(file, file2);
                long j = entry.lengths[i2];
                long size = this.fileSystem.size(file2);
                entry.lengths[i2] = size;
                this.size = (this.size - j) + size;
            }
        }
        this.redundantOpCount++;
        entry.currentEditor = null;
        if (entry.readable || z) {
            entry.readable = true;
            this.journalWriter.a(CLEAN).writeByte(32);
            this.journalWriter.a(entry.key);
            entry.writeLengths(this.journalWriter);
            this.journalWriter.writeByte(10);
            if (z) {
                long j2 = this.nextSequenceNumber;
                this.nextSequenceNumber = 1 + j2;
                entry.sequenceNumber = j2;
            }
        } else {
            this.lruEntries.remove(entry.key);
            this.journalWriter.a(REMOVE).writeByte(32);
            this.journalWriter.a(entry.key);
            this.journalWriter.writeByte(10);
        }
        this.journalWriter.flush();
        if (this.size > this.maxSize || journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    public void delete() {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    public Editor edit(String str) {
        return edit(str, -1L);
    }

    public synchronized void evictAll() {
        initialize();
        for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
            removeEntry(entry);
        }
        this.mostRecentTrimFailed = false;
    }

    @Override // java.io.Flushable
    public synchronized void flush() {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            this.journalWriter.flush();
        }
    }

    public synchronized Snapshot get(String str) {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry != null && entry.readable) {
            Snapshot snapshot = entry.snapshot();
            if (snapshot == null) {
                return null;
            }
            this.redundantOpCount++;
            this.journalWriter.a(READ).writeByte(32).a(str).writeByte(10);
            if (journalRebuildRequired()) {
                this.executor.execute(this.cleanupRunnable);
            }
            return snapshot;
        }
        return null;
    }

    public File getDirectory() {
        return this.directory;
    }

    public synchronized long getMaxSize() {
        return this.maxSize;
    }

    public synchronized void initialize() {
        if (this.initialized) {
            return;
        }
        if (this.fileSystem.exists(this.journalFileBackup)) {
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.delete(this.journalFileBackup);
            } else {
                this.fileSystem.rename(this.journalFileBackup, this.journalFile);
            }
        }
        if (this.fileSystem.exists(this.journalFile)) {
            try {
                readJournal();
                processJournal();
                this.initialized = true;
                return;
            } catch (IOException e2) {
                Platform.PLATFORM.log(5, "DiskLruCache " + this.directory + " is corrupt: " + e2.getMessage() + ", removing", e2);
                try {
                    delete();
                    this.closed = false;
                    rebuildJournal();
                    this.initialized = true;
                } catch (Throwable th) {
                    this.closed = false;
                    throw th;
                }
            }
        }
        rebuildJournal();
        this.initialized = true;
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    public boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    public synchronized void rebuildJournal() {
        try {
            if (this.journalWriter != null) {
                this.journalWriter.close();
            }
            h hVarA = t.a(this.fileSystem.sink(this.journalFileTmp));
            try {
                hVarA.a(MAGIC).writeByte(10);
                hVarA.a("1").writeByte(10);
                hVarA.e(this.appVersion).writeByte(10);
                hVarA.e(this.valueCount).writeByte(10);
                hVarA.writeByte(10);
                for (Entry entry : this.lruEntries.values()) {
                    if (entry.currentEditor != null) {
                        hVarA.a(DIRTY).writeByte(32);
                        hVarA.a(entry.key);
                        hVarA.writeByte(10);
                    } else {
                        hVarA.a(CLEAN).writeByte(32);
                        hVarA.a(entry.key);
                        entry.writeLengths(hVarA);
                        hVarA.writeByte(10);
                    }
                }
                hVarA.close();
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.rename(this.journalFile, this.journalFileBackup);
                }
                this.fileSystem.rename(this.journalFileTmp, this.journalFile);
                this.fileSystem.delete(this.journalFileBackup);
                this.journalWriter = newJournalWriter();
                this.hasJournalErrors = false;
                this.mostRecentRebuildFailed = false;
            } catch (Throwable th) {
                hVarA.close();
                throw th;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public synchronized boolean remove(String str) {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (entry == null) {
            return false;
        }
        boolean zRemoveEntry = removeEntry(entry);
        if (zRemoveEntry && this.size <= this.maxSize) {
            this.mostRecentTrimFailed = false;
        }
        return zRemoveEntry;
    }

    public boolean removeEntry(Entry entry) {
        Editor editor = entry.currentEditor;
        if (editor != null) {
            editor.detach();
        }
        for (int i = 0; i < this.valueCount; i++) {
            this.fileSystem.delete(entry.cleanFiles[i]);
            long j = this.size;
            long[] jArr = entry.lengths;
            this.size = j - jArr[i];
            jArr[i] = 0;
        }
        this.redundantOpCount++;
        this.journalWriter.a(REMOVE).writeByte(32).a(entry.key).writeByte(10);
        this.lruEntries.remove(entry.key);
        if (journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }

    public synchronized void setMaxSize(long j) {
        this.maxSize = j;
        if (this.initialized) {
            this.executor.execute(this.cleanupRunnable);
        }
    }

    public synchronized long size() {
        initialize();
        return this.size;
    }

    public synchronized Iterator<Snapshot> snapshots() {
        initialize();
        return new Iterator<Snapshot>() { // from class: okhttp3.internal.cache.DiskLruCache.3
            public final Iterator<Entry> delegate;
            public Snapshot nextSnapshot;
            public Snapshot removeSnapshot;

            {
                this.delegate = new ArrayList(DiskLruCache.this.lruEntries.values()).iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                Snapshot snapshot;
                if (this.nextSnapshot != null) {
                    return true;
                }
                synchronized (DiskLruCache.this) {
                    if (DiskLruCache.this.closed) {
                        return false;
                    }
                    while (this.delegate.hasNext()) {
                        Entry next = this.delegate.next();
                        if (next.readable && (snapshot = next.snapshot()) != null) {
                            this.nextSnapshot = snapshot;
                            return true;
                        }
                    }
                    return false;
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                Snapshot snapshot = this.removeSnapshot;
                if (snapshot == null) {
                    throw new IllegalStateException("remove() before next()");
                }
                try {
                    DiskLruCache.this.remove(snapshot.key);
                } catch (IOException unused) {
                } finally {
                    this.removeSnapshot = null;
                }
            }

            @Override // java.util.Iterator
            public Snapshot next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.removeSnapshot = this.nextSnapshot;
                this.nextSnapshot = null;
                return this.removeSnapshot;
            }
        };
    }

    public void trimToSize() {
        while (this.size > this.maxSize) {
            removeEntry(this.lruEntries.values().iterator().next());
        }
        this.mostRecentTrimFailed = false;
    }

    public synchronized Editor edit(String str, long j) {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = this.lruEntries.get(str);
        if (j != -1 && (entry == null || entry.sequenceNumber != j)) {
            return null;
        }
        if (entry != null && entry.currentEditor != null) {
            return null;
        }
        if (!this.mostRecentTrimFailed && !this.mostRecentRebuildFailed) {
            this.journalWriter.a(DIRTY).writeByte(32).a(str).writeByte(10);
            this.journalWriter.flush();
            if (this.hasJournalErrors) {
                return null;
            }
            if (entry == null) {
                entry = new Entry(str);
                this.lruEntries.put(str, entry);
            }
            Editor editor = new Editor(entry);
            entry.currentEditor = editor;
            return editor;
        }
        this.executor.execute(this.cleanupRunnable);
        return null;
    }
}
