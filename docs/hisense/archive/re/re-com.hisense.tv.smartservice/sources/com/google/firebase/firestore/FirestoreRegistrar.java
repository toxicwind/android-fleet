package com.google.firebase.firestore;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.firestore.FirestoreRegistrar;
import com.google.firebase.firestore.remote.FirebaseClientGrpcMetadataProvider;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class FirestoreRegistrar implements ComponentRegistrar {
    public static /* synthetic */ FirestoreMultiDbComponent a(ComponentContainer componentContainer) {
        return new FirestoreMultiDbComponent((Context) componentContainer.get(Context.class), (FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getDeferred(InternalAuthProvider.class), componentContainer.getDeferred(InternalAppCheckTokenProvider.class), new FirebaseClientGrpcMetadataProvider(componentContainer.getProvider(UserAgentPublisher.class), componentContainer.getProvider(HeartBeatInfo.class), (FirebaseOptions) componentContainer.get(FirebaseOptions.class)));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    @Keep
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(FirestoreMultiDbComponent.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.required(Context.class)).add(Dependency.optionalProvider(HeartBeatInfo.class)).add(Dependency.optionalProvider(UserAgentPublisher.class)).add(Dependency.deferred(InternalAuthProvider.class)).add(Dependency.deferred(InternalAppCheckTokenProvider.class)).add(Dependency.optional(FirebaseOptions.class)).factory(new ComponentFactory() { // from class: b.e.b.d.k
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return FirestoreRegistrar.a(componentContainer);
            }
        }).build(), LibraryVersionComponent.create(FirebaseClientGrpcMetadataProvider.HEART_BEAT_TAG, BuildConfig.VERSION_NAME));
    }
}
