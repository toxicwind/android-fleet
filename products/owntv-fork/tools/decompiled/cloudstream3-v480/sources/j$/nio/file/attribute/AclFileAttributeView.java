package j$.nio.file.attribute;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface AclFileAttributeView extends FileOwnerAttributeView {

    public final /* synthetic */ class VivifiedWrapper implements AclFileAttributeView {
        public final /* synthetic */ java.nio.file.attribute.AclFileAttributeView wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.AclFileAttributeView aclFileAttributeView) {
            this.wrappedValue = aclFileAttributeView;
        }

        public static /* synthetic */ AclFileAttributeView convert(java.nio.file.attribute.AclFileAttributeView aclFileAttributeView) {
            if (aclFileAttributeView == null) {
                return null;
            }
            return aclFileAttributeView instanceof Wrapper ? AclFileAttributeView.this : new VivifiedWrapper(aclFileAttributeView);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.AclFileAttributeView aclFileAttributeView = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return aclFileAttributeView.equals(obj);
        }

        @Override // j$.nio.file.attribute.AclFileAttributeView
        public /* synthetic */ List getAcl() {
            return this.wrappedValue.getAcl();
        }

        @Override // j$.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ UserPrincipal getOwner() {
            return UserPrincipal.VivifiedWrapper.convert(this.wrappedValue.getOwner());
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.AclFileAttributeView, j$.nio.file.attribute.FileOwnerAttributeView, j$.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return this.wrappedValue.name();
        }

        @Override // j$.nio.file.attribute.AclFileAttributeView
        public /* synthetic */ void setAcl(List list) throws IOException {
            this.wrappedValue.setAcl(list);
        }

        @Override // j$.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ void setOwner(UserPrincipal userPrincipal) throws IOException {
            this.wrappedValue.setOwner(UserPrincipal.Wrapper.convert(userPrincipal));
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.AclFileAttributeView {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.AclFileAttributeView convert(AclFileAttributeView aclFileAttributeView) {
            if (aclFileAttributeView == null) {
                return null;
            }
            return aclFileAttributeView instanceof VivifiedWrapper ? ((VivifiedWrapper) aclFileAttributeView).wrappedValue : aclFileAttributeView.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            AclFileAttributeView aclFileAttributeView = AclFileAttributeView.this;
            if (obj instanceof Wrapper) {
                obj = AclFileAttributeView.this;
            }
            return aclFileAttributeView.equals(obj);
        }

        @Override // java.nio.file.attribute.AclFileAttributeView
        public /* synthetic */ List getAcl() {
            return AclFileAttributeView.this.getAcl();
        }

        @Override // java.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ java.nio.file.attribute.UserPrincipal getOwner() {
            return UserPrincipal.Wrapper.convert(AclFileAttributeView.this.getOwner());
        }

        public /* synthetic */ int hashCode() {
            return AclFileAttributeView.this.hashCode();
        }

        @Override // java.nio.file.attribute.AclFileAttributeView, java.nio.file.attribute.FileOwnerAttributeView, java.nio.file.attribute.AttributeView
        public /* synthetic */ String name() {
            return AclFileAttributeView.this.name();
        }

        @Override // java.nio.file.attribute.AclFileAttributeView
        public /* synthetic */ void setAcl(List list) {
            AclFileAttributeView.this.setAcl(list);
        }

        @Override // java.nio.file.attribute.FileOwnerAttributeView
        public /* synthetic */ void setOwner(java.nio.file.attribute.UserPrincipal userPrincipal) {
            AclFileAttributeView.this.setOwner(UserPrincipal.VivifiedWrapper.convert(userPrincipal));
        }
    }

    List getAcl();

    @Override // j$.nio.file.attribute.FileOwnerAttributeView, j$.nio.file.attribute.AttributeView
    String name();

    void setAcl(List list);
}
