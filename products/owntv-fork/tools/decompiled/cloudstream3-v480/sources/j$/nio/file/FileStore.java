package j$.nio.file;

import j$.nio.file.attribute.FileStoreAttributeView;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FileStore {

    public final /* synthetic */ class VivifiedWrapper extends FileStore {
        public final /* synthetic */ java.nio.file.FileStore wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.FileStore fileStore) {
            this.wrappedValue = fileStore;
        }

        public static /* synthetic */ FileStore convert(java.nio.file.FileStore fileStore) {
            if (fileStore == null) {
                return null;
            }
            return fileStore instanceof Wrapper ? FileStore.this : new VivifiedWrapper(fileStore);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.FileStore fileStore = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return fileStore.equals(obj);
        }

        @Override // j$.nio.file.FileStore
        public /* synthetic */ Object getAttribute(String str) {
            return this.wrappedValue.getAttribute(str);
        }

        @Override // j$.nio.file.FileStore
        public /* synthetic */ long getBlockSize() {
            return this.wrappedValue.getBlockSize();
        }

        @Override // j$.nio.file.FileStore
        public /* synthetic */ FileStoreAttributeView getFileStoreAttributeView(Class cls) {
            return FileStoreAttributeView.VivifiedWrapper.convert(this.wrappedValue.getFileStoreAttributeView(cls));
        }

        @Override // j$.nio.file.FileStore
        public /* synthetic */ long getTotalSpace() {
            return this.wrappedValue.getTotalSpace();
        }

        @Override // j$.nio.file.FileStore
        public /* synthetic */ long getUnallocatedSpace() {
            return this.wrappedValue.getUnallocatedSpace();
        }

        @Override // j$.nio.file.FileStore
        public /* synthetic */ long getUsableSpace() {
            return this.wrappedValue.getUsableSpace();
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.FileStore
        public /* synthetic */ boolean isReadOnly() {
            return this.wrappedValue.isReadOnly();
        }

        @Override // j$.nio.file.FileStore
        public /* synthetic */ String name() {
            return this.wrappedValue.name();
        }

        @Override // j$.nio.file.FileStore
        public /* synthetic */ boolean supportsFileAttributeView(Class cls) {
            return this.wrappedValue.supportsFileAttributeView(FileApiFlips.flipFileAttributeView(cls));
        }

        @Override // j$.nio.file.FileStore
        public /* synthetic */ boolean supportsFileAttributeView(String str) {
            return this.wrappedValue.supportsFileAttributeView(str);
        }

        @Override // j$.nio.file.FileStore
        public /* synthetic */ String type() {
            return this.wrappedValue.type();
        }
    }

    public final /* synthetic */ class Wrapper extends java.nio.file.FileStore {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.FileStore convert(FileStore fileStore) {
            if (fileStore == null) {
                return null;
            }
            return fileStore instanceof VivifiedWrapper ? ((VivifiedWrapper) fileStore).wrappedValue : fileStore.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            FileStore fileStore = FileStore.this;
            if (obj instanceof Wrapper) {
                obj = FileStore.this;
            }
            return fileStore.equals(obj);
        }

        @Override // java.nio.file.FileStore
        public /* synthetic */ Object getAttribute(String str) {
            return FileStore.this.getAttribute(str);
        }

        @Override // java.nio.file.FileStore
        public /* synthetic */ long getBlockSize() {
            return FileStore.this.getBlockSize();
        }

        @Override // java.nio.file.FileStore
        public /* synthetic */ java.nio.file.attribute.FileStoreAttributeView getFileStoreAttributeView(Class cls) {
            return FileStoreAttributeView.Wrapper.convert(FileStore.this.getFileStoreAttributeView(cls));
        }

        @Override // java.nio.file.FileStore
        public /* synthetic */ long getTotalSpace() {
            return FileStore.this.getTotalSpace();
        }

        @Override // java.nio.file.FileStore
        public /* synthetic */ long getUnallocatedSpace() {
            return FileStore.this.getUnallocatedSpace();
        }

        @Override // java.nio.file.FileStore
        public /* synthetic */ long getUsableSpace() {
            return FileStore.this.getUsableSpace();
        }

        public /* synthetic */ int hashCode() {
            return FileStore.this.hashCode();
        }

        @Override // java.nio.file.FileStore
        public /* synthetic */ boolean isReadOnly() {
            return FileStore.this.isReadOnly();
        }

        @Override // java.nio.file.FileStore
        public /* synthetic */ String name() {
            return FileStore.this.name();
        }

        @Override // java.nio.file.FileStore
        public /* synthetic */ boolean supportsFileAttributeView(Class cls) {
            return FileStore.this.supportsFileAttributeView(FileApiFlips.flipFileAttributeView(cls));
        }

        @Override // java.nio.file.FileStore
        public /* synthetic */ boolean supportsFileAttributeView(String str) {
            return FileStore.this.supportsFileAttributeView(str);
        }

        @Override // java.nio.file.FileStore
        public /* synthetic */ String type() {
            return FileStore.this.type();
        }
    }

    public abstract Object getAttribute(String str);

    public abstract FileStoreAttributeView getFileStoreAttributeView(Class cls);

    public abstract long getTotalSpace();

    public abstract long getUnallocatedSpace();

    public abstract long getUsableSpace();

    public abstract boolean isReadOnly();

    public abstract String name();

    public abstract boolean supportsFileAttributeView(Class cls);

    public abstract boolean supportsFileAttributeView(String str);

    public abstract String type();

    protected FileStore() {
    }

    public long getBlockSize() {
        throw new UnsupportedOperationException();
    }
}
