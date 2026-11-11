package j$.nio.file.attribute;

/* JADX INFO: loaded from: classes3.dex */
public abstract class UserPrincipalLookupService {

    public final /* synthetic */ class VivifiedWrapper extends UserPrincipalLookupService {
        public final /* synthetic */ java.nio.file.attribute.UserPrincipalLookupService wrappedValue;

        private /* synthetic */ VivifiedWrapper(java.nio.file.attribute.UserPrincipalLookupService userPrincipalLookupService) {
            this.wrappedValue = userPrincipalLookupService;
        }

        public static /* synthetic */ UserPrincipalLookupService convert(java.nio.file.attribute.UserPrincipalLookupService userPrincipalLookupService) {
            if (userPrincipalLookupService == null) {
                return null;
            }
            return userPrincipalLookupService instanceof Wrapper ? UserPrincipalLookupService.this : new VivifiedWrapper(userPrincipalLookupService);
        }

        public /* synthetic */ boolean equals(Object obj) {
            java.nio.file.attribute.UserPrincipalLookupService userPrincipalLookupService = this.wrappedValue;
            if (obj instanceof VivifiedWrapper) {
                obj = ((VivifiedWrapper) obj).wrappedValue;
            }
            return userPrincipalLookupService.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return this.wrappedValue.hashCode();
        }

        @Override // j$.nio.file.attribute.UserPrincipalLookupService
        public /* synthetic */ GroupPrincipal lookupPrincipalByGroupName(String str) {
            return GroupPrincipal.VivifiedWrapper.convert(this.wrappedValue.lookupPrincipalByGroupName(str));
        }

        @Override // j$.nio.file.attribute.UserPrincipalLookupService
        public /* synthetic */ UserPrincipal lookupPrincipalByName(String str) {
            return UserPrincipal.VivifiedWrapper.convert(this.wrappedValue.lookupPrincipalByName(str));
        }
    }

    public final /* synthetic */ class Wrapper extends java.nio.file.attribute.UserPrincipalLookupService {
        private /* synthetic */ Wrapper() {
        }

        public static /* synthetic */ java.nio.file.attribute.UserPrincipalLookupService convert(UserPrincipalLookupService userPrincipalLookupService) {
            if (userPrincipalLookupService == null) {
                return null;
            }
            return userPrincipalLookupService instanceof VivifiedWrapper ? ((VivifiedWrapper) userPrincipalLookupService).wrappedValue : userPrincipalLookupService.new Wrapper();
        }

        public /* synthetic */ boolean equals(Object obj) {
            UserPrincipalLookupService userPrincipalLookupService = UserPrincipalLookupService.this;
            if (obj instanceof Wrapper) {
                obj = UserPrincipalLookupService.this;
            }
            return userPrincipalLookupService.equals(obj);
        }

        public /* synthetic */ int hashCode() {
            return UserPrincipalLookupService.this.hashCode();
        }

        @Override // java.nio.file.attribute.UserPrincipalLookupService
        public /* synthetic */ java.nio.file.attribute.GroupPrincipal lookupPrincipalByGroupName(String str) {
            return GroupPrincipal.Wrapper.convert(UserPrincipalLookupService.this.lookupPrincipalByGroupName(str));
        }

        @Override // java.nio.file.attribute.UserPrincipalLookupService
        public /* synthetic */ java.nio.file.attribute.UserPrincipal lookupPrincipalByName(String str) {
            return UserPrincipal.Wrapper.convert(UserPrincipalLookupService.this.lookupPrincipalByName(str));
        }
    }

    public abstract GroupPrincipal lookupPrincipalByGroupName(String str);

    public abstract UserPrincipal lookupPrincipalByName(String str);

    protected UserPrincipalLookupService() {
    }
}
