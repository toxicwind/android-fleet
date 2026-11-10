package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
import b.a.a.a.a;
import com.hmct.cloud.sdk.utils.Params;

/* JADX INFO: loaded from: classes.dex */
public final class zzkg extends zzki {
    public final AlarmManager zza;
    public zzam zzb;
    public Integer zzc;

    public zzkg(zzks zzksVar) {
        super(zzksVar);
        this.zza = (AlarmManager) this.zzs.zzau().getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    private final int zzf() {
        if (this.zzc == null) {
            String strValueOf = String.valueOf(this.zzs.zzau().getPackageName());
            this.zzc = Integer.valueOf((strValueOf.length() != 0 ? "measurement".concat(strValueOf) : new String("measurement")).hashCode());
        }
        return this.zzc.intValue();
    }

    private final PendingIntent zzh() {
        Context contextZzau = this.zzs.zzau();
        return com.google.android.gms.internal.measurement.zzbs.zza(contextZzau, 0, new Intent().setClassName(contextZzau, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), com.google.android.gms.internal.measurement.zzbs.zza);
    }

    private final zzam zzi() {
        if (this.zzb == null) {
            this.zzb = new zzkf(this, this.zzf.zzq());
        }
        return this.zzb;
    }

    @TargetApi(24)
    private final void zzj() {
        JobScheduler jobScheduler = (JobScheduler) this.zzs.zzau().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(zzf());
        }
    }

    public final void zza() {
        zzY();
        a.b(this.zzs, "Unscheduling upload");
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzh());
        }
        zzi().zzb();
        int i = Build.VERSION.SDK_INT;
        zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzki
    public final boolean zzb() {
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzh());
        }
        int i = Build.VERSION.SDK_INT;
        zzj();
        return false;
    }

    public final void zzd(long j) {
        zzY();
        this.zzs.zzaw();
        Context contextZzau = this.zzs.zzau();
        if (!zzkz.zzai(contextZzau)) {
            this.zzs.zzay().zzc().zza("Receiver not registered/enabled");
        }
        if (!zzkz.zzaj(contextZzau, false)) {
            this.zzs.zzay().zzc().zza("Service not registered/enabled");
        }
        zza();
        this.zzs.zzay().zzj().zzb("Scheduling upload, millis", Long.valueOf(j));
        this.zzs.zzav().elapsedRealtime();
        this.zzs.zzf();
        if (j < Math.max(0L, zzdy.zzw.zza(null).longValue()) && !zzi().zze()) {
            zzi().zzd(j);
        }
        this.zzs.zzaw();
        int i = Build.VERSION.SDK_INT;
        Context contextZzau2 = this.zzs.zzau();
        ComponentName componentName = new ComponentName(contextZzau2, "com.google.android.gms.measurement.AppMeasurementJobService");
        int iZzf = zzf();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString(Params.ACTION, "com.google.android.gms.measurement.UPLOAD");
        com.google.android.gms.internal.measurement.zzbt.zza(contextZzau2, new JobInfo.Builder(iZzf, componentName).setMinimumLatency(j).setOverrideDeadline(j + j).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
    }
}
