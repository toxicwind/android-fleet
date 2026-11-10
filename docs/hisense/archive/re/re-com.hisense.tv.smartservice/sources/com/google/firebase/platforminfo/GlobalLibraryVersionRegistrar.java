package com.google.firebase.platforminfo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class GlobalLibraryVersionRegistrar {
    public static volatile GlobalLibraryVersionRegistrar INSTANCE;
    public final Set<LibraryVersion> infos = new HashSet();

    public static GlobalLibraryVersionRegistrar getInstance() {
        GlobalLibraryVersionRegistrar globalLibraryVersionRegistrar = INSTANCE;
        if (globalLibraryVersionRegistrar == null) {
            synchronized (GlobalLibraryVersionRegistrar.class) {
                globalLibraryVersionRegistrar = INSTANCE;
                if (globalLibraryVersionRegistrar == null) {
                    globalLibraryVersionRegistrar = new GlobalLibraryVersionRegistrar();
                    INSTANCE = globalLibraryVersionRegistrar;
                }
            }
        }
        return globalLibraryVersionRegistrar;
    }

    public Set<LibraryVersion> getRegisteredVersions() {
        Set<LibraryVersion> setUnmodifiableSet;
        synchronized (this.infos) {
            setUnmodifiableSet = Collections.unmodifiableSet(this.infos);
        }
        return setUnmodifiableSet;
    }

    public void registerVersion(String str, String str2) {
        synchronized (this.infos) {
            this.infos.add(new AutoValue_LibraryVersion(str, str2));
        }
    }
}
