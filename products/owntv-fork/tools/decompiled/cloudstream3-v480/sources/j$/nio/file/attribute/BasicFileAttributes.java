package j$.nio.file.attribute;

/* JADX INFO: loaded from: classes3.dex */
public interface BasicFileAttributes {

    public final /* synthetic */ class VivifiedWrapper implements BasicFileAttributes {
        public final /* synthetic */ java.nio.file.attribute.BasicFileAttributes wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.BasicFileAttributes basicFileAttributes) {
            this.wrappedValue = basicFileAttributes;
        }

        public static /* synthetic */ BasicFileAttributes convert(java.nio.file.attribute.BasicFileAttributes basicFileAttributes) {
            if (basicFileAttributes == null) {
                return null;
            }
            if (basicFileAttributes instanceof Wrapper) {
                return BasicFileAttributes.this;
            }
            if (basicFileAttributes instanceof java.nio.file.attribute.DosFileAttributes) {
                return DosFileAttributes.VivifiedWrapper.convert((java.nio.file.attribute.DosFileAttributes) basicFileAttributes);
            }
            return basicFileAttributes instanceof java.nio.file.attribute.PosixFileAttributes ? PosixFileAttributes.VivifiedWrapper.convert((java.nio.file.attribute.PosixFileAttributes) basicFileAttributes) : new VivifiedWrapper(basicFileAttributes);
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ FileTime creationTime() {
            return FileAttributeConversions.convert(this.wrappedValue.creationTime());
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.BasicFileAttributes basicFileAttributes = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return basicFileAttributes.equals(obj);
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ Object fileKey() {
            return this.wrappedValue.fileKey();
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isDirectory() {
            return this.wrappedValue.isDirectory();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isOther() {
            return this.wrappedValue.isOther();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isRegularFile() {
            return this.wrappedValue.isRegularFile();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isSymbolicLink() {
            return this.wrappedValue.isSymbolicLink();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ FileTime lastAccessTime() {
            return FileAttributeConversions.convert(this.wrappedValue.lastAccessTime());
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ FileTime lastModifiedTime() {
            return FileAttributeConversions.convert(this.wrappedValue.lastModifiedTime());
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ long size() {
            return this.wrappedValue.size();
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.BasicFileAttributes {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.BasicFileAttributes convert(BasicFileAttributes basicFileAttributes) {
            if (basicFileAttributes == null) {
                return null;
            }
            if (basicFileAttributes instanceof VivifiedWrapper) {
                return ((VivifiedWrapper) basicFileAttributes).wrappedValue;
            }
            if (basicFileAttributes instanceof DosFileAttributes) {
                return DosFileAttributes.Wrapper.convert((DosFileAttributes) basicFileAttributes);
            }
            return basicFileAttributes instanceof PosixFileAttributes ? PosixFileAttributes.Wrapper.convert((PosixFileAttributes) basicFileAttributes) : basicFileAttributes.new Wrapper();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ java.nio.file.attribute.FileTime creationTime() {
            return FileAttributeConversions.convert(BasicFileAttributes.this.creationTime());
        }

        public /* synthetic */ boolean equals(Object obj) {
            BasicFileAttributes basicFileAttributes = BasicFileAttributes.this;
            if (obj instanceof Wrapper) {
                obj = BasicFileAttributes.this;
            }
            return basicFileAttributes.equals(obj);
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ Object fileKey() {
            return BasicFileAttributes.this.fileKey();
        }

        public /* synthetic */ int hashCode() {
            return BasicFileAttributes.this.hashCode();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isDirectory() {
            return BasicFileAttributes.this.isDirectory();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isOther() {
            return BasicFileAttributes.this.isOther();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isRegularFile() {
            return BasicFileAttributes.this.isRegularFile();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isSymbolicLink() {
            return BasicFileAttributes.this.isSymbolicLink();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ java.nio.file.attribute.FileTime lastAccessTime() {
            return FileAttributeConversions.convert(BasicFileAttributes.this.lastAccessTime());
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ java.nio.file.attribute.FileTime lastModifiedTime() {
            return FileAttributeConversions.convert(BasicFileAttributes.this.lastModifiedTime());
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ long size() {
            return BasicFileAttributes.this.size();
        }
    }

    FileTime creationTime();

    Object fileKey();

    boolean isDirectory();

    boolean isOther();

    boolean isRegularFile();

    boolean isSymbolicLink();

    FileTime lastAccessTime();

    FileTime lastModifiedTime();

    long size();
}
