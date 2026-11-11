package androidx.biometric;

import android.graphics.Bitmap;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.cast.MediaTrack;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthenticationRequest.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b&\u0018\u0000 \u00042\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\b"}, d2 = {"Landroidx/biometric/AuthenticationRequest;", "", "<init>", "()V", "Companion", "Biometric", "Credential", "BodyContent", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class AuthenticationRequest {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: AuthenticationRequest.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JF\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\n2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000fH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0010J)\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u00072\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000fH\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Companion;", "", "<init>", "()V", "biometricRequest", "Landroidx/biometric/AuthenticationRequest$Biometric;", "title", "", "authFallbacks", "", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "init", "Lkotlin/Function1;", "Landroidx/biometric/AuthenticationRequest$Biometric$Builder;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;[Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;Lkotlin/jvm/functions/Function1;)Landroidx/biometric/AuthenticationRequest$Biometric;", "credentialRequest", "Landroidx/biometric/AuthenticationRequest$Credential;", "Landroidx/biometric/AuthenticationRequest$Credential$Builder;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Biometric biometricRequest(String title, Biometric.Fallback[] authFallbacks, Function1<? super Biometric.Builder, Unit> init) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(authFallbacks, "authFallbacks");
            Intrinsics.checkNotNullParameter(init, "init");
            Biometric.Builder builder = new Biometric.Builder(title, (Biometric.Fallback[]) Arrays.copyOf(authFallbacks, authFallbacks.length));
            init.invoke(builder);
            return builder.build();
        }

        public final Credential credentialRequest(String title, Function1<? super Credential.Builder, Unit> init) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(init, "init");
            Credential.Builder builder = new Credential.Builder(title);
            init.invoke(builder);
            return builder.build();
        }
    }

    /* JADX INFO: compiled from: AuthenticationRequest.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\u0018\u0000 $2\u00020\u0001:\u0004$%&'Bm\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0003\u0010\u0010\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001eR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0010\u001a\u00020\u00118\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016¨\u0006("}, d2 = {"Landroidx/biometric/AuthenticationRequest$Biometric;", "Landroidx/biometric/AuthenticationRequest;", "title", "", "authFallbacks", "", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "minStrength", "Landroidx/biometric/AuthenticationRequest$Biometric$Strength;", MediaTrack.ROLE_SUBTITLE, "content", "Landroidx/biometric/AuthenticationRequest$BodyContent;", "isConfirmationRequired", "", "logoBitmap", "Landroid/graphics/Bitmap;", "logoRes", "", "logoDescription", "<init>", "(Ljava/lang/String;Ljava/util/List;Landroidx/biometric/AuthenticationRequest$Biometric$Strength;Ljava/lang/String;Landroidx/biometric/AuthenticationRequest$BodyContent;ZLandroid/graphics/Bitmap;ILjava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getAuthFallbacks", "()Ljava/util/List;", "getMinStrength", "()Landroidx/biometric/AuthenticationRequest$Biometric$Strength;", "getSubtitle", "getContent", "()Landroidx/biometric/AuthenticationRequest$BodyContent;", "()Z", "getLogoBitmap", "()Landroid/graphics/Bitmap;", "getLogoRes", "()I", "getLogoDescription", "Companion", "Builder", "Fallback", "Strength", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Biometric extends AuthenticationRequest {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final List<Fallback> authFallbacks;
        private final BodyContent content;
        private final boolean isConfirmationRequired;
        private final Bitmap logoBitmap;
        private final String logoDescription;
        private final int logoRes;
        private final Strength minStrength;
        private final String subtitle;
        private final String title;

        public /* synthetic */ Biometric(String str, List list, Strength strength, String str2, BodyContent bodyContent, boolean z, Bitmap bitmap, int i, String str3, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, list, strength, str2, bodyContent, z, bitmap, i, str3);
        }

        @JvmStatic
        public static final int getMaxFallbackOptions() {
            return INSTANCE.getMaxFallbackOptions();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Biometric(String str, List<? extends Fallback> list, Strength strength, String str2, BodyContent bodyContent, boolean z, Bitmap bitmap, int i, String str3) {
            int i2;
            this.title = str;
            this.authFallbacks = list;
            this.minStrength = strength;
            this.subtitle = str2;
            this.content = bodyContent;
            this.isConfirmationRequired = z;
            this.logoBitmap = bitmap;
            this.logoRes = i;
            this.logoDescription = str3;
            List<? extends Fallback> list2 = list;
            if ((list2 instanceof Collection) && list2.isEmpty()) {
                i2 = 0;
            } else {
                Iterator it = list2.iterator();
                i2 = 0;
                while (it.hasNext()) {
                    if ((((Fallback) it.next()) instanceof Fallback.DeviceCredential) && (i2 = i2 + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            if (!(i2 <= 1)) {
                throw new IllegalArgumentException("At most one DeviceCredential can be provided as a fallback option.".toString());
            }
            int size = this.authFallbacks.size();
            Companion companion = INSTANCE;
            if (size <= companion.getMaxFallbackOptions()) {
                return;
            }
            throw new IllegalArgumentException(("Maximum fallback option count exceeded. A maximum of " + companion.getMaxFallbackOptions() + " fallbacks are allowed.").toString());
        }

        public final String getTitle() {
            return this.title;
        }

        public final List<Fallback> getAuthFallbacks() {
            return this.authFallbacks;
        }

        /* synthetic */ Biometric(String str, List list, Strength.Class2 class2, String str2, BodyContent bodyContent, boolean z, Bitmap bitmap, int i, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, list, (i2 & 4) != 0 ? Strength.Class2.INSTANCE : class2, (i2 & 8) != 0 ? null : str2, (i2 & 16) != 0 ? null : bodyContent, (i2 & 32) != 0 ? true : z, (i2 & 64) != 0 ? null : bitmap, (i2 & 128) != 0 ? 0 : i, (i2 & 256) != 0 ? null : str3);
        }

        public final Strength getMinStrength() {
            return this.minStrength;
        }

        public final String getSubtitle() {
            return this.subtitle;
        }

        public final BodyContent getContent() {
            return this.content;
        }

        /* JADX INFO: renamed from: isConfirmationRequired, reason: from getter */
        public final boolean getIsConfirmationRequired() {
            return this.isConfirmationRequired;
        }

        public final Bitmap getLogoBitmap() {
            return this.logoBitmap;
        }

        public final int getLogoRes() {
            return this.logoRes;
        }

        public final String getLogoDescription() {
            return this.logoDescription;
        }

        /* JADX INFO: compiled from: AuthenticationRequest.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Biometric$Companion;", "", "<init>", "()V", "getMaxFallbackOptions", "", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            public final int getMaxFallbackOptions() {
                return 4;
            }

            private Companion() {
            }
        }

        /* JADX INFO: compiled from: AuthenticationRequest.kt */
        @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\u0017\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\u0018\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u001a\u001a\u00020\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007J\u0012\u0010\u001b\u001a\u00020\u00002\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u0007J\u0012\u0010\u001c\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u0007J\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u00148\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Biometric$Builder;", "", "title", "", "authFallbacks", "", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "<init>", "(Ljava/lang/String;[Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;)V", "", "minStrength", "Landroidx/biometric/AuthenticationRequest$Biometric$Strength;", MediaTrack.ROLE_SUBTITLE, "content", "Landroidx/biometric/AuthenticationRequest$BodyContent;", "isConfirmationRequired", "", "logoBitmap", "Landroid/graphics/Bitmap;", "logoRes", "", "logoDescription", "setMinStrength", "setSubtitle", "setContent", "setIsConfirmationRequired", "setLogoBitmap", "setLogoRes", "setLogoDescription", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Landroidx/biometric/AuthenticationRequest$Biometric;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Builder {
            private final List<Fallback> authFallbacks;
            private BodyContent content;
            private boolean isConfirmationRequired;
            private Bitmap logoBitmap;
            private String logoDescription;
            private int logoRes;
            private Strength minStrength;
            private String subtitle;
            private final String title;

            public Builder(String title, Fallback... authFallbacks) {
                Intrinsics.checkNotNullParameter(title, "title");
                Intrinsics.checkNotNullParameter(authFallbacks, "authFallbacks");
                this.title = title;
                this.authFallbacks = ArraysKt.toList(authFallbacks);
                this.minStrength = Strength.Class2.INSTANCE;
                this.isConfirmationRequired = true;
            }

            public final Builder setMinStrength(Strength minStrength) {
                Intrinsics.checkNotNullParameter(minStrength, "minStrength");
                this.minStrength = minStrength;
                return this;
            }

            public final Builder setSubtitle(String subtitle) {
                this.subtitle = subtitle;
                return this;
            }

            public final Builder setContent(BodyContent content) {
                this.content = content;
                return this;
            }

            public final Builder setIsConfirmationRequired(boolean isConfirmationRequired) {
                this.isConfirmationRequired = isConfirmationRequired;
                return this;
            }

            public final Builder setLogoBitmap(Bitmap logoBitmap) {
                this.logoBitmap = logoBitmap;
                return this;
            }

            public final Builder setLogoRes(int logoRes) {
                this.logoRes = logoRes;
                return this;
            }

            public final Builder setLogoDescription(String logoDescription) {
                this.logoDescription = logoDescription;
                return this;
            }

            public final Biometric build() {
                return new Biometric(this.title, this.authFallbacks, this.minStrength, this.subtitle, this.content, this.isConfirmationRequired, this.logoBitmap, this.logoRes, this.logoDescription, null);
            }
        }

        /* JADX INFO: compiled from: AuthenticationRequest.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\b&\u0018\u0000 \b2\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\t"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "", "<init>", "()V", "DeviceCredential", "CustomOption", "DefaultCancel", "OverriddenDeviceCredential", "Companion", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static abstract class Fallback {
            public static final int ICON_TYPE_ACCOUNT = 2;
            public static final int ICON_TYPE_GENERIC = 3;
            public static final int ICON_TYPE_PASSWORD = 0;
            public static final int ICON_TYPE_QR_CODE = 1;

            public /* synthetic */ Fallback(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Fallback() {
            }

            /* JADX INFO: compiled from: AuthenticationRequest.kt */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$DeviceCredential;", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "<init>", "()V", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class DeviceCredential extends Fallback {
                public static final DeviceCredential INSTANCE = new DeviceCredential();

                private DeviceCredential() {
                    super(null);
                }
            }

            /* JADX INFO: compiled from: AuthenticationRequest.kt */
            @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$CustomOption;", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "text", "", "iconType", "", "<init>", "(Ljava/lang/String;I)V", "getText", "()Ljava/lang/String;", "getIconType", "()I", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class CustomOption extends Fallback {
                private final int iconType;
                private final String text;

                /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
                public CustomOption(String text) {
                    this(text, 0, 2, null);
                    Intrinsics.checkNotNullParameter(text, "text");
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public CustomOption(String text, int i) {
                    super(null);
                    Intrinsics.checkNotNullParameter(text, "text");
                    this.text = text;
                    this.iconType = i;
                }

                public /* synthetic */ CustomOption(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                    this(str, (i2 & 2) != 0 ? 3 : i);
                }

                public final String getText() {
                    return this.text;
                }

                public final int getIconType() {
                    return this.iconType;
                }
            }

            /* JADX INFO: compiled from: AuthenticationRequest.kt */
            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$DefaultCancel;", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "text", "", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class DefaultCancel extends Fallback {
                private final String text;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public DefaultCancel(String text) {
                    super(null);
                    Intrinsics.checkNotNullParameter(text, "text");
                    this.text = text;
                }

                public final String getText() {
                    return this.text;
                }
            }

            /* JADX INFO: compiled from: AuthenticationRequest.kt */
            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Biometric$Fallback$OverriddenDeviceCredential;", "Landroidx/biometric/AuthenticationRequest$Biometric$Fallback;", "text", "", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class OverriddenDeviceCredential extends Fallback {
                private final String text;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public OverriddenDeviceCredential(String text) {
                    super(null);
                    Intrinsics.checkNotNullParameter(text, "text");
                    this.text = text;
                }

                public final String getText() {
                    return this.text;
                }
            }
        }

        /* JADX INFO: compiled from: AuthenticationRequest.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0002\u0007\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0006¨\u0006\t"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Biometric$Strength;", "", "<init>", "()V", "toAuthenticationType", "", "toAuthenticationType$biometric", "Class2", "Class3", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static abstract class Strength {
            public /* synthetic */ Strength(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Strength() {
            }

            /* JADX INFO: compiled from: AuthenticationRequest.kt */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Biometric$Strength$Class2;", "Landroidx/biometric/AuthenticationRequest$Biometric$Strength;", "<init>", "()V", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class Class2 extends Strength {
                public static final Class2 INSTANCE = new Class2();

                private Class2() {
                    super(null);
                }
            }

            /* JADX INFO: compiled from: AuthenticationRequest.kt */
            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Biometric$Strength$Class3;", "Landroidx/biometric/AuthenticationRequest$Biometric$Strength;", "cryptoObject", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "<init>", "(Landroidx/biometric/BiometricPrompt$CryptoObject;)V", "getCryptoObject", "()Landroidx/biometric/BiometricPrompt$CryptoObject;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
            public static final class Class3 extends Strength {
                private final BiometricPrompt.CryptoObject cryptoObject;

                /* JADX WARN: Multi-variable type inference failed */
                public Class3() {
                    this(null, 1, 0 == true ? 1 : 0);
                }

                public Class3(BiometricPrompt.CryptoObject cryptoObject) {
                    super(null);
                    this.cryptoObject = cryptoObject;
                }

                public /* synthetic */ Class3(BiometricPrompt.CryptoObject cryptoObject, int i, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i & 1) != 0 ? null : cryptoObject);
                }

                public final BiometricPrompt.CryptoObject getCryptoObject() {
                    return this.cryptoObject;
                }
            }

            public final int toAuthenticationType$biometric() {
                return this instanceof Class2 ? 255 : 15;
            }
        }
    }

    /* JADX INFO: compiled from: AuthenticationRequest.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0012B/\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Credential;", "Landroidx/biometric/AuthenticationRequest;", "title", "", MediaTrack.ROLE_SUBTITLE, "content", "Landroidx/biometric/AuthenticationRequest$BodyContent;", "cryptoObject", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroidx/biometric/AuthenticationRequest$BodyContent;Landroidx/biometric/BiometricPrompt$CryptoObject;)V", "getTitle", "()Ljava/lang/String;", "getSubtitle", "getContent", "()Landroidx/biometric/AuthenticationRequest$BodyContent;", "getCryptoObject", "()Landroidx/biometric/BiometricPrompt$CryptoObject;", "Builder", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Credential extends AuthenticationRequest {
        private final BodyContent content;
        private final BiometricPrompt.CryptoObject cryptoObject;
        private final String subtitle;
        private final String title;

        public /* synthetic */ Credential(String str, String str2, BodyContent bodyContent, BiometricPrompt.CryptoObject cryptoObject, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, bodyContent, cryptoObject);
        }

        private Credential(String str, String str2, BodyContent bodyContent, BiometricPrompt.CryptoObject cryptoObject) {
            this.title = str;
            this.subtitle = str2;
            this.content = bodyContent;
            this.cryptoObject = cryptoObject;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getSubtitle() {
            return this.subtitle;
        }

        public final BodyContent getContent() {
            return this.content;
        }

        public final BiometricPrompt.CryptoObject getCryptoObject() {
            return this.cryptoObject;
        }

        /* JADX INFO: compiled from: AuthenticationRequest.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\f\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\r\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/biometric/AuthenticationRequest$Credential$Builder;", "", "title", "", "<init>", "(Ljava/lang/String;)V", MediaTrack.ROLE_SUBTITLE, "content", "Landroidx/biometric/AuthenticationRequest$BodyContent;", "cryptoObject", "Landroidx/biometric/BiometricPrompt$CryptoObject;", "setSubtitle", "setContent", "setCryptoObject", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Landroidx/biometric/AuthenticationRequest$Credential;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Builder {
            private BodyContent content;
            private BiometricPrompt.CryptoObject cryptoObject;
            private String subtitle;
            private final String title;

            public Builder(String title) {
                Intrinsics.checkNotNullParameter(title, "title");
                this.title = title;
            }

            public final Builder setSubtitle(String subtitle) {
                this.subtitle = subtitle;
                return this;
            }

            public final Builder setContent(BodyContent content) {
                this.content = content;
                return this;
            }

            public final Builder setCryptoObject(BiometricPrompt.CryptoObject cryptoObject) {
                this.cryptoObject = cryptoObject;
                return this;
            }

            public final Credential build() {
                return new Credential(this.title, this.subtitle, this.content, this.cryptoObject, null);
            }
        }
    }

    /* JADX INFO: compiled from: AuthenticationRequest.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Landroidx/biometric/AuthenticationRequest$BodyContent;", "", "<init>", "()V", "PlainText", "VerticalList", "ContentViewWithMoreOptionsButton", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class BodyContent {
        public /* synthetic */ BodyContent(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private BodyContent() {
        }

        /* JADX INFO: compiled from: AuthenticationRequest.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/biometric/AuthenticationRequest$BodyContent$PlainText;", "Landroidx/biometric/AuthenticationRequest$BodyContent;", "description", "", "<init>", "(Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class PlainText extends BodyContent {
            private final String description;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PlainText(String description) {
                super(null);
                Intrinsics.checkNotNullParameter(description, "description");
                this.description = description;
            }

            public final String getDescription() {
                return this.description;
            }
        }

        /* JADX INFO: compiled from: AuthenticationRequest.kt */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/biometric/AuthenticationRequest$BodyContent$VerticalList;", "Landroidx/biometric/AuthenticationRequest$BodyContent;", "description", "", "items", "", "Landroidx/biometric/PromptContentItem;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getDescription", "()Ljava/lang/String;", "getItems", "()Ljava/util/List;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class VerticalList extends BodyContent {
            private final String description;
            private final List<PromptContentItem> items;

            /* JADX WARN: Multi-variable type inference failed */
            public VerticalList() {
                this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public VerticalList(String str) {
                this(str, null, 2, 0 == true ? 1 : 0);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public VerticalList(String str, List<? extends PromptContentItem> items) {
                super(null);
                Intrinsics.checkNotNullParameter(items, "items");
                this.description = str;
                this.items = items;
            }

            public final String getDescription() {
                return this.description;
            }

            public /* synthetic */ VerticalList(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str, (i & 2) != 0 ? CollectionsKt.emptyList() : list);
            }

            public final List<PromptContentItem> getItems() {
                return this.items;
            }
        }

        /* JADX INFO: compiled from: AuthenticationRequest.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/biometric/AuthenticationRequest$BodyContent$ContentViewWithMoreOptionsButton;", "Landroidx/biometric/AuthenticationRequest$BodyContent;", "description", "", "<init>", "(Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "biometric"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class ContentViewWithMoreOptionsButton extends BodyContent {
            private final String description;

            /* JADX WARN: Multi-variable type inference failed */
            public ContentViewWithMoreOptionsButton() {
                this(null, 1, 0 == true ? 1 : 0);
            }

            public ContentViewWithMoreOptionsButton(String str) {
                super(null);
                this.description = str;
            }

            public /* synthetic */ ContentViewWithMoreOptionsButton(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str);
            }

            public final String getDescription() {
                return this.description;
            }
        }
    }
}
