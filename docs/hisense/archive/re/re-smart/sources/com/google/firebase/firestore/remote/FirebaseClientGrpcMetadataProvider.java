package com.google.firebase.firestore.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import c.a.C0219ea;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import com.google.firebase.platforminfo.UserAgentPublisher;
import com.google.firebase.storage.network.NetworkRequest;

/* JADX INFO: loaded from: classes.dex */
public class FirebaseClientGrpcMetadataProvider implements GrpcMetadataProvider {
    public static final String HEART_BEAT_TAG = "fire-fst";
    public final FirebaseOptions firebaseOptions;
    public final Provider<HeartBeatInfo> heartBeatInfoProvider;
    public final Provider<UserAgentPublisher> userAgentPublisherProvider;
    public static final C0219ea.e<String> HEART_BEAT_HEADER = C0219ea.e.a(FirebaseInstallationServiceClient.HEART_BEAT_HEADER, C0219ea.f1798a);
    public static final C0219ea.e<String> USER_AGENT_HEADER = C0219ea.e.a(FirebaseInstallationServiceClient.USER_AGENT_HEADER, C0219ea.f1798a);
    public static final C0219ea.e<String> GMP_APP_ID_HEADER = C0219ea.e.a(NetworkRequest.X_FIREBASE_GMPID, C0219ea.f1798a);

    public FirebaseClientGrpcMetadataProvider(@NonNull Provider<UserAgentPublisher> provider, @NonNull Provider<HeartBeatInfo> provider2, @Nullable FirebaseOptions firebaseOptions) {
        this.userAgentPublisherProvider = provider;
        this.heartBeatInfoProvider = provider2;
        this.firebaseOptions = firebaseOptions;
    }

    private void maybeAddGmpAppId(@NonNull C0219ea c0219ea) {
        FirebaseOptions firebaseOptions = this.firebaseOptions;
        if (firebaseOptions == null) {
            return;
        }
        String applicationId = firebaseOptions.getApplicationId();
        if (applicationId.length() != 0) {
            c0219ea.a(GMP_APP_ID_HEADER, applicationId);
        }
    }

    @Override // com.google.firebase.firestore.remote.GrpcMetadataProvider
    public void updateMetadata(@NonNull C0219ea c0219ea) {
        if (this.heartBeatInfoProvider.get() == null || this.userAgentPublisherProvider.get() == null) {
            return;
        }
        int code = this.heartBeatInfoProvider.get().getHeartBeatCode(HEART_BEAT_TAG).getCode();
        if (code != 0) {
            c0219ea.a(HEART_BEAT_HEADER, Integer.toString(code));
        }
        c0219ea.a(USER_AGENT_HEADER, this.userAgentPublisherProvider.get().getUserAgent());
        maybeAddGmpAppId(c0219ea);
    }
}
