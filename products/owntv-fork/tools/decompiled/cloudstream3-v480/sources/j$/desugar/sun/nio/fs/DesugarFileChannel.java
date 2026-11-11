package j$.desugar.sun.nio.fs;

import j$.nio.channels.DesugarChannels;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import j$.nio.file.StandardOpenOption;
import j$.nio.file.attribute.FileAttribute;
import j$.time.ZoneId;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DesugarFileChannel {

    /* JADX INFO: renamed from: j$.desugar.sun.nio.fs.DesugarFileChannel$0, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass0 {
        public static /* synthetic */ FileChannel m(RandomAccessFile randomAccessFile) {
            return DesugarChannels.convertMaybeLegacyFileChannelFromLibrary(randomAccessFile.getChannel());
        }
    }

    public static FileChannel openEmulatedFileChannel(Path path, Set set, FileAttribute... fileAttributeArr) throws IOException {
        validateOpenOptions(path, set);
        RandomAccessFile randomAccessFile = new RandomAccessFile(path.toFile(), getFileAccessModeText(set));
        if (set.contains(StandardOpenOption.TRUNCATE_EXISTING) && set.contains(StandardOpenOption.WRITE)) {
            randomAccessFile.setLength(0L);
        }
        if (!set.contains(StandardOpenOption.APPEND) && !set.contains(StandardOpenOption.DELETE_ON_CLOSE)) {
            return AnonymousClass0.m(randomAccessFile);
        }
        return WrappedFileChannel.withExtraOptions(AnonymousClass0.m(randomAccessFile), set, path);
    }

    private static void validateOpenOptions(Path path, Set set) throws NoSuchFileException, FileAlreadyExistsException {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((OpenOption) it.next()).getClass();
        }
        if (path.toFile().exists()) {
            if (set.contains(StandardOpenOption.CREATE_NEW) && set.contains(StandardOpenOption.WRITE)) {
                throw new FileAlreadyExistsException(path.toString());
            }
        } else if (!set.contains(StandardOpenOption.CREATE) && !set.contains(StandardOpenOption.CREATE_NEW)) {
            throw new NoSuchFileException(path.toString());
        }
        if (set.contains(StandardOpenOption.READ) && set.contains(StandardOpenOption.APPEND)) {
            ZoneId.AnonymousClass4.m("READ + APPEND not allowed");
        } else if (set.contains(StandardOpenOption.APPEND) && set.contains(StandardOpenOption.TRUNCATE_EXISTING)) {
            ZoneId.AnonymousClass4.m("APPEND + TRUNCATE_EXISTING not allowed");
        }
    }

    private static String getFileAccessModeText(Set set) {
        if (!set.contains(StandardOpenOption.WRITE) && !set.contains(StandardOpenOption.APPEND)) {
            return "r";
        }
        if (set.contains(StandardOpenOption.SYNC)) {
            return "rws";
        }
        if (set.contains(StandardOpenOption.DSYNC)) {
            return "rwd";
        }
        return "rw";
    }

    public static FileChannel wrap(FileChannel fileChannel) {
        return WrappedFileChannel.wrap(fileChannel);
    }

    static class WrappedFileChannel extends FileChannel implements SeekableByteChannel {
        final boolean appendMode;
        final FileChannel delegate;
        final boolean deleteOnClose;
        final Path path;

        public static FileChannel wrap(FileChannel fileChannel) {
            return fileChannel instanceof WrappedFileChannel ? fileChannel : new WrappedFileChannel(fileChannel, false, false, null);
        }

        public static FileChannel withExtraOptions(FileChannel fileChannel, Set set, Path path) {
            if (fileChannel instanceof WrappedFileChannel) {
                fileChannel = ((WrappedFileChannel) fileChannel).delegate;
            }
            return new WrappedFileChannel(fileChannel, set.contains(StandardOpenOption.DELETE_ON_CLOSE), set.contains(StandardOpenOption.APPEND), path);
        }

        private WrappedFileChannel(FileChannel fileChannel, boolean z, boolean z2, Path path) {
            this.delegate = fileChannel;
            this.deleteOnClose = z;
            this.appendMode = z2;
            this.path = z ? path : null;
        }

        @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
        public int read(ByteBuffer byteBuffer) {
            return this.delegate.read(byteBuffer);
        }

        @Override // java.nio.channels.FileChannel, java.nio.channels.ScatteringByteChannel
        public long read(ByteBuffer[] byteBufferArr, int i, int i2) {
            return this.delegate.read(byteBufferArr, i, i2);
        }

        @Override // java.nio.channels.FileChannel, java.nio.channels.WritableByteChannel
        public int write(ByteBuffer byteBuffer) {
            boolean z = this.appendMode;
            FileChannel fileChannel = this.delegate;
            if (z) {
                return fileChannel.write(byteBuffer, size());
            }
            return fileChannel.write(byteBuffer);
        }

        @Override // java.nio.channels.FileChannel, java.nio.channels.GatheringByteChannel
        public long write(ByteBuffer[] byteBufferArr, int i, int i2) {
            return this.delegate.write(byteBufferArr, i, i2);
        }

        @Override // java.nio.channels.FileChannel
        public long position() {
            return this.delegate.position();
        }

        @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
        public FileChannel position(long j) {
            return wrap(this.delegate.position(j));
        }

        @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
        public long size() {
            return this.delegate.size();
        }

        @Override // java.nio.channels.FileChannel
        public FileChannel truncate(long j) {
            return wrap(this.delegate.truncate(j));
        }

        @Override // java.nio.channels.FileChannel
        public void force(boolean z) throws IOException {
            this.delegate.force(z);
        }

        @Override // java.nio.channels.FileChannel
        public long transferTo(long j, long j2, WritableByteChannel writableByteChannel) {
            return this.delegate.transferTo(j, j2, writableByteChannel);
        }

        @Override // java.nio.channels.FileChannel
        public long transferFrom(ReadableByteChannel readableByteChannel, long j, long j2) {
            return this.delegate.transferFrom(readableByteChannel, j, j2);
        }

        @Override // java.nio.channels.FileChannel
        public int read(ByteBuffer byteBuffer, long j) {
            return this.delegate.read(byteBuffer, j);
        }

        @Override // java.nio.channels.FileChannel
        public int write(ByteBuffer byteBuffer, long j) {
            return this.delegate.write(byteBuffer, j);
        }

        @Override // java.nio.channels.FileChannel
        public MappedByteBuffer map(FileChannel.MapMode mapMode, long j, long j2) {
            return this.delegate.map(mapMode, j, j2);
        }

        @Override // java.nio.channels.FileChannel
        public FileLock lock(long j, long j2, boolean z) {
            return wrapLock(this.delegate.lock(j, j2, z));
        }

        @Override // java.nio.channels.FileChannel
        public FileLock tryLock(long j, long j2, boolean z) {
            return wrapLock(this.delegate.tryLock(j, j2, z));
        }

        private FileLock wrapLock(FileLock fileLock) {
            if (fileLock == null) {
                return null;
            }
            return new WrappedFileChannelFileLock(fileLock, this);
        }

        @Override // java.nio.channels.spi.AbstractInterruptibleChannel
        public void implCloseChannel() {
            this.delegate.close();
            if (this.deleteOnClose) {
                this.path.toFile().delete();
            }
        }
    }

    static class WrappedFileChannelFileLock extends FileLock {
        private final FileLock delegate;

        WrappedFileChannelFileLock(FileLock fileLock, WrappedFileChannel wrappedFileChannel) {
            super(wrappedFileChannel, fileLock.position(), fileLock.size(), fileLock.isShared());
            this.delegate = fileLock;
        }

        @Override // java.nio.channels.FileLock
        public boolean isValid() {
            return this.delegate.isValid();
        }

        @Override // java.nio.channels.FileLock
        public void release() throws IOException {
            this.delegate.release();
        }
    }
}
