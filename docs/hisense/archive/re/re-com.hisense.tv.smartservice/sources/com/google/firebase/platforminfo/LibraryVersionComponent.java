package com.google.firebase.platforminfo;

import android.content.Context;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Dependency;
import com.google.firebase.platforminfo.LibraryVersionComponent;

/* JADX INFO: loaded from: classes.dex */
public class LibraryVersionComponent {

    public interface VersionExtractor<T> {
        String extract(T t);
    }

    public static /* synthetic */ LibraryVersion a(String str, VersionExtractor versionExtractor, ComponentContainer componentContainer) {
        return new AutoValue_LibraryVersion(str, versionExtractor.extract((Context) componentContainer.get(Context.class)));
    }

    public static Component<?> create(String str, String str2) {
        return Component.intoSet(new AutoValue_LibraryVersion(str, str2), LibraryVersion.class);
    }

    public static Component<?> fromContext(final String str, final VersionExtractor<Context> versionExtractor) {
        return Component.intoSetBuilder(LibraryVersion.class).add(Dependency.required(Context.class)).factory(new ComponentFactory() { // from class: b.e.b.g.b
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return LibraryVersionComponent.a(str, versionExtractor, componentContainer);
            }
        }).build();
    }
}
