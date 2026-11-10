package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@ShowFirstParty
public abstract class zac {
    public static final Map<Object, zac> zacm = new WeakHashMap();
    public static final Object sLock = new Object();

    public abstract void remove(int i);
}
