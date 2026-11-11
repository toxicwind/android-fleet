package j$.nio.file.attribute;

import j$.nio.file.FileApiFlips;
import java.io.IOException;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public interface PosixFileAttributeView extends BasicFileAttributeView, FileOwnerAttributeView {

    public final /* synthetic */ class VivifiedWrapper implements PosixFileAttributeView {
        public final /* synthetic */ java.nio.file.attribute.PosixFileAttributeView wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.PosixFileAttributeView posixFileAttributeView) {
            this.wrappedValue = posixFileAttributeView;
        }

        public static /* synthetic */ PosixFileAttributeView convert(java.nio.file.attribute.PosixFileAttributeView posixFileAttributeView) {
            if (posixFileAttributeView == null) {
                return null;
            }
            return posixFileAttributeView instanceof Wrapper ? PosixFileAttributeView.this : new VivifiedWrapper(posixFileAttributeView);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.PosixFileAttributeView posixFileAttributeView = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return posixFileAttributeView.equals(obj);
        }

        @Override // j$.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ UserPrincipal getOwner() {
            return UserPrincipal.VivifiedWrapper.convert(this.wrappedValue.getOwner());
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.PosixFileAttributeView, j$.nio.file.attribute.BasicFileAttributeView, j$.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return this.wrappedValue.name();
        }

        @Override // j$.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ BasicFileAttributes readAttributes() {
            return BasicFileAttributes.VivifiedWrapper.convert(this.wrappedValue.readAttributes());
        }

        @Override // j$.nio.file.attribute.PosixFileAttributeView, j$.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ PosixFileAttributes readAttributes() {
            return PosixFileAttributes.VivifiedWrapper.convert(this.wrappedValue.readAttributes());
        }

        @Override // j$.nio.file.attribute.PosixFileAttributeView
        public /* synthetic */ void setGroup(GroupPrincipal groupPrincipal) throws IOException {
            this.wrappedValue.setGroup(GroupPrincipal.Wrapper.convert(groupPrincipal));
        }

        @Override // j$.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ void setOwner(UserPrincipal userPrincipal) throws IOException {
            this.wrappedValue.setOwner(UserPrincipal.Wrapper.convert(userPrincipal));
        }

        @Override // j$.nio.file.attribute.PosixFileAttributeView
        public /* synthetic */ void setPermissions(Set set) throws IOException {
            this.wrappedValue.setPermissions(FileApiFlips.flipPosixPermissionSet(set));
        }

        @Override // j$.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ void setTimes(FileTime fileTime, FileTime fileTime2, FileTime fileTime3) throws IOException {
            this.wrappedValue.setTimes(FileAttributeConversions.convert(fileTime), FileAttributeConversions.convert(fileTime2), FileAttributeConversions.convert(fileTime3));
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.PosixFileAttributeView {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.PosixFileAttributeView convert(PosixFileAttributeView posixFileAttributeView) {
            if (posixFileAttributeView == null) {
                return null;
            }
            return posixFileAttributeView instanceof VivifiedWrapper ? ((VivifiedWrapper) posixFileAttributeView).wrappedValue : posixFileAttributeView.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            PosixFileAttributeView posixFileAttributeView = PosixFileAttributeView.this;
            if (obj instanceof Wrapper) {
                obj = PosixFileAttributeView.this;
            }
            return posixFileAttributeView.equals(obj);
        }

        @Override // java.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ java.nio.file.attribute.UserPrincipal getOwner() {
            return UserPrincipal.Wrapper.convert(PosixFileAttributeView.this.getOwner());
        }

        public /* synthetic */ int hashCode() {
            return PosixFileAttributeView.this.hashCode();
        }

        @Override // java.nio.file.attribute.PosixFileAttributeView, java.nio.file.attribute.BasicFileAttributeView, java.nio.file.attribute.AttributeView, java.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ String name() {
            return PosixFileAttributeView.this.name();
        }

        @Override // java.nio.file.attribute.PosixFileAttributeView, java.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ java.nio.file.attribute.BasicFileAttributes readAttributes() {
            return BasicFileAttributes.Wrapper.convert(PosixFileAttributeView.this.readAttributes());
        }

        @Override // java.nio.file.attribute.PosixFileAttributeView, java.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ java.nio.file.attribute.PosixFileAttributes readAttributes() {
            return PosixFileAttributes.Wrapper.convert(PosixFileAttributeView.this.readAttributes());
        }

        @Override // java.nio.file.attribute.PosixFileAttributeView
        public /* synthetic */ void setGroup(java.nio.file.attribute.GroupPrincipal groupPrincipal) {
            PosixFileAttributeView.this.setGroup(GroupPrincipal.VivifiedWrapper.convert(groupPrincipal));
        }

        @Override // java.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ void setOwner(java.nio.file.attribute.UserPrincipal userPrincipal) {
            PosixFileAttributeView.this.setOwner(UserPrincipal.VivifiedWrapper.convert(userPrincipal));
        }

        @Override // java.nio.file.attribute.PosixFileAttributeView
        public /* synthetic */ void setPermissions(Set set) {
            PosixFileAttributeView.this.setPermissions(FileApiFlips.flipPosixPermissionSet(set));
        }

        @Override // java.nio.file.attribute.BasicFileAttributeView
        public /* synthetic */ void setTimes(java.nio.file.attribute.FileTime fileTime, java.nio.file.attribute.FileTime fileTime2, java.nio.file.attribute.FileTime fileTime3) {
            PosixFileAttributeView.this.setTimes(FileAttributeConversions.convert(fileTime), FileAttributeConversions.convert(fileTime2), FileAttributeConversions.convert(fileTime3));
        }
    }

    @Override // j$.nio.file.attribute.BasicFileAttributeView, j$.nio.file.attribute.AttributeView
    String name();

    @Override // j$.nio.file.attribute.BasicFileAttributeView
    PosixFileAttributes readAttributes();

    void setGroup(GroupPrincipal groupPrincipal);

    void setPermissions(Set set);
}
