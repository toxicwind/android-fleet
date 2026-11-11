package androidx.media3.session;

import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.MediaSessionCompat;
import com.google.common.base.Preconditions;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class SessionTokenImplLegacy implements SessionToken.SessionTokenImpl {
    private final ComponentName componentName;
    private final Bundle extras;
    private final MediaSessionCompat.Token legacyToken;
    private final String packageName;
    private final int type;
    private final int uid;
    private static final String FIELD_LEGACY_TOKEN = Util.intToStringMaxRadix(0);
    private static final String FIELD_UID = Util.intToStringMaxRadix(1);
    private static final String FIELD_TYPE = Util.intToStringMaxRadix(2);
    private static final String FIELD_COMPONENT_NAME = Util.intToStringMaxRadix(3);
    private static final String FIELD_PACKAGE_NAME = Util.intToStringMaxRadix(4);
    private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(5);

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public int getInterfaceVersion() {
        return 0;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public int getLibraryVersion() {
        return 0;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public boolean isLegacySession() {
        return true;
    }

    public SessionTokenImplLegacy(MediaSessionCompat.Token token, String str, int i, Bundle bundle) {
        this((MediaSessionCompat.Token) Preconditions.checkNotNull(token), i, 100, null, str, (Bundle) Preconditions.checkNotNull(bundle));
    }

    public SessionTokenImplLegacy(ComponentName componentName, int i) {
        this(null, i, 101, (ComponentName) Preconditions.checkNotNull(componentName), componentName.getPackageName(), Bundle.EMPTY);
    }

    private SessionTokenImplLegacy(MediaSessionCompat.Token token, int i, int i2, ComponentName componentName, String str, Bundle bundle) {
        Preconditions.checkArgument((Build.MANUFACTURER.equals("samsung") && Build.VERSION.SDK_INT == 36) || !TextUtils.isEmpty(str));
        this.legacyToken = token;
        this.uid = i;
        this.type = i2;
        this.componentName = componentName;
        this.packageName = str;
        this.extras = bundle;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.type), this.componentName, this.legacyToken);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionTokenImplLegacy)) {
            return false;
        }
        SessionTokenImplLegacy sessionTokenImplLegacy = (SessionTokenImplLegacy) obj;
        int i = this.type;
        if (i != sessionTokenImplLegacy.type) {
            return false;
        }
        if (i == 100) {
            return Objects.equals(this.legacyToken, sessionTokenImplLegacy.legacyToken);
        }
        if (i != 101) {
            return false;
        }
        return Objects.equals(this.componentName, sessionTokenImplLegacy.componentName);
    }

    public String toString() {
        return "SessionToken {legacy, uid=" + this.uid + "}";
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public int getUid() {
        return this.uid;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public String getPackageName() {
        return this.packageName;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public String getServiceName() {
        ComponentName componentName = this.componentName;
        return componentName == null ? "" : componentName.getClassName();
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public ComponentName getComponentName() {
        return this.componentName;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public int getType() {
        return this.type != 101 ? 0 : 2;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public Bundle getExtras() {
        return new Bundle(this.extras);
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public Object getBinder() {
        return this.legacyToken;
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public android.media.session.MediaSession.Token getPlatformToken() {
        MediaSessionCompat.Token token = this.legacyToken;
        if (token == null) {
            return null;
        }
        return token.getToken();
    }

    @Override // androidx.media3.session.SessionToken.SessionTokenImpl
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        String str = FIELD_LEGACY_TOKEN;
        MediaSessionCompat.Token token = this.legacyToken;
        bundle.putBundle(str, token == null ? null : token.toBundle());
        bundle.putInt(FIELD_UID, this.uid);
        bundle.putInt(FIELD_TYPE, this.type);
        bundle.putParcelable(FIELD_COMPONENT_NAME, this.componentName);
        bundle.putString(FIELD_PACKAGE_NAME, this.packageName);
        bundle.putBundle(FIELD_EXTRAS, this.extras);
        return bundle;
    }

    public static SessionTokenImplLegacy fromBundle(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(FIELD_LEGACY_TOKEN);
        MediaSessionCompat.Token tokenFromBundle = bundle2 == null ? null : MediaSessionCompat.Token.fromBundle(bundle2);
        String str = FIELD_UID;
        Preconditions.checkArgument(bundle.containsKey(str), "uid should be set.");
        int i = bundle.getInt(str);
        String str2 = FIELD_TYPE;
        Preconditions.checkArgument(bundle.containsKey(str2), "type should be set.");
        int i2 = bundle.getInt(str2);
        ComponentName componentName = (ComponentName) bundle.getParcelable(FIELD_COMPONENT_NAME);
        String string = bundle.getString(FIELD_PACKAGE_NAME);
        Preconditions.checkArgument(!TextUtils.isEmpty(string), "package name should be set.");
        Bundle bundleConvertToNullIfInvalid = Util.convertToNullIfInvalid(bundle.getBundle(FIELD_EXTRAS));
        if (bundleConvertToNullIfInvalid == null) {
            bundleConvertToNullIfInvalid = Bundle.EMPTY;
        }
        return new SessionTokenImplLegacy(tokenFromBundle, i, i2, componentName, string, bundleConvertToNullIfInvalid);
    }
}
