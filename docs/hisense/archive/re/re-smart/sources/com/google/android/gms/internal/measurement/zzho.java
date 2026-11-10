package com.google.android.gms.internal.measurement;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class zzho extends zzhu<Boolean> {
    public zzho(zzhr zzhrVar, String str, Boolean bool, boolean z) {
        super(zzhrVar, str, bool, true, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzhu
    public final /* bridge */ /* synthetic */ Boolean zza(Object obj) {
        if (zzgv.zzc.matcher(obj).matches()) {
            return true;
        }
        if (zzgv.zzd.matcher(obj).matches()) {
            return false;
        }
        String strZzc = super.zzc();
        String str = (String) obj;
        StringBuilder sb = new StringBuilder(str.length() + String.valueOf(strZzc).length() + 28);
        sb.append("Invalid boolean value for ");
        sb.append(strZzc);
        sb.append(": ");
        sb.append(str);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}
