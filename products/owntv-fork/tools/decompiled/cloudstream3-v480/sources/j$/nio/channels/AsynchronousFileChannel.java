package j$.nio.channels;

import j$.nio.file.attribute.FileAttribute;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileLock;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AsynchronousFileChannel implements AsynchronousChannel {
    private static final FileAttribute[] NO_ATTRIBUTES = new FileAttribute[0];

    public final /* synthetic */ class VivifiedWrapper extends AsynchronousFileChannel {
        public final /* synthetic */ java.nio.channels.AsynchronousFileChannel wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.channels.AsynchronousFileChannel asynchronousFileChannel) {
            this.wrappedValue = asynchronousFileChannel;
        }

        public static /* synthetic */ AsynchronousFileChannel convert(java.nio.channels.AsynchronousFileChannel asynchronousFileChannel) {
            if (asynchronousFileChannel == null) {
                return null;
            }
            return asynchronousFileChannel instanceof Wrapper ? AsynchronousFileChannel.this : new VivifiedWrapper(asynchronousFileChannel);
        }

        @Override // j$.nio.channels.AsynchronousChannel, java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
        public /* synthetic */ void close() throws IOException {
            this.wrappedValue.close();
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.channels.AsynchronousFileChannel asynchronousFileChannel = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return asynchronousFileChannel.equals(obj);
        }

        @Override // j$.nio.channels.AsynchronousFileChannel
        public /* synthetic */ void force(boolean z) throws IOException {
            this.wrappedValue.force(z);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // java.nio.channels.Channel
        public /* synthetic */ boolean isOpen() {
            return this.wrappedValue.isOpen();
        }

        @Override // j$.nio.channels.AsynchronousFileChannel
        public /* synthetic */ Future lock(long j, long j2, boolean z) {
            return this.wrappedValue.lock(j, j2, z);
        }

        @Override // j$.nio.channels.AsynchronousFileChannel
        public /* synthetic */ void lock(long j, long j2, boolean z, Object obj, CompletionHandler completionHandler) {
            this.wrappedValue.lock(j, j2, z, obj, CompletionHandler.Wrapper.convert(completionHandler));
        }

        @Override // j$.nio.channels.AsynchronousFileChannel
        public /* synthetic */ Future read(ByteBuffer byteBuffer, long j) {
            return this.wrappedValue.read(byteBuffer, j);
        }

        @Override // j$.nio.channels.AsynchronousFileChannel
        public /* synthetic */ void read(ByteBuffer byteBuffer, long j, Object obj, CompletionHandler completionHandler) {
            this.wrappedValue.read(byteBuffer, j, obj, CompletionHandler.Wrapper.convert(completionHandler));
        }

        @Override // j$.nio.channels.AsynchronousFileChannel
        public /* synthetic */ long size() {
            return this.wrappedValue.size();
        }

        @Override // j$.nio.channels.AsynchronousFileChannel
        public /* synthetic */ AsynchronousFileChannel truncate(long j) {
            return convert(this.wrappedValue.truncate(j));
        }

        @Override // j$.nio.channels.AsynchronousFileChannel
        public /* synthetic */ FileLock tryLock(long j, long j2, boolean z) {
            return this.wrappedValue.tryLock(j, j2, z);
        }

        @Override // j$.nio.channels.AsynchronousFileChannel
        public /* synthetic */ Future write(ByteBuffer byteBuffer, long j) {
            return this.wrappedValue.write(byteBuffer, j);
        }

        @Override // j$.nio.channels.AsynchronousFileChannel
        public /* synthetic */ void write(ByteBuffer byteBuffer, long j, Object obj, CompletionHandler completionHandler) {
            this.wrappedValue.write(byteBuffer, j, obj, CompletionHandler.Wrapper.convert(completionHandler));
        }
    }

    public final /* synthetic */ class Wrapper extends java.nio.channels.AsynchronousFileChannel {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.channels.AsynchronousFileChannel convert(AsynchronousFileChannel asynchronousFileChannel) {
            if (asynchronousFileChannel == null) {
                return null;
            }
            return asynchronousFileChannel instanceof VivifiedWrapper ? ((VivifiedWrapper) asynchronousFileChannel).wrappedValue : asynchronousFileChannel.new Wrapper();
        }

        @Override // java.nio.channels.AsynchronousChannel, java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
        public /* synthetic */ void close() {
            AsynchronousFileChannel.this.close();
        }

        public /* synthetic */ boolean equals(Object obj) {
            AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.this;
            if (obj instanceof Wrapper) {
                obj = AsynchronousFileChannel.this;
            }
            return asynchronousFileChannel.equals(obj);
        }

        @Override // java.nio.channels.AsynchronousFileChannel
        public /* synthetic */ void force(boolean z) {
            AsynchronousFileChannel.this.force(z);
        }

        public /* synthetic */ int hashCode() {
            return AsynchronousFileChannel.this.hashCode();
        }

        @Override // java.nio.channels.Channel
        public /* synthetic */ boolean isOpen() {
            return AsynchronousFileChannel.this.isOpen();
        }

        @Override // java.nio.channels.AsynchronousFileChannel
        public /* synthetic */ Future lock(long j, long j2, boolean z) {
            return AsynchronousFileChannel.this.lock(j, j2, z);
        }

        @Override // java.nio.channels.AsynchronousFileChannel
        public /* synthetic */ void lock(long j, long j2, boolean z, Object obj, java.nio.channels.CompletionHandler completionHandler) {
            AsynchronousFileChannel.this.lock(j, j2, z, obj, CompletionHandler.VivifiedWrapper.convert(completionHandler));
        }

        @Override // java.nio.channels.AsynchronousFileChannel
        public /* synthetic */ Future read(ByteBuffer byteBuffer, long j) {
            return AsynchronousFileChannel.this.read(byteBuffer, j);
        }

        @Override // java.nio.channels.AsynchronousFileChannel
        public /* synthetic */ void read(ByteBuffer byteBuffer, long j, Object obj, java.nio.channels.CompletionHandler completionHandler) {
            AsynchronousFileChannel.this.read(byteBuffer, j, obj, CompletionHandler.VivifiedWrapper.convert(completionHandler));
        }

        @Override // java.nio.channels.AsynchronousFileChannel
        public /* synthetic */ long size() {
            return AsynchronousFileChannel.this.size();
        }

        @Override // java.nio.channels.AsynchronousFileChannel
        public /* synthetic */ java.nio.channels.AsynchronousFileChannel truncate(long j) {
            return convert(AsynchronousFileChannel.this.truncate(j));
        }

        @Override // java.nio.channels.AsynchronousFileChannel
        public /* synthetic */ FileLock tryLock(long j, long j2, boolean z) {
            return AsynchronousFileChannel.this.tryLock(j, j2, z);
        }

        @Override // java.nio.channels.AsynchronousFileChannel
        public /* synthetic */ Future write(ByteBuffer byteBuffer, long j) {
            return AsynchronousFileChannel.this.write(byteBuffer, j);
        }

        @Override // java.nio.channels.AsynchronousFileChannel
        public /* synthetic */ void write(ByteBuffer byteBuffer, long j, Object obj, java.nio.channels.CompletionHandler completionHandler) {
            AsynchronousFileChannel.this.write(byteBuffer, j, obj, CompletionHandler.VivifiedWrapper.convert(completionHandler));
        }
    }

    public abstract void force(boolean z);

    public abstract Future lock(long j, long j2, boolean z);

    public abstract void lock(long j, long j2, boolean z, Object obj, CompletionHandler completionHandler);

    public abstract Future read(ByteBuffer byteBuffer, long j);

    public abstract void read(ByteBuffer byteBuffer, long j, Object obj, CompletionHandler completionHandler);

    public abstract long size();

    public abstract AsynchronousFileChannel truncate(long j);

    public abstract FileLock tryLock(long j, long j2, boolean z);

    public abstract Future write(ByteBuffer byteBuffer, long j);

    public abstract void write(ByteBuffer byteBuffer, long j, Object obj, CompletionHandler completionHandler);

    protected AsynchronousFileChannel() {
    }
}
