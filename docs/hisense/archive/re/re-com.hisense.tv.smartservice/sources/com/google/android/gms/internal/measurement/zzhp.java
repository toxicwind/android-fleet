package com.google.android.gms.internal.measurement;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class zzhp extends zzhu<Double> {
    public zzhp(zzhr zzhrVar, String str, Double d2, boolean z) {
        super(zzhrVar, "measurement.test.double_flag", d2, true, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzhu
    public final /* bridge */ /* synthetic */ Double zza(Object obj) {
        try {
            return Double.valueOf(Double.parseDouble((String) obj));
        } catch (NumberFormatException unused) {
            String strZzc = super.zzc();
            String str = (String) obj;
            StringBuilder sb = new StringBuilder(str.length() + String.valueOf(strZzc).length() + 27);
            sb.append("Invalid double value for ");
            sb.append(strZzc);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
