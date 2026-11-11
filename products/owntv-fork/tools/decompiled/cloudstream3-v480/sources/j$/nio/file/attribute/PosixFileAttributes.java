package j$.nio.file.attribute;

import j$.nio.file.FileApiFlips;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public interface PosixFileAttributes extends BasicFileAttributes {

    public final /* synthetic */ class VivifiedWrapper implements PosixFileAttributes {
        public final /* synthetic */ java.nio.file.attribute.PosixFileAttributes wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.PosixFileAttributes posixFileAttributes) {
            this.wrappedValue = posixFileAttributes;
        }

        public static /* synthetic */ PosixFileAttributes convert(java.nio.file.attribute.PosixFileAttributes posixFileAttributes) {
            if (posixFileAttributes == null) {
                return null;
            }
            return posixFileAttributes instanceof Wrapper ? PosixFileAttributes.this : new VivifiedWrapper(posixFileAttributes);
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ FileTime creationTime() {
            return FileAttributeConversions.convert(this.wrappedValue.creationTime());
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.PosixFileAttributes posixFileAttributes = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return posixFileAttributes.equals(obj);
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ Object fileKey() {
            return this.wrappedValue.fileKey();
        }

        @Override // j$.nio.file.attribute.PosixFileAttributes
        public /* synthetic */ GroupPrincipal group() {
            return GroupPrincipal.VivifiedWrapper.convert(this.wrappedValue.group());
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

        @Override // j$.nio.file.attribute.PosixFileAttributes
        public /* synthetic */ UserPrincipal owner() {
            return UserPrincipal.VivifiedWrapper.convert(this.wrappedValue.owner());
        }

        @Override // j$.nio.file.attribute.PosixFileAttributes
        public /* synthetic */ Set permissions() {
            return FileApiFlips.flipPosixPermissionSet(this.wrappedValue.permissions());
        }

        @Override // j$.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ long size() {
            return this.wrappedValue.size();
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.PosixFileAttributes {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.PosixFileAttributes convert(PosixFileAttributes posixFileAttributes) {
            if (posixFileAttributes == null) {
                return null;
            }
            return posixFileAttributes instanceof VivifiedWrapper ? ((VivifiedWrapper) posixFileAttributes).wrappedValue : posixFileAttributes.new Wrapper();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ java.nio.file.attribute.FileTime creationTime() {
            return FileAttributeConversions.convert(PosixFileAttributes.this.creationTime());
        }

        public /* synthetic */ boolean equals(Object obj) {
            PosixFileAttributes posixFileAttributes = PosixFileAttributes.this;
            if (obj instanceof Wrapper) {
                obj = PosixFileAttributes.this;
            }
            return posixFileAttributes.equals(obj);
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ Object fileKey() {
            return PosixFileAttributes.this.fileKey();
        }

        @Override // java.nio.file.attribute.PosixFileAttributes
        public /* synthetic */ java.nio.file.attribute.GroupPrincipal group() {
            return GroupPrincipal.Wrapper.convert(PosixFileAttributes.this.group());
        }

        public /* synthetic */ int hashCode() {
            return PosixFileAttributes.this.hashCode();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isDirectory() {
            return PosixFileAttributes.this.isDirectory();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isOther() {
            return PosixFileAttributes.this.isOther();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isRegularFile() {
            return PosixFileAttributes.this.isRegularFile();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ boolean isSymbolicLink() {
            return PosixFileAttributes.this.isSymbolicLink();
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ java.nio.file.attribute.FileTime lastAccessTime() {
            return FileAttributeConversions.convert(PosixFileAttributes.this.lastAccessTime());
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ java.nio.file.attribute.FileTime lastModifiedTime() {
            return FileAttributeConversions.convert(PosixFileAttributes.this.lastModifiedTime());
        }

        @Override // java.nio.file.attribute.PosixFileAttributes
        public /* synthetic */ java.nio.file.attribute.UserPrincipal owner() {
            return UserPrincipal.Wrapper.convert(PosixFileAttributes.this.owner());
        }

        @Override // java.nio.file.attribute.PosixFileAttributes
        public /* synthetic */ Set permissions() {
            return FileApiFlips.flipPosixPermissionSet(PosixFileAttributes.this.permissions());
        }

        @Override // java.nio.file.attribute.BasicFileAttributes
        public /* synthetic */ long size() {
            return PosixFileAttributes.this.size();
        }
    }

    GroupPrincipal group();

    UserPrincipal owner();

    Set permissions();
}
