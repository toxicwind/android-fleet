package com.google.firebase.remoteconfig;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.component.AbtComponent;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.remoteconfig.RemoteConfigRegistrar;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class RemoteConfigRegistrar implements ComponentRegistrar {
    public static /* synthetic */ RemoteConfigComponent a(ComponentContainer componentContainer) {
        return new RemoteConfigComponent((Context) componentContainer.get(Context.class), (FirebaseApp) componentContainer.get(FirebaseApp.class), (FirebaseInstallationsApi) componentContainer.get(FirebaseInstallationsApi.class), ((AbtComponent) componentContainer.get(AbtComponent.class)).get("frc"), componentContainer.getProvider(AnalyticsConnector.class));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(RemoteConfigComponent.class).add(Dependency.required(Context.class)).add(Dependency.required(FirebaseApp.class)).add(Dependency.required(FirebaseInstallationsApi.class)).add(Dependency.required(AbtComponent.class)).add(Dependency.optionalProvider(AnalyticsConnector.class)).factory(new ComponentFactory() { // from class: b.e.b.h.l
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return RemoteConfigRegistrar.a(componentContainer);
            }
        }).eagerInDefaultApp().build(), LibraryVersionComponent.create("fire-rc", BuildConfig.VERSION_NAME));
    }
}
