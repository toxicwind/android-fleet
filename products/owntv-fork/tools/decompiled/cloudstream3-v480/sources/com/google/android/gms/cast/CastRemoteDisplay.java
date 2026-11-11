package com.google.android.gms.cast;

import android.content.Context;
import android.view.Display;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzef;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public final class CastRemoteDisplay {

    @Deprecated
    public static final Api<CastRemoteDisplayOptions> API;
    public static final int CONFIGURATION_INTERACTIVE_NONREALTIME = 2;
    public static final int CONFIGURATION_INTERACTIVE_REALTIME = 1;
    public static final int CONFIGURATION_NONINTERACTIVE = 3;

    @Deprecated
    public static final CastRemoteDisplayApi CastRemoteDisplayApi;
    public static final String EXTRA_INT_SESSION_ENDED_STATUS_CODE = "extra_int_session_ended_status_code";
    private static final Api.AbstractClientBuilder zza;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    @Deprecated
    public static final class CastRemoteDisplayOptions implements Api.ApiOptions.HasOptions {
        final CastDevice zza;
        final CastRemoteDisplaySessionCallbacks zzb;
        final int zzc;

        /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
        @Deprecated
        public static final class Builder {
            final CastDevice zza;
            final CastRemoteDisplaySessionCallbacks zzb;
            int zzc;

            public Builder(CastDevice castDevice, CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks) {
                Preconditions.checkNotNull(castDevice, "CastDevice parameter cannot be null");
                this.zza = castDevice;
                this.zzb = castRemoteDisplaySessionCallbacks;
                this.zzc = 2;
            }

            public CastRemoteDisplayOptions build() {
                return new CastRemoteDisplayOptions(this, null);
            }

            public Builder setConfigPreset(int i) {
                this.zzc = i;
                return this;
            }
        }

        /* synthetic */ CastRemoteDisplayOptions(Builder builder, zzx zzxVar) {
            this.zza = builder.zza;
            this.zzb = builder.zzb;
            this.zzc = builder.zzc;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    @Deprecated
    public interface CastRemoteDisplaySessionCallbacks {
        void onRemoteDisplayEnded(Status status);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    @Deprecated
    public interface CastRemoteDisplaySessionResult extends Result {
        Display getPresentationDisplay();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
    public @interface Configuration {
    }

    static {
        zzw zzwVar = new zzw();
        zza = zzwVar;
        Api<CastRemoteDisplayOptions> api = new Api<>("CastRemoteDisplay.API", zzwVar, com.google.android.gms.cast.internal.zzal.zzc);
        API = api;
        CastRemoteDisplayApi = new zzef(api);
    }

    private CastRemoteDisplay() {
    }

    public static CastRemoteDisplayClient getClient(Context context) {
        return new CastRemoteDisplayClient(context);
    }

    @Deprecated
    public static final boolean isRemoteDisplaySdkSupported(Context context) {
        return false;
    }
}
