package com.google.firebase.installations;

import androidx.annotation.Keep;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.installations.FirebaseInstallationsRegistrar;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class FirebaseInstallationsRegistrar implements ComponentRegistrar {
    public static /* synthetic */ FirebaseInstallationsApi a(ComponentContainer componentContainer) {
        return new FirebaseInstallations((FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getProvider(UserAgentPublisher.class), componentContainer.getProvider(HeartBeatInfo.class));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(FirebaseInstallationsApi.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.optionalProvider(HeartBeatInfo.class)).add(Dependency.optionalProvider(UserAgentPublisher.class)).factory(new ComponentFactory() { // from class: b.e.b.f.e
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return FirebaseInstallationsRegistrar.a(componentContainer);
            }
        }).build(), LibraryVersionComponent.create("fire-installations", "17.0.0"));
    }
}
