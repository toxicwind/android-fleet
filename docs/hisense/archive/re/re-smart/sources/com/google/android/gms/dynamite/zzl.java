package com.google.android.gms.dynamite;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class zzl implements DynamiteModule.VersionPolicy {
    /* JADX WARN: Code duplicated, block: B:12:0x0028  */
    /* JADX WARN: Code duplicated, block: B:13:0x002b  */
    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.SelectionResult selectModule(Context context, String str, DynamiteModule.VersionPolicy.IVersions iVersions) {
        int iZzb;
        DynamiteModule.VersionPolicy.SelectionResult selectionResult = new DynamiteModule.VersionPolicy.SelectionResult();
        int iZza = iVersions.zza(context, str);
        selectionResult.localVersion = iZza;
        if (iZza != 0) {
            iZzb = iVersions.zzb(context, str, false);
            selectionResult.remoteVersion = iZzb;
        } else {
            iZzb = iVersions.zzb(context, str, true);
            selectionResult.remoteVersion = iZzb;
        }
        int i = selectionResult.localVersion;
        if (i != 0) {
            if (iZzb >= i) {
                selectionResult.selection = 1;
            } else {
                selectionResult.selection = -1;
            }
        } else if (iZzb == 0) {
            selectionResult.selection = 0;
        } else {
            i = 0;
            if (iZzb >= i) {
                selectionResult.selection = 1;
            } else {
                selectionResult.selection = -1;
            }
        }
        return selectionResult;
    }
}
