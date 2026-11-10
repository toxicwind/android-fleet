package com.google.android.gms.dynamite;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class zzh implements DynamiteModule.VersionPolicy {
    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) {
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        int iZzb = iVersions.zzb(context, str, false);
        selectionResult.remoteVersion = iZzb;
        if (iZzb == 0) {
            selectionResult.selection = 0;
        } else {
            selectionResult.selection = 1;
        }
        return selectionResult;
    }
}
