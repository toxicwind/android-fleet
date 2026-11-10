package com.google.firebase.abt.component;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.firebase.abt.BuildConfig;
import com.google.firebase.abt.component.AbtRegistrar;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class AbtRegistrar implements ComponentRegistrar {
    public static /* synthetic */ AbtComponent a(ComponentContainer componentContainer) {
        return new AbtComponent((Context) componentContainer.get(Context.class), componentContainer.getProvider(AnalyticsConnector.class));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(AbtComponent.class).add(Dependency.required(Context.class)).add(Dependency.optionalProvider(AnalyticsConnector.class)).factory(new ComponentFactory() { // from class: b.e.b.a.a.a
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return AbtRegistrar.a(componentContainer);
            }
        }).build(), LibraryVersionComponent.create("fire-abt", BuildConfig.VERSION_NAME));
    }
}
