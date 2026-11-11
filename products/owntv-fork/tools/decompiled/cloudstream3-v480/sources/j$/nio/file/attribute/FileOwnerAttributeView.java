package j$.nio.file.attribute;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface FileOwnerAttributeView extends FileAttributeView {

    public final /* synthetic */ class VivifiedWrapper implements FileOwnerAttributeView {
        public final /* synthetic */ java.nio.file.attribute.FileOwnerAttributeView wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.FileOwnerAttributeView fileOwnerAttributeView) {
            this.wrappedValue = fileOwnerAttributeView;
        }

        public static /* synthetic */ FileOwnerAttributeView convert(java.nio.file.attribute.FileOwnerAttributeView fileOwnerAttributeView) {
            if (fileOwnerAttributeView == null) {
                return null;
            }
            if (fileOwnerAttributeView instanceof Wrapper) {
                return FileOwnerAttributeView.this;
            }
            if (fileOwnerAttributeView instanceof java.nio.file.attribute.AclFileAttributeView) {
                return AclFileAttributeView.VivifiedWrapper.convert((java.nio.file.attribute.AclFileAttributeView) fileOwnerAttributeView);
            }
            return fileOwnerAttributeView instanceof java.nio.file.attribute.PosixFileAttributeView ? PosixFileAttributeView.VivifiedWrapper.convert((java.nio.file.attribute.PosixFileAttributeView) fileOwnerAttributeView) : new VivifiedWrapper(fileOwnerAttributeView);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.FileOwnerAttributeView fileOwnerAttributeView = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return fileOwnerAttributeView.equals(obj);
        }

        @Override // j$.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ UserPrincipal getOwner() {
            return UserPrincipal.VivifiedWrapper.convert(this.wrappedValue.getOwner());
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.FileOwnerAttributeView, j$.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return this.wrappedValue.name();
        }

        @Override // j$.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ void setOwner(UserPrincipal userPrincipal) throws IOException {
            this.wrappedValue.setOwner(UserPrincipal.Wrapper.convert(userPrincipal));
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.FileOwnerAttributeView {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.FileOwnerAttributeView convert(FileOwnerAttributeView fileOwnerAttributeView) {
            if (fileOwnerAttributeView == null) {
                return null;
            }
            if (fileOwnerAttributeView instanceof VivifiedWrapper) {
                return ((VivifiedWrapper) fileOwnerAttributeView).wrappedValue;
            }
            if (fileOwnerAttributeView instanceof AclFileAttributeView) {
                return AclFileAttributeView.Wrapper.convert((AclFileAttributeView) fileOwnerAttributeView);
            }
            return fileOwnerAttributeView instanceof PosixFileAttributeView ? PosixFileAttributeView.Wrapper.convert((PosixFileAttributeView) fileOwnerAttributeView) : fileOwnerAttributeView.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            FileOwnerAttributeView fileOwnerAttributeView = FileOwnerAttributeView.this;
            if (obj instanceof Wrapper) {
                obj = FileOwnerAttributeView.this;
            }
            return fileOwnerAttributeView.equals(obj);
        }

        @Override // java.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ java.nio.file.attribute.UserPrincipal getOwner() {
            return UserPrincipal.Wrapper.convert(FileOwnerAttributeView.this.getOwner());
        }

        public /* synthetic */ int hashCode() {
            return FileOwnerAttributeView.this.hashCode();
        }

        @Override // java.nio.file.attribute.FileOwnerAttributeView, java.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return FileOwnerAttributeView.this.name();
        }

        @Override // java.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ void setOwner(java.nio.file.attribute.UserPrincipal userPrincipal) {
            FileOwnerAttributeView.this.setOwner(UserPrincipal.VivifiedWrapper.convert(userPrincipal));
        }
    }

    UserPrincipal getOwner();

    @Override // j$.nio.file.attribute.AttributeView
    String name();

    void setOwner(UserPrincipal userPrincipal);
}
