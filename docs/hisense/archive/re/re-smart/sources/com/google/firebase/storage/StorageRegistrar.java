package com.google.firebase.storage;

import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.storage.StorageRegistrar;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class StorageRegistrar implements ComponentRegistrar {
    public static /* synthetic */ FirebaseStorageComponent a(ComponentContainer componentContainer) {
        return new FirebaseStorageComponent((FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getProvider(InternalAuthProvider.class), componentContainer.getProvider(InternalAppCheckTokenProvider.class));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(FirebaseStorageComponent.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.optionalProvider(InternalAuthProvider.class)).add(Dependency.optionalProvider(InternalAppCheckTokenProvider.class)).factory(new ComponentFactory() { // from class: b.e.b.i.b
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return StorageRegistrar.a(componentContainer);
            }
        }).build(), LibraryVersionComponent.create("fire-gcs", "20.0.0"));
    }
}
