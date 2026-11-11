package j$.nio.file.attribute;

/* JADX INFO: loaded from: classes3.dex */
public interface DosFileAttributes extends BasicFileAttributes {

    public final /* synthetic */ class VivifiedWrapper implements DosFileAttributes {
        public final /* synthetic */ java.nio.file.attribute.DosFileAttributes wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.DosFileAttributes dosFileAttributes) {
            this.wrappedValue = dosFileAttributes;
        }

        public static /* synthetic */ DosFileAttributes convert(java.nio.file.attribute.DosFileAttributes dosFileAttributes) {
            if (dosFileAttributes == null) {
                return null;
            }
            return dosFileAttributes instanceof Wrapper ? DosFileAttributes.this : new VivifiedWrapper(dosFileAttributes);
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ FileTime creationTime() {
            return FileAttributeConversions.convert(this.wrappedValue.creationTime());
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.DosFileAttributes dosFileAttributes = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return dosFileAttributes.equals(obj);
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ Object fileKey() {
            return this.wrappedValue.fileKey();
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.DosFileAttributes
        public /* synthetic */ boolean isArchive() {
            return this.wrappedValue.isArchive();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isDirectory() {
            return this.wrappedValue.isDirectory();
        }

        @Override // j$.nio.file.attribute.DosFileAttributes
        public /* synthetic */ boolean isHidden() {
            return this.wrappedValue.isHidden();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isOther() {
            return this.wrappedValue.isOther();
        }

        @Override // j$.nio.file.attribute.DosFileAttributes
        public /* synthetic */ boolean isReadOnly() {
            return this.wrappedValue.isReadOnly();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isRegularFile() {
            return this.wrappedValue.isRegularFile();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isSymbolicLink() {
            return this.wrappedValue.isSymbolicLink();
        }

        @Override // j$.nio.file.attribute.DosFileAttributes
        public /* synthetic */ boolean isSystem() {
            return this.wrappedValue.isSystem();
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

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.DosFileAttributes {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.DosFileAttributes convert(DosFileAttributes dosFileAttributes) {
            if (dosFileAttributes == null) {
                return null;
            }
            return dosFileAttributes instanceof VivifiedWrapper ? ((VivifiedWrapper) dosFileAttributes).wrappedValue : dosFileAttributes.new Wrapper();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ java.nio.file.attribute.FileTime creationTime() {
            return FileAttributeConversions.convert(DosFileAttributes.this.creationTime());
        }

        public /* synthetic */ boolean equals(Object obj) {
            DosFileAttributes dosFileAttributes = DosFileAttributes.this;
            if (obj instanceof Wrapper) {
                obj = DosFileAttributes.this;
            }
            return dosFileAttributes.equals(obj);
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ Object fileKey() {
            return DosFileAttributes.this.fileKey();
        }

        public /* synthetic */ int hashCode() {
            return DosFileAttributes.this.hashCode();
        }

        @Override // java.nio.file.attribute.DosFileAttributes
        public /* synthetic */ boolean isArchive() {
            return DosFileAttributes.this.isArchive();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isDirectory() {
            return DosFileAttributes.this.isDirectory();
        }

        @Override // java.nio.file.attribute.DosFileAttributes
        public /* synthetic */ boolean isHidden() {
            return DosFileAttributes.this.isHidden();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isOther() {
            return DosFileAttributes.this.isOther();
        }

        @Override // java.nio.file.attribute.DosFileAttributes
        public /* synthetic */ boolean isReadOnly() {
            return DosFileAttributes.this.isReadOnly();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isRegularFile() {
            return DosFileAttributes.this.isRegularFile();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isSymbolicLink() {
            return DosFileAttributes.this.isSymbolicLink();
        }

        @Override // java.nio.file.attribute.DosFileAttributes
        public /* synthetic */ boolean isSystem() {
            return DosFileAttributes.this.isSystem();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ java.nio.file.attribute.FileTime lastAccessTime() {
            return FileAttributeConversions.convert(DosFileAttributes.this.lastAccessTime());
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ java.nio.file.attribute.FileTime lastModifiedTime() {
            return FileAttributeConversions.convert(DosFileAttributes.this.lastModifiedTime());
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ long size() {
            return DosFileAttributes.this.size();
        }
    }

    boolean isArchive();

    boolean isHidden();

    boolean isReadOnly();

    boolean isSystem();
}
