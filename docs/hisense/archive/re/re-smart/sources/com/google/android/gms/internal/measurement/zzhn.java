package com.google.android.gms.internal.measurement;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class zzhn extends zzhu<Long> {
    public zzhn(zzhr zzhrVar, String str, Long l, boolean z) {
        super(zzhrVar, str, l, true, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzhu
    public final /* bridge */ /* synthetic */ Long zza(Object obj) {
        try {
            return Long.valueOf(Long.parseLong((String) obj));
        } catch (NumberFormatException unused) {
            String strZzc = super.zzc();
            String str = (String) obj;
            StringBuilder sb = new StringBuilder(str.length() + String.valueOf(strZzc).length() + 25);
            sb.append("Invalid long value for ");
            sb.append(strZzc);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
