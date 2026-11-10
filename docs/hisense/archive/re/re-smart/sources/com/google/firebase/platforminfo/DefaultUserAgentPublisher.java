package com.google.firebase.platforminfo;

import com.google.common.base.Ascii;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Dependency;
import com.google.firebase.platforminfo.DefaultUserAgentPublisher;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class DefaultUserAgentPublisher implements UserAgentPublisher {
    public final GlobalLibraryVersionRegistrar gamesSDKRegistrar;
    public final String javaSDKVersionUserAgent;

    public DefaultUserAgentPublisher(Set<LibraryVersion> set, GlobalLibraryVersionRegistrar globalLibraryVersionRegistrar) {
        this.javaSDKVersionUserAgent = toUserAgent(set);
        this.gamesSDKRegistrar = globalLibraryVersionRegistrar;
    }

    public static /* synthetic */ UserAgentPublisher a(ComponentContainer componentContainer) {
        return new DefaultUserAgentPublisher(componentContainer.setOf(LibraryVersion.class), GlobalLibraryVersionRegistrar.getInstance());
    }

    public static Component<UserAgentPublisher> component() {
        return Component.builder(UserAgentPublisher.class).add(Dependency.setOf(LibraryVersion.class)).factory(new ComponentFactory() { // from class: b.e.b.g.a
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                return DefaultUserAgentPublisher.a(componentContainer);
            }
        }).build();
    }

    public static String toUserAgent(Set<LibraryVersion> set) {
        StringBuilder sb = new StringBuilder();
        Iterator<LibraryVersion> it = set.iterator();
        while (it.hasNext()) {
            LibraryVersion next = it.next();
            sb.append(next.getLibraryName());
            sb.append('/');
            sb.append(next.getVersion());
            if (it.hasNext()) {
                sb.append(Ascii.CASE_MASK);
            }
        }
        return sb.toString();
    }

    @Override // com.google.firebase.platforminfo.UserAgentPublisher
    public String getUserAgent() {
        if (this.gamesSDKRegistrar.getRegisteredVersions().isEmpty()) {
            return this.javaSDKVersionUserAgent;
        }
        return this.javaSDKVersionUserAgent + Ascii.CASE_MASK + toUserAgent(this.gamesSDKRegistrar.getRegisteredVersions());
    }
}
