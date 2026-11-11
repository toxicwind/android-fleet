package j$.nio.file.attribute;

import java.security.Principal;
import javax.security.auth.Subject;

/* JADX INFO: loaded from: classes3.dex */
public interface UserPrincipal extends Principal {

    public final /* synthetic */ class VivifiedWrapper implements UserPrincipal {
        public final /* synthetic */ java.nio.file.attribute.UserPrincipal wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.UserPrincipal userPrincipal) {
            this.wrappedValue = userPrincipal;
        }

        public static /* synthetic */ UserPrincipal convert(java.nio.file.attribute.UserPrincipal userPrincipal) {
            if (userPrincipal == null) {
                return null;
            }
            if (userPrincipal instanceof Wrapper) {
                return UserPrincipal.this;
            }
            return userPrincipal instanceof java.nio.file.attribute.GroupPrincipal ? GroupPrincipal.VivifiedWrapper.convert((java.nio.file.attribute.GroupPrincipal) userPrincipal) : new VivifiedWrapper(userPrincipal);
        }

        @Override // java.security.Principal
        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.UserPrincipal userPrincipal = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return userPrincipal.equals(obj);
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

    public final /* synthetic */ class Wrapper implements java.nio.file.attribute.UserPrincipal {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.UserPrincipal convert(UserPrincipal userPrincipal) {
            if (userPrincipal == null) {
                return null;
            }
            if (userPrincipal instanceof VivifiedWrapper) {
                return ((VivifiedWrapper) userPrincipal).wrappedValue;
            }
            return userPrincipal instanceof GroupPrincipal ? GroupPrincipal.Wrapper.convert((GroupPrincipal) userPrincipal) : userPrincipal.new Wrapper();
        }

        @Override // java.security.Principal
        public /* synthetic */ boolean equals(Object obj) {
            UserPrincipal userPrincipal = UserPrincipal.this;
            if (obj instanceof Wrapper) {
                obj = UserPrincipal.this;
            }
            return userPrincipal.equals(obj);
        }

        @Override // java.security.Principal
        public /* synthetic */ String getName() {
            return UserPrincipal.this.getName();
        }

        @Override // java.security.Principal
        public /* synthetic */ int hashCode() {
            return UserPrincipal.this.hashCode();
        }

        @Override // java.security.Principal
        public /* synthetic */ boolean implies(Subject subject) {
            return UserPrincipal.this.implies(subject);
        }

        @Override // java.security.Principal
        public /* synthetic */ String toString() {
            return UserPrincipal.this.toString();
        }
    }
}
