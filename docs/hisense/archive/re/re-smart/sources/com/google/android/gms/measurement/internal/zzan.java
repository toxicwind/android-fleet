package com.google.android.gms.measurement.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import androidx.annotation.WorkerThread;
import androidx.core.content.ContextCompat;
import b.a.a.a.a;
import com.google.android.gms.common.internal.AccountType;
import com.mediatek.twoworlds.tv.MtkTvScanDvbsBase;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class zzan extends zzgp {
    public long zza;
    public String zzb;
    public AccountManager zzc;
    public Boolean zzd;
    public long zze;

    public zzan(zzfv zzfvVar) {
        super(zzfvVar);
    }

    @WorkerThread
    public final long zza() {
        zzg();
        return this.zze;
    }

    public final long zzb() {
        zzu();
        return this.zza;
    }

    public final String zzc() {
        zzu();
        return this.zzb;
    }

    @WorkerThread
    public final void zzd() {
        zzg();
        this.zzd = null;
        this.zze = 0L;
    }

    @WorkerThread
    public final boolean zze() {
        zzg();
        long jCurrentTimeMillis = this.zzs.zzav().currentTimeMillis();
        if (jCurrentTimeMillis - this.zze > 86400000) {
            this.zzd = null;
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (ContextCompat.checkSelfPermission(this.zzs.zzau(), "android.permission.GET_ACCOUNTS") != 0) {
            this.zzs.zzay().zzm().zza("Permission error checking for dasher/unicorn accounts");
            this.zze = jCurrentTimeMillis;
            this.zzd = false;
            return false;
        }
        if (this.zzc == null) {
            this.zzc = AccountManager.get(this.zzs.zzau());
        }
        try {
            Account[] result = this.zzc.getAccountsByTypeAndFeatures(AccountType.GOOGLE, new String[]{"service_HOSTED"}, null, null).getResult();
            if (result != null && result.length > 0) {
                this.zzd = true;
                this.zze = jCurrentTimeMillis;
                return true;
            }
            Account[] result2 = this.zzc.getAccountsByTypeAndFeatures(AccountType.GOOGLE, new String[]{"service_uca"}, null, null).getResult();
            if (result2 != null && result2.length > 0) {
                this.zzd = true;
                this.zze = jCurrentTimeMillis;
                return true;
            }
            this.zze = jCurrentTimeMillis;
            this.zzd = false;
            return false;
        } catch (AuthenticatorException | OperationCanceledException | IOException e2) {
            this.zzs.zzay().zzh().zzb("Exception checking account types", e2);
        }
    }

    @Override // com.google.android.gms.measurement.internal.zzgp
    public final boolean zzf() {
        Calendar calendar = Calendar.getInstance();
        this.zza = TimeUnit.MINUTES.convert(calendar.get(16) + calendar.get(15), TimeUnit.MILLISECONDS);
        Locale locale = Locale.getDefault();
        String lowerCase = locale.getLanguage().toLowerCase(Locale.ENGLISH);
        String lowerCase2 = locale.getCountry().toLowerCase(Locale.ENGLISH);
        this.zzb = a.a(new StringBuilder(String.valueOf(lowerCase).length() + 1 + String.valueOf(lowerCase2).length()), lowerCase, MtkTvScanDvbsBase.TVAPI_DVBS_LINK_STRING, lowerCase2);
        return false;
    }
}
