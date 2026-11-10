package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zaa implements Comparator {
    public static final Comparator zae = new zaa();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((Scope) obj).getScopeUri().compareTo(((Scope) obj2).getScopeUri());
    }
}
