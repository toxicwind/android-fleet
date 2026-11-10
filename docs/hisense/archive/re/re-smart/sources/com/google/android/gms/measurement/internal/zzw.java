package com.google.android.gms.measurement.internal;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzoa;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class zzw extends zzx {
    public final /* synthetic */ zzz zza;
    public final com.google.android.gms.internal.measurement.zzej zzh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzw(zzz zzzVar, String str, int i, com.google.android.gms.internal.measurement.zzej zzejVar) {
        super(str, i);
        this.zza = zzzVar;
        this.zzh = zzejVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzx
    public final int zza() {
        return this.zzh.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzx
    public final boolean zzb() {
        return this.zzh.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzx
    public final boolean zzc() {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:106:0x02fb  */
    /* JADX WARN: Code duplicated, block: B:108:0x0301  */
    /* JADX WARN: Code duplicated, block: B:109:0x0314  */
    /* JADX WARN: Code duplicated, block: B:111:0x031a  */
    /* JADX WARN: Code duplicated, block: B:113:0x0322  */
    /* JADX WARN: Code duplicated, block: B:116:0x032e  */
    /* JADX WARN: Code duplicated, block: B:122:0x0391 A[EDGE_INSN: B:122:0x0391->B:125:0x03ed BREAK  A[LOOP:0: B:41:0x0124->B:46:0x0159]] */
    /* JADX WARN: Code duplicated, block: B:123:0x03bf A[EDGE_INSN: B:123:0x03bf->B:125:0x03ed BREAK  A[LOOP:0: B:41:0x0124->B:46:0x0159]] */
    /* JADX WARN: Code duplicated, block: B:158:0x0161 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:159:0x013a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:160:0x0201 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:161:0x0184 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:162:0x01a2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:163:0x018a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:164:0x01c0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:165:0x01d2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:166:0x01a8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:168:0x016e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:172:0x03e9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:173:0x022e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:174:0x02a4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:175:0x025b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:176:0x03ed A[EDGE_INSN: B:176:0x03ed->B:125:0x03ed BREAK  A[LOOP:0: B:41:0x0124->B:46:0x0159], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:177:0x029e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:178:0x02ae A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:179:0x03ed A[EDGE_INSN: B:179:0x03ed->B:125:0x03ed BREAK  A[LOOP:0: B:41:0x0124->B:46:0x0159], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:180:0x02f1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:181:0x0255 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:182:0x02f7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:183:0x02a8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:184:0x038f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:185:0x0365 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:186:0x033a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:187:0x03ed A[EDGE_INSN: B:187:0x03ed->B:125:0x03ed BREAK  A[LOOP:0: B:41:0x0124->B:46:0x0159], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:188:0x0334 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:190:0x0209 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:191:0x0209 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:192:0x0209 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:40:0x0117  */
    /* JADX WARN: Code duplicated, block: B:43:0x012a  */
    /* JADX WARN: Code duplicated, block: B:46:0x0159 A[LOOP:0: B:41:0x0124->B:46:0x0159, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:50:0x0174  */
    /* JADX WARN: Code duplicated, block: B:56:0x0194  */
    /* JADX WARN: Code duplicated, block: B:57:0x019d  */
    /* JADX WARN: Code duplicated, block: B:63:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:64:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:68:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:73:0x020f  */
    /* JADX WARN: Code duplicated, block: B:78:0x0223  */
    /* JADX WARN: Code duplicated, block: B:82:0x024d  */
    /* JADX WARN: Code duplicated, block: B:87:0x0286  */
    /* JADX WARN: Code duplicated, block: B:90:0x0298  */
    /* JADX WARN: Code duplicated, block: B:98:0x02d9  */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean zzd(Long l, Long l2, com.google.android.gms.internal.measurement.zzfo zzfoVar, long j, zzap zzapVar, boolean z) {
        HashSet hashSet;
        Iterator<com.google.android.gms.internal.measurement.zzel> it;
        ArrayMap arrayMap;
        Iterator<com.google.android.gms.internal.measurement.zzfs> it2;
        Iterator<com.google.android.gms.internal.measurement.zzel> it3;
        com.google.android.gms.internal.measurement.zzel next;
        boolean z2;
        String strZze;
        Object obj;
        Boolean boolZzh;
        Boolean boolZzg;
        String str;
        Boolean boolZzi;
        com.google.android.gms.internal.measurement.zzfs next2;
        Long lValueOf;
        Double dValueOf;
        com.google.android.gms.internal.measurement.zzel next3;
        zzoa.zzc();
        boolean zZzs = this.zza.zzs.zzf().zzs(this.zzb, zzdy.zzY);
        long j2 = this.zzh.zzn() ? zzapVar.zze : j;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        bool = null;
        Boolean bool = null;
        if (Log.isLoggable(this.zza.zzs.zzay().zzq(), 2)) {
            this.zza.zzs.zzay().zzj().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(this.zzc), this.zzh.zzp() ? Integer.valueOf(this.zzh.zzb()) : null, this.zza.zzs.zzj().zzd(this.zzh.zzg()));
            this.zza.zzs.zzay().zzj().zzb("Filter definition", this.zza.zzf.zzu().zzo(this.zzh));
        }
        if (!this.zzh.zzp() || this.zzh.zzb() > 256) {
            this.zza.zzs.zzay().zzk().zzc("Invalid event filter ID. appId, id", zzel.zzn(this.zzb), String.valueOf(this.zzh.zzp() ? Integer.valueOf(this.zzh.zzb()) : null));
            return false;
        }
        Object[] objArr = this.zzh.zzk() || this.zzh.zzm() || this.zzh.zzn();
        if (z && objArr != true) {
            this.zza.zzs.zzay().zzj().zzc("Event filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.zzc), this.zzh.zzp() ? Integer.valueOf(this.zzh.zzb()) : null);
            return true;
        }
        com.google.android.gms.internal.measurement.zzej zzejVar = this.zzh;
        String strZzh = zzfoVar.zzh();
        if (!zzejVar.zzo()) {
            hashSet = new HashSet();
            it = zzejVar.zzh().iterator();
            while (true) {
                if (it.hasNext()) {
                    arrayMap = new ArrayMap();
                    it2 = zzfoVar.zzi().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            it3 = zzejVar.zzh().iterator();
                            while (true) {
                                if (it3.hasNext()) {
                                    bool = true;
                                    break;
                                }
                                next = it3.next();
                                if (next.zzh() || !next.zzg()) {
                                    z2 = false;
                                } else {
                                    z2 = true;
                                }
                                strZze = next.zze();
                                if (strZze.isEmpty()) {
                                    obj = arrayMap.get(strZze);
                                    if (obj instanceof Long) {
                                        if (obj instanceof Double) {
                                            if (obj instanceof String) {
                                                if (obj == null) {
                                                    this.zza.zzs.zzay().zzk().zzc("Unknown param type. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                                    break;
                                                }
                                                this.zza.zzs.zzay().zzj().zzc("Missing param for filter. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                                bool = false;
                                                break;
                                            }
                                            if (next.zzk()) {
                                                if (next.zzi()) {
                                                    this.zza.zzs.zzay().zzk().zzc("No filter for String param. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                                    break;
                                                }
                                                str = (String) obj;
                                                if (zzku.zzy(str)) {
                                                    this.zza.zzs.zzay().zzk().zzc("Invalid param value for number filter. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                                    break;
                                                }
                                                boolZzi = zzx.zzi(str, next.zzc());
                                            } else {
                                                boolZzi = zzx.zzf((String) obj, next.zzd(), this.zza.zzs.zzay());
                                            }
                                            if (boolZzi != null) {
                                                break;
                                            }
                                            if (boolZzi.booleanValue() == z2) {
                                                bool = false;
                                                break;
                                            }
                                        } else {
                                            if (next.zzi()) {
                                                this.zza.zzs.zzay().zzk().zzc("No number filter for double param. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                                break;
                                            }
                                            boolZzg = zzx.zzg(((Double) obj).doubleValue(), next.zzc());
                                            if (boolZzg != null) {
                                                break;
                                            }
                                            if (boolZzg.booleanValue() == z2) {
                                                bool = false;
                                                break;
                                            }
                                        }
                                    } else {
                                        if (next.zzi()) {
                                            this.zza.zzs.zzay().zzk().zzc("No number filter for long param. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                            break;
                                        }
                                        boolZzh = zzx.zzh(((Long) obj).longValue(), next.zzc());
                                        if (boolZzh != null) {
                                            break;
                                        }
                                        if (boolZzh.booleanValue() == z2) {
                                            bool = false;
                                            break;
                                        }
                                    }
                                } else {
                                    this.zza.zzs.zzay().zzk().zzb("Event has empty param name. event", this.zza.zzs.zzj().zzd(strZzh));
                                    break;
                                }
                            }
                        } else {
                            next2 = it2.next();
                            if (!hashSet.contains(next2.zzg())) {
                                if (next2.zzw()) {
                                    if (next2.zzu()) {
                                        if (next2.zzy()) {
                                            this.zza.zzs.zzay().zzk().zzc("Unknown value for param. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(next2.zzg()));
                                            break;
                                        }
                                        arrayMap.put(next2.zzg(), next2.zzh());
                                    } else {
                                        String strZzg = next2.zzg();
                                        if (next2.zzu()) {
                                            dValueOf = Double.valueOf(next2.zza());
                                        } else {
                                            dValueOf = null;
                                        }
                                        arrayMap.put(strZzg, dValueOf);
                                    }
                                } else {
                                    String strZzg2 = next2.zzg();
                                    if (next2.zzw()) {
                                        lValueOf = Long.valueOf(next2.zzd());
                                    } else {
                                        lValueOf = null;
                                    }
                                    arrayMap.put(strZzg2, lValueOf);
                                }
                            }
                        }
                    }
                } else {
                    next3 = it.next();
                    if (next3.zze().isEmpty()) {
                        this.zza.zzs.zzay().zzk().zzb("null or empty param name in filter. event", this.zza.zzs.zzj().zzd(strZzh));
                        break;
                    }
                    hashSet.add(next3.zze());
                }
            }
        } else {
            Boolean boolZzh2 = zzx.zzh(j2, zzejVar.zzf());
            if (boolZzh2 != null) {
                if (boolZzh2.booleanValue()) {
                    hashSet = new HashSet();
                    it = zzejVar.zzh().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            arrayMap = new ArrayMap();
                            it2 = zzfoVar.zzi().iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    it3 = zzejVar.zzh().iterator();
                                    while (true) {
                                        if (it3.hasNext()) {
                                            bool = true;
                                            break;
                                        }
                                        next = it3.next();
                                        if (next.zzh()) {
                                            z2 = false;
                                        } else {
                                            z2 = false;
                                        }
                                        strZze = next.zze();
                                        if (strZze.isEmpty()) {
                                            obj = arrayMap.get(strZze);
                                            if (obj instanceof Long) {
                                                if (obj instanceof Double) {
                                                    if (obj instanceof String) {
                                                        if (obj == null) {
                                                            this.zza.zzs.zzay().zzk().zzc("Unknown param type. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                                            break;
                                                        }
                                                        this.zza.zzs.zzay().zzj().zzc("Missing param for filter. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                                        bool = false;
                                                        break;
                                                    }
                                                    if (next.zzk()) {
                                                        if (next.zzi()) {
                                                            this.zza.zzs.zzay().zzk().zzc("No filter for String param. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                                            break;
                                                        }
                                                        str = (String) obj;
                                                        if (zzku.zzy(str)) {
                                                            this.zza.zzs.zzay().zzk().zzc("Invalid param value for number filter. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                                            break;
                                                        }
                                                        boolZzi = zzx.zzi(str, next.zzc());
                                                    } else {
                                                        boolZzi = zzx.zzf((String) obj, next.zzd(), this.zza.zzs.zzay());
                                                    }
                                                    if (boolZzi != null) {
                                                        break;
                                                        break;
                                                    }
                                                    if (boolZzi.booleanValue() == z2) {
                                                        bool = false;
                                                        break;
                                                    }
                                                } else if (next.zzi()) {
                                                    boolZzg = zzx.zzg(((Double) obj).doubleValue(), next.zzc());
                                                    if (boolZzg != null) {
                                                        break;
                                                        break;
                                                    }
                                                    if (boolZzg.booleanValue() == z2) {
                                                        bool = false;
                                                        break;
                                                    }
                                                } else {
                                                    this.zza.zzs.zzay().zzk().zzc("No number filter for double param. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                                    break;
                                                }
                                            } else if (next.zzi()) {
                                                boolZzh = zzx.zzh(((Long) obj).longValue(), next.zzc());
                                                if (boolZzh != null) {
                                                    break;
                                                    break;
                                                }
                                                if (boolZzh.booleanValue() == z2) {
                                                    bool = false;
                                                    break;
                                                }
                                            } else {
                                                this.zza.zzs.zzay().zzk().zzc("No number filter for long param. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(strZze));
                                                break;
                                            }
                                        } else {
                                            this.zza.zzs.zzay().zzk().zzb("Event has empty param name. event", this.zza.zzs.zzj().zzd(strZzh));
                                            break;
                                        }
                                    }
                                } else {
                                    next2 = it2.next();
                                    if (!hashSet.contains(next2.zzg())) {
                                        if (next2.zzw()) {
                                            if (next2.zzu()) {
                                                if (next2.zzy()) {
                                                    this.zza.zzs.zzay().zzk().zzc("Unknown value for param. event, param", this.zza.zzs.zzj().zzd(strZzh), this.zza.zzs.zzj().zze(next2.zzg()));
                                                    break;
                                                }
                                                arrayMap.put(next2.zzg(), next2.zzh());
                                            } else {
                                                String strZzg3 = next2.zzg();
                                                if (next2.zzu()) {
                                                    dValueOf = Double.valueOf(next2.zza());
                                                } else {
                                                    dValueOf = null;
                                                }
                                                arrayMap.put(strZzg3, dValueOf);
                                            }
                                        } else {
                                            String strZzg4 = next2.zzg();
                                            if (next2.zzw()) {
                                                lValueOf = Long.valueOf(next2.zzd());
                                            } else {
                                                lValueOf = null;
                                            }
                                            arrayMap.put(strZzg4, lValueOf);
                                        }
                                    }
                                }
                            }
                        } else {
                            next3 = it.next();
                            if (next3.zze().isEmpty()) {
                                this.zza.zzs.zzay().zzk().zzb("null or empty param name in filter. event", this.zza.zzs.zzj().zzd(strZzh));
                                break;
                            }
                            hashSet.add(next3.zze());
                        }
                    }
                } else {
                    bool = false;
                }
            }
        }
        this.zza.zzs.zzay().zzj().zzb("Event filter result", bool == null ? "null" : bool);
        if (bool == null) {
            return false;
        }
        this.zzd = true;
        if (!bool.booleanValue()) {
            return true;
        }
        this.zze = true;
        if (objArr != false && zzfoVar.zzu()) {
            Long lValueOf2 = Long.valueOf(zzfoVar.zzd());
            if (this.zzh.zzm()) {
                if (zZzs && this.zzh.zzo()) {
                    lValueOf2 = l;
                }
                this.zzg = lValueOf2;
            } else {
                if (zZzs && this.zzh.zzo()) {
                    lValueOf2 = l2;
                }
                this.zzf = lValueOf2;
            }
        }
        return true;
    }
}
