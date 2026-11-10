package com.ju.lib.datacommunication.network.http.core.C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f79a = new ArrayList();
    private final HashMap b = new HashMap();
    private String c = "application/x-www-form-urlencoded";

    public b c(Map map) {
        this.b.putAll(map);
        for (Map.Entry entry : map.entrySet()) {
            this.f79a.add(new c((String) entry.getKey(), (String) entry.getValue()));
        }
        return this;
    }

    public d d() {
        return new d(this, this.b);
    }
}
