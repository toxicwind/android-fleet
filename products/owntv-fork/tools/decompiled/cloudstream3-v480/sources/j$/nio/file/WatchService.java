package j$.nio.file;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public interface WatchService extends Closeable {

    public final /* synthetic */ class VivifiedWrapper implements WatchService {
        public final /* synthetic */ java.nio.file.WatchService wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.WatchService watchService) {
            this.wrappedValue = watchService;
        }

        public static /* synthetic */ WatchService convert(java.nio.file.WatchService watchService) {
            if (watchService == null) {
                return null;
            }
            return watchService instanceof Wrapper ? WatchService.this : new VivifiedWrapper(watchService);
        }

        @Override // j$.nio.file.WatchService, java.io.Closeable, java.lang.AutoCloseable
        public /* synthetic */ void close() throws IOException {
            this.wrappedValue.close();
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.WatchService watchService = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return watchService.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.WatchService
        public /* synthetic */ WatchKey poll() {
            return WatchKey.VivifiedWrapper.convert(this.wrappedValue.poll());
        }

        @Override // j$.nio.file.WatchService
        public /* synthetic */ WatchKey poll(long j, TimeUnit timeUnit) {
            return WatchKey.VivifiedWrapper.convert(this.wrappedValue.poll(j, timeUnit));
        }

        @Override // j$.nio.file.WatchService
        public /* synthetic */ WatchKey take() {
            return WatchKey.VivifiedWrapper.convert(this.wrappedValue.take());
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.WatchService {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.WatchService convert(WatchService watchService) {
            if (watchService == null) {
                return null;
            }
            return watchService instanceof VivifiedWrapper ? ((VivifiedWrapper) watchService).wrappedValue : watchService.new Wrapper();
        }

        @Override // java.nio.file.WatchService, java.io.Closeable, java.lang.AutoCloseable
        public /* synthetic */ void close() {
            WatchService.this.close();
        }

        public /* synthetic */ boolean equals(Object obj) {
            WatchService watchService = WatchService.this;
            if (obj instanceof Wrapper) {
                obj = WatchService.this;
            }
            return watchService.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return WatchService.this.hashCode();
        }

        @Override // java.nio.file.WatchService
        public /* synthetic */ java.nio.file.WatchKey poll() {
            return WatchKey.Wrapper.convert(WatchService.this.poll());
        }

        @Override // java.nio.file.WatchService
        public /* synthetic */ java.nio.file.WatchKey poll(long j, TimeUnit timeUnit) {
            return WatchKey.Wrapper.convert(WatchService.this.poll(j, timeUnit));
        }

        @Override // java.nio.file.WatchService
        public /* synthetic */ java.nio.file.WatchKey take() {
            return WatchKey.Wrapper.convert(WatchService.this.take());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    WatchKey poll();

    WatchKey poll(long j, TimeUnit timeUnit);

    WatchKey take();
}
