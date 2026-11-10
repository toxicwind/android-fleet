package com.google.firebase.components;

import b.a.a.a.a;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DependencyCycleException extends DependencyException {
    public final List<Component<?>> componentsInCycle;

    /* JADX WARN: Illegal instructions before constructor call */
    public DependencyCycleException(List<Component<?>> list) {
        StringBuilder sbA = a.a("Dependency cycle detected: ");
        sbA.append(Arrays.toString(list.toArray()));
        super(sbA.toString());
        this.componentsInCycle = list;
    }

    public List<Component<?>> getComponentsInCycle() {
        return this.componentsInCycle;
    }
}
