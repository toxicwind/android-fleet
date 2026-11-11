package j$.nio.file.attribute;

import javax.security.auth.Subject;

/* JADX INFO: loaded from: classes3.dex */
public interface GroupPrincipal extends UserPrincipal {

    public final /* synthetic */ class VivifiedWrapper implements GroupPrincipal {
        public final /* synthetic */ java.nio.file.attribute.GroupPrincipal wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.GroupPrincipal groupPrincipal) {
            this.wrappedValue = groupPrincipal;
        }

        public static /* synthetic */ GroupPrincipal convert(java.nio.file.attribute.GroupPrincipal groupPrincipal) {
            if (groupPrincipal == null) {
                return null;
            }
            return groupPrincipal instanceof Wrapper ? GroupPrincipal.this : new VivifiedWrapper(groupPrincipal);
        }

        @Override // java.security.Principal
        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.GroupPrincipal groupPrincipal = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return groupPrincipal.equals(obj);
        }

        @Override // java.security.Principal
        public /* synthetic */ String getName() {
            return this.wrappedValue.getName();
        }

        @Override // java.security.Principal
        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // java.security.Principal
        public /* synthetic */ boolean implies(Subject subject) {
            return this.wrappedValue.implies(subject);
        }

        @Override // java.security.Principal
        public /* synthetic */ String toString() {
            return this.wrappedValue.toString();
        }
    }

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.GroupPrincipal {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.GroupPrincipal convert(GroupPrincipal groupPrincipal) {
            if (groupPrincipal == null) {
                return null;
            }
            return groupPrincipal instanceof VivifiedWrapper ? ((VivifiedWrapper) groupPrincipal).wrappedValue : groupPrincipal.new Wrapper();
        }

        @Override // java.security.Principal
        public /* synthetic */ boolean equals(Object obj) {
            GroupPrincipal groupPrincipal = GroupPrincipal.this;
            if (obj instanceof Wrapper) {
                obj = GroupPrincipal.this;
            }
            return groupPrincipal.equals(obj);
        }

        @Override // java.security.Principal
        public /* synthetic */ String getName() {
            return GroupPrincipal.this.getName();
        }

        @Override // java.security.Principal
        public /* synthetic */ int hashCode() {
            return GroupPrincipal.this.hashCode();
        }

        @Override // java.security.Principal
        public /* synthetic */ boolean implies(Subject subject) {
            return GroupPrincipal.this.implies(subject);
        }

        @Override // java.security.Principal
        public /* synthetic */ String toString() {
            return GroupPrincipal.this.toString();
        }
    }
}
