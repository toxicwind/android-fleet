package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import e.b.a;
import e.b.b;
import e.b.d;

/* JADX INFO: loaded from: classes.dex */
public final class zzev {
    public final /* synthetic */ zzfa zza;
    public final String zzb;
    public final Bundle zzc;
    public Bundle zzd;

    public zzev(zzfa zzfaVar, String str, Bundle bundle) {
        this.zza = zzfaVar;
        Preconditions.checkNotEmpty("default_event_parameters");
        this.zzb = "default_event_parameters";
        this.zzc = new Bundle();
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0068  */
    @WorkerThread
    public final Bundle zza() {
        byte b2;
        if (this.zzd == null) {
            String string = this.zza.zza().getString(this.zzb, null);
            if (string != null) {
                try {
                    Bundle bundle = new Bundle();
                    a aVar = new a(string);
                    for (int i = 0; i < aVar.a(); i++) {
                        try {
                            d dVarA = aVar.a(i);
                            String strH = dVarA.h(GoogleApiAvailabilityLight.TRACKING_SOURCE_NOTIFICATION);
                            String strH2 = dVarA.h("t");
                            int iHashCode = strH2.hashCode();
                            if (iHashCode != 100) {
                                if (iHashCode != 108) {
                                    if (iHashCode == 115 && strH2.equals("s")) {
                                        b2 = 0;
                                    } else {
                                        b2 = -1;
                                    }
                                } else if (strH2.equals("l")) {
                                    b2 = 2;
                                } else {
                                    b2 = -1;
                                }
                            } else if (strH2.equals(GoogleApiAvailabilityLight.TRACKING_SOURCE_DIALOG)) {
                                b2 = 1;
                            } else {
                                b2 = -1;
                            }
                            if (b2 == 0) {
                                bundle.putString(strH, dVarA.h("v"));
                            } else if (b2 == 1) {
                                bundle.putDouble(strH, Double.parseDouble(dVarA.h("v")));
                            } else if (b2 != 2) {
                                this.zza.zzs.zzay().zzd().zzb("Unrecognized persisted bundle type. Type", strH2);
                            } else {
                                bundle.putLong(strH, Long.parseLong(dVarA.h("v")));
                            }
                        } catch (b | NumberFormatException unused) {
                            this.zza.zzs.zzay().zzd().zza("Error reading value from SharedPreferences. Value dropped");
                        }
                    }
                    this.zzd = bundle;
                } catch (b unused2) {
                    b.a.a.a.a.a(this.zza.zzs, "Error loading bundle from SharedPreferences. Values will be lost");
                }
            }
            if (this.zzd == null) {
                this.zzd = this.zzc;
            }
        }
        return this.zzd;
    }

    @WorkerThread
    public final void zzb(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        SharedPreferences.Editor editorEdit = this.zza.zza().edit();
        if (bundle.size() == 0) {
            editorEdit.remove(this.zzb);
        } else {
            String str = this.zzb;
            a aVar = new a();
            for (String str2 : bundle.keySet()) {
                Object obj = bundle.get(str2);
                if (obj != null) {
                    try {
                        d dVar = new d();
                        dVar.a(GoogleApiAvailabilityLight.TRACKING_SOURCE_NOTIFICATION, (Object) str2);
                        dVar.a("v", (Object) obj.toString());
                        if (obj instanceof String) {
                            dVar.a("t", (Object) "s");
                        } else if (obj instanceof Long) {
                            dVar.a("t", (Object) "l");
                        } else if (obj instanceof Double) {
                            dVar.a("t", (Object) GoogleApiAvailabilityLight.TRACKING_SOURCE_DIALOG);
                        } else {
                            this.zza.zzs.zzay().zzd().zzb("Cannot serialize bundle value to SharedPreferences. Type", obj.getClass());
                        }
                        aVar.f2161a.add(dVar);
                    } catch (b e2) {
                        b.a.a.a.a.a(this.zza.zzs, "Cannot serialize bundle value to SharedPreferences", e2);
                    }
                }
            }
            editorEdit.putString(str, aVar.toString());
        }
        editorEdit.apply();
        this.zzd = bundle;
    }
}
